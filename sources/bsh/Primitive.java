package bsh;

import java.io.Serializable;
import java.util.Hashtable;

/* loaded from: classes.dex */
public final class Primitive implements ParserConstants, Serializable {
    public static final Primitive NULL;
    public static final Primitive VOID;
    static /* synthetic */ Class class$bsh$Primitive;
    static /* synthetic */ Class class$java$lang$Boolean;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Character;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Integer;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Short;
    static Hashtable wrapperMap;
    private Object value;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Special implements Serializable {
        public static final Special NULL_VALUE = new Special();
        public static final Special VOID_TYPE = new Special();

        private Special() {
        }
    }

    static {
        Hashtable hashtable = new Hashtable();
        wrapperMap = hashtable;
        Class cls = Boolean.TYPE;
        Class cls2 = class$java$lang$Boolean;
        if (cls2 == null) {
            cls2 = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls2;
        }
        hashtable.put(cls, cls2);
        Hashtable hashtable2 = wrapperMap;
        Class cls3 = Byte.TYPE;
        Class cls4 = class$java$lang$Byte;
        if (cls4 == null) {
            cls4 = class$("java.lang.Byte");
            class$java$lang$Byte = cls4;
        }
        hashtable2.put(cls3, cls4);
        Hashtable hashtable3 = wrapperMap;
        Class cls5 = Short.TYPE;
        Class cls6 = class$java$lang$Short;
        if (cls6 == null) {
            cls6 = class$("java.lang.Short");
            class$java$lang$Short = cls6;
        }
        hashtable3.put(cls5, cls6);
        Hashtable hashtable4 = wrapperMap;
        Class cls7 = Character.TYPE;
        Class cls8 = class$java$lang$Character;
        if (cls8 == null) {
            cls8 = class$("java.lang.Character");
            class$java$lang$Character = cls8;
        }
        hashtable4.put(cls7, cls8);
        Hashtable hashtable5 = wrapperMap;
        Class cls9 = Integer.TYPE;
        Class cls10 = class$java$lang$Integer;
        if (cls10 == null) {
            cls10 = class$("java.lang.Integer");
            class$java$lang$Integer = cls10;
        }
        hashtable5.put(cls9, cls10);
        Hashtable hashtable6 = wrapperMap;
        Class cls11 = Long.TYPE;
        Class cls12 = class$java$lang$Long;
        if (cls12 == null) {
            cls12 = class$("java.lang.Long");
            class$java$lang$Long = cls12;
        }
        hashtable6.put(cls11, cls12);
        Hashtable hashtable7 = wrapperMap;
        Class cls13 = Float.TYPE;
        Class cls14 = class$java$lang$Float;
        if (cls14 == null) {
            cls14 = class$("java.lang.Float");
            class$java$lang$Float = cls14;
        }
        hashtable7.put(cls13, cls14);
        Hashtable hashtable8 = wrapperMap;
        Class cls15 = Double.TYPE;
        Class cls16 = class$java$lang$Double;
        if (cls16 == null) {
            cls16 = class$("java.lang.Double");
            class$java$lang$Double = cls16;
        }
        hashtable8.put(cls15, cls16);
        Hashtable hashtable9 = wrapperMap;
        Class cls17 = class$java$lang$Boolean;
        if (cls17 == null) {
            cls17 = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls17;
        }
        hashtable9.put(cls17, cls);
        Hashtable hashtable10 = wrapperMap;
        Class cls18 = class$java$lang$Byte;
        if (cls18 == null) {
            cls18 = class$("java.lang.Byte");
            class$java$lang$Byte = cls18;
        }
        hashtable10.put(cls18, Byte.TYPE);
        Hashtable hashtable11 = wrapperMap;
        Class cls19 = class$java$lang$Short;
        if (cls19 == null) {
            cls19 = class$("java.lang.Short");
            class$java$lang$Short = cls19;
        }
        hashtable11.put(cls19, Short.TYPE);
        Hashtable hashtable12 = wrapperMap;
        Class cls20 = class$java$lang$Character;
        if (cls20 == null) {
            cls20 = class$("java.lang.Character");
            class$java$lang$Character = cls20;
        }
        hashtable12.put(cls20, Character.TYPE);
        Hashtable hashtable13 = wrapperMap;
        Class cls21 = class$java$lang$Integer;
        if (cls21 == null) {
            cls21 = class$("java.lang.Integer");
            class$java$lang$Integer = cls21;
        }
        hashtable13.put(cls21, cls9);
        Hashtable hashtable14 = wrapperMap;
        Class cls22 = class$java$lang$Long;
        if (cls22 == null) {
            cls22 = class$("java.lang.Long");
            class$java$lang$Long = cls22;
        }
        hashtable14.put(cls22, cls11);
        Hashtable hashtable15 = wrapperMap;
        Class cls23 = class$java$lang$Float;
        if (cls23 == null) {
            cls23 = class$("java.lang.Float");
            class$java$lang$Float = cls23;
        }
        hashtable15.put(cls23, cls13);
        Hashtable hashtable16 = wrapperMap;
        Class cls24 = class$java$lang$Double;
        if (cls24 == null) {
            cls24 = class$("java.lang.Double");
            class$java$lang$Double = cls24;
        }
        hashtable16.put(cls24, Double.TYPE);
        NULL = new Primitive(Special.NULL_VALUE);
        VOID = new Primitive(Special.VOID_TYPE);
    }

    public Primitive(byte b) {
        this(new Byte(b));
    }

    public Primitive(char c2) {
        this(new Character(c2));
    }

    public Primitive(double d2) {
        this(new Double(d2));
    }

    public Primitive(float f) {
        this(new Float(f));
    }

    public Primitive(int i2) {
        this(new Integer(i2));
    }

    public Primitive(long j) {
        this(new Long(j));
    }

    public Primitive(Object obj) {
        if (obj == null) {
            throw new InterpreterError("Use Primitve.NULL instead of Primitive(null)");
        }
        if (obj == Special.NULL_VALUE || obj == Special.VOID_TYPE || isWrapperType(obj.getClass())) {
            this.value = obj;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Not a wrapper type: ");
        stringBuffer.append(obj);
        throw new InterpreterError(stringBuffer.toString());
    }

    public Primitive(short s) {
        this(new Short(s));
    }

    public Primitive(boolean z) {
        this(new Boolean(z));
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0058, code lost:
    
        if (r1 != r5) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object binaryOperation(java.lang.Object r4, java.lang.Object r5, int r6) {
        /*
            bsh.Primitive r0 = bsh.Primitive.NULL
            if (r4 == r0) goto L9d
            if (r5 == r0) goto L9d
            bsh.Primitive r0 = bsh.Primitive.VOID
            if (r4 == r0) goto L95
            if (r5 == r0) goto L95
            java.lang.Class r0 = r4.getClass()
            java.lang.Class r1 = r5.getClass()
            boolean r2 = r4 instanceof bsh.Primitive
            if (r2 == 0) goto L1e
            bsh.Primitive r4 = (bsh.Primitive) r4
            java.lang.Object r4 = r4.getValue()
        L1e:
            boolean r2 = r5 instanceof bsh.Primitive
            if (r2 == 0) goto L28
            bsh.Primitive r5 = (bsh.Primitive) r5
            java.lang.Object r5 = r5.getValue()
        L28:
            java.lang.Object[] r4 = promotePrimitives(r4, r5)
            r5 = 0
            r5 = r4[r5]
            r2 = 1
            r4 = r4[r2]
            java.lang.Class r2 = r5.getClass()
            java.lang.Class r3 = r4.getClass()
            if (r2 != r3) goto L6e
            java.lang.Object r4 = binaryOperationImpl(r5, r4, r6)     // Catch: java.lang.ArithmeticException -> L65
            java.lang.Class r5 = bsh.Primitive.class$bsh$Primitive
            java.lang.String r6 = "bsh.Primitive"
            if (r5 != 0) goto L4c
            java.lang.Class r5 = class$(r6)
            bsh.Primitive.class$bsh$Primitive = r5
        L4c:
            if (r0 != r5) goto L5a
            java.lang.Class r5 = bsh.Primitive.class$bsh$Primitive
            if (r5 != 0) goto L58
            java.lang.Class r5 = class$(r6)
            bsh.Primitive.class$bsh$Primitive = r5
        L58:
            if (r1 == r5) goto L5e
        L5a:
            boolean r5 = r4 instanceof java.lang.Boolean
            if (r5 == 0) goto L64
        L5e:
            bsh.Primitive r5 = new bsh.Primitive
            r5.<init>(r4)
            return r5
        L64:
            return r4
        L65:
            r4 = move-exception
            bsh.UtilTargetError r5 = new bsh.UtilTargetError
            java.lang.String r6 = "Arithemetic Exception in binary op"
            r5.<init>(r6, r4)
            throw r5
        L6e:
            bsh.UtilEvalError r6 = new bsh.UtilEvalError
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "Type mismatch in operator.  "
            r0.append(r1)
            java.lang.Class r5 = r5.getClass()
            r0.append(r5)
            java.lang.String r5 = " cannot be used with "
            r0.append(r5)
            java.lang.Class r4 = r4.getClass()
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r6.<init>(r4)
            throw r6
        L95:
            bsh.UtilEvalError r4 = new bsh.UtilEvalError
            java.lang.String r5 = "Undefined variable, class, or 'void' literal in binary operation"
            r4.<init>(r5)
            throw r4
        L9d:
            bsh.UtilEvalError r4 = new bsh.UtilEvalError
            java.lang.String r5 = "Null value or 'null' literal in binary operation"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.Primitive.binaryOperation(java.lang.Object, java.lang.Object, int):java.lang.Object");
    }

    static Object binaryOperationImpl(Object obj, Object obj2, int i2) {
        if (obj instanceof Boolean) {
            return booleanBinaryOperation((Boolean) obj, (Boolean) obj2, i2);
        }
        if (obj instanceof Integer) {
            return intBinaryOperation((Integer) obj, (Integer) obj2, i2);
        }
        if (obj instanceof Long) {
            return longBinaryOperation((Long) obj, (Long) obj2, i2);
        }
        if (obj instanceof Float) {
            return floatBinaryOperation((Float) obj, (Float) obj2, i2);
        }
        if (obj instanceof Double) {
            return doubleBinaryOperation((Double) obj, (Double) obj2, i2);
        }
        throw new UtilEvalError("Invalid types in binary operator");
    }

    static Boolean booleanBinaryOperation(Boolean bool, Boolean bool2, int i2) {
        boolean booleanValue = bool.booleanValue();
        boolean booleanValue2 = bool2.booleanValue();
        if (i2 == 90) {
            return new Boolean(booleanValue == booleanValue2);
        }
        switch (i2) {
            case 95:
                return new Boolean(booleanValue != booleanValue2);
            case 96:
            case 97:
                if (!booleanValue && !booleanValue2) {
                    r1 = false;
                }
                return new Boolean(r1);
            case 98:
            case 99:
                return new Boolean(booleanValue && booleanValue2);
            default:
                throw new InterpreterError("unimplemented binary operator");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean booleanUnaryOperation(Boolean bool, int i2) {
        boolean booleanValue = bool.booleanValue();
        if (i2 == 86) {
            return !booleanValue;
        }
        throw new UtilEvalError("Operator inappropriate for boolean");
    }

    public static Class boxType(Class cls) {
        Class cls2 = (Class) wrapperMap.get(cls);
        if (cls2 != null) {
            return cls2;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Not a primitive type: ");
        stringBuffer.append(cls);
        throw new InterpreterError(stringBuffer.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Primitive castPrimitive(Class cls, Class cls2, Primitive primitive, boolean z, int i2) {
        if (z && primitive != null) {
            throw new InterpreterError("bad cast param 1");
        }
        if (!z && primitive == null) {
            throw new InterpreterError("bad cast param 2");
        }
        if (cls2 != null && !cls2.isPrimitive()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("bad fromType:");
            stringBuffer.append(cls2);
            throw new InterpreterError(stringBuffer.toString());
        }
        Primitive primitive2 = NULL;
        if (primitive == primitive2 && cls2 != null) {
            throw new InterpreterError("inconsistent args 1");
        }
        if (primitive == VOID && cls2 != Void.TYPE) {
            throw new InterpreterError("inconsistent args 2");
        }
        if (cls2 == Void.TYPE) {
            if (z) {
                return Types.INVALID_CAST;
            }
            throw Types.castError(Reflect.normalizeClassName(cls), "void value", i2);
        }
        Object value = primitive != null ? primitive.getValue() : null;
        if (!cls.isPrimitive()) {
            if (cls2 == null) {
                return z ? Types.VALID_CAST : primitive2;
            }
            if (z) {
                return Types.INVALID_CAST;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("object type:");
            stringBuffer2.append(cls);
            throw Types.castError(stringBuffer2.toString(), "primitive value", i2);
        }
        if (cls2 == null) {
            if (z) {
                return Types.INVALID_CAST;
            }
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("primitive type:");
            stringBuffer3.append(cls);
            throw Types.castError(stringBuffer3.toString(), "Null value", i2);
        }
        Class cls3 = Boolean.TYPE;
        if (cls2 == cls3) {
            if (cls == cls3) {
                return z ? Types.VALID_CAST : primitive;
            }
            if (z) {
                return Types.INVALID_CAST;
            }
            throw Types.castError(cls, cls2, i2);
        }
        if (i2 != 1 || Types.isJavaAssignable(cls, cls2)) {
            return z ? Types.VALID_CAST : new Primitive(castWrapper(cls, value));
        }
        if (z) {
            return Types.INVALID_CAST;
        }
        throw Types.castError(cls, cls2, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object castWrapper(Class cls, Object obj) {
        if (!cls.isPrimitive()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("invalid type in castWrapper: ");
            stringBuffer.append(cls);
            throw new InterpreterError(stringBuffer.toString());
        }
        if (obj == null) {
            throw new InterpreterError("null value in castWrapper, guard");
        }
        if (obj instanceof Boolean) {
            if (cls == Boolean.TYPE) {
                return obj;
            }
            throw new InterpreterError("bad wrapper cast of boolean");
        }
        if (obj instanceof Character) {
            obj = new Integer(((Character) obj).charValue());
        }
        if (!(obj instanceof Number)) {
            throw new InterpreterError("bad type in cast");
        }
        Number number = (Number) obj;
        if (cls == Byte.TYPE) {
            return new Byte(number.byteValue());
        }
        if (cls == Short.TYPE) {
            return new Short(number.shortValue());
        }
        if (cls == Character.TYPE) {
            return new Character((char) number.intValue());
        }
        if (cls == Integer.TYPE) {
            return new Integer(number.intValue());
        }
        if (cls == Long.TYPE) {
            return new Long(number.longValue());
        }
        if (cls == Float.TYPE) {
            return new Float(number.floatValue());
        }
        if (cls == Double.TYPE) {
            return new Double(number.doubleValue());
        }
        throw new InterpreterError("error in wrapper cast");
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static Object doubleBinaryOperation(Double d2, Double d3, int i2) {
        double doubleValue = d2.doubleValue();
        double doubleValue2 = d3.doubleValue();
        switch (i2) {
            case 82:
            case 83:
                return new Boolean(doubleValue > doubleValue2);
            case 84:
            case 85:
                return new Boolean(doubleValue < doubleValue2);
            default:
                switch (i2) {
                    case 90:
                        return new Boolean(doubleValue == doubleValue2);
                    case 91:
                    case 92:
                        return new Boolean(doubleValue <= doubleValue2);
                    case 93:
                    case 94:
                        return new Boolean(doubleValue >= doubleValue2);
                    case 95:
                        return new Boolean(doubleValue != doubleValue2);
                    default:
                        switch (i2) {
                            case 102:
                                return new Double(doubleValue + doubleValue2);
                            case 103:
                                return new Double(doubleValue - doubleValue2);
                            case 104:
                                return new Double(doubleValue * doubleValue2);
                            case 105:
                                return new Double(doubleValue / doubleValue2);
                            default:
                                switch (i2) {
                                    case 111:
                                        return new Double(doubleValue % doubleValue2);
                                    case 112:
                                    case 113:
                                    case 114:
                                    case 115:
                                    case 116:
                                    case 117:
                                        throw new UtilEvalError("Can't shift doubles");
                                    default:
                                        throw new InterpreterError("Unimplemented binary double operator");
                                }
                        }
                }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double doubleUnaryOperation(Double d2, int i2) {
        double doubleValue = d2.doubleValue();
        if (i2 == 102) {
            return doubleValue;
        }
        if (i2 == 103) {
            return -doubleValue;
        }
        throw new InterpreterError("bad double unaryOperation");
    }

    static Object floatBinaryOperation(Float f, Float f2, int i2) {
        float floatValue = f.floatValue();
        float floatValue2 = f2.floatValue();
        switch (i2) {
            case 82:
            case 83:
                return new Boolean(floatValue > floatValue2);
            case 84:
            case 85:
                return new Boolean(floatValue < floatValue2);
            default:
                switch (i2) {
                    case 90:
                        return new Boolean(floatValue == floatValue2);
                    case 91:
                    case 92:
                        return new Boolean(floatValue <= floatValue2);
                    case 93:
                    case 94:
                        return new Boolean(floatValue >= floatValue2);
                    case 95:
                        return new Boolean(floatValue != floatValue2);
                    default:
                        switch (i2) {
                            case 102:
                                return new Float(floatValue + floatValue2);
                            case 103:
                                return new Float(floatValue - floatValue2);
                            case 104:
                                return new Float(floatValue * floatValue2);
                            case 105:
                                return new Float(floatValue / floatValue2);
                            default:
                                switch (i2) {
                                    case 111:
                                        return new Float(floatValue % floatValue2);
                                    case 112:
                                    case 113:
                                    case 114:
                                    case 115:
                                    case 116:
                                    case 117:
                                        throw new UtilEvalError("Can't shift floats ");
                                    default:
                                        throw new InterpreterError("Unimplemented binary float operator");
                                }
                        }
                }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float floatUnaryOperation(Float f, int i2) {
        float floatValue = f.floatValue();
        if (i2 == 102) {
            return floatValue;
        }
        if (i2 == 103) {
            return -floatValue;
        }
        throw new InterpreterError("bad float unaryOperation");
    }

    public static Primitive getDefaultValue(Class cls) {
        if (cls == null || !cls.isPrimitive()) {
            return NULL;
        }
        if (cls == Boolean.TYPE) {
            return new Primitive(false);
        }
        try {
            return new Primitive(0).castToType(cls, 0);
        } catch (UtilEvalError unused) {
            throw new InterpreterError("bad cast");
        }
    }

    static Object intBinaryOperation(Integer num, Integer num2, int i2) {
        int intValue = num.intValue();
        int intValue2 = num2.intValue();
        switch (i2) {
            case 82:
            case 83:
                return new Boolean(intValue > intValue2);
            case 84:
            case 85:
                return new Boolean(intValue < intValue2);
            default:
                switch (i2) {
                    case 90:
                        return new Boolean(intValue == intValue2);
                    case 91:
                    case 92:
                        return new Boolean(intValue <= intValue2);
                    case 93:
                    case 94:
                        return new Boolean(intValue >= intValue2);
                    case 95:
                        return new Boolean(intValue != intValue2);
                    default:
                        switch (i2) {
                            case 102:
                                return new Integer(intValue + intValue2);
                            case 103:
                                return new Integer(intValue - intValue2);
                            case 104:
                                return new Integer(intValue * intValue2);
                            case 105:
                                return new Integer(intValue / intValue2);
                            case 106:
                            case 107:
                                return new Integer(intValue & intValue2);
                            case 108:
                            case 109:
                                return new Integer(intValue | intValue2);
                            case 110:
                                return new Integer(intValue ^ intValue2);
                            case 111:
                                return new Integer(intValue % intValue2);
                            case 112:
                            case 113:
                                return new Integer(intValue << intValue2);
                            case 114:
                            case 115:
                                return new Integer(intValue >> intValue2);
                            case 116:
                            case 117:
                                return new Integer(intValue >>> intValue2);
                            default:
                                throw new InterpreterError("Unimplemented binary integer operator");
                        }
                }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int intUnaryOperation(Integer num, int i2) {
        int intValue = num.intValue();
        if (i2 == 87) {
            return intValue ^ (-1);
        }
        switch (i2) {
            case 100:
                return intValue + 1;
            case 101:
                return intValue - 1;
            case 102:
                return intValue;
            case 103:
                return -intValue;
            default:
                throw new InterpreterError("bad integer unaryOperation");
        }
    }

    public static boolean isWrapperType(Class cls) {
        return (wrapperMap.get(cls) == null || cls.isPrimitive()) ? false : true;
    }

    static Object longBinaryOperation(Long l, Long l2, int i2) {
        long longValue = l.longValue();
        long longValue2 = l2.longValue();
        switch (i2) {
            case 82:
            case 83:
                return new Boolean(longValue > longValue2);
            case 84:
            case 85:
                return new Boolean(longValue < longValue2);
            default:
                switch (i2) {
                    case 90:
                        return new Boolean(longValue == longValue2);
                    case 91:
                    case 92:
                        return new Boolean(longValue <= longValue2);
                    case 93:
                    case 94:
                        return new Boolean(longValue >= longValue2);
                    case 95:
                        return new Boolean(longValue != longValue2);
                    default:
                        switch (i2) {
                            case 102:
                                return new Long(longValue + longValue2);
                            case 103:
                                return new Long(longValue - longValue2);
                            case 104:
                                return new Long(longValue * longValue2);
                            case 105:
                                return new Long(longValue / longValue2);
                            case 106:
                            case 107:
                                return new Long(longValue2 & longValue);
                            case 108:
                            case 109:
                                return new Long(longValue2 | longValue);
                            case 110:
                                return new Long(longValue2 ^ longValue);
                            case 111:
                                return new Long(longValue % longValue2);
                            case 112:
                            case 113:
                                return new Long(longValue << ((int) longValue2));
                            case 114:
                            case 115:
                                return new Long(longValue >> ((int) longValue2));
                            case 116:
                            case 117:
                                return new Long(longValue >>> ((int) longValue2));
                            default:
                                throw new InterpreterError("Unimplemented binary long operator");
                        }
                }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long longUnaryOperation(Long l, int i2) {
        long longValue = l.longValue();
        if (i2 == 87) {
            return (-1) ^ longValue;
        }
        switch (i2) {
            case 100:
                return longValue + 1;
            case 101:
                return longValue - 1;
            case 102:
                return longValue;
            case 103:
                return -longValue;
            default:
                throw new InterpreterError("bad long unaryOperation");
        }
    }

    static Object[] promotePrimitives(Object obj, Object obj2) {
        Object promoteToInteger = promoteToInteger(obj);
        Object promoteToInteger2 = promoteToInteger(obj2);
        if ((promoteToInteger instanceof Number) && (promoteToInteger2 instanceof Number)) {
            Number number = (Number) promoteToInteger;
            Number number2 = (Number) promoteToInteger2;
            boolean z = number instanceof Double;
            if (!z && !(number2 instanceof Double)) {
                boolean z2 = number instanceof Float;
                if (!z2 && !(number2 instanceof Float)) {
                    boolean z3 = number instanceof Long;
                    if (z3 || (number2 instanceof Long)) {
                        if (z3) {
                            promoteToInteger2 = new Long(number2.longValue());
                        } else {
                            promoteToInteger = new Long(number.longValue());
                        }
                    }
                } else if (z2) {
                    promoteToInteger2 = new Float(number2.floatValue());
                } else {
                    promoteToInteger = new Float(number.floatValue());
                }
            } else if (z) {
                promoteToInteger2 = new Double(number2.doubleValue());
            } else {
                promoteToInteger = new Double(number.doubleValue());
            }
        }
        return new Object[]{promoteToInteger, promoteToInteger2};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object promoteToInteger(Object obj) {
        return obj instanceof Character ? new Integer(((Character) obj).charValue()) : ((obj instanceof Byte) || (obj instanceof Short)) ? new Integer(((Number) obj).intValue()) : obj;
    }

    public static Primitive unaryOperation(Primitive primitive, int i2) {
        if (primitive == NULL) {
            throw new UtilEvalError("illegal use of null object or 'null' literal");
        }
        if (primitive == VOID) {
            throw new UtilEvalError("illegal use of undefined object or 'void' literal");
        }
        Class type = primitive.getType();
        Object promoteToInteger = promoteToInteger(primitive.getValue());
        if (promoteToInteger instanceof Boolean) {
            return new Primitive(booleanUnaryOperation((Boolean) promoteToInteger, i2));
        }
        if (!(promoteToInteger instanceof Integer)) {
            if (promoteToInteger instanceof Long) {
                return new Primitive(longUnaryOperation((Long) promoteToInteger, i2));
            }
            if (promoteToInteger instanceof Float) {
                return new Primitive(floatUnaryOperation((Float) promoteToInteger, i2));
            }
            if (promoteToInteger instanceof Double) {
                return new Primitive(doubleUnaryOperation((Double) promoteToInteger, i2));
            }
            throw new InterpreterError("An error occurred.  Please call technical support.");
        }
        int intUnaryOperation = intUnaryOperation((Integer) promoteToInteger, i2);
        if (i2 == 100 || i2 == 101) {
            if (type == Byte.TYPE) {
                return new Primitive((byte) intUnaryOperation);
            }
            if (type == Short.TYPE) {
                return new Primitive((short) intUnaryOperation);
            }
            if (type == Character.TYPE) {
                return new Primitive((char) intUnaryOperation);
            }
        }
        return new Primitive(intUnaryOperation);
    }

    public static Class unboxType(Class cls) {
        Class cls2 = (Class) wrapperMap.get(cls);
        if (cls2 != null) {
            return cls2;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Not a primitive wrapper type: ");
        stringBuffer.append(cls);
        throw new InterpreterError(stringBuffer.toString());
    }

    public static Object unwrap(Object obj) {
        if (obj == VOID) {
            return null;
        }
        return obj instanceof Primitive ? ((Primitive) obj).getValue() : obj;
    }

    public static Object[] unwrap(Object[] objArr) {
        Object[] objArr2 = new Object[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            objArr2[i2] = unwrap(objArr[i2]);
        }
        return objArr2;
    }

    public static Object wrap(Object obj, Class cls) {
        return cls == Void.TYPE ? VOID : obj == null ? NULL : cls.isPrimitive() ? new Primitive(obj) : obj;
    }

    public static Object[] wrap(Object[] objArr, Class[] clsArr) {
        if (objArr == null) {
            return null;
        }
        Object[] objArr2 = new Object[objArr.length];
        for (int i2 = 0; i2 < objArr.length; i2++) {
            objArr2[i2] = wrap(objArr[i2], clsArr[i2]);
        }
        return objArr2;
    }

    public boolean booleanValue() {
        Object obj = this.value;
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        throw new UtilEvalError("Primitive not a boolean");
    }

    public Primitive castToType(Class cls, int i2) {
        return castPrimitive(cls, getType(), this, false, i2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Primitive) {
            return ((Primitive) obj).value.equals(this.value);
        }
        return false;
    }

    public Class getType() {
        if (this == VOID) {
            return Void.TYPE;
        }
        if (this == NULL) {
            return null;
        }
        return unboxType(this.value.getClass());
    }

    public Object getValue() {
        Object obj = this.value;
        if (obj == Special.NULL_VALUE) {
            return null;
        }
        if (obj != Special.VOID_TYPE) {
            return obj;
        }
        throw new InterpreterError("attempt to unwrap void type");
    }

    public int hashCode() {
        return this.value.hashCode() * 21;
    }

    public int intValue() {
        Object obj = this.value;
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        throw new UtilEvalError("Primitive not a number");
    }

    public boolean isNumber() {
        return ((this.value instanceof Boolean) || this == NULL || this == VOID) ? false : true;
    }

    public Number numberValue() {
        Object obj = this.value;
        if (obj instanceof Character) {
            obj = new Integer(((Character) obj).charValue());
        }
        if (obj instanceof Number) {
            return (Number) obj;
        }
        throw new UtilEvalError("Primitive not a number");
    }

    public String toString() {
        Object obj = this.value;
        return obj == Special.NULL_VALUE ? "null" : obj == Special.VOID_TYPE ? "void" : obj.toString();
    }
}
