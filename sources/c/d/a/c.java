package c.d.a;

import c.d.a.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public final class c extends c.d.a.a {
    private ArrayList<c.d.a.a> b = new ArrayList<>();

    /* renamed from: c, reason: collision with root package name */
    private HashMap<c.d.a.a, f> f1386c = new HashMap<>();

    /* renamed from: d, reason: collision with root package name */
    private ArrayList<f> f1387d = new ArrayList<>();
    private ArrayList<f> e = new ArrayList<>();
    private boolean f = true;
    private b g = null;
    boolean h = false;

    /* renamed from: i, reason: collision with root package name */
    private boolean f1388i = false;
    private long j = 0;
    private m k = null;

    /* loaded from: classes.dex */
    class a extends c.d.a.b {
        boolean a = false;
        final /* synthetic */ ArrayList b;

        a(ArrayList arrayList) {
            this.b = arrayList;
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void a(c.d.a.a aVar) {
            if (this.a) {
                return;
            }
            int size = this.b.size();
            for (int i2 = 0; i2 < size; i2++) {
                f fVar = (f) this.b.get(i2);
                fVar.a.j();
                c.this.b.add(fVar.a);
            }
        }

        @Override // c.d.a.b, c.d.a.a.InterfaceC0049a
        public void d(c.d.a.a aVar) {
            this.a = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b implements a.InterfaceC0049a {
        private c a;

        b(c cVar) {
            this.a = cVar;
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void a(c.d.a.a aVar) {
            aVar.f(this);
            c.this.b.remove(aVar);
            boolean z = true;
            ((f) this.a.f1386c.get(aVar)).f = true;
            if (c.this.h) {
                return;
            }
            ArrayList arrayList = this.a.e;
            int size = arrayList.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                if (!((f) arrayList.get(i2)).f) {
                    z = false;
                    break;
                }
                i2++;
            }
            if (z) {
                ArrayList<a.InterfaceC0049a> arrayList2 = c.this.a;
                if (arrayList2 != null) {
                    ArrayList arrayList3 = (ArrayList) arrayList2.clone();
                    int size2 = arrayList3.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        ((a.InterfaceC0049a) arrayList3.get(i3)).a(this.a);
                    }
                }
                this.a.f1388i = false;
            }
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void b(c.d.a.a aVar) {
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void c(c.d.a.a aVar) {
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void d(c.d.a.a aVar) {
            ArrayList<a.InterfaceC0049a> arrayList;
            c cVar = c.this;
            if (cVar.h || cVar.b.size() != 0 || (arrayList = c.this.a) == null) {
                return;
            }
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                c.this.a.get(i2).d(this.a);
            }
        }
    }

    /* renamed from: c.d.a.c$c, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0050c {
        private f a;

        C0050c(c.d.a.a aVar) {
            f fVar = (f) c.this.f1386c.get(aVar);
            this.a = fVar;
            if (fVar == null) {
                this.a = new f(aVar);
                c.this.f1386c.put(aVar, this.a);
                c.this.f1387d.add(this.a);
            }
        }

        public C0050c a(c.d.a.a aVar) {
            f fVar = (f) c.this.f1386c.get(aVar);
            if (fVar == null) {
                fVar = new f(aVar);
                c.this.f1386c.put(aVar, fVar);
                c.this.f1387d.add(fVar);
            }
            fVar.a(new d(this.a, 1));
            return this;
        }

        public C0050c b(c.d.a.a aVar) {
            f fVar = (f) c.this.f1386c.get(aVar);
            if (fVar == null) {
                fVar = new f(aVar);
                c.this.f1386c.put(aVar, fVar);
                c.this.f1387d.add(fVar);
            }
            fVar.a(new d(this.a, 0));
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d {
        public f a;
        public int b;

        public d(f fVar, int i2) {
            this.a = fVar;
            this.b = i2;
        }
    }

    /* loaded from: classes.dex */
    private static class e implements a.InterfaceC0049a {
        private c a;
        private f b;

        /* renamed from: c, reason: collision with root package name */
        private int f1390c;

        public e(c cVar, f fVar, int i2) {
            this.a = cVar;
            this.b = fVar;
            this.f1390c = i2;
        }

        private void e(c.d.a.a aVar) {
            if (this.a.h) {
                return;
            }
            d dVar = null;
            int size = this.b.f1391c.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                d dVar2 = this.b.f1391c.get(i2);
                if (dVar2.b == this.f1390c && dVar2.a.a == aVar) {
                    aVar.f(this);
                    dVar = dVar2;
                    break;
                }
                i2++;
            }
            this.b.f1391c.remove(dVar);
            if (this.b.f1391c.size() == 0) {
                this.b.a.j();
                this.a.b.add(this.b.a);
            }
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void a(c.d.a.a aVar) {
            if (this.f1390c == 1) {
                e(aVar);
            }
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void b(c.d.a.a aVar) {
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void c(c.d.a.a aVar) {
            if (this.f1390c == 0) {
                e(aVar);
            }
        }

        @Override // c.d.a.a.InterfaceC0049a
        public void d(c.d.a.a aVar) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class f implements Cloneable {
        public c.d.a.a a;
        public ArrayList<d> b = null;

        /* renamed from: c, reason: collision with root package name */
        public ArrayList<d> f1391c = null;

        /* renamed from: d, reason: collision with root package name */
        public ArrayList<f> f1392d = null;
        public ArrayList<f> e = null;
        public boolean f = false;

        public f(c.d.a.a aVar) {
            this.a = aVar;
        }

        public void a(d dVar) {
            if (this.b == null) {
                this.b = new ArrayList<>();
                this.f1392d = new ArrayList<>();
            }
            this.b.add(dVar);
            if (!this.f1392d.contains(dVar.a)) {
                this.f1392d.add(dVar.a);
            }
            f fVar = dVar.a;
            if (fVar.e == null) {
                fVar.e = new ArrayList<>();
            }
            fVar.e.add(this);
        }

        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public f clone() {
            try {
                f fVar = (f) super.clone();
                fVar.a = this.a.clone();
                return fVar;
            } catch (CloneNotSupportedException unused) {
                throw new AssertionError();
            }
        }
    }

    private void v() {
        if (!this.f) {
            int size = this.f1387d.size();
            for (int i2 = 0; i2 < size; i2++) {
                f fVar = this.f1387d.get(i2);
                ArrayList<d> arrayList = fVar.b;
                if (arrayList != null && arrayList.size() > 0) {
                    int size2 = fVar.b.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        d dVar = fVar.b.get(i3);
                        if (fVar.f1392d == null) {
                            fVar.f1392d = new ArrayList<>();
                        }
                        if (!fVar.f1392d.contains(dVar.a)) {
                            fVar.f1392d.add(dVar.a);
                        }
                    }
                }
                fVar.f = false;
            }
            return;
        }
        this.e.clear();
        ArrayList arrayList2 = new ArrayList();
        int size3 = this.f1387d.size();
        for (int i4 = 0; i4 < size3; i4++) {
            f fVar2 = this.f1387d.get(i4);
            ArrayList<d> arrayList3 = fVar2.b;
            if (arrayList3 == null || arrayList3.size() == 0) {
                arrayList2.add(fVar2);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        while (arrayList2.size() > 0) {
            int size4 = arrayList2.size();
            for (int i5 = 0; i5 < size4; i5++) {
                f fVar3 = (f) arrayList2.get(i5);
                this.e.add(fVar3);
                ArrayList<f> arrayList5 = fVar3.e;
                if (arrayList5 != null) {
                    int size5 = arrayList5.size();
                    for (int i6 = 0; i6 < size5; i6++) {
                        f fVar4 = fVar3.e.get(i6);
                        fVar4.f1392d.remove(fVar3);
                        if (fVar4.f1392d.size() == 0) {
                            arrayList4.add(fVar4);
                        }
                    }
                }
            }
            arrayList2.clear();
            arrayList2.addAll(arrayList4);
            arrayList4.clear();
        }
        this.f = false;
        if (this.e.size() != this.f1387d.size()) {
            throw new IllegalStateException("Circular dependencies cannot exist in AnimatorSet");
        }
    }

    @Override // c.d.a.a
    public void b() {
        this.h = true;
        if (q()) {
            ArrayList arrayList = null;
            ArrayList<a.InterfaceC0049a> arrayList2 = this.a;
            if (arrayList2 != null) {
                arrayList = (ArrayList) arrayList2.clone();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((a.InterfaceC0049a) it.next()).d(this);
                }
            }
            m mVar = this.k;
            if (mVar != null && mVar.e()) {
                this.k.b();
            } else if (this.e.size() > 0) {
                Iterator<f> it2 = this.e.iterator();
                while (it2.hasNext()) {
                    it2.next().a.b();
                }
            }
            if (arrayList != null) {
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    ((a.InterfaceC0049a) it3.next()).a(this);
                }
            }
            this.f1388i = false;
        }
    }

    @Override // c.d.a.a
    public boolean e() {
        Iterator<f> it = this.f1387d.iterator();
        while (it.hasNext()) {
            if (it.next().a.e()) {
                return true;
            }
        }
        return false;
    }

    @Override // c.d.a.a
    public /* bridge */ /* synthetic */ c.d.a.a g(long j) {
        u(j);
        return this;
    }

    @Override // c.d.a.a
    public void h(long j) {
        this.j = j;
    }

    @Override // c.d.a.a
    public void i(Object obj) {
        Iterator<f> it = this.f1387d.iterator();
        while (it.hasNext()) {
            c.d.a.a aVar = it.next().a;
            if (aVar instanceof c) {
                ((c) aVar).i(obj);
            } else if (aVar instanceof i) {
                ((i) aVar).i(obj);
            }
        }
    }

    @Override // c.d.a.a
    public void j() {
        this.h = false;
        this.f1388i = true;
        v();
        int size = this.e.size();
        for (int i2 = 0; i2 < size; i2++) {
            f fVar = this.e.get(i2);
            ArrayList<a.InterfaceC0049a> d2 = fVar.a.d();
            if (d2 != null && d2.size() > 0) {
                Iterator it = new ArrayList(d2).iterator();
                while (it.hasNext()) {
                    a.InterfaceC0049a interfaceC0049a = (a.InterfaceC0049a) it.next();
                    if ((interfaceC0049a instanceof e) || (interfaceC0049a instanceof b)) {
                        fVar.a.f(interfaceC0049a);
                    }
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < size; i3++) {
            f fVar2 = this.e.get(i3);
            if (this.g == null) {
                this.g = new b(this);
            }
            ArrayList<d> arrayList2 = fVar2.b;
            if (arrayList2 == null || arrayList2.size() == 0) {
                arrayList.add(fVar2);
            } else {
                int size2 = fVar2.b.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    d dVar = fVar2.b.get(i4);
                    dVar.a.a.a(new e(this, fVar2, dVar.b));
                }
                fVar2.f1391c = (ArrayList) fVar2.b.clone();
            }
            fVar2.a.a(this.g);
        }
        if (this.j <= 0) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                f fVar3 = (f) it2.next();
                fVar3.a.j();
                this.b.add(fVar3.a);
            }
        } else {
            m C = m.C(0.0f, 1.0f);
            this.k = C;
            C.g(this.j);
            this.k.a(new a(arrayList));
            this.k.j();
        }
        ArrayList<a.InterfaceC0049a> arrayList3 = this.a;
        if (arrayList3 != null) {
            ArrayList arrayList4 = (ArrayList) arrayList3.clone();
            int size3 = arrayList4.size();
            for (int i5 = 0; i5 < size3; i5++) {
                ((a.InterfaceC0049a) arrayList4.get(i5)).c(this);
            }
        }
        if (this.f1387d.size() == 0 && this.j == 0) {
            this.f1388i = false;
            ArrayList<a.InterfaceC0049a> arrayList5 = this.a;
            if (arrayList5 != null) {
                ArrayList arrayList6 = (ArrayList) arrayList5.clone();
                int size4 = arrayList6.size();
                for (int i6 = 0; i6 < size4; i6++) {
                    ((a.InterfaceC0049a) arrayList6.get(i6)).a(this);
                }
            }
        }
    }

    @Override // c.d.a.a
    /* renamed from: p, reason: merged with bridge method [inline-methods] */
    public c clone() {
        c cVar = (c) super.clone();
        cVar.f = true;
        cVar.h = false;
        cVar.f1388i = false;
        cVar.b = new ArrayList<>();
        cVar.f1386c = new HashMap<>();
        cVar.f1387d = new ArrayList<>();
        cVar.e = new ArrayList<>();
        HashMap hashMap = new HashMap();
        Iterator<f> it = this.f1387d.iterator();
        while (it.hasNext()) {
            f next = it.next();
            f clone = next.clone();
            hashMap.put(next, clone);
            cVar.f1387d.add(clone);
            cVar.f1386c.put(clone.a, clone);
            ArrayList arrayList = null;
            clone.b = null;
            clone.f1391c = null;
            clone.e = null;
            clone.f1392d = null;
            ArrayList<a.InterfaceC0049a> d2 = clone.a.d();
            if (d2 != null) {
                Iterator<a.InterfaceC0049a> it2 = d2.iterator();
                while (it2.hasNext()) {
                    a.InterfaceC0049a next2 = it2.next();
                    if (next2 instanceof b) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(next2);
                    }
                }
                if (arrayList != null) {
                    Iterator it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        d2.remove((a.InterfaceC0049a) it3.next());
                    }
                }
            }
        }
        Iterator<f> it4 = this.f1387d.iterator();
        while (it4.hasNext()) {
            f next3 = it4.next();
            f fVar = (f) hashMap.get(next3);
            ArrayList<d> arrayList2 = next3.b;
            if (arrayList2 != null) {
                Iterator<d> it5 = arrayList2.iterator();
                while (it5.hasNext()) {
                    d next4 = it5.next();
                    fVar.a(new d((f) hashMap.get(next4.a), next4.b));
                }
            }
        }
        return cVar;
    }

    public boolean q() {
        return this.f1388i;
    }

    public C0050c r(c.d.a.a aVar) {
        if (aVar == null) {
            return null;
        }
        this.f = true;
        return new C0050c(aVar);
    }

    public void s(c.d.a.a... aVarArr) {
        if (aVarArr != null) {
            this.f = true;
            int i2 = 0;
            if (aVarArr.length == 1) {
                r(aVarArr[0]);
                return;
            }
            while (i2 < aVarArr.length - 1) {
                C0050c r = r(aVarArr[i2]);
                i2++;
                r.a(aVarArr[i2]);
            }
        }
    }

    public void t(c.d.a.a... aVarArr) {
        if (aVarArr != null) {
            this.f = true;
            C0050c r = r(aVarArr[0]);
            for (int i2 = 1; i2 < aVarArr.length; i2++) {
                r.b(aVarArr[i2]);
            }
        }
    }

    public c u(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("duration must be a value of zero or greater");
        }
        Iterator<f> it = this.f1387d.iterator();
        while (it.hasNext()) {
            it.next().a.g(j);
        }
        return this;
    }
}
