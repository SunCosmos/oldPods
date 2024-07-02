package c.b.a.a.w;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import c.b.a.a.d;
import c.b.a.a.i;
import c.b.a.a.p;
import com.oldpods.app.R;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class b {
    private c.b.a.a.w.a a;
    private Context b;

    /* renamed from: c, reason: collision with root package name */
    public ArrayList<c> f1379c = new ArrayList<>();

    /* renamed from: d, reason: collision with root package name */
    private boolean f1380d = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends Thread {
        a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            boolean z;
            while (b.this.f1380d) {
                if (b.this.a.h < b.this.a.f1377d) {
                    Iterator<c> it = b.this.f1379c.iterator();
                    while (true) {
                        z = true;
                        if (!it.hasNext()) {
                            break;
                        }
                        c next = it.next();
                        if (next.m == 0 && !next.q) {
                            next.m = 1;
                            next.s();
                            z = false;
                            break;
                        }
                    }
                    if (z) {
                        b.this.f1380d = false;
                        return;
                    }
                }
                try {
                    Thread.sleep(300L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public b(Context context, String str, String str2, int i2, int i3, int i4, int i5, boolean z) {
        this.a = null;
        this.b = null;
        c.b.a.a.w.a aVar = new c.b.a.a.w.a();
        this.a = aVar;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        String str3 = File.separator;
        sb.append(str3);
        aVar.a = sb.toString();
        this.a.b = str2 + str3;
        c.b.a.a.w.a aVar2 = this.a;
        aVar2.f1377d = i2;
        aVar2.e = i3;
        aVar2.f = i4;
        aVar2.g = i5;
        aVar2.f1378i = z;
        aVar2.j = context;
        aVar2.k = this;
        this.b = context;
        k();
    }

    private Object i(Object obj) {
        if ((obj instanceof Bitmap) || (obj instanceof Integer)) {
            return obj;
        }
        String valueOf = String.valueOf(obj);
        if (valueOf.startsWith("@")) {
            Context context = this.b;
            return i.b(context, d.p(context, valueOf));
        }
        if (valueOf.startsWith("%") || valueOf.startsWith("$")) {
            return i.c(d.p(this.b, valueOf));
        }
        if (valueOf.matches("[0-9]+")) {
            return Integer.valueOf(Integer.parseInt(valueOf));
        }
        return null;
    }

    private void k() {
        d.b(this.a.a, true);
        d.b(this.a.b, true);
        this.f1380d = false;
    }

    public c d(String str, String str2, Object obj) {
        return e(str, str2, str2, obj);
    }

    public c e(String str, String str2, String str3, Object obj) {
        boolean endsWith = str2.endsWith(".zip");
        int i2 = R.mipmap.img_list_myu;
        if (endsWith || str2.endsWith(".rar") || str2.endsWith(".7z")) {
            i2 = R.mipmap.img_list_zip;
        } else if (str2.endsWith(".mp3")) {
            i2 = R.mipmap.img_list_mp3;
        } else if (str2.endsWith(".mp4")) {
            i2 = R.mipmap.img_list_mp4;
        } else if (str2.endsWith(".xml")) {
            i2 = R.mipmap.img_list_xml;
        } else if (str2.endsWith(".txt")) {
            i2 = R.mipmap.img_list_txt;
        } else if (!str2.endsWith(".myu") && !str2.endsWith(".iyu")) {
            if (str2.endsWith(".apk")) {
                i2 = android.R.drawable.sym_def_app_icon;
            } else if (!str2.endsWith(".iapp")) {
                i2 = R.mipmap.img_list_object;
            }
        }
        return g(str, this.a.b, str2, str3, Integer.valueOf(i2), this.a.f1378i, obj);
    }

    public c f(String str, String str2, String str3, Object obj, Object obj2) {
        c.b.a.a.w.a aVar = this.a;
        return g(str, aVar.b, str2, str3, obj, aVar.f1378i, obj2);
    }

    public c g(String str, String str2, String str3, String str4, Object obj, boolean z, Object obj2) {
        String str5 = str;
        if (str5.contains(" ")) {
            str5 = str5.replace(" ", "%20");
        }
        if (str5.getBytes().length != str5.length()) {
            StringBuilder sb = new StringBuilder();
            for (char c2 : str5.toCharArray()) {
                String valueOf = String.valueOf(c2);
                if (valueOf.getBytes().length == 1) {
                    sb.append(c2);
                } else {
                    sb.append(Uri.encode(valueOf));
                }
            }
            str5 = sb.toString();
        }
        String str6 = str5;
        String replace = str2.replace('\\', '/');
        if (!replace.endsWith("/")) {
            replace = replace + File.separator;
        }
        String str7 = replace;
        d.b(str7, true);
        c cVar = null;
        try {
            cVar = new c(this.a, 0, str6, str7, str3, p.d(str6.toLowerCase()), obj2, str4, i(obj), z);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (cVar != null) {
            this.f1379c.add(cVar);
            cVar.b = this.f1379c.size() - 1;
            j();
        }
        return cVar;
    }

    public Object h(c cVar, String str) {
        if (str.equals("id")) {
            return Integer.valueOf(cVar.b);
        }
        if (str.equals("url")) {
            return cVar.f1381c;
        }
        if (str.equals("dirfilename")) {
            return cVar.f1382d;
        }
        if (str.equals("urlmd5")) {
            return cVar.e;
        }
        if (str.equals("dir")) {
            return cVar.f;
        }
        if (str.equals("filename")) {
            return cVar.g;
        }
        if (str.equals("contentlength")) {
            return Long.valueOf(cVar.h);
        }
        if (str.equals("equivalent")) {
            return Long.valueOf(cVar.j);
        }
        if (str.equals("downloadspeed")) {
            return Integer.valueOf(cVar.k);
        }
        if (str.equals("downloadpercentage")) {
            return Integer.valueOf(cVar.l);
        }
        if (str.equals("status")) {
            return Integer.valueOf(cVar.m);
        }
        if (str.equals("notificationshow")) {
            return Boolean.valueOf(cVar.r);
        }
        if (str.equals("text")) {
            return cVar.t;
        }
        if (str.equals("title")) {
            return cVar.u;
        }
        if (str.equals("icon")) {
            return cVar.v;
        }
        return null;
    }

    public void j() {
        if (this.f1380d) {
            return;
        }
        this.f1380d = true;
        a aVar = new a();
        aVar.setName("CeShi_" + aVar.getId());
        aVar.start();
    }

    public void l(c cVar, String str, Object obj) {
        if (str.equals("status")) {
            cVar.t(Integer.parseInt(String.valueOf(obj)));
            return;
        }
        if (str.equals("notificationshow")) {
            cVar.r = obj.equals(Boolean.TRUE);
            return;
        }
        if (str.equals("text")) {
            cVar.t = obj;
            return;
        }
        if (!str.equals("title")) {
            if (str.equals("icon")) {
                cVar.v = i(obj);
            }
        } else {
            cVar.u = String.valueOf(obj);
            cVar.w = "“" + cVar.u + "”下载";
        }
    }

    public void m(String str, String str2, int i2, int i3, int i4, int i5, boolean z) {
        String replace = str.replace('\\', '/');
        if (!replace.endsWith("/")) {
            replace = replace + File.separator;
        }
        String replace2 = str2.replace('\\', '/');
        if (!replace2.endsWith("/")) {
            replace2 = replace2 + File.separator;
        }
        c.b.a.a.w.a aVar = this.a;
        aVar.a = replace;
        aVar.b = replace2;
        d.b(replace, true);
        d.b(replace2, true);
        c.b.a.a.w.a aVar2 = this.a;
        aVar2.f1377d = i2;
        aVar2.e = i3;
        aVar2.f = i4;
        aVar2.g = i5;
        aVar2.f1378i = z;
    }
}
