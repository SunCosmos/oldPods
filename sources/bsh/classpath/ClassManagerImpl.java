package bsh.classpath;

import bsh.BshClassManager;
import bsh.ClassPathException;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.UtilEvalError;
import bsh.classpath.BshClassPath;
import bsh.classpath.DiscreteFilesClassLoader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/* loaded from: classes.dex */
public class ClassManagerImpl extends BshClassManager {
    static final String BSH_PACKAGE = "bsh";
    static /* synthetic */ Class class$bsh$Interpreter;
    private BshClassPath baseClassPath;
    private BshClassLoader baseLoader;
    private BshClassPath fullClassPath;
    private Map loaderMap;
    private boolean superImport;
    private Vector listeners = new Vector();
    private ReferenceQueue refQueue = new ReferenceQueue();

    public ClassManagerImpl() {
        reset();
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private void initBaseLoader() {
        this.baseLoader = new BshClassLoader(this, this.baseClassPath);
    }

    @Override // bsh.BshClassManager
    public void addClassPath(URL url) {
        BshClassLoader bshClassLoader = this.baseLoader;
        if (bshClassLoader == null) {
            setClassPath(new URL[]{url});
            return;
        }
        bshClassLoader.addURL(url);
        this.baseClassPath.add(url);
        classLoaderChanged();
    }

    @Override // bsh.BshClassManager
    public void addListener(BshClassManager.Listener listener) {
        this.listeners.addElement(new WeakReference(listener, this.refQueue));
        while (true) {
            Reference poll = this.refQueue.poll();
            if (poll == null) {
                return;
            }
            if (!this.listeners.removeElement(poll) && Interpreter.DEBUG) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("tried to remove non-existent weak ref: ");
                stringBuffer.append(poll);
                Interpreter.debug(stringBuffer.toString());
            }
        }
    }

    @Override // bsh.BshClassManager
    public Class classForName(String str) {
        ClassLoader classLoader;
        URLClassLoader uRLClassLoader;
        Class cls = (Class) this.absoluteClassCache.get(str);
        if (cls != null) {
            return cls;
        }
        if (this.absoluteNonClasses.get(str) != null) {
            if (!Interpreter.DEBUG) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("absoluteNonClass list hit: ");
            stringBuffer.append(str);
            Interpreter.debug(stringBuffer.toString());
            return null;
        }
        if (Interpreter.DEBUG) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Trying to load class: ");
            stringBuffer2.append(str);
            Interpreter.debug(stringBuffer2.toString());
        }
        ClassLoader loaderForClass = getLoaderForClass(str);
        if (loaderForClass != null) {
            try {
                cls = loaderForClass.loadClass(str);
            } catch (Exception unused) {
            } catch (NoClassDefFoundError e) {
                throw BshClassManager.noClassDefFound(str, e);
            }
        }
        if (cls == null && str.startsWith(BSH_PACKAGE)) {
            try {
                Class cls2 = class$bsh$Interpreter;
                if (cls2 == null) {
                    cls2 = class$("bsh.Interpreter");
                    class$bsh$Interpreter = cls2;
                }
                cls = cls2.getClassLoader().loadClass(str);
            } catch (ClassNotFoundException unused2) {
            }
        }
        if (cls == null && (uRLClassLoader = this.baseLoader) != null) {
            try {
                cls = uRLClassLoader.loadClass(str);
            } catch (ClassNotFoundException unused3) {
            }
        }
        if (cls == null && (classLoader = this.externalClassLoader) != null) {
            try {
                cls = classLoader.loadClass(str);
            } catch (ClassNotFoundException unused4) {
            }
        }
        if (cls == null) {
            try {
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                if (contextClassLoader != null) {
                    cls = Class.forName(str, true, contextClassLoader);
                }
            } catch (ClassNotFoundException | SecurityException unused5) {
            }
        }
        if (cls == null) {
            try {
                cls = plainClassForName(str);
            } catch (ClassNotFoundException unused6) {
            }
        }
        if (cls == null) {
            cls = loadSourceClass(str);
        }
        cacheClassInfo(str, cls);
        return cls;
    }

    @Override // bsh.BshClassManager
    protected void classLoaderChanged() {
        clearCaches();
        Vector vector = new Vector();
        Enumeration elements = this.listeners.elements();
        while (elements.hasMoreElements()) {
            WeakReference weakReference = (WeakReference) elements.nextElement();
            BshClassManager.Listener listener = (BshClassManager.Listener) weakReference.get();
            if (listener == null) {
                vector.add(weakReference);
            } else {
                listener.classLoaderChanged();
            }
        }
        Enumeration elements2 = vector.elements();
        while (elements2.hasMoreElements()) {
            this.listeners.removeElement(elements2.nextElement());
        }
    }

    @Override // bsh.BshClassManager
    public Class defineClass(String str, byte[] bArr) {
        this.baseClassPath.setClassSource(str, new BshClassPath.GeneratedClassSource(bArr));
        try {
            reloadClasses(new String[]{str});
            return classForName(str);
        } catch (ClassPathException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("defineClass: ");
            stringBuffer.append(e);
            throw new InterpreterError(stringBuffer.toString());
        }
    }

    @Override // bsh.BshClassManager
    public void doSuperImport() {
        try {
            getClassPath().insureInitialized();
            getClassNameByUnqName("");
            this.superImport = true;
        } catch (ClassPathException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Error importing classpath ");
            stringBuffer.append(e);
            throw new UtilEvalError(stringBuffer.toString());
        }
    }

    @Override // bsh.BshClassManager
    public void dump(PrintWriter printWriter) {
        printWriter.println("Bsh Class Manager Dump: ");
        printWriter.println("----------------------- ");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("baseLoader = ");
        stringBuffer.append(this.baseLoader);
        printWriter.println(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("loaderMap= ");
        stringBuffer2.append(this.loaderMap);
        printWriter.println(stringBuffer2.toString());
        printWriter.println("----------------------- ");
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("baseClassPath = ");
        stringBuffer3.append(this.baseClassPath);
        printWriter.println(stringBuffer3.toString());
    }

    public ClassLoader getBaseLoader() {
        return this.baseLoader;
    }

    @Override // bsh.BshClassManager
    public String getClassNameByUnqName(String str) {
        return getClassPath().getClassNameByUnqName(str);
    }

    public BshClassPath getClassPath() {
        BshClassPath bshClassPath = this.fullClassPath;
        if (bshClassPath != null) {
            return bshClassPath;
        }
        BshClassPath bshClassPath2 = new BshClassPath("BeanShell Full Class Path");
        this.fullClassPath = bshClassPath2;
        bshClassPath2.addComponent(BshClassPath.getUserClassPath());
        try {
            this.fullClassPath.addComponent(BshClassPath.getBootClassPath());
        } catch (ClassPathException unused) {
            System.err.println("Warning: can't get boot class path");
        }
        this.fullClassPath.addComponent(this.baseClassPath);
        return this.fullClassPath;
    }

    public ClassLoader getLoaderForClass(String str) {
        return (ClassLoader) this.loaderMap.get(str);
    }

    @Override // bsh.BshClassManager
    public URL getResource(String str) {
        BshClassLoader bshClassLoader = this.baseLoader;
        URL resource = bshClassLoader != null ? bshClassLoader.getResource(str.substring(1)) : null;
        return resource == null ? super.getResource(str) : resource;
    }

    @Override // bsh.BshClassManager
    public InputStream getResourceAsStream(String str) {
        BshClassLoader bshClassLoader = this.baseLoader;
        InputStream resourceAsStream = bshClassLoader != null ? bshClassLoader.getResourceAsStream(str.substring(1)) : null;
        return resourceAsStream == null ? super.getResourceAsStream(str) : resourceAsStream;
    }

    @Override // bsh.BshClassManager
    public boolean hasSuperImport() {
        return this.superImport;
    }

    @Override // bsh.BshClassManager
    public void reloadAllClasses() {
        BshClassPath bshClassPath = new BshClassPath("temp");
        bshClassPath.addComponent(this.baseClassPath);
        bshClassPath.addComponent(BshClassPath.getUserClassPath());
        setClassPath(bshClassPath.getPathComponents());
    }

    @Override // bsh.BshClassManager
    public void reloadClasses(String[] strArr) {
        if (this.baseLoader == null) {
            initBaseLoader();
        }
        DiscreteFilesClassLoader.ClassSourceMap classSourceMap = new DiscreteFilesClassLoader.ClassSourceMap();
        for (String str : strArr) {
            BshClassPath.ClassSource classSource = this.baseClassPath.getClassSource(str);
            if (classSource == null) {
                BshClassPath.getUserClassPath().insureInitialized();
                classSource = BshClassPath.getUserClassPath().getClassSource(str);
            }
            if (classSource == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Nothing known about class: ");
                stringBuffer.append(str);
                throw new ClassPathException(stringBuffer.toString());
            }
            if (classSource instanceof BshClassPath.JarClassSource) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Cannot reload class: ");
                stringBuffer2.append(str);
                stringBuffer2.append(" from source: ");
                stringBuffer2.append(classSource);
                throw new ClassPathException(stringBuffer2.toString());
            }
            classSourceMap.put(str, classSource);
        }
        DiscreteFilesClassLoader discreteFilesClassLoader = new DiscreteFilesClassLoader(this, classSourceMap);
        Iterator it = classSourceMap.keySet().iterator();
        while (it.hasNext()) {
            this.loaderMap.put((String) it.next(), discreteFilesClassLoader);
        }
        classLoaderChanged();
    }

    @Override // bsh.BshClassManager
    public void reloadPackage(String str) {
        Set classesForPackage = this.baseClassPath.getClassesForPackage(str);
        if (classesForPackage == null) {
            classesForPackage = BshClassPath.getUserClassPath().getClassesForPackage(str);
        }
        if (classesForPackage != null) {
            reloadClasses((String[]) classesForPackage.toArray(new String[0]));
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("No classes found for package: ");
        stringBuffer.append(str);
        throw new ClassPathException(stringBuffer.toString());
    }

    @Override // bsh.BshClassManager
    public void removeListener(BshClassManager.Listener listener) {
        throw new Error("unimplemented");
    }

    @Override // bsh.BshClassManager
    public void reset() {
        this.baseClassPath = new BshClassPath("baseClassPath");
        this.baseLoader = null;
        this.loaderMap = new HashMap();
        classLoaderChanged();
    }

    @Override // bsh.BshClassManager
    public void setClassPath(URL[] urlArr) {
        this.baseClassPath.setPath(urlArr);
        initBaseLoader();
        this.loaderMap = new HashMap();
        classLoaderChanged();
    }
}
