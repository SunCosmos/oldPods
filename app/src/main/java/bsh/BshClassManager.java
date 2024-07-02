package bsh;

import bsh.Capabilities;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class BshClassManager {
    private static Object NOVALUE = new Object();
    static /* synthetic */ Class class$bsh$Interpreter;
    private Interpreter declaringInterpreter;
    protected ClassLoader externalClassLoader;
    protected transient Hashtable absoluteClassCache = new Hashtable();
    protected transient Hashtable absoluteNonClasses = new Hashtable();
    protected transient Hashtable resolvedObjectMethods = new Hashtable();
    protected transient Hashtable resolvedStaticMethods = new Hashtable();
    protected transient Hashtable definingClasses = new Hashtable();
    protected transient Hashtable definingClassesBaseNames = new Hashtable();

    /* loaded from: classes.dex */
    public interface Listener {
        void classLoaderChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SignatureKey {
        Class clas;
        int hashCode = 0;
        String methodName;
        Class[] types;

        SignatureKey(Class cls, String str, Class[] clsArr) {
            this.clas = cls;
            this.methodName = str;
            this.types = clsArr;
        }

        public boolean equals(Object obj) {
            SignatureKey signatureKey = (SignatureKey) obj;
            if (this.types == null) {
                return signatureKey.types == null;
            }
            if (this.clas != signatureKey.clas || !this.methodName.equals(signatureKey.methodName) || this.types.length != signatureKey.types.length) {
                return false;
            }
            int i2 = 0;
            while (true) {
                Class[] clsArr = this.types;
                if (i2 >= clsArr.length) {
                    return true;
                }
                if (clsArr[i2] == null) {
                    if (signatureKey.types[i2] != null) {
                        return false;
                    }
                } else if (!clsArr[i2].equals(signatureKey.types[i2])) {
                    return false;
                }
                i2++;
            }
        }

        public int hashCode() {
            if (this.hashCode == 0) {
                int hashCode = this.clas.hashCode() * this.methodName.hashCode();
                this.hashCode = hashCode;
                if (this.types == null) {
                    return hashCode;
                }
                int i2 = 0;
                while (true) {
                    Class[] clsArr = this.types;
                    if (i2 >= clsArr.length) {
                        break;
                    }
                    int hashCode2 = clsArr[i2] == null ? 21 : clsArr[i2].hashCode();
                    i2++;
                    this.hashCode = (this.hashCode * i2) + hashCode2;
                }
            }
            return this.hashCode;
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    protected static UtilEvalError cmUnavailable() {
        return new Capabilities.Unavailable("ClassLoading features unavailable.");
    }

    public static BshClassManager createClassManager(Interpreter interpreter) {
        BshClassManager bshClassManager;
        if (Capabilities.classExists("java.lang.ref.WeakReference") && Capabilities.classExists("java.util.HashMap") && Capabilities.classExists("bsh.classpath.ClassManagerImpl")) {
            try {
                bshClassManager = (BshClassManager) Class.forName("bsh.classpath.ClassManagerImpl").newInstance();
            } catch (Exception e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Error loading classmanager: ");
                stringBuffer.append(e);
                throw new InterpreterError(stringBuffer.toString());
            }
        } else {
            bshClassManager = new BshClassManager();
        }
        if (interpreter == null) {
            interpreter = new Interpreter();
        }
        bshClassManager.declaringInterpreter = interpreter;
        return bshClassManager;
    }

    public static Error noClassDefFound(String str, Error error) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("A class required by class: ");
        stringBuffer.append(str);
        stringBuffer.append(" could not be loaded:\n");
        stringBuffer.append(error.toString());
        return new NoClassDefFoundError(stringBuffer.toString());
    }

    public void addClassPath(URL url) {
    }

    public void addListener(Listener listener) {
    }

    public void cacheClassInfo(String str, Class cls) {
        if (cls != null) {
            this.absoluteClassCache.put(str, cls);
        } else {
            this.absoluteNonClasses.put(str, NOVALUE);
        }
    }

    public void cacheResolvedMethod(Class cls, Class[] clsArr, Method method) {
        if (Interpreter.DEBUG) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("cacheResolvedMethod putting: ");
            stringBuffer.append(cls);
            stringBuffer.append(" ");
            stringBuffer.append(method);
            Interpreter.debug(stringBuffer.toString());
        }
        (Modifier.isStatic(method.getModifiers()) ? this.resolvedStaticMethods : this.resolvedObjectMethods).put(new SignatureKey(cls, method.getName(), clsArr), method);
    }

    public boolean classExists(String str) {
        return classForName(str) != null;
    }

    public Class classForName(String str) {
        if (!isClassBeingDefined(str)) {
            Class cls = null;
            try {
                cls = plainClassForName(str);
            } catch (ClassNotFoundException unused) {
            }
            return cls == null ? loadSourceClass(str) : cls;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Attempting to load class in the process of being defined: ");
        stringBuffer.append(str);
        throw new InterpreterError(stringBuffer.toString());
    }

    protected void classLoaderChanged() {
    }

    public void clearCaches() {
        this.absoluteNonClasses = new Hashtable();
        this.absoluteClassCache = new Hashtable();
        this.resolvedObjectMethods = new Hashtable();
        this.resolvedStaticMethods = new Hashtable();
    }

    public Class defineClass(String str, byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Can't create class (");
        stringBuffer.append(str);
        stringBuffer.append(") without class manager package.");
        throw new InterpreterError(stringBuffer.toString());
    }

    public void definingClass(String str) {
        String suffix = Name.suffix(str, 1);
        int indexOf = suffix.indexOf("$");
        if (indexOf != -1) {
            suffix = suffix.substring(indexOf + 1);
        }
        String str2 = (String) this.definingClassesBaseNames.get(suffix);
        if (str2 == null) {
            this.definingClasses.put(str, NOVALUE);
            this.definingClassesBaseNames.put(suffix, str);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Defining class problem: ");
        stringBuffer.append(str);
        stringBuffer.append(": BeanShell cannot yet simultaneously define two or more ");
        stringBuffer.append("dependant classes of the same name.  Attempt to define: ");
        stringBuffer.append(str);
        stringBuffer.append(" while defining: ");
        stringBuffer.append(str2);
        throw new InterpreterError(stringBuffer.toString());
    }

    public void doSuperImport() {
        throw cmUnavailable();
    }

    public void doneDefiningClass(String str) {
        String suffix = Name.suffix(str, 1);
        this.definingClasses.remove(str);
        this.definingClassesBaseNames.remove(suffix);
    }

    public void dump(PrintWriter printWriter) {
        printWriter.println("BshClassManager: no class manager.");
    }

    public String getClassBeingDefined(String str) {
        return (String) this.definingClassesBaseNames.get(Name.suffix(str, 1));
    }

    public String getClassNameByUnqName(String str) {
        throw cmUnavailable();
    }

    public Method getResolvedMethod(Class cls, String str, Class[] clsArr, boolean z) {
        String stringBuffer;
        SignatureKey signatureKey = new SignatureKey(cls, str, clsArr);
        Method method = (Method) this.resolvedStaticMethods.get(signatureKey);
        if (method == null && !z) {
            method = (Method) this.resolvedObjectMethods.get(signatureKey);
        }
        if (Interpreter.DEBUG) {
            if (method == null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("getResolvedMethod cache MISS: ");
                stringBuffer2.append(cls);
                stringBuffer2.append(" - ");
                stringBuffer2.append(str);
                stringBuffer = stringBuffer2.toString();
            } else {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("getResolvedMethod cache HIT: ");
                stringBuffer3.append(cls);
                stringBuffer3.append(" - ");
                stringBuffer3.append(method);
                stringBuffer = stringBuffer3.toString();
            }
            Interpreter.debug(stringBuffer);
        }
        return method;
    }

    public URL getResource(String str) {
        ClassLoader classLoader = this.externalClassLoader;
        URL resource = classLoader != null ? classLoader.getResource(str.substring(1)) : null;
        if (resource != null) {
            return resource;
        }
        Class cls = class$bsh$Interpreter;
        if (cls == null) {
            cls = class$("bsh.Interpreter");
            class$bsh$Interpreter = cls;
        }
        return cls.getResource(str);
    }

    public InputStream getResourceAsStream(String str) {
        ClassLoader classLoader = this.externalClassLoader;
        InputStream resourceAsStream = classLoader != null ? classLoader.getResourceAsStream(str.substring(1)) : null;
        if (resourceAsStream != null) {
            return resourceAsStream;
        }
        Class cls = class$bsh$Interpreter;
        if (cls == null) {
            cls = class$("bsh.Interpreter");
            class$bsh$Interpreter = cls;
        }
        return cls.getResourceAsStream(str);
    }

    public boolean hasSuperImport() {
        return false;
    }

    protected boolean isClassBeingDefined(String str) {
        return this.definingClasses.get(str) != null;
    }

    public Class loadSourceClass(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("/");
        stringBuffer.append(str.replace('.', '/'));
        stringBuffer.append(".java");
        String stringBuffer2 = stringBuffer.toString();
        InputStream resourceAsStream = getResourceAsStream(stringBuffer2);
        if (resourceAsStream == null) {
            return null;
        }
        try {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Loading class from source file: ");
            stringBuffer3.append(stringBuffer2);
            printStream.println(stringBuffer3.toString());
            this.declaringInterpreter.eval(new InputStreamReader(resourceAsStream));
        } catch (EvalError e) {
            System.err.println(e);
        }
        try {
            return plainClassForName(str);
        } catch (ClassNotFoundException unused) {
            PrintStream printStream2 = System.err;
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("Class not found in source file: ");
            stringBuffer4.append(str);
            printStream2.println(stringBuffer4.toString());
            return null;
        }
    }

    public Class plainClassForName(String str) {
        try {
            ClassLoader classLoader = this.externalClassLoader;
            Class<?> loadClass = classLoader != null ? classLoader.loadClass(str) : Class.forName(str);
            cacheClassInfo(str, loadClass);
            return loadClass;
        } catch (NoClassDefFoundError e) {
            throw noClassDefFound(str, e);
        }
    }

    public void reloadAllClasses() {
        throw cmUnavailable();
    }

    public void reloadClasses(String[] strArr) {
        throw cmUnavailable();
    }

    public void reloadPackage(String str) {
        throw cmUnavailable();
    }

    public void removeListener(Listener listener) {
    }

    public void reset() {
        clearCaches();
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.externalClassLoader = classLoader;
        classLoaderChanged();
    }

    public void setClassPath(URL[] urlArr) {
        throw cmUnavailable();
    }
}
