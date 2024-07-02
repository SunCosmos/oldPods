package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat;
import androidx.appcompat.resources.R;
import androidx.collection.LongSparseArray;
import androidx.collection.LruCache;
import androidx.collection.SimpleArrayMap;
import androidx.collection.SparseArrayCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public final class ResourceManagerInternal {

    /* renamed from: i */
    private static ResourceManagerInternal f181i;
    private WeakHashMap<Context, SparseArrayCompat<ColorStateList>> a;
    private SimpleArrayMap<String, e> b;

    /* renamed from: c */
    private SparseArrayCompat<String> f182c;

    /* renamed from: d */
    private final WeakHashMap<Context, LongSparseArray<WeakReference<Drawable.ConstantState>>> f183d = new WeakHashMap<>(0);
    private TypedValue e;
    private boolean f;
    private ResourceManagerHooks g;
    private static final PorterDuff.Mode h = PorterDuff.Mode.SRC_IN;
    private static final c j = new c(6);

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public interface ResourceManagerHooks {
        @Nullable
        Drawable createDrawableFor(@NonNull ResourceManagerInternal resourceManagerInternal, @NonNull Context context, @DrawableRes int i2);

        @Nullable
        ColorStateList getTintListForDrawableRes(@NonNull Context context, @DrawableRes int i2);

        @Nullable
        PorterDuff.Mode getTintModeForDrawableRes(int i2);

        boolean tintDrawable(@NonNull Context context, @DrawableRes int i2, @NonNull Drawable drawable);

        boolean tintDrawableUsingColorFilter(@NonNull Context context, @DrawableRes int i2, @NonNull Drawable drawable);
    }

    /* loaded from: classes.dex */
    public static class a implements e {
        a() {
        }

        @Override // androidx.appcompat.widget.ResourceManagerInternal.e
        public Drawable a(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
            try {
                return AnimatedStateListDrawableCompat.createFromXmlInner(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e);
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b implements e {
        b() {
        }

        @Override // androidx.appcompat.widget.ResourceManagerInternal.e
        public Drawable a(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
            try {
                return AnimatedVectorDrawableCompat.createFromXmlInner(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e);
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c extends LruCache<Integer, PorterDuffColorFilter> {
        public c(int i2) {
            super(i2);
        }

        private static int e(int i2, PorterDuff.Mode mode) {
            return ((i2 + 31) * 31) + mode.hashCode();
        }

        PorterDuffColorFilter f(int i2, PorterDuff.Mode mode) {
            return get(Integer.valueOf(e(i2, mode)));
        }

        PorterDuffColorFilter g(int i2, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return put(Integer.valueOf(e(i2, mode)), porterDuffColorFilter);
        }
    }

    /* loaded from: classes.dex */
    public static class d implements e {
        d() {
        }

        @Override // androidx.appcompat.widget.ResourceManagerInternal.e
        public Drawable a(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
            String classAttribute = attributeSet.getClassAttribute();
            if (classAttribute != null) {
                try {
                    Drawable drawable = (Drawable) d.class.getClassLoader().loadClass(classAttribute).asSubclass(Drawable.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    if (Build.VERSION.SDK_INT >= 21) {
                        drawable.inflate(context.getResources(), xmlPullParser, attributeSet, theme);
                    } else {
                        drawable.inflate(context.getResources(), xmlPullParser, attributeSet);
                    }
                    return drawable;
                } catch (Exception e) {
                    Log.e("DrawableDelegate", "Exception while inflating <drawable>", e);
                }
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    public interface e {
        Drawable a(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme);
    }

    /* loaded from: classes.dex */
    public static class f implements e {
        f() {
        }

        @Override // androidx.appcompat.widget.ResourceManagerInternal.e
        public Drawable a(@NonNull Context context, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
            try {
                return VectorDrawableCompat.createFromXmlInner(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e);
                return null;
            }
        }
    }

    private void a(@NonNull String str, @NonNull e eVar) {
        if (this.b == null) {
            this.b = new SimpleArrayMap<>();
        }
        this.b.put(str, eVar);
    }

    private synchronized boolean b(@NonNull Context context, long j2, @NonNull Drawable drawable) {
        boolean z;
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            LongSparseArray<WeakReference<Drawable.ConstantState>> longSparseArray = this.f183d.get(context);
            if (longSparseArray == null) {
                longSparseArray = new LongSparseArray<>();
                this.f183d.put(context, longSparseArray);
            }
            longSparseArray.put(j2, new WeakReference<>(constantState));
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    private void c(@NonNull Context context, @DrawableRes int i2, @NonNull ColorStateList colorStateList) {
        if (this.a == null) {
            this.a = new WeakHashMap<>();
        }
        SparseArrayCompat<ColorStateList> sparseArrayCompat = this.a.get(context);
        if (sparseArrayCompat == null) {
            sparseArrayCompat = new SparseArrayCompat<>();
            this.a.put(context, sparseArrayCompat);
        }
        sparseArrayCompat.append(i2, colorStateList);
    }

    private void d(@NonNull Context context) {
        if (this.f) {
            return;
        }
        this.f = true;
        Drawable drawable = getDrawable(context, R.drawable.abc_vector_test);
        if (drawable == null || !n(drawable)) {
            this.f = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    private static long e(TypedValue typedValue) {
        return (typedValue.assetCookie << 32) | typedValue.data;
    }

    private Drawable f(@NonNull Context context, @DrawableRes int i2) {
        if (this.e == null) {
            this.e = new TypedValue();
        }
        TypedValue typedValue = this.e;
        context.getResources().getValue(i2, typedValue, true);
        long e2 = e(typedValue);
        Drawable h2 = h(context, e2);
        if (h2 != null) {
            return h2;
        }
        ResourceManagerHooks resourceManagerHooks = this.g;
        Drawable createDrawableFor = resourceManagerHooks == null ? null : resourceManagerHooks.createDrawableFor(this, context, i2);
        if (createDrawableFor != null) {
            createDrawableFor.setChangingConfigurations(typedValue.changingConfigurations);
            b(context, e2, createDrawableFor);
        }
        return createDrawableFor;
    }

    private static PorterDuffColorFilter g(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return getPorterDuffColorFilter(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized ResourceManagerInternal get() {
        ResourceManagerInternal resourceManagerInternal;
        synchronized (ResourceManagerInternal.class) {
            if (f181i == null) {
                ResourceManagerInternal resourceManagerInternal2 = new ResourceManagerInternal();
                f181i = resourceManagerInternal2;
                m(resourceManagerInternal2);
            }
            resourceManagerInternal = f181i;
        }
        return resourceManagerInternal;
    }

    public static synchronized PorterDuffColorFilter getPorterDuffColorFilter(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter f2;
        synchronized (ResourceManagerInternal.class) {
            c cVar = j;
            f2 = cVar.f(i2, mode);
            if (f2 == null) {
                f2 = new PorterDuffColorFilter(i2, mode);
                cVar.g(i2, mode, f2);
            }
        }
        return f2;
    }

    private synchronized Drawable h(@NonNull Context context, long j2) {
        LongSparseArray<WeakReference<Drawable.ConstantState>> longSparseArray = this.f183d.get(context);
        if (longSparseArray == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReference = longSparseArray.get(j2);
        if (weakReference != null) {
            Drawable.ConstantState constantState = weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            longSparseArray.remove(j2);
        }
        return null;
    }

    private ColorStateList k(@NonNull Context context, @DrawableRes int i2) {
        SparseArrayCompat<ColorStateList> sparseArrayCompat;
        WeakHashMap<Context, SparseArrayCompat<ColorStateList>> weakHashMap = this.a;
        if (weakHashMap == null || (sparseArrayCompat = weakHashMap.get(context)) == null) {
            return null;
        }
        return sparseArrayCompat.get(i2);
    }

    private static void m(@NonNull ResourceManagerInternal resourceManagerInternal) {
        if (Build.VERSION.SDK_INT < 24) {
            resourceManagerInternal.a("vector", new f());
            resourceManagerInternal.a("animated-vector", new b());
            resourceManagerInternal.a("animated-selector", new a());
            resourceManagerInternal.a("drawable", new d());
        }
    }

    private static boolean n(@NonNull Drawable drawable) {
        return (drawable instanceof VectorDrawableCompat) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    private Drawable o(@NonNull Context context, @DrawableRes int i2) {
        int next;
        SimpleArrayMap<String, e> simpleArrayMap = this.b;
        if (simpleArrayMap == null || simpleArrayMap.isEmpty()) {
            return null;
        }
        SparseArrayCompat<String> sparseArrayCompat = this.f182c;
        if (sparseArrayCompat != null) {
            String str = sparseArrayCompat.get(i2);
            if ("appcompat_skip_skip".equals(str) || (str != null && this.b.get(str) == null)) {
                return null;
            }
        } else {
            this.f182c = new SparseArrayCompat<>();
        }
        if (this.e == null) {
            this.e = new TypedValue();
        }
        TypedValue typedValue = this.e;
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        long e2 = e(typedValue);
        Drawable h2 = h(context, e2);
        if (h2 != null) {
            return h2;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i2);
                AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String name = xml.getName();
                this.f182c.append(i2, name);
                e eVar = this.b.get(name);
                if (eVar != null) {
                    h2 = eVar.a(context, xml, asAttributeSet, context.getTheme());
                }
                if (h2 != null) {
                    h2.setChangingConfigurations(typedValue.changingConfigurations);
                    b(context, e2, h2);
                }
            } catch (Exception e3) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e3);
            }
        }
        if (h2 == null) {
            this.f182c.append(i2, "appcompat_skip_skip");
        }
        return h2;
    }

    private Drawable q(@NonNull Context context, @DrawableRes int i2, boolean z, @NonNull Drawable drawable) {
        ColorStateList j2 = j(context, i2);
        if (j2 == null) {
            ResourceManagerHooks resourceManagerHooks = this.g;
            if ((resourceManagerHooks == null || !resourceManagerHooks.tintDrawable(context, i2, drawable)) && !s(context, i2, drawable) && z) {
                return null;
            }
            return drawable;
        }
        if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
            drawable = drawable.mutate();
        }
        Drawable wrap = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrap, j2);
        PorterDuff.Mode l = l(i2);
        if (l == null) {
            return wrap;
        }
        DrawableCompat.setTintMode(wrap, l);
        return wrap;
    }

    public static void r(Drawable drawable, TintInfo tintInfo, int[] iArr) {
        if (DrawableUtils.canSafelyMutateDrawable(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        boolean z = tintInfo.mHasTintList;
        if (z || tintInfo.mHasTintMode) {
            drawable.setColorFilter(g(z ? tintInfo.mTintList : null, tintInfo.mHasTintMode ? tintInfo.mTintMode : h, iArr));
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    public synchronized Drawable getDrawable(@NonNull Context context, @DrawableRes int i2) {
        return i(context, i2, false);
    }

    public synchronized Drawable i(@NonNull Context context, @DrawableRes int i2, boolean z) {
        Drawable o;
        d(context);
        o = o(context, i2);
        if (o == null) {
            o = f(context, i2);
        }
        if (o == null) {
            o = ContextCompat.getDrawable(context, i2);
        }
        if (o != null) {
            o = q(context, i2, z, o);
        }
        if (o != null) {
            DrawableUtils.a(o);
        }
        return o;
    }

    public synchronized ColorStateList j(@NonNull Context context, @DrawableRes int i2) {
        ColorStateList k;
        k = k(context, i2);
        if (k == null) {
            ResourceManagerHooks resourceManagerHooks = this.g;
            k = resourceManagerHooks == null ? null : resourceManagerHooks.getTintListForDrawableRes(context, i2);
            if (k != null) {
                c(context, i2, k);
            }
        }
        return k;
    }

    PorterDuff.Mode l(int i2) {
        ResourceManagerHooks resourceManagerHooks = this.g;
        if (resourceManagerHooks == null) {
            return null;
        }
        return resourceManagerHooks.getTintModeForDrawableRes(i2);
    }

    public synchronized void onConfigurationChanged(@NonNull Context context) {
        LongSparseArray<WeakReference<Drawable.ConstantState>> longSparseArray = this.f183d.get(context);
        if (longSparseArray != null) {
            longSparseArray.clear();
        }
    }

    public synchronized Drawable p(@NonNull Context context, @NonNull VectorEnabledTintResources vectorEnabledTintResources, @DrawableRes int i2) {
        Drawable o = o(context, i2);
        if (o == null) {
            o = vectorEnabledTintResources.a(i2);
        }
        if (o == null) {
            return null;
        }
        return q(context, i2, false, o);
    }

    public boolean s(@NonNull Context context, @DrawableRes int i2, @NonNull Drawable drawable) {
        ResourceManagerHooks resourceManagerHooks = this.g;
        return resourceManagerHooks != null && resourceManagerHooks.tintDrawableUsingColorFilter(context, i2, drawable);
    }

    public synchronized void setHooks(ResourceManagerHooks resourceManagerHooks) {
        this.g = resourceManagerHooks;
    }
}
