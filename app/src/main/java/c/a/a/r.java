package c.a.a;

/* loaded from: classes.dex */
public class r {
    private final float a;
    private final float b;

    public r(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    private static float a(r rVar, r rVar2, r rVar3) {
        float f = rVar2.a;
        float f2 = rVar2.b;
        return ((rVar3.a - f) * (rVar.b - f2)) - ((rVar3.b - f2) * (rVar.a - f));
    }

    public static float b(r rVar, r rVar2) {
        return c.a.a.x.m.a.a(rVar.a, rVar.b, rVar2.a, rVar2.b);
    }

    public static void e(r[] rVarArr) {
        r rVar;
        r rVar2;
        r rVar3;
        float b = b(rVarArr[0], rVarArr[1]);
        float b2 = b(rVarArr[1], rVarArr[2]);
        float b3 = b(rVarArr[0], rVarArr[2]);
        if (b2 >= b && b2 >= b3) {
            rVar = rVarArr[0];
            rVar2 = rVarArr[1];
            rVar3 = rVarArr[2];
        } else if (b3 < b2 || b3 < b) {
            rVar = rVarArr[2];
            rVar2 = rVarArr[0];
            rVar3 = rVarArr[1];
        } else {
            rVar = rVarArr[1];
            rVar2 = rVarArr[0];
            rVar3 = rVarArr[2];
        }
        if (a(rVar2, rVar, rVar3) < 0.0f) {
            r rVar4 = rVar3;
            rVar3 = rVar2;
            rVar2 = rVar4;
        }
        rVarArr[0] = rVar2;
        rVarArr[1] = rVar;
        rVarArr[2] = rVar3;
    }

    public final float c() {
        return this.a;
    }

    public final float d() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.a == rVar.a && this.b == rVar.b;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.a) * 31) + Float.floatToIntBits(this.b);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(25);
        sb.append('(');
        sb.append(this.a);
        sb.append(',');
        sb.append(this.b);
        sb.append(')');
        return sb.toString();
    }
}
