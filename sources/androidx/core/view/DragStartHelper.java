package androidx.core.view;

import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes.dex */
public class DragStartHelper {
    private final View a;
    private final OnDragStartListener b;

    /* renamed from: c, reason: collision with root package name */
    private int f616c;

    /* renamed from: d, reason: collision with root package name */
    private int f617d;
    private boolean e;
    private final View.OnLongClickListener f = new a();
    private final View.OnTouchListener g = new b();

    /* loaded from: classes.dex */
    public interface OnDragStartListener {
        boolean onDragStart(View view, DragStartHelper dragStartHelper);
    }

    /* loaded from: classes.dex */
    class a implements View.OnLongClickListener {
        a() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            return DragStartHelper.this.onLongClick(view);
        }
    }

    /* loaded from: classes.dex */
    class b implements View.OnTouchListener {
        b() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return DragStartHelper.this.onTouch(view, motionEvent);
        }
    }

    public DragStartHelper(View view, OnDragStartListener onDragStartListener) {
        this.a = view;
        this.b = onDragStartListener;
    }

    public void attach() {
        this.a.setOnLongClickListener(this.f);
        this.a.setOnTouchListener(this.g);
    }

    public void detach() {
        this.a.setOnLongClickListener(null);
        this.a.setOnTouchListener(null);
    }

    public void getTouchPosition(Point point) {
        point.set(this.f616c, this.f617d);
    }

    public boolean onLongClick(View view) {
        return this.b.onDragStart(view, this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0018, code lost:
    
        if (r2 != 3) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r7, android.view.MotionEvent r8) {
        /*
            r6 = this;
            float r0 = r8.getX()
            int r0 = (int) r0
            float r1 = r8.getY()
            int r1 = (int) r1
            int r2 = r8.getAction()
            r3 = 0
            if (r2 == 0) goto L49
            r4 = 1
            if (r2 == r4) goto L46
            r5 = 2
            if (r2 == r5) goto L1b
            r7 = 3
            if (r2 == r7) goto L46
            goto L4d
        L1b:
            r2 = 8194(0x2002, float:1.1482E-41)
            boolean r2 = androidx.core.view.MotionEventCompat.isFromSource(r8, r2)
            if (r2 == 0) goto L4d
            int r8 = r8.getButtonState()
            r8 = r8 & r4
            if (r8 != 0) goto L2b
            goto L4d
        L2b:
            boolean r8 = r6.e
            if (r8 == 0) goto L30
            goto L4d
        L30:
            int r8 = r6.f616c
            if (r8 != r0) goto L39
            int r8 = r6.f617d
            if (r8 != r1) goto L39
            goto L4d
        L39:
            r6.f616c = r0
            r6.f617d = r1
            androidx.core.view.DragStartHelper$OnDragStartListener r8 = r6.b
            boolean r7 = r8.onDragStart(r7, r6)
            r6.e = r7
            return r7
        L46:
            r6.e = r3
            goto L4d
        L49:
            r6.f616c = r0
            r6.f617d = r1
        L4d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.DragStartHelper.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
