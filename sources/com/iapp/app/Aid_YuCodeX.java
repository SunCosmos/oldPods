package com.iapp.app;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import bsh.EvalError;
import c.b.a.a.o;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.iapp.app.run.main;
import com.iapp.app.run.main2;
import com.iapp.app.run.main3;
import com.iapp.app.run.mian;
import com.iapp.app.x5.WebView;
import com.iapp.interfaces.OnFileDownStatusListener;
import com.iapp.interfaces.OnMessagesListener;
import dalvik.system.DexClassLoader;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.keplerproject.luajava.LuaException;

@SuppressLint({"HandlerLeak"})
/* loaded from: classes.dex */
public class Aid_YuCodeX {
    public Context a;

    /* renamed from: d */
    private Activity f1817d;
    private Handler h;

    /* renamed from: i */
    private static HashMap<String, Object> f1815i = new HashMap<>();
    private static int j = 0;
    private static HashMap<Integer, String> k = new HashMap<>();
    private static Aid_YuCodeX l = null;
    public static int v = 9865198;
    public static int f = 9865197;
    private HashMap<String, Object> b = new HashMap<>();

    /* renamed from: c */
    private HashMap<String, Object> f1816c = new HashMap<>();
    private int e = -1;
    private Aid_YuCodeX g = null;

    /* loaded from: classes.dex */
    public class a implements DialogInterface.OnClickListener {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String str = this.a;
            if (str != null) {
                Aid_YuCodeX.this.YuGo(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public class a0 implements AppBarLayout.OnOffsetChangedListener {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        a0(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        public void onOffsetChanged(AppBarLayout appBarLayout, int i2) {
            if (this.a != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(appBarLayout.getId()));
                aid_YuCodeX2.dim("st_vW", appBarLayout);
                aid_YuCodeX2.dim("st_vO", Integer.valueOf(i2));
                String str = this.b;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(this.a);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onoffsetchanged" + this.a, aid_YuCodeX2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements DialogInterface.OnClickListener {
        final /* synthetic */ String a;

        b(String str) {
            this.a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String str = this.a;
            if (str != null) {
                Aid_YuCodeX.this.YuGo(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b0 implements com.iapp.app.x5.a {
        b0(Aid_YuCodeX aid_YuCodeX) {
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
    public class c implements DialogInterface.OnClickListener {
        final /* synthetic */ String a;

        c(String str) {
            this.a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String str = this.a;
            if (str != null) {
                Aid_YuCodeX.this.YuGo(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public class c0 implements com.iapp.app.x5.a {
        c0(Aid_YuCodeX aid_YuCodeX) {
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
    public class d implements DialogInterface.OnClickListener {
        final /* synthetic */ String a;

        d(String str) {
            this.a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String str = this.a;
            if (str != null) {
                Aid_YuCodeX.this.YuGo(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public class d0 extends Thread {
        d0() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            aid_YuCodeX.YuGo(aid_YuCodeX.f1816c.get(String.valueOf(getId())).toString());
        }
    }

    /* loaded from: classes.dex */
    public class e implements DialogInterface.OnClickListener {
        final /* synthetic */ String a;

        e(String str) {
            this.a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String str = this.a;
            if (str != null) {
                Aid_YuCodeX.this.YuGo(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public class e0 implements View.OnClickListener {
        final /* synthetic */ String a;

        e0(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Aid_YuCodeX.this.YuGo(this.a);
        }
    }

    /* loaded from: classes.dex */
    public class f implements DialogInterface.OnClickListener {
        final /* synthetic */ String a;

        f(String str) {
            this.a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            String str = this.a;
            if (str != null) {
                Aid_YuCodeX.this.YuGo(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public class f0 extends ActionBarDrawerToggle {
        final /* synthetic */ String l;
        final /* synthetic */ DrawerLayout m;
        final /* synthetic */ String n;
        final /* synthetic */ String o;
        final /* synthetic */ String p;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f0(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i2, int i3, String str, DrawerLayout drawerLayout2, String str2, String str3, String str4) {
            super(activity, drawerLayout, toolbar, i2, i3);
            this.l = str;
            this.m = drawerLayout2;
            this.n = str2;
            this.o = str3;
            this.p = str4;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(View view) {
            if (this.l != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(this.m.getId()));
                aid_YuCodeX2.dim("st_vW", this.m);
                aid_YuCodeX2.dim("st_dW", view);
                String str = this.n;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(this.l);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "ondrawerclosed" + this.l, aid_YuCodeX2);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(View view) {
            if (this.o != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(this.m.getId()));
                aid_YuCodeX2.dim("st_vW", this.m);
                aid_YuCodeX2.dim("st_dW", view);
                String str = this.n;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(this.o);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "ondraweropened" + this.o, aid_YuCodeX2);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            if (this.p == null) {
                return false;
            }
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
            aid_YuCodeX2.dim("st_vId", Integer.valueOf(this.m.getId()));
            aid_YuCodeX2.dim("st_vW", this.m);
            aid_YuCodeX2.dim("st_iM", menuItem);
            String str = this.n;
            if (str == null) {
                aid_YuCodeX2.YuGoX(this.p);
                return false;
            }
            Aid_YuCodeX.this.goUIEventset(str, "onoptionsitemselected" + this.p, aid_YuCodeX2);
            return false;
        }
    }

    /* loaded from: classes.dex */
    public class g implements View.OnClickListener {
        final /* synthetic */ String a;

        g(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
            aid_YuCodeX2.dim("st_vId", Integer.valueOf(view.getId()));
            aid_YuCodeX2.dim("st_vW", view);
            String str = this.a;
            if (str == null) {
                aid_YuCodeX2.YuGoX(c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"clicki\">", "</eventItme>"));
                return;
            }
            Aid_YuCodeX.this.goUIEventset(str, "clicki" + c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"clicki\">", "</eventItme>"), aid_YuCodeX2);
        }
    }

    /* loaded from: classes.dex */
    public class g0 implements View.OnClickListener {
        final /* synthetic */ String a;

        g0(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Aid_YuCodeX.this.YuGo(this.a);
        }
    }

    /* loaded from: classes.dex */
    public class h implements View.OnTouchListener {
        final /* synthetic */ String a;

        h(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
            aid_YuCodeX2.dim("st_vId", Integer.valueOf(view.getId()));
            aid_YuCodeX2.dim("st_vW", view);
            aid_YuCodeX2.dim("st_eA", Integer.valueOf(motionEvent.getAction()));
            aid_YuCodeX2.dim("st_eX", Float.valueOf(motionEvent.getX()));
            aid_YuCodeX2.dim("st_eY", Float.valueOf(motionEvent.getY()));
            aid_YuCodeX2.dim("st_rX", Float.valueOf(motionEvent.getRawX()));
            aid_YuCodeX2.dim("st_rY", Float.valueOf(motionEvent.getRawY()));
            return Aid_YuCodeX.this.N(this.a, aid_YuCodeX2, "touchmonitor", ((Object[]) view.getTag())[2].toString());
        }
    }

    /* loaded from: classes.dex */
    public class h0 extends WebChromeClient {
        h0() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            c.b.a.a.t.P2(Aid_YuCodeX.this.a, "JsErr：\nLine" + consoleMessage.lineNumber() + ": " + consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* loaded from: classes.dex */
    public class i implements View.OnLongClickListener {
        final /* synthetic */ String a;

        i(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
            aid_YuCodeX2.dim("st_vId", Integer.valueOf(view.getId()));
            aid_YuCodeX2.dim("st_vW", view);
            return Aid_YuCodeX.this.N(this.a, aid_YuCodeX2, "press", ((Object[]) view.getTag())[2].toString());
        }
    }

    /* loaded from: classes.dex */
    public class i0 implements OnMessagesListener {
        final /* synthetic */ String a;

        i0(String str) {
            this.a = str;
        }

        @Override // com.iapp.interfaces.OnMessagesListener
        public void Message(Object obj, o.c cVar) {
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            aid_YuCodeX.dim("st_msG", aid_YuCodeX.C(obj));
            Aid_YuCodeX.this.dim("st_ssR", cVar);
            Aid_YuCodeX.this.YuGoX(this.a.toString());
        }
    }

    /* loaded from: classes.dex */
    public class j implements View.OnKeyListener {
        final /* synthetic */ String a;

        j(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
            aid_YuCodeX2.dim("st_vId", Integer.valueOf(view.getId()));
            aid_YuCodeX2.dim("st_vW", view);
            aid_YuCodeX2.dim("st_kC", Integer.valueOf(i2));
            aid_YuCodeX2.dim("st_eA", Integer.valueOf(keyEvent.getAction()));
            aid_YuCodeX2.dim("st_eR", Integer.valueOf(keyEvent.getRepeatCount()));
            return Aid_YuCodeX.this.N(this.a, aid_YuCodeX2, "keyboard", ((Object[]) view.getTag())[2].toString());
        }
    }

    /* loaded from: classes.dex */
    public class j0 implements OnMessagesListener {
        final /* synthetic */ String a;

        j0(String str) {
            this.a = str;
        }

        @Override // com.iapp.interfaces.OnMessagesListener
        public void Message(Object obj, o.c cVar) {
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            aid_YuCodeX.dim("st_msG", aid_YuCodeX.C(obj));
            Aid_YuCodeX.this.dim("st_ssR", cVar);
            Aid_YuCodeX.this.YuGoX(this.a.toString());
        }
    }

    /* loaded from: classes.dex */
    public class k extends Handler {
        k(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                Toast.makeText(Aid_YuCodeX.this.a, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                Aid_YuCodeX.this.YuGo(message.obj.toString());
            }
        }
    }

    /* loaded from: classes.dex */
    public class k0 implements OnFileDownStatusListener {
        k0() {
        }

        @Override // com.iapp.interfaces.OnFileDownStatusListener
        public void complete(int i2, Object obj) {
            if (obj == null) {
                return;
            }
            Aid_YuCodeX.this.dim("st_drJ", Integer.valueOf(i2));
            Aid_YuCodeX.this.YuGoX(obj.toString());
        }

        @Override // com.iapp.interfaces.OnFileDownStatusListener
        public void resultStatus(int i2, int i3, Object obj) {
            if (obj == null) {
                return;
            }
            Aid_YuCodeX.this.dim("st_drD", Integer.valueOf(i2));
            Aid_YuCodeX.this.dim("st_drI", Integer.valueOf(i3));
            Aid_YuCodeX.this.YuGoX(obj.toString());
        }
    }

    /* loaded from: classes.dex */
    public class l implements View.OnCreateContextMenuListener {
        final /* synthetic */ String a;

        l(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            String c2 = c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"pressmenu\">", "</eventItme>");
            contextMenu.setHeaderTitle(c.b.a.a.p.c(c2, "title:", "\n"));
            Aid_YuCodeX.k.clear();
            String[] split = c2.split("\ncase ");
            for (int i2 = 1; i2 < split.length; i2++) {
                contextMenu.add(0, i2, 0, c.b.a.a.p.c(split[i2], null, ":"));
                Aid_YuCodeX.k.put(Integer.valueOf(i2), c.b.a.a.p.c(split[i2], ":", "\nbreak"));
            }
            String c3 = c.b.a.a.p.c(c2, "\ndefault:", "\nbreak");
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            Aid_YuCodeX unused = Aid_YuCodeX.l = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
            Aid_YuCodeX.l.dim("st_vId", Integer.valueOf(view.getId()));
            Aid_YuCodeX.l.dim("st_vW", view);
            if (c3 != null) {
                String str = this.a;
                if (str == null) {
                    Aid_YuCodeX.l.YuGoX(c3);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "pressmenu" + c3, Aid_YuCodeX.l);
            }
        }
    }

    /* loaded from: classes.dex */
    public class l0 {
        public ArrayList<HashMap<Integer, Object>> a;
        public Object b;

        private l0(Aid_YuCodeX aid_YuCodeX) {
            this.a = new ArrayList<>();
            this.b = null;
        }

        /* synthetic */ l0(Aid_YuCodeX aid_YuCodeX, k kVar) {
            this(aid_YuCodeX);
        }
    }

    /* loaded from: classes.dex */
    public class m implements TextView.OnEditorActionListener {
        final /* synthetic */ String a;

        m(String str) {
            this.a = str;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            Integer num;
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
            aid_YuCodeX2.dim("st_vId", Integer.valueOf(textView.getId()));
            aid_YuCodeX2.dim("st_vW", textView);
            aid_YuCodeX2.dim("st_aI", Integer.valueOf(i2));
            if (keyEvent != null) {
                aid_YuCodeX2.dim("st_eA", Integer.valueOf(keyEvent.getAction()));
                aid_YuCodeX2.dim("st_eR", Integer.valueOf(keyEvent.getRepeatCount()));
                num = Integer.valueOf(keyEvent.getKeyCode());
            } else {
                num = null;
                aid_YuCodeX2.dim("st_eA", null);
                aid_YuCodeX2.dim("st_eR", null);
            }
            aid_YuCodeX2.dim("st_eK", num);
            return Aid_YuCodeX.this.N(this.a, aid_YuCodeX2, "editormonitor", ((Object[]) textView.getTag())[2].toString());
        }
    }

    /* loaded from: classes.dex */
    public class m0 extends BaseAdapter {
        private Context a;
        private c.b.a.a.h b;

        /* renamed from: c */
        private l0 f1819c;
        private int h;
        private Iterator k;
        private a l;

        /* renamed from: d */
        private String f1820d = null;
        private String e = null;
        private String f = null;
        private int g = 0;

        /* renamed from: i */
        private int f1821i = -1;
        private int j = -2;
        private Aid_YuCodeX m = null;
        private ArrayList<Integer> n = null;

        /* loaded from: classes.dex */
        private final class a {
            public ViewGroup a;
            public int[] b;

            /* renamed from: c */
            public View[] f1822c;

            private a(m0 m0Var) {
                this.a = null;
                this.b = null;
                this.f1822c = null;
            }

            /* synthetic */ a(m0 m0Var, k kVar) {
                this(m0Var);
            }
        }

        public m0(Context context) {
            this.a = context;
            this.b = new c.b.a.a.h(context, this, 1);
        }

        public ArrayList<HashMap<Integer, Object>> a() {
            return this.f1819c.a;
        }

        public void b(String str, l0 l0Var) {
            this.f1820d = str;
            this.f1819c = l0Var;
            String substring = str.substring(0, str.length() - 4);
            this.e = substring;
            this.g = Aid_YuCodeX.this.m(substring);
            this.e = this.e.toLowerCase();
            this.f = Aid_YuCodeX.this.getopenRestoreinterface(str);
        }

        public void c(int i2, int i3) {
            this.f1821i = i2;
            this.j = i3;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            l0 l0Var = this.f1819c;
            if (l0Var == null) {
                return 0;
            }
            return l0Var.a.size();
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
            View view2;
            HashMap<Integer, Object> hashMap = this.f1819c.a.get(i2);
            if (view == null) {
                this.l = new a(this, null);
                LinearLayout linearLayout = new LinearLayout(this.a);
                linearLayout.setLayoutParams(new AbsListView.LayoutParams(this.f1821i, this.j));
                linearLayout.setOrientation(1);
                LinearLayout linearLayout2 = new LinearLayout(this.a);
                linearLayout2.setLayoutParams(new ViewGroup.LayoutParams(this.f1821i, this.j));
                linearLayout2.setOrientation(1);
                linearLayout.addView(linearLayout2);
                Aid_YuCodeX.this.openRestoreinterface(this.f1820d, linearLayout2, this.g, hashMap);
                this.k = hashMap.keySet().iterator();
                ArrayList arrayList = new ArrayList();
                while (this.k.hasNext()) {
                    int parseInt = Integer.parseInt(String.valueOf(this.k.next()));
                    this.h = parseInt;
                    if (parseInt > 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                    }
                }
                int size = arrayList.size();
                a aVar = this.l;
                aVar.f1822c = new View[size];
                aVar.b = new int[size];
                if (this.n == null) {
                    this.n = new ArrayList<>();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(',');
                    for (int i3 = 0; i3 < size; i3++) {
                        int intValue = ((Integer) arrayList.get(i3)).intValue();
                        this.h = intValue;
                        a aVar2 = this.l;
                        aVar2.b[i3] = intValue;
                        if (intValue > 0) {
                            aVar2.f1822c[i3] = linearLayout.findViewById(intValue + this.g);
                        }
                        stringBuffer.append(this.h + this.g);
                        stringBuffer.append(',');
                    }
                    Aid_YuCodeX.this.setClickable(this.n, stringBuffer, linearLayout);
                } else {
                    for (int i4 = 0; i4 < size; i4++) {
                        int intValue2 = ((Integer) arrayList.get(i4)).intValue();
                        this.h = intValue2;
                        a aVar3 = this.l;
                        aVar3.b[i4] = intValue2;
                        if (intValue2 > 0) {
                            aVar3.f1822c[i4] = linearLayout.findViewById(intValue2 + this.g);
                        }
                    }
                }
                a aVar4 = this.l;
                aVar4.a = linearLayout2;
                linearLayout.setTag(aVar4);
                view2 = linearLayout;
            } else {
                this.l = (a) view.getTag();
                view2 = view;
            }
            int i5 = 0;
            while (true) {
                a aVar5 = this.l;
                int[] iArr = aVar5.b;
                if (i5 >= iArr.length) {
                    break;
                }
                if (iArr[i5] > 0) {
                    c.b.a.a.f.w(aVar5.f1822c[i5], hashMap.get(Integer.valueOf(iArr[i5])), hashMap, this.b);
                }
                i5++;
            }
            Iterator<Integer> it = this.n.iterator();
            while (it.hasNext()) {
                View findViewById = view2.findViewById(it.next().intValue());
                Object[] objArr = (Object[]) findViewById.getTag();
                objArr[3] = hashMap;
                findViewById.setTag(objArr);
            }
            if (this.f != null) {
                this.l.a.setTag(new Object[]{null, null, null, hashMap});
                if (this.m == null) {
                    Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                    this.m = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                }
                this.m.dim("st_vW", this.l.a);
                this.m.dim("st_lS", this.f1819c.a);
                this.m.dim("st_pN", Integer.valueOf(i2));
                Aid_YuCodeX.this.goUIEventset(this.e, "loading" + this.f, this.m);
            }
            return view2;
        }
    }

    /* loaded from: classes.dex */
    public class n implements View.OnFocusChangeListener {
        final /* synthetic */ String a;

        n(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
            aid_YuCodeX2.dim("st_vId", Integer.valueOf(view.getId()));
            aid_YuCodeX2.dim("st_vW", view);
            aid_YuCodeX2.dim("st_hF", Boolean.valueOf(z));
            String str = this.a;
            if (str == null) {
                aid_YuCodeX2.YuGoX(c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"focuschange\">", "</eventItme>"));
                return;
            }
            Aid_YuCodeX.this.goUIEventset(str, "focuschange" + c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"focuschange\">", "</eventItme>"), aid_YuCodeX2);
        }
    }

    /* loaded from: classes.dex */
    public class n0 extends RecyclerView.Adapter<a> {

        /* renamed from: c */
        private Context f1823c;

        /* renamed from: d */
        private l0 f1824d;
        private c.b.a.a.h e;
        private int j;
        private Iterator m;
        private String f = null;
        private String g = null;
        private String h = null;

        /* renamed from: i */
        private int f1825i = 0;
        private int k = -1;
        private int l = -2;
        private Aid_YuCodeX n = null;
        private ArrayList<Integer> o = null;

        /* loaded from: classes.dex */
        public class a extends RecyclerView.ViewHolder {
            public ViewGroup s;
            public int[] t;
            public View[] u;

            public a(n0 n0Var, ViewGroup viewGroup) {
                super(viewGroup);
                this.t = null;
                this.u = null;
                this.s = viewGroup;
            }
        }

        public n0(Context context) {
            this.f1823c = context;
            this.e = new c.b.a.a.h(context, this, 1);
        }

        public ArrayList<HashMap<Integer, Object>> a() {
            return this.f1824d.a;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: b */
        public void onBindViewHolder(a aVar, int i2) {
            HashMap<Integer, Object> hashMap = this.f1824d.a.get(i2);
            if (aVar.t == null) {
                Aid_YuCodeX.this.openRestoreinterface(this.f, aVar.s, this.f1825i, hashMap);
                this.m = hashMap.keySet().iterator();
                ArrayList arrayList = new ArrayList();
                while (this.m.hasNext()) {
                    int parseInt = Integer.parseInt(String.valueOf(this.m.next()));
                    this.j = parseInt;
                    if (parseInt > 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                    }
                }
                int size = arrayList.size();
                aVar.u = new View[size];
                aVar.t = new int[size];
                if (this.o == null) {
                    this.o = new ArrayList<>();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(',');
                    for (int i3 = 0; i3 < size; i3++) {
                        int intValue = ((Integer) arrayList.get(i3)).intValue();
                        this.j = intValue;
                        aVar.t[i3] = intValue;
                        if (intValue > 0) {
                            aVar.u[i3] = aVar.s.findViewById(intValue + this.f1825i);
                        }
                        stringBuffer.append(this.j + this.f1825i);
                        stringBuffer.append(',');
                    }
                    Aid_YuCodeX.this.setClickable(this.o, stringBuffer, aVar.s);
                } else {
                    for (int i4 = 0; i4 < size; i4++) {
                        int intValue2 = ((Integer) arrayList.get(i4)).intValue();
                        this.j = intValue2;
                        aVar.t[i4] = intValue2;
                        if (intValue2 > 0) {
                            aVar.u[i4] = aVar.s.findViewById(intValue2 + this.f1825i);
                        }
                    }
                }
            }
            int i5 = 0;
            while (true) {
                int[] iArr = aVar.t;
                if (i5 >= iArr.length) {
                    break;
                }
                if (iArr[i5] > 0) {
                    c.b.a.a.f.w(aVar.u[i5], hashMap.get(Integer.valueOf(iArr[i5])), hashMap, this.e);
                }
                i5++;
            }
            Iterator<Integer> it = this.o.iterator();
            while (it.hasNext()) {
                View findViewById = aVar.s.findViewById(it.next().intValue());
                Object[] objArr = (Object[]) findViewById.getTag();
                objArr[3] = hashMap;
                findViewById.setTag(objArr);
            }
            if (this.h != null) {
                aVar.s.setTag(new Object[]{null, null, null, hashMap});
                if (this.n == null) {
                    Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                    this.n = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                }
                this.n.dim("st_vW", aVar.s);
                this.n.dim("st_lS", this.f1824d.a);
                this.n.dim("st_pN", Integer.valueOf(i2));
                Aid_YuCodeX.this.goUIEventset(this.g, "loading" + this.h, this.n);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: c */
        public a onCreateViewHolder(ViewGroup viewGroup, int i2) {
            LinearLayout linearLayout = new LinearLayout(this.f1823c);
            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(this.k, this.l));
            linearLayout.setOrientation(1);
            return new a(this, linearLayout);
        }

        public void d(String str, l0 l0Var) {
            this.f = str;
            this.f1824d = l0Var;
            String substring = str.substring(0, str.length() - 4);
            this.g = substring;
            this.f1825i = Aid_YuCodeX.this.m(substring);
            this.g = this.g.toLowerCase();
            this.h = Aid_YuCodeX.this.getopenRestoreinterface(str);
        }

        public void e(int i2, int i3) {
            this.k = i2;
            this.l = i3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            l0 l0Var = this.f1824d;
            if (l0Var == null) {
                return 0;
            }
            return l0Var.a.size();
        }
    }

    /* loaded from: classes.dex */
    public class o implements AbsListView.OnScrollListener {
        final /* synthetic */ String a;

        o(String str) {
            this.a = str;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            String c2 = c.b.a.a.p.c(((Object[]) absListView.getTag())[2].toString(), "<eventItme type=\"onscroll\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(absListView.getId()));
                aid_YuCodeX2.dim("st_vW", absListView);
                aid_YuCodeX2.dim("st_fM", Integer.valueOf(i2));
                aid_YuCodeX2.dim("st_vT", Integer.valueOf(i3));
                aid_YuCodeX2.dim("st_bT", Integer.valueOf(i4));
                String str = this.a;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(c2);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onscroll" + c2, aid_YuCodeX2);
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            String c2 = c.b.a.a.p.c(((Object[]) absListView.getTag())[2].toString(), "<eventItme type=\"onscrollstatechanged\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(absListView.getId()));
                aid_YuCodeX2.dim("st_vW", absListView);
                aid_YuCodeX2.dim("st_sE", Integer.valueOf(i2));
                String str = this.a;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(c2);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onscrollstatechanged" + c2, aid_YuCodeX2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class p implements AdapterView.OnItemClickListener {
        final /* synthetic */ String a;

        p(String str) {
            this.a = str;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
            aid_YuCodeX2.dim("st_vId", Integer.valueOf(adapterView.getId()));
            aid_YuCodeX2.dim("st_vW", adapterView);
            aid_YuCodeX2.dim("st_pN", Integer.valueOf(i2));
            aid_YuCodeX2.dim("st_iD", Long.valueOf(j));
            String str = this.a;
            if (str == null) {
                aid_YuCodeX2.YuGoX(c.b.a.a.p.c(((Object[]) adapterView.getTag())[2].toString(), "<eventItme type=\"clickitem\">", "</eventItme>"));
                return;
            }
            Aid_YuCodeX.this.goUIEventset(str, "clickitem" + c.b.a.a.p.c(((Object[]) adapterView.getTag())[2].toString(), "<eventItme type=\"clickitem\">", "</eventItme>"), aid_YuCodeX2);
        }
    }

    /* loaded from: classes.dex */
    public class q implements AdapterView.OnItemSelectedListener {
        final /* synthetic */ String a;

        q(String str) {
            this.a = str;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            String c2 = c.b.a.a.p.c(((Object[]) adapterView.getTag())[2].toString(), "<eventItme type=\"onitemselected\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(adapterView.getId()));
                aid_YuCodeX2.dim("st_vW", adapterView);
                aid_YuCodeX2.dim("st_vW2", view);
                aid_YuCodeX2.dim("st_pN", Integer.valueOf(i2));
                aid_YuCodeX2.dim("st_iD", Long.valueOf(j));
                String str = this.a;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(c2);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onitemselected" + c2, aid_YuCodeX2);
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            String c2 = c.b.a.a.p.c(((Object[]) adapterView.getTag())[2].toString(), "<eventItme type=\"onnothingselected\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(adapterView.getId()));
                aid_YuCodeX2.dim("st_vW", adapterView);
                String str = this.a;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(c2);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onnothingselected" + c2, aid_YuCodeX2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class r implements SeekBar.OnSeekBarChangeListener {
        final /* synthetic */ String a;

        r(String str) {
            this.a = str;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            String c2 = c.b.a.a.p.c(((Object[]) seekBar.getTag())[2].toString(), "<eventItme type=\"onprogresschanged2\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(seekBar.getId()));
                aid_YuCodeX2.dim("st_vW", seekBar);
                aid_YuCodeX2.dim("st_nS", Integer.valueOf(i2));
                aid_YuCodeX2.dim("st_fR", Boolean.valueOf(z));
                String str = this.a;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(c2);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onprogresschanged2" + c2, aid_YuCodeX2);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            String c2 = c.b.a.a.p.c(((Object[]) seekBar.getTag())[2].toString(), "<eventItme type=\"onstarttrackingtouch\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(seekBar.getId()));
                aid_YuCodeX2.dim("st_vW", seekBar);
                String str = this.a;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(c2);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onstarttrackingtouch" + c2, aid_YuCodeX2);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            String c2 = c.b.a.a.p.c(((Object[]) seekBar.getTag())[2].toString(), "<eventItme type=\"onstoptrackingtouch\">", "</eventItme>");
            if (c2 != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(seekBar.getId()));
                aid_YuCodeX2.dim("st_vW", seekBar);
                String str = this.a;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(c2);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onstoptrackingtouch" + c2, aid_YuCodeX2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class s implements TabLayout.OnTabSelectedListener {
        final /* synthetic */ String a;
        final /* synthetic */ View b;

        /* renamed from: c */
        final /* synthetic */ String f1826c;

        /* renamed from: d */
        final /* synthetic */ String f1827d;
        final /* synthetic */ String e;

        s(String str, View view, String str2, String str3, String str4) {
            this.a = str;
            this.b = view;
            this.f1826c = str2;
            this.f1827d = str3;
            this.e = str4;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
            if (this.e != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX2.dim("st_vW", this.b);
                aid_YuCodeX2.dim("st_pN", Integer.valueOf(tab.getPosition()));
                aid_YuCodeX2.dim("st_tT", String.valueOf(tab.getText()));
                aid_YuCodeX2.dim("st_taB", tab);
                String str = this.f1826c;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(this.e);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "ontabreselected" + this.e, aid_YuCodeX2);
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            if (this.a != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX2.dim("st_vW", this.b);
                aid_YuCodeX2.dim("st_pN", Integer.valueOf(tab.getPosition()));
                aid_YuCodeX2.dim("st_tT", String.valueOf(tab.getText()));
                aid_YuCodeX2.dim("st_taB", tab);
                String str = this.f1826c;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(this.a);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "ontabselected" + this.a, aid_YuCodeX2);
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            if (this.f1827d != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX2.dim("st_vW", this.b);
                aid_YuCodeX2.dim("st_pN", Integer.valueOf(tab.getPosition()));
                aid_YuCodeX2.dim("st_tT", String.valueOf(tab.getText()));
                aid_YuCodeX2.dim("st_taB", tab);
                String str = this.f1826c;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(this.f1827d);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "ontabunselected" + this.f1827d, aid_YuCodeX2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class t extends RecyclerView.OnScrollListener {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ String f1828c;

        t(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.f1828c = str3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (this.a != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(recyclerView.getId()));
                aid_YuCodeX2.dim("st_vW", recyclerView);
                aid_YuCodeX2.dim("st_sE", Integer.valueOf(i2));
                String str = this.b;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(this.a);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onscrollstatechanged" + this.a, aid_YuCodeX2);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            if (this.f1828c != null) {
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
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(recyclerView.getId()));
                aid_YuCodeX2.dim("st_vW", recyclerView);
                aid_YuCodeX2.dim("st_fM", Integer.valueOf(i4));
                aid_YuCodeX2.dim("st_vT", Integer.valueOf(childCount));
                aid_YuCodeX2.dim("st_bT", Integer.valueOf(itemCount));
                aid_YuCodeX2.dim("st_dX", Integer.valueOf(i2));
                aid_YuCodeX2.dim("st_dY", Integer.valueOf(i3));
                if (i3 > 0 && childCount + i4 >= itemCount) {
                    z = true;
                }
                aid_YuCodeX2.dim("st_isB", Boolean.valueOf(z));
                String str = this.b;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(this.f1828c);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onscrolled" + this.f1828c, aid_YuCodeX2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class u extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ View a;
        final /* synthetic */ String b;

        /* renamed from: c */
        final /* synthetic */ String f1830c;

        u(View view, String str, String str2) {
            this.a = view;
            this.b = str;
            this.f1830c = str2;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            View findChildViewUnder = ((RecyclerView) this.a).findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (findChildViewUnder == null || this.b == null) {
                return;
            }
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
            aid_YuCodeX2.dim("st_vId", Integer.valueOf(this.a.getId()));
            aid_YuCodeX2.dim("st_vW", this.a);
            aid_YuCodeX2.dim("st_pN", Integer.valueOf(((RecyclerView) this.a).getChildLayoutPosition(findChildViewUnder)));
            aid_YuCodeX2.dim("st_vW2", findChildViewUnder);
            String str = this.f1830c;
            if (str == null) {
                aid_YuCodeX2.YuGoX(this.b);
                return;
            }
            Aid_YuCodeX.this.goUIEventset(str, "clickitem" + this.b, aid_YuCodeX2);
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
            Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
            Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
            aid_YuCodeX2.dim("st_vId", Integer.valueOf(this.a.getId()));
            aid_YuCodeX2.dim("st_vW", this.a);
            aid_YuCodeX2.dim("st_pN", Integer.valueOf(((RecyclerView) this.a).getChildLayoutPosition(findChildViewUnder)));
            aid_YuCodeX2.dim("st_vW2", findChildViewUnder);
            String str = this.f1830c;
            if (str == null) {
                aid_YuCodeX2.YuGoX(this.b);
                return true;
            }
            Aid_YuCodeX.this.goUIEventset(str, "clickitem" + this.b, aid_YuCodeX2);
            return true;
        }
    }

    /* loaded from: classes.dex */
    public class v extends Handler {
        v(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                Toast.makeText(Aid_YuCodeX.this.a, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                Aid_YuCodeX.this.YuGo(message.obj.toString());
            }
        }
    }

    /* loaded from: classes.dex */
    public class w implements RecyclerView.OnItemTouchListener {
        final /* synthetic */ GestureDetector a;

        w(Aid_YuCodeX aid_YuCodeX, GestureDetector gestureDetector) {
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
    public class x implements ViewPager.OnPageChangeListener {
        final /* synthetic */ String a;
        final /* synthetic */ View b;

        /* renamed from: c */
        final /* synthetic */ String f1832c;

        /* renamed from: d */
        final /* synthetic */ String f1833d;
        final /* synthetic */ String e;

        x(String str, View view, String str2, String str3, String str4) {
            this.a = str;
            this.b = view;
            this.f1832c = str2;
            this.f1833d = str3;
            this.e = str4;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (this.e != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX2.dim("st_vW", this.b);
                aid_YuCodeX2.dim("st_sE", Integer.valueOf(i2));
                String str = this.f1832c;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(this.e);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onpagescrollstatechanged" + this.e, aid_YuCodeX2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            if (this.f1833d != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX2.dim("st_vW", this.b);
                aid_YuCodeX2.dim("st_pN", Integer.valueOf(i2));
                aid_YuCodeX2.dim("st_pT", Float.valueOf(f));
                aid_YuCodeX2.dim("st_pS", Integer.valueOf(i3));
                String str = this.f1832c;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(this.f1833d);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onpagescrolled" + this.f1833d, aid_YuCodeX2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (this.a != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX2.dim("st_vW", this.b);
                aid_YuCodeX2.dim("st_pN", Integer.valueOf(i2));
                String str = this.f1832c;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(this.a);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onpageselected" + this.a, aid_YuCodeX2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class y implements SwipeRefreshLayout.OnRefreshListener {
        final /* synthetic */ String a;
        final /* synthetic */ View b;

        /* renamed from: c */
        final /* synthetic */ String f1834c;

        y(String str, View view, String str2) {
            this.a = str;
            this.b = view;
            this.f1834c = str2;
        }

        @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
        public void onRefresh() {
            if (this.a != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX2.dim("st_vW", this.b);
                String str = this.f1834c;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(this.a);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "onrefresh" + this.a, aid_YuCodeX2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class z implements CompoundButton.OnCheckedChangeListener {
        final /* synthetic */ String a;
        final /* synthetic */ View b;

        /* renamed from: c */
        final /* synthetic */ String f1836c;

        z(String str, View view, String str2) {
            this.a = str;
            this.b = view;
            this.f1836c = str2;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (this.a != null) {
                Aid_YuCodeX aid_YuCodeX = Aid_YuCodeX.this;
                Aid_YuCodeX aid_YuCodeX2 = new Aid_YuCodeX(aid_YuCodeX.a, aid_YuCodeX.f1817d);
                aid_YuCodeX2.dim("st_vId", Integer.valueOf(this.b.getId()));
                aid_YuCodeX2.dim("st_vW", this.b);
                aid_YuCodeX2.dim("st_iC", Boolean.valueOf(z));
                String str = this.f1836c;
                if (str == null) {
                    aid_YuCodeX2.YuGoX(this.a);
                    return;
                }
                Aid_YuCodeX.this.goUIEventset(str, "oncheckedchanged" + this.a, aid_YuCodeX2);
            }
        }
    }

    public Aid_YuCodeX(Activity activity) {
        this.f1817d = null;
        this.a = null;
        this.h = null;
        this.f1817d = activity;
        this.a = activity;
        this.h = new k(activity.getMainLooper());
    }

    public Aid_YuCodeX(Context context, Activity activity) {
        this.f1817d = null;
        this.a = null;
        this.h = null;
        this.f1817d = activity;
        this.a = context;
        this.h = new v(context.getMainLooper());
    }

    private int[] A() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.f1817d.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    private String[] B() {
        return new String[]{Build.MODEL, Build.BRAND, String.valueOf(Build.VERSION.SDK_INT)};
    }

    private int D() {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return this.a.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private int E() {
        int identifier = this.a.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return this.a.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private Object F(String str) {
        String trim = str.trim();
        if (trim.equals("null")) {
            return null;
        }
        if (trim.equals("true")) {
            return Boolean.TRUE;
        }
        if (trim.equals("false")) {
            return Boolean.FALSE;
        }
        if (trim.equals("activity")) {
            return this.a;
        }
        boolean z2 = true;
        if (!trim.contains("[\n龘`/r}")) {
            return (trim.startsWith("\"") && trim.endsWith("\"")) ? c.b.a.a.t.V(c.b.a.a.t.a(trim.substring(1, trim.length() - 1))) : O(trim) ? trim.contains(".") ? trim.startsWith("00") ? trim : Double.valueOf(Double.parseDouble(trim)) : trim.startsWith("00") ? trim : trim.length() < 10 ? Integer.valueOf(Integer.parseInt(trim)) : Long.valueOf(Long.parseLong(trim)) : r(trim);
        }
        String[] a02 = a0(trim, "[\n龘`/r}");
        int length = a02.length;
        Object[] objArr = new Object[length];
        double d2 = 0.0d;
        for (int i2 = 0; i2 < length; i2++) {
            objArr[i2] = F(a02[i2].trim());
            if (z2) {
                if (objArr[i2] instanceof Number) {
                    d2 += Double.parseDouble(objArr[i2].toString());
                } else {
                    z2 = false;
                }
            }
        }
        if (z2) {
            return Double.valueOf(d2);
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = 0; i3 < length; i3++) {
            stringBuffer.append(objArr[i3]);
        }
        return stringBuffer.toString();
    }

    private String G(String str) {
        return String.valueOf(F(str));
    }

    private String H(String str) {
        Object F = F(str);
        if (F == null) {
            return null;
        }
        return F.toString();
    }

    private String J(String str, String str2, String str3) {
        int indexOf = str.indexOf("type=\"" + str2 + "\">");
        if (indexOf == -1) {
            return str + "<eventItme type=\"" + str2 + "\">" + str3.replace("<", "&lt;").replace(">", "&gt;") + "</eventItme>";
        }
        int indexOf2 = str.indexOf("</eventItme>");
        if (indexOf2 == -1) {
            return str;
        }
        return str.substring(0, indexOf) + "type=\"" + str2 + "\">" + str3.replace("<", "&lt;").replace(">", "&gt;") + str.substring(indexOf2);
    }

    private GradientDrawable.Orientation K(String str) {
        return str.equals("topbottom") ? GradientDrawable.Orientation.TOP_BOTTOM : str.equals("trbl") ? GradientDrawable.Orientation.TR_BL : str.equals("rightleft") ? GradientDrawable.Orientation.RIGHT_LEFT : str.equals("brtl") ? GradientDrawable.Orientation.BR_TL : str.equals("bottomtop") ? GradientDrawable.Orientation.BOTTOM_TOP : str.equals("bltr") ? GradientDrawable.Orientation.BL_TR : str.equals("leftright") ? GradientDrawable.Orientation.LEFT_RIGHT : str.equals("tlbr") ? GradientDrawable.Orientation.TL_BR : GradientDrawable.Orientation.TOP_BOTTOM;
    }

    private String L(String str) {
        String c2;
        String c3;
        Intent launchIntentForPackage = this.a.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null || (c2 = c.b.a.a.p.c(launchIntentForPackage.toString(), "cmp=", " ")) == null || (c3 = c.b.a.a.p.c(c2, "/", null)) == null) {
            return "";
        }
        if (!c3.startsWith(".")) {
            return c3;
        }
        return str + c3;
    }

    private int M(String str) {
        if (str.equals("-1")) {
            return -1;
        }
        if (str.equals("-2")) {
            return -2;
        }
        return str.endsWith("dp") ? c.b.a.a.p.l(this.a, Float.parseFloat(str.substring(0, str.length() - 2))) : c(str);
    }

    public boolean N(String str, Aid_YuCodeX aid_YuCodeX, String str2, String str3) {
        String c2 = c.b.a.a.p.c(str3, "<eventItme type=\"" + str2 + "\">", "</eventItme>");
        boolean z2 = false;
        if (c2 == null) {
            return false;
        }
        String trim = c2.trim();
        if (trim.startsWith("[true]")) {
            z2 = true;
            trim = trim.substring(6);
        }
        if (str == null) {
            aid_YuCodeX.YuGoX(trim);
        } else {
            mian.c(str, str2 + trim, aid_YuCodeX);
        }
        return z2;
    }

    private boolean O(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("[+-]?[0-9]+[0-9]*(\\.[0-9]+)?");
    }

    private void P(File file) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        c.b.a.a.k.a(this.a, intent, file, c.b.a.a.p.p(file));
        this.a.startActivity(intent);
    }

    private static String[] Q(String str, char c2, char c3) {
        return c.b.a.a.t.M1(str, c2, c3);
    }

    private static int R(String str, char c2) {
        return c.b.a.a.t.N1(str, c2);
    }

    private static int S(String str, char c2, char c3) {
        return c.b.a.a.t.O1(str, c2, c3);
    }

    private static String[] T(String str) {
        return c.b.a.a.t.P1(str);
    }

    @TargetApi(11)
    private void U(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    @TargetApi(16)
    private void V(WebView webView, String str) {
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAppCachePath(this.a.getApplicationContext().getDir("cache", 0).getPath());
        webView.getSettings().setAppCacheMaxSize(8388608L);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDatabasePath(this.a.getApplicationContext().getDir("database", 0).getPath());
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
            new com.iapp.app.n(webView, this.f1817d);
        } else {
            webView.setDownloadListener(new b0(this));
        }
        new com.iapp.app.x5.b().n(webView, this.a, this.f1817d);
        U(webView);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    @TargetApi(16)
    private void W(WebView webView, String str, String str2) {
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAppCachePath(this.a.getApplicationContext().getDir("cache", 0).getPath());
        webView.getSettings().setAppCacheMaxSize(8388608L);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDatabasePath(this.a.getApplicationContext().getDir("database", 0).getPath());
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
            new com.iapp.app.n(webView, this.f1817d, str2);
        } else {
            webView.setDownloadListener(new c0(this));
        }
        new com.iapp.app.x5.b().o(webView, this.a, this.f1817d, this, str2);
        U(webView);
    }

    private void X(String str) {
        c.b.a.a.f.G(this.a, str);
    }

    private boolean Y(String str, String str2) {
        int indexOf = str.indexOf(10);
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        return str.startsWith(str2) && str.endsWith(")");
    }

    public static String YuLaGf(String str) {
        String str2 = "";
        if (str.contains("\r")) {
            str = str.replace("\r", "");
        }
        boolean z2 = false;
        for (String str3 : c.b.a.a.q.b(str, '\n')) {
            String trim = str3.trim();
            if (trim.length() != 0) {
                if (z2 && trim.startsWith("<")) {
                    z2 = false;
                } else if (trim.startsWith(".") || trim.startsWith("//") || trim.startsWith("/.") || trim.startsWith("./") || z2) {
                    if (trim.startsWith("/.")) {
                        z2 = true;
                    }
                    if (trim.startsWith("./")) {
                        z2 = false;
                    }
                }
                if (!trim.equals("}")) {
                    if (trim.startsWith("}")) {
                        trim = "}\n" + trim.substring(1).trim();
                    }
                    if (trim.endsWith("}")) {
                        trim = trim.substring(0, trim.length() - 1).trim() + "\n}";
                    }
                }
                if (!trim.equals("{")) {
                    if (trim.startsWith("{")) {
                        trim = "{\n" + trim.substring(1).trim();
                    }
                    if (trim.endsWith("{")) {
                        trim = trim.substring(0, trim.length() - 1).trim() + "\n{";
                    }
                }
                str2 = str2 + trim + "\n";
            }
        }
        return str2;
    }

    private String[] Z(String str, char c2) {
        return c.b.a.a.q.b(str, c2);
    }

    private boolean a(String str) {
        int S = S(str, '=', '=');
        if (S != -1) {
            Object F = F(str.substring(0, S));
            Object F2 = F(str.substring(S + 2));
            if (F == null && F2 == null) {
                return true;
            }
            if (F != null && F2 != null) {
                if ((F instanceof Double) || (F2 instanceof Double)) {
                    try {
                        if (Double.parseDouble(String.valueOf(F)) == Double.parseDouble(String.valueOf(F2))) {
                            return true;
                        }
                    } catch (NumberFormatException unused) {
                    }
                }
                if (String.valueOf(F).equals(String.valueOf(F2))) {
                    return true;
                }
            }
            return false;
        }
        int S2 = S(str, '!', '=');
        if (S2 != -1) {
            Object F3 = F(str.substring(0, S2));
            Object F4 = F(str.substring(S2 + 2));
            if (F3 == null && F4 == null) {
                return false;
            }
            return F3 == null || F4 == null || !String.valueOf(F3).equals(String.valueOf(F4));
        }
        int S3 = S(str, '>', '=');
        if (S3 != -1) {
            Object F5 = F(str.substring(0, S3));
            Object F6 = F(str.substring(S3 + 2));
            return (F5 == null || F6 == null || Double.parseDouble(F5.toString()) < Double.parseDouble(F6.toString())) ? false : true;
        }
        int S4 = S(str, '<', '=');
        if (S4 != -1) {
            Object F7 = F(str.substring(0, S4));
            Object F8 = F(str.substring(S4 + 2));
            return (F7 == null || F8 == null || Double.parseDouble(F7.toString()) > Double.parseDouble(F8.toString())) ? false : true;
        }
        int R = R(str, '>');
        if (R != -1) {
            Object F9 = F(str.substring(0, R));
            Object F10 = F(str.substring(R + 1));
            return (F9 == null || F10 == null || Double.parseDouble(F9.toString()) <= Double.parseDouble(F10.toString())) ? false : true;
        }
        int R2 = R(str, '<');
        if (R2 != -1) {
            String G = G(str.substring(0, R2));
            String G2 = G(str.substring(R2 + 1));
            return (G == null || G2 == null || Double.parseDouble(G.toString()) >= Double.parseDouble(G2.toString())) ? false : true;
        }
        int S5 = S(str, '?', '*');
        if (S5 != -1) {
            String G3 = G(str.substring(0, S5));
            String G4 = G(str.substring(S5 + 2));
            return (G3 == null || G4 == null || !G3.startsWith(G4)) ? false : true;
        }
        int S6 = S(str, '*', '?');
        if (S6 != -1) {
            String G5 = G(str.substring(0, S6));
            String G6 = G(str.substring(S6 + 2));
            return (G5 == null || G6 == null || !G5.endsWith(G6)) ? false : true;
        }
        int R3 = R(str, '?');
        if (R3 == -1) {
            Object F11 = F(str);
            return F11 != null && F11.equals(Boolean.TRUE);
        }
        String G7 = G(str.substring(0, R3));
        String G8 = G(str.substring(R3 + 1));
        return (G7 == null || G8 == null || !G7.contains(G8)) ? false : true;
    }

    private String[] a0(String str, String str2) {
        return c.b.a.a.q.d(str, str2);
    }

    private boolean b(String str) {
        boolean z2 = false;
        for (String str2 : Q(str, '|', '|')) {
            boolean z3 = false;
            for (String str3 : Q(str2, '&', '&')) {
                String trim = str3.trim();
                z3 = trim.startsWith("!") ? !a(trim.substring(1)) : a(trim);
                if (!z3) {
                    break;
                }
            }
            z2 = z3;
            if (z2) {
                break;
            }
        }
        return z2;
    }

    private void b0(String str) {
        c.b.a.a.t.P2(this.a, str);
    }

    private int c(String str) {
        return (int) Double.parseDouble(str);
    }

    private String c0(String str) {
        return c.b.a.a.t.a(str.substring(str.indexOf(40) + 1, str.lastIndexOf(41)));
    }

    private String d0(String str) {
        int indexOf = str.indexOf(10);
        if (indexOf == -1) {
            indexOf = str.length();
        }
        return c.b.a.a.t.a(str.substring(str.indexOf(40) + 1, str.lastIndexOf(41, indexOf)));
    }

    public static void degclear() {
        c.b.a.a.t.j.clear();
        k.clear();
        l = null;
        f1815i.clear();
        j = 0;
    }

    private void k(View view, String str, String str2) {
        if (str.contains("<eventItme type=\"clicki\">")) {
            view.setOnClickListener(new g(str2));
        }
        if (str.contains("<eventItme type=\"touchmonitor\">")) {
            view.setOnTouchListener(new h(str2));
        }
        if (str.contains("<eventItme type=\"press\">")) {
            view.setOnLongClickListener(new i(str2));
        }
        if (str.contains("<eventItme type=\"keyboard\">")) {
            view.setOnKeyListener(new j(str2));
        }
        if (str.contains("<eventItme type=\"pressmenu\">")) {
            view.setOnCreateContextMenuListener(new l(str2));
        }
        if (view instanceof TextView) {
            if (str.contains("<eventItme type=\"editormonitor\">")) {
                ((TextView) view).setOnEditorActionListener(new m(str2));
            }
            if (str.contains("<eventItme type=\"ontextchanged\">") || str.contains("<eventItme type=\"beforetextchanged\">") || str.contains("<eventItme type=\"aftertextchanged\">")) {
                TextView textView = (TextView) view;
                Activity activity = this.f1817d;
                if (str2 == null) {
                    new com.iapp.app.s(textView, activity);
                } else {
                    new com.iapp.app.s(textView, activity, str2);
                }
            }
        }
        if (view instanceof WebView) {
            WebView webView = (WebView) view;
            if (str2 == null) {
                V(webView, str);
            } else {
                W(webView, str, str2);
            }
        }
        if (str.contains("<eventItme type=\"focuschange\">")) {
            view.setOnFocusChangeListener(new n(str2));
        }
        if ((view instanceof AbsListView) && (str.contains("<eventItme type=\"onscrollstatechanged\">") || str.contains("<eventItme type=\"onscroll\">"))) {
            ((AbsListView) view).setOnScrollListener(new o(str2));
        }
        if (view instanceof AdapterView) {
            if (str.contains("<eventItme type=\"clickitem\">")) {
                ((AdapterView) view).setOnItemClickListener(new p(str2));
            }
            if (str.contains("<eventItme type=\"onitemselected\">") || str.contains("<eventItme type=\"onnothingselected\">")) {
                ((AdapterView) view).setOnItemSelectedListener(new q(str2));
            }
        }
        if ((view instanceof ViewPager) && (str.contains("<eventItme type=\"onpageselected\">") || str.contains("<eventItme type=\"onpagescrolled\">") || str.contains("<eventItme type=\"onpagescrollstatechanged\">"))) {
            ViewPager viewPager = (ViewPager) view;
            Activity activity2 = this.f1817d;
            if (str2 == null) {
                new com.iapp.app.q(viewPager, activity2);
            } else {
                new com.iapp.app.q(viewPager, activity2, str2);
            }
        }
        if ((view instanceof DrawerLayout) && (str.contains("<eventItme type=\"ondrawerclosed\">") || str.contains("<eventItme type=\"ondraweropened\">") || str.contains("<eventItme type=\"onoptionsitemselected\">"))) {
            DrawerLayout drawerLayout = (DrawerLayout) view;
            Activity activity3 = this.f1817d;
            if (str2 == null) {
                new com.iapp.app.o(drawerLayout, activity3);
            } else {
                new com.iapp.app.o(drawerLayout, activity3, str2);
            }
        }
        if ((view instanceof SeekBar) && (str.contains("<eventItme type=\"onstarttrackingtouch\">") || str.contains("<eventItme type=\"onstoptrackingtouch\">") || str.contains("<eventItme type=\"onprogresschanged2\">"))) {
            ((SeekBar) view).setOnSeekBarChangeListener(new r(str2));
        }
        l(view, str, str2);
    }

    private void l(View view, String str, String str2) {
        String c2;
        if ((view instanceof TabLayout) && (str.contains("<eventItme type=\"ontabselected\">") || str.contains("<eventItme type=\"ontabunselected\">") || str.contains("<eventItme type=\"ontabreselected\">"))) {
            ((TabLayout) view).addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new s(c.b.a.a.p.c(str, "<eventItme type=\"ontabselected\">", "</eventItme>"), view, str2, c.b.a.a.p.c(str, "<eventItme type=\"ontabunselected\">", "</eventItme>"), c.b.a.a.p.c(str, "<eventItme type=\"ontabreselected\">", "</eventItme>")));
        }
        if (view instanceof RecyclerView) {
            if (str.contains("<eventItme type=\"onscrollstatechanged\">") || str.contains("<eventItme type=\"onscrolled\">")) {
                ((RecyclerView) view).addOnScrollListener(new t(c.b.a.a.p.c(str, "<eventItme type=\"onscrollstatechanged\">", "</eventItme>"), str2, c.b.a.a.p.c(str, "<eventItme type=\"onscrolled\">", "</eventItme>")));
            }
            if (str.contains("<eventItme type=\"clickitem\">") && (c2 = c.b.a.a.p.c(str, "<eventItme type=\"clickitem\">", "</eventItme>")) != null) {
                ((RecyclerView) view).addOnItemTouchListener(new w(this, new GestureDetector(this.a, new u(view, c2, str2))));
            }
        }
        if ((view instanceof VerticalViewPager) && (str.contains("<eventItme type=\"onpageselected\">") || str.contains("<eventItme type=\"onpagescrolled\">") || str.contains("<eventItme type=\"onpagescrollstatechanged\">"))) {
            ((VerticalViewPager) view).setOnPageChangeListener(new x(c.b.a.a.p.c(str, "<eventItme type=\"onpageselected\">", "</eventItme>"), view, str2, c.b.a.a.p.c(str, "<eventItme type=\"onpagescrolled\">", "</eventItme>"), c.b.a.a.p.c(str, "<eventItme type=\"onpagescrollstatechanged\">", "</eventItme>")));
        }
        if ((view instanceof SwipeRefreshLayout) && str.contains("<eventItme type=\"onrefresh\">")) {
            ((SwipeRefreshLayout) view).setOnRefreshListener(new y(c.b.a.a.p.c(str, "<eventItme type=\"onrefresh\">", "</eventItme>"), view, str2));
        }
        if ((view instanceof CompoundButton) && str.contains("<eventItme type=\"oncheckedchanged\">")) {
            ((CompoundButton) view).setOnCheckedChangeListener(new z(c.b.a.a.p.c(str, "<eventItme type=\"oncheckedchanged\">", "</eventItme>"), view, str2));
        }
        if ((view instanceof AppBarLayout) && str.contains("<eventItme type=\"onoffsetchanged\">")) {
            ((AppBarLayout) view).addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) new a0(c.b.a.a.p.c(str, "<eventItme type=\"onoffsetchanged\">", "</eventItme>"), str2));
        }
    }

    public synchronized int m(String str) {
        Object obj = f1815i.get(str);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        int i2 = j + 10000;
        j = i2;
        f1815i.put(str, Integer.valueOf(i2));
        return j;
    }

    private int o(String str) {
        if (str.startsWith("s ")) {
            return dims(str, 0) ? 0 : -1;
        }
        if (str.startsWith("ss ")) {
            return dims(str, 1) ? 0 : -1;
        }
        if (str.startsWith("sss ")) {
            return dims(str, 2) ? 0 : -1;
        }
        if (Y(str, c.b.a.a.t.t[0])) {
            return v != f ? f(str) : if_y2(str);
        }
        if (Y(str, c.b.a.a.t.t[1])) {
            return for_y(str);
        }
        if (Y(str, c.b.a.a.t.t[2])) {
            return w(str);
        }
        if (Y(str, c.b.a.a.t.t[3])) {
            t(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[4])) {
            ssj(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[5])) {
            syso(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[6])) {
            tw(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[7])) {
            fd(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[8])) {
            fe(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[9])) {
            fs(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[10])) {
            fr(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[11])) {
            fc(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[12])) {
            fw(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[13])) {
            fl(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[14])) {
            ft(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[15])) {
            fdir(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[16])) {
            fuz(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[17])) {
            fuzs(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[18])) {
            fo(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[19])) {
            fj(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[20])) {
            s(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[21])) {
            s2(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[22])) {
            sn(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[23])) {
            sjia(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[24])) {
            sjian(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[25])) {
            scheng(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[26])) {
            schu(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[27])) {
            syu(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[28])) {
            ss(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[29])) {
            sr(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[30])) {
            sj(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[31])) {
            sl(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[32])) {
            siof(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[33])) {
            slof(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[34])) {
            ssg(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[35])) {
            slg(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[36])) {
            strim(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[37])) {
            slower(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[38])) {
            supper(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[39])) {
            stop(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[40])) {
            sran(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[41])) {
            nsz(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[42])) {
            sgsz(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[43])) {
            sssz(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[44])) {
            sgszl(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[45])) {
            hs(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[46])) {
            hd(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[47])) {
            hw(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[48])) {
            hws(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[49])) {
            ug(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[50])) {
            us(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[51])) {
            uigo(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[52])) {
            utw(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[53])) {
            endutw();
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[54])) {
            this.f1817d.finish();
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[55])) {
            ends();
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[56])) {
            bfm(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[57])) {
            bfms(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[58])) {
            ula(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[59])) {
            uls(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[60])) {
            ulas(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[61])) {
            ulag(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[62])) {
            usms(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[63])) {
            ucall(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[64])) {
            time(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[65])) {
            fi(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[66])) {
            stobm(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[67])) {
            sutf8to(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[68])) {
            uycl(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[69])) {
            ushsp(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[70])) {
            bfv(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[71])) {
            bfvs(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[72])) {
            bfvss(str);
            return 0;
        }
        if (str.startsWith("fn ")) {
            function(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[73])) {
            ftz(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[74])) {
            uapp(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[75])) {
            uapplist(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[76])) {
            uapplistgo(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[77])) {
            uninapp(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[78])) {
            huf(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[79])) {
            nvw(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[80])) {
            uall(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[81])) {
            urvw(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[82])) {
            sbp(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[83])) {
            sdeg(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[84])) {
            bfs(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[85])) {
            tot(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[86])) {
            tzz(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[87])) {
            tsf(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[88])) {
            tfz(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[89])) {
            sxb(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[90])) {
            shb(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[91])) {
            tcc(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[92])) {
            usjxm(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[93])) {
            addv(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[94])) {
            gvs(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[95])) {
            aslist(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[96])) {
            sslist(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[97])) {
            gslist(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[98])) {
            gslistl(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[99])) {
            dslist(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[100])) {
            gslistsz(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[101])) {
            gslistis(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[102])) {
            gslistiof(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[103])) {
            gslistlof(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[104])) {
            nuibs(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[105])) {
            ngde(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[106])) {
            sit(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[107])) {
            uit(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[108])) {
            git(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[109])) {
            uqr(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[110])) {
            zdp(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[111])) {
            zpd(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[112])) {
            zps(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[113])) {
            zsp(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[114])) {
            zsp(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[115])) {
            lan(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[116])) {
            sjxx(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[117])) {
            simsi(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[118])) {
            simei(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[119])) {
            endkeyboard();
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[120])) {
            hdfl(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[121])) {
            hdfla(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[122])) {
            hdd(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[123])) {
            hdda(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[124])) {
            hddgl(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[125])) {
            hddg(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[126])) {
            hdds(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[127])) {
            hdduigo(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[128])) {
            swh(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[129])) {
            ufnsui(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[130])) {
            se(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[131])) {
            usg(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[132])) {
            uzd(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[133])) {
            usxq(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[134])) {
            usxh(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[135])) {
            usx(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[136])) {
            ujp(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[137])) {
            bly(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[138])) {
            sqlite(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[139])) {
            sql(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[140])) {
            sqlsele(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[141])) {
            dha(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[142])) {
            dhs(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[143])) {
            dht(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[144])) {
            dhr(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[145])) {
            dhset(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[146])) {
            dhas(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[147])) {
            dhast(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[148])) {
            dh(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[149])) {
            dhon(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[150])) {
            dhb(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[151])) {
            hsas(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[152])) {
            has(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[153])) {
            uxf(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[154])) {
            tts(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[155])) {
            blp(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[156])) {
            otob(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[157])) {
            btoo(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[158])) {
            sot(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[159])) {
            sota(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[160])) {
            loadso(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[161])) {
            loadjar(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[162])) {
            cls(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[163])) {
            javacb(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[164])) {
            javanew(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[165])) {
            java(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[166])) {
            javax(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[167])) {
            javags(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[168])) {
            javass(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[169])) {
            res(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[170])) {
            clssm(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[171])) {
            zj(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[172])) {
            call(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[173])) {
            json(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[174])) {
            utb(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[175])) {
            tws(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[176])) {
            uht(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[177])) {
            cast(str);
            return 0;
        }
        if (Y(str, c.b.a.a.t.t[178])) {
            yul(str);
            return 0;
        }
        if (!Y(str, c.b.a.a.t.t[179])) {
            return -1;
        }
        rps(str);
        return 0;
    }

    private int p(String str) {
        try {
            return o(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    private void q(String str, Object obj, int i2) {
        HashMap<String, Object> hashMap;
        if (str.equals("null")) {
            return;
        }
        if (i2 == 0) {
            hashMap = this.b;
        } else if (i2 == 1) {
            hashMap = c.b.a.a.t.k;
        } else if (i2 != 2) {
            return;
        } else {
            hashMap = c.b.a.a.t.j;
        }
        hashMap.put(str, obj);
    }

    private Object r(String str) {
        HashMap<String, Object> hashMap;
        int indexOf = str.indexOf(46);
        if (indexOf != -1) {
            String trim = str.substring(0, indexOf).trim();
            str = str.substring(indexOf + 1).trim();
            if (trim.equals("ss")) {
                hashMap = c.b.a.a.t.k;
            } else if (trim.equals("sss")) {
                hashMap = c.b.a.a.t.j;
            }
            return hashMap.get(str);
        }
        hashMap = this.b;
        return hashMap.get(str);
    }

    private String u(String str) {
        return str.substring(str.indexOf(123, !str.startsWith("{") ? str.indexOf(10) : 0) + 1, str.lastIndexOf(125));
    }

    private View v(View view, Object obj, String str) {
        String trim = str.trim();
        if ((obj instanceof Integer) && (trim.startsWith("ss.") || trim.startsWith("sss."))) {
            return this.f1817d.findViewById(Integer.parseInt(obj.toString()));
        }
        int indexOf = trim.indexOf(46);
        if (indexOf != -1 && trim.indexOf(34) == -1) {
            String substring = trim.substring(0, indexOf);
            String substring2 = trim.substring(indexOf + 1);
            Object obj2 = f1815i.get(substring);
            if (obj2 == null) {
                return null;
            }
            return view.findViewById(Integer.parseInt(String.valueOf(obj2)) + Integer.parseInt(substring2));
        }
        String valueOf = String.valueOf(obj);
        int indexOf2 = valueOf.indexOf(46);
        if (indexOf2 == -1) {
            return view.findViewById(Integer.parseInt(valueOf));
        }
        String substring3 = valueOf.substring(0, indexOf2);
        String substring4 = valueOf.substring(indexOf2 + 1);
        Object obj3 = f1815i.get(substring3);
        if (obj3 == null) {
            return null;
        }
        return view.findViewById(Integer.parseInt(String.valueOf(obj3)) + Integer.parseInt(substring4));
    }

    private View w(Object obj, String str) {
        String trim = str.trim();
        if ((obj instanceof Integer) && (trim.startsWith("ss.") || trim.startsWith("sss."))) {
            return this.f1817d.findViewById(Integer.parseInt(obj.toString()));
        }
        int indexOf = trim.indexOf(46);
        if (indexOf != -1 && trim.indexOf(34) == -1) {
            String substring = trim.substring(0, indexOf);
            String substring2 = trim.substring(indexOf + 1);
            Object obj2 = f1815i.get(substring);
            if (obj2 == null) {
                return null;
            }
            return this.f1817d.findViewById(Integer.parseInt(String.valueOf(obj2)) + Integer.parseInt(substring2));
        }
        String valueOf = String.valueOf(obj);
        int indexOf2 = valueOf.indexOf(46);
        if (indexOf2 == -1) {
            return this.f1817d.findViewById(Integer.parseInt(valueOf));
        }
        String substring3 = valueOf.substring(0, indexOf2);
        String substring4 = valueOf.substring(indexOf2 + 1);
        Object obj3 = f1815i.get(substring3);
        if (obj3 == null) {
            return null;
        }
        return this.f1817d.findViewById(Integer.parseInt(String.valueOf(obj3)) + Integer.parseInt(substring4));
    }

    private int x() {
        try {
            Display defaultDisplay = this.f1817d.getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            this.f1817d.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics2);
            return displayMetrics.heightPixels - displayMetrics2.heightPixels;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private String[] y() {
        String[] strArr = {"", ""};
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
            String[] split = bufferedReader.readLine().split("\\s+");
            for (int i2 = 2; i2 < split.length; i2++) {
                strArr[0] = strArr[0] + split[i2] + " ";
            }
            strArr[1] = strArr[1] + bufferedReader.readLine().split("\\s+")[2];
            bufferedReader.close();
        } catch (IOException unused) {
        }
        return strArr;
    }

    private int[] z() {
        return new int[]{this.f1817d.getWindowManager().getDefaultDisplay().getWidth(), this.f1817d.getWindowManager().getDefaultDisplay().getHeight()};
    }

    String C(Object obj) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof Byte[])) {
            return obj.toString();
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : (byte[]) obj) {
            stringBuffer.append(' ');
            stringBuffer.append((int) b2);
        }
        return stringBuffer.toString().trim();
    }

    String I(String str) {
        return c.b.a.a.f.o(this.a, str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:84:0x0153, code lost:
    
        if (r2[r11].startsWith("else") != false) goto L210;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0176, code lost:
    
        if (r2[r12].equals("{") != false) goto L210;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0038, code lost:
    
        if (r10.length() == 0) goto L210;
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x018a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int YuGo(java.lang.String r19) {
        /*
            Method dump skipped, instructions count: 442
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_YuCodeX.YuGo(java.lang.String):int");
    }

    public void YuGoX(String str) {
        if (str == null || str.trim().length() == 0) {
            return;
        }
        YuGo(str.replace("&lt;", "<").replace("&gt;", ">"));
    }

    public void addv(String str) {
        String[] T = T(c0(str));
        Object F = F(T[0]);
        View w2 = F instanceof View ? (View) F : w(F, T[0]);
        int length = T.length;
        if (length != 3) {
            if (length == 2) {
                if ((w2 instanceof DrawerLayout) || (w2 instanceof ViewGroup)) {
                    for (String str2 : Z(G(T[1]), '|')) {
                        String trim = str2.trim();
                        if (trim.endsWith(".iyu")) {
                            openRestoreinterface(str2.trim(), (ViewGroup) w2, m(trim.substring(0, trim.length() - 4)), null);
                        }
                    }
                    return;
                }
                return;
            }
            return;
        }
        if ((w2 instanceof ViewPager) || (w2 instanceof VerticalViewPager)) {
            ArrayList arrayList = new ArrayList();
            String[] Z = Z(G(T[1]), '|');
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            for (String str3 : Z) {
                String trim2 = str3.trim();
                if (trim2.endsWith(".iyu")) {
                    LinearLayout linearLayout = new LinearLayout(this.a);
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.setOrientation(1);
                    openRestoreinterface(trim2, linearLayout, m(trim2.substring(0, trim2.length() - 4)), null);
                    arrayList.add(linearLayout);
                }
            }
            new com.iapp.app.u(w2, arrayList);
            dim(T[2].trim(), arrayList);
        }
    }

    public void aslist(String str) {
        ArrayList arrayList;
        String[] T = T(c0(str));
        Object F = F(T[0]);
        if (F instanceof ArrayList) {
            arrayList = (ArrayList) F;
        } else {
            arrayList = new ArrayList();
            dim(T[0].trim(), arrayList);
        }
        if (T.length == 2) {
            arrayList.add(F(T[1]));
        } else if (T.length == 3) {
            arrayList.add(c(G(T[2])), F(T[1]));
        }
    }

    public void bfm(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        String G = G(T[0]);
        MediaPlayer mediaPlayer = new MediaPlayer();
        if (c.b.a.a.f.d(this.a, mediaPlayer, G) && length > 1) {
            dim(T[1].trim(), mediaPlayer);
        }
    }

    public void bfms(String str) {
        String trim;
        Object obj;
        int currentPosition;
        String[] T = T(c0(str));
        Object F = F(T[0]);
        if (F instanceof MediaPlayer) {
            MediaPlayer mediaPlayer = (MediaPlayer) F;
            String G = G(T[1]);
            if (G != null) {
                try {
                    if (G.equals("st")) {
                        mediaPlayer.start();
                    } else if (G.equals("pe")) {
                        mediaPlayer.pause();
                    } else {
                        if (G.equals("sp")) {
                            mediaPlayer.stop();
                            return;
                        }
                        if (!G.equals("re")) {
                            if (G.equals("ip")) {
                                try {
                                    dim(T[2].trim(), Boolean.valueOf(mediaPlayer.isPlaying()));
                                    return;
                                } catch (IllegalStateException unused) {
                                    trim = T[2].trim();
                                    obj = Boolean.FALSE;
                                }
                            } else {
                                if (G.equals("dn")) {
                                    trim = T[2].trim();
                                    currentPosition = mediaPlayer.getDuration();
                                } else {
                                    if (!G.equals("cn")) {
                                        if (G.equals("seekto")) {
                                            mediaPlayer.seekTo(c(G(T[2])));
                                            return;
                                        }
                                        if (G.equals("volume")) {
                                            mediaPlayer.setVolume(c(G(T[2])), c(G(T[3])));
                                            return;
                                        } else {
                                            if (G.equals("sl")) {
                                                mediaPlayer.setLooping(F(T[2]).equals(Boolean.TRUE));
                                                return;
                                            }
                                            try {
                                                mediaPlayer.stop();
                                                mediaPlayer.reset();
                                            } catch (Exception unused2) {
                                            }
                                            c.b.a.a.f.d(this.a, mediaPlayer, G);
                                            return;
                                        }
                                    }
                                    trim = T[2].trim();
                                    currentPosition = mediaPlayer.getCurrentPosition();
                                }
                                obj = Integer.valueOf(currentPosition);
                            }
                            dim(trim, obj);
                            return;
                        }
                        mediaPlayer.release();
                    }
                } catch (IllegalStateException | Exception unused3) {
                }
            }
        }
    }

    public void bfs(String str) {
        Bitmap bitmap;
        int c2;
        String str2;
        String[] T = T(c0(str));
        Object F = F(T[0]);
        if (F instanceof Bitmap) {
            int length = T.length;
            if (length == 2) {
                bitmap = (Bitmap) F;
                c2 = 100;
                str2 = T[1];
            } else {
                if (length != 3) {
                    return;
                }
                bitmap = (Bitmap) F;
                c2 = c(G(T[1]));
                str2 = T[2];
            }
            c.b.a.a.i.h(bitmap, c2, I(G(str2)));
        }
    }

    public void bfv(String str) {
        Intent intent;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            intent = new Intent(this.a, (Class<?>) Videoview.class);
            Bundle bundle = new Bundle();
            bundle.putString("video", G(T[0]));
            bundle.putBoolean("sfhp", F(T[1]).equals(Boolean.TRUE));
            intent.putExtras(bundle);
        } else {
            if (length != 1) {
                return;
            }
            intent = new Intent(this.a, (Class<?>) Videoview.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("video", G(T[0]));
            bundle2.putBoolean("sfhp", false);
            intent.putExtras(bundle2);
        }
        this.a.startActivity(intent);
    }

    public void bfvs(String str) {
        Uri c2;
        String[] T = T(c0(str));
        Object F = F(T[0]);
        VideoView videoView = F instanceof VideoView ? (VideoView) F : (VideoView) w(F, T[0]);
        if (T.length == 2) {
            Object F2 = F(T[1]);
            if (F2 instanceof Uri) {
                c2 = (Uri) F2;
            } else {
                String valueOf = String.valueOf(F2);
                if (!c.b.a.a.p.v(valueOf.toLowerCase())) {
                    videoView.setVideoPath(I(valueOf));
                    return;
                }
                c2 = c.b.a.a.k.c(this.a, valueOf);
            }
            videoView.setVideoURI(c2);
        }
    }

    public void bfvss(String str) {
        String trim;
        int currentPosition;
        Object obj;
        String[] T = T(c0(str));
        Object F = F(T[0]);
        VideoView videoView = F instanceof VideoView ? (VideoView) F : (VideoView) w(F, T[0]);
        int length = T.length;
        if (length == 2) {
            String G = G(T[1]);
            if (G.equals("st")) {
                videoView.requestFocus();
                videoView.start();
                return;
            } else if (G.equals("pe")) {
                videoView.pause();
                return;
            } else {
                if (G.equals("sp")) {
                    videoView.stopPlayback();
                    return;
                }
                return;
            }
        }
        if (length == 3) {
            String G2 = G(T[1]);
            if (G2.equals("seekto")) {
                videoView.seekTo(c(G(T[2])));
                return;
            }
            if (G2.equals("media")) {
                MediaController mediaController = new MediaController(this.a);
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                trim = T[2].trim();
                obj = mediaController;
            } else if (G2.equals("ip")) {
                try {
                    dim(T[2].trim(), Boolean.valueOf(videoView.isPlaying()));
                    return;
                } catch (Exception unused) {
                    trim = T[2].trim();
                    obj = Boolean.FALSE;
                }
            } else {
                if (G2.equals("dn")) {
                    trim = T[2].trim();
                    currentPosition = videoView.getDuration();
                } else {
                    if (!G2.equals("cn")) {
                        return;
                    }
                    trim = T[2].trim();
                    currentPosition = videoView.getCurrentPosition();
                }
                obj = Integer.valueOf(currentPosition);
            }
            dim(trim, obj);
        }
    }

    public void blp(String str) {
        String trim;
        String str2;
        Boolean bool = Boolean.TRUE;
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 5) {
            com.iapp.app.p pVar = com.iapp.app.p.n;
            if (pVar != null) {
                pVar.j(I(G(T[0])), Integer.parseInt(G(T[1])), Integer.parseInt(G(T[2])), Integer.parseInt(G(T[3])), Integer.parseInt(G(T[4])));
                return;
            }
            com.iapp.app.p pVar2 = new com.iapp.app.p(this.f1817d);
            com.iapp.app.p.n = pVar2;
            pVar2.n(I(G(T[0])), Integer.parseInt(G(T[1])), Integer.parseInt(G(T[2])), Integer.parseInt(G(T[3])), Integer.parseInt(G(T[4])));
            return;
        }
        if (length == 2) {
            if (com.iapp.app.p.n == null) {
                dim(T[1].trim(), Boolean.FALSE);
                return;
            }
            String G = G(T[0]);
            if (G.equals("st")) {
                com.iapp.app.p.n.p(true);
                str2 = T[1];
            } else if (G.equals("sp")) {
                com.iapp.app.p.n.p(false);
                str2 = T[1];
            } else {
                if (!G.equals("re")) {
                    if (G.equals("ip")) {
                        trim = T[1].trim();
                        bool = Boolean.valueOf(com.iapp.app.p.n.i());
                        dim(trim, bool);
                    }
                    return;
                }
                com.iapp.app.p.n.o();
                com.iapp.app.p.n = null;
                str2 = T[1];
            }
            trim = str2.trim();
            dim(trim, bool);
        }
    }

    public void bly(String str) {
        String[] T = T(c0(str));
        if (T.length == 2) {
            Object F = F(T[1]);
            if (F.equals("sp")) {
                Object F2 = F(T[0]);
                if (F2 instanceof MediaRecorder) {
                    MediaRecorder mediaRecorder = (MediaRecorder) F2;
                    try {
                        mediaRecorder.stop();
                        mediaRecorder.release();
                    } catch (IllegalStateException e2) {
                        e2.printStackTrace();
                    }
                    dim(T[0].trim(), null);
                    return;
                }
                return;
            }
            MediaRecorder mediaRecorder2 = new MediaRecorder();
            mediaRecorder2.setAudioSource(1);
            mediaRecorder2.setOutputFormat(0);
            String I = I(String.valueOf(F));
            c.b.a.a.d.b(I, false);
            mediaRecorder2.setOutputFile(I);
            mediaRecorder2.setAudioEncoder(0);
            try {
                mediaRecorder2.prepare();
                mediaRecorder2.start();
                dim(T[0].trim(), mediaRecorder2);
            } catch (Exception e3) {
                e3.printStackTrace();
                dim(T[0].trim(), null);
            }
        }
    }

    public void btoo(String str) {
        byte[] bArr;
        String[] T = T(c0(str));
        int length = T.length;
        byte[] bArr2 = null;
        if (length == 2) {
            Object F = F(T[0]);
            if (F instanceof byte[]) {
                bArr2 = (byte[]) F;
            } else {
                try {
                    bArr2 = n(F.toString().trim(), ' ');
                } catch (Exception unused) {
                }
            }
            if (bArr2 == null) {
                return;
            }
            c.b.a.a.d.j(I(G(T[1])), bArr2);
            return;
        }
        if (length == 3) {
            Object F2 = F(T[1]);
            if (F2 instanceof byte[]) {
                bArr = (byte[]) F2;
            } else {
                try {
                    bArr = n(F2.toString().trim(), ' ');
                } catch (Exception unused2) {
                    bArr = null;
                }
            }
            if (bArr == null) {
                return;
            }
            Object F3 = F(T[0]);
            if (F3 == null) {
                dim(T[2].trim(), new String(bArr));
                return;
            }
            try {
                dim(T[2].trim(), new String(bArr, F3.toString()));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                dim(T[2].trim(), null);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void call(String str) {
        String trim;
        Object b2;
        String[] T = T(c0(str));
        int length = T.length;
        Object F = F(T[1]);
        int i2 = 3;
        if (F.equals("myu")) {
            int i3 = length - 3;
            if (i3 >= 0) {
                String G = G(T[2]);
                Object obj = c.b.a.a.t.l.get(G + i3);
                int indexOf = G.indexOf(46);
                if (obj == null) {
                    String substring = G.substring(0, indexOf + 1);
                    Iterator<String> it = c.b.a.a.t.l.keySet().iterator();
                    while (it.hasNext()) {
                        if (it.next().toString().startsWith(substring)) {
                            return;
                        }
                    }
                    X(substring + "myu");
                    obj = c.b.a.a.t.l.get(G + i3);
                }
                String obj2 = obj.toString();
                int indexOf2 = obj2.indexOf(10);
                String[] T2 = T(c0(obj2.substring(0, indexOf2)));
                if (i3 != T2.length) {
                    return;
                }
                while (i2 < length) {
                    dim(T2[i2 - 3].trim(), F(T[i2]));
                    i2++;
                }
                mian.c(G.substring(0, indexOf), G.substring(indexOf + 1) + obj2.substring(indexOf2).trim(), this);
                return;
            }
            return;
        }
        String str2 = "i";
        if (F.equals("mjava")) {
            com.iapp.app.j jVar = new com.iapp.app.j(this.a);
            try {
                jVar.set("activity", this.a);
                jVar.set("i", new Aid_javaCode(this.a, this.f1817d, jVar));
            } catch (EvalError e2) {
                e2.printStackTrace();
            }
            String G2 = G(T[2]);
            int indexOf3 = G2.indexOf(46);
            if (jVar.e(G2.substring(0, indexOf3) + ".mjava")) {
                if (length == 3) {
                    trim = T[0].trim();
                    b2 = jVar.a(G2.substring(indexOf3 + 1));
                } else {
                    if (length <= 3) {
                        return;
                    }
                    Object[] objArr = new Object[length - 3];
                    int i4 = 0;
                    while (i2 < length) {
                        objArr[i4] = F(T[i2]);
                        i4++;
                        i2++;
                    }
                    trim = T[0].trim();
                    b2 = jVar.b(G2.substring(indexOf3 + 1), objArr);
                }
                dim(trim, b2);
                return;
            }
            return;
        }
        String str3 = "_a";
        if (F.equals("mlua")) {
            String G3 = G(T[2]);
            long id = Thread.currentThread().getId();
            String str4 = "";
            String str5 = str4;
            int i5 = 3;
            int i6 = 0;
            while (i5 < length) {
                int i7 = length;
                int i8 = i6 + 1;
                String str6 = "$_Call_SsS_" + id + str3 + i8;
                c.b.a.a.t.j.put(str6, F(T[i5]));
                str5 = str5 + "local a" + i8 + " = i:sss(\"" + str6 + "\")\n";
                str4 = str4 + ",a" + i8;
                i5++;
                i6 = i8;
                length = i7;
                str3 = str3;
                str2 = str2;
            }
            String str7 = str2;
            if (str4.length() > 0) {
                str4 = str4.substring(1);
            }
            String str8 = "$_Call_SsS_" + id + "_return";
            String str9 = "require 'import'\nrequire '" + G3.substring(0, G3.indexOf(46)) + "'\n" + str5 + "\nlocal returns = " + G3.substring(G3.indexOf(46) + 1) + "(" + str4 + ")\ni:sss(\"" + str8 + "\", returns)";
            com.iapp.app.d dVar = new com.iapp.app.d(this.a);
            dVar.l("activity", this.a);
            dVar.l(str7, new Aid_luaCode(this.a, this.f1817d, dVar));
            dVar.k();
            try {
                dVar.h(str9);
                dim(T[0].trim(), c.b.a.a.t.j.get(str8));
                return;
            } catch (LuaException e3) {
                e3.printStackTrace();
                c.b.a.a.t.P2(this.a, "LuaErr：\n" + e3.getMessage());
                return;
            }
        }
        String str10 = "_a";
        if (F.equals("mjs")) {
            String G4 = G(T[2]);
            long id2 = Thread.currentThread().getId();
            String str11 = "";
            String str12 = str11;
            int i9 = 0;
            int i10 = 3;
            while (i10 < length) {
                i9++;
                StringBuilder sb = new StringBuilder();
                sb.append("$_Call_SsS_");
                sb.append(id2);
                String str13 = str10;
                sb.append(str13);
                sb.append(i9);
                String sb2 = sb.toString();
                c.b.a.a.t.j.put(sb2, F(T[i10]));
                str12 = str12 + "var a" + i9 + " = I.sss(\"" + sb2 + "\");\n";
                str11 = str11 + ",a" + i9;
                i10++;
                id2 = id2;
                str10 = str13;
            }
            if (str11.length() > 0) {
                str11 = str11.substring(1);
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("<html><head><script type='text/javascript'>");
            sb3.append(c.b.a.a.f.e(this.a, "import.mjs"));
            sb3.append("\n");
            sb3.append(c.b.a.a.f.a(this.a, G4.substring(0, G4.indexOf(46)) + ".mjs"));
            sb3.append("\n");
            sb3.append(str12);
            sb3.append("\nvar returns = ");
            sb3.append(G4.substring(G4.indexOf(46) + 1));
            sb3.append("(");
            sb3.append(str11);
            sb3.append(");</script></head></html>");
            String sb4 = sb3.toString();
            android.webkit.WebView webView = new android.webkit.WebView(this.a);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setAppCachePath(this.a.getApplicationContext().getDir("cache", 0).getPath());
            webView.getSettings().setAppCacheMaxSize(8388608L);
            webView.getSettings().setDatabaseEnabled(true);
            webView.getSettings().setDatabasePath(this.a.getApplicationContext().getDir("database", 0).getPath());
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setGeolocationEnabled(true);
            webView.getSettings().setLightTouchEnabled(true);
            webView.getSettings().setCacheMode(-1);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.setWebChromeClient(new h0());
            if (Build.VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
            }
            webView.addJavascriptInterface(new Aid_jsCode(this.a, this.f1817d, webView, 0), "I");
            com.iapp.app.c.d(webView, sb4);
        }
    }

    public void cast(String str) {
        Class<?> a2;
        String trim;
        String str2;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            Object F = F(T[0]);
            a2 = F instanceof Class ? (Class) F : F instanceof String ? c.b.a.a.b.a(String.valueOf(F)) : F.getClass();
            trim = T[1].trim();
            str2 = T[1];
        } else {
            if (length != 3) {
                return;
            }
            Object F2 = F(T[0]);
            a2 = F2 instanceof Class ? (Class) F2 : F2 instanceof String ? c.b.a.a.b.a(String.valueOf(F2)) : F2.getClass();
            trim = T[2].trim();
            str2 = T[1];
        }
        dim(trim, a2.cast(F(str2)));
    }

    public void clear_s_dim() {
        this.b.clear();
    }

    public void cls(String str) {
        String str2;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            dim(T[1].trim(), c.b.a.a.b.b(G(T[0])));
            return;
        }
        if (length == 3) {
            Object F = F(T[0]);
            if (F instanceof ClassLoader) {
                try {
                    dim(T[2].trim(), ((ClassLoader) F).loadClass(G(T[1])));
                    return;
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                    str2 = T[2];
                }
            } else {
                str2 = T[2];
            }
            dim(str2.trim(), null);
        }
    }

    public void clssm(String str) {
        String[] T = T(c0(str));
        if (T.length == 3) {
            Object F = F(T[0]);
            if (F instanceof Class) {
                Object F2 = F(T[1]);
                if (F2.equals("init")) {
                    dim(T[2].trim(), ((Class) F).getDeclaredConstructors());
                } else if (F2.equals("method")) {
                    dim(T[2].trim(), ((Class) F).getDeclaredMethods());
                } else if (F2.equals("field")) {
                    dim(T[2].trim(), ((Class) F).getDeclaredFields());
                }
            }
        }
    }

    public void dh(String str) {
        String trim;
        boolean e2;
        Object clone;
        Boolean bool = Boolean.TRUE;
        String[] T = T(c0(str));
        Object F = F(T[0]);
        if (F instanceof Animation) {
            Animation animation = (Animation) F;
            Object F2 = F(T[1]);
            if (F2.equals("cancel")) {
                animation.cancel();
                return;
            }
            if (F2.equals("reset")) {
                animation.reset();
                return;
            }
            if (F2.equals("start")) {
                animation.start();
                return;
            }
            if (F2.equals("duration")) {
                animation.setDuration(Long.parseLong(G(T[2])));
                return;
            }
            if (F2.equals("enabled")) {
                animation.setFillEnabled(F(T[2]).equals(bool));
                return;
            }
            if (F2.equals("after")) {
                animation.setFillAfter(F(T[2]).equals(bool));
                return;
            }
            if (F2.equals("before")) {
                animation.setFillBefore(F(T[2]).equals(bool));
                return;
            } else if (F2.equals("delay")) {
                animation.setStartOffset(Long.parseLong(G(T[2])));
                return;
            } else {
                if (F2.equals("repeat")) {
                    animation.setRepeatCount(c(G(T[2])));
                    return;
                }
                return;
            }
        }
        if (F instanceof c.d.a.a) {
            c.d.a.a aVar = (c.d.a.a) F;
            Object F3 = F(T[1]);
            if (F3.equals("cancel")) {
                aVar.b();
                return;
            }
            if (F3.equals("clone")) {
                trim = T[2].trim();
                clone = aVar.clone();
            } else {
                if (F3.equals("start")) {
                    aVar.j();
                    return;
                }
                if (!F3.equals("running")) {
                    if (F3.equals("duration")) {
                        aVar.g(Long.parseLong(G(T[2])));
                        return;
                    }
                    if (F3.equals("delay")) {
                        aVar.h(Long.parseLong(G(T[2])));
                        return;
                    } else {
                        if (F3.equals("target")) {
                            Object F4 = F(T[2]);
                            aVar.i(F4 instanceof View ? (View) F4 : w(F4, T[2]));
                            return;
                        }
                        return;
                    }
                }
                trim = T[2].trim();
                e2 = aVar.e();
                clone = Boolean.valueOf(e2);
            }
        } else {
            if (F instanceof AnimationSet) {
                AnimationSet animationSet = (AnimationSet) F;
                Object F5 = F(T[1]);
                if (F5.equals("cancel")) {
                    animationSet.cancel();
                    return;
                }
                if (F5.equals("reset")) {
                    animationSet.reset();
                    return;
                }
                if (F5.equals("start")) {
                    animationSet.start();
                    return;
                }
                if (F5.equals("duration")) {
                    animationSet.setDuration(Long.parseLong(G(T[2])));
                    return;
                }
                if (F5.equals("enabled")) {
                    animationSet.setFillEnabled(F(T[2]).equals(bool));
                    return;
                }
                if (F5.equals("after")) {
                    animationSet.setFillAfter(F(T[2]).equals(bool));
                    return;
                }
                if (F5.equals("before")) {
                    animationSet.setFillBefore(F(T[2]).equals(bool));
                    return;
                }
                if (F5.equals("delay")) {
                    animationSet.setStartOffset(Long.parseLong(G(T[2])));
                    return;
                } else if (F5.equals("repeat")) {
                    animationSet.setRepeatCount(c(G(T[2])));
                    return;
                } else {
                    if (F5.equals("add")) {
                        animationSet.addAnimation((Animation) F(T[2]));
                        return;
                    }
                    return;
                }
            }
            if (!(F instanceof c.d.a.c)) {
                return;
            }
            c.d.a.c cVar = (c.d.a.c) F;
            Object F6 = F(T[1]);
            if (F6.equals("cancel")) {
                cVar.b();
                return;
            }
            if (F6.equals("clone")) {
                trim = T[2].trim();
                clone = cVar.clone();
            } else {
                if (F6.equals("start")) {
                    cVar.j();
                    return;
                }
                if (!F6.equals("running")) {
                    if (F6.equals("duration")) {
                        cVar.u(Long.parseLong(G(T[2])));
                        return;
                    }
                    if (F6.equals("delay")) {
                        cVar.h(Long.parseLong(G(T[2])));
                        return;
                    } else {
                        if (F6.equals("target")) {
                            Object F7 = F(T[2]);
                            cVar.i(F7 instanceof View ? (View) F7 : w(F7, T[2]));
                            return;
                        }
                        return;
                    }
                }
                trim = T[2].trim();
                e2 = cVar.e();
                clone = Boolean.valueOf(e2);
            }
        }
        dim(trim, clone);
    }

    public void dha(String str) {
        String[] T = T(c0(str));
        if (T.length == 3) {
            String trim = T[0].trim();
            Object F = F(T[1]);
            Boolean bool = Boolean.TRUE;
            dim(trim, new AlphaAnimation(F.equals(bool) ? 1.0f : 0.0f, F(T[2]).equals(bool) ? 1.0f : 0.0f));
        }
    }

    public void dhas(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        dim(T[0].trim(), null);
        Object F = F(T[1]);
        View w2 = F instanceof View ? (View) F : w(F, T[1]);
        float[] fArr = new float[length - 3];
        int i2 = 0;
        for (int i3 = 3; i3 < length; i3++) {
            fArr[i2] = Float.parseFloat(G(T[i3]));
            i2++;
        }
        dim(T[0].trim(), c.d.a.i.K(w2, G(T[2]), fArr));
    }

    public void dhast(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        dim(T[0].trim(), null);
        c.d.a.c cVar = new c.d.a.c();
        c.d.a.a[] aVarArr = new c.d.a.a[length - 2];
        int i2 = 0;
        for (int i3 = 2; i3 < length; i3++) {
            Object F = F(T[i3]);
            if (F instanceof c.d.a.a) {
                aVarArr[i2] = (c.d.a.a) F;
                i2++;
            }
        }
        Object F2 = F(T[1]);
        if (F2.equals("sequen")) {
            cVar.s(aVarArr);
        } else if (F2.equals("together")) {
            cVar.t(aVarArr);
        }
        dim(T[0].trim(), cVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void dhb(String str) {
        String trim;
        Boolean bool;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            Object F = F(T[1]);
            if (!(F instanceof Boolean)) {
                if (F.equals("start")) {
                    Object F2 = F(T[0]);
                    if (F2 instanceof AnimationDrawable) {
                        ((AnimationDrawable) F2).start();
                        return;
                    }
                    return;
                }
                if (F.equals("stop")) {
                    Object F3 = F(T[0]);
                    if (F3 instanceof AnimationDrawable) {
                        ((AnimationDrawable) F3).stop();
                        return;
                    }
                    return;
                }
                return;
            }
            AnimationDrawable animationDrawable = new AnimationDrawable();
            animationDrawable.setOneShot(!F(T[1]).equals(Boolean.TRUE));
            trim = T[0].trim();
            bool = animationDrawable;
        } else {
            if (length != 3) {
                return;
            }
            Object F4 = F(T[0]);
            if (!(F4 instanceof AnimationDrawable)) {
                return;
            }
            AnimationDrawable animationDrawable2 = (AnimationDrawable) F4;
            Object F5 = F(T[1]);
            if (!F5.equals("running")) {
                animationDrawable2.addFrame(F5 instanceof Drawable ? (Drawable) F5 : F5 instanceof Bitmap ? new BitmapDrawable((Bitmap) F5) : com.iapp.app.i.m(String.valueOf(F5), this.a), c(G(T[2])));
                return;
            } else {
                trim = T[2].trim();
                bool = Boolean.valueOf(animationDrawable2.isRunning());
            }
        }
        dim(trim, bool);
    }

    public void dhon(String str) {
        String[] T = T(d0(str));
        ArrayList arrayList = new ArrayList();
        String[] Z = Z(str, '\n');
        String str2 = "";
        int i2 = 0;
        for (int i3 = 0; i3 < Z.length; i3++) {
            if (Z[i3].equals("{")) {
                i2++;
            } else if (Z[i3].equals("}")) {
                i2--;
            } else if (i2 == 0) {
                if (Z[i3].startsWith("else")) {
                    str2 = Z[i3] + "\n";
                }
            }
            str2 = str2 + Z[i3] + "\n";
            if (i2 == 0) {
                arrayList.add(u(str2));
            }
        }
        Object F = F(T[0]);
        if (F instanceof Animation) {
            new com.iapp.app.l(this, (Animation) F, arrayList.toArray());
        } else if (F instanceof c.d.a.a) {
            new com.iapp.app.l(this, (c.d.a.a) F, arrayList.toArray());
        }
    }

    public void dhr(String str) {
        String trim;
        RotateAnimation rotateAnimation;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 3) {
            trim = T[0].trim();
            rotateAnimation = new RotateAnimation(Float.parseFloat(G(T[1])), Float.parseFloat(G(T[2])));
        } else {
            if (length != 7) {
                return;
            }
            trim = T[0].trim();
            rotateAnimation = new RotateAnimation(Float.parseFloat(G(T[1])), Float.parseFloat(G(T[2])), c(G(T[3])), Float.parseFloat(G(T[4])), c(G(T[5])), Float.parseFloat(G(T[6])));
        }
        dim(trim, rotateAnimation);
    }

    public void dhs(String str) {
        String trim;
        ScaleAnimation scaleAnimation;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 5) {
            trim = T[0].trim();
            scaleAnimation = new ScaleAnimation(Float.parseFloat(G(T[1])), Float.parseFloat(G(T[2])), Float.parseFloat(G(T[3])), Float.parseFloat(G(T[4])));
        } else {
            if (length != 9) {
                return;
            }
            trim = T[0].trim();
            scaleAnimation = new ScaleAnimation(Float.parseFloat(G(T[1])), Float.parseFloat(G(T[2])), Float.parseFloat(G(T[3])), Float.parseFloat(G(T[4])), c(G(T[5])), Float.parseFloat(G(T[6])), c(G(T[7])), Float.parseFloat(G(T[8])));
        }
        dim(trim, scaleAnimation);
    }

    public void dhset(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        AnimationSet animationSet = new AnimationSet(F(T[1]).equals(Boolean.TRUE));
        for (int i2 = 2; i2 < length; i2++) {
            Object F = F(T[i2]);
            if (F instanceof Animation) {
                animationSet.addAnimation((Animation) F);
            }
        }
        dim(T[0].trim(), animationSet);
    }

    public void dht(String str) {
        String[] T = T(c0(str));
        if (T.length == 5) {
            dim(T[0].trim(), new TranslateAnimation(Float.parseFloat(G(T[1])), Float.parseFloat(G(T[2])), Float.parseFloat(G(T[3])), Float.parseFloat(G(T[4]))));
        }
    }

    public void dim(String str, Object obj) {
        int indexOf = str.indexOf(46);
        if (indexOf != -1) {
            String substring = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
            if (substring.equals("ss")) {
                q(str, obj, 1);
                return;
            } else if (substring.equals("sss")) {
                q(str, obj, 2);
                return;
            }
        }
        this.b.put(str, obj);
    }

    public void dim_y(String str) {
        String substring = str.substring(str.indexOf(40) + 1, str.lastIndexOf(41));
        int indexOf = substring.indexOf(44);
        String trim = substring.substring(0, indexOf).trim();
        String replace = trim.replace('+', ',').replace('-', ',').replace('*', ',').replace('/', ',').replace('(', ',').replace(')', ',').replace('%', ',');
        String str2 = " " + trim.replace("+", " + ").replace("-", " - ").replace("*", " * ").replace("/", " / ").replace("(", " ( ").replace(")", " ) ").replace("%", " % ") + " ";
        for (String str3 : Z(replace, ',')) {
            str2 = str2.replace(" " + str3.trim() + " ", G(str3));
        }
        dim(substring.substring(indexOf + 1).trim(), String.valueOf((long) c.f.a.a.a(str2)));
    }

    public void dim_y2(String str) {
        String substring = str.substring(str.indexOf(40) + 1, str.lastIndexOf(41));
        int indexOf = substring.indexOf(44);
        String trim = substring.substring(0, indexOf).trim();
        String replace = trim.replace('+', ',').replace('-', ',').replace('*', ',').replace('/', ',').replace('(', ',').replace(')', ',').replace('%', ',');
        String str2 = " " + trim.replace("+", " + ").replace("-", " - ").replace("*", " * ").replace("/", " / ").replace("(", " ( ").replace(")", " ) ").replace("%", " % ") + " ";
        for (String str3 : Z(replace, ',')) {
            str2 = str2.replace(" " + str3.trim() + " ", G(str3));
        }
        dim(substring.substring(indexOf + 1).trim(), String.format("%.2f", Double.valueOf(c.f.a.a.a(str2))));
    }

    public void dim_yn(String str) {
        String substring = str.substring(str.indexOf(40) + 1, str.lastIndexOf(41));
        int indexOf = substring.indexOf(44);
        String trim = substring.substring(0, indexOf).trim();
        String replace = trim.replace('+', ',').replace('-', ',').replace('*', ',').replace('/', ',').replace('(', ',').replace(')', ',').replace('%', ',');
        String str2 = " " + trim.replace("+", " + ").replace("-", " - ").replace("*", " * ").replace("/", " / ").replace("(", " ( ").replace(")", " ) ").replace("%", " % ") + " ";
        for (String str3 : Z(replace, ',')) {
            str2 = str2.replace(" " + str3.trim() + " ", G(str3));
        }
        dim(substring.substring(indexOf + 1).trim(), String.valueOf(c.f.a.a.a(str2)));
    }

    public Object dimget2(String str) {
        HashMap<String, Object> hashMap;
        int indexOf = str.indexOf(46);
        if (indexOf != -1) {
            String trim = str.substring(0, indexOf).trim();
            str = str.substring(indexOf + 1).trim();
            if (trim.equals("ss")) {
                hashMap = c.b.a.a.t.k;
            } else if (trim.equals("sss")) {
                hashMap = c.b.a.a.t.j;
            }
            return hashMap.get(str);
        }
        hashMap = this.b;
        return hashMap.get(str);
    }

    public boolean dims(String str, int i2) {
        Object r2;
        Object valueOf;
        int indexOf = str.indexOf(61);
        if (indexOf == -1) {
            String trim = str.substring(i2 + 2).trim();
            if (O(trim) || trim.indexOf(32) != -1) {
                return false;
            }
            q(trim, null, i2);
            return true;
        }
        String trim2 = str.substring(i2 + 2, indexOf).trim();
        if (O(trim2) || trim2.indexOf(32) != -1) {
            return false;
        }
        String trim3 = str.substring(indexOf + 1).trim();
        if (!trim3.contains("\"")) {
            if (trim3.equals("null")) {
                q(trim2, null, i2);
                return true;
            }
            if (trim3.equals("true")) {
                valueOf = Boolean.TRUE;
            } else if (trim3.equals("false")) {
                valueOf = Boolean.FALSE;
            } else if (!O(trim3)) {
                r2 = r(trim3);
            } else {
                if (!trim3.contains(".")) {
                    if (trim2.startsWith("00")) {
                        q(trim2, trim3, i2);
                    }
                    q(trim2, trim3.length() < 10 ? Integer.valueOf(Integer.parseInt(trim3)) : Long.valueOf(Long.parseLong(trim3)), i2);
                    return true;
                }
                if (trim3.startsWith("00")) {
                    q(trim2, trim3, i2);
                }
                valueOf = Double.valueOf(Double.parseDouble(trim3));
            }
            q(trim2, valueOf, i2);
            return true;
        }
        if (!trim3.startsWith("\"") || !trim3.endsWith("\"")) {
            return false;
        }
        r2 = c.b.a.a.t.V(c.b.a.a.t.a(trim3.substring(1, trim3.length() - 1)));
        q(trim2, r2, i2);
        return true;
    }

    public void dslist(String str) {
        String[] T = T(c0(str));
        if (T.length == 2) {
            Object F = F(T[1]);
            if (!(F instanceof Integer)) {
                ((ArrayList) F(T[0])).remove(F);
                return;
            }
            int parseInt = Integer.parseInt(String.valueOf(F));
            if (parseInt == -1) {
                ((ArrayList) F(T[0])).clear();
            } else {
                ((ArrayList) F(T[0])).remove(parseInt);
            }
        }
    }

    public void end() {
        this.f1817d.finish();
    }

    public void endkeyboard() {
        View peekDecorView = this.f1817d.getWindow().peekDecorView();
        if (peekDecorView != null) {
            ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }

    public void ends() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addFlags(270532608);
        this.a.startActivity(intent);
    }

    public void endutw() {
        AlertDialog alertDialog = c.b.a.a.t.n;
        if (alertDialog != null) {
            alertDialog.dismiss();
            c.b.a.a.t.n = null;
        }
    }

    public int f(String str) {
        StringBuilder sb;
        String str2;
        if (str.indexOf(40) == 0) {
            str = "f" + str;
        }
        String[] Z = Z(str, '\n');
        String str3 = "";
        int i2 = 0;
        for (int i3 = 0; i3 < Z.length; i3++) {
            if (Z[i3].equals("{")) {
                i2++;
            } else if (Z[i3].equals("}")) {
                i2--;
            } else if (i2 == 0) {
                if (Z[i3].startsWith("f(")) {
                    sb = new StringBuilder();
                    str2 = Z[i3];
                } else {
                    if (Z[i3].startsWith("else")) {
                        String trim = Z[i3].substring(4).trim();
                        if (trim.startsWith("f(")) {
                            str3 = trim + "\n";
                        } else {
                            sb = new StringBuilder();
                            str2 = Z[i3];
                        }
                    }
                }
                sb.append(str2);
                sb.append("\n");
                str3 = sb.toString();
            }
            str3 = str3 + Z[i3] + "\n";
            if (i2 != 0) {
                continue;
            } else {
                if (!str3.startsWith("f(")) {
                    int YuGo = YuGo(u(str3));
                    if (YuGo == 2) {
                        return 2;
                    }
                    return YuGo == 1 ? 1 : 0;
                }
                if (b(d0(str3))) {
                    int YuGo2 = YuGo(u(str3));
                    if (YuGo2 == 2) {
                        return 2;
                    }
                    return YuGo2 == 1 ? 1 : 0;
                }
            }
        }
        return 0;
    }

    public void fc(String str) {
        String trim;
        boolean f2;
        String[] T = T(c0(str));
        if (T.length == 3) {
            trim = T[2].trim();
            f2 = c.b.a.a.f.f(this.a, G(T[0]), G(T[1]), true);
        } else {
            if (T.length != 4) {
                return;
            }
            trim = T[3].trim();
            f2 = c.b.a.a.f.f(this.a, G(T[0]), G(T[1]), F(T[2]).equals(Boolean.TRUE));
        }
        dim(trim, Boolean.valueOf(f2));
    }

    public void fd(String str) {
        String trim;
        Boolean bool;
        String[] T = T(c0(str));
        File file = new File(I(G(T[0])));
        if (file.exists()) {
            trim = T[1].trim();
            bool = Boolean.valueOf(file.delete());
        } else {
            trim = T[1].trim();
            bool = Boolean.FALSE;
        }
        dim(trim, bool);
    }

    public void fdir(String str) {
        String[] T = T(c0(str));
        c.b.a.a.d.r(this.f1817d);
        if (T.length == 1) {
            dim(T[0].trim(), c.b.a.a.d.m(this.a));
        } else if (T.length == 2) {
            dim(T[1].trim(), I(G(T[0])));
        }
    }

    public void fe(String str) {
        String[] T = T(c0(str));
        dim(T[1].trim(), Boolean.valueOf(c.b.a.a.f.g(this.a, G(T[0]))));
    }

    public void fi(String str) {
        String[] T = T(c0(str));
        dim(T[1].trim(), Boolean.valueOf(new File(I(G(T[0]))).isDirectory()));
    }

    public void fj(String str) {
        String str2;
        Boolean bool = Boolean.TRUE;
        Boolean bool2 = Boolean.FALSE;
        String[] T = T(c0(str));
        if (T.length == 3) {
            String I = I(G(T[1]));
            c.b.a.a.d.b(I, false);
            try {
                c.b.a.a.c.c(I(G(T[0])), I, true);
                dim(T[2].trim(), bool);
                return;
            } catch (Exception unused) {
                str2 = T[2];
            }
        } else {
            if (T.length != 4) {
                return;
            }
            String I2 = I(G(T[1]));
            c.b.a.a.d.b(I2, false);
            try {
                c.b.a.a.c.c(I(G(T[0])), I2, F(T[2]).equals(bool));
                dim(T[3].trim(), bool);
                return;
            } catch (Exception unused2) {
                str2 = T[3];
            }
        }
        dim(str2.trim(), bool2);
    }

    public void fl(String str) {
        int i2;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            dim(T[1].trim(), c.b.a.a.f.h(this.a, G(T[0])));
            return;
        }
        if (length == 3) {
            File file = new File(I(G(T[0])));
            if (!file.exists()) {
                dim(T[2].trim(), null);
                return;
            }
            ArrayList arrayList = new ArrayList();
            boolean equals = F(T[1]).equals(Boolean.TRUE);
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                dim(T[2].trim(), null);
                return;
            }
            int length2 = listFiles.length;
            while (i2 < length2) {
                File file2 = listFiles[i2];
                if (equals) {
                    i2 = file2.isDirectory() ? 0 : i2 + 1;
                    arrayList.add(file2.getName());
                } else {
                    if (!file2.isFile()) {
                    }
                    arrayList.add(file2.getName());
                }
            }
            dim(T[2].trim(), arrayList.toArray());
        }
    }

    public void fo(String str) {
        String I = I(G(T(c0(str))[0]));
        File file = new File(I);
        if (file.exists()) {
            if (I.toLowerCase().endsWith(".apk")) {
                c.b.a.a.d.d(this.a, I);
            } else {
                try {
                    P(file);
                } catch (Exception unused) {
                }
            }
        }
    }

    public int for_y(String str) {
        String d02 = d0(str);
        int indexOf = d02.indexOf(59);
        String trim = d02.substring(0, indexOf).trim();
        String trim2 = d02.substring(indexOf + 1).trim();
        Object F = F(trim);
        Object F2 = F(trim2);
        if (F2 instanceof String[]) {
            String u2 = u(str);
            for (String str2 : (String[]) F2) {
                dim(trim, str2);
                int YuGo = YuGo(u2);
                if (YuGo == 2) {
                    return 2;
                }
                if (YuGo == 1) {
                    return 0;
                }
            }
        } else if (F2 instanceof Object[]) {
            String u3 = u(str);
            for (Object obj : (Object[]) F2) {
                dim(trim, obj);
                int YuGo2 = YuGo(u3);
                if (YuGo2 == 2) {
                    return 2;
                }
                if (YuGo2 == 1) {
                    return 0;
                }
            }
        } else if (F2 instanceof ArrayList) {
            String u4 = u(str);
            Iterator it = ((ArrayList) F2).iterator();
            while (it.hasNext()) {
                dim(trim, it.next());
                int YuGo3 = YuGo(u4);
                if (YuGo3 == 2) {
                    return 2;
                }
                if (YuGo3 == 1) {
                    return 0;
                }
            }
        } else {
            int c2 = c(F2.toString());
            String u5 = u(str);
            for (int c3 = c(F.toString()); c3 <= c2; c3++) {
                int YuGo4 = YuGo(u5);
                if (YuGo4 == 2) {
                    return 2;
                }
                if (YuGo4 == 1) {
                    return 0;
                }
            }
        }
        return 0;
    }

    public void fr(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        String G = G(T[0]);
        if (length == 2) {
            dim(T[1].trim(), c.b.a.a.f.i(this.a, G));
        } else if (length == 3) {
            dim(T[2].trim(), c.b.a.a.f.j(this.a, G, G(T[1])));
        }
    }

    public void fs(String str) {
        String[] T = T(c0(str));
        dim(T[1].trim(), Long.valueOf(c.b.a.a.f.k(this.a, G(T[0]))));
    }

    public void ft(String str) {
        String[] T = T(c0(str));
        if (T.length == 3) {
            File file = new File(I(G(T[0])));
            if (!file.exists()) {
                dim(T[2].trim(), Boolean.FALSE);
                return;
            }
            String I = I(G(T[1]));
            c.b.a.a.d.b(I, false);
            dim(T[2].trim(), Boolean.valueOf(file.renameTo(new File(I))));
        }
    }

    public void ftz(String str) {
        String[] T = T(d0(str));
        if (T.length == 4) {
            c.b.a.a.r.b(this.a, G(T[0]), G(T[1]), G(T[2]), F(T[3]), new Intent().setClass(this.a, logoActivity.class).putExtra("command", u(str)));
        }
    }

    public void function(String str) {
        String lowerCase = str.substring(2, str.indexOf(40)).trim().toLowerCase();
        String[] T = T(c0(str));
        int length = T.length;
        Object obj = c.b.a.a.t.l.get(lowerCase + length);
        if (obj == null) {
            String trim = str.substring(2, str.indexOf(46) + 1).trim();
            Iterator<String> it = c.b.a.a.t.l.keySet().iterator();
            while (it.hasNext()) {
                if (it.next().toString().startsWith(trim)) {
                    return;
                }
            }
            X(trim + "myu");
            obj = c.b.a.a.t.l.get(lowerCase + length);
        }
        String obj2 = obj.toString();
        int indexOf = obj2.indexOf(10);
        String[] T2 = T(c0(obj2.substring(0, indexOf)));
        if (length != T2.length) {
            return;
        }
        Aid_YuCodeX aid_YuCodeX = this.g;
        if (aid_YuCodeX == null) {
            this.g = new Aid_YuCodeX(this.a, this.f1817d);
        } else {
            aid_YuCodeX.clear_s_dim();
        }
        for (int i2 = 0; i2 < length; i2++) {
            this.g.dim(T2[i2].trim(), F(T[i2]));
        }
        int indexOf2 = lowerCase.indexOf(46);
        mian.c(lowerCase.substring(0, indexOf2), lowerCase.substring(indexOf2 + 1) + obj2.substring(indexOf).trim(), this.g);
    }

    public void function2(String str) {
        String lowerCase = str.substring(0, str.indexOf(40)).trim().toLowerCase();
        String[] T = T(c0(str));
        int length = T.length;
        Object obj = c.b.a.a.t.l.get(lowerCase + length);
        if (obj == null) {
            String trim = str.substring(0, str.indexOf(46) + 1).trim();
            Iterator<String> it = c.b.a.a.t.l.keySet().iterator();
            while (it.hasNext()) {
                if (it.next().toString().startsWith(trim)) {
                    return;
                }
            }
            X(trim + "myu");
            obj = c.b.a.a.t.l.get(lowerCase + length);
        }
        String obj2 = obj.toString();
        int indexOf = obj2.indexOf(10);
        String[] T2 = T(c0(obj2.substring(0, indexOf)));
        if (length != T2.length) {
            return;
        }
        Aid_YuCodeX aid_YuCodeX = this.g;
        if (aid_YuCodeX == null) {
            this.g = new Aid_YuCodeX(this.a, this.f1817d);
        } else {
            aid_YuCodeX.clear_s_dim();
        }
        for (int i2 = 0; i2 < length; i2++) {
            this.g.dim(T2[i2].trim(), F(T[i2]));
        }
        int indexOf2 = lowerCase.indexOf(46);
        mian.c(lowerCase.substring(0, indexOf2), lowerCase.substring(indexOf2 + 1) + obj2.substring(indexOf).trim(), this.g);
    }

    public void function3(String str, String str2, Object obj) {
        String lowerCase = str.substring(0, str.indexOf(40)).trim().toLowerCase();
        String[] T = T(c0(str));
        int length = T.length;
        Object obj2 = c.b.a.a.t.l.get(lowerCase + length);
        if (obj2 == null) {
            String trim = str.substring(0, str.indexOf(46) + 1).trim();
            Iterator<String> it = c.b.a.a.t.l.keySet().iterator();
            while (it.hasNext()) {
                if (it.next().toString().startsWith(trim)) {
                    return;
                }
            }
            X(trim + "myu");
            obj2 = c.b.a.a.t.l.get(lowerCase + length);
        }
        String obj3 = obj2.toString();
        int indexOf = obj3.indexOf(10);
        String[] T2 = T(c0(obj3.substring(0, indexOf)));
        if (length != T2.length) {
            return;
        }
        Aid_YuCodeX aid_YuCodeX = this.g;
        if (aid_YuCodeX == null) {
            this.g = new Aid_YuCodeX(this.a, this.f1817d);
        } else {
            aid_YuCodeX.clear_s_dim();
        }
        for (int i2 = 0; i2 < length; i2++) {
            this.g.dim(T2[i2].trim(), F(T[i2]));
        }
        this.g.dim(str2, obj);
        int indexOf2 = lowerCase.indexOf(46);
        mian.c(lowerCase.substring(0, indexOf2), lowerCase.substring(indexOf2 + 1) + obj3.substring(indexOf).trim(), this.g);
    }

    public void fuz(String str) {
        String str2;
        String[] T = T(c0(str));
        if (T.length == 4) {
            String I = I(G(T[2]));
            c.b.a.a.d.b(I, false);
            try {
                dim(T[3].trim(), Integer.valueOf(c.b.a.a.f.l(this.a, G(T[0]), G(T[1]), I, true)));
                return;
            } catch (Exception unused) {
                str2 = T[3];
            }
        } else {
            if (T.length != 5) {
                return;
            }
            String I2 = I(G(T[2]));
            c.b.a.a.d.b(I2, false);
            try {
                dim(T[4].trim(), Integer.valueOf(c.b.a.a.f.l(this.a, G(T[0]), G(T[1]), I2, F(T[3]).equals(Boolean.TRUE))));
                return;
            } catch (Exception unused2) {
                str2 = T[4];
            }
        }
        dim(str2.trim(), -1);
    }

    public void fuzs(String str) {
        String str2;
        Boolean bool = Boolean.TRUE;
        Boolean bool2 = Boolean.FALSE;
        String[] T = T(c0(str));
        if (T.length == 3) {
            String I = I(G(T[1]));
            c.b.a.a.d.b(I, false);
            try {
                c.b.a.a.f.m(this.a, G(T[0]), I, true);
                dim(T[2].trim(), bool);
                return;
            } catch (Exception unused) {
                str2 = T[2];
            }
        } else {
            if (T.length != 4) {
                return;
            }
            String I2 = I(G(T[1]));
            c.b.a.a.d.b(I2, false);
            try {
                c.b.a.a.f.m(this.a, G(T[0]), I2, F(T[2]).equals(bool));
                dim(T[3].trim(), bool);
                return;
            } catch (Exception unused2) {
                str2 = T[3];
            }
        }
        dim(str2.trim(), bool2);
    }

    public void fw(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            c.b.a.a.d.k(I(G(T[0])), G(T[1]));
        } else if (length == 3) {
            c.b.a.a.d.l(I(G(T[0])), G(T[1]), G(T[2]));
        }
    }

    public String getopenRestoreinterface(String str) {
        return c.b.a.a.f.C(this.a, str);
    }

    public void git(String str) {
        String trim;
        Object component;
        String str2;
        String[] T = T(c0(str));
        Object F = F(T[0]);
        String G = G(T[1]);
        Intent intent = F instanceof Intent ? (Intent) F : null;
        if (G.equals("action")) {
            if (intent == null) {
                str2 = T[2];
                dim(str2.trim(), null);
            } else {
                trim = T[2].trim();
                component = intent.getAction();
                dim(trim, component);
                return;
            }
        }
        if (G.equals("type")) {
            if (intent == null) {
                str2 = T[2];
                dim(str2.trim(), null);
            } else {
                trim = T[2].trim();
                component = intent.getType();
                dim(trim, component);
                return;
            }
        }
        if (G.equals("extra")) {
            if (intent != null) {
                dim(T[3].trim(), intent.getExtras().get(G(T[2])));
                return;
            }
            str2 = T[3];
        } else if (G.equals("flags")) {
            if (intent != null) {
                trim = T[2].trim();
                component = Integer.valueOf(intent.getFlags());
                dim(trim, component);
                return;
            }
            str2 = T[2];
        } else if (G.equals("data")) {
            if (intent != null) {
                trim = T[2].trim();
                component = intent.getData();
                dim(trim, component);
                return;
            }
            str2 = T[2];
        } else if (G.equals("datastring")) {
            if (intent != null) {
                trim = T[2].trim();
                component = intent.getDataString();
                dim(trim, component);
                return;
            }
            str2 = T[2];
        } else {
            if (!G.equals("component")) {
                return;
            }
            if (intent != null) {
                trim = T[2].trim();
                component = intent.getComponent();
                dim(trim, component);
                return;
            }
            str2 = T[2];
        }
        dim(str2.trim(), null);
    }

    public void goUIEventset(String str, String str2, Aid_YuCodeX aid_YuCodeX) {
        mian.c(str, str2, aid_YuCodeX);
    }

    public void gslist(String str) {
        String[] T = T(c0(str));
        if (T.length == 3) {
            dim(T[2].trim(), ((ArrayList) F(T[0])).get(c(G(T[1]))));
        }
    }

    public void gslistiof(String str) {
        String[] T = T(c0(str));
        if (T.length == 3) {
            dim(T[2].trim(), Integer.valueOf(((ArrayList) F(T[0])).indexOf(F(T[1]))));
        }
    }

    public void gslistis(String str) {
        String[] T = T(c0(str));
        if (T.length == 3) {
            dim(T[2].trim(), Boolean.valueOf(((ArrayList) F(T[0])).contains(F(T[1]))));
        }
    }

    public void gslistl(String str) {
        String[] T = T(c0(str));
        if (T.length == 2) {
            dim(T[1].trim(), Integer.valueOf(((ArrayList) F(T[0])).size()));
        }
    }

    public void gslistlof(String str) {
        String[] T = T(c0(str));
        if (T.length == 3) {
            dim(T[2].trim(), Integer.valueOf(((ArrayList) F(T[0])).lastIndexOf(F(T[1]))));
        }
    }

    public void gslistsz(String str) {
        String[] T = T(c0(str));
        if (T.length == 2) {
            dim(T[1].trim(), ((ArrayList) F(T[0])).toArray());
        }
    }

    public void gvs(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            dim(T[1].trim(), w(F(T[0]), T[0]));
        } else if (length == 3) {
            Object F = F(T[0]);
            View w2 = F instanceof View ? (View) F : w(F, T[0]);
            Object F2 = F(T[1]);
            dim(T[2].trim(), F2 instanceof View ? (View) F2 : v(w2, F2, T[1]));
        }
    }

    public void has(String str) {
        String[] T = T(c0(str));
        if (T.length == 2) {
            Object F = F(T[0]);
            View w2 = F instanceof View ? (View) F : w(F, T[0]);
            if (w2 instanceof WebView) {
                ((WebView) w2).loadUrl("javascript:{" + F(T[1]) + "};");
            }
        }
    }

    public void hd(String str) {
        String trim;
        int b2;
        Boolean bool = Boolean.TRUE;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 3) {
            dim(T[2].trim(), Integer.valueOf(c.b.a.a.g.a(G(T[0]), I(G(T[1])), false)));
            return;
        }
        if (length == 4) {
            trim = T[3].trim();
            b2 = c.b.a.a.g.a(G(T[0]), I(G(T[1])), F(T[2]).equals(bool));
        } else {
            if (length != 9) {
                return;
            }
            trim = T[8].trim();
            b2 = c.b.a.a.g.b(G(T[0]), I(G(T[1])), F(T[2]).equals(bool), (String) F(T[3]), (String) F(T[4]), (String) F(T[5]), F(T[6]).equals(bool), (String) F(T[7]));
        }
        dim(trim, Integer.valueOf(b2));
    }

    public void hdd(String str) {
        String[] T = T(c0(str));
        if (T.length == 7) {
            com.iapp.app.a.b.m(I(G(T[0])), I(G(T[1])), c(G(T[2])), c(G(T[3])), c(G(T[4])), c(G(T[5])), F(T[6]).equals(Boolean.TRUE));
        }
    }

    public void hdda(String str) {
        c.b.a.a.w.c g2;
        String str2;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 4) {
            g2 = com.iapp.app.a.b.d(G(T[0]), G(T[1]), F(T[2]));
            str2 = T[3];
        } else if (length == 5) {
            g2 = com.iapp.app.a.b.e(G(T[0]), G(T[1]), G(T[2]), F(T[3]));
            str2 = T[4];
        } else if (length == 6) {
            g2 = com.iapp.app.a.b.f(G(T[0]), G(T[1]), G(T[2]), F(T[3]), F(T[4]));
            str2 = T[5];
        } else {
            if (length != 8) {
                return;
            }
            g2 = com.iapp.app.a.b.g(G(T[0]), I(G(T[1])), G(T[2]), G(T[3]), F(T[4]), F(T[5]).equals(Boolean.TRUE), F(T[6]));
            str2 = T[7];
        }
        dim(str2.trim(), g2);
    }

    public void hddg(String str) {
        String[] T = T(c0(str));
        if (T.length == 3) {
            Object F = F(T[0]);
            if (!(F instanceof c.b.a.a.w.c)) {
                F = com.iapp.app.a.b.f1379c.get(c(String.valueOf(F)));
            }
            dim(T[2].trim(), com.iapp.app.a.b.h((c.b.a.a.w.c) F, G(T[1])));
        }
    }

    public void hddgl(String str) {
        dim(c0(str).trim(), com.iapp.app.a.b.f1379c);
    }

    public void hdds(String str) {
        String[] T = T(c0(str));
        if (T.length == 3) {
            Object F = F(T[0]);
            if (!(F instanceof c.b.a.a.w.c)) {
                F = com.iapp.app.a.b.f1379c.get(c(String.valueOf(F)));
            }
            com.iapp.app.a.b.l((c.b.a.a.w.c) F, G(T[1]), F(T[2]));
        }
    }

    public void hdduigo(String str) {
        Intent intent;
        Context context;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 0) {
            context = this.a;
            intent = new Intent().setClass(this.a, DownList.class);
        } else {
            if (length != 2) {
                return;
            }
            intent = new Intent(this.a, (Class<?>) DownList.class);
            Bundle bundle = new Bundle();
            bundle.putString("background", G(T[0]));
            bundle.putString("backgroundShadow", G(T[1]));
            intent.putExtras(bundle);
            context = this.a;
        }
        context.startActivity(intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void hdfl(java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_YuCodeX.hdfl(java.lang.String):void");
    }

    public void hdfla(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        Object F = F(T[0]);
        if (F instanceof c.b.a.a.e) {
            c.b.a.a.e eVar = (c.b.a.a.e) F;
            if (length == 4) {
                eVar.n(G(T[1]), c(G(T[2])), F(T[3]));
            } else if (length != 5) {
                return;
            } else {
                eVar.o(G(T[1]), c(G(T[2])), F(T[3]), I(G(T[4])));
            }
            eVar.t();
        }
    }

    public void hs(String str) {
        String trim;
        String h2;
        String trim2;
        String f2;
        String substring;
        Boolean bool = Boolean.TRUE;
        String[] T = T(c0(str.replace("\\&", "\\\\\\&")));
        int length = T.length;
        if (length == 2) {
            T[0] = G(T[0]);
            if (T[0].equals("cookie")) {
                trim2 = T[1].trim();
                substring = T[0];
            } else if (T[0].startsWith("cookie:")) {
                trim2 = T[1].trim();
                substring = T[0].substring(7);
            } else {
                trim2 = T[1].trim();
                f2 = c.b.a.a.g.e(T[0], null, null);
            }
            f2 = c.b.a.a.g.d(substring);
        } else if (length == 4) {
            trim2 = T[3].trim();
            f2 = c.b.a.a.g.e((String) F(T[0]), (String) F(T[1]), (String) F(T[2]));
        } else {
            if (length != 5) {
                if (length == 6) {
                    trim = T[5].trim();
                    h2 = c.b.a.a.g.g((String) F(T[0]), (String) F(T[1]), (String) F(T[2]), (String) F(T[3]), F(T[4]).equals(bool));
                } else if (length == 7) {
                    trim = T[6].trim();
                    h2 = c.b.a.a.g.h((String) F(T[0]), (String) F(T[1]), (String) F(T[2]), (String) F(T[3]), F(T[4]).equals(bool), (String) F(T[5]), 20000, 20000, null);
                } else {
                    if (length != 10) {
                        if (length == 1) {
                            T[0] = G(T[0]);
                            c.b.a.a.g.d(T[0]);
                            return;
                        }
                        return;
                    }
                    trim = T[9].trim();
                    h2 = c.b.a.a.g.h((String) F(T[0]), (String) F(T[1]), (String) F(T[2]), (String) F(T[3]), F(T[4]).equals(bool), (String) F(T[5]), Integer.parseInt(G(T[6])), Integer.parseInt(G(T[7])), H(T[8]));
                }
                dim(trim, h2);
                return;
            }
            trim2 = T[4].trim();
            f2 = c.b.a.a.g.f((String) F(T[0]), (String) F(T[1]), (String) F(T[2]), (String) F(T[3]));
        }
        dim(trim2, f2);
    }

    @TargetApi(11)
    public void hsas(String str) {
        String[] T = T(c0(str));
        if (T.length == 2) {
            Object F = F(T[0]);
            View w2 = F instanceof View ? (View) F : w(F, T[0]);
            if (w2 instanceof WebView) {
                WebView webView = (WebView) w2;
                if (Build.VERSION.SDK_INT >= 11) {
                    webView.removeJavascriptInterface("iapp");
                }
                if (F(T[1]).equals(Boolean.TRUE)) {
                    webView.addJavascriptInterface(new iapp(this.a, this.f1817d), "iapp");
                }
            }
        }
    }

    public void huf(String str) {
        String str2;
        String[] T = T(c0(str));
        if (T.length == 5) {
            try {
                dim(T[4].trim(), c.b.a.a.g.m(this.a, G(T[0]), G(T[1]), G(T[2]), G(T[3])));
                return;
            } catch (Exception unused) {
                str2 = T[4];
            }
        } else {
            if (T.length != 6) {
                return;
            }
            try {
                dim(T[5].trim(), c.b.a.a.g.n(this.a, G(T[0]), G(T[1]), G(T[2]), G(T[3]), (String) F(T[4])));
                return;
            } catch (Exception unused2) {
                str2 = T[5];
            }
        }
        dim(str2.trim(), null);
    }

    public void hw(String str) {
        Intent intent;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 1) {
            intent = new Intent(this.a, (Class<?>) Webview.class);
            Bundle bundle = new Bundle();
            bundle.putString("WebURL", G(T[0]));
            intent.putExtras(bundle);
        } else {
            if (length != 3) {
                return;
            }
            intent = new Intent(this.a, (Class<?>) Webview.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("WebURL", G(T[0]));
            bundle2.putString("background", G(T[1]));
            bundle2.putString("backgroundShadow", G(T[2]));
            intent.putExtras(bundle2);
        }
        this.a.startActivity(intent);
    }

    public void hws(String str) {
        Intent intent;
        String G = G(c0(str));
        try {
            intent = c.b.a.a.g.q(this.a, G);
        } catch (Exception unused) {
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.VIEW");
            intent2.setData(c.b.a.a.k.c(this.a, G));
            intent = intent2;
        }
        this.a.startActivity(intent);
    }

    public int if_y2(String str) {
        StringBuilder sb;
        String str2;
        String[] Z = Z(str, '\n');
        String str3 = "";
        int i2 = 0;
        for (int i3 = 0; i3 < Z.length; i3++) {
            if (Z[i3].equals("{")) {
                i2++;
            } else if (Z[i3].equals("}")) {
                i2--;
            } else if (i2 == 0) {
                if (Z[i3].startsWith(c.b.a.a.t.t[0])) {
                    sb = new StringBuilder();
                    str2 = Z[i3];
                } else {
                    if (Z[i3].startsWith("else")) {
                        String trim = Z[i3].substring(4).trim();
                        if (trim.startsWith("f(")) {
                            str3 = c.b.a.a.t.t[0] + trim.substring(2) + "\n";
                        } else {
                            sb = new StringBuilder();
                            str2 = Z[i3];
                        }
                    }
                }
                sb.append(str2);
                sb.append("\n");
                str3 = sb.toString();
            }
            str3 = str3 + Z[i3] + "\n";
            if (i2 != 0) {
                continue;
            } else {
                if (!str3.startsWith(c.b.a.a.t.t[0])) {
                    int YuGo = YuGo(u(str3));
                    if (YuGo == 2) {
                        return 2;
                    }
                    return YuGo == 1 ? 1 : 0;
                }
                if (b(d0(str3))) {
                    int YuGo2 = YuGo(u(str3));
                    if (YuGo2 == 2) {
                        return 2;
                    }
                    return YuGo2 == 1 ? 1 : 0;
                }
            }
        }
        return 0;
    }

    public boolean is(int i2) {
        return i2 == 2 || i2 == -1;
    }

    public void java(String str) {
        Object[] objArr;
        Object obj = null;
        String u2 = str.endsWith("}") ? u(str) : null;
        String[] T = T(d0(str));
        int length = T.length;
        if (length > 2) {
            if (length > 3) {
                Object[] objArr2 = new Object[length - 3];
                for (int i2 = 3; i2 < length; i2++) {
                    objArr2[i2 - 3] = F(T[i2]);
                }
                objArr = objArr2;
            } else {
                objArr = null;
            }
            try {
                obj = c.b.a.a.b.g(this.a, F(T[1]), G(T[2]), objArr, u2 == null ? null : this, u2);
            } catch (Throwable th) {
                th.printStackTrace();
                b0(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.e), str));
            }
            dim(T[0].trim(), obj);
        }
    }

    public void javacb(String str) {
        Object obj;
        String u2 = u(str);
        String[] T = T(d0(str));
        Class cls = (Class) F(T[1]);
        try {
            obj = c.b.a.a.b.o(cls.getClassLoader(), cls, this, u2);
        } catch (Throwable th) {
            th.printStackTrace();
            b0(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.e), str));
            obj = null;
        }
        dim(T[0].trim(), obj);
    }

    public void javags(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        if (length != 3) {
            if (length == 4) {
                Object F = F(T[2]);
                dim(T[0].trim(), F instanceof Class ? c.b.a.a.b.i(F(T[1]), (Class) F, G(T[3])) : c.b.a.a.b.k(F(T[1]), F.toString(), G(T[3])));
                return;
            }
            return;
        }
        Object obj = null;
        try {
            obj = c.b.a.a.b.j(F(T[1]), G(T[2]));
        } catch (Throwable th) {
            th.printStackTrace();
            b0(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.e), str));
        }
        dim(T[0].trim(), obj);
    }

    public void javanew(String str) {
        Object[] objArr;
        Object obj = null;
        String u2 = str.endsWith("}") ? u(str) : null;
        String[] T = T(d0(str));
        int length = T.length;
        if (length > 1) {
            if (length > 2) {
                objArr = new Object[length - 2];
                for (int i2 = 2; i2 < length; i2++) {
                    objArr[i2 - 2] = F(T[i2]);
                }
            } else {
                objArr = null;
            }
            Object F = F(T[1]);
            try {
                obj = F instanceof Class ? c.b.a.a.b.m(this.a, (Class) F, objArr, u2 == null ? null : this, u2) : c.b.a.a.b.n(this.a, F.toString(), objArr, u2 == null ? null : this, u2);
            } catch (Throwable th) {
                th.printStackTrace();
                b0(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.e), str));
            }
            dim(T[0].trim(), obj);
        }
    }

    public void javass(String str) {
        String str2;
        String[] T = T(c0(str));
        int length = T.length;
        Object obj = null;
        if (length == 4) {
            try {
                obj = c.b.a.a.b.p(F(T[1]), G(T[2]), F(T[3]));
            } catch (Throwable th) {
                th.printStackTrace();
                b0(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.e), str));
            }
            str2 = T[0];
        } else {
            if (length != 5) {
                return;
            }
            Object F = F(T[2]);
            try {
                obj = Boolean.valueOf(F instanceof Class ? c.b.a.a.b.q(F(T[1]), (Class) F, G(T[3]), F(T[4])) : c.b.a.a.b.r(F(T[1]), F.toString(), G(T[3]), F(T[4])));
            } catch (Throwable th2) {
                th2.printStackTrace();
                b0(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.e), str));
            }
            str2 = T[0];
        }
        dim(str2.trim(), obj);
    }

    public void javax(String str) {
        Object[] objArr;
        Object obj = null;
        String u2 = str.endsWith("}") ? u(str) : null;
        String[] T = T(d0(str));
        int length = T.length;
        if (length > 3) {
            if (length > 4) {
                Object[] objArr2 = new Object[length - 4];
                for (int i2 = 4; i2 < length; i2++) {
                    objArr2[i2 - 4] = F(T[i2]);
                }
                objArr = objArr2;
            } else {
                objArr = null;
            }
            Object F = F(T[2]);
            try {
                obj = F instanceof Class ? c.b.a.a.b.e(this.a, F(T[1]), (Class) F, G(T[3]), objArr, u2 == null ? null : this, u2) : c.b.a.a.b.f(this.a, F(T[1]), F.toString(), G(T[3]), objArr, u2 == null ? null : this, u2);
            } catch (Throwable th) {
                th.printStackTrace();
                b0(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.e), str));
            }
            dim(T[0].trim(), obj);
        }
    }

    @SuppressLint({"NewApi"})
    public void json(String str) {
        String str2;
        String trim;
        JSONObject jSONObject;
        String trim2;
        Object obj;
        String trim3;
        Object jSONArray;
        int i2;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            try {
                dim(T[1].trim(), new JSONObject(G(T[0])));
                return;
            } catch (JSONException e2) {
                e2.printStackTrace();
                str2 = T[0];
            }
        } else {
            if (length == 3) {
                Object F = F(T[1]);
                if (F.equals("size")) {
                    Object F2 = F(T[0]);
                    if (F2 instanceof JSONObject) {
                        trim3 = T[2].trim();
                        i2 = ((JSONObject) F2).length();
                    } else if (F2 instanceof JSONArray) {
                        trim3 = T[2].trim();
                        i2 = ((JSONArray) F2).length();
                    } else {
                        trim3 = T[2].trim();
                        i2 = -1;
                    }
                    jSONArray = Integer.valueOf(i2);
                } else {
                    if (F.equals("del")) {
                        Object F3 = F(T[0]);
                        if (F3 instanceof JSONObject) {
                            ((JSONObject) F3).remove(G(T[2]));
                            return;
                        } else {
                            if (!(F3 instanceof JSONArray) || Build.VERSION.SDK_INT < 19) {
                                return;
                            }
                            ((JSONArray) F3).remove(c(G(T[2])));
                            return;
                        }
                    }
                    if (!F.equals("json")) {
                        return;
                    }
                    Object F4 = F(T[0]);
                    if (F4 instanceof JSONObject) {
                        trim3 = T[2].trim();
                        jSONArray = ((JSONObject) F4).toString();
                    } else if (F4 instanceof JSONArray) {
                        trim3 = T[2].trim();
                        jSONArray = ((JSONArray) F4).toString();
                    } else {
                        str2 = T[2];
                    }
                }
                dim(trim3, jSONArray);
                return;
            }
            if (length != 4) {
                return;
            }
            Object F5 = F(T[1]);
            if (F5.equals("get")) {
                Object F6 = F(T[0]);
                try {
                    if (F6 instanceof JSONObject) {
                        trim2 = T[3].trim();
                        obj = ((JSONObject) F6).get(G(T[2]));
                    } else if (!(F6 instanceof JSONArray)) {
                        dim(T[3].trim(), null);
                        return;
                    } else {
                        trim2 = T[3].trim();
                        obj = ((JSONArray) F6).get(c(G(T[2])));
                    }
                    dim(trim2, obj);
                    return;
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    str2 = T[3];
                }
            } else {
                if (F5.equals("set")) {
                    Object F7 = F(T[0]);
                    try {
                        if (F7 instanceof JSONObject) {
                            ((JSONObject) F7).put(G(T[2]), F(T[3]));
                        } else if (F7 instanceof JSONArray) {
                            ((JSONArray) F7).put(c(G(T[2])), F(T[3]));
                        }
                        return;
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                        return;
                    }
                }
                if (F5.equals("list")) {
                    Object F8 = F(T[0]);
                    if (F8 instanceof JSONObject) {
                        try {
                            dim(T[3].trim(), ((JSONObject) F8).getJSONArray(G(T[2])));
                            return;
                        } catch (JSONException e5) {
                            e5.printStackTrace();
                            str2 = T[3];
                        }
                    } else {
                        str2 = T[3];
                    }
                } else {
                    if (!F5.equals("data")) {
                        return;
                    }
                    Object F9 = F(T[0]);
                    try {
                        if (F9 instanceof JSONObject) {
                            trim = T[3].trim();
                            jSONObject = ((JSONObject) F9).getJSONObject(G(T[2]));
                        } else if (!(F9 instanceof JSONArray)) {
                            dim(T[3].trim(), null);
                            return;
                        } else {
                            trim = T[3].trim();
                            jSONObject = ((JSONArray) F9).getJSONObject(c(G(T[2])));
                        }
                        dim(trim, jSONObject);
                        return;
                    } catch (JSONException e6) {
                        e6.printStackTrace();
                        str2 = T[3];
                    }
                }
            }
        }
        dim(str2.trim(), null);
    }

    public void lan(String str) {
        c.c.a.a.a(this.f1817d, c(G(T(c0(str))[0])));
    }

    public void loadjar(String str) {
        DexClassLoader r2;
        String str2;
        Object obj = Boolean.TRUE;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            dim(T[1].trim(), c.b.a.a.f.r(this.a, G(T[0]), this.a.getClassLoader()));
            return;
        }
        if (length == 3) {
            r2 = c.b.a.a.f.r(this.a, G(T[0]), this.a.getClassLoader());
            if (F(T[1]).equals(obj)) {
                c.b.a.a.f.u(this.a, r2);
            }
            str2 = T[2];
        } else {
            if (length != 4) {
                return;
            }
            r2 = c.b.a.a.f.r(this.a, G(T[0]), (ClassLoader) F(T[2]));
            if (F(T[1]).equals(obj)) {
                c.b.a.a.f.u(this.a, r2);
            }
            str2 = T[3];
        }
        dim(str2.trim(), r2);
    }

    public void loadso(String str) {
        c.b.a.a.f.s(G(str));
    }

    byte[] n(String str, char c2) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        while (i2 < length) {
            if (str.charAt(i2) == c2) {
                if (z2) {
                    byteArrayOutputStream.write(Integer.parseInt(str.substring(i3, i2)));
                    z2 = false;
                }
                i3 = i2 + 1;
                i2 = i3;
            } else {
                i2++;
                z2 = true;
            }
        }
        if (z2) {
            byteArrayOutputStream.write(Integer.parseInt(str.substring(i3, i2)));
        }
        return byteArrayOutputStream.toByteArray();
    }

    public void ngde(String str) {
        GradientDrawable gradientDrawable;
        String str2;
        GradientDrawable gradientDrawable2;
        String str3;
        String[] T = T(c0(str));
        if (T.length != 2) {
            if (T.length == 3) {
                int c2 = c(G(T[0]));
                int o2 = c.b.a.a.p.o(G(T[1]));
                gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setColor(o2);
                if (c2 > 0) {
                    gradientDrawable2.setCornerRadius(c2);
                }
                str3 = T[2];
            } else if (T.length == 4) {
                int c3 = c(G(T[0]));
                int o3 = c.b.a.a.p.o(G(T[1]));
                int o4 = c.b.a.a.p.o(G(T[2]));
                gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setColor(o3);
                if (c3 > 0) {
                    gradientDrawable2.setStroke(c3, o4);
                }
                str3 = T[3];
            } else {
                if (T.length == 5) {
                    int c4 = c(G(T[0]));
                    int c5 = c(G(T[1]));
                    int o5 = c.b.a.a.p.o(G(T[2]));
                    int o6 = c.b.a.a.p.o(G(T[3]));
                    GradientDrawable gradientDrawable3 = new GradientDrawable();
                    gradientDrawable3.setColor(o5);
                    if (c5 > 0) {
                        gradientDrawable3.setCornerRadius(c5);
                    }
                    if (c4 > 0) {
                        gradientDrawable3.setStroke(c4, o6);
                    }
                    dim(T[4].trim(), gradientDrawable3);
                    return;
                }
                if (T.length != 6) {
                    return;
                }
                int c6 = c(G(T[0]));
                int c7 = c(G(T[1]));
                int o7 = c.b.a.a.p.o(G(T[3]));
                String[] Z = Z(G(T[2]), '|');
                int[] iArr = new int[Z.length];
                for (int i2 = 0; i2 < Z.length; i2++) {
                    iArr[i2] = c.b.a.a.p.o(Z[i2]);
                }
                gradientDrawable = new GradientDrawable(K(G(T[4])), iArr);
                if (c7 > 0) {
                    gradientDrawable.setCornerRadius(c7);
                }
                if (c6 > 0) {
                    gradientDrawable.setStroke(c6, o7);
                }
                str2 = T[5];
            }
            dim(str3.trim(), gradientDrawable2);
            return;
        }
        int o8 = c.b.a.a.p.o(G(T[0]));
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(o8);
        str2 = T[1];
        dim(str2.trim(), gradientDrawable);
    }

    public void nslist(String str) {
        dim(c0(str).trim(), new ArrayList());
    }

    public void nsz(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            dim(T[1].trim(), new Object[c(G(T[0]))]);
            return;
        }
        if (length == 3) {
            int c2 = c(G(T[0]));
            Object F = F(T[1]);
            if (F instanceof String) {
                dim(T[2].trim(), Array.newInstance(c.b.a.a.b.b(F.toString()), c2));
            } else if (F instanceof Class) {
                dim(T[2].trim(), Array.newInstance((Class<?>) F, c2));
            } else {
                dim(T[2].trim(), null);
            }
        }
    }

    public void nuibs(String str) {
        String[] T = T(c0(str));
        if (T.length == 4) {
            dim(T[3].trim(), new c.c.a.c(this.a).d(F(T[0]), F(T[1]), F(T[2])));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0095 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0133  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void nvw(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_YuCodeX.nvw(java.lang.String):void");
    }

    public void openRestoreinterface(String str, ViewGroup viewGroup, int i2, Object obj) {
        Context context = this.a;
        c.c.a.b bVar = new c.c.a.b(context);
        bVar.a = 0;
        c.b.a.a.f.D(this, context, str, viewGroup, i2, obj, bVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void openRestoreinterfaceX(java.lang.String r8, android.view.ViewGroup r9, int r10, java.lang.Object r11, c.c.a.b r12, java.lang.String r13) {
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
            if (r0 == r2) goto Lb4
            if (r3 != r2) goto L2f
            goto Lb4
        L2f:
            int r0 = r0 + r10
            int r3 = r3 + r10
            java.lang.String r2 = "ProgressBar"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L5a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "\n"
            r2.append(r5)
            r2.append(r4)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            java.lang.String r6 = "\nstyle="
            java.lang.String r2 = c.b.a.a.p.c(r2, r6, r5)
            if (r2 == 0) goto L5a
            android.view.View r0 = r12.f(r0, r1, r2)
            goto L5e
        L5a:
            android.view.View r0 = r12.e(r0, r1)
        L5e:
            if (r0 != 0) goto L61
            return
        L61:
            if (r3 != r10) goto L64
            goto L6a
        L64:
            android.view.View r9 = r9.findViewById(r3)
            android.view.ViewGroup r9 = (android.view.ViewGroup) r9
        L6a:
            android.view.ViewGroup$LayoutParams r2 = r12.d(r9, r0)
            r0.setLayoutParams(r2)
            com.iapp.app.i r2 = new com.iapp.app.i
            android.content.Context r3 = r7.a
            r2.<init>(r0, r3, r10)
            boolean r10 = r12.a(r2, r4)
            if (r10 != 0) goto L7f
            return
        L7f:
            boolean r10 = r0 instanceof androidx.drawerlayout.widget.DrawerLayout
            if (r10 == 0) goto L9c
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r8)
            java.lang.String r8 = "<eventItme type=\"$__tag_namexml_\">"
            r10.append(r8)
            r10.append(r13)
            java.lang.String r8 = "</eventItme>"
            r10.append(r8)
            java.lang.String r8 = r10.toString()
        L9c:
            r10 = 4
            java.lang.Object[] r10 = new java.lang.Object[r10]
            r12 = 0
            r10[r12] = r1
            r12 = 1
            r10[r12] = r4
            r12 = 2
            r10[r12] = r8
            r12 = 3
            r10[r12] = r11
            r0.setTag(r10)
            r7.k(r0, r8, r13)
            r9.addView(r0)
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_YuCodeX.openRestoreinterfaceX(java.lang.String, android.view.ViewGroup, int, java.lang.Object, c.c.a.b, java.lang.String):void");
    }

    public void otob(String str) {
        byte[] bArr;
        String str2;
        String[] T = T(c0(str));
        int length = T.length;
        byte[] bArr2 = null;
        int i2 = 0;
        if (length == 2) {
            byte[] t2 = c.b.a.a.f.t(this.a, G(T[0]));
            if (t2 != null) {
                StringBuffer stringBuffer = new StringBuffer();
                int length2 = t2.length;
                while (i2 < length2) {
                    byte b2 = t2[i2];
                    stringBuffer.append(' ');
                    stringBuffer.append((int) b2);
                    i2++;
                }
                dim(T[1].trim(), stringBuffer.toString().trim());
                return;
            }
            str2 = T[1];
        } else {
            if (length != 3) {
                if (length == 4) {
                    Object F = F(T[0]);
                    if (F.equals("file")) {
                        dim(T[3].trim(), c.b.a.a.f.t(this.a, G(T[2])));
                        return;
                    } else {
                        if (F.equals("str")) {
                            Object F2 = F(T[1]);
                            if (F2 == null) {
                                bArr2 = G(T[2]).getBytes();
                            } else {
                                try {
                                    bArr2 = G(T[2]).getBytes(F2.toString());
                                } catch (UnsupportedEncodingException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            dim(T[3].trim(), bArr2);
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            Object F3 = F(T[0]);
            if (F3 == null) {
                bArr = G(T[1]).getBytes();
            } else {
                try {
                    bArr = G(T[1]).getBytes(F3.toString());
                } catch (UnsupportedEncodingException e3) {
                    e3.printStackTrace();
                    bArr = null;
                }
            }
            if (bArr != null) {
                StringBuffer stringBuffer2 = new StringBuffer();
                int length3 = bArr.length;
                while (i2 < length3) {
                    byte b3 = bArr[i2];
                    stringBuffer2.append(' ');
                    stringBuffer2.append((int) b3);
                    i2++;
                }
                dim(T[2].trim(), stringBuffer2.toString().trim());
                return;
            }
            str2 = T[2];
        }
        dim(str2.trim(), null);
    }

    public void res(String str) {
        String trim;
        com.iapp.app.k kVar;
        String G;
        String str2;
        Object valueOf;
        String trim2;
        Object c2;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 1) {
            trim2 = T[0].trim();
            c2 = new com.iapp.app.k(this.a);
        } else {
            if (length == 2) {
                dim(T[1].trim(), new com.iapp.app.k(this.a, I(G(T[0]))));
                return;
            }
            if (length != 3) {
                if (length == 4) {
                    Object F = F(T[0]);
                    if (!(F instanceof com.iapp.app.k)) {
                        return;
                    }
                    trim = T[3].trim();
                    kVar = (com.iapp.app.k) F;
                    G = G(T[1]);
                    str2 = T[2];
                } else {
                    if (length != 5) {
                        return;
                    }
                    Object F2 = F(T[0]);
                    if (!(F2 instanceof com.iapp.app.k)) {
                        return;
                    }
                    if (F(T[3]).equals(Boolean.TRUE)) {
                        trim = T[4].trim();
                        valueOf = Integer.valueOf(((com.iapp.app.k) F2).b(G(T[1]), G(T[2])));
                        dim(trim, valueOf);
                        return;
                    } else {
                        trim = T[4].trim();
                        kVar = (com.iapp.app.k) F2;
                        G = G(T[1]);
                        str2 = T[2];
                    }
                }
                valueOf = kVar.d(G, G(str2));
                dim(trim, valueOf);
                return;
            }
            Object F3 = F(T[0]);
            if (!(F3 instanceof com.iapp.app.k)) {
                return;
            }
            Object F4 = F(T[1]);
            if (F4.equals("asset")) {
                trim2 = T[2].trim();
                c2 = ((com.iapp.app.k) F3).a();
            } else {
                if (!F4.equals("resources")) {
                    return;
                }
                trim2 = T[2].trim();
                c2 = ((com.iapp.app.k) F3).c();
            }
        }
        dim(trim2, c2);
    }

    public void rps(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        try {
            if (length == 0) {
                c.b.a.a.k.d(this.f1817d, this.f1817d.getPackageManager().getPackageInfo(this.f1817d.getPackageName(), 4096).requestedPermissions);
                return;
            }
            boolean z2 = true;
            if (length != 1) {
                if (length == 2) {
                    if (ContextCompat.checkSelfPermission(this.f1817d, String.valueOf(F(T[1]))) == 0) {
                        z2 = false;
                    }
                    dim(T[0].trim(), Boolean.valueOf(z2));
                    return;
                }
                return;
            }
            Object F = F(T[0]);
            if (!F.getClass().isArray()) {
                c.b.a.a.k.d(this.f1817d, new String[]{String.valueOf(F)});
                return;
            }
            int length2 = Array.getLength(F);
            String[] strArr = new String[length2];
            for (int i2 = 0; i2 < length2; i2++) {
                strArr[i2] = String.valueOf(Array.get(F, i2));
            }
            c.b.a.a.k.d(this.f1817d, strArr);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void s(String str) {
        String substring = str.substring(str.indexOf(40) + 1, str.lastIndexOf(41));
        int indexOf = substring.indexOf(44);
        String trim = substring.substring(0, indexOf).trim();
        String replace = trim.replace('+', ',').replace('-', ',').replace('*', ',').replace('/', ',').replace('(', ',').replace(')', ',').replace('%', ',');
        String str2 = " " + trim.replace("+", " + ").replace("-", " - ").replace("*", " * ").replace("/", " / ").replace("(", " ( ").replace(")", " ) ").replace("%", " % ") + " ";
        for (String str3 : Z(replace, ',')) {
            str2 = str2.replace(" " + str3.trim() + " ", G(str3));
        }
        long j2 = -1;
        try {
            j2 = (long) d.a.h.f(str2).a();
        } catch (d.b.e e2) {
            e2.printStackTrace();
        }
        dim(substring.substring(indexOf + 1).trim(), Long.valueOf(j2));
    }

    public void s2(String str) {
        String substring = str.substring(str.indexOf(40) + 1, str.lastIndexOf(41));
        int indexOf = substring.indexOf(44);
        String trim = substring.substring(0, indexOf).trim();
        String replace = trim.replace('+', ',').replace('-', ',').replace('*', ',').replace('/', ',').replace('(', ',').replace(')', ',').replace('%', ',');
        String str2 = " " + trim.replace("+", " + ").replace("-", " - ").replace("*", " * ").replace("/", " / ").replace("(", " ( ").replace(")", " ) ").replace("%", " % ") + " ";
        for (String str3 : Z(replace, ',')) {
            str2 = str2.replace(" " + str3.trim() + " ", G(str3));
        }
        double d2 = -1.0d;
        try {
            d2 = d.a.h.f(str2).a();
        } catch (d.b.e e2) {
            e2.printStackTrace();
        }
        dim(substring.substring(indexOf + 1).trim(), String.format("%.2f", Double.valueOf(d2)));
    }

    public void sbp(String str) {
        Object createBitmap;
        String str2;
        String str3;
        String[] T = T(c0(str));
        int length = T.length;
        Object F = F(T[0]);
        Bitmap v2 = F instanceof Bitmap ? (Bitmap) F : c.b.a.a.f.v(this.a, String.valueOf(F));
        if (length == 2) {
            if (v2 != null) {
                dim(T[1].trim(), v2);
                return;
            }
            str3 = T[1];
        } else if (length == 6) {
            if (v2 != null) {
                createBitmap = Bitmap.createBitmap(v2, c(G(T[1])), c(G(T[2])), c(G(T[3])), c(G(T[4])));
                str2 = T[5];
                dim(str2.trim(), createBitmap);
                return;
            }
            str3 = T[5];
        } else {
            if (length != 7) {
                return;
            }
            if (v2 != null) {
                Matrix matrix = new Matrix();
                matrix.preRotate(c(G(T[5])));
                createBitmap = Bitmap.createBitmap(v2, c(G(T[1])), c(G(T[2])), c(G(T[3])), c(G(T[4])), matrix, true);
                str2 = T[6];
                dim(str2.trim(), createBitmap);
                return;
            }
            str3 = T[6];
        }
        dim(str3.trim(), null);
    }

    public void scheng(String str) {
        String trim;
        Object valueOf;
        long parseDouble;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            trim = T[1].trim();
            parseDouble = (long) (Double.parseDouble(G(T[1])) * Double.parseDouble(G(T[0])));
        } else {
            if (length != 3) {
                if (length == 4 && F(T[2]).equals(Boolean.TRUE)) {
                    trim = T[3].trim();
                    valueOf = Double.valueOf(Double.parseDouble(G(T[0])) * Double.parseDouble(G(T[1])));
                    dim(trim, valueOf);
                }
                return;
            }
            trim = T[2].trim();
            parseDouble = (long) (Double.parseDouble(G(T[0])) * Double.parseDouble(G(T[1])));
        }
        valueOf = Long.valueOf(parseDouble);
        dim(trim, valueOf);
    }

    public void schu(String str) {
        String trim;
        Object valueOf;
        long parseDouble;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            trim = T[1].trim();
            parseDouble = (long) (Double.parseDouble(G(T[1])) / Double.parseDouble(G(T[0])));
        } else {
            if (length != 3) {
                if (length == 4 && F(T[2]).equals(Boolean.TRUE)) {
                    trim = T[3].trim();
                    valueOf = Double.valueOf(Double.parseDouble(G(T[0])) / Double.parseDouble(G(T[1])));
                    dim(trim, valueOf);
                }
                return;
            }
            trim = T[2].trim();
            parseDouble = (long) (Double.parseDouble(G(T[0])) / Double.parseDouble(G(T[1])));
        }
        valueOf = Long.valueOf(parseDouble);
        dim(trim, valueOf);
    }

    public void sdeg(String str) {
        c.b.a.a.t.u = Integer.parseInt(G(T(c0(str))[0]));
    }

    public void se(String str) {
        Object matcher;
        String trim;
        String trim2;
        Object group;
        int end;
        int end2;
        boolean find;
        String[] T = T(c0(str));
        int length = T.length;
        Object F = F(T[0]);
        if (F instanceof Matcher) {
            Matcher matcher2 = (Matcher) F;
            String G = G(T[1]);
            if (G.equals("sral")) {
                trim2 = T[3].trim();
                group = matcher2.replaceAll(G(T[2]));
            } else if (G.equals("srft")) {
                trim2 = T[3].trim();
                group = matcher2.replaceFirst(G(T[2]));
            } else {
                if (G.equals("ms")) {
                    trim = T[2].trim();
                    find = matcher2.matches();
                } else if (!G.equals("find")) {
                    if (G.equals("gl")) {
                        trim = T[2].trim();
                        end = matcher2.groupCount();
                    } else if (G.equals("start")) {
                        if (length == 4) {
                            trim2 = T[3].trim();
                            end2 = matcher2.start(c(G(T[2])));
                            group = Integer.valueOf(end2);
                        } else {
                            trim = T[2].trim();
                            end = matcher2.start();
                        }
                    } else if (G.equals("end")) {
                        if (length == 4) {
                            trim2 = T[3].trim();
                            end2 = matcher2.end(c(G(T[2])));
                            group = Integer.valueOf(end2);
                        } else {
                            trim = T[2].trim();
                            end = matcher2.end();
                        }
                    } else {
                        if (!G.equals("group")) {
                            return;
                        }
                        if (length == 4) {
                            trim2 = T[3].trim();
                            group = matcher2.group(c(G(T[2])));
                        } else {
                            trim = T[2].trim();
                            matcher = matcher2.group();
                        }
                    }
                    matcher = Integer.valueOf(end);
                } else if (length == 4) {
                    trim2 = T[3].trim();
                    group = Boolean.valueOf(matcher2.find(c(G(T[2]))));
                } else {
                    trim = T[2].trim();
                    find = matcher2.find();
                }
                matcher = Boolean.valueOf(find);
            }
            dim(trim2, group);
            return;
        }
        if (!(F instanceof String)) {
            return;
        }
        matcher = Pattern.compile(G(T[1]), c(G(T[2]))).matcher(String.valueOf(F));
        trim = T[3].trim();
        dim(trim, matcher);
    }

    public void setClickable(ArrayList<Integer> arrayList, StringBuffer stringBuffer, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                setClickable(arrayList, stringBuffer, viewGroup.getChildAt(i2));
            }
        }
        int id = view.getId();
        if (id > 0) {
            if (view.isClickable() || view.isLongClickable()) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(',');
                stringBuffer2.append(id);
                stringBuffer2.append(',');
                if (stringBuffer.indexOf(stringBuffer2.toString()) == -1) {
                    arrayList.add(Integer.valueOf(id));
                }
            }
        }
    }

    public void sgsz(String str) {
        String[] T = T(c0(str));
        if (T.length == 3) {
            try {
                dim(T[2].trim(), Array.get(F(T[0]), c(G(T[1]))));
            } catch (Exception e2) {
                e2.printStackTrace();
                dim(T[2].trim(), null);
            }
        }
    }

    public void sgszl(String str) {
        String[] T = T(c0(str));
        try {
            dim(T[1].trim(), Integer.valueOf(Array.getLength(F(T[0]))));
        } catch (Exception e2) {
            e2.printStackTrace();
            dim(T[1].trim(), -1);
        }
    }

    public void shb(String str) {
        String c02 = c0(str);
        CharSequence text = ((ClipboardManager) this.a.getSystemService("clipboard")).getText();
        dim(c02.trim(), text == null ? null : text.toString());
    }

    public void simei(String str) {
        dim(c0(str).trim(), c.b.a.a.p.q(this.a));
    }

    public void simsi(String str) {
        String c02 = c0(str);
        try {
            dim(c02.trim(), ((TelephonyManager) this.a.getSystemService("phone")).getSubscriberId());
        } catch (Exception e2) {
            e2.printStackTrace();
            dim(c02.trim(), null);
        }
    }

    public void siof(String str) {
        String trim;
        int indexOf;
        String[] T = T(c0(str));
        if (T.length == 4) {
            trim = T[3].trim();
            indexOf = G(T[0]).indexOf(G(T[1]), c(G(T[2])));
        } else {
            if (T.length != 3) {
                return;
            }
            trim = T[2].trim();
            indexOf = G(T[0]).indexOf(G(T[1]));
        }
        dim(trim, Integer.valueOf(indexOf));
    }

    public void sit(String str) {
        Intent intent;
        String[] T = T(c0(str));
        Object F = F(T[0]);
        String G = G(T[1]);
        if (F instanceof Intent) {
            intent = (Intent) F;
        } else {
            intent = new Intent();
            dim(T[0].trim(), intent);
        }
        if (G.equals("action")) {
            intent.setAction(G(T[2]));
            return;
        }
        if (G.equals("type")) {
            intent.setType(G(T[2]));
            return;
        }
        if (!G.equals("extra")) {
            if (G.equals("flags")) {
                intent.setFlags(c(G(T[2])));
                return;
            }
            if (G.equals("data")) {
                intent.setData(c.b.a.a.k.c(this.a, G(T[2])));
                return;
            } else if (G.equals("classname")) {
                intent.setClassName(G(T[2]), G(T[3]));
                return;
            } else {
                if (G.equals("component")) {
                    intent.setComponent(new ComponentName(G(T[2]), G(T[3])));
                    return;
                }
                return;
            }
        }
        Object F2 = F(T[3]);
        if (F2 instanceof String[]) {
            intent.putExtra(G(T[2]), (String[]) F2);
            return;
        }
        if (F2 instanceof Boolean) {
            intent.putExtra(G(T[2]), F2.equals(Boolean.TRUE));
            return;
        }
        if (F2 instanceof Integer) {
            intent.putExtra(G(T[2]), Integer.parseInt(String.valueOf(F2)));
        } else if (F2 instanceof Long) {
            intent.putExtra(G(T[2]), Long.parseLong(String.valueOf(F2)));
        } else {
            intent.putExtra(G(T[2]), String.valueOf(F2));
        }
    }

    public void sj(String str) {
        String[] T = T(c0(str));
        if (T.length == 4) {
            Object F = F(T[1]);
            Object F2 = F(T[2]);
            dim(T[3].trim(), c.b.a.a.p.c(G(T[0]), F != null ? String.valueOf(F) : null, F2 == null ? null : String.valueOf(F2)));
        }
    }

    public void sjia(String str) {
        String trim;
        Object valueOf;
        long parseDouble;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            trim = T[1].trim();
            parseDouble = (long) (Double.parseDouble(G(T[1])) + Double.parseDouble(G(T[0])));
        } else {
            if (length != 3) {
                if (length == 4 && F(T[2]).equals(Boolean.TRUE)) {
                    trim = T[3].trim();
                    valueOf = Double.valueOf(Double.parseDouble(G(T[0])) + Double.parseDouble(G(T[1])));
                    dim(trim, valueOf);
                }
                return;
            }
            trim = T[2].trim();
            parseDouble = (long) (Double.parseDouble(G(T[0])) + Double.parseDouble(G(T[1])));
        }
        valueOf = Long.valueOf(parseDouble);
        dim(trim, valueOf);
    }

    public void sjian(String str) {
        String trim;
        Object valueOf;
        long parseDouble;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            trim = T[1].trim();
            parseDouble = (long) (Double.parseDouble(G(T[1])) - Double.parseDouble(G(T[0])));
        } else {
            if (length != 3) {
                if (length == 4 && F(T[2]).equals(Boolean.TRUE)) {
                    trim = T[3].trim();
                    valueOf = Double.valueOf(Double.parseDouble(G(T[0])) - Double.parseDouble(G(T[1])));
                    dim(trim, valueOf);
                }
                return;
            }
            trim = T[2].trim();
            parseDouble = (long) (Double.parseDouble(G(T[0])) - Double.parseDouble(G(T[1])));
        }
        valueOf = Long.valueOf(parseDouble);
        dim(trim, valueOf);
    }

    public void sjxx(String str) {
        String c02 = c0(str);
        String[] y2 = y();
        int[] z2 = z();
        int[] A = A();
        String[] B = B();
        dim(c02.trim(), new String[]{y2[0] + "\n" + y2[1], z2[0] + "\n" + z2[1] + "\n" + A[0] + "\n" + A[1], B[0] + "\n" + B[1] + "\n" + B[2]});
    }

    public void sl(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 3) {
            dim(T[2].trim(), c.b.a.a.q.e(G(T[0]), G(T[1])));
        } else if (length == 4) {
            dim(T[3].trim(), F(T[2]).equals(Boolean.TRUE) ? G(T[0]).split(G(T[1])) : c.b.a.a.q.e(G(T[0]), G(T[1])));
        }
    }

    public void slg(String str) {
        String[] T = T(c0(str));
        dim(T[1].trim(), Integer.valueOf(G(T[0]).length()));
    }

    public void slof(String str) {
        String trim;
        int lastIndexOf;
        String[] T = T(c0(str));
        if (T.length == 4) {
            trim = T[3].trim();
            lastIndexOf = G(T[0]).lastIndexOf(G(T[1]), c(G(T[2])));
        } else {
            if (T.length != 3) {
                return;
            }
            trim = T[2].trim();
            lastIndexOf = G(T[0]).lastIndexOf(G(T[1]));
        }
        dim(trim, Integer.valueOf(lastIndexOf));
    }

    public void slower(String str) {
        String[] T = T(c0(str));
        dim(T[1].trim(), G(T[0]).toLowerCase());
    }

    public void sn(String str) {
        String substring = str.substring(str.indexOf(40) + 1, str.lastIndexOf(41));
        int indexOf = substring.indexOf(44);
        String trim = substring.substring(0, indexOf).trim();
        String replace = trim.replace('+', ',').replace('-', ',').replace('*', ',').replace('/', ',').replace('(', ',').replace(')', ',').replace('%', ',');
        String str2 = " " + trim.replace("+", " + ").replace("-", " - ").replace("*", " * ").replace("/", " / ").replace("(", " ( ").replace(")", " ) ").replace("%", " % ") + " ";
        for (String str3 : Z(replace, ',')) {
            str2 = str2.replace(" " + str3.trim() + " ", G(str3));
        }
        double d2 = -1.0d;
        try {
            d2 = d.a.h.f(str2).a();
        } catch (d.b.e e2) {
            e2.printStackTrace();
        }
        dim(substring.substring(indexOf + 1).trim(), Double.valueOf(d2));
    }

    public void sot(String str) {
        String trim;
        Object v2;
        int u2;
        Object obj = Boolean.TRUE;
        String[] T = T(d0(str));
        int length = T.length;
        if (length == 6) {
            String u3 = u(str);
            c.b.a.a.o oVar = new c.b.a.a.o(c(G(T[0])), I(G(T[1])), c(G(T[2])), c(G(T[3])), F(T[4]).equals(obj));
            oVar.F(new i0(u3));
            dim(T[5].trim(), oVar);
            return;
        }
        if (length == 5) {
            String u4 = u(str);
            c.b.a.a.o oVar2 = new c.b.a.a.o(G(T[0]), c(G(T[1])), c(G(T[2])), F(T[3]).equals(obj));
            oVar2.F(new j0(u4));
            dim(T[4].trim(), oVar2);
            return;
        }
        if (length == 4) {
            if (F(T[1]).equals("list")) {
                Object F = F(T[0]);
                if (F instanceof c.b.a.a.o) {
                    dim(T[3].trim(), ((c.b.a.a.o) F).A(c(G(T[2]))));
                    return;
                }
                return;
            }
            return;
        }
        if (length != 3) {
            if (length == 2 && F(T[1]).equals("re")) {
                Object F2 = F(T[0]);
                if (F2 instanceof c.b.a.a.o) {
                    ((c.b.a.a.o) F2).s();
                    return;
                }
                return;
            }
            return;
        }
        Object F3 = F(T[0]);
        if (F3 instanceof c.b.a.a.o) {
            Object F4 = F(T[1]);
            if (F4.equals("str")) {
                ((c.b.a.a.o) F3).b(G(T[2]));
                return;
            }
            if (F4.equals("file")) {
                ((c.b.a.a.o) F3).a(new File(I(G(T[2]))));
                return;
            }
            if (F4.equals("bt")) {
                ((c.b.a.a.o) F3).c(n(G(T[2]), ' '));
                return;
            }
            if (F4.equals("bt2")) {
                ((c.b.a.a.o) F3).d((byte[]) F(T[2]));
                return;
            }
            if (F4.equals("ip")) {
                trim = T[2].trim();
                v2 = Boolean.valueOf(((c.b.a.a.o) F3).B());
            } else {
                if (F4.equals("id")) {
                    trim = T[2].trim();
                    u2 = ((c.b.a.a.o) F3).t();
                } else if (F4.equals("list")) {
                    trim = T[2].trim();
                    v2 = ((c.b.a.a.o) F3).w();
                } else if (F4.equals("size")) {
                    trim = T[2].trim();
                    u2 = ((c.b.a.a.o) F3).u();
                } else {
                    if (!F4.equals("server")) {
                        if (F4.equals("new")) {
                            ((c.b.a.a.o) F3).f1351c = F(T[2]).equals(obj);
                            return;
                        }
                        return;
                    }
                    trim = T[2].trim();
                    v2 = ((c.b.a.a.o) F3).v();
                }
                v2 = Integer.valueOf(u2);
            }
            dim(trim, v2);
        }
    }

    public void sota(String str) {
        String trim;
        Object h2;
        String[] T = T(c0(str));
        int length = T.length;
        if (length != 3) {
            if (length == 2 && F(T[1]).equals("re")) {
                Object F = F(T[0]);
                if (F instanceof o.c) {
                    ((o.c) F).g();
                    return;
                }
                return;
            }
            return;
        }
        Object F2 = F(T[0]);
        if (F2 instanceof o.c) {
            Object F3 = F(T[1]);
            if (F3.equals("str")) {
                ((o.c) F2).b(G(T[2]));
                return;
            }
            if (F3.equals("file")) {
                ((o.c) F2).a(new File(I(G(T[2]))));
                return;
            }
            if (F3.equals("bt")) {
                ((o.c) F2).c(n(G(T[2]), ' '));
                return;
            }
            if (F3.equals("bt2")) {
                ((o.c) F2).d((byte[]) F(T[2]));
                return;
            }
            if (F3.equals("ip")) {
                trim = T[2].trim();
                h2 = Boolean.valueOf(((o.c) F2).k());
            } else if (F3.equals("id")) {
                trim = T[2].trim();
                h2 = Integer.valueOf(((o.c) F2).i());
            } else if (F3.equals("socket")) {
                trim = T[2].trim();
                h2 = ((o.c) F2).j();
            } else {
                if (!F3.equals("ht")) {
                    return;
                }
                trim = T[2].trim();
                h2 = ((o.c) F2).h();
            }
            dim(trim, h2);
        }
    }

    public void sql(String str) {
        String trim;
        Object h2;
        boolean j2;
        String str2;
        String[] T = T(c0(str));
        int length = T.length;
        Object F = F(T[0]);
        SQLiteDatabase sQLiteDatabase = F instanceof SQLiteDatabase ? (SQLiteDatabase) F : null;
        if (length == 2) {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.execSQL(G(T[0]));
                return;
            }
            return;
        }
        if (length == 3) {
            if (sQLiteDatabase != null) {
                try {
                    dim(T[2].trim(), sQLiteDatabase.rawQuery(G(T[1]), null));
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    str2 = T[2];
                }
            } else {
                str2 = T[2];
            }
            dim(str2.trim(), null);
            return;
        }
        if (length == 4) {
            Object F2 = F(T[2]);
            if (F2.equals("ip")) {
                trim = T[3].trim();
                j2 = c.b.a.a.n.i(sQLiteDatabase, G(T[1]));
            } else {
                if (!F2.equals("del")) {
                    return;
                }
                trim = T[3].trim();
                j2 = c.b.a.a.n.e(sQLiteDatabase, G(T[1]));
            }
        } else if (length == 5) {
            Object F3 = F(T[2]);
            if (F3.equals("add")) {
                trim = T[4].trim();
                j2 = c.b.a.a.n.b(sQLiteDatabase, G(T[1]), G(T[3]));
            } else {
                if (!F3.equals("del")) {
                    return;
                }
                trim = T[4].trim();
                j2 = c.b.a.a.n.d(sQLiteDatabase, G(T[1]), H(T[3]));
            }
        } else {
            if (length != 6) {
                return;
            }
            Object F4 = F(T[2]);
            if (F4.equals("add")) {
                trim = T[5].trim();
                j2 = c.b.a.a.n.a(sQLiteDatabase, G(T[1]), G(T[3]), G(T[4]));
            } else {
                if (!F4.equals("up")) {
                    if (F4.equals("sele")) {
                        trim = T[5].trim();
                        h2 = c.b.a.a.n.h(sQLiteDatabase, G(T[3]), G(T[1]), H(T[4]));
                        dim(trim, h2);
                    }
                    return;
                }
                trim = T[5].trim();
                j2 = c.b.a.a.n.j(sQLiteDatabase, G(T[1]), G(T[3]), H(T[4]));
            }
        }
        h2 = Boolean.valueOf(j2);
        dim(trim, h2);
    }

    public void sqlite(String str) {
        String trim;
        boolean x2;
        Object valueOf;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            String G = G(T[1]);
            if (G.equals("re")) {
                Object F = F(T[0]);
                if (F instanceof SQLiteDatabase) {
                    ((SQLiteDatabase) F).close();
                    return;
                }
                return;
            }
            if (G.contains("/") || G.contains("\\") || G.startsWith("@") || G.startsWith("$") || G.startsWith("%")) {
                trim = T[0].trim();
                valueOf = c.b.a.a.f.z(this.a, I(G), false);
            } else {
                trim = T[0].trim();
                valueOf = c.b.a.a.f.z(this.a, G, true);
            }
        } else {
            if (length != 3) {
                return;
            }
            Object F2 = F(T[1]);
            if (F2.equals("ip")) {
                String G2 = G(T[0]);
                if (G2.contains("/") || G2.contains("\\") || G2.startsWith("@") || G2.startsWith("$") || G2.startsWith("%")) {
                    trim = T[2].trim();
                    x2 = c.b.a.a.f.y(this.a, I(G2), false);
                } else {
                    trim = T[2].trim();
                    x2 = c.b.a.a.f.y(this.a, G2, true);
                }
            } else {
                if (!F2.equals("del")) {
                    return;
                }
                String G3 = G(T[0]);
                if (G3.contains("/") || G3.contains("\\") || G3.startsWith("@") || G3.startsWith("$") || G3.startsWith("%")) {
                    trim = T[2].trim();
                    x2 = c.b.a.a.f.x(this.a, I(G3), false);
                } else {
                    trim = T[2].trim();
                    x2 = c.b.a.a.f.x(this.a, G3, true);
                }
            }
            valueOf = Boolean.valueOf(x2);
        }
        dim(trim, valueOf);
    }

    public void sqlsele(String str) {
        String trim;
        Object string;
        int position;
        boolean moveToLast;
        String[] T = T(c0(str));
        int length = T.length;
        Object F = F(T[0]);
        Object F2 = F(T[1]);
        Cursor cursor = F instanceof Cursor ? (Cursor) F : null;
        if (length == 2) {
            if (!F2.equals("re") || cursor == null) {
                return;
            }
            cursor.close();
            return;
        }
        if (length == 3) {
            if (F2 instanceof Integer) {
                dim(T[2].trim(), cursor.getString(Integer.parseInt(F2.toString())));
                return;
            }
            if (F2.equals("columncount")) {
                trim = T[2].trim();
                position = cursor.getColumnCount();
            } else {
                if (!F2.equals("count")) {
                    if (F2.equals("next")) {
                        trim = T[2].trim();
                        moveToLast = cursor.moveToNext();
                    } else if (F2.equals("previous")) {
                        trim = T[2].trim();
                        moveToLast = cursor.moveToPrevious();
                    } else if (F2.equals("first")) {
                        trim = T[2].trim();
                        moveToLast = cursor.moveToFirst();
                    } else if (F2.equals("last")) {
                        trim = T[2].trim();
                        moveToLast = cursor.moveToLast();
                    } else if (F2.equals("position")) {
                        cursor.moveToPosition(c(F(T[2]).toString()));
                        return;
                    } else {
                        if (!F2.equals("getposition")) {
                            trim = T[2].trim();
                            string = cursor.getString(c(F2.toString()));
                            dim(trim, string);
                        }
                        trim = T[2].trim();
                        position = cursor.getPosition();
                    }
                    string = Boolean.valueOf(moveToLast);
                    dim(trim, string);
                }
                trim = T[2].trim();
                position = cursor.getCount();
            }
            string = Integer.valueOf(position);
            dim(trim, string);
        }
    }

    public void sr(String str) {
        String trim;
        String G;
        String G2;
        String str2;
        String replaceAll;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 4) {
            trim = T[3].trim();
            G = G(T[0]);
            G2 = G(T[1]);
            str2 = T[2];
        } else {
            if (length != 5) {
                return;
            }
            if (F(T[3]).equals(Boolean.TRUE)) {
                trim = T[4].trim();
                replaceAll = G(T[0]).replaceAll(G(T[1]), G(T[2]));
                dim(trim, replaceAll);
            } else {
                trim = T[4].trim();
                G = G(T[0]);
                G2 = G(T[1]);
                str2 = T[2];
            }
        }
        replaceAll = G.replace(G2, G(str2));
        dim(trim, replaceAll);
    }

    public void sran(String str) {
        String[] T = T(c0(str));
        if (T.length == 3) {
            dim(T[2].trim(), Integer.valueOf(c.b.a.a.p.g(c(G(T[0])), c(G(T[1])))));
        }
    }

    public void ss(String str) {
        String c02 = c0(str);
        int lastIndexOf = c02.lastIndexOf(44);
        String trim = c02.substring(lastIndexOf + 1).trim();
        String str2 = "";
        for (String str3 : Z(c02.substring(0, lastIndexOf).trim(), '+')) {
            Object F = F(str3);
            if (F != null) {
                str2 = str2 + F;
            }
        }
        dim(trim.trim(), str2);
    }

    public void ssg(String str) {
        String str2;
        String[] T = T(c0(str));
        if (T.length == 4) {
            try {
                dim(T[3].trim(), G(T[0]).substring(c(G(T[1])), c(G(T[2]))));
                return;
            } catch (Exception unused) {
                str2 = T[3];
            }
        } else {
            if (T.length != 3) {
                return;
            }
            try {
                dim(T[2].trim(), G(T[0]).substring(c(G(T[1]))));
                return;
            } catch (Exception unused2) {
                str2 = T[2];
            }
        }
        dim(str2.trim(), null);
    }

    public void ssj(String str) {
        String[] T = T(d0(str));
        if (T.length == 2) {
            String u2 = u(str);
            Object F = F(T[0]);
            View w2 = F instanceof View ? (View) F : w(F, T[0]);
            if (w2 != null) {
                Object[] objArr = (Object[]) w2.getTag();
                String G = G(T[1]);
                objArr[2] = J((String) objArr[2], G, u2);
                w2.setTag(objArr);
                k(w2, "<eventItme type=\"" + G + "\">" + u2.replace("<", "&lt;").replace(">", "&gt;") + "</eventItme>", null);
            }
        }
    }

    public void sslist(String str) {
        String[] T = T(c0(str));
        if (T.length == 3) {
            ((ArrayList) F(T[0])).set(c(G(T[1])), F(T[2]));
        }
    }

    public void sssz(String str) {
        String[] T = T(c0(str));
        if (T.length == 3) {
            try {
                Array.set(F(T[0]), c(G(T[1])), F(T[2]));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void stobm(String str) {
        String trim;
        String C;
        String[] T = T(c0(str));
        if (T.length == 3) {
            trim = T[2].trim();
            C = c.b.a.a.p.B(G(T[0]), G(T[1]));
        } else {
            if (T.length != 4) {
                return;
            }
            trim = T[3].trim();
            C = c.b.a.a.p.C(G(T[0]), G(T[1]));
        }
        dim(trim, C);
    }

    public void stop(String str) {
        try {
            Thread.sleep(c(G(T(c0(str))[0])));
        } catch (InterruptedException unused) {
        }
    }

    public void strim(String str) {
        String[] T = T(c0(str));
        dim(T[1].trim(), G(T[0]).trim());
    }

    public void supper(String str) {
        String[] T = T(c0(str));
        dim(T[1].trim(), G(T[0]).toUpperCase());
    }

    public void sutf8to(String str) {
        String trim;
        String E;
        String[] T = T(c0(str));
        if (T.length == 2) {
            trim = T[1].trim();
            E = c.b.a.a.p.D(G(T[0]));
        } else {
            if (T.length != 4) {
                return;
            }
            trim = T[3].trim();
            E = c.b.a.a.p.E(G(T[0]), G(T[1]));
        }
        dim(trim, E);
    }

    public void swh(String str) {
        String trim;
        Context context;
        int x2;
        Context context2;
        int i2;
        String[] T = T(c0(str));
        int i3 = 0;
        String G = G(T[0]);
        if (!G.equals("w")) {
            if (!G.equals("h")) {
                if (G.equals("pxw")) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    this.f1817d.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    trim = T[1].trim();
                    i3 = displayMetrics.widthPixels;
                } else if (G.equals("pxh")) {
                    WindowManager.LayoutParams attributes = this.f1817d.getWindow().getAttributes();
                    DisplayMetrics displayMetrics2 = new DisplayMetrics();
                    this.f1817d.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics2);
                    if ((attributes.flags & 1024) == 1024) {
                        trim = T[1].trim();
                        i3 = displayMetrics2.heightPixels;
                    } else {
                        trim = T[1].trim();
                        i3 = displayMetrics2.heightPixels - D();
                    }
                } else if (G.equals("pxhh")) {
                    DisplayMetrics displayMetrics3 = new DisplayMetrics();
                    this.f1817d.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics3);
                    trim = T[1].trim();
                    i3 = displayMetrics3.heightPixels;
                } else if (G.equals("hh")) {
                    DisplayMetrics displayMetrics4 = new DisplayMetrics();
                    this.f1817d.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics4);
                    trim = T[1].trim();
                    context2 = this.a;
                    i2 = displayMetrics4.heightPixels;
                } else if (G.equals("pxztl")) {
                    trim = T[1].trim();
                    i3 = E();
                } else if (G.equals("pxbvk")) {
                    trim = T[1].trim();
                    i3 = x();
                } else if (G.equals("ztl")) {
                    trim = T[1].trim();
                    context = this.a;
                    x2 = E();
                } else if (G.equals("bvk")) {
                    trim = T[1].trim();
                    context = this.a;
                    x2 = x();
                } else {
                    trim = T[1].trim();
                }
                dim(trim, Integer.valueOf(i3));
            }
            WindowManager.LayoutParams attributes2 = this.f1817d.getWindow().getAttributes();
            DisplayMetrics displayMetrics5 = new DisplayMetrics();
            this.f1817d.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics5);
            if ((attributes2.flags & 1024) == 1024) {
                trim = T[1].trim();
                context = this.a;
                x2 = displayMetrics5.heightPixels;
            } else {
                trim = T[1].trim();
                context = this.a;
                x2 = displayMetrics5.heightPixels - D();
            }
            i3 = c.b.a.a.p.w(context, x2);
            dim(trim, Integer.valueOf(i3));
        }
        DisplayMetrics displayMetrics6 = new DisplayMetrics();
        this.f1817d.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics6);
        trim = T[1].trim();
        context2 = this.a;
        i2 = displayMetrics6.widthPixels;
        i3 = c.b.a.a.p.w(context2, i2);
        dim(trim, Integer.valueOf(i3));
    }

    public void sxb(String str) {
        ((ClipboardManager) this.a.getSystemService("clipboard")).setText(G(T(c0(str))[0]));
    }

    public void syso(String str) {
        c.b.a.a.t.P2(this.a, F(T(c0(str))[0]));
    }

    public void syu(String str) {
        String trim;
        Object valueOf;
        long parseDouble;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            trim = T[1].trim();
            parseDouble = (long) (Double.parseDouble(G(T[1])) % Double.parseDouble(G(T[0])));
        } else {
            if (length != 3) {
                if (length == 4 && F(T[2]).equals(Boolean.TRUE)) {
                    trim = T[3].trim();
                    valueOf = Double.valueOf(Double.parseDouble(G(T[0])) % Double.parseDouble(G(T[1])));
                    dim(trim, valueOf);
                }
                return;
            }
            trim = T[2].trim();
            parseDouble = (long) (Double.parseDouble(G(T[0])) % Double.parseDouble(G(T[1])));
        }
        valueOf = Long.valueOf(parseDouble);
        dim(trim, valueOf);
    }

    public void t(String str) {
        String u2 = u(str);
        d0 d0Var = new d0();
        this.f1816c.put(String.valueOf(d0Var.getId()), u2);
        d0Var.setName("CeShi_" + d0Var.getId());
        d0Var.start();
    }

    public void tcc(String str) {
        String trim;
        Integer num;
        int height;
        String[] T = T(c0(str));
        if (T.length == 3) {
            Object F = F(T[0]);
            if (F instanceof Bitmap) {
                String G = G(T[1]);
                if (G.equals("w")) {
                    trim = T[2].trim();
                    height = ((Bitmap) F).getWidth();
                } else {
                    if (!G.equals("h")) {
                        return;
                    }
                    trim = T[2].trim();
                    height = ((Bitmap) F).getHeight();
                }
                num = Integer.valueOf(height);
            } else {
                trim = T[2].trim();
                num = null;
            }
            dim(trim, num);
        }
    }

    public void tfz(String str) {
        String trim;
        Bitmap bitmap;
        String[] T = T(c0(str));
        if (T.length == 3) {
            Object F = F(T[0]);
            if (F instanceof Bitmap) {
                String G = G(T[1]);
                if (G.equals("x")) {
                    trim = T[2].trim();
                    bitmap = c.b.a.a.i.f((Bitmap) F, 0);
                } else {
                    if (!G.equals("y")) {
                        return;
                    }
                    trim = T[2].trim();
                    bitmap = c.b.a.a.i.f((Bitmap) F, 1);
                }
            } else {
                trim = T[2].trim();
                bitmap = null;
            }
            dim(trim, bitmap);
        }
    }

    public void time(String str) {
        String trim;
        Formatter format;
        String[] T = T(c0(str));
        try {
            dim(T[1].trim(), c.b.a.a.p.u(c(G(T[0]))));
        } catch (NumberFormatException unused) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(new Date().getTime());
            Formatter formatter = new Formatter(Locale.CHINA);
            T[0] = G(T[0]);
            if (T[0].length() == 1) {
                trim = T[1].trim();
                format = formatter.format("%1$t" + T[0], calendar);
            } else {
                trim = T[1].trim();
                format = formatter.format(T[0], calendar);
            }
            dim(trim, format.toString());
        }
    }

    public void tot(String str) {
        String trim;
        String[] T = T(c0(str));
        if (T.length == 2) {
            Object F = F(T[0]);
            View w2 = F instanceof View ? (View) F : w(F, T[0]);
            Bitmap bitmap = null;
            if (w2 == null) {
                dim(T[1].trim(), null);
                return;
            }
            if (w2 instanceof ImageView) {
                trim = T[1].trim();
                bitmap = c.b.a.a.i.a((ImageView) w2);
            } else {
                trim = T[1].trim();
            }
            dim(trim, bitmap);
        }
    }

    public void tsf(String str) {
        String str2;
        Bitmap e2;
        String str3;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 3) {
            Object F = F(T[0]);
            if (!(F instanceof Bitmap)) {
                str2 = T[2];
                dim(str2.trim(), null);
            } else {
                e2 = c.b.a.a.i.d((Bitmap) F, c(G(T[1])));
                str3 = T[2];
                dim(str3.trim(), e2);
            }
        }
        if (length == 4) {
            Object F2 = F(T[0]);
            if (!(F2 instanceof Bitmap)) {
                str2 = T[3];
                dim(str2.trim(), null);
            } else {
                e2 = c.b.a.a.i.e((Bitmap) F2, c(G(T[1])), c(G(T[2])));
                str3 = T[3];
                dim(str3.trim(), e2);
            }
        }
    }

    public void tts(String str) {
        String trim;
        boolean k2;
        String trim2;
        int i2;
        String str2;
        boolean d2;
        Object obj = Boolean.FALSE;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 1) {
            trim2 = T[0].trim();
            obj = new com.iapp.app.r(this.a);
        } else if (length == 2) {
            if (!G(T[1]).equals("re")) {
                return;
            }
            Object F = F(T[0]);
            if (!(F instanceof com.iapp.app.r)) {
                return;
            }
            ((com.iapp.app.r) F).h();
            trim2 = T[0].trim();
            obj = null;
        } else {
            if (length != 3) {
                if (length == 5) {
                    String G = G(T[1]);
                    if (G.equals("st")) {
                        Object F2 = F(T[0]);
                        if (!(F2 instanceof com.iapp.app.r)) {
                            return;
                        }
                        trim = T[4].trim();
                        k2 = ((com.iapp.app.r) F2).i(G(T[2]), Integer.parseInt(G(T[3])));
                    } else {
                        if (!G.equals("ft")) {
                            dim(T[0].trim(), new com.iapp.app.r(this.a, G(T[1]), G(T[2]), Float.parseFloat(G(T[3])), Float.parseFloat(G(T[4]))));
                            return;
                        }
                        Object F3 = F(T[0]);
                        if (!(F3 instanceof com.iapp.app.r)) {
                            return;
                        }
                        trim = T[4].trim();
                        k2 = ((com.iapp.app.r) F3).k(G(T[2]), I(G(T[3])));
                    }
                    dim(trim, Boolean.valueOf(k2));
                    return;
                }
                return;
            }
            Object F4 = F(T[0]);
            String G2 = G(T[1]);
            if (!G2.equals("sp")) {
                if (G2.equals("ip")) {
                    if (F4 instanceof com.iapp.app.r) {
                        trim2 = T[2].trim();
                        d2 = ((com.iapp.app.r) F4).d();
                        obj = Boolean.valueOf(d2);
                    } else {
                        str2 = T[2];
                    }
                } else if (G2.equals("is")) {
                    if (F4 instanceof com.iapp.app.r) {
                        trim2 = T[2].trim();
                        obj = Boolean.TRUE;
                    } else {
                        str2 = T[2];
                    }
                } else {
                    if (G2.equals("ph")) {
                        if (F4 instanceof com.iapp.app.r) {
                            ((com.iapp.app.r) F4).f(Float.parseFloat(G(T[2])));
                            return;
                        }
                        return;
                    }
                    if (G2.equals("se")) {
                        if (F4 instanceof com.iapp.app.r) {
                            ((com.iapp.app.r) F4).g(Float.parseFloat(G(T[2])));
                            return;
                        }
                        return;
                    } else if (G2.equals("lg")) {
                        if (F4 instanceof com.iapp.app.r) {
                            ((com.iapp.app.r) F4).e(G(T[2]));
                            return;
                        }
                        return;
                    } else {
                        if (!G2.equals("zt")) {
                            return;
                        }
                        if (F4 instanceof com.iapp.app.r) {
                            trim2 = T[2].trim();
                            i2 = ((com.iapp.app.r) F4).c();
                        } else {
                            trim2 = T[2].trim();
                            i2 = -3;
                        }
                        obj = Integer.valueOf(i2);
                    }
                }
                trim2 = str2.trim();
            } else if (F4 instanceof com.iapp.app.r) {
                trim2 = T[2].trim();
                d2 = ((com.iapp.app.r) F4).j();
                obj = Boolean.valueOf(d2);
            } else {
                str2 = T[2];
                trim2 = str2.trim();
            }
        }
        dim(trim2, obj);
    }

    public void turnLightOff(Camera camera) {
        Camera.Parameters parameters;
        if (camera == null || (parameters = camera.getParameters()) == null) {
            return;
        }
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        String flashMode = parameters.getFlashMode();
        if (supportedFlashModes == null || "off".equals(flashMode) || !supportedFlashModes.contains("off")) {
            return;
        }
        parameters.setFlashMode("off");
        camera.setParameters(parameters);
    }

    public void turnLightOn(Camera camera) {
        Camera.Parameters parameters;
        List<String> supportedFlashModes;
        if (camera == null || (parameters = camera.getParameters()) == null || (supportedFlashModes = parameters.getSupportedFlashModes()) == null || "torch".equals(parameters.getFlashMode()) || !supportedFlashModes.contains("torch")) {
            return;
        }
        parameters.setFlashMode("torch");
        camera.setParameters(parameters);
    }

    public void tw(String str) {
        Toast makeText;
        String[] T = T(c0(str));
        if (T.length == 1) {
            makeText = Toast.makeText(this.a, G(T[0]), 1);
        } else if (T.length != 2) {
            return;
        } else {
            makeText = Toast.makeText(this.a, G(T[0]), Integer.parseInt(G(T[1])));
        }
        makeText.show();
    }

    public void tws(String str) {
        Snackbar action;
        String[] T = T(d0(str));
        int length = T.length;
        if (length == 3) {
            Object F = F(T[0]);
            View findViewById = F == null ? this.f1817d.findViewById(c.b.a.a.f.a) : F instanceof View ? (View) F : w(F, T[0]);
            if (findViewById == null) {
                return;
            } else {
                action = Snackbar.make(findViewById, G(T[1]), c(G(T[2])));
            }
        } else {
            if (length != 4) {
                return;
            }
            Object F2 = F(T[0]);
            View findViewById2 = F2 == null ? this.f1817d.findViewById(c.b.a.a.f.a) : F2 instanceof View ? (View) F2 : w(F2, T[0]);
            if (findViewById2 == null) {
                return;
            } else {
                action = Snackbar.make(findViewById2, G(T[1]), c(G(T[2]))).setAction(G(T[3]), new e0(u(str)));
            }
        }
        action.show();
    }

    public void tzz(String str) {
        String trim;
        Bitmap bitmap;
        String[] T = T(c0(str));
        if (T.length == 3) {
            Object F = F(T[0]);
            if (F instanceof Bitmap) {
                bitmap = c.b.a.a.i.g((Bitmap) F, c(G(T[1])));
                trim = T[2].trim();
            } else {
                trim = T[2].trim();
                bitmap = null;
            }
            dim(trim, bitmap);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r5v0, types: [com.iapp.app.Aid_YuCodeX] */
    public void uall(String str) {
        String[] T = T(c0(str));
        Object F = F(T[0]);
        if (!(F instanceof ViewGroup)) {
            F = w(F, T[0]);
        }
        ViewGroup viewGroup = (ViewGroup) F;
        boolean equals = F(T[1]).equals(Boolean.TRUE);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            ?? childAt = viewGroup.getChildAt(i2);
            if (!equals) {
                childAt = Integer.valueOf(childAt.getId());
            }
            arrayList.add(childAt);
        }
        dim(T[2].trim(), arrayList);
    }

    public void uapp(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 2) {
            dim(T[1].trim(), Boolean.valueOf(c.b.a.a.d.s(this.a, G(T[0]))));
            return;
        }
        if (length == 3) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(G(T[0]), G(T[1])));
                this.a.startActivity(intent);
                dim(T[2].trim(), Boolean.TRUE);
            } catch (Exception unused) {
                dim(T[2].trim(), Boolean.FALSE);
            }
        }
    }

    public void uapplist(String str) {
        StringBuilder sb;
        String[] T = T(c0(str));
        ArrayList arrayList = new ArrayList();
        boolean equals = F(T[0]).equals(Boolean.TRUE);
        for (PackageInfo packageInfo : this.a.getPackageManager().getInstalledPackages(0)) {
            String str2 = packageInfo.packageName;
            if (equals) {
                sb = new StringBuilder();
            } else if ((1 & packageInfo.applicationInfo.flags) == 0) {
                sb = new StringBuilder();
            }
            sb.append(packageInfo.packageName);
            sb.append("\n");
            sb.append(L(str2));
            sb.append("\n");
            sb.append(packageInfo.applicationInfo.loadLabel(this.a.getPackageManager()).toString());
            sb.append("\n");
            sb.append(packageInfo.versionName);
            arrayList.add(sb.toString());
        }
        dim(T[1].trim(), arrayList.toArray());
    }

    public void uapplistgo(String str) {
        String c02 = c0(str);
        ArrayList arrayList = new ArrayList();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.a.getSystemService("activity")).getRunningAppProcesses()) {
            arrayList.add(runningAppProcessInfo.processName + "\n" + runningAppProcessInfo.pid + "\n" + runningAppProcessInfo.uid);
        }
        dim(c02.trim(), arrayList.toArray());
    }

    public void ucall(String str) {
        String G = G(c0(str));
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DIAL");
        intent.setData(c.b.a.a.k.c(this.a, "tel:" + G));
        this.a.startActivity(intent);
    }

    public void ufnsui(String str) {
        String u2 = u(str);
        Message message = new Message();
        message.what = 2;
        message.obj = u2;
        this.h.sendMessage(message);
    }

    public void ug(String str) {
        String str2;
        String trim;
        Object f2;
        String[] T = T(c0(str));
        Object F = F(T[0]);
        View w2 = F instanceof View ? (View) F : w(F, T[0]);
        if (T.length == 3) {
            if (w2 == null) {
                str2 = T[2];
                dim(str2.trim(), null);
            } else {
                trim = T[2].trim();
                f2 = new com.iapp.app.i(w2, this.a).e(G(T[1]));
                dim(trim, f2);
            }
        }
        if (T.length == 4) {
            if (w2 == null) {
                str2 = T[3];
                dim(str2.trim(), null);
            } else {
                trim = T[3].trim();
                f2 = new com.iapp.app.i(w2, this.a).f(G(T[1]), F(T[2]));
                dim(trim, f2);
            }
        }
    }

    public void uht(String str) {
        com.iapp.app.h hVar;
        int i2;
        boolean z2;
        String[] T = T(c0(str));
        Object F = F(T[0]);
        View w2 = F instanceof View ? (View) F : w(F, T[0]);
        boolean z3 = w2 instanceof ViewPager;
        if (z3 || (w2 instanceof VerticalViewPager)) {
            Object F2 = F(T[1]);
            if (F2.equals("bd")) {
                if (z3) {
                    Object F3 = F(T[2]);
                    View w3 = F3 instanceof View ? (View) F3 : w(F3, T[2]);
                    if (w3 instanceof TabLayout) {
                        ((TabLayout) w3).setupWithViewPager((ViewPager) w2, F(T[3]).equals(Boolean.TRUE));
                        return;
                    }
                    return;
                }
                return;
            }
            PagerAdapter pagerAdapter = null;
            if (z3) {
                pagerAdapter = ((ViewPager) w2).getAdapter();
            } else if (w2 instanceof VerticalViewPager) {
                pagerAdapter = ((VerticalViewPager) w2).getAdapter();
            }
            if (!F2.equals("add")) {
                if (F2.equals("del")) {
                    if (pagerAdapter instanceof FragmentStatePagerAdapter) {
                        ((com.iapp.app.h) pagerAdapter).d(c(G(T[2])));
                        return;
                    }
                    return;
                } else if (F2.equals("title")) {
                    if (pagerAdapter instanceof FragmentStatePagerAdapter) {
                        ((com.iapp.app.h) pagerAdapter).f(c(G(T[2])), G(T[3]));
                        return;
                    }
                    return;
                } else if (F2.equals("size")) {
                    if (pagerAdapter instanceof FragmentStatePagerAdapter) {
                        dim(T[2].trim(), Integer.valueOf(((com.iapp.app.h) pagerAdapter).g()));
                        return;
                    }
                    return;
                } else {
                    if (F2.equals("close") && (pagerAdapter instanceof FragmentStatePagerAdapter)) {
                        ((com.iapp.app.h) pagerAdapter).c();
                        return;
                    }
                    return;
                }
            }
            int length = T.length;
            HashMap<Integer, Object> hashMap = new HashMap<>();
            for (int i3 = 5; i3 < length; i3++) {
                int indexOf = T[i3].indexOf(61);
                if (indexOf != -1) {
                    try {
                        hashMap.put(Integer.valueOf(c(G(T[i3].substring(0, indexOf)))), F(T[i3].substring(indexOf + 1)));
                    } catch (NumberFormatException unused) {
                    }
                }
            }
            if (pagerAdapter == null || !((z2 = pagerAdapter instanceof FragmentStatePagerAdapter))) {
                hVar = new com.iapp.app.h(this.f1817d, w2.getId(), ((AppCompatActivity) this.f1817d).getSupportFragmentManager());
                if (z3) {
                    ViewPager viewPager = (ViewPager) w2;
                    viewPager.setAdapter(hVar);
                    viewPager.setOffscreenPageLimit(5);
                } else if (w2 instanceof VerticalViewPager) {
                    VerticalViewPager verticalViewPager = (VerticalViewPager) w2;
                    verticalViewPager.setAdapter(hVar);
                    verticalViewPager.setOffscreenPageLimit(5);
                }
            } else if (!z2) {
                return;
            } else {
                hVar = (com.iapp.app.h) pagerAdapter;
            }
            String[] b2 = c.b.a.a.q.b(G(T[4]), '|');
            int length2 = b2.length;
            int i4 = 0;
            while (i4 < length2) {
                String trim = b2[i4].trim();
                if (trim.endsWith(".iyu")) {
                    i2 = i4;
                    hVar.b(c(G(T[2])), G(T[3]), trim, m(trim.substring(0, trim.length() - 4)), hashMap);
                } else {
                    i2 = i4;
                }
                i4 = i2 + 1;
            }
        }
    }

    public void uigo(String str) {
        Intent intent;
        Intent intent2;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 1) {
            String G = G(T[0]);
            if (G.endsWith(".iyu")) {
                intent2 = new Intent(this.a, (Class<?>) mian.class);
            } else if (G.endsWith(".ijava")) {
                intent2 = new Intent(this.a, (Class<?>) main3.class);
            } else if (G.endsWith(".ilua")) {
                intent2 = new Intent(this.a, (Class<?>) main.class);
            } else if (!G.endsWith(".ijs")) {
                return;
            } else {
                intent2 = new Intent(this.a, (Class<?>) main2.class);
            }
            intent2.putExtras(c.b.a.a.f.B(G));
            this.a.startActivity(intent2);
            return;
        }
        if (length == 2) {
            String G2 = G(T[0]);
            if (G2.endsWith(".iyu")) {
                intent = new Intent(this.a, (Class<?>) mian.class);
            } else if (G2.endsWith(".ijava")) {
                intent = new Intent(this.a, (Class<?>) main3.class);
            } else if (G2.endsWith(".ilua")) {
                intent = new Intent(this.a, (Class<?>) main.class);
            } else if (!G2.endsWith(".ijs")) {
                return;
            } else {
                intent = new Intent(this.a, (Class<?>) main2.class);
            }
            intent.putExtras(c.b.a.a.f.B(G2));
            intent.setFlags(c(G(T[1])));
            this.a.startActivity(intent);
        }
    }

    public void uit(String str) {
        String[] T = T(c0(str));
        Object F = F(T[0]);
        if (F instanceof Intent) {
            Intent intent = (Intent) F;
            if (G(T[1]).equals("chooser")) {
                this.a.startActivity(Intent.createChooser(intent, G(T[2])));
            } else if (G(T[1]).equals("result")) {
                this.f1817d.startActivityForResult(intent, c(G(T[2])));
            } else {
                this.a.startActivity(intent);
            }
        }
    }

    public void ujp(String str) {
        String[] T = T(c0(str));
        c.b.a.a.r.a(this.f1817d, I(G(T[0])), c(G(T[1])));
    }

    public void ula(String str) {
        Object obj;
        ArrayList<HashMap<Integer, Object>> a2;
        Object obj2;
        String[] T = T(c0(str));
        Object F = F(T[0]);
        int length = T.length;
        if (length == 1) {
            if (!(F instanceof l0) || (obj2 = ((l0) F).b) == null) {
                return;
            }
            if (obj2 instanceof m0) {
                ((m0) obj2).notifyDataSetChanged();
                return;
            } else if (obj2 instanceof ArrayAdapter) {
                ((ArrayAdapter) obj2).notifyDataSetChanged();
                return;
            } else {
                if (obj2 instanceof n0) {
                    ((n0) obj2).notifyDataSetChanged();
                    return;
                }
                return;
            }
        }
        if (length == 2) {
            if (!T[1].contains("=")) {
                Object F2 = F(T[1]);
                if (F2 == null || F2.equals("clear")) {
                    if (!(F instanceof l0) || (obj = ((l0) F).b) == null) {
                        return;
                    }
                    if (obj instanceof m0) {
                        a2 = ((m0) obj).a();
                    } else if (!(obj instanceof n0)) {
                        return;
                    } else {
                        a2 = ((n0) obj).a();
                    }
                    a2.clear();
                    return;
                }
                if ((F instanceof l0) && (F2 instanceof Number)) {
                    Object obj3 = ((l0) F).b;
                    if (obj3 == null || !(obj3 instanceof n0)) {
                        return;
                    }
                    ((n0) obj3).notifyItemChanged(((Number) F2).intValue());
                    return;
                }
            }
        } else if (length == 3 && G(T[1]).equals("list")) {
            dim(T[2].trim(), ((F == null || !(F instanceof l0)) ? new l0(this, null) : (l0) F).a);
            return;
        }
        l0 l0Var = (F == null || !(F instanceof l0)) ? new l0(this, null) : (l0) F;
        HashMap<Integer, Object> hashMap = new HashMap<>();
        for (int i2 = 1; i2 < length; i2++) {
            int indexOf = T[i2].indexOf(61);
            if (indexOf != -1) {
                try {
                    hashMap.put(Integer.valueOf(c(G(T[i2].substring(0, indexOf)))), F(T[i2].substring(indexOf + 1)));
                } catch (NumberFormatException unused) {
                }
            }
        }
        l0Var.a.add(hashMap);
        dim(T[0].trim(), l0Var);
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x01d0, code lost:
    
        if ((r0 instanceof com.iapp.app.Aid_YuCodeX.l0) != false) goto L237;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0174  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void ulag(java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_YuCodeX.ulag(java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00d8, code lost:
    
        if ((r0 instanceof com.iapp.app.Aid_YuCodeX.l0) != false) goto L139;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00df A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void ulas(java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_YuCodeX.ulas(java.lang.String):void");
    }

    public void uls(String str) {
        ArrayAdapter arrayAdapter;
        String[] T = T(c0(str));
        Object F = F(T[0]);
        View w2 = F instanceof View ? (View) F : w(F, T[0]);
        if (w2 == null) {
            return;
        }
        int length = T.length;
        if (w2 instanceof ListView) {
            ListView listView = (ListView) w2;
            Object F2 = F(T[1]);
            if (F2 instanceof l0) {
                l0 l0Var = (l0) F2;
                if (l0Var.b == null) {
                    l0Var.b = new m0(this.f1817d);
                }
                m0 m0Var = (m0) l0Var.b;
                m0Var.b(G(T[2]), l0Var);
                if (length > 4) {
                    m0Var.c(M(G(T[3])), M(G(T[4])));
                }
                listView.setAdapter((ListAdapter) m0Var);
                Object[] objArr = (Object[]) listView.getTag();
                objArr[3] = l0Var;
                listView.setTag(objArr);
                return;
            }
            return;
        }
        if (w2 instanceof GridView) {
            GridView gridView = (GridView) w2;
            Object F3 = F(T[1]);
            if (F3 instanceof l0) {
                l0 l0Var2 = (l0) F3;
                if (l0Var2.b == null) {
                    l0Var2.b = new m0(this.f1817d);
                }
                m0 m0Var2 = (m0) l0Var2.b;
                m0Var2.b(G(T[2]), l0Var2);
                if (length > 4) {
                    m0Var2.c(M(G(T[3])), M(G(T[4])));
                }
                gridView.setAdapter((ListAdapter) m0Var2);
                Object[] objArr2 = (Object[]) gridView.getTag();
                objArr2[3] = l0Var2;
                gridView.setTag(objArr2);
                return;
            }
            return;
        }
        if (w2 instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) w2;
            Object F4 = F(T[1]);
            if (F4 instanceof l0) {
                l0 l0Var3 = (l0) F4;
                if (l0Var3.b == null) {
                    l0Var3.b = new n0(this.f1817d);
                }
                n0 n0Var = (n0) l0Var3.b;
                n0Var.d(G(T[2]), l0Var3);
                if (length > 4) {
                    n0Var.e(M(G(T[3])), M(G(T[4])));
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(this.f1817d));
                recyclerView.setAdapter(n0Var);
                Object[] objArr3 = (Object[]) recyclerView.getTag();
                objArr3[3] = l0Var3;
                recyclerView.setTag(objArr3);
                return;
            }
            return;
        }
        if (!(w2 instanceof TabLayout)) {
            if (w2 instanceof Spinner) {
                Spinner spinner = (Spinner) w2;
                Object F5 = F(T[1]);
                if (F5 instanceof ArrayList) {
                    arrayAdapter = new ArrayAdapter(this.a, R.layout.simple_spinner_item, (ArrayList) F5);
                } else if (F5 instanceof String[]) {
                    arrayAdapter = new ArrayAdapter(this.a, R.layout.simple_spinner_item, (String[]) F5);
                } else if (!(F5 instanceof Object[])) {
                    return;
                } else {
                    arrayAdapter = new ArrayAdapter(this.a, R.layout.simple_spinner_item, (Object[]) F5);
                }
                arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter((SpinnerAdapter) arrayAdapter);
                return;
            }
            return;
        }
        TabLayout tabLayout = (TabLayout) w2;
        Object F6 = F(T[1]);
        if (F6 instanceof l0) {
            c.b.a.a.h hVar = new c.b.a.a.h(this.f1817d, 2);
            String G = G(T[2]);
            int m2 = m(G.substring(0, G.length() - 4));
            Iterator<HashMap<Integer, Object>> it = ((l0) F6).a.iterator();
            while (it.hasNext()) {
                HashMap<Integer, Object> next = it.next();
                LinearLayout linearLayout = new LinearLayout(this.f1817d);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(M(G(T[3])), M(G(T[4]))));
                linearLayout.setOrientation(1);
                openRestoreinterface(G, linearLayout, m2, next);
                tabLayout.addTab(tabLayout.newTab().setCustomView(linearLayout));
                Iterator<Integer> it2 = next.keySet().iterator();
                while (it2.hasNext()) {
                    int parseInt = Integer.parseInt(String.valueOf(it2.next()));
                    if (parseInt > 0) {
                        c.b.a.a.f.w(linearLayout.findViewById(parseInt + m2), next.get(Integer.valueOf(parseInt)), next, hVar);
                    }
                }
            }
        }
    }

    public void uninapp(String str) {
        String[] T = T(c0(str));
        this.a.startActivity(new Intent("android.intent.action.DELETE", c.b.a.a.k.c(this.a, "package:" + G(T[0]))));
    }

    public void uqr(String str) {
        String trim;
        c.a.a.p e2;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 0) {
            new c.b.a.a.m(this.a, this.f1817d).a();
            return;
        }
        if (length == 1) {
            new c.b.a.a.m(this.a, this.f1817d).b(c(G(T[0])));
            return;
        }
        if (length == 3) {
            dim(T[2].trim(), new c.b.a.a.m(this.a, this.f1817d).c(G(T[0]), c(G(T[1]))));
            return;
        }
        if (length == 2) {
            Object F = F(T[0]);
            if (F instanceof Bitmap) {
                trim = T[1].trim();
                e2 = new c.b.a.a.m(this.a, this.f1817d).d((Bitmap) F);
            } else {
                trim = T[1].trim();
                e2 = new c.b.a.a.m(this.a, this.f1817d).e(I(String.valueOf(F)));
            }
            dim(trim, e2.f());
        }
    }

    public void urvw(String str) {
        String[] T = T(c0(str));
        Object F = F(T[0]);
        View w2 = F instanceof View ? (View) F : w(F, T[0]);
        if (w2 != null) {
            ((ViewGroup) w2.getParent()).removeView(w2);
        }
    }

    public void us(String str) {
        Activity activity;
        int parseInt;
        View findViewById;
        String str2;
        String trim;
        boolean h02;
        String[] T = T(c0(str));
        int i2 = 0;
        Object F = F(T[0]);
        if (F instanceof View) {
            findViewById = (View) F;
        } else {
            View w2 = w(F, T[0]);
            int indexOf = T[0].indexOf(46);
            if (indexOf == -1 || T[0].indexOf(34) != -1) {
                String valueOf = String.valueOf(F);
                int indexOf2 = valueOf.indexOf(46);
                if (indexOf2 != -1) {
                    String substring = valueOf.substring(0, indexOf2);
                    String substring2 = valueOf.substring(indexOf2 + 1);
                    Object obj = f1815i.get(substring);
                    if (obj != null) {
                        i2 = Integer.parseInt(String.valueOf(obj));
                        activity = this.f1817d;
                        parseInt = Integer.parseInt(substring2) + i2;
                    }
                    findViewById = w2;
                } else {
                    activity = this.f1817d;
                    parseInt = Integer.parseInt(valueOf);
                }
                findViewById = activity.findViewById(parseInt);
            } else {
                String substring3 = T[0].substring(0, indexOf);
                String substring4 = T[0].substring(indexOf + 1);
                Object obj2 = f1815i.get(substring3);
                if (obj2 != null) {
                    i2 = Integer.parseInt(String.valueOf(obj2));
                    findViewById = this.f1817d.findViewById(Integer.parseInt(substring4) + i2);
                }
                findViewById = w2;
            }
        }
        if (T.length == 3) {
            if (findViewById != null) {
                new com.iapp.app.i(findViewById, this.a, i2).e0(G(T[1]), F(T[2]));
                return;
            }
            return;
        }
        if (T.length == 4) {
            if (findViewById == null) {
                str2 = T[3];
                dim(str2.trim(), null);
            } else {
                trim = T[3].trim();
                h02 = new com.iapp.app.i(findViewById, this.a, i2).e0(G(T[1]), F(T[2]));
                dim(trim, Boolean.valueOf(h02));
            }
        }
        if (T.length == 5) {
            if (findViewById == null) {
                str2 = T[4];
                dim(str2.trim(), null);
            } else {
                trim = T[4].trim();
                h02 = new com.iapp.app.i(findViewById, this.a, i2).f0(G(T[1]), G(T[2]), G(T[3]));
                dim(trim, Boolean.valueOf(h02));
            }
        }
        if (T.length == 6) {
            if (findViewById == null) {
                str2 = T[5];
                dim(str2.trim(), null);
            } else {
                trim = T[5].trim();
                h02 = new com.iapp.app.i(findViewById, this.a, i2).g0(G(T[1]), G(T[2]), G(T[3]), G(T[4]));
                dim(trim, Boolean.valueOf(h02));
            }
        }
        if (T.length == 7) {
            if (findViewById == null) {
                str2 = T[6];
                dim(str2.trim(), null);
            } else {
                trim = T[6].trim();
                h02 = new com.iapp.app.i(findViewById, this.a, i2).h0(G(T[1]), G(T[2]), G(T[3]), G(T[4]), G(T[5]));
                dim(trim, Boolean.valueOf(h02));
            }
        }
    }

    public void usg(String str) {
        String[] T = T(c0(str));
        if (T.length == 2) {
            T[0] = T[0].trim();
            try {
                if (F(T[1]).equals(Boolean.TRUE)) {
                    Object F = F(T[0]);
                    if (F instanceof Camera) {
                        ((Camera) F).release();
                    }
                    Camera open = Camera.open();
                    dim(T[0], open);
                    turnLightOn(open);
                    return;
                }
                Object F2 = F(T[0]);
                if (F2 instanceof Camera) {
                    Camera camera = (Camera) F2;
                    turnLightOff(camera);
                    camera.release();
                    dim(T[0], null);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void ushsp(String str) {
        int i2 = 0;
        if (!F(T(c0(str))[0]).equals(Boolean.TRUE)) {
            i2 = 1;
            if (this.f1817d.getRequestedOrientation() == 1) {
                return;
            }
        } else if (this.f1817d.getRequestedOrientation() == 0) {
            return;
        }
        this.f1817d.setRequestedOrientation(i2);
    }

    public void usjxm(String str) {
        if (F(T(c0(str))[0]).equals(Boolean.TRUE)) {
            this.f1817d.getWindow().clearFlags(128);
        } else {
            this.f1817d.getWindow().setFlags(128, 128);
        }
    }

    public void usms(String str) {
        String[] T = T(c0(str));
        Intent intent = new Intent("android.intent.action.SENDTO", c.b.a.a.k.c(this.a, "smsto:" + G(T[0])));
        intent.putExtra("sms_body", G(T[1]));
        this.a.startActivity(intent);
    }

    public void usx(String str) {
        Boolean bool = Boolean.TRUE;
        String[] T = T(c0(str));
        Object F = F(T[0]);
        if (F instanceof com.iapp.app.m) {
            com.iapp.app.m mVar = (com.iapp.app.m) F;
            Object F2 = F(T[1]);
            if (F2.equals("shot")) {
                mVar.q(I(G(T[2])), c(G(T[3])), F(T[4]).equals(bool));
                return;
            }
            if (F2.equals("st")) {
                mVar.y();
                return;
            }
            if (F2.equals("sp")) {
                mVar.z();
                return;
            }
            if (F2.equals("re")) {
                mVar.u();
                return;
            }
            if (F2.equals("rotaing")) {
                mVar.w(c(G(T[2])));
            } else if (F2.equals("getrotaing")) {
                dim(T[2].trim(), Integer.valueOf(mVar.r()));
            } else if (F2.equals("usg")) {
                mVar.x(F(T[2]).equals(bool));
            }
        }
    }

    public void usxh(String str) {
        String str2;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 3) {
            Object F = F(T[1]);
            View w2 = F instanceof View ? (View) F : w(F, T[1]);
            if (w2 instanceof SurfaceView) {
                dim(T[0].trim(), new com.iapp.app.m(this.a, this.f1817d, (SurfaceView) w2, false, c(G(T[2]))));
                return;
            }
            str2 = T[0];
        } else {
            if (length != 6) {
                return;
            }
            Object F2 = F(T[1]);
            View w3 = F2 instanceof View ? (View) F2 : w(F2, T[1]);
            if (w3 instanceof SurfaceView) {
                dim(T[0].trim(), new com.iapp.app.m(this.a, this.f1817d, (SurfaceView) w3, false, c(G(T[2])), c(G(T[3])), c(G(T[4])), c(G(T[5]))));
                return;
            }
            str2 = T[0];
        }
        dim(str2.trim(), null);
    }

    public void usxq(String str) {
        String str2;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 3) {
            Object F = F(T[1]);
            View w2 = F instanceof View ? (View) F : w(F, T[1]);
            if (w2 instanceof SurfaceView) {
                dim(T[0].trim(), new com.iapp.app.m(this.a, this.f1817d, (SurfaceView) w2, true, c(G(T[2]))));
                return;
            }
            str2 = T[0];
        } else {
            if (length != 6) {
                return;
            }
            Object F2 = F(T[1]);
            View w3 = F2 instanceof View ? (View) F2 : w(F2, T[1]);
            if (w3 instanceof SurfaceView) {
                dim(T[0].trim(), new com.iapp.app.m(this.a, this.f1817d, (SurfaceView) w3, true, c(G(T[2])), c(G(T[3])), c(G(T[4])), c(G(T[5]))));
                return;
            }
            str2 = T[0];
        }
        dim(str2.trim(), null);
    }

    public void utb(String str) {
        String trim;
        int height;
        Object valueOf;
        CharSequence subtitle;
        Boolean bool = Boolean.TRUE;
        String[] T = T(d0(str));
        int length = T.length;
        if (length == 1) {
            Object F = F(T[0]);
            View w2 = F instanceof View ? (View) F : w(F, T[0]);
            if (w2 == null || !(w2 instanceof Toolbar)) {
                return;
            }
            ((AppCompatActivity) this.f1817d).setSupportActionBar((Toolbar) w2);
            return;
        }
        if (length == 2) {
            Object F2 = F(T[0]);
            View w3 = F2 instanceof View ? (View) F2 : w(F2, T[0]);
            if (w3 == null || !(w3 instanceof Toolbar)) {
                return;
            }
            Object F3 = F(T[1]);
            View w4 = F3 instanceof View ? (View) F3 : w(F3, T[1]);
            if (w4 == null || !(w4 instanceof DrawerLayout)) {
                return;
            }
            ((AppCompatActivity) this.f1817d).getSupportActionBar().setHomeButtonEnabled(true);
            DrawerLayout drawerLayout = (DrawerLayout) w4;
            String obj = ((Object[]) drawerLayout.getTag())[2].toString();
            f0 f0Var = new f0(this.f1817d, drawerLayout, (Toolbar) w3, 0, 0, c.b.a.a.p.c(obj, "<eventItme type=\"ondrawerclosed\">", "</eventItme>"), drawerLayout, c.b.a.a.p.c(obj, "<eventItme type=\"$__tag_namexml_\">", "</eventItme>"), c.b.a.a.p.c(obj, "<eventItme type=\"ondraweropened\">", "</eventItme>"), c.b.a.a.p.c(obj, "<eventItme type=\"onoptionsitemselected\">", "</eventItme>"));
            drawerLayout.setDrawerListener(f0Var);
            f0Var.syncState();
            return;
        }
        if (length == 3) {
            Object F4 = F(T[0]);
            Object F5 = F(T[1]);
            if (F4.equals("get")) {
                if (F5.equals("sab")) {
                    trim = T[2].trim();
                    valueOf = ((AppCompatActivity) this.f1817d).getSupportActionBar();
                } else {
                    if (F5.equals("title")) {
                        trim = T[2].trim();
                        subtitle = ((AppCompatActivity) this.f1817d).getSupportActionBar().getTitle();
                    } else if (F5.equals("subtitle")) {
                        trim = T[2].trim();
                        subtitle = ((AppCompatActivity) this.f1817d).getSupportActionBar().getSubtitle();
                    } else if (F5.equals("cv")) {
                        trim = T[2].trim();
                        valueOf = ((AppCompatActivity) this.f1817d).getSupportActionBar().getCustomView();
                    } else {
                        if (F5.equals("do")) {
                            trim = T[2].trim();
                            height = ((AppCompatActivity) this.f1817d).getSupportActionBar().getDisplayOptions();
                        } else {
                            if (!F5.equals("height")) {
                                return;
                            }
                            trim = T[2].trim();
                            height = ((AppCompatActivity) this.f1817d).getSupportActionBar().getHeight();
                        }
                        valueOf = Integer.valueOf(height);
                    }
                    valueOf = String.valueOf(subtitle);
                }
                dim(trim, valueOf);
                return;
            }
            if (F4.equals("set")) {
                if (F5.equals("title")) {
                    ((AppCompatActivity) this.f1817d).getSupportActionBar().setTitle(G(T[2]));
                }
                if (F5.equals("subtitle")) {
                    ((AppCompatActivity) this.f1817d).getSupportActionBar().setSubtitle(G(T[2]));
                    return;
                }
                if (F5.equals("cv")) {
                    ((AppCompatActivity) this.f1817d).getSupportActionBar().setCustomView((View) F(T[2]));
                    return;
                }
                if (F5.equals("do")) {
                    ((AppCompatActivity) this.f1817d).getSupportActionBar().setDisplayOptions(c(G(T[2])));
                    return;
                }
                if (F5.equals("dste")) {
                    ((AppCompatActivity) this.f1817d).getSupportActionBar().setDisplayShowTitleEnabled(F(T[2]).equals(bool));
                    return;
                }
                if (F5.equals("dsce")) {
                    ((AppCompatActivity) this.f1817d).getSupportActionBar().setDisplayShowCustomEnabled(F(T[2]).equals(bool));
                    return;
                }
                if (F5.equals("dshe")) {
                    boolean equals = F(T[2]).equals(bool);
                    ((AppCompatActivity) this.f1817d).getSupportActionBar().setHomeButtonEnabled(equals);
                    ((AppCompatActivity) this.f1817d).getSupportActionBar().setDisplayShowHomeEnabled(equals);
                    ((AppCompatActivity) this.f1817d).getSupportActionBar().setDisplayHomeAsUpEnabled(equals);
                    return;
                }
                if (!F5.equals("leftck")) {
                    return;
                }
                Object F6 = F(T[2]);
                View w5 = F6 instanceof View ? (View) F6 : w(F6, T[2]);
                if (w5 == null || !(w5 instanceof Toolbar)) {
                    return;
                } else {
                    ((Toolbar) w5).setNavigationOnClickListener(new g0(u(str)));
                }
            } else {
                if (!F4.equals("left")) {
                    if (F4.equals("right")) {
                        View w6 = F5 instanceof View ? (View) F5 : w(F5, T[1]);
                        if (w6 == null || !(w6 instanceof Toolbar)) {
                            return;
                        }
                        ((Toolbar) w6).setOverflowIcon(com.iapp.app.i.m(G(T[2]), this.a));
                        return;
                    }
                    return;
                }
                View w7 = F5 instanceof View ? (View) F5 : w(F5, T[1]);
                if (w7 == null || !(w7 instanceof Toolbar)) {
                    return;
                } else {
                    ((Toolbar) w7).setNavigationIcon(com.iapp.app.i.m(G(T[2]), this.a));
                }
            }
            ((AppCompatActivity) this.f1817d).getSupportActionBar().setHomeButtonEnabled(true);
            ((AppCompatActivity) this.f1817d).getSupportActionBar().setDisplayShowHomeEnabled(true);
            ((AppCompatActivity) this.f1817d).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x02ff, code lost:
    
        if (F(r3[3]).equals(r2) != false) goto L304;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01f9, code lost:
    
        if (F(r3[5]).equals(r2) != false) goto L304;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0312, code lost:
    
        r0 = r0.create();
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0301, code lost:
    
        r0 = r0.create();
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0288, code lost:
    
        if (F(r3[4]).equals(r2) != false) goto L304;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void utw(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 796
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_YuCodeX.utw(java.lang.String):void");
    }

    public void uxf(String str) {
        View view;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 1) {
            Object F = F(T[0]);
            view = F instanceof View ? (View) F : null;
            if (view != null) {
                ((WindowManager) this.a.getApplicationContext().getSystemService("window")).updateViewLayout(view, view.getLayoutParams());
                return;
            }
            return;
        }
        if (length == 2) {
            if (F(T[1]).equals("del")) {
                Object F2 = F(T[0]);
                view = F2 instanceof View ? (View) F2 : null;
                if (view != null) {
                    ((WindowManager) this.a.getApplicationContext().getSystemService("window")).removeView(view);
                    return;
                }
                return;
            }
            return;
        }
        try {
            if (length == 5) {
                String G = G(T[0]);
                LinearLayout linearLayout = new LinearLayout(this.a);
                linearLayout.setOrientation(1);
                int m2 = m(G.substring(0, G.length() - 4));
                linearLayout.setId(m2);
                openRestoreinterface(G, linearLayout, m2, null);
                View childAt = linearLayout.getChildAt(0);
                linearLayout.removeAllViews();
                c.b.a.a.r.e(this.a, childAt, 0, 0, c(G(T[1])), c(G(T[2])), 0, com.iapp.app.i.t(G(T[3])), 0, 0);
                dim(T[4].trim(), childAt);
            } else {
                if (length == 7) {
                    if (F(T[1]).equals("set")) {
                        Object F3 = F(T[0]);
                        view = F3 instanceof View ? (View) F3 : null;
                        if (view != null) {
                            WindowManager windowManager = (WindowManager) this.a.getApplicationContext().getSystemService("window");
                            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
                            layoutParams.x = (int) Float.parseFloat(G(T[2]));
                            layoutParams.y = (int) Float.parseFloat(G(T[3]));
                            int c2 = c(G(T[4]));
                            if (c2 == 0) {
                                layoutParams.width = -2;
                            } else {
                                layoutParams.width = c2;
                            }
                            int c3 = c(G(T[5]));
                            if (c3 == 0) {
                                layoutParams.height = -2;
                            } else {
                                layoutParams.height = c3;
                            }
                            layoutParams.gravity = com.iapp.app.i.t(G(T[6]));
                            view.setLayoutParams(layoutParams);
                            windowManager.updateViewLayout(view, layoutParams);
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (length != 10) {
                    return;
                }
                Object F4 = F(T[0]);
                if (F4 instanceof View) {
                    if (F(T[1]).equals("set")) {
                        View view2 = (View) F4;
                        WindowManager windowManager2 = (WindowManager) this.a.getApplicationContext().getSystemService("window");
                        WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) view2.getLayoutParams();
                        layoutParams2.x = (int) Float.parseFloat(G(T[2]));
                        layoutParams2.y = (int) Float.parseFloat(G(T[3]));
                        int c4 = c(G(T[4]));
                        if (c4 == 0) {
                            layoutParams2.width = -2;
                        } else {
                            layoutParams2.width = c4;
                        }
                        int c5 = c(G(T[5]));
                        if (c5 == 0) {
                            layoutParams2.height = -2;
                        } else {
                            layoutParams2.height = c5;
                        }
                        int c6 = c(G(T[6]));
                        if (c6 == 0) {
                            c6 = c.b.a.a.r.f();
                        }
                        layoutParams2.type = c6;
                        layoutParams2.gravity = com.iapp.app.i.t(G(T[7]));
                        int c7 = c(G(T[8]));
                        if (c7 == 0) {
                            c7 = 40;
                        } else if (c7 == 1) {
                            c7 = 32;
                        }
                        layoutParams2.flags = c7;
                        int c8 = c(G(T[9]));
                        if (c8 == 0) {
                            c8 = -3;
                        }
                        layoutParams2.format = c8;
                        view2.setLayoutParams(layoutParams2);
                        windowManager2.updateViewLayout(view2, layoutParams2);
                        return;
                    }
                    return;
                }
                String valueOf = String.valueOf(F4);
                LinearLayout linearLayout2 = new LinearLayout(this.a);
                linearLayout2.setOrientation(1);
                int m3 = m(valueOf.substring(0, valueOf.length() - 4));
                linearLayout2.setId(m3);
                openRestoreinterface(valueOf, linearLayout2, m3, null);
                View childAt2 = linearLayout2.getChildAt(0);
                linearLayout2.removeAllViews();
                c.b.a.a.r.e(this.a, childAt2, (int) Float.parseFloat(G(T[1])), (int) Float.parseFloat(G(T[2])), c(G(T[3])), c(G(T[4])), c(G(T[5])), com.iapp.app.i.t(G(T[6])), c(G(T[7])), c(G(T[8])));
                dim(T[9].trim(), childAt2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void uycl(String str) {
        Boolean bool = Boolean.TRUE;
        String[] T = T(c0(str));
        int length = T.length;
        if (length == 1) {
            if (!F(T[0]).equals(bool)) {
                this.f1817d.getWindow().addFlags(2048);
                return;
            } else {
                this.f1817d.getWindow().clearFlags(2048);
                this.f1817d.getWindow().setFlags(1024, 1024);
                return;
            }
        }
        if (length == 2) {
            c.b.a.a.r.c(this.f1817d, c.b.a.a.p.o(G(T[0])), F(T[1]).equals(bool), this.f1817d.findViewById(c.b.a.a.f.a));
        } else if (length == 3) {
            c.b.a.a.r.d(this.f1817d, c.b.a.a.p.o(G(T[0])), F(T[1]).equals(bool), this.f1817d.findViewById(c.b.a.a.f.a), c(G(T[2])));
        }
    }

    @TargetApi(11)
    public void uzd(String str) {
        Vibrator vibrator;
        String trim;
        Object obj;
        String[] T = T(c0(str));
        int length = T.length;
        Object F = F(T[0]);
        if (F instanceof Vibrator) {
            vibrator = (Vibrator) F;
        } else {
            vibrator = (Vibrator) this.a.getSystemService("vibrator");
            dim(T[0].trim(), vibrator);
        }
        if (length == 2) {
            if (!F(T[1]).equals("sp")) {
                vibrator.vibrate(c(String.valueOf(r0)));
                return;
            } else {
                try {
                    vibrator.cancel();
                } catch (Exception unused) {
                }
                trim = T[0].trim();
                obj = null;
            }
        } else {
            if (length != 3) {
                return;
            }
            Object F2 = F(T[1]);
            if (!F2.equals("ip")) {
                int length2 = Z(String.valueOf(F2), ' ').length;
                long[] jArr = new long[length2];
                for (int i2 = 0; i2 < length2; i2++) {
                    try {
                        jArr[i2] = Integer.parseInt(r0[i2]);
                    } catch (NumberFormatException unused2) {
                        jArr[i2] = 0;
                    }
                }
                vibrator.vibrate(jArr, F(T[2]).equals(Boolean.TRUE) ? 1 : -1);
                return;
            }
            if (Build.VERSION.SDK_INT >= 11) {
                trim = T[2].trim();
                obj = Boolean.valueOf(vibrator.hasVibrator());
            } else {
                trim = T[2].trim();
                obj = Boolean.FALSE;
            }
        }
        dim(trim, obj);
    }

    public int w(String str) {
        String d02 = d0(str);
        String u2 = u(str);
        while (b(d02)) {
            int YuGo = YuGo(u2);
            if (YuGo == 2) {
                return 2;
            }
            if (YuGo == 1) {
                break;
            }
        }
        return 0;
    }

    public void yul(String str) {
        View p2;
        String[] T = T(c0(str));
        if (T.length == 2) {
            Object F = F(T[0]);
            if (String.valueOf(F).endsWith(".yul")) {
                dim(T[1].trim(), c.b.a.a.f.p(this.a, String.valueOf(F)));
                return;
            }
            Object F2 = F(T[1]);
            if (String.valueOf(F2).endsWith(".yul")) {
                View w2 = F instanceof View ? (View) F : w(F, T[0]);
                if (w2 == null || (p2 = c.b.a.a.f.p(this.a, String.valueOf(F2))) == null) {
                    return;
                }
                ((ViewGroup) w2).addView(p2);
            }
        }
    }

    public void zdp(String str) {
        dim(T(c0(str))[1].trim(), Integer.valueOf(c.b.a.a.p.l(this.a, c(G(r3[0])))));
    }

    public void zj(String str) {
        String[] T = T(c0(str));
        int length = T.length;
        if (length > 1) {
            Object[] objArr = new Object[length];
            objArr[0] = "Activity";
            objArr[1] = this.a;
            if (length > 2) {
                for (int i2 = 2; i2 < length; i2++) {
                    objArr[i2] = F(T[i2]);
                }
            }
            dim(T[0].trim(), c.b.a.a.f.n(G(T[1]), objArr));
        }
    }

    public void zpd(String str) {
        dim(T(c0(str))[1].trim(), Integer.valueOf(c.b.a.a.p.w(this.a, c(G(r3[0])))));
    }

    public void zps(String str) {
        dim(T(c0(str))[1].trim(), Integer.valueOf(c.b.a.a.p.x(this.a, c(G(r3[0])))));
    }

    public void zsp(String str) {
        dim(T(c0(str))[1].trim(), Integer.valueOf(c.b.a.a.p.A(this.a, c(G(r3[0])))));
    }
}
