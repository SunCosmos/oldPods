package androidx.constraintlayout.solver;

import java.util.Arrays;

/* loaded from: classes.dex */
public class SolverVariable {
    public static final int STRENGTH_BARRIER = 6;
    public static final int STRENGTH_CENTERING = 7;
    public static final int STRENGTH_EQUALITY = 5;
    public static final int STRENGTH_FIXED = 8;
    public static final int STRENGTH_HIGH = 3;
    public static final int STRENGTH_HIGHEST = 4;
    public static final int STRENGTH_LOW = 1;
    public static final int STRENGTH_MEDIUM = 2;
    public static final int STRENGTH_NONE = 0;
    private static int k = 1;
    private String a;
    int b;

    /* renamed from: c, reason: collision with root package name */
    float[] f358c;
    public float computedValue;

    /* renamed from: d, reason: collision with root package name */
    float[] f359d;
    Type e;
    ArrayRow[] f;
    int g;
    boolean h;

    /* renamed from: i, reason: collision with root package name */
    int f360i;
    public int id;
    public boolean inGoal;
    public boolean isFinalValue;
    float j;
    public int strength;
    public int usageInRowCount;

    /* loaded from: classes.dex */
    public enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public SolverVariable(Type type, String str) {
        this.id = -1;
        this.b = -1;
        this.strength = 0;
        this.isFinalValue = false;
        this.f358c = new float[9];
        this.f359d = new float[9];
        this.f = new ArrayRow[16];
        this.g = 0;
        this.usageInRowCount = 0;
        this.h = false;
        this.f360i = -1;
        this.j = 0.0f;
        this.e = type;
    }

    public SolverVariable(String str, Type type) {
        this.id = -1;
        this.b = -1;
        this.strength = 0;
        this.isFinalValue = false;
        this.f358c = new float[9];
        this.f359d = new float[9];
        this.f = new ArrayRow[16];
        this.g = 0;
        this.usageInRowCount = 0;
        this.h = false;
        this.f360i = -1;
        this.j = 0.0f;
        this.a = str;
        this.e = type;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a() {
        k++;
    }

    public final void addToRow(ArrayRow arrayRow) {
        int i2 = 0;
        while (true) {
            int i3 = this.g;
            if (i2 >= i3) {
                ArrayRow[] arrayRowArr = this.f;
                if (i3 >= arrayRowArr.length) {
                    this.f = (ArrayRow[]) Arrays.copyOf(arrayRowArr, arrayRowArr.length * 2);
                }
                ArrayRow[] arrayRowArr2 = this.f;
                int i4 = this.g;
                arrayRowArr2[i4] = arrayRow;
                this.g = i4 + 1;
                return;
            }
            if (this.f[i2] == arrayRow) {
                return;
            } else {
                i2++;
            }
        }
    }

    public String getName() {
        return this.a;
    }

    public final void removeFromRow(ArrayRow arrayRow) {
        int i2 = this.g;
        int i3 = 0;
        while (i3 < i2) {
            if (this.f[i3] == arrayRow) {
                while (i3 < i2 - 1) {
                    ArrayRow[] arrayRowArr = this.f;
                    int i4 = i3 + 1;
                    arrayRowArr[i3] = arrayRowArr[i4];
                    i3 = i4;
                }
                this.g--;
                return;
            }
            i3++;
        }
    }

    public void reset() {
        this.a = null;
        this.e = Type.UNKNOWN;
        this.strength = 0;
        this.id = -1;
        this.b = -1;
        this.computedValue = 0.0f;
        this.isFinalValue = false;
        this.h = false;
        this.f360i = -1;
        this.j = 0.0f;
        int i2 = this.g;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f[i3] = null;
        }
        this.g = 0;
        this.usageInRowCount = 0;
        this.inGoal = false;
        Arrays.fill(this.f359d, 0.0f);
    }

    public void setFinalValue(LinearSystem linearSystem, float f) {
        this.computedValue = f;
        this.isFinalValue = true;
        this.h = false;
        this.f360i = -1;
        this.j = 0.0f;
        int i2 = this.g;
        this.b = -1;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f[i3].updateFromFinalVariable(linearSystem, this, false);
        }
        this.g = 0;
    }

    public void setName(String str) {
        this.a = str;
    }

    public void setSynonym(LinearSystem linearSystem, SolverVariable solverVariable, float f) {
        this.h = true;
        this.f360i = solverVariable.id;
        this.j = f;
        int i2 = this.g;
        this.b = -1;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f[i3].updateFromSynonymVariable(linearSystem, this, false);
        }
        this.g = 0;
        linearSystem.displayReadableRows();
    }

    public void setType(Type type, String str) {
        this.e = type;
    }

    public String toString() {
        StringBuilder sb;
        if (this.a != null) {
            sb = new StringBuilder();
            sb.append("");
            sb.append(this.a);
        } else {
            sb = new StringBuilder();
            sb.append("");
            sb.append(this.id);
        }
        return sb.toString();
    }

    public final void updateReferencesWithNewDefinition(LinearSystem linearSystem, ArrayRow arrayRow) {
        int i2 = this.g;
        for (int i3 = 0; i3 < i2; i3++) {
            this.f[i3].updateFromRow(linearSystem, arrayRow, false);
        }
        this.g = 0;
    }
}
