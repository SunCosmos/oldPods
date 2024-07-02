package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Iterator;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class MaterialCalendar<S> extends com.google.android.material.datepicker.g<S> {

    @VisibleForTesting
    static final Object m0 = "MONTHS_VIEW_GROUP_TAG";

    @VisibleForTesting
    static final Object n0 = "NAVIGATION_PREV_TAG";

    @VisibleForTesting
    static final Object o0 = "NAVIGATION_NEXT_TAG";

    @VisibleForTesting
    static final Object p0 = "SELECTOR_TOGGLE_TAG";

    @StyleRes
    private int c0;

    @Nullable
    private DateSelector<S> d0;

    @Nullable
    private CalendarConstraints e0;

    @Nullable
    private Month f0;
    private k g0;
    private com.google.android.material.datepicker.b h0;
    private RecyclerView i0;
    private RecyclerView j0;
    private View k0;
    private View l0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Runnable {
        final /* synthetic */ int a;

        a(int i2) {
            this.a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            MaterialCalendar.this.j0.smoothScrollToPosition(this.a);
        }
    }

    /* loaded from: classes.dex */
    class b extends AccessibilityDelegateCompat {
        b(MaterialCalendar materialCalendar) {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setCollectionInfo(null);
        }
    }

    /* loaded from: classes.dex */
    class c extends com.google.android.material.datepicker.h {
        final /* synthetic */ int a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Context context, int i2, boolean z, int i3) {
            super(context, i2, z);
            this.a = i3;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void calculateExtraLayoutSpace(@NonNull RecyclerView.State state, @NonNull int[] iArr) {
            if (this.a == 0) {
                iArr[0] = MaterialCalendar.this.j0.getWidth();
                iArr[1] = MaterialCalendar.this.j0.getWidth();
            } else {
                iArr[0] = MaterialCalendar.this.j0.getHeight();
                iArr[1] = MaterialCalendar.this.j0.getHeight();
            }
        }
    }

    /* loaded from: classes.dex */
    class d implements l {
        d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.datepicker.MaterialCalendar.l
        public void a(long j) {
            if (MaterialCalendar.this.e0.getDateValidator().isValid(j)) {
                MaterialCalendar.this.d0.select(j);
                Iterator<OnSelectionChangedListener<S>> it = MaterialCalendar.this.b0.iterator();
                while (it.hasNext()) {
                    it.next().onSelectionChanged(MaterialCalendar.this.d0.getSelection());
                }
                MaterialCalendar.this.j0.getAdapter().notifyDataSetChanged();
                if (MaterialCalendar.this.i0 != null) {
                    MaterialCalendar.this.i0.getAdapter().notifyDataSetChanged();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends RecyclerView.ItemDecoration {
        private final Calendar a = com.google.android.material.datepicker.j.q();
        private final Calendar b = com.google.android.material.datepicker.j.q();

        e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            if ((recyclerView.getAdapter() instanceof YearGridAdapter) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                YearGridAdapter yearGridAdapter = (YearGridAdapter) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (Pair<Long, Long> pair : MaterialCalendar.this.d0.getSelectedRanges()) {
                    Long l = pair.first;
                    if (l != null && pair.second != null) {
                        this.a.setTimeInMillis(l.longValue());
                        this.b.setTimeInMillis(pair.second.longValue());
                        int c2 = yearGridAdapter.c(this.a.get(1));
                        int c3 = yearGridAdapter.c(this.b.get(1));
                        View findViewByPosition = gridLayoutManager.findViewByPosition(c2);
                        View findViewByPosition2 = gridLayoutManager.findViewByPosition(c3);
                        int spanCount = c2 / gridLayoutManager.getSpanCount();
                        int spanCount2 = c3 / gridLayoutManager.getSpanCount();
                        int i2 = spanCount;
                        while (i2 <= spanCount2) {
                            if (gridLayoutManager.findViewByPosition(gridLayoutManager.getSpanCount() * i2) != null) {
                                canvas.drawRect(i2 == spanCount ? findViewByPosition.getLeft() + (findViewByPosition.getWidth() / 2) : 0, r9.getTop() + MaterialCalendar.this.h0.f1553d.c(), i2 == spanCount2 ? findViewByPosition2.getLeft() + (findViewByPosition2.getWidth() / 2) : recyclerView.getWidth(), r9.getBottom() - MaterialCalendar.this.h0.f1553d.b(), MaterialCalendar.this.h0.h);
                            }
                            i2++;
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class f extends AccessibilityDelegateCompat {
        f() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            MaterialCalendar materialCalendar;
            int i2;
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            if (MaterialCalendar.this.l0.getVisibility() == 0) {
                materialCalendar = MaterialCalendar.this;
                i2 = R.string.mtrl_picker_toggle_to_year_selection;
            } else {
                materialCalendar = MaterialCalendar.this;
                i2 = R.string.mtrl_picker_toggle_to_day_selection;
            }
            accessibilityNodeInfoCompat.setHintText(materialCalendar.getString(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class g extends RecyclerView.OnScrollListener {
        final /* synthetic */ MonthsPagerAdapter a;
        final /* synthetic */ MaterialButton b;

        g(MonthsPagerAdapter monthsPagerAdapter, MaterialButton materialButton) {
            this.a = monthsPagerAdapter;
            this.b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
            if (i2 == 0) {
                CharSequence text = this.b.getText();
                if (Build.VERSION.SDK_INT >= 16) {
                    recyclerView.announceForAccessibility(text);
                } else {
                    recyclerView.sendAccessibilityEvent(2048);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
            LinearLayoutManager C0 = MaterialCalendar.this.C0();
            int findFirstVisibleItemPosition = i2 < 0 ? C0.findFirstVisibleItemPosition() : C0.findLastVisibleItemPosition();
            MaterialCalendar.this.f0 = this.a.b(findFirstVisibleItemPosition);
            this.b.setText(this.a.c(findFirstVisibleItemPosition));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class h implements View.OnClickListener {
        h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MaterialCalendar.this.G0();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class i implements View.OnClickListener {
        final /* synthetic */ MonthsPagerAdapter a;

        i(MonthsPagerAdapter monthsPagerAdapter) {
            this.a = monthsPagerAdapter;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int findFirstVisibleItemPosition = MaterialCalendar.this.C0().findFirstVisibleItemPosition() + 1;
            if (findFirstVisibleItemPosition < MaterialCalendar.this.j0.getAdapter().getItemCount()) {
                MaterialCalendar.this.E0(this.a.b(findFirstVisibleItemPosition));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class j implements View.OnClickListener {
        final /* synthetic */ MonthsPagerAdapter a;

        j(MonthsPagerAdapter monthsPagerAdapter) {
            this.a = monthsPagerAdapter;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int findLastVisibleItemPosition = MaterialCalendar.this.C0().findLastVisibleItemPosition() - 1;
            if (findLastVisibleItemPosition >= 0) {
                MaterialCalendar.this.E0(this.a.b(findLastVisibleItemPosition));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum k {
        DAY,
        YEAR
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface l {
        void a(long j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Px
    public static int B0(@NonNull Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.mtrl_calendar_day_height);
    }

    private void D0(int i2) {
        this.j0.post(new a(i2));
    }

    @NonNull
    public static <T> MaterialCalendar<T> newInstance(@NonNull DateSelector<T> dateSelector, @StyleRes int i2, @NonNull CalendarConstraints calendarConstraints) {
        MaterialCalendar<T> materialCalendar = new MaterialCalendar<>();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i2);
        bundle.putParcelable("GRID_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints.h());
        materialCalendar.setArguments(bundle);
        return materialCalendar;
    }

    private void w0(@NonNull View view, @NonNull MonthsPagerAdapter monthsPagerAdapter) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(R.id.month_navigation_fragment_toggle);
        materialButton.setTag(p0);
        ViewCompat.setAccessibilityDelegate(materialButton, new f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(R.id.month_navigation_previous);
        materialButton2.setTag(n0);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(R.id.month_navigation_next);
        materialButton3.setTag(o0);
        this.k0 = view.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.l0 = view.findViewById(R.id.mtrl_calendar_day_selector_frame);
        F0(k.DAY);
        materialButton.setText(this.f0.h(view.getContext()));
        this.j0.addOnScrollListener(new g(monthsPagerAdapter, materialButton));
        materialButton.setOnClickListener(new h());
        materialButton3.setOnClickListener(new i(monthsPagerAdapter));
        materialButton2.setOnClickListener(new j(monthsPagerAdapter));
    }

    @NonNull
    private RecyclerView.ItemDecoration x0() {
        return new e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Month A0() {
        return this.f0;
    }

    @NonNull
    LinearLayoutManager C0() {
        return (LinearLayoutManager) this.j0.getLayoutManager();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void E0(Month month) {
        RecyclerView recyclerView;
        int i2;
        MonthsPagerAdapter monthsPagerAdapter = (MonthsPagerAdapter) this.j0.getAdapter();
        int d2 = monthsPagerAdapter.d(month);
        int d3 = d2 - monthsPagerAdapter.d(this.f0);
        boolean z = Math.abs(d3) > 3;
        boolean z2 = d3 > 0;
        this.f0 = month;
        if (!z || !z2) {
            if (z) {
                recyclerView = this.j0;
                i2 = d2 + 3;
            }
            D0(d2);
        }
        recyclerView = this.j0;
        i2 = d2 - 3;
        recyclerView.scrollToPosition(i2);
        D0(d2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F0(k kVar) {
        this.g0 = kVar;
        if (kVar == k.YEAR) {
            this.i0.getLayoutManager().scrollToPosition(((YearGridAdapter) this.i0.getAdapter()).c(this.f0.f1541c));
            this.k0.setVisibility(0);
            this.l0.setVisibility(8);
        } else if (kVar == k.DAY) {
            this.k0.setVisibility(8);
            this.l0.setVisibility(0);
            E0(this.f0);
        }
    }

    void G0() {
        k kVar = this.g0;
        k kVar2 = k.YEAR;
        if (kVar == kVar2) {
            F0(k.DAY);
        } else if (kVar == k.DAY) {
            F0(kVar2);
        }
    }

    @Override // com.google.android.material.datepicker.g
    public boolean addOnSelectionChangedListener(@NonNull OnSelectionChangedListener<S> onSelectionChangedListener) {
        return super.addOnSelectionChangedListener(onSelectionChangedListener);
    }

    @Nullable
    public DateSelector<S> getDateSelector() {
        return this.d0;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.c0 = bundle.getInt("THEME_RES_ID_KEY");
        this.d0 = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.e0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.f0 = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        int i2;
        int i3;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.c0);
        this.h0 = new com.google.android.material.datepicker.b(contextThemeWrapper);
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month i4 = this.e0.i();
        if (MaterialDatePicker.I0(contextThemeWrapper)) {
            i2 = R.layout.mtrl_calendar_vertical;
            i3 = 1;
        } else {
            i2 = R.layout.mtrl_calendar_horizontal;
            i3 = 0;
        }
        View inflate = cloneInContext.inflate(i2, viewGroup, false);
        GridView gridView = (GridView) inflate.findViewById(R.id.mtrl_calendar_days_of_week);
        ViewCompat.setAccessibilityDelegate(gridView, new b(this));
        gridView.setAdapter((ListAdapter) new com.google.android.material.datepicker.e());
        gridView.setNumColumns(i4.f1542d);
        gridView.setEnabled(false);
        this.j0 = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_months);
        this.j0.setLayoutManager(new c(getContext(), i3, false, i3));
        this.j0.setTag(m0);
        MonthsPagerAdapter monthsPagerAdapter = new MonthsPagerAdapter(contextThemeWrapper, this.d0, this.e0, new d());
        this.j0.setAdapter(monthsPagerAdapter);
        int integer = contextThemeWrapper.getResources().getInteger(R.integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.i0 = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.i0.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.i0.setAdapter(new YearGridAdapter(this));
            this.i0.addItemDecoration(x0());
        }
        if (inflate.findViewById(R.id.month_navigation_fragment_toggle) != null) {
            w0(inflate, monthsPagerAdapter);
        }
        if (!MaterialDatePicker.I0(contextThemeWrapper)) {
            new PagerSnapHelper().attachToRecyclerView(this.j0);
        }
        this.j0.scrollToPosition(monthsPagerAdapter.d(this.f0));
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.c0);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.d0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.e0);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.f0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public CalendarConstraints y0() {
        return this.e0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.google.android.material.datepicker.b z0() {
        return this.h0;
    }
}
