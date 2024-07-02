package pl.droidsonroids.gif;

import android.content.res.AssetManager;
import android.content.res.Resources;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RawRes;

/* loaded from: classes.dex */
public abstract class g {

    /* loaded from: classes.dex */
    public static final class b extends g {
        private final AssetManager a;
        private final String b;

        public b(@NonNull AssetManager assetManager, @NonNull String str) {
            super();
            this.a = assetManager;
            this.b = str;
        }

        @Override // pl.droidsonroids.gif.g
        GifInfoHandle a() {
            return new GifInfoHandle(this.a.openFd(this.b));
        }
    }

    /* loaded from: classes.dex */
    public static class c extends g {
        private final Resources a;
        private final int b;

        public c(@NonNull Resources resources, @DrawableRes @RawRes int i2) {
            super();
            this.a = resources;
            this.b = i2;
        }

        @Override // pl.droidsonroids.gif.g
        GifInfoHandle a() {
            return new GifInfoHandle(this.a.openRawResourceFd(this.b));
        }
    }

    private g() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract GifInfoHandle a();
}
