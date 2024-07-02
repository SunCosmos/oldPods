package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;

/* loaded from: classes.dex */
class p {
    private static final boolean a;
    private static final boolean b;

    /* renamed from: c, reason: collision with root package name */
    private static final boolean f1087c;

    /* loaded from: classes.dex */
    static class a implements TypeEvaluator<Matrix> {
        final float[] a = new float[9];
        final float[] b = new float[9];

        /* renamed from: c, reason: collision with root package name */
        final Matrix f1088c = new Matrix();

        @Override // android.animation.TypeEvaluator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
            matrix.getValues(this.a);
            matrix2.getValues(this.b);
            for (int i2 = 0; i2 < 9; i2++) {
                float[] fArr = this.b;
                float f2 = fArr[i2];
                float[] fArr2 = this.a;
                fArr[i2] = fArr2[i2] + ((f2 - fArr2[i2]) * f);
            }
            this.f1088c.setValues(this.b);
            return this.f1088c;
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        a = i2 >= 19;
        b = i2 >= 18;
        f1087c = i2 >= 28;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static View a(ViewGroup viewGroup, View view, View view2) {
        Matrix matrix = new Matrix();
        matrix.setTranslate(-view2.getScrollX(), -view2.getScrollY());
        b0.j(view, matrix);
        b0.k(viewGroup, matrix);
        RectF rectF = new RectF(0.0f, 0.0f, view.getWidth(), view.getHeight());
        matrix.mapRect(rectF);
        int round = Math.round(rectF.left);
        int round2 = Math.round(rectF.top);
        int round3 = Math.round(rectF.right);
        int round4 = Math.round(rectF.bottom);
        ImageView imageView = new ImageView(view.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Bitmap b2 = b(view, matrix, rectF, viewGroup);
        if (b2 != null) {
            imageView.setImageBitmap(b2);
        }
        imageView.measure(View.MeasureSpec.makeMeasureSpec(round3 - round, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(round4 - round2, BasicMeasure.EXACTLY));
        imageView.layout(round, round2, round3, round4);
        return imageView;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Bitmap b(android.view.View r9, android.graphics.Matrix r10, android.graphics.RectF r11, android.view.ViewGroup r12) {
        /*
            boolean r0 = androidx.transition.p.a
            r1 = 0
            if (r0 == 0) goto L13
            boolean r0 = r9.isAttachedToWindow()
            r0 = r0 ^ 1
            if (r12 != 0) goto Le
            goto L14
        Le:
            boolean r2 = r12.isAttachedToWindow()
            goto L15
        L13:
            r0 = 0
        L14:
            r2 = 0
        L15:
            boolean r3 = androidx.transition.p.b
            r4 = 0
            if (r3 == 0) goto L31
            if (r0 == 0) goto L31
            if (r2 != 0) goto L1f
            return r4
        L1f:
            android.view.ViewParent r1 = r9.getParent()
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            int r2 = r1.indexOfChild(r9)
            android.view.ViewGroupOverlay r5 = r12.getOverlay()
            r5.add(r9)
            goto L33
        L31:
            r1 = r4
            r2 = 0
        L33:
            float r5 = r11.width()
            int r5 = java.lang.Math.round(r5)
            float r6 = r11.height()
            int r6 = java.lang.Math.round(r6)
            if (r5 <= 0) goto L99
            if (r6 <= 0) goto L99
            r4 = 1065353216(0x3f800000, float:1.0)
            r7 = 1233125376(0x49800000, float:1048576.0)
            int r8 = r5 * r6
            float r8 = (float) r8
            float r7 = r7 / r8
            float r4 = java.lang.Math.min(r4, r7)
            float r5 = (float) r5
            float r5 = r5 * r4
            int r5 = java.lang.Math.round(r5)
            float r6 = (float) r6
            float r6 = r6 * r4
            int r6 = java.lang.Math.round(r6)
            float r7 = r11.left
            float r7 = -r7
            float r11 = r11.top
            float r11 = -r11
            r10.postTranslate(r7, r11)
            r10.postScale(r4, r4)
            boolean r11 = androidx.transition.p.f1087c
            if (r11 == 0) goto L88
            android.graphics.Picture r11 = new android.graphics.Picture
            r11.<init>()
            android.graphics.Canvas r4 = r11.beginRecording(r5, r6)
            r4.concat(r10)
            r9.draw(r4)
            r11.endRecording()
            android.graphics.Bitmap r4 = android.graphics.Bitmap.createBitmap(r11)
            goto L99
        L88:
            android.graphics.Bitmap$Config r11 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r4 = android.graphics.Bitmap.createBitmap(r5, r6, r11)
            android.graphics.Canvas r11 = new android.graphics.Canvas
            r11.<init>(r4)
            r11.concat(r10)
            r9.draw(r11)
        L99:
            if (r3 == 0) goto La7
            if (r0 == 0) goto La7
            android.view.ViewGroupOverlay r10 = r12.getOverlay()
            r10.remove(r9)
            r1.addView(r9, r2)
        La7:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.p.b(android.view.View, android.graphics.Matrix, android.graphics.RectF, android.view.ViewGroup):android.graphics.Bitmap");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Animator c(Animator animator, Animator animator2) {
        if (animator == null) {
            return animator2;
        }
        if (animator2 == null) {
            return animator;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator, animator2);
        return animatorSet;
    }
}
