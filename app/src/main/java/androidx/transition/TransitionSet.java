package androidx.transition;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.TypedArrayUtils;
import androidx.transition.Transition;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class TransitionSet extends Transition {
    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER = 0;
    private ArrayList<Transition> J;
    private boolean K;
    int L;
    boolean M;
    private int N;

    /* loaded from: classes.dex */
    class a extends TransitionListenerAdapter {
        final /* synthetic */ Transition a;

        a(TransitionSet transitionSet, Transition transition) {
            this.a = transition;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            this.a.D();
            transition.removeListener(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b extends TransitionListenerAdapter {
        TransitionSet a;

        b(TransitionSet transitionSet) {
            this.a = transitionSet;
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            TransitionSet transitionSet = this.a;
            int i2 = transitionSet.L - 1;
            transitionSet.L = i2;
            if (i2 == 0) {
                transitionSet.M = false;
                transitionSet.k();
            }
            transition.removeListener(this);
        }

        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
        public void onTransitionStart(@NonNull Transition transition) {
            TransitionSet transitionSet = this.a;
            if (transitionSet.M) {
                return;
            }
            transitionSet.G();
            this.a.M = true;
        }
    }

    public TransitionSet() {
        this.J = new ArrayList<>();
        this.K = true;
        this.M = false;
        this.N = 0;
    }

    @SuppressLint({"RestrictedApi"})
    public TransitionSet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.J = new ArrayList<>();
        this.K = true;
        this.M = false;
        this.N = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, o.f1086i);
        setOrdering(TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionOrdering", 0, 0));
        obtainStyledAttributes.recycle();
    }

    private void I(@NonNull Transition transition) {
        this.J.add(transition);
        transition.r = this;
    }

    private void K() {
        b bVar = new b(this);
        Iterator<Transition> it = this.J.iterator();
        while (it.hasNext()) {
            it.next().addListener(bVar);
        }
        this.L = this.J.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void D() {
        if (this.J.isEmpty()) {
            G();
            k();
            return;
        }
        K();
        if (this.K) {
            Iterator<Transition> it = this.J.iterator();
            while (it.hasNext()) {
                it.next().D();
            }
            return;
        }
        for (int i2 = 1; i2 < this.J.size(); i2++) {
            this.J.get(i2 - 1).addListener(new a(this, this.J.get(i2)));
        }
        Transition transition = this.J.get(0);
        if (transition != null) {
            transition.D();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.transition.Transition
    public void E(boolean z) {
        super.E(z);
        int size = this.J.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.J.get(i2).E(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition F(ViewGroup viewGroup) {
        J(viewGroup);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.transition.Transition
    public String H(String str) {
        String H = super.H(str);
        for (int i2 = 0; i2 < this.J.size(); i2++) {
            StringBuilder sb = new StringBuilder();
            sb.append(H);
            sb.append("\n");
            sb.append(this.J.get(i2).H(str + "  "));
            H = sb.toString();
        }
        return H;
    }

    TransitionSet J(ViewGroup viewGroup) {
        super.F(viewGroup);
        int size = this.J.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.J.get(i2).F(viewGroup);
        }
        return this;
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet addListener(@NonNull Transition.TransitionListener transitionListener) {
        return (TransitionSet) super.addListener(transitionListener);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public /* bridge */ /* synthetic */ Transition addTarget(@NonNull Class cls) {
        return addTarget((Class<?>) cls);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet addTarget(@IdRes int i2) {
        for (int i3 = 0; i3 < this.J.size(); i3++) {
            this.J.get(i3).addTarget(i2);
        }
        return (TransitionSet) super.addTarget(i2);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet addTarget(@NonNull View view) {
        for (int i2 = 0; i2 < this.J.size(); i2++) {
            this.J.get(i2).addTarget(view);
        }
        return (TransitionSet) super.addTarget(view);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet addTarget(@NonNull Class<?> cls) {
        for (int i2 = 0; i2 < this.J.size(); i2++) {
            this.J.get(i2).addTarget(cls);
        }
        return (TransitionSet) super.addTarget(cls);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet addTarget(@NonNull String str) {
        for (int i2 = 0; i2 < this.J.size(); i2++) {
            this.J.get(i2).addTarget(str);
        }
        return (TransitionSet) super.addTarget(str);
    }

    @NonNull
    public TransitionSet addTransition(@NonNull Transition transition) {
        I(transition);
        long j = this.f1058c;
        if (j >= 0) {
            transition.setDuration(j);
        }
        if ((this.N & 1) != 0) {
            transition.setInterpolator(getInterpolator());
        }
        if ((this.N & 2) != 0) {
            transition.setPropagation(getPropagation());
        }
        if ((this.N & 4) != 0) {
            transition.setPathMotion(getPathMotion());
        }
        if ((this.N & 8) != 0) {
            transition.setEpicenterCallback(getEpicenterCallback());
        }
        return this;
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        if (t(transitionValues.view)) {
            Iterator<Transition> it = this.J.iterator();
            while (it.hasNext()) {
                Transition next = it.next();
                if (next.t(transitionValues.view)) {
                    next.captureEndValues(transitionValues);
                    transitionValues.a.add(next);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        if (t(transitionValues.view)) {
            Iterator<Transition> it = this.J.iterator();
            while (it.hasNext()) {
                Transition next = it.next();
                if (next.t(transitionValues.view)) {
                    next.captureStartValues(transitionValues);
                    transitionValues.a.add(next);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    /* renamed from: clone */
    public Transition mo11clone() {
        TransitionSet transitionSet = (TransitionSet) super.mo11clone();
        transitionSet.J = new ArrayList<>();
        int size = this.J.size();
        for (int i2 = 0; i2 < size; i2++) {
            transitionSet.I(this.J.get(i2).mo11clone());
        }
        return transitionSet;
    }

    @Override // androidx.transition.Transition
    @NonNull
    public Transition excludeTarget(int i2, boolean z) {
        for (int i3 = 0; i3 < this.J.size(); i3++) {
            this.J.get(i3).excludeTarget(i2, z);
        }
        return super.excludeTarget(i2, z);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public Transition excludeTarget(@NonNull View view, boolean z) {
        for (int i2 = 0; i2 < this.J.size(); i2++) {
            this.J.get(i2).excludeTarget(view, z);
        }
        return super.excludeTarget(view, z);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public Transition excludeTarget(@NonNull Class<?> cls, boolean z) {
        for (int i2 = 0; i2 < this.J.size(); i2++) {
            this.J.get(i2).excludeTarget(cls, z);
        }
        return super.excludeTarget(cls, z);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public Transition excludeTarget(@NonNull String str, boolean z) {
        for (int i2 = 0; i2 < this.J.size(); i2++) {
            this.J.get(i2).excludeTarget(str, z);
        }
        return super.excludeTarget(str, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.transition.Transition
    public void g(TransitionValues transitionValues) {
        super.g(transitionValues);
        int size = this.J.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.J.get(i2).g(transitionValues);
        }
    }

    public int getOrdering() {
        return !this.K ? 1 : 0;
    }

    @Nullable
    public Transition getTransitionAt(int i2) {
        if (i2 < 0 || i2 >= this.J.size()) {
            return null;
        }
        return this.J.get(i2);
    }

    public int getTransitionCount() {
        return this.J.size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void j(ViewGroup viewGroup, q qVar, q qVar2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        long startDelay = getStartDelay();
        int size = this.J.size();
        for (int i2 = 0; i2 < size; i2++) {
            Transition transition = this.J.get(i2);
            if (startDelay > 0 && (this.K || i2 == 0)) {
                long startDelay2 = transition.getStartDelay();
                if (startDelay2 > 0) {
                    transition.setStartDelay(startDelay2 + startDelay);
                } else {
                    transition.setStartDelay(startDelay);
                }
            }
            transition.j(viewGroup, qVar, qVar2, arrayList, arrayList2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void p(ViewGroup viewGroup) {
        super.p(viewGroup);
        int size = this.J.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.J.get(i2).p(viewGroup);
        }
    }

    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void pause(View view) {
        super.pause(view);
        int size = this.J.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.J.get(i2).pause(view);
        }
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet removeListener(@NonNull Transition.TransitionListener transitionListener) {
        return (TransitionSet) super.removeListener(transitionListener);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public /* bridge */ /* synthetic */ Transition removeTarget(@NonNull Class cls) {
        return removeTarget((Class<?>) cls);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet removeTarget(@IdRes int i2) {
        for (int i3 = 0; i3 < this.J.size(); i3++) {
            this.J.get(i3).removeTarget(i2);
        }
        return (TransitionSet) super.removeTarget(i2);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet removeTarget(@NonNull View view) {
        for (int i2 = 0; i2 < this.J.size(); i2++) {
            this.J.get(i2).removeTarget(view);
        }
        return (TransitionSet) super.removeTarget(view);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet removeTarget(@NonNull Class<?> cls) {
        for (int i2 = 0; i2 < this.J.size(); i2++) {
            this.J.get(i2).removeTarget(cls);
        }
        return (TransitionSet) super.removeTarget(cls);
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet removeTarget(@NonNull String str) {
        for (int i2 = 0; i2 < this.J.size(); i2++) {
            this.J.get(i2).removeTarget(str);
        }
        return (TransitionSet) super.removeTarget(str);
    }

    @NonNull
    public TransitionSet removeTransition(@NonNull Transition transition) {
        this.J.remove(transition);
        transition.r = null;
        return this;
    }

    @Override // androidx.transition.Transition
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void resume(View view) {
        super.resume(view);
        int size = this.J.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.J.get(i2).resume(view);
        }
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet setDuration(long j) {
        ArrayList<Transition> arrayList;
        super.setDuration(j);
        if (this.f1058c >= 0 && (arrayList = this.J) != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.J.get(i2).setDuration(j);
            }
        }
        return this;
    }

    @Override // androidx.transition.Transition
    public void setEpicenterCallback(Transition.EpicenterCallback epicenterCallback) {
        super.setEpicenterCallback(epicenterCallback);
        this.N |= 8;
        int size = this.J.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.J.get(i2).setEpicenterCallback(epicenterCallback);
        }
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet setInterpolator(@Nullable TimeInterpolator timeInterpolator) {
        this.N |= 1;
        ArrayList<Transition> arrayList = this.J;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.J.get(i2).setInterpolator(timeInterpolator);
            }
        }
        return (TransitionSet) super.setInterpolator(timeInterpolator);
    }

    @NonNull
    public TransitionSet setOrdering(int i2) {
        if (i2 == 0) {
            this.K = true;
        } else {
            if (i2 != 1) {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i2);
            }
            this.K = false;
        }
        return this;
    }

    @Override // androidx.transition.Transition
    public void setPathMotion(PathMotion pathMotion) {
        super.setPathMotion(pathMotion);
        this.N |= 4;
        if (this.J != null) {
            for (int i2 = 0; i2 < this.J.size(); i2++) {
                this.J.get(i2).setPathMotion(pathMotion);
            }
        }
    }

    @Override // androidx.transition.Transition
    public void setPropagation(TransitionPropagation transitionPropagation) {
        super.setPropagation(transitionPropagation);
        this.N |= 2;
        int size = this.J.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.J.get(i2).setPropagation(transitionPropagation);
        }
    }

    @Override // androidx.transition.Transition
    @NonNull
    public TransitionSet setStartDelay(long j) {
        return (TransitionSet) super.setStartDelay(j);
    }
}
