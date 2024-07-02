package c.a.a.x;

import java.util.List;

/* loaded from: classes.dex */
public final class e {
    private final byte[] a;
    private final String b;

    /* renamed from: c, reason: collision with root package name */
    private final List<byte[]> f1306c;

    /* renamed from: d, reason: collision with root package name */
    private final String f1307d;
    private Object e;
    private final int f;
    private final int g;

    public e(byte[] bArr, String str, List<byte[]> list, String str2) {
        this(bArr, str, list, str2, -1, -1);
    }

    public e(byte[] bArr, String str, List<byte[]> list, String str2, int i2, int i3) {
        this.a = bArr;
        this.b = str;
        this.f1306c = list;
        this.f1307d = str2;
        this.f = i3;
        this.g = i2;
    }

    public List<byte[]> a() {
        return this.f1306c;
    }

    public String b() {
        return this.f1307d;
    }

    public Object c() {
        return this.e;
    }

    public byte[] d() {
        return this.a;
    }

    public int e() {
        return this.f;
    }

    public int f() {
        return this.g;
    }

    public String g() {
        return this.b;
    }

    public boolean h() {
        return this.f >= 0 && this.g >= 0;
    }

    public void i(Integer num) {
    }

    public void j(Integer num) {
    }

    public void k(Object obj) {
        this.e = obj;
    }
}
