package androidx.constraintlayout.solver;

import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public class LinearSystem {
    public static long ARRAY_ROW_CREATION = 0;
    public static final boolean DEBUG = false;
    public static final boolean FULL_DEBUG = false;
    public static final boolean MEASURE = false;
    public static long OPTIMIZED_ARRAY_ROW_CREATION = 0;
    public static boolean OPTIMIZED_ENGINE = false;
    public static boolean SIMPLIFY_SYNONYMS = true;
    public static boolean SKIP_COLUMNS = true;
    public static boolean USE_BASIC_SYNONYMS = true;
    public static boolean USE_DEPENDENCY_ORDERING = false;
    public static boolean USE_SYNONYMS = true;
    private static int o = 1000;
    public static Metrics sMetrics;

    /* renamed from: c */
    private a f354c;
    ArrayRow[] f;
    final Cache k;
    private a n;
    public boolean hasSimpleDefinition = false;
    int a = 0;
    private HashMap<String, SolverVariable> b = null;

    /* renamed from: d */
    private int f355d = 32;
    private int e = 32;
    public boolean graphOptimizer = false;
    public boolean newgraphOptimizer = false;
    private boolean[] g = new boolean[32];
    int h = 1;

    /* renamed from: i */
    int f356i = 0;
    private int j = 32;
    private SolverVariable[] l = new SolverVariable[o];
    private int m = 0;

    /* loaded from: classes.dex */
    public interface a {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);

        void initFromRow(a aVar);

        boolean isEmpty();
    }

    /* loaded from: classes.dex */
    public class b extends ArrayRow {
        public b(LinearSystem linearSystem, Cache cache) {
            this.variables = new SolverVariableValues(this, cache);
        }
    }

    public LinearSystem() {
        this.f = null;
        this.f = new ArrayRow[32];
        j();
        Cache cache = new Cache();
        this.k = cache;
        this.f354c = new PriorityGoalRow(cache);
        this.n = OPTIMIZED_ENGINE ? new b(this, cache) : new ArrayRow(cache);
    }

    private SolverVariable a(SolverVariable.Type type, String str) {
        SolverVariable acquire = this.k.f352c.acquire();
        if (acquire == null) {
            acquire = new SolverVariable(type, str);
        } else {
            acquire.reset();
        }
        acquire.setType(type, str);
        int i2 = this.m;
        int i3 = o;
        if (i2 >= i3) {
            int i4 = i3 * 2;
            o = i4;
            this.l = (SolverVariable[]) Arrays.copyOf(this.l, i4);
        }
        SolverVariable[] solverVariableArr = this.l;
        int i5 = this.m;
        this.m = i5 + 1;
        solverVariableArr[i5] = acquire;
        return acquire;
    }

    private final void b(ArrayRow arrayRow) {
        int i2;
        if (SIMPLIFY_SYNONYMS && arrayRow.e) {
            arrayRow.a.setFinalValue(this, arrayRow.b);
        } else {
            ArrayRow[] arrayRowArr = this.f;
            int i3 = this.f356i;
            arrayRowArr[i3] = arrayRow;
            SolverVariable solverVariable = arrayRow.a;
            solverVariable.b = i3;
            this.f356i = i3 + 1;
            solverVariable.updateReferencesWithNewDefinition(this, arrayRow);
        }
        if (SIMPLIFY_SYNONYMS && this.hasSimpleDefinition) {
            int i4 = 0;
            while (i4 < this.f356i) {
                if (this.f[i4] == null) {
                    System.out.println("WTF");
                }
                ArrayRow[] arrayRowArr2 = this.f;
                if (arrayRowArr2[i4] != null && arrayRowArr2[i4].e) {
                    ArrayRow arrayRow2 = arrayRowArr2[i4];
                    arrayRow2.a.setFinalValue(this, arrayRow2.b);
                    (OPTIMIZED_ENGINE ? this.k.a : this.k.b).release(arrayRow2);
                    this.f[i4] = null;
                    int i5 = i4 + 1;
                    int i6 = i5;
                    while (true) {
                        i2 = this.f356i;
                        if (i5 >= i2) {
                            break;
                        }
                        ArrayRow[] arrayRowArr3 = this.f;
                        int i7 = i5 - 1;
                        arrayRowArr3[i7] = arrayRowArr3[i5];
                        if (arrayRowArr3[i7].a.b == i5) {
                            arrayRowArr3[i7].a.b = i7;
                        }
                        i6 = i5;
                        i5++;
                    }
                    if (i6 < i2) {
                        this.f[i6] = null;
                    }
                    this.f356i = i2 - 1;
                    i4--;
                }
                i4++;
            }
            this.hasSimpleDefinition = false;
        }
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, float f) {
        ArrayRow createRow = linearSystem.createRow();
        createRow.f(solverVariable, solverVariable2, f);
        return createRow;
    }

    private void d() {
        for (int i2 = 0; i2 < this.f356i; i2++) {
            ArrayRow arrayRow = this.f[i2];
            arrayRow.a.computedValue = arrayRow.b;
        }
    }

    private void e() {
        System.out.println("Display Rows (" + this.f356i + "x" + this.h + ")\n");
    }

    private int f(a aVar) {
        boolean z;
        int i2 = 0;
        while (true) {
            if (i2 >= this.f356i) {
                z = false;
                break;
            }
            ArrayRow[] arrayRowArr = this.f;
            if (arrayRowArr[i2].a.e != SolverVariable.Type.UNRESTRICTED && arrayRowArr[i2].b < 0.0f) {
                z = true;
                break;
            }
            i2++;
        }
        if (!z) {
            return 0;
        }
        boolean z2 = false;
        int i3 = 0;
        while (!z2) {
            Metrics metrics = sMetrics;
            if (metrics != null) {
                metrics.bfs++;
            }
            i3++;
            float f = Float.MAX_VALUE;
            int i4 = -1;
            int i5 = -1;
            int i6 = 0;
            for (int i7 = 0; i7 < this.f356i; i7++) {
                ArrayRow arrayRow = this.f[i7];
                if (arrayRow.a.e != SolverVariable.Type.UNRESTRICTED && !arrayRow.e && arrayRow.b < 0.0f) {
                    int i8 = 9;
                    if (SKIP_COLUMNS) {
                        int currentSize = arrayRow.variables.getCurrentSize();
                        int i9 = 0;
                        while (i9 < currentSize) {
                            SolverVariable variable = arrayRow.variables.getVariable(i9);
                            float f2 = arrayRow.variables.get(variable);
                            if (f2 > 0.0f) {
                                int i10 = 0;
                                while (i10 < i8) {
                                    float f3 = variable.f358c[i10] / f2;
                                    if ((f3 < f && i10 == i6) || i10 > i6) {
                                        i5 = variable.id;
                                        i6 = i10;
                                        i4 = i7;
                                        f = f3;
                                    }
                                    i10++;
                                    i8 = 9;
                                }
                            }
                            i9++;
                            i8 = 9;
                        }
                    } else {
                        for (int i11 = 1; i11 < this.h; i11++) {
                            SolverVariable solverVariable = this.k.f353d[i11];
                            float f4 = arrayRow.variables.get(solverVariable);
                            if (f4 > 0.0f) {
                                for (int i12 = 0; i12 < 9; i12++) {
                                    float f5 = solverVariable.f358c[i12] / f4;
                                    if ((f5 < f && i12 == i6) || i12 > i6) {
                                        i5 = i11;
                                        i6 = i12;
                                        i4 = i7;
                                        f = f5;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (i4 != -1) {
                ArrayRow arrayRow2 = this.f[i4];
                arrayRow2.a.b = -1;
                Metrics metrics2 = sMetrics;
                if (metrics2 != null) {
                    metrics2.pivots++;
                }
                arrayRow2.l(this.k.f353d[i5]);
                SolverVariable solverVariable2 = arrayRow2.a;
                solverVariable2.b = i4;
                solverVariable2.updateReferencesWithNewDefinition(this, arrayRow2);
            } else {
                z2 = true;
            }
            if (i3 > this.h / 2) {
                z2 = true;
            }
        }
        return i3;
    }

    private void g() {
        int i2 = this.f355d * 2;
        this.f355d = i2;
        this.f = (ArrayRow[]) Arrays.copyOf(this.f, i2);
        Cache cache = this.k;
        cache.f353d = (SolverVariable[]) Arrays.copyOf(cache.f353d, this.f355d);
        int i3 = this.f355d;
        this.g = new boolean[i3];
        this.e = i3;
        this.j = i3;
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.tableSizeIncrease++;
            metrics.maxTableSize = Math.max(metrics.maxTableSize, i3);
            Metrics metrics2 = sMetrics;
            metrics2.lastTableSize = metrics2.maxTableSize;
        }
    }

    public static Metrics getMetrics() {
        return sMetrics;
    }

    private final int i(a aVar, boolean z) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.optimize++;
        }
        for (int i2 = 0; i2 < this.h; i2++) {
            this.g[i2] = false;
        }
        boolean z2 = false;
        int i3 = 0;
        while (!z2) {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.iterations++;
            }
            i3++;
            if (i3 >= this.h * 2) {
                return i3;
            }
            if (aVar.getKey() != null) {
                this.g[aVar.getKey().id] = true;
            }
            SolverVariable pivotCandidate = aVar.getPivotCandidate(this, this.g);
            if (pivotCandidate != null) {
                boolean[] zArr = this.g;
                int i4 = pivotCandidate.id;
                if (zArr[i4]) {
                    return i3;
                }
                zArr[i4] = true;
            }
            if (pivotCandidate != null) {
                float f = Float.MAX_VALUE;
                int i5 = -1;
                for (int i6 = 0; i6 < this.f356i; i6++) {
                    ArrayRow arrayRow = this.f[i6];
                    if (arrayRow.a.e != SolverVariable.Type.UNRESTRICTED && !arrayRow.e && arrayRow.i(pivotCandidate)) {
                        float f2 = arrayRow.variables.get(pivotCandidate);
                        if (f2 < 0.0f) {
                            float f3 = (-arrayRow.b) / f2;
                            if (f3 < f) {
                                i5 = i6;
                                f = f3;
                            }
                        }
                    }
                }
                if (i5 > -1) {
                    ArrayRow arrayRow2 = this.f[i5];
                    arrayRow2.a.b = -1;
                    Metrics metrics3 = sMetrics;
                    if (metrics3 != null) {
                        metrics3.pivots++;
                    }
                    arrayRow2.l(pivotCandidate);
                    SolverVariable solverVariable = arrayRow2.a;
                    solverVariable.b = i5;
                    solverVariable.updateReferencesWithNewDefinition(this, arrayRow2);
                }
            } else {
                z2 = true;
            }
        }
        return i3;
    }

    private void j() {
        int i2 = 0;
        if (OPTIMIZED_ENGINE) {
            while (i2 < this.f356i) {
                ArrayRow arrayRow = this.f[i2];
                if (arrayRow != null) {
                    this.k.a.release(arrayRow);
                }
                this.f[i2] = null;
                i2++;
            }
            return;
        }
        while (i2 < this.f356i) {
            ArrayRow arrayRow2 = this.f[i2];
            if (arrayRow2 != null) {
                this.k.b.release(arrayRow2);
            }
            this.f[i2] = null;
            i2++;
        }
    }

    public void addCenterPoint(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f, int i2) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.LEFT;
        SolverVariable createObjectVariable = createObjectVariable(constraintWidget.getAnchor(type));
        ConstraintAnchor.Type type2 = ConstraintAnchor.Type.TOP;
        SolverVariable createObjectVariable2 = createObjectVariable(constraintWidget.getAnchor(type2));
        ConstraintAnchor.Type type3 = ConstraintAnchor.Type.RIGHT;
        SolverVariable createObjectVariable3 = createObjectVariable(constraintWidget.getAnchor(type3));
        ConstraintAnchor.Type type4 = ConstraintAnchor.Type.BOTTOM;
        SolverVariable createObjectVariable4 = createObjectVariable(constraintWidget.getAnchor(type4));
        SolverVariable createObjectVariable5 = createObjectVariable(constraintWidget2.getAnchor(type));
        SolverVariable createObjectVariable6 = createObjectVariable(constraintWidget2.getAnchor(type2));
        SolverVariable createObjectVariable7 = createObjectVariable(constraintWidget2.getAnchor(type3));
        SolverVariable createObjectVariable8 = createObjectVariable(constraintWidget2.getAnchor(type4));
        ArrayRow createRow = createRow();
        double d2 = f;
        double sin = Math.sin(d2);
        double d3 = i2;
        Double.isNaN(d3);
        createRow.createRowWithAngle(createObjectVariable2, createObjectVariable4, createObjectVariable6, createObjectVariable8, (float) (sin * d3));
        addConstraint(createRow);
        ArrayRow createRow2 = createRow();
        double cos = Math.cos(d2);
        Double.isNaN(d3);
        createRow2.createRowWithAngle(createObjectVariable, createObjectVariable3, createObjectVariable5, createObjectVariable7, (float) (cos * d3));
        addConstraint(createRow2);
    }

    public void addCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i3, int i4) {
        ArrayRow createRow = createRow();
        createRow.d(solverVariable, solverVariable2, i2, f, solverVariable3, solverVariable4, i3);
        if (i4 != 8) {
            createRow.addError(this, i4);
        }
        addConstraint(createRow);
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x009c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addConstraint(androidx.constraintlayout.solver.ArrayRow r8) {
        /*
            r7 = this;
            if (r8 != 0) goto L3
            return
        L3:
            androidx.constraintlayout.solver.Metrics r0 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            r1 = 1
            if (r0 == 0) goto L17
            long r3 = r0.constraints
            long r3 = r3 + r1
            r0.constraints = r3
            boolean r3 = r8.e
            if (r3 == 0) goto L17
            long r3 = r0.simpleconstraints
            long r3 = r3 + r1
            r0.simpleconstraints = r3
        L17:
            int r0 = r7.f356i
            r3 = 1
            int r0 = r0 + r3
            int r4 = r7.j
            if (r0 >= r4) goto L26
            int r0 = r7.h
            int r0 = r0 + r3
            int r4 = r7.e
            if (r0 < r4) goto L29
        L26:
            r7.g()
        L29:
            r0 = 0
            boolean r4 = r8.e
            if (r4 != 0) goto L9e
            r8.updateFromSystem(r7)
            boolean r4 = r8.isEmpty()
            if (r4 == 0) goto L38
            return
        L38:
            r8.g()
            boolean r4 = r8.b(r7)
            if (r4 == 0) goto L95
            androidx.constraintlayout.solver.SolverVariable r4 = r7.createExtraVariable()
            r8.a = r4
            int r5 = r7.f356i
            r7.b(r8)
            int r6 = r7.f356i
            int r5 = r5 + r3
            if (r6 != r5) goto L95
            androidx.constraintlayout.solver.LinearSystem$a r0 = r7.n
            r0.initFromRow(r8)
            androidx.constraintlayout.solver.LinearSystem$a r0 = r7.n
            r7.i(r0, r3)
            int r0 = r4.b
            r5 = -1
            if (r0 != r5) goto L96
            androidx.constraintlayout.solver.SolverVariable r0 = r8.a
            if (r0 != r4) goto L76
            androidx.constraintlayout.solver.SolverVariable r0 = r8.pickPivot(r4)
            if (r0 == 0) goto L76
            androidx.constraintlayout.solver.Metrics r4 = androidx.constraintlayout.solver.LinearSystem.sMetrics
            if (r4 == 0) goto L73
            long r5 = r4.pivots
            long r5 = r5 + r1
            r4.pivots = r5
        L73:
            r8.l(r0)
        L76:
            boolean r0 = r8.e
            if (r0 != 0) goto L7f
            androidx.constraintlayout.solver.SolverVariable r0 = r8.a
            r0.updateReferencesWithNewDefinition(r7, r8)
        L7f:
            boolean r0 = androidx.constraintlayout.solver.LinearSystem.OPTIMIZED_ENGINE
            if (r0 == 0) goto L88
            androidx.constraintlayout.solver.Cache r0 = r7.k
            androidx.constraintlayout.solver.a<androidx.constraintlayout.solver.ArrayRow> r0 = r0.a
            goto L8c
        L88:
            androidx.constraintlayout.solver.Cache r0 = r7.k
            androidx.constraintlayout.solver.a<androidx.constraintlayout.solver.ArrayRow> r0 = r0.b
        L8c:
            r0.release(r8)
            int r0 = r7.f356i
            int r0 = r0 - r3
            r7.f356i = r0
            goto L96
        L95:
            r3 = 0
        L96:
            boolean r0 = r8.h()
            if (r0 != 0) goto L9d
            return
        L9d:
            r0 = r3
        L9e:
            if (r0 != 0) goto La3
            r7.b(r8)
        La3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.LinearSystem.addConstraint(androidx.constraintlayout.solver.ArrayRow):void");
    }

    public ArrayRow addEquality(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        if (USE_BASIC_SYNONYMS && i3 == 8 && solverVariable2.isFinalValue && solverVariable.b == -1) {
            solverVariable.setFinalValue(this, solverVariable2.computedValue + i2);
            return null;
        }
        ArrayRow createRow = createRow();
        createRow.createRowEquals(solverVariable, solverVariable2, i2);
        if (i3 != 8) {
            createRow.addError(this, i3);
        }
        addConstraint(createRow);
        return createRow;
    }

    public void addEquality(SolverVariable solverVariable, int i2) {
        ArrayRow createRow;
        if (USE_BASIC_SYNONYMS && solverVariable.b == -1) {
            float f = i2;
            solverVariable.setFinalValue(this, f);
            for (int i3 = 0; i3 < this.a + 1; i3++) {
                SolverVariable solverVariable2 = this.k.f353d[i3];
                if (solverVariable2 != null && solverVariable2.h && solverVariable2.f360i == solverVariable.id) {
                    solverVariable2.setFinalValue(this, solverVariable2.j + f);
                }
            }
            return;
        }
        int i4 = solverVariable.b;
        if (i4 != -1) {
            ArrayRow arrayRow = this.f[i4];
            if (!arrayRow.e) {
                if (arrayRow.variables.getCurrentSize() == 0) {
                    arrayRow.e = true;
                } else {
                    createRow = createRow();
                    createRow.createRowEquals(solverVariable, i2);
                }
            }
            arrayRow.b = i2;
            return;
        }
        createRow = createRow();
        createRow.e(solverVariable, i2);
        addConstraint(createRow);
    }

    public void addGreaterBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i2);
        addConstraint(createRow);
    }

    public void addGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowGreaterThan(solverVariable, solverVariable2, createSlackVariable, i2);
        if (i3 != 8) {
            c(createRow, (int) (createRow.variables.get(createSlackVariable) * (-1.0f)), i3);
        }
        addConstraint(createRow);
    }

    public void addLowerBarrier(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, boolean z) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i2);
        addConstraint(createRow);
    }

    public void addLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, int i2, int i3) {
        ArrayRow createRow = createRow();
        SolverVariable createSlackVariable = createSlackVariable();
        createSlackVariable.strength = 0;
        createRow.createRowLowerThan(solverVariable, solverVariable2, createSlackVariable, i2);
        if (i3 != 8) {
            c(createRow, (int) (createRow.variables.get(createSlackVariable) * (-1.0f)), i3);
        }
        addConstraint(createRow);
    }

    public void addRatio(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4, float f, int i2) {
        ArrayRow createRow = createRow();
        createRow.createRowDimensionRatio(solverVariable, solverVariable2, solverVariable3, solverVariable4, f);
        if (i2 != 8) {
            createRow.addError(this, i2);
        }
        addConstraint(createRow);
    }

    public void addSynonym(SolverVariable solverVariable, SolverVariable solverVariable2, int i2) {
        if (solverVariable.b != -1 || i2 != 0) {
            addEquality(solverVariable, solverVariable2, i2, 8);
            return;
        }
        if (solverVariable2.h) {
            float f = solverVariable2.j;
            solverVariable2 = this.k.f353d[solverVariable2.f360i];
        }
        if (!solverVariable.h) {
            solverVariable.setSynonym(this, solverVariable2, 0.0f);
        } else {
            float f2 = solverVariable.j;
            SolverVariable solverVariable3 = this.k.f353d[solverVariable.f360i];
        }
    }

    void c(ArrayRow arrayRow, int i2, int i3) {
        arrayRow.a(createErrorVariable(i3, null), i2);
    }

    public SolverVariable createErrorVariable(int i2, String str) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.errors++;
        }
        if (this.h + 1 >= this.e) {
            g();
        }
        SolverVariable a2 = a(SolverVariable.Type.ERROR, str);
        int i3 = this.a + 1;
        this.a = i3;
        this.h++;
        a2.id = i3;
        a2.strength = i2;
        this.k.f353d[i3] = a2;
        this.f354c.addError(a2);
        return a2;
    }

    public SolverVariable createExtraVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.extravariables++;
        }
        if (this.h + 1 >= this.e) {
            g();
        }
        SolverVariable a2 = a(SolverVariable.Type.SLACK, null);
        int i2 = this.a + 1;
        this.a = i2;
        this.h++;
        a2.id = i2;
        this.k.f353d[i2] = a2;
        return a2;
    }

    public SolverVariable createObjectVariable(Object obj) {
        SolverVariable solverVariable = null;
        if (obj == null) {
            return null;
        }
        if (this.h + 1 >= this.e) {
            g();
        }
        if (obj instanceof ConstraintAnchor) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) obj;
            solverVariable = constraintAnchor.getSolverVariable();
            if (solverVariable == null) {
                constraintAnchor.resetSolverVariable(this.k);
                solverVariable = constraintAnchor.getSolverVariable();
            }
            int i2 = solverVariable.id;
            if (i2 == -1 || i2 > this.a || this.k.f353d[i2] == null) {
                if (i2 != -1) {
                    solverVariable.reset();
                }
                int i3 = this.a + 1;
                this.a = i3;
                this.h++;
                solverVariable.id = i3;
                solverVariable.e = SolverVariable.Type.UNRESTRICTED;
                this.k.f353d[i3] = solverVariable;
            }
        }
        return solverVariable;
    }

    public ArrayRow createRow() {
        ArrayRow acquire;
        if (OPTIMIZED_ENGINE) {
            acquire = this.k.a.acquire();
            if (acquire == null) {
                acquire = new b(this, this.k);
                OPTIMIZED_ARRAY_ROW_CREATION++;
            }
            acquire.reset();
        } else {
            acquire = this.k.b.acquire();
            if (acquire == null) {
                acquire = new ArrayRow(this.k);
                ARRAY_ROW_CREATION++;
            }
            acquire.reset();
        }
        SolverVariable.a();
        return acquire;
    }

    public SolverVariable createSlackVariable() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.slackvariables++;
        }
        if (this.h + 1 >= this.e) {
            g();
        }
        SolverVariable a2 = a(SolverVariable.Type.SLACK, null);
        int i2 = this.a + 1;
        this.a = i2;
        this.h++;
        a2.id = i2;
        this.k.f353d[i2] = a2;
        return a2;
    }

    public void displayReadableRows() {
        e();
        String str = " num vars " + this.a + "\n";
        for (int i2 = 0; i2 < this.a + 1; i2++) {
            SolverVariable solverVariable = this.k.f353d[i2];
            if (solverVariable != null && solverVariable.isFinalValue) {
                str = str + " $[" + i2 + "] => " + solverVariable + " = " + solverVariable.computedValue + "\n";
            }
        }
        String str2 = str + "\n";
        for (int i3 = 0; i3 < this.a + 1; i3++) {
            SolverVariable[] solverVariableArr = this.k.f353d;
            SolverVariable solverVariable2 = solverVariableArr[i3];
            if (solverVariable2 != null && solverVariable2.h) {
                str2 = str2 + " ~[" + i3 + "] => " + solverVariable2 + " = " + solverVariableArr[solverVariable2.f360i] + " + " + solverVariable2.j + "\n";
            }
        }
        String str3 = str2 + "\n\n #  ";
        for (int i4 = 0; i4 < this.f356i; i4++) {
            str3 = (str3 + this.f[i4].n()) + "\n #  ";
        }
        if (this.f354c != null) {
            str3 = str3 + "Goal: " + this.f354c + "\n";
        }
        System.out.println(str3);
    }

    public void displayVariablesReadableRows() {
        e();
        String str = "";
        for (int i2 = 0; i2 < this.f356i; i2++) {
            if (this.f[i2].a.e == SolverVariable.Type.UNRESTRICTED) {
                str = (str + this.f[i2].n()) + "\n";
            }
        }
        System.out.println(str + this.f354c + "\n");
    }

    public void fillMetrics(Metrics metrics) {
        sMetrics = metrics;
    }

    public Cache getCache() {
        return this.k;
    }

    public int getMemoryUsed() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.f356i; i3++) {
            ArrayRow[] arrayRowArr = this.f;
            if (arrayRowArr[i3] != null) {
                i2 += arrayRowArr[i3].m();
            }
        }
        return i2;
    }

    public int getNumEquations() {
        return this.f356i;
    }

    public int getNumVariables() {
        return this.a;
    }

    public int getObjectVariableValue(Object obj) {
        SolverVariable solverVariable = ((ConstraintAnchor) obj).getSolverVariable();
        if (solverVariable != null) {
            return (int) (solverVariable.computedValue + 0.5f);
        }
        return 0;
    }

    void h(a aVar) {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimizeGoal++;
            metrics.maxVariables = Math.max(metrics.maxVariables, this.h);
            Metrics metrics2 = sMetrics;
            metrics2.maxRows = Math.max(metrics2.maxRows, this.f356i);
        }
        f(aVar);
        i(aVar, false);
        d();
    }

    public void minimize() {
        Metrics metrics = sMetrics;
        if (metrics != null) {
            metrics.minimize++;
        }
        if (this.f354c.isEmpty()) {
            d();
            return;
        }
        if (this.graphOptimizer || this.newgraphOptimizer) {
            Metrics metrics2 = sMetrics;
            if (metrics2 != null) {
                metrics2.graphOptimizer++;
            }
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= this.f356i) {
                    z = true;
                    break;
                } else if (!this.f[i2].e) {
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                Metrics metrics3 = sMetrics;
                if (metrics3 != null) {
                    metrics3.fullySolved++;
                }
                d();
                return;
            }
        }
        h(this.f354c);
    }

    public void removeRow(ArrayRow arrayRow) {
        SolverVariable solverVariable;
        int i2;
        if (!arrayRow.e || (solverVariable = arrayRow.a) == null) {
            return;
        }
        int i3 = solverVariable.b;
        if (i3 != -1) {
            while (true) {
                i2 = this.f356i;
                if (i3 >= i2 - 1) {
                    break;
                }
                ArrayRow[] arrayRowArr = this.f;
                int i4 = i3 + 1;
                SolverVariable solverVariable2 = arrayRowArr[i4].a;
                if (solverVariable2.b == i4) {
                    solverVariable2.b = i3;
                }
                arrayRowArr[i3] = arrayRowArr[i4];
                i3 = i4;
            }
            this.f356i = i2 - 1;
        }
        SolverVariable solverVariable3 = arrayRow.a;
        if (!solverVariable3.isFinalValue) {
            solverVariable3.setFinalValue(this, arrayRow.b);
        }
        (OPTIMIZED_ENGINE ? this.k.a : this.k.b).release(arrayRow);
    }

    public void reset() {
        Cache cache;
        int i2 = 0;
        while (true) {
            cache = this.k;
            SolverVariable[] solverVariableArr = cache.f353d;
            if (i2 >= solverVariableArr.length) {
                break;
            }
            SolverVariable solverVariable = solverVariableArr[i2];
            if (solverVariable != null) {
                solverVariable.reset();
            }
            i2++;
        }
        cache.f352c.a(this.l, this.m);
        this.m = 0;
        Arrays.fill(this.k.f353d, (Object) null);
        HashMap<String, SolverVariable> hashMap = this.b;
        if (hashMap != null) {
            hashMap.clear();
        }
        this.a = 0;
        this.f354c.clear();
        this.h = 1;
        for (int i3 = 0; i3 < this.f356i; i3++) {
            ArrayRow[] arrayRowArr = this.f;
            if (arrayRowArr[i3] != null) {
                arrayRowArr[i3].f350c = false;
            }
        }
        j();
        this.f356i = 0;
        this.n = OPTIMIZED_ENGINE ? new b(this, this.k) : new ArrayRow(this.k);
    }
}
