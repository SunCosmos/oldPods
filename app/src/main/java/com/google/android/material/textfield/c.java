package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

/* loaded from: classes.dex */
class c extends MaterialShapeDrawable {
    private int A;

    @NonNull
    private final Paint y;

    @NonNull
    private final RectF z;

    c() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(@Nullable ShapeAppearanceModel shapeAppearanceModel) {
        super(shapeAppearanceModel == null ? new ShapeAppearanceModel() : shapeAppearanceModel);
        this.y = new Paint(1);
        K();
        this.z = new RectF();
    }

    private void E(@NonNull Canvas canvas) {
        if (L(getCallback())) {
            return;
        }
        canvas.restoreToCount(this.A);
    }

    private void F(@NonNull Canvas canvas) {
        Drawable.Callback callback = getCallback();
        if (!L(callback)) {
            H(canvas);
            return;
        }
        View view = (View) callback;
        if (view.getLayerType() != 2) {
            view.setLayerType(2, null);
        }
    }

    private void H(@NonNull Canvas canvas) {
        this.A = Build.VERSION.SDK_INT >= 21 ? canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null) : canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null, 31);
    }

    private void K() {
        this.y.setStyle(Paint.Style.FILL_AND_STROKE);
        this.y.setColor(-1);
        this.y.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    private boolean L(Drawable.Callback callback) {
        return callback instanceof View;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean D() {
        return !this.z.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void G() {
        I(0.0f, 0.0f, 0.0f, 0.0f);
    }

    void I(float f, float f2, float f3, float f4) {
        RectF rectF = this.z;
        if (f == rectF.left && f2 == rectF.top && f3 == rectF.right && f4 == rectF.bottom) {
            return;
        }
        rectF.set(f, f2, f3, f4);
        invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void J(@NonNull RectF rectF) {
        I(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    @Override // com.google.android.material.shape.MaterialShapeDrawable, android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        F(canvas);
        super.draw(canvas);
        canvas.drawRect(this.z, this.y);
        E(canvas);
    }
}
