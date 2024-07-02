package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.IdRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.collection.ArraySet;
import androidx.core.os.CancellationSignal;
import androidx.fragment.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.c;
import androidx.fragment.app.j;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public abstract class FragmentManager implements FragmentResultOwner {
    private static boolean O = false;
    static boolean P = true;
    public static final int POP_BACK_STACK_INCLUSIVE = 1;
    private ActivityResultLauncher<IntentSenderRequest> A;
    private ActivityResultLauncher<String[]> B;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private ArrayList<androidx.fragment.app.a> I;
    private ArrayList<Boolean> J;
    private ArrayList<Fragment> K;
    private ArrayList<o> L;
    private androidx.fragment.app.g M;
    private boolean b;

    /* renamed from: d */
    ArrayList<androidx.fragment.app.a> f718d;
    private ArrayList<Fragment> e;
    private OnBackPressedDispatcher g;
    private ArrayList<OnBackStackChangedListener> l;
    private FragmentHostCallback<?> r;
    private FragmentContainer s;
    private Fragment t;

    @Nullable
    Fragment u;
    private ActivityResultLauncher<Intent> z;
    private final ArrayList<m> a = new ArrayList<>();

    /* renamed from: c */
    private final androidx.fragment.app.i f717c = new androidx.fragment.app.i();
    private final androidx.fragment.app.d f = new androidx.fragment.app.d(this);
    private final OnBackPressedCallback h = new c(false);

    /* renamed from: i */
    private final AtomicInteger f719i = new AtomicInteger();
    private final Map<String, Bundle> j = Collections.synchronizedMap(new HashMap());
    private final Map<String, l> k = Collections.synchronizedMap(new HashMap());
    private Map<Fragment, HashSet<CancellationSignal>> m = Collections.synchronizedMap(new HashMap());
    private final j.g n = new d();
    private final androidx.fragment.app.e o = new androidx.fragment.app.e(this);
    private final CopyOnWriteArrayList<FragmentOnAttachListener> p = new CopyOnWriteArrayList<>();
    int q = -1;
    private FragmentFactory v = null;
    private FragmentFactory w = new e();
    private androidx.fragment.app.o x = null;
    private androidx.fragment.app.o y = new f(this);
    ArrayDeque<LaunchedFragmentInfo> C = new ArrayDeque<>();
    private Runnable N = new g();

    /* renamed from: androidx.fragment.app.FragmentManager$6 */
    /* loaded from: classes.dex */
    class AnonymousClass6 implements LifecycleEventObserver {
        final /* synthetic */ String a;
        final /* synthetic */ FragmentResultListener b;

        /* renamed from: c */
        final /* synthetic */ Lifecycle f720c;

        AnonymousClass6(String str, FragmentResultListener fragmentResultListener, Lifecycle lifecycle) {
            str = str;
            fragmentResultListener = fragmentResultListener;
            lifecycle = lifecycle;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
            Bundle bundle;
            if (event == Lifecycle.Event.ON_START && (bundle = (Bundle) FragmentManager.this.j.get(str)) != null) {
                fragmentResultListener.onFragmentResult(str, bundle);
                FragmentManager.this.clearFragmentResult(str);
            }
            if (event == Lifecycle.Event.ON_DESTROY) {
                lifecycle.removeObserver(this);
                FragmentManager.this.k.remove(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface BackStackEntry {
        @Nullable
        @Deprecated
        CharSequence getBreadCrumbShortTitle();

        @StringRes
        @Deprecated
        int getBreadCrumbShortTitleRes();

        @Nullable
        @Deprecated
        CharSequence getBreadCrumbTitle();

        @StringRes
        @Deprecated
        int getBreadCrumbTitleRes();

        int getId();

        @Nullable
        String getName();
    }

    /* loaded from: classes.dex */
    public static abstract class FragmentLifecycleCallbacks {
        @Deprecated
        public void onFragmentActivityCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        }

        public void onFragmentAttached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context) {
        }

        public void onFragmentCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        }

        public void onFragmentDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentDetached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentPaused(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentPreAttached(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Context context) {
        }

        public void onFragmentPreCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @Nullable Bundle bundle) {
        }

        public void onFragmentResumed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentSaveInstanceState(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull Bundle bundle) {
        }

        public void onFragmentStarted(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentStopped(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }

        public void onFragmentViewCreated(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle) {
        }

        public void onFragmentViewDestroyed(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    /* loaded from: classes.dex */
    public static class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator<LaunchedFragmentInfo> CREATOR = new a();
        String a;
        int b;

        /* loaded from: classes.dex */
        class a implements Parcelable.Creator<LaunchedFragmentInfo> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public LaunchedFragmentInfo createFromParcel(Parcel parcel) {
                return new LaunchedFragmentInfo(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public LaunchedFragmentInfo[] newArray(int i2) {
                return new LaunchedFragmentInfo[i2];
            }
        }

        LaunchedFragmentInfo(@NonNull Parcel parcel) {
            this.a = parcel.readString();
            this.b = parcel.readInt();
        }

        LaunchedFragmentInfo(@NonNull String str, int i2) {
            this.a = str;
            this.b = i2;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.a);
            parcel.writeInt(this.b);
        }
    }

    /* loaded from: classes.dex */
    public interface OnBackStackChangedListener {
        @MainThread
        void onBackStackChanged();
    }

    /* loaded from: classes.dex */
    public class a implements ActivityResultCallback<ActivityResult> {
        a() {
        }

        @Override // androidx.activity.result.ActivityResultCallback
        /* renamed from: a */
        public void onActivityResult(ActivityResult activityResult) {
            LaunchedFragmentInfo pollFirst = FragmentManager.this.C.pollFirst();
            if (pollFirst == null) {
                Log.w("FragmentManager", "No IntentSenders were started for " + this);
                return;
            }
            String str = pollFirst.a;
            int i2 = pollFirst.b;
            Fragment i3 = FragmentManager.this.f717c.i(str);
            if (i3 != null) {
                i3.onActivityResult(i2, activityResult.getResultCode(), activityResult.getData());
                return;
            }
            Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str);
        }
    }

    /* loaded from: classes.dex */
    public class b implements ActivityResultCallback<Map<String, Boolean>> {
        b() {
        }

        @Override // androidx.activity.result.ActivityResultCallback
        @SuppressLint({"SyntheticAccessor"})
        /* renamed from: a */
        public void onActivityResult(Map<String, Boolean> map) {
            StringBuilder sb;
            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
            ArrayList arrayList = new ArrayList(map.values());
            int[] iArr = new int[arrayList.size()];
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                iArr[i2] = ((Boolean) arrayList.get(i2)).booleanValue() ? 0 : -1;
            }
            LaunchedFragmentInfo pollFirst = FragmentManager.this.C.pollFirst();
            if (pollFirst == null) {
                sb = new StringBuilder();
                sb.append("No permissions were requested for ");
                sb.append(this);
            } else {
                String str = pollFirst.a;
                int i3 = pollFirst.b;
                Fragment i4 = FragmentManager.this.f717c.i(str);
                if (i4 != null) {
                    i4.onRequestPermissionsResult(i3, strArr, iArr);
                    return;
                } else {
                    sb = new StringBuilder();
                    sb.append("Permission request result delivered for unknown Fragment ");
                    sb.append(str);
                }
            }
            Log.w("FragmentManager", sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends OnBackPressedCallback {
        c(boolean z) {
            super(z);
        }

        @Override // androidx.activity.OnBackPressedCallback
        public void handleOnBackPressed() {
            FragmentManager.this.u0();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements j.g {
        d() {
        }

        @Override // androidx.fragment.app.j.g
        public void a(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
            if (cancellationSignal.isCanceled()) {
                return;
            }
            FragmentManager.this.Q0(fragment, cancellationSignal);
        }

        @Override // androidx.fragment.app.j.g
        public void b(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
            FragmentManager.this.f(fragment, cancellationSignal);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends FragmentFactory {
        e() {
        }

        @Override // androidx.fragment.app.FragmentFactory
        @NonNull
        public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String str) {
            return FragmentManager.this.n0().instantiate(FragmentManager.this.n0().b(), str, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f implements androidx.fragment.app.o {
        f(FragmentManager fragmentManager) {
        }

        @Override // androidx.fragment.app.o
        @NonNull
        public androidx.fragment.app.n a(@NonNull ViewGroup viewGroup) {
            return new androidx.fragment.app.b(viewGroup);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FragmentManager.this.X(true);
        }
    }

    /* loaded from: classes.dex */
    public class h extends AnimatorListenerAdapter {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ View b;

        /* renamed from: c */
        final /* synthetic */ Fragment f723c;

        h(FragmentManager fragmentManager, ViewGroup viewGroup, View view, Fragment fragment) {
            this.a = viewGroup;
            this.b = view;
            this.f723c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.endViewTransition(this.b);
            animator.removeListener(this);
            Fragment fragment = this.f723c;
            View view = fragment.H;
            if (view == null || !fragment.z) {
                return;
            }
            view.setVisibility(8);
        }
    }

    /* loaded from: classes.dex */
    public class i implements FragmentOnAttachListener {
        final /* synthetic */ Fragment a;

        i(FragmentManager fragmentManager, Fragment fragment) {
            this.a = fragment;
        }

        @Override // androidx.fragment.app.FragmentOnAttachListener
        public void onAttachFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
            this.a.onAttachFragment(fragment);
        }
    }

    /* loaded from: classes.dex */
    public class j implements ActivityResultCallback<ActivityResult> {
        j() {
        }

        @Override // androidx.activity.result.ActivityResultCallback
        /* renamed from: a */
        public void onActivityResult(ActivityResult activityResult) {
            LaunchedFragmentInfo pollFirst = FragmentManager.this.C.pollFirst();
            if (pollFirst == null) {
                Log.w("FragmentManager", "No Activities were started for result for " + this);
                return;
            }
            String str = pollFirst.a;
            int i2 = pollFirst.b;
            Fragment i3 = FragmentManager.this.f717c.i(str);
            if (i3 != null) {
                i3.onActivityResult(i2, activityResult.getResultCode(), activityResult.getData());
                return;
            }
            Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str);
        }
    }

    /* loaded from: classes.dex */
    public static class k extends ActivityResultContract<IntentSenderRequest, ActivityResult> {
        k() {
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        @NonNull
        public Intent createIntent(@NonNull Context context, IntentSenderRequest intentSenderRequest) {
            Bundle bundleExtra;
            Intent intent = new Intent(ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST);
            Intent fillInIntent = intentSenderRequest.getFillInIntent();
            if (fillInIntent != null && (bundleExtra = fillInIntent.getBundleExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE)) != null) {
                intent.putExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundleExtra);
                fillInIntent.removeExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE);
                if (fillInIntent.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                    intentSenderRequest = new IntentSenderRequest.Builder(intentSenderRequest.getIntentSender()).setFillInIntent(null).setFlags(intentSenderRequest.getFlagsValues(), intentSenderRequest.getFlagsMask()).build();
                }
            }
            intent.putExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_INTENT_SENDER_REQUEST, intentSenderRequest);
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "CreateIntent created the following intent: " + intent);
            }
            return intent;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        @NonNull
        public ActivityResult parseResult(int i2, @Nullable Intent intent) {
            return new ActivityResult(i2, intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class l implements FragmentResultListener {
        private final Lifecycle a;
        private final FragmentResultListener b;

        /* renamed from: c */
        private final LifecycleEventObserver f724c;

        l(@NonNull Lifecycle lifecycle, @NonNull FragmentResultListener fragmentResultListener, @NonNull LifecycleEventObserver lifecycleEventObserver) {
            this.a = lifecycle;
            this.b = fragmentResultListener;
            this.f724c = lifecycleEventObserver;
        }

        public boolean a(Lifecycle.State state) {
            return this.a.getCurrentState().isAtLeast(state);
        }

        public void b() {
            this.a.removeObserver(this.f724c);
        }

        @Override // androidx.fragment.app.FragmentResultListener
        public void onFragmentResult(@NonNull String str, @NonNull Bundle bundle) {
            this.b.onFragmentResult(str, bundle);
        }
    }

    /* loaded from: classes.dex */
    public interface m {
        boolean a(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class n implements m {
        final String a;
        final int b;

        /* renamed from: c */
        final int f725c;

        n(@Nullable String str, int i2, int i3) {
            this.a = str;
            this.b = i2;
            this.f725c = i3;
        }

        @Override // androidx.fragment.app.FragmentManager.m
        public boolean a(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
            Fragment fragment = FragmentManager.this.u;
            if (fragment == null || this.b >= 0 || this.a != null || !fragment.getChildFragmentManager().popBackStackImmediate()) {
                return FragmentManager.this.O0(arrayList, arrayList2, this.a, this.b, this.f725c);
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class o implements Fragment.k {
        final boolean a;
        final androidx.fragment.app.a b;

        /* renamed from: c */
        private int f727c;

        o(@NonNull androidx.fragment.app.a aVar, boolean z) {
            this.a = z;
            this.b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.k
        public void a() {
            int i2 = this.f727c - 1;
            this.f727c = i2;
            if (i2 != 0) {
                return;
            }
            this.b.t.a1();
        }

        @Override // androidx.fragment.app.Fragment.k
        public void b() {
            this.f727c++;
        }

        void c() {
            androidx.fragment.app.a aVar = this.b;
            aVar.t.r(aVar, this.a, false, false);
        }

        void d() {
            boolean z = this.f727c > 0;
            for (Fragment fragment : this.b.t.getFragments()) {
                fragment.k0(null);
                if (z && fragment.y()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            androidx.fragment.app.a aVar = this.b;
            aVar.t.r(aVar, this.a, !z, true);
        }

        public boolean e() {
            return this.f727c == 0;
        }
    }

    private void F0(@NonNull ArraySet<Fragment> arraySet) {
        int size = arraySet.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment valueAt = arraySet.valueAt(i2);
            if (!valueAt.l) {
                View requireView = valueAt.requireView();
                valueAt.O = requireView.getAlpha();
                requireView.setAlpha(0.0f);
            }
        }
    }

    private void J(@Nullable Fragment fragment) {
        if (fragment == null || !fragment.equals(c0(fragment.f))) {
            return;
        }
        fragment.T();
    }

    private boolean N0(@Nullable String str, int i2, int i3) {
        X(false);
        W(true);
        Fragment fragment = this.u;
        if (fragment != null && i2 < 0 && str == null && fragment.getChildFragmentManager().popBackStackImmediate()) {
            return true;
        }
        boolean O0 = O0(this.I, this.J, str, i2, i3);
        if (O0) {
            this.b = true;
            try {
                S0(this.I, this.J);
            } finally {
                o();
            }
        }
        i1();
        T();
        this.f717c.b();
        return O0;
    }

    private int P0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i2, int i3, @NonNull ArraySet<Fragment> arraySet) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            androidx.fragment.app.a aVar = arrayList.get(i5);
            boolean booleanValue = arrayList2.get(i5).booleanValue();
            if (aVar.p() && !aVar.n(arrayList, i5 + 1, i3)) {
                if (this.L == null) {
                    this.L = new ArrayList<>();
                }
                o oVar = new o(aVar, booleanValue);
                this.L.add(oVar);
                aVar.r(oVar);
                if (booleanValue) {
                    aVar.j();
                } else {
                    aVar.k(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, aVar);
                }
                d(arraySet);
            }
        }
        return i4;
    }

    private void Q(int i2) {
        try {
            this.b = true;
            this.f717c.d(i2);
            H0(i2, false);
            if (P) {
                Iterator<androidx.fragment.app.n> it = p().iterator();
                while (it.hasNext()) {
                    it.next().j();
                }
            }
            this.b = false;
            X(true);
        } catch (Throwable th) {
            this.b = false;
            throw th;
        }
    }

    private void S0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        b0(arrayList, arrayList2);
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            if (!arrayList.get(i2).r) {
                if (i3 != i2) {
                    a0(arrayList, arrayList2, i3, i2);
                }
                i3 = i2 + 1;
                if (arrayList2.get(i2).booleanValue()) {
                    while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).r) {
                        i3++;
                    }
                }
                a0(arrayList, arrayList2, i2, i3);
                i2 = i3 - 1;
            }
            i2++;
        }
        if (i3 != size) {
            a0(arrayList, arrayList2, i3, size);
        }
    }

    private void T() {
        if (this.H) {
            this.H = false;
            g1();
        }
    }

    private void U() {
        if (P) {
            Iterator<androidx.fragment.app.n> it = p().iterator();
            while (it.hasNext()) {
                it.next().j();
            }
        } else {
            if (this.m.isEmpty()) {
                return;
            }
            for (Fragment fragment : this.m.keySet()) {
                l(fragment);
                I0(fragment);
            }
        }
    }

    private void U0() {
        if (this.l != null) {
            for (int i2 = 0; i2 < this.l.size(); i2++) {
                this.l.get(i2).onBackStackChanged();
            }
        }
    }

    private void W(boolean z) {
        if (this.b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.r == null) {
            if (!this.G) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.r.c().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z) {
            n();
        }
        if (this.I == null) {
            this.I = new ArrayList<>();
            this.J = new ArrayList<>();
        }
        this.b = true;
        try {
            b0(null, null);
        } finally {
            this.b = false;
        }
    }

    public static int Y0(int i2) {
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 != 4099) {
            return i2 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    private static void Z(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            androidx.fragment.app.a aVar = arrayList.get(i2);
            if (arrayList2.get(i2).booleanValue()) {
                aVar.f(-1);
                aVar.k(i2 == i3 + (-1));
            } else {
                aVar.f(1);
                aVar.j();
            }
            i2++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0143  */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a0(@androidx.annotation.NonNull java.util.ArrayList<androidx.fragment.app.a> r18, @androidx.annotation.NonNull java.util.ArrayList<java.lang.Boolean> r19, int r20, int r21) {
        /*
            Method dump skipped, instructions count: 450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.a0(java.util.ArrayList, java.util.ArrayList, int, int):void");
    }

    private void b0(@Nullable ArrayList<androidx.fragment.app.a> arrayList, @Nullable ArrayList<Boolean> arrayList2) {
        int indexOf;
        int indexOf2;
        ArrayList<o> arrayList3 = this.L;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            o oVar = this.L.get(i2);
            if (arrayList == null || oVar.a || (indexOf2 = arrayList.indexOf(oVar.b)) == -1 || arrayList2 == null || !arrayList2.get(indexOf2).booleanValue()) {
                if (oVar.e() || (arrayList != null && oVar.b.n(arrayList, 0, arrayList.size()))) {
                    this.L.remove(i2);
                    i2--;
                    size--;
                    if (arrayList == null || oVar.a || (indexOf = arrayList.indexOf(oVar.b)) == -1 || arrayList2 == null || !arrayList2.get(indexOf).booleanValue()) {
                        oVar.d();
                    }
                }
                i2++;
            } else {
                this.L.remove(i2);
                i2--;
                size--;
            }
            oVar.c();
            i2++;
        }
    }

    private void d(@NonNull ArraySet<Fragment> arraySet) {
        int i2 = this.q;
        if (i2 < 1) {
            return;
        }
        int min = Math.min(i2, 5);
        for (Fragment fragment : this.f717c.o()) {
            if (fragment.a < min) {
                J0(fragment, min);
                if (fragment.H != null && !fragment.z && fragment.M) {
                    arraySet.add(fragment);
                }
            }
        }
    }

    @Nullable
    private static Fragment e0(@NonNull View view) {
        while (view != null) {
            Fragment s0 = s0(view);
            if (s0 != null) {
                return s0;
            }
            Object parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return null;
    }

    private void e1(@NonNull Fragment fragment) {
        ViewGroup l0 = l0(fragment);
        if (l0 == null || fragment.h() + fragment.j() + fragment.p() + fragment.q() <= 0) {
            return;
        }
        int i2 = R.id.visible_removing_fragment_view_tag;
        if (l0.getTag(i2) == null) {
            l0.setTag(i2, fragment);
        }
        ((Fragment) l0.getTag(i2)).l0(fragment.o());
    }

    @Deprecated
    public static void enableDebugLogging(boolean z) {
        O = z;
    }

    @FragmentStateManagerControl
    public static void enableNewStateManager(boolean z) {
        P = z;
    }

    private void f0() {
        if (P) {
            Iterator<androidx.fragment.app.n> it = p().iterator();
            while (it.hasNext()) {
                it.next().k();
            }
        } else if (this.L != null) {
            while (!this.L.isEmpty()) {
                this.L.remove(0).d();
            }
        }
    }

    @NonNull
    public static <F extends Fragment> F findFragment(@NonNull View view) {
        F f2 = (F) e0(view);
        if (f2 != null) {
            return f2;
        }
        throw new IllegalStateException("View " + view + " does not have a Fragment set");
    }

    private boolean g0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        synchronized (this.a) {
            if (this.a.isEmpty()) {
                return false;
            }
            int size = this.a.size();
            boolean z = false;
            for (int i2 = 0; i2 < size; i2++) {
                z |= this.a.get(i2).a(arrayList, arrayList2);
            }
            this.a.clear();
            this.r.c().removeCallbacks(this.N);
            return z;
        }
    }

    private void g1() {
        Iterator<androidx.fragment.app.h> it = this.f717c.l().iterator();
        while (it.hasNext()) {
            M0(it.next());
        }
    }

    private void h1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new androidx.fragment.app.m("FragmentManager"));
        FragmentHostCallback<?> fragmentHostCallback = this.r;
        try {
            if (fragmentHostCallback != null) {
                fragmentHostCallback.onDump("  ", null, printWriter, new String[0]);
            } else {
                dump("  ", null, printWriter, new String[0]);
            }
            throw runtimeException;
        } catch (Exception e2) {
            Log.e("FragmentManager", "Failed dumping state", e2);
            throw runtimeException;
        }
    }

    private void i1() {
        synchronized (this.a) {
            if (this.a.isEmpty()) {
                this.h.setEnabled(getBackStackEntryCount() > 0 && A0(this.t));
            } else {
                this.h.setEnabled(true);
            }
        }
    }

    @NonNull
    private androidx.fragment.app.g j0(@NonNull Fragment fragment) {
        return this.M.i(fragment);
    }

    private void l(@NonNull Fragment fragment) {
        HashSet<CancellationSignal> hashSet = this.m.get(fragment);
        if (hashSet != null) {
            Iterator<CancellationSignal> it = hashSet.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            hashSet.clear();
            u(fragment);
            this.m.remove(fragment);
        }
    }

    private ViewGroup l0(@NonNull Fragment fragment) {
        ViewGroup viewGroup = fragment.G;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (fragment.x > 0 && this.s.onHasView()) {
            View onFindViewById = this.s.onFindViewById(fragment.x);
            if (onFindViewById instanceof ViewGroup) {
                return (ViewGroup) onFindViewById;
            }
        }
        return null;
    }

    private void n() {
        if (isStateSaved()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private void o() {
        this.b = false;
        this.J.clear();
        this.I.clear();
    }

    private Set<androidx.fragment.app.n> p() {
        HashSet hashSet = new HashSet();
        Iterator<androidx.fragment.app.h> it = this.f717c.l().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = it.next().k().G;
            if (viewGroup != null) {
                hashSet.add(androidx.fragment.app.n.o(viewGroup, r0()));
            }
        }
        return hashSet;
    }

    private Set<androidx.fragment.app.n> q(@NonNull ArrayList<androidx.fragment.app.a> arrayList, int i2, int i3) {
        ViewGroup viewGroup;
        HashSet hashSet = new HashSet();
        while (i2 < i3) {
            Iterator<FragmentTransaction.a> it = arrayList.get(i2).f743c.iterator();
            while (it.hasNext()) {
                Fragment fragment = it.next().b;
                if (fragment != null && (viewGroup = fragment.G) != null) {
                    hashSet.add(androidx.fragment.app.n.n(viewGroup, this));
                }
            }
            i2++;
        }
        return hashSet;
    }

    private void s(@NonNull Fragment fragment) {
        Animator animator;
        if (fragment.H != null) {
            c.d c2 = androidx.fragment.app.c.c(this.r.b(), fragment, !fragment.z, fragment.o());
            if (c2 == null || (animator = c2.b) == null) {
                if (c2 != null) {
                    fragment.H.startAnimation(c2.a);
                    c2.a.start();
                }
                fragment.H.setVisibility((!fragment.z || fragment.w()) ? 0 : 8);
                if (fragment.w()) {
                    fragment.i0(false);
                }
            } else {
                animator.setTarget(fragment.H);
                if (!fragment.z) {
                    fragment.H.setVisibility(0);
                } else if (fragment.w()) {
                    fragment.i0(false);
                } else {
                    ViewGroup viewGroup = fragment.G;
                    View view = fragment.H;
                    viewGroup.startViewTransition(view);
                    c2.b.addListener(new h(this, viewGroup, view, fragment));
                }
                c2.b.start();
            }
        }
        w0(fragment);
        fragment.N = false;
        fragment.onHiddenChanged(fragment.z);
    }

    @Nullable
    public static Fragment s0(@NonNull View view) {
        Object tag = view.getTag(R.id.fragment_container_view_tag);
        if (tag instanceof Fragment) {
            return (Fragment) tag;
        }
        return null;
    }

    private void u(@NonNull Fragment fragment) {
        fragment.J();
        this.o.n(fragment, false);
        fragment.G = null;
        fragment.H = null;
        fragment.T = null;
        fragment.U.setValue(null);
        fragment.o = false;
    }

    public static boolean x0(int i2) {
        return O || Log.isLoggable("FragmentManager", i2);
    }

    private boolean y0(@NonNull Fragment fragment) {
        return (fragment.D && fragment.E) || fragment.u.m();
    }

    public void A() {
        this.E = false;
        this.F = false;
        this.M.q(false);
        Q(1);
    }

    public boolean A0(@Nullable Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        FragmentManager fragmentManager = fragment.s;
        return fragment.equals(fragmentManager.getPrimaryNavigationFragment()) && A0(fragmentManager.t);
    }

    public boolean B(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        if (this.q < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (Fragment fragment : this.f717c.o()) {
            if (fragment != null && z0(fragment) && fragment.G(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.e != null) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                Fragment fragment2 = this.e.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.e = arrayList;
        return z;
    }

    public boolean B0(int i2) {
        return this.q >= i2;
    }

    public void C() {
        this.G = true;
        X(true);
        U();
        Q(-1);
        this.r = null;
        this.s = null;
        this.t = null;
        if (this.g != null) {
            this.h.remove();
            this.g = null;
        }
        ActivityResultLauncher<Intent> activityResultLauncher = this.z;
        if (activityResultLauncher != null) {
            activityResultLauncher.unregister();
            this.A.unregister();
            this.B.unregister();
        }
    }

    public void C0(@NonNull Fragment fragment, @NonNull String[] strArr, int i2) {
        if (this.B == null) {
            this.r.onRequestPermissionsFromFragment(fragment, strArr, i2);
            return;
        }
        this.C.addLast(new LaunchedFragmentInfo(fragment.f, i2));
        this.B.launch(strArr);
    }

    public void D() {
        Q(1);
    }

    public void D0(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i2, @Nullable Bundle bundle) {
        if (this.z == null) {
            this.r.onStartActivityFromFragment(fragment, intent, i2, bundle);
            return;
        }
        this.C.addLast(new LaunchedFragmentInfo(fragment.f, i2));
        if (intent != null && bundle != null) {
            intent.putExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundle);
        }
        this.z.launch(intent);
    }

    public void E() {
        for (Fragment fragment : this.f717c.o()) {
            if (fragment != null) {
                fragment.M();
            }
        }
    }

    public void E0(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i2, @Nullable Intent intent, int i3, int i4, int i5, @Nullable Bundle bundle) {
        Intent intent2;
        if (this.A == null) {
            this.r.onStartIntentSenderFromFragment(fragment, intentSender, i2, intent, i3, i4, i5, bundle);
            return;
        }
        if (bundle != null) {
            if (intent == null) {
                intent2 = new Intent();
                intent2.putExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", true);
            } else {
                intent2 = intent;
            }
            if (x0(2)) {
                Log.v("FragmentManager", "ActivityOptions " + bundle + " were added to fillInIntent " + intent2 + " for fragment " + fragment);
            }
            intent2.putExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE, bundle);
        } else {
            intent2 = intent;
        }
        IntentSenderRequest build = new IntentSenderRequest.Builder(intentSender).setFillInIntent(intent2).setFlags(i4, i3).build();
        this.C.addLast(new LaunchedFragmentInfo(fragment.f, i2));
        if (x0(2)) {
            Log.v("FragmentManager", "Fragment " + fragment + "is launching an IntentSender for result ");
        }
        this.A.launch(build);
    }

    public void F(boolean z) {
        for (Fragment fragment : this.f717c.o()) {
            if (fragment != null) {
                fragment.N(z);
            }
        }
    }

    public void G(@NonNull Fragment fragment) {
        Iterator<FragmentOnAttachListener> it = this.p.iterator();
        while (it.hasNext()) {
            it.next().onAttachFragment(this, fragment);
        }
    }

    public void G0(@NonNull Fragment fragment) {
        if (!this.f717c.c(fragment.f)) {
            if (x0(3)) {
                Log.d("FragmentManager", "Ignoring moving " + fragment + " to state " + this.q + "since it is not added to " + this);
                return;
            }
            return;
        }
        I0(fragment);
        View view = fragment.H;
        if (view != null && fragment.M && fragment.G != null) {
            float f2 = fragment.O;
            if (f2 > 0.0f) {
                view.setAlpha(f2);
            }
            fragment.O = 0.0f;
            fragment.M = false;
            c.d c2 = androidx.fragment.app.c.c(this.r.b(), fragment, true, fragment.o());
            if (c2 != null) {
                Animation animation = c2.a;
                if (animation != null) {
                    fragment.H.startAnimation(animation);
                } else {
                    c2.b.setTarget(fragment.H);
                    c2.b.start();
                }
            }
        }
        if (fragment.N) {
            s(fragment);
        }
    }

    public boolean H(@NonNull MenuItem menuItem) {
        if (this.q < 1) {
            return false;
        }
        for (Fragment fragment : this.f717c.o()) {
            if (fragment != null && fragment.O(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void H0(int i2, boolean z) {
        FragmentHostCallback<?> fragmentHostCallback;
        if (this.r == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.q) {
            this.q = i2;
            if (P) {
                this.f717c.s();
            } else {
                Iterator<Fragment> it = this.f717c.o().iterator();
                while (it.hasNext()) {
                    G0(it.next());
                }
                for (androidx.fragment.app.h hVar : this.f717c.l()) {
                    Fragment k2 = hVar.k();
                    if (!k2.M) {
                        G0(k2);
                    }
                    if (k2.m && !k2.x()) {
                        this.f717c.r(hVar);
                    }
                }
            }
            g1();
            if (this.D && (fragmentHostCallback = this.r) != null && this.q == 7) {
                fragmentHostCallback.onSupportInvalidateOptionsMenu();
                this.D = false;
            }
        }
    }

    public void I(@NonNull Menu menu) {
        if (this.q < 1) {
            return;
        }
        for (Fragment fragment : this.f717c.o()) {
            if (fragment != null) {
                fragment.P(menu);
            }
        }
    }

    public void I0(@NonNull Fragment fragment) {
        J0(fragment, this.q);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0053, code lost:
    
        if (r2 != 5) goto L210;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void J0(@androidx.annotation.NonNull androidx.fragment.app.Fragment r11, int r12) {
        /*
            Method dump skipped, instructions count: 407
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.J0(androidx.fragment.app.Fragment, int):void");
    }

    public void K() {
        Q(5);
    }

    public void K0() {
        if (this.r == null) {
            return;
        }
        this.E = false;
        this.F = false;
        this.M.q(false);
        for (Fragment fragment : this.f717c.o()) {
            if (fragment != null) {
                fragment.A();
            }
        }
    }

    public void L(boolean z) {
        for (Fragment fragment : this.f717c.o()) {
            if (fragment != null) {
                fragment.R(z);
            }
        }
    }

    public void L0(@NonNull FragmentContainerView fragmentContainerView) {
        View view;
        for (androidx.fragment.app.h hVar : this.f717c.l()) {
            Fragment k2 = hVar.k();
            if (k2.x == fragmentContainerView.getId() && (view = k2.H) != null && view.getParent() == null) {
                k2.G = fragmentContainerView;
                hVar.b();
            }
        }
    }

    public boolean M(@NonNull Menu menu) {
        boolean z = false;
        if (this.q < 1) {
            return false;
        }
        for (Fragment fragment : this.f717c.o()) {
            if (fragment != null && z0(fragment) && fragment.S(menu)) {
                z = true;
            }
        }
        return z;
    }

    public void M0(@NonNull androidx.fragment.app.h hVar) {
        Fragment k2 = hVar.k();
        if (k2.I) {
            if (this.b) {
                this.H = true;
                return;
            }
            k2.I = false;
            if (P) {
                hVar.m();
            } else {
                I0(k2);
            }
        }
    }

    public void N() {
        i1();
        J(this.u);
    }

    public void O() {
        this.E = false;
        this.F = false;
        this.M.q(false);
        Q(7);
    }

    boolean O0(@NonNull ArrayList<androidx.fragment.app.a> arrayList, @NonNull ArrayList<Boolean> arrayList2, @Nullable String str, int i2, int i3) {
        int i4;
        Boolean bool = Boolean.TRUE;
        ArrayList<androidx.fragment.app.a> arrayList3 = this.f718d;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.f718d.remove(size));
            arrayList2.add(bool);
        } else {
            if (str != null || i2 >= 0) {
                int size2 = arrayList3.size() - 1;
                while (size2 >= 0) {
                    androidx.fragment.app.a aVar = this.f718d.get(size2);
                    if ((str != null && str.equals(aVar.getName())) || (i2 >= 0 && i2 == aVar.v)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        androidx.fragment.app.a aVar2 = this.f718d.get(size2);
                        if (str == null || !str.equals(aVar2.getName())) {
                            if (i2 < 0 || i2 != aVar2.v) {
                                break;
                            }
                        }
                    }
                }
                i4 = size2;
            } else {
                i4 = -1;
            }
            if (i4 == this.f718d.size() - 1) {
                return false;
            }
            for (int size3 = this.f718d.size() - 1; size3 > i4; size3--) {
                arrayList.add(this.f718d.remove(size3));
                arrayList2.add(bool);
            }
        }
        return true;
    }

    public void P() {
        this.E = false;
        this.F = false;
        this.M.q(false);
        Q(5);
    }

    void Q0(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
        HashSet<CancellationSignal> hashSet = this.m.get(fragment);
        if (hashSet != null && hashSet.remove(cancellationSignal) && hashSet.isEmpty()) {
            this.m.remove(fragment);
            if (fragment.a < 5) {
                u(fragment);
                I0(fragment);
            }
        }
    }

    public void R() {
        this.F = true;
        this.M.q(true);
        Q(4);
    }

    public void R0(@NonNull Fragment fragment) {
        if (x0(2)) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.r);
        }
        boolean z = !fragment.x();
        if (!fragment.A || z) {
            this.f717c.t(fragment);
            if (y0(fragment)) {
                this.D = true;
            }
            fragment.m = true;
            e1(fragment);
        }
    }

    public void S() {
        Q(2);
    }

    public void T0(@NonNull Fragment fragment) {
        this.M.o(fragment);
    }

    public void V(@NonNull m mVar, boolean z) {
        if (!z) {
            if (this.r == null) {
                if (!this.G) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            n();
        }
        synchronized (this.a) {
            if (this.r == null) {
                if (!z) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
            } else {
                this.a.add(mVar);
                a1();
            }
        }
    }

    public void V0(@Nullable Parcelable parcelable, @Nullable FragmentManagerNonConfig fragmentManagerNonConfig) {
        if (this.r instanceof ViewModelStoreOwner) {
            h1(new IllegalStateException("You must use restoreSaveState when your FragmentHostCallback implements ViewModelStoreOwner"));
            throw null;
        }
        this.M.p(fragmentManagerNonConfig);
        W0(parcelable);
    }

    public void W0(@Nullable Parcelable parcelable) {
        androidx.fragment.app.h hVar;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.a == null) {
            return;
        }
        this.f717c.u();
        Iterator<FragmentState> it = fragmentManagerState.a.iterator();
        while (it.hasNext()) {
            FragmentState next = it.next();
            if (next != null) {
                Fragment h2 = this.M.h(next.b);
                if (h2 != null) {
                    if (x0(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + h2);
                    }
                    hVar = new androidx.fragment.app.h(this.o, this.f717c, h2, next);
                } else {
                    hVar = new androidx.fragment.app.h(this.o, this.f717c, this.r.b().getClassLoader(), getFragmentFactory(), next);
                }
                Fragment k2 = hVar.k();
                k2.s = this;
                if (x0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + k2.f + "): " + k2);
                }
                hVar.o(this.r.b().getClassLoader());
                this.f717c.q(hVar);
                hVar.u(this.q);
            }
        }
        for (Fragment fragment : this.M.k()) {
            if (!this.f717c.c(fragment.f)) {
                if (x0(2)) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.a);
                }
                this.M.o(fragment);
                fragment.s = this;
                androidx.fragment.app.h hVar2 = new androidx.fragment.app.h(this.o, this.f717c, fragment);
                hVar2.u(1);
                hVar2.m();
                fragment.m = true;
                hVar2.m();
            }
        }
        this.f717c.v(fragmentManagerState.b);
        if (fragmentManagerState.f729c != null) {
            this.f718d = new ArrayList<>(fragmentManagerState.f729c.length);
            int i2 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.f729c;
                if (i2 >= backStackStateArr.length) {
                    break;
                }
                androidx.fragment.app.a a2 = backStackStateArr[i2].a(this);
                if (x0(2)) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + a2.v + "): " + a2);
                    PrintWriter printWriter = new PrintWriter(new androidx.fragment.app.m("FragmentManager"));
                    a2.i("  ", printWriter, false);
                    printWriter.close();
                }
                this.f718d.add(a2);
                i2++;
            }
        } else {
            this.f718d = null;
        }
        this.f719i.set(fragmentManagerState.f730d);
        String str = fragmentManagerState.e;
        if (str != null) {
            Fragment c0 = c0(str);
            this.u = c0;
            J(c0);
        }
        ArrayList<String> arrayList = fragmentManagerState.f;
        if (arrayList != null) {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                Bundle bundle = fragmentManagerState.g.get(i3);
                bundle.setClassLoader(this.r.b().getClassLoader());
                this.j.put(arrayList.get(i3), bundle);
            }
        }
        this.C = new ArrayDeque<>(fragmentManagerState.h);
    }

    public boolean X(boolean z) {
        W(z);
        boolean z2 = false;
        while (g0(this.I, this.J)) {
            this.b = true;
            try {
                S0(this.I, this.J);
                o();
                z2 = true;
            } catch (Throwable th) {
                o();
                throw th;
            }
        }
        i1();
        T();
        this.f717c.b();
        return z2;
    }

    @Deprecated
    public FragmentManagerNonConfig X0() {
        if (!(this.r instanceof ViewModelStoreOwner)) {
            return this.M.l();
        }
        h1(new IllegalStateException("You cannot use retainNonConfig when your FragmentHostCallback implements ViewModelStoreOwner."));
        throw null;
    }

    public void Y(@NonNull m mVar, boolean z) {
        if (z && (this.r == null || this.G)) {
            return;
        }
        W(z);
        if (mVar.a(this.I, this.J)) {
            this.b = true;
            try {
                S0(this.I, this.J);
            } finally {
                o();
            }
        }
        i1();
        T();
        this.f717c.b();
    }

    public Parcelable Z0() {
        int size;
        f0();
        U();
        X(true);
        this.E = true;
        this.M.q(true);
        ArrayList<FragmentState> w = this.f717c.w();
        BackStackState[] backStackStateArr = null;
        if (w.isEmpty()) {
            if (x0(2)) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        ArrayList<String> x = this.f717c.x();
        ArrayList<androidx.fragment.app.a> arrayList = this.f718d;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i2 = 0; i2 < size; i2++) {
                backStackStateArr[i2] = new BackStackState(this.f718d.get(i2));
                if (x0(2)) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.f718d.get(i2));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.a = w;
        fragmentManagerState.b = x;
        fragmentManagerState.f729c = backStackStateArr;
        fragmentManagerState.f730d = this.f719i.get();
        Fragment fragment = this.u;
        if (fragment != null) {
            fragmentManagerState.e = fragment.f;
        }
        fragmentManagerState.f.addAll(this.j.keySet());
        fragmentManagerState.g.addAll(this.j.values());
        fragmentManagerState.h = new ArrayList<>(this.C);
        return fragmentManagerState;
    }

    void a1() {
        synchronized (this.a) {
            ArrayList<o> arrayList = this.L;
            boolean z = (arrayList == null || arrayList.isEmpty()) ? false : true;
            boolean z2 = this.a.size() == 1;
            if (z || z2) {
                this.r.c().removeCallbacks(this.N);
                this.r.c().post(this.N);
                i1();
            }
        }
    }

    public void addFragmentOnAttachListener(@NonNull FragmentOnAttachListener fragmentOnAttachListener) {
        this.p.add(fragmentOnAttachListener);
    }

    public void addOnBackStackChangedListener(@NonNull OnBackStackChangedListener onBackStackChangedListener) {
        if (this.l == null) {
            this.l = new ArrayList<>();
        }
        this.l.add(onBackStackChangedListener);
    }

    public void b1(@NonNull Fragment fragment, boolean z) {
        ViewGroup l0 = l0(fragment);
        if (l0 == null || !(l0 instanceof FragmentContainerView)) {
            return;
        }
        ((FragmentContainerView) l0).setDrawDisappearingViewsLast(!z);
    }

    @NonNull
    public FragmentTransaction beginTransaction() {
        return new androidx.fragment.app.a(this);
    }

    @Nullable
    public Fragment c0(@NonNull String str) {
        return this.f717c.f(str);
    }

    public void c1(@NonNull Fragment fragment, @NonNull Lifecycle.State state) {
        if (fragment.equals(c0(fragment.f)) && (fragment.t == null || fragment.s == this)) {
            fragment.R = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    public final void clearFragmentResult(@NonNull String str) {
        this.j.remove(str);
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    public final void clearFragmentResultListener(@NonNull String str) {
        l remove = this.k.remove(str);
        if (remove != null) {
            remove.b();
        }
    }

    public Fragment d0(@NonNull String str) {
        return this.f717c.i(str);
    }

    public void d1(@Nullable Fragment fragment) {
        if (fragment == null || (fragment.equals(c0(fragment.f)) && (fragment.t == null || fragment.s == this))) {
            Fragment fragment2 = this.u;
            this.u = fragment;
            J(fragment2);
            J(this.u);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void dump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        int size;
        int size2;
        String str2 = str + "    ";
        this.f717c.e(str, fileDescriptor, printWriter, strArr);
        ArrayList<Fragment> arrayList = this.e;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                Fragment fragment = this.e.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
        ArrayList<androidx.fragment.app.a> arrayList2 = this.f718d;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                androidx.fragment.app.a aVar = this.f718d.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.h(str2, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.f719i.get());
        synchronized (this.a) {
            int size3 = this.a.size();
            if (size3 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i4 = 0; i4 < size3; i4++) {
                    m mVar = this.a.get(i4);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i4);
                    printWriter.print(": ");
                    printWriter.println(mVar);
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.r);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.s);
        if (this.t != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.t);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.q);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.E);
        printWriter.print(" mStopped=");
        printWriter.print(this.F);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.G);
        if (this.D) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.D);
        }
    }

    public void e(androidx.fragment.app.a aVar) {
        if (this.f718d == null) {
            this.f718d = new ArrayList<>();
        }
        this.f718d.add(aVar);
    }

    public boolean executePendingTransactions() {
        boolean X = X(true);
        f0();
        return X;
    }

    void f(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
        if (this.m.get(fragment) == null) {
            this.m.put(fragment, new HashSet<>());
        }
        this.m.get(fragment).add(cancellationSignal);
    }

    public void f1(@NonNull Fragment fragment) {
        if (x0(2)) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.z) {
            fragment.z = false;
            fragment.N = !fragment.N;
        }
    }

    @Nullable
    public Fragment findFragmentById(@IdRes int i2) {
        return this.f717c.g(i2);
    }

    @Nullable
    public Fragment findFragmentByTag(@Nullable String str) {
        return this.f717c.h(str);
    }

    public androidx.fragment.app.h g(@NonNull Fragment fragment) {
        if (x0(2)) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        androidx.fragment.app.h t = t(fragment);
        fragment.s = this;
        this.f717c.q(t);
        if (!fragment.A) {
            this.f717c.a(fragment);
            fragment.m = false;
            if (fragment.H == null) {
                fragment.N = false;
            }
            if (y0(fragment)) {
                this.D = true;
            }
        }
        return t;
    }

    @NonNull
    public BackStackEntry getBackStackEntryAt(int i2) {
        return this.f718d.get(i2);
    }

    public int getBackStackEntryCount() {
        ArrayList<androidx.fragment.app.a> arrayList = this.f718d;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Nullable
    public Fragment getFragment(@NonNull Bundle bundle, @NonNull String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment c0 = c0(string);
        if (c0 != null) {
            return c0;
        }
        h1(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        throw null;
    }

    @NonNull
    public FragmentFactory getFragmentFactory() {
        FragmentFactory fragmentFactory = this.v;
        if (fragmentFactory != null) {
            return fragmentFactory;
        }
        Fragment fragment = this.t;
        return fragment != null ? fragment.s.getFragmentFactory() : this.w;
    }

    @NonNull
    public List<Fragment> getFragments() {
        return this.f717c.o();
    }

    @Nullable
    public Fragment getPrimaryNavigationFragment() {
        return this.u;
    }

    public void h(@NonNull Fragment fragment) {
        this.M.f(fragment);
    }

    public int h0() {
        return this.f717c.k();
    }

    public int i() {
        return this.f719i.getAndIncrement();
    }

    @NonNull
    public List<Fragment> i0() {
        return this.f717c.m();
    }

    public boolean isDestroyed() {
        return this.G;
    }

    public boolean isStateSaved() {
        return this.E || this.F;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    @android.annotation.SuppressLint({"SyntheticAccessor"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void j(@androidx.annotation.NonNull androidx.fragment.app.FragmentHostCallback<?> r3, @androidx.annotation.NonNull androidx.fragment.app.FragmentContainer r4, @androidx.annotation.Nullable androidx.fragment.app.Fragment r5) {
        /*
            Method dump skipped, instructions count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.j(androidx.fragment.app.FragmentHostCallback, androidx.fragment.app.FragmentContainer, androidx.fragment.app.Fragment):void");
    }

    public void k(@NonNull Fragment fragment) {
        if (x0(2)) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.A) {
            fragment.A = false;
            if (fragment.l) {
                return;
            }
            this.f717c.a(fragment);
            if (x0(2)) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            if (y0(fragment)) {
                this.D = true;
            }
        }
    }

    @NonNull
    public FragmentContainer k0() {
        return this.s;
    }

    boolean m() {
        boolean z = false;
        for (Fragment fragment : this.f717c.m()) {
            if (fragment != null) {
                z = y0(fragment);
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public androidx.fragment.app.i m0() {
        return this.f717c;
    }

    @NonNull
    public FragmentHostCallback<?> n0() {
        return this.r;
    }

    @NonNull
    public LayoutInflater.Factory2 o0() {
        return this.f;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public FragmentTransaction openTransaction() {
        return beginTransaction();
    }

    @NonNull
    public androidx.fragment.app.e p0() {
        return this.o;
    }

    public void popBackStack() {
        V(new n(null, -1, 0), false);
    }

    public void popBackStack(int i2, int i3) {
        if (i2 >= 0) {
            V(new n(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    public void popBackStack(@Nullable String str, int i2) {
        V(new n(str, -1, i2), false);
    }

    public boolean popBackStackImmediate() {
        return N0(null, -1, 0);
    }

    public boolean popBackStackImmediate(int i2, int i3) {
        if (i2 >= 0) {
            return N0(null, i2, i3);
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    public boolean popBackStackImmediate(@Nullable String str, int i2) {
        return N0(str, -1, i2);
    }

    public void putFragment(@NonNull Bundle bundle, @NonNull String str, @NonNull Fragment fragment) {
        if (fragment.s == this) {
            bundle.putString(str, fragment.f);
            return;
        }
        h1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        throw null;
    }

    @Nullable
    public Fragment q0() {
        return this.t;
    }

    void r(@NonNull androidx.fragment.app.a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.k(z3);
        } else {
            aVar.j();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2 && this.q >= 1) {
            androidx.fragment.app.j.C(this.r.b(), this.s, arrayList, arrayList2, 0, 1, true, this.n);
        }
        if (z3) {
            H0(this.q, true);
        }
        for (Fragment fragment : this.f717c.m()) {
            if (fragment != null && fragment.H != null && fragment.M && aVar.m(fragment.x)) {
                float f2 = fragment.O;
                if (f2 > 0.0f) {
                    fragment.H.setAlpha(f2);
                }
                if (z3) {
                    fragment.O = 0.0f;
                } else {
                    fragment.O = -1.0f;
                    fragment.M = false;
                }
            }
        }
    }

    @NonNull
    public androidx.fragment.app.o r0() {
        androidx.fragment.app.o oVar = this.x;
        if (oVar != null) {
            return oVar;
        }
        Fragment fragment = this.t;
        return fragment != null ? fragment.s.r0() : this.y;
    }

    public void registerFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
        this.o.o(fragmentLifecycleCallbacks, z);
    }

    public void removeFragmentOnAttachListener(@NonNull FragmentOnAttachListener fragmentOnAttachListener) {
        this.p.remove(fragmentOnAttachListener);
    }

    public void removeOnBackStackChangedListener(@NonNull OnBackStackChangedListener onBackStackChangedListener) {
        ArrayList<OnBackStackChangedListener> arrayList = this.l;
        if (arrayList != null) {
            arrayList.remove(onBackStackChangedListener);
        }
    }

    @Nullable
    public Fragment.SavedState saveFragmentInstanceState(@NonNull Fragment fragment) {
        androidx.fragment.app.h n2 = this.f717c.n(fragment.f);
        if (n2 != null && n2.k().equals(fragment)) {
            return n2.r();
        }
        h1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        throw null;
    }

    public void setFragmentFactory(@NonNull FragmentFactory fragmentFactory) {
        this.v = fragmentFactory;
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    public final void setFragmentResult(@NonNull String str, @NonNull Bundle bundle) {
        l lVar = this.k.get(str);
        if (lVar == null || !lVar.a(Lifecycle.State.STARTED)) {
            this.j.put(str, bundle);
        } else {
            lVar.onFragmentResult(str, bundle);
        }
    }

    @Override // androidx.fragment.app.FragmentResultOwner
    @SuppressLint({"SyntheticAccessor"})
    public final void setFragmentResultListener(@NonNull String str, @NonNull LifecycleOwner lifecycleOwner, @NonNull FragmentResultListener fragmentResultListener) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
            return;
        }
        AnonymousClass6 anonymousClass6 = new LifecycleEventObserver() { // from class: androidx.fragment.app.FragmentManager.6
            final /* synthetic */ String a;
            final /* synthetic */ FragmentResultListener b;

            /* renamed from: c */
            final /* synthetic */ Lifecycle f720c;

            AnonymousClass6(String str2, FragmentResultListener fragmentResultListener2, Lifecycle lifecycle2) {
                str = str2;
                fragmentResultListener = fragmentResultListener2;
                lifecycle = lifecycle2;
            }

            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner2, @NonNull Lifecycle.Event event) {
                Bundle bundle;
                if (event == Lifecycle.Event.ON_START && (bundle = (Bundle) FragmentManager.this.j.get(str)) != null) {
                    fragmentResultListener.onFragmentResult(str, bundle);
                    FragmentManager.this.clearFragmentResult(str);
                }
                if (event == Lifecycle.Event.ON_DESTROY) {
                    lifecycle.removeObserver(this);
                    FragmentManager.this.k.remove(str);
                }
            }
        };
        lifecycle2.addObserver(anonymousClass6);
        l put = this.k.put(str2, new l(lifecycle2, fragmentResultListener2, anonymousClass6));
        if (put != null) {
            put.b();
        }
    }

    @NonNull
    public androidx.fragment.app.h t(@NonNull Fragment fragment) {
        androidx.fragment.app.h n2 = this.f717c.n(fragment.f);
        if (n2 != null) {
            return n2;
        }
        androidx.fragment.app.h hVar = new androidx.fragment.app.h(this.o, this.f717c, fragment);
        hVar.o(this.r.b().getClassLoader());
        hVar.u(this.q);
        return hVar;
    }

    @NonNull
    public ViewModelStore t0(@NonNull Fragment fragment) {
        return this.M.m(fragment);
    }

    @NonNull
    public String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.t;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            obj = this.t;
        } else {
            FragmentHostCallback<?> fragmentHostCallback = this.r;
            if (fragmentHostCallback == null) {
                sb.append("null");
                sb.append("}}");
                return sb.toString();
            }
            sb.append(fragmentHostCallback.getClass().getSimpleName());
            sb.append("{");
            obj = this.r;
        }
        sb.append(Integer.toHexString(System.identityHashCode(obj)));
        sb.append("}");
        sb.append("}}");
        return sb.toString();
    }

    void u0() {
        X(true);
        if (this.h.isEnabled()) {
            popBackStackImmediate();
        } else {
            this.g.onBackPressed();
        }
    }

    public void unregisterFragmentLifecycleCallbacks(@NonNull FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        this.o.p(fragmentLifecycleCallbacks);
    }

    public void v(@NonNull Fragment fragment) {
        if (x0(2)) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.A) {
            return;
        }
        fragment.A = true;
        if (fragment.l) {
            if (x0(2)) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            this.f717c.t(fragment);
            if (y0(fragment)) {
                this.D = true;
            }
            e1(fragment);
        }
    }

    public void v0(@NonNull Fragment fragment) {
        if (x0(2)) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.z) {
            return;
        }
        fragment.z = true;
        fragment.N = true ^ fragment.N;
        e1(fragment);
    }

    public void w() {
        this.E = false;
        this.F = false;
        this.M.q(false);
        Q(4);
    }

    public void w0(@NonNull Fragment fragment) {
        if (fragment.l && y0(fragment)) {
            this.D = true;
        }
    }

    public void x() {
        this.E = false;
        this.F = false;
        this.M.q(false);
        Q(0);
    }

    public void y(@NonNull Configuration configuration) {
        for (Fragment fragment : this.f717c.o()) {
            if (fragment != null) {
                fragment.D(configuration);
            }
        }
    }

    public boolean z(@NonNull MenuItem menuItem) {
        if (this.q < 1) {
            return false;
        }
        for (Fragment fragment : this.f717c.o()) {
            if (fragment != null && fragment.E(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean z0(@Nullable Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        return fragment.isMenuVisible();
    }
}
