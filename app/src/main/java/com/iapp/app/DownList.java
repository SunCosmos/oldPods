package com.iapp.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.oldpods.app.R;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class DownList extends AppCompatActivity implements View.OnClickListener {
    private d a;

    /* renamed from: c, reason: collision with root package name */
    private ImageButton f1920c;

    /* renamed from: d, reason: collision with root package name */
    private ImageButton f1921d;
    private TextView e;
    private View f;
    private View g;
    private ListView h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f1922i;
    private ArrayList<HashMap<String, Object>> b = new ArrayList<>();
    private DecimalFormat j = new DecimalFormat("0.0");
    private Handler k = new c();

    /* loaded from: classes.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DownList.this.f1922i = false;
            DownList.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends Thread {
        b() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (DownList.this.f1922i) {
                DownList.this.k.sendEmptyMessage(1);
                try {
                    Thread.sleep(1600L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    class c extends Handler {
        c() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                DownList.this.m();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d extends BaseAdapter {
        private LayoutInflater a;
        private Object b;

        /* renamed from: c, reason: collision with root package name */
        private b f1923c;

        /* renamed from: d, reason: collision with root package name */
        private View.OnClickListener f1924d = new a();

        /* loaded from: classes.dex */
        class a implements View.OnClickListener {
            a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Boolean bool = Boolean.TRUE;
                int parseInt = Integer.parseInt(view.getTag().toString());
                if (view.getId() != R.id.ui_downlist_listitem_status_view) {
                    if (view.getId() == R.id.ui_downlist_listitem_sele_view) {
                        if (((HashMap) DownList.this.b.get(parseInt)).get("sele").equals(bool)) {
                            ((HashMap) DownList.this.b.get(parseInt)).put("sele", Boolean.FALSE);
                        } else {
                            ((HashMap) DownList.this.b.get(parseInt)).put("sele", bool);
                        }
                        DownList.this.a.notifyDataSetChanged();
                        Iterator it = DownList.this.b.iterator();
                        boolean z = false;
                        int i2 = 0;
                        while (it.hasNext()) {
                            if (((HashMap) it.next()).get("sele").equals(bool)) {
                                i2++;
                                z = true;
                            }
                        }
                        DownList downList = DownList.this;
                        if (!z) {
                            downList.g.setVisibility(8);
                            return;
                        }
                        downList.e.setText("已选中" + i2 + "个");
                        DownList.this.g.setVisibility(0);
                        return;
                    }
                    return;
                }
                c.b.a.a.w.c cVar = (c.b.a.a.w.c) ((HashMap) DownList.this.b.get(parseInt)).get("download");
                int i3 = cVar.m;
                if (i3 == 0 || i3 == 3 || i3 == -1) {
                    cVar.t(0);
                    ((HashMap) DownList.this.b.get(parseInt)).put("status_t", "等待");
                    ((HashMap) DownList.this.b.get(parseInt)).put("status_title", "暂停");
                    ((HashMap) DownList.this.b.get(parseInt)).put("status_icon", Integer.valueOf(R.mipmap.img_down_go));
                    com.iapp.app.a.b.j();
                } else {
                    if (i3 != 1) {
                        if (i3 == 2) {
                            File file = new File(((HashMap) DownList.this.b.get(parseInt)).get("dirfilename").toString());
                            if (file.exists()) {
                                Toast.makeText(DownList.this, "正在打开", 0).show();
                                try {
                                    DownList.this.n(file);
                                    return;
                                } catch (Exception unused) {
                                    Toast.makeText(DownList.this, "找不到程序打开此文件！", 1).show();
                                    return;
                                }
                            }
                            return;
                        }
                        return;
                    }
                    cVar.t(3);
                    ((HashMap) DownList.this.b.get(parseInt)).put("status_t", "已暂停");
                    ((HashMap) DownList.this.b.get(parseInt)).put("status_title", "继续");
                    ((HashMap) DownList.this.b.get(parseInt)).put("status_icon", Integer.valueOf(R.mipmap.img_down_go));
                }
                DownList.this.a.notifyDataSetChanged();
            }
        }

        /* loaded from: classes.dex */
        private final class b {
            public ImageView a;
            public TextView b;

            /* renamed from: c, reason: collision with root package name */
            public ImageView f1925c;

            /* renamed from: d, reason: collision with root package name */
            public LinearLayout f1926d;
            public ImageView e;
            public TextView f;
            public ProgressBar g;
            public TextView h;

            /* renamed from: i, reason: collision with root package name */
            public TextView f1927i;
            public TextView j;
            public LinearLayout k;

            private b(d dVar) {
            }

            /* synthetic */ b(d dVar, a aVar) {
                this(dVar);
            }
        }

        public d(Context context) {
            this.a = LayoutInflater.from(context);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return DownList.this.b.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i2) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return 0L;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            ImageView imageView;
            int i3;
            if (view == null) {
                this.f1923c = new b(this, null);
                view = this.a.inflate(R.layout.ui_downlist_listitem, (ViewGroup) null);
                this.f1923c.a = (ImageView) view.findViewById(R.id.ui_downlist_listitem_status_icon);
                this.f1923c.b = (TextView) view.findViewById(R.id.ui_downlist_listitem_status_title);
                this.f1923c.f1925c = (ImageView) view.findViewById(R.id.ui_downlist_listitem_sele);
                this.f1923c.f1926d = (LinearLayout) view.findViewById(R.id.ui_downlist_listitem_sele_view);
                this.f1923c.e = (ImageView) view.findViewById(R.id.ui_downlist_listitem_icon);
                this.f1923c.f = (TextView) view.findViewById(R.id.ui_downlist_listitem_title);
                this.f1923c.g = (ProgressBar) view.findViewById(R.id.ui_downlist_listitem_Progressbar1);
                this.f1923c.h = (TextView) view.findViewById(R.id.ui_downlist_listitem_status_t);
                this.f1923c.f1927i = (TextView) view.findViewById(R.id.ui_downlist_listitem_status_s);
                this.f1923c.j = (TextView) view.findViewById(R.id.ui_downlist_listitem_status_d);
                this.f1923c.k = (LinearLayout) view.findViewById(R.id.ui_downlist_listitem_status_view);
                view.setTag(this.f1923c);
            } else {
                this.f1923c = (b) view.getTag();
            }
            this.f1923c.a.setImageResource(((Integer) ((HashMap) DownList.this.b.get(i2)).get("status_icon")).intValue());
            this.f1923c.b.setText((String) ((HashMap) DownList.this.b.get(i2)).get("status_title"));
            if (((HashMap) DownList.this.b.get(i2)).get("sele").equals(Boolean.TRUE)) {
                imageView = this.f1923c.f1925c;
                i3 = R.mipmap.img_down_select_no;
            } else {
                imageView = this.f1923c.f1925c;
                i3 = R.mipmap.img_down_select_off;
            }
            imageView.setImageResource(i3);
            Object obj = ((HashMap) DownList.this.b.get(i2)).get("icon");
            this.b = obj;
            if (obj instanceof Bitmap) {
                this.f1923c.e.setImageBitmap((Bitmap) obj);
            } else if (obj instanceof Integer) {
                this.f1923c.e.setImageResource(Integer.parseInt(String.valueOf(obj)));
            } else {
                this.f1923c.e.setImageResource(R.mipmap.ic_launcher);
            }
            this.f1923c.f.setText((String) ((HashMap) DownList.this.b.get(i2)).get("title"));
            this.f1923c.g.setProgress(Integer.parseInt(((HashMap) DownList.this.b.get(i2)).get("Progressbar1").toString()));
            this.f1923c.h.setText((String) ((HashMap) DownList.this.b.get(i2)).get("status_t"));
            this.f1923c.f1927i.setText((String) ((HashMap) DownList.this.b.get(i2)).get("status_s"));
            this.f1923c.j.setText((String) ((HashMap) DownList.this.b.get(i2)).get("status_d"));
            this.f1923c.f1926d.setTag(Integer.valueOf(i2));
            this.f1923c.k.setTag(Integer.valueOf(i2));
            this.f1923c.f1926d.setOnClickListener(this.f1924d);
            this.f1923c.k.setOnClickListener(this.f1924d);
            return view;
        }
    }

    private String j(double d2) {
        double d3 = d2 / 1024.0d;
        if (d3 <= 1024.0d) {
            return this.j.format(d3) + "K";
        }
        double d4 = d3 / 1024.0d;
        if (d4 > 1024.0d) {
            return this.j.format(d4 / 1024.0d) + "G";
        }
        return this.j.format(d4) + "M";
    }

    private String k(int i2, long j, long j2) {
        StringBuilder sb;
        String str;
        long j3 = j2 - j;
        if (i2 < 1 || j3 < 1) {
            return "";
        }
        int i3 = (int) (j3 / i2);
        if (i3 > 120) {
            int i4 = i3 / 60;
            if (i4 > 60) {
                sb = new StringBuilder();
                sb.append("还剩");
                sb.append(i4 / 60);
                str = "小时";
            } else {
                sb = new StringBuilder();
                sb.append("还剩");
                sb.append(i4);
                str = "分";
            }
        } else {
            sb = new StringBuilder();
            sb.append("还剩");
            sb.append(i3);
            str = "秒";
        }
        sb.append(str);
        return sb.toString();
    }

    private void l() {
        this.f1922i = true;
        this.f1920c = (ImageButton) findViewById(R.id.ui_downlist_seleall);
        this.f1921d = (ImageButton) findViewById(R.id.ui_downlist_delete);
        this.e = (TextView) findViewById(R.id.ui_downlist_seletitle);
        this.f = findViewById(R.id.ui_downlist_nof);
        this.g = findViewById(R.id.ui_downlist_top_2);
        this.h = (ListView) findViewById(R.id.ui_downlist_list);
        d dVar = new d(this);
        this.a = dVar;
        this.h.setAdapter((ListAdapter) dVar);
        new b().start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        Iterator<c.b.a.a.w.c> it = com.iapp.app.a.b.f1379c.iterator();
        while (it.hasNext()) {
            c.b.a.a.w.c next = it.next();
            boolean z = false;
            Iterator<HashMap<String, Object>> it2 = this.b.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                HashMap<String, Object> next2 = it2.next();
                if (next2.get("id").equals(Integer.valueOf(next.b))) {
                    o(next, next2);
                    z = true;
                    break;
                }
            }
            if (!z) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("id", Integer.valueOf(next.b));
                hashMap.put("sele", Boolean.FALSE);
                hashMap.put("icon", next.v);
                hashMap.put("title", next.u);
                o(next, hashMap);
                hashMap.put("dirfilename", next.f1382d);
                hashMap.put("download", next);
                this.b.add(hashMap);
            }
        }
        this.a.notifyDataSetChanged();
        if (this.b.size() <= 0 || this.f.getVisibility() != 0) {
            return;
        }
        this.f.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        c.b.a.a.k.a(this, intent, file, c.b.a.a.p.p(file));
        startActivity(intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void o(c.b.a.a.w.c r13, java.util.HashMap<java.lang.String, java.lang.Object> r14) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.DownList.o(c.b.a.a.w.c, java.util.HashMap):void");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Boolean bool = Boolean.FALSE;
        Boolean bool2 = Boolean.TRUE;
        int id = view.getId();
        boolean z = false;
        if (id != R.id.ui_downlist_delete) {
            if (id != R.id.ui_downlist_seleall) {
                return;
            }
            Iterator<HashMap<String, Object>> it = this.b.iterator();
            while (it.hasNext()) {
                HashMap<String, Object> next = it.next();
                if (next.get("sele").equals(bool)) {
                    z = true;
                    next.put("sele", bool2);
                }
            }
            if (!z) {
                Iterator<HashMap<String, Object>> it2 = this.b.iterator();
                while (it2.hasNext()) {
                    it2.next().put("sele", bool);
                }
                this.g.setVisibility(8);
            }
            this.a.notifyDataSetChanged();
            return;
        }
        Iterator it3 = ((ArrayList) this.b.clone()).iterator();
        while (it3.hasNext()) {
            HashMap hashMap = (HashMap) it3.next();
            if (hashMap.get("sele").equals(bool2)) {
                c.b.a.a.w.c cVar = (c.b.a.a.w.c) hashMap.get("download");
                cVar.t(-2);
                com.iapp.app.a.b.f1379c.remove(cVar);
                this.b.remove(hashMap);
            }
        }
        this.g.setVisibility(8);
        this.a.notifyDataSetChanged();
        if (this.b.size() == 0 && this.f.getVisibility() == 8) {
            this.f.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ui_downlist);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String string = extras.getString("background");
            String string2 = extras.getString("backgroundShadow");
            if (string != null && string2 != null) {
                findViewById(R.id.ui_downlist_bar).setBackgroundColor(Color.parseColor(string));
                c.b.a.a.r.c(this, Color.parseColor(string2), true, findViewById(R.id.ui_downlist_view));
            }
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.ui_downlist_top);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setHomeButtonEnabled(true);
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new a());
        l();
        this.f1920c.setOnClickListener(this);
        this.f1921d.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.f1922i = false;
        super.onDestroy();
    }
}
