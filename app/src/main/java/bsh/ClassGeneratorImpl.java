package bsh;

import bsh.BSHBlock;
import bsh.Capabilities;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ClassGeneratorImpl extends ClassGenerator {

    /* loaded from: classes.dex */
    public static class ClassNodeFilter implements BSHBlock.NodeFilter {
        public static final int CLASSES = 2;
        public static final int INSTANCE = 1;
        public static final int STATIC = 0;
        int context;
        public static ClassNodeFilter CLASSSTATIC = new ClassNodeFilter(0);
        public static ClassNodeFilter CLASSINSTANCE = new ClassNodeFilter(1);
        public static ClassNodeFilter CLASSCLASSES = new ClassNodeFilter(2);

        private ClassNodeFilter(int i2) {
            this.context = i2;
        }

        boolean isStatic(SimpleNode simpleNode) {
            if (simpleNode instanceof BSHTypedVariableDeclaration) {
                Modifiers modifiers = ((BSHTypedVariableDeclaration) simpleNode).modifiers;
                return modifiers != null && modifiers.hasModifier("static");
            }
            if (simpleNode instanceof BSHMethodDeclaration) {
                Modifiers modifiers2 = ((BSHMethodDeclaration) simpleNode).modifiers;
                return modifiers2 != null && modifiers2.hasModifier("static");
            }
            boolean z = simpleNode instanceof BSHBlock;
            return false;
        }

        @Override // bsh.BSHBlock.NodeFilter
        public boolean isVisible(SimpleNode simpleNode) {
            int i2 = this.context;
            if (i2 == 2) {
                return simpleNode instanceof BSHClassDeclaration;
            }
            if (simpleNode instanceof BSHClassDeclaration) {
                return false;
            }
            if (i2 == 0) {
                return isStatic(simpleNode);
            }
            if (i2 == 1) {
                return !isStatic(simpleNode);
            }
            return true;
        }
    }

    public static Class generateClassImpl(String str, Modifiers modifiers, Class[] clsArr, Class cls, BSHBlock bSHBlock, boolean z, CallStack callStack, Interpreter interpreter) {
        String str2;
        String stringBuffer;
        try {
            Capabilities.setAccessibility(true);
            NameSpace pVar = callStack.top();
            String str3 = pVar.getPackage();
            if (pVar.isClass) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(pVar.getName());
                stringBuffer2.append("$");
                stringBuffer2.append(str);
                str2 = stringBuffer2.toString();
            } else {
                str2 = str;
            }
            if (str3 == null) {
                stringBuffer = str2;
            } else {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append(str3);
                stringBuffer3.append(".");
                stringBuffer3.append(str2);
                stringBuffer = stringBuffer3.toString();
            }
            BshClassManager classManager = interpreter.getClassManager();
            classManager.definingClass(stringBuffer);
            NameSpace nameSpace = new NameSpace(pVar, str2);
            nameSpace.isClass = true;
            callStack.push(nameSpace);
            bSHBlock.evalBlock(callStack, interpreter, true, ClassNodeFilter.CLASSCLASSES);
            String str4 = stringBuffer;
            byte[] generateClass = new ClassGeneratorUtil(modifiers, str2, str3, cls, clsArr, getDeclaredVariables(bSHBlock, callStack, interpreter, str3), getDeclaredMethods(bSHBlock, callStack, interpreter, str3), nameSpace, z).generateClass();
            String property = System.getProperty("debugClasses");
            if (property != null) {
                try {
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append(property);
                    stringBuffer4.append("/");
                    stringBuffer4.append(str2);
                    stringBuffer4.append(".class");
                    FileOutputStream fileOutputStream = new FileOutputStream(stringBuffer4.toString());
                    fileOutputStream.write(generateClass);
                    fileOutputStream.close();
                } catch (IOException unused) {
                }
            }
            Class defineClass = classManager.defineClass(str4, generateClass);
            pVar.importClass(str4.replace('$', '.'));
            try {
                nameSpace.setLocalVariable("_bshInstanceInitializer", bSHBlock, false);
                nameSpace.setClassStatic(defineClass);
                bSHBlock.evalBlock(callStack, interpreter, true, ClassNodeFilter.CLASSSTATIC);
                callStack.pop();
                if (!defineClass.isInterface()) {
                    StringBuffer stringBuffer5 = new StringBuffer();
                    stringBuffer5.append("_bshStatic");
                    stringBuffer5.append(str2);
                    try {
                        Reflect.getLHSStaticField(defineClass, stringBuffer5.toString()).assign(nameSpace.getThis(interpreter), false);
                    } catch (Exception e) {
                        StringBuffer stringBuffer6 = new StringBuffer();
                        stringBuffer6.append("Error in class gen setup: ");
                        stringBuffer6.append(e);
                        throw new InterpreterError(stringBuffer6.toString());
                    }
                }
                classManager.doneDefiningClass(str4);
                return defineClass;
            } catch (UtilEvalError e2) {
                StringBuffer stringBuffer7 = new StringBuffer();
                stringBuffer7.append("unable to init static: ");
                stringBuffer7.append(e2);
                throw new InterpreterError(stringBuffer7.toString());
            }
        } catch (Capabilities.Unavailable unused2) {
            throw new EvalError("Defining classes currently requires reflective Accessibility.", bSHBlock, callStack);
        }
    }

    static DelayedEvalBshMethod[] getDeclaredMethods(BSHBlock bSHBlock, CallStack callStack, Interpreter interpreter, String str) {
        int i2;
        CallStack callStack2 = callStack;
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 0;
        while (i4 < bSHBlock.jjtGetNumChildren()) {
            SimpleNode simpleNode = (SimpleNode) bSHBlock.jjtGetChild(i4);
            if (simpleNode instanceof BSHMethodDeclaration) {
                BSHMethodDeclaration bSHMethodDeclaration = (BSHMethodDeclaration) simpleNode;
                bSHMethodDeclaration.insureNodesParsed();
                Modifiers modifiers = bSHMethodDeclaration.modifiers;
                String str2 = bSHMethodDeclaration.name;
                String returnTypeDescriptor = bSHMethodDeclaration.getReturnTypeDescriptor(callStack2, interpreter, str);
                BSHReturnType returnTypeNode = bSHMethodDeclaration.getReturnTypeNode();
                BSHFormalParameters bSHFormalParameters = bSHMethodDeclaration.paramsNode;
                i2 = i4;
                arrayList.add(new DelayedEvalBshMethod(str2, returnTypeDescriptor, returnTypeNode, bSHMethodDeclaration.paramsNode.getParamNames(), bSHFormalParameters.getTypeDescriptors(callStack2, interpreter, str), bSHFormalParameters, bSHMethodDeclaration.blockNode, null, modifiers, callStack, interpreter));
            } else {
                i2 = i4;
            }
            i4 = i2 + 1;
            callStack2 = callStack;
            i3 = 0;
        }
        return (DelayedEvalBshMethod[]) arrayList.toArray(new DelayedEvalBshMethod[i3]);
    }

    static Variable[] getDeclaredVariables(BSHBlock bSHBlock, CallStack callStack, Interpreter interpreter, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < bSHBlock.jjtGetNumChildren(); i2++) {
            SimpleNode simpleNode = (SimpleNode) bSHBlock.jjtGetChild(i2);
            if (simpleNode instanceof BSHTypedVariableDeclaration) {
                BSHTypedVariableDeclaration bSHTypedVariableDeclaration = (BSHTypedVariableDeclaration) simpleNode;
                Modifiers modifiers = bSHTypedVariableDeclaration.modifiers;
                String typeDescriptor = bSHTypedVariableDeclaration.getTypeDescriptor(callStack, interpreter, str);
                for (BSHVariableDeclarator bSHVariableDeclarator : bSHTypedVariableDeclaration.getDeclarators()) {
                    try {
                        arrayList.add(new Variable(bSHVariableDeclarator.name, typeDescriptor, (Object) null, modifiers));
                    } catch (UtilEvalError unused) {
                    }
                }
            }
        }
        return (Variable[]) arrayList.toArray(new Variable[0]);
    }

    public static Object invokeSuperclassMethodImpl(BshClassManager bshClassManager, Object obj, String str, Object[] objArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("_bshSuper");
        stringBuffer.append(str);
        String stringBuffer2 = stringBuffer.toString();
        Class<?> cls = obj.getClass();
        Method resolveJavaMethod = Reflect.resolveJavaMethod(bshClassManager, cls, stringBuffer2, Types.getTypes(objArr), false);
        return resolveJavaMethod != null ? Reflect.invokeMethod(resolveJavaMethod, obj, objArr) : Reflect.invokeMethod(Reflect.resolveExpectedJavaMethod(bshClassManager, cls.getSuperclass(), obj, str, objArr, false), obj, objArr);
    }

    @Override // bsh.ClassGenerator
    public Class generateClass(String str, Modifiers modifiers, Class[] clsArr, Class cls, BSHBlock bSHBlock, boolean z, CallStack callStack, Interpreter interpreter) {
        return generateClassImpl(str, modifiers, clsArr, cls, bSHBlock, z, callStack, interpreter);
    }

    @Override // bsh.ClassGenerator
    public Object invokeSuperclassMethod(BshClassManager bshClassManager, Object obj, String str, Object[] objArr) {
        return invokeSuperclassMethodImpl(bshClassManager, obj, str, objArr);
    }

    @Override // bsh.ClassGenerator
    public void setInstanceNameSpaceParent(Object obj, String str, NameSpace nameSpace) {
        ClassGeneratorUtil.getClassInstanceThis(obj, str).getNameSpace().setParent(nameSpace);
    }
}
