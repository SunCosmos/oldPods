package c.a.a.c0.d;

import c.a.a.l;
import c.a.a.r;
import c.a.a.s;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class e {
    private final c.a.a.x.b a;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1240c;
    private final s e;
    private final List<d> b = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    private final int[] f1241d = new int[5];

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class b implements Comparator<d>, Serializable {
        private final float a;

        private b(float f) {
            this.a = f;
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(d dVar, d dVar2) {
            if (dVar2.h() != dVar.h()) {
                return dVar2.h() - dVar.h();
            }
            float abs = Math.abs(dVar2.i() - this.a);
            float abs2 = Math.abs(dVar.i() - this.a);
            if (abs < abs2) {
                return 1;
            }
            return abs == abs2 ? 0 : -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class c implements Comparator<d>, Serializable {
        private final float a;

        private c(float f) {
            this.a = f;
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(d dVar, d dVar2) {
            float abs = Math.abs(dVar2.i() - this.a);
            float abs2 = Math.abs(dVar.i() - this.a);
            if (abs < abs2) {
                return -1;
            }
            return abs == abs2 ? 0 : 1;
        }
    }

    public e(c.a.a.x.b bVar, s sVar) {
        this.a = bVar;
        this.e = sVar;
    }

    private static float a(int[] iArr, int i2) {
        return ((i2 - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x002a, code lost:
    
        if (r18 >= r6) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
    
        if (r17 < r6) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0030, code lost:
    
        if (r18 < r6) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003c, code lost:
    
        if (r16.a.d(r18 - r6, r17 - r6) != false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0040, code lost:
    
        if (r4[1] > r19) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0042, code lost:
    
        r4[1] = r4[1] + 1;
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004a, code lost:
    
        if (r17 < r6) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004c, code lost:
    
        if (r18 < r6) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0050, code lost:
    
        if (r4[1] <= r19) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0054, code lost:
    
        if (r17 < r6) goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0056, code lost:
    
        if (r18 < r6) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0062, code lost:
    
        if (r16.a.d(r18 - r6, r17 - r6) == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0066, code lost:
    
        if (r4[0] > r19) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0068, code lost:
    
        r4[0] = r4[0] + 1;
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0072, code lost:
    
        if (r4[0] <= r19) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0074, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0075, code lost:
    
        r6 = r16.a.g();
        r9 = r16.a.j();
        r10 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0082, code lost:
    
        r11 = r17 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0084, code lost:
    
        if (r11 >= r6) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0086, code lost:
    
        r12 = r18 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0088, code lost:
    
        if (r12 >= r9) goto L92;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0090, code lost:
    
        if (r16.a.d(r12, r11) == false) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0092, code lost:
    
        r4[2] = r4[2] + 1;
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009a, code lost:
    
        if (r11 >= r6) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x009e, code lost:
    
        if ((r18 + r10) < r9) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a2, code lost:
    
        r11 = r17 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a5, code lost:
    
        if (r11 >= r6) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a7, code lost:
    
        r13 = r18 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a9, code lost:
    
        if (r13 >= r9) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00b1, code lost:
    
        if (r16.a.d(r13, r11) != false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00b5, code lost:
    
        if (r4[3] >= r19) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00b7, code lost:
    
        r4[3] = r4[3] + 1;
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00bf, code lost:
    
        if (r11 >= r6) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00c3, code lost:
    
        if ((r18 + r10) >= r9) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00c7, code lost:
    
        if (r4[3] < r19) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00ca, code lost:
    
        r11 = r17 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00cd, code lost:
    
        if (r11 >= r6) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00cf, code lost:
    
        r14 = r18 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00d1, code lost:
    
        if (r14 >= r9) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00d9, code lost:
    
        if (r16.a.d(r14, r11) == false) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00dd, code lost:
    
        if (r4[4] >= r19) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00df, code lost:
    
        r4[4] = r4[4] + 1;
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00e9, code lost:
    
        if (r4[4] < r19) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00eb, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0102, code lost:
    
        if (java.lang.Math.abs(((((r4[0] + r4[1]) + r4[2]) + r4[3]) + r4[4]) - r20) >= (r20 * 2)) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0108, code lost:
    
        if (g(r4) == false) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x010a, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:?, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:?, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean b(int r17, int r18, int r19, int r20) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.c0.d.e.b(int, int, int, int):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x003a, code lost:
    
        if (r2[1] <= r13) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003f, code lost:
    
        if (r3 < 0) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0045, code lost:
    
        if (r0.d(r3, r12) == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0049, code lost:
    
        if (r2[0] > r13) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004b, code lost:
    
        r2[0] = r2[0] + 1;
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0055, code lost:
    
        if (r2[0] <= r13) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0057, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0058, code lost:
    
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
    
        if (r11 >= r1) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x005f, code lost:
    
        if (r0.d(r11, r12) == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0061, code lost:
    
        r2[2] = r2[2] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0069, code lost:
    
        if (r11 != r1) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x006b, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x006d, code lost:
    
        if (r11 >= r1) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0073, code lost:
    
        if (r0.d(r11, r12) != false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0077, code lost:
    
        if (r2[3] >= r13) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0079, code lost:
    
        r2[3] = r2[3] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0081, code lost:
    
        if (r11 == r1) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0085, code lost:
    
        if (r2[3] < r13) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0089, code lost:
    
        if (r11 >= r1) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x008f, code lost:
    
        if (r0.d(r11, r12) == false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0093, code lost:
    
        if (r2[4] >= r13) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0095, code lost:
    
        r2[4] = r2[4] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x009f, code lost:
    
        if (r2[4] < r13) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a1, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00b7, code lost:
    
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r2[4]) - r14) * 5) < r14) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00b9, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00be, code lost:
    
        if (g(r2) == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00c4, code lost:
    
        return a(r2, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:?, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:?, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:?, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:?, code lost:
    
        return Float.NaN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float c(int r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 197
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.c0.d.e.c(int, int, int, int):float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x003a, code lost:
    
        if (r2[1] <= r13) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003f, code lost:
    
        if (r3 < 0) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0045, code lost:
    
        if (r0.d(r12, r3) == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0049, code lost:
    
        if (r2[0] > r13) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004b, code lost:
    
        r2[0] = r2[0] + 1;
        r3 = r3 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0055, code lost:
    
        if (r2[0] <= r13) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0057, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0058, code lost:
    
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0059, code lost:
    
        if (r11 >= r1) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x005f, code lost:
    
        if (r0.d(r12, r11) == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0061, code lost:
    
        r2[2] = r2[2] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0069, code lost:
    
        if (r11 != r1) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x006b, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x006d, code lost:
    
        if (r11 >= r1) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0073, code lost:
    
        if (r0.d(r12, r11) != false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0077, code lost:
    
        if (r2[3] >= r13) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0079, code lost:
    
        r2[3] = r2[3] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0081, code lost:
    
        if (r11 == r1) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0085, code lost:
    
        if (r2[3] < r13) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0089, code lost:
    
        if (r11 >= r1) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x008f, code lost:
    
        if (r0.d(r12, r11) == false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0093, code lost:
    
        if (r2[4] >= r13) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0095, code lost:
    
        r2[4] = r2[4] + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x009f, code lost:
    
        if (r2[4] < r13) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a1, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00b9, code lost:
    
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r2[4]) - r14) * 5) < (r14 * 2)) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00bb, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00c0, code lost:
    
        if (g(r2) == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00c6, code lost:
    
        return a(r2, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:?, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:?, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:?, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:?, code lost:
    
        return Float.NaN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private float d(int r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 199
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.c0.d.e.d(int, int, int, int):float");
    }

    private int f() {
        if (this.b.size() <= 1) {
            return 0;
        }
        d dVar = null;
        for (d dVar2 : this.b) {
            if (dVar2.h() >= 2) {
                if (dVar != null) {
                    this.f1240c = true;
                    return ((int) (Math.abs(dVar.c() - dVar2.c()) - Math.abs(dVar.d() - dVar2.d()))) / 2;
                }
                dVar = dVar2;
            }
        }
        return 0;
    }

    protected static boolean g(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 5; i3++) {
            int i4 = iArr[i3];
            if (i4 == 0) {
                return false;
            }
            i2 += i4;
        }
        if (i2 < 7) {
            return false;
        }
        float f = i2 / 7.0f;
        float f2 = f / 2.0f;
        return Math.abs(f - ((float) iArr[0])) < f2 && Math.abs(f - ((float) iArr[1])) < f2 && Math.abs((f * 3.0f) - ((float) iArr[2])) < 3.0f * f2 && Math.abs(f - ((float) iArr[3])) < f2 && Math.abs(f - ((float) iArr[4])) < f2;
    }

    private int[] h() {
        int[] iArr = this.f1241d;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        iArr[4] = 0;
        return iArr;
    }

    private boolean j() {
        int size = this.b.size();
        float f = 0.0f;
        int i2 = 0;
        float f2 = 0.0f;
        for (d dVar : this.b) {
            if (dVar.h() >= 2) {
                i2++;
                f2 += dVar.i();
            }
        }
        if (i2 < 3) {
            return false;
        }
        float f3 = f2 / size;
        Iterator<d> it = this.b.iterator();
        while (it.hasNext()) {
            f += Math.abs(it.next().i() - f3);
        }
        return f <= f2 * 0.05f;
    }

    private d[] k() {
        int size = this.b.size();
        if (size < 3) {
            throw l.a();
        }
        float f = 0.0f;
        if (size > 3) {
            Iterator<d> it = this.b.iterator();
            float f2 = 0.0f;
            float f3 = 0.0f;
            while (it.hasNext()) {
                float i2 = it.next().i();
                f2 += i2;
                f3 += i2 * i2;
            }
            float f4 = f2 / size;
            float sqrt = (float) Math.sqrt((f3 / r0) - (f4 * f4));
            Collections.sort(this.b, new c(f4));
            float max = Math.max(0.2f * f4, sqrt);
            int i3 = 0;
            while (i3 < this.b.size() && this.b.size() > 3) {
                if (Math.abs(this.b.get(i3).i() - f4) > max) {
                    this.b.remove(i3);
                    i3--;
                }
                i3++;
            }
        }
        if (this.b.size() > 3) {
            Iterator<d> it2 = this.b.iterator();
            while (it2.hasNext()) {
                f += it2.next().i();
            }
            Collections.sort(this.b, new b(f / this.b.size()));
            List<d> list = this.b;
            list.subList(3, list.size()).clear();
        }
        return new d[]{this.b.get(0), this.b.get(1), this.b.get(2)};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final f e(Map<c.a.a.e, ?> map) {
        boolean z = map != null && map.containsKey(c.a.a.e.TRY_HARDER);
        boolean z2 = map != null && map.containsKey(c.a.a.e.PURE_BARCODE);
        int g = this.a.g();
        int j = this.a.j();
        int i2 = (g * 3) / 228;
        if (i2 < 3 || z) {
            i2 = 3;
        }
        int[] iArr = new int[5];
        int i3 = i2 - 1;
        boolean z3 = false;
        while (i3 < g && !z3) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            int i4 = 0;
            int i5 = 0;
            while (i4 < j) {
                if (this.a.d(i4, i3)) {
                    if ((i5 & 1) == 1) {
                        i5++;
                    }
                    iArr[i5] = iArr[i5] + 1;
                } else if ((i5 & 1) != 0) {
                    iArr[i5] = iArr[i5] + 1;
                } else if (i5 == 4) {
                    if (!g(iArr)) {
                        iArr[0] = iArr[2];
                        iArr[1] = iArr[3];
                        iArr[2] = iArr[4];
                        iArr[3] = 1;
                        iArr[4] = 0;
                    } else if (i(iArr, i3, i4, z2)) {
                        if (this.f1240c) {
                            z3 = j();
                        } else {
                            int f = f();
                            if (f > iArr[2]) {
                                i3 += (f - iArr[2]) - 2;
                                i4 = j - 1;
                            }
                        }
                        iArr[0] = 0;
                        iArr[1] = 0;
                        iArr[2] = 0;
                        iArr[3] = 0;
                        iArr[4] = 0;
                        i2 = 2;
                        i5 = 0;
                    } else {
                        iArr[0] = iArr[2];
                        iArr[1] = iArr[3];
                        iArr[2] = iArr[4];
                        iArr[3] = 1;
                        iArr[4] = 0;
                    }
                    i5 = 3;
                } else {
                    i5++;
                    iArr[i5] = iArr[i5] + 1;
                }
                i4++;
            }
            if (g(iArr) && i(iArr, i3, j, z2)) {
                i2 = iArr[0];
                if (this.f1240c) {
                    z3 = j();
                }
            }
            i3 += i2;
        }
        d[] k = k();
        r.e(k);
        return new f(k);
    }

    protected final boolean i(int[] iArr, int i2, int i3, boolean z) {
        boolean z2 = false;
        int i4 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int a2 = (int) a(iArr, i3);
        float d2 = d(i2, a2, iArr[2], i4);
        if (!Float.isNaN(d2)) {
            int i5 = (int) d2;
            float c2 = c(a2, i5, iArr[2], i4);
            if (!Float.isNaN(c2) && (!z || b(i5, (int) c2, iArr[2], i4))) {
                float f = i4 / 7.0f;
                int i6 = 0;
                while (true) {
                    if (i6 >= this.b.size()) {
                        break;
                    }
                    d dVar = this.b.get(i6);
                    if (dVar.f(f, d2, c2)) {
                        this.b.set(i6, dVar.g(d2, c2, f));
                        z2 = true;
                        break;
                    }
                    i6++;
                }
                if (!z2) {
                    d dVar2 = new d(c2, d2, f);
                    this.b.add(dVar2);
                    s sVar = this.e;
                    if (sVar != null) {
                        sVar.a(dVar2);
                    }
                }
                return true;
            }
        }
        return false;
    }
}
