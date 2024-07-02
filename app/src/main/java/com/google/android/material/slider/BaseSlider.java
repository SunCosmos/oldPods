package com.google.android.material.slider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.ActivityChooserView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.SeekBar;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewOverlayImpl;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.slider.BaseOnChangeListener;
import com.google.android.material.slider.BaseOnSliderTouchListener;
import com.google.android.material.slider.BaseSlider;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.android.material.tooltip.TooltipDrawable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends BaseOnChangeListener<S>, T extends BaseOnSliderTouchListener<S>> extends View {
    private static final String c0 = BaseSlider.class.getSimpleName();
    static final int d0 = R.style.Widget_MaterialComponents_Slider;
    private int A;
    private float B;
    private MotionEvent C;
    private LabelFormatter D;
    private boolean E;
    private float F;
    private float G;
    private ArrayList<Float> H;
    private int I;
    private int J;
    private float K;
    private float[] L;
    private boolean M;
    private int N;
    private boolean O;
    private boolean P;
    private boolean Q;

    @NonNull
    private ColorStateList R;

    @NonNull
    private ColorStateList S;

    @NonNull
    private ColorStateList T;

    @NonNull
    private ColorStateList U;

    @NonNull
    private ColorStateList V;

    @NonNull
    private final MaterialShapeDrawable W;

    @NonNull
    private final Paint a;
    private float a0;

    @NonNull
    private final Paint b;
    private int b0;

    /* renamed from: c, reason: collision with root package name */
    @NonNull
    private final Paint f1685c;

    /* renamed from: d, reason: collision with root package name */
    @NonNull
    private final Paint f1686d;

    @NonNull
    private final Paint e;

    @NonNull
    private final Paint f;

    @NonNull
    private final e g;
    private final AccessibilityManager h;

    /* renamed from: i, reason: collision with root package name */
    private BaseSlider<S, L, T>.d f1687i;

    @NonNull
    private final f j;

    @NonNull
    private final List<TooltipDrawable> k;

    @NonNull
    private final List<L> l;

    @NonNull
    private final List<T> m;
    private boolean n;
    private ValueAnimator o;
    private ValueAnimator p;
    private final int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new a();
        float a;
        float b;

        /* renamed from: c, reason: collision with root package name */
        ArrayList<Float> f1688c;

        /* renamed from: d, reason: collision with root package name */
        float f1689d;
        boolean e;

        /* loaded from: classes.dex */
        static class a implements Parcelable.Creator<SliderState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SliderState createFromParcel(@NonNull Parcel parcel) {
                return new SliderState(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            @NonNull
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SliderState[] newArray(int i2) {
                return new SliderState[i2];
            }
        }

        private SliderState(@NonNull Parcel parcel) {
            super(parcel);
            this.a = parcel.readFloat();
            this.b = parcel.readFloat();
            ArrayList<Float> arrayList = new ArrayList<>();
            this.f1688c = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.f1689d = parcel.readFloat();
            this.e = parcel.createBooleanArray()[0];
        }

        /* synthetic */ SliderState(Parcel parcel, a aVar) {
            this(parcel);
        }

        SliderState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeFloat(this.a);
            parcel.writeFloat(this.b);
            parcel.writeList(this.f1688c);
            parcel.writeFloat(this.f1689d);
            parcel.writeBooleanArray(new boolean[]{this.e});
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements f {
        final /* synthetic */ AttributeSet a;
        final /* synthetic */ int b;

        a(AttributeSet attributeSet, int i2) {
            this.a = attributeSet;
            this.b = i2;
        }

        @Override // com.google.android.material.slider.BaseSlider.f
        public TooltipDrawable a() {
            TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(BaseSlider.this.getContext(), this.a, R.styleable.Slider, this.b, BaseSlider.d0, new int[0]);
            TooltipDrawable Q = BaseSlider.Q(BaseSlider.this.getContext(), obtainStyledAttributes);
            obtainStyledAttributes.recycle();
            return Q;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {
        b() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            Iterator it = BaseSlider.this.k.iterator();
            while (it.hasNext()) {
                ((TooltipDrawable) it.next()).setRevealFraction(floatValue);
            }
            ViewCompat.postInvalidateOnAnimation(BaseSlider.this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends AnimatorListenerAdapter {
        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            Iterator it = BaseSlider.this.k.iterator();
            while (it.hasNext()) {
                ViewUtils.getContentViewOverlay(BaseSlider.this).remove((TooltipDrawable) it.next());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d implements Runnable {
        int a;

        private d() {
            this.a = -1;
        }

        /* synthetic */ d(BaseSlider baseSlider, a aVar) {
            this();
        }

        void a(int i2) {
            this.a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseSlider.this.g.sendEventForVirtualView(this.a, 4);
        }
    }

    /* loaded from: classes.dex */
    private static class e extends ExploreByTouchHelper {
        private final BaseSlider<?, ?, ?> q;
        Rect r;

        e(BaseSlider<?, ?, ?> baseSlider) {
            super(baseSlider);
            this.r = new Rect();
            this.q = baseSlider;
        }

        @NonNull
        private String F(int i2) {
            Context context;
            int i3;
            if (i2 == this.q.getValues().size() - 1) {
                context = this.q.getContext();
                i3 = R.string.material_slider_range_end;
            } else {
                if (i2 != 0) {
                    return "";
                }
                context = this.q.getContext();
                i3 = R.string.material_slider_range_start;
            }
            return context.getString(i3);
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected int n(float f, float f2) {
            for (int i2 = 0; i2 < this.q.getValues().size(); i2++) {
                this.q.b0(i2, this.r);
                if (this.r.contains((int) f, (int) f2)) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected void o(List<Integer> list) {
            for (int i2 = 0; i2 < this.q.getValues().size(); i2++) {
                list.add(Integer.valueOf(i2));
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x002e, code lost:
        
            if (r4.q.Z(r5, r7.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) != false) goto L17;
         */
        @Override // androidx.customview.widget.ExploreByTouchHelper
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        protected boolean u(int r5, int r6, android.os.Bundle r7) {
            /*
                r4 = this;
                com.google.android.material.slider.BaseSlider<?, ?, ?> r0 = r4.q
                boolean r0 = r0.isEnabled()
                r1 = 0
                if (r0 != 0) goto La
                return r1
            La:
                r0 = 4096(0x1000, float:5.74E-42)
                r2 = 1
                r3 = 8192(0x2000, float:1.148E-41)
                if (r6 == r0) goto L3f
                if (r6 == r3) goto L3f
                r0 = 16908349(0x102003d, float:2.38774E-38)
                if (r6 == r0) goto L19
                return r1
            L19:
                if (r7 == 0) goto L3e
                java.lang.String r6 = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"
                boolean r0 = r7.containsKey(r6)
                if (r0 != 0) goto L24
                goto L3e
            L24:
                float r6 = r7.getFloat(r6)
                com.google.android.material.slider.BaseSlider<?, ?, ?> r7 = r4.q
                boolean r6 = com.google.android.material.slider.BaseSlider.e(r7, r5, r6)
                if (r6 == 0) goto L3e
            L30:
                com.google.android.material.slider.BaseSlider<?, ?, ?> r6 = r4.q
                com.google.android.material.slider.BaseSlider.f(r6)
                com.google.android.material.slider.BaseSlider<?, ?, ?> r6 = r4.q
                r6.postInvalidate()
                r4.invalidateVirtualView(r5)
                return r2
            L3e:
                return r1
            L3f:
                com.google.android.material.slider.BaseSlider<?, ?, ?> r7 = r4.q
                r0 = 20
                float r7 = com.google.android.material.slider.BaseSlider.g(r7, r0)
                if (r6 != r3) goto L4a
                float r7 = -r7
            L4a:
                com.google.android.material.slider.BaseSlider<?, ?, ?> r6 = r4.q
                boolean r6 = r6.E()
                if (r6 == 0) goto L53
                float r7 = -r7
            L53:
                com.google.android.material.slider.BaseSlider<?, ?, ?> r6 = r4.q
                java.util.List r6 = r6.getValues()
                java.lang.Object r6 = r6.get(r5)
                java.lang.Float r6 = (java.lang.Float) r6
                float r6 = r6.floatValue()
                float r6 = r6 + r7
                com.google.android.material.slider.BaseSlider<?, ?, ?> r7 = r4.q
                float r7 = r7.getValueFrom()
                com.google.android.material.slider.BaseSlider<?, ?, ?> r0 = r4.q
                float r0 = r0.getValueTo()
                float r6 = androidx.core.math.MathUtils.clamp(r6, r7, r0)
                com.google.android.material.slider.BaseSlider<?, ?, ?> r7 = r4.q
                boolean r6 = com.google.android.material.slider.BaseSlider.e(r7, r5, r6)
                if (r6 == 0) goto L7d
                goto L30
            L7d:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.e.u(int, int, android.os.Bundle):boolean");
        }

        @Override // androidx.customview.widget.ExploreByTouchHelper
        protected void y(int i2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SET_PROGRESS);
            List<Float> values = this.q.getValues();
            float floatValue = values.get(i2).floatValue();
            float valueFrom = this.q.getValueFrom();
            float valueTo = this.q.getValueTo();
            if (this.q.isEnabled()) {
                if (floatValue > valueFrom) {
                    accessibilityNodeInfoCompat.addAction(8192);
                }
                if (floatValue < valueTo) {
                    accessibilityNodeInfoCompat.addAction(4096);
                }
            }
            accessibilityNodeInfoCompat.setRangeInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(1, valueFrom, valueTo, floatValue));
            accessibilityNodeInfoCompat.setClassName(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (this.q.getContentDescription() != null) {
                sb.append(this.q.getContentDescription());
                sb.append(",");
            }
            if (values.size() > 1) {
                sb.append(F(i2));
                sb.append(this.q.y(floatValue));
            }
            accessibilityNodeInfoCompat.setContentDescription(sb.toString());
            this.q.b0(i2, this.r);
            accessibilityNodeInfoCompat.setBoundsInParent(this.r);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface f {
        TooltipDrawable a();
    }

    public BaseSlider(@NonNull Context context) {
        this(context, null);
    }

    public BaseSlider(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.sliderStyle);
    }

    public BaseSlider(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i2, d0), attributeSet, i2);
        this.k = new ArrayList();
        this.l = new ArrayList();
        this.m = new ArrayList();
        this.n = false;
        this.E = false;
        this.H = new ArrayList<>();
        this.I = -1;
        this.J = -1;
        this.K = 0.0f;
        this.M = true;
        this.P = false;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.W = materialShapeDrawable;
        this.b0 = 0;
        Context context2 = getContext();
        Paint paint = new Paint();
        this.a = paint;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.b = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        Paint paint3 = new Paint(1);
        this.f1685c = paint3;
        paint3.setStyle(Paint.Style.FILL);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint4 = new Paint(1);
        this.f1686d = paint4;
        paint4.setStyle(Paint.Style.FILL);
        Paint paint5 = new Paint();
        this.e = paint5;
        paint5.setStyle(Paint.Style.STROKE);
        paint5.setStrokeCap(Paint.Cap.ROUND);
        Paint paint6 = new Paint();
        this.f = paint6;
        paint6.setStyle(Paint.Style.STROKE);
        paint6.setStrokeCap(Paint.Cap.ROUND);
        F(context2.getResources());
        this.j = new a(attributeSet, i2);
        T(context2, attributeSet, i2);
        setFocusable(true);
        setClickable(true);
        materialShapeDrawable.setShadowCompatibilityMode(2);
        this.q = ViewConfiguration.get(context2).getScaledTouchSlop();
        e eVar = new e(this);
        this.g = eVar;
        ViewCompat.setAccessibilityDelegate(this, eVar);
        this.h = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    private float A(int i2, float f2) {
        float minSeparation = this.K == 0.0f ? getMinSeparation() : 0.0f;
        if (this.b0 == 0) {
            minSeparation = p(minSeparation);
        }
        if (E()) {
            minSeparation = -minSeparation;
        }
        int i3 = i2 + 1;
        int i4 = i2 - 1;
        return MathUtils.clamp(f2, i4 < 0 ? this.F : this.H.get(i4).floatValue() + minSeparation, i3 >= this.H.size() ? this.G : this.H.get(i3).floatValue() - minSeparation);
    }

    @ColorInt
    private int B(@NonNull ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    private void C() {
        this.a.setStrokeWidth(this.v);
        this.b.setStrokeWidth(this.v);
        this.e.setStrokeWidth(this.v / 2.0f);
        this.f.setStrokeWidth(this.v / 2.0f);
    }

    private boolean D() {
        ViewParent parent = getParent();
        while (true) {
            if (!(parent instanceof ViewGroup)) {
                return false;
            }
            ViewGroup viewGroup = (ViewGroup) parent;
            if ((viewGroup.canScrollVertically(1) || viewGroup.canScrollVertically(-1)) && viewGroup.shouldDelayChildPressedState()) {
                return true;
            }
            parent = parent.getParent();
        }
    }

    private void F(@NonNull Resources resources) {
        this.t = resources.getDimensionPixelSize(R.dimen.mtrl_slider_widget_height);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_slider_track_side_padding);
        this.r = dimensionPixelOffset;
        this.w = dimensionPixelOffset;
        this.s = resources.getDimensionPixelSize(R.dimen.mtrl_slider_thumb_radius);
        this.x = resources.getDimensionPixelOffset(R.dimen.mtrl_slider_track_top);
        this.A = resources.getDimensionPixelSize(R.dimen.mtrl_slider_label_padding);
    }

    private void G() {
        if (this.K <= 0.0f) {
            return;
        }
        e0();
        int min = Math.min((int) (((this.G - this.F) / this.K) + 1.0f), (this.N / (this.v * 2)) + 1);
        float[] fArr = this.L;
        if (fArr == null || fArr.length != min * 2) {
            this.L = new float[min * 2];
        }
        float f2 = this.N / (min - 1);
        for (int i2 = 0; i2 < min * 2; i2 += 2) {
            float[] fArr2 = this.L;
            fArr2[i2] = this.w + ((i2 / 2) * f2);
            fArr2[i2 + 1] = l();
        }
    }

    private void H(@NonNull Canvas canvas, int i2, int i3) {
        if (W()) {
            int M = (int) (this.w + (M(this.H.get(this.J).floatValue()) * i2));
            if (Build.VERSION.SDK_INT < 28) {
                int i4 = this.z;
                canvas.clipRect(M - i4, i3 - i4, M + i4, i4 + i3, Region.Op.UNION);
            }
            canvas.drawCircle(M, i3, this.z, this.f1686d);
        }
    }

    private void I(@NonNull Canvas canvas) {
        if (!this.M || this.K <= 0.0f) {
            return;
        }
        float[] activeRange = getActiveRange();
        int S = S(this.L, activeRange[0]);
        int S2 = S(this.L, activeRange[1]);
        int i2 = S * 2;
        canvas.drawPoints(this.L, 0, i2, this.e);
        int i3 = S2 * 2;
        canvas.drawPoints(this.L, i2, i3 - i2, this.f);
        float[] fArr = this.L;
        canvas.drawPoints(fArr, i3, fArr.length - i3, this.e);
    }

    private void J() {
        this.w = this.r + Math.max(this.y - this.s, 0);
        if (ViewCompat.isLaidOut(this)) {
            d0(getWidth());
        }
    }

    private boolean K(int i2) {
        int i3 = this.J;
        int clamp = (int) MathUtils.clamp(i3 + i2, 0L, this.H.size() - 1);
        this.J = clamp;
        if (clamp == i3) {
            return false;
        }
        if (this.I != -1) {
            this.I = clamp;
        }
        c0();
        postInvalidate();
        return true;
    }

    private boolean L(int i2) {
        if (E()) {
            i2 = i2 == Integer.MIN_VALUE ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : -i2;
        }
        return K(i2);
    }

    private float M(float f2) {
        float f3 = this.F;
        float f4 = (f2 - f3) / (this.G - f3);
        return E() ? 1.0f - f4 : f4;
    }

    private Boolean N(int i2, @NonNull KeyEvent keyEvent) {
        boolean K;
        Boolean bool = Boolean.TRUE;
        if (i2 == 61) {
            if (keyEvent.hasNoModifiers()) {
                K = K(1);
            } else {
                if (!keyEvent.isShiftPressed()) {
                    return Boolean.FALSE;
                }
                K = K(-1);
            }
            return Boolean.valueOf(K);
        }
        if (i2 != 66) {
            if (i2 != 81) {
                if (i2 == 69) {
                    K(-1);
                    return bool;
                }
                if (i2 != 70) {
                    switch (i2) {
                        case 21:
                            L(-1);
                            return bool;
                        case 22:
                            L(1);
                            return bool;
                        case 23:
                            break;
                        default:
                            return null;
                    }
                }
            }
            K(1);
            return bool;
        }
        this.I = this.J;
        postInvalidate();
        return bool;
    }

    private void O() {
        Iterator<T> it = this.m.iterator();
        while (it.hasNext()) {
            it.next().onStartTrackingTouch(this);
        }
    }

    private void P() {
        Iterator<T> it = this.m.iterator();
        while (it.hasNext()) {
            it.next().onStopTrackingTouch(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public static TooltipDrawable Q(@NonNull Context context, @NonNull TypedArray typedArray) {
        return TooltipDrawable.createFromAttributes(context, null, 0, typedArray.getResourceId(R.styleable.Slider_labelStyle, R.style.Widget_MaterialComponents_Tooltip));
    }

    private static int S(float[] fArr, float f2) {
        return Math.round(f2 * ((fArr.length / 2) - 1));
    }

    private void T(Context context, AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.Slider, i2, d0, new int[0]);
        this.F = obtainStyledAttributes.getFloat(R.styleable.Slider_android_valueFrom, 0.0f);
        this.G = obtainStyledAttributes.getFloat(R.styleable.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.F));
        this.K = obtainStyledAttributes.getFloat(R.styleable.Slider_android_stepSize, 0.0f);
        int i3 = R.styleable.Slider_trackColor;
        boolean hasValue = obtainStyledAttributes.hasValue(i3);
        int i4 = hasValue ? i3 : R.styleable.Slider_trackColorInactive;
        if (!hasValue) {
            i3 = R.styleable.Slider_trackColorActive;
        }
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, i4);
        if (colorStateList == null) {
            colorStateList = AppCompatResources.getColorStateList(context, R.color.material_slider_inactive_track_color);
        }
        setTrackInactiveTintList(colorStateList);
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(context, obtainStyledAttributes, i3);
        if (colorStateList2 == null) {
            colorStateList2 = AppCompatResources.getColorStateList(context, R.color.material_slider_active_track_color);
        }
        setTrackActiveTintList(colorStateList2);
        this.W.setFillColor(MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.Slider_thumbColor));
        int i5 = R.styleable.Slider_thumbStrokeColor;
        if (obtainStyledAttributes.hasValue(i5)) {
            setThumbStrokeColor(MaterialResources.getColorStateList(context, obtainStyledAttributes, i5));
        }
        setThumbStrokeWidth(obtainStyledAttributes.getDimension(R.styleable.Slider_thumbStrokeWidth, 0.0f));
        ColorStateList colorStateList3 = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.Slider_haloColor);
        if (colorStateList3 == null) {
            colorStateList3 = AppCompatResources.getColorStateList(context, R.color.material_slider_halo_color);
        }
        setHaloTintList(colorStateList3);
        this.M = obtainStyledAttributes.getBoolean(R.styleable.Slider_tickVisible, true);
        int i6 = R.styleable.Slider_tickColor;
        boolean hasValue2 = obtainStyledAttributes.hasValue(i6);
        int i7 = hasValue2 ? i6 : R.styleable.Slider_tickColorInactive;
        if (!hasValue2) {
            i6 = R.styleable.Slider_tickColorActive;
        }
        ColorStateList colorStateList4 = MaterialResources.getColorStateList(context, obtainStyledAttributes, i7);
        if (colorStateList4 == null) {
            colorStateList4 = AppCompatResources.getColorStateList(context, R.color.material_slider_inactive_tick_marks_color);
        }
        setTickInactiveTintList(colorStateList4);
        ColorStateList colorStateList5 = MaterialResources.getColorStateList(context, obtainStyledAttributes, i6);
        if (colorStateList5 == null) {
            colorStateList5 = AppCompatResources.getColorStateList(context, R.color.material_slider_active_tick_marks_color);
        }
        setTickActiveTintList(colorStateList5);
        setThumbRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_thumbRadius, 0));
        setHaloRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_haloRadius, 0));
        setThumbElevation(obtainStyledAttributes.getDimension(R.styleable.Slider_thumbElevation, 0.0f));
        setTrackHeight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_trackHeight, 0));
        this.u = obtainStyledAttributes.getInt(R.styleable.Slider_labelBehavior, 0);
        if (!obtainStyledAttributes.getBoolean(R.styleable.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        obtainStyledAttributes.recycle();
    }

    private void U(int i2) {
        BaseSlider<S, L, T>.d dVar = this.f1687i;
        if (dVar == null) {
            this.f1687i = new d(this, null);
        } else {
            removeCallbacks(dVar);
        }
        this.f1687i.a(i2);
        postDelayed(this.f1687i, 200L);
    }

    private void V(TooltipDrawable tooltipDrawable, float f2) {
        tooltipDrawable.setText(y(f2));
        int M = (this.w + ((int) (M(f2) * this.N))) - (tooltipDrawable.getIntrinsicWidth() / 2);
        int l = l() - (this.A + this.y);
        tooltipDrawable.setBounds(M, l - tooltipDrawable.getIntrinsicHeight(), tooltipDrawable.getIntrinsicWidth() + M, l);
        Rect rect = new Rect(tooltipDrawable.getBounds());
        DescendantOffsetUtils.offsetDescendantRect(ViewUtils.getContentView(this), this, rect);
        tooltipDrawable.setBounds(rect);
        ViewUtils.getContentViewOverlay(this).add(tooltipDrawable);
    }

    private boolean W() {
        return this.O || Build.VERSION.SDK_INT < 21 || !(getBackground() instanceof RippleDrawable);
    }

    private boolean X(float f2) {
        return Z(this.I, f2);
    }

    private double Y(float f2) {
        float f3 = this.K;
        if (f3 <= 0.0f) {
            return f2;
        }
        int i2 = (int) ((this.G - this.F) / f3);
        double round = Math.round(f2 * i2);
        double d2 = i2;
        Double.isNaN(round);
        Double.isNaN(d2);
        return round / d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean Z(int i2, float f2) {
        if (Math.abs(f2 - this.H.get(i2).floatValue()) < 1.0E-4d) {
            return false;
        }
        this.H.set(i2, Float.valueOf(A(i2, f2)));
        this.J = i2;
        q(i2);
        return true;
    }

    private boolean a0() {
        return X(getValueOfTouchPosition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c0() {
        if (W() || getMeasuredWidth() <= 0) {
            return;
        }
        Drawable background = getBackground();
        if (background instanceof RippleDrawable) {
            int M = (int) ((M(this.H.get(this.J).floatValue()) * this.N) + this.w);
            int l = l();
            int i2 = this.z;
            DrawableCompat.setHotspotBounds(background, M - i2, l - i2, M + i2, l + i2);
        }
    }

    private void d0(int i2) {
        this.N = Math.max(i2 - (this.w * 2), 0);
        G();
    }

    private void e0() {
        if (this.Q) {
            g0();
            h0();
            f0();
            i0();
            l0();
            this.Q = false;
        }
    }

    private void f0() {
        if (this.K > 0.0f && !j0(this.G)) {
            throw new IllegalStateException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.toString(this.K), Float.toString(this.F), Float.toString(this.G)));
        }
    }

    private void g0() {
        if (this.F >= this.G) {
            throw new IllegalStateException(String.format("valueFrom(%s) must be smaller than valueTo(%s)", Float.toString(this.F), Float.toString(this.G)));
        }
    }

    private float[] getActiveRange() {
        float floatValue = ((Float) Collections.max(getValues())).floatValue();
        float floatValue2 = ((Float) Collections.min(getValues())).floatValue();
        if (this.H.size() == 1) {
            floatValue2 = this.F;
        }
        float M = M(floatValue2);
        float M2 = M(floatValue);
        return E() ? new float[]{M2, M} : new float[]{M, M2};
    }

    private float getValueOfTouchPosition() {
        double Y = Y(this.a0);
        if (E()) {
            Y = 1.0d - Y;
        }
        float f2 = this.G;
        float f3 = this.F;
        double d2 = f2 - f3;
        Double.isNaN(d2);
        double d3 = f3;
        Double.isNaN(d3);
        return (float) ((Y * d2) + d3);
    }

    private float getValueOfTouchPositionAbsolute() {
        float f2 = this.a0;
        if (E()) {
            f2 = 1.0f - f2;
        }
        float f3 = this.G;
        float f4 = this.F;
        return (f2 * (f3 - f4)) + f4;
    }

    private void h(TooltipDrawable tooltipDrawable) {
        tooltipDrawable.setRelativeToView(ViewUtils.getContentView(this));
    }

    private void h0() {
        if (this.G <= this.F) {
            throw new IllegalStateException(String.format("valueTo(%s) must be greater than valueFrom(%s)", Float.toString(this.G), Float.toString(this.F)));
        }
    }

    private Float i(int i2) {
        float k = this.P ? k(20) : j();
        if (i2 == 21) {
            if (!E()) {
                k = -k;
            }
            return Float.valueOf(k);
        }
        if (i2 == 22) {
            if (E()) {
                k = -k;
            }
            return Float.valueOf(k);
        }
        if (i2 == 69) {
            return Float.valueOf(-k);
        }
        if (i2 == 70 || i2 == 81) {
            return Float.valueOf(k);
        }
        return null;
    }

    private void i0() {
        Iterator<Float> it = this.H.iterator();
        while (it.hasNext()) {
            Float next = it.next();
            if (next.floatValue() < this.F || next.floatValue() > this.G) {
                throw new IllegalStateException(String.format("Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)", Float.toString(next.floatValue()), Float.toString(this.F), Float.toString(this.G)));
            }
            if (this.K > 0.0f && !j0(next.floatValue())) {
                throw new IllegalStateException(String.format("Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)", Float.toString(next.floatValue()), Float.toString(this.F), Float.toString(this.K), Float.toString(this.K)));
            }
        }
    }

    private float j() {
        float f2 = this.K;
        if (f2 == 0.0f) {
            return 1.0f;
        }
        return f2;
    }

    private boolean j0(float f2) {
        double doubleValue = new BigDecimal(Float.toString(f2)).subtract(new BigDecimal(Float.toString(this.F))).divide(new BigDecimal(Float.toString(this.K)), MathContext.DECIMAL64).doubleValue();
        double round = Math.round(doubleValue);
        Double.isNaN(round);
        return Math.abs(round - doubleValue) < 1.0E-4d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float k(int i2) {
        float j = j();
        return (this.G - this.F) / j <= i2 ? j : Math.round(r1 / r4) * j;
    }

    private float k0(float f2) {
        return (M(f2) * this.N) + this.w;
    }

    private int l() {
        return this.x + (this.u == 1 ? this.k.get(0).getIntrinsicHeight() : 0);
    }

    private void l0() {
        float f2 = this.K;
        if (f2 == 0.0f) {
            return;
        }
        if (((int) f2) != f2) {
            Log.w(c0, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "stepSize", Float.valueOf(f2)));
        }
        float f3 = this.F;
        if (((int) f3) != f3) {
            Log.w(c0, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueFrom", Float.valueOf(f3)));
        }
        float f4 = this.G;
        if (((int) f4) != f4) {
            Log.w(c0, String.format("Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the  value correctly.", "valueTo", Float.valueOf(f4)));
        }
    }

    private ValueAnimator m(boolean z) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(z(z ? this.p : this.o, z ? 0.0f : 1.0f), z ? 1.0f : 0.0f);
        ofFloat.setDuration(z ? 83L : 117L);
        ofFloat.setInterpolator(z ? AnimationUtils.DECELERATE_INTERPOLATOR : AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        ofFloat.addUpdateListener(new b());
        return ofFloat;
    }

    private void n() {
        if (this.k.size() > this.H.size()) {
            List<TooltipDrawable> subList = this.k.subList(this.H.size(), this.k.size());
            for (TooltipDrawable tooltipDrawable : subList) {
                if (ViewCompat.isAttachedToWindow(this)) {
                    o(tooltipDrawable);
                }
            }
            subList.clear();
        }
        while (this.k.size() < this.H.size()) {
            TooltipDrawable a2 = this.j.a();
            this.k.add(a2);
            if (ViewCompat.isAttachedToWindow(this)) {
                h(a2);
            }
        }
        int i2 = this.k.size() == 1 ? 0 : 1;
        Iterator<TooltipDrawable> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().setStrokeWidth(i2);
        }
    }

    private void o(TooltipDrawable tooltipDrawable) {
        ViewOverlayImpl contentViewOverlay = ViewUtils.getContentViewOverlay(this);
        if (contentViewOverlay != null) {
            contentViewOverlay.remove(tooltipDrawable);
            tooltipDrawable.detachView(ViewUtils.getContentView(this));
        }
    }

    private float p(float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        float f3 = (f2 - this.w) / this.N;
        float f4 = this.F;
        return (f3 * (f4 - this.G)) + f4;
    }

    private void q(int i2) {
        Iterator<L> it = this.l.iterator();
        while (it.hasNext()) {
            it.next().onValueChange(this, this.H.get(i2).floatValue(), true);
        }
        AccessibilityManager accessibilityManager = this.h;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        U(i2);
    }

    private void r() {
        for (L l : this.l) {
            Iterator<Float> it = this.H.iterator();
            while (it.hasNext()) {
                l.onValueChange(this, it.next().floatValue(), false);
            }
        }
    }

    private void s(@NonNull Canvas canvas, int i2, int i3) {
        float[] activeRange = getActiveRange();
        int i4 = this.w;
        float f2 = i2;
        float f3 = i3;
        canvas.drawLine(i4 + (activeRange[0] * f2), f3, i4 + (activeRange[1] * f2), f3, this.b);
    }

    private void setValuesInternal(@NonNull ArrayList<Float> arrayList) {
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("At least one value must be set");
        }
        Collections.sort(arrayList);
        if (this.H.size() == arrayList.size() && this.H.equals(arrayList)) {
            return;
        }
        this.H = arrayList;
        this.Q = true;
        this.J = 0;
        c0();
        n();
        r();
        postInvalidate();
    }

    private void t(@NonNull Canvas canvas, int i2, int i3) {
        float[] activeRange = getActiveRange();
        float f2 = i2;
        float f3 = this.w + (activeRange[1] * f2);
        if (f3 < r1 + i2) {
            float f4 = i3;
            canvas.drawLine(f3, f4, r1 + i2, f4, this.a);
        }
        int i4 = this.w;
        float f5 = i4 + (activeRange[0] * f2);
        if (f5 > i4) {
            float f6 = i3;
            canvas.drawLine(i4, f6, f5, f6, this.a);
        }
    }

    private void u(@NonNull Canvas canvas, int i2, int i3) {
        if (!isEnabled()) {
            Iterator<Float> it = this.H.iterator();
            while (it.hasNext()) {
                canvas.drawCircle(this.w + (M(it.next().floatValue()) * i2), i3, this.y, this.f1685c);
            }
        }
        Iterator<Float> it2 = this.H.iterator();
        while (it2.hasNext()) {
            Float next = it2.next();
            canvas.save();
            int M = this.w + ((int) (M(next.floatValue()) * i2));
            int i4 = this.y;
            canvas.translate(M - i4, i3 - i4);
            this.W.draw(canvas);
            canvas.restore();
        }
    }

    private void v() {
        if (this.u == 2) {
            return;
        }
        if (!this.n) {
            this.n = true;
            ValueAnimator m = m(true);
            this.o = m;
            this.p = null;
            m.start();
        }
        Iterator<TooltipDrawable> it = this.k.iterator();
        for (int i2 = 0; i2 < this.H.size() && it.hasNext(); i2++) {
            if (i2 != this.J) {
                V(it.next(), this.H.get(i2).floatValue());
            }
        }
        if (!it.hasNext()) {
            throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", Integer.valueOf(this.k.size()), Integer.valueOf(this.H.size())));
        }
        V(it.next(), this.H.get(this.J).floatValue());
    }

    private void w() {
        if (this.n) {
            this.n = false;
            ValueAnimator m = m(false);
            this.p = m;
            this.o = null;
            m.addListener(new c());
            this.p.start();
        }
    }

    private void x(int i2) {
        if (i2 == 1) {
            K(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            return;
        }
        if (i2 == 2) {
            K(Integer.MIN_VALUE);
        } else if (i2 == 17) {
            L(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        } else {
            if (i2 != 66) {
                return;
            }
            L(Integer.MIN_VALUE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String y(float f2) {
        if (hasLabelFormatter()) {
            return this.D.getFormattedValue(f2);
        }
        return String.format(((float) ((int) f2)) == f2 ? "%.0f" : "%.2f", Float.valueOf(f2));
    }

    private static float z(ValueAnimator valueAnimator, float f2) {
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return f2;
        }
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        valueAnimator.cancel();
        return floatValue;
    }

    final boolean E() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    protected boolean R() {
        if (this.I != -1) {
            return true;
        }
        float valueOfTouchPositionAbsolute = getValueOfTouchPositionAbsolute();
        float k0 = k0(valueOfTouchPositionAbsolute);
        this.I = 0;
        float abs = Math.abs(this.H.get(0).floatValue() - valueOfTouchPositionAbsolute);
        for (int i2 = 1; i2 < this.H.size(); i2++) {
            float abs2 = Math.abs(this.H.get(i2).floatValue() - valueOfTouchPositionAbsolute);
            float k02 = k0(this.H.get(i2).floatValue());
            if (Float.compare(abs2, abs) > 1) {
                break;
            }
            boolean z = !E() ? k02 - k0 >= 0.0f : k02 - k0 <= 0.0f;
            if (Float.compare(abs2, abs) >= 0) {
                if (Float.compare(abs2, abs) != 0) {
                    continue;
                } else {
                    if (Math.abs(k02 - k0) < this.q) {
                        this.I = -1;
                        return false;
                    }
                    if (!z) {
                    }
                }
            }
            this.I = i2;
            abs = abs2;
        }
        return this.I != -1;
    }

    public void addOnChangeListener(@Nullable L l) {
        this.l.add(l);
    }

    public void addOnSliderTouchListener(@NonNull T t) {
        this.m.add(t);
    }

    void b0(int i2, Rect rect) {
        int M = this.w + ((int) (M(getValues().get(i2).floatValue()) * this.N));
        int l = l();
        int i3 = this.y;
        rect.set(M - i3, l - i3, M + i3, l + i3);
    }

    public void clearOnChangeListeners() {
        this.l.clear();
    }

    public void clearOnSliderTouchListeners() {
        this.m.clear();
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(@NonNull MotionEvent motionEvent) {
        return this.g.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(@NonNull KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.a.setColor(B(this.V));
        this.b.setColor(B(this.U));
        this.e.setColor(B(this.T));
        this.f.setColor(B(this.S));
        for (TooltipDrawable tooltipDrawable : this.k) {
            if (tooltipDrawable.isStateful()) {
                tooltipDrawable.setState(getDrawableState());
            }
        }
        if (this.W.isStateful()) {
            this.W.setState(getDrawableState());
        }
        this.f1686d.setColor(B(this.R));
        this.f1686d.setAlpha(63);
    }

    @Override // android.view.View
    @NonNull
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    @VisibleForTesting
    final int getAccessibilityFocusedVirtualViewId() {
        return this.g.getAccessibilityFocusedVirtualViewId();
    }

    public int getActiveThumbIndex() {
        return this.I;
    }

    public int getFocusedThumbIndex() {
        return this.J;
    }

    @Dimension
    public int getHaloRadius() {
        return this.z;
    }

    @NonNull
    public ColorStateList getHaloTintList() {
        return this.R;
    }

    public int getLabelBehavior() {
        return this.u;
    }

    protected float getMinSeparation() {
        return 0.0f;
    }

    public float getStepSize() {
        return this.K;
    }

    public float getThumbElevation() {
        return this.W.getElevation();
    }

    @Dimension
    public int getThumbRadius() {
        return this.y;
    }

    public ColorStateList getThumbStrokeColor() {
        return this.W.getStrokeColor();
    }

    public float getThumbStrokeWidth() {
        return this.W.getStrokeWidth();
    }

    @NonNull
    public ColorStateList getThumbTintList() {
        return this.W.getFillColor();
    }

    @NonNull
    public ColorStateList getTickActiveTintList() {
        return this.S;
    }

    @NonNull
    public ColorStateList getTickInactiveTintList() {
        return this.T;
    }

    @NonNull
    public ColorStateList getTickTintList() {
        if (this.T.equals(this.S)) {
            return this.S;
        }
        throw new IllegalStateException("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
    }

    @NonNull
    public ColorStateList getTrackActiveTintList() {
        return this.U;
    }

    @Dimension
    public int getTrackHeight() {
        return this.v;
    }

    @NonNull
    public ColorStateList getTrackInactiveTintList() {
        return this.V;
    }

    @Dimension
    public int getTrackSidePadding() {
        return this.w;
    }

    @NonNull
    public ColorStateList getTrackTintList() {
        if (this.V.equals(this.U)) {
            return this.U;
        }
        throw new IllegalStateException("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
    }

    @Dimension
    public int getTrackWidth() {
        return this.N;
    }

    public float getValueFrom() {
        return this.F;
    }

    public float getValueTo() {
        return this.G;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public List<Float> getValues() {
        return new ArrayList(this.H);
    }

    public boolean hasLabelFormatter() {
        return this.D != null;
    }

    public boolean isTickVisible() {
        return this.M;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Iterator<TooltipDrawable> it = this.k.iterator();
        while (it.hasNext()) {
            h(it.next());
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        BaseSlider<S, L, T>.d dVar = this.f1687i;
        if (dVar != null) {
            removeCallbacks(dVar);
        }
        this.n = false;
        Iterator<TooltipDrawable> it = this.k.iterator();
        while (it.hasNext()) {
            o(it.next());
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(@NonNull Canvas canvas) {
        if (this.Q) {
            e0();
            G();
        }
        super.onDraw(canvas);
        int l = l();
        t(canvas, this.N, l);
        if (((Float) Collections.max(getValues())).floatValue() > this.F) {
            s(canvas, this.N, l);
        }
        I(canvas);
        if ((this.E || isFocused()) && isEnabled()) {
            H(canvas, this.N, l);
            if (this.I != -1) {
                v();
            }
        }
        u(canvas, this.N, l);
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i2, @Nullable Rect rect) {
        super.onFocusChanged(z, i2, rect);
        if (z) {
            x(i2);
            this.g.requestKeyboardFocusForVirtualView(this.J);
        } else {
            this.I = -1;
            w();
            this.g.clearKeyboardFocusForVirtualView(this.J);
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, @NonNull KeyEvent keyEvent) {
        if (!isEnabled()) {
            return super.onKeyDown(i2, keyEvent);
        }
        if (this.H.size() == 1) {
            this.I = 0;
        }
        if (this.I == -1) {
            Boolean N = N(i2, keyEvent);
            return N != null ? N.booleanValue() : super.onKeyDown(i2, keyEvent);
        }
        this.P |= keyEvent.isLongPress();
        Float i3 = i(i2);
        if (i3 != null) {
            if (X(this.H.get(this.I).floatValue() + i3.floatValue())) {
                c0();
                postInvalidate();
            }
            return true;
        }
        if (i2 != 23) {
            if (i2 == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return K(1);
                }
                if (keyEvent.isShiftPressed()) {
                    return K(-1);
                }
                return false;
            }
            if (i2 != 66) {
                return super.onKeyDown(i2, keyEvent);
            }
        }
        this.I = -1;
        w();
        postInvalidate();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, @NonNull KeyEvent keyEvent) {
        this.P = false;
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(this.t + (this.u == 1 ? this.k.get(0).getIntrinsicHeight() : 0), BasicMeasure.EXACTLY));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.F = sliderState.a;
        this.G = sliderState.b;
        setValuesInternal(sliderState.f1688c);
        this.K = sliderState.f1689d;
        if (sliderState.e) {
            requestFocus();
        }
        r();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.a = this.F;
        sliderState.b = this.G;
        sliderState.f1688c = new ArrayList<>(this.H);
        sliderState.f1689d = this.K;
        sliderState.e = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        d0(i2);
        c0();
    }

    @Override // android.view.View
    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        float x = motionEvent.getX();
        float f2 = (x - this.w) / this.N;
        this.a0 = f2;
        float max = Math.max(0.0f, f2);
        this.a0 = max;
        this.a0 = Math.min(1.0f, max);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                this.E = false;
                MotionEvent motionEvent2 = this.C;
                if (motionEvent2 != null && motionEvent2.getActionMasked() == 0 && Math.abs(this.C.getX() - motionEvent.getX()) <= this.q && Math.abs(this.C.getY() - motionEvent.getY()) <= this.q && R()) {
                    O();
                }
                if (this.I != -1) {
                    a0();
                    this.I = -1;
                    P();
                }
                w();
            } else if (actionMasked == 2) {
                if (!this.E) {
                    if (D() && Math.abs(x - this.B) < this.q) {
                        return false;
                    }
                    getParent().requestDisallowInterceptTouchEvent(true);
                    O();
                }
                if (R()) {
                    this.E = true;
                    a0();
                    c0();
                }
            }
            invalidate();
        } else {
            this.B = x;
            if (!D()) {
                getParent().requestDisallowInterceptTouchEvent(true);
                if (R()) {
                    requestFocus();
                    this.E = true;
                    a0();
                    c0();
                    invalidate();
                    O();
                }
            }
        }
        setPressed(this.E);
        this.C = MotionEvent.obtain(motionEvent);
        return true;
    }

    public void removeOnChangeListener(@NonNull L l) {
        this.l.remove(l);
    }

    public void removeOnSliderTouchListener(@NonNull T t) {
        this.m.remove(t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setActiveThumbIndex(int i2) {
        this.I = i2;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setLayerType(z ? 0 : 2, null);
    }

    public void setFocusedThumbIndex(int i2) {
        if (i2 < 0 || i2 >= this.H.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.J = i2;
        this.g.requestKeyboardFocusForVirtualView(i2);
        postInvalidate();
    }

    public void setHaloRadius(@IntRange(from = 0) @Dimension int i2) {
        if (i2 == this.z) {
            return;
        }
        this.z = i2;
        Drawable background = getBackground();
        if (W() || !(background instanceof RippleDrawable)) {
            postInvalidate();
        } else {
            DrawableUtils.setRippleDrawableRadius((RippleDrawable) background, this.z);
        }
    }

    public void setHaloRadiusResource(@DimenRes int i2) {
        setHaloRadius(getResources().getDimensionPixelSize(i2));
    }

    public void setHaloTintList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.equals(this.R)) {
            return;
        }
        this.R = colorStateList;
        Drawable background = getBackground();
        if (!W() && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setColor(colorStateList);
            return;
        }
        this.f1686d.setColor(B(colorStateList));
        this.f1686d.setAlpha(63);
        invalidate();
    }

    public void setLabelBehavior(int i2) {
        if (this.u != i2) {
            this.u = i2;
            requestLayout();
        }
    }

    public void setLabelFormatter(@Nullable LabelFormatter labelFormatter) {
        this.D = labelFormatter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setSeparationUnit(int i2) {
        this.b0 = i2;
    }

    public void setStepSize(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException(String.format("The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range", Float.toString(f2), Float.toString(this.F), Float.toString(this.G)));
        }
        if (this.K != f2) {
            this.K = f2;
            this.Q = true;
            postInvalidate();
        }
    }

    public void setThumbElevation(float f2) {
        this.W.setElevation(f2);
    }

    public void setThumbElevationResource(@DimenRes int i2) {
        setThumbElevation(getResources().getDimension(i2));
    }

    public void setThumbRadius(@IntRange(from = 0) @Dimension int i2) {
        if (i2 == this.y) {
            return;
        }
        this.y = i2;
        J();
        this.W.setShapeAppearanceModel(ShapeAppearanceModel.builder().setAllCorners(0, this.y).build());
        MaterialShapeDrawable materialShapeDrawable = this.W;
        int i3 = this.y;
        materialShapeDrawable.setBounds(0, 0, i3 * 2, i3 * 2);
        postInvalidate();
    }

    public void setThumbRadiusResource(@DimenRes int i2) {
        setThumbRadius(getResources().getDimensionPixelSize(i2));
    }

    public void setThumbStrokeColor(@Nullable ColorStateList colorStateList) {
        this.W.setStrokeColor(colorStateList);
        postInvalidate();
    }

    public void setThumbStrokeColorResource(@ColorRes int i2) {
        if (i2 != 0) {
            setThumbStrokeColor(AppCompatResources.getColorStateList(getContext(), i2));
        }
    }

    public void setThumbStrokeWidth(float f2) {
        this.W.setStrokeWidth(f2);
        postInvalidate();
    }

    public void setThumbStrokeWidthResource(@DimenRes int i2) {
        if (i2 != 0) {
            setThumbStrokeWidth(getResources().getDimension(i2));
        }
    }

    public void setThumbTintList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.equals(this.W.getFillColor())) {
            return;
        }
        this.W.setFillColor(colorStateList);
        invalidate();
    }

    public void setTickActiveTintList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.equals(this.S)) {
            return;
        }
        this.S = colorStateList;
        this.f.setColor(B(colorStateList));
        invalidate();
    }

    public void setTickInactiveTintList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.equals(this.T)) {
            return;
        }
        this.T = colorStateList;
        this.e.setColor(B(colorStateList));
        invalidate();
    }

    public void setTickTintList(@NonNull ColorStateList colorStateList) {
        setTickInactiveTintList(colorStateList);
        setTickActiveTintList(colorStateList);
    }

    public void setTickVisible(boolean z) {
        if (this.M != z) {
            this.M = z;
            postInvalidate();
        }
    }

    public void setTrackActiveTintList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.equals(this.U)) {
            return;
        }
        this.U = colorStateList;
        this.b.setColor(B(colorStateList));
        invalidate();
    }

    public void setTrackHeight(@IntRange(from = 0) @Dimension int i2) {
        if (this.v != i2) {
            this.v = i2;
            C();
            postInvalidate();
        }
    }

    public void setTrackInactiveTintList(@NonNull ColorStateList colorStateList) {
        if (colorStateList.equals(this.V)) {
            return;
        }
        this.V = colorStateList;
        this.a.setColor(B(colorStateList));
        invalidate();
    }

    public void setTrackTintList(@NonNull ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public void setValueFrom(float f2) {
        this.F = f2;
        this.Q = true;
        postInvalidate();
    }

    public void setValueTo(float f2) {
        this.G = f2;
        this.Q = true;
        postInvalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValues(@NonNull List<Float> list) {
        setValuesInternal(new ArrayList<>(list));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValues(@NonNull Float... fArr) {
        ArrayList<Float> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, fArr);
        setValuesInternal(arrayList);
    }
}
