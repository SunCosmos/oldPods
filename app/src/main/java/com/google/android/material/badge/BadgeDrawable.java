package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.StyleableRes;
import androidx.annotation.XmlRes;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;

/* loaded from: classes.dex */
public class BadgeDrawable extends Drawable implements TextDrawableHelper.TextDrawableDelegate {
    public static final int BOTTOM_END = 8388693;
    public static final int BOTTOM_START = 8388691;
    public static final int TOP_END = 8388661;
    public static final int TOP_START = 8388659;

    @StyleRes
    private static final int q = R.style.Widget_MaterialComponents_Badge;

    @AttrRes
    private static final int r = R.attr.badgeStyle;

    @NonNull
    private final WeakReference<Context> a;

    @NonNull
    private final MaterialShapeDrawable b;

    /* renamed from: c */
    @NonNull
    private final TextDrawableHelper f1464c;

    /* renamed from: d */
    @NonNull
    private final Rect f1465d;
    private final float e;
    private final float f;
    private final float g;

    @NonNull
    private final SavedState h;

    /* renamed from: i */
    private float f1466i;
    private float j;
    private int k;
    private float l;
    private float m;
    private float n;

    @Nullable
    private WeakReference<View> o;

    @Nullable
    private WeakReference<FrameLayout> p;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface BadgeGravity {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        @ColorInt
        private int a;

        @ColorInt
        private int b;

        /* renamed from: c */
        private int f1467c;

        /* renamed from: d */
        private int f1468d;
        private int e;

        @Nullable
        private CharSequence f;

        @PluralsRes
        private int g;

        @StringRes
        private int h;

        /* renamed from: i */
        private int f1469i;
        private boolean j;

        @Dimension(unit = 1)
        private int k;

        @Dimension(unit = 1)
        private int l;

        @Dimension(unit = 1)
        private int m;

        @Dimension(unit = 1)
        private int n;

        /* loaded from: classes.dex */
        static class a implements Parcelable.Creator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            @NonNull
            /* renamed from: a */
            public SavedState createFromParcel(@NonNull Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            @NonNull
            /* renamed from: b */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState(@NonNull Context context) {
            this.f1467c = 255;
            this.f1468d = -1;
            this.b = new TextAppearance(context, R.style.TextAppearance_MaterialComponents_Badge).textColor.getDefaultColor();
            this.f = context.getString(R.string.mtrl_badge_numberless_content_description);
            this.g = R.plurals.mtrl_badge_content_description;
            this.h = R.string.mtrl_exceed_max_badge_number_content_description;
            this.j = true;
        }

        protected SavedState(@NonNull Parcel parcel) {
            this.f1467c = 255;
            this.f1468d = -1;
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.f1467c = parcel.readInt();
            this.f1468d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = parcel.readString();
            this.g = parcel.readInt();
            this.f1469i = parcel.readInt();
            this.k = parcel.readInt();
            this.l = parcel.readInt();
            this.m = parcel.readInt();
            this.n = parcel.readInt();
            this.j = parcel.readInt() != 0;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i2) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.f1467c);
            parcel.writeInt(this.f1468d);
            parcel.writeInt(this.e);
            parcel.writeString(this.f.toString());
            parcel.writeInt(this.g);
            parcel.writeInt(this.f1469i);
            parcel.writeInt(this.k);
            parcel.writeInt(this.l);
            parcel.writeInt(this.m);
            parcel.writeInt(this.n);
            parcel.writeInt(this.j ? 1 : 0);
        }
    }

    /* loaded from: classes.dex */
    public class a implements Runnable {
        final /* synthetic */ View a;
        final /* synthetic */ FrameLayout b;

        a(View view, FrameLayout frameLayout) {
            this.a = view;
            this.b = frameLayout;
        }

        @Override // java.lang.Runnable
        public void run() {
            BadgeDrawable.this.updateBadgeCoordinates(this.a, this.b);
        }
    }

    private BadgeDrawable(@NonNull Context context) {
        this.a = new WeakReference<>(context);
        ThemeEnforcement.checkMaterialTheme(context);
        Resources resources = context.getResources();
        this.f1465d = new Rect();
        this.b = new MaterialShapeDrawable();
        this.e = resources.getDimensionPixelSize(R.dimen.mtrl_badge_radius);
        this.g = resources.getDimensionPixelSize(R.dimen.mtrl_badge_long_text_horizontal_padding);
        this.f = resources.getDimensionPixelSize(R.dimen.mtrl_badge_with_text_radius);
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.f1464c = textDrawableHelper;
        textDrawableHelper.getTextPaint().setTextAlign(Paint.Align.CENTER);
        this.h = new SavedState(context);
        l(R.style.TextAppearance_MaterialComponents_Badge);
    }

    private void a(@NonNull Context context, @NonNull Rect rect, @NonNull View view) {
        float textWidth;
        int i2 = this.h.l + this.h.n;
        int i3 = this.h.f1469i;
        this.j = (i3 == 8388691 || i3 == 8388693) ? rect.bottom - i2 : rect.top + i2;
        if (getNumber() <= 9) {
            textWidth = !hasNumber() ? this.e : this.f;
            this.l = textWidth;
            this.n = textWidth;
        } else {
            float f = this.f;
            this.l = f;
            this.n = f;
            textWidth = (this.f1464c.getTextWidth(e()) / 2.0f) + this.g;
        }
        this.m = textWidth;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(hasNumber() ? R.dimen.mtrl_badge_text_horizontal_edge_offset : R.dimen.mtrl_badge_horizontal_edge_offset);
        int i4 = this.h.k + this.h.m;
        int i5 = this.h.f1469i;
        this.f1466i = (i5 == 8388659 || i5 == 8388691 ? ViewCompat.getLayoutDirection(view) != 0 : ViewCompat.getLayoutDirection(view) == 0) ? ((rect.right + this.m) - dimensionPixelSize) - i4 : (rect.left - this.m) + dimensionPixelSize + i4;
    }

    @NonNull
    private static BadgeDrawable b(@NonNull Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.f(context, attributeSet, i2, i3);
        return badgeDrawable;
    }

    @NonNull
    public static BadgeDrawable c(@NonNull Context context, @NonNull SavedState savedState) {
        BadgeDrawable badgeDrawable = new BadgeDrawable(context);
        badgeDrawable.h(savedState);
        return badgeDrawable;
    }

    @NonNull
    public static BadgeDrawable create(@NonNull Context context) {
        return b(context, null, r, q);
    }

    @NonNull
    public static BadgeDrawable createFromResource(@NonNull Context context, @XmlRes int i2) {
        AttributeSet parseDrawableXml = DrawableUtils.parseDrawableXml(context, i2, "badge");
        int styleAttribute = parseDrawableXml.getStyleAttribute();
        if (styleAttribute == 0) {
            styleAttribute = q;
        }
        return b(context, parseDrawableXml, r, styleAttribute);
    }

    private void d(Canvas canvas) {
        Rect rect = new Rect();
        String e = e();
        this.f1464c.getTextPaint().getTextBounds(e, 0, e.length(), rect);
        canvas.drawText(e, this.f1466i, this.j + (rect.height() / 2), this.f1464c.getTextPaint());
    }

    @NonNull
    private String e() {
        if (getNumber() <= this.k) {
            return NumberFormat.getInstance().format(getNumber());
        }
        Context context = this.a.get();
        return context == null ? "" : context.getString(R.string.mtrl_exceed_max_badge_number_suffix, Integer.valueOf(this.k), "+");
    }

    private void f(Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.Badge, i2, i3, new int[0]);
        setMaxCharacterCount(obtainStyledAttributes.getInt(R.styleable.Badge_maxCharacterCount, 4));
        int i4 = R.styleable.Badge_number;
        if (obtainStyledAttributes.hasValue(i4)) {
            setNumber(obtainStyledAttributes.getInt(i4, 0));
        }
        setBackgroundColor(g(context, obtainStyledAttributes, R.styleable.Badge_backgroundColor));
        int i5 = R.styleable.Badge_badgeTextColor;
        if (obtainStyledAttributes.hasValue(i5)) {
            setBadgeTextColor(g(context, obtainStyledAttributes, i5));
        }
        setBadgeGravity(obtainStyledAttributes.getInt(R.styleable.Badge_badgeGravity, TOP_END));
        setHorizontalOffset(obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Badge_horizontalOffset, 0));
        setVerticalOffset(obtainStyledAttributes.getDimensionPixelOffset(R.styleable.Badge_verticalOffset, 0));
        obtainStyledAttributes.recycle();
    }

    private static int g(Context context, @NonNull TypedArray typedArray, @StyleableRes int i2) {
        return MaterialResources.getColorStateList(context, typedArray, i2).getDefaultColor();
    }

    private void h(@NonNull SavedState savedState) {
        setMaxCharacterCount(savedState.e);
        if (savedState.f1468d != -1) {
            setNumber(savedState.f1468d);
        }
        setBackgroundColor(savedState.a);
        setBadgeTextColor(savedState.b);
        setBadgeGravity(savedState.f1469i);
        setHorizontalOffset(savedState.k);
        setVerticalOffset(savedState.l);
        i(savedState.m);
        j(savedState.n);
        setVisible(savedState.j);
    }

    private void k(@Nullable TextAppearance textAppearance) {
        Context context;
        if (this.f1464c.getTextAppearance() == textAppearance || (context = this.a.get()) == null) {
            return;
        }
        this.f1464c.setTextAppearance(textAppearance, context);
        o();
    }

    private void l(@StyleRes int i2) {
        Context context = this.a.get();
        if (context == null) {
            return;
        }
        k(new TextAppearance(context, i2));
    }

    private void m(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup == null || viewGroup.getId() != R.id.mtrl_anchor_parent) {
            WeakReference<FrameLayout> weakReference = this.p;
            if (weakReference == null || weakReference.get() != viewGroup) {
                n(view);
                FrameLayout frameLayout = new FrameLayout(view.getContext());
                frameLayout.setId(R.id.mtrl_anchor_parent);
                frameLayout.setClipChildren(false);
                frameLayout.setClipToPadding(false);
                frameLayout.setLayoutParams(view.getLayoutParams());
                frameLayout.setMinimumWidth(view.getWidth());
                frameLayout.setMinimumHeight(view.getHeight());
                int indexOfChild = viewGroup.indexOfChild(view);
                viewGroup.removeViewAt(indexOfChild);
                view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                frameLayout.addView(view);
                viewGroup.addView(frameLayout, indexOfChild);
                this.p = new WeakReference<>(frameLayout);
                frameLayout.post(new a(view, frameLayout));
            }
        }
    }

    private static void n(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
    }

    private void o() {
        Context context = this.a.get();
        WeakReference<View> weakReference = this.o;
        View view = weakReference != null ? weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        rect.set(this.f1465d);
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        WeakReference<FrameLayout> weakReference2 = this.p;
        FrameLayout frameLayout = weakReference2 != null ? weakReference2.get() : null;
        if (frameLayout != null || BadgeUtils.USE_COMPAT_PARENT) {
            if (frameLayout == null) {
                frameLayout = (ViewGroup) view.getParent();
            }
            frameLayout.offsetDescendantRectToMyCoords(view, rect2);
        }
        a(context, rect2, view);
        BadgeUtils.updateBadgeBounds(this.f1465d, this.f1466i, this.j, this.m, this.n);
        this.b.setCornerSize(this.l);
        if (rect.equals(this.f1465d)) {
            return;
        }
        this.b.setBounds(this.f1465d);
    }

    private void p() {
        Double.isNaN(getMaxCharacterCount());
        this.k = ((int) Math.pow(10.0d, r0 - 1.0d)) - 1;
    }

    public void clearNumber() {
        this.h.f1468d = -1;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.b.draw(canvas);
        if (hasNumber()) {
            d(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.h.f1467c;
    }

    @ColorInt
    public int getBackgroundColor() {
        return this.b.getFillColor().getDefaultColor();
    }

    public int getBadgeGravity() {
        return this.h.f1469i;
    }

    @ColorInt
    public int getBadgeTextColor() {
        return this.f1464c.getTextPaint().getColor();
    }

    @Nullable
    public CharSequence getContentDescription() {
        Context context;
        if (!isVisible()) {
            return null;
        }
        if (!hasNumber()) {
            return this.h.f;
        }
        if (this.h.g <= 0 || (context = this.a.get()) == null) {
            return null;
        }
        return getNumber() <= this.k ? context.getResources().getQuantityString(this.h.g, getNumber(), Integer.valueOf(getNumber())) : context.getString(this.h.h, Integer.valueOf(this.k));
    }

    @Nullable
    public FrameLayout getCustomBadgeParent() {
        WeakReference<FrameLayout> weakReference = this.p;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public int getHorizontalOffset() {
        return this.h.k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.f1465d.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.f1465d.width();
    }

    public int getMaxCharacterCount() {
        return this.h.e;
    }

    public int getNumber() {
        if (hasNumber()) {
            return this.h.f1468d;
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @NonNull
    public SavedState getSavedState() {
        return this.h;
    }

    public int getVerticalOffset() {
        return this.h.l;
    }

    public boolean hasNumber() {
        return this.h.f1468d != -1;
    }

    public void i(int i2) {
        this.h.m = i2;
        o();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    public void j(int i2) {
        this.h.n = i2;
        o();
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onTextSizeChange() {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.h.f1467c = i2;
        this.f1464c.getTextPaint().setAlpha(i2);
        invalidateSelf();
    }

    public void setBackgroundColor(@ColorInt int i2) {
        this.h.a = i2;
        ColorStateList valueOf = ColorStateList.valueOf(i2);
        if (this.b.getFillColor() != valueOf) {
            this.b.setFillColor(valueOf);
            invalidateSelf();
        }
    }

    public void setBadgeGravity(int i2) {
        if (this.h.f1469i != i2) {
            this.h.f1469i = i2;
            WeakReference<View> weakReference = this.o;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            View view = this.o.get();
            WeakReference<FrameLayout> weakReference2 = this.p;
            updateBadgeCoordinates(view, weakReference2 != null ? weakReference2.get() : null);
        }
    }

    public void setBadgeTextColor(@ColorInt int i2) {
        this.h.b = i2;
        if (this.f1464c.getTextPaint().getColor() != i2) {
            this.f1464c.getTextPaint().setColor(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setContentDescriptionExceedsMaxBadgeNumberStringResource(@StringRes int i2) {
        this.h.h = i2;
    }

    public void setContentDescriptionNumberless(CharSequence charSequence) {
        this.h.f = charSequence;
    }

    public void setContentDescriptionQuantityStringsResource(@PluralsRes int i2) {
        this.h.g = i2;
    }

    public void setHorizontalOffset(int i2) {
        this.h.k = i2;
        o();
    }

    public void setMaxCharacterCount(int i2) {
        if (this.h.e != i2) {
            this.h.e = i2;
            p();
            this.f1464c.setTextWidthDirty(true);
            o();
            invalidateSelf();
        }
    }

    public void setNumber(int i2) {
        int max = Math.max(0, i2);
        if (this.h.f1468d != max) {
            this.h.f1468d = max;
            this.f1464c.setTextWidthDirty(true);
            o();
            invalidateSelf();
        }
    }

    public void setVerticalOffset(int i2) {
        this.h.l = i2;
        o();
    }

    public void setVisible(boolean z) {
        setVisible(z, false);
        this.h.j = z;
        if (!BadgeUtils.USE_COMPAT_PARENT || getCustomBadgeParent() == null || z) {
            return;
        }
        ((ViewGroup) getCustomBadgeParent().getParent()).invalidate();
    }

    public void updateBadgeCoordinates(@NonNull View view) {
        updateBadgeCoordinates(view, (FrameLayout) null);
    }

    @Deprecated
    public void updateBadgeCoordinates(@NonNull View view, @Nullable ViewGroup viewGroup) {
        if (!(viewGroup instanceof FrameLayout)) {
            throw new IllegalArgumentException("customBadgeParent must be a FrameLayout");
        }
        updateBadgeCoordinates(view, (FrameLayout) viewGroup);
    }

    public void updateBadgeCoordinates(@NonNull View view, @Nullable FrameLayout frameLayout) {
        this.o = new WeakReference<>(view);
        boolean z = BadgeUtils.USE_COMPAT_PARENT;
        if (z && frameLayout == null) {
            m(view);
        } else {
            this.p = new WeakReference<>(frameLayout);
        }
        if (!z) {
            n(view);
        }
        o();
        invalidateSelf();
    }
}
