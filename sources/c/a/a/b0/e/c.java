package c.a.a.b0.e;

import c.a.a.l;
import c.a.a.r;

/* loaded from: classes.dex */
final class c {
    private c.a.a.x.b a;
    private r b;

    /* renamed from: c, reason: collision with root package name */
    private r f1197c;

    /* renamed from: d, reason: collision with root package name */
    private r f1198d;
    private r e;
    private int f;
    private int g;
    private int h;

    /* renamed from: i, reason: collision with root package name */
    private int f1199i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(c cVar) {
        k(cVar.a, cVar.b, cVar.f1197c, cVar.f1198d, cVar.e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(c.a.a.x.b bVar, r rVar, r rVar2, r rVar3, r rVar4) {
        if ((rVar == null && rVar3 == null) || ((rVar2 == null && rVar4 == null) || ((rVar != null && rVar2 == null) || (rVar3 != null && rVar4 == null)))) {
            throw l.a();
        }
        k(bVar, rVar, rVar2, rVar3, rVar4);
    }

    private void b() {
        if (this.b == null) {
            this.b = new r(0.0f, this.f1198d.d());
            this.f1197c = new r(0.0f, this.e.d());
        } else if (this.f1198d == null) {
            this.f1198d = new r(this.a.j() - 1, this.b.d());
            this.e = new r(this.a.j() - 1, this.f1197c.d());
        }
        this.f = (int) Math.min(this.b.c(), this.f1197c.c());
        this.g = (int) Math.max(this.f1198d.c(), this.e.c());
        this.h = (int) Math.min(this.b.d(), this.f1198d.d());
        this.f1199i = (int) Math.max(this.f1197c.d(), this.e.d());
    }

    private void k(c.a.a.x.b bVar, r rVar, r rVar2, r rVar3, r rVar4) {
        this.a = bVar;
        this.b = rVar;
        this.f1197c = rVar2;
        this.f1198d = rVar3;
        this.e = rVar4;
        b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static c l(c cVar, c cVar2) {
        return cVar == null ? cVar2 : cVar2 == null ? cVar : new c(cVar.a, cVar.b, cVar.f1197c, cVar2.f1198d, cVar2.e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public c.a.a.b0.e.c a(int r13, int r14, boolean r15) {
        /*
            r12 = this;
            c.a.a.r r0 = r12.b
            c.a.a.r r1 = r12.f1197c
            c.a.a.r r2 = r12.f1198d
            c.a.a.r r3 = r12.e
            if (r13 <= 0) goto L29
            if (r15 == 0) goto Le
            r4 = r0
            goto Lf
        Le:
            r4 = r2
        Lf:
            float r5 = r4.d()
            int r5 = (int) r5
            int r5 = r5 - r13
            if (r5 >= 0) goto L18
            r5 = 0
        L18:
            c.a.a.r r13 = new c.a.a.r
            float r4 = r4.c()
            float r5 = (float) r5
            r13.<init>(r4, r5)
            if (r15 == 0) goto L26
            r8 = r13
            goto L2a
        L26:
            r10 = r13
            r8 = r0
            goto L2b
        L29:
            r8 = r0
        L2a:
            r10 = r2
        L2b:
            if (r14 <= 0) goto L5b
            if (r15 == 0) goto L32
            c.a.a.r r13 = r12.f1197c
            goto L34
        L32:
            c.a.a.r r13 = r12.e
        L34:
            float r0 = r13.d()
            int r0 = (int) r0
            int r0 = r0 + r14
            c.a.a.x.b r14 = r12.a
            int r14 = r14.g()
            if (r0 < r14) goto L4a
            c.a.a.x.b r14 = r12.a
            int r14 = r14.g()
            int r0 = r14 + (-1)
        L4a:
            c.a.a.r r14 = new c.a.a.r
            float r13 = r13.c()
            float r0 = (float) r0
            r14.<init>(r13, r0)
            if (r15 == 0) goto L58
            r9 = r14
            goto L5c
        L58:
            r11 = r14
            r9 = r1
            goto L5d
        L5b:
            r9 = r1
        L5c:
            r11 = r3
        L5d:
            r12.b()
            c.a.a.b0.e.c r13 = new c.a.a.b0.e.c
            c.a.a.x.b r7 = r12.a
            r6 = r13
            r6.<init>(r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.b0.e.c.a(int, int, boolean):c.a.a.b0.e.c");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public r c() {
        return this.f1197c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public r d() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.f1199i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int h() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public r i() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public r j() {
        return this.f1198d;
    }
}
