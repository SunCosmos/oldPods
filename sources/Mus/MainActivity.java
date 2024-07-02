package Mus;

import adrt.ADRTLogCatReader;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/* loaded from: lib/Mus.dex */
public class MainActivity extends AppCompatActivity {
    static AnimatorSet animatorSetsuofang;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        ADRTLogCatReader.onContext(this, "com.aide.ui");
        super.onCreate(bundle);
        setContentView(2130968603);
        start((ImageButton) findViewById(2131361932), 1000, 1, 1.5f, 1);
    }

    public static AnimatorSet start(View view, int i2, int i3, float f, int i4) {
        animatorSetsuofang = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleX", i3, f, i4);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleY", i3, f, i4);
        ofFloat.setRepeatCount(-1);
        ofFloat2.setRepeatCount(-1);
        animatorSetsuofang.setDuration(i2);
        animatorSetsuofang.play(ofFloat).with(ofFloat2);
        animatorSetsuofang.start();
        return animatorSetsuofang;
    }

    public static void end() {
        animatorSetsuofang.end();
    }

    public static void start(AnimatorSet animatorSet) {
        animatorSet.start();
    }

    public static void wait(AnimatorSet animatorSet) {
        try {
            animatorSet.wait();
        } catch (InterruptedException e) {
        }
    }
}
