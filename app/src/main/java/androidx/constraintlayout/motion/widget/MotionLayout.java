package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.utils.StopLogic;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.solver.widgets.Flow;
import androidx.constraintlayout.solver.widgets.Guideline;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.HelperWidget;
import androidx.constraintlayout.solver.widgets.VirtualLayout;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintLayoutStates;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.StateSet;
import androidx.core.view.NestedScrollingParent3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements NestedScrollingParent3 {
    public static final int DEBUG_SHOW_NONE = 0;
    public static final int DEBUG_SHOW_PATH = 2;
    public static final int DEBUG_SHOW_PROGRESS = 1;
    public static boolean IS_IN_EDIT_MODE = false;
    public static final int TOUCH_UP_COMPLETE = 0;
    public static final int TOUCH_UP_COMPLETE_TO_END = 2;
    public static final int TOUCH_UP_COMPLETE_TO_START = 1;
    public static final int TOUCH_UP_DECELERATE = 4;
    public static final int TOUCH_UP_DECELERATE_AND_COMPLETE = 5;
    public static final int TOUCH_UP_STOP = 3;
    public static final int VELOCITY_LAYOUT = 1;
    public static final int VELOCITY_POST_LAYOUT = 0;
    public static final int VELOCITY_STATIC_LAYOUT = 3;
    public static final int VELOCITY_STATIC_POST_LAYOUT = 2;
    private int A;
    private boolean A0;
    private boolean B;
    private RectF B0;
    HashMap<View, MotionController> C;
    private View C0;
    private long D;
    ArrayList<Integer> D0;
    private float E;
    float F;
    float G;
    private long H;
    float I;
    private boolean J;
    boolean K;
    private TransitionListener L;
    private float M;
    private float N;
    int O;
    d P;
    private boolean Q;
    private StopLogic R;
    private c S;
    private DesignTool T;
    int U;
    int V;
    boolean W;
    float a0;
    float b0;
    long c0;
    float d0;
    private boolean e0;
    private ArrayList<MotionHelper> f0;
    private ArrayList<MotionHelper> g0;
    private ArrayList<TransitionListener> h0;
    private int i0;
    private long j0;
    private float k0;
    private int l0;
    private float m0;
    protected boolean n0;
    int o0;
    int p0;
    int q0;
    int r0;
    int s0;
    MotionScene t;
    int t0;
    Interpolator u;
    float u0;
    float v;
    private KeyCache v0;
    private int w;
    private boolean w0;
    int x;
    private g x0;
    private int y;
    h y0;
    private int z;
    e z0;

    /* loaded from: classes.dex */
    public interface MotionTracker {
        void addMovement(MotionEvent motionEvent);

        void clear();

        void computeCurrentVelocity(int i2);

        void computeCurrentVelocity(int i2, float f);

        float getXVelocity();

        float getXVelocity(int i2);

        float getYVelocity();

        float getYVelocity(int i2);

        void recycle();
    }

    /* loaded from: classes.dex */
    public interface TransitionListener {
        void onTransitionChange(MotionLayout motionLayout, int i2, int i3, float f);

        void onTransitionCompleted(MotionLayout motionLayout, int i2);

        void onTransitionStarted(MotionLayout motionLayout, int i2, int i3);

        void onTransitionTrigger(MotionLayout motionLayout, int i2, boolean z, float f);
    }

    /* loaded from: classes.dex */
    class a implements Runnable {
        final /* synthetic */ View a;

        a(MotionLayout motionLayout, View view) {
            this.a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.setNestedScrollingEnabled(true);
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[h.values().length];
            a = iArr;
            try {
                iArr[h.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[h.SETUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[h.MOVING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[h.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes.dex */
    public class c extends MotionInterpolator {
        float a = 0.0f;
        float b = 0.0f;

        /* renamed from: c */
        float f315c;

        c() {
        }

        public void a(float f, float f2, float f3) {
            this.a = f;
            this.b = f2;
            this.f315c = f3;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionInterpolator, android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2;
            float f3 = this.a;
            if (f3 > 0.0f) {
                float f4 = this.f315c;
                if (f3 / f4 < f) {
                    f = f3 / f4;
                }
                MotionLayout.this.v = f3 - (f4 * f);
                f2 = (f3 * f) - (((f4 * f) * f) / 2.0f);
            } else {
                float f5 = this.f315c;
                if ((-f3) / f5 < f) {
                    f = (-f3) / f5;
                }
                MotionLayout.this.v = (f5 * f) + f3;
                f2 = (f3 * f) + (((f5 * f) * f) / 2.0f);
            }
            return f2 + this.b;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionInterpolator
        public float getVelocity() {
            return MotionLayout.this.v;
        }
    }

    /* loaded from: classes.dex */
    private class d {
        float[] a;
        int[] b;

        /* renamed from: c */
        float[] f317c;

        /* renamed from: d */
        Path f318d;
        Paint e;
        Paint f;
        Paint g;
        Paint h;

        /* renamed from: i */
        Paint f319i;
        private float[] j;
        DashPathEffect k;
        int l;
        Rect m = new Rect();
        boolean n = false;
        int o;

        public d() {
            this.o = 1;
            Paint paint = new Paint();
            this.e = paint;
            paint.setAntiAlias(true);
            this.e.setColor(-21965);
            this.e.setStrokeWidth(2.0f);
            this.e.setStyle(Paint.Style.STROKE);
            Paint paint2 = new Paint();
            this.f = paint2;
            paint2.setAntiAlias(true);
            this.f.setColor(-2067046);
            this.f.setStrokeWidth(2.0f);
            this.f.setStyle(Paint.Style.STROKE);
            Paint paint3 = new Paint();
            this.g = paint3;
            paint3.setAntiAlias(true);
            this.g.setColor(-13391360);
            this.g.setStrokeWidth(2.0f);
            this.g.setStyle(Paint.Style.STROKE);
            Paint paint4 = new Paint();
            this.h = paint4;
            paint4.setAntiAlias(true);
            this.h.setColor(-13391360);
            this.h.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.j = new float[8];
            Paint paint5 = new Paint();
            this.f319i = paint5;
            paint5.setAntiAlias(true);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.k = dashPathEffect;
            this.g.setPathEffect(dashPathEffect);
            this.f317c = new float[100];
            this.b = new int[50];
            if (this.n) {
                this.e.setStrokeWidth(8.0f);
                this.f319i.setStrokeWidth(8.0f);
                this.f.setStrokeWidth(8.0f);
                this.o = 4;
            }
        }

        private void c(Canvas canvas) {
            canvas.drawLines(this.a, this.e);
        }

        private void d(Canvas canvas) {
            boolean z = false;
            boolean z2 = false;
            for (int i2 = 0; i2 < this.l; i2++) {
                int[] iArr = this.b;
                if (iArr[i2] == 1) {
                    z = true;
                }
                if (iArr[i2] == 2) {
                    z2 = true;
                }
            }
            if (z) {
                g(canvas);
            }
            if (z2) {
                e(canvas);
            }
        }

        private void e(Canvas canvas) {
            float[] fArr = this.a;
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[fArr.length - 2];
            float f4 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f, f3), Math.max(f2, f4), Math.max(f, f3), Math.max(f2, f4), this.g);
            canvas.drawLine(Math.min(f, f3), Math.min(f2, f4), Math.min(f, f3), Math.max(f2, f4), this.g);
        }

        private void f(Canvas canvas, float f, float f2) {
            float[] fArr = this.a;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            float min = Math.min(f3, f5);
            float max = Math.max(f4, f6);
            float min2 = f - Math.min(f3, f5);
            float max2 = Math.max(f4, f6) - f2;
            StringBuilder sb = new StringBuilder();
            sb.append("");
            Double.isNaN((min2 * 100.0f) / Math.abs(f5 - f3));
            sb.append(((int) (r14 + 0.5d)) / 100.0f);
            String sb2 = sb.toString();
            l(sb2, this.h);
            canvas.drawText(sb2, ((min2 / 2.0f) - (this.m.width() / 2)) + min, f2 - 20.0f, this.h);
            canvas.drawLine(f, f2, Math.min(f3, f5), f2, this.g);
            StringBuilder sb3 = new StringBuilder();
            sb3.append("");
            Double.isNaN((max2 * 100.0f) / Math.abs(f6 - f4));
            sb3.append(((int) (r2 + 0.5d)) / 100.0f);
            String sb4 = sb3.toString();
            l(sb4, this.h);
            canvas.drawText(sb4, f + 5.0f, max - ((max2 / 2.0f) - (this.m.height() / 2)), this.h);
            canvas.drawLine(f, f2, f, Math.max(f4, f6), this.g);
        }

        private void g(Canvas canvas) {
            float[] fArr = this.a;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.g);
        }

        private void h(Canvas canvas, float f, float f2) {
            float[] fArr = this.a;
            float f3 = fArr[0];
            float f4 = fArr[1];
            float f5 = fArr[fArr.length - 2];
            float f6 = fArr[fArr.length - 1];
            float hypot = (float) Math.hypot(f3 - f5, f4 - f6);
            float f7 = f5 - f3;
            float f8 = f6 - f4;
            float f9 = (((f - f3) * f7) + ((f2 - f4) * f8)) / (hypot * hypot);
            float f10 = f3 + (f7 * f9);
            float f11 = f4 + (f9 * f8);
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f10, f11);
            float hypot2 = (float) Math.hypot(f10 - f, f11 - f2);
            String str = "" + (((int) ((hypot2 * 100.0f) / hypot)) / 100.0f);
            l(str, this.h);
            canvas.drawTextOnPath(str, path, (hypot2 / 2.0f) - (this.m.width() / 2), -20.0f, this.h);
            canvas.drawLine(f, f2, f10, f11, this.g);
        }

        private void i(Canvas canvas, float f, float f2, int i2, int i3) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            Double.isNaN(((f - (i2 / 2)) * 100.0f) / (MotionLayout.this.getWidth() - i2));
            sb.append(((int) (r2 + 0.5d)) / 100.0f);
            String sb2 = sb.toString();
            l(sb2, this.h);
            canvas.drawText(sb2, ((f / 2.0f) - (this.m.width() / 2)) + 0.0f, f2 - 20.0f, this.h);
            canvas.drawLine(f, f2, Math.min(0.0f, 1.0f), f2, this.g);
            StringBuilder sb3 = new StringBuilder();
            sb3.append("");
            Double.isNaN(((f2 - (i3 / 2)) * 100.0f) / (MotionLayout.this.getHeight() - i3));
            sb3.append(((int) (r2 + 0.5d)) / 100.0f);
            String sb4 = sb3.toString();
            l(sb4, this.h);
            canvas.drawText(sb4, f + 5.0f, 0.0f - ((f2 / 2.0f) - (this.m.height() / 2)), this.h);
            canvas.drawLine(f, f2, f, Math.max(0.0f, 1.0f), this.g);
        }

        private void j(Canvas canvas, MotionController motionController) {
            this.f318d.reset();
            for (int i2 = 0; i2 <= 50; i2++) {
                motionController.e(i2 / 50, this.j, 0);
                Path path = this.f318d;
                float[] fArr = this.j;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.f318d;
                float[] fArr2 = this.j;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.f318d;
                float[] fArr3 = this.j;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.f318d;
                float[] fArr4 = this.j;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.f318d.close();
            }
            this.e.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.f318d, this.e);
            canvas.translate(-2.0f, -2.0f);
            this.e.setColor(-65536);
            canvas.drawPath(this.f318d, this.e);
        }

        private void k(Canvas canvas, int i2, int i3, MotionController motionController) {
            int i4;
            int i5;
            float f;
            float f2;
            int i6;
            View view = motionController.a;
            if (view != null) {
                i4 = view.getWidth();
                i5 = motionController.a.getHeight();
            } else {
                i4 = 0;
                i5 = 0;
            }
            for (int i7 = 1; i7 < i3 - 1; i7++) {
                if (i2 != 4 || this.b[i7 - 1] != 0) {
                    float[] fArr = this.f317c;
                    int i8 = i7 * 2;
                    float f3 = fArr[i8];
                    float f4 = fArr[i8 + 1];
                    this.f318d.reset();
                    this.f318d.moveTo(f3, f4 + 10.0f);
                    this.f318d.lineTo(f3 + 10.0f, f4);
                    this.f318d.lineTo(f3, f4 - 10.0f);
                    this.f318d.lineTo(f3 - 10.0f, f4);
                    this.f318d.close();
                    int i9 = i7 - 1;
                    motionController.l(i9);
                    if (i2 == 4) {
                        int[] iArr = this.b;
                        if (iArr[i9] == 1) {
                            h(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else if (iArr[i9] == 2) {
                            f(canvas, f3 - 0.0f, f4 - 0.0f);
                        } else if (iArr[i9] == 3) {
                            i6 = 3;
                            f = f4;
                            f2 = f3;
                            i(canvas, f3 - 0.0f, f4 - 0.0f, i4, i5);
                            canvas.drawPath(this.f318d, this.f319i);
                        }
                        f = f4;
                        f2 = f3;
                        i6 = 3;
                        canvas.drawPath(this.f318d, this.f319i);
                    } else {
                        f = f4;
                        f2 = f3;
                        i6 = 3;
                    }
                    if (i2 == 2) {
                        h(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i2 == i6) {
                        f(canvas, f2 - 0.0f, f - 0.0f);
                    }
                    if (i2 == 6) {
                        i(canvas, f2 - 0.0f, f - 0.0f, i4, i5);
                    }
                    canvas.drawPath(this.f318d, this.f319i);
                }
            }
            float[] fArr2 = this.a;
            if (fArr2.length > 1) {
                canvas.drawCircle(fArr2[0], fArr2[1], 8.0f, this.f);
                float[] fArr3 = this.a;
                canvas.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.f);
            }
        }

        public void a(Canvas canvas, HashMap<View, MotionController> hashMap, int i2, int i3) {
            if (hashMap == null || hashMap.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (i3 & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.y) + ":" + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.h);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.e);
            }
            for (MotionController motionController : hashMap.values()) {
                int drawPath = motionController.getDrawPath();
                if (i3 > 0 && drawPath == 0) {
                    drawPath = 1;
                }
                if (drawPath != 0) {
                    this.l = motionController.c(this.f317c, this.b);
                    if (drawPath >= 1) {
                        int i4 = i2 / 16;
                        float[] fArr = this.a;
                        if (fArr == null || fArr.length != i4 * 2) {
                            this.a = new float[i4 * 2];
                            this.f318d = new Path();
                        }
                        int i5 = this.o;
                        canvas.translate(i5, i5);
                        this.e.setColor(1996488704);
                        this.f319i.setColor(1996488704);
                        this.f.setColor(1996488704);
                        this.g.setColor(1996488704);
                        motionController.d(this.a, i4);
                        b(canvas, drawPath, this.l, motionController);
                        this.e.setColor(-21965);
                        this.f.setColor(-2067046);
                        this.f319i.setColor(-2067046);
                        this.g.setColor(-13391360);
                        int i6 = this.o;
                        canvas.translate(-i6, -i6);
                        b(canvas, drawPath, this.l, motionController);
                        if (drawPath == 5) {
                            j(canvas, motionController);
                        }
                    }
                }
            }
            canvas.restore();
        }

        public void b(Canvas canvas, int i2, int i3, MotionController motionController) {
            if (i2 == 4) {
                d(canvas);
            }
            if (i2 == 2) {
                g(canvas);
            }
            if (i2 == 3) {
                e(canvas);
            }
            c(canvas);
            k(canvas, i2, i3, motionController);
        }

        void l(String str, Paint paint) {
            paint.getTextBounds(str, 0, str.length(), this.m);
        }
    }

    /* loaded from: classes.dex */
    public class e {
        ConstraintWidgetContainer a = new ConstraintWidgetContainer();
        ConstraintWidgetContainer b = new ConstraintWidgetContainer();

        /* renamed from: c */
        ConstraintSet f320c = null;

        /* renamed from: d */
        ConstraintSet f321d = null;
        int e;
        int f;

        e() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void i(ConstraintWidgetContainer constraintWidgetContainer, ConstraintSet constraintSet) {
            SparseArray<ConstraintWidget> sparseArray = new SparseArray<>();
            Constraints.LayoutParams layoutParams = new Constraints.LayoutParams(-2, -2);
            sparseArray.clear();
            sparseArray.put(0, constraintWidgetContainer);
            sparseArray.put(MotionLayout.this.getId(), constraintWidgetContainer);
            Iterator<ConstraintWidget> it = constraintWidgetContainer.getChildren().iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                sparseArray.put(((View) next.getCompanionWidget()).getId(), next);
            }
            Iterator<ConstraintWidget> it2 = constraintWidgetContainer.getChildren().iterator();
            while (it2.hasNext()) {
                ConstraintWidget next2 = it2.next();
                View view = (View) next2.getCompanionWidget();
                constraintSet.applyToLayoutParams(view.getId(), layoutParams);
                next2.setWidth(constraintSet.getWidth(view.getId()));
                next2.setHeight(constraintSet.getHeight(view.getId()));
                if (view instanceof ConstraintHelper) {
                    constraintSet.applyToHelper((ConstraintHelper) view, next2, layoutParams, sparseArray);
                    if (view instanceof Barrier) {
                        ((Barrier) view).validateParams();
                    }
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    layoutParams.resolveLayoutDirection(MotionLayout.this.getLayoutDirection());
                } else {
                    layoutParams.resolveLayoutDirection(0);
                }
                MotionLayout.this.c(false, view, next2, layoutParams, sparseArray);
                next2.setVisibility(constraintSet.getVisibilityMode(view.getId()) == 1 ? view.getVisibility() : constraintSet.getVisibility(view.getId()));
            }
            Iterator<ConstraintWidget> it3 = constraintWidgetContainer.getChildren().iterator();
            while (it3.hasNext()) {
                ConstraintWidget next3 = it3.next();
                if (next3 instanceof VirtualLayout) {
                    ConstraintHelper constraintHelper = (ConstraintHelper) next3.getCompanionWidget();
                    Helper helper = (Helper) next3;
                    constraintHelper.updatePreLayout(constraintWidgetContainer, helper, sparseArray);
                    ((VirtualLayout) helper).captureWidgets();
                }
            }
        }

        public void a() {
            int childCount = MotionLayout.this.getChildCount();
            MotionLayout.this.C.clear();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = MotionLayout.this.getChildAt(i2);
                MotionLayout.this.C.put(childAt, new MotionController(childAt));
            }
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt2 = MotionLayout.this.getChildAt(i3);
                MotionController motionController = MotionLayout.this.C.get(childAt2);
                if (motionController != null) {
                    if (this.f320c != null) {
                        ConstraintWidget c2 = c(this.a, childAt2);
                        if (c2 != null) {
                            motionController.w(c2, this.f320c);
                        } else if (MotionLayout.this.O != 0) {
                            Log.e("MotionLayout", Debug.getLocation() + "no widget for  " + Debug.getName(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                    if (this.f321d != null) {
                        ConstraintWidget c3 = c(this.b, childAt2);
                        if (c3 != null) {
                            motionController.u(c3, this.f321d);
                        } else if (MotionLayout.this.O != 0) {
                            Log.e("MotionLayout", Debug.getLocation() + "no widget for  " + Debug.getName(childAt2) + " (" + childAt2.getClass().getName() + ")");
                        }
                    }
                }
            }
        }

        void b(ConstraintWidgetContainer constraintWidgetContainer, ConstraintWidgetContainer constraintWidgetContainer2) {
            ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
            HashMap<ConstraintWidget, ConstraintWidget> hashMap = new HashMap<>();
            hashMap.put(constraintWidgetContainer, constraintWidgetContainer2);
            constraintWidgetContainer2.getChildren().clear();
            constraintWidgetContainer2.copy(constraintWidgetContainer, hashMap);
            Iterator<ConstraintWidget> it = children.iterator();
            while (it.hasNext()) {
                ConstraintWidget next = it.next();
                ConstraintWidget barrier = next instanceof androidx.constraintlayout.solver.widgets.Barrier ? new androidx.constraintlayout.solver.widgets.Barrier() : next instanceof Guideline ? new Guideline() : next instanceof Flow ? new Flow() : next instanceof Helper ? new HelperWidget() : new ConstraintWidget();
                constraintWidgetContainer2.add(barrier);
                hashMap.put(next, barrier);
            }
            Iterator<ConstraintWidget> it2 = children.iterator();
            while (it2.hasNext()) {
                ConstraintWidget next2 = it2.next();
                hashMap.get(next2).copy(next2, hashMap);
            }
        }

        ConstraintWidget c(ConstraintWidgetContainer constraintWidgetContainer, View view) {
            if (constraintWidgetContainer.getCompanionWidget() == view) {
                return constraintWidgetContainer;
            }
            ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
            int size = children.size();
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintWidget constraintWidget = children.get(i2);
                if (constraintWidget.getCompanionWidget() == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        void d(ConstraintWidgetContainer constraintWidgetContainer, ConstraintSet constraintSet, ConstraintSet constraintSet2) {
            this.f320c = constraintSet;
            this.f321d = constraintSet2;
            this.a = new ConstraintWidgetContainer();
            this.b = new ConstraintWidgetContainer();
            this.a.setMeasurer(((ConstraintLayout) MotionLayout.this).f423c.getMeasurer());
            this.b.setMeasurer(((ConstraintLayout) MotionLayout.this).f423c.getMeasurer());
            this.a.removeAllChildren();
            this.b.removeAllChildren();
            b(((ConstraintLayout) MotionLayout.this).f423c, this.a);
            b(((ConstraintLayout) MotionLayout.this).f423c, this.b);
            if (MotionLayout.this.G > 0.5d) {
                if (constraintSet != null) {
                    i(this.a, constraintSet);
                }
                i(this.b, constraintSet2);
            } else {
                i(this.b, constraintSet2);
                if (constraintSet != null) {
                    i(this.a, constraintSet);
                }
            }
            this.a.setRtl(MotionLayout.this.g());
            this.a.updateHierarchy();
            this.b.setRtl(MotionLayout.this.g());
            this.b.updateHierarchy();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    constraintWidgetContainer2.setHorizontalDimensionBehaviour(dimensionBehaviour);
                    this.b.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (layoutParams.height == -2) {
                    ConstraintWidgetContainer constraintWidgetContainer3 = this.a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    constraintWidgetContainer3.setVerticalDimensionBehaviour(dimensionBehaviour2);
                    this.b.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            }
        }

        public boolean e(int i2, int i3) {
            return (i2 == this.e && i3 == this.f) ? false : true;
        }

        public void f(int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            MotionLayout motionLayout = MotionLayout.this;
            motionLayout.s0 = mode;
            motionLayout.t0 = mode2;
            int optimizationLevel = motionLayout.getOptimizationLevel();
            MotionLayout motionLayout2 = MotionLayout.this;
            if (motionLayout2.x == motionLayout2.getStartState()) {
                MotionLayout.this.k(this.b, optimizationLevel, i2, i3);
                if (this.f320c != null) {
                    MotionLayout.this.k(this.a, optimizationLevel, i2, i3);
                }
            } else {
                if (this.f320c != null) {
                    MotionLayout.this.k(this.a, optimizationLevel, i2, i3);
                }
                MotionLayout.this.k(this.b, optimizationLevel, i2, i3);
            }
            if (((MotionLayout.this.getParent() instanceof MotionLayout) && mode == 1073741824 && mode2 == 1073741824) ? false : true) {
                MotionLayout motionLayout3 = MotionLayout.this;
                motionLayout3.s0 = mode;
                motionLayout3.t0 = mode2;
                if (motionLayout3.x == motionLayout3.getStartState()) {
                    MotionLayout.this.k(this.b, optimizationLevel, i2, i3);
                    if (this.f320c != null) {
                        MotionLayout.this.k(this.a, optimizationLevel, i2, i3);
                    }
                } else {
                    if (this.f320c != null) {
                        MotionLayout.this.k(this.a, optimizationLevel, i2, i3);
                    }
                    MotionLayout.this.k(this.b, optimizationLevel, i2, i3);
                }
                MotionLayout.this.o0 = this.a.getWidth();
                MotionLayout.this.p0 = this.a.getHeight();
                MotionLayout.this.q0 = this.b.getWidth();
                MotionLayout.this.r0 = this.b.getHeight();
                MotionLayout motionLayout4 = MotionLayout.this;
                motionLayout4.n0 = (motionLayout4.o0 == motionLayout4.q0 && motionLayout4.p0 == motionLayout4.r0) ? false : true;
            }
            MotionLayout motionLayout5 = MotionLayout.this;
            int i4 = motionLayout5.o0;
            int i5 = motionLayout5.p0;
            int i6 = motionLayout5.s0;
            if (i6 == Integer.MIN_VALUE || i6 == 0) {
                i4 = (int) (i4 + (motionLayout5.u0 * (motionLayout5.q0 - i4)));
            }
            int i7 = motionLayout5.t0;
            if (i7 == Integer.MIN_VALUE || i7 == 0) {
                i5 = (int) (i5 + (motionLayout5.u0 * (motionLayout5.r0 - i5)));
            }
            MotionLayout.this.j(i2, i3, i4, i5, this.a.isWidthMeasuredTooSmall() || this.b.isWidthMeasuredTooSmall(), this.a.isHeightMeasuredTooSmall() || this.b.isHeightMeasuredTooSmall());
        }

        public void g() {
            f(MotionLayout.this.z, MotionLayout.this.A);
            MotionLayout.this.b0();
        }

        public void h(int i2, int i3) {
            this.e = i2;
            this.f = i3;
        }
    }

    /* loaded from: classes.dex */
    public static class f implements MotionTracker {
        private static f b = new f();
        VelocityTracker a;

        private f() {
        }

        public static f a() {
            b.a = VelocityTracker.obtain();
            return b;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void addMovement(MotionEvent motionEvent) {
            VelocityTracker velocityTracker = this.a;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void clear() {
            VelocityTracker velocityTracker = this.a;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void computeCurrentVelocity(int i2) {
            VelocityTracker velocityTracker = this.a;
            if (velocityTracker != null) {
                velocityTracker.computeCurrentVelocity(i2);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void computeCurrentVelocity(int i2, float f) {
            VelocityTracker velocityTracker = this.a;
            if (velocityTracker != null) {
                velocityTracker.computeCurrentVelocity(i2, f);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getXVelocity() {
            VelocityTracker velocityTracker = this.a;
            if (velocityTracker != null) {
                return velocityTracker.getXVelocity();
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getXVelocity(int i2) {
            VelocityTracker velocityTracker = this.a;
            if (velocityTracker != null) {
                return velocityTracker.getXVelocity(i2);
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getYVelocity() {
            VelocityTracker velocityTracker = this.a;
            if (velocityTracker != null) {
                return velocityTracker.getYVelocity();
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float getYVelocity(int i2) {
            if (this.a != null) {
                return getYVelocity(i2);
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void recycle() {
            VelocityTracker velocityTracker = this.a;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.a = null;
            }
        }
    }

    /* loaded from: classes.dex */
    public class g {
        float a = Float.NaN;
        float b = Float.NaN;

        /* renamed from: c */
        int f322c = -1;

        /* renamed from: d */
        int f323d = -1;

        g() {
        }

        void a() {
            int i2 = this.f322c;
            if (i2 != -1 || this.f323d != -1) {
                if (i2 == -1) {
                    MotionLayout.this.transitionToState(this.f323d);
                } else {
                    int i3 = this.f323d;
                    if (i3 == -1) {
                        MotionLayout.this.setState(i2, -1, -1);
                    } else {
                        MotionLayout.this.setTransition(i2, i3);
                    }
                }
                MotionLayout.this.setState(h.SETUP);
            }
            if (Float.isNaN(this.b)) {
                if (Float.isNaN(this.a)) {
                    return;
                }
                MotionLayout.this.setProgress(this.a);
            } else {
                MotionLayout.this.setProgress(this.a, this.b);
                this.a = Float.NaN;
                this.b = Float.NaN;
                this.f322c = -1;
                this.f323d = -1;
            }
        }

        public Bundle b() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.a);
            bundle.putFloat("motion.velocity", this.b);
            bundle.putInt("motion.StartState", this.f322c);
            bundle.putInt("motion.EndState", this.f323d);
            return bundle;
        }

        public void c() {
            this.f323d = MotionLayout.this.y;
            this.f322c = MotionLayout.this.w;
            this.b = MotionLayout.this.getVelocity();
            this.a = MotionLayout.this.getProgress();
        }

        public void d(int i2) {
            this.f323d = i2;
        }

        public void e(float f) {
            this.a = f;
        }

        public void f(int i2) {
            this.f322c = i2;
        }

        public void g(Bundle bundle) {
            this.a = bundle.getFloat("motion.progress");
            this.b = bundle.getFloat("motion.velocity");
            this.f322c = bundle.getInt("motion.StartState");
            this.f323d = bundle.getInt("motion.EndState");
        }

        public void h(float f) {
            this.b = f;
        }
    }

    /* loaded from: classes.dex */
    public enum h {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    public MotionLayout(@NonNull Context context) {
        super(context);
        this.v = 0.0f;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = 0;
        this.A = 0;
        this.B = true;
        this.C = new HashMap<>();
        this.D = 0L;
        this.E = 1.0f;
        this.F = 0.0f;
        this.G = 0.0f;
        this.I = 0.0f;
        this.K = false;
        this.O = 0;
        this.Q = false;
        this.R = new StopLogic();
        this.S = new c();
        this.W = false;
        this.e0 = false;
        this.f0 = null;
        this.g0 = null;
        this.h0 = null;
        this.i0 = 0;
        this.j0 = -1L;
        this.k0 = 0.0f;
        this.l0 = 0;
        this.m0 = 0.0f;
        this.n0 = false;
        this.v0 = new KeyCache();
        this.w0 = false;
        this.y0 = h.UNDEFINED;
        this.z0 = new e();
        this.A0 = false;
        this.B0 = new RectF();
        this.C0 = null;
        this.D0 = new ArrayList<>();
        W(null);
    }

    public MotionLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.v = 0.0f;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = 0;
        this.A = 0;
        this.B = true;
        this.C = new HashMap<>();
        this.D = 0L;
        this.E = 1.0f;
        this.F = 0.0f;
        this.G = 0.0f;
        this.I = 0.0f;
        this.K = false;
        this.O = 0;
        this.Q = false;
        this.R = new StopLogic();
        this.S = new c();
        this.W = false;
        this.e0 = false;
        this.f0 = null;
        this.g0 = null;
        this.h0 = null;
        this.i0 = 0;
        this.j0 = -1L;
        this.k0 = 0.0f;
        this.l0 = 0;
        this.m0 = 0.0f;
        this.n0 = false;
        this.v0 = new KeyCache();
        this.w0 = false;
        this.y0 = h.UNDEFINED;
        this.z0 = new e();
        this.A0 = false;
        this.B0 = new RectF();
        this.C0 = null;
        this.D0 = new ArrayList<>();
        W(attributeSet);
    }

    public MotionLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.v = 0.0f;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = 0;
        this.A = 0;
        this.B = true;
        this.C = new HashMap<>();
        this.D = 0L;
        this.E = 1.0f;
        this.F = 0.0f;
        this.G = 0.0f;
        this.I = 0.0f;
        this.K = false;
        this.O = 0;
        this.Q = false;
        this.R = new StopLogic();
        this.S = new c();
        this.W = false;
        this.e0 = false;
        this.f0 = null;
        this.g0 = null;
        this.h0 = null;
        this.i0 = 0;
        this.j0 = -1L;
        this.k0 = 0.0f;
        this.l0 = 0;
        this.m0 = 0.0f;
        this.n0 = false;
        this.v0 = new KeyCache();
        this.w0 = false;
        this.y0 = h.UNDEFINED;
        this.z0 = new e();
        this.A0 = false;
        this.B0 = new RectF();
        this.C0 = null;
        this.D0 = new ArrayList<>();
        W(attributeSet);
    }

    private void K() {
        MotionScene motionScene = this.t;
        if (motionScene == null) {
            Log.e("MotionLayout", "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        int q = motionScene.q();
        MotionScene motionScene2 = this.t;
        L(q, motionScene2.f(motionScene2.q()));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        Iterator<MotionScene.Transition> it = this.t.getDefinedTransitions().iterator();
        while (it.hasNext()) {
            MotionScene.Transition next = it.next();
            if (next == this.t.f326c) {
                Log.v("MotionLayout", "CHECK: CURRENT");
            }
            M(next);
            int startConstraintSetId = next.getStartConstraintSetId();
            int endConstraintSetId = next.getEndConstraintSetId();
            String name = Debug.getName(getContext(), startConstraintSetId);
            String name2 = Debug.getName(getContext(), endConstraintSetId);
            if (sparseIntArray.get(startConstraintSetId) == endConstraintSetId) {
                Log.e("MotionLayout", "CHECK: two transitions with the same start and end " + name + "->" + name2);
            }
            if (sparseIntArray2.get(endConstraintSetId) == startConstraintSetId) {
                Log.e("MotionLayout", "CHECK: you can't have reverse transitions" + name + "->" + name2);
            }
            sparseIntArray.put(startConstraintSetId, endConstraintSetId);
            sparseIntArray2.put(endConstraintSetId, startConstraintSetId);
            if (this.t.f(startConstraintSetId) == null) {
                Log.e("MotionLayout", " no such constraintSetStart " + name);
            }
            if (this.t.f(endConstraintSetId) == null) {
                Log.e("MotionLayout", " no such constraintSetEnd " + name);
            }
        }
    }

    private void L(int i2, ConstraintSet constraintSet) {
        String name = Debug.getName(getContext(), i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            int id = childAt.getId();
            if (id == -1) {
                Log.w("MotionLayout", "CHECK: " + name + " ALL VIEWS SHOULD HAVE ID's " + childAt.getClass().getName() + " does not!");
            }
            if (constraintSet.getConstraint(id) == null) {
                Log.w("MotionLayout", "CHECK: " + name + " NO CONSTRAINTS for " + Debug.getName(childAt));
            }
        }
        int[] knownIds = constraintSet.getKnownIds();
        for (int i4 = 0; i4 < knownIds.length; i4++) {
            int i5 = knownIds[i4];
            String name2 = Debug.getName(getContext(), i5);
            if (findViewById(knownIds[i4]) == null) {
                Log.w("MotionLayout", "CHECK: " + name + " NO View matches id " + name2);
            }
            if (constraintSet.getHeight(i5) == -1) {
                Log.w("MotionLayout", "CHECK: " + name + "(" + name2 + ") no LAYOUT_HEIGHT");
            }
            if (constraintSet.getWidth(i5) == -1) {
                Log.w("MotionLayout", "CHECK: " + name + "(" + name2 + ") no LAYOUT_HEIGHT");
            }
        }
    }

    private void M(MotionScene.Transition transition) {
        Log.v("MotionLayout", "CHECK: transition = " + transition.debugString(getContext()));
        Log.v("MotionLayout", "CHECK: transition.setDuration = " + transition.getDuration());
        if (transition.getStartConstraintSetId() == transition.getEndConstraintSetId()) {
            Log.e("MotionLayout", "CHECK: start and end constraint set should not be the same!");
        }
    }

    private void N() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            MotionController motionController = this.C.get(childAt);
            if (motionController != null) {
                motionController.v(childAt);
            }
        }
    }

    private void Q() {
        boolean z;
        float signum = Math.signum(this.I - this.G);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.u;
        float f2 = this.G + (!(interpolator instanceof StopLogic) ? ((((float) (nanoTime - this.H)) * signum) * 1.0E-9f) / this.E : 0.0f);
        if (this.J) {
            f2 = this.I;
        }
        if ((signum <= 0.0f || f2 < this.I) && (signum > 0.0f || f2 > this.I)) {
            z = false;
        } else {
            f2 = this.I;
            z = true;
        }
        if (interpolator != null && !z) {
            f2 = this.Q ? interpolator.getInterpolation(((float) (nanoTime - this.D)) * 1.0E-9f) : interpolator.getInterpolation(f2);
        }
        if ((signum > 0.0f && f2 >= this.I) || (signum <= 0.0f && f2 <= this.I)) {
            f2 = this.I;
        }
        this.u0 = f2;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            MotionController motionController = this.C.get(childAt);
            if (motionController != null) {
                motionController.r(childAt, f2, nanoTime2, this.v0);
            }
        }
        if (this.n0) {
            requestLayout();
        }
    }

    private void R() {
        ArrayList<TransitionListener> arrayList;
        if ((this.L == null && ((arrayList = this.h0) == null || arrayList.isEmpty())) || this.m0 == this.F) {
            return;
        }
        if (this.l0 != -1) {
            TransitionListener transitionListener = this.L;
            if (transitionListener != null) {
                transitionListener.onTransitionStarted(this, this.w, this.y);
            }
            ArrayList<TransitionListener> arrayList2 = this.h0;
            if (arrayList2 != null) {
                Iterator<TransitionListener> it = arrayList2.iterator();
                while (it.hasNext()) {
                    it.next().onTransitionStarted(this, this.w, this.y);
                }
            }
        }
        this.l0 = -1;
        float f2 = this.F;
        this.m0 = f2;
        TransitionListener transitionListener2 = this.L;
        if (transitionListener2 != null) {
            transitionListener2.onTransitionChange(this, this.w, this.y, f2);
        }
        ArrayList<TransitionListener> arrayList3 = this.h0;
        if (arrayList3 != null) {
            Iterator<TransitionListener> it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                it2.next().onTransitionChange(this, this.w, this.y, this.F);
            }
        }
    }

    private boolean V(float f2, float f3, View view, MotionEvent motionEvent) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (V(view.getLeft() + f2, view.getTop() + f3, viewGroup.getChildAt(i2), motionEvent)) {
                    return true;
                }
            }
        }
        this.B0.set(view.getLeft() + f2, view.getTop() + f3, f2 + view.getRight(), f3 + view.getBottom());
        if (motionEvent.getAction() == 0) {
            if (this.B0.contains(motionEvent.getX(), motionEvent.getY()) && view.onTouchEvent(motionEvent)) {
                return true;
            }
        } else if (view.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    private void W(AttributeSet attributeSet) {
        MotionScene motionScene;
        int i2;
        IS_IN_EDIT_MODE = isInEditMode();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MotionLayout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            boolean z = true;
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = obtainStyledAttributes.getIndex(i3);
                if (index == R.styleable.MotionLayout_layoutDescription) {
                    this.t = new MotionScene(getContext(), this, obtainStyledAttributes.getResourceId(index, -1));
                } else if (index == R.styleable.MotionLayout_currentState) {
                    this.x = obtainStyledAttributes.getResourceId(index, -1);
                } else if (index == R.styleable.MotionLayout_motionProgress) {
                    this.I = obtainStyledAttributes.getFloat(index, 0.0f);
                    this.K = true;
                } else if (index == R.styleable.MotionLayout_applyMotionScene) {
                    z = obtainStyledAttributes.getBoolean(index, z);
                } else if (index == R.styleable.MotionLayout_showPaths) {
                    if (this.O == 0) {
                        i2 = obtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                        this.O = i2;
                    }
                } else if (index == R.styleable.MotionLayout_motionDebug) {
                    i2 = obtainStyledAttributes.getInt(index, 0);
                    this.O = i2;
                }
            }
            obtainStyledAttributes.recycle();
            if (this.t == null) {
                Log.e("MotionLayout", "WARNING NO app:layoutDescription tag");
            }
            if (!z) {
                this.t = null;
            }
        }
        if (this.O != 0) {
            K();
        }
        if (this.x != -1 || (motionScene = this.t) == null) {
            return;
        }
        this.x = motionScene.q();
        this.w = this.t.q();
        this.y = this.t.h();
    }

    private void a0() {
        ArrayList<TransitionListener> arrayList;
        if (this.L == null && ((arrayList = this.h0) == null || arrayList.isEmpty())) {
            return;
        }
        Iterator<Integer> it = this.D0.iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            TransitionListener transitionListener = this.L;
            if (transitionListener != null) {
                transitionListener.onTransitionCompleted(this, next.intValue());
            }
            ArrayList<TransitionListener> arrayList2 = this.h0;
            if (arrayList2 != null) {
                Iterator<TransitionListener> it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    it2.next().onTransitionCompleted(this, next.intValue());
                }
            }
        }
        this.D0.clear();
    }

    public void b0() {
        int childCount = getChildCount();
        this.z0.a();
        boolean z = true;
        this.K = true;
        int width = getWidth();
        int height = getHeight();
        int gatPathMotionArc = this.t.gatPathMotionArc();
        int i2 = 0;
        if (gatPathMotionArc != -1) {
            for (int i3 = 0; i3 < childCount; i3++) {
                MotionController motionController = this.C.get(getChildAt(i3));
                if (motionController != null) {
                    motionController.setPathMotionArc(gatPathMotionArc);
                }
            }
        }
        for (int i4 = 0; i4 < childCount; i4++) {
            MotionController motionController2 = this.C.get(getChildAt(i4));
            if (motionController2 != null) {
                this.t.getKeyFrames(motionController2);
                motionController2.setup(width, height, this.E, getNanoTime());
            }
        }
        float staggered = this.t.getStaggered();
        if (staggered != 0.0f) {
            boolean z2 = ((double) staggered) < 0.0d;
            float abs = Math.abs(staggered);
            float f2 = -3.4028235E38f;
            float f3 = Float.MAX_VALUE;
            int i5 = 0;
            float f4 = Float.MAX_VALUE;
            float f5 = -3.4028235E38f;
            while (true) {
                if (i5 >= childCount) {
                    z = false;
                    break;
                }
                MotionController motionController3 = this.C.get(getChildAt(i5));
                if (!Float.isNaN(motionController3.j)) {
                    break;
                }
                float j = motionController3.j();
                float k = motionController3.k();
                float f6 = z2 ? k - j : k + j;
                f4 = Math.min(f4, f6);
                f5 = Math.max(f5, f6);
                i5++;
            }
            if (!z) {
                while (i2 < childCount) {
                    MotionController motionController4 = this.C.get(getChildAt(i2));
                    float j2 = motionController4.j();
                    float k2 = motionController4.k();
                    float f7 = z2 ? k2 - j2 : k2 + j2;
                    motionController4.l = 1.0f / (1.0f - abs);
                    motionController4.k = abs - (((f7 - f4) * abs) / (f5 - f4));
                    i2++;
                }
                return;
            }
            for (int i6 = 0; i6 < childCount; i6++) {
                MotionController motionController5 = this.C.get(getChildAt(i6));
                if (!Float.isNaN(motionController5.j)) {
                    f3 = Math.min(f3, motionController5.j);
                    f2 = Math.max(f2, motionController5.j);
                }
            }
            while (i2 < childCount) {
                MotionController motionController6 = this.C.get(getChildAt(i2));
                if (!Float.isNaN(motionController6.j)) {
                    motionController6.l = 1.0f / (1.0f - abs);
                    float f8 = motionController6.j;
                    motionController6.k = abs - (z2 ? ((f2 - f8) / (f2 - f3)) * abs : ((f8 - f3) * abs) / (f2 - f3));
                }
                i2++;
            }
        }
    }

    private static boolean c0(float f2, float f3, float f4) {
        if (f2 > 0.0f) {
            float f5 = f2 / f4;
            return f3 + ((f2 * f5) - (((f4 * f5) * f5) / 2.0f)) > 1.0f;
        }
        float f6 = (-f2) / f4;
        return f3 + ((f2 * f6) + (((f4 * f6) * f6) / 2.0f)) < 0.0f;
    }

    void J(float f2) {
        if (this.t == null) {
            return;
        }
        float f3 = this.G;
        float f4 = this.F;
        if (f3 != f4 && this.J) {
            this.G = f4;
        }
        float f5 = this.G;
        if (f5 == f2) {
            return;
        }
        this.Q = false;
        this.I = f2;
        this.E = r0.getDuration() / 1000.0f;
        setProgress(this.I);
        this.u = this.t.getInterpolator();
        this.J = false;
        this.D = getNanoTime();
        this.K = true;
        this.F = f5;
        this.G = f5;
        invalidate();
    }

    public void O(boolean z) {
        MotionScene motionScene = this.t;
        if (motionScene == null) {
            return;
        }
        motionScene.disableAutoTransition(z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x020b, code lost:
    
        if (r1 != r2) goto L312;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x020e, code lost:
    
        r6 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x020f, code lost:
    
        r19.x = r2;
        r7 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x021b, code lost:
    
        if (r1 != r2) goto L312;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void P(boolean r20) {
        /*
            Method dump skipped, instructions count: 561
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.P(boolean):void");
    }

    protected void S() {
        int i2;
        ArrayList<TransitionListener> arrayList;
        if ((this.L != null || ((arrayList = this.h0) != null && !arrayList.isEmpty())) && this.l0 == -1) {
            this.l0 = this.x;
            if (this.D0.isEmpty()) {
                i2 = -1;
            } else {
                i2 = this.D0.get(r0.size() - 1).intValue();
            }
            int i3 = this.x;
            if (i2 != i3 && i3 != -1) {
                this.D0.add(Integer.valueOf(i3));
            }
        }
        a0();
    }

    public void T(int i2, float f2, float f3, float f4, float[] fArr) {
        String resourceName;
        HashMap<View, MotionController> hashMap = this.C;
        View viewById = getViewById(i2);
        MotionController motionController = hashMap.get(viewById);
        if (motionController != null) {
            motionController.i(f2, f3, f4, fArr);
            float y = viewById.getY();
            int i3 = ((f2 - this.M) > 0.0f ? 1 : ((f2 - this.M) == 0.0f ? 0 : -1));
            this.M = f2;
            this.N = y;
            return;
        }
        if (viewById == null) {
            resourceName = "" + i2;
        } else {
            resourceName = viewById.getContext().getResources().getResourceName(i2);
        }
        Log.w("MotionLayout", "WARNING could not find view id " + resourceName);
    }

    public String U(int i2) {
        MotionScene motionScene = this.t;
        if (motionScene == null) {
            return null;
        }
        return motionScene.lookUpConstraintName(i2);
    }

    public int X(String str) {
        MotionScene motionScene = this.t;
        if (motionScene == null) {
            return 0;
        }
        return motionScene.lookUpConstraintId(str);
    }

    public MotionTracker Y() {
        return f.a();
    }

    public void Z() {
        MotionScene motionScene = this.t;
        if (motionScene == null) {
            return;
        }
        if (motionScene.e(this, this.x)) {
            requestLayout();
            return;
        }
        int i2 = this.x;
        if (i2 != -1) {
            this.t.addOnClickListeners(this, i2);
        }
        if (this.t.E()) {
            this.t.D();
        }
    }

    public void addTransitionListener(TransitionListener transitionListener) {
        if (this.h0 == null) {
            this.h0 = new ArrayList<>();
        }
        this.h0.add(transitionListener);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a7  */
    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void dispatchDraw(android.graphics.Canvas r10) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.dispatchDraw(android.graphics.Canvas):void");
    }

    public void enableTransition(int i2, boolean z) {
        boolean z2;
        MotionScene.Transition transition = getTransition(i2);
        if (z) {
            z2 = true;
        } else {
            MotionScene motionScene = this.t;
            if (transition == motionScene.f326c) {
                Iterator<MotionScene.Transition> it = motionScene.getTransitionsWithState(this.x).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MotionScene.Transition next = it.next();
                    if (next.isEnabled()) {
                        this.t.f326c = next;
                        break;
                    }
                }
            }
            z2 = false;
        }
        transition.setEnable(z2);
    }

    public void fireTrigger(int i2, boolean z, float f2) {
        TransitionListener transitionListener = this.L;
        if (transitionListener != null) {
            transitionListener.onTransitionTrigger(this, i2, z, f2);
        }
        ArrayList<TransitionListener> arrayList = this.h0;
        if (arrayList != null) {
            Iterator<TransitionListener> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().onTransitionTrigger(this, i2, z, f2);
            }
        }
    }

    public ConstraintSet getConstraintSet(int i2) {
        MotionScene motionScene = this.t;
        if (motionScene == null) {
            return null;
        }
        return motionScene.f(i2);
    }

    public int[] getConstraintSetIds() {
        MotionScene motionScene = this.t;
        if (motionScene == null) {
            return null;
        }
        return motionScene.getConstraintSetIds();
    }

    public int getCurrentState() {
        return this.x;
    }

    public void getDebugMode(boolean z) {
        this.O = z ? 2 : 1;
        invalidate();
    }

    public ArrayList<MotionScene.Transition> getDefinedTransitions() {
        MotionScene motionScene = this.t;
        if (motionScene == null) {
            return null;
        }
        return motionScene.getDefinedTransitions();
    }

    public DesignTool getDesignTool() {
        if (this.T == null) {
            this.T = new DesignTool(this);
        }
        return this.T;
    }

    public int getEndState() {
        return this.y;
    }

    protected long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.G;
    }

    public int getStartState() {
        return this.w;
    }

    public float getTargetPosition() {
        return this.I;
    }

    public MotionScene.Transition getTransition(int i2) {
        return this.t.getTransitionById(i2);
    }

    public Bundle getTransitionState() {
        if (this.x0 == null) {
            this.x0 = new g();
        }
        this.x0.c();
        return this.x0.b();
    }

    public long getTransitionTimeMs() {
        if (this.t != null) {
            this.E = r0.getDuration() / 1000.0f;
        }
        return this.E * 1000.0f;
    }

    public float getVelocity() {
        return this.v;
    }

    public void getViewVelocity(View view, float f2, float f3, float[] fArr, int i2) {
        float f4;
        float f5 = this.v;
        float f6 = this.G;
        if (this.u != null) {
            float signum = Math.signum(this.I - f6);
            float interpolation = this.u.getInterpolation(this.G + 1.0E-5f);
            float interpolation2 = this.u.getInterpolation(this.G);
            f5 = (signum * ((interpolation - interpolation2) / 1.0E-5f)) / this.E;
            f4 = interpolation2;
        } else {
            f4 = f6;
        }
        Interpolator interpolator = this.u;
        if (interpolator instanceof MotionInterpolator) {
            f5 = ((MotionInterpolator) interpolator).getVelocity();
        }
        MotionController motionController = this.C.get(view);
        if ((i2 & 1) == 0) {
            motionController.o(f4, view.getWidth(), view.getHeight(), f2, f3, fArr);
        } else {
            motionController.i(f4, f2, f3, fArr);
        }
        if (i2 < 2) {
            fArr[0] = fArr[0] * f5;
            fArr[1] = fArr[1] * f5;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    protected void i(int i2) {
        this.k = null;
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return Build.VERSION.SDK_INT >= 19 ? super.isAttachedToWindow() : getWindowToken() != null;
    }

    public boolean isInteractionEnabled() {
        return this.B;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void loadLayoutDescription(int i2) {
        if (i2 == 0) {
            this.t = null;
            return;
        }
        try {
            this.t = new MotionScene(getContext(), this, i2);
            if (Build.VERSION.SDK_INT < 19 || isAttachedToWindow()) {
                this.t.B(this);
                this.z0.d(this.f423c, this.t.f(this.w), this.t.f(this.y));
                rebuildScene();
                this.t.setRtl(g());
            }
        } catch (Exception e2) {
            throw new IllegalArgumentException("unable to parse MotionScene file", e2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        MotionScene.Transition transition;
        int i2;
        super.onAttachedToWindow();
        MotionScene motionScene = this.t;
        if (motionScene != null && (i2 = this.x) != -1) {
            ConstraintSet f2 = motionScene.f(i2);
            this.t.B(this);
            if (f2 != null) {
                f2.applyTo(this);
            }
            this.w = this.x;
        }
        Z();
        g gVar = this.x0;
        if (gVar != null) {
            gVar.a();
            return;
        }
        MotionScene motionScene2 = this.t;
        if (motionScene2 == null || (transition = motionScene2.f326c) == null || transition.getAutoTransition() != 4) {
            return;
        }
        transitionToEnd();
        setState(h.SETUP);
        setState(h.MOVING);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MotionScene.Transition transition;
        androidx.constraintlayout.motion.widget.d touchResponse;
        int k;
        RectF j;
        MotionScene motionScene = this.t;
        if (motionScene != null && this.B && (transition = motionScene.f326c) != null && transition.isEnabled() && (touchResponse = transition.getTouchResponse()) != null && ((motionEvent.getAction() != 0 || (j = touchResponse.j(this, new RectF())) == null || j.contains(motionEvent.getX(), motionEvent.getY())) && (k = touchResponse.k()) != -1)) {
            View view = this.C0;
            if (view == null || view.getId() != k) {
                this.C0 = findViewById(k);
            }
            if (this.C0 != null) {
                this.B0.set(r0.getLeft(), this.C0.getTop(), this.C0.getRight(), this.C0.getBottom());
                if (this.B0.contains(motionEvent.getX(), motionEvent.getY()) && !V(0.0f, 0.0f, this.C0, motionEvent)) {
                    return onTouchEvent(motionEvent);
                }
            }
        }
        return false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.w0 = true;
        try {
            if (this.t == null) {
                super.onLayout(z, i2, i3, i4, i5);
                return;
            }
            int i6 = i4 - i2;
            int i7 = i5 - i3;
            if (this.U != i6 || this.V != i7) {
                rebuildScene();
                P(true);
            }
            this.U = i6;
            this.V = i7;
        } finally {
            this.w0 = false;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.t == null) {
            super.onMeasure(i2, i3);
            return;
        }
        boolean z = false;
        boolean z2 = (this.z == i2 && this.A == i3) ? false : true;
        if (this.A0) {
            this.A0 = false;
            Z();
            a0();
            z2 = true;
        }
        if (this.h) {
            z2 = true;
        }
        this.z = i2;
        this.A = i3;
        int q = this.t.q();
        int h2 = this.t.h();
        if ((z2 || this.z0.e(q, h2)) && this.w != -1) {
            super.onMeasure(i2, i3);
            this.z0.d(this.f423c, this.t.f(q), this.t.f(h2));
            this.z0.g();
            this.z0.h(q, h2);
        } else {
            z = true;
        }
        if (this.n0 || z) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int width = this.f423c.getWidth() + getPaddingLeft() + getPaddingRight();
            int height = this.f423c.getHeight() + paddingTop;
            int i4 = this.s0;
            if (i4 == Integer.MIN_VALUE || i4 == 0) {
                width = (int) (this.o0 + (this.u0 * (this.q0 - r7)));
                requestLayout();
            }
            int i5 = this.t0;
            if (i5 == Integer.MIN_VALUE || i5 == 0) {
                height = (int) (this.p0 + (this.u0 * (this.r0 - r7)));
                requestLayout();
            }
            setMeasuredDimension(width, height);
        }
        Q();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr, int i4) {
        MotionScene.Transition transition;
        androidx.constraintlayout.motion.widget.d touchResponse;
        int k;
        MotionScene motionScene = this.t;
        if (motionScene == null || (transition = motionScene.f326c) == null || !transition.isEnabled()) {
            return;
        }
        MotionScene.Transition transition2 = this.t.f326c;
        if (transition2 == null || !transition2.isEnabled() || (touchResponse = transition2.getTouchResponse()) == null || (k = touchResponse.k()) == -1 || view.getId() == k) {
            MotionScene motionScene2 = this.t;
            if (motionScene2 != null && motionScene2.n()) {
                float f2 = this.F;
                if ((f2 == 1.0f || f2 == 0.0f) && view.canScrollVertically(-1)) {
                    return;
                }
            }
            if (transition2.getTouchResponse() != null && (this.t.f326c.getTouchResponse().d() & 1) != 0) {
                float o = this.t.o(i2, i3);
                float f3 = this.G;
                if ((f3 <= 0.0f && o < 0.0f) || (f3 >= 1.0f && o > 0.0f)) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        view.setNestedScrollingEnabled(false);
                        view.post(new a(this, view));
                        return;
                    }
                    return;
                }
            }
            float f4 = this.F;
            long nanoTime = getNanoTime();
            float f5 = i2;
            this.a0 = f5;
            float f6 = i3;
            this.b0 = f6;
            double d2 = nanoTime - this.c0;
            Double.isNaN(d2);
            this.d0 = (float) (d2 * 1.0E-9d);
            this.c0 = nanoTime;
            this.t.x(f5, f6);
            if (f4 != this.F) {
                iArr[0] = i2;
                iArr[1] = i3;
            }
            P(false);
            if (iArr[0] == 0 && iArr[1] == 0) {
                return;
            }
            this.W = true;
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5, int i6) {
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        if (this.W || i2 != 0 || i3 != 0) {
            iArr[0] = iArr[0] + i4;
            iArr[1] = iArr[1] + i5;
        }
        this.W = false;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View view, View view2, int i2, int i3) {
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        MotionScene motionScene = this.t;
        if (motionScene != null) {
            motionScene.setRtl(g());
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View view, View view2, int i2, int i3) {
        MotionScene.Transition transition;
        MotionScene motionScene = this.t;
        return (motionScene == null || (transition = motionScene.f326c) == null || transition.getTouchResponse() == null || (this.t.f326c.getTouchResponse().d() & 2) != 0) ? false : true;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View view, int i2) {
        MotionScene motionScene = this.t;
        if (motionScene == null) {
            return;
        }
        float f2 = this.a0;
        float f3 = this.d0;
        motionScene.y(f2 / f3, this.b0 / f3);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionScene motionScene = this.t;
        if (motionScene == null || !this.B || !motionScene.E()) {
            return super.onTouchEvent(motionEvent);
        }
        MotionScene.Transition transition = this.t.f326c;
        if (transition != null && !transition.isEnabled()) {
            return super.onTouchEvent(motionEvent);
        }
        this.t.z(motionEvent, getCurrentState(), this);
        return true;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.h0 == null) {
                this.h0 = new ArrayList<>();
            }
            this.h0.add(motionHelper);
            if (motionHelper.isUsedOnShow()) {
                if (this.f0 == null) {
                    this.f0 = new ArrayList<>();
                }
                this.f0.add(motionHelper);
            }
            if (motionHelper.isUseOnHide()) {
                if (this.g0 == null) {
                    this.g0 = new ArrayList<>();
                }
                this.g0.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<MotionHelper> arrayList = this.f0;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<MotionHelper> arrayList2 = this.g0;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    @Deprecated
    public void rebuildMotion() {
        Log.e("MotionLayout", "This method is deprecated. Please call rebuildScene() instead.");
        rebuildScene();
    }

    public void rebuildScene() {
        this.z0.g();
        invalidate();
    }

    public boolean removeTransitionListener(TransitionListener transitionListener) {
        ArrayList<TransitionListener> arrayList = this.h0;
        if (arrayList == null) {
            return false;
        }
        return arrayList.remove(transitionListener);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public void requestLayout() {
        MotionScene motionScene;
        MotionScene.Transition transition;
        if (this.n0 || this.x != -1 || (motionScene = this.t) == null || (transition = motionScene.f326c) == null || transition.getLayoutDuringTransition() != 0) {
            super.requestLayout();
        }
    }

    public void setDebugMode(int i2) {
        this.O = i2;
        invalidate();
    }

    public void setInteractionEnabled(boolean z) {
        this.B = z;
    }

    public void setInterpolatedProgress(float f2) {
        if (this.t != null) {
            setState(h.MOVING);
            Interpolator interpolator = this.t.getInterpolator();
            if (interpolator != null) {
                setProgress(interpolator.getInterpolation(f2));
                return;
            }
        }
        setProgress(f2);
    }

    public void setOnHide(float f2) {
        ArrayList<MotionHelper> arrayList = this.g0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.g0.get(i2).setProgress(f2);
            }
        }
    }

    public void setOnShow(float f2) {
        ArrayList<MotionHelper> arrayList = this.f0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f0.get(i2).setProgress(f2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0035, code lost:
    
        if (r4.G == 0.0f) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
    
        r0 = androidx.constraintlayout.motion.widget.MotionLayout.h.FINISHED;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0046, code lost:
    
        if (r4.G == 1.0f) goto L49;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setProgress(float r5) {
        /*
            r4 = this;
            r0 = 1065353216(0x3f800000, float:1.0)
            r1 = 0
            int r2 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r2 < 0) goto Lb
            int r2 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r2 <= 0) goto L12
        Lb:
            java.lang.String r2 = "MotionLayout"
            java.lang.String r3 = "Warning! Progress is defined for values between 0.0 and 1.0 inclusive"
            android.util.Log.w(r2, r3)
        L12:
            boolean r2 = r4.isAttachedToWindow()
            if (r2 != 0) goto L29
            androidx.constraintlayout.motion.widget.MotionLayout$g r0 = r4.x0
            if (r0 != 0) goto L23
            androidx.constraintlayout.motion.widget.MotionLayout$g r0 = new androidx.constraintlayout.motion.widget.MotionLayout$g
            r0.<init>()
            r4.x0 = r0
        L23:
            androidx.constraintlayout.motion.widget.MotionLayout$g r0 = r4.x0
            r0.e(r5)
            return
        L29:
            int r2 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r2 > 0) goto L3a
            int r0 = r4.w
            r4.x = r0
            float r0 = r4.G
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 != 0) goto L51
        L37:
            androidx.constraintlayout.motion.widget.MotionLayout$h r0 = androidx.constraintlayout.motion.widget.MotionLayout.h.FINISHED
            goto L4e
        L3a:
            int r1 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r1 < 0) goto L49
            int r1 = r4.y
            r4.x = r1
            float r1 = r4.G
            int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r0 != 0) goto L51
            goto L37
        L49:
            r0 = -1
            r4.x = r0
            androidx.constraintlayout.motion.widget.MotionLayout$h r0 = androidx.constraintlayout.motion.widget.MotionLayout.h.MOVING
        L4e:
            r4.setState(r0)
        L51:
            androidx.constraintlayout.motion.widget.MotionScene r0 = r4.t
            if (r0 != 0) goto L56
            return
        L56:
            r0 = 1
            r4.J = r0
            r4.I = r5
            r4.F = r5
            r1 = -1
            r4.H = r1
            r4.D = r1
            r5 = 0
            r4.u = r5
            r4.K = r0
            r4.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.setProgress(float):void");
    }

    public void setProgress(float f2, float f3) {
        if (isAttachedToWindow()) {
            setProgress(f2);
            setState(h.MOVING);
            this.v = f3;
            J(1.0f);
            return;
        }
        if (this.x0 == null) {
            this.x0 = new g();
        }
        this.x0.e(f2);
        this.x0.h(f3);
    }

    public void setScene(MotionScene motionScene) {
        this.t = motionScene;
        motionScene.setRtl(g());
        rebuildScene();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    public void setState(int i2, int i3, int i4) {
        setState(h.SETUP);
        this.x = i2;
        this.w = -1;
        this.y = -1;
        ConstraintLayoutStates constraintLayoutStates = this.k;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.updateConstraints(i2, i3, i4);
            return;
        }
        MotionScene motionScene = this.t;
        if (motionScene != null) {
            motionScene.f(i2).applyTo(this);
        }
    }

    public void setState(h hVar) {
        h hVar2 = h.FINISHED;
        if (hVar == hVar2 && this.x == -1) {
            return;
        }
        h hVar3 = this.y0;
        this.y0 = hVar;
        h hVar4 = h.MOVING;
        if (hVar3 == hVar4 && hVar == hVar4) {
            R();
        }
        int i2 = b.a[hVar3.ordinal()];
        if (i2 == 1 || i2 == 2) {
            if (hVar == hVar4) {
                R();
            }
            if (hVar != hVar2) {
                return;
            }
        } else if (i2 != 3 || hVar != hVar2) {
            return;
        }
        S();
    }

    public void setTransition(int i2) {
        if (this.t != null) {
            MotionScene.Transition transition = getTransition(i2);
            this.w = transition.getStartConstraintSetId();
            this.y = transition.getEndConstraintSetId();
            if (!isAttachedToWindow()) {
                if (this.x0 == null) {
                    this.x0 = new g();
                }
                this.x0.f(this.w);
                this.x0.d(this.y);
                return;
            }
            float f2 = Float.NaN;
            int i3 = this.x;
            if (i3 == this.w) {
                f2 = 0.0f;
            } else if (i3 == this.y) {
                f2 = 1.0f;
            }
            this.t.setTransition(transition);
            this.z0.d(this.f423c, this.t.f(this.w), this.t.f(this.y));
            rebuildScene();
            this.G = Float.isNaN(f2) ? 0.0f : f2;
            if (!Float.isNaN(f2)) {
                setProgress(f2);
                return;
            }
            Log.v("MotionLayout", Debug.getLocation() + " transitionToStart ");
            transitionToStart();
        }
    }

    public void setTransition(int i2, int i3) {
        if (!isAttachedToWindow()) {
            if (this.x0 == null) {
                this.x0 = new g();
            }
            this.x0.f(i2);
            this.x0.d(i3);
            return;
        }
        MotionScene motionScene = this.t;
        if (motionScene != null) {
            this.w = i2;
            this.y = i3;
            motionScene.C(i2, i3);
            this.z0.d(this.f423c, this.t.f(i2), this.t.f(i3));
            rebuildScene();
            this.G = 0.0f;
            transitionToStart();
        }
    }

    public void setTransition(MotionScene.Transition transition) {
        this.t.setTransition(transition);
        setState(h.SETUP);
        float f2 = this.x == this.t.h() ? 1.0f : 0.0f;
        this.G = f2;
        this.F = f2;
        this.I = f2;
        this.H = transition.isTransitionFlag(1) ? -1L : getNanoTime();
        int q = this.t.q();
        int h2 = this.t.h();
        if (q == this.w && h2 == this.y) {
            return;
        }
        this.w = q;
        this.y = h2;
        this.t.C(q, h2);
        this.z0.d(this.f423c, this.t.f(this.w), this.t.f(this.y));
        this.z0.h(this.w, this.y);
        this.z0.g();
        rebuildScene();
    }

    public void setTransitionDuration(int i2) {
        MotionScene motionScene = this.t;
        if (motionScene == null) {
            Log.e("MotionLayout", "MotionScene not defined");
        } else {
            motionScene.setDuration(i2);
        }
    }

    public void setTransitionListener(TransitionListener transitionListener) {
        this.L = transitionListener;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.x0 == null) {
            this.x0 = new g();
        }
        this.x0.g(bundle);
        if (isAttachedToWindow()) {
            this.x0.a();
        }
    }

    @Override // android.view.View
    public String toString() {
        Context context = getContext();
        return Debug.getName(context, this.w) + "->" + Debug.getName(context, this.y) + " (pos:" + this.G + " Dpos/Dt:" + this.v;
    }

    public void touchAnimateTo(int i2, float f2, float f3) {
        Interpolator interpolator;
        if (this.t == null || this.G == f2) {
            return;
        }
        this.Q = true;
        this.D = getNanoTime();
        float duration = this.t.getDuration() / 1000.0f;
        this.E = duration;
        this.I = f2;
        this.K = true;
        if (i2 != 0 && i2 != 1 && i2 != 2) {
            if (i2 != 4) {
                if (i2 == 5) {
                    if (!c0(f3, this.G, this.t.l())) {
                        this.R.config(this.G, f2, f3, this.E, this.t.l(), this.t.m());
                        this.v = 0.0f;
                    }
                }
                this.J = false;
                this.D = getNanoTime();
                invalidate();
            }
            this.S.a(f3, this.G, this.t.l());
            interpolator = this.S;
            this.u = interpolator;
            this.J = false;
            this.D = getNanoTime();
            invalidate();
        }
        if (i2 == 1) {
            f2 = 0.0f;
        } else if (i2 == 2) {
            f2 = 1.0f;
        }
        this.R.config(this.G, f2, f3, duration, this.t.l(), this.t.m());
        int i3 = this.x;
        this.I = f2;
        this.x = i3;
        interpolator = this.R;
        this.u = interpolator;
        this.J = false;
        this.D = getNanoTime();
        invalidate();
    }

    public void transitionToEnd() {
        J(1.0f);
    }

    public void transitionToStart() {
        J(0.0f);
    }

    public void transitionToState(int i2) {
        if (isAttachedToWindow()) {
            transitionToState(i2, -1, -1);
            return;
        }
        if (this.x0 == null) {
            this.x0 = new g();
        }
        this.x0.d(i2);
    }

    public void transitionToState(int i2, int i3, int i4) {
        StateSet stateSet;
        int convertToConstraintSet;
        MotionScene motionScene = this.t;
        if (motionScene != null && (stateSet = motionScene.b) != null && (convertToConstraintSet = stateSet.convertToConstraintSet(this.x, i2, i3, i4)) != -1) {
            i2 = convertToConstraintSet;
        }
        int i5 = this.x;
        if (i5 == i2) {
            return;
        }
        if (this.w == i2) {
            J(0.0f);
            return;
        }
        if (this.y == i2) {
            J(1.0f);
            return;
        }
        this.y = i2;
        if (i5 != -1) {
            setTransition(i5, i2);
            J(1.0f);
            this.G = 0.0f;
            transitionToEnd();
            return;
        }
        this.Q = false;
        this.I = 1.0f;
        this.F = 0.0f;
        this.G = 0.0f;
        this.H = getNanoTime();
        this.D = getNanoTime();
        this.J = false;
        this.u = null;
        this.E = this.t.getDuration() / 1000.0f;
        this.w = -1;
        this.t.C(-1, this.y);
        this.t.q();
        int childCount = getChildCount();
        this.C.clear();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            this.C.put(childAt, new MotionController(childAt));
        }
        this.K = true;
        this.z0.d(this.f423c, null, this.t.f(i2));
        rebuildScene();
        this.z0.a();
        N();
        int width = getWidth();
        int height = getHeight();
        for (int i7 = 0; i7 < childCount; i7++) {
            MotionController motionController = this.C.get(getChildAt(i7));
            this.t.getKeyFrames(motionController);
            motionController.setup(width, height, this.E, getNanoTime());
        }
        float staggered = this.t.getStaggered();
        if (staggered != 0.0f) {
            float f2 = Float.MAX_VALUE;
            float f3 = -3.4028235E38f;
            for (int i8 = 0; i8 < childCount; i8++) {
                MotionController motionController2 = this.C.get(getChildAt(i8));
                float k = motionController2.k() + motionController2.j();
                f2 = Math.min(f2, k);
                f3 = Math.max(f3, k);
            }
            for (int i9 = 0; i9 < childCount; i9++) {
                MotionController motionController3 = this.C.get(getChildAt(i9));
                float j = motionController3.j();
                float k2 = motionController3.k();
                motionController3.l = 1.0f / (1.0f - staggered);
                motionController3.k = staggered - ((((j + k2) - f2) * staggered) / (f3 - f2));
            }
        }
        this.F = 0.0f;
        this.G = 0.0f;
        this.K = true;
        invalidate();
    }

    public void updateState() {
        this.z0.d(this.f423c, this.t.f(this.w), this.t.f(this.y));
        rebuildScene();
    }

    public void updateState(int i2, ConstraintSet constraintSet) {
        MotionScene motionScene = this.t;
        if (motionScene != null) {
            motionScene.setConstraintSet(i2, constraintSet);
        }
        updateState();
        if (this.x == i2) {
            constraintSet.applyTo(this);
        }
    }
}
