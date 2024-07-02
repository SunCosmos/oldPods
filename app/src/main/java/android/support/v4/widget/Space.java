package android.support.v4.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;

@Deprecated
/* loaded from: lib/Mus.dex */
public class Space extends View {
    @Deprecated
    public Space(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        if (getVisibility() == 0) {
            setVisibility(4);
        }
    }

    @Deprecated
    public Space(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Deprecated
    public Space(@NonNull Context context) {
        this(context, null);
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    @Deprecated
    public void draw(Canvas canvas) {
    }

    private static int getDefaultSize2(int i2, int i3) {
        int i4 = i2;
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        switch (mode) {
            case Integer.MIN_VALUE:
                i4 = Math.min(i2, size);
                break;
            case 0:
                i4 = i2;
                break;
            case BasicMeasure.EXACTLY /* 1073741824 */:
                i4 = size;
                break;
        }
        return i4;
    }

    @Override // android.view.View
    @Deprecated
    protected void onMeasure(int i2, int i3) {
        setMeasuredDimension(getDefaultSize2(getSuggestedMinimumWidth(), i2), getDefaultSize2(getSuggestedMinimumHeight(), i3));
    }
}
