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
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import bsh.EvalError;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.iapp.app.Aid_javaCode;
import com.iapp.app.x5.WebView;
import com.oldpods.app.R;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import java.lang.reflect.Method;
import java.util.HashMap;

@SuppressLint({"UseSparseArrays"})
/* loaded from: classes.dex */
public class main3 extends iActivity {

    /* renamed from: c, reason: collision with root package name */
    private LinearLayout f1977c;
    public com.iapp.app.j javaj;
    private Aid_javaCode k;

    /* renamed from: d, reason: collision with root package name */
    private HashMap<String, Object> f1978d = new HashMap<>();
    private String[] e = null;
    private c.c.a.b f = new c.c.a.b(this);
    private String g = null;
    public String r = null;
    private SensorEventListener h = null;

    /* renamed from: i, reason: collision with root package name */
    private SensorManager f1979i = null;
    private Sensor j = null;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements AbsListView.OnScrollListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ boolean b;

        a(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            if (this.b) {
                int id = absListView.getId();
                main3.this.javaj.b("onscroll" + id, Integer.valueOf(id), absListView, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (this.a) {
                int id = absListView.getId();
                main3.this.javaj.b("onscrollstatechanged" + id, Integer.valueOf(id), absListView, Integer.valueOf(i2));
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
            main3.this.javaj.b("clickitem" + id, Integer.valueOf(id), adapterView, Integer.valueOf(i2), Long.valueOf(j), view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements AdapterView.OnItemSelectedListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ boolean b;

        c(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            if (this.a) {
                int id = adapterView.getId();
                main3.this.javaj.b("onitemselected" + id, Integer.valueOf(id), adapterView, view, Integer.valueOf(i2), Long.valueOf(j));
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            if (this.b) {
                int id = adapterView.getId();
                main3.this.javaj.b("onnothingselected" + id, Integer.valueOf(id), adapterView);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements SeekBar.OnSeekBarChangeListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ boolean b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1982c;

        d(boolean z, boolean z2, boolean z3) {
            this.a = z;
            this.b = z2;
            this.f1982c = z3;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            if (this.f1982c) {
                int id = seekBar.getId();
                main3.this.javaj.b("onprogresschanged2" + id, Integer.valueOf(id), seekBar, Integer.valueOf(i2), Boolean.valueOf(z));
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (this.b) {
                int id = seekBar.getId();
                main3.this.javaj.b("onstarttrackingtouch" + id, Integer.valueOf(id), seekBar);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (this.a) {
                int id = seekBar.getId();
                main3.this.javaj.b("onstoptrackingtouch" + id, Integer.valueOf(id), seekBar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements TabLayout.OnTabSelectedListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1984c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f1985d;

        e(boolean z, View view, boolean z2, boolean z3) {
            this.a = z;
            this.b = view;
            this.f1984c = z2;
            this.f1985d = z3;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
            if (this.f1985d) {
                int id = this.b.getId();
                main3.this.javaj.b("ontabreselected" + id, Integer.valueOf(id), (com.iapp.app.TabLayout) this.b, Integer.valueOf(tab.getPosition()), String.valueOf(tab.getText()), tab);
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            if (this.a) {
                int id = this.b.getId();
                main3.this.javaj.b("ontabselected" + id, Integer.valueOf(id), (com.iapp.app.TabLayout) this.b, Integer.valueOf(tab.getPosition()), String.valueOf(tab.getText()), tab);
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            if (this.f1984c) {
                int id = this.b.getId();
                main3.this.javaj.b("ontabunselected" + id, Integer.valueOf(id), (com.iapp.app.TabLayout) this.b, Integer.valueOf(tab.getPosition()), String.valueOf(tab.getText()), tab);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f extends RecyclerView.OnScrollListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ boolean b;

        f(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (this.a) {
                int id = recyclerView.getId();
                main3.this.javaj.b("onscrollstatechanged" + id, Integer.valueOf(id), recyclerView, Integer.valueOf(i2));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            if (this.b) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int childCount = layoutManager.getChildCount();
                int itemCount = layoutManager.getItemCount();
                int i4 = -1;
                if (layoutManager instanceof LinearLayoutManager) {
                    i4 = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                    if (staggeredGridLayoutManager.getSpanCount() > 0) {
                        i4 = staggeredGridLayoutManager.findFirstVisibleItemPositions(null)[0];
                    }
                }
                int id = recyclerView.getId();
                boolean z = i3 > 0 && childCount + i4 >= itemCount;
                main3.this.javaj.b("onscrolled" + id, Integer.valueOf(id), recyclerView, Integer.valueOf(i4), Integer.valueOf(childCount), Integer.valueOf(itemCount), Integer.valueOf(i2), Integer.valueOf(i3), Boolean.valueOf(z));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        g(boolean z, View view) {
            this.a = z;
            this.b = view;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            RecyclerView recyclerView;
            View findChildViewUnder;
            if (!this.a || (findChildViewUnder = (recyclerView = (RecyclerView) this.b).findChildViewUnder(motionEvent.getX(), motionEvent.getY())) == null) {
                return;
            }
            int id = recyclerView.getId();
            main3.this.javaj.b("clickitem" + id, Integer.valueOf(id), recyclerView, Integer.valueOf(recyclerView.getChildLayoutPosition(findChildViewUnder)), findChildViewUnder);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            RecyclerView recyclerView;
            View findChildViewUnder;
            if (!this.a || (findChildViewUnder = (recyclerView = (RecyclerView) this.b).findChildViewUnder(motionEvent.getX(), motionEvent.getY())) == null) {
                return false;
            }
            int id = recyclerView.getId();
            main3.this.javaj.b("clickitem" + id, Integer.valueOf(id), recyclerView, Integer.valueOf(recyclerView.getChildLayoutPosition(findChildViewUnder)), findChildViewUnder);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h implements RecyclerView.OnItemTouchListener {
        final /* synthetic */ GestureDetector a;

        h(main3 main3Var, GestureDetector gestureDetector) {
            this.a = gestureDetector;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            this.a.onTouchEvent(motionEvent);
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class i implements ViewPager.OnPageChangeListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1988c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f1989d;

        i(boolean z, View view, boolean z2, boolean z3) {
            this.a = z;
            this.b = view;
            this.f1988c = z2;
            this.f1989d = z3;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (this.f1989d) {
                int id = this.b.getId();
                main3.this.javaj.b("onpagescrollstatechanged" + id, Integer.valueOf(id), (VerticalViewPager) this.b, Integer.valueOf(i2));
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            if (this.f1988c) {
                int id = this.b.getId();
                main3.this.javaj.b("onpagescrolled" + id, Integer.valueOf(id), (VerticalViewPager) this.b, Integer.valueOf(i2), Float.valueOf(f), Integer.valueOf(i3));
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (this.a) {
                int id = this.b.getId();
                main3.this.javaj.b("onpageselected" + id, Integer.valueOf(id), (VerticalViewPager) this.b, Integer.valueOf(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class j implements SwipeRefreshLayout.OnRefreshListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        j(boolean z, View view) {
            this.a = z;
            this.b = view;
        }

        @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
        public void onRefresh() {
            if (this.a) {
                int id = this.b.getId();
                main3.this.javaj.b("onrefresh" + id, Integer.valueOf(id), (SwipeRefreshLayout) this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class k implements com.iapp.app.x5.a {
        k(main3 main3Var) {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            String lowerCase = str4.toLowerCase();
            String c2 = str3 != null ? c.b.a.a.p.c(str3, "filename=\"", "\"") : null;
            if (c2 == null) {
                if (str.contains("?")) {
                    str = str.substring(0, str.indexOf(63));
                }
                c2 = c.b.a.a.p.b(str);
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class l implements CompoundButton.OnCheckedChangeListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        l(boolean z, View view) {
            this.a = z;
            this.b = view;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (this.a) {
                int id = this.b.getId();
                main3.this.javaj.b("oncheckedchanged" + id, Integer.valueOf(id), compoundButton, Boolean.valueOf(z));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class m implements AppBarLayout.OnOffsetChangedListener {
        final /* synthetic */ boolean a;

        m(boolean z) {
            this.a = z;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        public void onOffsetChanged(AppBarLayout appBarLayout, int i2) {
            if (this.a) {
                int id = appBarLayout.getId();
                main3.this.javaj.b("onoffsetchanged" + id, Integer.valueOf(id), appBarLayout, Integer.valueOf(i2));
            }
        }
    }

    /* loaded from: classes.dex */
    class n implements SensorEventListener {
        n() {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            main3.this.javaj.b("sensor", Float.valueOf(sensorEvent.values[0]), Float.valueOf(sensorEvent.values[1]), Float.valueOf(sensorEvent.values[2]));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class o implements View.OnClickListener {
        o() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            main3.this.javaj.b("clicki" + id, Integer.valueOf(id), view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class p implements View.OnTouchListener {
        p() {
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int id = view.getId();
            return main3.this.javaj.c("touchmonitor" + id, Integer.valueOf(id), view, Integer.valueOf(motionEvent.getAction()), Float.valueOf(motionEvent.getX()), Float.valueOf(motionEvent.getY()), Float.valueOf(motionEvent.getRawX()), Float.valueOf(motionEvent.getRawY()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class q implements View.OnLongClickListener {
        q() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            int id = view.getId();
            return main3.this.javaj.c("press" + id, Integer.valueOf(id), view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class r implements View.OnKeyListener {
        r() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            int id = view.getId();
            return main3.this.javaj.c("keyboard" + id, Integer.valueOf(id), view, Integer.valueOf(i2), Integer.valueOf(keyEvent.getAction()), Integer.valueOf(keyEvent.getRepeatCount()), keyEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class s implements View.OnCreateContextMenuListener {
        s() {
        }

        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            String c2 = c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"pressmenu\">", "</eventItme>");
            contextMenu.setHeaderTitle(c.b.a.a.p.c(c2, "title:", "\n"));
            int id = view.getId();
            String[] split = c2.split("\ncase ");
            for (int i2 = 1; i2 < split.length; i2++) {
                contextMenu.add(id, i2, 0, c.b.a.a.p.c(split[i2], null, ":"));
            }
            if (c.b.a.a.p.c(c2, "\ndefault:", "\nbreak") != null) {
                main3.this.javaj.b("onCreateContextMenu" + id + "x0", Integer.valueOf(id), view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class t implements TextView.OnEditorActionListener {
        t() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            int id = textView.getId();
            com.iapp.app.j jVar = main3.this.javaj;
            if (keyEvent != null) {
                return jVar.c("editormonitor" + id, Integer.valueOf(id), textView, Integer.valueOf(i2), Integer.valueOf(keyEvent.getAction()), Integer.valueOf(keyEvent.getRepeatCount()), Integer.valueOf(keyEvent.getKeyCode()), keyEvent);
            }
            return jVar.c("editormonitor" + id, Integer.valueOf(id), textView, Integer.valueOf(i2), null, null, null, keyEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class u implements View.OnFocusChangeListener {
        u() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            int id = view.getId();
            main3.this.javaj.b("focuschange" + id, Integer.valueOf(id), view, Boolean.valueOf(z));
        }
    }

    private void e(View view, int i2, String str) {
        if (addViewEventItme(str, i2, "clicki", "int st_vId,android.view.View st_vW")) {
            view.setOnClickListener(new o());
        }
        if (addViewEventItme(str, i2, "touchmonitor", "int st_vId,android.view.View st_vW,int st_eA,float st_eX,float st_eY,float st_rX,float st_rY")) {
            view.setOnTouchListener(new p());
        }
        if (addViewEventItme(str, i2, "press", "int st_vId,android.view.View st_vW")) {
            view.setOnLongClickListener(new q());
        }
        if (addViewEventItme(str, i2, "keyboard", "int st_vId,android.view.View st_vW,int st_kC,int st_eA,int st_eR,android.view.KeyEvent st_eT")) {
            view.setOnKeyListener(new r());
        }
        if (str.contains("<eventItme type=\"pressmenu\">")) {
            String c2 = c.b.a.a.p.c(str, "<eventItme type=\"pressmenu\">", "</eventItme>");
            String[] split = c2.split("\ncase ");
            for (int i3 = 1; i3 < split.length; i3++) {
                this.l += "private void onCreateContextMenu" + i2 + "x" + i3 + "(){\n" + c.b.a.a.p.c(split[i3], ":", "\nbreak") + "\n}\n";
            }
            String c3 = c.b.a.a.p.c(c2, "\ndefault:", "\nbreak");
            if (c3 != null) {
                this.l += "private void onCreateContextMenu" + i2 + "x0(int st_vId, android.view.View st_vW){\n" + c3 + "\n}\n";
            }
            view.setOnCreateContextMenuListener(new s());
        }
        if (view instanceof TextView) {
            if (addViewEventItme(str, i2, "editormonitor", "int st_vId,android.widget.TextView st_vW,st_aI,st_eA,st_eR,st_eK,st_eT")) {
                ((TextView) view).setOnEditorActionListener(new t());
            }
            boolean addViewEventItme = addViewEventItme(str, i2, "ontextchanged", "int st_vId,android.widget.TextView st_vW,String st_sS,st_sT,int st_bE,int st_cT");
            boolean addViewEventItme2 = addViewEventItme(str, i2, "beforetextchanged", "int st_vId,android.view.TextView st_vW,String st_sS,int st_sT,int st_cT,int st_aR");
            boolean addViewEventItme3 = addViewEventItme(str, i2, "aftertextchanged", "int st_vId,android.view.TextView st_vW,String st_sS");
            if (addViewEventItme || addViewEventItme2 || addViewEventItme3) {
                new com.iapp.app.t((TextView) view, this.javaj, addViewEventItme, addViewEventItme2, addViewEventItme3);
            }
        }
        if (view instanceof WebView) {
            l((WebView) view, str);
        }
        if (addViewEventItme(str, i2, "focuschange", "int st_vId,android.view.View st_vW,boolean st_hF")) {
            view.setOnFocusChangeListener(new u());
        }
        if (view instanceof AbsListView) {
            boolean addViewEventItme4 = addViewEventItme(str, i2, "onscrollstatechanged", "int st_vId,android.widget.AbsListView st_vW,int st_sE");
            boolean addViewEventItme5 = addViewEventItme(str, i2, "onscroll", "int st_vId,android.widget.AbsListView st_vW,int st_fM,int st_vT,int st_bT");
            if (addViewEventItme4 || addViewEventItme5) {
                ((AbsListView) view).setOnScrollListener(new a(addViewEventItme4, addViewEventItme5));
            }
        }
        if (view instanceof AdapterView) {
            if (addViewEventItme(str, i2, "clickitem", "int st_vId,android.widget.AdapterView st_vW,int st_pN,long st_iD,android.view.View st_vW2")) {
                ((AdapterView) view).setOnItemClickListener(new b());
            }
            boolean addViewEventItme6 = addViewEventItme(str, i2, "onitemselected", "int st_vId,android.widget.AdapterView st_vW,android.view.View st_vW2,int st_pN,long st_iD");
            boolean addViewEventItme7 = addViewEventItme(str, i2, "onnothingselected", "int st_vId,android.widget.AdapterView st_vW");
            if (addViewEventItme6 || addViewEventItme7) {
                ((AdapterView) view).setOnItemSelectedListener(new c(addViewEventItme6, addViewEventItme7));
            }
        }
        if (view instanceof ViewPager) {
            boolean addViewEventItme8 = addViewEventItme(str, i2, "onpageselected", "int st_vId,android.support.v4.view.ViewPager st_vW,int st_pN");
            boolean addViewEventItme9 = addViewEventItme(str, i2, "onpagescrolled", "int st_vId,android.support.v4.view.ViewPager st_vW,int st_pN,float st_pT,int st_pS");
            boolean addViewEventItme10 = addViewEventItme(str, i2, "onpagescrollstatechanged", "int st_vId,android.support.v4.view.ViewPager st_vW,int st_sE");
            if (addViewEventItme8 || addViewEventItme9 || addViewEventItme10) {
                new com.iapp.app.q((ViewPager) view, this.javaj, addViewEventItme8, addViewEventItme9, addViewEventItme10);
            }
        }
        if (view instanceof DrawerLayout) {
            boolean addViewEventItme11 = addViewEventItme(str, i2, "ondrawerclosed", "int st_vId,android.support.v4.widget.DrawerLayout st_vW,android.view.View st_dW");
            boolean addViewEventItme12 = addViewEventItme(str, i2, "ondraweropened", "int st_vId,android.support.v4.widget.DrawerLayout st_vW,android.view.View st_dW");
            boolean addViewEventItme13 = addViewEventItme(str, i2, "onoptionsitemselected", "int st_vId,android.support.v4.widget.DrawerLayout st_vW,android.view.MenuItem st_iM");
            if (addViewEventItme11 || addViewEventItme12 || addViewEventItme13) {
                new com.iapp.app.o((DrawerLayout) view, this.javaj, addViewEventItme11, addViewEventItme12, addViewEventItme13);
            }
        }
        if ((view instanceof SeekBar) && (str.contains("<eventItme type=\"onstarttrackingtouch\">") || str.contains("<eventItme type=\"onstoptrackingtouch\">") || str.contains("<eventItme type=\"onprogresschanged2\">"))) {
            boolean addViewEventItme14 = addViewEventItme(str, i2, "onstarttrackingtouch", "int st_vId,android.widget.SeekBar st_vW");
            boolean addViewEventItme15 = addViewEventItme(str, i2, "onstoptrackingtouch", "int st_vId,android.widget.SeekBar st_vW");
            boolean addViewEventItme16 = addViewEventItme(str, i2, "onprogresschanged2", "int st_vId,android.widget.SeekBar st_vW,int st_nS,boolean st_fR");
            if (addViewEventItme14 || addViewEventItme15 || addViewEventItme16) {
                ((SeekBar) view).setOnSeekBarChangeListener(new d(addViewEventItme15, addViewEventItme14, addViewEventItme16));
            }
        }
        f(view, str);
    }

    private void f(View view, String str) {
        if ((view instanceof com.iapp.app.TabLayout) && (str.contains("<eventItme type=\"ontabselected\">") || str.contains("<eventItme type=\"ontabunselected\">") || str.contains("<eventItme type=\"ontabreselected\">"))) {
            ((com.iapp.app.TabLayout) view).addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new e(addViewEventItme(str, view.getId(), "ontabselected", "int st_vId,com.iapp.app.TabLayout st_vW,int st_pN,String st_tT,com.iapp.app.TabLayout.Tab st_taB"), view, addViewEventItme(str, view.getId(), "ontabunselected", "int st_vId,com.iapp.app.TabLayout st_vW,int st_pN,String st_tT,com.iapp.app.TabLayout.Tab st_taB"), addViewEventItme(str, view.getId(), "ontabreselected", "int st_vId,com.iapp.app.TabLayout st_vW,int st_pN,String st_tT,com.iapp.app.TabLayout.Tab st_taB")));
        }
        if (view instanceof RecyclerView) {
            if (str.contains("<eventItme type=\"onscrollstatechanged\">") || str.contains("<eventItme type=\"onscrolled\">")) {
                ((RecyclerView) view).addOnScrollListener(new f(addViewEventItme(str, view.getId(), "onscrollstatechanged", "int st_vId,androidx.recyclerview.widget.RecyclerView st_vW,int st_sE"), addViewEventItme(str, view.getId(), "onscrolled", "int st_vId,androidx.recyclerview.widget.RecyclerView st_vW,int st_fM,int st_vT,int st_bT,int st_dX,int st_dY,boolean st_isB")));
            }
            if (str.contains("<eventItme type=\"clickitem\">")) {
                ((RecyclerView) view).addOnItemTouchListener(new h(this, new GestureDetector(this, new g(addViewEventItme(str, view.getId(), "clickitem", "int st_vId,androidx.recyclerview.widget.RecyclerView st_vW,int st_pN,android.view.View st_vW2"), view))));
            }
        }
        if ((view instanceof VerticalViewPager) && (str.contains("<eventItme type=\"onpageselected\">") || str.contains("<eventItme type=\"onpagescrolled\">") || str.contains("<eventItme type=\"onpagescrollstatechanged\">"))) {
            ((VerticalViewPager) view).setOnPageChangeListener(new i(addViewEventItme(str, view.getId(), "onpageselected", "int st_vId,fr.castorflex.android.verticalviewpager.VerticalViewPager st_vW,int st_pN"), view, addViewEventItme(str, view.getId(), "onpagescrolled", "int st_vId,fr.castorflex.android.verticalviewpager.VerticalViewPager st_vW,int st_pN,float st_pT,int st_pS"), addViewEventItme(str, view.getId(), "onpagescrollstatechanged", "int st_vId,fr.castorflex.android.verticalviewpager.VerticalViewPager st_vW,int st_sE")));
        }
        if ((view instanceof SwipeRefreshLayout) && str.contains("<eventItme type=\"onrefresh\">")) {
            ((SwipeRefreshLayout) view).setOnRefreshListener(new j(addViewEventItme(str, view.getId(), "onrefresh", "int st_vId,SwipeRefreshLayout st_vW"), view));
        }
        if ((view instanceof CompoundButton) && str.contains("<eventItme type=\"oncheckedchanged\">")) {
            ((CompoundButton) view).setOnCheckedChangeListener(new l(addViewEventItme(str, view.getId(), "oncheckedchanged", "int st_vId,android.widget.CompoundButton st_vW,boolean st_iC"), view));
        }
        if ((view instanceof AppBarLayout) && str.contains("<eventItme type=\"onoffsetchanged\">")) {
            ((AppBarLayout) view).addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) new m(addViewEventItme(str, view.getId(), "onoffsetchanged", "st_vId,st_vW,st_vO")));
        }
    }

    private boolean g(String str, String str2) {
        if (!str.contains("<eventItme type=\"" + str2 + "\">")) {
            return false;
        }
        String c2 = c.b.a.a.p.c(str, "<eventItme type=\"" + str2 + "\">", "</eventItme>");
        if (c2 == null) {
            return false;
        }
        this.l += "private void " + str2 + "(){\n" + c2 + "\n}\n";
        return true;
    }

    private boolean h(String str, String str2, String str3) {
        if (!str.contains("<eventItme type=\"" + str2 + "\">")) {
            return false;
        }
        String c2 = c.b.a.a.p.c(str, "<eventItme type=\"" + str2 + "\">", "</eventItme>");
        if (c2 == null) {
            return false;
        }
        this.l += "private void " + str2 + "(" + str3 + "){\n" + c2 + "\n}\n";
        return true;
    }

    private void i(String str) {
        if (this.m) {
            str = str + "\nloading();\n";
        }
        this.javaj.d(str);
    }

    private String j(String str, String str2) {
        if (!str.contains("<eventItme type=\"" + str2 + "\">")) {
            return null;
        }
        String c2 = c.b.a.a.p.c(str, "<eventItme type=\"" + str2 + "\">", "</eventItme>");
        if (c2 != null) {
            return c2;
        }
        return null;
    }

    @TargetApi(11)
    private void k(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    @TargetApi(16)
    private void l(WebView webView, String str) {
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
        if (addViewEventItme(str, webView.getId(), "ondownloadstart", "int st_vId,android.webkit.WebView st_vW,String st_url,String st_uT,String st_cN,String st_mE,long st_cH")) {
            new com.iapp.app.n(webView, this.javaj);
        } else {
            webView.setDownloadListener(new k(this));
        }
        new com.iapp.app.x5.b().j(webView, str, this);
        k(webView);
    }

    private void m() {
        String c2;
        String str = this.r;
        if (str != null) {
            String j2 = j(str, "loading");
            if (j2 != null) {
                this.m = true;
                this.l += "private void loading(){\n" + j2 + "\n}\n";
            }
            this.n = g(this.r, "loadingComplete");
            this.p = h(this.r, "downkey", "int st_kC,int st_eA,int st_eR,android.view.KeyEvent st_eT");
            this.q = h(this.r, "upkey", "int st_kC,int st_eA,int st_eR,android.view.KeyEvent st_eT");
            this.s = g(this.r, "destroy");
            this.t = g(this.r, "stop");
            this.u = g(this.r, "restart");
            this.v = g(this.r, "start");
            this.w = g(this.r, "resume");
            this.x = g(this.r, "pause");
            this.y = h(this.r, "onactivityresult", "int st_sC,int st_lC,android.content.Intent st_iT");
            this.z = h(this.r, "onrequestpermissionsresult", "int st_sC,String[] st_pS,int[] st_gR");
            this.A = h(this.r, "sensor", "float st_x,float st_y,float st_z");
            if (!this.r.contains("<eventItme type=\"menu\">") || (c2 = c.b.a.a.p.c(this.r, "<eventItme type=\"menu\">", "</eventItme>")) == null) {
                return;
            }
            String[] split = ("m\n" + c2).split("\ncase ", -1);
            this.e = new String[split.length];
            for (int i2 = 1; i2 < split.length; i2++) {
                this.e[i2] = c.b.a.a.p.c(split[i2], null, ":");
                this.l += "private void CreateOptionsMenu" + i2 + "(){\n" + c.b.a.a.p.c(split[i2], ":", "\nbreak") + "\n}\n";
            }
            String c3 = c.b.a.a.p.c(c2, "\ndefault:", "\nbreak");
            if (c3 != null) {
                this.o = true;
                this.l += "private void onCreateOptionsMenuloading(){\n" + c3 + "\n}\n";
            }
        }
    }

    public boolean addViewEventItme(String str, int i2, String str2, String str3) {
        if (!str.contains("<eventItme type=\"" + str2 + "\">")) {
            return false;
        }
        String c2 = c.b.a.a.p.c(str, "<eventItme type=\"" + str2 + "\">", "</eventItme>");
        if (c2 == null) {
            return false;
        }
        this.l += "private void " + str2 + i2 + "(" + str3 + "){\n" + c2 + "\n}\n";
        return true;
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
        c.b.a.a.t.k = this.f1978d;
        setContentView(R.layout.ui_run_mian);
        this.g = getIntent().getExtras().getString("OpenFilexmlui");
        this.f1977c = (LinearLayout) findViewById(R.id.ui_run_mian_mian);
        com.iapp.app.b.h3(this, this.g.toLowerCase());
    }

    public void g() {
        this.javaj = new com.iapp.app.j(this);
        this.k = new Aid_javaCode(this, this.javaj);
        try {
            this.javaj.set("activity", this);
            this.javaj.set("i", this.k);
        } catch (EvalError e2) {
            e2.printStackTrace();
        }
        m();
        if (this.A) {
            SensorManager sensorManager = (SensorManager) getSystemService("sensor");
            this.f1979i = sensorManager;
            Sensor defaultSensor = sensorManager.getDefaultSensor(1);
            this.j = defaultSensor;
            n nVar = new n();
            this.h = nVar;
            this.f1979i.registerListener(nVar, defaultSensor, 2);
        }
        i(this.l);
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
            android.widget.LinearLayout r3 = r7.f1977c
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
            r7.e(r2, r0, r8)
            r3.addView(r2)
        L9d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.run.main3.g(java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    @SuppressLint({"NewApi"})
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 != 1101) {
            if (i2 != 1103) {
                if (this.y) {
                    this.javaj.b("onactivityresult", Integer.valueOf(i2), Integer.valueOf(i3), intent);
                    return;
                }
                return;
            } else {
                com.iapp.app.p pVar = com.iapp.app.p.n;
                if (pVar != null) {
                    pVar.m(i2, i3, intent);
                    return;
                }
                return;
            }
        }
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
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        this.javaj.a("onCreateContextMenu" + menuItem.getGroupId() + "x" + menuItem.getItemId());
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
                if (this.e[i2].contains("|")) {
                    boolean c0 = com.iapp.app.i.c0(menu, i2, this.e[i2], this);
                    if (!this.B && c0) {
                        this.B = true;
                    }
                } else {
                    menu.add(0, i2, 0, this.e[i2]);
                }
            }
        }
        if (this.o) {
            this.javaj.a("onCreateOptionsMenuloading");
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.s) {
            this.javaj.a("destroy");
        }
        SensorManager sensorManager = this.f1979i;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this.h, this.j);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (this.p) {
            return this.javaj.c("downkey", Integer.valueOf(i2), Integer.valueOf(keyEvent.getAction()), Integer.valueOf(keyEvent.getRepeatCount()), keyEvent);
        }
        if (i2 != 4) {
            return false;
        }
        finish();
        return true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (this.q) {
            return this.javaj.c("upkey", Integer.valueOf(i2), Integer.valueOf(keyEvent.getAction()), Integer.valueOf(keyEvent.getRepeatCount()), keyEvent);
        }
        return false;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i2, Menu menu) {
        if (this.B && menu != null && menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
            try {
                Method declaredMethod = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(menu, Boolean.TRUE);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return super.onMenuOpened(i2, menu);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        this.javaj.a("CreateOptionsMenu" + menuItem.getItemId());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.x) {
            this.javaj.a("pause");
        }
    }

    @Override // com.iapp.app.run.iActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (this.z) {
            this.javaj.b("onrequestpermissionsresult", Integer.valueOf(i2), strArr, iArr);
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        c.b.a.a.t.k = this.f1978d;
        if (this.u) {
            this.javaj.a("restart");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.w) {
            this.javaj.a("resume");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (this.v) {
            this.javaj.a("start");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        if (this.t) {
            this.javaj.a("stop");
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z || this.C) {
            return;
        }
        this.C = true;
        if (this.n) {
            this.javaj.a("loadingComplete");
        }
    }
}
