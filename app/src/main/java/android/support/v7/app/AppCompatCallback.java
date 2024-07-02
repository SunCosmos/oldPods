package android.support.v7.app;

import android.support.annotation.Nullable;
import android.support.v7.view.ActionMode;

/* loaded from: lib/Mus.dex */
public interface AppCompatCallback {
    void onSupportActionModeFinished(ActionMode actionMode);

    void onSupportActionModeStarted(ActionMode actionMode);

    @Nullable
    ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback);
}
