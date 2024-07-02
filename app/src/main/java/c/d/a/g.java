package c.d.a;

import android.view.animation.Interpolator;

/* loaded from: classes.dex */
public abstract class g implements Cloneable {
    float a;
    private Interpolator b = null;

    /* renamed from: c, reason: collision with root package name */
    boolean f1394c = false;

    /* loaded from: classes.dex */
    static class a extends g {

        /* renamed from: d, reason: collision with root package name */
        float f1395d;

        a(float f) {
            this.a = f;
            Class cls = Float.TYPE;
        }

        a(float f, float f2) {
            this.a = f;
            this.f1395d = f2;
            Class cls = Float.TYPE;
            this.f1394c = true;
        }

        @Override // c.d.a.g
        public Object d() {
            return Float.valueOf(this.f1395d);
        }

        @Override // c.d.a.g
        public void i(Object obj) {
            if (obj == null || obj.getClass() != Float.class) {
                return;
            }
            this.f1395d = ((Float) obj).floatValue();
            this.f1394c = true;
        }

        @Override // c.d.a.g
        /* renamed from: j, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public a clone() {
            a aVar = new a(b(), this.f1395d);
            aVar.h(c());
            return aVar;
        }

        public float k() {
            return this.f1395d;
        }
    }

    public static g f(float f) {
        return new a(f);
    }

    public static g g(float f, float f2) {
        return new a(f, f2);
    }

    @Override // 
    /* renamed from: a */
    public abstract g clone();

    public float b() {
        return this.a;
    }

    public Interpolator c() {
        return this.b;
    }

    public abstract Object d();

    public boolean e() {
        return this.f1394c;
    }

    public void h(Interpolator interpolator) {
        this.b = interpolator;
    }

    public abstract void i(Object obj);
}
