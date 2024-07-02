package com.google.android.material.shape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import androidx.annotation.AttrRes;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import com.google.android.material.R;

/* loaded from: classes.dex */
public class ShapeAppearanceModel {
    public static final CornerSize PILL = new RelativeCornerSize(0.5f);
    CornerTreatment a;
    CornerTreatment b;

    /* renamed from: c, reason: collision with root package name */
    CornerTreatment f1667c;

    /* renamed from: d, reason: collision with root package name */
    CornerTreatment f1668d;
    CornerSize e;
    CornerSize f;
    CornerSize g;
    CornerSize h;

    /* renamed from: i, reason: collision with root package name */
    EdgeTreatment f1669i;
    EdgeTreatment j;
    EdgeTreatment k;
    EdgeTreatment l;

    /* loaded from: classes.dex */
    public static final class Builder {

        @NonNull
        private CornerTreatment a;

        @NonNull
        private CornerTreatment b;

        /* renamed from: c, reason: collision with root package name */
        @NonNull
        private CornerTreatment f1670c;

        /* renamed from: d, reason: collision with root package name */
        @NonNull
        private CornerTreatment f1671d;

        @NonNull
        private CornerSize e;

        @NonNull
        private CornerSize f;

        @NonNull
        private CornerSize g;

        @NonNull
        private CornerSize h;

        /* renamed from: i, reason: collision with root package name */
        @NonNull
        private EdgeTreatment f1672i;

        @NonNull
        private EdgeTreatment j;

        @NonNull
        private EdgeTreatment k;

        @NonNull
        private EdgeTreatment l;

        public Builder() {
            this.a = MaterialShapeUtils.b();
            this.b = MaterialShapeUtils.b();
            this.f1670c = MaterialShapeUtils.b();
            this.f1671d = MaterialShapeUtils.b();
            this.e = new AbsoluteCornerSize(0.0f);
            this.f = new AbsoluteCornerSize(0.0f);
            this.g = new AbsoluteCornerSize(0.0f);
            this.h = new AbsoluteCornerSize(0.0f);
            this.f1672i = MaterialShapeUtils.c();
            this.j = MaterialShapeUtils.c();
            this.k = MaterialShapeUtils.c();
            this.l = MaterialShapeUtils.c();
        }

        public Builder(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
            this.a = MaterialShapeUtils.b();
            this.b = MaterialShapeUtils.b();
            this.f1670c = MaterialShapeUtils.b();
            this.f1671d = MaterialShapeUtils.b();
            this.e = new AbsoluteCornerSize(0.0f);
            this.f = new AbsoluteCornerSize(0.0f);
            this.g = new AbsoluteCornerSize(0.0f);
            this.h = new AbsoluteCornerSize(0.0f);
            this.f1672i = MaterialShapeUtils.c();
            this.j = MaterialShapeUtils.c();
            this.k = MaterialShapeUtils.c();
            this.l = MaterialShapeUtils.c();
            this.a = shapeAppearanceModel.a;
            this.b = shapeAppearanceModel.b;
            this.f1670c = shapeAppearanceModel.f1667c;
            this.f1671d = shapeAppearanceModel.f1668d;
            this.e = shapeAppearanceModel.e;
            this.f = shapeAppearanceModel.f;
            this.g = shapeAppearanceModel.g;
            this.h = shapeAppearanceModel.h;
            this.f1672i = shapeAppearanceModel.f1669i;
            this.j = shapeAppearanceModel.j;
            this.k = shapeAppearanceModel.k;
            this.l = shapeAppearanceModel.l;
        }

        private static float m(CornerTreatment cornerTreatment) {
            if (cornerTreatment instanceof RoundedCornerTreatment) {
                return ((RoundedCornerTreatment) cornerTreatment).a;
            }
            if (cornerTreatment instanceof CutCornerTreatment) {
                return ((CutCornerTreatment) cornerTreatment).a;
            }
            return -1.0f;
        }

        @NonNull
        public ShapeAppearanceModel build() {
            return new ShapeAppearanceModel(this);
        }

        @NonNull
        public Builder setAllCornerSizes(@Dimension float f) {
            return setTopLeftCornerSize(f).setTopRightCornerSize(f).setBottomRightCornerSize(f).setBottomLeftCornerSize(f);
        }

        @NonNull
        public Builder setAllCornerSizes(@NonNull CornerSize cornerSize) {
            return setTopLeftCornerSize(cornerSize).setTopRightCornerSize(cornerSize).setBottomRightCornerSize(cornerSize).setBottomLeftCornerSize(cornerSize);
        }

        @NonNull
        public Builder setAllCorners(int i2, @Dimension float f) {
            return setAllCorners(MaterialShapeUtils.a(i2)).setAllCornerSizes(f);
        }

        @NonNull
        public Builder setAllCorners(@NonNull CornerTreatment cornerTreatment) {
            return setTopLeftCorner(cornerTreatment).setTopRightCorner(cornerTreatment).setBottomRightCorner(cornerTreatment).setBottomLeftCorner(cornerTreatment);
        }

        @NonNull
        public Builder setAllEdges(@NonNull EdgeTreatment edgeTreatment) {
            return setLeftEdge(edgeTreatment).setTopEdge(edgeTreatment).setRightEdge(edgeTreatment).setBottomEdge(edgeTreatment);
        }

        @NonNull
        public Builder setBottomEdge(@NonNull EdgeTreatment edgeTreatment) {
            this.k = edgeTreatment;
            return this;
        }

        @NonNull
        public Builder setBottomLeftCorner(int i2, @Dimension float f) {
            return setBottomLeftCorner(MaterialShapeUtils.a(i2)).setBottomLeftCornerSize(f);
        }

        @NonNull
        public Builder setBottomLeftCorner(int i2, @NonNull CornerSize cornerSize) {
            return setBottomLeftCorner(MaterialShapeUtils.a(i2)).setBottomLeftCornerSize(cornerSize);
        }

        @NonNull
        public Builder setBottomLeftCorner(@NonNull CornerTreatment cornerTreatment) {
            this.f1671d = cornerTreatment;
            float m = m(cornerTreatment);
            if (m != -1.0f) {
                setBottomLeftCornerSize(m);
            }
            return this;
        }

        @NonNull
        public Builder setBottomLeftCornerSize(@Dimension float f) {
            this.h = new AbsoluteCornerSize(f);
            return this;
        }

        @NonNull
        public Builder setBottomLeftCornerSize(@NonNull CornerSize cornerSize) {
            this.h = cornerSize;
            return this;
        }

        @NonNull
        public Builder setBottomRightCorner(int i2, @Dimension float f) {
            return setBottomRightCorner(MaterialShapeUtils.a(i2)).setBottomRightCornerSize(f);
        }

        @NonNull
        public Builder setBottomRightCorner(int i2, @NonNull CornerSize cornerSize) {
            return setBottomRightCorner(MaterialShapeUtils.a(i2)).setBottomRightCornerSize(cornerSize);
        }

        @NonNull
        public Builder setBottomRightCorner(@NonNull CornerTreatment cornerTreatment) {
            this.f1670c = cornerTreatment;
            float m = m(cornerTreatment);
            if (m != -1.0f) {
                setBottomRightCornerSize(m);
            }
            return this;
        }

        @NonNull
        public Builder setBottomRightCornerSize(@Dimension float f) {
            this.g = new AbsoluteCornerSize(f);
            return this;
        }

        @NonNull
        public Builder setBottomRightCornerSize(@NonNull CornerSize cornerSize) {
            this.g = cornerSize;
            return this;
        }

        @NonNull
        public Builder setLeftEdge(@NonNull EdgeTreatment edgeTreatment) {
            this.l = edgeTreatment;
            return this;
        }

        @NonNull
        public Builder setRightEdge(@NonNull EdgeTreatment edgeTreatment) {
            this.j = edgeTreatment;
            return this;
        }

        @NonNull
        public Builder setTopEdge(@NonNull EdgeTreatment edgeTreatment) {
            this.f1672i = edgeTreatment;
            return this;
        }

        @NonNull
        public Builder setTopLeftCorner(int i2, @Dimension float f) {
            return setTopLeftCorner(MaterialShapeUtils.a(i2)).setTopLeftCornerSize(f);
        }

        @NonNull
        public Builder setTopLeftCorner(int i2, @NonNull CornerSize cornerSize) {
            return setTopLeftCorner(MaterialShapeUtils.a(i2)).setTopLeftCornerSize(cornerSize);
        }

        @NonNull
        public Builder setTopLeftCorner(@NonNull CornerTreatment cornerTreatment) {
            this.a = cornerTreatment;
            float m = m(cornerTreatment);
            if (m != -1.0f) {
                setTopLeftCornerSize(m);
            }
            return this;
        }

        @NonNull
        public Builder setTopLeftCornerSize(@Dimension float f) {
            this.e = new AbsoluteCornerSize(f);
            return this;
        }

        @NonNull
        public Builder setTopLeftCornerSize(@NonNull CornerSize cornerSize) {
            this.e = cornerSize;
            return this;
        }

        @NonNull
        public Builder setTopRightCorner(int i2, @Dimension float f) {
            return setTopRightCorner(MaterialShapeUtils.a(i2)).setTopRightCornerSize(f);
        }

        @NonNull
        public Builder setTopRightCorner(int i2, @NonNull CornerSize cornerSize) {
            return setTopRightCorner(MaterialShapeUtils.a(i2)).setTopRightCornerSize(cornerSize);
        }

        @NonNull
        public Builder setTopRightCorner(@NonNull CornerTreatment cornerTreatment) {
            this.b = cornerTreatment;
            float m = m(cornerTreatment);
            if (m != -1.0f) {
                setTopRightCornerSize(m);
            }
            return this;
        }

        @NonNull
        public Builder setTopRightCornerSize(@Dimension float f) {
            this.f = new AbsoluteCornerSize(f);
            return this;
        }

        @NonNull
        public Builder setTopRightCornerSize(@NonNull CornerSize cornerSize) {
            this.f = cornerSize;
            return this;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public interface CornerSizeUnaryOperator {
        @NonNull
        CornerSize apply(@NonNull CornerSize cornerSize);
    }

    public ShapeAppearanceModel() {
        this.a = MaterialShapeUtils.b();
        this.b = MaterialShapeUtils.b();
        this.f1667c = MaterialShapeUtils.b();
        this.f1668d = MaterialShapeUtils.b();
        this.e = new AbsoluteCornerSize(0.0f);
        this.f = new AbsoluteCornerSize(0.0f);
        this.g = new AbsoluteCornerSize(0.0f);
        this.h = new AbsoluteCornerSize(0.0f);
        this.f1669i = MaterialShapeUtils.c();
        this.j = MaterialShapeUtils.c();
        this.k = MaterialShapeUtils.c();
        this.l = MaterialShapeUtils.c();
    }

    private ShapeAppearanceModel(@NonNull Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.f1667c = builder.f1670c;
        this.f1668d = builder.f1671d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.f1669i = builder.f1672i;
        this.j = builder.j;
        this.k = builder.k;
        this.l = builder.l;
    }

    @NonNull
    private static Builder a(Context context, @StyleRes int i2, @StyleRes int i3, int i4) {
        return b(context, i2, i3, new AbsoluteCornerSize(i4));
    }

    @NonNull
    private static Builder b(Context context, @StyleRes int i2, @StyleRes int i3, @NonNull CornerSize cornerSize) {
        if (i3 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i2);
            i2 = i3;
            context = contextThemeWrapper;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i2, R.styleable.ShapeAppearance);
        try {
            int i4 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamily, 0);
            int i5 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyTopLeft, i4);
            int i6 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyTopRight, i4);
            int i7 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyBottomRight, i4);
            int i8 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyBottomLeft, i4);
            CornerSize c2 = c(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSize, cornerSize);
            CornerSize c3 = c(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSizeTopLeft, c2);
            CornerSize c4 = c(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSizeTopRight, c2);
            CornerSize c5 = c(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSizeBottomRight, c2);
            return new Builder().setTopLeftCorner(i5, c3).setTopRightCorner(i6, c4).setBottomRightCorner(i7, c5).setBottomLeftCorner(i8, c(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSizeBottomLeft, c2));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public static Builder builder(Context context, @StyleRes int i2, @StyleRes int i3) {
        return a(context, i2, i3, 0);
    }

    @NonNull
    public static Builder builder(@NonNull Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        return builder(context, attributeSet, i2, i3, 0);
    }

    @NonNull
    public static Builder builder(@NonNull Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3, int i4) {
        return builder(context, attributeSet, i2, i3, new AbsoluteCornerSize(i4));
    }

    @NonNull
    public static Builder builder(@NonNull Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3, @NonNull CornerSize cornerSize) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialShape, i2, i3);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.MaterialShape_shapeAppearance, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.MaterialShape_shapeAppearanceOverlay, 0);
        obtainStyledAttributes.recycle();
        return b(context, resourceId, resourceId2, cornerSize);
    }

    @NonNull
    private static CornerSize c(TypedArray typedArray, int i2, @NonNull CornerSize cornerSize) {
        TypedValue peekValue = typedArray.peekValue(i2);
        if (peekValue == null) {
            return cornerSize;
        }
        int i3 = peekValue.type;
        return i3 == 5 ? new AbsoluteCornerSize(TypedValue.complexToDimensionPixelSize(peekValue.data, typedArray.getResources().getDisplayMetrics())) : i3 == 6 ? new RelativeCornerSize(peekValue.getFraction(1.0f, 1.0f)) : cornerSize;
    }

    @NonNull
    public EdgeTreatment getBottomEdge() {
        return this.k;
    }

    @NonNull
    public CornerTreatment getBottomLeftCorner() {
        return this.f1668d;
    }

    @NonNull
    public CornerSize getBottomLeftCornerSize() {
        return this.h;
    }

    @NonNull
    public CornerTreatment getBottomRightCorner() {
        return this.f1667c;
    }

    @NonNull
    public CornerSize getBottomRightCornerSize() {
        return this.g;
    }

    @NonNull
    public EdgeTreatment getLeftEdge() {
        return this.l;
    }

    @NonNull
    public EdgeTreatment getRightEdge() {
        return this.j;
    }

    @NonNull
    public EdgeTreatment getTopEdge() {
        return this.f1669i;
    }

    @NonNull
    public CornerTreatment getTopLeftCorner() {
        return this.a;
    }

    @NonNull
    public CornerSize getTopLeftCornerSize() {
        return this.e;
    }

    @NonNull
    public CornerTreatment getTopRightCorner() {
        return this.b;
    }

    @NonNull
    public CornerSize getTopRightCornerSize() {
        return this.f;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isRoundRect(@NonNull RectF rectF) {
        boolean z = this.l.getClass().equals(EdgeTreatment.class) && this.j.getClass().equals(EdgeTreatment.class) && this.f1669i.getClass().equals(EdgeTreatment.class) && this.k.getClass().equals(EdgeTreatment.class);
        float cornerSize = this.e.getCornerSize(rectF);
        return z && ((this.f.getCornerSize(rectF) > cornerSize ? 1 : (this.f.getCornerSize(rectF) == cornerSize ? 0 : -1)) == 0 && (this.h.getCornerSize(rectF) > cornerSize ? 1 : (this.h.getCornerSize(rectF) == cornerSize ? 0 : -1)) == 0 && (this.g.getCornerSize(rectF) > cornerSize ? 1 : (this.g.getCornerSize(rectF) == cornerSize ? 0 : -1)) == 0) && ((this.b instanceof RoundedCornerTreatment) && (this.a instanceof RoundedCornerTreatment) && (this.f1667c instanceof RoundedCornerTreatment) && (this.f1668d instanceof RoundedCornerTreatment));
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this);
    }

    @NonNull
    public ShapeAppearanceModel withCornerSize(float f) {
        return toBuilder().setAllCornerSizes(f).build();
    }

    @NonNull
    public ShapeAppearanceModel withCornerSize(@NonNull CornerSize cornerSize) {
        return toBuilder().setAllCornerSizes(cornerSize).build();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ShapeAppearanceModel withTransformedCornerSizes(@NonNull CornerSizeUnaryOperator cornerSizeUnaryOperator) {
        return toBuilder().setTopLeftCornerSize(cornerSizeUnaryOperator.apply(getTopLeftCornerSize())).setTopRightCornerSize(cornerSizeUnaryOperator.apply(getTopRightCornerSize())).setBottomLeftCornerSize(cornerSizeUnaryOperator.apply(getBottomLeftCornerSize())).setBottomRightCornerSize(cornerSizeUnaryOperator.apply(getBottomRightCornerSize())).build();
    }
}
