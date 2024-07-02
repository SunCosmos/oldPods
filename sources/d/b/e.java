package d.b;

import java.util.List;

/* loaded from: classes.dex */
public class e extends Exception {
    private List<d> a;

    private e(String str, List<d> list) {
        super(str);
        this.a = list;
    }

    public static e a(List<d> list) {
        return list.size() == 1 ? new e(list.get(0).b(), list) : list.size() > 1 ? new e(String.format("%d errors occured. First: %s", Integer.valueOf(list.size()), list.get(0).b()), list) : new e("An unknown error occured", list);
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (d dVar : this.a) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(dVar);
        }
        return sb.toString();
    }
}
