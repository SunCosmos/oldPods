package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class i {
    private final ArrayList<Fragment> a = new ArrayList<>();
    private final HashMap<String, h> b = new HashMap<>();

    /* renamed from: c */
    private g f773c;

    public void a(@NonNull Fragment fragment) {
        if (this.a.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.a) {
            this.a.add(fragment);
        }
        fragment.l = true;
    }

    public void b() {
        this.b.values().removeAll(Collections.singleton(null));
    }

    public boolean c(@NonNull String str) {
        return this.b.get(str) != null;
    }

    public void d(int i2) {
        for (h hVar : this.b.values()) {
            if (hVar != null) {
                hVar.u(i2);
            }
        }
    }

    public void e(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        String str2 = str + "    ";
        if (!this.b.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (h hVar : this.b.values()) {
                printWriter.print(str);
                if (hVar != null) {
                    Fragment k = hVar.k();
                    printWriter.println(k);
                    k.dump(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size = this.a.size();
        if (size > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size; i2++) {
                Fragment fragment = this.a.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
    }

    @Nullable
    public Fragment f(@NonNull String str) {
        h hVar = this.b.get(str);
        if (hVar != null) {
            return hVar.k();
        }
        return null;
    }

    @Nullable
    public Fragment g(@IdRes int i2) {
        for (int size = this.a.size() - 1; size >= 0; size--) {
            Fragment fragment = this.a.get(size);
            if (fragment != null && fragment.w == i2) {
                return fragment;
            }
        }
        for (h hVar : this.b.values()) {
            if (hVar != null) {
                Fragment k = hVar.k();
                if (k.w == i2) {
                    return k;
                }
            }
        }
        return null;
    }

    @Nullable
    public Fragment h(@Nullable String str) {
        if (str != null) {
            for (int size = this.a.size() - 1; size >= 0; size--) {
                Fragment fragment = this.a.get(size);
                if (fragment != null && str.equals(fragment.y)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (h hVar : this.b.values()) {
            if (hVar != null) {
                Fragment k = hVar.k();
                if (str.equals(k.y)) {
                    return k;
                }
            }
        }
        return null;
    }

    @Nullable
    public Fragment i(@NonNull String str) {
        Fragment d2;
        for (h hVar : this.b.values()) {
            if (hVar != null && (d2 = hVar.k().d(str)) != null) {
                return d2;
            }
        }
        return null;
    }

    public int j(@NonNull Fragment fragment) {
        View view;
        View view2;
        ViewGroup viewGroup = fragment.G;
        if (viewGroup == null) {
            return -1;
        }
        int indexOf = this.a.indexOf(fragment);
        for (int i2 = indexOf - 1; i2 >= 0; i2--) {
            Fragment fragment2 = this.a.get(i2);
            if (fragment2.G == viewGroup && (view2 = fragment2.H) != null) {
                return viewGroup.indexOfChild(view2) + 1;
            }
        }
        while (true) {
            indexOf++;
            if (indexOf >= this.a.size()) {
                return -1;
            }
            Fragment fragment3 = this.a.get(indexOf);
            if (fragment3.G == viewGroup && (view = fragment3.H) != null) {
                return viewGroup.indexOfChild(view);
            }
        }
    }

    public int k() {
        return this.b.size();
    }

    @NonNull
    public List<h> l() {
        ArrayList arrayList = new ArrayList();
        for (h hVar : this.b.values()) {
            if (hVar != null) {
                arrayList.add(hVar);
            }
        }
        return arrayList;
    }

    @NonNull
    public List<Fragment> m() {
        ArrayList arrayList = new ArrayList();
        Iterator<h> it = this.b.values().iterator();
        while (it.hasNext()) {
            h next = it.next();
            arrayList.add(next != null ? next.k() : null);
        }
        return arrayList;
    }

    @Nullable
    public h n(@NonNull String str) {
        return this.b.get(str);
    }

    @NonNull
    public List<Fragment> o() {
        ArrayList arrayList;
        if (this.a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.a) {
            arrayList = new ArrayList(this.a);
        }
        return arrayList;
    }

    public g p() {
        return this.f773c;
    }

    public void q(@NonNull h hVar) {
        Fragment k = hVar.k();
        if (c(k.f)) {
            return;
        }
        this.b.put(k.f, hVar);
        if (k.C) {
            if (k.B) {
                this.f773c.f(k);
            } else {
                this.f773c.o(k);
            }
            k.C = false;
        }
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + k);
        }
    }

    public void r(@NonNull h hVar) {
        Fragment k = hVar.k();
        if (k.B) {
            this.f773c.o(k);
        }
        if (this.b.put(k.f, null) != null && FragmentManager.x0(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + k);
        }
    }

    public void s() {
        Iterator<Fragment> it = this.a.iterator();
        while (it.hasNext()) {
            h hVar = this.b.get(it.next().f);
            if (hVar != null) {
                hVar.m();
            }
        }
        for (h hVar2 : this.b.values()) {
            if (hVar2 != null) {
                hVar2.m();
                Fragment k = hVar2.k();
                if (k.m && !k.x()) {
                    r(hVar2);
                }
            }
        }
    }

    public void t(@NonNull Fragment fragment) {
        synchronized (this.a) {
            this.a.remove(fragment);
        }
        fragment.l = false;
    }

    public void u() {
        this.b.clear();
    }

    public void v(@Nullable List<String> list) {
        this.a.clear();
        if (list != null) {
            for (String str : list) {
                Fragment f = f(str);
                if (f == null) {
                    throw new IllegalStateException("No instantiated fragment for (" + str + ")");
                }
                if (FragmentManager.x0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + f);
                }
                a(f);
            }
        }
    }

    @NonNull
    public ArrayList<FragmentState> w() {
        ArrayList<FragmentState> arrayList = new ArrayList<>(this.b.size());
        for (h hVar : this.b.values()) {
            if (hVar != null) {
                Fragment k = hVar.k();
                FragmentState s = hVar.s();
                arrayList.add(s);
                if (FragmentManager.x0(2)) {
                    Log.v("FragmentManager", "Saved state of " + k + ": " + s.m);
                }
            }
        }
        return arrayList;
    }

    @Nullable
    public ArrayList<String> x() {
        synchronized (this.a) {
            if (this.a.isEmpty()) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>(this.a.size());
            Iterator<Fragment> it = this.a.iterator();
            while (it.hasNext()) {
                Fragment next = it.next();
                arrayList.add(next.f);
                if (FragmentManager.x0(2)) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + next.f + "): " + next);
                }
            }
            return arrayList;
        }
    }

    public void y(@NonNull g gVar) {
        this.f773c = gVar;
    }
}
