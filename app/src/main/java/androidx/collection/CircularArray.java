package androidx.collection;

/* loaded from: classes.dex */
public final class CircularArray<E> {
    private E[] a;
    private int b;

    /* renamed from: c, reason: collision with root package name */
    private int f262c;

    /* renamed from: d, reason: collision with root package name */
    private int f263d;

    public CircularArray() {
        this(8);
    }

    public CircularArray(int i2) {
        if (i2 < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (i2 > 1073741824) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        i2 = Integer.bitCount(i2) != 1 ? Integer.highestOneBit(i2 - 1) << 1 : i2;
        this.f263d = i2 - 1;
        this.a = (E[]) new Object[i2];
    }

    private void a() {
        E[] eArr = this.a;
        int length = eArr.length;
        int i2 = this.b;
        int i3 = length - i2;
        int i4 = length << 1;
        if (i4 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        E[] eArr2 = (E[]) new Object[i4];
        System.arraycopy(eArr, i2, eArr2, 0, i3);
        System.arraycopy(this.a, 0, eArr2, i3, this.b);
        this.a = eArr2;
        this.b = 0;
        this.f262c = length;
        this.f263d = i4 - 1;
    }

    public void addFirst(E e) {
        int i2 = (this.b - 1) & this.f263d;
        this.b = i2;
        this.a[i2] = e;
        if (i2 == this.f262c) {
            a();
        }
    }

    public void addLast(E e) {
        E[] eArr = this.a;
        int i2 = this.f262c;
        eArr[i2] = e;
        int i3 = this.f263d & (i2 + 1);
        this.f262c = i3;
        if (i3 == this.b) {
            a();
        }
    }

    public void clear() {
        removeFromStart(size());
    }

    public E get(int i2) {
        if (i2 < 0 || i2 >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.a[this.f263d & (this.b + i2)];
    }

    public E getFirst() {
        int i2 = this.b;
        if (i2 != this.f262c) {
            return this.a[i2];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E getLast() {
        int i2 = this.b;
        int i3 = this.f262c;
        if (i2 != i3) {
            return this.a[(i3 - 1) & this.f263d];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public boolean isEmpty() {
        return this.b == this.f262c;
    }

    public E popFirst() {
        int i2 = this.b;
        if (i2 == this.f262c) {
            throw new ArrayIndexOutOfBoundsException();
        }
        E[] eArr = this.a;
        E e = eArr[i2];
        eArr[i2] = null;
        this.b = (i2 + 1) & this.f263d;
        return e;
    }

    public E popLast() {
        int i2 = this.b;
        int i3 = this.f262c;
        if (i2 == i3) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i4 = this.f263d & (i3 - 1);
        E[] eArr = this.a;
        E e = eArr[i4];
        eArr[i4] = null;
        this.f262c = i4;
        return e;
    }

    public void removeFromEnd(int i2) {
        int i3;
        if (i2 <= 0) {
            return;
        }
        if (i2 > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i4 = this.f262c;
        int i5 = i2 < i4 ? i4 - i2 : 0;
        int i6 = i5;
        while (true) {
            i3 = this.f262c;
            if (i6 >= i3) {
                break;
            }
            this.a[i6] = null;
            i6++;
        }
        int i7 = i3 - i5;
        int i8 = i2 - i7;
        this.f262c = i3 - i7;
        if (i8 > 0) {
            int length = this.a.length;
            this.f262c = length;
            int i9 = length - i8;
            for (int i10 = i9; i10 < this.f262c; i10++) {
                this.a[i10] = null;
            }
            this.f262c = i9;
        }
    }

    public void removeFromStart(int i2) {
        if (i2 <= 0) {
            return;
        }
        if (i2 > size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int length = this.a.length;
        int i3 = this.b;
        if (i2 < length - i3) {
            length = i3 + i2;
        }
        while (i3 < length) {
            this.a[i3] = null;
            i3++;
        }
        int i4 = this.b;
        int i5 = length - i4;
        int i6 = i2 - i5;
        this.b = this.f263d & (i4 + i5);
        if (i6 > 0) {
            for (int i7 = 0; i7 < i6; i7++) {
                this.a[i7] = null;
            }
            this.b = i6;
        }
    }

    public int size() {
        return (this.f262c - this.b) & this.f263d;
    }
}
