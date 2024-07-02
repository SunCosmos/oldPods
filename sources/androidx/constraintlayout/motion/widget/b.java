package androidx.constraintlayout.motion.widget;

import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.motion.widget.SplineSet;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b implements Comparable<b> {

    /* renamed from: c, reason: collision with root package name */
    int f338c;
    private float o;
    private float a = 1.0f;
    int b = 0;

    /* renamed from: d, reason: collision with root package name */
    private float f339d = 0.0f;
    private float e = 0.0f;
    private float f = 0.0f;
    public float g = 0.0f;
    private float h = 1.0f;

    /* renamed from: i, reason: collision with root package name */
    private float f340i = 1.0f;
    private float j = Float.NaN;
    private float k = Float.NaN;
    private float l = 0.0f;
    private float m = 0.0f;
    private float n = 0.0f;
    private float p = Float.NaN;
    private float q = Float.NaN;
    LinkedHashMap<String, ConstraintAttribute> r = new LinkedHashMap<>();

    private boolean e(float f, float f2) {
        return (Float.isNaN(f) || Float.isNaN(f2)) ? Float.isNaN(f) != Float.isNaN(f2) : Math.abs(f - f2) > 1.0E-6f;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:49:0x00d6. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0023. Please report as an issue. */
    public void a(HashMap<String, SplineSet> hashMap, int i2) {
        String str;
        for (String str2 : hashMap.keySet()) {
            SplineSet splineSet = hashMap.get(str2);
            str2.hashCode();
            char c2 = 65535;
            switch (str2.hashCode()) {
                case -1249320806:
                    if (str2.equals("rotationX")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -1249320805:
                    if (str2.equals("rotationY")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -1225497657:
                    if (str2.equals("translationX")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -1225497656:
                    if (str2.equals("translationY")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case -1225497655:
                    if (str2.equals("translationZ")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case -1001078227:
                    if (str2.equals("progress")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case -908189618:
                    if (str2.equals("scaleX")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case -908189617:
                    if (str2.equals("scaleY")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case -760884510:
                    if (str2.equals("transformPivotX")) {
                        c2 = '\b';
                        break;
                    }
                    break;
                case -760884509:
                    if (str2.equals("transformPivotY")) {
                        c2 = '\t';
                        break;
                    }
                    break;
                case -40300674:
                    if (str2.equals("rotation")) {
                        c2 = '\n';
                        break;
                    }
                    break;
                case -4379043:
                    if (str2.equals("elevation")) {
                        c2 = 11;
                        break;
                    }
                    break;
                case 37232917:
                    if (str2.equals("transitionPathRotate")) {
                        c2 = '\f';
                        break;
                    }
                    break;
                case 92909918:
                    if (str2.equals("alpha")) {
                        c2 = '\r';
                        break;
                    }
                    break;
            }
            float f = 1.0f;
            float f2 = 0.0f;
            switch (c2) {
                case 0:
                    if (!Float.isNaN(this.f)) {
                        f2 = this.f;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case 1:
                    if (!Float.isNaN(this.g)) {
                        f2 = this.g;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case 2:
                    if (!Float.isNaN(this.l)) {
                        f2 = this.l;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case 3:
                    if (!Float.isNaN(this.m)) {
                        f2 = this.m;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case 4:
                    if (!Float.isNaN(this.n)) {
                        f2 = this.n;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case 5:
                    if (!Float.isNaN(this.q)) {
                        f2 = this.q;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case 6:
                    if (!Float.isNaN(this.h)) {
                        f = this.h;
                    }
                    splineSet.setPoint(i2, f);
                    break;
                case 7:
                    if (!Float.isNaN(this.f340i)) {
                        f = this.f340i;
                    }
                    splineSet.setPoint(i2, f);
                    break;
                case '\b':
                    if (!Float.isNaN(this.j)) {
                        f2 = this.j;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case '\t':
                    if (!Float.isNaN(this.k)) {
                        f2 = this.k;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case '\n':
                    if (!Float.isNaN(this.e)) {
                        f2 = this.e;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case 11:
                    if (!Float.isNaN(this.f339d)) {
                        f2 = this.f339d;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case '\f':
                    if (!Float.isNaN(this.p)) {
                        f2 = this.p;
                    }
                    splineSet.setPoint(i2, f2);
                    break;
                case '\r':
                    if (!Float.isNaN(this.a)) {
                        f = this.a;
                    }
                    splineSet.setPoint(i2, f);
                    break;
                default:
                    if (str2.startsWith("CUSTOM")) {
                        String str3 = str2.split(",")[1];
                        if (this.r.containsKey(str3)) {
                            ConstraintAttribute constraintAttribute = this.r.get(str3);
                            if (splineSet instanceof SplineSet.b) {
                                ((SplineSet.b) splineSet).c(i2, constraintAttribute);
                                break;
                            } else {
                                str = str2 + " splineSet not a CustomSet frame = " + i2 + ", value" + constraintAttribute.getValueToInterpolate() + splineSet;
                            }
                        } else {
                            str = "UNKNOWN customName " + str3;
                        }
                    } else {
                        str = "UNKNOWN spline " + str2;
                    }
                    Log.e("MotionPaths", str);
                    break;
            }
        }
    }

    public void b(View view) {
        this.f338c = view.getVisibility();
        this.a = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            this.f339d = view.getElevation();
        }
        this.e = view.getRotation();
        this.f = view.getRotationX();
        this.g = view.getRotationY();
        this.h = view.getScaleX();
        this.f340i = view.getScaleY();
        this.j = view.getPivotX();
        this.k = view.getPivotY();
        this.l = view.getTranslationX();
        this.m = view.getTranslationY();
        if (i2 >= 21) {
            this.n = view.getTranslationZ();
        }
    }

    public void c(ConstraintSet.Constraint constraint) {
        ConstraintSet.PropertySet propertySet = constraint.propertySet;
        int i2 = propertySet.mVisibilityMode;
        this.b = i2;
        int i3 = propertySet.visibility;
        this.f338c = i3;
        this.a = (i3 == 0 || i2 != 0) ? propertySet.alpha : 0.0f;
        ConstraintSet.Transform transform = constraint.transform;
        boolean z = transform.applyElevation;
        this.f339d = transform.elevation;
        this.e = transform.rotation;
        this.f = transform.rotationX;
        this.g = transform.rotationY;
        this.h = transform.scaleX;
        this.f340i = transform.scaleY;
        this.j = transform.transformPivotX;
        this.k = transform.transformPivotY;
        this.l = transform.translationX;
        this.m = transform.translationY;
        this.n = transform.translationZ;
        Easing.getInterpolator(constraint.motion.mTransitionEasing);
        ConstraintSet.Motion motion = constraint.motion;
        this.p = motion.mPathRotate;
        int i4 = motion.mDrawPath;
        this.q = constraint.propertySet.mProgress;
        for (String str : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = constraint.mCustomConstraints.get(str);
            if (constraintAttribute.getType() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.r.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public int compareTo(b bVar) {
        return Float.compare(this.o, bVar.o);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(b bVar, HashSet<String> hashSet) {
        if (e(this.a, bVar.a)) {
            hashSet.add("alpha");
        }
        if (e(this.f339d, bVar.f339d)) {
            hashSet.add("elevation");
        }
        int i2 = this.f338c;
        int i3 = bVar.f338c;
        if (i2 != i3 && this.b == 0 && (i2 == 0 || i3 == 0)) {
            hashSet.add("alpha");
        }
        if (e(this.e, bVar.e)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.p) || !Float.isNaN(bVar.p)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.q) || !Float.isNaN(bVar.q)) {
            hashSet.add("progress");
        }
        if (e(this.f, bVar.f)) {
            hashSet.add("rotationX");
        }
        if (e(this.g, bVar.g)) {
            hashSet.add("rotationY");
        }
        if (e(this.j, bVar.j)) {
            hashSet.add("transformPivotX");
        }
        if (e(this.k, bVar.k)) {
            hashSet.add("transformPivotY");
        }
        if (e(this.h, bVar.h)) {
            hashSet.add("scaleX");
        }
        if (e(this.f340i, bVar.f340i)) {
            hashSet.add("scaleY");
        }
        if (e(this.l, bVar.l)) {
            hashSet.add("translationX");
        }
        if (e(this.m, bVar.m)) {
            hashSet.add("translationY");
        }
        if (e(this.n, bVar.n)) {
            hashSet.add("translationZ");
        }
    }

    void g(float f, float f2, float f3, float f4) {
    }

    public void h(View view) {
        g(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        b(view);
    }

    public void i(ConstraintWidget constraintWidget, ConstraintSet constraintSet, int i2) {
        g(constraintWidget.getX(), constraintWidget.getY(), constraintWidget.getWidth(), constraintWidget.getHeight());
        c(constraintSet.getParameters(i2));
    }
}
