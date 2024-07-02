package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ArrayRow implements LinearSystem.a {

    /* renamed from: c */
    boolean f350c;
    public ArrayRowVariables variables;
    SolverVariable a = null;
    float b = 0.0f;

    /* renamed from: d */
    ArrayList<SolverVariable> f351d = new ArrayList<>();
    boolean e = false;

    /* loaded from: classes.dex */
    public interface ArrayRowVariables {
        void add(SolverVariable solverVariable, float f, boolean z);

        void clear();

        boolean contains(SolverVariable solverVariable);

        void display();

        void divideByAmount(float f);

        float get(SolverVariable solverVariable);

        int getCurrentSize();

        SolverVariable getVariable(int i2);

        float getVariableValue(int i2);

        int indexOf(SolverVariable solverVariable);

        void invert();

        void put(SolverVariable solverVariable, float f);

        float remove(SolverVariable solverVariable, boolean z);

        int sizeInBytes();

        float use(ArrayRow arrayRow, boolean z);
    }

    public ArrayRow() {
    }

    public ArrayRow(Cache cache) {
        this.variables = new ArrayLinkedVariables(this, cache);
    }

    private boolean j(SolverVariable solverVariable, LinearSystem linearSystem) {
        return solverVariable.usageInRowCount <= 1;
    }

    private SolverVariable k(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable.Type type;
        int currentSize = this.variables.getCurrentSize();
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        for (int i2 = 0; i2 < currentSize; i2++) {
            float variableValue = this.variables.getVariableValue(i2);
            if (variableValue < 0.0f) {
                SolverVariable variable = this.variables.getVariable(i2);
                if ((zArr == null || !zArr[variable.id]) && variable != solverVariable && (((type = variable.e) == SolverVariable.Type.SLACK || type == SolverVariable.Type.ERROR) && variableValue < f)) {
                    f = variableValue;
                    solverVariable2 = variable;
                }
            }
        }
        return solverVariable2;
    }

    public ArrayRow a(SolverVariable solverVariable, int i2) {
        this.variables.put(solverVariable, i2);
        return this;
    }

    public ArrayRow addError(LinearSystem linearSystem, int i2) {
        this.variables.put(linearSystem.createErrorVariable(i2, "ep"), 1.0f);
        this.variables.put(linearSystem.createErrorVariable(i2, "em"), -1.0f);
        return this;
    }

    @Override // androidx.constraintlayout.solver.LinearSystem.a
    public void addError(SolverVariable solverVariable) {
        int i2 = solverVariable.strength;
        float f = 1.0f;
        if (i2 != 1) {
            if (i2 == 2) {
                f = 1000.0f;
            } else if (i2 == 3) {
                f = 1000000.0f;
            } else if (i2 == 4) {
                f = 1.0E9f;
            } else if (i2 == 5) {
                f = 1.0E12f;
            }
        }
        this.variables.put(solverVariable, f);
    }

    public boolean b(LinearSystem linearSystem) {
        boolean z;
        SolverVariable c2 = c(linearSystem);
        if (c2 == null) {
            z = true;
        } else {
            l(c2);
            z = false;
        }
        if (this.variables.getCurrentSize() == 0) {
            this.e = true;
        }
        return z;
    }

    SolverVariable c(LinearSystem linearSystem) {
        int currentSize = this.variables.getCurrentSize();
        SolverVariable solverVariable = null;
        SolverVariable solverVariable2 = null;
        boolean z = false;
        boolean z2 = false;
        float f = 0.0f;
        float f2 = 0.0f;
        for (int i2 = 0; i2 < currentSize; i2++) {
            float variableValue = this.variables.getVariableValue(i2);
            SolverVariable variable = this.variables.getVariable(i2);
            if (variable.e == SolverVariable.Type.UNRESTRICTED) {
                if (solverVariable == null || f > variableValue) {
                    z = j(variable, linearSystem);
                    f = variableValue;
                    solverVariable = variable;
                } else if (!z && j(variable, linearSystem)) {
                    f = variableValue;
                    solverVariable = variable;
                    z = true;
                }
            } else if (solverVariable == null && variableValue < 0.0f) {
                if (solverVariable2 == null || f2 > variableValue) {
                    z2 = j(variable, linearSystem);
                    f2 = variableValue;
                    solverVariable2 = variable;
                } else if (!z2 && j(variable, linearSystem)) {
                    f2 = variableValue;
                    solverVariable2 = variable;
                    z2 = true;
                }
            }
        }
        return solverVariable != null ? solverVariable : solverVariable2;
    }

    @Override // androidx.constraintlayout.solver.LinearSystem.a
    public void clear() {
        this.variables.clear();
        this.a = null;
        this.b = 0.0f;
    }

    public ArrayRow createRowDimensionRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, 1.0f);
        this.variables.put(solverVariable3, f);
        this.variables.put(solverVariable4, -f);
        return this;
    }

    public ArrayRow createRowEqualDimension(float f, float f2, float f3, SolverVariable solverVariable, int i2, SolverVariable solverVariable2, int i3, SolverVariable solverVariable3, int i4, SolverVariable solverVariable4, int i5) {
        if (f2 == 0.0f || f == f3) {
            this.b = ((-i2) - i3) + i4 + i5;
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else {
            float f4 = (f / f2) / (f3 / f2);
            this.b = ((-i2) - i3) + (i4 * f4) + (i5 * f4);
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, f4);
            this.variables.put(solverVariable3, -f4);
        }
        return this;
    }

    public ArrayRow createRowEqualMatchDimensions(float f, float f2, float f3, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        this.b = 0.0f;
        if (f2 == 0.0f || f == f3) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else if (f == 0.0f) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
        } else if (f3 == 0.0f) {
            this.variables.put(solverVariable3, 1.0f);
            this.variables.put(solverVariable4, -1.0f);
        } else {
            float f4 = (f / f2) / (f3 / f2);
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable4, f4);
            this.variables.put(solverVariable3, -f4);
        }
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, int i2) {
        ArrayRowVariables arrayRowVariables;
        float f;
        if (i2 < 0) {
            this.b = i2 * (-1);
            arrayRowVariables = this.variables;
            f = 1.0f;
        } else {
            this.b = i2;
            arrayRowVariables = this.variables;
            f = -1.0f;
        }
        arrayRowVariables.put(solverVariable, f);
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, SolverVariable solverVariable2, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.b = i2;
        }
        if (z) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
        } else {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
        }
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable solverVariable, int i2, SolverVariable solverVariable2) {
        this.b = i2;
        this.variables.put(solverVariable, -1.0f);
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.b = i2;
        }
        if (z) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, -1.0f);
        } else {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.variables.put(solverVariable3, 1.0f);
        }
        return this;
    }

    public ArrayRow createRowLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i2) {
        boolean z = false;
        if (i2 != 0) {
            if (i2 < 0) {
                i2 *= -1;
                z = true;
            }
            this.b = i2;
        }
        if (z) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, 1.0f);
        } else {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.variables.put(solverVariable3, -1.0f);
        }
        return this;
    }

    public ArrayRow createRowWithAngle(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f) {
        this.variables.put(solverVariable3, 0.5f);
        this.variables.put(solverVariable4, 0.5f);
        this.variables.put(solverVariable, -0.5f);
        this.variables.put(solverVariable2, -0.5f);
        this.b = -f;
        return this;
    }

    public ArrayRow d(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i3) {
        float f2;
        int i4;
        if (solverVariable2 == solverVariable3) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable4, 1.0f);
            this.variables.put(solverVariable2, -2.0f);
            return this;
        }
        if (f == 0.5f) {
            this.variables.put(solverVariable, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, -1.0f);
            this.variables.put(solverVariable4, 1.0f);
            if (i2 > 0 || i3 > 0) {
                i4 = (-i2) + i3;
                f2 = i4;
            }
            return this;
        }
        if (f <= 0.0f) {
            this.variables.put(solverVariable, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            f2 = i2;
        } else {
            if (f < 1.0f) {
                float f3 = 1.0f - f;
                this.variables.put(solverVariable, f3 * 1.0f);
                this.variables.put(solverVariable2, f3 * (-1.0f));
                this.variables.put(solverVariable3, (-1.0f) * f);
                this.variables.put(solverVariable4, 1.0f * f);
                if (i2 > 0 || i3 > 0) {
                    f2 = ((-i2) * f3) + (i3 * f);
                }
                return this;
            }
            this.variables.put(solverVariable4, -1.0f);
            this.variables.put(solverVariable3, 1.0f);
            i4 = -i3;
            f2 = i4;
        }
        this.b = f2;
        return this;
    }

    public ArrayRow e(SolverVariable solverVariable, int i2) {
        this.a = solverVariable;
        float f = i2;
        solverVariable.computedValue = f;
        this.b = f;
        this.e = true;
        return this;
    }

    public ArrayRow f(SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        this.variables.put(solverVariable, -1.0f);
        this.variables.put(solverVariable2, f);
        return this;
    }

    public void g() {
        float f = this.b;
        if (f < 0.0f) {
            this.b = f * (-1.0f);
            this.variables.invert();
        }
    }

    @Override // androidx.constraintlayout.solver.LinearSystem.a
    public SolverVariable getKey() {
        return this.a;
    }

    @Override // androidx.constraintlayout.solver.LinearSystem.a
    public SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr) {
        return k(zArr, null);
    }

    public boolean h() {
        SolverVariable solverVariable = this.a;
        return solverVariable != null && (solverVariable.e == SolverVariable.Type.UNRESTRICTED || this.b >= 0.0f);
    }

    public boolean i(SolverVariable solverVariable) {
        return this.variables.contains(solverVariable);
    }

    @Override // androidx.constraintlayout.solver.LinearSystem.a
    public void initFromRow(LinearSystem.a aVar) {
        if (aVar instanceof ArrayRow) {
            ArrayRow arrayRow = (ArrayRow) aVar;
            this.a = null;
            this.variables.clear();
            for (int i2 = 0; i2 < arrayRow.variables.getCurrentSize(); i2++) {
                this.variables.add(arrayRow.variables.getVariable(i2), arrayRow.variables.getVariableValue(i2), true);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.LinearSystem.a
    public boolean isEmpty() {
        return this.a == null && this.b == 0.0f && this.variables.getCurrentSize() == 0;
    }

    public void l(SolverVariable solverVariable) {
        SolverVariable solverVariable2 = this.a;
        if (solverVariable2 != null) {
            this.variables.put(solverVariable2, -1.0f);
            this.a.b = -1;
            this.a = null;
        }
        float remove = this.variables.remove(solverVariable, true) * (-1.0f);
        this.a = solverVariable;
        if (remove == 1.0f) {
            return;
        }
        this.b /= remove;
        this.variables.divideByAmount(remove);
    }

    public int m() {
        return (this.a != null ? 4 : 0) + 4 + 4 + this.variables.sizeInBytes();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00bb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String n() {
        /*
            r9 = this;
            androidx.constraintlayout.solver.SolverVariable r0 = r9.a
            java.lang.String r1 = ""
            if (r0 != 0) goto L14
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            java.lang.String r1 = "0"
            r0.append(r1)
            goto L21
        L14:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            androidx.constraintlayout.solver.SolverVariable r1 = r9.a
            r0.append(r1)
        L21:
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = " = "
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            float r1 = r9.b
            r2 = 0
            r3 = 1
            r4 = 0
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L52
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            float r0 = r9.b
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r1 = 1
            goto L53
        L52:
            r1 = 0
        L53:
            androidx.constraintlayout.solver.ArrayRow$ArrayRowVariables r5 = r9.variables
            int r5 = r5.getCurrentSize()
        L59:
            if (r2 >= r5) goto Ld6
            androidx.constraintlayout.solver.ArrayRow$ArrayRowVariables r6 = r9.variables
            androidx.constraintlayout.solver.SolverVariable r6 = r6.getVariable(r2)
            if (r6 != 0) goto L64
            goto Ld3
        L64:
            androidx.constraintlayout.solver.ArrayRow$ArrayRowVariables r7 = r9.variables
            float r7 = r7.getVariableValue(r2)
            int r8 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r8 != 0) goto L6f
            goto Ld3
        L6f:
            java.lang.String r6 = r6.toString()
            r8 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r1 != 0) goto L86
            int r1 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r1 >= 0) goto Laf
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "- "
            goto La6
        L86:
            int r1 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r1 <= 0) goto L9c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = " + "
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            goto Laf
        L9c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = " - "
        La6:
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            float r7 = r7 * r8
        Laf:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 != 0) goto Lbb
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            goto Lc8
        Lbb:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            r1.append(r7)
            java.lang.String r0 = " "
        Lc8:
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
            r1 = 1
        Ld3:
            int r2 = r2 + 1
            goto L59
        Ld6:
            if (r1 != 0) goto Le9
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "0.0"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
        Le9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.ArrayRow.n():java.lang.String");
    }

    public SolverVariable pickPivot(SolverVariable solverVariable) {
        return k(null, solverVariable);
    }

    public void reset() {
        this.a = null;
        this.variables.clear();
        this.b = 0.0f;
        this.e = false;
    }

    public String toString() {
        return n();
    }

    public void updateFromFinalVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z) {
        if (solverVariable.isFinalValue) {
            this.b += solverVariable.computedValue * this.variables.get(solverVariable);
            this.variables.remove(solverVariable, z);
            if (z) {
                solverVariable.removeFromRow(this);
            }
            if (LinearSystem.SIMPLIFY_SYNONYMS && solverVariable != null && this.variables.getCurrentSize() == 0) {
                this.e = true;
                linearSystem.hasSimpleDefinition = true;
            }
        }
    }

    public void updateFromRow(LinearSystem linearSystem, ArrayRow arrayRow, boolean z) {
        this.b += arrayRow.b * this.variables.use(arrayRow, z);
        if (z) {
            arrayRow.a.removeFromRow(this);
        }
        if (LinearSystem.SIMPLIFY_SYNONYMS && this.a != null && this.variables.getCurrentSize() == 0) {
            this.e = true;
            linearSystem.hasSimpleDefinition = true;
        }
    }

    public void updateFromSynonymVariable(LinearSystem linearSystem, SolverVariable solverVariable, boolean z) {
        if (solverVariable.h) {
            float f = this.variables.get(solverVariable);
            this.b += solverVariable.j * f;
            this.variables.remove(solverVariable, z);
            if (z) {
                solverVariable.removeFromRow(this);
            }
            this.variables.add(linearSystem.k.f353d[solverVariable.f360i], f, z);
            if (LinearSystem.SIMPLIFY_SYNONYMS && solverVariable != null && this.variables.getCurrentSize() == 0) {
                this.e = true;
                linearSystem.hasSimpleDefinition = true;
            }
        }
    }

    public void updateFromSystem(LinearSystem linearSystem) {
        if (linearSystem.f.length == 0) {
            return;
        }
        boolean z = false;
        while (!z) {
            int currentSize = this.variables.getCurrentSize();
            for (int i2 = 0; i2 < currentSize; i2++) {
                SolverVariable variable = this.variables.getVariable(i2);
                if (variable.b != -1 || variable.isFinalValue || variable.h) {
                    this.f351d.add(variable);
                }
            }
            int size = this.f351d.size();
            if (size > 0) {
                for (int i3 = 0; i3 < size; i3++) {
                    SolverVariable solverVariable = this.f351d.get(i3);
                    if (solverVariable.isFinalValue) {
                        updateFromFinalVariable(linearSystem, solverVariable, true);
                    } else if (solverVariable.h) {
                        updateFromSynonymVariable(linearSystem, solverVariable, true);
                    } else {
                        updateFromRow(linearSystem, linearSystem.f[solverVariable.b], true);
                    }
                }
                this.f351d.clear();
            } else {
                z = true;
            }
        }
        if (LinearSystem.SIMPLIFY_SYNONYMS && this.a != null && this.variables.getCurrentSize() == 0) {
            this.e = true;
            linearSystem.hasSimpleDefinition = true;
        }
    }
}
