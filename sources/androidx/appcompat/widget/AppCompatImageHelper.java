package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.ViewCompat;
import androidx.core.widget.ImageViewCompat;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class AppCompatImageHelper {

    @NonNull
    private final ImageView a;
    private TintInfo b;

    /* renamed from: c */
    private TintInfo f155c;

    /* renamed from: d */
    private TintInfo f156d;

    public AppCompatImageHelper(@NonNull ImageView imageView) {
        this.a = imageView;
    }

    private boolean a(@NonNull Drawable drawable) {
        if (this.f156d == null) {
            this.f156d = new TintInfo();
        }
        TintInfo tintInfo = this.f156d;
        tintInfo.a();
        ColorStateList imageTintList = ImageViewCompat.getImageTintList(this.a);
        if (imageTintList != null) {
            tintInfo.mHasTintList = true;
            tintInfo.mTintList = imageTintList;
        }
        PorterDuff.Mode imageTintMode = ImageViewCompat.getImageTintMode(this.a);
        if (imageTintMode != null) {
            tintInfo.mHasTintMode = true;
            tintInfo.mTintMode = imageTintMode;
        }
        if (!tintInfo.mHasTintList && !tintInfo.mHasTintMode) {
            return false;
        }
        AppCompatDrawableManager.d(drawable, tintInfo, this.a.getDrawableState());
        return true;
    }

    private boolean h() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 > 21 ? this.b != null : i2 == 21;
    }

    public void b() {
        Drawable drawable = this.a.getDrawable();
        if (drawable != null) {
            DrawableUtils.a(drawable);
        }
        if (drawable != null) {
            if (h() && a(drawable)) {
                return;
            }
            TintInfo tintInfo = this.f155c;
            if (tintInfo != null) {
                AppCompatDrawableManager.d(drawable, tintInfo, this.a.getDrawableState());
                return;
            }
            TintInfo tintInfo2 = this.b;
            if (tintInfo2 != null) {
                AppCompatDrawableManager.d(drawable, tintInfo2, this.a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        TintInfo tintInfo = this.f155c;
        if (tintInfo != null) {
            return tintInfo.mTintList;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        TintInfo tintInfo = this.f155c;
        if (tintInfo != null) {
            return tintInfo.mTintMode;
        }
        return null;
    }

    public boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.a.getBackground() instanceof RippleDrawable);
    }

    public void f(ColorStateList colorStateList) {
        if (this.f155c == null) {
            this.f155c = new TintInfo();
        }
        TintInfo tintInfo = this.f155c;
        tintInfo.mTintList = colorStateList;
        tintInfo.mHasTintList = true;
        b();
    }

    public void g(PorterDuff.Mode mode) {
        if (this.f155c == null) {
            this.f155c = new TintInfo();
        }
        TintInfo tintInfo = this.f155c;
        tintInfo.mTintMode = mode;
        tintInfo.mHasTintMode = true;
        b();
    }

    public void loadFromAttributes(AttributeSet attributeSet, int i2) {
        int resourceId;
        Context context = this.a.getContext();
        int[] iArr = R.styleable.AppCompatImageView;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i2, 0);
        ImageView imageView = this.a;
        ViewCompat.saveAttributeDataForStyleable(imageView, imageView.getContext(), iArr, attributeSet, obtainStyledAttributes.getWrappedTypeArray(), i2, 0);
        try {
            Drawable drawable = this.a.getDrawable();
            if (drawable == null && (resourceId = obtainStyledAttributes.getResourceId(R.styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = AppCompatResources.getDrawable(this.a.getContext(), resourceId)) != null) {
                this.a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                DrawableUtils.a(drawable);
            }
            int i3 = R.styleable.AppCompatImageView_tint;
            if (obtainStyledAttributes.hasValue(i3)) {
                ImageViewCompat.setImageTintList(this.a, obtainStyledAttributes.getColorStateList(i3));
            }
            int i4 = R.styleable.AppCompatImageView_tintMode;
            if (obtainStyledAttributes.hasValue(i4)) {
                ImageViewCompat.setImageTintMode(this.a, DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(i4, -1), null));
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setImageResource(int i2) {
        if (i2 != 0) {
            Drawable drawable = AppCompatResources.getDrawable(this.a.getContext(), i2);
            if (drawable != null) {
                DrawableUtils.a(drawable);
            }
            this.a.setImageDrawable(drawable);
        } else {
            this.a.setImageDrawable(null);
        }
        b();
    }
}
