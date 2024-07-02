package cn.hugo.android.scanner.h;

import android.content.Context;
import android.graphics.Bitmap;
import c.a.a.j;
import c.a.a.l;
import c.a.a.p;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: classes.dex */
public class a {
    j a = new j();

    public a(Context context) {
        Hashtable hashtable = new Hashtable(2);
        Vector vector = new Vector();
        if (vector.isEmpty()) {
            vector = new Vector();
            vector.addAll(d.b);
            vector.addAll(d.f1439c);
            vector.addAll(d.f1440d);
        }
        hashtable.put(c.a.a.e.POSSIBLE_FORMATS, vector);
        hashtable.put(c.a.a.e.CHARACTER_SET, "utf-8");
        this.a.e(hashtable);
    }

    public p a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            return this.a.d(new c.a.a.c(new c.a.a.x.j(new b(bitmap))));
        } catch (l e) {
            e.printStackTrace();
            return null;
        }
    }
}
