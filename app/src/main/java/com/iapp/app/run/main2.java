package com.iapp.app.run;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import c.b.a.a.p;
import com.iapp.app.n;
import com.iapp.app.o;
import com.iapp.app.q;
import com.iapp.app.t;
import com.oldpods.app.R;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"UseSparseArrays"})
/* loaded from: classes.dex */
public class main2 extends iActivity {
    private static HashMap<String, Object> I = new HashMap<>();
    public int TaskId;

    /* renamed from: c, reason: collision with root package name */
    private LinearLayout f1974c;
    private WebView h;

    /* renamed from: d, reason: collision with root package name */
    private HashMap<String, Object> f1975d = new HashMap<>();
    private String[] e = null;
    private c.c.a.b f = new c.c.a.b(this);
    private String g = null;
    public String r = null;

    /* renamed from: i, reason: collision with root package name */
    private SensorEventListener f1976i = null;
    private SensorManager j = null;
    private Sensor k = null;
    private String l = "";
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;
    private boolean A = false;
    private boolean B = false;
    private boolean C = false;
    private boolean D = false;
    private boolean E = false;
    private boolean F = false;
    private boolean G = false;
    private boolean H = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements AbsListView.OnScrollListener {
        a() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            if (main2.this.B) {
                int id = absListView.getId();
                main2.this.callMethod("onscroll" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "', " + i2 + ", " + i3 + ", " + i4);
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (main2.this.A) {
                int id = absListView.getId();
                main2.this.callMethod("onscrollstatechanged" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "', " + i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements AdapterView.OnItemClickListener {
        b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            int id = adapterView.getId();
            main2.this.callMethod("clickitem" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "', " + i2 + ", " + j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements AdapterView.OnItemSelectedListener {
        c() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            if (main2.this.C) {
                int id = adapterView.getId();
                StringBuilder sb = new StringBuilder();
                sb.append(id);
                sb.append(", '^view");
                sb.append(main2.this.TaskId);
                sb.append("st_vW");
                sb.append(id);
                sb.append("', '");
                sb.append(main2.set("^onitemselected" + main2.this.TaskId + "st_vW2" + id, view));
                sb.append("', ");
                sb.append(i2);
                sb.append(", ");
                sb.append(j);
                main2.this.callMethod("onitemselected" + id, sb.toString());
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            if (main2.this.D) {
                int id = adapterView.getId();
                main2.this.callMethod("onnothingselected" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "'");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements SeekBar.OnSeekBarChangeListener {
        d() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            if (main2.this.G) {
                int id = seekBar.getId();
                main2.this.callMethod("onprogresschanged2" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "', " + i2 + ", " + z);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (main2.this.E) {
                int id = seekBar.getId();
                main2.this.callMethod("onstarttrackingtouch" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "'");
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (main2.this.F) {
                int id = seekBar.getId();
                main2.this.callMethod("onstoptrackingtouch" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "'");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements com.iapp.app.x5.a {
        e(main2 main2Var) {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            String lowerCase = str4.toLowerCase();
            String c2 = str3 != null ? p.c(str3, "filename=\"", "\"") : null;
            if (c2 == null) {
                if (str.contains("?")) {
                    str = str.substring(0, str.indexOf(63));
                }
                c2 = p.b(str);
                String lowerCase2 = c2.toLowerCase();
                if (lowerCase.equals("application/vnd.android.package-archive") && !lowerCase2.endsWith(".apk")) {
                    c2 = c2 + ".apk";
                }
            }
            if (c2 != null) {
                com.iapp.app.a.b.d(str, c2, null);
            }
        }
    }

    /* loaded from: classes.dex */
    class f implements SensorEventListener {
        f() {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            main2.this.callMethod("sensor", sensorEvent.values[0] + ", " + sensorEvent.values[1] + ", " + sensorEvent.values[2]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g implements View.OnClickListener {
        g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            main2.this.callMethod("clicki" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "'");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h implements View.OnTouchListener {
        h() {
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int id = view.getId();
            main2.this.callMethod("touchmonitor" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "', " + motionEvent.getAction() + ", " + motionEvent.getX() + ", " + motionEvent.getY() + ", " + motionEvent.getRawX() + ", " + motionEvent.getRawY());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class i implements View.OnLongClickListener {
        i() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            int id = view.getId();
            main2.this.callMethod("press" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "'");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class j implements View.OnKeyListener {
        j() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            int id = view.getId();
            main2.this.callMethod("keyboard" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "', " + i2 + ", " + keyEvent.getAction() + ", " + keyEvent.getRepeatCount());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class k implements View.OnCreateContextMenuListener {
        k() {
        }

        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            String c2 = p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"pressmenu\">", "</eventItme>");
            contextMenu.setHeaderTitle(p.c(c2, "title:", "\n"));
            int id = view.getId();
            String[] split = c2.split("\ncase ");
            for (int i2 = 1; i2 < split.length; i2++) {
                contextMenu.add(id, i2, 0, p.c(split[i2], null, ":"));
            }
            if (p.c(c2, "\ndefault:", "\nbreak") != null) {
                main2.this.callMethod("onCreateContextMenu" + id + "x0", id + ", '^view" + main2.this.TaskId + "st_vW" + id + "'");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class l implements TextView.OnEditorActionListener {
        l() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            int id = textView.getId();
            if (keyEvent == null) {
                main2.this.callMethod("editormonitor" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "', " + i2 + ", null, null, null");
                return false;
            }
            main2.this.callMethod("editormonitor" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "', " + i2 + ", " + keyEvent.getAction() + ", " + keyEvent.getRepeatCount() + ", " + keyEvent.getKeyCode());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class m implements View.OnFocusChangeListener {
        m() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            int id = view.getId();
            main2.this.callMethod("focuschange" + id, id + ", '^view" + main2.this.TaskId + "st_vW" + id + "', " + z);
        }
    }

    public static Object get(String str) {
        return I.get(str);
    }

    private void l(View view, int i2, String str) {
        set("^view" + this.TaskId + "st_vW" + i2, view);
        if (addViewEventItme(str, i2, "clicki", "st_vId,st_vW")) {
            view.setOnClickListener(new g());
        }
        if (addViewEventItme(str, i2, "touchmonitor", "st_vId,st_vW,st_eA,st_eX,st_eY,st_rX,st_rY")) {
            view.setOnTouchListener(new h());
        }
        if (addViewEventItme(str, i2, "press", "st_vId,st_vW")) {
            view.setOnLongClickListener(new i());
        }
        if (addViewEventItme(str, i2, "keyboard", "st_vId,st_vW,st_kC,st_eA,st_eR")) {
            view.setOnKeyListener(new j());
        }
        if (str.contains("<eventItme type=\"pressmenu\">")) {
            String c2 = p.c(str, "<eventItme type=\"pressmenu\">", "</eventItme>");
            String[] split = c2.split("\ncase ");
            for (int i3 = 1; i3 < split.length; i3++) {
                this.l += "function onCreateContextMenu" + i2 + "x" + i3 + "(){\n" + p.c(split[i3], ":", "\nbreak") + "\n}\n";
            }
            String c3 = p.c(c2, "\ndefault:", "\nbreak");
            if (c3 != null) {
                this.l += "function onCreateContextMenu" + i2 + "x0(st_vId,st_vW){\n" + c3 + "\n}\n";
            }
            view.setOnCreateContextMenuListener(new k());
        }
        if (view instanceof TextView) {
            if (addViewEventItme(str, i2, "editormonitor", "st_vId,st_vW,st_aI,st_eA,st_eR,st_eK")) {
                ((TextView) view).setOnEditorActionListener(new l());
            }
            boolean addViewEventItme = addViewEventItme(str, i2, "ontextchanged", "st_vId,st_vW,st_sS,st_sT,st_bE,st_cT");
            boolean addViewEventItme2 = addViewEventItme(str, i2, "beforetextchanged", "st_vId,st_vW,st_sS,st_sT,st_cT,st_aR");
            boolean addViewEventItme3 = addViewEventItme(str, i2, "aftertextchanged", "st_vId,st_vW,st_sS");
            if (addViewEventItme || addViewEventItme2 || addViewEventItme3) {
                new t((TextView) view, this.h, "^view" + this.TaskId + "st_vW" + i2, addViewEventItme, addViewEventItme2, addViewEventItme3);
            }
        }
        if (view instanceof com.iapp.app.x5.WebView) {
            r((com.iapp.app.x5.WebView) view, str);
        }
        if (addViewEventItme(str, i2, "focuschange", "st_vId,st_vW,st_hF")) {
            view.setOnFocusChangeListener(new m());
        }
        if (view instanceof AbsListView) {
            this.A = addViewEventItme(str, i2, "onscrollstatechanged", "st_vId,st_vW,st_sE");
            boolean addViewEventItme4 = addViewEventItme(str, i2, "onscroll", "st_vId,st_vW,st_fM,st_vT,st_bT");
            this.B = addViewEventItme4;
            if (this.A || addViewEventItme4) {
                ((AbsListView) view).setOnScrollListener(new a());
            }
        }
        if (view instanceof AdapterView) {
            if (addViewEventItme(str, i2, "clickitem", "st_vId,st_vW,st_pN,st_iD")) {
                ((AdapterView) view).setOnItemClickListener(new b());
            }
            this.C = addViewEventItme(str, i2, "onitemselected", "st_vId,st_vW,st_vW2,st_pN,st_iD");
            boolean addViewEventItme5 = addViewEventItme(str, i2, "onnothingselected", "st_vId,st_vW");
            this.D = addViewEventItme5;
            if (this.C || addViewEventItme5) {
                ((AdapterView) view).setOnItemSelectedListener(new c());
            }
        }
        if (view instanceof ViewPager) {
            boolean addViewEventItme6 = addViewEventItme(str, i2, "onpageselected", "st_vId,st_vW,st_pN");
            boolean addViewEventItme7 = addViewEventItme(str, i2, "onpagescrolled", "st_vId,st_vW,st_pN,st_pT,st_pS");
            boolean addViewEventItme8 = addViewEventItme(str, i2, "onpagescrollstatechanged", "st_vId,st_vW,st_sE");
            if (addViewEventItme6 || addViewEventItme7 || addViewEventItme8) {
                new q((ViewPager) view, this.h, "^view" + this.TaskId + "st_vW" + i2, addViewEventItme6, addViewEventItme7, addViewEventItme8);
            }
        }
        if (view instanceof DrawerLayout) {
            boolean addViewEventItme9 = addViewEventItme(str, i2, "ondrawerclosed", "st_vId,st_vW,st_dW");
            boolean addViewEventItme10 = addViewEventItme(str, i2, "ondraweropened", "st_vId,st_vW,st_dW");
            boolean addViewEventItme11 = addViewEventItme(str, i2, "onoptionsitemselected", "st_vId,st_vW,st_iM");
            if (addViewEventItme9 || addViewEventItme10 || addViewEventItme11) {
                new o((DrawerLayout) view, this.h, "^view" + this.TaskId + "st_vW" + i2, this.TaskId, addViewEventItme9, addViewEventItme10, addViewEventItme11);
            }
        }
        if (view instanceof SeekBar) {
            if (str.contains("<eventItme type=\"onstarttrackingtouch\">") || str.contains("<eventItme type=\"onstoptrackingtouch\">") || str.contains("<eventItme type=\"onprogresschanged2\">")) {
                this.E = addViewEventItme(str, i2, "onstarttrackingtouch", "st_vId,st_vW");
                this.F = addViewEventItme(str, i2, "onstoptrackingtouch", "st_vId,st_vW");
                boolean addViewEventItme12 = addViewEventItme(str, i2, "onprogresschanged2", "st_vId,st_vW,st_nS,st_fR");
                this.G = addViewEventItme12;
                if (this.E || this.F || addViewEventItme12) {
                    ((SeekBar) view).setOnSeekBarChangeListener(new d());
                }
            }
        }
    }

    private boolean m(String str, String str2) {
        if (!str.contains("<eventItme type=\"" + str2 + "\">")) {
            return false;
        }
        String c2 = p.c(str, "<eventItme type=\"" + str2 + "\">", "</eventItme>");
        if (c2 == null) {
            return false;
        }
        this.l += "function " + str2 + "(){\n" + c2 + "\n}\n";
        return true;
    }

    private boolean n(String str, String str2, String str3) {
        if (!str.contains("<eventItme type=\"" + str2 + "\">")) {
            return false;
        }
        String c2 = p.c(str, "<eventItme type=\"" + str2 + "\">", "</eventItme>");
        if (c2 == null) {
            return false;
        }
        this.l += "function " + str2 + "(" + str3 + "){\n" + c2 + "\n}\n";
        return true;
    }

    private void o(String str) {
        this.h.loadUrl("javascript:" + str + "()");
    }

    private String p(String str, String str2) {
        if (!str.contains("<eventItme type=\"" + str2 + "\">")) {
            return null;
        }
        String c2 = p.c(str, "<eventItme type=\"" + str2 + "\">", "</eventItme>");
        if (c2 != null) {
            return c2;
        }
        return null;
    }

    @TargetApi(11)
    private void q(com.iapp.app.x5.WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    @TargetApi(16)
    private void r(com.iapp.app.x5.WebView webView, String str) {
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAppCachePath(getApplicationContext().getDir("cache", 0).getPath());
        webView.getSettings().setAppCacheMaxSize(8388608L);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDatabasePath(getApplicationContext().getDir("database", 0).getPath());
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setLightTouchEnabled(true);
        com.iapp.app.x5.c.a(webView);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= 16) {
            webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
            webView.getSettings().setAllowFileAccessFromFileURLs(true);
        }
        webView.setScrollBarStyle(0);
        if (addViewEventItme(str, webView.getId(), "ondownloadstart", "st_vId,st_vW,st_url,st_uT,st_cN,st_mE,st_cH")) {
            new n(webView, this.h, this.TaskId);
        } else {
            webView.setDownloadListener(new e(this));
        }
        new com.iapp.app.x5.b().i(webView, str, this);
        q(webView);
    }

    private void s() {
        String c2;
        String str = this.r;
        if (str != null) {
            String p = p(str, "loading");
            if (p != null) {
                this.m = true;
                this.l += "function loading(){\n" + p + "\n}\n";
            }
            this.n = m(this.r, "loadingComplete");
            this.p = n(this.r, "downkey", "st_kC,st_eA,st_eR");
            this.q = n(this.r, "upkey", "st_kC,st_eA,st_eR");
            this.s = m(this.r, "destroy");
            this.t = m(this.r, "stop");
            this.u = m(this.r, "restart");
            this.v = m(this.r, "start");
            this.w = m(this.r, "resume");
            this.x = m(this.r, "pause");
            this.y = n(this.r, "onactivityresult", "st_sC,st_lC,st_iT");
            this.z = n(this.r, "sensor", "st_x,st_y,st_z");
            if (!this.r.contains("<eventItme type=\"menu\">") || (c2 = p.c(this.r, "<eventItme type=\"menu\">", "</eventItme>")) == null) {
                return;
            }
            String[] split = ("m\n" + c2).split("\ncase ", -1);
            this.e = new String[split.length];
            for (int i2 = 1; i2 < split.length; i2++) {
                this.e[i2] = p.c(split[i2], null, ":");
                this.l += "function CreateOptionsMenu" + i2 + "(){\n" + p.c(split[i2], ":", "\nbreak") + "\n}\n";
            }
            String c3 = p.c(c2, "\ndefault:", "\nbreak");
            if (c3 != null) {
                this.o = true;
                this.l += "function onCreateOptionsMenuloading(){\n" + c3 + "\n}\n";
            }
        }
    }

    public static String set(String str, Object obj) {
        if (I.containsKey(str)) {
            Object obj2 = I.get(str);
            if (obj == null && obj2 == null) {
                return str;
            }
            if (obj != null && obj2 != null && obj2.equals(obj)) {
                return str;
            }
        } else if (obj != null && I.containsValue(obj)) {
            for (Map.Entry<String, Object> entry : I.entrySet()) {
                if (entry.getValue().equals(obj)) {
                    return entry.getKey().toString();
                }
            }
        }
        I.put(str, obj);
        return str;
    }

    public boolean addViewEventItme(String str, int i2, String str2, String str3) {
        if (!str.contains("<eventItme type=\"" + str2 + "\">")) {
            return false;
        }
        String c2 = p.c(str, "<eventItme type=\"" + str2 + "\">", "</eventItme>");
        if (c2 == null) {
            return false;
        }
        this.l += "function " + str2 + i2 + "(" + str3 + "){\n" + c2 + "\n}\n";
        return true;
    }

    public void callMethod(String str, String str2) {
        this.h.loadUrl("javascript:" + str + "(" + str2 + ")");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iapp.app.run.iActivity
    public void d() {
        super.d();
        if (com.iapp.app.a.b == null) {
            c.b.a.a.d.t(this);
            finish();
            return;
        }
        c.b.a.a.t.k = this.f1975d;
        this.TaskId = getTaskId();
        setContentView(R.layout.ui_run_mian);
        this.h = com.iapp.app.c.a(this, this.TaskId);
        this.g = getIntent().getExtras().getString("OpenFilexmlui");
        this.f1974c = (LinearLayout) findViewById(R.id.ui_run_mian_mian);
        com.iapp.app.b.h3(this, this.g.toLowerCase());
    }

    public void g() {
        s();
        if (this.z) {
            SensorManager sensorManager = (SensorManager) getSystemService("sensor");
            this.j = sensorManager;
            Sensor defaultSensor = sensorManager.getDefaultSensor(1);
            this.k = defaultSensor;
            f fVar = new f();
            this.f1976i = fVar;
            this.j.registerListener(fVar, defaultSensor, 2);
        }
        if (this.m) {
            this.l += "\nloading();\n";
        }
        com.iapp.app.c.c(this, this.h, this.l);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0061 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = "id=\""
            java.lang.String r1 = "\""
            java.lang.String r0 = c.b.a.a.p.c(r8, r0, r1)
            r2 = -1
            int r0 = c.b.a.a.p.i(r0, r2)
            java.lang.String r3 = "did=\""
            java.lang.String r3 = c.b.a.a.p.c(r8, r3, r1)
            int r3 = c.b.a.a.p.i(r3, r2)
            java.lang.String r4 = "type=\""
            java.lang.String r1 = c.b.a.a.p.c(r8, r4, r1)
            java.lang.String r4 = "ppt"
            java.lang.String r4 = c.b.a.a.p.h(r8, r4)
            java.lang.String r5 = "event"
            java.lang.String r8 = c.b.a.a.p.h(r8, r5)
            if (r0 == r2) goto L9d
            if (r3 != r2) goto L2e
            goto L9d
        L2e:
            java.lang.String r2 = "ProgressBar"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L59
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "\n"
            r2.append(r5)
            r2.append(r4)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            java.lang.String r6 = "\nstyle="
            java.lang.String r2 = c.b.a.a.p.c(r2, r6, r5)
            if (r2 == 0) goto L59
            c.c.a.b r5 = r7.f
            android.view.View r2 = r5.f(r0, r1, r2)
            goto L5f
        L59:
            c.c.a.b r2 = r7.f
            android.view.View r2 = r2.e(r0, r1)
        L5f:
            if (r2 != 0) goto L62
            return
        L62:
            if (r3 != 0) goto L67
            android.widget.LinearLayout r3 = r7.f1974c
            goto L6d
        L67:
            android.view.View r3 = r7.findViewById(r3)
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
        L6d:
            c.c.a.b r5 = r7.f
            android.view.ViewGroup$LayoutParams r5 = r5.d(r3, r2)
            r2.setLayoutParams(r5)
            c.c.a.b r5 = r7.f
            com.iapp.app.i r6 = new com.iapp.app.i
            r6.<init>(r2, r7)
            boolean r5 = r5.a(r6, r4)
            if (r5 != 0) goto L84
            return
        L84:
            r5 = 4
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r5[r6] = r1
            r1 = 1
            r5[r1] = r4
            r1 = 2
            r5[r1] = r8
            r1 = 3
            r4 = 0
            r5[r1] = r4
            r2.setTag(r5)
            r7.l(r2, r0, r8)
            r3.addView(r2)
        L9d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.run.main2.g(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    @SuppressLint({"NewApi"})
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 1101) {
            Uri data = (intent == null || i3 != -1) ? null : intent.getData();
            try {
                ValueCallback<Uri> valueCallback = com.iapp.app.x5.b.a;
                if (valueCallback != null) {
                    valueCallback.onReceiveValue(data);
                } else {
                    ValueCallback<Uri[]> valueCallback2 = com.iapp.app.x5.b.b;
                    if (valueCallback2 != null) {
                        valueCallback2.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(i3, intent));
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            com.iapp.app.x5.b.a = null;
            com.iapp.app.x5.b.b = null;
            return;
        }
        if (i2 == 1103) {
            com.iapp.app.p pVar = com.iapp.app.p.n;
            if (pVar != null) {
                pVar.m(i2, i3, intent);
                return;
            }
            return;
        }
        if (this.y) {
            StringBuilder sb = new StringBuilder();
            sb.append(i2);
            sb.append(", ");
            sb.append(i3);
            sb.append(", '");
            sb.append(set("^onactivityresult" + this.TaskId, intent));
            sb.append("'");
            callMethod("onactivityresult", sb.toString());
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        o("onCreateContextMenu" + menuItem.getGroupId() + "x" + menuItem.getItemId());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iapp.app.run.iActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        String[] strArr = this.e;
        if (strArr != null) {
            int length = strArr.length;
            for (int i2 = 1; i2 < length; i2++) {
                menu.add(0, i2, 0, this.e[i2]);
            }
        }
        if (this.o) {
            o("onCreateOptionsMenuloading");
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.s) {
            o("destroy");
        }
        SensorManager sensorManager = this.j;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this.f1976i, this.k);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (!this.p) {
            if (i2 != 4) {
                return false;
            }
            finish();
            return true;
        }
        callMethod("downkey", i2 + ", " + keyEvent.getAction() + ", " + keyEvent.getRepeatCount());
        return false;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (!this.q) {
            return false;
        }
        callMethod("upkey", i2 + ", " + keyEvent.getAction() + ", " + keyEvent.getRepeatCount());
        return false;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        o("CreateOptionsMenu" + menuItem.getItemId());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.x) {
            o("pause");
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        c.b.a.a.t.k = this.f1975d;
        if (this.u) {
            o("restart");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.w) {
            o("resume");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (this.v) {
            o("start");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        if (this.t) {
            o("stop");
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z || this.H) {
            return;
        }
        this.H = true;
        if (this.n) {
            o("loadingComplete");
        }
    }
}
