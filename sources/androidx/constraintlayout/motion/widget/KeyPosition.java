package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;

/* loaded from: classes.dex */
public class KeyPosition extends androidx.constraintlayout.motion.widget.a {
    public static final int TYPE_CARTESIAN = 0;
    public static final int TYPE_PATH = 1;
    public static final int TYPE_SCREEN = 2;
    String g = null;
    int h = Key.UNSET;

    /* renamed from: i, reason: collision with root package name */
    int f309i = 0;
    float j = Float.NaN;
    float k = Float.NaN;
    float l = Float.NaN;
    float m = Float.NaN;
    float n = Float.NaN;
    float o = Float.NaN;
    int p = 0;
    private float q = Float.NaN;
    private float r = Float.NaN;

    /* loaded from: classes.dex */
    private static class a {
        private static SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyPosition_motionTarget, 1);
            a.append(R.styleable.KeyPosition_framePosition, 2);
            a.append(R.styleable.KeyPosition_transitionEasing, 3);
            a.append(R.styleable.KeyPosition_curveFit, 4);
            a.append(R.styleable.KeyPosition_drawPath, 5);
            a.append(R.styleable.KeyPosition_percentX, 6);
            a.append(R.styleable.KeyPosition_percentY, 7);
            a.append(R.styleable.KeyPosition_keyPositionType, 9);
            a.append(R.styleable.KeyPosition_sizePercent, 8);
            a.append(R.styleable.KeyPosition_percentWidth, 11);
            a.append(R.styleable.KeyPosition_percentHeight, 12);
            a.append(R.styleable.KeyPosition_pathMotionArc, 10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(KeyPosition keyPosition, TypedArray typedArray) {
            float f;
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (a.get(index)) {
                    case 1:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyPosition.b);
                            keyPosition.b = resourceId;
                            if (resourceId != -1) {
                                break;
                            }
                            keyPosition.f299c = typedArray.getString(index);
                            break;
                        } else {
                            if (typedArray.peekValue(index).type != 3) {
                                keyPosition.b = typedArray.getResourceId(index, keyPosition.b);
                                continue;
                            }
                            keyPosition.f299c = typedArray.getString(index);
                        }
                    case 2:
                        keyPosition.a = typedArray.getInt(index, keyPosition.a);
                        continue;
                    case 3:
                        keyPosition.g = typedArray.peekValue(index).type == 3 ? typedArray.getString(index) : Easing.NAMED_EASING[typedArray.getInteger(index, 0)];
                        continue;
                    case 4:
                        keyPosition.f = typedArray.getInteger(index, keyPosition.f);
                        continue;
                    case 5:
                        keyPosition.f309i = typedArray.getInt(index, keyPosition.f309i);
                        continue;
                    case 6:
                        keyPosition.l = typedArray.getFloat(index, keyPosition.l);
                        continue;
                    case 7:
                        keyPosition.m = typedArray.getFloat(index, keyPosition.m);
                        continue;
                    case 8:
                        f = typedArray.getFloat(index, keyPosition.k);
                        keyPosition.j = f;
                        break;
                    case 9:
                        keyPosition.p = typedArray.getInt(index, keyPosition.p);
                        continue;
                    case 10:
                        keyPosition.h = typedArray.getInt(index, keyPosition.h);
                        continue;
                    case 11:
                        keyPosition.j = typedArray.getFloat(index, keyPosition.j);
                        continue;
                    case 12:
                        f = typedArray.getFloat(index, keyPosition.k);
                        break;
                    default:
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + a.get(index));
                        continue;
                }
                keyPosition.k = f;
            }
            if (keyPosition.a == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    public KeyPosition() {
        this.f300d = 2;
    }

    private void e(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        float f7 = Float.isNaN(this.l) ? 0.0f : this.l;
        float f8 = Float.isNaN(this.o) ? 0.0f : this.o;
        float f9 = Float.isNaN(this.m) ? 0.0f : this.m;
        this.q = (int) (f + (f7 * f5) + ((Float.isNaN(this.n) ? 0.0f : this.n) * f6));
        this.r = (int) (f2 + (f5 * f8) + (f6 * f9));
    }

    private void f(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        float f7 = this.l;
        float f8 = this.m;
        this.q = f + (f5 * f7) + ((-f6) * f8);
        this.r = f2 + (f6 * f7) + (f5 * f8);
    }

    private void h(int i2, int i3) {
        float f = this.l;
        float f2 = 0;
        this.q = ((i2 - 0) * f) + f2;
        this.r = ((i3 - 0) * f) + f2;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap<String, SplineSet> hashMap) {
    }

    void g(int i2, int i3, float f, float f2, float f3, float f4) {
        int i4 = this.p;
        if (i4 == 1) {
            f(f, f2, f3, f4);
        } else if (i4 != 2) {
            e(f, f2, f3, f4);
        } else {
            h(i2, i3);
        }
    }

    void i(RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr) {
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        float centerX2 = rectF2.centerX() - centerX;
        float centerY2 = rectF2.centerY() - centerY;
        if (strArr[0] == null) {
            strArr[0] = "percentX";
            fArr[0] = (f - centerX) / centerX2;
            strArr[1] = "percentY";
            fArr[1] = (f2 - centerY) / centerY2;
            return;
        }
        float f3 = (f - centerX) / centerX2;
        if ("percentX".equals(strArr[0])) {
            fArr[0] = f3;
            fArr[1] = (f2 - centerY) / centerY2;
        } else {
            fArr[1] = f3;
            fArr[0] = (f2 - centerY) / centerY2;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.a
    public boolean intersects(int i2, int i3, RectF rectF, RectF rectF2, float f, float f2) {
        g(i2, i3, rectF.centerX(), rectF.centerY(), rectF2.centerX(), rectF2.centerY());
        return Math.abs(f - this.q) < 20.0f && Math.abs(f2 - this.r) < 20.0f;
    }

    void j(RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr) {
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        float centerX2 = rectF2.centerX() - centerX;
        float centerY2 = rectF2.centerY() - centerY;
        float hypot = (float) Math.hypot(centerX2, centerY2);
        if (hypot < 1.0E-4d) {
            System.out.println("distance ~ 0");
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            return;
        }
        float f3 = centerX2 / hypot;
        float f4 = centerY2 / hypot;
        float f5 = f2 - centerY;
        float f6 = f - centerX;
        float f7 = ((f3 * f5) - (f6 * f4)) / hypot;
        float f8 = ((f3 * f6) + (f4 * f5)) / hypot;
        if (strArr[0] != null) {
            if ("percentX".equals(strArr[0])) {
                fArr[0] = f8;
                fArr[1] = f7;
                return;
            }
            return;
        }
        strArr[0] = "percentX";
        strArr[1] = "percentY";
        fArr[0] = f8;
        fArr[1] = f7;
    }

    void k(View view, RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr) {
        rectF.centerX();
        rectF.centerY();
        rectF2.centerX();
        rectF2.centerY();
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        int width = viewGroup.getWidth();
        int height = viewGroup.getHeight();
        if (strArr[0] == null) {
            strArr[0] = "percentX";
            fArr[0] = f / width;
            strArr[1] = "percentY";
            fArr[1] = f2 / height;
            return;
        }
        float f3 = f / width;
        if ("percentX".equals(strArr[0])) {
            fArr[0] = f3;
            fArr[1] = f2 / height;
        } else {
            fArr[1] = f3;
            fArr[0] = f2 / height;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attributeSet) {
        a.b(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyPosition));
    }

    @Override // androidx.constraintlayout.motion.widget.a
    public void positionAttributes(View view, RectF rectF, RectF rectF2, float f, float f2, String[] strArr, float[] fArr) {
        int i2 = this.p;
        if (i2 == 1) {
            j(rectF, rectF2, f, f2, strArr, fArr);
        } else if (i2 != 2) {
            i(rectF, rectF2, f, f2, strArr, fArr);
        } else {
            k(view, rectF, rectF2, f, f2, strArr, fArr);
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setValue(String str, Object obj) {
        float c2;
        str.hashCode();
        char c3 = 65535;
        switch (str.hashCode()) {
            case -1812823328:
                if (str.equals("transitionEasing")) {
                    c3 = 0;
                    break;
                }
                break;
            case -1127236479:
                if (str.equals("percentWidth")) {
                    c3 = 1;
                    break;
                }
                break;
            case -1017587252:
                if (str.equals("percentHeight")) {
                    c3 = 2;
                    break;
                }
                break;
            case -827014263:
                if (str.equals("drawPath")) {
                    c3 = 3;
                    break;
                }
                break;
            case -200259324:
                if (str.equals("sizePercent")) {
                    c3 = 4;
                    break;
                }
                break;
            case 428090547:
                if (str.equals("percentX")) {
                    c3 = 5;
                    break;
                }
                break;
            case 428090548:
                if (str.equals("percentY")) {
                    c3 = 6;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                this.g = obj.toString();
                return;
            case 1:
                this.j = c(obj);
                return;
            case 2:
                c2 = c(obj);
                break;
            case 3:
                this.f309i = d(obj);
                return;
            case 4:
                c2 = c(obj);
                this.j = c2;
                break;
            case 5:
                this.l = c(obj);
                return;
            case 6:
                this.m = c(obj);
                return;
            default:
                return;
        }
        this.k = c2;
    }
}
