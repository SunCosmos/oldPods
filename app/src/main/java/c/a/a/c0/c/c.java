package c.a.a.c0.c;

/* loaded from: classes.dex */
abstract class c {
    private static final c[] a;

    /* loaded from: classes.dex */
    private static final class b extends c {
        private b() {
            super();
        }

        @Override // c.a.a.c0.c.c
        boolean b(int i2, int i3) {
            return ((i2 + i3) & 1) == 0;
        }
    }

    /* renamed from: c.a.a.c0.c.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static final class C0043c extends c {
        private C0043c() {
            super();
        }

        @Override // c.a.a.c0.c.c
        boolean b(int i2, int i3) {
            return (i2 & 1) == 0;
        }
    }

    /* loaded from: classes.dex */
    private static final class d extends c {
        private d() {
            super();
        }

        @Override // c.a.a.c0.c.c
        boolean b(int i2, int i3) {
            return i3 % 3 == 0;
        }
    }

    /* loaded from: classes.dex */
    private static final class e extends c {
        private e() {
            super();
        }

        @Override // c.a.a.c0.c.c
        boolean b(int i2, int i3) {
            return (i2 + i3) % 3 == 0;
        }
    }

    /* loaded from: classes.dex */
    private static final class f extends c {
        private f() {
            super();
        }

        @Override // c.a.a.c0.c.c
        boolean b(int i2, int i3) {
            return (((i2 / 2) + (i3 / 3)) & 1) == 0;
        }
    }

    /* loaded from: classes.dex */
    private static final class g extends c {
        private g() {
            super();
        }

        @Override // c.a.a.c0.c.c
        boolean b(int i2, int i3) {
            int i4 = i2 * i3;
            return (i4 & 1) + (i4 % 3) == 0;
        }
    }

    /* loaded from: classes.dex */
    private static final class h extends c {
        private h() {
            super();
        }

        @Override // c.a.a.c0.c.c
        boolean b(int i2, int i3) {
            int i4 = i2 * i3;
            return (((i4 & 1) + (i4 % 3)) & 1) == 0;
        }
    }

    /* loaded from: classes.dex */
    private static final class i extends c {
        private i() {
            super();
        }

        @Override // c.a.a.c0.c.c
        boolean b(int i2, int i3) {
            return ((((i2 + i3) & 1) + ((i2 * i3) % 3)) & 1) == 0;
        }
    }

    static {
        a = new c[]{new b(), new C0043c(), new d(), new e(), new f(), new g(), new h(), new i()};
    }

    private c() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static c a(int i2) {
        if (i2 < 0 || i2 > 7) {
            throw new IllegalArgumentException();
        }
        return a[i2];
    }

    abstract boolean b(int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(c.a.a.x.b bVar, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                if (b(i3, i4)) {
                    bVar.c(i4, i3);
                }
            }
        }
    }
}
