package com.google.android.material.timepicker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.Dimension;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ClockHandView extends View {
    private ValueAnimator a;
    private boolean b;

    /* renamed from: c, reason: collision with root package name */
    private float f1736c;

    /* renamed from: d, reason: collision with root package name */
    private float f1737d;
    private boolean e;
    private int f;
    private final List<OnRotateListener> g;
    private final int h;

    /* renamed from: i, reason: collision with root package name */
    private final float f1738i;
    private final Paint j;
    private final RectF k;

    @Px
    private final int l;
    private float m;
    private boolean n;
    private OnActionUpListener o;
    private double p;
    private int q;

    /* loaded from: classes.dex */
    public interface OnActionUpListener {
        void onActionUp(@FloatRange(from = 0.0d, to = 360.0d) float f, boolean z);
    }

    /* loaded from: classes.dex */
    public interface OnRotateListener {
        void onRotate(@FloatRange(from = 0.0d, to = 360.0d) float f, boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            ClockHandView.this.n(((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends AnimatorListenerAdapter {
        b(ClockHandView clockHandView) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            animator.end();
        }
    }

    public ClockHandView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialClockStyle);
    }

    public ClockHandView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.g = new ArrayList();
        Paint paint = new Paint();
        this.j = paint;
        this.k = new RectF();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClockHandView, i2, R.style.Widget_MaterialComponents_TimePicker_Clock);
        this.q = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClockHandView_materialCircleRadius, 0);
        this.h = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ClockHandView_selectorSize, 0);
        this.l = getResources().getDimensionPixelSize(R.dimen.material_clock_hand_stroke_width);
        this.f1738i = r6.getDimensionPixelSize(R.dimen.material_clock_hand_center_dot_radius);
        int color = obtainStyledAttributes.getColor(R.styleable.ClockHandView_clockHandColor, 0);
        paint.setAntiAlias(true);
        paint.setColor(color);
        l(0.0f);
        this.f = ViewConfiguration.get(context).getScaledTouchSlop();
        ViewCompat.setImportantForAccessibility(this, 2);
        obtainStyledAttributes.recycle();
    }

    private void c(Canvas canvas) {
        int height = getHeight() / 2;
        float width = getWidth() / 2;
        float cos = (this.q * ((float) Math.cos(this.p))) + width;
        float f = height;
        float sin = (this.q * ((float) Math.sin(this.p))) + f;
        this.j.setStrokeWidth(0.0f);
        canvas.drawCircle(cos, sin, this.h, this.j);
        double sin2 = Math.sin(this.p);
        double cos2 = Math.cos(this.p);
        Double.isNaN(r6);
        Double.isNaN(r6);
        this.j.setStrokeWidth(this.l);
        canvas.drawLine(width, f, r1 + ((int) (cos2 * r6)), height + ((int) (r6 * sin2)), this.j);
        canvas.drawCircle(width, f, this.f1738i, this.j);
    }

    private int e(float f, float f2) {
        int degrees = ((int) Math.toDegrees(Math.atan2(f2 - (getHeight() / 2), f - (getWidth() / 2)))) + 90;
        return degrees < 0 ? degrees + 360 : degrees;
    }

    private Pair<Float, Float> h(float f) {
        float f2 = f();
        if (Math.abs(f2 - f) > 180.0f) {
            if (f2 > 180.0f && f < 180.0f) {
                f += 360.0f;
            }
            if (f2 < 180.0f && f > 180.0f) {
                f2 += 360.0f;
            }
        }
        return new Pair<>(Float.valueOf(f2), Float.valueOf(f));
    }

    private boolean i(float f, float f2, boolean z, boolean z2, boolean z3) {
        float e = e(f, f2);
        boolean z4 = false;
        boolean z5 = f() != e;
        if (z2 && z5) {
            return true;
        }
        if (!z5 && !z) {
            return false;
        }
        if (z3 && this.b) {
            z4 = true;
        }
        m(e, z4);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(@FloatRange(from = 0.0d, to = 360.0d) float f, boolean z) {
        float f2 = f % 360.0f;
        this.m = f2;
        this.p = Math.toRadians(f2 - 90.0f);
        int height = getHeight() / 2;
        float width = (getWidth() / 2) + (this.q * ((float) Math.cos(this.p)));
        float sin = height + (this.q * ((float) Math.sin(this.p)));
        RectF rectF = this.k;
        int i2 = this.h;
        rectF.set(width - i2, sin - i2, width + i2, sin + i2);
        Iterator<OnRotateListener> it = this.g.iterator();
        while (it.hasNext()) {
            it.next().onRotate(f2, z);
        }
        invalidate();
    }

    public void b(OnRotateListener onRotateListener) {
        this.g.add(onRotateListener);
    }

    public RectF d() {
        return this.k;
    }

    @FloatRange(from = 0.0d, to = 360.0d)
    public float f() {
        return this.m;
    }

    public int g() {
        return this.h;
    }

    public void j(boolean z) {
        this.b = z;
    }

    public void k(@Dimension int i2) {
        this.q = i2;
        invalidate();
    }

    public void l(@FloatRange(from = 0.0d, to = 360.0d) float f) {
        m(f, false);
    }

    public void m(@FloatRange(from = 0.0d, to = 360.0d) float f, boolean z) {
        ValueAnimator valueAnimator = this.a;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (!z) {
            n(f, false);
            return;
        }
        Pair<Float, Float> h = h(f);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(((Float) h.first).floatValue(), ((Float) h.second).floatValue());
        this.a = ofFloat;
        ofFloat.setDuration(200L);
        this.a.addUpdateListener(new a());
        this.a.addListener(new b(this));
        this.a.start();
    }

    public void o(OnActionUpListener onActionUpListener) {
        this.o = onActionUpListener;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        c(canvas);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        l(f());
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        boolean z3;
        OnActionUpListener onActionUpListener;
        int actionMasked = motionEvent.getActionMasked();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (actionMasked != 0) {
            if (actionMasked == 1 || actionMasked == 2) {
                int i2 = (int) (x - this.f1736c);
                int i3 = (int) (y - this.f1737d);
                this.e = (i2 * i2) + (i3 * i3) > this.f;
                boolean z4 = this.n;
                z = actionMasked == 1;
                z2 = z4;
            } else {
                z = false;
                z2 = false;
            }
            z3 = false;
        } else {
            this.f1736c = x;
            this.f1737d = y;
            this.e = true;
            this.n = false;
            z = false;
            z2 = false;
            z3 = true;
        }
        boolean i4 = i(x, y, z2, z3, z) | this.n;
        this.n = i4;
        if (i4 && z && (onActionUpListener = this.o) != null) {
            onActionUpListener.onActionUp(e(x, y), this.e);
        }
        return true;
    }
}
