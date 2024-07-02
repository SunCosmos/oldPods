package bsh;

/* loaded from: classes.dex */
public class UtilTargetError extends UtilEvalError {
    public Throwable t;

    public UtilTargetError(String str, Throwable th) {
        super(str);
        this.t = th;
    }

    public UtilTargetError(Throwable th) {
        this(null, th);
    }

    @Override // bsh.UtilEvalError
    public EvalError toEvalError(String str, SimpleNode simpleNode, CallStack callStack) {
        String stringBuffer;
        if (str == null) {
            stringBuffer = getMessage();
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(str);
            stringBuffer2.append(": ");
            stringBuffer2.append(getMessage());
            stringBuffer = stringBuffer2.toString();
        }
        return new TargetError(stringBuffer, this.t, simpleNode, callStack, false);
    }
}
