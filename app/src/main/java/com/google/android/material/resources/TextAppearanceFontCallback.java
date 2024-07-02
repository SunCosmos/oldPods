package com.google.android.material.resources;

import android.graphics.Typeface;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public abstract class TextAppearanceFontCallback {
    public abstract void onFontRetrievalFailed(int i2);

    public abstract void onFontRetrieved(Typeface typeface, boolean z);
}
