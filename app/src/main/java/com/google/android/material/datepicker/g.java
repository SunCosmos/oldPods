package com.google.android.material.datepicker;

import androidx.fragment.app.Fragment;
import java.util.LinkedHashSet;

/* loaded from: classes.dex */
public abstract class g<S> extends Fragment {
    protected final LinkedHashSet<OnSelectionChangedListener<S>> b0 = new LinkedHashSet<>();

    public boolean addOnSelectionChangedListener(OnSelectionChangedListener<S> onSelectionChangedListener) {
        return this.b0.add(onSelectionChangedListener);
    }

    public void o0() {
        this.b0.clear();
    }
}
