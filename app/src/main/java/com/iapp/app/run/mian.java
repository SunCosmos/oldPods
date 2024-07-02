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
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.iapp.app.Aid_YuCodeX;
import com.iapp.app.x5.WebView;
import com.oldpods.app.R;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import java.lang.reflect.Method;
import java.util.HashMap;

@SuppressLint({"UseSparseArrays"})
/* loaded from: classes.dex */
public class mian extends iActivity {
    public static String packageName = null;
    public static boolean sh = true;
    private static Class[] v;
    private static HashMap<String, Class<?>> w = new HashMap<>();

    /* renamed from: c */
    private LinearLayout f1992c;
    private c.b.a.a.t g;
    private c.b.a.a.t h;
    private Aid_YuCodeX j;
    private Aid_YuCodeX k;

    /* renamed from: d */
    private HashMap<String, Object> f1993d = new HashMap<>();
    private HashMap<Integer, String> e = new HashMap<>();
    private HashMap<Integer, String> f = new HashMap<>();

    /* renamed from: i */
    private c.b.a.a.t f1994i = null;
    private Aid_YuCodeX l = null;
    private c.c.a.b m = new c.c.a.b(this);
    private String n = null;
    private String o = null;
    public String r = null;
    private SensorEventListener p = null;
    private SensorManager q = null;
    private Sensor s = null;
    private boolean t = false;
    private boolean u = false;

    /* loaded from: classes.dex */
    public class a implements TextView.OnEditorActionListener {
        a() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            if (mian.sh) {
                c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                tVar.T("st_vId", Integer.valueOf(textView.getId()));
                tVar.T("st_vW", textView);
                tVar.T("st_aI", Integer.valueOf(i2));
                if (keyEvent != null) {
                    tVar.T("st_eA", Integer.valueOf(keyEvent.getAction()));
                    tVar.T("st_eR", Integer.valueOf(keyEvent.getRepeatCount()));
                    tVar.T("st_eK", Integer.valueOf(keyEvent.getKeyCode()));
                } else {
                    tVar.T("st_eA", null);
                    tVar.T("st_eR", null);
                    tVar.T("st_eK", null);
                }
                return mian.this.s(tVar, "editormonitor", ((Object[]) textView.getTag())[2].toString());
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(textView.getId()));
            aid_YuCodeX.dim("st_vW", textView);
            aid_YuCodeX.dim("st_aI", Integer.valueOf(i2));
            if (keyEvent != null) {
                aid_YuCodeX.dim("st_eA", Integer.valueOf(keyEvent.getAction()));
                aid_YuCodeX.dim("st_eR", Integer.valueOf(keyEvent.getRepeatCount()));
                aid_YuCodeX.dim("st_eK", Integer.valueOf(keyEvent.getKeyCode()));
            } else {
                aid_YuCodeX.dim("st_eA", null);
                aid_YuCodeX.dim("st_eR", null);
                aid_YuCodeX.dim("st_eK", null);
            }
            return mian.this.t(aid_YuCodeX, "editormonitor", ((Object[]) textView.getTag())[2].toString());
        }
    }

    /* loaded from: classes.dex */
    public class b implements View.OnFocusChangeListener {
        b() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (mian.sh) {
                c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                tVar.T("st_vId", Integer.valueOf(view.getId()));
                tVar.T("st_vW", view);
                tVar.T("st_hF", Boolean.valueOf(z));
                tVar.f(c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"focuschange\">", "</eventItme>"));
                return;
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(view.getId()));
            aid_YuCodeX.dim("st_vW", view);
            aid_YuCodeX.dim("st_hF", Boolean.valueOf(z));
            mian.this.goUIEventset("focuschange" + c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"focuschange\">", "</eventItme>"), aid_YuCodeX);
        }
    }

    /* loaded from: classes.dex */
    public class c implements AbsListView.OnScrollListener {
        c() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            String c2 = c.b.a.a.p.c(((Object[]) absListView.getTag())[2].toString(), "<eventItme type=\"onscroll\">", "</eventItme>");
            if (c2 != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(absListView.getId()));
                    tVar.T("st_vW", absListView);
                    tVar.T("st_fM", Integer.valueOf(i2));
                    tVar.T("st_vT", Integer.valueOf(i3));
                    tVar.T("st_bT", Integer.valueOf(i4));
                    tVar.f(c2);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(absListView.getId()));
                aid_YuCodeX.dim("st_vW", absListView);
                aid_YuCodeX.dim("st_fM", Integer.valueOf(i2));
                aid_YuCodeX.dim("st_vT", Integer.valueOf(i3));
                aid_YuCodeX.dim("st_bT", Integer.valueOf(i4));
                mian.this.goUIEventset("onscroll" + c2, aid_YuCodeX);
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            String c2 = c.b.a.a.p.c(((Object[]) absListView.getTag())[2].toString(), "<eventItme type=\"onscrollstatechanged\">", "</eventItme>");
            if (c2 != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(absListView.getId()));
                    tVar.T("st_vW", absListView);
                    tVar.T("st_sE", Integer.valueOf(i2));
                    tVar.f(c2);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(absListView.getId()));
                aid_YuCodeX.dim("st_vW", absListView);
                aid_YuCodeX.dim("st_sE", Integer.valueOf(i2));
                mian.this.goUIEventset("onscrollstatechanged" + c2, aid_YuCodeX);
            }
        }
    }

    /* loaded from: classes.dex */
    public class d implements AdapterView.OnItemClickListener {
        d() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            if (mian.sh) {
                c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                tVar.T("st_vId", Integer.valueOf(adapterView.getId()));
                tVar.T("st_vW", adapterView);
                tVar.T("st_pN", Integer.valueOf(i2));
                tVar.T("st_iD", Long.valueOf(j));
                tVar.f(c.b.a.a.p.c(((Object[]) adapterView.getTag())[2].toString(), "<eventItme type=\"clickitem\">", "</eventItme>"));
                return;
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(adapterView.getId()));
            aid_YuCodeX.dim("st_vW", adapterView);
            aid_YuCodeX.dim("st_pN", Integer.valueOf(i2));
            aid_YuCodeX.dim("st_iD", Long.valueOf(j));
            mian.this.goUIEventset("clickitem" + c.b.a.a.p.c(((Object[]) adapterView.getTag())[2].toString(), "<eventItme type=\"clickitem\">", "</eventItme>"), aid_YuCodeX);
        }
    }

    /* loaded from: classes.dex */
    public class e implements AdapterView.OnItemSelectedListener {
        e() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            String c2 = c.b.a.a.p.c(((Object[]) adapterView.getTag())[2].toString(), "<eventItme type=\"onitemselected\">", "</eventItme>");
            if (c2 != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(adapterView.getId()));
                    tVar.T("st_vW", adapterView);
                    tVar.T("st_vW2", view);
                    tVar.T("st_pN", Integer.valueOf(i2));
                    tVar.T("st_iD", Long.valueOf(j));
                    tVar.f(c2);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(adapterView.getId()));
                aid_YuCodeX.dim("st_vW", adapterView);
                aid_YuCodeX.dim("st_vW2", view);
                aid_YuCodeX.dim("st_pN", Integer.valueOf(i2));
                aid_YuCodeX.dim("st_iD", Long.valueOf(j));
                mian.this.goUIEventset("onitemselected" + c2, aid_YuCodeX);
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            String c2 = c.b.a.a.p.c(((Object[]) adapterView.getTag())[2].toString(), "<eventItme type=\"onnothingselected\">", "</eventItme>");
            if (c2 != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(adapterView.getId()));
                    tVar.T("st_vW", adapterView);
                    tVar.f(c2);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(adapterView.getId()));
                aid_YuCodeX.dim("st_vW", adapterView);
                mian.this.goUIEventset("onnothingselected" + c2, aid_YuCodeX);
            }
        }
    }

    /* loaded from: classes.dex */
    public class f implements SeekBar.OnSeekBarChangeListener {
        f() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            String c2 = c.b.a.a.p.c(((Object[]) seekBar.getTag())[2].toString(), "<eventItme type=\"onprogresschanged2\">", "</eventItme>");
            if (c2 != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(seekBar.getId()));
                    tVar.T("st_vW", seekBar);
                    tVar.T("st_nS", Integer.valueOf(i2));
                    tVar.T("st_fR", Boolean.valueOf(z));
                    tVar.f(c2);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(seekBar.getId()));
                aid_YuCodeX.dim("st_vW", seekBar);
                aid_YuCodeX.dim("st_nS", Integer.valueOf(i2));
                aid_YuCodeX.dim("st_fR", Boolean.valueOf(z));
                mian.this.goUIEventset("onprogresschanged2" + c2, aid_YuCodeX);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            String c2 = c.b.a.a.p.c(((Object[]) seekBar.getTag())[2].toString(), "<eventItme type=\"onstarttrackingtouch\">", "</eventItme>");
            if (c2 != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(seekBar.getId()));
                    tVar.T("st_vW", seekBar);
                    tVar.f(c2);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(seekBar.getId()));
                aid_YuCodeX.dim("st_vW", seekBar);
                mian.this.goUIEventset("onstarttrackingtouch" + c2, aid_YuCodeX);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            String c2 = c.b.a.a.p.c(((Object[]) seekBar.getTag())[2].toString(), "<eventItme type=\"onstoptrackingtouch\">", "</eventItme>");
            if (c2 != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(seekBar.getId()));
                    tVar.T("st_vW", seekBar);
                    tVar.f(c2);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(seekBar.getId()));
                aid_YuCodeX.dim("st_vW", seekBar);
                mian.this.goUIEventset("onstoptrackingtouch" + c2, aid_YuCodeX);
            }
        }
    }

    /* loaded from: classes.dex */
    public class g implements TabLayout.OnTabSelectedListener {
        final /* synthetic */ String a;
        final /* synthetic */ View b;

        /* renamed from: c */
        final /* synthetic */ String f1995c;

        /* renamed from: d */
        final /* synthetic */ String f1996d;

        g(String str, View view, String str2, String str3) {
            this.a = str;
            this.b = view;
            this.f1995c = str2;
            this.f1996d = str3;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
            if (this.f1996d != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                    tVar.T("st_vW", this.b);
                    tVar.T("st_pN", Integer.valueOf(tab.getPosition()));
                    tVar.T("st_tT", String.valueOf(tab.getText()));
                    tVar.T("st_taB", tab);
                    tVar.f(this.f1996d);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX.dim("st_vW", this.b);
                aid_YuCodeX.dim("st_pN", Integer.valueOf(tab.getPosition()));
                aid_YuCodeX.dim("st_tT", String.valueOf(tab.getText()));
                aid_YuCodeX.dim("st_taB", tab);
                mian.this.goUIEventset("ontabreselected" + this.f1996d, aid_YuCodeX);
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            if (this.a != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                    tVar.T("st_vW", this.b);
                    tVar.T("st_pN", Integer.valueOf(tab.getPosition()));
                    tVar.T("st_tT", String.valueOf(tab.getText()));
                    tVar.T("st_taB", tab);
                    tVar.f(this.a);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX.dim("st_vW", this.b);
                aid_YuCodeX.dim("st_pN", Integer.valueOf(tab.getPosition()));
                aid_YuCodeX.dim("st_tT", String.valueOf(tab.getText()));
                aid_YuCodeX.dim("st_taB", tab);
                mian.this.goUIEventset("ontabselected" + this.a, aid_YuCodeX);
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            if (this.f1995c != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                    tVar.T("st_vW", this.b);
                    tVar.T("st_pN", Integer.valueOf(tab.getPosition()));
                    tVar.T("st_tT", String.valueOf(tab.getText()));
                    tVar.T("st_taB", tab);
                    tVar.f(this.f1995c);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX.dim("st_vW", this.b);
                aid_YuCodeX.dim("st_pN", Integer.valueOf(tab.getPosition()));
                aid_YuCodeX.dim("st_tT", String.valueOf(tab.getText()));
                aid_YuCodeX.dim("st_taB", tab);
                mian.this.goUIEventset("ontabunselected" + this.f1995c, aid_YuCodeX);
            }
        }
    }

    /* loaded from: classes.dex */
    public class h extends RecyclerView.OnScrollListener {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        h(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (this.a != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(recyclerView.getId()));
                    tVar.T("st_vW", recyclerView);
                    tVar.T("st_sE", Integer.valueOf(i2));
                    tVar.f(this.a);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(recyclerView.getId()));
                aid_YuCodeX.dim("st_vW", recyclerView);
                aid_YuCodeX.dim("st_sE", Integer.valueOf(i2));
                mian.this.goUIEventset("onscrollstatechanged" + this.a, aid_YuCodeX);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            if (this.b != null) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int childCount = layoutManager.getChildCount();
                int itemCount = layoutManager.getItemCount();
                int i4 = -1;
                boolean z = false;
                if (layoutManager instanceof LinearLayoutManager) {
                    i4 = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                    if (staggeredGridLayoutManager.getSpanCount() > 0) {
                        i4 = staggeredGridLayoutManager.findFirstVisibleItemPositions(null)[0];
                    }
                }
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(recyclerView.getId()));
                    tVar.T("st_vW", recyclerView);
                    tVar.T("st_fM", Integer.valueOf(i4));
                    tVar.T("st_vT", Integer.valueOf(childCount));
                    tVar.T("st_bT", Integer.valueOf(itemCount));
                    tVar.T("st_dX", Integer.valueOf(i2));
                    tVar.T("st_dY", Integer.valueOf(i3));
                    if (i3 > 0 && childCount + i4 >= itemCount) {
                        z = true;
                    }
                    tVar.T("st_isB", Boolean.valueOf(z));
                    tVar.f(this.b);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(recyclerView.getId()));
                aid_YuCodeX.dim("st_vW", recyclerView);
                aid_YuCodeX.dim("st_fM", Integer.valueOf(i4));
                aid_YuCodeX.dim("st_vT", Integer.valueOf(childCount));
                aid_YuCodeX.dim("st_bT", Integer.valueOf(itemCount));
                aid_YuCodeX.dim("st_dX", Integer.valueOf(i2));
                aid_YuCodeX.dim("st_dY", Integer.valueOf(i3));
                if (i3 > 0 && childCount + i4 >= itemCount) {
                    z = true;
                }
                aid_YuCodeX.dim("st_isB", Boolean.valueOf(z));
                mian.this.goUIEventset("onscrolled" + this.b, aid_YuCodeX);
            }
        }
    }

    /* loaded from: classes.dex */
    public class i extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ View a;
        final /* synthetic */ String b;

        i(View view, String str) {
            this.a = view;
            this.b = str;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            View findChildViewUnder = ((RecyclerView) this.a).findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (findChildViewUnder == null || this.b == null) {
                return;
            }
            if (mian.sh) {
                c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                tVar.T("st_vId", Integer.valueOf(this.a.getId()));
                tVar.T("st_vW", this.a);
                tVar.T("st_pN", Integer.valueOf(((RecyclerView) this.a).getChildLayoutPosition(findChildViewUnder)));
                tVar.T("st_vW2", findChildViewUnder);
                tVar.f(this.b);
                return;
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(this.a.getId()));
            aid_YuCodeX.dim("st_vW", this.a);
            aid_YuCodeX.dim("st_pN", Integer.valueOf(((RecyclerView) this.a).getChildLayoutPosition(findChildViewUnder)));
            aid_YuCodeX.dim("st_vW2", findChildViewUnder);
            mian.this.goUIEventset("clickitem" + this.b, aid_YuCodeX);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            View findChildViewUnder = ((RecyclerView) this.a).findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (findChildViewUnder == null) {
                return false;
            }
            if (this.b == null) {
                return true;
            }
            if (mian.sh) {
                c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                tVar.T("st_vId", Integer.valueOf(this.a.getId()));
                tVar.T("st_vW", this.a);
                tVar.T("st_pN", Integer.valueOf(((RecyclerView) this.a).getChildLayoutPosition(findChildViewUnder)));
                tVar.T("st_vW2", findChildViewUnder);
                tVar.f(this.b);
                return true;
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(this.a.getId()));
            aid_YuCodeX.dim("st_vW", this.a);
            aid_YuCodeX.dim("st_pN", Integer.valueOf(((RecyclerView) this.a).getChildLayoutPosition(findChildViewUnder)));
            aid_YuCodeX.dim("st_vW2", findChildViewUnder);
            mian.this.goUIEventset("clickitem" + this.b, aid_YuCodeX);
            return true;
        }
    }

    /* loaded from: classes.dex */
    public class j implements RecyclerView.OnItemTouchListener {
        final /* synthetic */ GestureDetector a;

        j(mian mianVar, GestureDetector gestureDetector) {
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

    /* loaded from: classes.dex */
    public class k implements com.iapp.app.x5.a {
        k(mian mianVar) {
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

    /* loaded from: classes.dex */
    public class l implements ViewPager.OnPageChangeListener {
        final /* synthetic */ String a;
        final /* synthetic */ View b;

        /* renamed from: c */
        final /* synthetic */ String f1999c;

        /* renamed from: d */
        final /* synthetic */ String f2000d;

        l(String str, View view, String str2, String str3) {
            this.a = str;
            this.b = view;
            this.f1999c = str2;
            this.f2000d = str3;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (this.f2000d != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                    tVar.T("st_vW", this.b);
                    tVar.T("st_sE", Integer.valueOf(i2));
                    tVar.f(this.f2000d);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX.dim("st_vW", this.b);
                aid_YuCodeX.dim("st_sE", Integer.valueOf(i2));
                mian.this.goUIEventset("onpagescrollstatechanged" + this.f2000d, aid_YuCodeX);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            if (this.f1999c != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                    tVar.T("st_vW", this.b);
                    tVar.T("st_pN", Integer.valueOf(i2));
                    tVar.T("st_pT", Float.valueOf(f));
                    tVar.T("st_pS", Integer.valueOf(i3));
                    tVar.f(this.f1999c);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX.dim("st_vW", this.b);
                aid_YuCodeX.dim("st_pN", Integer.valueOf(i2));
                aid_YuCodeX.dim("st_pT", Float.valueOf(f));
                aid_YuCodeX.dim("st_pS", Integer.valueOf(i3));
                mian.this.goUIEventset("onpagescrolled" + this.f1999c, aid_YuCodeX);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (this.a != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                    tVar.T("st_vW", this.b);
                    tVar.T("st_pN", Integer.valueOf(i2));
                    tVar.f(this.a);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX.dim("st_vW", this.b);
                aid_YuCodeX.dim("st_pN", Integer.valueOf(i2));
                mian.this.goUIEventset("onpageselected" + this.a, aid_YuCodeX);
            }
        }
    }

    /* loaded from: classes.dex */
    public class m implements SwipeRefreshLayout.OnRefreshListener {
        final /* synthetic */ String a;
        final /* synthetic */ View b;

        m(String str, View view) {
            this.a = str;
            this.b = view;
        }

        @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
        public void onRefresh() {
            if (this.a != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                    tVar.T("st_vW", this.b);
                    tVar.f(this.a);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX.dim("st_vW", this.b);
                mian.this.goUIEventset("onrefresh" + this.a, aid_YuCodeX);
            }
        }
    }

    /* loaded from: classes.dex */
    public class n implements CompoundButton.OnCheckedChangeListener {
        final /* synthetic */ String a;
        final /* synthetic */ View b;

        n(String str, View view) {
            this.a = str;
            this.b = view;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (this.a != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                    tVar.T("st_vW", this.b);
                    tVar.T("st_iC", Boolean.valueOf(z));
                    tVar.f(this.a);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX.dim("st_vW", this.b);
                aid_YuCodeX.dim("st_iC", Boolean.valueOf(z));
                mian.this.goUIEventset("oncheckedchanged" + this.a, aid_YuCodeX);
            }
        }
    }

    /* loaded from: classes.dex */
    public class o implements AppBarLayout.OnOffsetChangedListener {
        final /* synthetic */ String a;

        o(String str) {
            this.a = str;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        public void onOffsetChanged(AppBarLayout appBarLayout, int i2) {
            if (this.a != null) {
                if (mian.sh) {
                    c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                    tVar.T("st_vId", Integer.valueOf(appBarLayout.getId()));
                    tVar.T("st_vW", appBarLayout);
                    tVar.T("st_vO", Integer.valueOf(i2));
                    tVar.f(this.a);
                    return;
                }
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(appBarLayout.getId()));
                aid_YuCodeX.dim("st_vW", appBarLayout);
                aid_YuCodeX.dim("st_vO", Integer.valueOf(i2));
                mian.this.goUIEventset("onoffsetchanged" + this.a, aid_YuCodeX);
            }
        }
    }

    /* loaded from: classes.dex */
    public class p implements com.iapp.app.x5.a {
        p(mian mianVar) {
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

    /* loaded from: classes.dex */
    class q implements SensorEventListener {
        q() {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            mian.this.f1994i.T("st_x", Float.valueOf(sensorEvent.values[0]));
            mian.this.f1994i.T("st_y", Float.valueOf(sensorEvent.values[1]));
            mian.this.f1994i.T("st_z", Float.valueOf(sensorEvent.values[2]));
            mian.this.f1994i.f(c.b.a.a.p.c(mian.this.r, "<eventItme type=\"sensor\">", "</eventItme>"));
        }
    }

    /* loaded from: classes.dex */
    class r implements SensorEventListener {
        r() {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i2) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            mian.this.l.dim("st_x", Float.valueOf(sensorEvent.values[0]));
            mian.this.l.dim("st_y", Float.valueOf(sensorEvent.values[1]));
            mian.this.l.dim("st_z", Float.valueOf(sensorEvent.values[2]));
            mian.this.goUIEventset("sensor" + c.b.a.a.p.c(mian.this.r, "<eventItme type=\"sensor\">", "</eventItme>"), mian.this.l);
        }
    }

    /* loaded from: classes.dex */
    public class s implements View.OnClickListener {
        s() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (mian.sh) {
                c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                tVar.T("st_vId", Integer.valueOf(view.getId()));
                tVar.T("st_vW", view);
                tVar.f(c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"clicki\">", "</eventItme>"));
                return;
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(view.getId()));
            aid_YuCodeX.dim("st_vW", view);
            mian.this.goUIEventset("clicki" + c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"clicki\">", "</eventItme>"), aid_YuCodeX);
        }
    }

    /* loaded from: classes.dex */
    public class t implements View.OnTouchListener {
        t() {
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (mian.sh) {
                c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                tVar.T("st_vId", Integer.valueOf(view.getId()));
                tVar.T("st_vW", view);
                tVar.T("st_eA", Integer.valueOf(motionEvent.getAction()));
                tVar.T("st_eX", Float.valueOf(motionEvent.getX()));
                tVar.T("st_eY", Float.valueOf(motionEvent.getY()));
                tVar.T("st_rX", Float.valueOf(motionEvent.getRawX()));
                tVar.T("st_rY", Float.valueOf(motionEvent.getRawY()));
                return mian.this.s(tVar, "touchmonitor", ((Object[]) view.getTag())[2].toString());
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(view.getId()));
            aid_YuCodeX.dim("st_vW", view);
            aid_YuCodeX.dim("st_eA", Integer.valueOf(motionEvent.getAction()));
            aid_YuCodeX.dim("st_eX", Float.valueOf(motionEvent.getX()));
            aid_YuCodeX.dim("st_eY", Float.valueOf(motionEvent.getY()));
            aid_YuCodeX.dim("st_rX", Float.valueOf(motionEvent.getRawX()));
            aid_YuCodeX.dim("st_rY", Float.valueOf(motionEvent.getRawY()));
            return mian.this.t(aid_YuCodeX, "touchmonitor", ((Object[]) view.getTag())[2].toString());
        }
    }

    /* loaded from: classes.dex */
    public class u implements View.OnLongClickListener {
        u() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (mian.sh) {
                c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                tVar.T("st_vId", Integer.valueOf(view.getId()));
                tVar.T("st_vW", view);
                return mian.this.s(tVar, "press", ((Object[]) view.getTag())[2].toString());
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(view.getId()));
            aid_YuCodeX.dim("st_vW", view);
            return mian.this.t(aid_YuCodeX, "press", ((Object[]) view.getTag())[2].toString());
        }
    }

    /* loaded from: classes.dex */
    public class v implements View.OnKeyListener {
        v() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            if (mian.sh) {
                c.b.a.a.t tVar = new c.b.a.a.t(mian.this);
                tVar.T("st_vId", Integer.valueOf(view.getId()));
                tVar.T("st_vW", view);
                tVar.T("st_kC", Integer.valueOf(i2));
                tVar.T("st_eA", Integer.valueOf(keyEvent.getAction()));
                tVar.T("st_eR", Integer.valueOf(keyEvent.getRepeatCount()));
                return mian.this.s(tVar, "keyboard", ((Object[]) view.getTag())[2].toString());
            }
            Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(mian.this);
            aid_YuCodeX.dim("st_vId", Integer.valueOf(view.getId()));
            aid_YuCodeX.dim("st_vW", view);
            aid_YuCodeX.dim("st_kC", Integer.valueOf(i2));
            aid_YuCodeX.dim("st_eA", Integer.valueOf(keyEvent.getAction()));
            aid_YuCodeX.dim("st_eR", Integer.valueOf(keyEvent.getRepeatCount()));
            return mian.this.t(aid_YuCodeX, "keyboard", ((Object[]) view.getTag())[2].toString());
        }
    }

    /* loaded from: classes.dex */
    public class w implements View.OnCreateContextMenuListener {
        w() {
        }

        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            String c2 = c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"pressmenu\">", "</eventItme>");
            contextMenu.setHeaderTitle(c.b.a.a.p.c(c2, "title:", "\n"));
            mian.this.e.clear();
            String[] split = c2.split("\ncase ");
            for (int i2 = 1; i2 < split.length; i2++) {
                contextMenu.add(0, i2, 0, c.b.a.a.p.c(split[i2], null, ":"));
                mian.this.e.put(Integer.valueOf(i2), c.b.a.a.p.c(split[i2], ":", "\nbreak"));
            }
            String c3 = c.b.a.a.p.c(c2, "\ndefault:", "\nbreak");
            if (mian.sh) {
                mian.this.g = new c.b.a.a.t(mian.this);
                mian.this.g.T("st_vId", Integer.valueOf(view.getId()));
                mian.this.g.T("st_vW", view);
                if (c3 != null) {
                    mian.this.g.f(c3);
                    return;
                }
                return;
            }
            mian.this.j = new Aid_YuCodeX(mian.this);
            mian.this.j.dim("st_vId", Integer.valueOf(view.getId()));
            mian.this.j.dim("st_vW", view);
            if (c3 != null) {
                mian.this.goUIEventset("pressmenu" + c3, mian.this.j);
            }
        }
    }

    public static void c(String str, String str2, Object obj) {
        Object e2 = e(str, null, null);
        c.b.a.a.d.n(e2, e2.getClass(), str2, q(), new Object[]{obj});
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0067 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0097 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x003b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.Object e(java.lang.String r4, java.lang.Class<?>[] r5, java.lang.Object[] r6) {
        /*
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r1 = com.iapp.app.run.mian.packageName
            r2 = 0
            r0[r2] = r1
            r1 = 1
            r0[r1] = r4
            java.lang.String r4 = "%s.%s"
            java.lang.String r4 = java.lang.String.format(r4, r0)
            java.util.HashMap<java.lang.String, java.lang.Class<?>> r0 = com.iapp.app.run.mian.w
            boolean r0 = r0.containsKey(r4)
            r2 = 0
            if (r0 == 0) goto L23
            java.util.HashMap<java.lang.String, java.lang.Class<?>> r0 = com.iapp.app.run.mian.w
            java.lang.Object r4 = r0.get(r4)
            java.lang.Class r4 = (java.lang.Class) r4
            goto L35
        L23:
            java.lang.Class r0 = java.lang.Class.forName(r4)     // Catch: java.lang.ClassNotFoundException -> L2f
            java.util.HashMap<java.lang.String, java.lang.Class<?>> r3 = com.iapp.app.run.mian.w     // Catch: java.lang.ClassNotFoundException -> L2d
            r3.put(r4, r0)     // Catch: java.lang.ClassNotFoundException -> L2d
            goto L34
        L2d:
            r4 = move-exception
            goto L31
        L2f:
            r4 = move-exception
            r0 = r2
        L31:
            r4.printStackTrace()
        L34:
            r4 = r0
        L35:
            java.lang.String r0 = " "
            java.lang.String r3 = "YuErr: \n"
            if (r6 != 0) goto L67
            java.lang.Object r4 = r4.newInstance()     // Catch: java.lang.Exception -> L40
            return r4
        L40:
            r5 = move-exception
            r5.printStackTrace()
            java.lang.String r5 = r5.toString()
            if (r5 == 0) goto Lac
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
        L4f:
            r6.append(r3)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r6.append(r4)
            r6.append(r0)
            r6.append(r5)
            java.lang.String r4 = r6.toString()
        L63:
            c.b.a.a.t.P2(r2, r4)
            return r2
        L67:
            java.lang.reflect.Constructor r5 = r4.getDeclaredConstructor(r5)     // Catch: java.lang.Exception -> L72
            r5.setAccessible(r1)     // Catch: java.lang.Exception -> L70
            r1 = r2
            goto L7b
        L70:
            r1 = move-exception
            goto L74
        L72:
            r1 = move-exception
            r5 = r2
        L74:
            r1.printStackTrace()
            java.lang.String r1 = r1.toString()
        L7b:
            if (r1 == 0) goto L97
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r3)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r5.append(r4)
            r5.append(r0)
            r5.append(r1)
            java.lang.String r4 = r5.toString()
            goto L63
        L97:
            java.lang.Object r4 = r5.newInstance(r6)     // Catch: java.lang.Exception -> L9c
            return r4
        L9c:
            r5 = move-exception
            r5.printStackTrace()
            java.lang.String r5 = r5.toString()
            if (r5 == 0) goto Lac
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            goto L4f
        Lac:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.run.mian.e(java.lang.String, java.lang.Class[], java.lang.Object[]):java.lang.Object");
    }

    private void o(View view, String str) {
        if (str.contains("<eventItme type=\"clicki\">")) {
            view.setOnClickListener(new s());
        }
        if (str.contains("<eventItme type=\"touchmonitor\">")) {
            view.setOnTouchListener(new t());
        }
        if (str.contains("<eventItme type=\"press\">")) {
            view.setOnLongClickListener(new u());
        }
        if (str.contains("<eventItme type=\"keyboard\">")) {
            view.setOnKeyListener(new v());
        }
        if (str.contains("<eventItme type=\"pressmenu\">")) {
            view.setOnCreateContextMenuListener(new w());
        }
        if (view instanceof TextView) {
            if (str.contains("<eventItme type=\"editormonitor\">")) {
                ((TextView) view).setOnEditorActionListener(new a());
            }
            if (str.contains("<eventItme type=\"ontextchanged\">") || str.contains("<eventItme type=\"beforetextchanged\">") || str.contains("<eventItme type=\"aftertextchanged\">")) {
                if (sh) {
                    new com.iapp.app.s((TextView) view, this);
                } else {
                    new com.iapp.app.s((TextView) view, this, this.o);
                }
            }
        }
        if (view instanceof WebView) {
            if (sh) {
                v((WebView) view, str);
            } else {
                w((WebView) view, str, this.o);
            }
        }
        if (str.contains("<eventItme type=\"focuschange\">")) {
            view.setOnFocusChangeListener(new b());
        }
        if ((view instanceof AbsListView) && (str.contains("<eventItme type=\"onscrollstatechanged\">") || str.contains("<eventItme type=\"onscroll\">"))) {
            ((AbsListView) view).setOnScrollListener(new c());
        }
        if (view instanceof AdapterView) {
            if (str.contains("<eventItme type=\"clickitem\">")) {
                ((AdapterView) view).setOnItemClickListener(new d());
            }
            if (str.contains("<eventItme type=\"onitemselected\">") || str.contains("<eventItme type=\"onnothingselected\">")) {
                ((AdapterView) view).setOnItemSelectedListener(new e());
            }
        }
        if ((view instanceof ViewPager) && (str.contains("<eventItme type=\"onpageselected\">") || str.contains("<eventItme type=\"onpagescrolled\">") || str.contains("<eventItme type=\"onpagescrollstatechanged\">"))) {
            if (sh) {
                new com.iapp.app.q((ViewPager) view, this);
            } else {
                new com.iapp.app.q((ViewPager) view, this, this.o);
            }
        }
        if ((view instanceof DrawerLayout) && (str.contains("<eventItme type=\"ondrawerclosed\">") || str.contains("<eventItme type=\"ondraweropened\">") || str.contains("<eventItme type=\"onoptionsitemselected\">"))) {
            if (sh) {
                new com.iapp.app.o((DrawerLayout) view, this);
            } else {
                new com.iapp.app.o((DrawerLayout) view, this, this.o);
            }
        }
        if ((view instanceof SeekBar) && (str.contains("<eventItme type=\"onstarttrackingtouch\">") || str.contains("<eventItme type=\"onstoptrackingtouch\">") || str.contains("<eventItme type=\"onprogresschanged2\">"))) {
            ((SeekBar) view).setOnSeekBarChangeListener(new f());
        }
        p(view, str);
    }

    private void p(View view, String str) {
        String c2;
        if ((view instanceof com.iapp.app.TabLayout) && (str.contains("<eventItme type=\"ontabselected\">") || str.contains("<eventItme type=\"ontabunselected\">") || str.contains("<eventItme type=\"ontabreselected\">"))) {
            ((com.iapp.app.TabLayout) view).addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new g(c.b.a.a.p.c(str, "<eventItme type=\"ontabselected\">", "</eventItme>"), view, c.b.a.a.p.c(str, "<eventItme type=\"ontabunselected\">", "</eventItme>"), c.b.a.a.p.c(str, "<eventItme type=\"ontabreselected\">", "</eventItme>")));
        }
        if (view instanceof RecyclerView) {
            if (str.contains("<eventItme type=\"onscrollstatechanged\">") || str.contains("<eventItme type=\"onscrolled\">")) {
                ((RecyclerView) view).addOnScrollListener(new h(c.b.a.a.p.c(str, "<eventItme type=\"onscrollstatechanged\">", "</eventItme>"), c.b.a.a.p.c(str, "<eventItme type=\"onscrolled\">", "</eventItme>")));
            }
            if (str.contains("<eventItme type=\"clickitem\">") && (c2 = c.b.a.a.p.c(str, "<eventItme type=\"clickitem\">", "</eventItme>")) != null) {
                ((RecyclerView) view).addOnItemTouchListener(new j(this, new GestureDetector(this, new i(view, c2))));
            }
        }
        if ((view instanceof VerticalViewPager) && (str.contains("<eventItme type=\"onpageselected\">") || str.contains("<eventItme type=\"onpagescrolled\">") || str.contains("<eventItme type=\"onpagescrollstatechanged\">"))) {
            ((VerticalViewPager) view).setOnPageChangeListener(new l(c.b.a.a.p.c(str, "<eventItme type=\"onpageselected\">", "</eventItme>"), view, c.b.a.a.p.c(str, "<eventItme type=\"onpagescrolled\">", "</eventItme>"), c.b.a.a.p.c(str, "<eventItme type=\"onpagescrollstatechanged\">", "</eventItme>")));
        }
        if ((view instanceof SwipeRefreshLayout) && str.contains("<eventItme type=\"onrefresh\">")) {
            ((SwipeRefreshLayout) view).setOnRefreshListener(new m(c.b.a.a.p.c(str, "<eventItme type=\"onrefresh\">", "</eventItme>"), view));
        }
        if ((view instanceof CompoundButton) && str.contains("<eventItme type=\"oncheckedchanged\">")) {
            ((CompoundButton) view).setOnCheckedChangeListener(new n(c.b.a.a.p.c(str, "<eventItme type=\"oncheckedchanged\">", "</eventItme>"), view));
        }
        if ((view instanceof AppBarLayout) && str.contains("<eventItme type=\"onoffsetchanged\">")) {
            ((AppBarLayout) view).addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) new o(c.b.a.a.p.c(str, "<eventItme type=\"onoffsetchanged\">", "</eventItme>")));
        }
    }

    private static Class[] q() {
        if (v == null) {
            v = new Class[]{Aid_YuCodeX.class};
        }
        return v;
    }

    private void r(String str) {
        if (sh) {
            String str2 = this.r;
            if (str2 != null) {
                if (str2.contains("<eventItme type=\"" + str + "\">")) {
                    new c.b.a.a.t(this).f(c.b.a.a.p.c(this.r, "<eventItme type=\"" + str + "\">", "</eventItme>"));
                    return;
                }
                return;
            }
            return;
        }
        String str3 = this.r;
        if (str3 != null) {
            if (str3.contains("<eventItme type=\"" + str + "\">")) {
                Object e2 = e(this.o, null, null);
                Class<?> cls = e2.getClass();
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(c.b.a.a.p.c(this.r, "<eventItme type=\"" + str + "\">", "</eventItme>"));
                c.b.a.a.d.n(e2, cls, sb.toString(), q(), new Object[]{new Aid_YuCodeX(this)});
            }
        }
    }

    public boolean s(c.b.a.a.t tVar, String str, String str2) {
        String c2 = c.b.a.a.p.c(str2, "<eventItme type=\"" + str + "\">", "</eventItme>");
        boolean z = false;
        if (c2 == null) {
            return false;
        }
        String trim = c2.trim();
        if (trim.startsWith("[true]")) {
            z = true;
            trim = trim.substring(6);
        }
        tVar.f(trim);
        return z;
    }

    public boolean t(Aid_YuCodeX aid_YuCodeX, String str, String str2) {
        String c2 = c.b.a.a.p.c(str2, "<eventItme type=\"" + str + "\">", "</eventItme>");
        boolean z = false;
        if (c2 == null) {
            return false;
        }
        String trim = c2.trim();
        if (trim.startsWith("[true]")) {
            z = true;
            trim = trim.substring(6);
        }
        goUIEventset(str + trim, aid_YuCodeX);
        return z;
    }

    @TargetApi(11)
    private void u(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    @TargetApi(16)
    private void v(WebView webView, String str) {
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
        if (str.contains("<eventItme type=\"ondownloadstart\">")) {
            new com.iapp.app.n(webView, this);
        } else {
            webView.setDownloadListener(new k(this));
        }
        new com.iapp.app.x5.b().k(webView, str, this);
        u(webView);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    @TargetApi(16)
    private void w(WebView webView, String str, String str2) {
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
        if (str.contains("<eventItme type=\"ondownloadstart\">")) {
            new com.iapp.app.n(webView, this, str2);
        } else {
            webView.setDownloadListener(new p(this));
        }
        new com.iapp.app.x5.b().l(webView, str, str2, this);
        u(webView);
    }

    @Override // com.iapp.app.run.iActivity
    public void d() {
        super.d();
        if (com.iapp.app.a.b == null) {
            c.b.a.a.d.t(this);
            finish();
            return;
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.n = extras.getString("OpenFilexmlui");
        }
        if (this.n == null) {
            this.n = "mian.iyu";
        }
        String lowerCase = this.n.toLowerCase();
        this.n = lowerCase;
        this.o = lowerCase.substring(0, lowerCase.length() - 4);
        c.b.a.a.t.k = this.f1993d;
        if (packageName == null) {
            upPackageName();
        }
        setContentView(R.layout.ui_run_mian);
        this.f1992c = (LinearLayout) findViewById(R.id.ui_run_mian_mian);
        com.iapp.app.b.h3(this, this.n);
    }

    public void g() {
        Sensor defaultSensor;
        SensorEventListener rVar;
        boolean z = sh;
        r("loading");
        if (z) {
            String str = this.r;
            if (str == null || !str.contains("<eventItme type=\"sensor\">")) {
                return;
            }
            this.f1994i = new c.b.a.a.t(this);
            SensorManager sensorManager = (SensorManager) getSystemService("sensor");
            this.q = sensorManager;
            defaultSensor = sensorManager.getDefaultSensor(1);
            this.s = defaultSensor;
            rVar = new q();
        } else {
            String str2 = this.r;
            if (str2 == null || !str2.contains("<eventItme type=\"sensor\">")) {
                return;
            }
            this.l = new Aid_YuCodeX(this);
            SensorManager sensorManager2 = (SensorManager) getSystemService("sensor");
            this.q = sensorManager2;
            defaultSensor = sensorManager2.getDefaultSensor(1);
            this.s = defaultSensor;
            rVar = new r();
        }
        this.p = rVar;
        this.q.registerListener(rVar, defaultSensor, 2);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0070 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0071  */
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
            if (r0 == r2) goto Lc0
            if (r3 != r2) goto L2f
            goto Lc0
        L2f:
            if (r3 != 0) goto L34
            android.widget.LinearLayout r2 = r7.f1992c
            goto L3a
        L34:
            android.view.View r2 = r7.findViewById(r3)
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
        L3a:
            if (r2 != 0) goto L3d
            return
        L3d:
            java.lang.String r3 = "ProgressBar"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L68
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "\n"
            r3.append(r5)
            r3.append(r4)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            java.lang.String r6 = "\nstyle="
            java.lang.String r3 = c.b.a.a.p.c(r3, r6, r5)
            if (r3 == 0) goto L68
            c.c.a.b r5 = r7.m
            android.view.View r0 = r5.f(r0, r1, r3)
            goto L6e
        L68:
            c.c.a.b r3 = r7.m
            android.view.View r0 = r3.e(r0, r1)
        L6e:
            if (r0 != 0) goto L71
            return
        L71:
            c.c.a.b r3 = r7.m
            android.view.ViewGroup$LayoutParams r3 = r3.d(r2, r0)
            r0.setLayoutParams(r3)
            c.c.a.b r3 = r7.m
            com.iapp.app.i r5 = new com.iapp.app.i
            r5.<init>(r0, r7)
            boolean r3 = r3.a(r5, r4)
            if (r3 != 0) goto L88
            return
        L88:
            boolean r3 = r0 instanceof androidx.drawerlayout.widget.DrawerLayout
            if (r3 == 0) goto La7
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r8)
            java.lang.String r8 = "<eventItme type=\"$__tag_namexml_\">"
            r3.append(r8)
            java.lang.String r8 = r7.o
            r3.append(r8)
            java.lang.String r8 = "</eventItme>"
            r3.append(r8)
            java.lang.String r8 = r3.toString()
        La7:
            r3 = 4
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r5 = 0
            r3[r5] = r1
            r1 = 1
            r3[r1] = r4
            r1 = 2
            r3[r1] = r8
            r1 = 3
            r4 = 0
            r3[r1] = r4
            r0.setTag(r3)
            r7.o(r0, r8)
            r2.addView(r0)
        Lc0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.run.mian.g(java.lang.String):void");
    }

    public void goUIEventset(String str, Aid_YuCodeX aid_YuCodeX) {
        Object e2 = e(this.o, null, null);
        c.b.a.a.d.n(e2, e2.getClass(), str, q(), new Object[]{aid_YuCodeX});
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    @SuppressLint({"NewApi"})
    public void onActivityResult(int i2, int i3, Intent intent) {
        String c2;
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
        String str = this.r;
        if (str == null || (c2 = c.b.a.a.p.c(str, "<eventItme type=\"onactivityresult\">", "</eventItme>")) == null) {
            return;
        }
        if (sh) {
            c.b.a.a.t tVar = new c.b.a.a.t(this);
            tVar.T("st_sC", Integer.valueOf(i2));
            tVar.T("st_lC", Integer.valueOf(i3));
            tVar.T("st_iT", intent);
            tVar.f(c2);
            return;
        }
        Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this);
        aid_YuCodeX.dim("st_sC", Integer.valueOf(i2));
        aid_YuCodeX.dim("st_lC", Integer.valueOf(i3));
        aid_YuCodeX.dim("st_iT", intent);
        goUIEventset("onactivityresult" + c2, aid_YuCodeX);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        if (sh) {
            this.g.f(this.e.get(Integer.valueOf(menuItem.getItemId())));
            return true;
        }
        goUIEventset("pressmenu" + this.e.get(Integer.valueOf(menuItem.getItemId())), this.j);
        return true;
    }

    @Override // com.iapp.app.run.iActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        String c2;
        String str = this.r;
        if (str == null || (c2 = c.b.a.a.p.c(str, "<eventItme type=\"menu\">", "</eventItme>")) == null) {
            return true;
        }
        this.f.clear();
        String[] split = ("m\n" + c2).split("\ncase ", -1);
        for (int i2 = 1; i2 < split.length; i2++) {
            String c3 = c.b.a.a.p.c(split[i2], null, ":");
            if (c3.contains("|")) {
                boolean c0 = com.iapp.app.i.c0(menu, i2, c3, this);
                if (!this.t && c0) {
                    this.t = true;
                }
            } else {
                menu.add(0, i2, 0, c3);
            }
            this.f.put(Integer.valueOf(i2), c.b.a.a.p.c(split[i2], ":", "\nbreak"));
        }
        String c4 = c.b.a.a.p.c(c2, "\ndefault:", "\nbreak");
        if (sh) {
            c.b.a.a.t tVar = new c.b.a.a.t(this);
            this.h = tVar;
            if (c4 != null) {
                tVar.f(c4);
            }
        } else {
            this.k = new Aid_YuCodeX(this);
            if (c4 != null) {
                goUIEventset("menu" + c4, this.k);
            }
        }
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        r("destroy");
        SensorManager sensorManager = this.q;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this.p, this.s);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        String str = this.r;
        if (str == null || !str.contains("<eventItme type=\"downkey\">")) {
            if (i2 != 4) {
                return false;
            }
            finish();
            return true;
        }
        if (sh) {
            c.b.a.a.t tVar = new c.b.a.a.t(this);
            tVar.T("st_kC", Integer.valueOf(i2));
            tVar.T("st_eA", Integer.valueOf(keyEvent.getAction()));
            tVar.T("st_eR", Integer.valueOf(keyEvent.getRepeatCount()));
            return s(tVar, "downkey", this.r);
        }
        Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this);
        aid_YuCodeX.dim("st_kC", Integer.valueOf(i2));
        aid_YuCodeX.dim("st_eA", Integer.valueOf(keyEvent.getAction()));
        aid_YuCodeX.dim("st_eR", Integer.valueOf(keyEvent.getRepeatCount()));
        return t(aid_YuCodeX, "downkey", this.r);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        String str = this.r;
        if (str == null || !str.contains("<eventItme type=\"upkey\">")) {
            return false;
        }
        if (sh) {
            c.b.a.a.t tVar = new c.b.a.a.t(this);
            tVar.T("st_kC", Integer.valueOf(i2));
            tVar.T("st_eA", Integer.valueOf(keyEvent.getAction()));
            tVar.T("st_eR", Integer.valueOf(keyEvent.getRepeatCount()));
            return s(tVar, "upkey", this.r);
        }
        Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this);
        aid_YuCodeX.dim("st_kC", Integer.valueOf(i2));
        aid_YuCodeX.dim("st_eA", Integer.valueOf(keyEvent.getAction()));
        aid_YuCodeX.dim("st_eR", Integer.valueOf(keyEvent.getRepeatCount()));
        return t(aid_YuCodeX, "upkey", this.r);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i2, Menu menu) {
        if (this.t && menu != null && menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
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
        if (sh) {
            this.h.f(this.f.get(Integer.valueOf(menuItem.getItemId())));
            return true;
        }
        goUIEventset("menu" + this.f.get(Integer.valueOf(menuItem.getItemId())), this.k);
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        r("pause");
    }

    @Override // com.iapp.app.run.iActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        String c2;
        super.onRequestPermissionsResult(i2, strArr, iArr);
        String str = this.r;
        if (str == null || (c2 = c.b.a.a.p.c(str, "<eventItme type=\"onrequestpermissionsresult\">", "</eventItme>")) == null) {
            return;
        }
        if (sh) {
            c.b.a.a.t tVar = new c.b.a.a.t(this);
            tVar.T("st_sC", Integer.valueOf(i2));
            tVar.T("st_pS", strArr);
            tVar.T("st_gR", iArr);
            tVar.f(c2);
            return;
        }
        Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(this);
        aid_YuCodeX.dim("st_sC", Integer.valueOf(i2));
        aid_YuCodeX.dim("st_pS", strArr);
        aid_YuCodeX.dim("st_gR", iArr);
        goUIEventset("onrequestpermissionsresult" + c2, aid_YuCodeX);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        c.b.a.a.t.k = this.f1993d;
        r("restart");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        r("resume");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        r("start");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        r("stop");
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z || this.u) {
            return;
        }
        this.u = true;
        r("loadingComplete");
    }

    public void upPackageName() {
        packageName = "null";
        try {
            String obj = Class.forName(String.format("%s.IConfig", getPackageName())).getMethod("packageName", new Class[0]).invoke(null, new Object[0]).toString();
            packageName = obj;
            if (obj != null) {
                sh = false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
