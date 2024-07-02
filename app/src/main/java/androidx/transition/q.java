package androidx.transition;

import android.util.SparseArray;
import android.view.View;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class q {
    final ArrayMap<View, TransitionValues> a = new ArrayMap<>();
    final SparseArray<View> b = new SparseArray<>();

    /* renamed from: c, reason: collision with root package name */
    final LongSparseArray<View> f1089c = new LongSparseArray<>();

    /* renamed from: d, reason: collision with root package name */
    final ArrayMap<String, View> f1090d = new ArrayMap<>();
}
