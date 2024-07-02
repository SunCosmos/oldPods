package androidx.core.view;

import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public class NestedScrollingChildHelper {
    private ViewParent a;
    private ViewParent b;

    /* renamed from: c, reason: collision with root package name */
    private final View f623c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f624d;
    private int[] e;

    public NestedScrollingChildHelper(@NonNull View view) {
        this.f623c = view;
    }

    private boolean a(int i2, int i3, int i4, int i5, @Nullable int[] iArr, int i6, @Nullable int[] iArr2) {
        ViewParent b;
        int i7;
        int i8;
        int[] iArr3;
        if (!isNestedScrollingEnabled() || (b = b(i6)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.f623c.getLocationInWindow(iArr);
            i7 = iArr[0];
            i8 = iArr[1];
        } else {
            i7 = 0;
            i8 = 0;
        }
        if (iArr2 == null) {
            int[] c2 = c();
            c2[0] = 0;
            c2[1] = 0;
            iArr3 = c2;
        } else {
            iArr3 = iArr2;
        }
        ViewParentCompat.onNestedScroll(b, this.f623c, i2, i3, i4, i5, i6, iArr3);
        if (iArr != null) {
            this.f623c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i7;
            iArr[1] = iArr[1] - i8;
        }
        return true;
    }

    private ViewParent b(int i2) {
        if (i2 == 0) {
            return this.a;
        }
        if (i2 != 1) {
            return null;
        }
        return this.b;
    }

    private int[] c() {
        if (this.e == null) {
            this.e = new int[2];
        }
        return this.e;
    }

    private void d(int i2, ViewParent viewParent) {
        if (i2 == 0) {
            this.a = viewParent;
        } else {
            if (i2 != 1) {
                return;
            }
            this.b = viewParent;
        }
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        ViewParent b;
        if (!isNestedScrollingEnabled() || (b = b(0)) == null) {
            return false;
        }
        return ViewParentCompat.onNestedFling(b, this.f623c, f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        ViewParent b;
        if (!isNestedScrollingEnabled() || (b = b(0)) == null) {
            return false;
        }
        return ViewParentCompat.onNestedPreFling(b, this.f623c, f, f2);
    }

    public boolean dispatchNestedPreScroll(int i2, int i3, @Nullable int[] iArr, @Nullable int[] iArr2) {
        return dispatchNestedPreScroll(i2, i3, iArr, iArr2, 0);
    }

    public boolean dispatchNestedPreScroll(int i2, int i3, @Nullable int[] iArr, @Nullable int[] iArr2, int i4) {
        ViewParent b;
        int i5;
        int i6;
        if (!isNestedScrollingEnabled() || (b = b(i4)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        if (iArr2 != null) {
            this.f623c.getLocationInWindow(iArr2);
            i5 = iArr2[0];
            i6 = iArr2[1];
        } else {
            i5 = 0;
            i6 = 0;
        }
        if (iArr == null) {
            iArr = c();
        }
        iArr[0] = 0;
        iArr[1] = 0;
        ViewParentCompat.onNestedPreScroll(b, this.f623c, i2, i3, iArr, i4);
        if (iArr2 != null) {
            this.f623c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i5;
            iArr2[1] = iArr2[1] - i6;
        }
        return (iArr[0] == 0 && iArr[1] == 0) ? false : true;
    }

    public void dispatchNestedScroll(int i2, int i3, int i4, int i5, @Nullable int[] iArr, int i6, @Nullable int[] iArr2) {
        a(i2, i3, i4, i5, iArr, i6, iArr2);
    }

    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, @Nullable int[] iArr) {
        return a(i2, i3, i4, i5, iArr, 0, null);
    }

    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, @Nullable int[] iArr, int i6) {
        return a(i2, i3, i4, i5, iArr, i6, null);
    }

    public boolean hasNestedScrollingParent() {
        return hasNestedScrollingParent(0);
    }

    public boolean hasNestedScrollingParent(int i2) {
        return b(i2) != null;
    }

    public boolean isNestedScrollingEnabled() {
        return this.f624d;
    }

    public void onDetachedFromWindow() {
        ViewCompat.stopNestedScroll(this.f623c);
    }

    public void onStopNestedScroll(@NonNull View view) {
        ViewCompat.stopNestedScroll(this.f623c);
    }

    public void setNestedScrollingEnabled(boolean z) {
        if (this.f624d) {
            ViewCompat.stopNestedScroll(this.f623c);
        }
        this.f624d = z;
    }

    public boolean startNestedScroll(int i2) {
        return startNestedScroll(i2, 0);
    }

    public boolean startNestedScroll(int i2, int i3) {
        if (hasNestedScrollingParent(i3)) {
            return true;
        }
        if (!isNestedScrollingEnabled()) {
            return false;
        }
        View view = this.f623c;
        for (ViewParent parent = this.f623c.getParent(); parent != null; parent = parent.getParent()) {
            if (ViewParentCompat.onStartNestedScroll(parent, view, this.f623c, i2, i3)) {
                d(i3, parent);
                ViewParentCompat.onNestedScrollAccepted(parent, view, this.f623c, i2, i3);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    public void stopNestedScroll(int i2) {
        ViewParent b = b(i2);
        if (b != null) {
            ViewParentCompat.onStopNestedScroll(b, this.f623c, i2);
            d(i2, null);
        }
    }
}
