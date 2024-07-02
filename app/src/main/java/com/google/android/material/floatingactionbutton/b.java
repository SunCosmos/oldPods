package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Property;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionSpec;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class b implements f {
    private final Context a;

    @NonNull
    private final ExtendedFloatingActionButton b;

    /* renamed from: c */
    private final ArrayList<Animator.AnimatorListener> f1573c = new ArrayList<>();

    /* renamed from: d */
    private final com.google.android.material.floatingactionbutton.a f1574d;

    @Nullable
    private MotionSpec e;

    @Nullable
    private MotionSpec f;

    /* loaded from: classes.dex */
    public class a extends Property<ExtendedFloatingActionButton, Float> {
        a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a */
        public Float get(ExtendedFloatingActionButton extendedFloatingActionButton) {
            return Float.valueOf(AnimationUtils.lerp(0.0f, 1.0f, (Color.alpha(extendedFloatingActionButton.getCurrentTextColor()) / 255.0f) / Color.alpha(extendedFloatingActionButton.D.getColorForState(extendedFloatingActionButton.getDrawableState(), b.this.b.D.getDefaultColor()))));
        }

        @Override // android.util.Property
        /* renamed from: b */
        public void set(ExtendedFloatingActionButton extendedFloatingActionButton, Float f) {
            int colorForState = extendedFloatingActionButton.D.getColorForState(extendedFloatingActionButton.getDrawableState(), b.this.b.D.getDefaultColor());
            ColorStateList valueOf = ColorStateList.valueOf(Color.argb((int) (AnimationUtils.lerp(0.0f, Color.alpha(colorForState) / 255.0f, f.floatValue()) * 255.0f), Color.red(colorForState), Color.green(colorForState), Color.blue(colorForState)));
            if (f.floatValue() == 1.0f) {
                extendedFloatingActionButton.A(extendedFloatingActionButton.D);
            } else {
                extendedFloatingActionButton.A(valueOf);
            }
        }
    }

    public b(@NonNull ExtendedFloatingActionButton extendedFloatingActionButton, com.google.android.material.floatingactionbutton.a aVar) {
        this.b = extendedFloatingActionButton;
        this.a = extendedFloatingActionButton.getContext();
        this.f1574d = aVar;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final void a(@NonNull Animator.AnimatorListener animatorListener) {
        this.f1573c.add(animatorListener);
    }

    @Override // com.google.android.material.floatingactionbutton.f
    @Nullable
    public MotionSpec b() {
        return this.f;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public AnimatorSet c() {
        return n(o());
    }

    @Override // com.google.android.material.floatingactionbutton.f
    @CallSuper
    public void d() {
        this.f1574d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.f
    @CallSuper
    public void e() {
        this.f1574d.b();
    }

    @Override // com.google.android.material.floatingactionbutton.f
    @NonNull
    public final List<Animator.AnimatorListener> f() {
        return this.f1573c;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final void i(@Nullable MotionSpec motionSpec) {
        this.f = motionSpec;
    }

    @Override // com.google.android.material.floatingactionbutton.f
    public final void k(@NonNull Animator.AnimatorListener animatorListener) {
        this.f1573c.remove(animatorListener);
    }

    @NonNull
    public AnimatorSet n(@NonNull MotionSpec motionSpec) {
        ArrayList arrayList = new ArrayList();
        if (motionSpec.hasPropertyValues("opacity")) {
            arrayList.add(motionSpec.getAnimator("opacity", this.b, View.ALPHA));
        }
        if (motionSpec.hasPropertyValues("scale")) {
            arrayList.add(motionSpec.getAnimator("scale", this.b, View.SCALE_Y));
            arrayList.add(motionSpec.getAnimator("scale", this.b, View.SCALE_X));
        }
        if (motionSpec.hasPropertyValues("width")) {
            arrayList.add(motionSpec.getAnimator("width", this.b, ExtendedFloatingActionButton.F));
        }
        if (motionSpec.hasPropertyValues("height")) {
            arrayList.add(motionSpec.getAnimator("height", this.b, ExtendedFloatingActionButton.G));
        }
        if (motionSpec.hasPropertyValues("paddingStart")) {
            arrayList.add(motionSpec.getAnimator("paddingStart", this.b, ExtendedFloatingActionButton.H));
        }
        if (motionSpec.hasPropertyValues("paddingEnd")) {
            arrayList.add(motionSpec.getAnimator("paddingEnd", this.b, ExtendedFloatingActionButton.I));
        }
        if (motionSpec.hasPropertyValues("labelOpacity")) {
            arrayList.add(motionSpec.getAnimator("labelOpacity", this.b, new a(Float.class, "LABEL_OPACITY_PROPERTY")));
        }
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        return animatorSet;
    }

    public final MotionSpec o() {
        MotionSpec motionSpec = this.f;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.e == null) {
            this.e = MotionSpec.createFromResource(this.a, j());
        }
        return (MotionSpec) Preconditions.checkNotNull(this.e);
    }

    @Override // com.google.android.material.floatingactionbutton.f
    @CallSuper
    public void onAnimationStart(Animator animator) {
        this.f1574d.c(animator);
    }
}
