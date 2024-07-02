package androidx.recyclerview.widget;

import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public interface ListUpdateCallback {
    void onChanged(int i2, int i3, @Nullable Object obj);

    void onInserted(int i2, int i3);

    void onMoved(int i2, int i3);

    void onRemoved(int i2, int i3);
}
