package fr.castorflex.android.verticalviewpager;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import androidx.core.os.ParcelableCompat;
import androidx.core.os.ParcelableCompatCreatorCallbacks;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.KeyEventCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.VelocityTrackerCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityRecordCompat;
import androidx.core.widget.EdgeEffectCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes.dex */
public class VerticalViewPager extends ViewGroup {
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final int[] e0 = {R.attr.layout_gravity};
    private static final Comparator<d> f0 = new a();
    private static final Interpolator g0 = new b();
    private static final h h0 = new h();
    private int A;
    private float B;
    private float C;
    private float D;
    private float E;
    private int F;
    private VelocityTracker G;
    private int H;
    private int I;
    private int J;
    private int K;
    private boolean L;
    private long M;
    private EdgeEffectCompat N;
    private EdgeEffectCompat O;
    private boolean P;
    private boolean Q;
    private int R;
    private ViewPager.OnPageChangeListener S;
    private ViewPager.OnPageChangeListener T;
    private f U;
    private ViewPager.PageTransformer V;
    private Method W;
    private int a;
    private int a0;
    private final ArrayList<d> b;
    private ArrayList<View> b0;

    /* renamed from: c, reason: collision with root package name */
    private final d f2051c;
    private final Runnable c0;

    /* renamed from: d, reason: collision with root package name */
    private final Rect f2052d;
    private int d0;
    private PagerAdapter e;
    private int f;
    private int g;
    private Parcelable h;

    /* renamed from: i, reason: collision with root package name */
    private ClassLoader f2053i;
    private Scroller j;
    private g k;
    private int l;
    private Drawable m;
    private int n;
    private int o;
    private float p;
    private float q;
    private int r;
    private boolean s;
    private boolean t;
    private boolean u;
    private int v;
    private boolean w;
    private boolean x;
    private int y;
    private int z;

    /* loaded from: classes.dex */
    public static class LayoutParams extends ViewGroup.LayoutParams {
        public boolean a;
        public int b;

        /* renamed from: c, reason: collision with root package name */
        float f2054c;

        /* renamed from: d, reason: collision with root package name */
        boolean f2055d;
        int e;
        int f;

        public LayoutParams() {
            super(-1, -1);
            this.f2054c = 0.0f;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f2054c = 0.0f;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, VerticalViewPager.e0);
            this.b = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new a());
        int a;
        Parcelable b;

        /* renamed from: c, reason: collision with root package name */
        ClassLoader f2056c;

        /* loaded from: classes.dex */
        static class a implements ParcelableCompatCreatorCallbacks<SavedState> {
            a() {
            }

            @Override // androidx.core.os.ParcelableCompatCreatorCallbacks
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // androidx.core.os.ParcelableCompatCreatorCallbacks
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.a = parcel.readInt();
            this.b = parcel.readParcelable(classLoader);
            this.f2056c = classLoader;
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.a + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.a);
            parcel.writeParcelable(this.b, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a implements Comparator<d> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(d dVar, d dVar2) {
            return dVar.b - dVar2.b;
        }
    }

    /* loaded from: classes.dex */
    static class b implements Interpolator {
        b() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    }

    /* loaded from: classes.dex */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            VerticalViewPager.this.setScrollState(0);
            VerticalViewPager.this.A();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class d {
        Object a;
        int b;

        /* renamed from: c, reason: collision with root package name */
        boolean f2057c;

        /* renamed from: d, reason: collision with root package name */
        float f2058d;
        float e;

        d() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class e extends AccessibilityDelegateCompat {
        e() {
        }

        private boolean e() {
            return VerticalViewPager.this.e != null && VerticalViewPager.this.e.getCount() > 1;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(ViewPager.class.getName());
            AccessibilityRecordCompat obtain = AccessibilityRecordCompat.obtain();
            obtain.setScrollable(e());
            if (accessibilityEvent.getEventType() != 4096 || VerticalViewPager.this.e == null) {
                return;
            }
            obtain.setItemCount(VerticalViewPager.this.e.getCount());
            obtain.setFromIndex(VerticalViewPager.this.f);
            obtain.setToIndex(VerticalViewPager.this.f);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
            accessibilityNodeInfoCompat.setScrollable(e());
            if (VerticalViewPager.this.internalCanScrollVertically(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
            }
            if (VerticalViewPager.this.internalCanScrollVertically(-1)) {
                accessibilityNodeInfoCompat.addAction(8192);
            }
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            VerticalViewPager verticalViewPager;
            int i3;
            if (super.performAccessibilityAction(view, i2, bundle)) {
                return true;
            }
            if (i2 != 4096) {
                if (i2 != 8192 || !VerticalViewPager.this.internalCanScrollVertically(-1)) {
                    return false;
                }
                verticalViewPager = VerticalViewPager.this;
                i3 = verticalViewPager.f - 1;
            } else {
                if (!VerticalViewPager.this.internalCanScrollVertically(1)) {
                    return false;
                }
                verticalViewPager = VerticalViewPager.this;
                i3 = verticalViewPager.f + 1;
            }
            verticalViewPager.setCurrentItem(i3);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface f {
        void a(PagerAdapter pagerAdapter, PagerAdapter pagerAdapter2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class g extends DataSetObserver {
        private g() {
        }

        /* synthetic */ g(VerticalViewPager verticalViewPager, a aVar) {
            this();
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            VerticalViewPager.this.i();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            VerticalViewPager.this.i();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class h implements Comparator<View> {
        h() {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(View view, View view2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
            boolean z = layoutParams.a;
            return z != layoutParams2.a ? z ? 1 : -1 : layoutParams.e - layoutParams2.e;
        }
    }

    public VerticalViewPager(Context context) {
        super(context);
        this.b = new ArrayList<>();
        this.f2051c = new d();
        this.f2052d = new Rect();
        this.g = -1;
        this.h = null;
        this.f2053i = null;
        this.p = -3.4028235E38f;
        this.q = Float.MAX_VALUE;
        this.v = 1;
        this.F = -1;
        this.P = true;
        this.c0 = new c();
        this.d0 = 0;
        s();
    }

    public VerticalViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new ArrayList<>();
        this.f2051c = new d();
        this.f2052d = new Rect();
        this.g = -1;
        this.h = null;
        this.f2053i = null;
        this.p = -3.4028235E38f;
        this.q = Float.MAX_VALUE;
        this.v = 1;
        this.F = -1;
        this.P = true;
        this.c0 = new c();
        this.d0 = 0;
        s();
    }

    private void C(int i2, int i3, int i4, int i5) {
        if (i3 <= 0 || this.b.isEmpty()) {
            d r = r(this.f);
            int min = (int) ((r != null ? Math.min(r.e, this.q) : 0.0f) * ((i2 - getPaddingTop()) - getPaddingBottom()));
            if (min != getScrollY()) {
                h(false);
                scrollTo(getScrollX(), min);
                return;
            }
            return;
        }
        int scrollY = (int) ((getScrollY() / (((i3 - getPaddingTop()) - getPaddingBottom()) + i5)) * (((i2 - getPaddingTop()) - getPaddingBottom()) + i4));
        scrollTo(getScrollX(), scrollY);
        if (this.j.isFinished()) {
            return;
        }
        this.j.startScroll(0, scrollY, 0, (int) (r(this.f).e * i2), this.j.getDuration() - this.j.timePassed());
    }

    private void D() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((LayoutParams) getChildAt(i2).getLayoutParams()).a) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    private void E(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private void F(int i2, boolean z, int i3, boolean z2) {
        ViewPager.OnPageChangeListener onPageChangeListener;
        ViewPager.OnPageChangeListener onPageChangeListener2;
        ViewPager.OnPageChangeListener onPageChangeListener3;
        ViewPager.OnPageChangeListener onPageChangeListener4;
        d r = r(i2);
        int clientHeight = r != null ? (int) (getClientHeight() * Math.max(this.p, Math.min(r.e, this.q))) : 0;
        if (z) {
            I(0, clientHeight, i3);
            if (z2 && (onPageChangeListener4 = this.S) != null) {
                onPageChangeListener4.onPageSelected(i2);
            }
            if (!z2 || (onPageChangeListener3 = this.T) == null) {
                return;
            }
            onPageChangeListener3.onPageSelected(i2);
            return;
        }
        if (z2 && (onPageChangeListener2 = this.S) != null) {
            onPageChangeListener2.onPageSelected(i2);
        }
        if (z2 && (onPageChangeListener = this.T) != null) {
            onPageChangeListener.onPageSelected(i2);
        }
        h(false);
        scrollTo(0, clientHeight);
        x(clientHeight);
    }

    private void J() {
        if (this.a0 != 0) {
            ArrayList<View> arrayList = this.b0;
            if (arrayList == null) {
                this.b0 = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.b0.add(getChildAt(i2));
            }
            Collections.sort(this.b0, h0);
        }
    }

    private void f(d dVar, int i2, d dVar2) {
        int i3;
        int i4;
        d dVar3;
        d dVar4;
        int count = this.e.getCount();
        int clientHeight = getClientHeight();
        float f2 = clientHeight > 0 ? this.l / clientHeight : 0.0f;
        if (dVar2 != null) {
            int i5 = dVar2.b;
            int i6 = dVar.b;
            if (i5 < i6) {
                int i7 = 0;
                float f3 = dVar2.e + dVar2.f2058d + f2;
                while (true) {
                    i5++;
                    if (i5 > dVar.b || i7 >= this.b.size()) {
                        break;
                    }
                    while (true) {
                        dVar4 = this.b.get(i7);
                        if (i5 <= dVar4.b || i7 >= this.b.size() - 1) {
                            break;
                        } else {
                            i7++;
                        }
                    }
                    while (i5 < dVar4.b) {
                        f3 += this.e.getPageWidth(i5) + f2;
                        i5++;
                    }
                    dVar4.e = f3;
                    f3 += dVar4.f2058d + f2;
                }
            } else if (i5 > i6) {
                int size = this.b.size() - 1;
                float f4 = dVar2.e;
                while (true) {
                    i5--;
                    if (i5 < dVar.b || size < 0) {
                        break;
                    }
                    while (true) {
                        dVar3 = this.b.get(size);
                        if (i5 >= dVar3.b || size <= 0) {
                            break;
                        } else {
                            size--;
                        }
                    }
                    while (i5 > dVar3.b) {
                        f4 -= this.e.getPageWidth(i5) + f2;
                        i5--;
                    }
                    f4 -= dVar3.f2058d + f2;
                    dVar3.e = f4;
                }
            }
        }
        int size2 = this.b.size();
        float f5 = dVar.e;
        int i8 = dVar.b;
        int i9 = i8 - 1;
        this.p = i8 == 0 ? f5 : -3.4028235E38f;
        int i10 = count - 1;
        this.q = i8 == i10 ? (dVar.f2058d + f5) - 1.0f : Float.MAX_VALUE;
        int i11 = i2 - 1;
        while (i11 >= 0) {
            d dVar5 = this.b.get(i11);
            while (true) {
                i4 = dVar5.b;
                if (i9 <= i4) {
                    break;
                }
                f5 -= this.e.getPageWidth(i9) + f2;
                i9--;
            }
            f5 -= dVar5.f2058d + f2;
            dVar5.e = f5;
            if (i4 == 0) {
                this.p = f5;
            }
            i11--;
            i9--;
        }
        float f6 = dVar.e + dVar.f2058d + f2;
        int i12 = dVar.b + 1;
        int i13 = i2 + 1;
        while (i13 < size2) {
            d dVar6 = this.b.get(i13);
            while (true) {
                i3 = dVar6.b;
                if (i12 >= i3) {
                    break;
                }
                f6 += this.e.getPageWidth(i12) + f2;
                i12++;
            }
            if (i3 == i10) {
                this.q = (dVar6.f2058d + f6) - 1.0f;
            }
            dVar6.e = f6;
            f6 += dVar6.f2058d + f2;
            i13++;
            i12++;
        }
    }

    private int getClientHeight() {
        return (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private void h(boolean z) {
        boolean z2 = this.d0 == 2;
        if (z2) {
            setScrollingCacheEnabled(false);
            this.j.abortAnimation();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.j.getCurrX();
            int currY = this.j.getCurrY();
            if (scrollX != currX || scrollY != currY) {
                scrollTo(currX, currY);
            }
        }
        this.u = false;
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            d dVar = this.b.get(i2);
            if (dVar.f2057c) {
                dVar.f2057c = false;
                z2 = true;
            }
        }
        if (z2) {
            if (z) {
                ViewCompat.postOnAnimation(this, this.c0);
            } else {
                this.c0.run();
            }
        }
    }

    private int j(int i2, float f2, int i3, int i4) {
        if (Math.abs(i4) <= this.J || Math.abs(i3) <= this.H) {
            i2 = (int) (i2 + f2 + (i2 >= this.f ? 0.4f : 0.6f));
        } else if (i3 <= 0) {
            i2++;
        }
        if (this.b.size() <= 0) {
            return i2;
        }
        return Math.max(this.b.get(0).b, Math.min(i2, this.b.get(r4.size() - 1).b));
    }

    private void l(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ViewCompat.setLayerType(getChildAt(i2), z ? 2 : 0, null);
        }
    }

    private void m() {
        this.w = false;
        this.x = false;
        VelocityTracker velocityTracker = this.G;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.G = null;
        }
    }

    private Rect n(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left += viewGroup.getLeft();
            rect.right += viewGroup.getRight();
            rect.top += viewGroup.getTop();
            rect.bottom += viewGroup.getBottom();
            parent = viewGroup.getParent();
        }
        return rect;
    }

    private d q() {
        int i2;
        int clientHeight = getClientHeight();
        float f2 = 0.0f;
        float scrollY = clientHeight > 0 ? getScrollY() / clientHeight : 0.0f;
        float f3 = clientHeight > 0 ? this.l / clientHeight : 0.0f;
        d dVar = null;
        float f4 = 0.0f;
        int i3 = -1;
        int i4 = 0;
        boolean z = true;
        while (i4 < this.b.size()) {
            d dVar2 = this.b.get(i4);
            if (!z && dVar2.b != (i2 = i3 + 1)) {
                dVar2 = this.f2051c;
                dVar2.e = f2 + f4 + f3;
                dVar2.b = i2;
                dVar2.f2058d = this.e.getPageWidth(i2);
                i4--;
            }
            f2 = dVar2.e;
            float f5 = dVar2.f2058d + f2 + f3;
            if (!z && scrollY < f2) {
                return dVar;
            }
            if (scrollY < f5 || i4 == this.b.size() - 1) {
                return dVar2;
            }
            i3 = dVar2.b;
            f4 = dVar2.f2058d;
            i4++;
            dVar = dVar2;
            z = false;
        }
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setScrollState(int i2) {
        if (this.d0 == i2) {
            return;
        }
        this.d0 = i2;
        if (this.V != null) {
            l(i2 != 0);
        }
        ViewPager.OnPageChangeListener onPageChangeListener = this.S;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i2);
        }
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.t != z) {
            this.t = z;
        }
    }

    private boolean t(float f2, float f3) {
        return (f2 < ((float) this.z) && f3 > 0.0f) || (f2 > ((float) (getHeight() - this.z)) && f3 < 0.0f);
    }

    private void v(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.F) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.C = MotionEventCompat.getY(motionEvent, i2);
            this.F = MotionEventCompat.getPointerId(motionEvent, i2);
            VelocityTracker velocityTracker = this.G;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private boolean x(int i2) {
        if (this.b.size() == 0) {
            this.Q = false;
            u(0, 0.0f, 0);
            if (this.Q) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        d q = q();
        int clientHeight = getClientHeight();
        int i3 = this.l;
        int i4 = clientHeight + i3;
        float f2 = clientHeight;
        int i5 = q.b;
        float f3 = ((i2 / f2) - q.e) / (q.f2058d + (i3 / f2));
        this.Q = false;
        u(i5, f3, (int) (i4 * f3));
        if (this.Q) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    private boolean z(float f2) {
        boolean z;
        float f3 = this.C - f2;
        this.C = f2;
        float scrollY = getScrollY() + f3;
        float clientHeight = getClientHeight();
        float f4 = this.p * clientHeight;
        float f5 = this.q * clientHeight;
        d dVar = this.b.get(0);
        ArrayList<d> arrayList = this.b;
        boolean z2 = true;
        d dVar2 = arrayList.get(arrayList.size() - 1);
        if (dVar.b != 0) {
            f4 = dVar.e * clientHeight;
            z = false;
        } else {
            z = true;
        }
        if (dVar2.b != this.e.getCount() - 1) {
            f5 = dVar2.e * clientHeight;
            z2 = false;
        }
        if (scrollY < f4) {
            r4 = z ? this.N.onPull(Math.abs(f4 - scrollY) / clientHeight) : false;
            scrollY = f4;
        } else if (scrollY > f5) {
            r4 = z2 ? this.O.onPull(Math.abs(scrollY - f5) / clientHeight) : false;
            scrollY = f5;
        }
        int i2 = (int) scrollY;
        this.B += scrollY - i2;
        scrollTo(getScrollX(), i2);
        x(i2);
        return r4;
    }

    void A() {
        B(this.f);
    }

    /* JADX WARN: Code restructure failed: missing block: B:112:0x00d5, code lost:
    
        if (r11 >= 0) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x00e3, code lost:
    
        if (r11 >= 0) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0068, code lost:
    
        if (r10 == r11) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00c7, code lost:
    
        if (r11 >= 0) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ee, code lost:
    
        r10 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00e5, code lost:
    
        r10 = r17.b.get(r11);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void B(int r18) {
        /*
            Method dump skipped, instructions count: 597
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: fr.castorflex.android.verticalviewpager.VerticalViewPager.B(int):void");
    }

    void G(int i2, boolean z, boolean z2) {
        H(i2, z, z2, 0);
    }

    void H(int i2, boolean z, boolean z2, int i3) {
        ViewPager.OnPageChangeListener onPageChangeListener;
        ViewPager.OnPageChangeListener onPageChangeListener2;
        PagerAdapter pagerAdapter = this.e;
        if (pagerAdapter == null || pagerAdapter.getCount() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!z2 && this.f == i2 && this.b.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= this.e.getCount()) {
            i2 = this.e.getCount() - 1;
        }
        int i4 = this.v;
        int i5 = this.f;
        if (i2 > i5 + i4 || i2 < i5 - i4) {
            for (int i6 = 0; i6 < this.b.size(); i6++) {
                this.b.get(i6).f2057c = true;
            }
        }
        boolean z3 = this.f != i2;
        if (!this.P) {
            B(i2);
            F(i2, z, i3, z3);
            return;
        }
        this.f = i2;
        if (z3 && (onPageChangeListener2 = this.S) != null) {
            onPageChangeListener2.onPageSelected(i2);
        }
        if (z3 && (onPageChangeListener = this.T) != null) {
            onPageChangeListener.onPageSelected(i2);
        }
        requestLayout();
    }

    void I(int i2, int i3, int i4) {
        int abs;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i5 = i2 - scrollX;
        int i6 = i3 - scrollY;
        if (i5 == 0 && i6 == 0) {
            h(false);
            A();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientHeight = getClientHeight();
        int i7 = clientHeight / 2;
        float f2 = clientHeight;
        float f3 = i7;
        float k = f3 + (k(Math.min(1.0f, (Math.abs(i5) * 1.0f) / f2)) * f3);
        int abs2 = Math.abs(i4);
        if (abs2 > 0) {
            abs = Math.round(Math.abs(k / abs2) * 1000.0f) * 4;
        } else {
            abs = (int) (((Math.abs(i5) / ((f2 * this.e.getPageWidth(this.f)) + this.l)) + 1.0f) * 100.0f);
        }
        this.j.startScroll(scrollX, scrollY, i5, i6, Math.min(abs, 600));
        ViewCompat.postInvalidateOnAnimation(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        d p;
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i4 = 0; i4 < getChildCount(); i4++) {
                View childAt = getChildAt(i4);
                if (childAt.getVisibility() == 0 && (p = p(childAt)) != null && p.b == this.f) {
                    childAt.addFocusables(arrayList, i2, i3);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if (((i3 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) || arrayList == null) {
                return;
            }
            arrayList.add(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        d p;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (p = p(childAt)) != null && p.b == this.f) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        boolean z = layoutParams2.a | false;
        layoutParams2.a = z;
        if (!this.s) {
            super.addView(view, i2, layoutParams);
        } else {
            if (layoutParams2 != null && z) {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
            layoutParams2.f2055d = true;
            addViewInLayout(view, i2, layoutParams);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean arrowScroll(int r7) {
        /*
            r6 = this;
            android.view.View r0 = r6.findFocus()
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 != r6) goto Lb
        L9:
            r0 = r3
            goto L69
        Lb:
            if (r0 == 0) goto L69
            android.view.ViewParent r4 = r0.getParent()
        L11:
            boolean r5 = r4 instanceof android.view.ViewGroup
            if (r5 == 0) goto L1e
            if (r4 != r6) goto L19
            r4 = 1
            goto L1f
        L19:
            android.view.ViewParent r4 = r4.getParent()
            goto L11
        L1e:
            r4 = 0
        L1f:
            if (r4 != 0) goto L69
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.Class r5 = r0.getClass()
            java.lang.String r5 = r5.getSimpleName()
            r4.append(r5)
            android.view.ViewParent r0 = r0.getParent()
        L35:
            boolean r5 = r0 instanceof android.view.ViewGroup
            if (r5 == 0) goto L4e
            java.lang.String r5 = " => "
            r4.append(r5)
            java.lang.Class r5 = r0.getClass()
            java.lang.String r5 = r5.getSimpleName()
            r4.append(r5)
            android.view.ViewParent r0 = r0.getParent()
            goto L35
        L4e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "arrowScroll tried to find focus based on non-child current focused view "
            r0.append(r5)
            java.lang.String r4 = r4.toString()
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.String r4 = "ViewPager"
            android.util.Log.e(r4, r0)
            goto L9
        L69:
            android.view.FocusFinder r3 = android.view.FocusFinder.getInstance()
            android.view.View r3 = r3.findNextFocus(r6, r0, r7)
            r4 = 130(0x82, float:1.82E-43)
            r5 = 33
            if (r3 == 0) goto Lb5
            if (r3 == r0) goto Lb5
            if (r7 != r5) goto L9a
            android.graphics.Rect r1 = r6.f2052d
            android.graphics.Rect r1 = r6.n(r1, r3)
            int r1 = r1.top
            android.graphics.Rect r2 = r6.f2052d
            android.graphics.Rect r2 = r6.n(r2, r0)
            int r2 = r2.top
            if (r0 == 0) goto L94
            if (r1 < r2) goto L94
            boolean r0 = r6.y()
            goto L98
        L94:
            boolean r0 = r3.requestFocus()
        L98:
            r2 = r0
            goto Lc8
        L9a:
            if (r7 != r4) goto Lc8
            android.graphics.Rect r1 = r6.f2052d
            android.graphics.Rect r1 = r6.n(r1, r3)
            int r1 = r1.bottom
            android.graphics.Rect r2 = r6.f2052d
            android.graphics.Rect r2 = r6.n(r2, r0)
            int r2 = r2.bottom
            if (r0 == 0) goto L94
            if (r1 > r2) goto L94
            boolean r0 = r6.w()
            goto L98
        Lb5:
            if (r7 == r5) goto Lc4
            if (r7 != r1) goto Lba
            goto Lc4
        Lba:
            if (r7 == r4) goto Lbf
            r0 = 2
            if (r7 != r0) goto Lc8
        Lbf:
            boolean r2 = r6.w()
            goto Lc8
        Lc4:
            boolean r2 = r6.y()
        Lc8:
            if (r2 == 0) goto Ld1
            int r7 = android.view.SoundEffectConstants.getContantForFocusDirection(r7)
            r6.playSoundEffect(r7)
        Ld1:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: fr.castorflex.android.verticalviewpager.VerticalViewPager.arrowScroll(int):boolean");
    }

    public boolean beginFakeDrag() {
        if (this.w) {
            return false;
        }
        this.L = true;
        setScrollState(1);
        this.C = 0.0f;
        this.E = 0.0f;
        VelocityTracker velocityTracker = this.G;
        if (velocityTracker == null) {
            this.G = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, 0.0f, 0.0f, 0);
        this.G.addMovement(obtain);
        obtain.recycle();
        this.M = uptimeMillis;
        return true;
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.j.isFinished() || !this.j.computeScrollOffset()) {
            h(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.j.getCurrX();
        int currY = this.j.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!x(currY)) {
                this.j.abortAnimation();
                scrollTo(currX, 0);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        d p;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (p = p(childAt)) != null && p.b == this.f && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        PagerAdapter pagerAdapter;
        super.draw(canvas);
        int overScrollMode = ViewCompat.getOverScrollMode(this);
        boolean z = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (pagerAdapter = this.e) != null && pagerAdapter.getCount() > 1)) {
            if (!this.N.isFinished()) {
                int save = canvas.save();
                int height = getHeight();
                int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.translate(getPaddingLeft(), this.p * height);
                this.N.setSize(width, height);
                z = false | this.N.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.O.isFinished()) {
                int save2 = canvas.save();
                int height2 = getHeight();
                int width2 = (getWidth() - getPaddingLeft()) - getPaddingRight();
                canvas.rotate(180.0f);
                canvas.translate((-width2) - getPaddingLeft(), (-(this.q + 1.0f)) * height2);
                this.O.setSize(width2, height2);
                z |= this.O.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.N.finish();
            this.O.finish();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.m;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    d e(int i2, int i3) {
        d dVar = new d();
        dVar.b = i2;
        dVar.a = this.e.instantiateItem((ViewGroup) this, i2);
        dVar.f2058d = this.e.getPageWidth(i2);
        if (i3 < 0 || i3 >= this.b.size()) {
            this.b.add(dVar);
        } else {
            this.b.add(i3, dVar);
        }
        return dVar;
    }

    public void endFakeDrag() {
        if (!this.L) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        VelocityTracker velocityTracker = this.G;
        velocityTracker.computeCurrentVelocity(1000, this.I);
        int yVelocity = (int) VelocityTrackerCompat.getYVelocity(velocityTracker, this.F);
        this.u = true;
        int clientHeight = getClientHeight();
        int scrollY = getScrollY();
        d q = q();
        H(j(q.b, ((scrollY / clientHeight) - q.e) / q.f2058d, yVelocity, (int) (this.C - this.E)), true, true, yVelocity);
        m();
        this.L = false;
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        int i2;
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 21) {
                i2 = 17;
            } else if (keyCode == 22) {
                i2 = 66;
            } else if (keyCode == 61 && Build.VERSION.SDK_INT >= 11) {
                if (KeyEventCompat.hasNoModifiers(keyEvent)) {
                    i2 = 2;
                } else if (KeyEventCompat.hasModifiers(keyEvent, 1)) {
                    return arrowScroll(1);
                }
            }
            return arrowScroll(i2);
        }
        return false;
    }

    public void fakeDragBy(float f2) {
        if (!this.L) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        this.C += f2;
        float scrollY = getScrollY() - f2;
        float clientHeight = getClientHeight();
        float f3 = this.p * clientHeight;
        float f4 = this.q * clientHeight;
        d dVar = this.b.get(0);
        d dVar2 = this.b.get(r4.size() - 1);
        if (dVar.b != 0) {
            f3 = dVar.e * clientHeight;
        }
        if (dVar2.b != this.e.getCount() - 1) {
            f4 = dVar2.e * clientHeight;
        }
        if (scrollY < f3) {
            scrollY = f3;
        } else if (scrollY > f4) {
            scrollY = f4;
        }
        int i2 = (int) scrollY;
        this.C += scrollY - i2;
        scrollTo(getScrollX(), i2);
        x(i2);
        MotionEvent obtain = MotionEvent.obtain(this.M, SystemClock.uptimeMillis(), 2, 0.0f, this.C, 0);
        this.G.addMovement(obtain);
        obtain.recycle();
    }

    protected boolean g(View view, boolean z, int i2, int i3, int i4) {
        int i5;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i6 = i4 + scrollY;
                if (i6 >= childAt.getTop() && i6 < childAt.getBottom() && (i5 = i3 + scrollX) >= childAt.getLeft() && i5 < childAt.getRight() && g(childAt, true, i2, i5 - childAt.getLeft(), i6 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && ViewCompat.canScrollVertically(view, -i2);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public PagerAdapter getAdapter() {
        return this.e;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        if (this.a0 == 2) {
            i3 = (i2 - 1) - i3;
        }
        return ((LayoutParams) this.b0.get(i3).getLayoutParams()).f;
    }

    public int getCurrentItem() {
        return this.f;
    }

    public int getOffscreenPageLimit() {
        return this.v;
    }

    public int getPageMargin() {
        return this.l;
    }

    void i() {
        int count = this.e.getCount();
        this.a = count;
        boolean z = this.b.size() < (this.v * 2) + 1 && this.b.size() < count;
        int i2 = this.f;
        int i3 = 0;
        boolean z2 = false;
        while (i3 < this.b.size()) {
            d dVar = this.b.get(i3);
            int itemPosition = this.e.getItemPosition(dVar.a);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.b.remove(i3);
                    i3--;
                    if (!z2) {
                        this.e.startUpdate((ViewGroup) this);
                        z2 = true;
                    }
                    this.e.destroyItem((ViewGroup) this, dVar.b, dVar.a);
                    int i4 = this.f;
                    if (i4 == dVar.b) {
                        i2 = Math.max(0, Math.min(i4, count - 1));
                    }
                } else {
                    int i5 = dVar.b;
                    if (i5 != itemPosition) {
                        if (i5 == this.f) {
                            i2 = itemPosition;
                        }
                        dVar.b = itemPosition;
                    }
                }
                z = true;
            }
            i3++;
        }
        if (z2) {
            this.e.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.b, f0);
        if (z) {
            int childCount = getChildCount();
            for (int i6 = 0; i6 < childCount; i6++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i6).getLayoutParams();
                if (!layoutParams.a) {
                    layoutParams.f2054c = 0.0f;
                }
            }
            G(i2, false, true);
            requestLayout();
        }
    }

    public boolean internalCanScrollVertically(int i2) {
        if (this.e == null) {
            return false;
        }
        int clientHeight = getClientHeight();
        int scrollY = getScrollY();
        return i2 < 0 ? scrollY > ((int) (((float) clientHeight) * this.p)) : i2 > 0 && scrollY < ((int) (((float) clientHeight) * this.q));
    }

    public boolean isFakeDragging() {
        return this.L;
    }

    float k(float f2) {
        Double.isNaN(f2 - 0.5f);
        return (float) Math.sin((float) (r0 * 0.4712389167638204d));
    }

    d o(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent == this) {
                return p(view);
            }
            if (parent == null || !(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.P = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.c0);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        float f2;
        float f3;
        super.onDraw(canvas);
        if (this.l <= 0 || this.m == null || this.b.size() <= 0 || this.e == null) {
            return;
        }
        int scrollY = getScrollY();
        float height = getHeight();
        float f4 = this.l / height;
        int i3 = 0;
        d dVar = this.b.get(0);
        float f5 = dVar.e;
        int size = this.b.size();
        int i4 = dVar.b;
        int i5 = this.b.get(size - 1).b;
        while (i4 < i5) {
            while (true) {
                i2 = dVar.b;
                if (i4 <= i2 || i3 >= size) {
                    break;
                }
                i3++;
                dVar = this.b.get(i3);
            }
            if (i4 == i2) {
                float f6 = dVar.e;
                float f7 = dVar.f2058d;
                f2 = (f6 + f7) * height;
                f5 = f6 + f7 + f4;
            } else {
                float pageWidth = this.e.getPageWidth(i4);
                f2 = (f5 + pageWidth) * height;
                f5 += pageWidth + f4;
            }
            int i6 = this.l;
            if (i6 + f2 > scrollY) {
                f3 = f4;
                this.m.setBounds(this.n, (int) f2, this.o, (int) (i6 + f2 + 0.5f));
                this.m.draw(canvas);
            } else {
                f3 = f4;
            }
            if (f2 > scrollY + r2) {
                return;
            }
            i4++;
            f4 = f3;
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.w = false;
            this.x = false;
            this.F = -1;
            VelocityTracker velocityTracker = this.G;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.G = null;
            }
            return false;
        }
        if (action != 0) {
            if (this.w) {
                return true;
            }
            if (this.x) {
                return false;
            }
        }
        if (action == 0) {
            float x = motionEvent.getX();
            this.D = x;
            this.B = x;
            float y = motionEvent.getY();
            this.E = y;
            this.C = y;
            this.F = MotionEventCompat.getPointerId(motionEvent, 0);
            this.x = false;
            this.j.computeScrollOffset();
            if (this.d0 != 2 || Math.abs(this.j.getFinalY() - this.j.getCurrY()) <= this.K) {
                h(false);
                this.w = false;
            } else {
                this.j.abortAnimation();
                this.u = false;
                A();
                this.w = true;
                E(true);
                setScrollState(1);
            }
        } else if (action == 2) {
            int i2 = this.F;
            if (i2 != -1) {
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i2);
                float y2 = MotionEventCompat.getY(motionEvent, findPointerIndex);
                float f2 = y2 - this.C;
                float abs = Math.abs(f2);
                float x2 = MotionEventCompat.getX(motionEvent, findPointerIndex);
                float abs2 = Math.abs(x2 - this.D);
                if (f2 != 0.0f && !t(this.C, f2) && g(this, false, (int) f2, (int) x2, (int) y2)) {
                    this.B = x2;
                    this.C = y2;
                    this.x = true;
                    return false;
                }
                int i3 = this.A;
                if (abs > i3 && abs * 0.5f > abs2) {
                    this.w = true;
                    E(true);
                    setScrollState(1);
                    this.C = f2 > 0.0f ? this.E + this.A : this.E - this.A;
                    this.B = x2;
                    setScrollingCacheEnabled(true);
                } else if (abs2 > i3) {
                    this.x = true;
                }
                if (this.w && z(y2)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            }
        } else if (action == 6) {
            v(motionEvent);
        }
        if (this.G == null) {
            this.G = VelocityTracker.obtain();
        }
        this.G.addMovement(motionEvent);
        return this.w;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008e  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: fr.castorflex.android.verticalviewpager.VerticalViewPager.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0089  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r14, int r15) {
        /*
            Method dump skipped, instructions count: 241
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: fr.castorflex.android.verticalviewpager.VerticalViewPager.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i3;
        int i4;
        d p;
        int childCount = getChildCount();
        int i5 = -1;
        if ((i2 & 2) != 0) {
            i5 = childCount;
            i3 = 0;
            i4 = 1;
        } else {
            i3 = childCount - 1;
            i4 = -1;
        }
        while (i3 != i5) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() == 0 && (p = p(childAt)) != null && p.b == this.f && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i3 += i4;
        }
        return false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        PagerAdapter pagerAdapter = this.e;
        if (pagerAdapter != null) {
            pagerAdapter.restoreState(savedState.b, savedState.f2056c);
            G(savedState.a, false, true);
        } else {
            this.g = savedState.a;
            this.h = savedState.b;
            this.f2053i = savedState.f2056c;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.f;
        PagerAdapter pagerAdapter = this.e;
        if (pagerAdapter != null) {
            savedState.b = pagerAdapter.saveState();
        }
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i3 != i5) {
            int i6 = this.l;
            C(i3, i5, i6, i6);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0157  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instructions count: 348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: fr.castorflex.android.verticalviewpager.VerticalViewPager.onTouchEvent(android.view.MotionEvent):boolean");
    }

    d p(View view) {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            d dVar = this.b.get(i2);
            if (this.e.isViewFromObject(view, dVar.a)) {
                return dVar;
            }
        }
        return null;
    }

    d r(int i2) {
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            d dVar = this.b.get(i3);
            if (dVar.b == i2) {
                return dVar;
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.s) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    void s() {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.j = new Scroller(context, g0);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.A = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.H = (int) (400.0f * f2);
        this.I = viewConfiguration.getScaledMaximumFlingVelocity();
        this.N = new EdgeEffectCompat(context);
        this.O = new EdgeEffectCompat(context);
        this.J = (int) (25.0f * f2);
        this.K = (int) (2.0f * f2);
        this.y = (int) (f2 * 16.0f);
        ViewCompat.setAccessibilityDelegate(this, new e());
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        PagerAdapter pagerAdapter2 = this.e;
        if (pagerAdapter2 != null) {
            pagerAdapter2.unregisterDataSetObserver(this.k);
            this.e.startUpdate((ViewGroup) this);
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                d dVar = this.b.get(i2);
                this.e.destroyItem((ViewGroup) this, dVar.b, dVar.a);
            }
            this.e.finishUpdate((ViewGroup) this);
            this.b.clear();
            D();
            this.f = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter3 = this.e;
        this.e = pagerAdapter;
        this.a = 0;
        if (pagerAdapter != null) {
            a aVar = null;
            if (this.k == null) {
                this.k = new g(this, aVar);
            }
            this.e.registerDataSetObserver(this.k);
            this.u = false;
            boolean z = this.P;
            this.P = true;
            this.a = this.e.getCount();
            if (this.g >= 0) {
                this.e.restoreState(this.h, this.f2053i);
                G(this.g, false, true);
                this.g = -1;
                this.h = null;
                this.f2053i = null;
            } else if (z) {
                requestLayout();
            } else {
                A();
            }
        }
        f fVar = this.U;
        if (fVar == null || pagerAdapter3 == pagerAdapter) {
            return;
        }
        fVar.a(pagerAdapter3, pagerAdapter);
    }

    void setChildrenDrawingOrderEnabledCompat(boolean z) {
        if (Build.VERSION.SDK_INT >= 7) {
            if (this.W == null) {
                try {
                    this.W = ViewGroup.class.getDeclaredMethod("setChildrenDrawingOrderEnabled", Boolean.TYPE);
                } catch (NoSuchMethodException e2) {
                    Log.e("ViewPager", "Can't find setChildrenDrawingOrderEnabled", e2);
                }
            }
            try {
                this.W.invoke(this, Boolean.valueOf(z));
            } catch (Exception e3) {
                Log.e("ViewPager", "Error changing children drawing order", e3);
            }
        }
    }

    public void setCurrentItem(int i2) {
        this.u = false;
        G(i2, !this.P, false);
    }

    public void setCurrentItem(int i2, boolean z) {
        this.u = false;
        G(i2, z, false);
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i2 + " too small; defaulting to 1");
            i2 = 1;
        }
        if (i2 != this.v) {
            this.v = i2;
            A();
        }
    }

    void setOnAdapterChangeListener(f fVar) {
        this.U = fVar;
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.S = onPageChangeListener;
    }

    public void setPageMargin(int i2) {
        int i3 = this.l;
        this.l = i2;
        int height = getHeight();
        C(height, height, i2, i3);
        requestLayout();
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(getContext().getResources().getDrawable(i2));
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.m = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageTransformer(boolean z, ViewPager.PageTransformer pageTransformer) {
        if (Build.VERSION.SDK_INT >= 11) {
            boolean z2 = pageTransformer != null;
            boolean z3 = z2 != (this.V != null);
            this.V = pageTransformer;
            setChildrenDrawingOrderEnabledCompat(z2);
            if (z2) {
                this.a0 = z ? 2 : 1;
            } else {
                this.a0 = 0;
            }
            if (z3) {
                A();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0067  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void u(int r12, float r13, int r14) {
        /*
            r11 = this;
            int r0 = r11.R
            r1 = 0
            if (r0 <= 0) goto L6e
            int r0 = r11.getScrollY()
            int r2 = r11.getPaddingTop()
            int r3 = r11.getPaddingBottom()
            int r4 = r11.getHeight()
            int r5 = r11.getChildCount()
            r6 = 0
        L1a:
            if (r6 >= r5) goto L6e
            android.view.View r7 = r11.getChildAt(r6)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            fr.castorflex.android.verticalviewpager.VerticalViewPager$LayoutParams r8 = (fr.castorflex.android.verticalviewpager.VerticalViewPager.LayoutParams) r8
            boolean r9 = r8.a
            if (r9 != 0) goto L2b
            goto L6b
        L2b:
            int r8 = r8.b
            r8 = r8 & 112(0x70, float:1.57E-43)
            r9 = 16
            if (r8 == r9) goto L50
            r9 = 48
            if (r8 == r9) goto L4a
            r9 = 80
            if (r8 == r9) goto L3d
            r8 = r2
            goto L5f
        L3d:
            int r8 = r4 - r3
            int r9 = r7.getMeasuredHeight()
            int r8 = r8 - r9
            int r9 = r7.getMeasuredHeight()
            int r3 = r3 + r9
            goto L5c
        L4a:
            int r8 = r7.getHeight()
            int r8 = r8 + r2
            goto L5f
        L50:
            int r8 = r7.getMeasuredHeight()
            int r8 = r4 - r8
            int r8 = r8 / 2
            int r8 = java.lang.Math.max(r8, r2)
        L5c:
            r10 = r8
            r8 = r2
            r2 = r10
        L5f:
            int r2 = r2 + r0
            int r9 = r7.getTop()
            int r2 = r2 - r9
            if (r2 == 0) goto L6a
            r7.offsetTopAndBottom(r2)
        L6a:
            r2 = r8
        L6b:
            int r6 = r6 + 1
            goto L1a
        L6e:
            androidx.viewpager.widget.ViewPager$OnPageChangeListener r0 = r11.S
            if (r0 == 0) goto L75
            r0.onPageScrolled(r12, r13, r14)
        L75:
            androidx.viewpager.widget.ViewPager$OnPageChangeListener r0 = r11.T
            if (r0 == 0) goto L7c
            r0.onPageScrolled(r12, r13, r14)
        L7c:
            androidx.viewpager.widget.ViewPager$PageTransformer r12 = r11.V
            if (r12 == 0) goto Lad
            int r12 = r11.getScrollY()
            int r13 = r11.getChildCount()
        L88:
            if (r1 >= r13) goto Lad
            android.view.View r14 = r11.getChildAt(r1)
            android.view.ViewGroup$LayoutParams r0 = r14.getLayoutParams()
            fr.castorflex.android.verticalviewpager.VerticalViewPager$LayoutParams r0 = (fr.castorflex.android.verticalviewpager.VerticalViewPager.LayoutParams) r0
            boolean r0 = r0.a
            if (r0 == 0) goto L99
            goto Laa
        L99:
            int r0 = r14.getTop()
            int r0 = r0 - r12
            float r0 = (float) r0
            int r2 = r11.getClientHeight()
            float r2 = (float) r2
            float r0 = r0 / r2
            androidx.viewpager.widget.ViewPager$PageTransformer r2 = r11.V
            r2.transformPage(r14, r0)
        Laa:
            int r1 = r1 + 1
            goto L88
        Lad:
            r12 = 1
            r11.Q = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: fr.castorflex.android.verticalviewpager.VerticalViewPager.u(int, float, int):void");
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.m;
    }

    boolean w() {
        PagerAdapter pagerAdapter = this.e;
        if (pagerAdapter == null || this.f >= pagerAdapter.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.f + 1, true);
        return true;
    }

    boolean y() {
        int i2 = this.f;
        if (i2 <= 0) {
            return false;
        }
        setCurrentItem(i2 - 1, true);
        return true;
    }
}
