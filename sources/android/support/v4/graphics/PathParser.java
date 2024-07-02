package android.support.v4.graphics;

import android.graphics.Path;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: lib/Mus.dex */
public class PathParser {
    private static final String LOGTAG = "PathParser";

    static float[] copyOfRange(float[] fArr, int i2, int i3) {
        if (i2 > i3) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (i2 < 0 || i2 > length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i4 = i3 - i2;
        int min = Math.min(i4, length - i2);
        float[] fArr2 = new float[i4];
        System.arraycopy(fArr, i2, fArr2, 0, min);
        return fArr2;
    }

    public static Path createPathFromPathData(String str) {
        Path path = new Path();
        PathDataNode[] createNodesFromPathData = createNodesFromPathData(str);
        if (createNodesFromPathData != null) {
            try {
                PathDataNode.nodesToPath(createNodesFromPathData, path);
                return path;
            } catch (RuntimeException e) {
                throw new RuntimeException("Error in parsing " + str, e);
            }
        }
        return null;
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        if (str == null) {
            return null;
        }
        int i2 = 0;
        int i3 = 1;
        ArrayList arrayList = new ArrayList();
        while (i3 < str.length()) {
            int nextStart = nextStart(str, i3);
            String trim = str.substring(i2, nextStart).trim();
            if (trim.length() > 0) {
                addNode(arrayList, trim.charAt(0), getFloats(trim));
            }
            i2 = nextStart;
            i3 = nextStart + 1;
        }
        if (i3 - i2 == 1 && i2 < str.length()) {
            addNode(arrayList, str.charAt(i2), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[arrayList.size()]);
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        if (pathDataNodeArr == null) {
            return null;
        }
        PathDataNode[] pathDataNodeArr2 = new PathDataNode[pathDataNodeArr.length];
        for (int i2 = 0; i2 < pathDataNodeArr.length; i2++) {
            pathDataNodeArr2[i2] = new PathDataNode(pathDataNodeArr[i2]);
        }
        return pathDataNodeArr2;
    }

    public static boolean canMorph(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null) {
            return false;
        }
        if (pathDataNodeArr.length != pathDataNodeArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 < pathDataNodeArr.length; i2++) {
            if (pathDataNodeArr[i2].mType != pathDataNodeArr2[i2].mType || pathDataNodeArr[i2].mParams.length != pathDataNodeArr2[i2].mParams.length) {
                return false;
            }
        }
        return true;
    }

    public static void updateNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        for (int i2 = 0; i2 < pathDataNodeArr2.length; i2++) {
            pathDataNodeArr[i2].mType = pathDataNodeArr2[i2].mType;
            for (int i3 = 0; i3 < pathDataNodeArr2[i2].mParams.length; i3++) {
                pathDataNodeArr[i2].mParams[i3] = pathDataNodeArr2[i2].mParams[i3];
            }
        }
    }

    private static int nextStart(String str, int i2) {
        int i3 = i2;
        while (i3 < str.length()) {
            char charAt = str.charAt(i3);
            if (((charAt - 'A') * (charAt - 'Z') <= 0 || (charAt - 'a') * (charAt - 'z') <= 0) && charAt != 'e' && charAt != 'E') {
                return i3;
            }
            i3++;
        }
        return i3;
    }

    private static void addNode(ArrayList<PathDataNode> arrayList, char c2, float[] fArr) {
        arrayList.add(new PathDataNode(c2, fArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: lib/Mus.dex */
    public static class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

        ExtractFloatResult() {
        }
    }

    private static float[] getFloats(String str) {
        if (str.charAt(0) == 'z' || str.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            int i2 = 0;
            int i3 = 1;
            ExtractFloatResult extractFloatResult = new ExtractFloatResult();
            int length = str.length();
            while (i3 < length) {
                extract(str, i3, extractFloatResult);
                int i4 = extractFloatResult.mEndPosition;
                if (i3 < i4) {
                    int i5 = i2;
                    i2++;
                    fArr[i5] = Float.parseFloat(str.substring(i3, i4));
                }
                if (extractFloatResult.mEndWithNegOrDot) {
                    i3 = i4;
                } else {
                    i3 = i4 + 1;
                }
            }
            return copyOfRange(fArr, 0, i2);
        } catch (NumberFormatException e) {
            throw new RuntimeException("error in parsing \"" + str + "\"", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x004f A[LOOP:0: B:2:0x000f->B:8:0x004f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0029 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void extract(java.lang.String r11, int r12, android.support.v4.graphics.PathParser.ExtractFloatResult r13) {
        /*
            r0 = r11
            r1 = r12
            r2 = r13
            r9 = r1
            r3 = r9
            r9 = 0
            r4 = r9
            r9 = r2
            r10 = 0
            r9.mEndWithNegOrDot = r10
            r9 = 0
            r5 = r9
            r9 = 0
            r6 = r9
        Lf:
            r9 = r3
            r10 = r0
            int r10 = r10.length()
            if (r9 >= r10) goto L29
            r9 = r6
            r7 = r9
            r9 = 0
            r6 = r9
            r9 = r0
            r10 = r3
            char r9 = r9.charAt(r10)
            r8 = r9
            r9 = r8
            switch(r9) {
                case 32: goto L2e;
                case 44: goto L2e;
                case 45: goto L31;
                case 46: goto L3f;
                case 69: goto L4c;
                case 101: goto L4c;
                default: goto L26;
            }
        L26:
            r9 = r4
            if (r9 == 0) goto L4f
        L29:
            r9 = r2
            r10 = r3
            r9.mEndPosition = r10
            return
        L2e:
            r9 = 1
            r4 = r9
            goto L26
        L31:
            r9 = r3
            r10 = r1
            if (r9 == r10) goto L26
            r9 = r7
            if (r9 != 0) goto L26
            r9 = 1
            r4 = r9
            r9 = r2
            r10 = 1
            r9.mEndWithNegOrDot = r10
            goto L26
        L3f:
            r9 = r5
            if (r9 != 0) goto L45
            r9 = 1
            r5 = r9
            goto L26
        L45:
            r9 = 1
            r4 = r9
            r9 = r2
            r10 = 1
            r9.mEndWithNegOrDot = r10
            goto L26
        L4c:
            r9 = 1
            r6 = r9
            goto L26
        L4f:
            int r3 = r3 + 1
            goto Lf
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.PathParser.extract(java.lang.String, int, android.support.v4.graphics.PathParser$ExtractFloatResult):void");
    }

    /* loaded from: lib/Mus.dex */
    public static class PathDataNode {

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public float[] mParams;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public char mType;

        PathDataNode(char c2, float[] fArr) {
            this.mType = c2;
            this.mParams = fArr;
        }

        PathDataNode(PathDataNode pathDataNode) {
            this.mType = pathDataNode.mType;
            this.mParams = PathParser.copyOfRange(pathDataNode.mParams, 0, pathDataNode.mParams.length);
        }

        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            float[] fArr = new float[6];
            char c2 = 'm';
            for (int i2 = 0; i2 < pathDataNodeArr.length; i2++) {
                addCommand(path, fArr, c2, pathDataNodeArr[i2].mType, pathDataNodeArr[i2].mParams);
                c2 = pathDataNodeArr[i2].mType;
            }
        }

        public void interpolatePathDataNode(PathDataNode pathDataNode, PathDataNode pathDataNode2, float f) {
            for (int i2 = 0; i2 < pathDataNode.mParams.length; i2++) {
                this.mParams[i2] = (pathDataNode.mParams[i2] * (1.0f - f)) + (pathDataNode2.mParams[i2] * f);
            }
        }

        private static void addCommand(Path path, float[] fArr, char c2, char c3, float[] fArr2) {
            char c4 = c2;
            int i2 = 2;
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[2];
            float f4 = fArr[3];
            float f5 = fArr[4];
            float f6 = fArr[5];
            switch (c3) {
                case 'A':
                case 'a':
                    i2 = 7;
                    break;
                case 'C':
                case 'c':
                    i2 = 6;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i2 = 1;
                    break;
                case 'L':
                case 'M':
                case 'T':
                case 'l':
                case 'm':
                case 't':
                    i2 = 2;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i2 = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    f = f5;
                    f2 = f6;
                    f3 = f5;
                    f4 = f6;
                    path.moveTo(f, f2);
                    break;
            }
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 < fArr2.length) {
                    switch (c3) {
                        case 'A':
                            drawArc(path, f, f2, fArr2[i4 + 5], fArr2[i4 + 6], fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3] != 0.0f, fArr2[i4 + 4] != 0.0f);
                            f = fArr2[i4 + 5];
                            f2 = fArr2[i4 + 6];
                            f3 = f;
                            f4 = f2;
                            break;
                        case 'C':
                            path.cubicTo(fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3], fArr2[i4 + 4], fArr2[i4 + 5]);
                            f = fArr2[i4 + 4];
                            f2 = fArr2[i4 + 5];
                            f3 = fArr2[i4 + 2];
                            f4 = fArr2[i4 + 3];
                            break;
                        case 'H':
                            path.lineTo(fArr2[i4 + 0], f2);
                            f = fArr2[i4 + 0];
                            break;
                        case 'L':
                            path.lineTo(fArr2[i4 + 0], fArr2[i4 + 1]);
                            f = fArr2[i4 + 0];
                            f2 = fArr2[i4 + 1];
                            break;
                        case 'M':
                            f = fArr2[i4 + 0];
                            f2 = fArr2[i4 + 1];
                            if (i4 > 0) {
                                path.lineTo(fArr2[i4 + 0], fArr2[i4 + 1]);
                                break;
                            } else {
                                path.moveTo(fArr2[i4 + 0], fArr2[i4 + 1]);
                                f5 = f;
                                f6 = f2;
                                break;
                            }
                        case 'Q':
                            path.quadTo(fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3]);
                            f3 = fArr2[i4 + 0];
                            f4 = fArr2[i4 + 1];
                            f = fArr2[i4 + 2];
                            f2 = fArr2[i4 + 3];
                            break;
                        case 'S':
                            float f7 = f;
                            float f8 = f2;
                            if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                                f7 = (2.0f * f) - f3;
                                f8 = (2.0f * f2) - f4;
                            }
                            path.cubicTo(f7, f8, fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3]);
                            f3 = fArr2[i4 + 0];
                            f4 = fArr2[i4 + 1];
                            f = fArr2[i4 + 2];
                            f2 = fArr2[i4 + 3];
                            break;
                        case 'T':
                            float f9 = f;
                            float f10 = f2;
                            if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                                f9 = (2.0f * f) - f3;
                                f10 = (2.0f * f2) - f4;
                            }
                            path.quadTo(f9, f10, fArr2[i4 + 0], fArr2[i4 + 1]);
                            f3 = f9;
                            f4 = f10;
                            f = fArr2[i4 + 0];
                            f2 = fArr2[i4 + 1];
                            break;
                        case 'V':
                            path.lineTo(f, fArr2[i4 + 0]);
                            f2 = fArr2[i4 + 0];
                            break;
                        case 'a':
                            drawArc(path, f, f2, fArr2[i4 + 5] + f, fArr2[i4 + 6] + f2, fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3] != 0.0f, fArr2[i4 + 4] != 0.0f);
                            f += fArr2[i4 + 5];
                            f2 += fArr2[i4 + 6];
                            f3 = f;
                            f4 = f2;
                            break;
                        case 'c':
                            path.rCubicTo(fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3], fArr2[i4 + 4], fArr2[i4 + 5]);
                            f3 = f + fArr2[i4 + 2];
                            f4 = f2 + fArr2[i4 + 3];
                            f += fArr2[i4 + 4];
                            f2 += fArr2[i4 + 5];
                            break;
                        case 'h':
                            path.rLineTo(fArr2[i4 + 0], 0.0f);
                            f += fArr2[i4 + 0];
                            break;
                        case 'l':
                            path.rLineTo(fArr2[i4 + 0], fArr2[i4 + 1]);
                            f += fArr2[i4 + 0];
                            f2 += fArr2[i4 + 1];
                            break;
                        case 'm':
                            f += fArr2[i4 + 0];
                            f2 += fArr2[i4 + 1];
                            if (i4 > 0) {
                                path.rLineTo(fArr2[i4 + 0], fArr2[i4 + 1]);
                                break;
                            } else {
                                path.rMoveTo(fArr2[i4 + 0], fArr2[i4 + 1]);
                                f5 = f;
                                f6 = f2;
                                break;
                            }
                        case 'q':
                            path.rQuadTo(fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3]);
                            f3 = f + fArr2[i4 + 0];
                            f4 = f2 + fArr2[i4 + 1];
                            f += fArr2[i4 + 2];
                            f2 += fArr2[i4 + 3];
                            break;
                        case 's':
                            float f11 = 0.0f;
                            float f12 = 0.0f;
                            if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                                f11 = f - f3;
                                f12 = f2 - f4;
                            }
                            path.rCubicTo(f11, f12, fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3]);
                            f3 = f + fArr2[i4 + 0];
                            f4 = f2 + fArr2[i4 + 1];
                            f += fArr2[i4 + 2];
                            f2 += fArr2[i4 + 3];
                            break;
                        case 't':
                            float f13 = 0.0f;
                            float f14 = 0.0f;
                            if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                                f13 = f - f3;
                                f14 = f2 - f4;
                            }
                            path.rQuadTo(f13, f14, fArr2[i4 + 0], fArr2[i4 + 1]);
                            f3 = f + f13;
                            f4 = f2 + f14;
                            f += fArr2[i4 + 0];
                            f2 += fArr2[i4 + 1];
                            break;
                        case 'v':
                            path.rLineTo(0.0f, fArr2[i4 + 0]);
                            f2 += fArr2[i4 + 0];
                            break;
                    }
                    c4 = c3;
                    i3 = i4 + i2;
                } else {
                    fArr[0] = f;
                    fArr[1] = f2;
                    fArr[2] = f3;
                    fArr[3] = f4;
                    fArr[4] = f5;
                    fArr[5] = f6;
                    return;
                }
            }
        }

        private static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double d2;
            double d3;
            double radians = Math.toRadians(f7);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d4 = ((f * cos) + (f2 * sin)) / f5;
            double d5 = (((-f) * sin) + (f2 * cos)) / f6;
            double d6 = ((f3 * cos) + (f4 * sin)) / f5;
            double d7 = (((-f3) * sin) + (f4 * cos)) / f6;
            double d8 = d4 - d6;
            double d9 = d5 - d7;
            double d10 = (d4 + d6) / 2.0d;
            double d11 = (d5 + d7) / 2.0d;
            double d12 = (d8 * d8) + (d9 * d9);
            if (d12 == 0.0d) {
                Log.w(PathParser.LOGTAG, " Points are coincident");
                return;
            }
            double d13 = (1.0d / d12) - 0.25d;
            if (d13 < 0.0d) {
                Log.w(PathParser.LOGTAG, "Points are too far apart " + d12);
                float sqrt = (float) (Math.sqrt(d12) / 1.99999d);
                drawArc(path, f, f2, f3, f4, f5 * sqrt, f6 * sqrt, f7, z, z2);
                return;
            }
            double sqrt2 = Math.sqrt(d13);
            double d14 = sqrt2 * d8;
            double d15 = sqrt2 * d9;
            if (z == z2) {
                d2 = d10 - d15;
                d3 = d11 + d14;
            } else {
                d2 = d10 + d15;
                d3 = d11 - d14;
            }
            double atan2 = Math.atan2(d5 - d3, d4 - d2);
            double atan22 = Math.atan2(d7 - d3, d6 - d2) - atan2;
            if (z2 != (atan22 >= 0.0d)) {
                if (atan22 > 0.0d) {
                    atan22 -= 6.283185307179586d;
                } else {
                    atan22 += 6.283185307179586d;
                }
            }
            double d16 = d2 * f5;
            double d17 = d3 * f6;
            arcToBezier(path, (d16 * cos) - (d17 * sin), (d16 * sin) + (d17 * cos), f5, f6, f, f2, radians, atan2, atan22);
        }

        private static void arcToBezier(Path path, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
            double d11 = d6;
            double d12 = d7;
            int ceil = (int) Math.ceil(Math.abs((d10 * 4.0d) / 3.141592653589793d));
            double d13 = d9;
            double cos = Math.cos(d8);
            double sin = Math.sin(d8);
            double cos2 = Math.cos(d13);
            double sin2 = Math.sin(d13);
            double d14 = (((-d4) * cos) * sin2) - ((d5 * sin) * cos2);
            double d15 = ((-d4) * sin * sin2) + (d5 * cos * cos2);
            double d16 = d10 / ceil;
            for (int i2 = 0; i2 < ceil; i2++) {
                double d17 = d13 + d16;
                double sin3 = Math.sin(d17);
                double cos3 = Math.cos(d17);
                double d18 = (d2 + ((d4 * cos) * cos3)) - ((d5 * sin) * sin3);
                double d19 = d3 + (d4 * sin * cos3) + (d5 * cos * sin3);
                double d20 = (((-d4) * cos) * sin3) - ((d5 * sin) * cos3);
                double d21 = ((-d4) * sin * sin3) + (d5 * cos * cos3);
                double tan = Math.tan((d17 - d13) / 2.0d);
                double sin4 = (Math.sin(d17 - d13) * (Math.sqrt(4.0d + ((3.0d * tan) * tan)) - 1.0d)) / 3.0d;
                path.rLineTo(0.0f, 0.0f);
                path.cubicTo((float) (d11 + (sin4 * d14)), (float) (d12 + (sin4 * d15)), (float) (d18 - (sin4 * d20)), (float) (d19 - (sin4 * d21)), (float) d18, (float) d19);
                d13 = d17;
                d11 = d18;
                d12 = d19;
                d14 = d20;
                d15 = d21;
            }
        }
    }
}
