package com.iapp.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import java.io.File;

/* loaded from: classes.dex */
public class Videoview extends Activity {
    private String a = null;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        getWindow().clearFlags(2048);
        getWindow().setFlags(1024, 1024);
        getWindow().addFlags(128);
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        String string = extras.getString("video");
        this.a = string;
        if (string == null) {
            finish();
        }
        if (!c.b.a.a.p.v(this.a.toLowerCase())) {
            this.a = c.b.a.a.d.p(this, this.a);
            if (!new File(this.a).exists()) {
                Toast.makeText(this, "视频文件不存在!", 1).show();
                finish();
            }
        }
        if (extras.getBoolean("sfhp")) {
            if (getRequestedOrientation() != 0) {
                setRequestedOrientation(0);
            }
        } else if (getRequestedOrientation() != 1) {
            setRequestedOrientation(1);
        }
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        linearLayout.setGravity(17);
        linearLayout.setBackgroundColor(Color.parseColor("#000000"));
        com.iapp.app.v.a aVar = new com.iapp.app.v.a(this);
        aVar.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        linearLayout.addView(aVar);
        setContentView(linearLayout);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(aVar);
        aVar.setMediaController(mediaController);
        aVar.setVideoURI(c.b.a.a.k.c(this, this.a));
        aVar.start();
        aVar.requestFocus();
    }
}
