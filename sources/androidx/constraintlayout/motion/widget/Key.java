package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes.dex */
public abstract class Key {
    public static int UNSET = -1;
    int a;
    int b;

    /* renamed from: c, reason: collision with root package name */
    String f299c;

    /* renamed from: d, reason: collision with root package name */
    protected int f300d;
    HashMap<String, ConstraintAttribute> e;

    public Key() {
        int i2 = UNSET;
        this.a = i2;
        this.b = i2;
        this.f299c = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(String str) {
        String str2 = this.f299c;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public abstract void addValues(HashMap<String, SplineSet> hashMap);

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(Object obj) {
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : Boolean.parseBoolean(obj.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float c(Object obj) {
        return obj instanceof Float ? ((Float) obj).floatValue() : Float.parseFloat(obj.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d(Object obj) {
        return obj instanceof Integer ? ((Integer) obj).intValue() : Integer.parseInt(obj.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void getAttributeNames(HashSet<String> hashSet);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void load(Context context, AttributeSet attributeSet);

    public void setInterpolation(HashMap<String, Integer> hashMap) {
    }

    public abstract void setValue(String str, Object obj);
}
