package androidx.transition;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

/* loaded from: classes.dex */
class l<T> extends Property<T, Float> {
    private final Property<T, PointF> a;
    private final PathMeasure b;

    /* renamed from: c, reason: collision with root package name */
    private final float f1082c;

    /* renamed from: d, reason: collision with root package name */
    private final float[] f1083d;
    private final PointF e;
    private float f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.f1083d = new float[2];
        this.e = new PointF();
        this.a = property;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        this.b = pathMeasure;
        this.f1082c = pathMeasure.getLength();
    }

    @Override // android.util.Property
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Float get(T t) {
        return Float.valueOf(this.f);
    }

    @Override // android.util.Property
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void set(T t, Float f) {
        this.f = f.floatValue();
        this.b.getPosTan(this.f1082c * f.floatValue(), this.f1083d, null);
        PointF pointF = this.e;
        float[] fArr = this.f1083d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.a.set(t, pointF);
    }
}
