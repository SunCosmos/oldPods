package com.google.android.material.resources;

@Deprecated
/* loaded from: classes.dex */
public class TextAppearanceConfig {
    private static boolean a;

    public static void setShouldLoadFontSynchronously(boolean z) {
        a = z;
    }

    public static boolean shouldLoadFontSynchronously() {
        return a;
    }
}
