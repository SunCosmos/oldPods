package c.d.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import c.d.a.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class m extends c.d.a.a {
    private static ThreadLocal<f> s = new ThreadLocal<>();
    private static final ThreadLocal<ArrayList<m>> t = new a();
    private static final ThreadLocal<ArrayList<m>> u = new b();
    private static final ThreadLocal<ArrayList<m>> v = new c();
    private static final ThreadLocal<ArrayList<m>> w = new d();
    private static final ThreadLocal<ArrayList<m>> x = new e();
    private static final Interpolator y = new AccelerateDecelerateInterpolator();
    private static long z = 10;
    long b;
    private long g;
    k[] q;
    HashMap<String, k> r;

    /* renamed from: c, reason: collision with root package name */
    long f1404c = -1;

    /* renamed from: d, reason: collision with root package name */
    private boolean f1405d = false;
    private int e = 0;
    private boolean f = false;
    int h = 0;

    /* renamed from: i, reason: collision with root package name */
    private boolean f1406i = false;
    boolean j = false;
    private long k = 300;
    private long l = 0;
    private int m = 0;
    private int n = 1;
    private Interpolator o = y;
    private ArrayList<g> p = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends ThreadLocal<ArrayList<m>> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ArrayList<m> initialValue() {
            return new ArrayList<>();
        }
    }

    /* loaded from: classes.dex */
    class b extends ThreadLocal<ArrayList<m>> {
        b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ArrayList<m> initialValue() {
            return new ArrayList<>();
        }
    }

    /* loaded from: classes.dex */
    class c extends ThreadLocal<ArrayList<m>> {
        c() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ArrayList<m> initialValue() {
            return new ArrayList<>();
        }
    }

    /* loaded from: classes.dex */
    class d extends ThreadLocal<ArrayList<m>> {
        d() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ArrayList<m> initialValue() {
            return new ArrayList<>();
        }
    }

    /* loaded from: classes.dex */
    class e extends ThreadLocal<ArrayList<m>> {
        e() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ArrayList<m> initialValue() {
            return new ArrayList<>();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class f extends Handler {
        private f() {
        }

        /* synthetic */ f(a aVar) {
            this();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            boolean z;
            ArrayList arrayList = (ArrayList) m.t.get();
            ArrayList arrayList2 = (ArrayList) m.v.get();
            int i2 = message.what;
            if (i2 == 0) {
                ArrayList arrayList3 = (ArrayList) m.u.get();
                z = arrayList.size() <= 0 && arrayList2.size() <= 0;
                while (arrayList3.size() > 0) {
                    ArrayList arrayList4 = (ArrayList) arrayList3.clone();
                    arrayList3.clear();
                    int size = arrayList4.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        m mVar = (m) arrayList4.get(i3);
                        if (mVar.l == 0) {
                            mVar.I();
                        } else {
                            arrayList2.add(mVar);
                        }
                    }
                }
            } else if (i2 != 1) {
                return;
            } else {
                z = true;
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            ArrayList arrayList5 = (ArrayList) m.x.get();
            ArrayList arrayList6 = (ArrayList) m.w.get();
            int size2 = arrayList2.size();
            for (int i4 = 0; i4 < size2; i4++) {
                m mVar2 = (m) arrayList2.get(i4);
                if (mVar2.y(currentAnimationTimeMillis)) {
                    arrayList5.add(mVar2);
                }
            }
            int size3 = arrayList5.size();
            if (size3 > 0) {
                for (int i5 = 0; i5 < size3; i5++) {
                    m mVar3 = (m) arrayList5.get(i5);
                    mVar3.I();
                    mVar3.f1406i = true;
                    arrayList2.remove(mVar3);
                }
                arrayList5.clear();
            }
            int size4 = arrayList.size();
            int i6 = 0;
            while (i6 < size4) {
                m mVar4 = (m) arrayList.get(i6);
                if (mVar4.w(currentAnimationTimeMillis)) {
                    arrayList6.add(mVar4);
                }
                if (arrayList.size() == size4) {
                    i6++;
                } else {
                    size4--;
                    arrayList6.remove(mVar4);
                }
            }
            if (arrayList6.size() > 0) {
                for (int i7 = 0; i7 < arrayList6.size(); i7++) {
                    ((m) arrayList6.get(i7)).z();
                }
                arrayList6.clear();
            }
            if (z) {
                if (arrayList.isEmpty() && arrayList2.isEmpty()) {
                    return;
                }
                sendEmptyMessageDelayed(1, Math.max(0L, m.z - (AnimationUtils.currentAnimationTimeMillis() - currentAnimationTimeMillis)));
            }
        }
    }

    /* loaded from: classes.dex */
    public interface g {
        void a(m mVar);
    }

    public static m C(float... fArr) {
        m mVar = new m();
        mVar.F(fArr);
        return mVar;
    }

    private void H(boolean z2) {
        if (Looper.myLooper() == null) {
            throw new AndroidRuntimeException("Animators may only be run on Looper threads");
        }
        this.f1405d = z2;
        this.e = 0;
        this.h = 0;
        this.f = false;
        u.get().add(this);
        if (this.l == 0) {
            D(A());
            this.h = 0;
            this.f1406i = true;
            ArrayList<a.InterfaceC0049a> arrayList = this.a;
            if (arrayList != null) {
                ArrayList arrayList2 = (ArrayList) arrayList.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((a.InterfaceC0049a) arrayList2.get(i2)).c(this);
                }
            }
        }
        f fVar = s.get();
        if (fVar == null) {
            fVar = new f(null);
            s.set(fVar);
        }
        fVar.sendEmptyMessage(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        ArrayList<a.InterfaceC0049a> arrayList;
        B();
        t.get().add(this);
        if (this.l <= 0 || (arrayList = this.a) == null) {
            return;
        }
        ArrayList arrayList2 = (ArrayList) arrayList.clone();
        int size = arrayList2.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((a.InterfaceC0049a) arrayList2.get(i2)).c(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean y(long j) {
        if (!this.f) {
            this.f = true;
            this.g = j;
            return false;
        }
        long j2 = j - this.g;
        long j3 = this.l;
        if (j2 <= j3) {
            return false;
        }
        this.b = j - (j2 - j3);
        this.h = 1;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        ArrayList<a.InterfaceC0049a> arrayList;
        t.get().remove(this);
        u.get().remove(this);
        v.get().remove(this);
        this.h = 0;
        if (this.f1406i && (arrayList = this.a) != null) {
            ArrayList arrayList2 = (ArrayList) arrayList.clone();
            int size = arrayList2.size();
            for (int i2 = 0; i2 < size; i2++) {
                ((a.InterfaceC0049a) arrayList2.get(i2)).a(this);
            }
        }
        this.f1406i = false;
    }

    public long A() {
        if (!this.j || this.h == 0) {
            return 0L;
        }
        return AnimationUtils.currentAnimationTimeMillis() - this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void B() {
        if (this.j) {
            return;
        }
        int length = this.q.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.q[i2].g();
        }
        this.j = true;
    }

    public void D(long j) {
        B();
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (this.h != 1) {
            this.f1404c = j;
            this.h = 2;
        }
        this.b = currentAnimationTimeMillis - j;
        w(currentAnimationTimeMillis);
    }

    @Override // c.d.a.a
    /* renamed from: E, reason: merged with bridge method [inline-methods] */
    public m g(long j) {
        if (j >= 0) {
            this.k = j;
            return this;
        }
        throw new IllegalArgumentException("Animators cannot have negative duration: " + j);
    }

    public void F(float... fArr) {
        if (fArr == null || fArr.length == 0) {
            return;
        }
        k[] kVarArr = this.q;
        if (kVarArr == null || kVarArr.length == 0) {
            G(k.i("", fArr));
        } else {
            kVarArr[0].k(fArr);
        }
        this.j = false;
    }

    public void G(k... kVarArr) {
        int length = kVarArr.length;
        this.q = kVarArr;
        this.r = new HashMap<>(length);
        for (k kVar : kVarArr) {
            this.r.put(kVar.f(), kVar);
        }
        this.j = false;
    }

    @Override // c.d.a.a
    public void b() {
        ArrayList<a.InterfaceC0049a> arrayList;
        if (this.h != 0 || u.get().contains(this) || v.get().contains(this)) {
            if (this.f1406i && (arrayList = this.a) != null) {
                Iterator it = ((ArrayList) arrayList.clone()).iterator();
                while (it.hasNext()) {
                    ((a.InterfaceC0049a) it.next()).d(this);
                }
            }
            z();
        }
    }

    @Override // c.d.a.a
    public boolean e() {
        return this.h == 1 || this.f1406i;
    }

    @Override // c.d.a.a
    public void h(long j) {
        this.l = j;
    }

    @Override // c.d.a.a
    public void j() {
        H(false);
    }

    public String toString() {
        String str = "ValueAnimator@" + Integer.toHexString(hashCode());
        if (this.q != null) {
            for (int i2 = 0; i2 < this.q.length; i2++) {
                str = str + "\n    " + this.q[i2].toString();
            }
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v(float f2) {
        float interpolation = this.o.getInterpolation(f2);
        int length = this.q.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.q[i2].a(interpolation);
        }
        ArrayList<g> arrayList = this.p;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.p.get(i3).a(this);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean w(long r10) {
        /*
            r9 = this;
            int r0 = r9.h
            r1 = 0
            r3 = 1
            if (r0 != 0) goto L1a
            r9.h = r3
            long r4 = r9.f1404c
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 >= 0) goto L12
            r9.b = r10
            goto L1a
        L12:
            long r4 = r10 - r4
            r9.b = r4
            r4 = -1
            r9.f1404c = r4
        L1a:
            int r0 = r9.h
            r4 = 2
            r5 = 0
            if (r0 == r3) goto L23
            if (r0 == r4) goto L23
            goto L82
        L23:
            long r6 = r9.k
            r0 = 1065353216(0x3f800000, float:1.0)
            int r8 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r8 <= 0) goto L32
            long r1 = r9.b
            long r10 = r10 - r1
            float r10 = (float) r10
            float r11 = (float) r6
            float r10 = r10 / r11
            goto L34
        L32:
            r10 = 1065353216(0x3f800000, float:1.0)
        L34:
            int r11 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r11 < 0) goto L77
            int r11 = r9.e
            int r1 = r9.m
            if (r11 < r1) goto L47
            r11 = -1
            if (r1 != r11) goto L42
            goto L47
        L42:
            float r10 = java.lang.Math.min(r10, r0)
            goto L78
        L47:
            java.util.ArrayList<c.d.a.a$a> r11 = r9.a
            if (r11 == 0) goto L60
            int r11 = r11.size()
            r1 = 0
        L50:
            if (r1 >= r11) goto L60
            java.util.ArrayList<c.d.a.a$a> r2 = r9.a
            java.lang.Object r2 = r2.get(r1)
            c.d.a.a$a r2 = (c.d.a.a.InterfaceC0049a) r2
            r2.b(r9)
            int r1 = r1 + 1
            goto L50
        L60:
            int r11 = r9.n
            if (r11 != r4) goto L69
            boolean r11 = r9.f1405d
            r11 = r11 ^ r3
            r9.f1405d = r11
        L69:
            int r11 = r9.e
            int r1 = (int) r10
            int r11 = r11 + r1
            r9.e = r11
            float r10 = r10 % r0
            long r1 = r9.b
            long r3 = r9.k
            long r1 = r1 + r3
            r9.b = r1
        L77:
            r3 = 0
        L78:
            boolean r11 = r9.f1405d
            if (r11 == 0) goto L7e
            float r10 = r0 - r10
        L7e:
            r9.v(r10)
            r5 = r3
        L82:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: c.d.a.m.w(long):boolean");
    }

    @Override // c.d.a.a
    /* renamed from: x, reason: merged with bridge method [inline-methods] */
    public m clone() {
        m mVar = (m) super.clone();
        ArrayList<g> arrayList = this.p;
        if (arrayList != null) {
            mVar.p = new ArrayList<>();
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                mVar.p.add(arrayList.get(i2));
            }
        }
        mVar.f1404c = -1L;
        mVar.f1405d = false;
        mVar.e = 0;
        mVar.j = false;
        mVar.h = 0;
        mVar.f = false;
        k[] kVarArr = this.q;
        if (kVarArr != null) {
            int length = kVarArr.length;
            mVar.q = new k[length];
            mVar.r = new HashMap<>(length);
            for (int i3 = 0; i3 < length; i3++) {
                k clone = kVarArr[i3].clone();
                mVar.q[i3] = clone;
                mVar.r.put(clone.f(), clone);
            }
        }
        return mVar;
    }
}
