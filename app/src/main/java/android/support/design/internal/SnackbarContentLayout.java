package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RestrictTo;
import android.support.design.R;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: lib/Mus.dex */
public class SnackbarContentLayout extends LinearLayout implements BaseTransientBottomBar.ContentViewCallback {
    private Button mActionView;
    private int mMaxInlineActionWidth;
    private int mMaxWidth;
    private TextView mMessageView;

    public SnackbarContentLayout(Context context) {
        this(context, null);
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SnackbarLayout);
        this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
        this.mMaxInlineActionWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mMessageView = (TextView) findViewById(R.id.snackbar_text);
        this.mActionView = (Button) findViewById(R.id.snackbar_action);
    }

    public TextView getMessageView() {
        return this.mMessageView;
    }

    public Button getActionView() {
        return this.mActionView;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4 = i2;
        super.onMeasure(i4, i3);
        if (this.mMaxWidth > 0 && getMeasuredWidth() > this.mMaxWidth) {
            i4 = View.MeasureSpec.makeMeasureSpec(this.mMaxWidth, BasicMeasure.EXACTLY);
            super.onMeasure(i4, i3);
        }
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical_2lines);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical);
        boolean z = this.mMessageView.getLayout().getLineCount() > 1;
        boolean z2 = false;
        if (z && this.mMaxInlineActionWidth > 0 && this.mActionView.getMeasuredWidth() > this.mMaxInlineActionWidth) {
            if (updateViewsWithinLayout(1, dimensionPixelSize, dimensionPixelSize - dimensionPixelSize2)) {
                z2 = true;
            }
        } else {
            int i5 = z ? dimensionPixelSize : dimensionPixelSize2;
            if (updateViewsWithinLayout(0, i5, i5)) {
                z2 = true;
            }
        }
        if (z2) {
            super.onMeasure(i4, i3);
        }
    }

    private boolean updateViewsWithinLayout(int i2, int i3, int i4) {
        boolean z = false;
        if (i2 != getOrientation()) {
            setOrientation(i2);
            z = true;
        }
        if (this.mMessageView.getPaddingTop() != i3 || this.mMessageView.getPaddingBottom() != i4) {
            updateTopBottomPadding(this.mMessageView, i3, i4);
            z = true;
        }
        return z;
    }

    private static void updateTopBottomPadding(View view, int i2, int i3) {
        if (ViewCompat.isPaddingRelative(view)) {
            ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), i2, ViewCompat.getPaddingEnd(view), i3);
        } else {
            view.setPadding(view.getPaddingLeft(), i2, view.getPaddingRight(), i3);
        }
    }

    @Override // android.support.design.widget.BaseTransientBottomBar.ContentViewCallback
    public void animateContentIn(int i2, int i3) {
        this.mMessageView.setAlpha(0.0f);
        this.mMessageView.animate().alpha(1.0f).setDuration(i3).setStartDelay(i2).start();
        if (this.mActionView.getVisibility() == 0) {
            this.mActionView.setAlpha(0.0f);
            this.mActionView.animate().alpha(1.0f).setDuration(i3).setStartDelay(i2).start();
        }
    }

    @Override // android.support.design.widget.BaseTransientBottomBar.ContentViewCallback
    public void animateContentOut(int i2, int i3) {
        this.mMessageView.setAlpha(1.0f);
        this.mMessageView.animate().alpha(0.0f).setDuration(i3).setStartDelay(i2).start();
        if (this.mActionView.getVisibility() == 0) {
            this.mActionView.setAlpha(1.0f);
            this.mActionView.animate().alpha(0.0f).setDuration(i3).setStartDelay(i2).start();
        }
    }
}
