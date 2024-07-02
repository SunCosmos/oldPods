package com.iapp.app;

import android.R;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.AbsSeekBar;
import android.widget.AbsSpinner;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import bsh.org.objectweb.asm.Constants;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.iapp.app.x5.WebView;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import java.lang.reflect.Field;
import pl.droidsonroids.gif.GifImageView;

/* loaded from: classes.dex */
public class i {
    private View a;
    private Context b;

    /* renamed from: c, reason: collision with root package name */
    private int f1935c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements InputFilter {
        a(i iVar) {
        }

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements InputFilter {
        b(i iVar) {
        }

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
            return charSequence.length() < 1 ? spanned.subSequence(i4, i5) : "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class c extends RecyclerView.ItemDecoration {
        private int a;
        private int b;

        /* renamed from: c, reason: collision with root package name */
        private int f1936c;

        /* renamed from: d, reason: collision with root package name */
        private int f1937d;

        public c(i iVar, int i2, int i3, int i4, int i5) {
            this.a = i2;
            this.b = i3;
            this.f1936c = i4;
            this.f1937d = i5;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            rect.left = this.a;
            rect.right = this.f1936c;
            rect.bottom = this.f1937d;
            rect.top = this.b;
        }
    }

    public i(View view, Context context) {
        this.a = null;
        this.b = null;
        this.f1935c = 0;
        this.a = view;
        this.b = context;
    }

    public i(View view, Context context, int i2) {
        this.a = null;
        this.b = null;
        this.f1935c = 0;
        this.a = view;
        this.b = context;
        this.f1935c = i2;
    }

    private int A(String str) {
        if (!str.contains("|")) {
            return z(str.trim());
        }
        String[] d2 = c.b.a.a.q.d(str, "|");
        int length = d2.length;
        if (length == 2) {
            return z(d2[1].trim()) | z(d2[0].trim());
        }
        if (length == 3) {
            return z(d2[2].trim()) | z(d2[0].trim()) | z(d2[1].trim());
        }
        if (length != 4) {
            return 0;
        }
        return z(d2[3].trim()) | z(d2[0].trim()) | z(d2[1].trim()) | z(d2[2].trim());
    }

    private ViewGroup.LayoutParams C(ViewGroup.LayoutParams layoutParams, String str) {
        FrameLayout.LayoutParams layoutParams2;
        LinearLayout.LayoutParams layoutParams3;
        int t = t(str);
        if (!(layoutParams instanceof LinearLayout.LayoutParams)) {
            if (layoutParams instanceof DrawerLayout.LayoutParams) {
                ((DrawerLayout.LayoutParams) layoutParams).gravity = t;
            } else {
                if (layoutParams instanceof FrameLayout.LayoutParams) {
                    layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                } else if (layoutParams instanceof AppBarLayout.LayoutParams) {
                    layoutParams3 = (AppBarLayout.LayoutParams) layoutParams;
                } else if (layoutParams instanceof CollapsingToolbarLayout.LayoutParams) {
                    layoutParams2 = (CollapsingToolbarLayout.LayoutParams) layoutParams;
                } else if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                    ((CoordinatorLayout.LayoutParams) layoutParams).gravity = t;
                } else if (layoutParams instanceof ViewPager.LayoutParams) {
                    ((ViewPager.LayoutParams) layoutParams).gravity = t;
                } else {
                    if (!(layoutParams instanceof VerticalViewPager.LayoutParams)) {
                        return null;
                    }
                    ((VerticalViewPager.LayoutParams) layoutParams).b = t;
                }
                layoutParams2.gravity = t;
            }
            return layoutParams;
        }
        layoutParams3 = (LinearLayout.LayoutParams) layoutParams;
        layoutParams3.gravity = t;
        return layoutParams;
    }

    private ViewGroup.LayoutParams H(ViewGroup.LayoutParams layoutParams, String str) {
        int parseDouble = (int) Double.parseDouble(str);
        if (!(layoutParams instanceof LinearLayout.LayoutParams)) {
            return null;
        }
        ((LinearLayout.LayoutParams) layoutParams).weight = parseDouble;
        return layoutParams;
    }

    @TargetApi(9)
    private int J(String str) {
        if (str.equals("always")) {
            return 0;
        }
        if (str.equals("if")) {
            return 1;
        }
        return str.equals("never") ? 2 : 0;
    }

    private ImageView.ScaleType O(String str) {
        if (str.equals("center")) {
            return ImageView.ScaleType.CENTER;
        }
        if (str.equals("centercrop")) {
            return ImageView.ScaleType.CENTER_CROP;
        }
        if (str.equals("centerinside")) {
            return ImageView.ScaleType.CENTER_INSIDE;
        }
        if (str.equals("fitcenter")) {
            return ImageView.ScaleType.FIT_CENTER;
        }
        if (str.equals("fitend")) {
            return ImageView.ScaleType.FIT_END;
        }
        if (str.equals("fitstart")) {
            return ImageView.ScaleType.FIT_START;
        }
        if (str.equals("fitxy")) {
            return ImageView.ScaleType.FIT_XY;
        }
        if (str.equals("matrix")) {
            return ImageView.ScaleType.MATRIX;
        }
        return null;
    }

    private int P(String str) {
        if (str.equals("insideoverlay")) {
            return 0;
        }
        if (str.equals("insideinset")) {
            return 16777216;
        }
        if (str.equals("outsideoverlay")) {
            return 33554432;
        }
        return str.equals("outsideinset") ? 50331648 : 0;
    }

    private float[] S(String str) {
        float parseFloat;
        int i2;
        if (str.endsWith("sp")) {
            str = str.substring(0, str.length() - 2);
        } else if (!str.matches("[0-9]+")) {
            if (str.endsWith("dp")) {
                parseFloat = Float.parseFloat(str.substring(0, str.length() - 2));
                i2 = 1;
            } else {
                if (!str.endsWith("px")) {
                    return null;
                }
                parseFloat = Float.parseFloat(str.substring(0, str.length() - 2));
                i2 = 0;
            }
            return new float[]{i2, parseFloat};
        }
        parseFloat = Float.parseFloat(str);
        i2 = 2;
        return new float[]{i2, parseFloat};
    }

    private int T(String str) {
        if (str.equals("bold")) {
            return 1;
        }
        if (str.equals("italic")) {
            return 2;
        }
        return str.equals("bolditalic") ? 3 : 0;
    }

    private Typeface U(String str) {
        if (str.equals("normal")) {
            return null;
        }
        if (str.equals("sans")) {
            return Typeface.SANS_SERIF;
        }
        if (str.equals("serif")) {
            return Typeface.SERIF;
        }
        if (str.equals("monospace")) {
            return Typeface.MONOSPACE;
        }
        if (str.startsWith("@")) {
            return Typeface.createFromAsset(this.b.getAssets(), c.b.a.a.d.p(this.b, str));
        }
        if (str.startsWith("%") || str.startsWith("$") || str.startsWith("/")) {
            return Typeface.createFromFile(c.b.a.a.d.p(this.b, str));
        }
        return null;
    }

    private int Z(String str) {
        return str.endsWith("dp") ? c.b.a.a.p.l(this.b, Float.parseFloat(str.substring(0, str.length() - 2))) : (int) Double.parseDouble(str);
    }

    private boolean Z1(Object obj) {
        View view = this.a;
        if (!(view instanceof WebView)) {
            return false;
        }
        ((WebView) view).goBackOrForward((int) Double.parseDouble(String.valueOf(obj)));
        return true;
    }

    private boolean Z2(String str, String str2) {
        View view = this.a;
        if (!(view instanceof EditText)) {
            return false;
        }
        ((EditText) view).setSelection((int) Double.parseDouble(str), (int) Double.parseDouble(str2));
        return true;
    }

    private int c(String str) {
        return str.equals("top") ? ((ListView) this.a).getTop() : str.equals("bottom") ? ((ListView) this.a).getBottom() : str.equals("left") ? ((ListView) this.a).getLeft() : str.equals("right") ? ((ListView) this.a).getRight() : (int) Double.parseDouble(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0044 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean c0(android.view.Menu r4, int r5, java.lang.String r6, android.content.Context r7) {
        /*
            r0 = 124(0x7c, float:1.74E-43)
            java.lang.String[] r6 = c.b.a.a.q.b(r6, r0)
            int r0 = r6.length
            r1 = 3
            r2 = 0
            if (r0 <= r1) goto L16
            r1 = r6[r1]     // Catch: java.lang.Exception -> L12
            int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Exception -> L12
            goto L17
        L12:
            r1 = move-exception
            r1.printStackTrace()
        L16:
            r1 = 0
        L17:
            r3 = r6[r2]
            r4.add(r2, r5, r1, r3)
            int r5 = r4.size()
            r1 = 1
            int r5 = r5 - r1
            android.view.MenuItem r4 = r4.getItem(r5)
            r5 = 0
            r3 = r6[r1]
            int r3 = r3.length()
            if (r3 <= 0) goto L3a
            r3 = r6[r1]     // Catch: java.lang.Exception -> L36
            android.graphics.drawable.Drawable r5 = m(r3, r7)     // Catch: java.lang.Exception -> L36
            goto L3a
        L36:
            r7 = move-exception
            r7.printStackTrace()
        L3a:
            if (r5 == 0) goto L40
            r4.setIcon(r5)
            goto L41
        L40:
            r1 = 0
        L41:
            r5 = 2
            if (r0 <= r5) goto L4f
            r5 = r6[r5]     // Catch: java.lang.Exception -> L4b
            int r2 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.Exception -> L4b
            goto L4f
        L4b:
            r5 = move-exception
            r5.printStackTrace()
        L4f:
            r4.setShowAsAction(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iapp.app.i.c0(android.view.Menu, int, java.lang.String, android.content.Context):boolean");
    }

    public static int g(String str) {
        if (!str.contains("|")) {
            return h(str.trim());
        }
        String[] d2 = c.b.a.a.q.d(str, "|");
        int length = d2.length;
        if (length == 2) {
            return h(d2[1].trim()) | h(d2[0].trim());
        }
        if (length == 3) {
            return h(d2[2].trim()) | h(d2[0].trim()) | h(d2[1].trim());
        }
        if (length != 4) {
            return 1;
        }
        return h(d2[3].trim()) | h(d2[0].trim()) | h(d2[1].trim()) | h(d2[2].trim());
    }

    private static int h(String str) {
        if (str.equals("exitUntilCollapsed")) {
            return 2;
        }
        if (str.equals("enterAlwaysCollapsed")) {
            return 8;
        }
        if (str.equals("scroll")) {
            return 1;
        }
        if (str.equals("enterAlways")) {
            return 4;
        }
        if (str.equals("snap")) {
            return 16;
        }
        if (str.matches("[0-9]+")) {
            return (int) Double.parseDouble(str);
        }
        return 1;
    }

    private static Drawable i(Context context, int i2) {
        Drawable drawable = null;
        try {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(i2, typedValue, true);
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(typedValue.resourceId, new int[]{i2});
            drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            return drawable;
        } catch (Exception e) {
            e.printStackTrace();
            return drawable;
        }
    }

    private ColorStateList j(int i2, int i3, int i4) {
        return new ColorStateList(new int[][]{new int[]{-16842910}, new int[]{R.attr.state_checked}, new int[0]}, new int[]{i4, i2, i3});
    }

    private int l(String str) {
        if (str.equals("web")) {
            return 1;
        }
        if (str.equals("email")) {
            return 2;
        }
        if (str.equals("phone")) {
            return 4;
        }
        if (str.equals("map")) {
            return 8;
        }
        return str.equals("all") ? 15 : 0;
    }

    public static Drawable m(String str, Context context) {
        if (str.startsWith("@")) {
            return new BitmapDrawable(c.b.a.a.i.b(context, c.b.a.a.d.p(context, str)));
        }
        if (str.startsWith("%") || str.startsWith("$") || str.startsWith("/")) {
            return new BitmapDrawable(c.b.a.a.i.c(c.b.a.a.d.p(context, str)));
        }
        if (str.startsWith("#")) {
            return new ColorDrawable(Color.parseColor(str));
        }
        if (str.equals("through")) {
            return new c.c.a.c(context).b(Color.parseColor("#10000000"), Color.parseColor("#10000000"), Color.parseColor("#00000000"));
        }
        if (str.equals("white")) {
            return new c.c.a.c(context).b(Color.parseColor("#e6eaf7"), Color.parseColor("#e6eaf7"), Color.parseColor("#ffffff"));
        }
        if (str.equals("black")) {
            return new c.c.a.c(context).b(Color.parseColor("#202020"), Color.parseColor("#202020"), Color.parseColor("#000000"));
        }
        if (str.equals("selectableitem")) {
            return i(context, R.attr.selectableItemBackground);
        }
        if (str.equals("selectableitemborderless")) {
            return i(context, R.attr.selectableItemBackgroundBorderless);
        }
        if (str.matches("[0-9]+")) {
            return context.getResources().getDrawable((int) Double.parseDouble(str));
        }
        if (str.equals("null")) {
            return null;
        }
        return new BitmapDrawable(c.b.a.a.i.c(c.b.a.a.d.p(context, str)));
    }

    private int n(String str) {
        if (str.equals("spread_inside")) {
            return 1;
        }
        return (!str.equals("spread") && str.equals("packed")) ? 2 : 0;
    }

    private int q(String str) {
        return str.startsWith("#") ? Color.parseColor(str) : Integer.parseInt(str);
    }

    private int r(String str) {
        if (str.equals("closed")) {
            return 1;
        }
        if (str.equals("open")) {
            return 2;
        }
        if (str.equals("unlocked")) {
            return 0;
        }
        return Integer.parseInt(str);
    }

    private TextUtils.TruncateAt s(String str) {
        return str.equals("start") ? TextUtils.TruncateAt.START : str.equals("end") ? TextUtils.TruncateAt.END : str.equals("middle") ? TextUtils.TruncateAt.MIDDLE : str.equals("marquee") ? TextUtils.TruncateAt.MARQUEE : TextUtils.TruncateAt.valueOf(str);
    }

    public static int t(String str) {
        if (!str.contains("|")) {
            return u(str.trim());
        }
        String[] d2 = c.b.a.a.q.d(str, "|");
        int length = d2.length;
        if (length == 2) {
            return u(d2[1].trim()) | u(d2[0].trim());
        }
        if (length == 3) {
            return u(d2[2].trim()) | u(d2[0].trim()) | u(d2[1].trim());
        }
        if (length != 4) {
            return 0;
        }
        return u(d2[3].trim()) | u(d2[0].trim()) | u(d2[1].trim()) | u(d2[2].trim());
    }

    @TargetApi(14)
    private static int u(String str) {
        int i2 = Build.VERSION.SDK_INT;
        if (str.equals("center")) {
            return 17;
        }
        if (str.equals("top")) {
            return 48;
        }
        if (str.equals("bottom")) {
            return 80;
        }
        if (str.equals("left")) {
            return 3;
        }
        if (str.equals("right")) {
            return 5;
        }
        if (str.equals("center_vertical")) {
            return 16;
        }
        if (str.equals("center_horizontal")) {
            return 1;
        }
        if (str.equals("start")) {
            return i2 >= 14 ? 8388611 : 3;
        }
        if (str.equals("end")) {
            return i2 >= 14 ? 8388613 : 5;
        }
        if (str.matches("[0-9]+")) {
            return (int) Double.parseDouble(str);
        }
        return 0;
    }

    private int z(String str) {
        if (str.equals("none")) {
            return 0;
        }
        if (str.equals("text")) {
            return 1;
        }
        if (str.equals("textcapcharacters")) {
            return 4097;
        }
        if (str.equals("textcapwords")) {
            return 8193;
        }
        if (str.equals("textcapsentences")) {
            return 16385;
        }
        if (str.equals("textautocomplete")) {
            return 65537;
        }
        if (str.equals("textautocorrect")) {
            return 32769;
        }
        if (str.equals("texturi")) {
            return 17;
        }
        if (str.equals("textemailaddress")) {
            return 33;
        }
        if (str.equals("textpostaladdress")) {
            return 113;
        }
        if (str.equals("textpassword")) {
            return 129;
        }
        if (str.equals("textvisiblepassword")) {
            return Constants.I2B;
        }
        if (str.equals("textwebedittext")) {
            return Constants.IF_ICMPLT;
        }
        if (str.equals("number")) {
            return 2;
        }
        if (str.equals("numbersigned")) {
            return 4098;
        }
        if (str.equals("numberdecimal")) {
            return 8194;
        }
        if (str.equals("phone")) {
            return 3;
        }
        if (str.equals("datetime")) {
            return 4;
        }
        if (str.equals("date")) {
            return 20;
        }
        if (str.equals("time")) {
            return 36;
        }
        if (str.matches("[0-9]+")) {
            return (int) Double.parseDouble(str);
        }
        return 0;
    }

    public boolean A0(String str) {
        View view = this.a;
        if (!(view instanceof CollapsingToolbarLayout)) {
            return false;
        }
        ((CollapsingToolbarLayout) view).setExpandedTitleGravity(t(str));
        return true;
    }

    public boolean A1(int i2) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).rightToLeft = i2 != 0 ? this.f1935c + i2 : 0;
        return true;
    }

    public boolean A2(int i2) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setFilters(new InputFilter[]{new InputFilter.LengthFilter(i2)});
        return true;
    }

    public boolean A3(String str) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setTypeface(((TextView) view).getTypeface(), T(str));
        return true;
    }

    public Object B(Object obj) {
        View view = this.a;
        if (view instanceof DrawerLayout) {
            return Boolean.valueOf(((DrawerLayout) view).isDrawerOpen(t(obj.toString())));
        }
        return null;
    }

    public boolean B0(String str) {
        if (!(this.a instanceof CollapsingToolbarLayout)) {
            return false;
        }
        String[] a2 = c.b.a.a.q.a(str.trim());
        int length = a2.length;
        ((CollapsingToolbarLayout) this.a).setExpandedTitleMargin(length > 0 ? Z(a2[0].trim()) : 0, length > 1 ? Z(a2[1].trim()) : 0, length > 2 ? Z(a2[2].trim()) : 0, length > 3 ? Z(a2[3].trim()) : 0);
        return true;
    }

    public boolean B1(int i2) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).rightToRight = i2 != 0 ? this.f1935c + i2 : 0;
        return true;
    }

    public boolean B2(int i2) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setMaxLines(i2);
        return true;
    }

    public boolean B3(String str) {
        View view = this.a;
        if (!(view instanceof SwitchCompat)) {
            return false;
        }
        ((SwitchCompat) view).setThumbTintList(ColorStateList.valueOf(q(str)));
        return true;
    }

    public boolean C0(int i2) {
        if (!(this.a.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
            return false;
        }
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) this.a.getLayoutParams();
        layoutParams.setAnchorId(this.f1935c + i2);
        this.a.setLayoutParams(layoutParams);
        return true;
    }

    public boolean C1(int i2) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).startToEnd = i2 != 0 ? this.f1935c + i2 : 0;
        return true;
    }

    public boolean C2(String str) {
        View view = this.a;
        if (!(view instanceof ImageView)) {
            return false;
        }
        ((ImageView) view).setMaxWidth(Z(str));
        return true;
    }

    public boolean C3(String str) {
        SwitchCompat switchCompat;
        PorterDuff.Mode mode;
        if (!(this.a instanceof SwitchCompat)) {
            return false;
        }
        if (str.equals("add")) {
            switchCompat = (SwitchCompat) this.a;
            mode = PorterDuff.Mode.ADD;
        } else if (str.equals("multiply")) {
            switchCompat = (SwitchCompat) this.a;
            mode = PorterDuff.Mode.MULTIPLY;
        } else if (str.equals("screen")) {
            switchCompat = (SwitchCompat) this.a;
            mode = PorterDuff.Mode.SCREEN;
        } else if (str.equals("src_atop")) {
            switchCompat = (SwitchCompat) this.a;
            mode = PorterDuff.Mode.SRC_ATOP;
        } else if (!str.equals("src_in") && str.equals("src_over")) {
            switchCompat = (SwitchCompat) this.a;
            mode = PorterDuff.Mode.SRC_OVER;
        } else {
            switchCompat = (SwitchCompat) this.a;
            mode = PorterDuff.Mode.SRC_IN;
        }
        switchCompat.setThumbTintMode(mode);
        return true;
    }

    public int D() {
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return 0;
    }

    public boolean D0(String str) {
        if (!(this.a.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
            return false;
        }
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) this.a.getLayoutParams();
        layoutParams.anchorGravity = t(str);
        this.a.setLayoutParams(layoutParams);
        return true;
    }

    public boolean D1(int i2) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).startToStart = i2 != 0 ? this.f1935c + i2 : 0;
        return true;
    }

    public boolean D2(String str) {
        this.a.setMinimumHeight(Z(str));
        return true;
    }

    public boolean D3(String str) {
        SwitchCompat switchCompat;
        ColorStateList valueOf;
        if (!(this.a instanceof SwitchCompat)) {
            return false;
        }
        if (str.contains("|")) {
            String[] b2 = c.b.a.a.q.b(str, '|');
            switchCompat = (SwitchCompat) this.a;
            valueOf = j(q(b2[0].trim()), q(b2[1].trim()), q(b2[2].trim()));
        } else {
            switchCompat = (SwitchCompat) this.a;
            valueOf = ColorStateList.valueOf(q(str));
        }
        switchCompat.setTrackTintList(valueOf);
        return true;
    }

    public int E() {
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        }
        return 0;
    }

    public boolean E0(String str) {
        try {
            Class<?> cls = Class.forName(str);
            if (!(this.a.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
                return false;
            }
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) this.a.getLayoutParams();
            layoutParams.setBehavior((CoordinatorLayout.Behavior) cls.newInstance());
            this.a.setLayoutParams(layoutParams);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean E1(int i2) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).topToBottom = i2 != 0 ? this.f1935c + i2 : 0;
        return true;
    }

    public boolean E2(int i2) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setMinLines(i2);
        return true;
    }

    public boolean E3(String str) {
        SwitchCompat switchCompat;
        PorterDuff.Mode mode;
        View view = this.a;
        if (!(view instanceof SwitchCompat) || !(view instanceof SwitchCompat)) {
            return false;
        }
        if (str.equals("add")) {
            switchCompat = (SwitchCompat) this.a;
            mode = PorterDuff.Mode.ADD;
        } else if (str.equals("multiply")) {
            switchCompat = (SwitchCompat) this.a;
            mode = PorterDuff.Mode.MULTIPLY;
        } else if (str.equals("screen")) {
            switchCompat = (SwitchCompat) this.a;
            mode = PorterDuff.Mode.SCREEN;
        } else if (str.equals("src_atop")) {
            switchCompat = (SwitchCompat) this.a;
            mode = PorterDuff.Mode.SRC_ATOP;
        } else if (!str.equals("src_in") && str.equals("src_over")) {
            switchCompat = (SwitchCompat) this.a;
            mode = PorterDuff.Mode.SRC_OVER;
        } else {
            switchCompat = (SwitchCompat) this.a;
            mode = PorterDuff.Mode.SRC_IN;
        }
        switchCompat.setTrackTintMode(mode);
        return true;
    }

    public int F() {
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        }
        return 0;
    }

    public boolean F0(String str) {
        if (!(this.a.getLayoutParams() instanceof CollapsingToolbarLayout.LayoutParams)) {
            return false;
        }
        CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams) this.a.getLayoutParams();
        if (str.equals("pin")) {
            layoutParams.setCollapseMode(1);
        } else if (str.equals("parallax")) {
            layoutParams.setCollapseMode(2);
        } else if (str.equals("none")) {
            layoutParams.setCollapseMode(0);
        }
        this.a.setLayoutParams(layoutParams);
        return true;
    }

    public boolean F1(int i2) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).topToTop = i2 != 0 ? this.f1935c + i2 : 0;
        return true;
    }

    public boolean F2(String str) {
        this.a.setMinimumWidth(Z(str));
        return true;
    }

    public boolean F3(String str) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setTypeface(U(str));
        return true;
    }

    public int G() {
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        }
        return 0;
    }

    public boolean G0(String str) {
        if (!(this.a.getLayoutParams() instanceof AppBarLayout.LayoutParams)) {
            return false;
        }
        AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) this.a.getLayoutParams();
        layoutParams.setScrollFlags(g(str));
        this.a.setLayoutParams(layoutParams);
        return true;
    }

    public boolean G1(String str) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).verticalBias = Float.parseFloat(str);
        return true;
    }

    public boolean G2(String str) {
        View view = this.a;
        if (!(view instanceof RatingBar)) {
            return false;
        }
        ((RatingBar) view).setNumStars((int) Double.parseDouble(str));
        return true;
    }

    public boolean G3(String str) {
        StringBuilder sb;
        String str2;
        if (!(this.a instanceof WebView)) {
            return false;
        }
        if (!str.startsWith("@")) {
            if (str.startsWith("%") || str.startsWith("$") || str.startsWith("/")) {
                sb = new StringBuilder();
                str2 = "file://";
            }
            ((WebView) this.a).loadUrl(str);
            return true;
        }
        sb = new StringBuilder();
        str2 = "file:///android_asset/";
        sb.append(str2);
        sb.append(c.b.a.a.d.p(this.b, str));
        str = sb.toString();
        ((WebView) this.a).loadUrl(str);
        return true;
    }

    public boolean H0(String str) {
        int parseDouble;
        if (this.a instanceof Toolbar) {
            if (str.equals("ThemeOverlay_AppCompat")) {
                parseDouble = 2131755488;
            } else if (str.equals("ThemeOverlay_AppCompat_Dark")) {
                parseDouble = 2131755490;
            } else if (str.equals("ThemeOverlay_AppCompat_ActionBar")) {
                parseDouble = 2131755489;
            } else if (str.equals("ThemeOverlay_AppCompat_Dark_ActionBar")) {
                parseDouble = 2131755491;
            } else if (str.equals("ThemeOverlay_AppCompat_Dialog")) {
                parseDouble = 2131755494;
            } else if (str.equals("ThemeOverlay_AppCompat_Dialog_Alert")) {
                parseDouble = 2131755495;
            } else if (str.equals("ThemeOverlay_AppCompat_Light")) {
                parseDouble = 2131755496;
            } else if (str.matches("[0-9]+")) {
                parseDouble = (int) Double.parseDouble(str);
            }
            ((Toolbar) this.a).setPopupTheme(parseDouble);
        }
        return false;
    }

    public boolean H1(String str) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).verticalChainStyle = n(str);
        return true;
    }

    public boolean H2(Object obj) {
        View view = this.a;
        if (!(view instanceof DrawerLayout)) {
            return false;
        }
        ((DrawerLayout) view).openDrawer(t(obj.toString()));
        return true;
    }

    public boolean H3(String str, String str2, String str3) {
        View view = this.a;
        if (!(view instanceof WebView)) {
            return false;
        }
        WebView webView = (WebView) view;
        webView.getSettings().setDefaultTextEncodingName(str2);
        webView.loadDataWithBaseURL(null, str, str3, str2, null);
        return true;
    }

    public int I(String str) {
        if (str.equals("marquee_forever")) {
            return -1;
        }
        if (str.matches("[0-9]+")) {
            return (int) Double.parseDouble(str);
        }
        return 1;
    }

    public boolean I0(String str) {
        View view = this.a;
        if (!(view instanceof Toolbar)) {
            return false;
        }
        ((Toolbar) view).setSubtitle(str);
        return true;
    }

    public boolean I1(String str) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).verticalWeight = Integer.parseInt(str);
        return true;
    }

    public boolean I2(String str) {
        int i2 = !str.equals("horizontal") ? 1 : 0;
        View view = this.a;
        if (view instanceof LinearLayout) {
            ((LinearLayout) view).setOrientation(i2);
        } else if (view instanceof RadioGroup) {
            ((RadioGroup) view).setOrientation(i2);
        } else if (view instanceof TableLayout) {
            ((TableLayout) view).setOrientation(i2);
        } else {
            if (!(view instanceof TableRow)) {
                return false;
            }
            ((TableRow) view).setOrientation(i2);
        }
        return true;
    }

    public boolean I3(int i2) {
        return Y3(2, i2);
    }

    public boolean J0(String str) {
        View view = this.a;
        if (!(view instanceof Toolbar)) {
            return false;
        }
        ((Toolbar) view).setSubtitleTextColor(q(str));
        return true;
    }

    public boolean J1(Object obj) {
        View view = this.a;
        if (view instanceof ViewPager) {
            ((ViewPager) view).setCurrentItem((int) Double.parseDouble(obj.toString()));
            return true;
        }
        if (!(view instanceof VerticalViewPager)) {
            return false;
        }
        ((VerticalViewPager) view).setCurrentItem((int) Double.parseDouble(obj.toString()));
        return true;
    }

    @SuppressLint({"NewApi"})
    public boolean J2(String str) {
        if (Build.VERSION.SDK_INT < 9) {
            return true;
        }
        this.a.setOverScrollMode(J(str));
        return true;
    }

    public boolean J3(int i2) {
        return Y3(4, i2);
    }

    public int K() {
        return this.a.getPaddingBottom();
    }

    public boolean K0(String str) {
        View view = this.a;
        if (!(view instanceof TabLayout)) {
            return false;
        }
        TabLayout tabLayout = (TabLayout) view;
        tabLayout.addTab(tabLayout.newTab().setText(str));
        return true;
    }

    public boolean K1(String str) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setCursorVisible(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean K2(String str) {
        int Z = Z(str);
        this.a.setPadding(Z, Z, Z, Z);
        return true;
    }

    public boolean K3(int i2) {
        return Y3(8, i2);
    }

    public int L() {
        return this.a.getPaddingLeft();
    }

    public boolean L0(String str) {
        int i2;
        View view = this.a;
        if (!(view instanceof TabLayout)) {
            return false;
        }
        TabLayout tabLayout = (TabLayout) view;
        try {
            i2 = Integer.parseInt(str);
            tabLayout.removeTabAt(i2);
        } catch (Exception e) {
            e.printStackTrace();
            i2 = -1;
        }
        if (i2 != -1) {
            return true;
        }
        for (int i3 = 0; i3 < tabLayout.getTabCount(); i3++) {
            if (String.valueOf(tabLayout.getTabAt(i3).getText()).equals(str)) {
                tabLayout.removeTabAt(i3);
                return true;
            }
        }
        return true;
    }

    public boolean L1(String str) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setKeyListener(DigitsKeyListener.getInstance(str));
        return true;
    }

    public boolean L2(String str) {
        int Z = Z(str);
        View view = this.a;
        view.setPadding(view.getPaddingLeft(), this.a.getPaddingTop(), this.a.getPaddingRight(), Z);
        return true;
    }

    public boolean L3(int i2) {
        return Y3(5, i2);
    }

    public int M() {
        return this.a.getPaddingRight();
    }

    public boolean M0(int i2) {
        View view = this.a;
        if (!(view instanceof TabLayout)) {
            return false;
        }
        ((TabLayout) view).setTabGravity(i2);
        return true;
    }

    public boolean M1(Object obj) {
        if (!(this.a instanceof ListView)) {
            return false;
        }
        ((ListView) this.a).setDivider(obj instanceof Drawable ? (Drawable) obj : obj instanceof Bitmap ? new BitmapDrawable((Resources) null, (Bitmap) obj) : m(String.valueOf(obj), this.b));
        return true;
    }

    public boolean M2(String str) {
        int Z = Z(str);
        View view = this.a;
        view.setPadding(Z, view.getPaddingTop(), this.a.getPaddingRight(), this.a.getPaddingBottom());
        return true;
    }

    public boolean M3(String str) {
        return str.equals("true") || str.equals("1") ? Y3(12, -1) : Y3(12, 0);
    }

    public int N() {
        return this.a.getPaddingTop();
    }

    public boolean N0(String str) {
        View view = this.a;
        if (!(view instanceof TabLayout)) {
            return false;
        }
        ((TabLayout) view).setSelectedTabIndicatorColor(q(str));
        return true;
    }

    public boolean N1(String str) {
        View view = this.a;
        if (!(view instanceof ListView)) {
            return false;
        }
        ((ListView) view).setDivider(m(str, this.b));
        return true;
    }

    public boolean N2(String str) {
        int Z = Z(str);
        View view = this.a;
        view.setPadding(view.getPaddingLeft(), this.a.getPaddingTop(), Z, this.a.getPaddingBottom());
        return true;
    }

    public boolean N3(String str) {
        return str.equals("true") || str.equals("1") ? Y3(9, -1) : Y3(9, 0);
    }

    public boolean O0(String str) {
        View view = this.a;
        if (!(view instanceof TabLayout)) {
            return false;
        }
        ((TabLayout) view).setSelectedTabIndicatorHeight(Z(str));
        return true;
    }

    public boolean O1(String str) {
        View view = this.a;
        if (!(view instanceof ListView)) {
            return false;
        }
        ((ListView) view).setDividerHeight(Z(str));
        return true;
    }

    public boolean O2(String str) {
        int Z = Z(str);
        View view = this.a;
        view.setPadding(view.getPaddingLeft(), Z, this.a.getPaddingRight(), this.a.getPaddingBottom());
        return true;
    }

    public boolean O3(String str) {
        return str.equals("true") || str.equals("1") ? Y3(11, -1) : Y3(11, 0);
    }

    public boolean P0(String str) {
        boolean z = this.a instanceof TabLayout;
        return false;
    }

    public boolean P1(Object obj) {
        View view = this.a;
        if (!(view instanceof DrawerLayout)) {
            return false;
        }
        ((DrawerLayout) view).setDrawerLockMode(r(obj.toString()));
        return true;
    }

    public boolean P2(String str) {
        View view = this.a;
        if (!(view instanceof ProgressBar)) {
            return false;
        }
        ((ProgressBar) view).setProgress((int) Double.parseDouble(str));
        return true;
    }

    public boolean P3(String str) {
        return str.equals("true") || str.equals("1") ? Y3(10, -1) : Y3(10, 0);
    }

    public Object Q(Object obj) {
        View view = this.a;
        if (view instanceof TableLayout) {
            return Boolean.valueOf(((TableLayout) view).isColumnShrinkable((int) Double.parseDouble(String.valueOf(obj))));
        }
        return null;
    }

    public boolean Q0(String str) {
        View view = this.a;
        if (!(view instanceof TabLayout)) {
            return false;
        }
        TabLayout tabLayout = (TabLayout) view;
        String[] b2 = c.b.a.a.q.b(str, '|');
        tabLayout.removeAllTabs();
        for (String str2 : b2) {
            tabLayout.addTab(tabLayout.newTab().setText(str2));
        }
        return true;
    }

    public boolean Q1(String str) {
        if (!(this.a instanceof TextView)) {
            return false;
        }
        if (str.equals("true") || str.equals("1")) {
            ((TextView) this.a).setFilters(new InputFilter[]{new a(this)});
        } else {
            ((TextView) this.a).setFilters(new InputFilter[]{new b(this)});
        }
        return true;
    }

    public boolean Q2(String str) {
        View view = this.a;
        if (!(view instanceof RatingBar)) {
            return false;
        }
        ((RatingBar) view).setRating(Float.parseFloat(str));
        return true;
    }

    public boolean Q3(int i2) {
        return Y3(7, i2);
    }

    public Object R(Object obj) {
        View view = this.a;
        if (view instanceof TableLayout) {
            return Boolean.valueOf(((TableLayout) view).isColumnStretchable((int) Double.parseDouble(String.valueOf(obj))));
        }
        return null;
    }

    public boolean R0(int i2) {
        View view = this.a;
        if (!(view instanceof TabLayout)) {
            return false;
        }
        ((TabLayout) view).setTabMode(i2);
        return true;
    }

    @SuppressLint({"NewApi"})
    public boolean R1(String str) {
        if (Build.VERSION.SDK_INT < 21) {
            return true;
        }
        this.a.setElevation(Z(str));
        return true;
    }

    public boolean R2(String str) {
        View view = this.a;
        if (!(view instanceof SwipeRefreshLayout)) {
            return false;
        }
        ((SwipeRefreshLayout) view).setRefreshing(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean R3(int i2) {
        return Y3(6, i2);
    }

    public boolean S0(String str) {
        if (this.a instanceof TabLayout) {
            String[] b2 = c.b.a.a.q.b(str, '|');
            if (b2.length == 2) {
                ((TabLayout) this.a).setTabTextColors(q(b2[0]), q(b2[1]));
                return true;
            }
        }
        return false;
    }

    public boolean S1(String str) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setEllipsize(s(str));
        return true;
    }

    public boolean S2(String str) {
        View view = this.a;
        if (!(view instanceof ImageView)) {
            return false;
        }
        ((ImageView) view).setScaleType(O(str));
        return true;
    }

    public boolean S3(int i2) {
        return Y3(3, i2);
    }

    public boolean T0(String str) {
        View view = this.a;
        if (!(view instanceof Toolbar)) {
            return false;
        }
        ((Toolbar) view).setTitle(str);
        return true;
    }

    public boolean T1(String str) {
        if (str.equals("none")) {
            this.a.setVerticalFadingEdgeEnabled(false);
            this.a.setHorizontalFadingEdgeEnabled(false);
            return true;
        }
        if (str.equals("horizontal")) {
            this.a.setHorizontalFadingEdgeEnabled(true);
        } else {
            if (!str.equals("vertical")) {
                return false;
            }
            this.a.setVerticalFadingEdgeEnabled(true);
        }
        return true;
    }

    public boolean T2(String str) {
        if (str.equals("none")) {
            this.a.setVerticalScrollBarEnabled(false);
            this.a.setHorizontalScrollBarEnabled(false);
            return true;
        }
        if (str.equals("horizontal")) {
            this.a.setHorizontalScrollBarEnabled(true);
        } else {
            if (!str.equals("vertical")) {
                return false;
            }
            this.a.setVerticalScrollBarEnabled(true);
        }
        return true;
    }

    public boolean T3(String str) {
        return str.equals("true") || str.equals("1") ? Y3(14, -1) : Y3(14, 0);
    }

    public boolean U0(String str) {
        View view = this.a;
        if (!(view instanceof Toolbar)) {
            return false;
        }
        ((Toolbar) view).setTitleTextColor(q(str));
        return true;
    }

    public boolean U1(String str) {
        this.a.setFadingEdgeLength((int) Double.parseDouble(str));
        return true;
    }

    public boolean U2(String str) {
        this.a.setScrollBarStyle(P(str));
        return true;
    }

    public boolean U3(String str) {
        return str.equals("true") || str.equals("1") ? Y3(13, -1) : Y3(13, 0);
    }

    public String V() {
        View view = this.a;
        if (view instanceof WebView) {
            return ((WebView) view).getUrl();
        }
        return null;
    }

    public boolean V0(String str) {
        boolean z = false;
        if (this.a instanceof Toolbar) {
            String[] a2 = c.b.a.a.q.a(str.trim());
            int length = a2.length;
            if (length > 0) {
                ((Toolbar) this.a).setTitleMarginStart(Z(a2[0].trim()));
            }
            z = true;
            if (length > 1) {
                ((Toolbar) this.a).setTitleMarginTop(Z(a2[1].trim()));
            }
            if (length > 2) {
                ((Toolbar) this.a).setTitleMarginEnd(Z(a2[2].trim()));
            }
            if (length > 3) {
                ((Toolbar) this.a).setTitleMarginBottom(Z(a2[3].trim()));
            }
        }
        return z;
    }

    public boolean V1(String str) {
        this.a.setFocusable(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean V2(String str) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setHorizontallyScrolling(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean V3(String str) {
        return str.equals("true") || str.equals("1") ? Y3(15, -1) : Y3(15, 0);
    }

    public int W() {
        return this.a.getVisibility();
    }

    public boolean W0(String str) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setAutoLinkMask(l(str));
        return true;
    }

    public boolean W1(String str) {
        this.a.setFocusableInTouchMode(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean W2(String str) {
        View view = this.a;
        if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            scrollView.smoothScrollTo((int) Double.parseDouble(str), scrollView.getScrollY());
            return true;
        }
        if (!(view instanceof HorizontalScrollView)) {
            return false;
        }
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) view;
        horizontalScrollView.smoothScrollTo((int) Double.parseDouble(str), horizontalScrollView.getScrollY());
        return true;
    }

    public boolean W3(int i2) {
        return Y3(0, i2);
    }

    public int X(String str) {
        if (str.equals("visible")) {
            return 0;
        }
        if (str.equals("invisible")) {
            return 4;
        }
        if (str.equals("gone")) {
            return 8;
        }
        if (str.matches("[0-9]+")) {
            return (int) Double.parseDouble(str);
        }
        return 0;
    }

    public boolean X0(Object obj) {
        if (this.b != null && (obj instanceof String)) {
            String obj2 = obj.toString();
            String lowerCase = obj2.toLowerCase();
            if (lowerCase.startsWith("http:") || lowerCase.startsWith("https:") || lowerCase.startsWith("ftp:")) {
                c.b.a.a.h.a(this.b, this.a, obj2, lowerCase, true);
                return true;
            }
        }
        if (obj != null) {
            Drawable bitmapDrawable = obj instanceof Drawable ? (Drawable) obj : obj instanceof Bitmap ? new BitmapDrawable((Bitmap) obj) : m(String.valueOf(obj), this.b);
            if (bitmapDrawable != null) {
                this.a.setBackgroundDrawable(bitmapDrawable);
                return true;
            }
        }
        this.a.setBackgroundDrawable(null);
        return true;
    }

    @SuppressLint({"NewApi"})
    public boolean X1(Object obj) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        Drawable bitmapDrawable = obj instanceof Drawable ? (Drawable) obj : obj instanceof Bitmap ? new BitmapDrawable((Resources) null, (Bitmap) obj) : m(String.valueOf(obj), this.b);
        if (bitmapDrawable == null) {
            this.a.setForeground(null);
            return true;
        }
        this.a.setForeground(bitmapDrawable);
        return true;
    }

    public boolean X2(String str) {
        View view = this.a;
        if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            scrollView.smoothScrollTo(scrollView.getScrollX(), (int) Double.parseDouble(str));
            return true;
        }
        if (!(view instanceof HorizontalScrollView)) {
            return false;
        }
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) view;
        horizontalScrollView.smoothScrollTo(horizontalScrollView.getScrollX(), (int) Double.parseDouble(str));
        return true;
    }

    public boolean X3(int i2) {
        return Y3(1, i2);
    }

    public int Y() {
        return this.a.getLayoutParams().width;
    }

    public boolean Y0(String str) {
        if (this.b != null && str != null) {
            String lowerCase = str.toLowerCase();
            if (lowerCase.startsWith("http:") || lowerCase.startsWith("https:") || lowerCase.startsWith("ftp:")) {
                c.b.a.a.h.a(this.b, this.a, str, lowerCase, true);
                return true;
            }
        }
        Drawable m = m(str, this.b);
        if (m == null) {
            this.a.setBackgroundDrawable(null);
        } else {
            this.a.setBackgroundDrawable(m);
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    public boolean Y1(String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        this.a.setForeground(m(str, this.b));
        return true;
    }

    public boolean Y2(String str) {
        View view = this.a;
        if (view instanceof AbsSpinner) {
            ((AbsSpinner) view).setSelection((int) Double.parseDouble(str), true);
        } else if (view instanceof EditText) {
            ((EditText) view).setSelection((int) Double.parseDouble(str));
        } else if (view instanceof ListView) {
            ((ListView) view).setSelection(c(str));
        } else {
            if (!(view instanceof RecyclerView)) {
                return false;
            }
            ((RecyclerView) view).smoothScrollToPosition((int) Double.parseDouble(str));
        }
        return true;
    }

    public boolean Y3(int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (!(layoutParams instanceof RelativeLayout.LayoutParams)) {
            return false;
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        if (i3 != -1 && i3 != 0) {
            i3 += this.f1935c;
        }
        layoutParams2.addRule(i2, i3);
        this.a.setLayoutParams(layoutParams2);
        return true;
    }

    public boolean Z0(int i2) {
        this.a.setBackgroundColor(i2);
        return true;
    }

    public boolean Z3(String str) {
        this.a.setVisibility(X(str));
        return true;
    }

    public boolean a(String str) {
        if (!(this.a instanceof Toolbar)) {
            return false;
        }
        String[] a2 = c.b.a.a.q.a(str.trim());
        int length = a2.length;
        int Z = length > 0 ? Z(a2[0].trim()) : 0;
        int Z2 = length > 1 ? Z(a2[1].trim()) : 0;
        int Z3 = length > 2 ? Z(a2[2].trim()) : 0;
        int Z4 = length > 3 ? Z(a2[3].trim()) : 0;
        ((Toolbar) this.a).setContentInsetsAbsolute(Z, Z3);
        if (Z2 != 0 || Z4 != 0) {
            ((Toolbar) this.a).setContentInsetsRelative(Z2, Z4);
        }
        return true;
    }

    public int a0() {
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (layoutParams instanceof WindowManager.LayoutParams) {
            return ((WindowManager.LayoutParams) layoutParams).x;
        }
        return -1;
    }

    @SuppressLint({"NewApi"})
    public boolean a1(String str) {
        if (Build.VERSION.SDK_INT < 21) {
            return true;
        }
        this.a.setBackgroundDrawable(new RippleDrawable(ColorStateList.valueOf(q(str)), this.a.getBackground(), this.a.getBackground()));
        return true;
    }

    public boolean a2(String str) {
        View view = this.a;
        if (view instanceof TextView) {
            ((TextView) view).setGravity(t(str));
            return true;
        }
        if (view instanceof GridView) {
            ((GridView) view).setGravity(t(str));
            return true;
        }
        if (view instanceof LinearLayout) {
            ((LinearLayout) view).setGravity(t(str));
            return true;
        }
        if (!(view instanceof RelativeLayout)) {
            return false;
        }
        ((RelativeLayout) view).setGravity(t(str));
        return true;
    }

    public boolean a3(float f, float f2, float f3, int i2) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setShadowLayer(f, f2, f3, i2);
        return true;
    }

    public boolean a4(String str) {
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        layoutParams.width = str.equals("-1") ? -1 : str.equals("-2") ? -2 : Z(str);
        this.a.setLayoutParams(layoutParams);
        return true;
    }

    public boolean b(String str, String str2) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.equals("id")) {
            this.a.setId((int) Double.parseDouble(str2));
            return true;
        }
        if (lowerCase.equals("text")) {
            return i0(str2.replace("\\\\", "\\U5c").replace("\\n", "\n").replace("\\U5c", "\\"));
        }
        if (lowerCase.equals("textcolor")) {
            return q3(q(str2));
        }
        if (lowerCase.equals("textcolorhighlight")) {
            return r3(q(str2));
        }
        if (lowerCase.equals("textcolorlink")) {
            return t3(q(str2));
        }
        if (lowerCase.equals("textscalex")) {
            return y3(Float.parseFloat(str2));
        }
        if (lowerCase.equals("textstyle")) {
            return A3(str2);
        }
        if (lowerCase.equals("typeface")) {
            return F3(str2);
        }
        if (lowerCase.equals("src")) {
            return k3(str2);
        }
        if (lowerCase.equals("gravity")) {
            return a2(str2);
        }
        if (lowerCase.equals("layout_gravity")) {
            return i2(str2);
        }
        if (lowerCase.equals("textsize")) {
            return z3(str2);
        }
        if (lowerCase.equals("background")) {
            return Y0(str2);
        }
        if (lowerCase.equals("backgroundcolor")) {
            return Z0(q(str2));
        }
        if (lowerCase.equals("width")) {
            return a4(str2);
        }
        if (lowerCase.equals("height")) {
            return b2(str2);
        }
        if (lowerCase.equals("adjustviewbounds")) {
            return j0(str2);
        }
        if (lowerCase.equals("maxheight")) {
            return z2(str2);
        }
        if (lowerCase.equals("minheight")) {
            return D2(str2);
        }
        if (lowerCase.equals("maxwidth")) {
            return C2(str2);
        }
        if (lowerCase.equals("minwidth")) {
            return F2(str2);
        }
        if (lowerCase.equals("layout_weight")) {
            return p2(str2);
        }
        if (lowerCase.equals("padding")) {
            return K2(str2);
        }
        if (lowerCase.equals("paddingleft")) {
            return M2(str2);
        }
        if (lowerCase.equals("paddingtop")) {
            return O2(str2);
        }
        if (lowerCase.equals("paddingright")) {
            return N2(str2);
        }
        if (lowerCase.equals("paddingbottom")) {
            return L2(str2);
        }
        if (lowerCase.equals("layout_margin")) {
            return j2(str2);
        }
        if (lowerCase.equals("layout_marginleft")) {
            return l2(str2);
        }
        if (lowerCase.equals("layout_margintop")) {
            return n2(str2);
        }
        if (lowerCase.equals("layout_marginright")) {
            return m2(str2);
        }
        if (lowerCase.equals("layout_marginbottom")) {
            return k2(str2);
        }
        if (lowerCase.equals("singleline")) {
            return h3(str2);
        }
        if (lowerCase.equals("scaletype")) {
            return S2(str2);
        }
        if (lowerCase.equals("hint")) {
            return c2(str2.replace("\\\\", "\\U5c").replace("\\n", "\n").replace("\\U5c", "\\"));
        }
        if (lowerCase.equals("textcolorhint")) {
            return s3(q(str2));
        }
        if (lowerCase.equals("inputtype")) {
            return e2(str2);
        }
        if (lowerCase.equals("autolink")) {
            return W0(str2);
        }
        if (lowerCase.equals("cursorvisible")) {
            return K1(str2);
        }
        if (lowerCase.equals("digits")) {
            return L1(str2);
        }
        if (lowerCase.equals("editable")) {
            return Q1(str2);
        }
        if (lowerCase.equals("ellipsize")) {
            return S1(str2);
        }
        if (lowerCase.equals("marqueerepeatlimit")) {
            return x2(str2);
        }
        if (lowerCase.equals("imeoptions")) {
            return d2(str2);
        }
        if (lowerCase.equals("linksclickable")) {
            return t2(str2);
        }
        if (lowerCase.equals("maxlength")) {
            return A2((int) Double.parseDouble(str2));
        }
        if (lowerCase.equals("lines")) {
            return q2((int) Double.parseDouble(str2));
        }
        if (lowerCase.equals("maxlines")) {
            return B2((int) Double.parseDouble(str2));
        }
        if (lowerCase.equals("minlines")) {
            return E2((int) Double.parseDouble(str2));
        }
        if (lowerCase.equals("linespacingextra")) {
            return r2(Float.parseFloat(str2));
        }
        if (lowerCase.equals("linespacingmultiplier")) {
            return s2(Float.parseFloat(str2));
        }
        if (lowerCase.equals("scrollhorizontally")) {
            return V2(str2);
        }
        if (lowerCase.equals("shadowradius")) {
            return e3(Float.parseFloat(str2));
        }
        if (lowerCase.equals("shadowcolor")) {
            return b3(q(str2));
        }
        if (lowerCase.equals("shadowdx")) {
            return c3(Float.parseFloat(str2));
        }
        if (lowerCase.equals("shadowdy")) {
            return d3(Float.parseFloat(str2));
        }
        if (lowerCase.equals("orientation")) {
            return I2(str2);
        }
        if (lowerCase.equals("ut_above")) {
            return I3((int) Double.parseDouble(str2));
        }
        if (lowerCase.equals("ut_below")) {
            return S3((int) Double.parseDouble(str2));
        }
        if (lowerCase.equals("ut_toleftof")) {
            return W3((int) Double.parseDouble(str2));
        }
        if (lowerCase.equals("ut_torightof")) {
            return X3((int) Double.parseDouble(str2));
        }
        if (lowerCase.equals("ut_alignbaseline")) {
            return J3((int) Double.parseDouble(str2));
        }
        if (lowerCase.equals("ut_aligntop")) {
            return R3((int) Double.parseDouble(str2));
        }
        if (lowerCase.equals("ut_alignbottom")) {
            return K3((int) Double.parseDouble(str2));
        }
        if (lowerCase.equals("ut_alignleft")) {
            return L3((int) Double.parseDouble(str2));
        }
        if (lowerCase.equals("ut_alignright")) {
            return Q3((int) Double.parseDouble(str2));
        }
        if (lowerCase.equals("ut_alignparenttop")) {
            return P3(str2);
        }
        if (lowerCase.equals("ut_alignparentbottom")) {
            return M3(str2);
        }
        if (lowerCase.equals("ut_alignparentleft")) {
            return N3(str2);
        }
        if (lowerCase.equals("ut_alignparentright")) {
            return O3(str2);
        }
        if (lowerCase.equals("ut_centerhorizontal")) {
            return T3(str2);
        }
        if (lowerCase.equals("ut_centervertical")) {
            return V3(str2);
        }
        if (lowerCase.equals("ut_centerinparent")) {
            return U3(str2);
        }
        if (lowerCase.equals("visibility")) {
            return Z3(str2);
        }
        if (lowerCase.equals("checked")) {
            return c1(str2);
        }
        if (lowerCase.equals("url")) {
            return G3(str2);
        }
        if (lowerCase.equals("selection")) {
            return Y2(str2);
        }
        if (lowerCase.equals("rating")) {
            return Q2(str2);
        }
        if (lowerCase.equals("numstars")) {
            return G2(str2);
        }
        if (lowerCase.equals("isindicator")) {
            return f2(str2);
        }
        if (lowerCase.equals("max")) {
            return y2(str2);
        }
        if (lowerCase.equals("progress")) {
            return P2(str2);
        }
        if (lowerCase.equals("style")) {
            return n3(str2);
        }
        if (lowerCase.equals("columnwidth")) {
            return i1(str2);
        }
        if (lowerCase.equals("clickable")) {
            return d1(str2);
        }
        if (lowerCase.equals("longclickable")) {
            return w2(str2);
        }
        if (lowerCase.equals("fadingedge")) {
            return T1(str2);
        }
        if (lowerCase.equals("fadingedgelength")) {
            return U1(str2);
        }
        if (lowerCase.equals("focusable")) {
            return V1(str2);
        }
        if (lowerCase.equals("focusableintouchmode")) {
            return W1(str2);
        }
        if (lowerCase.equals("scrollx")) {
            return W2(str2);
        }
        if (lowerCase.equals("scrolly")) {
            return X2(str2);
        }
        if (lowerCase.equals("scrollbarstyle")) {
            return U2(str2);
        }
        if (lowerCase.equals("scrollbars")) {
            return T2(str2);
        }
        if (lowerCase.equals("overscrollmode")) {
            return J2(str2);
        }
        if (lowerCase.equals("divider")) {
            return N1(str2);
        }
        if (lowerCase.equals("dividerheight")) {
            return O1(str2);
        }
        if (lowerCase.equals("cachecolorhint")) {
            return b1(str2);
        }
        if (lowerCase.equals("listselector")) {
            return v2(str2);
        }
        if (lowerCase.equals("collapsecolumns")) {
            return g1(str2);
        }
        if (lowerCase.equals("stretchcolumns")) {
            return m3(str2);
        }
        if (lowerCase.equals("shrinkcolumns")) {
            return g3(str2);
        }
        if (lowerCase.equals("layout_column")) {
            return h2(str2);
        }
        if (lowerCase.equals("layout_span")) {
            return o2(str2);
        }
        if (lowerCase.equals("textisselectable")) {
            return v3(str2);
        }
        if (!lowerCase.equals("backgroundripple")) {
            return lowerCase.equals("textcursordrawable") ? u3(str2) : lowerCase.equals("pagelimit") ? d0(str2) : lowerCase.equals("app_layout_behavior") ? E0(str2) : lowerCase.equals("app_contentscrim") ? u0(str2) : lowerCase.equals("app_layout_scrollflags") ? G0(str2) : lowerCase.equals("app_layout_collapsemode") ? F0(str2) : lowerCase.equals("app_popuptheme") ? H0(str2) : lowerCase.equals("app_title") ? T0(str2) : lowerCase.equals("app_subtitle") ? I0(str2) : lowerCase.equals("app_titlecolor") ? U0(str2) : lowerCase.equals("app_subtitlecolor") ? J0(str2) : lowerCase.equals("app_titlemargin") ? V0(str2) : lowerCase.equals("ct_left_toleft") ? y1((int) Double.parseDouble(str2)) : lowerCase.equals("ct_left_toright") ? z1((int) Double.parseDouble(str2)) : lowerCase.equals("ct_right_toleft") ? A1((int) Double.parseDouble(str2)) : lowerCase.equals("ct_right_toright") ? B1((int) Double.parseDouble(str2)) : lowerCase.equals("ct_top_totop") ? F1((int) Double.parseDouble(str2)) : lowerCase.equals("ct_top_tobottom") ? E1((int) Double.parseDouble(str2)) : lowerCase.equals("ct_bottom_totop") ? l1((int) Double.parseDouble(str2)) : lowerCase.equals("ct_bottom_tobottom") ? k1((int) Double.parseDouble(str2)) : lowerCase.equals("ct_baseline_tobaseline") ? j1((int) Double.parseDouble(str2)) : lowerCase.equals("ct_start_toend") ? C1((int) Double.parseDouble(str2)) : lowerCase.equals("ct_start_tostart") ? D1((int) Double.parseDouble(str2)) : lowerCase.equals("ct_end_tostart") ? o1((int) Double.parseDouble(str2)) : lowerCase.equals("ct_end_toend") ? n1((int) Double.parseDouble(str2)) : lowerCase.equals("ct_gonemarginstart") ? t1(str2) : lowerCase.equals("ct_gonemarginend") ? q1(str2) : lowerCase.equals("ct_gonemarginleft") ? r1(str2) : lowerCase.equals("ct_gonemargintop") ? u1(str2) : lowerCase.equals("ct_gonemarginright") ? s1(str2) : lowerCase.equals("ct_gonemarginbottom") ? p1(str2) : lowerCase.equals("ct_horizontal_bias") ? v1(str2) : lowerCase.equals("ct_vertical_bias") ? G1(str2) : lowerCase.equals("ct_dimensionratio") ? m1(str2) : lowerCase.equals("ct_horizontal_chainstyle") ? w1(str2) : lowerCase.equals("ct_vertical_chainstyle") ? H1(str2) : lowerCase.equals("ct_horizontal_weight") ? x1(str2) : lowerCase.equals("ct_vertical_weight") ? I1(str2) : lowerCase.equals("app_tablist") ? Q0(str2) : lowerCase.equals("app_tabindicatorcolor") ? N0(str2) : lowerCase.equals("app_tabindicatorheight") ? O0(str2) : lowerCase.equals("app_tabtextcolor") ? S0(str2) : lowerCase.equals("app_tabmode") ? R0((int) Double.parseDouble(str2)) : lowerCase.equals("app_tabgravity") ? M0((int) Double.parseDouble(str2)) : lowerCase.equals("app_tabindicatorwidth") ? P0(str2) : lowerCase.equals("app_tabadd") ? K0(str2) : lowerCase.equals("app_tabdel") ? L0(str2) : lowerCase.equals("app_layout_anchor") ? C0((int) Double.parseDouble(str2)) : lowerCase.equals("app_layout_anchorgravity") ? D0(str2) : lowerCase.equals("colorschemecolors") ? h1(str2) : lowerCase.equals("refreshing") ? R2(str2) : lowerCase.equals("itemdecoration") ? g2(str2) : lowerCase.equals("foreground") ? Y1(str2) : lowerCase.equals("tabselect") ? p3((int) Double.parseDouble(str2)) : lowerCase.equals("elevation") ? R1(str2) : lowerCase.equals("app_counterenabled") ? v0(str2) : lowerCase.equals("app_countermaxlength") ? w0((int) Double.parseDouble(str2)) : lowerCase.equals("app_errorenabled") ? y0(str2) : lowerCase.equals("app_error") ? x0(str2) : lowerCase.equals("showtext") ? f3(str2) : lowerCase.equals("splittrack") ? i3(str2) : lowerCase.equals("switchminwidth") ? o3(str2) : lowerCase.equals("thumbtint") ? B3(str2) : lowerCase.equals("thumbtintmode") ? C3(str2) : lowerCase.equals("tracktint") ? D3(str2) : lowerCase.equals("tracktintmode") ? E3(str2) : lowerCase.equals("textoff") ? w3(str2) : lowerCase.equals("texton") ? x3(str2) : lowerCase.equals("app_cardbackgroundcolor") ? k0(str2) : lowerCase.equals("app_cardcornerradius") ? l0(str2) : lowerCase.equals("app_cardelevation") ? m0(str2) : lowerCase.equals("app_cardmaxelevation") ? n0(str2) : lowerCase.equals("app_cardusecompatpadding") ? p0(str2) : lowerCase.equals("app_cardpreventcorneroverlap") ? o0(str2) : lowerCase.equals("app_contentpadding") ? s0(str2) : lowerCase.equals("app_expandedtitlecolor") ? z0(str2) : lowerCase.equals("app_expandedtitlegravity") ? A0(str2) : lowerCase.equals("app_expandedtitlemargin") ? B0(str2) : lowerCase.equals("app_collapsedtitletextcolor") ? r0(str2) : lowerCase.equals("app_collapsedtitlegravity") ? q0(str2) : lowerCase.equals("clipchildren") ? e1(str2) : lowerCase.equals("app_contentInset") ? a(str2) : lowerCase.equals("ps");
        }
        a1(str2);
        return true;
    }

    public int b0() {
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (layoutParams instanceof WindowManager.LayoutParams) {
            return ((WindowManager.LayoutParams) layoutParams).y;
        }
        return -1;
    }

    public boolean b1(String str) {
        View view = this.a;
        if (!(view instanceof ListView)) {
            return false;
        }
        ((ListView) view).setCacheColorHint(Z(str));
        return true;
    }

    public boolean b2(String str) {
        int Z;
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (!str.equals("-1")) {
            if (!str.equals("-2")) {
                if (str.equals("-3")) {
                    View view = this.a;
                    if ((view instanceof Toolbar) || ((view.getTag() instanceof Object[]) && ((Object[]) this.a.getTag())[0].equals("Toolbar"))) {
                        Z = c.b.a.a.p.s(this.b);
                    }
                } else {
                    Z = Z(str);
                }
            }
            layoutParams.height = -2;
            this.a.setLayoutParams(layoutParams);
            return true;
        }
        Z = -1;
        layoutParams.height = Z;
        this.a.setLayoutParams(layoutParams);
        return true;
    }

    @SuppressLint({"NewApi"})
    public boolean b3(int i2) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 16) {
            return true;
        }
        ((TextView) view).setShadowLayer(((TextView) view).getShadowRadius(), ((TextView) this.a).getShadowDx(), ((TextView) this.a).getShadowDy(), i2);
        return true;
    }

    public boolean b4(Object obj) {
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (!(layoutParams instanceof WindowManager.LayoutParams)) {
            return false;
        }
        ((WindowManager.LayoutParams) layoutParams).x = (int) Float.parseFloat(String.valueOf(obj));
        return true;
    }

    public boolean c1(String str) {
        View view = this.a;
        if (!(view instanceof CompoundButton)) {
            return false;
        }
        ((CompoundButton) view).setChecked(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean c2(String str) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setHint(str);
        return true;
    }

    @SuppressLint({"NewApi"})
    public boolean c3(float f) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 16) {
            return true;
        }
        ((TextView) view).setShadowLayer(((TextView) view).getShadowRadius(), f, ((TextView) this.a).getShadowDy(), ((TextView) this.a).getShadowColor());
        return true;
    }

    public boolean c4(Object obj) {
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (!(layoutParams instanceof WindowManager.LayoutParams)) {
            return false;
        }
        ((WindowManager.LayoutParams) layoutParams).y = (int) Float.parseFloat(String.valueOf(obj));
        return true;
    }

    public Object d() {
        View view = this.a;
        return Integer.valueOf(view instanceof ViewPager ? ((ViewPager) view).getOffscreenPageLimit() : view instanceof VerticalViewPager ? ((VerticalViewPager) view).getOffscreenPageLimit() : -1);
    }

    public boolean d0(String str) {
        View view = this.a;
        if (view instanceof ViewPager) {
            ((ViewPager) view).setOffscreenPageLimit((int) Double.parseDouble(str));
            return true;
        }
        if (!(view instanceof VerticalViewPager)) {
            return false;
        }
        ((VerticalViewPager) view).setOffscreenPageLimit((int) Double.parseDouble(str));
        return true;
    }

    public boolean d1(String str) {
        this.a.setClickable(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean d2(String str) {
        View view = this.a;
        if (!(view instanceof EditText)) {
            return false;
        }
        ((EditText) view).setImeOptions(y(str));
        return true;
    }

    @TargetApi(16)
    public boolean d3(float f) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 16) {
            return true;
        }
        ((TextView) view).setShadowLayer(((TextView) view).getShadowRadius(), ((TextView) this.a).getShadowDx(), f, ((TextView) this.a).getShadowColor());
        return true;
    }

    public Object e(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.equals("text")) {
            return k();
        }
        if (lowerCase.equals("background")) {
            return this.a.getBackground();
        }
        if (lowerCase.equals("width")) {
            return Integer.valueOf(Y());
        }
        if (lowerCase.equals("height")) {
            return Integer.valueOf(v());
        }
        if (lowerCase.equals("x")) {
            return Integer.valueOf(a0());
        }
        if (lowerCase.equals("y")) {
            return Integer.valueOf(b0());
        }
        if (lowerCase.equals("paddingleft")) {
            return Integer.valueOf(L());
        }
        if (lowerCase.equals("paddingtop")) {
            return Integer.valueOf(N());
        }
        if (lowerCase.equals("paddingright")) {
            return Integer.valueOf(M());
        }
        if (lowerCase.equals("paddingbottom")) {
            return Integer.valueOf(K());
        }
        if (lowerCase.equals("layout_marginleft")) {
            return Integer.valueOf(E());
        }
        if (lowerCase.equals("layout_margintop")) {
            return Integer.valueOf(G());
        }
        if (lowerCase.equals("layout_marginright")) {
            return Integer.valueOf(F());
        }
        if (lowerCase.equals("layout_marginbottom")) {
            return Integer.valueOf(D());
        }
        if (lowerCase.equals("hint")) {
            return w();
        }
        if (lowerCase.equals("imeoptions")) {
            return Integer.valueOf(x());
        }
        if (lowerCase.equals("visibility")) {
            return Integer.valueOf(W());
        }
        if (lowerCase.equals("checked")) {
            return Boolean.valueOf(o());
        }
        if (lowerCase.equals("url")) {
            return V();
        }
        if (lowerCase.equals("title")) {
            View view = this.a;
            if (view instanceof WebView) {
                return ((WebView) view).getTitle();
            }
        }
        if (lowerCase.equals("lastvisibleposition")) {
            View view2 = this.a;
            if (view2 instanceof RecyclerView) {
                return Integer.valueOf(((LinearLayoutManager) ((RecyclerView) view2).getLayoutManager()).findFirstVisibleItemPosition());
            }
            if (view2 instanceof AbsListView) {
                return Integer.valueOf(((AbsListView) view2).getLastVisiblePosition());
            }
            return null;
        }
        if (lowerCase.equals("count")) {
            View view3 = this.a;
            if (view3 instanceof RecyclerView) {
                return Integer.valueOf(((LinearLayoutManager) ((RecyclerView) view3).getLayoutManager()).getItemCount());
            }
            if (view3 instanceof AbsListView) {
                return Integer.valueOf(((AbsListView) view3).getCount());
            }
            return null;
        }
        if (lowerCase.equals("selecteditem")) {
            View view4 = this.a;
            if (view4 instanceof AdapterView) {
                return ((AdapterView) view4).getSelectedItem().toString();
            }
        }
        if (lowerCase.equals("rating")) {
            View view5 = this.a;
            if (view5 instanceof RatingBar) {
                return Float.valueOf(((RatingBar) view5).getRating());
            }
        }
        if (lowerCase.equals("progress")) {
            View view6 = this.a;
            if (view6 instanceof ProgressBar) {
                return Integer.valueOf(((ProgressBar) view6).getProgress());
            }
        }
        if (lowerCase.equals("date")) {
            View view7 = this.a;
            if (view7 instanceof DatePicker) {
                DatePicker datePicker = (DatePicker) view7;
                return datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getDayOfMonth();
            }
        }
        if (lowerCase.equals("time")) {
            View view8 = this.a;
            if (view8 instanceof TimePicker) {
                TimePicker timePicker = (TimePicker) view8;
                return timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
            }
        }
        if (lowerCase.equals("currentitem")) {
            View view9 = this.a;
            if (view9 instanceof ViewPager) {
                return Integer.valueOf(((ViewPager) view9).getCurrentItem());
            }
            if (view9 instanceof VerticalViewPager) {
                return Integer.valueOf(((VerticalViewPager) view9).getCurrentItem());
            }
            return null;
        }
        if (lowerCase.equals("selectionstart")) {
            View view10 = this.a;
            if (view10 instanceof EditText) {
                return Integer.valueOf(((EditText) view10).getSelectionStart());
            }
        }
        if (lowerCase.equals("selectionend")) {
            View view11 = this.a;
            if (view11 instanceof EditText) {
                return Integer.valueOf(((EditText) view11).getSelectionEnd());
            }
        }
        if (lowerCase.equals("cangoback")) {
            View view12 = this.a;
            if (view12 instanceof WebView) {
                return Boolean.valueOf(((WebView) view12).canGoBack());
            }
        }
        if (lowerCase.equals("cangoforward")) {
            View view13 = this.a;
            if (view13 instanceof WebView) {
                return Boolean.valueOf(((WebView) view13).canGoForward());
            }
        }
        if (lowerCase.equals("shrinkcolumnsall")) {
            View view14 = this.a;
            if (view14 instanceof TableLayout) {
                return Boolean.valueOf(((TableLayout) view14).isShrinkAllColumns());
            }
        }
        if (lowerCase.equals("stretchcolumnsall")) {
            View view15 = this.a;
            if (view15 instanceof TableLayout) {
                return Boolean.valueOf(((TableLayout) view15).isStretchAllColumns());
            }
        }
        if (lowerCase.equals("pagelimit")) {
            return d();
        }
        if (lowerCase.equals("app_title")) {
            return String.valueOf(((Toolbar) this.a).getTitle());
        }
        if (lowerCase.equals("app_subtitle")) {
            return String.valueOf(((Toolbar) this.a).getSubtitle());
        }
        if (lowerCase.equals("refreshing")) {
            return Boolean.valueOf(((SwipeRefreshLayout) this.a).isRefreshing());
        }
        return null;
    }

    public boolean e0(String str, Object obj) {
        String lowerCase = str.toLowerCase();
        return lowerCase.equals("text") ? i0(String.valueOf(obj)) : lowerCase.equals("hint") ? c2(String.valueOf(obj)) : lowerCase.equals("background") ? X0(obj) : lowerCase.equals("src") ? j3(obj) : lowerCase.equals("currentitem") ? J1(obj) : lowerCase.equals("closedrawer") ? f1(obj) : lowerCase.equals("opendrawer") ? H2(obj) : lowerCase.equals("drawerlockmode") ? P1(obj) : lowerCase.equals("gobackorforward") ? Z1(obj) : lowerCase.equals("dh") ? l3(obj) : lowerCase.equals("x") ? b4(obj) : lowerCase.equals("y") ? c4(obj) : lowerCase.equals("backgroundripple") ? a1(String.valueOf(obj)) : lowerCase.equals("foreground") ? X1(obj) : lowerCase.equals("app_contentscrim") ? t0(obj) : lowerCase.equals("listselector") ? u2(obj) : lowerCase.equals("divider") ? M1(obj) : b(lowerCase, String.valueOf(obj));
    }

    public boolean e1(String str) {
        View view = this.a;
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ((ViewGroup) view).setClipChildren(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean e2(String str) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setInputType(A(str));
        return true;
    }

    @SuppressLint({"NewApi"})
    public boolean e3(float f) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 16) {
            return true;
        }
        ((TextView) view).setShadowLayer(f, ((TextView) view).getShadowDx(), ((TextView) this.a).getShadowDy(), ((TextView) this.a).getShadowColor());
        return true;
    }

    public Object f(String str, Object obj) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.equals("isdraweropen")) {
            return B(obj);
        }
        if (lowerCase.equals("collapsecolumns")) {
            return p(obj);
        }
        if (lowerCase.equals("stretchcolumns")) {
            return R(obj);
        }
        if (lowerCase.equals("shrinkcolumns")) {
            return Q(obj);
        }
        return null;
    }

    public boolean f0(String str, String str2, String str3) {
        if (str.toLowerCase().equals("selection")) {
            return Z2(str2, str3);
        }
        return false;
    }

    public boolean f1(Object obj) {
        View view = this.a;
        if (!(view instanceof DrawerLayout)) {
            return false;
        }
        ((DrawerLayout) view).closeDrawer(t(obj.toString()));
        return true;
    }

    public boolean f2(String str) {
        View view = this.a;
        if (!(view instanceof RatingBar)) {
            return false;
        }
        ((RatingBar) view).setIsIndicator(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean f3(String str) {
        View view = this.a;
        if (!(view instanceof SwitchCompat)) {
            return false;
        }
        ((SwitchCompat) view).setShowText(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean g0(String str, String str2, String str3, String str4) {
        if (str.toLowerCase().equals("url")) {
            return H3(str2, str3, str4);
        }
        return false;
    }

    public boolean g1(String str) {
        View view = this.a;
        if (!(view instanceof TableLayout)) {
            return false;
        }
        ((TableLayout) view).setColumnCollapsed((int) Double.parseDouble(String.valueOf(str)), true);
        return true;
    }

    public boolean g2(String str) {
        if (!(this.a instanceof RecyclerView)) {
            return false;
        }
        String[] a2 = c.b.a.a.q.a(str.trim());
        int length = a2.length;
        ((RecyclerView) this.a).addItemDecoration(new c(this, length > 0 ? Z(a2[0].trim()) : 0, length > 1 ? Z(a2[1].trim()) : 0, length > 2 ? Z(a2[2].trim()) : 0, length > 3 ? Z(a2[3].trim()) : 0));
        return true;
    }

    public boolean g3(String str) {
        if (!(this.a instanceof TableLayout)) {
            return false;
        }
        if (str.equals("true")) {
            ((TableLayout) this.a).setShrinkAllColumns(true);
        } else if (str.equals("false")) {
            ((TableLayout) this.a).setShrinkAllColumns(false);
        } else {
            ((TableLayout) this.a).setColumnShrinkable((int) Double.parseDouble(String.valueOf(str)), true);
        }
        return true;
    }

    public boolean h0(String str, String str2, String str3, String str4, String str5) {
        if (str.toLowerCase().equals("shadow")) {
            return a3(Float.parseFloat(str2), Float.parseFloat(str3), Float.parseFloat(str4), q(str5));
        }
        return false;
    }

    public boolean h1(String str) {
        if (!(this.a instanceof SwipeRefreshLayout)) {
            return false;
        }
        String[] b2 = c.b.a.a.q.b(str, '|');
        int[] iArr = new int[b2.length];
        for (int i2 = 0; i2 < b2.length; i2++) {
            iArr[i2] = q(b2[i2]);
        }
        ((SwipeRefreshLayout) this.a).setColorSchemeColors(iArr);
        return true;
    }

    public boolean h2(String str) {
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (!(layoutParams instanceof TableRow.LayoutParams)) {
            return false;
        }
        TableRow.LayoutParams layoutParams2 = (TableRow.LayoutParams) layoutParams;
        layoutParams2.column = (int) Double.parseDouble(String.valueOf(str));
        this.a.setLayoutParams(layoutParams2);
        return true;
    }

    public boolean h3(String str) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setSingleLine(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean i0(String str) {
        CharSequence charSequence;
        TextView textView;
        if (!(this.a instanceof TextView)) {
            return false;
        }
        if (str.startsWith("(html)")) {
            textView = (TextView) this.a;
            charSequence = Html.fromHtml(str.substring(6));
        } else {
            textView = (TextView) this.a;
            charSequence = str;
        }
        textView.setText(charSequence);
        return true;
    }

    public boolean i1(String str) {
        View view = this.a;
        if (!(view instanceof GridView)) {
            return false;
        }
        ((GridView) view).setColumnWidth(Z(str));
        return true;
    }

    public boolean i2(String str) {
        ViewGroup.LayoutParams C = C(this.a.getLayoutParams(), str);
        if (C == null) {
            return false;
        }
        this.a.setLayoutParams(C);
        return true;
    }

    public boolean i3(String str) {
        View view = this.a;
        if (!(view instanceof SwitchCompat)) {
            return false;
        }
        ((SwitchCompat) view).setSplitTrack(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean j0(String str) {
        View view = this.a;
        if (!(view instanceof ImageView)) {
            return false;
        }
        ((ImageView) view).setAdjustViewBounds(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean j1(int i2) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).baselineToBaseline = i2 != 0 ? this.f1935c + i2 : 0;
        return true;
    }

    public boolean j2(String str) {
        int Z = Z(str);
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return false;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(Z, Z, Z, Z);
        this.a.setLayoutParams(layoutParams);
        return true;
    }

    public boolean j3(Object obj) {
        ImageView imageView;
        Bitmap c2;
        ImageView imageView2;
        View view = this.a;
        if (view instanceof GifImageView) {
            if (!(obj instanceof Drawable)) {
                return k3(obj.toString());
            }
            imageView2 = (GifImageView) view;
        } else {
            if (!(view instanceof ImageView)) {
                return false;
            }
            if (this.b != null && (obj instanceof String)) {
                String obj2 = obj.toString();
                String lowerCase = obj2.toLowerCase();
                if (lowerCase.startsWith("http:") || lowerCase.startsWith("https:") || lowerCase.startsWith("ftp:")) {
                    c.b.a.a.h.a(this.b, this.a, obj2, lowerCase, false);
                    return true;
                }
            }
            if (!(obj instanceof Drawable)) {
                if (!(obj instanceof Bitmap)) {
                    if (obj == null) {
                        ((ImageView) this.a).setImageBitmap(null);
                    } else {
                        String valueOf = String.valueOf(obj);
                        if (valueOf.startsWith("@")) {
                            imageView = (ImageView) this.a;
                            Context context = this.b;
                            c2 = c.b.a.a.i.b(context, c.b.a.a.d.p(context, valueOf));
                        } else if (valueOf.startsWith("%") || valueOf.startsWith("$") || valueOf.startsWith("/")) {
                            imageView = (ImageView) this.a;
                            c2 = c.b.a.a.i.c(c.b.a.a.d.p(this.b, valueOf));
                        } else {
                            if (!valueOf.matches("[0-9]+")) {
                                return false;
                            }
                            ((ImageView) this.a).setImageResource((int) Double.parseDouble(valueOf));
                        }
                    }
                    return true;
                }
                imageView = (ImageView) this.a;
                c2 = (Bitmap) obj;
                imageView.setImageBitmap(c2);
                return true;
            }
            imageView2 = (ImageView) this.a;
        }
        imageView2.setImageDrawable((Drawable) obj);
        return true;
    }

    public Object k() {
        View view = this.a;
        if (view instanceof TextView) {
            return ((TextView) view).getText().toString();
        }
        return null;
    }

    public boolean k0(String str) {
        View view = this.a;
        if (!(view instanceof CardView)) {
            return false;
        }
        ((CardView) view).setCardBackgroundColor(q(str));
        return true;
    }

    public boolean k1(int i2) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).bottomToBottom = i2 != 0 ? this.f1935c + i2 : 0;
        return true;
    }

    public boolean k2(String str) {
        int Z = Z(str);
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return false;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = Z;
        this.a.setLayoutParams(layoutParams);
        return true;
    }

    public boolean k3(String str) {
        ImageView imageView;
        Bitmap c2;
        View view = this.a;
        if (view instanceof GifImageView) {
            if (str.startsWith("@")) {
                try {
                    pl.droidsonroids.gif.c cVar = new pl.droidsonroids.gif.c(this.b.getAssets(), c.b.a.a.d.p(this.b, str));
                    ((GifImageView) this.a).setImageDrawable(cVar);
                    cVar.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            } else if (str.startsWith("%") || str.startsWith("$") || str.startsWith("/")) {
                try {
                    pl.droidsonroids.gif.c cVar2 = new pl.droidsonroids.gif.c(c.b.a.a.d.p(this.b, str));
                    ((GifImageView) this.a).setImageDrawable(cVar2);
                    cVar2.start();
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return false;
                }
            } else if (str.matches("[0-9]+")) {
                ((GifImageView) this.a).setImageResource((int) Double.parseDouble(str));
            }
            return true;
        }
        if (!(view instanceof ImageView)) {
            return false;
        }
        if (this.b != null && str != null) {
            String lowerCase = str.toLowerCase();
            if (lowerCase.startsWith("http:") || lowerCase.startsWith("https:") || lowerCase.startsWith("ftp:")) {
                c.b.a.a.h.a(this.b, this.a, str, lowerCase, false);
                return true;
            }
        }
        if (str.startsWith("@")) {
            imageView = (ImageView) this.a;
            Context context = this.b;
            c2 = c.b.a.a.i.b(context, c.b.a.a.d.p(context, str));
        } else {
            if (!str.startsWith("%") && !str.startsWith("$") && !str.startsWith("/")) {
                if (str.equals("null")) {
                    ((ImageView) this.a).setImageBitmap(null);
                } else {
                    if (!str.matches("[0-9]+")) {
                        return false;
                    }
                    ((ImageView) this.a).setImageResource((int) Double.parseDouble(str));
                }
                return true;
            }
            imageView = (ImageView) this.a;
            c2 = c.b.a.a.i.c(c.b.a.a.d.p(this.b, str));
        }
        imageView.setImageBitmap(c2);
        return true;
    }

    public boolean l0(String str) {
        View view = this.a;
        if (!(view instanceof CardView)) {
            return false;
        }
        ((CardView) view).setRadius(Z(str));
        return true;
    }

    public boolean l1(int i2) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).bottomToTop = i2 != 0 ? this.f1935c + i2 : 0;
        return true;
    }

    public boolean l2(String str) {
        int Z = Z(str);
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return false;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = Z;
        this.a.setLayoutParams(layoutParams);
        return true;
    }

    public boolean l3(Object obj) {
        this.a.startAnimation((Animation) obj);
        return true;
    }

    public boolean m0(String str) {
        View view = this.a;
        if (!(view instanceof CardView)) {
            return false;
        }
        ((CardView) view).setCardElevation(Z(str));
        return true;
    }

    public boolean m1(String str) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).dimensionRatio = str;
        return true;
    }

    public boolean m2(String str) {
        int Z = Z(str);
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return false;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = Z;
        this.a.setLayoutParams(layoutParams);
        return true;
    }

    public boolean m3(String str) {
        if (!(this.a instanceof TableLayout)) {
            return false;
        }
        if (str.equals("true")) {
            ((TableLayout) this.a).setStretchAllColumns(true);
        } else if (str.equals("false")) {
            ((TableLayout) this.a).setStretchAllColumns(false);
        } else {
            ((TableLayout) this.a).setColumnStretchable((int) Double.parseDouble(String.valueOf(str)), true);
        }
        return true;
    }

    public boolean n0(String str) {
        View view = this.a;
        if (!(view instanceof CardView)) {
            return false;
        }
        ((CardView) view).setMaxCardElevation(Z(str));
        return true;
    }

    public boolean n1(int i2) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).endToEnd = i2 != 0 ? this.f1935c + i2 : 0;
        return true;
    }

    public boolean n2(String str) {
        int Z = Z(str);
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return false;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = Z;
        this.a.setLayoutParams(layoutParams);
        return true;
    }

    public boolean n3(String str) {
        return this.a instanceof ProgressBar;
    }

    public boolean o() {
        View view = this.a;
        if (view instanceof CompoundButton) {
            return ((CompoundButton) view).isChecked();
        }
        return false;
    }

    public boolean o0(String str) {
        View view = this.a;
        if (!(view instanceof CardView)) {
            return false;
        }
        ((CardView) view).setPreventCornerOverlap(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean o1(int i2) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).endToStart = i2 != 0 ? this.f1935c + i2 : 0;
        return true;
    }

    public boolean o2(String str) {
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (!(layoutParams instanceof TableRow.LayoutParams)) {
            return false;
        }
        TableRow.LayoutParams layoutParams2 = (TableRow.LayoutParams) layoutParams;
        layoutParams2.span = (int) Double.parseDouble(String.valueOf(str));
        this.a.setLayoutParams(layoutParams2);
        return true;
    }

    public boolean o3(String str) {
        View view = this.a;
        if (!(view instanceof SwitchCompat)) {
            return false;
        }
        ((SwitchCompat) view).setSwitchMinWidth(Z(str));
        return true;
    }

    public Object p(Object obj) {
        View view = this.a;
        if (view instanceof TableLayout) {
            return Boolean.valueOf(((TableLayout) view).isColumnCollapsed((int) Double.parseDouble(String.valueOf(obj))));
        }
        return null;
    }

    public boolean p0(String str) {
        View view = this.a;
        if (!(view instanceof CardView)) {
            return false;
        }
        ((CardView) view).setUseCompatPadding(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean p1(String str) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).goneBottomMargin = Z(str);
        return true;
    }

    public boolean p2(String str) {
        ViewGroup.LayoutParams H = H(this.a.getLayoutParams(), str);
        if (H == null) {
            return false;
        }
        this.a.setLayoutParams(H);
        return true;
    }

    public boolean p3(int i2) {
        View view = this.a;
        if (!(view instanceof TabLayout)) {
            return false;
        }
        TabLayout tabLayout = (TabLayout) view;
        if (tabLayout.getTabCount() <= 0) {
            return true;
        }
        tabLayout.getTabAt(i2).select();
        return true;
    }

    public boolean q0(String str) {
        View view = this.a;
        if (!(view instanceof CollapsingToolbarLayout)) {
            return false;
        }
        ((CollapsingToolbarLayout) view).setCollapsedTitleGravity(t(str));
        return true;
    }

    public boolean q1(String str) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).goneEndMargin = Z(str);
        return true;
    }

    public boolean q2(int i2) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setLines(i2);
        return true;
    }

    public boolean q3(int i2) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setTextColor(i2);
        return true;
    }

    public boolean r0(String str) {
        View view = this.a;
        if (!(view instanceof CollapsingToolbarLayout)) {
            return false;
        }
        ((CollapsingToolbarLayout) view).setCollapsedTitleTextColor(q(str));
        return true;
    }

    public boolean r1(String str) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).goneLeftMargin = Z(str);
        return true;
    }

    @TargetApi(16)
    public boolean r2(float f) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 16) {
            return true;
        }
        ((TextView) view).setLineSpacing(f, ((TextView) view).getLineSpacingMultiplier());
        return true;
    }

    public boolean r3(int i2) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setHighlightColor(i2);
        return true;
    }

    public boolean s0(String str) {
        if (!(this.a instanceof CardView)) {
            return false;
        }
        String[] a2 = c.b.a.a.q.a(str.trim());
        int length = a2.length;
        ((CardView) this.a).setContentPadding(length > 0 ? Z(a2[0].trim()) : 0, length > 1 ? Z(a2[1].trim()) : 0, length > 2 ? Z(a2[2].trim()) : 0, length > 3 ? Z(a2[3].trim()) : 0);
        return true;
    }

    public boolean s1(String str) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).goneRightMargin = Z(str);
        return true;
    }

    @TargetApi(16)
    public boolean s2(float f) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 16) {
            return true;
        }
        ((TextView) view).setLineSpacing(((TextView) view).getLineSpacingExtra(), f);
        return true;
    }

    public boolean s3(int i2) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setHintTextColor(i2);
        return true;
    }

    public boolean t0(Object obj) {
        if (!(this.a instanceof CollapsingToolbarLayout)) {
            return false;
        }
        ((CollapsingToolbarLayout) this.a).setContentScrim(obj instanceof Drawable ? (Drawable) obj : obj instanceof Bitmap ? new BitmapDrawable((Resources) null, (Bitmap) obj) : m(String.valueOf(obj), this.b));
        return true;
    }

    public boolean t1(String str) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).goneStartMargin = Z(str);
        return true;
    }

    public boolean t2(String str) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setLinksClickable(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean t3(int i2) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setLinkTextColor(i2);
        return true;
    }

    public boolean u0(String str) {
        View view = this.a;
        if (!(view instanceof CollapsingToolbarLayout)) {
            return false;
        }
        ((CollapsingToolbarLayout) view).setContentScrim(m(str, this.b));
        return true;
    }

    public boolean u1(String str) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).goneTopMargin = Z(str);
        return true;
    }

    public boolean u2(Object obj) {
        if (!(this.a instanceof ListView)) {
            return false;
        }
        ((ListView) this.a).setSelector(obj instanceof Drawable ? (Drawable) obj : obj instanceof Bitmap ? new BitmapDrawable((Resources) null, (Bitmap) obj) : m(String.valueOf(obj), this.b));
        return true;
    }

    public boolean u3(String str) {
        if (!(this.a instanceof TextView)) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                ((Drawable) TextView.class.getMethod("getTextCursorDrawable", new Class[0]).invoke((TextView) this.a, new Object[0])).setColorFilter(q(str), PorterDuff.Mode.SRC_IN);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
                declaredField.setAccessible(true);
                int i2 = declaredField.getInt(this.a);
                Field declaredField2 = TextView.class.getDeclaredField("mEditor");
                declaredField2.setAccessible(true);
                Object obj = declaredField2.get(this.a);
                Field declaredField3 = obj.getClass().getDeclaredField("mCursorDrawable");
                declaredField3.setAccessible(true);
                Drawable[] drawableArr = {this.a.getContext().getResources().getDrawable(i2)};
                drawableArr[0].setColorFilter(q(str), PorterDuff.Mode.SRC_IN);
                declaredField3.set(obj, drawableArr);
            } catch (Exception unused) {
            }
        }
        return true;
    }

    public int v() {
        return this.a.getLayoutParams().height;
    }

    public boolean v0(String str) {
        View view = this.a;
        if (!(view instanceof TextInputLayout)) {
            return false;
        }
        ((TextInputLayout) view).setCounterEnabled(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean v1(String str) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).horizontalBias = Float.parseFloat(str);
        return true;
    }

    public boolean v2(String str) {
        View view = this.a;
        if (!(view instanceof ListView)) {
            return false;
        }
        ((ListView) view).setSelector(m(str, this.b));
        return true;
    }

    @TargetApi(11)
    public boolean v3(String str) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 11) {
            ((TextView) view).setTextIsSelectable(str.equals("true") || str.equals("1"));
        }
        return true;
    }

    public String w() {
        View view = this.a;
        if (view instanceof TextView) {
            return ((TextView) view).getHint().toString();
        }
        return null;
    }

    public boolean w0(int i2) {
        View view = this.a;
        if (!(view instanceof TextInputLayout)) {
            return false;
        }
        ((TextInputLayout) view).setCounterMaxLength(i2);
        return true;
    }

    public boolean w1(String str) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).horizontalChainStyle = n(str);
        return true;
    }

    public boolean w2(String str) {
        this.a.setLongClickable(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean w3(String str) {
        View view = this.a;
        if (!(view instanceof SwitchCompat)) {
            return false;
        }
        ((SwitchCompat) view).setTextOff(str);
        return true;
    }

    public int x() {
        View view = this.a;
        if (view instanceof EditText) {
            return ((EditText) view).getImeOptions();
        }
        return 0;
    }

    public boolean x0(String str) {
        View view = this.a;
        if (!(view instanceof TextInputLayout)) {
            return false;
        }
        ((TextInputLayout) view).setError(str);
        return true;
    }

    public boolean x1(String str) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).horizontalWeight = Integer.parseInt(str);
        return true;
    }

    public boolean x2(String str) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setMarqueeRepeatLimit(I(str));
        return true;
    }

    public boolean x3(String str) {
        View view = this.a;
        if (!(view instanceof SwitchCompat)) {
            return false;
        }
        ((SwitchCompat) view).setTextOn(str);
        return true;
    }

    public int y(String str) {
        if (str.equals("actionnone")) {
            return 1;
        }
        if (str.equals("actiongo")) {
            return 2;
        }
        if (str.equals("actionsearch")) {
            return 3;
        }
        if (str.equals("actionsend")) {
            return 4;
        }
        if (str.equals("actionnext")) {
            return 5;
        }
        if (str.equals("actiondone")) {
            return 6;
        }
        if (str.matches("[0-9]+")) {
            return (int) Double.parseDouble(str);
        }
        return 1;
    }

    public boolean y0(String str) {
        View view = this.a;
        if (!(view instanceof TextInputLayout)) {
            return false;
        }
        ((TextInputLayout) view).setErrorEnabled(str.equals("true") || str.equals("1"));
        return true;
    }

    public boolean y1(int i2) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).leftToLeft = i2 != 0 ? this.f1935c + i2 : 0;
        return true;
    }

    public boolean y2(String str) {
        View view = this.a;
        if (view instanceof AbsSeekBar) {
            ((AbsSeekBar) view).setMax((int) Double.parseDouble(str));
            return true;
        }
        if (!(view instanceof ProgressBar)) {
            return false;
        }
        ((ProgressBar) view).setMax((int) Double.parseDouble(str));
        return true;
    }

    public boolean y3(float f) {
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setTextScaleX(f);
        return true;
    }

    public boolean z0(String str) {
        View view = this.a;
        if (!(view instanceof CollapsingToolbarLayout)) {
            return false;
        }
        ((CollapsingToolbarLayout) view).setExpandedTitleColor(q(str));
        return true;
    }

    public boolean z1(int i2) {
        if (!(this.a.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            return false;
        }
        ((ConstraintLayout.LayoutParams) this.a.getLayoutParams()).leftToRight = i2 != 0 ? this.f1935c + i2 : 0;
        return true;
    }

    public boolean z2(String str) {
        View view = this.a;
        if (!(view instanceof ImageView)) {
            return false;
        }
        ((ImageView) view).setMaxHeight(Z(str));
        return true;
    }

    public boolean z3(String str) {
        float[] S = S(str);
        if (S == null) {
            return false;
        }
        View view = this.a;
        if (!(view instanceof TextView)) {
            return false;
        }
        ((TextView) view).setTextSize((int) S[0], S[1]);
        return true;
    }
}
