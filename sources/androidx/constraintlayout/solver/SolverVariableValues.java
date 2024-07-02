package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.ArrayRow;
import java.util.Arrays;

/* loaded from: classes.dex */
public class SolverVariableValues implements ArrayRow.ArrayRowVariables {
    private static float m = 0.001f;
    private int a = 16;
    private int b = 16;

    /* renamed from: c, reason: collision with root package name */
    int[] f361c = new int[16];

    /* renamed from: d, reason: collision with root package name */
    int[] f362d = new int[16];
    int[] e = new int[16];
    float[] f = new float[16];
    int[] g = new int[16];
    int[] h = new int[16];

    /* renamed from: i, reason: collision with root package name */
    int f363i = 0;
    int j = -1;
    private final ArrayRow k;
    protected final Cache l;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SolverVariableValues(ArrayRow arrayRow, Cache cache) {
        this.k = arrayRow;
        this.l = cache;
        clear();
    }

    private void a(SolverVariable solverVariable, int i2) {
        int[] iArr;
        int i3 = solverVariable.id % this.b;
        int[] iArr2 = this.f361c;
        int i4 = iArr2[i3];
        if (i4 == -1) {
            iArr2[i3] = i2;
        } else {
            while (true) {
                iArr = this.f362d;
                if (iArr[i4] == -1) {
                    break;
                } else {
                    i4 = iArr[i4];
                }
            }
            iArr[i4] = i2;
        }
        this.f362d[i2] = -1;
    }

    private void b(int i2, SolverVariable solverVariable, float f) {
        this.e[i2] = solverVariable.id;
        this.f[i2] = f;
        this.g[i2] = -1;
        this.h[i2] = -1;
        solverVariable.addToRow(this.k);
        solverVariable.usageInRowCount++;
        this.f363i++;
    }

    private int c() {
        for (int i2 = 0; i2 < this.a; i2++) {
            if (this.e[i2] == -1) {
                return i2;
            }
        }
        return -1;
    }

    private void d() {
        int i2 = this.a * 2;
        this.e = Arrays.copyOf(this.e, i2);
        this.f = Arrays.copyOf(this.f, i2);
        this.g = Arrays.copyOf(this.g, i2);
        this.h = Arrays.copyOf(this.h, i2);
        this.f362d = Arrays.copyOf(this.f362d, i2);
        for (int i3 = this.a; i3 < i2; i3++) {
            this.e[i3] = -1;
            this.f362d[i3] = -1;
        }
        this.a = i2;
    }

    private void e(int i2, SolverVariable solverVariable, float f) {
        int c2 = c();
        b(c2, solverVariable, f);
        if (i2 != -1) {
            this.g[c2] = i2;
            int[] iArr = this.h;
            iArr[c2] = iArr[i2];
            iArr[i2] = c2;
        } else {
            this.g[c2] = -1;
            if (this.f363i > 0) {
                this.h[c2] = this.j;
                this.j = c2;
            } else {
                this.h[c2] = -1;
            }
        }
        int[] iArr2 = this.h;
        if (iArr2[c2] != -1) {
            this.g[iArr2[c2]] = c2;
        }
        a(solverVariable, c2);
    }

    private void f(SolverVariable solverVariable) {
        int[] iArr;
        int i2 = solverVariable.id;
        int i3 = i2 % this.b;
        int[] iArr2 = this.f361c;
        int i4 = iArr2[i3];
        if (i4 == -1) {
            return;
        }
        if (this.e[i4] == i2) {
            int[] iArr3 = this.f362d;
            iArr2[i3] = iArr3[i4];
            iArr3[i4] = -1;
            return;
        }
        while (true) {
            iArr = this.f362d;
            if (iArr[i4] == -1 || this.e[iArr[i4]] == i2) {
                break;
            } else {
                i4 = iArr[i4];
            }
        }
        int i5 = iArr[i4];
        if (i5 == -1 || this.e[i5] != i2) {
            return;
        }
        iArr[i4] = iArr[i5];
        iArr[i5] = -1;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void add(SolverVariable solverVariable, float f, boolean z) {
        float f2 = m;
        if (f <= (-f2) || f >= f2) {
            int indexOf = indexOf(solverVariable);
            if (indexOf == -1) {
                put(solverVariable, f);
                return;
            }
            float[] fArr = this.f;
            fArr[indexOf] = fArr[indexOf] + f;
            float f3 = fArr[indexOf];
            float f4 = m;
            if (f3 <= (-f4) || fArr[indexOf] >= f4) {
                return;
            }
            fArr[indexOf] = 0.0f;
            remove(solverVariable, z);
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void clear() {
        int i2 = this.f363i;
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable variable = getVariable(i3);
            if (variable != null) {
                variable.removeFromRow(this.k);
            }
        }
        for (int i4 = 0; i4 < this.a; i4++) {
            this.e[i4] = -1;
            this.f362d[i4] = -1;
        }
        for (int i5 = 0; i5 < this.b; i5++) {
            this.f361c[i5] = -1;
        }
        this.f363i = 0;
        this.j = -1;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public boolean contains(SolverVariable solverVariable) {
        return indexOf(solverVariable) != -1;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void display() {
        int i2 = this.f363i;
        System.out.print("{ ");
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable variable = getVariable(i3);
            if (variable != null) {
                System.out.print(variable + " = " + getVariableValue(i3) + " ");
            }
        }
        System.out.println(" }");
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void divideByAmount(float f) {
        int i2 = this.f363i;
        int i3 = this.j;
        for (int i4 = 0; i4 < i2; i4++) {
            float[] fArr = this.f;
            fArr[i3] = fArr[i3] / f;
            i3 = this.h[i3];
            if (i3 == -1) {
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public float get(SolverVariable solverVariable) {
        int indexOf = indexOf(solverVariable);
        if (indexOf != -1) {
            return this.f[indexOf];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public int getCurrentSize() {
        return this.f363i;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public SolverVariable getVariable(int i2) {
        int i3 = this.f363i;
        if (i3 == 0) {
            return null;
        }
        int i4 = this.j;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i5 == i2 && i4 != -1) {
                return this.l.f353d[this.e[i4]];
            }
            i4 = this.h[i4];
            if (i4 == -1) {
                break;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public float getVariableValue(int i2) {
        int i3 = this.f363i;
        int i4 = this.j;
        for (int i5 = 0; i5 < i3; i5++) {
            if (i5 == i2) {
                return this.f[i4];
            }
            i4 = this.h[i4];
            if (i4 == -1) {
                return 0.0f;
            }
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public int indexOf(SolverVariable solverVariable) {
        int[] iArr;
        if (this.f363i != 0 && solverVariable != null) {
            int i2 = solverVariable.id;
            int i3 = this.f361c[i2 % this.b];
            if (i3 == -1) {
                return -1;
            }
            if (this.e[i3] == i2) {
                return i3;
            }
            while (true) {
                iArr = this.f362d;
                if (iArr[i3] == -1 || this.e[iArr[i3]] == i2) {
                    break;
                }
                i3 = iArr[i3];
            }
            if (iArr[i3] != -1 && this.e[iArr[i3]] == i2) {
                return iArr[i3];
            }
        }
        return -1;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void invert() {
        int i2 = this.f363i;
        int i3 = this.j;
        for (int i4 = 0; i4 < i2; i4++) {
            float[] fArr = this.f;
            fArr[i3] = fArr[i3] * (-1.0f);
            i3 = this.h[i3];
            if (i3 == -1) {
                return;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void put(SolverVariable solverVariable, float f) {
        float f2 = m;
        if (f > (-f2) && f < f2) {
            remove(solverVariable, true);
            return;
        }
        if (this.f363i == 0) {
            b(0, solverVariable, f);
            a(solverVariable, 0);
            this.j = 0;
            return;
        }
        int indexOf = indexOf(solverVariable);
        if (indexOf != -1) {
            this.f[indexOf] = f;
            return;
        }
        if (this.f363i + 1 >= this.a) {
            d();
        }
        int i2 = this.f363i;
        int i3 = this.j;
        int i4 = -1;
        for (int i5 = 0; i5 < i2; i5++) {
            int[] iArr = this.e;
            int i6 = iArr[i3];
            int i7 = solverVariable.id;
            if (i6 == i7) {
                this.f[i3] = f;
                return;
            }
            if (iArr[i3] < i7) {
                i4 = i3;
            }
            i3 = this.h[i3];
            if (i3 == -1) {
                break;
            }
        }
        e(i4, solverVariable, f);
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public float remove(SolverVariable solverVariable, boolean z) {
        int indexOf = indexOf(solverVariable);
        if (indexOf == -1) {
            return 0.0f;
        }
        f(solverVariable);
        float f = this.f[indexOf];
        if (this.j == indexOf) {
            this.j = this.h[indexOf];
        }
        this.e[indexOf] = -1;
        int[] iArr = this.g;
        if (iArr[indexOf] != -1) {
            int[] iArr2 = this.h;
            iArr2[iArr[indexOf]] = iArr2[indexOf];
        }
        int[] iArr3 = this.h;
        if (iArr3[indexOf] != -1) {
            iArr[iArr3[indexOf]] = iArr[indexOf];
        }
        this.f363i--;
        solverVariable.usageInRowCount--;
        if (z) {
            solverVariable.removeFromRow(this.k);
        }
        return f;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public int sizeInBytes() {
        return 0;
    }

    public String toString() {
        StringBuilder sb;
        String str = hashCode() + " { ";
        int i2 = this.f363i;
        for (int i3 = 0; i3 < i2; i3++) {
            SolverVariable variable = getVariable(i3);
            if (variable != null) {
                String str2 = str + variable + " = " + getVariableValue(i3) + " ";
                int indexOf = indexOf(variable);
                String str3 = str2 + "[p: ";
                if (this.g[indexOf] != -1) {
                    sb = new StringBuilder();
                    sb.append(str3);
                    sb.append(this.l.f353d[this.e[this.g[indexOf]]]);
                } else {
                    sb = new StringBuilder();
                    sb.append(str3);
                    sb.append("none");
                }
                String str4 = sb.toString() + ", n: ";
                str = (this.h[indexOf] != -1 ? str4 + this.l.f353d[this.e[this.h[indexOf]]] : str4 + "none") + "]";
            }
        }
        return str + " }";
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public float use(ArrayRow arrayRow, boolean z) {
        float f = get(arrayRow.a);
        remove(arrayRow.a, z);
        SolverVariableValues solverVariableValues = (SolverVariableValues) arrayRow.variables;
        int currentSize = solverVariableValues.getCurrentSize();
        int i2 = solverVariableValues.j;
        int i3 = 0;
        int i4 = 0;
        while (i3 < currentSize) {
            int[] iArr = solverVariableValues.e;
            if (iArr[i4] != -1) {
                add(this.l.f353d[iArr[i4]], solverVariableValues.f[i4] * f, z);
                i3++;
            }
            i4++;
        }
        return f;
    }
}
