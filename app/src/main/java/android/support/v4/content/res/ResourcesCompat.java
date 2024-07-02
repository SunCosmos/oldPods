package android.support.v4.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.Preconditions;
import android.util.TypedValue;

/* loaded from: lib/Mus.dex */
public final class ResourcesCompat {
    private static final String TAG = "ResourcesCompat";

    @Nullable
    public static Drawable getDrawable(@NonNull Resources resources, @DrawableRes int i2, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT < 21) {
            return resources.getDrawable(i2);
        }
        return resources.getDrawable(i2, theme);
    }

    @Nullable
    public static Drawable getDrawableForDensity(@NonNull Resources resources, @DrawableRes int i2, int i3, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 21) {
            return resources.getDrawableForDensity(i2, i3, theme);
        }
        if (Build.VERSION.SDK_INT >= 15) {
            return resources.getDrawableForDensity(i2, i3);
        }
        return resources.getDrawable(i2);
    }

    @ColorInt
    public static int getColor(@NonNull Resources resources, @ColorRes int i2, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT < 23) {
            return resources.getColor(i2);
        }
        return resources.getColor(i2, theme);
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Resources resources, @ColorRes int i2, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT < 23) {
            return resources.getColorStateList(i2);
        }
        return resources.getColorStateList(i2, theme);
    }

    @Nullable
    public static Typeface getFont(@NonNull Context context, @FontRes int i2) throws Resources.NotFoundException {
        if (!context.isRestricted()) {
            return loadFont(context, i2, new TypedValue(), 0, null, null, false);
        }
        return null;
    }

    /* loaded from: lib/Mus.dex */
    public static abstract class FontCallback {
        public abstract void onFontRetrievalFailed(int i2);

        public abstract void onFontRetrieved(@NonNull Typeface typeface);

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final void callbackSuccessAsync(Typeface typeface, @Nullable Handler handler) {
            Handler handler2 = handler;
            if (handler2 == null) {
                handler2 = new Handler(Looper.getMainLooper());
            }
            handler2.post(new Runnable() { // from class: android.support.v4.content.res.ResourcesCompat.FontCallback.1
                final /* synthetic */ Typeface val$typeface;

                AnonymousClass1(Typeface typeface2) {
                    typeface = typeface2;
                }

                @Override // java.lang.Runnable
                public void run() {
                    FontCallback.this.onFontRetrieved(typeface);
                }
            });
        }

        /* renamed from: android.support.v4.content.res.ResourcesCompat$FontCallback$1 */
        /* loaded from: lib/Mus.dex */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ Typeface val$typeface;

            AnonymousClass1(Typeface typeface2) {
                typeface = typeface2;
            }

            @Override // java.lang.Runnable
            public void run() {
                FontCallback.this.onFontRetrieved(typeface);
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public final void callbackFailAsync(int i2, @Nullable Handler handler) {
            Handler handler2 = handler;
            if (handler2 == null) {
                handler2 = new Handler(Looper.getMainLooper());
            }
            handler2.post(new Runnable() { // from class: android.support.v4.content.res.ResourcesCompat.FontCallback.2
                final /* synthetic */ int val$reason;

                AnonymousClass2(int i22) {
                    i2 = i22;
                }

                @Override // java.lang.Runnable
                public void run() {
                    FontCallback.this.onFontRetrievalFailed(i2);
                }
            });
        }

        /* renamed from: android.support.v4.content.res.ResourcesCompat$FontCallback$2 */
        /* loaded from: lib/Mus.dex */
        public class AnonymousClass2 implements Runnable {
            final /* synthetic */ int val$reason;

            AnonymousClass2(int i22) {
                i2 = i22;
            }

            @Override // java.lang.Runnable
            public void run() {
                FontCallback.this.onFontRetrievalFailed(i2);
            }
        }
    }

    public static void getFont(@NonNull Context context, @FontRes int i2, @NonNull FontCallback fontCallback, @Nullable Handler handler) throws Resources.NotFoundException {
        Preconditions.checkNotNull(fontCallback);
        if (context.isRestricted()) {
            fontCallback.callbackFailAsync(-4, handler);
        } else {
            loadFont(context, i2, new TypedValue(), 0, fontCallback, handler, false);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Typeface getFont(@NonNull Context context, @FontRes int i2, TypedValue typedValue, int i3, @Nullable FontCallback fontCallback) throws Resources.NotFoundException {
        if (!context.isRestricted()) {
            return loadFont(context, i2, typedValue, i3, fontCallback, null, true);
        }
        return null;
    }

    private static Typeface loadFont(@NonNull Context context, int i2, TypedValue typedValue, int i3, @Nullable FontCallback fontCallback, @Nullable Handler handler, boolean z) {
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        Typeface loadFont = loadFont(context, resources, typedValue, i2, i3, fontCallback, handler, z);
        if (loadFont == null && fontCallback == null) {
            throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i2) + " could not be retrieved.");
        }
        return loadFont;
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x011a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Typeface loadFont(@android.support.annotation.NonNull android.content.Context r21, android.content.res.Resources r22, android.util.TypedValue r23, int r24, int r25, @android.support.annotation.Nullable android.support.v4.content.res.ResourcesCompat.FontCallback r26, @android.support.annotation.Nullable android.os.Handler r27, boolean r28) {
        /*
            Method dump skipped, instructions count: 328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.res.ResourcesCompat.loadFont(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, android.support.v4.content.res.ResourcesCompat$FontCallback, android.os.Handler, boolean):android.graphics.Typeface");
    }

    private ResourcesCompat() {
    }
}
