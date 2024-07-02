package c.c.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import c.b.a.a.d;
import c.b.a.a.i;

/* loaded from: classes.dex */
public class c extends View {
    private Context a;

    public c(Context context) {
        super(context);
        this.a = null;
        this.a = context;
    }

    private Drawable a(Object obj) {
        if (obj instanceof Integer) {
            return new ColorDrawable(Integer.parseInt(String.valueOf(obj)));
        }
        if (obj instanceof Bitmap) {
            return new BitmapDrawable((Bitmap) obj);
        }
        if (obj instanceof GradientDrawable) {
            return (GradientDrawable) obj;
        }
        if (!(obj instanceof String)) {
            return null;
        }
        String obj2 = obj.toString();
        if (obj2.startsWith("#")) {
            return new ColorDrawable(Color.parseColor(obj.toString()));
        }
        if (obj2.startsWith("@")) {
            Context context = this.a;
            return new BitmapDrawable(i.b(context, d.p(context, obj2)));
        }
        if (obj2.startsWith("%") || obj2.startsWith("$")) {
            return new BitmapDrawable(i.c(d.p(this.a, obj2)));
        }
        return null;
    }

    public StateListDrawable b(int i2, int i3, int i4) {
        return c(new ColorDrawable(i2), new ColorDrawable(i3), new ColorDrawable(i4));
    }

    public StateListDrawable c(Drawable drawable, Drawable drawable2, Drawable drawable3) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, drawable);
        stateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, drawable2);
        stateListDrawable.addState(View.ENABLED_STATE_SET, drawable3);
        stateListDrawable.addState(View.FOCUSED_STATE_SET, drawable2);
        stateListDrawable.addState(View.EMPTY_STATE_SET, drawable3);
        return stateListDrawable;
    }

    public StateListDrawable d(Object obj, Object obj2, Object obj3) {
        Drawable a;
        Drawable a2;
        Drawable a3 = a(obj);
        if (a3 == null || (a = a(obj2)) == null || (a2 = a(obj3)) == null) {
            return null;
        }
        return c(a3, a, a2);
    }
}
