package androidx.recyclerview.widget;

import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public class BatchingListUpdateCallback implements ListUpdateCallback {
    final ListUpdateCallback a;
    int b = 0;

    /* renamed from: c, reason: collision with root package name */
    int f907c = -1;

    /* renamed from: d, reason: collision with root package name */
    int f908d = -1;
    Object e = null;

    public BatchingListUpdateCallback(@NonNull ListUpdateCallback listUpdateCallback) {
        this.a = listUpdateCallback;
    }

    public void dispatchLastEvent() {
        int i2 = this.b;
        if (i2 == 0) {
            return;
        }
        if (i2 == 1) {
            this.a.onInserted(this.f907c, this.f908d);
        } else if (i2 == 2) {
            this.a.onRemoved(this.f907c, this.f908d);
        } else if (i2 == 3) {
            this.a.onChanged(this.f907c, this.f908d, this.e);
        }
        this.e = null;
        this.b = 0;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onChanged(int i2, int i3, Object obj) {
        int i4;
        if (this.b == 3) {
            int i5 = this.f907c;
            int i6 = this.f908d;
            if (i2 <= i5 + i6 && (i4 = i2 + i3) >= i5 && this.e == obj) {
                this.f907c = Math.min(i2, i5);
                this.f908d = Math.max(i6 + i5, i4) - this.f907c;
                return;
            }
        }
        dispatchLastEvent();
        this.f907c = i2;
        this.f908d = i3;
        this.e = obj;
        this.b = 3;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onInserted(int i2, int i3) {
        int i4;
        if (this.b == 1 && i2 >= (i4 = this.f907c)) {
            int i5 = this.f908d;
            if (i2 <= i4 + i5) {
                this.f908d = i5 + i3;
                this.f907c = Math.min(i2, i4);
                return;
            }
        }
        dispatchLastEvent();
        this.f907c = i2;
        this.f908d = i3;
        this.b = 1;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onMoved(int i2, int i3) {
        dispatchLastEvent();
        this.a.onMoved(i2, i3);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onRemoved(int i2, int i3) {
        int i4;
        if (this.b == 2 && (i4 = this.f907c) >= i2 && i4 <= i2 + i3) {
            this.f908d += i3;
            this.f907c = i2;
        } else {
            dispatchLastEvent();
            this.f907c = i2;
            this.f908d = i3;
            this.b = 2;
        }
    }
}
