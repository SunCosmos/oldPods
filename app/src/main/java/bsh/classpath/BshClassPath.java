package bsh.classpath;

import bsh.ClassPathException;
import bsh.NameSource;
import bsh.StringUtil;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes.dex */
public class BshClassPath implements ClassPathListener, NameSource {
    static BshClassPath bootClassPath;
    static /* synthetic */ Class class$java$lang$Class;
    static MappingFeedback mappingFeedbackListener;
    static BshClassPath userClassPath;
    static URL[] userClassPathComp;
    private Map classSource;
    private List compPaths;
    Vector listeners;
    private boolean mapsInitialized;
    String name;
    private boolean nameCompletionIncludesUnqNames;
    List nameSourceListeners;
    private Map packageMap;
    private List path;
    private UnqualifiedNameTable unqNameTable;

    /* loaded from: classes.dex */
    public static class AmbiguousName {
        List list = new ArrayList();

        public void add(String str) {
            this.list.add(str);
        }

        public List get() {
            return this.list;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class ClassSource {
        Object source;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract byte[] getCode(String str);
    }

    /* loaded from: classes.dex */
    public static class DirClassSource extends ClassSource {
        DirClassSource(File file) {
            this.source = file;
        }

        public static byte[] readBytesFromFile(File file, String str) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str.replace('.', File.separatorChar));
            stringBuffer.append(".class");
            File file2 = new File(file, stringBuffer.toString());
            if (!file2.exists()) {
                return null;
            }
            try {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file2));
                byte[] bArr = new byte[(int) file2.length()];
                dataInputStream.readFully(bArr);
                dataInputStream.close();
                return bArr;
            } catch (IOException unused) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Couldn't load file: ");
                stringBuffer2.append(file2);
                throw new RuntimeException(stringBuffer2.toString());
            }
        }

        @Override // bsh.classpath.BshClassPath.ClassSource
        public byte[] getCode(String str) {
            return readBytesFromFile(getDir(), str);
        }

        public File getDir() {
            return (File) this.source;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Dir: ");
            stringBuffer.append(this.source);
            return stringBuffer.toString();
        }
    }

    /* loaded from: classes.dex */
    public static class GeneratedClassSource extends ClassSource {
        /* JADX INFO: Access modifiers changed from: package-private */
        public GeneratedClassSource(byte[] bArr) {
            this.source = bArr;
        }

        @Override // bsh.classpath.BshClassPath.ClassSource
        public byte[] getCode(String str) {
            return (byte[]) this.source;
        }
    }

    /* loaded from: classes.dex */
    public static class JarClassSource extends ClassSource {
        JarClassSource(URL url) {
            this.source = url;
        }

        @Override // bsh.classpath.BshClassPath.ClassSource
        public byte[] getCode(String str) {
            throw new Error("Unimplemented");
        }

        public URL getURL() {
            return (URL) this.source;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Jar: ");
            stringBuffer.append(this.source);
            return stringBuffer.toString();
        }
    }

    /* loaded from: classes.dex */
    public interface MappingFeedback {
        void classMapping(String str);

        void endClassMapping();

        void errorWhileMapping(String str);

        void startClassMapping();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class UnqualifiedNameTable extends HashMap {
        UnqualifiedNameTable() {
        }

        void add(String str) {
            String str2 = BshClassPath.splitClassname(str)[1];
            Object obj = super.get(str2);
            if (obj == null) {
                super.put(str2, str);
                return;
            }
            if (obj instanceof AmbiguousName) {
                ((AmbiguousName) obj).add(str);
                return;
            }
            AmbiguousName ambiguousName = new AmbiguousName();
            ambiguousName.add((String) obj);
            ambiguousName.add(str);
            super.put(str2, ambiguousName);
        }
    }

    public BshClassPath(String str) {
        this.nameCompletionIncludesUnqNames = true;
        this.listeners = new Vector();
        this.name = str;
        reset();
    }

    public BshClassPath(String str, URL[] urlArr) {
        this(str);
        add(urlArr);
    }

    public static void addMappingFeedback(MappingFeedback mappingFeedback) {
        if (mappingFeedbackListener != null) {
            throw new RuntimeException("Unimplemented: already a listener");
        }
        mappingFeedbackListener = mappingFeedback;
    }

    private UnqualifiedNameTable buildUnqualifiedNameTable() {
        UnqualifiedNameTable unqualifiedNameTable = new UnqualifiedNameTable();
        if (this.compPaths != null) {
            for (int i2 = 0; i2 < this.compPaths.size(); i2++) {
                Iterator it = ((BshClassPath) this.compPaths.get(i2)).classSource.keySet().iterator();
                while (it.hasNext()) {
                    unqualifiedNameTable.add((String) it.next());
                }
            }
        }
        Iterator it2 = this.classSource.keySet().iterator();
        while (it2.hasNext()) {
            unqualifiedNameTable.add((String) it2.next());
        }
        return unqualifiedNameTable;
    }

    public static String canonicalizeClassName(String str) {
        String replace = str.replace('/', '.').replace('\\', '.');
        if (replace.startsWith("class ")) {
            replace = replace.substring(6);
        }
        return replace.endsWith(".class") ? replace.substring(0, replace.length() - 6) : replace;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private synchronized void clearCachedStructures() {
        this.mapsInitialized = false;
        this.packageMap = new HashMap();
        this.classSource = new HashMap();
        this.unqNameTable = null;
        nameSpaceChanged();
    }

    public static BshClassPath getBootClassPath() {
        if (bootClassPath == null) {
            try {
                bootClassPath = new BshClassPath("Boot Class Path", new URL[]{new File(getRTJarPath()).toURL()});
            } catch (MalformedURLException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(" can't find boot jar: ");
                stringBuffer.append(e);
                throw new ClassPathException(stringBuffer.toString());
            }
        }
        return bootClassPath;
    }

    private static String getRTJarPath() {
        int indexOf;
        Class cls = class$java$lang$Class;
        if (cls == null) {
            cls = class$("java.lang.Class");
            class$java$lang$Class = cls;
        }
        String externalForm = cls.getResource("/java/lang/String.class").toExternalForm();
        if (externalForm.startsWith("jar:file:") && (indexOf = externalForm.indexOf("!")) != -1) {
            return externalForm.substring(9, indexOf);
        }
        return null;
    }

    private UnqualifiedNameTable getUnqualifiedNameTable() {
        if (this.unqNameTable == null) {
            this.unqNameTable = buildUnqualifiedNameTable();
        }
        return this.unqNameTable;
    }

    public static BshClassPath getUserClassPath() {
        if (userClassPath == null) {
            userClassPath = new BshClassPath("User Class Path", getUserClassPathComponents());
        }
        return userClassPath;
    }

    public static URL[] getUserClassPathComponents() {
        URL[] urlArr = userClassPathComp;
        if (urlArr != null) {
            return urlArr;
        }
        String[] split = StringUtil.split(System.getProperty("java.class.path"), File.pathSeparator);
        URL[] urlArr2 = new URL[split.length];
        for (int i2 = 0; i2 < split.length; i2++) {
            try {
                urlArr2[i2] = new File(new File(split[i2]).getCanonicalPath()).toURL();
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("can't parse class path: ");
                stringBuffer.append(e);
                throw new ClassPathException(stringBuffer.toString());
            }
        }
        userClassPathComp = urlArr2;
        return urlArr2;
    }

    public static boolean isArchiveFileName(String str) {
        String lowerCase = str.toLowerCase();
        return lowerCase.endsWith(".jar") || lowerCase.endsWith(".zip");
    }

    public static boolean isClassFileName(String str) {
        return str.toLowerCase().endsWith(".class");
    }

    public static void main(String[] strArr) {
        URL[] urlArr = new URL[strArr.length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            urlArr[i2] = new File(strArr[i2]).toURL();
        }
        new BshClassPath("Test", urlArr);
    }

    private void map(String[] strArr, Object obj) {
        for (String str : strArr) {
            mapClass(str, obj);
        }
    }

    private void mapClass(String str, Object obj) {
        String[] splitClassname = splitClassname(str);
        String str2 = splitClassname[0];
        String str3 = splitClassname[1];
        Set set = (Set) this.packageMap.get(str2);
        if (set == null) {
            set = new HashSet();
            this.packageMap.put(str2, set);
        }
        set.add(str);
        if (this.classSource.get(str) == null) {
            this.classSource.put(str, obj);
        }
    }

    public static Collection removeInnerClassNames(Collection collection) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(collection);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (((String) it.next()).indexOf("$") != -1) {
                it.remove();
            }
        }
        return arrayList;
    }

    private synchronized void reset() {
        this.path = new ArrayList();
        this.compPaths = null;
        clearCachedStructures();
    }

    static String[] searchJarForClasses(URL url) {
        Vector vector = new Vector();
        ZipInputStream zipInputStream = new ZipInputStream(url.openStream());
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                zipInputStream.close();
                String[] strArr = new String[vector.size()];
                vector.copyInto(strArr);
                return strArr;
            }
            String name = nextEntry.getName();
            if (isClassFileName(name)) {
                vector.addElement(canonicalizeClassName(name));
            }
        }
    }

    public static String[] splitClassname(String str) {
        String str2;
        String canonicalizeClassName = canonicalizeClassName(str);
        int lastIndexOf = canonicalizeClassName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            str2 = "<unpackaged>";
        } else {
            String substring = canonicalizeClassName.substring(0, lastIndexOf);
            canonicalizeClassName = canonicalizeClassName.substring(lastIndexOf + 1);
            str2 = substring;
        }
        return new String[]{str2, canonicalizeClassName};
    }

    static String[] traverseDirForClasses(File file) {
        return (String[]) traverseDirForClassesAux(file, file).toArray(new String[0]);
    }

    static List traverseDirForClassesAux(File file, File file2) {
        ArrayList arrayList = new ArrayList();
        String absolutePath = file.getAbsolutePath();
        for (File file3 : file2.listFiles()) {
            if (file3.isDirectory()) {
                arrayList.addAll(traverseDirForClassesAux(file, file3));
            } else {
                String absolutePath2 = file3.getAbsolutePath();
                if (!isClassFileName(absolutePath2)) {
                    continue;
                } else {
                    if (!absolutePath2.startsWith(absolutePath)) {
                        throw new IOException("problem parsing paths");
                    }
                    arrayList.add(canonicalizeClassName(absolutePath2.substring(absolutePath.length() + 1)));
                }
            }
        }
        return arrayList;
    }

    public void add(URL url) {
        this.path.add(url);
        if (this.mapsInitialized) {
            map(url);
        }
    }

    public void add(URL[] urlArr) {
        this.path.addAll(Arrays.asList(urlArr));
        if (this.mapsInitialized) {
            map(urlArr);
        }
    }

    public void addComponent(BshClassPath bshClassPath) {
        if (this.compPaths == null) {
            this.compPaths = new ArrayList();
        }
        this.compPaths.add(bshClassPath);
        bshClassPath.addListener(this);
    }

    public void addListener(ClassPathListener classPathListener) {
        this.listeners.addElement(new WeakReference(classPathListener));
    }

    @Override // bsh.NameSource
    public void addNameSourceListener(NameSource.Listener listener) {
        if (this.nameSourceListeners == null) {
            this.nameSourceListeners = new ArrayList();
        }
        this.nameSourceListeners.add(listener);
    }

    void classMapping(String str) {
        MappingFeedback mappingFeedback = mappingFeedbackListener;
        if (mappingFeedback != null) {
            mappingFeedback.classMapping(str);
            return;
        }
        PrintStream printStream = System.err;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Mapping: ");
        stringBuffer.append(str);
        printStream.println(stringBuffer.toString());
    }

    @Override // bsh.classpath.ClassPathListener
    public void classPathChanged() {
        clearCachedStructures();
        notifyListeners();
    }

    void endClassMapping() {
        MappingFeedback mappingFeedback = mappingFeedbackListener;
        if (mappingFeedback != null) {
            mappingFeedback.endClassMapping();
        } else {
            System.err.println("End ClassPath Mapping");
        }
    }

    void errorWhileMapping(String str) {
        MappingFeedback mappingFeedback = mappingFeedbackListener;
        if (mappingFeedback != null) {
            mappingFeedback.errorWhileMapping(str);
        } else {
            System.err.println(str);
        }
    }

    @Override // bsh.NameSource
    public String[] getAllNames() {
        insureInitialized();
        ArrayList arrayList = new ArrayList();
        Iterator it = getPackagesSet().iterator();
        while (it.hasNext()) {
            arrayList.addAll(removeInnerClassNames(getClassesForPackage((String) it.next())));
        }
        if (this.nameCompletionIncludesUnqNames) {
            arrayList.addAll(getUnqualifiedNameTable().keySet());
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public String getClassNameByUnqName(String str) {
        insureInitialized();
        Object obj = getUnqualifiedNameTable().get(str);
        if (!(obj instanceof AmbiguousName)) {
            return (String) obj;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Ambigous class names: ");
        stringBuffer.append(((AmbiguousName) obj).get());
        throw new ClassPathException(stringBuffer.toString());
    }

    public synchronized ClassSource getClassSource(String str) {
        ClassSource classSource = (ClassSource) this.classSource.get(str);
        if (classSource != null) {
            return classSource;
        }
        insureInitialized();
        ClassSource classSource2 = (ClassSource) this.classSource.get(str);
        if (classSource2 == null && this.compPaths != null) {
            for (int i2 = 0; i2 < this.compPaths.size() && classSource2 == null; i2++) {
                classSource2 = ((BshClassPath) this.compPaths.get(i2)).getClassSource(str);
            }
        }
        return classSource2;
    }

    public synchronized Set getClassesForPackage(String str) {
        HashSet hashSet;
        insureInitialized();
        hashSet = new HashSet();
        Collection collection = (Collection) this.packageMap.get(str);
        if (collection != null) {
            hashSet.addAll(collection);
        }
        if (this.compPaths != null) {
            for (int i2 = 0; i2 < this.compPaths.size(); i2++) {
                Set classesForPackage = ((BshClassPath) this.compPaths.get(i2)).getClassesForPackage(str);
                if (classesForPackage != null) {
                    hashSet.addAll(classesForPackage);
                }
            }
        }
        return hashSet;
    }

    protected List getFullPath() {
        ArrayList arrayList = new ArrayList();
        if (this.compPaths != null) {
            for (int i2 = 0; i2 < this.compPaths.size(); i2++) {
                for (Object obj : ((BshClassPath) this.compPaths.get(i2)).getFullPath()) {
                    if (!arrayList.contains(obj)) {
                        arrayList.add(obj);
                    }
                }
            }
        }
        arrayList.addAll(this.path);
        return arrayList;
    }

    public Set getPackagesSet() {
        insureInitialized();
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.packageMap.keySet());
        if (this.compPaths != null) {
            for (int i2 = 0; i2 < this.compPaths.size(); i2++) {
                hashSet.addAll(((BshClassPath) this.compPaths.get(i2)).packageMap.keySet());
            }
        }
        return hashSet;
    }

    public URL[] getPathComponents() {
        return (URL[]) getFullPath().toArray(new URL[0]);
    }

    public void insureInitialized() {
        insureInitialized(true);
    }

    protected synchronized void insureInitialized(boolean z) {
        if (z) {
            if (!this.mapsInitialized) {
                startClassMapping();
            }
        }
        if (this.compPaths != null) {
            for (int i2 = 0; i2 < this.compPaths.size(); i2++) {
                ((BshClassPath) this.compPaths.get(i2)).insureInitialized(false);
            }
        }
        if (!this.mapsInitialized) {
            map((URL[]) this.path.toArray(new URL[0]));
        }
        if (z && !this.mapsInitialized) {
            endClassMapping();
        }
        this.mapsInitialized = true;
    }

    synchronized void map(URL url) {
        String file = url.getFile();
        File file2 = new File(file);
        if (file2.isDirectory()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Directory ");
            stringBuffer.append(file2.toString());
            classMapping(stringBuffer.toString());
            map(traverseDirForClasses(file2), new DirClassSource(file2));
        } else if (isArchiveFileName(file)) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Archive: ");
            stringBuffer2.append(url);
            classMapping(stringBuffer2.toString());
            map(searchJarForClasses(url), new JarClassSource(url));
        } else {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Not a classpath component: ");
            stringBuffer3.append(file);
            errorWhileMapping(stringBuffer3.toString());
        }
    }

    synchronized void map(URL[] urlArr) {
        for (int i2 = 0; i2 < urlArr.length; i2++) {
            try {
                map(urlArr[i2]);
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Error constructing classpath: ");
                stringBuffer.append(urlArr[i2]);
                stringBuffer.append(": ");
                stringBuffer.append(e);
                errorWhileMapping(stringBuffer.toString());
            }
        }
    }

    void nameSpaceChanged() {
        if (this.nameSourceListeners == null) {
            return;
        }
        for (int i2 = 0; i2 < this.nameSourceListeners.size(); i2++) {
            ((NameSource.Listener) this.nameSourceListeners.get(i2)).nameSourceChanged(this);
        }
    }

    void notifyListeners() {
        Enumeration elements = this.listeners.elements();
        while (elements.hasMoreElements()) {
            WeakReference weakReference = (WeakReference) elements.nextElement();
            ClassPathListener classPathListener = (ClassPathListener) weakReference.get();
            if (classPathListener == null) {
                this.listeners.removeElement(weakReference);
            } else {
                classPathListener.classPathChanged();
            }
        }
    }

    public void removeListener(ClassPathListener classPathListener) {
        this.listeners.removeElement(classPathListener);
    }

    public synchronized void setClassSource(String str, ClassSource classSource) {
        this.classSource.put(str, classSource);
    }

    public void setPath(URL[] urlArr) {
        reset();
        add(urlArr);
    }

    void startClassMapping() {
        MappingFeedback mappingFeedback = mappingFeedbackListener;
        if (mappingFeedback != null) {
            mappingFeedback.startClassMapping();
        } else {
            System.err.println("Start ClassPath Mapping");
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("BshClassPath ");
        stringBuffer.append(this.name);
        stringBuffer.append("(");
        stringBuffer.append(super.toString());
        stringBuffer.append(") path= ");
        stringBuffer.append(this.path);
        stringBuffer.append("\n");
        stringBuffer.append("compPaths = {");
        stringBuffer.append(this.compPaths);
        stringBuffer.append(" }");
        return stringBuffer.toString();
    }
}
