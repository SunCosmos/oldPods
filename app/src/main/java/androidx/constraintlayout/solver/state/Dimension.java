package androidx.constraintlayout.solver.state;

import android.support.v7.widget.ActivityChooserView;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* loaded from: classes.dex */
public class Dimension {
    int a;
    int b;

    /* renamed from: c */
    float f367c;

    /* renamed from: d */
    int f368d;
    Object e;
    boolean f;
    public static final Object FIXED_DIMENSION = new Object();
    public static final Object WRAP_DIMENSION = new Object();
    public static final Object SPREAD_DIMENSION = new Object();
    public static final Object PARENT_DIMENSION = new Object();
    public static final Object PERCENT_DIMENSION = new Object();

    /* loaded from: classes.dex */
    public enum Type {
        FIXED,
        WRAP,
        MATCH_PARENT,
        MATCH_CONSTRAINT
    }

    private Dimension() {
        this.a = 0;
        this.b = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f367c = 1.0f;
        this.f368d = 0;
        this.e = WRAP_DIMENSION;
        this.f = false;
    }

    private Dimension(Object obj) {
        this.a = 0;
        this.b = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.f367c = 1.0f;
        this.f368d = 0;
        this.e = WRAP_DIMENSION;
        this.f = false;
        this.e = obj;
    }

    public static Dimension Fixed(int i2) {
        Dimension dimension = new Dimension(FIXED_DIMENSION);
        dimension.fixed(i2);
        return dimension;
    }

    public static Dimension Fixed(Object obj) {
        Dimension dimension = new Dimension(FIXED_DIMENSION);
        dimension.fixed(obj);
        return dimension;
    }

    public static Dimension Parent() {
        return new Dimension(PARENT_DIMENSION);
    }

    public static Dimension Percent(Object obj, float f) {
        Dimension dimension = new Dimension(PERCENT_DIMENSION);
        dimension.percent(obj, f);
        return dimension;
    }

    public static Dimension Spread() {
        return new Dimension(SPREAD_DIMENSION);
    }

    public static Dimension Suggested(int i2) {
        Dimension dimension = new Dimension();
        dimension.suggested(i2);
        return dimension;
    }

    public static Dimension Suggested(Object obj) {
        Dimension dimension = new Dimension();
        dimension.suggested(obj);
        return dimension;
    }

    public static Dimension Wrap() {
        return new Dimension(WRAP_DIMENSION);
    }

    public int a() {
        return this.f368d;
    }

    public void apply(State state, ConstraintWidget constraintWidget, int i2) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour2;
        int i3 = 2;
        if (i2 == 0) {
            if (this.f) {
                constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                Object obj = this.e;
                if (obj == WRAP_DIMENSION) {
                    i3 = 1;
                } else if (obj != PERCENT_DIMENSION) {
                    i3 = 0;
                }
                constraintWidget.setHorizontalMatchStyle(i3, this.a, this.b, this.f367c);
                return;
            }
            int i4 = this.a;
            if (i4 > 0) {
                constraintWidget.setMinWidth(i4);
            }
            int i5 = this.b;
            if (i5 < Integer.MAX_VALUE) {
                constraintWidget.setMaxWidth(i5);
            }
            Object obj2 = this.e;
            if (obj2 == WRAP_DIMENSION) {
                dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            } else {
                if (obj2 != PARENT_DIMENSION) {
                    if (obj2 == null) {
                        constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                        constraintWidget.setWidth(this.f368d);
                        return;
                    }
                    return;
                }
                dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
            }
            constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour2);
            return;
        }
        if (this.f) {
            constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
            Object obj3 = this.e;
            if (obj3 == WRAP_DIMENSION) {
                i3 = 1;
            } else if (obj3 != PERCENT_DIMENSION) {
                i3 = 0;
            }
            constraintWidget.setVerticalMatchStyle(i3, this.a, this.b, this.f367c);
            return;
        }
        int i6 = this.a;
        if (i6 > 0) {
            constraintWidget.setMinHeight(i6);
        }
        int i7 = this.b;
        if (i7 < Integer.MAX_VALUE) {
            constraintWidget.setMaxHeight(i7);
        }
        Object obj4 = this.e;
        if (obj4 == WRAP_DIMENSION) {
            dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        } else {
            if (obj4 != PARENT_DIMENSION) {
                if (obj4 == null) {
                    constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    constraintWidget.setHeight(this.f368d);
                    return;
                }
                return;
            }
            dimensionBehaviour = ConstraintWidget.DimensionBehaviour.MATCH_PARENT;
        }
        constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour);
    }

    public Dimension fixed(int i2) {
        this.e = null;
        this.f368d = i2;
        return this;
    }

    public Dimension fixed(Object obj) {
        this.e = obj;
        if (obj instanceof Integer) {
            this.f368d = ((Integer) obj).intValue();
            this.e = null;
        }
        return this;
    }

    public Dimension max(int i2) {
        if (this.b >= 0) {
            this.b = i2;
        }
        return this;
    }

    public Dimension max(Object obj) {
        Object obj2 = WRAP_DIMENSION;
        if (obj == obj2 && this.f) {
            this.e = obj2;
            this.b = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        return this;
    }

    public Dimension min(int i2) {
        if (i2 >= 0) {
            this.a = i2;
        }
        return this;
    }

    public Dimension min(Object obj) {
        if (obj == WRAP_DIMENSION) {
            this.a = -2;
        }
        return this;
    }

    public Dimension percent(Object obj, float f) {
        this.f367c = f;
        return this;
    }

    public Dimension ratio(float f) {
        return this;
    }

    public Dimension suggested(int i2) {
        this.f = true;
        return this;
    }

    public Dimension suggested(Object obj) {
        this.e = obj;
        this.f = true;
        return this;
    }
}
