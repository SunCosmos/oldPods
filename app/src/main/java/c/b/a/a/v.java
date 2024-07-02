package c.b.a.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* loaded from: classes.dex */
public class v {
    public v() {
        Pattern.compile("classes(?:[2-9]?|[1-9][0-9]+)\\.dex(\\.jar)?");
    }

    private Object a(Object obj, Object obj2) {
        Class<?> componentType = obj.getClass().getComponentType();
        int length = Array.getLength(obj) + 1;
        Object newInstance = Array.newInstance(componentType, length);
        Array.set(newInstance, 0, obj2);
        for (int i2 = 1; i2 < length; i2++) {
            Array.set(newInstance, i2, Array.get(obj, i2 - 1));
        }
        return newInstance;
    }

    private Object b(Object obj, Object obj2) {
        Class<?> componentType = obj2.getClass().getComponentType();
        int length = Array.getLength(obj2);
        int length2 = Array.getLength(obj) + length;
        Object newInstance = Array.newInstance(componentType, length2);
        int i2 = 0;
        while (i2 < length2) {
            Array.set(newInstance, i2, i2 < length ? Array.get(obj2, i2) : Array.get(obj, i2 - length));
            i2++;
        }
        return newInstance;
    }

    private Object c(Object obj) {
        return d(obj, obj.getClass(), "dexElements");
    }

    private Object d(Object obj, Class cls, String str) {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    private Object e(Object obj) {
        return d(obj, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
    }

    private boolean f() {
        try {
            Class.forName("dalvik.system.BaseDexClassLoader");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private boolean g() {
        try {
            Class.forName("dalvik.system.LexClassLoader");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private void h(Context context, String str) {
        if (str == null || !new File(str).exists()) {
            return;
        }
        i(context, str);
    }

    private void i(Context context, String str) {
        try {
            if (g()) {
                l(context, str);
            } else if (f()) {
                j(context, str);
            } else {
                k(context, str);
            }
        } catch (Throwable unused) {
        }
    }

    private void j(Context context, String str) {
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        Object b = b(c(e(pathClassLoader)), c(e(new DexClassLoader(str, context.getDir("dex", 0).getAbsolutePath(), str, context.getClassLoader()))));
        Object e = e(pathClassLoader);
        n(e, e.getClass(), "dexElements", b);
    }

    @TargetApi(14)
    private void k(Context context, String str) {
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        DexClassLoader dexClassLoader = new DexClassLoader(str, context.getDir("dex", 0).getAbsolutePath(), str, context.getClassLoader());
        n(pathClassLoader, PathClassLoader.class, "mPaths", a(d(pathClassLoader, PathClassLoader.class, "mPaths"), d(dexClassLoader, DexClassLoader.class, "mRawDexPath")));
        n(pathClassLoader, PathClassLoader.class, "mFiles", b(d(pathClassLoader, PathClassLoader.class, "mFiles"), d(dexClassLoader, DexClassLoader.class, "mFiles")));
        n(pathClassLoader, PathClassLoader.class, "mZips", b(d(pathClassLoader, PathClassLoader.class, "mZips"), d(dexClassLoader, DexClassLoader.class, "mZips")));
        n(pathClassLoader, PathClassLoader.class, "mDexs", b(d(pathClassLoader, PathClassLoader.class, "mDexs"), d(dexClassLoader, DexClassLoader.class, "mDexs")));
    }

    private void l(Context context, String str) {
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        String replaceAll = new File(str).getName().replaceAll("\\.[a-zA-Z0-9]+", ".lex");
        Class<?> cls = Class.forName("dalvik.system.LexClassLoader");
        Object newInstance = cls.getConstructor(String.class, String.class, String.class, ClassLoader.class).newInstance(context.getDir("dex", 0).getAbsolutePath() + File.separator + replaceAll, context.getDir("dex", 0).getAbsolutePath(), str, pathClassLoader);
        n(pathClassLoader, PathClassLoader.class, "mPaths", a(d(pathClassLoader, PathClassLoader.class, "mPaths"), d(newInstance, cls, "mRawDexPath")));
        n(pathClassLoader, PathClassLoader.class, "mFiles", b(d(pathClassLoader, PathClassLoader.class, "mFiles"), d(newInstance, cls, "mFiles")));
        n(pathClassLoader, PathClassLoader.class, "mZips", b(d(pathClassLoader, PathClassLoader.class, "mZips"), d(newInstance, cls, "mZips")));
        n(pathClassLoader, PathClassLoader.class, "mLexs", b(d(pathClassLoader, PathClassLoader.class, "mLexs"), d(newInstance, cls, "mDexs")));
    }

    private void n(Object obj, Class cls, String str, Object obj2) {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        declaredField.set(obj, obj2);
    }

    public void m(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo != null) {
            String str = context.getFilesDir().getPath() + "/_RunDex";
            new File(str).mkdirs();
            try {
                ZipFile zipFile = new ZipFile(new File(applicationInfo.sourceDir));
                ZipEntry entry = zipFile.getEntry("classes2.dex");
                int i2 = 2;
                while (entry != null) {
                    String format = String.format("%s/%s", str, entry.getName());
                    InputStream inputStream = zipFile.getInputStream(entry);
                    FileOutputStream fileOutputStream = new FileOutputStream(format);
                    byte[] bArr = new byte[5024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read != -1) {
                            fileOutputStream.write(bArr, 0, read);
                        }
                    }
                    inputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    h(context, format);
                    i2++;
                    entry = zipFile.getEntry("classes" + i2 + ".dex");
                }
                zipFile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
