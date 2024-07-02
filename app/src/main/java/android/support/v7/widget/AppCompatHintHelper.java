package android.support.v7.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/* loaded from: lib/Mus.dex */
class AppCompatHintHelper {
    AppCompatHintHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InputConnection onCreateInputConnection(InputConnection inputConnection, EditorInfo editorInfo, View view) {
        if (inputConnection != null && editorInfo.hintText == null) {
            ViewParent parent = view.getParent();
            while (true) {
                ViewParent viewParent = parent;
                if (!(viewParent instanceof View)) {
                    break;
                }
                if (viewParent instanceof WithHint) {
                    editorInfo.hintText = ((WithHint) viewParent).getHint();
                    break;
                }
                parent = viewParent.getParent();
            }
        }
        return inputConnection;
    }
}
