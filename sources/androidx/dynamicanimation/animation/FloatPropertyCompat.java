package androidx.dynamicanimation.animation;

import android.util.FloatProperty;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public abstract class FloatPropertyCompat<T> {

    /* loaded from: classes.dex */
    static class a extends FloatPropertyCompat<T> {
        final /* synthetic */ FloatProperty a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, FloatProperty floatProperty) {
            super(str);
            this.a = floatProperty;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(T t) {
            return ((Float) this.a.get(t)).floatValue();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(T t, float f) {
            this.a.setValue(t, f);
        }
    }

    public FloatPropertyCompat(String str) {
    }

    @RequiresApi(24)
    public static <T> FloatPropertyCompat<T> createFloatPropertyCompat(FloatProperty<T> floatProperty) {
        return new a(floatProperty.getName(), floatProperty);
    }

    public abstract float getValue(T t);

    public abstract void setValue(T t, float f);
}
