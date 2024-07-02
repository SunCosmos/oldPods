package bsh;

import java.io.Serializable;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class Name implements Serializable {
    private static String FINISHED;
    Class asClass;
    private int callstackDepth;
    Class classOfStaticMethod;
    private Object evalBaseObject;
    private String evalName;
    private String lastEvalName;
    public NameSpace namespace;
    String value;

    public Name(NameSpace nameSpace, String str) {
        this.value = null;
        this.namespace = nameSpace;
        this.value = str;
    }

    private Object completeRound(String str, String str2, Object obj) {
        if (obj != null) {
            this.lastEvalName = str;
            this.evalName = str2;
            this.evalBaseObject = obj;
            return obj;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("lastEvalName = ");
        stringBuffer.append(str);
        throw new InterpreterError(stringBuffer.toString());
    }

    private Object consumeNextObjectField(CallStack callStack, Interpreter interpreter, boolean z, boolean z2) {
        NameSpace nameSpace;
        boolean z3;
        Object resolveThisFieldReference;
        if (this.evalBaseObject == null && !isCompound(this.evalName) && !z && (resolveThisFieldReference = resolveThisFieldReference(callStack, this.namespace, interpreter, this.evalName, false)) != Primitive.VOID) {
            return completeRound(this.evalName, FINISHED, resolveThisFieldReference);
        }
        String prefix = prefix(this.evalName, 1);
        Object obj = this.evalBaseObject;
        if ((obj == null || (obj instanceof This)) && !z) {
            if (Interpreter.DEBUG) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("trying to resolve variable: ");
                stringBuffer.append(prefix);
                Interpreter.debug(stringBuffer.toString());
            }
            Object obj2 = this.evalBaseObject;
            if (obj2 == null) {
                nameSpace = this.namespace;
                z3 = false;
            } else {
                nameSpace = ((This) obj2).namespace;
                z3 = true;
            }
            Object resolveThisFieldReference2 = resolveThisFieldReference(callStack, nameSpace, interpreter, prefix, z3);
            if (resolveThisFieldReference2 != Primitive.VOID) {
                if (Interpreter.DEBUG) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("resolved variable: ");
                    stringBuffer2.append(prefix);
                    stringBuffer2.append(" in namespace: ");
                    stringBuffer2.append(this.namespace);
                    Interpreter.debug(stringBuffer2.toString());
                }
                return completeRound(prefix, suffix(this.evalName), resolveThisFieldReference2);
            }
        }
        Object obj3 = null;
        if (this.evalBaseObject == null) {
            if (Interpreter.DEBUG) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("trying class: ");
                stringBuffer3.append(this.evalName);
                Interpreter.debug(stringBuffer3.toString());
            }
            Class cls = null;
            String str = null;
            int i2 = 1;
            while (i2 <= countParts(this.evalName) && (cls = this.namespace.getClass((str = prefix(this.evalName, i2)))) == null) {
                i2++;
            }
            if (cls != null) {
                String str2 = this.evalName;
                return completeRound(str, suffix(str2, countParts(str2) - i2), new ClassIdentifier(cls));
            }
            if (Interpreter.DEBUG) {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("not a class, trying var prefix ");
                stringBuffer4.append(this.evalName);
                Interpreter.debug(stringBuffer4.toString());
            }
        }
        Object obj4 = this.evalBaseObject;
        if ((obj4 == null || (obj4 instanceof This)) && !z && z2) {
            NameSpace nameSpace2 = obj4 == null ? this.namespace : ((This) obj4).namespace;
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("auto: ");
            stringBuffer5.append(prefix);
            This r10 = new NameSpace(nameSpace2, stringBuffer5.toString()).getThis(interpreter);
            nameSpace2.setVariable(prefix, r10, false);
            return completeRound(prefix, suffix(this.evalName), r10);
        }
        if (obj4 == null) {
            if (!isCompound(this.evalName)) {
                return completeRound(this.evalName, FINISHED, Primitive.VOID);
            }
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append("Class or variable not found: ");
            stringBuffer6.append(this.evalName);
            throw new UtilEvalError(stringBuffer6.toString());
        }
        if (obj4 == Primitive.NULL) {
            StringBuffer stringBuffer7 = new StringBuffer();
            stringBuffer7.append("Null Pointer while evaluating: ");
            stringBuffer7.append(this.value);
            throw new UtilTargetError(new NullPointerException(stringBuffer7.toString()));
        }
        if (obj4 == Primitive.VOID) {
            StringBuffer stringBuffer8 = new StringBuffer();
            stringBuffer8.append("Undefined variable or class name while evaluating: ");
            stringBuffer8.append(this.value);
            throw new UtilEvalError(stringBuffer8.toString());
        }
        if (obj4 instanceof Primitive) {
            StringBuffer stringBuffer9 = new StringBuffer();
            stringBuffer9.append("Can't treat primitive like an object. Error while evaluating: ");
            stringBuffer9.append(this.value);
            throw new UtilEvalError(stringBuffer9.toString());
        }
        if (!(obj4 instanceof ClassIdentifier)) {
            if (z) {
                StringBuffer stringBuffer10 = new StringBuffer();
                stringBuffer10.append(this.value);
                stringBuffer10.append(" does not resolve to a class name.");
                throw new UtilEvalError(stringBuffer10.toString());
            }
            String prefix2 = prefix(this.evalName, 1);
            if (prefix2.equals("length") && this.evalBaseObject.getClass().isArray()) {
                return completeRound(prefix2, suffix(this.evalName), new Primitive(Array.getLength(this.evalBaseObject)));
            }
            try {
                return completeRound(prefix2, suffix(this.evalName), Reflect.getObjectFieldValue(this.evalBaseObject, prefix2));
            } catch (ReflectError unused) {
                StringBuffer stringBuffer11 = new StringBuffer();
                stringBuffer11.append("Cannot access field: ");
                stringBuffer11.append(prefix2);
                stringBuffer11.append(", on object: ");
                stringBuffer11.append(this.evalBaseObject);
                throw new UtilEvalError(stringBuffer11.toString());
            }
        }
        Class<?> targetClass = ((ClassIdentifier) obj4).getTargetClass();
        String prefix3 = prefix(this.evalName, 1);
        if (prefix3.equals("this")) {
            for (NameSpace nameSpace3 = this.namespace; nameSpace3 != null; nameSpace3 = nameSpace3.getParent()) {
                Object obj5 = nameSpace3.classInstance;
                if (obj5 != null && obj5.getClass() == targetClass) {
                    return completeRound(prefix3, suffix(this.evalName), nameSpace3.classInstance);
                }
            }
            StringBuffer stringBuffer12 = new StringBuffer();
            stringBuffer12.append("Can't find enclosing 'this' instance of class: ");
            stringBuffer12.append(targetClass);
            throw new UtilEvalError(stringBuffer12.toString());
        }
        try {
            if (Interpreter.DEBUG) {
                StringBuffer stringBuffer13 = new StringBuffer();
                stringBuffer13.append("Name call to getStaticFieldValue, class: ");
                stringBuffer13.append(targetClass);
                stringBuffer13.append(", field:");
                stringBuffer13.append(prefix3);
                Interpreter.debug(stringBuffer13.toString());
            }
            obj3 = Reflect.getStaticFieldValue(targetClass, prefix3);
        } catch (ReflectError e) {
            if (Interpreter.DEBUG) {
                StringBuffer stringBuffer14 = new StringBuffer();
                stringBuffer14.append("field reflect error: ");
                stringBuffer14.append(e);
                Interpreter.debug(stringBuffer14.toString());
            }
        }
        if (obj3 == null) {
            StringBuffer stringBuffer15 = new StringBuffer();
            stringBuffer15.append(targetClass.getName());
            stringBuffer15.append("$");
            stringBuffer15.append(prefix3);
            Class cls2 = this.namespace.getClass(stringBuffer15.toString());
            if (cls2 != null) {
                obj3 = new ClassIdentifier(cls2);
            }
        }
        if (obj3 != null) {
            return completeRound(prefix3, suffix(this.evalName), obj3);
        }
        StringBuffer stringBuffer16 = new StringBuffer();
        stringBuffer16.append("No static field or inner class: ");
        stringBuffer16.append(prefix3);
        stringBuffer16.append(" of ");
        stringBuffer16.append(targetClass);
        throw new UtilEvalError(stringBuffer16.toString());
    }

    static int countParts(String str) {
        int i2 = 0;
        if (str == null) {
            return 0;
        }
        int i3 = -1;
        while (true) {
            i3 = str.indexOf(46, i3 + 1);
            if (i3 == -1) {
                return i2 + 1;
            }
            i2++;
        }
    }

    public static NameSpace getClassNameSpace(NameSpace nameSpace) {
        if (nameSpace.isClass) {
            return nameSpace;
        }
        if (nameSpace.isMethod && nameSpace.getParent() != null && nameSpace.getParent().isClass) {
            return nameSpace.getParent();
        }
        return null;
    }

    private Object invokeLocalMethod(Interpreter interpreter, Object[] objArr, CallStack callStack, SimpleNode simpleNode) {
        if (Interpreter.DEBUG) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("invokeLocalMethod: ");
            stringBuffer.append(this.value);
            Interpreter.debug(stringBuffer.toString());
        }
        if (interpreter == null) {
            throw new InterpreterError("invokeLocalMethod: interpreter = null");
        }
        String str = this.value;
        Class[] types = Types.getTypes(objArr);
        try {
            BshMethod method = this.namespace.getMethod(str, types);
            if (method != null) {
                return method.invoke(objArr, interpreter, callStack, simpleNode);
            }
            interpreter.getClassManager();
            try {
                Object command = this.namespace.getCommand(str, types, interpreter);
                if (command != null) {
                    if (command instanceof BshMethod) {
                        return ((BshMethod) command).invoke(objArr, interpreter, callStack, simpleNode);
                    }
                    if (!(command instanceof Class)) {
                        throw new InterpreterError("invalid command type");
                    }
                    try {
                        return Reflect.invokeCompiledCommand((Class) command, objArr, interpreter, callStack);
                    } catch (UtilEvalError e) {
                        throw e.toEvalError("Error invoking compiled command: ", simpleNode, callStack);
                    }
                }
                try {
                    BshMethod method2 = this.namespace.getMethod("invoke", new Class[]{null, null});
                    if (method2 != null) {
                        return method2.invoke(new Object[]{str, objArr}, interpreter, callStack, simpleNode);
                    }
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Command not found: ");
                    stringBuffer2.append(StringUtil.methodString(str, types));
                    throw new EvalError(stringBuffer2.toString(), simpleNode, callStack);
                } catch (UtilEvalError e2) {
                    throw e2.toEvalError("Local method invocation", simpleNode, callStack);
                }
            } catch (UtilEvalError e3) {
                throw e3.toEvalError("Error loading command: ", simpleNode, callStack);
            }
        } catch (UtilEvalError e4) {
            throw e4.toEvalError("Local method invocation", simpleNode, callStack);
        }
    }

    public static boolean isCompound(String str) {
        return str.indexOf(46) != -1;
    }

    static String prefix(String str) {
        if (isCompound(str)) {
            return prefix(str, countParts(str) - 1);
        }
        return null;
    }

    static String prefix(String str, int i2) {
        if (i2 < 1) {
            return null;
        }
        int i3 = -1;
        int i4 = 0;
        do {
            i3 = str.indexOf(46, i3 + 1);
            if (i3 == -1) {
                break;
            }
            i4++;
        } while (i4 < i2);
        return i3 == -1 ? str : str.substring(0, i3);
    }

    private void reset() {
        this.evalName = this.value;
        this.evalBaseObject = null;
        this.callstackDepth = 0;
    }

    static String suffix(String str) {
        if (isCompound(str)) {
            return suffix(str, countParts(str) - 1);
        }
        return null;
    }

    public static String suffix(String str, int i2) {
        if (i2 < 1) {
            return null;
        }
        int i3 = 0;
        int length = str.length() + 1;
        do {
            length = str.lastIndexOf(46, length - 1);
            if (length == -1) {
                break;
            }
            i3++;
        } while (i3 < i2);
        return length == -1 ? str : str.substring(length + 1);
    }

    public Object invokeMethod(Interpreter interpreter, Object[] objArr, CallStack callStack, SimpleNode simpleNode) {
        NameSpace classNameSpace;
        String suffix = suffix(this.value, 1);
        BshClassManager classManager = interpreter.getClassManager();
        NameSpace pVar = callStack.top();
        Class cls = this.classOfStaticMethod;
        if (cls != null) {
            return Reflect.invokeStaticMethod(classManager, cls, suffix, objArr);
        }
        if (!isCompound(this.value)) {
            return invokeLocalMethod(interpreter, objArr, callStack, simpleNode);
        }
        String prefix = prefix(this.value);
        if (prefix.equals("super") && countParts(this.value) == 2 && (classNameSpace = getClassNameSpace(pVar.getThis(interpreter).getNameSpace())) != null) {
            return ClassGenerator.getClassGenerator().invokeSuperclassMethod(classManager, classNameSpace.getClassInstance(), suffix, objArr);
        }
        Name nameResolver = pVar.getNameResolver(prefix);
        Object object = nameResolver.toObject(callStack, interpreter);
        if (object == Primitive.VOID) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Attempt to resolve method: ");
            stringBuffer.append(suffix);
            stringBuffer.append("() on undefined variable or class name: ");
            stringBuffer.append(nameResolver);
            throw new UtilEvalError(stringBuffer.toString());
        }
        if (!(object instanceof ClassIdentifier)) {
            if (object instanceof Primitive) {
                if (object == Primitive.NULL) {
                    throw new UtilTargetError(new NullPointerException("Null Pointer in Method Invocation"));
                }
                if (Interpreter.DEBUG) {
                    Interpreter.debug("Attempt to access method on primitive... allowing bsh.Primitive to peek through for debugging");
                }
            }
            return Reflect.invokeObjectMethod(object, suffix, objArr, interpreter, callStack, simpleNode);
        }
        if (Interpreter.DEBUG) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("invokeMethod: trying static - ");
            stringBuffer2.append(nameResolver);
            Interpreter.debug(stringBuffer2.toString());
        }
        Class targetClass = ((ClassIdentifier) object).getTargetClass();
        this.classOfStaticMethod = targetClass;
        if (targetClass != null) {
            return Reflect.invokeStaticMethod(classManager, targetClass, suffix, objArr);
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("invokeMethod: unknown target: ");
        stringBuffer3.append(nameResolver);
        throw new UtilEvalError(stringBuffer3.toString());
    }

    Object resolveThisFieldReference(CallStack callStack, NameSpace nameSpace, Interpreter interpreter, String str, boolean z) {
        Object obj;
        if (str.equals("this")) {
            if (z) {
                throw new UtilEvalError("Redundant to call .this on This type");
            }
            This r6 = nameSpace.getThis(interpreter);
            NameSpace classNameSpace = getClassNameSpace(r6.getNameSpace());
            return classNameSpace != null ? isCompound(this.evalName) ? classNameSpace.getThis(interpreter) : classNameSpace.getClassInstance() : r6;
        }
        if (str.equals("super")) {
            This r62 = nameSpace.getSuper(interpreter);
            NameSpace nameSpace2 = r62.getNameSpace();
            return (nameSpace2.getParent() == null || !nameSpace2.getParent().isClass) ? r62 : nameSpace2.getParent().getThis(interpreter);
        }
        Object global = str.equals("global") ? nameSpace.getGlobal(interpreter) : null;
        if (global == null && z) {
            if (str.equals("namespace")) {
                global = nameSpace;
            } else if (str.equals("variables")) {
                global = nameSpace.getVariableNames();
            } else if (str.equals("methods")) {
                global = nameSpace.getMethodNames();
            } else if (str.equals("interpreter")) {
                if (!this.lastEvalName.equals("this")) {
                    throw new UtilEvalError("Can only call .interpreter on literal 'this'");
                }
                global = interpreter;
            }
        }
        if (global == null && z && str.equals("caller")) {
            if (!this.lastEvalName.equals("this") && !this.lastEvalName.equals("caller")) {
                throw new UtilEvalError("Can only call .caller on literal 'this' or literal '.caller'");
            }
            if (callStack == null) {
                throw new InterpreterError("no callstack");
            }
            int i2 = this.callstackDepth + 1;
            this.callstackDepth = i2;
            return callStack.get(i2).getThis(interpreter);
        }
        if (global == null && z && str.equals("callstack")) {
            obj = callStack;
            if (!this.lastEvalName.equals("this")) {
                throw new UtilEvalError("Can only call .callstack on literal 'this'");
            }
            if (callStack == null) {
                throw new InterpreterError("no callstack");
            }
        } else {
            obj = global;
        }
        if (obj == null) {
            obj = nameSpace.getVariable(str);
        }
        if (obj != null) {
            return obj;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("null this field ref:");
        stringBuffer.append(str);
        throw new InterpreterError(stringBuffer.toString());
    }

    public synchronized Class toClass() {
        Class cls = this.asClass;
        if (cls != null) {
            return cls;
        }
        reset();
        Object obj = null;
        if (this.evalName.equals("var")) {
            this.asClass = null;
            return null;
        }
        Class cls2 = this.namespace.getClass(this.evalName);
        if (cls2 == null) {
            try {
                obj = toObject(null, null, true);
            } catch (UtilEvalError unused) {
            }
            if (obj instanceof ClassIdentifier) {
                cls2 = ((ClassIdentifier) obj).getTargetClass();
            }
        }
        if (cls2 != null) {
            this.asClass = cls2;
            return cls2;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Class: ");
        stringBuffer.append(this.value);
        stringBuffer.append(" not found in namespace");
        throw new ClassNotFoundException(stringBuffer.toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0062, code lost:
    
        if ((r0 instanceof bsh.This) == false) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006a, code lost:
    
        if (r5.equals("namespace") != false) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0074, code lost:
    
        if (r4.evalName.equals("variables") != false) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007e, code lost:
    
        if (r4.evalName.equals("methods") != false) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0088, code lost:
    
        if (r4.evalName.equals("caller") != false) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008a, code lost:
    
        bsh.Interpreter.debug("found This reference evaluating LHS");
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0097, code lost:
    
        if (r4.lastEvalName.equals("super") != false) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0099, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a6, code lost:
    
        return new bsh.LHS(((bsh.This) r0).namespace, r4.evalName, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a7, code lost:
    
        r6 = new java.lang.StringBuffer();
        r6.append("Can't assign to special variable: ");
        r6.append(r4.evalName);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00bf, code lost:
    
        throw new bsh.UtilEvalError(r6.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00c0, code lost:
    
        if (r5 == null) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00f9, code lost:
    
        throw new bsh.InterpreterError("Internal error in lhs...");
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00c4, code lost:
    
        if ((r0 instanceof bsh.ClassIdentifier) == false) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d3, code lost:
    
        return bsh.Reflect.getLHSStaticField(((bsh.ClassIdentifier) r0).getTargetClass(), r4.evalName);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00d9, code lost:
    
        return bsh.Reflect.getLHSObjectField(r0, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00da, code lost:
    
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00db, code lost:
    
        r0 = new java.lang.StringBuffer();
        r0.append("Field access: ");
        r0.append(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00f1, code lost:
    
        throw new bsh.UtilEvalError(r0.toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized bsh.LHS toLHS(bsh.CallStack r5, bsh.Interpreter r6) {
        /*
            Method dump skipped, instructions count: 308
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.Name.toLHS(bsh.CallStack, bsh.Interpreter):bsh.LHS");
    }

    public Object toObject(CallStack callStack, Interpreter interpreter) {
        return toObject(callStack, interpreter, false);
    }

    public synchronized Object toObject(CallStack callStack, Interpreter interpreter, boolean z) {
        Object obj;
        reset();
        obj = null;
        while (this.evalName != null) {
            obj = consumeNextObjectField(callStack, interpreter, z, false);
        }
        if (obj == null) {
            throw new InterpreterError("null value in toObject()");
        }
        return obj;
    }

    public String toString() {
        return this.value;
    }
}
