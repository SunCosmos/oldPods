package androidx.activity;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public abstract class OnBackPressedCallback {
    private boolean a;
    private CopyOnWriteArrayList<a> b = new CopyOnWriteArrayList<>();

    public OnBackPressedCallback(boolean z) {
        this.a = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(@NonNull a aVar) {
        this.b.add(aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(@NonNull a aVar) {
        this.b.remove(aVar);
    }

    @MainThread
    public abstract void handleOnBackPressed();

    @MainThread
    public final boolean isEnabled() {
        return this.a;
    }

    @MainThread
    public final void remove() {
        Iterator<a> it = this.b.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
    }

    @MainThread
    public final void setEnabled(boolean z) {
        this.a = z;
    }
}
