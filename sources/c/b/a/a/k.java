package c.b.a.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class k {
    public static void a(Context context, Intent intent, File file, String str) {
        Uri fromFile;
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setFlags(1);
            fromFile = FileProvider.getUriForFile(context, String.format("%s.myFileProvider", context.getPackageName()), file);
        } else {
            intent.addFlags(268435456);
            fromFile = Uri.fromFile(file);
        }
        intent.setDataAndType(fromFile, str);
    }

    public static Uri b(Context context, File file) {
        return Build.VERSION.SDK_INT >= 24 ? FileProvider.getUriForFile(context, String.format("%s.myFileProvider", context.getPackageName()), file) : Uri.fromFile(file);
    }

    public static Uri c(Context context, String str) {
        String lowerCase = str.toLowerCase();
        if ((lowerCase.startsWith("file://") || lowerCase.startsWith("/")) && Build.VERSION.SDK_INT >= 24) {
            try {
                return FileProvider.getUriForFile(context, String.format("%s.myFileProvider", context.getPackageName()), new File(str));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Uri.parse(str);
    }

    public static boolean d(Activity activity, String[] strArr) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(activity, str) != 0 || ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.size() <= 0) {
            return false;
        }
        ActivityCompat.requestPermissions(activity, (String[]) arrayList.toArray(new String[arrayList.size()]), 60542);
        return true;
    }
}
