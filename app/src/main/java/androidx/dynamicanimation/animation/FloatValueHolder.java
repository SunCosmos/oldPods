package androidx.dynamicanimation.animation;

/* loaded from: classes.dex */
public final class FloatValueHolder {
    private float a = 0.0f;

    public FloatValueHolder() {
    }

    public FloatValueHolder(float f) {
        setValue(f);
    }

    public float getValue() {
        return this.a;
    }

    public void setValue(float f) {
        this.a = f;
    }
}
