package bsh;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ReturnControl implements ParserConstants {
    public int kind;
    public SimpleNode returnPoint;
    public Object value;

    public ReturnControl(int i2, Object obj, SimpleNode simpleNode) {
        this.kind = i2;
        this.value = obj;
        this.returnPoint = simpleNode;
    }
}
