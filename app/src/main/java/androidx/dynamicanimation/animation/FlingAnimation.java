package androidx.dynamicanimation.animation;

import androidx.annotation.FloatRange;
import androidx.dynamicanimation.animation.DynamicAnimation;

/* loaded from: classes.dex */
public final class FlingAnimation extends DynamicAnimation<FlingAnimation> {
    private final a m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class a {
        private float b;
        private float a = -4.2f;

        /* renamed from: c, reason: collision with root package name */
        private final DynamicAnimation.p f693c = new DynamicAnimation.p();

        a() {
        }

        float a() {
            return this.a / (-4.2f);
        }

        public boolean b(float f, float f2) {
            return Math.abs(f2) < this.b;
        }

        void c(float f) {
            this.a = f * (-4.2f);
        }

        void d(float f) {
            this.b = f * 62.5f;
        }

        DynamicAnimation.p e(float f, float f2, long j) {
            DynamicAnimation.p pVar = this.f693c;
            double d2 = f2;
            float f3 = (float) j;
            double exp = Math.exp((f3 / 1000.0f) * this.a);
            Double.isNaN(d2);
            pVar.b = (float) (d2 * exp);
            DynamicAnimation.p pVar2 = this.f693c;
            float f4 = this.a;
            double d3 = f - (f2 / f4);
            double d4 = f2 / f4;
            double exp2 = Math.exp((f4 * f3) / 1000.0f);
            Double.isNaN(d4);
            Double.isNaN(d3);
            pVar2.a = (float) (d3 + (d4 * exp2));
            DynamicAnimation.p pVar3 = this.f693c;
            if (b(pVar3.a, pVar3.b)) {
                this.f693c.b = 0.0f;
            }
            return this.f693c;
        }
    }

    public FlingAnimation(FloatValueHolder floatValueHolder) {
        super(floatValueHolder);
        a aVar = new a();
        this.m = aVar;
        aVar.d(c());
    }

    public <K> FlingAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat) {
        super(k, floatPropertyCompat);
        a aVar = new a();
        this.m = aVar;
        aVar.d(c());
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    void g(float f) {
        this.m.d(f);
    }

    public float getFriction() {
        return this.m.a();
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    boolean i(long j) {
        DynamicAnimation.p e = this.m.e(this.b, this.a, j);
        float f = e.a;
        this.b = f;
        float f2 = e.b;
        this.a = f2;
        float f3 = this.h;
        if (f < f3) {
            this.b = f3;
            return true;
        }
        float f4 = this.g;
        if (f <= f4) {
            return j(f, f2);
        }
        this.b = f4;
        return true;
    }

    boolean j(float f, float f2) {
        return f >= this.g || f <= this.h || this.m.b(f, f2);
    }

    public FlingAnimation setFriction(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Friction must be positive");
        }
        this.m.c(f);
        return this;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public FlingAnimation setMaxValue(float f) {
        super.setMaxValue(f);
        return this;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public FlingAnimation setMinValue(float f) {
        super.setMinValue(f);
        return this;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public FlingAnimation setStartVelocity(float f) {
        super.setStartVelocity(f);
        return this;
    }
}
