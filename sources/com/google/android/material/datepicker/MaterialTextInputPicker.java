package com.google.android.material.datepicker;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import java.util.Iterator;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class MaterialTextInputPicker<S> extends g<S> {

    @StyleRes
    private int c0;

    @Nullable
    private DateSelector<S> d0;

    @Nullable
    private CalendarConstraints e0;

    /* loaded from: classes.dex */
    class a extends OnSelectionChangedListener<S> {
        a() {
        }

        @Override // com.google.android.material.datepicker.OnSelectionChangedListener
        public void onIncompleteSelectionChanged() {
            Iterator<OnSelectionChangedListener<S>> it = MaterialTextInputPicker.this.b0.iterator();
            while (it.hasNext()) {
                it.next().onIncompleteSelectionChanged();
            }
        }

        @Override // com.google.android.material.datepicker.OnSelectionChangedListener
        public void onSelectionChanged(S s) {
            Iterator<OnSelectionChangedListener<S>> it = MaterialTextInputPicker.this.b0.iterator();
            while (it.hasNext()) {
                it.next().onSelectionChanged(s);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static <T> MaterialTextInputPicker<T> p0(DateSelector<T> dateSelector, @StyleRes int i2, @NonNull CalendarConstraints calendarConstraints) {
        MaterialTextInputPicker<T> materialTextInputPicker = new MaterialTextInputPicker<>();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i2);
        bundle.putParcelable("DATE_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        materialTextInputPicker.setArguments(bundle);
        return materialTextInputPicker;
    }

    @NonNull
    public DateSelector<S> getDateSelector() {
        DateSelector<S> dateSelector = this.d0;
        if (dateSelector != null) {
            return dateSelector;
        }
        throw new IllegalStateException("dateSelector should not be null. Use MaterialTextInputPicker#newInstance() to create this fragment with a DateSelector, and call this method after the fragment has been created.");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.c0 = bundle.getInt("THEME_RES_ID_KEY");
        this.d0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.e0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return this.d0.onCreateTextInputView(layoutInflater.cloneInContext(new ContextThemeWrapper(getContext(), this.c0)), viewGroup, bundle, this.e0, new a());
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.c0);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.d0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.e0);
    }
}
