package c.a.a;

import java.util.Map;

/* loaded from: classes.dex */
public final class k implements t {

    /* loaded from: classes.dex */
    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[c.a.a.a.values().length];
            a = iArr;
            try {
                iArr[c.a.a.a.EAN_8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[c.a.a.a.EAN_13.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[c.a.a.a.UPC_A.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[c.a.a.a.QR_CODE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[c.a.a.a.CODE_39.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[c.a.a.a.CODE_128.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[c.a.a.a.ITF.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[c.a.a.a.PDF_417.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[c.a.a.a.CODABAR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[c.a.a.a.DATA_MATRIX.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[c.a.a.a.AZTEC.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    @Override // c.a.a.t
    public c.a.a.x.b a(String str, c.a.a.a aVar, int i2, int i3, Map<g, ?> map) {
        t kVar;
        switch (a.a[aVar.ordinal()]) {
            case 1:
                kVar = new c.a.a.a0.k();
                break;
            case 2:
                kVar = new c.a.a.a0.i();
                break;
            case 3:
                kVar = new c.a.a.a0.t();
                break;
            case 4:
                kVar = new c.a.a.c0.b();
                break;
            case 5:
                kVar = new c.a.a.a0.f();
                break;
            case 6:
                kVar = new c.a.a.a0.d();
                break;
            case 7:
                kVar = new c.a.a.a0.n();
                break;
            case 8:
                kVar = new c.a.a.b0.d();
                break;
            case 9:
                kVar = new c.a.a.a0.b();
                break;
            case 10:
                kVar = new c.a.a.y.b();
                break;
            case 11:
                kVar = new c.a.a.v.c();
                break;
            default:
                throw new IllegalArgumentException("No encoder available for format " + aVar);
        }
        return kVar.a(str, aVar, i2, i3, map);
    }
}
