package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class KeyFrames {
    public static final int UNSET = -1;
    static HashMap<String, Constructor<? extends Key>> b;
    private HashMap<Integer, ArrayList<Key>> a = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends Key>> hashMap = new HashMap<>();
        b = hashMap;
        try {
            hashMap.put("KeyAttribute", KeyAttributes.class.getConstructor(new Class[0]));
            b.put("KeyPosition", KeyPosition.class.getConstructor(new Class[0]));
            b.put("KeyCycle", KeyCycle.class.getConstructor(new Class[0]));
            b.put("KeyTimeCycle", KeyTimeCycle.class.getConstructor(new Class[0]));
            b.put("KeyTrigger", KeyTrigger.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e) {
            Log.e("KeyFrames", "unable to load", e);
        }
    }

    public KeyFrames(Context context, XmlPullParser xmlPullParser) {
        Key key;
        Exception e;
        HashMap<String, ConstraintAttribute> hashMap;
        Key key2 = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    if (b.containsKey(name)) {
                        try {
                            key = b.get(name).newInstance(new Object[0]);
                            try {
                                key.load(context, Xml.asAttributeSet(xmlPullParser));
                                a(key);
                            } catch (Exception e2) {
                                e = e2;
                                Log.e("KeyFrames", "unable to create ", e);
                                key2 = key;
                                eventType = xmlPullParser.next();
                            }
                        } catch (Exception e3) {
                            key = key2;
                            e = e3;
                        }
                        key2 = key;
                    } else if (name.equalsIgnoreCase("CustomAttribute") && key2 != null && (hashMap = key2.e) != null) {
                        ConstraintAttribute.parse(context, xmlPullParser, hashMap);
                    }
                } else if (eventType == 3 && "KeyFrameSet".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        } catch (XmlPullParserException e5) {
            e5.printStackTrace();
        }
    }

    private void a(Key key) {
        if (!this.a.containsKey(Integer.valueOf(key.b))) {
            this.a.put(Integer.valueOf(key.b), new ArrayList<>());
        }
        this.a.get(Integer.valueOf(key.b)).add(key);
    }

    public void addFrames(MotionController motionController) {
        ArrayList<Key> arrayList = this.a.get(Integer.valueOf(motionController.b));
        if (arrayList != null) {
            motionController.b(arrayList);
        }
        ArrayList<Key> arrayList2 = this.a.get(-1);
        if (arrayList2 != null) {
            Iterator<Key> it = arrayList2.iterator();
            while (it.hasNext()) {
                Key next = it.next();
                if (next.a(((ConstraintLayout.LayoutParams) motionController.a.getLayoutParams()).constraintTag)) {
                    motionController.a(next);
                }
            }
        }
    }

    public ArrayList<Key> getKeyFramesForView(int i2) {
        return this.a.get(Integer.valueOf(i2));
    }

    public Set<Integer> getKeys() {
        return this.a.keySet();
    }
}
