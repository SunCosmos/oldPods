package androidx.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class Slide extends Visibility {
    private static final TimeInterpolator N = new DecelerateInterpolator();
    private static final TimeInterpolator O = new AccelerateInterpolator();
    private static final g P = new a();
    private static final g Q = new b();
    private static final g R = new c();
    private static final g S = new d();
    private static final g T = new e();
    private static final g U = new f();
    private g L;
    private int M;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface GravityFlag {
    }

    /* loaded from: classes.dex */
    static class a extends h {
        a() {
            super(null);
        }

        @Override // androidx.transition.Slide.g
        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationX() - viewGroup.getWidth();
        }
    }

    /* loaded from: classes.dex */
    static class b extends h {
        b() {
            super(null);
        }

        @Override // androidx.transition.Slide.g
        public float a(ViewGroup viewGroup, View view) {
            boolean z = ViewCompat.getLayoutDirection(viewGroup) == 1;
            float translationX = view.getTranslationX();
            float width = viewGroup.getWidth();
            return z ? translationX + width : translationX - width;
        }
    }

    /* loaded from: classes.dex */
    static class c extends i {
        c() {
            super(null);
        }

        @Override // androidx.transition.Slide.g
        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationY() - viewGroup.getHeight();
        }
    }

    /* loaded from: classes.dex */
    static class d extends h {
        d() {
            super(null);
        }

        @Override // androidx.transition.Slide.g
        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationX() + viewGroup.getWidth();
        }
    }

    /* loaded from: classes.dex */
    static class e extends h {
        e() {
            super(null);
        }

        @Override // androidx.transition.Slide.g
        public float a(ViewGroup viewGroup, View view) {
            boolean z = ViewCompat.getLayoutDirection(viewGroup) == 1;
            float translationX = view.getTranslationX();
            float width = viewGroup.getWidth();
            return z ? translationX - width : translationX + width;
        }
    }

    /* loaded from: classes.dex */
    static class f extends i {
        f() {
            super(null);
        }

        @Override // androidx.transition.Slide.g
        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationY() + viewGroup.getHeight();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface g {
        float a(ViewGroup viewGroup, View view);

        float b(ViewGroup viewGroup, View view);
    }

    /* loaded from: classes.dex */
    private static abstract class h implements g {
        private h() {
        }

        /* synthetic */ h(a aVar) {
            this();
        }

        @Override // androidx.transition.Slide.g
        public float b(ViewGroup viewGroup, View view) {
            return view.getTranslationY();
        }
    }

    /* loaded from: classes.dex */
    private static abstract class i implements g {
        private i() {
        }

        /* synthetic */ i(a aVar) {
            this();
        }

        @Override // androidx.transition.Slide.g
        public float a(ViewGroup viewGroup, View view) {
            return view.getTranslationX();
        }
    }

    public Slide() {
        this.L = U;
        this.M = 80;
        setSlideEdge(80);
    }

    public Slide(int i2) {
        this.L = U;
        this.M = 80;
        setSlideEdge(i2);
    }

    @SuppressLint({"RestrictedApi"})
    public Slide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.L = U;
        this.M = 80;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, o.h);
        int namedInt = TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlPullParser) attributeSet, "slideEdge", 0, 80);
        obtainStyledAttributes.recycle();
        setSlideEdge(namedInt);
    }

    private void I(TransitionValues transitionValues) {
        int[] iArr = new int[2];
        transitionValues.view.getLocationOnScreen(iArr);
        transitionValues.values.put("android:slide:screenPosition", iArr);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
        I(transitionValues);
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        I(transitionValues);
    }

    public int getSlideEdge() {
        return this.M;
    }

    @Override // androidx.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues2 == null) {
            return null;
        }
        int[] iArr = (int[]) transitionValues2.values.get("android:slide:screenPosition");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        return s.a(view, transitionValues2, iArr[0], iArr[1], this.L.a(viewGroup, view), this.L.b(viewGroup, view), translationX, translationY, N, this);
    }

    @Override // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null) {
            return null;
        }
        int[] iArr = (int[]) transitionValues.values.get("android:slide:screenPosition");
        return s.a(view, transitionValues, iArr[0], iArr[1], view.getTranslationX(), view.getTranslationY(), this.L.a(viewGroup, view), this.L.b(viewGroup, view), O, this);
    }

    public void setSlideEdge(int i2) {
        g gVar;
        if (i2 == 3) {
            gVar = P;
        } else if (i2 == 5) {
            gVar = S;
        } else if (i2 == 48) {
            gVar = R;
        } else if (i2 == 80) {
            gVar = U;
        } else if (i2 == 8388611) {
            gVar = Q;
        } else {
            if (i2 != 8388613) {
                throw new IllegalArgumentException("Invalid slide direction");
            }
            gVar = T;
        }
        this.L = gVar;
        this.M = i2;
        SidePropagation sidePropagation = new SidePropagation();
        sidePropagation.setSide(i2);
        setPropagation(sidePropagation);
    }
}
