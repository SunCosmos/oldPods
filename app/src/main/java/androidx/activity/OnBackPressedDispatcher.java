package androidx.activity;

import android.annotation.SuppressLint;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayDeque;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    @Nullable
    private final Runnable a;
    final ArrayDeque<OnBackPressedCallback> b;

    /* loaded from: classes.dex */
    private class LifecycleOnBackPressedCancellable implements LifecycleEventObserver, androidx.activity.a {
        private final Lifecycle a;
        private final OnBackPressedCallback b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        private androidx.activity.a f5c;

        LifecycleOnBackPressedCancellable(@NonNull Lifecycle lifecycle, @NonNull OnBackPressedCallback onBackPressedCallback) {
            this.a = lifecycle;
            this.b = onBackPressedCallback;
            lifecycle.addObserver(this);
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.a.removeObserver(this);
            this.b.b(this);
            androidx.activity.a aVar = this.f5c;
            if (aVar != null) {
                aVar.cancel();
                this.f5c = null;
            }
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.f5c = OnBackPressedDispatcher.this.a(this.b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar = this.f5c;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a implements androidx.activity.a {
        private final OnBackPressedCallback a;

        a(OnBackPressedCallback onBackPressedCallback) {
            this.a = onBackPressedCallback;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.b.remove(this.a);
            this.a.b(this);
        }
    }

    public OnBackPressedDispatcher() {
        this(null);
    }

    public OnBackPressedDispatcher(@Nullable Runnable runnable) {
        this.b = new ArrayDeque<>();
        this.a = runnable;
    }

    @NonNull
    @MainThread
    androidx.activity.a a(@NonNull OnBackPressedCallback onBackPressedCallback) {
        this.b.add(onBackPressedCallback);
        a aVar = new a(onBackPressedCallback);
        onBackPressedCallback.a(aVar);
        return aVar;
    }

    @MainThread
    public void addCallback(@NonNull OnBackPressedCallback onBackPressedCallback) {
        a(onBackPressedCallback);
    }

    @SuppressLint({"LambdaLast"})
    @MainThread
    public void addCallback(@NonNull LifecycleOwner lifecycleOwner, @NonNull OnBackPressedCallback onBackPressedCallback) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
            return;
        }
        onBackPressedCallback.a(new LifecycleOnBackPressedCancellable(lifecycle, onBackPressedCallback));
    }

    @MainThread
    public boolean hasEnabledCallbacks() {
        Iterator<OnBackPressedCallback> descendingIterator = this.b.descendingIterator();
        while (descendingIterator.hasNext()) {
            if (descendingIterator.next().isEnabled()) {
                return true;
            }
        }
        return false;
    }

    @MainThread
    public void onBackPressed() {
        Iterator<OnBackPressedCallback> descendingIterator = this.b.descendingIterator();
        while (descendingIterator.hasNext()) {
            OnBackPressedCallback next = descendingIterator.next();
            if (next.isEnabled()) {
                next.handleOnBackPressed();
                return;
            }
        }
        Runnable runnable = this.a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
