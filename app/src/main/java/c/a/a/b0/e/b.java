package c.a.a.b0.e;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
final class b {
    private final Map<Integer, Integer> a = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] a() {
        ArrayList arrayList = new ArrayList();
        int i2 = -1;
        for (Map.Entry<Integer, Integer> entry : this.a.entrySet()) {
            if (entry.getValue().intValue() > i2) {
                i2 = entry.getValue().intValue();
                arrayList.clear();
            } else if (entry.getValue().intValue() == i2) {
            }
            arrayList.add(entry.getKey());
        }
        return c.a.a.b0.a.c(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i2) {
        Integer num = this.a.get(Integer.valueOf(i2));
        if (num == null) {
            num = 0;
        }
        this.a.put(Integer.valueOf(i2), Integer.valueOf(num.intValue() + 1));
    }
}
