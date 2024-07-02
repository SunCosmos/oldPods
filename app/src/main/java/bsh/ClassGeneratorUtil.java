package bsh;

import bsh.org.objectweb.asm.ClassWriter;
import bsh.org.objectweb.asm.CodeVisitor;
import bsh.org.objectweb.asm.Constants;
import bsh.org.objectweb.asm.Label;
import bsh.org.objectweb.asm.Type;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ClassGeneratorUtil implements Constants {
    static final String BSHCONSTRUCTORS = "_bshConstructors";
    static final String BSHINIT = "_bshInstanceInitializer";
    static final String BSHSTATIC = "_bshStatic";
    static final String BSHSUPER = "_bshSuper";
    static final String BSHTHIS = "_bshThis";
    static final int DEFAULTCONSTRUCTOR = -1;
    static final String OBJECT = "Ljava/lang/Object;";
    static /* synthetic */ Class array$Ljava$lang$Object;
    static /* synthetic */ Class class$bsh$CallStack;
    static /* synthetic */ Class class$bsh$Interpreter;
    static /* synthetic */ Class class$bsh$SimpleNode;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$String;
    Modifiers classModifiers;
    String className;
    NameSpace classStaticNameSpace;
    DelayedEvalBshMethod[] constructors;
    String fqClassName;
    Class[] interfaces;
    boolean isInterface;
    DelayedEvalBshMethod[] methods;
    Class superClass;
    String superClassName;
    Constructor[] superConstructors;
    Variable[] vars;

    /* loaded from: classes.dex */
    public static class ConstructorArgs {
        public static ConstructorArgs DEFAULT = new ConstructorArgs();
        int arg;
        Object[] args;
        public int selector;

        ConstructorArgs() {
            this.selector = -1;
            this.arg = 0;
        }

        ConstructorArgs(int i2, Object[] objArr) {
            this.selector = -1;
            this.arg = 0;
            this.selector = i2;
            this.args = objArr;
        }

        public boolean getBoolean() {
            return ((Boolean) next()).booleanValue();
        }

        public byte getByte() {
            return ((Byte) next()).byteValue();
        }

        public char getChar() {
            return ((Character) next()).charValue();
        }

        public double getDouble() {
            return ((Double) next()).doubleValue();
        }

        public float getFloat() {
            return ((Float) next()).floatValue();
        }

        public int getInt() {
            return ((Integer) next()).intValue();
        }

        public long getLong() {
            return ((Long) next()).longValue();
        }

        public Object getObject() {
            return next();
        }

        public short getShort() {
            return ((Short) next()).shortValue();
        }

        Object next() {
            Object[] objArr = this.args;
            int i2 = this.arg;
            this.arg = i2 + 1;
            return objArr[i2];
        }
    }

    public ClassGeneratorUtil(Modifiers modifiers, String str, String str2, Class cls, Class[] clsArr, Variable[] variableArr, DelayedEvalBshMethod[] delayedEvalBshMethodArr, NameSpace nameSpace, boolean z) {
        this.classModifiers = modifiers;
        this.className = str;
        if (str2 != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str2.replace('.', '/'));
            stringBuffer.append("/");
            stringBuffer.append(str);
            this.fqClassName = stringBuffer.toString();
        } else {
            this.fqClassName = str;
        }
        if (cls == null) {
            Class cls2 = class$java$lang$Object;
            if (cls2 == null) {
                cls2 = class$("java.lang.Object");
                class$java$lang$Object = cls2;
            }
            cls = cls2;
        }
        this.superClass = cls;
        this.superClassName = Type.getInternalName(cls);
        this.interfaces = clsArr == null ? new Class[0] : clsArr;
        this.vars = variableArr;
        this.classStaticNameSpace = nameSpace;
        this.superConstructors = cls.getDeclaredConstructors();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String baseName = getBaseName(str);
        for (int i2 = 0; i2 < delayedEvalBshMethodArr.length; i2++) {
            if (delayedEvalBshMethodArr[i2].getName().equals(baseName)) {
                arrayList.add(delayedEvalBshMethodArr[i2]);
            } else {
                arrayList2.add(delayedEvalBshMethodArr[i2]);
            }
        }
        this.constructors = (DelayedEvalBshMethod[]) arrayList.toArray(new DelayedEvalBshMethod[0]);
        this.methods = (DelayedEvalBshMethod[]) arrayList2.toArray(new DelayedEvalBshMethod[0]);
        try {
            nameSpace.setLocalVariable(BSHCONSTRUCTORS, this.constructors, false);
            this.isInterface = z;
        } catch (UtilEvalError unused) {
            throw new InterpreterError("can't set cons var");
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static String descriptorToClassName(String str) {
        return (str.startsWith("[") || !str.startsWith("L")) ? str : str.substring(1, str.length() - 1);
    }

    static void doSwitchBranch(int i2, String str, String[] strArr, Label label, Label[] labelArr, int i3, CodeVisitor codeVisitor) {
        codeVisitor.visitLabel(labelArr[i2]);
        codeVisitor.visitVarInsn(25, 0);
        for (String str2 : strArr) {
            String str3 = str2.equals("Z") ? "getBoolean" : str2.equals("B") ? "getByte" : str2.equals("C") ? "getChar" : str2.equals("S") ? "getShort" : str2.equals("I") ? "getInt" : str2.equals("J") ? "getLong" : str2.equals("D") ? "getDouble" : str2.equals("F") ? "getFloat" : "getObject";
            codeVisitor.visitVarInsn(25, i3);
            String str4 = str3.equals("getObject") ? OBJECT : str2;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("()");
            stringBuffer.append(str4);
            codeVisitor.visitMethodInsn(Constants.INVOKEVIRTUAL, "bsh/ClassGeneratorUtil$ConstructorArgs", str3, stringBuffer.toString());
            if (str3.equals("getObject")) {
                codeVisitor.visitTypeInsn(Constants.CHECKCAST, descriptorToClassName(str2));
            }
        }
        codeVisitor.visitMethodInsn(Constants.INVOKESPECIAL, str, "<init>", getMethodDescriptor("V", strArr));
        codeVisitor.visitJumpInsn(Constants.GOTO, label);
    }

    static void generateField(String str, String str2, int i2, ClassWriter classWriter) {
        classWriter.visitField(i2, str, str2, null);
    }

    static void generateMethod(String str, String str2, String str3, String str4, String[] strArr, int i2, ClassWriter classWriter) {
        int i3;
        StringBuffer stringBuffer;
        String str5;
        boolean z = (i2 & 8) != 0;
        if (str4 == null) {
            str4 = OBJECT;
        }
        CodeVisitor visitMethod = classWriter.visitMethod(i2, str3, getMethodDescriptor(str4, strArr), null);
        if ((i2 & 1024) != 0) {
            return;
        }
        if (z) {
            i3 = Constants.GETSTATIC;
            stringBuffer = new StringBuffer();
            str5 = BSHSTATIC;
        } else {
            visitMethod.visitVarInsn(25, 0);
            i3 = Constants.GETFIELD;
            stringBuffer = new StringBuffer();
            str5 = BSHTHIS;
        }
        stringBuffer.append(str5);
        stringBuffer.append(str);
        visitMethod.visitFieldInsn(i3, str2, stringBuffer.toString(), "Lbsh/This;");
        visitMethod.visitLdcInsn(str3);
        generateParameterReifierCode(strArr, z, visitMethod);
        visitMethod.visitInsn(1);
        visitMethod.visitInsn(1);
        visitMethod.visitInsn(1);
        visitMethod.visitInsn(4);
        Class cls = class$java$lang$Object;
        if (cls == null) {
            cls = class$("java.lang.Object");
            class$java$lang$Object = cls;
        }
        Type type = Type.getType(cls);
        Type[] typeArr = new Type[6];
        Class cls2 = class$java$lang$String;
        if (cls2 == null) {
            cls2 = class$("java.lang.String");
            class$java$lang$String = cls2;
        }
        typeArr[0] = Type.getType(cls2);
        Class cls3 = array$Ljava$lang$Object;
        if (cls3 == null) {
            cls3 = class$("[Ljava.lang.Object;");
            array$Ljava$lang$Object = cls3;
        }
        typeArr[1] = Type.getType(cls3);
        Class cls4 = class$bsh$Interpreter;
        if (cls4 == null) {
            cls4 = class$("bsh.Interpreter");
            class$bsh$Interpreter = cls4;
        }
        typeArr[2] = Type.getType(cls4);
        Class cls5 = class$bsh$CallStack;
        if (cls5 == null) {
            cls5 = class$("bsh.CallStack");
            class$bsh$CallStack = cls5;
        }
        typeArr[3] = Type.getType(cls5);
        Class cls6 = class$bsh$SimpleNode;
        if (cls6 == null) {
            cls6 = class$("bsh.SimpleNode");
            class$bsh$SimpleNode = cls6;
        }
        typeArr[4] = Type.getType(cls6);
        typeArr[5] = Type.getType(Boolean.TYPE);
        visitMethod.visitMethodInsn(Constants.INVOKEVIRTUAL, "bsh/This", "invokeMethod", Type.getMethodDescriptor(type, typeArr));
        visitMethod.visitMethodInsn(Constants.INVOKESTATIC, "bsh/Primitive", "unwrap", "(Ljava/lang/Object;)Ljava/lang/Object;");
        generateReturnCode(str4, visitMethod);
        visitMethod.visitMaxs(20, 20);
    }

    public static void generateParameterReifierCode(String[] strArr, boolean z, CodeVisitor codeVisitor) {
        codeVisitor.visitIntInsn(17, strArr.length);
        codeVisitor.visitTypeInsn(Constants.ANEWARRAY, "java/lang/Object");
        int i2 = !z ? 1 : 0;
        for (int i3 = 0; i3 < strArr.length; i3++) {
            String str = strArr[i3];
            codeVisitor.visitInsn(89);
            codeVisitor.visitIntInsn(17, i3);
            if (isPrimitive(str)) {
                int i4 = str.equals("F") ? 23 : str.equals("D") ? 24 : str.equals("J") ? 22 : 21;
                codeVisitor.visitTypeInsn(Constants.NEW, "bsh/Primitive");
                codeVisitor.visitInsn(89);
                codeVisitor.visitVarInsn(i4, i2);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("(");
                stringBuffer.append(str);
                stringBuffer.append(")V");
                codeVisitor.visitMethodInsn(Constants.INVOKESPECIAL, "bsh/Primitive", "<init>", stringBuffer.toString());
            } else {
                codeVisitor.visitVarInsn(25, i2);
            }
            codeVisitor.visitInsn(83);
            i2 += (str.equals("D") || str.equals("J")) ? 2 : 1;
        }
    }

    static void generatePlainReturnCode(String str, CodeVisitor codeVisitor) {
        int i2;
        if (str.equals("V")) {
            i2 = Constants.RETURN;
        } else {
            if (isPrimitive(str)) {
                int i3 = Constants.IRETURN;
                if (str.equals("D")) {
                    i3 = Constants.DRETURN;
                } else if (str.equals("F")) {
                    i3 = Constants.FRETURN;
                } else if (str.equals("J")) {
                    i3 = Constants.LRETURN;
                }
                codeVisitor.visitInsn(i3);
                return;
            }
            codeVisitor.visitTypeInsn(Constants.CHECKCAST, descriptorToClassName(str));
            i2 = Constants.ARETURN;
        }
        codeVisitor.visitInsn(i2);
    }

    public static void generateReturnCode(String str, CodeVisitor codeVisitor) {
        int i2;
        String str2;
        String str3;
        if (str.equals("V")) {
            codeVisitor.visitInsn(87);
            i2 = Constants.RETURN;
        } else {
            if (isPrimitive(str)) {
                int i3 = Constants.IRETURN;
                if (str.equals("B")) {
                    str2 = "java/lang/Byte";
                    str3 = "byteValue";
                } else if (str.equals("I")) {
                    str2 = "java/lang/Integer";
                    str3 = "intValue";
                } else if (str.equals("Z")) {
                    str2 = "java/lang/Boolean";
                    str3 = "booleanValue";
                } else if (str.equals("D")) {
                    i3 = Constants.DRETURN;
                    str2 = "java/lang/Double";
                    str3 = "doubleValue";
                } else if (str.equals("F")) {
                    i3 = Constants.FRETURN;
                    str2 = "java/lang/Float";
                    str3 = "floatValue";
                } else if (str.equals("J")) {
                    i3 = Constants.LRETURN;
                    str2 = "java/lang/Long";
                    str3 = "longValue";
                } else if (str.equals("C")) {
                    str2 = "java/lang/Character";
                    str3 = "charValue";
                } else {
                    str2 = "java/lang/Short";
                    str3 = "shortValue";
                }
                codeVisitor.visitTypeInsn(Constants.CHECKCAST, str2);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("()");
                stringBuffer.append(str);
                codeVisitor.visitMethodInsn(Constants.INVOKEVIRTUAL, str2, str3, stringBuffer.toString());
                codeVisitor.visitInsn(i3);
                return;
            }
            codeVisitor.visitTypeInsn(Constants.CHECKCAST, descriptorToClassName(str));
            i2 = Constants.ARETURN;
        }
        codeVisitor.visitInsn(i2);
    }

    static void generateSuperDelegateMethod(String str, String str2, String str3, String[] strArr, int i2, ClassWriter classWriter) {
        if (str3 == null) {
            str3 = OBJECT;
        }
        String methodDescriptor = getMethodDescriptor(str3, strArr);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(BSHSUPER);
        stringBuffer.append(str2);
        CodeVisitor visitMethod = classWriter.visitMethod(i2, stringBuffer.toString(), methodDescriptor, null);
        visitMethod.visitVarInsn(25, 0);
        int i3 = 1;
        for (int i4 = 0; i4 < strArr.length; i4++) {
            if (isPrimitive(strArr[i4])) {
                visitMethod.visitVarInsn(21, i3);
            } else {
                visitMethod.visitVarInsn(25, i3);
            }
            i3 += (strArr[i4].equals("D") || strArr[i4].equals("J")) ? 2 : 1;
        }
        visitMethod.visitMethodInsn(Constants.INVOKESPECIAL, str, str2, methodDescriptor);
        generatePlainReturnCode(str3, visitMethod);
        visitMethod.visitMaxs(20, 20);
    }

    static int getASMModifiers(Modifiers modifiers) {
        if (modifiers == null) {
            return 0;
        }
        int i2 = modifiers.hasModifier("public") ? 1 : 0;
        if (modifiers.hasModifier("protected")) {
            i2 += 4;
        }
        if (modifiers.hasModifier("static")) {
            i2 += 8;
        }
        if (modifiers.hasModifier("synchronized")) {
            i2 += 32;
        }
        return modifiers.hasModifier("abstract") ? i2 + 1024 : i2;
    }

    private static String getBaseName(String str) {
        int indexOf = str.indexOf("$");
        return indexOf == -1 ? str : str.substring(indexOf + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static This getClassInstanceThis(Object obj, String str) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(BSHTHIS);
            stringBuffer.append(str);
            return (This) Primitive.unwrap(Reflect.getObjectFieldValue(obj, stringBuffer.toString()));
        } catch (Exception e) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Generated class: Error getting This");
            stringBuffer2.append(e);
            throw new InterpreterError(stringBuffer2.toString());
        }
    }

    static This getClassStaticThis(Class cls, String str) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(BSHSTATIC);
            stringBuffer.append(str);
            return (This) Reflect.getStaticFieldValue(cls, stringBuffer.toString());
        } catch (Exception e) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Unable to get class static space: ");
            stringBuffer2.append(e);
            throw new InterpreterError(stringBuffer2.toString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static bsh.ClassGeneratorUtil.ConstructorArgs getConstructorArgs(java.lang.String r16, bsh.This r17, java.lang.Object[] r18, int r19) {
        /*
            Method dump skipped, instructions count: 353
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.ClassGeneratorUtil.getConstructorArgs(java.lang.String, bsh.This, java.lang.Object[], int):bsh.ClassGeneratorUtil$ConstructorArgs");
    }

    static String getMethodDescriptor(String str, String[] strArr) {
        StringBuffer stringBuffer = new StringBuffer("(");
        for (String str2 : strArr) {
            stringBuffer.append(str2);
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(")");
        stringBuffer2.append(str);
        stringBuffer.append(stringBuffer2.toString());
        return stringBuffer.toString();
    }

    static String[] getTypeDescriptors(Class[] clsArr) {
        int length = clsArr.length;
        String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            strArr[i2] = BSHType.getTypeDescriptor(clsArr[i2]);
        }
        return strArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00d3, code lost:
    
        r1.invoke(r12, r3, r4, null, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00d9, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void initInstance(java.lang.Object r10, java.lang.String r11, java.lang.Object[] r12) {
        /*
            Method dump skipped, instructions count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.ClassGeneratorUtil.initInstance(java.lang.Object, java.lang.String, java.lang.Object[]):void");
    }

    private static boolean isPrimitive(String str) {
        return str.length() == 1;
    }

    boolean classContainsMethod(Class cls, String str, String[] strArr) {
        boolean z;
        while (cls != null) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            for (int i2 = 0; i2 < declaredMethods.length; i2++) {
                if (declaredMethods[i2].getName().equals(str)) {
                    String[] typeDescriptors = getTypeDescriptors(declaredMethods[i2].getParameterTypes());
                    int i3 = 0;
                    while (true) {
                        if (i3 >= typeDescriptors.length) {
                            z = true;
                            break;
                        }
                        if (!strArr[i3].equals(typeDescriptors[i3])) {
                            z = false;
                            break;
                        }
                        i3++;
                    }
                    if (z) {
                        return true;
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return false;
    }

    public byte[] generateClass() {
        int aSMModifiers = getASMModifiers(this.classModifiers) | 1;
        if (this.isInterface) {
            aSMModifiers |= 512;
        }
        int i2 = aSMModifiers;
        String[] strArr = new String[this.interfaces.length];
        int i3 = 0;
        while (true) {
            Class[] clsArr = this.interfaces;
            if (i3 >= clsArr.length) {
                break;
            }
            strArr[i3] = Type.getInternalName(clsArr[i3]);
            i3++;
        }
        ClassWriter classWriter = new ClassWriter(false);
        classWriter.visit(i2, this.fqClassName, this.superClassName, strArr, "BeanShell Generated via ASM (www.objectweb.org)");
        if (!this.isInterface) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(BSHTHIS);
            stringBuffer.append(this.className);
            generateField(stringBuffer.toString(), "Lbsh/This;", 1, classWriter);
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(BSHSTATIC);
            stringBuffer2.append(this.className);
            generateField(stringBuffer2.toString(), "Lbsh/This;", 9, classWriter);
        }
        int i4 = 0;
        while (true) {
            Variable[] variableArr = this.vars;
            if (i4 >= variableArr.length) {
                break;
            }
            String typeDescriptor = variableArr[i4].getTypeDescriptor();
            if (!this.vars[i4].hasModifier("private") && typeDescriptor != null) {
                generateField(this.vars[i4].getName(), typeDescriptor, this.isInterface ? 25 : getASMModifiers(this.vars[i4].getModifiers()), classWriter);
            }
            i4++;
        }
        int i5 = 0;
        boolean z = false;
        while (true) {
            DelayedEvalBshMethod[] delayedEvalBshMethodArr = this.constructors;
            if (i5 >= delayedEvalBshMethodArr.length) {
                break;
            }
            if (!delayedEvalBshMethodArr[i5].hasModifier("private")) {
                generateConstructor(i5, this.constructors[i5].getParamTypeDescriptors(), getASMModifiers(this.constructors[i5].getModifiers()), classWriter);
                z = true;
            }
            i5++;
        }
        if (!this.isInterface && !z) {
            generateConstructor(-1, new String[0], 1, classWriter);
        }
        int i6 = 0;
        while (true) {
            DelayedEvalBshMethod[] delayedEvalBshMethodArr2 = this.methods;
            if (i6 >= delayedEvalBshMethodArr2.length) {
                return classWriter.toByteArray();
            }
            String returnTypeDescriptor = delayedEvalBshMethodArr2[i6].getReturnTypeDescriptor();
            if (!this.methods[i6].hasModifier("private")) {
                int aSMModifiers2 = getASMModifiers(this.methods[i6].getModifiers());
                if (this.isInterface) {
                    aSMModifiers2 |= 1025;
                }
                generateMethod(this.className, this.fqClassName, this.methods[i6].getName(), returnTypeDescriptor, this.methods[i6].getParamTypeDescriptors(), aSMModifiers2, classWriter);
                boolean z2 = (aSMModifiers2 & 8) > 0;
                boolean classContainsMethod = classContainsMethod(this.superClass, this.methods[i6].getName(), this.methods[i6].getParamTypeDescriptors());
                if (!z2 && classContainsMethod) {
                    generateSuperDelegateMethod(this.superClassName, this.methods[i6].getName(), returnTypeDescriptor, this.methods[i6].getParamTypeDescriptors(), aSMModifiers2, classWriter);
                }
            }
            i6++;
        }
    }

    void generateConstructor(int i2, String[] strArr, int i3, ClassWriter classWriter) {
        int length = strArr.length + 1;
        int length2 = strArr.length + 2;
        CodeVisitor visitMethod = classWriter.visitMethod(i3, "<init>", getMethodDescriptor("V", strArr), null);
        generateParameterReifierCode(strArr, false, visitMethod);
        visitMethod.visitVarInsn(58, length);
        generateConstructorSwitch(i2, length, length2, visitMethod);
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitLdcInsn(this.className);
        visitMethod.visitVarInsn(25, length);
        visitMethod.visitMethodInsn(Constants.INVOKESTATIC, "bsh/ClassGeneratorUtil", "initInstance", "(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V");
        visitMethod.visitInsn(Constants.RETURN);
        visitMethod.visitMaxs(20, 20);
    }

    void generateConstructorSwitch(int i2, int i3, int i4, CodeVisitor codeVisitor) {
        Label label = new Label();
        Label label2 = new Label();
        int length = this.superConstructors.length + this.constructors.length;
        Label[] labelArr = new Label[length];
        for (int i5 = 0; i5 < length; i5++) {
            labelArr[i5] = new Label();
        }
        codeVisitor.visitLdcInsn(this.superClass.getName());
        String str = this.fqClassName;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(BSHSTATIC);
        stringBuffer.append(this.className);
        codeVisitor.visitFieldInsn(Constants.GETSTATIC, str, stringBuffer.toString(), "Lbsh/This;");
        codeVisitor.visitVarInsn(25, i3);
        codeVisitor.visitIntInsn(16, i2);
        codeVisitor.visitMethodInsn(Constants.INVOKESTATIC, "bsh/ClassGeneratorUtil", "getConstructorArgs", "(Ljava/lang/String;Lbsh/This;[Ljava/lang/Object;I)Lbsh/ClassGeneratorUtil$ConstructorArgs;");
        codeVisitor.visitVarInsn(58, i4);
        codeVisitor.visitVarInsn(25, i4);
        codeVisitor.visitFieldInsn(Constants.GETFIELD, "bsh/ClassGeneratorUtil$ConstructorArgs", "selector", "I");
        codeVisitor.visitTableSwitchInsn(0, length - 1, label, labelArr);
        int i6 = 0;
        int i7 = 0;
        while (true) {
            Constructor[] constructorArr = this.superConstructors;
            if (i6 >= constructorArr.length) {
                break;
            }
            doSwitchBranch(i7, this.superClassName, getTypeDescriptors(constructorArr[i6].getParameterTypes()), label2, labelArr, i4, codeVisitor);
            i6++;
            i7++;
        }
        int i8 = 0;
        while (true) {
            DelayedEvalBshMethod[] delayedEvalBshMethodArr = this.constructors;
            if (i8 >= delayedEvalBshMethodArr.length) {
                codeVisitor.visitLabel(label);
                codeVisitor.visitVarInsn(25, 0);
                codeVisitor.visitMethodInsn(Constants.INVOKESPECIAL, this.superClassName, "<init>", "()V");
                codeVisitor.visitLabel(label2);
                return;
            }
            doSwitchBranch(i7, this.fqClassName, delayedEvalBshMethodArr[i8].getParamTypeDescriptors(), label2, labelArr, i4, codeVisitor);
            i8++;
            i7++;
        }
    }
}
