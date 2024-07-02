package c.c.a;

import android.app.Activity;
import com.oldpods.app.R;

/* loaded from: classes.dex */
public class a {
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0003. Please report as an issue. */
    public static void a(Activity activity, int i2) {
        int i3;
        int i4 = R.anim.my_alpha_action;
        switch (i2) {
            case 0:
                i3 = R.anim.fade;
                i4 = R.anim.hold;
                activity.overridePendingTransition(i3, i4);
                return;
            case 1:
                i3 = R.anim.my_scale_action;
                activity.overridePendingTransition(i3, i4);
                return;
            case 2:
                i3 = R.anim.scale_rotate;
                activity.overridePendingTransition(i3, i4);
                return;
            case 3:
                i3 = R.anim.scale_translate_rotate;
                activity.overridePendingTransition(i3, i4);
                return;
            case 4:
                i3 = R.anim.scale_translate;
                activity.overridePendingTransition(i3, i4);
                return;
            case 5:
                i3 = R.anim.hyperspace_in;
                i4 = R.anim.hyperspace_out;
                activity.overridePendingTransition(i3, i4);
                return;
            case 6:
                i3 = R.anim.push_left_in;
                i4 = R.anim.push_left_out;
                activity.overridePendingTransition(i3, i4);
                return;
            case 7:
                i3 = R.anim.push_up_in;
                i4 = R.anim.push_up_out;
                activity.overridePendingTransition(i3, i4);
                return;
            case 8:
                i3 = R.anim.slide_left;
                i4 = R.anim.slide_right;
                activity.overridePendingTransition(i3, i4);
                return;
            case 9:
                i3 = R.anim.wave_scale;
                activity.overridePendingTransition(i3, i4);
                return;
            case 10:
                i3 = R.anim.zoom_enter;
                i4 = R.anim.zoom_exit;
                activity.overridePendingTransition(i3, i4);
                return;
            case 11:
                i3 = R.anim.slide_up_in;
                i4 = R.anim.slide_down_out;
                activity.overridePendingTransition(i3, i4);
                return;
            case 12:
                i3 = R.anim.push_left_in2;
                i4 = R.anim.push_left_out2;
                activity.overridePendingTransition(i3, i4);
                return;
            default:
                return;
        }
    }
}
