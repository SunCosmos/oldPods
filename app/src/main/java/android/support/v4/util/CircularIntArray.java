package android.support.v4.util;

/* loaded from: lib/Mus.dex */
public final class CircularIntArray {
    private int mCapacityBitmask;
    private int[] mElements;
    private int mHead;
    private int mTail;

    private void doubleCapacity() {
        int length = this.mElements.length;
        int i2 = length - this.mHead;
        int i3 = length << 1;
        if (i3 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        int[] iArr = new int[i3];
        System.arraycopy(this.mElements, this.mHead, iArr, 0, i2);
        System.arraycopy(this.mElements, 0, iArr, i2, this.mHead);
        this.mElements = iArr;
        this.mHead = 0;
        this.mTail = length;
        this.mCapacityBitmask = i3 - 1;
    }

    public CircularIntArray() {
        this(8);
    }

    public CircularIntArray(int i2) {
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
        this.mElements = new int[i3];
    }

    public void addFirst(int i2) {
        this.mHead = (this.mHead - 1) & this.mCapacityBitmask;
        this.mElements[this.mHead] = i2;
        if (this.mHead == this.mTail) {
            doubleCapacity();
        }
    }

    public void addLast(int i2) {
        this.mElements[this.mTail] = i2;
        this.mTail = (this.mTail + 1) & this.mCapacityBitmask;
        if (this.mTail == this.mHead) {
            doubleCapacity();
        }
    }

    public int popFirst() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i2 = this.mElements[this.mHead];
        this.mHead = (this.mHead + 1) & this.mCapacityBitmask;
        return i2;
    }

    public int popLast() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i2 = (this.mTail - 1) & this.mCapacityBitmask;
        int i3 = this.mElements[i2];
        this.mTail = i2;
        return i3;
    }

    public void clear() {
        this.mTail = this.mHead;
    }

    public void removeFromStart(int i2) {
        if (i2 > 0) {
            if (i2 > size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            this.mHead = (this.mHead + i2) & this.mCapacityBitmask;
        }
    }

    public void removeFromEnd(int i2) {
        if (i2 > 0) {
            if (i2 > size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            this.mTail = (this.mTail - i2) & this.mCapacityBitmask;
        }
    }

    public int getFirst() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[this.mHead];
    }

    public int getLast() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[(this.mTail - 1) & this.mCapacityBitmask];
    }

    public int get(int i2) {
        if (i2 < 0 || i2 >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[(this.mHead + i2) & this.mCapacityBitmask];
    }

    public int size() {
        return (this.mTail - this.mHead) & this.mCapacityBitmask;
    }

    public boolean isEmpty() {
        return this.mHead == this.mTail;
    }
}
