package androidx.cardview.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.g;

@RequiresApi(17)
/* loaded from: classes.dex */
class a extends c {

    /* renamed from: androidx.cardview.widget.a$a, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0008a implements g.a {
        C0008a(a aVar) {
        }

        @Override // androidx.cardview.widget.g.a
        public void a(Canvas canvas, RectF rectF, float f, Paint paint) {
            canvas.drawRoundRect(rectF, f, f, paint);
        }
    }

    @Override // androidx.cardview.widget.c, androidx.cardview.widget.e
    public void o() {
        g.r = new C0008a(this);
    }
}
