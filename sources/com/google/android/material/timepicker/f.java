package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.timepicker.TimePickerView;
import java.lang.reflect.Field;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class f implements TimePickerView.g, com.google.android.material.timepicker.d {
    private final LinearLayout a;
    private final TimeModel b;

    /* renamed from: c, reason: collision with root package name */
    private final TextWatcher f1748c = new a();

    /* renamed from: d, reason: collision with root package name */
    private final TextWatcher f1749d = new b();
    private final ChipTextInputComboView e;
    private final ChipTextInputComboView f;
    private final e g;
    private final EditText h;

    /* renamed from: i, reason: collision with root package name */
    private final EditText f1750i;
    private MaterialButtonToggleGroup j;

    /* loaded from: classes.dex */
    class a extends TextWatcherAdapter {
        a() {
        }

        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            try {
                if (TextUtils.isEmpty(editable)) {
                    f.this.b.i(0);
                } else {
                    f.this.b.i(Integer.parseInt(editable.toString()));
                }
            } catch (NumberFormatException unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    class b extends TextWatcherAdapter {
        b() {
        }

        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            try {
                if (TextUtils.isEmpty(editable)) {
                    f.this.b.g(0);
                } else {
                    f.this.b.g(Integer.parseInt(editable.toString()));
                }
            } catch (NumberFormatException unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.this.b(((Integer) view.getTag(R.id.selection_type)).intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class d implements MaterialButtonToggleGroup.OnButtonCheckedListener {
        d() {
        }

        @Override // com.google.android.material.button.MaterialButtonToggleGroup.OnButtonCheckedListener
        public void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i2, boolean z) {
            f.this.b.j(i2 == R.id.material_clock_period_pm_button ? 1 : 0);
        }
    }

    public f(LinearLayout linearLayout, TimeModel timeModel) {
        this.a = linearLayout;
        this.b = timeModel;
        Resources resources = linearLayout.getResources();
        ChipTextInputComboView chipTextInputComboView = (ChipTextInputComboView) linearLayout.findViewById(R.id.material_minute_text_input);
        this.e = chipTextInputComboView;
        ChipTextInputComboView chipTextInputComboView2 = (ChipTextInputComboView) linearLayout.findViewById(R.id.material_hour_text_input);
        this.f = chipTextInputComboView2;
        int i2 = R.id.material_label;
        TextView textView = (TextView) chipTextInputComboView.findViewById(i2);
        TextView textView2 = (TextView) chipTextInputComboView2.findViewById(i2);
        textView.setText(resources.getString(R.string.material_timepicker_minute));
        textView2.setText(resources.getString(R.string.material_timepicker_hour));
        int i3 = R.id.selection_type;
        chipTextInputComboView.setTag(i3, 12);
        chipTextInputComboView2.setTag(i3, 10);
        if (timeModel.f1741c == 0) {
            l();
        }
        c cVar = new c();
        chipTextInputComboView2.setOnClickListener(cVar);
        chipTextInputComboView.setOnClickListener(cVar);
        chipTextInputComboView2.c(timeModel.d());
        chipTextInputComboView.c(timeModel.e());
        EditText editText = chipTextInputComboView2.e().getEditText();
        this.h = editText;
        EditText editText2 = chipTextInputComboView.e().getEditText();
        this.f1750i = editText2;
        if (Build.VERSION.SDK_INT < 21) {
            int color = MaterialColors.getColor(linearLayout, R.attr.colorPrimary);
            j(editText, color);
            j(editText2, color);
        }
        this.g = new e(chipTextInputComboView2, chipTextInputComboView, timeModel);
        chipTextInputComboView2.f(new com.google.android.material.timepicker.a(linearLayout.getContext(), R.string.material_hour_selection));
        chipTextInputComboView.f(new com.google.android.material.timepicker.a(linearLayout.getContext(), R.string.material_minute_selection));
        g();
    }

    private void e() {
        this.h.addTextChangedListener(this.f1749d);
        this.f1750i.addTextChangedListener(this.f1748c);
    }

    private void h() {
        this.h.removeTextChangedListener(this.f1749d);
        this.f1750i.removeTextChangedListener(this.f1748c);
    }

    private static void j(EditText editText, @ColorInt int i2) {
        try {
            Context context = editText.getContext();
            Field declaredField = TextView.class.getDeclaredField("mCursorDrawableRes");
            declaredField.setAccessible(true);
            int i3 = declaredField.getInt(editText);
            Field declaredField2 = TextView.class.getDeclaredField("mEditor");
            declaredField2.setAccessible(true);
            Object obj = declaredField2.get(editText);
            Field declaredField3 = obj.getClass().getDeclaredField("mCursorDrawable");
            declaredField3.setAccessible(true);
            Drawable drawable = AppCompatResources.getDrawable(context, i3);
            drawable.setColorFilter(i2, PorterDuff.Mode.SRC_IN);
            declaredField3.set(obj, new Drawable[]{drawable, drawable});
        } catch (Throwable unused) {
        }
    }

    private void k(TimeModel timeModel) {
        h();
        Locale locale = this.a.getResources().getConfiguration().locale;
        String format = String.format(locale, "%02d", Integer.valueOf(timeModel.e));
        String format2 = String.format(locale, "%02d", Integer.valueOf(timeModel.c()));
        this.e.g(format);
        this.f.g(format2);
        e();
        m();
    }

    private void l() {
        MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) this.a.findViewById(R.id.material_clock_period_toggle);
        this.j = materialButtonToggleGroup;
        materialButtonToggleGroup.addOnButtonCheckedListener(new d());
        this.j.setVisibility(0);
        m();
    }

    private void m() {
        MaterialButtonToggleGroup materialButtonToggleGroup = this.j;
        if (materialButtonToggleGroup == null) {
            return;
        }
        materialButtonToggleGroup.check(this.b.g == 0 ? R.id.material_clock_period_am_button : R.id.material_clock_period_pm_button);
    }

    @Override // com.google.android.material.timepicker.d
    public void a() {
        k(this.b);
    }

    @Override // com.google.android.material.timepicker.TimePickerView.g
    public void b(int i2) {
        this.b.f = i2;
        this.e.setChecked(i2 == 12);
        this.f.setChecked(i2 == 10);
        m();
    }

    @Override // com.google.android.material.timepicker.d
    public void d() {
        InputMethodManager inputMethodManager;
        View focusedChild = this.a.getFocusedChild();
        if (focusedChild != null && (inputMethodManager = (InputMethodManager) ContextCompat.getSystemService(this.a.getContext(), InputMethodManager.class)) != null) {
            inputMethodManager.hideSoftInputFromWindow(focusedChild.getWindowToken(), 0);
        }
        this.a.setVisibility(8);
    }

    public void f() {
        this.e.setChecked(false);
        this.f.setChecked(false);
    }

    public void g() {
        e();
        k(this.b);
        this.g.a();
    }

    public void i() {
        this.e.setChecked(this.b.f == 12);
        this.f.setChecked(this.b.f == 10);
    }

    @Override // com.google.android.material.timepicker.d
    public void show() {
        this.a.setVisibility(0);
    }
}
