package androidx.constraintlayout.motion.widget;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.utils.CurveFit;
import androidx.constraintlayout.motion.utils.Oscillator;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class KeyCycleOscillator {
    private CurveFit a;
    private d b;

    /* renamed from: c, reason: collision with root package name */
    protected ConstraintAttribute f303c;

    /* renamed from: d, reason: collision with root package name */
    private String f304d;
    private int e = 0;
    public int mVariesBy = 0;
    ArrayList<p> f = new ArrayList<>();

    /* loaded from: classes.dex */
    class a implements Comparator<p> {
        a(KeyCycleOscillator keyCycleOscillator) {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(p pVar, p pVar2) {
            return defpackage.a.a(pVar.a, pVar2.a);
        }
    }

    /* loaded from: classes.dex */
    static class b extends KeyCycleOscillator {
        b() {
        }

        @Override // androidx.constraintlayout.motion.widget.KeyCycleOscillator
        public void setProperty(View view, float f) {
            view.setAlpha(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class c extends KeyCycleOscillator {
        float[] g = new float[1];

        c() {
        }

        @Override // androidx.constraintlayout.motion.widget.KeyCycleOscillator
        public void setProperty(View view, float f) {
            this.g[0] = get(f);
            this.f303c.setInterpolatedValue(view, this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d {
        Oscillator a = new Oscillator();
        float[] b;

        /* renamed from: c, reason: collision with root package name */
        double[] f305c;

        /* renamed from: d, reason: collision with root package name */
        float[] f306d;
        float[] e;
        CurveFit f;
        double[] g;
        double[] h;

        d(int i2, int i3, int i4) {
            new HashMap();
            this.a.setType(i2);
            this.b = new float[i4];
            this.f305c = new double[i4];
            this.f306d = new float[i4];
            this.e = new float[i4];
            float[] fArr = new float[i4];
        }

        public double a(float f) {
            CurveFit curveFit = this.f;
            if (curveFit != null) {
                double d2 = f;
                curveFit.getSlope(d2, this.h);
                this.f.getPos(d2, this.g);
            } else {
                double[] dArr = this.h;
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
            }
            double d3 = f;
            double value = this.a.getValue(d3);
            double slope = this.a.getSlope(d3);
            double[] dArr2 = this.h;
            return dArr2[0] + (value * dArr2[1]) + (slope * this.g[1]);
        }

        public double b(float f) {
            CurveFit curveFit = this.f;
            if (curveFit != null) {
                curveFit.getPos(f, this.g);
            } else {
                double[] dArr = this.g;
                dArr[0] = this.e[0];
                dArr[1] = this.b[0];
            }
            return this.g[0] + (this.a.getValue(f) * this.g[1]);
        }

        public void c(int i2, int i3, float f, float f2, float f3) {
            double[] dArr = this.f305c;
            double d2 = i3;
            Double.isNaN(d2);
            dArr[i2] = d2 / 100.0d;
            this.f306d[i2] = f;
            this.e[i2] = f2;
            this.b[i2] = f3;
        }

        public void d(float f) {
            double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, this.f305c.length, 2);
            float[] fArr = this.b;
            this.g = new double[fArr.length + 1];
            this.h = new double[fArr.length + 1];
            if (this.f305c[0] > 0.0d) {
                this.a.addPoint(0.0d, this.f306d[0]);
            }
            double[] dArr2 = this.f305c;
            int length = dArr2.length - 1;
            if (dArr2[length] < 1.0d) {
                this.a.addPoint(1.0d, this.f306d[length]);
            }
            for (int i2 = 0; i2 < dArr.length; i2++) {
                dArr[i2][0] = this.e[i2];
                int i3 = 0;
                while (true) {
                    if (i3 < this.b.length) {
                        dArr[i3][1] = r4[i3];
                        i3++;
                    }
                }
                this.a.addPoint(this.f305c[i2], this.f306d[i2]);
            }
            this.a.normalize();
            double[] dArr3 = this.f305c;
            this.f = dArr3.length > 1 ? CurveFit.get(0, dArr3, dArr) : null;
        }
    }

    /* loaded from: classes.dex */
    static class e extends KeyCycleOscillator {
        e() {
        }

        @Override // androidx.constraintlayout.motion.widget.KeyCycleOscillator
        public void setProperty(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(get(f));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class f extends KeyCycleOscillator {
        f() {
        }

        public void b(View view, float f, double d2, double d3) {
            view.setRotation(get(f) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }

        @Override // androidx.constraintlayout.motion.widget.KeyCycleOscillator
        public void setProperty(View view, float f) {
        }
    }

    /* loaded from: classes.dex */
    static class g extends KeyCycleOscillator {
        boolean g = false;

        g() {
        }

        @Override // androidx.constraintlayout.motion.widget.KeyCycleOscillator
        public void setProperty(View view, float f) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(get(f));
                return;
            }
            if (this.g) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.g = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(get(f)));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    Log.e("KeyCycleOscillator", "unable to setProgress", e);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    static class h extends KeyCycleOscillator {
        h() {
        }

        @Override // androidx.constraintlayout.motion.widget.KeyCycleOscillator
        public void setProperty(View view, float f) {
            view.setRotation(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class i extends KeyCycleOscillator {
        i() {
        }

        @Override // androidx.constraintlayout.motion.widget.KeyCycleOscillator
        public void setProperty(View view, float f) {
            view.setRotationX(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class j extends KeyCycleOscillator {
        j() {
        }

        @Override // androidx.constraintlayout.motion.widget.KeyCycleOscillator
        public void setProperty(View view, float f) {
            view.setRotationY(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class k extends KeyCycleOscillator {
        k() {
        }

        @Override // androidx.constraintlayout.motion.widget.KeyCycleOscillator
        public void setProperty(View view, float f) {
            view.setScaleX(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class l extends KeyCycleOscillator {
        l() {
        }

        @Override // androidx.constraintlayout.motion.widget.KeyCycleOscillator
        public void setProperty(View view, float f) {
            view.setScaleY(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class m extends KeyCycleOscillator {
        m() {
        }

        @Override // androidx.constraintlayout.motion.widget.KeyCycleOscillator
        public void setProperty(View view, float f) {
            view.setTranslationX(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class n extends KeyCycleOscillator {
        n() {
        }

        @Override // androidx.constraintlayout.motion.widget.KeyCycleOscillator
        public void setProperty(View view, float f) {
            view.setTranslationY(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class o extends KeyCycleOscillator {
        o() {
        }

        @Override // androidx.constraintlayout.motion.widget.KeyCycleOscillator
        public void setProperty(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(get(f));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class p {
        int a;
        float b;

        /* renamed from: c, reason: collision with root package name */
        float f307c;

        /* renamed from: d, reason: collision with root package name */
        float f308d;

        public p(int i2, float f, float f2, float f3) {
            this.a = i2;
            this.b = f3;
            this.f307c = f2;
            this.f308d = f;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KeyCycleOscillator a(String str) {
        if (str.startsWith("CUSTOM")) {
            return new c();
        }
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1249320806:
                if (str.equals("rotationX")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1249320805:
                if (str.equals("rotationY")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1225497657:
                if (str.equals("translationX")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1225497656:
                if (str.equals("translationY")) {
                    c2 = 3;
                    break;
                }
                break;
            case -1225497655:
                if (str.equals("translationZ")) {
                    c2 = 4;
                    break;
                }
                break;
            case -1001078227:
                if (str.equals("progress")) {
                    c2 = 5;
                    break;
                }
                break;
            case -908189618:
                if (str.equals("scaleX")) {
                    c2 = 6;
                    break;
                }
                break;
            case -908189617:
                if (str.equals("scaleY")) {
                    c2 = 7;
                    break;
                }
                break;
            case -797520672:
                if (str.equals("waveVariesBy")) {
                    c2 = '\b';
                    break;
                }
                break;
            case -40300674:
                if (str.equals("rotation")) {
                    c2 = '\t';
                    break;
                }
                break;
            case -4379043:
                if (str.equals("elevation")) {
                    c2 = '\n';
                    break;
                }
                break;
            case 37232917:
                if (str.equals("transitionPathRotate")) {
                    c2 = 11;
                    break;
                }
                break;
            case 92909918:
                if (str.equals("alpha")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 156108012:
                if (str.equals("waveOffset")) {
                    c2 = '\r';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return new i();
            case 1:
                return new j();
            case 2:
                return new m();
            case 3:
                return new n();
            case 4:
                return new o();
            case 5:
                return new g();
            case 6:
                return new k();
            case 7:
                return new l();
            case '\b':
                return new b();
            case '\t':
                return new h();
            case '\n':
                return new e();
            case 11:
                return new f();
            case '\f':
                return new b();
            case '\r':
                return new b();
            default:
                return null;
        }
    }

    public float get(float f2) {
        return (float) this.b.b(f2);
    }

    public CurveFit getCurveFit() {
        return this.a;
    }

    public float getSlope(float f2) {
        return (float) this.b.a(f2);
    }

    public void setPoint(int i2, int i3, int i4, float f2, float f3, float f4) {
        this.f.add(new p(i2, f2, f3, f4));
        if (i4 != -1) {
            this.mVariesBy = i4;
        }
        this.e = i3;
    }

    public void setPoint(int i2, int i3, int i4, float f2, float f3, float f4, ConstraintAttribute constraintAttribute) {
        this.f.add(new p(i2, f2, f3, f4));
        if (i4 != -1) {
            this.mVariesBy = i4;
        }
        this.e = i3;
        this.f303c = constraintAttribute;
    }

    public abstract void setProperty(View view, float f2);

    public void setType(String str) {
        this.f304d = str;
    }

    @TargetApi(19)
    public void setup(float f2) {
        int size = this.f.size();
        if (size == 0) {
            return;
        }
        Collections.sort(this.f, new a(this));
        double[] dArr = new double[size];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
        this.b = new d(this.e, this.mVariesBy, size);
        Iterator<p> it = this.f.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            p next = it.next();
            float f3 = next.f308d;
            double d2 = f3;
            Double.isNaN(d2);
            dArr[i2] = d2 * 0.01d;
            double[] dArr3 = dArr2[i2];
            float f4 = next.b;
            dArr3[0] = f4;
            double[] dArr4 = dArr2[i2];
            float f5 = next.f307c;
            dArr4[1] = f5;
            this.b.c(i2, next.a, f3, f5, f4);
            i2++;
        }
        this.b.d(f2);
        this.a = CurveFit.get(0, dArr, dArr2);
    }

    public String toString() {
        String str = this.f304d;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        Iterator<p> it = this.f.iterator();
        while (it.hasNext()) {
            str = str + "[" + it.next().a + " , " + decimalFormat.format(r3.b) + "] ";
        }
        return str;
    }

    public boolean variesByPath() {
        return this.mVariesBy == 1;
    }
}
