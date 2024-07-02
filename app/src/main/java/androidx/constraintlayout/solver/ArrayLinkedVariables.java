package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.ArrayRow;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ArrayLinkedVariables implements ArrayRow.ArrayRowVariables {
    private static float l = 0.001f;
    private final ArrayRow b;

    /* renamed from: c, reason: collision with root package name */
    protected final Cache f347c;
    int a = 0;

    /* renamed from: d, reason: collision with root package name */
    private int f348d = 8;
    private SolverVariable e = null;
    private int[] f = new int[8];
    private int[] g = new int[8];
    private float[] h = new float[8];

    /* renamed from: i, reason: collision with root package name */
    private int f349i = -1;
    private int j = -1;
    private boolean k = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayLinkedVariables(ArrayRow arrayRow, Cache cache) {
        this.b = arrayRow;
        this.f347c = cache;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void add(SolverVariable solverVariable, float f, boolean z) {
        float f2 = l;
        if (f <= (-f2) || f >= f2) {
            int i2 = this.f349i;
            if (i2 == -1) {
                this.f349i = 0;
                this.h[0] = f;
                this.f[0] = solverVariable.id;
                this.g[0] = -1;
                solverVariable.usageInRowCount++;
                solverVariable.addToRow(this.b);
                this.a++;
                if (this.k) {
                    return;
                }
                int i3 = this.j + 1;
                this.j = i3;
                int[] iArr = this.f;
                if (i3 >= iArr.length) {
                    this.k = true;
                    this.j = iArr.length - 1;
                    return;
                }
                return;
            }
            int i4 = -1;
            for (int i5 = 0; i2 != -1 && i5 < this.a; i5++) {
                int[] iArr2 = this.f;
                int i6 = iArr2[i2];
                int i7 = solverVariable.id;
                if (i6 == i7) {
                    float[] fArr = this.h;
                    float f3 = fArr[i2] + f;
                    float f4 = l;
                    if (f3 > (-f4) && f3 < f4) {
                        f3 = 0.0f;
                    }
                    fArr[i2] = f3;
                    if (f3 == 0.0f) {
                        if (i2 == this.f349i) {
                            this.f349i = this.g[i2];
                        } else {
                            int[] iArr3 = this.g;
                            iArr3[i4] = iArr3[i2];
                        }
                        if (z) {
                            solverVariable.removeFromRow(this.b);
                        }
                        if (this.k) {
                            this.j = i2;
                        }
                        solverVariable.usageInRowCount--;
                        this.a--;
                        return;
                    }
                    return;
                }
                if (iArr2[i2] < i7) {
                    i4 = i2;
                }
                i2 = this.g[i2];
            }
            int i8 = this.j;
            int i9 = i8 + 1;
            if (this.k) {
                int[] iArr4 = this.f;
                if (iArr4[i8] != -1) {
                    i8 = iArr4.length;
                }
            } else {
                i8 = i9;
            }
            int[] iArr5 = this.f;
            if (i8 >= iArr5.length && this.a < iArr5.length) {
                int i10 = 0;
                while (true) {
                    int[] iArr6 = this.f;
                    if (i10 >= iArr6.length) {
                        break;
                    }
                    if (iArr6[i10] == -1) {
                        i8 = i10;
                        break;
                    }
                    i10++;
                }
            }
            int[] iArr7 = this.f;
            if (i8 >= iArr7.length) {
                i8 = iArr7.length;
                int i11 = this.f348d * 2;
                this.f348d = i11;
                this.k = false;
                this.j = i8 - 1;
                this.h = Arrays.copyOf(this.h, i11);
                this.f = Arrays.copyOf(this.f, this.f348d);
                this.g = Arrays.copyOf(this.g, this.f348d);
            }
            this.f[i8] = solverVariable.id;
            this.h[i8] = f;
            int[] iArr8 = this.g;
            if (i4 != -1) {
                iArr8[i8] = iArr8[i4];
                iArr8[i4] = i8;
            } else {
                iArr8[i8] = this.f349i;
                this.f349i = i8;
            }
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.b);
            this.a++;
            if (!this.k) {
                this.j++;
            }
            int i12 = this.j;
            int[] iArr9 = this.f;
            if (i12 >= iArr9.length) {
                this.k = true;
                this.j = iArr9.length - 1;
            }
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public final void clear() {
        int i2 = this.f349i;
        for (int i3 = 0; i2 != -1 && i3 < this.a; i3++) {
            SolverVariable solverVariable = this.f347c.f353d[this.f[i2]];
            if (solverVariable != null) {
                solverVariable.removeFromRow(this.b);
            }
            i2 = this.g[i2];
        }
        this.f349i = -1;
        this.j = -1;
        this.k = false;
        this.a = 0;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public boolean contains(SolverVariable solverVariable) {
        int i2 = this.f349i;
        if (i2 == -1) {
            return false;
        }
        for (int i3 = 0; i2 != -1 && i3 < this.a; i3++) {
            if (this.f[i2] == solverVariable.id) {
                return true;
            }
            i2 = this.g[i2];
        }
        return false;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void display() {
        int i2 = this.a;
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
        int i2 = this.f349i;
        for (int i3 = 0; i2 != -1 && i3 < this.a; i3++) {
            float[] fArr = this.h;
            fArr[i2] = fArr[i2] / f;
            i2 = this.g[i2];
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public final float get(SolverVariable solverVariable) {
        int i2 = this.f349i;
        for (int i3 = 0; i2 != -1 && i3 < this.a; i3++) {
            if (this.f[i2] == solverVariable.id) {
                return this.h[i2];
            }
            i2 = this.g[i2];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public int getCurrentSize() {
        return this.a;
    }

    public int getHead() {
        return this.f349i;
    }

    public final int getId(int i2) {
        return this.f[i2];
    }

    public final int getNextIndice(int i2) {
        return this.g[i2];
    }

    public final float getValue(int i2) {
        return this.h[i2];
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public SolverVariable getVariable(int i2) {
        int i3 = this.f349i;
        for (int i4 = 0; i3 != -1 && i4 < this.a; i4++) {
            if (i4 == i2) {
                return this.f347c.f353d[this.f[i3]];
            }
            i3 = this.g[i3];
        }
        return null;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public float getVariableValue(int i2) {
        int i3 = this.f349i;
        for (int i4 = 0; i3 != -1 && i4 < this.a; i4++) {
            if (i4 == i2) {
                return this.h[i3];
            }
            i3 = this.g[i3];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public int indexOf(SolverVariable solverVariable) {
        int i2 = this.f349i;
        if (i2 == -1) {
            return -1;
        }
        for (int i3 = 0; i2 != -1 && i3 < this.a; i3++) {
            if (this.f[i2] == solverVariable.id) {
                return i2;
            }
            i2 = this.g[i2];
        }
        return -1;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public void invert() {
        int i2 = this.f349i;
        for (int i3 = 0; i2 != -1 && i3 < this.a; i3++) {
            float[] fArr = this.h;
            fArr[i2] = fArr[i2] * (-1.0f);
            i2 = this.g[i2];
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public final void put(SolverVariable solverVariable, float f) {
        if (f == 0.0f) {
            remove(solverVariable, true);
            return;
        }
        int i2 = this.f349i;
        if (i2 == -1) {
            this.f349i = 0;
            this.h[0] = f;
            this.f[0] = solverVariable.id;
            this.g[0] = -1;
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.b);
            this.a++;
            if (this.k) {
                return;
            }
            int i3 = this.j + 1;
            this.j = i3;
            int[] iArr = this.f;
            if (i3 >= iArr.length) {
                this.k = true;
                this.j = iArr.length - 1;
                return;
            }
            return;
        }
        int i4 = -1;
        for (int i5 = 0; i2 != -1 && i5 < this.a; i5++) {
            int[] iArr2 = this.f;
            int i6 = iArr2[i2];
            int i7 = solverVariable.id;
            if (i6 == i7) {
                this.h[i2] = f;
                return;
            }
            if (iArr2[i2] < i7) {
                i4 = i2;
            }
            i2 = this.g[i2];
        }
        int i8 = this.j;
        int i9 = i8 + 1;
        if (this.k) {
            int[] iArr3 = this.f;
            if (iArr3[i8] != -1) {
                i8 = iArr3.length;
            }
        } else {
            i8 = i9;
        }
        int[] iArr4 = this.f;
        if (i8 >= iArr4.length && this.a < iArr4.length) {
            int i10 = 0;
            while (true) {
                int[] iArr5 = this.f;
                if (i10 >= iArr5.length) {
                    break;
                }
                if (iArr5[i10] == -1) {
                    i8 = i10;
                    break;
                }
                i10++;
            }
        }
        int[] iArr6 = this.f;
        if (i8 >= iArr6.length) {
            i8 = iArr6.length;
            int i11 = this.f348d * 2;
            this.f348d = i11;
            this.k = false;
            this.j = i8 - 1;
            this.h = Arrays.copyOf(this.h, i11);
            this.f = Arrays.copyOf(this.f, this.f348d);
            this.g = Arrays.copyOf(this.g, this.f348d);
        }
        this.f[i8] = solverVariable.id;
        this.h[i8] = f;
        int[] iArr7 = this.g;
        if (i4 != -1) {
            iArr7[i8] = iArr7[i4];
            iArr7[i4] = i8;
        } else {
            iArr7[i8] = this.f349i;
            this.f349i = i8;
        }
        solverVariable.usageInRowCount++;
        solverVariable.addToRow(this.b);
        int i12 = this.a + 1;
        this.a = i12;
        if (!this.k) {
            this.j++;
        }
        int[] iArr8 = this.f;
        if (i12 >= iArr8.length) {
            this.k = true;
        }
        if (this.j >= iArr8.length) {
            this.k = true;
            this.j = iArr8.length - 1;
        }
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public final float remove(SolverVariable solverVariable, boolean z) {
        if (this.e == solverVariable) {
            this.e = null;
        }
        int i2 = this.f349i;
        if (i2 == -1) {
            return 0.0f;
        }
        int i3 = 0;
        int i4 = -1;
        while (i2 != -1 && i3 < this.a) {
            if (this.f[i2] == solverVariable.id) {
                if (i2 == this.f349i) {
                    this.f349i = this.g[i2];
                } else {
                    int[] iArr = this.g;
                    iArr[i4] = iArr[i2];
                }
                if (z) {
                    solverVariable.removeFromRow(this.b);
                }
                solverVariable.usageInRowCount--;
                this.a--;
                this.f[i2] = -1;
                if (this.k) {
                    this.j = i2;
                }
                return this.h[i2];
            }
            i3++;
            i4 = i2;
            i2 = this.g[i2];
        }
        return 0.0f;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public int sizeInBytes() {
        return (this.f.length * 4 * 3) + 0 + 36;
    }

    public String toString() {
        int i2 = this.f349i;
        String str = "";
        for (int i3 = 0; i2 != -1 && i3 < this.a; i3++) {
            str = ((str + " -> ") + this.h[i2] + " : ") + this.f347c.f353d[this.f[i2]];
            i2 = this.g[i2];
        }
        return str;
    }

    @Override // androidx.constraintlayout.solver.ArrayRow.ArrayRowVariables
    public float use(ArrayRow arrayRow, boolean z) {
        float f = get(arrayRow.a);
        remove(arrayRow.a, z);
        ArrayRow.ArrayRowVariables arrayRowVariables = arrayRow.variables;
        int currentSize = arrayRowVariables.getCurrentSize();
        for (int i2 = 0; i2 < currentSize; i2++) {
            SolverVariable variable = arrayRowVariables.getVariable(i2);
            add(variable, arrayRowVariables.get(variable) * f, z);
        }
        return f;
    }
}
