package c.a.a.c0.d;

import c.a.a.c0.c.j;
import c.a.a.l;
import c.a.a.r;
import c.a.a.s;
import c.a.a.x.g;
import c.a.a.x.i;
import c.a.a.x.k;
import java.util.Map;

/* loaded from: classes.dex */
public class c {
    private final c.a.a.x.b a;
    private s b;

    public c(c.a.a.x.b bVar) {
        this.a = bVar;
    }

    private float b(r rVar, r rVar2) {
        float j = j((int) rVar.c(), (int) rVar.d(), (int) rVar2.c(), (int) rVar2.d());
        float j2 = j((int) rVar2.c(), (int) rVar2.d(), (int) rVar.c(), (int) rVar.d());
        return Float.isNaN(j) ? j2 / 7.0f : Float.isNaN(j2) ? j / 7.0f : (j + j2) / 14.0f;
    }

    private static int c(r rVar, r rVar2, r rVar3, float f) {
        int c2 = ((c.a.a.x.m.a.c(r.b(rVar, rVar2) / f) + c.a.a.x.m.a.c(r.b(rVar, rVar3) / f)) / 2) + 7;
        int i2 = c2 & 3;
        if (i2 == 0) {
            return c2 + 1;
        }
        if (i2 == 2) {
            return c2 - 1;
        }
        if (i2 != 3) {
            return c2;
        }
        throw l.a();
    }

    private static k d(r rVar, r rVar2, r rVar3, r rVar4, int i2) {
        float c2;
        float d2;
        float f;
        float f2 = i2 - 3.5f;
        if (rVar4 != null) {
            c2 = rVar4.c();
            d2 = rVar4.d();
            f = f2 - 3.0f;
        } else {
            c2 = (rVar2.c() - rVar.c()) + rVar3.c();
            d2 = (rVar2.d() - rVar.d()) + rVar3.d();
            f = f2;
        }
        return k.b(3.5f, 3.5f, f2, 3.5f, f, f, 3.5f, f2, rVar.c(), rVar.d(), rVar2.c(), rVar2.d(), c2, d2, rVar3.c(), rVar3.d());
    }

    private static c.a.a.x.b h(c.a.a.x.b bVar, k kVar, int i2) {
        return i.b().d(bVar, i2, i2, kVar);
    }

    private float i(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        c cVar;
        boolean z;
        boolean z2;
        int i11 = 1;
        boolean z3 = Math.abs(i5 - i3) > Math.abs(i4 - i2);
        if (z3) {
            i7 = i2;
            i6 = i3;
            i9 = i4;
            i8 = i5;
        } else {
            i6 = i2;
            i7 = i3;
            i8 = i4;
            i9 = i5;
        }
        int abs = Math.abs(i8 - i6);
        int abs2 = Math.abs(i9 - i7);
        int i12 = (-abs) / 2;
        int i13 = i6 < i8 ? 1 : -1;
        int i14 = i7 < i9 ? 1 : -1;
        int i15 = i8 + i13;
        int i16 = i6;
        int i17 = i7;
        int i18 = 0;
        while (true) {
            if (i16 == i15) {
                i10 = i15;
                break;
            }
            int i19 = z3 ? i17 : i16;
            int i20 = z3 ? i16 : i17;
            if (i18 == i11) {
                cVar = this;
                z = z3;
                i10 = i15;
                z2 = true;
            } else {
                cVar = this;
                z = z3;
                i10 = i15;
                z2 = false;
            }
            if (z2 == cVar.a.d(i19, i20)) {
                if (i18 == 2) {
                    return c.a.a.x.m.a.b(i16, i17, i6, i7);
                }
                i18++;
            }
            i12 += abs2;
            if (i12 > 0) {
                if (i17 == i9) {
                    break;
                }
                i17 += i14;
                i12 -= abs;
            }
            i16 += i13;
            i15 = i10;
            z3 = z;
            i11 = 1;
        }
        if (i18 == 2) {
            return c.a.a.x.m.a.b(i10, i9, i6, i7);
        }
        return Float.NaN;
    }

    private float j(int i2, int i3, int i4, int i5) {
        float f;
        float f2;
        float i6 = i(i2, i3, i4, i5);
        int i7 = i2 - (i4 - i2);
        int i8 = 0;
        if (i7 < 0) {
            f = i2 / (i2 - i7);
            i7 = 0;
        } else if (i7 >= this.a.j()) {
            f = ((this.a.j() - 1) - i2) / (i7 - i2);
            i7 = this.a.j() - 1;
        } else {
            f = 1.0f;
        }
        float f3 = i3;
        int i9 = (int) (f3 - ((i5 - i3) * f));
        if (i9 < 0) {
            f2 = f3 / (i3 - i9);
        } else if (i9 >= this.a.g()) {
            f2 = ((this.a.g() - 1) - i3) / (i9 - i3);
            i8 = this.a.g() - 1;
        } else {
            i8 = i9;
            f2 = 1.0f;
        }
        return (i6 + i(i2, i3, (int) (i2 + ((i7 - i2) * f2)), i8)) - 1.0f;
    }

    protected final float a(r rVar, r rVar2, r rVar3) {
        return (b(rVar, rVar2) + b(rVar, rVar3)) / 2.0f;
    }

    public final g e(Map<c.a.a.e, ?> map) {
        s sVar = map == null ? null : (s) map.get(c.a.a.e.NEED_RESULT_POINT_CALLBACK);
        this.b = sVar;
        return g(new e(this.a, sVar).e(map));
    }

    protected final a f(float f, int i2, int i3, float f2) {
        int i4 = (int) (f2 * f);
        int max = Math.max(0, i2 - i4);
        int min = Math.min(this.a.j() - 1, i2 + i4) - max;
        float f3 = 3.0f * f;
        if (min < f3) {
            throw l.a();
        }
        int max2 = Math.max(0, i3 - i4);
        int min2 = Math.min(this.a.g() - 1, i3 + i4) - max2;
        if (min2 >= f3) {
            return new b(this.a, max, max2, min, min2, f, this.b).c();
        }
        throw l.a();
    }

    protected final g g(f fVar) {
        d b = fVar.b();
        d c2 = fVar.c();
        d a = fVar.a();
        float a2 = a(b, c2, a);
        if (a2 < 1.0f) {
            throw l.a();
        }
        int c3 = c(b, c2, a, a2);
        j g = j.g(c3);
        int e = g.e() - 7;
        a aVar = null;
        if (g.d().length > 0) {
            float c4 = (c2.c() - b.c()) + a.c();
            float d2 = (c2.d() - b.d()) + a.d();
            float f = 1.0f - (3.0f / e);
            int c5 = (int) (b.c() + ((c4 - b.c()) * f));
            int d3 = (int) (b.d() + (f * (d2 - b.d())));
            for (int i2 = 4; i2 <= 16; i2 <<= 1) {
                try {
                    aVar = f(a2, c5, d3, i2);
                    break;
                } catch (l unused) {
                }
            }
        }
        return new g(h(this.a, d(b, c2, a, aVar, c3), c3), aVar == null ? new r[]{a, b, c2} : new r[]{a, b, c2, aVar});
    }
}
