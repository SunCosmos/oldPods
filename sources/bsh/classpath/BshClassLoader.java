package bsh.classpath;

import bsh.BshClassManager;
import java.net.URL;
import java.net.URLClassLoader;

/* loaded from: classes.dex */
public class BshClassLoader extends URLClassLoader {
    static /* synthetic */ Class class$bsh$Interpreter;
    BshClassManager classManager;

    /* JADX INFO: Access modifiers changed from: protected */
    public BshClassLoader(BshClassManager bshClassManager) {
        this(bshClassManager, new URL[0]);
    }

    public BshClassLoader(BshClassManager bshClassManager, BshClassPath bshClassPath) {
        this(bshClassManager, bshClassPath.getPathComponents());
    }

    public BshClassLoader(BshClassManager bshClassManager, URL[] urlArr) {
        super(urlArr);
        this.classManager = bshClassManager;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    @Override // java.net.URLClassLoader
    public void addURL(URL url) {
        super.addURL(url);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.net.URLClassLoader, java.lang.ClassLoader
    public Class findClass(String str) {
        ClassManagerImpl classManagerImpl = (ClassManagerImpl) getClassManager();
        ClassLoader loaderForClass = classManagerImpl.getLoaderForClass(str);
        if (loaderForClass != null && loaderForClass != this) {
            try {
                return loaderForClass.loadClass(str);
            } catch (ClassNotFoundException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Designated loader could not find class: ");
                stringBuffer.append(e);
                throw new ClassNotFoundException(stringBuffer.toString());
            }
        }
        if (getURLs().length > 0) {
            try {
                return super.findClass(str);
            } catch (ClassNotFoundException unused) {
            }
        }
        ClassLoader baseLoader = classManagerImpl.getBaseLoader();
        if (baseLoader != null && baseLoader != this) {
            try {
                return baseLoader.loadClass(str);
            } catch (ClassNotFoundException unused2) {
            }
        }
        return classManagerImpl.plainClassForName(str);
    }

    BshClassManager getClassManager() {
        return this.classManager;
    }

    @Override // java.lang.ClassLoader
    public Class loadClass(String str, boolean z) {
        Class findLoadedClass = findLoadedClass(str);
        if (findLoadedClass != null) {
            return findLoadedClass;
        }
        if (str.startsWith("bsh")) {
            try {
                Class cls = class$bsh$Interpreter;
                if (cls == null) {
                    cls = class$("bsh.Interpreter");
                    class$bsh$Interpreter = cls;
                }
                return cls.getClassLoader().loadClass(str);
            } catch (ClassNotFoundException unused) {
            }
        }
        try {
            findLoadedClass = findClass(str);
        } catch (ClassNotFoundException unused2) {
        }
        if (findLoadedClass == null) {
            throw new ClassNotFoundException("here in loaClass");
        }
        if (z) {
            resolveClass(findLoadedClass);
        }
        return findLoadedClass;
    }
}
