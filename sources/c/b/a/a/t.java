package c.b.a.a;

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
import android.webkit.WebView;
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
import com.iapp.app.Aid_YuCodeX;
import com.iapp.app.Aid_javaCode;
import com.iapp.app.Aid_jsCode;
import com.iapp.app.Aid_luaCode;
import com.iapp.app.DownList;
import com.iapp.app.Videoview;
import com.iapp.app.Webview;
import com.iapp.app.iapp;
import com.iapp.app.logoActivity;
import com.iapp.app.run.main;
import com.iapp.app.run.main2;
import com.iapp.app.run.main3;
import com.iapp.app.run.mian;
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
public class t {
    public static HashMap<String, Object> k;

    /* renamed from: c */
    private Activity f1358c;

    /* renamed from: d */
    private Context f1359d;
    private int e;

    /* renamed from: i */
    private Handler f1360i;
    public static HashMap<String, Object> j = new HashMap<>();
    public static HashMap<String, Object> l = new HashMap<>();
    public static String m = "\n\niAppV3.0 2018版 -数据调试工具";
    public static AlertDialog n = null;
    public static int o = 3;
    private static HashMap<String, Object> p = new HashMap<>();
    private static int q = 0;
    private static HashMap<Integer, String> r = new HashMap<>();
    private static t s = null;
    public static String[] t = null;
    public static int u = 0;
    private HashMap<String, Object> a = new HashMap<>();
    private HashMap<String, Object> b = new HashMap<>();
    private int g = -1;
    private t h = null;
    private int f = Z();

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
                t.this.e(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public class a0 implements AppBarLayout.OnOffsetChangedListener {
        final /* synthetic */ String a;

        a0(String str) {
            this.a = str;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        public void onOffsetChanged(AppBarLayout appBarLayout, int i2) {
            if (this.a != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(appBarLayout.getId()));
                tVar.T("st_vW", appBarLayout);
                tVar.T("st_vO", Integer.valueOf(i2));
                tVar.f(this.a);
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
                t.this.e(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b0 implements com.iapp.app.x5.a {
        b0(t tVar) {
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
                t.this.e(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public class c0 extends Thread {
        c0() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            t tVar = t.this;
            tVar.e(tVar.b.get(String.valueOf(getId())).toString());
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
                t.this.e(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public class d0 implements View.OnClickListener {
        final /* synthetic */ String a;

        d0(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            t.this.e(this.a);
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
                t.this.e(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public class e0 extends ActionBarDrawerToggle {
        final /* synthetic */ String l;
        final /* synthetic */ DrawerLayout m;
        final /* synthetic */ String n;
        final /* synthetic */ String o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e0(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i2, int i3, String str, DrawerLayout drawerLayout2, String str2, String str3) {
            super(activity, drawerLayout, toolbar, i2, i3);
            this.l = str;
            this.m = drawerLayout2;
            this.n = str2;
            this.o = str3;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(View view) {
            if (this.l != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(this.m.getId()));
                tVar.T("st_vW", this.m);
                tVar.T("st_dW", view);
                tVar.f(this.l);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(View view) {
            if (this.n != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(this.m.getId()));
                tVar.T("st_vW", this.m);
                tVar.T("st_dW", view);
                tVar.f(this.n);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            if (this.o == null) {
                return false;
            }
            t tVar = new t(t.this.f1359d, t.this.f1358c);
            tVar.T("st_vId", Integer.valueOf(this.m.getId()));
            tVar.T("st_vW", this.m);
            tVar.T("st_iM", menuItem);
            tVar.f(this.o);
            return false;
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
                t.this.e(str);
            }
        }
    }

    /* loaded from: classes.dex */
    public class f0 implements View.OnClickListener {
        final /* synthetic */ String a;

        f0(String str) {
            this.a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            t.this.e(this.a);
        }
    }

    /* loaded from: classes.dex */
    public class g implements View.OnClickListener {
        g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            t tVar = new t(t.this.f1359d, t.this.f1358c);
            tVar.T("st_vId", Integer.valueOf(view.getId()));
            tVar.T("st_vW", view);
            tVar.f(c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"clicki\">", "</eventItme>"));
        }
    }

    /* loaded from: classes.dex */
    public class g0 extends WebChromeClient {
        g0() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            t.P2(t.this.f1359d, "JsErr：\nLine" + consoleMessage.lineNumber() + ": " + consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* loaded from: classes.dex */
    public class h implements View.OnTouchListener {
        h() {
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            t tVar = new t(t.this.f1359d, t.this.f1358c);
            tVar.T("st_vId", Integer.valueOf(view.getId()));
            tVar.T("st_vW", view);
            tVar.T("st_eA", Integer.valueOf(motionEvent.getAction()));
            tVar.T("st_eX", Float.valueOf(motionEvent.getX()));
            tVar.T("st_eY", Float.valueOf(motionEvent.getY()));
            tVar.T("st_rX", Float.valueOf(motionEvent.getRawX()));
            tVar.T("st_rY", Float.valueOf(motionEvent.getRawY()));
            return t.this.U0(tVar, "touchmonitor", ((Object[]) view.getTag())[2].toString());
        }
    }

    /* loaded from: classes.dex */
    public class h0 implements OnMessagesListener {
        final /* synthetic */ String a;

        h0(String str) {
            this.a = str;
        }

        @Override // com.iapp.interfaces.OnMessagesListener
        public void Message(Object obj, o.c cVar) {
            t tVar = t.this;
            tVar.T("st_msG", tVar.G0(obj));
            t.this.T("st_ssR", cVar);
            t.this.f(this.a.toString());
        }
    }

    /* loaded from: classes.dex */
    public class i implements View.OnLongClickListener {
        i() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            t tVar = new t(t.this.f1359d, t.this.f1358c);
            tVar.T("st_vId", Integer.valueOf(view.getId()));
            tVar.T("st_vW", view);
            return t.this.U0(tVar, "press", ((Object[]) view.getTag())[2].toString());
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
            t tVar = t.this;
            tVar.T("st_msG", tVar.G0(obj));
            t.this.T("st_ssR", cVar);
            t.this.f(this.a.toString());
        }
    }

    /* loaded from: classes.dex */
    public class j implements View.OnKeyListener {
        j() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            t tVar = new t(t.this.f1359d, t.this.f1358c);
            tVar.T("st_vId", Integer.valueOf(view.getId()));
            tVar.T("st_vW", view);
            tVar.T("st_kC", Integer.valueOf(i2));
            tVar.T("st_eA", Integer.valueOf(keyEvent.getAction()));
            tVar.T("st_eR", Integer.valueOf(keyEvent.getRepeatCount()));
            return t.this.U0(tVar, "keyboard", ((Object[]) view.getTag())[2].toString());
        }
    }

    /* loaded from: classes.dex */
    public class j0 implements OnFileDownStatusListener {
        j0() {
        }

        @Override // com.iapp.interfaces.OnFileDownStatusListener
        public void complete(int i2, Object obj) {
            if (obj == null) {
                return;
            }
            t.this.T("st_drJ", Integer.valueOf(i2));
            t.this.f(obj.toString());
        }

        @Override // com.iapp.interfaces.OnFileDownStatusListener
        public void resultStatus(int i2, int i3, Object obj) {
            if (obj == null) {
                return;
            }
            t.this.T("st_drD", Integer.valueOf(i2));
            t.this.T("st_drI", Integer.valueOf(i3));
            t.this.f(obj.toString());
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
                Toast.makeText(t.this.f1359d, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                t.this.e(message.obj.toString());
            }
        }
    }

    /* loaded from: classes.dex */
    public class k0 {
        public ArrayList<HashMap<Integer, Object>> a;
        public Object b;

        private k0(t tVar) {
            this.a = new ArrayList<>();
            this.b = null;
        }

        /* synthetic */ k0(t tVar, k kVar) {
            this(tVar);
        }
    }

    /* loaded from: classes.dex */
    public class l implements View.OnCreateContextMenuListener {
        l() {
        }

        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            String c2 = c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"pressmenu\">", "</eventItme>");
            contextMenu.setHeaderTitle(c.b.a.a.p.c(c2, "title:", "\n"));
            t.r.clear();
            String[] split = c2.split("\ncase ");
            for (int i2 = 1; i2 < split.length; i2++) {
                contextMenu.add(0, i2, 0, c.b.a.a.p.c(split[i2], null, ":"));
                t.r.put(Integer.valueOf(i2), c.b.a.a.p.c(split[i2], ":", "\nbreak"));
            }
            String c3 = c.b.a.a.p.c(c2, "\ndefault:", "\nbreak");
            t unused = t.s = new t(t.this.f1359d, t.this.f1358c);
            t.s.T("st_vId", Integer.valueOf(view.getId()));
            t.s.T("st_vW", view);
            if (c3 != null) {
                t.s.f(c3);
            }
        }
    }

    /* loaded from: classes.dex */
    public class l0 extends BaseAdapter {
        private Context a;
        private c.b.a.a.h b;

        /* renamed from: c */
        private k0 f1361c;
        private int g;
        private Iterator j;
        private a k;

        /* renamed from: d */
        private String f1362d = null;
        private String e = null;
        private int f = 0;
        private int h = -1;

        /* renamed from: i */
        private int f1363i = -2;
        private t l = null;
        private ArrayList<Integer> m = null;

        /* loaded from: classes.dex */
        private final class a {
            public ViewGroup a;
            public int[] b;

            /* renamed from: c */
            public View[] f1364c;

            private a(l0 l0Var) {
                this.a = null;
                this.b = null;
                this.f1364c = null;
            }

            /* synthetic */ a(l0 l0Var, k kVar) {
                this(l0Var);
            }
        }

        public l0(Context context) {
            this.b = new c.b.a.a.h(context, this, 1);
            this.a = context;
        }

        public ArrayList<HashMap<Integer, Object>> a() {
            return this.f1361c.a;
        }

        public void b(String str, k0 k0Var) {
            this.f1362d = str;
            this.f1361c = k0Var;
            this.f = t.this.r(str.substring(0, str.length() - 4));
            this.e = t.this.Q0(str);
        }

        public void c(int i2, int i3) {
            this.h = i2;
            this.f1363i = i3;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            k0 k0Var = this.f1361c;
            if (k0Var == null) {
                return 0;
            }
            return k0Var.a.size();
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
            HashMap<Integer, Object> hashMap = this.f1361c.a.get(i2);
            if (view == null) {
                this.k = new a(this, null);
                LinearLayout linearLayout = new LinearLayout(this.a);
                linearLayout.setLayoutParams(new AbsListView.LayoutParams(this.h, this.f1363i));
                linearLayout.setOrientation(1);
                LinearLayout linearLayout2 = new LinearLayout(this.a);
                linearLayout2.setLayoutParams(new ViewGroup.LayoutParams(this.h, this.f1363i));
                linearLayout2.setOrientation(1);
                linearLayout.addView(linearLayout2);
                t.this.J1(this.f1362d, linearLayout2, this.f, hashMap);
                this.j = hashMap.keySet().iterator();
                ArrayList arrayList = new ArrayList();
                while (this.j.hasNext()) {
                    int parseInt = Integer.parseInt(String.valueOf(this.j.next()));
                    this.g = parseInt;
                    if (parseInt > 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                    }
                }
                int size = arrayList.size();
                a aVar = this.k;
                aVar.f1364c = new View[size];
                aVar.b = new int[size];
                if (this.m == null) {
                    this.m = new ArrayList<>();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(',');
                    for (int i3 = 0; i3 < size; i3++) {
                        int intValue = ((Integer) arrayList.get(i3)).intValue();
                        this.g = intValue;
                        a aVar2 = this.k;
                        aVar2.b[i3] = intValue;
                        if (intValue > 0) {
                            aVar2.f1364c[i3] = linearLayout.findViewById(intValue + this.f);
                        }
                        stringBuffer.append(this.g + this.f);
                        stringBuffer.append(',');
                    }
                    t.this.a2(this.m, stringBuffer, linearLayout);
                } else {
                    for (int i4 = 0; i4 < size; i4++) {
                        int intValue2 = ((Integer) arrayList.get(i4)).intValue();
                        this.g = intValue2;
                        a aVar3 = this.k;
                        aVar3.b[i4] = intValue2;
                        if (intValue2 > 0) {
                            aVar3.f1364c[i4] = linearLayout.findViewById(intValue2 + this.f);
                        }
                    }
                }
                a aVar4 = this.k;
                aVar4.a = linearLayout2;
                linearLayout.setTag(aVar4);
                view2 = linearLayout;
            } else {
                this.k = (a) view.getTag();
                view2 = view;
            }
            int i5 = 0;
            while (true) {
                a aVar5 = this.k;
                int[] iArr = aVar5.b;
                if (i5 >= iArr.length) {
                    break;
                }
                if (iArr[i5] > 0) {
                    c.b.a.a.f.w(aVar5.f1364c[i5], hashMap.get(Integer.valueOf(iArr[i5])), hashMap, this.b);
                }
                i5++;
            }
            Iterator<Integer> it = this.m.iterator();
            while (it.hasNext()) {
                View findViewById = view2.findViewById(it.next().intValue());
                Object[] objArr = (Object[]) findViewById.getTag();
                objArr[3] = hashMap;
                findViewById.setTag(objArr);
            }
            if (this.e != null) {
                this.k.a.setTag(new Object[]{null, null, null, hashMap});
                if (this.l == null) {
                    this.l = new t(t.this.f1359d, t.this.f1358c);
                }
                this.l.T("st_vW", this.k.a);
                this.l.T("st_lS", this.f1361c.a);
                this.l.T("st_pN", Integer.valueOf(i2));
                this.l.f(this.e);
            }
            return view2;
        }
    }

    /* loaded from: classes.dex */
    public class m implements TextView.OnEditorActionListener {
        m() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            Integer num;
            t tVar = new t(t.this.f1359d, t.this.f1358c);
            tVar.T("st_vId", Integer.valueOf(textView.getId()));
            tVar.T("st_vW", textView);
            tVar.T("st_aI", Integer.valueOf(i2));
            if (keyEvent != null) {
                tVar.T("st_eA", Integer.valueOf(keyEvent.getAction()));
                tVar.T("st_eR", Integer.valueOf(keyEvent.getRepeatCount()));
                num = Integer.valueOf(keyEvent.getKeyCode());
            } else {
                num = null;
                tVar.T("st_eA", null);
                tVar.T("st_eR", null);
            }
            tVar.T("st_eK", num);
            return t.this.U0(tVar, "editormonitor", ((Object[]) textView.getTag())[2].toString());
        }
    }

    /* loaded from: classes.dex */
    public class m0 extends RecyclerView.Adapter<a> {

        /* renamed from: c */
        private Context f1365c;

        /* renamed from: d */
        private c.b.a.a.h f1366d;
        private k0 e;

        /* renamed from: i */
        private int f1367i;
        private Iterator l;
        private String f = null;
        private String g = null;
        private int h = 0;
        private int j = -1;
        private int k = -2;
        private t m = null;
        private ArrayList<Integer> n = null;

        /* loaded from: classes.dex */
        public class a extends RecyclerView.ViewHolder {
            public ViewGroup s;
            public int[] t;
            public View[] u;

            public a(m0 m0Var, ViewGroup viewGroup) {
                super(viewGroup);
                this.t = null;
                this.u = null;
                this.s = viewGroup;
            }
        }

        public m0(Context context) {
            this.f1365c = context;
            this.f1366d = new c.b.a.a.h(context, this, 1);
        }

        public ArrayList<HashMap<Integer, Object>> a() {
            return this.e.a;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: b */
        public void onBindViewHolder(a aVar, int i2) {
            HashMap<Integer, Object> hashMap = this.e.a.get(i2);
            if (aVar.t == null) {
                t.this.J1(this.f, aVar.s, this.h, hashMap);
                this.l = hashMap.keySet().iterator();
                ArrayList arrayList = new ArrayList();
                while (this.l.hasNext()) {
                    int parseInt = Integer.parseInt(String.valueOf(this.l.next()));
                    this.f1367i = parseInt;
                    if (parseInt > 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                    }
                }
                int size = arrayList.size();
                aVar.u = new View[size];
                aVar.t = new int[size];
                if (this.n == null) {
                    this.n = new ArrayList<>();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(',');
                    for (int i3 = 0; i3 < size; i3++) {
                        int intValue = ((Integer) arrayList.get(i3)).intValue();
                        this.f1367i = intValue;
                        aVar.t[i3] = intValue;
                        if (intValue > 0) {
                            aVar.u[i3] = aVar.s.findViewById(intValue + this.h);
                        }
                        stringBuffer.append(this.f1367i + this.h);
                        stringBuffer.append(',');
                    }
                    t.this.a2(this.n, stringBuffer, aVar.s);
                } else {
                    for (int i4 = 0; i4 < size; i4++) {
                        int intValue2 = ((Integer) arrayList.get(i4)).intValue();
                        this.f1367i = intValue2;
                        aVar.t[i4] = intValue2;
                        if (intValue2 > 0) {
                            aVar.u[i4] = aVar.s.findViewById(intValue2 + this.h);
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
                    c.b.a.a.f.w(aVar.u[i5], hashMap.get(Integer.valueOf(iArr[i5])), hashMap, this.f1366d);
                }
                i5++;
            }
            Iterator<Integer> it = this.n.iterator();
            while (it.hasNext()) {
                View findViewById = aVar.s.findViewById(it.next().intValue());
                Object[] objArr = (Object[]) findViewById.getTag();
                objArr[3] = hashMap;
                findViewById.setTag(objArr);
            }
            if (this.g != null) {
                aVar.s.setTag(new Object[]{null, null, null, hashMap});
                if (this.m == null) {
                    this.m = new t(t.this.f1359d, t.this.f1358c);
                }
                this.m.T("st_vW", aVar.s);
                this.m.T("st_lS", this.e.a);
                this.m.T("st_pN", Integer.valueOf(i2));
                this.m.f(this.g);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: c */
        public a onCreateViewHolder(ViewGroup viewGroup, int i2) {
            LinearLayout linearLayout = new LinearLayout(this.f1365c);
            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(this.j, this.k));
            linearLayout.setOrientation(1);
            return new a(this, linearLayout);
        }

        public void d(String str, k0 k0Var) {
            this.f = str;
            this.e = k0Var;
            this.h = t.this.r(str.substring(0, str.length() - 4));
            this.g = t.this.Q0(str);
        }

        public void e(int i2, int i3) {
            this.j = i2;
            this.k = i3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            k0 k0Var = this.e;
            if (k0Var == null) {
                return 0;
            }
            return k0Var.a.size();
        }
    }

    /* loaded from: classes.dex */
    public class n implements View.OnFocusChangeListener {
        n() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            t tVar = new t(t.this.f1359d, t.this.f1358c);
            tVar.T("st_vId", Integer.valueOf(view.getId()));
            tVar.T("st_vW", view);
            tVar.T("st_hF", Boolean.valueOf(z));
            tVar.f(c.b.a.a.p.c(((Object[]) view.getTag())[2].toString(), "<eventItme type=\"focuschange\">", "</eventItme>"));
        }
    }

    /* loaded from: classes.dex */
    public class o implements AbsListView.OnScrollListener {
        o() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            String c2 = c.b.a.a.p.c(((Object[]) absListView.getTag())[2].toString(), "<eventItme type=\"onscroll\">", "</eventItme>");
            if (c2 != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(absListView.getId()));
                tVar.T("st_vW", absListView);
                tVar.T("st_fM", Integer.valueOf(i2));
                tVar.T("st_vT", Integer.valueOf(i3));
                tVar.T("st_bT", Integer.valueOf(i4));
                tVar.f(c2);
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            String c2 = c.b.a.a.p.c(((Object[]) absListView.getTag())[2].toString(), "<eventItme type=\"onscrollstatechanged\">", "</eventItme>");
            if (c2 != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(absListView.getId()));
                tVar.T("st_vW", absListView);
                tVar.T("st_sE", Integer.valueOf(i2));
                tVar.f(c2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class p implements AdapterView.OnItemClickListener {
        p() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            t tVar = new t(t.this.f1359d, t.this.f1358c);
            tVar.T("st_vId", Integer.valueOf(adapterView.getId()));
            tVar.T("st_vW", adapterView);
            tVar.T("st_pN", Integer.valueOf(i2));
            tVar.T("st_iD", Long.valueOf(j));
            tVar.f(c.b.a.a.p.c(((Object[]) adapterView.getTag())[2].toString(), "<eventItme type=\"clickitem\">", "</eventItme>"));
        }
    }

    /* loaded from: classes.dex */
    public class q implements AdapterView.OnItemSelectedListener {
        q() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            String c2 = c.b.a.a.p.c(((Object[]) adapterView.getTag())[2].toString(), "<eventItme type=\"onitemselected\">", "</eventItme>");
            if (c2 != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(adapterView.getId()));
                tVar.T("st_vW", adapterView);
                tVar.T("st_vW2", view);
                tVar.T("st_pN", Integer.valueOf(i2));
                tVar.T("st_iD", Long.valueOf(j));
                tVar.f(c2);
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            String c2 = c.b.a.a.p.c(((Object[]) adapterView.getTag())[2].toString(), "<eventItme type=\"onnothingselected\">", "</eventItme>");
            if (c2 != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(adapterView.getId()));
                tVar.T("st_vW", adapterView);
                tVar.f(c2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class r implements SeekBar.OnSeekBarChangeListener {
        r() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            String c2 = c.b.a.a.p.c(((Object[]) seekBar.getTag())[2].toString(), "<eventItme type=\"onprogresschanged2\">", "</eventItme>");
            if (c2 != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(seekBar.getId()));
                tVar.T("st_vW", seekBar);
                tVar.T("st_nS", Integer.valueOf(i2));
                tVar.T("st_fR", Boolean.valueOf(z));
                tVar.f(c2);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            String c2 = c.b.a.a.p.c(((Object[]) seekBar.getTag())[2].toString(), "<eventItme type=\"onstarttrackingtouch\">", "</eventItme>");
            if (c2 != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(seekBar.getId()));
                tVar.T("st_vW", seekBar);
                tVar.f(c2);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            String c2 = c.b.a.a.p.c(((Object[]) seekBar.getTag())[2].toString(), "<eventItme type=\"onstoptrackingtouch\">", "</eventItme>");
            if (c2 != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(seekBar.getId()));
                tVar.T("st_vW", seekBar);
                tVar.f(c2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class s implements TabLayout.OnTabSelectedListener {
        final /* synthetic */ String a;
        final /* synthetic */ View b;

        /* renamed from: c */
        final /* synthetic */ String f1368c;

        /* renamed from: d */
        final /* synthetic */ String f1369d;

        s(String str, View view, String str2, String str3) {
            this.a = str;
            this.b = view;
            this.f1368c = str2;
            this.f1369d = str3;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
            if (this.f1369d != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                tVar.T("st_vW", this.b);
                tVar.T("st_pN", Integer.valueOf(tab.getPosition()));
                tVar.T("st_tT", String.valueOf(tab.getText()));
                tVar.T("st_taB", tab);
                tVar.f(this.f1369d);
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            if (this.a != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                tVar.T("st_vW", this.b);
                tVar.T("st_pN", Integer.valueOf(tab.getPosition()));
                tVar.T("st_tT", String.valueOf(tab.getText()));
                tVar.T("st_taB", tab);
                tVar.f(this.a);
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            if (this.f1368c != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                tVar.T("st_vW", this.b);
                tVar.T("st_pN", Integer.valueOf(tab.getPosition()));
                tVar.T("st_tT", String.valueOf(tab.getText()));
                tVar.T("st_taB", tab);
                tVar.f(this.f1368c);
            }
        }
    }

    /* renamed from: c.b.a.a.t$t */
    /* loaded from: classes.dex */
    public class C0048t extends RecyclerView.OnScrollListener {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        C0048t(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (this.a != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(recyclerView.getId()));
                tVar.T("st_vW", recyclerView);
                tVar.T("st_sE", Integer.valueOf(i2));
                tVar.f(this.a);
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
                t tVar = new t(t.this.f1359d, t.this.f1358c);
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
            }
        }
    }

    /* loaded from: classes.dex */
    public class u extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ View a;
        final /* synthetic */ String b;

        u(View view, String str) {
            this.a = view;
            this.b = str;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            View findChildViewUnder = ((RecyclerView) this.a).findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (findChildViewUnder == null || this.b == null) {
                return;
            }
            t tVar = new t(t.this.f1359d, t.this.f1358c);
            tVar.T("st_vId", Integer.valueOf(this.a.getId()));
            tVar.T("st_vW", this.a);
            tVar.T("st_pN", Integer.valueOf(((RecyclerView) this.a).getChildLayoutPosition(findChildViewUnder)));
            tVar.T("st_vW2", findChildViewUnder);
            tVar.f(this.b);
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
            t tVar = new t(t.this.f1359d, t.this.f1358c);
            tVar.T("st_vId", Integer.valueOf(this.a.getId()));
            tVar.T("st_vW", this.a);
            tVar.T("st_pN", Integer.valueOf(((RecyclerView) this.a).getChildLayoutPosition(findChildViewUnder)));
            tVar.T("st_vW2", findChildViewUnder);
            tVar.f(this.b);
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
                Toast.makeText(t.this.f1359d, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                t.this.e(message.obj.toString());
            }
        }
    }

    /* loaded from: classes.dex */
    public class w implements RecyclerView.OnItemTouchListener {
        final /* synthetic */ GestureDetector a;

        w(t tVar, GestureDetector gestureDetector) {
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
        final /* synthetic */ String f1372c;

        /* renamed from: d */
        final /* synthetic */ String f1373d;

        x(String str, View view, String str2, String str3) {
            this.a = str;
            this.b = view;
            this.f1372c = str2;
            this.f1373d = str3;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (this.f1373d != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                tVar.T("st_vW", this.b);
                tVar.T("st_sE", Integer.valueOf(i2));
                tVar.f(this.f1373d);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            if (this.f1372c != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                tVar.T("st_vW", this.b);
                tVar.T("st_pN", Integer.valueOf(i2));
                tVar.T("st_pT", Float.valueOf(f));
                tVar.T("st_pS", Integer.valueOf(i3));
                tVar.f(this.f1372c);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (this.a != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                tVar.T("st_vW", this.b);
                tVar.T("st_pN", Integer.valueOf(i2));
                tVar.f(this.a);
            }
        }
    }

    /* loaded from: classes.dex */
    public class y implements SwipeRefreshLayout.OnRefreshListener {
        final /* synthetic */ String a;
        final /* synthetic */ View b;

        y(String str, View view) {
            this.a = str;
            this.b = view;
        }

        @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
        public void onRefresh() {
            if (this.a != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                tVar.T("st_vW", this.b);
                tVar.f(this.a);
            }
        }
    }

    /* loaded from: classes.dex */
    public class z implements CompoundButton.OnCheckedChangeListener {
        final /* synthetic */ String a;
        final /* synthetic */ View b;

        z(String str, View view) {
            this.a = str;
            this.b = view;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (this.a != null) {
                t tVar = new t(t.this.f1359d, t.this.f1358c);
                tVar.T("st_vId", Integer.valueOf(this.b.getId()));
                tVar.T("st_vW", this.b);
                tVar.T("st_iC", Boolean.valueOf(z));
                tVar.f(this.a);
            }
        }
    }

    public t(Activity activity) {
        this.f1358c = null;
        this.f1359d = null;
        this.f1360i = null;
        this.f1358c = activity;
        this.f1359d = activity;
        this.f1360i = new k(activity.getMainLooper());
    }

    public t(Context context, Activity activity) {
        this.f1358c = null;
        this.f1359d = null;
        this.f1360i = null;
        this.f1358c = activity;
        this.f1359d = context;
        this.f1360i = new v(context.getMainLooper());
    }

    private void A(String str) {
        String trim;
        String str2;
        Boolean bool = Boolean.TRUE;
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 5) {
            com.iapp.app.p pVar = com.iapp.app.p.n;
            if (pVar != null) {
                pVar.j(N0(L0(P1[0])), Integer.parseInt(L0(P1[1])), Integer.parseInt(L0(P1[2])), Integer.parseInt(L0(P1[3])), Integer.parseInt(L0(P1[4])));
                return;
            }
            com.iapp.app.p pVar2 = new com.iapp.app.p(this.f1358c);
            com.iapp.app.p.n = pVar2;
            pVar2.n(N0(L0(P1[0])), Integer.parseInt(L0(P1[1])), Integer.parseInt(L0(P1[2])), Integer.parseInt(L0(P1[3])), Integer.parseInt(L0(P1[4])));
            return;
        }
        if (length == 2) {
            if (com.iapp.app.p.n == null) {
                T(P1[1].trim(), Boolean.FALSE);
                return;
            }
            String L0 = L0(P1[0]);
            if (L0.equals("st")) {
                com.iapp.app.p.n.p(true);
                str2 = P1[1];
            } else if (L0.equals("sp")) {
                com.iapp.app.p.n.p(false);
                str2 = P1[1];
            } else {
                if (!L0.equals("re")) {
                    if (L0.equals("ip")) {
                        trim = P1[1].trim();
                        bool = Boolean.valueOf(com.iapp.app.p.n.i());
                        T(trim, bool);
                    }
                    return;
                }
                com.iapp.app.p.n.o();
                com.iapp.app.p.n = null;
                str2 = P1[1];
            }
            trim = str2.trim();
            T(trim, bool);
        }
    }

    private void A0(String str) {
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            c.b.a.a.d.k(N0(L0(P1[0])), L0(P1[1]));
        } else if (length == 3) {
            c.b.a.a.d.l(N0(L0(P1[0])), L0(P1[1]), L0(P1[2]));
        }
    }

    @SuppressLint({"NewApi"})
    private void A1(String str) {
        String str2;
        String trim;
        JSONObject jSONObject;
        String trim2;
        Object obj;
        String trim3;
        Object jSONArray;
        int i2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            try {
                T(P1[1].trim(), new JSONObject(L0(P1[0])));
                return;
            } catch (JSONException e2) {
                e2.printStackTrace();
                str2 = P1[1];
            }
        } else {
            if (length == 3) {
                Object K0 = K0(P1[1]);
                if (K0.equals("size")) {
                    Object K02 = K0(P1[0]);
                    if (K02 instanceof JSONObject) {
                        trim3 = P1[2].trim();
                        i2 = ((JSONObject) K02).length();
                    } else if (K02 instanceof JSONArray) {
                        trim3 = P1[2].trim();
                        i2 = ((JSONArray) K02).length();
                    } else {
                        trim3 = P1[2].trim();
                        i2 = -1;
                    }
                    jSONArray = Integer.valueOf(i2);
                } else {
                    if (K0.equals("del")) {
                        Object K03 = K0(P1[0]);
                        if (K03 instanceof JSONObject) {
                            ((JSONObject) K03).remove(L0(P1[2]));
                            return;
                        } else {
                            if (!(K03 instanceof JSONArray) || Build.VERSION.SDK_INT < 19) {
                                return;
                            }
                            ((JSONArray) K03).remove(d(L0(P1[2])));
                            return;
                        }
                    }
                    if (!K0.equals("json")) {
                        return;
                    }
                    Object K04 = K0(P1[0]);
                    if (K04 instanceof JSONObject) {
                        trim3 = P1[2].trim();
                        jSONArray = ((JSONObject) K04).toString();
                    } else if (K04 instanceof JSONArray) {
                        trim3 = P1[2].trim();
                        jSONArray = ((JSONArray) K04).toString();
                    } else {
                        str2 = P1[2];
                    }
                }
                T(trim3, jSONArray);
                return;
            }
            if (length != 4) {
                return;
            }
            Object K05 = K0(P1[1]);
            if (K05.equals("get")) {
                Object K06 = K0(P1[0]);
                try {
                    if (K06 instanceof JSONObject) {
                        trim2 = P1[3].trim();
                        obj = ((JSONObject) K06).get(L0(P1[2]));
                    } else if (!(K06 instanceof JSONArray)) {
                        T(P1[3].trim(), null);
                        return;
                    } else {
                        trim2 = P1[3].trim();
                        obj = ((JSONArray) K06).get(d(L0(P1[2])));
                    }
                    T(trim2, obj);
                    return;
                } catch (JSONException e3) {
                    e3.printStackTrace();
                    str2 = P1[3];
                }
            } else {
                if (K05.equals("set")) {
                    Object K07 = K0(P1[0]);
                    try {
                        if (K07 instanceof JSONObject) {
                            ((JSONObject) K07).put(L0(P1[2]), K0(P1[3]));
                        } else if (K07 instanceof JSONArray) {
                            ((JSONArray) K07).put(d(L0(P1[2])), K0(P1[3]));
                        }
                        return;
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                        return;
                    }
                }
                if (K05.equals("list")) {
                    Object K08 = K0(P1[0]);
                    if (K08 instanceof JSONObject) {
                        try {
                            T(P1[3].trim(), ((JSONObject) K08).getJSONArray(L0(P1[2])));
                            return;
                        } catch (JSONException e5) {
                            e5.printStackTrace();
                            str2 = P1[3];
                        }
                    } else {
                        str2 = P1[3];
                    }
                } else {
                    if (!K05.equals("data")) {
                        return;
                    }
                    Object K09 = K0(P1[0]);
                    try {
                        if (K09 instanceof JSONObject) {
                            trim = P1[3].trim();
                            jSONObject = ((JSONObject) K09).getJSONObject(L0(P1[2]));
                        } else if (!(K09 instanceof JSONArray)) {
                            T(P1[3].trim(), null);
                            return;
                        } else {
                            trim = P1[3].trim();
                            jSONObject = ((JSONArray) K09).getJSONObject(d(L0(P1[2])));
                        }
                        T(trim, jSONObject);
                        return;
                    } catch (JSONException e6) {
                        e6.printStackTrace();
                        str2 = P1[3];
                    }
                }
            }
        }
        T(str2.trim(), null);
    }

    private void A2(String str) {
        String trim;
        Object string;
        int position;
        boolean moveToLast;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        Object K0 = K0(P1[0]);
        Object K02 = K0(P1[1]);
        Cursor cursor = K0 instanceof Cursor ? (Cursor) K0 : null;
        if (length == 2) {
            if (!K02.equals("re") || cursor == null) {
                return;
            }
            cursor.close();
            return;
        }
        if (length == 3) {
            if (K02 instanceof Integer) {
                T(P1[2].trim(), cursor.getString(Integer.parseInt(K02.toString())));
                return;
            }
            if (K02.equals("columncount")) {
                trim = P1[2].trim();
                position = cursor.getColumnCount();
            } else {
                if (!K02.equals("count")) {
                    if (K02.equals("next")) {
                        trim = P1[2].trim();
                        moveToLast = cursor.moveToNext();
                    } else if (K02.equals("previous")) {
                        trim = P1[2].trim();
                        moveToLast = cursor.moveToPrevious();
                    } else if (K02.equals("first")) {
                        trim = P1[2].trim();
                        moveToLast = cursor.moveToFirst();
                    } else if (K02.equals("last")) {
                        trim = P1[2].trim();
                        moveToLast = cursor.moveToLast();
                    } else if (K02.equals("position")) {
                        cursor.moveToPosition(d(K0(P1[2]).toString()));
                        return;
                    } else {
                        if (!K02.equals("getposition")) {
                            trim = P1[2].trim();
                            string = cursor.getString(d(K02.toString()));
                            T(trim, string);
                        }
                        trim = P1[2].trim();
                        position = cursor.getPosition();
                    }
                    string = Boolean.valueOf(moveToLast);
                    T(trim, string);
                }
                trim = P1[2].trim();
                position = cursor.getCount();
            }
            string = Integer.valueOf(position);
            T(trim, string);
        }
    }

    private void A3(String str) {
        int i2 = 0;
        if (!K0(P1(W2(str))[0]).equals(Boolean.TRUE)) {
            i2 = 1;
            if (this.f1358c.getRequestedOrientation() == 1) {
                return;
            }
        } else if (this.f1358c.getRequestedOrientation() == 0) {
            return;
        }
        this.f1358c.setRequestedOrientation(i2);
    }

    private void B(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 2) {
            Object K0 = K0(P1[1]);
            if (K0.equals("sp")) {
                Object K02 = K0(P1[0]);
                if (K02 instanceof MediaRecorder) {
                    MediaRecorder mediaRecorder = (MediaRecorder) K02;
                    try {
                        mediaRecorder.stop();
                        mediaRecorder.release();
                    } catch (IllegalStateException e2) {
                        e2.printStackTrace();
                    }
                    T(P1[0].trim(), null);
                    return;
                }
                return;
            }
            MediaRecorder mediaRecorder2 = new MediaRecorder();
            mediaRecorder2.setAudioSource(1);
            mediaRecorder2.setOutputFormat(0);
            String N0 = N0(String.valueOf(K0));
            c.b.a.a.d.b(N0, false);
            mediaRecorder2.setOutputFile(N0);
            mediaRecorder2.setAudioEncoder(0);
            try {
                mediaRecorder2.prepare();
                mediaRecorder2.start();
                T(P1[0].trim(), mediaRecorder2);
            } catch (Exception e3) {
                e3.printStackTrace();
                T(P1[0].trim(), null);
            }
        }
    }

    private int B0() {
        try {
            Display defaultDisplay = this.f1358c.getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            this.f1358c.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics2);
            return displayMetrics.heightPixels - displayMetrics2.heightPixels;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private void B1(String str) {
        c.c.a.a.a(this.f1358c, d(L0(P1(W2(str))[0])));
    }

    private void B2(String str) {
        String trim;
        String L0;
        String L02;
        String str2;
        String replaceAll;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 4) {
            trim = P1[3].trim();
            L0 = L0(P1[0]);
            L02 = L0(P1[1]);
            str2 = P1[2];
        } else {
            if (length != 5) {
                return;
            }
            if (K0(P1[3]).equals(Boolean.TRUE)) {
                trim = P1[4].trim();
                replaceAll = L0(P1[0]).replaceAll(L0(P1[1]), L0(P1[2]));
                T(trim, replaceAll);
            } else {
                trim = P1[4].trim();
                L0 = L0(P1[0]);
                L02 = L0(P1[1]);
                str2 = P1[2];
            }
        }
        replaceAll = L0.replace(L02, L0(str2));
        T(trim, replaceAll);
    }

    private void B3(String str) {
        if (K0(P1(W2(str))[0]).equals(Boolean.TRUE)) {
            this.f1358c.getWindow().clearFlags(128);
        } else {
            this.f1358c.getWindow().setFlags(128, 128);
        }
    }

    private void C(String str) {
        byte[] bArr;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        byte[] bArr2 = null;
        if (length == 2) {
            Object K0 = K0(P1[0]);
            if (K0 instanceof byte[]) {
                bArr2 = (byte[]) K0;
            } else {
                try {
                    bArr2 = D(K0.toString().trim(), ' ');
                } catch (Exception unused) {
                }
            }
            if (bArr2 == null) {
                return;
            }
            c.b.a.a.d.j(N0(L0(P1[1])), bArr2);
            return;
        }
        if (length == 3) {
            Object K02 = K0(P1[1]);
            if (K02 instanceof byte[]) {
                bArr = (byte[]) K02;
            } else {
                try {
                    bArr = D(K02.toString().trim(), ' ');
                } catch (Exception unused2) {
                    bArr = null;
                }
            }
            if (bArr == null) {
                return;
            }
            Object K03 = K0(P1[0]);
            if (K03 == null) {
                T(P1[2].trim(), new String(bArr));
                return;
            }
            try {
                T(P1[2].trim(), new String(bArr, K03.toString()));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                T(P1[2].trim(), null);
            }
        }
    }

    private String[] C0() {
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

    private void C1(String str) {
        DexClassLoader r2;
        String str2;
        Object obj = Boolean.TRUE;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            T(P1[1].trim(), c.b.a.a.f.r(this.f1359d, L0(P1[0]), this.f1359d.getClassLoader()));
            return;
        }
        if (length == 3) {
            r2 = c.b.a.a.f.r(this.f1359d, L0(P1[0]), this.f1359d.getClassLoader());
            if (K0(P1[1]).equals(obj)) {
                c.b.a.a.f.u(this.f1359d, r2);
            }
            str2 = P1[2];
        } else {
            if (length != 4) {
                return;
            }
            r2 = c.b.a.a.f.r(this.f1359d, L0(P1[0]), (ClassLoader) K0(P1[2]));
            if (K0(P1[1]).equals(obj)) {
                c.b.a.a.f.u(this.f1359d, r2);
            }
            str2 = P1[3];
        }
        T(str2.trim(), r2);
    }

    private void C2(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            T(P1[2].trim(), Integer.valueOf(c.b.a.a.p.g(d(L0(P1[0])), d(L0(P1[1])))));
        }
    }

    private void C3(String str) {
        String[] P1 = P1(W2(str));
        Intent intent = new Intent("android.intent.action.SENDTO", c.b.a.a.k.c(this.f1359d, "smsto:" + L0(P1[0])));
        intent.putExtra("sms_body", L0(P1[1]));
        this.f1359d.startActivity(intent);
    }

    private byte[] D(String str, char c2) {
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

    private int[] D0() {
        return new int[]{this.f1358c.getWindowManager().getDefaultDisplay().getWidth(), this.f1358c.getWindowManager().getDefaultDisplay().getHeight()};
    }

    private void D1(String str) {
        c.b.a.a.f.s(L0(str));
    }

    private void D2(String str) {
        String W2 = W2(str);
        int lastIndexOf = W2.lastIndexOf(44);
        String trim = W2.substring(lastIndexOf + 1).trim();
        String str2 = "";
        for (String str3 : w2(W2.substring(0, lastIndexOf).trim(), '+')) {
            Object K0 = K0(str3);
            if (K0 != null) {
                str2 = str2 + K0;
            }
        }
        T(trim.trim(), str2);
    }

    private void D3(String str) {
        Boolean bool = Boolean.TRUE;
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        if (K0 instanceof com.iapp.app.m) {
            com.iapp.app.m mVar = (com.iapp.app.m) K0;
            Object K02 = K0(P1[1]);
            if (K02.equals("shot")) {
                mVar.q(N0(L0(P1[2])), d(L0(P1[3])), K0(P1[4]).equals(bool));
                return;
            }
            if (K02.equals("st")) {
                mVar.y();
                return;
            }
            if (K02.equals("sp")) {
                mVar.z();
                return;
            }
            if (K02.equals("re")) {
                mVar.u();
                return;
            }
            if (K02.equals("rotaing")) {
                mVar.w(d(L0(P1[2])));
            } else if (K02.equals("getrotaing")) {
                T(P1[2].trim(), Integer.valueOf(mVar.r()));
            } else if (K02.equals("usg")) {
                mVar.x(K0(P1[2]).equals(bool));
            }
        }
    }

    @SuppressLint({"NewApi"})
    private void E(String str) {
        String trim;
        Object b2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        Object K0 = K0(P1[1]);
        int i2 = 3;
        if (K0.equals("myu")) {
            int i3 = length - 3;
            if (i3 >= 0) {
                String L0 = L0(P1[2]);
                Object obj = l.get(L0 + i3);
                if (obj == null) {
                    String substring = L0.substring(0, L0.indexOf(46) + 1);
                    Iterator<String> it = l.keySet().iterator();
                    while (it.hasNext()) {
                        if (it.next().toString().startsWith(substring)) {
                            return;
                        }
                    }
                    c2(substring + "myu");
                    obj = l.get(L0 + i3);
                }
                String obj2 = obj.toString();
                int indexOf = obj2.indexOf(10);
                String[] P12 = P1(W2(obj2.substring(0, indexOf)));
                if (i3 != P12.length) {
                    return;
                }
                while (i2 < length) {
                    T(P12[i2 - 3].trim(), K0(P1[i2]));
                    i2++;
                }
                e(obj2.substring(indexOf).trim());
                return;
            }
            return;
        }
        String str2 = "i";
        if (K0.equals("mjava")) {
            com.iapp.app.j jVar = new com.iapp.app.j(this.f1359d);
            try {
                jVar.set("activity", this.f1359d);
                jVar.set("i", new Aid_javaCode(this.f1359d, this.f1358c, jVar));
            } catch (EvalError e2) {
                e2.printStackTrace();
            }
            String L02 = L0(P1[2]);
            int indexOf2 = L02.indexOf(46);
            if (jVar.e(L02.substring(0, indexOf2) + ".mjava")) {
                if (length == 3) {
                    trim = P1[0].trim();
                    b2 = jVar.a(L02.substring(indexOf2 + 1));
                } else {
                    if (length <= 3) {
                        return;
                    }
                    Object[] objArr = new Object[length - 3];
                    int i4 = 0;
                    while (i2 < length) {
                        objArr[i4] = K0(P1[i2]);
                        i4++;
                        i2++;
                    }
                    trim = P1[0].trim();
                    b2 = jVar.b(L02.substring(indexOf2 + 1), objArr);
                }
                T(trim, b2);
                return;
            }
            return;
        }
        String str3 = "_a";
        if (K0.equals("mlua")) {
            String L03 = L0(P1[2]);
            long id = Thread.currentThread().getId();
            String str4 = "";
            String str5 = str4;
            int i5 = 3;
            int i6 = 0;
            while (i5 < length) {
                int i7 = length;
                int i8 = i6 + 1;
                String str6 = "$_Call_SsS_" + id + str3 + i8;
                j.put(str6, K0(P1[i5]));
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
            String str9 = "require 'import'\nrequire '" + L03.substring(0, L03.indexOf(46)) + "'\n" + str5 + "\nlocal returns = " + L03.substring(L03.indexOf(46) + 1) + "(" + str4 + ")\ni:sss(\"" + str8 + "\", returns)";
            com.iapp.app.d dVar = new com.iapp.app.d(this.f1359d);
            dVar.l("activity", this.f1359d);
            dVar.l(str7, new Aid_luaCode(this.f1359d, this.f1358c, dVar));
            dVar.k();
            try {
                dVar.h(str9);
                T(P1[0].trim(), j.get(str8));
                return;
            } catch (LuaException e3) {
                e3.printStackTrace();
                P2(this.f1359d, "LuaErr：\n" + e3.getMessage());
                return;
            }
        }
        String str10 = "_a";
        if (K0.equals("mjs")) {
            String L04 = L0(P1[2]);
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
                j.put(sb2, K0(P1[i10]));
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
            sb3.append(c.b.a.a.f.e(this.f1359d, "import.mjs"));
            sb3.append("\n");
            sb3.append(c.b.a.a.f.a(this.f1359d, L04.substring(0, L04.indexOf(46)) + ".mjs"));
            sb3.append("\n");
            sb3.append(str12);
            sb3.append("\nvar returns = ");
            sb3.append(L04.substring(L04.indexOf(46) + 1));
            sb3.append("(");
            sb3.append(str11);
            sb3.append(");</script></head></html>");
            String sb4 = sb3.toString();
            WebView webView = new WebView(this.f1359d);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setAppCachePath(this.f1359d.getApplicationContext().getDir("cache", 0).getPath());
            webView.getSettings().setAppCacheMaxSize(8388608L);
            webView.getSettings().setDatabaseEnabled(true);
            webView.getSettings().setDatabasePath(this.f1359d.getApplicationContext().getDir("database", 0).getPath());
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setGeolocationEnabled(true);
            webView.getSettings().setLightTouchEnabled(true);
            webView.getSettings().setCacheMode(-1);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.setWebChromeClient(new g0());
            if (Build.VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
            }
            webView.addJavascriptInterface(new Aid_jsCode(this.f1359d, this.f1358c, webView, 0), "I");
            com.iapp.app.c.d(webView, sb4);
        }
    }

    private int[] E0() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.f1358c.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    private void E1(String str) {
        GradientDrawable gradientDrawable;
        String str2;
        GradientDrawable gradientDrawable2;
        String str3;
        String[] P1 = P1(W2(str));
        if (P1.length != 2) {
            if (P1.length == 3) {
                int d2 = d(L0(P1[0]));
                int o2 = c.b.a.a.p.o(L0(P1[1]));
                gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setColor(o2);
                if (d2 > 0) {
                    gradientDrawable2.setCornerRadius(d2);
                }
                str3 = P1[2];
            } else if (P1.length == 4) {
                int d3 = d(L0(P1[0]));
                int o3 = c.b.a.a.p.o(L0(P1[1]));
                int o4 = c.b.a.a.p.o(L0(P1[2]));
                gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setColor(o3);
                if (d3 > 0) {
                    gradientDrawable2.setStroke(d3, o4);
                }
                str3 = P1[3];
            } else {
                if (P1.length == 5) {
                    int d4 = d(L0(P1[0]));
                    int d5 = d(L0(P1[1]));
                    int o5 = c.b.a.a.p.o(L0(P1[2]));
                    int o6 = c.b.a.a.p.o(L0(P1[3]));
                    GradientDrawable gradientDrawable3 = new GradientDrawable();
                    gradientDrawable3.setColor(o5);
                    if (d5 > 0) {
                        gradientDrawable3.setCornerRadius(d5);
                    }
                    if (d4 > 0) {
                        gradientDrawable3.setStroke(d4, o6);
                    }
                    T(P1[4].trim(), gradientDrawable3);
                    return;
                }
                if (P1.length != 6) {
                    return;
                }
                int d6 = d(L0(P1[0]));
                int d7 = d(L0(P1[1]));
                int o7 = c.b.a.a.p.o(L0(P1[3]));
                String[] w2 = w2(L0(P1[2]), '|');
                int[] iArr = new int[w2.length];
                for (int i2 = 0; i2 < w2.length; i2++) {
                    iArr[i2] = c.b.a.a.p.o(w2[i2]);
                }
                gradientDrawable = new GradientDrawable(P0(L0(P1[4])), iArr);
                if (d7 > 0) {
                    gradientDrawable.setCornerRadius(d7);
                }
                if (d6 > 0) {
                    gradientDrawable.setStroke(d6, o7);
                }
                str2 = P1[5];
            }
            T(str3.trim(), gradientDrawable2);
            return;
        }
        int o8 = c.b.a.a.p.o(L0(P1[0]));
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(o8);
        str2 = P1[1];
        T(str2.trim(), gradientDrawable);
    }

    private void E2(String str) {
        String str2;
        String[] P1 = P1(W2(str));
        if (P1.length == 4) {
            try {
                T(P1[3].trim(), L0(P1[0]).substring(d(L0(P1[1])), d(L0(P1[2]))));
                return;
            } catch (Exception unused) {
                str2 = P1[3];
            }
        } else {
            if (P1.length != 3) {
                return;
            }
            try {
                T(P1[2].trim(), L0(P1[0]).substring(d(L0(P1[1]))));
                return;
            } catch (Exception unused2) {
                str2 = P1[2];
            }
        }
        T(str2.trim(), null);
    }

    private void E3(String str) {
        String str2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 3) {
            Object K0 = K0(P1[1]);
            View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[1]);
            if (m02 instanceof SurfaceView) {
                T(P1[0].trim(), new com.iapp.app.m(this.f1359d, this.f1358c, (SurfaceView) m02, false, d(L0(P1[2]))));
                return;
            }
            str2 = P1[0];
        } else {
            if (length != 6) {
                return;
            }
            Object K02 = K0(P1[1]);
            View m03 = K02 instanceof View ? (View) K02 : m0(K02, P1[1]);
            if (m03 instanceof SurfaceView) {
                T(P1[0].trim(), new com.iapp.app.m(this.f1359d, this.f1358c, (SurfaceView) m03, false, d(L0(P1[2])), d(L0(P1[3])), d(L0(P1[4])), d(L0(P1[5]))));
                return;
            }
            str2 = P1[0];
        }
        T(str2.trim(), null);
    }

    private void F(String str) {
        Class<?> a2;
        String trim;
        String str2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            Object K0 = K0(P1[0]);
            a2 = K0 instanceof Class ? (Class) K0 : K0 instanceof String ? c.b.a.a.b.a(String.valueOf(K0)) : K0.getClass();
            trim = P1[1].trim();
            str2 = P1[1];
        } else {
            if (length != 3) {
                return;
            }
            Object K02 = K0(P1[0]);
            a2 = K02 instanceof Class ? (Class) K02 : K02 instanceof String ? c.b.a.a.b.a(String.valueOf(K02)) : K02.getClass();
            trim = P1[2].trim();
            str2 = P1[1];
        }
        T(trim, a2.cast(K0(str2)));
    }

    private String[] F0() {
        return new String[]{Build.MODEL, Build.BRAND, String.valueOf(Build.VERSION.SDK_INT)};
    }

    private void F1(String str) {
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            T(P1[1].trim(), new Object[d(L0(P1[0]))]);
            return;
        }
        if (length == 3) {
            int d2 = d(L0(P1[0]));
            Object K0 = K0(P1[1]);
            if (K0 instanceof String) {
                T(P1[2].trim(), Array.newInstance(c.b.a.a.b.b(K0.toString()), d2));
            } else if (K0 instanceof Class) {
                T(P1[2].trim(), Array.newInstance((Class<?>) K0, d2));
            } else {
                T(P1[2].trim(), null);
            }
        }
    }

    private void F2(String str) {
        String[] P1 = P1(X2(str));
        if (P1.length == 2) {
            String b02 = b0(str);
            Object K0 = K0(P1[0]);
            View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[0]);
            if (m02 != null) {
                Object[] objArr = (Object[]) m02.getTag();
                String L0 = L0(P1[1]);
                objArr[2] = O0((String) objArr[2], L0, b02);
                m02.setTag(objArr);
                p(m02, "<eventItme type=\"" + L0 + "\">" + b02.replace("<", "&lt;").replace(">", "&gt;") + "</eventItme>");
            }
        }
    }

    private void F3(String str) {
        String str2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 3) {
            Object K0 = K0(P1[1]);
            View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[1]);
            if (m02 instanceof SurfaceView) {
                T(P1[0].trim(), new com.iapp.app.m(this.f1359d, this.f1358c, (SurfaceView) m02, true, d(L0(P1[2]))));
                return;
            }
            str2 = P1[0];
        } else {
            if (length != 6) {
                return;
            }
            Object K02 = K0(P1[1]);
            View m03 = K02 instanceof View ? (View) K02 : m0(K02, P1[1]);
            if (m03 instanceof SurfaceView) {
                T(P1[0].trim(), new com.iapp.app.m(this.f1359d, this.f1358c, (SurfaceView) m03, true, d(L0(P1[2])), d(L0(P1[3])), d(L0(P1[4])), d(L0(P1[5]))));
                return;
            }
            str2 = P1[0];
        }
        T(str2.trim(), null);
    }

    public String G0(Object obj) {
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

    private void G1(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 4) {
            T(P1[3].trim(), new c.c.a.c(this.f1359d).d(K0(P1[0]), K0(P1[1]), K0(P1[2])));
        }
    }

    private void G2(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            ((ArrayList) K0(P1[0])).set(d(L0(P1[1])), K0(P1[2]));
        }
    }

    private void G3(String str) {
        String trim;
        int height;
        Object valueOf;
        CharSequence subtitle;
        Boolean bool = Boolean.TRUE;
        String[] P1 = P1(X2(str));
        int length = P1.length;
        if (length == 1) {
            Object K0 = K0(P1[0]);
            View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[0]);
            if (m02 == null || !(m02 instanceof Toolbar)) {
                return;
            }
            ((AppCompatActivity) this.f1358c).setSupportActionBar((Toolbar) m02);
            return;
        }
        if (length == 2) {
            Object K02 = K0(P1[0]);
            View m03 = K02 instanceof View ? (View) K02 : m0(K02, P1[0]);
            if (m03 == null || !(m03 instanceof Toolbar)) {
                return;
            }
            Object K03 = K0(P1[1]);
            View m04 = K03 instanceof View ? (View) K03 : m0(K03, P1[1]);
            if (m04 == null || !(m04 instanceof DrawerLayout)) {
                return;
            }
            ((AppCompatActivity) this.f1358c).getSupportActionBar().setHomeButtonEnabled(true);
            DrawerLayout drawerLayout = (DrawerLayout) m04;
            String obj = ((Object[]) drawerLayout.getTag())[2].toString();
            e0 e0Var = new e0(this.f1358c, drawerLayout, (Toolbar) m03, 0, 0, c.b.a.a.p.c(obj, "<eventItme type=\"ondrawerclosed\">", "</eventItme>"), drawerLayout, c.b.a.a.p.c(obj, "<eventItme type=\"ondraweropened\">", "</eventItme>"), c.b.a.a.p.c(obj, "<eventItme type=\"onoptionsitemselected\">", "</eventItme>"));
            drawerLayout.setDrawerListener(e0Var);
            e0Var.syncState();
            return;
        }
        if (length == 3) {
            Object K04 = K0(P1[0]);
            Object K05 = K0(P1[1]);
            if (K04.equals("get")) {
                if (K05.equals("sab")) {
                    trim = P1[2].trim();
                    valueOf = ((AppCompatActivity) this.f1358c).getSupportActionBar();
                } else {
                    if (K05.equals("title")) {
                        trim = P1[2].trim();
                        subtitle = ((AppCompatActivity) this.f1358c).getSupportActionBar().getTitle();
                    } else if (K05.equals("subtitle")) {
                        trim = P1[2].trim();
                        subtitle = ((AppCompatActivity) this.f1358c).getSupportActionBar().getSubtitle();
                    } else if (K05.equals("cv")) {
                        trim = P1[2].trim();
                        valueOf = ((AppCompatActivity) this.f1358c).getSupportActionBar().getCustomView();
                    } else {
                        if (K05.equals("do")) {
                            trim = P1[2].trim();
                            height = ((AppCompatActivity) this.f1358c).getSupportActionBar().getDisplayOptions();
                        } else {
                            if (!K05.equals("height")) {
                                return;
                            }
                            trim = P1[2].trim();
                            height = ((AppCompatActivity) this.f1358c).getSupportActionBar().getHeight();
                        }
                        valueOf = Integer.valueOf(height);
                    }
                    valueOf = String.valueOf(subtitle);
                }
                T(trim, valueOf);
                return;
            }
            if (K04.equals("set")) {
                if (K05.equals("title")) {
                    ((AppCompatActivity) this.f1358c).getSupportActionBar().setTitle(L0(P1[2]));
                }
                if (K05.equals("subtitle")) {
                    ((AppCompatActivity) this.f1358c).getSupportActionBar().setSubtitle(L0(P1[2]));
                    return;
                }
                if (K05.equals("cv")) {
                    ((AppCompatActivity) this.f1358c).getSupportActionBar().setCustomView((View) K0(P1[2]));
                    return;
                }
                if (K05.equals("do")) {
                    ((AppCompatActivity) this.f1358c).getSupportActionBar().setDisplayOptions(d(L0(P1[2])));
                    return;
                }
                if (K05.equals("dste")) {
                    ((AppCompatActivity) this.f1358c).getSupportActionBar().setDisplayShowTitleEnabled(K0(P1[2]).equals(bool));
                    return;
                }
                if (K05.equals("dsce")) {
                    ((AppCompatActivity) this.f1358c).getSupportActionBar().setDisplayShowCustomEnabled(K0(P1[2]).equals(bool));
                    return;
                }
                if (K05.equals("dshe")) {
                    boolean equals = K0(P1[2]).equals(bool);
                    ((AppCompatActivity) this.f1358c).getSupportActionBar().setHomeButtonEnabled(equals);
                    ((AppCompatActivity) this.f1358c).getSupportActionBar().setDisplayShowHomeEnabled(equals);
                    ((AppCompatActivity) this.f1358c).getSupportActionBar().setDisplayHomeAsUpEnabled(equals);
                    return;
                }
                if (!K05.equals("leftck")) {
                    return;
                }
                Object K06 = K0(P1[2]);
                View m05 = K06 instanceof View ? (View) K06 : m0(K06, P1[2]);
                if (m05 == null || !(m05 instanceof Toolbar)) {
                    return;
                } else {
                    ((Toolbar) m05).setNavigationOnClickListener(new f0(b0(str)));
                }
            } else {
                if (!K04.equals("left")) {
                    if (K04.equals("right")) {
                        View m06 = K05 instanceof View ? (View) K05 : m0(K05, P1[1]);
                        if (m06 == null || !(m06 instanceof Toolbar)) {
                            return;
                        }
                        ((Toolbar) m06).setOverflowIcon(com.iapp.app.i.m(L0(P1[2]), this.f1359d));
                        return;
                    }
                    return;
                }
                View m07 = K05 instanceof View ? (View) K05 : m0(K05, P1[1]);
                if (m07 == null || !(m07 instanceof Toolbar)) {
                    return;
                } else {
                    ((Toolbar) m07).setNavigationIcon(com.iapp.app.i.m(L0(P1[2]), this.f1359d));
                }
            }
            ((AppCompatActivity) this.f1358c).getSupportActionBar().setHomeButtonEnabled(true);
            ((AppCompatActivity) this.f1358c).getSupportActionBar().setDisplayShowHomeEnabled(true);
            ((AppCompatActivity) this.f1358c).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void H(String str) {
        String str2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            T(P1[1].trim(), c.b.a.a.b.b(L0(P1[0])));
            return;
        }
        if (length == 3) {
            Object K0 = K0(P1[0]);
            if (K0 instanceof ClassLoader) {
                try {
                    T(P1[2].trim(), ((ClassLoader) K0).loadClass(L0(P1[1])));
                    return;
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                    str2 = P1[2];
                }
            } else {
                str2 = P1[2];
            }
            T(str2.trim(), null);
        }
    }

    private int H0() {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return this.f1359d.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
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
    private void H1(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.t.H1(java.lang.String):void");
    }

    private void H2(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            try {
                Array.set(K0(P1[0]), d(L0(P1[1])), K0(P1[2]));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x02ff, code lost:
    
        if (K0(r3[3]).equals(r2) != false) goto L304;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x01f9, code lost:
    
        if (K0(r3[5]).equals(r2) != false) goto L304;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0312, code lost:
    
        r0 = r0.create();
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0301, code lost:
    
        r0 = r0.create();
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0288, code lost:
    
        if (K0(r3[4]).equals(r2) != false) goto L304;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void H3(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 796
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.t.H3(java.lang.String):void");
    }

    private void I(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            Object K0 = K0(P1[0]);
            if (K0 instanceof Class) {
                Object K02 = K0(P1[1]);
                if (K02.equals("init")) {
                    T(P1[2].trim(), ((Class) K0).getDeclaredConstructors());
                } else if (K02.equals("method")) {
                    T(P1[2].trim(), ((Class) K0).getDeclaredMethods());
                } else if (K02.equals("field")) {
                    T(P1[2].trim(), ((Class) K0).getDeclaredFields());
                }
            }
        }
    }

    private int I0() {
        int identifier = this.f1359d.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return this.f1359d.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private void I1(File file) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        c.b.a.a.k.a(this.f1359d, intent, file, c.b.a.a.p.p(file));
        this.f1359d.startActivity(intent);
    }

    private void I2(String str) {
        String trim;
        String C;
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            trim = P1[2].trim();
            C = c.b.a.a.p.B(L0(P1[0]), L0(P1[1]));
        } else {
            if (P1.length != 4) {
                return;
            }
            trim = P1[3].trim();
            C = c.b.a.a.p.C(L0(P1[0]), L0(P1[1]));
        }
        T(trim, C);
    }

    private void I3(String str) {
        View view;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 1) {
            Object K0 = K0(P1[0]);
            view = K0 instanceof View ? (View) K0 : null;
            if (view != null) {
                ((WindowManager) this.f1359d.getApplicationContext().getSystemService("window")).updateViewLayout(view, view.getLayoutParams());
                return;
            }
            return;
        }
        if (length == 2) {
            if (K0(P1[1]).equals("del")) {
                Object K02 = K0(P1[0]);
                view = K02 instanceof View ? (View) K02 : null;
                if (view != null) {
                    ((WindowManager) this.f1359d.getApplicationContext().getSystemService("window")).removeView(view);
                    return;
                }
                return;
            }
            return;
        }
        try {
            if (length == 5) {
                String L0 = L0(P1[0]);
                LinearLayout linearLayout = new LinearLayout(this.f1359d);
                linearLayout.setOrientation(1);
                int r2 = r(L0.substring(0, L0.length() - 4));
                linearLayout.setId(r2);
                J1(L0, linearLayout, r2, null);
                View childAt = linearLayout.getChildAt(0);
                linearLayout.removeAllViews();
                c.b.a.a.r.e(this.f1359d, childAt, 0, 0, d(L0(P1[1])), d(L0(P1[2])), 0, com.iapp.app.i.t(L0(P1[3])), 0, 0);
                T(P1[4].trim(), childAt);
            } else {
                if (length == 7) {
                    if (K0(P1[1]).equals("set")) {
                        Object K03 = K0(P1[0]);
                        view = K03 instanceof View ? (View) K03 : null;
                        if (view != null) {
                            WindowManager windowManager = (WindowManager) this.f1359d.getApplicationContext().getSystemService("window");
                            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
                            layoutParams.x = (int) Float.parseFloat(L0(P1[2]));
                            layoutParams.y = (int) Float.parseFloat(L0(P1[3]));
                            int d2 = d(L0(P1[4]));
                            if (d2 == 0) {
                                layoutParams.width = -2;
                            } else {
                                layoutParams.width = d2;
                            }
                            int d3 = d(L0(P1[5]));
                            if (d3 == 0) {
                                layoutParams.height = -2;
                            } else {
                                layoutParams.height = d3;
                            }
                            layoutParams.gravity = com.iapp.app.i.t(L0(P1[6]));
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
                Object K04 = K0(P1[0]);
                if (K04 instanceof View) {
                    if (K0(P1[1]).equals("set")) {
                        View view2 = (View) K04;
                        WindowManager windowManager2 = (WindowManager) this.f1359d.getApplicationContext().getSystemService("window");
                        WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) view2.getLayoutParams();
                        layoutParams2.x = (int) Float.parseFloat(L0(P1[2]));
                        layoutParams2.y = (int) Float.parseFloat(L0(P1[3]));
                        int d4 = d(L0(P1[4]));
                        if (d4 == 0) {
                            layoutParams2.width = -2;
                        } else {
                            layoutParams2.width = d4;
                        }
                        int d5 = d(L0(P1[5]));
                        if (d5 == 0) {
                            layoutParams2.height = -2;
                        } else {
                            layoutParams2.height = d5;
                        }
                        int d6 = d(L0(P1[6]));
                        if (d6 == 0) {
                            d6 = c.b.a.a.r.f();
                        }
                        layoutParams2.type = d6;
                        layoutParams2.gravity = com.iapp.app.i.t(L0(P1[7]));
                        int d7 = d(L0(P1[8]));
                        if (d7 == 0) {
                            d7 = 40;
                        } else if (d7 == 1) {
                            d7 = 32;
                        }
                        layoutParams2.flags = d7;
                        int d8 = d(L0(P1[9]));
                        if (d8 == 0) {
                            d8 = -3;
                        }
                        layoutParams2.format = d8;
                        view2.setLayoutParams(layoutParams2);
                        windowManager2.updateViewLayout(view2, layoutParams2);
                        return;
                    }
                    return;
                }
                String valueOf = String.valueOf(K04);
                LinearLayout linearLayout2 = new LinearLayout(this.f1359d);
                linearLayout2.setOrientation(1);
                int r3 = r(valueOf.substring(0, valueOf.length() - 4));
                linearLayout2.setId(r3);
                J1(valueOf, linearLayout2, r3, null);
                View childAt2 = linearLayout2.getChildAt(0);
                linearLayout2.removeAllViews();
                c.b.a.a.r.e(this.f1359d, childAt2, (int) Float.parseFloat(L0(P1[1])), (int) Float.parseFloat(L0(P1[2])), d(L0(P1[3])), d(L0(P1[4])), d(L0(P1[5])), com.iapp.app.i.t(L0(P1[6])), d(L0(P1[7])), d(L0(P1[8])));
                T(P1[9].trim(), childAt2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void J(String str) {
        String trim;
        boolean e2;
        Object clone;
        Boolean bool = Boolean.TRUE;
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        if (K0 instanceof Animation) {
            Animation animation = (Animation) K0;
            Object K02 = K0(P1[1]);
            if (K02.equals("cancel")) {
                animation.cancel();
                return;
            }
            if (K02.equals("reset")) {
                animation.reset();
                return;
            }
            if (K02.equals("start")) {
                animation.start();
                return;
            }
            if (K02.equals("duration")) {
                animation.setDuration(Long.parseLong(L0(P1[2])));
                return;
            }
            if (K02.equals("enabled")) {
                animation.setFillEnabled(K0(P1[2]).equals(bool));
                return;
            }
            if (K02.equals("after")) {
                animation.setFillAfter(K0(P1[2]).equals(bool));
                return;
            }
            if (K02.equals("before")) {
                animation.setFillBefore(K0(P1[2]).equals(bool));
                return;
            } else if (K02.equals("delay")) {
                animation.setStartOffset(Long.parseLong(L0(P1[2])));
                return;
            } else {
                if (K02.equals("repeat")) {
                    animation.setRepeatCount(d(L0(P1[2])));
                    return;
                }
                return;
            }
        }
        if (K0 instanceof c.d.a.a) {
            c.d.a.a aVar = (c.d.a.a) K0;
            Object K03 = K0(P1[1]);
            if (K03.equals("cancel")) {
                aVar.b();
                return;
            }
            if (K03.equals("clone")) {
                trim = P1[2].trim();
                clone = aVar.clone();
            } else {
                if (K03.equals("start")) {
                    aVar.j();
                    return;
                }
                if (!K03.equals("running")) {
                    if (K03.equals("duration")) {
                        aVar.g(Long.parseLong(L0(P1[2])));
                        return;
                    }
                    if (K03.equals("delay")) {
                        aVar.h(Long.parseLong(L0(P1[2])));
                        return;
                    } else {
                        if (K03.equals("target")) {
                            Object K04 = K0(P1[2]);
                            aVar.i(K04 instanceof View ? (View) K04 : m0(K04, P1[2]));
                            return;
                        }
                        return;
                    }
                }
                trim = P1[2].trim();
                e2 = aVar.e();
                clone = Boolean.valueOf(e2);
            }
        } else {
            if (K0 instanceof AnimationSet) {
                AnimationSet animationSet = (AnimationSet) K0;
                Object K05 = K0(P1[1]);
                if (K05.equals("cancel")) {
                    animationSet.cancel();
                    return;
                }
                if (K05.equals("reset")) {
                    animationSet.reset();
                    return;
                }
                if (K05.equals("start")) {
                    animationSet.start();
                    return;
                }
                if (K05.equals("duration")) {
                    animationSet.setDuration(Long.parseLong(L0(P1[2])));
                    return;
                }
                if (K05.equals("enabled")) {
                    animationSet.setFillEnabled(K0(P1[2]).equals(bool));
                    return;
                }
                if (K05.equals("after")) {
                    animationSet.setFillAfter(K0(P1[2]).equals(bool));
                    return;
                }
                if (K05.equals("before")) {
                    animationSet.setFillBefore(K0(P1[2]).equals(bool));
                    return;
                }
                if (K05.equals("delay")) {
                    animationSet.setStartOffset(Long.parseLong(L0(P1[2])));
                    return;
                } else if (K05.equals("repeat")) {
                    animationSet.setRepeatCount(d(L0(P1[2])));
                    return;
                } else {
                    if (K05.equals("add")) {
                        animationSet.addAnimation((Animation) K0(P1[2]));
                        return;
                    }
                    return;
                }
            }
            if (!(K0 instanceof c.d.a.c)) {
                return;
            }
            c.d.a.c cVar = (c.d.a.c) K0;
            Object K06 = K0(P1[1]);
            if (K06.equals("cancel")) {
                cVar.b();
                return;
            }
            if (K06.equals("clone")) {
                trim = P1[2].trim();
                clone = cVar.clone();
            } else {
                if (K06.equals("start")) {
                    cVar.j();
                    return;
                }
                if (!K06.equals("running")) {
                    if (K06.equals("duration")) {
                        cVar.u(Long.parseLong(L0(P1[2])));
                        return;
                    }
                    if (K06.equals("delay")) {
                        cVar.h(Long.parseLong(L0(P1[2])));
                        return;
                    } else {
                        if (K06.equals("target")) {
                            Object K07 = K0(P1[2]);
                            cVar.i(K07 instanceof View ? (View) K07 : m0(K07, P1[2]));
                            return;
                        }
                        return;
                    }
                }
                trim = P1[2].trim();
                e2 = cVar.e();
                clone = Boolean.valueOf(e2);
            }
        }
        T(trim, clone);
    }

    private void J0(String str) {
        String trim;
        Context context;
        int B0;
        Context context2;
        int i2;
        String[] P1 = P1(W2(str));
        int i3 = 0;
        String L0 = L0(P1[0]);
        if (!L0.equals("w")) {
            if (!L0.equals("h")) {
                if (L0.equals("pxw")) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    this.f1358c.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    trim = P1[1].trim();
                    i3 = displayMetrics.widthPixels;
                } else if (L0.equals("pxh")) {
                    WindowManager.LayoutParams attributes = this.f1358c.getWindow().getAttributes();
                    DisplayMetrics displayMetrics2 = new DisplayMetrics();
                    this.f1358c.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics2);
                    if ((attributes.flags & 1024) == 1024) {
                        trim = P1[1].trim();
                        i3 = displayMetrics2.heightPixels;
                    } else {
                        trim = P1[1].trim();
                        i3 = displayMetrics2.heightPixels - H0();
                    }
                } else if (L0.equals("pxhh")) {
                    DisplayMetrics displayMetrics3 = new DisplayMetrics();
                    this.f1358c.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics3);
                    trim = P1[1].trim();
                    i3 = displayMetrics3.heightPixels;
                } else if (L0.equals("hh")) {
                    DisplayMetrics displayMetrics4 = new DisplayMetrics();
                    this.f1358c.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics4);
                    trim = P1[1].trim();
                    context2 = this.f1359d;
                    i2 = displayMetrics4.heightPixels;
                } else if (L0.equals("pxztl")) {
                    trim = P1[1].trim();
                    i3 = I0();
                } else if (L0.equals("pxbvk")) {
                    trim = P1[1].trim();
                    i3 = B0();
                } else if (L0.equals("ztl")) {
                    trim = P1[1].trim();
                    context = this.f1359d;
                    B0 = I0();
                } else if (L0.equals("bvk")) {
                    trim = P1[1].trim();
                    context = this.f1359d;
                    B0 = B0();
                } else {
                    trim = P1[1].trim();
                }
                T(trim, Integer.valueOf(i3));
            }
            WindowManager.LayoutParams attributes2 = this.f1358c.getWindow().getAttributes();
            DisplayMetrics displayMetrics5 = new DisplayMetrics();
            this.f1358c.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics5);
            if ((attributes2.flags & 1024) == 1024) {
                trim = P1[1].trim();
                context = this.f1359d;
                B0 = displayMetrics5.heightPixels;
            } else {
                trim = P1[1].trim();
                context = this.f1359d;
                B0 = displayMetrics5.heightPixels - H0();
            }
            i3 = c.b.a.a.p.w(context, B0);
            T(trim, Integer.valueOf(i3));
        }
        DisplayMetrics displayMetrics6 = new DisplayMetrics();
        this.f1358c.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics6);
        trim = P1[1].trim();
        context2 = this.f1359d;
        i2 = displayMetrics6.widthPixels;
        i3 = c.b.a.a.p.w(context2, i2);
        T(trim, Integer.valueOf(i3));
    }

    private void J2(String str) {
        try {
            Thread.sleep(d(L0(P1(W2(str))[0])));
        } catch (InterruptedException unused) {
        }
    }

    private void J3(String str) {
        Boolean bool = Boolean.TRUE;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 1) {
            if (!K0(P1[0]).equals(bool)) {
                this.f1358c.getWindow().addFlags(2048);
                return;
            } else {
                this.f1358c.getWindow().clearFlags(2048);
                this.f1358c.getWindow().setFlags(1024, 1024);
                return;
            }
        }
        if (length == 2) {
            c.b.a.a.r.c(this.f1358c, c.b.a.a.p.o(L0(P1[0])), K0(P1[1]).equals(bool), this.f1358c.findViewById(c.b.a.a.f.a));
        } else if (length == 3) {
            c.b.a.a.r.d(this.f1358c, c.b.a.a.p.o(L0(P1[0])), K0(P1[1]).equals(bool), this.f1358c.findViewById(c.b.a.a.f.a), d(L0(P1[2])));
        }
    }

    private void K(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            String trim = P1[0].trim();
            Object K0 = K0(P1[1]);
            Boolean bool = Boolean.TRUE;
            T(trim, new AlphaAnimation(K0.equals(bool) ? 1.0f : 0.0f, K0(P1[2]).equals(bool) ? 1.0f : 0.0f));
        }
    }

    private Object K0(String str) {
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
            return this.f1359d;
        }
        boolean z2 = true;
        if (!trim.contains("[\n龘`/r}")) {
            return (trim.startsWith("\"") && trim.endsWith("\"")) ? V(a(trim.substring(1, trim.length() - 1))) : t1(trim) ? trim.contains(".") ? trim.startsWith("00") ? trim : Double.valueOf(Double.parseDouble(trim)) : trim.startsWith("00") ? trim : trim.length() < 10 ? Integer.valueOf(Integer.parseInt(trim)) : Long.valueOf(Long.parseLong(trim)) : W(trim);
        }
        String[] x2 = x2(trim, "[\n龘`/r}");
        int length = x2.length;
        Object[] objArr = new Object[length];
        double d2 = 0.0d;
        for (int i2 = 0; i2 < length; i2++) {
            objArr[i2] = K0(x2[i2].trim());
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

    private void K2(String str) {
        String[] P1 = P1(W2(str));
        T(P1[1].trim(), L0(P1[0]).trim());
    }

    @TargetApi(11)
    private void K3(String str) {
        Vibrator vibrator;
        String trim;
        Object obj;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        Object K0 = K0(P1[0]);
        if (K0 instanceof Vibrator) {
            vibrator = (Vibrator) K0;
        } else {
            vibrator = (Vibrator) this.f1359d.getSystemService("vibrator");
            T(P1[0].trim(), vibrator);
        }
        if (length == 2) {
            if (!K0(P1[1]).equals("sp")) {
                vibrator.vibrate(d(String.valueOf(r0)));
                return;
            } else {
                try {
                    vibrator.cancel();
                } catch (Exception unused) {
                }
                trim = P1[0].trim();
                obj = null;
            }
        } else {
            if (length != 3) {
                return;
            }
            Object K02 = K0(P1[1]);
            if (!K02.equals("ip")) {
                int length2 = w2(String.valueOf(K02), ' ').length;
                long[] jArr = new long[length2];
                for (int i2 = 0; i2 < length2; i2++) {
                    try {
                        jArr[i2] = Integer.parseInt(r0[i2]);
                    } catch (NumberFormatException unused2) {
                        jArr[i2] = 0;
                    }
                }
                vibrator.vibrate(jArr, K0(P1[2]).equals(Boolean.TRUE) ? 1 : -1);
                return;
            }
            if (Build.VERSION.SDK_INT >= 11) {
                trim = P1[2].trim();
                obj = Boolean.valueOf(vibrator.hasVibrator());
            } else {
                trim = P1[2].trim();
                obj = Boolean.FALSE;
            }
        }
        T(trim, obj);
    }

    private void L(String str) {
        String[] P1 = P1(W2(str));
        int length = P1.length;
        T(P1[0].trim(), null);
        Object K0 = K0(P1[1]);
        View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[1]);
        float[] fArr = new float[length - 3];
        int i2 = 0;
        for (int i3 = 3; i3 < length; i3++) {
            fArr[i2] = Float.parseFloat(L0(P1[i3]));
            i2++;
        }
        T(P1[0].trim(), c.d.a.i.K(m02, L0(P1[2]), fArr));
    }

    private String L0(String str) {
        return String.valueOf(K0(str));
    }

    private void L1(String str) {
        byte[] bArr;
        String str2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        byte[] bArr2 = null;
        int i2 = 0;
        if (length == 2) {
            byte[] t2 = c.b.a.a.f.t(this.f1359d, L0(P1[0]));
            if (t2 != null) {
                StringBuffer stringBuffer = new StringBuffer();
                int length2 = t2.length;
                while (i2 < length2) {
                    byte b2 = t2[i2];
                    stringBuffer.append(' ');
                    stringBuffer.append((int) b2);
                    i2++;
                }
                T(P1[1].trim(), stringBuffer.toString().trim());
                return;
            }
            str2 = P1[1];
        } else {
            if (length != 3) {
                if (length == 4) {
                    Object K0 = K0(P1[0]);
                    if (K0.equals("file")) {
                        T(P1[3].trim(), c.b.a.a.f.t(this.f1359d, L0(P1[2])));
                        return;
                    } else {
                        if (K0.equals("str")) {
                            Object K02 = K0(P1[1]);
                            if (K02 == null) {
                                bArr2 = L0(P1[2]).getBytes();
                            } else {
                                try {
                                    bArr2 = L0(P1[2]).getBytes(K02.toString());
                                } catch (UnsupportedEncodingException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            T(P1[3].trim(), bArr2);
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            Object K03 = K0(P1[0]);
            if (K03 == null) {
                bArr = L0(P1[1]).getBytes();
            } else {
                try {
                    bArr = L0(P1[1]).getBytes(K03.toString());
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
                T(P1[2].trim(), stringBuffer2.toString().trim());
                return;
            }
            str2 = P1[2];
        }
        T(str2.trim(), null);
    }

    private void L2(String str) {
        String[] P1 = P1(W2(str));
        T(P1[1].trim(), L0(P1[0]).toUpperCase());
    }

    private int L3(String str) {
        String X2 = X2(str);
        String b02 = b0(str);
        while (c(X2)) {
            int e2 = e(b02);
            if (e2 == 2) {
                return 2;
            }
            if (e2 == 1) {
                break;
            }
        }
        return 0;
    }

    private void M(String str) {
        String[] P1 = P1(W2(str));
        int length = P1.length;
        T(P1[0].trim(), null);
        c.d.a.c cVar = new c.d.a.c();
        c.d.a.a[] aVarArr = new c.d.a.a[length - 2];
        int i2 = 0;
        for (int i3 = 2; i3 < length; i3++) {
            Object K0 = K0(P1[i3]);
            if (K0 instanceof c.d.a.a) {
                aVarArr[i2] = (c.d.a.a) K0;
                i2++;
            }
        }
        Object K02 = K0(P1[1]);
        if (K02.equals("sequen")) {
            cVar.s(aVarArr);
        } else if (K02.equals("together")) {
            cVar.t(aVarArr);
        }
        T(P1[0].trim(), cVar);
    }

    private String M0(String str) {
        Object K0 = K0(str);
        if (K0 == null) {
            return null;
        }
        return K0.toString();
    }

    public static String[] M1(String str, char c2, char c3) {
        char c4;
        if (o == 0) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(c2);
            stringBuffer.append(c3);
            return c.b.a.a.q.d(str, stringBuffer.toString());
        }
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer2 = new StringBuffer();
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i2 = 0;
        boolean z2 = false;
        loop0: while (i2 < length) {
            if (charArray[i2] == '\"') {
                stringBuffer2.append(charArray[i2]);
                do {
                    i2++;
                    if (i2 >= length) {
                        break loop0;
                    }
                    stringBuffer2.append(charArray[i2]);
                } while (charArray[i2] != '\"');
                z2 = true;
                i2++;
            } else {
                if (charArray[i2] == c2) {
                    if (z2) {
                        z2 = false;
                    }
                    i2++;
                    if (i2 >= length) {
                        break;
                    }
                    if (charArray[i2] == c3) {
                        String trim = stringBuffer2.toString().trim();
                        if (trim.length() > 0) {
                            arrayList.add(trim);
                        }
                    }
                    stringBuffer2.setLength(0);
                } else if (charArray[i2] == '+') {
                    if (z2) {
                        z2 = false;
                    }
                    stringBuffer2.append("[\n龘`/r}");
                } else {
                    if (charArray[i2] == '=' || charArray[i2] == '!' || charArray[i2] == '>' || charArray[i2] == '<' || charArray[i2] == '?' || charArray[i2] == '*' || charArray[i2] == '|' || charArray[i2] == '&' || charArray[i2] == '[') {
                        if (z2) {
                            z2 = false;
                        }
                        c4 = charArray[i2];
                    } else {
                        if (z2 && charArray[i2] != ' ') {
                            return null;
                        }
                        c4 = charArray[i2];
                    }
                    stringBuffer2.append(c4);
                }
                i2++;
            }
        }
        String trim2 = stringBuffer2.toString().trim();
        if (trim2.length() > 0) {
            arrayList.add(trim2);
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private void M2(String str) {
        String trim;
        String E;
        String[] P1 = P1(W2(str));
        if (P1.length == 2) {
            trim = P1[1].trim();
            E = c.b.a.a.p.D(L0(P1[0]));
        } else {
            if (P1.length != 4) {
                return;
            }
            trim = P1[3].trim();
            E = c.b.a.a.p.E(L0(P1[0]), L0(P1[1]));
        }
        T(trim, E);
    }

    private void M3(String str) {
        View p2;
        String[] P1 = P1(W2(str));
        if (P1.length == 2) {
            Object K0 = K0(P1[0]);
            if (String.valueOf(K0).endsWith(".yul")) {
                T(P1[1].trim(), c.b.a.a.f.p(this.f1359d, String.valueOf(K0)));
                return;
            }
            Object K02 = K0(P1[1]);
            if (String.valueOf(K02).endsWith(".yul")) {
                View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[0]);
                if (m02 == null || (p2 = c.b.a.a.f.p(this.f1359d, String.valueOf(K02))) == null) {
                    return;
                }
                ((ViewGroup) m02).addView(p2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void N(String str) {
        String trim;
        Boolean bool;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            Object K0 = K0(P1[1]);
            if (!(K0 instanceof Boolean)) {
                if (K0.equals("start")) {
                    Object K02 = K0(P1[0]);
                    if (K02 instanceof AnimationDrawable) {
                        ((AnimationDrawable) K02).start();
                        return;
                    }
                    return;
                }
                if (K0.equals("stop")) {
                    Object K03 = K0(P1[0]);
                    if (K03 instanceof AnimationDrawable) {
                        ((AnimationDrawable) K03).stop();
                        return;
                    }
                    return;
                }
                return;
            }
            AnimationDrawable animationDrawable = new AnimationDrawable();
            animationDrawable.setOneShot(!K0(P1[1]).equals(Boolean.TRUE));
            trim = P1[0].trim();
            bool = animationDrawable;
        } else {
            if (length != 3) {
                return;
            }
            Object K04 = K0(P1[0]);
            if (!(K04 instanceof AnimationDrawable)) {
                return;
            }
            AnimationDrawable animationDrawable2 = (AnimationDrawable) K04;
            Object K05 = K0(P1[1]);
            if (!K05.equals("running")) {
                animationDrawable2.addFrame(K05 instanceof Drawable ? (Drawable) K05 : K05 instanceof Bitmap ? new BitmapDrawable((Bitmap) K05) : com.iapp.app.i.m(String.valueOf(K05), this.f1359d), d(L0(P1[2])));
                return;
            } else {
                trim = P1[2].trim();
                bool = Boolean.valueOf(animationDrawable2.isRunning());
            }
        }
        T(trim, bool);
    }

    private String N0(String str) {
        return c.b.a.a.f.o(this.f1359d, str);
    }

    public static int N1(String str, char c2) {
        if (o == 0) {
            return str.indexOf(c2);
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i2 = 0;
        while (i2 < length) {
            if (charArray[i2] != '\"') {
                if (charArray[i2] == c2) {
                    return i2;
                }
                i2++;
            }
            do {
                i2++;
                if (i2 >= length) {
                    return -1;
                }
            } while (charArray[i2] != '\"');
            i2++;
        }
        return -1;
    }

    private void N2(String str) {
        ((ClipboardManager) this.f1359d.getSystemService("clipboard")).setText(L0(P1(W2(str))[0]));
    }

    private void N3(String str) {
        T(P1(W2(str))[1].trim(), Integer.valueOf(c.b.a.a.p.l(this.f1359d, d(L0(r3[0])))));
    }

    private void O(String str) {
        String[] P1 = P1(X2(str));
        ArrayList arrayList = new ArrayList();
        String[] w2 = w2(str, '\n');
        String str2 = "";
        int i2 = 0;
        for (int i3 = 0; i3 < w2.length; i3++) {
            if (w2[i3].equals("{")) {
                i2++;
            } else if (w2[i3].equals("}")) {
                i2--;
            } else if (i2 == 0) {
                if (w2[i3].startsWith("else")) {
                    str2 = w2[i3] + "\n";
                }
            }
            str2 = str2 + w2[i3] + "\n";
            if (i2 == 0) {
                arrayList.add(b0(str2));
            }
        }
        Object K0 = K0(P1[0]);
        if (K0 instanceof Animation) {
            new com.iapp.app.l(this, (Animation) K0, arrayList.toArray());
        } else if (K0 instanceof c.d.a.a) {
            new com.iapp.app.l(this, (c.d.a.a) K0, arrayList.toArray());
        }
    }

    private String O0(String str, String str2, String str3) {
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

    public static int O1(String str, char c2, char c3) {
        if (o == 0) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(c2);
            stringBuffer.append(c3);
            return str.indexOf(stringBuffer.toString());
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i2 = 0;
        while (i2 < length) {
            if (charArray[i2] != '\"') {
                if (charArray[i2] != c2) {
                    continue;
                } else {
                    i2++;
                    if (i2 >= length) {
                        return -1;
                    }
                    if (charArray[i2] == c3) {
                        return i2 - 1;
                    }
                }
                i2++;
            }
            do {
                i2++;
                if (i2 >= length) {
                    return -1;
                }
            } while (charArray[i2] != '\"');
            i2++;
        }
        return -1;
    }

    private void O2(String str) {
        P2(this.f1359d, K0(P1(W2(str))[0]));
    }

    private void O3(String str) {
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length > 1) {
            Object[] objArr = new Object[length];
            objArr[0] = "Activity";
            objArr[1] = this.f1359d;
            if (length > 2) {
                for (int i2 = 2; i2 < length; i2++) {
                    objArr[i2] = K0(P1[i2]);
                }
            }
            T(P1[0].trim(), c.b.a.a.f.n(L0(P1[1]), objArr));
        }
    }

    private void P(String str) {
        String trim;
        RotateAnimation rotateAnimation;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 3) {
            trim = P1[0].trim();
            rotateAnimation = new RotateAnimation(Float.parseFloat(L0(P1[1])), Float.parseFloat(L0(P1[2])));
        } else {
            if (length != 7) {
                return;
            }
            trim = P1[0].trim();
            rotateAnimation = new RotateAnimation(Float.parseFloat(L0(P1[1])), Float.parseFloat(L0(P1[2])), d(L0(P1[3])), Float.parseFloat(L0(P1[4])), d(L0(P1[5])), Float.parseFloat(L0(P1[6])));
        }
        T(trim, rotateAnimation);
    }

    private GradientDrawable.Orientation P0(String str) {
        return str.equals("topbottom") ? GradientDrawable.Orientation.TOP_BOTTOM : str.equals("trbl") ? GradientDrawable.Orientation.TR_BL : str.equals("rightleft") ? GradientDrawable.Orientation.RIGHT_LEFT : str.equals("brtl") ? GradientDrawable.Orientation.BR_TL : str.equals("bottomtop") ? GradientDrawable.Orientation.BOTTOM_TOP : str.equals("bltr") ? GradientDrawable.Orientation.BL_TR : str.equals("leftright") ? GradientDrawable.Orientation.LEFT_RIGHT : str.equals("tlbr") ? GradientDrawable.Orientation.TL_BR : GradientDrawable.Orientation.TOP_BOTTOM;
    }

    public static String[] P1(String str) {
        if (o == 0) {
            return c.b.a.a.q.b(str, ',');
        }
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i2 = 0;
        boolean z2 = false;
        loop0: while (i2 < length) {
            if (charArray[i2] == '\"') {
                stringBuffer.append(charArray[i2]);
                do {
                    i2++;
                    if (i2 >= length) {
                        break loop0;
                    }
                    stringBuffer.append(charArray[i2]);
                } while (charArray[i2] != '\"');
                z2 = true;
            } else if (charArray[i2] == ',') {
                if (z2) {
                    z2 = false;
                }
                String trim = stringBuffer.toString().trim();
                if (trim.length() > 0) {
                    arrayList.add(trim);
                }
                stringBuffer.setLength(0);
            } else if (charArray[i2] == '+') {
                if (z2) {
                    z2 = false;
                }
                stringBuffer.append("[\n龘`/r}");
            } else {
                if (z2 && charArray[i2] != ' ') {
                    return null;
                }
                stringBuffer.append(charArray[i2]);
            }
            i2++;
        }
        String trim2 = stringBuffer.toString().trim();
        if (trim2.length() > 0) {
            arrayList.add(trim2);
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static void P2(Context context, Object obj) {
        c.b.a.a.f.A(context, obj);
    }

    private void P3(String str) {
        T(P1(W2(str))[1].trim(), Integer.valueOf(c.b.a.a.p.w(this.f1359d, d(L0(r3[0])))));
    }

    private void Q(String str) {
        String trim;
        ScaleAnimation scaleAnimation;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 5) {
            trim = P1[0].trim();
            scaleAnimation = new ScaleAnimation(Float.parseFloat(L0(P1[1])), Float.parseFloat(L0(P1[2])), Float.parseFloat(L0(P1[3])), Float.parseFloat(L0(P1[4])));
        } else {
            if (length != 9) {
                return;
            }
            trim = P1[0].trim();
            scaleAnimation = new ScaleAnimation(Float.parseFloat(L0(P1[1])), Float.parseFloat(L0(P1[2])), Float.parseFloat(L0(P1[3])), Float.parseFloat(L0(P1[4])), d(L0(P1[5])), Float.parseFloat(L0(P1[6])), d(L0(P1[7])), Float.parseFloat(L0(P1[8])));
        }
        T(trim, scaleAnimation);
    }

    @TargetApi(11)
    private void Q1(com.iapp.app.x5.WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
    }

    private void Q2(String str) {
        P2(this.f1359d, str);
    }

    private void Q3(String str) {
        T(P1(W2(str))[1].trim(), Integer.valueOf(c.b.a.a.p.x(this.f1359d, d(L0(r3[0])))));
    }

    private void R(String str) {
        String[] P1 = P1(W2(str));
        int length = P1.length;
        AnimationSet animationSet = new AnimationSet(K0(P1[1]).equals(Boolean.TRUE));
        for (int i2 = 2; i2 < length; i2++) {
            Object K0 = K0(P1[i2]);
            if (K0 instanceof Animation) {
                animationSet.addAnimation((Animation) K0);
            }
        }
        T(P1[0].trim(), animationSet);
    }

    private String R0(String str) {
        String c2;
        String c3;
        Intent launchIntentForPackage = this.f1359d.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null || (c2 = c.b.a.a.p.c(launchIntentForPackage.toString(), "cmp=", " ")) == null || (c3 = c.b.a.a.p.c(c2, "/", null)) == null) {
            return "";
        }
        if (!c3.startsWith(".")) {
            return c3;
        }
        return str + c3;
    }

    private void R1(String str) {
        String trim;
        com.iapp.app.k kVar;
        String L0;
        String str2;
        Object valueOf;
        String trim2;
        Object c2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 1) {
            trim2 = P1[0].trim();
            c2 = new com.iapp.app.k(this.f1359d);
        } else {
            if (length == 2) {
                T(P1[1].trim(), new com.iapp.app.k(this.f1359d, N0(L0(P1[0]))));
                return;
            }
            if (length != 3) {
                if (length == 4) {
                    Object K0 = K0(P1[0]);
                    if (!(K0 instanceof com.iapp.app.k)) {
                        return;
                    }
                    trim = P1[3].trim();
                    kVar = (com.iapp.app.k) K0;
                    L0 = L0(P1[1]);
                    str2 = P1[2];
                } else {
                    if (length != 5) {
                        return;
                    }
                    Object K02 = K0(P1[0]);
                    if (!(K02 instanceof com.iapp.app.k)) {
                        return;
                    }
                    if (K0(P1[3]).equals(Boolean.TRUE)) {
                        trim = P1[4].trim();
                        valueOf = Integer.valueOf(((com.iapp.app.k) K02).b(L0(P1[1]), L0(P1[2])));
                        T(trim, valueOf);
                        return;
                    } else {
                        trim = P1[4].trim();
                        kVar = (com.iapp.app.k) K02;
                        L0 = L0(P1[1]);
                        str2 = P1[2];
                    }
                }
                valueOf = kVar.d(L0, L0(str2));
                T(trim, valueOf);
                return;
            }
            Object K03 = K0(P1[0]);
            if (!(K03 instanceof com.iapp.app.k)) {
                return;
            }
            Object K04 = K0(P1[1]);
            if (K04.equals("asset")) {
                trim2 = P1[2].trim();
                c2 = ((com.iapp.app.k) K03).a();
            } else {
                if (!K04.equals("resources")) {
                    return;
                }
                trim2 = P1[2].trim();
                c2 = ((com.iapp.app.k) K03).c();
            }
        }
        T(trim2, c2);
    }

    private void R2(String str) {
        String trim;
        Object valueOf;
        long parseDouble;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            trim = P1[1].trim();
            parseDouble = (long) (Double.parseDouble(L0(P1[1])) % Double.parseDouble(L0(P1[0])));
        } else {
            if (length != 3) {
                if (length == 4 && K0(P1[2]).equals(Boolean.TRUE)) {
                    trim = P1[3].trim();
                    valueOf = Double.valueOf(Double.parseDouble(L0(P1[0])) % Double.parseDouble(L0(P1[1])));
                    T(trim, valueOf);
                }
                return;
            }
            trim = P1[2].trim();
            parseDouble = (long) (Double.parseDouble(L0(P1[0])) % Double.parseDouble(L0(P1[1])));
        }
        valueOf = Long.valueOf(parseDouble);
        T(trim, valueOf);
    }

    private void R3(String str) {
        T(P1(W2(str))[1].trim(), Integer.valueOf(c.b.a.a.p.A(this.f1359d, d(L0(r3[0])))));
    }

    private void S(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 5) {
            T(P1[0].trim(), new TranslateAnimation(Float.parseFloat(L0(P1[1])), Float.parseFloat(L0(P1[2])), Float.parseFloat(L0(P1[3])), Float.parseFloat(L0(P1[4]))));
        }
    }

    private int S0(String str) {
        if (str.equals("-1")) {
            return -1;
        }
        if (str.equals("-2")) {
            return -2;
        }
        return str.endsWith("dp") ? c.b.a.a.p.l(this.f1359d, Float.parseFloat(str.substring(0, str.length() - 2))) : d(str);
    }

    private void S1(String str) {
        String[] P1 = P1(W2(str));
        int length = P1.length;
        try {
            if (length == 0) {
                c.b.a.a.k.d(this.f1358c, this.f1358c.getPackageManager().getPackageInfo(this.f1358c.getPackageName(), 4096).requestedPermissions);
                return;
            }
            boolean z2 = true;
            if (length != 1) {
                if (length == 2) {
                    if (ContextCompat.checkSelfPermission(this.f1358c, String.valueOf(K0(P1[1]))) == 0) {
                        z2 = false;
                    }
                    T(P1[0].trim(), Boolean.valueOf(z2));
                    return;
                }
                return;
            }
            Object K0 = K0(P1[0]);
            if (!K0.getClass().isArray()) {
                c.b.a.a.k.d(this.f1358c, new String[]{String.valueOf(K0)});
                return;
            }
            int length2 = Array.getLength(K0);
            String[] strArr = new String[length2];
            for (int i2 = 0; i2 < length2; i2++) {
                strArr[i2] = String.valueOf(Array.get(K0, i2));
            }
            c.b.a.a.k.d(this.f1358c, strArr);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void S2(String str) {
        String b02 = b0(str);
        c0 c0Var = new c0();
        this.b.put(String.valueOf(c0Var.getId()), b02);
        c0Var.setName("CeShi_" + c0Var.getId());
        c0Var.start();
    }

    private void T0(String str) {
        String trim;
        Object component;
        String str2;
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        String L0 = L0(P1[1]);
        Intent intent = K0 instanceof Intent ? (Intent) K0 : null;
        if (L0.equals("action")) {
            if (intent == null) {
                str2 = P1[2];
                T(str2.trim(), null);
            } else {
                trim = P1[2].trim();
                component = intent.getAction();
                T(trim, component);
                return;
            }
        }
        if (L0.equals("type")) {
            if (intent == null) {
                str2 = P1[2];
                T(str2.trim(), null);
            } else {
                trim = P1[2].trim();
                component = intent.getType();
                T(trim, component);
                return;
            }
        }
        if (L0.equals("extra")) {
            if (intent != null) {
                T(P1[3].trim(), intent.getExtras().get(L0(P1[2])));
                return;
            }
            str2 = P1[3];
        } else if (L0.equals("flags")) {
            if (intent != null) {
                trim = P1[2].trim();
                component = Integer.valueOf(intent.getFlags());
                T(trim, component);
                return;
            }
            str2 = P1[2];
        } else if (L0.equals("data")) {
            if (intent != null) {
                trim = P1[2].trim();
                component = intent.getData();
                T(trim, component);
                return;
            }
            str2 = P1[2];
        } else if (L0.equals("datastring")) {
            if (intent != null) {
                trim = P1[2].trim();
                component = intent.getDataString();
                T(trim, component);
                return;
            }
            str2 = P1[2];
        } else {
            if (!L0.equals("component")) {
                return;
            }
            if (intent != null) {
                trim = P1[2].trim();
                component = intent.getComponent();
                T(trim, component);
                return;
            }
            str2 = P1[2];
        }
        T(str2.trim(), null);
    }

    private void T1(String str) {
        String substring = str.substring(str.indexOf(40) + 1, str.lastIndexOf(41));
        int indexOf = substring.indexOf(44);
        String trim = substring.substring(0, indexOf).trim();
        String replace = trim.replace('+', ',').replace('-', ',').replace('*', ',').replace('/', ',').replace('(', ',').replace(')', ',').replace('%', ',');
        String str2 = " " + trim.replace("+", " + ").replace("-", " - ").replace("*", " * ").replace("/", " / ").replace("(", " ( ").replace(")", " ) ").replace("%", " % ") + " ";
        for (String str3 : w2(replace, ',')) {
            str2 = str2.replace(" " + str3.trim() + " ", L0(str3));
        }
        long j2 = -1;
        try {
            j2 = (long) d.a.h.f(str2).a();
        } catch (d.b.e e2) {
            e2.printStackTrace();
        }
        T(substring.substring(indexOf + 1).trim(), Long.valueOf(j2));
    }

    private void T2(String str) {
        String trim;
        Integer num;
        int height;
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            Object K0 = K0(P1[0]);
            if (K0 instanceof Bitmap) {
                String L0 = L0(P1[1]);
                if (L0.equals("w")) {
                    trim = P1[2].trim();
                    height = ((Bitmap) K0).getWidth();
                } else {
                    if (!L0.equals("h")) {
                        return;
                    }
                    trim = P1[2].trim();
                    height = ((Bitmap) K0).getHeight();
                }
                num = Integer.valueOf(height);
            } else {
                trim = P1[2].trim();
                num = null;
            }
            T(trim, num);
        }
    }

    private void U(String str, Object obj, int i2) {
        HashMap<String, Object> hashMap;
        if (str.equals("null")) {
            return;
        }
        if (i2 == 0) {
            hashMap = this.a;
        } else if (i2 == 1) {
            hashMap = k;
        } else if (i2 != 2) {
            return;
        } else {
            hashMap = j;
        }
        hashMap.put(str, obj);
    }

    public boolean U0(t tVar, String str, String str2) {
        String c2 = c.b.a.a.p.c(str2, "<eventItme type=\"" + str + "\">", "</eventItme>");
        boolean z2 = false;
        if (c2 == null) {
            return false;
        }
        String trim = c2.trim();
        if (trim.startsWith("[true]")) {
            z2 = true;
            trim = trim.substring(6);
        }
        tVar.f(trim);
        return z2;
    }

    private void U1(String str) {
        String substring = str.substring(str.indexOf(40) + 1, str.lastIndexOf(41));
        int indexOf = substring.indexOf(44);
        String trim = substring.substring(0, indexOf).trim();
        String replace = trim.replace('+', ',').replace('-', ',').replace('*', ',').replace('/', ',').replace('(', ',').replace(')', ',').replace('%', ',');
        String str2 = " " + trim.replace("+", " + ").replace("-", " - ").replace("*", " * ").replace("/", " / ").replace("(", " ( ").replace(")", " ) ").replace("%", " % ") + " ";
        for (String str3 : w2(replace, ',')) {
            str2 = str2.replace(" " + str3.trim() + " ", L0(str3));
        }
        double d2 = -1.0d;
        try {
            d2 = d.a.h.f(str2).a();
        } catch (d.b.e e2) {
            e2.printStackTrace();
        }
        T(substring.substring(indexOf + 1).trim(), String.format("%.2f", Double.valueOf(d2)));
    }

    private void U2(String str) {
        String trim;
        Bitmap bitmap;
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            Object K0 = K0(P1[0]);
            if (K0 instanceof Bitmap) {
                String L0 = L0(P1[1]);
                if (L0.equals("x")) {
                    trim = P1[2].trim();
                    bitmap = c.b.a.a.i.f((Bitmap) K0, 0);
                } else {
                    if (!L0.equals("y")) {
                        return;
                    }
                    trim = P1[2].trim();
                    bitmap = c.b.a.a.i.f((Bitmap) K0, 1);
                }
            } else {
                trim = P1[2].trim();
                bitmap = null;
            }
            T(trim, bitmap);
        }
    }

    public static String V(String str) {
        if (!str.contains("\\")) {
            return str;
        }
        if (str.contains("\\\\")) {
            str = str.replace("\\\\", "\\U5c");
        }
        if (str.contains("\\t")) {
            str = str.replace("\\t", "\t");
        }
        if (str.contains("\\n")) {
            str = str.replace("\\n", "\n");
        }
        if (str.contains("\\r")) {
            str = str.replace("\\r", "\r");
        }
        if (str.contains("\\b")) {
            str = str.replace("\\b", "\b");
        }
        if (str.contains("\\f")) {
            str = str.replace("\\f", "\f");
        }
        if (!str.contains("\\U")) {
            return str;
        }
        if (str.contains("\\U5c")) {
            str = str.replace("\\U5c", "\\");
        }
        if (str.contains("\\U28")) {
            str = str.replace("\\U28", "(");
        }
        if (str.contains("\\U29")) {
            str = str.replace("\\U29", ")");
        }
        if (str.contains("\\U2c")) {
            str = str.replace("\\U2c", ",");
        }
        if (str.contains("\\U3d")) {
            str = str.replace("\\U3d", "=");
        }
        if (str.contains("\\U21")) {
            str = str.replace("\\U21", "!");
        }
        if (str.contains("\\U3e")) {
            str = str.replace("\\U3e", ">");
        }
        if (str.contains("\\U3c")) {
            str = str.replace("\\U3c", "<");
        }
        if (str.contains("\\U3f")) {
            str = str.replace("\\U3f", "?");
        }
        if (str.contains("\\U2a")) {
            str = str.replace("\\U2a", "*");
        }
        if (str.contains("\\U2b")) {
            str = str.replace("\\U2b", "+");
        }
        if (str.contains("\\U7b")) {
            str = str.replace("\\U7b", "{");
        }
        if (str.contains("\\U7d")) {
            str = str.replace("\\U7d", "}");
        }
        if (str.contains("\\U7c")) {
            str = str.replace("\\U7c", "|");
        }
        if (str.contains("\\U26")) {
            str = str.replace("\\U26", "&");
        }
        return str.contains("\\U22") ? str.replace("\\U22", "\"") : str;
    }

    private void V0(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            T(P1[2].trim(), ((ArrayList) K0(P1[0])).get(d(L0(P1[1]))));
        }
    }

    private void V1(String str) {
        Object createBitmap;
        String str2;
        String str3;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        Object K0 = K0(P1[0]);
        Bitmap v2 = K0 instanceof Bitmap ? (Bitmap) K0 : c.b.a.a.f.v(this.f1359d, String.valueOf(K0));
        if (length == 2) {
            if (v2 != null) {
                T(P1[1].trim(), v2);
                return;
            }
            str3 = P1[1];
        } else if (length == 6) {
            if (v2 != null) {
                createBitmap = Bitmap.createBitmap(v2, d(L0(P1[1])), d(L0(P1[2])), d(L0(P1[3])), d(L0(P1[4])));
                str2 = P1[5];
                T(str2.trim(), createBitmap);
                return;
            }
            str3 = P1[5];
        } else {
            if (length != 7) {
                return;
            }
            if (v2 != null) {
                Matrix matrix = new Matrix();
                matrix.preRotate(d(L0(P1[5])));
                createBitmap = Bitmap.createBitmap(v2, d(L0(P1[1])), d(L0(P1[2])), d(L0(P1[3])), d(L0(P1[4])), matrix, true);
                str2 = P1[6];
                T(str2.trim(), createBitmap);
                return;
            }
            str3 = P1[6];
        }
        T(str3.trim(), null);
    }

    private void V2(String str) {
        String trim;
        Formatter format;
        String[] P1 = P1(W2(str));
        try {
            T(P1[1].trim(), c.b.a.a.p.u(d(L0(P1[0]))));
        } catch (NumberFormatException unused) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(new Date().getTime());
            Formatter formatter = new Formatter(Locale.CHINA);
            P1[0] = L0(P1[0]);
            if (P1[0].length() == 1) {
                trim = P1[1].trim();
                format = formatter.format("%1$t" + P1[0], calendar);
            } else {
                trim = P1[1].trim();
                format = formatter.format(P1[0], calendar);
            }
            T(trim, format.toString());
        }
    }

    private Object W(String str) {
        HashMap<String, Object> hashMap;
        int indexOf = str.indexOf(46);
        if (indexOf != -1) {
            String trim = str.substring(0, indexOf).trim();
            str = str.substring(indexOf + 1).trim();
            if (trim.equals("ss")) {
                hashMap = k;
            } else if (trim.equals("sss")) {
                hashMap = j;
            }
            return hashMap.get(str);
        }
        hashMap = this.a;
        return hashMap.get(str);
    }

    private void W0(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            T(P1[2].trim(), Integer.valueOf(((ArrayList) K0(P1[0])).indexOf(K0(P1[1]))));
        }
    }

    private void W1(String str) {
        String trim;
        Object valueOf;
        long parseDouble;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            trim = P1[1].trim();
            parseDouble = (long) (Double.parseDouble(L0(P1[1])) * Double.parseDouble(L0(P1[0])));
        } else {
            if (length != 3) {
                if (length == 4 && K0(P1[2]).equals(Boolean.TRUE)) {
                    trim = P1[3].trim();
                    valueOf = Double.valueOf(Double.parseDouble(L0(P1[0])) * Double.parseDouble(L0(P1[1])));
                    T(trim, valueOf);
                }
                return;
            }
            trim = P1[2].trim();
            parseDouble = (long) (Double.parseDouble(L0(P1[0])) * Double.parseDouble(L0(P1[1])));
        }
        valueOf = Long.valueOf(parseDouble);
        T(trim, valueOf);
    }

    private String W2(String str) {
        return a(str.substring(str.indexOf(40) + 1, str.lastIndexOf(41)));
    }

    private void X0(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            T(P1[2].trim(), Boolean.valueOf(((ArrayList) K0(P1[0])).contains(K0(P1[1]))));
        }
    }

    private void X1(String str) {
        String trim;
        Object valueOf;
        long parseDouble;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            trim = P1[1].trim();
            parseDouble = (long) (Double.parseDouble(L0(P1[1])) / Double.parseDouble(L0(P1[0])));
        } else {
            if (length != 3) {
                if (length == 4 && K0(P1[2]).equals(Boolean.TRUE)) {
                    trim = P1[3].trim();
                    valueOf = Double.valueOf(Double.parseDouble(L0(P1[0])) / Double.parseDouble(L0(P1[1])));
                    T(trim, valueOf);
                }
                return;
            }
            trim = P1[2].trim();
            parseDouble = (long) (Double.parseDouble(L0(P1[0])) / Double.parseDouble(L0(P1[1])));
        }
        valueOf = Long.valueOf(parseDouble);
        T(trim, valueOf);
    }

    private String X2(String str) {
        int indexOf = str.indexOf(10);
        if (indexOf == -1) {
            indexOf = str.length();
        }
        return a(str.substring(str.indexOf(40) + 1, str.lastIndexOf(41, indexOf)));
    }

    private boolean Y(String str, int i2) {
        Object W;
        Object valueOf;
        int indexOf = str.indexOf(61);
        if (indexOf == -1) {
            String trim = str.substring(i2 + 2).trim();
            if (t1(trim) || trim.indexOf(32) != -1) {
                return false;
            }
            U(trim, null, i2);
            return true;
        }
        String trim2 = str.substring(i2 + 2, indexOf).trim();
        if (t1(trim2) || trim2.indexOf(32) != -1) {
            return false;
        }
        String trim3 = str.substring(indexOf + 1).trim();
        if (!trim3.contains("\"")) {
            if (trim3.equals("null")) {
                U(trim2, null, i2);
                return true;
            }
            if (trim3.equals("true")) {
                valueOf = Boolean.TRUE;
            } else if (trim3.equals("false")) {
                valueOf = Boolean.FALSE;
            } else if (!t1(trim3)) {
                W = W(trim3);
            } else {
                if (!trim3.contains(".")) {
                    if (trim2.startsWith("00")) {
                        U(trim2, trim3, i2);
                    }
                    U(trim2, trim3.length() < 10 ? Integer.valueOf(Integer.parseInt(trim3)) : Long.valueOf(Long.parseLong(trim3)), i2);
                    return true;
                }
                if (trim3.startsWith("00")) {
                    U(trim2, trim3, i2);
                }
                valueOf = Double.valueOf(Double.parseDouble(trim3));
            }
            U(trim2, valueOf, i2);
            return true;
        }
        if (!trim3.startsWith("\"") || !trim3.endsWith("\"")) {
            return false;
        }
        W = V(a(trim3.substring(1, trim3.length() - 1)));
        U(trim2, W, i2);
        return true;
    }

    private void Y0(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 2) {
            T(P1[1].trim(), Integer.valueOf(((ArrayList) K0(P1[0])).size()));
        }
    }

    private void Y1(String str) {
        u = Integer.parseInt(L0(P1(W2(str))[0]));
    }

    private void Y2(String str) {
        String trim;
        String[] P1 = P1(W2(str));
        if (P1.length == 2) {
            Object K0 = K0(P1[0]);
            View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[0]);
            Bitmap bitmap = null;
            if (m02 == null) {
                T(P1[1].trim(), null);
                return;
            }
            if (m02 instanceof ImageView) {
                trim = P1[1].trim();
                bitmap = c.b.a.a.i.a((ImageView) m02);
            } else {
                trim = P1[1].trim();
            }
            T(trim, bitmap);
        }
    }

    private int Z() {
        this.e = 9865198;
        this.f = 9865197;
        return 9865197 + 1;
    }

    private void Z0(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            T(P1[2].trim(), Integer.valueOf(((ArrayList) K0(P1[0])).lastIndexOf(K0(P1[1]))));
        }
    }

    private void Z1(String str) {
        Object matcher;
        String trim;
        String trim2;
        Object group;
        int end;
        int end2;
        boolean find;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        Object K0 = K0(P1[0]);
        if (K0 instanceof Matcher) {
            Matcher matcher2 = (Matcher) K0;
            String L0 = L0(P1[1]);
            if (L0.equals("sral")) {
                trim2 = P1[3].trim();
                group = matcher2.replaceAll(L0(P1[2]));
            } else if (L0.equals("srft")) {
                trim2 = P1[3].trim();
                group = matcher2.replaceFirst(L0(P1[2]));
            } else {
                if (L0.equals("ms")) {
                    trim = P1[2].trim();
                    find = matcher2.matches();
                } else if (!L0.equals("find")) {
                    if (L0.equals("gl")) {
                        trim = P1[2].trim();
                        end = matcher2.groupCount();
                    } else if (L0.equals("start")) {
                        if (length == 4) {
                            trim2 = P1[3].trim();
                            end2 = matcher2.start(d(L0(P1[2])));
                            group = Integer.valueOf(end2);
                        } else {
                            trim = P1[2].trim();
                            end = matcher2.start();
                        }
                    } else if (L0.equals("end")) {
                        if (length == 4) {
                            trim2 = P1[3].trim();
                            end2 = matcher2.end(d(L0(P1[2])));
                            group = Integer.valueOf(end2);
                        } else {
                            trim = P1[2].trim();
                            end = matcher2.end();
                        }
                    } else {
                        if (!L0.equals("group")) {
                            return;
                        }
                        if (length == 4) {
                            trim2 = P1[3].trim();
                            group = matcher2.group(d(L0(P1[2])));
                        } else {
                            trim = P1[2].trim();
                            matcher = matcher2.group();
                        }
                    }
                    matcher = Integer.valueOf(end);
                } else if (length == 4) {
                    trim2 = P1[3].trim();
                    group = Boolean.valueOf(matcher2.find(d(L0(P1[2]))));
                } else {
                    trim = P1[2].trim();
                    find = matcher2.find();
                }
                matcher = Boolean.valueOf(find);
            }
            T(trim2, group);
            return;
        }
        if (!(K0 instanceof String)) {
            return;
        }
        matcher = Pattern.compile(L0(P1[1]), d(L0(P1[2]))).matcher(String.valueOf(K0));
        trim = P1[3].trim();
        T(trim, matcher);
    }

    private void Z2(String str) {
        String str2;
        Bitmap e2;
        String str3;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 3) {
            Object K0 = K0(P1[0]);
            if (!(K0 instanceof Bitmap)) {
                str2 = P1[2];
                T(str2.trim(), null);
            } else {
                e2 = c.b.a.a.i.d((Bitmap) K0, d(L0(P1[1])));
                str3 = P1[2];
                T(str3.trim(), e2);
            }
        }
        if (length == 4) {
            Object K02 = K0(P1[0]);
            if (!(K02 instanceof Bitmap)) {
                str2 = P1[3];
                T(str2.trim(), null);
            } else {
                e2 = c.b.a.a.i.e((Bitmap) K02, d(L0(P1[1])), d(L0(P1[2])));
                str3 = P1[3];
                T(str3.trim(), e2);
            }
        }
    }

    public static String a(String str) {
        if (!str.contains("\\")) {
            return str;
        }
        if (str.contains("\\\\")) {
            str = str.replace("\\\\", "\\U5c");
        }
        if (str.contains("\\(")) {
            str = str.replace("\\(", "\\U28");
        }
        if (str.contains("\\)")) {
            str = str.replace("\\)", "\\U29");
        }
        if (str.contains("\\,")) {
            str = str.replace("\\,", "\\U2c");
        }
        if (str.contains("\\=")) {
            str = str.replace("\\=", "\\U3d");
        }
        if (str.contains("\\!")) {
            str = str.replace("\\!", "\\U21");
        }
        if (str.contains("\\>")) {
            str = str.replace("\\>", "\\U3e");
        }
        if (str.contains("\\<")) {
            str = str.replace("\\<", "\\U3c");
        }
        if (str.contains("\\?")) {
            str = str.replace("\\?", "\\U3f");
        }
        if (str.contains("\\*")) {
            str = str.replace("\\*", "\\U2a");
        }
        if (str.contains("\\+")) {
            str = str.replace("\\+", "\\U2b");
        }
        if (str.contains("\\{")) {
            str = str.replace("\\{", "\\U7b");
        }
        if (str.contains("\\}")) {
            str = str.replace("\\}", "\\U7d");
        }
        if (str.contains("\\|")) {
            str = str.replace("\\|", "\\U7c");
        }
        if (str.contains("\\&")) {
            str = str.replace("\\&", "\\U26");
        }
        if (str.contains("\\\"")) {
            str = str.replace("\\\"", "\\U22");
        }
        return str.contains("\\U5c") ? str.replace("\\U5c", "\\\\") : str;
    }

    public static void a0() {
        if (t == null) {
            if (Aid_YuCodeX.v != Aid_YuCodeX.f) {
                t = new String[]{"f(", "for(", "w(", "t(", "ssj(", "syso(", "tw(", "fd(", "fe(", "fs(", "fr(", "fc(", "fw(", "fl(", "ft(", "fdir(", "fuz(", "fuzs(", "fo(", "fj(", "s(", "s2(", "sn(", "s+(", "s-(", "s*(", "s/(", "s%(", "ss(", "sr(", "sj(", "sl(", "siof(", "slof(", "ssg(", "slg(", "strim(", "slower(", "supper(", "stop(", "sran(", "nsz(", "sgsz(", "sssz(", "sgszl(", "hs(", "hd(", "hw(", "hws(", "ug(", "us(", "uigo(", "utw(", "endutw(", "end(", "ends(", "bfm(", "bfms(", "ula(", "uls(", "ulas(", "ulag(", "usms(", "ucall(", "time(", "fi(", "stobm(", "sutf8to(", "uycl(", "ushsp(", "bfv(", "bfvs(", "bfvss(", "ftz(", "uapp(", "uapplist(", "uapplistgo(", "uninapp(", "huf(", "nvw(", "uall(", "urvw(", "sbp(", "sdeg(", "bfs(", "tot(", "tzz(", "tsf(", "tfz(", "sxb(", "shb(", "tcc(", "usjxm(", "addv(", "gvs(", "aslist(", "sslist(", "gslist(", "gslistl(", "dslist(", "gslistsz(", "gslistis(", "gslistiof(", "gslistlof(", "nuibs(", "ngde(", "sit(", "uit(", "git(", "uqr(", "zdp(", "zpd(", "zps(", "zsp(", "zsp(", "lan(", "sjxx(", "simsi(", "simei(", "endkeyboard(", "hdfl(", "hdfla(", "hdd(", "hdda(", "hddgl(", "hddg(", "hdds(", "hdduigo(", "swh(", "ufnsui(", "se(", "usg(", "uzd(", "usxq(", "usxh(", "usx(", "ujp(", "bly(", "sqlite(", "sql(", "sqlsele(", "dha(", "dhs(", "dht(", "dhr(", "dhset(", "dhas(", "dhast(", "dh(", "dhon(", "dhb(", "hsas(", "has(", "uxf(", "tts(", "blp(", "otob(", "btoo(", "sot(", "sota(", "loadso(", "loadjar(", "cls(", "javacb(", "javanew(", "java(", "javax(", "javags(", "javass(", "res(", "clssm(", "zj(", "call(", "json(", "utb(", "tws(", "uht(", "cast(", "yul(", "rps("};
                return;
            }
            StringBuffer stringBuffer = new StringBuffer(Integer.toString(145001));
            stringBuffer.append('(');
            StringBuffer stringBuffer2 = new StringBuffer(Integer.toString(145002));
            stringBuffer2.append('(');
            StringBuffer stringBuffer3 = new StringBuffer(Integer.toString(145003));
            stringBuffer3.append('(');
            StringBuffer stringBuffer4 = new StringBuffer(Integer.toString(145004));
            stringBuffer4.append('(');
            StringBuffer stringBuffer5 = new StringBuffer(Integer.toString(145005));
            stringBuffer5.append('(');
            StringBuffer stringBuffer6 = new StringBuffer(Integer.toString(145006));
            stringBuffer6.append('(');
            StringBuffer stringBuffer7 = new StringBuffer(Integer.toString(145007));
            stringBuffer7.append('(');
            StringBuffer stringBuffer8 = new StringBuffer(Integer.toString(145008));
            stringBuffer8.append('(');
            StringBuffer stringBuffer9 = new StringBuffer(Integer.toString(145009));
            stringBuffer9.append('(');
            StringBuffer stringBuffer10 = new StringBuffer(Integer.toString(145010));
            stringBuffer10.append('(');
            StringBuffer stringBuffer11 = new StringBuffer(Integer.toString(145011));
            stringBuffer11.append('(');
            StringBuffer stringBuffer12 = new StringBuffer(Integer.toString(145012));
            stringBuffer12.append('(');
            StringBuffer stringBuffer13 = new StringBuffer(Integer.toString(145013));
            stringBuffer13.append('(');
            StringBuffer stringBuffer14 = new StringBuffer(Integer.toString(145014));
            stringBuffer14.append('(');
            StringBuffer stringBuffer15 = new StringBuffer(Integer.toString(145015));
            stringBuffer15.append('(');
            StringBuffer stringBuffer16 = new StringBuffer(Integer.toString(145016));
            stringBuffer16.append('(');
            StringBuffer stringBuffer17 = new StringBuffer(Integer.toString(145017));
            stringBuffer17.append('(');
            StringBuffer stringBuffer18 = new StringBuffer(Integer.toString(145018));
            stringBuffer18.append('(');
            StringBuffer stringBuffer19 = new StringBuffer(Integer.toString(145019));
            stringBuffer19.append('(');
            StringBuffer stringBuffer20 = new StringBuffer(Integer.toString(145020));
            stringBuffer20.append('(');
            StringBuffer stringBuffer21 = new StringBuffer(Integer.toString(145021));
            stringBuffer21.append('(');
            StringBuffer stringBuffer22 = new StringBuffer(Integer.toString(145022));
            stringBuffer22.append('(');
            StringBuffer stringBuffer23 = new StringBuffer(Integer.toString(145023));
            stringBuffer23.append('(');
            StringBuffer stringBuffer24 = new StringBuffer(Integer.toString(145024));
            stringBuffer24.append('(');
            StringBuffer stringBuffer25 = new StringBuffer(Integer.toString(145025));
            stringBuffer25.append('(');
            StringBuffer stringBuffer26 = new StringBuffer(Integer.toString(145026));
            stringBuffer26.append('(');
            StringBuffer stringBuffer27 = new StringBuffer(Integer.toString(145027));
            stringBuffer27.append('(');
            StringBuffer stringBuffer28 = new StringBuffer(Integer.toString(145028));
            stringBuffer28.append('(');
            StringBuffer stringBuffer29 = new StringBuffer(Integer.toString(145029));
            stringBuffer29.append('(');
            StringBuffer stringBuffer30 = new StringBuffer(Integer.toString(145030));
            stringBuffer30.append('(');
            StringBuffer stringBuffer31 = new StringBuffer(Integer.toString(145031));
            stringBuffer31.append('(');
            StringBuffer stringBuffer32 = new StringBuffer(Integer.toString(145032));
            stringBuffer32.append('(');
            StringBuffer stringBuffer33 = new StringBuffer(Integer.toString(145033));
            stringBuffer33.append('(');
            StringBuffer stringBuffer34 = new StringBuffer(Integer.toString(145034));
            stringBuffer34.append('(');
            StringBuffer stringBuffer35 = new StringBuffer(Integer.toString(145035));
            stringBuffer35.append('(');
            StringBuffer stringBuffer36 = new StringBuffer(Integer.toString(145036));
            stringBuffer36.append('(');
            StringBuffer stringBuffer37 = new StringBuffer(Integer.toString(145037));
            stringBuffer37.append('(');
            StringBuffer stringBuffer38 = new StringBuffer(Integer.toString(145038));
            stringBuffer38.append('(');
            StringBuffer stringBuffer39 = new StringBuffer(Integer.toString(145039));
            stringBuffer39.append('(');
            StringBuffer stringBuffer40 = new StringBuffer(Integer.toString(145040));
            stringBuffer40.append('(');
            StringBuffer stringBuffer41 = new StringBuffer(Integer.toString(145041));
            stringBuffer41.append('(');
            StringBuffer stringBuffer42 = new StringBuffer(Integer.toString(145042));
            stringBuffer42.append('(');
            StringBuffer stringBuffer43 = new StringBuffer(Integer.toString(145043));
            stringBuffer43.append('(');
            StringBuffer stringBuffer44 = new StringBuffer(Integer.toString(145044));
            stringBuffer44.append('(');
            StringBuffer stringBuffer45 = new StringBuffer(Integer.toString(145045));
            stringBuffer45.append('(');
            StringBuffer stringBuffer46 = new StringBuffer(Integer.toString(145046));
            stringBuffer46.append('(');
            StringBuffer stringBuffer47 = new StringBuffer(Integer.toString(145047));
            stringBuffer47.append('(');
            StringBuffer stringBuffer48 = new StringBuffer(Integer.toString(145048));
            stringBuffer48.append('(');
            StringBuffer stringBuffer49 = new StringBuffer(Integer.toString(145049));
            stringBuffer49.append('(');
            StringBuffer stringBuffer50 = new StringBuffer(Integer.toString(145050));
            stringBuffer50.append('(');
            StringBuffer stringBuffer51 = new StringBuffer(Integer.toString(145051));
            stringBuffer51.append('(');
            StringBuffer stringBuffer52 = new StringBuffer(Integer.toString(145052));
            stringBuffer52.append('(');
            StringBuffer stringBuffer53 = new StringBuffer(Integer.toString(145053));
            stringBuffer53.append('(');
            StringBuffer stringBuffer54 = new StringBuffer(Integer.toString(145054));
            stringBuffer54.append('(');
            StringBuffer stringBuffer55 = new StringBuffer(Integer.toString(145055));
            stringBuffer55.append('(');
            StringBuffer stringBuffer56 = new StringBuffer(Integer.toString(145056));
            stringBuffer56.append('(');
            StringBuffer stringBuffer57 = new StringBuffer(Integer.toString(145057));
            stringBuffer57.append('(');
            StringBuffer stringBuffer58 = new StringBuffer(Integer.toString(145058));
            stringBuffer58.append('(');
            StringBuffer stringBuffer59 = new StringBuffer(Integer.toString(145059));
            stringBuffer59.append('(');
            StringBuffer stringBuffer60 = new StringBuffer(Integer.toString(145060));
            stringBuffer60.append('(');
            StringBuffer stringBuffer61 = new StringBuffer(Integer.toString(145061));
            stringBuffer61.append('(');
            StringBuffer stringBuffer62 = new StringBuffer(Integer.toString(145062));
            stringBuffer62.append('(');
            StringBuffer stringBuffer63 = new StringBuffer(Integer.toString(145063));
            stringBuffer63.append('(');
            StringBuffer stringBuffer64 = new StringBuffer(Integer.toString(145064));
            stringBuffer64.append('(');
            StringBuffer stringBuffer65 = new StringBuffer(Integer.toString(145065));
            stringBuffer65.append('(');
            StringBuffer stringBuffer66 = new StringBuffer(Integer.toString(145066));
            stringBuffer66.append('(');
            StringBuffer stringBuffer67 = new StringBuffer(Integer.toString(145067));
            stringBuffer67.append('(');
            StringBuffer stringBuffer68 = new StringBuffer(Integer.toString(145068));
            stringBuffer68.append('(');
            StringBuffer stringBuffer69 = new StringBuffer(Integer.toString(145069));
            stringBuffer69.append('(');
            StringBuffer stringBuffer70 = new StringBuffer(Integer.toString(145070));
            stringBuffer70.append('(');
            StringBuffer stringBuffer71 = new StringBuffer(Integer.toString(145071));
            stringBuffer71.append('(');
            StringBuffer stringBuffer72 = new StringBuffer(Integer.toString(145072));
            stringBuffer72.append('(');
            StringBuffer stringBuffer73 = new StringBuffer(Integer.toString(145073));
            stringBuffer73.append('(');
            StringBuffer stringBuffer74 = new StringBuffer(Integer.toString(145074));
            stringBuffer74.append('(');
            StringBuffer stringBuffer75 = new StringBuffer(Integer.toString(145075));
            stringBuffer75.append('(');
            StringBuffer stringBuffer76 = new StringBuffer(Integer.toString(145076));
            stringBuffer76.append('(');
            StringBuffer stringBuffer77 = new StringBuffer(Integer.toString(145077));
            stringBuffer77.append('(');
            StringBuffer stringBuffer78 = new StringBuffer(Integer.toString(145078));
            stringBuffer78.append('(');
            StringBuffer stringBuffer79 = new StringBuffer(Integer.toString(145079));
            stringBuffer79.append('(');
            StringBuffer stringBuffer80 = new StringBuffer(Integer.toString(145080));
            stringBuffer80.append('(');
            StringBuffer stringBuffer81 = new StringBuffer(Integer.toString(145081));
            stringBuffer81.append('(');
            StringBuffer stringBuffer82 = new StringBuffer(Integer.toString(145082));
            stringBuffer82.append('(');
            StringBuffer stringBuffer83 = new StringBuffer(Integer.toString(145083));
            stringBuffer83.append('(');
            StringBuffer stringBuffer84 = new StringBuffer(Integer.toString(145084));
            stringBuffer84.append('(');
            StringBuffer stringBuffer85 = new StringBuffer(Integer.toString(145085));
            stringBuffer85.append('(');
            StringBuffer stringBuffer86 = new StringBuffer(Integer.toString(145086));
            stringBuffer86.append('(');
            StringBuffer stringBuffer87 = new StringBuffer(Integer.toString(145087));
            stringBuffer87.append('(');
            StringBuffer stringBuffer88 = new StringBuffer(Integer.toString(145088));
            stringBuffer88.append('(');
            StringBuffer stringBuffer89 = new StringBuffer(Integer.toString(145089));
            stringBuffer89.append('(');
            StringBuffer stringBuffer90 = new StringBuffer(Integer.toString(145090));
            stringBuffer90.append('(');
            StringBuffer stringBuffer91 = new StringBuffer(Integer.toString(145091));
            stringBuffer91.append('(');
            StringBuffer stringBuffer92 = new StringBuffer(Integer.toString(145092));
            stringBuffer92.append('(');
            StringBuffer stringBuffer93 = new StringBuffer(Integer.toString(145093));
            stringBuffer93.append('(');
            StringBuffer stringBuffer94 = new StringBuffer(Integer.toString(145094));
            stringBuffer94.append('(');
            StringBuffer stringBuffer95 = new StringBuffer(Integer.toString(145095));
            stringBuffer95.append('(');
            StringBuffer stringBuffer96 = new StringBuffer(Integer.toString(145096));
            stringBuffer96.append('(');
            StringBuffer stringBuffer97 = new StringBuffer(Integer.toString(145097));
            stringBuffer97.append('(');
            StringBuffer stringBuffer98 = new StringBuffer(Integer.toString(145098));
            stringBuffer98.append('(');
            StringBuffer stringBuffer99 = new StringBuffer(Integer.toString(145099));
            stringBuffer99.append('(');
            StringBuffer stringBuffer100 = new StringBuffer(Integer.toString(145100));
            stringBuffer100.append('(');
            StringBuffer stringBuffer101 = new StringBuffer(Integer.toString(145101));
            stringBuffer101.append('(');
            StringBuffer stringBuffer102 = new StringBuffer(Integer.toString(145102));
            stringBuffer102.append('(');
            StringBuffer stringBuffer103 = new StringBuffer(Integer.toString(145103));
            stringBuffer103.append('(');
            StringBuffer stringBuffer104 = new StringBuffer(Integer.toString(145104));
            stringBuffer104.append('(');
            StringBuffer stringBuffer105 = new StringBuffer(Integer.toString(145105));
            stringBuffer105.append('(');
            StringBuffer stringBuffer106 = new StringBuffer(Integer.toString(145106));
            stringBuffer106.append('(');
            StringBuffer stringBuffer107 = new StringBuffer(Integer.toString(145107));
            stringBuffer107.append('(');
            StringBuffer stringBuffer108 = new StringBuffer(Integer.toString(145108));
            stringBuffer108.append('(');
            StringBuffer stringBuffer109 = new StringBuffer(Integer.toString(145109));
            stringBuffer109.append('(');
            StringBuffer stringBuffer110 = new StringBuffer(Integer.toString(145110));
            stringBuffer110.append('(');
            StringBuffer stringBuffer111 = new StringBuffer(Integer.toString(145111));
            stringBuffer111.append('(');
            StringBuffer stringBuffer112 = new StringBuffer(Integer.toString(145112));
            stringBuffer112.append('(');
            StringBuffer stringBuffer113 = new StringBuffer(Integer.toString(145113));
            stringBuffer113.append('(');
            StringBuffer stringBuffer114 = new StringBuffer(Integer.toString(145114));
            stringBuffer114.append('(');
            StringBuffer stringBuffer115 = new StringBuffer(Integer.toString(145115));
            stringBuffer115.append('(');
            StringBuffer stringBuffer116 = new StringBuffer(Integer.toString(145116));
            stringBuffer116.append('(');
            StringBuffer stringBuffer117 = new StringBuffer(Integer.toString(145117));
            stringBuffer117.append('(');
            StringBuffer stringBuffer118 = new StringBuffer(Integer.toString(145118));
            stringBuffer118.append('(');
            StringBuffer stringBuffer119 = new StringBuffer(Integer.toString(145119));
            stringBuffer119.append('(');
            StringBuffer stringBuffer120 = new StringBuffer(Integer.toString(145120));
            stringBuffer120.append('(');
            StringBuffer stringBuffer121 = new StringBuffer(Integer.toString(145121));
            stringBuffer121.append('(');
            StringBuffer stringBuffer122 = new StringBuffer(Integer.toString(145122));
            stringBuffer122.append('(');
            StringBuffer stringBuffer123 = new StringBuffer(Integer.toString(145123));
            stringBuffer123.append('(');
            StringBuffer stringBuffer124 = new StringBuffer(Integer.toString(145124));
            stringBuffer124.append('(');
            StringBuffer stringBuffer125 = new StringBuffer(Integer.toString(145125));
            stringBuffer125.append('(');
            StringBuffer stringBuffer126 = new StringBuffer(Integer.toString(145126));
            stringBuffer126.append('(');
            StringBuffer stringBuffer127 = new StringBuffer(Integer.toString(145127));
            stringBuffer127.append('(');
            StringBuffer stringBuffer128 = new StringBuffer(Integer.toString(145128));
            stringBuffer128.append('(');
            StringBuffer stringBuffer129 = new StringBuffer(Integer.toString(145129));
            stringBuffer129.append('(');
            StringBuffer stringBuffer130 = new StringBuffer(Integer.toString(145130));
            stringBuffer130.append('(');
            StringBuffer stringBuffer131 = new StringBuffer(Integer.toString(145131));
            stringBuffer131.append('(');
            StringBuffer stringBuffer132 = new StringBuffer(Integer.toString(145132));
            stringBuffer132.append('(');
            StringBuffer stringBuffer133 = new StringBuffer(Integer.toString(145133));
            stringBuffer133.append('(');
            StringBuffer stringBuffer134 = new StringBuffer(Integer.toString(145134));
            stringBuffer134.append('(');
            StringBuffer stringBuffer135 = new StringBuffer(Integer.toString(145135));
            stringBuffer135.append('(');
            StringBuffer stringBuffer136 = new StringBuffer(Integer.toString(145136));
            stringBuffer136.append('(');
            StringBuffer stringBuffer137 = new StringBuffer(Integer.toString(145137));
            stringBuffer137.append('(');
            StringBuffer stringBuffer138 = new StringBuffer(Integer.toString(145138));
            stringBuffer138.append('(');
            StringBuffer stringBuffer139 = new StringBuffer(Integer.toString(145139));
            stringBuffer139.append('(');
            StringBuffer stringBuffer140 = new StringBuffer(Integer.toString(145140));
            stringBuffer140.append('(');
            StringBuffer stringBuffer141 = new StringBuffer(Integer.toString(145141));
            stringBuffer141.append('(');
            StringBuffer stringBuffer142 = new StringBuffer(Integer.toString(145142));
            stringBuffer142.append('(');
            StringBuffer stringBuffer143 = new StringBuffer(Integer.toString(145143));
            stringBuffer143.append('(');
            StringBuffer stringBuffer144 = new StringBuffer(Integer.toString(145144));
            stringBuffer144.append('(');
            StringBuffer stringBuffer145 = new StringBuffer(Integer.toString(145145));
            stringBuffer145.append('(');
            StringBuffer stringBuffer146 = new StringBuffer(Integer.toString(145146));
            stringBuffer146.append('(');
            StringBuffer stringBuffer147 = new StringBuffer(Integer.toString(145147));
            stringBuffer147.append('(');
            StringBuffer stringBuffer148 = new StringBuffer(Integer.toString(145148));
            stringBuffer148.append('(');
            StringBuffer stringBuffer149 = new StringBuffer(Integer.toString(145149));
            stringBuffer149.append('(');
            StringBuffer stringBuffer150 = new StringBuffer(Integer.toString(145150));
            stringBuffer150.append('(');
            StringBuffer stringBuffer151 = new StringBuffer(Integer.toString(145151));
            stringBuffer151.append('(');
            StringBuffer stringBuffer152 = new StringBuffer(Integer.toString(145152));
            stringBuffer152.append('(');
            StringBuffer stringBuffer153 = new StringBuffer(Integer.toString(145153));
            stringBuffer153.append('(');
            StringBuffer stringBuffer154 = new StringBuffer(Integer.toString(145154));
            stringBuffer154.append('(');
            StringBuffer stringBuffer155 = new StringBuffer(Integer.toString(145155));
            stringBuffer155.append('(');
            StringBuffer stringBuffer156 = new StringBuffer(Integer.toString(145156));
            stringBuffer156.append('(');
            StringBuffer stringBuffer157 = new StringBuffer(Integer.toString(145157));
            stringBuffer157.append('(');
            StringBuffer stringBuffer158 = new StringBuffer(Integer.toString(145158));
            stringBuffer158.append('(');
            StringBuffer stringBuffer159 = new StringBuffer(Integer.toString(145159));
            stringBuffer159.append('(');
            StringBuffer stringBuffer160 = new StringBuffer(Integer.toString(145160));
            stringBuffer160.append('(');
            StringBuffer stringBuffer161 = new StringBuffer(Integer.toString(145161));
            stringBuffer161.append('(');
            StringBuffer stringBuffer162 = new StringBuffer(Integer.toString(145162));
            stringBuffer162.append('(');
            StringBuffer stringBuffer163 = new StringBuffer(Integer.toString(145163));
            stringBuffer163.append('(');
            StringBuffer stringBuffer164 = new StringBuffer(Integer.toString(145164));
            stringBuffer164.append('(');
            StringBuffer stringBuffer165 = new StringBuffer(Integer.toString(145165));
            stringBuffer165.append('(');
            StringBuffer stringBuffer166 = new StringBuffer(Integer.toString(145166));
            stringBuffer166.append('(');
            StringBuffer stringBuffer167 = new StringBuffer(Integer.toString(145167));
            stringBuffer167.append('(');
            StringBuffer stringBuffer168 = new StringBuffer(Integer.toString(145168));
            stringBuffer168.append('(');
            StringBuffer stringBuffer169 = new StringBuffer(Integer.toString(145169));
            stringBuffer169.append('(');
            StringBuffer stringBuffer170 = new StringBuffer(Integer.toString(145170));
            stringBuffer170.append('(');
            StringBuffer stringBuffer171 = new StringBuffer(Integer.toString(145171));
            stringBuffer171.append('(');
            StringBuffer stringBuffer172 = new StringBuffer(Integer.toString(145172));
            stringBuffer172.append('(');
            StringBuffer stringBuffer173 = new StringBuffer(Integer.toString(145173));
            stringBuffer173.append('(');
            StringBuffer stringBuffer174 = new StringBuffer(Integer.toString(145174));
            stringBuffer174.append('(');
            StringBuffer stringBuffer175 = new StringBuffer(Integer.toString(145175));
            stringBuffer175.append('(');
            StringBuffer stringBuffer176 = new StringBuffer(Integer.toString(145176));
            stringBuffer176.append('(');
            StringBuffer stringBuffer177 = new StringBuffer(Integer.toString(145177));
            stringBuffer177.append('(');
            StringBuffer stringBuffer178 = new StringBuffer(Integer.toString(145178));
            stringBuffer178.append('(');
            StringBuffer stringBuffer179 = new StringBuffer(Integer.toString(145179));
            stringBuffer179.append('(');
            StringBuffer stringBuffer180 = new StringBuffer(Integer.toString(145180));
            stringBuffer180.append('(');
            t = new String[]{stringBuffer.toString(), stringBuffer2.toString(), stringBuffer3.toString(), stringBuffer4.toString(), stringBuffer5.toString(), stringBuffer6.toString(), stringBuffer7.toString(), stringBuffer8.toString(), stringBuffer9.toString(), stringBuffer10.toString(), stringBuffer11.toString(), stringBuffer12.toString(), stringBuffer13.toString(), stringBuffer14.toString(), stringBuffer15.toString(), stringBuffer16.toString(), stringBuffer17.toString(), stringBuffer18.toString(), stringBuffer19.toString(), stringBuffer20.toString(), stringBuffer21.toString(), stringBuffer22.toString(), stringBuffer23.toString(), stringBuffer24.toString(), stringBuffer25.toString(), stringBuffer26.toString(), stringBuffer27.toString(), stringBuffer28.toString(), stringBuffer29.toString(), stringBuffer30.toString(), stringBuffer31.toString(), stringBuffer32.toString(), stringBuffer33.toString(), stringBuffer34.toString(), stringBuffer35.toString(), stringBuffer36.toString(), stringBuffer37.toString(), stringBuffer38.toString(), stringBuffer39.toString(), stringBuffer40.toString(), stringBuffer41.toString(), stringBuffer42.toString(), stringBuffer43.toString(), stringBuffer44.toString(), stringBuffer45.toString(), stringBuffer46.toString(), stringBuffer47.toString(), stringBuffer48.toString(), stringBuffer49.toString(), stringBuffer50.toString(), stringBuffer51.toString(), stringBuffer52.toString(), stringBuffer53.toString(), stringBuffer54.toString(), stringBuffer55.toString(), stringBuffer56.toString(), stringBuffer57.toString(), stringBuffer58.toString(), stringBuffer59.toString(), stringBuffer60.toString(), stringBuffer61.toString(), stringBuffer62.toString(), stringBuffer63.toString(), stringBuffer64.toString(), stringBuffer65.toString(), stringBuffer66.toString(), stringBuffer67.toString(), stringBuffer68.toString(), stringBuffer69.toString(), stringBuffer70.toString(), stringBuffer71.toString(), stringBuffer72.toString(), stringBuffer73.toString(), stringBuffer74.toString(), stringBuffer75.toString(), stringBuffer76.toString(), stringBuffer77.toString(), stringBuffer78.toString(), stringBuffer79.toString(), stringBuffer80.toString(), stringBuffer81.toString(), stringBuffer82.toString(), stringBuffer83.toString(), stringBuffer84.toString(), stringBuffer85.toString(), stringBuffer86.toString(), stringBuffer87.toString(), stringBuffer88.toString(), stringBuffer89.toString(), stringBuffer90.toString(), stringBuffer91.toString(), stringBuffer92.toString(), stringBuffer93.toString(), stringBuffer94.toString(), stringBuffer95.toString(), stringBuffer96.toString(), stringBuffer97.toString(), stringBuffer98.toString(), stringBuffer99.toString(), stringBuffer100.toString(), stringBuffer101.toString(), stringBuffer102.toString(), stringBuffer103.toString(), stringBuffer104.toString(), stringBuffer105.toString(), stringBuffer106.toString(), stringBuffer107.toString(), stringBuffer108.toString(), stringBuffer109.toString(), stringBuffer110.toString(), stringBuffer111.toString(), stringBuffer112.toString(), stringBuffer113.toString(), stringBuffer114.toString(), stringBuffer115.toString(), stringBuffer116.toString(), stringBuffer117.toString(), stringBuffer118.toString(), stringBuffer119.toString(), stringBuffer120.toString(), stringBuffer121.toString(), stringBuffer122.toString(), stringBuffer123.toString(), stringBuffer124.toString(), stringBuffer125.toString(), stringBuffer126.toString(), stringBuffer127.toString(), stringBuffer128.toString(), stringBuffer129.toString(), stringBuffer130.toString(), stringBuffer131.toString(), stringBuffer132.toString(), stringBuffer133.toString(), stringBuffer134.toString(), stringBuffer135.toString(), stringBuffer136.toString(), stringBuffer137.toString(), stringBuffer138.toString(), stringBuffer139.toString(), stringBuffer140.toString(), stringBuffer141.toString(), stringBuffer142.toString(), stringBuffer143.toString(), stringBuffer144.toString(), stringBuffer145.toString(), stringBuffer146.toString(), stringBuffer147.toString(), stringBuffer148.toString(), stringBuffer149.toString(), stringBuffer150.toString(), stringBuffer151.toString(), stringBuffer152.toString(), stringBuffer153.toString(), stringBuffer154.toString(), stringBuffer155.toString(), stringBuffer156.toString(), stringBuffer157.toString(), stringBuffer158.toString(), stringBuffer159.toString(), stringBuffer160.toString(), stringBuffer161.toString(), stringBuffer162.toString(), stringBuffer163.toString(), stringBuffer164.toString(), stringBuffer165.toString(), stringBuffer166.toString(), stringBuffer167.toString(), stringBuffer168.toString(), stringBuffer169.toString(), stringBuffer170.toString(), stringBuffer171.toString(), stringBuffer172.toString(), stringBuffer173.toString(), stringBuffer174.toString(), stringBuffer175.toString(), stringBuffer176.toString(), stringBuffer177.toString(), stringBuffer178.toString(), stringBuffer179.toString(), stringBuffer180.toString()};
        }
    }

    private void a1(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 2) {
            T(P1[1].trim(), ((ArrayList) K0(P1[0])).toArray());
        }
    }

    private void a3(String str) {
        String trim;
        boolean k2;
        String trim2;
        int i2;
        String str2;
        boolean d2;
        Object obj = Boolean.FALSE;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 1) {
            trim2 = P1[0].trim();
            obj = new com.iapp.app.r(this.f1359d);
        } else if (length == 2) {
            if (!L0(P1[1]).equals("re")) {
                return;
            }
            Object K0 = K0(P1[0]);
            if (!(K0 instanceof com.iapp.app.r)) {
                return;
            }
            ((com.iapp.app.r) K0).h();
            trim2 = P1[0].trim();
            obj = null;
        } else {
            if (length != 3) {
                if (length == 5) {
                    String L0 = L0(P1[1]);
                    if (L0.equals("st")) {
                        Object K02 = K0(P1[0]);
                        if (!(K02 instanceof com.iapp.app.r)) {
                            return;
                        }
                        trim = P1[4].trim();
                        k2 = ((com.iapp.app.r) K02).i(L0(P1[2]), Integer.parseInt(L0(P1[3])));
                    } else {
                        if (!L0.equals("ft")) {
                            T(P1[0].trim(), new com.iapp.app.r(this.f1359d, L0(P1[1]), L0(P1[2]), Float.parseFloat(L0(P1[3])), Float.parseFloat(L0(P1[4]))));
                            return;
                        }
                        Object K03 = K0(P1[0]);
                        if (!(K03 instanceof com.iapp.app.r)) {
                            return;
                        }
                        trim = P1[4].trim();
                        k2 = ((com.iapp.app.r) K03).k(L0(P1[2]), N0(L0(P1[3])));
                    }
                    T(trim, Boolean.valueOf(k2));
                    return;
                }
                return;
            }
            Object K04 = K0(P1[0]);
            String L02 = L0(P1[1]);
            if (!L02.equals("sp")) {
                if (L02.equals("ip")) {
                    if (K04 instanceof com.iapp.app.r) {
                        trim2 = P1[2].trim();
                        d2 = ((com.iapp.app.r) K04).d();
                        obj = Boolean.valueOf(d2);
                    } else {
                        str2 = P1[2];
                    }
                } else if (L02.equals("is")) {
                    if (K04 instanceof com.iapp.app.r) {
                        trim2 = P1[2].trim();
                        obj = Boolean.TRUE;
                    } else {
                        str2 = P1[2];
                    }
                } else {
                    if (L02.equals("ph")) {
                        if (K04 instanceof com.iapp.app.r) {
                            ((com.iapp.app.r) K04).f(Float.parseFloat(L0(P1[2])));
                            return;
                        }
                        return;
                    }
                    if (L02.equals("se")) {
                        if (K04 instanceof com.iapp.app.r) {
                            ((com.iapp.app.r) K04).g(Float.parseFloat(L0(P1[2])));
                            return;
                        }
                        return;
                    } else if (L02.equals("lg")) {
                        if (K04 instanceof com.iapp.app.r) {
                            ((com.iapp.app.r) K04).e(L0(P1[2]));
                            return;
                        }
                        return;
                    } else {
                        if (!L02.equals("zt")) {
                            return;
                        }
                        if (K04 instanceof com.iapp.app.r) {
                            trim2 = P1[2].trim();
                            i2 = ((com.iapp.app.r) K04).c();
                        } else {
                            trim2 = P1[2].trim();
                            i2 = -3;
                        }
                        obj = Integer.valueOf(i2);
                    }
                }
                trim2 = str2.trim();
            } else if (K04 instanceof com.iapp.app.r) {
                trim2 = P1[2].trim();
                d2 = ((com.iapp.app.r) K04).j();
                obj = Boolean.valueOf(d2);
            } else {
                str2 = P1[2];
                trim2 = str2.trim();
            }
        }
        T(trim2, obj);
    }

    private boolean b(String str) {
        int O1 = O1(str, '=', '=');
        if (O1 != -1) {
            Object K0 = K0(str.substring(0, O1));
            Object K02 = K0(str.substring(O1 + 2));
            if (K0 == null && K02 == null) {
                return true;
            }
            if (K0 != null && K02 != null) {
                if ((K0 instanceof Double) || (K02 instanceof Double)) {
                    try {
                        if (Double.parseDouble(String.valueOf(K0)) == Double.parseDouble(String.valueOf(K02))) {
                            return true;
                        }
                    } catch (NumberFormatException unused) {
                    }
                }
                if (String.valueOf(K0).equals(String.valueOf(K02))) {
                    return true;
                }
            }
            return false;
        }
        int O12 = O1(str, '!', '=');
        if (O12 != -1) {
            Object K03 = K0(str.substring(0, O12));
            Object K04 = K0(str.substring(O12 + 2));
            if (K03 == null && K04 == null) {
                return false;
            }
            return K03 == null || K04 == null || !String.valueOf(K03).equals(String.valueOf(K04));
        }
        int O13 = O1(str, '>', '=');
        if (O13 != -1) {
            Object K05 = K0(str.substring(0, O13));
            Object K06 = K0(str.substring(O13 + 2));
            return (K05 == null || K06 == null || Double.parseDouble(K05.toString()) < Double.parseDouble(K06.toString())) ? false : true;
        }
        int O14 = O1(str, '<', '=');
        if (O14 != -1) {
            Object K07 = K0(str.substring(0, O14));
            Object K08 = K0(str.substring(O14 + 2));
            return (K07 == null || K08 == null || Double.parseDouble(K07.toString()) > Double.parseDouble(K08.toString())) ? false : true;
        }
        int N1 = N1(str, '>');
        if (N1 != -1) {
            Object K09 = K0(str.substring(0, N1));
            Object K010 = K0(str.substring(N1 + 1));
            return (K09 == null || K010 == null || Double.parseDouble(K09.toString()) <= Double.parseDouble(K010.toString())) ? false : true;
        }
        int N12 = N1(str, '<');
        if (N12 != -1) {
            String L0 = L0(str.substring(0, N12));
            String L02 = L0(str.substring(N12 + 1));
            return (L0 == null || L02 == null || Double.parseDouble(L0.toString()) >= Double.parseDouble(L02.toString())) ? false : true;
        }
        int O15 = O1(str, '?', '*');
        if (O15 != -1) {
            String L03 = L0(str.substring(0, O15));
            String L04 = L0(str.substring(O15 + 2));
            return (L03 == null || L04 == null || !L03.startsWith(L04)) ? false : true;
        }
        int O16 = O1(str, '*', '?');
        if (O16 != -1) {
            String L05 = L0(str.substring(0, O16));
            String L06 = L0(str.substring(O16 + 2));
            return (L05 == null || L06 == null || !L05.endsWith(L06)) ? false : true;
        }
        int N13 = N1(str, '?');
        if (N13 == -1) {
            Object K011 = K0(str);
            return K011 != null && K011.equals(Boolean.TRUE);
        }
        String L07 = L0(str.substring(0, N13));
        String L08 = L0(str.substring(N13 + 1));
        return (L07 == null || L08 == null || !L07.contains(L08)) ? false : true;
    }

    private String b0(String str) {
        return str.substring(str.indexOf(123, !str.startsWith("{") ? str.indexOf(10) : 0) + 1, str.lastIndexOf(125));
    }

    private void b1(String str) {
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            T(P1[1].trim(), m0(K0(P1[0]), P1[0]));
        } else if (length == 3) {
            Object K0 = K0(P1[0]);
            View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[0]);
            Object K02 = K0(P1[1]);
            T(P1[2].trim(), K02 instanceof View ? (View) K02 : l0(m02, K02, P1[1]));
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    @TargetApi(16)
    private void b2(com.iapp.app.x5.WebView webView, String str) {
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAppCachePath(this.f1359d.getApplicationContext().getDir("cache", 0).getPath());
        webView.getSettings().setAppCacheMaxSize(8388608L);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDatabasePath(this.f1359d.getApplicationContext().getDir("database", 0).getPath());
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
        new com.iapp.app.x5.b().m(webView, this.f1359d, this.f1358c);
        if (str.contains("<eventItme type=\"ondownloadstart\">")) {
            new com.iapp.app.n(webView, this.f1358c);
        } else {
            webView.setDownloadListener(new b0(this));
        }
        Q1(webView);
    }

    private void b3(Camera camera) {
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

    private boolean c(String str) {
        boolean z2 = false;
        for (String str2 : M1(str, '|', '|')) {
            boolean z3 = false;
            for (String str3 : M1(str2, '&', '&')) {
                String trim = str3.trim();
                z3 = trim.startsWith("!") ? !b(trim.substring(1)) : b(trim);
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

    private void c0(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 2) {
            Object K0 = K0(P1[1]);
            if (!(K0 instanceof Integer)) {
                ((ArrayList) K0(P1[0])).remove(K0);
                return;
            }
            int parseInt = Integer.parseInt(String.valueOf(K0));
            if (parseInt == -1) {
                ((ArrayList) K0(P1[0])).clear();
            } else {
                ((ArrayList) K0(P1[0])).remove(parseInt);
            }
        }
    }

    private void c1(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 2) {
            Object K0 = K0(P1[0]);
            View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[0]);
            if (m02 instanceof com.iapp.app.x5.WebView) {
                ((com.iapp.app.x5.WebView) m02).loadUrl("javascript:{" + K0(P1[1]) + "};");
            }
        }
    }

    private void c2(String str) {
        c.b.a.a.f.G(this.f1359d, str);
    }

    private void c3(Camera camera) {
        Camera.Parameters parameters;
        List<String> supportedFlashModes;
        if (camera == null || (parameters = camera.getParameters()) == null || (supportedFlashModes = parameters.getSupportedFlashModes()) == null || "torch".equals(parameters.getFlashMode()) || !supportedFlashModes.contains("torch")) {
            return;
        }
        parameters.setFlashMode("torch");
        camera.setParameters(parameters);
    }

    private int d(String str) {
        return (int) Double.parseDouble(str);
    }

    private void d0() {
        View peekDecorView = this.f1358c.getWindow().peekDecorView();
        if (peekDecorView != null) {
            ((InputMethodManager) this.f1359d.getSystemService("input_method")).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }

    private void d1(String str) {
        String trim;
        int b2;
        Boolean bool = Boolean.TRUE;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 3) {
            T(P1[2].trim(), Integer.valueOf(c.b.a.a.g.a(L0(P1[0]), N0(L0(P1[1])), false)));
            return;
        }
        if (length == 4) {
            trim = P1[3].trim();
            b2 = c.b.a.a.g.a(L0(P1[0]), N0(L0(P1[1])), K0(P1[2]).equals(bool));
        } else {
            if (length != 9) {
                return;
            }
            trim = P1[8].trim();
            b2 = c.b.a.a.g.b(L0(P1[0]), N0(L0(P1[1])), K0(P1[2]).equals(bool), (String) K0(P1[3]), (String) K0(P1[4]), (String) K0(P1[5]), K0(P1[6]).equals(bool), (String) K0(P1[7]));
        }
        T(trim, Integer.valueOf(b2));
    }

    private boolean d2(String str, String str2) {
        int indexOf = str.indexOf(10);
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        return str.startsWith(str2) && str.endsWith(")");
    }

    private void d3(String str) {
        Toast makeText;
        String[] P1 = P1(W2(str));
        if (P1.length == 1) {
            makeText = Toast.makeText(this.f1359d, L0(P1[0]), 1);
        } else if (P1.length != 2) {
            return;
        } else {
            makeText = Toast.makeText(this.f1359d, L0(P1[0]), Integer.parseInt(L0(P1[1])));
        }
        makeText.show();
    }

    private void e0() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addFlags(270532608);
        this.f1359d.startActivity(intent);
    }

    private void e1(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 7) {
            com.iapp.app.a.b.m(N0(L0(P1[0])), N0(L0(P1[1])), d(L0(P1[2])), d(L0(P1[3])), d(L0(P1[4])), d(L0(P1[5])), K0(P1[6]).equals(Boolean.TRUE));
        }
    }

    private void e2(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            try {
                T(P1[2].trim(), Array.get(K0(P1[0]), d(L0(P1[1]))));
            } catch (Exception e2) {
                e2.printStackTrace();
                T(P1[2].trim(), null);
            }
        }
    }

    private void e3(String str) {
        Snackbar action;
        String[] P1 = P1(X2(str));
        int length = P1.length;
        if (length == 3) {
            Object K0 = K0(P1[0]);
            View findViewById = K0 == null ? this.f1358c.findViewById(c.b.a.a.f.a) : K0 instanceof View ? (View) K0 : m0(K0, P1[0]);
            if (findViewById == null) {
                return;
            } else {
                action = Snackbar.make(findViewById, L0(P1[1]), d(L0(P1[2])));
            }
        } else {
            if (length != 4) {
                return;
            }
            Object K02 = K0(P1[0]);
            View findViewById2 = K02 == null ? this.f1358c.findViewById(c.b.a.a.f.a) : K02 instanceof View ? (View) K02 : m0(K02, P1[0]);
            if (findViewById2 == null) {
                return;
            } else {
                action = Snackbar.make(findViewById2, L0(P1[1]), d(L0(P1[2]))).setAction(L0(P1[3]), new d0(b0(str)));
            }
        }
        action.show();
    }

    private void f0() {
        AlertDialog alertDialog = n;
        if (alertDialog != null) {
            alertDialog.dismiss();
            n = null;
        }
    }

    private void f1(String str) {
        c.b.a.a.w.c g2;
        String str2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 4) {
            g2 = com.iapp.app.a.b.d(L0(P1[0]), L0(P1[1]), K0(P1[2]));
            str2 = P1[3];
        } else if (length == 5) {
            g2 = com.iapp.app.a.b.e(L0(P1[0]), L0(P1[1]), L0(P1[2]), K0(P1[3]));
            str2 = P1[4];
        } else if (length == 6) {
            g2 = com.iapp.app.a.b.f(L0(P1[0]), L0(P1[1]), L0(P1[2]), K0(P1[3]), K0(P1[4]));
            str2 = P1[5];
        } else {
            if (length != 8) {
                return;
            }
            g2 = com.iapp.app.a.b.g(L0(P1[0]), N0(L0(P1[1])), L0(P1[2]), L0(P1[3]), K0(P1[4]), K0(P1[5]).equals(Boolean.TRUE), K0(P1[6]));
            str2 = P1[7];
        }
        T(str2.trim(), g2);
    }

    private void f2(String str) {
        String[] P1 = P1(W2(str));
        try {
            T(P1[1].trim(), Integer.valueOf(Array.getLength(K0(P1[0]))));
        } catch (Exception e2) {
            e2.printStackTrace();
            T(P1[1].trim(), -1);
        }
    }

    private void f3(String str) {
        String trim;
        Bitmap bitmap;
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            Object K0 = K0(P1[0]);
            if (K0 instanceof Bitmap) {
                bitmap = c.b.a.a.i.g((Bitmap) K0, d(L0(P1[1])));
                trim = P1[2].trim();
            } else {
                trim = P1[2].trim();
                bitmap = null;
            }
            T(trim, bitmap);
        }
    }

    private void g0(String str) {
        String trim;
        boolean f2;
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            trim = P1[2].trim();
            f2 = c.b.a.a.f.f(this.f1359d, L0(P1[0]), L0(P1[1]), true);
        } else {
            if (P1.length != 4) {
                return;
            }
            trim = P1[3].trim();
            f2 = c.b.a.a.f.f(this.f1359d, L0(P1[0]), L0(P1[1]), K0(P1[2]).equals(Boolean.TRUE));
        }
        T(trim, Boolean.valueOf(f2));
    }

    private void g1(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            Object K0 = K0(P1[0]);
            if (!(K0 instanceof c.b.a.a.w.c)) {
                K0 = com.iapp.app.a.b.f1379c.get(d(String.valueOf(K0)));
            }
            T(P1[2].trim(), com.iapp.app.a.b.h((c.b.a.a.w.c) K0, L0(P1[1])));
        }
    }

    private void g2(String str) {
        String W2 = W2(str);
        CharSequence text = ((ClipboardManager) this.f1359d.getSystemService("clipboard")).getText();
        T(W2.trim(), text == null ? null : text.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r5v0, types: [c.b.a.a.t] */
    private void g3(String str) {
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        if (!(K0 instanceof ViewGroup)) {
            K0 = m0(K0, P1[0]);
        }
        ViewGroup viewGroup = (ViewGroup) K0;
        boolean equals = K0(P1[1]).equals(Boolean.TRUE);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            ?? childAt = viewGroup.getChildAt(i2);
            if (!equals) {
                childAt = Integer.valueOf(childAt.getId());
            }
            arrayList.add(childAt);
        }
        T(P1[2].trim(), arrayList);
    }

    private void h0(String str) {
        String trim;
        Boolean bool;
        String[] P1 = P1(W2(str));
        File file = new File(N0(L0(P1[0])));
        if (file.exists()) {
            trim = P1[1].trim();
            bool = Boolean.valueOf(file.delete());
        } else {
            trim = P1[1].trim();
            bool = Boolean.FALSE;
        }
        T(trim, bool);
    }

    private void h1(String str) {
        T(W2(str).trim(), com.iapp.app.a.b.f1379c);
    }

    private void h2(String str) {
        T(W2(str).trim(), c.b.a.a.p.q(this.f1359d));
    }

    private void h3(String str) {
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            T(P1[1].trim(), Boolean.valueOf(c.b.a.a.d.s(this.f1359d, L0(P1[0]))));
            return;
        }
        if (length == 3) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(L0(P1[0]), L0(P1[1])));
                this.f1359d.startActivity(intent);
                T(P1[2].trim(), Boolean.TRUE);
            } catch (Exception unused) {
                T(P1[2].trim(), Boolean.FALSE);
            }
        }
    }

    private void i0(String str) {
        String[] P1 = P1(W2(str));
        c.b.a.a.d.r(this.f1358c);
        if (P1.length == 1) {
            T(P1[0].trim(), c.b.a.a.d.m(this.f1359d));
        } else if (P1.length == 2) {
            T(P1[1].trim(), N0(L0(P1[0])));
        }
    }

    private void i1(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            Object K0 = K0(P1[0]);
            if (!(K0 instanceof c.b.a.a.w.c)) {
                K0 = com.iapp.app.a.b.f1379c.get(d(String.valueOf(K0)));
            }
            com.iapp.app.a.b.l((c.b.a.a.w.c) K0, L0(P1[1]), K0(P1[2]));
        }
    }

    private void i2(String str) {
        String W2 = W2(str);
        try {
            T(W2.trim(), ((TelephonyManager) this.f1359d.getSystemService("phone")).getSubscriberId());
        } catch (Exception e2) {
            e2.printStackTrace();
            T(W2.trim(), null);
        }
    }

    private void i3(String str) {
        StringBuilder sb;
        String[] P1 = P1(W2(str));
        ArrayList arrayList = new ArrayList();
        boolean equals = K0(P1[0]).equals(Boolean.TRUE);
        for (PackageInfo packageInfo : this.f1359d.getPackageManager().getInstalledPackages(0)) {
            String str2 = packageInfo.packageName;
            if (equals) {
                sb = new StringBuilder();
            } else if ((1 & packageInfo.applicationInfo.flags) == 0) {
                sb = new StringBuilder();
            }
            sb.append(packageInfo.packageName);
            sb.append("\n");
            sb.append(R0(str2));
            sb.append("\n");
            sb.append(packageInfo.applicationInfo.loadLabel(this.f1359d.getPackageManager()).toString());
            sb.append("\n");
            sb.append(packageInfo.versionName);
            arrayList.add(sb.toString());
        }
        T(P1[1].trim(), arrayList.toArray());
    }

    private void j0(String str) {
        String[] P1 = P1(W2(str));
        T(P1[1].trim(), Boolean.valueOf(c.b.a.a.f.g(this.f1359d, L0(P1[0]))));
    }

    private void j1(String str) {
        Intent intent;
        Context context;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 0) {
            context = this.f1359d;
            intent = new Intent().setClass(this.f1359d, DownList.class);
        } else {
            if (length != 2) {
                return;
            }
            intent = new Intent(this.f1359d, (Class<?>) DownList.class);
            Bundle bundle = new Bundle();
            bundle.putString("background", L0(P1[0]));
            bundle.putString("backgroundShadow", L0(P1[1]));
            intent.putExtras(bundle);
            context = this.f1359d;
        }
        context.startActivity(intent);
    }

    private void j2(String str) {
        String trim;
        int indexOf;
        String[] P1 = P1(W2(str));
        if (P1.length == 4) {
            trim = P1[3].trim();
            indexOf = L0(P1[0]).indexOf(L0(P1[1]), d(L0(P1[2])));
        } else {
            if (P1.length != 3) {
                return;
            }
            trim = P1[2].trim();
            indexOf = L0(P1[0]).indexOf(L0(P1[1]));
        }
        T(trim, Integer.valueOf(indexOf));
    }

    private void j3(String str) {
        String W2 = W2(str);
        ArrayList arrayList = new ArrayList();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.f1359d.getSystemService("activity")).getRunningAppProcesses()) {
            arrayList.add(runningAppProcessInfo.processName + "\n" + runningAppProcessInfo.pid + "\n" + runningAppProcessInfo.uid);
        }
        T(W2.trim(), arrayList.toArray());
    }

    private void k0(String str) {
        String[] P1 = P1(W2(str));
        T(P1[1].trim(), Boolean.valueOf(new File(N0(L0(P1[0]))).isDirectory()));
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void k1(java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.t.k1(java.lang.String):void");
    }

    private void k2(String str) {
        Intent intent;
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        String L0 = L0(P1[1]);
        if (K0 instanceof Intent) {
            intent = (Intent) K0;
        } else {
            intent = new Intent();
            T(P1[0].trim(), intent);
        }
        if (L0.equals("action")) {
            intent.setAction(L0(P1[2]));
            return;
        }
        if (L0.equals("type")) {
            intent.setType(L0(P1[2]));
            return;
        }
        if (!L0.equals("extra")) {
            if (L0.equals("flags")) {
                intent.setFlags(d(L0(P1[2])));
                return;
            }
            if (L0.equals("data")) {
                intent.setData(c.b.a.a.k.c(this.f1359d, L0(P1[2])));
                return;
            } else if (L0.equals("classname")) {
                intent.setClassName(L0(P1[2]), L0(P1[3]));
                return;
            } else {
                if (L0.equals("component")) {
                    intent.setComponent(new ComponentName(L0(P1[2]), L0(P1[3])));
                    return;
                }
                return;
            }
        }
        Object K02 = K0(P1[3]);
        if (K02 instanceof String[]) {
            intent.putExtra(L0(P1[2]), (String[]) K02);
            return;
        }
        if (K02 instanceof Boolean) {
            intent.putExtra(L0(P1[2]), K02.equals(Boolean.TRUE));
            return;
        }
        if (K02 instanceof Integer) {
            intent.putExtra(L0(P1[2]), Integer.parseInt(String.valueOf(K02)));
        } else if (K02 instanceof Long) {
            intent.putExtra(L0(P1[2]), Long.parseLong(String.valueOf(K02)));
        } else {
            intent.putExtra(L0(P1[2]), String.valueOf(K02));
        }
    }

    private void k3(String str) {
        String L0 = L0(W2(str));
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DIAL");
        intent.setData(c.b.a.a.k.c(this.f1359d, "tel:" + L0));
        this.f1359d.startActivity(intent);
    }

    private View l0(View view, Object obj, String str) {
        String trim = str.trim();
        if ((obj instanceof Integer) && (trim.startsWith("ss.") || trim.startsWith("sss."))) {
            return this.f1358c.findViewById(Integer.parseInt(obj.toString()));
        }
        int indexOf = trim.indexOf(46);
        if (indexOf != -1 && trim.indexOf(34) == -1) {
            String substring = trim.substring(0, indexOf);
            String substring2 = trim.substring(indexOf + 1);
            Object obj2 = p.get(substring);
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
        Object obj3 = p.get(substring3);
        if (obj3 == null) {
            return null;
        }
        return view.findViewById(Integer.parseInt(String.valueOf(obj3)) + Integer.parseInt(substring4));
    }

    private void l1(String str) {
        String[] P1 = P1(W2(str));
        int length = P1.length;
        Object K0 = K0(P1[0]);
        if (K0 instanceof c.b.a.a.e) {
            c.b.a.a.e eVar = (c.b.a.a.e) K0;
            if (length == 4) {
                eVar.n(L0(P1[1]), d(L0(P1[2])), K0(P1[3]));
            } else if (length != 5) {
                return;
            } else {
                eVar.o(L0(P1[1]), d(L0(P1[2])), K0(P1[3]), N0(L0(P1[4])));
            }
            eVar.t();
        }
    }

    private void l2(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 4) {
            Object K0 = K0(P1[1]);
            Object K02 = K0(P1[2]);
            T(P1[3].trim(), c.b.a.a.p.c(L0(P1[0]), K0 != null ? String.valueOf(K0) : null, K02 == null ? null : String.valueOf(K02)));
        }
    }

    private void l3(String str) {
        String b02 = b0(str);
        Message message = new Message();
        message.what = 2;
        message.obj = b02;
        this.f1360i.sendMessage(message);
    }

    private View m0(Object obj, String str) {
        String trim = str.trim();
        if ((obj instanceof Integer) && (trim.startsWith("ss.") || trim.startsWith("sss."))) {
            return this.f1358c.findViewById(Integer.parseInt(obj.toString()));
        }
        int indexOf = trim.indexOf(46);
        if (indexOf != -1 && trim.indexOf(34) == -1) {
            String substring = trim.substring(0, indexOf);
            String substring2 = trim.substring(indexOf + 1);
            Object obj2 = p.get(substring);
            if (obj2 == null) {
                return null;
            }
            return this.f1358c.findViewById(Integer.parseInt(String.valueOf(obj2)) + Integer.parseInt(substring2));
        }
        String valueOf = String.valueOf(obj);
        int indexOf2 = valueOf.indexOf(46);
        if (indexOf2 == -1) {
            return this.f1358c.findViewById(Integer.parseInt(valueOf));
        }
        String substring3 = valueOf.substring(0, indexOf2);
        String substring4 = valueOf.substring(indexOf2 + 1);
        Object obj3 = p.get(substring3);
        if (obj3 == null) {
            return null;
        }
        return this.f1358c.findViewById(Integer.parseInt(String.valueOf(obj3)) + Integer.parseInt(substring4));
    }

    private void m1(String str) {
        String trim;
        String h2;
        String trim2;
        String f2;
        String substring;
        Boolean bool = Boolean.TRUE;
        String[] P1 = P1(W2(str.replace("\\&", "\\\\\\&")));
        int length = P1.length;
        if (length == 2) {
            P1[0] = L0(P1[0]);
            if (P1[0].equals("cookie")) {
                trim2 = P1[1].trim();
                substring = P1[0];
            } else if (P1[0].startsWith("cookie:")) {
                trim2 = P1[1].trim();
                substring = P1[0].substring(7);
            } else {
                trim2 = P1[1].trim();
                f2 = c.b.a.a.g.e(P1[0], null, null);
            }
            f2 = c.b.a.a.g.d(substring);
        } else if (length == 4) {
            trim2 = P1[3].trim();
            f2 = c.b.a.a.g.e((String) K0(P1[0]), (String) K0(P1[1]), (String) K0(P1[2]));
        } else {
            if (length != 5) {
                if (length == 6) {
                    trim = P1[5].trim();
                    h2 = c.b.a.a.g.g((String) K0(P1[0]), (String) K0(P1[1]), (String) K0(P1[2]), (String) K0(P1[3]), K0(P1[4]).equals(bool));
                } else if (length == 7) {
                    trim = P1[6].trim();
                    h2 = c.b.a.a.g.h((String) K0(P1[0]), (String) K0(P1[1]), (String) K0(P1[2]), (String) K0(P1[3]), K0(P1[4]).equals(bool), (String) K0(P1[5]), 20000, 20000, null);
                } else {
                    if (length != 10) {
                        if (length == 1) {
                            P1[0] = L0(P1[0]);
                            c.b.a.a.g.d(P1[0]);
                            return;
                        }
                        return;
                    }
                    trim = P1[9].trim();
                    h2 = c.b.a.a.g.h((String) K0(P1[0]), (String) K0(P1[1]), (String) K0(P1[2]), (String) K0(P1[3]), K0(P1[4]).equals(bool), (String) K0(P1[5]), Integer.parseInt(L0(P1[6])), Integer.parseInt(L0(P1[7])), M0(P1[8]));
                }
                T(trim, h2);
                return;
            }
            trim2 = P1[4].trim();
            f2 = c.b.a.a.g.f((String) K0(P1[0]), (String) K0(P1[1]), (String) K0(P1[2]), (String) K0(P1[3]));
        }
        T(trim2, f2);
    }

    private void m2(String str) {
        String trim;
        Object valueOf;
        long parseDouble;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            trim = P1[1].trim();
            parseDouble = (long) (Double.parseDouble(L0(P1[1])) + Double.parseDouble(L0(P1[0])));
        } else {
            if (length != 3) {
                if (length == 4 && K0(P1[2]).equals(Boolean.TRUE)) {
                    trim = P1[3].trim();
                    valueOf = Double.valueOf(Double.parseDouble(L0(P1[0])) + Double.parseDouble(L0(P1[1])));
                    T(trim, valueOf);
                }
                return;
            }
            trim = P1[2].trim();
            parseDouble = (long) (Double.parseDouble(L0(P1[0])) + Double.parseDouble(L0(P1[1])));
        }
        valueOf = Long.valueOf(parseDouble);
        T(trim, valueOf);
    }

    private void m3(String str) {
        String str2;
        String trim;
        Object f2;
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[0]);
        if (P1.length == 3) {
            if (m02 == null) {
                str2 = P1[2];
                T(str2.trim(), null);
            } else {
                trim = P1[2].trim();
                f2 = new com.iapp.app.i(m02, this.f1359d).e(L0(P1[1]));
                T(trim, f2);
            }
        }
        if (P1.length == 4) {
            if (m02 == null) {
                str2 = P1[3];
                T(str2.trim(), null);
            } else {
                trim = P1[3].trim();
                f2 = new com.iapp.app.i(m02, this.f1359d).f(L0(P1[1]), K0(P1[2]));
                T(trim, f2);
            }
        }
    }

    private void n0(String str) {
        String str2;
        Boolean bool = Boolean.TRUE;
        Boolean bool2 = Boolean.FALSE;
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            String N0 = N0(L0(P1[1]));
            c.b.a.a.d.b(N0, false);
            try {
                c.b.a.a.c.c(N0(L0(P1[0])), N0, true);
                T(P1[2].trim(), bool);
                return;
            } catch (Exception unused) {
                str2 = P1[2];
            }
        } else {
            if (P1.length != 4) {
                return;
            }
            String N02 = N0(L0(P1[1]));
            c.b.a.a.d.b(N02, false);
            try {
                c.b.a.a.c.c(N0(L0(P1[0])), N02, K0(P1[2]).equals(bool));
                T(P1[3].trim(), bool);
                return;
            } catch (Exception unused2) {
                str2 = P1[3];
            }
        }
        T(str2.trim(), bool2);
    }

    @TargetApi(11)
    private void n1(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 2) {
            Object K0 = K0(P1[0]);
            View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[0]);
            if (m02 instanceof com.iapp.app.x5.WebView) {
                com.iapp.app.x5.WebView webView = (com.iapp.app.x5.WebView) m02;
                if (Build.VERSION.SDK_INT >= 11) {
                    webView.removeJavascriptInterface("iapp");
                }
                if (K0(P1[1]).equals(Boolean.TRUE)) {
                    webView.addJavascriptInterface(new iapp(this.f1359d, this.f1358c), "iapp");
                }
            }
        }
    }

    private void n2(String str) {
        String trim;
        Object valueOf;
        long parseDouble;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            trim = P1[1].trim();
            parseDouble = (long) (Double.parseDouble(L0(P1[1])) - Double.parseDouble(L0(P1[0])));
        } else {
            if (length != 3) {
                if (length == 4 && K0(P1[2]).equals(Boolean.TRUE)) {
                    trim = P1[3].trim();
                    valueOf = Double.valueOf(Double.parseDouble(L0(P1[0])) - Double.parseDouble(L0(P1[1])));
                    T(trim, valueOf);
                }
                return;
            }
            trim = P1[2].trim();
            parseDouble = (long) (Double.parseDouble(L0(P1[0])) - Double.parseDouble(L0(P1[1])));
        }
        valueOf = Long.valueOf(parseDouble);
        T(trim, valueOf);
    }

    private void n3(String str) {
        com.iapp.app.h hVar;
        int i2;
        boolean z2;
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[0]);
        boolean z3 = m02 instanceof ViewPager;
        if (z3 || (m02 instanceof VerticalViewPager)) {
            Object K02 = K0(P1[1]);
            if (K02.equals("bd")) {
                if (z3) {
                    Object K03 = K0(P1[2]);
                    View m03 = K03 instanceof View ? (View) K03 : m0(K03, P1[2]);
                    if (m03 instanceof com.iapp.app.TabLayout) {
                        ((com.iapp.app.TabLayout) m03).setupWithViewPager((ViewPager) m02, K0(P1[3]).equals(Boolean.TRUE));
                        return;
                    }
                    return;
                }
                return;
            }
            PagerAdapter pagerAdapter = null;
            if (z3) {
                pagerAdapter = ((ViewPager) m02).getAdapter();
            } else if (m02 instanceof VerticalViewPager) {
                pagerAdapter = ((VerticalViewPager) m02).getAdapter();
            }
            if (!K02.equals("add")) {
                if (K02.equals("del")) {
                    if (pagerAdapter instanceof FragmentStatePagerAdapter) {
                        ((com.iapp.app.h) pagerAdapter).d(d(L0(P1[2])));
                        return;
                    }
                    return;
                } else if (K02.equals("title")) {
                    if (pagerAdapter instanceof FragmentStatePagerAdapter) {
                        ((com.iapp.app.h) pagerAdapter).f(d(L0(P1[2])), L0(P1[3]));
                        return;
                    }
                    return;
                } else if (K02.equals("size")) {
                    if (pagerAdapter instanceof FragmentStatePagerAdapter) {
                        T(P1[2].trim(), Integer.valueOf(((com.iapp.app.h) pagerAdapter).g()));
                        return;
                    }
                    return;
                } else {
                    if (K02.equals("close") && (pagerAdapter instanceof FragmentStatePagerAdapter)) {
                        ((com.iapp.app.h) pagerAdapter).c();
                        return;
                    }
                    return;
                }
            }
            int length = P1.length;
            HashMap<Integer, Object> hashMap = new HashMap<>();
            for (int i3 = 5; i3 < length; i3++) {
                int indexOf = P1[i3].indexOf(61);
                if (indexOf != -1) {
                    try {
                        hashMap.put(Integer.valueOf(d(L0(P1[i3].substring(0, indexOf)))), K0(P1[i3].substring(indexOf + 1)));
                    } catch (NumberFormatException unused) {
                    }
                }
            }
            if (pagerAdapter == null || !((z2 = pagerAdapter instanceof FragmentStatePagerAdapter))) {
                hVar = new com.iapp.app.h(this.f1358c, m02.getId(), ((AppCompatActivity) this.f1358c).getSupportFragmentManager());
                if (z3) {
                    ViewPager viewPager = (ViewPager) m02;
                    viewPager.setAdapter(hVar);
                    viewPager.setOffscreenPageLimit(5);
                } else if (m02 instanceof VerticalViewPager) {
                    VerticalViewPager verticalViewPager = (VerticalViewPager) m02;
                    verticalViewPager.setAdapter(hVar);
                    verticalViewPager.setOffscreenPageLimit(5);
                }
            } else if (!z2) {
                return;
            } else {
                hVar = (com.iapp.app.h) pagerAdapter;
            }
            String[] b2 = c.b.a.a.q.b(L0(P1[4]), '|');
            int length2 = b2.length;
            int i4 = 0;
            while (i4 < length2) {
                String trim = b2[i4].trim();
                if (trim.endsWith(".iyu")) {
                    i2 = i4;
                    hVar.b(d(L0(P1[2])), L0(P1[3]), trim, r(trim.substring(0, trim.length() - 4)), hashMap);
                } else {
                    i2 = i4;
                }
                i4 = i2 + 1;
            }
        }
    }

    private void o0(String str) {
        int i2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            T(P1[1].trim(), c.b.a.a.f.h(this.f1359d, L0(P1[0])));
            return;
        }
        if (length == 3) {
            File file = new File(N0(L0(P1[0])));
            if (!file.exists()) {
                T(P1[2].trim(), null);
                return;
            }
            ArrayList arrayList = new ArrayList();
            boolean equals = K0(P1[1]).equals(Boolean.TRUE);
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                T(P1[2].trim(), null);
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
            T(P1[2].trim(), arrayList.toArray());
        }
    }

    private void o1(String str) {
        String str2;
        String[] P1 = P1(W2(str));
        if (P1.length == 5) {
            try {
                T(P1[4].trim(), c.b.a.a.g.m(this.f1359d, L0(P1[0]), L0(P1[1]), L0(P1[2]), L0(P1[3])));
                return;
            } catch (Exception unused) {
                str2 = P1[4];
            }
        } else {
            if (P1.length != 6) {
                return;
            }
            try {
                T(P1[5].trim(), c.b.a.a.g.n(this.f1359d, L0(P1[0]), L0(P1[1]), L0(P1[2]), L0(P1[3]), (String) K0(P1[4])));
                return;
            } catch (Exception unused2) {
                str2 = P1[5];
            }
        }
        T(str2.trim(), null);
    }

    private void o2(String str) {
        String W2 = W2(str);
        String[] C0 = C0();
        int[] D0 = D0();
        int[] E0 = E0();
        String[] F0 = F0();
        T(W2.trim(), new String[]{C0[0] + "\n" + C0[1], D0[0] + "\n" + D0[1] + "\n" + E0[0] + "\n" + E0[1], F0[0] + "\n" + F0[1] + "\n" + F0[2]});
    }

    private void o3(String str) {
        Intent intent;
        Intent intent2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 1) {
            String L0 = L0(P1[0]);
            if (L0.endsWith(".iyu")) {
                intent2 = new Intent(this.f1359d, (Class<?>) mian.class);
            } else if (L0.endsWith(".ijava")) {
                intent2 = new Intent(this.f1359d, (Class<?>) main3.class);
            } else if (L0.endsWith(".ilua")) {
                intent2 = new Intent(this.f1359d, (Class<?>) main.class);
            } else if (!L0.endsWith(".ijs")) {
                return;
            } else {
                intent2 = new Intent(this.f1359d, (Class<?>) main2.class);
            }
            intent2.putExtras(c.b.a.a.f.B(L0));
            this.f1359d.startActivity(intent2);
            return;
        }
        if (length == 2) {
            String L02 = L0(P1[0]);
            if (L02.endsWith(".iyu")) {
                intent = new Intent(this.f1359d, (Class<?>) mian.class);
            } else if (L02.endsWith(".ijava")) {
                intent = new Intent(this.f1359d, (Class<?>) main3.class);
            } else if (L02.endsWith(".ilua")) {
                intent = new Intent(this.f1359d, (Class<?>) main.class);
            } else if (!L02.endsWith(".ijs")) {
                return;
            } else {
                intent = new Intent(this.f1359d, (Class<?>) main2.class);
            }
            intent.putExtras(c.b.a.a.f.B(L02));
            intent.setFlags(d(L0(P1[1])));
            this.f1359d.startActivity(intent);
        }
    }

    private void p(View view, String str) {
        if (str.contains("<eventItme type=\"clicki\">")) {
            view.setOnClickListener(new g());
        }
        if (str.contains("<eventItme type=\"touchmonitor\">")) {
            view.setOnTouchListener(new h());
        }
        if (str.contains("<eventItme type=\"press\">")) {
            view.setOnLongClickListener(new i());
        }
        if (str.contains("<eventItme type=\"keyboard\">")) {
            view.setOnKeyListener(new j());
        }
        if (str.contains("<eventItme type=\"pressmenu\">")) {
            view.setOnCreateContextMenuListener(new l());
        }
        if (view instanceof TextView) {
            if (str.contains("<eventItme type=\"editormonitor\">")) {
                ((TextView) view).setOnEditorActionListener(new m());
            }
            if (str.contains("<eventItme type=\"ontextchanged\">") || str.contains("<eventItme type=\"beforetextchanged\">") || str.contains("<eventItme type=\"aftertextchanged\">")) {
                new com.iapp.app.s((TextView) view, this.f1358c);
            }
        }
        if (view instanceof com.iapp.app.x5.WebView) {
            b2((com.iapp.app.x5.WebView) view, str);
        }
        if (str.contains("<eventItme type=\"focuschange\">")) {
            view.setOnFocusChangeListener(new n());
        }
        if ((view instanceof AbsListView) && (str.contains("<eventItme type=\"onscrollstatechanged\">") || str.contains("<eventItme type=\"onscroll\">"))) {
            ((AbsListView) view).setOnScrollListener(new o());
        }
        if (view instanceof AdapterView) {
            if (str.contains("<eventItme type=\"clickitem\">")) {
                ((AdapterView) view).setOnItemClickListener(new p());
            }
            if (str.contains("<eventItme type=\"onitemselected\">") || str.contains("<eventItme type=\"onnothingselected\">")) {
                ((AdapterView) view).setOnItemSelectedListener(new q());
            }
        }
        if ((view instanceof ViewPager) && (str.contains("<eventItme type=\"onpageselected\">") || str.contains("<eventItme type=\"onpagescrolled\">") || str.contains("<eventItme type=\"onpagescrollstatechanged\">"))) {
            new com.iapp.app.q((ViewPager) view, this.f1358c);
        }
        if ((view instanceof DrawerLayout) && (str.contains("<eventItme type=\"ondrawerclosed\">") || str.contains("<eventItme type=\"ondraweropened\">") || str.contains("<eventItme type=\"onoptionsitemselected\">"))) {
            new com.iapp.app.o((DrawerLayout) view, this.f1358c);
        }
        if ((view instanceof SeekBar) && (str.contains("<eventItme type=\"onstarttrackingtouch\">") || str.contains("<eventItme type=\"onstoptrackingtouch\">") || str.contains("<eventItme type=\"onprogresschanged2\">"))) {
            ((SeekBar) view).setOnSeekBarChangeListener(new r());
        }
        q(view, str);
    }

    private void p0(String str) {
        String N0 = N0(L0(P1(W2(str))[0]));
        File file = new File(N0);
        if (file.exists()) {
            if (N0.toLowerCase().endsWith(".apk")) {
                c.b.a.a.d.d(this.f1359d, N0);
            } else {
                try {
                    I1(file);
                } catch (Exception unused) {
                }
            }
        }
    }

    private void p1(String str) {
        Intent intent;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 1) {
            intent = new Intent(this.f1359d, (Class<?>) Webview.class);
            Bundle bundle = new Bundle();
            bundle.putString("WebURL", L0(P1[0]));
            intent.putExtras(bundle);
        } else {
            if (length != 3) {
                return;
            }
            intent = new Intent(this.f1359d, (Class<?>) Webview.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("WebURL", L0(P1[0]));
            bundle2.putString("background", L0(P1[1]));
            bundle2.putString("backgroundShadow", L0(P1[2]));
            intent.putExtras(bundle2);
        }
        this.f1359d.startActivity(intent);
    }

    private void p2(String str) {
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 3) {
            T(P1[2].trim(), c.b.a.a.q.e(L0(P1[0]), L0(P1[1])));
        } else if (length == 4) {
            T(P1[3].trim(), K0(P1[2]).equals(Boolean.TRUE) ? L0(P1[0]).split(L0(P1[1])) : c.b.a.a.q.e(L0(P1[0]), L0(P1[1])));
        }
    }

    private void p3(String str) {
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        if (K0 instanceof Intent) {
            Intent intent = (Intent) K0;
            if (L0(P1[1]).equals("chooser")) {
                this.f1359d.startActivity(Intent.createChooser(intent, L0(P1[2])));
            } else if (L0(P1[1]).equals("result")) {
                this.f1358c.startActivityForResult(intent, d(L0(P1[2])));
            } else {
                this.f1359d.startActivity(intent);
            }
        }
    }

    private void q(View view, String str) {
        String c2;
        if ((view instanceof com.iapp.app.TabLayout) && (str.contains("<eventItme type=\"ontabselected\">") || str.contains("<eventItme type=\"ontabunselected\">") || str.contains("<eventItme type=\"ontabreselected\">"))) {
            ((com.iapp.app.TabLayout) view).addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new s(c.b.a.a.p.c(str, "<eventItme type=\"ontabselected\">", "</eventItme>"), view, c.b.a.a.p.c(str, "<eventItme type=\"ontabunselected\">", "</eventItme>"), c.b.a.a.p.c(str, "<eventItme type=\"ontabreselected\">", "</eventItme>")));
        }
        if (view instanceof RecyclerView) {
            if (str.contains("<eventItme type=\"onscrollstatechanged\">") || str.contains("<eventItme type=\"onscrolled\">")) {
                ((RecyclerView) view).addOnScrollListener(new C0048t(c.b.a.a.p.c(str, "<eventItme type=\"onscrollstatechanged\">", "</eventItme>"), c.b.a.a.p.c(str, "<eventItme type=\"onscrolled\">", "</eventItme>")));
            }
            if (str.contains("<eventItme type=\"clickitem\">") && (c2 = c.b.a.a.p.c(str, "<eventItme type=\"clickitem\">", "</eventItme>")) != null) {
                ((RecyclerView) view).addOnItemTouchListener(new w(this, new GestureDetector(this.f1359d, new u(view, c2))));
            }
        }
        if ((view instanceof VerticalViewPager) && (str.contains("<eventItme type=\"onpageselected\">") || str.contains("<eventItme type=\"onpagescrolled\">") || str.contains("<eventItme type=\"onpagescrollstatechanged\">"))) {
            ((VerticalViewPager) view).setOnPageChangeListener(new x(c.b.a.a.p.c(str, "<eventItme type=\"onpageselected\">", "</eventItme>"), view, c.b.a.a.p.c(str, "<eventItme type=\"onpagescrolled\">", "</eventItme>"), c.b.a.a.p.c(str, "<eventItme type=\"onpagescrollstatechanged\">", "</eventItme>")));
        }
        if ((view instanceof SwipeRefreshLayout) && str.contains("<eventItme type=\"onrefresh\">")) {
            ((SwipeRefreshLayout) view).setOnRefreshListener(new y(c.b.a.a.p.c(str, "<eventItme type=\"onrefresh\">", "</eventItme>"), view));
        }
        if ((view instanceof CompoundButton) && str.contains("<eventItme type=\"oncheckedchanged\">")) {
            ((CompoundButton) view).setOnCheckedChangeListener(new z(c.b.a.a.p.c(str, "<eventItme type=\"oncheckedchanged\">", "</eventItme>"), view));
        }
        if ((view instanceof AppBarLayout) && str.contains("<eventItme type=\"onoffsetchanged\">")) {
            ((AppBarLayout) view).addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) new a0(c.b.a.a.p.c(str, "<eventItme type=\"onoffsetchanged\">", "</eventItme>")));
        }
    }

    private int q0(String str) {
        String X2 = X2(str);
        int indexOf = X2.indexOf(59);
        String trim = X2.substring(0, indexOf).trim();
        String trim2 = X2.substring(indexOf + 1).trim();
        Object K0 = K0(trim);
        Object K02 = K0(trim2);
        if (K02 instanceof String[]) {
            String b02 = b0(str);
            for (String str2 : (String[]) K02) {
                T(trim, str2);
                int e2 = e(b02);
                if (e2 == 2) {
                    return 2;
                }
                if (e2 == 1) {
                    return 0;
                }
            }
        } else if (K02 instanceof Object[]) {
            String b03 = b0(str);
            for (Object obj : (Object[]) K02) {
                T(trim, obj);
                int e3 = e(b03);
                if (e3 == 2) {
                    return 2;
                }
                if (e3 == 1) {
                    return 0;
                }
            }
        } else if (K02 instanceof ArrayList) {
            String b04 = b0(str);
            Iterator it = ((ArrayList) K02).iterator();
            while (it.hasNext()) {
                T(trim, it.next());
                int e4 = e(b04);
                if (e4 == 2) {
                    return 2;
                }
                if (e4 == 1) {
                    return 0;
                }
            }
        } else {
            int d2 = d(K02.toString());
            String b05 = b0(str);
            for (int d3 = d(K0.toString()); d3 <= d2; d3++) {
                int e5 = e(b05);
                if (e5 == 2) {
                    return 2;
                }
                if (e5 == 1) {
                    return 0;
                }
            }
        }
        return 0;
    }

    private void q1(String str) {
        Intent intent;
        String L0 = L0(W2(str));
        try {
            intent = c.b.a.a.g.q(this.f1359d, L0);
        } catch (Exception unused) {
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.VIEW");
            intent2.setData(c.b.a.a.k.c(this.f1359d, L0));
            intent = intent2;
        }
        this.f1359d.startActivity(intent);
    }

    private void q2(String str) {
        String[] P1 = P1(W2(str));
        T(P1[1].trim(), Integer.valueOf(L0(P1[0]).length()));
    }

    private void q3(String str) {
        String[] P1 = P1(W2(str));
        c.b.a.a.r.a(this.f1358c, N0(L0(P1[0])), d(L0(P1[1])));
    }

    public synchronized int r(String str) {
        Object obj = p.get(str);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        int i2 = q + 10000;
        q = i2;
        p.put(str, Integer.valueOf(i2));
        return q;
    }

    private void r0(String str) {
        String[] P1 = P1(W2(str));
        int length = P1.length;
        String L0 = L0(P1[0]);
        if (length == 2) {
            T(P1[1].trim(), c.b.a.a.f.i(this.f1359d, L0));
        } else if (length == 3) {
            T(P1[2].trim(), c.b.a.a.f.j(this.f1359d, L0, L0(P1[1])));
        }
    }

    private int r1(String str) {
        StringBuilder sb;
        String str2;
        String[] w2 = w2(str, '\n');
        String str3 = "";
        int i2 = 0;
        for (int i3 = 0; i3 < w2.length; i3++) {
            if (w2[i3].equals("{")) {
                i2++;
            } else if (w2[i3].equals("}")) {
                i2--;
            } else if (i2 == 0) {
                if (w2[i3].startsWith(t[0])) {
                    sb = new StringBuilder();
                    str2 = w2[i3];
                } else {
                    if (w2[i3].startsWith("else")) {
                        String trim = w2[i3].substring(4).trim();
                        if (trim.startsWith("f(")) {
                            str3 = trim + "\n";
                        } else {
                            sb = new StringBuilder();
                            str2 = w2[i3];
                        }
                    }
                }
                sb.append(str2);
                sb.append("\n");
                str3 = sb.toString();
            }
            str3 = str3 + w2[i3] + "\n";
            if (i2 != 0) {
                continue;
            } else {
                if (!str3.startsWith(t[0])) {
                    int e2 = e(b0(str3));
                    if (e2 == 2) {
                        return 2;
                    }
                    return e2 == 1 ? 1 : 0;
                }
                if (c(X2(str3))) {
                    int e3 = e(b0(str3));
                    if (e3 == 2) {
                        return 2;
                    }
                    return e3 == 1 ? 1 : 0;
                }
            }
        }
        return 0;
    }

    private void r2(String str) {
        String trim;
        int lastIndexOf;
        String[] P1 = P1(W2(str));
        if (P1.length == 4) {
            trim = P1[3].trim();
            lastIndexOf = L0(P1[0]).lastIndexOf(L0(P1[1]), d(L0(P1[2])));
        } else {
            if (P1.length != 3) {
                return;
            }
            trim = P1[2].trim();
            lastIndexOf = L0(P1[0]).lastIndexOf(L0(P1[1]));
        }
        T(trim, Integer.valueOf(lastIndexOf));
    }

    private void r3(String str) {
        Object obj;
        ArrayList<HashMap<Integer, Object>> a2;
        Object obj2;
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        int length = P1.length;
        if (length == 1) {
            if (!(K0 instanceof k0) || (obj2 = ((k0) K0).b) == null) {
                return;
            }
            if (obj2 instanceof l0) {
                ((l0) obj2).notifyDataSetChanged();
                return;
            } else if (obj2 instanceof ArrayAdapter) {
                ((ArrayAdapter) obj2).notifyDataSetChanged();
                return;
            } else {
                if (obj2 instanceof m0) {
                    ((m0) obj2).notifyDataSetChanged();
                    return;
                }
                return;
            }
        }
        if (length == 2) {
            if (!P1[1].contains("=")) {
                Object K02 = K0(P1[1]);
                if (K02 == null || K02.equals("clear")) {
                    if (!(K0 instanceof k0) || (obj = ((k0) K0).b) == null) {
                        return;
                    }
                    if (obj instanceof l0) {
                        a2 = ((l0) obj).a();
                    } else if (!(obj instanceof m0)) {
                        return;
                    } else {
                        a2 = ((m0) obj).a();
                    }
                    a2.clear();
                    return;
                }
                if ((K0 instanceof k0) && (K02 instanceof Number)) {
                    Object obj3 = ((k0) K0).b;
                    if (obj3 == null || !(obj3 instanceof m0)) {
                        return;
                    }
                    ((m0) obj3).notifyItemChanged(((Number) K02).intValue());
                    return;
                }
            }
        } else if (length == 3 && L0(P1[1]).equals("list")) {
            T(P1[2].trim(), ((K0 == null || !(K0 instanceof k0)) ? new k0(this, null) : (k0) K0).a);
            return;
        }
        k0 k0Var = (K0 == null || !(K0 instanceof k0)) ? new k0(this, null) : (k0) K0;
        HashMap<Integer, Object> hashMap = new HashMap<>();
        for (int i2 = 1; i2 < length; i2++) {
            int indexOf = P1[i2].indexOf(61);
            if (indexOf != -1) {
                try {
                    hashMap.put(Integer.valueOf(d(L0(P1[i2].substring(0, indexOf)))), K0(P1[i2].substring(indexOf + 1)));
                } catch (NumberFormatException unused) {
                }
            }
        }
        k0Var.a.add(hashMap);
        T(P1[0].trim(), k0Var);
    }

    private void s(String str) {
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[0]);
        int length = P1.length;
        if (length != 3) {
            if (length == 2) {
                if ((m02 instanceof DrawerLayout) || (m02 instanceof ViewGroup)) {
                    for (String str2 : w2(L0(P1[1]), '|')) {
                        String trim = str2.trim();
                        if (trim.endsWith(".iyu")) {
                            J1(str2.trim(), (ViewGroup) m02, r(trim.substring(0, trim.length() - 4)), null);
                        }
                    }
                    return;
                }
                return;
            }
            return;
        }
        if ((m02 instanceof ViewPager) || (m02 instanceof VerticalViewPager)) {
            ArrayList arrayList = new ArrayList();
            String[] w2 = w2(L0(P1[1]), '|');
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            for (String str3 : w2) {
                String trim2 = str3.trim();
                if (trim2.endsWith(".iyu")) {
                    LinearLayout linearLayout = new LinearLayout(this.f1359d);
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.setOrientation(1);
                    J1(trim2, linearLayout, r(trim2.substring(0, trim2.length() - 4)), null);
                    arrayList.add(linearLayout);
                }
            }
            new com.iapp.app.u(m02, arrayList);
            T(P1[2].trim(), arrayList);
        }
    }

    private void s0(String str) {
        String[] P1 = P1(W2(str));
        T(P1[1].trim(), Long.valueOf(c.b.a.a.f.k(this.f1359d, L0(P1[0]))));
    }

    private int s1(String str) {
        StringBuilder sb;
        String str2;
        String[] w2 = w2(str, '\n');
        String str3 = "";
        int i2 = 0;
        for (int i3 = 0; i3 < w2.length; i3++) {
            if (w2[i3].equals("{")) {
                i2++;
            } else if (w2[i3].equals("}")) {
                i2--;
            } else if (i2 == 0) {
                if (w2[i3].startsWith(t[0])) {
                    sb = new StringBuilder();
                    str2 = w2[i3];
                } else {
                    if (w2[i3].startsWith("else")) {
                        String trim = w2[i3].substring(4).trim();
                        if (trim.startsWith("f(")) {
                            str3 = t[0] + trim.substring(2) + "\n";
                        } else {
                            sb = new StringBuilder();
                            str2 = w2[i3];
                        }
                    }
                }
                sb.append(str2);
                sb.append("\n");
                str3 = sb.toString();
            }
            str3 = str3 + w2[i3] + "\n";
            if (i2 != 0) {
                continue;
            } else {
                if (!str3.startsWith(t[0])) {
                    int e2 = e(b0(str3));
                    if (e2 == 2) {
                        return 2;
                    }
                    return e2 == 1 ? 1 : 0;
                }
                if (c(X2(str3))) {
                    int e3 = e(b0(str3));
                    if (e3 == 2) {
                        return 2;
                    }
                    return e3 == 1 ? 1 : 0;
                }
            }
        }
        return 0;
    }

    private void s2(String str) {
        String[] P1 = P1(W2(str));
        T(P1[1].trim(), L0(P1[0]).toLowerCase());
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x01d0, code lost:
    
        if ((r0 instanceof c.b.a.a.t.k0) != false) goto L237;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0174  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void s3(java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.t.s3(java.lang.String):void");
    }

    private void t(String str) {
        ArrayList arrayList;
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        if (K0 instanceof ArrayList) {
            arrayList = (ArrayList) K0;
        } else {
            arrayList = new ArrayList();
            T(P1[0].trim(), arrayList);
        }
        if (P1.length == 2) {
            arrayList.add(K0(P1[1]));
        } else if (P1.length == 3) {
            arrayList.add(d(L0(P1[2])), K0(P1[1]));
        }
    }

    private void t0(String str) {
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            File file = new File(N0(L0(P1[0])));
            if (!file.exists()) {
                T(P1[2].trim(), Boolean.FALSE);
                return;
            }
            String N0 = N0(L0(P1[1]));
            c.b.a.a.d.b(N0, false);
            T(P1[2].trim(), Boolean.valueOf(file.renameTo(new File(N0))));
        }
    }

    private boolean t1(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("[+-]?[0-9]+[0-9]*(\\.[0-9]+)?");
    }

    private void t2(String str) {
        String substring = str.substring(str.indexOf(40) + 1, str.lastIndexOf(41));
        int indexOf = substring.indexOf(44);
        String trim = substring.substring(0, indexOf).trim();
        String replace = trim.replace('+', ',').replace('-', ',').replace('*', ',').replace('/', ',').replace('(', ',').replace(')', ',').replace('%', ',');
        String str2 = " " + trim.replace("+", " + ").replace("-", " - ").replace("*", " * ").replace("/", " / ").replace("(", " ( ").replace(")", " ) ").replace("%", " % ") + " ";
        for (String str3 : w2(replace, ',')) {
            str2 = str2.replace(" " + str3.trim() + " ", L0(str3));
        }
        double d2 = -1.0d;
        try {
            d2 = d.a.h.f(str2).a();
        } catch (d.b.e e2) {
            e2.printStackTrace();
        }
        T(substring.substring(indexOf + 1).trim(), Double.valueOf(d2));
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00d8, code lost:
    
        if ((r0 instanceof c.b.a.a.t.k0) != false) goto L139;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00df A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void t3(java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.t.t3(java.lang.String):void");
    }

    private void u(String str) {
        String[] P1 = P1(W2(str));
        int length = P1.length;
        String L0 = L0(P1[0]);
        MediaPlayer mediaPlayer = new MediaPlayer();
        if (c.b.a.a.f.d(this.f1359d, mediaPlayer, L0) && length > 1) {
            T(P1[1].trim(), mediaPlayer);
        }
    }

    private void u0(String str) {
        String[] P1 = P1(X2(str));
        if (P1.length == 4) {
            c.b.a.a.r.b(this.f1359d, L0(P1[0]), L0(P1[1]), L0(P1[2]), K0(P1[3]), new Intent().setClass(this.f1359d, logoActivity.class).putExtra("command", b0(str)));
        }
    }

    private void u1(String str) {
        Object[] objArr;
        Object obj = null;
        String b02 = str.endsWith("}") ? b0(str) : null;
        String[] P1 = P1(X2(str));
        int length = P1.length;
        if (length > 2) {
            if (length > 3) {
                Object[] objArr2 = new Object[length - 3];
                for (int i2 = 3; i2 < length; i2++) {
                    objArr2[i2 - 3] = K0(P1[i2]);
                }
                objArr = objArr2;
            } else {
                objArr = null;
            }
            try {
                obj = c.b.a.a.b.g(this.f1359d, K0(P1[1]), L0(P1[2]), objArr, b02 == null ? null : this, b02);
            } catch (Throwable th) {
                th.printStackTrace();
                Q2(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.g), str));
            }
            T(P1[0].trim(), obj);
        }
    }

    private void u2(String str) {
        String trim;
        Object v2;
        int u2;
        Object obj = Boolean.TRUE;
        String[] P1 = P1(X2(str));
        int length = P1.length;
        if (length == 6) {
            String b02 = b0(str);
            c.b.a.a.o oVar = new c.b.a.a.o(d(L0(P1[0])), N0(L0(P1[1])), d(L0(P1[2])), d(L0(P1[3])), K0(P1[4]).equals(obj));
            oVar.F(new h0(b02));
            T(P1[5].trim(), oVar);
            return;
        }
        if (length == 5) {
            String b03 = b0(str);
            c.b.a.a.o oVar2 = new c.b.a.a.o(L0(P1[0]), d(L0(P1[1])), d(L0(P1[2])), K0(P1[3]).equals(obj));
            oVar2.F(new i0(b03));
            T(P1[4].trim(), oVar2);
            return;
        }
        if (length == 4) {
            if (K0(P1[1]).equals("list")) {
                Object K0 = K0(P1[0]);
                if (K0 instanceof c.b.a.a.o) {
                    T(P1[3].trim(), ((c.b.a.a.o) K0).A(d(L0(P1[2]))));
                    return;
                }
                return;
            }
            return;
        }
        if (length != 3) {
            if (length == 2 && K0(P1[1]).equals("re")) {
                Object K02 = K0(P1[0]);
                if (K02 instanceof c.b.a.a.o) {
                    ((c.b.a.a.o) K02).s();
                    return;
                }
                return;
            }
            return;
        }
        Object K03 = K0(P1[0]);
        if (K03 instanceof c.b.a.a.o) {
            Object K04 = K0(P1[1]);
            if (K04.equals("str")) {
                ((c.b.a.a.o) K03).b(L0(P1[2]));
                return;
            }
            if (K04.equals("file")) {
                ((c.b.a.a.o) K03).a(new File(N0(L0(P1[2]))));
                return;
            }
            if (K04.equals("bt")) {
                ((c.b.a.a.o) K03).c(D(L0(P1[2]), ' '));
                return;
            }
            if (K04.equals("bt2")) {
                ((c.b.a.a.o) K03).d((byte[]) K0(P1[2]));
                return;
            }
            if (K04.equals("ip")) {
                trim = P1[2].trim();
                v2 = Boolean.valueOf(((c.b.a.a.o) K03).B());
            } else {
                if (K04.equals("id")) {
                    trim = P1[2].trim();
                    u2 = ((c.b.a.a.o) K03).t();
                } else if (K04.equals("list")) {
                    trim = P1[2].trim();
                    v2 = ((c.b.a.a.o) K03).w();
                } else if (K04.equals("size")) {
                    trim = P1[2].trim();
                    u2 = ((c.b.a.a.o) K03).u();
                } else {
                    if (!K04.equals("server")) {
                        if (K04.equals("new")) {
                            ((c.b.a.a.o) K03).f1351c = K0(P1[2]).equals(obj);
                            return;
                        }
                        return;
                    }
                    trim = P1[2].trim();
                    v2 = ((c.b.a.a.o) K03).v();
                }
                v2 = Integer.valueOf(u2);
            }
            T(trim, v2);
        }
    }

    private void u3(String str) {
        ArrayAdapter arrayAdapter;
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[0]);
        if (m02 == null) {
            return;
        }
        int length = P1.length;
        if (m02 instanceof ListView) {
            ListView listView = (ListView) m02;
            Object K02 = K0(P1[1]);
            if (K02 instanceof k0) {
                k0 k0Var = (k0) K02;
                if (k0Var.b == null) {
                    k0Var.b = new l0(this.f1358c);
                }
                l0 l0Var = (l0) k0Var.b;
                l0Var.b(L0(P1[2]), k0Var);
                if (length > 4) {
                    l0Var.c(S0(L0(P1[3])), S0(L0(P1[4])));
                }
                listView.setAdapter((ListAdapter) l0Var);
                Object[] objArr = (Object[]) listView.getTag();
                objArr[3] = k0Var;
                listView.setTag(objArr);
                return;
            }
            return;
        }
        if (m02 instanceof GridView) {
            GridView gridView = (GridView) m02;
            Object K03 = K0(P1[1]);
            if (K03 instanceof k0) {
                k0 k0Var2 = (k0) K03;
                if (k0Var2.b == null) {
                    k0Var2.b = new l0(this.f1358c);
                }
                l0 l0Var2 = (l0) k0Var2.b;
                l0Var2.b(L0(P1[2]), k0Var2);
                if (length > 4) {
                    l0Var2.c(S0(L0(P1[3])), S0(L0(P1[4])));
                }
                gridView.setAdapter((ListAdapter) l0Var2);
                Object[] objArr2 = (Object[]) gridView.getTag();
                objArr2[3] = k0Var2;
                gridView.setTag(objArr2);
                return;
            }
            return;
        }
        if (m02 instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) m02;
            Object K04 = K0(P1[1]);
            if (K04 instanceof k0) {
                k0 k0Var3 = (k0) K04;
                if (k0Var3.b == null) {
                    k0Var3.b = new m0(this.f1358c);
                }
                m0 m0Var = (m0) k0Var3.b;
                m0Var.d(L0(P1[2]), k0Var3);
                if (length > 4) {
                    m0Var.e(S0(L0(P1[3])), S0(L0(P1[4])));
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(this.f1358c));
                recyclerView.setAdapter(m0Var);
                Object[] objArr3 = (Object[]) recyclerView.getTag();
                objArr3[3] = k0Var3;
                recyclerView.setTag(objArr3);
                return;
            }
            return;
        }
        if (!(m02 instanceof com.iapp.app.TabLayout)) {
            if (m02 instanceof Spinner) {
                Spinner spinner = (Spinner) m02;
                Object K05 = K0(P1[1]);
                if (K05 instanceof ArrayList) {
                    arrayAdapter = new ArrayAdapter(this.f1359d, R.layout.simple_spinner_item, (ArrayList) K05);
                } else if (K05 instanceof String[]) {
                    arrayAdapter = new ArrayAdapter(this.f1359d, R.layout.simple_spinner_item, (String[]) K05);
                } else if (!(K05 instanceof Object[])) {
                    return;
                } else {
                    arrayAdapter = new ArrayAdapter(this.f1359d, R.layout.simple_spinner_item, (Object[]) K05);
                }
                arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter((SpinnerAdapter) arrayAdapter);
                return;
            }
            return;
        }
        com.iapp.app.TabLayout tabLayout = (com.iapp.app.TabLayout) m02;
        Object K06 = K0(P1[1]);
        if (K06 instanceof k0) {
            c.b.a.a.h hVar = new c.b.a.a.h(this.f1358c, 2);
            String L0 = L0(P1[2]);
            int r2 = r(L0.substring(0, L0.length() - 4));
            Iterator<HashMap<Integer, Object>> it = ((k0) K06).a.iterator();
            while (it.hasNext()) {
                HashMap<Integer, Object> next = it.next();
                LinearLayout linearLayout = new LinearLayout(this.f1358c);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(S0(L0(P1[3])), S0(L0(P1[4]))));
                linearLayout.setOrientation(1);
                J1(L0, linearLayout, r2, next);
                tabLayout.addTab(tabLayout.newTab().setCustomView(linearLayout));
                Iterator<Integer> it2 = next.keySet().iterator();
                while (it2.hasNext()) {
                    int parseInt = Integer.parseInt(String.valueOf(it2.next()));
                    if (parseInt > 0) {
                        c.b.a.a.f.w(linearLayout.findViewById(parseInt + r2), next.get(Integer.valueOf(parseInt)), next, hVar);
                    }
                }
            }
        }
    }

    private void v(String str) {
        String trim;
        Object obj;
        int currentPosition;
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        if (K0 instanceof MediaPlayer) {
            MediaPlayer mediaPlayer = (MediaPlayer) K0;
            String L0 = L0(P1[1]);
            if (L0 != null) {
                try {
                    if (L0.equals("st")) {
                        mediaPlayer.start();
                    } else if (L0.equals("pe")) {
                        mediaPlayer.pause();
                    } else {
                        if (L0.equals("sp")) {
                            mediaPlayer.stop();
                            return;
                        }
                        if (!L0.equals("re")) {
                            if (L0.equals("ip")) {
                                try {
                                    T(P1[2].trim(), Boolean.valueOf(mediaPlayer.isPlaying()));
                                    return;
                                } catch (IllegalStateException unused) {
                                    trim = P1[2].trim();
                                    obj = Boolean.FALSE;
                                }
                            } else {
                                if (L0.equals("dn")) {
                                    trim = P1[2].trim();
                                    currentPosition = mediaPlayer.getDuration();
                                } else {
                                    if (!L0.equals("cn")) {
                                        if (L0.equals("seekto")) {
                                            mediaPlayer.seekTo(d(L0(P1[2])));
                                            return;
                                        }
                                        if (L0.equals("volume")) {
                                            mediaPlayer.setVolume(d(L0(P1[2])), d(L0(P1[3])));
                                            return;
                                        } else {
                                            if (L0.equals("sl")) {
                                                mediaPlayer.setLooping(K0(P1[2]).equals(Boolean.TRUE));
                                                return;
                                            }
                                            try {
                                                mediaPlayer.stop();
                                                mediaPlayer.reset();
                                            } catch (Exception unused2) {
                                            }
                                            c.b.a.a.f.d(this.f1359d, mediaPlayer, L0);
                                            return;
                                        }
                                    }
                                    trim = P1[2].trim();
                                    currentPosition = mediaPlayer.getCurrentPosition();
                                }
                                obj = Integer.valueOf(currentPosition);
                            }
                            T(trim, obj);
                            return;
                        }
                        mediaPlayer.release();
                    }
                } catch (IllegalStateException | Exception unused3) {
                }
            }
        }
    }

    private void v0(String str) {
        String lowerCase = str.substring(2, str.indexOf(40)).trim().toLowerCase();
        String[] P1 = P1(W2(str));
        int length = P1.length;
        Object obj = l.get(lowerCase + length);
        if (obj == null) {
            String trim = str.substring(2, str.indexOf(46) + 1).trim();
            Iterator<String> it = l.keySet().iterator();
            while (it.hasNext()) {
                if (it.next().toString().startsWith(trim)) {
                    return;
                }
            }
            c2(trim + "myu");
            obj = l.get(lowerCase + length);
        }
        String obj2 = obj.toString();
        int indexOf = obj2.indexOf(10);
        String[] P12 = P1(W2(obj2.substring(0, indexOf)));
        if (length != P12.length) {
            return;
        }
        t tVar = this.h;
        if (tVar == null) {
            this.h = new t(this.f1359d, this.f1358c);
        } else {
            tVar.G();
        }
        for (int i2 = 0; i2 < length; i2++) {
            this.h.T(P12[i2].trim(), K0(P1[i2]));
        }
        this.h.e(obj2.substring(indexOf).trim());
    }

    private void v1(String str) {
        Object obj;
        String b02 = b0(str);
        String[] P1 = P1(X2(str));
        Class cls = (Class) K0(P1[1]);
        try {
            obj = c.b.a.a.b.o(cls.getClassLoader(), cls, this, b02);
        } catch (Throwable th) {
            th.printStackTrace();
            Q2(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.g), str));
            obj = null;
        }
        T(P1[0].trim(), obj);
    }

    private void v2(String str) {
        String trim;
        Object h2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length != 3) {
            if (length == 2 && K0(P1[1]).equals("re")) {
                Object K0 = K0(P1[0]);
                if (K0 instanceof o.c) {
                    ((o.c) K0).g();
                    return;
                }
                return;
            }
            return;
        }
        Object K02 = K0(P1[0]);
        if (K02 instanceof o.c) {
            Object K03 = K0(P1[1]);
            if (K03.equals("str")) {
                ((o.c) K02).b(L0(P1[2]));
                return;
            }
            if (K03.equals("file")) {
                ((o.c) K02).a(new File(N0(L0(P1[2]))));
                return;
            }
            if (K03.equals("bt")) {
                ((o.c) K02).c(D(L0(P1[2]), ' '));
                return;
            }
            if (K03.equals("bt2")) {
                ((o.c) K02).d((byte[]) K0(P1[2]));
                return;
            }
            if (K03.equals("ip")) {
                trim = P1[2].trim();
                h2 = Boolean.valueOf(((o.c) K02).k());
            } else if (K03.equals("id")) {
                trim = P1[2].trim();
                h2 = Integer.valueOf(((o.c) K02).i());
            } else if (K03.equals("socket")) {
                trim = P1[2].trim();
                h2 = ((o.c) K02).j();
            } else {
                if (!K03.equals("ht")) {
                    return;
                }
                trim = P1[2].trim();
                h2 = ((o.c) K02).h();
            }
            T(trim, h2);
        }
    }

    private void v3(String str) {
        String[] P1 = P1(W2(str));
        this.f1359d.startActivity(new Intent("android.intent.action.DELETE", c.b.a.a.k.c(this.f1359d, "package:" + L0(P1[0]))));
    }

    private void w(String str) {
        Bitmap bitmap;
        int d2;
        String str2;
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        if (K0 instanceof Bitmap) {
            int length = P1.length;
            if (length == 2) {
                bitmap = (Bitmap) K0;
                d2 = 100;
                str2 = P1[1];
            } else {
                if (length != 3) {
                    return;
                }
                bitmap = (Bitmap) K0;
                d2 = d(L0(P1[1]));
                str2 = P1[2];
            }
            c.b.a.a.i.h(bitmap, d2, N0(L0(str2)));
        }
    }

    private void w1(String str) {
        String str2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        Object obj = null;
        if (length == 3) {
            try {
                obj = c.b.a.a.b.j(K0(P1[1]), L0(P1[2]));
            } catch (Throwable th) {
                th.printStackTrace();
                Q2(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.g), str));
            }
            str2 = P1[0];
        } else {
            if (length != 4) {
                return;
            }
            Object K0 = K0(P1[2]);
            try {
                obj = K0 instanceof Class ? c.b.a.a.b.i(K0(P1[1]), (Class) K0, L0(P1[3])) : c.b.a.a.b.k(K0(P1[1]), K0.toString(), L0(P1[3]));
            } catch (Throwable th2) {
                th2.printStackTrace();
                Q2(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.g), str));
            }
            str2 = P1[0];
        }
        T(str2.trim(), obj);
    }

    private String[] w2(String str, char c2) {
        return c.b.a.a.q.b(str, c2);
    }

    private void w3(String str) {
        String trim;
        c.a.a.p e2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 0) {
            new c.b.a.a.m(this.f1359d, this.f1358c).a();
            return;
        }
        if (length == 1) {
            new c.b.a.a.m(this.f1359d, this.f1358c).b(d(L0(P1[0])));
            return;
        }
        if (length == 3) {
            T(P1[2].trim(), new c.b.a.a.m(this.f1359d, this.f1358c).c(L0(P1[0]), d(L0(P1[1]))));
            return;
        }
        if (length == 2) {
            Object K0 = K0(P1[0]);
            if (K0 instanceof Bitmap) {
                trim = P1[1].trim();
                e2 = new c.b.a.a.m(this.f1359d, this.f1358c).d((Bitmap) K0);
            } else {
                trim = P1[1].trim();
                e2 = new c.b.a.a.m(this.f1359d, this.f1358c).e(N0(String.valueOf(K0)));
            }
            T(trim, e2.f());
        }
    }

    private void x(String str) {
        Intent intent;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            intent = new Intent(this.f1359d, (Class<?>) Videoview.class);
            Bundle bundle = new Bundle();
            bundle.putString("video", L0(P1[0]));
            bundle.putBoolean("sfhp", K0(P1[1]).equals(Boolean.TRUE));
            intent.putExtras(bundle);
        } else {
            if (length != 1) {
                return;
            }
            intent = new Intent(this.f1359d, (Class<?>) Videoview.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("video", L0(P1[0]));
            bundle2.putBoolean("sfhp", false);
            intent.putExtras(bundle2);
        }
        this.f1359d.startActivity(intent);
    }

    private void x1(String str) {
        Object[] objArr;
        Object obj = null;
        String b02 = str.endsWith("}") ? b0(str) : null;
        String[] P1 = P1(X2(str));
        int length = P1.length;
        if (length > 1) {
            if (length > 2) {
                objArr = new Object[length - 2];
                for (int i2 = 2; i2 < length; i2++) {
                    objArr[i2 - 2] = K0(P1[i2]);
                }
            } else {
                objArr = null;
            }
            Object K0 = K0(P1[1]);
            try {
                obj = K0 instanceof Class ? c.b.a.a.b.m(this.f1359d, (Class) K0, objArr, b02 == null ? null : this, b02) : c.b.a.a.b.n(this.f1359d, K0.toString(), objArr, b02 == null ? null : this, b02);
            } catch (Throwable th) {
                th.printStackTrace();
                Q2(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.g), str));
            }
            T(P1[0].trim(), obj);
        }
    }

    private String[] x2(String str, String str2) {
        return c.b.a.a.q.d(str, str2);
    }

    private void x3(String str) {
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        View m02 = K0 instanceof View ? (View) K0 : m0(K0, P1[0]);
        if (m02 != null) {
            ((ViewGroup) m02.getParent()).removeView(m02);
        }
    }

    private void y(String str) {
        Uri c2;
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        VideoView videoView = K0 instanceof VideoView ? (VideoView) K0 : (VideoView) m0(K0, P1[0]);
        if (P1.length == 2) {
            Object K02 = K0(P1[1]);
            if (K02 instanceof Uri) {
                c2 = (Uri) K02;
            } else {
                String valueOf = String.valueOf(K02);
                if (!c.b.a.a.p.v(valueOf.toLowerCase())) {
                    videoView.setVideoPath(N0(valueOf));
                    return;
                }
                c2 = c.b.a.a.k.c(this.f1359d, valueOf);
            }
            videoView.setVideoURI(c2);
        }
    }

    private void y0(String str) {
        String str2;
        String[] P1 = P1(W2(str));
        if (P1.length == 4) {
            String N0 = N0(L0(P1[2]));
            c.b.a.a.d.b(N0, false);
            try {
                T(P1[3].trim(), Integer.valueOf(c.b.a.a.f.l(this.f1359d, L0(P1[0]), L0(P1[1]), N0, true)));
                return;
            } catch (Exception unused) {
                str2 = P1[3];
            }
        } else {
            if (P1.length != 5) {
                return;
            }
            String N02 = N0(L0(P1[2]));
            c.b.a.a.d.b(N02, false);
            try {
                T(P1[4].trim(), Integer.valueOf(c.b.a.a.f.l(this.f1359d, L0(P1[0]), L0(P1[1]), N02, K0(P1[3]).equals(Boolean.TRUE))));
                return;
            } catch (Exception unused2) {
                str2 = P1[4];
            }
        }
        T(str2.trim(), -1);
    }

    private void y1(String str) {
        String str2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        Object obj = null;
        if (length == 4) {
            try {
                obj = c.b.a.a.b.p(K0(P1[1]), L0(P1[2]), K0(P1[3]));
            } catch (Throwable th) {
                th.printStackTrace();
                Q2(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.g), str));
            }
            str2 = P1[0];
        } else {
            if (length != 5) {
                return;
            }
            Object K0 = K0(P1[2]);
            try {
                obj = Boolean.valueOf(K0 instanceof Class ? c.b.a.a.b.q(K0(P1[1]), (Class) K0, L0(P1[3]), K0(P1[4])) : c.b.a.a.b.r(K0(P1[1]), K0.toString(), L0(P1[3]), K0(P1[4])));
            } catch (Throwable th2) {
                th2.printStackTrace();
                Q2(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.g), str));
            }
            str2 = P1[0];
        }
        T(str2.trim(), obj);
    }

    private void y2(String str) {
        String trim;
        Object h2;
        boolean j2;
        String str2;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        Object K0 = K0(P1[0]);
        SQLiteDatabase sQLiteDatabase = K0 instanceof SQLiteDatabase ? (SQLiteDatabase) K0 : null;
        if (length == 2) {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.execSQL(L0(P1[0]));
                return;
            }
            return;
        }
        if (length == 3) {
            if (sQLiteDatabase != null) {
                try {
                    T(P1[2].trim(), sQLiteDatabase.rawQuery(L0(P1[1]), null));
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    str2 = P1[2];
                }
            } else {
                str2 = P1[2];
            }
            T(str2.trim(), null);
            return;
        }
        if (length == 4) {
            Object K02 = K0(P1[2]);
            if (K02.equals("ip")) {
                trim = P1[3].trim();
                j2 = c.b.a.a.n.i(sQLiteDatabase, L0(P1[1]));
            } else {
                if (!K02.equals("del")) {
                    return;
                }
                trim = P1[3].trim();
                j2 = c.b.a.a.n.e(sQLiteDatabase, L0(P1[1]));
            }
        } else if (length == 5) {
            Object K03 = K0(P1[2]);
            if (K03.equals("add")) {
                trim = P1[4].trim();
                j2 = c.b.a.a.n.b(sQLiteDatabase, L0(P1[1]), L0(P1[3]));
            } else {
                if (!K03.equals("del")) {
                    return;
                }
                trim = P1[4].trim();
                j2 = c.b.a.a.n.d(sQLiteDatabase, L0(P1[1]), M0(P1[3]));
            }
        } else {
            if (length != 6) {
                return;
            }
            Object K04 = K0(P1[2]);
            if (K04.equals("add")) {
                trim = P1[5].trim();
                j2 = c.b.a.a.n.a(sQLiteDatabase, L0(P1[1]), L0(P1[3]), L0(P1[4]));
            } else {
                if (!K04.equals("up")) {
                    if (K04.equals("sele")) {
                        trim = P1[5].trim();
                        h2 = c.b.a.a.n.h(sQLiteDatabase, L0(P1[3]), L0(P1[1]), M0(P1[4]));
                        T(trim, h2);
                    }
                    return;
                }
                trim = P1[5].trim();
                j2 = c.b.a.a.n.j(sQLiteDatabase, L0(P1[1]), L0(P1[3]), M0(P1[4]));
            }
        }
        h2 = Boolean.valueOf(j2);
        T(trim, h2);
    }

    private void y3(String str) {
        Activity activity;
        int parseInt;
        View findViewById;
        String str2;
        String trim;
        boolean h02;
        String[] P1 = P1(W2(str));
        int i2 = 0;
        Object K0 = K0(P1[0]);
        if (K0 instanceof View) {
            findViewById = (View) K0;
        } else {
            View m02 = m0(K0, P1[0]);
            int indexOf = P1[0].indexOf(46);
            if (indexOf == -1 || P1[0].indexOf(34) != -1) {
                String valueOf = String.valueOf(K0);
                int indexOf2 = valueOf.indexOf(46);
                if (indexOf2 != -1) {
                    String substring = valueOf.substring(0, indexOf2);
                    String substring2 = valueOf.substring(indexOf2 + 1);
                    Object obj = p.get(substring);
                    if (obj != null) {
                        i2 = Integer.parseInt(String.valueOf(obj));
                        activity = this.f1358c;
                        parseInt = Integer.parseInt(substring2) + i2;
                    }
                    findViewById = m02;
                } else {
                    activity = this.f1358c;
                    parseInt = Integer.parseInt(valueOf);
                }
                findViewById = activity.findViewById(parseInt);
            } else {
                String substring3 = P1[0].substring(0, indexOf);
                String substring4 = P1[0].substring(indexOf + 1);
                Object obj2 = p.get(substring3);
                if (obj2 != null) {
                    i2 = Integer.parseInt(String.valueOf(obj2));
                    findViewById = this.f1358c.findViewById(Integer.parseInt(substring4) + i2);
                }
                findViewById = m02;
            }
        }
        if (P1.length == 3) {
            if (findViewById != null) {
                new com.iapp.app.i(findViewById, this.f1359d, i2).e0(L0(P1[1]), K0(P1[2]));
                return;
            }
            return;
        }
        if (P1.length == 4) {
            if (findViewById == null) {
                str2 = P1[3];
                T(str2.trim(), null);
            } else {
                trim = P1[3].trim();
                h02 = new com.iapp.app.i(findViewById, this.f1359d, i2).e0(L0(P1[1]), K0(P1[2]));
                T(trim, Boolean.valueOf(h02));
            }
        }
        if (P1.length == 5) {
            if (findViewById == null) {
                str2 = P1[4];
                T(str2.trim(), null);
            } else {
                trim = P1[4].trim();
                h02 = new com.iapp.app.i(findViewById, this.f1359d, i2).f0(L0(P1[1]), L0(P1[2]), L0(P1[3]));
                T(trim, Boolean.valueOf(h02));
            }
        }
        if (P1.length == 6) {
            if (findViewById == null) {
                str2 = P1[5];
                T(str2.trim(), null);
            } else {
                trim = P1[5].trim();
                h02 = new com.iapp.app.i(findViewById, this.f1359d, i2).g0(L0(P1[1]), L0(P1[2]), L0(P1[3]), L0(P1[4]));
                T(trim, Boolean.valueOf(h02));
            }
        }
        if (P1.length == 7) {
            if (findViewById == null) {
                str2 = P1[6];
                T(str2.trim(), null);
            } else {
                trim = P1[6].trim();
                h02 = new com.iapp.app.i(findViewById, this.f1359d, i2).h0(L0(P1[1]), L0(P1[2]), L0(P1[3]), L0(P1[4]), L0(P1[5]));
                T(trim, Boolean.valueOf(h02));
            }
        }
    }

    private void z(String str) {
        String trim;
        int currentPosition;
        Object obj;
        String[] P1 = P1(W2(str));
        Object K0 = K0(P1[0]);
        VideoView videoView = K0 instanceof VideoView ? (VideoView) K0 : (VideoView) m0(K0, P1[0]);
        int length = P1.length;
        if (length == 2) {
            String L0 = L0(P1[1]);
            if (L0.equals("st")) {
                videoView.requestFocus();
                videoView.start();
                return;
            } else if (L0.equals("pe")) {
                videoView.pause();
                return;
            } else {
                if (L0.equals("sp")) {
                    videoView.stopPlayback();
                    return;
                }
                return;
            }
        }
        if (length == 3) {
            String L02 = L0(P1[1]);
            if (L02.equals("seekto")) {
                videoView.seekTo(d(L0(P1[2])));
                return;
            }
            if (L02.equals("media")) {
                MediaController mediaController = new MediaController(this.f1359d);
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                trim = P1[2].trim();
                obj = mediaController;
            } else if (L02.equals("ip")) {
                try {
                    T(P1[2].trim(), Boolean.valueOf(videoView.isPlaying()));
                    return;
                } catch (Exception unused) {
                    trim = P1[2].trim();
                    obj = Boolean.FALSE;
                }
            } else {
                if (L02.equals("dn")) {
                    trim = P1[2].trim();
                    currentPosition = videoView.getDuration();
                } else {
                    if (!L02.equals("cn")) {
                        return;
                    }
                    trim = P1[2].trim();
                    currentPosition = videoView.getCurrentPosition();
                }
                obj = Integer.valueOf(currentPosition);
            }
            T(trim, obj);
        }
    }

    private void z0(String str) {
        String str2;
        Boolean bool = Boolean.TRUE;
        Boolean bool2 = Boolean.FALSE;
        String[] P1 = P1(W2(str));
        if (P1.length == 3) {
            String N0 = N0(L0(P1[1]));
            c.b.a.a.d.b(N0, false);
            try {
                c.b.a.a.f.m(this.f1359d, L0(P1[0]), N0, true);
                T(P1[2].trim(), bool);
                return;
            } catch (Exception unused) {
                str2 = P1[2];
            }
        } else {
            if (P1.length != 4) {
                return;
            }
            String N02 = N0(L0(P1[1]));
            c.b.a.a.d.b(N02, false);
            try {
                c.b.a.a.f.m(this.f1359d, L0(P1[0]), N02, K0(P1[2]).equals(bool));
                T(P1[3].trim(), bool);
                return;
            } catch (Exception unused2) {
                str2 = P1[3];
            }
        }
        T(str2.trim(), bool2);
    }

    private void z1(String str) {
        Object[] objArr;
        Object obj = null;
        String b02 = str.endsWith("}") ? b0(str) : null;
        String[] P1 = P1(X2(str));
        int length = P1.length;
        if (length > 3) {
            if (length > 4) {
                Object[] objArr2 = new Object[length - 4];
                for (int i2 = 4; i2 < length; i2++) {
                    objArr2[i2 - 4] = K0(P1[i2]);
                }
                objArr = objArr2;
            } else {
                objArr = null;
            }
            Object K0 = K0(P1[2]);
            try {
                obj = K0 instanceof Class ? c.b.a.a.b.e(this.f1359d, K0(P1[1]), (Class) K0, L0(P1[3]), objArr, b02 == null ? null : this, b02) : c.b.a.a.b.f(this.f1359d, K0(P1[1]), K0.toString(), L0(P1[3]), objArr, b02 == null ? null : this, b02);
            } catch (Throwable th) {
                th.printStackTrace();
                Q2(String.format("javaErr：\n第%s行,错误,但不终止:%s", Integer.valueOf(this.g), str));
            }
            T(P1[0].trim(), obj);
        }
    }

    private void z2(String str) {
        String trim;
        boolean x2;
        Object valueOf;
        String[] P1 = P1(W2(str));
        int length = P1.length;
        if (length == 2) {
            String L0 = L0(P1[1]);
            if (L0.equals("re")) {
                Object K0 = K0(P1[0]);
                if (K0 instanceof SQLiteDatabase) {
                    ((SQLiteDatabase) K0).close();
                    return;
                }
                return;
            }
            if (L0.contains("/") || L0.contains("\\") || L0.startsWith("@") || L0.startsWith("$") || L0.startsWith("%")) {
                trim = P1[0].trim();
                valueOf = c.b.a.a.f.z(this.f1359d, N0(L0), false);
            } else {
                trim = P1[0].trim();
                valueOf = c.b.a.a.f.z(this.f1359d, L0, true);
            }
        } else {
            if (length != 3) {
                return;
            }
            Object K02 = K0(P1[1]);
            if (K02.equals("ip")) {
                String L02 = L0(P1[0]);
                if (L02.contains("/") || L02.contains("\\") || L02.startsWith("@") || L02.startsWith("$") || L02.startsWith("%")) {
                    trim = P1[2].trim();
                    x2 = c.b.a.a.f.y(this.f1359d, N0(L02), false);
                } else {
                    trim = P1[2].trim();
                    x2 = c.b.a.a.f.y(this.f1359d, L02, true);
                }
            } else {
                if (!K02.equals("del")) {
                    return;
                }
                String L03 = L0(P1[0]);
                if (L03.contains("/") || L03.contains("\\") || L03.startsWith("@") || L03.startsWith("$") || L03.startsWith("%")) {
                    trim = P1[2].trim();
                    x2 = c.b.a.a.f.x(this.f1359d, N0(L03), false);
                } else {
                    trim = P1[2].trim();
                    x2 = c.b.a.a.f.x(this.f1359d, L03, true);
                }
            }
            valueOf = Boolean.valueOf(x2);
        }
        T(trim, valueOf);
    }

    private void z3(String str) {
        String str2;
        Object i2;
        int i3 = Build.VERSION.SDK_INT;
        String[] P1 = P1(W2(str));
        if (P1.length == 2) {
            P1[0] = P1[0].trim();
            try {
                if (K0(P1[1]).equals(Boolean.TRUE)) {
                    Object K0 = K0(P1[0]);
                    if (i3 < 23) {
                        if (K0 instanceof Camera) {
                            ((Camera) K0).release();
                        }
                        Camera open = Camera.open();
                        T(P1[0], open);
                        c3(open);
                        return;
                    }
                    str2 = P1[0];
                    i2 = c.b.a.a.r.i(this.f1359d, true);
                } else {
                    Object K02 = K0(P1[0]);
                    if (i3 < 23) {
                        if (K02 instanceof Camera) {
                            Camera camera = (Camera) K02;
                            b3(camera);
                            camera.release();
                            T(P1[0], null);
                            return;
                        }
                        return;
                    }
                    str2 = P1[0];
                    i2 = c.b.a.a.r.i(this.f1359d, false);
                }
                T(str2, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void G() {
        this.a.clear();
    }

    public void J1(String str, ViewGroup viewGroup, int i2, Object obj) {
        Context context = this.f1359d;
        c.c.a.b bVar = new c.c.a.b(context);
        bVar.a = 0;
        c.b.a.a.f.D(this, context, str, viewGroup, i2, obj, bVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x005f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void K1(java.lang.String r8, android.view.ViewGroup r9, int r10, java.lang.Object r11, c.c.a.b r12) {
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
            if (r0 == r2) goto L96
            if (r3 != r2) goto L2e
            goto L96
        L2e:
            int r0 = r0 + r10
            int r3 = r3 + r10
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
            android.view.View r0 = r12.f(r0, r1, r2)
            goto L5d
        L59:
            android.view.View r0 = r12.e(r0, r1)
        L5d:
            if (r0 != 0) goto L60
            return
        L60:
            if (r3 != r10) goto L63
            goto L69
        L63:
            android.view.View r9 = r9.findViewById(r3)
            android.view.ViewGroup r9 = (android.view.ViewGroup) r9
        L69:
            android.view.ViewGroup$LayoutParams r2 = r12.d(r9, r0)
            r0.setLayoutParams(r2)
            com.iapp.app.i r2 = new com.iapp.app.i
            android.content.Context r3 = r7.f1359d
            r2.<init>(r0, r3, r10)
            boolean r10 = r12.a(r2, r4)
            if (r10 != 0) goto L7e
            return
        L7e:
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
            r7.p(r0, r8)
            r9.addView(r0)
        L96:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.t.K1(java.lang.String, android.view.ViewGroup, int, java.lang.Object, c.c.a.b):void");
    }

    public String Q0(String str) {
        return c.b.a.a.f.C(this.f1359d, str);
    }

    public void T(String str, Object obj) {
        int indexOf = str.indexOf(46);
        if (indexOf != -1) {
            String substring = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
            if (substring.equals("ss")) {
                U(str, obj, 1);
                return;
            } else if (substring.equals("sss")) {
                U(str, obj, 2);
                return;
            }
        }
        this.a.put(str, obj);
    }

    public Object X(String str) {
        HashMap<String, Object> hashMap;
        int indexOf = str.indexOf(46);
        if (indexOf != -1) {
            String trim = str.substring(0, indexOf).trim();
            str = str.substring(indexOf + 1).trim();
            if (trim.equals("ss")) {
                hashMap = k;
            } else if (trim.equals("sss")) {
                hashMap = j;
            }
            return hashMap.get(str);
        }
        hashMap = this.a;
        return hashMap.get(str);
    }

    public void a2(ArrayList<Integer> arrayList, StringBuffer stringBuffer, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                a2(arrayList, stringBuffer, viewGroup.getChildAt(i2));
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

    /* JADX WARN: Code restructure failed: missing block: B:1411:0x148c, code lost:
    
        if (r10.length() == 0) goto L3215;
     */
    /* JADX WARN: Code restructure failed: missing block: B:658:0x156f, code lost:
    
        if (r3[r2].startsWith("else") != false) goto L3215;
     */
    /* JADX WARN: Code restructure failed: missing block: B:671:0x2072, code lost:
    
        if (Y(r2, 0) != false) goto L3857;
     */
    /* JADX WARN: Code restructure failed: missing block: B:675:0x2076, code lost:
    
        r2 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:684:0x208d, code lost:
    
        if (Y(r2, 1) != false) goto L3857;
     */
    /* JADX WARN: Code restructure failed: missing block: B:688:0x209c, code lost:
    
        if (Y(r2, 2) != false) goto L3857;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x1598, code lost:
    
        if (Y(r2, r11) != false) goto L3285;
     */
    /* JADX WARN: Removed duplicated region for block: B:77:0x2b5c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x2b36 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int e(java.lang.String r28) {
        /*
            Method dump skipped, instructions count: 11118
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.t.e(java.lang.String):int");
    }

    public void f(String str) {
        if (str == null || str.trim().length() == 0) {
            return;
        }
        e(str.replace("&lt;", "<").replace("&gt;", ">"));
    }

    public void w0(String str) {
        String lowerCase = str.substring(0, str.indexOf(40)).trim().toLowerCase();
        String[] P1 = P1(W2(str));
        int length = P1.length;
        Object obj = l.get(lowerCase + length);
        if (obj == null) {
            String trim = str.substring(0, str.indexOf(46) + 1).trim();
            Iterator<String> it = l.keySet().iterator();
            while (it.hasNext()) {
                if (it.next().toString().startsWith(trim)) {
                    return;
                }
            }
            c2(trim + "myu");
            obj = l.get(lowerCase + length);
        }
        String obj2 = obj.toString();
        int indexOf = obj2.indexOf(10);
        String[] P12 = P1(W2(obj2.substring(0, indexOf)));
        if (length != P12.length) {
            return;
        }
        t tVar = this.h;
        if (tVar == null) {
            this.h = new t(this.f1359d, this.f1358c);
        } else {
            tVar.G();
        }
        for (int i2 = 0; i2 < length; i2++) {
            this.h.T(P12[i2].trim(), K0(P1[i2]));
        }
        this.h.e(obj2.substring(indexOf).trim());
    }

    public void x0(String str, String str2, Object obj) {
        String lowerCase = str.substring(0, str.indexOf(40)).trim().toLowerCase();
        String[] P1 = P1(W2(str));
        int length = P1.length;
        Object obj2 = l.get(lowerCase + length);
        if (obj2 == null) {
            String trim = str.substring(0, str.indexOf(46) + 1).trim();
            Iterator<String> it = l.keySet().iterator();
            while (it.hasNext()) {
                if (it.next().toString().startsWith(trim)) {
                    return;
                }
            }
            c2(trim + "myu");
            obj2 = l.get(lowerCase + length);
        }
        String obj3 = obj2.toString();
        int indexOf = obj3.indexOf(10);
        String[] P12 = P1(W2(obj3.substring(0, indexOf)));
        if (length != P12.length) {
            return;
        }
        t tVar = this.h;
        if (tVar == null) {
            this.h = new t(this.f1359d, this.f1358c);
        } else {
            tVar.G();
        }
        for (int i2 = 0; i2 < length; i2++) {
            this.h.T(P12[i2].trim(), K0(P1[i2]));
        }
        this.h.T(str2, obj);
        this.h.e(obj3.substring(indexOf).trim());
    }
}
