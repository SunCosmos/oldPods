package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R;
import androidx.core.widget.NestedScrollView;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class d {
    private static final float[][] v = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
    private static final float[][] w = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    private float m;
    private float n;
    private final MotionLayout o;
    private int a = 0;
    private int b = 0;

    /* renamed from: c, reason: collision with root package name */
    private int f344c = 0;

    /* renamed from: d, reason: collision with root package name */
    private int f345d = -1;
    private int e = -1;
    private int f = -1;
    private float g = 0.5f;
    private float h = 0.5f;

    /* renamed from: i, reason: collision with root package name */
    private float f346i = 0.0f;
    private float j = 1.0f;
    private boolean k = false;
    private float[] l = new float[2];
    private float p = 4.0f;
    private float q = 1.2f;
    private boolean r = true;
    private float s = 1.0f;
    private int t = 0;
    private float u = 10.0f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements View.OnTouchListener {
        a(d dVar) {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements NestedScrollView.OnScrollChangeListener {
        b(d dVar) {
        }

        @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
        public void onScrollChange(NestedScrollView nestedScrollView, int i2, int i3, int i4, int i5) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.o = motionLayout;
        c(context, Xml.asAttributeSet(xmlPullParser));
    }

    private void b(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArray.getIndex(i2);
            if (index == R.styleable.OnSwipe_touchAnchorId) {
                this.f345d = typedArray.getResourceId(index, this.f345d);
            } else if (index == R.styleable.OnSwipe_touchAnchorSide) {
                int i3 = typedArray.getInt(index, this.a);
                this.a = i3;
                float[][] fArr = v;
                this.h = fArr[i3][0];
                this.g = fArr[i3][1];
            } else if (index == R.styleable.OnSwipe_dragDirection) {
                int i4 = typedArray.getInt(index, this.b);
                this.b = i4;
                float[][] fArr2 = w;
                this.f346i = fArr2[i4][0];
                this.j = fArr2[i4][1];
            } else if (index == R.styleable.OnSwipe_maxVelocity) {
                this.p = typedArray.getFloat(index, this.p);
            } else if (index == R.styleable.OnSwipe_maxAcceleration) {
                this.q = typedArray.getFloat(index, this.q);
            } else if (index == R.styleable.OnSwipe_moveWhenScrollAtTop) {
                this.r = typedArray.getBoolean(index, this.r);
            } else if (index == R.styleable.OnSwipe_dragScale) {
                this.s = typedArray.getFloat(index, this.s);
            } else if (index == R.styleable.OnSwipe_dragThreshold) {
                this.u = typedArray.getFloat(index, this.u);
            } else if (index == R.styleable.OnSwipe_touchRegionId) {
                this.e = typedArray.getResourceId(index, this.e);
            } else if (index == R.styleable.OnSwipe_onTouchUp) {
                this.f344c = typedArray.getInt(index, this.f344c);
            } else if (index == R.styleable.OnSwipe_nestedScrollFlags) {
                this.t = typedArray.getInteger(index, 0);
            } else if (index == R.styleable.OnSwipe_limitBoundsTo) {
                this.f = typedArray.getResourceId(index, 0);
            }
        }
    }

    private void c(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OnSwipe);
        b(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float a(float f, float f2) {
        return (f * this.f346i) + (f2 * this.j);
    }

    public int d() {
        return this.t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectF e(ViewGroup viewGroup, RectF rectF) {
        View findViewById;
        int i2 = this.f;
        if (i2 == -1 || (findViewById = viewGroup.findViewById(i2)) == null) {
            return null;
        }
        rectF.set(findViewById.getLeft(), findViewById.getTop(), findViewById.getRight(), findViewById.getBottom());
        return rectF;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float f() {
        return this.q;
    }

    public float g() {
        return this.p;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h() {
        return this.r;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float i(float f, float f2) {
        this.o.T(this.f345d, this.o.getProgress(), this.h, this.g, this.l);
        float f3 = this.f346i;
        if (f3 != 0.0f) {
            float[] fArr = this.l;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f * f3) / fArr[0];
        }
        float[] fArr2 = this.l;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f2 * this.j) / fArr2[1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectF j(ViewGroup viewGroup, RectF rectF) {
        View findViewById;
        int i2 = this.e;
        if (i2 == -1 || (findViewById = viewGroup.findViewById(i2)) == null) {
            return null;
        }
        rectF.set(findViewById.getLeft(), findViewById.getTop(), findViewById.getRight(), findViewById.getBottom());
        return rectF;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int k() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(MotionEvent motionEvent, MotionLayout.MotionTracker motionTracker, int i2, MotionScene motionScene) {
        int i3;
        motionTracker.addMovement(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.m = motionEvent.getRawX();
            this.n = motionEvent.getRawY();
            this.k = false;
            return;
        }
        if (action == 1) {
            this.k = false;
            motionTracker.computeCurrentVelocity(1000);
            float xVelocity = motionTracker.getXVelocity();
            float yVelocity = motionTracker.getYVelocity();
            float progress = this.o.getProgress();
            int i4 = this.f345d;
            if (i4 != -1) {
                this.o.T(i4, progress, this.h, this.g, this.l);
            } else {
                float min = Math.min(this.o.getWidth(), this.o.getHeight());
                float[] fArr = this.l;
                fArr[1] = this.j * min;
                fArr[0] = min * this.f346i;
            }
            float f = this.f346i;
            float[] fArr2 = this.l;
            float f2 = fArr2[0];
            float f3 = fArr2[1];
            float f4 = f != 0.0f ? xVelocity / fArr2[0] : yVelocity / fArr2[1];
            float f5 = !Float.isNaN(f4) ? (f4 / 3.0f) + progress : progress;
            if (f5 != 0.0f && f5 != 1.0f && (i3 = this.f344c) != 3) {
                this.o.touchAnimateTo(i3, ((double) f5) < 0.5d ? 0.0f : 1.0f, f4);
                if (0.0f < progress && 1.0f > progress) {
                    return;
                }
            } else if (0.0f < f5 && 1.0f > f5) {
                return;
            }
            this.o.setState(MotionLayout.h.FINISHED);
            return;
        }
        if (action != 2) {
            return;
        }
        float rawY = motionEvent.getRawY() - this.n;
        float rawX = motionEvent.getRawX() - this.m;
        if (Math.abs((this.f346i * rawX) + (this.j * rawY)) > this.u || this.k) {
            float progress2 = this.o.getProgress();
            if (!this.k) {
                this.k = true;
                this.o.setProgress(progress2);
            }
            int i5 = this.f345d;
            if (i5 != -1) {
                this.o.T(i5, progress2, this.h, this.g, this.l);
            } else {
                float min2 = Math.min(this.o.getWidth(), this.o.getHeight());
                float[] fArr3 = this.l;
                fArr3[1] = this.j * min2;
                fArr3[0] = min2 * this.f346i;
            }
            float f6 = this.f346i;
            float[] fArr4 = this.l;
            if (Math.abs(((f6 * fArr4[0]) + (this.j * fArr4[1])) * this.s) < 0.01d) {
                float[] fArr5 = this.l;
                fArr5[0] = 0.01f;
                fArr5[1] = 0.01f;
            }
            float max = Math.max(Math.min(progress2 + (this.f346i != 0.0f ? rawX / this.l[0] : rawY / this.l[1]), 1.0f), 0.0f);
            if (max != this.o.getProgress()) {
                this.o.setProgress(max);
                motionTracker.computeCurrentVelocity(1000);
                this.o.v = this.f346i != 0.0f ? motionTracker.getXVelocity() / this.l[0] : motionTracker.getYVelocity() / this.l[1];
            } else {
                this.o.v = 0.0f;
            }
            this.m = motionEvent.getRawX();
            this.n = motionEvent.getRawY();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(float f, float f2) {
        float progress = this.o.getProgress();
        if (!this.k) {
            this.k = true;
            this.o.setProgress(progress);
        }
        this.o.T(this.f345d, progress, this.h, this.g, this.l);
        float f3 = this.f346i;
        float[] fArr = this.l;
        if (Math.abs((f3 * fArr[0]) + (this.j * fArr[1])) < 0.01d) {
            float[] fArr2 = this.l;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f4 = this.f346i;
        float max = Math.max(Math.min(progress + (f4 != 0.0f ? (f * f4) / this.l[0] : (f2 * this.j) / this.l[1]), 1.0f), 0.0f);
        if (max != this.o.getProgress()) {
            this.o.setProgress(max);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(float f, float f2) {
        this.k = false;
        float progress = this.o.getProgress();
        this.o.T(this.f345d, progress, this.h, this.g, this.l);
        float f3 = this.f346i;
        float[] fArr = this.l;
        float f4 = fArr[0];
        float f5 = this.j;
        float f6 = fArr[1];
        float f7 = f3 != 0.0f ? (f * f3) / fArr[0] : (f2 * f5) / fArr[1];
        if (!Float.isNaN(f7)) {
            progress += f7 / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z = progress != 1.0f;
            int i2 = this.f344c;
            if ((i2 != 3) && z) {
                this.o.touchAnimateTo(i2, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f7);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(float f, float f2) {
        this.m = f;
        this.n = f2;
    }

    public void p(boolean z) {
        if (z) {
            float[][] fArr = w;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = v;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = w;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = v;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[][] fArr5 = v;
        int i2 = this.a;
        this.h = fArr5[i2][0];
        this.g = fArr5[i2][1];
        float[][] fArr6 = w;
        int i3 = this.b;
        this.f346i = fArr6[i3][0];
        this.j = fArr6[i3][1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(float f, float f2) {
        this.m = f;
        this.n = f2;
        this.k = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r() {
        View view;
        int i2 = this.f345d;
        if (i2 != -1) {
            view = this.o.findViewById(i2);
            if (view == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + Debug.getName(this.o.getContext(), this.f345d));
            }
        } else {
            view = null;
        }
        if (view instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            nestedScrollView.setOnTouchListener(new a(this));
            nestedScrollView.setOnScrollChangeListener(new b(this));
        }
    }

    public String toString() {
        return this.f346i + " , " + this.j;
    }
}
