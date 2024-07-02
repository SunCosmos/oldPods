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
import java.util.Arrays;

/* loaded from: classes.dex */
public abstract class SplineSet {
    protected CurveFit a;
    protected int[] b = new int[10];

    /* renamed from: c, reason: collision with root package name */
    protected float[] f333c = new float[10];

    /* renamed from: d, reason: collision with root package name */
    private int f334d;
    private String e;

    /* loaded from: classes.dex */
    static class a extends SplineSet {
        a() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            view.setAlpha(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class b extends SplineSet {
        SparseArray<ConstraintAttribute> f;
        float[] g;

        public b(String str, SparseArray<ConstraintAttribute> sparseArray) {
            String str2 = str.split(",")[1];
            this.f = sparseArray;
        }

        public void c(int i2, ConstraintAttribute constraintAttribute) {
            this.f.append(i2, constraintAttribute);
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setPoint(int i2, float f) {
            throw new RuntimeException("don't call for custom attribute call setPoint(pos, ConstraintAttribute)");
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            this.a.getPos(f, this.g);
            this.f.valueAt(0).setInterpolatedValue(view, this.g);
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setup(int i2) {
            int size = this.f.size();
            int noOfInterpValues = this.f.valueAt(0).noOfInterpValues();
            double[] dArr = new double[size];
            this.g = new float[noOfInterpValues];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, size, noOfInterpValues);
            for (int i3 = 0; i3 < size; i3++) {
                int keyAt = this.f.keyAt(i3);
                ConstraintAttribute valueAt = this.f.valueAt(i3);
                double d2 = keyAt;
                Double.isNaN(d2);
                dArr[i3] = d2 * 0.01d;
                valueAt.getValuesToInterpolate(this.g);
                int i4 = 0;
                while (true) {
                    if (i4 < this.g.length) {
                        dArr2[i3][i4] = r6[i4];
                        i4++;
                    }
                }
            }
            this.a = CurveFit.get(i2, dArr, dArr2);
        }
    }

    /* loaded from: classes.dex */
    static class c extends SplineSet {
        c() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(get(f));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d extends SplineSet {
        d() {
        }

        public void c(View view, float f, double d2, double d3) {
            view.setRotation(get(f) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
        }
    }

    /* loaded from: classes.dex */
    static class e extends SplineSet {
        e() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            view.setPivotX(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class f extends SplineSet {
        f() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            view.setPivotY(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class g extends SplineSet {
        boolean f = false;

        g() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(get(f));
                return;
            }
            if (this.f) {
                return;
            }
            Method method = null;
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f = true;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(get(f)));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    Log.e("SplineSet", "unable to setProgress", e);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    static class h extends SplineSet {
        h() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            view.setRotation(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class i extends SplineSet {
        i() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            view.setRotationX(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class j extends SplineSet {
        j() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            view.setRotationY(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class k extends SplineSet {
        k() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            view.setScaleX(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class l extends SplineSet {
        l() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            view.setScaleY(get(f));
        }
    }

    /* loaded from: classes.dex */
    private static class m {
        static void a(int[] iArr, float[] fArr, int i2, int i3) {
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

        private static int b(int[] iArr, float[] fArr, int i2, int i3) {
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

        private static void c(int[] iArr, float[] fArr, int i2, int i3) {
            int i4 = iArr[i2];
            iArr[i2] = iArr[i3];
            iArr[i3] = i4;
            float f = fArr[i2];
            fArr[i2] = fArr[i3];
            fArr[i3] = f;
        }
    }

    /* loaded from: classes.dex */
    static class n extends SplineSet {
        n() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            view.setTranslationX(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class o extends SplineSet {
        o() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            view.setTranslationY(get(f));
        }
    }

    /* loaded from: classes.dex */
    static class p extends SplineSet {
        p() {
        }

        @Override // androidx.constraintlayout.motion.widget.SplineSet
        public void setProperty(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(get(f));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SplineSet a(String str, SparseArray<ConstraintAttribute> sparseArray) {
        return new b(str, sparseArray);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SplineSet b(String str) {
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
            case -760884510:
                if (str.equals("transformPivotX")) {
                    c2 = '\t';
                    break;
                }
                break;
            case -760884509:
                if (str.equals("transformPivotY")) {
                    c2 = '\n';
                    break;
                }
                break;
            case -40300674:
                if (str.equals("rotation")) {
                    c2 = 11;
                    break;
                }
                break;
            case -4379043:
                if (str.equals("elevation")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 37232917:
                if (str.equals("transitionPathRotate")) {
                    c2 = '\r';
                    break;
                }
                break;
            case 92909918:
                if (str.equals("alpha")) {
                    c2 = 14;
                    break;
                }
                break;
            case 156108012:
                if (str.equals("waveOffset")) {
                    c2 = 15;
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
                return new n();
            case 3:
                return new o();
            case 4:
                return new p();
            case 5:
                return new g();
            case 6:
                return new k();
            case 7:
                return new l();
            case '\b':
                return new a();
            case '\t':
                return new e();
            case '\n':
                return new f();
            case 11:
                return new h();
            case '\f':
                return new c();
            case '\r':
                return new d();
            case 14:
                return new a();
            case 15:
                return new a();
            default:
                return null;
        }
    }

    public float get(float f2) {
        return (float) this.a.getPos(f2, 0);
    }

    public CurveFit getCurveFit() {
        return this.a;
    }

    public float getSlope(float f2) {
        return (float) this.a.getSlope(f2, 0);
    }

    public void setPoint(int i2, float f2) {
        int[] iArr = this.b;
        if (iArr.length < this.f334d + 1) {
            this.b = Arrays.copyOf(iArr, iArr.length * 2);
            float[] fArr = this.f333c;
            this.f333c = Arrays.copyOf(fArr, fArr.length * 2);
        }
        int[] iArr2 = this.b;
        int i3 = this.f334d;
        iArr2[i3] = i2;
        this.f333c[i3] = f2;
        this.f334d = i3 + 1;
    }

    public abstract void setProperty(View view, float f2);

    public void setType(String str) {
        this.e = str;
    }

    public void setup(int i2) {
        int i3;
        int i4 = this.f334d;
        if (i4 == 0) {
            return;
        }
        m.a(this.b, this.f333c, 0, i4 - 1);
        int i5 = 1;
        for (int i6 = 1; i6 < this.f334d; i6++) {
            int[] iArr = this.b;
            if (iArr[i6 - 1] != iArr[i6]) {
                i5++;
            }
        }
        double[] dArr = new double[i5];
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) double.class, i5, 1);
        int i7 = 0;
        while (i3 < this.f334d) {
            if (i3 > 0) {
                int[] iArr2 = this.b;
                i3 = iArr2[i3] == iArr2[i3 + (-1)] ? i3 + 1 : 0;
            }
            double d2 = this.b[i3];
            Double.isNaN(d2);
            dArr[i7] = d2 * 0.01d;
            dArr2[i7][0] = this.f333c[i3];
            i7++;
        }
        this.a = CurveFit.get(i2, dArr, dArr2);
    }

    public String toString() {
        String str = this.e;
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        for (int i2 = 0; i2 < this.f334d; i2++) {
            str = str + "[" + this.b[i2] + " , " + decimalFormat.format(this.f333c[i2]) + "] ";
        }
        return str;
    }
}
