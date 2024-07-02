package c.a.a.c0.d;

import c.a.a.l;
import c.a.a.s;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
final class b {
    private final c.a.a.x.b a;

    /* renamed from: c, reason: collision with root package name */
    private final int f1235c;

    /* renamed from: d, reason: collision with root package name */
    private final int f1236d;
    private final int e;
    private final int f;
    private final float g;

    /* renamed from: i, reason: collision with root package name */
    private final s f1237i;
    private final List<a> b = new ArrayList(5);
    private final int[] h = new int[3];

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(c.a.a.x.b bVar, int i2, int i3, int i4, int i5, float f, s sVar) {
        this.a = bVar;
        this.f1235c = i2;
        this.f1236d = i3;
        this.e = i4;
        this.f = i5;
        this.g = f;
        this.f1237i = sVar;
    }

    private static float a(int[] iArr, int i2) {
        return (i2 - iArr[2]) - (iArr[1] / 2.0f);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0062, code lost:
    
        if (r2[1] <= r12) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0065, code lost:
    
        if (r10 >= r1) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006b, code lost:
    
        if (r0.d(r11, r10) != false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x006f, code lost:
    
        if (r2[2] > r12) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0071, code lost:
    
        r2[2] = r2[2] + 1;
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x007b, code lost:
    
        if (r2[2] <= r12) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x007d, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x008f, code lost:
    
        if ((java.lang.Math.abs(((r2[0] + r2[1]) + r2[2]) - r13) * 5) < (r13 * 2)) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0091, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0096, code lost:
    
        if (d(r2) == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x009c, code lost:
    
        return a(r2, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:?, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:?, code lost:
    
        return Float.NaN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float b(int r10, int r11, int r12, int r13) {
        /*
            r9 = this;
            c.a.a.x.b r0 = r9.a
            int r1 = r0.g()
            int[] r2 = r9.h
            r3 = 0
            r2[r3] = r3
            r4 = 1
            r2[r4] = r3
            r5 = 2
            r2[r5] = r3
            r6 = r10
        L12:
            if (r6 < 0) goto L26
            boolean r7 = r0.d(r11, r6)
            if (r7 == 0) goto L26
            r7 = r2[r4]
            if (r7 > r12) goto L26
            r7 = r2[r4]
            int r7 = r7 + r4
            r2[r4] = r7
            int r6 = r6 + (-1)
            goto L12
        L26:
            r7 = 2143289344(0x7fc00000, float:NaN)
            if (r6 < 0) goto L9c
            r8 = r2[r4]
            if (r8 <= r12) goto L30
            goto L9c
        L30:
            if (r6 < 0) goto L44
            boolean r8 = r0.d(r11, r6)
            if (r8 != 0) goto L44
            r8 = r2[r3]
            if (r8 > r12) goto L44
            r8 = r2[r3]
            int r8 = r8 + r4
            r2[r3] = r8
            int r6 = r6 + (-1)
            goto L30
        L44:
            r6 = r2[r3]
            if (r6 <= r12) goto L49
            return r7
        L49:
            int r10 = r10 + r4
        L4a:
            if (r10 >= r1) goto L5e
            boolean r6 = r0.d(r11, r10)
            if (r6 == 0) goto L5e
            r6 = r2[r4]
            if (r6 > r12) goto L5e
            r6 = r2[r4]
            int r6 = r6 + r4
            r2[r4] = r6
            int r10 = r10 + 1
            goto L4a
        L5e:
            if (r10 == r1) goto L9c
            r6 = r2[r4]
            if (r6 <= r12) goto L65
            goto L9c
        L65:
            if (r10 >= r1) goto L79
            boolean r6 = r0.d(r11, r10)
            if (r6 != 0) goto L79
            r6 = r2[r5]
            if (r6 > r12) goto L79
            r6 = r2[r5]
            int r6 = r6 + r4
            r2[r5] = r6
            int r10 = r10 + 1
            goto L65
        L79:
            r11 = r2[r5]
            if (r11 <= r12) goto L7e
            return r7
        L7e:
            r11 = r2[r3]
            r12 = r2[r4]
            int r11 = r11 + r12
            r12 = r2[r5]
            int r11 = r11 + r12
            int r11 = r11 - r13
            int r11 = java.lang.Math.abs(r11)
            int r11 = r11 * 5
            int r13 = r13 * 2
            if (r11 < r13) goto L92
            return r7
        L92:
            boolean r11 = r9.d(r2)
            if (r11 == 0) goto L9c
            float r7 = a(r2, r10)
        L9c:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.c0.d.b.b(int, int, int, int):float");
    }

    private boolean d(int[] iArr) {
        float f = this.g;
        float f2 = f / 2.0f;
        for (int i2 = 0; i2 < 3; i2++) {
            if (Math.abs(f - iArr[i2]) >= f2) {
                return false;
            }
        }
        return true;
    }

    private a e(int[] iArr, int i2, int i3) {
        int i4 = iArr[0] + iArr[1] + iArr[2];
        float a = a(iArr, i3);
        float b = b(i2, (int) a, iArr[1] * 2, i4);
        if (Float.isNaN(b)) {
            return null;
        }
        float f = ((iArr[0] + iArr[1]) + iArr[2]) / 3.0f;
        for (a aVar : this.b) {
            if (aVar.f(f, b, a)) {
                return aVar.g(b, a, f);
            }
        }
        a aVar2 = new a(a, b, f);
        this.b.add(aVar2);
        s sVar = this.f1237i;
        if (sVar == null) {
            return null;
        }
        sVar.a(aVar2);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a c() {
        a e;
        a e2;
        int i2 = this.f1235c;
        int i3 = this.f;
        int i4 = this.e + i2;
        int i5 = this.f1236d + (i3 / 2);
        int[] iArr = new int[3];
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = ((i6 & 1) == 0 ? (i6 + 1) / 2 : -((i6 + 1) / 2)) + i5;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            int i8 = i2;
            while (i8 < i4 && !this.a.d(i8, i7)) {
                i8++;
            }
            int i9 = 0;
            while (i8 < i4) {
                if (!this.a.d(i8, i7)) {
                    if (i9 == 1) {
                        i9++;
                    }
                    iArr[i9] = iArr[i9] + 1;
                } else if (i9 == 1) {
                    iArr[i9] = iArr[i9] + 1;
                } else if (i9 != 2) {
                    i9++;
                    iArr[i9] = iArr[i9] + 1;
                } else {
                    if (d(iArr) && (e2 = e(iArr, i7, i8)) != null) {
                        return e2;
                    }
                    iArr[0] = iArr[2];
                    iArr[1] = 1;
                    iArr[2] = 0;
                    i9 = 1;
                }
                i8++;
            }
            if (d(iArr) && (e = e(iArr, i7, i4)) != null) {
                return e;
            }
        }
        if (this.b.isEmpty()) {
            throw l.a();
        }
        return this.b.get(0);
    }
}
