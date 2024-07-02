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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.Display;
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
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
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
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import bsh.EvalError;
import c.b.a.a.o;
import c.d.a.a;
import com.iapp.app.run.main;
import com.iapp.app.run.main2;
import com.iapp.app.run.main3;
import com.iapp.app.run.mian;
import com.iapp.interfaces.OnFileDownStatusListener;
import com.iapp.interfaces.OnMessagesListener;
import dalvik.system.DexClassLoader;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import org.keplerproject.luajava.LuaException;

@SuppressLint({"HandlerLeak"})
/* loaded from: classes.dex */
public class Aid_jsCode {
    public static AlertDialog alertdialog;
    private static HashMap<String, Object> g = new HashMap<>();
    private static int h = 0;
    public static HashMap<String, Object> ss_dim;
    public int TaskId;
    private Activity b;

    /* renamed from: c, reason: collision with root package name */
    private Context f1859c;

    /* renamed from: d, reason: collision with root package name */
    private WebView f1860d;
    private Handler f;
    private HashMap<String, Object> a = new HashMap<>();
    private long e = 0;

    /* loaded from: classes.dex */
    class a implements DialogInterface.OnClickListener {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            Aid_jsCode.this.a(this.a);
        }
    }

    /* loaded from: classes.dex */
    class a0 implements Animation.AnimationListener {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f1861c;

        a0(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.f1861c = str3;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            String str = this.a;
            if (str == null) {
                return;
            }
            Aid_jsCode.this.a(str);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            String str = this.b;
            if (str == null) {
                return;
            }
            Aid_jsCode.this.a(str);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            String str = this.f1861c;
            if (str == null) {
                return;
            }
            Aid_jsCode.this.a(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a1 implements View.OnClickListener {
        final /* synthetic */ String[] a;

        a1(String[] strArr) {
            this.a = strArr;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            Aid_jsCode.this.TheCallbackString(this.a[0], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "'");
        }
    }

    /* loaded from: classes.dex */
    class b implements DialogInterface.OnClickListener {
        final /* synthetic */ String a;

        b(String str) {
            this.a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            Aid_jsCode.this.a(this.a);
        }
    }

    /* loaded from: classes.dex */
    class b0 implements a.InterfaceC0049a {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f1863c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f1864d;

        b0(String str, String str2, String str3, String str4) {
            this.a = str;
            this.b = str2;
            this.f1863c = str3;
            this.f1864d = str4;
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void a(c.d.a.a aVar) {
            String str = this.a;
            if (str == null) {
                return;
            }
            Aid_jsCode.this.a(str);
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void b(c.d.a.a aVar) {
            String str = this.b;
            if (str == null) {
                return;
            }
            Aid_jsCode.this.a(str);
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void c(c.d.a.a aVar) {
            String str = this.f1863c;
            if (str == null) {
                return;
            }
            Aid_jsCode.this.a(str);
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void d(c.d.a.a aVar) {
            String str = this.f1864d;
            if (str == null) {
                return;
            }
            Aid_jsCode.this.a(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b1 implements View.OnTouchListener {
        final /* synthetic */ String[] a;

        b1(String[] strArr) {
            this.a = strArr;
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int id = view.getId();
            Aid_jsCode.this.TheCallbackString(this.a[0], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + motionEvent.getAction() + ", " + motionEvent.getX() + ", " + motionEvent.getY() + ", " + motionEvent.getRawX() + ", " + motionEvent.getRawY());
            return false;
        }
    }

    /* loaded from: classes.dex */
    class c implements DialogInterface.OnClickListener {
        final /* synthetic */ String a;

        c(String str) {
            this.a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            Aid_jsCode.this.a(this.a);
        }
    }

    /* loaded from: classes.dex */
    class c0 implements Runnable {
        final /* synthetic */ View a;

        c0(View view) {
            this.a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            WindowManager windowManager = (WindowManager) Aid_jsCode.this.f1859c.getApplicationContext().getSystemService("window");
            View view = this.a;
            windowManager.updateViewLayout(view, view.getLayoutParams());
        }
    }

    /* loaded from: classes.dex */
    class c1 implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ int b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f1865c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ Object f1866d;

        c1(View view, int i2, String str, Object obj) {
            this.a = view;
            this.b = i2;
            this.f1865c = str;
            this.f1866d = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            new com.iapp.app.i(this.a, Aid_jsCode.this.f1859c, this.b).e0(this.f1865c, this.f1866d);
        }
    }

    /* loaded from: classes.dex */
    class d implements DialogInterface.OnClickListener {
        final /* synthetic */ String a;

        d(String str) {
            this.a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            Aid_jsCode.this.a(this.a);
        }
    }

    /* loaded from: classes.dex */
    class d0 implements Runnable {
        final /* synthetic */ View a;

        d0(View view) {
            this.a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((WindowManager) Aid_jsCode.this.f1859c.getApplicationContext().getSystemService("window")).removeView(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d1 implements View.OnLongClickListener {
        final /* synthetic */ String[] a;

        d1(String[] strArr) {
            this.a = strArr;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            int id = view.getId();
            Aid_jsCode.this.TheCallbackString(this.a[0], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "'");
            return false;
        }
    }

    /* loaded from: classes.dex */
    class e implements DialogInterface.OnClickListener {
        final /* synthetic */ String a;

        e(String str) {
            this.a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            Aid_jsCode.this.a(this.a);
        }
    }

    /* loaded from: classes.dex */
    class e0 implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ int b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f1867c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f1868d;

        e0(View view, int i2, int i3, String str) {
            this.a = view;
            this.b = i2;
            this.f1867c = i3;
            this.f1868d = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.b.a.a.r.e(Aid_jsCode.this.f1859c, this.a, 0, 0, this.b, this.f1867c, 0, com.iapp.app.i.t(this.f1868d), 0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e1 implements View.OnKeyListener {
        final /* synthetic */ String[] a;

        e1(String[] strArr) {
            this.a = strArr;
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            int id = view.getId();
            Aid_jsCode.this.TheCallbackString(this.a[0], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2 + ", " + keyEvent.getAction() + ", " + keyEvent.getRepeatCount());
            return false;
        }
    }

    /* loaded from: classes.dex */
    class f implements Runnable {
        final /* synthetic */ String a;

        f(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object x = Aid_jsCode.this.x(this.a);
            if (x instanceof t1) {
                ((t1) x).notifyDataSetChanged();
            } else if (x instanceof ArrayAdapter) {
                ((ArrayAdapter) x).notifyDataSetChanged();
            }
        }
    }

    /* loaded from: classes.dex */
    class f0 implements Runnable {
        final /* synthetic */ WindowManager a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ WindowManager.LayoutParams f1869c;

        f0(Aid_jsCode aid_jsCode, WindowManager windowManager, View view, WindowManager.LayoutParams layoutParams) {
            this.a = windowManager;
            this.b = view;
            this.f1869c = layoutParams;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.updateViewLayout(this.b, this.f1869c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f1 implements TextView.OnEditorActionListener {
        final /* synthetic */ String[] a;

        f1(String[] strArr) {
            this.a = strArr;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            int id = textView.getId();
            if (keyEvent != null) {
                Aid_jsCode.this.TheCallbackString(this.a[0], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2 + ", " + keyEvent.getAction() + ", " + keyEvent.getRepeatCount() + ", " + keyEvent.getKeyCode());
            } else {
                Aid_jsCode.this.TheCallbackString(this.a[0], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2 + ", null, null, null");
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    class g implements Runnable {
        final /* synthetic */ Spinner a;
        final /* synthetic */ ArrayAdapter b;

        g(Aid_jsCode aid_jsCode, Spinner spinner, ArrayAdapter arrayAdapter) {
            this.a = spinner;
            this.b = arrayAdapter;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.setAdapter((SpinnerAdapter) this.b);
        }
    }

    /* loaded from: classes.dex */
    class g0 implements Runnable {
        final /* synthetic */ String a;

        g0(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(Aid_jsCode.this.f1859c, this.a, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g1 implements TextWatcher {
        final /* synthetic */ String[] a;
        final /* synthetic */ View b;

        g1(String[] strArr, View view) {
            this.a = strArr;
            this.b = view;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (this.a[2] != null) {
                int id = this.b.getId();
                Aid_jsCode.this.TheCallbackString(this.a[2], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', '" + editable.toString() + "'");
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (this.a[1] != null) {
                int id = this.b.getId();
                Aid_jsCode.this.TheCallbackString(this.a[1], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', '" + charSequence.toString() + "', " + i2 + ", " + i3 + ", " + i4);
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (this.a[0] != null) {
                int id = this.b.getId();
                Aid_jsCode.this.TheCallbackString(this.a[0], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', '" + charSequence.toString() + "', " + i2 + ", " + i3 + ", " + i4);
            }
        }
    }

    /* loaded from: classes.dex */
    class h implements Runnable {
        final /* synthetic */ Spinner a;
        final /* synthetic */ ArrayAdapter b;

        h(Aid_jsCode aid_jsCode, Spinner spinner, ArrayAdapter arrayAdapter) {
            this.a = spinner;
            this.b = arrayAdapter;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.setAdapter((SpinnerAdapter) this.b);
        }
    }

    /* loaded from: classes.dex */
    class h0 implements Runnable {
        final /* synthetic */ WindowManager a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ WindowManager.LayoutParams f1871c;

        h0(Aid_jsCode aid_jsCode, WindowManager windowManager, View view, WindowManager.LayoutParams layoutParams) {
            this.a = windowManager;
            this.b = view;
            this.f1871c = layoutParams;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.updateViewLayout(this.b, this.f1871c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h1 implements com.iapp.app.x5.a {
        final /* synthetic */ View a;
        final /* synthetic */ String[] b;

        h1(View view, String[] strArr) {
            this.a = view;
            this.b = strArr;
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            int id = this.a.getId();
            Aid_jsCode.this.TheCallbackString(this.b[0], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', '" + str + "', '" + str2 + "', '" + str3 + "', '" + str4 + "', " + j);
        }
    }

    /* loaded from: classes.dex */
    class i implements Runnable {
        final /* synthetic */ Spinner a;
        final /* synthetic */ ArrayAdapter b;

        i(Aid_jsCode aid_jsCode, Spinner spinner, ArrayAdapter arrayAdapter) {
            this.a = spinner;
            this.b = arrayAdapter;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.setAdapter((SpinnerAdapter) this.b);
        }
    }

    /* loaded from: classes.dex */
    class i0 implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ int b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f1873c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f1874d;
        final /* synthetic */ int e;
        final /* synthetic */ int f;
        final /* synthetic */ String g;
        final /* synthetic */ int h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ int f1875i;

        i0(View view, int i2, int i3, int i4, int i5, int i6, String str, int i7, int i8) {
            this.a = view;
            this.b = i2;
            this.f1873c = i3;
            this.f1874d = i4;
            this.e = i5;
            this.f = i6;
            this.g = str;
            this.h = i7;
            this.f1875i = i8;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.b.a.a.r.e(Aid_jsCode.this.f1859c, this.a, this.b, this.f1873c, this.f1874d, this.e, this.f, com.iapp.app.i.t(this.g), this.h, this.f1875i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class i1 implements View.OnFocusChangeListener {
        final /* synthetic */ String[] a;

        i1(String[] strArr) {
            this.a = strArr;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            int id = view.getId();
            Aid_jsCode.this.TheCallbackString(this.a[0], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + z);
        }
    }

    /* loaded from: classes.dex */
    class j implements Runnable {
        final /* synthetic */ ListView a;
        final /* synthetic */ t1 b;

        j(Aid_jsCode aid_jsCode, ListView listView, t1 t1Var) {
            this.a = listView;
            this.b = t1Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.setAdapter((ListAdapter) this.b);
        }
    }

    /* loaded from: classes.dex */
    class j0 implements OnMessagesListener {
        final /* synthetic */ String a;

        j0(String str) {
            this.a = str;
        }

        @Override // com.iapp.interfaces.OnMessagesListener
        public void Message(Object obj, o.c cVar) {
            Aid_jsCode.this.TheCallbackString(this.a, "'" + Aid_jsCode.this.z(obj) + "', '" + Aid_jsCode.this.N("sot", cVar) + "'");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class j1 implements AbsListView.OnScrollListener {
        final /* synthetic */ String[] a;

        j1(String[] strArr) {
            this.a = strArr;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            if (this.a[1] != null) {
                int id = absListView.getId();
                Aid_jsCode.this.TheCallbackString(this.a[1], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2 + ", " + i3 + ", " + i4);
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (this.a[0] != null) {
                int id = absListView.getId();
                Aid_jsCode.this.TheCallbackString(this.a[0], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2);
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
                Toast.makeText(Aid_jsCode.this.f1859c, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                Aid_jsCode.this.a(message.obj.toString());
            }
        }
    }

    /* loaded from: classes.dex */
    class k0 implements OnMessagesListener {
        final /* synthetic */ String a;

        k0(String str) {
            this.a = str;
        }

        @Override // com.iapp.interfaces.OnMessagesListener
        public void Message(Object obj, o.c cVar) {
            Aid_jsCode.this.TheCallbackString(this.a, "'" + Aid_jsCode.this.z(obj) + "', '" + Aid_jsCode.this.N("sot", cVar) + "'");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class k1 implements AdapterView.OnItemClickListener {
        final /* synthetic */ String[] a;

        k1(String[] strArr) {
            this.a = strArr;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            int id = adapterView.getId();
            Aid_jsCode.this.TheCallbackString(this.a[0], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2 + ", " + j);
        }
    }

    /* loaded from: classes.dex */
    class l implements Runnable {
        final /* synthetic */ GridView a;
        final /* synthetic */ t1 b;

        l(Aid_jsCode aid_jsCode, GridView gridView, t1 t1Var) {
            this.a = gridView;
            this.b = t1Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.setAdapter((ListAdapter) this.b);
        }
    }

    /* loaded from: classes.dex */
    class l0 extends WebChromeClient {
        l0() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            c.b.a.a.t.P2(Aid_jsCode.this.f1859c, "JsErr：\nLine" + consoleMessage.lineNumber() + ": " + consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class l1 implements AdapterView.OnItemSelectedListener {
        final /* synthetic */ String[] a;

        l1(String[] strArr) {
            this.a = strArr;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            if (this.a[0] != null) {
                int id = adapterView.getId();
                Aid_jsCode aid_jsCode = Aid_jsCode.this;
                String str = this.a[0];
                StringBuilder sb = new StringBuilder();
                sb.append(id);
                sb.append(", '^view");
                sb.append(Aid_jsCode.this.TaskId);
                sb.append("st_vW");
                sb.append(id);
                sb.append("', '");
                sb.append(main2.set("^onitemselected" + Aid_jsCode.this.TaskId + "st_vW2" + id, view));
                sb.append("', ");
                sb.append(i2);
                sb.append(", ");
                sb.append(j);
                aid_jsCode.TheCallbackString(str, sb.toString());
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            if (this.a[1] != null) {
                int id = adapterView.getId();
                Aid_jsCode.this.TheCallbackString(this.a[1], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "'");
            }
        }
    }

    /* loaded from: classes.dex */
    class m implements Runnable {
        final /* synthetic */ ListView a;
        final /* synthetic */ t1 b;

        m(Aid_jsCode aid_jsCode, ListView listView, t1 t1Var) {
            this.a = listView;
            this.b = t1Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.setAdapter((ListAdapter) this.b);
        }
    }

    /* loaded from: classes.dex */
    class m0 extends WebChromeClient {
        m0() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            c.b.a.a.t.P2(Aid_jsCode.this.f1859c, "JsErr：\nLine" + consoleMessage.lineNumber() + ": " + consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class m1 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ String[] a;
        final /* synthetic */ View b;

        m1(String[] strArr, View view) {
            this.a = strArr;
            this.b = view;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (this.a[2] != null) {
                int id = this.b.getId();
                Aid_jsCode.this.TheCallbackString(this.a[2], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            if (this.a[1] != null) {
                int id = this.b.getId();
                Aid_jsCode.this.TheCallbackString(this.a[1], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2 + ", " + f + ", " + i3);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (this.a[0] != null) {
                int id = this.b.getId();
                Aid_jsCode.this.TheCallbackString(this.a[0], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2);
            }
        }
    }

    /* loaded from: classes.dex */
    class n implements Runnable {
        final /* synthetic */ GridView a;
        final /* synthetic */ t1 b;

        n(Aid_jsCode aid_jsCode, GridView gridView, t1 t1Var) {
            this.a = gridView;
            this.b = t1Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.setAdapter((ListAdapter) this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class n0 implements View.OnClickListener {
        n0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            Aid_jsCode.this.callMethod("clicki" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "'");
        }
    }

    /* loaded from: classes.dex */
    class n1 implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ int b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f1877c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f1878d;
        final /* synthetic */ String e;

        n1(View view, int i2, String str, String str2, String str3) {
            this.a = view;
            this.b = i2;
            this.f1877c = str;
            this.f1878d = str2;
            this.e = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            new com.iapp.app.i(this.a, Aid_jsCode.this.f1859c, this.b).f0(this.f1877c, this.f1878d, this.e);
        }
    }

    /* loaded from: classes.dex */
    class o implements Runnable {
        final /* synthetic */ boolean a;

        o(boolean z) {
            this.a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!this.a) {
                Aid_jsCode.this.b.getWindow().addFlags(2048);
            } else {
                Aid_jsCode.this.b.getWindow().clearFlags(2048);
                Aid_jsCode.this.b.getWindow().setFlags(1024, 1024);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class o0 implements View.OnTouchListener {
        o0() {
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int id = view.getId();
            Aid_jsCode.this.callMethod("touchmonitor" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + motionEvent.getAction() + ", " + motionEvent.getX() + ", " + motionEvent.getY() + ", " + motionEvent.getRawX() + ", " + motionEvent.getRawY());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class o1 extends ActionBarDrawerToggle {
        final /* synthetic */ String[] l;
        final /* synthetic */ View m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        o1(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i2, int i3, String[] strArr, View view) {
            super(activity, drawerLayout, toolbar, i2, i3);
            this.l = strArr;
            this.m = view;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(View view) {
            if (this.l[0] != null) {
                int id = this.m.getId();
                Aid_jsCode aid_jsCode = Aid_jsCode.this;
                String str = this.l[0];
                StringBuilder sb = new StringBuilder();
                sb.append(id);
                sb.append(", '^view");
                sb.append(Aid_jsCode.this.TaskId);
                sb.append("st_vW");
                sb.append(id);
                sb.append("', '");
                sb.append(main2.set("^ondrawerclosed" + Aid_jsCode.this.TaskId + "st_dW" + id, view));
                sb.append("'");
                aid_jsCode.TheCallbackString(str, sb.toString());
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(View view) {
            if (this.l[1] != null) {
                int id = this.m.getId();
                Aid_jsCode aid_jsCode = Aid_jsCode.this;
                String str = this.l[1];
                StringBuilder sb = new StringBuilder();
                sb.append(id);
                sb.append(", '^view");
                sb.append(Aid_jsCode.this.TaskId);
                sb.append("st_vW");
                sb.append(id);
                sb.append("', '");
                sb.append(main2.set("^ondraweropened" + Aid_jsCode.this.TaskId + "st_dW" + id, view));
                sb.append("'");
                aid_jsCode.TheCallbackString(str, sb.toString());
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            if (this.l[2] == null) {
                return false;
            }
            int id = this.m.getId();
            Aid_jsCode aid_jsCode = Aid_jsCode.this;
            String str = this.l[2];
            StringBuilder sb = new StringBuilder();
            sb.append(id);
            sb.append(", '^view");
            sb.append(Aid_jsCode.this.TaskId);
            sb.append("st_vW");
            sb.append(id);
            sb.append("', '");
            sb.append(main2.set("^onoptionsitemselected" + Aid_jsCode.this.TaskId + "st_iM" + id, menuItem));
            sb.append("'");
            aid_jsCode.TheCallbackString(str, sb.toString());
            return false;
        }
    }

    /* loaded from: classes.dex */
    class p implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        p(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.b.a.a.r.c(Aid_jsCode.this.b, c.b.a.a.p.o(this.a), this.b, Aid_jsCode.this.b.findViewById(c.b.a.a.f.a));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class p0 implements View.OnLongClickListener {
        p0() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            int id = view.getId();
            Aid_jsCode.this.callMethod("press" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "'");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class p1 implements SeekBar.OnSeekBarChangeListener {
        final /* synthetic */ String[] a;

        p1(String[] strArr) {
            this.a = strArr;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            if (this.a[2] != null) {
                int id = seekBar.getId();
                Aid_jsCode.this.TheCallbackString(this.a[2], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2 + ", " + z);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (this.a[1] != null) {
                int id = seekBar.getId();
                Aid_jsCode.this.TheCallbackString(this.a[1], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "'");
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (this.a[0] != null) {
                int id = seekBar.getId();
                Aid_jsCode.this.TheCallbackString(this.a[0], id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "'");
            }
        }
    }

    /* loaded from: classes.dex */
    class q implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f1880c;

        q(String str, boolean z, int i2) {
            this.a = str;
            this.b = z;
            this.f1880c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.b.a.a.r.d(Aid_jsCode.this.b, c.b.a.a.p.o(this.a), this.b, Aid_jsCode.this.b.findViewById(c.b.a.a.f.a), this.f1880c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class q0 implements View.OnKeyListener {
        q0() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            int id = view.getId();
            Aid_jsCode.this.callMethod("keyboard" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2 + ", " + keyEvent.getAction() + ", " + keyEvent.getRepeatCount());
            return false;
        }
    }

    /* loaded from: classes.dex */
    class q1 implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ int b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f1882c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f1883d;
        final /* synthetic */ String e;
        final /* synthetic */ String f;

        q1(View view, int i2, String str, String str2, String str3, String str4) {
            this.a = view;
            this.b = i2;
            this.f1882c = str;
            this.f1883d = str2;
            this.e = str3;
            this.f = str4;
        }

        @Override // java.lang.Runnable
        public void run() {
            new com.iapp.app.i(this.a, Aid_jsCode.this.f1859c, this.b).g0(this.f1882c, this.f1883d, this.e, this.f);
        }
    }

    /* loaded from: classes.dex */
    class r implements Runnable {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ View b;

        r(Aid_jsCode aid_jsCode, ViewGroup viewGroup, View view) {
            this.a = viewGroup;
            this.b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.addView(this.b);
        }
    }

    /* loaded from: classes.dex */
    class r0 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ int b;

        r0(String str, int i2) {
            this.a = str;
            this.b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(Aid_jsCode.this.f1859c, this.a, this.b).show();
        }
    }

    /* loaded from: classes.dex */
    class r1 implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ int b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f1885c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f1886d;
        final /* synthetic */ String e;
        final /* synthetic */ String f;
        final /* synthetic */ String g;

        r1(View view, int i2, String str, String str2, String str3, String str4, String str5) {
            this.a = view;
            this.b = i2;
            this.f1885c = str;
            this.f1886d = str2;
            this.e = str3;
            this.f = str4;
            this.g = str5;
        }

        @Override // java.lang.Runnable
        public void run() {
            new com.iapp.app.i(this.a, Aid_jsCode.this.f1859c, this.b).h0(this.f1885c, this.f1886d, this.e, this.f, this.g);
        }
    }

    /* loaded from: classes.dex */
    class s implements Runnable {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f1887c;

        s(Aid_jsCode aid_jsCode, ViewGroup viewGroup, View view, int i2) {
            this.a = viewGroup;
            this.b = view;
            this.f1887c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.addView(this.b, this.f1887c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class s0 implements View.OnCreateContextMenuListener {
        s0() {
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
                Aid_jsCode.this.callMethod("onCreateContextMenu" + id + "x0", id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "'");
            }
        }
    }

    /* loaded from: classes.dex */
    class s1 implements DialogInterface.OnClickListener {
        final /* synthetic */ String a;

        s1(String str) {
            this.a = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            Aid_jsCode.this.a(this.a);
        }
    }

    /* loaded from: classes.dex */
    class t implements Runnable {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ View b;

        t(Aid_jsCode aid_jsCode, ViewGroup viewGroup, View view) {
            this.a = viewGroup;
            this.b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.addView(this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class t0 implements TextView.OnEditorActionListener {
        t0() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            int id = textView.getId();
            if (keyEvent == null) {
                Aid_jsCode.this.callMethod("editormonitor" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2 + ", null, null, null");
                return false;
            }
            Aid_jsCode.this.callMethod("editormonitor" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2 + ", " + keyEvent.getAction() + ", " + keyEvent.getRepeatCount() + ", " + keyEvent.getKeyCode());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class t1 extends BaseAdapter {
        private Context a;
        private c.b.a.a.h b;
        private int g;
        private Iterator j;
        private LinearLayout k;
        private a l;

        /* renamed from: c, reason: collision with root package name */
        private ArrayList<HashMap<Integer, Object>> f1888c = new ArrayList<>();

        /* renamed from: d, reason: collision with root package name */
        private String f1889d = null;
        private WebView e = null;
        private int f = 0;
        private int h = -1;

        /* renamed from: i, reason: collision with root package name */
        private int f1890i = -2;

        /* loaded from: classes.dex */
        private final class a {
            public HashMap<Integer, View> a;

            private a(t1 t1Var) {
                this.a = new HashMap<>();
            }

            /* synthetic */ a(t1 t1Var, k kVar) {
                this(t1Var);
            }
        }

        public t1(Context context) {
            this.a = context;
            this.b = new c.b.a.a.h(context, this, 1);
        }

        public void a(HashMap<Integer, Object> hashMap) {
            this.f1888c.add(hashMap);
        }

        public ArrayList<HashMap<Integer, Object>> b() {
            return this.f1888c;
        }

        public void c(String str) {
            this.f1889d = str;
            this.f = Aid_jsCode.this.m(str.substring(0, str.length() - 4));
        }

        public void d(int i2, int i3) {
            this.h = i2;
            this.f1890i = i3;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f1888c.size();
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
            if (view == null) {
                this.l = new a(this, null);
                LinearLayout linearLayout = new LinearLayout(this.a);
                this.k = linearLayout;
                linearLayout.setLayoutParams(new AbsListView.LayoutParams(this.h, this.f1890i));
                this.k.setOrientation(1);
                this.e = Aid_jsCode.this.K(this.f1889d, this.k, this.f, this.f1888c.get(i2));
                this.j = this.f1888c.get(i2).keySet().iterator();
                while (this.j.hasNext()) {
                    int parseInt = Integer.parseInt(String.valueOf(this.j.next()));
                    this.g = parseInt;
                    if (parseInt > 0) {
                        this.l.a.put(Integer.valueOf(parseInt), this.k.findViewById(this.g + this.f));
                    }
                }
                view = this.k;
                view.setTag(this.l);
            } else {
                this.l = (a) view.getTag();
            }
            this.j = this.f1888c.get(i2).keySet().iterator();
            while (this.j.hasNext()) {
                int parseInt2 = Integer.parseInt(String.valueOf(this.j.next()));
                this.g = parseInt2;
                if (parseInt2 > 0) {
                    c.b.a.a.f.w(this.l.a.get(Integer.valueOf(parseInt2)), this.f1888c.get(i2).get(Integer.valueOf(this.g)), this.f1888c.get(i2), this.b);
                }
            }
            WebView webView = this.e;
            if (webView != null) {
                Aid_jsCode.this.o(webView, "loading");
            }
            return view;
        }
    }

    /* loaded from: classes.dex */
    class u implements Runnable {
        final /* synthetic */ ViewGroup a;
        final /* synthetic */ View b;

        u(Aid_jsCode aid_jsCode, ViewGroup viewGroup, View view) {
            this.a = viewGroup;
            this.b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.removeView(this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class u0 implements View.OnFocusChangeListener {
        u0() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            int id = view.getId();
            Aid_jsCode.this.callMethod("focuschange" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + z);
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
                Toast.makeText(Aid_jsCode.this.f1859c, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                Aid_jsCode.this.a(message.obj.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class v0 implements AbsListView.OnScrollListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ boolean b;

        v0(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            if (this.b) {
                int id = absListView.getId();
                Aid_jsCode.this.callMethod("onscroll" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2 + ", " + i3 + ", " + i4);
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (this.a) {
                int id = absListView.getId();
                Aid_jsCode.this.callMethod("onscrollstatechanged" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2);
            }
        }
    }

    /* loaded from: classes.dex */
    class w implements Runnable {
        final /* synthetic */ ViewPager a;
        final /* synthetic */ ArrayList b;

        w(Aid_jsCode aid_jsCode, ViewPager viewPager, ArrayList arrayList) {
            this.a = viewPager;
            this.b = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            new com.iapp.app.u(this.a, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class w0 implements AdapterView.OnItemClickListener {
        w0() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            int id = adapterView.getId();
            Aid_jsCode.this.callMethod("clickitem" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2 + ", " + j);
        }
    }

    /* loaded from: classes.dex */
    class x implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ ViewGroup b;

        x(String str, ViewGroup viewGroup) {
            this.a = str;
            this.b = viewGroup;
        }

        @Override // java.lang.Runnable
        public void run() {
            Aid_jsCode aid_jsCode = Aid_jsCode.this;
            String str = this.a;
            aid_jsCode.K(str, this.b, aid_jsCode.m(str.substring(0, str.length() - 5)), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class x0 implements AdapterView.OnItemSelectedListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ boolean b;

        x0(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            if (this.a) {
                int id = adapterView.getId();
                StringBuilder sb = new StringBuilder();
                sb.append(id);
                sb.append(", '^view");
                sb.append(Aid_jsCode.this.TaskId);
                sb.append("st_vW");
                sb.append(id);
                sb.append("', '");
                sb.append(main2.set("^onitemselected" + Aid_jsCode.this.TaskId + "st_vW2" + id, view));
                sb.append("', ");
                sb.append(i2);
                sb.append(", ");
                sb.append(j);
                Aid_jsCode.this.callMethod("onitemselected" + id, sb.toString());
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            if (this.b) {
                int id = adapterView.getId();
                Aid_jsCode.this.callMethod("onnothingselected" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "'");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class y implements OnFileDownStatusListener {
        y() {
        }

        @Override // com.iapp.interfaces.OnFileDownStatusListener
        public void complete(int i2, Object obj) {
            if (obj == null) {
                return;
            }
            Aid_jsCode.this.TheCallbackString(obj.toString(), String.valueOf(i2));
        }

        @Override // com.iapp.interfaces.OnFileDownStatusListener
        public void resultStatus(int i2, int i3, Object obj) {
            if (obj == null) {
                return;
            }
            Aid_jsCode.this.TheCallbackString(obj.toString(), i2 + "," + i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class y0 implements SeekBar.OnSeekBarChangeListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ boolean b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1894c;

        y0(boolean z, boolean z2, boolean z3) {
            this.a = z;
            this.b = z2;
            this.f1894c = z3;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            if (this.f1894c) {
                int id = seekBar.getId();
                Aid_jsCode.this.callMethod("onprogresschanged2" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "', " + i2 + ", " + z);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (this.b) {
                int id = seekBar.getId();
                Aid_jsCode.this.callMethod("onstarttrackingtouch" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "'");
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (this.a) {
                int id = seekBar.getId();
                Aid_jsCode.this.callMethod("onstoptrackingtouch" + id, id + ", '^view" + Aid_jsCode.this.TaskId + "st_vW" + id + "'");
            }
        }
    }

    /* loaded from: classes.dex */
    class z extends Thread {
        final /* synthetic */ String a;

        z(String str) {
            this.a = str;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Aid_jsCode.this.a(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class z0 implements com.iapp.app.x5.a {
        z0(Aid_jsCode aid_jsCode) {
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

    public Aid_jsCode(Activity activity, WebView webView, int i2) {
        this.b = null;
        this.f1859c = null;
        this.f1860d = null;
        this.TaskId = 0;
        this.f = null;
        this.f1859c = activity;
        this.b = activity;
        this.f1860d = webView;
        this.TaskId = i2;
        this.f = new k(activity.getMainLooper());
    }

    public Aid_jsCode(Context context, Activity activity, WebView webView, int i2) {
        this.b = null;
        this.f1859c = null;
        this.f1860d = null;
        this.TaskId = 0;
        this.f = null;
        this.f1859c = context;
        this.b = activity;
        this.f1860d = webView;
        this.TaskId = i2;
        this.f = new v(context.getMainLooper());
    }

    private int A() {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return this.f1859c.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private int B() {
        int identifier = this.f1859c.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return this.f1859c.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private String C(String str) {
        return c.b.a.a.f.o(this.f1859c, str);
    }

    private GradientDrawable.Orientation D(String str) {
        return str.equals("topbottom") ? GradientDrawable.Orientation.TOP_BOTTOM : str.equals("trbl") ? GradientDrawable.Orientation.TR_BL : str.equals("rightleft") ? GradientDrawable.Orientation.RIGHT_LEFT : str.equals("brtl") ? GradientDrawable.Orientation.BR_TL : str.equals("bottomtop") ? GradientDrawable.Orientation.BOTTOM_TOP : str.equals("bltr") ? GradientDrawable.Orientation.BL_TR : str.equals("leftright") ? GradientDrawable.Orientation.LEFT_RIGHT : str.equals("tlbr") ? GradientDrawable.Orientation.TL_BR : GradientDrawable.Orientation.TOP_BOTTOM;
    }

    private String E(String str) {
        String c2;
        String c3;
        Intent launchIntentForPackage = this.f1859c.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null || (c2 = c.b.a.a.p.c(launchIntentForPackage.toString(), "cmp=", " ")) == null || (c3 = c.b.a.a.p.c(c2, "/", null)) == null) {
            return "";
        }
        if (!c3.startsWith(".")) {
            return c3;
        }
        return str + c3;
    }

    private String[] F(String str) {
        if (str == null) {
            return null;
        }
        return c.b.a.a.q.d(str, "\n\\\r");
    }

    private Object[] G(String str) {
        if (str == null) {
            return null;
        }
        String[] d2 = c.b.a.a.q.d(str, "\n\\\r");
        int length = d2.length;
        Object[] objArr = new Object[length];
        for (int i2 = 0; i2 < length; i2++) {
            objArr[i2] = x(d2[i2]);
        }
        return objArr;
    }

    private void H(c.b.a.a.e eVar, String str, String str2) {
        if (eVar != null) {
            eVar.j = str;
            eVar.k = str2;
            eVar.s(new y());
        }
    }

    private String I(String str, Object obj) {
        if (obj == null) {
            return null;
        }
        return ((obj instanceof String) || (obj instanceof Number) || (obj instanceof Boolean)) ? String.valueOf(obj) : N(str, obj);
    }

    private void J(File file) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        c.b.a.a.k.a(this.f1859c, intent, file, c.b.a.a.p.p(file));
        this.f1859c.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WebView K(String str, ViewGroup viewGroup, int i2, Object obj) {
        WebView b2 = com.iapp.app.c.b(this.f1859c, this.b, 0);
        StringBuffer stringBuffer = new StringBuffer();
        Context context = this.f1859c;
        c.c.a.b bVar = new c.c.a.b(context);
        bVar.a = 0;
        c.b.a.a.f.q(this, context, str, viewGroup, i2, obj, bVar, stringBuffer, b2);
        String C = c.b.a.a.f.C(this.f1859c, str);
        if (C != null) {
            stringBuffer.append("function loading()\n");
            stringBuffer.append(C);
            stringBuffer.append("\nend\n");
        }
        com.iapp.app.c.c(this.f1859c, b2, stringBuffer.toString());
        if (C == null) {
            return null;
        }
        return b2;
    }

    @TargetApi(11)
    private void L(com.iapp.app.x5.WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
    }

    private String M(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int length = objArr.length;
        if (length > 0) {
            stringBuffer.append(objArr[0]);
        }
        if (length > 1) {
            for (int i2 = 1; i2 < length; i2++) {
                stringBuffer.append("\n\\\r");
                stringBuffer.append(objArr[i2]);
            }
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String N(String str, Object obj) {
        if (obj == null) {
            return null;
        }
        this.e++;
        StringBuffer stringBuffer = new StringBuffer("^_");
        stringBuffer.append(str);
        stringBuffer.append(this.TaskId);
        stringBuffer.append('_');
        stringBuffer.append(this.e);
        return main2.set(stringBuffer.toString(), obj);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    @TargetApi(16)
    private void O(com.iapp.app.x5.WebView webView, String str, StringBuffer stringBuffer, WebView webView2) {
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAppCachePath(this.f1859c.getApplicationContext().getDir("cache", 0).getPath());
        webView.getSettings().setAppCacheMaxSize(8388608L);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDatabasePath(this.f1859c.getApplicationContext().getDir("database", 0).getPath());
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
        if (addViewEventItme(stringBuffer, str, webView.getId(), "ondownloadstart", "st_vId,st_vW,st_url,st_uT,st_cN,st_mE,st_cH")) {
            new com.iapp.app.n(webView, webView2, this.TaskId);
        } else {
            webView.setDownloadListener(new z0(this));
        }
        new com.iapp.app.x5.b().d(webView, str, stringBuffer, this.b, this);
        L(webView);
    }

    private void P(String str) {
        c.b.a.a.f.G(this.f1859c, str);
    }

    private String[] Q(String str, char c2) {
        return c.b.a.a.q.b(str, c2);
    }

    private String R(String str) {
        return str.substring(str.indexOf(40) + 1, str.lastIndexOf(41));
    }

    private void S(Camera camera) {
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

    private void T(Camera camera) {
        Camera.Parameters parameters;
        List<String> supportedFlashModes;
        if (camera == null || (parameters = camera.getParameters()) == null || (supportedFlashModes = parameters.getSupportedFlashModes()) == null || "torch".equals(parameters.getFlashMode()) || !supportedFlashModes.contains("torch")) {
            return;
        }
        parameters.setFlashMode("torch");
        camera.setParameters(parameters);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.f1860d.loadUrl("javascript:{\nvar functionItme = " + str + ";\nfunctionItme();\n}");
    }

    public static void degclear() {
        c.b.a.a.t.j.clear();
        g.clear();
        h = 0;
    }

    private void k(View view, int i2, String str, StringBuffer stringBuffer, WebView webView) {
        main2.set("^view" + this.TaskId + "st_vW" + i2, view);
        if (addViewEventItme(stringBuffer, str, i2, "clicki", "st_vId,st_vW")) {
            view.setOnClickListener(new n0());
        }
        if (addViewEventItme(stringBuffer, str, i2, "touchmonitor", "st_vId,st_vW,st_eA,st_eX,st_eY,st_rX,st_rY")) {
            view.setOnTouchListener(new o0());
        }
        if (addViewEventItme(stringBuffer, str, i2, "press", "st_vId,st_vW")) {
            view.setOnLongClickListener(new p0());
        }
        if (addViewEventItme(stringBuffer, str, i2, "keyboard", "st_vId,st_vW,st_kC,st_eA,st_eR,st_eT")) {
            view.setOnKeyListener(new q0());
        }
        if (str.contains("<eventItme type=\"pressmenu\">")) {
            String c2 = c.b.a.a.p.c(str, "<eventItme type=\"pressmenu\">", "</eventItme>");
            String[] split = c2.split("\ncase ");
            for (int i3 = 1; i3 < split.length; i3++) {
                stringBuffer.append("function onCreateContextMenu" + i2 + "x" + i3 + "(){\n" + c.b.a.a.p.c(split[i3], ":", "\nbreak") + "\n}\n");
            }
            String c3 = c.b.a.a.p.c(c2, "\ndefault:", "\nbreak");
            if (c3 != null) {
                stringBuffer.append("function onCreateContextMenu" + i2 + "x0(st_vId,st_vW){\n" + c3 + "\n}\n");
            }
            view.setOnCreateContextMenuListener(new s0());
        }
        if (view instanceof TextView) {
            if (addViewEventItme(stringBuffer, str, i2, "editormonitor", "st_vId,st_vW,st_aI,st_eA,st_eR,st_eK,st_eT")) {
                ((TextView) view).setOnEditorActionListener(new t0());
            }
            boolean addViewEventItme = addViewEventItme(stringBuffer, str, i2, "ontextchanged", "st_vId,st_vW,st_sS,st_sT,st_bE,st_cT");
            boolean addViewEventItme2 = addViewEventItme(stringBuffer, str, i2, "beforetextchanged", "st_vId,st_vW,st_sS,st_sT,st_cT,st_aR");
            boolean addViewEventItme3 = addViewEventItme(stringBuffer, str, i2, "aftertextchanged", "st_vId,st_vW,st_sS");
            if (addViewEventItme || addViewEventItme2 || addViewEventItme3) {
                new com.iapp.app.t((TextView) view, this.f1860d, "^view" + this.TaskId + "st_vW" + i2, addViewEventItme, addViewEventItme2, addViewEventItme3);
            }
        }
        if (view instanceof com.iapp.app.x5.WebView) {
            O((com.iapp.app.x5.WebView) view, str, stringBuffer, webView);
        }
        if (addViewEventItme(stringBuffer, str, i2, "focuschange", "st_vId,st_vW,st_hF")) {
            view.setOnFocusChangeListener(new u0());
        }
        if (view instanceof AbsListView) {
            boolean addViewEventItme4 = addViewEventItme(stringBuffer, str, i2, "onscrollstatechanged", "st_vId,st_vW,st_sE");
            boolean addViewEventItme5 = addViewEventItme(stringBuffer, str, i2, "onscroll", "st_vId,st_vW,st_fM,st_vT,st_bT");
            if (addViewEventItme4 || addViewEventItme5) {
                ((AbsListView) view).setOnScrollListener(new v0(addViewEventItme4, addViewEventItme5));
            }
        }
        if (view instanceof AdapterView) {
            if (addViewEventItme(stringBuffer, str, i2, "clickitem", "st_vId,st_vW,st_pN,st_iD,st_vW2")) {
                ((AdapterView) view).setOnItemClickListener(new w0());
            }
            boolean addViewEventItme6 = addViewEventItme(stringBuffer, str, i2, "onitemselected", "st_vId,st_vW,st_vW2,st_pN,st_iD");
            boolean addViewEventItme7 = addViewEventItme(stringBuffer, str, i2, "onnothingselected", "st_vId,st_vW");
            if (addViewEventItme6 || addViewEventItme7) {
                ((AdapterView) view).setOnItemSelectedListener(new x0(addViewEventItme6, addViewEventItme7));
            }
        }
        if (view instanceof ViewPager) {
            boolean addViewEventItme8 = addViewEventItme(stringBuffer, str, i2, "onpageselected", "st_vId,st_vW,st_pN");
            boolean addViewEventItme9 = addViewEventItme(stringBuffer, str, i2, "onpagescrolled", "st_vId,st_vW,st_pN,st_pT,st_pS");
            boolean addViewEventItme10 = addViewEventItme(stringBuffer, str, i2, "onpagescrollstatechanged", "st_vId,st_vW,st_sE");
            if (addViewEventItme8 || addViewEventItme9 || addViewEventItme10) {
                new com.iapp.app.q((ViewPager) view, this.f1860d, "^view" + this.TaskId + "st_vW" + i2, addViewEventItme8, addViewEventItme9, addViewEventItme10);
            }
        }
        if (view instanceof DrawerLayout) {
            boolean addViewEventItme11 = addViewEventItme(stringBuffer, str, i2, "ondrawerclosed", "st_vId,st_vW,st_dW");
            boolean addViewEventItme12 = addViewEventItme(stringBuffer, str, i2, "ondraweropened", "st_vId,st_vW,st_dW");
            boolean addViewEventItme13 = addViewEventItme(stringBuffer, str, i2, "onoptionsitemselected", "st_vId,st_vW,st_iM");
            if (addViewEventItme11 || addViewEventItme12 || addViewEventItme13) {
                new com.iapp.app.o((DrawerLayout) view, this.f1860d, "^view" + this.TaskId + "st_vW" + i2, this.TaskId, addViewEventItme11, addViewEventItme12, addViewEventItme13);
            }
        }
        if (view instanceof SeekBar) {
            if (str.contains("<eventItme type=\"onstarttrackingtouch\">") || str.contains("<eventItme type=\"onstoptrackingtouch\">") || str.contains("<eventItme type=\"onprogresschanged2\">")) {
                boolean addViewEventItme14 = addViewEventItme(stringBuffer, str, i2, "onstarttrackingtouch", "st_vId,st_vW");
                boolean addViewEventItme15 = addViewEventItme(stringBuffer, str, i2, "onstoptrackingtouch", "st_vId,st_vW");
                boolean addViewEventItme16 = addViewEventItme(stringBuffer, str, i2, "onprogresschanged2", "st_vId,st_vW,st_nS,st_fR");
                if (addViewEventItme14 || addViewEventItme15 || addViewEventItme16) {
                    ((SeekBar) view).setOnSeekBarChangeListener(new y0(addViewEventItme15, addViewEventItme14, addViewEventItme16));
                }
            }
        }
    }

    private void l(View view, String str, String[] strArr) {
        main2.set("^view" + this.TaskId + "st_vW" + view.getId(), view);
        if (str.equals("clicki")) {
            view.setOnClickListener(new a1(strArr));
            return;
        }
        if (str.equals("touchmonitor")) {
            view.setOnTouchListener(new b1(strArr));
            return;
        }
        if (str.equals("press")) {
            view.setOnLongClickListener(new d1(strArr));
            return;
        }
        if (str.equals("keyboard")) {
            view.setOnKeyListener(new e1(strArr));
            return;
        }
        if (str.equals("editormonitor")) {
            ((TextView) view).setOnEditorActionListener(new f1(strArr));
            return;
        }
        if (str.equals("addtextchanged")) {
            ((TextView) view).addTextChangedListener(new g1(strArr, view));
            return;
        }
        if (str.equals("ondownloadstart")) {
            ((com.iapp.app.x5.WebView) view).setDownloadListener(new h1(view, strArr));
            return;
        }
        if (str.equals("webviewclient")) {
            new com.iapp.app.x5.b().e(view, strArr, this);
            return;
        }
        if (str.equals("focuschange")) {
            view.setOnFocusChangeListener(new i1(strArr));
            return;
        }
        if (str.equals("onscroll")) {
            ((AbsListView) view).setOnScrollListener(new j1(strArr));
            return;
        }
        if (str.equals("clickitem")) {
            ((AdapterView) view).setOnItemClickListener(new k1(strArr));
            return;
        }
        if (str.equals("onitemselected")) {
            ((AdapterView) view).setOnItemSelectedListener(new l1(strArr));
            return;
        }
        if (str.equals("onpagechange")) {
            ((ViewPager) view).setOnPageChangeListener(new m1(strArr, view));
            return;
        }
        if (!str.equals("ondrawer")) {
            if (str.equals("onseekbarchange")) {
                ((SeekBar) view).setOnSeekBarChangeListener(new p1(strArr));
            }
        } else {
            DrawerLayout drawerLayout = (DrawerLayout) view;
            Activity activity = this.b;
            int i2 = c.b.a.a.f.b;
            drawerLayout.setDrawerListener(new o1(activity, drawerLayout, null, i2, i2, strArr, view));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int m(String str) {
        Object obj = g.get(str);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        int i2 = h + 10000;
        h = i2;
        g.put(str, Integer.valueOf(i2));
        return h;
    }

    private byte[] n(String str, char c2) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void o(WebView webView, String str) {
        webView.loadUrl("javascript:" + str + "()");
    }

    private void p(String str, Object obj, int i2) {
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

    private View q(View view, Object obj, String str) {
        String trim = str.trim();
        int indexOf = trim.indexOf(46);
        if (indexOf != -1 && trim.indexOf(34) == -1) {
            String substring = trim.substring(0, indexOf);
            String substring2 = trim.substring(indexOf + 1);
            Object obj2 = g.get(substring);
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
        Object obj3 = g.get(substring3);
        if (obj3 == null) {
            return null;
        }
        return view.findViewById(Integer.parseInt(String.valueOf(obj3)) + Integer.parseInt(substring4));
    }

    private View r(Object obj, String str) {
        String trim = str.trim();
        int indexOf = trim.indexOf(46);
        if (indexOf != -1 && trim.indexOf(34) == -1) {
            String substring = trim.substring(0, indexOf);
            String substring2 = trim.substring(indexOf + 1);
            Object obj2 = g.get(substring);
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
        Object obj3 = g.get(substring3);
        if (obj3 == null) {
            return null;
        }
        return this.b.findViewById(Integer.parseInt(String.valueOf(obj3)) + Integer.parseInt(substring4));
    }

    private int s() {
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

    private String[] t() {
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

    private int[] u() {
        return new int[]{this.b.getWindowManager().getDefaultDisplay().getWidth(), this.b.getWindowManager().getDefaultDisplay().getHeight()};
    }

    private int[] v() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.b.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    private String[] w() {
        return new String[]{Build.MODEL, Build.BRAND, String.valueOf(Build.VERSION.SDK_INT)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object x(String str) {
        if (str == null) {
            return null;
        }
        return str.equals("^_activity_") ? this.f1859c : str.startsWith("^") ? main2.get(str) : str;
    }

    private String y(Object obj) {
        return ((obj instanceof Number) || (obj instanceof Boolean) || (obj instanceof String)) ? obj.toString() : N("sss", obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String z(Object obj) {
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

    public void TheCallbackString(String str, String str2) {
        this.f1860d.loadUrl("javascript:{\nvar functionItme = " + str + ";\nfunctionItme(" + str2 + ");\n}");
    }

    public boolean addViewEventItme(StringBuffer stringBuffer, String str, int i2, String str2, String str3) {
        if (!str.contains("<eventItme type=\"" + str2 + "\">")) {
            return false;
        }
        String c2 = c.b.a.a.p.c(str, "<eventItme type=\"" + str2 + "\">", "</eventItme>");
        if (c2 == null) {
            return false;
        }
        stringBuffer.append("function " + str2 + i2 + "(" + str3 + "){\n" + c2 + "\n}\n");
        return true;
    }

    @JavascriptInterface
    public String addv(String str, String str2) {
        Object x2 = x(str);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 != null) {
            if (r2 instanceof ViewPager) {
                ViewPager viewPager = (ViewPager) r2;
                ArrayList arrayList = new ArrayList();
                String[] Q = Q(str2, '|');
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                for (String str3 : Q) {
                    String trim = str3.trim();
                    if (trim.endsWith(".ijs")) {
                        LinearLayout linearLayout = new LinearLayout(this.f1859c);
                        linearLayout.setLayoutParams(layoutParams);
                        linearLayout.setOrientation(1);
                        K(trim, linearLayout, m(trim.substring(0, trim.length() - 5)), null);
                        arrayList.add(linearLayout);
                    }
                }
                this.f.post(new w(this, viewPager, arrayList));
                return N("addv", arrayList);
            }
            if ((r2 instanceof DrawerLayout) || (r2 instanceof ViewGroup)) {
                ViewGroup viewGroup = (ViewGroup) r2;
                for (String str4 : Q(str2, '|')) {
                    String trim2 = str4.trim();
                    if (trim2.endsWith(".ijs")) {
                        this.f.post(new x(trim2, viewGroup));
                    }
                }
            }
        }
        return null;
    }

    @JavascriptInterface
    public String aslist(String str, String str2) {
        Object x2 = x(str);
        ArrayList arrayList = x2 == null ? new ArrayList() : (ArrayList) x2;
        String[] F = F(str2);
        if (F != null) {
            for (String str3 : F) {
                arrayList.add(str3);
            }
        }
        return (str == null || !str.startsWith("^")) ? N("aslist", arrayList) : main2.set(str, arrayList);
    }

    @JavascriptInterface
    public String aslist(String str, String str2, int i2) {
        Object x2 = x(str);
        ArrayList arrayList = x2 == null ? new ArrayList() : (ArrayList) x2;
        String[] F = F(str2);
        if (F != null) {
            for (String str3 : F) {
                arrayList.add(i2, str3);
            }
        }
        return (str == null || !str.startsWith("^")) ? N("aslist", arrayList) : main2.set(str, arrayList);
    }

    @JavascriptInterface
    public String bfm(String str) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        if (c.b.a.a.f.d(this.f1859c, mediaPlayer, str)) {
            return N("bfm", mediaPlayer);
        }
        return null;
    }

    @JavascriptInterface
    public String bfms(String str, String str2) {
        Object x2 = x(str);
        if (!(x2 instanceof MediaPlayer)) {
            return null;
        }
        MediaPlayer mediaPlayer = (MediaPlayer) x2;
        if (str2 == null) {
            return null;
        }
        try {
            if (str2.equals("st")) {
                mediaPlayer.start();
            } else if (str2.equals("pe")) {
                mediaPlayer.pause();
            } else {
                if (str2.equals("sp")) {
                    mediaPlayer.stop();
                    return null;
                }
                if (!str2.equals("re")) {
                    if (str2.equals("ip")) {
                        try {
                            return String.valueOf(mediaPlayer.isPlaying());
                        } catch (IllegalStateException unused) {
                            return String.valueOf(false);
                        }
                    }
                    if (str2.equals("dn")) {
                        return String.valueOf(mediaPlayer.getDuration());
                    }
                    if (str2.equals("cn")) {
                        return String.valueOf(mediaPlayer.getCurrentPosition());
                    }
                    try {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    } catch (Exception unused2) {
                    }
                    c.b.a.a.f.d(this.f1859c, mediaPlayer, str2);
                    return null;
                }
                mediaPlayer.release();
            }
            return null;
        } catch (IllegalStateException | Exception unused3) {
            return null;
        }
    }

    @JavascriptInterface
    public String bfms(String str, String str2, int i2, int i3) {
        Object x2 = x(str);
        if (!(x2 instanceof MediaPlayer)) {
            return null;
        }
        MediaPlayer mediaPlayer = (MediaPlayer) x2;
        if (str2 == null || !str2.equals("volume")) {
            return null;
        }
        mediaPlayer.setVolume(i2, i3);
        return null;
    }

    @JavascriptInterface
    public String bfms(String str, String str2, String str3) {
        Object x2 = x(str);
        if (!(x2 instanceof MediaPlayer)) {
            return null;
        }
        MediaPlayer mediaPlayer = (MediaPlayer) x2;
        if (str2 == null) {
            return null;
        }
        if (str2.equals("seekto")) {
            mediaPlayer.seekTo(Integer.parseInt(str3));
            return null;
        }
        if (!str2.equals("sl")) {
            return null;
        }
        mediaPlayer.setLooping(str3.equals("true"));
        return null;
    }

    @JavascriptInterface
    public void bfs(String str, int i2, String str2) {
        Object x2 = x(str);
        if (x2 instanceof Bitmap) {
            c.b.a.a.i.h((Bitmap) x2, i2, C(str2));
        }
    }

    @JavascriptInterface
    public void bfs(String str, String str2) {
        Object x2 = x(str);
        if (x2 instanceof Bitmap) {
            c.b.a.a.i.h((Bitmap) x2, 100, C(str2));
        }
    }

    @JavascriptInterface
    public void bfv(String str) {
        Intent intent = new Intent(this.f1859c, (Class<?>) Videoview.class);
        Bundle bundle = new Bundle();
        bundle.putString("video", str);
        bundle.putBoolean("sfhp", false);
        intent.putExtras(bundle);
        this.f1859c.startActivity(intent);
    }

    @JavascriptInterface
    public void bfv(String str, boolean z2) {
        Intent intent = new Intent(this.f1859c, (Class<?>) Videoview.class);
        Bundle bundle = new Bundle();
        bundle.putString("video", str);
        bundle.putBoolean("sfhp", z2);
        intent.putExtras(bundle);
        this.f1859c.startActivity(intent);
    }

    @JavascriptInterface
    public void bfvs(String str, String str2) {
        Uri c2;
        Object x2 = x(str);
        Object x3 = x(str2);
        if (!(x2 instanceof VideoView)) {
            x2 = r(x2, String.valueOf(x2));
        }
        VideoView videoView = (VideoView) x2;
        if (x3 instanceof Uri) {
            c2 = (Uri) x3;
        } else {
            String valueOf = String.valueOf(x3);
            if (!c.b.a.a.p.v(valueOf.toLowerCase())) {
                videoView.setVideoPath(C(valueOf));
                return;
            }
            c2 = c.b.a.a.k.c(this.f1859c, valueOf);
        }
        videoView.setVideoURI(c2);
    }

    @JavascriptInterface
    public String bfvss(String str, String str2) {
        Object x2 = x(str);
        if (!(x2 instanceof VideoView)) {
            x2 = r(x2, String.valueOf(x2));
        }
        VideoView videoView = (VideoView) x2;
        if (str2.equals("st")) {
            videoView.requestFocus();
            videoView.start();
            return null;
        }
        if (str2.equals("pe")) {
            videoView.pause();
            return null;
        }
        if (str2.equals("sp")) {
            videoView.stopPlayback();
            return null;
        }
        if (str2.equals("media")) {
            MediaController mediaController = new MediaController(this.f1859c);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            return I("bfvss", mediaController);
        }
        if (str2.equals("ip")) {
            try {
                return String.valueOf(videoView.isPlaying());
            } catch (Exception unused) {
                return "false";
            }
        }
        if (str2.equals("dn")) {
            return String.valueOf(videoView.getDuration());
        }
        if (str2.equals("cn")) {
            return String.valueOf(videoView.getCurrentPosition());
        }
        return null;
    }

    @JavascriptInterface
    public String bfvss(String str, String str2, int i2) {
        Object x2 = x(str);
        if (!(x2 instanceof VideoView)) {
            x2 = r(x2, String.valueOf(x2));
        }
        VideoView videoView = (VideoView) x2;
        if (!str2.equals("seekto")) {
            return null;
        }
        videoView.seekTo(i2);
        return null;
    }

    @JavascriptInterface
    public void blp(String str, int i2, int i3, int i4, int i5) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        com.iapp.app.p pVar = com.iapp.app.p.n;
        if (pVar != null) {
            pVar.j(C(str), i2, i3, i4, i5);
            return;
        }
        com.iapp.app.p pVar2 = new com.iapp.app.p(this.b);
        com.iapp.app.p.n = pVar2;
        pVar2.n(C(str), i2, i3, i4, i5);
    }

    @JavascriptInterface
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

    @JavascriptInterface
    public String bly(String str, String str2) {
        Object x2 = x(str);
        if (str2.equals("sp")) {
            if (x2 == null) {
                return null;
            }
            MediaRecorder mediaRecorder = (MediaRecorder) x2;
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
        String C = C(str2);
        c.b.a.a.d.b(C, false);
        mediaRecorder2.setOutputFile(C);
        mediaRecorder2.setAudioEncoder(0);
        try {
            mediaRecorder2.prepare();
            mediaRecorder2.start();
            return N("bly", mediaRecorder2);
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    @JavascriptInterface
    public String btoo(String str, String str2) {
        byte[] bArr;
        try {
            bArr = n(str2.trim(), ' ');
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

    @JavascriptInterface
    public void btoo(String str, String str2, boolean z2) {
        byte[] bArr;
        try {
            bArr = n(str2.trim(), ' ');
        } catch (Exception unused) {
            bArr = null;
        }
        if (bArr == null) {
            return;
        }
        String C = C(str);
        File file = new File(C);
        if (z2 || !file.exists()) {
            c.b.a.a.d.j(C, bArr);
        }
    }

    @JavascriptInterface
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
                P(substring + "myu");
                obj = c.b.a.a.t.l.get(str2 + "0");
            }
            String obj2 = obj.toString();
            c.b.a.a.f.E(this.f1859c, this.b, str2, obj2.substring(obj2.indexOf(10)).trim());
        } else if (str.equals("mjava")) {
            com.iapp.app.j jVar = new com.iapp.app.j(this.f1859c);
            try {
                jVar.set("activity", this.f1859c);
                jVar.set("i", new Aid_javaCode(this.f1859c, this.b, jVar));
            } catch (EvalError e2) {
                e2.printStackTrace();
            }
            int indexOf = str2.indexOf(46);
            if (jVar.e(str2.substring(0, indexOf) + ".mjava")) {
                return jVar.a(str2.substring(indexOf + 1));
            }
        } else if (str.equals("mlua")) {
            String str3 = "$_Call_SsS_" + Thread.currentThread().getId() + "_return";
            String str4 = "require 'import'\nrequire '" + str2.substring(0, str2.indexOf(46)) + "'\n\nlocal returns = " + str2.substring(str2.indexOf(46) + 1) + "()\ni:sss(\"" + str3 + "\", returns)";
            com.iapp.app.d dVar = new com.iapp.app.d(this.f1859c);
            dVar.l("activity", this.f1859c);
            dVar.l("i", new Aid_luaCode(this.f1859c, this.b, dVar));
            dVar.k();
            try {
                dVar.h(str4);
                return I("zj", c.b.a.a.t.j.get(str3));
            } catch (LuaException e3) {
                e3.printStackTrace();
                c.b.a.a.t.P2(this.f1859c, "LuaErr：\n" + e3.getMessage());
            }
        } else if (str.equals("mjs")) {
            StringBuilder sb = new StringBuilder();
            sb.append("<html><head><script type='text/javascript'>");
            sb.append(c.b.a.a.f.e(this.f1859c, "import.mjs"));
            sb.append("\n");
            sb.append(c.b.a.a.f.a(this.f1859c, str2.substring(0, str2.indexOf(46)) + ".mjs"));
            sb.append("\n\nvar returns = ");
            sb.append(str2.substring(str2.indexOf(46) + 1));
            sb.append("();</script></head></html>");
            String sb2 = sb.toString();
            WebView webView = new WebView(this.f1859c);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setAppCachePath(this.f1859c.getApplicationContext().getDir("cache", 0).getPath());
            webView.getSettings().setAppCacheMaxSize(8388608L);
            webView.getSettings().setDatabaseEnabled(true);
            webView.getSettings().setDatabasePath(this.f1859c.getApplicationContext().getDir("database", 0).getPath());
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setGeolocationEnabled(true);
            webView.getSettings().setLightTouchEnabled(true);
            webView.getSettings().setCacheMode(-1);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.setWebChromeClient(new m0());
            if (Build.VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
            }
            webView.addJavascriptInterface(new Aid_jsCode(this.f1859c, this.b, webView, 0), "I");
            com.iapp.app.c.d(webView, sb2);
        }
        return null;
    }

    @JavascriptInterface
    @SuppressLint({"NewApi"})
    public Object call(String str, String str2, String str3) {
        Object[] G = G(str3);
        int length = G.length;
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
                P(substring + "myu");
                obj = c.b.a.a.t.l.get(str2 + length);
            }
            String obj2 = obj.toString();
            int indexOf = obj2.indexOf(10);
            String[] Q = Q(R(obj2.substring(0, indexOf)), ',');
            if (length != Q.length) {
                return null;
            }
            c.b.a.a.f.F(this.f1859c, this.b, Q, G, str2, obj2.substring(indexOf).trim());
        } else {
            if (!str.equals("mjava")) {
                String str4 = "_a";
                if (str.equals("mlua")) {
                    long id = Thread.currentThread().getId();
                    String str5 = "";
                    String str6 = str5;
                    int i2 = 0;
                    while (i2 < length) {
                        String str7 = "$_Call_SsS_" + id + str4 + i2;
                        c.b.a.a.t.j.put(str7, G[i2]);
                        str6 = str6 + "local a" + i2 + " = i:sss(\"" + str7 + "\")\n";
                        str5 = str5 + ",a" + i2;
                        i2++;
                        str4 = str4;
                        length = length;
                    }
                    if (str5.length() > 0) {
                        str5 = str5.substring(1);
                    }
                    String str8 = "$_Call_SsS_" + id + "_return";
                    String str9 = "require 'import'\nrequire '" + str2.substring(0, str2.indexOf(46)) + "'\n" + str6 + "\nlocal returns = " + str2.substring(str2.indexOf(46) + 1) + "(" + str5 + ")\ni:sss(\"" + str8 + "\", returns)";
                    com.iapp.app.d dVar = new com.iapp.app.d(this.f1859c);
                    dVar.l("activity", this.f1859c);
                    dVar.l("i", new Aid_luaCode(this.f1859c, this.b, dVar));
                    dVar.k();
                    try {
                        dVar.h(str9);
                        return I("zj", c.b.a.a.t.j.get(str8));
                    } catch (LuaException e2) {
                        e2.printStackTrace();
                        c.b.a.a.t.P2(this.f1859c, "LuaErr：\n" + e2.getMessage());
                    }
                } else if (str.equals("mjs")) {
                    long id2 = Thread.currentThread().getId();
                    String str10 = "";
                    String str11 = str10;
                    for (int i3 = 0; i3 < length; i3++) {
                        String str12 = "$_Call_SsS_" + id2 + "_a" + i3;
                        c.b.a.a.t.j.put(str12, G[i3]);
                        str11 = str11 + "var a" + i3 + " = I.sss(\"" + str12 + "\");\n";
                        str10 = str10 + ",a" + i3;
                    }
                    if (str10.length() > 0) {
                        str10 = str10.substring(1);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("<html><head><script type='text/javascript'>");
                    sb.append(c.b.a.a.f.e(this.f1859c, "import.mjs"));
                    sb.append("\n");
                    sb.append(c.b.a.a.f.a(this.f1859c, str2.substring(0, str2.indexOf(46)) + ".mjs"));
                    sb.append("\n");
                    sb.append(str11);
                    sb.append("\nvar returns = ");
                    sb.append(str2.substring(str2.indexOf(46) + 1));
                    sb.append("(");
                    sb.append(str10);
                    sb.append(");</script></head></html>");
                    String sb2 = sb.toString();
                    WebView webView = new WebView(this.f1859c);
                    webView.getSettings().setAllowFileAccess(true);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setAppCacheEnabled(true);
                    webView.getSettings().setAppCachePath(this.f1859c.getApplicationContext().getDir("cache", 0).getPath());
                    webView.getSettings().setAppCacheMaxSize(8388608L);
                    webView.getSettings().setDatabaseEnabled(true);
                    webView.getSettings().setDatabasePath(this.f1859c.getApplicationContext().getDir("database", 0).getPath());
                    webView.getSettings().setDomStorageEnabled(true);
                    webView.getSettings().setGeolocationEnabled(true);
                    webView.getSettings().setLightTouchEnabled(true);
                    webView.getSettings().setCacheMode(-1);
                    webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                    webView.setWebChromeClient(new l0());
                    if (Build.VERSION.SDK_INT >= 11) {
                        webView.removeJavascriptInterface("searchBoxJavaBridge_");
                        webView.removeJavascriptInterface("accessibility");
                        webView.removeJavascriptInterface("accessibilityTraversal");
                    }
                    webView.addJavascriptInterface(new Aid_jsCode(this.f1859c, this.b, webView, 0), "I");
                    com.iapp.app.c.d(webView, sb2);
                }
                return null;
            }
            com.iapp.app.j jVar = new com.iapp.app.j(this.f1859c);
            try {
                jVar.set("activity", this.f1859c);
                jVar.set("i", new Aid_javaCode(this.f1859c, this.b, jVar));
            } catch (EvalError e3) {
                e3.printStackTrace();
            }
            int indexOf2 = str2.indexOf(46);
            if (jVar.e(str2.substring(0, indexOf2) + ".mjava")) {
                return jVar.b(str2.substring(indexOf2 + 1), G);
            }
        }
        return null;
    }

    public void callMethod(String str, String str2) {
        this.f1860d.loadUrl("javascript:" + str + "(" + str2 + ")");
    }

    public void clear_s_dim() {
        this.a.clear();
    }

    @JavascriptInterface
    public String cls(String str) {
        return N("cls", c.b.a.a.b.b(str));
    }

    @JavascriptInterface
    public String cls(String str, String str2) {
        try {
            return N("cls", ((ClassLoader) x(str)).loadClass(str2));
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @JavascriptInterface
    public String clssm(String str, String str2) {
        Class cls = (Class) x(str);
        if (str2.equals("init")) {
            return N("clssm", cls.getDeclaredConstructors());
        }
        if (str2.equals("method")) {
            return N("clssm", cls.getDeclaredMethods());
        }
        if (str2.equals("field")) {
            return N("clssm", cls.getDeclaredFields());
        }
        return null;
    }

    @JavascriptInterface
    public String dh(String str, String str2) {
        Object x2 = x(str);
        if (x2 instanceof c.d.a.c) {
            c.d.a.c cVar = (c.d.a.c) x2;
            if (str2.equals("cancel")) {
                cVar.b();
                return null;
            }
            if (str2.equals("clone")) {
                return N("dh", cVar.clone());
            }
            if (str2.equals("start")) {
                cVar.j();
                return null;
            }
            if (str2.equals("running")) {
                return String.valueOf(cVar.e());
            }
            return null;
        }
        if (x2 instanceof AnimationSet) {
            AnimationSet animationSet = (AnimationSet) x2;
            if (str2.equals("cancel")) {
                animationSet.cancel();
                return null;
            }
            if (str2.equals("reset")) {
                animationSet.reset();
                return null;
            }
            if (!str2.equals("start")) {
                return null;
            }
            animationSet.start();
            return null;
        }
        if (x2 instanceof Animation) {
            Animation animation = (Animation) x2;
            if (str2.equals("cancel")) {
                animation.cancel();
                return null;
            }
            if (str2.equals("reset")) {
                animation.reset();
                return null;
            }
            if (!str2.equals("start")) {
                return null;
            }
            animation.start();
            return null;
        }
        if (!(x2 instanceof c.d.a.a)) {
            return null;
        }
        c.d.a.a aVar = (c.d.a.a) x2;
        if (str2.equals("cancel")) {
            aVar.b();
            return null;
        }
        if (str2.equals("clone")) {
            return N("dh", aVar.clone());
        }
        if (str2.equals("start")) {
            aVar.j();
            return null;
        }
        if (str2.equals("running")) {
            return String.valueOf(aVar.e());
        }
        return null;
    }

    @JavascriptInterface
    public String dh(String str, String str2, String str3) {
        Object x2 = x(str);
        if (x2 instanceof Animation) {
            Animation animation = (Animation) x2;
            if (str2.equals("duration")) {
                animation.setDuration(Long.parseLong(str3));
                return null;
            }
            if (str2.equals("delay")) {
                animation.setStartOffset(Long.parseLong(str3));
                return null;
            }
            if (str2.equals("enabled")) {
                animation.setFillEnabled(str3.equals("true"));
                return null;
            }
            if (str2.equals("after")) {
                animation.setFillAfter(str3.equals("true"));
                return null;
            }
            if (str2.equals("before")) {
                animation.setFillBefore(str3.equals("true"));
                return null;
            }
            if (!str2.equals("repeat")) {
                return null;
            }
            animation.setRepeatCount(Integer.parseInt(str3));
            return null;
        }
        if (x2 instanceof c.d.a.a) {
            c.d.a.a aVar = (c.d.a.a) x2;
            if (str2.equals("duration")) {
                aVar.g(Long.parseLong(str3));
                return null;
            }
            if (str2.equals("delay")) {
                aVar.h(Long.parseLong(str3));
                return null;
            }
            if (!str2.equals("target")) {
                return null;
            }
            Object x3 = x(str3);
            aVar.i(x3 instanceof View ? (View) x3 : r(x3, String.valueOf(x3)));
            return null;
        }
        if (!(x2 instanceof AnimationSet)) {
            if (!(x2 instanceof c.d.a.c)) {
                return null;
            }
            c.d.a.c cVar = (c.d.a.c) x2;
            if (str2.equals("duration")) {
                cVar.u(Long.parseLong(str3));
                return null;
            }
            if (str2.equals("delay")) {
                cVar.h(Long.parseLong(str3));
                return null;
            }
            if (!str2.equals("target")) {
                return null;
            }
            Object x4 = x(str3);
            cVar.i(x4 instanceof View ? (View) x4 : r(x4, String.valueOf(x4)));
            return null;
        }
        AnimationSet animationSet = (AnimationSet) x2;
        if (str2.equals("duration")) {
            animationSet.setDuration(Long.parseLong(str3));
            return null;
        }
        if (str2.equals("enabled")) {
            animationSet.setFillEnabled(str3.equals("true"));
            return null;
        }
        if (str2.equals("after")) {
            animationSet.setFillAfter(str3.equals("true"));
            return null;
        }
        if (str2.equals("before")) {
            animationSet.setFillBefore(str3.equals("true"));
            return null;
        }
        if (str2.equals("delay")) {
            animationSet.setStartOffset(Long.parseLong(str3));
            return null;
        }
        if (str2.equals("repeat")) {
            animationSet.setRepeatCount(Integer.parseInt(str3));
            return null;
        }
        if (!str2.equals("add")) {
            return null;
        }
        animationSet.addAnimation((Animation) x(str3));
        return null;
    }

    @JavascriptInterface
    public String dha(boolean z2, boolean z3) {
        return N("dha", new AlphaAnimation(z2 ? 1.0f : 0.0f, z3 ? 1.0f : 0.0f));
    }

    @JavascriptInterface
    public String dhas(String str, String str2, String str3) {
        Object x2 = x(str);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        String[] F = F(str3);
        int length = F.length;
        float[] fArr = new float[length];
        for (int i2 = 0; i2 < length; i2++) {
            fArr[i2] = Float.parseFloat(F[i2]);
        }
        return N("dhas", c.d.a.i.K(r2, str2, fArr));
    }

    @JavascriptInterface
    public String dhast(String str, String str2) {
        String[] F = F(str2);
        int length = F.length;
        c.d.a.a[] aVarArr = new c.d.a.a[length];
        for (int i2 = 0; i2 < length; i2++) {
            Object x2 = x(F[i2]);
            if (x2 instanceof c.d.a.a) {
                aVarArr[i2] = (c.d.a.a) x2;
            }
        }
        c.d.a.c cVar = new c.d.a.c();
        if (str.equals("sequen")) {
            cVar.s(aVarArr);
        } else if (str.equals("together")) {
            cVar.t(aVarArr);
        }
        return N("dhast", cVar);
    }

    @JavascriptInterface
    public String dhb(String str, String str2) {
        AnimationDrawable animationDrawable = (AnimationDrawable) x(str);
        if (str2.equals("start")) {
            animationDrawable.start();
            return null;
        }
        if (str2.equals("stop")) {
            animationDrawable.stop();
            return null;
        }
        if (str2.equals("running")) {
            return String.valueOf(animationDrawable.isRunning());
        }
        return null;
    }

    @JavascriptInterface
    public String dhb(boolean z2) {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        animationDrawable.setOneShot(z2);
        return N("dhb", animationDrawable);
    }

    @JavascriptInterface
    public void dhb(String str, String str2, int i2) {
        AnimationDrawable animationDrawable = (AnimationDrawable) x(str);
        Object x2 = x(str2);
        animationDrawable.addFrame(x2 instanceof Drawable ? (Drawable) x2 : x2 instanceof Bitmap ? new BitmapDrawable((Bitmap) x2) : com.iapp.app.i.m(String.valueOf(x2), this.f1859c), i2);
    }

    @JavascriptInterface
    public void dhon(String str, String str2, String str3, String str4) {
        ((Animation) x(str)).setAnimationListener(new a0(str2, str3, str4));
    }

    @JavascriptInterface
    public void dhon(String str, String str2, String str3, String str4, String str5) {
        ((c.d.a.a) x(str)).a(new b0(str2, str3, str4, str5));
    }

    @JavascriptInterface
    public String dhr(float f2, float f3) {
        return N("dhr", new RotateAnimation(f2, f3));
    }

    @JavascriptInterface
    public String dhr(float f2, float f3, int i2, float f4, int i3, float f5) {
        return N("dhr", new RotateAnimation(f2, f3, i2, f4, i3, f5));
    }

    @JavascriptInterface
    public String dhs(float f2, float f3, float f4, float f5) {
        return N("dhs", new ScaleAnimation(f2, f3, f4, f5));
    }

    @JavascriptInterface
    public String dhs(float f2, float f3, float f4, float f5, int i2, float f6, int i3, float f7) {
        return N("dhs", new ScaleAnimation(f2, f3, f4, f5, i2, f6, i3, f7));
    }

    @JavascriptInterface
    public String dhset(String str, String str2) {
        String[] F = F(str2);
        Object x2 = x(str);
        AnimationSet animationSet = x2 instanceof AnimationSet ? (AnimationSet) x2 : new AnimationSet(str.equals("true"));
        for (String str3 : F) {
            Object x3 = x(str3);
            if (x3 instanceof Animation) {
                animationSet.addAnimation((Animation) x3);
            }
        }
        return N("dhset", animationSet);
    }

    @JavascriptInterface
    public String dht(float f2, float f3, float f4, float f5) {
        return N("dht", new TranslateAnimation(f2, f3, f4, f5));
    }

    public void dim(String str, Object obj) {
        int indexOf = str.indexOf(46);
        if (indexOf != -1) {
            String substring = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
            if (substring.equals("ss")) {
                p(str, obj, 1);
                return;
            } else if (substring.equals("sss")) {
                p(str, obj, 2);
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

    @JavascriptInterface
    public void dslist(String str, String str2) {
        ArrayList arrayList = (ArrayList) x(str);
        Object x2 = x(str2);
        if (!(x2 instanceof Integer) && !(x2 instanceof Double)) {
            arrayList.remove(x2);
            return;
        }
        int parseInt = Integer.parseInt(String.valueOf(x2));
        if (parseInt == -1) {
            arrayList.clear();
        } else {
            arrayList.remove(parseInt);
        }
    }

    @JavascriptInterface
    public void end() {
        this.b.finish();
    }

    @JavascriptInterface
    public void endkeyboard() {
        View peekDecorView = this.b.getWindow().peekDecorView();
        if (peekDecorView != null) {
            ((InputMethodManager) this.f1859c.getSystemService("input_method")).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }

    @JavascriptInterface
    public void ends() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addFlags(270532608);
        this.f1859c.startActivity(intent);
    }

    @JavascriptInterface
    public void endutw() {
        AlertDialog alertDialog = alertdialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            alertdialog = null;
        }
    }

    @JavascriptInterface
    public boolean fc(String str, String str2) {
        return c.b.a.a.f.f(this.f1859c, str, str2, true);
    }

    @JavascriptInterface
    public boolean fc(String str, String str2, boolean z2) {
        return c.b.a.a.f.f(this.f1859c, str, str2, z2);
    }

    @JavascriptInterface
    public boolean fd(String str) {
        File file = new File(C(str));
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    @JavascriptInterface
    public String fdir() {
        return c.b.a.a.d.m(this.f1859c);
    }

    @JavascriptInterface
    public String fdir(String str) {
        return C(str);
    }

    @JavascriptInterface
    public boolean fe(String str) {
        return c.b.a.a.f.g(this.f1859c, str);
    }

    @JavascriptInterface
    public boolean fi(String str) {
        return new File(C(str)).isDirectory();
    }

    @JavascriptInterface
    public boolean fj(String str, String str2) {
        String C = C(str2);
        c.b.a.a.d.b(C, false);
        try {
            c.b.a.a.c.c(C(str), C, true);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @JavascriptInterface
    public boolean fj(String str, String str2, boolean z2) {
        String C = C(str2);
        c.b.a.a.d.b(C, false);
        try {
            c.b.a.a.c.c(C(str), C, z2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @JavascriptInterface
    public String fl(String str) {
        return M(c.b.a.a.f.h(this.f1859c, str));
    }

    @JavascriptInterface
    public String fl(String str, boolean z2) {
        File[] listFiles;
        File file = new File(C(str));
        if (!file.exists() || (listFiles = file.listFiles()) == null) {
            return null;
        }
        StringBuffer stringBuffer = null;
        for (File file2 : listFiles) {
            String name = (!z2 ? file2.isFile() : file2.isDirectory()) ? null : file2.getName();
            if (name != null) {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer();
                } else {
                    stringBuffer.append("\n\\\r");
                }
                stringBuffer.append(name);
            }
        }
        if (stringBuffer != null) {
            return stringBuffer.toString();
        }
        return null;
    }

    @JavascriptInterface
    public void fo(String str) {
        String C = C(str);
        File file = new File(C);
        if (file.exists()) {
            if (C.toLowerCase().endsWith(".apk")) {
                c.b.a.a.d.d(this.f1859c, C);
            } else {
                try {
                    J(file);
                } catch (Exception unused) {
                }
            }
        }
    }

    @JavascriptInterface
    public String fr(String str) {
        return c.b.a.a.f.i(this.f1859c, str);
    }

    @JavascriptInterface
    public String fr(String str, String str2) {
        return c.b.a.a.f.j(this.f1859c, str, str2);
    }

    @JavascriptInterface
    public long fs(String str) {
        return c.b.a.a.f.k(this.f1859c, str);
    }

    @JavascriptInterface
    public boolean ft(String str, String str2) {
        File file = new File(C(str));
        if (!file.exists()) {
            return false;
        }
        String C = C(str2);
        c.b.a.a.d.b(C, false);
        return file.renameTo(new File(C));
    }

    @JavascriptInterface
    public void ftz(String str, String str2, String str3, String str4, String str5) {
        c.b.a.a.r.b(this.f1859c, str, str2, str3, x(str4), new Intent().setClass(this.f1859c, logoActivity.class).putExtra("command2", str5));
    }

    @JavascriptInterface
    public int fuz(String str, String str2, String str3) {
        String C = C(str3);
        c.b.a.a.d.b(C, false);
        try {
            return c.b.a.a.f.l(this.f1859c, str, str2, C, true);
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @JavascriptInterface
    public int fuz(String str, String str2, String str3, boolean z2) {
        String C = C(str3);
        c.b.a.a.d.b(C, false);
        try {
            return c.b.a.a.f.l(this.f1859c, str, str2, C, z2);
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @JavascriptInterface
    public boolean fuzs(String str, String str2) {
        String C = C(str2);
        c.b.a.a.d.b(C, false);
        try {
            c.b.a.a.f.m(this.f1859c, str, C, true);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @JavascriptInterface
    public boolean fuzs(String str, String str2, boolean z2) {
        String C = C(str2);
        c.b.a.a.d.b(C, false);
        try {
            c.b.a.a.f.m(this.f1859c, str, C, z2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @JavascriptInterface
    public void fw(String str, String str2) {
        c.b.a.a.d.k(C(str), str2);
    }

    @JavascriptInterface
    public void fw(String str, String str2, String str3) {
        c.b.a.a.d.l(C(str), str2, str3);
    }

    @JavascriptInterface
    public String getjs(String str) {
        return c.b.a.a.f.a(this.f1859c, str + ".mjs");
    }

    @JavascriptInterface
    public String git(String str, String str2) {
        Object x2 = x(str);
        Intent intent = x2 instanceof Intent ? (Intent) x2 : null;
        if (intent == null) {
            return null;
        }
        if (str2.equals("action")) {
            return intent.getAction();
        }
        if (str2.equals("type")) {
            return intent.getType();
        }
        if (str2.equals("flags")) {
            return String.valueOf(intent.getFlags());
        }
        if (str2.equals("data")) {
            return N("git", intent.getData());
        }
        if (str2.equals("datastring")) {
            return intent.getDataString();
        }
        if (str2.equals("component")) {
            return N("git", intent.getComponent());
        }
        return null;
    }

    @JavascriptInterface
    public String git(String str, String str2, String str3) {
        Object x2 = x(str);
        Intent intent = x2 instanceof Intent ? (Intent) x2 : null;
        if (intent != null && str2.equals("extra")) {
            return I("git", intent.getExtras().get(str3));
        }
        return null;
    }

    @JavascriptInterface
    public String gslist(String str, int i2) {
        return I("gslist", ((ArrayList) x(str)).get(i2));
    }

    @JavascriptInterface
    public int gslistiof(String str, String str2) {
        return ((ArrayList) x(str)).indexOf(x(str2));
    }

    @JavascriptInterface
    public boolean gslistis(String str, String str2) {
        return ((ArrayList) x(str)).contains(x(str2));
    }

    @JavascriptInterface
    public int gslistl(String str) {
        return ((ArrayList) x(str)).size();
    }

    @JavascriptInterface
    public int gslistlof(String str, String str2) {
        return ((ArrayList) x(str)).lastIndexOf(x(str2));
    }

    @JavascriptInterface
    public String gslistsz(String str) {
        return M(((ArrayList) x(str)).toArray());
    }

    @JavascriptInterface
    public String gvs(String str) {
        Object x2 = x(str);
        return N("gvs", r(x2, String.valueOf(x2)));
    }

    @JavascriptInterface
    public String gvs(String str, String str2) {
        Object x2 = x(str);
        Object x3 = x(str2);
        return N("gvs", x3 instanceof View ? (View) x3 : q(x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2)), x3, String.valueOf(x3)));
    }

    @JavascriptInterface
    public void has(String str, String str2) {
        Object x2 = x(str);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 instanceof com.iapp.app.x5.WebView) {
            ((com.iapp.app.x5.WebView) r2).loadUrl("javascript:{" + str2 + "};");
        }
    }

    @JavascriptInterface
    public int hd(String str, String str2) {
        return c.b.a.a.g.a(str, C(str2), false);
    }

    @JavascriptInterface
    public int hd(String str, String str2, boolean z2) {
        return c.b.a.a.g.a(str, C(str2), z2);
    }

    @JavascriptInterface
    public int hd(String str, String str2, boolean z2, String str3, String str4, String str5, boolean z3, String str6) {
        return c.b.a.a.g.b(str, C(str2), z2, str3, str4, str5, z3, str6);
    }

    @JavascriptInterface
    public void hdd(String str, String str2, int i2, int i3, int i4, int i5, boolean z2) {
        com.iapp.app.a.b.m(C(str), C(str2), i2, i3, i4, i5, z2);
    }

    @JavascriptInterface
    public String hdda(String str, String str2, String str3) {
        return N("hdda", com.iapp.app.a.b.d(str, str2, x(str3)));
    }

    @JavascriptInterface
    public String hdda(String str, String str2, String str3, String str4) {
        return N("hdda", com.iapp.app.a.b.e(str, str2, str3, x(str4)));
    }

    @JavascriptInterface
    public String hdda(String str, String str2, String str3, String str4, String str5) {
        return N("hdda", com.iapp.app.a.b.f(str, str2, str3, x(str4), x(str5)));
    }

    @JavascriptInterface
    public String hdda(String str, String str2, String str3, String str4, String str5, boolean z2, String str6) {
        return N("hdda", com.iapp.app.a.b.g(str, C(str2), str3, str4, x(str5), z2, x(str6)));
    }

    @JavascriptInterface
    public String hddg(String str, String str2) {
        try {
            return I("hddg", com.iapp.app.a.b.h(com.iapp.app.a.b.f1379c.get(Integer.parseInt(str)), str2));
        } catch (NumberFormatException unused) {
            return I("hddg", com.iapp.app.a.b.h((c.b.a.a.w.c) x(str), str2));
        }
    }

    @JavascriptInterface
    public String hddgl() {
        return N("hddgl", com.iapp.app.a.b.f1379c);
    }

    @JavascriptInterface
    public void hdds(String str, String str2, String str3) {
        Object x2 = x(str3);
        try {
            com.iapp.app.a.b.l(com.iapp.app.a.b.f1379c.get(Integer.parseInt(str3)), str2, x2);
        } catch (NumberFormatException unused) {
            com.iapp.app.a.b.l((c.b.a.a.w.c) x(str), str2, x2);
        }
    }

    @JavascriptInterface
    public void hdduigo() {
        this.f1859c.startActivity(new Intent().setClass(this.f1859c, DownList.class));
    }

    @JavascriptInterface
    public void hdduigo(String str, String str2) {
        Intent intent = new Intent(this.f1859c, (Class<?>) DownList.class);
        Bundle bundle = new Bundle();
        bundle.putString("background", str);
        bundle.putString("backgroundShadow", str2);
        intent.putExtras(bundle);
        this.f1859c.startActivity(intent);
    }

    @JavascriptInterface
    public String hdfl(String str, String str2, int i2, int i3, boolean z2, String str3, String str4) {
        c.b.a.a.e eVar = new c.b.a.a.e(C(str), C(str2), i2, i3, z2);
        H(eVar, str3, str4);
        return N("hdfl", eVar);
    }

    @JavascriptInterface
    public String hdfl(String str, String str2, String str3) {
        c.b.a.a.e eVar = new c.b.a.a.e(C(str));
        H(eVar, str2, str3);
        return N("hdfl", eVar);
    }

    @JavascriptInterface
    public String hdfl(String str, String str2, String str3, String str4) {
        c.b.a.a.e eVar = new c.b.a.a.e(C(str), C(str2));
        H(eVar, str3, str4);
        return N("hdfl", eVar);
    }

    @JavascriptInterface
    public void hdfla(String str, String str2, int i2, String str3) {
        c.b.a.a.e eVar = (c.b.a.a.e) x(str);
        eVar.n(str2, i2, x(str3));
        eVar.t();
    }

    @JavascriptInterface
    public void hdfla(String str, String str2, int i2, String str3, String str4) {
        c.b.a.a.e eVar = (c.b.a.a.e) x(str);
        eVar.o(str2, i2, x(str3), C(str4));
        eVar.t();
    }

    @JavascriptInterface
    public String hs(String str) {
        return (str.equals("cookie") || str.equals("del cookie")) ? c.b.a.a.g.d(str) : c.b.a.a.g.e(str, null, null);
    }

    @JavascriptInterface
    public String hs(String str, String str2, String str3) {
        return c.b.a.a.g.e(str, str2, str3);
    }

    @JavascriptInterface
    public String hs(String str, String str2, String str3, String str4) {
        return c.b.a.a.g.f(str, str2, str3, str4);
    }

    @JavascriptInterface
    public String hs(String str, String str2, String str3, String str4, boolean z2) {
        return c.b.a.a.g.g(str, str2, str3, str4, z2);
    }

    @JavascriptInterface
    public String hs(String str, String str2, String str3, String str4, boolean z2, String str5) {
        return c.b.a.a.g.h(str, str2, str3, str4, z2, str5, 20000, 20000, null);
    }

    @JavascriptInterface
    public String hs(String str, String str2, String str3, String str4, boolean z2, String str5, int i2, int i3, String str6) {
        return c.b.a.a.g.h(str, str2, str3, str4, z2, str5, i2, i3, str6);
    }

    @JavascriptInterface
    @TargetApi(11)
    public void hsas(String str, boolean z2) {
        Object x2 = x(str);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 instanceof com.iapp.app.x5.WebView) {
            com.iapp.app.x5.WebView webView = (com.iapp.app.x5.WebView) r2;
            if (Build.VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface("iapp");
            }
            if (z2) {
                webView.addJavascriptInterface(new iapp(this.f1859c, this.b), "iapp");
            }
        }
    }

    @JavascriptInterface
    public String huf(String str, String str2, String str3, String str4) {
        try {
            return c.b.a.a.g.m(this.f1859c, str, str2, str3, str4);
        } catch (Exception unused) {
            return null;
        }
    }

    @JavascriptInterface
    public String huf(String str, String str2, String str3, String str4, String str5) {
        try {
            return c.b.a.a.g.n(this.f1859c, str, str2, str3, str4, str5);
        } catch (Exception unused) {
            return null;
        }
    }

    @JavascriptInterface
    public void hw(String str) {
        Intent intent = new Intent(this.f1859c, (Class<?>) Webview.class);
        Bundle bundle = new Bundle();
        bundle.putString("WebURL", str);
        intent.putExtras(bundle);
        this.f1859c.startActivity(intent);
    }

    @JavascriptInterface
    public void hw(String str, String str2, String str3) {
        Intent intent = new Intent(this.f1859c, (Class<?>) Webview.class);
        Bundle bundle = new Bundle();
        bundle.putString("WebURL", str);
        bundle.putString("background", str2);
        bundle.putString("backgroundShadow", str3);
        intent.putExtras(bundle);
        this.f1859c.startActivity(intent);
    }

    @JavascriptInterface
    public void hws(String str) {
        Intent intent;
        try {
            intent = c.b.a.a.g.q(this.f1859c, str);
        } catch (Exception unused) {
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.VIEW");
            intent2.setData(c.b.a.a.k.c(this.f1859c, str));
            intent = intent2;
        }
        this.f1859c.startActivity(intent);
    }

    @JavascriptInterface
    public String java(String str, String str2) {
        return I("java", c.b.a.a.b.g(this.f1859c, x(str), str2, null, null, null));
    }

    @JavascriptInterface
    public String java(String str, String str2, String str3) {
        return I("java", c.b.a.a.b.g(this.f1859c, x(str), str2, G(str3), null, null));
    }

    @JavascriptInterface
    public String java(String str, String str2, String str3, String str4) {
        return I("java", c.b.a.a.b.g(this.f1859c, x(str), str2, G(str3), this.f1860d, str4));
    }

    @JavascriptInterface
    public String javacb(String str, String str2) {
        Class cls = (Class) x(str);
        return N("javacb", c.b.a.a.b.o(cls.getClassLoader(), cls, this.f1860d, str2));
    }

    @JavascriptInterface
    public String javags(String str, String str2) {
        return I("javags", c.b.a.a.b.j(x(str), str2));
    }

    @JavascriptInterface
    public String javags(String str, String str2, String str3) {
        Object x2 = x(str);
        Object x3 = x(str2);
        return I("javags", x3 instanceof Class ? c.b.a.a.b.i(x2, (Class) x3, str3) : c.b.a.a.b.k(x2, x3.toString(), str3));
    }

    @JavascriptInterface
    public String javanew(String str) {
        Object x2 = x(str);
        return I("javanew", x2 instanceof Class ? c.b.a.a.b.m(this.f1859c, (Class) x2, null, null, null) : c.b.a.a.b.n(this.f1859c, x2.toString(), null, null, null));
    }

    @JavascriptInterface
    public String javanew(String str, String str2) {
        Object x2 = x(str);
        return I("javanew", x2 instanceof Class ? c.b.a.a.b.m(this.f1859c, (Class) x2, G(str2), null, null) : c.b.a.a.b.n(this.f1859c, x2.toString(), G(str2), null, null));
    }

    @JavascriptInterface
    public String javanew(String str, String str2, String str3) {
        Object x2 = x(str);
        return I("javanew", x2 instanceof Class ? c.b.a.a.b.m(this.f1859c, (Class) x2, G(str2), this.f1860d, str3) : c.b.a.a.b.n(this.f1859c, x2.toString(), G(str2), this.f1860d, str3));
    }

    @JavascriptInterface
    public String javass(String str, String str2, String str3) {
        return I("javass", c.b.a.a.b.p(x(str), str2, x(str3)));
    }

    @JavascriptInterface
    public String javass(String str, String str2, String str3, String str4) {
        Object x2 = x(str);
        Object x3 = x(str2);
        Object x4 = x(str4);
        return I("javass", Boolean.valueOf(x3 instanceof Class ? c.b.a.a.b.q(x2, (Class) x3, str3, x4) : c.b.a.a.b.r(x2, x3.toString(), str3, x4)));
    }

    @JavascriptInterface
    public String javax(String str, String str2, String str3) {
        Object x2 = x(str);
        Object x3 = x(str2);
        boolean z2 = x3 instanceof Class;
        Context context = this.f1859c;
        return I("javax", z2 ? c.b.a.a.b.e(context, x2, (Class) x3, str3, null, null, null) : c.b.a.a.b.f(context, x2, x3.toString(), str3, null, null, null));
    }

    @JavascriptInterface
    public String javax(String str, String str2, String str3, String str4) {
        Object x2 = x(str);
        Object x3 = x(str2);
        boolean z2 = x3 instanceof Class;
        Context context = this.f1859c;
        return I("javax", z2 ? c.b.a.a.b.e(context, x2, (Class) x3, str3, G(str4), null, null) : c.b.a.a.b.f(context, x2, x3.toString(), str3, G(str4), null, null));
    }

    @JavascriptInterface
    public String javax(String str, String str2, String str3, String str4, String str5) {
        Object x2 = x(str);
        Object x3 = x(str2);
        boolean z2 = x3 instanceof Class;
        Context context = this.f1859c;
        return I("javax", z2 ? c.b.a.a.b.e(context, x2, (Class) x3, str3, G(str4), this.f1860d, str5) : c.b.a.a.b.f(context, x2, x3.toString(), str3, G(str4), this.f1860d, str5));
    }

    @JavascriptInterface
    public void lan(int i2) {
        c.c.a.a.a(this.b, i2);
    }

    @JavascriptInterface
    public String loadjar(String str) {
        Context context = this.f1859c;
        return N("loadjar", c.b.a.a.f.r(context, str, context.getClassLoader()));
    }

    @JavascriptInterface
    public String loadjar(String str, boolean z2) {
        Context context = this.f1859c;
        DexClassLoader r2 = c.b.a.a.f.r(context, str, context.getClassLoader());
        if (z2) {
            c.b.a.a.f.u(this.f1859c, r2);
        }
        return N("loadjar", r2);
    }

    @JavascriptInterface
    public String loadjar(String str, boolean z2, String str2) {
        DexClassLoader r2 = c.b.a.a.f.r(this.f1859c, str, (ClassLoader) x(str2));
        if (z2) {
            c.b.a.a.f.u(this.f1859c, r2);
        }
        return N("loadjar", r2);
    }

    @JavascriptInterface
    public void loadso(String str) {
        c.b.a.a.f.s(str);
    }

    @JavascriptInterface
    public String ngde(int i2, int i3, String str, String str2) {
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
        return N("ngde", gradientDrawable);
    }

    @JavascriptInterface
    public String ngde(int i2, int i3, String str, String str2, String str3) {
        int o2 = c.b.a.a.p.o(str2);
        String[] Q = Q(str, '|');
        int[] iArr = new int[Q.length];
        for (int i4 = 0; i4 < Q.length; i4++) {
            iArr[i4] = c.b.a.a.p.o(Q[i4]);
        }
        GradientDrawable gradientDrawable = new GradientDrawable(D(str3), iArr);
        if (i3 > 0) {
            gradientDrawable.setCornerRadius(i3);
        }
        if (i2 > 0) {
            gradientDrawable.setStroke(i2, o2);
        }
        return N("ngde", gradientDrawable);
    }

    @JavascriptInterface
    public String ngde(int i2, String str) {
        int o2 = c.b.a.a.p.o(str);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(o2);
        if (i2 > 0) {
            gradientDrawable.setCornerRadius(i2);
        }
        return N("ngde", gradientDrawable);
    }

    @JavascriptInterface
    public String ngde(int i2, String str, String str2) {
        int o2 = c.b.a.a.p.o(str);
        int o3 = c.b.a.a.p.o(str2);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(o2);
        if (i2 > 0) {
            gradientDrawable.setStroke(i2, o3);
        }
        return N("ngde", gradientDrawable);
    }

    @JavascriptInterface
    public String ngde(String str) {
        int o2 = c.b.a.a.p.o(str);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(o2);
        return N("ngde", gradientDrawable);
    }

    @JavascriptInterface
    public String nsz(String str) {
        try {
            return M(new Object[Integer.parseInt(str)]);
        } catch (NumberFormatException unused) {
            return str;
        }
    }

    @JavascriptInterface
    public String nuibs(String str, String str2, String str3) {
        return N("nuibs", new c.c.a.c(this.f1859c).d(x(str), x(str2), x(str3)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0061 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0062  */
    /* JADX WARN: Type inference failed for: r1v0, types: [c.c.a.b] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.iapp.app.Aid_jsCode] */
    /* JADX WARN: Type inference failed for: r7v3, types: [android.view.View, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    @android.webkit.JavascriptInterface
    @android.annotation.TargetApi(16)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String nvw(int r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 329
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_jsCode.nvw(int, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    @JavascriptInterface
    public void nvw(String str, String str2) {
        Object x2 = x(str);
        Object x3 = x(str2);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 == null) {
            return;
        }
        if (!(x3 instanceof ViewGroup)) {
            x3 = r(x3, String.valueOf(x3));
        }
        ViewGroup viewGroup = (ViewGroup) x3;
        if (viewGroup == null) {
            return;
        }
        this.f.post(new r(this, viewGroup, r2));
    }

    @JavascriptInterface
    public void nvw(String str, String str2, int i2) {
        Object x2 = x(str);
        Object x3 = x(str2);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 == null) {
            return;
        }
        if (!(x3 instanceof ViewGroup)) {
            x3 = r(x3, String.valueOf(x3));
        }
        ViewGroup viewGroup = (ViewGroup) x3;
        if (viewGroup == null) {
            return;
        }
        this.f.post(new s(this, viewGroup, r2, i2));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void openRestoreinterfaceX(java.lang.String r16, android.view.ViewGroup r17, int r18, java.lang.Object r19, c.c.a.b r20, java.lang.StringBuffer r21, android.webkit.WebView r22) {
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
            android.content.Context r8 = r6.f1859c
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
            r9.k(r10, r11, r12, r13, r14)
            r3.addView(r0)
            return
        La8:
            r6 = r15
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_jsCode.openRestoreinterfaceX(java.lang.String, android.view.ViewGroup, int, java.lang.Object, c.c.a.b, java.lang.StringBuffer, android.webkit.WebView):void");
    }

    @JavascriptInterface
    public String otob(String str) {
        byte[] t2 = c.b.a.a.f.t(this.f1859c, str);
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

    @JavascriptInterface
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

    @JavascriptInterface
    public String res() {
        return N("res", new com.iapp.app.k(this.f1859c));
    }

    @JavascriptInterface
    public String res(String str) {
        return N("res", new com.iapp.app.k(this.f1859c, C(str)));
    }

    @JavascriptInterface
    public String res(String str, String str2) {
        Object c2;
        com.iapp.app.k kVar = (com.iapp.app.k) x(str);
        if (str2.equals("asset")) {
            c2 = kVar.a();
        } else {
            if (!str2.equals("resources")) {
                return null;
            }
            c2 = kVar.c();
        }
        return N("res", c2);
    }

    @JavascriptInterface
    public String res(String str, String str2, String str3) {
        return I("res", ((com.iapp.app.k) x(str)).d(str2, str3));
    }

    @JavascriptInterface
    public String res(String str, String str2, String str3, boolean z2) {
        com.iapp.app.k kVar = (com.iapp.app.k) x(str);
        return I("res", z2 ? Integer.valueOf(kVar.b(str2, str3)) : kVar.d(str2, str3));
    }

    @JavascriptInterface
    public String sbp(String str) {
        Object x2 = x(str);
        Bitmap v2 = x2 instanceof Bitmap ? (Bitmap) x2 : c.b.a.a.f.v(this.f1859c, String.valueOf(x2));
        if (v2 == null) {
            return null;
        }
        return N("sbp", v2);
    }

    @JavascriptInterface
    public String sbp(String str, int i2, int i3, int i4, int i5) {
        Object x2 = x(str);
        Bitmap c2 = x2 instanceof Bitmap ? (Bitmap) x2 : c.b.a.a.i.c(C(String.valueOf(x2)));
        if (c2 == null) {
            return null;
        }
        return N("sbp", Bitmap.createBitmap(c2, i2, i3, i4, i5));
    }

    @JavascriptInterface
    public String sbp(String str, int i2, int i3, int i4, int i5, float f2) {
        Object x2 = x(str);
        Bitmap c2 = x2 instanceof Bitmap ? (Bitmap) x2 : c.b.a.a.i.c(C(String.valueOf(x2)));
        if (c2 == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.preRotate(f2);
        return N("sbp", Bitmap.createBitmap(c2, i2, i3, i4, i5, matrix, true));
    }

    @JavascriptInterface
    public void sdeg(String str) {
        c.b.a.a.t.u = Integer.parseInt(str);
    }

    @JavascriptInterface
    public String se(String str, String str2) {
        Matcher matcher = (Matcher) x(str);
        if (str2.equals("ms")) {
            return String.valueOf(matcher.matches());
        }
        if (str2.equals("find")) {
            return String.valueOf(matcher.find());
        }
        if (str2.equals("gl")) {
            return String.valueOf(matcher.groupCount());
        }
        if (str2.equals("start")) {
            return String.valueOf(matcher.start());
        }
        if (str2.equals("end")) {
            return String.valueOf(matcher.end());
        }
        if (str2.equals("group")) {
            return matcher.group();
        }
        return null;
    }

    @JavascriptInterface
    public String se(String str, String str2, String str3) {
        Object x2 = x(str);
        if (x2 instanceof Matcher) {
            Matcher matcher = (Matcher) x2;
            if (str2.equals("sral")) {
                return matcher.replaceAll(str3);
            }
            if (str2.equals("srft")) {
                return matcher.replaceFirst(str3);
            }
            if (str2.equals("find")) {
                return String.valueOf(matcher.find(Integer.parseInt(str3)));
            }
            if (str2.equals("start")) {
                return String.valueOf(matcher.start(Integer.parseInt(str3)));
            }
            if (str2.equals("end")) {
                return String.valueOf(matcher.end(Integer.parseInt(str3)));
            }
            if (str2.equals("group")) {
                return matcher.group(Integer.parseInt(str3));
            }
        }
        return N("se", Pattern.compile(str2, Integer.parseInt(str3)).matcher(str));
    }

    @JavascriptInterface
    public String shb() {
        CharSequence text = ((ClipboardManager) this.f1859c.getSystemService("clipboard")).getText();
        if (text == null) {
            return null;
        }
        return text.toString();
    }

    @JavascriptInterface
    public String simei() {
        return c.b.a.a.p.q(this.f1859c);
    }

    @JavascriptInterface
    public String simsi() {
        try {
            return ((TelephonyManager) this.f1859c.getSystemService("phone")).getSubscriberId();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @JavascriptInterface
    public int siof(String str, String str2) {
        return str.indexOf(str2);
    }

    @JavascriptInterface
    public int siof(String str, String str2, int i2) {
        return str.indexOf(str2, i2);
    }

    @JavascriptInterface
    public String sit(String str, String str2, String str3) {
        Object x2 = x(str);
        Intent intent = x2 == null ? new Intent() : (Intent) x2;
        if (str2.equals("action")) {
            intent.setAction(str3);
        } else if (str2.equals("type")) {
            intent.setType(str3);
        } else if (str2.equals("flags")) {
            intent.setFlags(Integer.parseInt(str3));
        } else if (str2.equals("data")) {
            intent.setData(c.b.a.a.k.c(this.f1859c, str3));
        }
        return (str == null || !str.startsWith("^")) ? N("sit", intent) : main2.set(str, intent);
    }

    @JavascriptInterface
    public String sit(String str, String str2, String str3, String str4) {
        Object x2 = x(str);
        Object F = str4.contains("\n\\\r") ? F(str4) : x(str4);
        Intent intent = x2 == null ? new Intent() : (Intent) x2;
        if (str2.equals("extra")) {
            if (F instanceof String[]) {
                intent.putExtra(str3, (String[]) F);
            } else if (F instanceof Boolean) {
                intent.putExtra(str3, F.equals(Boolean.TRUE));
            } else if (F instanceof Integer) {
                intent.putExtra(str3, Integer.parseInt(String.valueOf(F)));
            } else if (F instanceof Long) {
                intent.putExtra(str3, Long.parseLong(String.valueOf(F)));
            } else {
                intent.putExtra(str3, String.valueOf(F));
            }
        } else if (str2.equals("classname")) {
            intent.setClassName(str3, String.valueOf(F));
        } else if (str2.equals("component")) {
            intent.setComponent(new ComponentName(str3, String.valueOf(F)));
        }
        return (str == null || !str.startsWith("^")) ? N("sit", intent) : main2.set(str, intent);
    }

    @JavascriptInterface
    public String sj(String str, String str2, String str3) {
        return c.b.a.a.p.c(str, str2, str3);
    }

    @JavascriptInterface
    public String sjxx() {
        String[] t2 = t();
        int[] u2 = u();
        int[] v2 = v();
        String[] w2 = w();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(t2[0]);
        stringBuffer.append('\n');
        stringBuffer.append(t2[1]);
        stringBuffer.append("\n\\\r");
        stringBuffer.append(u2[0]);
        stringBuffer.append('\n');
        stringBuffer.append(u2[1]);
        stringBuffer.append('\n');
        stringBuffer.append(v2[0]);
        stringBuffer.append('\n');
        stringBuffer.append(v2[1]);
        stringBuffer.append("\n\\\r");
        stringBuffer.append(w2[0]);
        stringBuffer.append('\n');
        stringBuffer.append(w2[1]);
        stringBuffer.append('\n');
        stringBuffer.append(w2[2]);
        return stringBuffer.toString();
    }

    @JavascriptInterface
    public String sl(String str, String str2) {
        return M(c.b.a.a.q.e(str, str2));
    }

    @JavascriptInterface
    public String sl(String str, String str2, boolean z2) {
        return z2 ? M(str.split(str2)) : M(c.b.a.a.q.e(str, str2));
    }

    @JavascriptInterface
    public int slg(String str) {
        return str.length();
    }

    @JavascriptInterface
    public int slof(String str, String str2) {
        return str.lastIndexOf(str2);
    }

    @JavascriptInterface
    public int slof(String str, String str2, int i2) {
        return str.lastIndexOf(str2, i2);
    }

    @JavascriptInterface
    public String slower(String str) {
        return str.toLowerCase();
    }

    @JavascriptInterface
    public String sot(int i2, String str, int i3, int i4, boolean z2, String str2) {
        c.b.a.a.o oVar = new c.b.a.a.o(i2, str, i3, i4, z2);
        oVar.F(new j0(str2));
        return N("sot", oVar);
    }

    @JavascriptInterface
    public String sot(String str, int i2, int i3, boolean z2, String str2) {
        c.b.a.a.o oVar = new c.b.a.a.o(str, i2, i3, z2);
        oVar.F(new k0(str2));
        return N("sot", oVar);
    }

    @JavascriptInterface
    public String sot(String str, String str2) {
        c.b.a.a.o oVar = (c.b.a.a.o) x(str);
        if (str2.equals("ip")) {
            return String.valueOf(oVar.B());
        }
        if (str2.equals("id")) {
            return String.valueOf(oVar.t());
        }
        if (str2.equals("list")) {
            return N("sot", oVar.w());
        }
        if (str2.equals("size")) {
            return String.valueOf(oVar.u());
        }
        if (str2.equals("server")) {
            return N("sot", oVar.v());
        }
        if (!str2.equals("re")) {
            return null;
        }
        oVar.s();
        return null;
    }

    @JavascriptInterface
    public String sot(String str, String str2, String str3) {
        c.b.a.a.o oVar = (c.b.a.a.o) x(str);
        if (str2.equals("list")) {
            return N("sot", oVar.A(Integer.parseInt(str3)));
        }
        if (str2.equals("str")) {
            oVar.b(str3);
            return null;
        }
        if (str2.equals("file")) {
            oVar.a(new File(C(str3)));
            return null;
        }
        if (str2.equals("bt")) {
            oVar.c(n(str3, ' '));
            return null;
        }
        if (!str2.equals("new")) {
            return null;
        }
        oVar.f1351c = str3.equals("true");
        return null;
    }

    @JavascriptInterface
    public String sota(String str, String str2) {
        o.c cVar = (o.c) x(str);
        if (str2.equals("re")) {
            cVar.g();
            return null;
        }
        if (str2.equals("ip")) {
            return String.valueOf(cVar.k());
        }
        if (str2.equals("id")) {
            return String.valueOf(cVar.i());
        }
        if (str2.equals("socket")) {
            return N("sota", cVar.j());
        }
        if (str2.equals("ht")) {
            return cVar.h();
        }
        return null;
    }

    @JavascriptInterface
    public void sota(String str, String str2, String str3) {
        o.c cVar = (o.c) x(str);
        if (str2.equals("str")) {
            cVar.b(str3);
        } else if (str2.equals("file")) {
            cVar.a(new File(C(str3)));
        } else if (str2.equals("bt")) {
            cVar.c(n(str3, ' '));
        }
    }

    @JavascriptInterface
    public String sql(String str, String str2) {
        Object x2 = x(str2);
        if (x2 instanceof SQLiteDatabase) {
            ((SQLiteDatabase) x2).execSQL(str);
            return null;
        }
        Object x3 = x(str);
        if (x3 instanceof SQLiteDatabase) {
            try {
                return N("sql", ((SQLiteDatabase) x3).rawQuery(str2, null));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    @JavascriptInterface
    public String sql(String str, String str2, String str3, String str4, String str5) {
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) x(str);
        if (sQLiteDatabase == null) {
            return null;
        }
        if (str3.equals("add")) {
            return String.valueOf(c.b.a.a.n.a(sQLiteDatabase, str2, str4, str5));
        }
        if (str3.equals("up")) {
            return String.valueOf(c.b.a.a.n.j(sQLiteDatabase, str2, str4, str5));
        }
        if (str3.equals("sele")) {
            return N("sql", c.b.a.a.n.h(sQLiteDatabase, str4, str2, str5));
        }
        return null;
    }

    @JavascriptInterface
    public boolean sql(String str, String str2, String str3) {
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) x(str);
        if (sQLiteDatabase == null) {
            return false;
        }
        if (str3.equals("ip")) {
            return c.b.a.a.n.i(sQLiteDatabase, str2);
        }
        if (str3.equals("del")) {
            return c.b.a.a.n.e(sQLiteDatabase, str2);
        }
        return false;
    }

    @JavascriptInterface
    public boolean sql(String str, String str2, String str3, String str4) {
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) x(str);
        if (sQLiteDatabase == null) {
            return false;
        }
        if (str3.equals("add")) {
            return c.b.a.a.n.b(sQLiteDatabase, str2, str4);
        }
        if (str3.equals("del")) {
            return c.b.a.a.n.d(sQLiteDatabase, str2, str4);
        }
        return false;
    }

    @JavascriptInterface
    public String sqlite(String str, String str2) {
        if (!str2.equals("re")) {
            return str2.equals("ip") ? (str.contains("/") || str.contains("\\") || str.startsWith("@") || str.startsWith("$") || str.startsWith("%")) ? String.valueOf(c.b.a.a.f.y(this.f1859c, C(str), false)) : String.valueOf(c.b.a.a.f.y(this.f1859c, str, true)) : str2.equals("del") ? (str.contains("/") || str.contains("\\") || str.startsWith("@") || str.startsWith("$") || str.startsWith("%")) ? String.valueOf(c.b.a.a.f.x(this.f1859c, C(str), false)) : String.valueOf(c.b.a.a.f.x(this.f1859c, str, true)) : (str2.contains("/") || str2.contains("\\") || str2.startsWith("@") || str2.startsWith("$") || str2.startsWith("%")) ? N("sqlite", c.b.a.a.f.z(this.f1859c, C(str2), false)) : N("sqlite", c.b.a.a.f.z(this.f1859c, str2, true));
        }
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) x(str);
        if (sQLiteDatabase == null) {
            return null;
        }
        sQLiteDatabase.close();
        return null;
    }

    @JavascriptInterface
    public String sqlsele(String str, String str2) {
        Cursor cursor = (Cursor) x(str);
        try {
            return cursor.getString(Integer.parseInt(str2));
        } catch (NumberFormatException unused) {
            if (!str2.equals("re")) {
                return str2.equals("columncount") ? String.valueOf(cursor.getColumnCount()) : str2.equals("count") ? String.valueOf(cursor.getCount()) : str2.equals("next") ? String.valueOf(cursor.moveToNext()) : str2.equals("previous") ? String.valueOf(cursor.moveToPrevious()) : str2.equals("first") ? String.valueOf(cursor.moveToFirst()) : str2.equals("last") ? String.valueOf(cursor.moveToLast()) : str2.equals("getposition") ? String.valueOf(cursor.getPosition()) : cursor.getString(Integer.parseInt(str2));
            }
            if (cursor == null) {
                return null;
            }
            cursor.close();
            return null;
        }
    }

    @JavascriptInterface
    public void sqlsele(String str, String str2, int i2) {
        if (str2.equals("position")) {
            ((Cursor) x(str)).moveToPosition(i2);
        }
    }

    @JavascriptInterface
    public String sr(String str, String str2, String str3) {
        return str.replace(str2, str3);
    }

    @JavascriptInterface
    public String sr(String str, String str2, String str3, boolean z2) {
        return z2 ? str.replaceAll(str2, str3) : str.replace(str2, str3);
    }

    @JavascriptInterface
    public int sran(int i2, int i3) {
        return c.b.a.a.p.g(i2, i3);
    }

    @SuppressLint({"JavascriptInterface"})
    public void src(String str, String str2) {
        this.f1860d.addJavascriptInterface(x(str2), str);
    }

    @JavascriptInterface
    public String ss(String str) {
        Object obj = ss_dim.get(str);
        if (obj != null) {
            return y(obj);
        }
        return null;
    }

    @JavascriptInterface
    public void ss(String str, String str2) {
        ss_dim.put(str, x(str2));
    }

    @JavascriptInterface
    public void ssBoolean(String str, boolean z2) {
        ss_dim.put(str, Boolean.valueOf(z2));
    }

    @JavascriptInterface
    public void ssNumber(String str, double d2) {
        ss_dim.put(str, Double.valueOf(d2));
    }

    @JavascriptInterface
    public void ssNumber(String str, long j2) {
        ss_dim.put(str, Long.valueOf(j2));
    }

    @JavascriptInterface
    public String ssg(String str, int i2) {
        return str.substring(i2);
    }

    @JavascriptInterface
    public String ssg(String str, int i2, int i3) {
        return str.substring(i2, i3);
    }

    public void ssj(Object obj, String str, String str2) {
        View r2 = obj instanceof View ? (View) obj : r(obj, String.valueOf(obj));
        if (r2 != null) {
            l(r2, str, F(str2));
        }
    }

    @JavascriptInterface
    public void sslist(String str, int i2, String str2) {
        ((ArrayList) x(str)).set(i2, x(str2));
    }

    @JavascriptInterface
    public String sss(String str) {
        Object obj = c.b.a.a.t.j.get(str);
        if (obj != null) {
            return y(obj);
        }
        return null;
    }

    @JavascriptInterface
    public void sss(String str, String str2) {
        c.b.a.a.t.j.put(str, str2);
    }

    @JavascriptInterface
    public void sssBoolean(String str, boolean z2) {
        c.b.a.a.t.j.put(str, Boolean.valueOf(z2));
    }

    @JavascriptInterface
    public void sssNumber(String str, double d2) {
        c.b.a.a.t.j.put(str, Double.valueOf(d2));
    }

    @JavascriptInterface
    public void sssNumber(String str, long j2) {
        c.b.a.a.t.j.put(str, Long.valueOf(j2));
    }

    @JavascriptInterface
    public String stobm(String str, String str2) {
        return c.b.a.a.p.B(str, str2);
    }

    @JavascriptInterface
    public void stop(long j2) {
        try {
            Thread.sleep(j2);
        } catch (InterruptedException unused) {
        }
    }

    @JavascriptInterface
    public String strim(String str) {
        return str.trim();
    }

    @JavascriptInterface
    public String supper(String str) {
        return str.toUpperCase();
    }

    @JavascriptInterface
    public String sutf8to(String str) {
        return c.b.a.a.p.D(str);
    }

    @JavascriptInterface
    public int swh(String str) {
        if (str.equals("w")) {
            this.b.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            return c.b.a.a.p.w(this.f1859c, r4.widthPixels);
        }
        if (str.equals("h")) {
            WindowManager.LayoutParams attributes = this.b.getWindow().getAttributes();
            this.b.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            return (attributes.flags & 1024) == 1024 ? c.b.a.a.p.w(this.f1859c, r0.heightPixels) : c.b.a.a.p.w(this.f1859c, r0.heightPixels - A());
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
            return (attributes2.flags & 1024) == 1024 ? displayMetrics2.heightPixels : displayMetrics2.heightPixels - A();
        }
        if (str.equals("pxhh")) {
            DisplayMetrics displayMetrics3 = new DisplayMetrics();
            this.b.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics3);
            return displayMetrics3.heightPixels;
        }
        if (str.equals("hh")) {
            this.b.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            return c.b.a.a.p.w(this.f1859c, r4.heightPixels);
        }
        if (str.equals("pxztl")) {
            return B();
        }
        if (str.equals("pxbvk")) {
            return s();
        }
        if (str.equals("ztl")) {
            return c.b.a.a.p.w(this.f1859c, B());
        }
        if (str.equals("bvk")) {
            return c.b.a.a.p.w(this.f1859c, s());
        }
        return 0;
    }

    @JavascriptInterface
    public void sxb(String str) {
        ((ClipboardManager) this.f1859c.getSystemService("clipboard")).setText(str);
    }

    @JavascriptInterface
    public void syso(String str) {
        c.b.a.a.t.P2(this.f1859c, str);
    }

    @JavascriptInterface
    public void t(String str) {
        if (str == null) {
            return;
        }
        z zVar = new z(str);
        zVar.setName("CeShi_" + zVar.getId());
        zVar.start();
    }

    @JavascriptInterface
    public int tcc(String str, String str2) {
        Object x2 = x(str);
        if (!(x2 instanceof Bitmap)) {
            return -1;
        }
        if (str2.equals("w")) {
            return ((Bitmap) x2).getWidth();
        }
        if (str2.equals("h")) {
            return ((Bitmap) x2).getHeight();
        }
        return -1;
    }

    @JavascriptInterface
    public String tfz(String str, String str2) {
        Bitmap bitmap;
        int i2;
        Object x2 = x(str);
        if (!(x2 instanceof Bitmap)) {
            return null;
        }
        if (str2.equals("x")) {
            bitmap = (Bitmap) x2;
            i2 = 0;
        } else {
            if (!str2.equals("y")) {
                return null;
            }
            bitmap = (Bitmap) x2;
            i2 = 1;
        }
        return N("tfz", c.b.a.a.i.f(bitmap, i2));
    }

    @JavascriptInterface
    public String time(String str) {
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

    @JavascriptInterface
    public String tot(String str) {
        Object x2 = x(str);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 != null && (r2 instanceof ImageView)) {
            return N("tot", c.b.a.a.i.a((ImageView) r2));
        }
        return null;
    }

    @JavascriptInterface
    public String tsf(String str, float f2) {
        Object x2 = x(str);
        if (x2 instanceof Bitmap) {
            return N("tsf", c.b.a.a.i.d((Bitmap) x2, f2));
        }
        return null;
    }

    @JavascriptInterface
    public String tsf(String str, int i2, int i3) {
        Object x2 = x(str);
        if (x2 instanceof Bitmap) {
            return N("tsf", c.b.a.a.i.e((Bitmap) x2, i2, i3));
        }
        return null;
    }

    @JavascriptInterface
    public String tts() {
        return N("tts", new com.iapp.app.r(this.f1859c));
    }

    @JavascriptInterface
    public String tts(String str, String str2) {
        com.iapp.app.r rVar = (com.iapp.app.r) x(str);
        if (str2.equals("re")) {
            rVar.h();
            return null;
        }
        if (str2.equals("sp")) {
            return String.valueOf(rVar.j());
        }
        if (str2.equals("ip")) {
            return String.valueOf(rVar.d());
        }
        if (str2.equals("is")) {
            return "true";
        }
        if (str2.equals("zt")) {
            return String.valueOf(rVar.c());
        }
        return null;
    }

    @JavascriptInterface
    public String tts(String str, String str2, float f2, float f3) {
        return N("tts", new com.iapp.app.r(this.f1859c, str, str2, f2, f3));
    }

    @JavascriptInterface
    public void tts(String str, String str2, String str3) {
        com.iapp.app.r rVar = (com.iapp.app.r) x(str);
        if (str2.equals("ph")) {
            rVar.f(Float.parseFloat(str3));
            return;
        }
        if (str2.equals("se")) {
            rVar.g(Float.parseFloat(str3));
            return;
        }
        if (str2.equals("lg")) {
            rVar.e(str3);
        } else if (str2.equals("st")) {
            rVar.i(str2, Integer.parseInt(str3));
        } else if (str2.equals("ft")) {
            rVar.k(str2, C(str3));
        }
    }

    @JavascriptInterface
    public void tw(String str) {
        this.f.post(new g0(str));
    }

    @JavascriptInterface
    public void tw(String str, int i2) {
        this.f.post(new r0(str, i2));
    }

    @JavascriptInterface
    public String tzz(String str, float f2) {
        Object x2 = x(str);
        if (x2 instanceof Bitmap) {
            return N("tzz", c.b.a.a.i.g((Bitmap) x2, f2));
        }
        return null;
    }

    @JavascriptInterface
    public String uall(String str, boolean z2) {
        Object x2 = x(str);
        if (!(x2 instanceof ViewGroup)) {
            x2 = r(x2, String.valueOf(x2));
        }
        ViewGroup viewGroup = (ViewGroup) x2;
        StringBuffer stringBuffer = null;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            Object N = z2 ? N("uall", childAt) : Integer.valueOf(childAt.getId());
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            } else {
                stringBuffer.append("\n\\\r");
            }
            stringBuffer.append(N);
        }
        if (stringBuffer == null) {
            return null;
        }
        return stringBuffer.toString();
    }

    @JavascriptInterface
    public boolean uapp(String str) {
        return c.b.a.a.d.s(this.f1859c, str);
    }

    @JavascriptInterface
    public boolean uapp(String str, String str2) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(str, str2));
            this.f1859c.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0011 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x006b A[SYNTHETIC] */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String uapplist(boolean r9) {
        /*
            r8 = this;
            android.content.Context r0 = r8.f1859c
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            r1 = 0
            java.util.List r0 = r0.getInstalledPackages(r1)
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
            r2 = r1
        L11:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L7c
            java.lang.Object r3 = r0.next()
            android.content.pm.PackageInfo r3 = (android.content.pm.PackageInfo) r3
            java.lang.String r4 = r3.packageName
            java.lang.String r5 = "\n"
            if (r9 == 0) goto L5a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
        L28:
            java.lang.String r7 = r3.packageName
            r6.append(r7)
            r6.append(r5)
            java.lang.String r4 = r8.E(r4)
            r6.append(r4)
            r6.append(r5)
            android.content.pm.ApplicationInfo r4 = r3.applicationInfo
            android.content.Context r7 = r8.f1859c
            android.content.pm.PackageManager r7 = r7.getPackageManager()
            java.lang.CharSequence r4 = r4.loadLabel(r7)
            java.lang.String r4 = r4.toString()
            r6.append(r4)
            r6.append(r5)
            java.lang.String r3 = r3.versionName
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            goto L69
        L5a:
            android.content.pm.ApplicationInfo r6 = r3.applicationInfo
            int r6 = r6.flags
            r6 = r6 & 1
            if (r6 != 0) goto L68
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            goto L28
        L68:
            r3 = r1
        L69:
            if (r3 == 0) goto L11
            if (r2 != 0) goto L73
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            goto L78
        L73:
            java.lang.String r4 = "\n\\\r"
            r2.append(r4)
        L78:
            r2.append(r3)
            goto L11
        L7c:
            if (r2 != 0) goto L7f
            return r1
        L7f:
            java.lang.String r9 = r2.toString()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_jsCode.uapplist(boolean):java.lang.String");
    }

    @JavascriptInterface
    public void ucall(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DIAL");
        intent.setData(c.b.a.a.k.c(this.f1859c, "tel:" + str));
        this.f1859c.startActivity(intent);
    }

    @JavascriptInterface
    public void ufnsui(String str) {
        if (str == null) {
            return;
        }
        Message message = new Message();
        message.what = 2;
        message.obj = str;
        this.f.sendMessage(message);
    }

    @JavascriptInterface
    public String ug(String str, String str2) {
        Object x2 = x(str);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 == null) {
            return null;
        }
        return I("ug", new com.iapp.app.i(r2, this.f1859c).e(str2));
    }

    @JavascriptInterface
    public String ug(String str, String str2, String str3) {
        Object x2 = x(str);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 == null) {
            return null;
        }
        return I("ug", new com.iapp.app.i(r2, this.f1859c).f(str2, str3));
    }

    @JavascriptInterface
    public void uigo(String str) {
        Intent intent;
        if (str.endsWith(".iyu")) {
            intent = new Intent(this.f1859c, (Class<?>) mian.class);
        } else if (str.endsWith(".ijava")) {
            intent = new Intent(this.f1859c, (Class<?>) main3.class);
        } else if (str.endsWith(".ilua")) {
            intent = new Intent(this.f1859c, (Class<?>) main.class);
        } else if (!str.endsWith(".ijs")) {
            return;
        } else {
            intent = new Intent(this.f1859c, (Class<?>) main2.class);
        }
        intent.putExtras(c.b.a.a.f.B(str));
        this.f1859c.startActivity(intent);
    }

    @JavascriptInterface
    public void uigo(String str, int i2) {
        Intent intent;
        if (str.endsWith(".iyu")) {
            intent = new Intent(this.f1859c, (Class<?>) mian.class);
        } else if (str.endsWith(".ijava")) {
            intent = new Intent(this.f1859c, (Class<?>) main3.class);
        } else if (str.endsWith(".ilua")) {
            intent = new Intent(this.f1859c, (Class<?>) main.class);
        } else if (!str.endsWith(".ijs")) {
            return;
        } else {
            intent = new Intent(this.f1859c, (Class<?>) main2.class);
        }
        intent.putExtras(c.b.a.a.f.B(str));
        intent.setFlags(i2);
        this.f1859c.startActivity(intent);
    }

    @JavascriptInterface
    public void uit(String str) {
        this.f1859c.startActivity((Intent) x(str));
    }

    @JavascriptInterface
    public void uit(String str, String str2, String str3) {
        Context context;
        Intent intent = (Intent) x(str);
        if (str2.equals("chooser")) {
            context = this.f1859c;
            intent = Intent.createChooser(intent, str3);
        } else {
            if (str2.equals("result")) {
                this.b.startActivityForResult(intent, Integer.parseInt(str3));
                return;
            }
            context = this.f1859c;
        }
        context.startActivity(intent);
    }

    @JavascriptInterface
    public void ujp(String str, int i2) {
        c.b.a.a.r.a(this.b, C(str), i2);
    }

    @JavascriptInterface
    public String ula(String str, String str2) {
        Object x2 = x(str);
        if (str2 != null && !str2.equals("clear")) {
            if (str2.equals("list")) {
                return N("ula", ((x2 == null || !(x2 instanceof t1)) ? new t1(this.f1859c) : (t1) x2).b());
            }
            return null;
        }
        if (x2 == null || !(x2 instanceof t1)) {
            return null;
        }
        ((t1) x2).b().clear();
        return null;
    }

    @JavascriptInterface
    public String ula(String str, String str2, String str3) {
        Object x2 = x(str);
        String[] F = F(str2);
        String[] F2 = F(str3);
        int length = F.length;
        t1 t1Var = (x2 == null || !(x2 instanceof t1)) ? new t1(this.f1859c) : (t1) x2;
        HashMap<Integer, Object> hashMap = new HashMap<>();
        for (int i2 = 0; i2 < length; i2++) {
            hashMap.put(Integer.valueOf(Integer.parseInt(F[i2])), F2[i2]);
        }
        t1Var.a(hashMap);
        return (str == null || !str.startsWith("^")) ? N("ula", t1Var) : main2.set(str, t1Var);
    }

    @JavascriptInterface
    public void ula(String str) {
        this.f.post(new f(str));
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0051, code lost:
    
        if ((r5 instanceof com.iapp.app.Aid_jsCode.t1) != false) goto L18;
     */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String ulag(java.lang.String r5, int r6, java.lang.String r7) {
        /*
            r4 = this;
            java.lang.Object r5 = r4.x(r5)
            boolean r0 = r5 instanceof c.b.a.a.e
            java.lang.String r1 = "ulag"
            if (r0 == 0) goto L1f
            c.b.a.a.e r5 = (c.b.a.a.e) r5
            java.util.ArrayList r5 = r5.q()
            java.lang.Object r5 = r5.get(r6)
            java.util.HashMap r5 = (java.util.HashMap) r5
            java.lang.Object r5 = r5.get(r7)
        L1a:
            java.lang.String r5 = r4.I(r1, r5)
            return r5
        L1f:
            boolean r0 = r5 instanceof java.util.ArrayList
            if (r0 == 0) goto L38
            java.util.ArrayList r5 = (java.util.ArrayList) r5
        L25:
            java.lang.Object r5 = r5.get(r6)
            java.util.HashMap r5 = (java.util.HashMap) r5
            int r6 = java.lang.Integer.parseInt(r7)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r5 = r5.get(r6)
            goto L1a
        L38:
            boolean r0 = r5 instanceof android.widget.AbsListView
            r2 = 0
            if (r0 == 0) goto L4f
            android.widget.AbsListView r5 = (android.widget.AbsListView) r5
            java.lang.Object r5 = r5.getTag()
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            r0 = 3
            r3 = r5[r0]
            boolean r3 = r3 instanceof com.iapp.app.Aid_jsCode.t1
            if (r3 == 0) goto L56
            r5 = r5[r0]
            goto L53
        L4f:
            boolean r0 = r5 instanceof com.iapp.app.Aid_jsCode.t1
            if (r0 == 0) goto L56
        L53:
            com.iapp.app.Aid_jsCode$t1 r5 = (com.iapp.app.Aid_jsCode.t1) r5
            goto L57
        L56:
            r5 = r2
        L57:
            if (r5 != 0) goto L5a
            return r2
        L5a:
            java.util.ArrayList r5 = r5.b()
            goto L25
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_jsCode.ulag(java.lang.String, int, java.lang.String):java.lang.String");
    }

    @JavascriptInterface
    public String ulag(String str, String str2) {
        Object x2 = x(str);
        if (x2 instanceof View) {
            Object[] objArr = (Object[]) ((View) x2).getTag();
            if (objArr[3] instanceof HashMap) {
                return I("ulag", ((HashMap) objArr[3]).get(Integer.valueOf(Integer.parseInt(str2))));
            }
            return null;
        }
        if (x2 instanceof HashMap) {
            return I("ulag", ((HashMap) x2).get(str2));
        }
        if (x2 instanceof ArrayList) {
            return N("ulag", ((ArrayList) x2).get(Integer.parseInt(str2)));
        }
        return null;
    }

    @JavascriptInterface
    public void ulas(String str, int i2, int i3, String str2) {
        Object x2 = x(str);
        Object x3 = x(str2);
        t1 t1Var = null;
        if (x2 instanceof AbsListView) {
            Object[] objArr = (Object[]) ((AbsListView) x2).getTag();
            if (objArr[3] instanceof t1) {
                t1Var = (t1) objArr[3];
            }
        } else if (x2 instanceof t1) {
            t1Var = (t1) x2;
        }
        if (t1Var == null) {
            return;
        }
        t1Var.b().get(i2).put(Integer.valueOf(i3), x3);
    }

    @JavascriptInterface
    public void ulas(String str, int i2, String str2) {
        Object x2 = x(str);
        Object x3 = x(str2);
        if (x2 instanceof View) {
            Object[] objArr = (Object[]) ((View) x2).getTag();
            if (objArr[3] instanceof HashMap) {
                ((HashMap) objArr[3]).put(Integer.valueOf(i2), x3);
            }
        }
    }

    @JavascriptInterface
    public void uls(String str, String str2) {
        Handler handler;
        Runnable iVar;
        Object x2 = x(str);
        Object F = str2.contains("\n\\\r") ? F(str2) : x(str2);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 != null && (r2 instanceof Spinner)) {
            Spinner spinner = (Spinner) r2;
            if (F instanceof ArrayList) {
                ArrayAdapter arrayAdapter = new ArrayAdapter(this.f1859c, R.layout.simple_spinner_item, (ArrayList) F);
                arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                handler = this.f;
                iVar = new g(this, spinner, arrayAdapter);
            } else if (F instanceof String[]) {
                ArrayAdapter arrayAdapter2 = new ArrayAdapter(this.f1859c, R.layout.simple_spinner_item, (String[]) F);
                arrayAdapter2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                handler = this.f;
                iVar = new h(this, spinner, arrayAdapter2);
            } else {
                if (!(F instanceof Object[])) {
                    return;
                }
                ArrayAdapter arrayAdapter3 = new ArrayAdapter(this.f1859c, R.layout.simple_spinner_item, (Object[]) F);
                arrayAdapter3.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                handler = this.f;
                iVar = new i(this, spinner, arrayAdapter3);
            }
            handler.post(iVar);
        }
    }

    @JavascriptInterface
    public void uls(String str, String str2, String str3) {
        Handler handler;
        Runnable lVar;
        Object x2 = x(str);
        Object x3 = x(str2);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 == null) {
            return;
        }
        if (r2 instanceof ListView) {
            ListView listView = (ListView) r2;
            if (!(x3 instanceof t1)) {
                return;
            }
            t1 t1Var = (t1) x3;
            t1Var.c(str3);
            Object[] objArr = (Object[]) listView.getTag();
            objArr[3] = t1Var;
            listView.setTag(objArr);
            handler = this.f;
            lVar = new j(this, listView, t1Var);
        } else {
            if (!(r2 instanceof GridView)) {
                return;
            }
            GridView gridView = (GridView) r2;
            if (!(x3 instanceof t1)) {
                return;
            }
            t1 t1Var2 = (t1) x3;
            t1Var2.c(str3);
            Object[] objArr2 = (Object[]) gridView.getTag();
            objArr2[3] = t1Var2;
            gridView.setTag(objArr2);
            handler = this.f;
            lVar = new l(this, gridView, t1Var2);
        }
        handler.post(lVar);
    }

    @JavascriptInterface
    public void uls(String str, String str2, String str3, int i2, int i3) {
        Handler handler;
        Runnable nVar;
        Object x2 = x(str);
        Object x3 = x(str2);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 == null) {
            return;
        }
        if (r2 instanceof ListView) {
            ListView listView = (ListView) r2;
            if (!(x3 instanceof t1)) {
                return;
            }
            t1 t1Var = (t1) x3;
            t1Var.c(str3);
            t1Var.d(i2, i3);
            Object[] objArr = (Object[]) listView.getTag();
            objArr[3] = t1Var;
            listView.setTag(objArr);
            handler = this.f;
            nVar = new m(this, listView, t1Var);
        } else {
            if (!(r2 instanceof GridView)) {
                return;
            }
            GridView gridView = (GridView) r2;
            if (!(x3 instanceof t1)) {
                return;
            }
            t1 t1Var2 = (t1) x3;
            t1Var2.c(str3);
            t1Var2.d(i2, i3);
            Object[] objArr2 = (Object[]) gridView.getTag();
            objArr2[3] = t1Var2;
            gridView.setTag(objArr2);
            handler = this.f;
            nVar = new n(this, gridView, t1Var2);
        }
        handler.post(nVar);
    }

    @JavascriptInterface
    public void uninapp(String str) {
        this.f1859c.startActivity(new Intent("android.intent.action.DELETE", c.b.a.a.k.c(this.f1859c, "package:" + str)));
    }

    @JavascriptInterface
    public String uqr(String str) {
        try {
            new c.b.a.a.m(this.f1859c, this.b).b(Integer.parseInt(str));
            return null;
        } catch (NumberFormatException unused) {
            Object x2 = x(str);
            return (x2 instanceof Bitmap ? new c.b.a.a.m(this.f1859c, this.b).d((Bitmap) x2) : new c.b.a.a.m(this.f1859c, this.b).e(C(String.valueOf(x2)))).f();
        }
    }

    @JavascriptInterface
    public String uqr(String str, int i2) {
        return N("uqr", new c.b.a.a.m(this.f1859c, this.b).c(str, i2));
    }

    @JavascriptInterface
    public void uqr() {
        new c.b.a.a.m(this.f1859c, this.b).a();
    }

    @JavascriptInterface
    public void urvw(String str) {
        Object x2 = x(str);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 != null) {
            this.f.post(new u(this, (ViewGroup) r2.getParent(), r2));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0057 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0058  */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean us(java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            java.lang.Object r8 = r7.x(r8)
            java.lang.Object r5 = r7.x(r10)
            boolean r10 = r8 instanceof android.view.View
            r6 = 1
            r0 = 0
            if (r10 == 0) goto L13
            android.view.View r8 = (android.view.View) r8
        L10:
            r2 = r8
            r3 = 0
            goto L55
        L13:
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r10 = 46
            int r10 = r8.indexOf(r10)
            r1 = -1
            if (r10 == r1) goto L4a
            java.lang.String r1 = r8.substring(r0, r10)
            int r10 = r10 + r6
            java.lang.String r8 = r8.substring(r10)
            java.util.HashMap<java.lang.String, java.lang.Object> r10 = com.iapp.app.Aid_jsCode.g
            java.lang.Object r10 = r10.get(r1)
            if (r10 == 0) goto L45
            java.lang.String r10 = java.lang.String.valueOf(r10)
            int r10 = java.lang.Integer.parseInt(r10)
            android.app.Activity r1 = r7.b
            int r8 = java.lang.Integer.parseInt(r8)
            int r8 = r8 + r10
            android.view.View r8 = r1.findViewById(r8)
            goto L47
        L45:
            r8 = 0
            r10 = 0
        L47:
            r2 = r8
            r3 = r10
            goto L55
        L4a:
            android.app.Activity r10 = r7.b
            int r8 = java.lang.Integer.parseInt(r8)
            android.view.View r8 = r10.findViewById(r8)
            goto L10
        L55:
            if (r2 != 0) goto L58
            return r0
        L58:
            android.os.Handler r8 = r7.f
            com.iapp.app.Aid_jsCode$c1 r10 = new com.iapp.app.Aid_jsCode$c1
            r0 = r10
            r1 = r7
            r4 = r9
            r0.<init>(r2, r3, r4, r5)
            r8.post(r10)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_jsCode.us(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0053 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0054  */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean us(java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14) {
        /*
            r10 = this;
            java.lang.Object r11 = r10.x(r11)
            boolean r0 = r11 instanceof android.view.View
            r1 = 1
            r2 = 0
            if (r0 == 0) goto Lf
            android.view.View r11 = (android.view.View) r11
        Lc:
            r5 = r11
            r6 = 0
            goto L51
        Lf:
            java.lang.String r11 = java.lang.String.valueOf(r11)
            r0 = 46
            int r0 = r11.indexOf(r0)
            r3 = -1
            if (r0 == r3) goto L46
            java.lang.String r3 = r11.substring(r2, r0)
            int r0 = r0 + r1
            java.lang.String r11 = r11.substring(r0)
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = com.iapp.app.Aid_jsCode.g
            java.lang.Object r0 = r0.get(r3)
            if (r0 == 0) goto L41
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r0 = java.lang.Integer.parseInt(r0)
            android.app.Activity r3 = r10.b
            int r11 = java.lang.Integer.parseInt(r11)
            int r11 = r11 + r0
            android.view.View r11 = r3.findViewById(r11)
            goto L43
        L41:
            r11 = 0
            r0 = 0
        L43:
            r5 = r11
            r6 = r0
            goto L51
        L46:
            android.app.Activity r0 = r10.b
            int r11 = java.lang.Integer.parseInt(r11)
            android.view.View r11 = r0.findViewById(r11)
            goto Lc
        L51:
            if (r5 != 0) goto L54
            return r2
        L54:
            android.os.Handler r11 = r10.f
            com.iapp.app.Aid_jsCode$n1 r0 = new com.iapp.app.Aid_jsCode$n1
            r3 = r0
            r4 = r10
            r7 = r12
            r8 = r13
            r9 = r14
            r3.<init>(r5, r6, r7, r8, r9)
            r11.post(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_jsCode.us(java.lang.String, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0054 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0055  */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean us(java.lang.String r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17) {
        /*
            r12 = this;
            r8 = r12
            java.lang.Object r0 = r12.x(r13)
            boolean r1 = r0 instanceof android.view.View
            r9 = 1
            r2 = 0
            if (r1 == 0) goto L10
            android.view.View r0 = (android.view.View) r0
        Ld:
            r3 = r0
            r4 = 0
            goto L52
        L10:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1 = 46
            int r1 = r0.indexOf(r1)
            r3 = -1
            if (r1 == r3) goto L47
            java.lang.String r3 = r0.substring(r2, r1)
            int r1 = r1 + r9
            java.lang.String r0 = r0.substring(r1)
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = com.iapp.app.Aid_jsCode.g
            java.lang.Object r1 = r1.get(r3)
            if (r1 == 0) goto L42
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r1 = java.lang.Integer.parseInt(r1)
            android.app.Activity r3 = r8.b
            int r0 = java.lang.Integer.parseInt(r0)
            int r0 = r0 + r1
            android.view.View r0 = r3.findViewById(r0)
            goto L44
        L42:
            r0 = 0
            r1 = 0
        L44:
            r3 = r0
            r4 = r1
            goto L52
        L47:
            android.app.Activity r1 = r8.b
            int r0 = java.lang.Integer.parseInt(r0)
            android.view.View r0 = r1.findViewById(r0)
            goto Ld
        L52:
            if (r3 != 0) goto L55
            return r2
        L55:
            android.os.Handler r10 = r8.f
            com.iapp.app.Aid_jsCode$q1 r11 = new com.iapp.app.Aid_jsCode$q1
            r0 = r11
            r1 = r12
            r2 = r3
            r3 = r4
            r4 = r14
            r5 = r15
            r6 = r16
            r7 = r17
            r0.<init>(r2, r3, r4, r5, r6, r7)
            r10.post(r11)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_jsCode.us(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0054 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0055  */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean us(java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19) {
        /*
            r13 = this;
            r9 = r13
            java.lang.Object r0 = r13.x(r14)
            boolean r1 = r0 instanceof android.view.View
            r10 = 1
            r2 = 0
            if (r1 == 0) goto L10
            android.view.View r0 = (android.view.View) r0
        Ld:
            r3 = r0
            r4 = 0
            goto L52
        L10:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1 = 46
            int r1 = r0.indexOf(r1)
            r3 = -1
            if (r1 == r3) goto L47
            java.lang.String r3 = r0.substring(r2, r1)
            int r1 = r1 + r10
            java.lang.String r0 = r0.substring(r1)
            java.util.HashMap<java.lang.String, java.lang.Object> r1 = com.iapp.app.Aid_jsCode.g
            java.lang.Object r1 = r1.get(r3)
            if (r1 == 0) goto L42
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r1 = java.lang.Integer.parseInt(r1)
            android.app.Activity r3 = r9.b
            int r0 = java.lang.Integer.parseInt(r0)
            int r0 = r0 + r1
            android.view.View r0 = r3.findViewById(r0)
            goto L44
        L42:
            r0 = 0
            r1 = 0
        L44:
            r3 = r0
            r4 = r1
            goto L52
        L47:
            android.app.Activity r1 = r9.b
            int r0 = java.lang.Integer.parseInt(r0)
            android.view.View r0 = r1.findViewById(r0)
            goto Ld
        L52:
            if (r3 != 0) goto L55
            return r2
        L55:
            android.os.Handler r11 = r9.f
            com.iapp.app.Aid_jsCode$r1 r12 = new com.iapp.app.Aid_jsCode$r1
            r0 = r12
            r1 = r13
            r2 = r3
            r3 = r4
            r4 = r15
            r5 = r16
            r6 = r17
            r7 = r18
            r8 = r19
            r0.<init>(r2, r3, r4, r5, r6, r7, r8)
            r11.post(r12)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_jsCode.us(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    @JavascriptInterface
    public String usg(String str, boolean z2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return main2.set(str, c.b.a.a.r.i(this.f1859c, z2));
        }
        Object x2 = x(str);
        Camera camera = null;
        Camera camera2 = x2 == null ? null : (Camera) x2;
        try {
            if (z2) {
                camera = Camera.open();
                try {
                    T(camera);
                } catch (Exception e2) {
                    e = e2;
                    camera2 = camera;
                    e.printStackTrace();
                    camera = camera2;
                    if (str == null) {
                    }
                }
            } else {
                if (camera2 == null) {
                    return null;
                }
                S(camera2);
                camera2.release();
            }
        } catch (Exception e3) {
            e = e3;
        }
        return (str == null && str.startsWith("^")) ? main2.set(str, camera) : N("usg", camera);
    }

    @JavascriptInterface
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

    @JavascriptInterface
    public void usjxm(boolean z2) {
        if (z2) {
            this.b.getWindow().clearFlags(128);
        } else {
            this.b.getWindow().setFlags(128, 128);
        }
    }

    @JavascriptInterface
    public void usms(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO", c.b.a.a.k.c(this.f1859c, "smsto:" + str));
        intent.putExtra("sms_body", str2);
        this.f1859c.startActivity(intent);
    }

    @JavascriptInterface
    public String usx(String str, String str2) {
        com.iapp.app.m mVar = (com.iapp.app.m) x(str);
        if (str2.equals("st")) {
            mVar.y();
            return null;
        }
        if (str2.equals("sp")) {
            mVar.z();
            return null;
        }
        if (str2.equals("re")) {
            mVar.u();
            return null;
        }
        if (str2.equals("getrotaing")) {
            return String.valueOf(mVar.r());
        }
        return null;
    }

    @JavascriptInterface
    public void usx(String str, String str2, String str3) {
        com.iapp.app.m mVar = (com.iapp.app.m) x(str);
        if (str2.equals("rotaing")) {
            mVar.w(Integer.parseInt(str3));
        } else if (str2.equals("usg")) {
            mVar.x(str3.equals("true"));
        }
    }

    @JavascriptInterface
    public void usx(String str, String str2, String str3, int i2, boolean z2) {
        com.iapp.app.m mVar = (com.iapp.app.m) x(str);
        if (str2.equals("shot")) {
            mVar.q(C(str3), i2, z2);
        }
    }

    @JavascriptInterface
    public String usxh(String str, int i2) {
        Object x2 = x(str);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 instanceof SurfaceView) {
            return N("usxh", new com.iapp.app.m(this.f1859c, this.b, (SurfaceView) r2, false, i2));
        }
        return null;
    }

    @JavascriptInterface
    public String usxh(String str, int i2, int i3, int i4, int i5) {
        Object x2 = x(str);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 instanceof SurfaceView) {
            return N("usxh", new com.iapp.app.m(this.f1859c, this.b, (SurfaceView) r2, false, i2, i3, i4, i5));
        }
        return null;
    }

    @JavascriptInterface
    public String usxq(String str, int i2) {
        Object x2 = x(str);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 instanceof SurfaceView) {
            return N("usxq", new com.iapp.app.m(this.f1859c, this.b, (SurfaceView) r2, true, i2));
        }
        return null;
    }

    @JavascriptInterface
    public String usxq(String str, int i2, int i3, int i4, int i5) {
        Object x2 = x(str);
        View r2 = x2 instanceof View ? (View) x2 : r(x2, String.valueOf(x2));
        if (r2 instanceof SurfaceView) {
            return N("usxq", new com.iapp.app.m(this.f1859c, this.b, (SurfaceView) r2, true, i2, i3, i4, i5));
        }
        return null;
    }

    @JavascriptInterface
    public String utw(String str, String str2, String str3, String str4, String str5, String str6, boolean z2, String str7, String str8, String str9) {
        Object x2 = x(str);
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(this.f1859c).setTitle(str2).setPositiveButton(str4, new b(str7)).setNeutralButton(str5, new a(str8)).setNegativeButton(str6, new s1(str9));
        if (x2 != null) {
            negativeButton.setIcon(x2 instanceof Drawable ? (Drawable) x2 : com.iapp.app.i.m(String.valueOf(x2), this.f1859c));
        }
        Object obj = null;
        if (str3.endsWith(".ijs")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout = new LinearLayout(this.f1859c);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(1);
            try {
                K(str3, linearLayout, 0, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            negativeButton.setView(linearLayout);
            obj = linearLayout;
        } else {
            negativeButton.setMessage(str3);
        }
        AlertDialog create = negativeButton.create();
        alertdialog = create;
        if (z2) {
            create.setCanceledOnTouchOutside(false);
            alertdialog.setCancelable(false);
            create = alertdialog;
        }
        create.show();
        return N("utw", obj);
    }

    @JavascriptInterface
    public String utw(String str, String str2, String str3, String str4, String str5, boolean z2, String str6, String str7) {
        Object x2 = x(str);
        AlertDialog.Builder neutralButton = new AlertDialog.Builder(this.f1859c).setTitle(str2).setPositiveButton(str4, new d(str6)).setNeutralButton(str5, new c(str7));
        if (x2 != null) {
            neutralButton.setIcon(x2 instanceof Drawable ? (Drawable) x2 : com.iapp.app.i.m(String.valueOf(x2), this.f1859c));
        }
        Object obj = null;
        if (str3.endsWith(".ijs")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout = new LinearLayout(this.f1859c);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(1);
            try {
                K(str3, linearLayout, 0, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            neutralButton.setView(linearLayout);
            obj = linearLayout;
        } else {
            neutralButton.setMessage(str3);
        }
        AlertDialog create = neutralButton.create();
        alertdialog = create;
        if (z2) {
            create.setCanceledOnTouchOutside(false);
            alertdialog.setCancelable(false);
            create = alertdialog;
        }
        create.show();
        return N("utw", obj);
    }

    @JavascriptInterface
    public String utw(String str, String str2, String str3, String str4, boolean z2, String str5) {
        Object x2 = x(str);
        AlertDialog.Builder positiveButton = new AlertDialog.Builder(this.f1859c).setTitle(str2).setPositiveButton(str4, new e(str5));
        if (x2 != null) {
            positiveButton.setIcon(x2 instanceof Drawable ? (Drawable) x2 : com.iapp.app.i.m(String.valueOf(x2), this.f1859c));
        }
        Object obj = null;
        if (str3.endsWith(".ijs")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout = new LinearLayout(this.f1859c);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(1);
            try {
                K(str3, linearLayout, 0, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            positiveButton.setView(linearLayout);
            obj = linearLayout;
        } else {
            positiveButton.setMessage(str3);
        }
        AlertDialog create = positiveButton.create();
        alertdialog = create;
        if (z2) {
            create.setCanceledOnTouchOutside(false);
            alertdialog.setCancelable(false);
            create = alertdialog;
        }
        create.show();
        return N("utw", obj);
    }

    @JavascriptInterface
    public String utw(String str, String str2, String str3, boolean z2) {
        Object x2 = x(str);
        AlertDialog.Builder title = new AlertDialog.Builder(this.f1859c).setTitle(str2);
        if (x2 != null) {
            title.setIcon(x2 instanceof Drawable ? (Drawable) x2 : com.iapp.app.i.m(String.valueOf(x2), this.f1859c));
        }
        if (str3.endsWith(".ijs")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout = new LinearLayout(this.f1859c);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(1);
            try {
                K(str3, linearLayout, 0, null);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            title.setView(linearLayout);
        } else {
            title.setMessage(str3);
        }
        AlertDialog create = title.create();
        alertdialog = create;
        if (z2) {
            create.setCanceledOnTouchOutside(false);
            alertdialog.setCancelable(false);
            create = alertdialog;
        }
        create.show();
        return N("utw", null);
    }

    @JavascriptInterface
    public String uxf(String str, int i2, int i3, int i4, int i5, int i6, String str2, int i7, int i8) {
        try {
            LinearLayout linearLayout = new LinearLayout(this.f1859c);
            linearLayout.setOrientation(1);
            int m2 = m(str.substring(0, str.length() - 4));
            linearLayout.setId(m2);
            K(str, linearLayout, m2, null);
            View childAt = linearLayout.getChildAt(0);
            linearLayout.removeAllViews();
            this.f.post(new i0(childAt, i2, i3, i4, i5, i6, str2, i7, i8));
            return N("uxf", childAt);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @JavascriptInterface
    public String uxf(String str, int i2, int i3, String str2) {
        try {
            LinearLayout linearLayout = new LinearLayout(this.f1859c);
            linearLayout.setOrientation(1);
            int m2 = m(str.substring(0, str.length() - 4));
            linearLayout.setId(m2);
            K(str, linearLayout, m2, null);
            View childAt = linearLayout.getChildAt(0);
            linearLayout.removeAllViews();
            this.f.post(new e0(childAt, i2, i3, str2));
            return N("uxf", childAt);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @JavascriptInterface
    public void uxf(String str) {
        Object x2 = x(str);
        View view = x2 instanceof View ? (View) x2 : null;
        if (view != null) {
            this.f.post(new c0(view));
        }
    }

    @JavascriptInterface
    public void uxf(String str, String str2) {
        Object x2 = x(str);
        if (str2.equals("del")) {
            View view = x2 instanceof View ? (View) x2 : null;
            if (view != null) {
                this.f.post(new d0(view));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005f  */
    @android.webkit.JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void uxf(java.lang.String r2, java.lang.String r3, int r4, int r5, int r6, int r7, int r8, java.lang.String r9, int r10, int r11) {
        /*
            r1 = this;
            java.lang.Object r2 = r1.x(r2)
            boolean r0 = r2 instanceof android.view.View
            if (r0 == 0) goto L71
            java.lang.String r0 = "set"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L71
            android.view.View r2 = (android.view.View) r2
            android.content.Context r3 = r1.f1859c
            android.content.Context r3 = r3.getApplicationContext()
            java.lang.String r0 = "window"
            java.lang.Object r3 = r3.getSystemService(r0)
            android.view.WindowManager r3 = (android.view.WindowManager) r3
            android.view.ViewGroup$LayoutParams r0 = r2.getLayoutParams()
            android.view.WindowManager$LayoutParams r0 = (android.view.WindowManager.LayoutParams) r0
            r0.x = r4
            r0.y = r5
            r4 = -2
            if (r6 != 0) goto L30
            r0.width = r4
            goto L32
        L30:
            r0.width = r6
        L32:
            if (r7 != 0) goto L37
            r0.height = r4
            goto L39
        L37:
            r0.height = r7
        L39:
            if (r8 != 0) goto L42
            int r4 = c.b.a.a.r.f()
            r0.type = r4
            goto L44
        L42:
            r0.type = r8
        L44:
            int r4 = com.iapp.app.i.t(r9)
            r0.gravity = r4
            if (r10 != 0) goto L51
            r4 = 40
        L4e:
            r0.flags = r4
            goto L59
        L51:
            r4 = 1
            if (r10 != r4) goto L57
            r4 = 32
            goto L4e
        L57:
            r0.flags = r10
        L59:
            if (r11 != 0) goto L5f
            r4 = -3
            r0.format = r4
            goto L61
        L5f:
            r0.format = r11
        L61:
            r2.setLayoutParams(r0)
            r3.updateViewLayout(r2, r0)
            android.os.Handler r4 = r1.f
            com.iapp.app.Aid_jsCode$h0 r5 = new com.iapp.app.Aid_jsCode$h0
            r5.<init>(r1, r3, r2, r0)
            r4.post(r5)
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_jsCode.uxf(java.lang.String, java.lang.String, int, int, int, int, int, java.lang.String, int, int):void");
    }

    @JavascriptInterface
    public void uxf(String str, String str2, int i2, int i3, int i4, int i5, String str3) {
        Object x2 = x(str);
        if (str2.equals("set")) {
            View view = x2 instanceof View ? (View) x2 : null;
            if (view != null) {
                WindowManager windowManager = (WindowManager) this.f1859c.getApplicationContext().getSystemService("window");
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
                layoutParams.gravity = com.iapp.app.i.t(str3);
                view.setLayoutParams(layoutParams);
                this.f.post(new f0(this, windowManager, view, layoutParams));
            }
        }
    }

    @JavascriptInterface
    public void uycl(String str, boolean z2) {
        this.f.post(new p(str, z2));
    }

    @JavascriptInterface
    public void uycl(String str, boolean z2, int i2) {
        this.f.post(new q(str, z2, i2));
    }

    @JavascriptInterface
    public void uycl(boolean z2) {
        this.f.post(new o(z2));
    }

    @JavascriptInterface
    @TargetApi(11)
    public String uzd(String str, String str2) {
        Object x2 = x(str);
        if (x2 == null) {
            x2 = this.f1859c.getSystemService("vibrator");
        }
        Vibrator vibrator = (Vibrator) x2;
        if (str2.equals("sp")) {
            try {
                vibrator.cancel();
                return null;
            } catch (Exception unused) {
                return null;
            }
        }
        if (str2.equals("ip")) {
            return Build.VERSION.SDK_INT >= 11 ? String.valueOf(vibrator.hasVibrator()) : "false";
        }
        vibrator.vibrate(Long.parseLong(str2));
        return (str == null || !str.startsWith("^")) ? N("uzd", vibrator) : main2.set(str, vibrator);
    }

    @JavascriptInterface
    public String uzd(String str, String str2, boolean z2) {
        Object x2 = x(str);
        if (x2 == null) {
            x2 = this.f1859c.getSystemService("vibrator");
        }
        Vibrator vibrator = (Vibrator) x2;
        String[] F = F(str2);
        int length = F.length;
        long[] jArr = new long[length];
        for (int i2 = 0; i2 < length; i2++) {
            jArr[i2] = Long.parseLong(F[i2]);
        }
        vibrator.vibrate(jArr, z2 ? 1 : -1);
        return (str == null || !str.startsWith("^")) ? N("uzd", vibrator) : main2.set(str, vibrator);
    }

    @JavascriptInterface
    public int zdp(float f2) {
        return c.b.a.a.p.l(this.f1859c, f2);
    }

    @JavascriptInterface
    public String zj(String str) {
        return I("zj", c.b.a.a.f.n(str, new Object[]{"Activity", this.f1859c}));
    }

    @JavascriptInterface
    public String zj(String str, String str2) {
        Object[] objArr;
        Object[] G = G(str2);
        if (G == null) {
            objArr = new Object[]{"Activity", this.f1859c};
        } else {
            int length = G.length;
            Object[] objArr2 = new Object[length + 2];
            objArr2[0] = "Activity";
            objArr2[1] = this.f1859c;
            for (int i2 = 0; i2 < length; i2++) {
                objArr2[i2 + 2] = G[i2];
            }
            objArr = objArr2;
        }
        return I("zj", c.b.a.a.f.n(str, objArr));
    }

    @JavascriptInterface
    public int zpd(float f2) {
        return c.b.a.a.p.w(this.f1859c, f2);
    }

    @JavascriptInterface
    public int zps(float f2) {
        return c.b.a.a.p.x(this.f1859c, f2);
    }

    @JavascriptInterface
    public int zsp(float f2) {
        return c.b.a.a.p.A(this.f1859c, f2);
    }
}
