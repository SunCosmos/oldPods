package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.c;
import androidx.fragment.app.n;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
class b extends n {

    /* loaded from: classes.dex */
    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[n.e.c.values().length];
            a = iArr;
            try {
                iArr[n.e.c.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[n.e.c.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[n.e.c.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[n.e.c.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* renamed from: androidx.fragment.app.b$b, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class RunnableC0029b implements Runnable {
        final /* synthetic */ List a;
        final /* synthetic */ n.e b;

        RunnableC0029b(List list, n.e eVar) {
            this.a = list;
            this.b = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a.contains(this.b)) {
                this.a.remove(this.b);
                b.this.s(this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends AnimatorListenerAdapter {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f751c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ n.e f752d;
        final /* synthetic */ k e;

        c(b bVar, ViewGroup viewGroup, View view, boolean z, n.e eVar, k kVar) {
            this.a = viewGroup;
            this.b = view;
            this.f751c = z;
            this.f752d = eVar;
            this.e = kVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.endViewTransition(this.b);
            if (this.f751c) {
                this.f752d.e().a(this.b);
            }
            this.e.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements CancellationSignal.OnCancelListener {
        final /* synthetic */ Animator a;

        d(b bVar, Animator animator) {
            this.a = animator;
        }

        @Override // androidx.core.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            this.a.end();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements Animation.AnimationListener {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ k f753c;

        /* loaded from: classes.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                e eVar = e.this;
                eVar.a.endViewTransition(eVar.b);
                e.this.f753c.a();
            }
        }

        e(b bVar, ViewGroup viewGroup, View view, k kVar) {
            this.a = viewGroup;
            this.b = view;
            this.f753c = kVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f implements CancellationSignal.OnCancelListener {
        final /* synthetic */ View a;
        final /* synthetic */ ViewGroup b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ k f754c;

        f(b bVar, View view, ViewGroup viewGroup, k kVar) {
            this.a = view;
            this.b = viewGroup;
            this.f754c = kVar;
        }

        @Override // androidx.core.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            this.a.clearAnimation();
            this.b.endViewTransition(this.a);
            this.f754c.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g implements Runnable {
        final /* synthetic */ n.e a;
        final /* synthetic */ n.e b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f755c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ ArrayMap f756d;

        g(b bVar, n.e eVar, n.e eVar2, boolean z, ArrayMap arrayMap) {
            this.a = eVar;
            this.b = eVar2;
            this.f755c = z;
            this.f756d = arrayMap;
        }

        @Override // java.lang.Runnable
        public void run() {
            androidx.fragment.app.j.f(this.a.f(), this.b.f(), this.f755c, this.f756d, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h implements Runnable {
        final /* synthetic */ FragmentTransitionImpl a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Rect f757c;

        h(b bVar, FragmentTransitionImpl fragmentTransitionImpl, View view, Rect rect) {
            this.a = fragmentTransitionImpl;
            this.b = view;
            this.f757c = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.f(this.b, this.f757c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class i implements Runnable {
        final /* synthetic */ ArrayList a;

        i(b bVar, ArrayList arrayList) {
            this.a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            androidx.fragment.app.j.B(this.a, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class j implements Runnable {
        final /* synthetic */ m a;

        j(b bVar, m mVar) {
            this.a = mVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class k extends l {

        /* renamed from: c, reason: collision with root package name */
        private boolean f758c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f759d;

        @Nullable
        private c.d e;

        k(@NonNull n.e eVar, @NonNull CancellationSignal cancellationSignal, boolean z) {
            super(eVar, cancellationSignal);
            this.f759d = false;
            this.f758c = z;
        }

        @Nullable
        c.d e(@NonNull Context context) {
            if (this.f759d) {
                return this.e;
            }
            c.d c2 = androidx.fragment.app.c.c(context, b().f(), b().e() == n.e.c.VISIBLE, this.f758c);
            this.e = c2;
            this.f759d = true;
            return c2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class l {

        @NonNull
        private final n.e a;

        @NonNull
        private final CancellationSignal b;

        l(@NonNull n.e eVar, @NonNull CancellationSignal cancellationSignal) {
            this.a = eVar;
            this.b = cancellationSignal;
        }

        void a() {
            this.a.d(this.b);
        }

        @NonNull
        n.e b() {
            return this.a;
        }

        @NonNull
        CancellationSignal c() {
            return this.b;
        }

        boolean d() {
            n.e.c cVar;
            n.e.c c2 = n.e.c.c(this.a.f().H);
            n.e.c e = this.a.e();
            return c2 == e || !(c2 == (cVar = n.e.c.VISIBLE) || e == cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class m extends l {

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        private final Object f760c;

        /* renamed from: d, reason: collision with root package name */
        private final boolean f761d;

        @Nullable
        private final Object e;

        m(@NonNull n.e eVar, @NonNull CancellationSignal cancellationSignal, boolean z, boolean z2) {
            super(eVar, cancellationSignal);
            boolean z3;
            Object obj;
            if (eVar.e() == n.e.c.VISIBLE) {
                Fragment f = eVar.f();
                this.f760c = z ? f.getReenterTransition() : f.getEnterTransition();
                Fragment f2 = eVar.f();
                z3 = z ? f2.getAllowReturnTransitionOverlap() : f2.getAllowEnterTransitionOverlap();
            } else {
                Fragment f3 = eVar.f();
                this.f760c = z ? f3.getReturnTransition() : f3.getExitTransition();
                z3 = true;
            }
            this.f761d = z3;
            if (z2) {
                Fragment f4 = eVar.f();
                obj = z ? f4.getSharedElementReturnTransition() : f4.getSharedElementEnterTransition();
            } else {
                obj = null;
            }
            this.e = obj;
        }

        @Nullable
        private FragmentTransitionImpl f(Object obj) {
            if (obj == null) {
                return null;
            }
            FragmentTransitionImpl fragmentTransitionImpl = androidx.fragment.app.j.b;
            if (fragmentTransitionImpl != null && fragmentTransitionImpl.canHandle(obj)) {
                return fragmentTransitionImpl;
            }
            FragmentTransitionImpl fragmentTransitionImpl2 = androidx.fragment.app.j.f774c;
            if (fragmentTransitionImpl2 != null && fragmentTransitionImpl2.canHandle(obj)) {
                return fragmentTransitionImpl2;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + b().f() + " is not a valid framework Transition or AndroidX Transition");
        }

        @Nullable
        FragmentTransitionImpl e() {
            FragmentTransitionImpl f = f(this.f760c);
            FragmentTransitionImpl f2 = f(this.e);
            if (f == null || f2 == null || f == f2) {
                return f != null ? f : f2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + b().f() + " returned Transition " + this.f760c + " which uses a different Transition  type than its shared element transition " + this.e);
        }

        @Nullable
        public Object g() {
            return this.e;
        }

        @Nullable
        Object h() {
            return this.f760c;
        }

        public boolean i() {
            return this.e != null;
        }

        boolean j() {
            return this.f761d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(@NonNull ViewGroup viewGroup) {
        super(viewGroup);
    }

    private void w(@NonNull List<k> list, @NonNull List<n.e> list2, boolean z, @NonNull Map<n.e, Boolean> map) {
        StringBuilder sb;
        String str;
        c.d e2;
        ViewGroup m2 = m();
        Context context = m2.getContext();
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (k kVar : list) {
            if (kVar.d() || (e2 = kVar.e(context)) == null) {
                kVar.a();
            } else {
                Animator animator = e2.b;
                if (animator == null) {
                    arrayList.add(kVar);
                } else {
                    n.e b = kVar.b();
                    Fragment f2 = b.f();
                    if (Boolean.TRUE.equals(map.get(b))) {
                        if (FragmentManager.x0(2)) {
                            Log.v("FragmentManager", "Ignoring Animator set on " + f2 + " as this Fragment was involved in a Transition.");
                        }
                        kVar.a();
                    } else {
                        boolean z3 = b.e() == n.e.c.GONE;
                        if (z3) {
                            list2.remove(b);
                        }
                        View view = f2.H;
                        m2.startViewTransition(view);
                        animator.addListener(new c(this, m2, view, z3, b, kVar));
                        animator.setTarget(view);
                        animator.start();
                        kVar.c().setOnCancelListener(new d(this, animator));
                        z2 = true;
                    }
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            k kVar2 = (k) it.next();
            n.e b2 = kVar2.b();
            Fragment f3 = b2.f();
            if (z) {
                if (FragmentManager.x0(2)) {
                    sb = new StringBuilder();
                    sb.append("Ignoring Animation set on ");
                    sb.append(f3);
                    str = " as Animations cannot run alongside Transitions.";
                    sb.append(str);
                    Log.v("FragmentManager", sb.toString());
                }
                kVar2.a();
            } else if (z2) {
                if (FragmentManager.x0(2)) {
                    sb = new StringBuilder();
                    sb.append("Ignoring Animation set on ");
                    sb.append(f3);
                    str = " as Animations cannot run alongside Animators.";
                    sb.append(str);
                    Log.v("FragmentManager", sb.toString());
                }
                kVar2.a();
            } else {
                View view2 = f3.H;
                Animation animation = (Animation) Preconditions.checkNotNull(((c.d) Preconditions.checkNotNull(kVar2.e(context))).a);
                if (b2.e() != n.e.c.REMOVED) {
                    view2.startAnimation(animation);
                    kVar2.a();
                } else {
                    m2.startViewTransition(view2);
                    c.e eVar = new c.e(animation, m2, view2);
                    eVar.setAnimationListener(new e(this, m2, view2, kVar2));
                    view2.startAnimation(eVar);
                }
                kVar2.c().setOnCancelListener(new f(this, view2, m2, kVar2));
            }
        }
    }

    @NonNull
    private Map<n.e, Boolean> x(@NonNull List<m> list, @NonNull List<n.e> list2, boolean z, @Nullable n.e eVar, @Nullable n.e eVar2) {
        View view;
        Iterator<m> it;
        Boolean bool;
        View view2;
        Object obj;
        ArrayList<View> arrayList;
        Object obj2;
        View view3;
        ArrayMap arrayMap;
        ArrayList<View> arrayList2;
        Rect rect;
        Boolean bool2;
        HashMap hashMap;
        View view4;
        FragmentTransitionImpl fragmentTransitionImpl;
        ArrayList<View> arrayList3;
        b bVar;
        n.e eVar3;
        Boolean bool3;
        SharedElementCallback i2;
        SharedElementCallback k2;
        Boolean bool4;
        int i3;
        View view5;
        View view6;
        String q;
        Boolean bool5;
        b bVar2 = this;
        boolean z2 = z;
        n.e eVar4 = eVar;
        n.e eVar5 = eVar2;
        Boolean bool6 = Boolean.TRUE;
        Boolean bool7 = Boolean.FALSE;
        HashMap hashMap2 = new HashMap();
        FragmentTransitionImpl fragmentTransitionImpl2 = null;
        for (m mVar : list) {
            if (!mVar.d()) {
                FragmentTransitionImpl e2 = mVar.e();
                if (fragmentTransitionImpl2 == null) {
                    fragmentTransitionImpl2 = e2;
                } else if (e2 != null && fragmentTransitionImpl2 != e2) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + mVar.b().f() + " returned Transition " + mVar.h() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        if (fragmentTransitionImpl2 == null) {
            for (m mVar2 : list) {
                hashMap2.put(mVar2.b(), bool7);
                mVar2.a();
            }
            return hashMap2;
        }
        View view7 = new View(m().getContext());
        Rect rect2 = new Rect();
        ArrayList<View> arrayList4 = new ArrayList<>();
        ArrayList<View> arrayList5 = new ArrayList<>();
        ArrayMap arrayMap2 = new ArrayMap();
        Iterator<m> it2 = list.iterator();
        Object obj3 = null;
        View view8 = null;
        boolean z3 = false;
        while (true) {
            view = view8;
            if (!it2.hasNext()) {
                break;
            }
            m next = it2.next();
            if (!next.i() || eVar4 == null || eVar5 == null) {
                arrayMap = arrayMap2;
                arrayList2 = arrayList4;
                rect = rect2;
                bool2 = bool7;
                hashMap = hashMap2;
                view4 = view7;
                fragmentTransitionImpl = fragmentTransitionImpl2;
                arrayList3 = arrayList5;
                bVar = bVar2;
                eVar3 = eVar4;
                bool3 = bool6;
                view8 = view;
            } else {
                Object wrapTransitionInSet = fragmentTransitionImpl2.wrapTransitionInSet(fragmentTransitionImpl2.cloneTransition(next.g()));
                ArrayList<String> s = eVar2.f().s();
                ArrayList<String> s2 = eVar.f().s();
                ArrayList<String> t = eVar.f().t();
                Rect rect3 = rect2;
                bool2 = bool7;
                int i4 = 0;
                while (i4 < t.size()) {
                    int indexOf = s.indexOf(t.get(i4));
                    ArrayList<String> arrayList6 = t;
                    if (indexOf != -1) {
                        s.set(indexOf, s2.get(i4));
                    }
                    i4++;
                    t = arrayList6;
                }
                ArrayList<String> t2 = eVar2.f().t();
                Fragment f2 = eVar.f();
                if (z2) {
                    i2 = f2.i();
                    k2 = eVar2.f().k();
                } else {
                    i2 = f2.k();
                    k2 = eVar2.f().i();
                }
                int i5 = 0;
                for (int size = s.size(); i5 < size; size = size) {
                    arrayMap2.put(s.get(i5), t2.get(i5));
                    i5++;
                }
                ArrayMap<String, View> arrayMap3 = new ArrayMap<>();
                bVar2.u(arrayMap3, eVar.f().H);
                arrayMap3.retainAll(s);
                if (i2 != null) {
                    i2.onMapSharedElements(s, arrayMap3);
                    int size2 = s.size() - 1;
                    while (size2 >= 0) {
                        String str = s.get(size2);
                        View view9 = arrayMap3.get(str);
                        if (view9 == null) {
                            arrayMap2.remove(str);
                            bool5 = bool6;
                        } else {
                            bool5 = bool6;
                            if (!str.equals(ViewCompat.getTransitionName(view9))) {
                                arrayMap2.put(ViewCompat.getTransitionName(view9), (String) arrayMap2.remove(str));
                            }
                        }
                        size2--;
                        bool6 = bool5;
                    }
                    bool4 = bool6;
                } else {
                    bool4 = bool6;
                    arrayMap2.retainAll(arrayMap3.keySet());
                }
                ArrayMap<String, View> arrayMap4 = new ArrayMap<>();
                bVar2.u(arrayMap4, eVar2.f().H);
                arrayMap4.retainAll(t2);
                arrayMap4.retainAll(arrayMap2.values());
                if (k2 != null) {
                    k2.onMapSharedElements(t2, arrayMap4);
                    for (int size3 = t2.size() - 1; size3 >= 0; size3--) {
                        String str2 = t2.get(size3);
                        View view10 = arrayMap4.get(str2);
                        if (view10 == null) {
                            String q2 = androidx.fragment.app.j.q(arrayMap2, str2);
                            if (q2 != null) {
                                arrayMap2.remove(q2);
                            }
                        } else if (!str2.equals(ViewCompat.getTransitionName(view10)) && (q = androidx.fragment.app.j.q(arrayMap2, str2)) != null) {
                            arrayMap2.put(q, ViewCompat.getTransitionName(view10));
                        }
                    }
                } else {
                    androidx.fragment.app.j.y(arrayMap2, arrayMap4);
                }
                bVar2.v(arrayMap3, arrayMap2.keySet());
                bVar2.v(arrayMap4, arrayMap2.values());
                if (arrayMap2.isEmpty()) {
                    arrayList4.clear();
                    arrayList5.clear();
                    eVar5 = eVar2;
                    arrayMap = arrayMap2;
                    arrayList2 = arrayList4;
                    hashMap = hashMap2;
                    view4 = view7;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    rect = rect3;
                    view8 = view;
                    bool3 = bool4;
                    obj3 = null;
                    arrayList3 = arrayList5;
                    bVar = bVar2;
                    eVar3 = eVar;
                } else {
                    androidx.fragment.app.j.f(eVar2.f(), eVar.f(), z2, arrayMap3, true);
                    arrayMap = arrayMap2;
                    HashMap hashMap3 = hashMap2;
                    arrayList3 = arrayList5;
                    View view11 = view7;
                    ArrayList<View> arrayList7 = arrayList4;
                    OneShotPreDrawListener.add(m(), new g(this, eVar2, eVar, z, arrayMap4));
                    arrayList7.addAll(arrayMap3.values());
                    if (s.isEmpty()) {
                        i3 = 0;
                        view8 = view;
                    } else {
                        i3 = 0;
                        View view12 = arrayMap3.get(s.get(0));
                        fragmentTransitionImpl2.setEpicenter(wrapTransitionInSet, view12);
                        view8 = view12;
                    }
                    arrayList3.addAll(arrayMap4.values());
                    if (t2.isEmpty() || (view6 = arrayMap4.get(t2.get(i3))) == null) {
                        bVar = this;
                        rect = rect3;
                        view5 = view11;
                    } else {
                        bVar = this;
                        rect = rect3;
                        OneShotPreDrawListener.add(m(), new h(bVar, fragmentTransitionImpl2, view6, rect));
                        view5 = view11;
                        z3 = true;
                    }
                    fragmentTransitionImpl2.setSharedElementTargets(wrapTransitionInSet, view5, arrayList7);
                    view4 = view5;
                    arrayList2 = arrayList7;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    fragmentTransitionImpl2.scheduleRemoveTargets(wrapTransitionInSet, null, null, null, null, wrapTransitionInSet, arrayList3);
                    eVar3 = eVar;
                    bool3 = bool4;
                    hashMap = hashMap3;
                    hashMap.put(eVar3, bool3);
                    eVar5 = eVar2;
                    hashMap.put(eVar5, bool3);
                    obj3 = wrapTransitionInSet;
                }
            }
            view7 = view4;
            fragmentTransitionImpl2 = fragmentTransitionImpl;
            bool6 = bool3;
            bool7 = bool2;
            rect2 = rect;
            eVar4 = eVar3;
            arrayList4 = arrayList2;
            bVar2 = bVar;
            arrayList5 = arrayList3;
            arrayMap2 = arrayMap;
            hashMap2 = hashMap;
            z2 = z;
        }
        ArrayMap arrayMap5 = arrayMap2;
        ArrayList<View> arrayList8 = arrayList4;
        Rect rect4 = rect2;
        Boolean bool8 = bool7;
        HashMap hashMap4 = hashMap2;
        View view13 = view7;
        FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl2;
        ArrayList<View> arrayList9 = arrayList5;
        b bVar3 = bVar2;
        n.e eVar6 = eVar4;
        Boolean bool9 = bool6;
        ArrayList arrayList10 = new ArrayList();
        Iterator<m> it3 = list.iterator();
        Object obj4 = null;
        Object obj5 = null;
        while (it3.hasNext()) {
            m next2 = it3.next();
            if (next2.d()) {
                it = it3;
                hashMap4.put(next2.b(), bool8);
                next2.a();
            } else {
                it = it3;
                Boolean bool10 = bool8;
                Object cloneTransition = fragmentTransitionImpl3.cloneTransition(next2.h());
                Object obj6 = obj4;
                n.e b = next2.b();
                boolean z4 = obj3 != null && (b == eVar6 || b == eVar5);
                if (cloneTransition == null) {
                    if (!z4) {
                        hashMap4.put(b, bool10);
                        next2.a();
                    }
                    view2 = view13;
                    arrayList = arrayList8;
                    bool = bool10;
                    view3 = view;
                    obj4 = obj6;
                } else {
                    bool = bool10;
                    ArrayList<View> arrayList11 = new ArrayList<>();
                    Object obj7 = obj5;
                    bVar3.t(arrayList11, b.f().H);
                    if (z4) {
                        if (b == eVar6) {
                            arrayList11.removeAll(arrayList8);
                        } else {
                            arrayList11.removeAll(arrayList9);
                        }
                    }
                    if (arrayList11.isEmpty()) {
                        fragmentTransitionImpl3.addTarget(cloneTransition, view13);
                        view2 = view13;
                        arrayList = arrayList8;
                        obj2 = cloneTransition;
                        obj = obj7;
                    } else {
                        fragmentTransitionImpl3.addTargets(cloneTransition, arrayList11);
                        view2 = view13;
                        obj = obj7;
                        fragmentTransitionImpl3.scheduleRemoveTargets(cloneTransition, cloneTransition, arrayList11, null, null, null, null);
                        if (b.e() == n.e.c.GONE) {
                            list2.remove(b);
                            ArrayList<View> arrayList12 = new ArrayList<>(arrayList11);
                            arrayList12.remove(b.f().H);
                            arrayList = arrayList8;
                            obj2 = cloneTransition;
                            fragmentTransitionImpl3.scheduleHideFragmentView(obj2, b.f().H, arrayList12);
                            OneShotPreDrawListener.add(m(), new i(bVar3, arrayList11));
                        } else {
                            arrayList = arrayList8;
                            obj2 = cloneTransition;
                        }
                    }
                    if (b.e() == n.e.c.VISIBLE) {
                        arrayList10.addAll(arrayList11);
                        if (z3) {
                            fragmentTransitionImpl3.setEpicenter(obj2, rect4);
                        }
                        view3 = view;
                    } else {
                        view3 = view;
                        fragmentTransitionImpl3.setEpicenter(obj2, view3);
                    }
                    hashMap4.put(b, bool9);
                    if (next2.j()) {
                        obj5 = obj;
                        obj4 = fragmentTransitionImpl3.mergeTransitionsTogether(obj6, obj2, null);
                    } else {
                        obj4 = obj6;
                        obj5 = fragmentTransitionImpl3.mergeTransitionsTogether(obj, obj2, null);
                    }
                }
                view = view3;
                arrayList8 = arrayList;
                bool8 = bool;
                view13 = view2;
            }
            it3 = it;
        }
        ArrayList<View> arrayList13 = arrayList8;
        Object mergeTransitionsInSequence = fragmentTransitionImpl3.mergeTransitionsInSequence(obj4, obj5, obj3);
        for (m mVar3 : list) {
            if (!mVar3.d()) {
                Object h2 = mVar3.h();
                n.e b2 = mVar3.b();
                boolean z5 = obj3 != null && (b2 == eVar6 || b2 == eVar5);
                if (h2 != null || z5) {
                    if (ViewCompat.isLaidOut(m())) {
                        fragmentTransitionImpl3.setListenerForTransitionEnd(mVar3.b().f(), mergeTransitionsInSequence, mVar3.c(), new j(bVar3, mVar3));
                    } else {
                        if (FragmentManager.x0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Container " + m() + " has not been laid out. Completing operation " + b2);
                        }
                        mVar3.a();
                    }
                }
            }
        }
        if (!ViewCompat.isLaidOut(m())) {
            return hashMap4;
        }
        androidx.fragment.app.j.B(arrayList10, 4);
        ArrayList<String> h3 = fragmentTransitionImpl3.h(arrayList9);
        fragmentTransitionImpl3.beginDelayedTransition(m(), mergeTransitionsInSequence);
        fragmentTransitionImpl3.k(m(), arrayList13, arrayList9, h3, arrayMap5);
        androidx.fragment.app.j.B(arrayList10, 0);
        fragmentTransitionImpl3.swapSharedElementTargets(obj3, arrayList13, arrayList9);
        return hashMap4;
    }

    @Override // androidx.fragment.app.n
    void f(@NonNull List<n.e> list, boolean z) {
        n.e eVar = null;
        n.e eVar2 = null;
        for (n.e eVar3 : list) {
            n.e.c c2 = n.e.c.c(eVar3.f().H);
            int i2 = a.a[eVar3.e().ordinal()];
            if (i2 == 1 || i2 == 2 || i2 == 3) {
                if (c2 == n.e.c.VISIBLE && eVar == null) {
                    eVar = eVar3;
                }
            } else if (i2 == 4 && c2 != n.e.c.VISIBLE) {
                eVar2 = eVar3;
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList(list);
        for (n.e eVar4 : list) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            eVar4.j(cancellationSignal);
            arrayList.add(new k(eVar4, cancellationSignal, z));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            eVar4.j(cancellationSignal2);
            boolean z2 = false;
            if (z) {
                if (eVar4 != eVar) {
                    arrayList2.add(new m(eVar4, cancellationSignal2, z, z2));
                    eVar4.a(new RunnableC0029b(arrayList3, eVar4));
                }
                z2 = true;
                arrayList2.add(new m(eVar4, cancellationSignal2, z, z2));
                eVar4.a(new RunnableC0029b(arrayList3, eVar4));
            } else {
                if (eVar4 != eVar2) {
                    arrayList2.add(new m(eVar4, cancellationSignal2, z, z2));
                    eVar4.a(new RunnableC0029b(arrayList3, eVar4));
                }
                z2 = true;
                arrayList2.add(new m(eVar4, cancellationSignal2, z, z2));
                eVar4.a(new RunnableC0029b(arrayList3, eVar4));
            }
        }
        Map<n.e, Boolean> x = x(arrayList2, arrayList3, z, eVar, eVar2);
        w(arrayList, arrayList3, x.containsValue(Boolean.TRUE), x);
        Iterator<n.e> it = arrayList3.iterator();
        while (it.hasNext()) {
            s(it.next());
        }
        arrayList3.clear();
    }

    void s(@NonNull n.e eVar) {
        eVar.e().a(eVar.f().H);
    }

    void t(ArrayList<View> arrayList, View view) {
        if (!(view instanceof ViewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(viewGroup);
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt.getVisibility() == 0) {
                t(arrayList, childAt);
            }
        }
    }

    void u(Map<String, View> map, @NonNull View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (childAt.getVisibility() == 0) {
                    u(map, childAt);
                }
            }
        }
    }

    void v(@NonNull ArrayMap<String, View> arrayMap, @NonNull Collection<String> collection) {
        Iterator<Map.Entry<String, View>> it = arrayMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.getTransitionName(it.next().getValue()))) {
                it.remove();
            }
        }
    }
}
