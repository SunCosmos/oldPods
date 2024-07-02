package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class ConstraintLayoutStates {
    public static final String TAG = "ConstraintLayoutStates";
    private final ConstraintLayout a;
    ConstraintSet b;

    /* renamed from: c */
    int f431c = -1;

    /* renamed from: d */
    int f432d = -1;
    private SparseArray<a> e = new SparseArray<>();
    private SparseArray<ConstraintSet> f = new SparseArray<>();
    private ConstraintsChangedListener g = null;

    /* loaded from: classes.dex */
    public static class a {
        int a;
        ArrayList<b> b = new ArrayList<>();

        /* renamed from: c */
        int f433c;

        /* renamed from: d */
        ConstraintSet f434d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f433c = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.State);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.State_android_id) {
                    this.a = obtainStyledAttributes.getResourceId(index, this.a);
                } else if (index == R.styleable.State_constraints) {
                    this.f433c = obtainStyledAttributes.getResourceId(index, this.f433c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f433c);
                    context.getResources().getResourceName(this.f433c);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.f434d = constraintSet;
                        constraintSet.clone(context, this.f433c);
                    }
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

    /* loaded from: classes.dex */
    public static class b {
        float a;
        float b;

        /* renamed from: c */
        float f435c;

        /* renamed from: d */
        float f436d;
        int e;
        ConstraintSet f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.a = Float.NaN;
            this.b = Float.NaN;
            this.f435c = Float.NaN;
            this.f436d = Float.NaN;
            this.e = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.Variant);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.Variant_constraints) {
                    this.e = obtainStyledAttributes.getResourceId(index, this.e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.e);
                    context.getResources().getResourceName(this.e);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        this.f = constraintSet;
                        constraintSet.clone(context, this.e);
                    }
                } else if (index == R.styleable.Variant_region_heightLessThan) {
                    this.f436d = obtainStyledAttributes.getDimension(index, this.f436d);
                } else if (index == R.styleable.Variant_region_heightMoreThan) {
                    this.b = obtainStyledAttributes.getDimension(index, this.b);
                } else if (index == R.styleable.Variant_region_widthLessThan) {
                    this.f435c = obtainStyledAttributes.getDimension(index, this.f435c);
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
            if (Float.isNaN(this.f435c) || f <= this.f435c) {
                return Float.isNaN(this.f436d) || f2 <= this.f436d;
            }
            return false;
        }
    }

    public ConstraintLayoutStates(Context context, ConstraintLayout constraintLayout, int i2) {
        this.a = constraintLayout;
        a(context, i2);
    }

    private void a(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        a aVar = null;
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    char c2 = 65535;
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                c2 = 4;
                                break;
                            }
                            break;
                        case 80204913:
                            if (name.equals("State")) {
                                c2 = 2;
                                break;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                c2 = 1;
                                break;
                            }
                            break;
                        case 1657696882:
                            if (name.equals("layoutDescription")) {
                                c2 = 0;
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
                            aVar = new a(context, xml);
                            this.e.put(aVar.a, aVar);
                        } else if (c2 == 3) {
                            b bVar = new b(context, xml);
                            if (aVar != null) {
                                aVar.a(bVar);
                            }
                        } else if (c2 != 4) {
                            Log.v("ConstraintLayoutStates", "unknown tag " + name);
                        } else {
                            b(context, xml);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }

    private void b(Context context, XmlPullParser xmlPullParser) {
        ConstraintSet constraintSet = new ConstraintSet();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            if ("id".equals(xmlPullParser.getAttributeName(i2))) {
                String attributeValue = xmlPullParser.getAttributeValue(i2);
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName()) : -1;
                if (identifier == -1) {
                    if (attributeValue == null || attributeValue.length() <= 1) {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    } else {
                        identifier = Integer.parseInt(attributeValue.substring(1));
                    }
                }
                constraintSet.load(context, xmlPullParser);
                this.f.put(identifier, constraintSet);
                return;
            }
        }
    }

    public boolean needsToChange(int i2, float f, float f2) {
        int i3 = this.f431c;
        if (i3 != i2) {
            return true;
        }
        a valueAt = i2 == -1 ? this.e.valueAt(0) : this.e.get(i3);
        int i4 = this.f432d;
        return (i4 == -1 || !valueAt.b.get(i4).a(f, f2)) && this.f432d != valueAt.b(f, f2);
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        this.g = constraintsChangedListener;
    }

    public void updateConstraints(int i2, float f, float f2) {
        int b2;
        int i3 = this.f431c;
        if (i3 == i2) {
            a valueAt = i2 == -1 ? this.e.valueAt(0) : this.e.get(i3);
            int i4 = this.f432d;
            if ((i4 == -1 || !valueAt.b.get(i4).a(f, f2)) && this.f432d != (b2 = valueAt.b(f, f2))) {
                ConstraintSet constraintSet = b2 == -1 ? this.b : valueAt.b.get(b2).f;
                int i5 = b2 == -1 ? valueAt.f433c : valueAt.b.get(b2).e;
                if (constraintSet == null) {
                    return;
                }
                this.f432d = b2;
                ConstraintsChangedListener constraintsChangedListener = this.g;
                if (constraintsChangedListener != null) {
                    constraintsChangedListener.preLayoutChange(-1, i5);
                }
                constraintSet.applyTo(this.a);
                ConstraintsChangedListener constraintsChangedListener2 = this.g;
                if (constraintsChangedListener2 != null) {
                    constraintsChangedListener2.postLayoutChange(-1, i5);
                    return;
                }
                return;
            }
            return;
        }
        this.f431c = i2;
        a aVar = this.e.get(i2);
        int b3 = aVar.b(f, f2);
        ConstraintSet constraintSet2 = b3 == -1 ? aVar.f434d : aVar.b.get(b3).f;
        int i6 = b3 == -1 ? aVar.f433c : aVar.b.get(b3).e;
        if (constraintSet2 == null) {
            Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i2 + ", dim =" + f + ", " + f2);
            return;
        }
        this.f432d = b3;
        ConstraintsChangedListener constraintsChangedListener3 = this.g;
        if (constraintsChangedListener3 != null) {
            constraintsChangedListener3.preLayoutChange(i2, i6);
        }
        constraintSet2.applyTo(this.a);
        ConstraintsChangedListener constraintsChangedListener4 = this.g;
        if (constraintsChangedListener4 != null) {
            constraintsChangedListener4.postLayoutChange(i2, i6);
        }
    }
}
