package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.ViewCompat;
import java.util.Map;

/* loaded from: classes.dex */
public class ChangeBounds extends Transition {
    private static final String[] M = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    private static final Property<Drawable, PointF> N = new b(PointF.class, "boundsOrigin");
    private static final Property<k, PointF> O = new c(PointF.class, "topLeft");
    private static final Property<k, PointF> P = new d(PointF.class, "bottomRight");
    private static final Property<View, PointF> Q = new e(PointF.class, "bottomRight");
    private static final Property<View, PointF> R = new f(PointF.class, "topLeft");
    private static final Property<View, PointF> S = new g(PointF.class, "position");
    private static n T = new n();
    private int[] J;
    private boolean K;
    private boolean L;

    /* loaded from: classes.dex */
    class a extends AnimatorListenerAdapter {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ BitmapDrawable b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f1040c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ float f1041d;

        a(ChangeBounds changeBounds, ViewGroup viewGroup, BitmapDrawable bitmapDrawable, View view, float f) {
            this.a = viewGroup;
            this.b = bitmapDrawable;
            this.f1040c = view;
            this.f1041d = f;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b0.b(this.a).remove(this.b);
            b0.h(this.f1040c, this.f1041d);
        }
    }

    /* loaded from: classes.dex */
    static class b extends Property<Drawable, PointF> {
        private Rect a;

        b(Class cls, String str) {
            super(cls, str);
            this.a = new Rect();
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.a);
            Rect rect = this.a;
            return new PointF(rect.left, rect.top);
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.a);
            this.a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.a);
        }
    }

    /* loaded from: classes.dex */
    static class c extends Property<k, PointF> {
        c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(k kVar, PointF pointF) {
            kVar.c(pointF);
        }
    }

    /* loaded from: classes.dex */
    static class d extends Property<k, PointF> {
        d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(k kVar, PointF pointF) {
            kVar.a(pointF);
        }
    }

    /* loaded from: classes.dex */
    static class e extends Property<View, PointF> {
        e(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            b0.g(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
        }
    }

    /* loaded from: classes.dex */
    static class f extends Property<View, PointF> {
        f(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            b0.g(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
        }
    }

    /* loaded from: classes.dex */
    static class g extends Property<View, PointF> {
        g(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            int round = Math.round(pointF.x);
            int round2 = Math.round(pointF.y);
            b0.g(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
        }
    }

    /* loaded from: classes.dex */
    class h extends AnimatorListenerAdapter {
        final /* synthetic */ k a;
        private k mViewBounds;

        h(ChangeBounds changeBounds, k kVar) {
            this.a = kVar;
            this.mViewBounds = kVar;
        }
    }

    /* loaded from: classes.dex */
    class i extends AnimatorListenerAdapter {
        private boolean a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Rect f1042c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f1043d;
        final /* synthetic */ int e;
        final /* synthetic */ int f;
        final /* synthetic */ int g;

        i(ChangeBounds changeBounds, View view, Rect rect, int i2, int i3, int i4, int i5) {
            this.b = view;
            this.f1042c = rect;
            this.f1043d = i2;
            this.e = i3;
            this.f = i4;
            this.g = i5;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.a) {
                return;
            }
            ViewCompat.setClipBounds(this.b, this.f1042c);
            b0.g(this.b, this.f1043d, this.e, this.f, this.g);
        }
    }

    /* loaded from: classes.dex */
    class j extends TransitionListenerAdapter {
        boolean a = false;
        final /* synthetic */ ViewGroup b;

        j(ChangeBounds changeBounds, ViewGroup viewGroup) {
            this.b = viewGroup;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(@NonNull Transition transition) {
            w.d(this.b, false);
            this.a = true;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            if (!this.a) {
                w.d(this.b, false);
            }
            transition.removeListener(this);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionPause(@NonNull Transition transition) {
            w.d(this.b, false);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionResume(@NonNull Transition transition) {
            w.d(this.b, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class k {
        private int a;
        private int b;

        /* renamed from: c, reason: collision with root package name */
        private int f1044c;

        /* renamed from: d, reason: collision with root package name */
        private int f1045d;
        private View e;
        private int f;
        private int g;

        k(View view) {
            this.e = view;
        }

        private void b() {
            b0.g(this.e, this.a, this.b, this.f1044c, this.f1045d);
            this.f = 0;
            this.g = 0;
        }

        void a(PointF pointF) {
            this.f1044c = Math.round(pointF.x);
            this.f1045d = Math.round(pointF.y);
            int i2 = this.g + 1;
            this.g = i2;
            if (this.f == i2) {
                b();
            }
        }

        void c(PointF pointF) {
            this.a = Math.round(pointF.x);
            this.b = Math.round(pointF.y);
            int i2 = this.f + 1;
            this.f = i2;
            if (i2 == this.g) {
                b();
            }
        }
    }

    public ChangeBounds() {
        this.J = new int[2];
        this.K = false;
        this.L = false;
    }

    @SuppressLint({"RestrictedApi"})
    public ChangeBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.J = new int[2];
        this.K = false;
        this.L = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, o.f1085d);
        boolean namedBoolean = TypedArrayUtils.getNamedBoolean(obtainStyledAttributes, (XmlResourceParser) attributeSet, "resizeClip", 0, false);
        obtainStyledAttributes.recycle();
        setResizeClip(namedBoolean);
    }

    private void I(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (!ViewCompat.isLaidOut(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        transitionValues.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        transitionValues.values.put("android:changeBounds:parent", transitionValues.view.getParent());
        if (this.L) {
            transitionValues.view.getLocationInWindow(this.J);
            transitionValues.values.put("android:changeBounds:windowX", Integer.valueOf(this.J[0]));
            transitionValues.values.put("android:changeBounds:windowY", Integer.valueOf(this.J[1]));
        }
        if (this.K) {
            transitionValues.values.put("android:changeBounds:clip", ViewCompat.getClipBounds(view));
        }
    }

    private boolean J(View view, View view2) {
        if (!this.L) {
            return true;
        }
        TransitionValues q = q(view, true);
        if (q == null) {
            if (view == view2) {
                return true;
            }
        } else if (view2 == q.view) {
            return true;
        }
        return false;
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        I(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        I(transitionValues);
    }

    @Override // androidx.transition.Transition
    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        int i2;
        View view;
        int i3;
        Rect rect;
        ObjectAnimator objectAnimator;
        Animator c2;
        Path path;
        Property<View, PointF> property;
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        Map<String, Object> map = transitionValues.values;
        Map<String, Object> map2 = transitionValues2.values;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view2 = transitionValues2.view;
        if (!J(viewGroup2, viewGroup3)) {
            int intValue = ((Integer) transitionValues.values.get("android:changeBounds:windowX")).intValue();
            int intValue2 = ((Integer) transitionValues.values.get("android:changeBounds:windowY")).intValue();
            int intValue3 = ((Integer) transitionValues2.values.get("android:changeBounds:windowX")).intValue();
            int intValue4 = ((Integer) transitionValues2.values.get("android:changeBounds:windowY")).intValue();
            if (intValue == intValue3 && intValue2 == intValue4) {
                return null;
            }
            viewGroup.getLocationInWindow(this.J);
            Bitmap createBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
            view2.draw(new Canvas(createBitmap));
            BitmapDrawable bitmapDrawable = new BitmapDrawable(createBitmap);
            float c3 = b0.c(view2);
            b0.h(view2, 0.0f);
            b0.b(viewGroup).add(bitmapDrawable);
            PathMotion pathMotion = getPathMotion();
            int[] iArr = this.J;
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, m.a(N, pathMotion.getPath(intValue - iArr[0], intValue2 - iArr[1], intValue3 - iArr[0], intValue4 - iArr[1])));
            ofPropertyValuesHolder.addListener(new a(this, viewGroup, bitmapDrawable, view2, c3));
            return ofPropertyValuesHolder;
        }
        Rect rect2 = (Rect) transitionValues.values.get("android:changeBounds:bounds");
        Rect rect3 = (Rect) transitionValues2.values.get("android:changeBounds:bounds");
        int i4 = rect2.left;
        int i5 = rect3.left;
        int i6 = rect2.top;
        int i7 = rect3.top;
        int i8 = rect2.right;
        int i9 = rect3.right;
        int i10 = rect2.bottom;
        int i11 = rect3.bottom;
        int i12 = i8 - i4;
        int i13 = i10 - i6;
        int i14 = i9 - i5;
        int i15 = i11 - i7;
        Rect rect4 = (Rect) transitionValues.values.get("android:changeBounds:clip");
        Rect rect5 = (Rect) transitionValues2.values.get("android:changeBounds:clip");
        if ((i12 == 0 || i13 == 0) && (i14 == 0 || i15 == 0)) {
            i2 = 0;
        } else {
            i2 = (i4 == i5 && i6 == i7) ? 0 : 1;
            if (i8 != i9 || i10 != i11) {
                i2++;
            }
        }
        if ((rect4 != null && !rect4.equals(rect5)) || (rect4 == null && rect5 != null)) {
            i2++;
        }
        if (i2 <= 0) {
            return null;
        }
        if (this.K) {
            view = view2;
            b0.g(view, i4, i6, Math.max(i12, i14) + i4, Math.max(i13, i15) + i6);
            ObjectAnimator a2 = (i4 == i5 && i6 == i7) ? null : androidx.transition.k.a(view, S, getPathMotion().getPath(i4, i6, i5, i7));
            if (rect4 == null) {
                i3 = 0;
                rect = new Rect(0, 0, i12, i13);
            } else {
                i3 = 0;
                rect = rect4;
            }
            Rect rect6 = rect5 == null ? new Rect(i3, i3, i14, i15) : rect5;
            if (rect.equals(rect6)) {
                objectAnimator = null;
            } else {
                ViewCompat.setClipBounds(view, rect);
                n nVar = T;
                Object[] objArr = new Object[2];
                objArr[i3] = rect;
                objArr[1] = rect6;
                ObjectAnimator ofObject = ObjectAnimator.ofObject(view, "clipBounds", nVar, objArr);
                ofObject.addListener(new i(this, view, rect5, i5, i7, i9, i11));
                objectAnimator = ofObject;
            }
            c2 = p.c(a2, objectAnimator);
        } else {
            view = view2;
            b0.g(view, i4, i6, i8, i10);
            if (i2 == 2) {
                if (i12 == i14 && i13 == i15) {
                    path = getPathMotion().getPath(i4, i6, i5, i7);
                    property = S;
                } else {
                    k kVar = new k(view);
                    ObjectAnimator a3 = androidx.transition.k.a(kVar, O, getPathMotion().getPath(i4, i6, i5, i7));
                    ObjectAnimator a4 = androidx.transition.k.a(kVar, P, getPathMotion().getPath(i8, i10, i9, i11));
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(a3, a4);
                    animatorSet.addListener(new h(this, kVar));
                    c2 = animatorSet;
                }
            } else if (i4 == i5 && i6 == i7) {
                path = getPathMotion().getPath(i8, i10, i9, i11);
                property = Q;
            } else {
                path = getPathMotion().getPath(i4, i6, i5, i7);
                property = R;
            }
            c2 = androidx.transition.k.a(view, property, path);
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            w.d(viewGroup4, true);
            addListener(new j(this, viewGroup4));
        }
        return c2;
    }

    public boolean getResizeClip() {
        return this.K;
    }

    @Override // androidx.transition.Transition
    @Nullable
    public String[] getTransitionProperties() {
        return M;
    }

    public void setResizeClip(boolean z) {
        this.K = z;
    }
}
