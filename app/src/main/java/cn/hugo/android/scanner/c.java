package cn.hugo.android.scanner;

import android.app.Activity;
import android.content.DialogInterface;

/* loaded from: classes.dex */
public final class c implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener {
    private final Activity a;

    public c(Activity activity) {
        this.a = activity;
    }

    private void a() {
        this.a.finish();
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        a();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i2) {
        a();
    }
}
