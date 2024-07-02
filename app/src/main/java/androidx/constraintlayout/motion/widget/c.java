package androidx.constraintlayout.motion.widget;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.LinkedHashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c implements Comparable<c> {
    static String[] o = {"position", "x", "y", "width", "height", "pathRotate"};
    Easing a;
    int b;

    /* renamed from: c, reason: collision with root package name */
    float f341c;

    /* renamed from: d, reason: collision with root package name */
    float f342d;
    float e;
    float f;
    float g;
    float h;

    /* renamed from: i, reason: collision with root package name */
    float f343i;
    int j;
    LinkedHashMap<String, ConstraintAttribute> k;
    int l;
    double[] m;
    double[] n;

    public c() {
        this.b = 0;
        this.f343i = Float.NaN;
        this.j = Key.UNSET;
        this.k = new LinkedHashMap<>();
        this.l = 0;
        this.m = new double[18];
        this.n = new double[18];
    }

    public c(int i2, int i3, KeyPosition keyPosition, c cVar, c cVar2) {
        this.b = 0;
        this.f343i = Float.NaN;
        this.j = Key.UNSET;
        this.k = new LinkedHashMap<>();
        this.l = 0;
        this.m = new double[18];
        this.n = new double[18];
        int i4 = keyPosition.p;
        if (i4 == 1) {
            l(keyPosition, cVar, cVar2);
        } else if (i4 != 2) {
            k(keyPosition, cVar, cVar2);
        } else {
            m(i2, i3, keyPosition, cVar, cVar2);
        }
    }

    private boolean c(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    public void a(ConstraintSet.Constraint constraint) {
        this.a = Easing.getInterpolator(constraint.motion.mTransitionEasing);
        ConstraintSet.Motion motion = constraint.motion;
        this.j = motion.mPathMotionArc;
        this.f343i = motion.mPathRotate;
        this.b = motion.mDrawPath;
        float f = constraint.propertySet.mProgress;
        for (String str : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = constraint.mCustomConstraints.get(str);
            if (constraintAttribute.getType() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.k.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compareTo(@NonNull c cVar) {
        return Float.compare(this.f342d, cVar.f342d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(c cVar, boolean[] zArr, String[] strArr, boolean z) {
        zArr[0] = zArr[0] | c(this.f342d, cVar.f342d);
        zArr[1] = zArr[1] | c(this.e, cVar.e) | z;
        zArr[2] = z | c(this.f, cVar.f) | zArr[2];
        zArr[3] = zArr[3] | c(this.g, cVar.g);
        zArr[4] = c(this.h, cVar.h) | zArr[4];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(double[] dArr, int[] iArr) {
        float[] fArr = {this.f342d, this.e, this.f, this.g, this.h, this.f343i};
        int i2 = 0;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            if (iArr[i3] < 6) {
                dArr[i2] = fArr[iArr[i3]];
                i2++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f = this.e;
        float f2 = this.f;
        float f3 = this.g;
        float f4 = this.h;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f5 = (float) dArr[i3];
            int i4 = iArr[i3];
            if (i4 == 1) {
                f = f5;
            } else if (i4 == 2) {
                f2 = f5;
            } else if (i4 == 3) {
                f3 = f5;
            } else if (i4 == 4) {
                f4 = f5;
            }
        }
        fArr[i2] = f + (f3 / 2.0f) + 0.0f;
        fArr[i2 + 1] = f2 + (f4 / 2.0f) + 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g(String str, double[] dArr, int i2) {
        ConstraintAttribute constraintAttribute = this.k.get(str);
        if (constraintAttribute.noOfInterpValues() == 1) {
            dArr[i2] = constraintAttribute.getValueToInterpolate();
            return 1;
        }
        int noOfInterpValues = constraintAttribute.noOfInterpValues();
        constraintAttribute.getValuesToInterpolate(new float[noOfInterpValues]);
        int i3 = 0;
        while (i3 < noOfInterpValues) {
            dArr[i2] = r1[i3];
            i3++;
            i2++;
        }
        return noOfInterpValues;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int h(String str) {
        return this.k.get(str).noOfInterpValues();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(int[] iArr, double[] dArr, float[] fArr, int i2) {
        float f = this.e;
        float f2 = this.f;
        float f3 = this.g;
        float f4 = this.h;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            float f5 = (float) dArr[i3];
            int i4 = iArr[i3];
            if (i4 == 1) {
                f = f5;
            } else if (i4 == 2) {
                f2 = f5;
            } else if (i4 == 3) {
                f3 = f5;
            } else if (i4 == 4) {
                f4 = f5;
            }
        }
        float f6 = f3 + f;
        float f7 = f4 + f2;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i5 = i2 + 1;
        fArr[i2] = f + 0.0f;
        int i6 = i5 + 1;
        fArr[i5] = f2 + 0.0f;
        int i7 = i6 + 1;
        fArr[i6] = f6 + 0.0f;
        int i8 = i7 + 1;
        fArr[i7] = f2 + 0.0f;
        int i9 = i8 + 1;
        fArr[i8] = f6 + 0.0f;
        int i10 = i9 + 1;
        fArr[i9] = f7 + 0.0f;
        fArr[i10] = f + 0.0f;
        fArr[i10 + 1] = f7 + 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean j(String str) {
        return this.k.containsKey(str);
    }

    void k(KeyPosition keyPosition, c cVar, c cVar2) {
        float f = keyPosition.a / 100.0f;
        this.f341c = f;
        this.b = keyPosition.f309i;
        float f2 = Float.isNaN(keyPosition.j) ? f : keyPosition.j;
        float f3 = Float.isNaN(keyPosition.k) ? f : keyPosition.k;
        float f4 = cVar2.g;
        float f5 = cVar.g;
        float f6 = cVar2.h;
        float f7 = cVar.h;
        this.f342d = this.f341c;
        float f8 = cVar.e;
        float f9 = cVar.f;
        float f10 = (cVar2.e + (f4 / 2.0f)) - ((f5 / 2.0f) + f8);
        float f11 = (cVar2.f + (f6 / 2.0f)) - (f9 + (f7 / 2.0f));
        float f12 = ((f4 - f5) * f2) / 2.0f;
        this.e = (int) ((f8 + (f10 * f)) - f12);
        float f13 = ((f6 - f7) * f3) / 2.0f;
        this.f = (int) ((f9 + (f11 * f)) - f13);
        this.g = (int) (f5 + r9);
        this.h = (int) (f7 + r12);
        float f14 = Float.isNaN(keyPosition.l) ? f : keyPosition.l;
        float f15 = Float.isNaN(keyPosition.o) ? 0.0f : keyPosition.o;
        if (!Float.isNaN(keyPosition.m)) {
            f = keyPosition.m;
        }
        float f16 = Float.isNaN(keyPosition.n) ? 0.0f : keyPosition.n;
        this.l = 2;
        this.e = (int) (((cVar.e + (f14 * f10)) + (f16 * f11)) - f12);
        this.f = (int) (((cVar.f + (f10 * f15)) + (f11 * f)) - f13);
        this.a = Easing.getInterpolator(keyPosition.g);
        this.j = keyPosition.h;
    }

    void l(KeyPosition keyPosition, c cVar, c cVar2) {
        float f = keyPosition.a / 100.0f;
        this.f341c = f;
        this.b = keyPosition.f309i;
        float f2 = Float.isNaN(keyPosition.j) ? f : keyPosition.j;
        float f3 = Float.isNaN(keyPosition.k) ? f : keyPosition.k;
        float f4 = cVar2.g - cVar.g;
        float f5 = cVar2.h - cVar.h;
        this.f342d = this.f341c;
        if (!Float.isNaN(keyPosition.l)) {
            f = keyPosition.l;
        }
        float f6 = cVar.e;
        float f7 = cVar.g;
        float f8 = cVar.f;
        float f9 = cVar.h;
        float f10 = (cVar2.e + (cVar2.g / 2.0f)) - ((f7 / 2.0f) + f6);
        float f11 = (cVar2.f + (cVar2.h / 2.0f)) - ((f9 / 2.0f) + f8);
        float f12 = f10 * f;
        float f13 = (f4 * f2) / 2.0f;
        this.e = (int) ((f6 + f12) - f13);
        float f14 = f * f11;
        float f15 = (f5 * f3) / 2.0f;
        this.f = (int) ((f8 + f14) - f15);
        this.g = (int) (f7 + r7);
        this.h = (int) (f9 + r8);
        float f16 = Float.isNaN(keyPosition.m) ? 0.0f : keyPosition.m;
        this.l = 1;
        float f17 = (int) ((cVar.e + f12) - f13);
        this.e = f17;
        float f18 = (int) ((cVar.f + f14) - f15);
        this.f = f18;
        this.e = f17 + ((-f11) * f16);
        this.f = f18 + (f10 * f16);
        this.a = Easing.getInterpolator(keyPosition.g);
        this.j = keyPosition.h;
    }

    void m(int i2, int i3, KeyPosition keyPosition, c cVar, c cVar2) {
        float f = keyPosition.a / 100.0f;
        this.f341c = f;
        this.b = keyPosition.f309i;
        float f2 = Float.isNaN(keyPosition.j) ? f : keyPosition.j;
        float f3 = Float.isNaN(keyPosition.k) ? f : keyPosition.k;
        float f4 = cVar2.g;
        float f5 = cVar.g;
        float f6 = cVar2.h;
        float f7 = cVar.h;
        this.f342d = this.f341c;
        float f8 = cVar.e;
        float f9 = cVar.f;
        float f10 = cVar2.e + (f4 / 2.0f);
        float f11 = cVar2.f + (f6 / 2.0f);
        float f12 = (f4 - f5) * f2;
        this.e = (int) ((f8 + ((f10 - ((f5 / 2.0f) + f8)) * f)) - (f12 / 2.0f));
        float f13 = (f6 - f7) * f3;
        this.f = (int) ((f9 + ((f11 - (f9 + (f7 / 2.0f))) * f)) - (f13 / 2.0f));
        this.g = (int) (f5 + f12);
        this.h = (int) (f7 + f13);
        this.l = 3;
        if (!Float.isNaN(keyPosition.l)) {
            this.e = (int) (keyPosition.l * ((int) (i2 - this.g)));
        }
        if (!Float.isNaN(keyPosition.m)) {
            this.f = (int) (keyPosition.m * ((int) (i3 - this.h)));
        }
        this.a = Easing.getInterpolator(keyPosition.g);
        this.j = keyPosition.h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(float f, float f2, float f3, float f4) {
        this.e = f;
        this.f = f2;
        this.g = f3;
        this.h = f4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(float f, float f2, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f7 = (float) dArr[i2];
            double d2 = dArr2[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f3 = f7;
            } else if (i3 == 2) {
                f5 = f7;
            } else if (i3 == 3) {
                f4 = f7;
            } else if (i3 == 4) {
                f6 = f7;
            }
        }
        float f8 = f3 - ((0.0f * f4) / 2.0f);
        float f9 = f5 - ((0.0f * f6) / 2.0f);
        fArr[0] = (f8 * (1.0f - f)) + (((f4 * 1.0f) + f8) * f) + 0.0f;
        fArr[1] = (f9 * (1.0f - f2)) + (((f6 * 1.0f) + f9) * f2) + 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00bb, code lost:
    
        if (java.lang.Float.isNaN(Float.NaN) == false) goto L47;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void p(android.view.View r22, int[] r23, double[] r24, double[] r25, double[] r26) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.c.p(android.view.View, int[], double[], double[], double[]):void");
    }
}
