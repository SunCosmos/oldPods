package cn.hugo.android.scanner.f;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.hardware.Camera;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
final class b {
    private final Context a;
    private Point b;

    /* renamed from: c, reason: collision with root package name */
    private Point f1426c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Comparator<Camera.Size> {
        a(b bVar) {
        }

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Camera.Size size, Camera.Size size2) {
            int i2 = size.height * size.width;
            int i3 = size2.height * size2.width;
            if (i3 < i2) {
                return -1;
            }
            return i3 > i2 ? 1 : 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Context context) {
        this.a = context;
    }

    private void a(Camera.Parameters parameters, boolean z, boolean z2) {
        String c2 = z ? c(parameters.getSupportedFlashModes(), "torch", "on") : c(parameters.getSupportedFlashModes(), "off");
        if (c2 != null) {
            parameters.setFlashMode(c2);
        }
    }

    private Point b(Camera.Parameters parameters, Point point) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes == null) {
            Log.w("CameraConfiguration", "Device returned no supported preview sizes; using default");
            Camera.Size previewSize = parameters.getPreviewSize();
            return new Point(previewSize.width, previewSize.height);
        }
        ArrayList<Camera.Size> arrayList = new ArrayList(supportedPreviewSizes);
        Collections.sort(arrayList, new a(this));
        if (Log.isLoggable("CameraConfiguration", 4)) {
            StringBuilder sb = new StringBuilder();
            for (Camera.Size size : arrayList) {
                sb.append(size.width);
                sb.append('x');
                sb.append(size.height);
                sb.append(' ');
            }
            Log.i("CameraConfiguration", "Supported preview sizes: " + ((Object) sb));
        }
        double d2 = point.x;
        double d3 = point.y;
        Double.isNaN(d2);
        Double.isNaN(d3);
        double d4 = d2 / d3;
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                if (arrayList.isEmpty()) {
                    Camera.Size previewSize2 = parameters.getPreviewSize();
                    Point point2 = new Point(previewSize2.width, previewSize2.height);
                    Log.i("CameraConfiguration", "No suitable preview sizes, using default: " + point2);
                    return point2;
                }
                Camera.Size size2 = (Camera.Size) arrayList.get(0);
                Point point3 = new Point(size2.width, size2.height);
                Log.i("CameraConfiguration", "Using largest suitable preview size: " + point3);
                return point3;
            }
            Camera.Size size3 = (Camera.Size) it.next();
            int i2 = size3.width;
            int i3 = size3.height;
            if (i2 * i3 >= 153600) {
                boolean z = i2 < i3;
                int i4 = z ? i3 : i2;
                int i5 = z ? i2 : i3;
                double d5 = i4;
                double d6 = i5;
                Double.isNaN(d5);
                Double.isNaN(d6);
                if (Math.abs((d5 / d6) - d4) <= 0.15d) {
                    if (i4 == point.x && i5 == point.y) {
                        Point point4 = new Point(i2, i3);
                        Log.i("CameraConfiguration", "Found preview size exactly matching screen size: " + point4);
                        return point4;
                    }
                }
            }
            it.remove();
        }
    }

    private static String c(Collection<String> collection, String... strArr) {
        String str;
        Log.i("CameraConfiguration", "Supported values: " + collection);
        if (collection != null) {
            int length = strArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                str = strArr[i2];
                if (collection.contains(str)) {
                    break;
                }
            }
        }
        str = null;
        Log.i("CameraConfiguration", "Settable value: " + str);
        return str;
    }

    @SuppressLint({"NewApi"})
    private Point e(Display display) {
        Point point = new Point();
        try {
            display.getSize(point);
        } catch (NoSuchMethodError unused) {
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        return point;
    }

    private void i(Camera.Parameters parameters, SharedPreferences sharedPreferences, boolean z) {
        a(parameters, d.b(sharedPreferences) == d.ON, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Point d() {
        return this.f1426c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Point f() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g(Camera camera) {
        String flashMode;
        if (camera == null || camera.getParameters() == null || (flashMode = camera.getParameters().getFlashMode()) == null) {
            return false;
        }
        return "on".equals(flashMode) || "torch".equals(flashMode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();
        Display defaultDisplay = ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay();
        new Point();
        this.b = e(defaultDisplay);
        Log.i("CameraConfiguration", "Screen resolution: " + this.b);
        Point point = new Point();
        Point point2 = this.b;
        point.x = point2.x;
        point.y = point2.y;
        int i2 = point2.x;
        int i3 = point2.y;
        if (i2 < i3) {
            point.x = i3;
            point.y = point2.x;
        }
        this.f1426c = b(parameters, point);
        Log.i("CameraConfiguration", "Camera resolution: " + this.f1426c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(Camera camera, boolean z) {
        String c2;
        Camera.Parameters parameters = camera.getParameters();
        if (parameters == null) {
            Log.w("CameraConfiguration", "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i("CameraConfiguration", "Initial camera parameters: " + parameters.flatten());
        if (z) {
            Log.w("CameraConfiguration", "In camera config safe mode -- most settings will not be honored");
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.a);
        i(parameters, defaultSharedPreferences, z);
        String c3 = c(parameters.getSupportedFocusModes(), "auto");
        if (!z && c3 == null) {
            c3 = c(parameters.getSupportedFocusModes(), "macro", "edof");
        }
        if (c3 != null) {
            parameters.setFocusMode(c3);
        }
        if (defaultSharedPreferences.getBoolean("preferences_invert_scan", false) && (c2 = c(parameters.getSupportedColorEffects(), "negative")) != null) {
            parameters.setColorEffect(c2);
        }
        Point point = this.f1426c;
        parameters.setPreviewSize(point.x, point.y);
        camera.setParameters(parameters);
        Camera.Size previewSize = camera.getParameters().getPreviewSize();
        if (previewSize != null) {
            Point point2 = this.f1426c;
            if (point2.x != previewSize.width || point2.y != previewSize.height) {
                Log.w("CameraConfiguration", "Camera said it supported preview size " + this.f1426c.x + 'x' + this.f1426c.y + ", but after setting it, preview size is " + previewSize.width + 'x' + previewSize.height);
                Point point3 = this.f1426c;
                point3.x = previewSize.width;
                point3.y = previewSize.height;
            }
        }
        camera.setDisplayOrientation(90);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(Camera camera, boolean z) {
        Camera.Parameters parameters = camera.getParameters();
        a(parameters, z, false);
        camera.setParameters(parameters);
    }
}
