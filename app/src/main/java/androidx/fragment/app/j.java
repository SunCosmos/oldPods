package androidx.fragment.app;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class j {
    private static final int[] a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};
    static final FragmentTransitionImpl b;

    /* renamed from: c, reason: collision with root package name */
    static final FragmentTransitionImpl f774c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        final /* synthetic */ g a;
        final /* synthetic */ Fragment b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ CancellationSignal f775c;

        a(g gVar, Fragment fragment, CancellationSignal cancellationSignal) {
            this.a = gVar;
            this.b = fragment;
            this.f775c = cancellationSignal;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b, this.f775c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Runnable {
        final /* synthetic */ ArrayList a;

        b(ArrayList arrayList) {
            this.a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            j.B(this.a, 4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements Runnable {
        final /* synthetic */ g a;
        final /* synthetic */ Fragment b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ CancellationSignal f776c;

        c(g gVar, Fragment fragment, CancellationSignal cancellationSignal) {
            this.a = gVar;
            this.b = fragment;
            this.f776c = cancellationSignal;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b, this.f776c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements Runnable {
        final /* synthetic */ Object a;
        final /* synthetic */ FragmentTransitionImpl b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f777c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ Fragment f778d;
        final /* synthetic */ ArrayList e;
        final /* synthetic */ ArrayList f;
        final /* synthetic */ ArrayList g;
        final /* synthetic */ Object h;

        d(Object obj, FragmentTransitionImpl fragmentTransitionImpl, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.a = obj;
            this.b = fragmentTransitionImpl;
            this.f777c = view;
            this.f778d = fragment;
            this.e = arrayList;
            this.f = arrayList2;
            this.g = arrayList3;
            this.h = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.a;
            if (obj != null) {
                this.b.removeTarget(obj, this.f777c);
                this.f.addAll(j.k(this.b, this.a, this.f778d, this.e, this.f777c));
            }
            if (this.g != null) {
                if (this.h != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.f777c);
                    this.b.replaceTargets(this.h, this.g, arrayList);
                }
                this.g.clear();
                this.g.add(this.f777c);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements Runnable {
        final /* synthetic */ Fragment a;
        final /* synthetic */ Fragment b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f779c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ ArrayMap f780d;
        final /* synthetic */ View e;
        final /* synthetic */ FragmentTransitionImpl f;
        final /* synthetic */ Rect g;

        e(Fragment fragment, Fragment fragment2, boolean z, ArrayMap arrayMap, View view, FragmentTransitionImpl fragmentTransitionImpl, Rect rect) {
            this.a = fragment;
            this.b = fragment2;
            this.f779c = z;
            this.f780d = arrayMap;
            this.e = view;
            this.f = fragmentTransitionImpl;
            this.g = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            j.f(this.a, this.b, this.f779c, this.f780d, false);
            View view = this.e;
            if (view != null) {
                this.f.f(view, this.g);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f implements Runnable {
        final /* synthetic */ FragmentTransitionImpl a;
        final /* synthetic */ ArrayMap b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Object f781c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ h f782d;
        final /* synthetic */ ArrayList e;
        final /* synthetic */ View f;
        final /* synthetic */ Fragment g;
        final /* synthetic */ Fragment h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ boolean f783i;
        final /* synthetic */ ArrayList j;
        final /* synthetic */ Object k;
        final /* synthetic */ Rect l;

        f(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap arrayMap, Object obj, h hVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.a = fragmentTransitionImpl;
            this.b = arrayMap;
            this.f781c = obj;
            this.f782d = hVar;
            this.e = arrayList;
            this.f = view;
            this.g = fragment;
            this.h = fragment2;
            this.f783i = z;
            this.j = arrayList2;
            this.k = obj2;
            this.l = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayMap<String, View> h = j.h(this.a, this.b, this.f781c, this.f782d);
            if (h != null) {
                this.e.addAll(h.values());
                this.e.add(this.f);
            }
            j.f(this.g, this.h, this.f783i, h, false);
            Object obj = this.f781c;
            if (obj != null) {
                this.a.swapSharedElementTargets(obj, this.j, this.e);
                View t = j.t(h, this.f782d, this.k, this.f783i);
                if (t != null) {
                    this.a.f(t, this.l);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface g {
        void a(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal);

        void b(@NonNull Fragment fragment, @NonNull CancellationSignal cancellationSignal);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class h {
        public Fragment a;
        public boolean b;

        /* renamed from: c, reason: collision with root package name */
        public androidx.fragment.app.a f784c;

        /* renamed from: d, reason: collision with root package name */
        public Fragment f785d;
        public boolean e;
        public androidx.fragment.app.a f;

        h() {
        }
    }

    static {
        b = Build.VERSION.SDK_INT >= 21 ? new k() : null;
        f774c = x();
    }

    private static void A(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, ArrayMap<String, View> arrayMap, boolean z, androidx.fragment.app.a aVar) {
        ArrayList<String> arrayList = aVar.p;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        View view = arrayMap.get((z ? aVar.q : aVar.p).get(0));
        fragmentTransitionImpl.setEpicenter(obj, view);
        if (obj2 != null) {
            fragmentTransitionImpl.setEpicenter(obj2, view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void B(ArrayList<View> arrayList, int i2) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void C(@NonNull Context context, @NonNull FragmentContainer fragmentContainer, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, boolean z, g gVar) {
        ViewGroup viewGroup;
        SparseArray sparseArray = new SparseArray();
        for (int i4 = i2; i4 < i3; i4++) {
            androidx.fragment.app.a aVar = arrayList.get(i4);
            if (arrayList2.get(i4).booleanValue()) {
                e(aVar, sparseArray, z);
            } else {
                c(aVar, sparseArray, z);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(context);
            int size = sparseArray.size();
            for (int i5 = 0; i5 < size; i5++) {
                int keyAt = sparseArray.keyAt(i5);
                ArrayMap<String, String> d2 = d(keyAt, arrayList, arrayList2, i2, i3);
                h hVar = (h) sparseArray.valueAt(i5);
                if (fragmentContainer.onHasView() && (viewGroup = (ViewGroup) fragmentContainer.onFindViewById(keyAt)) != null) {
                    if (z) {
                        o(viewGroup, hVar, view, d2, gVar);
                    } else {
                        n(viewGroup, hVar, view, d2, gVar);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean D() {
        return (b == null && f774c == null) ? false : true;
    }

    private static void a(ArrayList<View> arrayList, ArrayMap<String, View> arrayMap, Collection<String> collection) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View valueAt = arrayMap.valueAt(size);
            if (collection.contains(ViewCompat.getTransitionName(valueAt))) {
                arrayList.add(valueAt);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0039, code lost:
    
        if (r0.l != false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x008a, code lost:
    
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x006e, code lost:
    
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0088, code lost:
    
        if (r0.z == false) goto L69;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00d7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void b(androidx.fragment.app.a r8, androidx.fragment.app.FragmentTransaction.a r9, android.util.SparseArray<androidx.fragment.app.j.h> r10, boolean r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.j.b(androidx.fragment.app.a, androidx.fragment.app.FragmentTransaction$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static void c(androidx.fragment.app.a aVar, SparseArray<h> sparseArray, boolean z) {
        int size = aVar.f743c.size();
        for (int i2 = 0; i2 < size; i2++) {
            b(aVar, aVar.f743c.get(i2), sparseArray, false, z);
        }
    }

    private static ArrayMap<String, String> d(int i2, ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i3, int i4) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        for (int i5 = i4 - 1; i5 >= i3; i5--) {
            androidx.fragment.app.a aVar = arrayList.get(i5);
            if (aVar.m(i2)) {
                boolean booleanValue = arrayList2.get(i5).booleanValue();
                ArrayList<String> arrayList5 = aVar.p;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (booleanValue) {
                        arrayList3 = aVar.p;
                        arrayList4 = aVar.q;
                    } else {
                        ArrayList<String> arrayList6 = aVar.p;
                        arrayList3 = aVar.q;
                        arrayList4 = arrayList6;
                    }
                    for (int i6 = 0; i6 < size; i6++) {
                        String str = arrayList4.get(i6);
                        String str2 = arrayList3.get(i6);
                        String remove = arrayMap.remove(str2);
                        if (remove != null) {
                            arrayMap.put(str, remove);
                        } else {
                            arrayMap.put(str, str2);
                        }
                    }
                }
            }
        }
        return arrayMap;
    }

    public static void e(androidx.fragment.app.a aVar, SparseArray<h> sparseArray, boolean z) {
        if (aVar.t.k0().onHasView()) {
            for (int size = aVar.f743c.size() - 1; size >= 0; size--) {
                b(aVar, aVar.f743c.get(size), sparseArray, true, z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(Fragment fragment, Fragment fragment2, boolean z, ArrayMap<String, View> arrayMap, boolean z2) {
        SharedElementCallback i2 = z ? fragment2.i() : fragment.i();
        if (i2 != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = arrayMap == null ? 0 : arrayMap.size();
            for (int i3 = 0; i3 < size; i3++) {
                arrayList2.add(arrayMap.keyAt(i3));
                arrayList.add(arrayMap.valueAt(i3));
            }
            if (z2) {
                i2.onSharedElementStart(arrayList2, arrayList, null);
            } else {
                i2.onSharedElementEnd(arrayList2, arrayList, null);
            }
        }
    }

    private static boolean g(FragmentTransitionImpl fragmentTransitionImpl, List<Object> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!fragmentTransitionImpl.canHandle(list.get(i2))) {
                return false;
            }
        }
        return true;
    }

    static ArrayMap<String, View> h(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, h hVar) {
        SharedElementCallback i2;
        ArrayList<String> arrayList;
        String q;
        Fragment fragment = hVar.a;
        View view = fragment.getView();
        if (arrayMap.isEmpty() || obj == null || view == null) {
            arrayMap.clear();
            return null;
        }
        ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
        fragmentTransitionImpl.e(arrayMap2, view);
        androidx.fragment.app.a aVar = hVar.f784c;
        if (hVar.b) {
            i2 = fragment.k();
            arrayList = aVar.p;
        } else {
            i2 = fragment.i();
            arrayList = aVar.q;
        }
        if (arrayList != null) {
            arrayMap2.retainAll(arrayList);
            arrayMap2.retainAll(arrayMap.values());
        }
        if (i2 != null) {
            i2.onMapSharedElements(arrayList, arrayMap2);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = arrayList.get(size);
                View view2 = arrayMap2.get(str);
                if (view2 == null) {
                    String q2 = q(arrayMap, str);
                    if (q2 != null) {
                        arrayMap.remove(q2);
                    }
                } else if (!str.equals(ViewCompat.getTransitionName(view2)) && (q = q(arrayMap, str)) != null) {
                    arrayMap.put(q, ViewCompat.getTransitionName(view2));
                }
            }
        } else {
            y(arrayMap, arrayMap2);
        }
        return arrayMap2;
    }

    private static ArrayMap<String, View> i(FragmentTransitionImpl fragmentTransitionImpl, ArrayMap<String, String> arrayMap, Object obj, h hVar) {
        SharedElementCallback k;
        ArrayList<String> arrayList;
        if (arrayMap.isEmpty() || obj == null) {
            arrayMap.clear();
            return null;
        }
        Fragment fragment = hVar.f785d;
        ArrayMap<String, View> arrayMap2 = new ArrayMap<>();
        fragmentTransitionImpl.e(arrayMap2, fragment.requireView());
        androidx.fragment.app.a aVar = hVar.f;
        if (hVar.e) {
            k = fragment.i();
            arrayList = aVar.q;
        } else {
            k = fragment.k();
            arrayList = aVar.p;
        }
        if (arrayList != null) {
            arrayMap2.retainAll(arrayList);
        }
        if (k != null) {
            k.onMapSharedElements(arrayList, arrayMap2);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                String str = arrayList.get(size);
                View view = arrayMap2.get(str);
                if (view == null) {
                    arrayMap.remove(str);
                } else if (!str.equals(ViewCompat.getTransitionName(view))) {
                    arrayMap.put(ViewCompat.getTransitionName(view), arrayMap.remove(str));
                }
            }
        } else {
            arrayMap.retainAll(arrayMap2.keySet());
        }
        return arrayMap2;
    }

    private static FragmentTransitionImpl j(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            Object enterTransition = fragment2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        FragmentTransitionImpl fragmentTransitionImpl = b;
        if (fragmentTransitionImpl != null && g(fragmentTransitionImpl, arrayList)) {
            return fragmentTransitionImpl;
        }
        FragmentTransitionImpl fragmentTransitionImpl2 = f774c;
        if (fragmentTransitionImpl2 != null && g(fragmentTransitionImpl2, arrayList)) {
            return fragmentTransitionImpl2;
        }
        if (fragmentTransitionImpl == null && fragmentTransitionImpl2 == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    static ArrayList<View> k(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.getView();
        if (view2 != null) {
            fragmentTransitionImpl.b(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        fragmentTransitionImpl.addTargets(obj, arrayList2);
        return arrayList2;
    }

    private static Object l(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, ArrayMap<String, String> arrayMap, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object u;
        ArrayMap<String, String> arrayMap2;
        Object obj3;
        Rect rect;
        Fragment fragment = hVar.a;
        Fragment fragment2 = hVar.f785d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.b;
        if (arrayMap.isEmpty()) {
            arrayMap2 = arrayMap;
            u = null;
        } else {
            u = u(fragmentTransitionImpl, fragment, fragment2, z);
            arrayMap2 = arrayMap;
        }
        ArrayMap<String, View> i2 = i(fragmentTransitionImpl, arrayMap2, u, hVar);
        if (arrayMap.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(i2.values());
            obj3 = u;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, i2, true);
        if (obj3 != null) {
            rect = new Rect();
            fragmentTransitionImpl.setSharedElementTargets(obj3, view, arrayList);
            A(fragmentTransitionImpl, obj3, obj2, i2, hVar.e, hVar.f);
            if (obj != null) {
                fragmentTransitionImpl.setEpicenter(obj, rect);
            }
        } else {
            rect = null;
        }
        OneShotPreDrawListener.add(viewGroup, new f(fragmentTransitionImpl, arrayMap, obj3, hVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    private static Object m(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, View view, ArrayMap<String, String> arrayMap, h hVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = hVar.a;
        Fragment fragment2 = hVar.f785d;
        if (fragment != null) {
            fragment.requireView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = hVar.b;
        Object u = arrayMap.isEmpty() ? null : u(fragmentTransitionImpl, fragment, fragment2, z);
        ArrayMap<String, View> i2 = i(fragmentTransitionImpl, arrayMap, u, hVar);
        ArrayMap<String, View> h2 = h(fragmentTransitionImpl, arrayMap, u, hVar);
        if (arrayMap.isEmpty()) {
            if (i2 != null) {
                i2.clear();
            }
            if (h2 != null) {
                h2.clear();
            }
            obj3 = null;
        } else {
            a(arrayList, i2, arrayMap.keySet());
            a(arrayList2, h2, arrayMap.values());
            obj3 = u;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        f(fragment, fragment2, z, i2, true);
        if (obj3 != null) {
            arrayList2.add(view);
            fragmentTransitionImpl.setSharedElementTargets(obj3, view, arrayList);
            A(fragmentTransitionImpl, obj3, obj2, i2, hVar.e, hVar.f);
            Rect rect2 = new Rect();
            View t = t(h2, hVar, obj, z);
            if (t != null) {
                fragmentTransitionImpl.setEpicenter(obj, rect2);
            }
            rect = rect2;
            view2 = t;
        } else {
            view2 = null;
            rect = null;
        }
        OneShotPreDrawListener.add(viewGroup, new e(fragment, fragment2, z, h2, view2, fragmentTransitionImpl, rect));
        return obj3;
    }

    private static void n(@NonNull ViewGroup viewGroup, h hVar, View view, ArrayMap<String, String> arrayMap, g gVar) {
        Object obj;
        Fragment fragment = hVar.a;
        Fragment fragment2 = hVar.f785d;
        FragmentTransitionImpl j = j(fragment2, fragment);
        if (j == null) {
            return;
        }
        boolean z = hVar.b;
        boolean z2 = hVar.e;
        Object r = r(j, fragment, z);
        Object s = s(j, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object l = l(j, viewGroup, view, arrayMap, hVar, arrayList, arrayList2, r, s);
        if (r == null && l == null) {
            obj = s;
            if (obj == null) {
                return;
            }
        } else {
            obj = s;
        }
        ArrayList<View> k = k(j, obj, fragment2, arrayList, view);
        if (k == null || k.isEmpty()) {
            obj = null;
        }
        Object obj2 = obj;
        j.addTarget(r, view);
        Object v = v(j, r, obj2, l, fragment, hVar.b);
        if (fragment2 != null && k != null && (k.size() > 0 || arrayList.size() > 0)) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            gVar.b(fragment2, cancellationSignal);
            j.setListenerForTransitionEnd(fragment2, v, cancellationSignal, new c(gVar, fragment2, cancellationSignal));
        }
        if (v != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            j.scheduleRemoveTargets(v, r, arrayList3, obj2, k, l, arrayList2);
            z(j, viewGroup, fragment, view, arrayList2, r, arrayList3, obj2, k);
            j.j(viewGroup, arrayList2, arrayMap);
            j.beginDelayedTransition(viewGroup, v);
            j.i(viewGroup, arrayList2, arrayMap);
        }
    }

    private static void o(@NonNull ViewGroup viewGroup, h hVar, View view, ArrayMap<String, String> arrayMap, g gVar) {
        Object obj;
        Fragment fragment = hVar.a;
        Fragment fragment2 = hVar.f785d;
        FragmentTransitionImpl j = j(fragment2, fragment);
        if (j == null) {
            return;
        }
        boolean z = hVar.b;
        boolean z2 = hVar.e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object r = r(j, fragment, z);
        Object s = s(j, fragment2, z2);
        Object m = m(j, viewGroup, view, arrayMap, hVar, arrayList2, arrayList, r, s);
        if (r == null && m == null) {
            obj = s;
            if (obj == null) {
                return;
            }
        } else {
            obj = s;
        }
        ArrayList<View> k = k(j, obj, fragment2, arrayList2, view);
        ArrayList<View> k2 = k(j, r, fragment, arrayList, view);
        B(k2, 4);
        Object v = v(j, r, obj, m, fragment, z);
        if (fragment2 != null && k != null && (k.size() > 0 || arrayList2.size() > 0)) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            gVar.b(fragment2, cancellationSignal);
            j.setListenerForTransitionEnd(fragment2, v, cancellationSignal, new a(gVar, fragment2, cancellationSignal));
        }
        if (v != null) {
            w(j, obj, fragment2, k);
            ArrayList<String> h2 = j.h(arrayList);
            j.scheduleRemoveTargets(v, r, k2, obj, k, m, arrayList);
            j.beginDelayedTransition(viewGroup, v);
            j.k(viewGroup, arrayList2, arrayList, h2, arrayMap);
            B(k2, 0);
            j.swapSharedElementTargets(m, arrayList2, arrayList);
        }
    }

    private static h p(h hVar, SparseArray<h> sparseArray, int i2) {
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = new h();
        sparseArray.put(i2, hVar2);
        return hVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String q(ArrayMap<String, String> arrayMap, String str) {
        int size = arrayMap.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (str.equals(arrayMap.valueAt(i2))) {
                return arrayMap.keyAt(i2);
            }
        }
        return null;
    }

    private static Object r(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return fragmentTransitionImpl.cloneTransition(z ? fragment.getReenterTransition() : fragment.getEnterTransition());
    }

    private static Object s(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, boolean z) {
        if (fragment == null) {
            return null;
        }
        return fragmentTransitionImpl.cloneTransition(z ? fragment.getReturnTransition() : fragment.getExitTransition());
    }

    static View t(ArrayMap<String, View> arrayMap, h hVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        androidx.fragment.app.a aVar = hVar.f784c;
        if (obj == null || arrayMap == null || (arrayList = aVar.p) == null || arrayList.isEmpty()) {
            return null;
        }
        return arrayMap.get((z ? aVar.p : aVar.q).get(0));
    }

    private static Object u(FragmentTransitionImpl fragmentTransitionImpl, Fragment fragment, Fragment fragment2, boolean z) {
        if (fragment == null || fragment2 == null) {
            return null;
        }
        return fragmentTransitionImpl.wrapTransitionInSet(fragmentTransitionImpl.cloneTransition(z ? fragment2.getSharedElementReturnTransition() : fragment.getSharedElementEnterTransition()));
    }

    private static Object v(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        return (obj == null || obj2 == null || fragment == null) ? true : z ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap() ? fragmentTransitionImpl.mergeTransitionsTogether(obj2, obj, obj3) : fragmentTransitionImpl.mergeTransitionsInSequence(obj2, obj, obj3);
    }

    private static void w(FragmentTransitionImpl fragmentTransitionImpl, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.l && fragment.z && fragment.N) {
            fragment.i0(true);
            fragmentTransitionImpl.scheduleHideFragmentView(obj, fragment.getView(), arrayList);
            OneShotPreDrawListener.add(fragment.G, new b(arrayList));
        }
    }

    private static FragmentTransitionImpl x() {
        try {
            return (FragmentTransitionImpl) Class.forName("androidx.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void y(@NonNull ArrayMap<String, String> arrayMap, @NonNull ArrayMap<String, View> arrayMap2) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            if (!arrayMap2.containsKey(arrayMap.valueAt(size))) {
                arrayMap.removeAt(size);
            }
        }
    }

    private static void z(FragmentTransitionImpl fragmentTransitionImpl, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        OneShotPreDrawListener.add(viewGroup, new d(obj, fragmentTransitionImpl, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }
}
