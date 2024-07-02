package androidx.transition;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Field;

/* loaded from: classes.dex */
class i {
    private static boolean a = true;
    private static Field b;

    /* renamed from: c, reason: collision with root package name */
    private static boolean f1081c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull ImageView imageView, @Nullable Matrix matrix) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            imageView.animateTransform(matrix);
            return;
        }
        if (matrix == null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable == null) {
                return;
            } else {
                drawable.setBounds(0, 0, (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight(), (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom());
            }
        } else {
            if (i2 >= 21) {
                c(imageView, matrix);
                return;
            }
            Drawable drawable2 = imageView.getDrawable();
            if (drawable2 == null) {
                return;
            }
            drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
            Matrix matrix2 = null;
            b();
            Field field = b;
            if (field != null) {
                try {
                    Matrix matrix3 = (Matrix) field.get(imageView);
                    if (matrix3 == null) {
                        try {
                            matrix2 = new Matrix();
                            b.set(imageView, matrix2);
                        } catch (IllegalAccessException unused) {
                        }
                    }
                    matrix2 = matrix3;
                } catch (IllegalAccessException unused2) {
                }
            }
            if (matrix2 != null) {
                matrix2.set(matrix);
            }
        }
        imageView.invalidate();
    }

    private static void b() {
        if (f1081c) {
            return;
        }
        try {
            Field declaredField = ImageView.class.getDeclaredField("mDrawMatrix");
            b = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException unused) {
        }
        f1081c = true;
    }

    @RequiresApi(21)
    @SuppressLint({"NewApi"})
    private static void c(@NonNull ImageView imageView, @Nullable Matrix matrix) {
        if (a) {
            try {
                imageView.animateTransform(matrix);
            } catch (NoSuchMethodError unused) {
                a = false;
            }
        }
    }
}
