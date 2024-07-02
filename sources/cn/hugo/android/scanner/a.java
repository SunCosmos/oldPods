package cn.hugo.android.scanner;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;

/* loaded from: classes.dex */
final class a implements SensorEventListener {
    private final Context a;
    private cn.hugo.android.scanner.f.c b;

    /* renamed from: c, reason: collision with root package name */
    private Sensor f1417c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context) {
        this.a = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(cn.hugo.android.scanner.f.c cVar) {
        this.b = cVar;
        if (cn.hugo.android.scanner.f.d.b(PreferenceManager.getDefaultSharedPreferences(this.a)) == cn.hugo.android.scanner.f.d.AUTO) {
            SensorManager sensorManager = (SensorManager) this.a.getSystemService("sensor");
            Sensor defaultSensor = sensorManager.getDefaultSensor(5);
            this.f1417c = defaultSensor;
            if (defaultSensor != null) {
                sensorManager.registerListener(this, defaultSensor, 3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        if (this.f1417c != null) {
            ((SensorManager) this.a.getSystemService("sensor")).unregisterListener(this);
            this.b = null;
            this.f1417c = null;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        cn.hugo.android.scanner.f.c cVar = this.b;
        if (cVar != null) {
            if (f <= 45.0f) {
                cVar.j(true);
            } else if (f >= 450.0f) {
                cVar.j(false);
            }
        }
    }
}
