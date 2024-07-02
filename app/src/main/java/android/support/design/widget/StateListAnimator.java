package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

/* loaded from: lib/Mus.dex */
final class StateListAnimator {
    private final ArrayList<Tuple> mTuples = new ArrayList<>();
    private Tuple mLastMatch = null;
    ValueAnimator mRunningAnimator = null;
    private final Animator.AnimatorListener mAnimationListener = new AnimatorListenerAdapter() { // from class: android.support.design.widget.StateListAnimator.1
        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (StateListAnimator.this.mRunningAnimator == animator) {
                StateListAnimator.this.mRunningAnimator = null;
            }
        }
    };

    public void addState(int[] iArr, ValueAnimator valueAnimator) {
        Tuple tuple = new Tuple(iArr, valueAnimator);
        valueAnimator.addListener(this.mAnimationListener);
        this.mTuples.add(tuple);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setState(int[] iArr) {
        Tuple tuple = null;
        int size = this.mTuples.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            Tuple tuple2 = this.mTuples.get(i2);
            if (!StateSet.stateSetMatches(tuple2.mSpecs, iArr)) {
                i2++;
            } else {
                tuple = tuple2;
                break;
            }
        }
        if (tuple != this.mLastMatch) {
            if (this.mLastMatch != null) {
                cancel();
            }
            this.mLastMatch = tuple;
            if (tuple != null) {
                start(tuple);
            }
        }
    }

    private void start(Tuple tuple) {
        this.mRunningAnimator = tuple.mAnimator;
        this.mRunningAnimator.start();
    }

    private void cancel() {
        if (this.mRunningAnimator != null) {
            this.mRunningAnimator.cancel();
            this.mRunningAnimator = null;
        }
    }

    public void jumpToCurrentState() {
        if (this.mRunningAnimator != null) {
            this.mRunningAnimator.end();
            this.mRunningAnimator = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: lib/Mus.dex */
    public static class Tuple {
        final ValueAnimator mAnimator;
        final int[] mSpecs;

        Tuple(int[] iArr, ValueAnimator valueAnimator) {
            this.mSpecs = iArr;
            this.mAnimator = valueAnimator;
        }
    }
}
