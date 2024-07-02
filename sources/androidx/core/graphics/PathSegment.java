package androidx.core.graphics;

import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
public final class PathSegment {
    private final PointF a;
    private final float b;

    /* renamed from: c, reason: collision with root package name */
    private final PointF f539c;

    /* renamed from: d, reason: collision with root package name */
    private final float f540d;

    public PathSegment(@NonNull PointF pointF, float f, @NonNull PointF pointF2, float f2) {
        this.a = (PointF) Preconditions.checkNotNull(pointF, "start == null");
        this.b = f;
        this.f539c = (PointF) Preconditions.checkNotNull(pointF2, "end == null");
        this.f540d = f2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PathSegment)) {
            return false;
        }
        PathSegment pathSegment = (PathSegment) obj;
        return Float.compare(this.b, pathSegment.b) == 0 && Float.compare(this.f540d, pathSegment.f540d) == 0 && this.a.equals(pathSegment.a) && this.f539c.equals(pathSegment.f539c);
    }

    @NonNull
    public PointF getEnd() {
        return this.f539c;
    }

    public float getEndFraction() {
        return this.f540d;
    }

    @NonNull
    public PointF getStart() {
        return this.a;
    }

    public float getStartFraction() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        float f = this.b;
        int floatToIntBits = (((hashCode + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31) + this.f539c.hashCode()) * 31;
        float f2 = this.f540d;
        return floatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0);
    }

    public String toString() {
        return "PathSegment{start=" + this.a + ", startFraction=" + this.b + ", end=" + this.f539c + ", endFraction=" + this.f540d + '}';
    }
}
