package pl.droidsonroids.gif;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class m extends n {
    /* JADX INFO: Access modifiers changed from: package-private */
    public m(c cVar) {
        super(cVar);
    }

    @Override // pl.droidsonroids.gif.n
    public void a() {
        c cVar = this.a;
        long s = cVar.g.s(cVar.f);
        if (s >= 0) {
            this.a.f2066c = SystemClock.uptimeMillis() + s;
            if (this.a.isVisible() && this.a.b) {
                c cVar2 = this.a;
                if (!cVar2.l) {
                    cVar2.a.remove(this);
                    c cVar3 = this.a;
                    cVar3.p = cVar3.a.schedule(this, s, TimeUnit.MILLISECONDS);
                }
            }
            if (!this.a.h.isEmpty() && this.a.b() == this.a.g.j() - 1) {
                c cVar4 = this.a;
                cVar4.m.sendEmptyMessageAtTime(cVar4.c(), this.a.f2066c);
            }
        } else {
            c cVar5 = this.a;
            cVar5.f2066c = Long.MIN_VALUE;
            cVar5.b = false;
        }
        if (!this.a.isVisible() || this.a.m.hasMessages(-1)) {
            return;
        }
        this.a.m.sendEmptyMessageAtTime(-1, 0L);
    }
}
