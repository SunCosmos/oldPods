package pl.droidsonroids.gif;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: classes.dex */
final class e extends ScheduledThreadPoolExecutor {

    /* loaded from: classes.dex */
    private static final class b {
        private static final e a = new e();
    }

    private e() {
        super(1, new ThreadPoolExecutor.DiscardPolicy());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static e a() {
        return b.a;
    }
}
