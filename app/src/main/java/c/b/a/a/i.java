package c.b.a.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class i {
    public static Bitmap a(ImageView imageView) {
        return ((BitmapDrawable) imageView.getDrawable()).getBitmap();
    }

    public static Bitmap b(Context context, String str) {
        Bitmap bitmap = null;
        try {
            InputStream open = context.getAssets().open(str);
            try {
                bitmap = BitmapFactory.decodeStream(open);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (open != null) {
                try {
                    open.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            return bitmap;
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static Bitmap c(String str) {
        if (str == null || !new File(str).exists()) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFile(str, options);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Bitmap d(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap e(Bitmap bitmap, int i2, int i3) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i2 / width, i3 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap f(Bitmap bitmap, int i2) {
        float[] fArr = i2 != 0 ? i2 != 1 ? null : new float[]{1.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f} : new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f};
        if (fArr == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.setValues(fArr);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap g(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static void h(Bitmap bitmap, int i2, String str) {
        d.b(str, false);
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        bitmap.compress(str.toLowerCase().endsWith(".png") ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, i2, fileOutputStream);
        try {
            fileOutputStream.flush();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
            fileOutputStream.close();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }
}
