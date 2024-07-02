package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;

/* loaded from: classes.dex */
abstract class d<S extends BaseProgressIndicatorSpec> {
    S a;
    protected c b;

    public d(S s) {
        this.a = s;
    }

    abstract void a(@NonNull Canvas canvas, @FloatRange(from = 0.0d, to = 1.0d) float f);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void b(@NonNull Canvas canvas, @NonNull Paint paint, @FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2, @ColorInt int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void c(@NonNull Canvas canvas, @NonNull Paint paint);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int d();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int e();

    /* JADX INFO: Access modifiers changed from: protected */
    public void f(@NonNull c cVar) {
        this.b = cVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(@NonNull Canvas canvas, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.a.c();
        a(canvas, f);
    }
}
