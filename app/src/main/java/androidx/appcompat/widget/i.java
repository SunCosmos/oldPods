package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.widget.AutoSizeableTextView;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class i {

    @NonNull
    private final TextView a;
    private TintInfo b;

    /* renamed from: c */
    private TintInfo f217c;

    /* renamed from: d */
    private TintInfo f218d;
    private TintInfo e;
    private TintInfo f;
    private TintInfo g;
    private TintInfo h;

    /* renamed from: i */
    @NonNull
    private final j f219i;
    private int j = 0;
    private int k = -1;
    private Typeface l;
    private boolean m;

    /* loaded from: classes.dex */
    public class a extends ResourcesCompat.FontCallback {
        final /* synthetic */ int a;
        final /* synthetic */ int b;

        /* renamed from: c */
        final /* synthetic */ WeakReference f220c;

        a(int i2, int i3, WeakReference weakReference) {
            this.a = i2;
            this.b = i3;
            this.f220c = weakReference;
        }

        @Override // androidx.core.content.res.ResourcesCompat.FontCallback
        public void onFontRetrievalFailed(int i2) {
        }

        @Override // androidx.core.content.res.ResourcesCompat.FontCallback
        public void onFontRetrieved(@NonNull Typeface typeface) {
            int i2;
            if (Build.VERSION.SDK_INT >= 28 && (i2 = this.a) != -1) {
                typeface = Typeface.create(typeface, i2, (this.b & 2) != 0);
            }
            i.this.n(this.f220c, typeface);
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        final /* synthetic */ TextView a;
        final /* synthetic */ Typeface b;

        /* renamed from: c */
        final /* synthetic */ int f222c;

        b(i iVar, TextView textView, Typeface typeface, int i2) {
            this.a = textView;
            this.b = typeface;
            this.f222c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.setTypeface(this.b, this.f222c);
        }
    }

    public i(@NonNull TextView textView) {
        this.a = textView;
        this.f219i = new j(textView);
    }

    private void B(int i2, float f) {
        this.f219i.y(i2, f);
    }

    private void C(Context context, TintTypedArray tintTypedArray) {
        String string;
        Typeface create;
        Typeface typeface;
        int i2 = Build.VERSION.SDK_INT;
        this.j = tintTypedArray.getInt(R.styleable.TextAppearance_android_textStyle, this.j);
        if (i2 >= 28) {
            int i3 = tintTypedArray.getInt(R.styleable.TextAppearance_android_textFontWeight, -1);
            this.k = i3;
            if (i3 != -1) {
                this.j = (this.j & 2) | 0;
            }
        }
        int i4 = R.styleable.TextAppearance_android_fontFamily;
        if (!tintTypedArray.hasValue(i4) && !tintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
            int i5 = R.styleable.TextAppearance_android_typeface;
            if (tintTypedArray.hasValue(i5)) {
                this.m = false;
                int i6 = tintTypedArray.getInt(i5, 1);
                if (i6 == 1) {
                    typeface = Typeface.SANS_SERIF;
                } else if (i6 == 2) {
                    typeface = Typeface.SERIF;
                } else if (i6 != 3) {
                    return;
                } else {
                    typeface = Typeface.MONOSPACE;
                }
                this.l = typeface;
                return;
            }
            return;
        }
        this.l = null;
        int i7 = R.styleable.TextAppearance_fontFamily;
        if (tintTypedArray.hasValue(i7)) {
            i4 = i7;
        }
        int i8 = this.k;
        int i9 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface font = tintTypedArray.getFont(i4, this.j, new a(i8, i9, new WeakReference(this.a)));
                if (font != null) {
                    if (i2 >= 28 && this.k != -1) {
                        font = Typeface.create(Typeface.create(font, 0), this.k, (this.j & 2) != 0);
                    }
                    this.l = font;
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (string = tintTypedArray.getString(i4)) == null) {
            return;
        }
        if (i2 < 28 || this.k == -1) {
            create = Typeface.create(string, this.j);
        } else {
            create = Typeface.create(Typeface.create(string, 0), this.k, (this.j & 2) != 0);
        }
        this.l = create;
    }

    private void a(Drawable drawable, TintInfo tintInfo) {
        if (drawable == null || tintInfo == null) {
            return;
        }
        AppCompatDrawableManager.d(drawable, tintInfo, this.a.getDrawableState());
    }

    private static TintInfo d(Context context, AppCompatDrawableManager appCompatDrawableManager, int i2) {
        ColorStateList c2 = appCompatDrawableManager.c(context, i2);
        if (c2 == null) {
            return null;
        }
        TintInfo tintInfo = new TintInfo();
        tintInfo.mHasTintList = true;
        tintInfo.mTintList = c2;
        return tintInfo;
    }

    private void y(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.a.getCompoundDrawablesRelative();
            TextView textView = this.a;
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
            return;
        }
        if (drawable == null && drawable2 == null && drawable3 == null && drawable4 == null) {
            return;
        }
        if (i2 >= 17) {
            Drawable[] compoundDrawablesRelative2 = this.a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                TextView textView2 = this.a;
                Drawable drawable7 = compoundDrawablesRelative2[0];
                if (drawable2 == null) {
                    drawable2 = compoundDrawablesRelative2[1];
                }
                Drawable drawable8 = compoundDrawablesRelative2[2];
                if (drawable4 == null) {
                    drawable4 = compoundDrawablesRelative2[3];
                }
                textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, drawable8, drawable4);
                return;
            }
        }
        Drawable[] compoundDrawables = this.a.getCompoundDrawables();
        TextView textView3 = this.a;
        if (drawable == null) {
            drawable = compoundDrawables[0];
        }
        if (drawable2 == null) {
            drawable2 = compoundDrawables[1];
        }
        if (drawable3 == null) {
            drawable3 = compoundDrawables[2];
        }
        if (drawable4 == null) {
            drawable4 = compoundDrawables[3];
        }
        textView3.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
    }

    private void z() {
        TintInfo tintInfo = this.h;
        this.b = tintInfo;
        this.f217c = tintInfo;
        this.f218d = tintInfo;
        this.e = tintInfo;
        this.f = tintInfo;
        this.g = tintInfo;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void A(int i2, float f) {
        if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE || l()) {
            return;
        }
        B(i2, f);
    }

    public void b() {
        if (this.b != null || this.f217c != null || this.f218d != null || this.e != null) {
            Drawable[] compoundDrawables = this.a.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.f217c);
            a(compoundDrawables[2], this.f218d);
            a(compoundDrawables[3], this.e);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            if (this.f == null && this.g == null) {
                return;
            }
            Drawable[] compoundDrawablesRelative = this.a.getCompoundDrawablesRelative();
            a(compoundDrawablesRelative[0], this.f);
            a(compoundDrawablesRelative[2], this.g);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void c() {
        this.f219i.b();
    }

    public int e() {
        return this.f219i.j();
    }

    public int f() {
        return this.f219i.k();
    }

    public int g() {
        return this.f219i.l();
    }

    public int[] h() {
        return this.f219i.m();
    }

    public int i() {
        return this.f219i.n();
    }

    @Nullable
    public ColorStateList j() {
        TintInfo tintInfo = this.h;
        if (tintInfo != null) {
            return tintInfo.mTintList;
        }
        return null;
    }

    @Nullable
    public PorterDuff.Mode k() {
        TintInfo tintInfo = this.h;
        if (tintInfo != null) {
            return tintInfo.mTintMode;
        }
        return null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public boolean l() {
        return this.f219i.s();
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:145:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01c5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01e9  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m(@androidx.annotation.Nullable android.util.AttributeSet r24, int r25) {
        /*
            Method dump skipped, instructions count: 794
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.i.m(android.util.AttributeSet, int):void");
    }

    void n(WeakReference<TextView> weakReference, Typeface typeface) {
        if (this.m) {
            this.l = typeface;
            TextView textView = weakReference.get();
            if (textView != null) {
                if (ViewCompat.isAttachedToWindow(textView)) {
                    textView.post(new b(this, textView, typeface, this.j));
                } else {
                    textView.setTypeface(typeface, this.j);
                }
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void o(boolean z, int i2, int i3, int i4, int i5) {
        if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
            return;
        }
        c();
    }

    public void p() {
        b();
    }

    public void q(Context context, int i2) {
        String string;
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, i2, R.styleable.TextAppearance);
        int i3 = R.styleable.TextAppearance_textAllCaps;
        if (obtainStyledAttributes.hasValue(i3)) {
            s(obtainStyledAttributes.getBoolean(i3, false));
        }
        int i4 = Build.VERSION.SDK_INT;
        if (i4 < 23) {
            int i5 = R.styleable.TextAppearance_android_textColor;
            if (obtainStyledAttributes.hasValue(i5) && (colorStateList3 = obtainStyledAttributes.getColorStateList(i5)) != null) {
                this.a.setTextColor(colorStateList3);
            }
            int i6 = R.styleable.TextAppearance_android_textColorLink;
            if (obtainStyledAttributes.hasValue(i6) && (colorStateList2 = obtainStyledAttributes.getColorStateList(i6)) != null) {
                this.a.setLinkTextColor(colorStateList2);
            }
            int i7 = R.styleable.TextAppearance_android_textColorHint;
            if (obtainStyledAttributes.hasValue(i7) && (colorStateList = obtainStyledAttributes.getColorStateList(i7)) != null) {
                this.a.setHintTextColor(colorStateList);
            }
        }
        int i8 = R.styleable.TextAppearance_android_textSize;
        if (obtainStyledAttributes.hasValue(i8) && obtainStyledAttributes.getDimensionPixelSize(i8, -1) == 0) {
            this.a.setTextSize(0, 0.0f);
        }
        C(context, obtainStyledAttributes);
        if (i4 >= 26) {
            int i9 = R.styleable.TextAppearance_fontVariationSettings;
            if (obtainStyledAttributes.hasValue(i9) && (string = obtainStyledAttributes.getString(i9)) != null) {
                this.a.setFontVariationSettings(string);
            }
        }
        obtainStyledAttributes.recycle();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.a.setTypeface(typeface, this.j);
        }
    }

    public void r(@NonNull TextView textView, @Nullable InputConnection inputConnection, @NonNull EditorInfo editorInfo) {
        if (Build.VERSION.SDK_INT >= 30 || inputConnection == null) {
            return;
        }
        EditorInfoCompat.setInitialSurroundingText(editorInfo, textView.getText());
    }

    public void s(boolean z) {
        this.a.setAllCaps(z);
    }

    public void t(int i2, int i3, int i4, int i5) {
        this.f219i.u(i2, i3, i4, i5);
    }

    public void u(@NonNull int[] iArr, int i2) {
        this.f219i.v(iArr, i2);
    }

    public void v(int i2) {
        this.f219i.w(i2);
    }

    public void w(@Nullable ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new TintInfo();
        }
        TintInfo tintInfo = this.h;
        tintInfo.mTintList = colorStateList;
        tintInfo.mHasTintList = colorStateList != null;
        z();
    }

    public void x(@Nullable PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new TintInfo();
        }
        TintInfo tintInfo = this.h;
        tintInfo.mTintMode = mode;
        tintInfo.mHasTintMode = mode != null;
        z();
    }
}
