package pl.droidsonroids.gif;

/* loaded from: classes.dex */
class b {
    private volatile boolean a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a() {
        while (!this.a) {
            wait();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void b() {
        this.a = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void c() {
        boolean z = this.a;
        this.a = true;
        if (!z) {
            notify();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void d(boolean z) {
        if (z) {
            c();
        } else {
            b();
        }
    }
}
