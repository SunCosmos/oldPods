package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.widgets.analyzer.Grouping;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public class HelperWidget extends ConstraintWidget implements Helper {
    public ConstraintWidget[] mWidgets = new ConstraintWidget[4];
    public int mWidgetsCount = 0;

    @Override // androidx.constraintlayout.solver.widgets.Helper
    public void add(ConstraintWidget constraintWidget) {
        if (constraintWidget == this || constraintWidget == null) {
            return;
        }
        int i2 = this.mWidgetsCount + 1;
        ConstraintWidget[] constraintWidgetArr = this.mWidgets;
        if (i2 > constraintWidgetArr.length) {
            this.mWidgets = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr, constraintWidgetArr.length * 2);
        }
        ConstraintWidget[] constraintWidgetArr2 = this.mWidgets;
        int i3 = this.mWidgetsCount;
        constraintWidgetArr2[i3] = constraintWidget;
        this.mWidgetsCount = i3 + 1;
    }

    public void addDependents(ArrayList<WidgetGroup> arrayList, int i2, WidgetGroup widgetGroup) {
        for (int i3 = 0; i3 < this.mWidgetsCount; i3++) {
            widgetGroup.add(this.mWidgets[i3]);
        }
        for (int i4 = 0; i4 < this.mWidgetsCount; i4++) {
            Grouping.findDependents(this.mWidgets[i4], i2, arrayList, widgetGroup);
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        super.copy(constraintWidget, hashMap);
        HelperWidget helperWidget = (HelperWidget) constraintWidget;
        this.mWidgetsCount = 0;
        int i2 = helperWidget.mWidgetsCount;
        for (int i3 = 0; i3 < i2; i3++) {
            add(hashMap.get(helperWidget.mWidgets[i3]));
        }
    }

    public int findGroupInDependents(int i2) {
        int i3;
        int i4;
        for (int i5 = 0; i5 < this.mWidgetsCount; i5++) {
            ConstraintWidget constraintWidget = this.mWidgets[i5];
            if (i2 == 0 && (i4 = constraintWidget.horizontalGroup) != -1) {
                return i4;
            }
            if (i2 == 1 && (i3 = constraintWidget.verticalGroup) != -1) {
                return i3;
            }
        }
        return -1;
    }

    @Override // androidx.constraintlayout.solver.widgets.Helper
    public void removeAllIds() {
        this.mWidgetsCount = 0;
        Arrays.fill(this.mWidgets, (Object) null);
    }

    @Override // androidx.constraintlayout.solver.widgets.Helper
    public void updateConstraints(ConstraintWidgetContainer constraintWidgetContainer) {
    }
}
