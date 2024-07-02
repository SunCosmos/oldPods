package c.b.a.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class h {
    private static String n;
    public static h o;
    public Context a;
    public HashMap<String, SoftReference<Drawable>> b;

    /* renamed from: c */
    private ArrayList<c> f1346c;

    /* renamed from: d */
    private ArrayList<String> f1347d;
    private ArrayList<c> e;
    private ArrayList<String> f;
    private BaseAdapter g;
    private RecyclerView.Adapter h;

    /* renamed from: i */
    private FragmentStatePagerAdapter f1348i;
    private int j;
    private AtomicInteger k;
    private Handler l;
    private boolean m;

    /* loaded from: classes.dex */
    public class a extends Thread {
        a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                int size = h.this.f1346c.size();
                if (size <= 0) {
                    h.this.l.sendEmptyMessage(1);
                    h.this.m = false;
                    return;
                } else if (h.this.k.get() < 3) {
                    h.this.k.addAndGet(1);
                    int i2 = h.this.j == 1000 ? size - 1 : 0;
                    try {
                        c cVar = (c) h.this.f1346c.get(i2);
                        h.this.f1346c.remove(i2);
                        h.this.f1347d.remove(i2);
                        new d(cVar).start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    h.this.b(300L);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                h.this.u();
                return;
            }
            if (i2 != 2) {
                return;
            }
            c cVar = (c) message.obj;
            if (!cVar.h) {
                ImageView imageView = cVar.e;
                if (imageView == null) {
                    View view = cVar.f;
                    if (!(view instanceof ImageView)) {
                        return;
                    } else {
                        imageView = (ImageView) view;
                    }
                }
                imageView.setImageDrawable(cVar.g);
                return;
            }
            ImageView imageView2 = cVar.e;
            if (imageView2 != null) {
                imageView2.setBackgroundDrawable(cVar.g);
                return;
            }
            View view2 = cVar.f;
            if (view2 != null) {
                view2.setBackgroundDrawable(cVar.g);
            }
        }
    }

    /* loaded from: classes.dex */
    public class c {
        public String a;
        public String b;

        /* renamed from: c */
        public String f1349c;

        /* renamed from: d */
        public String f1350d;
        public ImageView e;
        public View f;
        public Drawable g;
        public boolean h;

        public c(h hVar, ImageView imageView, String str, String str2) {
            this.a = null;
            this.b = null;
            this.f1349c = null;
            this.f1350d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = false;
            this.e = imageView;
            this.b = str;
            this.f1350d = str2;
        }

        public c(h hVar, String str, String str2, View view, boolean z) {
            this.a = null;
            this.b = null;
            this.f1349c = null;
            this.f1350d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = false;
            this.a = str;
            this.f1349c = str2;
            this.f = view;
            this.h = z;
        }

        public c(h hVar, String str, String str2, ImageView imageView) {
            this.a = null;
            this.b = null;
            this.f1349c = null;
            this.f1350d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = false;
            this.a = str;
            this.f1349c = str2;
            this.e = imageView;
        }
    }

    /* loaded from: classes.dex */
    public class d extends Thread {
        private c a;

        public d(c cVar) {
            this.a = cVar;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            c cVar = this.a;
            if (cVar.a == null) {
                h.this.q(cVar);
            } else {
                h.this.t(cVar);
            }
            h.this.k.addAndGet(-1);
        }
    }

    public h(Context context, int i2) {
        this.b = new HashMap<>();
        this.f1346c = new ArrayList<>();
        this.f1347d = new ArrayList<>();
        this.e = new ArrayList<>();
        this.f = new ArrayList<>();
        this.g = null;
        this.h = null;
        this.f1348i = null;
        this.j = 0;
        this.k = new AtomicInteger(0);
        this.m = false;
        this.a = context;
        this.j = i2;
        n();
    }

    public h(Context context, BaseAdapter baseAdapter, int i2) {
        this.b = new HashMap<>();
        this.f1346c = new ArrayList<>();
        this.f1347d = new ArrayList<>();
        this.e = new ArrayList<>();
        this.f = new ArrayList<>();
        this.g = null;
        this.h = null;
        this.f1348i = null;
        this.j = 0;
        this.k = new AtomicInteger(0);
        this.m = false;
        this.a = context;
        this.g = baseAdapter;
        this.j = i2;
        n();
    }

    public h(Context context, FragmentStatePagerAdapter fragmentStatePagerAdapter, int i2) {
        this.b = new HashMap<>();
        this.f1346c = new ArrayList<>();
        this.f1347d = new ArrayList<>();
        this.e = new ArrayList<>();
        this.f = new ArrayList<>();
        this.g = null;
        this.h = null;
        this.f1348i = null;
        this.j = 0;
        this.k = new AtomicInteger(0);
        this.m = false;
        this.a = context;
        this.f1348i = fragmentStatePagerAdapter;
        this.j = i2;
        n();
    }

    public h(Context context, RecyclerView.Adapter adapter, int i2) {
        this.b = new HashMap<>();
        this.f1346c = new ArrayList<>();
        this.f1347d = new ArrayList<>();
        this.e = new ArrayList<>();
        this.f = new ArrayList<>();
        this.g = null;
        this.h = null;
        this.f1348i = null;
        this.j = 0;
        this.k = new AtomicInteger(0);
        this.m = false;
        this.a = context;
        this.h = adapter;
        this.j = i2;
        n();
    }

    public static void a(Context context, View view, String str, String str2, boolean z) {
        if (o == null) {
            o = new h(context, 2);
        }
        SoftReference<Drawable> softReference = o.b.get(str2);
        Drawable drawable = softReference == null ? null : softReference.get();
        if (drawable == null) {
            o.r(view, str, str2, z);
        } else if (z) {
            view.setBackgroundDrawable(drawable);
        } else if (view instanceof ImageView) {
            ((ImageView) view).setImageDrawable(drawable);
        }
    }

    public void b(long j) {
        try {
            Thread.sleep(j);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void n() {
        if (n == null) {
            n = this.a.getFilesDir().getAbsolutePath();
        }
        this.l = new b(this.a.getMainLooper());
    }

    private synchronized void o(String str, Drawable drawable) {
        while (true) {
            int indexOf = this.f.indexOf(str);
            if (indexOf != -1) {
                c cVar = this.e.get(indexOf);
                this.e.remove(cVar);
                this.f.remove(str);
                cVar.g = drawable;
                w(2, cVar);
            }
        }
    }

    public void q(c cVar) {
        int i2 = this.j;
        if (i2 == 1) {
            Bitmap b2 = f.b(this.a, cVar.b);
            this.b.put(cVar.f1350d, new SoftReference<>(new BitmapDrawable(this.a.getResources(), b2)));
            if (b2 != null) {
                this.l.sendEmptyMessage(1);
                return;
            }
            return;
        }
        if (i2 == 2 || i2 == 3) {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(this.a.getResources(), f.b(this.a, cVar.b));
            this.b.put(cVar.f1350d, new SoftReference<>(bitmapDrawable));
            cVar.g = bitmapDrawable;
            w(2, cVar);
            if (this.f.contains(cVar.f1350d)) {
                o(cVar.f1350d, bitmapDrawable);
            }
        }
    }

    public void t(c cVar) {
        int i2 = this.j;
        if (i2 == 1) {
            cVar.b = String.format("%s/temp/%s.img", n, p.d(cVar.a));
            File file = new File(cVar.b);
            if ((!file.exists() || new Date().getTime() - file.lastModified() < 1800000) && g.a(cVar.a, cVar.b, false) < 0) {
                return;
            }
            Bitmap c2 = f.c(this.a, cVar.b);
            this.b.put(cVar.f1349c, new SoftReference<>(new BitmapDrawable(this.a.getResources(), c2)));
            if (c2 != null) {
                this.l.sendEmptyMessage(1);
                return;
            }
            return;
        }
        if (i2 == 2 || i2 == 3) {
            cVar.b = String.format("%s/temp/%s.img", n, p.d(cVar.a));
            File file2 = new File(cVar.b);
            if ((!file2.exists() || new Date().getTime() - file2.lastModified() < 1800000) && g.a(cVar.a, cVar.b, false) < 0) {
                return;
            }
            BitmapDrawable bitmapDrawable = new BitmapDrawable(this.a.getResources(), f.b(this.a, cVar.b));
            this.b.put(cVar.f1349c, new SoftReference<>(bitmapDrawable));
            cVar.g = bitmapDrawable;
            w(2, cVar);
            if (this.f.contains(cVar.f1349c)) {
                o(cVar.f1349c, bitmapDrawable);
            }
        }
    }

    public void u() {
        BaseAdapter baseAdapter = this.g;
        if (baseAdapter != null) {
            baseAdapter.notifyDataSetChanged();
            return;
        }
        RecyclerView.Adapter adapter = this.h;
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            return;
        }
        FragmentStatePagerAdapter fragmentStatePagerAdapter = this.f1348i;
        if (fragmentStatePagerAdapter != null) {
            fragmentStatePagerAdapter.notifyDataSetChanged();
        }
    }

    private void v() {
        if (this.m) {
            return;
        }
        this.m = true;
        new a().start();
    }

    private void w(int i2, Object obj) {
        Message message = new Message();
        message.what = i2;
        message.obj = obj;
        this.l.sendMessage(message);
    }

    public void m() {
        this.b.clear();
        this.f1346c.clear();
        this.f1347d.clear();
        this.e.clear();
        this.f.clear();
        h hVar = o;
        if (hVar != null) {
            hVar.b.clear();
            o.f1346c.clear();
            o.f1347d.clear();
            o.e.clear();
            o.f.clear();
            o = null;
        }
    }

    public void p(ImageView imageView, String str, String str2, boolean z) {
        if (str == null) {
            imageView.setImageDrawable(null);
            return;
        }
        if (z) {
            imageView.setImageDrawable(null);
        }
        if (!this.f1347d.contains(str2)) {
            this.f1346c.add(new c(this, imageView, str, str2));
            this.f1347d.add(str2);
            v();
        } else {
            int i2 = this.j;
            if (i2 == 2 || i2 == 3) {
                this.e.add(new c(this, imageView, str, str2));
                this.f.add(str2);
            }
        }
    }

    public void r(View view, String str, String str2, boolean z) {
        if (!this.f1347d.contains(str2)) {
            this.f1346c.add(new c(this, str, str2, view, z));
            this.f1347d.add(str2);
            v();
        } else {
            int i2 = this.j;
            if (i2 == 2 || i2 == 3) {
                this.e.add(new c(this, str, str2, view, z));
                this.f.add(str2);
            }
        }
    }

    public void s(ImageView imageView, String str, String str2, boolean z) {
        if (str == null) {
            imageView.setImageDrawable(null);
            return;
        }
        if (z) {
            imageView.setImageDrawable(null);
        }
        if (!this.f1347d.contains(str2)) {
            this.f1346c.add(new c(this, str, str2, imageView));
            this.f1347d.add(str2);
            v();
        } else {
            int i2 = this.j;
            if (i2 == 2 || i2 == 3) {
                this.e.add(new c(this, str, str2, imageView));
                this.f.add(str2);
            }
        }
    }
}
