package androidx.collection;

/* loaded from: classes.dex */
public final class CircularIntArray {
    private int[] a;
    private int b;

    /* renamed from: c, reason: collision with root package name */
    private int f264c;

    /* renamed from: d, reason: collision with root package name */
    private int f265d;

    public CircularIntArray() {
        this(8);
    }

    public CircularIntArray(int i2) {
        if (i2 < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (i2 > 1073741824) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        i2 = Integer.bitCount(i2) != 1 ? Integer.highestOneBit(i2 - 1) << 1 : i2;
        this.f265d = i2 - 1;
        this.a = new int[i2];
    }

    private void a() {
        int[] iArr = this.a;
        int length = iArr.length;
        int i2 = this.b;
        int i3 = length - i2;
        int i4 = length << 1;
        if (i4 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        int[] iArr2 = new int[i4];
        System.arraycopy(iArr, i2, iArr2, 0, i3);
        System.arraycopy(this.a, 0, iArr2, i3, this.b);
        this.a = iArr2;
        this.b = 0;
        this.f264c = length;
        this.f265d = i4 - 1;
    }

    public void addFirst(int i2) {
        int i3 = (this.b - 1) & this.f265d;
        this.b = i3;
        this.a[i3] = i2;
        if (i3 == this.f264c) {
            a();
        }
    }

    public void addLast(int i2) {
        int[] iArr = this.a;
        int i3 = this.f264c;
        iArr[i3] = i2;
        int i4 = this.f265d & (i3 + 1);
        this.f264c = i4;
        if (i4 == this.b) {
            a();
        }
    }

    public void clear() {
        this.f264c = this.b;
    }

    public int get(int i2) {
        if (i2 < 0 || i2 >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.a[this.f265d & (this.b + i2)];
    }

    public int getFirst() {
        int i2 = this.b;
        if (i2 != this.f264c) {
            return this.a[i2];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int getLast() {
        int i2 = this.b;
        int i3 = this.f264c;
        if (i2 != i3) {
            return this.a[(i3 - 1) & this.f265d];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public boolean isEmpty() {
        return this.b == this.f264c;
    }

    public int popFirst() {
        int i2 = this.b;
        if (i2 == this.f264c) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = this.a[i2];
        this.b = (i2 + 1) & this.f265d;
        return i3;
    }

    public int popLast() {
        int i2 = this.b;
        int i3 = this.f264c;
        if (i2 == i3) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i4 = this.f265d & (i3 - 1);
        int i5 = this.a[i4];
        this.f264c = i4;
        return i5;
    }

    public void removeFromEnd(int i2) {
        if (i2 <= 0) {
            return;
        }
        if (i2 > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.f264c = this.f265d & (this.f264c - i2);
    }

    public void removeFromStart(int i2) {
        if (i2 <= 0) {
            return;
        }
        if (i2 > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.b = this.f265d & (this.b + i2);
    }

    public int size() {
        return (this.f264c - this.b) & this.f265d;
    }
}
