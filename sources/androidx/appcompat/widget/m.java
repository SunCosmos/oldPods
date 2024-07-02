package androidx.appcompat.widget;

/* loaded from: classes.dex */
class m {
    private int a = 0;
    private int b = 0;

    /* renamed from: c, reason: collision with root package name */
    private int f229c = Integer.MIN_VALUE;

    /* renamed from: d, reason: collision with root package name */
    private int f230d = Integer.MIN_VALUE;
    private int e = 0;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;

    public int a() {
        return this.g ? this.a : this.b;
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.b;
    }

    public int d() {
        return this.g ? this.b : this.a;
    }

    public void e(int i2, int i3) {
        this.h = false;
        if (i2 != Integer.MIN_VALUE) {
            this.e = i2;
            this.a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f = i3;
            this.b = i3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001a, code lost:
    
        if (r2 != Integer.MIN_VALUE) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0028, code lost:
    
        if (r2 != Integer.MIN_VALUE) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(boolean r2) {
        /*
            r1 = this;
            boolean r0 = r1.g
            if (r2 != r0) goto L5
            return
        L5:
            r1.g = r2
            boolean r0 = r1.h
            if (r0 == 0) goto L2b
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r2 == 0) goto L1d
            int r2 = r1.f230d
            if (r2 == r0) goto L14
            goto L16
        L14:
            int r2 = r1.e
        L16:
            r1.a = r2
            int r2 = r1.f229c
            if (r2 == r0) goto L2f
            goto L31
        L1d:
            int r2 = r1.f229c
            if (r2 == r0) goto L22
            goto L24
        L22:
            int r2 = r1.e
        L24:
            r1.a = r2
            int r2 = r1.f230d
            if (r2 == r0) goto L2f
            goto L31
        L2b:
            int r2 = r1.e
            r1.a = r2
        L2f:
            int r2 = r1.f
        L31:
            r1.b = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.m.f(boolean):void");
    }

    public void g(int i2, int i3) {
        this.f229c = i2;
        this.f230d = i3;
        this.h = true;
        if (this.g) {
            if (i3 != Integer.MIN_VALUE) {
                this.a = i3;
            }
            if (i2 != Integer.MIN_VALUE) {
                this.b = i2;
                return;
            }
            return;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.b = i3;
        }
    }
}
