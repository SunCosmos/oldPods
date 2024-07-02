package androidx.constraintlayout.solver.state;

import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ConstraintReference implements Reference {
    Dimension G;
    Dimension H;
    private Object I;
    private ConstraintWidget J;
    private Object a;
    final State b;

    /* renamed from: c, reason: collision with root package name */
    int f364c = 0;

    /* renamed from: d, reason: collision with root package name */
    int f365d = 0;
    float e = 0.5f;
    float f = 0.5f;
    int g = 0;
    int h = 0;

    /* renamed from: i, reason: collision with root package name */
    int f366i = 0;
    int j = 0;
    int k = 0;
    int l = 0;
    int m = 0;
    int n = 0;
    int o = 0;
    int p = 0;
    int q = 0;
    int r = 0;
    Object s = null;
    Object t = null;
    Object u = null;
    Object v = null;
    Object w = null;
    Object x = null;
    Object y = null;
    Object z = null;
    Object A = null;
    Object B = null;
    Object C = null;
    Object D = null;
    Object E = null;
    State.Constraint F = null;

    /* loaded from: classes.dex */
    public interface ConstraintReferenceFactory {
        ConstraintReference create(State state);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[State.Constraint.values().length];
            a = iArr;
            try {
                iArr[State.Constraint.LEFT_TO_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[State.Constraint.LEFT_TO_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[State.Constraint.RIGHT_TO_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[State.Constraint.RIGHT_TO_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[State.Constraint.START_TO_START.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[State.Constraint.START_TO_END.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[State.Constraint.END_TO_START.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[State.Constraint.END_TO_END.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[State.Constraint.TOP_TO_TOP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[State.Constraint.TOP_TO_BOTTOM.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[State.Constraint.BOTTOM_TO_TOP.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[State.Constraint.BOTTOM_TO_BOTTOM.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                a[State.Constraint.BASELINE_TO_BASELINE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                a[State.Constraint.CENTER_HORIZONTALLY.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                a[State.Constraint.CENTER_VERTICALLY.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    /* loaded from: classes.dex */
    class b extends Exception {
        private final ArrayList<String> a;

        public b(ConstraintReference constraintReference, ArrayList<String> arrayList) {
            this.a = arrayList;
        }

        @Override // java.lang.Throwable
        public String toString() {
            return "IncorrectConstraintException: " + this.a.toString();
        }
    }

    public ConstraintReference(State state) {
        Object obj = Dimension.WRAP_DIMENSION;
        this.G = Dimension.Fixed(obj);
        this.H = Dimension.Fixed(obj);
        this.b = state;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0016. Please report as an issue. */
    private void a(ConstraintWidget constraintWidget, Object obj, State.Constraint constraint) {
        ConstraintAnchor.Type type;
        ConstraintAnchor anchor;
        ConstraintAnchor anchor2;
        int i2;
        int i3;
        ConstraintAnchor.Type type2;
        ConstraintAnchor.Type type3;
        ConstraintAnchor.Type type4;
        ConstraintAnchor.Type type5;
        ConstraintAnchor.Type type6;
        ConstraintWidget d2 = d(obj);
        if (d2 == null) {
            return;
        }
        int[] iArr = a.a;
        int i4 = iArr[constraint.ordinal()];
        switch (iArr[constraint.ordinal()]) {
            case 1:
                type = ConstraintAnchor.Type.LEFT;
                anchor = constraintWidget.getAnchor(type);
                anchor2 = d2.getAnchor(type);
                i2 = this.g;
                i3 = this.m;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 2:
                anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT);
                type = ConstraintAnchor.Type.RIGHT;
                anchor2 = d2.getAnchor(type);
                i2 = this.g;
                i3 = this.m;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 3:
                anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT);
                type2 = ConstraintAnchor.Type.LEFT;
                anchor2 = d2.getAnchor(type2);
                i2 = this.h;
                i3 = this.n;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 4:
                type2 = ConstraintAnchor.Type.RIGHT;
                anchor = constraintWidget.getAnchor(type2);
                anchor2 = d2.getAnchor(type2);
                i2 = this.h;
                i3 = this.n;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 5:
                type3 = ConstraintAnchor.Type.LEFT;
                anchor = constraintWidget.getAnchor(type3);
                anchor2 = d2.getAnchor(type3);
                i2 = this.f366i;
                i3 = this.o;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 6:
                anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT);
                type3 = ConstraintAnchor.Type.RIGHT;
                anchor2 = d2.getAnchor(type3);
                i2 = this.f366i;
                i3 = this.o;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 7:
                anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT);
                type4 = ConstraintAnchor.Type.LEFT;
                anchor2 = d2.getAnchor(type4);
                i2 = this.j;
                i3 = this.p;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 8:
                type4 = ConstraintAnchor.Type.RIGHT;
                anchor = constraintWidget.getAnchor(type4);
                anchor2 = d2.getAnchor(type4);
                i2 = this.j;
                i3 = this.p;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 9:
                type5 = ConstraintAnchor.Type.TOP;
                anchor = constraintWidget.getAnchor(type5);
                anchor2 = d2.getAnchor(type5);
                i2 = this.k;
                i3 = this.q;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 10:
                anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.TOP);
                type5 = ConstraintAnchor.Type.BOTTOM;
                anchor2 = d2.getAnchor(type5);
                i2 = this.k;
                i3 = this.q;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 11:
                anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM);
                type6 = ConstraintAnchor.Type.TOP;
                anchor2 = d2.getAnchor(type6);
                i2 = this.l;
                i3 = this.r;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 12:
                type6 = ConstraintAnchor.Type.BOTTOM;
                anchor = constraintWidget.getAnchor(type6);
                anchor2 = d2.getAnchor(type6);
                i2 = this.l;
                i3 = this.r;
                anchor.connect(anchor2, i2, i3, false);
                return;
            case 13:
                ConstraintAnchor.Type type7 = ConstraintAnchor.Type.BASELINE;
                constraintWidget.immediateConnect(type7, d2, type7, 0, 0);
                return;
            default:
                return;
        }
    }

    private void b() {
        this.s = c(this.s);
        this.t = c(this.t);
        this.u = c(this.u);
        this.v = c(this.v);
        this.w = c(this.w);
        this.x = c(this.x);
        this.y = c(this.y);
        this.z = c(this.z);
        this.A = c(this.A);
        this.B = c(this.B);
        this.C = c(this.C);
        this.D = c(this.D);
        this.E = c(this.E);
    }

    private Object c(Object obj) {
        if (obj == null) {
            return null;
        }
        return !(obj instanceof ConstraintReference) ? this.b.b(obj) : obj;
    }

    private ConstraintWidget d(Object obj) {
        if (obj instanceof Reference) {
            return ((Reference) obj).getConstraintWidget();
        }
        return null;
    }

    @Override // androidx.constraintlayout.solver.state.Reference
    public void apply() {
        ConstraintWidget constraintWidget = this.J;
        if (constraintWidget == null) {
            return;
        }
        this.G.apply(this.b, constraintWidget, 0);
        this.H.apply(this.b, this.J, 1);
        b();
        a(this.J, this.s, State.Constraint.LEFT_TO_LEFT);
        a(this.J, this.t, State.Constraint.LEFT_TO_RIGHT);
        a(this.J, this.u, State.Constraint.RIGHT_TO_LEFT);
        a(this.J, this.v, State.Constraint.RIGHT_TO_RIGHT);
        a(this.J, this.w, State.Constraint.START_TO_START);
        a(this.J, this.x, State.Constraint.START_TO_END);
        a(this.J, this.y, State.Constraint.END_TO_START);
        a(this.J, this.z, State.Constraint.END_TO_END);
        a(this.J, this.A, State.Constraint.TOP_TO_TOP);
        a(this.J, this.B, State.Constraint.TOP_TO_BOTTOM);
        a(this.J, this.C, State.Constraint.BOTTOM_TO_TOP);
        a(this.J, this.D, State.Constraint.BOTTOM_TO_BOTTOM);
        a(this.J, this.E, State.Constraint.BASELINE_TO_BASELINE);
        int i2 = this.f364c;
        if (i2 != 0) {
            this.J.setHorizontalChainStyle(i2);
        }
        int i3 = this.f365d;
        if (i3 != 0) {
            this.J.setVerticalChainStyle(i3);
        }
        this.J.setHorizontalBiasPercent(this.e);
        this.J.setVerticalBiasPercent(this.f);
    }

    public ConstraintReference baseline() {
        this.F = State.Constraint.BASELINE_TO_BASELINE;
        return this;
    }

    public ConstraintReference baselineToBaseline(Object obj) {
        this.F = State.Constraint.BASELINE_TO_BASELINE;
        this.E = obj;
        return this;
    }

    public ConstraintReference bias(float f) {
        State.Constraint constraint = this.F;
        if (constraint == null) {
            return this;
        }
        switch (a.a[constraint.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 14:
                this.e = f;
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 15:
                this.f = f;
                break;
        }
        return this;
    }

    public ConstraintReference bottom() {
        this.F = this.C != null ? State.Constraint.BOTTOM_TO_TOP : State.Constraint.BOTTOM_TO_BOTTOM;
        return this;
    }

    public ConstraintReference bottomToBottom(Object obj) {
        this.F = State.Constraint.BOTTOM_TO_BOTTOM;
        this.D = obj;
        return this;
    }

    public ConstraintReference bottomToTop(Object obj) {
        this.F = State.Constraint.BOTTOM_TO_TOP;
        this.C = obj;
        return this;
    }

    public ConstraintReference centerHorizontally(Object obj) {
        Object c2 = c(obj);
        this.w = c2;
        this.z = c2;
        this.F = State.Constraint.CENTER_HORIZONTALLY;
        this.e = 0.5f;
        return this;
    }

    public ConstraintReference centerVertically(Object obj) {
        Object c2 = c(obj);
        this.A = c2;
        this.D = c2;
        this.F = State.Constraint.CENTER_VERTICALLY;
        this.f = 0.5f;
        return this;
    }

    public ConstraintReference clear() {
        State.Constraint constraint = this.F;
        if (constraint != null) {
            switch (a.a[constraint.ordinal()]) {
                case 1:
                case 2:
                    this.s = null;
                    this.t = null;
                    this.g = 0;
                    this.m = 0;
                    break;
                case 3:
                case 4:
                    this.u = null;
                    this.v = null;
                    this.h = 0;
                    this.n = 0;
                    break;
                case 5:
                case 6:
                    this.w = null;
                    this.x = null;
                    this.f366i = 0;
                    this.o = 0;
                    break;
                case 7:
                case 8:
                    this.y = null;
                    this.z = null;
                    this.j = 0;
                    this.p = 0;
                    break;
                case 9:
                case 10:
                    this.A = null;
                    this.B = null;
                    this.k = 0;
                    this.q = 0;
                    break;
                case 11:
                case 12:
                    this.C = null;
                    this.D = null;
                    this.l = 0;
                    break;
                case 13:
                    this.E = null;
                    break;
            }
            return this;
        }
        this.s = null;
        this.t = null;
        this.g = 0;
        this.u = null;
        this.v = null;
        this.h = 0;
        this.w = null;
        this.x = null;
        this.f366i = 0;
        this.y = null;
        this.z = null;
        this.j = 0;
        this.A = null;
        this.B = null;
        this.k = 0;
        this.C = null;
        this.D = null;
        this.l = 0;
        this.E = null;
        this.e = 0.5f;
        this.f = 0.5f;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        return this;
    }

    public ConstraintReference clearHorizontal() {
        start().clear();
        end().clear();
        left().clear();
        right().clear();
        return this;
    }

    public ConstraintReference clearVertical() {
        top().clear();
        baseline().clear();
        bottom().clear();
        return this;
    }

    public ConstraintWidget createConstraintWidget() {
        return new ConstraintWidget(getWidth().a(), getHeight().a());
    }

    public ConstraintReference end() {
        this.F = this.y != null ? State.Constraint.END_TO_START : State.Constraint.END_TO_END;
        return this;
    }

    public ConstraintReference endToEnd(Object obj) {
        this.F = State.Constraint.END_TO_END;
        this.z = obj;
        return this;
    }

    public ConstraintReference endToStart(Object obj) {
        this.F = State.Constraint.END_TO_START;
        this.y = obj;
        return this;
    }

    @Override // androidx.constraintlayout.solver.state.Reference
    public ConstraintWidget getConstraintWidget() {
        if (this.J == null) {
            ConstraintWidget createConstraintWidget = createConstraintWidget();
            this.J = createConstraintWidget;
            createConstraintWidget.setCompanionWidget(this.I);
        }
        return this.J;
    }

    public Dimension getHeight() {
        return this.H;
    }

    public int getHorizontalChainStyle() {
        return this.f364c;
    }

    @Override // androidx.constraintlayout.solver.state.Reference
    public Object getKey() {
        return this.a;
    }

    public int getVerticalChainStyle(int i2) {
        return this.f365d;
    }

    public Object getView() {
        return this.I;
    }

    public Dimension getWidth() {
        return this.G;
    }

    public ConstraintReference height(Dimension dimension) {
        return setHeight(dimension);
    }

    public ConstraintReference horizontalBias(float f) {
        this.e = f;
        return this;
    }

    public ConstraintReference left() {
        this.F = this.s != null ? State.Constraint.LEFT_TO_LEFT : State.Constraint.LEFT_TO_RIGHT;
        return this;
    }

    public ConstraintReference leftToLeft(Object obj) {
        this.F = State.Constraint.LEFT_TO_LEFT;
        this.s = obj;
        return this;
    }

    public ConstraintReference leftToRight(Object obj) {
        this.F = State.Constraint.LEFT_TO_RIGHT;
        this.t = obj;
        return this;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public ConstraintReference margin(int i2) {
        State.Constraint constraint = this.F;
        if (constraint != null) {
            switch (a.a[constraint.ordinal()]) {
                case 1:
                case 2:
                    this.g = i2;
                    break;
                case 3:
                case 4:
                    this.h = i2;
                    break;
                case 5:
                case 6:
                    this.f366i = i2;
                    break;
                case 7:
                case 8:
                    this.j = i2;
                    break;
                case 9:
                case 10:
                    this.k = i2;
                    break;
            }
        } else {
            this.g = i2;
            this.h = i2;
            this.f366i = i2;
            this.j = i2;
            this.k = i2;
        }
        this.l = i2;
        return this;
    }

    public ConstraintReference margin(Object obj) {
        return margin(this.b.convertDimension(obj));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public ConstraintReference marginGone(int i2) {
        State.Constraint constraint = this.F;
        if (constraint != null) {
            switch (a.a[constraint.ordinal()]) {
                case 1:
                case 2:
                    this.m = i2;
                    break;
                case 3:
                case 4:
                    this.n = i2;
                    break;
                case 5:
                case 6:
                    this.o = i2;
                    break;
                case 7:
                case 8:
                    this.p = i2;
                    break;
                case 9:
                case 10:
                    this.q = i2;
                    break;
            }
        } else {
            this.m = i2;
            this.n = i2;
            this.o = i2;
            this.p = i2;
            this.q = i2;
        }
        this.r = i2;
        return this;
    }

    public ConstraintReference right() {
        this.F = this.u != null ? State.Constraint.RIGHT_TO_LEFT : State.Constraint.RIGHT_TO_RIGHT;
        return this;
    }

    public ConstraintReference rightToLeft(Object obj) {
        this.F = State.Constraint.RIGHT_TO_LEFT;
        this.u = obj;
        return this;
    }

    public ConstraintReference rightToRight(Object obj) {
        this.F = State.Constraint.RIGHT_TO_RIGHT;
        this.v = obj;
        return this;
    }

    @Override // androidx.constraintlayout.solver.state.Reference
    public void setConstraintWidget(ConstraintWidget constraintWidget) {
        if (constraintWidget == null) {
            return;
        }
        this.J = constraintWidget;
        constraintWidget.setCompanionWidget(this.I);
    }

    public ConstraintReference setHeight(Dimension dimension) {
        this.H = dimension;
        return this;
    }

    public void setHorizontalChainStyle(int i2) {
        this.f364c = i2;
    }

    @Override // androidx.constraintlayout.solver.state.Reference
    public void setKey(Object obj) {
        this.a = obj;
    }

    public void setVerticalChainStyle(int i2) {
        this.f365d = i2;
    }

    public void setView(Object obj) {
        this.I = obj;
        ConstraintWidget constraintWidget = this.J;
        if (constraintWidget != null) {
            constraintWidget.setCompanionWidget(obj);
        }
    }

    public ConstraintReference setWidth(Dimension dimension) {
        this.G = dimension;
        return this;
    }

    public ConstraintReference start() {
        this.F = this.w != null ? State.Constraint.START_TO_START : State.Constraint.START_TO_END;
        return this;
    }

    public ConstraintReference startToEnd(Object obj) {
        this.F = State.Constraint.START_TO_END;
        this.x = obj;
        return this;
    }

    public ConstraintReference startToStart(Object obj) {
        this.F = State.Constraint.START_TO_START;
        this.w = obj;
        return this;
    }

    public ConstraintReference top() {
        this.F = this.A != null ? State.Constraint.TOP_TO_TOP : State.Constraint.TOP_TO_BOTTOM;
        return this;
    }

    public ConstraintReference topToBottom(Object obj) {
        this.F = State.Constraint.TOP_TO_BOTTOM;
        this.B = obj;
        return this;
    }

    public ConstraintReference topToTop(Object obj) {
        this.F = State.Constraint.TOP_TO_TOP;
        this.A = obj;
        return this;
    }

    public void validate() {
        ArrayList arrayList = new ArrayList();
        if (this.s != null && this.t != null) {
            arrayList.add("LeftToLeft and LeftToRight both defined");
        }
        if (this.u != null && this.v != null) {
            arrayList.add("RightToLeft and RightToRight both defined");
        }
        if (this.w != null && this.x != null) {
            arrayList.add("StartToStart and StartToEnd both defined");
        }
        if (this.y != null && this.z != null) {
            arrayList.add("EndToStart and EndToEnd both defined");
        }
        if ((this.s != null || this.t != null || this.u != null || this.v != null) && (this.w != null || this.x != null || this.y != null || this.z != null)) {
            arrayList.add("Both left/right and start/end constraints defined");
        }
        if (arrayList.size() > 0) {
            throw new b(this, arrayList);
        }
    }

    public ConstraintReference verticalBias(float f) {
        this.f = f;
        return this;
    }

    public ConstraintReference width(Dimension dimension) {
        return setWidth(dimension);
    }
}
