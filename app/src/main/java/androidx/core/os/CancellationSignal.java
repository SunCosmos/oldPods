package androidx.core.os;

import android.os.Build;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public final class CancellationSignal {
    private boolean a;
    private OnCancelListener b;

    /* renamed from: c, reason: collision with root package name */
    private Object f564c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f565d;

    /* loaded from: classes.dex */
    public interface OnCancelListener {
        void onCancel();
    }

    private void a() {
        while (this.f565d) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public void cancel() {
        synchronized (this) {
            if (this.a) {
                return;
            }
            this.a = true;
            this.f565d = true;
            OnCancelListener onCancelListener = this.b;
            Object obj = this.f564c;
            if (onCancelListener != null) {
                try {
                    onCancelListener.onCancel();
                } catch (Throwable th) {
                    synchronized (this) {
                        this.f565d = false;
                        notifyAll();
                        throw th;
                    }
                }
            }
            if (obj != null && Build.VERSION.SDK_INT >= 16) {
                ((android.os.CancellationSignal) obj).cancel();
            }
            synchronized (this) {
                this.f565d = false;
                notifyAll();
            }
        }
    }

    @Nullable
    public Object getCancellationSignalObject() {
        Object obj;
        if (Build.VERSION.SDK_INT < 16) {
            return null;
        }
        synchronized (this) {
            if (this.f564c == null) {
                android.os.CancellationSignal cancellationSignal = new android.os.CancellationSignal();
                this.f564c = cancellationSignal;
                if (this.a) {
                    cancellationSignal.cancel();
                }
            }
            obj = this.f564c;
        }
        return obj;
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this) {
            z = this.a;
        }
        return z;
    }

    public void setOnCancelListener(@Nullable OnCancelListener onCancelListener) {
        synchronized (this) {
            a();
            if (this.b == onCancelListener) {
                return;
            }
            this.b = onCancelListener;
            if (this.a && onCancelListener != null) {
                onCancelListener.onCancel();
            }
        }
    }

    public void throwIfCanceled() {
        if (isCanceled()) {
            throw new OperationCanceledException();
        }
    }
}
