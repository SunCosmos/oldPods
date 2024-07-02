package c.a.a.y.e;

import bsh.org.objectweb.asm.Constants;

/* loaded from: classes.dex */
public class k {

    /* renamed from: i, reason: collision with root package name */
    static final k[] f1333i;
    private static k[] j;
    private final boolean a;
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    private final int f1334c;

    /* renamed from: d, reason: collision with root package name */
    public final int f1335d;
    public final int e;
    private final int f;
    private final int g;
    private final int h;

    static {
        k[] kVarArr = {new k(false, 3, 5, 8, 8, 1), new k(false, 5, 7, 10, 10, 1), new k(true, 5, 7, 16, 6, 1), new k(false, 8, 10, 12, 12, 1), new k(true, 10, 11, 14, 6, 2), new k(false, 12, 12, 14, 14, 1), new k(true, 16, 14, 24, 10, 1), new k(false, 18, 14, 16, 16, 1), new k(false, 22, 18, 18, 18, 1), new k(true, 22, 18, 16, 10, 2), new k(false, 30, 20, 20, 20, 1), new k(true, 32, 24, 16, 14, 2), new k(false, 36, 24, 22, 22, 1), new k(false, 44, 28, 24, 24, 1), new k(true, 49, 28, 22, 14, 2), new k(false, 62, 36, 14, 14, 4), new k(false, 86, 42, 16, 16, 4), new k(false, 114, 48, 18, 18, 4), new k(false, Constants.D2F, 56, 20, 20, 4), new k(false, Constants.FRETURN, 68, 22, 22, 4), new k(false, 204, 84, 24, 24, 4, 102, 42), new k(false, 280, 112, 14, 14, 16, Constants.F2L, 56), new k(false, 368, Constants.D2F, 16, 16, 16, 92, 36), new k(false, 456, Constants.CHECKCAST, 18, 18, 16, 114, 48), new k(false, 576, 224, 20, 20, 16, Constants.D2F, 56), new k(false, 696, 272, 22, 22, 16, Constants.FRETURN, 68), new k(false, 816, 336, 24, 24, 16, Constants.L2I, 56), new k(false, 1050, 408, 18, 18, 36, Constants.DRETURN, 68), new k(false, 1304, 496, 20, 20, 36, Constants.IF_ICMPGT, 62), new d()};
        f1333i = kVarArr;
        j = kVarArr;
    }

    public k(boolean z, int i2, int i3, int i4, int i5, int i6) {
        this(z, i2, i3, i4, i5, i6, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(boolean z, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.a = z;
        this.b = i2;
        this.f1334c = i3;
        this.f1335d = i4;
        this.e = i5;
        this.f = i6;
        this.g = i7;
        this.h = i8;
    }

    public static k l(int i2, l lVar, c.a.a.f fVar, c.a.a.f fVar2, boolean z) {
        for (k kVar : j) {
            if (!(lVar == l.FORCE_SQUARE && kVar.a) && ((lVar != l.FORCE_RECTANGLE || kVar.a) && ((fVar == null || (kVar.j() >= fVar.b() && kVar.i() >= fVar.a())) && ((fVar2 == null || (kVar.j() <= fVar2.b() && kVar.i() <= fVar2.a())) && i2 <= kVar.b)))) {
                return kVar;
            }
        }
        if (!z) {
            return null;
        }
        throw new IllegalArgumentException("Can't find a symbol arrangement that matches the message. Data codewords: " + i2);
    }

    public final int a() {
        return this.b;
    }

    public int b(int i2) {
        return this.g;
    }

    public final int c() {
        return this.f1334c;
    }

    public final int d(int i2) {
        return this.h;
    }

    final int e() {
        int i2 = this.f;
        int i3 = 1;
        if (i2 != 1) {
            i3 = 2;
            if (i2 != 2 && i2 != 4) {
                if (i2 == 16) {
                    return 4;
                }
                if (i2 == 36) {
                    return 6;
                }
                throw new IllegalStateException("Cannot handle this number of data regions");
            }
        }
        return i3;
    }

    public int f() {
        return this.b / this.g;
    }

    public final int g() {
        return k() * this.e;
    }

    public final int h() {
        return e() * this.f1335d;
    }

    public final int i() {
        return g() + (k() * 2);
    }

    public final int j() {
        return h() + (e() * 2);
    }

    final int k() {
        int i2 = this.f;
        if (i2 == 1 || i2 == 2) {
            return 1;
        }
        if (i2 == 4) {
            return 2;
        }
        if (i2 == 16) {
            return 4;
        }
        if (i2 == 36) {
            return 6;
        }
        throw new IllegalStateException("Cannot handle this number of data regions");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a ? "Rectangular Symbol:" : "Square Symbol:");
        sb.append(" data region ");
        sb.append(this.f1335d);
        sb.append('x');
        sb.append(this.e);
        sb.append(", symbol size ");
        sb.append(j());
        sb.append('x');
        sb.append(i());
        sb.append(", symbol data size ");
        sb.append(h());
        sb.append('x');
        sb.append(g());
        sb.append(", codewords ");
        sb.append(this.b);
        sb.append('+');
        sb.append(this.f1334c);
        return sb.toString();
    }
}
