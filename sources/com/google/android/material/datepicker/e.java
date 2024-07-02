package com.google.android.material.datepicker;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.R;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes.dex */
class e extends BaseAdapter {

    /* renamed from: d, reason: collision with root package name */
    private static final int f1556d;

    @NonNull
    private final Calendar a;
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    private final int f1557c;

    static {
        f1556d = Build.VERSION.SDK_INT >= 26 ? 4 : 1;
    }

    public e() {
        Calendar q = j.q();
        this.a = q;
        this.b = q.getMaximum(7);
        this.f1557c = q.getFirstDayOfWeek();
    }

    private int b(int i2) {
        int i3 = i2 + this.f1557c;
        int i4 = this.b;
        return i3 > i4 ? i3 - i4 : i3;
    }

    @Override // android.widget.Adapter
    @Nullable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer getItem(int i2) {
        if (i2 >= this.b) {
            return null;
        }
        return Integer.valueOf(b(i2));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.b;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.Adapter
    @Nullable
    @SuppressLint({"WrongConstant"})
    public View getView(int i2, @Nullable View view, @NonNull ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_day_of_week, viewGroup, false);
        }
        this.a.set(7, b(i2));
        textView.setText(this.a.getDisplayName(7, f1556d, textView.getResources().getConfiguration().locale));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(R.string.mtrl_picker_day_of_week_column_header), this.a.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
