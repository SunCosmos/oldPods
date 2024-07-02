package com.google.android.material.transition.platform;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcelable;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

@RequiresApi(21)
/* loaded from: classes.dex */
public class MaterialContainerTransformSharedElementCallback extends SharedElementCallback {

    @Nullable
    private static WeakReference<View> f;

    /* renamed from: d, reason: collision with root package name */
    @Nullable
    private Rect f1797d;
    private boolean a = true;
    private boolean b = true;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1796c = false;

    @Nullable
    private ShapeProvider e = new ShapeableViewShapeProvider();

    /* loaded from: classes.dex */
    public interface ShapeProvider {
        @Nullable
        ShapeAppearanceModel provideShape(@NonNull View view);
    }

    /* loaded from: classes.dex */
    public static class ShapeableViewShapeProvider implements ShapeProvider {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback.ShapeProvider
        @Nullable
        public ShapeAppearanceModel provideShape(@NonNull View view) {
            if (view instanceof Shapeable) {
                return ((Shapeable) view).getShapeAppearanceModel();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends i {
        final /* synthetic */ Window a;

        a(MaterialContainerTransformSharedElementCallback materialContainerTransformSharedElementCallback, Window window) {
            this.a = window;
        }

        @Override // com.google.android.material.transition.platform.i, android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition transition) {
            MaterialContainerTransformSharedElementCallback.f(this.a);
        }

        @Override // com.google.android.material.transition.platform.i, android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition transition) {
            MaterialContainerTransformSharedElementCallback.e(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends i {
        final /* synthetic */ Activity a;

        b(MaterialContainerTransformSharedElementCallback materialContainerTransformSharedElementCallback, Activity activity) {
            this.a = activity;
        }

        @Override // com.google.android.material.transition.platform.i, android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition transition) {
            View view;
            if (MaterialContainerTransformSharedElementCallback.f != null && (view = (View) MaterialContainerTransformSharedElementCallback.f.get()) != null) {
                view.setAlpha(1.0f);
                WeakReference unused = MaterialContainerTransformSharedElementCallback.f = null;
            }
            this.a.finish();
            this.a.overridePendingTransition(0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends i {
        final /* synthetic */ Window a;

        c(MaterialContainerTransformSharedElementCallback materialContainerTransformSharedElementCallback, Window window) {
            this.a = window;
        }

        @Override // com.google.android.material.transition.platform.i, android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition transition) {
            MaterialContainerTransformSharedElementCallback.e(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(Window window) {
        window.getDecorView().getBackground().mutate().setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(0, BlendModeCompat.CLEAR));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(Window window) {
        window.getDecorView().getBackground().mutate().clearColorFilter();
    }

    private void g(Window window) {
        Transition sharedElementEnterTransition = window.getSharedElementEnterTransition();
        if (sharedElementEnterTransition instanceof MaterialContainerTransform) {
            MaterialContainerTransform materialContainerTransform = (MaterialContainerTransform) sharedElementEnterTransition;
            if (!this.f1796c) {
                window.setSharedElementReenterTransition(null);
            }
            if (this.b) {
                i(window, materialContainerTransform);
                materialContainerTransform.addListener(new a(this, window));
            }
        }
    }

    private void h(Activity activity, Window window) {
        Transition sharedElementReturnTransition = window.getSharedElementReturnTransition();
        if (sharedElementReturnTransition instanceof MaterialContainerTransform) {
            MaterialContainerTransform materialContainerTransform = (MaterialContainerTransform) sharedElementReturnTransition;
            materialContainerTransform.setHoldAtEndEnabled(true);
            materialContainerTransform.addListener(new b(this, activity));
            if (this.b) {
                i(window, materialContainerTransform);
                materialContainerTransform.addListener(new c(this, window));
            }
        }
    }

    private static void i(Window window, MaterialContainerTransform materialContainerTransform) {
        if (materialContainerTransform.getDuration() >= 0) {
            window.setTransitionBackgroundFadeDuration(materialContainerTransform.getDuration());
        }
    }

    @Nullable
    public ShapeProvider getShapeProvider() {
        return this.e;
    }

    public boolean isSharedElementReenterTransitionEnabled() {
        return this.f1796c;
    }

    public boolean isTransparentWindowBackgroundEnabled() {
        return this.b;
    }

    @Override // android.app.SharedElementCallback
    @Nullable
    public Parcelable onCaptureSharedElementSnapshot(@NonNull View view, @NonNull Matrix matrix, @NonNull RectF rectF) {
        f = new WeakReference<>(view);
        return super.onCaptureSharedElementSnapshot(view, matrix, rectF);
    }

    @Override // android.app.SharedElementCallback
    @Nullable
    public View onCreateSnapshotView(@NonNull Context context, @Nullable Parcelable parcelable) {
        WeakReference<View> weakReference;
        View view;
        ShapeAppearanceModel provideShape;
        View onCreateSnapshotView = super.onCreateSnapshotView(context, parcelable);
        if (onCreateSnapshotView != null && (weakReference = f) != null && this.e != null && (view = weakReference.get()) != null && (provideShape = this.e.provideShape(view)) != null) {
            onCreateSnapshotView.setTag(R.id.mtrl_motion_snapshot_view, provideShape);
        }
        return onCreateSnapshotView;
    }

    @Override // android.app.SharedElementCallback
    public void onMapSharedElements(@NonNull List<String> list, @NonNull Map<String, View> map) {
        View view;
        Activity activity;
        if (list.isEmpty() || map.isEmpty() || (view = map.get(list.get(0))) == null || (activity = ContextUtils.getActivity(view.getContext())) == null) {
            return;
        }
        Window window = activity.getWindow();
        if (this.a) {
            g(window);
        } else {
            h(activity, window);
        }
    }

    @Override // android.app.SharedElementCallback
    public void onSharedElementEnd(@NonNull List<String> list, @NonNull List<View> list2, @NonNull List<View> list3) {
        if (!list2.isEmpty()) {
            View view = list2.get(0);
            int i2 = R.id.mtrl_motion_snapshot_view;
            if (view.getTag(i2) instanceof View) {
                list2.get(0).setTag(i2, null);
            }
        }
        if (!this.a && !list2.isEmpty()) {
            this.f1797d = j.k(list2.get(0));
        }
        this.a = false;
    }

    @Override // android.app.SharedElementCallback
    public void onSharedElementStart(@NonNull List<String> list, @NonNull List<View> list2, @NonNull List<View> list3) {
        if (!list2.isEmpty() && !list3.isEmpty()) {
            list2.get(0).setTag(R.id.mtrl_motion_snapshot_view, list3.get(0));
        }
        if (this.a || list2.isEmpty() || this.f1797d == null) {
            return;
        }
        View view = list2.get(0);
        view.measure(View.MeasureSpec.makeMeasureSpec(this.f1797d.width(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(this.f1797d.height(), BasicMeasure.EXACTLY));
        Rect rect = this.f1797d;
        view.layout(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void setShapeProvider(@Nullable ShapeProvider shapeProvider) {
        this.e = shapeProvider;
    }

    public void setSharedElementReenterTransitionEnabled(boolean z) {
        this.f1796c = z;
    }

    public void setTransparentWindowBackgroundEnabled(boolean z) {
        this.b = z;
    }
}
