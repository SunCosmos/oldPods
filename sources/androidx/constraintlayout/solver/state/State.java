package androidx.constraintlayout.solver.state;

import androidx.constraintlayout.solver.state.helpers.AlignHorizontallyReference;
import androidx.constraintlayout.solver.state.helpers.AlignVerticallyReference;
import androidx.constraintlayout.solver.state.helpers.BarrierReference;
import androidx.constraintlayout.solver.state.helpers.GuidelineReference;
import androidx.constraintlayout.solver.state.helpers.HorizontalChainReference;
import androidx.constraintlayout.solver.state.helpers.VerticalChainReference;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.HelperWidget;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class State {
    public static final Integer PARENT = 0;
    protected HashMap<Object, Reference> a = new HashMap<>();
    protected HashMap<Object, HelperReference> b = new HashMap<>();

    /* renamed from: c */
    private int f371c;
    public final ConstraintReference mParent;

    /* loaded from: classes.dex */
    public enum Chain {
        SPREAD,
        SPREAD_INSIDE,
        PACKED
    }

    /* loaded from: classes.dex */
    public enum Constraint {
        LEFT_TO_LEFT,
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT,
        RIGHT_TO_RIGHT,
        START_TO_START,
        START_TO_END,
        END_TO_START,
        END_TO_END,
        TOP_TO_TOP,
        TOP_TO_BOTTOM,
        BOTTOM_TO_TOP,
        BOTTOM_TO_BOTTOM,
        BASELINE_TO_BASELINE,
        CENTER_HORIZONTALLY,
        CENTER_VERTICALLY
    }

    /* loaded from: classes.dex */
    public enum Direction {
        LEFT,
        RIGHT,
        START,
        END,
        TOP,
        BOTTOM
    }

    /* loaded from: classes.dex */
    public enum Helper {
        HORIZONTAL_CHAIN,
        VERTICAL_CHAIN,
        ALIGN_HORIZONTALLY,
        ALIGN_VERTICALLY,
        BARRIER,
        LAYER,
        FLOW
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[Helper.values().length];
            a = iArr;
            try {
                iArr[Helper.HORIZONTAL_CHAIN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Helper.VERTICAL_CHAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[Helper.ALIGN_HORIZONTALLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[Helper.ALIGN_VERTICALLY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[Helper.BARRIER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public State() {
        ConstraintReference constraintReference = new ConstraintReference(this);
        this.mParent = constraintReference;
        this.f371c = 0;
        this.a.put(PARENT, constraintReference);
    }

    private String a() {
        StringBuilder sb = new StringBuilder();
        sb.append("__HELPER_KEY_");
        int i2 = this.f371c;
        this.f371c = i2 + 1;
        sb.append(i2);
        sb.append("__");
        return sb.toString();
    }

    public void apply(ConstraintWidgetContainer constraintWidgetContainer) {
        constraintWidgetContainer.removeAllChildren();
        this.mParent.getWidth().apply(this, constraintWidgetContainer, 0);
        this.mParent.getHeight().apply(this, constraintWidgetContainer, 1);
        for (Object obj : this.b.keySet()) {
            HelperWidget helperWidget = this.b.get(obj).getHelperWidget();
            if (helperWidget != null) {
                Reference reference = this.a.get(obj);
                if (reference == null) {
                    reference = constraints(obj);
                }
                reference.setConstraintWidget(helperWidget);
            }
        }
        Iterator<Object> it = this.a.keySet().iterator();
        while (it.hasNext()) {
            Reference reference2 = this.a.get(it.next());
            if (reference2 != this.mParent) {
                ConstraintWidget constraintWidget = reference2.getConstraintWidget();
                constraintWidget.setParent(null);
                if (reference2 instanceof GuidelineReference) {
                    reference2.apply();
                }
                constraintWidgetContainer.add(constraintWidget);
            } else {
                reference2.setConstraintWidget(constraintWidgetContainer);
            }
        }
        Iterator<Object> it2 = this.b.keySet().iterator();
        while (it2.hasNext()) {
            HelperReference helperReference = this.b.get(it2.next());
            if (helperReference.getHelperWidget() != null) {
                Iterator<Object> it3 = helperReference.f369c.iterator();
                while (it3.hasNext()) {
                    helperReference.getHelperWidget().add(this.a.get(it3.next()).getConstraintWidget());
                }
                helperReference.apply();
            }
        }
        Iterator<Object> it4 = this.a.keySet().iterator();
        while (it4.hasNext()) {
            this.a.get(it4.next()).apply();
        }
    }

    public Reference b(Object obj) {
        return this.a.get(obj);
    }

    public BarrierReference barrier(Object obj, Direction direction) {
        BarrierReference barrierReference = (BarrierReference) helper(obj, Helper.BARRIER);
        barrierReference.setBarrierDirection(direction);
        return barrierReference;
    }

    public AlignHorizontallyReference centerHorizontally(Object... objArr) {
        AlignHorizontallyReference alignHorizontallyReference = (AlignHorizontallyReference) helper(null, Helper.ALIGN_HORIZONTALLY);
        alignHorizontallyReference.add(objArr);
        return alignHorizontallyReference;
    }

    public AlignVerticallyReference centerVertically(Object... objArr) {
        AlignVerticallyReference alignVerticallyReference = (AlignVerticallyReference) helper(null, Helper.ALIGN_VERTICALLY);
        alignVerticallyReference.add(objArr);
        return alignVerticallyReference;
    }

    public ConstraintReference constraints(Object obj) {
        Reference reference = this.a.get(obj);
        if (reference == null) {
            reference = createConstraintReference(obj);
            this.a.put(obj, reference);
            reference.setKey(obj);
        }
        if (reference instanceof ConstraintReference) {
            return (ConstraintReference) reference;
        }
        return null;
    }

    public int convertDimension(Object obj) {
        if (obj instanceof Float) {
            return ((Float) obj).intValue();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public ConstraintReference createConstraintReference(Object obj) {
        return new ConstraintReference(this);
    }

    public void directMapping() {
        for (Object obj : this.a.keySet()) {
            constraints(obj).setView(obj);
        }
    }

    public GuidelineReference guideline(Object obj, int i2) {
        Reference reference = this.a.get(obj);
        Reference reference2 = reference;
        if (reference == null) {
            GuidelineReference guidelineReference = new GuidelineReference(this);
            guidelineReference.setOrientation(i2);
            guidelineReference.setKey(obj);
            this.a.put(obj, guidelineReference);
            reference2 = guidelineReference;
        }
        return (GuidelineReference) reference2;
    }

    public State height(Dimension dimension) {
        return setHeight(dimension);
    }

    public HelperReference helper(Object obj, Helper helper) {
        HelperReference horizontalChainReference;
        if (obj == null) {
            obj = a();
        }
        HelperReference helperReference = this.b.get(obj);
        if (helperReference == null) {
            int i2 = a.a[helper.ordinal()];
            if (i2 == 1) {
                horizontalChainReference = new HorizontalChainReference(this);
            } else if (i2 == 2) {
                horizontalChainReference = new VerticalChainReference(this);
            } else if (i2 == 3) {
                horizontalChainReference = new AlignHorizontallyReference(this);
            } else if (i2 == 4) {
                horizontalChainReference = new AlignVerticallyReference(this);
            } else if (i2 != 5) {
                helperReference = new HelperReference(this, helper);
                this.b.put(obj, helperReference);
            } else {
                horizontalChainReference = new BarrierReference(this);
            }
            helperReference = horizontalChainReference;
            this.b.put(obj, helperReference);
        }
        return helperReference;
    }

    public HorizontalChainReference horizontalChain(Object... objArr) {
        HorizontalChainReference horizontalChainReference = (HorizontalChainReference) helper(null, Helper.HORIZONTAL_CHAIN);
        horizontalChainReference.add(objArr);
        return horizontalChainReference;
    }

    public GuidelineReference horizontalGuideline(Object obj) {
        return guideline(obj, 0);
    }

    public void map(Object obj, Object obj2) {
        constraints(obj).setView(obj2);
    }

    public void reset() {
        this.b.clear();
    }

    public State setHeight(Dimension dimension) {
        this.mParent.setHeight(dimension);
        return this;
    }

    public State setWidth(Dimension dimension) {
        this.mParent.setWidth(dimension);
        return this;
    }

    public VerticalChainReference verticalChain(Object... objArr) {
        VerticalChainReference verticalChainReference = (VerticalChainReference) helper(null, Helper.VERTICAL_CHAIN);
        verticalChainReference.add(objArr);
        return verticalChainReference;
    }

    public GuidelineReference verticalGuideline(Object obj) {
        return guideline(obj, 1);
    }

    public State width(Dimension dimension) {
        return setWidth(dimension);
    }
}
