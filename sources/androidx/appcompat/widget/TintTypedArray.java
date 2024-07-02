package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleableRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class TintTypedArray {
    private final Context a;
    private final TypedArray b;

    /* renamed from: c */
    private TypedValue f201c;

    private TintTypedArray(Context context, TypedArray typedArray) {
        this.a = context;
        this.b = typedArray;
    }

    public static TintTypedArray obtainStyledAttributes(Context context, int i2, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(i2, iArr));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3) {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, iArr, i2, i3));
    }

    public boolean getBoolean(int i2, boolean z) {
        return this.b.getBoolean(i2, z);
    }

    @RequiresApi(21)
    public int getChangingConfigurations() {
        return this.b.getChangingConfigurations();
    }

    public int getColor(int i2, int i3) {
        return this.b.getColor(i2, i3);
    }

    public ColorStateList getColorStateList(int i2) {
        int resourceId;
        ColorStateList colorStateList;
        return (!this.b.hasValue(i2) || (resourceId = this.b.getResourceId(i2, 0)) == 0 || (colorStateList = AppCompatResources.getColorStateList(this.a, resourceId)) == null) ? this.b.getColorStateList(i2) : colorStateList;
    }

    public float getDimension(int i2, float f) {
        return this.b.getDimension(i2, f);
    }

    public int getDimensionPixelOffset(int i2, int i3) {
        return this.b.getDimensionPixelOffset(i2, i3);
    }

    public int getDimensionPixelSize(int i2, int i3) {
        return this.b.getDimensionPixelSize(i2, i3);
    }

    public Drawable getDrawable(int i2) {
        int resourceId;
        return (!this.b.hasValue(i2) || (resourceId = this.b.getResourceId(i2, 0)) == 0) ? this.b.getDrawable(i2) : AppCompatResources.getDrawable(this.a, resourceId);
    }

    public Drawable getDrawableIfKnown(int i2) {
        int resourceId;
        if (!this.b.hasValue(i2) || (resourceId = this.b.getResourceId(i2, 0)) == 0) {
            return null;
        }
        return AppCompatDrawableManager.get().b(this.a, resourceId, true);
    }

    public float getFloat(int i2, float f) {
        return this.b.getFloat(i2, f);
    }

    @Nullable
    public Typeface getFont(@StyleableRes int i2, int i3, @Nullable ResourcesCompat.FontCallback fontCallback) {
        int resourceId = this.b.getResourceId(i2, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f201c == null) {
            this.f201c = new TypedValue();
        }
        return ResourcesCompat.getFont(this.a, resourceId, this.f201c, i3, fontCallback);
    }

    public float getFraction(int i2, int i3, int i4, float f) {
        return this.b.getFraction(i2, i3, i4, f);
    }

    public int getIndex(int i2) {
        return this.b.getIndex(i2);
    }

    public int getIndexCount() {
        return this.b.getIndexCount();
    }

    public int getInt(int i2, int i3) {
        return this.b.getInt(i2, i3);
    }

    public int getInteger(int i2, int i3) {
        return this.b.getInteger(i2, i3);
    }

    public int getLayoutDimension(int i2, int i3) {
        return this.b.getLayoutDimension(i2, i3);
    }

    public int getLayoutDimension(int i2, String str) {
        return this.b.getLayoutDimension(i2, str);
    }

    public String getNonResourceString(int i2) {
        return this.b.getNonResourceString(i2);
    }

    public String getPositionDescription() {
        return this.b.getPositionDescription();
    }

    public int getResourceId(int i2, int i3) {
        return this.b.getResourceId(i2, i3);
    }

    public Resources getResources() {
        return this.b.getResources();
    }

    public String getString(int i2) {
        return this.b.getString(i2);
    }

    public CharSequence getText(int i2) {
        return this.b.getText(i2);
    }

    public CharSequence[] getTextArray(int i2) {
        return this.b.getTextArray(i2);
    }

    public int getType(int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.b.getType(i2);
        }
        if (this.f201c == null) {
            this.f201c = new TypedValue();
        }
        this.b.getValue(i2, this.f201c);
        return this.f201c.type;
    }

    public boolean getValue(int i2, TypedValue typedValue) {
        return this.b.getValue(i2, typedValue);
    }

    public TypedArray getWrappedTypeArray() {
        return this.b;
    }

    public boolean hasValue(int i2) {
        return this.b.hasValue(i2);
    }

    public int length() {
        return this.b.length();
    }

    public TypedValue peekValue(int i2) {
        return this.b.peekValue(i2);
    }

    public void recycle() {
        this.b.recycle();
    }
}
