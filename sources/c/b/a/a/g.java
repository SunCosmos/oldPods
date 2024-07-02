package c.b.a.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Random;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

/* loaded from: classes.dex */
public class g {
    private static CookieManager a;
    private static X509TrustManager[] b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements X509TrustManager {
        private final X509Certificate[] a = new X509Certificate[0];

        a() {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return this.a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements HostnameVerifier {
        b() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0075 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0076 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a(java.lang.String r7, java.lang.String r8, boolean r9) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.g.a(java.lang.String, java.lang.String, boolean):int");
    }

    public static int b(String str, String str2, boolean z, String str3, String str4, String str5, boolean z2, String str6) {
        int i2 = 0;
        d.b(str2, false);
        File file = new File(str2);
        File file2 = new File(file.getParent() + "/" + new Random().nextLong() + ".xzz");
        try {
            URL url = new URL(p(str));
            HttpURLConnection l = l(url);
            if (str6 != null) {
                for (String str7 : str6.split("\\|\\|")) {
                    int indexOf = str7.indexOf(61);
                    if (indexOf != -1) {
                        l.setRequestProperty(str7.substring(0, indexOf).trim(), str7.substring(indexOf + 1).trim());
                    }
                }
            }
            if (str5 != null) {
                l.setRequestProperty("Cookie", str5);
            } else if (z2) {
                i(l, url.toURI());
            }
            c(l, str3, str4);
            int responseCode = l.getResponseCode();
            int i3 = 0;
            while (responseCode >= 300 && responseCode < 400 && i3 <= 8) {
                o().put(url.toURI(), l.getHeaderFields());
                String headerField = l.getHeaderField("Location");
                if (headerField == null) {
                    break;
                }
                URL url2 = new URL(p(u.i(url.toURI(), headerField)));
                l.disconnect();
                l = l(url2);
                i(l, url2.toURI());
                c(l, null, str4);
                i3++;
                responseCode = l.getResponseCode();
                url = url2;
            }
            if (responseCode == 200) {
                if (z2) {
                    o().put(url.toURI(), l.getHeaderFields());
                }
                InputStream inputStream = l.getInputStream();
                if (file.exists() && !z && (inputStream.available() == file.length() || l.getContentLength() == file.length())) {
                    if (inputStream == null) {
                        return 1;
                    }
                    inputStream.close();
                    return 1;
                }
                file2.createNewFile();
                byte[] bArr = new byte[1024];
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                if (file.exists()) {
                    file.delete();
                }
                file2.renameTo(file);
                inputStream.close();
                fileOutputStream.close();
            } else {
                i2 = -1;
            }
            return i2;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                file2.delete();
                return -1;
            } catch (Exception unused) {
                e.printStackTrace();
                return -1;
            }
        }
    }

    private static void c(HttpURLConnection httpURLConnection, String str, String str2) {
        if (httpURLConnection instanceof HttpsURLConnection) {
            r((HttpsURLConnection) httpURLConnection);
        }
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(false);
        if (str != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            String trim = str.trim();
            if (trim.indexOf(123) == 0 || trim.indexOf(91) == 0) {
                httpURLConnection.setRequestProperty("Charsert", str2);
                httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=" + str2);
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), str2));
                printWriter.println(str);
                printWriter.flush();
                printWriter.close();
            } else {
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                String[] split = str.replace("\\&", "\\U26").split("&");
                for (int i2 = 0; i2 < split.length; i2++) {
                    int indexOf = split[i2].indexOf("=");
                    if (indexOf != -1) {
                        int i3 = indexOf + 1;
                        if (i2 == 0) {
                            outputStream.write(split[i2].substring(0, i3).getBytes());
                            outputStream.write(split[i2].substring(i3).replace("\\U26", "%26").getBytes(str2));
                        } else {
                            outputStream.write(38);
                            outputStream.write(split[i2].substring(0, i3).getBytes());
                            outputStream.write(split[i2].substring(i3).replace("\\U26", "%26").getBytes(str2));
                        }
                    }
                }
                outputStream.flush();
                outputStream.close();
            }
        }
        httpURLConnection.connect();
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        String str2 = "";
        if (str.equals("cookie")) {
            for (HttpCookie httpCookie : o().getCookieStore().getCookies()) {
                str2 = str2 + httpCookie.getName() + "=" + httpCookie.getValue() + ";";
            }
            return str2;
        }
        if (str.equals("del cookie")) {
            a = null;
        } else {
            try {
                URI uri = new URI(str);
                CookieStore cookieStore = o().getCookieStore();
                String lowerCase = uri.getHost().toLowerCase();
                for (HttpCookie httpCookie2 : cookieStore.getCookies()) {
                    String lowerCase2 = httpCookie2.getDomain().toLowerCase();
                    if ((lowerCase2.startsWith(".") && lowerCase.endsWith(lowerCase2.substring(1))) || lowerCase.endsWith(lowerCase2)) {
                        str2 = str2 + httpCookie2.getName() + "=" + httpCookie2.getValue() + ";";
                    }
                }
                return str2;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String e(String str, String str2, String str3) {
        if (str3 == null) {
            str3 = "utf-8";
        }
        String str4 = str3;
        try {
            URL url = new URL(p(str));
            return j(url, l(url), str2, str4, false, false);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String f(String str, String str2, String str3, String str4) {
        try {
            URL url = new URL(p(str));
            HttpURLConnection l = l(url);
            l.setConnectTimeout(20000);
            l.setReadTimeout(20000);
            if (str4 != null) {
                l.setRequestProperty("Cookie", str4);
            }
            return j(url, l, str2, str3, false, false);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String g(String str, String str2, String str3, String str4, boolean z) {
        try {
            URL url = new URL(p(str));
            HttpURLConnection l = l(url);
            l.setConnectTimeout(20000);
            l.setReadTimeout(20000);
            if (str4 != null) {
                l.setRequestProperty("Cookie", str4);
            } else if (z) {
                i(l, url.toURI());
            }
            return j(url, l, str2, str3, false, z);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String h(String str, String str2, String str3, String str4, boolean z, String str5, int i2, int i3, String str6) {
        int i4;
        URLConnection openConnection;
        try {
            URL url = new URL(p(str));
            if (str6 == null) {
                openConnection = url.openConnection();
            } else {
                int indexOf = str6.indexOf(58);
                if (indexOf != -1) {
                    String substring = str6.substring(0, indexOf);
                    i4 = Integer.parseInt(str6.substring(indexOf + 1));
                    str6 = substring;
                } else {
                    i4 = 80;
                }
                openConnection = url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str6, i4)));
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setRequestProperty("User-Agent", "Apache-HttpClient/UNAVAILABLE (java 1.4)");
            httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
            httpURLConnection.setConnectTimeout(i2);
            httpURLConnection.setReadTimeout(i3);
            if (str5 != null) {
                for (String str7 : str5.split("\\|\\|")) {
                    int indexOf2 = str7.indexOf(61);
                    if (indexOf2 != -1) {
                        httpURLConnection.setRequestProperty(str7.substring(0, indexOf2).trim(), str7.substring(indexOf2 + 1).trim());
                    }
                }
            }
            if (str4 != null) {
                httpURLConnection.setRequestProperty("Cookie", str4);
            } else if (z) {
                i(httpURLConnection, url.toURI());
            }
            return j(url, httpURLConnection, str2, str3, false, z);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void i(HttpURLConnection httpURLConnection, URI uri) {
        try {
            CookieStore cookieStore = o().getCookieStore();
            String str = "";
            String lowerCase = uri.getHost().toLowerCase();
            for (HttpCookie httpCookie : cookieStore.getCookies()) {
                String lowerCase2 = httpCookie.getDomain().toLowerCase();
                if ((lowerCase2.startsWith(".") && lowerCase.endsWith(lowerCase2.substring(1))) || lowerCase.endsWith(lowerCase2)) {
                    str = str + httpCookie.getName() + "=" + httpCookie.getValue() + ";";
                }
            }
            if (str.length() > 0) {
                httpURLConnection.setRequestProperty("Cookie", str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String j(java.net.URL r6, java.net.HttpURLConnection r7, java.lang.String r8, java.lang.String r9, boolean r10, boolean r11) {
        /*
            r0 = 0
            k(r7, r8, r9, r10)     // Catch: java.lang.Exception -> L9f
            int r8 = r7.getResponseCode()     // Catch: java.lang.Exception -> L9f
            r10 = 0
            r1 = 0
        La:
            r2 = 300(0x12c, float:4.2E-43)
            if (r8 < r2) goto L5b
            r2 = 400(0x190, float:5.6E-43)
            if (r8 >= r2) goto L5b
            r2 = 8
            if (r1 <= r2) goto L17
            goto L5b
        L17:
            java.net.CookieManager r2 = o()     // Catch: java.lang.Exception -> L9f
            java.net.URI r3 = r6.toURI()     // Catch: java.lang.Exception -> L9f
            java.util.Map r4 = r7.getHeaderFields()     // Catch: java.lang.Exception -> L9f
            r2.put(r3, r4)     // Catch: java.lang.Exception -> L9f
            java.lang.String r2 = "Location"
            java.lang.String r2 = r7.getHeaderField(r2)     // Catch: java.lang.Exception -> L9f
            if (r2 != 0) goto L2f
            goto L5b
        L2f:
            java.net.URL r8 = new java.net.URL     // Catch: java.lang.Exception -> L9f
            java.net.URI r6 = r6.toURI()     // Catch: java.lang.Exception -> L9f
            java.lang.String r6 = c.b.a.a.u.i(r6, r2)     // Catch: java.lang.Exception -> L9f
            java.lang.String r6 = p(r6)     // Catch: java.lang.Exception -> L9f
            r8.<init>(r6)     // Catch: java.lang.Exception -> L9f
            r7.disconnect()     // Catch: java.lang.Exception -> L9f
            java.net.HttpURLConnection r7 = l(r8)     // Catch: java.lang.Exception -> L9f
            java.net.URI r6 = r8.toURI()     // Catch: java.lang.Exception -> L9f
            i(r7, r6)     // Catch: java.lang.Exception -> L9f
            k(r7, r0, r9, r10)     // Catch: java.lang.Exception -> L9f
            int r6 = r7.getResponseCode()     // Catch: java.lang.Exception -> L9f
            int r1 = r1 + 1
            r5 = r8
            r8 = r6
            r6 = r5
            goto La
        L5b:
            r1 = 200(0xc8, float:2.8E-43)
            if (r8 != r1) goto L9d
            if (r11 == 0) goto L70
            java.net.CookieManager r8 = o()     // Catch: java.lang.Exception -> L9f
            java.net.URI r6 = r6.toURI()     // Catch: java.lang.Exception -> L9f
            java.util.Map r11 = r7.getHeaderFields()     // Catch: java.lang.Exception -> L9f
            r8.put(r6, r11)     // Catch: java.lang.Exception -> L9f
        L70:
            java.io.InputStream r6 = r7.getInputStream()     // Catch: java.lang.Exception -> L9f
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch: java.lang.Exception -> L9f
            r7.<init>(r6, r9)     // Catch: java.lang.Exception -> L9f
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L9f
            r8.<init>(r7)     // Catch: java.lang.Exception -> L9f
            java.lang.StringBuffer r9 = new java.lang.StringBuffer     // Catch: java.lang.Exception -> L9f
            r9.<init>()     // Catch: java.lang.Exception -> L9f
            r11 = 512(0x200, float:7.17E-43)
            char[] r11 = new char[r11]     // Catch: java.lang.Exception -> L9b
        L87:
            int r1 = r8.read(r11)     // Catch: java.lang.Exception -> L9b
            if (r1 <= 0) goto L91
            r9.append(r11, r10, r1)     // Catch: java.lang.Exception -> L9b
            goto L87
        L91:
            r6.close()     // Catch: java.lang.Exception -> L9b
            r7.close()     // Catch: java.lang.Exception -> L9b
            r8.close()     // Catch: java.lang.Exception -> L9b
            goto La4
        L9b:
            r6 = move-exception
            goto La1
        L9d:
            r9 = r0
            goto La4
        L9f:
            r6 = move-exception
            r9 = r0
        La1:
            r6.printStackTrace()
        La4:
            if (r9 != 0) goto La7
            return r0
        La7:
            java.lang.String r6 = r9.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.g.j(java.net.URL, java.net.HttpURLConnection, java.lang.String, java.lang.String, boolean, boolean):java.lang.String");
    }

    private static void k(HttpURLConnection httpURLConnection, String str, String str2, boolean z) {
        String str3;
        if (httpURLConnection instanceof HttpsURLConnection) {
            r((HttpsURLConnection) httpURLConnection);
        }
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(false);
        if (str != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            String trim = str.trim();
            if (trim.indexOf(123) == 0 || trim.indexOf(91) == 0) {
                httpURLConnection.setRequestProperty("Charsert", str2);
                httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=" + str2);
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), str2));
                printWriter.println(str);
                printWriter.flush();
                printWriter.close();
            } else {
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                String[] split = str.replace("\\&", "\\U26").split("&");
                if (z) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i2 = 0; i2 < split.length; i2++) {
                        int indexOf = split[i2].indexOf("=");
                        if (indexOf != -1) {
                            int i3 = indexOf + 1;
                            if (i2 == 0) {
                                stringBuffer.append(split[i2].substring(0, i3));
                                str3 = split[i2];
                            } else {
                                stringBuffer.append('&');
                                stringBuffer.append(split[i2].substring(0, i3));
                                str3 = split[i2];
                            }
                            stringBuffer.append(s.a(str3.substring(i3).replace("\\U26", "&")));
                        }
                    }
                    outputStream.write(stringBuffer.toString().getBytes());
                } else {
                    for (int i4 = 0; i4 < split.length; i4++) {
                        int indexOf2 = split[i4].indexOf("=");
                        if (indexOf2 != -1) {
                            int i5 = indexOf2 + 1;
                            if (i4 == 0) {
                                outputStream.write(split[i4].substring(0, i5).getBytes());
                                outputStream.write(split[i4].substring(i5).replace("\\U26", "%26").getBytes(str2));
                            } else {
                                outputStream.write(38);
                                outputStream.write(split[i4].substring(0, i5).getBytes());
                                outputStream.write(split[i4].substring(i5).replace("\\U26", "%26").getBytes(str2));
                            }
                        }
                    }
                }
                outputStream.flush();
                outputStream.close();
            }
        }
        httpURLConnection.connect();
    }

    private static HttpURLConnection l(URL url) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("User-Agent", "Apache-HttpClient/UNAVAILABLE (java 1.4)");
        httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
        return httpURLConnection;
    }

    public static String m(Context context, String str, String str2, String str3, String str4) {
        return n(context, str, str2, str3, str4, null);
    }

    public static String n(Context context, String str, String str2, String str3, String str4, String str5) {
        String str6;
        String[] strArr;
        String str7;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(p(str)).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", str4);
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=******");
            int i2 = -1;
            if (str5 != null) {
                for (String str8 : str5.split("\\|\\|")) {
                    int indexOf = str8.indexOf(61);
                    if (indexOf != -1) {
                        httpURLConnection.setRequestProperty(str8.substring(0, indexOf).trim(), str8.substring(indexOf + 1).trim());
                    }
                }
            }
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            String[] split = str2.replace("\\&", "\\U26").split("&");
            int length = split.length;
            int i3 = 0;
            while (i3 < length) {
                String str9 = split[i3];
                int indexOf2 = str9.indexOf("=");
                if (indexOf2 != i2) {
                    dataOutputStream.writeBytes("--******\r\n");
                    dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + str9.substring(0, indexOf2) + "\"\r\n");
                    dataOutputStream.writeBytes("\r\n");
                    dataOutputStream.write(str9.substring(indexOf2 + 1).replace("\\U26", "&").getBytes(str4));
                    dataOutputStream.writeBytes("\r\n");
                }
                i3++;
                i2 = -1;
            }
            dataOutputStream.writeBytes("--******\r\n");
            String[] b2 = q.b(str3, '|');
            String str10 = b2.length > 1 ? "file[]" : "file";
            int length2 = b2.length;
            int i4 = 0;
            boolean z = false;
            while (i4 < length2) {
                String str11 = b2[i4];
                int indexOf3 = str11.indexOf(10);
                if (indexOf3 != -1) {
                    String trim = str11.substring(indexOf3 + 1).trim();
                    strArr = b2;
                    str7 = str11.substring(0, indexOf3).trim();
                    str11 = trim;
                } else {
                    strArr = b2;
                    str7 = str10;
                }
                File file = new File(f.o(context, str11));
                if (file.exists()) {
                    if (z) {
                        dataOutputStream.writeBytes("--******\r\n");
                    }
                    dataOutputStream.write(("Content-Disposition: form-data; name=\"" + str7 + "\"; filename=\"" + file.getName() + "\"\r\nContent-Type: " + p.p(file) + "\r\n").getBytes(str4));
                    dataOutputStream.writeBytes("\r\n");
                    FileInputStream fileInputStream = new FileInputStream(file);
                    byte[] bArr = new byte[8192];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        dataOutputStream.write(bArr, 0, read);
                    }
                    fileInputStream.close();
                    dataOutputStream.writeBytes("\r\n");
                    z = true;
                }
                i4++;
                b2 = strArr;
            }
            dataOutputStream.writeBytes("--******--\r\n");
            dataOutputStream.flush();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str4));
            str6 = "";
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    str6 = str6 + readLine;
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    return str6;
                }
            }
            dataOutputStream.close();
            inputStream.close();
        } catch (Exception e2) {
            e = e2;
            str6 = null;
        }
        return str6;
    }

    private static CookieManager o() {
        if (a == null) {
            CookieManager cookieManager = new CookieManager();
            a = cookieManager;
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        }
        return a;
    }

    private static String p(String str) {
        return str.replace(" ", "%20").replace("\\&", "%26");
    }

    public static Intent q(Context context, String str) {
        if (context == null) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(k.c(context, str));
            return intent;
        }
        String[] split = "com.tencent.mtt,com.tencent.mtt.x86,com.UCMobile,sogou.mobile.explorer,com.oupeng.browser,com.baidu.browser.apps,com.android.chrome,com.UCMobile.cmcc,org.mozilla.firefox,com.ijinshan.browser_fast,com.uc.browser,com.roboo.explorer,com.mx.browser,com.kk.jd.browser,com.opera.mini.android".split(",");
        PackageManager packageManager = context.getPackageManager();
        Intent intent2 = null;
        for (String str2 : split) {
            intent2 = packageManager.getLaunchIntentForPackage(str2);
            if (intent2 != null) {
                break;
            }
        }
        if (intent2 != null) {
            intent2.setAction("android.intent.action.VIEW");
            intent2.addCategory("android.intent.category.DEFAULT");
        } else {
            intent2 = new Intent();
            intent2.setAction("android.intent.action.VIEW");
        }
        intent2.setData(k.c(context, str));
        return intent2;
    }

    private static void r(HttpsURLConnection httpsURLConnection) {
        if (b == null) {
            b = new X509TrustManager[]{new a()};
        }
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(new KeyManager[0], b, new SecureRandom());
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(new b());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
