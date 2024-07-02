package com.iapp.app.run;

import android.content.Intent;
import android.os.Bundle;

/* loaded from: classes.dex */
public class load extends iActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iapp.app.run.iActivity
    public void d() {
        super.d();
        Intent intent = new Intent(this, (Class<?>) mian.class);
        Bundle bundle = new Bundle();
        bundle.putString("OpenFilexmlui", "mian.iyu");
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iapp.app.run.iActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
}
