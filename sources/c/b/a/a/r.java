package c.b.a.a;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import java.io.File;
import java.io.FileOutputStream;

/* loaded from: classes.dex */
public class r {
    private static CameraManager a;
    private static String b;

    public static void a(Activity activity, String str, int i2) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        d.b(str, false);
        FileOutputStream fileOutputStream = null;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            try {
                File file = new File(str);
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    drawingCache.compress(Bitmap.CompressFormat.JPEG, i2, fileOutputStream2);
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                } catch (Exception e2) {
                    fileOutputStream = fileOutputStream2;
                    e = e2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    decorView.setDrawingCacheEnabled(false);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
            }
            decorView.setDrawingCacheEnabled(false);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(android.content.Context r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, java.lang.Object r9, android.content.Intent r10) {
        /*
            java.lang.String r0 = "notification"
            java.lang.Object r0 = r5.getSystemService(r0)
            android.app.NotificationManager r0 = (android.app.NotificationManager) r0
            if (r10 != 0) goto Lc
            r10 = 0
            goto L13
        Lc:
            r1 = 0
            r2 = 134217728(0x8000000, float:3.85186E-34)
            android.app.PendingIntent r10 = android.app.PendingIntent.getActivity(r5, r1, r10, r2)
        L13:
            android.app.Notification$Builder r1 = new android.app.Notification$Builder
            r1.<init>(r5)
            long r2 = java.lang.System.currentTimeMillis()
            android.app.Notification$Builder r1 = r1.setWhen(r2)
            android.app.Notification$Builder r6 = r1.setTicker(r6)
            android.app.Notification$Builder r6 = r6.setContentTitle(r7)
            android.app.Notification$Builder r6 = r6.setContentText(r8)
            r7 = 1
            android.app.Notification$Builder r6 = r6.setAutoCancel(r7)
            int r8 = android.os.Build.VERSION.SDK_INT
            r1 = 26
            if (r8 < r1) goto L47
            android.app.NotificationChannel r1 = new android.app.NotificationChannel
            r2 = 4
            java.lang.String r3 = "i_message"
            java.lang.String r4 = "通知消息"
            r1.<init>(r3, r4, r2)
            r0.createNotificationChannel(r1)
            r6.setChannelId(r3)
        L47:
            r1 = 2131492876(0x7f0c000c, float:1.8609216E38)
            r6.setSmallIcon(r1)
            if (r9 == 0) goto L94
            boolean r2 = r9 instanceof android.graphics.Bitmap
            if (r2 == 0) goto L59
            android.graphics.Bitmap r9 = (android.graphics.Bitmap) r9
            r6.setLargeIcon(r9)
            goto L9f
        L59:
            java.lang.String r9 = java.lang.String.valueOf(r9)
            java.lang.String r2 = "@"
            boolean r2 = r9.startsWith(r2)
            if (r2 == 0) goto L6e
        L65:
            java.lang.String r5 = c.b.a.a.d.p(r5, r9)
            android.graphics.Bitmap r5 = c.b.a.a.i.c(r5)
            goto L9c
        L6e:
            java.lang.String r2 = "%"
            boolean r2 = r9.startsWith(r2)
            if (r2 != 0) goto L65
            java.lang.String r2 = "$"
            boolean r2 = r9.startsWith(r2)
            if (r2 == 0) goto L7f
            goto L65
        L7f:
            java.lang.String r2 = "[0-9]+"
            boolean r2 = r9.matches(r2)
            if (r2 == 0) goto L94
            android.content.res.Resources r5 = r5.getResources()
            int r9 = java.lang.Integer.parseInt(r9)
            android.graphics.Bitmap r5 = android.graphics.BitmapFactory.decodeResource(r5, r9)
            goto L9c
        L94:
            android.content.res.Resources r5 = r5.getResources()
            android.graphics.Bitmap r5 = android.graphics.BitmapFactory.decodeResource(r5, r1)
        L9c:
            r6.setLargeIcon(r5)
        L9f:
            r6.setContentIntent(r10)
            r5 = 16
            if (r8 < r5) goto Lab
            android.app.Notification r5 = r6.build()
            goto Laf
        Lab:
            android.app.Notification r5 = r6.getNotification()
        Laf:
            r0.notify(r7, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.r.b(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.Object, android.content.Intent):void");
    }

    @SuppressLint({"NewApi"})
    public static void c(Activity activity, int i2, boolean z, View view) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 21) {
            if (i3 >= 19) {
                if (!z) {
                    if (view != null) {
                        view.setBackgroundColor(i2);
                    }
                    i2 = 0;
                } else if (view != null) {
                    view.setFitsSystemWindows(true);
                }
                h(activity, true);
                c.e.a.a aVar = new c.e.a.a(activity);
                aVar.e(true);
                aVar.c(true);
                aVar.f(i2);
                return;
            }
            return;
        }
        if (!z) {
            if (view != null) {
                view.setBackgroundColor(i2);
            }
            i2 = 0;
        } else if (view != null) {
            view.setFitsSystemWindows(true);
        }
        Window window = activity.getWindow();
        window.clearFlags(201326592);
        window.getDecorView().setSystemUiVisibility(1792);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(i2);
        window.setNavigationBarColor(i2);
        View childAt = ((ViewGroup) activity.findViewById(R.id.content)).getChildAt(0);
        if (childAt != null) {
            ViewCompat.setFitsSystemWindows(childAt, z);
        }
    }

    @SuppressLint({"NewApi"})
    public static void d(Activity activity, int i2, boolean z, View view, int i3) {
        int i4 = Build.VERSION.SDK_INT;
        if (i4 < 21) {
            if (i4 >= 19) {
                if (!z) {
                    if (view != null) {
                        view.setBackgroundColor(i2);
                    }
                    i2 = 0;
                } else if (view != null) {
                    view.setFitsSystemWindows(true);
                }
                h(activity, true);
                c.e.a.a aVar = new c.e.a.a(activity);
                if (i3 == 0) {
                    aVar.e(true);
                } else if (i3 != 1) {
                    return;
                } else {
                    aVar.c(true);
                }
                aVar.f(i2);
                return;
            }
            return;
        }
        if (!z) {
            if (view != null) {
                view.setBackgroundColor(i2);
            }
            i2 = 0;
        } else if (view != null) {
            view.setFitsSystemWindows(true);
        }
        Window window = activity.getWindow();
        window.clearFlags(201326592);
        window.getDecorView().setSystemUiVisibility(1792);
        if (i3 == 0) {
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(i2);
        } else if (i3 == 1) {
            window.setNavigationBarColor(i2);
        }
        View childAt = ((ViewGroup) activity.findViewById(R.id.content)).getChildAt(0);
        if (childAt != null) {
            ViewCompat.setFitsSystemWindows(childAt, z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void e(android.content.Context r1, android.view.View r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10) {
        /*
            android.content.Context r1 = r1.getApplicationContext()
            java.lang.String r0 = "window"
            java.lang.Object r1 = r1.getSystemService(r0)
            android.view.WindowManager r1 = (android.view.WindowManager) r1
            android.view.WindowManager$LayoutParams r0 = new android.view.WindowManager$LayoutParams
            r0.<init>()
            r0.x = r3
            r0.y = r4
            r3 = -2
            if (r5 != 0) goto L1b
            r0.width = r3
            goto L1d
        L1b:
            r0.width = r5
        L1d:
            if (r6 != 0) goto L22
            r0.height = r3
            goto L24
        L22:
            r0.height = r6
        L24:
            if (r7 != 0) goto L2d
            int r3 = f()
            r0.type = r3
            goto L2f
        L2d:
            r0.type = r7
        L2f:
            if (r8 != 0) goto L36
            r3 = 51
            r0.gravity = r3
            goto L38
        L36:
            r0.gravity = r8
        L38:
            if (r9 != 0) goto L3f
            r3 = 40
        L3c:
            r0.flags = r3
            goto L47
        L3f:
            r3 = 1
            if (r9 != r3) goto L45
            r3 = 32
            goto L3c
        L45:
            r0.flags = r9
        L47:
            if (r10 != 0) goto L4d
            r3 = -3
            r0.format = r3
            goto L4f
        L4d:
            r0.format = r10
        L4f:
            r1.addView(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.r.e(android.content.Context, android.view.View, int, int, int, int, int, int, int, int):void");
    }

    public static int f() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            return 2038;
        }
        return (i2 < 25 && i2 >= 23) ? 2005 : 2003;
    }

    @RequiresApi(23)
    public static String g(CameraManager cameraManager, int i2) {
        try {
            for (String str : cameraManager.getCameraIdList()) {
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
                Boolean bool = (Boolean) cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                if (bool != null && bool.booleanValue() && num != null && num.intValue() == i2) {
                    return str;
                }
            }
            return null;
        } catch (CameraAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @TargetApi(19)
    private static void h(Activity activity, boolean z) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.flags = z ? attributes.flags | 67108864 : attributes.flags & (-67108865);
        window.setAttributes(attributes);
    }

    @RequiresApi(23)
    public static CameraManager i(Context context, boolean z) {
        if (a == null) {
            a = (CameraManager) context.getSystemService("camera");
        }
        if (b == null) {
            b = g(a, 1);
        }
        String str = b;
        if (str != null) {
            try {
                a.setTorchMode(str, z);
                return a;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return a;
    }
}
