package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.util.SparseArray;

@Deprecated
/* loaded from: lib/Mus.dex */
public abstract class WakefulBroadcastReceiver extends BroadcastReceiver {
    private static final String EXTRA_WAKE_LOCK_ID = "android.support.content.wakelockid";
    private static final SparseArray<PowerManager.WakeLock> sActiveWakeLocks = new SparseArray<>();
    private static int mNextId = 1;

    public static ComponentName startWakefulService(Context context, Intent intent) {
        synchronized (sActiveWakeLocks) {
            int i2 = mNextId;
            mNextId++;
            if (mNextId <= 0) {
                mNextId = 1;
            }
            intent.putExtra(EXTRA_WAKE_LOCK_ID, i2);
            ComponentName startService = context.startService(intent);
            if (startService == null) {
                return null;
            }
            PowerManager.WakeLock newWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "wake:" + startService.flattenToShortString());
            newWakeLock.setReferenceCounted(false);
            newWakeLock.acquire(60000L);
            sActiveWakeLocks.put(i2, newWakeLock);
            return startService;
        }
    }

    public static boolean completeWakefulIntent(Intent intent) {
        boolean z;
        int intExtra = intent.getIntExtra(EXTRA_WAKE_LOCK_ID, 0);
        if (intExtra == 0) {
            return false;
        }
        synchronized (sActiveWakeLocks) {
            PowerManager.WakeLock wakeLock = sActiveWakeLocks.get(intExtra);
            if (wakeLock != null) {
                wakeLock.release();
                sActiveWakeLocks.remove(intExtra);
                z = true;
            } else {
                Log.w("WakefulBroadcastReceiv.", "No active wake lock id #" + intExtra);
                z = true;
            }
        }
        return z;
    }
}
