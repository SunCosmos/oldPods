package cn.hugo.android.scanner.f;

import android.content.SharedPreferences;

/* loaded from: classes.dex */
public enum d {
    ON,
    AUTO,
    OFF;

    private static d a(String str) {
        return str == null ? OFF : valueOf(str);
    }

    public static d b(SharedPreferences sharedPreferences) {
        return a(sharedPreferences.getString("preferences_front_light_mode", null));
    }
}
