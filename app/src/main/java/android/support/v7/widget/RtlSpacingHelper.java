package android.support.v7.widget;

/* loaded from: lib/Mus.dex */
class RtlSpacingHelper {
    public static final int UNDEFINED = Integer.MIN_VALUE;
    private int mLeft = 0;
    private int mRight = 0;
    private int mStart = Integer.MIN_VALUE;
    private int mEnd = Integer.MIN_VALUE;
    private int mExplicitLeft = 0;
    private int mExplicitRight = 0;
    private boolean mIsRtl = false;
    private boolean mIsRelative = false;

    public int getLeft() {
        return this.mLeft;
    }

    public int getRight() {
        return this.mRight;
    }

    public int getStart() {
        return this.mIsRtl ? this.mRight : this.mLeft;
    }

    public int getEnd() {
        return this.mIsRtl ? this.mLeft : this.mRight;
    }

    public void setRelative(int i2, int i3) {
        this.mStart = i2;
        this.mEnd = i3;
        this.mIsRelative = true;
        if (this.mIsRtl) {
            if (i3 != Integer.MIN_VALUE) {
                this.mLeft = i3;
            }
            if (i2 != Integer.MIN_VALUE) {
                this.mRight = i2;
                return;
            }
            return;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.mLeft = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.mRight = i3;
        }
    }

    public void setAbsolute(int i2, int i3) {
        this.mIsRelative = false;
        if (i2 != Integer.MIN_VALUE) {
            this.mExplicitLeft = i2;
            this.mLeft = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.mExplicitRight = i3;
            this.mRight = i3;
        }
    }

    public void setDirection(boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (z != this.mIsRtl) {
            this.mIsRtl = z;
            if (this.mIsRelative) {
                if (z) {
                    if (this.mEnd != Integer.MIN_VALUE) {
                        i4 = this.mEnd;
                    } else {
                        i4 = this.mExplicitLeft;
                    }
                    this.mLeft = i4;
                    if (this.mStart != Integer.MIN_VALUE) {
                        i5 = this.mStart;
                    } else {
                        i5 = this.mExplicitRight;
                    }
                    this.mRight = i5;
                    return;
                }
                if (this.mStart != Integer.MIN_VALUE) {
                    i2 = this.mStart;
                } else {
                    i2 = this.mExplicitLeft;
                }
                this.mLeft = i2;
                if (this.mEnd != Integer.MIN_VALUE) {
                    i3 = this.mEnd;
                } else {
                    i3 = this.mExplicitRight;
                }
                this.mRight = i3;
                return;
            }
            this.mLeft = this.mExplicitLeft;
            this.mRight = this.mExplicitRight;
        }
    }
}
