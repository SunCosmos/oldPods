package android.support.v4.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.v4.os.BuildCompat;
import android.text.Editable;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: lib/Mus.dex */
public final class TextViewCompat {
    public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
    public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
    static final TextViewCompatBaseImpl IMPL;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public @interface AutoSizeTextType {
    }

    private TextViewCompat() {
    }

    /* loaded from: lib/Mus.dex */
    static class TextViewCompatBaseImpl {
        private static final int LINES = 1;
        private static final String LOG_TAG = "TextViewCompatBase";
        private static Field sMaxModeField;
        private static boolean sMaxModeFieldFetched;
        private static Field sMaximumField;
        private static boolean sMaximumFieldFetched;
        private static Field sMinModeField;
        private static boolean sMinModeFieldFetched;
        private static Field sMinimumField;
        private static boolean sMinimumFieldFetched;

        TextViewCompatBaseImpl() {
        }

        public void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
            textView.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4, @DrawableRes int i5) {
            textView.setCompoundDrawablesWithIntrinsicBounds(i2, i3, i4, i5);
        }

        private static Field retrieveField(String str) {
            Field field = null;
            try {
                field = TextView.class.getDeclaredField(str);
                field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e(LOG_TAG, "Could not retrieve " + str + " field.");
            }
            return field;
        }

        private static int retrieveIntFromField(Field field, TextView textView) {
            try {
                return field.getInt(textView);
            } catch (IllegalAccessException e) {
                Log.d(LOG_TAG, "Could not retrieve value of " + field.getName() + " field.");
                return -1;
            }
        }

        public int getMaxLines(TextView textView) {
            if (!sMaxModeFieldFetched) {
                sMaxModeField = retrieveField("mMaxMode");
                sMaxModeFieldFetched = true;
            }
            if (sMaxModeField != null && retrieveIntFromField(sMaxModeField, textView) == 1) {
                if (!sMaximumFieldFetched) {
                    sMaximumField = retrieveField("mMaximum");
                    sMaximumFieldFetched = true;
                }
                if (sMaximumField != null) {
                    return retrieveIntFromField(sMaximumField, textView);
                }
            }
            return -1;
        }

        public int getMinLines(TextView textView) {
            if (!sMinModeFieldFetched) {
                sMinModeField = retrieveField("mMinMode");
                sMinModeFieldFetched = true;
            }
            if (sMinModeField != null && retrieveIntFromField(sMinModeField, textView) == 1) {
                if (!sMinimumFieldFetched) {
                    sMinimumField = retrieveField("mMinimum");
                    sMinimumFieldFetched = true;
                }
                if (sMinimumField != null) {
                    return retrieveIntFromField(sMinimumField, textView);
                }
            }
            return -1;
        }

        public void setTextAppearance(TextView textView, @StyleRes int i2) {
            textView.setTextAppearance(textView.getContext(), i2);
        }

        public Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
            return textView.getCompoundDrawables();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setAutoSizeTextTypeWithDefaults(TextView textView, int i2) {
            if (textView instanceof AutoSizeableTextView) {
                ((AutoSizeableTextView) textView).setAutoSizeTextTypeWithDefaults(i2);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setAutoSizeTextTypeUniformWithConfiguration(TextView textView, int i2, int i3, int i4, int i5) throws IllegalArgumentException {
            if (textView instanceof AutoSizeableTextView) {
                ((AutoSizeableTextView) textView).setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setAutoSizeTextTypeUniformWithPresetSizes(TextView textView, @NonNull int[] iArr, int i2) throws IllegalArgumentException {
            if (textView instanceof AutoSizeableTextView) {
                ((AutoSizeableTextView) textView).setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int getAutoSizeTextType(TextView textView) {
            if (textView instanceof AutoSizeableTextView) {
                return ((AutoSizeableTextView) textView).getAutoSizeTextType();
            }
            return 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int getAutoSizeStepGranularity(TextView textView) {
            if (textView instanceof AutoSizeableTextView) {
                return ((AutoSizeableTextView) textView).getAutoSizeStepGranularity();
            }
            return -1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int getAutoSizeMinTextSize(TextView textView) {
            if (textView instanceof AutoSizeableTextView) {
                return ((AutoSizeableTextView) textView).getAutoSizeMinTextSize();
            }
            return -1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int getAutoSizeMaxTextSize(TextView textView) {
            if (textView instanceof AutoSizeableTextView) {
                return ((AutoSizeableTextView) textView).getAutoSizeMaxTextSize();
            }
            return -1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int[] getAutoSizeTextAvailableSizes(TextView textView) {
            return textView instanceof AutoSizeableTextView ? ((AutoSizeableTextView) textView).getAutoSizeTextAvailableSizes() : new int[0];
        }

        public void setCustomSelectionActionModeCallback(TextView textView, ActionMode.Callback callback) {
            textView.setCustomSelectionActionModeCallback(callback);
        }
    }

    @RequiresApi(16)
    /* loaded from: lib/Mus.dex */
    static class TextViewCompatApi16Impl extends TextViewCompatBaseImpl {
        TextViewCompatApi16Impl() {
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public int getMaxLines(TextView textView) {
            return textView.getMaxLines();
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public int getMinLines(TextView textView) {
            return textView.getMinLines();
        }
    }

    @RequiresApi(17)
    /* loaded from: lib/Mus.dex */
    static class TextViewCompatApi17Impl extends TextViewCompatApi16Impl {
        TextViewCompatApi17Impl() {
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
            boolean z = textView.getLayoutDirection() == 1;
            textView.setCompoundDrawables(z ? drawable3 : drawable, drawable2, z ? drawable : drawable3, drawable4);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
            boolean z = textView.getLayoutDirection() == 1;
            textView.setCompoundDrawablesWithIntrinsicBounds(z ? drawable3 : drawable, drawable2, z ? drawable : drawable3, drawable4);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4, @DrawableRes int i5) {
            boolean z = textView.getLayoutDirection() == 1;
            textView.setCompoundDrawablesWithIntrinsicBounds(z ? i4 : i2, i3, z ? i2 : i4, i5);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
            boolean z = textView.getLayoutDirection() == 1;
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            if (z) {
                Drawable drawable = compoundDrawables[2];
                Drawable drawable2 = compoundDrawables[0];
                compoundDrawables[0] = drawable;
                compoundDrawables[2] = drawable2;
            }
            return compoundDrawables;
        }
    }

    @RequiresApi(18)
    /* loaded from: lib/Mus.dex */
    static class TextViewCompatApi18Impl extends TextViewCompatApi17Impl {
        TextViewCompatApi18Impl() {
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatApi17Impl, android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
            textView.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatApi17Impl, android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatApi17Impl, android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4, @DrawableRes int i5) {
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(i2, i3, i4, i5);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatApi17Impl, android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
            return textView.getCompoundDrawablesRelative();
        }
    }

    @RequiresApi(23)
    /* loaded from: lib/Mus.dex */
    static class TextViewCompatApi23Impl extends TextViewCompatApi18Impl {
        TextViewCompatApi23Impl() {
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public void setTextAppearance(@NonNull TextView textView, @StyleRes int i2) {
            textView.setTextAppearance(i2);
        }
    }

    @RequiresApi(26)
    /* loaded from: lib/Mus.dex */
    static class TextViewCompatApi26Impl extends TextViewCompatApi23Impl {
        TextViewCompatApi26Impl() {
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public void setCustomSelectionActionModeCallback(final TextView textView, final ActionMode.Callback callback) {
            if (Build.VERSION.SDK_INT != 26 && Build.VERSION.SDK_INT != 27) {
                super.setCustomSelectionActionModeCallback(textView, callback);
            } else {
                textView.setCustomSelectionActionModeCallback(new ActionMode.Callback() { // from class: android.support.v4.widget.TextViewCompat.TextViewCompatApi26Impl.1
                    private static final int MENU_ITEM_ORDER_PROCESS_TEXT_INTENT_ACTIONS_START = 100;
                    private boolean mCanUseMenuBuilderReferences;
                    private boolean mInitializedMenuBuilderReferences = false;
                    private Class mMenuBuilderClass;
                    private Method mMenuBuilderRemoveItemAtMethod;

                    @Override // android.view.ActionMode.Callback
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        return callback.onCreateActionMode(actionMode, menu);
                    }

                    @Override // android.view.ActionMode.Callback
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        recomputeProcessTextMenuItems(menu);
                        return callback.onPrepareActionMode(actionMode, menu);
                    }

                    @Override // android.view.ActionMode.Callback
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        return callback.onActionItemClicked(actionMode, menuItem);
                    }

                    @Override // android.view.ActionMode.Callback
                    public void onDestroyActionMode(ActionMode actionMode) {
                        callback.onDestroyActionMode(actionMode);
                    }

                    private void recomputeProcessTextMenuItems(Menu menu) {
                        Method declaredMethod;
                        Context context = textView.getContext();
                        PackageManager packageManager = context.getPackageManager();
                        if (!this.mInitializedMenuBuilderReferences) {
                            this.mInitializedMenuBuilderReferences = true;
                            try {
                                this.mMenuBuilderClass = Class.forName("com.android.internal.view.menu.MenuBuilder");
                                this.mMenuBuilderRemoveItemAtMethod = this.mMenuBuilderClass.getDeclaredMethod("removeItemAt", Integer.TYPE);
                                this.mCanUseMenuBuilderReferences = true;
                            } catch (ClassNotFoundException | NoSuchMethodException e) {
                                this.mMenuBuilderClass = null;
                                this.mMenuBuilderRemoveItemAtMethod = null;
                                this.mCanUseMenuBuilderReferences = false;
                            }
                        }
                        try {
                            if (this.mCanUseMenuBuilderReferences && this.mMenuBuilderClass.isInstance(menu)) {
                                declaredMethod = this.mMenuBuilderRemoveItemAtMethod;
                            } else {
                                declaredMethod = menu.getClass().getDeclaredMethod("removeItemAt", Integer.TYPE);
                            }
                            Method method = declaredMethod;
                            for (int size = menu.size() - 1; size >= 0; size--) {
                                MenuItem item = menu.getItem(size);
                                if (item.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(item.getIntent().getAction())) {
                                    method.invoke(menu, Integer.valueOf(size));
                                }
                            }
                            List<ResolveInfo> supportedActivities = getSupportedActivities(context, packageManager);
                            for (int i2 = 0; i2 < supportedActivities.size(); i2++) {
                                ResolveInfo resolveInfo = supportedActivities.get(i2);
                                menu.add(0, 0, 100 + i2, resolveInfo.loadLabel(packageManager)).setIntent(createProcessTextIntentForResolveInfo(resolveInfo, textView)).setShowAsAction(1);
                            }
                        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                        }
                    }

                    private List<ResolveInfo> getSupportedActivities(Context context, PackageManager packageManager) {
                        ArrayList arrayList = new ArrayList();
                        if (!(context instanceof Activity)) {
                            return arrayList;
                        }
                        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(createProcessTextIntent(), 0)) {
                            if (isSupportedActivity(resolveInfo, context)) {
                                arrayList.add(resolveInfo);
                            }
                        }
                        return arrayList;
                    }

                    private boolean isSupportedActivity(ResolveInfo resolveInfo, Context context) {
                        if (context.getPackageName().equals(resolveInfo.activityInfo.packageName)) {
                            return true;
                        }
                        if (!resolveInfo.activityInfo.exported) {
                            return false;
                        }
                        return resolveInfo.activityInfo.permission == null || context.checkSelfPermission(resolveInfo.activityInfo.permission) == 0;
                    }

                    private Intent createProcessTextIntentForResolveInfo(ResolveInfo resolveInfo, TextView textView2) {
                        return createProcessTextIntent().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !isEditable(textView2)).setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                    }

                    private boolean isEditable(TextView textView2) {
                        return (textView2 instanceof Editable) && textView2.onCheckIsTextEditor() && textView2.isEnabled();
                    }

                    private Intent createProcessTextIntent() {
                        return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
                    }
                });
            }
        }
    }

    @RequiresApi(27)
    /* loaded from: lib/Mus.dex */
    static class TextViewCompatApi27Impl extends TextViewCompatApi26Impl {
        TextViewCompatApi27Impl() {
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public void setAutoSizeTextTypeWithDefaults(TextView textView, int i2) {
            textView.setAutoSizeTextTypeWithDefaults(i2);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public void setAutoSizeTextTypeUniformWithConfiguration(TextView textView, int i2, int i3, int i4, int i5) throws IllegalArgumentException {
            textView.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public void setAutoSizeTextTypeUniformWithPresetSizes(TextView textView, @NonNull int[] iArr, int i2) throws IllegalArgumentException {
            textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public int getAutoSizeTextType(TextView textView) {
            return textView.getAutoSizeTextType();
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public int getAutoSizeStepGranularity(TextView textView) {
            return textView.getAutoSizeStepGranularity();
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public int getAutoSizeMinTextSize(TextView textView) {
            return textView.getAutoSizeMinTextSize();
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public int getAutoSizeMaxTextSize(TextView textView) {
            return textView.getAutoSizeMaxTextSize();
        }

        @Override // android.support.v4.widget.TextViewCompat.TextViewCompatBaseImpl
        public int[] getAutoSizeTextAvailableSizes(TextView textView) {
            return textView.getAutoSizeTextAvailableSizes();
        }
    }

    static {
        if (BuildCompat.isAtLeastOMR1()) {
            IMPL = new TextViewCompatApi27Impl();
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            IMPL = new TextViewCompatApi26Impl();
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            IMPL = new TextViewCompatApi23Impl();
            return;
        }
        if (Build.VERSION.SDK_INT >= 18) {
            IMPL = new TextViewCompatApi18Impl();
            return;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            IMPL = new TextViewCompatApi17Impl();
        } else if (Build.VERSION.SDK_INT >= 16) {
            IMPL = new TextViewCompatApi16Impl();
        } else {
            IMPL = new TextViewCompatBaseImpl();
        }
    }

    public static void setCompoundDrawablesRelative(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        IMPL.setCompoundDrawablesRelative(textView, drawable, drawable2, drawable3, drawable4);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @Nullable Drawable drawable, @Nullable Drawable drawable2, @Nullable Drawable drawable3, @Nullable Drawable drawable4) {
        IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, drawable, drawable2, drawable3, drawable4);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView, @DrawableRes int i2, @DrawableRes int i3, @DrawableRes int i4, @DrawableRes int i5) {
        IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, i2, i3, i4, i5);
    }

    public static int getMaxLines(@NonNull TextView textView) {
        return IMPL.getMaxLines(textView);
    }

    public static int getMinLines(@NonNull TextView textView) {
        return IMPL.getMinLines(textView);
    }

    public static void setTextAppearance(@NonNull TextView textView, @StyleRes int i2) {
        IMPL.setTextAppearance(textView, i2);
    }

    @NonNull
    public static Drawable[] getCompoundDrawablesRelative(@NonNull TextView textView) {
        return IMPL.getCompoundDrawablesRelative(textView);
    }

    public static void setAutoSizeTextTypeWithDefaults(@NonNull TextView textView, int i2) {
        IMPL.setAutoSizeTextTypeWithDefaults(textView, i2);
    }

    public static void setAutoSizeTextTypeUniformWithConfiguration(@NonNull TextView textView, int i2, int i3, int i4, int i5) throws IllegalArgumentException {
        IMPL.setAutoSizeTextTypeUniformWithConfiguration(textView, i2, i3, i4, i5);
    }

    public static void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull TextView textView, @NonNull int[] iArr, int i2) throws IllegalArgumentException {
        IMPL.setAutoSizeTextTypeUniformWithPresetSizes(textView, iArr, i2);
    }

    public static int getAutoSizeTextType(@NonNull TextView textView) {
        return IMPL.getAutoSizeTextType(textView);
    }

    public static int getAutoSizeStepGranularity(@NonNull TextView textView) {
        return IMPL.getAutoSizeStepGranularity(textView);
    }

    public static int getAutoSizeMinTextSize(@NonNull TextView textView) {
        return IMPL.getAutoSizeMinTextSize(textView);
    }

    public static int getAutoSizeMaxTextSize(@NonNull TextView textView) {
        return IMPL.getAutoSizeMaxTextSize(textView);
    }

    @NonNull
    public static int[] getAutoSizeTextAvailableSizes(@NonNull TextView textView) {
        return IMPL.getAutoSizeTextAvailableSizes(textView);
    }

    public static void setCustomSelectionActionModeCallback(@NonNull TextView textView, @NonNull ActionMode.Callback callback) {
        IMPL.setCustomSelectionActionModeCallback(textView, callback);
    }
}
