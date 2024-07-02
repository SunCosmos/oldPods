package bsh;

/* loaded from: classes.dex */
class BSHAssignment extends SimpleNode implements ParserConstants {
    public int operator;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHAssignment(int i2) {
        super(i2);
    }

    private Object operation(Object obj, Object obj2, int i2) {
        if ((obj instanceof String) && obj2 != Primitive.VOID) {
            if (i2 != 102) {
                throw new UtilEvalError("Use of non + operator with String LHS");
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append((String) obj);
            stringBuffer.append(obj2);
            return stringBuffer.toString();
        }
        boolean z = obj instanceof Primitive;
        if (z || (obj2 instanceof Primitive)) {
            Primitive primitive = Primitive.VOID;
            if (obj == primitive || obj2 == primitive) {
                throw new UtilEvalError("Illegal use of undefined object or 'void' literal");
            }
            Primitive primitive2 = Primitive.NULL;
            if (obj == primitive2 || obj2 == primitive2) {
                throw new UtilEvalError("Illegal use of null object or 'null' literal");
            }
        }
        if (((obj instanceof Boolean) || (obj instanceof Character) || (obj instanceof Number) || z) && ((obj2 instanceof Boolean) || (obj2 instanceof Character) || (obj2 instanceof Number) || (obj2 instanceof Primitive))) {
            return Primitive.binaryOperation(obj, obj2, i2);
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Non primitive value in operator: ");
        stringBuffer2.append(obj.getClass());
        stringBuffer2.append(" ");
        stringBuffer2.append(ParserConstants.tokenImage[i2]);
        stringBuffer2.append(" ");
        stringBuffer2.append(obj2.getClass());
        throw new UtilEvalError(stringBuffer2.toString());
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        BSHPrimaryExpression bSHPrimaryExpression = (BSHPrimaryExpression) jjtGetChild(0);
        if (bSHPrimaryExpression == null) {
            throw new InterpreterError("Error, null LHSnode");
        }
        boolean strictJava = interpreter.getStrictJava();
        LHS lhs = bSHPrimaryExpression.toLHS(callStack, interpreter);
        if (lhs == null) {
            throw new InterpreterError("Error, null LHS");
        }
        Object obj = null;
        if (this.operator != 81) {
            try {
                obj = lhs.getValue();
            } catch (UtilEvalError e) {
                throw e.toEvalError(this, callStack);
            }
        }
        Object eval = ((SimpleNode) jjtGetChild(1)).eval(callStack, interpreter);
        if (eval == Primitive.VOID) {
            throw new EvalError("Void assignment.", this, callStack);
        }
        try {
            int i2 = this.operator;
            if (i2 == 81) {
                return lhs.assign(eval, strictJava);
            }
            switch (i2) {
                case 118:
                    return lhs.assign(operation(obj, eval, 102), strictJava);
                case 119:
                    return lhs.assign(operation(obj, eval, 103), strictJava);
                case 120:
                    return lhs.assign(operation(obj, eval, 104), strictJava);
                case 121:
                    return lhs.assign(operation(obj, eval, 105), strictJava);
                case 122:
                case 123:
                    return lhs.assign(operation(obj, eval, 106), strictJava);
                case 124:
                case 125:
                    return lhs.assign(operation(obj, eval, 108), strictJava);
                case 126:
                    return lhs.assign(operation(obj, eval, 110), strictJava);
                case 127:
                    return lhs.assign(operation(obj, eval, 111), strictJava);
                case 128:
                case 129:
                    return lhs.assign(operation(obj, eval, 112), strictJava);
                case 130:
                case 131:
                    return lhs.assign(operation(obj, eval, 114), strictJava);
                case 132:
                case 133:
                    return lhs.assign(operation(obj, eval, 116), strictJava);
                default:
                    throw new InterpreterError("unimplemented operator in assignment BSH");
            }
        } catch (UtilEvalError e2) {
            throw e2.toEvalError(this, callStack);
        }
    }
}
