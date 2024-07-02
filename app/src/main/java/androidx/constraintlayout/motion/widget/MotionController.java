package androidx.constraintlayout.motion.widget;

import android.graphics.RectF;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.utils.CurveFit;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.motion.utils.VelocityMatrix;
import androidx.constraintlayout.motion.widget.KeyCycleOscillator;
import androidx.constraintlayout.motion.widget.SplineSet;
import androidx.constraintlayout.motion.widget.TimeCycleSplineSet;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class MotionController {
    public static final int DRAW_PATH_AS_CONFIGURED = 4;
    public static final int DRAW_PATH_BASIC = 1;
    public static final int DRAW_PATH_CARTESIAN = 3;
    public static final int DRAW_PATH_NONE = 0;
    public static final int DRAW_PATH_RECTANGLE = 5;
    public static final int DRAW_PATH_RELATIVE = 2;
    public static final int DRAW_PATH_SCREEN = 6;
    public static final int HORIZONTAL_PATH_X = 2;
    public static final int HORIZONTAL_PATH_Y = 3;
    public static final int PATH_PERCENT = 0;
    public static final int PATH_PERPENDICULAR = 1;
    public static final int VERTICAL_PATH_X = 4;
    public static final int VERTICAL_PATH_Y = 5;
    View a;
    int b;
    private CurveFit[] h;

    /* renamed from: i, reason: collision with root package name */
    private CurveFit f314i;
    private int[] m;
    private double[] n;
    private double[] o;
    private String[] p;
    private int[] q;
    private HashMap<String, TimeCycleSplineSet> w;
    private HashMap<String, SplineSet> x;
    private HashMap<String, KeyCycleOscillator> y;
    private KeyTrigger[] z;

    /* renamed from: c, reason: collision with root package name */
    private int f312c = -1;

    /* renamed from: d, reason: collision with root package name */
    private c f313d = new c();
    private c e = new c();
    private b f = new b();
    private b g = new b();
    float j = Float.NaN;
    float k = 0.0f;
    float l = 1.0f;
    private int r = 4;
    private float[] s = new float[4];
    private ArrayList<c> t = new ArrayList<>();
    private float[] u = new float[1];
    private ArrayList<Key> v = new ArrayList<>();
    private int A = Key.UNSET;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MotionController(View view) {
        setView(view);
    }

    private float g(float f, float[] fArr) {
        float f2 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f3 = this.l;
            if (f3 != 1.0d) {
                float f4 = this.k;
                if (f < f4) {
                    f = 0.0f;
                }
                if (f > f4 && f < 1.0d) {
                    f = (f - f4) * f3;
                }
            }
        }
        Easing easing = this.f313d.a;
        float f5 = Float.NaN;
        Iterator<c> it = this.t.iterator();
        while (it.hasNext()) {
            c next = it.next();
            Easing easing2 = next.a;
            if (easing2 != null) {
                float f6 = next.f341c;
                if (f6 < f) {
                    easing = easing2;
                    f2 = f6;
                } else if (Float.isNaN(f5)) {
                    f5 = next.f341c;
                }
            }
        }
        if (easing != null) {
            float f7 = (Float.isNaN(f5) ? 1.0f : f5) - f2;
            double d2 = (f - f2) / f7;
            f = (((float) easing.get(d2)) * f7) + f2;
            if (fArr != null) {
                fArr[0] = (float) easing.getDiff(d2);
            }
        }
        return f;
    }

    private float p() {
        float[] fArr = new float[2];
        float f = 1.0f / 99;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i2 = 0;
        float f2 = 0.0f;
        while (i2 < 100) {
            float f3 = i2 * f;
            double d4 = f3;
            Easing easing = this.f313d.a;
            float f4 = Float.NaN;
            Iterator<c> it = this.t.iterator();
            float f5 = 0.0f;
            while (it.hasNext()) {
                c next = it.next();
                Easing easing2 = next.a;
                float f6 = f;
                if (easing2 != null) {
                    float f7 = next.f341c;
                    if (f7 < f3) {
                        f5 = f7;
                        easing = easing2;
                    } else if (Float.isNaN(f4)) {
                        f4 = next.f341c;
                    }
                }
                f = f6;
            }
            float f8 = f;
            if (easing != null) {
                if (Float.isNaN(f4)) {
                    f4 = 1.0f;
                }
                d4 = (((float) easing.get((f3 - f5) / r16)) * (f4 - f5)) + f5;
            }
            this.h[0].getPos(d4, this.n);
            this.f313d.f(this.m, this.n, fArr, 0);
            if (i2 > 0) {
                double d5 = f2;
                double d6 = fArr[1];
                Double.isNaN(d6);
                double d7 = d3 - d6;
                double d8 = fArr[0];
                Double.isNaN(d8);
                double hypot = Math.hypot(d7, d2 - d8);
                Double.isNaN(d5);
                f2 = (float) (d5 + hypot);
            }
            d2 = fArr[0];
            d3 = fArr[1];
            i2++;
            f = f8;
        }
        return f2;
    }

    private void q(c cVar) {
        if (Collections.binarySearch(this.t, cVar) == 0) {
            Log.e("MotionController", " KeyPath positon \"" + cVar.f342d + "\" outside of range");
        }
        this.t.add((-r0) - 1, cVar);
    }

    private void t(c cVar) {
        cVar.n((int) this.a.getX(), (int) this.a.getY(), this.a.getWidth(), this.a.getHeight());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Key key) {
        this.v.add(key);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(ArrayList<Key> arrayList) {
        this.v.addAll(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] timePoints = this.h[0].getTimePoints();
        if (iArr != null) {
            Iterator<c> it = this.t.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                iArr[i2] = it.next().l;
                i2++;
            }
        }
        int i3 = 0;
        for (double d2 : timePoints) {
            this.h[0].getPos(d2, this.n);
            this.f313d.f(this.m, this.n, fArr, i3);
            i3 += 2;
        }
        return i3 / 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00e5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(float[] r22, int r23) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionController.d(float[], int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(float f, float[] fArr, int i2) {
        this.h[0].getPos(g(f, null), this.n);
        this.f313d.i(this.m, this.n, fArr, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(float[] fArr, int i2) {
        float f = 1.0f / (i2 - 1);
        for (int i3 = 0; i3 < i2; i3++) {
            this.h[0].getPos(g(i3 * f, null), this.n);
            this.f313d.i(this.m, this.n, fArr, i3 * 8);
        }
    }

    public int getDrawPath() {
        int i2 = this.f313d.b;
        Iterator<c> it = this.t.iterator();
        while (it.hasNext()) {
            i2 = Math.max(i2, it.next().b);
        }
        return Math.max(i2, this.e.b);
    }

    public int getKeyFrameInfo(int i2, int[] iArr) {
        float[] fArr = new float[2];
        Iterator<Key> it = this.v.iterator();
        int i3 = 0;
        int i4 = 0;
        while (it.hasNext()) {
            Key next = it.next();
            int i5 = next.f300d;
            if (i5 == i2 || i2 != -1) {
                iArr[i4] = 0;
                int i6 = i4 + 1;
                iArr[i6] = i5;
                int i7 = i6 + 1;
                iArr[i7] = next.a;
                this.h[0].getPos(r8 / 100.0f, this.n);
                this.f313d.f(this.m, this.n, fArr, 0);
                int i8 = i7 + 1;
                iArr[i8] = Float.floatToIntBits(fArr[0]);
                int i9 = i8 + 1;
                iArr[i9] = Float.floatToIntBits(fArr[1]);
                if (next instanceof KeyPosition) {
                    KeyPosition keyPosition = (KeyPosition) next;
                    int i10 = i9 + 1;
                    iArr[i10] = keyPosition.p;
                    int i11 = i10 + 1;
                    iArr[i11] = Float.floatToIntBits(keyPosition.l);
                    i9 = i11 + 1;
                    iArr[i9] = Float.floatToIntBits(keyPosition.m);
                }
                int i12 = i9 + 1;
                iArr[i4] = i12 - i4;
                i3++;
                i4 = i12;
            }
        }
        return i3;
    }

    public int getkeyFramePositions(int[] iArr, float[] fArr) {
        Iterator<Key> it = this.v.iterator();
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            Key next = it.next();
            iArr[i2] = (next.f300d * 1000) + next.a;
            this.h[0].getPos(r6 / 100.0f, this.n);
            this.f313d.f(this.m, this.n, fArr, i3);
            i3 += 2;
            i2++;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int h(String str, float[] fArr, int i2) {
        SplineSet splineSet = this.x.get(str);
        if (splineSet == null) {
            return -1;
        }
        for (int i3 = 0; i3 < fArr.length; i3++) {
            fArr[i3] = splineSet.get(i3 / (fArr.length - 1));
        }
        return fArr.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float g = g(f, this.u);
        CurveFit[] curveFitArr = this.h;
        int i2 = 0;
        if (curveFitArr == null) {
            c cVar = this.e;
            float f4 = cVar.e;
            c cVar2 = this.f313d;
            float f5 = f4 - cVar2.e;
            float f6 = cVar.f - cVar2.f;
            float f7 = (cVar.g - cVar2.g) + f5;
            float f8 = (cVar.h - cVar2.h) + f6;
            fArr[0] = (f5 * (1.0f - f2)) + (f7 * f2);
            fArr[1] = (f6 * (1.0f - f3)) + (f8 * f3);
            return;
        }
        double d2 = g;
        curveFitArr[0].getSlope(d2, this.o);
        this.h[0].getPos(d2, this.n);
        float f9 = this.u[0];
        while (true) {
            dArr = this.o;
            if (i2 >= dArr.length) {
                break;
            }
            double d3 = dArr[i2];
            double d4 = f9;
            Double.isNaN(d4);
            dArr[i2] = d3 * d4;
            i2++;
        }
        CurveFit curveFit = this.f314i;
        if (curveFit == null) {
            this.f313d.o(f2, f3, fArr, this.m, dArr, this.n);
            return;
        }
        double[] dArr2 = this.n;
        if (dArr2.length > 0) {
            curveFit.getPos(d2, dArr2);
            this.f314i.getSlope(d2, this.o);
            this.f313d.o(f2, f3, fArr, this.m, this.o, this.n);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float j() {
        return this.e.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float k() {
        return this.e.f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c l(int i2) {
        return this.t.get(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float m(int i2, float f, float f2) {
        c cVar = this.e;
        float f3 = cVar.e;
        c cVar2 = this.f313d;
        float f4 = cVar2.e;
        float f5 = f3 - f4;
        float f6 = cVar.f;
        float f7 = cVar2.f;
        float f8 = f6 - f7;
        float f9 = f4 + (cVar2.g / 2.0f);
        float f10 = f7 + (cVar2.h / 2.0f);
        float hypot = (float) Math.hypot(f5, f8);
        if (hypot < 1.0E-7d) {
            return Float.NaN;
        }
        float f11 = f - f9;
        float f12 = f2 - f10;
        if (((float) Math.hypot(f11, f12)) == 0.0f) {
            return 0.0f;
        }
        float f13 = (f11 * f5) + (f12 * f8);
        if (i2 == 0) {
            return f13 / hypot;
        }
        if (i2 == 1) {
            return (float) Math.sqrt((hypot * hypot) - (f13 * f13));
        }
        if (i2 == 2) {
            return f11 / f5;
        }
        if (i2 == 3) {
            return f12 / f5;
        }
        if (i2 == 4) {
            return f11 / f8;
        }
        if (i2 != 5) {
            return 0.0f;
        }
        return f12 / f8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a n(int i2, int i3, float f, float f2) {
        RectF rectF = new RectF();
        c cVar = this.f313d;
        float f3 = cVar.e;
        rectF.left = f3;
        float f4 = cVar.f;
        rectF.top = f4;
        rectF.right = f3 + cVar.g;
        rectF.bottom = f4 + cVar.h;
        RectF rectF2 = new RectF();
        c cVar2 = this.e;
        float f5 = cVar2.e;
        rectF2.left = f5;
        float f6 = cVar2.f;
        rectF2.top = f6;
        rectF2.right = f5 + cVar2.g;
        rectF2.bottom = f6 + cVar2.h;
        Iterator<Key> it = this.v.iterator();
        while (it.hasNext()) {
            Key next = it.next();
            if (next instanceof a) {
                a aVar = (a) next;
                if (aVar.intersects(i2, i3, rectF, rectF2, f, f2)) {
                    return aVar;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(float f, int i2, int i3, float f2, float f3, float[] fArr) {
        float g = g(f, this.u);
        HashMap<String, SplineSet> hashMap = this.x;
        SplineSet splineSet = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, SplineSet> hashMap2 = this.x;
        SplineSet splineSet2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, SplineSet> hashMap3 = this.x;
        SplineSet splineSet3 = hashMap3 == null ? null : hashMap3.get("rotation");
        HashMap<String, SplineSet> hashMap4 = this.x;
        SplineSet splineSet4 = hashMap4 == null ? null : hashMap4.get("scaleX");
        HashMap<String, SplineSet> hashMap5 = this.x;
        SplineSet splineSet5 = hashMap5 == null ? null : hashMap5.get("scaleY");
        HashMap<String, KeyCycleOscillator> hashMap6 = this.y;
        KeyCycleOscillator keyCycleOscillator = hashMap6 == null ? null : hashMap6.get("translationX");
        HashMap<String, KeyCycleOscillator> hashMap7 = this.y;
        KeyCycleOscillator keyCycleOscillator2 = hashMap7 == null ? null : hashMap7.get("translationY");
        HashMap<String, KeyCycleOscillator> hashMap8 = this.y;
        KeyCycleOscillator keyCycleOscillator3 = hashMap8 == null ? null : hashMap8.get("rotation");
        HashMap<String, KeyCycleOscillator> hashMap9 = this.y;
        KeyCycleOscillator keyCycleOscillator4 = hashMap9 == null ? null : hashMap9.get("scaleX");
        HashMap<String, KeyCycleOscillator> hashMap10 = this.y;
        KeyCycleOscillator keyCycleOscillator5 = hashMap10 != null ? hashMap10.get("scaleY") : null;
        VelocityMatrix velocityMatrix = new VelocityMatrix();
        velocityMatrix.clear();
        velocityMatrix.setRotationVelocity(splineSet3, g);
        velocityMatrix.setTranslationVelocity(splineSet, splineSet2, g);
        velocityMatrix.setScaleVelocity(splineSet4, splineSet5, g);
        velocityMatrix.setRotationVelocity(keyCycleOscillator3, g);
        velocityMatrix.setTranslationVelocity(keyCycleOscillator, keyCycleOscillator2, g);
        velocityMatrix.setScaleVelocity(keyCycleOscillator4, keyCycleOscillator5, g);
        CurveFit curveFit = this.f314i;
        if (curveFit != null) {
            double[] dArr = this.n;
            if (dArr.length > 0) {
                double d2 = g;
                curveFit.getPos(d2, dArr);
                this.f314i.getSlope(d2, this.o);
                this.f313d.o(f2, f3, fArr, this.m, this.o, this.n);
            }
            velocityMatrix.applyTransform(f2, f3, i2, i3, fArr);
            return;
        }
        int i4 = 0;
        if (this.h == null) {
            c cVar = this.e;
            float f4 = cVar.e;
            c cVar2 = this.f313d;
            float f5 = f4 - cVar2.e;
            KeyCycleOscillator keyCycleOscillator6 = keyCycleOscillator5;
            float f6 = cVar.f - cVar2.f;
            KeyCycleOscillator keyCycleOscillator7 = keyCycleOscillator4;
            float f7 = (cVar.g - cVar2.g) + f5;
            float f8 = (cVar.h - cVar2.h) + f6;
            fArr[0] = (f5 * (1.0f - f2)) + (f7 * f2);
            fArr[1] = (f6 * (1.0f - f3)) + (f8 * f3);
            velocityMatrix.clear();
            velocityMatrix.setRotationVelocity(splineSet3, g);
            velocityMatrix.setTranslationVelocity(splineSet, splineSet2, g);
            velocityMatrix.setScaleVelocity(splineSet4, splineSet5, g);
            velocityMatrix.setRotationVelocity(keyCycleOscillator3, g);
            velocityMatrix.setTranslationVelocity(keyCycleOscillator, keyCycleOscillator2, g);
            velocityMatrix.setScaleVelocity(keyCycleOscillator7, keyCycleOscillator6, g);
            velocityMatrix.applyTransform(f2, f3, i2, i3, fArr);
            return;
        }
        double g2 = g(g, this.u);
        this.h[0].getSlope(g2, this.o);
        this.h[0].getPos(g2, this.n);
        float f9 = this.u[0];
        while (true) {
            double[] dArr2 = this.o;
            if (i4 >= dArr2.length) {
                this.f313d.o(f2, f3, fArr, this.m, dArr2, this.n);
                velocityMatrix.applyTransform(f2, f3, i2, i3, fArr);
                return;
            } else {
                double d3 = dArr2[i4];
                double d4 = f9;
                Double.isNaN(d4);
                dArr2[i4] = d3 * d4;
                i4++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean r(View view, float f, long j, KeyCache keyCache) {
        TimeCycleSplineSet.d dVar;
        boolean z;
        double d2;
        float g = g(f, null);
        HashMap<String, SplineSet> hashMap = this.x;
        if (hashMap != null) {
            Iterator<SplineSet> it = hashMap.values().iterator();
            while (it.hasNext()) {
                it.next().setProperty(view, g);
            }
        }
        HashMap<String, TimeCycleSplineSet> hashMap2 = this.w;
        if (hashMap2 != null) {
            dVar = null;
            boolean z2 = false;
            for (TimeCycleSplineSet timeCycleSplineSet : hashMap2.values()) {
                if (timeCycleSplineSet instanceof TimeCycleSplineSet.d) {
                    dVar = (TimeCycleSplineSet.d) timeCycleSplineSet;
                } else {
                    z2 |= timeCycleSplineSet.setProperty(view, g, j, keyCache);
                }
            }
            z = z2;
        } else {
            dVar = null;
            z = false;
        }
        CurveFit[] curveFitArr = this.h;
        if (curveFitArr != null) {
            double d3 = g;
            curveFitArr[0].getPos(d3, this.n);
            this.h[0].getSlope(d3, this.o);
            CurveFit curveFit = this.f314i;
            if (curveFit != null) {
                double[] dArr = this.n;
                if (dArr.length > 0) {
                    curveFit.getPos(d3, dArr);
                    this.f314i.getSlope(d3, this.o);
                }
            }
            this.f313d.p(view, this.m, this.n, this.o, null);
            HashMap<String, SplineSet> hashMap3 = this.x;
            if (hashMap3 != null) {
                for (SplineSet splineSet : hashMap3.values()) {
                    if (splineSet instanceof SplineSet.d) {
                        double[] dArr2 = this.o;
                        ((SplineSet.d) splineSet).c(view, g, dArr2[0], dArr2[1]);
                    }
                }
            }
            if (dVar != null) {
                double[] dArr3 = this.o;
                d2 = d3;
                z = dVar.e(view, keyCache, g, j, dArr3[0], dArr3[1]) | z;
            } else {
                d2 = d3;
            }
            int i2 = 1;
            while (true) {
                CurveFit[] curveFitArr2 = this.h;
                if (i2 >= curveFitArr2.length) {
                    break;
                }
                curveFitArr2[i2].getPos(d2, this.s);
                this.f313d.k.get(this.p[i2 - 1]).setInterpolatedValue(view, this.s);
                i2++;
            }
            b bVar = this.f;
            if (bVar.b == 0) {
                if (g > 0.0f) {
                    if (g >= 1.0f) {
                        bVar = this.g;
                    } else if (this.g.f338c != bVar.f338c) {
                        view.setVisibility(0);
                    }
                }
                view.setVisibility(bVar.f338c);
            }
            if (this.z != null) {
                int i3 = 0;
                while (true) {
                    KeyTrigger[] keyTriggerArr = this.z;
                    if (i3 >= keyTriggerArr.length) {
                        break;
                    }
                    keyTriggerArr[i3].conditionallyFire(g, view);
                    i3++;
                }
            }
        } else {
            c cVar = this.f313d;
            float f2 = cVar.e;
            c cVar2 = this.e;
            float f3 = f2 + ((cVar2.e - f2) * g);
            float f4 = cVar.f;
            float f5 = f4 + ((cVar2.f - f4) * g);
            float f6 = cVar.g;
            float f7 = cVar2.g;
            float f8 = cVar.h;
            float f9 = cVar2.h;
            float f10 = f3 + 0.5f;
            int i4 = (int) f10;
            float f11 = f5 + 0.5f;
            int i5 = (int) f11;
            int i6 = (int) (f10 + ((f7 - f6) * g) + f6);
            int i7 = (int) (f11 + ((f9 - f8) * g) + f8);
            int i8 = i6 - i4;
            int i9 = i7 - i5;
            if (f7 != f6 || f9 != f8) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i8, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(i9, BasicMeasure.EXACTLY));
            }
            view.layout(i4, i5, i6, i7);
        }
        HashMap<String, KeyCycleOscillator> hashMap4 = this.y;
        if (hashMap4 != null) {
            for (KeyCycleOscillator keyCycleOscillator : hashMap4.values()) {
                if (keyCycleOscillator instanceof KeyCycleOscillator.f) {
                    double[] dArr4 = this.o;
                    ((KeyCycleOscillator.f) keyCycleOscillator).b(view, g, dArr4[0], dArr4[1]);
                } else {
                    keyCycleOscillator.setProperty(view, g);
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(View view, a aVar, float f, float f2, String[] strArr, float[] fArr) {
        RectF rectF = new RectF();
        c cVar = this.f313d;
        float f3 = cVar.e;
        rectF.left = f3;
        float f4 = cVar.f;
        rectF.top = f4;
        rectF.right = f3 + cVar.g;
        rectF.bottom = f4 + cVar.h;
        RectF rectF2 = new RectF();
        c cVar2 = this.e;
        float f5 = cVar2.e;
        rectF2.left = f5;
        float f6 = cVar2.f;
        rectF2.top = f6;
        rectF2.right = f5 + cVar2.g;
        rectF2.bottom = f6 + cVar2.h;
        aVar.positionAttributes(view, rectF, rectF2, f, f2, strArr, fArr);
    }

    public void setDrawPath(int i2) {
        this.f313d.b = i2;
    }

    public void setPathMotionArc(int i2) {
        this.A = i2;
    }

    public void setView(View view) {
        this.a = view;
        this.b = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ConstraintLayout.LayoutParams) layoutParams).getConstraintTag();
        }
    }

    public void setup(int i2, int i3, float f, long j) {
        ArrayList arrayList;
        String[] strArr;
        TimeCycleSplineSet c2;
        ConstraintAttribute constraintAttribute;
        SplineSet b;
        ConstraintAttribute constraintAttribute2;
        new HashSet();
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        HashSet<String> hashSet3 = new HashSet<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        int i4 = this.A;
        if (i4 != Key.UNSET) {
            this.f313d.j = i4;
        }
        this.f.f(this.g, hashSet2);
        ArrayList<Key> arrayList2 = this.v;
        if (arrayList2 != null) {
            Iterator<Key> it = arrayList2.iterator();
            arrayList = null;
            while (it.hasNext()) {
                Key next = it.next();
                if (next instanceof KeyPosition) {
                    KeyPosition keyPosition = (KeyPosition) next;
                    q(new c(i2, i3, keyPosition, this.f313d, this.e));
                    int i5 = keyPosition.f;
                    if (i5 != Key.UNSET) {
                        this.f312c = i5;
                    }
                } else if (next instanceof KeyCycle) {
                    next.getAttributeNames(hashSet3);
                } else if (next instanceof KeyTimeCycle) {
                    next.getAttributeNames(hashSet);
                } else if (next instanceof KeyTrigger) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((KeyTrigger) next);
                } else {
                    next.setInterpolation(hashMap);
                    next.getAttributeNames(hashSet2);
                }
            }
        } else {
            arrayList = null;
        }
        char c3 = 0;
        if (arrayList != null) {
            this.z = (KeyTrigger[]) arrayList.toArray(new KeyTrigger[0]);
        }
        char c4 = 1;
        if (!hashSet2.isEmpty()) {
            this.x = new HashMap<>();
            Iterator<String> it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                String next2 = it2.next();
                if (next2.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    String str = next2.split(",")[c4];
                    Iterator<Key> it3 = this.v.iterator();
                    while (it3.hasNext()) {
                        Key next3 = it3.next();
                        HashMap<String, ConstraintAttribute> hashMap2 = next3.e;
                        if (hashMap2 != null && (constraintAttribute2 = hashMap2.get(str)) != null) {
                            sparseArray.append(next3.a, constraintAttribute2);
                        }
                    }
                    b = SplineSet.a(next2, sparseArray);
                } else {
                    b = SplineSet.b(next2);
                }
                if (b != null) {
                    b.setType(next2);
                    this.x.put(next2, b);
                }
                c4 = 1;
            }
            ArrayList<Key> arrayList3 = this.v;
            if (arrayList3 != null) {
                Iterator<Key> it4 = arrayList3.iterator();
                while (it4.hasNext()) {
                    Key next4 = it4.next();
                    if (next4 instanceof KeyAttributes) {
                        next4.addValues(this.x);
                    }
                }
            }
            this.f.a(this.x, 0);
            this.g.a(this.x, 100);
            for (String str2 : this.x.keySet()) {
                this.x.get(str2).setup(hashMap.containsKey(str2) ? hashMap.get(str2).intValue() : 0);
            }
        }
        if (!hashSet.isEmpty()) {
            if (this.w == null) {
                this.w = new HashMap<>();
            }
            Iterator<String> it5 = hashSet.iterator();
            while (it5.hasNext()) {
                String next5 = it5.next();
                if (!this.w.containsKey(next5)) {
                    if (next5.startsWith("CUSTOM,")) {
                        SparseArray sparseArray2 = new SparseArray();
                        String str3 = next5.split(",")[1];
                        Iterator<Key> it6 = this.v.iterator();
                        while (it6.hasNext()) {
                            Key next6 = it6.next();
                            HashMap<String, ConstraintAttribute> hashMap3 = next6.e;
                            if (hashMap3 != null && (constraintAttribute = hashMap3.get(str3)) != null) {
                                sparseArray2.append(next6.a, constraintAttribute);
                            }
                        }
                        c2 = TimeCycleSplineSet.b(next5, sparseArray2);
                    } else {
                        c2 = TimeCycleSplineSet.c(next5, j);
                    }
                    if (c2 != null) {
                        c2.setType(next5);
                        this.w.put(next5, c2);
                    }
                }
            }
            ArrayList<Key> arrayList4 = this.v;
            if (arrayList4 != null) {
                Iterator<Key> it7 = arrayList4.iterator();
                while (it7.hasNext()) {
                    Key next7 = it7.next();
                    if (next7 instanceof KeyTimeCycle) {
                        ((KeyTimeCycle) next7).addTimeValues(this.w);
                    }
                }
            }
            for (String str4 : this.w.keySet()) {
                this.w.get(str4).setup(hashMap.containsKey(str4) ? hashMap.get(str4).intValue() : 0);
            }
        }
        int i6 = 2;
        int size = this.t.size() + 2;
        c[] cVarArr = new c[size];
        cVarArr[0] = this.f313d;
        cVarArr[size - 1] = this.e;
        if (this.t.size() > 0 && this.f312c == -1) {
            this.f312c = 0;
        }
        Iterator<c> it8 = this.t.iterator();
        int i7 = 1;
        while (it8.hasNext()) {
            cVarArr[i7] = it8.next();
            i7++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str5 : this.e.k.keySet()) {
            if (this.f313d.k.containsKey(str5)) {
                if (!hashSet2.contains("CUSTOM," + str5)) {
                    hashSet4.add(str5);
                }
            }
        }
        String[] strArr2 = (String[]) hashSet4.toArray(new String[0]);
        this.p = strArr2;
        this.q = new int[strArr2.length];
        int i8 = 0;
        while (true) {
            strArr = this.p;
            if (i8 >= strArr.length) {
                break;
            }
            String str6 = strArr[i8];
            this.q[i8] = 0;
            int i9 = 0;
            while (true) {
                if (i9 >= size) {
                    break;
                }
                if (cVarArr[i9].k.containsKey(str6)) {
                    int[] iArr = this.q;
                    iArr[i8] = iArr[i8] + cVarArr[i9].k.get(str6).noOfInterpValues();
                    break;
                }
                i9++;
            }
            i8++;
        }
        boolean z = cVarArr[0].j != Key.UNSET;
        int length = 18 + strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i10 = 1; i10 < size; i10++) {
            cVarArr[i10].d(cVarArr[i10 - 1], zArr, this.p, z);
        }
        int i11 = 0;
        for (int i12 = 1; i12 < length; i12++) {
            if (zArr[i12]) {
                i11++;
            }
        }
        int[] iArr2 = new int[i11];
        this.m = iArr2;
        this.n = new double[iArr2.length];
        this.o = new double[iArr2.length];
        int i13 = 0;
        for (int i14 = 1; i14 < length; i14++) {
            if (zArr[i14]) {
                this.m[i13] = i14;
                i13++;
            }
        }
        double[][] dArr = (double[][]) Array.newInstance((Class<?>) double.class, size, this.m.length);
        double[] dArr2 = new double[size];
        for (int i15 = 0; i15 < size; i15++) {
            cVarArr[i15].e(dArr[i15], this.m);
            dArr2[i15] = cVarArr[i15].f341c;
        }
        int i16 = 0;
        while (true) {
            int[] iArr3 = this.m;
            if (i16 >= iArr3.length) {
                break;
            }
            if (iArr3[i16] < c.o.length) {
                String str7 = c.o[this.m[i16]] + " [";
                for (int i17 = 0; i17 < size; i17++) {
                    str7 = str7 + dArr[i17][i16];
                }
            }
            i16++;
        }
        this.h = new CurveFit[this.p.length + 1];
        int i18 = 0;
        while (true) {
            String[] strArr3 = this.p;
            if (i18 >= strArr3.length) {
                break;
            }
            String str8 = strArr3[i18];
            int i19 = 0;
            double[] dArr3 = null;
            int i20 = 0;
            double[][] dArr4 = null;
            while (i19 < size) {
                if (cVarArr[i19].j(str8)) {
                    if (dArr4 == null) {
                        dArr3 = new double[size];
                        int[] iArr4 = new int[i6];
                        iArr4[1] = cVarArr[i19].h(str8);
                        iArr4[c3] = size;
                        dArr4 = (double[][]) Array.newInstance((Class<?>) double.class, iArr4);
                    }
                    dArr3[i20] = cVarArr[i19].f341c;
                    cVarArr[i19].g(str8, dArr4[i20], 0);
                    i20++;
                }
                i19++;
                i6 = 2;
                c3 = 0;
            }
            i18++;
            this.h[i18] = CurveFit.get(this.f312c, Arrays.copyOf(dArr3, i20), (double[][]) Arrays.copyOf(dArr4, i20));
            i6 = 2;
            c3 = 0;
        }
        this.h[0] = CurveFit.get(this.f312c, dArr2, dArr);
        if (cVarArr[0].j != Key.UNSET) {
            int[] iArr5 = new int[size];
            double[] dArr5 = new double[size];
            double[][] dArr6 = (double[][]) Array.newInstance((Class<?>) double.class, size, 2);
            for (int i21 = 0; i21 < size; i21++) {
                iArr5[i21] = cVarArr[i21].j;
                dArr5[i21] = cVarArr[i21].f341c;
                dArr6[i21][0] = cVarArr[i21].e;
                dArr6[i21][1] = cVarArr[i21].f;
            }
            this.f314i = CurveFit.getArc(iArr5, dArr5, dArr6);
        }
        float f2 = Float.NaN;
        this.y = new HashMap<>();
        if (this.v != null) {
            Iterator<String> it9 = hashSet3.iterator();
            while (it9.hasNext()) {
                String next8 = it9.next();
                KeyCycleOscillator a = KeyCycleOscillator.a(next8);
                if (a != null) {
                    if (a.variesByPath() && Float.isNaN(f2)) {
                        f2 = p();
                    }
                    a.setType(next8);
                    this.y.put(next8, a);
                }
            }
            Iterator<Key> it10 = this.v.iterator();
            while (it10.hasNext()) {
                Key next9 = it10.next();
                if (next9 instanceof KeyCycle) {
                    ((KeyCycle) next9).addCycleValues(this.y);
                }
            }
            Iterator<KeyCycleOscillator> it11 = this.y.values().iterator();
            while (it11.hasNext()) {
                it11.next().setup(f2);
            }
        }
    }

    public String toString() {
        return " start: x: " + this.f313d.e + " y: " + this.f313d.f + " end: x: " + this.e.e + " y: " + this.e.f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u(ConstraintWidget constraintWidget, ConstraintSet constraintSet) {
        c cVar = this.e;
        cVar.f341c = 1.0f;
        cVar.f342d = 1.0f;
        t(cVar);
        this.e.n(constraintWidget.getX(), constraintWidget.getY(), constraintWidget.getWidth(), constraintWidget.getHeight());
        this.e.a(constraintSet.getParameters(this.b));
        this.g.i(constraintWidget, constraintSet, this.b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v(View view) {
        c cVar = this.f313d;
        cVar.f341c = 0.0f;
        cVar.f342d = 0.0f;
        cVar.n(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.f.h(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(ConstraintWidget constraintWidget, ConstraintSet constraintSet) {
        c cVar = this.f313d;
        cVar.f341c = 0.0f;
        cVar.f342d = 0.0f;
        t(cVar);
        this.f313d.n(constraintWidget.getX(), constraintWidget.getY(), constraintWidget.getWidth(), constraintWidget.getHeight());
        ConstraintSet.Constraint parameters = constraintSet.getParameters(this.b);
        this.f313d.a(parameters);
        this.j = parameters.motion.mMotionStagger;
        this.f.i(constraintWidget, constraintSet, this.b);
    }
}
