package c.c.a;

import android.R;
import android.content.Context;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.VideoView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import c.b.a.a.p;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.iapp.app.RoundImageView;
import com.iapp.app.TabLayout;
import com.iapp.app.i;
import com.iapp.app.x5.WebView;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import pl.droidsonroids.gif.GifImageView;

/* loaded from: classes.dex */
public class b {
    public int a = 0;
    private Context b;

    public b(Context context) {
        this.b = null;
        this.b = context;
    }

    private Object[] b(String str, String str2) {
        return new Object[]{str, str2, "", null};
    }

    public NestedScrollView A(int i2) {
        NestedScrollView nestedScrollView = new NestedScrollView(this.b);
        nestedScrollView.setId(i2);
        nestedScrollView.setTag(b("NestedScrollView", "width=-2\nheight=-2"));
        g(i2);
        return nestedScrollView;
    }

    public ProgressBar B(int i2) {
        ProgressBar progressBar = new ProgressBar(this.b, null, R.attr.progressBarStyleHorizontal);
        progressBar.setId(i2);
        progressBar.setTag(b("ProgressBar", "width=-2\nheight=-2"));
        g(i2);
        return progressBar;
    }

    public ProgressBar C(int i2, int i3) {
        ProgressBar progressBar = new ProgressBar(this.b, null, i3);
        progressBar.setId(i2);
        progressBar.setTag(b("ProgressBar", "width=-2\nheight=-2"));
        g(i2);
        return progressBar;
    }

    public RadioButton D(int i2) {
        RadioButton radioButton = new RadioButton(this.b);
        radioButton.setId(i2);
        String str = "单选项" + i2;
        radioButton.setText(str);
        radioButton.setTag(b("RadioButton", "width=-2\nheight=-2\ntext=" + str));
        g(i2);
        return radioButton;
    }

    public RadioGroup E(int i2) {
        RadioGroup radioGroup = new RadioGroup(this.b);
        radioGroup.setId(i2);
        radioGroup.setTag(b("RadioGroup", "width=-2\nheight=-2"));
        g(i2);
        return radioGroup;
    }

    public RatingBar F(int i2) {
        RatingBar ratingBar = new RatingBar(this.b);
        ratingBar.setId(i2);
        ratingBar.setTag(b("RatingBar", "width=-2\nheight=-2"));
        g(i2);
        return ratingBar;
    }

    public RecyclerView G(int i2) {
        RecyclerView recyclerView = new RecyclerView(this.b);
        recyclerView.setId(i2);
        recyclerView.setTag(b("RecyclerView", "width=-1\nheight=-2"));
        g(i2);
        return recyclerView;
    }

    public RelativeLayout H(int i2) {
        RelativeLayout relativeLayout = new RelativeLayout(this.b);
        relativeLayout.setId(i2);
        relativeLayout.setTag(b("RelativeLayout", "width=-2\nheight=-2"));
        g(i2);
        return relativeLayout;
    }

    public RoundImageView I(int i2) {
        RoundImageView roundImageView = new RoundImageView(this.b);
        roundImageView.setId(i2);
        roundImageView.setImageResource(R.drawable.ic_menu_gallery);
        roundImageView.setTag(b("RoundImageView", "width=-2\nheight=-2\nsrc=17301567"));
        g(i2);
        return roundImageView;
    }

    public ScrollView J(int i2) {
        ScrollView scrollView = new ScrollView(this.b);
        scrollView.setId(i2);
        scrollView.setTag(b("ScrollView", "width=-2\nheight=-2"));
        g(i2);
        return scrollView;
    }

    public SeekBar K(int i2) {
        SeekBar seekBar = new SeekBar(this.b);
        seekBar.setId(i2);
        seekBar.setTag(b("SeekBar", "width=-2\nheight=-2"));
        g(i2);
        return seekBar;
    }

    public Spinner L(int i2) {
        Spinner spinner = new Spinner(this.b);
        spinner.setId(i2);
        spinner.setTag(b("Spinner", "width=-2\nheight=-2"));
        g(i2);
        return spinner;
    }

    public SurfaceView M(int i2) {
        SurfaceView surfaceView = new SurfaceView(this.b);
        surfaceView.setId(i2);
        surfaceView.setTag(b("SurfaceView", "width=-2\nheight=-2"));
        g(i2);
        return surfaceView;
    }

    public SwipeRefreshLayout N(int i2) {
        SwipeRefreshLayout swipeRefreshLayout = new SwipeRefreshLayout(this.b);
        swipeRefreshLayout.setId(i2);
        swipeRefreshLayout.setTag(b("SwipeRefreshLayout", "width=-1\nheight=-2"));
        swipeRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        g(i2);
        return swipeRefreshLayout;
    }

    public SwitchCompat O(int i2) {
        SwitchCompat switchCompat = new SwitchCompat(this.b);
        switchCompat.setId(i2);
        switchCompat.setTag(b("SwitchCompat", "width=-2\nheight=-2"));
        g(i2);
        return switchCompat;
    }

    public TabLayout P(int i2) {
        TabLayout tabLayout = new TabLayout(this.b);
        tabLayout.setId(i2);
        tabLayout.setTag(b("TabLayout", "width=-2\nheight=-2"));
        g(i2);
        return tabLayout;
    }

    public TableLayout Q(int i2) {
        TableLayout tableLayout = new TableLayout(this.b);
        tableLayout.setId(i2);
        tableLayout.setTag(b("TableLayout", "width=-2\nheight=-2"));
        tableLayout.setColumnCollapsed(1, true);
        g(i2);
        return tableLayout;
    }

    public TableRow R(int i2) {
        TableRow tableRow = new TableRow(this.b);
        tableRow.setId(i2);
        tableRow.setTag(b("TableRow", ""));
        g(i2);
        return tableRow;
    }

    public TextInputLayout S(int i2) {
        TextInputLayout textInputLayout = new TextInputLayout(this.b);
        textInputLayout.setId(i2);
        textInputLayout.setTag(b("TextInputLayout", "width=-2\nheight=-2"));
        g(i2);
        return textInputLayout;
    }

    public TextView T(int i2) {
        TextView textView = new TextView(this.b);
        textView.setId(i2);
        String str = "文本" + i2;
        textView.setText(str);
        textView.setTag(b("TextView", "width=-2\nheight=-2\ntext=" + str));
        g(i2);
        return textView;
    }

    public TimePicker U(int i2) {
        TimePicker timePicker = new TimePicker(this.b);
        timePicker.setId(i2);
        timePicker.setTag(b("TimePicker", "width=-2\nheight=-2"));
        g(i2);
        return timePicker;
    }

    public Toolbar V(int i2) {
        Toolbar toolbar = new Toolbar(this.b);
        toolbar.setId(i2);
        toolbar.setTag(b("Toolbar", "width=-1\nheight=-3"));
        toolbar.setLayoutParams(new ViewGroup.LayoutParams(-1, p.s(this.b)));
        g(i2);
        return toolbar;
    }

    public VerticalViewPager W(int i2) {
        VerticalViewPager verticalViewPager = new VerticalViewPager(this.b);
        verticalViewPager.setId(i2);
        verticalViewPager.setTag(b("VerticalViewPager", "width=-1\nheight=-2"));
        verticalViewPager.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        g(i2);
        return verticalViewPager;
    }

    public VideoView X(int i2) {
        VideoView videoView = new VideoView(this.b);
        videoView.setId(i2);
        videoView.setTag(b("VideoView", "width=-2\nheight=-2"));
        g(i2);
        return videoView;
    }

    public ViewPager Y(int i2) {
        ViewPager viewPager = new ViewPager(this.b);
        viewPager.setId(i2);
        viewPager.setTag(b("ViewPager", "width=-2\nheight=-2"));
        g(i2);
        return viewPager;
    }

    public WebView Z(int i2) {
        WebView webView = new WebView(this.b);
        webView.setId(i2);
        webView.setTag(b("WebView", "width=-2\nheight=-2"));
        g(i2);
        return webView;
    }

    public boolean a(i iVar, String str) {
        int indexOf;
        for (String str2 : str.replace("&lt;", "<").replace("&gt;", ">").replace("\r", "").split("\n")) {
            if (!str2.startsWith(".") && !str2.startsWith("//") && (indexOf = str2.indexOf(61)) != -1) {
                try {
                    iVar.b(str2.substring(0, indexOf).toLowerCase(), str2.substring(indexOf + 1));
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    public String c(String str) {
        if (str.equals("文本")) {
            return "TextView";
        }
        if (str.equals("图像")) {
            return "ImageView";
        }
        if (str.equals("按钮")) {
            return "Button";
        }
        if (str.equals("图像按钮")) {
            return "ImageButton";
        }
        if (str.equals("编辑框")) {
            return "EditText";
        }
        if (str.equals("单选布局")) {
            return "RadioGroup";
        }
        if (str.equals("单选项")) {
            return "RadioButton";
        }
        if (str.equals("多选")) {
            return "CheckBox";
        }
        if (str.equals("列表")) {
            return "ListView";
        }
        if (str.equals("浏览器")) {
            return "WebView";
        }
        if (str.equals("下拉菜单")) {
            return "Spinner";
        }
        if (str.equals("评分")) {
            return "RatingBar";
        }
        if (str.equals("视频")) {
            return "VideoView";
        }
        if (str.equals("动态图")) {
            return "GifView";
        }
        if (str.equals("圆形图")) {
            return "RoundImageView";
        }
        if (str.equals("拖动条")) {
            return "SeekBar";
        }
        if (str.equals("进度条")) {
            return "ProgressBar";
        }
        if (str.equals("日期选择器")) {
            return "DatePicker";
        }
        if (str.equals("时间选择器")) {
            return "TimePicker";
        }
        if (str.equals("网格视图")) {
            return "GridView";
        }
        if (str.equals("线性布局")) {
            return "LinearLayout";
        }
        if (str.equals("相对布局")) {
            return "RelativeLayout";
        }
        if (str.equals("表格布局")) {
            return "TableLayout";
        }
        if (str.equals("表格项")) {
            return "TableRow";
        }
        if (str.equals("帧布局")) {
            return "FrameLayout";
        }
        if (str.equals("面控件")) {
            return "SurfaceView";
        }
        if (str.equals("滚动")) {
            return "ScrollView";
        }
        if (str.equals("水平滚动")) {
            return "HorizontalScrollView";
        }
        if (str.equals("滑动窗体")) {
            return "ViewPager";
        }
        if (str.equals("侧滑窗体")) {
            return "DrawerLayout";
        }
        if (str.equals("协调性布局")) {
            return "CoordinatorLayout";
        }
        if (str.equals("应用栏布局")) {
            return "AppBarLayout";
        }
        if (str.equals("折叠工具栏布局")) {
            return "CollapsingToolbarLayout";
        }
        if (str.equals("工具栏布局")) {
            return "Toolbar";
        }
        if (str.equals("浮动动作按钮")) {
            return "FloatingActionButton";
        }
        if (str.equals("嵌套滚动")) {
            return "NestedScrollView";
        }
        if (str.equals("标签布局")) {
            return "TabLayout";
        }
        if (str.equals("v7列表")) {
            return "RecyclerView";
        }
        if (str.equals("约束性布局")) {
            return "ConstraintLayout";
        }
        if (str.equals("垂直滑动窗体")) {
            return "VerticalViewPager";
        }
        if (str.equals("下拉刷新控件")) {
            return "SwipeRefreshLayout";
        }
        if (str.equals("文本输入布局")) {
            return "TextInputLayout";
        }
        if (str.equals("开关")) {
            return "SwitchCompat";
        }
        if (str.equals("卡片")) {
            return "CardView";
        }
        return null;
    }

    public ViewGroup.LayoutParams d(View view, View view2) {
        if (view2 instanceof TableRow) {
            return new TableLayout.LayoutParams();
        }
        if (view instanceof TableRow) {
            return new TableRow.LayoutParams(-2, -2);
        }
        if (!(view instanceof CoordinatorLayout) && !(view instanceof RecyclerView)) {
            return view instanceof AppBarLayout ? new AppBarLayout.LayoutParams(-1, -2) : view instanceof CollapsingToolbarLayout ? new CollapsingToolbarLayout.LayoutParams(-1, -2) : view instanceof Toolbar ? new Toolbar.LayoutParams(-1, -2) : view instanceof ConstraintLayout ? new ConstraintLayout.LayoutParams(-2, -2) : view instanceof TableLayout ? new TableLayout.LayoutParams(-2, -2) : view instanceof RelativeLayout ? new RelativeLayout.LayoutParams(-2, -2) : view instanceof DrawerLayout ? new DrawerLayout.LayoutParams(-2, -2) : view instanceof LinearLayout ? new LinearLayout.LayoutParams(-2, -2) : new ViewGroup.LayoutParams(-2, -2);
        }
        return new CoordinatorLayout.LayoutParams(-1, -2);
    }

    public View e(int i2, String str) {
        if (str.equals("TextView")) {
            return T(i2);
        }
        if (str.equals("ImageView")) {
            return x(i2);
        }
        if (str.equals("Button")) {
            return i(i2);
        }
        if (str.equals("ImageButton")) {
            return w(i2);
        }
        if (str.equals("EditText")) {
            return q(i2);
        }
        if (str.equals("RadioGroup")) {
            return E(i2);
        }
        if (str.equals("RadioButton")) {
            return D(i2);
        }
        if (str.equals("CheckBox")) {
            return k(i2);
        }
        if (str.equals("ListView")) {
            return z(i2);
        }
        if (str.equals("WebView")) {
            return Z(i2);
        }
        if (str.equals("Spinner")) {
            return L(i2);
        }
        if (str.equals("VideoView")) {
            return X(i2);
        }
        if (str.equals("GifView")) {
            return t(i2);
        }
        if (str.equals("RoundImageView")) {
            return I(i2);
        }
        if (str.equals("RatingBar")) {
            return F(i2);
        }
        if (str.equals("SeekBar")) {
            return K(i2);
        }
        if (str.equals("ProgressBar")) {
            return B(i2);
        }
        if (str.equals("DatePicker")) {
            return o(i2);
        }
        if (str.equals("TimePicker")) {
            return U(i2);
        }
        if (str.equals("GridView")) {
            return u(i2);
        }
        if (str.equals("LinearLayout")) {
            return y(i2);
        }
        if (str.equals("RelativeLayout")) {
            return H(i2);
        }
        if (str.equals("TableLayout")) {
            return Q(i2);
        }
        if (str.equals("TableRow")) {
            return R(i2);
        }
        if (str.equals("FrameLayout")) {
            return s(i2);
        }
        if (str.equals("SurfaceView")) {
            return M(i2);
        }
        if (str.equals("ScrollView")) {
            return J(i2);
        }
        if (str.equals("HorizontalScrollView")) {
            return v(i2);
        }
        if (str.equals("ViewPager")) {
            return Y(i2);
        }
        if (str.equals("DrawerLayout")) {
            return p(i2);
        }
        if (str.equals("CoordinatorLayout")) {
            return n(i2);
        }
        if (str.equals("AppBarLayout")) {
            return h(i2);
        }
        if (str.equals("CollapsingToolbarLayout")) {
            return l(i2);
        }
        if (str.equals("Toolbar")) {
            return V(i2);
        }
        if (str.equals("FloatingActionButton")) {
            return r(i2);
        }
        if (str.equals("NestedScrollView")) {
            return A(i2);
        }
        if (str.equals("TabLayout")) {
            return P(i2);
        }
        if (str.equals("RecyclerView")) {
            return G(i2);
        }
        if (str.equals("ConstraintLayout")) {
            return m(i2);
        }
        if (str.equals("VerticalViewPager")) {
            return W(i2);
        }
        if (str.equals("SwipeRefreshLayout")) {
            return N(i2);
        }
        if (str.equals("TextInputLayout")) {
            return S(i2);
        }
        if (str.equals("SwitchCompat")) {
            return O(i2);
        }
        if (str.equals("CardView")) {
            return j(i2);
        }
        return null;
    }

    public View f(int i2, String str, Object obj) {
        if (str.equals("ProgressBar")) {
            return C(i2, Integer.parseInt(String.valueOf(obj).trim()));
        }
        return null;
    }

    public void g(int i2) {
        if (this.a < i2) {
            this.a = i2;
        }
    }

    public AppBarLayout h(int i2) {
        AppBarLayout appBarLayout = new AppBarLayout(this.b);
        appBarLayout.setId(i2);
        appBarLayout.setTag(b("AppBarLayout", "width=-1\nheight=-2"));
        appBarLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        g(i2);
        return appBarLayout;
    }

    public Button i(int i2) {
        Button button = new Button(this.b);
        button.setId(i2);
        String str = "按钮" + i2;
        button.setText(str);
        button.setTag(b("Button", "width=-2\nheight=-2\ntext=" + str));
        g(i2);
        return button;
    }

    public CardView j(int i2) {
        CardView cardView = new CardView(this.b);
        cardView.setId(i2);
        cardView.setTag(b("CardView", "width=-2\nheight=-2"));
        g(i2);
        return cardView;
    }

    public CheckBox k(int i2) {
        CheckBox checkBox = new CheckBox(this.b);
        checkBox.setId(i2);
        String str = "多选项" + i2;
        checkBox.setText(str);
        checkBox.setTag(b("CheckBox", "width=-2\nheight=-2\ntext=" + str));
        g(i2);
        return checkBox;
    }

    public CollapsingToolbarLayout l(int i2) {
        CollapsingToolbarLayout collapsingToolbarLayout = new CollapsingToolbarLayout(this.b);
        collapsingToolbarLayout.setId(i2);
        collapsingToolbarLayout.setTag(b("CollapsingToolbarLayout", "width=-1\nheight=-2"));
        collapsingToolbarLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        g(i2);
        return collapsingToolbarLayout;
    }

    public ConstraintLayout m(int i2) {
        ConstraintLayout constraintLayout = new ConstraintLayout(this.b);
        constraintLayout.setId(i2);
        constraintLayout.setTag(b("ConstraintLayout", "width=-2\nheight=-2"));
        g(i2);
        return constraintLayout;
    }

    public CoordinatorLayout n(int i2) {
        CoordinatorLayout coordinatorLayout = new CoordinatorLayout(this.b);
        coordinatorLayout.setId(i2);
        coordinatorLayout.setTag(b("CoordinatorLayout", "width=-2\nheight=-2"));
        g(i2);
        return coordinatorLayout;
    }

    public DatePicker o(int i2) {
        DatePicker datePicker = new DatePicker(this.b);
        datePicker.setId(i2);
        datePicker.setTag(b("DatePicker", "width=-2\nheight=-2"));
        g(i2);
        return datePicker;
    }

    public DrawerLayout p(int i2) {
        DrawerLayout drawerLayout = new DrawerLayout(this.b);
        drawerLayout.setId(i2);
        drawerLayout.setTag(b("DrawerLayout", "width=-2\nheight=-2"));
        g(i2);
        return drawerLayout;
    }

    public EditText q(int i2) {
        EditText editText = new EditText(this.b);
        editText.setId(i2);
        String str = "文本框" + i2;
        editText.setText(str);
        editText.setTag(b("EditText", "width=-2\nheight=-2\ntext=" + str));
        g(i2);
        return editText;
    }

    public FloatingActionButton r(int i2) {
        FloatingActionButton floatingActionButton = new FloatingActionButton(this.b);
        floatingActionButton.setId(i2);
        floatingActionButton.setImageResource(R.drawable.ic_dialog_dialer);
        floatingActionButton.setTag(b("FloatingActionButton", "width=-2\nheight=-2\nsrc=17301544"));
        g(i2);
        return floatingActionButton;
    }

    public FrameLayout s(int i2) {
        FrameLayout frameLayout = new FrameLayout(this.b);
        frameLayout.setId(i2);
        frameLayout.setTag(b("FrameLayout", "width=-2\nheight=-2"));
        g(i2);
        return frameLayout;
    }

    public GifImageView t(int i2) {
        GifImageView gifImageView = new GifImageView(this.b);
        gifImageView.setId(i2);
        gifImageView.setTag(b("GifView", "width=-2\nheight=-2"));
        g(i2);
        return gifImageView;
    }

    public GridView u(int i2) {
        GridView gridView = new GridView(this.b);
        gridView.setId(i2);
        gridView.setNumColumns(-1);
        gridView.setStretchMode(2);
        gridView.setTag(b("GridView", "width=-2\nheight=-2"));
        g(i2);
        return gridView;
    }

    public HorizontalScrollView v(int i2) {
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this.b);
        horizontalScrollView.setId(i2);
        horizontalScrollView.setTag(b("HorizontalScrollView", "width=-2\nheight=-2"));
        g(i2);
        return horizontalScrollView;
    }

    public ImageButton w(int i2) {
        ImageButton imageButton = new ImageButton(this.b);
        imageButton.setId(i2);
        imageButton.setImageResource(R.drawable.ic_menu_revert);
        imageButton.setTag(b("ImageButton", "width=-2\nheight=-2\nsrc=17301580"));
        g(i2);
        return imageButton;
    }

    public ImageView x(int i2) {
        ImageView imageView = new ImageView(this.b);
        imageView.setId(i2);
        imageView.setImageResource(R.drawable.ic_menu_gallery);
        imageView.setTag(b("ImageView", "width=-2\nheight=-2\nsrc=17301567"));
        g(i2);
        return imageView;
    }

    public LinearLayout y(int i2) {
        LinearLayout linearLayout = new LinearLayout(this.b);
        linearLayout.setId(i2);
        linearLayout.setTag(b("LinearLayout", "width=-2\nheight=-2\norientation=vertical"));
        g(i2);
        return linearLayout;
    }

    public ListView z(int i2) {
        ListView listView = new ListView(this.b);
        listView.setId(i2);
        listView.setTag(b("ListView", "width=-2\nheight=-2"));
        g(i2);
        return listView;
    }
}
