package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class KeyTimeCycle extends Key {
    public static final int KEY_TYPE = 3;
    private String f;
    private int g = -1;
    private float h = Float.NaN;

    /* renamed from: i, reason: collision with root package name */
    private float f310i = Float.NaN;
    private float j = Float.NaN;
    private float k = Float.NaN;
    private float l = Float.NaN;
    private float m = Float.NaN;
    private float n = Float.NaN;
    private float o = Float.NaN;
    private float p = Float.NaN;
    private float q = Float.NaN;
    private float r = Float.NaN;
    private float s = Float.NaN;
    private int t = 0;
    private float u = Float.NaN;
    private float v = 0.0f;

    /* loaded from: classes.dex */
    private static class a {
        private static SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyTimeCycle_android_alpha, 1);
            a.append(R.styleable.KeyTimeCycle_android_elevation, 2);
            a.append(R.styleable.KeyTimeCycle_android_rotation, 4);
            a.append(R.styleable.KeyTimeCycle_android_rotationX, 5);
            a.append(R.styleable.KeyTimeCycle_android_rotationY, 6);
            a.append(R.styleable.KeyTimeCycle_android_scaleX, 7);
            a.append(R.styleable.KeyTimeCycle_transitionPathRotate, 8);
            a.append(R.styleable.KeyTimeCycle_transitionEasing, 9);
            a.append(R.styleable.KeyTimeCycle_motionTarget, 10);
            a.append(R.styleable.KeyTimeCycle_framePosition, 12);
            a.append(R.styleable.KeyTimeCycle_curveFit, 13);
            a.append(R.styleable.KeyTimeCycle_android_scaleY, 14);
            a.append(R.styleable.KeyTimeCycle_android_translationX, 15);
            a.append(R.styleable.KeyTimeCycle_android_translationY, 16);
            a.append(R.styleable.KeyTimeCycle_android_translationZ, 17);
            a.append(R.styleable.KeyTimeCycle_motionProgress, 18);
            a.append(R.styleable.KeyTimeCycle_wavePeriod, 20);
            a.append(R.styleable.KeyTimeCycle_waveOffset, 21);
            a.append(R.styleable.KeyTimeCycle_waveShape, 19);
        }

        public static void a(KeyTimeCycle keyTimeCycle, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (a.get(index)) {
                    case 1:
                        keyTimeCycle.h = typedArray.getFloat(index, keyTimeCycle.h);
                        break;
                    case 2:
                        keyTimeCycle.f310i = typedArray.getDimension(index, keyTimeCycle.f310i);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + a.get(index));
                        break;
                    case 4:
                        keyTimeCycle.j = typedArray.getFloat(index, keyTimeCycle.j);
                        break;
                    case 5:
                        keyTimeCycle.k = typedArray.getFloat(index, keyTimeCycle.k);
                        break;
                    case 6:
                        keyTimeCycle.l = typedArray.getFloat(index, keyTimeCycle.l);
                        break;
                    case 7:
                        keyTimeCycle.n = typedArray.getFloat(index, keyTimeCycle.n);
                        break;
                    case 8:
                        keyTimeCycle.m = typedArray.getFloat(index, keyTimeCycle.m);
                        break;
                    case 9:
                        keyTimeCycle.f = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyTimeCycle.b);
                            keyTimeCycle.b = resourceId;
                            if (resourceId != -1) {
                                break;
                            }
                            keyTimeCycle.f299c = typedArray.getString(index);
                            break;
                        } else {
                            if (typedArray.peekValue(index).type != 3) {
                                keyTimeCycle.b = typedArray.getResourceId(index, keyTimeCycle.b);
                                break;
                            }
                            keyTimeCycle.f299c = typedArray.getString(index);
                        }
                    case 12:
                        keyTimeCycle.a = typedArray.getInt(index, keyTimeCycle.a);
                        break;
                    case 13:
                        keyTimeCycle.g = typedArray.getInteger(index, keyTimeCycle.g);
                        break;
                    case 14:
                        keyTimeCycle.o = typedArray.getFloat(index, keyTimeCycle.o);
                        break;
                    case 15:
                        keyTimeCycle.p = typedArray.getDimension(index, keyTimeCycle.p);
                        break;
                    case 16:
                        keyTimeCycle.q = typedArray.getDimension(index, keyTimeCycle.q);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            keyTimeCycle.r = typedArray.getDimension(index, keyTimeCycle.r);
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        keyTimeCycle.s = typedArray.getFloat(index, keyTimeCycle.s);
                        break;
                    case 19:
                        keyTimeCycle.t = typedArray.getInt(index, keyTimeCycle.t);
                        break;
                    case 20:
                        keyTimeCycle.u = typedArray.getFloat(index, keyTimeCycle.u);
                        break;
                    case 21:
                        keyTimeCycle.v = typedArray.peekValue(index).type == 5 ? typedArray.getDimension(index, keyTimeCycle.v) : typedArray.getFloat(index, keyTimeCycle.v);
                        break;
                }
            }
        }
    }

    public KeyTimeCycle() {
        this.f300d = 3;
        this.e = new HashMap<>();
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0089, code lost:
    
        if (r1.equals("scaleY") == false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addTimeValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.widget.TimeCycleSplineSet> r11) {
        /*
            Method dump skipped, instructions count: 496
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyTimeCycle.addTimeValues(java.util.HashMap):void");
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap<String, SplineSet> hashMap) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.h)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f310i)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.j)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.k)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.l)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.m)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.s)) {
            hashSet.add("progress");
        }
        if (this.e.size() > 0) {
            Iterator<String> it = this.e.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + it.next());
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyTimeCycle));
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setInterpolation(HashMap<String, Integer> hashMap) {
        if (this.g == -1) {
            return;
        }
        if (!Float.isNaN(this.h)) {
            hashMap.put("alpha", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.f310i)) {
            hashMap.put("elevation", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.j)) {
            hashMap.put("rotation", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.k)) {
            hashMap.put("rotationX", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.l)) {
            hashMap.put("rotationY", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.p)) {
            hashMap.put("translationX", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.q)) {
            hashMap.put("translationY", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.r)) {
            hashMap.put("translationZ", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.m)) {
            hashMap.put("transitionPathRotate", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.n)) {
            hashMap.put("scaleX", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.n)) {
            hashMap.put("scaleY", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.s)) {
            hashMap.put("progress", Integer.valueOf(this.g));
        }
        if (this.e.size() > 0) {
            Iterator<String> it = this.e.keySet().iterator();
            while (it.hasNext()) {
                hashMap.put("CUSTOM," + it.next(), Integer.valueOf(this.g));
            }
        }
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
            case 579057826:
                if (str.equals("curveFit")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 1317633238:
                if (str.equals("mTranslationZ")) {
                    c2 = '\r';
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                obj.toString();
                return;
            case 1:
                this.k = c(obj);
                return;
            case 2:
                this.l = c(obj);
                return;
            case 3:
                this.p = c(obj);
                return;
            case 4:
                this.q = c(obj);
                return;
            case 5:
                this.s = c(obj);
                return;
            case 6:
                this.n = c(obj);
                return;
            case 7:
                this.o = c(obj);
                return;
            case '\b':
                this.j = c(obj);
                return;
            case '\t':
                this.f310i = c(obj);
                return;
            case '\n':
                this.m = c(obj);
                return;
            case 11:
                this.h = c(obj);
                return;
            case '\f':
                this.g = d(obj);
                return;
            case '\r':
                this.r = c(obj);
                return;
            default:
                return;
        }
    }
}
