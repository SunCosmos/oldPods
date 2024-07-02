package com.google.android.material.internal;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TextAppearanceFontCallback;
import java.lang.ref.WeakReference;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class TextDrawableHelper {

    /* renamed from: c, reason: collision with root package name */
    private float f1608c;

    @Nullable
    private TextAppearance f;
    private final TextPaint a = new TextPaint(1);
    private final TextAppearanceFontCallback b = new a();

    /* renamed from: d, reason: collision with root package name */
    private boolean f1609d = true;

    @Nullable
    private WeakReference<TextDrawableDelegate> e = new WeakReference<>(null);

    /* loaded from: classes.dex */
    public interface TextDrawableDelegate {
        @NonNull
        int[] getState();

        boolean onStateChange(int[] iArr);

        void onTextSizeChange();
    }

    /* loaded from: classes.dex */
    class a extends TextAppearanceFontCallback {
        a() {
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrievalFailed(int i2) {
            TextDrawableHelper.this.f1609d = true;
            TextDrawableDelegate textDrawableDelegate = (TextDrawableDelegate) TextDrawableHelper.this.e.get();
            if (textDrawableDelegate != null) {
                textDrawableDelegate.onTextSizeChange();
            }
        }

        @Override // com.google.android.material.resources.TextAppearanceFontCallback
        public void onFontRetrieved(@NonNull Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            TextDrawableHelper.this.f1609d = true;
            TextDrawableDelegate textDrawableDelegate = (TextDrawableDelegate) TextDrawableHelper.this.e.get();
            if (textDrawableDelegate != null) {
                textDrawableDelegate.onTextSizeChange();
            }
        }
    }

    public TextDrawableHelper(@Nullable TextDrawableDelegate textDrawableDelegate) {
        setDelegate(textDrawableDelegate);
    }

    private float c(@Nullable CharSequence charSequence) {
        if (charSequence == null) {
            return 0.0f;
        }
        return this.a.measureText(charSequence, 0, charSequence.length());
    }

    @Nullable
    public TextAppearance getTextAppearance() {
        return this.f;
    }

    @NonNull
    public TextPaint getTextPaint() {
        return this.a;
    }

    public float getTextWidth(String str) {
        if (!this.f1609d) {
            return this.f1608c;
        }
        float c2 = c(str);
        this.f1608c = c2;
        this.f1609d = false;
        return c2;
    }

    public boolean isTextWidthDirty() {
        return this.f1609d;
    }

    public void setDelegate(@Nullable TextDrawableDelegate textDrawableDelegate) {
        this.e = new WeakReference<>(textDrawableDelegate);
    }

    public void setTextAppearance(@Nullable TextAppearance textAppearance, Context context) {
        if (this.f != textAppearance) {
            this.f = textAppearance;
            if (textAppearance != null) {
                textAppearance.updateMeasureState(context, this.a, this.b);
                TextDrawableDelegate textDrawableDelegate = this.e.get();
                if (textDrawableDelegate != null) {
                    this.a.drawableState = textDrawableDelegate.getState();
                }
                textAppearance.updateDrawState(context, this.a, this.b);
                this.f1609d = true;
            }
            TextDrawableDelegate textDrawableDelegate2 = this.e.get();
            if (textDrawableDelegate2 != null) {
                textDrawableDelegate2.onTextSizeChange();
                textDrawableDelegate2.onStateChange(textDrawableDelegate2.getState());
            }
        }
    }

    public void setTextWidthDirty(boolean z) {
        this.f1609d = z;
    }

    public void updateTextPaintDrawState(Context context) {
        this.f.updateDrawState(context, this.a, this.b);
    }
}
