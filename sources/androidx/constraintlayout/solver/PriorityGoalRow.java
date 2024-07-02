package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.ArrayRow;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: classes.dex */
public class PriorityGoalRow extends ArrayRow {
    private int f;
    private SolverVariable[] g;
    private SolverVariable[] h;

    /* renamed from: i, reason: collision with root package name */
    private int f357i;
    b j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Comparator<SolverVariable> {
        a(PriorityGoalRow priorityGoalRow) {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(SolverVariable solverVariable, SolverVariable solverVariable2) {
            return solverVariable.id - solverVariable2.id;
        }
    }

    /* loaded from: classes.dex */
    class b implements Comparable {
        SolverVariable a;

        public b(PriorityGoalRow priorityGoalRow) {
        }

        public boolean a(SolverVariable solverVariable, float f) {
            boolean z = true;
            if (!this.a.inGoal) {
                for (int i2 = 0; i2 < 9; i2++) {
                    float f2 = solverVariable.f359d[i2];
                    if (f2 != 0.0f) {
                        float f3 = f2 * f;
                        if (Math.abs(f3) < 1.0E-4f) {
                            f3 = 0.0f;
                        }
                        this.a.f359d[i2] = f3;
                    } else {
                        this.a.f359d[i2] = 0.0f;
                    }
                }
                return true;
            }
            for (int i3 = 0; i3 < 9; i3++) {
                float[] fArr = this.a.f359d;
                fArr[i3] = fArr[i3] + (solverVariable.f359d[i3] * f);
                if (Math.abs(fArr[i3]) < 1.0E-4f) {
                    this.a.f359d[i3] = 0.0f;
                } else {
                    z = false;
                }
            }
            if (z) {
                PriorityGoalRow.this.q(this.a);
            }
            return false;
        }

        public void b(SolverVariable solverVariable) {
            this.a = solverVariable;
        }

        public final boolean c() {
            for (int i2 = 8; i2 >= 0; i2--) {
                float f = this.a.f359d[i2];
                if (f > 0.0f) {
                    return false;
                }
                if (f < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return this.a.id - ((SolverVariable) obj).id;
        }

        public final boolean d(SolverVariable solverVariable) {
            int i2 = 8;
            while (true) {
                if (i2 < 0) {
                    break;
                }
                float f = solverVariable.f359d[i2];
                float f2 = this.a.f359d[i2];
                if (f2 == f) {
                    i2--;
                } else if (f2 < f) {
                    return true;
                }
            }
            return false;
        }

        public void e() {
            Arrays.fill(this.a.f359d, 0.0f);
        }

        public String toString() {
            String str = "[ ";
            if (this.a != null) {
                for (int i2 = 0; i2 < 9; i2++) {
                    str = str + this.a.f359d[i2] + " ";
                }
            }
            return str + "] " + this.a;
        }
    }

    public PriorityGoalRow(Cache cache) {
        super(cache);
        this.f = 128;
        this.g = new SolverVariable[128];
        this.h = new SolverVariable[128];
        this.f357i = 0;
        this.j = new b(this);
    }

    private final void p(SolverVariable solverVariable) {
        int i2;
        int i3 = this.f357i + 1;
        SolverVariable[] solverVariableArr = this.g;
        if (i3 > solverVariableArr.length) {
            SolverVariable[] solverVariableArr2 = (SolverVariable[]) Arrays.copyOf(solverVariableArr, solverVariableArr.length * 2);
            this.g = solverVariableArr2;
            this.h = (SolverVariable[]) Arrays.copyOf(solverVariableArr2, solverVariableArr2.length * 2);
        }
        SolverVariable[] solverVariableArr3 = this.g;
        int i4 = this.f357i;
        solverVariableArr3[i4] = solverVariable;
        int i5 = i4 + 1;
        this.f357i = i5;
        if (i5 > 1 && solverVariableArr3[i5 - 1].id > solverVariable.id) {
            int i6 = 0;
            while (true) {
                i2 = this.f357i;
                if (i6 >= i2) {
                    break;
                }
                this.h[i6] = this.g[i6];
                i6++;
            }
            Arrays.sort(this.h, 0, i2, new a(this));
            for (int i7 = 0; i7 < this.f357i; i7++) {
                this.g[i7] = this.h[i7];
            }
        }
        solverVariable.inGoal = true;
        solverVariable.addToRow(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void q(SolverVariable solverVariable) {
        int i2 = 0;
        while (i2 < this.f357i) {
            if (this.g[i2] == solverVariable) {
                while (true) {
                    int i3 = this.f357i;
                    if (i2 >= i3 - 1) {
                        this.f357i = i3 - 1;
                        solverVariable.inGoal = false;
                        return;
                    } else {
                        SolverVariable[] solverVariableArr = this.g;
                        int i4 = i2 + 1;
                        solverVariableArr[i2] = solverVariableArr[i4];
                        i2 = i4;
                    }
                }
            } else {
                i2++;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow, androidx.constraintlayout.solver.LinearSystem.a
    public void addError(SolverVariable solverVariable) {
        this.j.b(solverVariable);
        this.j.e();
        solverVariable.f359d[solverVariable.strength] = 1.0f;
        p(solverVariable);
    }

    @Override // androidx.constraintlayout.solver.ArrayRow, androidx.constraintlayout.solver.LinearSystem.a
    public void clear() {
        this.f357i = 0;
        this.b = 0.0f;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow, androidx.constraintlayout.solver.LinearSystem.a
    public SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr) {
        int i2 = -1;
        for (int i3 = 0; i3 < this.f357i; i3++) {
            SolverVariable solverVariable = this.g[i3];
            if (!zArr[solverVariable.id]) {
                this.j.b(solverVariable);
                b bVar = this.j;
                if (i2 == -1) {
                    if (!bVar.c()) {
                    }
                    i2 = i3;
                } else {
                    if (!bVar.d(this.g[i2])) {
                    }
                    i2 = i3;
                }
            }
        }
        if (i2 == -1) {
            return null;
        }
        return this.g[i2];
    }

    @Override // androidx.constraintlayout.solver.ArrayRow, androidx.constraintlayout.solver.LinearSystem.a
    public boolean isEmpty() {
        return this.f357i == 0;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow
    public String toString() {
        String str = " goal -> (" + this.b + ") : ";
        for (int i2 = 0; i2 < this.f357i; i2++) {
            this.j.b(this.g[i2]);
            str = str + this.j + " ";
        }
        return str;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow
    public void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z) {
        SolverVariable solverVariable = arrayRow.a;
        if (solverVariable == null) {
            return;
        }
        ArrayRow.ArrayRowVariables arrayRowVariables = arrayRow.variables;
        int currentSize = arrayRowVariables.getCurrentSize();
        for (int i2 = 0; i2 < currentSize; i2++) {
            SolverVariable variable = arrayRowVariables.getVariable(i2);
            float variableValue = arrayRowVariables.getVariableValue(i2);
            this.j.b(variable);
            if (this.j.a(solverVariable, variableValue)) {
                p(variable);
            }
            this.b += arrayRow.b * variableValue;
        }
        q(solverVariable);
    }
}
