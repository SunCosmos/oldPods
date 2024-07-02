package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.ViewCompat;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class ChangeTransform extends Transition {
    private static final String[] M = {"android:changeTransform:matrix", "android:changeTransform:transforms", "android:changeTransform:parentMatrix"};
    private static final Property<e, float[]> N = new a(float[].class, "nonTranslations");
    private static final Property<e, PointF> O = new b(PointF.class, "translations");
    private static final boolean P;
    boolean J;
    private boolean K;
    private Matrix L;

    /* loaded from: classes.dex */
    static class a extends Property<e, float[]> {
        a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public float[] get(e eVar) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(e eVar, float[] fArr) {
            eVar.d(fArr);
        }
    }

    /* loaded from: classes.dex */
    static class b extends Property<e, PointF> {
        b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(e eVar) {
            return null;
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(e eVar, PointF pointF) {
            eVar.c(pointF);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends AnimatorListenerAdapter {
        private boolean a;
        private Matrix b = new Matrix();

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1046c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ Matrix f1047d;
        final /* synthetic */ View e;
        final /* synthetic */ f f;
        final /* synthetic */ e g;

        c(boolean z, Matrix matrix, View view, f fVar, e eVar) {
            this.f1046c = z;
            this.f1047d = matrix;
            this.e = view;
            this.f = fVar;
            this.g = eVar;
        }

        private void a(Matrix matrix) {
            this.b.set(matrix);
            this.e.setTag(R.id.transition_transform, this.b);
            this.f.a(this.e);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.a) {
                if (this.f1046c && ChangeTransform.this.J) {
                    a(this.f1047d);
                } else {
                    this.e.setTag(R.id.transition_transform, null);
                    this.e.setTag(R.id.parent_matrix, null);
                }
            }
            b0.f(this.e, null);
            this.f.a(this.e);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) {
            a(this.g.a());
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            ChangeTransform.M(this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d extends TransitionListenerAdapter {
        private View a;
        private androidx.transition.d b;

        d(View view, androidx.transition.d dVar) {
            this.a = view;
            this.b = dVar;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            transition.removeListener(this);
            h.b(this.a);
            this.a.setTag(R.id.transition_transform, null);
            this.a.setTag(R.id.parent_matrix, null);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionPause(@NonNull Transition transition) {
            this.b.setVisibility(4);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionResume(@NonNull Transition transition) {
            this.b.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class e {
        private final Matrix a = new Matrix();
        private final View b;

        /* renamed from: c, reason: collision with root package name */
        private final float[] f1048c;

        /* renamed from: d, reason: collision with root package name */
        private float f1049d;
        private float e;

        e(View view, float[] fArr) {
            this.b = view;
            float[] fArr2 = (float[]) fArr.clone();
            this.f1048c = fArr2;
            this.f1049d = fArr2[2];
            this.e = fArr2[5];
            b();
        }

        private void b() {
            float[] fArr = this.f1048c;
            fArr[2] = this.f1049d;
            fArr[5] = this.e;
            this.a.setValues(fArr);
            b0.f(this.b, this.a);
        }

        Matrix a() {
            return this.a;
        }

        void c(PointF pointF) {
            this.f1049d = pointF.x;
            this.e = pointF.y;
            b();
        }

        void d(float[] fArr) {
            System.arraycopy(fArr, 0, this.f1048c, 0, fArr.length);
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class f {
        final float a;
        final float b;

        /* renamed from: c, reason: collision with root package name */
        final float f1050c;

        /* renamed from: d, reason: collision with root package name */
        final float f1051d;
        final float e;
        final float f;
        final float g;
        final float h;

        f(View view) {
            this.a = view.getTranslationX();
            this.b = view.getTranslationY();
            this.f1050c = ViewCompat.getTranslationZ(view);
            this.f1051d = view.getScaleX();
            this.e = view.getScaleY();
            this.f = view.getRotationX();
            this.g = view.getRotationY();
            this.h = view.getRotation();
        }

        public void a(View view) {
            ChangeTransform.O(view, this.a, this.b, this.f1050c, this.f1051d, this.e, this.f, this.g, this.h);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof f)) {
                return false;
            }
            f fVar = (f) obj;
            return fVar.a == this.a && fVar.b == this.b && fVar.f1050c == this.f1050c && fVar.f1051d == this.f1051d && fVar.e == this.e && fVar.f == this.f && fVar.g == this.g && fVar.h == this.h;
        }

        public int hashCode() {
            float f = this.a;
            int floatToIntBits = (f != 0.0f ? Float.floatToIntBits(f) : 0) * 31;
            float f2 = this.b;
            int floatToIntBits2 = (floatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31;
            float f3 = this.f1050c;
            int floatToIntBits3 = (floatToIntBits2 + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0)) * 31;
            float f4 = this.f1051d;
            int floatToIntBits4 = (floatToIntBits3 + (f4 != 0.0f ? Float.floatToIntBits(f4) : 0)) * 31;
            float f5 = this.e;
            int floatToIntBits5 = (floatToIntBits4 + (f5 != 0.0f ? Float.floatToIntBits(f5) : 0)) * 31;
            float f6 = this.f;
            int floatToIntBits6 = (floatToIntBits5 + (f6 != 0.0f ? Float.floatToIntBits(f6) : 0)) * 31;
            float f7 = this.g;
            int floatToIntBits7 = (floatToIntBits6 + (f7 != 0.0f ? Float.floatToIntBits(f7) : 0)) * 31;
            float f8 = this.h;
            return floatToIntBits7 + (f8 != 0.0f ? Float.floatToIntBits(f8) : 0);
        }
    }

    static {
        P = Build.VERSION.SDK_INT >= 21;
    }

    public ChangeTransform() {
        this.J = true;
        this.K = true;
        this.L = new Matrix();
    }

    @SuppressLint({"RestrictedApi"})
    public ChangeTransform(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.J = true;
        this.K = true;
        this.L = new Matrix();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, o.g);
        XmlPullParser xmlPullParser = (XmlPullParser) attributeSet;
        this.J = TypedArrayUtils.getNamedBoolean(obtainStyledAttributes, xmlPullParser, "reparentWithOverlay", 1, true);
        this.K = TypedArrayUtils.getNamedBoolean(obtainStyledAttributes, xmlPullParser, "reparent", 0, true);
        obtainStyledAttributes.recycle();
    }

    private void I(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (view.getVisibility() == 8) {
            return;
        }
        transitionValues.values.put("android:changeTransform:parent", view.getParent());
        transitionValues.values.put("android:changeTransform:transforms", new f(view));
        Matrix matrix = view.getMatrix();
        transitionValues.values.put("android:changeTransform:matrix", (matrix == null || matrix.isIdentity()) ? null : new Matrix(matrix));
        if (this.K) {
            Matrix matrix2 = new Matrix();
            b0.j((ViewGroup) view.getParent(), matrix2);
            matrix2.preTranslate(-r2.getScrollX(), -r2.getScrollY());
            transitionValues.values.put("android:changeTransform:parentMatrix", matrix2);
            transitionValues.values.put("android:changeTransform:intermediateMatrix", view.getTag(R.id.transition_transform));
            transitionValues.values.put("android:changeTransform:intermediateParentMatrix", view.getTag(R.id.parent_matrix));
        }
    }

    private void J(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        View view = transitionValues2.view;
        Matrix matrix = new Matrix((Matrix) transitionValues2.values.get("android:changeTransform:parentMatrix"));
        b0.k(viewGroup, matrix);
        androidx.transition.d a2 = h.a(view, viewGroup, matrix);
        if (a2 == null) {
            return;
        }
        a2.a((ViewGroup) transitionValues.values.get("android:changeTransform:parent"), transitionValues.view);
        Transition transition = this;
        while (true) {
            Transition transition2 = transition.r;
            if (transition2 == null) {
                break;
            } else {
                transition = transition2;
            }
        }
        transition.addListener(new d(view, a2));
        if (P) {
            View view2 = transitionValues.view;
            if (view2 != transitionValues2.view) {
                b0.h(view2, 0.0f);
            }
            b0.h(view, 1.0f);
        }
    }

    private ObjectAnimator K(TransitionValues transitionValues, TransitionValues transitionValues2, boolean z) {
        Matrix matrix = (Matrix) transitionValues.values.get("android:changeTransform:matrix");
        Matrix matrix2 = (Matrix) transitionValues2.values.get("android:changeTransform:matrix");
        if (matrix == null) {
            matrix = j.a;
        }
        if (matrix2 == null) {
            matrix2 = j.a;
        }
        Matrix matrix3 = matrix2;
        if (matrix.equals(matrix3)) {
            return null;
        }
        f fVar = (f) transitionValues2.values.get("android:changeTransform:transforms");
        View view = transitionValues2.view;
        M(view);
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float[] fArr2 = new float[9];
        matrix3.getValues(fArr2);
        e eVar = new e(view, fArr);
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(eVar, PropertyValuesHolder.ofObject(N, new androidx.transition.c(new float[9]), fArr, fArr2), m.a(O, getPathMotion().getPath(fArr[2], fArr[5], fArr2[2], fArr2[5])));
        c cVar = new c(z, matrix3, view, fVar, eVar);
        ofPropertyValuesHolder.addListener(cVar);
        androidx.transition.a.a(ofPropertyValuesHolder, cVar);
        return ofPropertyValuesHolder;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001f, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x001a, code lost:
    
        if (r4 == r5) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
    
        if (r5 == r4.view) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001d, code lost:
    
        r1 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean L(android.view.ViewGroup r4, android.view.ViewGroup r5) {
        /*
            r3 = this;
            boolean r0 = r3.t(r4)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L1a
            boolean r0 = r3.t(r5)
            if (r0 != 0) goto Lf
            goto L1a
        Lf:
            androidx.transition.TransitionValues r4 = r3.q(r4, r1)
            if (r4 == 0) goto L1f
            android.view.View r4 = r4.view
            if (r5 != r4) goto L1d
            goto L1e
        L1a:
            if (r4 != r5) goto L1d
            goto L1e
        L1d:
            r1 = 0
        L1e:
            r2 = r1
        L1f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.ChangeTransform.L(android.view.ViewGroup, android.view.ViewGroup):boolean");
    }

    static void M(View view) {
        O(view, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
    }

    private void N(TransitionValues transitionValues, TransitionValues transitionValues2) {
        Matrix matrix = (Matrix) transitionValues2.values.get("android:changeTransform:parentMatrix");
        transitionValues2.view.setTag(R.id.parent_matrix, matrix);
        Matrix matrix2 = this.L;
        matrix2.reset();
        matrix.invert(matrix2);
        Matrix matrix3 = (Matrix) transitionValues.values.get("android:changeTransform:matrix");
        if (matrix3 == null) {
            matrix3 = new Matrix();
            transitionValues.values.put("android:changeTransform:matrix", matrix3);
        }
        matrix3.postConcat((Matrix) transitionValues.values.get("android:changeTransform:parentMatrix"));
        matrix3.postConcat(matrix2);
    }

    static void O(View view, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        view.setTranslationX(f2);
        view.setTranslationY(f3);
        ViewCompat.setTranslationZ(view, f4);
        view.setScaleX(f5);
        view.setScaleY(f6);
        view.setRotationX(f7);
        view.setRotationY(f8);
        view.setRotation(f9);
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        I(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        I(transitionValues);
        if (P) {
            return;
        }
        ((ViewGroup) transitionValues.view.getParent()).startViewTransition(transitionValues.view);
    }

    @Override // androidx.transition.Transition
    public Animator createAnimator(@NonNull ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null || !transitionValues.values.containsKey("android:changeTransform:parent") || !transitionValues2.values.containsKey("android:changeTransform:parent")) {
            return null;
        }
        ViewGroup viewGroup2 = (ViewGroup) transitionValues.values.get("android:changeTransform:parent");
        boolean z = this.K && !L(viewGroup2, (ViewGroup) transitionValues2.values.get("android:changeTransform:parent"));
        Matrix matrix = (Matrix) transitionValues.values.get("android:changeTransform:intermediateMatrix");
        if (matrix != null) {
            transitionValues.values.put("android:changeTransform:matrix", matrix);
        }
        Matrix matrix2 = (Matrix) transitionValues.values.get("android:changeTransform:intermediateParentMatrix");
        if (matrix2 != null) {
            transitionValues.values.put("android:changeTransform:parentMatrix", matrix2);
        }
        if (z) {
            N(transitionValues, transitionValues2);
        }
        ObjectAnimator K = K(transitionValues, transitionValues2, z);
        if (z && K != null && this.J) {
            J(viewGroup, transitionValues, transitionValues2);
        } else if (!P) {
            viewGroup2.endViewTransition(transitionValues.view);
        }
        return K;
    }

    public boolean getReparent() {
        return this.K;
    }

    public boolean getReparentWithOverlay() {
        return this.J;
    }

    @Override // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return M;
    }

    public void setReparent(boolean z) {
        this.K = z;
    }

    public void setReparentWithOverlay(boolean z) {
        this.J = z;
    }
}
