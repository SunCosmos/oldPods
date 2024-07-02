package adrt;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: lib/Mus.dex */
public class ADRTLogCatReader implements Runnable {
    private static Context context;

    public static void onContext(Context context2, String str) {
        if (context == null) {
            context = context2.getApplicationContext();
            if (0 != (context2.getApplicationInfo().flags & 2)) {
                try {
                    context2.getPackageManager().getPackageInfo(str, 128);
                    ADRTSender.onContext(context, str);
                    new Thread(new ADRTLogCatReader(), "LogCat").start();
                } catch (PackageManager.NameNotFoundException e) {
                }
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -v threadtime").getInputStream()), 20);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    ADRTSender.sendLogcatLines(new String[]{readLine});
                } else {
                    return;
                }
            }
        } catch (IOException e) {
        }
    }
}
