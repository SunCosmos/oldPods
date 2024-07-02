package bsh;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BSHClassDeclaration extends SimpleNode {
    static final String CLASSINITNAME = "_bshClassInit";
    boolean extend;
    boolean isInterface;
    Modifiers modifiers;
    String name;
    int numInterfaces;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BSHClassDeclaration(int i2) {
        super(i2);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        Class cls;
        int i2;
        int i3 = 0;
        if (this.extend) {
            i2 = 1;
            cls = ((BSHAmbiguousName) jjtGetChild(0)).toClass(callStack, interpreter);
        } else {
            cls = null;
            i2 = 0;
        }
        Class[] clsArr = new Class[this.numInterfaces];
        while (i3 < this.numInterfaces) {
            int i4 = i2 + 1;
            BSHAmbiguousName bSHAmbiguousName = (BSHAmbiguousName) jjtGetChild(i2);
            clsArr[i3] = bSHAmbiguousName.toClass(callStack, interpreter);
            if (!clsArr[i3].isInterface()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Type: ");
                stringBuffer.append(bSHAmbiguousName.text);
                stringBuffer.append(" is not an interface!");
                throw new EvalError(stringBuffer.toString(), this, callStack);
            }
            i3++;
            i2 = i4;
        }
        try {
            return ClassGenerator.getClassGenerator().generateClass(this.name, this.modifiers, clsArr, cls, i2 < jjtGetNumChildren() ? (BSHBlock) jjtGetChild(i2) : new BSHBlock(25), this.isInterface, callStack, interpreter);
        } catch (UtilEvalError e) {
            throw e.toEvalError(this, callStack);
        }
    }

    @Override // bsh.SimpleNode
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ClassDeclaration: ");
        stringBuffer.append(this.name);
        return stringBuffer.toString();
    }
}
