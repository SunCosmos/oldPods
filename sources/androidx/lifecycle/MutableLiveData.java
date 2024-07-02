package androidx.lifecycle;

/* loaded from: classes.dex */
public class MutableLiveData<T> extends LiveData<T> {
    public MutableLiveData() {
    }

    public MutableLiveData(T t) {
        super(t);
    }

    @Override // androidx.lifecycle.LiveData
    public void postValue(T t) {
        super.postValue(t);
    }

    @Override // androidx.lifecycle.LiveData
    public void setValue(T t) {
        super.setValue(t);
    }
}
