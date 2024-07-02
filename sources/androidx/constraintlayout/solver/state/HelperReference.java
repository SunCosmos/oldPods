package androidx.constraintlayout.solver.state;

import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.solver.widgets.HelperWidget;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class HelperReference {
    protected final State a;
    final State.Helper b;

    /* renamed from: c, reason: collision with root package name */
    protected ArrayList<Object> f369c = new ArrayList<>();

    /* renamed from: d, reason: collision with root package name */
    private HelperWidget f370d;

    public HelperReference(State state, State.Helper helper) {
        this.a = state;
        this.b = helper;
    }

    public HelperReference add(Object... objArr) {
        for (Object obj : objArr) {
            this.f369c.add(obj);
        }
        return this;
    }

    public void apply() {
    }

    public HelperWidget getHelperWidget() {
        return this.f370d;
    }

    public State.Helper getType() {
        return this.b;
    }

    public void setHelperWidget(HelperWidget helperWidget) {
        this.f370d = helperWidget;
    }
}
