package androidx.transition;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public class t extends y implements v {
    public t(Context context, ViewGroup viewGroup, View view) {
        super(context, viewGroup, view);
    }

    public static t e(ViewGroup viewGroup) {
        return (t) y.c(viewGroup);
    }

    @Override // androidx.transition.v
    public void a(@NonNull View view) {
        this.a.b(view);
    }

    @Override // androidx.transition.v
    public void b(@NonNull View view) {
        this.a.g(view);
    }
}
