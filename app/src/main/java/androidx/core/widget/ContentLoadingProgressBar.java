package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {
    long a;
    boolean b;

    /* renamed from: c, reason: collision with root package name */
    boolean f664c;

    /* renamed from: d, reason: collision with root package name */
    boolean f665d;
    private final Runnable e;
    private final Runnable f;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.b = false;
            contentLoadingProgressBar.a = -1L;
            contentLoadingProgressBar.setVisibility(8);
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ContentLoadingProgressBar contentLoadingProgressBar = ContentLoadingProgressBar.this;
            contentLoadingProgressBar.f664c = false;
            if (contentLoadingProgressBar.f665d) {
                return;
            }
            contentLoadingProgressBar.a = System.currentTimeMillis();
            ContentLoadingProgressBar.this.setVisibility(0);
        }
    }

    public ContentLoadingProgressBar(@NonNull Context context) {
        this(context, null);
    }

    public ContentLoadingProgressBar(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.a = -1L;
        this.b = false;
        this.f664c = false;
        this.f665d = false;
        this.e = new a();
        this.f = new b();
    }

    private void a() {
        removeCallbacks(this.e);
        removeCallbacks(this.f);
    }

    public synchronized void hide() {
        this.f665d = true;
        removeCallbacks(this.f);
        this.f664c = false;
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.a;
        long j2 = currentTimeMillis - j;
        if (j2 < 500 && j != -1) {
            if (!this.b) {
                postDelayed(this.e, 500 - j2);
                this.b = true;
            }
        }
        setVisibility(8);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    public synchronized void show() {
        this.a = -1L;
        this.f665d = false;
        removeCallbacks(this.e);
        this.b = false;
        if (!this.f664c) {
            postDelayed(this.f, 500L);
            this.f664c = true;
        }
    }
}
