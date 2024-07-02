package android.support.v4.widget;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: lib/Mus.dex */
public final class PopupWindowCompat {
    static final PopupWindowCompatBaseImpl IMPL;

    /* loaded from: lib/Mus.dex */
    static class PopupWindowCompatBaseImpl {
        private static Method sGetWindowLayoutTypeMethod;
        private static boolean sGetWindowLayoutTypeMethodAttempted;
        private static Method sSetWindowLayoutTypeMethod;
        private static boolean sSetWindowLayoutTypeMethodAttempted;

        PopupWindowCompatBaseImpl() {
        }

        public void showAsDropDown(PopupWindow popupWindow, View view, int i2, int i3, int i4) {
            int i5 = i2;
            if ((GravityCompat.getAbsoluteGravity(i4, ViewCompat.getLayoutDirection(view)) & 7) == 5) {
                i5 -= popupWindow.getWidth() - view.getWidth();
            }
            popupWindow.showAsDropDown(view, i5, i3);
        }

        public void setOverlapAnchor(PopupWindow popupWindow, boolean z) {
        }

        public boolean getOverlapAnchor(PopupWindow popupWindow) {
            return false;
        }

        public void setWindowLayoutType(PopupWindow popupWindow, int i2) {
            if (!sSetWindowLayoutTypeMethodAttempted) {
                try {
                    sSetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                    sSetWindowLayoutTypeMethod.setAccessible(true);
                } catch (Exception e) {
                }
                sSetWindowLayoutTypeMethodAttempted = true;
            }
            if (sSetWindowLayoutTypeMethod != null) {
                try {
                    sSetWindowLayoutTypeMethod.invoke(popupWindow, Integer.valueOf(i2));
                } catch (Exception e2) {
                }
            }
        }

        public int getWindowLayoutType(PopupWindow popupWindow) {
            if (!sGetWindowLayoutTypeMethodAttempted) {
                try {
                    sGetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("getWindowLayoutType", new Class[0]);
                    sGetWindowLayoutTypeMethod.setAccessible(true);
                } catch (Exception e) {
                }
                sGetWindowLayoutTypeMethodAttempted = true;
            }
            if (sGetWindowLayoutTypeMethod != null) {
                try {
                    return ((Integer) sGetWindowLayoutTypeMethod.invoke(popupWindow, new Object[0])).intValue();
                } catch (Exception e2) {
                }
            }
            return 0;
        }
    }

    @RequiresApi(19)
    /* loaded from: lib/Mus.dex */
    static class PopupWindowCompatApi19Impl extends PopupWindowCompatBaseImpl {
        PopupWindowCompatApi19Impl() {
        }

        @Override // android.support.v4.widget.PopupWindowCompat.PopupWindowCompatBaseImpl
        public void showAsDropDown(PopupWindow popupWindow, View view, int i2, int i3, int i4) {
            popupWindow.showAsDropDown(view, i2, i3, i4);
        }
    }

    @RequiresApi(21)
    /* loaded from: lib/Mus.dex */
    static class PopupWindowCompatApi21Impl extends PopupWindowCompatApi19Impl {
        private static final String TAG = "PopupWindowCompatApi21";
        private static Field sOverlapAnchorField;

        PopupWindowCompatApi21Impl() {
        }

        static {
            try {
                sOverlapAnchorField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                sOverlapAnchorField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.i(TAG, "Could not fetch mOverlapAnchor field from PopupWindow", e);
            }
        }

        @Override // android.support.v4.widget.PopupWindowCompat.PopupWindowCompatBaseImpl
        public void setOverlapAnchor(PopupWindow popupWindow, boolean z) {
            if (sOverlapAnchorField != null) {
                try {
                    sOverlapAnchorField.set(popupWindow, Boolean.valueOf(z));
                } catch (IllegalAccessException e) {
                    Log.i(TAG, "Could not set overlap anchor field in PopupWindow", e);
                }
            }
        }

        @Override // android.support.v4.widget.PopupWindowCompat.PopupWindowCompatBaseImpl
        public boolean getOverlapAnchor(PopupWindow popupWindow) {
            if (sOverlapAnchorField != null) {
                try {
                    return ((Boolean) sOverlapAnchorField.get(popupWindow)).booleanValue();
                } catch (IllegalAccessException e) {
                    Log.i(TAG, "Could not get overlap anchor field in PopupWindow", e);
                }
            }
            return false;
        }
    }

    @RequiresApi(23)
    /* loaded from: lib/Mus.dex */
    static class PopupWindowCompatApi23Impl extends PopupWindowCompatApi21Impl {
        PopupWindowCompatApi23Impl() {
        }

        @Override // android.support.v4.widget.PopupWindowCompat.PopupWindowCompatApi21Impl, android.support.v4.widget.PopupWindowCompat.PopupWindowCompatBaseImpl
        public void setOverlapAnchor(PopupWindow popupWindow, boolean z) {
            popupWindow.setOverlapAnchor(z);
        }

        @Override // android.support.v4.widget.PopupWindowCompat.PopupWindowCompatApi21Impl, android.support.v4.widget.PopupWindowCompat.PopupWindowCompatBaseImpl
        public boolean getOverlapAnchor(PopupWindow popupWindow) {
            return popupWindow.getOverlapAnchor();
        }

        @Override // android.support.v4.widget.PopupWindowCompat.PopupWindowCompatBaseImpl
        public void setWindowLayoutType(PopupWindow popupWindow, int i2) {
            popupWindow.setWindowLayoutType(i2);
        }

        @Override // android.support.v4.widget.PopupWindowCompat.PopupWindowCompatBaseImpl
        public int getWindowLayoutType(PopupWindow popupWindow) {
            return popupWindow.getWindowLayoutType();
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 23) {
            IMPL = new PopupWindowCompatApi23Impl();
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            IMPL = new PopupWindowCompatApi21Impl();
        } else if (Build.VERSION.SDK_INT >= 19) {
            IMPL = new PopupWindowCompatApi19Impl();
        } else {
            IMPL = new PopupWindowCompatBaseImpl();
        }
    }

    private PopupWindowCompat() {
    }

    public static void showAsDropDown(@NonNull PopupWindow popupWindow, @NonNull View view, int i2, int i3, int i4) {
        IMPL.showAsDropDown(popupWindow, view, i2, i3, i4);
    }

    public static void setOverlapAnchor(@NonNull PopupWindow popupWindow, boolean z) {
        IMPL.setOverlapAnchor(popupWindow, z);
    }

    public static boolean getOverlapAnchor(@NonNull PopupWindow popupWindow) {
        return IMPL.getOverlapAnchor(popupWindow);
    }

    public static void setWindowLayoutType(@NonNull PopupWindow popupWindow, int i2) {
        IMPL.setWindowLayoutType(popupWindow, i2);
    }

    public static int getWindowLayoutType(@NonNull PopupWindow popupWindow) {
        return IMPL.getWindowLayoutType(popupWindow);
    }
}
