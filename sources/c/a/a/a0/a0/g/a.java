package c.a.a.a0.a0.g;

import java.util.List;

/* loaded from: classes.dex */
final class a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static c.a.a.x.a a(List<b> list) {
        int size = (list.size() * 2) - 1;
        if (list.get(list.size() - 1).d() == null) {
            size--;
        }
        c.a.a.x.a aVar = new c.a.a.x.a(size * 12);
        int i2 = 0;
        int b = list.get(0).d().b();
        for (int i3 = 11; i3 >= 0; i3--) {
            if (((1 << i3) & b) != 0) {
                aVar.p(i2);
            }
            i2++;
        }
        for (int i4 = 1; i4 < list.size(); i4++) {
            b bVar = list.get(i4);
            int b2 = bVar.c().b();
            for (int i5 = 11; i5 >= 0; i5--) {
                if (((1 << i5) & b2) != 0) {
                    aVar.p(i2);
                }
                i2++;
            }
            if (bVar.d() != null) {
                int b3 = bVar.d().b();
                for (int i6 = 11; i6 >= 0; i6--) {
                    if (((1 << i6) & b3) != 0) {
                        aVar.p(i2);
                    }
                    i2++;
                }
            }
        }
        return aVar;
    }
}
