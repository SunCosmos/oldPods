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
public class KeyAttributes extends Key {
    public static final int KEY_TYPE = 1;
    private String f;
    private int g = -1;
    private float h = Float.NaN;

    /* renamed from: i, reason: collision with root package name */
    private float f301i = Float.NaN;
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
    private float t = Float.NaN;
    private float u = Float.NaN;

    /* loaded from: classes.dex */
    private static class a {
        private static SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyAttribute_android_alpha, 1);
            a.append(R.styleable.KeyAttribute_android_elevation, 2);
            a.append(R.styleable.KeyAttribute_android_rotation, 4);
            a.append(R.styleable.KeyAttribute_android_rotationX, 5);
            a.append(R.styleable.KeyAttribute_android_rotationY, 6);
            a.append(R.styleable.KeyAttribute_android_transformPivotX, 19);
            a.append(R.styleable.KeyAttribute_android_transformPivotY, 20);
            a.append(R.styleable.KeyAttribute_android_scaleX, 7);
            a.append(R.styleable.KeyAttribute_transitionPathRotate, 8);
            a.append(R.styleable.KeyAttribute_transitionEasing, 9);
            a.append(R.styleable.KeyAttribute_motionTarget, 10);
            a.append(R.styleable.KeyAttribute_framePosition, 12);
            a.append(R.styleable.KeyAttribute_curveFit, 13);
            a.append(R.styleable.KeyAttribute_android_scaleY, 14);
            a.append(R.styleable.KeyAttribute_android_translationX, 15);
            a.append(R.styleable.KeyAttribute_android_translationY, 16);
            a.append(R.styleable.KeyAttribute_android_translationZ, 17);
            a.append(R.styleable.KeyAttribute_motionProgress, 18);
        }

        public static void a(KeyAttributes keyAttributes, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (a.get(index)) {
                    case 1:
                        keyAttributes.h = typedArray.getFloat(index, keyAttributes.h);
                        break;
                    case 2:
                        keyAttributes.f301i = typedArray.getDimension(index, keyAttributes.f301i);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + a.get(index));
                        break;
                    case 4:
                        keyAttributes.j = typedArray.getFloat(index, keyAttributes.j);
                        break;
                    case 5:
                        keyAttributes.k = typedArray.getFloat(index, keyAttributes.k);
                        break;
                    case 6:
                        keyAttributes.l = typedArray.getFloat(index, keyAttributes.l);
                        break;
                    case 7:
                        keyAttributes.p = typedArray.getFloat(index, keyAttributes.p);
                        break;
                    case 8:
                        keyAttributes.o = typedArray.getFloat(index, keyAttributes.o);
                        break;
                    case 9:
                        keyAttributes.f = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyAttributes.b);
                            keyAttributes.b = resourceId;
                            if (resourceId != -1) {
                                break;
                            }
                            keyAttributes.f299c = typedArray.getString(index);
                            break;
                        } else {
                            if (typedArray.peekValue(index).type != 3) {
                                keyAttributes.b = typedArray.getResourceId(index, keyAttributes.b);
                                break;
                            }
                            keyAttributes.f299c = typedArray.getString(index);
                        }
                    case 12:
                        keyAttributes.a = typedArray.getInt(index, keyAttributes.a);
                        break;
                    case 13:
                        keyAttributes.g = typedArray.getInteger(index, keyAttributes.g);
                        break;
                    case 14:
                        keyAttributes.q = typedArray.getFloat(index, keyAttributes.q);
                        break;
                    case 15:
                        keyAttributes.r = typedArray.getDimension(index, keyAttributes.r);
                        break;
                    case 16:
                        keyAttributes.s = typedArray.getDimension(index, keyAttributes.s);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT >= 21) {
                            keyAttributes.t = typedArray.getDimension(index, keyAttributes.t);
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        keyAttributes.u = typedArray.getFloat(index, keyAttributes.u);
                        break;
                    case 19:
                        keyAttributes.m = typedArray.getDimension(index, keyAttributes.m);
                        break;
                    case 20:
                        keyAttributes.n = typedArray.getDimension(index, keyAttributes.n);
                        break;
                }
            }
        }
    }

    public KeyAttributes() {
        this.f300d = 1;
        this.e = new HashMap<>();
    }

    /* JADX WARN: Code restructure failed: missing block: B:120:0x009a, code lost:
    
        if (r1.equals("scaleY") == false) goto L12;
     */
    @Override // androidx.constraintlayout.motion.widget.Key
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.widget.SplineSet> r7) {
        /*
            Method dump skipped, instructions count: 550
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyAttributes.addValues(java.util.HashMap):void");
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.h)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f301i)) {
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
        if (!Float.isNaN(this.m)) {
            hashSet.add("transformPivotX");
        }
        if (!Float.isNaN(this.n)) {
            hashSet.add("transformPivotY");
        }
        if (!Float.isNaN(this.r)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.s)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.o)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.p)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.q)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.u)) {
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
        a.a(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyAttribute));
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setInterpolation(HashMap<String, Integer> hashMap) {
        if (this.g == -1) {
            return;
        }
        if (!Float.isNaN(this.h)) {
            hashMap.put("alpha", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.f301i)) {
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
        if (!Float.isNaN(this.m)) {
            hashMap.put("transformPivotX", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.n)) {
            hashMap.put("transformPivotY", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.r)) {
            hashMap.put("translationX", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.s)) {
            hashMap.put("translationY", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.t)) {
            hashMap.put("translationZ", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.o)) {
            hashMap.put("transitionPathRotate", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.p)) {
            hashMap.put("scaleX", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.q)) {
            hashMap.put("scaleY", Integer.valueOf(this.g));
        }
        if (!Float.isNaN(this.u)) {
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
            case -1913008125:
                if (str.equals("motionProgress")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1812823328:
                if (str.equals("transitionEasing")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1249320806:
                if (str.equals("rotationX")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1249320805:
                if (str.equals("rotationY")) {
                    c2 = 3;
                    break;
                }
                break;
            case -1225497657:
                if (str.equals("translationX")) {
                    c2 = 4;
                    break;
                }
                break;
            case -1225497656:
                if (str.equals("translationY")) {
                    c2 = 5;
                    break;
                }
                break;
            case -987906986:
                if (str.equals("pivotX")) {
                    c2 = 6;
                    break;
                }
                break;
            case -987906985:
                if (str.equals("pivotY")) {
                    c2 = 7;
                    break;
                }
                break;
            case -908189618:
                if (str.equals("scaleX")) {
                    c2 = '\b';
                    break;
                }
                break;
            case -908189617:
                if (str.equals("scaleY")) {
                    c2 = '\t';
                    break;
                }
                break;
            case -40300674:
                if (str.equals("rotation")) {
                    c2 = '\n';
                    break;
                }
                break;
            case -4379043:
                if (str.equals("elevation")) {
                    c2 = 11;
                    break;
                }
                break;
            case 37232917:
                if (str.equals("transitionPathRotate")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 92909918:
                if (str.equals("alpha")) {
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
            case 1941332754:
                if (str.equals("visibility")) {
                    c2 = 16;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                this.u = c(obj);
                return;
            case 1:
                obj.toString();
                return;
            case 2:
                this.k = c(obj);
                return;
            case 3:
                this.l = c(obj);
                return;
            case 4:
                this.r = c(obj);
                return;
            case 5:
                this.s = c(obj);
                return;
            case 6:
                this.m = c(obj);
                return;
            case 7:
                this.n = c(obj);
                return;
            case '\b':
                this.p = c(obj);
                return;
            case '\t':
                this.q = c(obj);
                return;
            case '\n':
                this.j = c(obj);
                return;
            case 11:
                this.f301i = c(obj);
                return;
            case '\f':
                this.o = c(obj);
                return;
            case '\r':
                this.h = c(obj);
                return;
            case 14:
                this.g = d(obj);
                return;
            case 15:
                this.t = c(obj);
                return;
            case 16:
                b(obj);
                return;
            default:
                return;
        }
    }
}
