package android.support.v4.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;

/* loaded from: lib/Mus.dex */
public class ViewDragHelper {
    private static final int BASE_SETTLE_DURATION = 256;
    public static final int DIRECTION_ALL = 3;
    public static final int DIRECTION_HORIZONTAL = 1;
    public static final int DIRECTION_VERTICAL = 2;
    public static final int EDGE_ALL = 15;
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    private static final int EDGE_SIZE = 20;
    public static final int EDGE_TOP = 4;
    public static final int INVALID_POINTER = -1;
    private static final int MAX_SETTLE_DURATION = 600;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "ViewDragHelper";
    private static final Interpolator sInterpolator = new Interpolator() { // from class: android.support.v4.widget.ViewDragHelper.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    private final Callback mCallback;
    private View mCapturedView;
    private int mDragState;
    private int[] mEdgeDragsInProgress;
    private int[] mEdgeDragsLocked;
    private int mEdgeSize;
    private int[] mInitialEdgesTouched;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private float mMaxVelocity;
    private float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private OverScroller mScroller;
    private int mTouchSlop;
    private int mTrackingEdges;
    private VelocityTracker mVelocityTracker;
    private int mActivePointerId = -1;
    private final Runnable mSetIdleRunnable = new Runnable() { // from class: android.support.v4.widget.ViewDragHelper.2
        @Override // java.lang.Runnable
        public void run() {
            ViewDragHelper.this.setDragState(0);
        }
    };

    /* loaded from: lib/Mus.dex */
    public static abstract class Callback {
        public abstract boolean tryCaptureView(@NonNull View view, int i2);

        public void onViewDragStateChanged(int i2) {
        }

        public void onViewPositionChanged(@NonNull View view, int i2, int i3, int i4, int i5) {
        }

        public void onViewCaptured(@NonNull View view, int i2) {
        }

        public void onViewReleased(@NonNull View view, float f, float f2) {
        }

        public void onEdgeTouched(int i2, int i3) {
        }

        public boolean onEdgeLock(int i2) {
            return false;
        }

        public void onEdgeDragStarted(int i2, int i3) {
        }

        public int getOrderedChildIndex(int i2) {
            return i2;
        }

        public int getViewHorizontalDragRange(@NonNull View view) {
            return 0;
        }

        public int getViewVerticalDragRange(@NonNull View view) {
            return 0;
        }

        public int clampViewPositionHorizontal(@NonNull View view, int i2, int i3) {
            return 0;
        }

        public int clampViewPositionVertical(@NonNull View view, int i2, int i3) {
            return 0;
        }
    }

    public static ViewDragHelper create(@NonNull ViewGroup viewGroup, @NonNull Callback callback) {
        return new ViewDragHelper(viewGroup.getContext(), viewGroup, callback);
    }

    public static ViewDragHelper create(@NonNull ViewGroup viewGroup, float f, @NonNull Callback callback) {
        ViewDragHelper create = create(viewGroup, callback);
        create.mTouchSlop = (int) (create.mTouchSlop * (1.0f / f));
        return create;
    }

    private ViewDragHelper(@NonNull Context context, @NonNull ViewGroup viewGroup, @NonNull Callback callback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (callback == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.mParentView = viewGroup;
        this.mCallback = callback;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mEdgeSize = (int) ((20.0f * context.getResources().getDisplayMetrics().density) + 0.5f);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMaxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mMinVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mScroller = new OverScroller(context, sInterpolator);
    }

    public void setMinVelocity(float f) {
        this.mMinVelocity = f;
    }

    public float getMinVelocity() {
        return this.mMinVelocity;
    }

    public int getViewDragState() {
        return this.mDragState;
    }

    public void setEdgeTrackingEnabled(int i2) {
        this.mTrackingEdges = i2;
    }

    public int getEdgeSize() {
        return this.mEdgeSize;
    }

    public void captureChildView(@NonNull View view, int i2) {
        if (view.getParent() != this.mParentView) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.mParentView + ")");
        }
        this.mCapturedView = view;
        this.mActivePointerId = i2;
        this.mCallback.onViewCaptured(view, i2);
        setDragState(1);
    }

    @Nullable
    public View getCapturedView() {
        return this.mCapturedView;
    }

    public int getActivePointerId() {
        return this.mActivePointerId;
    }

    public int getTouchSlop() {
        return this.mTouchSlop;
    }

    public void cancel() {
        this.mActivePointerId = -1;
        clearMotionHistory();
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void abort() {
        cancel();
        if (this.mDragState == 2) {
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            this.mScroller.abortAnimation();
            int currX2 = this.mScroller.getCurrX();
            int currY2 = this.mScroller.getCurrY();
            this.mCallback.onViewPositionChanged(this.mCapturedView, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        setDragState(0);
    }

    public boolean smoothSlideViewTo(@NonNull View view, int i2, int i3) {
        this.mCapturedView = view;
        this.mActivePointerId = -1;
        boolean forceSettleCapturedViewAt = forceSettleCapturedViewAt(i2, i3, 0, 0);
        if (!forceSettleCapturedViewAt && this.mDragState == 0 && this.mCapturedView != null) {
            this.mCapturedView = null;
        }
        return forceSettleCapturedViewAt;
    }

    public boolean settleCapturedViewAt(int i2, int i3) {
        if (!this.mReleaseInProgress) {
            throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
        }
        return forceSettleCapturedViewAt(i2, i3, (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
    }

    private boolean forceSettleCapturedViewAt(int i2, int i3, int i4, int i5) {
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        int i6 = i2 - left;
        int i7 = i3 - top;
        if (i6 == 0 && i7 == 0) {
            this.mScroller.abortAnimation();
            setDragState(0);
            return false;
        }
        this.mScroller.startScroll(left, top, i6, i7, computeSettleDuration(this.mCapturedView, i6, i7, i4, i5));
        setDragState(2);
        return true;
    }

    private int computeSettleDuration(View view, int i2, int i3, int i4, int i5) {
        int clampMag = clampMag(i4, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int clampMag2 = clampMag(i5, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int abs = Math.abs(i2);
        int abs2 = Math.abs(i3);
        int abs3 = Math.abs(clampMag);
        int abs4 = Math.abs(clampMag2);
        int i6 = abs3 + abs4;
        int i7 = abs + abs2;
        return (int) ((computeAxisDuration(i2, clampMag, this.mCallback.getViewHorizontalDragRange(view)) * (clampMag != 0 ? abs3 / i6 : abs / i7)) + (computeAxisDuration(i3, clampMag2, this.mCallback.getViewVerticalDragRange(view)) * (clampMag2 != 0 ? abs4 / i6 : abs2 / i7)));
    }

    private int computeAxisDuration(int i2, int i3, int i4) {
        int abs;
        if (i2 == 0) {
            return 0;
        }
        int width = this.mParentView.getWidth();
        int i5 = width / 2;
        float distanceInfluenceForSnapDuration = i5 + (i5 * distanceInfluenceForSnapDuration(Math.min(1.0f, Math.abs(i2) / width)));
        int abs2 = Math.abs(i3);
        if (abs2 > 0) {
            abs = 4 * Math.round(1000.0f * Math.abs(distanceInfluenceForSnapDuration / abs2));
        } else {
            abs = (int) (((Math.abs(i2) / i4) + 1.0f) * 256.0f);
        }
        return Math.min(abs, MAX_SETTLE_DURATION);
    }

    private int clampMag(int i2, int i3, int i4) {
        int abs = Math.abs(i2);
        if (abs < i3) {
            return 0;
        }
        if (abs > i4) {
            return i2 > 0 ? i4 : -i4;
        }
        return i2;
    }

    private float clampMag(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        if (abs > f3) {
            return f > 0.0f ? f3 : -f3;
        }
        return f;
    }

    private float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((f - 0.5f) * 0.47123894f);
    }

    public void flingCapturedView(int i2, int i3, int i4, int i5) {
        if (!this.mReleaseInProgress) {
            throw new IllegalStateException("Cannot flingCapturedView outside of a call to Callback#onViewReleased");
        }
        this.mScroller.fling(this.mCapturedView.getLeft(), this.mCapturedView.getTop(), (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId), i2, i4, i3, i5);
        setDragState(2);
    }

    public boolean continueSettling(boolean z) {
        if (this.mDragState == 2) {
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            int left = currX - this.mCapturedView.getLeft();
            int top = currY - this.mCapturedView.getTop();
            if (left != 0) {
                ViewCompat.offsetLeftAndRight(this.mCapturedView, left);
            }
            if (top != 0) {
                ViewCompat.offsetTopAndBottom(this.mCapturedView, top);
            }
            if (left != 0 || top != 0) {
                this.mCallback.onViewPositionChanged(this.mCapturedView, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.mScroller.getFinalX() && currY == this.mScroller.getFinalY()) {
                this.mScroller.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                if (z) {
                    this.mParentView.post(this.mSetIdleRunnable);
                } else {
                    setDragState(0);
                }
            }
        }
        return this.mDragState == 2;
    }

    private void dispatchViewReleased(float f, float f2) {
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, f, f2);
        this.mReleaseInProgress = false;
        if (this.mDragState == 1) {
            setDragState(0);
        }
    }

    private void clearMotionHistory() {
        if (this.mInitialMotionX != null) {
            Arrays.fill(this.mInitialMotionX, 0.0f);
            Arrays.fill(this.mInitialMotionY, 0.0f);
            Arrays.fill(this.mLastMotionX, 0.0f);
            Arrays.fill(this.mLastMotionY, 0.0f);
            Arrays.fill(this.mInitialEdgesTouched, 0);
            Arrays.fill(this.mEdgeDragsInProgress, 0);
            Arrays.fill(this.mEdgeDragsLocked, 0);
            this.mPointersDown = 0;
        }
    }

    private void clearMotionHistory(int i2) {
        if (this.mInitialMotionX != null && isPointerDown(i2)) {
            this.mInitialMotionX[i2] = 0.0f;
            this.mInitialMotionY[i2] = 0.0f;
            this.mLastMotionX[i2] = 0.0f;
            this.mLastMotionY[i2] = 0.0f;
            this.mInitialEdgesTouched[i2] = 0;
            this.mEdgeDragsInProgress[i2] = 0;
            this.mEdgeDragsLocked[i2] = 0;
            this.mPointersDown &= (1 << i2) ^ (-1);
        }
    }

    private void ensureMotionHistorySizeForId(int i2) {
        if (this.mInitialMotionX == null || this.mInitialMotionX.length <= i2) {
            float[] fArr = new float[i2 + 1];
            float[] fArr2 = new float[i2 + 1];
            float[] fArr3 = new float[i2 + 1];
            float[] fArr4 = new float[i2 + 1];
            int[] iArr = new int[i2 + 1];
            int[] iArr2 = new int[i2 + 1];
            int[] iArr3 = new int[i2 + 1];
            if (this.mInitialMotionX != null) {
                System.arraycopy(this.mInitialMotionX, 0, fArr, 0, this.mInitialMotionX.length);
                System.arraycopy(this.mInitialMotionY, 0, fArr2, 0, this.mInitialMotionY.length);
                System.arraycopy(this.mLastMotionX, 0, fArr3, 0, this.mLastMotionX.length);
                System.arraycopy(this.mLastMotionY, 0, fArr4, 0, this.mLastMotionY.length);
                System.arraycopy(this.mInitialEdgesTouched, 0, iArr, 0, this.mInitialEdgesTouched.length);
                System.arraycopy(this.mEdgeDragsInProgress, 0, iArr2, 0, this.mEdgeDragsInProgress.length);
                System.arraycopy(this.mEdgeDragsLocked, 0, iArr3, 0, this.mEdgeDragsLocked.length);
            }
            this.mInitialMotionX = fArr;
            this.mInitialMotionY = fArr2;
            this.mLastMotionX = fArr3;
            this.mLastMotionY = fArr4;
            this.mInitialEdgesTouched = iArr;
            this.mEdgeDragsInProgress = iArr2;
            this.mEdgeDragsLocked = iArr3;
        }
    }

    private void saveInitialMotion(float f, float f2, int i2) {
        ensureMotionHistorySizeForId(i2);
        float[] fArr = this.mInitialMotionX;
        this.mLastMotionX[i2] = f;
        fArr[i2] = f;
        float[] fArr2 = this.mInitialMotionY;
        this.mLastMotionY[i2] = f2;
        fArr2[i2] = f2;
        this.mInitialEdgesTouched[i2] = getEdgesTouched((int) f, (int) f2);
        this.mPointersDown |= 1 << i2;
    }

    private void saveLastMotion(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i2 = 0; i2 < pointerCount; i2++) {
            int pointerId = motionEvent.getPointerId(i2);
            if (isValidPointerForActionMove(pointerId)) {
                float x = motionEvent.getX(i2);
                float y = motionEvent.getY(i2);
                this.mLastMotionX[pointerId] = x;
                this.mLastMotionY[pointerId] = y;
            }
        }
    }

    public boolean isPointerDown(int i2) {
        return (this.mPointersDown & (1 << i2)) != 0;
    }

    void setDragState(int i2) {
        this.mParentView.removeCallbacks(this.mSetIdleRunnable);
        if (this.mDragState != i2) {
            this.mDragState = i2;
            this.mCallback.onViewDragStateChanged(i2);
            if (this.mDragState == 0) {
                this.mCapturedView = null;
            }
        }
    }

    boolean tryCaptureViewForDrag(View view, int i2) {
        if (view == this.mCapturedView && this.mActivePointerId == i2) {
            return true;
        }
        if (view != null && this.mCallback.tryCaptureView(view, i2)) {
            this.mActivePointerId = i2;
            captureChildView(view, i2);
            return true;
        }
        return false;
    }

    protected boolean canScroll(@NonNull View view, boolean z, int i2, int i3, int i4, int i5) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (i4 + scrollX >= childAt.getLeft() && i4 + scrollX < childAt.getRight() && i5 + scrollY >= childAt.getTop() && i5 + scrollY < childAt.getBottom() && canScroll(childAt, true, i2, i3, (i4 + scrollX) - childAt.getLeft(), (i5 + scrollY) - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z && (view.canScrollHorizontally(-i2) || view.canScrollVertically(-i3));
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x02d9, code lost:
    
        if (r23 != r23) goto L67;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean shouldInterceptTouchEvent(@android.support.annotation.NonNull android.view.MotionEvent r28) {
        /*
            Method dump skipped, instructions count: 856
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.widget.ViewDragHelper.shouldInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public void processTouchEvent(@NonNull MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        switch (actionMasked) {
            case 0:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                int pointerId = motionEvent.getPointerId(0);
                View findTopChildUnder = findTopChildUnder((int) x, (int) y);
                saveInitialMotion(x, y, pointerId);
                tryCaptureViewForDrag(findTopChildUnder, pointerId);
                int i2 = this.mInitialEdgesTouched[pointerId];
                if ((i2 & this.mTrackingEdges) != 0) {
                    this.mCallback.onEdgeTouched(i2 & this.mTrackingEdges, pointerId);
                    return;
                }
                return;
            case 1:
                if (this.mDragState == 1) {
                    releaseViewForPointerUp();
                }
                cancel();
                return;
            case 2:
                if (this.mDragState == 1) {
                    if (isValidPointerForActionMove(this.mActivePointerId)) {
                        int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                        float x2 = motionEvent.getX(findPointerIndex);
                        float y2 = motionEvent.getY(findPointerIndex);
                        int i3 = (int) (x2 - this.mLastMotionX[this.mActivePointerId]);
                        int i4 = (int) (y2 - this.mLastMotionY[this.mActivePointerId]);
                        dragTo(this.mCapturedView.getLeft() + i3, this.mCapturedView.getTop() + i4, i3, i4);
                        saveLastMotion(motionEvent);
                        return;
                    }
                    return;
                }
                int pointerCount = motionEvent.getPointerCount();
                for (int i5 = 0; i5 < pointerCount; i5++) {
                    int pointerId2 = motionEvent.getPointerId(i5);
                    if (isValidPointerForActionMove(pointerId2)) {
                        float x3 = motionEvent.getX(i5);
                        float y3 = motionEvent.getY(i5);
                        float f = x3 - this.mInitialMotionX[pointerId2];
                        float f2 = y3 - this.mInitialMotionY[pointerId2];
                        reportNewEdgeDrags(f, f2, pointerId2);
                        if (this.mDragState != 1) {
                            View findTopChildUnder2 = findTopChildUnder((int) x3, (int) y3);
                            if (checkTouchSlop(findTopChildUnder2, f, f2) && tryCaptureViewForDrag(findTopChildUnder2, pointerId2)) {
                            }
                        }
                        saveLastMotion(motionEvent);
                        return;
                    }
                }
                saveLastMotion(motionEvent);
                return;
            case 3:
                if (this.mDragState == 1) {
                    dispatchViewReleased(0.0f, 0.0f);
                }
                cancel();
                return;
            case 4:
            default:
                return;
            case 5:
                int pointerId3 = motionEvent.getPointerId(actionIndex);
                float x4 = motionEvent.getX(actionIndex);
                float y4 = motionEvent.getY(actionIndex);
                saveInitialMotion(x4, y4, pointerId3);
                if (this.mDragState != 0) {
                    if (isCapturedViewUnder((int) x4, (int) y4)) {
                        tryCaptureViewForDrag(this.mCapturedView, pointerId3);
                        return;
                    }
                    return;
                } else {
                    tryCaptureViewForDrag(findTopChildUnder((int) x4, (int) y4), pointerId3);
                    int i6 = this.mInitialEdgesTouched[pointerId3];
                    if ((i6 & this.mTrackingEdges) != 0) {
                        this.mCallback.onEdgeTouched(i6 & this.mTrackingEdges, pointerId3);
                        return;
                    }
                    return;
                }
            case 6:
                int pointerId4 = motionEvent.getPointerId(actionIndex);
                if (this.mDragState == 1 && pointerId4 == this.mActivePointerId) {
                    int i7 = -1;
                    int pointerCount2 = motionEvent.getPointerCount();
                    int i8 = 0;
                    while (true) {
                        if (i8 < pointerCount2) {
                            int pointerId5 = motionEvent.getPointerId(i8);
                            if (pointerId5 != this.mActivePointerId) {
                                if (findTopChildUnder((int) motionEvent.getX(i8), (int) motionEvent.getY(i8)) == this.mCapturedView && tryCaptureViewForDrag(this.mCapturedView, pointerId5)) {
                                    i7 = this.mActivePointerId;
                                }
                            }
                            i8++;
                        }
                    }
                    if (i7 == -1) {
                        releaseViewForPointerUp();
                    }
                }
                clearMotionHistory(pointerId4);
                return;
        }
    }

    private void reportNewEdgeDrags(float f, float f2, int i2) {
        int i3 = 0;
        if (checkNewEdgeDrag(f, f2, i2, 1)) {
            i3 = 0 | 1;
        }
        if (checkNewEdgeDrag(f2, f, i2, 4)) {
            i3 |= 4;
        }
        if (checkNewEdgeDrag(f, f2, i2, 2)) {
            i3 |= 2;
        }
        if (checkNewEdgeDrag(f2, f, i2, 8)) {
            i3 |= 8;
        }
        if (i3 != 0) {
            int[] iArr = this.mEdgeDragsInProgress;
            iArr[i2] = iArr[i2] | i3;
            this.mCallback.onEdgeDragStarted(i3, i2);
        }
    }

    private boolean checkNewEdgeDrag(float f, float f2, int i2, int i3) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if ((this.mInitialEdgesTouched[i2] & i3) == i3 && (this.mTrackingEdges & i3) != 0 && (this.mEdgeDragsLocked[i2] & i3) != i3 && (this.mEdgeDragsInProgress[i2] & i3) != i3 && (abs > this.mTouchSlop || abs2 > this.mTouchSlop)) {
            if (abs < abs2 * 0.5f && this.mCallback.onEdgeLock(i3)) {
                int[] iArr = this.mEdgeDragsLocked;
                iArr[i2] = iArr[i2] | i3;
                return false;
            }
            return (this.mEdgeDragsInProgress[i2] & i3) == 0 && abs > ((float) this.mTouchSlop);
        }
        return false;
    }

    private boolean checkTouchSlop(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z = this.mCallback.getViewHorizontalDragRange(view) > 0;
        boolean z2 = this.mCallback.getViewVerticalDragRange(view) > 0;
        if (z && z2) {
            return (f * f) + (f2 * f2) > ((float) (this.mTouchSlop * this.mTouchSlop));
        }
        if (z) {
            return Math.abs(f) > ((float) this.mTouchSlop);
        }
        if (z2) {
            return Math.abs(f2) > ((float) this.mTouchSlop);
        }
        return false;
    }

    public boolean checkTouchSlop(int i2) {
        int length = this.mInitialMotionX.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (checkTouchSlop(i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkTouchSlop(int i2, int i3) {
        if (!isPointerDown(i3)) {
            return false;
        }
        boolean z = (i2 & 1) == 1;
        boolean z2 = (i2 & 2) == 2;
        float f = this.mLastMotionX[i3] - this.mInitialMotionX[i3];
        float f2 = this.mLastMotionY[i3] - this.mInitialMotionY[i3];
        if (z && z2) {
            return (f * f) + (f2 * f2) > ((float) (this.mTouchSlop * this.mTouchSlop));
        }
        if (z) {
            return Math.abs(f) > ((float) this.mTouchSlop);
        }
        if (z2) {
            return Math.abs(f2) > ((float) this.mTouchSlop);
        }
        return false;
    }

    public boolean isEdgeTouched(int i2) {
        int length = this.mInitialEdgesTouched.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (isEdgeTouched(i2, i3)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEdgeTouched(int i2, int i3) {
        return isPointerDown(i3) && (this.mInitialEdgesTouched[i3] & i2) != 0;
    }

    private void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        dispatchViewReleased(clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
    }

    private void dragTo(int i2, int i3, int i4, int i5) {
        int i6 = i2;
        int i7 = i3;
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        if (i4 != 0) {
            i6 = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, i2, i4);
            ViewCompat.offsetLeftAndRight(this.mCapturedView, i6 - left);
        }
        if (i5 != 0) {
            i7 = this.mCallback.clampViewPositionVertical(this.mCapturedView, i3, i5);
            ViewCompat.offsetTopAndBottom(this.mCapturedView, i7 - top);
        }
        if (i4 != 0 || i5 != 0) {
            this.mCallback.onViewPositionChanged(this.mCapturedView, i6, i7, i6 - left, i7 - top);
        }
    }

    public boolean isCapturedViewUnder(int i2, int i3) {
        return isViewUnder(this.mCapturedView, i2, i3);
    }

    public boolean isViewUnder(@Nullable View view, int i2, int i3) {
        if (view == null) {
            return false;
        }
        return i2 >= view.getLeft() && i2 < view.getRight() && i3 >= view.getTop() && i3 < view.getBottom();
    }

    @Nullable
    public View findTopChildUnder(int i2, int i3) {
        for (int childCount = this.mParentView.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.mParentView.getChildAt(this.mCallback.getOrderedChildIndex(childCount));
            if (i2 >= childAt.getLeft() && i2 < childAt.getRight() && i3 >= childAt.getTop() && i3 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private int getEdgesTouched(int i2, int i3) {
        int i4 = 0;
        if (i2 < this.mParentView.getLeft() + this.mEdgeSize) {
            i4 = 0 | 1;
        }
        if (i3 < this.mParentView.getTop() + this.mEdgeSize) {
            i4 |= 4;
        }
        if (i2 > this.mParentView.getRight() - this.mEdgeSize) {
            i4 |= 2;
        }
        if (i3 > this.mParentView.getBottom() - this.mEdgeSize) {
            i4 |= 8;
        }
        return i4;
    }

    private boolean isValidPointerForActionMove(int i2) {
        if (isPointerDown(i2)) {
            return true;
        }
        Log.e(TAG, "Ignoring pointerId=" + i2 + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }
}
