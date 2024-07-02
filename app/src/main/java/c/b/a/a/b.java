package c.b.a.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.webkit.WebView;
import com.iapp.app.Aid_YuCodeX;
import com.iapp.app.Webview;
import com.iapp.app.run.main2;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import org.keplerproject.luajava.LuaObject;

/* loaded from: classes.dex */
public class b {
    public static HashMap<String, Class<?>> a;
    private static int b;

    /* loaded from: classes.dex */
    public static class a implements InvocationHandler {
        private t a;
        private Aid_YuCodeX b;

        /* renamed from: c */
        private WebView f1340c;

        /* renamed from: d */
        private LuaObject f1341d;
        private String e;
        private int f;
        private int g;

        public a(Object obj, Object obj2) {
            int i2;
            this.a = null;
            this.b = null;
            this.f1340c = null;
            this.f1341d = null;
            this.e = null;
            this.f = 0;
            b.d();
            this.g = b.b;
            if (obj2 != null) {
                if (obj == null) {
                    if (!(obj2 instanceof LuaObject)) {
                        return;
                    }
                    this.f1341d = (LuaObject) obj2;
                    i2 = 1;
                } else if (obj instanceof t) {
                    this.a = (t) obj;
                    this.e = obj2.toString();
                    i2 = 2;
                } else if (obj instanceof Aid_YuCodeX) {
                    this.b = (Aid_YuCodeX) obj;
                    this.e = obj2.toString();
                    i2 = 4;
                } else {
                    if (!(obj instanceof Webview)) {
                        return;
                    }
                    this.f1340c = (WebView) obj;
                    this.e = obj2.toString();
                    i2 = 3;
                }
                this.f = i2;
            }
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            int i2 = this.f;
            if (i2 == 2) {
                this.a.T("st_mD", method);
                this.a.T("st_aS", objArr);
                this.a.e(this.e);
                return null;
            }
            if (i2 == 4) {
                this.b.dim("st_mD", method);
                this.b.dim("st_aS", objArr);
                this.b.YuGo(this.e);
                return null;
            }
            if (i2 == 1) {
                this.f1341d.callNoErr(method, objArr);
                return null;
            }
            if (i2 != 3) {
                return null;
            }
            WebView webView = this.f1340c;
            StringBuilder sb = new StringBuilder();
            sb.append("javascript:{\nvar functionItme = ");
            sb.append(this.e);
            sb.append(";\nfunctionItme('");
            sb.append(main2.set("^_InvocationHandler_" + this.g + "_st_mD", method));
            sb.append("', '");
            sb.append(main2.set("^_InvocationHandler_" + this.g + "_st_aS", objArr));
            sb.append("');\n}");
            webView.loadUrl(sb.toString());
            return null;
        }
    }

    public static Class<?> a(String str) {
        if (a == null) {
            l();
        }
        Class<?> cls = null;
        try {
            cls = Class.forName(str);
            a.put(str, cls);
            return cls;
        } catch (Exception e) {
            e.printStackTrace();
            return cls;
        }
    }

    public static Class<?> b(String str) {
        if (a == null) {
            l();
        }
        Class<?> cls = a.get(str);
        if (cls == null) {
            try {
                cls = Class.forName(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (cls == null) {
                return null;
            }
            a.put(str, cls);
        }
        return cls;
    }

    static /* synthetic */ int d() {
        int i2 = b;
        b = i2 + 1;
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00c6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object e(android.content.Context r17, java.lang.Object r18, java.lang.Class<?> r19, java.lang.String r20, java.lang.Object[] r21, java.lang.Object r22, java.lang.Object r23) {
        /*
            Method dump skipped, instructions count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.b.e(android.content.Context, java.lang.Object, java.lang.Class, java.lang.String, java.lang.Object[], java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object f(Context context, Object obj, String str, String str2, Object[] objArr, Object obj2, Object obj3) {
        return e(context, obj, b(str), str2, objArr, obj2, obj3);
    }

    public static Object g(Context context, Object obj, String str, Object[] objArr, Object obj2, Object obj3) {
        int lastIndexOf = str.lastIndexOf(46);
        String substring = str.substring(0, lastIndexOf);
        return e(context, obj, b(substring), str.substring(lastIndexOf + 1), objArr, obj2, obj3);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005e A[Catch: InvocationTargetException -> 0x006a, IllegalAccessException -> 0x006f, IllegalArgumentException -> 0x0074, TRY_ENTER, TryCatch #6 {IllegalAccessException -> 0x006f, IllegalArgumentException -> 0x0074, InvocationTargetException -> 0x006a, blocks: (B:19:0x005e, B:22:0x0063), top: B:17:0x005c }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0063 A[Catch: InvocationTargetException -> 0x006a, IllegalAccessException -> 0x006f, IllegalArgumentException -> 0x0074, TRY_LEAVE, TryCatch #6 {IllegalAccessException -> 0x006f, IllegalArgumentException -> 0x0074, InvocationTargetException -> 0x006a, blocks: (B:19:0x005e, B:22:0x0063), top: B:17:0x005c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object h(java.lang.Object r10, java.lang.Class<?> r11, java.lang.String r12, java.lang.Object[] r13) {
        /*
            r0 = 0
            r1 = 1
            r2 = 0
            if (r13 != 0) goto L18
            java.lang.Class[] r13 = new java.lang.Class[r2]     // Catch: java.lang.NoSuchMethodException -> L11
            java.lang.reflect.Method r11 = r11.getDeclaredMethod(r12, r13)     // Catch: java.lang.NoSuchMethodException -> L11
            r11.setAccessible(r1)     // Catch: java.lang.NoSuchMethodException -> Lf
            goto L16
        Lf:
            r12 = move-exception
            goto L13
        L11:
            r12 = move-exception
            r11 = r0
        L13:
            r12.printStackTrace()
        L16:
            r4 = r0
            goto L5c
        L18:
            int r3 = r13.length
            int r4 = r3 / 2
            java.lang.Class[] r5 = new java.lang.Class[r4]
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r6 = 0
            r7 = 0
        L21:
            if (r6 >= r3) goto L4d
            r8 = r13[r6]
            boolean r8 = r8 instanceof java.lang.Class
            if (r8 == 0) goto L30
            r8 = r13[r6]
            java.lang.Class r8 = (java.lang.Class) r8
            r5[r7] = r8
            goto L3c
        L30:
            r8 = r13[r6]
            java.lang.String r8 = r8.toString()
            java.lang.Class r8 = b(r8)
            r5[r7] = r8
        L3c:
            r8 = r5[r7]
            int r9 = r6 + 1
            r9 = r13[r9]
            java.lang.Object r8 = s(r8, r9)
            r4[r7] = r8
            int r7 = r7 + 1
            int r6 = r6 + 2
            goto L21
        L4d:
            java.lang.reflect.Method r11 = r11.getDeclaredMethod(r12, r5)     // Catch: java.lang.NoSuchMethodException -> L57
            r11.setAccessible(r1)     // Catch: java.lang.NoSuchMethodException -> L55
            goto L5c
        L55:
            r12 = move-exception
            goto L59
        L57:
            r12 = move-exception
            r11 = r0
        L59:
            r12.printStackTrace()
        L5c:
            if (r4 == 0) goto L63
            java.lang.Object r10 = r11.invoke(r10, r4)     // Catch: java.lang.reflect.InvocationTargetException -> L6a java.lang.IllegalAccessException -> L6f java.lang.IllegalArgumentException -> L74
            return r10
        L63:
            java.lang.Object[] r12 = new java.lang.Object[r2]     // Catch: java.lang.reflect.InvocationTargetException -> L6a java.lang.IllegalAccessException -> L6f java.lang.IllegalArgumentException -> L74
            java.lang.Object r10 = r11.invoke(r10, r12)     // Catch: java.lang.reflect.InvocationTargetException -> L6a java.lang.IllegalAccessException -> L6f java.lang.IllegalArgumentException -> L74
            return r10
        L6a:
            r10 = move-exception
            r10.printStackTrace()
            goto L78
        L6f:
            r10 = move-exception
            r10.printStackTrace()
            goto L78
        L74:
            r10 = move-exception
            r10.printStackTrace()
        L78:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.b.h(java.lang.Object, java.lang.Class, java.lang.String, java.lang.Object[]):java.lang.Object");
    }

    public static Object i(Object obj, Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object j(Object obj, String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return k(obj, str.substring(0, lastIndexOf), str.substring(lastIndexOf + 1));
    }

    public static Object k(Object obj, String str, String str2) {
        return i(obj, b(str), str2);
    }

    @TargetApi(14)
    public static void l() {
        a = new HashMap<>(0);
        Class<?>[] clsArr = {Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Void.TYPE, Boolean.class, Byte.class, Character.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Void.class, Number.class, String.class, Object.class, CharSequence.class, File.class, Class.class, Context.class, Activity.class, AccessibilityEvent.class, boolean[].class, byte[].class, char[].class, short[].class, int[].class, long[].class, float[].class, double[].class, Boolean[].class, Byte[].class, Character[].class, Short[].class, Integer[].class, Long[].class, Float[].class, Double[].class, Number[].class, String[].class, Object[].class, CharSequence[].class, File[].class, Class[].class, Context[].class, Activity[].class};
        String[] strArr = {"boolean", "byte", "char", "short", "int", "long", "float", "double", "void", "Boolean", "Byte", "Character", "Short", "Integer", "Long", "Float", "Double", "Void", "Number", "String", "Object", "CharSequence", "File", "Class", "Context", "Activity", "AccessibilityEvent", "boolean[]", "byte[]", "char[]", "short[]", "int[]", "long[]", "float[]", "double[]", "Boolean[]", "Byte[]", "Character[]", "Short[]", "Integer[]", "Long[]", "Float[]", "Number[]", "Double[]", "String[]", "Object[]", "CharSequence[]", "File[]", "Class[]", "Context[]", "Activity[]"};
        for (int i2 = 0; i2 < 51; i2++) {
            a.put(strArr[i2], clsArr[i2]);
        }
        if (Build.VERSION.SDK_INT >= 14) {
            a.put("AccessibilityNodeInfo", AccessibilityNodeInfo.class);
        }
    }

    public static Object m(Context context, Class<?> cls, Object[] objArr, Object obj, Object obj2) {
        Constructor<?> constructor;
        try {
            if (objArr == null) {
                return cls.newInstance();
            }
            int length = objArr.length;
            int i2 = length / 2;
            Class<?>[] clsArr = new Class[i2];
            Object[] objArr2 = new Object[i2];
            ArrayList arrayList = new ArrayList();
            boolean[] zArr = new boolean[i2];
            int i3 = 0;
            for (int i4 = 0; i4 < length; i4 += 2) {
                if (objArr[i4] instanceof Class) {
                    clsArr[i3] = (Class) objArr[i4];
                    zArr[i3] = false;
                    objArr2[i3] = s(clsArr[i3], objArr[i4 + 1]);
                } else {
                    String obj3 = objArr[i4].toString();
                    if (obj3.indexOf(46) == 0) {
                        clsArr[i3] = b(obj3.substring(1));
                        arrayList.add(clsArr[i3]);
                        zArr[i3] = true;
                    } else {
                        clsArr[i3] = b(obj3);
                        zArr[i3] = false;
                        objArr2[i3] = s(clsArr[i3], objArr[i4 + 1]);
                    }
                }
                i3++;
            }
            int size = arrayList.size();
            if (size > 0) {
                Object newProxyInstance = Proxy.newProxyInstance(context.getClassLoader(), (Class[]) arrayList.toArray(new Class[size]), new a(obj, obj2));
                for (int i5 = 0; i5 < i2; i5++) {
                    if (zArr[i5]) {
                        objArr2[i5] = newProxyInstance;
                    }
                }
            }
            try {
                constructor = cls.getDeclaredConstructor(clsArr);
            } catch (NoSuchMethodException e) {
                e = e;
                constructor = null;
            }
            try {
                try {
                    constructor.setAccessible(true);
                } catch (NoSuchMethodException e2) {
                    e = e2;
                    e.printStackTrace();
                    return constructor.newInstance(objArr2);
                }
                return constructor.newInstance(objArr2);
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
                return null;
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
                return null;
            }
        } catch (IllegalAccessException e5) {
            e5.printStackTrace();
        } catch (InstantiationException e6) {
            e6.printStackTrace();
        }
    }

    public static Object n(Context context, String str, Object[] objArr, Object obj, Object obj2) {
        return m(context, b(str), objArr, obj, obj2);
    }

    public static Object o(ClassLoader classLoader, Class<?> cls, Object obj, Object obj2) {
        return Proxy.newProxyInstance(classLoader, new Class[]{cls}, new a(obj, obj2));
    }

    public static Object p(Object obj, String str, Object obj2) {
        int lastIndexOf = str.lastIndexOf(46);
        return Boolean.valueOf(r(obj, str.substring(0, lastIndexOf), str.substring(lastIndexOf + 1), obj2));
    }

    public static boolean q(Object obj, Class<?> cls, String str, Object obj2) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            Object obj3 = declaredField.get(obj);
            if (obj3 != null) {
                obj2 = s(obj3.getClass(), obj2);
            }
            declaredField.set(obj, obj2);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean r(Object obj, String str, String str2, Object obj2) {
        return q(obj, b(str), str2, obj2);
    }

    private static Object s(Class<?> cls, Object obj) {
        if (obj == null) {
            return null;
        }
        if (cls == Integer.TYPE || cls == Integer.class) {
            return obj instanceof Integer ? Integer.valueOf(((Integer) obj).intValue()) : obj instanceof Double ? Integer.valueOf((int) Double.parseDouble(obj.toString())) : Integer.valueOf(Integer.parseInt(obj.toString()));
        }
        if (cls == Double.TYPE || cls == Double.class) {
            return obj instanceof Double ? Double.valueOf(((Double) obj).doubleValue()) : Double.valueOf(Double.parseDouble(obj.toString()));
        }
        if (cls == Long.TYPE || cls == Long.class) {
            return obj instanceof Long ? Long.valueOf(((Long) obj).longValue()) : obj instanceof Double ? Long.valueOf((long) Double.parseDouble(obj.toString())) : Long.valueOf(Long.parseLong(obj.toString()));
        }
        if (cls == Float.TYPE || cls == Float.class) {
            return obj instanceof Float ? Float.valueOf(((Float) obj).floatValue()) : Float.valueOf(Float.parseFloat(obj.toString()));
        }
        if (cls == Boolean.TYPE || cls == Boolean.class) {
            return obj instanceof Boolean ? Boolean.valueOf(((Boolean) obj).booleanValue()) : Boolean.valueOf(obj.equals("true"));
        }
        if (cls == Byte.TYPE || cls == Byte.class) {
            return obj instanceof Byte ? Byte.valueOf(((Byte) obj).byteValue()) : obj instanceof Double ? Byte.valueOf((byte) Double.parseDouble(obj.toString())) : Byte.valueOf(Byte.parseByte(obj.toString()));
        }
        if (cls == Character.TYPE || cls == Character.class) {
            return obj instanceof Character ? Character.valueOf(((Character) obj).charValue()) : Character.valueOf(obj.toString().charAt(0));
        }
        if (cls == Short.TYPE || cls == Short.class) {
            return obj instanceof Short ? Short.valueOf(((Short) obj).shortValue()) : obj instanceof Double ? Short.valueOf((short) Double.parseDouble(obj.toString())) : Short.valueOf(Short.parseShort(obj.toString()));
        }
        try {
            return !obj.getClass().equals(cls) ? cls.cast(obj) : obj;
        } catch (Exception e) {
            e.printStackTrace();
            return obj;
        }
    }
}
