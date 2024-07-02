package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.util.Pair;
import androidx.core.util.Preconditions;
import com.google.android.material.R;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class RangeDateSelector implements DateSelector<Pair<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new c();
    private String a;

    @Nullable
    private Long b = null;

    /* renamed from: c, reason: collision with root package name */
    @Nullable
    private Long f1545c = null;

    /* renamed from: d, reason: collision with root package name */
    @Nullable
    private Long f1546d = null;

    @Nullable
    private Long e = null;

    /* loaded from: classes.dex */
    class a extends com.google.android.material.datepicker.c {
        final /* synthetic */ TextInputLayout g;
        final /* synthetic */ TextInputLayout h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ OnSelectionChangedListener f1547i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, OnSelectionChangedListener onSelectionChangedListener) {
            super(str, dateFormat, textInputLayout, calendarConstraints);
            this.g = textInputLayout2;
            this.h = textInputLayout3;
            this.f1547i = onSelectionChangedListener;
        }

        @Override // com.google.android.material.datepicker.c
        void e() {
            RangeDateSelector.this.f1546d = null;
            RangeDateSelector.this.i(this.g, this.h, this.f1547i);
        }

        @Override // com.google.android.material.datepicker.c
        void f(@Nullable Long l) {
            RangeDateSelector.this.f1546d = l;
            RangeDateSelector.this.i(this.g, this.h, this.f1547i);
        }
    }

    /* loaded from: classes.dex */
    class b extends com.google.android.material.datepicker.c {
        final /* synthetic */ TextInputLayout g;
        final /* synthetic */ TextInputLayout h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ OnSelectionChangedListener f1548i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, OnSelectionChangedListener onSelectionChangedListener) {
            super(str, dateFormat, textInputLayout, calendarConstraints);
            this.g = textInputLayout2;
            this.h = textInputLayout3;
            this.f1548i = onSelectionChangedListener;
        }

        @Override // com.google.android.material.datepicker.c
        void e() {
            RangeDateSelector.this.e = null;
            RangeDateSelector.this.i(this.g, this.h, this.f1548i);
        }

        @Override // com.google.android.material.datepicker.c
        void f(@Nullable Long l) {
            RangeDateSelector.this.e = l;
            RangeDateSelector.this.i(this.g, this.h, this.f1548i);
        }
    }

    /* loaded from: classes.dex */
    static class c implements Parcelable.Creator<RangeDateSelector> {
        c() {
        }

        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector createFromParcel(@NonNull Parcel parcel) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.b = (Long) parcel.readValue(Long.class.getClassLoader());
            rangeDateSelector.f1545c = (Long) parcel.readValue(Long.class.getClassLoader());
            return rangeDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector[] newArray(int i2) {
            return new RangeDateSelector[i2];
        }
    }

    private void f(@NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2) {
        if (textInputLayout.getError() != null && this.a.contentEquals(textInputLayout.getError())) {
            textInputLayout.setError(null);
        }
        if (textInputLayout2.getError() == null || !" ".contentEquals(textInputLayout2.getError())) {
            return;
        }
        textInputLayout2.setError(null);
    }

    private boolean g(long j, long j2) {
        return j <= j2;
    }

    private void h(@NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2) {
        textInputLayout.setError(this.a);
        textInputLayout2.setError(" ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(@NonNull TextInputLayout textInputLayout, @NonNull TextInputLayout textInputLayout2, @NonNull OnSelectionChangedListener<Pair<Long, Long>> onSelectionChangedListener) {
        Long l = this.f1546d;
        if (l == null || this.e == null) {
            f(textInputLayout, textInputLayout2);
            onSelectionChangedListener.onIncompleteSelectionChanged();
        } else if (!g(l.longValue(), this.e.longValue())) {
            h(textInputLayout, textInputLayout2);
            onSelectionChangedListener.onIncompleteSelectionChanged();
        } else {
            this.b = this.f1546d;
            this.f1545c = this.e;
            onSelectionChangedListener.onSelectionChanged(getSelection());
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int getDefaultThemeResId(@NonNull Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return MaterialAttributes.resolveOrThrow(context, Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) > resources.getDimensionPixelSize(R.dimen.mtrl_calendar_maximum_default_fullscreen_minor_axis) ? R.attr.materialCalendarTheme : R.attr.materialCalendarFullscreenTheme, MaterialDatePicker.class.getCanonicalName());
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int getDefaultTitleResId() {
        return R.string.mtrl_picker_range_header_title;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    @NonNull
    public Collection<Long> getSelectedDays() {
        ArrayList arrayList = new ArrayList();
        Long l = this.b;
        if (l != null) {
            arrayList.add(l);
        }
        Long l2 = this.f1545c;
        if (l2 != null) {
            arrayList.add(l2);
        }
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    @NonNull
    public Collection<Pair<Long, Long>> getSelectedRanges() {
        if (this.b == null || this.f1545c == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Pair(this.b, this.f1545c));
        return arrayList;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.material.datepicker.DateSelector
    @NonNull
    public Pair<Long, Long> getSelection() {
        return new Pair<>(this.b, this.f1545c);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    @NonNull
    public String getSelectionDisplayString(@NonNull Context context) {
        Resources resources = context.getResources();
        Long l = this.b;
        if (l == null && this.f1545c == null) {
            return resources.getString(R.string.mtrl_picker_range_header_unselected);
        }
        Long l2 = this.f1545c;
        if (l2 == null) {
            return resources.getString(R.string.mtrl_picker_range_header_only_start_selected, d.c(l.longValue()));
        }
        if (l == null) {
            return resources.getString(R.string.mtrl_picker_range_header_only_end_selected, d.c(l2.longValue()));
        }
        Pair<String, String> a2 = d.a(l, l2);
        return resources.getString(R.string.mtrl_picker_range_header_selected, a2.first, a2.second);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public boolean isSelectionComplete() {
        Long l = this.b;
        return (l == null || this.f1545c == null || !g(l.longValue(), this.f1545c.longValue())) ? false : true;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public View onCreateTextInputView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle, CalendarConstraints calendarConstraints, @NonNull OnSelectionChangedListener<Pair<Long, Long>> onSelectionChangedListener) {
        View inflate = layoutInflater.inflate(R.layout.mtrl_picker_text_input_date_range, viewGroup, false);
        TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(R.id.mtrl_picker_text_input_range_start);
        TextInputLayout textInputLayout2 = (TextInputLayout) inflate.findViewById(R.id.mtrl_picker_text_input_range_end);
        EditText editText = textInputLayout.getEditText();
        EditText editText2 = textInputLayout2.getEditText();
        if (ManufacturerUtils.isDateInputKeyboardMissingSeparatorCharacters()) {
            editText.setInputType(17);
            editText2.setInputType(17);
        }
        this.a = inflate.getResources().getString(R.string.mtrl_picker_invalid_range);
        SimpleDateFormat k = j.k();
        Long l = this.b;
        if (l != null) {
            editText.setText(k.format(l));
            this.f1546d = this.b;
        }
        Long l2 = this.f1545c;
        if (l2 != null) {
            editText2.setText(k.format(l2));
            this.e = this.f1545c;
        }
        String l3 = j.l(inflate.getResources(), k);
        textInputLayout.setPlaceholderText(l3);
        textInputLayout2.setPlaceholderText(l3);
        editText.addTextChangedListener(new a(l3, k, textInputLayout, calendarConstraints, textInputLayout, textInputLayout2, onSelectionChangedListener));
        editText2.addTextChangedListener(new b(l3, k, textInputLayout2, calendarConstraints, textInputLayout, textInputLayout2, onSelectionChangedListener));
        ViewUtils.requestFocusAndShowKeyboard(editText);
        return inflate;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void select(long j) {
        Long l = this.b;
        if (l != null) {
            if (this.f1545c == null && g(l.longValue(), j)) {
                this.f1545c = Long.valueOf(j);
                return;
            }
            this.f1545c = null;
        }
        this.b = Long.valueOf(j);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void setSelection(@NonNull Pair<Long, Long> pair) {
        Long l = pair.first;
        if (l != null && pair.second != null) {
            Preconditions.checkArgument(g(l.longValue(), pair.second.longValue()));
        }
        Long l2 = pair.first;
        this.b = l2 == null ? null : Long.valueOf(j.a(l2.longValue()));
        Long l3 = pair.second;
        this.f1545c = l3 != null ? Long.valueOf(j.a(l3.longValue())) : null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i2) {
        parcel.writeValue(this.b);
        parcel.writeValue(this.f1545c);
    }
}
