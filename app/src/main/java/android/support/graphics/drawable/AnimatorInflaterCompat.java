package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.AnimatorRes;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: lib/Mus.dex */
public class AnimatorInflaterCompat {
    private static final boolean DBG_ANIMATOR_INFLATER = false;
    private static final int MAX_NUM_POINTS = 100;
    private static final String TAG = "AnimatorInflater";
    private static final int TOGETHER = 0;
    private static final int VALUE_TYPE_COLOR = 3;
    private static final int VALUE_TYPE_FLOAT = 0;
    private static final int VALUE_TYPE_INT = 1;
    private static final int VALUE_TYPE_PATH = 2;
    private static final int VALUE_TYPE_UNDEFINED = 4;

    public static Animator loadAnimator(Context context, @AnimatorRes int i2) throws Resources.NotFoundException {
        Animator loadAnimator;
        if (Build.VERSION.SDK_INT >= 24) {
            loadAnimator = AnimatorInflater.loadAnimator(context, i2);
        } else {
            loadAnimator = loadAnimator(context, context.getResources(), context.getTheme(), i2);
        }
        return loadAnimator;
    }

    public static Animator loadAnimator(Context context, Resources resources, Resources.Theme theme, @AnimatorRes int i2) throws Resources.NotFoundException {
        return loadAnimator(context, resources, theme, i2, 1.0f);
    }

    public static Animator loadAnimator(Context context, Resources resources, Resources.Theme theme, @AnimatorRes int i2, float f) throws Resources.NotFoundException {
        XmlResourceParser xmlResourceParser = null;
        try {
            try {
                xmlResourceParser = resources.getAnimation(i2);
                Animator createAnimatorFromXml = createAnimatorFromXml(context, resources, theme, xmlResourceParser, f);
                if (xmlResourceParser != null) {
                    xmlResourceParser.close();
                }
                return createAnimatorFromXml;
            } catch (IOException e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i2));
                notFoundException.initCause(e);
                throw notFoundException;
            } catch (XmlPullParserException e2) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i2));
                notFoundException2.initCause(e2);
                throw notFoundException2;
            }
        } catch (Throwable th) {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: lib/Mus.dex */
    public static class PathDataEvaluator implements TypeEvaluator<PathParser.PathDataNode[]> {
        private PathParser.PathDataNode[] mNodeArray;

        private PathDataEvaluator() {
        }

        PathDataEvaluator(PathParser.PathDataNode[] pathDataNodeArr) {
            this.mNodeArray = pathDataNodeArr;
        }

        @Override // android.animation.TypeEvaluator
        public PathParser.PathDataNode[] evaluate(float f, PathParser.PathDataNode[] pathDataNodeArr, PathParser.PathDataNode[] pathDataNodeArr2) {
            if (!PathParser.canMorph(pathDataNodeArr, pathDataNodeArr2)) {
                throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
            }
            if (this.mNodeArray == null || !PathParser.canMorph(this.mNodeArray, pathDataNodeArr)) {
                this.mNodeArray = PathParser.deepCopyNodes(pathDataNodeArr);
            }
            for (int i2 = 0; i2 < pathDataNodeArr.length; i2++) {
                this.mNodeArray[i2].interpolatePathDataNode(pathDataNodeArr[i2], pathDataNodeArr2[i2], f);
            }
            return this.mNodeArray;
        }
    }

    private static PropertyValuesHolder getPVH(TypedArray typedArray, int i2, int i3, int i4, String str) {
        int i5;
        int i6;
        int i7;
        float f;
        float f2;
        float f3;
        int i8 = i2;
        TypedValue peekValue = typedArray.peekValue(i3);
        boolean z = peekValue != null;
        int i9 = z ? peekValue.type : 0;
        TypedValue peekValue2 = typedArray.peekValue(i4);
        boolean z2 = peekValue2 != null;
        int i10 = z2 ? peekValue2.type : 0;
        if (i8 == 4) {
            if ((z && isColorType(i9)) || (z2 && isColorType(i10))) {
                i8 = 3;
            } else {
                i8 = 0;
            }
        }
        boolean z3 = i8 == 0;
        PropertyValuesHolder propertyValuesHolder = null;
        if (i8 == 2) {
            String string = typedArray.getString(i3);
            String string2 = typedArray.getString(i4);
            PathParser.PathDataNode[] createNodesFromPathData = PathParser.createNodesFromPathData(string);
            PathParser.PathDataNode[] createNodesFromPathData2 = PathParser.createNodesFromPathData(string2);
            if (createNodesFromPathData != null || createNodesFromPathData2 != null) {
                if (createNodesFromPathData != null) {
                    PathDataEvaluator pathDataEvaluator = new PathDataEvaluator();
                    if (createNodesFromPathData2 != null) {
                        if (!PathParser.canMorph(createNodesFromPathData, createNodesFromPathData2)) {
                            throw new InflateException(" Can't morph from " + string + " to " + string2);
                        }
                        propertyValuesHolder = PropertyValuesHolder.ofObject(str, pathDataEvaluator, createNodesFromPathData, createNodesFromPathData2);
                    } else {
                        propertyValuesHolder = PropertyValuesHolder.ofObject(str, pathDataEvaluator, createNodesFromPathData);
                    }
                } else if (createNodesFromPathData2 != null) {
                    propertyValuesHolder = PropertyValuesHolder.ofObject(str, new PathDataEvaluator(), createNodesFromPathData2);
                }
            }
        } else {
            ArgbEvaluator argbEvaluator = null;
            if (i8 == 3) {
                argbEvaluator = ArgbEvaluator.getInstance();
            }
            if (z3) {
                if (z) {
                    if (i9 == 5) {
                        f2 = typedArray.getDimension(i3, 0.0f);
                    } else {
                        f2 = typedArray.getFloat(i3, 0.0f);
                    }
                    if (z2) {
                        if (i10 == 5) {
                            f3 = typedArray.getDimension(i4, 0.0f);
                        } else {
                            f3 = typedArray.getFloat(i4, 0.0f);
                        }
                        propertyValuesHolder = PropertyValuesHolder.ofFloat(str, f2, f3);
                    } else {
                        propertyValuesHolder = PropertyValuesHolder.ofFloat(str, f2);
                    }
                } else {
                    if (i10 == 5) {
                        f = typedArray.getDimension(i4, 0.0f);
                    } else {
                        f = typedArray.getFloat(i4, 0.0f);
                    }
                    propertyValuesHolder = PropertyValuesHolder.ofFloat(str, f);
                }
            } else if (z) {
                if (i9 == 5) {
                    i6 = (int) typedArray.getDimension(i3, 0.0f);
                } else if (isColorType(i9)) {
                    i6 = typedArray.getColor(i3, 0);
                } else {
                    i6 = typedArray.getInt(i3, 0);
                }
                if (z2) {
                    if (i10 == 5) {
                        i7 = (int) typedArray.getDimension(i4, 0.0f);
                    } else if (isColorType(i10)) {
                        i7 = typedArray.getColor(i4, 0);
                    } else {
                        i7 = typedArray.getInt(i4, 0);
                    }
                    propertyValuesHolder = PropertyValuesHolder.ofInt(str, i6, i7);
                } else {
                    propertyValuesHolder = PropertyValuesHolder.ofInt(str, i6);
                }
            } else if (z2) {
                if (i10 == 5) {
                    i5 = (int) typedArray.getDimension(i4, 0.0f);
                } else if (isColorType(i10)) {
                    i5 = typedArray.getColor(i4, 0);
                } else {
                    i5 = typedArray.getInt(i4, 0);
                }
                propertyValuesHolder = PropertyValuesHolder.ofInt(str, i5);
            }
            if (propertyValuesHolder != null && argbEvaluator != null) {
                propertyValuesHolder.setEvaluator(argbEvaluator);
            }
        }
        return propertyValuesHolder;
    }

    private static void parseAnimatorFromTypeArray(ValueAnimator valueAnimator, TypedArray typedArray, TypedArray typedArray2, float f, XmlPullParser xmlPullParser) {
        long namedInt = TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "duration", 1, 300);
        long namedInt2 = TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "startOffset", 2, 0);
        int namedInt3 = TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "valueType", 7, 4);
        if (TypedArrayUtils.hasAttribute(xmlPullParser, "valueFrom") && TypedArrayUtils.hasAttribute(xmlPullParser, "valueTo")) {
            if (namedInt3 == 4) {
                namedInt3 = inferValueTypeFromValues(typedArray, 5, 6);
            }
            PropertyValuesHolder pvh = getPVH(typedArray, namedInt3, 5, 6, "");
            if (pvh != null) {
                valueAnimator.setValues(pvh);
            }
        }
        valueAnimator.setDuration(namedInt);
        valueAnimator.setStartDelay(namedInt2);
        valueAnimator.setRepeatCount(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "repeatCount", 3, 0));
        valueAnimator.setRepeatMode(TypedArrayUtils.getNamedInt(typedArray, xmlPullParser, "repeatMode", 4, 1));
        if (typedArray2 != null) {
            setupObjectAnimator(valueAnimator, typedArray2, namedInt3, f, xmlPullParser);
        }
    }

    private static void setupObjectAnimator(ValueAnimator valueAnimator, TypedArray typedArray, int i2, float f, XmlPullParser xmlPullParser) {
        ObjectAnimator objectAnimator = (ObjectAnimator) valueAnimator;
        String namedString = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "pathData", 1);
        if (namedString != null) {
            String namedString2 = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyXName", 2);
            String namedString3 = TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyYName", 3);
            if (i2 == 2 || i2 == 4) {
            }
            if (namedString2 == null && namedString3 == null) {
                throw new InflateException(typedArray.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
            }
            setupPathMotion(PathParser.createPathFromPathData(namedString), objectAnimator, 0.5f * f, namedString2, namedString3);
            return;
        }
        objectAnimator.setPropertyName(TypedArrayUtils.getNamedString(typedArray, xmlPullParser, "propertyName", 0));
    }

    private static void setupPathMotion(Path path, ObjectAnimator objectAnimator, float f, String str, String str2) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float f2 = 0.0f;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Float.valueOf(0.0f));
        do {
            f2 += pathMeasure.getLength();
            arrayList.add(Float.valueOf(f2));
        } while (pathMeasure.nextContour());
        PathMeasure pathMeasure2 = new PathMeasure(path, false);
        int min = Math.min(100, ((int) (f2 / f)) + 1);
        float[] fArr = new float[min];
        float[] fArr2 = new float[min];
        float[] fArr3 = new float[2];
        int i2 = 0;
        float f3 = f2 / (min - 1);
        float f4 = 0.0f;
        for (int i3 = 0; i3 < min; i3++) {
            pathMeasure2.getPosTan(f4, fArr3, null);
            fArr[i3] = fArr3[0];
            fArr2[i3] = fArr3[1];
            f4 += f3;
            if (i2 + 1 < arrayList.size() && f4 > ((Float) arrayList.get(i2 + 1)).floatValue()) {
                f4 -= ((Float) arrayList.get(i2 + 1)).floatValue();
                i2++;
                pathMeasure2.nextContour();
            }
        }
        PropertyValuesHolder propertyValuesHolder = null;
        PropertyValuesHolder propertyValuesHolder2 = null;
        if (str != null) {
            propertyValuesHolder = PropertyValuesHolder.ofFloat(str, fArr);
        }
        if (str2 != null) {
            propertyValuesHolder2 = PropertyValuesHolder.ofFloat(str2, fArr2);
        }
        if (propertyValuesHolder == null) {
            objectAnimator.setValues(propertyValuesHolder2);
        } else if (propertyValuesHolder2 == null) {
            objectAnimator.setValues(propertyValuesHolder);
        } else {
            objectAnimator.setValues(propertyValuesHolder, propertyValuesHolder2);
        }
    }

    private static Animator createAnimatorFromXml(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, float f) throws XmlPullParserException, IOException {
        return createAnimatorFromXml(context, resources, theme, xmlPullParser, Xml.asAttributeSet(xmlPullParser), null, 0, f);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0185, code lost:
    
        if (r11 == null) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0187, code lost:
    
        r0 = new android.animation.Animator[r11.size()];
        r15 = 0;
        r18 = r11.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x01a7, code lost:
    
        if (r18.hasNext() == false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x01a9, code lost:
    
        r19 = r15;
        r15 = r15 + 1;
        r0[r19] = (android.animation.Animator) r18.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x01c0, code lost:
    
        if (r33 != 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x01c2, code lost:
    
        r32.playTogether(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x01ce, code lost:
    
        r32.playSequentially(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x01cd, code lost:
    
        return r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0181, code lost:
    
        if (r32 == null) goto L53;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.animation.Animator createAnimatorFromXml(android.content.Context r27, android.content.res.Resources r28, android.content.res.Resources.Theme r29, org.xmlpull.v1.XmlPullParser r30, android.util.AttributeSet r31, android.animation.AnimatorSet r32, int r33, float r34) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 470
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.AnimatorInflaterCompat.createAnimatorFromXml(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.animation.AnimatorSet, int, float):android.animation.Animator");
    }

    private static PropertyValuesHolder[] loadValues(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        ArrayList arrayList = null;
        while (true) {
            int eventType = xmlPullParser.getEventType();
            if (eventType == 3 || eventType == 1) {
                break;
            }
            if (eventType != 2) {
                xmlPullParser.next();
            } else {
                if (xmlPullParser.getName().equals("propertyValuesHolder")) {
                    TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
                    String namedString = TypedArrayUtils.getNamedString(obtainAttributes, xmlPullParser, "propertyName", 3);
                    int namedInt = TypedArrayUtils.getNamedInt(obtainAttributes, xmlPullParser, "valueType", 2, 4);
                    PropertyValuesHolder loadPvh = loadPvh(context, resources, theme, xmlPullParser, namedString, namedInt);
                    if (loadPvh == null) {
                        loadPvh = getPVH(obtainAttributes, namedInt, 0, 1, namedString);
                    }
                    if (loadPvh != null) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(loadPvh);
                    }
                    obtainAttributes.recycle();
                }
                xmlPullParser.next();
            }
        }
        PropertyValuesHolder[] propertyValuesHolderArr = null;
        if (arrayList != null) {
            int size = arrayList.size();
            propertyValuesHolderArr = new PropertyValuesHolder[size];
            for (int i2 = 0; i2 < size; i2++) {
                propertyValuesHolderArr[i2] = (PropertyValuesHolder) arrayList.get(i2);
            }
        }
        return propertyValuesHolderArr;
    }

    private static int inferValueTypeOfKeyframe(Resources resources, Resources.Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        int i2;
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_KEYFRAME);
        TypedValue peekNamedValue = TypedArrayUtils.peekNamedValue(obtainAttributes, xmlPullParser, "value", 0);
        if ((peekNamedValue != null) && isColorType(peekNamedValue.type)) {
            i2 = 3;
        } else {
            i2 = 0;
        }
        obtainAttributes.recycle();
        return i2;
    }

    private static int inferValueTypeFromValues(TypedArray typedArray, int i2, int i3) {
        int i4;
        TypedValue peekValue = typedArray.peekValue(i2);
        boolean z = peekValue != null;
        int i5 = z ? peekValue.type : 0;
        TypedValue peekValue2 = typedArray.peekValue(i3);
        boolean z2 = peekValue2 != null;
        int i6 = z2 ? peekValue2.type : 0;
        if ((z && isColorType(i5)) || (z2 && isColorType(i6))) {
            i4 = 3;
        } else {
            i4 = 0;
        }
        return i4;
    }

    private static void dumpKeyframes(Object[] objArr, String str) {
        if (objArr != null && objArr.length != 0) {
            Log.d(TAG, str);
            int length = objArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                Keyframe keyframe = (Keyframe) objArr[i2];
                Log.d(TAG, "Keyframe " + i2 + ": fraction " + (keyframe.getFraction() < 0.0f ? "null" : Float.valueOf(keyframe.getFraction())) + ", , value : " + (keyframe.hasValue() ? keyframe.getValue() : "null"));
            }
        }
    }

    private static PropertyValuesHolder loadPvh(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, String str, int i2) throws XmlPullParserException, IOException {
        int i3 = i2;
        PropertyValuesHolder propertyValuesHolder = null;
        ArrayList arrayList = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 3 || next == 1) {
                break;
            }
            if (xmlPullParser.getName().equals("keyframe")) {
                if (i3 == 4) {
                    i3 = inferValueTypeOfKeyframe(resources, theme, Xml.asAttributeSet(xmlPullParser), xmlPullParser);
                }
                Keyframe loadKeyframe = loadKeyframe(context, resources, theme, Xml.asAttributeSet(xmlPullParser), i3, xmlPullParser);
                if (loadKeyframe != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(loadKeyframe);
                }
                xmlPullParser.next();
            }
        }
        if (arrayList != null) {
            int size = arrayList.size();
            int i4 = size;
            if (size > 0) {
                Keyframe keyframe = (Keyframe) arrayList.get(0);
                Keyframe keyframe2 = (Keyframe) arrayList.get(i4 - 1);
                float fraction = keyframe2.getFraction();
                if (fraction < 1.0f) {
                    if (fraction < 0.0f) {
                        keyframe2.setFraction(1.0f);
                    } else {
                        arrayList.add(arrayList.size(), createNewKeyframe(keyframe2, 1.0f));
                        i4++;
                    }
                }
                float fraction2 = keyframe.getFraction();
                if (fraction2 != 0.0f) {
                    if (fraction2 < 0.0f) {
                        keyframe.setFraction(0.0f);
                    } else {
                        arrayList.add(0, createNewKeyframe(keyframe, 0.0f));
                        i4++;
                    }
                }
                Keyframe[] keyframeArr = new Keyframe[i4];
                arrayList.toArray(keyframeArr);
                for (int i5 = 0; i5 < i4; i5++) {
                    Keyframe keyframe3 = keyframeArr[i5];
                    if (keyframe3.getFraction() < 0.0f) {
                        if (i5 == 0) {
                            keyframe3.setFraction(0.0f);
                        } else if (i5 == i4 - 1) {
                            keyframe3.setFraction(1.0f);
                        } else {
                            int i6 = i5;
                            int i7 = i5;
                            for (int i8 = i6 + 1; i8 < i4 - 1 && keyframeArr[i8].getFraction() < 0.0f; i8++) {
                                i7 = i8;
                            }
                            distributeKeyframes(keyframeArr, keyframeArr[i7 + 1].getFraction() - keyframeArr[i6 - 1].getFraction(), i6, i7);
                        }
                    }
                }
                propertyValuesHolder = PropertyValuesHolder.ofKeyframe(str, keyframeArr);
                if (i3 == 3) {
                    propertyValuesHolder.setEvaluator(ArgbEvaluator.getInstance());
                }
            }
        }
        return propertyValuesHolder;
    }

    private static Keyframe createNewKeyframe(Keyframe keyframe, float f) {
        Keyframe ofObject;
        if (keyframe.getType() == Float.TYPE) {
            ofObject = Keyframe.ofFloat(f);
        } else if (keyframe.getType() == Integer.TYPE) {
            ofObject = Keyframe.ofInt(f);
        } else {
            ofObject = Keyframe.ofObject(f);
        }
        return ofObject;
    }

    private static void distributeKeyframes(Keyframe[] keyframeArr, float f, int i2, int i3) {
        float f2 = f / ((i3 - i2) + 2);
        for (int i4 = i2; i4 <= i3; i4++) {
            keyframeArr[i4].setFraction(keyframeArr[i4 - 1].getFraction() + f2);
        }
    }

    private static Keyframe loadKeyframe(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, int i2, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i3 = i2;
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_KEYFRAME);
        Keyframe keyframe = null;
        float namedFloat = TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "fraction", 3, -1.0f);
        TypedValue peekNamedValue = TypedArrayUtils.peekNamedValue(obtainAttributes, xmlPullParser, "value", 0);
        boolean z = peekNamedValue != null;
        if (i3 == 4) {
            if (z && isColorType(peekNamedValue.type)) {
                i3 = 3;
            } else {
                i3 = 0;
            }
        }
        if (z) {
            switch (i3) {
                case 0:
                    keyframe = Keyframe.ofFloat(namedFloat, TypedArrayUtils.getNamedFloat(obtainAttributes, xmlPullParser, "value", 0, 0.0f));
                    break;
                case 1:
                case 3:
                    keyframe = Keyframe.ofInt(namedFloat, TypedArrayUtils.getNamedInt(obtainAttributes, xmlPullParser, "value", 0, 0));
                    break;
            }
        } else {
            keyframe = i3 == 0 ? Keyframe.ofFloat(namedFloat) : Keyframe.ofInt(namedFloat);
        }
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainAttributes, xmlPullParser, "interpolator", 1, 0);
        if (namedResourceId > 0) {
            keyframe.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, namedResourceId));
        }
        obtainAttributes.recycle();
        return keyframe;
    }

    private static ObjectAnimator loadObjectAnimator(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, float f, XmlPullParser xmlPullParser) throws Resources.NotFoundException {
        ObjectAnimator objectAnimator = new ObjectAnimator();
        loadAnimator(context, resources, theme, attributeSet, objectAnimator, f, xmlPullParser);
        return objectAnimator;
    }

    private static ValueAnimator loadAnimator(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, ValueAnimator valueAnimator, float f, XmlPullParser xmlPullParser) throws Resources.NotFoundException {
        ValueAnimator valueAnimator2 = valueAnimator;
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_ANIMATOR);
        TypedArray obtainAttributes2 = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
        if (valueAnimator2 == null) {
            valueAnimator2 = new ValueAnimator();
        }
        parseAnimatorFromTypeArray(valueAnimator2, obtainAttributes, obtainAttributes2, f, xmlPullParser);
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainAttributes, xmlPullParser, "interpolator", 0, 0);
        if (namedResourceId > 0) {
            valueAnimator2.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, namedResourceId));
        }
        obtainAttributes.recycle();
        if (obtainAttributes2 != null) {
            obtainAttributes2.recycle();
        }
        return valueAnimator2;
    }

    private static boolean isColorType(int i2) {
        return i2 >= 28 && i2 <= 31;
    }
}
