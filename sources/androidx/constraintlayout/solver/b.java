package androidx.constraintlayout.solver;

/* loaded from: classes.dex */
class b<T> implements a<T> {
    private final Object[] a;
    private int b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.a = new Object[i2];
    }

    @Override // androidx.constraintlayout.solver.a
    public void a(T[] tArr, int i2) {
        if (i2 > tArr.length) {
            i2 = tArr.length;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            T t = tArr[i3];
            int i4 = this.b;
            Object[] objArr = this.a;
            if (i4 < objArr.length) {
                objArr[i4] = t;
                this.b = i4 + 1;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.a
    public T acquire() {
        int i2 = this.b;
        if (i2 <= 0) {
            return null;
        }
        int i3 = i2 - 1;
        Object[] objArr = this.a;
        T t = (T) objArr[i3];
        objArr[i3] = null;
        this.b = i2 - 1;
        return t;
    }

    @Override // androidx.constraintlayout.solver.a
    public boolean release(T t) {
        int i2 = this.b;
        Object[] objArr = this.a;
        if (i2 >= objArr.length) {
            return false;
        }
        objArr[i2] = t;
        this.b = i2 + 1;
        return true;
    }
}
