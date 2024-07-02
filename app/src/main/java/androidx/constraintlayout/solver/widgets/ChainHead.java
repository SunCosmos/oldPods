package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ChainHead {
    protected ConstraintWidget a;
    protected ConstraintWidget b;

    /* renamed from: c, reason: collision with root package name */
    protected ConstraintWidget f378c;

    /* renamed from: d, reason: collision with root package name */
    protected ConstraintWidget f379d;
    protected ConstraintWidget e;
    protected ConstraintWidget f;
    protected ConstraintWidget g;
    protected ArrayList<ConstraintWidget> h;

    /* renamed from: i, reason: collision with root package name */
    protected int f380i;
    protected int j;
    protected float k = 0.0f;
    int l;
    int m;
    int n;
    private int o;
    private boolean p;
    protected boolean q;
    protected boolean r;
    protected boolean s;
    private boolean t;

    public ChainHead(ConstraintWidget constraintWidget, int i2, boolean z) {
        this.p = false;
        this.a = constraintWidget;
        this.o = i2;
        this.p = z;
    }

    private void a() {
        int i2 = this.o * 2;
        ConstraintWidget constraintWidget = this.a;
        boolean z = false;
        ConstraintWidget constraintWidget2 = constraintWidget;
        boolean z2 = false;
        while (!z2) {
            this.f380i++;
            ConstraintWidget[] constraintWidgetArr = constraintWidget.a0;
            int i3 = this.o;
            ConstraintWidget constraintWidget3 = null;
            constraintWidgetArr[i3] = null;
            constraintWidget.Z[i3] = null;
            if (constraintWidget.getVisibility() != 8) {
                this.l++;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.getDimensionBehaviour(this.o);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour != dimensionBehaviour2) {
                    this.m += constraintWidget.getLength(this.o);
                }
                int margin = this.m + constraintWidget.mListAnchors[i2].getMargin();
                this.m = margin;
                int i4 = i2 + 1;
                this.m = margin + constraintWidget.mListAnchors[i4].getMargin();
                int margin2 = this.n + constraintWidget.mListAnchors[i2].getMargin();
                this.n = margin2;
                this.n = margin2 + constraintWidget.mListAnchors[i4].getMargin();
                if (this.b == null) {
                    this.b = constraintWidget;
                }
                this.f379d = constraintWidget;
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.mListDimensionBehaviors;
                int i5 = this.o;
                if (dimensionBehaviourArr[i5] == dimensionBehaviour2) {
                    int[] iArr = constraintWidget.mResolvedMatchConstraintDefault;
                    if (iArr[i5] == 0 || iArr[i5] == 3 || iArr[i5] == 2) {
                        this.j++;
                        float[] fArr = constraintWidget.mWeight;
                        float f = fArr[i5];
                        if (f > 0.0f) {
                            this.k += fArr[i5];
                        }
                        if (b(constraintWidget, i5)) {
                            if (f < 0.0f) {
                                this.q = true;
                            } else {
                                this.r = true;
                            }
                            if (this.h == null) {
                                this.h = new ArrayList<>();
                            }
                            this.h.add(constraintWidget);
                        }
                        if (this.f == null) {
                            this.f = constraintWidget;
                        }
                        ConstraintWidget constraintWidget4 = this.g;
                        if (constraintWidget4 != null) {
                            constraintWidget4.Z[this.o] = constraintWidget;
                        }
                        this.g = constraintWidget;
                    }
                    if (this.o == 0) {
                        if (constraintWidget.mMatchConstraintDefaultWidth == 0 && constraintWidget.mMatchConstraintMinWidth == 0) {
                            int i6 = constraintWidget.mMatchConstraintMaxWidth;
                        }
                    } else if (constraintWidget.mMatchConstraintDefaultHeight == 0 && constraintWidget.mMatchConstraintMinHeight == 0) {
                        int i7 = constraintWidget.mMatchConstraintMaxHeight;
                    }
                    int i8 = (constraintWidget.mDimensionRatio > 0.0f ? 1 : (constraintWidget.mDimensionRatio == 0.0f ? 0 : -1));
                }
            }
            if (constraintWidget2 != constraintWidget) {
                constraintWidget2.a0[this.o] = constraintWidget;
            }
            ConstraintAnchor constraintAnchor = constraintWidget.mListAnchors[i2 + 1].mTarget;
            if (constraintAnchor != null) {
                ConstraintWidget constraintWidget5 = constraintAnchor.mOwner;
                ConstraintAnchor[] constraintAnchorArr = constraintWidget5.mListAnchors;
                if (constraintAnchorArr[i2].mTarget != null && constraintAnchorArr[i2].mTarget.mOwner == constraintWidget) {
                    constraintWidget3 = constraintWidget5;
                }
            }
            if (constraintWidget3 == null) {
                constraintWidget3 = constraintWidget;
                z2 = true;
            }
            constraintWidget2 = constraintWidget;
            constraintWidget = constraintWidget3;
        }
        ConstraintWidget constraintWidget6 = this.b;
        if (constraintWidget6 != null) {
            this.m -= constraintWidget6.mListAnchors[i2].getMargin();
        }
        ConstraintWidget constraintWidget7 = this.f379d;
        if (constraintWidget7 != null) {
            this.m -= constraintWidget7.mListAnchors[i2 + 1].getMargin();
        }
        this.f378c = constraintWidget;
        if (this.o == 0 && this.p) {
            this.e = constraintWidget;
        } else {
            this.e = this.a;
        }
        if (this.r && this.q) {
            z = true;
        }
        this.s = z;
    }

    private static boolean b(ConstraintWidget constraintWidget, int i2) {
        if (constraintWidget.getVisibility() != 8 && constraintWidget.mListDimensionBehaviors[i2] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int[] iArr = constraintWidget.mResolvedMatchConstraintDefault;
            if (iArr[i2] == 0 || iArr[i2] == 3) {
                return true;
            }
        }
        return false;
    }

    public void define() {
        if (!this.t) {
            a();
        }
        this.t = true;
    }

    public ConstraintWidget getFirst() {
        return this.a;
    }

    public ConstraintWidget getFirstMatchConstraintWidget() {
        return this.f;
    }

    public ConstraintWidget getFirstVisibleWidget() {
        return this.b;
    }

    public ConstraintWidget getHead() {
        return this.e;
    }

    public ConstraintWidget getLast() {
        return this.f378c;
    }

    public ConstraintWidget getLastMatchConstraintWidget() {
        return this.g;
    }

    public ConstraintWidget getLastVisibleWidget() {
        return this.f379d;
    }

    public float getTotalWeight() {
        return this.k;
    }
}
