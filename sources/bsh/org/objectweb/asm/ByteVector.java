package bsh.org.objectweb.asm;

/* loaded from: classes.dex */
final class ByteVector {
    byte[] data;
    int length;

    public ByteVector() {
        this.data = new byte[64];
    }

    public ByteVector(int i2) {
        this.data = new byte[i2];
    }

    private void enlarge(int i2) {
        byte[] bArr = new byte[Math.max(this.data.length * 2, this.length + i2)];
        System.arraycopy(this.data, 0, bArr, 0, this.length);
        this.data = bArr;
    }

    public ByteVector put1(int i2) {
        int i3 = this.length;
        int i4 = i3 + 1;
        if (i4 > this.data.length) {
            enlarge(1);
        }
        this.data[i3] = (byte) i2;
        this.length = i4;
        return this;
    }

    public ByteVector put11(int i2, int i3) {
        int i4 = this.length;
        if (i4 + 2 > this.data.length) {
            enlarge(2);
        }
        byte[] bArr = this.data;
        int i5 = i4 + 1;
        bArr[i4] = (byte) i2;
        bArr[i5] = (byte) i3;
        this.length = i5 + 1;
        return this;
    }

    public ByteVector put12(int i2, int i3) {
        int i4 = this.length;
        if (i4 + 3 > this.data.length) {
            enlarge(3);
        }
        byte[] bArr = this.data;
        int i5 = i4 + 1;
        bArr[i4] = (byte) i2;
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i3 >>> 8);
        bArr[i6] = (byte) i3;
        this.length = i6 + 1;
        return this;
    }

    public ByteVector put2(int i2) {
        int i3 = this.length;
        if (i3 + 2 > this.data.length) {
            enlarge(2);
        }
        byte[] bArr = this.data;
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i2 >>> 8);
        bArr[i4] = (byte) i2;
        this.length = i4 + 1;
        return this;
    }

    public ByteVector put4(int i2) {
        int i3 = this.length;
        if (i3 + 4 > this.data.length) {
            enlarge(4);
        }
        byte[] bArr = this.data;
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i2 >>> 24);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i2 >>> 8);
        bArr[i6] = (byte) i2;
        this.length = i6 + 1;
        return this;
    }

    public ByteVector put8(long j) {
        int i2 = this.length;
        if (i2 + 8 > this.data.length) {
            enlarge(8);
        }
        byte[] bArr = this.data;
        int i3 = (int) (j >>> 32);
        int i4 = i2 + 1;
        bArr[i2] = (byte) (i3 >>> 24);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i3 >>> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i3 >>> 8);
        int i7 = i6 + 1;
        bArr[i6] = (byte) i3;
        int i8 = (int) j;
        int i9 = i7 + 1;
        bArr[i7] = (byte) (i8 >>> 24);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i8 >>> 16);
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i8 >>> 8);
        bArr[i11] = (byte) i8;
        this.length = i11 + 1;
        return this;
    }

    public ByteVector putByteArray(byte[] bArr, int i2, int i3) {
        if (this.length + i3 > this.data.length) {
            enlarge(i3);
        }
        if (bArr != null) {
            System.arraycopy(bArr, i2, this.data, this.length, i3);
        }
        this.length += i3;
        return this;
    }

    public ByteVector putUTF(String str) {
        int i2;
        int length = str.length();
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            i3 = (charAt < 1 || charAt > 127) ? charAt > 2047 ? i3 + 3 : i3 + 2 : i3 + 1;
        }
        if (i3 > 65535) {
            throw new IllegalArgumentException();
        }
        int i5 = this.length;
        if (i5 + 2 + i3 > this.data.length) {
            enlarge(i3 + 2);
        }
        byte[] bArr = this.data;
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i3 >>> 8);
        int i7 = i6 + 1;
        bArr[i6] = (byte) i3;
        for (int i8 = 0; i8 < length; i8++) {
            char charAt2 = str.charAt(i8);
            if (charAt2 < 1 || charAt2 > 127) {
                int i9 = i7 + 1;
                if (charAt2 > 2047) {
                    bArr[i7] = (byte) (((charAt2 >> '\f') & 15) | 224);
                    int i10 = i9 + 1;
                    bArr[i9] = (byte) (((charAt2 >> 6) & 63) | 128);
                    i2 = i10 + 1;
                    bArr[i10] = (byte) ((charAt2 & '?') | 128);
                } else {
                    bArr[i7] = (byte) (((charAt2 >> 6) & 31) | Constants.CHECKCAST);
                    i7 = i9 + 1;
                    bArr[i9] = (byte) ((charAt2 & '?') | 128);
                }
            } else {
                i2 = i7 + 1;
                bArr[i7] = (byte) charAt2;
            }
            i7 = i2;
        }
        this.length = i7;
        return this;
    }
}
