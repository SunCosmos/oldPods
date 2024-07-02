package bsh;

/* loaded from: classes.dex */
class BSHLiteral extends SimpleNode {
    public Object value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHLiteral(int i2) {
        super(i2);
    }

    private char getEscapeChar(char c2) {
        if (c2 == 'b') {
            return '\b';
        }
        if (c2 == 'f') {
            return '\f';
        }
        if (c2 == 'n') {
            return '\n';
        }
        if (c2 == 'r') {
            return '\r';
        }
        if (c2 != 't') {
            return c2;
        }
        return '\t';
    }

    public void charSetup(String str) {
        char charAt = str.charAt(0);
        if (charAt == '\\') {
            char charAt2 = str.charAt(1);
            charAt = Character.isDigit(charAt2) ? (char) Integer.parseInt(str.substring(1), 8) : getEscapeChar(charAt2);
        }
        this.value = new Primitive(new Character(charAt).charValue());
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        Object obj = this.value;
        if (obj != null) {
            return obj;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Null in bsh literal: ");
        stringBuffer.append(this.value);
        throw new InterpreterError(stringBuffer.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stringSetup(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = 0;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if (charAt == '\\') {
                i2++;
                char charAt2 = str.charAt(i2);
                if (Character.isDigit(charAt2)) {
                    int i3 = i2;
                    while (i3 < i2 + 2) {
                        int i4 = i3 + 1;
                        if (!Character.isDigit(str.charAt(i4))) {
                            break;
                        } else {
                            i3 = i4;
                        }
                    }
                    int i5 = i3;
                    charAt = (char) Integer.parseInt(str.substring(i2, i3 + 1), 8);
                    i2 = i5;
                } else {
                    charAt = getEscapeChar(charAt2);
                }
            }
            stringBuffer.append(charAt);
            i2++;
        }
        this.value = stringBuffer.toString().intern();
    }
}
