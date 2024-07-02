package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.CancellationSignal;
import androidx.core.view.ViewCompat;
import androidx.fragment.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class n {
    private final ViewGroup a;
    final ArrayList<e> b = new ArrayList<>();

    /* renamed from: c, reason: collision with root package name */
    final ArrayList<e> f790c = new ArrayList<>();

    /* renamed from: d, reason: collision with root package name */
    boolean f791d = false;
    boolean e = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        final /* synthetic */ d a;

        a(d dVar) {
            this.a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (n.this.b.contains(this.a)) {
                this.a.e().a(this.a.f().H);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Runnable {
        final /* synthetic */ d a;

        b(d dVar) {
            this.a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            n.this.b.remove(this.a);
            n.this.f790c.remove(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class c {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[e.b.values().length];
            b = iArr;
            try {
                iArr[e.b.ADDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[e.b.REMOVING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[e.b.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[e.c.values().length];
            a = iArr2;
            try {
                iArr2[e.c.REMOVED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[e.c.VISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[e.c.GONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[e.c.INVISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d extends e {

        @NonNull
        private final h h;

        d(@NonNull e.c cVar, @NonNull e.b bVar, @NonNull h hVar, @NonNull CancellationSignal cancellationSignal) {
            super(cVar, bVar, hVar.k(), cancellationSignal);
            this.h = hVar;
        }

        @Override // androidx.fragment.app.n.e
        public void c() {
            super.c();
            this.h.m();
        }

        @Override // androidx.fragment.app.n.e
        void l() {
            if (g() == e.b.ADDING) {
                Fragment k = this.h.k();
                View findFocus = k.H.findFocus();
                if (findFocus != null) {
                    k.h0(findFocus);
                    if (FragmentManager.x0(2)) {
                        Log.v("FragmentManager", "requestFocus: Saved focused view " + findFocus + " for Fragment " + k);
                    }
                }
                View requireView = f().requireView();
                if (requireView.getParent() == null) {
                    this.h.b();
                    requireView.setAlpha(0.0f);
                }
                if (requireView.getAlpha() == 0.0f && requireView.getVisibility() == 0) {
                    requireView.setVisibility(4);
                }
                requireView.setAlpha(k.r());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class e {

        @NonNull
        private c a;

        @NonNull
        private b b;

        /* renamed from: c, reason: collision with root package name */
        @NonNull
        private final Fragment f792c;

        /* renamed from: d, reason: collision with root package name */
        @NonNull
        private final List<Runnable> f793d = new ArrayList();

        @NonNull
        private final HashSet<CancellationSignal> e = new HashSet<>();
        private boolean f = false;
        private boolean g = false;

        /* loaded from: classes.dex */
        class a implements CancellationSignal.OnCancelListener {
            a() {
            }

            @Override // androidx.core.os.CancellationSignal.OnCancelListener
            public void onCancel() {
                e.this.b();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public enum b {
            NONE,
            ADDING,
            REMOVING
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public enum c {
            REMOVED,
            VISIBLE,
            GONE,
            INVISIBLE;

            /* JADX INFO: Access modifiers changed from: package-private */
            @NonNull
            public static c b(int i2) {
                if (i2 == 0) {
                    return VISIBLE;
                }
                if (i2 == 4) {
                    return INVISIBLE;
                }
                if (i2 == 8) {
                    return GONE;
                }
                throw new IllegalArgumentException("Unknown visibility " + i2);
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @NonNull
            public static c c(@NonNull View view) {
                return (view.getAlpha() == 0.0f && view.getVisibility() == 0) ? INVISIBLE : b(view.getVisibility());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public void a(@NonNull View view) {
                int i2;
                int i3 = c.a[ordinal()];
                if (i3 == 1) {
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup != null) {
                        if (FragmentManager.x0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Removing view " + view + " from container " + viewGroup);
                        }
                        viewGroup.removeView(view);
                        return;
                    }
                    return;
                }
                if (i3 == 2) {
                    if (FragmentManager.x0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to VISIBLE");
                    }
                    i2 = 0;
                } else {
                    if (i3 != 3) {
                        if (i3 != 4) {
                            return;
                        }
                        if (FragmentManager.x0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to INVISIBLE");
                        }
                        view.setVisibility(4);
                        return;
                    }
                    if (FragmentManager.x0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to GONE");
                    }
                    i2 = 8;
                }
                view.setVisibility(i2);
            }
        }

        e(@NonNull c cVar, @NonNull b bVar, @NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal) {
            this.a = cVar;
            this.b = bVar;
            this.f792c = fragment;
            cancellationSignal.setOnCancelListener(new a());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void a(@NonNull Runnable runnable) {
            this.f793d.add(runnable);
        }

        final void b() {
            if (h()) {
                return;
            }
            this.f = true;
            if (this.e.isEmpty()) {
                c();
                return;
            }
            Iterator it = new ArrayList(this.e).iterator();
            while (it.hasNext()) {
                ((CancellationSignal) it.next()).cancel();
            }
        }

        @CallSuper
        public void c() {
            if (this.g) {
                return;
            }
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
            }
            this.g = true;
            Iterator<Runnable> it = this.f793d.iterator();
            while (it.hasNext()) {
                it.next().run();
            }
        }

        public final void d(@NonNull CancellationSignal cancellationSignal) {
            if (this.e.remove(cancellationSignal) && this.e.isEmpty()) {
                c();
            }
        }

        @NonNull
        public c e() {
            return this.a;
        }

        @NonNull
        public final Fragment f() {
            return this.f792c;
        }

        @NonNull
        b g() {
            return this.b;
        }

        final boolean h() {
            return this.f;
        }

        final boolean i() {
            return this.g;
        }

        public final void j(@NonNull CancellationSignal cancellationSignal) {
            l();
            this.e.add(cancellationSignal);
        }

        final void k(@NonNull c cVar, @NonNull b bVar) {
            b bVar2;
            int i2 = c.b[bVar.ordinal()];
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3 && this.a != c.REMOVED) {
                        if (FragmentManager.x0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f792c + " mFinalState = " + this.a + " -> " + cVar + ". ");
                        }
                        this.a = cVar;
                        return;
                    }
                    return;
                }
                if (FragmentManager.x0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f792c + " mFinalState = " + this.a + " -> REMOVED. mLifecycleImpact  = " + this.b + " to REMOVING.");
                }
                this.a = c.REMOVED;
                bVar2 = b.REMOVING;
            } else {
                if (this.a != c.REMOVED) {
                    return;
                }
                if (FragmentManager.x0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f792c + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + this.b + " to ADDING.");
                }
                this.a = c.VISIBLE;
                bVar2 = b.ADDING;
            }
            this.b = bVar2;
        }

        void l() {
        }

        @NonNull
        public String toString() {
            return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + this.a + "} {mLifecycleImpact = " + this.b + "} {mFragment = " + this.f792c + "}";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(@NonNull ViewGroup viewGroup) {
        this.a = viewGroup;
    }

    private void a(@NonNull e.c cVar, @NonNull e.b bVar, @NonNull h hVar) {
        synchronized (this.b) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            e h = h(hVar.k());
            if (h != null) {
                h.k(cVar, bVar);
                return;
            }
            d dVar = new d(cVar, bVar, hVar, cancellationSignal);
            this.b.add(dVar);
            dVar.a(new a(dVar));
            dVar.a(new b(dVar));
        }
    }

    @Nullable
    private e h(@NonNull Fragment fragment) {
        Iterator<e> it = this.b.iterator();
        while (it.hasNext()) {
            e next = it.next();
            if (next.f().equals(fragment) && !next.h()) {
                return next;
            }
        }
        return null;
    }

    @Nullable
    private e i(@NonNull Fragment fragment) {
        Iterator<e> it = this.f790c.iterator();
        while (it.hasNext()) {
            e next = it.next();
            if (next.f().equals(fragment) && !next.h()) {
                return next;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static n n(@NonNull ViewGroup viewGroup, @NonNull FragmentManager fragmentManager) {
        return o(viewGroup, fragmentManager.r0());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static n o(@NonNull ViewGroup viewGroup, @NonNull o oVar) {
        int i2 = R.id.special_effects_controller_view_tag;
        Object tag = viewGroup.getTag(i2);
        if (tag instanceof n) {
            return (n) tag;
        }
        n a2 = oVar.a(viewGroup);
        viewGroup.setTag(i2, a2);
        return a2;
    }

    private void q() {
        Iterator<e> it = this.b.iterator();
        while (it.hasNext()) {
            e next = it.next();
            if (next.g() == e.b.ADDING) {
                next.k(e.c.b(next.f().requireView().getVisibility()), e.b.NONE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(@NonNull e.c cVar, @NonNull h hVar) {
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + hVar.k());
        }
        a(cVar, e.b.ADDING, hVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(@NonNull h hVar) {
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + hVar.k());
        }
        a(e.c.GONE, e.b.NONE, hVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(@NonNull h hVar) {
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + hVar.k());
        }
        a(e.c.REMOVED, e.b.REMOVING, hVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(@NonNull h hVar) {
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + hVar.k());
        }
        a(e.c.VISIBLE, e.b.NONE, hVar);
    }

    abstract void f(@NonNull List<e> list, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        if (this.e) {
            return;
        }
        if (!ViewCompat.isAttachedToWindow(this.a)) {
            j();
            this.f791d = false;
            return;
        }
        synchronized (this.b) {
            if (!this.b.isEmpty()) {
                ArrayList arrayList = new ArrayList(this.f790c);
                this.f790c.clear();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    e eVar = (e) it.next();
                    if (FragmentManager.x0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Cancelling operation " + eVar);
                    }
                    eVar.b();
                    if (!eVar.i()) {
                        this.f790c.add(eVar);
                    }
                }
                q();
                ArrayList arrayList2 = new ArrayList(this.b);
                this.b.clear();
                this.f790c.addAll(arrayList2);
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    ((e) it2.next()).l();
                }
                f(arrayList2, this.f791d);
                this.f791d = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        String str;
        String str2;
        boolean isAttachedToWindow = ViewCompat.isAttachedToWindow(this.a);
        synchronized (this.b) {
            q();
            Iterator<e> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().l();
            }
            Iterator it2 = new ArrayList(this.f790c).iterator();
            while (it2.hasNext()) {
                e eVar = (e) it2.next();
                if (FragmentManager.x0(2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("SpecialEffectsController: ");
                    if (isAttachedToWindow) {
                        str2 = "";
                    } else {
                        str2 = "Container " + this.a + " is not attached to window. ";
                    }
                    sb.append(str2);
                    sb.append("Cancelling running operation ");
                    sb.append(eVar);
                    Log.v("FragmentManager", sb.toString());
                }
                eVar.b();
            }
            Iterator it3 = new ArrayList(this.b).iterator();
            while (it3.hasNext()) {
                e eVar2 = (e) it3.next();
                if (FragmentManager.x0(2)) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("SpecialEffectsController: ");
                    if (isAttachedToWindow) {
                        str = "";
                    } else {
                        str = "Container " + this.a + " is not attached to window. ";
                    }
                    sb2.append(str);
                    sb2.append("Cancelling pending operation ");
                    sb2.append(eVar2);
                    Log.v("FragmentManager", sb2.toString());
                }
                eVar2.b();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k() {
        if (this.e) {
            this.e = false;
            g();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public e.b l(@NonNull h hVar) {
        e h = h(hVar.k());
        e.b g = h != null ? h.g() : null;
        e i2 = i(hVar.k());
        return (i2 == null || !(g == null || g == e.b.NONE)) ? g : i2.g();
    }

    @NonNull
    public ViewGroup m() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p() {
        synchronized (this.b) {
            q();
            this.e = false;
            int size = this.b.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                e eVar = this.b.get(size);
                e.c c2 = e.c.c(eVar.f().H);
                e.c e2 = eVar.e();
                e.c cVar = e.c.VISIBLE;
                if (e2 == cVar && c2 != cVar) {
                    this.e = eVar.f().y();
                    break;
                }
                size--;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r(boolean z) {
        this.f791d = z;
    }
}
