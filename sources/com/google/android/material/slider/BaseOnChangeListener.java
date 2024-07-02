package com.google.android.material.slider;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public interface BaseOnChangeListener<S> {
    void onValueChange(@NonNull S s, float f, boolean z);
}
