package androidx.transition;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
/* loaded from: classes.dex */
class f implements d {
    private static Class<?> b;

    /* renamed from: c, reason: collision with root package name */
    private static boolean f1075c;

    /* renamed from: d, reason: collision with root package name */
    private static Method f1076d;
    private static boolean e;
    private static Method f;
    private static boolean g;
    private final View a;

    private f(@NonNull View view) {
        this.a = view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d b(View view, ViewGroup viewGroup, Matrix matrix) {
        c();
        Method method = f1076d;
        if (method != null) {
            try {
                return new f((View) method.invoke(null, view, viewGroup, matrix));
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
        return null;
    }

    private static void c() {
        if (e) {
            return;
        }
        try {
            d();
            Method declaredMethod = b.getDeclaredMethod("addGhost", View.class, ViewGroup.class, Matrix.class);
            f1076d = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e2) {
            Log.i("GhostViewApi21", "Failed to retrieve addGhost method", e2);
        }
        e = true;
    }

    private static void d() {
        if (f1075c) {
            return;
        }
        try {
            b = Class.forName("android.view.GhostView");
        } catch (ClassNotFoundException e2) {
            Log.i("GhostViewApi21", "Failed to retrieve GhostView class", e2);
        }
        f1075c = true;
    }

    private static void e() {
        if (g) {
            return;
        }
        try {
            d();
            Method declaredMethod = b.getDeclaredMethod("removeGhost", View.class);
            f = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e2) {
            Log.i("GhostViewApi21", "Failed to retrieve removeGhost method", e2);
        }
        g = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(View view) {
        e();
        Method method = f;
        if (method != null) {
            try {
                method.invoke(null, view);
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    @Override // androidx.transition.d
    public void a(ViewGroup viewGroup, View view) {
    }

    @Override // androidx.transition.d
    public void setVisibility(int i2) {
        this.a.setVisibility(i2);
    }
}
