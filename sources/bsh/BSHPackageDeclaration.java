package bsh;

/* loaded from: classes.dex */
public class BSHPackageDeclaration extends SimpleNode {
    public BSHPackageDeclaration(int i2) {
        super(i2);
    }

    @Override // bsh.SimpleNode
    public Object eval(CallStack callStack, Interpreter interpreter) {
        BSHAmbiguousName bSHAmbiguousName = (BSHAmbiguousName) jjtGetChild(0);
        NameSpace pVar = callStack.top();
        pVar.setPackage(bSHAmbiguousName.text);
        pVar.importPackage(bSHAmbiguousName.text);
        return Primitive.VOID;
    }
}
