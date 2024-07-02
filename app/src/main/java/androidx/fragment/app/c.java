package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import androidx.annotation.AnimRes;
import androidx.annotation.NonNull;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.R;
import androidx.fragment.app.j;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements CancellationSignal.OnCancelListener {
        final /* synthetic */ Fragment a;

        a(Fragment fragment) {
            this.a = fragment;
        }

        @Override // androidx.core.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            if (this.a.f() != null) {
                View f = this.a.f();
                this.a.e0(null);
                f.clearAnimation();
            }
            this.a.g0(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Animation.AnimationListener {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ Fragment b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ j.g f762c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ CancellationSignal f763d;

        /* loaded from: classes.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (b.this.b.f() != null) {
                    b.this.b.e0(null);
                    b bVar = b.this;
                    bVar.f762c.a(bVar.b, bVar.f763d);
                }
            }
        }

        b(ViewGroup viewGroup, Fragment fragment, j.g gVar, CancellationSignal cancellationSignal) {
            this.a = viewGroup;
            this.b = fragment;
            this.f762c = gVar;
            this.f763d = cancellationSignal;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.fragment.app.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0030c extends AnimatorListenerAdapter {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f764c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ j.g f765d;
        final /* synthetic */ CancellationSignal e;

        C0030c(ViewGroup viewGroup, View view, Fragment fragment, j.g gVar, CancellationSignal cancellationSignal) {
            this.a = viewGroup;
            this.b = view;
            this.f764c = fragment;
            this.f765d = gVar;
            this.e = cancellationSignal;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.endViewTransition(this.b);
            Animator g = this.f764c.g();
            this.f764c.g0(null);
            if (g == null || this.a.indexOfChild(this.b) >= 0) {
                return;
            }
            this.f765d.a(this.f764c, this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d {
        public final Animation a;
        public final Animator b;

        d(Animator animator) {
            this.a = null;
            this.b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }

        d(Animation animation) {
            this.a = animation;
            this.b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class e extends AnimationSet implements Runnable {
        private final ViewGroup a;
        private final View b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f766c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f767d;
        private boolean e;

        /* JADX INFO: Access modifiers changed from: package-private */
        public e(@NonNull Animation animation, @NonNull ViewGroup viewGroup, @NonNull View view) {
            super(false);
            this.e = true;
            this.a = viewGroup;
            this.b = view;
            addAnimation(animation);
            viewGroup.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, @NonNull Transformation transformation) {
            this.e = true;
            if (this.f766c) {
                return !this.f767d;
            }
            if (!super.getTransformation(j, transformation)) {
                this.f766c = true;
                OneShotPreDrawListener.add(this.a, this);
            }
            return true;
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, @NonNull Transformation transformation, float f) {
            this.e = true;
            if (this.f766c) {
                return !this.f767d;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.f766c = true;
                OneShotPreDrawListener.add(this.a, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f766c || !this.e) {
                this.a.endViewTransition(this.b);
                this.f767d = true;
            } else {
                this.e = false;
                this.a.post(this);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull Fragment fragment, @NonNull d dVar, @NonNull j.g gVar) {
        View view = fragment.H;
        ViewGroup viewGroup = fragment.G;
        viewGroup.startViewTransition(view);
        CancellationSignal cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(new a(fragment));
        gVar.b(fragment, cancellationSignal);
        if (dVar.a != null) {
            e eVar = new e(dVar.a, viewGroup, view);
            fragment.e0(fragment.H);
            eVar.setAnimationListener(new b(viewGroup, fragment, gVar, cancellationSignal));
            fragment.H.startAnimation(eVar);
            return;
        }
        Animator animator = dVar.b;
        fragment.g0(animator);
        animator.addListener(new C0030c(viewGroup, view, fragment, gVar, cancellationSignal));
        animator.setTarget(fragment.H);
        animator.start();
    }

    private static int b(Fragment fragment, boolean z, boolean z2) {
        return z2 ? z ? fragment.p() : fragment.q() : z ? fragment.h() : fragment.j();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d c(@NonNull Context context, @NonNull Fragment fragment, boolean z, boolean z2) {
        int n = fragment.n();
        int b2 = b(fragment, z, z2);
        boolean z3 = false;
        fragment.f0(0, 0, 0, 0);
        ViewGroup viewGroup = fragment.G;
        if (viewGroup != null) {
            int i2 = R.id.visible_removing_fragment_view_tag;
            if (viewGroup.getTag(i2) != null) {
                fragment.G.setTag(i2, null);
            }
        }
        ViewGroup viewGroup2 = fragment.G;
        if (viewGroup2 != null && viewGroup2.getLayoutTransition() != null) {
            return null;
        }
        Animation onCreateAnimation = fragment.onCreateAnimation(n, z, b2);
        if (onCreateAnimation != null) {
            return new d(onCreateAnimation);
        }
        Animator onCreateAnimator = fragment.onCreateAnimator(n, z, b2);
        if (onCreateAnimator != null) {
            return new d(onCreateAnimator);
        }
        if (b2 == 0 && n != 0) {
            b2 = d(n, z);
        }
        if (b2 != 0) {
            boolean equals = "anim".equals(context.getResources().getResourceTypeName(b2));
            if (equals) {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(context, b2);
                    if (loadAnimation != null) {
                        return new d(loadAnimation);
                    }
                    z3 = true;
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                }
            }
            if (!z3) {
                try {
                    Animator loadAnimator = AnimatorInflater.loadAnimator(context, b2);
                    if (loadAnimator != null) {
                        return new d(loadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (equals) {
                        throw e3;
                    }
                    Animation loadAnimation2 = AnimationUtils.loadAnimation(context, b2);
                    if (loadAnimation2 != null) {
                        return new d(loadAnimation2);
                    }
                }
            }
        }
        return null;
    }

    @AnimRes
    private static int d(int i2, boolean z) {
        if (i2 == 4097) {
            return z ? R.animator.fragment_open_enter : R.animator.fragment_open_exit;
        }
        if (i2 == 4099) {
            return z ? R.animator.fragment_fade_enter : R.animator.fragment_fade_exit;
        }
        if (i2 != 8194) {
            return -1;
        }
        return z ? R.animator.fragment_close_enter : R.animator.fragment_close_exit;
    }
}
