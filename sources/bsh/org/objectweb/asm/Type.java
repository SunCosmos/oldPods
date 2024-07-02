package bsh.org.objectweb.asm;

import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class Type {
    public static final int ARRAY = 9;
    public static final int BOOLEAN = 1;
    public static final int BYTE = 3;
    public static final int CHAR = 2;
    public static final int DOUBLE = 8;
    public static final int FLOAT = 6;
    public static final int INT = 5;
    public static final int LONG = 7;
    public static final int OBJECT = 10;
    public static final int SHORT = 4;
    public static final int VOID = 0;
    private char[] buf;
    private int len;
    private int off;
    private final int sort;
    public static final Type VOID_TYPE = new Type(0);
    public static final Type BOOLEAN_TYPE = new Type(1);
    public static final Type CHAR_TYPE = new Type(2);
    public static final Type BYTE_TYPE = new Type(3);
    public static final Type SHORT_TYPE = new Type(4);
    public static final Type INT_TYPE = new Type(5);
    public static final Type FLOAT_TYPE = new Type(6);
    public static final Type LONG_TYPE = new Type(7);
    public static final Type DOUBLE_TYPE = new Type(8);

    private Type(int i2) {
        this.sort = i2;
        this.len = 1;
    }

    private Type(int i2, char[] cArr, int i3, int i4) {
        this.sort = i2;
        this.buf = cArr;
        this.off = i3;
        this.len = i4;
    }

    public static Type[] getArgumentTypes(String str) {
        char[] charArray = str.toCharArray();
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (true) {
            int i5 = i3 + 1;
            char c2 = charArray[i3];
            if (c2 == ')') {
                break;
            }
            if (c2 == 'L') {
                while (true) {
                    i3 = i5 + 1;
                    if (charArray[i5] == ';') {
                        break;
                    }
                    i5 = i3;
                }
                i4++;
            } else {
                if (c2 != '[') {
                    i4++;
                }
                i3 = i5;
            }
        }
        Type[] typeArr = new Type[i4];
        int i6 = 1;
        while (charArray[i6] != ')') {
            typeArr[i2] = getType(charArray, i6);
            i6 += typeArr[i2].len;
            i2++;
        }
        return typeArr;
    }

    public static Type[] getArgumentTypes(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        Type[] typeArr = new Type[parameterTypes.length];
        for (int length = parameterTypes.length - 1; length >= 0; length--) {
            typeArr[length] = getType(parameterTypes[length]);
        }
        return typeArr;
    }

    public static String getDescriptor(Class cls) {
        StringBuffer stringBuffer = new StringBuffer();
        getDescriptor(stringBuffer, cls);
        return stringBuffer.toString();
    }

    private void getDescriptor(StringBuffer stringBuffer) {
        switch (this.sort) {
            case 0:
                stringBuffer.append('V');
                return;
            case 1:
                stringBuffer.append('Z');
                return;
            case 2:
                stringBuffer.append('C');
                return;
            case 3:
                stringBuffer.append('B');
                return;
            case 4:
                stringBuffer.append('S');
                return;
            case 5:
                stringBuffer.append('I');
                return;
            case 6:
                stringBuffer.append('F');
                return;
            case 7:
                stringBuffer.append('J');
                return;
            case 8:
                stringBuffer.append('D');
                return;
            default:
                stringBuffer.append(this.buf, this.off, this.len);
                return;
        }
    }

    private static void getDescriptor(StringBuffer stringBuffer, Class cls) {
        while (!cls.isPrimitive()) {
            if (!cls.isArray()) {
                stringBuffer.append('L');
                String name = cls.getName();
                int length = name.length();
                for (int i2 = 0; i2 < length; i2++) {
                    char charAt = name.charAt(i2);
                    if (charAt == '.') {
                        charAt = '/';
                    }
                    stringBuffer.append(charAt);
                }
                stringBuffer.append(';');
                return;
            }
            stringBuffer.append('[');
            cls = cls.getComponentType();
        }
        stringBuffer.append(cls == Integer.TYPE ? 'I' : cls == Void.TYPE ? 'V' : cls == Boolean.TYPE ? 'Z' : cls == Byte.TYPE ? 'B' : cls == Character.TYPE ? 'C' : cls == Short.TYPE ? 'S' : cls == Double.TYPE ? 'D' : cls == Float.TYPE ? 'F' : 'J');
    }

    public static String getInternalName(Class cls) {
        return cls.getName().replace('.', '/');
    }

    public static String getMethodDescriptor(Type type, Type[] typeArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        for (Type type2 : typeArr) {
            type2.getDescriptor(stringBuffer);
        }
        stringBuffer.append(')');
        type.getDescriptor(stringBuffer);
        return stringBuffer.toString();
    }

    public static String getMethodDescriptor(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        for (Class<?> cls : parameterTypes) {
            getDescriptor(stringBuffer, cls);
        }
        stringBuffer.append(')');
        getDescriptor(stringBuffer, method.getReturnType());
        return stringBuffer.toString();
    }

    public static Type getReturnType(String str) {
        return getType(str.toCharArray(), str.indexOf(41) + 1);
    }

    public static Type getReturnType(Method method) {
        return getType(method.getReturnType());
    }

    public static Type getType(Class cls) {
        return cls.isPrimitive() ? cls == Integer.TYPE ? INT_TYPE : cls == Void.TYPE ? VOID_TYPE : cls == Boolean.TYPE ? BOOLEAN_TYPE : cls == Byte.TYPE ? BYTE_TYPE : cls == Character.TYPE ? CHAR_TYPE : cls == Short.TYPE ? SHORT_TYPE : cls == Double.TYPE ? DOUBLE_TYPE : cls == Float.TYPE ? FLOAT_TYPE : LONG_TYPE : getType(getDescriptor(cls));
    }

    public static Type getType(String str) {
        return getType(str.toCharArray(), 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x004b, code lost:
    
        if (r6[r4] == 'L') goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x004d, code lost:
    
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0053, code lost:
    
        if (r6[r7 + r0] != ';') goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x005d, code lost:
    
        return new bsh.org.objectweb.asm.Type(9, r6, r7, r0 + 1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static bsh.org.objectweb.asm.Type getType(char[] r6, int r7) {
        /*
            char r0 = r6[r7]
            r1 = 70
            if (r0 == r1) goto L70
            r1 = 83
            if (r0 == r1) goto L6d
            r1 = 86
            if (r0 == r1) goto L6a
            r1 = 73
            if (r0 == r1) goto L67
            r1 = 74
            if (r0 == r1) goto L64
            r1 = 90
            if (r0 == r1) goto L61
            r1 = 59
            r2 = 91
            r3 = 1
            if (r0 == r2) goto L40
            switch(r0) {
                case 66: goto L3d;
                case 67: goto L3a;
                case 68: goto L37;
                default: goto L24;
            }
        L24:
            r0 = 1
        L25:
            int r2 = r7 + r0
            char r2 = r6[r2]
            if (r2 != r1) goto L34
            bsh.org.objectweb.asm.Type r1 = new bsh.org.objectweb.asm.Type
            r2 = 10
            int r0 = r0 + r3
            r1.<init>(r2, r6, r7, r0)
            return r1
        L34:
            int r0 = r0 + 1
            goto L25
        L37:
            bsh.org.objectweb.asm.Type r6 = bsh.org.objectweb.asm.Type.DOUBLE_TYPE
            return r6
        L3a:
            bsh.org.objectweb.asm.Type r6 = bsh.org.objectweb.asm.Type.CHAR_TYPE
            return r6
        L3d:
            bsh.org.objectweb.asm.Type r6 = bsh.org.objectweb.asm.Type.BYTE_TYPE
            return r6
        L40:
            r0 = 1
        L41:
            int r4 = r7 + r0
            char r5 = r6[r4]
            if (r5 == r2) goto L5e
            char r2 = r6[r4]
            r4 = 76
            if (r2 != r4) goto L55
        L4d:
            int r0 = r0 + 1
            int r2 = r7 + r0
            char r2 = r6[r2]
            if (r2 != r1) goto L4d
        L55:
            bsh.org.objectweb.asm.Type r1 = new bsh.org.objectweb.asm.Type
            r2 = 9
            int r0 = r0 + r3
            r1.<init>(r2, r6, r7, r0)
            return r1
        L5e:
            int r0 = r0 + 1
            goto L41
        L61:
            bsh.org.objectweb.asm.Type r6 = bsh.org.objectweb.asm.Type.BOOLEAN_TYPE
            return r6
        L64:
            bsh.org.objectweb.asm.Type r6 = bsh.org.objectweb.asm.Type.LONG_TYPE
            return r6
        L67:
            bsh.org.objectweb.asm.Type r6 = bsh.org.objectweb.asm.Type.INT_TYPE
            return r6
        L6a:
            bsh.org.objectweb.asm.Type r6 = bsh.org.objectweb.asm.Type.VOID_TYPE
            return r6
        L6d:
            bsh.org.objectweb.asm.Type r6 = bsh.org.objectweb.asm.Type.SHORT_TYPE
            return r6
        L70:
            bsh.org.objectweb.asm.Type r6 = bsh.org.objectweb.asm.Type.FLOAT_TYPE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.org.objectweb.asm.Type.getType(char[], int):bsh.org.objectweb.asm.Type");
    }

    public String getClassName() {
        return new String(this.buf, this.off + 1, this.len - 2).replace('/', '.');
    }

    public String getDescriptor() {
        StringBuffer stringBuffer = new StringBuffer();
        getDescriptor(stringBuffer);
        return stringBuffer.toString();
    }

    public int getDimensions() {
        int i2 = 1;
        while (this.buf[this.off + i2] == '[') {
            i2++;
        }
        return i2;
    }

    public Type getElementType() {
        return getType(this.buf, this.off + getDimensions());
    }

    public String getInternalName() {
        return new String(this.buf, this.off + 1, this.len - 2);
    }

    public int getOpcode(int i2) {
        if (i2 != 46 && i2 != 79) {
            switch (this.sort) {
                case 0:
                    return i2 + 5;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    return i2;
                case 6:
                    return i2 + 2;
                case 7:
                    return i2 + 1;
                case 8:
                    return i2 + 3;
                default:
                    return i2 + 4;
            }
        }
        switch (this.sort) {
            case 0:
                return i2 + 5;
            case 1:
            case 3:
                return i2 + 6;
            case 2:
                return i2 + 7;
            case 4:
                return i2 + 8;
            case 5:
                return i2;
            case 6:
                return i2 + 2;
            case 7:
                return i2 + 1;
            case 8:
                return i2 + 3;
            default:
                return i2 + 4;
        }
    }

    public int getSize() {
        int i2 = this.sort;
        return (i2 == 7 || i2 == 8) ? 2 : 1;
    }

    public int getSort() {
        return this.sort;
    }
}
