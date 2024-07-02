package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d extends Drawable.ConstantState {
    int a;
    Drawable.ConstantState b;

    /* renamed from: c, reason: collision with root package name */
    ColorStateList f554c;

    /* renamed from: d, reason: collision with root package name */
    PorterDuff.Mode f555d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(@Nullable d dVar) {
        this.f554c = null;
        this.f555d = b.g;
        if (dVar != null) {
            this.a = dVar.a;
            this.b = dVar.b;
            this.f554c = dVar.f554c;
            this.f555d = dVar.f555d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return this.b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i2 = this.a;
        Drawable.ConstantState constantState = this.b;
        return i2 | (constantState != null ? constantState.getChangingConfigurations() : 0);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    @NonNull
    public Drawable newDrawable() {
        return newDrawable(null);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    @NonNull
    public Drawable newDrawable(@Nullable Resources resources) {
        return Build.VERSION.SDK_INT >= 21 ? new c(this, resources) : new b(this, resources);
    }
}
