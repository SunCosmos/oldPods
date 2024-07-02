package androidx.core.hardware.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.core.os.CancellationSignal;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

@Deprecated
/* loaded from: classes.dex */
public class FingerprintManagerCompat {
    private final Context a;

    /* loaded from: classes.dex */
    public static abstract class AuthenticationCallback {
        public void onAuthenticationError(int i2, CharSequence charSequence) {
        }

        public void onAuthenticationFailed() {
        }

        public void onAuthenticationHelp(int i2, CharSequence charSequence) {
        }

        public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
        }
    }

    /* loaded from: classes.dex */
    public static final class AuthenticationResult {
        private final CryptoObject a;

        public AuthenticationResult(CryptoObject cryptoObject) {
            this.a = cryptoObject;
        }

        public CryptoObject getCryptoObject() {
            return this.a;
        }
    }

    /* loaded from: classes.dex */
    public static class CryptoObject {
        private final Signature a;
        private final Cipher b;

        /* renamed from: c, reason: collision with root package name */
        private final Mac f556c;

        public CryptoObject(@NonNull Signature signature) {
            this.a = signature;
            this.b = null;
            this.f556c = null;
        }

        public CryptoObject(@NonNull Cipher cipher) {
            this.b = cipher;
            this.a = null;
            this.f556c = null;
        }

        public CryptoObject(@NonNull Mac mac) {
            this.f556c = mac;
            this.b = null;
            this.a = null;
        }

        @Nullable
        public Cipher getCipher() {
            return this.b;
        }

        @Nullable
        public Mac getMac() {
            return this.f556c;
        }

        @Nullable
        public Signature getSignature() {
            return this.a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends FingerprintManager.AuthenticationCallback {
        final /* synthetic */ AuthenticationCallback a;

        a(AuthenticationCallback authenticationCallback) {
            this.a = authenticationCallback;
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationError(int i2, CharSequence charSequence) {
            this.a.onAuthenticationError(i2, charSequence);
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationFailed() {
            this.a.onAuthenticationFailed();
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationHelp(int i2, CharSequence charSequence) {
            this.a.onAuthenticationHelp(i2, charSequence);
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
            this.a.onAuthenticationSucceeded(new AuthenticationResult(FingerprintManagerCompat.b(authenticationResult.getCryptoObject())));
        }
    }

    private FingerprintManagerCompat(Context context) {
        this.a = context;
    }

    @Nullable
    @RequiresApi(23)
    private static FingerprintManager a(@NonNull Context context) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 != 23 && (i2 <= 23 || !context.getPackageManager().hasSystemFeature("android.hardware.fingerprint"))) {
            return null;
        }
        return (FingerprintManager) context.getSystemService(FingerprintManager.class);
    }

    @RequiresApi(23)
    static CryptoObject b(FingerprintManager.CryptoObject cryptoObject) {
        if (cryptoObject == null) {
            return null;
        }
        if (cryptoObject.getCipher() != null) {
            return new CryptoObject(cryptoObject.getCipher());
        }
        if (cryptoObject.getSignature() != null) {
            return new CryptoObject(cryptoObject.getSignature());
        }
        if (cryptoObject.getMac() != null) {
            return new CryptoObject(cryptoObject.getMac());
        }
        return null;
    }

    @RequiresApi(23)
    private static FingerprintManager.AuthenticationCallback c(AuthenticationCallback authenticationCallback) {
        return new a(authenticationCallback);
    }

    @RequiresApi(23)
    private static FingerprintManager.CryptoObject d(CryptoObject cryptoObject) {
        if (cryptoObject == null) {
            return null;
        }
        if (cryptoObject.getCipher() != null) {
            return new FingerprintManager.CryptoObject(cryptoObject.getCipher());
        }
        if (cryptoObject.getSignature() != null) {
            return new FingerprintManager.CryptoObject(cryptoObject.getSignature());
        }
        if (cryptoObject.getMac() != null) {
            return new FingerprintManager.CryptoObject(cryptoObject.getMac());
        }
        return null;
    }

    @NonNull
    public static FingerprintManagerCompat from(@NonNull Context context) {
        return new FingerprintManagerCompat(context);
    }

    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public void authenticate(@Nullable CryptoObject cryptoObject, int i2, @Nullable CancellationSignal cancellationSignal, @NonNull AuthenticationCallback authenticationCallback, @Nullable Handler handler) {
        FingerprintManager a2;
        if (Build.VERSION.SDK_INT < 23 || (a2 = a(this.a)) == null) {
            return;
        }
        a2.authenticate(d(cryptoObject), cancellationSignal != null ? (android.os.CancellationSignal) cancellationSignal.getCancellationSignalObject() : null, i2, c(authenticationCallback), handler);
    }

    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public boolean hasEnrolledFingerprints() {
        FingerprintManager a2;
        return Build.VERSION.SDK_INT >= 23 && (a2 = a(this.a)) != null && a2.hasEnrolledFingerprints();
    }

    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public boolean isHardwareDetected() {
        FingerprintManager a2;
        return Build.VERSION.SDK_INT >= 23 && (a2 = a(this.a)) != null && a2.isHardwareDetected();
    }
}
