package bsh;

import java.util.Hashtable;

/* loaded from: classes.dex */
public class Capabilities {
    private static boolean accessibility = false;
    static /* synthetic */ Class class$java$lang$String;
    private static Hashtable classes = new Hashtable();

    /* loaded from: classes.dex */
    public static class Unavailable extends UtilEvalError {
        public Unavailable(String str) {
            super(str);
        }
    }

    public static boolean canGenerateInterfaces() {
        return classExists("java.lang.reflect.Proxy");
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static boolean classExists(String str) {
        Object obj = classes.get(str);
        if (obj == null) {
            try {
                obj = Class.forName(str);
            } catch (ClassNotFoundException unused) {
            }
            if (obj != null) {
                classes.put(obj, "unused");
            }
        }
        return obj != null;
    }

    public static boolean haveAccessibility() {
        return accessibility;
    }

    public static boolean haveSwing() {
        return classExists("javax.swing.JButton");
    }

    public static void setAccessibility(boolean z) {
        boolean z2;
        if (!z) {
            z2 = false;
        } else {
            if (!classExists("java.lang.reflect.AccessibleObject") || !classExists("bsh.reflect.ReflectManagerImpl")) {
                throw new Unavailable("Accessibility unavailable");
            }
            try {
                Class cls = class$java$lang$String;
                if (cls == null) {
                    cls = class$("java.lang.String");
                    class$java$lang$String = cls;
                }
                cls.getDeclaredMethods();
                z2 = true;
            } catch (SecurityException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Accessibility unavailable: ");
                stringBuffer.append(e);
                throw new Unavailable(stringBuffer.toString());
            }
        }
        accessibility = z2;
    }
}
