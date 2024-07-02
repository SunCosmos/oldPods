package com.iapp.app.v;

import android.content.Context;
import android.widget.VideoView;

/* loaded from: classes.dex */
public class a extends VideoView {
    public boolean a;

    public a(Context context) {
        super(context);
        this.a = true;
    }

    @Override // android.widget.VideoView, android.view.SurfaceView, android.view.View
    protected void onMeasure(int i2, int i3) {
        if (this.a) {
            setMeasuredDimension(VideoView.getDefaultSize(0, i2), VideoView.getDefaultSize(0, i3));
        } else {
            super.onMeasure(i2, i3);
        }
    }
}
