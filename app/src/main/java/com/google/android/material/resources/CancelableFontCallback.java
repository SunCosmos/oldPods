package com.google.android.material.resources;

import android.graphics.Typeface;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class CancelableFontCallback extends TextAppearanceFontCallback {
    private final Typeface a;
    private final ApplyFont b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1647c;

    /* loaded from: classes.dex */
    public interface ApplyFont {
        void apply(Typeface typeface);
    }

    public CancelableFontCallback(ApplyFont applyFont, Typeface typeface) {
        this.a = typeface;
        this.b = applyFont;
    }

    private void a(Typeface typeface) {
        if (this.f1647c) {
            return;
        }
        this.b.apply(typeface);
    }

    public void cancel() {
        this.f1647c = true;
    }

    @Override // com.google.android.material.resources.TextAppearanceFontCallback
    public void onFontRetrievalFailed(int i2) {
        a(this.a);
    }

    @Override // com.google.android.material.resources.TextAppearanceFontCallback
    public void onFontRetrieved(Typeface typeface, boolean z) {
        a(typeface);
    }
}
