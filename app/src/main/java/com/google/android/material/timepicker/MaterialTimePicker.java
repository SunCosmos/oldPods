package com.google.android.material.timepicker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.timepicker.TimePickerView;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes.dex */
public final class MaterialTimePicker extends DialogFragment {
    public static final int INPUT_MODE_CLOCK = 0;
    public static final int INPUT_MODE_KEYBOARD = 1;

    @DrawableRes
    private int A0;

    @DrawableRes
    private int B0;
    private String D0;
    private MaterialButton E0;
    private TimeModel G0;
    private TimePickerView v0;
    private ViewStub w0;

    @Nullable
    private com.google.android.material.timepicker.c x0;

    @Nullable
    private f y0;

    @Nullable
    private com.google.android.material.timepicker.d z0;
    private final Set<View.OnClickListener> r0 = new LinkedHashSet();
    private final Set<View.OnClickListener> s0 = new LinkedHashSet();
    private final Set<DialogInterface.OnCancelListener> t0 = new LinkedHashSet();
    private final Set<DialogInterface.OnDismissListener> u0 = new LinkedHashSet();
    private int C0 = 0;
    private int F0 = 0;
    private int H0 = 0;

    /* loaded from: classes.dex */
    public static final class Builder {
        private int b;

        /* renamed from: d, reason: collision with root package name */
        private CharSequence f1740d;
        private TimeModel a = new TimeModel();

        /* renamed from: c, reason: collision with root package name */
        private int f1739c = 0;
        private int e = 0;

        @NonNull
        public MaterialTimePicker build() {
            return MaterialTimePicker.G0(this);
        }

        @NonNull
        public Builder setHour(@IntRange(from = 0, to = 23) int i2) {
            this.a.h(i2);
            return this;
        }

        @NonNull
        public Builder setInputMode(int i2) {
            this.b = i2;
            return this;
        }

        @NonNull
        public Builder setMinute(@IntRange(from = 0, to = 60) int i2) {
            this.a.i(i2);
            return this;
        }

        @NonNull
        public Builder setTheme(@StyleRes int i2) {
            this.e = i2;
            return this;
        }

        @NonNull
        public Builder setTimeFormat(int i2) {
            TimeModel timeModel = this.a;
            int i3 = timeModel.f1742d;
            int i4 = timeModel.e;
            TimeModel timeModel2 = new TimeModel(i2);
            this.a = timeModel2;
            timeModel2.i(i4);
            this.a.h(i3);
            return this;
        }

        @NonNull
        public Builder setTitleText(@StringRes int i2) {
            this.f1739c = i2;
            return this;
        }

        @NonNull
        public Builder setTitleText(@Nullable CharSequence charSequence) {
            this.f1740d = charSequence;
            return this;
        }
    }

    /* loaded from: classes.dex */
    class a implements TimePickerView.e {
        a() {
        }

        @Override // com.google.android.material.timepicker.TimePickerView.e
        public void a() {
            MaterialTimePicker.this.F0 = 1;
            MaterialTimePicker materialTimePicker = MaterialTimePicker.this;
            materialTimePicker.I0(materialTimePicker.E0);
            MaterialTimePicker.this.y0.i();
        }
    }

    /* loaded from: classes.dex */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Iterator it = MaterialTimePicker.this.r0.iterator();
            while (it.hasNext()) {
                ((View.OnClickListener) it.next()).onClick(view);
            }
            MaterialTimePicker.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Iterator it = MaterialTimePicker.this.s0.iterator();
            while (it.hasNext()) {
                ((View.OnClickListener) it.next()).onClick(view);
            }
            MaterialTimePicker.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MaterialTimePicker materialTimePicker = MaterialTimePicker.this;
            materialTimePicker.F0 = materialTimePicker.F0 == 0 ? 1 : 0;
            MaterialTimePicker materialTimePicker2 = MaterialTimePicker.this;
            materialTimePicker2.I0(materialTimePicker2.E0);
        }
    }

    private Pair<Integer, Integer> D0(int i2) {
        if (i2 == 0) {
            return new Pair<>(Integer.valueOf(this.A0), Integer.valueOf(R.string.material_timepicker_text_input_mode_description));
        }
        if (i2 == 1) {
            return new Pair<>(Integer.valueOf(this.B0), Integer.valueOf(R.string.material_timepicker_clock_mode_description));
        }
        throw new IllegalArgumentException("no icon for mode: " + i2);
    }

    private int E0() {
        int i2 = this.H0;
        if (i2 != 0) {
            return i2;
        }
        TypedValue resolve = MaterialAttributes.resolve(requireContext(), R.attr.materialTimePickerTheme);
        if (resolve == null) {
            return 0;
        }
        return resolve.data;
    }

    private com.google.android.material.timepicker.d F0(int i2) {
        if (i2 != 0) {
            if (this.y0 == null) {
                this.y0 = new f((LinearLayout) this.w0.inflate(), this.G0);
            }
            this.y0.f();
            return this.y0;
        }
        com.google.android.material.timepicker.c cVar = this.x0;
        if (cVar == null) {
            cVar = new com.google.android.material.timepicker.c(this.v0, this.G0);
        }
        this.x0 = cVar;
        return cVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public static MaterialTimePicker G0(@NonNull Builder builder) {
        MaterialTimePicker materialTimePicker = new MaterialTimePicker();
        Bundle bundle = new Bundle();
        bundle.putParcelable("TIME_PICKER_TIME_MODEL", builder.a);
        bundle.putInt("TIME_PICKER_INPUT_MODE", builder.b);
        bundle.putInt("TIME_PICKER_TITLE_RES", builder.f1739c);
        bundle.putInt("TIME_PICKER_OVERRIDE_THEME_RES_ID", builder.e);
        if (builder.f1740d != null) {
            bundle.putString("TIME_PICKER_TITLE_TEXT", builder.f1740d.toString());
        }
        materialTimePicker.setArguments(bundle);
        return materialTimePicker;
    }

    private void H0(@Nullable Bundle bundle) {
        if (bundle == null) {
            return;
        }
        TimeModel timeModel = (TimeModel) bundle.getParcelable("TIME_PICKER_TIME_MODEL");
        this.G0 = timeModel;
        if (timeModel == null) {
            this.G0 = new TimeModel();
        }
        this.F0 = bundle.getInt("TIME_PICKER_INPUT_MODE", 0);
        this.C0 = bundle.getInt("TIME_PICKER_TITLE_RES", 0);
        this.D0 = bundle.getString("TIME_PICKER_TITLE_TEXT");
        this.H0 = bundle.getInt("TIME_PICKER_OVERRIDE_THEME_RES_ID", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I0(MaterialButton materialButton) {
        com.google.android.material.timepicker.d dVar = this.z0;
        if (dVar != null) {
            dVar.d();
        }
        com.google.android.material.timepicker.d F0 = F0(this.F0);
        this.z0 = F0;
        F0.show();
        this.z0.a();
        Pair<Integer, Integer> D0 = D0(this.F0);
        materialButton.setIconResource(((Integer) D0.first).intValue());
        materialButton.setContentDescription(getResources().getString(((Integer) D0.second).intValue()));
    }

    public boolean addOnCancelListener(@NonNull DialogInterface.OnCancelListener onCancelListener) {
        return this.t0.add(onCancelListener);
    }

    public boolean addOnDismissListener(@NonNull DialogInterface.OnDismissListener onDismissListener) {
        return this.u0.add(onDismissListener);
    }

    public boolean addOnNegativeButtonClickListener(@NonNull View.OnClickListener onClickListener) {
        return this.s0.add(onClickListener);
    }

    public boolean addOnPositiveButtonClickListener(@NonNull View.OnClickListener onClickListener) {
        return this.r0.add(onClickListener);
    }

    public void clearOnCancelListeners() {
        this.t0.clear();
    }

    public void clearOnDismissListeners() {
        this.u0.clear();
    }

    public void clearOnNegativeButtonClickListeners() {
        this.s0.clear();
    }

    public void clearOnPositiveButtonClickListeners() {
        this.r0.clear();
    }

    @IntRange(from = 0, to = 23)
    public int getHour() {
        return this.G0.f1742d % 24;
    }

    public int getInputMode() {
        return this.F0;
    }

    @IntRange(from = 0, to = 60)
    public int getMinute() {
        return this.G0.e;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(@NonNull DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnCancelListener> it = this.t0.iterator();
        while (it.hasNext()) {
            it.next().onCancel(dialogInterface);
        }
        super.onCancel(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        H0(bundle);
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public final Dialog onCreateDialog(@Nullable Bundle bundle) {
        Dialog dialog = new Dialog(requireContext(), E0());
        Context context = dialog.getContext();
        int resolveOrThrow = MaterialAttributes.resolveOrThrow(context, R.attr.colorSurface, MaterialTimePicker.class.getCanonicalName());
        int i2 = R.attr.materialTimePickerStyle;
        int i3 = R.style.Widget_MaterialComponents_TimePicker;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(context, null, i2, i3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, R.styleable.MaterialTimePicker, i2, i3);
        this.B0 = obtainStyledAttributes.getResourceId(R.styleable.MaterialTimePicker_clockIcon, 0);
        this.A0 = obtainStyledAttributes.getResourceId(R.styleable.MaterialTimePicker_keyboardIcon, 0);
        obtainStyledAttributes.recycle();
        materialShapeDrawable.initializeElevationOverlay(context);
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(resolveOrThrow));
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(materialShapeDrawable);
        window.requestFeature(1);
        window.setLayout(-2, -2);
        return dialog;
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public final View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.material_timepicker_dialog, viewGroup);
        TimePickerView timePickerView = (TimePickerView) viewGroup2.findViewById(R.id.material_timepicker_view);
        this.v0 = timePickerView;
        timePickerView.y(new a());
        this.w0 = (ViewStub) viewGroup2.findViewById(R.id.material_textinput_timepicker);
        this.E0 = (MaterialButton) viewGroup2.findViewById(R.id.material_timepicker_mode_button);
        TextView textView = (TextView) viewGroup2.findViewById(R.id.header_title);
        if (!TextUtils.isEmpty(this.D0)) {
            textView.setText(this.D0);
        }
        int i2 = this.C0;
        if (i2 != 0) {
            textView.setText(i2);
        }
        I0(this.E0);
        ((Button) viewGroup2.findViewById(R.id.material_timepicker_ok_button)).setOnClickListener(new b());
        ((Button) viewGroup2.findViewById(R.id.material_timepicker_cancel_button)).setOnClickListener(new c());
        this.E0.setOnClickListener(new d());
        return viewGroup2;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(@NonNull DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnDismissListener> it = this.u0.iterator();
        while (it.hasNext()) {
            it.next().onDismiss(dialogInterface);
        }
        super.onDismiss(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("TIME_PICKER_TIME_MODEL", this.G0);
        bundle.putInt("TIME_PICKER_INPUT_MODE", this.F0);
        bundle.putInt("TIME_PICKER_TITLE_RES", this.C0);
        bundle.putString("TIME_PICKER_TITLE_TEXT", this.D0);
        bundle.putInt("TIME_PICKER_OVERRIDE_THEME_RES_ID", this.H0);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.z0 = null;
        this.x0 = null;
        this.y0 = null;
        this.v0 = null;
    }

    public boolean removeOnCancelListener(@NonNull DialogInterface.OnCancelListener onCancelListener) {
        return this.t0.remove(onCancelListener);
    }

    public boolean removeOnDismissListener(@NonNull DialogInterface.OnDismissListener onDismissListener) {
        return this.u0.remove(onDismissListener);
    }

    public boolean removeOnNegativeButtonClickListener(@NonNull View.OnClickListener onClickListener) {
        return this.s0.remove(onClickListener);
    }

    public boolean removeOnPositiveButtonClickListener(@NonNull View.OnClickListener onClickListener) {
        return this.r0.remove(onClickListener);
    }
}
