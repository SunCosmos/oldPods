package androidx.constraintlayout.motion.widget;

import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.motion.utils.CurveFit;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

/* loaded from: classes.dex */
public abstract class TimeCycleSplineSet {
    private static float k = 6.2831855f;
    protected CurveFit a;
    private int e;
    private String f;

    /* renamed from: i, reason: collision with root package name */
    long f337i;
    protected int b = 0;

    /* renamed from: c, reason: collision with root package name */
    protected int[] f335c = new int[10];

    /* renamed from: d, reason: collision with root package name */
    protected float[][] f336d = (float[][]) Array.newInstance((Class<?>) float.class, 10, 3);
    private float[] g = new float[3];
    protected boolean h = false;
    float j = Float.NaN;

    /* loaded from: classes.dex */
    static class a extends TimeCycleSplineSet {
        a() {
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public boolean setProperty(View view, float f, long j, KeyCache keyCache) {
            view.setAlpha(get(f, j, view, keyCache));
            return this.h;
        }
    }

    /* loaded from: classes.dex */
    static class b extends TimeCycleSplineSet {
        String l;
        SparseArray<ConstraintAttribute> m;
        SparseArray<float[]> n = new SparseArray<>();
        float[] o;
        float[] p;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            this.l = str.split(",")[1];
            this.m = sparseArray;
        }

        public void e(int i2, ConstraintAttribute constraintAttribute, float f, int i3, float f2) {
            this.m.append(i2, constraintAttribute);
            this.n.append(i2, new float[]{f, f2});
            this.b = Math.max(this.b, i3);
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public void setPoint(int i2, float f, float f2, int i3, float f3) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute,...)");
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public boolean setProperty(View view, float f, long j, KeyCache keyCache) {
            this.a.getPos(f, this.o);
            float[] fArr = this.o;
            float f2 = fArr[fArr.length - 2];
            float f3 = fArr[fArr.length - 1];
            long j2 = j - this.f337i;
            if (Float.isNaN(this.j)) {
                float a = keyCache.a(view, this.l, 0);
                this.j = a;
                if (Float.isNaN(a)) {
                    this.j = 0.0f;
                }
            }
            double d2 = this.j;
            double d3 = j2;
            Double.isNaN(d3);
            double d4 = f2;
            Double.isNaN(d4);
            Double.isNaN(d2);
            float f4 = (float) ((d2 + ((d3 * 1.0E-9d) * d4)) % 1.0d);
            this.j = f4;
            this.f337i = j;
            float a2 = a(f4);
            this.h = false;
            int i2 = 0;
            while (true) {
                float[] fArr2 = this.p;
                if (i2 >= fArr2.length) {
                    break;
                }
                boolean z = this.h;
                float[] fArr3 = this.o;
                this.h = z | (((double) fArr3[i2]) != 0.0d);
                fArr2[i2] = (fArr3[i2] * a2) + f3;
                i2++;
            }
            this.m.valueAt(0).setInterpolatedValue(view, this.p);
            if (f2 != 0.0f) {
                this.h = true;
            }
            return this.h;
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public void setup(int i2) {
            int size = this.m.size();
            int noOfInterpValues = this.m.valueAt(0).noOfInterpValues();
            double[] dArr = new double[size];
            int i3 = noOfInterpValues + 2;
            this.o = new float[i3];
            this.p = new float[noOfInterpValues];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, i3);
            for (int i4 = 0; i4 < size; i4++) {
                int keyAt = this.m.keyAt(i4);
                ConstraintAttribute valueAt = this.m.valueAt(i4);
                float[] valueAt2 = this.n.valueAt(i4);
                double d2 = keyAt;
                Double.isNaN(d2);
                dArr[i4] = d2 * 0.01d;
                valueAt.getValuesToInterpolate(this.o);
                int i5 = 0;
                while (true) {
                    if (i5 < this.o.length) {
                        dArr2[i4][i5] = r8[i5];
                        i5++;
                    }
                }
                dArr2[i4][noOfInterpValues] = valueAt2[0];
                dArr2[i4][noOfInterpValues + 1] = valueAt2[1];
            }
            this.a = CurveFit.get(i2, dArr, dArr2);
        }
    }

    /* loaded from: classes.dex */
    static class c extends TimeCycleSplineSet {
        c() {
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public boolean setProperty(View view, float f, long j, KeyCache keyCache) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(get(f, j, view, keyCache));
            }
            return this.h;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d extends TimeCycleSplineSet {
        d() {
        }

        public boolean e(View view, KeyCache keyCache, float f, long j, double d2, double d3) {
            view.setRotation(get(f, j, view, keyCache) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
            return this.h;
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public boolean setProperty(View view, float f, long j, KeyCache keyCache) {
            return this.h;
        }
    }

    /* loaded from: classes.dex */
    static class e extends TimeCycleSplineSet {
        boolean l = false;

        e() {
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public boolean setProperty(View view, float f, long j, KeyCache keyCache) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(get(f, j, view, keyCache));
            } else {
                if (this.l) {
                    return false;
                }
                Method method = null;
                try {
                    method = view.getClass().getMethod("setProgress", Float.TYPE);
                } catch (NoSuchMethodException unused) {
                    this.l = true;
                }
                Method method2 = method;
                if (method2 != null) {
                    try {
                        method2.invoke(view, Float.valueOf(get(f, j, view, keyCache)));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        Log.e("SplineSet", "unable to setProgress", e);
                    }
                }
            }
            return this.h;
        }
    }

    /* loaded from: classes.dex */
    static class f extends TimeCycleSplineSet {
        f() {
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public boolean setProperty(View view, float f, long j, KeyCache keyCache) {
            view.setRotation(get(f, j, view, keyCache));
            return this.h;
        }
    }

    /* loaded from: classes.dex */
    static class g extends TimeCycleSplineSet {
        g() {
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public boolean setProperty(View view, float f, long j, KeyCache keyCache) {
            view.setRotationX(get(f, j, view, keyCache));
            return this.h;
        }
    }

    /* loaded from: classes.dex */
    static class h extends TimeCycleSplineSet {
        h() {
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public boolean setProperty(View view, float f, long j, KeyCache keyCache) {
            view.setRotationY(get(f, j, view, keyCache));
            return this.h;
        }
    }

    /* loaded from: classes.dex */
    static class i extends TimeCycleSplineSet {
        i() {
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public boolean setProperty(View view, float f, long j, KeyCache keyCache) {
            view.setScaleX(get(f, j, view, keyCache));
            return this.h;
        }
    }

    /* loaded from: classes.dex */
    static class j extends TimeCycleSplineSet {
        j() {
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public boolean setProperty(View view, float f, long j, KeyCache keyCache) {
            view.setScaleY(get(f, j, view, keyCache));
            return this.h;
        }
    }

    /* loaded from: classes.dex */
    private static class k {
        static void a(int[] iArr, float[][] fArr, int i2, int i3) {
            int[] iArr2 = new int[iArr.length + 10];
            iArr2[0] = i3;
            iArr2[1] = i2;
            int i4 = 2;
            while (i4 > 0) {
                int i5 = i4 - 1;
                int i6 = iArr2[i5];
                i4 = i5 - 1;
                int i7 = iArr2[i4];
                if (i6 < i7) {
                    int b = b(iArr, fArr, i6, i7);
                    int i8 = i4 + 1;
                    iArr2[i4] = b - 1;
                    int i9 = i8 + 1;
                    iArr2[i8] = i6;
                    int i10 = i9 + 1;
                    iArr2[i9] = i7;
                    i4 = i10 + 1;
                    iArr2[i10] = b + 1;
                }
            }
        }

        private static int b(int[] iArr, float[][] fArr, int i2, int i3) {
            int i4 = iArr[i3];
            int i5 = i2;
            while (i2 < i3) {
                if (iArr[i2] <= i4) {
                    c(iArr, fArr, i5, i2);
                    i5++;
                }
                i2++;
            }
            c(iArr, fArr, i5, i3);
            return i5;
        }

        private static void c(int[] iArr, float[][] fArr, int i2, int i3) {
            int i4 = iArr[i2];
            iArr[i2] = iArr[i3];
            iArr[i3] = i4;
            float[] fArr2 = fArr[i2];
            fArr[i2] = fArr[i3];
            fArr[i3] = fArr2;
        }
    }

    /* loaded from: classes.dex */
    static class l extends TimeCycleSplineSet {
        l() {
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public boolean setProperty(View view, float f, long j, KeyCache keyCache) {
            view.setTranslationX(get(f, j, view, keyCache));
            return this.h;
        }
    }

    /* loaded from: classes.dex */
    static class m extends TimeCycleSplineSet {
        m() {
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public boolean setProperty(View view, float f, long j, KeyCache keyCache) {
            view.setTranslationY(get(f, j, view, keyCache));
            return this.h;
        }
    }

    /* loaded from: classes.dex */
    static class n extends TimeCycleSplineSet {
        n() {
        }

        @Override // androidx.constraintlayout.motion.widget.TimeCycleSplineSet
        public boolean setProperty(View view, float f, long j, KeyCache keyCache) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(get(f, j, view, keyCache));
            }
            return this.h;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TimeCycleSplineSet b(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new b(str, sparseArray);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:39:0x009c. Please report as an issue. */
    public static TimeCycleSplineSet c(String str, long j2) {
        TimeCycleSplineSet gVar;
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
            case -40300674:
                if (str.equals("rotation")) {
                    c2 = '\b';
                    break;
                }
                break;
            case -4379043:
                if (str.equals("elevation")) {
                    c2 = '\t';
                    break;
                }
                break;
            case 37232917:
                if (str.equals("transitionPathRotate")) {
                    c2 = '\n';
                    break;
                }
                break;
            case 92909918:
                if (str.equals("alpha")) {
                    c2 = 11;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                gVar = new g();
                gVar.d(j2);
                return gVar;
            case 1:
                gVar = new h();
                gVar.d(j2);
                return gVar;
            case 2:
                gVar = new l();
                gVar.d(j2);
                return gVar;
            case 3:
                gVar = new m();
                gVar.d(j2);
                return gVar;
            case 4:
                gVar = new n();
                gVar.d(j2);
                return gVar;
            case 5:
                gVar = new e();
                gVar.d(j2);
                return gVar;
            case 6:
                gVar = new i();
                gVar.d(j2);
                return gVar;
            case 7:
                gVar = new j();
                gVar.d(j2);
                return gVar;
            case '\b':
                gVar = new f();
                gVar.d(j2);
                return gVar;
            case '\t':
                gVar = new c();
                gVar.d(j2);
                return gVar;
            case '\n':
                gVar = new d();
                gVar.d(j2);
                return gVar;
            case 11:
                gVar = new a();
                gVar.d(j2);
                return gVar;
            default:
                return null;
        }
    }

    protected float a(float f2) {
        float abs;
        switch (this.b) {
            case 1:
                return Math.signum(f2 * k);
            case 2:
                abs = Math.abs(f2);
                break;
            case 3:
                return (((f2 * 2.0f) + 1.0f) % 2.0f) - 1.0f;
            case 4:
                abs = ((f2 * 2.0f) + 1.0f) % 2.0f;
                break;
            case 5:
                return (float) Math.cos(f2 * k);
            case 6:
                float abs2 = 1.0f - Math.abs(((f2 * 4.0f) % 4.0f) - 2.0f);
                abs = abs2 * abs2;
                break;
            default:
                return (float) Math.sin(f2 * k);
        }
        return 1.0f - abs;
    }

    protected void d(long j2) {
        this.f337i = j2;
    }

    public float get(float f2, long j2, View view, KeyCache keyCache) {
        this.a.getPos(f2, this.g);
        float[] fArr = this.g;
        boolean z = true;
        float f3 = fArr[1];
        if (f3 == 0.0f) {
            this.h = false;
            return fArr[2];
        }
        if (Float.isNaN(this.j)) {
            float a2 = keyCache.a(view, this.f, 0);
            this.j = a2;
            if (Float.isNaN(a2)) {
                this.j = 0.0f;
            }
        }
        long j3 = j2 - this.f337i;
        double d2 = this.j;
        double d3 = j3;
        Double.isNaN(d3);
        double d4 = f3;
        Double.isNaN(d4);
        Double.isNaN(d2);
        float f4 = (float) ((d2 + ((d3 * 1.0E-9d) * d4)) % 1.0d);
        this.j = f4;
        keyCache.b(view, this.f, 0, f4);
        this.f337i = j2;
        float f5 = this.g[0];
        float a3 = (a(this.j) * f5) + this.g[2];
        if (f5 == 0.0f && f3 == 0.0f) {
            z = false;
        }
        this.h = z;
        return a3;
    }

    public CurveFit getCurveFit() {
        return this.a;
    }

    public void setPoint(int i2, float f2, float f3, int i3, float f4) {
        int[] iArr = this.f335c;
        int i4 = this.e;
        iArr[i4] = i2;
        float[][] fArr = this.f336d;
        fArr[i4][0] = f2;
        fArr[i4][1] = f3;
        fArr[i4][2] = f4;
        this.b = Math.max(this.b, i3);
        this.e++;
    }

    public abstract boolean setProperty(View view, float f2, long j2, KeyCache keyCache);

    public void setType(String str) {
        this.f = str;
    }

    public void setup(int i2) {
        int i3;
        int i4 = this.e;
        if (i4 == 0) {
            Log.e("SplineSet", "Error no points added to " + this.f);
            return;
        }
        k.a(this.f335c, this.f336d, 0, i4 - 1);
        int i5 = 1;
        int i6 = 0;
        while (true) {
            int[] iArr = this.f335c;
            if (i5 >= iArr.length) {
                break;
            }
            if (iArr[i5] != iArr[i5 - 1]) {
                i6++;
            }
            i5++;
        }
        if (i6 == 0) {
            i6 = 1;
        }
        double[] dArr = new double[i6];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, i6, 3);
        int i7 = 0;
        while (i3 < this.e) {
            if (i3 > 0) {
                int[] iArr2 = this.f335c;
                i3 = iArr2[i3] == iArr2[i3 + (-1)] ? i3 + 1 : 0;
            }
            double d2 = this.f335c[i3];
            Double.isNaN(d2);
            dArr[i7] = d2 * 0.01d;
            double[] dArr3 = dArr2[i7];
            float[][] fArr = this.f336d;
            dArr3[0] = fArr[i3][0];
            dArr2[i7][1] = fArr[i3][1];
            dArr2[i7][2] = fArr[i3][2];
            i7++;
        }
        this.a = CurveFit.get(i2, dArr, dArr2);
    }

    public String toString() {
        String str = this.f;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i2 = 0; i2 < this.e; i2++) {
            str = str + "[" + this.f335c[i2] + " , " + decimalFormat.format(this.f336d[i2]) + "] ";
        }
        return str;
    }
}
