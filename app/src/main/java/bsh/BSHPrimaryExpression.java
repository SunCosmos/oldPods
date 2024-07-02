package bsh;

/* loaded from: classes.dex */
class BSHPrimaryExpression extends SimpleNode {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHPrimaryExpression(int i2) {
        super(i2);
    }

    private Object eval(boolean z, CallStack callStack, Interpreter interpreter) {
        Object jjtGetChild = jjtGetChild(0);
        int jjtGetNumChildren = jjtGetNumChildren();
        for (int i2 = 1; i2 < jjtGetNumChildren; i2++) {
            jjtGetChild = ((BSHPrimarySuffix) jjtGetChild(i2)).doSuffix(jjtGetChild, z, callStack, interpreter);
        }
        if (jjtGetChild instanceof SimpleNode) {
            if (jjtGetChild instanceof BSHAmbiguousName) {
                BSHAmbiguousName bSHAmbiguousName = (BSHAmbiguousName) jjtGetChild;
                jjtGetChild = z ? bSHAmbiguousName.toLHS(callStack, interpreter) : bSHAmbiguousName.toObject(callStack, interpreter);
            } else {
                if (z) {
                    throw new EvalError("Can't assign to prefix.", this, callStack);
                }
                jjtGetChild = ((SimpleNode) jjtGetChild).eval(callStack, interpreter);
            }
        }
        if (!(jjtGetChild instanceof LHS) || z) {
            return jjtGetChild;
        }
        try {
            return ((LHS) jjtGetChild).getValue();
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callStack);
        }
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        return eval(false, callStack, interpreter);
    }

    public LHS toLHS(CallStack callStack, Interpreter interpreter) {
        Object eval = eval(true, callStack, interpreter);
        if (eval instanceof LHS) {
            return (LHS) eval;
        }
        throw new EvalError("Can't assign to:", this, callStack);
    }
}
