package c.d.a;

import android.view.animation.Interpolator;
import c.d.a.g;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class e extends h {
    private float f;
    private float g;
    private float h;

    /* renamed from: i */
    private boolean f1393i;

    public e(g.a... aVarArr) {
        super(aVarArr);
        this.f1393i = true;
    }

    @Override // c.d.a.h
    public Object b(float f) {
        return Float.valueOf(f(f));
    }

    @Override // c.d.a.h
    /* renamed from: e, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public e clone() {
        ArrayList<g> arrayList = this.f1397d;
        int size = arrayList.size();
        g.a[] aVarArr = new g.a[size];
        for (int i2 = 0; i2 < size; i2++) {
            aVarArr[i2] = (g.a) arrayList.get(i2).clone();
        }
        return new e(aVarArr);
    }

    public float f(float f) {
        Object d2;
        int i2 = this.a;
        if (i2 != 2) {
            if (f > 0.0f) {
                if (f < 1.0f) {
                    g.a aVar = (g.a) this.f1397d.get(0);
                    int i3 = 1;
                    while (true) {
                        int i4 = this.a;
                        if (i3 >= i4) {
                            d2 = this.f1397d.get(i4 - 1).d();
                            break;
                        }
                        g.a aVar2 = (g.a) this.f1397d.get(i3);
                        if (f < aVar2.b()) {
                            Interpolator c2 = aVar2.c();
                            if (c2 != null) {
                                f = c2.getInterpolation(f);
                            }
                            float b = (f - aVar.b()) / (aVar2.b() - aVar.b());
                            float k = aVar.k();
                            float k2 = aVar2.k();
                            l lVar = this.e;
                            return lVar == null ? k + (b * (k2 - k)) : ((Number) lVar.evaluate(b, Float.valueOf(k), Float.valueOf(k2))).floatValue();
                        }
                        i3++;
                        aVar = aVar2;
                    }
                } else {
                    g.a aVar3 = (g.a) this.f1397d.get(i2 - 2);
                    g.a aVar4 = (g.a) this.f1397d.get(this.a - 1);
                    float k3 = aVar3.k();
                    float k4 = aVar4.k();
                    float b2 = aVar3.b();
                    float b3 = aVar4.b();
                    Interpolator c3 = aVar4.c();
                    if (c3 != null) {
                        f = c3.getInterpolation(f);
                    }
                    float f2 = (f - b2) / (b3 - b2);
                    l lVar2 = this.e;
                    return lVar2 == null ? k3 + (f2 * (k4 - k3)) : ((Number) lVar2.evaluate(f2, Float.valueOf(k3), Float.valueOf(k4))).floatValue();
                }
            } else {
                g.a aVar5 = (g.a) this.f1397d.get(0);
                g.a aVar6 = (g.a) this.f1397d.get(1);
                float k5 = aVar5.k();
                float k6 = aVar6.k();
                float b4 = aVar5.b();
                float b5 = aVar6.b();
                Interpolator c4 = aVar6.c();
                if (c4 != null) {
                    f = c4.getInterpolation(f);
                }
                float f3 = (f - b4) / (b5 - b4);
                l lVar3 = this.e;
                return lVar3 == null ? k5 + (f3 * (k6 - k5)) : ((Number) lVar3.evaluate(f3, Float.valueOf(k5), Float.valueOf(k6))).floatValue();
            }
        } else {
            if (this.f1393i) {
                this.f1393i = false;
                this.f = ((g.a) this.f1397d.get(0)).k();
                float k7 = ((g.a) this.f1397d.get(1)).k();
                this.g = k7;
                this.h = k7 - this.f;
            }
            Interpolator interpolator = this.f1396c;
            if (interpolator != null) {
                f = interpolator.getInterpolation(f);
            }
            l lVar4 = this.e;
            if (lVar4 == null) {
                return this.f + (f * this.h);
            }
            d2 = lVar4.evaluate(f, Float.valueOf(this.f), Float.valueOf(this.g));
        }
        return ((Number) d2).floatValue();
    }
}
