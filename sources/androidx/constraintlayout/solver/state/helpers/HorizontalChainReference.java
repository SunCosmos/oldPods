package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.ConstraintReference;
import androidx.constraintlayout.solver.state.State;
import java.util.Iterator;

/* loaded from: classes.dex */
public class HorizontalChainReference extends ChainReference {
    private Object g;
    private Object h;

    /* renamed from: i, reason: collision with root package name */
    private Object f376i;
    private Object j;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[State.Chain.values().length];
            a = iArr;
            try {
                iArr[State.Chain.SPREAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[State.Chain.SPREAD_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[State.Chain.PACKED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public HorizontalChainReference(State state) {
        super(state, State.Helper.HORIZONTAL_CHAIN);
    }

    @Override // androidx.constraintlayout.solver.state.HelperReference
    public void apply() {
        Iterator<Object> it = this.f369c.iterator();
        while (it.hasNext()) {
            this.a.constraints(it.next()).clearHorizontal();
        }
        Iterator<Object> it2 = this.f369c.iterator();
        ConstraintReference constraintReference = null;
        ConstraintReference constraintReference2 = null;
        while (it2.hasNext()) {
            ConstraintReference constraints = this.a.constraints(it2.next());
            if (constraintReference2 == null) {
                Object obj = this.g;
                if (obj == null) {
                    Object obj2 = this.h;
                    if (obj2 != null) {
                        constraints.startToEnd(obj2);
                        constraintReference2 = constraints;
                    } else {
                        obj = State.PARENT;
                    }
                }
                constraints.startToStart(obj);
                constraintReference2 = constraints;
            }
            if (constraintReference != null) {
                constraintReference.endToStart(constraints.getKey());
                constraints.startToEnd(constraintReference.getKey());
            }
            constraintReference = constraints;
        }
        if (constraintReference != null) {
            Object obj3 = this.f376i;
            if (obj3 != null) {
                constraintReference.endToStart(obj3);
            } else {
                Object obj4 = this.j;
                if (obj4 == null) {
                    obj4 = State.PARENT;
                }
                constraintReference.endToEnd(obj4);
            }
        }
        if (constraintReference2 != null) {
            float f = this.e;
            if (f != 0.5f) {
                constraintReference2.horizontalBias(f);
            }
        }
        int i2 = a.a[this.f.ordinal()];
        if (i2 == 1) {
            constraintReference2.setHorizontalChainStyle(0);
        } else if (i2 == 2) {
            constraintReference2.setHorizontalChainStyle(1);
        } else {
            if (i2 != 3) {
                return;
            }
            constraintReference2.setHorizontalChainStyle(2);
        }
    }

    public void endToEnd(Object obj) {
        this.j = obj;
    }

    public void endToStart(Object obj) {
        this.f376i = obj;
    }

    public void startToEnd(Object obj) {
        this.h = obj;
    }

    public void startToStart(Object obj) {
        this.g = obj;
    }
}
