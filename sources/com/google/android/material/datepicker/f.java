package com.google.android.material.datepicker;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes.dex */
public class f extends BaseAdapter {
    static final int f = j.q().getMaximum(4);
    final Month a;
    final DateSelector<?> b;

    /* renamed from: c */
    private Collection<Long> f1558c;

    /* renamed from: d */
    b f1559d;
    final CalendarConstraints e;

    public f(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.a = month;
        this.b = dateSelector;
        this.e = calendarConstraints;
        this.f1558c = dateSelector.getSelectedDays();
    }

    private void e(Context context) {
        if (this.f1559d == null) {
            this.f1559d = new b(context);
        }
    }

    private boolean h(long j) {
        Iterator<Long> it = this.b.getSelectedDays().iterator();
        while (it.hasNext()) {
            if (j.a(j) == j.a(it.next().longValue())) {
                return true;
            }
        }
        return false;
    }

    private void k(@Nullable TextView textView, long j) {
        a aVar;
        if (textView == null) {
            return;
        }
        if (this.e.getDateValidator().isValid(j)) {
            textView.setEnabled(true);
            if (h(j)) {
                aVar = this.f1559d.b;
            } else {
                long timeInMillis = j.o().getTimeInMillis();
                b bVar = this.f1559d;
                aVar = timeInMillis == j ? bVar.f1552c : bVar.a;
            }
        } else {
            textView.setEnabled(false);
            aVar = this.f1559d.g;
        }
        aVar.d(textView);
    }

    private void l(MaterialCalendarGridView materialCalendarGridView, long j) {
        if (Month.c(j).equals(this.a)) {
            k((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter().a(this.a.g(j)) - materialCalendarGridView.getFirstVisiblePosition()), j);
        }
    }

    public int a(int i2) {
        return b() + (i2 - 1);
    }

    public int b() {
        return this.a.e();
    }

    @Override // android.widget.Adapter
    @Nullable
    /* renamed from: c */
    public Long getItem(int i2) {
        if (i2 < this.a.e() || i2 > i()) {
            return null;
        }
        return Long.valueOf(this.a.f(j(i2)));
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0080 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0081  */
    @Override // android.widget.Adapter
    @androidx.annotation.NonNull
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.widget.TextView getView(int r6, @androidx.annotation.Nullable android.view.View r7, @androidx.annotation.NonNull android.view.ViewGroup r8) {
        /*
            r5 = this;
            android.content.Context r0 = r8.getContext()
            r5.e(r0)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r7 != 0) goto L1e
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r0 = com.google.android.material.R.layout.mtrl_calendar_day
            android.view.View r7 = r7.inflate(r0, r8, r1)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
        L1e:
            int r7 = r5.b()
            int r7 = r6 - r7
            if (r7 < 0) goto L72
            com.google.android.material.datepicker.Month r8 = r5.a
            int r2 = r8.e
            if (r7 < r2) goto L2d
            goto L72
        L2d:
            r2 = 1
            int r7 = r7 + r2
            r0.setTag(r8)
            android.content.res.Resources r8 = r0.getResources()
            android.content.res.Configuration r8 = r8.getConfiguration()
            java.util.Locale r8 = r8.locale
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)
            r3[r1] = r4
            java.lang.String r4 = "%d"
            java.lang.String r8 = java.lang.String.format(r8, r4, r3)
            r0.setText(r8)
            com.google.android.material.datepicker.Month r8 = r5.a
            long r7 = r8.f(r7)
            com.google.android.material.datepicker.Month r3 = r5.a
            int r3 = r3.f1541c
            com.google.android.material.datepicker.Month r4 = com.google.android.material.datepicker.Month.d()
            int r4 = r4.f1541c
            if (r3 != r4) goto L64
            java.lang.String r7 = com.google.android.material.datepicker.d.g(r7)
            goto L68
        L64:
            java.lang.String r7 = com.google.android.material.datepicker.d.l(r7)
        L68:
            r0.setContentDescription(r7)
            r0.setVisibility(r1)
            r0.setEnabled(r2)
            goto L7a
        L72:
            r7 = 8
            r0.setVisibility(r7)
            r0.setEnabled(r1)
        L7a:
            java.lang.Long r6 = r5.getItem(r6)
            if (r6 != 0) goto L81
            return r0
        L81:
            long r6 = r6.longValue()
            r5.k(r0, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.f.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    public boolean f(int i2) {
        return i2 % this.a.f1542d == 0;
    }

    public boolean g(int i2) {
        return (i2 + 1) % this.a.f1542d == 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.a.e + b();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2 / this.a.f1542d;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public int i() {
        return (this.a.e() + this.a.e) - 1;
    }

    int j(int i2) {
        return (i2 - this.a.e()) + 1;
    }

    public void m(MaterialCalendarGridView materialCalendarGridView) {
        Iterator<Long> it = this.f1558c.iterator();
        while (it.hasNext()) {
            l(materialCalendarGridView, it.next().longValue());
        }
        DateSelector<?> dateSelector = this.b;
        if (dateSelector != null) {
            Iterator<Long> it2 = dateSelector.getSelectedDays().iterator();
            while (it2.hasNext()) {
                l(materialCalendarGridView, it2.next().longValue());
            }
            this.f1558c = this.b.getSelectedDays();
        }
    }

    public boolean n(int i2) {
        return i2 >= b() && i2 <= i();
    }
}
