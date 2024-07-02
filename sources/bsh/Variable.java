package bsh;

import java.io.Serializable;

/* loaded from: classes.dex */
public class Variable implements Serializable {
    static final int ASSIGNMENT = 1;
    static final int DECLARATION = 0;
    LHS lhs;
    Modifiers modifiers;
    String name;
    Class type;
    String typeDescriptor;
    Object value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Variable(String str, Class cls, LHS lhs) {
        this.type = null;
        this.name = str;
        this.lhs = lhs;
        this.type = cls;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Variable(String str, Class cls, Object obj, Modifiers modifiers) {
        this.type = null;
        this.name = str;
        this.type = cls;
        this.modifiers = modifiers;
        setValue(obj, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Variable(String str, Object obj, Modifiers modifiers) {
        this(str, (Class) null, obj, modifiers);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Variable(String str, String str2, Object obj, Modifiers modifiers) {
        this(str, (Class) null, obj, modifiers);
        this.typeDescriptor = str2;
    }

    public Modifiers getModifiers() {
        return this.modifiers;
    }

    public String getName() {
        return this.name;
    }

    public Class getType() {
        return this.type;
    }

    public String getTypeDescriptor() {
        return this.typeDescriptor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getValue() {
        LHS lhs = this.lhs;
        return lhs != null ? lhs.getValue() : this.value;
    }

    public boolean hasModifier(String str) {
        Modifiers modifiers = this.modifiers;
        return modifiers != null && modifiers.hasModifier(str);
    }

    public void setValue(Object obj, int i2) {
        if (hasModifier("final") && this.value != null) {
            throw new UtilEvalError("Final variable, can't re-assign.");
        }
        if (obj == null) {
            obj = Primitive.getDefaultValue(this.type);
        }
        LHS lhs = this.lhs;
        if (lhs != null) {
            lhs.assign(obj, false);
            return;
        }
        Class cls = this.type;
        if (cls != null) {
            obj = Types.castObject(obj, cls, i2 != 0 ? 1 : 0);
        }
        this.value = obj;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Variable: ");
        stringBuffer.append(super.toString());
        stringBuffer.append(" ");
        stringBuffer.append(this.name);
        stringBuffer.append(", type:");
        stringBuffer.append(this.type);
        stringBuffer.append(", value:");
        stringBuffer.append(this.value);
        stringBuffer.append(", lhs = ");
        stringBuffer.append(this.lhs);
        return stringBuffer.toString();
    }
}
