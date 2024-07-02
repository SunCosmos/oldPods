package bsh;

/* loaded from: classes.dex */
public class ClassIdentifier {
    Class clas;

    public ClassIdentifier(Class cls) {
        this.clas = cls;
    }

    public Class getTargetClass() {
        return this.clas;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Class Identifier: ");
        stringBuffer.append(this.clas.getName());
        return stringBuffer.toString();
    }
}
