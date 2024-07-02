package c.d.a;

import android.view.View;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class i extends m {
    private static final Map<String, c.d.b.c> D;
    private Object A;
    private String B;
    private c.d.b.c C;

    static {
        HashMap hashMap = new HashMap();
        D = hashMap;
        hashMap.put("alpha", j.a);
        hashMap.put("pivotX", j.b);
        hashMap.put("pivotY", j.f1398c);
        hashMap.put("translationX", j.f1399d);
        hashMap.put("translationY", j.e);
        hashMap.put("rotation", j.f);
        hashMap.put("rotationX", j.g);
        hashMap.put("rotationY", j.h);
        hashMap.put("scaleX", j.f1400i);
        hashMap.put("scaleY", j.j);
        hashMap.put("scrollX", j.k);
        hashMap.put("scrollY", j.l);
        hashMap.put("x", j.m);
        hashMap.put("y", j.n);
    }

    public i() {
    }

    private i(Object obj, String str) {
        this.A = obj;
        N(str);
    }

    public static i K(Object obj, String str, float... fArr) {
        i iVar = new i(obj, str);
        iVar.F(fArr);
        return iVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // c.d.a.m
    public void B() {
        if (this.j) {
            return;
        }
        if (this.C == null && c.d.c.a.a.q && (this.A instanceof View)) {
            Map<String, c.d.b.c> map = D;
            if (map.containsKey(this.B)) {
                M(map.get(this.B));
            }
        }
        int length = this.q.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.q[i2].p(this.A);
        }
        super.B();
    }

    @Override // c.d.a.m
    /* renamed from: E */
    public /* bridge */ /* synthetic */ m g(long j) {
        L(j);
        return this;
    }

    @Override // c.d.a.m
    public void F(float... fArr) {
        k[] kVarArr = this.q;
        if (kVarArr != null && kVarArr.length != 0) {
            super.F(fArr);
            return;
        }
        c.d.b.c cVar = this.C;
        if (cVar != null) {
            G(k.h(cVar, fArr));
        } else {
            G(k.i(this.B, fArr));
        }
    }

    @Override // c.d.a.m
    /* renamed from: J, reason: merged with bridge method [inline-methods] */
    public i clone() {
        return (i) super.clone();
    }

    public i L(long j) {
        super.g(j);
        return this;
    }

    public void M(c.d.b.c cVar) {
        k[] kVarArr = this.q;
        if (kVarArr != null) {
            k kVar = kVarArr[0];
            String f = kVar.f();
            kVar.l(cVar);
            this.r.remove(f);
            this.r.put(this.B, kVar);
        }
        if (this.C != null) {
            this.B = cVar.b();
        }
        this.C = cVar;
        this.j = false;
    }

    public void N(String str) {
        k[] kVarArr = this.q;
        if (kVarArr != null) {
            k kVar = kVarArr[0];
            String f = kVar.f();
            kVar.m(str);
            this.r.remove(f);
            this.r.put(str, kVar);
        }
        this.B = str;
        this.j = false;
    }

    @Override // c.d.a.m, c.d.a.a
    public /* bridge */ /* synthetic */ a g(long j) {
        L(j);
        return this;
    }

    @Override // c.d.a.a
    public void i(Object obj) {
        Object obj2 = this.A;
        if (obj2 != obj) {
            this.A = obj;
            if (obj2 == null || obj == null || obj2.getClass() != obj.getClass()) {
                this.j = false;
            }
        }
    }

    @Override // c.d.a.m, c.d.a.a
    public void j() {
        super.j();
    }

    @Override // c.d.a.m
    public String toString() {
        String str = "ObjectAnimator@" + Integer.toHexString(hashCode()) + ", target " + this.A;
        if (this.q != null) {
            for (int i2 = 0; i2 < this.q.length; i2++) {
                str = str + "\n    " + this.q[i2].toString();
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // c.d.a.m
    public void v(float f) {
        super.v(f);
        int length = this.q.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.q[i2].j(this.A);
        }
    }
}
