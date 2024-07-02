package android.support.transition;

import android.os.IBinder;
import android.support.annotation.RequiresApi;

@RequiresApi(14)
/* loaded from: lib/Mus.dex */
class WindowIdApi14 implements WindowIdImpl {
    private final IBinder mToken;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WindowIdApi14(IBinder iBinder) {
        this.mToken = iBinder;
    }

    public boolean equals(Object obj) {
        return (obj instanceof WindowIdApi14) && ((WindowIdApi14) obj).mToken.equals(this.mToken);
    }

    public int hashCode() {
        return this.mToken.hashCode();
    }
}
