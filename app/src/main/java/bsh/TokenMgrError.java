package bsh;

/* loaded from: classes.dex */
public class TokenMgrError extends Error {
    static final int INVALID_LEXICAL_STATE = 2;
    static final int LEXICAL_ERROR = 0;
    static final int LOOP_DETECTED = 3;
    static final int STATIC_LEXER_ERROR = 1;
    int errorCode;

    public TokenMgrError() {
    }

    public TokenMgrError(String str, int i2) {
        super(str);
        this.errorCode = i2;
    }

    public TokenMgrError(boolean z, int i2, int i3, int i4, String str, char c2, int i5) {
        this(LexicalError(z, i2, i3, i4, str, c2), i5);
    }

    protected static String LexicalError(boolean z, int i2, int i3, int i4, String str, char c2) {
        String stringBuffer;
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Lexical error at line ");
        stringBuffer2.append(i3);
        stringBuffer2.append(", column ");
        stringBuffer2.append(i4);
        stringBuffer2.append(".  Encountered: ");
        if (z) {
            stringBuffer = "<EOF> ";
        } else {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("\"");
            stringBuffer3.append(addEscapes(String.valueOf(c2)));
            stringBuffer3.append("\"");
            stringBuffer3.append(" (");
            stringBuffer3.append((int) c2);
            stringBuffer3.append("), ");
            stringBuffer = stringBuffer3.toString();
        }
        stringBuffer2.append(stringBuffer);
        stringBuffer2.append("after : \"");
        stringBuffer2.append(addEscapes(str));
        stringBuffer2.append("\"");
        return stringBuffer2.toString();
    }

    protected static final String addEscapes(String str) {
        String str2;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt != 0) {
                if (charAt == '\"') {
                    str2 = "\\\"";
                } else if (charAt == '\'') {
                    str2 = "\\'";
                } else if (charAt == '\\') {
                    str2 = "\\\\";
                } else if (charAt == '\f') {
                    str2 = "\\f";
                } else if (charAt != '\r') {
                    switch (charAt) {
                        case '\b':
                            str2 = "\\b";
                            break;
                        case '\t':
                            str2 = "\\t";
                            break;
                        case '\n':
                            str2 = "\\n";
                            break;
                        default:
                            char charAt2 = str.charAt(i2);
                            if (charAt2 < ' ' || charAt2 > '~') {
                                StringBuffer stringBuffer2 = new StringBuffer();
                                stringBuffer2.append("0000");
                                stringBuffer2.append(Integer.toString(charAt2, 16));
                                String stringBuffer3 = stringBuffer2.toString();
                                StringBuffer stringBuffer4 = new StringBuffer();
                                stringBuffer4.append("\\u");
                                stringBuffer4.append(stringBuffer3.substring(stringBuffer3.length() - 4, stringBuffer3.length()));
                                str2 = stringBuffer4.toString();
                                break;
                            } else {
                                stringBuffer.append(charAt2);
                                break;
                            }
                            break;
                    }
                } else {
                    str2 = "\\r";
                }
                stringBuffer.append(str2);
            }
        }
        return stringBuffer.toString();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage();
    }
}
