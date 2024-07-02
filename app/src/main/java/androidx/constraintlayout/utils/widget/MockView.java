package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.R;

/* loaded from: classes.dex */
public class MockView extends View {
    private Paint a;
    private Paint b;

    /* renamed from: c, reason: collision with root package name */
    private Paint f415c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f416d;
    private boolean e;
    protected String f;
    private Rect g;
    private int h;

    /* renamed from: i, reason: collision with root package name */
    private int f417i;
    private int j;
    private int k;

    public MockView(Context context) {
        super(context);
        this.a = new Paint();
        this.b = new Paint();
        this.f415c = new Paint();
        this.f416d = true;
        this.e = true;
        this.f = null;
        this.g = new Rect();
        this.h = Color.argb(255, 0, 0, 0);
        this.f417i = Color.argb(255, 200, 200, 200);
        this.j = Color.argb(255, 50, 50, 50);
        this.k = 4;
        a(context, null);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new Paint();
        this.b = new Paint();
        this.f415c = new Paint();
        this.f416d = true;
        this.e = true;
        this.f = null;
        this.g = new Rect();
        this.h = Color.argb(255, 0, 0, 0);
        this.f417i = Color.argb(255, 200, 200, 200);
        this.j = Color.argb(255, 50, 50, 50);
        this.k = 4;
        a(context, attributeSet);
    }

    public MockView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = new Paint();
        this.b = new Paint();
        this.f415c = new Paint();
        this.f416d = true;
        this.e = true;
        this.f = null;
        this.g = new Rect();
        this.h = Color.argb(255, 0, 0, 0);
        this.f417i = Color.argb(255, 200, 200, 200);
        this.j = Color.argb(255, 50, 50, 50);
        this.k = 4;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MockView);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.MockView_mock_label) {
                    this.f = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.MockView_mock_showDiagonals) {
                    this.f416d = obtainStyledAttributes.getBoolean(index, this.f416d);
                } else if (index == R.styleable.MockView_mock_diagonalsColor) {
                    this.h = obtainStyledAttributes.getColor(index, this.h);
                } else if (index == R.styleable.MockView_mock_labelBackgroundColor) {
                    this.j = obtainStyledAttributes.getColor(index, this.j);
                } else if (index == R.styleable.MockView_mock_labelColor) {
                    this.f417i = obtainStyledAttributes.getColor(index, this.f417i);
                } else if (index == R.styleable.MockView_mock_showLabel) {
                    this.e = obtainStyledAttributes.getBoolean(index, this.e);
                }
            }
            obtainStyledAttributes.recycle();
        }
        if (this.f == null) {
            try {
                this.f = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        this.a.setColor(this.h);
        this.a.setAntiAlias(true);
        this.b.setColor(this.f417i);
        this.b.setAntiAlias(true);
        this.f415c.setColor(this.j);
        this.k = Math.round(this.k * (getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.f416d) {
            width--;
            height--;
            float f = width;
            float f2 = height;
            canvas.drawLine(0.0f, 0.0f, f, f2, this.a);
            canvas.drawLine(0.0f, f2, f, 0.0f, this.a);
            canvas.drawLine(0.0f, 0.0f, f, 0.0f, this.a);
            canvas.drawLine(f, 0.0f, f, f2, this.a);
            canvas.drawLine(f, f2, 0.0f, f2, this.a);
            canvas.drawLine(0.0f, f2, 0.0f, 0.0f, this.a);
        }
        String str = this.f;
        if (str == null || !this.e) {
            return;
        }
        this.b.getTextBounds(str, 0, str.length(), this.g);
        float width2 = (width - this.g.width()) / 2.0f;
        float height2 = ((height - this.g.height()) / 2.0f) + this.g.height();
        this.g.offset((int) width2, (int) height2);
        Rect rect = this.g;
        int i2 = rect.left;
        int i3 = this.k;
        rect.set(i2 - i3, rect.top - i3, rect.right + i3, rect.bottom + i3);
        canvas.drawRect(this.g, this.f415c);
        canvas.drawText(this.f, width2, height2, this.b);
    }
}
