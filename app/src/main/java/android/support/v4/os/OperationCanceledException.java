package android.support.v4.os;

/* loaded from: lib/Mus.dex */
public class OperationCanceledException extends RuntimeException {
    public OperationCanceledException() {
        this(null);
    }

    public OperationCanceledException(String str) {
        super(str != null ? str : "The operation has been canceled.");
    }
}
