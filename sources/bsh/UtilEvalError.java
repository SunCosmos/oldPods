package bsh;

/* loaded from: classes.dex */
public class UtilEvalError extends Exception {
    protected UtilEvalError() {
    }

    public UtilEvalError(String str) {
        super(str);
    }

    public EvalError toEvalError(SimpleNode simpleNode, CallStack callStack) {
        return toEvalError(null, simpleNode, callStack);
    }

    public EvalError toEvalError(String str, SimpleNode simpleNode, CallStack callStack) {
        String stringBuffer;
        if (Interpreter.DEBUG) {
            printStackTrace();
        }
        if (str == null) {
            stringBuffer = "";
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(str);
            stringBuffer2.append(": ");
            stringBuffer = stringBuffer2.toString();
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append(stringBuffer);
        stringBuffer3.append(getMessage());
        return new EvalError(stringBuffer3.toString(), simpleNode, callStack);
    }
}
