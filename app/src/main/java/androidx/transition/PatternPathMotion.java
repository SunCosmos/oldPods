package androidx.transition;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class PatternPathMotion extends PathMotion {
    private Path a;
    private final Path b;

    /* renamed from: c, reason: collision with root package name */
    private final Matrix f1054c;

    public PatternPathMotion() {
        Path path = new Path();
        this.b = path;
        this.f1054c = new Matrix();
        path.lineTo(1.0f, 0.0f);
        this.a = path;
    }

    @SuppressLint({"RestrictedApi"})
    public PatternPathMotion(Context context, AttributeSet attributeSet) {
        this.b = new Path();
        this.f1054c = new Matrix();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, o.k);
        try {
            String namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, (XmlPullParser) attributeSet, "patternPathData", 0);
            if (namedString == null) {
                throw new RuntimeException("pathData must be supplied for patternPathMotion");
            }
            setPatternPath(PathParser.createPathFromPathData(namedString));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public PatternPathMotion(Path path) {
        this.b = new Path();
        this.f1054c = new Matrix();
        setPatternPath(path);
    }

    private static float a(float f, float f2) {
        return (float) Math.sqrt((f * f) + (f2 * f2));
    }

    @Override // androidx.transition.PathMotion
    public Path getPath(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        float a = a(f5, f6);
        double atan2 = Math.atan2(f6, f5);
        this.f1054c.setScale(a, a);
        this.f1054c.postRotate((float) Math.toDegrees(atan2));
        this.f1054c.postTranslate(f, f2);
        Path path = new Path();
        this.b.transform(this.f1054c, path);
        return path;
    }

    public Path getPatternPath() {
        return this.a;
    }

    public void setPatternPath(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float[] fArr = new float[2];
        pathMeasure.getPosTan(pathMeasure.getLength(), fArr, null);
        float f = fArr[0];
        float f2 = fArr[1];
        pathMeasure.getPosTan(0.0f, fArr, null);
        float f3 = fArr[0];
        float f4 = fArr[1];
        if (f3 == f && f4 == f2) {
            throw new IllegalArgumentException("pattern must not end at the starting point");
        }
        this.f1054c.setTranslate(-f3, -f4);
        float f5 = f - f3;
        float f6 = f2 - f4;
        float a = 1.0f / a(f5, f6);
        this.f1054c.postScale(a, a);
        this.f1054c.postRotate((float) Math.toDegrees(-Math.atan2(f6, f5)));
        path.transform(this.f1054c, this.b);
        this.a = path;
    }
}
