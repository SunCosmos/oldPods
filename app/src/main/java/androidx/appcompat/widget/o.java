package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class o extends l {
    private final WeakReference<Context> b;

    public o(@NonNull Context context, @NonNull Resources resources) {
        super(resources);
        this.b = new WeakReference<>(context);
    }

    @Override // androidx.appcompat.widget.l, android.content.res.Resources
    public Drawable getDrawable(int i2) {
        Drawable drawable = super.getDrawable(i2);
        Context context = this.b.get();
        if (drawable != null && context != null) {
            ResourceManagerInternal.get().s(context, i2, drawable);
        }
        return drawable;
    }
}
