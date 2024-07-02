package android.support.v4.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;

/* loaded from: lib/Mus.dex */
public class IconCompat {
    private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25f;
    private static final int AMBIENT_SHADOW_ALPHA = 30;
    private static final float BLUR_FACTOR = 0.010416667f;
    private static final float DEFAULT_VIEW_PORT_SCALE = 0.6666667f;
    private static final float ICON_DIAMETER_FACTOR = 0.9166667f;
    private static final int KEY_SHADOW_ALPHA = 61;
    private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833334f;
    private static final int TYPE_ADAPTIVE_BITMAP = 5;
    private static final int TYPE_BITMAP = 1;
    private static final int TYPE_DATA = 3;
    private static final int TYPE_RESOURCE = 2;
    private static final int TYPE_URI = 4;
    private int mInt1;
    private int mInt2;
    private Object mObj1;
    private final int mType;

    public static IconCompat createWithResource(Context context, @DrawableRes int i2) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        IconCompat iconCompat = new IconCompat(2);
        iconCompat.mInt1 = i2;
        iconCompat.mObj1 = context;
        return iconCompat;
    }

    public static IconCompat createWithBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap must not be null.");
        }
        IconCompat iconCompat = new IconCompat(1);
        iconCompat.mObj1 = bitmap;
        return iconCompat;
    }

    public static IconCompat createWithAdaptiveBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap must not be null.");
        }
        IconCompat iconCompat = new IconCompat(5);
        iconCompat.mObj1 = bitmap;
        return iconCompat;
    }

    public static IconCompat createWithData(byte[] bArr, int i2, int i3) {
        if (bArr == null) {
            throw new IllegalArgumentException("Data must not be null.");
        }
        IconCompat iconCompat = new IconCompat(3);
        iconCompat.mObj1 = bArr;
        iconCompat.mInt1 = i2;
        iconCompat.mInt2 = i3;
        return iconCompat;
    }

    public static IconCompat createWithContentUri(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Uri must not be null.");
        }
        IconCompat iconCompat = new IconCompat(4);
        iconCompat.mObj1 = str;
        return iconCompat;
    }

    public static IconCompat createWithContentUri(Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("Uri must not be null.");
        }
        return createWithContentUri(uri.toString());
    }

    private IconCompat(int i2) {
        this.mType = i2;
    }

    @RequiresApi(23)
    public Icon toIcon() {
        switch (this.mType) {
            case 1:
                return Icon.createWithBitmap((Bitmap) this.mObj1);
            case 2:
                return Icon.createWithResource((Context) this.mObj1, this.mInt1);
            case 3:
                return Icon.createWithData((byte[]) this.mObj1, this.mInt1, this.mInt2);
            case 4:
                return Icon.createWithContentUri((String) this.mObj1);
            case 5:
                if (Build.VERSION.SDK_INT >= 26) {
                    return Icon.createWithAdaptiveBitmap((Bitmap) this.mObj1);
                }
                return Icon.createWithBitmap(createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, false));
            default:
                throw new IllegalArgumentException("Unknown type");
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Deprecated
    public void addToShortcutIntent(@NonNull Intent intent) {
        addToShortcutIntent(intent, null);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void addToShortcutIntent(@NonNull Intent intent, @Nullable Drawable drawable) {
        Bitmap createBitmap;
        switch (this.mType) {
            case 1:
                createBitmap = (Bitmap) this.mObj1;
                if (drawable != null) {
                    createBitmap = createBitmap.copy(createBitmap.getConfig(), true);
                    break;
                }
                break;
            case 2:
                if (drawable == null) {
                    intent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext((Context) this.mObj1, this.mInt1));
                    return;
                }
                Context context = (Context) this.mObj1;
                Drawable drawable2 = ContextCompat.getDrawable(context, this.mInt1);
                if (drawable2.getIntrinsicWidth() <= 0 || drawable2.getIntrinsicHeight() <= 0) {
                    int launcherLargeIconSize = ((ActivityManager) context.getSystemService("activity")).getLauncherLargeIconSize();
                    createBitmap = Bitmap.createBitmap(launcherLargeIconSize, launcherLargeIconSize, Bitmap.Config.ARGB_8888);
                } else {
                    createBitmap = Bitmap.createBitmap(drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                }
                drawable2.setBounds(0, 0, createBitmap.getWidth(), createBitmap.getHeight());
                drawable2.draw(new Canvas(createBitmap));
                break;
                break;
            case 3:
            case 4:
            default:
                throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
            case 5:
                createBitmap = createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, true);
                break;
        }
        if (drawable != null) {
            int width = createBitmap.getWidth();
            int height = createBitmap.getHeight();
            drawable.setBounds(width / 2, height / 2, width, height);
            drawable.draw(new Canvas(createBitmap));
        }
        intent.putExtra("android.intent.extra.shortcut.ICON", createBitmap);
    }

    @VisibleForTesting
    static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap bitmap, boolean z) {
        int min = (int) (DEFAULT_VIEW_PORT_SCALE * Math.min(bitmap.getWidth(), bitmap.getHeight()));
        Bitmap createBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(3);
        float f = min * 0.5f;
        float f2 = f * ICON_DIAMETER_FACTOR;
        if (z) {
            float f3 = BLUR_FACTOR * min;
            paint.setColor(0);
            paint.setShadowLayer(f3, 0.0f, KEY_SHADOW_OFFSET_FACTOR * min, 1023410176);
            canvas.drawCircle(f, f, f2, paint);
            paint.setShadowLayer(f3, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f, f, f2, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(-16777216);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix matrix = new Matrix();
        matrix.setTranslate((-(bitmap.getWidth() - min)) / 2, (-(bitmap.getHeight() - min)) / 2);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f, f, f2, paint);
        canvas.setBitmap(null);
        return createBitmap;
    }
}
