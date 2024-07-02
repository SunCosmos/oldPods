package c.b.a.a;

/* loaded from: classes.dex */
public class a {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c.b.a.a.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0047a {
        private int[] a;
        private int b;

        /* renamed from: c, reason: collision with root package name */
        private byte[] f1339c;

        C0047a() {
        }

        public void a(byte[] bArr) {
            this.f1339c = bArr;
            int length = bArr.length;
            this.a = new int[length];
            int i2 = 0;
            for (int i3 = 1; i3 < length; i3++) {
                while (i2 > 0) {
                    byte[] bArr2 = this.f1339c;
                    if (bArr2[i2] == bArr2[i3]) {
                        break;
                    } else {
                        i2 = this.a[i2 - 1];
                    }
                }
                byte[] bArr3 = this.f1339c;
                if (bArr3[i2] == bArr3[i3]) {
                    i2++;
                }
                this.a[i3] = i2;
            }
        }

        public int b(byte[] bArr, int i2) {
            if (bArr.length != 0 && i2 <= bArr.length) {
                int i3 = 0;
                while (i2 < bArr.length) {
                    while (i3 > 0 && this.f1339c[i3] != bArr[i2]) {
                        i3 = this.a[i3 - 1];
                    }
                    byte[] bArr2 = this.f1339c;
                    if (bArr2[i3] == bArr[i2]) {
                        i3++;
                    }
                    if (i3 == bArr2.length) {
                        int length = (i2 - bArr2.length) + 1;
                        this.b = length;
                        return length;
                    }
                    i2++;
                }
            }
            return -1;
        }
    }

    public static byte[] a(byte[] bArr, int i2, int i3) {
        int i4 = i3 - i2;
        if (i4 < 1) {
            return null;
        }
        byte[] bArr2 = new byte[i4];
        int i5 = 0;
        while (i2 < i3) {
            bArr2[i5] = bArr[i2];
            i5++;
            i2++;
        }
        return bArr2;
    }

    public static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3, int i2) {
        int length;
        int d2;
        int i3;
        int d3 = d(bArr, bArr2, i2);
        if (d3 == -1 || (d2 = d(bArr, bArr3, (length = d3 + bArr2.length))) == -1 || (i3 = d2 - length) < 1) {
            return null;
        }
        byte[] bArr4 = new byte[i3];
        int i4 = 0;
        while (length < d2) {
            bArr4[i4] = bArr[length];
            i4++;
            length++;
        }
        return bArr4;
    }

    public static byte[] c(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static int d(byte[] bArr, byte[] bArr2, int i2) {
        C0047a c0047a = new C0047a();
        c0047a.a(bArr2);
        return c0047a.b(bArr, i2);
    }
}
