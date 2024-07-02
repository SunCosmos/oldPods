package com.google.android.material.datepicker;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.dialog.InsetDialogOnTouchListener;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public final class MaterialDatePicker<S> extends DialogFragment {
    public static final int INPUT_MODE_CALENDAR = 0;
    public static final int INPUT_MODE_TEXT = 1;

    @StringRes
    private int A0;
    private CharSequence B0;
    private boolean C0;
    private int D0;
    private TextView E0;
    private CheckableImageButton F0;

    @Nullable
    private MaterialShapeDrawable G0;
    private Button H0;
    private final LinkedHashSet<MaterialPickerOnPositiveButtonClickListener<? super S>> r0 = new LinkedHashSet<>();
    private final LinkedHashSet<View.OnClickListener> s0 = new LinkedHashSet<>();
    private final LinkedHashSet<DialogInterface.OnCancelListener> t0 = new LinkedHashSet<>();
    private final LinkedHashSet<DialogInterface.OnDismissListener> u0 = new LinkedHashSet<>();

    @StyleRes
    private int v0;

    @Nullable
    private DateSelector<S> w0;
    private g<S> x0;

    @Nullable
    private CalendarConstraints y0;
    private MaterialCalendar<S> z0;
    static final Object I0 = "CONFIRM_BUTTON_TAG";
    static final Object J0 = "CANCEL_BUTTON_TAG";
    static final Object K0 = "TOGGLE_BUTTON_TAG";

    /* loaded from: classes.dex */
    public static final class Builder<S> {
        final DateSelector<S> a;

        /* renamed from: c */
        CalendarConstraints f1538c;
        int b = 0;

        /* renamed from: d */
        int f1539d = 0;
        CharSequence e = null;

        @Nullable
        S f = null;
        int g = 0;

        private Builder(DateSelector<S> dateSelector) {
            this.a = dateSelector;
        }

        private Month a() {
            long j = this.f1538c.i().f;
            long j2 = this.f1538c.f().f;
            if (!this.a.getSelectedDays().isEmpty()) {
                long longValue = this.a.getSelectedDays().iterator().next().longValue();
                if (longValue >= j && longValue <= j2) {
                    return Month.c(longValue);
                }
            }
            long thisMonthInUtcMilliseconds = MaterialDatePicker.thisMonthInUtcMilliseconds();
            if (j <= thisMonthInUtcMilliseconds && thisMonthInUtcMilliseconds <= j2) {
                j = thisMonthInUtcMilliseconds;
            }
            return Month.c(j);
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static <S> Builder<S> customDatePicker(@NonNull DateSelector<S> dateSelector) {
            return new Builder<>(dateSelector);
        }

        @NonNull
        public static Builder<Long> datePicker() {
            return new Builder<>(new SingleDateSelector());
        }

        @NonNull
        public static Builder<Pair<Long, Long>> dateRangePicker() {
            return new Builder<>(new RangeDateSelector());
        }

        @NonNull
        public MaterialDatePicker<S> build() {
            if (this.f1538c == null) {
                this.f1538c = new CalendarConstraints.Builder().build();
            }
            if (this.f1539d == 0) {
                this.f1539d = this.a.getDefaultTitleResId();
            }
            S s = this.f;
            if (s != null) {
                this.a.setSelection(s);
            }
            if (this.f1538c.h() == null) {
                this.f1538c.l(a());
            }
            return MaterialDatePicker.K0(this);
        }

        @NonNull
        public Builder<S> setCalendarConstraints(CalendarConstraints calendarConstraints) {
            this.f1538c = calendarConstraints;
            return this;
        }

        @NonNull
        public Builder<S> setInputMode(int i2) {
            this.g = i2;
            return this;
        }

        @NonNull
        public Builder<S> setSelection(S s) {
            this.f = s;
            return this;
        }

        @NonNull
        public Builder<S> setTheme(@StyleRes int i2) {
            this.b = i2;
            return this;
        }

        @NonNull
        public Builder<S> setTitleText(@StringRes int i2) {
            this.f1539d = i2;
            this.e = null;
            return this;
        }

        @NonNull
        public Builder<S> setTitleText(@Nullable CharSequence charSequence) {
            this.e = charSequence;
            this.f1539d = 0;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface InputMode {
    }

    /* loaded from: classes.dex */
    class a implements View.OnClickListener {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Iterator it = MaterialDatePicker.this.r0.iterator();
            while (it.hasNext()) {
                ((MaterialPickerOnPositiveButtonClickListener) it.next()).onPositiveButtonClick(MaterialDatePicker.this.getSelection());
            }
            MaterialDatePicker.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Iterator it = MaterialDatePicker.this.s0.iterator();
            while (it.hasNext()) {
                ((View.OnClickListener) it.next()).onClick(view);
            }
            MaterialDatePicker.this.dismiss();
        }
    }

    /* loaded from: classes.dex */
    public class c extends OnSelectionChangedListener<S> {
        c() {
        }

        @Override // com.google.android.material.datepicker.OnSelectionChangedListener
        public void onIncompleteSelectionChanged() {
            MaterialDatePicker.this.H0.setEnabled(false);
        }

        @Override // com.google.android.material.datepicker.OnSelectionChangedListener
        public void onSelectionChanged(S s) {
            MaterialDatePicker.this.N0();
            MaterialDatePicker.this.H0.setEnabled(MaterialDatePicker.this.w0.isSelectionComplete());
        }
    }

    /* loaded from: classes.dex */
    public class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MaterialDatePicker.this.H0.setEnabled(MaterialDatePicker.this.w0.isSelectionComplete());
            MaterialDatePicker.this.F0.toggle();
            MaterialDatePicker materialDatePicker = MaterialDatePicker.this;
            materialDatePicker.O0(materialDatePicker.F0);
            MaterialDatePicker.this.M0();
        }
    }

    @NonNull
    private static Drawable D0(Context context) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_checked}, AppCompatResources.getDrawable(context, com.google.android.material.R.drawable.material_ic_calendar_black_24dp));
        stateListDrawable.addState(new int[0], AppCompatResources.getDrawable(context, com.google.android.material.R.drawable.material_ic_edit_black_24dp));
        return stateListDrawable;
    }

    private static int E0(@NonNull Context context) {
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(com.google.android.material.R.dimen.mtrl_calendar_navigation_height) + resources.getDimensionPixelOffset(com.google.android.material.R.dimen.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelOffset(com.google.android.material.R.dimen.mtrl_calendar_navigation_bottom_padding);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(com.google.android.material.R.dimen.mtrl_calendar_days_of_week_height);
        int i2 = f.f;
        return dimensionPixelSize + dimensionPixelSize2 + (resources.getDimensionPixelSize(com.google.android.material.R.dimen.mtrl_calendar_day_height) * i2) + ((i2 - 1) * resources.getDimensionPixelOffset(com.google.android.material.R.dimen.mtrl_calendar_month_vertical_padding)) + resources.getDimensionPixelOffset(com.google.android.material.R.dimen.mtrl_calendar_bottom_padding);
    }

    private static int F0(@NonNull Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(com.google.android.material.R.dimen.mtrl_calendar_content_padding);
        int i2 = Month.d().f1542d;
        return (dimensionPixelOffset * 2) + (resources.getDimensionPixelSize(com.google.android.material.R.dimen.mtrl_calendar_day_width) * i2) + ((i2 - 1) * resources.getDimensionPixelOffset(com.google.android.material.R.dimen.mtrl_calendar_month_horizontal_padding));
    }

    private int G0(Context context) {
        int i2 = this.v0;
        return i2 != 0 ? i2 : this.w0.getDefaultThemeResId(context);
    }

    private void H0(Context context) {
        this.F0.setTag(K0);
        this.F0.setImageDrawable(D0(context));
        this.F0.setChecked(this.D0 != 0);
        ViewCompat.setAccessibilityDelegate(this.F0, null);
        O0(this.F0);
        this.F0.setOnClickListener(new d());
    }

    public static boolean I0(@NonNull Context context) {
        return L0(context, R.attr.windowFullscreen);
    }

    public static boolean J0(@NonNull Context context) {
        return L0(context, com.google.android.material.R.attr.nestedScrollable);
    }

    @NonNull
    static <S> MaterialDatePicker<S> K0(@NonNull Builder<S> builder) {
        MaterialDatePicker<S> materialDatePicker = new MaterialDatePicker<>();
        Bundle bundle = new Bundle();
        bundle.putInt("OVERRIDE_THEME_RES_ID", builder.b);
        bundle.putParcelable("DATE_SELECTOR_KEY", builder.a);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", builder.f1538c);
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", builder.f1539d);
        bundle.putCharSequence("TITLE_TEXT_KEY", builder.e);
        bundle.putInt("INPUT_MODE_KEY", builder.g);
        materialDatePicker.setArguments(bundle);
        return materialDatePicker;
    }

    static boolean L0(@NonNull Context context, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(MaterialAttributes.resolveOrThrow(context, com.google.android.material.R.attr.materialCalendarStyle, MaterialCalendar.class.getCanonicalName()), new int[]{i2});
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return z;
    }

    public void M0() {
        int G0 = G0(requireContext());
        this.z0 = MaterialCalendar.newInstance(this.w0, G0, this.y0);
        this.x0 = this.F0.isChecked() ? MaterialTextInputPicker.p0(this.w0, G0, this.y0) : this.z0;
        N0();
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.replace(com.google.android.material.R.id.mtrl_calendar_frame, this.x0);
        beginTransaction.commitNow();
        this.x0.addOnSelectionChangedListener(new c());
    }

    public void N0() {
        String headerText = getHeaderText();
        this.E0.setContentDescription(String.format(getString(com.google.android.material.R.string.mtrl_picker_announce_current_selection), headerText));
        this.E0.setText(headerText);
    }

    public void O0(@NonNull CheckableImageButton checkableImageButton) {
        this.F0.setContentDescription(checkableImageButton.getContext().getString(this.F0.isChecked() ? com.google.android.material.R.string.mtrl_picker_toggle_to_calendar_input_mode : com.google.android.material.R.string.mtrl_picker_toggle_to_text_input_mode));
    }

    public static long thisMonthInUtcMilliseconds() {
        return Month.d().f;
    }

    public static long todayInUtcMilliseconds() {
        return j.o().getTimeInMillis();
    }

    public boolean addOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        return this.t0.add(onCancelListener);
    }

    public boolean addOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        return this.u0.add(onDismissListener);
    }

    public boolean addOnNegativeButtonClickListener(View.OnClickListener onClickListener) {
        return this.s0.add(onClickListener);
    }

    public boolean addOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener<? super S> materialPickerOnPositiveButtonClickListener) {
        return this.r0.add(materialPickerOnPositiveButtonClickListener);
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

    public String getHeaderText() {
        return this.w0.getSelectionDisplayString(getContext());
    }

    @Nullable
    public final S getSelection() {
        return this.w0.getSelection();
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
    public final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.v0 = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.w0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.y0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.A0 = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.B0 = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.D0 = bundle.getInt("INPUT_MODE_KEY");
    }

    @Override // androidx.fragment.app.DialogFragment
    @NonNull
    public final Dialog onCreateDialog(@Nullable Bundle bundle) {
        Dialog dialog = new Dialog(requireContext(), G0(requireContext()));
        Context context = dialog.getContext();
        this.C0 = I0(context);
        int resolveOrThrow = MaterialAttributes.resolveOrThrow(context, com.google.android.material.R.attr.colorSurface, MaterialDatePicker.class.getCanonicalName());
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(context, null, com.google.android.material.R.attr.materialCalendarStyle, com.google.android.material.R.style.Widget_MaterialComponents_MaterialCalendar);
        this.G0 = materialShapeDrawable;
        materialShapeDrawable.initializeElevationOverlay(context);
        this.G0.setFillColor(ColorStateList.valueOf(resolveOrThrow));
        this.G0.setElevation(ViewCompat.getElevation(dialog.getWindow().getDecorView()));
        return dialog;
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public final View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(this.C0 ? com.google.android.material.R.layout.mtrl_picker_fullscreen : com.google.android.material.R.layout.mtrl_picker_dialog, viewGroup);
        Context context = inflate.getContext();
        if (this.C0) {
            inflate.findViewById(com.google.android.material.R.id.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(F0(context), -2));
        } else {
            View findViewById = inflate.findViewById(com.google.android.material.R.id.mtrl_calendar_main_pane);
            View findViewById2 = inflate.findViewById(com.google.android.material.R.id.mtrl_calendar_frame);
            findViewById.setLayoutParams(new LinearLayout.LayoutParams(F0(context), -1));
            findViewById2.setMinimumHeight(E0(requireContext()));
        }
        TextView textView = (TextView) inflate.findViewById(com.google.android.material.R.id.mtrl_picker_header_selection_text);
        this.E0 = textView;
        ViewCompat.setAccessibilityLiveRegion(textView, 1);
        this.F0 = (CheckableImageButton) inflate.findViewById(com.google.android.material.R.id.mtrl_picker_header_toggle);
        TextView textView2 = (TextView) inflate.findViewById(com.google.android.material.R.id.mtrl_picker_title_text);
        CharSequence charSequence = this.B0;
        if (charSequence != null) {
            textView2.setText(charSequence);
        } else {
            textView2.setText(this.A0);
        }
        H0(context);
        this.H0 = (Button) inflate.findViewById(com.google.android.material.R.id.confirm_button);
        if (this.w0.isSelectionComplete()) {
            this.H0.setEnabled(true);
        } else {
            this.H0.setEnabled(false);
        }
        this.H0.setTag(I0);
        this.H0.setOnClickListener(new a());
        Button button = (Button) inflate.findViewById(com.google.android.material.R.id.cancel_button);
        button.setTag(J0);
        button.setOnClickListener(new b());
        return inflate;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(@NonNull DialogInterface dialogInterface) {
        Iterator<DialogInterface.OnDismissListener> it = this.u0.iterator();
        while (it.hasNext()) {
            it.next().onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) getView();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.v0);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.w0);
        CalendarConstraints.Builder builder = new CalendarConstraints.Builder(this.y0);
        if (this.z0.A0() != null) {
            builder.setOpenAt(this.z0.A0().f);
        }
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", builder.build());
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.A0);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.B0);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Window window = requireDialog().getWindow();
        if (this.C0) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.G0);
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(com.google.android.material.R.dimen.mtrl_calendar_dialog_background_inset);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.G0, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new InsetDialogOnTouchListener(requireDialog(), rect));
        }
        M0();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        this.x0.o0();
        super.onStop();
    }

    public boolean removeOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        return this.t0.remove(onCancelListener);
    }

    public boolean removeOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        return this.u0.remove(onDismissListener);
    }

    public boolean removeOnNegativeButtonClickListener(View.OnClickListener onClickListener) {
        return this.s0.remove(onClickListener);
    }

    public boolean removeOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener<? super S> materialPickerOnPositiveButtonClickListener) {
        return this.r0.remove(materialPickerOnPositiveButtonClickListener);
    }
}
