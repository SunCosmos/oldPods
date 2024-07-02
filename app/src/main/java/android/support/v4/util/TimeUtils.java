package android.support.v4.util;

import android.support.annotation.RestrictTo;
import java.io.PrintWriter;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: lib/Mus.dex */
public final class TimeUtils {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int HUNDRED_DAY_FIELD_LEN = 19;
    private static final int SECONDS_PER_DAY = 86400;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final Object sFormatSync = new Object();
    private static char[] sFormatStr = new char[24];

    private static int accumField(int i2, int i3, boolean z, int i4) {
        if (i2 > 99 || (z && i4 >= 3)) {
            return 3 + i3;
        }
        if (i2 > 9 || (z && i4 >= 2)) {
            return 2 + i3;
        }
        if (z || i2 > 0) {
            return 1 + i3;
        }
        return 0;
    }

    private static int printField(char[] cArr, int i2, char c2, int i3, boolean z, int i4) {
        int i5 = i2;
        int i6 = i3;
        if (z || i5 > 0) {
            if ((z && i4 >= 3) || i5 > 99) {
                int i7 = i5 / 100;
                cArr[i6] = (char) (i7 + 48);
                i6++;
                i5 -= i7 * 100;
            }
            if ((z && i4 >= 2) || i5 > 9 || i6 != i6) {
                int i8 = i5 / 10;
                cArr[i6] = (char) (i8 + 48);
                i6++;
                i5 -= i8 * 10;
            }
            cArr[i6] = (char) (i5 + 48);
            int i9 = i6 + 1;
            cArr[i9] = c2;
            i6 = i9 + 1;
        }
        return i6;
    }

    private static int formatDurationLocked(long j, int i2) {
        char c2;
        long j2 = j;
        if (sFormatStr.length < i2) {
            sFormatStr = new char[i2];
        }
        char[] cArr = sFormatStr;
        if (j2 == 0) {
            int i3 = i2 - 1;
            while (0 < i3) {
                cArr[0] = ' ';
            }
            cArr[0] = '0';
            return 0 + 1;
        }
        if (j2 > 0) {
            c2 = '+';
        } else {
            c2 = '-';
            j2 = -j2;
        }
        int i4 = (int) (j2 % 1000);
        int floor = (int) Math.floor(j2 / 1000);
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        if (floor > SECONDS_PER_DAY) {
            i5 = floor / SECONDS_PER_DAY;
            floor -= i5 * SECONDS_PER_DAY;
        }
        if (floor > SECONDS_PER_HOUR) {
            i6 = floor / SECONDS_PER_HOUR;
            floor -= i6 * SECONDS_PER_HOUR;
        }
        if (floor > 60) {
            i7 = floor / 60;
            floor -= i7 * 60;
        }
        int i8 = 0;
        if (i2 != 0) {
            int accumField = accumField(i5, 1, false, 0);
            int accumField2 = accumField + accumField(i6, 1, accumField > 0, 2);
            int accumField3 = accumField2 + accumField(i7, 1, accumField2 > 0, 2);
            int accumField4 = accumField3 + accumField(floor, 1, accumField3 > 0, 2);
            for (int accumField5 = accumField4 + accumField(i4, 2, true, accumField4 > 0 ? 3 : 0) + 1; accumField5 < i2; accumField5++) {
                cArr[i8] = ' ';
                i8++;
            }
        }
        cArr[i8] = c2;
        int i9 = i8 + 1;
        boolean z = i2 != 0;
        int printField = printField(cArr, i5, 'd', i9, false, 0);
        int printField2 = printField(cArr, i6, 'h', printField, printField != i9, z ? 2 : 0);
        int printField3 = printField(cArr, i7, 'm', printField2, printField2 != i9, z ? 2 : 0);
        int printField4 = printField(cArr, floor, 's', printField3, printField3 != i9, z ? 2 : 0);
        int printField5 = printField(cArr, i4, 'm', printField4, true, (!z || printField4 == i9) ? 0 : 3);
        cArr[printField5] = 's';
        return printField5 + 1;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j, StringBuilder sb) {
        synchronized (sFormatSync) {
            sb.append(sFormatStr, 0, formatDurationLocked(j, 0));
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j, PrintWriter printWriter, int i2) {
        synchronized (sFormatSync) {
            printWriter.print(new String(sFormatStr, 0, formatDurationLocked(j, i2)));
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j, PrintWriter printWriter) {
        formatDuration(j, printWriter, 0);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void formatDuration(long j, long j2, PrintWriter printWriter) {
        if (j == 0) {
            printWriter.print("--");
        } else {
            formatDuration(j - j2, printWriter, 0);
        }
    }

    private TimeUtils() {
    }
}
