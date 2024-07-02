package c.a.a.w.a;

/* loaded from: classes.dex */
public abstract class q {
    /* JADX INFO: Access modifiers changed from: protected */
    public q(r rVar) {
    }

    public static void b(String str, StringBuilder sb) {
        if (str == null || str.isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append('\n');
        }
        sb.append(str);
    }

    public static void c(String[] strArr, StringBuilder sb) {
        if (strArr != null) {
            for (String str : strArr) {
                b(str, sb);
            }
        }
    }

    public abstract String a();

    public final String toString() {
        return a();
    }
}
