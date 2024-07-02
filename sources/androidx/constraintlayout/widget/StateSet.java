package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class StateSet {
    public static final String TAG = "ConstraintLayoutStates";
    int a = -1;
    int b = -1;

    /* renamed from: c, reason: collision with root package name */
    int f440c = -1;

    /* renamed from: d, reason: collision with root package name */
    private SparseArray<a> f441d = new SparseArray<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a {
        int a;
        ArrayList<b> b = new ArrayList<>();

        /* renamed from: c, reason: collision with root package name */
        int f442c;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f442c = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.State);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.State_android_id) {
                    this.a = obtainStyledAttributes.getResourceId(index, this.a);
                } else if (index == R.styleable.State_constraints) {
                    this.f442c = obtainStyledAttributes.getResourceId(index, this.f442c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f442c);
                    context.getResources().getResourceName(this.f442c);
                    "layout".equals(resourceTypeName);
                }
            }
            obtainStyledAttributes.recycle();
        }

        void a(b bVar) {
            this.b.add(bVar);
        }

        public int b(float f, float f2) {
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                if (this.b.get(i2).a(f, f2)) {
                    return i2;
                }
            }
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {
        float a;
        float b;

        /* renamed from: c, reason: collision with root package name */
        float f443c;

        /* renamed from: d, reason: collision with root package name */
        float f444d;
        int e;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.a = Float.NaN;
            this.b = Float.NaN;
            this.f443c = Float.NaN;
            this.f444d = Float.NaN;
            this.e = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.Variant);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.Variant_constraints) {
                    this.e = obtainStyledAttributes.getResourceId(index, this.e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.e);
                    context.getResources().getResourceName(this.e);
                    "layout".equals(resourceTypeName);
                } else if (index == R.styleable.Variant_region_heightLessThan) {
                    this.f444d = obtainStyledAttributes.getDimension(index, this.f444d);
                } else if (index == R.styleable.Variant_region_heightMoreThan) {
                    this.b = obtainStyledAttributes.getDimension(index, this.b);
                } else if (index == R.styleable.Variant_region_widthLessThan) {
                    this.f443c = obtainStyledAttributes.getDimension(index, this.f443c);
                } else if (index == R.styleable.Variant_region_widthMoreThan) {
                    this.a = obtainStyledAttributes.getDimension(index, this.a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            obtainStyledAttributes.recycle();
        }

        boolean a(float f, float f2) {
            if (!Float.isNaN(this.a) && f < this.a) {
                return false;
            }
            if (!Float.isNaN(this.b) && f2 < this.b) {
                return false;
            }
            if (Float.isNaN(this.f443c) || f <= this.f443c) {
                return Float.isNaN(this.f444d) || f2 <= this.f444d;
            }
            return false;
        }
    }

    public StateSet(Context context, XmlPullParser xmlPullParser) {
        new SparseArray();
        a(context, xmlPullParser);
    }

    private void a(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.StateSet);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R.styleable.StateSet_defaultState) {
                this.a = obtainStyledAttributes.getResourceId(index, this.a);
            }
        }
        obtainStyledAttributes.recycle();
        a aVar = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 0) {
                    xmlPullParser.getName();
                } else if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    char c2 = 65535;
                    switch (name.hashCode()) {
                        case 80204913:
                            if (name.equals("State")) {
                                c2 = 2;
                                break;
                            }
                            break;
                        case 1301459538:
                            if (name.equals("LayoutDescription")) {
                                c2 = 0;
                                break;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                c2 = 1;
                                break;
                            }
                            break;
                        case 1901439077:
                            if (name.equals("Variant")) {
                                c2 = 3;
                                break;
                            }
                            break;
                    }
                    if (c2 != 0 && c2 != 1) {
                        if (c2 == 2) {
                            aVar = new a(context, xmlPullParser);
                            this.f441d.put(aVar.a, aVar);
                        } else if (c2 != 3) {
                            Log.v("ConstraintLayoutStates", "unknown tag " + name);
                        } else {
                            b bVar = new b(context, xmlPullParser);
                            if (aVar != null) {
                                aVar.a(bVar);
                            }
                        }
                    }
                } else if (eventType != 3) {
                    continue;
                } else if ("StateSet".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }

    public int convertToConstraintSet(int i2, int i3, float f, float f2) {
        a aVar = this.f441d.get(i3);
        if (aVar == null) {
            return i3;
        }
        if (f == -1.0f || f2 == -1.0f) {
            if (aVar.f442c == i2) {
                return i2;
            }
            Iterator<b> it = aVar.b.iterator();
            while (it.hasNext()) {
                if (i2 == it.next().e) {
                    return i2;
                }
            }
            return aVar.f442c;
        }
        b bVar = null;
        Iterator<b> it2 = aVar.b.iterator();
        while (it2.hasNext()) {
            b next = it2.next();
            if (next.a(f, f2)) {
                if (i2 == next.e) {
                    return i2;
                }
                bVar = next;
            }
        }
        return bVar != null ? bVar.e : aVar.f442c;
    }

    public boolean needsToChange(int i2, float f, float f2) {
        int i3 = this.b;
        if (i3 != i2) {
            return true;
        }
        a valueAt = i2 == -1 ? this.f441d.valueAt(0) : this.f441d.get(i3);
        int i4 = this.f440c;
        return (i4 == -1 || !valueAt.b.get(i4).a(f, f2)) && this.f440c != valueAt.b(f, f2);
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
    }

    public int stateGetConstraintID(int i2, int i3, int i4) {
        return updateConstraints(-1, i2, i3, i4);
    }

    public int updateConstraints(int i2, int i3, float f, float f2) {
        int b2;
        if (i2 == i3) {
            a valueAt = i3 == -1 ? this.f441d.valueAt(0) : this.f441d.get(this.b);
            if (valueAt == null) {
                return -1;
            }
            return ((this.f440c == -1 || !valueAt.b.get(i2).a(f, f2)) && i2 != (b2 = valueAt.b(f, f2))) ? b2 == -1 ? valueAt.f442c : valueAt.b.get(b2).e : i2;
        }
        a aVar = this.f441d.get(i3);
        if (aVar == null) {
            return -1;
        }
        int b3 = aVar.b(f, f2);
        return b3 == -1 ? aVar.f442c : aVar.b.get(b3).e;
    }
}
