package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: lib/Mus.dex */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5f;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0f;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5f;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    private boolean mInterceptingEvents;
    OnDismissListener mListener;
    private boolean mSensitivitySet;
    ViewDragHelper mViewDragHelper;
    private float mSensitivity = 0.0f;
    int mSwipeDirection = 2;
    float mDragDismissThreshold = 0.5f;
    float mAlphaStartSwipeDistance = 0.0f;
    float mAlphaEndSwipeDistance = 0.5f;
    private final ViewDragHelper.Callback mDragCallback = new ViewDragHelper.Callback() { // from class: android.support.design.widget.SwipeDismissBehavior.1
        private static final int INVALID_POINTER_ID = -1;
        private int mActivePointerId = -1;
        private int mOriginalCapturedViewLeft;

        AnonymousClass1() {
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            return this.mActivePointerId == -1 && SwipeDismissBehavior.this.canSwipeDismissView(view);
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public void onViewCaptured(View view, int i2) {
            this.mActivePointerId = i2;
            this.mOriginalCapturedViewLeft = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i2) {
            if (SwipeDismissBehavior.this.mListener != null) {
                SwipeDismissBehavior.this.mListener.onDragStateChanged(i2);
            }
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f, float f2) {
            int i2;
            int i3;
            this.mActivePointerId = -1;
            int width = view.getWidth();
            boolean z = false;
            if (shouldDismiss(view, f)) {
                if (view.getLeft() < this.mOriginalCapturedViewLeft) {
                    i3 = this.mOriginalCapturedViewLeft - width;
                } else {
                    i3 = this.mOriginalCapturedViewLeft + width;
                }
                i2 = i3;
                z = true;
            } else {
                i2 = this.mOriginalCapturedViewLeft;
            }
            if (SwipeDismissBehavior.this.mViewDragHelper.settleCapturedViewAt(i2, view.getTop())) {
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, z));
            } else if (z && SwipeDismissBehavior.this.mListener != null) {
                SwipeDismissBehavior.this.mListener.onDismiss(view);
            }
        }

        private boolean shouldDismiss(View view, float f) {
            if (f != 0.0f) {
                boolean z = ViewCompat.getLayoutDirection(view) == 1;
                if (SwipeDismissBehavior.this.mSwipeDirection == 2) {
                    return true;
                }
                if (SwipeDismissBehavior.this.mSwipeDirection == 0) {
                    return z ? f < 0.0f : f > 0.0f;
                }
                if (SwipeDismissBehavior.this.mSwipeDirection == 1) {
                    return z ? f > 0.0f : f < 0.0f;
                }
                return false;
            }
            return Math.abs(view.getLeft() - this.mOriginalCapturedViewLeft) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.mDragDismissThreshold);
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i2, int i3) {
            int width;
            int width2;
            boolean z = ViewCompat.getLayoutDirection(view) == 1;
            if (SwipeDismissBehavior.this.mSwipeDirection == 0) {
                if (z) {
                    width = this.mOriginalCapturedViewLeft - view.getWidth();
                    width2 = this.mOriginalCapturedViewLeft;
                } else {
                    width = this.mOriginalCapturedViewLeft;
                    width2 = this.mOriginalCapturedViewLeft + view.getWidth();
                }
            } else if (SwipeDismissBehavior.this.mSwipeDirection == 1) {
                if (z) {
                    width = this.mOriginalCapturedViewLeft;
                    width2 = this.mOriginalCapturedViewLeft + view.getWidth();
                } else {
                    width = this.mOriginalCapturedViewLeft - view.getWidth();
                    width2 = this.mOriginalCapturedViewLeft;
                }
            } else {
                width = this.mOriginalCapturedViewLeft - view.getWidth();
                width2 = this.mOriginalCapturedViewLeft + view.getWidth();
            }
            return SwipeDismissBehavior.clamp(width, i2, width2);
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i2, int i3) {
            return view.getTop();
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            float width = this.mOriginalCapturedViewLeft + (view.getWidth() * SwipeDismissBehavior.this.mAlphaStartSwipeDistance);
            float width2 = this.mOriginalCapturedViewLeft + (view.getWidth() * SwipeDismissBehavior.this.mAlphaEndSwipeDistance);
            if (i2 <= width) {
                view.setAlpha(1.0f);
            } else if (i2 >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.clamp(0.0f, 1.0f - SwipeDismissBehavior.fraction(width, width2, i2), 1.0f));
            }
        }
    };

    /* loaded from: lib/Mus.dex */
    public interface OnDismissListener {
        void onDismiss(View view);

        void onDragStateChanged(int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    private @interface SwipeDirection {
    }

    public void setListener(OnDismissListener onDismissListener) {
        this.mListener = onDismissListener;
    }

    public void setSwipeDirection(int i2) {
        this.mSwipeDirection = i2;
    }

    public void setDragDismissDistance(float f) {
        this.mDragDismissThreshold = clamp(0.0f, f, 1.0f);
    }

    public void setStartAlphaSwipeDistance(float f) {
        this.mAlphaStartSwipeDistance = clamp(0.0f, f, 1.0f);
    }

    public void setEndAlphaSwipeDistance(float f) {
        this.mAlphaEndSwipeDistance = clamp(0.0f, f, 1.0f);
    }

    public void setSensitivity(float f) {
        this.mSensitivity = f;
        this.mSensitivitySet = true;
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        boolean z = this.mInterceptingEvents;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.mInterceptingEvents = coordinatorLayout.isPointInChildBounds(v, (int) motionEvent.getX(), (int) motionEvent.getY());
                z = this.mInterceptingEvents;
                break;
            case 1:
            case 3:
                this.mInterceptingEvents = false;
                break;
        }
        if (z) {
            ensureViewDragHelper(coordinatorLayout);
            return this.mViewDragHelper.shouldInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.mViewDragHelper == null) {
            return false;
        }
        this.mViewDragHelper.processTouchEvent(motionEvent);
        return true;
    }

    public boolean canSwipeDismissView(@NonNull View view) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.support.design.widget.SwipeDismissBehavior$1 */
    /* loaded from: lib/Mus.dex */
    public class AnonymousClass1 extends ViewDragHelper.Callback {
        private static final int INVALID_POINTER_ID = -1;
        private int mActivePointerId = -1;
        private int mOriginalCapturedViewLeft;

        AnonymousClass1() {
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i2) {
            return this.mActivePointerId == -1 && SwipeDismissBehavior.this.canSwipeDismissView(view);
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public void onViewCaptured(View view, int i2) {
            this.mActivePointerId = i2;
            this.mOriginalCapturedViewLeft = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i2) {
            if (SwipeDismissBehavior.this.mListener != null) {
                SwipeDismissBehavior.this.mListener.onDragStateChanged(i2);
            }
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f, float f2) {
            int i2;
            int i3;
            this.mActivePointerId = -1;
            int width = view.getWidth();
            boolean z = false;
            if (shouldDismiss(view, f)) {
                if (view.getLeft() < this.mOriginalCapturedViewLeft) {
                    i3 = this.mOriginalCapturedViewLeft - width;
                } else {
                    i3 = this.mOriginalCapturedViewLeft + width;
                }
                i2 = i3;
                z = true;
            } else {
                i2 = this.mOriginalCapturedViewLeft;
            }
            if (SwipeDismissBehavior.this.mViewDragHelper.settleCapturedViewAt(i2, view.getTop())) {
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, z));
            } else if (z && SwipeDismissBehavior.this.mListener != null) {
                SwipeDismissBehavior.this.mListener.onDismiss(view);
            }
        }

        private boolean shouldDismiss(View view, float f) {
            if (f != 0.0f) {
                boolean z = ViewCompat.getLayoutDirection(view) == 1;
                if (SwipeDismissBehavior.this.mSwipeDirection == 2) {
                    return true;
                }
                if (SwipeDismissBehavior.this.mSwipeDirection == 0) {
                    return z ? f < 0.0f : f > 0.0f;
                }
                if (SwipeDismissBehavior.this.mSwipeDirection == 1) {
                    return z ? f > 0.0f : f < 0.0f;
                }
                return false;
            }
            return Math.abs(view.getLeft() - this.mOriginalCapturedViewLeft) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.mDragDismissThreshold);
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i2, int i3) {
            int width;
            int width2;
            boolean z = ViewCompat.getLayoutDirection(view) == 1;
            if (SwipeDismissBehavior.this.mSwipeDirection == 0) {
                if (z) {
                    width = this.mOriginalCapturedViewLeft - view.getWidth();
                    width2 = this.mOriginalCapturedViewLeft;
                } else {
                    width = this.mOriginalCapturedViewLeft;
                    width2 = this.mOriginalCapturedViewLeft + view.getWidth();
                }
            } else if (SwipeDismissBehavior.this.mSwipeDirection == 1) {
                if (z) {
                    width = this.mOriginalCapturedViewLeft;
                    width2 = this.mOriginalCapturedViewLeft + view.getWidth();
                } else {
                    width = this.mOriginalCapturedViewLeft - view.getWidth();
                    width2 = this.mOriginalCapturedViewLeft;
                }
            } else {
                width = this.mOriginalCapturedViewLeft - view.getWidth();
                width2 = this.mOriginalCapturedViewLeft + view.getWidth();
            }
            return SwipeDismissBehavior.clamp(width, i2, width2);
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i2, int i3) {
            return view.getTop();
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i2, int i3, int i4, int i5) {
            float width = this.mOriginalCapturedViewLeft + (view.getWidth() * SwipeDismissBehavior.this.mAlphaStartSwipeDistance);
            float width2 = this.mOriginalCapturedViewLeft + (view.getWidth() * SwipeDismissBehavior.this.mAlphaEndSwipeDistance);
            if (i2 <= width) {
                view.setAlpha(1.0f);
            } else if (i2 >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.clamp(0.0f, 1.0f - SwipeDismissBehavior.fraction(width, width2, i2), 1.0f));
            }
        }
    }

    private void ensureViewDragHelper(ViewGroup viewGroup) {
        ViewDragHelper create;
        if (this.mViewDragHelper == null) {
            if (this.mSensitivitySet) {
                create = ViewDragHelper.create(viewGroup, this.mSensitivity, this.mDragCallback);
            } else {
                create = ViewDragHelper.create(viewGroup, this.mDragCallback);
            }
            this.mViewDragHelper = create;
        }
    }

    /* loaded from: lib/Mus.dex */
    private class SettleRunnable implements Runnable {
        private final boolean mDismiss;
        private final View mView;

        SettleRunnable(View view, boolean z) {
            this.mView = view;
            this.mDismiss = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SwipeDismissBehavior.this.mViewDragHelper != null && SwipeDismissBehavior.this.mViewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.mView, this);
            } else if (this.mDismiss && SwipeDismissBehavior.this.mListener != null) {
                SwipeDismissBehavior.this.mListener.onDismiss(this.mView);
            }
        }
    }

    static float clamp(float f, float f2, float f3) {
        return Math.min(Math.max(f, f2), f3);
    }

    static int clamp(int i2, int i3, int i4) {
        return Math.min(Math.max(i2, i3), i4);
    }

    public int getDragState() {
        return this.mViewDragHelper != null ? this.mViewDragHelper.getViewDragState() : 0;
    }

    static float fraction(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }
}
