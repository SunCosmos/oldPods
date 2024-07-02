package androidx.transition;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public class Scene {
    private Context a;
    private int b;

    /* renamed from: c, reason: collision with root package name */
    private ViewGroup f1055c;

    /* renamed from: d, reason: collision with root package name */
    private View f1056d;
    private Runnable e;
    private Runnable f;

    public Scene(@NonNull ViewGroup viewGroup) {
        this.b = -1;
        this.f1055c = viewGroup;
    }

    private Scene(ViewGroup viewGroup, int i2, Context context) {
        this.b = -1;
        this.a = context;
        this.f1055c = viewGroup;
        this.b = i2;
    }

    public Scene(@NonNull ViewGroup viewGroup, @NonNull View view) {
        this.b = -1;
        this.f1055c = viewGroup;
        this.f1056d = view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull ViewGroup viewGroup, @Nullable Scene scene) {
        viewGroup.setTag(R.id.transition_current_scene, scene);
    }

    @Nullable
    public static Scene getCurrentScene(@NonNull ViewGroup viewGroup) {
        return (Scene) viewGroup.getTag(R.id.transition_current_scene);
    }

    @NonNull
    public static Scene getSceneForLayout(@NonNull ViewGroup viewGroup, @LayoutRes int i2, @NonNull Context context) {
        int i3 = R.id.transition_scene_layoutid_cache;
        SparseArray sparseArray = (SparseArray) viewGroup.getTag(i3);
        if (sparseArray == null) {
            sparseArray = new SparseArray();
            viewGroup.setTag(i3, sparseArray);
        }
        Scene scene = (Scene) sparseArray.get(i2);
        if (scene != null) {
            return scene;
        }
        Scene scene2 = new Scene(viewGroup, i2, context);
        sparseArray.put(i2, scene2);
        return scene2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return this.b > 0;
    }

    public void enter() {
        if (this.b > 0 || this.f1056d != null) {
            getSceneRoot().removeAllViews();
            if (this.b > 0) {
                LayoutInflater.from(this.a).inflate(this.b, this.f1055c);
            } else {
                this.f1055c.addView(this.f1056d);
            }
        }
        Runnable runnable = this.e;
        if (runnable != null) {
            runnable.run();
        }
        b(this.f1055c, this);
    }

    public void exit() {
        Runnable runnable;
        if (getCurrentScene(this.f1055c) != this || (runnable = this.f) == null) {
            return;
        }
        runnable.run();
    }

    @NonNull
    public ViewGroup getSceneRoot() {
        return this.f1055c;
    }

    public void setEnterAction(@Nullable Runnable runnable) {
        this.e = runnable;
    }

    public void setExitAction(@Nullable Runnable runnable) {
        this.f = runnable;
    }
}
