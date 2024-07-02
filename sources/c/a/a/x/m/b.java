package c.a.a.x.m;

import c.a.a.l;
import c.a.a.r;

/* loaded from: classes.dex */
public final class b {
    private final c.a.a.x.b a;
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    private final int f1313c;

    /* renamed from: d, reason: collision with root package name */
    private final int f1314d;
    private final int e;
    private final int f;
    private final int g;

    public b(c.a.a.x.b bVar) {
        this(bVar, 10, bVar.j() / 2, bVar.g() / 2);
    }

    public b(c.a.a.x.b bVar, int i2, int i3, int i4) {
        this.a = bVar;
        int g = bVar.g();
        this.b = g;
        int j = bVar.j();
        this.f1313c = j;
        int i5 = i2 / 2;
        int i6 = i3 - i5;
        this.f1314d = i6;
        int i7 = i3 + i5;
        this.e = i7;
        int i8 = i4 - i5;
        this.g = i8;
        int i9 = i4 + i5;
        this.f = i9;
        if (i8 < 0 || i6 < 0 || i9 >= g || i7 >= j) {
            throw l.a();
        }
    }

    private r[] a(r rVar, r rVar2, r rVar3, r rVar4) {
        float c2 = rVar.c();
        float d2 = rVar.d();
        float c3 = rVar2.c();
        float d3 = rVar2.d();
        float c4 = rVar3.c();
        float d4 = rVar3.d();
        float c5 = rVar4.c();
        float d5 = rVar4.d();
        return c2 < ((float) this.f1313c) / 2.0f ? new r[]{new r(c5 - 1.0f, d5 + 1.0f), new r(c3 + 1.0f, d3 + 1.0f), new r(c4 - 1.0f, d4 - 1.0f), new r(c2 + 1.0f, d2 - 1.0f)} : new r[]{new r(c5 + 1.0f, d5 + 1.0f), new r(c3 + 1.0f, d3 - 1.0f), new r(c4 - 1.0f, d4 + 1.0f), new r(c2 - 1.0f, d2 - 1.0f)};
    }

    private boolean b(int i2, int i3, int i4, boolean z) {
        if (z) {
            while (i2 <= i3) {
                if (this.a.d(i2, i4)) {
                    return true;
                }
                i2++;
            }
            return false;
        }
        while (i2 <= i3) {
            if (this.a.d(i4, i2)) {
                return true;
            }
            i2++;
        }
        return false;
    }

    private r d(float f, float f2, float f3, float f4) {
        int c2 = a.c(a.a(f, f2, f3, f4));
        float f5 = c2;
        float f6 = (f3 - f) / f5;
        float f7 = (f4 - f2) / f5;
        for (int i2 = 0; i2 < c2; i2++) {
            float f8 = i2;
            int c3 = a.c((f8 * f6) + f);
            int c4 = a.c((f8 * f7) + f2);
            if (this.a.d(c3, c4)) {
                return new r(c3, c4);
            }
        }
        return null;
    }

    public r[] c() {
        int i2 = this.f1314d;
        int i3 = this.e;
        int i4 = this.g;
        int i5 = this.f;
        boolean z = false;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        while (z2) {
            boolean z8 = true;
            boolean z9 = false;
            while (true) {
                if ((z8 || !z3) && i3 < this.f1313c) {
                    z8 = b(i4, i5, i3, false);
                    if (z8) {
                        i3++;
                        z3 = true;
                        z9 = true;
                    } else if (!z3) {
                        i3++;
                    }
                }
            }
            if (i3 < this.f1313c) {
                boolean z10 = true;
                while (true) {
                    if ((z10 || !z4) && i5 < this.b) {
                        z10 = b(i2, i3, i5, true);
                        if (z10) {
                            i5++;
                            z4 = true;
                            z9 = true;
                        } else if (!z4) {
                            i5++;
                        }
                    }
                }
                if (i5 < this.b) {
                    boolean z11 = true;
                    while (true) {
                        if ((z11 || !z5) && i2 >= 0) {
                            z11 = b(i4, i5, i2, false);
                            if (z11) {
                                i2--;
                                z5 = true;
                                z9 = true;
                            } else if (!z5) {
                                i2--;
                            }
                        }
                    }
                    if (i2 >= 0) {
                        z2 = z9;
                        boolean z12 = true;
                        while (true) {
                            if ((z12 || !z7) && i4 >= 0) {
                                z12 = b(i2, i3, i4, true);
                                if (z12) {
                                    i4--;
                                    z2 = true;
                                    z7 = true;
                                } else if (!z7) {
                                    i4--;
                                }
                            }
                        }
                        if (i4 >= 0) {
                            if (z2) {
                                z6 = true;
                            }
                        }
                    }
                }
            }
            z = true;
            break;
        }
        if (z || !z6) {
            throw l.a();
        }
        int i6 = i3 - i2;
        r rVar = null;
        r rVar2 = null;
        for (int i7 = 1; i7 < i6; i7++) {
            rVar2 = d(i2, i5 - i7, i2 + i7, i5);
            if (rVar2 != null) {
                break;
            }
        }
        if (rVar2 == null) {
            throw l.a();
        }
        r rVar3 = null;
        for (int i8 = 1; i8 < i6; i8++) {
            rVar3 = d(i2, i4 + i8, i2 + i8, i4);
            if (rVar3 != null) {
                break;
            }
        }
        if (rVar3 == null) {
            throw l.a();
        }
        r rVar4 = null;
        for (int i9 = 1; i9 < i6; i9++) {
            rVar4 = d(i3, i4 + i9, i3 - i9, i4);
            if (rVar4 != null) {
                break;
            }
        }
        if (rVar4 == null) {
            throw l.a();
        }
        for (int i10 = 1; i10 < i6; i10++) {
            rVar = d(i3, i5 - i10, i3 - i10, i5);
            if (rVar != null) {
                break;
            }
        }
        if (rVar != null) {
            return a(rVar, rVar2, rVar4, rVar3);
        }
        throw l.a();
    }
}
