package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.resources.MaterialResources;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class f {
    private final Context a;

    @NonNull
    private final TextInputLayout b;

    /* renamed from: c, reason: collision with root package name */
    private LinearLayout f1727c;

    /* renamed from: d, reason: collision with root package name */
    private int f1728d;
    private FrameLayout e;

    @Nullable
    private Animator f;
    private final float g;
    private int h;

    /* renamed from: i, reason: collision with root package name */
    private int f1729i;

    @Nullable
    private CharSequence j;
    private boolean k;

    @Nullable
    private TextView l;

    @Nullable
    private CharSequence m;
    private int n;

    @Nullable
    private ColorStateList o;
    private CharSequence p;
    private boolean q;

    @Nullable
    private TextView r;
    private int s;

    @Nullable
    private ColorStateList t;
    private Typeface u;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends AnimatorListenerAdapter {
        final /* synthetic */ int a;
        final /* synthetic */ TextView b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ int f1730c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ TextView f1731d;

        a(int i2, TextView textView, int i3, TextView textView2) {
            this.a = i2;
            this.b = textView;
            this.f1730c = i3;
            this.f1731d = textView2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f.this.h = this.a;
            f.this.f = null;
            TextView textView = this.b;
            if (textView != null) {
                textView.setVisibility(4);
                if (this.f1730c == 1 && f.this.l != null) {
                    f.this.l.setText((CharSequence) null);
                }
            }
            TextView textView2 = this.f1731d;
            if (textView2 != null) {
                textView2.setTranslationY(0.0f);
                this.f1731d.setAlpha(1.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            TextView textView = this.f1731d;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    public f(@NonNull TextInputLayout textInputLayout) {
        this.a = textInputLayout.getContext();
        this.b = textInputLayout;
        this.g = r0.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
    }

    private void C(int i2, int i3) {
        TextView l;
        TextView l2;
        if (i2 == i3) {
            return;
        }
        if (i3 != 0 && (l2 = l(i3)) != null) {
            l2.setVisibility(0);
            l2.setAlpha(1.0f);
        }
        if (i2 != 0 && (l = l(i2)) != null) {
            l.setVisibility(4);
            if (i2 == 1) {
                l.setText((CharSequence) null);
            }
        }
        this.h = i3;
    }

    private void K(@Nullable TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    private void M(@NonNull ViewGroup viewGroup, int i2) {
        if (i2 == 0) {
            viewGroup.setVisibility(8);
        }
    }

    private boolean N(@Nullable TextView textView, @Nullable CharSequence charSequence) {
        return ViewCompat.isLaidOut(this.b) && this.b.isEnabled() && !(this.f1729i == this.h && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    private void Q(int i2, int i3, boolean z) {
        if (i2 == i3) {
            return;
        }
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.f = animatorSet;
            ArrayList arrayList = new ArrayList();
            h(arrayList, this.q, this.r, 2, i2, i3);
            h(arrayList, this.k, this.l, 1, i2, i3);
            AnimatorSetCompat.playTogether(animatorSet, arrayList);
            animatorSet.addListener(new a(i3, l(i2), i2, l(i3)));
            animatorSet.start();
        } else {
            C(i2, i3);
        }
        this.b.updateEditTextBackground();
        this.b.updateLabelState(z);
        this.b.updateTextInputBoxState();
    }

    private boolean f() {
        return (this.f1727c == null || this.b.getEditText() == null) ? false : true;
    }

    private void h(@NonNull List<Animator> list, boolean z, @Nullable TextView textView, int i2, int i3, int i4) {
        if (textView == null || !z) {
            return;
        }
        if (i2 == i4 || i2 == i3) {
            list.add(i(textView, i4 == i2));
            if (i4 == i2) {
                list.add(j(textView));
            }
        }
    }

    private ObjectAnimator i(TextView textView, boolean z) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z ? 1.0f : 0.0f);
        ofFloat.setDuration(167L);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        return ofFloat;
    }

    private ObjectAnimator j(TextView textView) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.g, 0.0f);
        ofFloat.setDuration(217L);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        return ofFloat;
    }

    @Nullable
    private TextView l(int i2) {
        if (i2 == 1) {
            return this.l;
        }
        if (i2 != 2) {
            return null;
        }
        return this.r;
    }

    private int s(boolean z, @DimenRes int i2, int i3) {
        return z ? this.a.getResources().getDimensionPixelSize(i2) : i3;
    }

    private boolean w(int i2) {
        return (i2 != 1 || this.l == null || TextUtils.isEmpty(this.j)) ? false : true;
    }

    private boolean x(int i2) {
        return (i2 != 2 || this.r == null || TextUtils.isEmpty(this.p)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean A() {
        return this.q;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void B(TextView textView, int i2) {
        FrameLayout frameLayout;
        if (this.f1727c == null) {
            return;
        }
        if (!y(i2) || (frameLayout = this.e) == null) {
            this.f1727c.removeView(textView);
        } else {
            frameLayout.removeView(textView);
        }
        int i3 = this.f1728d - 1;
        this.f1728d = i3;
        M(this.f1727c, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void D(@Nullable CharSequence charSequence) {
        this.m = charSequence;
        TextView textView = this.l;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void E(boolean z) {
        if (this.k == z) {
            return;
        }
        g();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.a);
            this.l = appCompatTextView;
            appCompatTextView.setId(R.id.textinput_error);
            if (Build.VERSION.SDK_INT >= 17) {
                this.l.setTextAlignment(5);
            }
            Typeface typeface = this.u;
            if (typeface != null) {
                this.l.setTypeface(typeface);
            }
            F(this.n);
            G(this.o);
            D(this.m);
            this.l.setVisibility(4);
            ViewCompat.setAccessibilityLiveRegion(this.l, 1);
            d(this.l, 0);
        } else {
            u();
            B(this.l, 0);
            this.l = null;
            this.b.updateEditTextBackground();
            this.b.updateTextInputBoxState();
        }
        this.k = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F(@StyleRes int i2) {
        this.n = i2;
        TextView textView = this.l;
        if (textView != null) {
            this.b.setTextAppearanceCompatWithErrorFallback(textView, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void G(@Nullable ColorStateList colorStateList) {
        this.o = colorStateList;
        TextView textView = this.l;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void H(@StyleRes int i2) {
        this.s = i2;
        TextView textView = this.r;
        if (textView != null) {
            TextViewCompat.setTextAppearance(textView, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void I(boolean z) {
        if (this.q == z) {
            return;
        }
        g();
        if (z) {
            AppCompatTextView appCompatTextView = new AppCompatTextView(this.a);
            this.r = appCompatTextView;
            appCompatTextView.setId(R.id.textinput_helper_text);
            if (Build.VERSION.SDK_INT >= 17) {
                this.r.setTextAlignment(5);
            }
            Typeface typeface = this.u;
            if (typeface != null) {
                this.r.setTypeface(typeface);
            }
            this.r.setVisibility(4);
            ViewCompat.setAccessibilityLiveRegion(this.r, 1);
            H(this.s);
            J(this.t);
            d(this.r, 1);
        } else {
            v();
            B(this.r, 1);
            this.r = null;
            this.b.updateEditTextBackground();
            this.b.updateTextInputBoxState();
        }
        this.q = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void J(@Nullable ColorStateList colorStateList) {
        this.t = colorStateList;
        TextView textView = this.r;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void L(Typeface typeface) {
        if (typeface != this.u) {
            this.u = typeface;
            K(this.l, typeface);
            K(this.r, typeface);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void O(CharSequence charSequence) {
        g();
        this.j = charSequence;
        this.l.setText(charSequence);
        int i2 = this.h;
        if (i2 != 1) {
            this.f1729i = 1;
        }
        Q(i2, this.f1729i, N(this.l, charSequence));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void P(CharSequence charSequence) {
        g();
        this.p = charSequence;
        this.r.setText(charSequence);
        int i2 = this.h;
        if (i2 != 2) {
            this.f1729i = 2;
        }
        Q(i2, this.f1729i, N(this.r, charSequence));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(TextView textView, int i2) {
        if (this.f1727c == null && this.e == null) {
            LinearLayout linearLayout = new LinearLayout(this.a);
            this.f1727c = linearLayout;
            linearLayout.setOrientation(0);
            this.b.addView(this.f1727c, -1, -2);
            this.e = new FrameLayout(this.a);
            this.f1727c.addView(this.e, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.b.getEditText() != null) {
                e();
            }
        }
        if (y(i2)) {
            this.e.setVisibility(0);
            this.e.addView(textView);
        } else {
            this.f1727c.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.f1727c.setVisibility(0);
        this.f1728d++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        if (f()) {
            EditText editText = this.b.getEditText();
            boolean isFontScaleAtLeast1_3 = MaterialResources.isFontScaleAtLeast1_3(this.a);
            LinearLayout linearLayout = this.f1727c;
            int i2 = R.dimen.material_helper_text_font_1_3_padding_horizontal;
            ViewCompat.setPaddingRelative(linearLayout, s(isFontScaleAtLeast1_3, i2, ViewCompat.getPaddingStart(editText)), s(isFontScaleAtLeast1_3, R.dimen.material_helper_text_font_1_3_padding_top, this.a.getResources().getDimensionPixelSize(R.dimen.material_helper_text_default_padding_top)), s(isFontScaleAtLeast1_3, i2, ViewCompat.getPaddingEnd(editText)), 0);
        }
    }

    void g() {
        Animator animator = this.f;
        if (animator != null) {
            animator.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k() {
        return w(this.f1729i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public CharSequence m() {
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public CharSequence n() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @ColorInt
    public int o() {
        TextView textView = this.l;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public ColorStateList p() {
        TextView textView = this.l;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CharSequence q() {
        return this.p;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @ColorInt
    public int r() {
        TextView textView = this.r;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean t() {
        return x(this.h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u() {
        this.j = null;
        g();
        if (this.h == 1) {
            this.f1729i = (!this.q || TextUtils.isEmpty(this.p)) ? 0 : 2;
        }
        Q(this.h, this.f1729i, N(this.l, null));
    }

    void v() {
        g();
        int i2 = this.h;
        if (i2 == 2) {
            this.f1729i = 0;
        }
        Q(i2, this.f1729i, N(this.r, null));
    }

    boolean y(int i2) {
        return i2 == 0 || i2 == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean z() {
        return this.k;
    }
}
