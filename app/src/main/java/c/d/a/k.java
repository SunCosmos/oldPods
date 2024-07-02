package c.d.a;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes.dex */
public class k implements Cloneable {
    private static final l k = new f();
    private static final l l = new d();
    private static Class[] m;
    private static Class[] n;
    private static Class[] o;
    private static final HashMap<Class, HashMap<String, Method>> p;
    private static final HashMap<Class, HashMap<String, Method>> q;
    String a;
    protected c.d.b.c b;

    /* renamed from: c, reason: collision with root package name */
    Method f1401c;

    /* renamed from: d, reason: collision with root package name */
    private Method f1402d;
    Class e;
    h f;
    final ReentrantReadWriteLock g;
    final Object[] h;

    /* renamed from: i, reason: collision with root package name */
    private l f1403i;
    private Object j;

    /* loaded from: classes.dex */
    static class b extends k {
        private c.d.b.a r;
        e s;
        float t;

        public b(c.d.b.c cVar, float... fArr) {
            super(cVar);
            k(fArr);
            if (cVar instanceof c.d.b.a) {
                this.r = (c.d.b.a) this.b;
            }
        }

        public b(String str, float... fArr) {
            super(str);
            k(fArr);
        }

        @Override // c.d.a.k
        void a(float f) {
            this.t = this.s.f(f);
        }

        @Override // c.d.a.k
        Object c() {
            return Float.valueOf(this.t);
        }

        @Override // c.d.a.k
        void j(Object obj) {
            String invocationTargetException;
            c.d.b.a aVar = this.r;
            if (aVar != null) {
                aVar.e(obj, this.t);
                return;
            }
            c.d.b.c cVar = this.b;
            if (cVar != null) {
                cVar.c(obj, Float.valueOf(this.t));
                return;
            }
            if (this.f1401c != null) {
                try {
                    this.h[0] = Float.valueOf(this.t);
                    this.f1401c.invoke(obj, this.h);
                } catch (IllegalAccessException e) {
                    invocationTargetException = e.toString();
                    Log.e("PropertyValuesHolder", invocationTargetException);
                } catch (InvocationTargetException e2) {
                    invocationTargetException = e2.toString();
                    Log.e("PropertyValuesHolder", invocationTargetException);
                }
            }
        }

        @Override // c.d.a.k
        public void k(float... fArr) {
            super.k(fArr);
            this.s = (e) this.f;
        }

        @Override // c.d.a.k
        void o(Class cls) {
            if (this.b != null) {
                return;
            }
            super.o(cls);
        }

        @Override // c.d.a.k
        /* renamed from: r, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public b clone() {
            b bVar = (b) super.clone();
            bVar.s = (e) bVar.f;
            return bVar;
        }
    }

    static {
        Class cls = Float.TYPE;
        Class cls2 = Integer.TYPE;
        m = new Class[]{cls, Float.class, Double.TYPE, cls2, Double.class, Integer.class};
        Class cls3 = Double.TYPE;
        n = new Class[]{cls2, Integer.class, cls, cls3, Float.class, Double.class};
        o = new Class[]{cls3, Double.class, cls, cls2, Float.class, Integer.class};
        p = new HashMap<>();
        q = new HashMap<>();
    }

    private k(c.d.b.c cVar) {
        this.f1401c = null;
        this.f1402d = null;
        this.f = null;
        this.g = new ReentrantReadWriteLock();
        this.h = new Object[1];
        this.b = cVar;
        if (cVar != null) {
            this.a = cVar.b();
        }
    }

    private k(String str) {
        this.f1401c = null;
        this.f1402d = null;
        this.f = null;
        this.g = new ReentrantReadWriteLock();
        this.h = new Object[1];
        this.a = str;
    }

    static String d(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return str;
        }
        return str + Character.toUpperCase(str2.charAt(0)) + str2.substring(1);
    }

    private Method e(Class cls, String str, Class cls2) {
        StringBuilder sb;
        String d2 = d(str, this.a);
        Method method = null;
        if (cls2 == null) {
            try {
                return cls.getMethod(d2, null);
            } catch (NoSuchMethodException e) {
                try {
                    method = cls.getDeclaredMethod(d2, null);
                    method.setAccessible(true);
                } catch (NoSuchMethodException unused) {
                    sb = new StringBuilder();
                    sb.append("Couldn't find no-arg method for property ");
                    sb.append(this.a);
                    sb.append(": ");
                    sb.append(e);
                }
            }
        } else {
            Class<?>[] clsArr = new Class[1];
            for (Class<?> cls3 : this.e.equals(Float.class) ? m : this.e.equals(Integer.class) ? n : this.e.equals(Double.class) ? o : new Class[]{this.e}) {
                clsArr[0] = cls3;
                try {
                    try {
                        Method method2 = cls.getMethod(d2, clsArr);
                        this.e = cls3;
                        return method2;
                    } catch (NoSuchMethodException unused2) {
                        method = cls.getDeclaredMethod(d2, clsArr);
                        method.setAccessible(true);
                        this.e = cls3;
                        return method;
                    }
                } catch (NoSuchMethodException unused3) {
                }
            }
            sb = new StringBuilder();
            sb.append("Couldn't find setter/getter for property ");
            sb.append(this.a);
            sb.append(" with value type ");
            sb.append(this.e);
        }
        Log.e("PropertyValuesHolder", sb.toString());
        return method;
    }

    public static k h(c.d.b.c<?, Float> cVar, float... fArr) {
        return new b(cVar, fArr);
    }

    public static k i(String str, float... fArr) {
        return new b(str, fArr);
    }

    private void n(Class cls) {
        this.f1402d = q(cls, q, "get", null);
    }

    private Method q(Class cls, HashMap<Class, HashMap<String, Method>> hashMap, String str, Class cls2) {
        try {
            this.g.writeLock().lock();
            HashMap<String, Method> hashMap2 = hashMap.get(cls);
            Method method = hashMap2 != null ? hashMap2.get(this.a) : null;
            if (method == null) {
                method = e(cls, str, cls2);
                if (hashMap2 == null) {
                    hashMap2 = new HashMap<>();
                    hashMap.put(cls, hashMap2);
                }
                hashMap2.put(this.a, method);
            }
            return method;
        } finally {
            this.g.writeLock().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(float f) {
        this.j = this.f.b(f);
    }

    @Override // 
    /* renamed from: b */
    public k clone() {
        try {
            k kVar = (k) super.clone();
            kVar.a = this.a;
            kVar.b = this.b;
            kVar.f = this.f.clone();
            kVar.f1403i = this.f1403i;
            return kVar;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    Object c() {
        return this.j;
    }

    public String f() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        if (this.f1403i == null) {
            Class cls = this.e;
            this.f1403i = cls == Integer.class ? k : cls == Float.class ? l : null;
        }
        l lVar = this.f1403i;
        if (lVar != null) {
            this.f.d(lVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(Object obj) {
        String invocationTargetException;
        c.d.b.c cVar = this.b;
        if (cVar != null) {
            cVar.c(obj, c());
        }
        if (this.f1401c != null) {
            try {
                this.h[0] = c();
                this.f1401c.invoke(obj, this.h);
            } catch (IllegalAccessException e) {
                invocationTargetException = e.toString();
                Log.e("PropertyValuesHolder", invocationTargetException);
            } catch (InvocationTargetException e2) {
                invocationTargetException = e2.toString();
                Log.e("PropertyValuesHolder", invocationTargetException);
            }
        }
    }

    public void k(float... fArr) {
        this.e = Float.TYPE;
        this.f = h.c(fArr);
    }

    public void l(c.d.b.c cVar) {
        this.b = cVar;
    }

    public void m(String str) {
        this.a = str;
    }

    void o(Class cls) {
        this.f1401c = q(cls, p, "set", this.e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(Object obj) {
        String invocationTargetException;
        c.d.b.c cVar = this.b;
        if (cVar != null) {
            try {
                cVar.a(obj);
                Iterator<g> it = this.f.f1397d.iterator();
                while (it.hasNext()) {
                    g next = it.next();
                    if (!next.e()) {
                        next.i(this.b.a(obj));
                    }
                }
                return;
            } catch (ClassCastException unused) {
                Log.e("PropertyValuesHolder", "No such property (" + this.b.b() + ") on target object " + obj + ". Trying reflection instead");
                this.b = null;
            }
        }
        Class<?> cls = obj.getClass();
        if (this.f1401c == null) {
            o(cls);
        }
        Iterator<g> it2 = this.f.f1397d.iterator();
        while (it2.hasNext()) {
            g next2 = it2.next();
            if (!next2.e()) {
                if (this.f1402d == null) {
                    n(cls);
                }
                try {
                    next2.i(this.f1402d.invoke(obj, new Object[0]));
                } catch (IllegalAccessException e) {
                    invocationTargetException = e.toString();
                    Log.e("PropertyValuesHolder", invocationTargetException);
                } catch (InvocationTargetException e2) {
                    invocationTargetException = e2.toString();
                    Log.e("PropertyValuesHolder", invocationTargetException);
                }
            }
        }
    }

    public String toString() {
        return this.a + ": " + this.f.toString();
    }
}
