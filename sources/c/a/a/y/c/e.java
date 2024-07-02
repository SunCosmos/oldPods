package c.a.a.y.c;

import bsh.org.objectweb.asm.Constants;
import c.a.a.h;

/* loaded from: classes.dex */
public final class e {
    private static final e[] h = a();
    private final int a;
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    private final int f1323c;

    /* renamed from: d, reason: collision with root package name */
    private final int f1324d;
    private final int e;
    private final c f;
    private final int g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b {
        private final int a;
        private final int b;

        private b(int i2, int i3) {
            this.a = i2;
            this.b = i3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int a() {
            return this.a;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int b() {
            return this.b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class c {
        private final int a;
        private final b[] b;

        private c(int i2, b bVar) {
            this.a = i2;
            this.b = new b[]{bVar};
        }

        private c(int i2, b bVar, b bVar2) {
            this.a = i2;
            this.b = new b[]{bVar, bVar2};
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public b[] a() {
            return this.b;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int b() {
            return this.a;
        }
    }

    private e(int i2, int i3, int i4, int i5, int i6, c cVar) {
        this.a = i2;
        this.b = i3;
        this.f1323c = i4;
        this.f1324d = i5;
        this.e = i6;
        this.f = cVar;
        int b2 = cVar.b();
        int i7 = 0;
        for (b bVar : cVar.a()) {
            i7 += bVar.a() * (bVar.b() + b2);
        }
        this.g = i7;
    }

    private static e[] a() {
        int i2 = 1;
        int i3 = 5;
        int i4 = 8;
        e eVar = new e(3, 14, 14, 12, 12, new c(10, new b(i2, i4)));
        int i5 = 2;
        int i6 = 12;
        int i7 = 18;
        e eVar2 = new e(7, 22, 22, 20, 20, new c(20, new b(i2, 30)));
        int i8 = 6;
        int i9 = 36;
        int i10 = 62;
        int i11 = 56;
        int i12 = 68;
        b bVar = new b(i2, 5);
        b bVar2 = new b(i2, 10);
        b bVar3 = new b(i2, 16);
        return new e[]{new e(1, 10, 10, 8, 8, new c(i3, new b(i2, 3))), new e(2, 12, 12, 10, 10, new c(7, new b(i2, i3))), eVar, new e(4, 16, 16, 14, 14, new c(i6, new b(i2, i6))), new e(5, 18, 18, 16, 16, new c(14, new b(i2, i7))), new e(6, 20, 20, 18, 18, new c(i7, new b(i2, 22))), eVar2, new e(8, 24, 24, 22, 22, new c(24, new b(i2, i9))), new e(9, 26, 26, 24, 24, new c(28, new b(i2, 44))), new e(10, 32, 32, 14, 14, new c(i9, new b(i2, i10))), new e(11, 36, 36, 16, 16, new c(42, new b(i2, 86))), new e(12, 40, 40, 18, 18, new c(48, new b(i2, 114))), new e(13, 44, 44, 20, 20, new c(i11, new b(i2, Constants.D2F))), new e(14, 48, 48, 22, 22, new c(i12, new b(i2, Constants.FRETURN))), new e(15, 52, 52, 24, 24, new c(42, new b(i5, 102))), new e(16, 64, 64, 14, 14, new c(i11, new b(i5, Constants.F2L))), new e(17, 72, 72, 16, 16, new c(i9, new b(4, 92))), new e(18, 80, 80, 18, 18, new c(48, new b(4, 114))), new e(19, 88, 88, 20, 20, new c(i11, new b(4, Constants.D2F))), new e(20, 96, 96, 22, 22, new c(i12, new b(4, Constants.FRETURN))), new e(21, 104, 104, 24, 24, new c(i11, new b(i8, Constants.L2I))), new e(22, 120, 120, 18, 18, new c(i12, new b(i8, Constants.DRETURN))), new e(23, 132, 132, 20, 20, new c(i10, new b(i4, Constants.IF_ICMPGT))), new e(24, Constants.D2F, Constants.D2F, 22, 22, new c(i10, new b(i4, Constants.IFGE), new b(i5, Constants.IFLT))), new e(25, 8, 18, 6, 16, new c(7, bVar)), new e(26, 8, 32, 6, 14, new c(11, bVar2)), new e(27, 12, 26, 10, 24, new c(14, bVar3)), new e(28, 12, 36, 10, 16, new c(i7, new b(i2, 22))), new e(29, 16, 36, 14, 16, new c(24, new b(i2, 32))), new e(30, 16, 48, 14, 22, new c(28, new b(i2, 49)))};
    }

    public static e h(int i2, int i3) {
        if ((i2 & 1) != 0 || (i3 & 1) != 0) {
            throw h.a();
        }
        for (e eVar : h) {
            if (eVar.b == i2 && eVar.f1323c == i3) {
                return eVar;
            }
        }
        throw h.a();
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.f1324d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c d() {
        return this.f;
    }

    public int e() {
        return this.f1323c;
    }

    public int f() {
        return this.b;
    }

    public int g() {
        return this.g;
    }

    public int i() {
        return this.a;
    }

    public String toString() {
        return String.valueOf(this.a);
    }
}
