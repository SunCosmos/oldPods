package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.datepicker.MaterialCalendar;

/* loaded from: classes.dex */
public class MonthsPagerAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: c */
    private final Context f1543c;

    /* renamed from: d */
    @NonNull
    private final CalendarConstraints f1544d;
    private final DateSelector<?> e;
    private final MaterialCalendar.l f;
    private final int g;

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView s;
        final MaterialCalendarGridView t;

        ViewHolder(@NonNull LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(R.id.month_title);
            this.s = textView;
            ViewCompat.setAccessibilityHeading(textView, true);
            this.t = (MaterialCalendarGridView) linearLayout.findViewById(R.id.month_grid);
            if (z) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    /* loaded from: classes.dex */
    public class a implements AdapterView.OnItemClickListener {
        final /* synthetic */ MaterialCalendarGridView a;

        a(MaterialCalendarGridView materialCalendarGridView) {
            this.a = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            if (this.a.getAdapter().n(i2)) {
                MonthsPagerAdapter.this.f.a(this.a.getAdapter().getItem(i2).longValue());
            }
        }
    }

    public MonthsPagerAdapter(@NonNull Context context, DateSelector<?> dateSelector, @NonNull CalendarConstraints calendarConstraints, MaterialCalendar.l lVar) {
        Month i2 = calendarConstraints.i();
        Month f = calendarConstraints.f();
        Month h = calendarConstraints.h();
        if (i2.compareTo(h) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (h.compareTo(f) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        int B0 = f.f * MaterialCalendar.B0(context);
        int B02 = MaterialDatePicker.I0(context) ? MaterialCalendar.B0(context) : 0;
        this.f1543c = context;
        this.g = B0 + B02;
        this.f1544d = calendarConstraints;
        this.e = dateSelector;
        this.f = lVar;
        setHasStableIds(true);
    }

    @NonNull
    public Month b(int i2) {
        return this.f1544d.i().j(i2);
    }

    @NonNull
    public CharSequence c(int i2) {
        return b(i2).h(this.f1543c);
    }

    public int d(@NonNull Month month) {
        return this.f1544d.i().k(month);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e */
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i2) {
        Month j = this.f1544d.i().j(i2);
        viewHolder.s.setText(j.h(viewHolder.itemView.getContext()));
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) viewHolder.t.findViewById(R.id.month_grid);
        if (materialCalendarGridView.getAdapter() == null || !j.equals(materialCalendarGridView.getAdapter().a)) {
            f fVar = new f(j, this.e, this.f1544d);
            materialCalendarGridView.setNumColumns(j.f1542d);
            materialCalendarGridView.setAdapter((ListAdapter) fVar);
        } else {
            materialCalendarGridView.invalidate();
            materialCalendarGridView.getAdapter().m(materialCalendarGridView);
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: f */
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (!MaterialDatePicker.I0(viewGroup.getContext())) {
            return new ViewHolder(linearLayout, false);
        }
        linearLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, this.g));
        return new ViewHolder(linearLayout, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f1544d.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        return this.f1544d.i().j(i2).i();
    }
}
