package android.support.v7.widget;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: lib/Mus.dex */
class ViewBoundsCheck {
    static final int CVE_PVE_POS = 12;
    static final int CVE_PVS_POS = 8;
    static final int CVS_PVE_POS = 4;
    static final int CVS_PVS_POS = 0;
    static final int EQ = 2;
    static final int FLAG_CVE_EQ_PVE = 8192;
    static final int FLAG_CVE_EQ_PVS = 512;
    static final int FLAG_CVE_GT_PVE = 4096;
    static final int FLAG_CVE_GT_PVS = 256;
    static final int FLAG_CVE_LT_PVE = 16384;
    static final int FLAG_CVE_LT_PVS = 1024;
    static final int FLAG_CVS_EQ_PVE = 32;
    static final int FLAG_CVS_EQ_PVS = 2;
    static final int FLAG_CVS_GT_PVE = 16;
    static final int FLAG_CVS_GT_PVS = 1;
    static final int FLAG_CVS_LT_PVE = 64;
    static final int FLAG_CVS_LT_PVS = 4;
    static final int GT = 1;
    static final int LT = 4;
    static final int MASK = 7;
    BoundFlags mBoundFlags = new BoundFlags();
    final Callback mCallback;

    /* loaded from: lib/Mus.dex */
    interface Callback {
        View getChildAt(int i2);

        int getChildCount();

        int getChildEnd(View view);

        int getChildStart(View view);

        View getParent();

        int getParentEnd();

        int getParentStart();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: lib/Mus.dex */
    public @interface ViewBounds {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewBoundsCheck(Callback callback) {
        this.mCallback = callback;
    }

    /* loaded from: lib/Mus.dex */
    static class BoundFlags {
        int mBoundFlags = 0;
        int mChildEnd;
        int mChildStart;
        int mRvEnd;
        int mRvStart;

        BoundFlags() {
        }

        void setBounds(int i2, int i3, int i4, int i5) {
            this.mRvStart = i2;
            this.mRvEnd = i3;
            this.mChildStart = i4;
            this.mChildEnd = i5;
        }

        void setFlags(int i2, int i3) {
            this.mBoundFlags = (this.mBoundFlags & (i3 ^ (-1))) | (i2 & i3);
        }

        void addFlags(int i2) {
            this.mBoundFlags |= i2;
        }

        void resetFlags() {
            this.mBoundFlags = 0;
        }

        int compare(int i2, int i3) {
            if (i2 > i3) {
                return 1;
            }
            if (i2 == i3) {
                return 2;
            }
            return 4;
        }

        boolean boundsMatch() {
            if ((this.mBoundFlags & 7) != 0 && (this.mBoundFlags & (compare(this.mChildStart, this.mRvStart) << 0)) == 0) {
                return false;
            }
            if ((this.mBoundFlags & 112) != 0 && (this.mBoundFlags & (compare(this.mChildStart, this.mRvEnd) << 4)) == 0) {
                return false;
            }
            if ((this.mBoundFlags & 1792) != 0 && (this.mBoundFlags & (compare(this.mChildEnd, this.mRvStart) << 8)) == 0) {
                return false;
            }
            if ((this.mBoundFlags & 28672) != 0 && (this.mBoundFlags & (compare(this.mChildEnd, this.mRvEnd) << 12)) == 0) {
                return false;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View findOneViewWithinBoundFlags(int i2, int i3, int i4, int i5) {
        int parentStart = this.mCallback.getParentStart();
        int parentEnd = this.mCallback.getParentEnd();
        int i6 = i3 > i2 ? 1 : -1;
        View view = null;
        int i7 = i2;
        while (true) {
            int i8 = i7;
            if (i8 != i3) {
                View childAt = this.mCallback.getChildAt(i8);
                this.mBoundFlags.setBounds(parentStart, parentEnd, this.mCallback.getChildStart(childAt), this.mCallback.getChildEnd(childAt));
                if (i4 != 0) {
                    this.mBoundFlags.resetFlags();
                    this.mBoundFlags.addFlags(i4);
                    if (this.mBoundFlags.boundsMatch()) {
                        return childAt;
                    }
                }
                if (i5 != 0) {
                    this.mBoundFlags.resetFlags();
                    this.mBoundFlags.addFlags(i5);
                    if (this.mBoundFlags.boundsMatch()) {
                        view = childAt;
                    }
                }
                i7 = i8 + i6;
            } else {
                return view;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isViewWithinBoundFlags(View view, int i2) {
        this.mBoundFlags.setBounds(this.mCallback.getParentStart(), this.mCallback.getParentEnd(), this.mCallback.getChildStart(view), this.mCallback.getChildEnd(view));
        if (i2 == 0) {
            return false;
        }
        this.mBoundFlags.resetFlags();
        this.mBoundFlags.addFlags(i2);
        return this.mBoundFlags.boundsMatch();
    }
}
