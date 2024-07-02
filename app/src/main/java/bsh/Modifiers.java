package bsh;

import java.io.Serializable;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class Modifiers implements Serializable {
    public static final int CLASS = 0;
    public static final int FIELD = 2;
    public static final int METHOD = 1;
    Hashtable modifiers;

    private void insureNo(String str, String str2) {
        if (hasModifier(str)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str2);
            stringBuffer.append(" cannot be declared '");
            stringBuffer.append(str);
            stringBuffer.append("'");
            throw new IllegalStateException(stringBuffer.toString());
        }
    }

    private void validateForClass() {
        validateForMethod();
        insureNo("native", "Class");
        insureNo("synchronized", "Class");
    }

    private void validateForField() {
        insureNo("synchronized", "Variable");
        insureNo("native", "Variable");
        insureNo("abstract", "Variable");
    }

    private void validateForMethod() {
        insureNo("volatile", "Method");
        insureNo("transient", "Method");
    }

    /* JADX WARN: Type inference failed for: r4v3, types: [boolean, int] */
    public void addModifier(int i2, String str) {
        if (this.modifiers == null) {
            this.modifiers = new Hashtable();
        }
        if (this.modifiers.put(str, Void.TYPE) != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Duplicate modifier: ");
            stringBuffer.append(str);
            throw new IllegalStateException(stringBuffer.toString());
        }
        ?? hasModifier = hasModifier("private");
        int i3 = hasModifier;
        if (hasModifier("protected")) {
            i3 = hasModifier + 1;
        }
        int i4 = i3;
        if (hasModifier("public")) {
            i4 = i3 + 1;
        }
        if (i4 > 1) {
            throw new IllegalStateException("public/private/protected cannot be used in combination.");
        }
        if (i2 == 0) {
            validateForClass();
        } else if (i2 == 1) {
            validateForMethod();
        } else {
            if (i2 != 2) {
                return;
            }
            validateForField();
        }
    }

    public boolean hasModifier(String str) {
        if (this.modifiers == null) {
            this.modifiers = new Hashtable();
        }
        return this.modifiers.get(str) != null;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Modifiers: ");
        stringBuffer.append(this.modifiers);
        return stringBuffer.toString();
    }
}
