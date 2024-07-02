package pl.droidsonroids.gif;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import java.io.IOException;
import pl.droidsonroids.gif.f;

/* loaded from: classes.dex */
public class GifTextView extends TextView {
    private f.b a;

    public GifTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c(attributeSet, 0, 0);
    }

    public GifTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        c(attributeSet, i2, 0);
    }

    private void a() {
        if (this.a.b < 0) {
            return;
        }
        for (Drawable drawable : getCompoundDrawables()) {
            f.a(this.a.b, drawable);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            for (Drawable drawable2 : getCompoundDrawablesRelative()) {
                f.a(this.a.b, drawable2);
            }
        }
        f.a(this.a.b, getBackground());
    }

    private Drawable b(int i2) {
        if (i2 == 0) {
            return null;
        }
        Resources resources = getResources();
        String resourceTypeName = resources.getResourceTypeName(i2);
        if (!isInEditMode() && f.a.contains(resourceTypeName)) {
            try {
                return new c(resources, i2);
            } catch (Resources.NotFoundException | IOException unused) {
            }
        }
        return Build.VERSION.SDK_INT >= 21 ? resources.getDrawable(i2, getContext().getTheme()) : resources.getDrawable(i2);
    }

    private void c(AttributeSet attributeSet, int i2, int i3) {
        if (attributeSet != null) {
            Drawable b = b(attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableLeft", 0));
            Drawable b2 = b(attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableTop", 0));
            Drawable b3 = b(attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableRight", 0));
            Drawable b4 = b(attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableBottom", 0));
            Drawable b5 = b(attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableStart", 0));
            Drawable b6 = b(attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "drawableEnd", 0));
            if (Build.VERSION.SDK_INT >= 17) {
                if (getLayoutDirection() == 0) {
                    if (b5 == null) {
                        b5 = b;
                    }
                    if (b6 == null) {
                        b6 = b3;
                    }
                } else {
                    if (b5 == null) {
                        b5 = b3;
                    }
                    if (b6 == null) {
                        b6 = b;
                    }
                }
                setCompoundDrawablesRelativeWithIntrinsicBounds(b5, b2, b6, b4);
            }
            setCompoundDrawablesWithIntrinsicBounds(b, b2, b3, b4);
            setBackgroundInternal(b(attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "background", 0)));
            this.a = new f.b(this, attributeSet, i2, i3);
            a();
        }
        this.a = new f.b();
    }

    private static void d(Drawable[] drawableArr, boolean z) {
        for (Drawable drawable : drawableArr) {
            if (drawable != null) {
                drawable.setVisible(z, false);
            }
        }
    }

    private void setBackgroundInternal(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    private void setCompoundDrawablesVisible(boolean z) {
        d(getCompoundDrawables(), z);
        if (Build.VERSION.SDK_INT >= 17) {
            d(getCompoundDrawablesRelative(), z);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setCompoundDrawablesVisible(true);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setCompoundDrawablesVisible(false);
    }

    @Override // android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof GifViewSavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        GifViewSavedState gifViewSavedState = (GifViewSavedState) parcelable;
        super.onRestoreInstanceState(gifViewSavedState.getSuperState());
        Drawable[] compoundDrawables = getCompoundDrawables();
        gifViewSavedState.a(compoundDrawables[0], 0);
        gifViewSavedState.a(compoundDrawables[1], 1);
        gifViewSavedState.a(compoundDrawables[2], 2);
        gifViewSavedState.a(compoundDrawables[3], 3);
        if (Build.VERSION.SDK_INT >= 17) {
            Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
            gifViewSavedState.a(compoundDrawablesRelative[0], 4);
            gifViewSavedState.a(compoundDrawablesRelative[2], 5);
        }
        gifViewSavedState.a(getBackground(), 6);
    }

    @Override // android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        Drawable[] drawableArr = new Drawable[7];
        if (this.a.a) {
            Drawable[] compoundDrawables = getCompoundDrawables();
            System.arraycopy(compoundDrawables, 0, drawableArr, 0, compoundDrawables.length);
            if (Build.VERSION.SDK_INT >= 17) {
                Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
                drawableArr[4] = compoundDrawablesRelative[0];
                drawableArr[5] = compoundDrawablesRelative[2];
            }
            drawableArr[6] = getBackground();
        }
        return new GifViewSavedState(super.onSaveInstanceState(), drawableArr);
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        setBackgroundInternal(b(i2));
    }

    @Override // android.widget.TextView
    @RequiresApi(17)
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        setCompoundDrawablesRelativeWithIntrinsicBounds(b(i2), b(i3), b(i4), b(i5));
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        setCompoundDrawablesWithIntrinsicBounds(b(i2), b(i3), b(i4), b(i5));
    }

    public void setFreezesAnimation(boolean z) {
        this.a.a = z;
    }
}
