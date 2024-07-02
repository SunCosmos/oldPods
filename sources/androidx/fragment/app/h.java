package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.n;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelStoreOwner;

/* loaded from: classes.dex */
public class h {
    private final e a;
    private final i b;

    /* renamed from: c */
    @NonNull
    private final Fragment f771c;

    /* renamed from: d */
    private boolean f772d = false;
    private int e = -1;

    /* loaded from: classes.dex */
    public class a implements View.OnAttachStateChangeListener {
        final /* synthetic */ View a;

        a(h hVar, View view) {
            this.a = view;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            this.a.removeOnAttachStateChangeListener(this);
            ViewCompat.requestApplyInsets(this.a);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            a = iArr;
            try {
                iArr[Lifecycle.State.RESUMED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Lifecycle.State.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[Lifecycle.State.CREATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[Lifecycle.State.INITIALIZED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public h(@NonNull e eVar, @NonNull i iVar, @NonNull Fragment fragment) {
        this.a = eVar;
        this.b = iVar;
        this.f771c = fragment;
    }

    public h(@NonNull e eVar, @NonNull i iVar, @NonNull Fragment fragment, @NonNull FragmentState fragmentState) {
        this.a = eVar;
        this.b = iVar;
        this.f771c = fragment;
        fragment.f705c = null;
        fragment.f706d = null;
        fragment.r = 0;
        fragment.o = false;
        fragment.l = false;
        Fragment fragment2 = fragment.h;
        fragment.f707i = fragment2 != null ? fragment2.f : null;
        fragment.h = null;
        Bundle bundle = fragmentState.m;
        fragment.b = bundle == null ? new Bundle() : bundle;
    }

    public h(@NonNull e eVar, @NonNull i iVar, @NonNull ClassLoader classLoader, @NonNull FragmentFactory fragmentFactory, @NonNull FragmentState fragmentState) {
        this.a = eVar;
        this.b = iVar;
        Fragment instantiate = fragmentFactory.instantiate(classLoader, fragmentState.a);
        this.f771c = instantiate;
        Bundle bundle = fragmentState.j;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        instantiate.setArguments(fragmentState.j);
        instantiate.f = fragmentState.b;
        instantiate.n = fragmentState.f733c;
        instantiate.p = true;
        instantiate.w = fragmentState.f734d;
        instantiate.x = fragmentState.e;
        instantiate.y = fragmentState.f;
        instantiate.B = fragmentState.g;
        instantiate.m = fragmentState.h;
        instantiate.A = fragmentState.f735i;
        instantiate.z = fragmentState.k;
        instantiate.R = Lifecycle.State.values()[fragmentState.l];
        Bundle bundle2 = fragmentState.m;
        instantiate.b = bundle2 == null ? new Bundle() : bundle2;
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + instantiate);
        }
    }

    private boolean l(@NonNull View view) {
        if (view == this.f771c.H) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent == this.f771c.H) {
                return true;
            }
        }
        return false;
    }

    private Bundle q() {
        Bundle bundle = new Bundle();
        this.f771c.V(bundle);
        this.a.j(this.f771c, bundle, false);
        if (bundle.isEmpty()) {
            bundle = null;
        }
        if (this.f771c.H != null) {
            t();
        }
        if (this.f771c.f705c != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", this.f771c.f705c);
        }
        if (this.f771c.f706d != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBundle("android:view_registry_state", this.f771c.f706d);
        }
        if (!this.f771c.J) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", this.f771c.J);
        }
        return bundle;
    }

    public void a() {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + this.f771c);
        }
        Fragment fragment = this.f771c;
        fragment.B(fragment.b);
        e eVar = this.a;
        Fragment fragment2 = this.f771c;
        eVar.a(fragment2, fragment2.b, false);
    }

    public void b() {
        int j = this.b.j(this.f771c);
        Fragment fragment = this.f771c;
        fragment.G.addView(fragment.H, j);
    }

    public void c() {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "moveto ATTACHED: " + this.f771c);
        }
        Fragment fragment = this.f771c;
        Fragment fragment2 = fragment.h;
        h hVar = null;
        if (fragment2 != null) {
            h n = this.b.n(fragment2.f);
            if (n == null) {
                throw new IllegalStateException("Fragment " + this.f771c + " declared target fragment " + this.f771c.h + " that does not belong to this FragmentManager!");
            }
            Fragment fragment3 = this.f771c;
            fragment3.f707i = fragment3.h.f;
            fragment3.h = null;
            hVar = n;
        } else {
            String str = fragment.f707i;
            if (str != null && (hVar = this.b.n(str)) == null) {
                throw new IllegalStateException("Fragment " + this.f771c + " declared target fragment " + this.f771c.f707i + " that does not belong to this FragmentManager!");
            }
        }
        if (hVar != null && (FragmentManager.P || hVar.k().a < 1)) {
            hVar.m();
        }
        Fragment fragment4 = this.f771c;
        fragment4.t = fragment4.s.n0();
        Fragment fragment5 = this.f771c;
        fragment5.v = fragment5.s.q0();
        this.a.g(this.f771c, false);
        this.f771c.C();
        this.a.b(this.f771c, false);
    }

    public int d() {
        Fragment fragment;
        ViewGroup viewGroup;
        Fragment fragment2 = this.f771c;
        if (fragment2.s == null) {
            return fragment2.a;
        }
        int i2 = this.e;
        int i3 = b.a[fragment2.R.ordinal()];
        if (i3 != 1) {
            i2 = i3 != 2 ? i3 != 3 ? i3 != 4 ? Math.min(i2, -1) : Math.min(i2, 0) : Math.min(i2, 1) : Math.min(i2, 5);
        }
        Fragment fragment3 = this.f771c;
        if (fragment3.n) {
            if (fragment3.o) {
                i2 = Math.max(this.e, 2);
                View view = this.f771c.H;
                if (view != null && view.getParent() == null) {
                    i2 = Math.min(i2, 2);
                }
            } else {
                i2 = this.e < 4 ? Math.min(i2, fragment3.a) : Math.min(i2, 1);
            }
        }
        if (!this.f771c.l) {
            i2 = Math.min(i2, 1);
        }
        n.e.b bVar = null;
        if (FragmentManager.P && (viewGroup = (fragment = this.f771c).G) != null) {
            bVar = n.n(viewGroup, fragment.getParentFragmentManager()).l(this);
        }
        if (bVar == n.e.b.ADDING) {
            i2 = Math.min(i2, 6);
        } else if (bVar == n.e.b.REMOVING) {
            i2 = Math.max(i2, 3);
        } else {
            Fragment fragment4 = this.f771c;
            if (fragment4.m) {
                i2 = fragment4.x() ? Math.min(i2, 1) : Math.min(i2, -1);
            }
        }
        Fragment fragment5 = this.f771c;
        if (fragment5.I && fragment5.a < 5) {
            i2 = Math.min(i2, 4);
        }
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "computeExpectedState() of " + i2 + " for " + this.f771c);
        }
        return i2;
    }

    public void e() {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "moveto CREATED: " + this.f771c);
        }
        Fragment fragment = this.f771c;
        if (fragment.Q) {
            fragment.b0(fragment.b);
            this.f771c.a = 1;
            return;
        }
        this.a.h(fragment, fragment.b, false);
        Fragment fragment2 = this.f771c;
        fragment2.F(fragment2.b);
        e eVar = this.a;
        Fragment fragment3 = this.f771c;
        eVar.c(fragment3, fragment3.b, false);
    }

    public void f() {
        String str;
        if (this.f771c.n) {
            return;
        }
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f771c);
        }
        Fragment fragment = this.f771c;
        LayoutInflater L = fragment.L(fragment.b);
        ViewGroup viewGroup = null;
        Fragment fragment2 = this.f771c;
        ViewGroup viewGroup2 = fragment2.G;
        if (viewGroup2 != null) {
            viewGroup = viewGroup2;
        } else {
            int i2 = fragment2.x;
            if (i2 != 0) {
                if (i2 == -1) {
                    throw new IllegalArgumentException("Cannot create fragment " + this.f771c + " for a container view with no id");
                }
                viewGroup = (ViewGroup) fragment2.s.k0().onFindViewById(this.f771c.x);
                if (viewGroup == null) {
                    Fragment fragment3 = this.f771c;
                    if (!fragment3.p) {
                        try {
                            str = fragment3.getResources().getResourceName(this.f771c.x);
                        } catch (Resources.NotFoundException unused) {
                            str = "unknown";
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.f771c.x) + " (" + str + ") for fragment " + this.f771c);
                    }
                }
            }
        }
        Fragment fragment4 = this.f771c;
        fragment4.G = viewGroup;
        fragment4.H(L, viewGroup, fragment4.b);
        View view = this.f771c.H;
        if (view != null) {
            boolean z = false;
            view.setSaveFromParentEnabled(false);
            Fragment fragment5 = this.f771c;
            fragment5.H.setTag(R.id.fragment_container_view_tag, fragment5);
            if (viewGroup != null) {
                b();
            }
            Fragment fragment6 = this.f771c;
            if (fragment6.z) {
                fragment6.H.setVisibility(8);
            }
            if (ViewCompat.isAttachedToWindow(this.f771c.H)) {
                ViewCompat.requestApplyInsets(this.f771c.H);
            } else {
                View view2 = this.f771c.H;
                view2.addOnAttachStateChangeListener(new a(this, view2));
            }
            this.f771c.Y();
            e eVar = this.a;
            Fragment fragment7 = this.f771c;
            eVar.m(fragment7, fragment7.H, fragment7.b, false);
            int visibility = this.f771c.H.getVisibility();
            float alpha = this.f771c.H.getAlpha();
            if (FragmentManager.P) {
                this.f771c.m0(alpha);
                Fragment fragment8 = this.f771c;
                if (fragment8.G != null && visibility == 0) {
                    View findFocus = fragment8.H.findFocus();
                    if (findFocus != null) {
                        this.f771c.h0(findFocus);
                        if (FragmentManager.x0(2)) {
                            Log.v("FragmentManager", "requestFocus: Saved focused view " + findFocus + " for Fragment " + this.f771c);
                        }
                    }
                    this.f771c.H.setAlpha(0.0f);
                }
            } else {
                Fragment fragment9 = this.f771c;
                if (visibility == 0 && fragment9.G != null) {
                    z = true;
                }
                fragment9.M = z;
            }
        }
        this.f771c.a = 2;
    }

    public void g() {
        Fragment f;
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "movefrom CREATED: " + this.f771c);
        }
        Fragment fragment = this.f771c;
        boolean z = true;
        boolean z2 = fragment.m && !fragment.x();
        if (!(z2 || this.b.p().r(this.f771c))) {
            String str = this.f771c.f707i;
            if (str != null && (f = this.b.f(str)) != null && f.B) {
                this.f771c.h = f;
            }
            this.f771c.a = 0;
            return;
        }
        FragmentHostCallback<?> fragmentHostCallback = this.f771c.t;
        if (fragmentHostCallback instanceof ViewModelStoreOwner) {
            z = this.b.p().n();
        } else if (fragmentHostCallback.b() instanceof Activity) {
            z = true ^ ((Activity) fragmentHostCallback.b()).isChangingConfigurations();
        }
        if (z2 || z) {
            this.b.p().g(this.f771c);
        }
        this.f771c.I();
        this.a.d(this.f771c, false);
        for (h hVar : this.b.l()) {
            if (hVar != null) {
                Fragment k = hVar.k();
                if (this.f771c.f.equals(k.f707i)) {
                    k.h = this.f771c;
                    k.f707i = null;
                }
            }
        }
        Fragment fragment2 = this.f771c;
        String str2 = fragment2.f707i;
        if (str2 != null) {
            fragment2.h = this.b.f(str2);
        }
        this.b.r(this);
    }

    public void h() {
        View view;
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "movefrom CREATE_VIEW: " + this.f771c);
        }
        Fragment fragment = this.f771c;
        ViewGroup viewGroup = fragment.G;
        if (viewGroup != null && (view = fragment.H) != null) {
            viewGroup.removeView(view);
        }
        this.f771c.J();
        this.a.n(this.f771c, false);
        Fragment fragment2 = this.f771c;
        fragment2.G = null;
        fragment2.H = null;
        fragment2.T = null;
        fragment2.U.setValue(null);
        this.f771c.o = false;
    }

    public void i() {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + this.f771c);
        }
        this.f771c.K();
        boolean z = false;
        this.a.e(this.f771c, false);
        Fragment fragment = this.f771c;
        fragment.a = -1;
        fragment.t = null;
        fragment.v = null;
        fragment.s = null;
        if (fragment.m && !fragment.x()) {
            z = true;
        }
        if (z || this.b.p().r(this.f771c)) {
            if (FragmentManager.x0(3)) {
                Log.d("FragmentManager", "initState called for fragment: " + this.f771c);
            }
            this.f771c.v();
        }
    }

    public void j() {
        Fragment fragment = this.f771c;
        if (fragment.n && fragment.o && !fragment.q) {
            if (FragmentManager.x0(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f771c);
            }
            Fragment fragment2 = this.f771c;
            fragment2.H(fragment2.L(fragment2.b), null, this.f771c.b);
            View view = this.f771c.H;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                Fragment fragment3 = this.f771c;
                fragment3.H.setTag(R.id.fragment_container_view_tag, fragment3);
                Fragment fragment4 = this.f771c;
                if (fragment4.z) {
                    fragment4.H.setVisibility(8);
                }
                this.f771c.Y();
                e eVar = this.a;
                Fragment fragment5 = this.f771c;
                eVar.m(fragment5, fragment5.H, fragment5.b, false);
                this.f771c.a = 2;
            }
        }
    }

    @NonNull
    public Fragment k() {
        return this.f771c;
    }

    public void m() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        if (this.f772d) {
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "Ignoring re-entrant call to moveToExpectedState() for " + k());
                return;
            }
            return;
        }
        try {
            this.f772d = true;
            while (true) {
                int d2 = d();
                Fragment fragment = this.f771c;
                int i2 = fragment.a;
                if (d2 == i2) {
                    if (FragmentManager.P && fragment.N) {
                        if (fragment.H != null && (viewGroup = fragment.G) != null) {
                            n n = n.n(viewGroup, fragment.getParentFragmentManager());
                            if (this.f771c.z) {
                                n.c(this);
                            } else {
                                n.e(this);
                            }
                        }
                        Fragment fragment2 = this.f771c;
                        FragmentManager fragmentManager = fragment2.s;
                        if (fragmentManager != null) {
                            fragmentManager.w0(fragment2);
                        }
                        Fragment fragment3 = this.f771c;
                        fragment3.N = false;
                        fragment3.onHiddenChanged(fragment3.z);
                    }
                    return;
                }
                if (d2 <= i2) {
                    switch (i2 - 1) {
                        case -1:
                            i();
                            break;
                        case 0:
                            g();
                            break;
                        case 1:
                            h();
                            this.f771c.a = 1;
                            break;
                        case 2:
                            fragment.o = false;
                            fragment.a = 2;
                            break;
                        case 3:
                            if (FragmentManager.x0(3)) {
                                Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + this.f771c);
                            }
                            Fragment fragment4 = this.f771c;
                            if (fragment4.H != null && fragment4.f705c == null) {
                                t();
                            }
                            Fragment fragment5 = this.f771c;
                            if (fragment5.H != null && (viewGroup3 = fragment5.G) != null) {
                                n.n(viewGroup3, fragment5.getParentFragmentManager()).d(this);
                            }
                            this.f771c.a = 3;
                            break;
                        case 4:
                            w();
                            break;
                        case 5:
                            fragment.a = 5;
                            break;
                        case 6:
                            n();
                            break;
                    }
                } else {
                    switch (i2 + 1) {
                        case 0:
                            c();
                            break;
                        case 1:
                            e();
                            break;
                        case 2:
                            j();
                            f();
                            break;
                        case 3:
                            a();
                            break;
                        case 4:
                            if (fragment.H != null && (viewGroup2 = fragment.G) != null) {
                                n.n(viewGroup2, fragment.getParentFragmentManager()).b(n.e.c.b(this.f771c.H.getVisibility()), this);
                            }
                            this.f771c.a = 4;
                            break;
                        case 5:
                            v();
                            break;
                        case 6:
                            fragment.a = 6;
                            break;
                        case 7:
                            p();
                            break;
                    }
                }
            }
        } finally {
            this.f772d = false;
        }
    }

    public void n() {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "movefrom RESUMED: " + this.f771c);
        }
        this.f771c.Q();
        this.a.f(this.f771c, false);
    }

    public void o(@NonNull ClassLoader classLoader) {
        Bundle bundle = this.f771c.b;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        Fragment fragment = this.f771c;
        fragment.f705c = fragment.b.getSparseParcelableArray("android:view_state");
        Fragment fragment2 = this.f771c;
        fragment2.f706d = fragment2.b.getBundle("android:view_registry_state");
        Fragment fragment3 = this.f771c;
        fragment3.f707i = fragment3.b.getString("android:target_state");
        Fragment fragment4 = this.f771c;
        if (fragment4.f707i != null) {
            fragment4.j = fragment4.b.getInt("android:target_req_state", 0);
        }
        Fragment fragment5 = this.f771c;
        Boolean bool = fragment5.e;
        if (bool != null) {
            fragment5.J = bool.booleanValue();
            this.f771c.e = null;
        } else {
            fragment5.J = fragment5.b.getBoolean("android:user_visible_hint", true);
        }
        Fragment fragment6 = this.f771c;
        if (fragment6.J) {
            return;
        }
        fragment6.I = true;
    }

    public void p() {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "moveto RESUMED: " + this.f771c);
        }
        View l = this.f771c.l();
        if (l != null && l(l)) {
            boolean requestFocus = l.requestFocus();
            if (FragmentManager.x0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("requestFocus: Restoring focused view ");
                sb.append(l);
                sb.append(" ");
                sb.append(requestFocus ? "succeeded" : "failed");
                sb.append(" on Fragment ");
                sb.append(this.f771c);
                sb.append(" resulting in focused view ");
                sb.append(this.f771c.H.findFocus());
                Log.v("FragmentManager", sb.toString());
            }
        }
        this.f771c.h0(null);
        this.f771c.U();
        this.a.i(this.f771c, false);
        Fragment fragment = this.f771c;
        fragment.b = null;
        fragment.f705c = null;
        fragment.f706d = null;
    }

    @Nullable
    public Fragment.SavedState r() {
        Bundle q;
        if (this.f771c.a <= -1 || (q = q()) == null) {
            return null;
        }
        return new Fragment.SavedState(q);
    }

    @NonNull
    public FragmentState s() {
        FragmentState fragmentState = new FragmentState(this.f771c);
        Fragment fragment = this.f771c;
        if (fragment.a <= -1 || fragmentState.m != null) {
            fragmentState.m = fragment.b;
        } else {
            Bundle q = q();
            fragmentState.m = q;
            if (this.f771c.f707i != null) {
                if (q == null) {
                    fragmentState.m = new Bundle();
                }
                fragmentState.m.putString("android:target_state", this.f771c.f707i);
                int i2 = this.f771c.j;
                if (i2 != 0) {
                    fragmentState.m.putInt("android:target_req_state", i2);
                }
            }
        }
        return fragmentState;
    }

    public void t() {
        if (this.f771c.H == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        this.f771c.H.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            this.f771c.f705c = sparseArray;
        }
        Bundle bundle = new Bundle();
        this.f771c.T.e(bundle);
        if (bundle.isEmpty()) {
            return;
        }
        this.f771c.f706d = bundle;
    }

    public void u(int i2) {
        this.e = i2;
    }

    public void v() {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "moveto STARTED: " + this.f771c);
        }
        this.f771c.W();
        this.a.k(this.f771c, false);
    }

    public void w() {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "movefrom STARTED: " + this.f771c);
        }
        this.f771c.X();
        this.a.l(this.f771c, false);
    }
}
