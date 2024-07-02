package c.b.a.a.w;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Build;
import android.support.v4.media.session.PlaybackStateCompat;
import c.b.a.a.d;
import c.b.a.a.p;
import c.b.a.a.q;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/* loaded from: classes.dex */
public class c {
    public c.b.a.a.w.a a;
    public int b;

    /* renamed from: c */
    public URL f1381c;

    /* renamed from: d */
    public String f1382d;
    public String e;
    public String f;
    public String g;
    public boolean q;
    public boolean r;
    public Object t;
    public String u;
    public Object v;
    public String w;
    public long h = -1;

    /* renamed from: i */
    public long f1383i = -1;
    public long j = 0;
    public int k = 0;
    public int l = 0;
    public int m = 0;
    public int n = 0;
    public int o = 0;
    public long p = 0;
    public boolean s = true;
    private DecimalFormat x = new DecimalFormat("0.0");
    private NotificationManager y = null;
    private Notification.Builder z = null;
    public RandomAccessFile A = null;
    private FileChannel B = null;
    private ArrayList<b> C = new ArrayList<>();
    public String D = null;
    private long E = 0;

    /* loaded from: classes.dex */
    public class a extends Thread {
        a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            boolean z;
            c cVar;
            long[] o;
            HttpURLConnection q = c.this.q(-1L, -1L);
            if (q == null) {
                c.this.l(-1);
                return;
            }
            long contentLength = q.getContentLength();
            c cVar2 = c.this;
            cVar2.h = contentLength;
            cVar2.f1383i = contentLength - 1;
            if (!"bytes".equals(q.getHeaderField("Accept-Ranges"))) {
                c cVar3 = c.this;
                cVar3.s = false;
                HttpURLConnection q2 = cVar3.q(32L, 64L);
                if (q2 != null && (o = c.this.o(q2)) != null && o[0] == 32 && o[1] == 64) {
                    long j = o[2];
                    c cVar4 = c.this;
                    if (j == cVar4.h) {
                        cVar4.s = true;
                    }
                }
            }
            File file = new File(c.this.f1382d);
            if (file.exists()) {
                long length = file.length();
                c cVar5 = c.this;
                if (length == cVar5.h) {
                    cVar5.l(2);
                    c cVar6 = c.this;
                    cVar6.j = cVar6.h;
                    if (cVar6.r) {
                        cVar6.a(cVar6.a.j, "已下载");
                    }
                    c.this.n();
                    return;
                }
                file.delete();
            }
            c cVar7 = c.this;
            int i2 = cVar7.a.e;
            cVar7.n = i2;
            cVar7.p = cVar7.h / i2;
            File file2 = new File(c.this.a.a + c.this.e + c.this.a.f1376c);
            if (file2.exists() && file2.length() == c.this.h) {
                z = true;
            } else {
                c.m(file2, c.this.h);
                z = false;
            }
            try {
                c.this.A = new RandomAccessFile(file2, "rw");
                c cVar8 = c.this;
                cVar8.B = cVar8.A.getChannel();
                c.this.C.clear();
                c.this.D = c.this.a.a + c.this.e + c.this.a.f1376c + ".config";
                c cVar9 = c.this;
                if (!cVar9.s || cVar9.h <= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) {
                    int size = cVar9.C.size() + 1;
                    c cVar10 = c.this;
                    b bVar = new b(size, 0L, cVar10.f1383i, 0L, cVar10.h);
                    cVar10.C.add(bVar);
                    c.this.c(true);
                    bVar.g();
                } else {
                    if (z) {
                        String h = d.h(cVar9.D);
                        if (h != null && h.contains("</Thread>")) {
                            for (String str : q.d(h, "<Thread")) {
                                if (str.contains("</Thread>") || str.contains("/>")) {
                                    try {
                                        long parseLong = Long.parseLong(p.c(str, " start=\"", "\""));
                                        long parseLong2 = Long.parseLong(p.c(str, " end=\"", "\""));
                                        long parseLong3 = Long.parseLong(p.c(str, " current=\"", "\""));
                                        long parseLong4 = Long.parseLong(p.c(str, " size=\"", "\""));
                                        c cVar11 = c.this;
                                        if (parseLong4 == cVar11.h) {
                                            ArrayList arrayList = cVar11.C;
                                            c cVar12 = c.this;
                                            arrayList.add(new b(cVar12.C.size() + 1, parseLong, parseLong2, parseLong3, parseLong4));
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        z = false;
                        break;
                    }
                    if (!z) {
                        c cVar13 = c.this;
                        long j2 = cVar13.f1383i;
                        int i3 = cVar13.n - 1;
                        long j3 = j2;
                        int i4 = 0;
                        while (i4 < i3) {
                            c cVar14 = c.this;
                            long j4 = j3 - cVar14.p;
                            ArrayList arrayList2 = cVar14.C;
                            c cVar15 = c.this;
                            arrayList2.add(new b(cVar15.C.size() + 1, j4, j3, j4, c.this.h));
                            i4++;
                            j3 = j4;
                        }
                        ArrayList arrayList3 = c.this.C;
                        c cVar16 = c.this;
                        arrayList3.add(new b(cVar16.C.size() + 1, 0L, j3, 0L, c.this.h));
                    }
                    c.this.c(true);
                    Iterator it = c.this.C.iterator();
                    while (it.hasNext()) {
                        ((b) it.next()).g();
                    }
                }
                c cVar17 = c.this;
                if (cVar17.r) {
                    cVar17.a(cVar17.a.j, "开始下载");
                }
                while (true) {
                    cVar = c.this;
                    if (cVar.o <= 0) {
                        break;
                    }
                    long j5 = cVar.j;
                    try {
                        Thread.sleep(1600L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    c cVar18 = c.this;
                    cVar18.j = cVar18.p();
                    c cVar19 = c.this;
                    long j6 = cVar19.j;
                    cVar19.k = (int) (j6 - j5);
                    cVar19.l = (int) ((j6 * 100) / cVar19.h);
                    if (cVar19.r) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(c.this.l);
                        sb.append("% ");
                        sb.append(c.this.r(r4.k));
                        sb.append("/s，");
                        sb.append(c.this.r(r4.j));
                        sb.append("/");
                        sb.append(c.this.r(r4.h));
                        cVar19.b(sb.toString(), true);
                    }
                }
                if (cVar.q) {
                    cVar.l(3);
                    c cVar20 = c.this;
                    if (cVar20.r) {
                        cVar20.b("已暂停下载", true);
                        return;
                    }
                    return;
                }
                cVar.j = cVar.h;
                if (cVar.r) {
                    cVar.b("下载完成", false);
                }
                try {
                    RandomAccessFile randomAccessFile = c.this.A;
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                if (!file2.renameTo(file)) {
                    d.a(file2.getAbsolutePath(), file.getAbsolutePath(), Boolean.TRUE);
                    file2.delete();
                }
                new File(c.this.D).delete();
                c cVar21 = c.this;
                cVar21.m = 2;
                cVar21.q = true;
                cVar21.a.h--;
                cVar21.n();
            } catch (Exception e4) {
                e4.printStackTrace();
                c.this.l(-1);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b {
        long a;
        long b;

        /* renamed from: c */
        long f1384c;

        /* renamed from: d */
        long f1385d;
        long e;

        /* loaded from: classes.dex */
        public class a extends Thread {
            a() {
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                int read;
                b.this.a(-1);
                b bVar = b.this;
                HttpURLConnection q = c.this.q(bVar.f1384c, bVar.b);
                if (q != null) {
                    ByteBuffer allocate = ByteBuffer.allocate(2097152);
                    ReadableByteChannel readableByteChannel = null;
                    try {
                        try {
                            try {
                                readableByteChannel = Channels.newChannel(q.getInputStream());
                                while (!c.this.q && (read = readableByteChannel.read(allocate)) != -1) {
                                    b bVar2 = b.this;
                                    c.this.u(bVar2.f1384c, allocate);
                                    b.this.f(read);
                                    allocate.clear();
                                }
                                if (readableByteChannel != null) {
                                    readableByteChannel.close();
                                }
                            } catch (Throwable th) {
                                if (readableByteChannel != null) {
                                    try {
                                        readableByteChannel.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            c.this.l(-1);
                            if (readableByteChannel != null) {
                                readableByteChannel.close();
                            }
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                c.this.c(true);
                b.this.b(-1);
            }
        }

        public b(int i2, long j, long j2, long j3, long j4) {
            this.e = 0L;
            this.a = j;
            this.b = j2;
            this.f1384c = j3;
            this.f1385d = j4;
            this.e = j3 - j;
        }

        public synchronized void a(int i2) {
            c.this.n += i2;
        }

        public synchronized void b(int i2) {
            c.this.o += i2;
        }

        public synchronized void f(int i2) {
            long j = i2;
            this.e += j;
            this.f1384c += j;
        }

        public void g() {
            if (this.f1384c > this.b) {
                return;
            }
            b(1);
            a aVar = new a();
            aVar.setName("CeShi_" + aVar.getId());
            aVar.start();
        }
    }

    public c(c.b.a.a.w.a aVar, int i2, String str, String str2, String str3, String str4, Object obj, String str5, Object obj2, boolean z) {
        this.a = null;
        this.b = 0;
        this.f1381c = null;
        this.f1382d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.q = true;
        this.r = true;
        this.t = null;
        this.u = null;
        this.v = null;
        this.a = aVar;
        this.b = i2;
        this.e = str4;
        this.f1381c = new URL(str);
        this.f1382d = str2 + str3;
        this.f = str2;
        this.g = str3;
        this.t = obj;
        this.u = str5;
        this.v = obj2;
        this.r = z;
        this.q = false;
    }

    public synchronized void c(boolean z) {
        long time = new Date().getTime();
        if (z || time >= this.E) {
            this.E = time + 1000;
            StringBuilder sb = new StringBuilder();
            sb.append("<ys>");
            Iterator<b> it = this.C.iterator();
            while (it.hasNext()) {
                b next = it.next();
                sb.append("<Thread start=\"");
                sb.append(next.a);
                sb.append("\" end=\"");
                sb.append(next.b);
                sb.append("\" current=\"");
                sb.append(next.f1384c);
                sb.append("\" size=\"");
                sb.append(next.f1385d);
                sb.append("\"></Thread>");
            }
            sb.append("</ys>");
            d.k(this.D, sb.toString());
        }
    }

    public synchronized void l(int i2) {
        this.m = i2;
        this.q = true;
        this.a.h--;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0016 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m(java.io.File r3, long r4) {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: java.lang.Exception -> Lf
            java.lang.String r2 = "rw"
            r1.<init>(r3, r2)     // Catch: java.lang.Exception -> Lf
            r1.setLength(r4)     // Catch: java.lang.Exception -> Lc
            goto L14
        Lc:
            r3 = move-exception
            r0 = r1
            goto L10
        Lf:
            r3 = move-exception
        L10:
            r3.printStackTrace()
            r1 = r0
        L14:
            if (r1 == 0) goto L1e
            r1.close()     // Catch: java.lang.Exception -> L1a
            goto L1e
        L1a:
            r3 = move-exception
            r3.printStackTrace()
        L1e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.w.c.m(java.io.File, long):void");
    }

    public void n() {
        if (new File(this.f1382d).exists() && this.f1382d.toLowerCase().endsWith(".apk")) {
            d.d(this.a.j, this.f1382d);
        }
    }

    public long[] o(HttpURLConnection httpURLConnection) {
        String substring;
        int indexOf;
        String headerField = httpURLConnection.getHeaderField("Content-Range");
        if (headerField != null && headerField.startsWith("bytes") && (indexOf = (substring = headerField.substring(5)).indexOf(47)) != -1) {
            String trim = substring.substring(indexOf + 1).trim();
            String substring2 = substring.substring(0, indexOf);
            int indexOf2 = substring2.indexOf(45);
            if (indexOf2 != -1) {
                try {
                    return new long[]{Long.parseLong(substring2.substring(0, indexOf2).trim()), Long.parseLong(substring2.substring(indexOf2 + 1).trim()), Long.parseLong(trim)};
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public HttpURLConnection q(long j, long j2) {
        HttpURLConnection httpURLConnection;
        for (int i2 = 0; i2 < this.a.f; i2++) {
            try {
                httpURLConnection = (HttpURLConnection) this.f1381c.openConnection();
                if (j != -1 && j2 != -1) {
                    httpURLConnection.setRequestProperty("Range", "bytes=" + j + "-" + j2);
                }
                httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
                httpURLConnection.setConnectTimeout(this.a.g);
                httpURLConnection.setRequestProperty("Referer", this.f1381c.toString());
                httpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
                httpURLConnection.setRequestProperty("Charset", "UTF-8");
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (httpURLConnection.getContentLength() != -1) {
                return httpURLConnection;
            }
        }
        return null;
    }

    public String r(double d2) {
        double d3 = d2 / 1024.0d;
        if (d3 <= 1024.0d) {
            return this.x.format(d3) + "K";
        }
        double d4 = d3 / 1024.0d;
        if (d4 > 1024.0d) {
            return this.x.format(d4 / 1024.0d) + "G";
        }
        return this.x.format(d4) + "M";
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.content.Context r7, java.lang.String r8) {
        /*
            r6 = this;
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            c.b.a.a.w.a r1 = r6.a
            android.content.Context r1 = r1.j
            java.lang.Class<com.iapp.app.DownList> r2 = com.iapp.app.DownList.class
            r0.setClass(r1, r2)
            r1 = 541065216(0x20400000, float:1.6263033E-19)
            r0.setFlags(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "下载 "
            r1.append(r2)
            java.lang.String r2 = r6.u
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "notification"
            java.lang.Object r2 = r7.getSystemService(r2)
            android.app.NotificationManager r2 = (android.app.NotificationManager) r2
            r6.y = r2
            r2 = 0
            r3 = 134217728(0x8000000, float:3.85186E-34)
            android.app.PendingIntent r0 = android.app.PendingIntent.getActivity(r7, r2, r0, r3)
            android.app.Notification$Builder r2 = new android.app.Notification$Builder
            r2.<init>(r7)
            long r3 = java.lang.System.currentTimeMillis()
            android.app.Notification$Builder r2 = r2.setWhen(r3)
            android.app.Notification$Builder r2 = r2.setTicker(r1)
            android.app.Notification$Builder r1 = r2.setContentTitle(r1)
            android.app.Notification$Builder r8 = r1.setContentText(r8)
            r1 = 1
            android.app.Notification$Builder r8 = r8.setAutoCancel(r1)
            r6.z = r8
            int r8 = android.os.Build.VERSION.SDK_INT
            r2 = 26
            if (r8 < r2) goto L71
            android.app.NotificationChannel r2 = new android.app.NotificationChannel
            r3 = 4
            java.lang.String r4 = "i_message"
            java.lang.String r5 = "通知消息"
            r2.<init>(r4, r5, r3)
            android.app.NotificationManager r3 = r6.y
            r3.createNotificationChannel(r2)
            android.app.Notification$Builder r2 = r6.z
            r2.setChannelId(r4)
        L71:
            java.lang.Object r2 = r6.v
            android.app.Notification$Builder r3 = r6.z
            r4 = 2131492876(0x7f0c000c, float:1.8609216E38)
            r3.setSmallIcon(r4)
            if (r2 == 0) goto Lcb
            boolean r3 = r2 instanceof android.graphics.Bitmap
            if (r3 == 0) goto L89
            android.app.Notification$Builder r7 = r6.z
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            r7.setLargeIcon(r2)
            goto Ld8
        L89:
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r3 = "@"
            boolean r3 = r2.startsWith(r3)
            if (r3 == 0) goto La3
        L95:
            android.app.Notification$Builder r3 = r6.z
            java.lang.String r7 = c.b.a.a.d.p(r7, r2)
            android.graphics.Bitmap r7 = c.b.a.a.i.c(r7)
        L9f:
            r3.setLargeIcon(r7)
            goto Ld8
        La3:
            java.lang.String r3 = "%"
            boolean r3 = r2.startsWith(r3)
            if (r3 != 0) goto L95
            java.lang.String r3 = "$"
            boolean r3 = r2.startsWith(r3)
            if (r3 == 0) goto Lb4
            goto L95
        Lb4:
            java.lang.String r3 = "[0-9]+"
            boolean r3 = r2.matches(r3)
            if (r3 == 0) goto Lcb
            android.app.Notification$Builder r3 = r6.z
            android.content.res.Resources r7 = r7.getResources()
            int r2 = java.lang.Integer.parseInt(r2)
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeResource(r7, r2)
            goto L9f
        Lcb:
            android.app.Notification$Builder r2 = r6.z
            android.content.res.Resources r7 = r7.getResources()
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeResource(r7, r4)
            r2.setLargeIcon(r7)
        Ld8:
            android.app.Notification$Builder r7 = r6.z
            r7.setContentIntent(r0)
            r7 = 16
            if (r8 < r7) goto Le8
            android.app.Notification$Builder r7 = r6.z
            android.app.Notification r7 = r7.build()
            goto Lee
        Le8:
            android.app.Notification$Builder r7 = r6.z
            android.app.Notification r7 = r7.getNotification()
        Lee:
            android.app.NotificationManager r8 = r6.y
            r8.notify(r1, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.w.c.a(android.content.Context, java.lang.String):void");
    }

    public void b(String str, boolean z) {
        this.z.setContentText(str).setOnlyAlertOnce(z);
        this.y.notify(1, Build.VERSION.SDK_INT >= 16 ? this.z.build() : this.z.getNotification());
    }

    public long p() {
        Iterator<b> it = this.C.iterator();
        long j = 1;
        while (it.hasNext()) {
            long j2 = it.next().e;
            if (j2 > 0) {
                j += j2 - 1;
            }
        }
        return j;
    }

    public void s() {
        this.q = false;
        this.m = 1;
        this.a.h++;
        a aVar = new a();
        aVar.setName("CeShi_" + aVar.getId());
        aVar.start();
    }

    public void t(int i2) {
        this.m = i2;
        if (i2 == 0) {
            this.j = 0L;
            this.q = false;
            this.a.k.j();
        } else if (i2 == 3 || i2 == -2) {
            this.q = true;
        }
    }

    public synchronized void u(long j, ByteBuffer byteBuffer) {
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            this.B.write(byteBuffer, j);
        }
        c(false);
    }
}
