package android.support.transition;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.annotation.NonNull;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.ViewGroup;
import java.io.IOException;
import java.lang.reflect.Constructor;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: lib/Mus.dex */
public class TransitionInflater {
    private final Context mContext;
    private static final Class<?>[] CONSTRUCTOR_SIGNATURE = {Context.class, AttributeSet.class};
    private static final ArrayMap<String, Constructor> CONSTRUCTORS = new ArrayMap<>();

    private TransitionInflater(@NonNull Context context) {
        this.mContext = context;
    }

    public static TransitionInflater from(Context context) {
        return new TransitionInflater(context);
    }

    public Transition inflateTransition(int i2) {
        XmlResourceParser xml = this.mContext.getResources().getXml(i2);
        try {
            try {
                try {
                    return createTransitionFromXml(xml, Xml.asAttributeSet(xml), null);
                } catch (IOException e) {
                    throw new InflateException(xml.getPositionDescription() + ": " + e.getMessage(), e);
                }
            } catch (XmlPullParserException e2) {
                throw new InflateException(e2.getMessage(), e2);
            }
        } finally {
            xml.close();
        }
    }

    public TransitionManager inflateTransitionManager(int i2, ViewGroup viewGroup) {
        XmlResourceParser xml = this.mContext.getResources().getXml(i2);
        try {
            try {
                return createTransitionManagerFromXml(xml, Xml.asAttributeSet(xml), viewGroup);
            } catch (IOException e) {
                InflateException inflateException = new InflateException(xml.getPositionDescription() + ": " + e.getMessage());
                inflateException.initCause(e);
                throw inflateException;
            } catch (XmlPullParserException e2) {
                InflateException inflateException2 = new InflateException(e2.getMessage());
                inflateException2.initCause(e2);
                throw inflateException2;
            }
        } finally {
            xml.close();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x021e, code lost:
    
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.support.transition.Transition createTransitionFromXml(org.xmlpull.v1.XmlPullParser r16, android.util.AttributeSet r17, android.support.transition.Transition r18) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 543
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.TransitionInflater.createTransitionFromXml(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.support.transition.Transition):android.support.transition.Transition");
    }

    private Object createCustom(AttributeSet attributeSet, Class cls, String str) {
        Object newInstance;
        Class<? extends U> asSubclass;
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        if (attributeValue == null) {
            throw new InflateException(str + " tag must have a 'class' attribute");
        }
        try {
            synchronized (CONSTRUCTORS) {
                Constructor constructor = CONSTRUCTORS.get(attributeValue);
                if (constructor == null && (asSubclass = this.mContext.getClassLoader().loadClass(attributeValue).asSubclass(cls)) != 0) {
                    constructor = asSubclass.getConstructor(CONSTRUCTOR_SIGNATURE);
                    constructor.setAccessible(true);
                    CONSTRUCTORS.put(attributeValue, constructor);
                }
                newInstance = constructor.newInstance(this.mContext, attributeSet);
            }
            return newInstance;
        } catch (Exception e) {
            throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e);
        }
    }

    private void getTargetIds(XmlPullParser xmlPullParser, AttributeSet attributeSet, Transition transition) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if ((next != 3 || xmlPullParser.getDepth() > depth) && next != 1) {
                if (next == 2) {
                    if (xmlPullParser.getName().equals("target")) {
                        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, Styleable.TRANSITION_TARGET);
                        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "targetId", 1, 0);
                        if (namedResourceId != 0) {
                            transition.addTarget(namedResourceId);
                        } else {
                            int namedResourceId2 = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "excludeId", 2, 0);
                            if (namedResourceId2 != 0) {
                                transition.excludeTarget(namedResourceId2, true);
                            } else {
                                String namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "targetName", 4);
                                if (namedString != null) {
                                    transition.addTarget(namedString);
                                } else {
                                    String namedString2 = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "excludeName", 5);
                                    if (namedString2 != null) {
                                        transition.excludeTarget(namedString2, true);
                                    } else {
                                        String namedString3 = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "excludeClass", 3);
                                        if (namedString3 != null) {
                                            try {
                                                transition.excludeTarget((Class) Class.forName(namedString3), true);
                                            } catch (ClassNotFoundException e) {
                                                obtainStyledAttributes.recycle();
                                                throw new RuntimeException("Could not create " + namedString3, e);
                                            }
                                        } else {
                                            String namedString4 = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "targetClass", 0);
                                            if (namedString4 != null) {
                                                transition.addTarget(Class.forName(namedString4));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        obtainStyledAttributes.recycle();
                    } else {
                        throw new RuntimeException("Unknown scene name: " + xmlPullParser.getName());
                    }
                }
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x007f, code lost:
    
        return r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.support.transition.TransitionManager createTransitionManagerFromXml(org.xmlpull.v1.XmlPullParser r15, android.util.AttributeSet r16, android.view.ViewGroup r17) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r8 = r1
            int r8 = r8.getDepth()
            r5 = r8
            r8 = 0
            r6 = r8
        Le:
            r8 = r1
            int r8 = r8.next()
            r13 = r8
            r8 = r13
            r9 = r13
            r4 = r9
            r9 = 3
            if (r8 != r9) goto L22
            r8 = r1
            int r8 = r8.getDepth()
            r9 = r5
            if (r8 <= r9) goto L7d
        L22:
            r8 = r4
            r9 = 1
            if (r8 == r9) goto L7d
            r8 = r4
            r9 = 2
            if (r8 == r9) goto L2b
            goto Le
        L2b:
            r8 = r1
            java.lang.String r8 = r8.getName()
            r7 = r8
            r8 = r7
            java.lang.String r9 = "transitionManager"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L44
            android.support.transition.TransitionManager r8 = new android.support.transition.TransitionManager
            r13 = r8
            r8 = r13
            r9 = r13
            r9.<init>()
            r6 = r8
        L43:
            goto Le
        L44:
            r8 = r7
            java.lang.String r9 = "transition"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L59
            r8 = r6
            if (r8 == 0) goto L59
            r8 = r0
            r9 = r2
            r10 = r1
            r11 = r3
            r12 = r6
            r8.loadTransition(r9, r10, r11, r12)
            goto L43
        L59:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            r13 = r8
            r8 = r13
            r9 = r13
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r13 = r10
            r10 = r13
            r11 = r13
            r11.<init>()
            java.lang.String r11 = "Unknown scene name: "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r1
            java.lang.String r11 = r11.getName()
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r8
        L7d:
            r8 = r6
            r0 = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.TransitionInflater.createTransitionManagerFromXml(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.ViewGroup):android.support.transition.TransitionManager");
    }

    private void loadTransition(AttributeSet attributeSet, XmlPullParser xmlPullParser, ViewGroup viewGroup, TransitionManager transitionManager) throws Resources.NotFoundException {
        Transition inflateTransition;
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, Styleable.TRANSITION_MANAGER);
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "transition", 2, -1);
        int namedResourceId2 = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "fromScene", 0, -1);
        Scene sceneForLayout = namedResourceId2 < 0 ? null : Scene.getSceneForLayout(viewGroup, namedResourceId2, this.mContext);
        int namedResourceId3 = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "toScene", 1, -1);
        Scene sceneForLayout2 = namedResourceId3 < 0 ? null : Scene.getSceneForLayout(viewGroup, namedResourceId3, this.mContext);
        if (namedResourceId >= 0 && (inflateTransition = inflateTransition(namedResourceId)) != null) {
            if (sceneForLayout2 == null) {
                throw new RuntimeException("No toScene for transition ID " + namedResourceId);
            }
            if (sceneForLayout == null) {
                transitionManager.setTransition(sceneForLayout2, inflateTransition);
            } else {
                transitionManager.setTransition(sceneForLayout, sceneForLayout2, inflateTransition);
            }
        }
        obtainStyledAttributes.recycle();
    }
}
