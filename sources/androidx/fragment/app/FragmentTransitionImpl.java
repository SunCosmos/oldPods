package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressLint({"UnknownNullness"})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public abstract class FragmentTransitionImpl {

    /* loaded from: classes.dex */
    class a implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ ArrayList b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ArrayList f748c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ ArrayList f749d;
        final /* synthetic */ ArrayList e;

        a(FragmentTransitionImpl fragmentTransitionImpl, int i2, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
            this.a = i2;
            this.b = arrayList;
            this.f748c = arrayList2;
            this.f749d = arrayList3;
            this.e = arrayList4;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (int i2 = 0; i2 < this.a; i2++) {
                ViewCompat.setTransitionName((View) this.b.get(i2), (String) this.f748c.get(i2));
                ViewCompat.setTransitionName((View) this.f749d.get(i2), (String) this.e.get(i2));
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        final /* synthetic */ ArrayList a;
        final /* synthetic */ Map b;

        b(FragmentTransitionImpl fragmentTransitionImpl, ArrayList arrayList, Map map) {
            this.a = arrayList;
            this.b = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.a.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = (View) this.a.get(i2);
                String transitionName = ViewCompat.getTransitionName(view);
                if (transitionName != null) {
                    ViewCompat.setTransitionName(view, FragmentTransitionImpl.d(this.b, transitionName));
                }
            }
        }
    }

    /* loaded from: classes.dex */
    class c implements Runnable {
        final /* synthetic */ ArrayList a;
        final /* synthetic */ Map b;

        c(FragmentTransitionImpl fragmentTransitionImpl, ArrayList arrayList, Map map) {
            this.a = arrayList;
            this.b = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            int size = this.a.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = (View) this.a.get(i2);
                ViewCompat.setTransitionName(view, (String) this.b.get(ViewCompat.getTransitionName(view)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(List<View> list, View view) {
        int size = list.size();
        if (c(list, view, size)) {
            return;
        }
        if (ViewCompat.getTransitionName(view) != null) {
            list.add(view);
        }
        for (int i2 = size; i2 < list.size(); i2++) {
            View view2 = list.get(i2);
            if (view2 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view2;
                int childCount = viewGroup.getChildCount();
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = viewGroup.getChildAt(i3);
                    if (!c(list, childAt, size) && ViewCompat.getTransitionName(childAt) != null) {
                        list.add(childAt);
                    }
                }
            }
        }
    }

    private static boolean c(List<View> list, View view, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (list.get(i3) == view) {
                return true;
            }
        }
        return false;
    }

    static String d(Map<String, String> map, String str) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (str.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean g(List list) {
        return list == null || list.isEmpty();
    }

    public abstract void addTarget(Object obj, View view);

    public abstract void addTargets(Object obj, ArrayList<View> arrayList);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() == 0) {
            boolean z = view instanceof ViewGroup;
            View view2 = view;
            if (z) {
                ViewGroup viewGroup = (ViewGroup) view;
                boolean isTransitionGroup = ViewGroupCompat.isTransitionGroup(viewGroup);
                view2 = viewGroup;
                if (!isTransitionGroup) {
                    int childCount = viewGroup.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        b(arrayList, viewGroup.getChildAt(i2));
                    }
                    return;
                }
            }
            arrayList.add(view2);
        }
    }

    public abstract void beginDelayedTransition(ViewGroup viewGroup, Object obj);

    public abstract boolean canHandle(Object obj);

    public abstract Object cloneTransition(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(Map<String, View> map, @NonNull View view) {
        if (view.getVisibility() == 0) {
            String transitionName = ViewCompat.getTransitionName(view);
            if (transitionName != null) {
                map.put(transitionName, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    e(map, viewGroup.getChildAt(i2));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f(View view, Rect rect) {
        if (ViewCompat.isAttachedToWindow(view)) {
            RectF rectF = new RectF();
            rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
            view.getMatrix().mapRect(rectF);
            rectF.offset(view.getLeft(), view.getTop());
            Object parent = view.getParent();
            while (parent instanceof View) {
                View view2 = (View) parent;
                rectF.offset(-view2.getScrollX(), -view2.getScrollY());
                view2.getMatrix().mapRect(rectF);
                rectF.offset(view2.getLeft(), view2.getTop());
                parent = view2.getParent();
            }
            view.getRootView().getLocationOnScreen(new int[2]);
            rectF.offset(r1[0], r1[1]);
            rect.set(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList<String> h(ArrayList<View> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = arrayList.get(i2);
            arrayList2.add(ViewCompat.getTransitionName(view));
            ViewCompat.setTransitionName(view, null);
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(ViewGroup viewGroup, ArrayList<View> arrayList, Map<String, String> map) {
        OneShotPreDrawListener.add(viewGroup, new c(this, arrayList, map));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(View view, ArrayList<View> arrayList, Map<String, String> map) {
        OneShotPreDrawListener.add(view, new b(this, arrayList, map));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(View view, ArrayList<View> arrayList, ArrayList<View> arrayList2, ArrayList<String> arrayList3, Map<String, String> map) {
        int size = arrayList2.size();
        ArrayList arrayList4 = new ArrayList();
        for (int i2 = 0; i2 < size; i2++) {
            View view2 = arrayList.get(i2);
            String transitionName = ViewCompat.getTransitionName(view2);
            arrayList4.add(transitionName);
            if (transitionName != null) {
                ViewCompat.setTransitionName(view2, null);
                String str = map.get(transitionName);
                int i3 = 0;
                while (true) {
                    if (i3 >= size) {
                        break;
                    }
                    if (str.equals(arrayList3.get(i3))) {
                        ViewCompat.setTransitionName(arrayList2.get(i3), transitionName);
                        break;
                    }
                    i3++;
                }
            }
        }
        OneShotPreDrawListener.add(view, new a(this, size, arrayList2, arrayList3, arrayList, arrayList4));
    }

    public abstract Object mergeTransitionsInSequence(Object obj, Object obj2, Object obj3);

    public abstract Object mergeTransitionsTogether(Object obj, Object obj2, Object obj3);

    public abstract void removeTarget(Object obj, View view);

    public abstract void replaceTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract void scheduleHideFragmentView(Object obj, View view, ArrayList<View> arrayList);

    public abstract void scheduleRemoveTargets(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3);

    public abstract void setEpicenter(Object obj, Rect rect);

    public abstract void setEpicenter(Object obj, View view);

    public void setListenerForTransitionEnd(@NonNull Fragment fragment, @NonNull Object obj, @NonNull CancellationSignal cancellationSignal, @NonNull Runnable runnable) {
        runnable.run();
    }

    public abstract void setSharedElementTargets(Object obj, View view, ArrayList<View> arrayList);

    public abstract void swapSharedElementTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract Object wrapTransitionInSet(Object obj);
}
