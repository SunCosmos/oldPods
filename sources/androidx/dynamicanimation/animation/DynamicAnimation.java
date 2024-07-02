package androidx.dynamicanimation.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import android.view.View;
import androidx.annotation.FloatRange;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.a;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class DynamicAnimation<T extends DynamicAnimation<T>> implements a.b {
    public static final float MIN_VISIBLE_CHANGE_ALPHA = 0.00390625f;
    public static final float MIN_VISIBLE_CHANGE_PIXELS = 1.0f;
    public static final float MIN_VISIBLE_CHANGE_ROTATION_DEGREES = 0.1f;
    public static final float MIN_VISIBLE_CHANGE_SCALE = 0.002f;
    float a;
    float b;

    /* renamed from: c, reason: collision with root package name */
    boolean f690c;

    /* renamed from: d, reason: collision with root package name */
    final Object f691d;
    final FloatPropertyCompat e;
    boolean f;
    float g;
    float h;

    /* renamed from: i, reason: collision with root package name */
    private long f692i;
    private float j;
    private final ArrayList<OnAnimationEndListener> k;
    private final ArrayList<OnAnimationUpdateListener> l;
    public static final ViewProperty TRANSLATION_X = new g("translationX");
    public static final ViewProperty TRANSLATION_Y = new h("translationY");
    public static final ViewProperty TRANSLATION_Z = new i("translationZ");
    public static final ViewProperty SCALE_X = new j("scaleX");
    public static final ViewProperty SCALE_Y = new k("scaleY");
    public static final ViewProperty ROTATION = new l("rotation");
    public static final ViewProperty ROTATION_X = new m("rotationX");
    public static final ViewProperty ROTATION_Y = new n("rotationY");
    public static final ViewProperty X = new o("x");
    public static final ViewProperty Y = new a("y");
    public static final ViewProperty Z = new b("z");
    public static final ViewProperty ALPHA = new c("alpha");
    public static final ViewProperty SCROLL_X = new d("scrollX");
    public static final ViewProperty SCROLL_Y = new e("scrollY");

    /* loaded from: classes.dex */
    public interface OnAnimationEndListener {
        void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2);
    }

    /* loaded from: classes.dex */
    public interface OnAnimationUpdateListener {
        void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f2);
    }

    /* loaded from: classes.dex */
    public static abstract class ViewProperty extends FloatPropertyCompat<View> {
        private ViewProperty(String str) {
            super(str);
        }

        /* synthetic */ ViewProperty(String str, g gVar) {
            this(str);
        }
    }

    /* loaded from: classes.dex */
    static class a extends ViewProperty {
        a(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return view.getY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            view.setY(f);
        }
    }

    /* loaded from: classes.dex */
    static class b extends ViewProperty {
        b(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return ViewCompat.getZ(view);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            ViewCompat.setZ(view, f);
        }
    }

    /* loaded from: classes.dex */
    static class c extends ViewProperty {
        c(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return view.getAlpha();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            view.setAlpha(f);
        }
    }

    /* loaded from: classes.dex */
    static class d extends ViewProperty {
        d(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return view.getScrollX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            view.setScrollX((int) f);
        }
    }

    /* loaded from: classes.dex */
    static class e extends ViewProperty {
        e(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return view.getScrollY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            view.setScrollY((int) f);
        }
    }

    /* loaded from: classes.dex */
    class f extends FloatPropertyCompat {
        final /* synthetic */ FloatValueHolder a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(DynamicAnimation dynamicAnimation, String str, FloatValueHolder floatValueHolder) {
            super(str);
            this.a = floatValueHolder;
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(Object obj) {
            return this.a.getValue();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(Object obj, float f) {
            this.a.setValue(f);
        }
    }

    /* loaded from: classes.dex */
    static class g extends ViewProperty {
        g(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return view.getTranslationX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            view.setTranslationX(f);
        }
    }

    /* loaded from: classes.dex */
    static class h extends ViewProperty {
        h(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return view.getTranslationY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            view.setTranslationY(f);
        }
    }

    /* loaded from: classes.dex */
    static class i extends ViewProperty {
        i(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return ViewCompat.getTranslationZ(view);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            ViewCompat.setTranslationZ(view, f);
        }
    }

    /* loaded from: classes.dex */
    static class j extends ViewProperty {
        j(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return view.getScaleX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            view.setScaleX(f);
        }
    }

    /* loaded from: classes.dex */
    static class k extends ViewProperty {
        k(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return view.getScaleY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            view.setScaleY(f);
        }
    }

    /* loaded from: classes.dex */
    static class l extends ViewProperty {
        l(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return view.getRotation();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            view.setRotation(f);
        }
    }

    /* loaded from: classes.dex */
    static class m extends ViewProperty {
        m(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return view.getRotationX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            view.setRotationX(f);
        }
    }

    /* loaded from: classes.dex */
    static class n extends ViewProperty {
        n(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return view.getRotationY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            view.setRotationY(f);
        }
    }

    /* loaded from: classes.dex */
    static class o extends ViewProperty {
        o(String str) {
            super(str, null);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float getValue(View view) {
            return view.getX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void setValue(View view, float f) {
            view.setX(f);
        }
    }

    /* loaded from: classes.dex */
    static class p {
        float a;
        float b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DynamicAnimation(FloatValueHolder floatValueHolder) {
        this.a = 0.0f;
        this.b = Float.MAX_VALUE;
        this.f690c = false;
        this.f = false;
        this.g = Float.MAX_VALUE;
        this.h = -Float.MAX_VALUE;
        this.f692i = 0L;
        this.k = new ArrayList<>();
        this.l = new ArrayList<>();
        this.f691d = null;
        this.e = new f(this, "FloatValueHolder", floatValueHolder);
        this.j = 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <K> DynamicAnimation(K k2, FloatPropertyCompat<K> floatPropertyCompat) {
        float f2;
        this.a = 0.0f;
        this.b = Float.MAX_VALUE;
        this.f690c = false;
        this.f = false;
        this.g = Float.MAX_VALUE;
        this.h = -Float.MAX_VALUE;
        this.f692i = 0L;
        this.k = new ArrayList<>();
        this.l = new ArrayList<>();
        this.f691d = k2;
        this.e = floatPropertyCompat;
        if (floatPropertyCompat == ROTATION || floatPropertyCompat == ROTATION_X || floatPropertyCompat == ROTATION_Y) {
            f2 = 0.1f;
        } else {
            if (floatPropertyCompat == ALPHA || floatPropertyCompat == SCALE_X || floatPropertyCompat == SCALE_Y) {
                this.j = 0.00390625f;
                return;
            }
            f2 = 1.0f;
        }
        this.j = f2;
    }

    private void a(boolean z) {
        this.f = false;
        androidx.dynamicanimation.animation.a.d().g(this);
        this.f692i = 0L;
        this.f690c = false;
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            if (this.k.get(i2) != null) {
                this.k.get(i2).onAnimationEnd(this, z, this.b, this.a);
            }
        }
        e(this.k);
    }

    private float b() {
        return this.e.getValue(this.f691d);
    }

    private static <T> void d(ArrayList<T> arrayList, T t) {
        int indexOf = arrayList.indexOf(t);
        if (indexOf >= 0) {
            arrayList.set(indexOf, null);
        }
    }

    private static <T> void e(ArrayList<T> arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == null) {
                arrayList.remove(size);
            }
        }
    }

    private void h() {
        if (this.f) {
            return;
        }
        this.f = true;
        if (!this.f690c) {
            this.b = b();
        }
        float f2 = this.b;
        if (f2 > this.g || f2 < this.h) {
            throw new IllegalArgumentException("Starting value need to be in between min value and max value");
        }
        androidx.dynamicanimation.animation.a.d().a(this, 0L);
    }

    public T addEndListener(OnAnimationEndListener onAnimationEndListener) {
        if (!this.k.contains(onAnimationEndListener)) {
            this.k.add(onAnimationEndListener);
        }
        return this;
    }

    public T addUpdateListener(OnAnimationUpdateListener onAnimationUpdateListener) {
        if (isRunning()) {
            throw new UnsupportedOperationException("Error: Update listeners must be added beforethe animation.");
        }
        if (!this.l.contains(onAnimationUpdateListener)) {
            this.l.add(onAnimationUpdateListener);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float c() {
        return this.j * 0.75f;
    }

    public void cancel() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be canceled on the main thread");
        }
        if (this.f) {
            a(true);
        }
    }

    @Override // androidx.dynamicanimation.animation.a.b
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean doAnimationFrame(long j2) {
        long j3 = this.f692i;
        if (j3 == 0) {
            this.f692i = j2;
            f(this.b);
            return false;
        }
        this.f692i = j2;
        boolean i2 = i(j2 - j3);
        float min = Math.min(this.b, this.g);
        this.b = min;
        float max = Math.max(min, this.h);
        this.b = max;
        f(max);
        if (i2) {
            a(false);
        }
        return i2;
    }

    void f(float f2) {
        this.e.setValue(this.f691d, f2);
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            if (this.l.get(i2) != null) {
                this.l.get(i2).onAnimationUpdate(this, this.b, this.a);
            }
        }
        e(this.l);
    }

    abstract void g(float f2);

    public float getMinimumVisibleChange() {
        return this.j;
    }

    abstract boolean i(long j2);

    public boolean isRunning() {
        return this.f;
    }

    public void removeEndListener(OnAnimationEndListener onAnimationEndListener) {
        d(this.k, onAnimationEndListener);
    }

    public void removeUpdateListener(OnAnimationUpdateListener onAnimationUpdateListener) {
        d(this.l, onAnimationUpdateListener);
    }

    public T setMaxValue(float f2) {
        this.g = f2;
        return this;
    }

    public T setMinValue(float f2) {
        this.h = f2;
        return this;
    }

    public T setMinimumVisibleChange(@FloatRange(from = 0.0d, fromInclusive = false) float f2) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Minimum visible change must be positive.");
        }
        this.j = f2;
        g(f2 * 0.75f);
        return this;
    }

    public T setStartValue(float f2) {
        this.b = f2;
        this.f690c = true;
        return this;
    }

    public T setStartVelocity(float f2) {
        this.a = f2;
        return this;
    }

    public void start() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        if (this.f) {
            return;
        }
        h();
    }
}
