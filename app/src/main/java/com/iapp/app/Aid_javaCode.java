package com.iapp.app;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
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
import android.text.TextWatcher;
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
import androidx.appcompat.app.ActionBar;
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
import c.d.a.a;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.iapp.app.run.main;
import com.iapp.app.run.main2;
import com.iapp.app.run.main3;
import com.iapp.app.run.mian;
import com.iapp.app.x5.WebView;
import com.iapp.interfaces.OnFileDownStatusListener;
import com.iapp.interfaces.OnHandler;
import com.iapp.interfaces.OnMessagesListener;
import com.iapp.interfaces.OnThread;
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
public class Aid_javaCode {
    public static AlertDialog alertdialog;
    private static HashMap<String, Object> f = new HashMap<>();
    private static int g = 0;
    public static HashMap<String, Object> ss_dim;
    private HashMap<String, Object> a;
    private Activity b;

    /* renamed from: c, reason: collision with root package name */
    private Context f1838c;

    /* renamed from: d, reason: collision with root package name */
    private com.iapp.app.j f1839d;
    private Handler e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements View.OnLongClickListener {
        final /* synthetic */ com.iapp.app.j a;

        a(Aid_javaCode aid_javaCode, com.iapp.app.j jVar) {
            this.a = jVar;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            int id = view.getId();
            return this.a.c("press" + id, Integer.valueOf(id), view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a0 implements View.OnTouchListener {
        final /* synthetic */ com.iapp.app.j a;

        a0(Aid_javaCode aid_javaCode, com.iapp.app.j jVar) {
            this.a = jVar;
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int id = view.getId();
            return this.a.c("touchmonitor" + id, Integer.valueOf(id), view, Integer.valueOf(motionEvent.getAction()), Float.valueOf(motionEvent.getX()), Float.valueOf(motionEvent.getY()), Float.valueOf(motionEvent.getRawX()), Float.valueOf(motionEvent.getRawY()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements View.OnKeyListener {
        final /* synthetic */ com.iapp.app.j a;

        b(Aid_javaCode aid_javaCode, com.iapp.app.j jVar) {
            this.a = jVar;
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            int id = view.getId();
            return this.a.c("keyboard" + id, Integer.valueOf(id), view, Integer.valueOf(i2), Integer.valueOf(keyEvent.getAction()), Integer.valueOf(keyEvent.getRepeatCount()), keyEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b0 {
        public ArrayList<HashMap<Integer, Object>> a;
        public Object b;

        private b0(Aid_javaCode aid_javaCode) {
            this.a = new ArrayList<>();
            this.b = null;
        }

        /* synthetic */ b0(Aid_javaCode aid_javaCode, k kVar) {
            this(aid_javaCode);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements View.OnCreateContextMenuListener {
        final /* synthetic */ com.iapp.app.j a;

        c(Aid_javaCode aid_javaCode, com.iapp.app.j jVar) {
            this.a = jVar;
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
                this.a.b("onCreateContextMenu" + id + "x0", Integer.valueOf(id), view);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c0 extends BaseAdapter {
        private Context a;
        private c.b.a.a.h b;

        /* renamed from: c, reason: collision with root package name */
        private b0 f1840c;
        private int h;
        private Iterator k;
        private a l;

        /* renamed from: d, reason: collision with root package name */
        private String f1841d = null;
        private com.iapp.app.j e = null;
        private final Object[] f = new Object[0];
        private int g = 0;

        /* renamed from: i, reason: collision with root package name */
        private int f1842i = -1;
        private int j = -2;
        private ArrayList<Integer> m = null;

        /* loaded from: classes.dex */
        private final class a {
            public ViewGroup a;
            public int[] b;

            /* renamed from: c, reason: collision with root package name */
            public View[] f1843c;

            private a(c0 c0Var) {
                this.b = null;
                this.f1843c = null;
            }

            /* synthetic */ a(c0 c0Var, k kVar) {
                this(c0Var);
            }
        }

        public c0(Context context) {
            this.a = context;
            this.b = new c.b.a.a.h(context, this, 1);
        }

        public ArrayList<HashMap<Integer, Object>> a() {
            return this.f1840c.a;
        }

        public void b(String str, b0 b0Var) {
            this.f1841d = str;
            this.f1840c = b0Var;
            this.g = Aid_javaCode.this.h(str.substring(0, str.length() - 4));
        }

        public void c(int i2, int i3) {
            this.f1842i = i2;
            this.j = i3;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            b0 b0Var = this.f1840c;
            if (b0Var == null) {
                return 0;
            }
            return b0Var.a.size();
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
            HashMap<Integer, Object> hashMap = this.f1840c.a.get(i2);
            int i3 = 0;
            if (view == null) {
                this.l = new a(this, null);
                LinearLayout linearLayout = new LinearLayout(this.a);
                linearLayout.setLayoutParams(new AbsListView.LayoutParams(this.f1842i, this.j));
                linearLayout.setOrientation(1);
                LinearLayout linearLayout2 = new LinearLayout(this.a);
                linearLayout2.setLayoutParams(new ViewGroup.LayoutParams(this.f1842i, this.j));
                linearLayout2.setOrientation(1);
                linearLayout.addView(linearLayout2);
                this.e = Aid_javaCode.this.openRestoreinterface(this.f1841d, linearLayout2, this.g, hashMap);
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
                aVar.f1843c = new View[size];
                aVar.b = new int[size];
                if (this.m == null) {
                    this.m = new ArrayList<>();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(',');
                    for (int i4 = 0; i4 < size; i4++) {
                        int intValue = ((Integer) arrayList.get(i4)).intValue();
                        this.h = intValue;
                        a aVar2 = this.l;
                        aVar2.b[i4] = intValue;
                        if (intValue > 0) {
                            aVar2.f1843c[i4] = linearLayout.findViewById(intValue + this.g);
                        }
                        stringBuffer.append(this.h + this.g);
                        stringBuffer.append(',');
                    }
                    Aid_javaCode.this.setClickable(this.m, stringBuffer, linearLayout);
                } else {
                    for (int i5 = 0; i5 < size; i5++) {
                        int intValue2 = ((Integer) arrayList.get(i5)).intValue();
                        this.h = intValue2;
                        a aVar3 = this.l;
                        aVar3.b[i5] = intValue2;
                        if (intValue2 > 0) {
                            aVar3.f1843c[i5] = linearLayout.findViewById(intValue2 + this.g);
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
            while (true) {
                a aVar5 = this.l;
                int[] iArr = aVar5.b;
                if (i3 >= iArr.length) {
                    break;
                }
                if (iArr[i3] > 0) {
                    c.b.a.a.f.w(aVar5.f1843c[i3], hashMap.get(Integer.valueOf(iArr[i3])), hashMap, this.b);
                }
                i3++;
            }
            Iterator<Integer> it = this.m.iterator();
            while (it.hasNext()) {
                View findViewById = view2.findViewById(it.next().intValue());
                Object[] objArr = (Object[]) findViewById.getTag();
                objArr[3] = hashMap;
                findViewById.setTag(objArr);
            }
            com.iapp.app.j jVar = this.e;
            if (jVar != null) {
                try {
                    jVar.getNameSpace().invokeMethod("loading", this.f, this.e);
                } catch (EvalError e) {
                    e.printStackTrace();
                }
            }
            return view2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements TextView.OnEditorActionListener {
        final /* synthetic */ com.iapp.app.j a;

        d(Aid_javaCode aid_javaCode, com.iapp.app.j jVar) {
            this.a = jVar;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            int id = textView.getId();
            com.iapp.app.j jVar = this.a;
            if (keyEvent != null) {
                return jVar.c("editormonitor" + id, Integer.valueOf(id), textView, Integer.valueOf(i2), Integer.valueOf(keyEvent.getAction()), Integer.valueOf(keyEvent.getRepeatCount()), Integer.valueOf(keyEvent.getKeyCode()), keyEvent);
            }
            return jVar.c("editormonitor" + id, Integer.valueOf(id), textView, Integer.valueOf(i2), null, null, null, keyEvent);
        }
    }

    /* loaded from: classes.dex */
    private class d0 extends RecyclerView.Adapter<a> {

        /* renamed from: c, reason: collision with root package name */
        private Context f1844c;

        /* renamed from: d, reason: collision with root package name */
        private c.b.a.a.h f1845d;
        private b0 e;
        private int j;
        private Iterator m;
        private com.iapp.app.j f = null;
        private String g = null;
        private final Object[] h = new Object[0];

        /* renamed from: i, reason: collision with root package name */
        private int f1846i = 0;
        private int k = -1;
        private int l = -2;
        private ArrayList<Integer> n = null;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a extends RecyclerView.ViewHolder {
            public ViewGroup s;
            public int[] t;
            public View[] u;

            public a(d0 d0Var, ViewGroup viewGroup) {
                super(viewGroup);
                this.t = null;
                this.u = null;
                this.s = viewGroup;
            }
        }

        public d0(Context context) {
            this.f1844c = context;
            this.f1845d = new c.b.a.a.h(context, this, 1);
        }

        public ArrayList<HashMap<Integer, Object>> a() {
            return this.e.a;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(a aVar, int i2) {
            HashMap<Integer, Object> hashMap = this.e.a.get(i2);
            int i3 = 0;
            if (aVar.t == null) {
                this.f = Aid_javaCode.this.openRestoreinterface(this.g, aVar.s, this.f1846i, hashMap);
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
                if (this.n == null) {
                    this.n = new ArrayList<>();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(',');
                    for (int i4 = 0; i4 < size; i4++) {
                        int intValue = ((Integer) arrayList.get(i4)).intValue();
                        this.j = intValue;
                        aVar.t[i4] = intValue;
                        if (intValue > 0) {
                            aVar.u[i4] = aVar.s.findViewById(intValue + this.f1846i);
                        }
                        stringBuffer.append(this.j + this.f1846i);
                        stringBuffer.append(',');
                    }
                    Aid_javaCode.this.setClickable(this.n, stringBuffer, aVar.s);
                } else {
                    for (int i5 = 0; i5 < size; i5++) {
                        int intValue2 = ((Integer) arrayList.get(i5)).intValue();
                        this.j = intValue2;
                        aVar.t[i5] = intValue2;
                        if (intValue2 > 0) {
                            aVar.u[i5] = aVar.s.findViewById(intValue2 + this.f1846i);
                        }
                    }
                }
            }
            while (true) {
                int[] iArr = aVar.t;
                if (i3 >= iArr.length) {
                    break;
                }
                if (iArr[i3] > 0) {
                    c.b.a.a.f.w(aVar.u[i3], hashMap.get(Integer.valueOf(iArr[i3])), hashMap, this.f1845d);
                }
                i3++;
            }
            Iterator<Integer> it = this.n.iterator();
            while (it.hasNext()) {
                View findViewById = aVar.s.findViewById(it.next().intValue());
                Object[] objArr = (Object[]) findViewById.getTag();
                objArr[3] = hashMap;
                findViewById.setTag(objArr);
            }
            com.iapp.app.j jVar = this.f;
            if (jVar != null) {
                try {
                    jVar.getNameSpace().invokeMethod("loading", this.h, this.f);
                } catch (EvalError e) {
                    e.printStackTrace();
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public a onCreateViewHolder(ViewGroup viewGroup, int i2) {
            LinearLayout linearLayout = new LinearLayout(this.f1844c);
            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(this.k, this.l));
            linearLayout.setOrientation(1);
            return new a(this, linearLayout);
        }

        public void d(String str, b0 b0Var) {
            this.g = str;
            this.e = b0Var;
            this.f1846i = Aid_javaCode.this.h(str.substring(0, str.length() - 4));
        }

        public void e(int i2, int i3) {
            this.k = i2;
            this.l = i3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            b0 b0Var = this.e;
            if (b0Var == null) {
                return 0;
            }
            return b0Var.a.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e implements View.OnFocusChangeListener {
        final /* synthetic */ com.iapp.app.j a;

        e(Aid_javaCode aid_javaCode, com.iapp.app.j jVar) {
            this.a = jVar;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            int id = view.getId();
            this.a.b("focuschange" + id, Integer.valueOf(id), view, Boolean.valueOf(z));
        }
    }

    /* loaded from: classes.dex */
    private class e0 {
        private OnThread a;

        /* loaded from: classes.dex */
        class a extends Thread {
            a(Aid_javaCode aid_javaCode) {
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                e0.this.a.run();
            }
        }

        public e0(Aid_javaCode aid_javaCode, OnThread onThread) {
            this.a = onThread;
            a aVar = new a(aid_javaCode);
            aVar.setName("CeShi_" + aVar.getId());
            aVar.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f implements AbsListView.OnScrollListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.j b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1847c;

        f(Aid_javaCode aid_javaCode, boolean z, com.iapp.app.j jVar, boolean z2) {
            this.a = z;
            this.b = jVar;
            this.f1847c = z2;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            if (this.f1847c) {
                int id = absListView.getId();
                this.b.b("onscroll" + id, Integer.valueOf(id), absListView, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (this.a) {
                int id = absListView.getId();
                this.b.b("onscrollstatechanged" + id, Integer.valueOf(id), absListView, Integer.valueOf(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g implements AdapterView.OnItemClickListener {
        final /* synthetic */ com.iapp.app.j a;

        g(Aid_javaCode aid_javaCode, com.iapp.app.j jVar) {
            this.a = jVar;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            int id = adapterView.getId();
            this.a.b("clickitem" + id, Integer.valueOf(id), adapterView, Integer.valueOf(i2), Long.valueOf(j), view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h implements AdapterView.OnItemSelectedListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.j b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1848c;

        h(Aid_javaCode aid_javaCode, boolean z, com.iapp.app.j jVar, boolean z2) {
            this.a = z;
            this.b = jVar;
            this.f1848c = z2;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            if (this.a) {
                int id = adapterView.getId();
                this.b.b("onitemselected" + id, Integer.valueOf(id), adapterView, view, Integer.valueOf(i2), Long.valueOf(j));
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            if (this.f1848c) {
                int id = adapterView.getId();
                this.b.b("onnothingselected" + id, Integer.valueOf(id), adapterView);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class i implements SeekBar.OnSeekBarChangeListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.j b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1849c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f1850d;

        i(Aid_javaCode aid_javaCode, boolean z, com.iapp.app.j jVar, boolean z2, boolean z3) {
            this.a = z;
            this.b = jVar;
            this.f1849c = z2;
            this.f1850d = z3;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            if (this.f1850d) {
                int id = seekBar.getId();
                this.b.b("onprogresschanged2" + id, Integer.valueOf(id), seekBar, Integer.valueOf(i2), Boolean.valueOf(z));
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (this.f1849c) {
                int id = seekBar.getId();
                this.b.b("onstarttrackingtouch" + id, Integer.valueOf(id), seekBar);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (this.a) {
                int id = seekBar.getId();
                this.b.b("onstoptrackingtouch" + id, Integer.valueOf(id), seekBar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class j implements TabLayout.OnTabSelectedListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.iapp.app.j f1851c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f1852d;
        final /* synthetic */ boolean e;

        j(Aid_javaCode aid_javaCode, boolean z, View view, com.iapp.app.j jVar, boolean z2, boolean z3) {
            this.a = z;
            this.b = view;
            this.f1851c = jVar;
            this.f1852d = z2;
            this.e = z3;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
            if (this.e) {
                int id = this.b.getId();
                this.f1851c.b("ontabreselected" + id, Integer.valueOf(id), (TabLayout) this.b, Integer.valueOf(tab.getPosition()), String.valueOf(tab.getText()), tab);
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            if (this.a) {
                int id = this.b.getId();
                this.f1851c.b("ontabselected" + id, Integer.valueOf(id), (TabLayout) this.b, Integer.valueOf(tab.getPosition()), String.valueOf(tab.getText()), tab);
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            if (this.f1852d) {
                int id = this.b.getId();
                this.f1851c.b("ontabunselected" + id, Integer.valueOf(id), (TabLayout) this.b, Integer.valueOf(tab.getPosition()), String.valueOf(tab.getText()), tab);
            }
        }
    }

    /* loaded from: classes.dex */
    class k extends Handler {
        k(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                Toast.makeText(Aid_javaCode.this.f1838c, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                ((OnHandler) message.obj).on();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class l extends RecyclerView.OnScrollListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.j b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1853c;

        l(Aid_javaCode aid_javaCode, boolean z, com.iapp.app.j jVar, boolean z2) {
            this.a = z;
            this.b = jVar;
            this.f1853c = z2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (this.a) {
                int id = recyclerView.getId();
                this.b.b("onscrollstatechanged" + id, Integer.valueOf(id), recyclerView, Integer.valueOf(i2));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            if (this.f1853c) {
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
                this.b.b("onscrolled" + id, Integer.valueOf(id), recyclerView, Integer.valueOf(i4), Integer.valueOf(childCount), Integer.valueOf(itemCount), Integer.valueOf(i2), Integer.valueOf(i3), Boolean.valueOf(z));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class m extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.iapp.app.j f1854c;

        m(Aid_javaCode aid_javaCode, boolean z, View view, com.iapp.app.j jVar) {
            this.a = z;
            this.b = view;
            this.f1854c = jVar;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            RecyclerView recyclerView;
            View findChildViewUnder;
            if (!this.a || (findChildViewUnder = (recyclerView = (RecyclerView) this.b).findChildViewUnder(motionEvent.getX(), motionEvent.getY())) == null) {
                return;
            }
            int id = recyclerView.getId();
            this.f1854c.b("clickitem" + id, Integer.valueOf(id), recyclerView, Integer.valueOf(recyclerView.getChildLayoutPosition(findChildViewUnder)), findChildViewUnder);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            RecyclerView recyclerView;
            View findChildViewUnder;
            if (!this.a || (findChildViewUnder = (recyclerView = (RecyclerView) this.b).findChildViewUnder(motionEvent.getX(), motionEvent.getY())) == null) {
                return false;
            }
            int id = recyclerView.getId();
            this.f1854c.b("clickitem" + id, Integer.valueOf(id), recyclerView, Integer.valueOf(recyclerView.getChildLayoutPosition(findChildViewUnder)), findChildViewUnder);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class n implements RecyclerView.OnItemTouchListener {
        final /* synthetic */ GestureDetector a;

        n(Aid_javaCode aid_javaCode, GestureDetector gestureDetector) {
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
    public class o implements ViewPager.OnPageChangeListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.iapp.app.j f1855c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f1856d;
        final /* synthetic */ boolean e;

        o(Aid_javaCode aid_javaCode, boolean z, View view, com.iapp.app.j jVar, boolean z2, boolean z3) {
            this.a = z;
            this.b = view;
            this.f1855c = jVar;
            this.f1856d = z2;
            this.e = z3;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (this.e) {
                int id = this.b.getId();
                this.f1855c.b("onpagescrollstatechanged" + id, Integer.valueOf(id), (VerticalViewPager) this.b, Integer.valueOf(i2));
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            if (this.f1856d) {
                int id = this.b.getId();
                this.f1855c.b("onpagescrolled" + id, Integer.valueOf(id), (VerticalViewPager) this.b, Integer.valueOf(i2), Float.valueOf(f), Integer.valueOf(i3));
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (this.a) {
                int id = this.b.getId();
                this.f1855c.b("onpageselected" + id, Integer.valueOf(id), (VerticalViewPager) this.b, Integer.valueOf(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class p implements SwipeRefreshLayout.OnRefreshListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.iapp.app.j f1857c;

        p(Aid_javaCode aid_javaCode, boolean z, View view, com.iapp.app.j jVar) {
            this.a = z;
            this.b = view;
            this.f1857c = jVar;
        }

        @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
        public void onRefresh() {
            if (this.a) {
                int id = this.b.getId();
                this.f1857c.b("onrefresh" + id, Integer.valueOf(id), (SwipeRefreshLayout) this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class q implements CompoundButton.OnCheckedChangeListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.iapp.app.j f1858c;

        q(Aid_javaCode aid_javaCode, boolean z, View view, com.iapp.app.j jVar) {
            this.a = z;
            this.b = view;
            this.f1858c = jVar;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (this.a) {
                int id = this.b.getId();
                this.f1858c.b("oncheckedchanged" + id, Integer.valueOf(id), compoundButton, Boolean.valueOf(z));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class r implements AppBarLayout.OnOffsetChangedListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.j b;

        r(Aid_javaCode aid_javaCode, boolean z, com.iapp.app.j jVar) {
            this.a = z;
            this.b = jVar;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        public void onOffsetChanged(AppBarLayout appBarLayout, int i2) {
            if (this.a) {
                int id = appBarLayout.getId();
                this.b.b("onoffsetchanged" + id, Integer.valueOf(id), appBarLayout, Integer.valueOf(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class s implements com.iapp.app.x5.a {
        s(Aid_javaCode aid_javaCode) {
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
    class t extends Handler {
        t(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                Toast.makeText(Aid_javaCode.this.f1838c, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                ((OnHandler) message.obj).on();
            }
        }
    }

    /* loaded from: classes.dex */
    class u extends Handler {
        u(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                Toast.makeText(Aid_javaCode.this.f1838c, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                ((OnHandler) message.obj).on();
            }
        }
    }

    /* loaded from: classes.dex */
    class v extends Handler {
        v(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                Toast.makeText(Aid_javaCode.this.f1838c, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                ((OnHandler) message.obj).on();
            }
        }
    }

    /* loaded from: classes.dex */
    class w extends ActionBarDrawerToggle {
        final /* synthetic */ boolean l;
        final /* synthetic */ DrawerLayout m;
        final /* synthetic */ boolean n;
        final /* synthetic */ boolean o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        w(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i2, int i3, boolean z, DrawerLayout drawerLayout2, boolean z2, boolean z3) {
            super(activity, drawerLayout, toolbar, i2, i3);
            this.l = z;
            this.m = drawerLayout2;
            this.n = z2;
            this.o = z3;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(View view) {
            if (this.l) {
                int id = this.m.getId();
                Aid_javaCode.this.f1839d.b("ondrawerclosed" + id, Integer.valueOf(id), this.m, view);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(View view) {
            if (this.n) {
                int id = this.m.getId();
                Aid_javaCode.this.f1839d.b("ondraweropened" + id, Integer.valueOf(id), this.m, view);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            if (!this.o) {
                return false;
            }
            int id = this.m.getId();
            return Aid_javaCode.this.f1839d.c("onoptionsitemselected" + id, Integer.valueOf(id), this.m, menuItem);
        }
    }

    /* loaded from: classes.dex */
    class x extends WebChromeClient {
        x() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            c.b.a.a.t.P2(Aid_javaCode.this.f1838c, "JsErr：\nLine" + consoleMessage.lineNumber() + ": " + consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* loaded from: classes.dex */
    class y extends WebChromeClient {
        y() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            c.b.a.a.t.P2(Aid_javaCode.this.f1838c, "JsErr：\nLine" + consoleMessage.lineNumber() + ": " + consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class z implements View.OnClickListener {
        final /* synthetic */ com.iapp.app.j a;

        z(Aid_javaCode aid_javaCode, com.iapp.app.j jVar) {
            this.a = jVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            this.a.b("clicki" + id, Integer.valueOf(id), view);
        }
    }

    public Aid_javaCode(Activity activity) {
        this.a = new HashMap<>();
        this.b = null;
        this.f1838c = null;
        this.f1839d = null;
        this.e = null;
        this.f1838c = activity;
        this.b = activity;
        this.e = new k(activity.getMainLooper());
    }

    public Aid_javaCode(Activity activity, com.iapp.app.j jVar) {
        this.a = new HashMap<>();
        this.b = null;
        this.f1838c = null;
        this.f1839d = null;
        this.e = null;
        this.f1838c = activity;
        this.b = activity;
        this.f1839d = jVar;
        this.e = new u(activity.getMainLooper());
    }

    public Aid_javaCode(Context context, Activity activity) {
        this.a = new HashMap<>();
        this.b = null;
        this.f1838c = null;
        this.f1839d = null;
        this.e = null;
        this.f1838c = context;
        this.b = activity;
        this.e = new t(context.getMainLooper());
    }

    public Aid_javaCode(Context context, Activity activity, com.iapp.app.j jVar) {
        this.a = new HashMap<>();
        this.b = null;
        this.f1838c = null;
        this.f1839d = null;
        this.e = null;
        this.f1838c = context;
        this.b = activity;
        this.f1839d = jVar;
        this.e = new v(context.getMainLooper());
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    @TargetApi(16)
    private void A(WebView webView, String str, StringBuffer stringBuffer, com.iapp.app.j jVar) {
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAppCachePath(this.f1838c.getApplicationContext().getDir("cache", 0).getPath());
        webView.getSettings().setAppCacheMaxSize(8388608L);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDatabasePath(this.f1838c.getApplicationContext().getDir("database", 0).getPath());
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
        if (addViewEventItme(stringBuffer, str, webView.getId(), "ondownloadstart", "int st_vId,android.webkit.WebView st_vW,String st_url,String st_uT,String st_cN,String st_mE,long st_cH")) {
            new com.iapp.app.n(webView, jVar);
        } else {
            webView.setDownloadListener(new s(this));
        }
        new com.iapp.app.x5.b().b(webView, str, stringBuffer, jVar, this.b, this);
        z(webView);
    }

    private void B(String str) {
        c.b.a.a.f.G(this.f1838c, str);
    }

    private String[] C(String str, char c2) {
        return c.b.a.a.q.b(str, c2);
    }

    private String D(String str) {
        return str.substring(str.indexOf(40) + 1, str.lastIndexOf(41));
    }

    private void E(Camera camera) {
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

    private void F(Camera camera) {
        Camera.Parameters parameters;
        List<String> supportedFlashModes;
        if (camera == null || (parameters = camera.getParameters()) == null || (supportedFlashModes = parameters.getSupportedFlashModes()) == null || "torch".equals(parameters.getFlashMode()) || !supportedFlashModes.contains("torch")) {
            return;
        }
        parameters.setFlashMode("torch");
        camera.setParameters(parameters);
    }

    private int a(String str) {
        return (int) Double.parseDouble(str);
    }

    public static void degclear() {
        c.b.a.a.t.j.clear();
        f.clear();
        g = 0;
    }

    private void e(View view, int i2, String str, StringBuffer stringBuffer, com.iapp.app.j jVar) {
        if (addViewEventItme(stringBuffer, str, i2, "clicki", "int st_vId,android.view.View st_vW")) {
            view.setOnClickListener(new z(this, jVar));
        }
        if (addViewEventItme(stringBuffer, str, i2, "touchmonitor", "int st_vId,android.view.View st_vW,int st_eA,float st_eX,float st_eY,float st_rX,float st_rY")) {
            view.setOnTouchListener(new a0(this, jVar));
        }
        if (addViewEventItme(stringBuffer, str, i2, "press", "int st_vId,android.view.View st_vW")) {
            view.setOnLongClickListener(new a(this, jVar));
        }
        if (addViewEventItme(stringBuffer, str, i2, "keyboard", "int st_vId,android.view.View st_vW,int st_kC,int st_eA,int st_eR,android.view.KeyEvent st_eT")) {
            view.setOnKeyListener(new b(this, jVar));
        }
        if (str.contains("<eventItme type=\"pressmenu\">")) {
            String c2 = c.b.a.a.p.c(str, "<eventItme type=\"pressmenu\">", "</eventItme>");
            String[] split = c2.split("\ncase ");
            for (int i3 = 1; i3 < split.length; i3++) {
                stringBuffer.append("private void onCreateContextMenu" + i2 + "x" + i3 + "(){\n" + c.b.a.a.p.c(split[i3], ":", "\nbreak") + "\n}\n");
            }
            String c3 = c.b.a.a.p.c(c2, "\ndefault:", "\nbreak");
            if (c3 != null) {
                stringBuffer.append("private void onCreateContextMenu" + i2 + "x0(int st_vId, android.view.View st_vW){\n" + c3 + "\n}\n");
            }
            view.setOnCreateContextMenuListener(new c(this, jVar));
        }
        if (view instanceof TextView) {
            if (addViewEventItme(stringBuffer, str, i2, "editormonitor", "int st_vId,android.widget.TextView st_vW,st_aI,st_eA,st_eR,st_eK,st_eT")) {
                ((TextView) view).setOnEditorActionListener(new d(this, jVar));
            }
            boolean addViewEventItme = addViewEventItme(stringBuffer, str, i2, "ontextchanged", "int st_vId,android.widget.TextView st_vW,String st_sS,st_sT,int st_bE,int st_cT");
            boolean addViewEventItme2 = addViewEventItme(stringBuffer, str, i2, "beforetextchanged", "int st_vId,android.view.TextView st_vW,String st_sS,int st_sT,int st_cT,int st_aR");
            boolean addViewEventItme3 = addViewEventItme(stringBuffer, str, i2, "aftertextchanged", "int st_vId,android.view.TextView st_vW,String st_sS");
            if (addViewEventItme || addViewEventItme2 || addViewEventItme3) {
                new com.iapp.app.t((TextView) view, jVar, addViewEventItme, addViewEventItme2, addViewEventItme3);
            }
        }
        if (view instanceof WebView) {
            A((WebView) view, str, stringBuffer, jVar);
        }
        if (addViewEventItme(stringBuffer, str, i2, "focuschange", "int st_vId,android.view.View st_vW,boolean st_hF")) {
            view.setOnFocusChangeListener(new e(this, jVar));
        }
        if (view instanceof AbsListView) {
            boolean addViewEventItme4 = addViewEventItme(stringBuffer, str, i2, "onscrollstatechanged", "int st_vId,android.widget.AbsListView st_vW,int st_sE");
            boolean addViewEventItme5 = addViewEventItme(stringBuffer, str, i2, "onscroll", "int st_vId,android.widget.AbsListView st_vW,int st_fM,int st_vT,int st_bT");
            if (addViewEventItme4 || addViewEventItme5) {
                ((AbsListView) view).setOnScrollListener(new f(this, addViewEventItme4, jVar, addViewEventItme5));
            }
        }
        if (view instanceof AdapterView) {
            if (addViewEventItme(stringBuffer, str, i2, "clickitem", "int st_vId,android.widget.AdapterView st_vW,int st_pN,long st_iD,android.view.View st_vW2")) {
                ((AdapterView) view).setOnItemClickListener(new g(this, jVar));
            }
            boolean addViewEventItme6 = addViewEventItme(stringBuffer, str, i2, "onitemselected", "int st_vId,android.widget.AdapterView st_vW,android.view.View st_vW2,int st_pN,long st_iD");
            boolean addViewEventItme7 = addViewEventItme(stringBuffer, str, i2, "onnothingselected", "int st_vId,android.widget.AdapterView st_vW");
            if (addViewEventItme6 || addViewEventItme7) {
                ((AdapterView) view).setOnItemSelectedListener(new h(this, addViewEventItme6, jVar, addViewEventItme7));
            }
        }
        if (view instanceof ViewPager) {
            boolean addViewEventItme8 = addViewEventItme(stringBuffer, str, i2, "onpageselected", "int st_vId,android.support.v4.view.ViewPager st_vW,int st_pN");
            boolean addViewEventItme9 = addViewEventItme(stringBuffer, str, i2, "onpagescrolled", "int st_vId,android.support.v4.view.ViewPager st_vW,int st_pN,float st_pT,int st_pS");
            boolean addViewEventItme10 = addViewEventItme(stringBuffer, str, i2, "onpagescrollstatechanged", "int st_vId,android.support.v4.view.ViewPager st_vW,int st_sE");
            if (addViewEventItme8 || addViewEventItme9 || addViewEventItme10) {
                new com.iapp.app.q((ViewPager) view, jVar, addViewEventItme8, addViewEventItme9, addViewEventItme10);
            }
        }
        if (view instanceof DrawerLayout) {
            boolean addViewEventItme11 = addViewEventItme(stringBuffer, str, i2, "ondrawerclosed", "int st_vId,android.support.v4.widget.DrawerLayout st_vW,android.view.View st_dW");
            boolean addViewEventItme12 = addViewEventItme(stringBuffer, str, i2, "ondraweropened", "int st_vId,android.support.v4.widget.DrawerLayout st_vW,android.view.View st_dW");
            boolean addViewEventItme13 = addViewEventItme(stringBuffer, str, i2, "onoptionsitemselected", "int st_vId,android.support.v4.widget.DrawerLayout st_vW,android.view.MenuItem st_iM");
            if (addViewEventItme11 || addViewEventItme12 || addViewEventItme13) {
                new com.iapp.app.o((DrawerLayout) view, jVar, addViewEventItme11, addViewEventItme12, addViewEventItme13);
            }
        }
        if ((view instanceof SeekBar) && (str.contains("<eventItme type=\"onstarttrackingtouch\">") || str.contains("<eventItme type=\"onstoptrackingtouch\">") || str.contains("<eventItme type=\"onprogresschanged2\">"))) {
            boolean addViewEventItme14 = addViewEventItme(stringBuffer, str, i2, "onstarttrackingtouch", "int st_vId,android.widget.SeekBar st_vW");
            boolean addViewEventItme15 = addViewEventItme(stringBuffer, str, i2, "onstoptrackingtouch", "int st_vId,android.widget.SeekBar st_vW");
            boolean addViewEventItme16 = addViewEventItme(stringBuffer, str, i2, "onprogresschanged2", "int st_vId,android.widget.SeekBar st_vW,int st_nS,boolean st_fR");
            if (addViewEventItme14 || addViewEventItme15 || addViewEventItme16) {
                ((SeekBar) view).setOnSeekBarChangeListener(new i(this, addViewEventItme15, jVar, addViewEventItme14, addViewEventItme16));
            }
        }
        g(view, str, stringBuffer, jVar);
    }

    private void f(View view, String str, Object obj) {
        if (str.equals("clicki")) {
            view.setOnClickListener((View.OnClickListener) obj);
            return;
        }
        if (str.equals("touchmonitor")) {
            view.setOnTouchListener((View.OnTouchListener) obj);
            return;
        }
        if (str.equals("press")) {
            view.setOnLongClickListener((View.OnLongClickListener) obj);
            return;
        }
        if (str.equals("keyboard")) {
            view.setOnKeyListener((View.OnKeyListener) obj);
            return;
        }
        if (str.equals("editormonitor")) {
            ((TextView) view).setOnEditorActionListener((TextView.OnEditorActionListener) obj);
            return;
        }
        if (str.equals("addtextchanged")) {
            ((TextView) view).addTextChangedListener((TextWatcher) obj);
            return;
        }
        if (str.equals("ondownloadstart")) {
            ((WebView) view).setDownloadListener((com.iapp.app.x5.a) obj);
            return;
        }
        if (str.equals("webviewclient")) {
            com.iapp.app.x5.b.c((WebView) view, obj);
            return;
        }
        if (str.equals("focuschange")) {
            view.setOnFocusChangeListener((View.OnFocusChangeListener) obj);
            return;
        }
        if (str.equals("onscroll")) {
            ((AbsListView) view).setOnScrollListener((AbsListView.OnScrollListener) obj);
            return;
        }
        if (str.equals("clickitem")) {
            ((AdapterView) view).setOnItemClickListener((AdapterView.OnItemClickListener) obj);
            return;
        }
        if (str.equals("onitemselected")) {
            ((AdapterView) view).setOnItemSelectedListener((AdapterView.OnItemSelectedListener) obj);
            return;
        }
        if (str.equals("onpagechange")) {
            ((ViewPager) view).setOnPageChangeListener((ViewPager.OnPageChangeListener) obj);
        } else if (str.equals("ondrawer")) {
            ((DrawerLayout) view).setDrawerListener((DrawerLayout.DrawerListener) obj);
        } else if (str.equals("onseekbarchange")) {
            ((SeekBar) view).setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) obj);
        }
    }

    private void g(View view, String str, StringBuffer stringBuffer, com.iapp.app.j jVar) {
        if ((view instanceof TabLayout) && (str.contains("<eventItme type=\"ontabselected\">") || str.contains("<eventItme type=\"ontabunselected\">") || str.contains("<eventItme type=\"ontabreselected\">"))) {
            ((TabLayout) view).addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new j(this, addViewEventItme(stringBuffer, str, view.getId(), "ontabselected", "int st_vId,com.iapp.app.TabLayout st_vW,int st_pN,String st_tT,com.iapp.app.TabLayout.Tab st_taB"), view, jVar, addViewEventItme(stringBuffer, str, view.getId(), "ontabunselected", "int st_vId,com.iapp.app.TabLayout st_vW,int st_pN,String st_tT,com.iapp.app.TabLayout.Tab st_taB"), addViewEventItme(stringBuffer, str, view.getId(), "ontabreselected", "int st_vId,com.iapp.app.TabLayout st_vW,int st_pN,String st_tT,com.iapp.app.TabLayout.Tab st_taB")));
        }
        if (view instanceof RecyclerView) {
            if (str.contains("<eventItme type=\"onscrollstatechanged\">") || str.contains("<eventItme type=\"onscrolled\">")) {
                ((RecyclerView) view).addOnScrollListener(new l(this, addViewEventItme(stringBuffer, str, view.getId(), "onscrollstatechanged", "int st_vId,androidx.recyclerview.widget.RecyclerView st_vW,int st_sE"), jVar, addViewEventItme(stringBuffer, str, view.getId(), "onscrolled", "int st_vId,androidx.recyclerview.widget.RecyclerView st_vW,int st_fM,int st_vT,int st_bT,int st_dX,int st_dY,boolean st_isB")));
            }
            if (str.contains("<eventItme type=\"clickitem\">")) {
                ((RecyclerView) view).addOnItemTouchListener(new n(this, new GestureDetector(this.f1838c, new m(this, addViewEventItme(stringBuffer, str, view.getId(), "clickitem", "int st_vId,androidx.recyclerview.widget.RecyclerView st_vW,int st_pN,android.view.View st_vW2"), view, jVar))));
            }
        }
        if ((view instanceof VerticalViewPager) && (str.contains("<eventItme type=\"onpageselected\">") || str.contains("<eventItme type=\"onpagescrolled\">") || str.contains("<eventItme type=\"onpagescrollstatechanged\">"))) {
            ((VerticalViewPager) view).setOnPageChangeListener(new o(this, addViewEventItme(stringBuffer, str, view.getId(), "onpageselected", "int st_vId,fr.castorflex.android.verticalviewpager.VerticalViewPager st_vW,int st_pN"), view, jVar, addViewEventItme(stringBuffer, str, view.getId(), "onpagescrolled", "int st_vId,fr.castorflex.android.verticalviewpager.VerticalViewPager st_vW,int st_pN,float st_pT,int st_pS"), addViewEventItme(stringBuffer, str, view.getId(), "onpagescrollstatechanged", "int st_vId,fr.castorflex.android.verticalviewpager.VerticalViewPager st_vW,int st_sE")));
        }
        if ((view instanceof SwipeRefreshLayout) && str.contains("<eventItme type=\"onrefresh\">")) {
            ((SwipeRefreshLayout) view).setOnRefreshListener(new p(this, addViewEventItme(stringBuffer, str, view.getId(), "onrefresh", "int st_vId,SwipeRefreshLayout st_vW"), view, jVar));
        }
        if ((view instanceof CompoundButton) && str.contains("<eventItme type=\"oncheckedchanged\">")) {
            ((CompoundButton) view).setOnCheckedChangeListener(new q(this, addViewEventItme(stringBuffer, str, view.getId(), "oncheckedchanged", "int st_vId,android.widget.CompoundButton st_vW,boolean st_iC"), view, jVar));
        }
        if ((view instanceof AppBarLayout) && str.contains("<eventItme type=\"onoffsetchanged\">")) {
            ((AppBarLayout) view).addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) new r(this, addViewEventItme(stringBuffer, str, view.getId(), "onoffsetchanged", "st_vId,st_vW,st_vO"), jVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int h(String str) {
        Object obj = f.get(str);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        int i2 = g + 10000;
        g = i2;
        f.put(str, Integer.valueOf(i2));
        return g;
    }

    private byte[] i(String str, char c2) {
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

    private void j(String str, Object obj, int i2) {
        HashMap<String, Object> hashMap;
        if (str.equals("null")) {
            return;
        }
        if (i2 == 0) {
            hashMap = this.a;
        } else if (i2 == 1) {
            hashMap = ss_dim;
        } else if (i2 != 2) {
            return;
        } else {
            hashMap = c.b.a.a.t.j;
        }
        hashMap.put(str, obj);
    }

    private Object k(Object obj) {
        return obj;
    }

    private View l(View view, Object obj, String str) {
        String trim = str.trim();
        int indexOf = trim.indexOf(46);
        if (indexOf != -1 && trim.indexOf(34) == -1) {
            String substring = trim.substring(0, indexOf);
            String substring2 = trim.substring(indexOf + 1);
            Object obj2 = f.get(substring);
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
        Object obj3 = f.get(substring3);
        if (obj3 == null) {
            return null;
        }
        return view.findViewById(Integer.parseInt(String.valueOf(obj3)) + Integer.parseInt(substring4));
    }

    private View m(Object obj, String str) {
        String trim = str.trim();
        int indexOf = trim.indexOf(46);
        if (indexOf != -1 && trim.indexOf(34) == -1) {
            String substring = trim.substring(0, indexOf);
            String substring2 = trim.substring(indexOf + 1);
            Object obj2 = f.get(substring);
            if (obj2 == null) {
                return null;
            }
            return this.b.findViewById(Integer.parseInt(String.valueOf(obj2)) + Integer.parseInt(substring2));
        }
        String valueOf = String.valueOf(obj);
        int indexOf2 = valueOf.indexOf(46);
        if (indexOf2 == -1) {
            return this.b.findViewById(Integer.parseInt(valueOf));
        }
        String substring3 = valueOf.substring(0, indexOf2);
        String substring4 = valueOf.substring(indexOf2 + 1);
        Object obj3 = f.get(substring3);
        if (obj3 == null) {
            return null;
        }
        return this.b.findViewById(Integer.parseInt(String.valueOf(obj3)) + Integer.parseInt(substring4));
    }

    private int n() {
        try {
            Display defaultDisplay = this.b.getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, displayMetrics);
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            this.b.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics2);
            return displayMetrics.heightPixels - displayMetrics2.heightPixels;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private String[] o() {
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

    private int[] p() {
        return new int[]{this.b.getWindowManager().getDefaultDisplay().getWidth(), this.b.getWindowManager().getDefaultDisplay().getHeight()};
    }

    private int[] q() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.b.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    private String[] r() {
        return new String[]{Build.MODEL, Build.BRAND, String.valueOf(Build.VERSION.SDK_INT)};
    }

    private int s() {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return this.f1838c.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private int t() {
        int identifier = this.f1838c.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return this.f1838c.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private String u(String str) {
        return c.b.a.a.f.o(this.f1838c, str);
    }

    private GradientDrawable.Orientation v(String str) {
        return str.equals("topbottom") ? GradientDrawable.Orientation.TOP_BOTTOM : str.equals("trbl") ? GradientDrawable.Orientation.TR_BL : str.equals("rightleft") ? GradientDrawable.Orientation.RIGHT_LEFT : str.equals("brtl") ? GradientDrawable.Orientation.BR_TL : str.equals("bottomtop") ? GradientDrawable.Orientation.BOTTOM_TOP : str.equals("bltr") ? GradientDrawable.Orientation.BL_TR : str.equals("leftright") ? GradientDrawable.Orientation.LEFT_RIGHT : str.equals("tlbr") ? GradientDrawable.Orientation.TL_BR : GradientDrawable.Orientation.TOP_BOTTOM;
    }

    private String w(String str) {
        String c2;
        String c3;
        Intent launchIntentForPackage = this.f1838c.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null || (c2 = c.b.a.a.p.c(launchIntentForPackage.toString(), "cmp=", " ")) == null || (c3 = c.b.a.a.p.c(c2, "/", null)) == null) {
            return "";
        }
        if (!c3.startsWith(".")) {
            return c3;
        }
        return str + c3;
    }

    private void x(c.b.a.a.e eVar, Object obj) {
        if (eVar != null) {
            eVar.j = null;
            eVar.k = null;
            eVar.s((OnFileDownStatusListener) obj);
        }
    }

    private void y(File file) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        c.b.a.a.k.a(this.f1838c, intent, file, c.b.a.a.p.p(file));
        this.f1838c.startActivity(intent);
    }

    @TargetApi(11)
    private void z(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
    }

    public boolean addViewEventItme(StringBuffer stringBuffer, String str, int i2, String str2, String str3) {
        if (!str.contains("<eventItme type=\"" + str2 + "\">")) {
            return false;
        }
        String c2 = c.b.a.a.p.c(str, "<eventItme type=\"" + str2 + "\">", "</eventItme>");
        if (c2 == null) {
            return false;
        }
        stringBuffer.append("private void " + str2 + i2 + "(" + str3 + "){\n" + c2 + "\n}\n");
        return true;
    }

    public Object addv(Object obj, String str) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 != null) {
            if ((m2 instanceof ViewPager) || (m2 instanceof VerticalViewPager)) {
                ArrayList arrayList = new ArrayList();
                String[] C = C(str, '|');
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                for (String str2 : C) {
                    String trim = str2.trim();
                    if (trim.endsWith(".ijava")) {
                        LinearLayout linearLayout = new LinearLayout(this.f1838c);
                        linearLayout.setLayoutParams(layoutParams);
                        linearLayout.setOrientation(1);
                        openRestoreinterface(trim, linearLayout, h(trim.substring(0, trim.length() - 5)), null);
                        arrayList.add(linearLayout);
                    }
                }
                new com.iapp.app.u(m2, arrayList);
                return arrayList;
            }
            if ((m2 instanceof DrawerLayout) || (m2 instanceof ViewGroup)) {
                for (String str3 : C(str, '|')) {
                    String trim2 = str3.trim();
                    if (trim2.endsWith(".ijava")) {
                        openRestoreinterface(str3.trim(), (ViewGroup) m2, h(trim2.substring(0, trim2.length() - 5)), null);
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Object> aslist(ArrayList<Object> arrayList, Object[] objArr) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        if (objArr != null) {
            for (Object obj : objArr) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public ArrayList<Object> aslist(ArrayList<Object> arrayList, Object[] objArr, int i2) {
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        if (objArr != null) {
            for (Object obj : objArr) {
                arrayList.add(i2, obj);
            }
        }
        return arrayList;
    }

    public MediaPlayer bfm(String str) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        if (c.b.a.a.f.d(this.f1838c, mediaPlayer, str)) {
            return mediaPlayer;
        }
        return null;
    }

    public Object bfms(Object obj, String str) {
        int currentPosition;
        if (!(obj instanceof MediaPlayer)) {
            return null;
        }
        MediaPlayer mediaPlayer = (MediaPlayer) obj;
        if (str == null) {
            return null;
        }
        try {
            if (str.equals("st")) {
                mediaPlayer.start();
            } else if (str.equals("pe")) {
                mediaPlayer.pause();
            } else {
                if (str.equals("sp")) {
                    mediaPlayer.stop();
                    return null;
                }
                if (!str.equals("re")) {
                    if (str.equals("ip")) {
                        try {
                            return Boolean.valueOf(mediaPlayer.isPlaying());
                        } catch (IllegalStateException unused) {
                            return Boolean.FALSE;
                        }
                    }
                    if (str.equals("dn")) {
                        currentPosition = mediaPlayer.getDuration();
                    } else {
                        if (!str.equals("cn")) {
                            try {
                                mediaPlayer.stop();
                                mediaPlayer.reset();
                            } catch (Exception unused2) {
                            }
                            c.b.a.a.f.d(this.f1838c, mediaPlayer, str);
                            return null;
                        }
                        currentPosition = mediaPlayer.getCurrentPosition();
                    }
                    return Integer.valueOf(currentPosition);
                }
                mediaPlayer.release();
            }
            return null;
        } catch (IllegalStateException | Exception unused3) {
            return null;
        }
    }

    public Object bfms(Object obj, String str, int i2) {
        if (!(obj instanceof MediaPlayer)) {
            return null;
        }
        MediaPlayer mediaPlayer = (MediaPlayer) obj;
        if (str == null || !str.equals("seekto")) {
            return null;
        }
        mediaPlayer.seekTo(i2);
        return null;
    }

    public Object bfms(Object obj, String str, int i2, int i3) {
        if (!(obj instanceof MediaPlayer)) {
            return null;
        }
        MediaPlayer mediaPlayer = (MediaPlayer) obj;
        if (str == null || !str.equals("volume")) {
            return null;
        }
        mediaPlayer.setVolume(i2, i3);
        return null;
    }

    public Object bfms(Object obj, String str, boolean z2) {
        if (!(obj instanceof MediaPlayer)) {
            return null;
        }
        MediaPlayer mediaPlayer = (MediaPlayer) obj;
        if (str == null || !str.equals("sl")) {
            return null;
        }
        mediaPlayer.setLooping(z2);
        return null;
    }

    public void bfs(Object obj, int i2, String str) {
        if (obj instanceof Bitmap) {
            c.b.a.a.i.h((Bitmap) obj, i2, u(str));
        }
    }

    public void bfs(Object obj, String str) {
        if (obj instanceof Bitmap) {
            c.b.a.a.i.h((Bitmap) obj, 100, u(str));
        }
    }

    public void bfv(String str) {
        Intent intent = new Intent(this.f1838c, (Class<?>) Videoview.class);
        Bundle bundle = new Bundle();
        bundle.putString("video", str);
        bundle.putBoolean("sfhp", false);
        intent.putExtras(bundle);
        this.f1838c.startActivity(intent);
    }

    public void bfv(String str, boolean z2) {
        Intent intent = new Intent(this.f1838c, (Class<?>) Videoview.class);
        Bundle bundle = new Bundle();
        bundle.putString("video", str);
        bundle.putBoolean("sfhp", z2);
        intent.putExtras(bundle);
        this.f1838c.startActivity(intent);
    }

    public void bfvs(Object obj, Object obj2) {
        Uri c2;
        if (!(obj instanceof VideoView)) {
            k(obj);
            obj = m(obj, String.valueOf(obj));
        }
        VideoView videoView = (VideoView) obj;
        if (obj2 instanceof Uri) {
            c2 = (Uri) obj2;
        } else {
            String valueOf = String.valueOf(obj2);
            if (!c.b.a.a.p.v(valueOf.toLowerCase())) {
                videoView.setVideoPath(u(valueOf));
                return;
            }
            c2 = c.b.a.a.k.c(this.f1838c, valueOf);
        }
        videoView.setVideoURI(c2);
    }

    public Object bfvss(Object obj, String str) {
        int currentPosition;
        if (!(obj instanceof VideoView)) {
            k(obj);
            obj = m(obj, String.valueOf(obj));
        }
        VideoView videoView = (VideoView) obj;
        if (str.equals("st")) {
            videoView.requestFocus();
            videoView.start();
            return null;
        }
        if (str.equals("pe")) {
            videoView.pause();
            return null;
        }
        if (str.equals("sp")) {
            videoView.stopPlayback();
            return null;
        }
        if (str.equals("media")) {
            MediaController mediaController = new MediaController(this.f1838c);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            return mediaController;
        }
        if (str.equals("ip")) {
            try {
                return Boolean.valueOf(videoView.isPlaying());
            } catch (Exception unused) {
                return Boolean.FALSE;
            }
        }
        if (str.equals("dn")) {
            currentPosition = videoView.getDuration();
        } else {
            if (!str.equals("cn")) {
                return null;
            }
            currentPosition = videoView.getCurrentPosition();
        }
        return Integer.valueOf(currentPosition);
    }

    public Object bfvss(Object obj, String str, int i2) {
        if (!(obj instanceof VideoView)) {
            k(obj);
            obj = m(obj, String.valueOf(obj));
        }
        VideoView videoView = (VideoView) obj;
        if (!str.equals("seekto")) {
            return null;
        }
        videoView.seekTo(i2);
        return null;
    }

    public void blp(String str, int i2, int i3, int i4, int i5) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        com.iapp.app.p pVar = com.iapp.app.p.n;
        if (pVar != null) {
            pVar.j(u(str), i2, i3, i4, i5);
            return;
        }
        com.iapp.app.p pVar2 = new com.iapp.app.p(this.b);
        com.iapp.app.p.n = pVar2;
        pVar2.n(u(str), i2, i3, i4, i5);
    }

    public boolean blp(String str) {
        if (Build.VERSION.SDK_INT < 21 || com.iapp.app.p.n == null) {
            return false;
        }
        if (str.equals("st")) {
            com.iapp.app.p.n.p(true);
            return true;
        }
        if (str.equals("sp")) {
            com.iapp.app.p.n.p(false);
            return true;
        }
        if (str.equals("re")) {
            com.iapp.app.p.n.o();
            com.iapp.app.p.n = null;
            return true;
        }
        if (str.equals("ip")) {
            return com.iapp.app.p.n.i();
        }
        return false;
    }

    public MediaRecorder bly(MediaRecorder mediaRecorder, String str) {
        if (str.equals("sp")) {
            if (mediaRecorder == null) {
                return null;
            }
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
            }
            return null;
        }
        MediaRecorder mediaRecorder2 = new MediaRecorder();
        mediaRecorder2.setAudioSource(1);
        mediaRecorder2.setOutputFormat(0);
        String u2 = u(str);
        c.b.a.a.d.b(u2, false);
        mediaRecorder2.setOutputFile(u2);
        mediaRecorder2.setAudioEncoder(0);
        try {
            mediaRecorder2.prepare();
            mediaRecorder2.start();
            return mediaRecorder2;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public String btoo(String str, String str2) {
        byte[] bArr;
        try {
            bArr = i(str2.trim(), ' ');
        } catch (Exception unused) {
            bArr = null;
        }
        if (bArr == null) {
            return null;
        }
        if (str == null) {
            return new String(bArr);
        }
        try {
            return new String(bArr, str);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String btoo(String str, byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (str == null) {
            return new String(bArr);
        }
        try {
            return new String(bArr, str);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void btoo(String str, String str2, boolean z2) {
        byte[] bArr;
        try {
            bArr = i(str.trim(), ' ');
        } catch (Exception unused) {
            bArr = null;
        }
        if (bArr == null) {
            return;
        }
        String u2 = u(str2);
        File file = new File(u2);
        if (z2 || !file.exists()) {
            c.b.a.a.d.j(u2, bArr);
        }
    }

    public void btoo(byte[] bArr, String str, boolean z2) {
        if (bArr == null) {
            return;
        }
        String u2 = u(str);
        File file = new File(u2);
        if (z2 || !file.exists()) {
            c.b.a.a.d.j(u2, bArr);
        }
    }

    @SuppressLint({"NewApi"})
    public Object call(String str, String str2) {
        if (str.equals("myu")) {
            Object obj = c.b.a.a.t.l.get(str2 + "0");
            if (obj == null) {
                String substring = str2.substring(0, str2.indexOf(46) + 1);
                Iterator<String> it = c.b.a.a.t.l.keySet().iterator();
                while (it.hasNext()) {
                    if (it.next().toString().startsWith(substring)) {
                        return null;
                    }
                }
                B(substring + "myu");
                obj = c.b.a.a.t.l.get(str2 + "0");
            }
            String obj2 = obj.toString();
            c.b.a.a.f.E(this.f1838c, this.b, str2, obj2.substring(obj2.indexOf(10)).trim());
        } else if (str.equals("mjava")) {
            com.iapp.app.j jVar = new com.iapp.app.j(this.f1838c);
            try {
                jVar.set("activity", this.f1838c);
                jVar.set("i", new Aid_javaCode(this.f1838c, this.b, jVar));
            } catch (EvalError e2) {
                e2.printStackTrace();
            }
            int indexOf = str2.indexOf(46);
            if (jVar.e(str2.substring(0, indexOf) + ".mjava")) {
                return jVar.a(str2.substring(indexOf + 1));
            }
        } else if (str.equals("mlua")) {
            int indexOf2 = str2.indexOf(46);
            String str3 = "$_Call_SsS_" + Thread.currentThread().getId() + "_return";
            String str4 = "require 'import'\nrequire '" + str2.substring(0, indexOf2) + "'\nlocal returns = " + str2.substring(indexOf2 + 1) + "()\ni:sss(\"" + str3 + "\", returns)";
            com.iapp.app.d dVar = new com.iapp.app.d(this.f1838c);
            dVar.l("activity", this.f1838c);
            dVar.l("i", new Aid_luaCode(this.f1838c, this.b, dVar));
            dVar.k();
            try {
                dVar.h(str4);
                return c.b.a.a.t.j.get(str3);
            } catch (LuaException e3) {
                e3.printStackTrace();
                c.b.a.a.t.P2(this.f1838c, "LuaErr：\n" + e3.getMessage());
            }
        } else if (str.equals("mjs")) {
            int indexOf3 = str2.indexOf(46);
            StringBuilder sb = new StringBuilder();
            sb.append("<html><head><script type='text/javascript'>");
            sb.append(c.b.a.a.f.e(this.f1838c, "import.mjs"));
            sb.append("\n");
            sb.append(c.b.a.a.f.a(this.f1838c, str2.substring(0, indexOf3) + ".mjs"));
            sb.append("\n\nvar returns = ");
            sb.append(str2.substring(indexOf3 + 1));
            sb.append("();</script></head></html>");
            String sb2 = sb.toString();
            android.webkit.WebView webView = new android.webkit.WebView(this.f1838c);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setAppCachePath(this.f1838c.getApplicationContext().getDir("cache", 0).getPath());
            webView.getSettings().setAppCacheMaxSize(8388608L);
            webView.getSettings().setDatabaseEnabled(true);
            webView.getSettings().setDatabasePath(this.f1838c.getApplicationContext().getDir("database", 0).getPath());
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setGeolocationEnabled(true);
            webView.getSettings().setLightTouchEnabled(true);
            webView.getSettings().setCacheMode(-1);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.setWebChromeClient(new y());
            if (Build.VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
            }
            webView.addJavascriptInterface(new Aid_jsCode(this.f1838c, this.b, webView, 0), "I");
            com.iapp.app.c.d(webView, sb2);
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    public Object call(String str, String str2, Object[] objArr) {
        int length = objArr.length;
        if (str.equals("myu")) {
            Object obj = c.b.a.a.t.l.get(str2 + length);
            if (obj == null) {
                String substring = str2.substring(0, str2.indexOf(46) + 1);
                Iterator<String> it = c.b.a.a.t.l.keySet().iterator();
                while (it.hasNext()) {
                    if (it.next().toString().startsWith(substring)) {
                        return null;
                    }
                }
                B(substring + "myu");
                obj = c.b.a.a.t.l.get(str2 + length);
            }
            String obj2 = obj.toString();
            int indexOf = obj2.indexOf(10);
            String[] C = C(D(obj2.substring(0, indexOf)), ',');
            if (length != C.length) {
                return null;
            }
            c.b.a.a.f.F(this.f1838c, this.b, C, objArr, str2, obj2.substring(indexOf).trim());
        } else {
            if (!str.equals("mjava")) {
                String str3 = "_a";
                if (str.equals("mlua")) {
                    long id = Thread.currentThread().getId();
                    String str4 = "";
                    String str5 = str4;
                    int i2 = 0;
                    while (i2 < length) {
                        String str6 = "$_Call_SsS_" + id + str3 + i2;
                        c.b.a.a.t.j.put(str6, objArr[i2]);
                        str5 = str5 + "local a" + i2 + " = i:sss(\"" + str6 + "\")\n";
                        str4 = str4 + ",a" + i2;
                        i2++;
                        str3 = str3;
                        length = length;
                    }
                    if (str4.length() > 0) {
                        str4 = str4.substring(1);
                    }
                    String str7 = "$_Call_SsS_" + id + "_return";
                    int indexOf2 = str2.indexOf(46);
                    String str8 = "require 'import'\nrequire '" + str2.substring(0, indexOf2) + "'\n" + str5 + "\nlocal returns = " + str2.substring(indexOf2 + 1) + "(" + str4 + ")\ni:sss(\"" + str7 + "\", returns)";
                    com.iapp.app.d dVar = new com.iapp.app.d(this.f1838c);
                    dVar.l("activity", this.f1838c);
                    dVar.l("i", new Aid_luaCode(this.f1838c, this.b, dVar));
                    dVar.k();
                    try {
                        dVar.h(str8);
                        return c.b.a.a.t.j.get(str7);
                    } catch (LuaException e2) {
                        e2.printStackTrace();
                        c.b.a.a.t.P2(this.f1838c, "LuaErr：\n" + e2.getMessage());
                    }
                } else if (str.equals("mjs")) {
                    long id2 = Thread.currentThread().getId();
                    String str9 = "";
                    String str10 = str9;
                    for (int i3 = 0; i3 < length; i3++) {
                        String str11 = "$_Call_SsS_" + id2 + "_a" + i3;
                        c.b.a.a.t.j.put(str11, objArr[i3]);
                        str10 = str10 + "var a" + i3 + " = I.sss(\"" + str11 + "\");\n";
                        str9 = str9 + ",a" + i3;
                    }
                    if (str9.length() > 0) {
                        str9 = str9.substring(1);
                    }
                    int indexOf3 = str2.indexOf(46);
                    StringBuilder sb = new StringBuilder();
                    sb.append("<html><head><script type='text/javascript'>");
                    sb.append(c.b.a.a.f.e(this.f1838c, "import.mjs"));
                    sb.append("\n");
                    sb.append(c.b.a.a.f.a(this.f1838c, str2.substring(0, indexOf3) + ".mjs"));
                    sb.append("\n");
                    sb.append(str10);
                    sb.append("\nvar returns = ");
                    sb.append(str2.substring(indexOf3 + 1));
                    sb.append("(");
                    sb.append(str9);
                    sb.append(");</script></head></html>");
                    String sb2 = sb.toString();
                    android.webkit.WebView webView = new android.webkit.WebView(this.f1838c);
                    webView.getSettings().setAllowFileAccess(true);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setAppCacheEnabled(true);
                    webView.getSettings().setAppCachePath(this.f1838c.getApplicationContext().getDir("cache", 0).getPath());
                    webView.getSettings().setAppCacheMaxSize(8388608L);
                    webView.getSettings().setDatabaseEnabled(true);
                    webView.getSettings().setDatabasePath(this.f1838c.getApplicationContext().getDir("database", 0).getPath());
                    webView.getSettings().setDomStorageEnabled(true);
                    webView.getSettings().setGeolocationEnabled(true);
                    webView.getSettings().setLightTouchEnabled(true);
                    webView.getSettings().setCacheMode(-1);
                    webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                    webView.setWebChromeClient(new x());
                    if (Build.VERSION.SDK_INT >= 11) {
                        webView.removeJavascriptInterface("searchBoxJavaBridge_");
                        webView.removeJavascriptInterface("accessibility");
                        webView.removeJavascriptInterface("accessibilityTraversal");
                    }
                    webView.addJavascriptInterface(new Aid_jsCode(this.f1838c, this.b, webView, 0), "I");
                    com.iapp.app.c.d(webView, sb2);
                }
                return null;
            }
            com.iapp.app.j jVar = new com.iapp.app.j(this.f1838c);
            try {
                jVar.set("activity", this.f1838c);
                jVar.set("i", new Aid_javaCode(this.f1838c, this.b, jVar));
            } catch (EvalError e3) {
                e3.printStackTrace();
            }
            int indexOf4 = str2.indexOf(46);
            if (jVar.e(str2.substring(0, indexOf4) + ".mjava")) {
                return jVar.b(str2.substring(indexOf4 + 1), objArr);
            }
        }
        return null;
    }

    public Object cast(Object obj, Object obj2) {
        return (obj instanceof Class ? (Class) obj : obj instanceof String ? c.b.a.a.b.a(String.valueOf(obj)) : obj.getClass()).cast(obj2);
    }

    public void clear_s_dim() {
        this.a.clear();
    }

    public Class<?> cls(ClassLoader classLoader, String str) {
        try {
            return classLoader.loadClass(str);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public Class<?> cls(String str) {
        return c.b.a.a.b.b(str);
    }

    public Object[] clssm(Class<?> cls, String str) {
        if (str.equals("init")) {
            return cls.getDeclaredConstructors();
        }
        if (str.equals("method")) {
            return cls.getDeclaredMethods();
        }
        if (str.equals("field")) {
            return cls.getDeclaredFields();
        }
        return null;
    }

    public Object dh(c.d.a.a aVar, String str) {
        if (str.equals("cancel")) {
            aVar.b();
            return null;
        }
        if (str.equals("clone")) {
            return aVar.clone();
        }
        if (str.equals("start")) {
            aVar.j();
            return null;
        }
        if (str.equals("running")) {
            return Boolean.valueOf(aVar.e());
        }
        return null;
    }

    public Object dh(c.d.a.a aVar, String str, Object obj) {
        View m2;
        if (str.equals("duration")) {
            k(obj);
            aVar.g(Long.parseLong(obj.toString()));
            return null;
        }
        if (str.equals("delay")) {
            k(obj);
            aVar.h(Long.parseLong(obj.toString()));
            return null;
        }
        if (!str.equals("target")) {
            return null;
        }
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        aVar.i(m2);
        return null;
    }

    public Object dh(c.d.a.c cVar, String str) {
        if (str.equals("cancel")) {
            cVar.b();
            return null;
        }
        if (str.equals("clone")) {
            return cVar.clone();
        }
        if (str.equals("start")) {
            cVar.j();
            return null;
        }
        if (str.equals("running")) {
            return Boolean.valueOf(cVar.e());
        }
        return null;
    }

    public Object dh(c.d.a.c cVar, String str, Object obj) {
        View m2;
        if (str.equals("duration")) {
            k(obj);
            cVar.u(Long.parseLong(obj.toString()));
            return null;
        }
        if (str.equals("delay")) {
            k(obj);
            cVar.h(Long.parseLong(obj.toString()));
            return null;
        }
        if (!str.equals("target")) {
            return null;
        }
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        cVar.i(m2);
        return null;
    }

    public void dh(Animation animation, String str) {
        if (str.equals("cancel")) {
            animation.cancel();
        } else if (str.equals("reset")) {
            animation.reset();
        } else if (str.equals("start")) {
            animation.start();
        }
    }

    public void dh(Animation animation, String str, Object obj) {
        Boolean bool = Boolean.TRUE;
        if (str.equals("duration")) {
            k(obj);
            animation.setDuration(Long.parseLong(obj.toString()));
            return;
        }
        if (str.equals("delay")) {
            k(obj);
            animation.setStartOffset(Long.parseLong(obj.toString()));
            return;
        }
        if (str.equals("enabled")) {
            animation.setFillEnabled(obj.equals(bool));
            return;
        }
        if (str.equals("after")) {
            animation.setFillAfter(obj.equals(bool));
            return;
        }
        if (str.equals("before")) {
            animation.setFillBefore(obj.equals(bool));
        } else if (str.equals("repeat")) {
            k(obj);
            animation.setRepeatCount(Integer.parseInt(obj.toString()));
        }
    }

    public void dh(AnimationSet animationSet, String str) {
        if (str.equals("cancel")) {
            animationSet.cancel();
        } else if (str.equals("reset")) {
            animationSet.reset();
        } else if (str.equals("start")) {
            animationSet.start();
        }
    }

    public void dh(AnimationSet animationSet, String str, Object obj) {
        Boolean bool = Boolean.TRUE;
        if (str.equals("duration")) {
            k(obj);
            animationSet.setDuration(Long.parseLong(obj.toString()));
            return;
        }
        if (str.equals("enabled")) {
            animationSet.setFillEnabled(obj.equals(bool));
            return;
        }
        if (str.equals("after")) {
            animationSet.setFillAfter(obj.equals(bool));
            return;
        }
        if (str.equals("before")) {
            animationSet.setFillBefore(obj.equals(bool));
            return;
        }
        if (str.equals("delay")) {
            k(obj);
            animationSet.setStartOffset(Long.parseLong(obj.toString()));
        } else if (str.equals("repeat")) {
            k(obj);
            animationSet.setRepeatCount(Integer.parseInt(obj.toString()));
        } else if (str.equals("add")) {
            animationSet.addAnimation((Animation) obj);
        }
    }

    public AlphaAnimation dha(boolean z2, boolean z3) {
        return new AlphaAnimation(z2 ? 1.0f : 0.0f, z3 ? 1.0f : 0.0f);
    }

    public c.d.a.i dhas(Object obj, String str, Object[] objArr) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        int length = objArr.length;
        float[] fArr = new float[length];
        for (int i2 = 0; i2 < length; i2++) {
            fArr[i2] = Float.parseFloat(objArr[i2].toString());
        }
        return c.d.a.i.K(m2, str, fArr);
    }

    public c.d.a.c dhast(String str, Object[] objArr) {
        int length = objArr.length;
        c.d.a.a[] aVarArr = new c.d.a.a[length];
        for (int i2 = 0; i2 < length; i2++) {
            aVarArr[i2] = (c.d.a.a) objArr[i2];
        }
        c.d.a.c cVar = new c.d.a.c();
        if (str.equals("sequen")) {
            cVar.s(aVarArr);
        } else if (str.equals("together")) {
            cVar.t(aVarArr);
        }
        return cVar;
    }

    public AnimationDrawable dhb(boolean z2) {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.setOneShot(z2);
        return animationDrawable;
    }

    public Object dhb(AnimationDrawable animationDrawable, String str) {
        if (str.equals("start")) {
            animationDrawable.start();
            return null;
        }
        if (str.equals("stop")) {
            animationDrawable.stop();
            return null;
        }
        if (str.equals("running")) {
            return Boolean.valueOf(animationDrawable.isRunning());
        }
        return null;
    }

    public void dhb(AnimationDrawable animationDrawable, Object obj, int i2) {
        Drawable m2;
        if (obj instanceof Drawable) {
            m2 = (Drawable) obj;
        } else if (obj instanceof Bitmap) {
            m2 = new BitmapDrawable((Bitmap) obj);
        } else {
            k(obj);
            m2 = com.iapp.app.i.m(String.valueOf(obj), this.f1838c);
        }
        animationDrawable.addFrame(m2, i2);
    }

    public void dhon(Animation animation, Animation.AnimationListener animationListener) {
        animation.setAnimationListener(animationListener);
    }

    public void dhon(c.d.a.a aVar, a.InterfaceC0049a interfaceC0049a) {
        aVar.a(interfaceC0049a);
    }

    public RotateAnimation dhr(float f2, float f3) {
        return new RotateAnimation(f2, f3);
    }

    public RotateAnimation dhr(float f2, float f3, int i2, float f4, int i3, float f5) {
        return new RotateAnimation(f2, f3, i2, f4, i3, f5);
    }

    public ScaleAnimation dhs(float f2, float f3, float f4, float f5) {
        return new ScaleAnimation(f2, f3, f4, f5);
    }

    public ScaleAnimation dhs(float f2, float f3, float f4, float f5, int i2, float f6, int i3, float f7) {
        return new ScaleAnimation(f2, f3, f4, f5, i2, f6, i3, f7);
    }

    public AnimationSet dhset(AnimationSet animationSet, Object[] objArr) {
        for (Object obj : objArr) {
            animationSet.addAnimation((Animation) obj);
        }
        return animationSet;
    }

    public AnimationSet dhset(boolean z2, Object[] objArr) {
        AnimationSet animationSet = new AnimationSet(z2);
        for (Object obj : objArr) {
            animationSet.addAnimation((Animation) obj);
        }
        return animationSet;
    }

    public TranslateAnimation dht(float f2, float f3, float f4, float f5) {
        return new TranslateAnimation(f2, f3, f4, f5);
    }

    public void dim(String str, Object obj) {
        int indexOf = str.indexOf(46);
        if (indexOf != -1) {
            String substring = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
            if (substring.equals("ss")) {
                j(str, obj, 1);
                return;
            } else if (substring.equals("sss")) {
                j(str, obj, 2);
                return;
            }
        }
        this.a.put(str, obj);
    }

    public Object dimget(String str) {
        HashMap<String, Object> hashMap;
        int indexOf = str.indexOf(46);
        if (indexOf != -1) {
            String trim = str.substring(0, indexOf).trim();
            str = str.substring(indexOf + 1).trim();
            if (trim.equals("ss")) {
                hashMap = ss_dim;
            } else if (trim.equals("sss")) {
                hashMap = c.b.a.a.t.j;
            }
            return hashMap.get(str);
        }
        hashMap = this.a;
        return hashMap.get(str);
    }

    public void dslist(ArrayList<Object> arrayList, Object obj) {
        k(obj);
        if (!(obj instanceof Integer) && !(obj instanceof Double)) {
            arrayList.remove(obj);
            return;
        }
        int parseInt = Integer.parseInt(String.valueOf(obj));
        if (parseInt == -1) {
            arrayList.clear();
        } else {
            arrayList.remove(parseInt);
        }
    }

    public void endi() {
        this.b.finish();
    }

    public void endkeyboard() {
        View peekDecorView = this.b.getWindow().peekDecorView();
        if (peekDecorView != null) {
            ((InputMethodManager) this.f1838c.getSystemService("input_method")).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }

    public void ends() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addFlags(270532608);
        this.f1838c.startActivity(intent);
    }

    public void endutw() {
        AlertDialog alertDialog = alertdialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            alertdialog = null;
        }
    }

    public boolean fc(String str, String str2) {
        return c.b.a.a.f.f(this.f1838c, str, str2, true);
    }

    public boolean fc(String str, String str2, boolean z2) {
        return c.b.a.a.f.f(this.f1838c, str, str2, z2);
    }

    public boolean fd(String str) {
        File file = new File(u(str));
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public String fdir() {
        return c.b.a.a.d.m(this.f1838c);
    }

    public String fdir(String str) {
        return u(str);
    }

    public boolean fe(String str) {
        return c.b.a.a.f.g(this.f1838c, str);
    }

    public boolean fi(String str) {
        return new File(u(str)).isDirectory();
    }

    public boolean fj(String str, String str2) {
        String u2 = u(str2);
        c.b.a.a.d.b(u2, false);
        try {
            c.b.a.a.c.c(u(str), u2, true);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean fj(String str, String str2, boolean z2) {
        String u2 = u(str2);
        c.b.a.a.d.b(u2, false);
        try {
            c.b.a.a.c.c(u(str), u2, z2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public Object[] fl(String str) {
        return c.b.a.a.f.h(this.f1838c, str);
    }

    public Object[] fl(String str, boolean z2) {
        File[] listFiles;
        int i2;
        File file = new File(u(str));
        if (!file.exists() || (listFiles = file.listFiles()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int length = listFiles.length;
        while (i2 < length) {
            File file2 = listFiles[i2];
            if (z2) {
                i2 = file2.isDirectory() ? 0 : i2 + 1;
                arrayList.add(file2.getName());
            } else {
                if (!file2.isFile()) {
                }
                arrayList.add(file2.getName());
            }
        }
        return arrayList.toArray();
    }

    public void fn(String str) {
        this.f1839d.f(str + ".mjava");
    }

    public void fo(String str) {
        String u2 = u(str);
        File file = new File(u2);
        if (file.exists()) {
            if (u2.toLowerCase().endsWith(".apk")) {
                c.b.a.a.d.d(this.f1838c, u2);
            } else {
                try {
                    y(file);
                } catch (Exception unused) {
                }
            }
        }
    }

    public String fr(String str) {
        return c.b.a.a.f.i(this.f1838c, str);
    }

    public String fr(String str, String str2) {
        return c.b.a.a.f.j(this.f1838c, str, str2);
    }

    public long fs(String str) {
        return c.b.a.a.f.k(this.f1838c, str);
    }

    public boolean ft(String str, String str2) {
        File file = new File(u(str));
        if (!file.exists()) {
            return false;
        }
        String u2 = u(str2);
        c.b.a.a.d.b(u2, false);
        return file.renameTo(new File(u2));
    }

    public void ftz(String str, String str2, String str3, Object obj, String str4) {
        k(obj);
        c.b.a.a.r.b(this.f1838c, str, str2, str3, obj, new Intent().setClass(this.f1838c, logoActivity.class).putExtra("command2", str4));
    }

    public int fuz(String str, String str2, String str3) {
        String u2 = u(str3);
        c.b.a.a.d.b(u2, false);
        try {
            return c.b.a.a.f.l(this.f1838c, str, str2, u2, true);
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public int fuz(String str, String str2, String str3, boolean z2) {
        String u2 = u(str3);
        c.b.a.a.d.b(u2, false);
        try {
            return c.b.a.a.f.l(this.f1838c, str, str2, u2, z2);
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public boolean fuzs(String str, String str2) {
        String u2 = u(str2);
        c.b.a.a.d.b(u2, false);
        try {
            c.b.a.a.f.m(this.f1838c, str, u2, true);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean fuzs(String str, String str2, boolean z2) {
        String u2 = u(str2);
        c.b.a.a.d.b(u2, false);
        try {
            c.b.a.a.f.m(this.f1838c, str, u2, z2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public void fw(String str, String str2) {
        c.b.a.a.d.k(u(str), str2);
    }

    public void fw(String str, String str2, String str3) {
        c.b.a.a.d.l(u(str), str2, str3);
    }

    public Object git(Object obj, String str) {
        Intent intent = obj instanceof Intent ? (Intent) obj : null;
        if (intent == null) {
            return null;
        }
        if (str.equals("action")) {
            return intent.getAction();
        }
        if (str.equals("type")) {
            return intent.getType();
        }
        if (str.equals("flags")) {
            return Integer.valueOf(intent.getFlags());
        }
        if (str.equals("data")) {
            return intent.getData();
        }
        if (str.equals("datastring")) {
            return intent.getDataString();
        }
        if (str.equals("component")) {
            return intent.getComponent();
        }
        return null;
    }

    public Object git(Object obj, String str, String str2) {
        Intent intent = obj instanceof Intent ? (Intent) obj : null;
        if (intent != null && str.equals("extra")) {
            return intent.getExtras().get(str2);
        }
        return null;
    }

    public Object gslist(ArrayList<Object> arrayList, int i2) {
        return arrayList.get(i2);
    }

    public int gslistiof(ArrayList<Object> arrayList, Object obj) {
        return arrayList.indexOf(obj);
    }

    public boolean gslistis(ArrayList<Object> arrayList, Object obj) {
        return arrayList.contains(obj);
    }

    public int gslistl(ArrayList<Object> arrayList) {
        return arrayList.size();
    }

    public int gslistlof(ArrayList<Object> arrayList, Object obj) {
        return arrayList.lastIndexOf(obj);
    }

    public Object[] gslistsz(ArrayList<Object> arrayList) {
        return arrayList.toArray();
    }

    public View gvs(Object obj) {
        k(obj);
        return m(obj, String.valueOf(obj));
    }

    public View gvs(Object obj, Object obj2) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (obj2 instanceof View) {
            return (View) obj2;
        }
        k(obj2);
        return l(m2, obj2, String.valueOf(obj2));
    }

    public void has(Object obj, String str) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 instanceof WebView) {
            ((WebView) m2).loadUrl("javascript:{" + str + "};");
        }
    }

    public int hd(String str, String str2) {
        return c.b.a.a.g.a(str, u(str2), false);
    }

    public int hd(String str, String str2, boolean z2) {
        return c.b.a.a.g.a(str, u(str2), z2);
    }

    public int hd(String str, String str2, boolean z2, String str3, String str4, String str5, boolean z3, String str6) {
        return c.b.a.a.g.b(str, u(str2), z2, str3, str4, str5, z3, str6);
    }

    public void hdd(String str, String str2, int i2, int i3, int i4, int i5, boolean z2) {
        com.iapp.app.a.b.m(u(str), u(str2), i2, i3, i4, i5, z2);
    }

    public c.b.a.a.w.c hdda(String str, String str2, Object obj) {
        return com.iapp.app.a.b.d(str, str2, obj);
    }

    public c.b.a.a.w.c hdda(String str, String str2, String str3, Object obj) {
        return com.iapp.app.a.b.e(str, str2, str3, obj);
    }

    public c.b.a.a.w.c hdda(String str, String str2, String str3, Object obj, Object obj2) {
        k(obj);
        return com.iapp.app.a.b.f(str, str2, str3, obj, obj2);
    }

    public c.b.a.a.w.c hdda(String str, String str2, String str3, String str4, Object obj, boolean z2, Object obj2) {
        k(obj);
        return com.iapp.app.a.b.g(str, u(str2), str3, str4, obj, z2, obj2);
    }

    public Object hddg(int i2, String str) {
        return com.iapp.app.a.b.h(com.iapp.app.a.b.f1379c.get(i2), str);
    }

    public Object hddg(c.b.a.a.w.c cVar, String str) {
        return com.iapp.app.a.b.h(cVar, str);
    }

    public ArrayList<c.b.a.a.w.c> hddgl() {
        return com.iapp.app.a.b.f1379c;
    }

    public void hdds(int i2, String str, Object obj) {
        k(obj);
        com.iapp.app.a.b.l(com.iapp.app.a.b.f1379c.get(i2), str, obj);
    }

    public void hdds(c.b.a.a.w.c cVar, String str, Object obj) {
        k(obj);
        com.iapp.app.a.b.l(cVar, str, obj);
    }

    public void hdduigo() {
        this.f1838c.startActivity(new Intent().setClass(this.f1838c, DownList.class));
    }

    public void hdduigo(String str, String str2) {
        Intent intent = new Intent(this.f1838c, (Class<?>) DownList.class);
        Bundle bundle = new Bundle();
        bundle.putString("background", str);
        bundle.putString("backgroundShadow", str2);
        intent.putExtras(bundle);
        this.f1838c.startActivity(intent);
    }

    public c.b.a.a.e hdfl(String str, Object obj) {
        c.b.a.a.e eVar = new c.b.a.a.e(u(str));
        x(eVar, obj);
        return eVar;
    }

    public c.b.a.a.e hdfl(String str, String str2, int i2, int i3, boolean z2, Object obj) {
        c.b.a.a.e eVar = new c.b.a.a.e(u(str), u(str2), i2, i3, z2);
        x(eVar, obj);
        return eVar;
    }

    public c.b.a.a.e hdfl(String str, String str2, Object obj) {
        c.b.a.a.e eVar = new c.b.a.a.e(u(str), u(str2));
        x(eVar, obj);
        return eVar;
    }

    public void hdfla(c.b.a.a.e eVar, String str, int i2, Object obj) {
        eVar.n(str, i2, obj);
        eVar.t();
    }

    public void hdfla(c.b.a.a.e eVar, String str, int i2, Object obj, String str2) {
        eVar.o(str, i2, obj, u(str2));
        eVar.t();
    }

    public String hs(String str) {
        return (str.equals("cookie") || str.equals("del cookie")) ? c.b.a.a.g.d(str) : str.startsWith("cookie:") ? c.b.a.a.g.d(str.substring(7)) : c.b.a.a.g.e(str, null, null);
    }

    public String hs(String str, String str2, String str3) {
        return c.b.a.a.g.e(str, str2, str3);
    }

    public String hs(String str, String str2, String str3, String str4) {
        return c.b.a.a.g.f(str, str2, str3, str4);
    }

    public String hs(String str, String str2, String str3, String str4, boolean z2) {
        return c.b.a.a.g.g(str, str2, str3, str4, z2);
    }

    public String hs(String str, String str2, String str3, String str4, boolean z2, String str5) {
        return c.b.a.a.g.h(str, str2, str3, str4, z2, str5, 20000, 20000, null);
    }

    public String hs(String str, String str2, String str3, String str4, boolean z2, String str5, int i2, int i3, String str6) {
        return c.b.a.a.g.h(str, str2, str3, str4, z2, str5, i2, i3, str6);
    }

    @TargetApi(11)
    public void hsas(Object obj, boolean z2) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 instanceof WebView) {
            WebView webView = (WebView) m2;
            if (Build.VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface("iapp");
            }
            if (z2) {
                webView.addJavascriptInterface(new iapp(this.f1838c, this.b), "iapp");
            }
        }
    }

    public String huf(String str, String str2, String str3, String str4) {
        try {
            return c.b.a.a.g.m(this.f1838c, str, str2, str3, str4);
        } catch (Exception unused) {
            return null;
        }
    }

    public String huf(String str, String str2, String str3, String str4, String str5) {
        try {
            return c.b.a.a.g.n(this.f1838c, str, str2, str3, str4, str5);
        } catch (Exception unused) {
            return null;
        }
    }

    public void hw(String str) {
        Intent intent = new Intent(this.f1838c, (Class<?>) Webview.class);
        Bundle bundle = new Bundle();
        bundle.putString("WebURL", str);
        intent.putExtras(bundle);
        this.f1838c.startActivity(intent);
    }

    public void hw(String str, String str2, String str3) {
        Intent intent = new Intent(this.f1838c, (Class<?>) Webview.class);
        Bundle bundle = new Bundle();
        bundle.putString("WebURL", str);
        bundle.putString("background", str2);
        bundle.putString("backgroundShadow", str3);
        intent.putExtras(bundle);
        this.f1838c.startActivity(intent);
    }

    public void hws(String str) {
        Intent intent;
        try {
            intent = c.b.a.a.g.q(this.f1838c, str);
        } catch (Exception unused) {
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.VIEW");
            intent2.setData(c.b.a.a.k.c(this.f1838c, str));
            intent = intent2;
        }
        this.f1838c.startActivity(intent);
    }

    public void imports(String str) {
        try {
            this.f1839d.eval("import " + str.replace(';', ' ').replace('\n', ' ').replace(",", "; import ") + ";", this.f1839d.getNameSpace());
        } catch (EvalError e2) {
            e2.printStackTrace();
            c.b.a.a.t.P2(this.f1838c, "JavaErr：\n" + e2.toString());
        }
    }

    public Object java(Object obj, String str) {
        return c.b.a.a.b.g(this.f1838c, obj, str, null, null, null);
    }

    public Object java(Object obj, String str, Object[] objArr) {
        return c.b.a.a.b.g(this.f1838c, obj, str, objArr, null, null);
    }

    public Object java(Object obj, String str, Object[] objArr, Object obj2) {
        return c.b.a.a.b.g(this.f1838c, obj, str, objArr, null, obj2);
    }

    public Object javacb(Class<?> cls, Object obj) {
        return c.b.a.a.b.o(cls.getClassLoader(), cls, null, obj);
    }

    public Object javags(Object obj, Object obj2, String str) {
        return obj2 instanceof Class ? c.b.a.a.b.i(obj, (Class) obj2, str) : c.b.a.a.b.k(obj, obj2.toString(), str);
    }

    public Object javags(Object obj, String str) {
        return c.b.a.a.b.j(obj, str);
    }

    public Object javanew(Object obj) {
        return obj instanceof Class ? c.b.a.a.b.m(this.f1838c, (Class) obj, null, null, null) : c.b.a.a.b.n(this.f1838c, obj.toString(), null, null, null);
    }

    public Object javanew(Object obj, Object[] objArr) {
        return obj instanceof Class ? c.b.a.a.b.m(this.f1838c, (Class) obj, objArr, null, null) : c.b.a.a.b.n(this.f1838c, obj.toString(), objArr, null, null);
    }

    public Object javanew(Object obj, Object[] objArr, Object obj2) {
        return obj instanceof Class ? c.b.a.a.b.m(this.f1838c, (Class) obj, objArr, null, obj2) : c.b.a.a.b.n(this.f1838c, obj.toString(), objArr, null, obj2);
    }

    public Object javass(Object obj, Object obj2, String str, Object obj3) {
        return Boolean.valueOf(obj2 instanceof Class ? c.b.a.a.b.q(obj, (Class) obj2, str, obj3) : c.b.a.a.b.r(obj, obj2.toString(), str, obj3));
    }

    public Object javass(Object obj, String str, Object obj2) {
        return c.b.a.a.b.p(obj, str, obj2);
    }

    public Object javax(Object obj, Object obj2, String str) {
        return obj2 instanceof Class ? c.b.a.a.b.e(this.f1838c, obj, (Class) obj2, str, null, null, null) : c.b.a.a.b.f(this.f1838c, obj, obj2.toString(), str, null, null, null);
    }

    public Object javax(Object obj, Object obj2, String str, Object[] objArr) {
        return obj2 instanceof Class ? c.b.a.a.b.e(this.f1838c, obj, (Class) obj2, str, objArr, null, null) : c.b.a.a.b.f(this.f1838c, obj, obj2.toString(), str, objArr, null, null);
    }

    public Object javax(Object obj, Object obj2, String str, Object[] objArr, Object obj3) {
        return obj2 instanceof Class ? c.b.a.a.b.e(this.f1838c, obj, (Class) obj2, str, objArr, null, obj3) : c.b.a.a.b.f(this.f1838c, obj, obj2.toString(), str, objArr, null, obj3);
    }

    public Object json(Object obj, String str) {
        if (str.equals("size")) {
            if (obj instanceof JSONObject) {
                return Integer.valueOf(((JSONObject) obj).length());
            }
            if (obj instanceof JSONArray) {
                return Integer.valueOf(((JSONArray) obj).length());
            }
            return -1;
        }
        if (!str.equals("json")) {
            return null;
        }
        if (obj instanceof JSONObject) {
            return ((JSONObject) obj).toString();
        }
        if (obj instanceof JSONArray) {
            return ((JSONArray) obj).toString();
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    public Object json(Object obj, String str, Object obj2) {
        if (str.equals("del")) {
            if (obj instanceof JSONObject) {
                ((JSONObject) obj).remove(String.valueOf(obj2));
                return null;
            }
            if (!(obj instanceof JSONArray) || Build.VERSION.SDK_INT < 19) {
                return null;
            }
            ((JSONArray) obj).remove(a(String.valueOf(obj2)));
            return null;
        }
        try {
            if (str.equals("get")) {
                if (obj instanceof JSONObject) {
                    return ((JSONObject) obj).get(String.valueOf(obj2));
                }
                if (obj instanceof JSONArray) {
                    return ((JSONArray) obj).get(a(String.valueOf(obj2)));
                }
                return null;
            }
            if (str.equals("list")) {
                if (obj instanceof JSONObject) {
                    return ((JSONObject) obj).getJSONArray(String.valueOf(obj2));
                }
                return null;
            }
            if (!str.equals("data")) {
                return null;
            }
            if (obj instanceof JSONObject) {
                return ((JSONObject) obj).getJSONObject(String.valueOf(obj2));
            }
            if (obj instanceof JSONArray) {
                return ((JSONArray) obj).getJSONObject(a(String.valueOf(obj2)));
            }
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public JSONObject json(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void json(Object obj, String str, Object obj2, Object obj3) {
        if (str.equals("set")) {
            try {
                if (obj instanceof JSONObject) {
                    ((JSONObject) obj).put(String.valueOf(obj2), obj3);
                } else if (obj instanceof JSONArray) {
                    ((JSONArray) obj).put(a(String.valueOf(obj2)), obj3);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void lan(int i2) {
        c.c.a.a.a(this.b, i2);
    }

    public ClassLoader loadjar(String str) {
        Context context = this.f1838c;
        return c.b.a.a.f.r(context, str, context.getClassLoader());
    }

    public ClassLoader loadjar(String str, boolean z2) {
        Context context = this.f1838c;
        DexClassLoader r2 = c.b.a.a.f.r(context, str, context.getClassLoader());
        if (z2) {
            c.b.a.a.f.u(this.f1838c, r2);
        }
        return r2;
    }

    public ClassLoader loadjar(String str, boolean z2, ClassLoader classLoader) {
        DexClassLoader r2 = c.b.a.a.f.r(this.f1838c, str, classLoader);
        if (z2) {
            c.b.a.a.f.u(this.f1838c, r2);
        }
        return r2;
    }

    public void loadso(String str) {
        c.b.a.a.f.s(str);
    }

    public Object ngde(int i2, int i3, String str, String str2) {
        int o2 = c.b.a.a.p.o(str);
        int o3 = c.b.a.a.p.o(str2);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(o2);
        if (i3 > 0) {
            gradientDrawable.setCornerRadius(i3);
        }
        if (i2 > 0) {
            gradientDrawable.setStroke(i2, o3);
        }
        return gradientDrawable;
    }

    public Object ngde(int i2, int i3, String str, String str2, String str3) {
        int o2 = c.b.a.a.p.o(str2);
        String[] C = C(str, '|');
        int[] iArr = new int[C.length];
        for (int i4 = 0; i4 < C.length; i4++) {
            iArr[i4] = c.b.a.a.p.o(C[i4]);
        }
        GradientDrawable gradientDrawable = new GradientDrawable(v(str3), iArr);
        if (i3 > 0) {
            gradientDrawable.setCornerRadius(i3);
        }
        if (i2 > 0) {
            gradientDrawable.setStroke(i2, o2);
        }
        return gradientDrawable;
    }

    public Object ngde(int i2, String str) {
        int o2 = c.b.a.a.p.o(str);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(o2);
        if (i2 > 0) {
            gradientDrawable.setCornerRadius(i2);
        }
        return gradientDrawable;
    }

    public Object ngde(int i2, String str, String str2) {
        int o2 = c.b.a.a.p.o(str);
        int o3 = c.b.a.a.p.o(str2);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(o2);
        if (i2 > 0) {
            gradientDrawable.setStroke(i2, o3);
        }
        return gradientDrawable;
    }

    public Object ngde(String str) {
        int o2 = c.b.a.a.p.o(str);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(o2);
        return gradientDrawable;
    }

    public Object nuibs(Object obj, Object obj2, Object obj3) {
        k(obj);
        k(obj2);
        k(obj3);
        return new c.c.a.c(this.f1838c).d(obj, obj2, obj3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0061  */
    /* JADX WARN: Type inference failed for: r1v0, types: [c.c.a.b] */
    /* JADX WARN: Type inference failed for: r7v3, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v2, types: [android.view.View, android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r8v9 */
    @android.annotation.TargetApi(16)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View nvw(int r7, java.lang.Object r8, java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_javaCode.nvw(int, java.lang.Object, java.lang.String, java.lang.String):android.view.View");
    }

    public void nvw(Object obj, Object obj2) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 == null) {
            return;
        }
        if (!(obj2 instanceof ViewGroup)) {
            k(obj2);
            obj2 = m(obj2, String.valueOf(obj2));
        }
        ViewGroup viewGroup = (ViewGroup) obj2;
        if (viewGroup == null) {
            return;
        }
        viewGroup.addView(m2);
    }

    public void nvw(Object obj, Object obj2, int i2) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 == null) {
            return;
        }
        if (!(obj2 instanceof ViewGroup)) {
            k(obj2);
            obj2 = m(obj2, String.valueOf(obj2));
        }
        ViewGroup viewGroup = (ViewGroup) obj2;
        if (viewGroup == null) {
            return;
        }
        viewGroup.addView(m2, i2);
    }

    public com.iapp.app.j openRestoreinterface(String str, ViewGroup viewGroup, int i2, Object obj) {
        com.iapp.app.j jVar = new com.iapp.app.j(this.f1838c);
        try {
            jVar.set("activity", this.f1838c);
            jVar.set("i", this);
        } catch (EvalError e2) {
            e2.printStackTrace();
        }
        StringBuffer stringBuffer = new StringBuffer();
        Context context = this.f1838c;
        c.c.a.b bVar = new c.c.a.b(context);
        bVar.a = 0;
        c.b.a.a.f.q(this, context, str, viewGroup, i2, obj, bVar, stringBuffer, jVar);
        String C = c.b.a.a.f.C(this.f1838c, str);
        if (C != null) {
            stringBuffer.append("private void loading(){\n" + C + "\n}\n");
        }
        jVar.d(stringBuffer.toString());
        if (C == null) {
            return null;
        }
        return jVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void openRestoreinterfaceX(java.lang.String r16, android.view.ViewGroup r17, int r18, java.lang.Object r19, c.c.a.b r20, java.lang.StringBuffer r21, com.iapp.app.j r22) {
        /*
            r15 = this;
            r0 = r16
            r1 = r18
            r2 = r20
            java.lang.String r3 = "id=\""
            java.lang.String r4 = "\""
            java.lang.String r3 = c.b.a.a.p.c(r0, r3, r4)
            r5 = -1
            int r3 = c.b.a.a.p.i(r3, r5)
            java.lang.String r6 = "did=\""
            java.lang.String r6 = c.b.a.a.p.c(r0, r6, r4)
            int r6 = c.b.a.a.p.i(r6, r5)
            java.lang.String r7 = "type=\""
            java.lang.String r4 = c.b.a.a.p.c(r0, r7, r4)
            java.lang.String r7 = "ppt"
            java.lang.String r7 = c.b.a.a.p.h(r0, r7)
            java.lang.String r8 = "event"
            java.lang.String r12 = c.b.a.a.p.h(r0, r8)
            if (r3 == r5) goto La8
            if (r6 != r5) goto L35
            goto La8
        L35:
            int r11 = r3 + r1
            int r6 = r6 + r1
            java.lang.String r0 = "ProgressBar"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L61
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r3 = "\n"
            r0.append(r3)
            r0.append(r7)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r5 = "\nstyle="
            java.lang.String r0 = c.b.a.a.p.c(r0, r5, r3)
            if (r0 == 0) goto L61
            android.view.View r0 = r2.f(r11, r4, r0)
            goto L65
        L61:
            android.view.View r0 = r2.e(r11, r4)
        L65:
            if (r0 != 0) goto L68
            return
        L68:
            r3 = r17
            if (r6 != r1) goto L6d
            goto L73
        L6d:
            android.view.View r3 = r3.findViewById(r6)
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3
        L73:
            android.view.ViewGroup$LayoutParams r5 = r2.d(r3, r0)
            r0.setLayoutParams(r5)
            com.iapp.app.i r5 = new com.iapp.app.i
            r6 = r15
            android.content.Context r8 = r6.f1838c
            r5.<init>(r0, r8, r1)
            boolean r1 = r2.a(r5, r7)
            if (r1 != 0) goto L89
            return
        L89:
            r1 = 4
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            r1[r2] = r4
            r2 = 1
            r1[r2] = r7
            r2 = 2
            r1[r2] = r12
            r2 = 3
            r1[r2] = r19
            r0.setTag(r1)
            r9 = r15
            r10 = r0
            r13 = r21
            r14 = r22
            r9.e(r10, r11, r12, r13, r14)
            r3.addView(r0)
            return
        La8:
            r6 = r15
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_javaCode.openRestoreinterfaceX(java.lang.String, android.view.ViewGroup, int, java.lang.Object, c.c.a.b, java.lang.StringBuffer, com.iapp.app.j):void");
    }

    public String otob(String str) {
        byte[] t2 = c.b.a.a.f.t(this.f1838c, str);
        if (t2 == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : t2) {
            stringBuffer.append(' ');
            stringBuffer.append((int) b2);
        }
        return stringBuffer.toString().trim();
    }

    public String otob(String str, String str2) {
        byte[] bArr;
        if (str == null) {
            bArr = str2.getBytes();
        } else {
            try {
                bArr = str2.getBytes(str);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                bArr = null;
            }
        }
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            stringBuffer.append(' ');
            stringBuffer.append((int) b2);
        }
        return stringBuffer.toString().trim();
    }

    public byte[] otob(String str, String str2, String str3) {
        if (str.equals("file")) {
            return c.b.a.a.f.t(this.f1838c, str3);
        }
        if (!str.equals("str")) {
            return null;
        }
        if (str2 == null) {
            return str3.getBytes();
        }
        try {
            return str3.getBytes(str2);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public com.iapp.app.k res() {
        return new com.iapp.app.k(this.f1838c);
    }

    public com.iapp.app.k res(String str) {
        return new com.iapp.app.k(this.f1838c, u(str));
    }

    public Object res(com.iapp.app.k kVar, String str) {
        if (str.equals("asset")) {
            return kVar.a();
        }
        if (str.equals("resources")) {
            return kVar.c();
        }
        return null;
    }

    public Object res(com.iapp.app.k kVar, String str, String str2) {
        return kVar.d(str, str2);
    }

    public Object res(com.iapp.app.k kVar, String str, String str2, boolean z2) {
        return z2 ? Integer.valueOf(kVar.b(str, str2)) : kVar.d(str, str2);
    }

    public void rps() {
        try {
            c.b.a.a.k.d(this.b, this.b.getPackageManager().getPackageInfo(this.b.getPackageName(), 4096).requestedPermissions);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void rps(Object obj) {
        try {
            if (!obj.getClass().isArray()) {
                c.b.a.a.k.d(this.b, new String[]{String.valueOf(obj)});
                return;
            }
            int length = Array.getLength(obj);
            String[] strArr = new String[length];
            for (int i2 = 0; i2 < length; i2++) {
                strArr[i2] = String.valueOf(Array.get(obj, i2));
            }
            c.b.a.a.k.d(this.b, strArr);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean rps(Object obj, Object obj2) {
        try {
            return ContextCompat.checkSelfPermission(this.b, String.valueOf(obj2)) != 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public Bitmap sbp(Object obj) {
        Bitmap v2 = obj instanceof Bitmap ? (Bitmap) obj : c.b.a.a.f.v(this.f1838c, String.valueOf(obj));
        if (v2 == null) {
            return null;
        }
        return v2;
    }

    public Bitmap sbp(Object obj, int i2, int i3, int i4, int i5) {
        Bitmap c2 = obj instanceof Bitmap ? (Bitmap) obj : c.b.a.a.i.c(u(String.valueOf(obj)));
        if (c2 == null) {
            return null;
        }
        return Bitmap.createBitmap(c2, i2, i3, i4, i5);
    }

    public Bitmap sbp(Object obj, int i2, int i3, int i4, int i5, float f2) {
        Bitmap c2 = obj instanceof Bitmap ? (Bitmap) obj : c.b.a.a.i.c(u(String.valueOf(obj)));
        if (c2 == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.preRotate(f2);
        return Bitmap.createBitmap(c2, i2, i3, i4, i5, matrix, true);
    }

    public void sdeg(int i2) {
        c.b.a.a.t.u = i2;
    }

    public Object se(Matcher matcher, String str) {
        if (str.equals("ms")) {
            return Boolean.valueOf(matcher.matches());
        }
        if (str.equals("find")) {
            return Boolean.valueOf(matcher.find());
        }
        if (str.equals("gl")) {
            return Integer.valueOf(matcher.groupCount());
        }
        if (str.equals("start")) {
            return Integer.valueOf(matcher.start());
        }
        if (str.equals("end")) {
            return Integer.valueOf(matcher.end());
        }
        if (str.equals("group")) {
            return matcher.group();
        }
        return null;
    }

    public Object se(Matcher matcher, String str, int i2) {
        if (str.equals("find")) {
            return Boolean.valueOf(matcher.find(i2));
        }
        if (str.equals("start")) {
            return Integer.valueOf(matcher.start(i2));
        }
        if (str.equals("end")) {
            return Integer.valueOf(matcher.end(i2));
        }
        if (str.equals("group")) {
            return matcher.group(i2);
        }
        return null;
    }

    public String se(Matcher matcher, String str, String str2) {
        if (str.equals("sral")) {
            return matcher.replaceAll(str2);
        }
        if (str.equals("srft")) {
            return matcher.replaceFirst(str2);
        }
        return null;
    }

    public Matcher se(String str, String str2, int i2) {
        return Pattern.compile(str2, i2).matcher(str);
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

    public String shb() {
        CharSequence text = ((ClipboardManager) this.f1838c.getSystemService("clipboard")).getText();
        if (text == null) {
            return null;
        }
        return text.toString();
    }

    public String simei() {
        return c.b.a.a.p.q(this.f1838c);
    }

    public String simsi() {
        try {
            return ((TelephonyManager) this.f1838c.getSystemService("phone")).getSubscriberId();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public int siof(String str, String str2) {
        return str.indexOf(str2);
    }

    public int siof(String str, String str2, int i2) {
        return str.indexOf(str2, i2);
    }

    public Intent sit(Intent intent, String str, String str2) {
        if (intent == null) {
            intent = new Intent();
        }
        if (str.equals("action")) {
            intent.setAction(str2);
        } else if (str.equals("type")) {
            intent.setType(str2);
        } else if (str.equals("flags")) {
            intent.setFlags(Integer.parseInt(str2));
        } else if (str.equals("data")) {
            intent.setData(c.b.a.a.k.c(this.f1838c, str2));
        }
        return intent;
    }

    public Intent sit(Intent intent, String str, String str2, Object obj) {
        if (intent == null) {
            intent = new Intent();
        }
        if (str.equals("extra")) {
            if (obj instanceof String[]) {
                intent.putExtra(str2, (String[]) obj);
            } else if (obj instanceof Boolean) {
                intent.putExtra(str2, obj.equals(Boolean.TRUE));
            } else if (obj instanceof Integer) {
                k(obj);
                intent.putExtra(str2, Integer.parseInt(String.valueOf(obj)));
            } else if (obj instanceof Long) {
                k(obj);
                intent.putExtra(str2, Long.parseLong(String.valueOf(obj)));
            } else {
                intent.putExtra(str2, String.valueOf(obj));
            }
        } else if (str.equals("classname")) {
            intent.setClassName(str2, String.valueOf(obj));
        } else if (str.equals("component")) {
            intent.setComponent(new ComponentName(str2, String.valueOf(obj)));
        }
        return intent;
    }

    public String sj(String str, String str2, String str3) {
        return c.b.a.a.p.c(str, str2, str3);
    }

    public String[] sjxx() {
        String[] o2 = o();
        int[] p2 = p();
        int[] q2 = q();
        String[] r2 = r();
        return new String[]{o2[0] + "\n" + o2[1], p2[0] + "\n" + p2[1] + "\n" + q2[0] + "\n" + q2[1], r2[0] + "\n" + r2[1] + "\n" + r2[2]};
    }

    public String[] sl(String str, String str2) {
        return c.b.a.a.q.e(str, str2);
    }

    public String[] sl(String str, String str2, boolean z2) {
        return z2 ? str.split(str2) : c.b.a.a.q.e(str, str2);
    }

    public int slg(String str) {
        return str.length();
    }

    public int slof(String str, String str2) {
        return str.lastIndexOf(str2);
    }

    public int slof(String str, String str2, int i2) {
        return str.lastIndexOf(str2, i2);
    }

    public String slower(String str) {
        return str.toLowerCase();
    }

    public o.c sot(c.b.a.a.o oVar, String str, int i2) {
        if (str.equals("list")) {
            return oVar.A(i2);
        }
        return null;
    }

    public c.b.a.a.o sot(int i2, String str, int i3, int i4, boolean z2, Object obj) {
        c.b.a.a.o oVar = new c.b.a.a.o(i2, str, i3, i4, z2);
        oVar.F((OnMessagesListener) obj);
        return oVar;
    }

    public c.b.a.a.o sot(String str, int i2, int i3, boolean z2, Object obj) {
        c.b.a.a.o oVar = new c.b.a.a.o(str, i2, i3, z2);
        oVar.F((OnMessagesListener) obj);
        return oVar;
    }

    public Object sot(c.b.a.a.o oVar, String str) {
        if (str.equals("ip")) {
            return Boolean.valueOf(oVar.B());
        }
        if (str.equals("id")) {
            return Integer.valueOf(oVar.t());
        }
        if (str.equals("list")) {
            return oVar.w();
        }
        if (str.equals("size")) {
            return Integer.valueOf(oVar.u());
        }
        if (str.equals("server")) {
            return oVar.v();
        }
        if (!str.equals("re")) {
            return null;
        }
        oVar.s();
        return null;
    }

    public void sot(c.b.a.a.o oVar, String str, String str2) {
        if (str.equals("str")) {
            oVar.b(str2);
            return;
        }
        if (str.equals("file")) {
            oVar.a(new File(u(str2)));
        } else if (str.equals("bt")) {
            oVar.c(i(str2, ' '));
        } else if (str.equals("new")) {
            oVar.f1351c = str2.equals("true");
        }
    }

    public void sot(c.b.a.a.o oVar, String str, byte[] bArr) {
        if (str.equals("bt2")) {
            oVar.d(bArr);
        }
    }

    public Object sota(o.c cVar, String str) {
        if (str.equals("re")) {
            cVar.g();
            return null;
        }
        if (str.equals("ip")) {
            return Boolean.valueOf(cVar.k());
        }
        if (str.equals("id")) {
            return Integer.valueOf(cVar.i());
        }
        if (str.equals("socket")) {
            return cVar.j();
        }
        if (str.equals("ht")) {
            return cVar.h();
        }
        return null;
    }

    public void sota(o.c cVar, String str, String str2) {
        if (str.equals("str")) {
            cVar.b(str2);
        } else if (str.equals("file")) {
            cVar.a(new File(u(str2)));
        } else if (str.equals("bt")) {
            cVar.c(i(str2, ' '));
        }
    }

    public void sota(o.c cVar, String str, byte[] bArr) {
        if (str.equals("bt2")) {
            cVar.d(bArr);
        }
    }

    public Cursor sql(SQLiteDatabase sQLiteDatabase, String str) {
        if (sQLiteDatabase != null) {
            try {
                return sQLiteDatabase.rawQuery(str, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public Object sql(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) {
        boolean j2;
        if (sQLiteDatabase == null) {
            return null;
        }
        if (str2.equals("add")) {
            j2 = c.b.a.a.n.a(sQLiteDatabase, str, str3, str4);
        } else {
            if (!str2.equals("up")) {
                if (str2.equals("sele")) {
                    return c.b.a.a.n.h(sQLiteDatabase, str3, str, str4);
                }
                return null;
            }
            j2 = c.b.a.a.n.j(sQLiteDatabase, str, str3, str4);
        }
        return Boolean.valueOf(j2);
    }

    public void sql(String str, SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            sQLiteDatabase.execSQL(str);
        }
    }

    public boolean sql(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        if (sQLiteDatabase == null) {
            return false;
        }
        if (str2.equals("ip")) {
            return c.b.a.a.n.i(sQLiteDatabase, str);
        }
        if (str2.equals("del")) {
            return c.b.a.a.n.e(sQLiteDatabase, str);
        }
        return false;
    }

    public boolean sql(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        if (sQLiteDatabase == null) {
            return false;
        }
        if (str2.equals("add")) {
            return c.b.a.a.n.b(sQLiteDatabase, str, str3);
        }
        if (str2.equals("del")) {
            return c.b.a.a.n.d(sQLiteDatabase, str, str3);
        }
        return false;
    }

    public SQLiteDatabase sqlite(SQLiteDatabase sQLiteDatabase, String str) {
        Context context;
        boolean z2;
        if (str.equals("re")) {
            if (sQLiteDatabase == null) {
                return null;
            }
            sQLiteDatabase.close();
            return null;
        }
        if (str.contains("/") || str.contains("\\") || str.startsWith("@") || str.startsWith("$") || str.startsWith("%")) {
            context = this.f1838c;
            str = u(str);
            z2 = false;
        } else {
            context = this.f1838c;
            z2 = true;
        }
        return c.b.a.a.f.z(context, str, z2);
    }

    public boolean sqlite(String str, String str2) {
        if (str2.equals("ip")) {
            return (str.contains("/") || str.contains("\\") || str.startsWith("@") || str.startsWith("$") || str.startsWith("%")) ? c.b.a.a.f.y(this.f1838c, u(str), false) : c.b.a.a.f.y(this.f1838c, str, true);
        }
        if (str2.equals("del")) {
            return (str.contains("/") || str.contains("\\") || str.startsWith("@") || str.startsWith("$") || str.startsWith("%")) ? c.b.a.a.f.x(this.f1838c, u(str), false) : c.b.a.a.f.x(this.f1838c, str, true);
        }
        return false;
    }

    public Object sqlsele(Cursor cursor, Object obj) {
        k(obj);
        if (obj instanceof Integer) {
            return cursor.getString(Integer.parseInt(obj.toString()));
        }
        if (!obj.equals("re")) {
            return obj.equals("columncount") ? Integer.valueOf(cursor.getColumnCount()) : obj.equals("count") ? Integer.valueOf(cursor.getCount()) : obj.equals("next") ? Boolean.valueOf(cursor.moveToNext()) : obj.equals("previous") ? Boolean.valueOf(cursor.moveToPrevious()) : obj.equals("first") ? Boolean.valueOf(cursor.moveToFirst()) : obj.equals("last") ? Boolean.valueOf(cursor.moveToLast()) : obj.equals("getposition") ? Integer.valueOf(cursor.getPosition()) : cursor.getString(a(obj.toString()));
        }
        if (cursor == null) {
            return null;
        }
        cursor.close();
        return null;
    }

    public void sqlsele(Cursor cursor, String str, int i2) {
        if (str.equals("position")) {
            cursor.moveToPosition(i2);
        }
    }

    public String sr(String str, String str2, String str3) {
        return str.replace(str2, str3);
    }

    public String sr(String str, String str2, String str3, boolean z2) {
        return z2 ? str.replaceAll(str2, str3) : str.replace(str2, str3);
    }

    public int sran(int i2, int i3) {
        return c.b.a.a.p.g(i2, i3);
    }

    public void src(String str, Object obj) {
        try {
            this.f1839d.set(str, obj);
        } catch (EvalError e2) {
            e2.printStackTrace();
        }
    }

    public Object ss(String str) {
        return ss_dim.get(str);
    }

    public void ss(String str, Object obj) {
        ss_dim.put(str, obj);
    }

    public void ssDouble(String str, double d2) {
        ss_dim.put(str, Double.valueOf(d2));
    }

    public void ssLong(String str, long j2) {
        ss_dim.put(str, Long.valueOf(j2));
    }

    public String ssg(String str, int i2) {
        return str.substring(i2);
    }

    public String ssg(String str, int i2, int i3) {
        return str.substring(i2, i3);
    }

    public void ssj(Object obj, String str, Object obj2) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 != null) {
            f(m2, str, obj2);
        }
    }

    public Object sslist(ArrayList<Object> arrayList, int i2, Object obj) {
        return arrayList.set(i2, obj);
    }

    public Object sss(String str) {
        return c.b.a.a.t.j.get(str);
    }

    public void sss(String str, Object obj) {
        c.b.a.a.t.j.put(str, obj);
    }

    public void sssDouble(String str, double d2) {
        c.b.a.a.t.j.put(str, Double.valueOf(d2));
    }

    public void sssLong(String str, long j2) {
        c.b.a.a.t.j.put(str, Long.valueOf(j2));
    }

    public String stobm(String str, String str2) {
        return c.b.a.a.p.B(str, str2);
    }

    public void stop(long j2) {
        try {
            Thread.sleep(j2);
        } catch (InterruptedException unused) {
        }
    }

    public String strim(String str) {
        return str.trim();
    }

    public String supper(String str) {
        return str.toUpperCase();
    }

    public String sutf8to(String str) {
        return c.b.a.a.p.D(str);
    }

    public int swh(String str) {
        if (str.equals("w")) {
            this.b.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            return c.b.a.a.p.w(this.f1838c, r4.widthPixels);
        }
        if (str.equals("h")) {
            WindowManager.LayoutParams attributes = this.b.getWindow().getAttributes();
            this.b.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            return (attributes.flags & 1024) == 1024 ? c.b.a.a.p.w(this.f1838c, r0.heightPixels) : c.b.a.a.p.w(this.f1838c, r0.heightPixels - s());
        }
        if (str.equals("pxw")) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.b.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        }
        if (str.equals("pxh")) {
            WindowManager.LayoutParams attributes2 = this.b.getWindow().getAttributes();
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            this.b.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics2);
            return (attributes2.flags & 1024) == 1024 ? displayMetrics2.heightPixels : displayMetrics2.heightPixels - s();
        }
        if (str.equals("pxhh")) {
            DisplayMetrics displayMetrics3 = new DisplayMetrics();
            this.b.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics3);
            return displayMetrics3.heightPixels;
        }
        if (str.equals("hh")) {
            this.b.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            return c.b.a.a.p.w(this.f1838c, r4.heightPixels);
        }
        if (str.equals("pxztl")) {
            return t();
        }
        if (str.equals("pxbvk")) {
            return n();
        }
        if (str.equals("ztl")) {
            return c.b.a.a.p.w(this.f1838c, t());
        }
        if (str.equals("bvk")) {
            return c.b.a.a.p.w(this.f1838c, n());
        }
        return 0;
    }

    public void sxb(String str) {
        ((ClipboardManager) this.f1838c.getSystemService("clipboard")).setText(str);
    }

    public void syso(Object obj) {
        c.b.a.a.t.P2(this.f1838c, obj);
    }

    public void t(OnThread onThread) {
        new e0(this, onThread);
    }

    public int tcc(Object obj, String str) {
        if (!(obj instanceof Bitmap)) {
            return -1;
        }
        if (str.equals("w")) {
            return ((Bitmap) obj).getWidth();
        }
        if (str.equals("h")) {
            return ((Bitmap) obj).getHeight();
        }
        return -1;
    }

    public Bitmap tfz(Object obj, String str) {
        Bitmap bitmap;
        int i2;
        if (!(obj instanceof Bitmap)) {
            return null;
        }
        if (str.equals("x")) {
            bitmap = (Bitmap) obj;
            i2 = 0;
        } else {
            if (!str.equals("y")) {
                return null;
            }
            bitmap = (Bitmap) obj;
            i2 = 1;
        }
        return c.b.a.a.i.f(bitmap, i2);
    }

    public Object time(String str) {
        Formatter format;
        try {
            return c.b.a.a.p.u(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(new Date().getTime());
            Formatter formatter = new Formatter(Locale.CHINA);
            if (str.length() == 1) {
                format = formatter.format("%1$t" + str, calendar);
            } else {
                format = formatter.format(str, calendar);
            }
            return format.toString();
        }
    }

    public Bitmap tot(Object obj) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 != null && (m2 instanceof ImageView)) {
            return c.b.a.a.i.a((ImageView) m2);
        }
        return null;
    }

    public Bitmap tsf(Object obj, float f2) {
        if (obj instanceof Bitmap) {
            return c.b.a.a.i.d((Bitmap) obj, f2);
        }
        return null;
    }

    public Bitmap tsf(Object obj, int i2, int i3) {
        if (obj instanceof Bitmap) {
            return c.b.a.a.i.e((Bitmap) obj, i2, i3);
        }
        return null;
    }

    public com.iapp.app.r tts() {
        return new com.iapp.app.r(this.f1838c);
    }

    public com.iapp.app.r tts(String str, String str2, float f2, float f3) {
        return new com.iapp.app.r(this.f1838c, str, str2, f2, f3);
    }

    public Object tts(com.iapp.app.r rVar, String str) {
        if (str.equals("re")) {
            rVar.h();
        } else {
            if (str.equals("sp")) {
                return Boolean.valueOf(rVar.j());
            }
            if (str.equals("ip")) {
                return Boolean.valueOf(rVar.d());
            }
            if (str.equals("is")) {
                return Boolean.TRUE;
            }
            if (str.equals("zt")) {
                return Integer.valueOf(rVar.c());
            }
        }
        return Boolean.FALSE;
    }

    public void tts(com.iapp.app.r rVar, String str, Object obj) {
        if (str.equals("ph")) {
            rVar.f(Float.parseFloat(obj.toString()));
            return;
        }
        if (str.equals("se")) {
            rVar.g(Float.parseFloat(obj.toString()));
            return;
        }
        if (str.equals("lg")) {
            rVar.e(obj.toString());
            return;
        }
        if (str.equals("st")) {
            k(obj);
            rVar.i(str, Integer.parseInt(obj.toString()));
        } else if (str.equals("ft")) {
            rVar.k(str, u(obj.toString()));
        }
    }

    public void tw(Object obj) {
        Toast.makeText(this.f1838c, String.valueOf(obj), 1).show();
    }

    public void tw(Object obj, int i2) {
        Toast.makeText(this.f1838c, String.valueOf(obj), i2).show();
    }

    public void tws(Object obj, String str, int i2) {
        View m2;
        if (obj == null) {
            m2 = this.b.findViewById(c.b.a.a.f.a);
        } else if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 != null) {
            Snackbar.make(m2, str, i2).show();
        }
    }

    public void tws(Object obj, String str, int i2, String str2, Object obj2) {
        View m2;
        if (obj == null) {
            m2 = this.b.findViewById(c.b.a.a.f.a);
        } else if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 != null) {
            Snackbar.make(m2, str, i2).setAction(str2, (View.OnClickListener) obj2).show();
        }
    }

    public Bitmap tzz(Object obj, float f2) {
        if (obj instanceof Bitmap) {
            return c.b.a.a.i.g((Bitmap) obj, f2);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Integer] */
    public Object[] uall(Object obj, boolean z2) {
        if (!(obj instanceof ViewGroup)) {
            k(obj);
            obj = m(obj, String.valueOf(obj));
        }
        ViewGroup viewGroup = (ViewGroup) obj;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            ?? childAt = viewGroup.getChildAt(i2);
            if (!z2) {
                childAt = Integer.valueOf(childAt.getId());
            }
            arrayList.add(childAt);
        }
        return arrayList.toArray();
    }

    public boolean uapp(String str) {
        return c.b.a.a.d.s(this.f1838c, str);
    }

    public boolean uapp(String str, String str2) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(str, str2));
            this.f1838c.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public Object[] uapplist(boolean z2) {
        ArrayList arrayList = new ArrayList();
        for (PackageInfo packageInfo : this.f1838c.getPackageManager().getInstalledPackages(0)) {
            String str = packageInfo.packageName;
            if (z2) {
                arrayList.add(new String[]{str, w(str), packageInfo.applicationInfo.loadLabel(this.f1838c.getPackageManager()).toString(), packageInfo.versionName});
            } else if ((packageInfo.applicationInfo.flags & 1) == 0) {
                arrayList.add(new String[]{str, w(str), packageInfo.applicationInfo.loadLabel(this.f1838c.getPackageManager()).toString(), packageInfo.versionName});
            }
        }
        return arrayList.toArray();
    }

    public void ucall(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DIAL");
        intent.setData(c.b.a.a.k.c(this.f1838c, "tel:" + str));
        this.f1838c.startActivity(intent);
    }

    public void ufnsui(Object obj) {
        Message message = new Message();
        message.what = 2;
        message.obj = obj;
        this.e.sendMessage(message);
    }

    public Object ug(Object obj, String str) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 == null) {
            return null;
        }
        return new com.iapp.app.i(m2, this.f1838c).e(str);
    }

    public Object ug(Object obj, String str, Object obj2) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 == null) {
            return null;
        }
        return new com.iapp.app.i(m2, this.f1838c).f(str, obj2);
    }

    public Object uht(Object obj, String str) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        boolean z2 = m2 instanceof ViewPager;
        if (z2 || (m2 instanceof VerticalViewPager)) {
            PagerAdapter adapter = z2 ? ((ViewPager) m2).getAdapter() : m2 instanceof VerticalViewPager ? ((VerticalViewPager) m2).getAdapter() : null;
            if (str.equals("size")) {
                if (adapter instanceof FragmentStatePagerAdapter) {
                    return Integer.valueOf(((com.iapp.app.h) adapter).g());
                }
            } else if (str.equals("close") && (adapter instanceof FragmentStatePagerAdapter)) {
                ((com.iapp.app.h) adapter).c();
            }
        }
        return null;
    }

    public Object uht(Object obj, String str, int i2) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        boolean z2 = m2 instanceof ViewPager;
        if (z2 || (m2 instanceof VerticalViewPager)) {
            PagerAdapter adapter = z2 ? ((ViewPager) m2).getAdapter() : m2 instanceof VerticalViewPager ? ((VerticalViewPager) m2).getAdapter() : null;
            if (str.equals("del") && (adapter instanceof FragmentStatePagerAdapter)) {
                ((com.iapp.app.h) adapter).d(i2);
            }
        }
        return null;
    }

    public Object uht(Object obj, String str, int i2, String str2, String str3, Object[] objArr, Object[] objArr2) {
        View m2;
        com.iapp.app.h hVar;
        boolean z2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        boolean z3 = m2 instanceof ViewPager;
        if (z3 || (m2 instanceof VerticalViewPager)) {
            PagerAdapter adapter = z3 ? ((ViewPager) m2).getAdapter() : m2 instanceof VerticalViewPager ? ((VerticalViewPager) m2).getAdapter() : null;
            if (str.equals("add")) {
                int length = objArr.length;
                HashMap<Integer, Object> hashMap = new HashMap<>();
                for (int i3 = 0; i3 < length; i3++) {
                    hashMap.put(Integer.valueOf((int) Double.parseDouble(objArr[i3].toString())), objArr2[i3]);
                }
                if (adapter == null || !((z2 = adapter instanceof FragmentStatePagerAdapter))) {
                    hVar = new com.iapp.app.h(this.b, m2.getId(), ((AppCompatActivity) this.b).getSupportFragmentManager());
                    if (z3) {
                        ViewPager viewPager = (ViewPager) m2;
                        viewPager.setAdapter(hVar);
                        viewPager.setOffscreenPageLimit(5);
                    } else if (m2 instanceof VerticalViewPager) {
                        VerticalViewPager verticalViewPager = (VerticalViewPager) m2;
                        verticalViewPager.setAdapter(hVar);
                        verticalViewPager.setOffscreenPageLimit(5);
                    }
                } else {
                    if (!z2) {
                        return null;
                    }
                    hVar = (com.iapp.app.h) adapter;
                }
                for (String str4 : c.b.a.a.q.b(str3, '|')) {
                    String trim = str4.trim();
                    if (trim.endsWith(".ijava")) {
                        hVar.b(i2, str2, trim, h(trim.substring(0, trim.length() - 4)), hashMap);
                    }
                }
            }
        }
        return null;
    }

    public Object uht(Object obj, String str, Object obj2, Object obj3) {
        View m2;
        View m3;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        boolean z2 = m2 instanceof ViewPager;
        if (z2 || (m2 instanceof VerticalViewPager)) {
            if (str.equals("bd")) {
                if (z2) {
                    if (obj2 instanceof View) {
                        m3 = (View) obj2;
                    } else {
                        k(obj2);
                        m3 = m(obj2, String.valueOf(obj2));
                    }
                    if (m3 instanceof TabLayout) {
                        ((TabLayout) m3).setupWithViewPager((ViewPager) m2, obj3.equals(Boolean.TRUE));
                    }
                }
                return null;
            }
            PagerAdapter adapter = z2 ? ((ViewPager) m2).getAdapter() : m2 instanceof VerticalViewPager ? ((VerticalViewPager) m2).getAdapter() : null;
            if (str.equals("title") && (adapter instanceof FragmentStatePagerAdapter)) {
                ((com.iapp.app.h) adapter).f(a(String.valueOf(obj2)), String.valueOf(obj3));
            }
        }
        return null;
    }

    public void uigo(String str) {
        Intent intent;
        if (str.endsWith(".iyu")) {
            intent = new Intent(this.f1838c, (Class<?>) mian.class);
        } else if (str.endsWith(".ijava")) {
            intent = new Intent(this.f1838c, (Class<?>) main3.class);
        } else if (str.endsWith(".ilua")) {
            intent = new Intent(this.f1838c, (Class<?>) main.class);
        } else if (!str.endsWith(".ijs")) {
            return;
        } else {
            intent = new Intent(this.f1838c, (Class<?>) main2.class);
        }
        intent.putExtras(c.b.a.a.f.B(str));
        this.f1838c.startActivity(intent);
    }

    public void uigo(String str, int i2) {
        Intent intent;
        if (str.endsWith(".iyu")) {
            intent = new Intent(this.f1838c, (Class<?>) mian.class);
        } else if (str.endsWith(".ijava")) {
            intent = new Intent(this.f1838c, (Class<?>) main3.class);
        } else if (str.endsWith(".ilua")) {
            intent = new Intent(this.f1838c, (Class<?>) main.class);
        } else if (!str.endsWith(".ijs")) {
            return;
        } else {
            intent = new Intent(this.f1838c, (Class<?>) main2.class);
        }
        intent.putExtras(c.b.a.a.f.B(str));
        intent.setFlags(i2);
        this.f1838c.startActivity(intent);
    }

    public void uit(Intent intent) {
        this.f1838c.startActivity(intent);
    }

    public void uit(Intent intent, String str, String str2) {
        Context context;
        if (str.equals("chooser")) {
            context = this.f1838c;
            intent = Intent.createChooser(intent, str2);
        } else {
            if (str.equals("result")) {
                this.b.startActivityForResult(intent, Integer.parseInt(str2));
                return;
            }
            context = this.f1838c;
        }
        context.startActivity(intent);
    }

    public void ujp(String str, int i2) {
        c.b.a.a.r.a(this.b, u(str), i2);
    }

    public Object ula(Object obj, Object obj2) {
        Object obj3;
        ArrayList<HashMap<Integer, Object>> a2;
        Object obj4;
        k kVar = null;
        if (obj2 == null || obj2.equals("clear")) {
            if ((obj instanceof b0) && (obj3 = ((b0) obj).b) != null) {
                if (obj3 instanceof c0) {
                    a2 = ((c0) obj3).a();
                } else if (obj3 instanceof d0) {
                    a2 = ((d0) obj3).a();
                }
                a2.clear();
            }
        } else {
            if (obj2.equals("list")) {
                return ((obj == null || !(obj instanceof b0)) ? new b0(this, kVar) : (b0) obj).a;
            }
            if ((obj instanceof b0) && (obj2 instanceof Number) && (obj4 = ((b0) obj).b) != null && (obj4 instanceof d0)) {
                ((d0) obj4).notifyItemChanged(((Number) obj2).intValue());
            }
        }
        return null;
    }

    public Object ula(Object obj, Object[] objArr, Object[] objArr2) {
        int length = objArr.length;
        b0 b0Var = (obj == null || !(obj instanceof b0)) ? new b0(this, null) : (b0) obj;
        HashMap<Integer, Object> hashMap = new HashMap<>();
        for (int i2 = 0; i2 < length; i2++) {
            hashMap.put(Integer.valueOf((int) Double.parseDouble(objArr[i2].toString())), objArr2[i2]);
        }
        b0Var.a.add(hashMap);
        return b0Var;
    }

    public void ula(Object obj) {
        Object obj2;
        if (!(obj instanceof b0) || (obj2 = ((b0) obj).b) == null) {
            return;
        }
        if (obj2 instanceof c0) {
            ((c0) obj2).notifyDataSetChanged();
        } else if (obj2 instanceof ArrayAdapter) {
            ((ArrayAdapter) obj2).notifyDataSetChanged();
        } else if (obj2 instanceof d0) {
            ((d0) obj2).notifyDataSetChanged();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00b4, code lost:
    
        if ((r5 instanceof com.iapp.app.Aid_javaCode.b0) != false) goto L39;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object ulag(java.lang.Object r5, int r6, java.lang.Object r7) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof c.b.a.a.e
            if (r0 == 0) goto L18
            r4.k(r7)
            c.b.a.a.e r5 = (c.b.a.a.e) r5
            java.util.ArrayList r5 = r5.q()
            java.lang.Object r5 = r5.get(r6)
            java.util.HashMap r5 = (java.util.HashMap) r5
            java.lang.Object r5 = r5.get(r7)
            return r5
        L18:
            boolean r0 = r5 instanceof java.util.ArrayList
            if (r0 == 0) goto L38
            r4.k(r7)
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            java.lang.Object r5 = r5.get(r6)
            java.util.HashMap r5 = (java.util.HashMap) r5
            java.lang.String r6 = java.lang.String.valueOf(r7)
            int r6 = java.lang.Integer.parseInt(r6)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r5 = r5.get(r6)
            return r5
        L38:
            boolean r0 = r5 instanceof android.view.View
            r1 = 0
            if (r0 == 0) goto L87
            r0 = r5
            android.view.View r0 = (android.view.View) r0
            boolean r2 = r0 instanceof androidx.viewpager.widget.ViewPager
            if (r2 == 0) goto L56
            r2 = r0
            androidx.viewpager.widget.ViewPager r2 = (androidx.viewpager.widget.ViewPager) r2
            androidx.viewpager.widget.PagerAdapter r3 = r2.getAdapter()
            boolean r3 = r3 instanceof com.iapp.app.h
            if (r3 == 0) goto L56
            androidx.viewpager.widget.PagerAdapter r0 = r2.getAdapter()
        L53:
            com.iapp.app.h r0 = (com.iapp.app.h) r0
            goto L6a
        L56:
            boolean r2 = r0 instanceof fr.castorflex.android.verticalviewpager.VerticalViewPager
            if (r2 == 0) goto L69
            fr.castorflex.android.verticalviewpager.VerticalViewPager r0 = (fr.castorflex.android.verticalviewpager.VerticalViewPager) r0
            androidx.viewpager.widget.PagerAdapter r2 = r0.getAdapter()
            boolean r2 = r2 instanceof com.iapp.app.h
            if (r2 == 0) goto L69
            androidx.viewpager.widget.PagerAdapter r0 = r0.getAdapter()
            goto L53
        L69:
            r0 = r1
        L6a:
            if (r0 == 0) goto L87
            java.util.ArrayList r5 = r0.e()
            java.lang.Object r5 = r5.get(r6)
            java.util.HashMap r5 = (java.util.HashMap) r5
            java.lang.String r6 = java.lang.String.valueOf(r7)
            int r6 = r4.a(r6)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r5 = r5.get(r6)
            return r5
        L87:
            boolean r0 = r5 instanceof android.widget.AbsListView
            r2 = 3
            if (r0 == 0) goto L9d
            android.widget.AbsListView r5 = (android.widget.AbsListView) r5
            java.lang.Object r5 = r5.getTag()
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            r0 = r5[r2]
            boolean r0 = r0 instanceof com.iapp.app.Aid_javaCode.b0
            if (r0 == 0) goto Lb9
            r5 = r5[r2]
            goto Lb6
        L9d:
            boolean r0 = r5 instanceof androidx.recyclerview.widget.RecyclerView
            if (r0 == 0) goto Lb2
            androidx.recyclerview.widget.RecyclerView r5 = (androidx.recyclerview.widget.RecyclerView) r5
            java.lang.Object r5 = r5.getTag()
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            r0 = r5[r2]
            boolean r0 = r0 instanceof com.iapp.app.Aid_javaCode.b0
            if (r0 == 0) goto Lb9
            r5 = r5[r2]
            goto Lb6
        Lb2:
            boolean r0 = r5 instanceof com.iapp.app.Aid_javaCode.b0
            if (r0 == 0) goto Lb9
        Lb6:
            com.iapp.app.Aid_javaCode$b0 r5 = (com.iapp.app.Aid_javaCode.b0) r5
            goto Lba
        Lb9:
            r5 = r1
        Lba:
            if (r5 != 0) goto Lbd
            return r1
        Lbd:
            r4.k(r7)
            java.util.ArrayList<java.util.HashMap<java.lang.Integer, java.lang.Object>> r5 = r5.a
            java.lang.Object r5 = r5.get(r6)
            java.util.HashMap r5 = (java.util.HashMap) r5
            java.lang.String r6 = java.lang.String.valueOf(r7)
            int r6 = java.lang.Integer.parseInt(r6)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r5 = r5.get(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_javaCode.ulag(java.lang.Object, int, java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object ulag(java.lang.Object r4, java.lang.Object r5) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof android.view.View
            r1 = 0
            if (r0 == 0) goto L6a
            android.view.View r4 = (android.view.View) r4
            boolean r0 = r4 instanceof androidx.viewpager.widget.ViewPager
            if (r0 == 0) goto L1d
            r0 = r4
            androidx.viewpager.widget.ViewPager r0 = (androidx.viewpager.widget.ViewPager) r0
            androidx.viewpager.widget.PagerAdapter r2 = r0.getAdapter()
            boolean r2 = r2 instanceof com.iapp.app.h
            if (r2 == 0) goto L1d
            androidx.viewpager.widget.PagerAdapter r0 = r0.getAdapter()
        L1a:
            com.iapp.app.h r0 = (com.iapp.app.h) r0
            goto L32
        L1d:
            boolean r0 = r4 instanceof fr.castorflex.android.verticalviewpager.VerticalViewPager
            if (r0 == 0) goto L31
            r0 = r4
            fr.castorflex.android.verticalviewpager.VerticalViewPager r0 = (fr.castorflex.android.verticalviewpager.VerticalViewPager) r0
            androidx.viewpager.widget.PagerAdapter r2 = r0.getAdapter()
            boolean r2 = r2 instanceof com.iapp.app.h
            if (r2 == 0) goto L31
            androidx.viewpager.widget.PagerAdapter r0 = r0.getAdapter()
            goto L1a
        L31:
            r0 = r1
        L32:
            if (r0 == 0) goto L45
            java.util.ArrayList r4 = r0.e()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r5 = r3.a(r5)
            java.lang.Object r4 = r4.get(r5)
            return r4
        L45:
            java.lang.Object r4 = r4.getTag()
            java.lang.Object[] r4 = (java.lang.Object[]) r4
            r0 = 3
            r2 = r4[r0]
            boolean r2 = r2 instanceof java.util.HashMap
            if (r2 == 0) goto L8e
            r3.k(r5)
            r4 = r4[r0]
            java.util.HashMap r4 = (java.util.HashMap) r4
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r5 = java.lang.Integer.parseInt(r5)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r4 = r4.get(r5)
            return r4
        L6a:
            boolean r0 = r4 instanceof java.util.HashMap
            if (r0 == 0) goto L78
            r3.k(r5)
            java.util.HashMap r4 = (java.util.HashMap) r4
            java.lang.Object r4 = r4.get(r5)
            return r4
        L78:
            boolean r0 = r4 instanceof java.util.ArrayList
            if (r0 == 0) goto L8e
            r3.k(r5)
            java.util.ArrayList r4 = (java.util.ArrayList) r4
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r5 = java.lang.Integer.parseInt(r5)
            java.lang.Object r4 = r4.get(r5)
            return r4
        L8e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_javaCode.ulag(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void ulas(java.lang.Object r5, int r6, int r7, java.lang.Object r8) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof android.view.View
            r1 = 0
            if (r0 == 0) goto L46
            r0 = r5
            android.view.View r0 = (android.view.View) r0
            boolean r2 = r0 instanceof androidx.viewpager.widget.ViewPager
            if (r2 == 0) goto L1e
            r2 = r0
            androidx.viewpager.widget.ViewPager r2 = (androidx.viewpager.widget.ViewPager) r2
            androidx.viewpager.widget.PagerAdapter r3 = r2.getAdapter()
            boolean r3 = r3 instanceof com.iapp.app.h
            if (r3 == 0) goto L1e
            androidx.viewpager.widget.PagerAdapter r0 = r2.getAdapter()
        L1b:
            com.iapp.app.h r0 = (com.iapp.app.h) r0
            goto L32
        L1e:
            boolean r2 = r0 instanceof fr.castorflex.android.verticalviewpager.VerticalViewPager
            if (r2 == 0) goto L31
            fr.castorflex.android.verticalviewpager.VerticalViewPager r0 = (fr.castorflex.android.verticalviewpager.VerticalViewPager) r0
            androidx.viewpager.widget.PagerAdapter r2 = r0.getAdapter()
            boolean r2 = r2 instanceof com.iapp.app.h
            if (r2 == 0) goto L31
            androidx.viewpager.widget.PagerAdapter r0 = r0.getAdapter()
            goto L1b
        L31:
            r0 = r1
        L32:
            if (r0 == 0) goto L46
            java.util.ArrayList r5 = r0.e()
        L38:
            java.lang.Object r5 = r5.get(r6)
            java.util.HashMap r5 = (java.util.HashMap) r5
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)
            r5.put(r6, r8)
            return
        L46:
            boolean r0 = r5 instanceof android.widget.AbsListView
            if (r0 == 0) goto L5f
            android.widget.AbsListView r5 = (android.widget.AbsListView) r5
            java.lang.Object r5 = r5.getTag()
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            r0 = 3
            r2 = r5[r0]
            boolean r2 = r2 instanceof com.iapp.app.Aid_javaCode.b0
            if (r2 == 0) goto L66
            r5 = r5[r0]
            com.iapp.app.Aid_javaCode$b0 r5 = (com.iapp.app.Aid_javaCode.b0) r5
            r1 = r5
            goto L66
        L5f:
            boolean r0 = r5 instanceof com.iapp.app.Aid_javaCode.b0
            if (r0 == 0) goto L66
            r1 = r5
            com.iapp.app.Aid_javaCode$b0 r1 = (com.iapp.app.Aid_javaCode.b0) r1
        L66:
            if (r1 != 0) goto L69
            return
        L69:
            java.util.ArrayList<java.util.HashMap<java.lang.Integer, java.lang.Object>> r5 = r1.a
            goto L38
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_javaCode.ulas(java.lang.Object, int, int, java.lang.Object):void");
    }

    public void ulas(Object obj, int i2, Object obj2) {
        if (obj instanceof View) {
            Object[] objArr = (Object[]) ((View) obj).getTag();
            if (objArr[3] instanceof HashMap) {
                ((HashMap) objArr[3]).put(Integer.valueOf(i2), obj2);
            }
        }
    }

    public void uls(Object obj, Object obj2) {
        View m2;
        ArrayAdapter arrayAdapter;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 != null && (m2 instanceof Spinner)) {
            Spinner spinner = (Spinner) m2;
            if (obj2 instanceof ArrayList) {
                arrayAdapter = new ArrayAdapter(this.f1838c, R.layout.simple_spinner_item, (ArrayList) obj2);
            } else if (obj2 instanceof String[]) {
                arrayAdapter = new ArrayAdapter(this.f1838c, R.layout.simple_spinner_item, (String[]) obj2);
            } else if (!(obj2 instanceof Object[])) {
                return;
            } else {
                arrayAdapter = new ArrayAdapter(this.f1838c, R.layout.simple_spinner_item, (Object[]) obj2);
            }
            arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        }
    }

    public void uls(Object obj, Object obj2, String str) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 == null) {
            return;
        }
        if (m2 instanceof ListView) {
            ListView listView = (ListView) m2;
            if (obj2 instanceof b0) {
                b0 b0Var = (b0) obj2;
                if (b0Var.b == null) {
                    b0Var.b = new c0(this.b);
                }
                c0 c0Var = (c0) b0Var.b;
                c0Var.b(str, b0Var);
                listView.setAdapter((ListAdapter) c0Var);
                Object[] objArr = (Object[]) listView.getTag();
                objArr[3] = b0Var;
                listView.setTag(objArr);
                return;
            }
            return;
        }
        if (m2 instanceof GridView) {
            GridView gridView = (GridView) m2;
            if (obj2 instanceof b0) {
                b0 b0Var2 = (b0) obj2;
                if (b0Var2.b == null) {
                    b0Var2.b = new c0(this.b);
                }
                c0 c0Var2 = (c0) b0Var2.b;
                c0Var2.b(str, b0Var2);
                gridView.setAdapter((ListAdapter) c0Var2);
                Object[] objArr2 = (Object[]) gridView.getTag();
                objArr2[3] = b0Var2;
                gridView.setTag(objArr2);
                return;
            }
            return;
        }
        if (m2 instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) m2;
            if (obj2 instanceof b0) {
                b0 b0Var3 = (b0) obj2;
                if (b0Var3.b == null) {
                    b0Var3.b = new d0(this.b);
                }
                d0 d0Var = (d0) b0Var3.b;
                d0Var.d(str, b0Var3);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.b));
                recyclerView.setAdapter(d0Var);
                Object[] objArr3 = (Object[]) recyclerView.getTag();
                objArr3[3] = b0Var3;
                recyclerView.setTag(objArr3);
                return;
            }
            return;
        }
        if (m2 instanceof TabLayout) {
            TabLayout tabLayout = (TabLayout) m2;
            if (obj2 instanceof b0) {
                c.b.a.a.h hVar = new c.b.a.a.h(this.b, 2);
                int h2 = h(str.substring(0, str.length() - 4));
                Iterator<HashMap<Integer, Object>> it = ((b0) obj2).a.iterator();
                while (it.hasNext()) {
                    HashMap<Integer, Object> next = it.next();
                    LinearLayout linearLayout = new LinearLayout(this.b);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                    linearLayout.setOrientation(1);
                    openRestoreinterface(str, linearLayout, h2, next);
                    tabLayout.addTab(tabLayout.newTab().setCustomView(linearLayout));
                    Iterator<Integer> it2 = next.keySet().iterator();
                    while (it2.hasNext()) {
                        int parseInt = Integer.parseInt(String.valueOf(it2.next()));
                        if (parseInt > 0) {
                            c.b.a.a.f.w(linearLayout.findViewById(parseInt + h2), next.get(Integer.valueOf(parseInt)), next, hVar);
                        }
                    }
                }
            }
        }
    }

    public void uls(Object obj, Object obj2, String str, int i2, int i3) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 == null) {
            return;
        }
        if (m2 instanceof ListView) {
            ListView listView = (ListView) m2;
            if (obj2 instanceof b0) {
                b0 b0Var = (b0) obj2;
                if (b0Var.b == null) {
                    b0Var.b = new c0(this.b);
                }
                c0 c0Var = (c0) b0Var.b;
                c0Var.b(str, b0Var);
                c0Var.c(i2, i3);
                listView.setAdapter((ListAdapter) c0Var);
                Object[] objArr = (Object[]) listView.getTag();
                objArr[3] = b0Var;
                listView.setTag(objArr);
                return;
            }
            return;
        }
        if (m2 instanceof GridView) {
            GridView gridView = (GridView) m2;
            if (obj2 instanceof b0) {
                b0 b0Var2 = (b0) obj2;
                if (b0Var2.b == null) {
                    b0Var2.b = new c0(this.b);
                }
                c0 c0Var2 = (c0) b0Var2.b;
                c0Var2.b(str, b0Var2);
                c0Var2.c(i2, i3);
                gridView.setAdapter((ListAdapter) c0Var2);
                Object[] objArr2 = (Object[]) gridView.getTag();
                objArr2[3] = b0Var2;
                gridView.setTag(objArr2);
                return;
            }
            return;
        }
        if (m2 instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) m2;
            if (obj2 instanceof b0) {
                b0 b0Var3 = (b0) obj2;
                if (b0Var3.b == null) {
                    b0Var3.b = new d0(this.b);
                }
                d0 d0Var = (d0) b0Var3.b;
                d0Var.d(str, b0Var3);
                d0Var.e(i2, i3);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.b));
                recyclerView.setAdapter(d0Var);
                Object[] objArr3 = (Object[]) recyclerView.getTag();
                objArr3[3] = b0Var3;
                recyclerView.setTag(objArr3);
                return;
            }
            return;
        }
        if (m2 instanceof TabLayout) {
            TabLayout tabLayout = (TabLayout) m2;
            if (obj2 instanceof b0) {
                c.b.a.a.h hVar = new c.b.a.a.h(this.b, 2);
                int h2 = h(str.substring(0, str.length() - 4));
                Iterator<HashMap<Integer, Object>> it = ((b0) obj2).a.iterator();
                while (it.hasNext()) {
                    HashMap<Integer, Object> next = it.next();
                    LinearLayout linearLayout = new LinearLayout(this.b);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(i2, i3));
                    linearLayout.setOrientation(1);
                    openRestoreinterface(str, linearLayout, h2, next);
                    tabLayout.addTab(tabLayout.newTab().setCustomView(linearLayout));
                    Iterator<Integer> it2 = next.keySet().iterator();
                    while (it2.hasNext()) {
                        int parseInt = Integer.parseInt(String.valueOf(it2.next()));
                        if (parseInt > 0) {
                            c.b.a.a.f.w(linearLayout.findViewById(parseInt + h2), next.get(Integer.valueOf(parseInt)), next, hVar);
                        }
                    }
                }
            }
        }
    }

    public void uninapp(String str) {
        this.f1838c.startActivity(new Intent("android.intent.action.DELETE", c.b.a.a.k.c(this.f1838c, "package:" + str)));
    }

    public Bitmap uqr(String str, int i2) {
        return new c.b.a.a.m(this.f1838c, this.b).c(str, i2);
    }

    public String uqr(Object obj) {
        return (obj instanceof Bitmap ? new c.b.a.a.m(this.f1838c, this.b).d((Bitmap) obj) : new c.b.a.a.m(this.f1838c, this.b).e(u(String.valueOf(obj)))).f();
    }

    public void uqr() {
        new c.b.a.a.m(this.f1838c, this.b).a();
    }

    public void uqr(int i2) {
        new c.b.a.a.m(this.f1838c, this.b).b(i2);
    }

    public void urvw(Object obj) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 != null) {
            ((ViewGroup) m2.getParent()).removeView(m2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean us(java.lang.Object r4, java.lang.String r5, java.lang.Object r6) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof android.view.View
            r1 = 0
            if (r0 == 0) goto L9
            android.view.View r4 = (android.view.View) r4
        L7:
            r0 = 0
            goto L4c
        L9:
            r3.k(r4)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r0 = 46
            int r0 = r4.indexOf(r0)
            r2 = -1
            if (r0 == r2) goto L41
            java.lang.String r2 = r4.substring(r1, r0)
            int r0 = r0 + 1
            java.lang.String r4 = r4.substring(r0)
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = com.iapp.app.Aid_javaCode.f
            java.lang.Object r0 = r0.get(r2)
            if (r0 == 0) goto L3f
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r0 = java.lang.Integer.parseInt(r0)
            android.app.Activity r2 = r3.b
            int r4 = java.lang.Integer.parseInt(r4)
            int r4 = r4 + r0
            android.view.View r4 = r2.findViewById(r4)
            goto L4c
        L3f:
            r4 = 0
            goto L7
        L41:
            android.app.Activity r0 = r3.b
            int r4 = java.lang.Integer.parseInt(r4)
            android.view.View r4 = r0.findViewById(r4)
            goto L7
        L4c:
            if (r4 != 0) goto L4f
            return r1
        L4f:
            com.iapp.app.i r1 = new com.iapp.app.i
            android.content.Context r2 = r3.f1838c
            r1.<init>(r4, r2, r0)
            boolean r4 = r1.e0(r5, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_javaCode.us(java.lang.Object, java.lang.String, java.lang.Object):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean us(java.lang.Object r4, java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof android.view.View
            r1 = 0
            if (r0 == 0) goto L9
            android.view.View r4 = (android.view.View) r4
        L7:
            r0 = 0
            goto L4c
        L9:
            r3.k(r4)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r0 = 46
            int r0 = r4.indexOf(r0)
            r2 = -1
            if (r0 == r2) goto L41
            java.lang.String r2 = r4.substring(r1, r0)
            int r0 = r0 + 1
            java.lang.String r4 = r4.substring(r0)
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = com.iapp.app.Aid_javaCode.f
            java.lang.Object r0 = r0.get(r2)
            if (r0 == 0) goto L3f
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r0 = java.lang.Integer.parseInt(r0)
            android.app.Activity r2 = r3.b
            int r4 = java.lang.Integer.parseInt(r4)
            int r4 = r4 + r0
            android.view.View r4 = r2.findViewById(r4)
            goto L4c
        L3f:
            r4 = 0
            goto L7
        L41:
            android.app.Activity r0 = r3.b
            int r4 = java.lang.Integer.parseInt(r4)
            android.view.View r4 = r0.findViewById(r4)
            goto L7
        L4c:
            if (r4 != 0) goto L4f
            return r1
        L4f:
            com.iapp.app.i r1 = new com.iapp.app.i
            android.content.Context r2 = r3.f1838c
            r1.<init>(r4, r2, r0)
            boolean r4 = r1.f0(r5, r6, r7)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_javaCode.us(java.lang.Object, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean us(java.lang.Object r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof android.view.View
            r1 = 0
            if (r0 == 0) goto L9
            android.view.View r4 = (android.view.View) r4
        L7:
            r0 = 0
            goto L4c
        L9:
            r3.k(r4)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r0 = 46
            int r0 = r4.indexOf(r0)
            r2 = -1
            if (r0 == r2) goto L41
            java.lang.String r2 = r4.substring(r1, r0)
            int r0 = r0 + 1
            java.lang.String r4 = r4.substring(r0)
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = com.iapp.app.Aid_javaCode.f
            java.lang.Object r0 = r0.get(r2)
            if (r0 == 0) goto L3f
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r0 = java.lang.Integer.parseInt(r0)
            android.app.Activity r2 = r3.b
            int r4 = java.lang.Integer.parseInt(r4)
            int r4 = r4 + r0
            android.view.View r4 = r2.findViewById(r4)
            goto L4c
        L3f:
            r4 = 0
            goto L7
        L41:
            android.app.Activity r0 = r3.b
            int r4 = java.lang.Integer.parseInt(r4)
            android.view.View r4 = r0.findViewById(r4)
            goto L7
        L4c:
            if (r4 != 0) goto L4f
            return r1
        L4f:
            com.iapp.app.i r1 = new com.iapp.app.i
            android.content.Context r2 = r3.f1838c
            r1.<init>(r4, r2, r0)
            boolean r4 = r1.g0(r5, r6, r7, r8)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_javaCode.us(java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean us(java.lang.Object r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof android.view.View
            r1 = 0
            if (r0 == 0) goto L9
            android.view.View r9 = (android.view.View) r9
        L7:
            r0 = 0
            goto L4c
        L9:
            r8.k(r9)
            java.lang.String r9 = java.lang.String.valueOf(r9)
            r0 = 46
            int r0 = r9.indexOf(r0)
            r2 = -1
            if (r0 == r2) goto L41
            java.lang.String r2 = r9.substring(r1, r0)
            int r0 = r0 + 1
            java.lang.String r9 = r9.substring(r0)
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = com.iapp.app.Aid_javaCode.f
            java.lang.Object r0 = r0.get(r2)
            if (r0 == 0) goto L3f
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r0 = java.lang.Integer.parseInt(r0)
            android.app.Activity r2 = r8.b
            int r9 = java.lang.Integer.parseInt(r9)
            int r9 = r9 + r0
            android.view.View r9 = r2.findViewById(r9)
            goto L4c
        L3f:
            r9 = 0
            goto L7
        L41:
            android.app.Activity r0 = r8.b
            int r9 = java.lang.Integer.parseInt(r9)
            android.view.View r9 = r0.findViewById(r9)
            goto L7
        L4c:
            if (r9 != 0) goto L4f
            return r1
        L4f:
            com.iapp.app.i r2 = new com.iapp.app.i
            android.content.Context r1 = r8.f1838c
            r2.<init>(r9, r1, r0)
            r3 = r10
            r4 = r11
            r5 = r12
            r6 = r13
            r7 = r14
            boolean r9 = r2.h0(r3, r4, r5, r6, r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_javaCode.us(java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    public Object usg(Camera camera, boolean z2) {
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return c.b.a.a.r.i(this.f1838c, z2);
        }
        if (z2) {
            camera = Camera.open();
            F(camera);
            return camera;
        }
        if (camera == null) {
            return null;
        }
        E(camera);
        camera.release();
        return null;
    }

    public void ushsp(boolean z2) {
        int i2;
        Activity activity;
        if (!z2) {
            i2 = 1;
            if (this.b.getRequestedOrientation() == 1) {
                return;
            } else {
                activity = this.b;
            }
        } else {
            if (this.b.getRequestedOrientation() == 0) {
                return;
            }
            activity = this.b;
            i2 = 0;
        }
        activity.setRequestedOrientation(i2);
    }

    public void usjxm(boolean z2) {
        if (z2) {
            this.b.getWindow().clearFlags(128);
        } else {
            this.b.getWindow().setFlags(128, 128);
        }
    }

    public void usms(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO", c.b.a.a.k.c(this.f1838c, "smsto:" + str));
        intent.putExtra("sms_body", str2);
        this.f1838c.startActivity(intent);
    }

    public Object usx(com.iapp.app.m mVar, String str) {
        if (str.equals("st")) {
            mVar.y();
            return null;
        }
        if (str.equals("sp")) {
            mVar.z();
            return null;
        }
        if (str.equals("re")) {
            mVar.u();
            return null;
        }
        if (str.equals("getrotaing")) {
            return Integer.valueOf(mVar.r());
        }
        return null;
    }

    public void usx(com.iapp.app.m mVar, Object obj, Object obj2) {
        if (obj.equals("rotaing")) {
            mVar.w(Integer.parseInt(obj2.toString()));
        } else if (obj.equals("usg")) {
            mVar.x(obj2.equals(Boolean.TRUE));
        }
    }

    public void usx(com.iapp.app.m mVar, String str, String str2, int i2, boolean z2) {
        if (str.equals("shot")) {
            mVar.q(u(str2), i2, z2);
        }
    }

    public com.iapp.app.m usxh(Object obj, int i2) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 instanceof SurfaceView) {
            return new com.iapp.app.m(this.f1838c, this.b, (SurfaceView) m2, false, i2);
        }
        return null;
    }

    public com.iapp.app.m usxh(Object obj, int i2, int i3, int i4, int i5) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 instanceof SurfaceView) {
            return new com.iapp.app.m(this.f1838c, this.b, (SurfaceView) m2, false, i2, i3, i4, i5);
        }
        return null;
    }

    public com.iapp.app.m usxq(Object obj, int i2) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 instanceof SurfaceView) {
            return new com.iapp.app.m(this.f1838c, this.b, (SurfaceView) m2, true, i2);
        }
        return null;
    }

    public com.iapp.app.m usxq(Object obj, int i2, int i3, int i4, int i5) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 instanceof SurfaceView) {
            return new com.iapp.app.m(this.f1838c, this.b, (SurfaceView) m2, true, i2, i3, i4, i5);
        }
        return null;
    }

    public Object utb(Object obj, Object obj2) {
        View m2;
        View m3;
        if (String.valueOf(obj).equals("get")) {
            if (obj2.equals("sab")) {
                return ((AppCompatActivity) this.b).getSupportActionBar();
            }
            if (obj2.equals("title")) {
                return String.valueOf(((AppCompatActivity) this.b).getSupportActionBar().getTitle());
            }
            if (obj2.equals("subtitle")) {
                return String.valueOf(((AppCompatActivity) this.b).getSupportActionBar().getSubtitle());
            }
            if (obj2.equals("cv")) {
                return ((AppCompatActivity) this.b).getSupportActionBar().getCustomView();
            }
            if (obj2.equals("do")) {
                return Integer.valueOf(((AppCompatActivity) this.b).getSupportActionBar().getDisplayOptions());
            }
            if (obj2.equals("height")) {
                return Integer.valueOf(((AppCompatActivity) this.b).getSupportActionBar().getHeight());
            }
            return null;
        }
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 != null && (m2 instanceof Toolbar)) {
            if (obj2 instanceof View) {
                m3 = (View) obj2;
            } else {
                k(obj2);
                m3 = m(obj2, String.valueOf(obj2));
            }
            if (m3 != null && (m3 instanceof DrawerLayout)) {
                ((AppCompatActivity) this.b).getSupportActionBar().setHomeButtonEnabled(true);
                DrawerLayout drawerLayout = (DrawerLayout) m3;
                String obj3 = ((Object[]) drawerLayout.getTag())[2].toString();
                w wVar = new w(this.b, drawerLayout, (Toolbar) m2, 0, 0, obj3.contains("<eventItme type=\"ondrawerclosed\">"), drawerLayout, obj3.contains("<eventItme type=\"ondraweropened\">"), obj3.contains("<eventItme type=\"onoptionsitemselected\">"));
                drawerLayout.setDrawerListener(wVar);
                wVar.syncState();
            }
        }
        return null;
    }

    public void utb(Object obj) {
        View m2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 == null || !(m2 instanceof Toolbar)) {
            return;
        }
        ((AppCompatActivity) this.b).setSupportActionBar((Toolbar) m2);
    }

    public void utb(Object obj, Object obj2, Object obj3) {
        View m2;
        View m3;
        boolean z2;
        ActionBar supportActionBar;
        Boolean bool = Boolean.TRUE;
        if (obj.equals("set")) {
            if (obj2.equals("title")) {
                ((AppCompatActivity) this.b).getSupportActionBar().setTitle(String.valueOf(obj3));
            }
            if (obj2.equals("subtitle")) {
                ((AppCompatActivity) this.b).getSupportActionBar().setSubtitle(String.valueOf(obj3));
                return;
            }
            if (obj2.equals("cv")) {
                ((AppCompatActivity) this.b).getSupportActionBar().setCustomView((View) obj3);
                return;
            }
            if (obj2.equals("do")) {
                ((AppCompatActivity) this.b).getSupportActionBar().setDisplayOptions(a(String.valueOf(obj3)));
                return;
            }
            if (obj2.equals("dste")) {
                ((AppCompatActivity) this.b).getSupportActionBar().setDisplayShowTitleEnabled(obj3.equals(bool));
                return;
            }
            if (obj2.equals("dsce")) {
                ((AppCompatActivity) this.b).getSupportActionBar().setDisplayShowCustomEnabled(obj3.equals(bool));
                return;
            } else {
                if (!obj2.equals("dshe")) {
                    return;
                }
                ((AppCompatActivity) this.b).getSupportActionBar().setHomeButtonEnabled(obj3.equals(bool));
                ((AppCompatActivity) this.b).getSupportActionBar().setDisplayShowHomeEnabled(obj3.equals(bool));
                supportActionBar = ((AppCompatActivity) this.b).getSupportActionBar();
                z2 = obj3.equals(bool);
            }
        } else {
            if (!obj.equals("left")) {
                if (obj.equals("right")) {
                    if (obj2 instanceof View) {
                        m2 = (View) obj2;
                    } else {
                        k(obj2);
                        m2 = m(obj2, String.valueOf(obj2));
                    }
                    if (m2 == null || !(m2 instanceof Toolbar)) {
                        return;
                    }
                    ((Toolbar) m2).setOverflowIcon(com.iapp.app.i.m(String.valueOf(obj3), this.f1838c));
                    return;
                }
                return;
            }
            if (obj2 instanceof View) {
                m3 = (View) obj2;
            } else {
                k(obj2);
                m3 = m(obj2, String.valueOf(obj2));
            }
            if (m3 == null || !(m3 instanceof Toolbar)) {
                return;
            }
            ((Toolbar) m3).setNavigationIcon(com.iapp.app.i.m(String.valueOf(obj3), this.f1838c));
            z2 = true;
            ((AppCompatActivity) this.b).getSupportActionBar().setHomeButtonEnabled(true);
            ((AppCompatActivity) this.b).getSupportActionBar().setDisplayShowHomeEnabled(true);
            supportActionBar = ((AppCompatActivity) this.b).getSupportActionBar();
        }
        supportActionBar.setDisplayHomeAsUpEnabled(z2);
    }

    public void utb(Object obj, Object obj2, Object obj3, Object obj4) {
        View m2;
        if (obj.equals("set") && obj2.equals("leftck")) {
            if (obj3 instanceof View) {
                m2 = (View) obj3;
            } else {
                k(obj3);
                m2 = m(obj3, String.valueOf(obj3));
            }
            if (m2 == null || !(m2 instanceof Toolbar)) {
                return;
            }
            ((Toolbar) m2).setNavigationOnClickListener((View.OnClickListener) obj4);
            ((AppCompatActivity) this.b).getSupportActionBar().setHomeButtonEnabled(true);
            ((AppCompatActivity) this.b).getSupportActionBar().setDisplayShowHomeEnabled(true);
            ((AppCompatActivity) this.b).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public View utw(Object obj, String str, String str2, String str3, String str4, String str5, boolean z2, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2, DialogInterface.OnClickListener onClickListener3) {
        Drawable m2;
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(this.f1838c).setTitle(str).setPositiveButton(str3, onClickListener).setNeutralButton(str4, onClickListener2).setNegativeButton(str5, onClickListener3);
        if (obj != null) {
            if (obj instanceof Drawable) {
                m2 = (Drawable) obj;
            } else {
                k(obj);
                m2 = com.iapp.app.i.m(String.valueOf(obj), this.f1838c);
            }
            negativeButton.setIcon(m2);
        }
        LinearLayout linearLayout = null;
        if (str2.endsWith(".ijava")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout2 = new LinearLayout(this.f1838c);
            linearLayout2.setLayoutParams(layoutParams);
            linearLayout2.setOrientation(1);
            try {
                openRestoreinterface(str2, linearLayout2, 0, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            negativeButton.setView(linearLayout2);
            linearLayout = linearLayout2;
        } else {
            negativeButton.setMessage(str2);
        }
        AlertDialog create = negativeButton.create();
        alertdialog = create;
        if (z2) {
            create.setCanceledOnTouchOutside(false);
            alertdialog.setCancelable(false);
            create = alertdialog;
        }
        create.show();
        return linearLayout;
    }

    public View utw(Object obj, String str, String str2, String str3, String str4, boolean z2, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        Drawable m2;
        AlertDialog.Builder neutralButton = new AlertDialog.Builder(this.f1838c).setTitle(str).setPositiveButton(str3, onClickListener).setNeutralButton(str4, onClickListener2);
        if (obj != null) {
            if (obj instanceof Drawable) {
                m2 = (Drawable) obj;
            } else {
                k(obj);
                m2 = com.iapp.app.i.m(String.valueOf(obj), this.f1838c);
            }
            neutralButton.setIcon(m2);
        }
        LinearLayout linearLayout = null;
        if (str2.endsWith(".ijava")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout2 = new LinearLayout(this.f1838c);
            linearLayout2.setLayoutParams(layoutParams);
            linearLayout2.setOrientation(1);
            try {
                openRestoreinterface(str2, linearLayout2, 0, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            neutralButton.setView(linearLayout2);
            linearLayout = linearLayout2;
        } else {
            neutralButton.setMessage(str2);
        }
        AlertDialog create = neutralButton.create();
        alertdialog = create;
        if (z2) {
            create.setCanceledOnTouchOutside(false);
            alertdialog.setCancelable(false);
            create = alertdialog;
        }
        create.show();
        return linearLayout;
    }

    public View utw(Object obj, String str, String str2, String str3, boolean z2, DialogInterface.OnClickListener onClickListener) {
        Drawable m2;
        AlertDialog.Builder positiveButton = new AlertDialog.Builder(this.f1838c).setTitle(str).setPositiveButton(str3, onClickListener);
        if (obj != null) {
            if (obj instanceof Drawable) {
                m2 = (Drawable) obj;
            } else {
                k(obj);
                m2 = com.iapp.app.i.m(String.valueOf(obj), this.f1838c);
            }
            positiveButton.setIcon(m2);
        }
        LinearLayout linearLayout = null;
        if (str2.endsWith(".ijava")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout2 = new LinearLayout(this.f1838c);
            linearLayout2.setLayoutParams(layoutParams);
            linearLayout2.setOrientation(1);
            try {
                openRestoreinterface(str2, linearLayout2, 0, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            positiveButton.setView(linearLayout2);
            linearLayout = linearLayout2;
        } else {
            positiveButton.setMessage(str2);
        }
        AlertDialog create = positiveButton.create();
        alertdialog = create;
        if (z2) {
            create.setCanceledOnTouchOutside(false);
            alertdialog.setCancelable(false);
            create = alertdialog;
        }
        create.show();
        return linearLayout;
    }

    public View utw(Object obj, String str, String str2, boolean z2) {
        Drawable m2;
        AlertDialog.Builder title = new AlertDialog.Builder(this.f1838c).setTitle(str);
        if (obj != null) {
            if (obj instanceof Drawable) {
                m2 = (Drawable) obj;
            } else {
                k(obj);
                m2 = com.iapp.app.i.m(String.valueOf(obj), this.f1838c);
            }
            title.setIcon(m2);
        }
        if (str2.endsWith(".ijava")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout = new LinearLayout(this.f1838c);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(1);
            try {
                openRestoreinterface(str2, linearLayout, 0, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            title.setView(linearLayout);
        } else {
            title.setMessage(str2);
        }
        AlertDialog create = title.create();
        alertdialog = create;
        if (z2) {
            create.setCanceledOnTouchOutside(false);
            alertdialog.setCancelable(false);
            create = alertdialog;
        }
        create.show();
        return null;
    }

    public View uxf(String str, int i2, int i3, int i4, int i5, int i6, String str2, int i7, int i8) {
        try {
            LinearLayout linearLayout = new LinearLayout(this.f1838c);
            linearLayout.setOrientation(1);
            int h2 = h(str.substring(0, str.length() - 4));
            linearLayout.setId(h2);
            openRestoreinterface(str, linearLayout, h2, null);
            View childAt = linearLayout.getChildAt(0);
            linearLayout.removeAllViews();
            c.b.a.a.r.e(this.f1838c, childAt, i2, i3, i4, i5, i6, com.iapp.app.i.t(str2), i7, i8);
            return childAt;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public View uxf(String str, int i2, int i3, String str2) {
        try {
            LinearLayout linearLayout = new LinearLayout(this.f1838c);
            linearLayout.setOrientation(1);
            int h2 = h(str.substring(0, str.length() - 4));
            linearLayout.setId(h2);
            openRestoreinterface(str, linearLayout, h2, null);
            View childAt = linearLayout.getChildAt(0);
            linearLayout.removeAllViews();
            c.b.a.a.r.e(this.f1838c, childAt, 0, 0, i2, i3, 0, com.iapp.app.i.t(str2), 0, 0);
            return childAt;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void uxf(Object obj) {
        View view = obj instanceof View ? (View) obj : null;
        if (view != null) {
            ((WindowManager) this.f1838c.getApplicationContext().getSystemService("window")).updateViewLayout(view, view.getLayoutParams());
        }
    }

    public void uxf(Object obj, String str) {
        if (str.equals("del")) {
            View view = obj instanceof View ? (View) obj : null;
            if (view != null) {
                ((WindowManager) this.f1838c.getApplicationContext().getSystemService("window")).removeView(view);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void uxf(java.lang.Object r2, java.lang.String r3, int r4, int r5, int r6, int r7, int r8, java.lang.String r9, int r10, int r11) {
        /*
            r1 = this;
            boolean r0 = r2 instanceof android.view.View
            if (r0 == 0) goto L63
            java.lang.String r0 = "set"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L63
            android.view.View r2 = (android.view.View) r2
            android.content.Context r3 = r1.f1838c
            android.content.Context r3 = r3.getApplicationContext()
            java.lang.String r0 = "window"
            java.lang.Object r3 = r3.getSystemService(r0)
            android.view.WindowManager r3 = (android.view.WindowManager) r3
            android.view.ViewGroup$LayoutParams r0 = r2.getLayoutParams()
            android.view.WindowManager$LayoutParams r0 = (android.view.WindowManager.LayoutParams) r0
            r0.x = r4
            r0.y = r5
            r4 = -2
            if (r6 != 0) goto L2c
            r0.width = r4
            goto L2e
        L2c:
            r0.width = r6
        L2e:
            if (r7 != 0) goto L33
            r0.height = r4
            goto L35
        L33:
            r0.height = r7
        L35:
            if (r8 != 0) goto L3e
            int r4 = c.b.a.a.r.f()
            r0.type = r4
            goto L40
        L3e:
            r0.type = r8
        L40:
            int r4 = com.iapp.app.i.t(r9)
            r0.gravity = r4
            if (r10 != 0) goto L4d
            r4 = 40
        L4a:
            r0.flags = r4
            goto L55
        L4d:
            r4 = 1
            if (r10 != r4) goto L53
            r4 = 32
            goto L4a
        L53:
            r0.flags = r10
        L55:
            if (r11 != 0) goto L5b
            r4 = -3
            r0.format = r4
            goto L5d
        L5b:
            r0.format = r11
        L5d:
            r2.setLayoutParams(r0)
            r3.updateViewLayout(r2, r0)
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_javaCode.uxf(java.lang.Object, java.lang.String, int, int, int, int, int, java.lang.String, int, int):void");
    }

    public void uxf(Object obj, String str, int i2, int i3, int i4, int i5, String str2) {
        if (str.equals("set")) {
            View view = obj instanceof View ? (View) obj : null;
            if (view != null) {
                WindowManager windowManager = (WindowManager) this.f1838c.getApplicationContext().getSystemService("window");
                WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
                layoutParams.x = i2;
                layoutParams.y = i3;
                if (i4 == 0) {
                    layoutParams.width = -2;
                } else {
                    layoutParams.width = i4;
                }
                if (i5 == 0) {
                    layoutParams.height = -2;
                } else {
                    layoutParams.height = i5;
                }
                layoutParams.gravity = com.iapp.app.i.t(str2);
                view.setLayoutParams(layoutParams);
                windowManager.updateViewLayout(view, layoutParams);
            }
        }
    }

    public void uycl(String str, boolean z2) {
        c.b.a.a.r.c(this.b, c.b.a.a.p.o(str), z2, this.b.findViewById(c.b.a.a.f.a));
    }

    public void uycl(String str, boolean z2, int i2) {
        c.b.a.a.r.d(this.b, c.b.a.a.p.o(str), z2, this.b.findViewById(c.b.a.a.f.a), i2);
    }

    public void uycl(boolean z2) {
        if (!z2) {
            this.b.getWindow().addFlags(2048);
        } else {
            this.b.getWindow().clearFlags(2048);
            this.b.getWindow().setFlags(1024, 1024);
        }
    }

    public Vibrator uzd(Vibrator vibrator, long j2) {
        if (vibrator == null) {
            vibrator = (Vibrator) this.f1838c.getSystemService("vibrator");
        }
        vibrator.vibrate(j2);
        return vibrator;
    }

    public Vibrator uzd(Vibrator vibrator, long[] jArr, boolean z2) {
        if (vibrator == null) {
            vibrator = (Vibrator) this.f1838c.getSystemService("vibrator");
        }
        vibrator.vibrate(jArr, z2 ? 1 : -1);
        return vibrator;
    }

    @TargetApi(11)
    public Object uzd(Vibrator vibrator, String str) {
        if (vibrator == null) {
            vibrator = (Vibrator) this.f1838c.getSystemService("vibrator");
        }
        if (str.equals("sp")) {
            try {
                vibrator.cancel();
            } catch (Exception unused) {
            }
            return null;
        }
        if (str.equals("ip")) {
            return Build.VERSION.SDK_INT >= 11 ? Boolean.valueOf(vibrator.hasVibrator()) : Boolean.FALSE;
        }
        return null;
    }

    public String valueOf(Object obj) {
        return String.valueOf(obj);
    }

    public View yul(String str) {
        return c.b.a.a.f.p(this.f1838c, str);
    }

    public Object yul(Object obj, String str) {
        View m2;
        View p2;
        if (obj instanceof View) {
            m2 = (View) obj;
        } else {
            k(obj);
            m2 = m(obj, String.valueOf(obj));
        }
        if (m2 == null || (p2 = c.b.a.a.f.p(this.f1838c, str)) == null) {
            return null;
        }
        ((ViewGroup) m2).addView(p2);
        return null;
    }

    public int zdp(float f2) {
        return c.b.a.a.p.l(this.f1838c, f2);
    }

    public Object zj(String str) {
        return c.b.a.a.f.n(str, new Object[]{"Activity", this.f1838c});
    }

    public Object zj(String str, Object[] objArr) {
        Object[] objArr2;
        if (objArr == null) {
            objArr2 = new Object[]{"Activity", this.f1838c};
        } else {
            int length = objArr.length;
            Object[] objArr3 = new Object[length + 2];
            objArr3[0] = "Activity";
            objArr3[1] = this.f1838c;
            for (int i2 = 0; i2 < length; i2++) {
                objArr3[i2 + 2] = objArr[i2];
            }
            objArr2 = objArr3;
        }
        return c.b.a.a.f.n(str, objArr2);
    }

    public int zpd(float f2) {
        return c.b.a.a.p.w(this.f1838c, f2);
    }

    public int zps(float f2) {
        return c.b.a.a.p.x(this.f1838c, f2);
    }

    public int zsp(float f2) {
        return c.b.a.a.p.A(this.f1838c, f2);
    }
}
