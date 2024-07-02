package bsh;

import bsh.Capabilities;

/* loaded from: classes.dex */
public abstract class ReflectManager {
    private static ReflectManager rfm;

    public static boolean RMSetAccessible(Object obj) {
        return getReflectManager().setAccessible(obj);
    }

    public static ReflectManager getReflectManager() {
        if (rfm == null) {
            try {
                rfm = (ReflectManager) Class.forName("bsh.reflect.ReflectManagerImpl").newInstance();
            } catch (Exception e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Reflect Manager unavailable: ");
                stringBuffer.append(e);
                throw new Capabilities.Unavailable(stringBuffer.toString());
            }
        }
        return rfm;
    }

    public abstract boolean setAccessible(Object obj);
}
