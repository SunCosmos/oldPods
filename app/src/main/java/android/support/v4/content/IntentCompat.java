package android.support.v4.content;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;

/* loaded from: lib/Mus.dex */
public final class IntentCompat {
    public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final String EXTRA_START_PLAYBACK = "android.intent.extra.START_PLAYBACK";

    private IntentCompat() {
    }

    @NonNull
    public static Intent makeMainSelectorActivity(@NonNull String str, @NonNull String str2) {
        if (Build.VERSION.SDK_INT < 15) {
            Intent intent = new Intent(str);
            intent.addCategory(str2);
            return intent;
        }
        return Intent.makeMainSelectorActivity(str, str2);
    }
}
