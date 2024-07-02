package pl.droidsonroids.gif;

import androidx.annotation.NonNull;
import java.io.IOException;

/* loaded from: classes.dex */
public class GifIOException extends IOException {

    @NonNull
    public final d a;
    private final String b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GifIOException(int i2, String str) {
        this.a = d.a(i2);
        this.b = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GifIOException a(int i2) {
        if (i2 == d.NO_ERROR.b) {
            return null;
        }
        return new GifIOException(i2, null);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        if (this.b == null) {
            return this.a.b();
        }
        return this.a.b() + ": " + this.b;
    }
}
