package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.savedstate.SavedStateRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class SavedStateHandle {
    private static final Class[] e;
    final Map<String, Object> a;
    final Map<String, SavedStateRegistry.SavedStateProvider> b;

    /* renamed from: c, reason: collision with root package name */
    private final Map<String, b<?>> f819c;

    /* renamed from: d, reason: collision with root package name */
    private final SavedStateRegistry.SavedStateProvider f820d;

    /* loaded from: classes.dex */
    class a implements SavedStateRegistry.SavedStateProvider {
        a() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
        @NonNull
        public Bundle saveState() {
            for (Map.Entry entry : new HashMap(SavedStateHandle.this.b).entrySet()) {
                SavedStateHandle.this.set((String) entry.getKey(), ((SavedStateRegistry.SavedStateProvider) entry.getValue()).saveState());
            }
            Set<String> keySet = SavedStateHandle.this.a.keySet();
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(keySet.size());
            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>(arrayList.size());
            for (String str : keySet) {
                arrayList.add(str);
                arrayList2.add(SavedStateHandle.this.a.get(str));
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("keys", arrayList);
            bundle.putParcelableArrayList("values", arrayList2);
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b<T> extends MutableLiveData<T> {
        private String l;
        private SavedStateHandle m;

        b(SavedStateHandle savedStateHandle, String str) {
            this.l = str;
            this.m = savedStateHandle;
        }

        b(SavedStateHandle savedStateHandle, String str, T t) {
            super(t);
            this.l = str;
            this.m = savedStateHandle;
        }

        void h() {
            this.m = null;
        }

        @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
        public void setValue(T t) {
            SavedStateHandle savedStateHandle = this.m;
            if (savedStateHandle != null) {
                savedStateHandle.a.put(this.l, t);
            }
            super.setValue(t);
        }
    }

    static {
        Class[] clsArr = new Class[29];
        clsArr[0] = Boolean.TYPE;
        clsArr[1] = boolean[].class;
        clsArr[2] = Double.TYPE;
        clsArr[3] = double[].class;
        Class<SizeF> cls = Integer.TYPE;
        clsArr[4] = cls;
        clsArr[5] = int[].class;
        clsArr[6] = Long.TYPE;
        clsArr[7] = long[].class;
        clsArr[8] = String.class;
        clsArr[9] = String[].class;
        clsArr[10] = Binder.class;
        clsArr[11] = Bundle.class;
        clsArr[12] = Byte.TYPE;
        clsArr[13] = byte[].class;
        clsArr[14] = Character.TYPE;
        clsArr[15] = char[].class;
        clsArr[16] = CharSequence.class;
        clsArr[17] = CharSequence[].class;
        clsArr[18] = ArrayList.class;
        clsArr[19] = Float.TYPE;
        clsArr[20] = float[].class;
        clsArr[21] = Parcelable.class;
        clsArr[22] = Parcelable[].class;
        clsArr[23] = Serializable.class;
        clsArr[24] = Short.TYPE;
        clsArr[25] = short[].class;
        clsArr[26] = SparseArray.class;
        int i2 = Build.VERSION.SDK_INT;
        clsArr[27] = i2 >= 21 ? Size.class : cls;
        if (i2 >= 21) {
            cls = SizeF.class;
        }
        clsArr[28] = cls;
        e = clsArr;
    }

    public SavedStateHandle() {
        this.b = new HashMap();
        this.f819c = new HashMap();
        this.f820d = new a();
        this.a = new HashMap();
    }

    public SavedStateHandle(@NonNull Map<String, Object> map) {
        this.b = new HashMap();
        this.f819c = new HashMap();
        this.f820d = new a();
        this.a = new HashMap(map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SavedStateHandle a(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        if (bundle == null && bundle2 == null) {
            return new SavedStateHandle();
        }
        HashMap hashMap = new HashMap();
        if (bundle2 != null) {
            for (String str : bundle2.keySet()) {
                hashMap.put(str, bundle2.get(str));
            }
        }
        if (bundle == null) {
            return new SavedStateHandle(hashMap);
        }
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
        if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
            throw new IllegalStateException("Invalid bundle passed as restored state");
        }
        for (int i2 = 0; i2 < parcelableArrayList.size(); i2++) {
            hashMap.put((String) parcelableArrayList.get(i2), parcelableArrayList2.get(i2));
        }
        return new SavedStateHandle(hashMap);
    }

    @NonNull
    private <T> MutableLiveData<T> b(@NonNull String str, boolean z, @Nullable T t) {
        b<?> bVar = this.f819c.get(str);
        if (bVar != null) {
            return bVar;
        }
        b<?> bVar2 = this.a.containsKey(str) ? new b<>(this, str, this.a.get(str)) : z ? new b<>(this, str, t) : new b<>(this, str);
        this.f819c.put(str, bVar2);
        return bVar2;
    }

    private static void d(Object obj) {
        if (obj == null) {
            return;
        }
        for (Class cls : e) {
            if (cls.isInstance(obj)) {
                return;
            }
        }
        throw new IllegalArgumentException("Can't put value with type " + obj.getClass() + " into saved state");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public SavedStateRegistry.SavedStateProvider c() {
        return this.f820d;
    }

    @MainThread
    public void clearSavedStateProvider(@NonNull String str) {
        this.b.remove(str);
    }

    @MainThread
    public boolean contains(@NonNull String str) {
        return this.a.containsKey(str);
    }

    @Nullable
    @MainThread
    public <T> T get(@NonNull String str) {
        return (T) this.a.get(str);
    }

    @NonNull
    @MainThread
    public <T> MutableLiveData<T> getLiveData(@NonNull String str) {
        return b(str, false, null);
    }

    @NonNull
    @MainThread
    public <T> MutableLiveData<T> getLiveData(@NonNull String str, @SuppressLint({"UnknownNullness"}) T t) {
        return b(str, true, t);
    }

    @NonNull
    @MainThread
    public Set<String> keys() {
        HashSet hashSet = new HashSet(this.a.keySet());
        hashSet.addAll(this.b.keySet());
        hashSet.addAll(this.f819c.keySet());
        return hashSet;
    }

    @Nullable
    @MainThread
    public <T> T remove(@NonNull String str) {
        T t = (T) this.a.remove(str);
        b<?> remove = this.f819c.remove(str);
        if (remove != null) {
            remove.h();
        }
        return t;
    }

    @MainThread
    public <T> void set(@NonNull String str, @Nullable T t) {
        d(t);
        b<?> bVar = this.f819c.get(str);
        if (bVar != null) {
            bVar.setValue(t);
        } else {
            this.a.put(str, t);
        }
    }

    @MainThread
    public void setSavedStateProvider(@NonNull String str, @NonNull SavedStateRegistry.SavedStateProvider savedStateProvider) {
        this.b.put(str, savedStateProvider);
    }
}
