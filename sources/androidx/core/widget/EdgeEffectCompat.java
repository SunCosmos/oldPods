package androidx.core.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.widget.EdgeEffect;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public final class EdgeEffectCompat {
    private EdgeEffect a;

    @Deprecated
    public EdgeEffectCompat(Context context) {
        this.a = new EdgeEffect(context);
    }

    public static void onPull(@NonNull EdgeEffect edgeEffect, float f, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            edgeEffect.onPull(f, f2);
        } else {
            edgeEffect.onPull(f);
        }
    }

    @Deprecated
    public boolean draw(Canvas canvas) {
        return this.a.draw(canvas);
    }

    @Deprecated
    public void finish() {
        this.a.finish();
    }

    @Deprecated
    public boolean isFinished() {
        return this.a.isFinished();
    }

    @Deprecated
    public boolean onAbsorb(int i2) {
        this.a.onAbsorb(i2);
        return true;
    }

    @Deprecated
    public boolean onPull(float f) {
        this.a.onPull(f);
        return true;
    }

    @Deprecated
    public boolean onPull(float f, float f2) {
        onPull(this.a, f, f2);
        return true;
    }

    @Deprecated
    public boolean onRelease() {
        this.a.onRelease();
        return this.a.isFinished();
    }

    @Deprecated
    public void setSize(int i2, int i3) {
        this.a.setSize(i2, i3);
    }
}
