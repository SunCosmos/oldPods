package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.transition.GhostViewImpl;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
/* loaded from: lib/Mus.dex */
class GhostViewApi21 implements GhostViewImpl {
    private static final String TAG = "GhostViewApi21";
    private static Method sAddGhostMethod;
    private static boolean sAddGhostMethodFetched;
    private static Class<?> sGhostViewClass;
    private static boolean sGhostViewClassFetched;
    private static Method sRemoveGhostMethod;
    private static boolean sRemoveGhostMethodFetched;
    private final View mGhostView;

    /* loaded from: lib/Mus.dex */
    static class Creator implements GhostViewImpl.Creator {
        @Override // android.support.transition.GhostViewImpl.Creator
        public GhostViewImpl addGhost(View view, ViewGroup viewGroup, Matrix matrix) {
            GhostViewApi21.fetchAddGhostMethod();
            if (GhostViewApi21.sAddGhostMethod != null) {
                try {
                    return new GhostViewApi21((View) GhostViewApi21.sAddGhostMethod.invoke(null, view, viewGroup, matrix));
                } catch (IllegalAccessException e) {
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
            return null;
        }

        @Override // android.support.transition.GhostViewImpl.Creator
        public void removeGhost(View view) {
            GhostViewApi21.fetchRemoveGhostMethod();
            if (GhostViewApi21.sRemoveGhostMethod != null) {
                try {
                    GhostViewApi21.sRemoveGhostMethod.invoke(null, view);
                } catch (IllegalAccessException e) {
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2.getCause());
                }
            }
        }
    }

    private GhostViewApi21(@NonNull View view) {
        this.mGhostView = view;
    }

    @Override // android.support.transition.GhostViewImpl
    public void setVisibility(int i2) {
        this.mGhostView.setVisibility(i2);
    }

    @Override // android.support.transition.GhostViewImpl
    public void reserveEndViewTransition(ViewGroup viewGroup, View view) {
    }

    private static void fetchGhostViewClass() {
        if (!sGhostViewClassFetched) {
            try {
                sGhostViewClass = Class.forName("android.view.GhostView");
            } catch (ClassNotFoundException e) {
                Log.i(TAG, "Failed to retrieve GhostView class", e);
            }
            sGhostViewClassFetched = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fetchAddGhostMethod() {
        if (!sAddGhostMethodFetched) {
            try {
                fetchGhostViewClass();
                sAddGhostMethod = sGhostViewClass.getDeclaredMethod("addGhost", View.class, ViewGroup.class, Matrix.class);
                sAddGhostMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.i(TAG, "Failed to retrieve addGhost method", e);
            }
            sAddGhostMethodFetched = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fetchRemoveGhostMethod() {
        if (!sRemoveGhostMethodFetched) {
            try {
                fetchGhostViewClass();
                sRemoveGhostMethod = sGhostViewClass.getDeclaredMethod("removeGhost", View.class);
                sRemoveGhostMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.i(TAG, "Failed to retrieve removeGhost method", e);
            }
            sRemoveGhostMethodFetched = true;
        }
    }
}
