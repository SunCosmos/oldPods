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
import android.text.Editable;
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
import org.keplerproject.luajava.LuaObject;

@SuppressLint({"HandlerLeak"})
/* loaded from: classes.dex */
public class Aid_luaCode {
    public static AlertDialog alertdialog;
    private static HashMap<String, Object> f = new HashMap<>();
    private static int g = 0;
    public static HashMap<String, Object> ss_dim;
    private HashMap<String, Object> a;
    private Activity b;

    /* renamed from: c, reason: collision with root package name */
    private Context f1896c;

    /* renamed from: d, reason: collision with root package name */
    private com.iapp.app.d f1897d;
    private Handler e;

    /* loaded from: classes.dex */
    class a implements DialogInterface.OnClickListener {
        final /* synthetic */ LuaObject a;

        a(Aid_luaCode aid_luaCode, LuaObject luaObject) {
            this.a = luaObject;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            this.a.callxNoErr();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a0 extends RecyclerView.OnScrollListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.d b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1898c;

        a0(Aid_luaCode aid_luaCode, boolean z, com.iapp.app.d dVar, boolean z2) {
            this.a = z;
            this.b = dVar;
            this.f1898c = z2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (this.a) {
                int id = recyclerView.getId();
                this.b.d("onscrollstatechanged" + id, Integer.valueOf(id), recyclerView, Integer.valueOf(i2));
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            if (this.f1898c) {
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
                int id = recyclerView.getId();
                com.iapp.app.d dVar = this.b;
                String str = "onscrolled" + id;
                Object[] objArr = new Object[8];
                objArr[0] = Integer.valueOf(id);
                objArr[1] = recyclerView;
                objArr[2] = Integer.valueOf(i4);
                objArr[3] = Integer.valueOf(childCount);
                objArr[4] = Integer.valueOf(itemCount);
                objArr[5] = Integer.valueOf(i2);
                objArr[6] = Integer.valueOf(i3);
                if (i3 > 0 && childCount + i4 >= itemCount) {
                    z = true;
                }
                objArr[7] = Boolean.valueOf(z);
                dVar.d(str, objArr);
            }
        }
    }

    /* loaded from: classes.dex */
    class a1 implements DialogInterface.OnClickListener {
        final /* synthetic */ LuaObject a;

        a1(Aid_luaCode aid_luaCode, LuaObject luaObject) {
            this.a = luaObject;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            this.a.callxNoErr();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements OnFileDownStatusListener {
        b(Aid_luaCode aid_luaCode) {
        }

        @Override // com.iapp.interfaces.OnFileDownStatusListener
        public void complete(int i2, Object obj) {
            if (obj != null && (obj instanceof LuaObject)) {
                ((LuaObject) obj).callNoErr(Integer.valueOf(i2));
            }
        }

        @Override // com.iapp.interfaces.OnFileDownStatusListener
        public void resultStatus(int i2, int i3, Object obj) {
            if (obj != null && (obj instanceof LuaObject)) {
                ((LuaObject) obj).callNoErr(Integer.valueOf(i2), Integer.valueOf(i3));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b0 extends GestureDetector.SimpleOnGestureListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.iapp.app.d f1899c;

        b0(Aid_luaCode aid_luaCode, boolean z, View view, com.iapp.app.d dVar) {
            this.a = z;
            this.b = view;
            this.f1899c = dVar;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            RecyclerView recyclerView;
            View findChildViewUnder;
            if (!this.a || (findChildViewUnder = (recyclerView = (RecyclerView) this.b).findChildViewUnder(motionEvent.getX(), motionEvent.getY())) == null) {
                return;
            }
            int id = recyclerView.getId();
            this.f1899c.d("clickitem" + id, Integer.valueOf(id), recyclerView, Integer.valueOf(recyclerView.getChildLayoutPosition(findChildViewUnder)), findChildViewUnder);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            RecyclerView recyclerView;
            View findChildViewUnder;
            if (!this.a || (findChildViewUnder = (recyclerView = (RecyclerView) this.b).findChildViewUnder(motionEvent.getX(), motionEvent.getY())) == null) {
                return false;
            }
            int id = recyclerView.getId();
            this.f1899c.d("clickitem" + id, Integer.valueOf(id), recyclerView, Integer.valueOf(recyclerView.getChildLayoutPosition(findChildViewUnder)), findChildViewUnder);
            return true;
        }
    }

    /* loaded from: classes.dex */
    class b1 implements DialogInterface.OnClickListener {
        final /* synthetic */ LuaObject a;

        b1(Aid_luaCode aid_luaCode, LuaObject luaObject) {
            this.a = luaObject;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            this.a.callxNoErr();
        }
    }

    /* loaded from: classes.dex */
    class c extends Thread {
        final /* synthetic */ LuaObject a;

        c(Aid_luaCode aid_luaCode, LuaObject luaObject) {
            this.a = luaObject;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            this.a.callxNoErr();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c0 implements RecyclerView.OnItemTouchListener {
        final /* synthetic */ GestureDetector a;

        c0(Aid_luaCode aid_luaCode, GestureDetector gestureDetector) {
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
    class c1 implements DialogInterface.OnClickListener {
        final /* synthetic */ LuaObject a;

        c1(Aid_luaCode aid_luaCode, LuaObject luaObject) {
            this.a = luaObject;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            this.a.callxNoErr();
        }
    }

    /* loaded from: classes.dex */
    class d implements Animation.AnimationListener {
        final /* synthetic */ LuaObject a;
        final /* synthetic */ LuaObject b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ LuaObject f1900c;

        d(Aid_luaCode aid_luaCode, LuaObject luaObject, LuaObject luaObject2, LuaObject luaObject3) {
            this.a = luaObject;
            this.b = luaObject2;
            this.f1900c = luaObject3;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            LuaObject luaObject = this.a;
            if (luaObject == null) {
                return;
            }
            luaObject.callxNoErr();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            LuaObject luaObject = this.b;
            if (luaObject == null) {
                return;
            }
            luaObject.callxNoErr();
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            LuaObject luaObject = this.f1900c;
            if (luaObject == null) {
                return;
            }
            luaObject.callxNoErr();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d0 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.iapp.app.d f1901c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f1902d;
        final /* synthetic */ boolean e;

        d0(Aid_luaCode aid_luaCode, boolean z, View view, com.iapp.app.d dVar, boolean z2, boolean z3) {
            this.a = z;
            this.b = view;
            this.f1901c = dVar;
            this.f1902d = z2;
            this.e = z3;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            if (this.e) {
                int id = this.b.getId();
                this.f1901c.d("onpagescrollstatechanged" + id, Integer.valueOf(id), this.b, Integer.valueOf(i2));
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            if (this.f1902d) {
                int id = this.b.getId();
                this.f1901c.d("onpagescrolled" + id, Integer.valueOf(id), this.b, Integer.valueOf(i2), Float.valueOf(f), Integer.valueOf(i3));
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            if (this.a) {
                int id = this.b.getId();
                this.f1901c.d("onpageselected" + id, Integer.valueOf(id), this.b, Integer.valueOf(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class d1 {
        public ArrayList<HashMap<Integer, Object>> a;
        public Object b;

        private d1(Aid_luaCode aid_luaCode) {
            this.a = new ArrayList<>();
            this.b = null;
        }

        /* synthetic */ d1(Aid_luaCode aid_luaCode, k kVar) {
            this(aid_luaCode);
        }
    }

    /* loaded from: classes.dex */
    class e implements a.InterfaceC0049a {
        final /* synthetic */ LuaObject a;
        final /* synthetic */ LuaObject b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ LuaObject f1903c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ LuaObject f1904d;

        e(Aid_luaCode aid_luaCode, LuaObject luaObject, LuaObject luaObject2, LuaObject luaObject3, LuaObject luaObject4) {
            this.a = luaObject;
            this.b = luaObject2;
            this.f1903c = luaObject3;
            this.f1904d = luaObject4;
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void a(c.d.a.a aVar) {
            LuaObject luaObject = this.a;
            if (luaObject == null) {
                return;
            }
            luaObject.callxNoErr();
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void b(c.d.a.a aVar) {
            LuaObject luaObject = this.b;
            if (luaObject == null) {
                return;
            }
            luaObject.callxNoErr();
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void c(c.d.a.a aVar) {
            LuaObject luaObject = this.f1903c;
            if (luaObject == null) {
                return;
            }
            luaObject.callxNoErr();
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void d(c.d.a.a aVar) {
            LuaObject luaObject = this.f1904d;
            if (luaObject == null) {
                return;
            }
            luaObject.callxNoErr();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e0 implements SwipeRefreshLayout.OnRefreshListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.iapp.app.d f1905c;

        e0(Aid_luaCode aid_luaCode, boolean z, View view, com.iapp.app.d dVar) {
            this.a = z;
            this.b = view;
            this.f1905c = dVar;
        }

        @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
        public void onRefresh() {
            if (this.a) {
                int id = this.b.getId();
                this.f1905c.d("onrefresh" + id, Integer.valueOf(id), this.b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class e1 extends BaseAdapter {
        private Context a;
        private c.b.a.a.h b;

        /* renamed from: c, reason: collision with root package name */
        private d1 f1906c;
        private int g;
        private Iterator j;
        private a k;

        /* renamed from: d, reason: collision with root package name */
        private String f1907d = null;
        private LuaObject e = null;
        private int f = 0;
        private int h = -1;

        /* renamed from: i, reason: collision with root package name */
        private int f1908i = -2;
        private ArrayList<Integer> l = null;

        /* loaded from: classes.dex */
        private final class a {
            public ViewGroup a;
            public int[] b;

            /* renamed from: c, reason: collision with root package name */
            public View[] f1909c;

            private a(e1 e1Var) {
                this.b = null;
                this.f1909c = null;
            }

            /* synthetic */ a(e1 e1Var, k kVar) {
                this(e1Var);
            }
        }

        public e1(Context context) {
            this.a = context;
            this.b = new c.b.a.a.h(context, this, 1);
        }

        public ArrayList<HashMap<Integer, Object>> a() {
            return this.f1906c.a;
        }

        public void b(String str, d1 d1Var) {
            this.f1907d = str;
            this.f1906c = d1Var;
            this.f = Aid_luaCode.this.i(str.substring(0, str.length() - 4));
        }

        public void c(int i2, int i3) {
            this.h = i2;
            this.f1908i = i3;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            d1 d1Var = this.f1906c;
            if (d1Var == null) {
                return 0;
            }
            return d1Var.a.size();
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
            HashMap<Integer, Object> hashMap = this.f1906c.a.get(i2);
            int i3 = 0;
            if (view == null) {
                this.k = new a(this, null);
                LinearLayout linearLayout = new LinearLayout(this.a);
                linearLayout.setLayoutParams(new AbsListView.LayoutParams(this.h, this.f1908i));
                linearLayout.setOrientation(1);
                LinearLayout linearLayout2 = new LinearLayout(this.a);
                linearLayout2.setLayoutParams(new ViewGroup.LayoutParams(this.h, this.f1908i));
                linearLayout2.setOrientation(1);
                linearLayout.addView(linearLayout2);
                this.e = Aid_luaCode.this.openRestoreinterface(this.f1907d, linearLayout2, this.f, hashMap);
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
                aVar.f1909c = new View[size];
                aVar.b = new int[size];
                if (this.l == null) {
                    this.l = new ArrayList<>();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(',');
                    for (int i4 = 0; i4 < size; i4++) {
                        int intValue = ((Integer) arrayList.get(i4)).intValue();
                        this.g = intValue;
                        a aVar2 = this.k;
                        aVar2.b[i4] = intValue;
                        if (intValue > 0) {
                            aVar2.f1909c[i4] = linearLayout.findViewById(intValue + this.f);
                        }
                        stringBuffer.append(this.g + this.f);
                        stringBuffer.append(',');
                    }
                    Aid_luaCode.this.setClickable(this.l, stringBuffer, linearLayout);
                } else {
                    for (int i5 = 0; i5 < size; i5++) {
                        int intValue2 = ((Integer) arrayList.get(i5)).intValue();
                        this.g = intValue2;
                        a aVar3 = this.k;
                        aVar3.b[i5] = intValue2;
                        if (intValue2 > 0) {
                            aVar3.f1909c[i5] = linearLayout.findViewById(intValue2 + this.f);
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
            while (true) {
                a aVar5 = this.k;
                int[] iArr = aVar5.b;
                if (i3 >= iArr.length) {
                    break;
                }
                if (iArr[i3] > 0) {
                    c.b.a.a.f.w(aVar5.f1909c[i3], hashMap.get(Integer.valueOf(iArr[i3])), hashMap, this.b);
                }
                i3++;
            }
            Iterator<Integer> it = this.l.iterator();
            while (it.hasNext()) {
                View findViewById = view2.findViewById(it.next().intValue());
                Object[] objArr = (Object[]) findViewById.getTag();
                objArr[3] = hashMap;
                findViewById.setTag(objArr);
            }
            LuaObject luaObject = this.e;
            if (luaObject != null) {
                luaObject.callxNoErr();
            }
            return view2;
        }
    }

    /* loaded from: classes.dex */
    class f implements OnMessagesListener {
        final /* synthetic */ LuaObject a;

        f(LuaObject luaObject) {
            this.a = luaObject;
        }

        @Override // com.iapp.interfaces.OnMessagesListener
        public void Message(Object obj, o.c cVar) {
            this.a.callNoErr(Aid_luaCode.this.t(obj), cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f0 implements CompoundButton.OnCheckedChangeListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.iapp.app.d f1910c;

        f0(Aid_luaCode aid_luaCode, boolean z, View view, com.iapp.app.d dVar) {
            this.a = z;
            this.b = view;
            this.f1910c = dVar;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (this.a) {
                int id = this.b.getId();
                this.f1910c.d("oncheckedchanged" + id, Integer.valueOf(id), this.b, Boolean.valueOf(z));
            }
        }
    }

    /* loaded from: classes.dex */
    private class f1 extends RecyclerView.Adapter<a> {

        /* renamed from: c, reason: collision with root package name */
        private Context f1911c;

        /* renamed from: d, reason: collision with root package name */
        private c.b.a.a.h f1912d;
        private d1 e;

        /* renamed from: i, reason: collision with root package name */
        private int f1913i;
        private Iterator l;
        private LuaObject f = null;
        private String g = null;
        private int h = 0;
        private int j = -1;
        private int k = -2;
        private ArrayList<Integer> m = null;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class a extends RecyclerView.ViewHolder {
            public ViewGroup s;
            public int[] t;
            public View[] u;

            public a(f1 f1Var, ViewGroup viewGroup) {
                super(viewGroup);
                this.t = null;
                this.u = null;
                this.s = viewGroup;
            }
        }

        public f1(Context context) {
            this.f1911c = context;
            this.f1912d = new c.b.a.a.h(context, this, 1);
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
                this.f = Aid_luaCode.this.openRestoreinterface(this.g, aVar.s, this.h, hashMap);
                this.l = hashMap.keySet().iterator();
                ArrayList arrayList = new ArrayList();
                while (this.l.hasNext()) {
                    int parseInt = Integer.parseInt(String.valueOf(this.l.next()));
                    this.f1913i = parseInt;
                    if (parseInt > 0) {
                        arrayList.add(Integer.valueOf(parseInt));
                    }
                }
                int size = arrayList.size();
                aVar.u = new View[size];
                aVar.t = new int[size];
                if (this.m == null) {
                    this.m = new ArrayList<>();
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(',');
                    for (int i4 = 0; i4 < size; i4++) {
                        int intValue = ((Integer) arrayList.get(i4)).intValue();
                        this.f1913i = intValue;
                        aVar.t[i4] = intValue;
                        if (intValue > 0) {
                            aVar.u[i4] = aVar.s.findViewById(intValue + this.h);
                        }
                        stringBuffer.append(this.f1913i + this.h);
                        stringBuffer.append(',');
                    }
                    Aid_luaCode.this.setClickable(this.m, stringBuffer, aVar.s);
                } else {
                    for (int i5 = 0; i5 < size; i5++) {
                        int intValue2 = ((Integer) arrayList.get(i5)).intValue();
                        this.f1913i = intValue2;
                        aVar.t[i5] = intValue2;
                        if (intValue2 > 0) {
                            aVar.u[i5] = aVar.s.findViewById(intValue2 + this.h);
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
                    c.b.a.a.f.w(aVar.u[i3], hashMap.get(Integer.valueOf(iArr[i3])), hashMap, this.f1912d);
                }
                i3++;
            }
            Iterator<Integer> it = this.m.iterator();
            while (it.hasNext()) {
                View findViewById = aVar.s.findViewById(it.next().intValue());
                Object[] objArr = (Object[]) findViewById.getTag();
                objArr[3] = hashMap;
                findViewById.setTag(objArr);
            }
            LuaObject luaObject = this.f;
            if (luaObject != null) {
                luaObject.callxNoErr();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public a onCreateViewHolder(ViewGroup viewGroup, int i2) {
            LinearLayout linearLayout = new LinearLayout(this.f1911c);
            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(this.j, this.k));
            linearLayout.setOrientation(1);
            return new a(this, linearLayout);
        }

        public void d(String str, d1 d1Var) {
            this.g = str;
            this.e = d1Var;
            this.h = Aid_luaCode.this.i(str.substring(0, str.length() - 4));
        }

        public void e(int i2, int i3) {
            this.j = i2;
            this.k = i3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            d1 d1Var = this.e;
            if (d1Var == null) {
                return 0;
            }
            return d1Var.a.size();
        }
    }

    /* loaded from: classes.dex */
    class g implements OnMessagesListener {
        final /* synthetic */ LuaObject a;

        g(LuaObject luaObject) {
            this.a = luaObject;
        }

        @Override // com.iapp.interfaces.OnMessagesListener
        public void Message(Object obj, o.c cVar) {
            this.a.callNoErr(Aid_luaCode.this.t(obj), cVar);
        }
    }

    /* loaded from: classes.dex */
    class g0 extends Handler {
        g0(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                Toast.makeText(Aid_luaCode.this.f1896c, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                ((LuaObject) message.obj).callxNoErr();
            }
        }
    }

    /* loaded from: classes.dex */
    class h implements View.OnClickListener {
        final /* synthetic */ LuaObject a;

        h(Aid_luaCode aid_luaCode, LuaObject luaObject) {
            this.a = luaObject;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.callNoErr(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h0 implements AppBarLayout.OnOffsetChangedListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.d b;

        h0(Aid_luaCode aid_luaCode, boolean z, com.iapp.app.d dVar) {
            this.a = z;
            this.b = dVar;
        }

        @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        public void onOffsetChanged(AppBarLayout appBarLayout, int i2) {
            if (this.a) {
                int id = appBarLayout.getId();
                this.b.d("onoffsetchanged" + id, Integer.valueOf(id), appBarLayout, Integer.valueOf(i2));
            }
        }
    }

    /* loaded from: classes.dex */
    class i extends ActionBarDrawerToggle {
        final /* synthetic */ boolean l;
        final /* synthetic */ DrawerLayout m;
        final /* synthetic */ boolean n;
        final /* synthetic */ boolean o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        i(Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i2, int i3, boolean z, DrawerLayout drawerLayout2, boolean z2, boolean z3) {
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
                Aid_luaCode.this.f1897d.d("ondrawerclosed" + id, Integer.valueOf(id), this.m, view);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(View view) {
            if (this.n) {
                int id = this.m.getId();
                Aid_luaCode.this.f1897d.d("ondraweropened" + id, Integer.valueOf(id), this.m, view);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            if (!this.o) {
                return false;
            }
            int id = this.m.getId();
            return Aid_luaCode.this.f1897d.e("onoptionsitemselected" + id, Integer.valueOf(id), this.m, menuItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class i0 implements com.iapp.app.x5.a {
        i0(Aid_luaCode aid_luaCode) {
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
    class j implements View.OnClickListener {
        final /* synthetic */ LuaObject a;

        j(Aid_luaCode aid_luaCode, LuaObject luaObject) {
            this.a = luaObject;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a.callNoErr(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class j0 implements View.OnClickListener {
        final /* synthetic */ LuaObject[] a;

        j0(Aid_luaCode aid_luaCode, LuaObject[] luaObjectArr) {
            this.a = luaObjectArr;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.a[0].callNoErr(Integer.valueOf(view.getId()), view);
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
                Toast.makeText(Aid_luaCode.this.f1896c, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                ((LuaObject) message.obj).callxNoErr();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class k0 implements View.OnTouchListener {
        final /* synthetic */ LuaObject[] a;

        k0(Aid_luaCode aid_luaCode, LuaObject[] luaObjectArr) {
            this.a = luaObjectArr;
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return this.a[0].callBoolNoErr(Integer.valueOf(view.getId()), view, Integer.valueOf(motionEvent.getAction()), Float.valueOf(motionEvent.getX()), Float.valueOf(motionEvent.getY()), Float.valueOf(motionEvent.getRawX()), Float.valueOf(motionEvent.getRawY()));
        }
    }

    /* loaded from: classes.dex */
    class l extends WebChromeClient {
        l() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            c.b.a.a.t.P2(Aid_luaCode.this.f1896c, "JsErr：\nLine" + consoleMessage.lineNumber() + ": " + consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class l0 implements View.OnLongClickListener {
        final /* synthetic */ LuaObject[] a;

        l0(Aid_luaCode aid_luaCode, LuaObject[] luaObjectArr) {
            this.a = luaObjectArr;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            return this.a[0].callBoolNoErr(Integer.valueOf(view.getId()), view);
        }
    }

    /* loaded from: classes.dex */
    class m extends WebChromeClient {
        m() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            c.b.a.a.t.P2(Aid_luaCode.this.f1896c, "JsErr：\nLine" + consoleMessage.lineNumber() + ": " + consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class m0 implements View.OnKeyListener {
        final /* synthetic */ LuaObject[] a;

        m0(Aid_luaCode aid_luaCode, LuaObject[] luaObjectArr) {
            this.a = luaObjectArr;
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            return this.a[0].callBoolNoErr(Integer.valueOf(view.getId()), view, Integer.valueOf(i2), Integer.valueOf(keyEvent.getAction()), Integer.valueOf(keyEvent.getRepeatCount()), keyEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class n implements View.OnClickListener {
        final /* synthetic */ com.iapp.app.d a;

        n(Aid_luaCode aid_luaCode, com.iapp.app.d dVar) {
            this.a = dVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();
            this.a.d("clicki" + id, Integer.valueOf(id), view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class n0 implements TextView.OnEditorActionListener {
        final /* synthetic */ LuaObject[] a;

        n0(Aid_luaCode aid_luaCode, LuaObject[] luaObjectArr) {
            this.a = luaObjectArr;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            int id = textView.getId();
            LuaObject[] luaObjectArr = this.a;
            return keyEvent != null ? luaObjectArr[0].callBoolNoErr(Integer.valueOf(id), textView, Integer.valueOf(i2), Integer.valueOf(keyEvent.getAction()), Integer.valueOf(keyEvent.getRepeatCount()), Integer.valueOf(keyEvent.getKeyCode()), keyEvent) : luaObjectArr[0].callBoolNoErr(Integer.valueOf(id), textView, Integer.valueOf(i2), null, null, null, keyEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class o implements View.OnTouchListener {
        final /* synthetic */ com.iapp.app.d a;

        o(Aid_luaCode aid_luaCode, com.iapp.app.d dVar) {
            this.a = dVar;
        }

        @Override // android.view.View.OnTouchListener
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int id = view.getId();
            return this.a.e("touchmonitor" + id, Integer.valueOf(id), view, Integer.valueOf(motionEvent.getAction()), Float.valueOf(motionEvent.getX()), Float.valueOf(motionEvent.getY()), Float.valueOf(motionEvent.getRawX()), Float.valueOf(motionEvent.getRawY()));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class o0 implements TextWatcher {
        final /* synthetic */ LuaObject[] a;
        final /* synthetic */ View b;

        o0(Aid_luaCode aid_luaCode, LuaObject[] luaObjectArr, View view) {
            this.a = luaObjectArr;
            this.b = view;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            LuaObject[] luaObjectArr = this.a;
            if (luaObjectArr[2] != null) {
                luaObjectArr[2].callBoolNoErr(Integer.valueOf(this.b.getId()), this.b, editable.toString());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            LuaObject[] luaObjectArr = this.a;
            if (luaObjectArr[1] != null) {
                luaObjectArr[1].callBoolNoErr(Integer.valueOf(this.b.getId()), this.b, charSequence.toString(), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            LuaObject[] luaObjectArr = this.a;
            if (luaObjectArr[0] != null) {
                luaObjectArr[0].callBoolNoErr(Integer.valueOf(this.b.getId()), this.b, charSequence.toString(), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class p implements View.OnLongClickListener {
        final /* synthetic */ com.iapp.app.d a;

        p(Aid_luaCode aid_luaCode, com.iapp.app.d dVar) {
            this.a = dVar;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            int id = view.getId();
            return this.a.e("press" + id, Integer.valueOf(id), view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class p0 implements com.iapp.app.x5.a {
        final /* synthetic */ View a;
        final /* synthetic */ LuaObject[] b;

        p0(Aid_luaCode aid_luaCode, View view, LuaObject[] luaObjectArr) {
            this.a = view;
            this.b = luaObjectArr;
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            this.b[0].callBoolNoErr(Integer.valueOf(this.a.getId()), this.a, str, str2, str3, str4, Long.valueOf(j));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class q implements View.OnKeyListener {
        final /* synthetic */ com.iapp.app.d a;

        q(Aid_luaCode aid_luaCode, com.iapp.app.d dVar) {
            this.a = dVar;
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i2, KeyEvent keyEvent) {
            int id = view.getId();
            return this.a.e("keyboard" + id, Integer.valueOf(id), view, Integer.valueOf(i2), Integer.valueOf(keyEvent.getAction()), Integer.valueOf(keyEvent.getRepeatCount()), keyEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class q0 implements View.OnFocusChangeListener {
        final /* synthetic */ LuaObject[] a;

        q0(Aid_luaCode aid_luaCode, LuaObject[] luaObjectArr) {
            this.a = luaObjectArr;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            this.a[0].callNoErr(Integer.valueOf(view.getId()), view, Boolean.valueOf(z));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class r implements View.OnCreateContextMenuListener {
        final /* synthetic */ com.iapp.app.d a;

        r(Aid_luaCode aid_luaCode, com.iapp.app.d dVar) {
            this.a = dVar;
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
                this.a.d("onCreateContextMenu" + id + "x0", Integer.valueOf(id), view);
            }
        }
    }

    /* loaded from: classes.dex */
    class r0 extends Handler {
        r0(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                Toast.makeText(Aid_luaCode.this.f1896c, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                ((LuaObject) message.obj).callxNoErr();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class s implements TextView.OnEditorActionListener {
        final /* synthetic */ com.iapp.app.d a;

        s(Aid_luaCode aid_luaCode, com.iapp.app.d dVar) {
            this.a = dVar;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
            int id = textView.getId();
            com.iapp.app.d dVar = this.a;
            if (keyEvent != null) {
                return dVar.e("editormonitor" + id, Integer.valueOf(id), textView, Integer.valueOf(i2), Integer.valueOf(keyEvent.getAction()), Integer.valueOf(keyEvent.getRepeatCount()), Integer.valueOf(keyEvent.getKeyCode()), keyEvent);
            }
            return dVar.e("editormonitor" + id, Integer.valueOf(id), textView, Integer.valueOf(i2), null, null, null, keyEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class s0 implements AbsListView.OnScrollListener {
        final /* synthetic */ LuaObject[] a;

        s0(Aid_luaCode aid_luaCode, LuaObject[] luaObjectArr) {
            this.a = luaObjectArr;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            if (this.a[1] != null) {
                this.a[1].callNoErr(Integer.valueOf(absListView.getId()), absListView, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (this.a[0] != null) {
                this.a[0].callNoErr(Integer.valueOf(absListView.getId()), absListView, Integer.valueOf(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class t implements View.OnFocusChangeListener {
        final /* synthetic */ com.iapp.app.d a;

        t(Aid_luaCode aid_luaCode, com.iapp.app.d dVar) {
            this.a = dVar;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            int id = view.getId();
            this.a.d("focuschange" + id, Integer.valueOf(id), view, Boolean.valueOf(z));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class t0 implements AdapterView.OnItemClickListener {
        final /* synthetic */ LuaObject[] a;

        t0(Aid_luaCode aid_luaCode, LuaObject[] luaObjectArr) {
            this.a = luaObjectArr;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            this.a[0].callNoErr(Integer.valueOf(adapterView.getId()), adapterView, Integer.valueOf(i2), Long.valueOf(j), view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class u implements AbsListView.OnScrollListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.d b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1914c;

        u(Aid_luaCode aid_luaCode, boolean z, com.iapp.app.d dVar, boolean z2) {
            this.a = z;
            this.b = dVar;
            this.f1914c = z2;
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            if (this.f1914c) {
                int id = absListView.getId();
                this.b.d("onscroll" + id, Integer.valueOf(id), absListView, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (this.a) {
                int id = absListView.getId();
                this.b.d("onscrollstatechanged" + id, Integer.valueOf(id), absListView, Integer.valueOf(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class u0 implements AdapterView.OnItemSelectedListener {
        final /* synthetic */ LuaObject[] a;

        u0(Aid_luaCode aid_luaCode, LuaObject[] luaObjectArr) {
            this.a = luaObjectArr;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            if (this.a[0] != null) {
                this.a[0].callNoErr(Integer.valueOf(adapterView.getId()), adapterView, view, Integer.valueOf(i2), Long.valueOf(j));
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            if (this.a[1] != null) {
                this.a[1].callNoErr(Integer.valueOf(adapterView.getId()), adapterView);
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
                Toast.makeText(Aid_luaCode.this.f1896c, message.obj.toString(), 1).show();
            } else if (i2 == 2) {
                ((LuaObject) message.obj).callxNoErr();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class v0 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ LuaObject[] a;
        final /* synthetic */ View b;

        v0(Aid_luaCode aid_luaCode, LuaObject[] luaObjectArr, View view) {
            this.a = luaObjectArr;
            this.b = view;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i2) {
            LuaObject[] luaObjectArr = this.a;
            if (luaObjectArr[2] != null) {
                luaObjectArr[2].callNoErr(Integer.valueOf(this.b.getId()), this.b, Integer.valueOf(i2));
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i2, float f, int i3) {
            LuaObject[] luaObjectArr = this.a;
            if (luaObjectArr[1] != null) {
                luaObjectArr[1].callNoErr(Integer.valueOf(this.b.getId()), this.b, Integer.valueOf(i2), Float.valueOf(f), Integer.valueOf(i3));
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i2) {
            LuaObject[] luaObjectArr = this.a;
            if (luaObjectArr[0] != null) {
                luaObjectArr[0].callNoErr(Integer.valueOf(this.b.getId()), this.b, Integer.valueOf(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class w implements AdapterView.OnItemClickListener {
        final /* synthetic */ com.iapp.app.d a;

        w(Aid_luaCode aid_luaCode, com.iapp.app.d dVar) {
            this.a = dVar;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            int id = adapterView.getId();
            this.a.d("clickitem" + id, Integer.valueOf(id), adapterView, Integer.valueOf(i2), Long.valueOf(j), view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class w0 extends ActionBarDrawerToggle {
        final /* synthetic */ LuaObject[] l;
        final /* synthetic */ View m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        w0(Aid_luaCode aid_luaCode, Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int i2, int i3, LuaObject[] luaObjectArr, View view) {
            super(activity, drawerLayout, toolbar, i2, i3);
            this.l = luaObjectArr;
            this.m = view;
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(View view) {
            LuaObject[] luaObjectArr = this.l;
            if (luaObjectArr[0] != null) {
                luaObjectArr[0].callNoErr(Integer.valueOf(this.m.getId()), this.m, view);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle, androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(View view) {
            LuaObject[] luaObjectArr = this.l;
            if (luaObjectArr[1] != null) {
                luaObjectArr[1].callNoErr(Integer.valueOf(this.m.getId()), this.m, view);
            }
        }

        @Override // androidx.appcompat.app.ActionBarDrawerToggle
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            LuaObject[] luaObjectArr = this.l;
            if (luaObjectArr[2] != null) {
                return luaObjectArr[2].callBoolNoErr(Integer.valueOf(this.m.getId()), this.m, menuItem);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class x implements AdapterView.OnItemSelectedListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.d b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1915c;

        x(Aid_luaCode aid_luaCode, boolean z, com.iapp.app.d dVar, boolean z2) {
            this.a = z;
            this.b = dVar;
            this.f1915c = z2;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            if (this.a) {
                int id = adapterView.getId();
                this.b.d("onitemselected" + id, Integer.valueOf(id), adapterView, view, Integer.valueOf(i2), Long.valueOf(j));
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
            if (this.f1915c) {
                int id = adapterView.getId();
                this.b.d("onnothingselected" + id, Integer.valueOf(id), adapterView);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class x0 implements SeekBar.OnSeekBarChangeListener {
        final /* synthetic */ LuaObject[] a;

        x0(Aid_luaCode aid_luaCode, LuaObject[] luaObjectArr) {
            this.a = luaObjectArr;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            if (this.a[2] != null) {
                this.a[2].callNoErr(Integer.valueOf(seekBar.getId()), seekBar, Integer.valueOf(i2), Boolean.valueOf(z));
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (this.a[1] != null) {
                this.a[1].callNoErr(Integer.valueOf(seekBar.getId()), seekBar);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (this.a[0] != null) {
                this.a[0].callNoErr(Integer.valueOf(seekBar.getId()), seekBar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class y implements SeekBar.OnSeekBarChangeListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ com.iapp.app.d b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1916c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f1917d;

        y(Aid_luaCode aid_luaCode, boolean z, com.iapp.app.d dVar, boolean z2, boolean z3) {
            this.a = z;
            this.b = dVar;
            this.f1916c = z2;
            this.f1917d = z3;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            if (this.f1917d) {
                int id = seekBar.getId();
                this.b.d("onprogresschanged2" + id, Integer.valueOf(id), seekBar, Integer.valueOf(i2), Boolean.valueOf(z));
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (this.f1916c) {
                int id = seekBar.getId();
                this.b.d("onstarttrackingtouch" + id, Integer.valueOf(id), seekBar);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (this.a) {
                int id = seekBar.getId();
                this.b.d("onstoptrackingtouch" + id, Integer.valueOf(id), seekBar);
            }
        }
    }

    /* loaded from: classes.dex */
    class y0 implements DialogInterface.OnClickListener {
        final /* synthetic */ LuaObject a;

        y0(Aid_luaCode aid_luaCode, LuaObject luaObject) {
            this.a = luaObject;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            this.a.callxNoErr();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class z implements TabLayout.OnTabSelectedListener {
        final /* synthetic */ boolean a;
        final /* synthetic */ View b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.iapp.app.d f1918c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f1919d;
        final /* synthetic */ boolean e;

        z(Aid_luaCode aid_luaCode, boolean z, View view, com.iapp.app.d dVar, boolean z2, boolean z3) {
            this.a = z;
            this.b = view;
            this.f1918c = dVar;
            this.f1919d = z2;
            this.e = z3;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
            if (this.e) {
                int id = this.b.getId();
                this.f1918c.d("ontabreselected" + id, Integer.valueOf(id), this.b, Integer.valueOf(tab.getPosition()), String.valueOf(tab.getText()), tab);
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            if (this.a) {
                int id = this.b.getId();
                this.f1918c.d("ontabselected" + id, Integer.valueOf(id), this.b, Integer.valueOf(tab.getPosition()), String.valueOf(tab.getText()), tab);
            }
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
            if (this.f1919d) {
                int id = this.b.getId();
                this.f1918c.d("ontabunselected" + id, Integer.valueOf(id), this.b, Integer.valueOf(tab.getPosition()), String.valueOf(tab.getText()), tab);
            }
        }
    }

    /* loaded from: classes.dex */
    class z0 implements DialogInterface.OnClickListener {
        final /* synthetic */ LuaObject a;

        z0(Aid_luaCode aid_luaCode, LuaObject luaObject) {
            this.a = luaObject;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            this.a.callxNoErr();
        }
    }

    public Aid_luaCode(Activity activity) {
        this.a = new HashMap<>();
        this.b = null;
        this.f1896c = null;
        this.f1897d = null;
        this.e = null;
        this.f1896c = activity;
        this.b = activity;
        this.e = new k(activity.getMainLooper());
    }

    public Aid_luaCode(Activity activity, com.iapp.app.d dVar) {
        this.a = new HashMap<>();
        this.b = null;
        this.f1896c = null;
        this.f1897d = null;
        this.e = null;
        this.f1896c = activity;
        this.b = activity;
        this.f1897d = dVar;
        this.e = new g0(activity.getMainLooper());
    }

    public Aid_luaCode(Context context, Activity activity) {
        this.a = new HashMap<>();
        this.b = null;
        this.f1896c = null;
        this.f1897d = null;
        this.e = null;
        this.f1896c = context;
        this.b = activity;
        this.e = new v(context.getMainLooper());
    }

    public Aid_luaCode(Context context, Activity activity, com.iapp.app.d dVar) {
        this.a = new HashMap<>();
        this.b = null;
        this.f1896c = null;
        this.f1897d = null;
        this.e = null;
        this.f1896c = context;
        this.b = activity;
        this.f1897d = dVar;
        this.e = new r0(context.getMainLooper());
    }

    private void A(File file) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.intent.action.VIEW");
        c.b.a.a.k.a(this.f1896c, intent, file, c.b.a.a.p.p(file));
        this.f1896c.startActivity(intent);
    }

    @TargetApi(11)
    private void B(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    @TargetApi(16)
    private void C(WebView webView, String str, StringBuffer stringBuffer, com.iapp.app.d dVar) {
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAppCachePath(this.f1896c.getApplicationContext().getDir("cache", 0).getPath());
        webView.getSettings().setAppCacheMaxSize(8388608L);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDatabasePath(this.f1896c.getApplicationContext().getDir("database", 0).getPath());
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
            new com.iapp.app.n(webView, dVar);
        } else {
            webView.setDownloadListener(new i0(this));
        }
        new com.iapp.app.x5.b().f(webView, str, stringBuffer, this.b, dVar, this);
        B(webView);
    }

    private void D(String str) {
        c.b.a.a.f.G(this.f1896c, str);
    }

    private String[] E(String str, char c2) {
        return c.b.a.a.q.b(str, c2);
    }

    private String F(String str) {
        return str.substring(str.indexOf(40) + 1, str.lastIndexOf(41));
    }

    private void G(Camera camera) {
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

    private void H(Camera camera) {
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

    private void f(View view, int i2, String str, StringBuffer stringBuffer, com.iapp.app.d dVar) {
        if (addViewEventItme(stringBuffer, str, i2, "clicki", "st_vId,st_vW")) {
            view.setOnClickListener(new n(this, dVar));
        }
        if (addViewEventItme(stringBuffer, str, i2, "touchmonitor", "st_vId,st_vW,st_eA,st_eX,st_eY,st_rX,st_rY")) {
            view.setOnTouchListener(new o(this, dVar));
        }
        if (addViewEventItme(stringBuffer, str, i2, "press", "st_vId,st_vW")) {
            view.setOnLongClickListener(new p(this, dVar));
        }
        if (addViewEventItme(stringBuffer, str, i2, "keyboard", "st_vId,st_vW,st_kC,st_eA,st_eR,st_eT")) {
            view.setOnKeyListener(new q(this, dVar));
        }
        if (str.contains("<eventItme type=\"pressmenu\">")) {
            String c2 = c.b.a.a.p.c(str, "<eventItme type=\"pressmenu\">", "</eventItme>");
            String[] split = c2.split("\ncase ");
            for (int i3 = 1; i3 < split.length; i3++) {
                stringBuffer.append("function onCreateContextMenu" + i2 + "x" + i3 + "()\n" + c.b.a.a.p.c(split[i3], ":", "\nbreak") + "\nend\n");
            }
            String c3 = c.b.a.a.p.c(c2, "\ndefault:", "\nbreak");
            if (c3 != null) {
                stringBuffer.append("function onCreateContextMenu" + i2 + "x0(st_vId,st_vW)\n" + c3 + "\nend\n");
            }
            view.setOnCreateContextMenuListener(new r(this, dVar));
        }
        if (view instanceof TextView) {
            if (addViewEventItme(stringBuffer, str, i2, "editormonitor", "st_vId,st_vW,st_aI,st_eA,st_eR,st_eK,st_eT")) {
                ((TextView) view).setOnEditorActionListener(new s(this, dVar));
            }
            boolean addViewEventItme = addViewEventItme(stringBuffer, str, i2, "ontextchanged", "st_vId,st_vW,st_sS,st_sT,st_bE,st_cT");
            boolean addViewEventItme2 = addViewEventItme(stringBuffer, str, i2, "beforetextchanged", "st_vId,st_vW,st_sS,st_sT,st_cT,st_aR");
            boolean addViewEventItme3 = addViewEventItme(stringBuffer, str, i2, "aftertextchanged", "st_vId,st_vW,st_sS");
            if (addViewEventItme || addViewEventItme2 || addViewEventItme3) {
                new com.iapp.app.t((TextView) view, dVar, addViewEventItme, addViewEventItme2, addViewEventItme3);
            }
        }
        if (view instanceof WebView) {
            C((WebView) view, str, stringBuffer, dVar);
        }
        if (addViewEventItme(stringBuffer, str, i2, "focuschange", "st_vId,st_vW,st_hF")) {
            view.setOnFocusChangeListener(new t(this, dVar));
        }
        if (view instanceof AbsListView) {
            boolean addViewEventItme4 = addViewEventItme(stringBuffer, str, i2, "onscrollstatechanged", "st_vId,st_vW,st_sE");
            boolean addViewEventItme5 = addViewEventItme(stringBuffer, str, i2, "onscroll", "st_vId,st_vW,st_fM,st_vT,st_bT");
            if (addViewEventItme4 || addViewEventItme5) {
                ((AbsListView) view).setOnScrollListener(new u(this, addViewEventItme4, dVar, addViewEventItme5));
            }
        }
        if (view instanceof AdapterView) {
            if (addViewEventItme(stringBuffer, str, i2, "clickitem", "st_vId,st_vW,st_pN,st_iD,st_vW2")) {
                ((AdapterView) view).setOnItemClickListener(new w(this, dVar));
            }
            boolean addViewEventItme6 = addViewEventItme(stringBuffer, str, i2, "onitemselected", "st_vId,st_vW,st_vW2,st_pN,st_iD");
            boolean addViewEventItme7 = addViewEventItme(stringBuffer, str, i2, "onnothingselected", "st_vId,st_vW");
            if (addViewEventItme6 || addViewEventItme7) {
                ((AdapterView) view).setOnItemSelectedListener(new x(this, addViewEventItme6, dVar, addViewEventItme7));
            }
        }
        if (view instanceof ViewPager) {
            boolean addViewEventItme8 = addViewEventItme(stringBuffer, str, i2, "onpageselected", "st_vId,st_vW,st_pN");
            boolean addViewEventItme9 = addViewEventItme(stringBuffer, str, i2, "onpagescrolled", "st_vId,st_vW,st_pN,st_pT,st_pS");
            boolean addViewEventItme10 = addViewEventItme(stringBuffer, str, i2, "onpagescrollstatechanged", "st_vId,st_vW,st_sE");
            if (addViewEventItme8 || addViewEventItme9 || addViewEventItme10) {
                new com.iapp.app.q((ViewPager) view, dVar, addViewEventItme8, addViewEventItme9, addViewEventItme10);
            }
        }
        if (view instanceof DrawerLayout) {
            boolean addViewEventItme11 = addViewEventItme(stringBuffer, str, i2, "ondrawerclosed", "st_vId,st_vW,st_dW");
            boolean addViewEventItme12 = addViewEventItme(stringBuffer, str, i2, "ondraweropened", "st_vId,st_vW,st_dW");
            boolean addViewEventItme13 = addViewEventItme(stringBuffer, str, i2, "onoptionsitemselected", "st_vId,st_vW,st_iM");
            if (addViewEventItme11 || addViewEventItme12 || addViewEventItme13) {
                new com.iapp.app.o((DrawerLayout) view, dVar, addViewEventItme11, addViewEventItme12, addViewEventItme13);
            }
        }
        if ((view instanceof SeekBar) && (str.contains("<eventItme type=\"onstarttrackingtouch\">") || str.contains("<eventItme type=\"onstoptrackingtouch\">") || str.contains("<eventItme type=\"onprogresschanged2\">"))) {
            boolean addViewEventItme14 = addViewEventItme(stringBuffer, str, i2, "onstarttrackingtouch", "st_vId,st_vW");
            boolean addViewEventItme15 = addViewEventItme(stringBuffer, str, i2, "onstoptrackingtouch", "st_vId,st_vW");
            boolean addViewEventItme16 = addViewEventItme(stringBuffer, str, i2, "onprogresschanged2", "st_vId,st_vW,st_nS,st_fR");
            if (addViewEventItme14 || addViewEventItme15 || addViewEventItme16) {
                ((SeekBar) view).setOnSeekBarChangeListener(new y(this, addViewEventItme15, dVar, addViewEventItme14, addViewEventItme16));
            }
        }
        h(view, str, stringBuffer, dVar);
    }

    private void g(View view, String str, LuaObject[] luaObjectArr) {
        if (str.equals("clicki")) {
            view.setOnClickListener(new j0(this, luaObjectArr));
            return;
        }
        if (str.equals("touchmonitor")) {
            view.setOnTouchListener(new k0(this, luaObjectArr));
            return;
        }
        if (str.equals("press")) {
            view.setOnLongClickListener(new l0(this, luaObjectArr));
            return;
        }
        if (str.equals("keyboard")) {
            view.setOnKeyListener(new m0(this, luaObjectArr));
            return;
        }
        if (str.equals("editormonitor")) {
            ((TextView) view).setOnEditorActionListener(new n0(this, luaObjectArr));
            return;
        }
        if (str.equals("addtextchanged")) {
            ((TextView) view).addTextChangedListener(new o0(this, luaObjectArr, view));
            return;
        }
        if (str.equals("ondownloadstart")) {
            ((WebView) view).setDownloadListener(new p0(this, view, luaObjectArr));
            return;
        }
        if (str.equals("webviewclient")) {
            new com.iapp.app.x5.b().g(view, luaObjectArr);
            return;
        }
        if (str.equals("focuschange")) {
            view.setOnFocusChangeListener(new q0(this, luaObjectArr));
            return;
        }
        if (str.equals("onscroll")) {
            ((AbsListView) view).setOnScrollListener(new s0(this, luaObjectArr));
            return;
        }
        if (str.equals("clickitem")) {
            ((AdapterView) view).setOnItemClickListener(new t0(this, luaObjectArr));
            return;
        }
        if (str.equals("onitemselected")) {
            ((AdapterView) view).setOnItemSelectedListener(new u0(this, luaObjectArr));
            return;
        }
        if (str.equals("onpagechange")) {
            ((ViewPager) view).setOnPageChangeListener(new v0(this, luaObjectArr, view));
            return;
        }
        if (!str.equals("ondrawer")) {
            if (str.equals("onseekbarchange")) {
                ((SeekBar) view).setOnSeekBarChangeListener(new x0(this, luaObjectArr));
            }
        } else {
            DrawerLayout drawerLayout = (DrawerLayout) view;
            Activity activity = this.b;
            int i2 = c.b.a.a.f.b;
            drawerLayout.setDrawerListener(new w0(this, activity, drawerLayout, null, i2, i2, luaObjectArr, view));
        }
    }

    private void h(View view, String str, StringBuffer stringBuffer, com.iapp.app.d dVar) {
        if ((view instanceof TabLayout) && (str.contains("<eventItme type=\"ontabselected\">") || str.contains("<eventItme type=\"ontabunselected\">") || str.contains("<eventItme type=\"ontabreselected\">"))) {
            ((TabLayout) view).addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new z(this, addViewEventItme(stringBuffer, str, view.getId(), "ontabselected", "st_vId,st_vW,st_pN,st_tT,st_taB"), view, dVar, addViewEventItme(stringBuffer, str, view.getId(), "ontabunselected", "st_vId,st_vW,st_pN,st_tT,st_taB"), addViewEventItme(stringBuffer, str, view.getId(), "ontabreselected", "st_vId,st_vW,st_pN,st_tT,st_taB")));
        }
        if (view instanceof RecyclerView) {
            if (str.contains("<eventItme type=\"onscrollstatechanged\">") || str.contains("<eventItme type=\"onscrolled\">")) {
                ((RecyclerView) view).addOnScrollListener(new a0(this, addViewEventItme(stringBuffer, str, view.getId(), "onscrollstatechanged", "st_vId,st_vW,st_sE"), dVar, addViewEventItme(stringBuffer, str, view.getId(), "onscrolled", "st_vId,st_vW,st_fM,st_vT,st_bT,st_dX,st_dY,st_isB")));
            }
            if (str.contains("<eventItme type=\"clickitem\">")) {
                ((RecyclerView) view).addOnItemTouchListener(new c0(this, new GestureDetector(this.f1896c, new b0(this, addViewEventItme(stringBuffer, str, view.getId(), "clickitem", "st_vId,st_vW,st_pN,st_vW2"), view, dVar))));
            }
        }
        if ((view instanceof VerticalViewPager) && (str.contains("<eventItme type=\"onpageselected\">") || str.contains("<eventItme type=\"onpagescrolled\">") || str.contains("<eventItme type=\"onpagescrollstatechanged\">"))) {
            ((VerticalViewPager) view).setOnPageChangeListener(new d0(this, addViewEventItme(stringBuffer, str, view.getId(), "onpageselected", "st_vId,st_vW,st_pN"), view, dVar, addViewEventItme(stringBuffer, str, view.getId(), "onpagescrolled", "st_vId,st_vW,st_pN,st_pT,st_pS"), addViewEventItme(stringBuffer, str, view.getId(), "onpagescrollstatechanged", "st_vId,st_vW,st_sE")));
        }
        if ((view instanceof SwipeRefreshLayout) && str.contains("<eventItme type=\"onrefresh\">")) {
            ((SwipeRefreshLayout) view).setOnRefreshListener(new e0(this, addViewEventItme(stringBuffer, str, view.getId(), "onrefresh", "st_vId,st_vW"), view, dVar));
        }
        if ((view instanceof CompoundButton) && str.contains("<eventItme type=\"oncheckedchanged\">")) {
            ((CompoundButton) view).setOnCheckedChangeListener(new f0(this, addViewEventItme(stringBuffer, str, view.getId(), "oncheckedchanged", "st_vId,st_vW,st_iC"), view, dVar));
        }
        if ((view instanceof AppBarLayout) && str.contains("<eventItme type=\"onoffsetchanged\">")) {
            ((AppBarLayout) view).addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) new h0(this, addViewEventItme(stringBuffer, str, view.getId(), "onoffsetchanged", "st_vId,st_vW,st_vO"), dVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int i(String str) {
        Object obj = f.get(str);
        if (obj != null) {
            return Integer.parseInt(obj.toString());
        }
        int i2 = g + 10000;
        g = i2;
        f.put(str, Integer.valueOf(i2));
        return g;
    }

    private byte[] j(String str, char c2) {
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

    private void k(String str, Object obj, int i2) {
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

    private Object l(Object obj) {
        return obj instanceof Float ? Integer.valueOf(((Float) obj).intValue()) : obj instanceof Double ? Long.valueOf(((Double) obj).longValue()) : obj;
    }

    private View m(View view, Object obj, String str) {
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

    private View n(Object obj, String str) {
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

    private int o() {
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

    private String[] p() {
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

    private int[] q() {
        return new int[]{this.b.getWindowManager().getDefaultDisplay().getWidth(), this.b.getWindowManager().getDefaultDisplay().getHeight()};
    }

    private int[] r() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.b.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }

    private String[] s() {
        return new String[]{Build.MODEL, Build.BRAND, String.valueOf(Build.VERSION.SDK_INT)};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String t(Object obj) {
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

    private int u() {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return this.f1896c.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    private int v() {
        int identifier = this.f1896c.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return this.f1896c.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private String w(String str) {
        return c.b.a.a.f.o(this.f1896c, str);
    }

    private GradientDrawable.Orientation x(String str) {
        return str.equals("topbottom") ? GradientDrawable.Orientation.TOP_BOTTOM : str.equals("trbl") ? GradientDrawable.Orientation.TR_BL : str.equals("rightleft") ? GradientDrawable.Orientation.RIGHT_LEFT : str.equals("brtl") ? GradientDrawable.Orientation.BR_TL : str.equals("bottomtop") ? GradientDrawable.Orientation.BOTTOM_TOP : str.equals("bltr") ? GradientDrawable.Orientation.BL_TR : str.equals("leftright") ? GradientDrawable.Orientation.LEFT_RIGHT : str.equals("tlbr") ? GradientDrawable.Orientation.TL_BR : GradientDrawable.Orientation.TOP_BOTTOM;
    }

    private String y(String str) {
        String c2;
        String c3;
        Intent launchIntentForPackage = this.f1896c.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null || (c2 = c.b.a.a.p.c(launchIntentForPackage.toString(), "cmp=", " ")) == null || (c3 = c.b.a.a.p.c(c2, "/", null)) == null) {
            return "";
        }
        if (!c3.startsWith(".")) {
            return c3;
        }
        return str + c3;
    }

    private void z(c.b.a.a.e eVar, LuaObject luaObject, LuaObject luaObject2) {
        if (eVar != null) {
            eVar.j = luaObject;
            eVar.k = luaObject2;
            eVar.s(new b(this));
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
        stringBuffer.append("function " + str2 + i2 + "(" + str3 + ")\n" + c2 + "\nend\n");
        return true;
    }

    public Object addv(Object obj, String str) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 != null) {
            if ((n2 instanceof ViewPager) || (n2 instanceof VerticalViewPager)) {
                ArrayList arrayList = new ArrayList();
                String[] E = E(str, '|');
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                for (String str2 : E) {
                    String trim = str2.trim();
                    if (trim.endsWith(".ilua")) {
                        LinearLayout linearLayout = new LinearLayout(this.f1896c);
                        linearLayout.setLayoutParams(layoutParams);
                        linearLayout.setOrientation(1);
                        openRestoreinterface(trim, linearLayout, i(trim.substring(0, trim.length() - 5)), null);
                        arrayList.add(linearLayout);
                    }
                }
                new com.iapp.app.u(n2, arrayList);
                return arrayList;
            }
            if ((n2 instanceof DrawerLayout) || (n2 instanceof ViewGroup)) {
                for (String str3 : E(str, '|')) {
                    String trim2 = str3.trim();
                    if (trim2.endsWith(".ilua")) {
                        openRestoreinterface(str3.trim(), (ViewGroup) n2, i(trim2.substring(0, trim2.length() - 5)), null);
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Object> aslist(ArrayList<Object> arrayList, LuaObject luaObject) {
        Object[] objArr;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
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

    public ArrayList<Object> aslist(ArrayList<Object> arrayList, LuaObject luaObject, int i2) {
        Object[] objArr;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
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
        if (c.b.a.a.f.d(this.f1896c, mediaPlayer, str)) {
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
                            c.b.a.a.f.d(this.f1896c, mediaPlayer, str);
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
            c.b.a.a.i.h((Bitmap) obj, i2, w(str));
        }
    }

    public void bfs(Object obj, String str) {
        if (obj instanceof Bitmap) {
            c.b.a.a.i.h((Bitmap) obj, 100, w(str));
        }
    }

    public void bfv(String str) {
        Intent intent = new Intent(this.f1896c, (Class<?>) Videoview.class);
        Bundle bundle = new Bundle();
        bundle.putString("video", str);
        bundle.putBoolean("sfhp", false);
        intent.putExtras(bundle);
        this.f1896c.startActivity(intent);
    }

    public void bfv(String str, boolean z2) {
        Intent intent = new Intent(this.f1896c, (Class<?>) Videoview.class);
        Bundle bundle = new Bundle();
        bundle.putString("video", str);
        bundle.putBoolean("sfhp", z2);
        intent.putExtras(bundle);
        this.f1896c.startActivity(intent);
    }

    public void bfvs(Object obj, Object obj2) {
        Uri c2;
        if (!(obj instanceof VideoView)) {
            Object l2 = l(obj);
            obj = n(l2, String.valueOf(l2));
        }
        VideoView videoView = (VideoView) obj;
        if (obj2 instanceof Uri) {
            c2 = (Uri) obj2;
        } else {
            String valueOf = String.valueOf(obj2);
            if (!c.b.a.a.p.v(valueOf.toLowerCase())) {
                videoView.setVideoPath(w(valueOf));
                return;
            }
            c2 = c.b.a.a.k.c(this.f1896c, valueOf);
        }
        videoView.setVideoURI(c2);
    }

    public Object bfvss(Object obj, String str) {
        int currentPosition;
        if (!(obj instanceof VideoView)) {
            Object l2 = l(obj);
            obj = n(l2, String.valueOf(l2));
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
            MediaController mediaController = new MediaController(this.f1896c);
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
            Object l2 = l(obj);
            obj = n(l2, String.valueOf(l2));
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
            pVar.j(w(str), i2, i3, i4, i5);
            return;
        }
        com.iapp.app.p pVar2 = new com.iapp.app.p(this.b);
        com.iapp.app.p.n = pVar2;
        pVar2.n(w(str), i2, i3, i4, i5);
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
        String w2 = w(str);
        c.b.a.a.d.b(w2, false);
        mediaRecorder2.setOutputFile(w2);
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
            bArr = j(str2.trim(), ' ');
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
            bArr = j(str2.trim(), ' ');
        } catch (Exception unused) {
            bArr = null;
        }
        if (bArr == null) {
            return;
        }
        String w2 = w(str);
        File file = new File(w2);
        if (z2 || !file.exists()) {
            c.b.a.a.d.j(w2, bArr);
        }
    }

    public void btoo(byte[] bArr, String str, boolean z2) {
        if (bArr == null) {
            return;
        }
        String w2 = w(str);
        File file = new File(w2);
        if (z2 || !file.exists()) {
            c.b.a.a.d.j(w2, bArr);
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
                D(substring + "myu");
                obj = c.b.a.a.t.l.get(str2 + "0");
            }
            String obj2 = obj.toString();
            c.b.a.a.f.E(this.f1896c, this.b, str2, obj2.substring(obj2.indexOf(10)).trim());
        } else if (str.equals("mjava")) {
            com.iapp.app.j jVar = new com.iapp.app.j(this.f1896c);
            try {
                jVar.set("activity", this.f1896c);
                jVar.set("i", new Aid_javaCode(this.f1896c, this.b, jVar));
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
            com.iapp.app.d dVar = new com.iapp.app.d(this.f1896c);
            dVar.l("activity", this.f1896c);
            dVar.l("i", new Aid_luaCode(this.f1896c, this.b, dVar));
            dVar.k();
            try {
                dVar.h(str4);
                return c.b.a.a.t.j.get(str3);
            } catch (LuaException e3) {
                e3.printStackTrace();
                c.b.a.a.t.P2(this.f1896c, "LuaErr：\n" + e3.getMessage());
            }
        } else if (str.equals("mjs")) {
            int indexOf3 = str2.indexOf(46);
            StringBuilder sb = new StringBuilder();
            sb.append("<html><head><script type='text/javascript'>");
            sb.append(c.b.a.a.f.e(this.f1896c, "import.mjs"));
            sb.append("\n");
            sb.append(c.b.a.a.f.a(this.f1896c, str2.substring(0, indexOf3) + ".mjs"));
            sb.append("\n\nvar returns = ");
            sb.append(str2.substring(indexOf3 + 1));
            sb.append("();</script></head></html>");
            String sb2 = sb.toString();
            android.webkit.WebView webView = new android.webkit.WebView(this.f1896c);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setAppCachePath(this.f1896c.getApplicationContext().getDir("cache", 0).getPath());
            webView.getSettings().setAppCacheMaxSize(8388608L);
            webView.getSettings().setDatabaseEnabled(true);
            webView.getSettings().setDatabasePath(this.f1896c.getApplicationContext().getDir("database", 0).getPath());
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setGeolocationEnabled(true);
            webView.getSettings().setLightTouchEnabled(true);
            webView.getSettings().setCacheMode(-1);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.setWebChromeClient(new m());
            if (Build.VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
            }
            webView.addJavascriptInterface(new Aid_jsCode(this.f1896c, this.b, webView, 0), "I");
            com.iapp.app.c.d(webView, sb2);
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    public Object call(String str, String str2, LuaObject luaObject) {
        Object[] objArr;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
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
                D(substring + "myu");
                obj = c.b.a.a.t.l.get(str2 + length);
            }
            String obj2 = obj.toString();
            int indexOf = obj2.indexOf(10);
            String[] E = E(F(obj2.substring(0, indexOf)), ',');
            if (length != E.length) {
                return null;
            }
            c.b.a.a.f.F(this.f1896c, this.b, E, objArr, str2, obj2.substring(indexOf).trim());
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
                    com.iapp.app.d dVar = new com.iapp.app.d(this.f1896c);
                    dVar.l("activity", this.f1896c);
                    dVar.l("i", new Aid_luaCode(this.f1896c, this.b, dVar));
                    dVar.k();
                    try {
                        dVar.h(str8);
                        return c.b.a.a.t.j.get(str7);
                    } catch (LuaException e3) {
                        e3.printStackTrace();
                        c.b.a.a.t.P2(this.f1896c, "LuaErr：\n" + e3.getMessage());
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
                    sb.append(c.b.a.a.f.e(this.f1896c, "import.mjs"));
                    sb.append("\n");
                    sb.append(c.b.a.a.f.a(this.f1896c, str2.substring(0, indexOf3) + ".mjs"));
                    sb.append("\n");
                    sb.append(str10);
                    sb.append("\nvar returns = ");
                    sb.append(str2.substring(indexOf3 + 1));
                    sb.append("(");
                    sb.append(str9);
                    sb.append(");</script></head></html>");
                    String sb2 = sb.toString();
                    android.webkit.WebView webView = new android.webkit.WebView(this.f1896c);
                    webView.getSettings().setAllowFileAccess(true);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setAppCacheEnabled(true);
                    webView.getSettings().setAppCachePath(this.f1896c.getApplicationContext().getDir("cache", 0).getPath());
                    webView.getSettings().setAppCacheMaxSize(8388608L);
                    webView.getSettings().setDatabaseEnabled(true);
                    webView.getSettings().setDatabasePath(this.f1896c.getApplicationContext().getDir("database", 0).getPath());
                    webView.getSettings().setDomStorageEnabled(true);
                    webView.getSettings().setGeolocationEnabled(true);
                    webView.getSettings().setLightTouchEnabled(true);
                    webView.getSettings().setCacheMode(-1);
                    webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                    webView.setWebChromeClient(new l());
                    if (Build.VERSION.SDK_INT >= 11) {
                        webView.removeJavascriptInterface("searchBoxJavaBridge_");
                        webView.removeJavascriptInterface("accessibility");
                        webView.removeJavascriptInterface("accessibilityTraversal");
                    }
                    webView.addJavascriptInterface(new Aid_jsCode(this.f1896c, this.b, webView, 0), "I");
                    com.iapp.app.c.d(webView, sb2);
                }
                return null;
            }
            com.iapp.app.j jVar = new com.iapp.app.j(this.f1896c);
            try {
                jVar.set("activity", this.f1896c);
                jVar.set("i", new Aid_javaCode(this.f1896c, this.b, jVar));
            } catch (EvalError e4) {
                e4.printStackTrace();
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
        View n2;
        if (str.equals("duration")) {
            aVar.g(Long.parseLong(l(obj).toString()));
            return null;
        }
        if (str.equals("delay")) {
            aVar.h(Long.parseLong(l(obj).toString()));
            return null;
        }
        if (!str.equals("target")) {
            return null;
        }
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        aVar.i(n2);
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
        View n2;
        if (str.equals("duration")) {
            cVar.u(Long.parseLong(l(obj).toString()));
            return null;
        }
        if (str.equals("delay")) {
            cVar.h(Long.parseLong(l(obj).toString()));
            return null;
        }
        if (!str.equals("target")) {
            return null;
        }
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        cVar.i(n2);
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
            animation.setDuration(Long.parseLong(l(obj).toString()));
            return;
        }
        if (str.equals("delay")) {
            animation.setStartOffset(Long.parseLong(l(obj).toString()));
            return;
        }
        if (str.equals("enabled")) {
            animation.setFillEnabled(obj.equals(bool));
            return;
        }
        if (str.equals("after")) {
            animation.setFillAfter(obj.equals(bool));
        } else if (str.equals("before")) {
            animation.setFillBefore(obj.equals(bool));
        } else if (str.equals("repeat")) {
            animation.setRepeatCount(Integer.parseInt(l(obj).toString()));
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
            animationSet.setDuration(Long.parseLong(l(obj).toString()));
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
            animationSet.setStartOffset(Long.parseLong(l(obj).toString()));
        } else if (str.equals("repeat")) {
            animationSet.setRepeatCount(Integer.parseInt(l(obj).toString()));
        } else if (str.equals("add")) {
            animationSet.addAnimation((Animation) obj);
        }
    }

    public AlphaAnimation dha(boolean z2, boolean z3) {
        return new AlphaAnimation(z2 ? 1.0f : 0.0f, z3 ? 1.0f : 0.0f);
    }

    public c.d.a.i dhas(Object obj, String str, LuaObject luaObject) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        Object[] objArr = null;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        int length = objArr.length;
        float[] fArr = new float[length];
        for (int i2 = 0; i2 < length; i2++) {
            fArr[i2] = Float.parseFloat(objArr[i2].toString());
        }
        return c.d.a.i.K(n2, str, fArr);
    }

    public c.d.a.c dhast(String str, LuaObject luaObject) {
        Object[] objArr;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
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
        animationDrawable.addFrame(obj instanceof Drawable ? (Drawable) obj : obj instanceof Bitmap ? new BitmapDrawable((Bitmap) obj) : com.iapp.app.i.m(String.valueOf(l(obj)), this.f1896c), i2);
    }

    public void dhon(Animation animation, LuaObject luaObject, LuaObject luaObject2, LuaObject luaObject3) {
        animation.setAnimationListener(new d(this, luaObject, luaObject2, luaObject3));
    }

    public void dhon(c.d.a.a aVar, LuaObject luaObject, LuaObject luaObject2, LuaObject luaObject3, LuaObject luaObject4) {
        aVar.a(new e(this, luaObject, luaObject2, luaObject3, luaObject4));
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

    public AnimationSet dhset(AnimationSet animationSet, LuaObject luaObject) {
        Object[] objArr;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
        for (Object obj : objArr) {
            animationSet.addAnimation((Animation) obj);
        }
        return animationSet;
    }

    public AnimationSet dhset(boolean z2, LuaObject luaObject) {
        Object[] objArr;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
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
                k(str, obj, 1);
                return;
            } else if (substring.equals("sss")) {
                k(str, obj, 2);
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
        Object l2 = l(obj);
        if (!(l2 instanceof Integer) && !(l2 instanceof Double)) {
            arrayList.remove(l2);
            return;
        }
        int parseInt = Integer.parseInt(String.valueOf(l2));
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
            ((InputMethodManager) this.f1896c.getSystemService("input_method")).hideSoftInputFromWindow(peekDecorView.getWindowToken(), 0);
        }
    }

    public void ends() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addFlags(270532608);
        this.f1896c.startActivity(intent);
    }

    public void endutw() {
        AlertDialog alertDialog = alertdialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            alertdialog = null;
        }
    }

    public boolean fc(String str, String str2) {
        return c.b.a.a.f.f(this.f1896c, str, str2, true);
    }

    public boolean fc(String str, String str2, boolean z2) {
        return c.b.a.a.f.f(this.f1896c, str, str2, z2);
    }

    public boolean fd(String str) {
        File file = new File(w(str));
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public String fdir() {
        return c.b.a.a.d.m(this.f1896c);
    }

    public String fdir(String str) {
        return w(str);
    }

    public boolean fe(String str) {
        return c.b.a.a.f.g(this.f1896c, str);
    }

    public boolean fi(String str) {
        return new File(w(str)).isDirectory();
    }

    public boolean fj(String str, String str2) {
        String w2 = w(str2);
        c.b.a.a.d.b(w2, false);
        try {
            c.b.a.a.c.c(w(str), w2, true);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean fj(String str, String str2, boolean z2) {
        String w2 = w(str2);
        c.b.a.a.d.b(w2, false);
        try {
            c.b.a.a.c.c(w(str), w2, z2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public Object[] fl(String str) {
        return c.b.a.a.f.h(this.f1896c, str);
    }

    public Object[] fl(String str, boolean z2) {
        File[] listFiles;
        int i2;
        File file = new File(w(str));
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

    public void fo(String str) {
        String w2 = w(str);
        File file = new File(w2);
        if (file.exists()) {
            if (w2.toLowerCase().endsWith(".apk")) {
                c.b.a.a.d.d(this.f1896c, w2);
            } else {
                try {
                    A(file);
                } catch (Exception unused) {
                }
            }
        }
    }

    public String fr(String str) {
        return c.b.a.a.f.i(this.f1896c, str);
    }

    public String fr(String str, String str2) {
        return c.b.a.a.f.j(this.f1896c, str, str2);
    }

    public long fs(String str) {
        return c.b.a.a.f.k(this.f1896c, str);
    }

    public boolean ft(String str, String str2) {
        File file = new File(w(str));
        if (!file.exists()) {
            return false;
        }
        String w2 = w(str2);
        c.b.a.a.d.b(w2, false);
        return file.renameTo(new File(w2));
    }

    public void ftz(String str, String str2, String str3, Object obj, String str4) {
        c.b.a.a.r.b(this.f1896c, str, str2, str3, l(obj), new Intent().setClass(this.f1896c, logoActivity.class).putExtra("command2", str4));
    }

    public int fuz(String str, String str2, String str3) {
        String w2 = w(str3);
        c.b.a.a.d.b(w2, false);
        try {
            return c.b.a.a.f.l(this.f1896c, str, str2, w2, true);
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public int fuz(String str, String str2, String str3, boolean z2) {
        String w2 = w(str3);
        c.b.a.a.d.b(w2, false);
        try {
            return c.b.a.a.f.l(this.f1896c, str, str2, w2, z2);
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public boolean fuzs(String str, String str2) {
        String w2 = w(str2);
        c.b.a.a.d.b(w2, false);
        try {
            c.b.a.a.f.m(this.f1896c, str, w2, true);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean fuzs(String str, String str2, boolean z2) {
        String w2 = w(str2);
        c.b.a.a.d.b(w2, false);
        try {
            c.b.a.a.f.m(this.f1896c, str, w2, z2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public void fw(String str, String str2) {
        c.b.a.a.d.k(w(str), str2);
    }

    public void fw(String str, String str2, String str3) {
        c.b.a.a.d.l(w(str), str2, str3);
    }

    public Object[] getTableArray(LuaObject luaObject) {
        try {
            return luaObject.array();
        } catch (ArrayIndexOutOfBoundsException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return null;
        } catch (LuaException e4) {
            e4.printStackTrace();
            return null;
        }
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
        Object l2 = l(obj);
        return n(l2, String.valueOf(l2));
    }

    public View gvs(Object obj, Object obj2) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (obj2 instanceof View) {
            return (View) obj2;
        }
        Object l3 = l(obj2);
        return m(n2, l3, String.valueOf(l3));
    }

    public void has(Object obj, String str) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 instanceof WebView) {
            ((WebView) n2).loadUrl("javascript:{" + str + "};");
        }
    }

    public int hd(String str, String str2) {
        return c.b.a.a.g.a(str, w(str2), false);
    }

    public int hd(String str, String str2, boolean z2) {
        return c.b.a.a.g.a(str, w(str2), z2);
    }

    public int hd(String str, String str2, boolean z2, String str3, String str4, String str5, boolean z3, String str6) {
        return c.b.a.a.g.b(str, w(str2), z2, str3, str4, str5, z3, str6);
    }

    public void hdd(String str, String str2, int i2, int i3, int i4, int i5, boolean z2) {
        com.iapp.app.a.b.m(w(str), w(str2), i2, i3, i4, i5, z2);
    }

    public c.b.a.a.w.c hdda(String str, String str2, Object obj) {
        return com.iapp.app.a.b.d(str, str2, obj);
    }

    public c.b.a.a.w.c hdda(String str, String str2, String str3, Object obj) {
        return com.iapp.app.a.b.e(str, str2, str3, obj);
    }

    public c.b.a.a.w.c hdda(String str, String str2, String str3, Object obj, Object obj2) {
        return com.iapp.app.a.b.f(str, str2, str3, l(obj), obj2);
    }

    public c.b.a.a.w.c hdda(String str, String str2, String str3, String str4, Object obj, boolean z2, Object obj2) {
        return com.iapp.app.a.b.g(str, w(str2), str3, str4, l(obj), z2, obj2);
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
        Object l2 = l(obj);
        com.iapp.app.a.b.l(com.iapp.app.a.b.f1379c.get(i2), str, l2);
    }

    public void hdds(c.b.a.a.w.c cVar, String str, Object obj) {
        com.iapp.app.a.b.l(cVar, str, l(obj));
    }

    public void hdduigo() {
        this.f1896c.startActivity(new Intent().setClass(this.f1896c, DownList.class));
    }

    public void hdduigo(String str, String str2) {
        Intent intent = new Intent(this.f1896c, (Class<?>) DownList.class);
        Bundle bundle = new Bundle();
        bundle.putString("background", str);
        bundle.putString("backgroundShadow", str2);
        intent.putExtras(bundle);
        this.f1896c.startActivity(intent);
    }

    public c.b.a.a.e hdfl(String str, String str2, int i2, int i3, boolean z2, LuaObject luaObject, LuaObject luaObject2) {
        c.b.a.a.e eVar = new c.b.a.a.e(w(str), w(str2), i2, i3, z2);
        z(eVar, luaObject, luaObject2);
        return eVar;
    }

    public c.b.a.a.e hdfl(String str, String str2, LuaObject luaObject, LuaObject luaObject2) {
        c.b.a.a.e eVar = new c.b.a.a.e(w(str), w(str2));
        z(eVar, luaObject, luaObject2);
        return eVar;
    }

    public c.b.a.a.e hdfl(String str, LuaObject luaObject, LuaObject luaObject2) {
        c.b.a.a.e eVar = new c.b.a.a.e(w(str));
        z(eVar, luaObject, luaObject2);
        return eVar;
    }

    public void hdfla(c.b.a.a.e eVar, String str, int i2, Object obj) {
        eVar.n(str, i2, obj);
        eVar.t();
    }

    public void hdfla(c.b.a.a.e eVar, String str, int i2, Object obj, String str2) {
        eVar.o(str, i2, obj, w(str2));
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
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 instanceof WebView) {
            WebView webView = (WebView) n2;
            if (Build.VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface("iapp");
            }
            if (z2) {
                webView.addJavascriptInterface(new iapp(this.f1896c, this.b), "iapp");
            }
        }
    }

    public String huf(String str, String str2, String str3, String str4) {
        try {
            return c.b.a.a.g.m(this.f1896c, str, str2, str3, str4);
        } catch (Exception unused) {
            return null;
        }
    }

    public String huf(String str, String str2, String str3, String str4, String str5) {
        try {
            return c.b.a.a.g.n(this.f1896c, str, str2, str3, str4, str5);
        } catch (Exception unused) {
            return null;
        }
    }

    public void hw(String str) {
        Intent intent = new Intent(this.f1896c, (Class<?>) Webview.class);
        Bundle bundle = new Bundle();
        bundle.putString("WebURL", str);
        intent.putExtras(bundle);
        this.f1896c.startActivity(intent);
    }

    public void hw(String str, String str2, String str3) {
        Intent intent = new Intent(this.f1896c, (Class<?>) Webview.class);
        Bundle bundle = new Bundle();
        bundle.putString("WebURL", str);
        bundle.putString("background", str2);
        bundle.putString("backgroundShadow", str3);
        intent.putExtras(bundle);
        this.f1896c.startActivity(intent);
    }

    public void hws(String str) {
        Intent intent;
        try {
            intent = c.b.a.a.g.q(this.f1896c, str);
        } catch (Exception unused) {
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.VIEW");
            intent2.setData(c.b.a.a.k.c(this.f1896c, str));
            intent = intent2;
        }
        this.f1896c.startActivity(intent);
    }

    public Object java(Object obj, String str) {
        return c.b.a.a.b.g(this.f1896c, obj, str, null, null, null);
    }

    public Object java(Object obj, String str, LuaObject luaObject) {
        Object[] objArr;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
        return c.b.a.a.b.g(this.f1896c, obj, str, objArr, null, null);
    }

    public Object java(Object obj, String str, LuaObject luaObject, LuaObject luaObject2) {
        Object[] objArr;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
        return c.b.a.a.b.g(this.f1896c, obj, str, objArr, null, luaObject2);
    }

    public Object javacb(Class<?> cls, LuaObject luaObject) {
        return c.b.a.a.b.o(cls.getClassLoader(), cls, null, luaObject);
    }

    public Object javags(Object obj, Object obj2, String str) {
        return obj2 instanceof Class ? c.b.a.a.b.i(obj, (Class) obj2, str) : c.b.a.a.b.k(obj, obj2.toString(), str);
    }

    public Object javags(Object obj, String str) {
        return c.b.a.a.b.j(obj, str);
    }

    public Object javanew(Object obj) {
        return obj instanceof Class ? c.b.a.a.b.m(this.f1896c, (Class) obj, null, null, null) : c.b.a.a.b.n(this.f1896c, obj.toString(), null, null, null);
    }

    public Object javanew(Object obj, LuaObject luaObject) {
        Object[] objArr;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
        return obj instanceof Class ? c.b.a.a.b.m(this.f1896c, (Class) obj, objArr, null, null) : c.b.a.a.b.n(this.f1896c, obj.toString(), objArr, null, null);
    }

    public Object javanew(Object obj, LuaObject luaObject, LuaObject luaObject2) {
        Object[] objArr;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
        return obj instanceof Class ? c.b.a.a.b.m(this.f1896c, (Class) obj, objArr, null, luaObject2) : c.b.a.a.b.n(this.f1896c, obj.toString(), objArr, null, luaObject2);
    }

    public Object javass(Object obj, Object obj2, String str, Object obj3) {
        return Boolean.valueOf(obj2 instanceof Class ? c.b.a.a.b.q(obj, (Class) obj2, str, obj3) : c.b.a.a.b.r(obj, obj2.toString(), str, obj3));
    }

    public Object javass(Object obj, String str, Object obj2) {
        return c.b.a.a.b.p(obj, str, obj2);
    }

    public Object javax(Object obj, Object obj2, String str) {
        return obj2 instanceof Class ? c.b.a.a.b.e(this.f1896c, obj, (Class) obj2, str, null, null, null) : c.b.a.a.b.f(this.f1896c, obj, obj2.toString(), str, null, null, null);
    }

    public Object javax(Object obj, Object obj2, String str, LuaObject luaObject) {
        Object[] objArr;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
        Object[] objArr2 = objArr;
        boolean z2 = obj2 instanceof Class;
        Context context = this.f1896c;
        return z2 ? c.b.a.a.b.e(context, obj, (Class) obj2, str, objArr2, null, null) : c.b.a.a.b.f(context, obj, obj2.toString(), str, objArr2, null, null);
    }

    public Object javax(Object obj, Object obj2, String str, LuaObject luaObject, LuaObject luaObject2) {
        Object[] objArr;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
        Object[] objArr2 = objArr;
        boolean z2 = obj2 instanceof Class;
        Context context = this.f1896c;
        return z2 ? c.b.a.a.b.e(context, obj, (Class) obj2, str, objArr2, null, luaObject2) : c.b.a.a.b.f(context, obj, obj2.toString(), str, objArr2, null, luaObject2);
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
        Context context = this.f1896c;
        return c.b.a.a.f.r(context, str, context.getClassLoader());
    }

    public ClassLoader loadjar(String str, boolean z2) {
        Context context = this.f1896c;
        DexClassLoader r2 = c.b.a.a.f.r(context, str, context.getClassLoader());
        if (z2) {
            c.b.a.a.f.u(this.f1896c, r2);
        }
        return r2;
    }

    public ClassLoader loadjar(String str, boolean z2, ClassLoader classLoader) {
        DexClassLoader r2 = c.b.a.a.f.r(this.f1896c, str, classLoader);
        if (z2) {
            c.b.a.a.f.u(this.f1896c, r2);
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
        String[] E = E(str, '|');
        int[] iArr = new int[E.length];
        for (int i4 = 0; i4 < E.length; i4++) {
            iArr[i4] = c.b.a.a.p.o(E[i4]);
        }
        GradientDrawable gradientDrawable = new GradientDrawable(x(str3), iArr);
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

    public Object nsz(int i2, Object obj) {
        Class<?> cls;
        if (obj instanceof String) {
            cls = c.b.a.a.b.b(obj.toString());
        } else {
            if (!(obj instanceof Class)) {
                return null;
            }
            cls = (Class) obj;
        }
        return Array.newInstance(cls, i2);
    }

    public Object[] nsz(int i2) {
        return new Object[i2];
    }

    public Object[] nsz(LuaObject luaObject) {
        try {
            return luaObject.array();
        } catch (ArrayIndexOutOfBoundsException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return null;
        } catch (LuaException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public Object nuibs(Object obj, Object obj2, Object obj3) {
        return new c.c.a.c(this.f1896c).d(l(obj), l(obj2), l(obj3));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0061 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0062  */
    /* JADX WARN: Type inference failed for: r1v0, types: [c.c.a.b] */
    /* JADX WARN: Type inference failed for: r7v3, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v2, types: [android.view.View, android.view.ViewGroup] */
    @android.annotation.TargetApi(16)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View nvw(int r7, java.lang.Object r8, java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_luaCode.nvw(int, java.lang.Object, java.lang.String, java.lang.String):android.view.View");
    }

    public void nvw(Object obj, Object obj2) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 == null) {
            return;
        }
        if (!(obj2 instanceof ViewGroup)) {
            Object l3 = l(obj2);
            obj2 = n(l3, String.valueOf(l3));
        }
        ViewGroup viewGroup = (ViewGroup) obj2;
        if (viewGroup == null) {
            return;
        }
        viewGroup.addView(n2);
    }

    public void nvw(Object obj, Object obj2, int i2) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 == null) {
            return;
        }
        if (!(obj2 instanceof ViewGroup)) {
            Object l3 = l(obj2);
            obj2 = n(l3, String.valueOf(l3));
        }
        ViewGroup viewGroup = (ViewGroup) obj2;
        if (viewGroup == null) {
            return;
        }
        viewGroup.addView(n2, i2);
    }

    public LuaObject openRestoreinterface(String str, ViewGroup viewGroup, int i2, Object obj) {
        com.iapp.app.d dVar = new com.iapp.app.d(this.f1896c);
        dVar.l("activity", this.f1896c);
        dVar.l("i", this);
        dVar.k();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("require 'import'\n");
        Context context = this.f1896c;
        c.c.a.b bVar = new c.c.a.b(context);
        bVar.a = 0;
        c.b.a.a.f.q(this, context, str, viewGroup, i2, obj, bVar, stringBuffer, dVar);
        String C = c.b.a.a.f.C(this.f1896c, str);
        if (C != null) {
            stringBuffer.append("function loading()\n");
            stringBuffer.append(C);
            stringBuffer.append("\nend\n");
        }
        try {
            dVar.g(stringBuffer.toString());
        } catch (LuaException e2) {
            e2.printStackTrace();
            c.b.a.a.t.P2(this.f1896c, "LuaErr：\n" + e2.getMessage());
        }
        if (C == null) {
            return null;
        }
        return dVar.j().getLuaObject("loading");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0067 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void openRestoreinterfaceX(java.lang.String r16, android.view.ViewGroup r17, int r18, java.lang.Object r19, c.c.a.b r20, java.lang.StringBuffer r21, com.iapp.app.d r22) {
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
            android.content.Context r8 = r6.f1896c
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
            r9.f(r10, r11, r12, r13, r14)
            r3.addView(r0)
            return
        La8:
            r6 = r15
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_luaCode.openRestoreinterfaceX(java.lang.String, android.view.ViewGroup, int, java.lang.Object, c.c.a.b, java.lang.StringBuffer, com.iapp.app.d):void");
    }

    public String otob(String str) {
        byte[] t2 = c.b.a.a.f.t(this.f1896c, str);
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
            return c.b.a.a.f.t(this.f1896c, str3);
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
        return new com.iapp.app.k(this.f1896c);
    }

    public com.iapp.app.k res(String str) {
        return new com.iapp.app.k(this.f1896c, w(str));
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
        Bitmap v2 = obj instanceof Bitmap ? (Bitmap) obj : c.b.a.a.f.v(this.f1896c, String.valueOf(obj));
        if (v2 == null) {
            return null;
        }
        return v2;
    }

    public Bitmap sbp(Object obj, int i2, int i3, int i4, int i5) {
        Bitmap c2 = obj instanceof Bitmap ? (Bitmap) obj : c.b.a.a.i.c(w(String.valueOf(obj)));
        if (c2 == null) {
            return null;
        }
        return Bitmap.createBitmap(c2, i2, i3, i4, i5);
    }

    public Bitmap sbp(Object obj, int i2, int i3, int i4, int i5, float f2) {
        Bitmap c2 = obj instanceof Bitmap ? (Bitmap) obj : c.b.a.a.i.c(w(String.valueOf(obj)));
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

    public Object sgsz(Object obj, int i2) {
        try {
            return Array.get(obj, i2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public int sgszl(Object obj) {
        try {
            return Array.getLength(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public String shb() {
        CharSequence text = ((ClipboardManager) this.f1896c.getSystemService("clipboard")).getText();
        if (text == null) {
            return null;
        }
        return text.toString();
    }

    public String simei() {
        return c.b.a.a.p.q(this.f1896c);
    }

    public String simsi() {
        try {
            return ((TelephonyManager) this.f1896c.getSystemService("phone")).getSubscriberId();
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
            intent.setData(c.b.a.a.k.c(this.f1896c, str2));
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
                intent.putExtra(str2, Integer.parseInt(String.valueOf(l(obj))));
            } else if (obj instanceof Long) {
                intent.putExtra(str2, Long.parseLong(String.valueOf(l(obj))));
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
        String[] p2 = p();
        int[] q2 = q();
        int[] r2 = r();
        String[] s2 = s();
        return new String[]{p2[0] + "\n" + p2[1], q2[0] + "\n" + q2[1] + "\n" + r2[0] + "\n" + r2[1], s2[0] + "\n" + s2[1] + "\n" + s2[2]};
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

    public c.b.a.a.o sot(int i2, String str, int i3, int i4, boolean z2, LuaObject luaObject) {
        c.b.a.a.o oVar = new c.b.a.a.o(i2, str, i3, i4, z2);
        oVar.F(new f(luaObject));
        return oVar;
    }

    public c.b.a.a.o sot(String str, int i2, int i3, boolean z2, LuaObject luaObject) {
        c.b.a.a.o oVar = new c.b.a.a.o(str, i2, i3, z2);
        oVar.F(new g(luaObject));
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
            oVar.a(new File(w(str2)));
        } else if (str.equals("bt")) {
            oVar.c(j(str2, ' '));
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
            cVar.a(new File(w(str2)));
        } else if (str.equals("bt")) {
            cVar.c(j(str2, ' '));
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
            context = this.f1896c;
            str = w(str);
            z2 = false;
        } else {
            context = this.f1896c;
            z2 = true;
        }
        return c.b.a.a.f.z(context, str, z2);
    }

    public boolean sqlite(String str, String str2) {
        if (str2.equals("ip")) {
            return (str.contains("/") || str.contains("\\") || str.startsWith("@") || str.startsWith("$") || str.startsWith("%")) ? c.b.a.a.f.y(this.f1896c, w(str), false) : c.b.a.a.f.y(this.f1896c, str, true);
        }
        if (str2.equals("del")) {
            return (str.contains("/") || str.contains("\\") || str.startsWith("@") || str.startsWith("$") || str.startsWith("%")) ? c.b.a.a.f.x(this.f1896c, w(str), false) : c.b.a.a.f.x(this.f1896c, str, true);
        }
        return false;
    }

    public Object sqlsele(Cursor cursor, Object obj) {
        Object l2 = l(obj);
        if (l2 instanceof Integer) {
            return cursor.getString(Integer.parseInt(l2.toString()));
        }
        if (!l2.equals("re")) {
            return l2.equals("columncount") ? Integer.valueOf(cursor.getColumnCount()) : l2.equals("count") ? Integer.valueOf(cursor.getCount()) : l2.equals("next") ? Boolean.valueOf(cursor.moveToNext()) : l2.equals("previous") ? Boolean.valueOf(cursor.moveToPrevious()) : l2.equals("first") ? Boolean.valueOf(cursor.moveToFirst()) : l2.equals("last") ? Boolean.valueOf(cursor.moveToLast()) : l2.equals("getposition") ? Integer.valueOf(cursor.getPosition()) : cursor.getString(a(l2.toString()));
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
        this.f1897d.l(str, obj);
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

    public void ssj(Object obj, String str, LuaObject luaObject) {
        Object[] objArr;
        View n2;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
        int length = objArr.length;
        LuaObject[] luaObjectArr = new LuaObject[length];
        for (int i2 = 0; i2 < length; i2++) {
            luaObjectArr[i2] = (LuaObject) objArr[i2];
        }
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 != null) {
            g(n2, str, luaObjectArr);
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

    public void sssz(Object obj, int i2, Object obj2) {
        try {
            Array.set(obj, i2, obj2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
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
            return c.b.a.a.p.w(this.f1896c, r4.widthPixels);
        }
        if (str.equals("h")) {
            WindowManager.LayoutParams attributes = this.b.getWindow().getAttributes();
            this.b.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            return (attributes.flags & 1024) == 1024 ? c.b.a.a.p.w(this.f1896c, r0.heightPixels) : c.b.a.a.p.w(this.f1896c, r0.heightPixels - u());
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
            return (attributes2.flags & 1024) == 1024 ? displayMetrics2.heightPixels : displayMetrics2.heightPixels - u();
        }
        if (str.equals("pxhh")) {
            DisplayMetrics displayMetrics3 = new DisplayMetrics();
            this.b.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics3);
            return displayMetrics3.heightPixels;
        }
        if (str.equals("hh")) {
            this.b.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
            return c.b.a.a.p.w(this.f1896c, r4.heightPixels);
        }
        if (str.equals("pxztl")) {
            return v();
        }
        if (str.equals("pxbvk")) {
            return o();
        }
        if (str.equals("ztl")) {
            return c.b.a.a.p.w(this.f1896c, v());
        }
        if (str.equals("bvk")) {
            return c.b.a.a.p.w(this.f1896c, o());
        }
        return 0;
    }

    public void sxb(String str) {
        ((ClipboardManager) this.f1896c.getSystemService("clipboard")).setText(str);
    }

    public void syso(Object obj) {
        c.b.a.a.t.P2(this.f1896c, obj);
    }

    public void sysoDouble(double d2) {
        c.b.a.a.t.P2(this.f1896c, Double.valueOf(d2));
    }

    public void sysoLong(long j2) {
        c.b.a.a.t.P2(this.f1896c, Long.valueOf(j2));
    }

    public void t(LuaObject luaObject) {
        c cVar = new c(this, luaObject);
        cVar.setName("CeShi_" + cVar.getId());
        cVar.start();
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

    public double toi(double d2) {
        double longValue = Double.valueOf(d2).longValue();
        Double.isNaN(longValue);
        return longValue >= 0.0d ? longValue + 0.09393d : longValue - 0.09393d;
    }

    public Bitmap tot(Object obj) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 != null && (n2 instanceof ImageView)) {
            return c.b.a.a.i.a((ImageView) n2);
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
        return new com.iapp.app.r(this.f1896c);
    }

    public com.iapp.app.r tts(String str, String str2, float f2, float f3) {
        return new com.iapp.app.r(this.f1896c, str, str2, f2, f3);
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
        } else if (str.equals("st")) {
            rVar.i(str, Integer.parseInt(l(obj).toString()));
        } else if (str.equals("ft")) {
            rVar.k(str, w(obj.toString()));
        }
    }

    public void tw(Object obj) {
        Toast.makeText(this.f1896c, String.valueOf(obj), 1).show();
    }

    public void tw(Object obj, int i2) {
        Toast.makeText(this.f1896c, String.valueOf(obj), i2).show();
    }

    public void twDouble(double d2) {
        Toast.makeText(this.f1896c, String.valueOf(d2), 1).show();
    }

    public void twDouble(double d2, int i2) {
        Toast.makeText(this.f1896c, String.valueOf(d2), i2).show();
    }

    public void twLong(long j2) {
        Toast.makeText(this.f1896c, String.valueOf(j2), 1).show();
    }

    public void twLong(long j2, int i2) {
        Toast.makeText(this.f1896c, String.valueOf(j2), i2).show();
    }

    public void tws(Object obj, String str, int i2) {
        View n2;
        if (obj == null) {
            n2 = this.b.findViewById(c.b.a.a.f.a);
        } else if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 != null) {
            Snackbar.make(n2, str, i2).show();
        }
    }

    public void tws(Object obj, String str, int i2, String str2, LuaObject luaObject) {
        View n2;
        if (obj == null) {
            n2 = this.b.findViewById(c.b.a.a.f.a);
        } else if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 != null) {
            Snackbar.make(n2, str, i2).setAction(str2, new h(this, luaObject)).show();
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
            Object l2 = l(obj);
            obj = n(l2, String.valueOf(l2));
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
        return c.b.a.a.d.s(this.f1896c, str);
    }

    public boolean uapp(String str, String str2) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(str, str2));
            this.f1896c.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public Object[] uapplist(boolean z2) {
        ArrayList arrayList = new ArrayList();
        for (PackageInfo packageInfo : this.f1896c.getPackageManager().getInstalledPackages(0)) {
            String str = packageInfo.packageName;
            if (z2) {
                arrayList.add(new String[]{str, y(str), packageInfo.applicationInfo.loadLabel(this.f1896c.getPackageManager()).toString(), packageInfo.versionName});
            } else if ((packageInfo.applicationInfo.flags & 1) == 0) {
                arrayList.add(new String[]{str, y(str), packageInfo.applicationInfo.loadLabel(this.f1896c.getPackageManager()).toString(), packageInfo.versionName});
            }
        }
        return arrayList.toArray();
    }

    public void ucall(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.DIAL");
        intent.setData(c.b.a.a.k.c(this.f1896c, "tel:" + str));
        this.f1896c.startActivity(intent);
    }

    public void ufnsui(LuaObject luaObject) {
        Message message = new Message();
        message.what = 2;
        message.obj = luaObject;
        this.e.sendMessage(message);
    }

    public Object ug(Object obj, String str) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 == null) {
            return null;
        }
        return new com.iapp.app.i(n2, this.f1896c).e(str);
    }

    public Object ug(Object obj, String str, Object obj2) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 == null) {
            return null;
        }
        return new com.iapp.app.i(n2, this.f1896c).f(str, obj2);
    }

    public Object uht(Object obj, String str) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        boolean z2 = n2 instanceof ViewPager;
        if (z2 || (n2 instanceof VerticalViewPager)) {
            PagerAdapter adapter = z2 ? ((ViewPager) n2).getAdapter() : n2 instanceof VerticalViewPager ? ((VerticalViewPager) n2).getAdapter() : null;
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
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        boolean z2 = n2 instanceof ViewPager;
        if (z2 || (n2 instanceof VerticalViewPager)) {
            PagerAdapter adapter = z2 ? ((ViewPager) n2).getAdapter() : n2 instanceof VerticalViewPager ? ((VerticalViewPager) n2).getAdapter() : null;
            if (str.equals("del") && (adapter instanceof FragmentStatePagerAdapter)) {
                ((com.iapp.app.h) adapter).d(i2);
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0043 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object uht(java.lang.Object r17, java.lang.String r18, int r19, java.lang.String r20, java.lang.String r21, org.keplerproject.luajava.LuaObject r22, org.keplerproject.luajava.LuaObject r23) {
        /*
            Method dump skipped, instructions count: 232
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_luaCode.uht(java.lang.Object, java.lang.String, int, java.lang.String, java.lang.String, org.keplerproject.luajava.LuaObject, org.keplerproject.luajava.LuaObject):java.lang.Object");
    }

    public Object uht(Object obj, String str, Object obj2, Object obj3) {
        View n2;
        View n3;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        boolean z2 = n2 instanceof ViewPager;
        if (z2 || (n2 instanceof VerticalViewPager)) {
            if (str.equals("bd")) {
                if (z2) {
                    if (obj2 instanceof View) {
                        n3 = (View) obj2;
                    } else {
                        Object l3 = l(obj2);
                        n3 = n(l3, String.valueOf(l3));
                    }
                    if (n3 instanceof TabLayout) {
                        ((TabLayout) n3).setupWithViewPager((ViewPager) n2, obj3.equals(Boolean.TRUE));
                    }
                }
                return null;
            }
            PagerAdapter adapter = z2 ? ((ViewPager) n2).getAdapter() : n2 instanceof VerticalViewPager ? ((VerticalViewPager) n2).getAdapter() : null;
            if (str.equals("title") && (adapter instanceof FragmentStatePagerAdapter)) {
                ((com.iapp.app.h) adapter).f(a(String.valueOf(obj2)), String.valueOf(obj3));
            }
        }
        return null;
    }

    public void uigo(String str) {
        Intent intent;
        if (str.endsWith(".iyu")) {
            intent = new Intent(this.f1896c, (Class<?>) mian.class);
        } else if (str.endsWith(".ijava")) {
            intent = new Intent(this.f1896c, (Class<?>) main3.class);
        } else if (str.endsWith(".ilua")) {
            intent = new Intent(this.f1896c, (Class<?>) main.class);
        } else if (!str.endsWith(".ijs")) {
            return;
        } else {
            intent = new Intent(this.f1896c, (Class<?>) main2.class);
        }
        intent.putExtras(c.b.a.a.f.B(str));
        this.f1896c.startActivity(intent);
    }

    public void uigo(String str, int i2) {
        Intent intent;
        if (str.endsWith(".iyu")) {
            intent = new Intent(this.f1896c, (Class<?>) mian.class);
        } else if (str.endsWith(".ijava")) {
            intent = new Intent(this.f1896c, (Class<?>) main3.class);
        } else if (str.endsWith(".ilua")) {
            intent = new Intent(this.f1896c, (Class<?>) main.class);
        } else if (!str.endsWith(".ijs")) {
            return;
        } else {
            intent = new Intent(this.f1896c, (Class<?>) main2.class);
        }
        intent.putExtras(c.b.a.a.f.B(str));
        intent.setFlags(i2);
        this.f1896c.startActivity(intent);
    }

    public void uit(Intent intent) {
        this.f1896c.startActivity(intent);
    }

    public void uit(Intent intent, String str, String str2) {
        Context context;
        if (str.equals("chooser")) {
            context = this.f1896c;
            intent = Intent.createChooser(intent, str2);
        } else {
            if (str.equals("result")) {
                this.b.startActivityForResult(intent, Integer.parseInt(str2));
                return;
            }
            context = this.f1896c;
        }
        context.startActivity(intent);
    }

    public void ujp(String str, int i2) {
        c.b.a.a.r.a(this.b, w(str), i2);
    }

    public Object ula(Object obj, Object obj2) {
        Object obj3;
        ArrayList<HashMap<Integer, Object>> a2;
        Object obj4;
        k kVar = null;
        if (obj2 == null || obj2.equals("clear")) {
            if ((obj instanceof d1) && (obj3 = ((d1) obj).b) != null) {
                if (obj3 instanceof e1) {
                    a2 = ((e1) obj3).a();
                } else if (obj3 instanceof f1) {
                    a2 = ((f1) obj3).a();
                }
                a2.clear();
            }
            return null;
        }
        if (obj2.equals("list")) {
            return ((obj == null || !(obj instanceof d1)) ? new d1(this, kVar) : (d1) obj).a;
        }
        if ((obj instanceof d1) && (obj2 instanceof Number) && (obj4 = ((d1) obj).b) != null && (obj4 instanceof f1)) {
            ((f1) obj4).notifyItemChanged(((Number) obj2).intValue());
        }
        return null;
    }

    public Object ula(Object obj, LuaObject luaObject, LuaObject luaObject2) {
        Object[] objArr;
        Object[] objArr2;
        k kVar = null;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
        try {
            objArr2 = luaObject2.array();
        } catch (Exception e3) {
            e3.printStackTrace();
            objArr2 = null;
        }
        int length = objArr.length;
        d1 d1Var = (obj == null || !(obj instanceof d1)) ? new d1(this, kVar) : (d1) obj;
        HashMap<Integer, Object> hashMap = new HashMap<>();
        for (int i2 = 0; i2 < length; i2++) {
            hashMap.put(Integer.valueOf((int) Double.parseDouble(objArr[i2].toString())), objArr2[i2]);
        }
        d1Var.a.add(hashMap);
        return d1Var;
    }

    public void ula(Object obj) {
        Object obj2;
        if (!(obj instanceof d1) || (obj2 = ((d1) obj).b) == null) {
            return;
        }
        if (obj2 instanceof e1) {
            ((e1) obj2).notifyDataSetChanged();
        } else if (obj2 instanceof ArrayAdapter) {
            ((ArrayAdapter) obj2).notifyDataSetChanged();
        } else if (obj2 instanceof f1) {
            ((f1) obj2).notifyDataSetChanged();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00b6, code lost:
    
        if ((r5 instanceof com.iapp.app.Aid_luaCode.d1) != false) goto L39;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object ulag(java.lang.Object r5, int r6, java.lang.Object r7) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof c.b.a.a.e
            if (r0 == 0) goto L19
            java.lang.Object r7 = r4.l(r7)
            c.b.a.a.e r5 = (c.b.a.a.e) r5
            java.util.ArrayList r5 = r5.q()
            java.lang.Object r5 = r5.get(r6)
            java.util.HashMap r5 = (java.util.HashMap) r5
            java.lang.Object r5 = r5.get(r7)
            return r5
        L19:
            boolean r0 = r5 instanceof java.util.ArrayList
            if (r0 == 0) goto L3a
            java.lang.Object r7 = r4.l(r7)
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            java.lang.Object r5 = r5.get(r6)
            java.util.HashMap r5 = (java.util.HashMap) r5
            java.lang.String r6 = java.lang.String.valueOf(r7)
            int r6 = java.lang.Integer.parseInt(r6)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r5 = r5.get(r6)
            return r5
        L3a:
            boolean r0 = r5 instanceof android.view.View
            r1 = 0
            if (r0 == 0) goto L89
            r0 = r5
            android.view.View r0 = (android.view.View) r0
            boolean r2 = r0 instanceof androidx.viewpager.widget.ViewPager
            if (r2 == 0) goto L58
            r2 = r0
            androidx.viewpager.widget.ViewPager r2 = (androidx.viewpager.widget.ViewPager) r2
            androidx.viewpager.widget.PagerAdapter r3 = r2.getAdapter()
            boolean r3 = r3 instanceof com.iapp.app.h
            if (r3 == 0) goto L58
            androidx.viewpager.widget.PagerAdapter r0 = r2.getAdapter()
        L55:
            com.iapp.app.h r0 = (com.iapp.app.h) r0
            goto L6c
        L58:
            boolean r2 = r0 instanceof fr.castorflex.android.verticalviewpager.VerticalViewPager
            if (r2 == 0) goto L6b
            fr.castorflex.android.verticalviewpager.VerticalViewPager r0 = (fr.castorflex.android.verticalviewpager.VerticalViewPager) r0
            androidx.viewpager.widget.PagerAdapter r2 = r0.getAdapter()
            boolean r2 = r2 instanceof com.iapp.app.h
            if (r2 == 0) goto L6b
            androidx.viewpager.widget.PagerAdapter r0 = r0.getAdapter()
            goto L55
        L6b:
            r0 = r1
        L6c:
            if (r0 == 0) goto L89
            java.util.ArrayList r5 = r0.e()
            java.lang.Object r5 = r5.get(r6)
            java.util.HashMap r5 = (java.util.HashMap) r5
            java.lang.String r6 = java.lang.String.valueOf(r7)
            int r6 = r4.a(r6)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r5 = r5.get(r6)
            return r5
        L89:
            boolean r0 = r5 instanceof android.widget.AbsListView
            r2 = 3
            if (r0 == 0) goto L9f
            android.widget.AbsListView r5 = (android.widget.AbsListView) r5
            java.lang.Object r5 = r5.getTag()
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            r0 = r5[r2]
            boolean r0 = r0 instanceof com.iapp.app.Aid_luaCode.d1
            if (r0 == 0) goto Lbb
            r5 = r5[r2]
            goto Lb8
        L9f:
            boolean r0 = r5 instanceof androidx.recyclerview.widget.RecyclerView
            if (r0 == 0) goto Lb4
            androidx.recyclerview.widget.RecyclerView r5 = (androidx.recyclerview.widget.RecyclerView) r5
            java.lang.Object r5 = r5.getTag()
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            r0 = r5[r2]
            boolean r0 = r0 instanceof com.iapp.app.Aid_luaCode.d1
            if (r0 == 0) goto Lbb
            r5 = r5[r2]
            goto Lb8
        Lb4:
            boolean r0 = r5 instanceof com.iapp.app.Aid_luaCode.d1
            if (r0 == 0) goto Lbb
        Lb8:
            com.iapp.app.Aid_luaCode$d1 r5 = (com.iapp.app.Aid_luaCode.d1) r5
            goto Lbc
        Lbb:
            r5 = r1
        Lbc:
            if (r5 != 0) goto Lbf
            return r1
        Lbf:
            java.lang.Object r7 = r4.l(r7)
            java.util.ArrayList<java.util.HashMap<java.lang.Integer, java.lang.Object>> r5 = r5.a
            java.lang.Object r5 = r5.get(r6)
            java.util.HashMap r5 = (java.util.HashMap) r5
            java.lang.String r6 = java.lang.String.valueOf(r7)
            int r6 = java.lang.Integer.parseInt(r6)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r5 = r5.get(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_luaCode.ulag(java.lang.Object, int, java.lang.Object):java.lang.Object");
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
            if (r0 == 0) goto L6b
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
            if (r2 == 0) goto L91
            java.lang.Object r5 = r3.l(r5)
            r4 = r4[r0]
            java.util.HashMap r4 = (java.util.HashMap) r4
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r5 = java.lang.Integer.parseInt(r5)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r4 = r4.get(r5)
            return r4
        L6b:
            boolean r0 = r4 instanceof java.util.HashMap
            if (r0 == 0) goto L7a
            java.lang.Object r5 = r3.l(r5)
            java.util.HashMap r4 = (java.util.HashMap) r4
            java.lang.Object r4 = r4.get(r5)
            return r4
        L7a:
            boolean r0 = r4 instanceof java.util.ArrayList
            if (r0 == 0) goto L91
            java.lang.Object r5 = r3.l(r5)
            java.util.ArrayList r4 = (java.util.ArrayList) r4
            java.lang.String r5 = java.lang.String.valueOf(r5)
            int r5 = java.lang.Integer.parseInt(r5)
            java.lang.Object r4 = r4.get(r5)
            return r4
        L91:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_luaCode.ulag(java.lang.Object, java.lang.Object):java.lang.Object");
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
            boolean r2 = r2 instanceof com.iapp.app.Aid_luaCode.d1
            if (r2 == 0) goto L66
            r5 = r5[r0]
            com.iapp.app.Aid_luaCode$d1 r5 = (com.iapp.app.Aid_luaCode.d1) r5
            r1 = r5
            goto L66
        L5f:
            boolean r0 = r5 instanceof com.iapp.app.Aid_luaCode.d1
            if (r0 == 0) goto L66
            r1 = r5
            com.iapp.app.Aid_luaCode$d1 r1 = (com.iapp.app.Aid_luaCode.d1) r1
        L66:
            if (r1 != 0) goto L69
            return
        L69:
            java.util.ArrayList<java.util.HashMap<java.lang.Integer, java.lang.Object>> r5 = r1.a
            goto L38
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_luaCode.ulas(java.lang.Object, int, int, java.lang.Object):void");
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
        View n2;
        ArrayAdapter arrayAdapter;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 != null && (n2 instanceof Spinner)) {
            Spinner spinner = (Spinner) n2;
            if (obj2 instanceof ArrayList) {
                arrayAdapter = new ArrayAdapter(this.f1896c, R.layout.simple_spinner_item, (ArrayList) obj2);
            } else if (obj2 instanceof String[]) {
                arrayAdapter = new ArrayAdapter(this.f1896c, R.layout.simple_spinner_item, (String[]) obj2);
            } else if (!(obj2 instanceof Object[])) {
                return;
            } else {
                arrayAdapter = new ArrayAdapter(this.f1896c, R.layout.simple_spinner_item, (Object[]) obj2);
            }
            arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        }
    }

    public void uls(Object obj, Object obj2, String str) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 == null) {
            return;
        }
        if (n2 instanceof ListView) {
            ListView listView = (ListView) n2;
            if (obj2 instanceof d1) {
                d1 d1Var = (d1) obj2;
                if (d1Var.b == null) {
                    d1Var.b = new e1(this.b);
                }
                e1 e1Var = (e1) d1Var.b;
                e1Var.b(str, d1Var);
                listView.setAdapter((ListAdapter) e1Var);
                Object[] objArr = (Object[]) listView.getTag();
                objArr[3] = d1Var;
                listView.setTag(objArr);
                return;
            }
            return;
        }
        if (n2 instanceof GridView) {
            GridView gridView = (GridView) n2;
            if (obj2 instanceof d1) {
                d1 d1Var2 = (d1) obj2;
                if (d1Var2.b == null) {
                    d1Var2.b = new e1(this.b);
                }
                e1 e1Var2 = (e1) d1Var2.b;
                e1Var2.b(str, d1Var2);
                gridView.setAdapter((ListAdapter) e1Var2);
                Object[] objArr2 = (Object[]) gridView.getTag();
                objArr2[3] = d1Var2;
                gridView.setTag(objArr2);
                return;
            }
            return;
        }
        if (n2 instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) n2;
            if (obj2 instanceof d1) {
                d1 d1Var3 = (d1) obj2;
                if (d1Var3.b == null) {
                    d1Var3.b = new f1(this.b);
                }
                f1 f1Var = (f1) d1Var3.b;
                f1Var.d(str, d1Var3);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.b));
                recyclerView.setAdapter(f1Var);
                Object[] objArr3 = (Object[]) recyclerView.getTag();
                objArr3[3] = d1Var3;
                recyclerView.setTag(objArr3);
                return;
            }
            return;
        }
        if (n2 instanceof TabLayout) {
            TabLayout tabLayout = (TabLayout) n2;
            if (obj2 instanceof d1) {
                c.b.a.a.h hVar = new c.b.a.a.h(this.b, 2);
                int i2 = i(str.substring(0, str.length() - 4));
                Iterator<HashMap<Integer, Object>> it = ((d1) obj2).a.iterator();
                while (it.hasNext()) {
                    HashMap<Integer, Object> next = it.next();
                    LinearLayout linearLayout = new LinearLayout(this.b);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                    linearLayout.setOrientation(1);
                    openRestoreinterface(str, linearLayout, i2, next);
                    tabLayout.addTab(tabLayout.newTab().setCustomView(linearLayout));
                    Iterator<Integer> it2 = next.keySet().iterator();
                    while (it2.hasNext()) {
                        int parseInt = Integer.parseInt(String.valueOf(it2.next()));
                        if (parseInt > 0) {
                            c.b.a.a.f.w(linearLayout.findViewById(parseInt + i2), next.get(Integer.valueOf(parseInt)), next, hVar);
                        }
                    }
                }
            }
        }
    }

    public void uls(Object obj, Object obj2, String str, int i2, int i3) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 == null) {
            return;
        }
        if (n2 instanceof ListView) {
            ListView listView = (ListView) n2;
            if (obj2 instanceof d1) {
                d1 d1Var = (d1) obj2;
                if (d1Var.b == null) {
                    d1Var.b = new e1(this.b);
                }
                e1 e1Var = (e1) d1Var.b;
                e1Var.b(str, d1Var);
                e1Var.c(i2, i3);
                listView.setAdapter((ListAdapter) e1Var);
                Object[] objArr = (Object[]) listView.getTag();
                objArr[3] = d1Var;
                listView.setTag(objArr);
                return;
            }
            return;
        }
        if (n2 instanceof GridView) {
            GridView gridView = (GridView) n2;
            if (obj2 instanceof d1) {
                d1 d1Var2 = (d1) obj2;
                if (d1Var2.b == null) {
                    d1Var2.b = new e1(this.b);
                }
                e1 e1Var2 = (e1) d1Var2.b;
                e1Var2.b(str, d1Var2);
                e1Var2.c(i2, i3);
                gridView.setAdapter((ListAdapter) e1Var2);
                Object[] objArr2 = (Object[]) gridView.getTag();
                objArr2[3] = d1Var2;
                gridView.setTag(objArr2);
                return;
            }
            return;
        }
        if (n2 instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) n2;
            if (obj2 instanceof d1) {
                d1 d1Var3 = (d1) obj2;
                if (d1Var3.b == null) {
                    d1Var3.b = new f1(this.b);
                }
                f1 f1Var = (f1) d1Var3.b;
                f1Var.d(str, d1Var3);
                f1Var.e(i2, i3);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.b));
                recyclerView.setAdapter(f1Var);
                Object[] objArr3 = (Object[]) recyclerView.getTag();
                objArr3[3] = d1Var3;
                recyclerView.setTag(objArr3);
                return;
            }
            return;
        }
        if (n2 instanceof TabLayout) {
            TabLayout tabLayout = (TabLayout) n2;
            if (obj2 instanceof d1) {
                c.b.a.a.h hVar = new c.b.a.a.h(this.b, 2);
                int i4 = i(str.substring(0, str.length() - 4));
                Iterator<HashMap<Integer, Object>> it = ((d1) obj2).a.iterator();
                while (it.hasNext()) {
                    HashMap<Integer, Object> next = it.next();
                    LinearLayout linearLayout = new LinearLayout(this.b);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(i2, i3));
                    linearLayout.setOrientation(1);
                    openRestoreinterface(str, linearLayout, i4, next);
                    tabLayout.addTab(tabLayout.newTab().setCustomView(linearLayout));
                    Iterator<Integer> it2 = next.keySet().iterator();
                    while (it2.hasNext()) {
                        int parseInt = Integer.parseInt(String.valueOf(it2.next()));
                        if (parseInt > 0) {
                            c.b.a.a.f.w(linearLayout.findViewById(parseInt + i4), next.get(Integer.valueOf(parseInt)), next, hVar);
                        }
                    }
                }
            }
        }
    }

    public void uninapp(String str) {
        this.f1896c.startActivity(new Intent("android.intent.action.DELETE", c.b.a.a.k.c(this.f1896c, "package:" + str)));
    }

    public Bitmap uqr(String str, int i2) {
        return new c.b.a.a.m(this.f1896c, this.b).c(str, i2);
    }

    public String uqr(Object obj) {
        return (obj instanceof Bitmap ? new c.b.a.a.m(this.f1896c, this.b).d((Bitmap) obj) : new c.b.a.a.m(this.f1896c, this.b).e(w(String.valueOf(obj)))).f();
    }

    public void uqr() {
        new c.b.a.a.m(this.f1896c, this.b).a();
    }

    public void uqr(int i2) {
        new c.b.a.a.m(this.f1896c, this.b).b(i2);
    }

    public void urvw(Object obj) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 != null) {
            ((ViewGroup) n2.getParent()).removeView(n2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x004f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0050  */
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
            goto L4d
        L9:
            java.lang.Object r4 = r3.l(r4)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r0 = 46
            int r0 = r4.indexOf(r0)
            r2 = -1
            if (r0 == r2) goto L42
            java.lang.String r2 = r4.substring(r1, r0)
            int r0 = r0 + 1
            java.lang.String r4 = r4.substring(r0)
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = com.iapp.app.Aid_luaCode.f
            java.lang.Object r0 = r0.get(r2)
            if (r0 == 0) goto L40
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r0 = java.lang.Integer.parseInt(r0)
            android.app.Activity r2 = r3.b
            int r4 = java.lang.Integer.parseInt(r4)
            int r4 = r4 + r0
            android.view.View r4 = r2.findViewById(r4)
            goto L4d
        L40:
            r4 = 0
            goto L7
        L42:
            android.app.Activity r0 = r3.b
            int r4 = java.lang.Integer.parseInt(r4)
            android.view.View r4 = r0.findViewById(r4)
            goto L7
        L4d:
            if (r4 != 0) goto L50
            return r1
        L50:
            com.iapp.app.i r1 = new com.iapp.app.i
            android.content.Context r2 = r3.f1896c
            r1.<init>(r4, r2, r0)
            boolean r4 = r1.e0(r5, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_luaCode.us(java.lang.Object, java.lang.String, java.lang.Object):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x004f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0050  */
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
            goto L4d
        L9:
            java.lang.Object r4 = r3.l(r4)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r0 = 46
            int r0 = r4.indexOf(r0)
            r2 = -1
            if (r0 == r2) goto L42
            java.lang.String r2 = r4.substring(r1, r0)
            int r0 = r0 + 1
            java.lang.String r4 = r4.substring(r0)
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = com.iapp.app.Aid_luaCode.f
            java.lang.Object r0 = r0.get(r2)
            if (r0 == 0) goto L40
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r0 = java.lang.Integer.parseInt(r0)
            android.app.Activity r2 = r3.b
            int r4 = java.lang.Integer.parseInt(r4)
            int r4 = r4 + r0
            android.view.View r4 = r2.findViewById(r4)
            goto L4d
        L40:
            r4 = 0
            goto L7
        L42:
            android.app.Activity r0 = r3.b
            int r4 = java.lang.Integer.parseInt(r4)
            android.view.View r4 = r0.findViewById(r4)
            goto L7
        L4d:
            if (r4 != 0) goto L50
            return r1
        L50:
            com.iapp.app.i r1 = new com.iapp.app.i
            android.content.Context r2 = r3.f1896c
            r1.<init>(r4, r2, r0)
            boolean r4 = r1.f0(r5, r6, r7)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_luaCode.us(java.lang.Object, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x004f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0050  */
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
            goto L4d
        L9:
            java.lang.Object r4 = r3.l(r4)
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r0 = 46
            int r0 = r4.indexOf(r0)
            r2 = -1
            if (r0 == r2) goto L42
            java.lang.String r2 = r4.substring(r1, r0)
            int r0 = r0 + 1
            java.lang.String r4 = r4.substring(r0)
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = com.iapp.app.Aid_luaCode.f
            java.lang.Object r0 = r0.get(r2)
            if (r0 == 0) goto L40
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r0 = java.lang.Integer.parseInt(r0)
            android.app.Activity r2 = r3.b
            int r4 = java.lang.Integer.parseInt(r4)
            int r4 = r4 + r0
            android.view.View r4 = r2.findViewById(r4)
            goto L4d
        L40:
            r4 = 0
            goto L7
        L42:
            android.app.Activity r0 = r3.b
            int r4 = java.lang.Integer.parseInt(r4)
            android.view.View r4 = r0.findViewById(r4)
            goto L7
        L4d:
            if (r4 != 0) goto L50
            return r1
        L50:
            com.iapp.app.i r1 = new com.iapp.app.i
            android.content.Context r2 = r3.f1896c
            r1.<init>(r4, r2, r0)
            boolean r4 = r1.g0(r5, r6, r7, r8)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_luaCode.us(java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x004f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0050  */
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
            goto L4d
        L9:
            java.lang.Object r9 = r8.l(r9)
            java.lang.String r9 = java.lang.String.valueOf(r9)
            r0 = 46
            int r0 = r9.indexOf(r0)
            r2 = -1
            if (r0 == r2) goto L42
            java.lang.String r2 = r9.substring(r1, r0)
            int r0 = r0 + 1
            java.lang.String r9 = r9.substring(r0)
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = com.iapp.app.Aid_luaCode.f
            java.lang.Object r0 = r0.get(r2)
            if (r0 == 0) goto L40
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r0 = java.lang.Integer.parseInt(r0)
            android.app.Activity r2 = r8.b
            int r9 = java.lang.Integer.parseInt(r9)
            int r9 = r9 + r0
            android.view.View r9 = r2.findViewById(r9)
            goto L4d
        L40:
            r9 = 0
            goto L7
        L42:
            android.app.Activity r0 = r8.b
            int r9 = java.lang.Integer.parseInt(r9)
            android.view.View r9 = r0.findViewById(r9)
            goto L7
        L4d:
            if (r9 != 0) goto L50
            return r1
        L50:
            com.iapp.app.i r2 = new com.iapp.app.i
            android.content.Context r1 = r8.f1896c
            r2.<init>(r9, r1, r0)
            r3 = r10
            r4 = r11
            r5 = r12
            r6 = r13
            r7 = r14
            boolean r9 = r2.h0(r3, r4, r5, r6, r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_luaCode.us(java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    public Object usg(Camera camera, boolean z2) {
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return c.b.a.a.r.i(this.f1896c, z2);
        }
        if (z2) {
            camera = Camera.open();
            H(camera);
            return camera;
        }
        if (camera == null) {
            return null;
        }
        G(camera);
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
        Intent intent = new Intent("android.intent.action.SENDTO", c.b.a.a.k.c(this.f1896c, "smsto:" + str));
        intent.putExtra("sms_body", str2);
        this.f1896c.startActivity(intent);
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
            mVar.q(w(str2), i2, z2);
        }
    }

    public com.iapp.app.m usxh(Object obj, int i2) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 instanceof SurfaceView) {
            return new com.iapp.app.m(this.f1896c, this.b, (SurfaceView) n2, false, i2);
        }
        return null;
    }

    public com.iapp.app.m usxh(Object obj, int i2, int i3, int i4, int i5) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 instanceof SurfaceView) {
            return new com.iapp.app.m(this.f1896c, this.b, (SurfaceView) n2, false, i2, i3, i4, i5);
        }
        return null;
    }

    public com.iapp.app.m usxq(Object obj, int i2) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 instanceof SurfaceView) {
            return new com.iapp.app.m(this.f1896c, this.b, (SurfaceView) n2, true, i2);
        }
        return null;
    }

    public com.iapp.app.m usxq(Object obj, int i2, int i3, int i4, int i5) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 instanceof SurfaceView) {
            return new com.iapp.app.m(this.f1896c, this.b, (SurfaceView) n2, true, i2, i3, i4, i5);
        }
        return null;
    }

    public Object utb(Object obj, Object obj2) {
        View n2;
        View n3;
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
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 != null && (n2 instanceof Toolbar)) {
            if (obj2 instanceof View) {
                n3 = (View) obj2;
            } else {
                Object l3 = l(obj2);
                n3 = n(l3, String.valueOf(l3));
            }
            if (n3 != null && (n3 instanceof DrawerLayout)) {
                ((AppCompatActivity) this.b).getSupportActionBar().setHomeButtonEnabled(true);
                DrawerLayout drawerLayout = (DrawerLayout) n3;
                String obj3 = ((Object[]) drawerLayout.getTag())[2].toString();
                i iVar = new i(this.b, drawerLayout, (Toolbar) n2, 0, 0, obj3.contains("<eventItme type=\"ondrawerclosed\">"), drawerLayout, obj3.contains("<eventItme type=\"ondraweropened\">"), obj3.contains("<eventItme type=\"onoptionsitemselected\">"));
                drawerLayout.setDrawerListener(iVar);
                iVar.syncState();
            }
        }
        return null;
    }

    public void utb(Object obj) {
        View n2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 == null || !(n2 instanceof Toolbar)) {
            return;
        }
        ((AppCompatActivity) this.b).setSupportActionBar((Toolbar) n2);
    }

    public void utb(Object obj, Object obj2, Object obj3) {
        View n2;
        View n3;
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
                        n2 = (View) obj2;
                    } else {
                        Object l2 = l(obj2);
                        n2 = n(l2, String.valueOf(l2));
                    }
                    if (n2 == null || !(n2 instanceof Toolbar)) {
                        return;
                    }
                    ((Toolbar) n2).setOverflowIcon(com.iapp.app.i.m(String.valueOf(obj3), this.f1896c));
                    return;
                }
                return;
            }
            if (obj2 instanceof View) {
                n3 = (View) obj2;
            } else {
                Object l3 = l(obj2);
                n3 = n(l3, String.valueOf(l3));
            }
            if (n3 == null || !(n3 instanceof Toolbar)) {
                return;
            }
            ((Toolbar) n3).setNavigationIcon(com.iapp.app.i.m(String.valueOf(obj3), this.f1896c));
            z2 = true;
            ((AppCompatActivity) this.b).getSupportActionBar().setHomeButtonEnabled(true);
            ((AppCompatActivity) this.b).getSupportActionBar().setDisplayShowHomeEnabled(true);
            supportActionBar = ((AppCompatActivity) this.b).getSupportActionBar();
        }
        supportActionBar.setDisplayHomeAsUpEnabled(z2);
    }

    public void utb(Object obj, Object obj2, Object obj3, LuaObject luaObject) {
        View n2;
        if (obj.equals("set") && obj2.equals("leftck")) {
            if (obj3 instanceof View) {
                n2 = (View) obj3;
            } else {
                Object l2 = l(obj3);
                n2 = n(l2, String.valueOf(l2));
            }
            if (n2 == null || !(n2 instanceof Toolbar)) {
                return;
            }
            ((Toolbar) n2).setNavigationOnClickListener(new j(this, luaObject));
            ((AppCompatActivity) this.b).getSupportActionBar().setHomeButtonEnabled(true);
            ((AppCompatActivity) this.b).getSupportActionBar().setDisplayShowHomeEnabled(true);
            ((AppCompatActivity) this.b).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public View utw(Object obj, String str, String str2, String str3, String str4, String str5, boolean z2, LuaObject luaObject, LuaObject luaObject2, LuaObject luaObject3) {
        AlertDialog.Builder negativeButton = new AlertDialog.Builder(this.f1896c).setTitle(str).setPositiveButton(str3, new a1(this, luaObject)).setNeutralButton(str4, new z0(this, luaObject2)).setNegativeButton(str5, new y0(this, luaObject3));
        if (obj != null) {
            negativeButton.setIcon(obj instanceof Drawable ? (Drawable) obj : com.iapp.app.i.m(String.valueOf(l(obj)), this.f1896c));
        }
        LinearLayout linearLayout = null;
        if (str2.endsWith(".ilua")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout2 = new LinearLayout(this.f1896c);
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

    public View utw(Object obj, String str, String str2, String str3, String str4, boolean z2, LuaObject luaObject, LuaObject luaObject2) {
        AlertDialog.Builder neutralButton = new AlertDialog.Builder(this.f1896c).setTitle(str).setPositiveButton(str3, new c1(this, luaObject)).setNeutralButton(str4, new b1(this, luaObject2));
        if (obj != null) {
            neutralButton.setIcon(obj instanceof Drawable ? (Drawable) obj : com.iapp.app.i.m(String.valueOf(l(obj)), this.f1896c));
        }
        LinearLayout linearLayout = null;
        if (str2.endsWith(".ilua")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout2 = new LinearLayout(this.f1896c);
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

    public View utw(Object obj, String str, String str2, String str3, boolean z2, LuaObject luaObject) {
        AlertDialog.Builder positiveButton = new AlertDialog.Builder(this.f1896c).setTitle(str).setPositiveButton(str3, new a(this, luaObject));
        if (obj != null) {
            positiveButton.setIcon(obj instanceof Drawable ? (Drawable) obj : com.iapp.app.i.m(String.valueOf(l(obj)), this.f1896c));
        }
        LinearLayout linearLayout = null;
        if (str2.endsWith(".ilua")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout2 = new LinearLayout(this.f1896c);
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
        AlertDialog.Builder title = new AlertDialog.Builder(this.f1896c).setTitle(str);
        if (obj != null) {
            title.setIcon(obj instanceof Drawable ? (Drawable) obj : com.iapp.app.i.m(String.valueOf(l(obj)), this.f1896c));
        }
        if (str2.endsWith(".ilua")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
            LinearLayout linearLayout = new LinearLayout(this.f1896c);
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
            LinearLayout linearLayout = new LinearLayout(this.f1896c);
            linearLayout.setOrientation(1);
            int i9 = i(str.substring(0, str.length() - 4));
            linearLayout.setId(i9);
            openRestoreinterface(str, linearLayout, i9, null);
            View childAt = linearLayout.getChildAt(0);
            linearLayout.removeAllViews();
            c.b.a.a.r.e(this.f1896c, childAt, i2, i3, i4, i5, i6, com.iapp.app.i.t(str2), i7, i8);
            return childAt;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public View uxf(String str, int i2, int i3, String str2) {
        try {
            LinearLayout linearLayout = new LinearLayout(this.f1896c);
            linearLayout.setOrientation(1);
            int i4 = i(str.substring(0, str.length() - 4));
            linearLayout.setId(i4);
            openRestoreinterface(str, linearLayout, i4, null);
            View childAt = linearLayout.getChildAt(0);
            linearLayout.removeAllViews();
            c.b.a.a.r.e(this.f1896c, childAt, 0, 0, i2, i3, 0, com.iapp.app.i.t(str2), 0, 0);
            return childAt;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void uxf(Object obj) {
        View view = obj instanceof View ? (View) obj : null;
        if (view != null) {
            ((WindowManager) this.f1896c.getApplicationContext().getSystemService("window")).updateViewLayout(view, view.getLayoutParams());
        }
    }

    public void uxf(Object obj, String str) {
        if (str.equals("del")) {
            View view = obj instanceof View ? (View) obj : null;
            if (view != null) {
                ((WindowManager) this.f1896c.getApplicationContext().getSystemService("window")).removeView(view);
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
            android.content.Context r3 = r1.f1896c
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
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.Aid_luaCode.uxf(java.lang.Object, java.lang.String, int, int, int, int, int, java.lang.String, int, int):void");
    }

    public void uxf(Object obj, String str, int i2, int i3, int i4, int i5, String str2) {
        if (str.equals("set")) {
            View view = obj instanceof View ? (View) obj : null;
            if (view != null) {
                WindowManager windowManager = (WindowManager) this.f1896c.getApplicationContext().getSystemService("window");
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
            vibrator = (Vibrator) this.f1896c.getSystemService("vibrator");
        }
        vibrator.vibrate(j2);
        return vibrator;
    }

    public Vibrator uzd(Vibrator vibrator, LuaObject luaObject, boolean z2) {
        if (vibrator == null) {
            vibrator = (Vibrator) this.f1896c.getSystemService("vibrator");
        }
        Object[] objArr = null;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        int length = objArr.length;
        long[] jArr = new long[length];
        for (int i2 = 0; i2 < length; i2++) {
            jArr[i2] = Long.parseLong(objArr[i2].toString());
        }
        vibrator.vibrate(jArr, z2 ? 1 : -1);
        return vibrator;
    }

    @TargetApi(11)
    public Object uzd(Vibrator vibrator, String str) {
        if (vibrator == null) {
            vibrator = (Vibrator) this.f1896c.getSystemService("vibrator");
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

    public Object yul(Object obj, String str) {
        View n2;
        View p2;
        if (obj instanceof View) {
            n2 = (View) obj;
        } else {
            Object l2 = l(obj);
            n2 = n(l2, String.valueOf(l2));
        }
        if (n2 == null || (p2 = c.b.a.a.f.p(this.f1896c, str)) == null) {
            return null;
        }
        ((ViewGroup) n2).addView(p2);
        return null;
    }

    public Object yul(String str) {
        return c.b.a.a.f.p(this.f1896c, str);
    }

    public int zdp(float f2) {
        return c.b.a.a.p.l(this.f1896c, f2);
    }

    public Object zj(String str) {
        return c.b.a.a.f.n(str, new Object[]{"Activity", this.f1896c});
    }

    public Object zj(String str, LuaObject luaObject) {
        Object[] objArr;
        Object[] objArr2;
        try {
            objArr = luaObject.array();
        } catch (Exception e2) {
            e2.printStackTrace();
            objArr = null;
        }
        if (objArr == null) {
            objArr2 = new Object[]{"Activity", this.f1896c};
        } else {
            int length = objArr.length;
            Object[] objArr3 = new Object[length + 2];
            objArr3[0] = "Activity";
            objArr3[1] = this.f1896c;
            for (int i2 = 0; i2 < length; i2++) {
                objArr3[i2 + 2] = objArr[i2];
            }
            objArr2 = objArr3;
        }
        return c.b.a.a.f.n(str, objArr2);
    }

    public int zpd(float f2) {
        return c.b.a.a.p.w(this.f1896c, f2);
    }

    public int zps(float f2) {
        return c.b.a.a.p.x(this.f1896c, f2);
    }

    public int zsp(float f2) {
        return c.b.a.a.p.A(this.f1896c, f2);
    }
}
