package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.ActivityChooserView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    static final boolean DEBUG = false;

    @Deprecated
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    private static final String TAG = "StaggeredGridLManager";
    public static final int VERTICAL = 1;
    private int mFullSizeSpec;
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;

    @NonNull
    private final f mLayoutState;
    private int mOrientation;
    private SavedState mPendingSavedState;
    private int[] mPrefetchDistances;

    @NonNull
    OrientationHelper mPrimaryOrientation;
    private BitSet mRemainingSpans;

    @NonNull
    OrientationHelper mSecondaryOrientation;
    private int mSizePerSpan;
    c[] mSpans;
    private int mSpanCount = -1;
    boolean mReverseLayout = false;
    boolean mShouldReverseLayout = false;
    int mPendingScrollPosition = -1;
    int mPendingScrollPositionOffset = Integer.MIN_VALUE;
    LazySpanLookup mLazySpanLookup = new LazySpanLookup();
    private int mGapStrategy = 2;
    private final Rect mTmpRect = new Rect();
    private final b mAnchorInfo = new b();
    private boolean mLaidOutInvalidFullSpan = false;
    private boolean mSmoothScrollbarEnabled = true;
    private final Runnable mCheckForGapsRunnable = new a();

    /* loaded from: classes.dex */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        public static final int INVALID_SPAN_ID = -1;
        c e;
        boolean f;

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public final int getSpanIndex() {
            c cVar = this.e;
            if (cVar == null) {
                return -1;
            }
            return cVar.e;
        }

        public boolean isFullSpan() {
            return this.f;
        }

        public void setFullSpan(boolean z) {
            this.f = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class LazySpanLookup {
        int[] a;
        List<FullSpanItem> b;

        /* JADX INFO: Access modifiers changed from: package-private */
        @SuppressLint({"BanParcelableUsage"})
        /* loaded from: classes.dex */
        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new a();
            int a;
            int b;

            /* renamed from: c, reason: collision with root package name */
            int[] f982c;

            /* renamed from: d, reason: collision with root package name */
            boolean f983d;

            /* loaded from: classes.dex */
            static class a implements Parcelable.Creator<FullSpanItem> {
                a() {
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public FullSpanItem[] newArray(int i2) {
                    return new FullSpanItem[i2];
                }
            }

            FullSpanItem() {
            }

            FullSpanItem(Parcel parcel) {
                this.a = parcel.readInt();
                this.b = parcel.readInt();
                this.f983d = parcel.readInt() == 1;
                int readInt = parcel.readInt();
                if (readInt > 0) {
                    int[] iArr = new int[readInt];
                    this.f982c = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            int a(int i2) {
                int[] iArr = this.f982c;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i2];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.a + ", mGapDir=" + this.b + ", mHasUnwantedGapAfter=" + this.f983d + ", mGapPerSpan=" + Arrays.toString(this.f982c) + '}';
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i2) {
                parcel.writeInt(this.a);
                parcel.writeInt(this.b);
                parcel.writeInt(this.f983d ? 1 : 0);
                int[] iArr = this.f982c;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.f982c);
                }
            }
        }

        LazySpanLookup() {
        }

        private int i(int i2) {
            if (this.b == null) {
                return -1;
            }
            FullSpanItem f = f(i2);
            if (f != null) {
                this.b.remove(f);
            }
            int size = this.b.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    i3 = -1;
                    break;
                }
                if (this.b.get(i3).a >= i2) {
                    break;
                }
                i3++;
            }
            if (i3 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = this.b.get(i3);
            this.b.remove(i3);
            return fullSpanItem.a;
        }

        private void l(int i2, int i3) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                int i4 = fullSpanItem.a;
                if (i4 >= i2) {
                    fullSpanItem.a = i4 + i3;
                }
            }
        }

        private void m(int i2, int i3) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return;
            }
            int i4 = i2 + i3;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                int i5 = fullSpanItem.a;
                if (i5 >= i2) {
                    if (i5 < i4) {
                        this.b.remove(size);
                    } else {
                        fullSpanItem.a = i5 - i3;
                    }
                }
            }
        }

        public void a(FullSpanItem fullSpanItem) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            int size = this.b.size();
            for (int i2 = 0; i2 < size; i2++) {
                FullSpanItem fullSpanItem2 = this.b.get(i2);
                if (fullSpanItem2.a == fullSpanItem.a) {
                    this.b.remove(i2);
                }
                if (fullSpanItem2.a >= fullSpanItem.a) {
                    this.b.add(i2, fullSpanItem);
                    return;
                }
            }
            this.b.add(fullSpanItem);
        }

        void b() {
            int[] iArr = this.a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.b = null;
        }

        void c(int i2) {
            int[] iArr = this.a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i2, 10) + 1];
                this.a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i2 >= iArr.length) {
                int[] iArr3 = new int[o(i2)];
                this.a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        int d(int i2) {
            List<FullSpanItem> list = this.b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.b.get(size).a >= i2) {
                        this.b.remove(size);
                    }
                }
            }
            return h(i2);
        }

        public FullSpanItem e(int i2, int i3, int i4, boolean z) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i5 = 0; i5 < size; i5++) {
                FullSpanItem fullSpanItem = this.b.get(i5);
                int i6 = fullSpanItem.a;
                if (i6 >= i3) {
                    return null;
                }
                if (i6 >= i2 && (i4 == 0 || fullSpanItem.b == i4 || (z && fullSpanItem.f983d))) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public FullSpanItem f(int i2) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                if (fullSpanItem.a == i2) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        int g(int i2) {
            int[] iArr = this.a;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            return iArr[i2];
        }

        int h(int i2) {
            int[] iArr = this.a;
            if (iArr == null || i2 >= iArr.length) {
                return -1;
            }
            int i3 = i(i2);
            if (i3 == -1) {
                int[] iArr2 = this.a;
                Arrays.fill(iArr2, i2, iArr2.length, -1);
                return this.a.length;
            }
            int i4 = i3 + 1;
            Arrays.fill(this.a, i2, i4, -1);
            return i4;
        }

        void j(int i2, int i3) {
            int[] iArr = this.a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            c(i4);
            int[] iArr2 = this.a;
            System.arraycopy(iArr2, i2, iArr2, i4, (iArr2.length - i2) - i3);
            Arrays.fill(this.a, i2, i4, -1);
            l(i2, i3);
        }

        void k(int i2, int i3) {
            int[] iArr = this.a;
            if (iArr == null || i2 >= iArr.length) {
                return;
            }
            int i4 = i2 + i3;
            c(i4);
            int[] iArr2 = this.a;
            System.arraycopy(iArr2, i4, iArr2, i2, (iArr2.length - i2) - i3);
            int[] iArr3 = this.a;
            Arrays.fill(iArr3, iArr3.length - i3, iArr3.length, -1);
            m(i2, i3);
        }

        void n(int i2, c cVar) {
            c(i2);
            this.a[i2] = cVar.e;
        }

        int o(int i2) {
            int length = this.a.length;
            while (length <= i2) {
                length *= 2;
            }
            return length;
        }
    }

    @SuppressLint({"BanParcelableUsage"})
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        int a;
        int b;

        /* renamed from: c, reason: collision with root package name */
        int f984c;

        /* renamed from: d, reason: collision with root package name */
        int[] f985d;
        int e;
        int[] f;
        List<LazySpanLookup.FullSpanItem> g;
        boolean h;

        /* renamed from: i, reason: collision with root package name */
        boolean f986i;
        boolean j;

        /* loaded from: classes.dex */
        static class a implements Parcelable.Creator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        public SavedState() {
        }

        SavedState(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            int readInt = parcel.readInt();
            this.f984c = readInt;
            if (readInt > 0) {
                int[] iArr = new int[readInt];
                this.f985d = iArr;
                parcel.readIntArray(iArr);
            }
            int readInt2 = parcel.readInt();
            this.e = readInt2;
            if (readInt2 > 0) {
                int[] iArr2 = new int[readInt2];
                this.f = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.h = parcel.readInt() == 1;
            this.f986i = parcel.readInt() == 1;
            this.j = parcel.readInt() == 1;
            this.g = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.f984c = savedState.f984c;
            this.a = savedState.a;
            this.b = savedState.b;
            this.f985d = savedState.f985d;
            this.e = savedState.e;
            this.f = savedState.f;
            this.h = savedState.h;
            this.f986i = savedState.f986i;
            this.j = savedState.j;
            this.g = savedState.g;
        }

        void a() {
            this.f985d = null;
            this.f984c = 0;
            this.a = -1;
            this.b = -1;
        }

        void b() {
            this.f985d = null;
            this.f984c = 0;
            this.e = 0;
            this.f = null;
            this.g = null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.f984c);
            if (this.f984c > 0) {
                parcel.writeIntArray(this.f985d);
            }
            parcel.writeInt(this.e);
            if (this.e > 0) {
                parcel.writeIntArray(this.f);
            }
            parcel.writeInt(this.h ? 1 : 0);
            parcel.writeInt(this.f986i ? 1 : 0);
            parcel.writeInt(this.j ? 1 : 0);
            parcel.writeList(this.g);
        }
    }

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.checkForGaps();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b {
        int a;
        int b;

        /* renamed from: c, reason: collision with root package name */
        boolean f987c;

        /* renamed from: d, reason: collision with root package name */
        boolean f988d;
        boolean e;
        int[] f;

        b() {
            c();
        }

        void a() {
            this.b = this.f987c ? StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() : StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
        }

        void b(int i2) {
            this.b = this.f987c ? StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding() - i2 : StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding() + i2;
        }

        void c() {
            this.a = -1;
            this.b = Integer.MIN_VALUE;
            this.f987c = false;
            this.f988d = false;
            this.e = false;
            int[] iArr = this.f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        void d(c[] cVarArr) {
            int length = cVarArr.length;
            int[] iArr = this.f;
            if (iArr == null || iArr.length < length) {
                this.f = new int[StaggeredGridLayoutManager.this.mSpans.length];
            }
            for (int i2 = 0; i2 < length; i2++) {
                this.f[i2] = cVarArr[i2].u(Integer.MIN_VALUE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c {
        ArrayList<View> a = new ArrayList<>();
        int b = Integer.MIN_VALUE;

        /* renamed from: c, reason: collision with root package name */
        int f989c = Integer.MIN_VALUE;

        /* renamed from: d, reason: collision with root package name */
        int f990d = 0;
        final int e;

        c(int i2) {
            this.e = i2;
        }

        void A(int i2) {
            this.b = i2;
            this.f989c = i2;
        }

        void a(View view) {
            LayoutParams s = s(view);
            s.e = this;
            this.a.add(view);
            this.f989c = Integer.MIN_VALUE;
            if (this.a.size() == 1) {
                this.b = Integer.MIN_VALUE;
            }
            if (s.isItemRemoved() || s.isItemChanged()) {
                this.f990d += StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
            }
        }

        void b(boolean z, int i2) {
            int q = z ? q(Integer.MIN_VALUE) : u(Integer.MIN_VALUE);
            e();
            if (q == Integer.MIN_VALUE) {
                return;
            }
            if (!z || q >= StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding()) {
                if (z || q <= StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding()) {
                    if (i2 != Integer.MIN_VALUE) {
                        q += i2;
                    }
                    this.f989c = q;
                    this.b = q;
                }
            }
        }

        void c() {
            LazySpanLookup.FullSpanItem f;
            ArrayList<View> arrayList = this.a;
            View view = arrayList.get(arrayList.size() - 1);
            LayoutParams s = s(view);
            this.f989c = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
            if (s.f && (f = StaggeredGridLayoutManager.this.mLazySpanLookup.f(s.getViewLayoutPosition())) != null && f.b == 1) {
                this.f989c += f.a(this.e);
            }
        }

        void d() {
            LazySpanLookup.FullSpanItem f;
            View view = this.a.get(0);
            LayoutParams s = s(view);
            this.b = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
            if (s.f && (f = StaggeredGridLayoutManager.this.mLazySpanLookup.f(s.getViewLayoutPosition())) != null && f.b == -1) {
                this.b -= f.a(this.e);
            }
        }

        void e() {
            this.a.clear();
            v();
            this.f990d = 0;
        }

        public int f() {
            int i2;
            int size;
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                i2 = this.a.size() - 1;
                size = -1;
            } else {
                i2 = 0;
                size = this.a.size();
            }
            return n(i2, size, true);
        }

        public int g() {
            int i2;
            int size;
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                i2 = this.a.size() - 1;
                size = -1;
            } else {
                i2 = 0;
                size = this.a.size();
            }
            return m(i2, size, true);
        }

        public int h() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? n(this.a.size() - 1, -1, false) : n(0, this.a.size(), false);
        }

        public int i() {
            int size;
            int i2;
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                size = 0;
                i2 = this.a.size();
            } else {
                size = this.a.size() - 1;
                i2 = -1;
            }
            return n(size, i2, true);
        }

        public int j() {
            int size;
            int i2;
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                size = 0;
                i2 = this.a.size();
            } else {
                size = this.a.size() - 1;
                i2 = -1;
            }
            return m(size, i2, true);
        }

        public int k() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? n(0, this.a.size(), false) : n(this.a.size() - 1, -1, false);
        }

        int l(int i2, int i3, boolean z, boolean z2, boolean z3) {
            int startAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            int endAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            int i4 = i3 > i2 ? 1 : -1;
            while (i2 != i3) {
                View view = this.a.get(i2);
                int decoratedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
                int decoratedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
                boolean z4 = false;
                boolean z5 = !z3 ? decoratedStart >= endAfterPadding : decoratedStart > endAfterPadding;
                if (!z3 ? decoratedEnd > startAfterPadding : decoratedEnd >= startAfterPadding) {
                    z4 = true;
                }
                if (z5 && z4) {
                    if (!z || !z2) {
                        if (!z2 && decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                        }
                        return StaggeredGridLayoutManager.this.getPosition(view);
                    }
                    if (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding) {
                        return StaggeredGridLayoutManager.this.getPosition(view);
                    }
                }
                i2 += i4;
            }
            return -1;
        }

        int m(int i2, int i3, boolean z) {
            return l(i2, i3, false, false, z);
        }

        int n(int i2, int i3, boolean z) {
            return l(i2, i3, z, true, false);
        }

        public int o() {
            return this.f990d;
        }

        int p() {
            int i2 = this.f989c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            c();
            return this.f989c;
        }

        int q(int i2) {
            int i3 = this.f989c;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.a.size() == 0) {
                return i2;
            }
            c();
            return this.f989c;
        }

        public View r(int i2, int i3) {
            View view = null;
            if (i3 != -1) {
                int size = this.a.size() - 1;
                while (size >= 0) {
                    View view2 = this.a.get(size);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.mReverseLayout && staggeredGridLayoutManager.getPosition(view2) >= i2) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.mReverseLayout && staggeredGridLayoutManager2.getPosition(view2) <= i2) || !view2.hasFocusable()) {
                        break;
                    }
                    size--;
                    view = view2;
                }
            } else {
                int size2 = this.a.size();
                int i4 = 0;
                while (i4 < size2) {
                    View view3 = this.a.get(i4);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.mReverseLayout && staggeredGridLayoutManager3.getPosition(view3) <= i2) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.mReverseLayout && staggeredGridLayoutManager4.getPosition(view3) >= i2) || !view3.hasFocusable()) {
                        break;
                    }
                    i4++;
                    view = view3;
                }
            }
            return view;
        }

        LayoutParams s(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        int t() {
            int i2 = this.b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            d();
            return this.b;
        }

        int u(int i2) {
            int i3 = this.b;
            if (i3 != Integer.MIN_VALUE) {
                return i3;
            }
            if (this.a.size() == 0) {
                return i2;
            }
            d();
            return this.b;
        }

        void v() {
            this.b = Integer.MIN_VALUE;
            this.f989c = Integer.MIN_VALUE;
        }

        void w(int i2) {
            int i3 = this.b;
            if (i3 != Integer.MIN_VALUE) {
                this.b = i3 + i2;
            }
            int i4 = this.f989c;
            if (i4 != Integer.MIN_VALUE) {
                this.f989c = i4 + i2;
            }
        }

        void x() {
            int size = this.a.size();
            View remove = this.a.remove(size - 1);
            LayoutParams s = s(remove);
            s.e = null;
            if (s.isItemRemoved() || s.isItemChanged()) {
                this.f990d -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(remove);
            }
            if (size == 1) {
                this.b = Integer.MIN_VALUE;
            }
            this.f989c = Integer.MIN_VALUE;
        }

        void y() {
            View remove = this.a.remove(0);
            LayoutParams s = s(remove);
            s.e = null;
            if (this.a.size() == 0) {
                this.f989c = Integer.MIN_VALUE;
            }
            if (s.isItemRemoved() || s.isItemChanged()) {
                this.f990d -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(remove);
            }
            this.b = Integer.MIN_VALUE;
        }

        void z(View view) {
            LayoutParams s = s(view);
            s.e = this;
            this.a.add(0, view);
            this.b = Integer.MIN_VALUE;
            if (this.a.size() == 1) {
                this.f989c = Integer.MIN_VALUE;
            }
            if (s.isItemRemoved() || s.isItemChanged()) {
                this.f990d += StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
            }
        }
    }

    public StaggeredGridLayoutManager(int i2, int i3) {
        this.mOrientation = i3;
        setSpanCount(i2);
        this.mLayoutState = new f();
        createOrientationHelpers();
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i2, i3);
        setOrientation(properties.orientation);
        setSpanCount(properties.spanCount);
        setReverseLayout(properties.reverseLayout);
        this.mLayoutState = new f();
        createOrientationHelpers();
    }

    private void appendViewToAllSpans(View view) {
        for (int i2 = this.mSpanCount - 1; i2 >= 0; i2--) {
            this.mSpans[i2].a(view);
        }
    }

    private void applyPendingSavedState(b bVar) {
        boolean z;
        SavedState savedState = this.mPendingSavedState;
        int i2 = savedState.f984c;
        if (i2 > 0) {
            if (i2 == this.mSpanCount) {
                for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                    this.mSpans[i3].e();
                    SavedState savedState2 = this.mPendingSavedState;
                    int i4 = savedState2.f985d[i3];
                    if (i4 != Integer.MIN_VALUE) {
                        i4 += savedState2.f986i ? this.mPrimaryOrientation.getEndAfterPadding() : this.mPrimaryOrientation.getStartAfterPadding();
                    }
                    this.mSpans[i3].A(i4);
                }
            } else {
                savedState.b();
                SavedState savedState3 = this.mPendingSavedState;
                savedState3.a = savedState3.b;
            }
        }
        SavedState savedState4 = this.mPendingSavedState;
        this.mLastLayoutRTL = savedState4.j;
        setReverseLayout(savedState4.h);
        resolveShouldLayoutReverse();
        SavedState savedState5 = this.mPendingSavedState;
        int i5 = savedState5.a;
        if (i5 != -1) {
            this.mPendingScrollPosition = i5;
            z = savedState5.f986i;
        } else {
            z = this.mShouldReverseLayout;
        }
        bVar.f987c = z;
        if (savedState5.e > 1) {
            LazySpanLookup lazySpanLookup = this.mLazySpanLookup;
            lazySpanLookup.a = savedState5.f;
            lazySpanLookup.b = savedState5.g;
        }
    }

    private void attachViewToSpans(View view, LayoutParams layoutParams, f fVar) {
        if (fVar.e == 1) {
            if (layoutParams.f) {
                appendViewToAllSpans(view);
                return;
            } else {
                layoutParams.e.a(view);
                return;
            }
        }
        if (layoutParams.f) {
            prependViewToAllSpans(view);
        } else {
            layoutParams.e.z(view);
        }
    }

    private int calculateScrollDirectionForPosition(int i2) {
        if (getChildCount() == 0) {
            return this.mShouldReverseLayout ? 1 : -1;
        }
        return (i2 < getFirstChildPosition()) != this.mShouldReverseLayout ? -1 : 1;
    }

    private boolean checkSpanForGap(c cVar) {
        if (this.mShouldReverseLayout) {
            if (cVar.p() < this.mPrimaryOrientation.getEndAfterPadding()) {
                ArrayList<View> arrayList = cVar.a;
                return !cVar.s(arrayList.get(arrayList.size() - 1)).f;
            }
        } else if (cVar.t() > this.mPrimaryOrientation.getStartAfterPadding()) {
            return !cVar.s(cVar.a.get(0)).f;
        }
        return false;
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return i.a(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return i.b(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return i.c(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private int convertFocusDirectionToLayoutDirection(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 17 ? i2 != 33 ? i2 != 66 ? (i2 == 130 && this.mOrientation == 1) ? 1 : Integer.MIN_VALUE : this.mOrientation == 0 ? 1 : Integer.MIN_VALUE : this.mOrientation == 1 ? -1 : Integer.MIN_VALUE : this.mOrientation == 0 ? -1 : Integer.MIN_VALUE : (this.mOrientation != 1 && isLayoutRTL()) ? -1 : 1 : (this.mOrientation != 1 && isLayoutRTL()) ? 1 : -1;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromEnd(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f982c = new int[this.mSpanCount];
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            fullSpanItem.f982c[i3] = i2 - this.mSpans[i3].q(i2);
        }
        return fullSpanItem;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromStart(int i2) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.f982c = new int[this.mSpanCount];
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            fullSpanItem.f982c[i3] = this.mSpans[i3].u(i2) - i2;
        }
        return fullSpanItem;
    }

    private void createOrientationHelpers() {
        this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v7 */
    private int fill(RecyclerView.Recycler recycler, f fVar, RecyclerView.State state) {
        int i2;
        c cVar;
        int decoratedMeasurement;
        int i3;
        int i4;
        int decoratedMeasurement2;
        RecyclerView.LayoutManager layoutManager;
        View view;
        int i5;
        int i6;
        ?? r9 = 0;
        this.mRemainingSpans.set(0, this.mSpanCount, true);
        if (this.mLayoutState.f1010i) {
            i2 = fVar.e == 1 ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : Integer.MIN_VALUE;
        } else {
            i2 = fVar.e == 1 ? fVar.g + fVar.b : fVar.f - fVar.b;
        }
        updateAllRemainingSpans(fVar.e, i2);
        int endAfterPadding = this.mShouldReverseLayout ? this.mPrimaryOrientation.getEndAfterPadding() : this.mPrimaryOrientation.getStartAfterPadding();
        boolean z = false;
        while (fVar.a(state) && (this.mLayoutState.f1010i || !this.mRemainingSpans.isEmpty())) {
            View b2 = fVar.b(recycler);
            LayoutParams layoutParams = (LayoutParams) b2.getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition();
            int g = this.mLazySpanLookup.g(viewLayoutPosition);
            boolean z2 = g == -1;
            if (z2) {
                cVar = layoutParams.f ? this.mSpans[r9] : getNextSpan(fVar);
                this.mLazySpanLookup.n(viewLayoutPosition, cVar);
            } else {
                cVar = this.mSpans[g];
            }
            c cVar2 = cVar;
            layoutParams.e = cVar2;
            if (fVar.e == 1) {
                addView(b2);
            } else {
                addView(b2, r9);
            }
            measureChildWithDecorationsAndMargin(b2, layoutParams, r9);
            if (fVar.e == 1) {
                int maxEnd = layoutParams.f ? getMaxEnd(endAfterPadding) : cVar2.q(endAfterPadding);
                int decoratedMeasurement3 = this.mPrimaryOrientation.getDecoratedMeasurement(b2) + maxEnd;
                if (z2 && layoutParams.f) {
                    LazySpanLookup.FullSpanItem createFullSpanItemFromEnd = createFullSpanItemFromEnd(maxEnd);
                    createFullSpanItemFromEnd.b = -1;
                    createFullSpanItemFromEnd.a = viewLayoutPosition;
                    this.mLazySpanLookup.a(createFullSpanItemFromEnd);
                }
                i3 = decoratedMeasurement3;
                decoratedMeasurement = maxEnd;
            } else {
                int minStart = layoutParams.f ? getMinStart(endAfterPadding) : cVar2.u(endAfterPadding);
                decoratedMeasurement = minStart - this.mPrimaryOrientation.getDecoratedMeasurement(b2);
                if (z2 && layoutParams.f) {
                    LazySpanLookup.FullSpanItem createFullSpanItemFromStart = createFullSpanItemFromStart(minStart);
                    createFullSpanItemFromStart.b = 1;
                    createFullSpanItemFromStart.a = viewLayoutPosition;
                    this.mLazySpanLookup.a(createFullSpanItemFromStart);
                }
                i3 = minStart;
            }
            if (layoutParams.f && fVar.f1009d == -1) {
                if (!z2) {
                    if (!(fVar.e == 1 ? areAllEndsEqual() : areAllStartsEqual())) {
                        LazySpanLookup.FullSpanItem f = this.mLazySpanLookup.f(viewLayoutPosition);
                        if (f != null) {
                            f.f983d = true;
                        }
                    }
                }
                this.mLaidOutInvalidFullSpan = true;
            }
            attachViewToSpans(b2, layoutParams, fVar);
            if (isLayoutRTL() && this.mOrientation == 1) {
                int endAfterPadding2 = layoutParams.f ? this.mSecondaryOrientation.getEndAfterPadding() : this.mSecondaryOrientation.getEndAfterPadding() - (((this.mSpanCount - 1) - cVar2.e) * this.mSizePerSpan);
                decoratedMeasurement2 = endAfterPadding2;
                i4 = endAfterPadding2 - this.mSecondaryOrientation.getDecoratedMeasurement(b2);
            } else {
                int startAfterPadding = layoutParams.f ? this.mSecondaryOrientation.getStartAfterPadding() : (cVar2.e * this.mSizePerSpan) + this.mSecondaryOrientation.getStartAfterPadding();
                i4 = startAfterPadding;
                decoratedMeasurement2 = this.mSecondaryOrientation.getDecoratedMeasurement(b2) + startAfterPadding;
            }
            if (this.mOrientation == 1) {
                layoutManager = this;
                view = b2;
                i5 = i4;
                i4 = decoratedMeasurement;
                i6 = decoratedMeasurement2;
            } else {
                layoutManager = this;
                view = b2;
                i5 = decoratedMeasurement;
                i6 = i3;
                i3 = decoratedMeasurement2;
            }
            layoutManager.layoutDecoratedWithMargins(view, i5, i4, i6, i3);
            if (layoutParams.f) {
                updateAllRemainingSpans(this.mLayoutState.e, i2);
            } else {
                updateRemainingSpans(cVar2, this.mLayoutState.e, i2);
            }
            recycle(recycler, this.mLayoutState);
            if (this.mLayoutState.h && b2.hasFocusable()) {
                if (layoutParams.f) {
                    this.mRemainingSpans.clear();
                } else {
                    this.mRemainingSpans.set(cVar2.e, false);
                    z = true;
                    r9 = 0;
                }
            }
            z = true;
            r9 = 0;
        }
        if (!z) {
            recycle(recycler, this.mLayoutState);
        }
        int startAfterPadding2 = this.mLayoutState.e == -1 ? this.mPrimaryOrientation.getStartAfterPadding() - getMinStart(this.mPrimaryOrientation.getStartAfterPadding()) : getMaxEnd(this.mPrimaryOrientation.getEndAfterPadding()) - this.mPrimaryOrientation.getEndAfterPadding();
        if (startAfterPadding2 > 0) {
            return Math.min(fVar.b, startAfterPadding2);
        }
        return 0;
    }

    private int findFirstReferenceChildPosition(int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            int position = getPosition(getChildAt(i3));
            if (position >= 0 && position < i2) {
                return position;
            }
        }
        return 0;
    }

    private int findLastReferenceChildPosition(int i2) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            int position = getPosition(getChildAt(childCount));
            if (position >= 0 && position < i2) {
                return position;
            }
        }
        return 0;
    }

    private void fixEndGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int maxEnd = getMaxEnd(Integer.MIN_VALUE);
        if (maxEnd != Integer.MIN_VALUE && (endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding() - maxEnd) > 0) {
            int i2 = endAfterPadding - (-scrollBy(-endAfterPadding, recycler, state));
            if (!z || i2 <= 0) {
                return;
            }
            this.mPrimaryOrientation.offsetChildren(i2);
        }
    }

    private void fixStartGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int minStart = getMinStart(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        if (minStart != Integer.MAX_VALUE && (startAfterPadding = minStart - this.mPrimaryOrientation.getStartAfterPadding()) > 0) {
            int scrollBy = startAfterPadding - scrollBy(startAfterPadding, recycler, state);
            if (!z || scrollBy <= 0) {
                return;
            }
            this.mPrimaryOrientation.offsetChildren(-scrollBy);
        }
    }

    private int getMaxEnd(int i2) {
        int q = this.mSpans[0].q(i2);
        for (int i3 = 1; i3 < this.mSpanCount; i3++) {
            int q2 = this.mSpans[i3].q(i2);
            if (q2 > q) {
                q = q2;
            }
        }
        return q;
    }

    private int getMaxStart(int i2) {
        int u = this.mSpans[0].u(i2);
        for (int i3 = 1; i3 < this.mSpanCount; i3++) {
            int u2 = this.mSpans[i3].u(i2);
            if (u2 > u) {
                u = u2;
            }
        }
        return u;
    }

    private int getMinEnd(int i2) {
        int q = this.mSpans[0].q(i2);
        for (int i3 = 1; i3 < this.mSpanCount; i3++) {
            int q2 = this.mSpans[i3].q(i2);
            if (q2 < q) {
                q = q2;
            }
        }
        return q;
    }

    private int getMinStart(int i2) {
        int u = this.mSpans[0].u(i2);
        for (int i3 = 1; i3 < this.mSpanCount; i3++) {
            int u2 = this.mSpans[i3].u(i2);
            if (u2 < u) {
                u = u2;
            }
        }
        return u;
    }

    private c getNextSpan(f fVar) {
        int i2;
        int i3;
        int i4 = -1;
        if (preferLastSpan(fVar.e)) {
            i2 = this.mSpanCount - 1;
            i3 = -1;
        } else {
            i2 = 0;
            i4 = this.mSpanCount;
            i3 = 1;
        }
        c cVar = null;
        if (fVar.e == 1) {
            int i5 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
            while (i2 != i4) {
                c cVar2 = this.mSpans[i2];
                int q = cVar2.q(startAfterPadding);
                if (q < i5) {
                    cVar = cVar2;
                    i5 = q;
                }
                i2 += i3;
            }
            return cVar;
        }
        int i6 = Integer.MIN_VALUE;
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        while (i2 != i4) {
            c cVar3 = this.mSpans[i2];
            int u = cVar3.u(endAfterPadding);
            if (u > i6) {
                cVar = cVar3;
                i6 = u;
            }
            i2 += i3;
        }
        return cVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void handleUpdate(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.mShouldReverseLayout
            if (r0 == 0) goto L9
            int r0 = r6.getLastChildPosition()
            goto Ld
        L9:
            int r0 = r6.getFirstChildPosition()
        Ld:
            r1 = 8
            if (r9 != r1) goto L1a
            if (r7 >= r8) goto L16
            int r2 = r8 + 1
            goto L1c
        L16:
            int r2 = r7 + 1
            r3 = r8
            goto L1d
        L1a:
            int r2 = r7 + r8
        L1c:
            r3 = r7
        L1d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r6.mLazySpanLookup
            r4.h(r3)
            r4 = 1
            if (r9 == r4) goto L3c
            r5 = 2
            if (r9 == r5) goto L36
            if (r9 == r1) goto L2b
            goto L41
        L2b:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.k(r7, r4)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r7 = r6.mLazySpanLookup
            r7.j(r8, r4)
            goto L41
        L36:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.k(r7, r8)
            goto L41
        L3c:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.j(r7, r8)
        L41:
            if (r2 > r0) goto L44
            return
        L44:
            boolean r7 = r6.mShouldReverseLayout
            if (r7 == 0) goto L4d
            int r7 = r6.getFirstChildPosition()
            goto L51
        L4d:
            int r7 = r6.getLastChildPosition()
        L51:
            if (r3 > r7) goto L56
            r6.requestLayout()
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.handleUpdate(int, int, int):void");
    }

    private void measureChildWithDecorationsAndMargin(View view, int i2, int i3, boolean z) {
        calculateItemDecorationsForChild(view, this.mTmpRect);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        Rect rect = this.mTmpRect;
        int updateSpecWithExtra = updateSpecWithExtra(i2, i4 + rect.left, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + rect.right);
        int i5 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        Rect rect2 = this.mTmpRect;
        int updateSpecWithExtra2 = updateSpecWithExtra(i3, i5 + rect2.top, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + rect2.bottom);
        if (z ? shouldReMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams) : shouldMeasureChild(view, updateSpecWithExtra, updateSpecWithExtra2, layoutParams)) {
            view.measure(updateSpecWithExtra, updateSpecWithExtra2);
        }
    }

    private void measureChildWithDecorationsAndMargin(View view, LayoutParams layoutParams, boolean z) {
        int childMeasureSpec;
        int childMeasureSpec2;
        if (layoutParams.f) {
            if (this.mOrientation != 1) {
                measureChildWithDecorationsAndMargin(view, RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) layoutParams).width, true), this.mFullSizeSpec, z);
                return;
            }
            childMeasureSpec = this.mFullSizeSpec;
        } else {
            if (this.mOrientation != 1) {
                childMeasureSpec = RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) layoutParams).width, true);
                childMeasureSpec2 = RecyclerView.LayoutManager.getChildMeasureSpec(this.mSizePerSpan, getHeightMode(), 0, ((ViewGroup.MarginLayoutParams) layoutParams).height, false);
                measureChildWithDecorationsAndMargin(view, childMeasureSpec, childMeasureSpec2, z);
            }
            childMeasureSpec = RecyclerView.LayoutManager.getChildMeasureSpec(this.mSizePerSpan, getWidthMode(), 0, ((ViewGroup.MarginLayoutParams) layoutParams).width, false);
        }
        childMeasureSpec2 = RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), ((ViewGroup.MarginLayoutParams) layoutParams).height, true);
        measureChildWithDecorationsAndMargin(view, childMeasureSpec, childMeasureSpec2, z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x014b, code lost:
    
        if (checkForGaps() != false) goto L90;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void onLayoutChildren(androidx.recyclerview.widget.RecyclerView.Recycler r9, androidx.recyclerview.widget.RecyclerView.State r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 367
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.onLayoutChildren(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, boolean):void");
    }

    private boolean preferLastSpan(int i2) {
        if (this.mOrientation == 0) {
            return (i2 == -1) != this.mShouldReverseLayout;
        }
        return ((i2 == -1) == this.mShouldReverseLayout) == isLayoutRTL();
    }

    private void prependViewToAllSpans(View view) {
        for (int i2 = this.mSpanCount - 1; i2 >= 0; i2--) {
            this.mSpans[i2].z(view);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0010, code lost:
    
        if (r4.e == (-1)) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void recycle(androidx.recyclerview.widget.RecyclerView.Recycler r3, androidx.recyclerview.widget.f r4) {
        /*
            r2 = this;
            boolean r0 = r4.a
            if (r0 == 0) goto L4d
            boolean r0 = r4.f1010i
            if (r0 == 0) goto L9
            goto L4d
        L9:
            int r0 = r4.b
            r1 = -1
            if (r0 != 0) goto L1e
            int r0 = r4.e
            if (r0 != r1) goto L18
        L12:
            int r4 = r4.g
        L14:
            r2.recycleFromEnd(r3, r4)
            goto L4d
        L18:
            int r4 = r4.f
        L1a:
            r2.recycleFromStart(r3, r4)
            goto L4d
        L1e:
            int r0 = r4.e
            if (r0 != r1) goto L37
            int r0 = r4.f
            int r1 = r2.getMaxStart(r0)
            int r0 = r0 - r1
            if (r0 >= 0) goto L2c
            goto L12
        L2c:
            int r1 = r4.g
            int r4 = r4.b
            int r4 = java.lang.Math.min(r0, r4)
            int r4 = r1 - r4
            goto L14
        L37:
            int r0 = r4.g
            int r0 = r2.getMinEnd(r0)
            int r1 = r4.g
            int r0 = r0 - r1
            if (r0 >= 0) goto L43
            goto L18
        L43:
            int r1 = r4.f
            int r4 = r4.b
            int r4 = java.lang.Math.min(r0, r4)
            int r4 = r4 + r1
            goto L1a
        L4d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.recycle(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.f):void");
    }

    private void recycleFromEnd(RecyclerView.Recycler recycler, int i2) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (this.mPrimaryOrientation.getDecoratedStart(childAt) < i2 || this.mPrimaryOrientation.getTransformedStartWithDecoration(childAt) < i2) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.f) {
                for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                    if (this.mSpans[i3].a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.mSpanCount; i4++) {
                    this.mSpans[i4].x();
                }
            } else if (layoutParams.e.a.size() == 1) {
                return;
            } else {
                layoutParams.e.x();
            }
            removeAndRecycleView(childAt, recycler);
        }
    }

    private void recycleFromStart(RecyclerView.Recycler recycler, int i2) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) > i2 || this.mPrimaryOrientation.getTransformedEndWithDecoration(childAt) > i2) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.f) {
                for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                    if (this.mSpans[i3].a.size() == 1) {
                        return;
                    }
                }
                for (int i4 = 0; i4 < this.mSpanCount; i4++) {
                    this.mSpans[i4].y();
                }
            } else if (layoutParams.e.a.size() == 1) {
                return;
            } else {
                layoutParams.e.y();
            }
            removeAndRecycleView(childAt, recycler);
        }
    }

    private void repositionToWrapContentIfNecessary() {
        if (this.mSecondaryOrientation.getMode() == 1073741824) {
            return;
        }
        float f = 0.0f;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            float decoratedMeasurement = this.mSecondaryOrientation.getDecoratedMeasurement(childAt);
            if (decoratedMeasurement >= f) {
                if (((LayoutParams) childAt.getLayoutParams()).isFullSpan()) {
                    decoratedMeasurement = (decoratedMeasurement * 1.0f) / this.mSpanCount;
                }
                f = Math.max(f, decoratedMeasurement);
            }
        }
        int i3 = this.mSizePerSpan;
        int round = Math.round(f * this.mSpanCount);
        if (this.mSecondaryOrientation.getMode() == Integer.MIN_VALUE) {
            round = Math.min(round, this.mSecondaryOrientation.getTotalSpace());
        }
        updateMeasureSpecs(round);
        if (this.mSizePerSpan == i3) {
            return;
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt2 = getChildAt(i4);
            LayoutParams layoutParams = (LayoutParams) childAt2.getLayoutParams();
            if (!layoutParams.f) {
                if (isLayoutRTL() && this.mOrientation == 1) {
                    int i5 = this.mSpanCount;
                    int i6 = layoutParams.e.e;
                    childAt2.offsetLeftAndRight(((-((i5 - 1) - i6)) * this.mSizePerSpan) - ((-((i5 - 1) - i6)) * i3));
                } else {
                    int i7 = layoutParams.e.e;
                    int i8 = this.mOrientation;
                    int i9 = (this.mSizePerSpan * i7) - (i7 * i3);
                    if (i8 == 1) {
                        childAt2.offsetLeftAndRight(i9);
                    } else {
                        childAt2.offsetTopAndBottom(i9);
                    }
                }
            }
        }
    }

    private void resolveShouldLayoutReverse() {
        this.mShouldReverseLayout = (this.mOrientation == 1 || !isLayoutRTL()) ? this.mReverseLayout : !this.mReverseLayout;
    }

    private void setLayoutStateDirection(int i2) {
        f fVar = this.mLayoutState;
        fVar.e = i2;
        fVar.f1009d = this.mShouldReverseLayout != (i2 == -1) ? -1 : 1;
    }

    private void updateAllRemainingSpans(int i2, int i3) {
        for (int i4 = 0; i4 < this.mSpanCount; i4++) {
            if (!this.mSpans[i4].a.isEmpty()) {
                updateRemainingSpans(this.mSpans[i4], i2, i3);
            }
        }
    }

    private boolean updateAnchorFromChildren(RecyclerView.State state, b bVar) {
        boolean z = this.mLastLayoutFromEnd;
        int itemCount = state.getItemCount();
        bVar.a = z ? findLastReferenceChildPosition(itemCount) : findFirstReferenceChildPosition(itemCount);
        bVar.b = Integer.MIN_VALUE;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateLayoutState(int r5, androidx.recyclerview.widget.RecyclerView.State r6) {
        /*
            r4 = this;
            androidx.recyclerview.widget.f r0 = r4.mLayoutState
            r1 = 0
            r0.b = r1
            r0.f1008c = r5
            boolean r0 = r4.isSmoothScrolling()
            r2 = 1
            if (r0 == 0) goto L2e
            int r6 = r6.getTargetScrollPosition()
            r0 = -1
            if (r6 == r0) goto L2e
            boolean r0 = r4.mShouldReverseLayout
            if (r6 >= r5) goto L1b
            r5 = 1
            goto L1c
        L1b:
            r5 = 0
        L1c:
            if (r0 != r5) goto L25
            androidx.recyclerview.widget.OrientationHelper r5 = r4.mPrimaryOrientation
            int r5 = r5.getTotalSpace()
            goto L2f
        L25:
            androidx.recyclerview.widget.OrientationHelper r5 = r4.mPrimaryOrientation
            int r5 = r5.getTotalSpace()
            r6 = r5
            r5 = 0
            goto L30
        L2e:
            r5 = 0
        L2f:
            r6 = 0
        L30:
            boolean r0 = r4.getClipToPadding()
            if (r0 == 0) goto L4d
            androidx.recyclerview.widget.f r0 = r4.mLayoutState
            androidx.recyclerview.widget.OrientationHelper r3 = r4.mPrimaryOrientation
            int r3 = r3.getStartAfterPadding()
            int r3 = r3 - r6
            r0.f = r3
            androidx.recyclerview.widget.f r6 = r4.mLayoutState
            androidx.recyclerview.widget.OrientationHelper r0 = r4.mPrimaryOrientation
            int r0 = r0.getEndAfterPadding()
            int r0 = r0 + r5
            r6.g = r0
            goto L5d
        L4d:
            androidx.recyclerview.widget.f r0 = r4.mLayoutState
            androidx.recyclerview.widget.OrientationHelper r3 = r4.mPrimaryOrientation
            int r3 = r3.getEnd()
            int r3 = r3 + r5
            r0.g = r3
            androidx.recyclerview.widget.f r5 = r4.mLayoutState
            int r6 = -r6
            r5.f = r6
        L5d:
            androidx.recyclerview.widget.f r5 = r4.mLayoutState
            r5.h = r1
            r5.a = r2
            androidx.recyclerview.widget.OrientationHelper r6 = r4.mPrimaryOrientation
            int r6 = r6.getMode()
            if (r6 != 0) goto L74
            androidx.recyclerview.widget.OrientationHelper r6 = r4.mPrimaryOrientation
            int r6 = r6.getEnd()
            if (r6 != 0) goto L74
            r1 = 1
        L74:
            r5.f1010i = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.updateLayoutState(int, androidx.recyclerview.widget.RecyclerView$State):void");
    }

    private void updateRemainingSpans(c cVar, int i2, int i3) {
        int o = cVar.o();
        if (i2 == -1) {
            if (cVar.t() + o > i3) {
                return;
            }
        } else if (cVar.p() - o < i3) {
            return;
        }
        this.mRemainingSpans.set(cVar.e, false);
    }

    private int updateSpecWithExtra(int i2, int i3, int i4) {
        if (i3 == 0 && i4 == 0) {
            return i2;
        }
        int mode = View.MeasureSpec.getMode(i2);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i2) - i3) - i4), mode) : i2;
    }

    boolean areAllEndsEqual() {
        int q = this.mSpans[0].q(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            if (this.mSpans[i2].q(Integer.MIN_VALUE) != q) {
                return false;
            }
        }
        return true;
    }

    boolean areAllStartsEqual() {
        int u = this.mSpans[0].u(Integer.MIN_VALUE);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            if (this.mSpans[i2].u(Integer.MIN_VALUE) != u) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    boolean checkForGaps() {
        int firstChildPosition;
        int lastChildPosition;
        if (getChildCount() == 0 || this.mGapStrategy == 0 || !isAttachedToWindow()) {
            return false;
        }
        if (this.mShouldReverseLayout) {
            firstChildPosition = getLastChildPosition();
            lastChildPosition = getFirstChildPosition();
        } else {
            firstChildPosition = getFirstChildPosition();
            lastChildPosition = getLastChildPosition();
        }
        if (firstChildPosition == 0 && hasGapsToFix() != null) {
            this.mLazySpanLookup.b();
        } else {
            if (!this.mLaidOutInvalidFullSpan) {
                return false;
            }
            int i2 = this.mShouldReverseLayout ? -1 : 1;
            int i3 = lastChildPosition + 1;
            LazySpanLookup.FullSpanItem e = this.mLazySpanLookup.e(firstChildPosition, i3, i2, true);
            if (e == null) {
                this.mLaidOutInvalidFullSpan = false;
                this.mLazySpanLookup.d(i3);
                return false;
            }
            LazySpanLookup.FullSpanItem e2 = this.mLazySpanLookup.e(firstChildPosition, e.a, i2 * (-1), true);
            if (e2 == null) {
                this.mLazySpanLookup.d(e.a);
            } else {
                this.mLazySpanLookup.d(e2.a + 1);
            }
        }
        requestSimpleAnimationsInNextLayout();
        requestLayout();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void collectAdjacentPrefetchPositions(int i2, int i3, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int q;
        int i4;
        if (this.mOrientation != 0) {
            i2 = i3;
        }
        if (getChildCount() == 0 || i2 == 0) {
            return;
        }
        prepareLayoutStateForDelta(i2, state);
        int[] iArr = this.mPrefetchDistances;
        if (iArr == null || iArr.length < this.mSpanCount) {
            this.mPrefetchDistances = new int[this.mSpanCount];
        }
        int i5 = 0;
        for (int i6 = 0; i6 < this.mSpanCount; i6++) {
            f fVar = this.mLayoutState;
            if (fVar.f1009d == -1) {
                q = fVar.f;
                i4 = this.mSpans[i6].u(q);
            } else {
                q = this.mSpans[i6].q(fVar.g);
                i4 = this.mLayoutState.g;
            }
            int i7 = q - i4;
            if (i7 >= 0) {
                this.mPrefetchDistances[i5] = i7;
                i5++;
            }
        }
        Arrays.sort(this.mPrefetchDistances, 0, i5);
        for (int i8 = 0; i8 < i5 && this.mLayoutState.a(state); i8++) {
            layoutPrefetchRegistry.addPosition(this.mLayoutState.f1008c, this.mPrefetchDistances[i8]);
            f fVar2 = this.mLayoutState;
            fVar2.f1008c += fVar2.f1009d;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i2) {
        int calculateScrollDirectionForPosition = calculateScrollDirectionForPosition(i2);
        PointF pointF = new PointF();
        if (calculateScrollDirectionForPosition == 0) {
            return null;
        }
        if (this.mOrientation == 0) {
            pointF.x = calculateScrollDirectionForPosition;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = calculateScrollDirectionForPosition;
        }
        return pointF;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public int[] findFirstCompletelyVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr[i2] = this.mSpans[i2].f();
        }
        return iArr;
    }

    View findFirstVisibleItemClosestToEnd(boolean z) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(childAt);
            if (decoratedEnd > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedEnd <= endAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    View findFirstVisibleItemClosestToStart(boolean z) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        int childCount = getChildCount();
        View view = null;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedStart >= startAfterPadding || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    int findFirstVisibleItemPositionInt() {
        View findFirstVisibleItemClosestToEnd = this.mShouldReverseLayout ? findFirstVisibleItemClosestToEnd(true) : findFirstVisibleItemClosestToStart(true);
        if (findFirstVisibleItemClosestToEnd == null) {
            return -1;
        }
        return getPosition(findFirstVisibleItemClosestToEnd);
    }

    public int[] findFirstVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr[i2] = this.mSpans[i2].h();
        }
        return iArr;
    }

    public int[] findLastCompletelyVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr[i2] = this.mSpans[i2].i();
        }
        return iArr;
    }

    public int[] findLastVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            iArr[i2] = this.mSpans[i2].k();
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return this.mOrientation == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return this.mOrientation == 1 ? this.mSpanCount : super.getColumnCountForAccessibility(recycler, state);
    }

    int getFirstChildPosition() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    public int getGapStrategy() {
        return this.mGapStrategy;
    }

    int getLastChildPosition() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        return this.mOrientation == 0 ? this.mSpanCount : super.getRowCountForAccessibility(recycler, state);
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0074, code lost:
    
        if (r10 == r11) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008a, code lost:
    
        r10 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0088, code lost:
    
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0086, code lost:
    
        if (r10 == r11) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    android.view.View hasGapsToFix() {
        /*
            r12 = this;
            int r0 = r12.getChildCount()
            r1 = 1
            int r0 = r0 - r1
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r12.mSpanCount
            r2.<init>(r3)
            int r3 = r12.mSpanCount
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r12.mOrientation
            r5 = -1
            if (r3 != r1) goto L20
            boolean r3 = r12.isLayoutRTL()
            if (r3 == 0) goto L20
            r3 = 1
            goto L21
        L20:
            r3 = -1
        L21:
            boolean r6 = r12.mShouldReverseLayout
            if (r6 == 0) goto L27
            r6 = -1
            goto L2b
        L27:
            int r0 = r0 + 1
            r6 = r0
            r0 = 0
        L2b:
            if (r0 >= r6) goto L2e
            r5 = 1
        L2e:
            if (r0 == r6) goto Lab
            android.view.View r7 = r12.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LayoutParams r8 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams) r8
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = r8.e
            int r9 = r9.e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = r8.e
            boolean r9 = r12.checkSpanForGap(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = r8.e
            int r9 = r9.e
            r2.clear(r9)
        L54:
            boolean r9 = r8.f
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.getChildAt(r9)
            boolean r10 = r12.mShouldReverseLayout
            if (r10 == 0) goto L77
            androidx.recyclerview.widget.OrientationHelper r10 = r12.mPrimaryOrientation
            int r10 = r10.getDecoratedEnd(r7)
            androidx.recyclerview.widget.OrientationHelper r11 = r12.mPrimaryOrientation
            int r11 = r11.getDecoratedEnd(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            androidx.recyclerview.widget.OrientationHelper r10 = r12.mPrimaryOrientation
            int r10 = r10.getDecoratedStart(r7)
            androidx.recyclerview.widget.OrientationHelper r11 = r12.mPrimaryOrientation
            int r11 = r11.getDecoratedStart(r9)
            if (r10 <= r11) goto L86
            return r7
        L86:
            if (r10 != r11) goto L8a
        L88:
            r10 = 1
            goto L8b
        L8a:
            r10 = 0
        L8b:
            if (r10 == 0) goto La9
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LayoutParams r9 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams) r9
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r8 = r8.e
            int r8 = r8.e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = r9.e
            int r9 = r9.e
            int r8 = r8 - r9
            if (r8 >= 0) goto La0
            r8 = 1
            goto La1
        La0:
            r8 = 0
        La1:
            if (r3 >= 0) goto La5
            r9 = 1
            goto La6
        La5:
            r9 = 0
        La6:
            if (r8 == r9) goto La9
            return r7
        La9:
            int r0 = r0 + r5
            goto L2e
        Lab:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.hasGapsToFix():android.view.View");
    }

    public void invalidateSpanAssignments() {
        this.mLazySpanLookup.b();
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean isAutoMeasureEnabled() {
        return this.mGapStrategy != 0;
    }

    boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void offsetChildrenHorizontal(int i2) {
        super.offsetChildrenHorizontal(i2);
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            this.mSpans[i3].w(i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void offsetChildrenVertical(int i2) {
        super.offsetChildrenVertical(i2);
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            this.mSpans[i3].w(i2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        removeCallbacks(this.mCheckForGapsRunnable);
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            this.mSpans[i2].e();
        }
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    @Nullable
    public View onFocusSearchFailed(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        View findContainingItemView;
        View r;
        if (getChildCount() == 0 || (findContainingItemView = findContainingItemView(view)) == null) {
            return null;
        }
        resolveShouldLayoutReverse();
        int convertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i2);
        if (convertFocusDirectionToLayoutDirection == Integer.MIN_VALUE) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) findContainingItemView.getLayoutParams();
        boolean z = layoutParams.f;
        c cVar = layoutParams.e;
        int lastChildPosition = convertFocusDirectionToLayoutDirection == 1 ? getLastChildPosition() : getFirstChildPosition();
        updateLayoutState(lastChildPosition, state);
        setLayoutStateDirection(convertFocusDirectionToLayoutDirection);
        f fVar = this.mLayoutState;
        fVar.f1008c = fVar.f1009d + lastChildPosition;
        fVar.b = (int) (this.mPrimaryOrientation.getTotalSpace() * MAX_SCROLL_FACTOR);
        f fVar2 = this.mLayoutState;
        fVar2.h = true;
        fVar2.a = false;
        fill(recycler, fVar2, state);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        if (!z && (r = cVar.r(lastChildPosition, convertFocusDirectionToLayoutDirection)) != null && r != findContainingItemView) {
            return r;
        }
        if (preferLastSpan(convertFocusDirectionToLayoutDirection)) {
            for (int i3 = this.mSpanCount - 1; i3 >= 0; i3--) {
                View r2 = this.mSpans[i3].r(lastChildPosition, convertFocusDirectionToLayoutDirection);
                if (r2 != null && r2 != findContainingItemView) {
                    return r2;
                }
            }
        } else {
            for (int i4 = 0; i4 < this.mSpanCount; i4++) {
                View r3 = this.mSpans[i4].r(lastChildPosition, convertFocusDirectionToLayoutDirection);
                if (r3 != null && r3 != findContainingItemView) {
                    return r3;
                }
            }
        }
        boolean z2 = (this.mReverseLayout ^ true) == (convertFocusDirectionToLayoutDirection == -1);
        if (!z) {
            View findViewByPosition = findViewByPosition(z2 ? cVar.g() : cVar.j());
            if (findViewByPosition != null && findViewByPosition != findContainingItemView) {
                return findViewByPosition;
            }
        }
        if (preferLastSpan(convertFocusDirectionToLayoutDirection)) {
            for (int i5 = this.mSpanCount - 1; i5 >= 0; i5--) {
                if (i5 != cVar.e) {
                    c[] cVarArr = this.mSpans;
                    View findViewByPosition2 = findViewByPosition(z2 ? cVarArr[i5].g() : cVarArr[i5].j());
                    if (findViewByPosition2 != null && findViewByPosition2 != findContainingItemView) {
                        return findViewByPosition2;
                    }
                }
            }
        } else {
            for (int i6 = 0; i6 < this.mSpanCount; i6++) {
                c[] cVarArr2 = this.mSpans;
                View findViewByPosition3 = findViewByPosition(z2 ? cVarArr2[i6].g() : cVarArr2[i6].j());
                if (findViewByPosition3 != null && findViewByPosition3 != findContainingItemView) {
                    return findViewByPosition3;
                }
            }
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View findFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToStart(false);
            View findFirstVisibleItemClosestToEnd = findFirstVisibleItemClosestToEnd(false);
            if (findFirstVisibleItemClosestToStart == null || findFirstVisibleItemClosestToEnd == null) {
                return;
            }
            int position = getPosition(findFirstVisibleItemClosestToStart);
            int position2 = getPosition(findFirstVisibleItemClosestToEnd);
            if (position < position2) {
                accessibilityEvent.setFromIndex(position);
                accessibilityEvent.setToIndex(position2);
            } else {
                accessibilityEvent.setFromIndex(position2);
                accessibilityEvent.setToIndex(position);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int i2;
        int i3;
        int spanIndex;
        int i4;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        if (this.mOrientation == 0) {
            i2 = layoutParams2.getSpanIndex();
            i3 = layoutParams2.f ? this.mSpanCount : 1;
            spanIndex = -1;
            i4 = -1;
        } else {
            i2 = -1;
            i3 = -1;
            spanIndex = layoutParams2.getSpanIndex();
            i4 = layoutParams2.f ? this.mSpanCount : 1;
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(i2, i3, spanIndex, i4, false, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i2, int i3) {
        handleUpdate(i2, i3, 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        this.mLazySpanLookup.b();
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i2, int i3, int i4) {
        handleUpdate(i2, i3, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i2, int i3) {
        handleUpdate(i2, i3, 2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i2, int i3, Object obj) {
        handleUpdate(i2, i3, 4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        onLayoutChildren(recycler, state, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo.c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        int u;
        int startAfterPadding;
        int[] iArr;
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        savedState.h = this.mReverseLayout;
        savedState.f986i = this.mLastLayoutFromEnd;
        savedState.j = this.mLastLayoutRTL;
        LazySpanLookup lazySpanLookup = this.mLazySpanLookup;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.a) == null) {
            savedState.e = 0;
        } else {
            savedState.f = iArr;
            savedState.e = iArr.length;
            savedState.g = lazySpanLookup.b;
        }
        if (getChildCount() > 0) {
            savedState.a = this.mLastLayoutFromEnd ? getLastChildPosition() : getFirstChildPosition();
            savedState.b = findFirstVisibleItemPositionInt();
            int i2 = this.mSpanCount;
            savedState.f984c = i2;
            savedState.f985d = new int[i2];
            for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                if (this.mLastLayoutFromEnd) {
                    u = this.mSpans[i3].q(Integer.MIN_VALUE);
                    if (u != Integer.MIN_VALUE) {
                        startAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
                        u -= startAfterPadding;
                        savedState.f985d[i3] = u;
                    } else {
                        savedState.f985d[i3] = u;
                    }
                } else {
                    u = this.mSpans[i3].u(Integer.MIN_VALUE);
                    if (u != Integer.MIN_VALUE) {
                        startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
                        u -= startAfterPadding;
                        savedState.f985d[i3] = u;
                    } else {
                        savedState.f985d[i3] = u;
                    }
                }
            }
        } else {
            savedState.a = -1;
            savedState.b = -1;
            savedState.f984c = 0;
        }
        return savedState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onScrollStateChanged(int i2) {
        if (i2 == 0) {
            checkForGaps();
        }
    }

    void prepareLayoutStateForDelta(int i2, RecyclerView.State state) {
        int firstChildPosition;
        int i3;
        if (i2 > 0) {
            firstChildPosition = getLastChildPosition();
            i3 = 1;
        } else {
            firstChildPosition = getFirstChildPosition();
            i3 = -1;
        }
        this.mLayoutState.a = true;
        updateLayoutState(firstChildPosition, state);
        setLayoutStateDirection(i3);
        f fVar = this.mLayoutState;
        fVar.f1008c = firstChildPosition + fVar.f1009d;
        fVar.b = Math.abs(i2);
    }

    int scrollBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i2 == 0) {
            return 0;
        }
        prepareLayoutStateForDelta(i2, state);
        int fill = fill(recycler, this.mLayoutState, state);
        if (this.mLayoutState.b >= fill) {
            i2 = i2 < 0 ? -fill : fill;
        }
        this.mPrimaryOrientation.offsetChildren(-i2);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        f fVar = this.mLayoutState;
        fVar.b = 0;
        recycle(recycler, fVar);
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i2, recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i2) {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.a != i2) {
            savedState.a();
        }
        this.mPendingScrollPosition = i2;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i2, int i3) {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.a();
        }
        this.mPendingScrollPosition = i2;
        this.mPendingScrollPositionOffset = i3;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i2, recycler, state);
    }

    public void setGapStrategy(int i2) {
        assertNotInLayoutOrScroll(null);
        if (i2 == this.mGapStrategy) {
            return;
        }
        if (i2 != 0 && i2 != 2) {
            throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
        }
        this.mGapStrategy = i2;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void setMeasuredDimension(Rect rect, int i2, int i3) {
        int chooseSize;
        int chooseSize2;
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            chooseSize2 = RecyclerView.LayoutManager.chooseSize(i3, rect.height() + paddingTop, getMinimumHeight());
            chooseSize = RecyclerView.LayoutManager.chooseSize(i2, (this.mSizePerSpan * this.mSpanCount) + paddingLeft, getMinimumWidth());
        } else {
            chooseSize = RecyclerView.LayoutManager.chooseSize(i2, rect.width() + paddingLeft, getMinimumWidth());
            chooseSize2 = RecyclerView.LayoutManager.chooseSize(i3, (this.mSizePerSpan * this.mSpanCount) + paddingTop, getMinimumHeight());
        }
        setMeasuredDimension(chooseSize, chooseSize2);
    }

    public void setOrientation(int i2) {
        if (i2 != 0 && i2 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        assertNotInLayoutOrScroll(null);
        if (i2 == this.mOrientation) {
            return;
        }
        this.mOrientation = i2;
        OrientationHelper orientationHelper = this.mPrimaryOrientation;
        this.mPrimaryOrientation = this.mSecondaryOrientation;
        this.mSecondaryOrientation = orientationHelper;
        requestLayout();
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.h != z) {
            savedState.h = z;
        }
        this.mReverseLayout = z;
        requestLayout();
    }

    public void setSpanCount(int i2) {
        assertNotInLayoutOrScroll(null);
        if (i2 != this.mSpanCount) {
            invalidateSpanAssignments();
            this.mSpanCount = i2;
            this.mRemainingSpans = new BitSet(this.mSpanCount);
            this.mSpans = new c[this.mSpanCount];
            for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                this.mSpans[i3] = new c(i3);
            }
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i2);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }

    boolean updateAnchorFromPendingData(RecyclerView.State state, b bVar) {
        int i2;
        int startAfterPadding;
        int decoratedStart;
        if (!state.isPreLayout() && (i2 = this.mPendingScrollPosition) != -1) {
            if (i2 >= 0 && i2 < state.getItemCount()) {
                SavedState savedState = this.mPendingSavedState;
                if (savedState == null || savedState.a == -1 || savedState.f984c < 1) {
                    View findViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                    if (findViewByPosition != null) {
                        bVar.a = this.mShouldReverseLayout ? getLastChildPosition() : getFirstChildPosition();
                        if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                            if (bVar.f987c) {
                                startAfterPadding = this.mPrimaryOrientation.getEndAfterPadding() - this.mPendingScrollPositionOffset;
                                decoratedStart = this.mPrimaryOrientation.getDecoratedEnd(findViewByPosition);
                            } else {
                                startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding() + this.mPendingScrollPositionOffset;
                                decoratedStart = this.mPrimaryOrientation.getDecoratedStart(findViewByPosition);
                            }
                            bVar.b = startAfterPadding - decoratedStart;
                            return true;
                        }
                        if (this.mPrimaryOrientation.getDecoratedMeasurement(findViewByPosition) > this.mPrimaryOrientation.getTotalSpace()) {
                            bVar.b = bVar.f987c ? this.mPrimaryOrientation.getEndAfterPadding() : this.mPrimaryOrientation.getStartAfterPadding();
                            return true;
                        }
                        int decoratedStart2 = this.mPrimaryOrientation.getDecoratedStart(findViewByPosition) - this.mPrimaryOrientation.getStartAfterPadding();
                        if (decoratedStart2 < 0) {
                            bVar.b = -decoratedStart2;
                            return true;
                        }
                        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding() - this.mPrimaryOrientation.getDecoratedEnd(findViewByPosition);
                        if (endAfterPadding < 0) {
                            bVar.b = endAfterPadding;
                            return true;
                        }
                        bVar.b = Integer.MIN_VALUE;
                    } else {
                        int i3 = this.mPendingScrollPosition;
                        bVar.a = i3;
                        int i4 = this.mPendingScrollPositionOffset;
                        if (i4 == Integer.MIN_VALUE) {
                            bVar.f987c = calculateScrollDirectionForPosition(i3) == 1;
                            bVar.a();
                        } else {
                            bVar.b(i4);
                        }
                        bVar.f988d = true;
                    }
                } else {
                    bVar.b = Integer.MIN_VALUE;
                    bVar.a = this.mPendingScrollPosition;
                }
                return true;
            }
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        }
        return false;
    }

    void updateAnchorInfoForLayout(RecyclerView.State state, b bVar) {
        if (updateAnchorFromPendingData(state, bVar) || updateAnchorFromChildren(state, bVar)) {
            return;
        }
        bVar.a();
        bVar.a = 0;
    }

    void updateMeasureSpecs(int i2) {
        this.mSizePerSpan = i2 / this.mSpanCount;
        this.mFullSizeSpec = View.MeasureSpec.makeMeasureSpec(i2, this.mSecondaryOrientation.getMode());
    }
}
