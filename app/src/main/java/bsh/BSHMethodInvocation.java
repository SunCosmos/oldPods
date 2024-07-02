package bsh;

import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
class BSHMethodInvocation extends SimpleNode {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHMethodInvocation(int i2) {
        super(i2);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        NameSpace pVar = callStack.top();
        BSHAmbiguousName nameNode = getNameNode();
        if (pVar.getParent() != null && pVar.getParent().isClass && (nameNode.text.equals("super") || nameNode.text.equals("this"))) {
            return Primitive.VOID;
        }
        Name name = nameNode.getName(pVar);
        try {
            return name.invokeMethod(interpreter, getArgsNode().getArguments(callStack, interpreter), callStack, this);
        } catch (ReflectError e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Error in method invocation: ");
            stringBuffer.append(e.getMessage());
            throw new EvalError(stringBuffer.toString(), this, callStack);
        } catch (UtilEvalError e2) {
            throw e2.toEvalError(this, callStack);
        } catch (InvocationTargetException e3) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Method Invocation ");
            stringBuffer2.append(name);
            String stringBuffer3 = stringBuffer2.toString();
            Throwable targetException = e3.getTargetException();
            throw new TargetError(stringBuffer3, targetException, this, callStack, targetException instanceof EvalError ? targetException instanceof TargetError ? ((TargetError) targetException).inNativeCode() : false : true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHArguments getArgsNode() {
        return (BSHArguments) jjtGetChild(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHAmbiguousName getNameNode() {
        return (BSHAmbiguousName) jjtGetChild(0);
    }
}
