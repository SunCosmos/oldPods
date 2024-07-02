package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* loaded from: classes.dex */
public final class e extends RecyclerView.OnScrollListener {
    private ViewPager2.OnPageChangeCallback a;

    @NonNull
    private final ViewPager2 b;

    /* renamed from: c */
    @NonNull
    private final RecyclerView f1153c;

    /* renamed from: d */
    @NonNull
    private final LinearLayoutManager f1154d;
    private int e;
    private int f;
    private a g;
    private int h;

    /* renamed from: i */
    private int f1155i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;

    /* loaded from: classes.dex */
    public static final class a {
        int a;
        float b;

        /* renamed from: c */
        int f1156c;

        a() {
        }

        void a() {
            this.a = -1;
            this.b = 0.0f;
            this.f1156c = 0;
        }
    }

    public e(@NonNull ViewPager2 viewPager2) {
        this.b = viewPager2;
        RecyclerView recyclerView = viewPager2.j;
        this.f1153c = recyclerView;
        this.f1154d = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.g = new a();
        o();
    }

    private void a(int i2, float f, int i3) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.a;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageScrolled(i2, f, i3);
        }
    }

    private void b(int i2) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.a;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageSelected(i2);
        }
    }

    private void c(int i2) {
        if ((this.e == 3 && this.f == 0) || this.f == i2) {
            return;
        }
        this.f = i2;
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.a;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageScrollStateChanged(i2);
        }
    }

    private int d() {
        return this.f1154d.findFirstVisibleItemPosition();
    }

    private boolean j() {
        int i2 = this.e;
        return i2 == 1 || i2 == 4;
    }

    private void o() {
        this.e = 0;
        this.f = 0;
        this.g.a();
        this.h = -1;
        this.f1155i = -1;
        this.j = false;
        this.k = false;
        this.m = false;
        this.l = false;
    }

    private void q(boolean z) {
        this.m = z;
        this.e = z ? 4 : 1;
        int i2 = this.f1155i;
        if (i2 != -1) {
            this.h = i2;
            this.f1155i = -1;
        } else if (this.h == -1) {
            this.h = d();
        }
        c(1);
    }

    private void r() {
        int top;
        a aVar = this.g;
        int findFirstVisibleItemPosition = this.f1154d.findFirstVisibleItemPosition();
        aVar.a = findFirstVisibleItemPosition;
        if (findFirstVisibleItemPosition == -1) {
            aVar.a();
            return;
        }
        View findViewByPosition = this.f1154d.findViewByPosition(findFirstVisibleItemPosition);
        if (findViewByPosition == null) {
            aVar.a();
            return;
        }
        int leftDecorationWidth = this.f1154d.getLeftDecorationWidth(findViewByPosition);
        int rightDecorationWidth = this.f1154d.getRightDecorationWidth(findViewByPosition);
        int topDecorationHeight = this.f1154d.getTopDecorationHeight(findViewByPosition);
        int bottomDecorationHeight = this.f1154d.getBottomDecorationHeight(findViewByPosition);
        ViewGroup.LayoutParams layoutParams = findViewByPosition.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            leftDecorationWidth += marginLayoutParams.leftMargin;
            rightDecorationWidth += marginLayoutParams.rightMargin;
            topDecorationHeight += marginLayoutParams.topMargin;
            bottomDecorationHeight += marginLayoutParams.bottomMargin;
        }
        int height = findViewByPosition.getHeight() + topDecorationHeight + bottomDecorationHeight;
        int width = findViewByPosition.getWidth() + leftDecorationWidth + rightDecorationWidth;
        if (this.f1154d.getOrientation() == 0) {
            top = (findViewByPosition.getLeft() - leftDecorationWidth) - this.f1153c.getPaddingLeft();
            if (this.b.c()) {
                top = -top;
            }
            height = width;
        } else {
            top = (findViewByPosition.getTop() - topDecorationHeight) - this.f1153c.getPaddingTop();
        }
        int i2 = -top;
        aVar.f1156c = i2;
        if (i2 >= 0) {
            aVar.b = height == 0 ? 0.0f : i2 / height;
        } else {
            if (!new androidx.viewpager2.widget.a(this.f1154d).d()) {
                throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", Integer.valueOf(aVar.f1156c)));
            }
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        }
    }

    public double e() {
        r();
        a aVar = this.g;
        double d2 = aVar.a;
        double d3 = aVar.b;
        Double.isNaN(d2);
        Double.isNaN(d3);
        return d2 + d3;
    }

    public int f() {
        return this.f;
    }

    public boolean g() {
        return this.f == 1;
    }

    public boolean h() {
        return this.m;
    }

    public boolean i() {
        return this.f == 0;
    }

    public void k() {
        this.e = 4;
        q(true);
    }

    public void l() {
        this.l = true;
    }

    public void m() {
        if (!g() || this.m) {
            this.m = false;
            r();
            a aVar = this.g;
            if (aVar.f1156c != 0) {
                c(2);
                return;
            }
            int i2 = aVar.a;
            if (i2 != this.h) {
                b(i2);
            }
            c(0);
            o();
        }
    }

    public void n(int i2, boolean z) {
        this.e = z ? 2 : 3;
        this.m = false;
        boolean z2 = this.f1155i != i2;
        this.f1155i = i2;
        c(2);
        if (z2) {
            b(i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
        boolean z = true;
        if (!(this.e == 1 && this.f == 1) && i2 == 1) {
            q(false);
            return;
        }
        if (j() && i2 == 2) {
            if (this.k) {
                c(2);
                this.j = true;
                return;
            }
            return;
        }
        if (j() && i2 == 0) {
            r();
            if (this.k) {
                a aVar = this.g;
                if (aVar.f1156c == 0) {
                    int i3 = this.h;
                    int i4 = aVar.a;
                    if (i3 != i4) {
                        b(i4);
                    }
                } else {
                    z = false;
                }
            } else {
                int i5 = this.g.a;
                if (i5 != -1) {
                    a(i5, 0.0f, 0);
                }
            }
            if (z) {
                c(0);
                o();
            }
        }
        if (this.e == 2 && i2 == 0 && this.l) {
            r();
            a aVar2 = this.g;
            if (aVar2.f1156c == 0) {
                int i6 = this.f1155i;
                int i7 = aVar2.a;
                if (i6 != i7) {
                    if (i7 == -1) {
                        i7 = 0;
                    }
                    b(i7);
                }
                c(0);
                o();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
    
        if (r3.h != r5) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001d, code lost:
    
        if ((r5 < 0) == r3.b.c()) goto L102;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onScrolled(@androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView r4, int r5, int r6) {
        /*
            r3 = this;
            r4 = 1
            r3.k = r4
            r3.r()
            boolean r0 = r3.j
            r1 = -1
            r2 = 0
            if (r0 == 0) goto L3a
            r3.j = r2
            if (r6 > 0) goto L22
            if (r6 != 0) goto L20
            if (r5 >= 0) goto L16
            r5 = 1
            goto L17
        L16:
            r5 = 0
        L17:
            androidx.viewpager2.widget.ViewPager2 r6 = r3.b
            boolean r6 = r6.c()
            if (r5 != r6) goto L20
            goto L22
        L20:
            r5 = 0
            goto L23
        L22:
            r5 = 1
        L23:
            if (r5 == 0) goto L2f
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r6 = r5.f1156c
            if (r6 == 0) goto L2f
            int r5 = r5.a
            int r5 = r5 + r4
            goto L33
        L2f:
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r5 = r5.a
        L33:
            r3.f1155i = r5
            int r6 = r3.h
            if (r6 == r5) goto L48
            goto L45
        L3a:
            int r5 = r3.e
            if (r5 != 0) goto L48
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r5 = r5.a
            if (r5 != r1) goto L45
            r5 = 0
        L45:
            r3.b(r5)
        L48:
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r6 = r5.a
            if (r6 != r1) goto L4f
            r6 = 0
        L4f:
            float r0 = r5.b
            int r5 = r5.f1156c
            r3.a(r6, r0, r5)
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r6 = r5.a
            int r0 = r3.f1155i
            if (r6 == r0) goto L60
            if (r0 != r1) goto L6e
        L60:
            int r5 = r5.f1156c
            if (r5 != 0) goto L6e
            int r5 = r3.f
            if (r5 == r4) goto L6e
            r3.c(r2)
            r3.o()
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.e.onScrolled(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public void p(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.a = onPageChangeCallback;
    }
}
