package c.d.a;

import android.view.animation.Interpolator;
import c.d.a.g;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class h {
    int a;
    g b;

    /* renamed from: c, reason: collision with root package name */
    Interpolator f1396c;

    /* renamed from: d, reason: collision with root package name */
    ArrayList<g> f1397d;
    l e;

    public h(g... gVarArr) {
        this.a = gVarArr.length;
        ArrayList<g> arrayList = new ArrayList<>();
        this.f1397d = arrayList;
        arrayList.addAll(Arrays.asList(gVarArr));
        this.f1397d.get(0);
        g gVar = this.f1397d.get(this.a - 1);
        this.b = gVar;
        this.f1396c = gVar.c();
    }

    public static h c(float... fArr) {
        int length = fArr.length;
        g.a[] aVarArr = new g.a[Math.max(length, 2)];
        if (length == 1) {
            aVarArr[0] = (g.a) g.f(0.0f);
            aVarArr[1] = (g.a) g.g(1.0f, fArr[0]);
        } else {
            aVarArr[0] = (g.a) g.g(0.0f, fArr[0]);
            for (int i2 = 1; i2 < length; i2++) {
                aVarArr[i2] = (g.a) g.g(i2 / (length - 1), fArr[i2]);
            }
        }
        return new e(aVarArr);
    }

    /* renamed from: a */
    public h clone() {
        throw null;
    }

    public Object b(float f) {
        throw null;
    }

    public void d(l lVar) {
        this.e = lVar;
    }

    public String toString() {
        String str = " ";
        for (int i2 = 0; i2 < this.a; i2++) {
            str = str + this.f1397d.get(i2).d() + "  ";
        }
        return str;
    }
}
