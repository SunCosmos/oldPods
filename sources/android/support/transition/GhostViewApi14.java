package android.support.transition;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.transition.GhostViewImpl;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

@RequiresApi(14)
@SuppressLint({"ViewConstructor"})
/* loaded from: lib/Mus.dex */
class GhostViewApi14 extends View implements GhostViewImpl {
    Matrix mCurrentMatrix;
    private int mDeltaX;
    private int mDeltaY;
    private final Matrix mMatrix;
    private final ViewTreeObserver.OnPreDrawListener mOnPreDrawListener;
    int mReferences;
    ViewGroup mStartParent;
    View mStartView;
    final View mView;

    /* loaded from: lib/Mus.dex */
    static class Creator implements GhostViewImpl.Creator {
        @Override // android.support.transition.GhostViewImpl.Creator
        public GhostViewImpl addGhost(View view, ViewGroup viewGroup, Matrix matrix) {
            GhostViewApi14 ghostView = GhostViewApi14.getGhostView(view);
            if (ghostView == null) {
                FrameLayout findFrameLayout = findFrameLayout(viewGroup);
                if (findFrameLayout == null) {
                    return null;
                }
                ghostView = new GhostViewApi14(view);
                findFrameLayout.addView(ghostView);
            }
            ghostView.mReferences++;
            return ghostView;
        }

        @Override // android.support.transition.GhostViewImpl.Creator
        public void removeGhost(View view) {
            GhostViewApi14 ghostView = GhostViewApi14.getGhostView(view);
            if (ghostView != null) {
                ghostView.mReferences--;
                if (ghostView.mReferences <= 0) {
                    ViewParent parent = ghostView.getParent();
                    if (parent instanceof ViewGroup) {
                        ViewGroup viewGroup = (ViewGroup) parent;
                        viewGroup.endViewTransition(ghostView);
                        viewGroup.removeView(ghostView);
                    }
                }
            }
        }

        private static FrameLayout findFrameLayout(ViewGroup viewGroup) {
            ViewGroup viewGroup2 = viewGroup;
            while (!(viewGroup2 instanceof FrameLayout)) {
                ViewParent parent = viewGroup2.getParent();
                if (!(parent instanceof ViewGroup)) {
                    return null;
                }
                viewGroup2 = (ViewGroup) parent;
            }
            return (FrameLayout) viewGroup2;
        }
    }

    GhostViewApi14(View view) {
        super(view.getContext());
        this.mMatrix = new Matrix();
        this.mOnPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: android.support.transition.GhostViewApi14.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                GhostViewApi14.this.mCurrentMatrix = GhostViewApi14.this.mView.getMatrix();
                ViewCompat.postInvalidateOnAnimation(GhostViewApi14.this);
                if (GhostViewApi14.this.mStartParent != null && GhostViewApi14.this.mStartView != null) {
                    GhostViewApi14.this.mStartParent.endViewTransition(GhostViewApi14.this.mStartView);
                    ViewCompat.postInvalidateOnAnimation(GhostViewApi14.this.mStartParent);
                    GhostViewApi14.this.mStartParent = null;
                    GhostViewApi14.this.mStartView = null;
                }
                return true;
            }
        };
        this.mView = view;
        setLayerType(2, null);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setGhostView(this.mView, this);
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        this.mView.getLocationOnScreen(r3);
        int[] iArr2 = {(int) (iArr2[0] - this.mView.getTranslationX()), (int) (iArr2[1] - this.mView.getTranslationY())};
        this.mDeltaX = iArr2[0] - iArr[0];
        this.mDeltaY = iArr2[1] - iArr[1];
        this.mView.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        this.mView.setVisibility(4);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        this.mView.setVisibility(0);
        setGhostView(this.mView, null);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        this.mMatrix.set(this.mCurrentMatrix);
        this.mMatrix.postTranslate(this.mDeltaX, this.mDeltaY);
        canvas.setMatrix(this.mMatrix);
        this.mView.draw(canvas);
    }

    @Override // android.view.View, android.support.transition.GhostViewImpl
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        this.mView.setVisibility(i2 == 0 ? 4 : 0);
    }

    @Override // android.support.transition.GhostViewImpl
    public void reserveEndViewTransition(ViewGroup viewGroup, View view) {
        this.mStartParent = viewGroup;
        this.mStartView = view;
    }

    private static void setGhostView(@NonNull View view, GhostViewApi14 ghostViewApi14) {
        view.setTag(R.id.ghost_view, ghostViewApi14);
    }

    static GhostViewApi14 getGhostView(@NonNull View view) {
        return (GhostViewApi14) view.getTag(R.id.ghost_view);
    }
}
