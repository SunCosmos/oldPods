package bsh;

/* loaded from: classes.dex */
class BSHSwitchStatement extends SimpleNode implements ParserConstants {
    public BSHSwitchStatement(int i2) {
        super(i2);
    }

    private boolean primitiveEquals(Object obj, Object obj2, CallStack callStack, SimpleNode simpleNode) {
        if (!(obj instanceof Primitive) && !(obj2 instanceof Primitive)) {
            return obj.equals(obj2);
        }
        try {
            return Primitive.unwrap(Primitive.binaryOperation(obj, obj2, 90)).equals(Boolean.TRUE);
        } catch (UtilEvalError e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Switch value: ");
            stringBuffer.append(simpleNode.getText());
            stringBuffer.append(": ");
            throw e.toEvalError(stringBuffer.toString(), this, callStack);
        }
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        int i2;
        int jjtGetNumChildren = jjtGetNumChildren();
        SimpleNode simpleNode = (SimpleNode) jjtGetChild(0);
        Object eval = simpleNode.eval(callStack, interpreter);
        if (1 >= jjtGetNumChildren) {
            throw new EvalError("Empty switch statement.", this, callStack);
        }
        int i3 = 2;
        BSHSwitchLabel bSHSwitchLabel = (BSHSwitchLabel) jjtGetChild(1);
        ReturnControl returnControl = null;
        while (i3 < jjtGetNumChildren && returnControl == null) {
            if (bSHSwitchLabel.isDefault || primitiveEquals(eval, bSHSwitchLabel.eval(callStack, interpreter), callStack, simpleNode)) {
                while (i3 < jjtGetNumChildren) {
                    i2 = i3 + 1;
                    Node jjtGetChild = jjtGetChild(i3);
                    if (!(jjtGetChild instanceof BSHSwitchLabel)) {
                        Object eval2 = ((SimpleNode) jjtGetChild).eval(callStack, interpreter);
                        if (eval2 instanceof ReturnControl) {
                            returnControl = (ReturnControl) eval2;
                            i3 = i2;
                            break;
                        }
                    }
                    i3 = i2;
                }
            } else {
                while (i3 < jjtGetNumChildren) {
                    i2 = i3 + 1;
                    Node jjtGetChild2 = jjtGetChild(i3);
                    if (jjtGetChild2 instanceof BSHSwitchLabel) {
                        bSHSwitchLabel = (BSHSwitchLabel) jjtGetChild2;
                        i3 = i2;
                        break;
                        break;
                    }
                    i3 = i2;
                }
            }
        }
        return (returnControl == null || returnControl.kind != 46) ? Primitive.VOID : returnControl;
    }
}
