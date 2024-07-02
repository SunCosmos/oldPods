package com.google.android.material.timepicker;

import android.os.Build;
import android.view.accessibility.AccessibilityManager;
import androidx.core.content.ContextCompat;
import com.google.android.material.R;
import com.google.android.material.timepicker.ClockHandView;
import com.google.android.material.timepicker.TimePickerView;

/* loaded from: classes.dex */
class c implements ClockHandView.OnRotateListener, TimePickerView.g, TimePickerView.f, ClockHandView.OnActionUpListener, d {
    private static final String[] f = {"12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    private static final String[] g = {"00", "2", "4", "6", "8", "10", "12", "14", "16", "18", "20", "22"};
    private static final String[] h = {"00", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};
    private TimePickerView a;
    private TimeModel b;

    /* renamed from: c, reason: collision with root package name */
    private float f1744c;

    /* renamed from: d, reason: collision with root package name */
    private float f1745d;
    private boolean e = false;

    public c(TimePickerView timePickerView, TimeModel timeModel) {
        this.a = timePickerView;
        this.b = timeModel;
        g();
    }

    private int e() {
        return this.b.f1741c == 1 ? 15 : 30;
    }

    private String[] f() {
        return this.b.f1741c == 1 ? g : f;
    }

    private void h(int i2, int i3) {
        TimeModel timeModel = this.b;
        if (timeModel.e == i3 && timeModel.f1742d == i2) {
            return;
        }
        this.a.performHapticFeedback(Build.VERSION.SDK_INT >= 21 ? 4 : 1);
    }

    private void j() {
        TimePickerView timePickerView = this.a;
        TimeModel timeModel = this.b;
        timePickerView.F(timeModel.g, timeModel.c(), this.b.e);
    }

    private void k() {
        l(f, "%d");
        l(g, "%d");
        l(h, "%02d");
    }

    private void l(String[] strArr, String str) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            strArr[i2] = TimeModel.b(this.a.getResources(), strArr[i2], str);
        }
    }

    @Override // com.google.android.material.timepicker.d
    public void a() {
        this.f1745d = this.b.c() * e();
        TimeModel timeModel = this.b;
        this.f1744c = timeModel.e * 6;
        i(timeModel.f, false);
        j();
    }

    @Override // com.google.android.material.timepicker.TimePickerView.g
    public void b(int i2) {
        i(i2, true);
    }

    @Override // com.google.android.material.timepicker.TimePickerView.f
    public void c(int i2) {
        this.b.j(i2);
    }

    @Override // com.google.android.material.timepicker.d
    public void d() {
        this.a.setVisibility(8);
    }

    public void g() {
        if (this.b.f1741c == 0) {
            this.a.E();
        }
        this.a.r(this);
        this.a.A(this);
        this.a.z(this);
        this.a.x(this);
        k();
        a();
    }

    void i(int i2, boolean z) {
        boolean z2 = i2 == 12;
        this.a.t(z2);
        this.b.f = i2;
        this.a.C(z2 ? h : f(), z2 ? R.string.material_minute_suffix : R.string.material_hour_suffix);
        this.a.u(z2 ? this.f1744c : this.f1745d, z);
        this.a.s(i2);
        this.a.w(new a(this.a.getContext(), R.string.material_hour_selection));
        this.a.v(new a(this.a.getContext(), R.string.material_minute_selection));
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnActionUpListener
    public void onActionUp(float f2, boolean z) {
        this.e = true;
        TimeModel timeModel = this.b;
        int i2 = timeModel.e;
        int i3 = timeModel.f1742d;
        if (timeModel.f == 10) {
            this.a.u(this.f1745d, false);
            if (!((AccessibilityManager) ContextCompat.getSystemService(this.a.getContext(), AccessibilityManager.class)).isTouchExplorationEnabled()) {
                i(12, true);
            }
        } else {
            int round = Math.round(f2);
            if (!z) {
                this.b.i(((round + 15) / 30) * 5);
                this.f1744c = this.b.e * 6;
            }
            this.a.u(this.f1744c, z);
        }
        this.e = false;
        j();
        h(i3, i2);
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnRotateListener
    public void onRotate(float f2, boolean z) {
        if (this.e) {
            return;
        }
        TimeModel timeModel = this.b;
        int i2 = timeModel.f1742d;
        int i3 = timeModel.e;
        int round = Math.round(f2);
        TimeModel timeModel2 = this.b;
        if (timeModel2.f == 12) {
            timeModel2.i((round + 3) / 6);
            this.f1744c = (float) Math.floor(this.b.e * 6);
        } else {
            this.b.g((round + (e() / 2)) / e());
            this.f1745d = this.b.c() * e();
        }
        if (z) {
            return;
        }
        j();
        h(i2, i3);
    }

    @Override // com.google.android.material.timepicker.d
    public void show() {
        this.a.setVisibility(0);
    }
}
