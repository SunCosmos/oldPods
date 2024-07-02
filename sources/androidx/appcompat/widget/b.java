package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.R;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public class b {

    @NonNull
    private final View a;

    /* renamed from: d */
    private TintInfo f211d;
    private TintInfo e;
    private TintInfo f;

    /* renamed from: c */
    private int f210c = -1;
    private final AppCompatDrawableManager b = AppCompatDrawableManager.get();

    public b(@NonNull View view) {
        this.a = view;
    }

    private boolean a(@NonNull Drawable drawable) {
        if (this.f == null) {
            this.f = new TintInfo();
        }
        TintInfo tintInfo = this.f;
        tintInfo.a();
        ColorStateList backgroundTintList = ViewCompat.getBackgroundTintList(this.a);
        if (backgroundTintList != null) {
            tintInfo.mHasTintList = true;
            tintInfo.mTintList = backgroundTintList;
        }
        PorterDuff.Mode backgroundTintMode = ViewCompat.getBackgroundTintMode(this.a);
        if (backgroundTintMode != null) {
            tintInfo.mHasTintMode = true;
            tintInfo.mTintMode = backgroundTintMode;
        }
        if (!tintInfo.mHasTintList && !tintInfo.mHasTintMode) {
            return false;
        }
        AppCompatDrawableManager.d(drawable, tintInfo, this.a.getDrawableState());
        return true;
    }

    private boolean k() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 > 21 ? this.f211d != null : i2 == 21;
    }

    public void b() {
        Drawable background = this.a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            TintInfo tintInfo = this.e;
            if (tintInfo != null) {
                AppCompatDrawableManager.d(background, tintInfo, this.a.getDrawableState());
                return;
            }
            TintInfo tintInfo2 = this.f211d;
            if (tintInfo2 != null) {
                AppCompatDrawableManager.d(background, tintInfo2, this.a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        TintInfo tintInfo = this.e;
        if (tintInfo != null) {
            return tintInfo.mTintList;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        TintInfo tintInfo = this.e;
        if (tintInfo != null) {
            return tintInfo.mTintMode;
        }
        return null;
    }

    public void e(@Nullable AttributeSet attributeSet, int i2) {
        Context context = this.a.getContext();
        int[] iArr = R.styleable.ViewBackgroundHelper;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i2, 0);
        View view = this.a;
        ViewCompat.saveAttributeDataForStyleable(view, view.getContext(), iArr, attributeSet, obtainStyledAttributes.getWrappedTypeArray(), i2, 0);
        try {
            int i3 = R.styleable.ViewBackgroundHelper_android_background;
            if (obtainStyledAttributes.hasValue(i3)) {
                this.f210c = obtainStyledAttributes.getResourceId(i3, -1);
                ColorStateList c2 = this.b.c(this.a.getContext(), this.f210c);
                if (c2 != null) {
                    h(c2);
                }
            }
            int i4 = R.styleable.ViewBackgroundHelper_backgroundTint;
            if (obtainStyledAttributes.hasValue(i4)) {
                ViewCompat.setBackgroundTintList(this.a, obtainStyledAttributes.getColorStateList(i4));
            }
            int i5 = R.styleable.ViewBackgroundHelper_backgroundTintMode;
            if (obtainStyledAttributes.hasValue(i5)) {
                ViewCompat.setBackgroundTintMode(this.a, DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(i5, -1), null));
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void f(Drawable drawable) {
        this.f210c = -1;
        h(null);
        b();
    }

    public void g(int i2) {
        this.f210c = i2;
        AppCompatDrawableManager appCompatDrawableManager = this.b;
        h(appCompatDrawableManager != null ? appCompatDrawableManager.c(this.a.getContext(), i2) : null);
        b();
    }

    void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f211d == null) {
                this.f211d = new TintInfo();
            }
            TintInfo tintInfo = this.f211d;
            tintInfo.mTintList = colorStateList;
            tintInfo.mHasTintList = true;
        } else {
            this.f211d = null;
        }
        b();
    }

    public void i(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new TintInfo();
        }
        TintInfo tintInfo = this.e;
        tintInfo.mTintList = colorStateList;
        tintInfo.mHasTintList = true;
        b();
    }

    public void j(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new TintInfo();
        }
        TintInfo tintInfo = this.e;
        tintInfo.mTintMode = mode;
        tintInfo.mHasTintMode = true;
        b();
    }
}
