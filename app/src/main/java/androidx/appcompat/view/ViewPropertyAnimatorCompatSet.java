package androidx.appcompat.view;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import java.util.ArrayList;
import java.util.Iterator;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class ViewPropertyAnimatorCompatSet {

    /* renamed from: c, reason: collision with root package name */
    private Interpolator f89c;

    /* renamed from: d, reason: collision with root package name */
    ViewPropertyAnimatorListener f90d;
    private boolean e;
    private long b = -1;
    private final ViewPropertyAnimatorListenerAdapter f = new a();
    final ArrayList<ViewPropertyAnimatorCompat> a = new ArrayList<>();

    /* loaded from: classes.dex */
    class a extends ViewPropertyAnimatorListenerAdapter {
        private boolean a = false;
        private int b = 0;

        a() {
        }

        void a() {
            this.b = 0;
            this.a = false;
            ViewPropertyAnimatorCompatSet.this.a();
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view) {
            int i2 = this.b + 1;
            this.b = i2;
            if (i2 == ViewPropertyAnimatorCompatSet.this.a.size()) {
                ViewPropertyAnimatorListener viewPropertyAnimatorListener = ViewPropertyAnimatorCompatSet.this.f90d;
                if (viewPropertyAnimatorListener != null) {
                    viewPropertyAnimatorListener.onAnimationEnd(null);
                }
                a();
            }
        }

        @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart(View view) {
            if (this.a) {
                return;
            }
            this.a = true;
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = ViewPropertyAnimatorCompatSet.this.f90d;
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationStart(null);
            }
        }
    }

    void a() {
        this.e = false;
    }

    public void cancel() {
        if (this.e) {
            Iterator<ViewPropertyAnimatorCompat> it = this.a.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            this.e = false;
        }
    }

    public ViewPropertyAnimatorCompatSet play(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        if (!this.e) {
            this.a.add(viewPropertyAnimatorCompat);
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet playSequentially(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2) {
        this.a.add(viewPropertyAnimatorCompat);
        viewPropertyAnimatorCompat2.setStartDelay(viewPropertyAnimatorCompat.getDuration());
        this.a.add(viewPropertyAnimatorCompat2);
        return this;
    }

    public ViewPropertyAnimatorCompatSet setDuration(long j) {
        if (!this.e) {
            this.b = j;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet setInterpolator(Interpolator interpolator) {
        if (!this.e) {
            this.f89c = interpolator;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet setListener(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (!this.e) {
            this.f90d = viewPropertyAnimatorListener;
        }
        return this;
    }

    public void start() {
        if (this.e) {
            return;
        }
        Iterator<ViewPropertyAnimatorCompat> it = this.a.iterator();
        while (it.hasNext()) {
            ViewPropertyAnimatorCompat next = it.next();
            long j = this.b;
            if (j >= 0) {
                next.setDuration(j);
            }
            Interpolator interpolator = this.f89c;
            if (interpolator != null) {
                next.setInterpolator(interpolator);
            }
            if (this.f90d != null) {
                next.setListener(this.f);
            }
            next.start();
        }
        this.e = true;
    }
}
