package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.HelperWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

/* loaded from: classes.dex */
public class Barrier extends ConstraintHelper {
    public static final int BOTTOM = 3;
    public static final int END = 6;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int START = 5;
    public static final int TOP = 2;
    private int j;
    private int k;
    private androidx.constraintlayout.solver.widgets.Barrier l;

    public Barrier(Context context) {
        super(context);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setVisibility(8);
    }

    public Barrier(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        super.setVisibility(8);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0013, code lost:
    
        if (r7 == 6) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x001f, code lost:
    
        if (r7 == 6) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0025, code lost:
    
        if (r7 == 6) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void k(androidx.constraintlayout.solver.widgets.ConstraintWidget r6, int r7, boolean r8) {
        /*
            r5 = this;
            r5.k = r7
            int r7 = android.os.Build.VERSION.SDK_INT
            r0 = 1
            r1 = 0
            r2 = 6
            r3 = 5
            r4 = 17
            if (r7 >= r4) goto L16
            int r7 = r5.j
            if (r7 != r3) goto L13
        L10:
            r5.k = r1
            goto L28
        L13:
            if (r7 != r2) goto L28
        L15:
            goto L1c
        L16:
            int r7 = r5.j
            if (r8 == 0) goto L22
            if (r7 != r3) goto L1f
        L1c:
            r5.k = r0
            goto L28
        L1f:
            if (r7 != r2) goto L28
            goto L10
        L22:
            if (r7 != r3) goto L25
            goto L10
        L25:
            if (r7 != r2) goto L28
            goto L15
        L28:
            boolean r7 = r6 instanceof androidx.constraintlayout.solver.widgets.Barrier
            if (r7 == 0) goto L33
            androidx.constraintlayout.solver.widgets.Barrier r6 = (androidx.constraintlayout.solver.widgets.Barrier) r6
            int r7 = r5.k
            r6.setBarrierType(r7)
        L33:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.Barrier.k(androidx.constraintlayout.solver.widgets.ConstraintWidget, int, boolean):void");
    }

    public boolean allowsGoneWidget() {
        return this.l.allowsGoneWidget();
    }

    public int getMargin() {
        return this.l.getMargin();
    }

    public int getType() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void j(AttributeSet attributeSet) {
        super.j(attributeSet);
        this.l = new androidx.constraintlayout.solver.widgets.Barrier();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.ConstraintLayout_Layout_barrierDirection) {
                    setType(obtainStyledAttributes.getInt(index, 0));
                } else if (index == R.styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.l.setAllowsGoneWidget(obtainStyledAttributes.getBoolean(index, true));
                } else if (index == R.styleable.ConstraintLayout_Layout_barrierMargin) {
                    this.l.setMargin(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.f421d = this.l;
        validateParams();
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void loadParameters(ConstraintSet.Constraint constraint, HelperWidget helperWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        super.loadParameters(constraint, helperWidget, layoutParams, sparseArray);
        if (helperWidget instanceof androidx.constraintlayout.solver.widgets.Barrier) {
            androidx.constraintlayout.solver.widgets.Barrier barrier = (androidx.constraintlayout.solver.widgets.Barrier) helperWidget;
            k(barrier, constraint.layout.mBarrierDirection, ((ConstraintWidgetContainer) helperWidget.getParent()).isRtl());
            barrier.setAllowsGoneWidget(constraint.layout.mBarrierAllowsGoneWidgets);
            barrier.setMargin(constraint.layout.mBarrierMargin);
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper
    public void resolveRtl(ConstraintWidget constraintWidget, boolean z) {
        k(constraintWidget, this.j, z);
    }

    public void setAllowsGoneWidget(boolean z) {
        this.l.setAllowsGoneWidget(z);
    }

    public void setDpMargin(int i2) {
        this.l.setMargin((int) ((i2 * getResources().getDisplayMetrics().density) + 0.5f));
    }

    public void setMargin(int i2) {
        this.l.setMargin(i2);
    }

    public void setType(int i2) {
        this.j = i2;
    }
}
