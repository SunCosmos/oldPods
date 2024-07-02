package androidx.appcompat.app;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.app.ActionBar;

/* loaded from: classes.dex */
class c implements AdapterView.OnItemSelectedListener {
    private final ActionBar.OnNavigationListener a;

    public c(ActionBar.OnNavigationListener onNavigationListener) {
        this.a = onNavigationListener;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
        ActionBar.OnNavigationListener onNavigationListener = this.a;
        if (onNavigationListener != null) {
            onNavigationListener.onNavigationItemSelected(i2, j);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
