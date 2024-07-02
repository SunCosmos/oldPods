package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.widgets.Chain;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class WidgetGroup {
    static int g;
    int b;

    /* renamed from: d, reason: collision with root package name */
    int f398d;
    ArrayList<ConstraintWidget> a = new ArrayList<>();

    /* renamed from: c, reason: collision with root package name */
    boolean f397c = false;
    ArrayList<a> e = null;
    private int f = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a {
        WeakReference<ConstraintWidget> a;
        int b;

        /* renamed from: c, reason: collision with root package name */
        int f399c;

        /* renamed from: d, reason: collision with root package name */
        int f400d;
        int e;
        int f;
        int g;

        public a(WidgetGroup widgetGroup, ConstraintWidget constraintWidget, LinearSystem linearSystem, int i2) {
            this.a = new WeakReference<>(constraintWidget);
            this.b = linearSystem.getObjectVariableValue(constraintWidget.mLeft);
            this.f399c = linearSystem.getObjectVariableValue(constraintWidget.mTop);
            this.f400d = linearSystem.getObjectVariableValue(constraintWidget.mRight);
            this.e = linearSystem.getObjectVariableValue(constraintWidget.mBottom);
            this.f = linearSystem.getObjectVariableValue(constraintWidget.mBaseline);
            this.g = i2;
        }

        public void a() {
            ConstraintWidget constraintWidget = this.a.get();
            if (constraintWidget != null) {
                constraintWidget.setFinalFrame(this.b, this.f399c, this.f400d, this.e, this.f, this.g);
            }
        }
    }

    public WidgetGroup(int i2) {
        this.b = -1;
        this.f398d = 0;
        int i3 = g;
        g = i3 + 1;
        this.b = i3;
        this.f398d = i2;
    }

    private boolean a(ConstraintWidget constraintWidget) {
        return this.a.contains(constraintWidget);
    }

    private String b() {
        int i2 = this.f398d;
        return i2 == 0 ? "Horizontal" : i2 == 1 ? "Vertical" : i2 == 2 ? "Both" : "Unknown";
    }

    private int c(LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i2) {
        int objectVariableValue;
        ConstraintAnchor constraintAnchor;
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) arrayList.get(0).getParent();
        linearSystem.reset();
        constraintWidgetContainer.addToSolver(linearSystem, false);
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            arrayList.get(i3).addToSolver(linearSystem, false);
        }
        if (i2 == 0 && constraintWidgetContainer.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 0);
        }
        if (i2 == 1 && constraintWidgetContainer.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(constraintWidgetContainer, linearSystem, arrayList, 1);
        }
        try {
            linearSystem.minimize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.e = new ArrayList<>();
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            this.e.add(new a(this, arrayList.get(i4), linearSystem, i2));
        }
        if (i2 == 0) {
            objectVariableValue = linearSystem.getObjectVariableValue(constraintWidgetContainer.mLeft);
            constraintAnchor = constraintWidgetContainer.mRight;
        } else {
            objectVariableValue = linearSystem.getObjectVariableValue(constraintWidgetContainer.mTop);
            constraintAnchor = constraintWidgetContainer.mBottom;
        }
        int objectVariableValue2 = linearSystem.getObjectVariableValue(constraintAnchor);
        linearSystem.reset();
        return objectVariableValue2 - objectVariableValue;
    }

    public boolean add(ConstraintWidget constraintWidget) {
        if (this.a.contains(constraintWidget)) {
            return false;
        }
        this.a.add(constraintWidget);
        return true;
    }

    public void apply() {
        if (this.e != null && this.f397c) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                this.e.get(i2).a();
            }
        }
    }

    public void cleanup(ArrayList<WidgetGroup> arrayList) {
        int size = this.a.size();
        if (this.f != -1 && size > 0) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                WidgetGroup widgetGroup = arrayList.get(i2);
                if (this.f == widgetGroup.b) {
                    moveTo(this.f398d, widgetGroup);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public void clear() {
        this.a.clear();
    }

    public int getId() {
        return this.b;
    }

    public int getOrientation() {
        return this.f398d;
    }

    public boolean intersectWith(WidgetGroup widgetGroup) {
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            if (widgetGroup.a(this.a.get(i2))) {
                return true;
            }
        }
        return false;
    }

    public boolean isAuthoritative() {
        return this.f397c;
    }

    public int measureWrap(LinearSystem linearSystem, int i2) {
        if (this.a.size() == 0) {
            return 0;
        }
        return c(linearSystem, this.a, i2);
    }

    public void moveTo(int i2, WidgetGroup widgetGroup) {
        Iterator<ConstraintWidget> it = this.a.iterator();
        while (it.hasNext()) {
            ConstraintWidget next = it.next();
            widgetGroup.add(next);
            int id = widgetGroup.getId();
            if (i2 == 0) {
                next.horizontalGroup = id;
            } else {
                next.verticalGroup = id;
            }
        }
        this.f = widgetGroup.b;
    }

    public void setAuthoritative(boolean z) {
        this.f397c = z;
    }

    public void setOrientation(int i2) {
        this.f398d = i2;
    }

    public int size() {
        return this.a.size();
    }

    public String toString() {
        String str = b() + " [" + this.b + "] <";
        Iterator<ConstraintWidget> it = this.a.iterator();
        while (it.hasNext()) {
            str = str + " " + it.next().getDebugName();
        }
        return str + " >";
    }
}
