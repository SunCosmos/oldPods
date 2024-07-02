package androidx.fragment.app;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class FragmentTransaction {
    public static final int TRANSIT_ENTER_MASK = 4096;
    public static final int TRANSIT_EXIT_MASK = 8192;
    public static final int TRANSIT_FRAGMENT_CLOSE = 8194;
    public static final int TRANSIT_FRAGMENT_FADE = 4099;
    public static final int TRANSIT_FRAGMENT_OPEN = 4097;
    public static final int TRANSIT_NONE = 0;
    public static final int TRANSIT_UNSET = -1;
    private final FragmentFactory a;
    private final ClassLoader b;

    /* renamed from: c */
    ArrayList<a> f743c;

    /* renamed from: d */
    int f744d;
    int e;
    int f;
    int g;
    int h;

    /* renamed from: i */
    boolean f745i;
    boolean j;

    @Nullable
    String k;
    int l;
    CharSequence m;
    int n;
    CharSequence o;
    ArrayList<String> p;
    ArrayList<String> q;
    boolean r;
    ArrayList<Runnable> s;

    /* loaded from: classes.dex */
    public static final class a {
        int a;
        Fragment b;

        /* renamed from: c */
        int f746c;

        /* renamed from: d */
        int f747d;
        int e;
        int f;
        Lifecycle.State g;
        Lifecycle.State h;

        public a() {
        }

        public a(int i2, Fragment fragment) {
            this.a = i2;
            this.b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.g = state;
            this.h = state;
        }

        a(int i2, @NonNull Fragment fragment, Lifecycle.State state) {
            this.a = i2;
            this.b = fragment;
            this.g = fragment.R;
            this.h = state;
        }
    }

    @Deprecated
    public FragmentTransaction() {
        this.f743c = new ArrayList<>();
        this.j = true;
        this.r = false;
        this.a = null;
        this.b = null;
    }

    public FragmentTransaction(@NonNull FragmentFactory fragmentFactory, @Nullable ClassLoader classLoader) {
        this.f743c = new ArrayList<>();
        this.j = true;
        this.r = false;
        this.a = fragmentFactory;
        this.b = classLoader;
    }

    @NonNull
    private Fragment d(@NonNull Class<? extends Fragment> cls, @Nullable Bundle bundle) {
        FragmentFactory fragmentFactory = this.a;
        if (fragmentFactory == null) {
            throw new IllegalStateException("Creating a Fragment requires that this FragmentTransaction was built with FragmentManager.beginTransaction()");
        }
        ClassLoader classLoader = this.b;
        if (classLoader == null) {
            throw new IllegalStateException("The FragmentManager must be attached to itshost to create a Fragment");
        }
        Fragment instantiate = fragmentFactory.instantiate(classLoader, cls.getName());
        if (bundle != null) {
            instantiate.setArguments(bundle);
        }
        return instantiate;
    }

    @NonNull
    public FragmentTransaction add(@IdRes int i2, @NonNull Fragment fragment) {
        e(i2, fragment, null, 1);
        return this;
    }

    @NonNull
    public FragmentTransaction add(@IdRes int i2, @NonNull Fragment fragment, @Nullable String str) {
        e(i2, fragment, str, 1);
        return this;
    }

    @NonNull
    public final FragmentTransaction add(@IdRes int i2, @NonNull Class<? extends Fragment> cls, @Nullable Bundle bundle) {
        return add(i2, d(cls, bundle));
    }

    @NonNull
    public final FragmentTransaction add(@IdRes int i2, @NonNull Class<? extends Fragment> cls, @Nullable Bundle bundle, @Nullable String str) {
        return add(i2, d(cls, bundle), str);
    }

    @NonNull
    public FragmentTransaction add(@NonNull Fragment fragment, @Nullable String str) {
        e(0, fragment, str, 1);
        return this;
    }

    @NonNull
    public final FragmentTransaction add(@NonNull Class<? extends Fragment> cls, @Nullable Bundle bundle, @Nullable String str) {
        return add(d(cls, bundle), str);
    }

    @NonNull
    public FragmentTransaction addSharedElement(@NonNull View view, @NonNull String str) {
        if (j.D()) {
            String transitionName = ViewCompat.getTransitionName(view);
            if (transitionName == null) {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (this.p == null) {
                this.p = new ArrayList<>();
                this.q = new ArrayList<>();
            } else {
                if (this.q.contains(str)) {
                    throw new IllegalArgumentException("A shared element with the target name '" + str + "' has already been added to the transaction.");
                }
                if (this.p.contains(transitionName)) {
                    throw new IllegalArgumentException("A shared element with the source name '" + transitionName + "' has already been added to the transaction.");
                }
            }
            this.p.add(transitionName);
            this.q.add(str);
        }
        return this;
    }

    @NonNull
    public FragmentTransaction addToBackStack(@Nullable String str) {
        if (!this.j) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        this.f745i = true;
        this.k = str;
        return this;
    }

    @NonNull
    public FragmentTransaction attach(@NonNull Fragment fragment) {
        c(new a(7, fragment));
        return this;
    }

    public FragmentTransaction b(@NonNull ViewGroup viewGroup, @NonNull Fragment fragment, @Nullable String str) {
        fragment.G = viewGroup;
        return add(viewGroup.getId(), fragment, str);
    }

    public void c(a aVar) {
        this.f743c.add(aVar);
        aVar.f746c = this.f744d;
        aVar.f747d = this.e;
        aVar.e = this.f;
        aVar.f = this.g;
    }

    public abstract int commit();

    public abstract int commitAllowingStateLoss();

    public abstract void commitNow();

    public abstract void commitNowAllowingStateLoss();

    @NonNull
    public FragmentTransaction detach(@NonNull Fragment fragment) {
        c(new a(6, fragment));
        return this;
    }

    @NonNull
    public FragmentTransaction disallowAddToBackStack() {
        if (this.f745i) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.j = false;
        return this;
    }

    public void e(int i2, Fragment fragment, @Nullable String str, int i3) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str2 = fragment.y;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.y + " now " + str);
            }
            fragment.y = str;
        }
        if (i2 != 0) {
            if (i2 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i4 = fragment.w;
            if (i4 != 0 && i4 != i2) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.w + " now " + i2);
            }
            fragment.w = i2;
            fragment.x = i2;
        }
        c(new a(i3, fragment));
    }

    @NonNull
    public FragmentTransaction hide(@NonNull Fragment fragment) {
        c(new a(4, fragment));
        return this;
    }

    public boolean isAddToBackStackAllowed() {
        return this.j;
    }

    public boolean isEmpty() {
        return this.f743c.isEmpty();
    }

    @NonNull
    public FragmentTransaction remove(@NonNull Fragment fragment) {
        c(new a(3, fragment));
        return this;
    }

    @NonNull
    public FragmentTransaction replace(@IdRes int i2, @NonNull Fragment fragment) {
        return replace(i2, fragment, (String) null);
    }

    @NonNull
    public FragmentTransaction replace(@IdRes int i2, @NonNull Fragment fragment, @Nullable String str) {
        if (i2 == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        e(i2, fragment, str, 2);
        return this;
    }

    @NonNull
    public final FragmentTransaction replace(@IdRes int i2, @NonNull Class<? extends Fragment> cls, @Nullable Bundle bundle) {
        return replace(i2, cls, bundle, null);
    }

    @NonNull
    public final FragmentTransaction replace(@IdRes int i2, @NonNull Class<? extends Fragment> cls, @Nullable Bundle bundle, @Nullable String str) {
        return replace(i2, d(cls, bundle), str);
    }

    @NonNull
    public FragmentTransaction runOnCommit(@NonNull Runnable runnable) {
        disallowAddToBackStack();
        if (this.s == null) {
            this.s = new ArrayList<>();
        }
        this.s.add(runnable);
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setAllowOptimization(boolean z) {
        return setReorderingAllowed(z);
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setBreadCrumbShortTitle(@StringRes int i2) {
        this.n = i2;
        this.o = null;
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setBreadCrumbShortTitle(@Nullable CharSequence charSequence) {
        this.n = 0;
        this.o = charSequence;
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setBreadCrumbTitle(@StringRes int i2) {
        this.l = i2;
        this.m = null;
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setBreadCrumbTitle(@Nullable CharSequence charSequence) {
        this.l = 0;
        this.m = charSequence;
        return this;
    }

    @NonNull
    public FragmentTransaction setCustomAnimations(@AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3) {
        return setCustomAnimations(i2, i3, 0, 0);
    }

    @NonNull
    public FragmentTransaction setCustomAnimations(@AnimRes @AnimatorRes int i2, @AnimRes @AnimatorRes int i3, @AnimRes @AnimatorRes int i4, @AnimRes @AnimatorRes int i5) {
        this.f744d = i2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
        return this;
    }

    @NonNull
    public FragmentTransaction setMaxLifecycle(@NonNull Fragment fragment, @NonNull Lifecycle.State state) {
        c(new a(10, fragment, state));
        return this;
    }

    @NonNull
    public FragmentTransaction setPrimaryNavigationFragment(@Nullable Fragment fragment) {
        c(new a(8, fragment));
        return this;
    }

    @NonNull
    public FragmentTransaction setReorderingAllowed(boolean z) {
        this.r = z;
        return this;
    }

    @NonNull
    public FragmentTransaction setTransition(int i2) {
        this.h = i2;
        return this;
    }

    @NonNull
    @Deprecated
    public FragmentTransaction setTransitionStyle(@StyleRes int i2) {
        return this;
    }

    @NonNull
    public FragmentTransaction show(@NonNull Fragment fragment) {
        c(new a(5, fragment));
        return this;
    }
}
