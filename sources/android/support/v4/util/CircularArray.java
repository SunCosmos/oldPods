package android.support.v4.util;

/* loaded from: lib/Mus.dex */
public final class CircularArray<E> {
    private int mCapacityBitmask;
    private E[] mElements;
    private int mHead;
    private int mTail;

    private void doubleCapacity() {
        int length = this.mElements.length;
        int i2 = length - this.mHead;
        int i3 = length << 1;
        if (i3 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        Object[] objArr = new Object[i3];
        System.arraycopy(this.mElements, this.mHead, objArr, 0, i2);
        System.arraycopy(this.mElements, 0, objArr, i2, this.mHead);
        this.mElements = (E[]) objArr;
        this.mHead = 0;
        this.mTail = length;
        this.mCapacityBitmask = i3 - 1;
    }

    public CircularArray() {
        this(8);
    }

    public CircularArray(int i2) {
        int i3;
        if (i2 < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (i2 > 1073741824) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        if (Integer.bitCount(i2) != 1) {
            i3 = Integer.highestOneBit(i2 - 1) << 1;
        } else {
            i3 = i2;
        }
        this.mCapacityBitmask = i3 - 1;
        this.mElements = (E[]) new Object[i3];
    }

    public void addFirst(E e) {
        this.mHead = (this.mHead - 1) & this.mCapacityBitmask;
        this.mElements[this.mHead] = e;
        if (this.mHead == this.mTail) {
            doubleCapacity();
        }
    }

    public void addLast(E e) {
        this.mElements[this.mTail] = e;
        this.mTail = (this.mTail + 1) & this.mCapacityBitmask;
        if (this.mTail == this.mHead) {
            doubleCapacity();
        }
    }

    public E popFirst() {
        if (this.mHead != this.mTail) {
            E e = this.mElements[this.mHead];
            this.mElements[this.mHead] = null;
            this.mHead = (this.mHead + 1) & this.mCapacityBitmask;
            return e;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E popLast() {
        if (this.mHead != this.mTail) {
            int i2 = (this.mTail - 1) & this.mCapacityBitmask;
            E e = this.mElements[i2];
            this.mElements[i2] = null;
            this.mTail = i2;
            return e;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void clear() {
        removeFromStart(size());
    }

    public void removeFromStart(int i2) {
        if (i2 > 0) {
            if (i2 > size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int length = this.mElements.length;
            if (i2 < length - this.mHead) {
                length = this.mHead + i2;
            }
            for (int i3 = this.mHead; i3 < length; i3++) {
                this.mElements[i3] = null;
            }
            int i4 = length - this.mHead;
            int i5 = i2 - i4;
            this.mHead = (this.mHead + i4) & this.mCapacityBitmask;
            if (i5 > 0) {
                for (int i6 = 0; i6 < i5; i6++) {
                    this.mElements[i6] = null;
                }
                this.mHead = i5;
            }
        }
    }

    public void removeFromEnd(int i2) {
        if (i2 > 0) {
            if (i2 > size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = 0;
            if (i2 < this.mTail) {
                i3 = this.mTail - i2;
            }
            for (int i4 = i3; i4 < this.mTail; i4++) {
                this.mElements[i4] = null;
            }
            int i5 = this.mTail - i3;
            int i6 = i2 - i5;
            this.mTail -= i5;
            if (i6 > 0) {
                this.mTail = this.mElements.length;
                int i7 = this.mTail - i6;
                for (int i8 = i7; i8 < this.mTail; i8++) {
                    this.mElements[i8] = null;
                }
                this.mTail = i7;
            }
        }
    }

    public E getFirst() {
        if (this.mHead != this.mTail) {
            return this.mElements[this.mHead];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E getLast() {
        if (this.mHead != this.mTail) {
            return this.mElements[(this.mTail - 1) & this.mCapacityBitmask];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public E get(int i2) {
        if (i2 >= 0 && i2 < size()) {
            return this.mElements[(this.mHead + i2) & this.mCapacityBitmask];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public int size() {
        return (this.mTail - this.mHead) & this.mCapacityBitmask;
    }

    public boolean isEmpty() {
        return this.mHead == this.mTail;
    }
}
