package androidx.fragment.app;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.CallSuper;
import androidx.annotation.ContentView;
import androidx.annotation.LayoutRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import androidx.arch.core.util.Function;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.LayoutInflaterCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, ActivityResultCaller {
    static final Object a0 = new Object();
    boolean A;
    boolean B;
    boolean C;
    boolean D;
    boolean E;
    private boolean F;
    ViewGroup G;
    View H;
    boolean I;
    boolean J;
    i K;
    Runnable L;
    boolean M;
    boolean N;
    float O;
    LayoutInflater P;
    boolean Q;
    Lifecycle.State R;
    LifecycleRegistry S;

    @Nullable
    l T;
    MutableLiveData<LifecycleOwner> U;
    ViewModelProvider.Factory V;
    SavedStateRegistryController W;

    @LayoutRes
    private int X;
    private final AtomicInteger Y;
    private final ArrayList<j> Z;
    int a;
    Bundle b;

    /* renamed from: c */
    SparseArray<Parcelable> f705c;

    /* renamed from: d */
    Bundle f706d;

    @Nullable
    Boolean e;

    @NonNull
    String f;
    Bundle g;
    Fragment h;

    /* renamed from: i */
    String f707i;
    int j;
    private Boolean k;
    boolean l;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    int r;
    FragmentManager s;
    FragmentHostCallback<?> t;

    @NonNull
    FragmentManager u;
    Fragment v;
    int w;
    int x;
    String y;
    boolean z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.fragment.app.Fragment$5 */
    /* loaded from: classes.dex */
    public class AnonymousClass5 implements LifecycleEventObserver {
        AnonymousClass5() {
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
            View view;
            if (event != Lifecycle.Event.ON_STOP || (view = Fragment.this.H) == null) {
                return;
            }
            view.cancelPendingInputEvents();
        }
    }

    /* loaded from: classes.dex */
    public static class InstantiationException extends RuntimeException {
        public InstantiationException(@NonNull String str, @Nullable Exception exc) {
            super(str, exc);
        }
    }

    @SuppressLint({"BanParcelableUsage, ParcelClassLoader"})
    /* loaded from: classes.dex */
    public static class SavedState implements Parcelable {

        @NonNull
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        final Bundle a;

        /* loaded from: classes.dex */
        class a implements Parcelable.ClassLoaderCreator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(Bundle bundle) {
            this.a = bundle;
        }

        SavedState(@NonNull Parcel parcel, @Nullable ClassLoader classLoader) {
            Bundle readBundle = parcel.readBundle();
            this.a = readBundle;
            if (classLoader == null || readBundle == null) {
                return;
            }
            readBundle.setClassLoader(classLoader);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i2) {
            parcel.writeBundle(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.startPostponedEnterTransition();
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.a(false);
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        final /* synthetic */ n a;

        c(Fragment fragment, n nVar) {
            this.a = nVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.g();
        }
    }

    /* loaded from: classes.dex */
    public class d extends FragmentContainer {
        d() {
        }

        @Override // androidx.fragment.app.FragmentContainer
        @Nullable
        public View onFindViewById(int i2) {
            View view = Fragment.this.H;
            if (view != null) {
                return view.findViewById(i2);
            }
            throw new IllegalStateException("Fragment " + Fragment.this + " does not have a view");
        }

        @Override // androidx.fragment.app.FragmentContainer
        public boolean onHasView() {
            return Fragment.this.H != null;
        }
    }

    /* loaded from: classes.dex */
    class e implements Function<Void, ActivityResultRegistry> {
        e() {
        }

        @Override // androidx.arch.core.util.Function
        /* renamed from: a */
        public ActivityResultRegistry apply(Void r3) {
            Fragment fragment = Fragment.this;
            Object obj = fragment.t;
            return obj instanceof ActivityResultRegistryOwner ? ((ActivityResultRegistryOwner) obj).getActivityResultRegistry() : fragment.requireActivity().getActivityResultRegistry();
        }
    }

    /* loaded from: classes.dex */
    class f implements Function<Void, ActivityResultRegistry> {
        final /* synthetic */ ActivityResultRegistry a;

        f(Fragment fragment, ActivityResultRegistry activityResultRegistry) {
            this.a = activityResultRegistry;
        }

        @Override // androidx.arch.core.util.Function
        /* renamed from: a */
        public ActivityResultRegistry apply(Void r1) {
            return this.a;
        }
    }

    /* loaded from: classes.dex */
    public class g extends j {
        final /* synthetic */ Function a;
        final /* synthetic */ AtomicReference b;

        /* renamed from: c */
        final /* synthetic */ ActivityResultContract f708c;

        /* renamed from: d */
        final /* synthetic */ ActivityResultCallback f709d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(Function function, AtomicReference atomicReference, ActivityResultContract activityResultContract, ActivityResultCallback activityResultCallback) {
            super(null);
            this.a = function;
            this.b = atomicReference;
            this.f708c = activityResultContract;
            this.f709d = activityResultCallback;
        }

        @Override // androidx.fragment.app.Fragment.j
        void a() {
            String e = Fragment.this.e();
            this.b.set(((ActivityResultRegistry) this.a.apply(null)).register(e, Fragment.this, this.f708c, this.f709d));
        }
    }

    /* loaded from: classes.dex */
    public class h<I> extends ActivityResultLauncher<I> {
        final /* synthetic */ AtomicReference a;
        final /* synthetic */ ActivityResultContract b;

        h(Fragment fragment, AtomicReference atomicReference, ActivityResultContract activityResultContract) {
            this.a = atomicReference;
            this.b = activityResultContract;
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        @NonNull
        public ActivityResultContract<I, ?> getContract() {
            return this.b;
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void launch(I i2, @Nullable ActivityOptionsCompat activityOptionsCompat) {
            ActivityResultLauncher activityResultLauncher = (ActivityResultLauncher) this.a.get();
            if (activityResultLauncher == null) {
                throw new IllegalStateException("Operation cannot be started before fragment is in created state");
            }
            activityResultLauncher.launch(i2, activityOptionsCompat);
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void unregister() {
            ActivityResultLauncher activityResultLauncher = (ActivityResultLauncher) this.a.getAndSet(null);
            if (activityResultLauncher != null) {
                activityResultLauncher.unregister();
            }
        }
    }

    /* loaded from: classes.dex */
    public static class i {
        View a;
        Animator b;

        /* renamed from: c */
        boolean f710c;

        /* renamed from: d */
        int f711d;
        int e;
        int f;
        int g;
        int h;

        /* renamed from: i */
        ArrayList<String> f712i;
        ArrayList<String> j;
        Object k = null;
        Object l;
        Object m;
        Object n;
        Object o;
        Object p;
        Boolean q;
        Boolean r;
        SharedElementCallback s;
        SharedElementCallback t;
        float u;
        View v;
        boolean w;
        k x;
        boolean y;

        i() {
            Object obj = Fragment.a0;
            this.l = obj;
            this.m = null;
            this.n = obj;
            this.o = null;
            this.p = obj;
            this.s = null;
            this.t = null;
            this.u = 1.0f;
            this.v = null;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class j {
        private j() {
        }

        /* synthetic */ j(a aVar) {
            this();
        }

        abstract void a();
    }

    /* loaded from: classes.dex */
    public interface k {
        void a();

        void b();
    }

    public Fragment() {
        this.a = -1;
        this.f = UUID.randomUUID().toString();
        this.f707i = null;
        this.k = null;
        this.u = new androidx.fragment.app.f();
        this.E = true;
        this.J = true;
        this.L = new a();
        this.R = Lifecycle.State.RESUMED;
        this.U = new MutableLiveData<>();
        this.Y = new AtomicInteger();
        this.Z = new ArrayList<>();
        u();
    }

    @ContentView
    public Fragment(@LayoutRes int i2) {
        this();
        this.X = i2;
    }

    @NonNull
    private <I, O> ActivityResultLauncher<I> Z(@NonNull ActivityResultContract<I, O> activityResultContract, @NonNull Function<Void, ActivityResultRegistry> function, @NonNull ActivityResultCallback<O> activityResultCallback) {
        if (this.a <= 1) {
            AtomicReference atomicReference = new AtomicReference();
            a0(new g(function, atomicReference, activityResultContract, activityResultCallback));
            return new h(this, atomicReference, activityResultContract);
        }
        throw new IllegalStateException("Fragment " + this + " is attempting to registerForActivityResult after being created. Fragments must call registerForActivityResult() before they are created (i.e. initialization, onAttach(), or onCreate()).");
    }

    private void a0(@NonNull j jVar) {
        if (this.a >= 0) {
            jVar.a();
        } else {
            this.Z.add(jVar);
        }
    }

    private i c() {
        if (this.K == null) {
            this.K = new i();
        }
        return this.K;
    }

    private void c0() {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + this);
        }
        if (this.H != null) {
            d0(this.b);
        }
        this.b = null;
    }

    @NonNull
    @Deprecated
    public static Fragment instantiate(@NonNull Context context, @NonNull String str) {
        return instantiate(context, str, null);
    }

    @NonNull
    @Deprecated
    public static Fragment instantiate(@NonNull Context context, @NonNull String str, @Nullable Bundle bundle) {
        try {
            Fragment newInstance = FragmentFactory.loadFragmentClass(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(newInstance.getClass().getClassLoader());
                newInstance.setArguments(bundle);
            }
            return newInstance;
        } catch (IllegalAccessException e2) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (java.lang.InstantiationException e3) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        } catch (NoSuchMethodException e4) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e4);
        } catch (InvocationTargetException e5) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e5);
        }
    }

    private int m() {
        Lifecycle.State state = this.R;
        return (state == Lifecycle.State.INITIALIZED || this.v == null) ? state.ordinal() : Math.min(state.ordinal(), this.v.m());
    }

    private void u() {
        this.S = new LifecycleRegistry(this);
        this.W = SavedStateRegistryController.create(this);
        this.V = null;
    }

    public void A() {
        this.u.K0();
    }

    public void B(Bundle bundle) {
        this.u.K0();
        this.a = 3;
        this.F = false;
        onActivityCreated(bundle);
        if (this.F) {
            c0();
            this.u.w();
        } else {
            throw new p("Fragment " + this + " did not call through to super.onActivityCreated()");
        }
    }

    public void C() {
        Iterator<j> it = this.Z.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.Z.clear();
        this.u.j(this.t, b(), this);
        this.a = 0;
        this.F = false;
        onAttach(this.t.b());
        if (this.F) {
            this.s.G(this);
            this.u.x();
        } else {
            throw new p("Fragment " + this + " did not call through to super.onAttach()");
        }
    }

    public void D(@NonNull Configuration configuration) {
        onConfigurationChanged(configuration);
        this.u.y(configuration);
    }

    public boolean E(@NonNull MenuItem menuItem) {
        if (this.z) {
            return false;
        }
        if (onContextItemSelected(menuItem)) {
            return true;
        }
        return this.u.z(menuItem);
    }

    public void F(Bundle bundle) {
        this.u.K0();
        this.a = 1;
        this.F = false;
        if (Build.VERSION.SDK_INT >= 19) {
            this.S.addObserver(new LifecycleEventObserver() { // from class: androidx.fragment.app.Fragment.5
                AnonymousClass5() {
                }

                @Override // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
                    View view;
                    if (event != Lifecycle.Event.ON_STOP || (view = Fragment.this.H) == null) {
                        return;
                    }
                    view.cancelPendingInputEvents();
                }
            });
        }
        this.W.performRestore(bundle);
        onCreate(bundle);
        this.Q = true;
        if (this.F) {
            this.S.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
            return;
        }
        throw new p("Fragment " + this + " did not call through to super.onCreate()");
    }

    public boolean G(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        boolean z = false;
        if (this.z) {
            return false;
        }
        if (this.D && this.E) {
            z = true;
            onCreateOptionsMenu(menu, menuInflater);
        }
        return z | this.u.B(menu, menuInflater);
    }

    public void H(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.u.K0();
        this.q = true;
        this.T = new l(this, getViewModelStore());
        View onCreateView = onCreateView(layoutInflater, viewGroup, bundle);
        this.H = onCreateView;
        if (onCreateView == null) {
            if (this.T.c()) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.T = null;
        } else {
            this.T.b();
            ViewTreeLifecycleOwner.set(this.H, this.T);
            ViewTreeViewModelStoreOwner.set(this.H, this.T);
            ViewTreeSavedStateRegistryOwner.set(this.H, this.T);
            this.U.setValue(this.T);
        }
    }

    public void I() {
        this.u.C();
        this.S.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        this.a = 0;
        this.F = false;
        this.Q = false;
        onDestroy();
        if (this.F) {
            return;
        }
        throw new p("Fragment " + this + " did not call through to super.onDestroy()");
    }

    public void J() {
        this.u.D();
        if (this.H != null && this.T.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED)) {
            this.T.a(Lifecycle.Event.ON_DESTROY);
        }
        this.a = 1;
        this.F = false;
        onDestroyView();
        if (this.F) {
            LoaderManager.getInstance(this).markForRedelivery();
            this.q = false;
        } else {
            throw new p("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    public void K() {
        this.a = -1;
        this.F = false;
        onDetach();
        this.P = null;
        if (this.F) {
            if (this.u.isDestroyed()) {
                return;
            }
            this.u.C();
            this.u = new androidx.fragment.app.f();
            return;
        }
        throw new p("Fragment " + this + " did not call through to super.onDetach()");
    }

    @NonNull
    public LayoutInflater L(@Nullable Bundle bundle) {
        LayoutInflater onGetLayoutInflater = onGetLayoutInflater(bundle);
        this.P = onGetLayoutInflater;
        return onGetLayoutInflater;
    }

    public void M() {
        onLowMemory();
        this.u.E();
    }

    public void N(boolean z) {
        onMultiWindowModeChanged(z);
        this.u.F(z);
    }

    public boolean O(@NonNull MenuItem menuItem) {
        if (this.z) {
            return false;
        }
        if (this.D && this.E && onOptionsItemSelected(menuItem)) {
            return true;
        }
        return this.u.H(menuItem);
    }

    public void P(@NonNull Menu menu) {
        if (this.z) {
            return;
        }
        if (this.D && this.E) {
            onOptionsMenuClosed(menu);
        }
        this.u.I(menu);
    }

    public void Q() {
        this.u.K();
        if (this.H != null) {
            this.T.a(Lifecycle.Event.ON_PAUSE);
        }
        this.S.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        this.a = 6;
        this.F = false;
        onPause();
        if (this.F) {
            return;
        }
        throw new p("Fragment " + this + " did not call through to super.onPause()");
    }

    public void R(boolean z) {
        onPictureInPictureModeChanged(z);
        this.u.L(z);
    }

    public boolean S(@NonNull Menu menu) {
        boolean z = false;
        if (this.z) {
            return false;
        }
        if (this.D && this.E) {
            z = true;
            onPrepareOptionsMenu(menu);
        }
        return z | this.u.M(menu);
    }

    public void T() {
        boolean A0 = this.s.A0(this);
        Boolean bool = this.k;
        if (bool == null || bool.booleanValue() != A0) {
            this.k = Boolean.valueOf(A0);
            onPrimaryNavigationFragmentChanged(A0);
            this.u.N();
        }
    }

    public void U() {
        this.u.K0();
        this.u.X(true);
        this.a = 7;
        this.F = false;
        onResume();
        if (!this.F) {
            throw new p("Fragment " + this + " did not call through to super.onResume()");
        }
        LifecycleRegistry lifecycleRegistry = this.S;
        Lifecycle.Event event = Lifecycle.Event.ON_RESUME;
        lifecycleRegistry.handleLifecycleEvent(event);
        if (this.H != null) {
            this.T.a(event);
        }
        this.u.O();
    }

    public void V(Bundle bundle) {
        onSaveInstanceState(bundle);
        this.W.performSave(bundle);
        Parcelable Z0 = this.u.Z0();
        if (Z0 != null) {
            bundle.putParcelable("android:support:fragments", Z0);
        }
    }

    public void W() {
        this.u.K0();
        this.u.X(true);
        this.a = 5;
        this.F = false;
        onStart();
        if (!this.F) {
            throw new p("Fragment " + this + " did not call through to super.onStart()");
        }
        LifecycleRegistry lifecycleRegistry = this.S;
        Lifecycle.Event event = Lifecycle.Event.ON_START;
        lifecycleRegistry.handleLifecycleEvent(event);
        if (this.H != null) {
            this.T.a(event);
        }
        this.u.P();
    }

    public void X() {
        this.u.R();
        if (this.H != null) {
            this.T.a(Lifecycle.Event.ON_STOP);
        }
        this.S.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        this.a = 4;
        this.F = false;
        onStop();
        if (this.F) {
            return;
        }
        throw new p("Fragment " + this + " did not call through to super.onStop()");
    }

    public void Y() {
        onViewCreated(this.H, this.b);
        this.u.S();
    }

    void a(boolean z) {
        ViewGroup viewGroup;
        FragmentManager fragmentManager;
        i iVar = this.K;
        k kVar = null;
        if (iVar != null) {
            iVar.w = false;
            k kVar2 = iVar.x;
            iVar.x = null;
            kVar = kVar2;
        }
        if (kVar != null) {
            kVar.a();
            return;
        }
        if (!FragmentManager.P || this.H == null || (viewGroup = this.G) == null || (fragmentManager = this.s) == null) {
            return;
        }
        n n = n.n(viewGroup, fragmentManager);
        n.p();
        if (z) {
            this.t.c().post(new c(this, n));
        } else {
            n.g();
        }
    }

    @NonNull
    public FragmentContainer b() {
        return new d();
    }

    public void b0(@Nullable Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.u.W0(parcelable);
        this.u.A();
    }

    @Nullable
    public Fragment d(@NonNull String str) {
        return str.equals(this.f) ? this : this.u.d0(str);
    }

    final void d0(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.f705c;
        if (sparseArray != null) {
            this.H.restoreHierarchyState(sparseArray);
            this.f705c = null;
        }
        if (this.H != null) {
            this.T.d(this.f706d);
            this.f706d = null;
        }
        this.F = false;
        onViewStateRestored(bundle);
        if (this.F) {
            if (this.H != null) {
                this.T.a(Lifecycle.Event.ON_CREATE);
            }
        } else {
            throw new p("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    public void dump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.w));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.x));
        printWriter.print(" mTag=");
        printWriter.println(this.y);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.a);
        printWriter.print(" mWho=");
        printWriter.print(this.f);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.r);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.l);
        printWriter.print(" mRemoving=");
        printWriter.print(this.m);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.n);
        printWriter.print(" mInLayout=");
        printWriter.println(this.o);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.z);
        printWriter.print(" mDetached=");
        printWriter.print(this.A);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.E);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.D);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.B);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.J);
        if (this.s != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.s);
        }
        if (this.t != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.t);
        }
        if (this.v != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.v);
        }
        if (this.g != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.g);
        }
        if (this.b != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.b);
        }
        if (this.f705c != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.f705c);
        }
        if (this.f706d != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewRegistryState=");
            printWriter.println(this.f706d);
        }
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(targetFragment);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.j);
        }
        printWriter.print(str);
        printWriter.print("mPopDirection=");
        printWriter.println(o());
        if (h() != 0) {
            printWriter.print(str);
            printWriter.print("getEnterAnim=");
            printWriter.println(h());
        }
        if (j() != 0) {
            printWriter.print(str);
            printWriter.print("getExitAnim=");
            printWriter.println(j());
        }
        if (p() != 0) {
            printWriter.print(str);
            printWriter.print("getPopEnterAnim=");
            printWriter.println(p());
        }
        if (q() != 0) {
            printWriter.print(str);
            printWriter.print("getPopExitAnim=");
            printWriter.println(q());
        }
        if (this.G != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.G);
        }
        if (this.H != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.H);
        }
        if (f() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(f());
        }
        if (getContext() != null) {
            LoaderManager.getInstance(this).dump(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.u + ":");
        this.u.dump(str + "  ", fileDescriptor, printWriter, strArr);
    }

    @NonNull
    String e() {
        return "fragment_" + this.f + "_rq#" + this.Y.getAndIncrement();
    }

    public void e0(View view) {
        c().a = view;
    }

    public final boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    public View f() {
        i iVar = this.K;
        if (iVar == null) {
            return null;
        }
        return iVar.a;
    }

    public void f0(int i2, int i3, int i4, int i5) {
        if (this.K == null && i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) {
            return;
        }
        c().f711d = i2;
        c().e = i3;
        c().f = i4;
        c().g = i5;
    }

    public Animator g() {
        i iVar = this.K;
        if (iVar == null) {
            return null;
        }
        return iVar.b;
    }

    public void g0(Animator animator) {
        c().b = animator;
    }

    @Nullable
    public final FragmentActivity getActivity() {
        FragmentHostCallback<?> fragmentHostCallback = this.t;
        if (fragmentHostCallback == null) {
            return null;
        }
        return (FragmentActivity) fragmentHostCallback.a();
    }

    public boolean getAllowEnterTransitionOverlap() {
        Boolean bool;
        i iVar = this.K;
        if (iVar == null || (bool = iVar.r) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public boolean getAllowReturnTransitionOverlap() {
        Boolean bool;
        i iVar = this.K;
        if (iVar == null || (bool = iVar.q) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    @Nullable
    public final Bundle getArguments() {
        return this.g;
    }

    @NonNull
    public final FragmentManager getChildFragmentManager() {
        if (this.t != null) {
            return this.u;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    @Nullable
    public Context getContext() {
        FragmentHostCallback<?> fragmentHostCallback = this.t;
        if (fragmentHostCallback == null) {
            return null;
        }
        return fragmentHostCallback.b();
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    @NonNull
    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        if (this.s == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (this.V == null) {
            Application application = null;
            Context applicationContext = requireContext().getApplicationContext();
            while (true) {
                if (!(applicationContext instanceof ContextWrapper)) {
                    break;
                }
                if (applicationContext instanceof Application) {
                    application = (Application) applicationContext;
                    break;
                }
                applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
            }
            if (application == null && FragmentManager.x0(3)) {
                Log.d("FragmentManager", "Could not find Application instance from Context " + requireContext().getApplicationContext() + ", you will not be able to use AndroidViewModel with the default ViewModelProvider.Factory");
            }
            this.V = new SavedStateViewModelFactory(application, this, getArguments());
        }
        return this.V;
    }

    @Nullable
    public Object getEnterTransition() {
        i iVar = this.K;
        if (iVar == null) {
            return null;
        }
        return iVar.k;
    }

    @Nullable
    public Object getExitTransition() {
        i iVar = this.K;
        if (iVar == null) {
            return null;
        }
        return iVar.m;
    }

    @Nullable
    @Deprecated
    public final FragmentManager getFragmentManager() {
        return this.s;
    }

    @Nullable
    public final Object getHost() {
        FragmentHostCallback<?> fragmentHostCallback = this.t;
        if (fragmentHostCallback == null) {
            return null;
        }
        return fragmentHostCallback.onGetHost();
    }

    public final int getId() {
        return this.w;
    }

    @NonNull
    public final LayoutInflater getLayoutInflater() {
        LayoutInflater layoutInflater = this.P;
        return layoutInflater == null ? L(null) : layoutInflater;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public LayoutInflater getLayoutInflater(@Nullable Bundle bundle) {
        FragmentHostCallback<?> fragmentHostCallback = this.t;
        if (fragmentHostCallback == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        LayoutInflater onGetLayoutInflater = fragmentHostCallback.onGetLayoutInflater();
        LayoutInflaterCompat.setFactory2(onGetLayoutInflater, this.u.o0());
        return onGetLayoutInflater;
    }

    @Override // androidx.lifecycle.LifecycleOwner
    @NonNull
    public Lifecycle getLifecycle() {
        return this.S;
    }

    @NonNull
    @Deprecated
    public LoaderManager getLoaderManager() {
        return LoaderManager.getInstance(this);
    }

    @Nullable
    public final Fragment getParentFragment() {
        return this.v;
    }

    @NonNull
    public final FragmentManager getParentFragmentManager() {
        FragmentManager fragmentManager = this.s;
        if (fragmentManager != null) {
            return fragmentManager;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    @Nullable
    public Object getReenterTransition() {
        i iVar = this.K;
        if (iVar == null) {
            return null;
        }
        Object obj = iVar.n;
        return obj == a0 ? getExitTransition() : obj;
    }

    @NonNull
    public final Resources getResources() {
        return requireContext().getResources();
    }

    @Deprecated
    public final boolean getRetainInstance() {
        return this.B;
    }

    @Nullable
    public Object getReturnTransition() {
        i iVar = this.K;
        if (iVar == null) {
            return null;
        }
        Object obj = iVar.l;
        return obj == a0 ? getEnterTransition() : obj;
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    @NonNull
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.W.getSavedStateRegistry();
    }

    @Nullable
    public Object getSharedElementEnterTransition() {
        i iVar = this.K;
        if (iVar == null) {
            return null;
        }
        return iVar.o;
    }

    @Nullable
    public Object getSharedElementReturnTransition() {
        i iVar = this.K;
        if (iVar == null) {
            return null;
        }
        Object obj = iVar.p;
        return obj == a0 ? getSharedElementEnterTransition() : obj;
    }

    @NonNull
    public final String getString(@StringRes int i2) {
        return getResources().getString(i2);
    }

    @NonNull
    public final String getString(@StringRes int i2, @Nullable Object... objArr) {
        return getResources().getString(i2, objArr);
    }

    @Nullable
    public final String getTag() {
        return this.y;
    }

    @Nullable
    @Deprecated
    public final Fragment getTargetFragment() {
        String str;
        Fragment fragment = this.h;
        if (fragment != null) {
            return fragment;
        }
        FragmentManager fragmentManager = this.s;
        if (fragmentManager == null || (str = this.f707i) == null) {
            return null;
        }
        return fragmentManager.c0(str);
    }

    @Deprecated
    public final int getTargetRequestCode() {
        return this.j;
    }

    @NonNull
    public final CharSequence getText(@StringRes int i2) {
        return getResources().getText(i2);
    }

    @Deprecated
    public boolean getUserVisibleHint() {
        return this.J;
    }

    @Nullable
    public View getView() {
        return this.H;
    }

    @NonNull
    @MainThread
    public LifecycleOwner getViewLifecycleOwner() {
        l lVar = this.T;
        if (lVar != null) {
            return lVar;
        }
        throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
    }

    @NonNull
    public LiveData<LifecycleOwner> getViewLifecycleOwnerLiveData() {
        return this.U;
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    @NonNull
    public ViewModelStore getViewModelStore() {
        if (this.s == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (m() != Lifecycle.State.INITIALIZED.ordinal()) {
            return this.s.t0(this);
        }
        throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
    }

    public int h() {
        i iVar = this.K;
        if (iVar == null) {
            return 0;
        }
        return iVar.f711d;
    }

    public void h0(View view) {
        c().v = view;
    }

    @SuppressLint({"KotlinPropertyAccess"})
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public final boolean hasOptionsMenu() {
        return this.D;
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public SharedElementCallback i() {
        i iVar = this.K;
        if (iVar == null) {
            return null;
        }
        return iVar.s;
    }

    public void i0(boolean z) {
        c().y = z;
    }

    public final boolean isAdded() {
        return this.t != null && this.l;
    }

    public final boolean isDetached() {
        return this.A;
    }

    public final boolean isHidden() {
        return this.z;
    }

    public final boolean isInLayout() {
        return this.o;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public final boolean isMenuVisible() {
        FragmentManager fragmentManager;
        return this.E && ((fragmentManager = this.s) == null || fragmentManager.z0(this.v));
    }

    public final boolean isRemoving() {
        return this.m;
    }

    public final boolean isResumed() {
        return this.a >= 7;
    }

    public final boolean isStateSaved() {
        FragmentManager fragmentManager = this.s;
        if (fragmentManager == null) {
            return false;
        }
        return fragmentManager.isStateSaved();
    }

    public final boolean isVisible() {
        View view;
        return (!isAdded() || isHidden() || (view = this.H) == null || view.getWindowToken() == null || this.H.getVisibility() != 0) ? false : true;
    }

    public int j() {
        i iVar = this.K;
        if (iVar == null) {
            return 0;
        }
        return iVar.e;
    }

    public void j0(int i2) {
        if (this.K == null && i2 == 0) {
            return;
        }
        c();
        this.K.h = i2;
    }

    public SharedElementCallback k() {
        i iVar = this.K;
        if (iVar == null) {
            return null;
        }
        return iVar.t;
    }

    public void k0(k kVar) {
        c();
        i iVar = this.K;
        k kVar2 = iVar.x;
        if (kVar == kVar2) {
            return;
        }
        if (kVar != null && kVar2 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        if (iVar.w) {
            iVar.x = kVar;
        }
        if (kVar != null) {
            kVar.b();
        }
    }

    public View l() {
        i iVar = this.K;
        if (iVar == null) {
            return null;
        }
        return iVar.v;
    }

    public void l0(boolean z) {
        if (this.K == null) {
            return;
        }
        c().f710c = z;
    }

    public void m0(float f2) {
        c().u = f2;
    }

    public int n() {
        i iVar = this.K;
        if (iVar == null) {
            return 0;
        }
        return iVar.h;
    }

    public void n0(@Nullable ArrayList<String> arrayList, @Nullable ArrayList<String> arrayList2) {
        c();
        i iVar = this.K;
        iVar.f712i = arrayList;
        iVar.j = arrayList2;
    }

    public boolean o() {
        i iVar = this.K;
        if (iVar == null) {
            return false;
        }
        return iVar.f710c;
    }

    @CallSuper
    @MainThread
    @Deprecated
    public void onActivityCreated(@Nullable Bundle bundle) {
        this.F = true;
    }

    @Deprecated
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in onActivityResult(): requestCode: " + i2 + " resultCode: " + i3 + " data: " + intent);
        }
    }

    @CallSuper
    @MainThread
    @Deprecated
    public void onAttach(@NonNull Activity activity) {
        this.F = true;
    }

    @CallSuper
    @MainThread
    public void onAttach(@NonNull Context context) {
        this.F = true;
        FragmentHostCallback<?> fragmentHostCallback = this.t;
        Activity a2 = fragmentHostCallback == null ? null : fragmentHostCallback.a();
        if (a2 != null) {
            this.F = false;
            onAttach(a2);
        }
    }

    @MainThread
    @Deprecated
    public void onAttachFragment(@NonNull Fragment fragment) {
    }

    @Override // android.content.ComponentCallbacks
    @CallSuper
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        this.F = true;
    }

    @MainThread
    public boolean onContextItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @CallSuper
    @MainThread
    public void onCreate(@Nullable Bundle bundle) {
        this.F = true;
        b0(bundle);
        if (this.u.B0(1)) {
            return;
        }
        this.u.A();
    }

    @Nullable
    @MainThread
    public Animation onCreateAnimation(int i2, boolean z, int i3) {
        return null;
    }

    @Nullable
    @MainThread
    public Animator onCreateAnimator(int i2, boolean z, int i3) {
        return null;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    @MainThread
    public void onCreateContextMenu(@NonNull ContextMenu contextMenu, @NonNull View view, @Nullable ContextMenu.ContextMenuInfo contextMenuInfo) {
        requireActivity().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @MainThread
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
    }

    @Nullable
    @MainThread
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        int i2 = this.X;
        if (i2 != 0) {
            return layoutInflater.inflate(i2, viewGroup, false);
        }
        return null;
    }

    @CallSuper
    @MainThread
    public void onDestroy() {
        this.F = true;
    }

    @MainThread
    public void onDestroyOptionsMenu() {
    }

    @CallSuper
    @MainThread
    public void onDestroyView() {
        this.F = true;
    }

    @CallSuper
    @MainThread
    public void onDetach() {
        this.F = true;
    }

    @NonNull
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle bundle) {
        return getLayoutInflater(bundle);
    }

    @MainThread
    public void onHiddenChanged(boolean z) {
    }

    @CallSuper
    @UiThread
    @Deprecated
    public void onInflate(@NonNull Activity activity, @NonNull AttributeSet attributeSet, @Nullable Bundle bundle) {
        this.F = true;
    }

    @CallSuper
    @UiThread
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attributeSet, @Nullable Bundle bundle) {
        this.F = true;
        FragmentHostCallback<?> fragmentHostCallback = this.t;
        Activity a2 = fragmentHostCallback == null ? null : fragmentHostCallback.a();
        if (a2 != null) {
            this.F = false;
            onInflate(a2, attributeSet, bundle);
        }
    }

    @Override // android.content.ComponentCallbacks
    @CallSuper
    @MainThread
    public void onLowMemory() {
        this.F = true;
    }

    public void onMultiWindowModeChanged(boolean z) {
    }

    @MainThread
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @MainThread
    public void onOptionsMenuClosed(@NonNull Menu menu) {
    }

    @CallSuper
    @MainThread
    public void onPause() {
        this.F = true;
    }

    public void onPictureInPictureModeChanged(boolean z) {
    }

    @MainThread
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
    }

    @MainThread
    public void onPrimaryNavigationFragmentChanged(boolean z) {
    }

    @Deprecated
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
    }

    @CallSuper
    @MainThread
    public void onResume() {
        this.F = true;
    }

    @MainThread
    public void onSaveInstanceState(@NonNull Bundle bundle) {
    }

    @CallSuper
    @MainThread
    public void onStart() {
        this.F = true;
    }

    @CallSuper
    @MainThread
    public void onStop() {
        this.F = true;
    }

    @MainThread
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
    }

    @CallSuper
    @MainThread
    public void onViewStateRestored(@Nullable Bundle bundle) {
        this.F = true;
    }

    public int p() {
        i iVar = this.K;
        if (iVar == null) {
            return 0;
        }
        return iVar.f;
    }

    public void postponeEnterTransition() {
        c().w = true;
    }

    public final void postponeEnterTransition(long j2, @NonNull TimeUnit timeUnit) {
        c().w = true;
        FragmentManager fragmentManager = this.s;
        Handler c2 = fragmentManager != null ? fragmentManager.n0().c() : new Handler(Looper.getMainLooper());
        c2.removeCallbacks(this.L);
        c2.postDelayed(this.L, timeUnit.toMillis(j2));
    }

    public int q() {
        i iVar = this.K;
        if (iVar == null) {
            return 0;
        }
        return iVar.g;
    }

    public float r() {
        i iVar = this.K;
        if (iVar == null) {
            return 1.0f;
        }
        return iVar.u;
    }

    @Override // androidx.activity.result.ActivityResultCaller
    @NonNull
    @MainThread
    public final <I, O> ActivityResultLauncher<I> registerForActivityResult(@NonNull ActivityResultContract<I, O> activityResultContract, @NonNull ActivityResultCallback<O> activityResultCallback) {
        return Z(activityResultContract, new e(), activityResultCallback);
    }

    @Override // androidx.activity.result.ActivityResultCaller
    @NonNull
    @MainThread
    public final <I, O> ActivityResultLauncher<I> registerForActivityResult(@NonNull ActivityResultContract<I, O> activityResultContract, @NonNull ActivityResultRegistry activityResultRegistry, @NonNull ActivityResultCallback<O> activityResultCallback) {
        return Z(activityResultContract, new f(this, activityResultRegistry), activityResultCallback);
    }

    public void registerForContextMenu(@NonNull View view) {
        view.setOnCreateContextMenuListener(this);
    }

    @Deprecated
    public final void requestPermissions(@NonNull String[] strArr, int i2) {
        if (this.t != null) {
            getParentFragmentManager().C0(this, strArr, i2);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    @NonNull
    public final FragmentActivity requireActivity() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return activity;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    @NonNull
    public final Bundle requireArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments;
        }
        throw new IllegalStateException("Fragment " + this + " does not have any arguments.");
    }

    @NonNull
    public final Context requireContext() {
        Context context = getContext();
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    @NonNull
    @Deprecated
    public final FragmentManager requireFragmentManager() {
        return getParentFragmentManager();
    }

    @NonNull
    public final Object requireHost() {
        Object host = getHost();
        if (host != null) {
            return host;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a host.");
    }

    @NonNull
    public final Fragment requireParentFragment() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            return parentFragment;
        }
        if (getContext() == null) {
            throw new IllegalStateException("Fragment " + this + " is not attached to any Fragment or host");
        }
        throw new IllegalStateException("Fragment " + this + " is not a child Fragment, it is directly attached to " + getContext());
    }

    @NonNull
    public final View requireView() {
        View view = getView();
        if (view != null) {
            return view;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    @NonNull
    public ArrayList<String> s() {
        ArrayList<String> arrayList;
        i iVar = this.K;
        return (iVar == null || (arrayList = iVar.f712i) == null) ? new ArrayList<>() : arrayList;
    }

    public void setAllowEnterTransitionOverlap(boolean z) {
        c().r = Boolean.valueOf(z);
    }

    public void setAllowReturnTransitionOverlap(boolean z) {
        c().q = Boolean.valueOf(z);
    }

    public void setArguments(@Nullable Bundle bundle) {
        if (this.s != null && isStateSaved()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.g = bundle;
    }

    public void setEnterSharedElementCallback(@Nullable SharedElementCallback sharedElementCallback) {
        c().s = sharedElementCallback;
    }

    public void setEnterTransition(@Nullable Object obj) {
        c().k = obj;
    }

    public void setExitSharedElementCallback(@Nullable SharedElementCallback sharedElementCallback) {
        c().t = sharedElementCallback;
    }

    public void setExitTransition(@Nullable Object obj) {
        c().m = obj;
    }

    public void setHasOptionsMenu(boolean z) {
        if (this.D != z) {
            this.D = z;
            if (!isAdded() || isHidden()) {
                return;
            }
            this.t.onSupportInvalidateOptionsMenu();
        }
    }

    public void setInitialSavedState(@Nullable SavedState savedState) {
        Bundle bundle;
        if (this.s != null) {
            throw new IllegalStateException("Fragment already added");
        }
        if (savedState == null || (bundle = savedState.a) == null) {
            bundle = null;
        }
        this.b = bundle;
    }

    public void setMenuVisibility(boolean z) {
        if (this.E != z) {
            this.E = z;
            if (this.D && isAdded() && !isHidden()) {
                this.t.onSupportInvalidateOptionsMenu();
            }
        }
    }

    public void setReenterTransition(@Nullable Object obj) {
        c().n = obj;
    }

    @Deprecated
    public void setRetainInstance(boolean z) {
        this.B = z;
        FragmentManager fragmentManager = this.s;
        if (fragmentManager == null) {
            this.C = true;
        } else if (z) {
            fragmentManager.h(this);
        } else {
            fragmentManager.T0(this);
        }
    }

    public void setReturnTransition(@Nullable Object obj) {
        c().l = obj;
    }

    public void setSharedElementEnterTransition(@Nullable Object obj) {
        c().o = obj;
    }

    public void setSharedElementReturnTransition(@Nullable Object obj) {
        c().p = obj;
    }

    @Deprecated
    public void setTargetFragment(@Nullable Fragment fragment, int i2) {
        FragmentManager fragmentManager = this.s;
        FragmentManager fragmentManager2 = fragment != null ? fragment.s : null;
        if (fragmentManager != null && fragmentManager2 != null && fragmentManager != fragmentManager2) {
            throw new IllegalArgumentException("Fragment " + fragment + " must share the same FragmentManager to be set as a target fragment");
        }
        for (Fragment fragment2 = fragment; fragment2 != null; fragment2 = fragment2.getTargetFragment()) {
            if (fragment2.equals(this)) {
                throw new IllegalArgumentException("Setting " + fragment + " as the target of " + this + " would create a target cycle");
            }
        }
        if (fragment == null) {
            this.f707i = null;
        } else {
            if (this.s == null || fragment.s == null) {
                this.f707i = null;
                this.h = fragment;
                this.j = i2;
            }
            this.f707i = fragment.f;
        }
        this.h = null;
        this.j = i2;
    }

    @Deprecated
    public void setUserVisibleHint(boolean z) {
        if (!this.J && z && this.a < 5 && this.s != null && isAdded() && this.Q) {
            FragmentManager fragmentManager = this.s;
            fragmentManager.M0(fragmentManager.t(this));
        }
        this.J = z;
        this.I = this.a < 5 && !z;
        if (this.b != null) {
            this.e = Boolean.valueOf(z);
        }
    }

    public boolean shouldShowRequestPermissionRationale(@NonNull String str) {
        FragmentHostCallback<?> fragmentHostCallback = this.t;
        if (fragmentHostCallback != null) {
            return fragmentHostCallback.onShouldShowRequestPermissionRationale(str);
        }
        return false;
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent intent) {
        startActivity(intent, null);
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent intent, @Nullable Bundle bundle) {
        FragmentHostCallback<?> fragmentHostCallback = this.t;
        if (fragmentHostCallback != null) {
            fragmentHostCallback.onStartActivityFromFragment(this, intent, -1, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    @Deprecated
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i2) {
        startActivityForResult(intent, i2, null);
    }

    @Deprecated
    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i2, @Nullable Bundle bundle) {
        if (this.t != null) {
            getParentFragmentManager().D0(this, intent, i2, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    @Deprecated
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i2, @Nullable Intent intent, int i3, int i4, int i5, @Nullable Bundle bundle) {
        if (this.t == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in startIntentSenderForResult() requestCode: " + i2 + " IntentSender: " + intentSender + " fillInIntent: " + intent + " options: " + bundle);
        }
        getParentFragmentManager().E0(this, intentSender, i2, intent, i3, i4, i5, bundle);
    }

    public void startPostponedEnterTransition() {
        if (this.K == null || !c().w) {
            return;
        }
        if (this.t == null) {
            c().w = false;
        } else if (Looper.myLooper() != this.t.c().getLooper()) {
            this.t.c().postAtFrontOfQueue(new b());
        } else {
            a(true);
        }
    }

    @NonNull
    public ArrayList<String> t() {
        ArrayList<String> arrayList;
        i iVar = this.K;
        return (iVar == null || (arrayList = iVar.j) == null) ? new ArrayList<>() : arrayList;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("}");
        sb.append(" (");
        sb.append(this.f);
        if (this.w != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.w));
        }
        if (this.y != null) {
            sb.append(" tag=");
            sb.append(this.y);
        }
        sb.append(")");
        return sb.toString();
    }

    public void unregisterForContextMenu(@NonNull View view) {
        view.setOnCreateContextMenuListener(null);
    }

    public void v() {
        u();
        this.f = UUID.randomUUID().toString();
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.r = 0;
        this.s = null;
        this.u = new androidx.fragment.app.f();
        this.t = null;
        this.w = 0;
        this.x = 0;
        this.y = null;
        this.z = false;
        this.A = false;
    }

    public boolean w() {
        i iVar = this.K;
        if (iVar == null) {
            return false;
        }
        return iVar.y;
    }

    public final boolean x() {
        return this.r > 0;
    }

    public boolean y() {
        i iVar = this.K;
        if (iVar == null) {
            return false;
        }
        return iVar.w;
    }

    public final boolean z() {
        Fragment parentFragment = getParentFragment();
        return parentFragment != null && (parentFragment.isRemoving() || parentFragment.z());
    }
}
