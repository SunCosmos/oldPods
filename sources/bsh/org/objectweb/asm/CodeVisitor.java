package bsh.org.objectweb.asm;

/* loaded from: classes.dex */
public interface CodeVisitor {
    void visitFieldInsn(int i2, String str, String str2, String str3);

    void visitIincInsn(int i2, int i3);

    void visitInsn(int i2);

    void visitIntInsn(int i2, int i3);

    void visitJumpInsn(int i2, Label label);

    void visitLabel(Label label);

    void visitLdcInsn(Object obj);

    void visitLineNumber(int i2, Label label);

    void visitLocalVariable(String str, String str2, Label label, Label label2, int i2);

    void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr);

    void visitMaxs(int i2, int i3);

    void visitMethodInsn(int i2, String str, String str2, String str3);

    void visitMultiANewArrayInsn(String str, int i2);

    void visitTableSwitchInsn(int i2, int i3, Label label, Label[] labelArr);

    void visitTryCatchBlock(Label label, Label label2, Label label3, String str);

    void visitTypeInsn(int i2, String str);

    void visitVarInsn(int i2, int i3);
}
