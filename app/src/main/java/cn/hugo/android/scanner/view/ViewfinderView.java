package cn.hugo.android.scanner.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import bsh.org.objectweb.asm.Constants;
import c.a.a.r;
import cn.hugo.android.scanner.f.c;
import com.oldpods.app.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class ViewfinderView extends View {
    private static int m;
    private static int n;
    private int a;
    private Paint b;

    /* renamed from: c, reason: collision with root package name */
    private int f1445c;

    /* renamed from: d, reason: collision with root package name */
    private int f1446d;
    private Bitmap e;
    private final int f;
    private final int g;
    private final int h;

    /* renamed from: i, reason: collision with root package name */
    private List<r> f1447i;
    private List<r> j;
    boolean k;
    private c l;

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = true;
        this.a = b(context, 0.0f);
        n = b(context, 20.0f);
        m = b(context, 3.0f);
        this.b = new Paint(1);
        Resources resources = getResources();
        this.f = resources.getColor(R.color.viewfinder_mask);
        this.g = resources.getColor(R.color.result_view);
        this.h = resources.getColor(R.color.possible_result_points);
        this.f1447i = new ArrayList(5);
        this.j = null;
    }

    private void c(Canvas canvas, Rect rect) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        this.b.setColor(this.e != null ? this.g : this.f);
        float f = width;
        canvas.drawRect(0.0f, 0.0f, f, rect.top, this.b);
        canvas.drawRect(0.0f, rect.top, rect.left, rect.bottom + 1, this.b);
        canvas.drawRect(rect.right + 1, rect.top, f, rect.bottom + 1, this.b);
        canvas.drawRect(0.0f, rect.bottom + 1, f, height, this.b);
    }

    private void d(Canvas canvas, Rect rect) {
        this.b.setColor(-1);
        this.b.setAlpha(255);
        Resources resources = getResources();
        Bitmap decodeResource = BitmapFactory.decodeResource(resources, R.mipmap.scan_corner_top_left);
        Bitmap decodeResource2 = BitmapFactory.decodeResource(resources, R.mipmap.scan_corner_top_right);
        Bitmap decodeResource3 = BitmapFactory.decodeResource(resources, R.mipmap.scan_corner_bottom_left);
        Bitmap decodeResource4 = BitmapFactory.decodeResource(resources, R.mipmap.scan_corner_bottom_right);
        int i2 = rect.left;
        int i3 = this.a;
        canvas.drawBitmap(decodeResource, i2 + i3, rect.top + i3, this.b);
        canvas.drawBitmap(decodeResource2, (rect.right - this.a) - decodeResource2.getWidth(), rect.top + this.a, this.b);
        int i4 = rect.left;
        int i5 = this.a;
        canvas.drawBitmap(decodeResource3, i4 + i5, ((rect.bottom - i5) - decodeResource3.getHeight()) + 2, this.b);
        canvas.drawBitmap(decodeResource4, (rect.right - this.a) - decodeResource4.getWidth(), ((rect.bottom - this.a) - decodeResource4.getHeight()) + 2, this.b);
        decodeResource.recycle();
        decodeResource2.recycle();
        decodeResource3.recycle();
        decodeResource4.recycle();
    }

    private void e(Canvas canvas, Rect rect) {
        if (this.k) {
            this.k = false;
            this.f1445c = rect.top;
            this.f1446d = rect.bottom;
        }
        int i2 = this.f1445c + 10;
        this.f1445c = i2;
        if (i2 >= this.f1446d) {
            this.f1445c = rect.top;
        }
        Rect rect2 = new Rect();
        int i3 = rect.left;
        int i4 = n;
        rect2.left = i3 + i4;
        rect2.right = rect.right - i4;
        int i5 = this.f1445c;
        rect2.top = i5;
        rect2.bottom = i5 + m;
        canvas.drawBitmap(((BitmapDrawable) getResources().getDrawable(R.mipmap.scan_laser)).getBitmap(), (Rect) null, rect2, this.b);
    }

    public void a(r rVar) {
        List<r> list = this.f1447i;
        synchronized (list) {
            list.add(rVar);
            int size = list.size();
            if (size > 20) {
                list.subList(0, size - 10).clear();
            }
        }
    }

    public int b(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void f() {
        Bitmap bitmap = this.e;
        this.e = null;
        if (bitmap != null) {
            bitmap.recycle();
        }
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Rect d2;
        c cVar = this.l;
        if (cVar == null || (d2 = cVar.d()) == null) {
            return;
        }
        c(canvas, d2);
        if (this.e != null) {
            this.b.setAlpha(Constants.IF_ICMPNE);
            canvas.drawBitmap(this.e, (Rect) null, d2, this.b);
            return;
        }
        d(canvas, d2);
        e(canvas, d2);
        List<r> list = this.f1447i;
        List<r> list2 = this.j;
        if (list.isEmpty()) {
            this.j = null;
        } else {
            this.f1447i = new ArrayList(5);
            this.j = list;
            this.b.setAlpha(255);
            this.b.setColor(this.h);
            for (r rVar : list) {
                canvas.drawCircle(d2.left + rVar.c(), d2.top + rVar.d(), 6.0f, this.b);
            }
        }
        if (list2 != null) {
            this.b.setAlpha(127);
            this.b.setColor(this.h);
            for (r rVar2 : list2) {
                canvas.drawCircle(d2.left + rVar2.c(), d2.top + rVar2.d(), 3.0f, this.b);
            }
        }
        postInvalidateDelayed(10L, d2.left, d2.top, d2.right, d2.bottom);
    }

    public void setCameraManager(c cVar) {
        this.l = cVar;
    }
}
