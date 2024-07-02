package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.datepicker.MaterialCalendar;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes.dex */
public class YearGridAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: c */
    private final MaterialCalendar<?> f1549c;

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView s;

        ViewHolder(TextView textView) {
            super(textView);
            this.s = textView;
        }
    }

    /* loaded from: classes.dex */
    public class a implements View.OnClickListener {
        final /* synthetic */ int a;

        a(int i2) {
            this.a = i2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            YearGridAdapter.this.f1549c.E0(YearGridAdapter.this.f1549c.y0().e(Month.b(this.a, YearGridAdapter.this.f1549c.A0().b)));
            YearGridAdapter.this.f1549c.F0(MaterialCalendar.k.DAY);
        }
    }

    public YearGridAdapter(MaterialCalendar<?> materialCalendar) {
        this.f1549c = materialCalendar;
    }

    @NonNull
    private View.OnClickListener b(int i2) {
        return new a(i2);
    }

    public int c(int i2) {
        return i2 - this.f1549c.y0().i().f1541c;
    }

    int d(int i2) {
        return this.f1549c.y0().i().f1541c + i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e */
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i2) {
        int d2 = d(i2);
        String string = viewHolder.s.getContext().getString(R.string.mtrl_picker_navigate_to_year_description);
        viewHolder.s.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(d2)));
        viewHolder.s.setContentDescription(String.format(string, Integer.valueOf(d2)));
        b z0 = this.f1549c.z0();
        Calendar o = j.o();
        com.google.android.material.datepicker.a aVar = o.get(1) == d2 ? z0.f : z0.f1553d;
        Iterator<Long> it = this.f1549c.getDateSelector().getSelectedDays().iterator();
        while (it.hasNext()) {
            o.setTimeInMillis(it.next().longValue());
            if (o.get(1) == d2) {
                aVar = z0.e;
            }
        }
        aVar.d(viewHolder.s);
        viewHolder.s.setOnClickListener(b(d2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: f */
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return new ViewHolder((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_year, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f1549c.y0().j();
    }
}
