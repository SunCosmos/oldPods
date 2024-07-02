package androidx.dynamicanimation.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import androidx.dynamicanimation.animation.DynamicAnimation;

/* loaded from: classes.dex */
public final class SpringAnimation extends DynamicAnimation<SpringAnimation> {
    private SpringForce m;
    private float n;
    private boolean o;

    public SpringAnimation(FloatValueHolder floatValueHolder) {
        super(floatValueHolder);
        this.m = null;
        this.n = Float.MAX_VALUE;
        this.o = false;
    }

    public <K> SpringAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat) {
        super(k, floatPropertyCompat);
        this.m = null;
        this.n = Float.MAX_VALUE;
        this.o = false;
    }

    public <K> SpringAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat, float f) {
        super(k, floatPropertyCompat);
        this.m = null;
        this.n = Float.MAX_VALUE;
        this.o = false;
        this.m = new SpringForce(f);
    }

    private void k() {
        SpringForce springForce = this.m;
        if (springForce == null) {
            throw new UnsupportedOperationException("Incomplete SpringAnimation: Either final position or a spring force needs to be set.");
        }
        double finalPosition = springForce.getFinalPosition();
        if (finalPosition > this.g) {
            throw new UnsupportedOperationException("Final position of the spring cannot be greater than the max value.");
        }
        if (finalPosition < this.h) {
            throw new UnsupportedOperationException("Final position of the spring cannot be less than the min value.");
        }
    }

    public void animateToFinalPosition(float f) {
        if (isRunning()) {
            this.n = f;
            return;
        }
        if (this.m == null) {
            this.m = new SpringForce(f);
        }
        this.m.setFinalPosition(f);
        start();
    }

    public boolean canSkipToEnd() {
        return this.m.b > 0.0d;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    void g(float f) {
    }

    public SpringForce getSpring() {
        return this.m;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    boolean i(long j) {
        SpringForce springForce;
        double d2;
        double d3;
        long j2;
        if (this.o) {
            float f = this.n;
            if (f != Float.MAX_VALUE) {
                this.m.setFinalPosition(f);
                this.n = Float.MAX_VALUE;
            }
            this.b = this.m.getFinalPosition();
            this.a = 0.0f;
            this.o = false;
            return true;
        }
        if (this.n != Float.MAX_VALUE) {
            this.m.getFinalPosition();
            j2 = j / 2;
            DynamicAnimation.p c2 = this.m.c(this.b, this.a, j2);
            this.m.setFinalPosition(this.n);
            this.n = Float.MAX_VALUE;
            springForce = this.m;
            d2 = c2.a;
            d3 = c2.b;
        } else {
            springForce = this.m;
            d2 = this.b;
            d3 = this.a;
            j2 = j;
        }
        DynamicAnimation.p c3 = springForce.c(d2, d3, j2);
        this.b = c3.a;
        this.a = c3.b;
        float max = Math.max(this.b, this.h);
        this.b = max;
        float min = Math.min(max, this.g);
        this.b = min;
        if (!j(min, this.a)) {
            return false;
        }
        this.b = this.m.getFinalPosition();
        this.a = 0.0f;
        return true;
    }

    boolean j(float f, float f2) {
        return this.m.isAtEquilibrium(f, f2);
    }

    public SpringAnimation setSpring(SpringForce springForce) {
        this.m = springForce;
        return this;
    }

    public void skipToEnd() {
        if (!canSkipToEnd()) {
            throw new UnsupportedOperationException("Spring animations can only come to an end when there is damping");
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        if (this.f) {
            this.o = true;
        }
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public void start() {
        k();
        this.m.b(c());
        super.start();
    }
}
