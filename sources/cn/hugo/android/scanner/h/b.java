package cn.hugo.android.scanner.h;

import android.graphics.Bitmap;
import c.a.a.i;

/* loaded from: classes.dex */
public class b extends i {

    /* renamed from: c, reason: collision with root package name */
    private byte[] f1434c;

    /* JADX INFO: Access modifiers changed from: protected */
    public b(Bitmap bitmap) {
        super(bitmap.getWidth(), bitmap.getHeight());
        int width = bitmap.getWidth() * bitmap.getHeight();
        int[] iArr = new int[width];
        this.f1434c = new byte[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(iArr, 0, d(), 0, 0, d(), a());
        for (int i2 = 0; i2 < width; i2++) {
            this.f1434c[i2] = (byte) iArr[i2];
        }
    }

    @Override // c.a.a.i
    public byte[] b() {
        return this.f1434c;
    }

    @Override // c.a.a.i
    public byte[] c(int i2, byte[] bArr) {
        System.arraycopy(this.f1434c, i2 * d(), bArr, 0, d());
        return bArr;
    }
}
