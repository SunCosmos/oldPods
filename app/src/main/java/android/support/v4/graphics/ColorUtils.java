package android.support.v4.graphics;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

/* loaded from: lib/Mus.dex */
public final class ColorUtils {
    private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
    private static final int MIN_ALPHA_SEARCH_PRECISION = 1;
    private static final ThreadLocal<double[]> TEMP_ARRAY = new ThreadLocal<>();
    private static final double XYZ_EPSILON = 0.008856d;
    private static final double XYZ_KAPPA = 903.3d;
    private static final double XYZ_WHITE_REFERENCE_X = 95.047d;
    private static final double XYZ_WHITE_REFERENCE_Y = 100.0d;
    private static final double XYZ_WHITE_REFERENCE_Z = 108.883d;

    private ColorUtils() {
    }

    public static int compositeColors(@ColorInt int i2, @ColorInt int i3) {
        int alpha = Color.alpha(i3);
        int alpha2 = Color.alpha(i2);
        int compositeAlpha = compositeAlpha(alpha2, alpha);
        return Color.argb(compositeAlpha, compositeComponent(Color.red(i2), alpha2, Color.red(i3), alpha, compositeAlpha), compositeComponent(Color.green(i2), alpha2, Color.green(i3), alpha, compositeAlpha), compositeComponent(Color.blue(i2), alpha2, Color.blue(i3), alpha, compositeAlpha));
    }

    private static int compositeAlpha(int i2, int i3) {
        return 255 - (((255 - i3) * (255 - i2)) / 255);
    }

    private static int compositeComponent(int i2, int i3, int i4, int i5, int i6) {
        if (i6 == 0) {
            return 0;
        }
        return (((255 * i2) * i3) + ((i4 * i5) * (255 - i3))) / (i6 * 255);
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public static double calculateLuminance(@ColorInt int i2) {
        double[] tempDouble3Array = getTempDouble3Array();
        colorToXYZ(i2, tempDouble3Array);
        return tempDouble3Array[1] / XYZ_WHITE_REFERENCE_Y;
    }

    public static double calculateContrast(@ColorInt int i2, @ColorInt int i3) {
        int i4 = i2;
        if (Color.alpha(i3) != 255) {
            throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(i3));
        }
        if (Color.alpha(i4) < 255) {
            i4 = compositeColors(i4, i3);
        }
        double calculateLuminance = calculateLuminance(i4) + 0.05d;
        double calculateLuminance2 = calculateLuminance(i3) + 0.05d;
        return Math.max(calculateLuminance, calculateLuminance2) / Math.min(calculateLuminance, calculateLuminance2);
    }

    public static int calculateMinimumAlpha(@ColorInt int i2, @ColorInt int i3, float f) {
        if (Color.alpha(i3) != 255) {
            throw new IllegalArgumentException("background can not be translucent: #" + Integer.toHexString(i3));
        }
        if (calculateContrast(setAlphaComponent(i2, 255), i3) < f) {
            return -1;
        }
        int i4 = 0;
        int i5 = 255;
        for (int i6 = 0; i6 <= 10 && i5 - i4 > 1; i6++) {
            int i7 = (i4 + i5) / 2;
            if (calculateContrast(setAlphaComponent(i2, i7), i3) < f) {
                i4 = i7;
            } else {
                i5 = i7;
            }
        }
        return i5;
    }

    public static void RGBToHSL(@IntRange(from = 0, to = 255) int i2, @IntRange(from = 0, to = 255) int i3, @IntRange(from = 0, to = 255) int i4, @NonNull float[] fArr) {
        float f;
        float abs;
        float f2 = i2 / 255.0f;
        float f3 = i3 / 255.0f;
        float f4 = i4 / 255.0f;
        float max = Math.max(f2, Math.max(f3, f4));
        float min = Math.min(f2, Math.min(f3, f4));
        float f5 = max - min;
        float f6 = (max + min) / 2.0f;
        if (max == min) {
            abs = 0.0f;
            f = 0.0f;
        } else {
            if (max == f2) {
                f = ((f3 - f4) / f5) % 6.0f;
            } else if (max == f3) {
                f = ((f4 - f2) / f5) + 2.0f;
            } else {
                f = ((f2 - f3) / f5) + 4.0f;
            }
            abs = f5 / (1.0f - Math.abs((2.0f * f6) - 1.0f));
        }
        float f7 = (f * 60.0f) % 360.0f;
        if (f7 < 0.0f) {
            f7 += 360.0f;
        }
        fArr[0] = constrain(f7, 0.0f, 360.0f);
        fArr[1] = constrain(abs, 0.0f, 1.0f);
        fArr[2] = constrain(f6, 0.0f, 1.0f);
    }

    public static void colorToHSL(@ColorInt int i2, @NonNull float[] fArr) {
        RGBToHSL(Color.red(i2), Color.green(i2), Color.blue(i2), fArr);
    }

    @ColorInt
    public static int HSLToColor(@NonNull float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float abs = (1.0f - Math.abs((2.0f * f3) - 1.0f)) * f2;
        float f4 = f3 - (0.5f * abs);
        float abs2 = abs * (1.0f - Math.abs(((f / 60.0f) % 2.0f) - 1.0f));
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        switch (((int) f) / 60) {
            case 0:
                i2 = Math.round(255.0f * (abs + f4));
                i3 = Math.round(255.0f * (abs2 + f4));
                i4 = Math.round(255.0f * f4);
                break;
            case 1:
                i2 = Math.round(255.0f * (abs2 + f4));
                i3 = Math.round(255.0f * (abs + f4));
                i4 = Math.round(255.0f * f4);
                break;
            case 2:
                i2 = Math.round(255.0f * f4);
                i3 = Math.round(255.0f * (abs + f4));
                i4 = Math.round(255.0f * (abs2 + f4));
                break;
            case 3:
                i2 = Math.round(255.0f * f4);
                i3 = Math.round(255.0f * (abs2 + f4));
                i4 = Math.round(255.0f * (abs + f4));
                break;
            case 4:
                i2 = Math.round(255.0f * (abs2 + f4));
                i3 = Math.round(255.0f * f4);
                i4 = Math.round(255.0f * (abs + f4));
                break;
            case 5:
            case 6:
                i2 = Math.round(255.0f * (abs + f4));
                i3 = Math.round(255.0f * f4);
                i4 = Math.round(255.0f * (abs2 + f4));
                break;
        }
        return Color.rgb(constrain(i2, 0, 255), constrain(i3, 0, 255), constrain(i4, 0, 255));
    }

    @ColorInt
    public static int setAlphaComponent(@ColorInt int i2, @IntRange(from = 0, to = 255) int i3) {
        if (i3 < 0 || i3 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i2 & 16777215) | (i3 << 24);
    }

    public static void colorToLAB(@ColorInt int i2, @NonNull double[] dArr) {
        RGBToLAB(Color.red(i2), Color.green(i2), Color.blue(i2), dArr);
    }

    public static void RGBToLAB(@IntRange(from = 0, to = 255) int i2, @IntRange(from = 0, to = 255) int i3, @IntRange(from = 0, to = 255) int i4, @NonNull double[] dArr) {
        RGBToXYZ(i2, i3, i4, dArr);
        XYZToLAB(dArr[0], dArr[1], dArr[2], dArr);
    }

    public static void colorToXYZ(@ColorInt int i2, @NonNull double[] dArr) {
        RGBToXYZ(Color.red(i2), Color.green(i2), Color.blue(i2), dArr);
    }

    public static void RGBToXYZ(@IntRange(from = 0, to = 255) int i2, @IntRange(from = 0, to = 255) int i3, @IntRange(from = 0, to = 255) int i4, @NonNull double[] dArr) {
        if (dArr.length != 3) {
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }
        double d2 = i2 / 255.0d;
        double pow = d2 < 0.04045d ? d2 / 12.92d : Math.pow((d2 + 0.055d) / 1.055d, 2.4d);
        double d3 = i3 / 255.0d;
        double pow2 = d3 < 0.04045d ? d3 / 12.92d : Math.pow((d3 + 0.055d) / 1.055d, 2.4d);
        double d4 = i4 / 255.0d;
        double pow3 = d4 < 0.04045d ? d4 / 12.92d : Math.pow((d4 + 0.055d) / 1.055d, 2.4d);
        dArr[0] = XYZ_WHITE_REFERENCE_Y * ((pow * 0.4124d) + (pow2 * 0.3576d) + (pow3 * 0.1805d));
        dArr[1] = XYZ_WHITE_REFERENCE_Y * ((pow * 0.2126d) + (pow2 * 0.7152d) + (pow3 * 0.0722d));
        dArr[2] = XYZ_WHITE_REFERENCE_Y * ((pow * 0.0193d) + (pow2 * 0.1192d) + (pow3 * 0.9505d));
    }

    public static void XYZToLAB(@FloatRange(from = 0.0d, to = 95.047d) double d2, @FloatRange(from = 0.0d, to = 100.0d) double d3, @FloatRange(from = 0.0d, to = 108.883d) double d4, @NonNull double[] dArr) {
        if (dArr.length != 3) {
            throw new IllegalArgumentException("outLab must have a length of 3.");
        }
        double pivotXyzComponent = pivotXyzComponent(d2 / XYZ_WHITE_REFERENCE_X);
        double pivotXyzComponent2 = pivotXyzComponent(d3 / XYZ_WHITE_REFERENCE_Y);
        double pivotXyzComponent3 = pivotXyzComponent(d4 / XYZ_WHITE_REFERENCE_Z);
        dArr[0] = Math.max(0.0d, (116.0d * pivotXyzComponent2) - 16.0d);
        dArr[1] = 500.0d * (pivotXyzComponent - pivotXyzComponent2);
        dArr[2] = 200.0d * (pivotXyzComponent2 - pivotXyzComponent3);
    }

    public static void LABToXYZ(@FloatRange(from = 0.0d, to = 100.0d) double d2, @FloatRange(from = -128.0d, to = 127.0d) double d3, @FloatRange(from = -128.0d, to = 127.0d) double d4, @NonNull double[] dArr) {
        double d5 = (d2 + 16.0d) / 116.0d;
        double d6 = (d3 / 500.0d) + d5;
        double d7 = d5 - (d4 / 200.0d);
        double pow = Math.pow(d6, 3.0d);
        double d8 = pow > XYZ_EPSILON ? pow : ((116.0d * d6) - 16.0d) / XYZ_KAPPA;
        double pow2 = d2 > 7.9996247999999985d ? Math.pow(d5, 3.0d) : d2 / XYZ_KAPPA;
        double pow3 = Math.pow(d7, 3.0d);
        double d9 = pow3 > XYZ_EPSILON ? pow3 : ((116.0d * d7) - 16.0d) / XYZ_KAPPA;
        dArr[0] = d8 * XYZ_WHITE_REFERENCE_X;
        dArr[1] = pow2 * XYZ_WHITE_REFERENCE_Y;
        dArr[2] = d9 * XYZ_WHITE_REFERENCE_Z;
    }

    @ColorInt
    public static int XYZToColor(@FloatRange(from = 0.0d, to = 95.047d) double d2, @FloatRange(from = 0.0d, to = 100.0d) double d3, @FloatRange(from = 0.0d, to = 108.883d) double d4) {
        double d5 = (((d2 * 3.2406d) + (d3 * (-1.5372d))) + (d4 * (-0.4986d))) / XYZ_WHITE_REFERENCE_Y;
        double d6 = (((d2 * (-0.9689d)) + (d3 * 1.8758d)) + (d4 * 0.0415d)) / XYZ_WHITE_REFERENCE_Y;
        double d7 = (((d2 * 0.0557d) + (d3 * (-0.204d))) + (d4 * 1.057d)) / XYZ_WHITE_REFERENCE_Y;
        return Color.rgb(constrain((int) Math.round((d5 > 0.0031308d ? (1.055d * Math.pow(d5, 0.4166666666666667d)) - 0.055d : 12.92d * d5) * 255.0d), 0, 255), constrain((int) Math.round((d6 > 0.0031308d ? (1.055d * Math.pow(d6, 0.4166666666666667d)) - 0.055d : 12.92d * d6) * 255.0d), 0, 255), constrain((int) Math.round((d7 > 0.0031308d ? (1.055d * Math.pow(d7, 0.4166666666666667d)) - 0.055d : 12.92d * d7) * 255.0d), 0, 255));
    }

    @ColorInt
    public static int LABToColor(@FloatRange(from = 0.0d, to = 100.0d) double d2, @FloatRange(from = -128.0d, to = 127.0d) double d3, @FloatRange(from = -128.0d, to = 127.0d) double d4) {
        double[] tempDouble3Array = getTempDouble3Array();
        LABToXYZ(d2, d3, d4, tempDouble3Array);
        return XYZToColor(tempDouble3Array[0], tempDouble3Array[1], tempDouble3Array[2]);
    }

    public static double distanceEuclidean(@NonNull double[] dArr, @NonNull double[] dArr2) {
        return Math.sqrt(Math.pow(dArr[0] - dArr2[0], 2.0d) + Math.pow(dArr[1] - dArr2[1], 2.0d) + Math.pow(dArr[2] - dArr2[2], 2.0d));
    }

    private static float constrain(float f, float f2, float f3) {
        float f4;
        if (f < f2) {
            f4 = f2;
        } else {
            f4 = f > f3 ? f3 : f;
        }
        return f4;
    }

    private static int constrain(int i2, int i3, int i4) {
        int i5;
        if (i2 < i3) {
            i5 = i3;
        } else {
            i5 = i2 > i4 ? i4 : i2;
        }
        return i5;
    }

    private static double pivotXyzComponent(double d2) {
        double d3;
        if (d2 <= XYZ_EPSILON) {
            d3 = ((XYZ_KAPPA * d2) + 16.0d) / 116.0d;
        } else {
            d3 = Math.pow(d2, 0.3333333333333333d);
        }
        return d3;
    }

    @ColorInt
    public static int blendARGB(@ColorInt int i2, @ColorInt int i3, @FloatRange(from = 0.0d, to = 1.0d) float f) {
        float f2 = 1.0f - f;
        return Color.argb((int) ((Color.alpha(i2) * f2) + (Color.alpha(i3) * f)), (int) ((Color.red(i2) * f2) + (Color.red(i3) * f)), (int) ((Color.green(i2) * f2) + (Color.green(i3) * f)), (int) ((Color.blue(i2) * f2) + (Color.blue(i3) * f)));
    }

    public static void blendHSL(@NonNull float[] fArr, @NonNull float[] fArr2, @FloatRange(from = 0.0d, to = 1.0d) float f, @NonNull float[] fArr3) {
        if (fArr3.length != 3) {
            throw new IllegalArgumentException("result must have a length of 3.");
        }
        float f2 = 1.0f - f;
        fArr3[0] = circularInterpolate(fArr[0], fArr2[0], f);
        fArr3[1] = (fArr[1] * f2) + (fArr2[1] * f);
        fArr3[2] = (fArr[2] * f2) + (fArr2[2] * f);
    }

    public static void blendLAB(@NonNull double[] dArr, @NonNull double[] dArr2, @FloatRange(from = 0.0d, to = 1.0d) double d2, @NonNull double[] dArr3) {
        if (dArr3.length != 3) {
            throw new IllegalArgumentException("outResult must have a length of 3.");
        }
        double d3 = 1.0d - d2;
        dArr3[0] = (dArr[0] * d3) + (dArr2[0] * d2);
        dArr3[1] = (dArr[1] * d3) + (dArr2[1] * d2);
        dArr3[2] = (dArr[2] * d3) + (dArr2[2] * d2);
    }

    @VisibleForTesting
    static float circularInterpolate(float f, float f2, float f3) {
        float f4 = f;
        float f5 = f2;
        if (Math.abs(f5 - f4) > 180.0f) {
            if (f5 > f4) {
                f4 += 360.0f;
            } else {
                f5 += 360.0f;
            }
        }
        return (f4 + ((f5 - f4) * f3)) % 360.0f;
    }

    private static double[] getTempDouble3Array() {
        double[] dArr = TEMP_ARRAY.get();
        if (dArr == null) {
            dArr = new double[3];
            TEMP_ARRAY.set(dArr);
        }
        return dArr;
    }
}
