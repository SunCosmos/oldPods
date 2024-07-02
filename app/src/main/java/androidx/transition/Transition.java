package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/* loaded from: classes.dex */
public abstract class Transition implements Cloneable {
    private static final int[] G = {2, 1, 3, 4};
    private static final PathMotion H = new a();
    private static ThreadLocal<ArrayMap<Animator, d>> I = new ThreadLocal<>();
    public static final int MATCH_ID = 3;
    public static final int MATCH_INSTANCE = 1;
    public static final int MATCH_ITEM_ID = 4;
    public static final int MATCH_NAME = 2;
    TransitionPropagation C;
    private EpicenterCallback D;
    private ArrayMap<String, String> E;
    private ArrayList<TransitionValues> t;
    private ArrayList<TransitionValues> u;
    private String a = getClass().getName();
    private long b = -1;

    /* renamed from: c */
    long f1058c = -1;

    /* renamed from: d */
    private TimeInterpolator f1059d = null;
    ArrayList<Integer> e = new ArrayList<>();
    ArrayList<View> f = new ArrayList<>();
    private ArrayList<String> g = null;
    private ArrayList<Class<?>> h = null;

    /* renamed from: i */
    private ArrayList<Integer> f1060i = null;
    private ArrayList<View> j = null;
    private ArrayList<Class<?>> k = null;
    private ArrayList<String> l = null;
    private ArrayList<Integer> m = null;
    private ArrayList<View> n = null;
    private ArrayList<Class<?>> o = null;
    private q p = new q();
    private q q = new q();
    TransitionSet r = null;
    private int[] s = G;
    boolean v = false;
    ArrayList<Animator> w = new ArrayList<>();
    private int x = 0;
    private boolean y = false;
    private boolean z = false;
    private ArrayList<TransitionListener> A = null;
    private ArrayList<Animator> B = new ArrayList<>();
    private PathMotion F = H;

    /* loaded from: classes.dex */
    public static abstract class EpicenterCallback {
        public abstract Rect onGetEpicenter(@NonNull Transition transition);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface MatchOrder {
    }

    /* loaded from: classes.dex */
    public interface TransitionListener {
        void onTransitionCancel(@NonNull Transition transition);

        void onTransitionEnd(@NonNull Transition transition);

        void onTransitionPause(@NonNull Transition transition);

        void onTransitionResume(@NonNull Transition transition);

        void onTransitionStart(@NonNull Transition transition);
    }

    /* loaded from: classes.dex */
    static class a extends PathMotion {
        a() {
        }

        @Override // androidx.transition.PathMotion
        public Path getPath(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    }

    /* loaded from: classes.dex */
    public class b extends AnimatorListenerAdapter {
        final /* synthetic */ ArrayMap a;

        b(ArrayMap arrayMap) {
            this.a = arrayMap;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.a.remove(animator);
            Transition.this.w.remove(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            Transition.this.w.add(animator);
        }
    }

    /* loaded from: classes.dex */
    public class c extends AnimatorListenerAdapter {
        c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            Transition.this.k();
            animator.removeListener(this);
        }
    }

    /* loaded from: classes.dex */
    public static class d {
        View a;
        String b;

        /* renamed from: c */
        TransitionValues f1061c;

        /* renamed from: d */
        k0 f1062d;
        Transition e;

        d(View view, String str, Transition transition, k0 k0Var, TransitionValues transitionValues) {
            this.a = view;
            this.b = str;
            this.f1061c = transitionValues;
            this.f1062d = k0Var;
            this.e = transition;
        }
    }

    /* loaded from: classes.dex */
    public static class e {
        static <T> ArrayList<T> a(ArrayList<T> arrayList, T t) {
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            if (!arrayList.contains(t)) {
                arrayList.add(t);
            }
            return arrayList;
        }

        static <T> ArrayList<T> b(ArrayList<T> arrayList, T t) {
            if (arrayList == null) {
                return arrayList;
            }
            arrayList.remove(t);
            if (arrayList.isEmpty()) {
                return null;
            }
            return arrayList;
        }
    }

    public Transition() {
    }

    @SuppressLint({"RestrictedApi"})
    public Transition(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, o.f1084c);
        XmlResourceParser xmlResourceParser = (XmlResourceParser) attributeSet;
        long namedInt = TypedArrayUtils.getNamedInt(obtainStyledAttributes, xmlResourceParser, "duration", 1, -1);
        if (namedInt >= 0) {
            setDuration(namedInt);
        }
        long namedInt2 = TypedArrayUtils.getNamedInt(obtainStyledAttributes, xmlResourceParser, "startDelay", 2, -1);
        if (namedInt2 > 0) {
            setStartDelay(namedInt2);
        }
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlResourceParser, "interpolator", 0, 0);
        if (namedResourceId > 0) {
            setInterpolator(AnimationUtils.loadInterpolator(context, namedResourceId));
        }
        String namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlResourceParser, "matchOrder", 3);
        if (namedString != null) {
            setMatchOrder(A(namedString));
        }
        obtainStyledAttributes.recycle();
    }

    private static int[] A(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        int[] iArr = new int[stringTokenizer.countTokens()];
        int i2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String trim = stringTokenizer.nextToken().trim();
            if ("id".equalsIgnoreCase(trim)) {
                iArr[i2] = 3;
            } else if ("instance".equalsIgnoreCase(trim)) {
                iArr[i2] = 1;
            } else if ("name".equalsIgnoreCase(trim)) {
                iArr[i2] = 2;
            } else if ("itemId".equalsIgnoreCase(trim)) {
                iArr[i2] = 4;
            } else {
                if (!trim.isEmpty()) {
                    throw new InflateException("Unknown match type in matchOrder: '" + trim + "'");
                }
                int[] iArr2 = new int[iArr.length - 1];
                System.arraycopy(iArr, 0, iArr2, 0, i2);
                i2--;
                iArr = iArr2;
            }
            i2++;
        }
        return iArr;
    }

    private void C(Animator animator, ArrayMap<Animator, d> arrayMap) {
        if (animator != null) {
            animator.addListener(new b(arrayMap));
            d(animator);
        }
    }

    private void a(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2) {
        for (int i2 = 0; i2 < arrayMap.size(); i2++) {
            TransitionValues valueAt = arrayMap.valueAt(i2);
            if (t(valueAt.view)) {
                this.t.add(valueAt);
                this.u.add(null);
            }
        }
        for (int i3 = 0; i3 < arrayMap2.size(); i3++) {
            TransitionValues valueAt2 = arrayMap2.valueAt(i3);
            if (t(valueAt2.view)) {
                this.u.add(valueAt2);
                this.t.add(null);
            }
        }
    }

    private static void b(q qVar, View view, TransitionValues transitionValues) {
        qVar.a.put(view, transitionValues);
        int id = view.getId();
        if (id >= 0) {
            if (qVar.b.indexOfKey(id) >= 0) {
                qVar.b.put(id, null);
            } else {
                qVar.b.put(id, view);
            }
        }
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            if (qVar.f1090d.containsKey(transitionName)) {
                qVar.f1090d.put(transitionName, null);
            } else {
                qVar.f1090d.put(transitionName, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (qVar.f1089c.indexOfKey(itemIdAtPosition) < 0) {
                    ViewCompat.setHasTransientState(view, true);
                    qVar.f1089c.put(itemIdAtPosition, view);
                    return;
                }
                View view2 = qVar.f1089c.get(itemIdAtPosition);
                if (view2 != null) {
                    ViewCompat.setHasTransientState(view2, false);
                    qVar.f1089c.put(itemIdAtPosition, null);
                }
            }
        }
    }

    private static boolean c(int[] iArr, int i2) {
        int i3 = iArr[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            if (iArr[i4] == i3) {
                return true;
            }
        }
        return false;
    }

    private void f(View view, boolean z) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        ArrayList<Integer> arrayList = this.f1060i;
        if (arrayList == null || !arrayList.contains(Integer.valueOf(id))) {
            ArrayList<View> arrayList2 = this.j;
            if (arrayList2 == null || !arrayList2.contains(view)) {
                ArrayList<Class<?>> arrayList3 = this.k;
                if (arrayList3 != null) {
                    int size = arrayList3.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        if (this.k.get(i2).isInstance(view)) {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    TransitionValues transitionValues = new TransitionValues(view);
                    if (z) {
                        captureStartValues(transitionValues);
                    } else {
                        captureEndValues(transitionValues);
                    }
                    transitionValues.a.add(this);
                    g(transitionValues);
                    b(z ? this.p : this.q, view, transitionValues);
                }
                if (view instanceof ViewGroup) {
                    ArrayList<Integer> arrayList4 = this.m;
                    if (arrayList4 == null || !arrayList4.contains(Integer.valueOf(id))) {
                        ArrayList<View> arrayList5 = this.n;
                        if (arrayList5 == null || !arrayList5.contains(view)) {
                            ArrayList<Class<?>> arrayList6 = this.o;
                            if (arrayList6 != null) {
                                int size2 = arrayList6.size();
                                for (int i3 = 0; i3 < size2; i3++) {
                                    if (this.o.get(i3).isInstance(view)) {
                                        return;
                                    }
                                }
                            }
                            ViewGroup viewGroup = (ViewGroup) view;
                            for (int i4 = 0; i4 < viewGroup.getChildCount(); i4++) {
                                f(viewGroup.getChildAt(i4), z);
                            }
                        }
                    }
                }
            }
        }
    }

    private ArrayList<Integer> l(ArrayList<Integer> arrayList, int i2, boolean z) {
        if (i2 <= 0) {
            return arrayList;
        }
        Integer valueOf = Integer.valueOf(i2);
        return z ? e.a(arrayList, valueOf) : e.b(arrayList, valueOf);
    }

    private static <T> ArrayList<T> m(ArrayList<T> arrayList, T t, boolean z) {
        return t != null ? z ? e.a(arrayList, t) : e.b(arrayList, t) : arrayList;
    }

    private ArrayList<Class<?>> n(ArrayList<Class<?>> arrayList, Class<?> cls, boolean z) {
        return cls != null ? z ? e.a(arrayList, cls) : e.b(arrayList, cls) : arrayList;
    }

    private ArrayList<View> o(ArrayList<View> arrayList, View view, boolean z) {
        return view != null ? z ? e.a(arrayList, view) : e.b(arrayList, view) : arrayList;
    }

    private static ArrayMap<Animator, d> r() {
        ArrayMap<Animator, d> arrayMap = I.get();
        if (arrayMap != null) {
            return arrayMap;
        }
        ArrayMap<Animator, d> arrayMap2 = new ArrayMap<>();
        I.set(arrayMap2);
        return arrayMap2;
    }

    private static boolean s(int i2) {
        return i2 >= 1 && i2 <= 4;
    }

    private static boolean u(TransitionValues transitionValues, TransitionValues transitionValues2, String str) {
        Object obj = transitionValues.values.get(str);
        Object obj2 = transitionValues2.values.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    private void v(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            View valueAt = sparseArray.valueAt(i2);
            if (valueAt != null && t(valueAt) && (view = sparseArray2.get(sparseArray.keyAt(i2))) != null && t(view)) {
                TransitionValues transitionValues = arrayMap.get(valueAt);
                TransitionValues transitionValues2 = arrayMap2.get(view);
                if (transitionValues != null && transitionValues2 != null) {
                    this.t.add(transitionValues);
                    this.u.add(transitionValues2);
                    arrayMap.remove(valueAt);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void w(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2) {
        TransitionValues remove;
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            View keyAt = arrayMap.keyAt(size);
            if (keyAt != null && t(keyAt) && (remove = arrayMap2.remove(keyAt)) != null && t(remove.view)) {
                this.t.add(arrayMap.removeAt(size));
                this.u.add(remove);
            }
        }
    }

    private void x(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, LongSparseArray<View> longSparseArray, LongSparseArray<View> longSparseArray2) {
        View view;
        int size = longSparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            View valueAt = longSparseArray.valueAt(i2);
            if (valueAt != null && t(valueAt) && (view = longSparseArray2.get(longSparseArray.keyAt(i2))) != null && t(view)) {
                TransitionValues transitionValues = arrayMap.get(valueAt);
                TransitionValues transitionValues2 = arrayMap2.get(view);
                if (transitionValues != null && transitionValues2 != null) {
                    this.t.add(transitionValues);
                    this.u.add(transitionValues2);
                    arrayMap.remove(valueAt);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void y(ArrayMap<View, TransitionValues> arrayMap, ArrayMap<View, TransitionValues> arrayMap2, ArrayMap<String, View> arrayMap3, ArrayMap<String, View> arrayMap4) {
        View view;
        int size = arrayMap3.size();
        for (int i2 = 0; i2 < size; i2++) {
            View valueAt = arrayMap3.valueAt(i2);
            if (valueAt != null && t(valueAt) && (view = arrayMap4.get(arrayMap3.keyAt(i2))) != null && t(view)) {
                TransitionValues transitionValues = arrayMap.get(valueAt);
                TransitionValues transitionValues2 = arrayMap2.get(view);
                if (transitionValues != null && transitionValues2 != null) {
                    this.t.add(transitionValues);
                    this.u.add(transitionValues2);
                    arrayMap.remove(valueAt);
                    arrayMap2.remove(view);
                }
            }
        }
    }

    private void z(q qVar, q qVar2) {
        ArrayMap<View, TransitionValues> arrayMap = new ArrayMap<>(qVar.a);
        ArrayMap<View, TransitionValues> arrayMap2 = new ArrayMap<>(qVar2.a);
        int i2 = 0;
        while (true) {
            int[] iArr = this.s;
            if (i2 >= iArr.length) {
                a(arrayMap, arrayMap2);
                return;
            }
            int i3 = iArr[i2];
            if (i3 == 1) {
                w(arrayMap, arrayMap2);
            } else if (i3 == 2) {
                y(arrayMap, arrayMap2, qVar.f1090d, qVar2.f1090d);
            } else if (i3 == 3) {
                v(arrayMap, arrayMap2, qVar.b, qVar2.b);
            } else if (i3 == 4) {
                x(arrayMap, arrayMap2, qVar.f1089c, qVar2.f1089c);
            }
            i2++;
        }
    }

    public void B(ViewGroup viewGroup) {
        d dVar;
        this.t = new ArrayList<>();
        this.u = new ArrayList<>();
        z(this.p, this.q);
        ArrayMap<Animator, d> r = r();
        int size = r.size();
        k0 d2 = b0.d(viewGroup);
        for (int i2 = size - 1; i2 >= 0; i2--) {
            Animator keyAt = r.keyAt(i2);
            if (keyAt != null && (dVar = r.get(keyAt)) != null && dVar.a != null && d2.equals(dVar.f1062d)) {
                TransitionValues transitionValues = dVar.f1061c;
                View view = dVar.a;
                TransitionValues transitionValues2 = getTransitionValues(view, true);
                TransitionValues q = q(view, true);
                if (transitionValues2 == null && q == null) {
                    q = this.q.a.get(view);
                }
                if (!(transitionValues2 == null && q == null) && dVar.e.isTransitionRequired(transitionValues, q)) {
                    if (keyAt.isRunning() || keyAt.isStarted()) {
                        keyAt.cancel();
                    } else {
                        r.remove(keyAt);
                    }
                }
            }
        }
        j(viewGroup, this.p, this.q, this.t, this.u);
        D();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void D() {
        G();
        ArrayMap<Animator, d> r = r();
        Iterator<Animator> it = this.B.iterator();
        while (it.hasNext()) {
            Animator next = it.next();
            if (r.containsKey(next)) {
                G();
                C(next, r);
            }
        }
        this.B.clear();
        k();
    }

    public void E(boolean z) {
        this.v = z;
    }

    public Transition F(ViewGroup viewGroup) {
        return this;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void G() {
        if (this.x == 0) {
            ArrayList<TransitionListener> arrayList = this.A;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.A.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((TransitionListener) arrayList2.get(i2)).onTransitionStart(this);
                }
            }
            this.z = false;
        }
        this.x++;
    }

    public String H(String str) {
        String str2 = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.f1058c != -1) {
            str2 = str2 + "dur(" + this.f1058c + ") ";
        }
        if (this.b != -1) {
            str2 = str2 + "dly(" + this.b + ") ";
        }
        if (this.f1059d != null) {
            str2 = str2 + "interp(" + this.f1059d + ") ";
        }
        if (this.e.size() <= 0 && this.f.size() <= 0) {
            return str2;
        }
        String str3 = str2 + "tgts(";
        if (this.e.size() > 0) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                if (i2 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.e.get(i2);
            }
        }
        if (this.f.size() > 0) {
            for (int i3 = 0; i3 < this.f.size(); i3++) {
                if (i3 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.f.get(i3);
            }
        }
        return str3 + ")";
    }

    @NonNull
    public Transition addListener(@NonNull TransitionListener transitionListener) {
        if (this.A == null) {
            this.A = new ArrayList<>();
        }
        this.A.add(transitionListener);
        return this;
    }

    @NonNull
    public Transition addTarget(@IdRes int i2) {
        if (i2 != 0) {
            this.e.add(Integer.valueOf(i2));
        }
        return this;
    }

    @NonNull
    public Transition addTarget(@NonNull View view) {
        this.f.add(view);
        return this;
    }

    @NonNull
    public Transition addTarget(@NonNull Class<?> cls) {
        if (this.h == null) {
            this.h = new ArrayList<>();
        }
        this.h.add(cls);
        return this;
    }

    @NonNull
    public Transition addTarget(@NonNull String str) {
        if (this.g == null) {
            this.g = new ArrayList<>();
        }
        this.g.add(str);
        return this;
    }

    public abstract void captureEndValues(@NonNull TransitionValues transitionValues);

    public abstract void captureStartValues(@NonNull TransitionValues transitionValues);

    @Override // 
    /* renamed from: clone */
    public Transition mo11clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.B = new ArrayList<>();
            transition.p = new q();
            transition.q = new q();
            transition.t = null;
            transition.u = null;
            return transition;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        return null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    protected void d(Animator animator) {
        if (animator == null) {
            k();
            return;
        }
        if (getDuration() >= 0) {
            animator.setDuration(getDuration());
        }
        if (getStartDelay() >= 0) {
            animator.setStartDelay(getStartDelay() + animator.getStartDelay());
        }
        if (getInterpolator() != null) {
            animator.setInterpolator(getInterpolator());
        }
        animator.addListener(new c());
        animator.start();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void e() {
        for (int size = this.w.size() - 1; size >= 0; size--) {
            this.w.get(size).cancel();
        }
        ArrayList<TransitionListener> arrayList = this.A;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        ArrayList arrayList2 = (ArrayList) this.A.clone();
        int size2 = arrayList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((TransitionListener) arrayList2.get(i2)).onTransitionCancel(this);
        }
    }

    @NonNull
    public Transition excludeChildren(@IdRes int i2, boolean z) {
        this.m = l(this.m, i2, z);
        return this;
    }

    @NonNull
    public Transition excludeChildren(@NonNull View view, boolean z) {
        this.n = o(this.n, view, z);
        return this;
    }

    @NonNull
    public Transition excludeChildren(@NonNull Class<?> cls, boolean z) {
        this.o = n(this.o, cls, z);
        return this;
    }

    @NonNull
    public Transition excludeTarget(@IdRes int i2, boolean z) {
        this.f1060i = l(this.f1060i, i2, z);
        return this;
    }

    @NonNull
    public Transition excludeTarget(@NonNull View view, boolean z) {
        this.j = o(this.j, view, z);
        return this;
    }

    @NonNull
    public Transition excludeTarget(@NonNull Class<?> cls, boolean z) {
        this.k = n(this.k, cls, z);
        return this;
    }

    @NonNull
    public Transition excludeTarget(@NonNull String str, boolean z) {
        this.l = m(this.l, str, z);
        return this;
    }

    public void g(TransitionValues transitionValues) {
        String[] propagationProperties;
        if (this.C == null || transitionValues.values.isEmpty() || (propagationProperties = this.C.getPropagationProperties()) == null) {
            return;
        }
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= propagationProperties.length) {
                z = true;
                break;
            } else if (!transitionValues.values.containsKey(propagationProperties[i2])) {
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            return;
        }
        this.C.captureValues(transitionValues);
    }

    public long getDuration() {
        return this.f1058c;
    }

    @Nullable
    public Rect getEpicenter() {
        EpicenterCallback epicenterCallback = this.D;
        if (epicenterCallback == null) {
            return null;
        }
        return epicenterCallback.onGetEpicenter(this);
    }

    @Nullable
    public EpicenterCallback getEpicenterCallback() {
        return this.D;
    }

    @Nullable
    public TimeInterpolator getInterpolator() {
        return this.f1059d;
    }

    @NonNull
    public String getName() {
        return this.a;
    }

    @NonNull
    public PathMotion getPathMotion() {
        return this.F;
    }

    @Nullable
    public TransitionPropagation getPropagation() {
        return this.C;
    }

    public long getStartDelay() {
        return this.b;
    }

    @NonNull
    public List<Integer> getTargetIds() {
        return this.e;
    }

    @Nullable
    public List<String> getTargetNames() {
        return this.g;
    }

    @Nullable
    public List<Class<?>> getTargetTypes() {
        return this.h;
    }

    @NonNull
    public List<View> getTargets() {
        return this.f;
    }

    @Nullable
    public String[] getTransitionProperties() {
        return null;
    }

    @Nullable
    public TransitionValues getTransitionValues(@NonNull View view, boolean z) {
        TransitionSet transitionSet = this.r;
        if (transitionSet != null) {
            return transitionSet.getTransitionValues(view, z);
        }
        return (z ? this.p : this.q).a.get(view);
    }

    public void h(ViewGroup viewGroup, boolean z) {
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        ArrayMap<String, String> arrayMap;
        i(z);
        if ((this.e.size() > 0 || this.f.size() > 0) && (((arrayList = this.g) == null || arrayList.isEmpty()) && ((arrayList2 = this.h) == null || arrayList2.isEmpty()))) {
            for (int i2 = 0; i2 < this.e.size(); i2++) {
                View findViewById = viewGroup.findViewById(this.e.get(i2).intValue());
                if (findViewById != null) {
                    TransitionValues transitionValues = new TransitionValues(findViewById);
                    if (z) {
                        captureStartValues(transitionValues);
                    } else {
                        captureEndValues(transitionValues);
                    }
                    transitionValues.a.add(this);
                    g(transitionValues);
                    b(z ? this.p : this.q, findViewById, transitionValues);
                }
            }
            for (int i3 = 0; i3 < this.f.size(); i3++) {
                View view = this.f.get(i3);
                TransitionValues transitionValues2 = new TransitionValues(view);
                if (z) {
                    captureStartValues(transitionValues2);
                } else {
                    captureEndValues(transitionValues2);
                }
                transitionValues2.a.add(this);
                g(transitionValues2);
                b(z ? this.p : this.q, view, transitionValues2);
            }
        } else {
            f(viewGroup, z);
        }
        if (z || (arrayMap = this.E) == null) {
            return;
        }
        int size = arrayMap.size();
        ArrayList arrayList3 = new ArrayList(size);
        for (int i4 = 0; i4 < size; i4++) {
            arrayList3.add(this.p.f1090d.remove(this.E.keyAt(i4)));
        }
        for (int i5 = 0; i5 < size; i5++) {
            View view2 = (View) arrayList3.get(i5);
            if (view2 != null) {
                this.p.f1090d.put(this.E.valueAt(i5), view2);
            }
        }
    }

    public void i(boolean z) {
        q qVar;
        if (z) {
            this.p.a.clear();
            this.p.b.clear();
            qVar = this.p;
        } else {
            this.q.a.clear();
            this.q.b.clear();
            qVar = this.q;
        }
        qVar.f1089c.clear();
    }

    public boolean isTransitionRequired(@Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return false;
        }
        String[] transitionProperties = getTransitionProperties();
        if (transitionProperties == null) {
            Iterator<String> it = transitionValues.values.keySet().iterator();
            while (it.hasNext()) {
                if (u(transitionValues, transitionValues2, it.next())) {
                }
            }
            return false;
        }
        for (String str : transitionProperties) {
            if (!u(transitionValues, transitionValues2, str)) {
            }
        }
        return false;
        return true;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void j(ViewGroup viewGroup, q qVar, q qVar2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        Animator createAnimator;
        int i2;
        int i3;
        View view;
        Animator animator;
        TransitionValues transitionValues;
        Animator animator2;
        TransitionValues transitionValues2;
        ArrayMap<Animator, d> r = r();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        long j = Long.MAX_VALUE;
        int i4 = 0;
        while (i4 < size) {
            TransitionValues transitionValues3 = arrayList.get(i4);
            TransitionValues transitionValues4 = arrayList2.get(i4);
            if (transitionValues3 != null && !transitionValues3.a.contains(this)) {
                transitionValues3 = null;
            }
            if (transitionValues4 != null && !transitionValues4.a.contains(this)) {
                transitionValues4 = null;
            }
            if (transitionValues3 != null || transitionValues4 != null) {
                if ((transitionValues3 == null || transitionValues4 == null || isTransitionRequired(transitionValues3, transitionValues4)) && (createAnimator = createAnimator(viewGroup, transitionValues3, transitionValues4)) != null) {
                    if (transitionValues4 != null) {
                        view = transitionValues4.view;
                        String[] transitionProperties = getTransitionProperties();
                        if (transitionProperties != null && transitionProperties.length > 0) {
                            transitionValues2 = new TransitionValues(view);
                            i2 = size;
                            TransitionValues transitionValues5 = qVar2.a.get(view);
                            if (transitionValues5 != null) {
                                int i5 = 0;
                                while (i5 < transitionProperties.length) {
                                    transitionValues2.values.put(transitionProperties[i5], transitionValues5.values.get(transitionProperties[i5]));
                                    i5++;
                                    i4 = i4;
                                    transitionValues5 = transitionValues5;
                                }
                            }
                            i3 = i4;
                            int size2 = r.size();
                            int i6 = 0;
                            while (true) {
                                if (i6 >= size2) {
                                    animator2 = createAnimator;
                                    break;
                                }
                                d dVar = r.get(r.keyAt(i6));
                                if (dVar.f1061c != null && dVar.a == view && dVar.b.equals(getName()) && dVar.f1061c.equals(transitionValues2)) {
                                    animator2 = null;
                                    break;
                                }
                                i6++;
                            }
                        } else {
                            i2 = size;
                            i3 = i4;
                            animator2 = createAnimator;
                            transitionValues2 = null;
                        }
                        animator = animator2;
                        transitionValues = transitionValues2;
                    } else {
                        i2 = size;
                        i3 = i4;
                        view = transitionValues3.view;
                        animator = createAnimator;
                        transitionValues = null;
                    }
                    if (animator != null) {
                        TransitionPropagation transitionPropagation = this.C;
                        if (transitionPropagation != null) {
                            long startDelay = transitionPropagation.getStartDelay(viewGroup, this, transitionValues3, transitionValues4);
                            sparseIntArray.put(this.B.size(), (int) startDelay);
                            j = Math.min(startDelay, j);
                        }
                        r.put(animator, new d(view, getName(), this, b0.d(viewGroup), transitionValues));
                        this.B.add(animator);
                        j = j;
                    }
                    i4 = i3 + 1;
                    size = i2;
                }
            }
            i2 = size;
            i3 = i4;
            i4 = i3 + 1;
            size = i2;
        }
        if (sparseIntArray.size() != 0) {
            for (int i7 = 0; i7 < sparseIntArray.size(); i7++) {
                Animator animator3 = this.B.get(sparseIntArray.keyAt(i7));
                animator3.setStartDelay((sparseIntArray.valueAt(i7) - j) + animator3.getStartDelay());
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void k() {
        int i2 = this.x - 1;
        this.x = i2;
        if (i2 == 0) {
            ArrayList<TransitionListener> arrayList = this.A;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.A.clone();
                int size = arrayList2.size();
                for (int i3 = 0; i3 < size; i3++) {
                    ((TransitionListener) arrayList2.get(i3)).onTransitionEnd(this);
                }
            }
            for (int i4 = 0; i4 < this.p.f1089c.size(); i4++) {
                View valueAt = this.p.f1089c.valueAt(i4);
                if (valueAt != null) {
                    ViewCompat.setHasTransientState(valueAt, false);
                }
            }
            for (int i5 = 0; i5 < this.q.f1089c.size(); i5++) {
                View valueAt2 = this.q.f1089c.valueAt(i5);
                if (valueAt2 != null) {
                    ViewCompat.setHasTransientState(valueAt2, false);
                }
            }
            this.z = true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void p(ViewGroup viewGroup) {
        ArrayMap<Animator, d> r = r();
        int size = r.size();
        if (viewGroup == null || size == 0) {
            return;
        }
        k0 d2 = b0.d(viewGroup);
        ArrayMap arrayMap = new ArrayMap(r);
        r.clear();
        for (int i2 = size - 1; i2 >= 0; i2--) {
            d dVar = (d) arrayMap.valueAt(i2);
            if (dVar.a != null && d2 != null && d2.equals(dVar.f1062d)) {
                ((Animator) arrayMap.keyAt(i2)).end();
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void pause(View view) {
        if (this.z) {
            return;
        }
        ArrayMap<Animator, d> r = r();
        int size = r.size();
        k0 d2 = b0.d(view);
        for (int i2 = size - 1; i2 >= 0; i2--) {
            d valueAt = r.valueAt(i2);
            if (valueAt.a != null && d2.equals(valueAt.f1062d)) {
                androidx.transition.a.b(r.keyAt(i2));
            }
        }
        ArrayList<TransitionListener> arrayList = this.A;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.A.clone();
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ((TransitionListener) arrayList2.get(i3)).onTransitionPause(this);
            }
        }
        this.y = true;
    }

    public TransitionValues q(View view, boolean z) {
        TransitionSet transitionSet = this.r;
        if (transitionSet != null) {
            return transitionSet.q(view, z);
        }
        ArrayList<TransitionValues> arrayList = z ? this.t : this.u;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        int i2 = -1;
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                break;
            }
            TransitionValues transitionValues = arrayList.get(i3);
            if (transitionValues == null) {
                return null;
            }
            if (transitionValues.view == view) {
                i2 = i3;
                break;
            }
            i3++;
        }
        if (i2 >= 0) {
            return (z ? this.u : this.t).get(i2);
        }
        return null;
    }

    @NonNull
    public Transition removeListener(@NonNull TransitionListener transitionListener) {
        ArrayList<TransitionListener> arrayList = this.A;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(transitionListener);
        if (this.A.size() == 0) {
            this.A = null;
        }
        return this;
    }

    @NonNull
    public Transition removeTarget(@IdRes int i2) {
        if (i2 != 0) {
            this.e.remove(Integer.valueOf(i2));
        }
        return this;
    }

    @NonNull
    public Transition removeTarget(@NonNull View view) {
        this.f.remove(view);
        return this;
    }

    @NonNull
    public Transition removeTarget(@NonNull Class<?> cls) {
        ArrayList<Class<?>> arrayList = this.h;
        if (arrayList != null) {
            arrayList.remove(cls);
        }
        return this;
    }

    @NonNull
    public Transition removeTarget(@NonNull String str) {
        ArrayList<String> arrayList = this.g;
        if (arrayList != null) {
            arrayList.remove(str);
        }
        return this;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void resume(View view) {
        if (this.y) {
            if (!this.z) {
                ArrayMap<Animator, d> r = r();
                int size = r.size();
                k0 d2 = b0.d(view);
                for (int i2 = size - 1; i2 >= 0; i2--) {
                    d valueAt = r.valueAt(i2);
                    if (valueAt.a != null && d2.equals(valueAt.f1062d)) {
                        androidx.transition.a.c(r.keyAt(i2));
                    }
                }
                ArrayList<TransitionListener> arrayList = this.A;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.A.clone();
                    int size2 = arrayList2.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        ((TransitionListener) arrayList2.get(i3)).onTransitionResume(this);
                    }
                }
            }
            this.y = false;
        }
    }

    @NonNull
    public Transition setDuration(long j) {
        this.f1058c = j;
        return this;
    }

    public void setEpicenterCallback(@Nullable EpicenterCallback epicenterCallback) {
        this.D = epicenterCallback;
    }

    @NonNull
    public Transition setInterpolator(@Nullable TimeInterpolator timeInterpolator) {
        this.f1059d = timeInterpolator;
        return this;
    }

    public void setMatchOrder(int... iArr) {
        if (iArr == null || iArr.length == 0) {
            this.s = G;
            return;
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (!s(iArr[i2])) {
                throw new IllegalArgumentException("matches contains invalid value");
            }
            if (c(iArr, i2)) {
                throw new IllegalArgumentException("matches contains a duplicate value");
            }
        }
        this.s = (int[]) iArr.clone();
    }

    public void setPathMotion(@Nullable PathMotion pathMotion) {
        if (pathMotion == null) {
            pathMotion = H;
        }
        this.F = pathMotion;
    }

    public void setPropagation(@Nullable TransitionPropagation transitionPropagation) {
        this.C = transitionPropagation;
    }

    @NonNull
    public Transition setStartDelay(long j) {
        this.b = j;
        return this;
    }

    public boolean t(View view) {
        ArrayList<Class<?>> arrayList;
        ArrayList<String> arrayList2;
        int id = view.getId();
        ArrayList<Integer> arrayList3 = this.f1060i;
        if (arrayList3 != null && arrayList3.contains(Integer.valueOf(id))) {
            return false;
        }
        ArrayList<View> arrayList4 = this.j;
        if (arrayList4 != null && arrayList4.contains(view)) {
            return false;
        }
        ArrayList<Class<?>> arrayList5 = this.k;
        if (arrayList5 != null) {
            int size = arrayList5.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.k.get(i2).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.l != null && ViewCompat.getTransitionName(view) != null && this.l.contains(ViewCompat.getTransitionName(view))) {
            return false;
        }
        if ((this.e.size() == 0 && this.f.size() == 0 && (((arrayList = this.h) == null || arrayList.isEmpty()) && ((arrayList2 = this.g) == null || arrayList2.isEmpty()))) || this.e.contains(Integer.valueOf(id)) || this.f.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.g;
        if (arrayList6 != null && arrayList6.contains(ViewCompat.getTransitionName(view))) {
            return true;
        }
        if (this.h != null) {
            for (int i3 = 0; i3 < this.h.size(); i3++) {
                if (this.h.get(i3).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        return H("");
    }
}
