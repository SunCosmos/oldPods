package bsh;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class BshMethod implements Serializable {
    private Class[] cparamTypes;
    private Class creturnType;
    NameSpace declaringNameSpace;
    private Method javaMethod;
    private Object javaObject;
    BSHBlock methodBody;
    Modifiers modifiers;
    private String name;
    private int numArgs;
    private String[] paramNames;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BshMethod(BSHMethodDeclaration bSHMethodDeclaration, NameSpace nameSpace, Modifiers modifiers) {
        this(bSHMethodDeclaration.name, bSHMethodDeclaration.returnType, bSHMethodDeclaration.paramsNode.getParamNames(), bSHMethodDeclaration.paramsNode.paramTypes, bSHMethodDeclaration.blockNode, nameSpace, modifiers);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BshMethod(String str, Class cls, String[] strArr, Class[] clsArr, BSHBlock bSHBlock, NameSpace nameSpace, Modifiers modifiers) {
        this.name = str;
        this.creturnType = cls;
        this.paramNames = strArr;
        if (strArr != null) {
            this.numArgs = strArr.length;
        }
        this.cparamTypes = clsArr;
        this.methodBody = bSHBlock;
        this.declaringNameSpace = nameSpace;
        this.modifiers = modifiers;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BshMethod(Method method, Object obj) {
        this(method.getName(), method.getReturnType(), null, method.getParameterTypes(), null, null, null);
        this.javaMethod = method;
        this.javaObject = obj;
    }

    private Object invokeImpl(Object[] objArr, Interpreter interpreter, CallStack callStack, SimpleNode simpleNode, boolean z) {
        NameSpace nameSpace;
        Class returnType = getReturnType();
        Class[] parameterTypes = getParameterTypes();
        if (callStack == null) {
            callStack = new CallStack(this.declaringNameSpace);
        }
        int i2 = 0;
        if (objArr == null) {
            objArr = new Object[0];
        }
        if (objArr.length != this.numArgs) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Wrong number of arguments for local method: ");
            stringBuffer.append(this.name);
            throw new EvalError(stringBuffer.toString(), simpleNode, callStack);
        }
        if (z) {
            nameSpace = callStack.top();
        } else {
            nameSpace = new NameSpace(this.declaringNameSpace, this.name);
            nameSpace.isMethod = true;
        }
        nameSpace.setNode(simpleNode);
        while (true) {
            ReturnControl returnControl = null;
            if (i2 >= this.numArgs) {
                if (!z) {
                    callStack.push(nameSpace);
                }
                Object eval = this.methodBody.eval(callStack, interpreter, true);
                CallStack copy = callStack.copy();
                if (!z) {
                    callStack.pop();
                }
                if (eval instanceof ReturnControl) {
                    returnControl = (ReturnControl) eval;
                    if (returnControl.kind != 46) {
                        throw new EvalError("'continue' or 'break' in method body", returnControl.returnPoint, copy);
                    }
                    eval = returnControl.value;
                    if (returnType == Void.TYPE && eval != Primitive.VOID) {
                        throw new EvalError("Cannot return value from void method", returnControl.returnPoint, copy);
                    }
                }
                if (returnType == null) {
                    return eval;
                }
                if (returnType == Void.TYPE) {
                    return Primitive.VOID;
                }
                try {
                    return Types.castObject(eval, returnType, 1);
                } catch (UtilEvalError e) {
                    if (returnControl != null) {
                        simpleNode = returnControl.returnPoint;
                    }
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Incorrect type returned from method: ");
                    stringBuffer2.append(this.name);
                    stringBuffer2.append(e.getMessage());
                    throw e.toEvalError(stringBuffer2.toString(), simpleNode, callStack);
                }
            }
            if (parameterTypes[i2] != null) {
                try {
                    objArr[i2] = Types.castObject(objArr[i2], parameterTypes[i2], 1);
                    try {
                        nameSpace.setTypedVariable(this.paramNames[i2], parameterTypes[i2], objArr[i2], (Modifiers) null);
                    } catch (UtilEvalError e2) {
                        throw e2.toEvalError("Typed method parameter assignment", simpleNode, callStack);
                    }
                } catch (UtilEvalError e3) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Invalid argument: `");
                    stringBuffer3.append(this.paramNames[i2]);
                    stringBuffer3.append("'");
                    stringBuffer3.append(" for method: ");
                    stringBuffer3.append(this.name);
                    stringBuffer3.append(" : ");
                    stringBuffer3.append(e3.getMessage());
                    throw new EvalError(stringBuffer3.toString(), simpleNode, callStack);
                }
            } else {
                if (objArr[i2] == Primitive.VOID) {
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append("Undefined variable or class name, parameter: ");
                    stringBuffer4.append(this.paramNames[i2]);
                    stringBuffer4.append(" to method: ");
                    stringBuffer4.append(this.name);
                    throw new EvalError(stringBuffer4.toString(), simpleNode, callStack);
                }
                try {
                    nameSpace.setLocalVariable(this.paramNames[i2], objArr[i2], interpreter.getStrictJava());
                } catch (UtilEvalError e4) {
                    throw e4.toEvalError(simpleNode, callStack);
                }
            }
            i2++;
        }
    }

    public Modifiers getModifiers() {
        return this.modifiers;
    }

    public String getName() {
        return this.name;
    }

    public String[] getParameterNames() {
        return this.paramNames;
    }

    public Class[] getParameterTypes() {
        return this.cparamTypes;
    }

    public Class getReturnType() {
        return this.creturnType;
    }

    public boolean hasModifier(String str) {
        Modifiers modifiers = this.modifiers;
        return modifiers != null && modifiers.hasModifier(str);
    }

    public Object invoke(Object[] objArr, Interpreter interpreter) {
        return invoke(objArr, interpreter, null, null, false);
    }

    public Object invoke(Object[] objArr, Interpreter interpreter, CallStack callStack, SimpleNode simpleNode) {
        return invoke(objArr, interpreter, callStack, simpleNode, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object invoke(Object[] objArr, Interpreter interpreter, CallStack callStack, SimpleNode simpleNode, boolean z) {
        Object classInstance;
        Object invokeImpl;
        if (objArr != null) {
            for (Object obj : objArr) {
                if (obj == null) {
                    throw new Error("HERE!");
                }
            }
        }
        Method method = this.javaMethod;
        if (method != null) {
            try {
                return Reflect.invokeMethod(method, this.javaObject, objArr);
            } catch (ReflectError e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Error invoking Java method: ");
                stringBuffer.append(e);
                throw new EvalError(stringBuffer.toString(), simpleNode, callStack);
            } catch (InvocationTargetException e2) {
                throw new TargetError("Exception invoking imported object method.", e2, simpleNode, callStack, true);
            }
        }
        Modifiers modifiers = this.modifiers;
        if (modifiers == null || !modifiers.hasModifier("synchronized")) {
            return invokeImpl(objArr, interpreter, callStack, simpleNode, z);
        }
        NameSpace nameSpace = this.declaringNameSpace;
        if (nameSpace.isClass) {
            try {
                classInstance = nameSpace.getClassInstance();
            } catch (UtilEvalError unused) {
                throw new InterpreterError("Can't get class instance for synchronized method.");
            }
        } else {
            classInstance = nameSpace.getThis(interpreter);
        }
        synchronized (classInstance) {
            invokeImpl = invokeImpl(objArr, interpreter, callStack, simpleNode, z);
        }
        return invokeImpl;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Scripted Method: ");
        stringBuffer.append(StringUtil.methodString(this.name, getParameterTypes()));
        return stringBuffer.toString();
    }
}
