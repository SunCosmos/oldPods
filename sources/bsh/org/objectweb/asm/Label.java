package bsh.org.objectweb.asm;

/* loaded from: classes.dex */
public class Label {
    int beginStackSize;
    int maxStackSize;
    Label next;
    CodeWriter owner;
    int position;
    boolean pushed;
    private int referenceCount;
    boolean resolved;
    private int[] srcAndRefPositions;
    Edge successors;

    private void addReference(int i2, int i3) {
        if (this.srcAndRefPositions == null) {
            this.srcAndRefPositions = new int[6];
        }
        int i4 = this.referenceCount;
        int[] iArr = this.srcAndRefPositions;
        if (i4 >= iArr.length) {
            int[] iArr2 = new int[iArr.length + 6];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.srcAndRefPositions = iArr2;
        }
        int[] iArr3 = this.srcAndRefPositions;
        int i5 = this.referenceCount;
        int i6 = i5 + 1;
        this.referenceCount = i6;
        iArr3[i5] = i2;
        this.referenceCount = i6 + 1;
        iArr3[i6] = i3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0007, code lost:
    
        if (r4 != false) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void put(bsh.org.objectweb.asm.CodeWriter r1, bsh.org.objectweb.asm.ByteVector r2, int r3, boolean r4) {
        /*
            r0 = this;
            boolean r1 = r0.resolved
            if (r1 == 0) goto La
            int r1 = r0.position
            int r1 = r1 - r3
            if (r4 == 0) goto L1d
            goto L14
        La:
            r1 = -1
            if (r4 == 0) goto L18
            int r3 = (-1) - r3
            int r4 = r2.length
            r0.addReference(r3, r4)
        L14:
            r2.put4(r1)
            goto L20
        L18:
            int r4 = r2.length
            r0.addReference(r3, r4)
        L1d:
            r2.put2(r1)
        L20:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.org.objectweb.asm.Label.put(bsh.org.objectweb.asm.CodeWriter, bsh.org.objectweb.asm.ByteVector, int, boolean):void");
    }

    public boolean resolve(CodeWriter codeWriter, int i2, byte[] bArr) {
        this.resolved = true;
        this.position = i2;
        int i3 = 0;
        boolean z = false;
        while (i3 < this.referenceCount) {
            int[] iArr = this.srcAndRefPositions;
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            int i6 = i4 + 1;
            int i7 = iArr[i4];
            if (i5 >= 0) {
                int i8 = i2 - i5;
                if (i8 < -32768 || i8 > 32767) {
                    int i9 = i7 - 1;
                    int i10 = bArr[i9] & 255;
                    if (i10 <= 168) {
                        bArr[i9] = (byte) (i10 + 49);
                    } else {
                        bArr[i9] = (byte) (i10 + 20);
                    }
                    z = true;
                }
                bArr[i7] = (byte) (i8 >>> 8);
                bArr[i7 + 1] = (byte) i8;
            } else {
                int i11 = i5 + i2 + 1;
                int i12 = i7 + 1;
                bArr[i7] = (byte) (i11 >>> 24);
                int i13 = i12 + 1;
                bArr[i12] = (byte) (i11 >>> 16);
                bArr[i13] = (byte) (i11 >>> 8);
                bArr[i13 + 1] = (byte) i11;
            }
            i3 = i6;
        }
        return z;
    }
}
