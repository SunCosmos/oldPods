package bsh;

import bsh.Capabilities;

/* loaded from: classes.dex */
public abstract class ClassGenerator {
    private static ClassGenerator cg;

    public static ClassGenerator getClassGenerator() {
        if (cg == null) {
            try {
                cg = (ClassGenerator) Class.forName("bsh.ClassGeneratorImpl").newInstance();
            } catch (Exception e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("ClassGenerator unavailable: ");
                stringBuffer.append(e);
                throw new Capabilities.Unavailable(stringBuffer.toString());
            }
        }
        return cg;
    }

    public abstract Class generateClass(String str, Modifiers modifiers, Class[] clsArr, Class cls, BSHBlock bSHBlock, boolean z, CallStack callStack, Interpreter interpreter);

    public abstract Object invokeSuperclassMethod(BshClassManager bshClassManager, Object obj, String str, Object[] objArr);

    public abstract void setInstanceNameSpaceParent(Object obj, String str, NameSpace nameSpace);
}
