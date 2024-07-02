package androidx.constraintlayout.motion.widget;

import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes.dex */
public class KeyCache {
    HashMap<Object, HashMap<String, float[]>> a = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public float a(Object obj, String str, int i2) {
        if (!this.a.containsKey(obj)) {
            return Float.NaN;
        }
        HashMap<String, float[]> hashMap = this.a.get(obj);
        if (!hashMap.containsKey(str)) {
            return Float.NaN;
        }
        float[] fArr = hashMap.get(str);
        if (fArr.length > i2) {
            return fArr[i2];
        }
        return Float.NaN;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Object obj, String str, int i2, float f) {
        HashMap<String, float[]> hashMap;
        if (this.a.containsKey(obj)) {
            hashMap = this.a.get(obj);
            if (hashMap.containsKey(str)) {
                float[] fArr = hashMap.get(str);
                if (fArr.length <= i2) {
                    fArr = Arrays.copyOf(fArr, i2 + 1);
                }
                fArr[i2] = f;
                hashMap.put(str, fArr);
                return;
            }
            float[] fArr2 = new float[i2 + 1];
            fArr2[i2] = f;
            hashMap.put(str, fArr2);
        } else {
            hashMap = new HashMap<>();
            float[] fArr3 = new float[i2 + 1];
            fArr3[i2] = f;
            hashMap.put(str, fArr3);
        }
        this.a.put(obj, hashMap);
    }
}
