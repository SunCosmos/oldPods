package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.StateSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class MotionScene {
    public static final int LAYOUT_HONOR_REQUEST = 1;
    public static final int LAYOUT_IGNORE_REQUEST = 0;
    public static final String TAG = "MotionScene";
    public static final int UNSET = -1;
    private final MotionLayout a;
    private MotionEvent n;
    private MotionLayout.MotionTracker q;
    private boolean r;
    float s;
    float t;
    StateSet b = null;

    /* renamed from: c */
    Transition f326c = null;

    /* renamed from: d */
    private boolean f327d = false;
    private ArrayList<Transition> e = new ArrayList<>();
    private Transition f = null;
    private ArrayList<Transition> g = new ArrayList<>();
    private SparseArray<ConstraintSet> h = new SparseArray<>();

    /* renamed from: i */
    private HashMap<String, Integer> f328i = new HashMap<>();
    private SparseIntArray j = new SparseIntArray();
    private boolean k = false;
    private int l = 400;
    private int m = 0;
    private boolean o = false;
    private boolean p = false;

    /* loaded from: classes.dex */
    public static class Transition {
        public static final int AUTO_ANIMATE_TO_END = 4;
        public static final int AUTO_ANIMATE_TO_START = 3;
        public static final int AUTO_JUMP_TO_END = 2;
        public static final int AUTO_JUMP_TO_START = 1;
        public static final int AUTO_NONE = 0;
        private int a;
        private boolean b;

        /* renamed from: c */
        private int f329c;

        /* renamed from: d */
        private int f330d;
        private int e;
        private String f;
        private int g;
        private int h;

        /* renamed from: i */
        private float f331i;
        private final MotionScene j;
        private ArrayList<KeyFrames> k;
        private d l;
        private ArrayList<a> m;
        private int n;
        private boolean o;
        private int p;
        private int q;
        private int r;

        /* loaded from: classes.dex */
        public static class a implements View.OnClickListener {
            private final Transition a;
            int b;

            /* renamed from: c */
            int f332c;

            public a(Context context, Transition transition, XmlPullParser xmlPullParser) {
                this.b = -1;
                this.f332c = 17;
                this.a = transition;
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.OnClick);
                int indexCount = obtainStyledAttributes.getIndexCount();
                for (int i2 = 0; i2 < indexCount; i2++) {
                    int index = obtainStyledAttributes.getIndex(i2);
                    if (index == R.styleable.OnClick_targetId) {
                        this.b = obtainStyledAttributes.getResourceId(index, this.b);
                    } else if (index == R.styleable.OnClick_clickAction) {
                        this.f332c = obtainStyledAttributes.getInt(index, this.f332c);
                    }
                }
                obtainStyledAttributes.recycle();
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r7v4, types: [android.view.View] */
            public void a(MotionLayout motionLayout, int i2, Transition transition) {
                int i3 = this.b;
                MotionLayout motionLayout2 = motionLayout;
                if (i3 != -1) {
                    motionLayout2 = motionLayout.findViewById(i3);
                }
                if (motionLayout2 == null) {
                    Log.e(MotionScene.TAG, "OnClick could not find id " + this.b);
                    return;
                }
                int i4 = transition.f330d;
                int i5 = transition.f329c;
                if (i4 == -1) {
                    motionLayout2.setOnClickListener(this);
                    return;
                }
                int i6 = this.f332c;
                boolean z = false;
                boolean z2 = ((i6 & 1) != 0 && i2 == i4) | ((i6 & 1) != 0 && i2 == i4) | ((i6 & 256) != 0 && i2 == i4) | ((i6 & 16) != 0 && i2 == i5);
                if ((i6 & 4096) != 0 && i2 == i5) {
                    z = true;
                }
                if (z2 || z) {
                    motionLayout2.setOnClickListener(this);
                }
            }

            boolean b(Transition transition, MotionLayout motionLayout) {
                Transition transition2 = this.a;
                if (transition2 == transition) {
                    return true;
                }
                int i2 = transition2.f329c;
                int i3 = this.a.f330d;
                int i4 = motionLayout.x;
                return i3 == -1 ? i4 != i2 : i4 == i3 || i4 == i2;
            }

            public void c(MotionLayout motionLayout) {
                int i2 = this.b;
                if (i2 == -1) {
                    return;
                }
                View findViewById = motionLayout.findViewById(i2);
                if (findViewById != null) {
                    findViewById.setOnClickListener(null);
                    return;
                }
                Log.e(MotionScene.TAG, " (*)  could not find id " + this.b);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                float f;
                MotionLayout motionLayout = this.a.j.a;
                if (motionLayout.isInteractionEnabled()) {
                    if (this.a.f330d == -1) {
                        int currentState = motionLayout.getCurrentState();
                        if (currentState == -1) {
                            motionLayout.transitionToState(this.a.f329c);
                            return;
                        }
                        Transition transition = new Transition(this.a.j, this.a);
                        transition.f330d = currentState;
                        transition.f329c = this.a.f329c;
                        motionLayout.setTransition(transition);
                        motionLayout.transitionToEnd();
                        return;
                    }
                    Transition transition2 = this.a.j.f326c;
                    int i2 = this.f332c;
                    boolean z = false;
                    boolean z2 = ((i2 & 1) == 0 && (i2 & 256) == 0) ? false : true;
                    boolean z3 = ((i2 & 16) == 0 && (i2 & 4096) == 0) ? false : true;
                    if (z2 && z3) {
                        Transition transition3 = this.a.j.f326c;
                        Transition transition4 = this.a;
                        if (transition3 != transition4) {
                            motionLayout.setTransition(transition4);
                        }
                        if (motionLayout.getCurrentState() != motionLayout.getEndState() && motionLayout.getProgress() <= 0.5f) {
                            z = z2;
                            z3 = false;
                        }
                    } else {
                        z = z2;
                    }
                    if (b(transition2, motionLayout)) {
                        if (z && (this.f332c & 1) != 0) {
                            motionLayout.setTransition(this.a);
                            motionLayout.transitionToEnd();
                            return;
                        }
                        if (z3 && (this.f332c & 16) != 0) {
                            motionLayout.setTransition(this.a);
                            motionLayout.transitionToStart();
                            return;
                        }
                        if (z && (this.f332c & 256) != 0) {
                            motionLayout.setTransition(this.a);
                            f = 1.0f;
                        } else {
                            if (!z3 || (this.f332c & 4096) == 0) {
                                return;
                            }
                            motionLayout.setTransition(this.a);
                            f = 0.0f;
                        }
                        motionLayout.setProgress(f);
                    }
                }
            }
        }

        public Transition(int i2, MotionScene motionScene, int i3, int i4) {
            this.a = -1;
            this.b = false;
            this.f329c = -1;
            this.f330d = -1;
            this.e = 0;
            this.f = null;
            this.g = -1;
            this.h = 400;
            this.f331i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.a = i2;
            this.j = motionScene;
            this.f330d = i3;
            this.f329c = i4;
            this.h = motionScene.l;
            this.q = motionScene.m;
        }

        Transition(MotionScene motionScene, Context context, XmlPullParser xmlPullParser) {
            this.a = -1;
            this.b = false;
            this.f329c = -1;
            this.f330d = -1;
            this.e = 0;
            this.f = null;
            this.g = -1;
            this.h = 400;
            this.f331i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.h = motionScene.l;
            this.q = motionScene.m;
            this.j = motionScene;
            u(motionScene, context, Xml.asAttributeSet(xmlPullParser));
        }

        Transition(MotionScene motionScene, Transition transition) {
            this.a = -1;
            this.b = false;
            this.f329c = -1;
            this.f330d = -1;
            this.e = 0;
            this.f = null;
            this.g = -1;
            this.h = 400;
            this.f331i = 0.0f;
            this.k = new ArrayList<>();
            this.l = null;
            this.m = new ArrayList<>();
            this.n = 0;
            this.o = false;
            this.p = -1;
            this.q = 0;
            this.r = 0;
            this.j = motionScene;
            if (transition != null) {
                this.p = transition.p;
                this.e = transition.e;
                this.f = transition.f;
                this.g = transition.g;
                this.h = transition.h;
                this.k = transition.k;
                this.f331i = transition.f331i;
                this.q = transition.q;
            }
        }

        private void t(MotionScene motionScene, Context context, TypedArray typedArray) {
            ConstraintSet constraintSet;
            SparseArray sparseArray;
            int i2;
            int indexCount = typedArray.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArray.getIndex(i3);
                if (index == R.styleable.Transition_constraintSetEnd) {
                    this.f329c = typedArray.getResourceId(index, this.f329c);
                    if ("layout".equals(context.getResources().getResourceTypeName(this.f329c))) {
                        constraintSet = new ConstraintSet();
                        constraintSet.load(context, this.f329c);
                        sparseArray = motionScene.h;
                        i2 = this.f329c;
                        sparseArray.append(i2, constraintSet);
                    }
                } else {
                    if (index == R.styleable.Transition_constraintSetStart) {
                        this.f330d = typedArray.getResourceId(index, this.f330d);
                        if ("layout".equals(context.getResources().getResourceTypeName(this.f330d))) {
                            constraintSet = new ConstraintSet();
                            constraintSet.load(context, this.f330d);
                            sparseArray = motionScene.h;
                            i2 = this.f330d;
                            sparseArray.append(i2, constraintSet);
                        }
                    } else if (index == R.styleable.Transition_motionInterpolator) {
                        int i4 = typedArray.peekValue(index).type;
                        if (i4 == 1) {
                            int resourceId = typedArray.getResourceId(index, -1);
                            this.g = resourceId;
                            if (resourceId == -1) {
                            }
                            this.e = -2;
                        } else if (i4 == 3) {
                            String string = typedArray.getString(index);
                            this.f = string;
                            if (string.indexOf("/") > 0) {
                                this.g = typedArray.getResourceId(index, -1);
                                this.e = -2;
                            } else {
                                this.e = -1;
                            }
                        } else {
                            this.e = typedArray.getInteger(index, this.e);
                        }
                    } else if (index == R.styleable.Transition_duration) {
                        this.h = typedArray.getInt(index, this.h);
                    } else if (index == R.styleable.Transition_staggered) {
                        this.f331i = typedArray.getFloat(index, this.f331i);
                    } else if (index == R.styleable.Transition_autoTransition) {
                        this.n = typedArray.getInteger(index, this.n);
                    } else if (index == R.styleable.Transition_android_id) {
                        this.a = typedArray.getResourceId(index, this.a);
                    } else if (index == R.styleable.Transition_transitionDisable) {
                        this.o = typedArray.getBoolean(index, this.o);
                    } else if (index == R.styleable.Transition_pathMotionArc) {
                        this.p = typedArray.getInteger(index, -1);
                    } else if (index == R.styleable.Transition_layoutDuringTransition) {
                        this.q = typedArray.getInteger(index, 0);
                    } else if (index == R.styleable.Transition_transitionFlags) {
                        this.r = typedArray.getInteger(index, 0);
                    }
                }
            }
            if (this.f330d == -1) {
                this.b = true;
            }
        }

        private void u(MotionScene motionScene, Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Transition);
            t(motionScene, context, obtainStyledAttributes);
            obtainStyledAttributes.recycle();
        }

        public void addOnClick(Context context, XmlPullParser xmlPullParser) {
            this.m.add(new a(context, this, xmlPullParser));
        }

        public String debugString(Context context) {
            String resourceEntryName = this.f330d == -1 ? "null" : context.getResources().getResourceEntryName(this.f330d);
            if (this.f329c == -1) {
                return resourceEntryName + " -> null";
            }
            return resourceEntryName + " -> " + context.getResources().getResourceEntryName(this.f329c);
        }

        public int getAutoTransition() {
            return this.n;
        }

        public int getDuration() {
            return this.h;
        }

        public int getEndConstraintSetId() {
            return this.f329c;
        }

        public int getId() {
            return this.a;
        }

        public List<KeyFrames> getKeyFrameList() {
            return this.k;
        }

        public int getLayoutDuringTransition() {
            return this.q;
        }

        public List<a> getOnClickList() {
            return this.m;
        }

        public int getPathMotionArc() {
            return this.p;
        }

        public float getStagger() {
            return this.f331i;
        }

        public int getStartConstraintSetId() {
            return this.f330d;
        }

        public d getTouchResponse() {
            return this.l;
        }

        public boolean isEnabled() {
            return !this.o;
        }

        public boolean isTransitionFlag(int i2) {
            return (i2 & this.r) != 0;
        }

        public void setAutoTransition(int i2) {
            this.n = i2;
        }

        public void setDuration(int i2) {
            this.h = i2;
        }

        public void setEnable(boolean z) {
            this.o = !z;
        }

        public void setPathMotionArc(int i2) {
            this.p = i2;
        }

        public void setStagger(float f) {
            this.f331i = f;
        }
    }

    /* loaded from: classes.dex */
    public class a implements Interpolator {
        final /* synthetic */ Easing a;

        a(MotionScene motionScene, Easing easing) {
            this.a = easing;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            return (float) this.a.get(f);
        }
    }

    public MotionScene(Context context, MotionLayout motionLayout, int i2) {
        this.a = motionLayout;
        u(context, i2);
        SparseArray<ConstraintSet> sparseArray = this.h;
        int i3 = R.id.motion_base;
        sparseArray.put(i3, new ConstraintSet());
        this.f328i.put("motion_base", Integer.valueOf(i3));
    }

    public MotionScene(MotionLayout motionLayout) {
        this.a = motionLayout;
    }

    private void A(int i2) {
        int i3 = this.j.get(i2);
        if (i3 > 0) {
            A(this.j.get(i2));
            ConstraintSet constraintSet = this.h.get(i2);
            ConstraintSet constraintSet2 = this.h.get(i3);
            if (constraintSet2 != null) {
                constraintSet.readFallback(constraintSet2);
                this.j.put(i2, -1);
            } else {
                Log.e(TAG, "ERROR! invalid deriveConstraintsFrom: @id/" + Debug.getName(this.a.getContext(), i3));
            }
        }
    }

    private int i(Context context, String str) {
        int i2;
        if (str.contains("/")) {
            i2 = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), "id", context.getPackageName());
            if (this.k) {
                System.out.println("id getMap res = " + i2);
            }
        } else {
            i2 = -1;
        }
        if (i2 != -1) {
            return i2;
        }
        if (str != null && str.length() > 1) {
            return Integer.parseInt(str.substring(1));
        }
        Log.e(TAG, "error in parsing id");
        return i2;
    }

    private int j(Transition transition) {
        int i2 = transition.a;
        if (i2 == -1) {
            throw new IllegalArgumentException("The transition must have an id");
        }
        for (int i3 = 0; i3 < this.e.size(); i3++) {
            if (this.e.get(i3).a == i2) {
                return i3;
            }
        }
        return -1;
    }

    private int p(int i2) {
        int stateGetConstraintID;
        StateSet stateSet = this.b;
        return (stateSet == null || (stateGetConstraintID = stateSet.stateGetConstraintID(i2, -1, -1)) == -1) ? i2 : stateGetConstraintID;
    }

    private boolean r(int i2) {
        int i3 = this.j.get(i2);
        int size = this.j.size();
        while (i3 > 0) {
            if (i3 == i2) {
                return true;
            }
            int i4 = size - 1;
            if (size < 0) {
                return true;
            }
            i3 = this.j.get(i3);
            size = i4;
        }
        return false;
    }

    public static String stripID(String str) {
        if (str == null) {
            return "";
        }
        int indexOf = str.indexOf(47);
        return indexOf < 0 ? str : str.substring(indexOf + 1);
    }

    private boolean t() {
        return this.q != null;
    }

    private void u(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        Transition transition = null;
        try {
            int eventType = xml.getEventType();
            while (true) {
                char c2 = 1;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    if (this.k) {
                        System.out.println("parsing = " + name);
                    }
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                c2 = 5;
                                break;
                            }
                            break;
                        case -1239391468:
                            if (name.equals("KeyFrameSet")) {
                                c2 = 6;
                                break;
                            }
                            break;
                        case 269306229:
                            if (name.equals("Transition")) {
                                break;
                            }
                            break;
                        case 312750793:
                            if (name.equals("OnClick")) {
                                c2 = 3;
                                break;
                            }
                            break;
                        case 327855227:
                            if (name.equals("OnSwipe")) {
                                c2 = 2;
                                break;
                            }
                            break;
                        case 793277014:
                            if (name.equals(TAG)) {
                                c2 = 0;
                                break;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                c2 = 4;
                                break;
                            }
                            break;
                    }
                    c2 = 65535;
                    switch (c2) {
                        case 0:
                            w(context, xml);
                            break;
                        case 1:
                            ArrayList<Transition> arrayList = this.e;
                            Transition transition2 = new Transition(this, context, xml);
                            arrayList.add(transition2);
                            if (this.f326c == null && !transition2.b) {
                                this.f326c = transition2;
                                if (transition2.l != null) {
                                    this.f326c.l.p(this.r);
                                }
                            }
                            if (transition2.b) {
                                if (transition2.f329c == -1) {
                                    this.f = transition2;
                                } else {
                                    this.g.add(transition2);
                                }
                                this.e.remove(transition2);
                            }
                            transition = transition2;
                            break;
                        case 2:
                            if (transition == null) {
                                Log.v(TAG, " OnSwipe (" + context.getResources().getResourceEntryName(i2) + ".xml:" + xml.getLineNumber() + ")");
                            }
                            transition.l = new d(context, this.a, xml);
                            break;
                        case 3:
                            transition.addOnClick(context, xml);
                            break;
                        case 4:
                            this.b = new StateSet(context, xml);
                            break;
                        case 5:
                            v(context, xml);
                            break;
                        case 6:
                            transition.k.add(new KeyFrames(context, xml));
                            break;
                        default:
                            Log.v(TAG, "WARNING UNKNOWN ATTRIBUTE " + name);
                            break;
                    }
                }
                eventType = xml.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }

    private void v(Context context, XmlPullParser xmlPullParser) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.setForceId(false);
        int attributeCount = xmlPullParser.getAttributeCount();
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < attributeCount; i4++) {
            String attributeName = xmlPullParser.getAttributeName(i4);
            String attributeValue = xmlPullParser.getAttributeValue(i4);
            if (this.k) {
                System.out.println("id string = " + attributeValue);
            }
            attributeName.hashCode();
            if (attributeName.equals("deriveConstraintsFrom")) {
                i3 = i(context, attributeValue);
            } else if (attributeName.equals("id")) {
                i2 = i(context, attributeValue);
                this.f328i.put(stripID(attributeValue), Integer.valueOf(i2));
            }
        }
        if (i2 != -1) {
            if (this.a.O != 0) {
                constraintSet.setValidateOnParse(true);
            }
            constraintSet.load(context, xmlPullParser);
            if (i3 != -1) {
                this.j.put(i2, i3);
            }
            this.h.put(i2, constraintSet);
        }
    }

    private void w(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.MotionScene);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R.styleable.MotionScene_defaultDuration) {
                this.l = obtainStyledAttributes.getInt(index, this.l);
            } else if (index == R.styleable.MotionScene_layoutDuringTransition) {
                this.m = obtainStyledAttributes.getInteger(index, 0);
            }
        }
        obtainStyledAttributes.recycle();
    }

    public void B(MotionLayout motionLayout) {
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            int keyAt = this.h.keyAt(i2);
            if (r(keyAt)) {
                Log.e(TAG, "Cannot be derived from yourself");
                return;
            }
            A(keyAt);
        }
        for (int i3 = 0; i3 < this.h.size(); i3++) {
            this.h.valueAt(i3).readFallback(motionLayout);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0013, code lost:
    
        if (r2 != (-1)) goto L123;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void C(int r7, int r8) {
        /*
            r6 = this;
            androidx.constraintlayout.widget.StateSet r0 = r6.b
            r1 = -1
            if (r0 == 0) goto L16
            int r0 = r0.stateGetConstraintID(r7, r1, r1)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r7
        Ld:
            androidx.constraintlayout.widget.StateSet r2 = r6.b
            int r2 = r2.stateGetConstraintID(r8, r1, r1)
            if (r2 == r1) goto L17
            goto L18
        L16:
            r0 = r7
        L17:
            r2 = r8
        L18:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r3 = r6.e
            java.util.Iterator r3 = r3.iterator()
        L1e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L58
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.MotionScene$Transition r4 = (androidx.constraintlayout.motion.widget.MotionScene.Transition) r4
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.a(r4)
            if (r5 != r2) goto L36
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.c(r4)
            if (r5 == r0) goto L42
        L36:
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.a(r4)
            if (r5 != r8) goto L1e
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.c(r4)
            if (r5 != r7) goto L1e
        L42:
            r6.f326c = r4
            if (r4 == 0) goto L57
            androidx.constraintlayout.motion.widget.d r7 = androidx.constraintlayout.motion.widget.MotionScene.Transition.m(r4)
            if (r7 == 0) goto L57
            androidx.constraintlayout.motion.widget.MotionScene$Transition r7 = r6.f326c
            androidx.constraintlayout.motion.widget.d r7 = androidx.constraintlayout.motion.widget.MotionScene.Transition.m(r7)
            boolean r8 = r6.r
            r7.p(r8)
        L57:
            return
        L58:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r7 = r6.f
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r3 = r6.g
            java.util.Iterator r3 = r3.iterator()
        L60:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L74
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.MotionScene$Transition r4 = (androidx.constraintlayout.motion.widget.MotionScene.Transition) r4
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.a(r4)
            if (r5 != r8) goto L60
            r7 = r4
            goto L60
        L74:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r8 = new androidx.constraintlayout.motion.widget.MotionScene$Transition
            r8.<init>(r6, r7)
            androidx.constraintlayout.motion.widget.MotionScene.Transition.d(r8, r0)
            androidx.constraintlayout.motion.widget.MotionScene.Transition.b(r8, r2)
            if (r0 == r1) goto L86
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionScene$Transition> r7 = r6.e
            r7.add(r8)
        L86:
            r6.f326c = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.C(int, int):void");
    }

    public void D() {
        Transition transition = this.f326c;
        if (transition == null || transition.l == null) {
            return;
        }
        this.f326c.l.r();
    }

    public boolean E() {
        Iterator<Transition> it = this.e.iterator();
        while (it.hasNext()) {
            if (it.next().l != null) {
                return true;
            }
        }
        Transition transition = this.f326c;
        return (transition == null || transition.l == null) ? false : true;
    }

    public void addOnClickListeners(MotionLayout motionLayout, int i2) {
        Iterator<Transition> it = this.e.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.m.size() > 0) {
                Iterator it2 = next.m.iterator();
                while (it2.hasNext()) {
                    ((Transition.a) it2.next()).c(motionLayout);
                }
            }
        }
        Iterator<Transition> it3 = this.g.iterator();
        while (it3.hasNext()) {
            Transition next2 = it3.next();
            if (next2.m.size() > 0) {
                Iterator it4 = next2.m.iterator();
                while (it4.hasNext()) {
                    ((Transition.a) it4.next()).c(motionLayout);
                }
            }
        }
        Iterator<Transition> it5 = this.e.iterator();
        while (it5.hasNext()) {
            Transition next3 = it5.next();
            if (next3.m.size() > 0) {
                Iterator it6 = next3.m.iterator();
                while (it6.hasNext()) {
                    ((Transition.a) it6.next()).a(motionLayout, i2, next3);
                }
            }
        }
        Iterator<Transition> it7 = this.g.iterator();
        while (it7.hasNext()) {
            Transition next4 = it7.next();
            if (next4.m.size() > 0) {
                Iterator it8 = next4.m.iterator();
                while (it8.hasNext()) {
                    ((Transition.a) it8.next()).a(motionLayout, i2, next4);
                }
            }
        }
    }

    public void addTransition(Transition transition) {
        int j = j(transition);
        if (j == -1) {
            this.e.add(transition);
        } else {
            this.e.set(j, transition);
        }
    }

    public Transition bestTransitionFor(int i2, float f, float f2, MotionEvent motionEvent) {
        if (i2 == -1) {
            return this.f326c;
        }
        List<Transition> transitionsWithState = getTransitionsWithState(i2);
        float f3 = 0.0f;
        Transition transition = null;
        RectF rectF = new RectF();
        for (Transition transition2 : transitionsWithState) {
            if (!transition2.o && transition2.l != null) {
                transition2.l.p(this.r);
                RectF j = transition2.l.j(this.a, rectF);
                if (j == null || motionEvent == null || j.contains(motionEvent.getX(), motionEvent.getY())) {
                    RectF j2 = transition2.l.j(this.a, rectF);
                    if (j2 == null || motionEvent == null || j2.contains(motionEvent.getX(), motionEvent.getY())) {
                        float a2 = transition2.l.a(f, f2) * (transition2.f329c == i2 ? -1.0f : 1.1f);
                        if (a2 > f3) {
                            transition = transition2;
                            f3 = a2;
                        }
                    }
                }
            }
        }
        return transition;
    }

    public void disableAutoTransition(boolean z) {
        this.f327d = z;
    }

    public boolean e(MotionLayout motionLayout, int i2) {
        if (t() || this.f327d) {
            return false;
        }
        Iterator<Transition> it = this.e.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.n != 0 && this.f326c != next) {
                if (i2 == next.f330d && (next.n == 4 || next.n == 2)) {
                    MotionLayout.h hVar = MotionLayout.h.FINISHED;
                    motionLayout.setState(hVar);
                    motionLayout.setTransition(next);
                    if (next.n == 4) {
                        motionLayout.transitionToEnd();
                        motionLayout.setState(MotionLayout.h.SETUP);
                        motionLayout.setState(MotionLayout.h.MOVING);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.P(true);
                        motionLayout.setState(MotionLayout.h.SETUP);
                        motionLayout.setState(MotionLayout.h.MOVING);
                        motionLayout.setState(hVar);
                        motionLayout.Z();
                    }
                    return true;
                }
                if (i2 == next.f329c && (next.n == 3 || next.n == 1)) {
                    MotionLayout.h hVar2 = MotionLayout.h.FINISHED;
                    motionLayout.setState(hVar2);
                    motionLayout.setTransition(next);
                    if (next.n == 3) {
                        motionLayout.transitionToStart();
                        motionLayout.setState(MotionLayout.h.SETUP);
                        motionLayout.setState(MotionLayout.h.MOVING);
                    } else {
                        motionLayout.setProgress(0.0f);
                        motionLayout.P(true);
                        motionLayout.setState(MotionLayout.h.SETUP);
                        motionLayout.setState(MotionLayout.h.MOVING);
                        motionLayout.setState(hVar2);
                        motionLayout.Z();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public ConstraintSet f(int i2) {
        return g(i2, -1, -1);
    }

    ConstraintSet g(int i2, int i3, int i4) {
        ConstraintSet constraintSet;
        int stateGetConstraintID;
        if (this.k) {
            System.out.println("id " + i2);
            System.out.println("size " + this.h.size());
        }
        StateSet stateSet = this.b;
        if (stateSet != null && (stateGetConstraintID = stateSet.stateGetConstraintID(i2, i3, i4)) != -1) {
            i2 = stateGetConstraintID;
        }
        if (this.h.get(i2) == null) {
            Log.e(TAG, "Warning could not find ConstraintSet id/" + Debug.getName(this.a.getContext(), i2) + " In MotionScene");
            SparseArray<ConstraintSet> sparseArray = this.h;
            constraintSet = sparseArray.get(sparseArray.keyAt(0));
        } else {
            constraintSet = this.h.get(i2);
        }
        return constraintSet;
    }

    public int gatPathMotionArc() {
        Transition transition = this.f326c;
        if (transition != null) {
            return transition.p;
        }
        return -1;
    }

    public ConstraintSet getConstraintSet(Context context, String str) {
        if (this.k) {
            System.out.println("id " + str);
            System.out.println("size " + this.h.size());
        }
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            int keyAt = this.h.keyAt(i2);
            String resourceName = context.getResources().getResourceName(keyAt);
            if (this.k) {
                System.out.println("Id for <" + i2 + "> is <" + resourceName + "> looking for <" + str + ">");
            }
            if (str.equals(resourceName)) {
                return this.h.get(keyAt);
            }
        }
        return null;
    }

    public int[] getConstraintSetIds() {
        int size = this.h.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = this.h.keyAt(i2);
        }
        return iArr;
    }

    public ArrayList<Transition> getDefinedTransitions() {
        return this.e;
    }

    public int getDuration() {
        Transition transition = this.f326c;
        return transition != null ? transition.h : this.l;
    }

    public Interpolator getInterpolator() {
        int i2 = this.f326c.e;
        if (i2 == -2) {
            return AnimationUtils.loadInterpolator(this.a.getContext(), this.f326c.g);
        }
        if (i2 == -1) {
            return new a(this, Easing.getInterpolator(this.f326c.f));
        }
        if (i2 == 0) {
            return new AccelerateDecelerateInterpolator();
        }
        if (i2 == 1) {
            return new AccelerateInterpolator();
        }
        if (i2 == 2) {
            return new DecelerateInterpolator();
        }
        if (i2 == 4) {
            return new AnticipateInterpolator();
        }
        if (i2 != 5) {
            return null;
        }
        return new BounceInterpolator();
    }

    public void getKeyFrames(MotionController motionController) {
        Transition transition = this.f326c;
        if (transition != null) {
            Iterator it = transition.k.iterator();
            while (it.hasNext()) {
                ((KeyFrames) it.next()).addFrames(motionController);
            }
        } else {
            Transition transition2 = this.f;
            if (transition2 != null) {
                Iterator it2 = transition2.k.iterator();
                while (it2.hasNext()) {
                    ((KeyFrames) it2.next()).addFrames(motionController);
                }
            }
        }
    }

    public float getPathPercent(View view, int i2) {
        return 0.0f;
    }

    public float getStaggered() {
        Transition transition = this.f326c;
        if (transition != null) {
            return transition.f331i;
        }
        return 0.0f;
    }

    public Transition getTransitionById(int i2) {
        Iterator<Transition> it = this.e.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.a == i2) {
                return next;
            }
        }
        return null;
    }

    public List<Transition> getTransitionsWithState(int i2) {
        int p = p(i2);
        ArrayList arrayList = new ArrayList();
        Iterator<Transition> it = this.e.iterator();
        while (it.hasNext()) {
            Transition next = it.next();
            if (next.f330d == p || next.f329c == p) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public int h() {
        Transition transition = this.f326c;
        if (transition == null) {
            return -1;
        }
        return transition.f329c;
    }

    public Key k(Context context, int i2, int i3, int i4) {
        Transition transition = this.f326c;
        if (transition == null) {
            return null;
        }
        Iterator it = transition.k.iterator();
        while (it.hasNext()) {
            KeyFrames keyFrames = (KeyFrames) it.next();
            for (Integer num : keyFrames.getKeys()) {
                if (i3 == num.intValue()) {
                    Iterator<Key> it2 = keyFrames.getKeyFramesForView(num.intValue()).iterator();
                    while (it2.hasNext()) {
                        Key next = it2.next();
                        if (next.a == i4 && next.f300d == i2) {
                            return next;
                        }
                    }
                }
            }
        }
        return null;
    }

    public float l() {
        Transition transition = this.f326c;
        if (transition == null || transition.l == null) {
            return 0.0f;
        }
        return this.f326c.l.f();
    }

    public int lookUpConstraintId(String str) {
        return this.f328i.get(str).intValue();
    }

    public String lookUpConstraintName(int i2) {
        for (Map.Entry<String, Integer> entry : this.f328i.entrySet()) {
            if (entry.getValue().intValue() == i2) {
                return entry.getKey();
            }
        }
        return null;
    }

    public float m() {
        Transition transition = this.f326c;
        if (transition == null || transition.l == null) {
            return 0.0f;
        }
        return this.f326c.l.g();
    }

    public boolean n() {
        Transition transition = this.f326c;
        if (transition == null || transition.l == null) {
            return false;
        }
        return this.f326c.l.h();
    }

    public float o(float f, float f2) {
        Transition transition = this.f326c;
        if (transition == null || transition.l == null) {
            return 0.0f;
        }
        return this.f326c.l.i(f, f2);
    }

    public int q() {
        Transition transition = this.f326c;
        if (transition == null) {
            return -1;
        }
        return transition.f330d;
    }

    public void removeTransition(Transition transition) {
        int j = j(transition);
        if (j != -1) {
            this.e.remove(j);
        }
    }

    public boolean s(View view, int i2) {
        Transition transition = this.f326c;
        if (transition == null) {
            return false;
        }
        Iterator it = transition.k.iterator();
        while (it.hasNext()) {
            Iterator<Key> it2 = ((KeyFrames) it.next()).getKeyFramesForView(view.getId()).iterator();
            while (it2.hasNext()) {
                if (it2.next().a == i2) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setConstraintSet(int i2, ConstraintSet constraintSet) {
        this.h.put(i2, constraintSet);
    }

    public void setDuration(int i2) {
        Transition transition = this.f326c;
        if (transition != null) {
            transition.setDuration(i2);
        } else {
            this.l = i2;
        }
    }

    public void setKeyframe(View view, int i2, String str, Object obj) {
        Transition transition = this.f326c;
        if (transition == null) {
            return;
        }
        Iterator it = transition.k.iterator();
        while (it.hasNext()) {
            Iterator<Key> it2 = ((KeyFrames) it.next()).getKeyFramesForView(view.getId()).iterator();
            while (it2.hasNext()) {
                if (it2.next().a == i2) {
                    int i3 = ((obj != null ? ((Float) obj).floatValue() : 0.0f) > 0.0f ? 1 : ((obj != null ? ((Float) obj).floatValue() : 0.0f) == 0.0f ? 0 : -1));
                    str.equalsIgnoreCase("app:PerpendicularPath_percent");
                }
            }
        }
    }

    public void setRtl(boolean z) {
        this.r = z;
        Transition transition = this.f326c;
        if (transition == null || transition.l == null) {
            return;
        }
        this.f326c.l.p(this.r);
    }

    public void setTransition(Transition transition) {
        this.f326c = transition;
        if (transition == null || transition.l == null) {
            return;
        }
        this.f326c.l.p(this.r);
    }

    public boolean validateLayout(MotionLayout motionLayout) {
        return motionLayout == this.a && motionLayout.t == this;
    }

    public void x(float f, float f2) {
        Transition transition = this.f326c;
        if (transition == null || transition.l == null) {
            return;
        }
        this.f326c.l.m(f, f2);
    }

    public void y(float f, float f2) {
        Transition transition = this.f326c;
        if (transition == null || transition.l == null) {
            return;
        }
        this.f326c.l.n(f, f2);
    }

    public void z(MotionEvent motionEvent, int i2, MotionLayout motionLayout) {
        MotionLayout.MotionTracker motionTracker;
        MotionEvent motionEvent2;
        RectF rectF = new RectF();
        if (this.q == null) {
            this.q = this.a.Y();
        }
        this.q.addMovement(motionEvent);
        if (i2 != -1) {
            int action = motionEvent.getAction();
            boolean z = false;
            if (action == 0) {
                this.s = motionEvent.getRawX();
                this.t = motionEvent.getRawY();
                this.n = motionEvent;
                this.o = false;
                if (this.f326c.l != null) {
                    RectF e = this.f326c.l.e(this.a, rectF);
                    if (e != null && !e.contains(this.n.getX(), this.n.getY())) {
                        this.n = null;
                        this.o = true;
                        return;
                    }
                    RectF j = this.f326c.l.j(this.a, rectF);
                    if (j == null || j.contains(this.n.getX(), this.n.getY())) {
                        this.p = false;
                    } else {
                        this.p = true;
                    }
                    this.f326c.l.o(this.s, this.t);
                    return;
                }
                return;
            }
            if (action == 2 && !this.o) {
                float rawY = motionEvent.getRawY() - this.t;
                float rawX = motionEvent.getRawX() - this.s;
                if ((rawX == 0.0d && rawY == 0.0d) || (motionEvent2 = this.n) == null) {
                    return;
                }
                Transition bestTransitionFor = bestTransitionFor(i2, rawX, rawY, motionEvent2);
                if (bestTransitionFor != null) {
                    motionLayout.setTransition(bestTransitionFor);
                    RectF j2 = this.f326c.l.j(this.a, rectF);
                    if (j2 != null && !j2.contains(this.n.getX(), this.n.getY())) {
                        z = true;
                    }
                    this.p = z;
                    this.f326c.l.q(this.s, this.t);
                }
            }
        }
        if (this.o) {
            return;
        }
        Transition transition = this.f326c;
        if (transition != null && transition.l != null && !this.p) {
            this.f326c.l.l(motionEvent, this.q, i2, this);
        }
        this.s = motionEvent.getRawX();
        this.t = motionEvent.getRawY();
        if (motionEvent.getAction() != 1 || (motionTracker = this.q) == null) {
            return;
        }
        motionTracker.recycle();
        this.q = null;
        int i3 = motionLayout.x;
        if (i3 != -1) {
            e(motionLayout, i3);
        }
    }
}
