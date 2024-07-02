package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class KeyCycle extends Key {
    public static final int KEY_TYPE = 4;
    private String f;
    private int g = 0;
    private int h = -1;

    /* renamed from: i, reason: collision with root package name */
    private float f302i = Float.NaN;
    private float j = 0.0f;
    private float k = Float.NaN;
    private int l = -1;
    private float m = Float.NaN;
    private float n = Float.NaN;
    private float o = Float.NaN;
    private float p = Float.NaN;
    private float q = Float.NaN;
    private float r = Float.NaN;
    private float s = Float.NaN;
    private float t = Float.NaN;
    private float u = Float.NaN;
    private float v = Float.NaN;
    private float w = Float.NaN;

    /* loaded from: classes.dex */
    private static class a {
        private static SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyCycle_motionTarget, 1);
            a.append(R.styleable.KeyCycle_framePosition, 2);
            a.append(R.styleable.KeyCycle_transitionEasing, 3);
            a.append(R.styleable.KeyCycle_curveFit, 4);
            a.append(R.styleable.KeyCycle_waveShape, 5);
            a.append(R.styleable.KeyCycle_wavePeriod, 6);
            a.append(R.styleable.KeyCycle_waveOffset, 7);
            a.append(R.styleable.KeyCycle_waveVariesBy, 8);
            a.append(R.styleable.KeyCycle_android_alpha, 9);
            a.append(R.styleable.KeyCycle_android_elevation, 10);
            a.append(R.styleable.KeyCycle_android_rotation, 11);
            a.append(R.styleable.KeyCycle_android_rotationX, 12);
            a.append(R.styleable.KeyCycle_android_rotationY, 13);
            a.append(R.styleable.KeyCycle_transitionPathRotate, 14);
            a.append(R.styleable.KeyCycle_android_scaleX, 15);
            a.append(R.styleable.KeyCycle_android_scaleY, 16);
            a.append(R.styleable.KeyCycle_android_translationX, 17);
            a.append(R.styleable.KeyCycle_android_translationY, 18);
            a.append(R.styleable.KeyCycle_android_translationZ, 19);
            a.append(R.styleable.KeyCycle_motionProgress, 20);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(KeyCycle keyCycle, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (a.get(index)) {
                    case 1:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyCycle.b);
                            keyCycle.b = resourceId;
                            if (resourceId != -1) {
                                break;
                            }
                            keyCycle.f299c = typedArray.getString(index);
                            break;
                        } else {
                            if (typedArray.peekValue(index).type != 3) {
                                keyCycle.b = typedArray.getResourceId(index, keyCycle.b);
                                break;
                            }
                            keyCycle.f299c = typedArray.getString(index);
                        }
                    case 2:
                        keyCycle.a = typedArray.getInt(index, keyCycle.a);
                        break;
                    case 3:
                        keyCycle.f = typedArray.getString(index);
                        break;
                    case 4:
                        keyCycle.g = typedArray.getInteger(index, keyCycle.g);
                        break;
                    case 5:
                        keyCycle.h = typedArray.getInt(index, keyCycle.h);
                        break;
                    case 6:
                        keyCycle.f302i = typedArray.getFloat(index, keyCycle.f302i);
                        break;
                    case 7:
                        keyCycle.j = typedArray.peekValue(index).type == 5 ? typedArray.getDimension(index, keyCycle.j) : typedArray.getFloat(index, keyCycle.j);
                        break;
                    case 8:
                        keyCycle.l = typedArray.getInt(index, keyCycle.l);
                        break;
                    case 9:
                        keyCycle.m = typedArray.getFloat(index, keyCycle.m);
                        break;
                    case 10:
                        keyCycle.n = typedArray.getDimension(index, keyCycle.n);
                        break;
                    case 11:
                        keyCycle.o = typedArray.getFloat(index, keyCycle.o);
                        break;
                    case 12:
                        keyCycle.q = typedArray.getFloat(index, keyCycle.q);
                        break;
                    case 13:
                        keyCycle.r = typedArray.getFloat(index, keyCycle.r);
                        break;
                    case 14:
                        keyCycle.p = typedArray.getFloat(index, keyCycle.p);
                        break;
                    case 15:
                        keyCycle.s = typedArray.getFloat(index, keyCycle.s);
                        break;
                    case 16:
                        keyCycle.t = typedArray.getFloat(index, keyCycle.t);
                        break;
                    case 17:
                        keyCycle.u = typedArray.getDimension(index, keyCycle.u);
                        break;
                    case 18:
                        keyCycle.v = typedArray.getDimension(index, keyCycle.v);
                        break;
                    case 19:
                        if (Build.VERSION.SDK_INT >= 21) {
                            keyCycle.w = typedArray.getDimension(index, keyCycle.w);
                            break;
                        } else {
                            break;
                        }
                    case 20:
                        keyCycle.k = typedArray.getFloat(index, keyCycle.k);
                        break;
                    default:
                        Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + a.get(index));
                        break;
                }
            }
        }
    }

    public KeyCycle() {
        this.f300d = 4;
        this.e = new HashMap<>();
    }

    public void addCycleValues(HashMap<String, KeyCycleOscillator> hashMap) {
        for (String str : hashMap.keySet()) {
            if (str.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute = this.e.get(str.substring(7));
                if (constraintAttribute != null && constraintAttribute.getType() == ConstraintAttribute.AttributeType.FLOAT_TYPE) {
                    hashMap.get(str).setPoint(this.a, this.h, this.l, this.f302i, this.j, constraintAttribute.getValueToInterpolate(), constraintAttribute);
                }
            } else {
                float value = getValue(str);
                if (!Float.isNaN(value)) {
                    hashMap.get(str).setPoint(this.a, this.h, this.l, this.f302i, this.j, value);
                }
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0042. Please report as an issue. */
    @Override // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap<String, SplineSet> hashMap) {
        int i2;
        float f;
        Debug.logStack("KeyCycle", "add " + hashMap.size() + " values", 2);
        for (String str : hashMap.keySet()) {
            SplineSet splineSet = hashMap.get(str);
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
                case 156108012:
                    if (str.equals("waveOffset")) {
                        c2 = '\f';
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    i2 = this.a;
                    f = this.q;
                    break;
                case 1:
                    i2 = this.a;
                    f = this.r;
                    break;
                case 2:
                    i2 = this.a;
                    f = this.u;
                    break;
                case 3:
                    i2 = this.a;
                    f = this.v;
                    break;
                case 4:
                    i2 = this.a;
                    f = this.w;
                    break;
                case 5:
                    i2 = this.a;
                    f = this.k;
                    break;
                case 6:
                    i2 = this.a;
                    f = this.s;
                    break;
                case 7:
                    i2 = this.a;
                    f = this.t;
                    break;
                case '\b':
                    i2 = this.a;
                    f = this.o;
                    break;
                case '\t':
                    i2 = this.a;
                    f = this.n;
                    break;
                case '\n':
                    i2 = this.a;
                    f = this.p;
                    break;
                case 11:
                    i2 = this.a;
                    f = this.m;
                    break;
                case '\f':
                    i2 = this.a;
                    f = this.j;
                    break;
                default:
                    Log.v("KeyCycle", "WARNING KeyCycle UNKNOWN  " + str);
                    continue;
            }
            splineSet.setPoint(i2, f);
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.m)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.s)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.u)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.v)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.w)) {
            hashSet.add("translationZ");
        }
        if (this.e.size() > 0) {
            Iterator<String> it = this.e.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    public float getValue(String str) {
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
            case 156108012:
                if (str.equals("waveOffset")) {
                    c2 = '\f';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return this.q;
            case 1:
                return this.r;
            case 2:
                return this.u;
            case 3:
                return this.v;
            case 4:
                return this.w;
            case 5:
                return this.k;
            case 6:
                return this.s;
            case 7:
                return this.t;
            case '\b':
                return this.o;
            case '\t':
                return this.n;
            case '\n':
                return this.p;
            case 11:
                return this.m;
            case '\f':
                return this.j;
            default:
                Log.v("KeyCycle", "WARNING! KeyCycle UNKNOWN  " + str);
                return Float.NaN;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyCycle));
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setValue(String str, Object obj) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1812823328:
                if (str.equals("transitionEasing")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1249320806:
                if (str.equals("rotationX")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1249320805:
                if (str.equals("rotationY")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1225497657:
                if (str.equals("translationX")) {
                    c2 = 3;
                    break;
                }
                break;
            case -1225497656:
                if (str.equals("translationY")) {
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
            case 156108012:
                if (str.equals("waveOffset")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 184161818:
                if (str.equals("wavePeriod")) {
                    c2 = '\r';
                    break;
                }
                break;
            case 579057826:
                if (str.equals("curveFit")) {
                    c2 = 14;
                    break;
                }
                break;
            case 1317633238:
                if (str.equals("mTranslationZ")) {
                    c2 = 15;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                obj.toString();
                return;
            case 1:
                this.q = c(obj);
                return;
            case 2:
                this.r = c(obj);
                return;
            case 3:
                this.u = c(obj);
                return;
            case 4:
                this.v = c(obj);
                return;
            case 5:
                this.k = c(obj);
                return;
            case 6:
                this.s = c(obj);
                return;
            case 7:
                this.t = c(obj);
                return;
            case '\b':
                this.o = c(obj);
                return;
            case '\t':
                this.n = c(obj);
                return;
            case '\n':
                this.p = c(obj);
                return;
            case 11:
                this.m = c(obj);
                return;
            case '\f':
                this.j = c(obj);
                return;
            case '\r':
                this.f302i = c(obj);
                return;
            case 14:
                this.g = d(obj);
                return;
            case 15:
                this.w = c(obj);
                return;
            default:
                return;
        }
    }
}
