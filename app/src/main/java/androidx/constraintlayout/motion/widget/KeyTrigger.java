package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.R;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes.dex */
public class KeyTrigger extends Key {
    public static final int KEY_TYPE = 5;
    private String f = null;
    private int g;
    private String h;

    /* renamed from: i, reason: collision with root package name */
    private String f311i;
    private int j;
    private int k;
    private View l;
    float m;
    private boolean n;
    private boolean o;
    private boolean p;
    private float q;
    private Method r;
    private Method s;
    private Method t;
    private float u;
    private boolean v;
    RectF w;
    RectF x;

    /* loaded from: classes.dex */
    private static class a {
        private static SparseIntArray a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyTrigger_framePosition, 8);
            a.append(R.styleable.KeyTrigger_onCross, 4);
            a.append(R.styleable.KeyTrigger_onNegativeCross, 1);
            a.append(R.styleable.KeyTrigger_onPositiveCross, 2);
            a.append(R.styleable.KeyTrigger_motionTarget, 7);
            a.append(R.styleable.KeyTrigger_triggerId, 6);
            a.append(R.styleable.KeyTrigger_triggerSlack, 5);
            a.append(R.styleable.KeyTrigger_motion_triggerOnCollision, 9);
            a.append(R.styleable.KeyTrigger_motion_postLayoutCollision, 10);
            a.append(R.styleable.KeyTrigger_triggerReceiver, 11);
        }

        public static void a(KeyTrigger keyTrigger, TypedArray typedArray, Context context) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (a.get(index)) {
                    case 1:
                        keyTrigger.h = typedArray.getString(index);
                        continue;
                    case 2:
                        keyTrigger.f311i = typedArray.getString(index);
                        continue;
                    case 4:
                        keyTrigger.f = typedArray.getString(index);
                        continue;
                    case 5:
                        keyTrigger.m = typedArray.getFloat(index, keyTrigger.m);
                        continue;
                    case 6:
                        keyTrigger.j = typedArray.getResourceId(index, keyTrigger.j);
                        continue;
                    case 7:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyTrigger.b);
                            keyTrigger.b = resourceId;
                            if (resourceId != -1) {
                                continue;
                            }
                            keyTrigger.f299c = typedArray.getString(index);
                            break;
                        } else {
                            if (typedArray.peekValue(index).type != 3) {
                                keyTrigger.b = typedArray.getResourceId(index, keyTrigger.b);
                                break;
                            }
                            keyTrigger.f299c = typedArray.getString(index);
                        }
                    case 8:
                        int integer = typedArray.getInteger(index, keyTrigger.a);
                        keyTrigger.a = integer;
                        keyTrigger.q = (integer + 0.5f) / 100.0f;
                        continue;
                    case 9:
                        keyTrigger.k = typedArray.getResourceId(index, keyTrigger.k);
                        continue;
                    case 10:
                        keyTrigger.v = typedArray.getBoolean(index, keyTrigger.v);
                        continue;
                    case 11:
                        keyTrigger.g = typedArray.getResourceId(index, keyTrigger.g);
                        break;
                }
                Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + a.get(index));
            }
        }
    }

    public KeyTrigger() {
        int i2 = Key.UNSET;
        this.g = i2;
        this.h = null;
        this.f311i = null;
        this.j = i2;
        this.k = i2;
        this.l = null;
        this.m = 0.1f;
        this.n = true;
        this.o = true;
        this.p = true;
        this.q = Float.NaN;
        this.v = false;
        this.w = new RectF();
        this.x = new RectF();
        this.f300d = 5;
        this.e = new HashMap<>();
    }

    private void q(RectF rectF, View view, boolean z) {
        rectF.top = view.getTop();
        rectF.bottom = view.getBottom();
        rectF.left = view.getLeft();
        rectF.right = view.getRight();
        if (z) {
            view.getMatrix().mapRect(rectF);
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap<String, SplineSet> hashMap) {
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void conditionallyFire(float r11, android.view.View r12) {
        /*
            Method dump skipped, instructions count: 636
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyTrigger.conditionallyFire(float, android.view.View):void");
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet<String> hashSet) {
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void load(Context context, AttributeSet attributeSet) {
        a.a(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyTrigger), context);
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void setValue(String str, Object obj) {
    }
}
