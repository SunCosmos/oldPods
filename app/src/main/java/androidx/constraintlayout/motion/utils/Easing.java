package androidx.constraintlayout.motion.utils;

import android.util.Log;
import java.util.Arrays;

/* loaded from: classes.dex */
public class Easing {
    String a = "identity";
    static Easing b = new Easing();
    public static String[] NAMED_EASING = {"standard", "accelerate", "decelerate", "linear"};

    /* loaded from: classes.dex */
    static class a extends Easing {
        private static double g = 0.01d;
        private static double h = 1.0E-4d;

        /* renamed from: c, reason: collision with root package name */
        double f280c;

        /* renamed from: d, reason: collision with root package name */
        double f281d;
        double e;
        double f;

        a(String str) {
            this.a = str;
            int indexOf = str.indexOf(40);
            int indexOf2 = str.indexOf(44, indexOf);
            this.f280c = Double.parseDouble(str.substring(indexOf + 1, indexOf2).trim());
            int i2 = indexOf2 + 1;
            int indexOf3 = str.indexOf(44, i2);
            this.f281d = Double.parseDouble(str.substring(i2, indexOf3).trim());
            int i3 = indexOf3 + 1;
            int indexOf4 = str.indexOf(44, i3);
            this.e = Double.parseDouble(str.substring(i3, indexOf4).trim());
            int i4 = indexOf4 + 1;
            this.f = Double.parseDouble(str.substring(i4, str.indexOf(41, i4)).trim());
        }

        private double a(double d2) {
            double d3 = 1.0d - d2;
            double d4 = 3.0d * d3;
            return (this.f280c * d3 * d4 * d2) + (this.e * d4 * d2 * d2) + (d2 * d2 * d2);
        }

        private double b(double d2) {
            double d3 = 1.0d - d2;
            double d4 = 3.0d * d3;
            return (this.f281d * d3 * d4 * d2) + (this.f * d4 * d2 * d2) + (d2 * d2 * d2);
        }

        @Override // androidx.constraintlayout.motion.utils.Easing
        public double get(double d2) {
            if (d2 <= 0.0d) {
                return 0.0d;
            }
            if (d2 >= 1.0d) {
                return 1.0d;
            }
            double d3 = 0.5d;
            double d4 = 0.5d;
            while (d3 > g) {
                d3 *= 0.5d;
                d4 = a(d4) < d2 ? d4 + d3 : d4 - d3;
            }
            double d5 = d4 - d3;
            double a = a(d5);
            double d6 = d4 + d3;
            double a2 = a(d6);
            double b = b(d5);
            return (((b(d6) - b) * (d2 - a)) / (a2 - a)) + b;
        }

        @Override // androidx.constraintlayout.motion.utils.Easing
        public double getDiff(double d2) {
            double d3 = 0.5d;
            double d4 = 0.5d;
            while (d3 > h) {
                d3 *= 0.5d;
                d4 = a(d4) < d2 ? d4 + d3 : d4 - d3;
            }
            double d5 = d4 - d3;
            double d6 = d4 + d3;
            return (b(d6) - b(d5)) / (a(d6) - a(d5));
        }
    }

    public static Easing getInterpolator(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith("cubic")) {
            return new a(str);
        }
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1354466595:
                if (str.equals("accelerate")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1263948740:
                if (str.equals("decelerate")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1102672091:
                if (str.equals("linear")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1312628413:
                if (str.equals("standard")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return new a("cubic(0.4, 0.05, 0.8, 0.7)");
            case 1:
                return new a("cubic(0.0, 0.0, 0.2, 0.95)");
            case 2:
                return new a("cubic(1, 1, 0, 0)");
            case 3:
                return new a("cubic(0.4, 0.0, 0.2, 1)");
            default:
                Log.e("ConstraintSet", "transitionEasing syntax error syntax:transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or " + Arrays.toString(NAMED_EASING));
                return b;
        }
    }

    public double get(double d2) {
        return d2;
    }

    public double getDiff(double d2) {
        return 1.0d;
    }

    public String toString() {
        return this.a;
    }
}
