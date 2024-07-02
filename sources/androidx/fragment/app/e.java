package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class e {

    @NonNull
    private final CopyOnWriteArrayList<a> a = new CopyOnWriteArrayList<>();

    @NonNull
    private final FragmentManager b;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class a {

        @NonNull
        final FragmentManager.FragmentLifecycleCallbacks a;
        final boolean b;

        a(@NonNull FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
            this.a = fragmentLifecycleCallbacks;
            this.b = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(@NonNull FragmentManager fragmentManager) {
        this.b = fragmentManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().a(fragment, bundle, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentActivityCreated(this.b, fragment, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(@NonNull Fragment fragment, boolean z) {
        Context b = this.b.n0().b();
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().b(fragment, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentAttached(this.b, fragment, b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().c(fragment, bundle, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentCreated(this.b, fragment, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().d(fragment, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentDestroyed(this.b, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().e(fragment, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentDetached(this.b, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().f(fragment, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentPaused(this.b, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(@NonNull Fragment fragment, boolean z) {
        Context b = this.b.n0().b();
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().g(fragment, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentPreAttached(this.b, fragment, b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(@NonNull Fragment fragment, @Nullable Bundle bundle, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().h(fragment, bundle, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentPreCreated(this.b, fragment, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().i(fragment, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentResumed(this.b, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(@NonNull Fragment fragment, @NonNull Bundle bundle, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().j(fragment, bundle, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentSaveInstanceState(this.b, fragment, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().k(fragment, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentStarted(this.b, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().l(fragment, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentStopped(this.b, fragment);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(@NonNull Fragment fragment, @NonNull View view, @Nullable Bundle bundle, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().m(fragment, view, bundle, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentViewCreated(this.b, fragment, view, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(@NonNull Fragment fragment, boolean z) {
        Fragment q0 = this.b.q0();
        if (q0 != null) {
            q0.getParentFragmentManager().p0().n(fragment, true);
        }
        Iterator<a> it = this.a.iterator();
        while (it.hasNext()) {
            a next = it.next();
            if (!z || next.b) {
                next.a.onFragmentViewDestroyed(this.b, fragment);
            }
        }
    }

    public void o(@NonNull FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
        this.a.add(new a(fragmentLifecycleCallbacks, z));
    }

    public void p(@NonNull FragmentManager.FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        synchronized (this.a) {
            int i2 = 0;
            int size = this.a.size();
            while (true) {
                if (i2 >= size) {
                    break;
                }
                if (this.a.get(i2).a == fragmentLifecycleCallbacks) {
                    this.a.remove(i2);
                    break;
                }
                i2++;
            }
        }
    }
}
