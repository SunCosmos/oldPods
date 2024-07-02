package cn.hugo.android.scanner.f;

import android.hardware.Camera;
import android.util.Log;

/* loaded from: classes.dex */
public final class e {
    private static final String a = "cn.hugo.android.scanner.f.e";

    public static Camera a() {
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras == 0) {
            Log.w(a, "No cameras!");
            return null;
        }
        int i2 = 0;
        while (i2 < numberOfCameras) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == 0) {
                break;
            }
            i2++;
        }
        if (i2 >= numberOfCameras) {
            Log.i(a, "No camera facing back; returning camera #0");
            return Camera.open(0);
        }
        Log.i(a, "Opening camera #" + i2);
        return Camera.open(i2);
    }
}
