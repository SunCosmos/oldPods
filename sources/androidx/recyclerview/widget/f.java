package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
class f {
    int b;

    /* renamed from: c, reason: collision with root package name */
    int f1008c;

    /* renamed from: d, reason: collision with root package name */
    int f1009d;
    int e;
    boolean h;

    /* renamed from: i, reason: collision with root package name */
    boolean f1010i;
    boolean a = true;
    int f = 0;
    int g = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(RecyclerView.State state) {
        int i2 = this.f1008c;
        return i2 >= 0 && i2 < state.getItemCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View b(RecyclerView.Recycler recycler) {
        View viewForPosition = recycler.getViewForPosition(this.f1008c);
        this.f1008c += this.f1009d;
        return viewForPosition;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.b + ", mCurrentPosition=" + this.f1008c + ", mItemDirection=" + this.f1009d + ", mLayoutDirection=" + this.e + ", mStartLine=" + this.f + ", mEndLine=" + this.g + '}';
    }
}
