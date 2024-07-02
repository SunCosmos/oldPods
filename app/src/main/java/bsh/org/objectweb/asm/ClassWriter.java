package bsh.org.objectweb.asm;

import android.support.v7.widget.ActivityChooserView;

/* loaded from: classes.dex */
public class ClassWriter implements ClassVisitor {
    static final int CLASS = 7;
    static final int DOUBLE = 6;
    static final int FIELD = 9;
    static final int FIELDORMETH_INSN = 6;
    static final int FLOAT = 4;
    static final int IINC_INSN = 12;
    static final int IMETH = 11;
    static final int IMPLVAR_INSN = 4;
    static final int INT = 3;
    static final int ITFMETH_INSN = 7;
    static final int LABELW_INSN = 9;
    static final int LABEL_INSN = 8;
    static final int LDCW_INSN = 11;
    static final int LDC_INSN = 10;
    static final int LONG = 5;
    static final int LOOK_INSN = 14;
    static final int MANA_INSN = 15;
    static final int METH = 10;
    static final int NAME_TYPE = 12;
    static final int NOARG_INSN = 0;
    static final int SBYTE_INSN = 1;
    static final int SHORT_INSN = 2;
    static final int STR = 8;
    static final int TABL_INSN = 13;
    static byte[] TYPE = null;
    static final int TYPE_INSN = 5;
    static final int UTF8 = 1;
    static final int VAR_INSN = 3;
    static final int WIDE_INSN = 16;
    private int access;
    private boolean computeMaxs;
    private int fieldCount;
    private ByteVector fields;
    CodeWriter firstMethod;
    private ByteVector innerClasses;
    private int innerClassesCount;
    private int interfaceCount;
    private int[] interfaces;
    Item key;
    Item key2;
    Item key3;
    CodeWriter lastMethod;
    private int name;
    private Item sourceFile;
    private int superName;
    private Item[] table;
    private int threshold;
    private short index = 1;
    private ByteVector pool = new ByteVector();

    static {
        byte[] bArr = new byte[220];
        for (int i2 = 0; i2 < 220; i2++) {
            bArr[i2] = (byte) ("AAAAAAAAAAAAAAAABCKLLDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMAAAAAAAAAAAAAAAAAAAAIIIIIIIIIIIIIIIIDNOAAAAAAGGGGGGGHAFBFAAFFAAQPIIJJIIIIIIIIIIIIIIIIII".charAt(i2) - 'A');
        }
        TYPE = bArr;
    }

    public ClassWriter(boolean z) {
        Item[] itemArr = new Item[64];
        this.table = itemArr;
        double length = itemArr.length;
        Double.isNaN(length);
        this.threshold = (int) (length * 0.75d);
        this.key = new Item();
        this.key2 = new Item();
        this.key3 = new Item();
        this.computeMaxs = z;
    }

    private Item get(Item item) {
        Item[] itemArr = this.table;
        int i2 = item.hashCode;
        for (Item item2 = itemArr[(Integer.MAX_VALUE & i2) % itemArr.length]; item2 != null; item2 = item2.next) {
            if (item2.hashCode == i2 && item.isEqualTo(item2)) {
                return item2;
            }
        }
        return null;
    }

    private Item newDouble(double d2) {
        this.key.set(d2);
        Item item = get(this.key);
        if (item != null) {
            return item;
        }
        this.pool.put1(6).put8(Double.doubleToLongBits(d2));
        Item item2 = new Item(this.index, this.key);
        put(item2);
        this.index = (short) (this.index + 2);
        return item2;
    }

    private Item newFloat(float f) {
        this.key.set(f);
        Item item = get(this.key);
        if (item != null) {
            return item;
        }
        this.pool.put1(4).put4(Float.floatToIntBits(f));
        short s = this.index;
        this.index = (short) (s + 1);
        Item item2 = new Item(s, this.key);
        put(item2);
        return item2;
    }

    private Item newInteger(int i2) {
        this.key.set(i2);
        Item item = get(this.key);
        if (item != null) {
            return item;
        }
        this.pool.put1(3).put4(i2);
        short s = this.index;
        this.index = (short) (s + 1);
        Item item2 = new Item(s, this.key);
        put(item2);
        return item2;
    }

    private Item newLong(long j) {
        this.key.set(j);
        Item item = get(this.key);
        if (item != null) {
            return item;
        }
        this.pool.put1(5).put8(j);
        Item item2 = new Item(this.index, this.key);
        put(item2);
        this.index = (short) (this.index + 2);
        return item2;
    }

    private Item newNameType(String str, String str2) {
        this.key2.set(12, str, str2, null);
        Item item = get(this.key2);
        if (item != null) {
            return item;
        }
        put122(12, newUTF8(str).index, newUTF8(str2).index);
        short s = this.index;
        this.index = (short) (s + 1);
        Item item2 = new Item(s, this.key2);
        put(item2);
        return item2;
    }

    private Item newString(String str) {
        this.key2.set(8, str, null, null);
        Item item = get(this.key2);
        if (item != null) {
            return item;
        }
        this.pool.put12(8, newUTF8(str).index);
        short s = this.index;
        this.index = (short) (s + 1);
        Item item2 = new Item(s, this.key2);
        put(item2);
        return item2;
    }

    private void put(Item item) {
        if (this.index > this.threshold) {
            Item[] itemArr = this.table;
            int length = itemArr.length;
            int i2 = (length * 2) + 1;
            Item[] itemArr2 = new Item[i2];
            double d2 = i2;
            Double.isNaN(d2);
            this.threshold = (int) (d2 * 0.75d);
            this.table = itemArr2;
            while (true) {
                int i3 = length - 1;
                if (length <= 0) {
                    break;
                }
                Item item2 = itemArr[i3];
                while (item2 != null) {
                    Item item3 = item2.next;
                    int i4 = (item2.hashCode & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) % i2;
                    item2.next = itemArr2[i4];
                    itemArr2[i4] = item2;
                    item2 = item3;
                }
                length = i3;
            }
        }
        int i5 = item.hashCode & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        Item[] itemArr3 = this.table;
        int length2 = i5 % itemArr3.length;
        item.next = itemArr3[length2];
        itemArr3[length2] = item;
    }

    private void put122(int i2, int i3, int i4) {
        this.pool.put12(i2, i3).put2(i4);
    }

    public Item newClass(String str) {
        this.key2.set(7, str, null, null);
        Item item = get(this.key2);
        if (item != null) {
            return item;
        }
        this.pool.put12(7, newUTF8(str).index);
        short s = this.index;
        this.index = (short) (s + 1);
        Item item2 = new Item(s, this.key2);
        put(item2);
        return item2;
    }

    public Item newCst(Object obj) {
        if (obj instanceof Integer) {
            return newInteger(((Integer) obj).intValue());
        }
        if (obj instanceof Float) {
            return newFloat(((Float) obj).floatValue());
        }
        if (obj instanceof Long) {
            return newLong(((Long) obj).longValue());
        }
        if (obj instanceof Double) {
            return newDouble(((Double) obj).doubleValue());
        }
        if (obj instanceof String) {
            return newString((String) obj);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("value ");
        stringBuffer.append(obj);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public Item newField(String str, String str2, String str3) {
        this.key3.set(9, str, str2, str3);
        Item item = get(this.key3);
        if (item != null) {
            return item;
        }
        put122(9, newClass(str).index, newNameType(str2, str3).index);
        short s = this.index;
        this.index = (short) (s + 1);
        Item item2 = new Item(s, this.key3);
        put(item2);
        return item2;
    }

    public Item newItfMethod(String str, String str2, String str3) {
        this.key3.set(11, str, str2, str3);
        Item item = get(this.key3);
        if (item != null) {
            return item;
        }
        put122(11, newClass(str).index, newNameType(str2, str3).index);
        short s = this.index;
        this.index = (short) (s + 1);
        Item item2 = new Item(s, this.key3);
        put(item2);
        return item2;
    }

    public Item newMethod(String str, String str2, String str3) {
        this.key3.set(10, str, str2, str3);
        Item item = get(this.key3);
        if (item != null) {
            return item;
        }
        put122(10, newClass(str).index, newNameType(str2, str3).index);
        short s = this.index;
        this.index = (short) (s + 1);
        Item item2 = new Item(s, this.key3);
        put(item2);
        return item2;
    }

    public Item newUTF8(String str) {
        this.key.set(1, str, null, null);
        Item item = get(this.key);
        if (item != null) {
            return item;
        }
        this.pool.put1(1).putUTF(str);
        short s = this.index;
        this.index = (short) (s + 1);
        Item item2 = new Item(s, this.key);
        put(item2);
        return item2;
    }

    public byte[] toByteArray() {
        int i2;
        int i3 = (this.interfaceCount * 2) + 24;
        ByteVector byteVector = this.fields;
        if (byteVector != null) {
            i3 += byteVector.length;
        }
        int i4 = 0;
        for (CodeWriter codeWriter = this.firstMethod; codeWriter != null; codeWriter = codeWriter.next) {
            i4++;
            i3 += codeWriter.getSize();
        }
        int i5 = i3 + this.pool.length;
        if (this.sourceFile != null) {
            i5 += 8;
            i2 = 1;
        } else {
            i2 = 0;
        }
        if ((this.access & 131072) != 0) {
            i2++;
            i5 += 6;
        }
        ByteVector byteVector2 = this.innerClasses;
        if (byteVector2 != null) {
            i2++;
            i5 += byteVector2.length + 8;
        }
        int i6 = i2;
        ByteVector byteVector3 = new ByteVector(i5);
        byteVector3.put4(-889275714).put2(3).put2(45);
        ByteVector put2 = byteVector3.put2(this.index);
        ByteVector byteVector4 = this.pool;
        put2.putByteArray(byteVector4.data, 0, byteVector4.length);
        byteVector3.put2(this.access).put2(this.name).put2(this.superName);
        byteVector3.put2(this.interfaceCount);
        for (int i7 = 0; i7 < this.interfaceCount; i7++) {
            byteVector3.put2(this.interfaces[i7]);
        }
        byteVector3.put2(this.fieldCount);
        ByteVector byteVector5 = this.fields;
        if (byteVector5 != null) {
            byteVector3.putByteArray(byteVector5.data, 0, byteVector5.length);
        }
        byteVector3.put2(i4);
        for (CodeWriter codeWriter2 = this.firstMethod; codeWriter2 != null; codeWriter2 = codeWriter2.next) {
            codeWriter2.put(byteVector3);
        }
        byteVector3.put2(i6);
        if (this.sourceFile != null) {
            byteVector3.put2(newUTF8("SourceFile").index).put4(2).put2(this.sourceFile.index);
        }
        if ((this.access & 131072) != 0) {
            byteVector3.put2(newUTF8("Deprecated").index).put4(0);
        }
        if (this.innerClasses != null) {
            byteVector3.put2(newUTF8("InnerClasses").index);
            byteVector3.put4(this.innerClasses.length + 2).put2(this.innerClassesCount);
            ByteVector byteVector6 = this.innerClasses;
            byteVector3.putByteArray(byteVector6.data, 0, byteVector6.length);
        }
        return byteVector3.data;
    }

    @Override // bsh.org.objectweb.asm.ClassVisitor
    public void visit(int i2, String str, String str2, String[] strArr, String str3) {
        this.access = i2;
        this.name = newClass(str).index;
        this.superName = str2 == null ? (short) 0 : newClass(str2).index;
        if (strArr != null && strArr.length > 0) {
            int length = strArr.length;
            this.interfaceCount = length;
            this.interfaces = new int[length];
            for (int i3 = 0; i3 < this.interfaceCount; i3++) {
                this.interfaces[i3] = newClass(strArr[i3]).index;
            }
        }
        if (str3 != null) {
            newUTF8("SourceFile");
            this.sourceFile = newUTF8(str3);
        }
        if ((i2 & 131072) != 0) {
            newUTF8("Deprecated");
        }
    }

    @Override // bsh.org.objectweb.asm.ClassVisitor
    public void visitEnd() {
    }

    @Override // bsh.org.objectweb.asm.ClassVisitor
    public void visitField(int i2, String str, String str2, Object obj) {
        this.fieldCount++;
        if (this.fields == null) {
            this.fields = new ByteVector();
        }
        this.fields.put2(i2).put2(newUTF8(str).index).put2(newUTF8(str2).index);
        int i3 = obj == null ? 0 : 1;
        int i4 = 65536 & i2;
        if (i4 != 0) {
            i3++;
        }
        int i5 = i2 & 131072;
        if (i5 != 0) {
            i3++;
        }
        this.fields.put2(i3);
        if (obj != null) {
            this.fields.put2(newUTF8("ConstantValue").index);
            this.fields.put4(2).put2(newCst(obj).index);
        }
        if (i4 != 0) {
            this.fields.put2(newUTF8("Synthetic").index).put4(0);
        }
        if (i5 != 0) {
            this.fields.put2(newUTF8("Deprecated").index).put4(0);
        }
    }

    @Override // bsh.org.objectweb.asm.ClassVisitor
    public void visitInnerClass(String str, String str2, String str3, int i2) {
        if (this.innerClasses == null) {
            newUTF8("InnerClasses");
            this.innerClasses = new ByteVector();
        }
        this.innerClassesCount++;
        this.innerClasses.put2(str == null ? (short) 0 : newClass(str).index);
        this.innerClasses.put2(str2 == null ? (short) 0 : newClass(str2).index);
        this.innerClasses.put2(str3 != null ? newUTF8(str3).index : (short) 0);
        this.innerClasses.put2(i2);
    }

    @Override // bsh.org.objectweb.asm.ClassVisitor
    public CodeVisitor visitMethod(int i2, String str, String str2, String[] strArr) {
        CodeWriter codeWriter = new CodeWriter(this, this.computeMaxs);
        codeWriter.init(i2, str, str2, strArr);
        return codeWriter;
    }
}
