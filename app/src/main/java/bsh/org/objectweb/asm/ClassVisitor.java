package bsh.org.objectweb.asm;

/* loaded from: classes.dex */
public interface ClassVisitor {
    void visit(int i2, String str, String str2, String[] strArr, String str3);

    void visitEnd();

    void visitField(int i2, String str, String str2, Object obj);

    void visitInnerClass(String str, String str2, String str3, int i2);

    CodeVisitor visitMethod(int i2, String str, String str2, String[] strArr);
}
