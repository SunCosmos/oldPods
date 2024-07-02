package c.b.a.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Environment;
import com.iapp.app.logoActivity;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class d {
    private static String a = Environment.getExternalStorageDirectory().toString();
    private static String b = null;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:64:0x004f -> B:27:0x007a). Please report as a decompilation issue!!! */
    public static boolean a(String str, String str2, Boolean bool) {
        Exception e;
        FileOutputStream fileOutputStream;
        boolean z = false;
        b(str2, false);
        File file = new File(str2);
        ?? file2 = new File(str);
        if (!file2.exists()) {
            return false;
        }
        if (file.exists()) {
            if (!bool.booleanValue()) {
                return false;
            }
            file.delete();
        }
        try {
            try {
                try {
                    bool = new FileInputStream((File) file2);
                } catch (Exception e2) {
                    bool = 0;
                    e = e2;
                    fileOutputStream = null;
                } catch (Throwable th) {
                    bool = 0;
                    th = th;
                    file2 = 0;
                }
                try {
                    file.createNewFile();
                    fileOutputStream = new FileOutputStream(file);
                } catch (Exception e3) {
                    fileOutputStream = null;
                    e = e3;
                } catch (Throwable th2) {
                    file2 = 0;
                    th = th2;
                    if (file2 != 0) {
                        try {
                            file2.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                    if (bool == 0) {
                        throw th;
                    }
                    try {
                        bool.close();
                        throw th;
                    } catch (Exception e5) {
                        e5.printStackTrace();
                        throw th;
                    }
                }
                try {
                    byte[] bArr = new byte[512];
                    while (true) {
                        int read = bool.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    z = true;
                    try {
                        fileOutputStream.close();
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                    bool.close();
                } catch (Exception e7) {
                    e = e7;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Exception e8) {
                            e8.printStackTrace();
                        }
                    }
                    if (bool != 0) {
                        bool.close();
                    }
                    return z;
                }
            } catch (Exception e9) {
                e9.printStackTrace();
            }
            return z;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static boolean b(String str, boolean z) {
        File file;
        if (z) {
            file = new File(str);
        } else {
            String parent = new File(str).getParent();
            parent.getClass();
            file = new File(parent);
        }
        return file.mkdirs();
    }

    public static String c(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
        if (packageArchiveInfo != null) {
            return packageArchiveInfo.packageName;
        }
        return null;
    }

    public static String d(Context context, String str) {
        String c2;
        File file = new File(str);
        if (!file.exists() || (c2 = c(context, str)) == null) {
            return null;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        k.a(context, intent, file, "application/vnd.android.package-archive");
        context.startActivity(intent);
        return c2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r5v8 */
    public static boolean e(Context context, String str, String str2, boolean z) {
        Throwable th;
        FileOutputStream fileOutputStream;
        Exception e;
        File file = new File(str2);
        boolean z2 = false;
        if (file.exists()) {
            if (!z) {
                return false;
            }
            file.delete();
        }
        b(str2, false);
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            try {
                context = context.getAssets().open(str);
            } catch (Exception e3) {
                fileOutputStream = null;
                e = e3;
                context = 0;
            } catch (Throwable th2) {
                str = 0;
                th = th2;
                context = 0;
            }
            try {
                file.createNewFile();
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception e4) {
                e = e4;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                str = 0;
                if (str != 0) {
                    try {
                        str.close();
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                }
                if (context == 0) {
                    throw th;
                }
                try {
                    context.close();
                    throw th;
                } catch (Exception e6) {
                    e6.printStackTrace();
                    throw th;
                }
            }
            try {
                byte[] bArr = new byte[512];
                while (true) {
                    int read = context.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                z2 = true;
                try {
                    fileOutputStream.close();
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
            } catch (Exception e8) {
                e = e8;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e9) {
                        e9.printStackTrace();
                    }
                }
                if (context != 0) {
                    context.close();
                }
                return z2;
            }
            if (context != 0) {
                context.close();
            }
            return z2;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static String f(Context context, String str) {
        return g(context, str, "utf-8");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0067 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x005d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String g(android.content.Context r4, java.lang.String r5, java.lang.String r6) {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 0
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch: java.lang.Exception -> L70
            java.io.InputStream r4 = r4.open(r5)     // Catch: java.lang.Exception -> L70
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            r5.<init>(r4, r6)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            r6 = 512(0x200, float:7.17E-43)
            char[] r6 = new char[r6]     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L59
        L17:
            int r2 = r5.read(r6)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L59
            if (r2 <= 0) goto L22
            r3 = 0
            r0.append(r6, r3, r2)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L59
            goto L17
        L22:
            java.lang.String r6 = new java.lang.String     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L59
            r6.<init>(r0)     // Catch: java.lang.Exception -> L3b java.lang.Throwable -> L59
            if (r4 == 0) goto L31
            r4.close()     // Catch: java.lang.Exception -> L2d
            goto L31
        L2d:
            r4 = move-exception
            r4.printStackTrace()
        L31:
            r5.close()     // Catch: java.lang.Exception -> L35
            goto L39
        L35:
            r4 = move-exception
            r4.printStackTrace()
        L39:
            r1 = r6
            goto L58
        L3b:
            r6 = move-exception
            goto L41
        L3d:
            r6 = move-exception
            goto L5b
        L3f:
            r6 = move-exception
            r5 = r1
        L41:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L59
            if (r4 == 0) goto L4e
            r4.close()     // Catch: java.lang.Exception -> L4a
            goto L4e
        L4a:
            r4 = move-exception
            r4.printStackTrace()
        L4e:
            if (r5 == 0) goto L58
            r5.close()     // Catch: java.lang.Exception -> L54
            goto L58
        L54:
            r4 = move-exception
            r4.printStackTrace()
        L58:
            return r1
        L59:
            r6 = move-exception
            r1 = r5
        L5b:
            if (r4 == 0) goto L65
            r4.close()     // Catch: java.lang.Exception -> L61
            goto L65
        L61:
            r4 = move-exception
            r4.printStackTrace()
        L65:
            if (r1 == 0) goto L6f
            r1.close()     // Catch: java.lang.Exception -> L6b
            goto L6f
        L6b:
            r4 = move-exception
            r4.printStackTrace()
        L6f:
            throw r6
        L70:
            r4 = move-exception
            r4.printStackTrace()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.d.g(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }

    public static String h(String str) {
        return i(str, "utf-8");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x006c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r0v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String i(java.lang.String r5, java.lang.String r6) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r5)
            boolean r5 = r0.exists()
            r1 = 0
            if (r5 != 0) goto Ld
            return r1
        Ld:
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r5.<init>()
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4d
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4d
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            r0.<init>(r2, r6)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            r6 = 512(0x200, float:7.17E-43)
            char[] r6 = new char[r6]     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L68
        L20:
            int r3 = r0.read(r6)     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L68
            if (r3 <= 0) goto L2b
            r4 = 0
            r5.append(r6, r4, r3)     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L68
            goto L20
        L2b:
            java.lang.String r6 = new java.lang.String     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L68
            r6.<init>(r5)     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L68
            r2.close()     // Catch: java.io.IOException -> L34
            goto L38
        L34:
            r5 = move-exception
            r5.printStackTrace()
        L38:
            r0.close()     // Catch: java.io.IOException -> L3c
            goto L40
        L3c:
            r5 = move-exception
            r5.printStackTrace()
        L40:
            r1 = r6
            goto L67
        L42:
            r5 = move-exception
            goto L50
        L44:
            r5 = move-exception
            r0 = r1
            goto L69
        L47:
            r5 = move-exception
            r0 = r1
            goto L50
        L4a:
            r5 = move-exception
            r0 = r1
            goto L6a
        L4d:
            r5 = move-exception
            r0 = r1
            r2 = r0
        L50:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L68
            if (r2 == 0) goto L5d
            r2.close()     // Catch: java.io.IOException -> L59
            goto L5d
        L59:
            r5 = move-exception
            r5.printStackTrace()
        L5d:
            if (r0 == 0) goto L67
            r0.close()     // Catch: java.io.IOException -> L63
            goto L67
        L63:
            r5 = move-exception
            r5.printStackTrace()
        L67:
            return r1
        L68:
            r5 = move-exception
        L69:
            r1 = r2
        L6a:
            if (r1 == 0) goto L74
            r1.close()     // Catch: java.io.IOException -> L70
            goto L74
        L70:
            r6 = move-exception
            r6.printStackTrace()
        L74:
            if (r0 == 0) goto L7e
            r0.close()     // Catch: java.io.IOException -> L7a
            goto L7e
        L7a:
            r6 = move-exception
            r6.printStackTrace()
        L7e:
            goto L80
        L7f:
            throw r5
        L80:
            goto L7f
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.d.i(java.lang.String, java.lang.String):java.lang.String");
    }

    public static boolean j(String str, byte[] bArr) {
        boolean z;
        b(str, false);
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(bArr, 0, bArr.length);
                z = true;
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
            try {
                fileOutputStream.close();
                return z;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public static void k(String str, String str2) {
        l(str, str2, "utf-8");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0038 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void l(java.lang.String r3, java.lang.String r4, java.lang.String r5) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r3)
            r1 = 0
            b(r3, r1)
            r3 = 0
            java.io.OutputStreamWriter r1 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L24
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L24
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L24
            r1.<init>(r2, r5)     // Catch: java.lang.Throwable -> L20 java.lang.Exception -> L24
            r1.write(r4)     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L35
            r1.flush()     // Catch: java.lang.Exception -> L1e java.lang.Throwable -> L35
            r1.close()     // Catch: java.lang.Exception -> L30
            goto L34
        L1e:
            r3 = move-exception
            goto L27
        L20:
            r4 = move-exception
            r1 = r3
            r3 = r4
            goto L36
        L24:
            r4 = move-exception
            r1 = r3
            r3 = r4
        L27:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L35
            if (r1 == 0) goto L34
            r1.close()     // Catch: java.lang.Exception -> L30
            goto L34
        L30:
            r3 = move-exception
            r3.printStackTrace()
        L34:
            return
        L35:
            r3 = move-exception
        L36:
            if (r1 == 0) goto L40
            r1.close()     // Catch: java.lang.Exception -> L3c
            goto L40
        L3c:
            r4 = move-exception
            r4.printStackTrace()
        L40:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.d.l(java.lang.String, java.lang.String, java.lang.String):void");
    }

    public static String m(Context context) {
        if (b == null) {
            r(context);
        }
        return b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object n(java.lang.Object r4, java.lang.Class<?> r5, java.lang.String r6, java.lang.Class<?>[] r7, java.lang.Object[] r8) {
        /*
            r0 = 1
            r1 = 0
            r2 = 0
            if (r8 != 0) goto Lf
            java.lang.Class[] r7 = new java.lang.Class[r1]     // Catch: java.lang.Exception -> L1a
            java.lang.reflect.Method r6 = r5.getDeclaredMethod(r6, r7)     // Catch: java.lang.Exception -> L1a
            r6.setAccessible(r0)     // Catch: java.lang.Exception -> L18
            goto L16
        Lf:
            java.lang.reflect.Method r6 = r5.getDeclaredMethod(r6, r7)     // Catch: java.lang.Exception -> L1a
            r6.setAccessible(r0)     // Catch: java.lang.Exception -> L18
        L16:
            r7 = r2
            goto L23
        L18:
            r7 = move-exception
            goto L1c
        L1a:
            r7 = move-exception
            r6 = r2
        L1c:
            r7.printStackTrace()
            java.lang.String r7 = r7.toString()
        L23:
            java.lang.String r0 = " "
            java.lang.String r3 = "YuErr: \n"
            if (r7 == 0) goto L42
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            r4.append(r5)
            r4.append(r0)
            r4.append(r7)
            java.lang.String r4 = r4.toString()
        L3e:
            c.b.a.a.t.P2(r2, r4)
            return r2
        L42:
            if (r8 != 0) goto L4b
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L50
            java.lang.Object r4 = r6.invoke(r4, r7)     // Catch: java.lang.Exception -> L50
            return r4
        L4b:
            java.lang.Object r4 = r6.invoke(r4, r8)     // Catch: java.lang.Exception -> L50
            return r4
        L50:
            r4 = move-exception
            java.lang.String r4 = q(r4)
            if (r4 == 0) goto L6d
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r3)
            r6.append(r5)
            r6.append(r0)
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            goto L3e
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.d.n(java.lang.Object, java.lang.Class, java.lang.String, java.lang.Class[], java.lang.Object[]):java.lang.Object");
    }

    private static String o(Context context) {
        File externalFilesDir = context.getExternalFilesDir(null);
        return externalFilesDir == null ? String.format("%s/Android/data/com.iapp.app/files", a) : externalFilesDir.getAbsolutePath();
    }

    public static String p(Context context, String str) {
        if (str.startsWith("@")) {
            return "res/" + str.substring(1);
        }
        if (str.startsWith("%")) {
            String substring = str.substring(1);
            if (substring.indexOf(92) != -1) {
                substring = substring.replace('\\', '/');
            }
            if (substring.startsWith("/")) {
                return substring;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                return String.format("%s/%s", context.getExternalFilesDir(""), substring);
            }
            return m(context) + '/' + substring;
        }
        if (str.startsWith("$")) {
            return com.iapp.app.a.a(context) + str.substring(1);
        }
        if (str.startsWith("/")) {
            return str;
        }
        return a + '/' + str;
    }

    private static String q(Exception exc) {
        if (!(exc instanceof InvocationTargetException)) {
            return exc.getMessage();
        }
        Throwable targetException = ((InvocationTargetException) exc).getTargetException();
        if (targetException != null) {
            return targetException.getMessage();
        }
        return null;
    }

    public static void r(Context context) {
        b = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0 ? context.getFilesDir().getAbsolutePath() : Build.VERSION.SDK_INT >= 30 ? o(context) : a;
    }

    public static boolean s(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return false;
        }
        launchIntentForPackage.setFlags(337641472);
        context.startActivity(launchIntentForPackage);
        return true;
    }

    public static void t(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, logoActivity.class);
        intent.setFlags(335544320);
        context.startActivity(intent);
    }

    public static byte[] u(Context context, String str) {
        InputStream inputStream;
        try {
            inputStream = context.getAssets().open(str);
        } catch (Exception unused) {
            inputStream = null;
        }
        if (inputStream == null) {
            return null;
        }
        return w(inputStream);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] v(java.io.File r7) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            long r1 = r7.length()
            int r2 = (int) r1
            r0.<init>(r2)
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r3.<init>(r7)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3e
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r7]     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L51
        L19:
            r4 = -1
            r5 = 0
            int r6 = r2.read(r3, r5, r7)     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L51
            if (r4 == r6) goto L25
            r0.write(r3, r5, r6)     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L51
            goto L19
        L25:
            byte[] r1 = r0.toByteArray()     // Catch: java.lang.Exception -> L3a java.lang.Throwable -> L51
            r2.close()     // Catch: java.lang.Exception -> L2d
            goto L31
        L2d:
            r7 = move-exception
            r7.printStackTrace()
        L31:
            r0.close()     // Catch: java.lang.Exception -> L35
            goto L50
        L35:
            r7 = move-exception
            r7.printStackTrace()
            goto L50
        L3a:
            r7 = move-exception
            goto L40
        L3c:
            r7 = move-exception
            goto L53
        L3e:
            r7 = move-exception
            r2 = r1
        L40:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L51
            if (r2 == 0) goto L4d
            r2.close()     // Catch: java.lang.Exception -> L49
            goto L4d
        L49:
            r7 = move-exception
            r7.printStackTrace()
        L4d:
            r0.close()     // Catch: java.lang.Exception -> L35
        L50:
            return r1
        L51:
            r7 = move-exception
            r1 = r2
        L53:
            if (r1 == 0) goto L5d
            r1.close()     // Catch: java.lang.Exception -> L59
            goto L5d
        L59:
            r1 = move-exception
            r1.printStackTrace()
        L5d:
            r0.close()     // Catch: java.lang.Exception -> L61
            goto L65
        L61:
            r0 = move-exception
            r0.printStackTrace()
        L65:
            goto L67
        L66:
            throw r7
        L67:
            goto L66
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.d.v(java.io.File):byte[]");
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    public static byte[] w(InputStream inputStream) {
        BufferedInputStream bufferedInputStream;
        byte[] bArr = null;
        bArr = null;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            ?? available = inputStream.available();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(available);
            try {
                try {
                    try {
                        bufferedInputStream = new BufferedInputStream(inputStream);
                    } catch (Exception e) {
                        e = e;
                        bufferedInputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        try {
                            byteArrayOutputStream.close();
                            throw th;
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            throw th;
                        }
                    }
                    try {
                        byte[] bArr2 = new byte[1024];
                        while (true) {
                            int read = bufferedInputStream.read(bArr2, 0, 1024);
                            if (-1 == read) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr2, 0, read);
                        }
                        bArr = byteArrayOutputStream.toByteArray();
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        byteArrayOutputStream.close();
                    } catch (Exception e5) {
                        e = e5;
                        e.printStackTrace();
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Exception e6) {
                                e6.printStackTrace();
                            }
                        }
                        byteArrayOutputStream.close();
                        return bArr;
                    }
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
                return bArr;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = available;
            }
        } catch (IOException e8) {
            e8.printStackTrace();
            return null;
        }
    }

    public static byte[] x(String str) {
        File file = new File(str);
        if (file.exists()) {
            return v(file);
        }
        return null;
    }
}
