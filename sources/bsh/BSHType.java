package bsh;

import bsh.BshClassManager;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class BSHType extends SimpleNode implements BshClassManager.Listener {
    private int arrayDims;
    private Class baseType;
    String descriptor;
    private Class type;

    public BSHType(int i2) {
        super(i2);
    }

    public static String getTypeDescriptor(Class cls) {
        if (cls == Boolean.TYPE) {
            return "Z";
        }
        if (cls == Character.TYPE) {
            return "C";
        }
        if (cls == Byte.TYPE) {
            return "B";
        }
        if (cls == Short.TYPE) {
            return "S";
        }
        if (cls == Integer.TYPE) {
            return "I";
        }
        if (cls == Long.TYPE) {
            return "J";
        }
        if (cls == Float.TYPE) {
            return "F";
        }
        if (cls == Double.TYPE) {
            return "D";
        }
        if (cls == Void.TYPE) {
            return "V";
        }
        String replace = cls.getName().replace('.', '/');
        if (replace.startsWith("[") || replace.endsWith(";")) {
            return replace;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("L");
        stringBuffer.append(replace.replace('.', '/'));
        stringBuffer.append(";");
        return stringBuffer.toString();
    }

    public void addArrayDimension() {
        this.arrayDims++;
    }

    @Override // bsh.BshClassManager.Listener
    public void classLoaderChanged() {
        this.type = null;
        this.baseType = null;
    }

    public int getArrayDims() {
        return this.arrayDims;
    }

    public Class getBaseType() {
        return this.baseType;
    }

    public Class getType(CallStack callStack, Interpreter interpreter) {
        Class cls = this.type;
        if (cls != null) {
            return cls;
        }
        SimpleNode typeNode = getTypeNode();
        this.baseType = typeNode instanceof BSHPrimitiveType ? ((BSHPrimitiveType) typeNode).getType() : ((BSHAmbiguousName) typeNode).toClass(callStack, interpreter);
        int i2 = this.arrayDims;
        if (i2 > 0) {
            try {
                this.type = Array.newInstance((Class<?>) this.baseType, new int[i2]).getClass();
            } catch (Exception unused) {
                throw new EvalError("Couldn't construct array type", this, callStack);
            }
        } else {
            this.type = this.baseType;
        }
        interpreter.getClassManager().addListener(this);
        return this.type;
    }

    public String getTypeDescriptor(CallStack callStack, Interpreter interpreter, String str) {
        String stringBuffer;
        String str2 = this.descriptor;
        if (str2 != null) {
            return str2;
        }
        SimpleNode typeNode = getTypeNode();
        if (typeNode instanceof BSHPrimitiveType) {
            stringBuffer = getTypeDescriptor(((BSHPrimitiveType) typeNode).type);
        } else {
            String str3 = ((BSHAmbiguousName) typeNode).text;
            String classBeingDefined = interpreter.getClassManager().getClassBeingDefined(str3);
            Class cls = null;
            if (classBeingDefined == null) {
                try {
                    cls = ((BSHAmbiguousName) typeNode).toClass(callStack, interpreter);
                } catch (EvalError unused) {
                }
            } else {
                str3 = classBeingDefined;
            }
            if (cls != null) {
                stringBuffer = getTypeDescriptor(cls);
            } else if (str == null || Name.isCompound(str3)) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("L");
                stringBuffer2.append(str3.replace('.', '/'));
                stringBuffer2.append(";");
                stringBuffer = stringBuffer2.toString();
            } else {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("L");
                stringBuffer3.append(str.replace('.', '/'));
                stringBuffer3.append("/");
                stringBuffer3.append(str3);
                stringBuffer3.append(";");
                stringBuffer = stringBuffer3.toString();
            }
        }
        for (int i2 = 0; i2 < this.arrayDims; i2++) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("[");
            stringBuffer4.append(stringBuffer);
            stringBuffer = stringBuffer4.toString();
        }
        this.descriptor = stringBuffer;
        return stringBuffer;
    }

    SimpleNode getTypeNode() {
        return (SimpleNode) jjtGetChild(0);
    }
}
