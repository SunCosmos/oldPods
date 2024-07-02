package pl.droidsonroids.gif;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RawRes;
import bsh.org.objectweb.asm.Constants;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class f {
    static final List<String> a = Arrays.asList("raw", "drawable", "mipmap");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends b {

        /* renamed from: c */
        final int f2073c;

        /* renamed from: d */
        final int f2074d;

        a() {
            this.f2073c = 0;
            this.f2074d = 0;
        }

        a(ImageView imageView, AttributeSet attributeSet, int i2, int i3) {
            super(imageView, attributeSet, i2, i3);
            this.f2073c = a(imageView, attributeSet, true);
            this.f2074d = a(imageView, attributeSet, false);
        }

        private static int a(ImageView imageView, AttributeSet attributeSet, boolean z) {
            int attributeResourceValue = attributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", z ? "src" : "background", 0);
            if (attributeResourceValue > 0) {
                if (f.a.contains(imageView.getResources().getResourceTypeName(attributeResourceValue)) && !f.e(imageView, z, attributeResourceValue)) {
                    return attributeResourceValue;
                }
            }
            return 0;
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        boolean a;
        final int b;

        public b() {
            this.a = false;
            this.b = -1;
        }

        public b(View view, AttributeSet attributeSet, int i2, int i3) {
            TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, k.f2076d, i2, i3);
            this.a = obtainStyledAttributes.getBoolean(k.e, false);
            this.b = obtainStyledAttributes.getInt(k.f, -1);
            obtainStyledAttributes.recycle();
        }
    }

    public static void a(int i2, Drawable drawable) {
        if (drawable instanceof c) {
            ((c) drawable).h(i2);
        }
    }

    public static float b(@NonNull Resources resources, @DrawableRes @RawRes int i2) {
        TypedValue typedValue = new TypedValue();
        resources.getValue(i2, typedValue, true);
        int i3 = typedValue.density;
        if (i3 == 0) {
            i3 = Constants.IF_ICMPNE;
        } else if (i3 == 65535) {
            i3 = 0;
        }
        int i4 = resources.getDisplayMetrics().densityDpi;
        if (i3 <= 0 || i4 <= 0) {
            return 1.0f;
        }
        return i4 / i3;
    }

    public static a c(ImageView imageView, AttributeSet attributeSet, int i2, int i3) {
        if (attributeSet == null || imageView.isInEditMode()) {
            return new a();
        }
        a aVar = new a(imageView, attributeSet, i2, i3);
        int i4 = aVar.b;
        if (i4 >= 0) {
            a(i4, imageView.getDrawable());
            a(i4, imageView.getBackground());
        }
        return aVar;
    }

    public static boolean d(ImageView imageView, Uri uri) {
        if (uri == null) {
            return false;
        }
        try {
            imageView.setImageDrawable(new c(imageView.getContext().getContentResolver(), uri));
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean e(ImageView imageView, boolean z, int i2) {
        Resources resources = imageView.getResources();
        if (resources == null) {
            return false;
        }
        try {
            c cVar = new c(resources, i2);
            if (z) {
                imageView.setImageDrawable(cVar);
                return true;
            }
            if (Build.VERSION.SDK_INT >= 16) {
                imageView.setBackground(cVar);
                return true;
            }
            imageView.setBackgroundDrawable(cVar);
            return true;
        } catch (Resources.NotFoundException | IOException unused) {
            return false;
        }
    }
}
