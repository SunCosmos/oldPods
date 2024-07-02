package androidx.constraintlayout.solver.state.helpers;

import androidx.constraintlayout.solver.state.HelperReference;
import androidx.constraintlayout.solver.state.State;

/* loaded from: classes.dex */
public class ChainReference extends HelperReference {
    protected float e;
    protected State.Chain f;

    public ChainReference(State state, State.Helper helper) {
        super(state, helper);
        this.e = 0.5f;
        this.f = State.Chain.SPREAD;
    }

    public void bias(float f) {
        this.e = f;
    }

    public float getBias() {
        return this.e;
    }

    public State.Chain getStyle() {
        return State.Chain.SPREAD;
    }

    public void style(State.Chain chain) {
        this.f = chain;
    }
}
