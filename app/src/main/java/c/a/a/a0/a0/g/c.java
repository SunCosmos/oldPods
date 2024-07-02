package c.a.a.a0.a0.g;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
final class c {
    private final List<b> a;
    private final int b;

    /* renamed from: c, reason: collision with root package name */
    private final boolean f1169c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(List<b> list, int i2, boolean z) {
        this.a = new ArrayList(list);
        this.b = i2;
        this.f1169c = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<b> a() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(List<b> list) {
        return this.a.equals(list);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.a.equals(cVar.a()) && this.f1169c == cVar.f1169c;
    }

    public int hashCode() {
        return this.a.hashCode() ^ Boolean.valueOf(this.f1169c).hashCode();
    }

    public String toString() {
        return "{ " + this.a + " }";
    }
}
