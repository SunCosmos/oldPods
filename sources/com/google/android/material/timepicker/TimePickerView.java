package com.google.android.material.timepicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Locale;

/* loaded from: classes.dex */
class TimePickerView extends ConstraintLayout {
    private g A;
    private e B;
    private final Chip t;
    private final Chip u;
    private final ClockHandView v;
    private final ClockFaceView w;
    private final MaterialButtonToggleGroup x;
    private final View.OnClickListener y;
    private f z;

    /* loaded from: classes.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TimePickerView.this.A != null) {
                TimePickerView.this.A.b(((Integer) view.getTag(R.id.selection_type)).intValue());
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements MaterialButtonToggleGroup.OnButtonCheckedListener {
        b() {
        }

        @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
        public void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i2, boolean z) {
            int i3 = i2 == R.id.material_clock_period_pm_button ? 1 : 0;
            if (TimePickerView.this.z == null || !z) {
                return;
            }
            TimePickerView.this.z.c(i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends GestureDetector.SimpleOnGestureListener {
        c() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            boolean onDoubleTap = super.onDoubleTap(motionEvent);
            if (TimePickerView.this.B != null) {
                TimePickerView.this.B.a();
            }
            return onDoubleTap;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements View.OnTouchListener {
        final /* synthetic */ GestureDetector a;

        d(TimePickerView timePickerView, GestureDetector gestureDetector) {
            this.a = gestureDetector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (((Checkable) view).isChecked()) {
                return this.a.onTouchEvent(motionEvent);
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    interface e {
        void a();
    }

    /* loaded from: classes.dex */
    interface f {
        void c(int i2);
    }

    /* loaded from: classes.dex */
    interface g {
        void b(int i2);
    }

    public TimePickerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimePickerView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.y = new a();
        LayoutInflater.from(context).inflate(R.layout.material_timepicker, this);
        this.w = (ClockFaceView) findViewById(R.id.material_clock_face);
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) findViewById(R.id.material_clock_period_toggle);
        this.x = materialButtonToggleGroup;
        materialButtonToggleGroup.addOnButtonCheckedListener(new b());
        this.t = (Chip) findViewById(R.id.material_minute_tv);
        this.u = (Chip) findViewById(R.id.material_hour_tv);
        this.v = (ClockHandView) findViewById(R.id.material_clock_hand);
        D();
        B();
    }

    private void B() {
        Chip chip = this.t;
        int i2 = R.id.selection_type;
        chip.setTag(i2, 12);
        this.u.setTag(i2, 10);
        this.t.setOnClickListener(this.y);
        this.u.setOnClickListener(this.y);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void D() {
        d dVar = new d(this, new GestureDetector(getContext(), new c()));
        this.t.setOnTouchListener(dVar);
        this.u.setOnTouchListener(dVar);
    }

    private void G() {
        if (this.x.getVisibility() == 0) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this);
            constraintSet.clear(R.id.material_clock_display, ViewCompat.getLayoutDirection(this) == 0 ? 2 : 1);
            constraintSet.applyTo(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A(g gVar) {
        this.A = gVar;
    }

    public void C(String[] strArr, @StringRes int i2) {
        this.w.A(strArr, i2);
    }

    public void E() {
        this.x.setVisibility(0);
    }

    @SuppressLint({"DefaultLocale"})
    public void F(int i2, int i3, int i4) {
        this.x.check(i2 == 1 ? R.id.material_clock_period_pm_button : R.id.material_clock_period_am_button);
        Locale locale = getResources().getConfiguration().locale;
        String format = String.format(locale, "%02d", Integer.valueOf(i4));
        String format2 = String.format(locale, "%02d", Integer.valueOf(i3));
        this.t.setText(format);
        this.u.setText(format2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        G();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(@NonNull View view, int i2) {
        super.onVisibilityChanged(view, i2);
        if (view == this && i2 == 0) {
            G();
        }
    }

    public void r(ClockHandView.OnRotateListener onRotateListener) {
        this.v.b(onRotateListener);
    }

    public void s(int i2) {
        this.t.setChecked(i2 == 12);
        this.u.setChecked(i2 == 10);
    }

    public void t(boolean z) {
        this.v.j(z);
    }

    public void u(float f2, boolean z) {
        this.v.m(f2, z);
    }

    public void v(AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompat.setAccessibilityDelegate(this.t, accessibilityDelegateCompat);
    }

    public void w(AccessibilityDelegateCompat accessibilityDelegateCompat) {
        ViewCompat.setAccessibilityDelegate(this.u, accessibilityDelegateCompat);
    }

    public void x(ClockHandView.OnActionUpListener onActionUpListener) {
        this.v.o(onActionUpListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void y(@Nullable e eVar) {
        this.B = eVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void z(f fVar) {
        this.z = fVar;
    }
}
