package bsh;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
class BSHAllocationExpression extends SimpleNode {
    private static int innerClassCount;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHAllocationExpression(int i2) {
        super(i2);
    }

    private Object arrayAllocation(BSHArrayDimensions bSHArrayDimensions, Class cls, CallStack callStack, Interpreter interpreter) {
        Object eval = bSHArrayDimensions.eval(cls, callStack, interpreter);
        return eval != Primitive.VOID ? eval : arrayNewInstance(cls, bSHArrayDimensions, callStack);
    }

    private Object arrayNewInstance(Class cls, BSHArrayDimensions bSHArrayDimensions, CallStack callStack) {
        int i2 = bSHArrayDimensions.numUndefinedDims;
        if (i2 > 0) {
            cls = Array.newInstance((Class<?>) cls, new int[i2]).getClass();
        }
        try {
            return Array.newInstance((Class<?>) cls, bSHArrayDimensions.definedDimensions);
        } catch (NegativeArraySizeException e) {
            throw new TargetError(e, this, callStack);
        } catch (Exception e2) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Can't construct primitive array: ");
            stringBuffer.append(e2.getMessage());
            throw new EvalError(stringBuffer.toString(), this, callStack);
        }
    }

    private Object constructObject(Class cls, Object[] objArr, CallStack callStack) {
        NameSpace classNameSpace;
        try {
            Object constructObject = Reflect.constructObject(cls, objArr);
            String name = cls.getName();
            if (name.indexOf("$") != -1 && (classNameSpace = Name.getClassNameSpace(callStack.top().getThis(null).getNameSpace())) != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(classNameSpace.getName());
                stringBuffer.append("$");
                if (name.startsWith(stringBuffer.toString())) {
                    try {
                        ClassGenerator.getClassGenerator().setInstanceNameSpaceParent(constructObject, name, classNameSpace);
                    } catch (UtilEvalError e) {
                        throw e.toEvalError(this, callStack);
                    }
                }
            }
            return constructObject;
        } catch (ReflectError e2) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Constructor error: ");
            stringBuffer2.append(e2.getMessage());
            throw new EvalError(stringBuffer2.toString(), this, callStack);
        } catch (InvocationTargetException e3) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("The constructor threw an exception:\n\t");
            stringBuffer3.append(e3.getTargetException());
            Interpreter.debug(stringBuffer3.toString());
            throw new TargetError("Object constructor", e3.getTargetException(), this, callStack, true);
        }
    }

    private Object constructWithClassBody(Class cls, Object[] objArr, BSHBlock bSHBlock, CallStack callStack, Interpreter interpreter) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(callStack.top().getName());
        stringBuffer.append("$");
        int i2 = innerClassCount + 1;
        innerClassCount = i2;
        stringBuffer.append(i2);
        String stringBuffer2 = stringBuffer.toString();
        Modifiers modifiers = new Modifiers();
        modifiers.addModifier(0, "public");
        try {
            try {
                return Reflect.constructObject(ClassGenerator.getClassGenerator().generateClass(stringBuffer2, modifiers, null, cls, bSHBlock, false, callStack, interpreter), objArr);
            } catch (Exception e) {
                Exception exc = e;
                if (exc instanceof InvocationTargetException) {
                    exc = (Exception) ((InvocationTargetException) exc).getTargetException();
                }
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Error constructing inner class instance: ");
                stringBuffer3.append(exc);
                throw new EvalError(stringBuffer3.toString(), this, callStack);
            }
        } catch (UtilEvalError e2) {
            throw e2.toEvalError(this, callStack);
        }
    }

    private Object constructWithInterfaceBody(Class cls, Object[] objArr, BSHBlock bSHBlock, CallStack callStack, Interpreter interpreter) {
        NameSpace nameSpace = new NameSpace(callStack.top(), "AnonymousBlock");
        callStack.push(nameSpace);
        bSHBlock.eval(callStack, interpreter, true);
        callStack.pop();
        nameSpace.importStatic(cls);
        try {
            return nameSpace.getThis(interpreter).getInterface(cls);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callStack);
        }
    }

    private Object objectAllocation(BSHAmbiguousName bSHAmbiguousName, BSHArguments bSHArguments, CallStack callStack, Interpreter interpreter) {
        callStack.top();
        Object[] arguments = bSHArguments.getArguments(callStack, interpreter);
        if (arguments == null) {
            throw new EvalError("Null args in new.", this, callStack);
        }
        bSHAmbiguousName.toObject(callStack, interpreter, false);
        Object object = bSHAmbiguousName.toObject(callStack, interpreter, true);
        if (!(object instanceof ClassIdentifier)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unknown class: ");
            stringBuffer.append(bSHAmbiguousName.text);
            throw new EvalError(stringBuffer.toString(), this, callStack);
        }
        Class targetClass = ((ClassIdentifier) object).getTargetClass();
        if (!(jjtGetNumChildren() > 2)) {
            return constructObject(targetClass, arguments, callStack);
        }
        BSHBlock bSHBlock = (BSHBlock) jjtGetChild(2);
        return targetClass.isInterface() ? constructWithInterfaceBody(targetClass, arguments, bSHBlock, callStack, interpreter) : constructWithClassBody(targetClass, arguments, bSHBlock, callStack, interpreter);
    }

    private Object objectArrayAllocation(BSHAmbiguousName bSHAmbiguousName, BSHArrayDimensions bSHArrayDimensions, CallStack callStack, Interpreter interpreter) {
        NameSpace pVar = callStack.top();
        Class cls = bSHAmbiguousName.toClass(callStack, interpreter);
        if (cls != null) {
            return arrayAllocation(bSHArrayDimensions, cls, callStack, interpreter);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Class ");
        stringBuffer.append(bSHAmbiguousName.getName(pVar));
        stringBuffer.append(" not found.");
        throw new EvalError(stringBuffer.toString(), this, callStack);
    }

    private Object primitiveArrayAllocation(BSHPrimitiveType bSHPrimitiveType, BSHArrayDimensions bSHArrayDimensions, CallStack callStack, Interpreter interpreter) {
        return arrayAllocation(bSHArrayDimensions, bSHPrimitiveType.getType(), callStack, interpreter);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        SimpleNode simpleNode = (SimpleNode) jjtGetChild(0);
        SimpleNode simpleNode2 = (SimpleNode) jjtGetChild(1);
        if (!(simpleNode instanceof BSHAmbiguousName)) {
            return primitiveArrayAllocation((BSHPrimitiveType) simpleNode, (BSHArrayDimensions) simpleNode2, callStack, interpreter);
        }
        BSHAmbiguousName bSHAmbiguousName = (BSHAmbiguousName) simpleNode;
        return simpleNode2 instanceof BSHArguments ? objectAllocation(bSHAmbiguousName, (BSHArguments) simpleNode2, callStack, interpreter) : objectArrayAllocation(bSHAmbiguousName, (BSHArrayDimensions) simpleNode2, callStack, interpreter);
    }
}
