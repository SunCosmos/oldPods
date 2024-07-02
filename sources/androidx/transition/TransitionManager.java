package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class TransitionManager {

    /* renamed from: c, reason: collision with root package name */
    private static Transition f1064c = new AutoTransition();

    /* renamed from: d, reason: collision with root package name */
    private static ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>> f1065d = new ThreadLocal<>();
    static ArrayList<ViewGroup> e = new ArrayList<>();
    private ArrayMap<Scene, Transition> a = new ArrayMap<>();
    private ArrayMap<Scene, ArrayMap<Scene, Transition>> b = new ArrayMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        Transition a;
        ViewGroup b;

        /* renamed from: androidx.transition.TransitionManager$a$a, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class C0039a extends TransitionListenerAdapter {
            final /* synthetic */ ArrayMap a;

            C0039a(ArrayMap arrayMap) {
                this.a = arrayMap;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
            public void onTransitionEnd(@NonNull Transition transition) {
                ((ArrayList) this.a.get(a.this.b)).remove(transition);
                transition.removeListener(this);
            }
        }

        a(Transition transition, ViewGroup viewGroup) {
            this.a = transition;
            this.b = viewGroup;
        }

        private void a() {
            this.b.getViewTreeObserver().removeOnPreDrawListener(this);
            this.b.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!TransitionManager.e.remove(this.b)) {
                return true;
            }
            ArrayMap<ViewGroup, ArrayList<Transition>> b = TransitionManager.b();
            ArrayList<Transition> arrayList = b.get(this.b);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                b.put(this.b, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.a);
            this.a.addListener(new C0039a(b));
            this.a.h(this.b, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).resume(this.b);
                }
            }
            this.a.B(this.b);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            TransitionManager.e.remove(this.b);
            ArrayList<Transition> arrayList = TransitionManager.b().get(this.b);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<Transition> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().resume(this.b);
                }
            }
            this.a.i(true);
        }
    }

    private static void a(Scene scene, Transition transition) {
        ViewGroup sceneRoot = scene.getSceneRoot();
        if (e.contains(sceneRoot)) {
            return;
        }
        Scene currentScene = Scene.getCurrentScene(sceneRoot);
        if (transition == null) {
            if (currentScene != null) {
                currentScene.exit();
            }
            scene.enter();
            return;
        }
        e.add(sceneRoot);
        Transition mo11clone = transition.mo11clone();
        mo11clone.F(sceneRoot);
        if (currentScene != null && currentScene.a()) {
            mo11clone.E(true);
        }
        e(sceneRoot, mo11clone);
        scene.enter();
        d(sceneRoot, mo11clone);
    }

    static ArrayMap<ViewGroup, ArrayList<Transition>> b() {
        ArrayMap<ViewGroup, ArrayList<Transition>> arrayMap;
        WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>> weakReference = f1065d.get();
        if (weakReference != null && (arrayMap = weakReference.get()) != null) {
            return arrayMap;
        }
        ArrayMap<ViewGroup, ArrayList<Transition>> arrayMap2 = new ArrayMap<>();
        f1065d.set(new WeakReference<>(arrayMap2));
        return arrayMap2;
    }

    public static void beginDelayedTransition(@NonNull ViewGroup viewGroup) {
        beginDelayedTransition(viewGroup, null);
    }

    public static void beginDelayedTransition(@NonNull ViewGroup viewGroup, @Nullable Transition transition) {
        if (e.contains(viewGroup) || !ViewCompat.isLaidOut(viewGroup)) {
            return;
        }
        e.add(viewGroup);
        if (transition == null) {
            transition = f1064c;
        }
        Transition mo11clone = transition.mo11clone();
        e(viewGroup, mo11clone);
        Scene.b(viewGroup, null);
        d(viewGroup, mo11clone);
    }

    private Transition c(Scene scene) {
        Scene currentScene;
        ArrayMap<Scene, Transition> arrayMap;
        Transition transition;
        ViewGroup sceneRoot = scene.getSceneRoot();
        if (sceneRoot != null && (currentScene = Scene.getCurrentScene(sceneRoot)) != null && (arrayMap = this.b.get(scene)) != null && (transition = arrayMap.get(currentScene)) != null) {
            return transition;
        }
        Transition transition2 = this.a.get(scene);
        return transition2 != null ? transition2 : f1064c;
    }

    private static void d(ViewGroup viewGroup, Transition transition) {
        if (transition == null || viewGroup == null) {
            return;
        }
        a aVar = new a(transition, viewGroup);
        viewGroup.addOnAttachStateChangeListener(aVar);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
    }

    private static void e(ViewGroup viewGroup, Transition transition) {
        ArrayList<Transition> arrayList = b().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<Transition> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().pause(viewGroup);
            }
        }
        if (transition != null) {
            transition.h(viewGroup, true);
        }
        Scene currentScene = Scene.getCurrentScene(viewGroup);
        if (currentScene != null) {
            currentScene.exit();
        }
    }

    public static void endTransitions(ViewGroup viewGroup) {
        e.remove(viewGroup);
        ArrayList<Transition> arrayList = b().get(viewGroup);
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        ArrayList arrayList2 = new ArrayList(arrayList);
        for (int size = arrayList2.size() - 1; size >= 0; size--) {
            ((Transition) arrayList2.get(size)).p(viewGroup);
        }
    }

    public static void go(@NonNull Scene scene) {
        a(scene, f1064c);
    }

    public static void go(@NonNull Scene scene, @Nullable Transition transition) {
        a(scene, transition);
    }

    public void setTransition(@NonNull Scene scene, @NonNull Scene scene2, @Nullable Transition transition) {
        ArrayMap<Scene, Transition> arrayMap = this.b.get(scene2);
        if (arrayMap == null) {
            arrayMap = new ArrayMap<>();
            this.b.put(scene2, arrayMap);
        }
        arrayMap.put(scene, transition);
    }

    public void setTransition(@NonNull Scene scene, @Nullable Transition transition) {
        this.a.put(scene, transition);
    }

    public void transitionTo(@NonNull Scene scene) {
        a(scene, c(scene));
    }
}
