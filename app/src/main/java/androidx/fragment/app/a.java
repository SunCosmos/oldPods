package androidx.fragment.app;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import java.io.PrintWriter;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class a extends FragmentTransaction implements FragmentManager.BackStackEntry, FragmentManager.m {
    final FragmentManager t;
    boolean u;
    int v;

    public a(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager.getFragmentFactory(), fragmentManager.n0() != null ? fragmentManager.n0().b().getClassLoader() : null);
        this.v = -1;
        this.t = fragmentManager;
    }

    private static boolean o(FragmentTransaction.a aVar) {
        Fragment fragment = aVar.b;
        return (fragment == null || !fragment.l || fragment.H == null || fragment.A || fragment.z || !fragment.y()) ? false : true;
    }

    @Override // androidx.fragment.app.FragmentManager.m
    public boolean a(@NonNull ArrayList<a> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.f745i) {
            return true;
        }
        this.t.e(this);
        return true;
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public int commit() {
        return g(false);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public int commitAllowingStateLoss() {
        return g(true);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public void commitNow() {
        disallowAddToBackStack();
        this.t.Y(this, false);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public void commitNowAllowingStateLoss() {
        disallowAddToBackStack();
        this.t.Y(this, true);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction detach(@NonNull Fragment fragment) {
        FragmentManager fragmentManager = fragment.s;
        if (fragmentManager == null || fragmentManager == this.t) {
            return super.detach(fragment);
        }
        throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public void e(int i2, Fragment fragment, @Nullable String str, int i3) {
        super.e(i2, fragment, str, i3);
        fragment.s = this.t;
    }

    public void f(int i2) {
        if (this.f745i) {
            if (FragmentManager.x0(2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i2);
            }
            int size = this.f743c.size();
            for (int i3 = 0; i3 < size; i3++) {
                FragmentTransaction.a aVar = this.f743c.get(i3);
                Fragment fragment = aVar.b;
                if (fragment != null) {
                    fragment.r += i2;
                    if (FragmentManager.x0(2)) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.b + " to " + aVar.b.r);
                    }
                }
            }
        }
    }

    int g(boolean z) {
        if (this.u) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManager.x0(2)) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new m("FragmentManager"));
            h("  ", printWriter);
            printWriter.close();
        }
        this.u = true;
        this.v = this.f745i ? this.t.i() : -1;
        this.t.V(this, z);
        return this.v;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    @Nullable
    public CharSequence getBreadCrumbShortTitle() {
        return this.n != 0 ? this.t.n0().b().getText(this.n) : this.o;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    public int getBreadCrumbShortTitleRes() {
        return this.n;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    @Nullable
    public CharSequence getBreadCrumbTitle() {
        return this.l != 0 ? this.t.n0().b().getText(this.l) : this.m;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    public int getBreadCrumbTitleRes() {
        return this.l;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    public int getId() {
        return this.v;
    }

    @Override // androidx.fragment.app.FragmentManager.BackStackEntry
    @Nullable
    public String getName() {
        return this.k;
    }

    public void h(String str, PrintWriter printWriter) {
        i(str, printWriter, true);
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction hide(@NonNull Fragment fragment) {
        FragmentManager fragmentManager = fragment.s;
        if (fragmentManager == null || fragmentManager == this.t) {
            return super.hide(fragment);
        }
        throw new IllegalStateException("Cannot hide Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    public void i(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.k);
            printWriter.print(" mIndex=");
            printWriter.print(this.v);
            printWriter.print(" mCommitted=");
            printWriter.println(this.u);
            if (this.h != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.h));
            }
            if (this.f744d != 0 || this.e != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f744d));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.e));
            }
            if (this.f != 0 || this.g != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.g));
            }
            if (this.l != 0 || this.m != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.l));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.m);
            }
            if (this.n != 0 || this.o != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.n));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.o);
            }
        }
        if (this.f743c.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f743c.size();
        for (int i2 = 0; i2 < size; i2++) {
            FragmentTransaction.a aVar = this.f743c.get(i2);
            switch (aVar.a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + aVar.a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i2);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(aVar.b);
            if (z) {
                if (aVar.f746c != 0 || aVar.f747d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f746c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f747d));
                }
                if (aVar.e != 0 || aVar.f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f));
                }
            }
        }
    }

    @Override // androidx.fragment.app.FragmentTransaction
    public boolean isEmpty() {
        return this.f743c.isEmpty();
    }

    public void j() {
        int size = this.f743c.size();
        for (int i2 = 0; i2 < size; i2++) {
            FragmentTransaction.a aVar = this.f743c.get(i2);
            Fragment fragment = aVar.b;
            if (fragment != null) {
                fragment.l0(false);
                fragment.j0(this.h);
                fragment.n0(this.p, this.q);
            }
            switch (aVar.a) {
                case 1:
                    fragment.f0(aVar.f746c, aVar.f747d, aVar.e, aVar.f);
                    this.t.b1(fragment, false);
                    this.t.g(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.a);
                case 3:
                    fragment.f0(aVar.f746c, aVar.f747d, aVar.e, aVar.f);
                    this.t.R0(fragment);
                    break;
                case 4:
                    fragment.f0(aVar.f746c, aVar.f747d, aVar.e, aVar.f);
                    this.t.v0(fragment);
                    break;
                case 5:
                    fragment.f0(aVar.f746c, aVar.f747d, aVar.e, aVar.f);
                    this.t.b1(fragment, false);
                    this.t.f1(fragment);
                    break;
                case 6:
                    fragment.f0(aVar.f746c, aVar.f747d, aVar.e, aVar.f);
                    this.t.v(fragment);
                    break;
                case 7:
                    fragment.f0(aVar.f746c, aVar.f747d, aVar.e, aVar.f);
                    this.t.b1(fragment, false);
                    this.t.k(fragment);
                    break;
                case 8:
                    this.t.d1(fragment);
                    break;
                case 9:
                    this.t.d1(null);
                    break;
                case 10:
                    this.t.c1(fragment, aVar.h);
                    break;
            }
            if (!this.r && aVar.a != 1 && fragment != null && !FragmentManager.P) {
                this.t.G0(fragment);
            }
        }
        if (this.r || FragmentManager.P) {
            return;
        }
        FragmentManager fragmentManager = this.t;
        fragmentManager.H0(fragmentManager.q, true);
    }

    public void k(boolean z) {
        for (int size = this.f743c.size() - 1; size >= 0; size--) {
            FragmentTransaction.a aVar = this.f743c.get(size);
            Fragment fragment = aVar.b;
            if (fragment != null) {
                fragment.l0(true);
                fragment.j0(FragmentManager.Y0(this.h));
                fragment.n0(this.q, this.p);
            }
            switch (aVar.a) {
                case 1:
                    fragment.f0(aVar.f746c, aVar.f747d, aVar.e, aVar.f);
                    this.t.b1(fragment, true);
                    this.t.R0(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.a);
                case 3:
                    fragment.f0(aVar.f746c, aVar.f747d, aVar.e, aVar.f);
                    this.t.g(fragment);
                    break;
                case 4:
                    fragment.f0(aVar.f746c, aVar.f747d, aVar.e, aVar.f);
                    this.t.f1(fragment);
                    break;
                case 5:
                    fragment.f0(aVar.f746c, aVar.f747d, aVar.e, aVar.f);
                    this.t.b1(fragment, true);
                    this.t.v0(fragment);
                    break;
                case 6:
                    fragment.f0(aVar.f746c, aVar.f747d, aVar.e, aVar.f);
                    this.t.k(fragment);
                    break;
                case 7:
                    fragment.f0(aVar.f746c, aVar.f747d, aVar.e, aVar.f);
                    this.t.b1(fragment, true);
                    this.t.v(fragment);
                    break;
                case 8:
                    this.t.d1(null);
                    break;
                case 9:
                    this.t.d1(fragment);
                    break;
                case 10:
                    this.t.c1(fragment, aVar.g);
                    break;
            }
            if (!this.r && aVar.a != 3 && fragment != null && !FragmentManager.P) {
                this.t.G0(fragment);
            }
        }
        if (this.r || !z || FragmentManager.P) {
            return;
        }
        FragmentManager fragmentManager = this.t;
        fragmentManager.H0(fragmentManager.q, true);
    }

    public Fragment l(ArrayList<Fragment> arrayList, Fragment fragment) {
        Fragment fragment2 = fragment;
        int i2 = 0;
        while (i2 < this.f743c.size()) {
            FragmentTransaction.a aVar = this.f743c.get(i2);
            int i3 = aVar.a;
            if (i3 != 1) {
                if (i3 == 2) {
                    Fragment fragment3 = aVar.b;
                    int i4 = fragment3.x;
                    boolean z = false;
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        Fragment fragment4 = arrayList.get(size);
                        if (fragment4.x == i4) {
                            if (fragment4 == fragment3) {
                                z = true;
                            } else {
                                if (fragment4 == fragment2) {
                                    this.f743c.add(i2, new FragmentTransaction.a(9, fragment4));
                                    i2++;
                                    fragment2 = null;
                                }
                                FragmentTransaction.a aVar2 = new FragmentTransaction.a(3, fragment4);
                                aVar2.f746c = aVar.f746c;
                                aVar2.e = aVar.e;
                                aVar2.f747d = aVar.f747d;
                                aVar2.f = aVar.f;
                                this.f743c.add(i2, aVar2);
                                arrayList.remove(fragment4);
                                i2++;
                            }
                        }
                    }
                    if (z) {
                        this.f743c.remove(i2);
                        i2--;
                    } else {
                        aVar.a = 1;
                        arrayList.add(fragment3);
                    }
                } else if (i3 == 3 || i3 == 6) {
                    arrayList.remove(aVar.b);
                    Fragment fragment5 = aVar.b;
                    if (fragment5 == fragment2) {
                        this.f743c.add(i2, new FragmentTransaction.a(9, fragment5));
                        i2++;
                        fragment2 = null;
                    }
                } else if (i3 != 7) {
                    if (i3 == 8) {
                        this.f743c.add(i2, new FragmentTransaction.a(9, fragment2));
                        i2++;
                        fragment2 = aVar.b;
                    }
                }
                i2++;
            }
            arrayList.add(aVar.b);
            i2++;
        }
        return fragment2;
    }

    public boolean m(int i2) {
        int size = this.f743c.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = this.f743c.get(i3).b;
            int i4 = fragment != null ? fragment.x : 0;
            if (i4 != 0 && i4 == i2) {
                return true;
            }
        }
        return false;
    }

    public boolean n(ArrayList<a> arrayList, int i2, int i3) {
        if (i3 == i2) {
            return false;
        }
        int size = this.f743c.size();
        int i4 = -1;
        for (int i5 = 0; i5 < size; i5++) {
            Fragment fragment = this.f743c.get(i5).b;
            int i6 = fragment != null ? fragment.x : 0;
            if (i6 != 0 && i6 != i4) {
                for (int i7 = i2; i7 < i3; i7++) {
                    a aVar = arrayList.get(i7);
                    int size2 = aVar.f743c.size();
                    for (int i8 = 0; i8 < size2; i8++) {
                        Fragment fragment2 = aVar.f743c.get(i8).b;
                        if ((fragment2 != null ? fragment2.x : 0) == i6) {
                            return true;
                        }
                    }
                }
                i4 = i6;
            }
        }
        return false;
    }

    public boolean p() {
        for (int i2 = 0; i2 < this.f743c.size(); i2++) {
            if (o(this.f743c.get(i2))) {
                return true;
            }
        }
        return false;
    }

    public void q() {
        if (this.s != null) {
            for (int i2 = 0; i2 < this.s.size(); i2++) {
                this.s.get(i2).run();
            }
            this.s = null;
        }
    }

    public void r(Fragment.k kVar) {
        for (int i2 = 0; i2 < this.f743c.size(); i2++) {
            FragmentTransaction.a aVar = this.f743c.get(i2);
            if (o(aVar)) {
                aVar.b.k0(kVar);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction remove(@NonNull Fragment fragment) {
        FragmentManager fragmentManager = fragment.s;
        if (fragmentManager == null || fragmentManager == this.t) {
            return super.remove(fragment);
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    public Fragment s(ArrayList<Fragment> arrayList, Fragment fragment) {
        for (int size = this.f743c.size() - 1; size >= 0; size--) {
            FragmentTransaction.a aVar = this.f743c.get(size);
            int i2 = aVar.a;
            if (i2 != 1) {
                if (i2 != 3) {
                    switch (i2) {
                        case 8:
                            fragment = null;
                            break;
                        case 9:
                            fragment = aVar.b;
                            break;
                        case 10:
                            aVar.h = aVar.g;
                            break;
                    }
                }
                arrayList.add(aVar.b);
            }
            arrayList.remove(aVar.b);
        }
        return fragment;
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction setMaxLifecycle(@NonNull Fragment fragment, @NonNull Lifecycle.State state) {
        if (fragment.s != this.t) {
            throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + this.t);
        }
        if (state == Lifecycle.State.INITIALIZED && fragment.a > -1) {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + state + " after the Fragment has been created");
        }
        if (state != Lifecycle.State.DESTROYED) {
            return super.setMaxLifecycle(fragment, state);
        }
        throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + state + ". Use remove() to remove the fragment from the FragmentManager and trigger its destruction.");
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction setPrimaryNavigationFragment(@Nullable Fragment fragment) {
        FragmentManager fragmentManager;
        if (fragment == null || (fragmentManager = fragment.s) == null || fragmentManager == this.t) {
            return super.setPrimaryNavigationFragment(fragment);
        }
        throw new IllegalStateException("Cannot setPrimaryNavigation for Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.FragmentTransaction
    @NonNull
    public FragmentTransaction show(@NonNull Fragment fragment) {
        FragmentManager fragmentManager = fragment.s;
        if (fragmentManager == null || fragmentManager == this.t) {
            return super.show(fragment);
        }
        throw new IllegalStateException("Cannot show Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.v >= 0) {
            sb.append(" #");
            sb.append(this.v);
        }
        if (this.k != null) {
            sb.append(" ");
            sb.append(this.k);
        }
        sb.append("}");
        return sb.toString();
    }
}
