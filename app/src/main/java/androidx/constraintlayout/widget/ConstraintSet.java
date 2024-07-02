package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.HelperWidget;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class ConstraintSet {
    public static final int BASELINE = 5;
    public static final int BOTTOM = 4;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static final int END = 7;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int HORIZONTAL_GUIDELINE = 0;
    public static final int INVISIBLE = 4;
    public static final int LEFT = 1;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int PARENT_ID = 0;
    public static final int RIGHT = 2;
    public static final int START = 6;
    public static final int TOP = 3;
    public static final int UNSET = -1;
    public static final int VERTICAL = 1;
    public static final int VERTICAL_GUIDELINE = 1;
    public static final int VISIBILITY_MODE_IGNORE = 1;
    public static final int VISIBILITY_MODE_NORMAL = 0;
    public static final int VISIBLE = 0;
    public static final int WRAP_CONTENT = -2;

    /* renamed from: d, reason: collision with root package name */
    private static final int[] f437d = {0, 4, 8};
    private static SparseIntArray e;
    private HashMap<String, ConstraintAttribute> a = new HashMap<>();
    private boolean b = true;

    /* renamed from: c, reason: collision with root package name */
    private HashMap<Integer, Constraint> f438c = new HashMap<>();

    /* loaded from: classes.dex */
    public static class Constraint {
        int a;
        public final PropertySet propertySet = new PropertySet();
        public final Motion motion = new Motion();
        public final Layout layout = new Layout();
        public final Transform transform = new Transform();
        public HashMap<String, ConstraintAttribute> mCustomConstraints = new HashMap<>();

        /* JADX INFO: Access modifiers changed from: private */
        public void h(int i2, ConstraintLayout.LayoutParams layoutParams) {
            this.a = i2;
            Layout layout = this.layout;
            layout.leftToLeft = layoutParams.leftToLeft;
            layout.leftToRight = layoutParams.leftToRight;
            layout.rightToLeft = layoutParams.rightToLeft;
            layout.rightToRight = layoutParams.rightToRight;
            layout.topToTop = layoutParams.topToTop;
            layout.topToBottom = layoutParams.topToBottom;
            layout.bottomToTop = layoutParams.bottomToTop;
            layout.bottomToBottom = layoutParams.bottomToBottom;
            layout.baselineToBaseline = layoutParams.baselineToBaseline;
            layout.startToEnd = layoutParams.startToEnd;
            layout.startToStart = layoutParams.startToStart;
            layout.endToStart = layoutParams.endToStart;
            layout.endToEnd = layoutParams.endToEnd;
            layout.horizontalBias = layoutParams.horizontalBias;
            layout.verticalBias = layoutParams.verticalBias;
            layout.dimensionRatio = layoutParams.dimensionRatio;
            layout.circleConstraint = layoutParams.circleConstraint;
            layout.circleRadius = layoutParams.circleRadius;
            layout.circleAngle = layoutParams.circleAngle;
            layout.editorAbsoluteX = layoutParams.editorAbsoluteX;
            layout.editorAbsoluteY = layoutParams.editorAbsoluteY;
            layout.orientation = layoutParams.orientation;
            layout.guidePercent = layoutParams.guidePercent;
            layout.guideBegin = layoutParams.guideBegin;
            layout.guideEnd = layoutParams.guideEnd;
            Layout layout2 = this.layout;
            layout2.mWidth = ((ViewGroup.MarginLayoutParams) layoutParams).width;
            layout2.mHeight = ((ViewGroup.MarginLayoutParams) layoutParams).height;
            layout2.leftMargin = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            layout2.rightMargin = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            layout2.topMargin = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            layout2.bottomMargin = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            layout2.verticalWeight = layoutParams.verticalWeight;
            layout2.horizontalWeight = layoutParams.horizontalWeight;
            layout2.verticalChainStyle = layoutParams.verticalChainStyle;
            layout2.horizontalChainStyle = layoutParams.horizontalChainStyle;
            layout2.constrainedWidth = layoutParams.constrainedWidth;
            layout2.constrainedHeight = layoutParams.constrainedHeight;
            layout2.widthDefault = layoutParams.matchConstraintDefaultWidth;
            layout2.heightDefault = layoutParams.matchConstraintDefaultHeight;
            layout2.widthMax = layoutParams.matchConstraintMaxWidth;
            layout2.heightMax = layoutParams.matchConstraintMaxHeight;
            layout2.widthMin = layoutParams.matchConstraintMinWidth;
            layout2.heightMin = layoutParams.matchConstraintMinHeight;
            layout2.widthPercent = layoutParams.matchConstraintPercentWidth;
            layout2.heightPercent = layoutParams.matchConstraintPercentHeight;
            layout2.mConstraintTag = layoutParams.constraintTag;
            layout2.goneTopMargin = layoutParams.goneTopMargin;
            layout2.goneBottomMargin = layoutParams.goneBottomMargin;
            layout2.goneLeftMargin = layoutParams.goneLeftMargin;
            layout2.goneRightMargin = layoutParams.goneRightMargin;
            Layout layout3 = this.layout;
            layout3.goneStartMargin = layoutParams.goneStartMargin;
            layout3.goneEndMargin = layoutParams.goneEndMargin;
            if (Build.VERSION.SDK_INT >= 17) {
                layout3.endMargin = layoutParams.getMarginEnd();
                this.layout.startMargin = layoutParams.getMarginStart();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void i(int i2, Constraints.LayoutParams layoutParams) {
            h(i2, layoutParams);
            this.propertySet.alpha = layoutParams.alpha;
            Transform transform = this.transform;
            transform.rotation = layoutParams.rotation;
            transform.rotationX = layoutParams.rotationX;
            transform.rotationY = layoutParams.rotationY;
            transform.scaleX = layoutParams.scaleX;
            transform.scaleY = layoutParams.scaleY;
            transform.transformPivotX = layoutParams.transformPivotX;
            transform.transformPivotY = layoutParams.transformPivotY;
            transform.translationX = layoutParams.translationX;
            transform.translationY = layoutParams.translationY;
            transform.translationZ = layoutParams.translationZ;
            transform.elevation = layoutParams.elevation;
            transform.applyElevation = layoutParams.applyElevation;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void j(ConstraintHelper constraintHelper, int i2, Constraints.LayoutParams layoutParams) {
            i(i2, layoutParams);
            if (constraintHelper instanceof Barrier) {
                Layout layout = this.layout;
                layout.mHelperType = 1;
                Barrier barrier = (Barrier) constraintHelper;
                layout.mBarrierDirection = barrier.getType();
                this.layout.mReferenceIds = barrier.getReferencedIds();
                this.layout.mBarrierMargin = barrier.getMargin();
            }
        }

        private ConstraintAttribute k(String str, ConstraintAttribute.AttributeType attributeType) {
            if (!this.mCustomConstraints.containsKey(str)) {
                ConstraintAttribute constraintAttribute = new ConstraintAttribute(str, attributeType);
                this.mCustomConstraints.put(str, constraintAttribute);
                return constraintAttribute;
            }
            ConstraintAttribute constraintAttribute2 = this.mCustomConstraints.get(str);
            if (constraintAttribute2.getType() == attributeType) {
                return constraintAttribute2;
            }
            throw new IllegalArgumentException("ConstraintAttribute is already a " + constraintAttribute2.getType().name());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void l(String str, int i2) {
            k(str, ConstraintAttribute.AttributeType.COLOR_TYPE).setColorValue(i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void m(String str, float f) {
            k(str, ConstraintAttribute.AttributeType.FLOAT_TYPE).setFloatValue(f);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void n(String str, int i2) {
            k(str, ConstraintAttribute.AttributeType.INT_TYPE).setIntValue(i2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void o(String str, String str2) {
            k(str, ConstraintAttribute.AttributeType.STRING_TYPE).setStringValue(str2);
        }

        public void applyTo(ConstraintLayout.LayoutParams layoutParams) {
            Layout layout = this.layout;
            layoutParams.leftToLeft = layout.leftToLeft;
            layoutParams.leftToRight = layout.leftToRight;
            layoutParams.rightToLeft = layout.rightToLeft;
            layoutParams.rightToRight = layout.rightToRight;
            layoutParams.topToTop = layout.topToTop;
            layoutParams.topToBottom = layout.topToBottom;
            layoutParams.bottomToTop = layout.bottomToTop;
            layoutParams.bottomToBottom = layout.bottomToBottom;
            layoutParams.baselineToBaseline = layout.baselineToBaseline;
            layoutParams.startToEnd = layout.startToEnd;
            layoutParams.startToStart = layout.startToStart;
            layoutParams.endToStart = layout.endToStart;
            layoutParams.endToEnd = layout.endToEnd;
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = layout.leftMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = layout.rightMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = layout.topMargin;
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = layout.bottomMargin;
            layoutParams.goneStartMargin = layout.goneStartMargin;
            layoutParams.goneEndMargin = layout.goneEndMargin;
            layoutParams.goneTopMargin = layout.goneTopMargin;
            layoutParams.goneBottomMargin = layout.goneBottomMargin;
            layoutParams.horizontalBias = layout.horizontalBias;
            layoutParams.verticalBias = layout.verticalBias;
            layoutParams.circleConstraint = layout.circleConstraint;
            layoutParams.circleRadius = layout.circleRadius;
            Layout layout2 = this.layout;
            layoutParams.circleAngle = layout2.circleAngle;
            layoutParams.dimensionRatio = layout2.dimensionRatio;
            layoutParams.editorAbsoluteX = layout2.editorAbsoluteX;
            layoutParams.editorAbsoluteY = layout2.editorAbsoluteY;
            layoutParams.verticalWeight = layout2.verticalWeight;
            layoutParams.horizontalWeight = layout2.horizontalWeight;
            layoutParams.verticalChainStyle = layout2.verticalChainStyle;
            layoutParams.horizontalChainStyle = layout2.horizontalChainStyle;
            layoutParams.constrainedWidth = layout2.constrainedWidth;
            layoutParams.constrainedHeight = layout2.constrainedHeight;
            layoutParams.matchConstraintDefaultWidth = layout2.widthDefault;
            layoutParams.matchConstraintDefaultHeight = layout2.heightDefault;
            layoutParams.matchConstraintMaxWidth = layout2.widthMax;
            layoutParams.matchConstraintMaxHeight = layout2.heightMax;
            layoutParams.matchConstraintMinWidth = layout2.widthMin;
            layoutParams.matchConstraintMinHeight = layout2.heightMin;
            layoutParams.matchConstraintPercentWidth = layout2.widthPercent;
            layoutParams.matchConstraintPercentHeight = layout2.heightPercent;
            layoutParams.orientation = layout2.orientation;
            layoutParams.guidePercent = layout2.guidePercent;
            layoutParams.guideBegin = layout2.guideBegin;
            layoutParams.guideEnd = layout2.guideEnd;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = layout2.mWidth;
            ((ViewGroup.MarginLayoutParams) layoutParams).height = layout2.mHeight;
            String str = layout2.mConstraintTag;
            if (str != null) {
                layoutParams.constraintTag = str;
            }
            if (Build.VERSION.SDK_INT >= 17) {
                layoutParams.setMarginStart(this.layout.startMargin);
                layoutParams.setMarginEnd(this.layout.endMargin);
            }
            layoutParams.validate();
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Constraint m8clone() {
            Constraint constraint = new Constraint();
            constraint.layout.copyFrom(this.layout);
            constraint.motion.copyFrom(this.motion);
            constraint.propertySet.copyFrom(this.propertySet);
            constraint.transform.copyFrom(this.transform);
            constraint.a = this.a;
            return constraint;
        }
    }

    /* loaded from: classes.dex */
    public static class Layout {
        public static final int UNSET = -1;
        private static SparseIntArray a;
        public String mConstraintTag;
        public int mHeight;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        public int mWidth;
        public boolean mIsGuideline = false;
        public boolean mApply = false;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int topToTop = -1;
        public int topToBottom = -1;
        public int bottomToTop = -1;
        public int bottomToBottom = -1;
        public int baselineToBaseline = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int endToStart = -1;
        public int endToEnd = -1;
        public float horizontalBias = 0.5f;
        public float verticalBias = 0.5f;
        public String dimensionRatio = null;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public float circleAngle = 0.0f;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int orientation = -1;
        public int leftMargin = -1;
        public int rightMargin = -1;
        public int topMargin = -1;
        public int bottomMargin = -1;
        public int endMargin = -1;
        public int startMargin = -1;
        public int goneLeftMargin = -1;
        public int goneTopMargin = -1;
        public int goneRightMargin = -1;
        public int goneBottomMargin = -1;
        public int goneEndMargin = -1;
        public int goneStartMargin = -1;
        public float verticalWeight = -1.0f;
        public float horizontalWeight = -1.0f;
        public int horizontalChainStyle = 0;
        public int verticalChainStyle = 0;
        public int widthDefault = 0;
        public int heightDefault = 0;
        public int widthMax = -1;
        public int heightMax = -1;
        public int widthMin = -1;
        public int heightMin = -1;
        public float widthPercent = 1.0f;
        public float heightPercent = 1.0f;
        public int mBarrierDirection = -1;
        public int mBarrierMargin = 0;
        public int mHelperType = -1;
        public boolean constrainedWidth = false;
        public boolean constrainedHeight = false;
        public boolean mBarrierAllowsGoneWidgets = true;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R.styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            a.append(R.styleable.Layout_layout_constraintLeft_toRightOf, 25);
            a.append(R.styleable.Layout_layout_constraintRight_toLeftOf, 28);
            a.append(R.styleable.Layout_layout_constraintRight_toRightOf, 29);
            a.append(R.styleable.Layout_layout_constraintTop_toTopOf, 35);
            a.append(R.styleable.Layout_layout_constraintTop_toBottomOf, 34);
            a.append(R.styleable.Layout_layout_constraintBottom_toTopOf, 4);
            a.append(R.styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            a.append(R.styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            a.append(R.styleable.Layout_layout_editor_absoluteX, 6);
            a.append(R.styleable.Layout_layout_editor_absoluteY, 7);
            a.append(R.styleable.Layout_layout_constraintGuide_begin, 17);
            a.append(R.styleable.Layout_layout_constraintGuide_end, 18);
            a.append(R.styleable.Layout_layout_constraintGuide_percent, 19);
            a.append(R.styleable.Layout_android_orientation, 26);
            a.append(R.styleable.Layout_layout_constraintStart_toEndOf, 31);
            a.append(R.styleable.Layout_layout_constraintStart_toStartOf, 32);
            a.append(R.styleable.Layout_layout_constraintEnd_toStartOf, 10);
            a.append(R.styleable.Layout_layout_constraintEnd_toEndOf, 9);
            a.append(R.styleable.Layout_layout_goneMarginLeft, 13);
            a.append(R.styleable.Layout_layout_goneMarginTop, 16);
            a.append(R.styleable.Layout_layout_goneMarginRight, 14);
            a.append(R.styleable.Layout_layout_goneMarginBottom, 11);
            a.append(R.styleable.Layout_layout_goneMarginStart, 15);
            a.append(R.styleable.Layout_layout_goneMarginEnd, 12);
            a.append(R.styleable.Layout_layout_constraintVertical_weight, 38);
            a.append(R.styleable.Layout_layout_constraintHorizontal_weight, 37);
            a.append(R.styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            a.append(R.styleable.Layout_layout_constraintVertical_chainStyle, 40);
            a.append(R.styleable.Layout_layout_constraintHorizontal_bias, 20);
            a.append(R.styleable.Layout_layout_constraintVertical_bias, 36);
            a.append(R.styleable.Layout_layout_constraintDimensionRatio, 5);
            a.append(R.styleable.Layout_layout_constraintLeft_creator, 76);
            a.append(R.styleable.Layout_layout_constraintTop_creator, 76);
            a.append(R.styleable.Layout_layout_constraintRight_creator, 76);
            a.append(R.styleable.Layout_layout_constraintBottom_creator, 76);
            a.append(R.styleable.Layout_layout_constraintBaseline_creator, 76);
            a.append(R.styleable.Layout_android_layout_marginLeft, 23);
            a.append(R.styleable.Layout_android_layout_marginRight, 27);
            a.append(R.styleable.Layout_android_layout_marginStart, 30);
            a.append(R.styleable.Layout_android_layout_marginEnd, 8);
            a.append(R.styleable.Layout_android_layout_marginTop, 33);
            a.append(R.styleable.Layout_android_layout_marginBottom, 2);
            a.append(R.styleable.Layout_android_layout_width, 22);
            a.append(R.styleable.Layout_android_layout_height, 21);
            a.append(R.styleable.Layout_layout_constraintCircle, 61);
            a.append(R.styleable.Layout_layout_constraintCircleRadius, 62);
            a.append(R.styleable.Layout_layout_constraintCircleAngle, 63);
            a.append(R.styleable.Layout_layout_constraintWidth_percent, 69);
            a.append(R.styleable.Layout_layout_constraintHeight_percent, 70);
            a.append(R.styleable.Layout_chainUseRtl, 71);
            a.append(R.styleable.Layout_barrierDirection, 72);
            a.append(R.styleable.Layout_barrierMargin, 73);
            a.append(R.styleable.Layout_constraint_referenced_ids, 74);
            a.append(R.styleable.Layout_barrierAllowsGoneWidgets, 75);
        }

        void a(Context context, AttributeSet attributeSet) {
            StringBuilder sb;
            String str;
            int i2 = Build.VERSION.SDK_INT;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Layout);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = obtainStyledAttributes.getIndex(i3);
                int i4 = a.get(index);
                if (i4 == 80) {
                    this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                } else if (i4 != 81) {
                    switch (i4) {
                        case 1:
                            this.baselineToBaseline = ConstraintSet.i(obtainStyledAttributes, index, this.baselineToBaseline);
                            break;
                        case 2:
                            this.bottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.bottomMargin);
                            break;
                        case 3:
                            this.bottomToBottom = ConstraintSet.i(obtainStyledAttributes, index, this.bottomToBottom);
                            break;
                        case 4:
                            this.bottomToTop = ConstraintSet.i(obtainStyledAttributes, index, this.bottomToTop);
                            break;
                        case 5:
                            this.dimensionRatio = obtainStyledAttributes.getString(index);
                            break;
                        case 6:
                            this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                            break;
                        case 7:
                            this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                            break;
                        case 8:
                            if (i2 >= 17) {
                                this.endMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.endMargin);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            this.endToEnd = ConstraintSet.i(obtainStyledAttributes, index, this.endToEnd);
                            break;
                        case 10:
                            this.endToStart = ConstraintSet.i(obtainStyledAttributes, index, this.endToStart);
                            break;
                        case 11:
                            this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                            break;
                        case 12:
                            this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                            break;
                        case 13:
                            this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                            break;
                        case 14:
                            this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                            break;
                        case 15:
                            this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                            break;
                        case 16:
                            this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                            break;
                        case 17:
                            this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                            break;
                        case 18:
                            this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                            break;
                        case 19:
                            this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                            break;
                        case 20:
                            this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                            break;
                        case 21:
                            this.mHeight = obtainStyledAttributes.getLayoutDimension(index, this.mHeight);
                            break;
                        case 22:
                            this.mWidth = obtainStyledAttributes.getLayoutDimension(index, this.mWidth);
                            break;
                        case 23:
                            this.leftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.leftMargin);
                            break;
                        case 24:
                            this.leftToLeft = ConstraintSet.i(obtainStyledAttributes, index, this.leftToLeft);
                            break;
                        case 25:
                            this.leftToRight = ConstraintSet.i(obtainStyledAttributes, index, this.leftToRight);
                            break;
                        case 26:
                            this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                            break;
                        case 27:
                            this.rightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.rightMargin);
                            break;
                        case 28:
                            this.rightToLeft = ConstraintSet.i(obtainStyledAttributes, index, this.rightToLeft);
                            break;
                        case 29:
                            this.rightToRight = ConstraintSet.i(obtainStyledAttributes, index, this.rightToRight);
                            break;
                        case 30:
                            if (i2 >= 17) {
                                this.startMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.startMargin);
                                break;
                            } else {
                                break;
                            }
                        case 31:
                            this.startToEnd = ConstraintSet.i(obtainStyledAttributes, index, this.startToEnd);
                            break;
                        case 32:
                            this.startToStart = ConstraintSet.i(obtainStyledAttributes, index, this.startToStart);
                            break;
                        case 33:
                            this.topMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.topMargin);
                            break;
                        case 34:
                            this.topToBottom = ConstraintSet.i(obtainStyledAttributes, index, this.topToBottom);
                            break;
                        case 35:
                            this.topToTop = ConstraintSet.i(obtainStyledAttributes, index, this.topToTop);
                            break;
                        case 36:
                            this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                            break;
                        case 37:
                            this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                            break;
                        case 38:
                            this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                            break;
                        case 39:
                            this.horizontalChainStyle = obtainStyledAttributes.getInt(index, this.horizontalChainStyle);
                            break;
                        case 40:
                            this.verticalChainStyle = obtainStyledAttributes.getInt(index, this.verticalChainStyle);
                            break;
                        default:
                            switch (i4) {
                                case 54:
                                    this.widthDefault = obtainStyledAttributes.getInt(index, this.widthDefault);
                                    break;
                                case 55:
                                    this.heightDefault = obtainStyledAttributes.getInt(index, this.heightDefault);
                                    break;
                                case 56:
                                    this.widthMax = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMax);
                                    break;
                                case 57:
                                    this.heightMax = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMax);
                                    break;
                                case 58:
                                    this.widthMin = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMin);
                                    break;
                                case 59:
                                    this.heightMin = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMin);
                                    break;
                                default:
                                    switch (i4) {
                                        case 61:
                                            this.circleConstraint = ConstraintSet.i(obtainStyledAttributes, index, this.circleConstraint);
                                            break;
                                        case 62:
                                            this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                                            break;
                                        case 63:
                                            this.circleAngle = obtainStyledAttributes.getFloat(index, this.circleAngle);
                                            break;
                                        default:
                                            switch (i4) {
                                                case 69:
                                                    this.widthPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                                    continue;
                                                case 70:
                                                    this.heightPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                                    continue;
                                                case 71:
                                                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                                    continue;
                                                case 72:
                                                    this.mBarrierDirection = obtainStyledAttributes.getInt(index, this.mBarrierDirection);
                                                    continue;
                                                case 73:
                                                    this.mBarrierMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.mBarrierMargin);
                                                    continue;
                                                case 74:
                                                    this.mReferenceIdString = obtainStyledAttributes.getString(index);
                                                    continue;
                                                case 75:
                                                    this.mBarrierAllowsGoneWidgets = obtainStyledAttributes.getBoolean(index, this.mBarrierAllowsGoneWidgets);
                                                    continue;
                                                case 76:
                                                    sb = new StringBuilder();
                                                    str = "unused attribute 0x";
                                                    break;
                                                case 77:
                                                    this.mConstraintTag = obtainStyledAttributes.getString(index);
                                                    continue;
                                                default:
                                                    sb = new StringBuilder();
                                                    str = "Unknown attribute 0x";
                                                    break;
                                            }
                                            sb.append(str);
                                            sb.append(Integer.toHexString(index));
                                            sb.append("   ");
                                            sb.append(a.get(index));
                                            Log.w("ConstraintSet", sb.toString());
                                            break;
                                    }
                            }
                    }
                } else {
                    this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void copyFrom(Layout layout) {
            this.mIsGuideline = layout.mIsGuideline;
            this.mWidth = layout.mWidth;
            this.mApply = layout.mApply;
            this.mHeight = layout.mHeight;
            this.guideBegin = layout.guideBegin;
            this.guideEnd = layout.guideEnd;
            this.guidePercent = layout.guidePercent;
            this.leftToLeft = layout.leftToLeft;
            this.leftToRight = layout.leftToRight;
            this.rightToLeft = layout.rightToLeft;
            this.rightToRight = layout.rightToRight;
            this.topToTop = layout.topToTop;
            this.topToBottom = layout.topToBottom;
            this.bottomToTop = layout.bottomToTop;
            this.bottomToBottom = layout.bottomToBottom;
            this.baselineToBaseline = layout.baselineToBaseline;
            this.startToEnd = layout.startToEnd;
            this.startToStart = layout.startToStart;
            this.endToStart = layout.endToStart;
            this.endToEnd = layout.endToEnd;
            this.horizontalBias = layout.horizontalBias;
            this.verticalBias = layout.verticalBias;
            this.dimensionRatio = layout.dimensionRatio;
            this.circleConstraint = layout.circleConstraint;
            this.circleRadius = layout.circleRadius;
            this.circleAngle = layout.circleAngle;
            this.editorAbsoluteX = layout.editorAbsoluteX;
            this.editorAbsoluteY = layout.editorAbsoluteY;
            this.orientation = layout.orientation;
            this.leftMargin = layout.leftMargin;
            this.rightMargin = layout.rightMargin;
            this.topMargin = layout.topMargin;
            this.bottomMargin = layout.bottomMargin;
            this.endMargin = layout.endMargin;
            this.startMargin = layout.startMargin;
            this.goneLeftMargin = layout.goneLeftMargin;
            this.goneTopMargin = layout.goneTopMargin;
            this.goneRightMargin = layout.goneRightMargin;
            this.goneBottomMargin = layout.goneBottomMargin;
            this.goneEndMargin = layout.goneEndMargin;
            this.goneStartMargin = layout.goneStartMargin;
            this.verticalWeight = layout.verticalWeight;
            this.horizontalWeight = layout.horizontalWeight;
            this.horizontalChainStyle = layout.horizontalChainStyle;
            this.verticalChainStyle = layout.verticalChainStyle;
            this.widthDefault = layout.widthDefault;
            this.heightDefault = layout.heightDefault;
            this.widthMax = layout.widthMax;
            this.heightMax = layout.heightMax;
            this.widthMin = layout.widthMin;
            this.heightMin = layout.heightMin;
            this.widthPercent = layout.widthPercent;
            this.heightPercent = layout.heightPercent;
            this.mBarrierDirection = layout.mBarrierDirection;
            this.mBarrierMargin = layout.mBarrierMargin;
            this.mHelperType = layout.mHelperType;
            this.mConstraintTag = layout.mConstraintTag;
            int[] iArr = layout.mReferenceIds;
            if (iArr != null) {
                this.mReferenceIds = Arrays.copyOf(iArr, iArr.length);
            } else {
                this.mReferenceIds = null;
            }
            this.mReferenceIdString = layout.mReferenceIdString;
            this.constrainedWidth = layout.constrainedWidth;
            this.constrainedHeight = layout.constrainedHeight;
            this.mBarrierAllowsGoneWidgets = layout.mBarrierAllowsGoneWidgets;
        }

        public void dump(MotionScene motionScene, StringBuilder sb) {
            Field[] declaredFields = getClass().getDeclaredFields();
            sb.append("\n");
            for (Field field : declaredFields) {
                String name = field.getName();
                if (!Modifier.isStatic(field.getModifiers())) {
                    try {
                        Object obj = field.get(this);
                        Class<?> type = field.getType();
                        if (type == Integer.TYPE) {
                            Integer num = (Integer) obj;
                            if (num.intValue() != -1) {
                                Object lookUpConstraintName = motionScene.lookUpConstraintName(num.intValue());
                                sb.append("    ");
                                sb.append(name);
                                sb.append(" = \"");
                                sb.append(lookUpConstraintName == null ? num : lookUpConstraintName);
                            }
                        } else if (type == Float.TYPE) {
                            Float f = (Float) obj;
                            if (f.floatValue() != -1.0f) {
                                sb.append("    ");
                                sb.append(name);
                                sb.append(" = \"");
                                sb.append(f);
                            }
                        }
                        sb.append("\"\n");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Motion {
        private static SparseIntArray a;
        public boolean mApply = false;
        public int mAnimateRelativeTo = -1;
        public String mTransitionEasing = null;
        public int mPathMotionArc = -1;
        public int mDrawPath = 0;
        public float mMotionStagger = Float.NaN;
        public float mPathRotate = Float.NaN;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R.styleable.Motion_motionPathRotate, 1);
            a.append(R.styleable.Motion_pathMotionArc, 2);
            a.append(R.styleable.Motion_transitionEasing, 3);
            a.append(R.styleable.Motion_drawPath, 4);
            a.append(R.styleable.Motion_animate_relativeTo, 5);
            a.append(R.styleable.Motion_motionStagger, 6);
        }

        void a(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Motion);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                switch (a.get(index)) {
                    case 1:
                        this.mPathRotate = obtainStyledAttributes.getFloat(index, this.mPathRotate);
                        break;
                    case 2:
                        this.mPathMotionArc = obtainStyledAttributes.getInt(index, this.mPathMotionArc);
                        break;
                    case 3:
                        this.mTransitionEasing = obtainStyledAttributes.peekValue(index).type == 3 ? obtainStyledAttributes.getString(index) : Easing.NAMED_EASING[obtainStyledAttributes.getInteger(index, 0)];
                        break;
                    case 4:
                        this.mDrawPath = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.mAnimateRelativeTo = ConstraintSet.i(obtainStyledAttributes, index, this.mAnimateRelativeTo);
                        break;
                    case 6:
                        this.mMotionStagger = obtainStyledAttributes.getFloat(index, this.mMotionStagger);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void copyFrom(Motion motion) {
            this.mApply = motion.mApply;
            this.mAnimateRelativeTo = motion.mAnimateRelativeTo;
            this.mTransitionEasing = motion.mTransitionEasing;
            this.mPathMotionArc = motion.mPathMotionArc;
            this.mDrawPath = motion.mDrawPath;
            this.mPathRotate = motion.mPathRotate;
            this.mMotionStagger = motion.mMotionStagger;
        }
    }

    /* loaded from: classes.dex */
    public static class PropertySet {
        public boolean mApply = false;
        public int visibility = 0;
        public int mVisibilityMode = 0;
        public float alpha = 1.0f;
        public float mProgress = Float.NaN;

        void a(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PropertySet);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.PropertySet_android_alpha) {
                    this.alpha = obtainStyledAttributes.getFloat(index, this.alpha);
                } else if (index == R.styleable.PropertySet_android_visibility) {
                    this.visibility = obtainStyledAttributes.getInt(index, this.visibility);
                    this.visibility = ConstraintSet.f437d[this.visibility];
                } else if (index == R.styleable.PropertySet_visibilityMode) {
                    this.mVisibilityMode = obtainStyledAttributes.getInt(index, this.mVisibilityMode);
                } else if (index == R.styleable.PropertySet_motionProgress) {
                    this.mProgress = obtainStyledAttributes.getFloat(index, this.mProgress);
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void copyFrom(PropertySet propertySet) {
            this.mApply = propertySet.mApply;
            this.visibility = propertySet.visibility;
            this.alpha = propertySet.alpha;
            this.mProgress = propertySet.mProgress;
            this.mVisibilityMode = propertySet.mVisibilityMode;
        }
    }

    /* loaded from: classes.dex */
    public static class Transform {
        private static SparseIntArray a;
        public boolean mApply = false;
        public float rotation = 0.0f;
        public float rotationX = 0.0f;
        public float rotationY = 0.0f;
        public float scaleX = 1.0f;
        public float scaleY = 1.0f;
        public float transformPivotX = Float.NaN;
        public float transformPivotY = Float.NaN;
        public float translationX = 0.0f;
        public float translationY = 0.0f;
        public float translationZ = 0.0f;
        public boolean applyElevation = false;
        public float elevation = 0.0f;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            a = sparseIntArray;
            sparseIntArray.append(R.styleable.Transform_android_rotation, 1);
            a.append(R.styleable.Transform_android_rotationX, 2);
            a.append(R.styleable.Transform_android_rotationY, 3);
            a.append(R.styleable.Transform_android_scaleX, 4);
            a.append(R.styleable.Transform_android_scaleY, 5);
            a.append(R.styleable.Transform_android_transformPivotX, 6);
            a.append(R.styleable.Transform_android_transformPivotY, 7);
            a.append(R.styleable.Transform_android_translationX, 8);
            a.append(R.styleable.Transform_android_translationY, 9);
            a.append(R.styleable.Transform_android_translationZ, 10);
            a.append(R.styleable.Transform_android_elevation, 11);
        }

        void a(Context context, AttributeSet attributeSet) {
            int i2 = Build.VERSION.SDK_INT;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Transform);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = obtainStyledAttributes.getIndex(i3);
                switch (a.get(index)) {
                    case 1:
                        this.rotation = obtainStyledAttributes.getFloat(index, this.rotation);
                        break;
                    case 2:
                        this.rotationX = obtainStyledAttributes.getFloat(index, this.rotationX);
                        break;
                    case 3:
                        this.rotationY = obtainStyledAttributes.getFloat(index, this.rotationY);
                        break;
                    case 4:
                        this.scaleX = obtainStyledAttributes.getFloat(index, this.scaleX);
                        break;
                    case 5:
                        this.scaleY = obtainStyledAttributes.getFloat(index, this.scaleY);
                        break;
                    case 6:
                        this.transformPivotX = obtainStyledAttributes.getDimension(index, this.transformPivotX);
                        break;
                    case 7:
                        this.transformPivotY = obtainStyledAttributes.getDimension(index, this.transformPivotY);
                        break;
                    case 8:
                        this.translationX = obtainStyledAttributes.getDimension(index, this.translationX);
                        break;
                    case 9:
                        this.translationY = obtainStyledAttributes.getDimension(index, this.translationY);
                        break;
                    case 10:
                        if (i2 >= 21) {
                            this.translationZ = obtainStyledAttributes.getDimension(index, this.translationZ);
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (i2 >= 21) {
                            this.applyElevation = true;
                            this.elevation = obtainStyledAttributes.getDimension(index, this.elevation);
                            break;
                        } else {
                            break;
                        }
                }
            }
            obtainStyledAttributes.recycle();
        }

        public void copyFrom(Transform transform) {
            this.mApply = transform.mApply;
            this.rotation = transform.rotation;
            this.rotationX = transform.rotationX;
            this.rotationY = transform.rotationY;
            this.scaleX = transform.scaleX;
            this.scaleY = transform.scaleY;
            this.transformPivotX = transform.transformPivotX;
            this.transformPivotY = transform.transformPivotY;
            this.translationX = transform.translationX;
            this.translationY = transform.translationY;
            this.translationZ = transform.translationZ;
            this.applyElevation = transform.applyElevation;
            this.elevation = transform.elevation;
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        e = sparseIntArray;
        sparseIntArray.append(R.styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        e.append(R.styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        e.append(R.styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        e.append(R.styleable.Constraint_layout_constraintRight_toRightOf, 30);
        e.append(R.styleable.Constraint_layout_constraintTop_toTopOf, 36);
        e.append(R.styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        e.append(R.styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        e.append(R.styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        e.append(R.styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        e.append(R.styleable.Constraint_layout_editor_absoluteX, 6);
        e.append(R.styleable.Constraint_layout_editor_absoluteY, 7);
        e.append(R.styleable.Constraint_layout_constraintGuide_begin, 17);
        e.append(R.styleable.Constraint_layout_constraintGuide_end, 18);
        e.append(R.styleable.Constraint_layout_constraintGuide_percent, 19);
        e.append(R.styleable.Constraint_android_orientation, 27);
        e.append(R.styleable.Constraint_layout_constraintStart_toEndOf, 32);
        e.append(R.styleable.Constraint_layout_constraintStart_toStartOf, 33);
        e.append(R.styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        e.append(R.styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        e.append(R.styleable.Constraint_layout_goneMarginLeft, 13);
        e.append(R.styleable.Constraint_layout_goneMarginTop, 16);
        e.append(R.styleable.Constraint_layout_goneMarginRight, 14);
        e.append(R.styleable.Constraint_layout_goneMarginBottom, 11);
        e.append(R.styleable.Constraint_layout_goneMarginStart, 15);
        e.append(R.styleable.Constraint_layout_goneMarginEnd, 12);
        e.append(R.styleable.Constraint_layout_constraintVertical_weight, 40);
        e.append(R.styleable.Constraint_layout_constraintHorizontal_weight, 39);
        e.append(R.styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        e.append(R.styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        e.append(R.styleable.Constraint_layout_constraintHorizontal_bias, 20);
        e.append(R.styleable.Constraint_layout_constraintVertical_bias, 37);
        e.append(R.styleable.Constraint_layout_constraintDimensionRatio, 5);
        e.append(R.styleable.Constraint_layout_constraintLeft_creator, 82);
        e.append(R.styleable.Constraint_layout_constraintTop_creator, 82);
        e.append(R.styleable.Constraint_layout_constraintRight_creator, 82);
        e.append(R.styleable.Constraint_layout_constraintBottom_creator, 82);
        e.append(R.styleable.Constraint_layout_constraintBaseline_creator, 82);
        e.append(R.styleable.Constraint_android_layout_marginLeft, 24);
        e.append(R.styleable.Constraint_android_layout_marginRight, 28);
        e.append(R.styleable.Constraint_android_layout_marginStart, 31);
        e.append(R.styleable.Constraint_android_layout_marginEnd, 8);
        e.append(R.styleable.Constraint_android_layout_marginTop, 34);
        e.append(R.styleable.Constraint_android_layout_marginBottom, 2);
        e.append(R.styleable.Constraint_android_layout_width, 23);
        e.append(R.styleable.Constraint_android_layout_height, 21);
        e.append(R.styleable.Constraint_android_visibility, 22);
        e.append(R.styleable.Constraint_android_alpha, 43);
        e.append(R.styleable.Constraint_android_elevation, 44);
        e.append(R.styleable.Constraint_android_rotationX, 45);
        e.append(R.styleable.Constraint_android_rotationY, 46);
        e.append(R.styleable.Constraint_android_rotation, 60);
        e.append(R.styleable.Constraint_android_scaleX, 47);
        e.append(R.styleable.Constraint_android_scaleY, 48);
        e.append(R.styleable.Constraint_android_transformPivotX, 49);
        e.append(R.styleable.Constraint_android_transformPivotY, 50);
        e.append(R.styleable.Constraint_android_translationX, 51);
        e.append(R.styleable.Constraint_android_translationY, 52);
        e.append(R.styleable.Constraint_android_translationZ, 53);
        e.append(R.styleable.Constraint_layout_constraintWidth_default, 54);
        e.append(R.styleable.Constraint_layout_constraintHeight_default, 55);
        e.append(R.styleable.Constraint_layout_constraintWidth_max, 56);
        e.append(R.styleable.Constraint_layout_constraintHeight_max, 57);
        e.append(R.styleable.Constraint_layout_constraintWidth_min, 58);
        e.append(R.styleable.Constraint_layout_constraintHeight_min, 59);
        e.append(R.styleable.Constraint_layout_constraintCircle, 61);
        e.append(R.styleable.Constraint_layout_constraintCircleRadius, 62);
        e.append(R.styleable.Constraint_layout_constraintCircleAngle, 63);
        e.append(R.styleable.Constraint_animate_relativeTo, 64);
        e.append(R.styleable.Constraint_transitionEasing, 65);
        e.append(R.styleable.Constraint_drawPath, 66);
        e.append(R.styleable.Constraint_transitionPathRotate, 67);
        e.append(R.styleable.Constraint_motionStagger, 79);
        e.append(R.styleable.Constraint_android_id, 38);
        e.append(R.styleable.Constraint_motionProgress, 68);
        e.append(R.styleable.Constraint_layout_constraintWidth_percent, 69);
        e.append(R.styleable.Constraint_layout_constraintHeight_percent, 70);
        e.append(R.styleable.Constraint_chainUseRtl, 71);
        e.append(R.styleable.Constraint_barrierDirection, 72);
        e.append(R.styleable.Constraint_barrierMargin, 73);
        e.append(R.styleable.Constraint_constraint_referenced_ids, 74);
        e.append(R.styleable.Constraint_barrierAllowsGoneWidgets, 75);
        e.append(R.styleable.Constraint_pathMotionArc, 76);
        e.append(R.styleable.Constraint_layout_constraintTag, 77);
        e.append(R.styleable.Constraint_visibilityMode, 78);
        e.append(R.styleable.Constraint_layout_constrainedWidth, 80);
        e.append(R.styleable.Constraint_layout_constrainedHeight, 81);
    }

    private void c(ConstraintAttribute.AttributeType attributeType, String... strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (this.a.containsKey(strArr[i2])) {
                ConstraintAttribute constraintAttribute = this.a.get(strArr[i2]);
                if (constraintAttribute.getType() != attributeType) {
                    throw new IllegalArgumentException("ConstraintAttribute is already a " + constraintAttribute.getType().name());
                }
            } else {
                this.a.put(strArr[i2], new ConstraintAttribute(strArr[i2], attributeType));
            }
        }
    }

    private int[] e(View view, String str) {
        int i2;
        Object designInformation;
        String[] split = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[split.length];
        int i3 = 0;
        int i4 = 0;
        while (i3 < split.length) {
            String trim = split[i3].trim();
            try {
                i2 = R.id.class.getField(trim).getInt(null);
            } catch (Exception unused) {
                i2 = 0;
            }
            if (i2 == 0) {
                i2 = context.getResources().getIdentifier(trim, "id", context.getPackageName());
            }
            if (i2 == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (designInformation = ((ConstraintLayout) view.getParent()).getDesignInformation(0, trim)) != null && (designInformation instanceof Integer)) {
                i2 = ((Integer) designInformation).intValue();
            }
            iArr[i4] = i2;
            i3++;
            i4++;
        }
        return i4 != split.length ? Arrays.copyOf(iArr, i4) : iArr;
    }

    private void f(int i2, int i3, int i4, int i5, int[] iArr, float[] fArr, int i6, int i7, int i8) {
        if (iArr.length < 2) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (fArr != null && fArr.length != iArr.length) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (fArr != null) {
            h(iArr[0]).layout.horizontalWeight = fArr[0];
        }
        h(iArr[0]).layout.horizontalChainStyle = i6;
        connect(iArr[0], i7, i2, i3, -1);
        for (int i9 = 1; i9 < iArr.length; i9++) {
            int i10 = iArr[i9];
            int i11 = i9 - 1;
            connect(iArr[i9], i7, iArr[i11], i8, -1);
            connect(iArr[i11], i8, iArr[i9], i7, -1);
            if (fArr != null) {
                h(iArr[i9]).layout.horizontalWeight = fArr[i9];
            }
        }
        connect(iArr[iArr.length - 1], i8, i4, i5, -1);
    }

    private Constraint g(Context context, AttributeSet attributeSet) {
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Constraint);
        j(context, constraint, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        return constraint;
    }

    private Constraint h(int i2) {
        if (!this.f438c.containsKey(Integer.valueOf(i2))) {
            this.f438c.put(Integer.valueOf(i2), new Constraint());
        }
        return this.f438c.get(Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int i(TypedArray typedArray, int i2, int i3) {
        int resourceId = typedArray.getResourceId(i2, i3);
        return resourceId == -1 ? typedArray.getInt(i2, -1) : resourceId;
    }

    private void j(Context context, Constraint constraint, TypedArray typedArray) {
        Motion motion;
        String str;
        StringBuilder sb;
        String str2;
        int i2 = Build.VERSION.SDK_INT;
        int indexCount = typedArray.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = typedArray.getIndex(i3);
            if (index != R.styleable.Constraint_android_id && R.styleable.Constraint_android_layout_marginStart != index && R.styleable.Constraint_android_layout_marginEnd != index) {
                constraint.motion.mApply = true;
                constraint.layout.mApply = true;
                constraint.propertySet.mApply = true;
                constraint.transform.mApply = true;
            }
            switch (e.get(index)) {
                case 1:
                    Layout layout = constraint.layout;
                    layout.baselineToBaseline = i(typedArray, index, layout.baselineToBaseline);
                    continue;
                case 2:
                    Layout layout2 = constraint.layout;
                    layout2.bottomMargin = typedArray.getDimensionPixelSize(index, layout2.bottomMargin);
                    continue;
                case 3:
                    Layout layout3 = constraint.layout;
                    layout3.bottomToBottom = i(typedArray, index, layout3.bottomToBottom);
                    continue;
                case 4:
                    Layout layout4 = constraint.layout;
                    layout4.bottomToTop = i(typedArray, index, layout4.bottomToTop);
                    continue;
                case 5:
                    constraint.layout.dimensionRatio = typedArray.getString(index);
                    continue;
                case 6:
                    Layout layout5 = constraint.layout;
                    layout5.editorAbsoluteX = typedArray.getDimensionPixelOffset(index, layout5.editorAbsoluteX);
                    continue;
                case 7:
                    Layout layout6 = constraint.layout;
                    layout6.editorAbsoluteY = typedArray.getDimensionPixelOffset(index, layout6.editorAbsoluteY);
                    continue;
                case 8:
                    if (i2 >= 17) {
                        Layout layout7 = constraint.layout;
                        layout7.endMargin = typedArray.getDimensionPixelSize(index, layout7.endMargin);
                        break;
                    } else {
                        continue;
                    }
                case 9:
                    Layout layout8 = constraint.layout;
                    layout8.endToEnd = i(typedArray, index, layout8.endToEnd);
                    continue;
                case 10:
                    Layout layout9 = constraint.layout;
                    layout9.endToStart = i(typedArray, index, layout9.endToStart);
                    continue;
                case 11:
                    Layout layout10 = constraint.layout;
                    layout10.goneBottomMargin = typedArray.getDimensionPixelSize(index, layout10.goneBottomMargin);
                    continue;
                case 12:
                    Layout layout11 = constraint.layout;
                    layout11.goneEndMargin = typedArray.getDimensionPixelSize(index, layout11.goneEndMargin);
                    continue;
                case 13:
                    Layout layout12 = constraint.layout;
                    layout12.goneLeftMargin = typedArray.getDimensionPixelSize(index, layout12.goneLeftMargin);
                    continue;
                case 14:
                    Layout layout13 = constraint.layout;
                    layout13.goneRightMargin = typedArray.getDimensionPixelSize(index, layout13.goneRightMargin);
                    continue;
                case 15:
                    Layout layout14 = constraint.layout;
                    layout14.goneStartMargin = typedArray.getDimensionPixelSize(index, layout14.goneStartMargin);
                    continue;
                case 16:
                    Layout layout15 = constraint.layout;
                    layout15.goneTopMargin = typedArray.getDimensionPixelSize(index, layout15.goneTopMargin);
                    continue;
                case 17:
                    Layout layout16 = constraint.layout;
                    layout16.guideBegin = typedArray.getDimensionPixelOffset(index, layout16.guideBegin);
                    continue;
                case 18:
                    Layout layout17 = constraint.layout;
                    layout17.guideEnd = typedArray.getDimensionPixelOffset(index, layout17.guideEnd);
                    continue;
                case 19:
                    Layout layout18 = constraint.layout;
                    layout18.guidePercent = typedArray.getFloat(index, layout18.guidePercent);
                    continue;
                case 20:
                    Layout layout19 = constraint.layout;
                    layout19.horizontalBias = typedArray.getFloat(index, layout19.horizontalBias);
                    continue;
                case 21:
                    Layout layout20 = constraint.layout;
                    layout20.mHeight = typedArray.getLayoutDimension(index, layout20.mHeight);
                    continue;
                case 22:
                    PropertySet propertySet = constraint.propertySet;
                    propertySet.visibility = typedArray.getInt(index, propertySet.visibility);
                    PropertySet propertySet2 = constraint.propertySet;
                    propertySet2.visibility = f437d[propertySet2.visibility];
                    continue;
                case 23:
                    Layout layout21 = constraint.layout;
                    layout21.mWidth = typedArray.getLayoutDimension(index, layout21.mWidth);
                    continue;
                case 24:
                    Layout layout22 = constraint.layout;
                    layout22.leftMargin = typedArray.getDimensionPixelSize(index, layout22.leftMargin);
                    continue;
                case 25:
                    Layout layout23 = constraint.layout;
                    layout23.leftToLeft = i(typedArray, index, layout23.leftToLeft);
                    continue;
                case 26:
                    Layout layout24 = constraint.layout;
                    layout24.leftToRight = i(typedArray, index, layout24.leftToRight);
                    continue;
                case 27:
                    Layout layout25 = constraint.layout;
                    layout25.orientation = typedArray.getInt(index, layout25.orientation);
                    continue;
                case 28:
                    Layout layout26 = constraint.layout;
                    layout26.rightMargin = typedArray.getDimensionPixelSize(index, layout26.rightMargin);
                    continue;
                case 29:
                    Layout layout27 = constraint.layout;
                    layout27.rightToLeft = i(typedArray, index, layout27.rightToLeft);
                    continue;
                case 30:
                    Layout layout28 = constraint.layout;
                    layout28.rightToRight = i(typedArray, index, layout28.rightToRight);
                    continue;
                case 31:
                    if (i2 >= 17) {
                        Layout layout29 = constraint.layout;
                        layout29.startMargin = typedArray.getDimensionPixelSize(index, layout29.startMargin);
                        break;
                    } else {
                        continue;
                    }
                case 32:
                    Layout layout30 = constraint.layout;
                    layout30.startToEnd = i(typedArray, index, layout30.startToEnd);
                    continue;
                case 33:
                    Layout layout31 = constraint.layout;
                    layout31.startToStart = i(typedArray, index, layout31.startToStart);
                    continue;
                case 34:
                    Layout layout32 = constraint.layout;
                    layout32.topMargin = typedArray.getDimensionPixelSize(index, layout32.topMargin);
                    continue;
                case 35:
                    Layout layout33 = constraint.layout;
                    layout33.topToBottom = i(typedArray, index, layout33.topToBottom);
                    continue;
                case 36:
                    Layout layout34 = constraint.layout;
                    layout34.topToTop = i(typedArray, index, layout34.topToTop);
                    continue;
                case 37:
                    Layout layout35 = constraint.layout;
                    layout35.verticalBias = typedArray.getFloat(index, layout35.verticalBias);
                    continue;
                case 38:
                    constraint.a = typedArray.getResourceId(index, constraint.a);
                    continue;
                case 39:
                    Layout layout36 = constraint.layout;
                    layout36.horizontalWeight = typedArray.getFloat(index, layout36.horizontalWeight);
                    continue;
                case 40:
                    Layout layout37 = constraint.layout;
                    layout37.verticalWeight = typedArray.getFloat(index, layout37.verticalWeight);
                    continue;
                case 41:
                    Layout layout38 = constraint.layout;
                    layout38.horizontalChainStyle = typedArray.getInt(index, layout38.horizontalChainStyle);
                    continue;
                case 42:
                    Layout layout39 = constraint.layout;
                    layout39.verticalChainStyle = typedArray.getInt(index, layout39.verticalChainStyle);
                    continue;
                case 43:
                    PropertySet propertySet3 = constraint.propertySet;
                    propertySet3.alpha = typedArray.getFloat(index, propertySet3.alpha);
                    continue;
                case 44:
                    if (i2 >= 21) {
                        Transform transform = constraint.transform;
                        transform.applyElevation = true;
                        transform.elevation = typedArray.getDimension(index, transform.elevation);
                        break;
                    } else {
                        continue;
                    }
                case 45:
                    Transform transform2 = constraint.transform;
                    transform2.rotationX = typedArray.getFloat(index, transform2.rotationX);
                    continue;
                case 46:
                    Transform transform3 = constraint.transform;
                    transform3.rotationY = typedArray.getFloat(index, transform3.rotationY);
                    continue;
                case 47:
                    Transform transform4 = constraint.transform;
                    transform4.scaleX = typedArray.getFloat(index, transform4.scaleX);
                    continue;
                case 48:
                    Transform transform5 = constraint.transform;
                    transform5.scaleY = typedArray.getFloat(index, transform5.scaleY);
                    continue;
                case 49:
                    Transform transform6 = constraint.transform;
                    transform6.transformPivotX = typedArray.getDimension(index, transform6.transformPivotX);
                    continue;
                case 50:
                    Transform transform7 = constraint.transform;
                    transform7.transformPivotY = typedArray.getDimension(index, transform7.transformPivotY);
                    continue;
                case 51:
                    Transform transform8 = constraint.transform;
                    transform8.translationX = typedArray.getDimension(index, transform8.translationX);
                    continue;
                case 52:
                    Transform transform9 = constraint.transform;
                    transform9.translationY = typedArray.getDimension(index, transform9.translationY);
                    continue;
                case 53:
                    if (i2 >= 21) {
                        Transform transform10 = constraint.transform;
                        transform10.translationZ = typedArray.getDimension(index, transform10.translationZ);
                        break;
                    } else {
                        continue;
                    }
                case 54:
                    Layout layout40 = constraint.layout;
                    layout40.widthDefault = typedArray.getInt(index, layout40.widthDefault);
                    continue;
                case 55:
                    Layout layout41 = constraint.layout;
                    layout41.heightDefault = typedArray.getInt(index, layout41.heightDefault);
                    continue;
                case 56:
                    Layout layout42 = constraint.layout;
                    layout42.widthMax = typedArray.getDimensionPixelSize(index, layout42.widthMax);
                    continue;
                case 57:
                    Layout layout43 = constraint.layout;
                    layout43.heightMax = typedArray.getDimensionPixelSize(index, layout43.heightMax);
                    continue;
                case 58:
                    Layout layout44 = constraint.layout;
                    layout44.widthMin = typedArray.getDimensionPixelSize(index, layout44.widthMin);
                    continue;
                case 59:
                    Layout layout45 = constraint.layout;
                    layout45.heightMin = typedArray.getDimensionPixelSize(index, layout45.heightMin);
                    continue;
                case 60:
                    Transform transform11 = constraint.transform;
                    transform11.rotation = typedArray.getFloat(index, transform11.rotation);
                    continue;
                case 61:
                    Layout layout46 = constraint.layout;
                    layout46.circleConstraint = i(typedArray, index, layout46.circleConstraint);
                    continue;
                case 62:
                    Layout layout47 = constraint.layout;
                    layout47.circleRadius = typedArray.getDimensionPixelSize(index, layout47.circleRadius);
                    continue;
                case 63:
                    Layout layout48 = constraint.layout;
                    layout48.circleAngle = typedArray.getFloat(index, layout48.circleAngle);
                    continue;
                case 64:
                    Motion motion2 = constraint.motion;
                    motion2.mAnimateRelativeTo = i(typedArray, index, motion2.mAnimateRelativeTo);
                    continue;
                case 65:
                    if (typedArray.peekValue(index).type == 3) {
                        motion = constraint.motion;
                        str = typedArray.getString(index);
                    } else {
                        motion = constraint.motion;
                        str = Easing.NAMED_EASING[typedArray.getInteger(index, 0)];
                    }
                    motion.mTransitionEasing = str;
                    continue;
                case 66:
                    constraint.motion.mDrawPath = typedArray.getInt(index, 0);
                    continue;
                case 67:
                    Motion motion3 = constraint.motion;
                    motion3.mPathRotate = typedArray.getFloat(index, motion3.mPathRotate);
                    continue;
                case 68:
                    PropertySet propertySet4 = constraint.propertySet;
                    propertySet4.mProgress = typedArray.getFloat(index, propertySet4.mProgress);
                    continue;
                case 69:
                    constraint.layout.widthPercent = typedArray.getFloat(index, 1.0f);
                    continue;
                case 70:
                    constraint.layout.heightPercent = typedArray.getFloat(index, 1.0f);
                    continue;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    continue;
                case 72:
                    Layout layout49 = constraint.layout;
                    layout49.mBarrierDirection = typedArray.getInt(index, layout49.mBarrierDirection);
                    continue;
                case 73:
                    Layout layout50 = constraint.layout;
                    layout50.mBarrierMargin = typedArray.getDimensionPixelSize(index, layout50.mBarrierMargin);
                    continue;
                case 74:
                    constraint.layout.mReferenceIdString = typedArray.getString(index);
                    continue;
                case 75:
                    Layout layout51 = constraint.layout;
                    layout51.mBarrierAllowsGoneWidgets = typedArray.getBoolean(index, layout51.mBarrierAllowsGoneWidgets);
                    continue;
                case 76:
                    Motion motion4 = constraint.motion;
                    motion4.mPathMotionArc = typedArray.getInt(index, motion4.mPathMotionArc);
                    continue;
                case 77:
                    constraint.layout.mConstraintTag = typedArray.getString(index);
                    continue;
                case 78:
                    PropertySet propertySet5 = constraint.propertySet;
                    propertySet5.mVisibilityMode = typedArray.getInt(index, propertySet5.mVisibilityMode);
                    continue;
                case 79:
                    Motion motion5 = constraint.motion;
                    motion5.mMotionStagger = typedArray.getFloat(index, motion5.mMotionStagger);
                    continue;
                case 80:
                    Layout layout52 = constraint.layout;
                    layout52.constrainedWidth = typedArray.getBoolean(index, layout52.constrainedWidth);
                    continue;
                case 81:
                    Layout layout53 = constraint.layout;
                    layout53.constrainedHeight = typedArray.getBoolean(index, layout53.constrainedHeight);
                    continue;
                case 82:
                    sb = new StringBuilder();
                    str2 = "unused attribute 0x";
                    break;
                default:
                    sb = new StringBuilder();
                    str2 = "Unknown attribute 0x";
                    break;
            }
            sb.append(str2);
            sb.append(Integer.toHexString(index));
            sb.append("   ");
            sb.append(e.get(index));
            Log.w("ConstraintSet", sb.toString());
        }
    }

    private String k(int i2) {
        switch (i2) {
            case 1:
                return "left";
            case 2:
                return "right";
            case 3:
                return "top";
            case 4:
                return "bottom";
            case 5:
                return "baseline";
            case 6:
                return "start";
            case 7:
                return "end";
            default:
                return "undefined";
        }
    }

    private static String[] l(String str) {
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        boolean z = false;
        for (int i3 = 0; i3 < charArray.length; i3++) {
            if (charArray[i3] == ',' && !z) {
                arrayList.add(new String(charArray, i2, i3 - i2));
                i2 = i3 + 1;
            } else if (charArray[i3] == '\"') {
                z = !z;
            }
        }
        arrayList.add(new String(charArray, i2, charArray.length - i2));
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public void addColorAttributes(String... strArr) {
        c(ConstraintAttribute.AttributeType.COLOR_TYPE, strArr);
    }

    public void addFloatAttributes(String... strArr) {
        c(ConstraintAttribute.AttributeType.FLOAT_TYPE, strArr);
    }

    public void addIntAttributes(String... strArr) {
        c(ConstraintAttribute.AttributeType.INT_TYPE, strArr);
    }

    public void addStringAttributes(String... strArr) {
        c(ConstraintAttribute.AttributeType.STRING_TYPE, strArr);
    }

    public void addToHorizontalChain(int i2, int i3, int i4) {
        connect(i2, 1, i3, i3 == 0 ? 1 : 2, 0);
        connect(i2, 2, i4, i4 == 0 ? 2 : 1, 0);
        if (i3 != 0) {
            connect(i3, 2, i2, 1, 0);
        }
        if (i4 != 0) {
            connect(i4, 1, i2, 2, 0);
        }
    }

    public void addToHorizontalChainRTL(int i2, int i3, int i4) {
        connect(i2, 6, i3, i3 == 0 ? 6 : 7, 0);
        connect(i2, 7, i4, i4 == 0 ? 7 : 6, 0);
        if (i3 != 0) {
            connect(i3, 7, i2, 6, 0);
        }
        if (i4 != 0) {
            connect(i4, 6, i2, 7, 0);
        }
    }

    public void addToVerticalChain(int i2, int i3, int i4) {
        connect(i2, 3, i3, i3 == 0 ? 3 : 4, 0);
        connect(i2, 4, i4, i4 == 0 ? 4 : 3, 0);
        if (i3 != 0) {
            connect(i3, 4, i2, 3, 0);
        }
        if (i4 != 0) {
            connect(i4, 3, i2, 4, 0);
        }
    }

    public void applyCustomAttributes(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!this.f438c.containsKey(Integer.valueOf(id))) {
                Log.v("ConstraintSet", "id unknown " + Debug.getName(childAt));
            } else {
                if (this.b && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (this.f438c.containsKey(Integer.valueOf(id))) {
                    ConstraintAttribute.setAttributes(childAt, this.f438c.get(Integer.valueOf(id)).mCustomConstraints);
                }
            }
        }
    }

    public void applyTo(ConstraintLayout constraintLayout) {
        d(constraintLayout, true);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public void applyToHelper(ConstraintHelper constraintHelper, ConstraintWidget constraintWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        int id = constraintHelper.getId();
        if (this.f438c.containsKey(Integer.valueOf(id))) {
            Constraint constraint = this.f438c.get(Integer.valueOf(id));
            if (constraintWidget instanceof HelperWidget) {
                constraintHelper.loadParameters(constraint, (HelperWidget) constraintWidget, layoutParams, sparseArray);
            }
        }
    }

    public void applyToLayoutParams(int i2, ConstraintLayout.LayoutParams layoutParams) {
        if (this.f438c.containsKey(Integer.valueOf(i2))) {
            this.f438c.get(Integer.valueOf(i2)).applyTo(layoutParams);
        }
    }

    public void applyToWithoutCustom(ConstraintLayout constraintLayout) {
        d(constraintLayout, false);
        constraintLayout.setConstraintSet(null);
    }

    public void center(int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f) {
        ConstraintSet constraintSet;
        int i9;
        int i10;
        if (i5 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (i8 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        }
        if (f <= 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
        }
        if (i4 == 1 || i4 == 2) {
            constraintSet = this;
            i9 = i2;
            constraintSet.connect(i9, 1, i3, i4, i5);
            i10 = 2;
        } else if (i4 != 6 && i4 != 7) {
            connect(i2, 3, i3, i4, i5);
            connect(i2, 4, i6, i7, i8);
            this.f438c.get(Integer.valueOf(i2)).layout.verticalBias = f;
            return;
        } else {
            constraintSet = this;
            i9 = i2;
            constraintSet.connect(i9, 6, i3, i4, i5);
            i10 = 7;
        }
        constraintSet.connect(i9, i10, i6, i7, i8);
        this.f438c.get(Integer.valueOf(i2)).layout.horizontalBias = f;
    }

    public void centerHorizontally(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        float f;
        ConstraintSet constraintSet;
        int i8;
        int i9;
        int i10;
        if (i3 == 0) {
            i9 = 0;
            i4 = 1;
            i5 = 0;
            i10 = 0;
            i6 = 2;
            i7 = 0;
            f = 0.5f;
            constraintSet = this;
            i8 = i2;
        } else {
            i4 = 2;
            i5 = 0;
            i6 = 1;
            i7 = 0;
            f = 0.5f;
            constraintSet = this;
            i8 = i2;
            i9 = i3;
            i10 = i3;
        }
        constraintSet.center(i8, i9, i4, i5, i10, i6, i7, f);
    }

    public void centerHorizontally(int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f) {
        connect(i2, 1, i3, i4, i5);
        connect(i2, 2, i6, i7, i8);
        this.f438c.get(Integer.valueOf(i2)).layout.horizontalBias = f;
    }

    public void centerHorizontallyRtl(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        float f;
        ConstraintSet constraintSet;
        int i8;
        int i9;
        int i10;
        if (i3 == 0) {
            i9 = 0;
            i4 = 6;
            i5 = 0;
            i10 = 0;
            i6 = 7;
            i7 = 0;
            f = 0.5f;
            constraintSet = this;
            i8 = i2;
        } else {
            i4 = 7;
            i5 = 0;
            i6 = 6;
            i7 = 0;
            f = 0.5f;
            constraintSet = this;
            i8 = i2;
            i9 = i3;
            i10 = i3;
        }
        constraintSet.center(i8, i9, i4, i5, i10, i6, i7, f);
    }

    public void centerHorizontallyRtl(int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f) {
        connect(i2, 6, i3, i4, i5);
        connect(i2, 7, i6, i7, i8);
        this.f438c.get(Integer.valueOf(i2)).layout.horizontalBias = f;
    }

    public void centerVertically(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        float f;
        ConstraintSet constraintSet;
        int i8;
        int i9;
        int i10;
        if (i3 == 0) {
            i9 = 0;
            i4 = 3;
            i5 = 0;
            i10 = 0;
            i6 = 4;
            i7 = 0;
            f = 0.5f;
            constraintSet = this;
            i8 = i2;
        } else {
            i4 = 4;
            i5 = 0;
            i6 = 3;
            i7 = 0;
            f = 0.5f;
            constraintSet = this;
            i8 = i2;
            i9 = i3;
            i10 = i3;
        }
        constraintSet.center(i8, i9, i4, i5, i10, i6, i7, f);
    }

    public void centerVertically(int i2, int i3, int i4, int i5, int i6, int i7, int i8, float f) {
        connect(i2, 3, i3, i4, i5);
        connect(i2, 4, i6, i7, i8);
        this.f438c.get(Integer.valueOf(i2)).layout.verticalBias = f;
    }

    public void clear(int i2) {
        this.f438c.remove(Integer.valueOf(i2));
    }

    public void clear(int i2, int i3) {
        if (this.f438c.containsKey(Integer.valueOf(i2))) {
            Constraint constraint = this.f438c.get(Integer.valueOf(i2));
            switch (i3) {
                case 1:
                    Layout layout = constraint.layout;
                    layout.leftToRight = -1;
                    layout.leftToLeft = -1;
                    layout.leftMargin = -1;
                    layout.goneLeftMargin = -1;
                    return;
                case 2:
                    Layout layout2 = constraint.layout;
                    layout2.rightToRight = -1;
                    layout2.rightToLeft = -1;
                    layout2.rightMargin = -1;
                    layout2.goneRightMargin = -1;
                    return;
                case 3:
                    Layout layout3 = constraint.layout;
                    layout3.topToBottom = -1;
                    layout3.topToTop = -1;
                    layout3.topMargin = -1;
                    layout3.goneTopMargin = -1;
                    return;
                case 4:
                    Layout layout4 = constraint.layout;
                    layout4.bottomToTop = -1;
                    layout4.bottomToBottom = -1;
                    layout4.bottomMargin = -1;
                    layout4.goneBottomMargin = -1;
                    return;
                case 5:
                    constraint.layout.baselineToBaseline = -1;
                    return;
                case 6:
                    Layout layout5 = constraint.layout;
                    layout5.startToEnd = -1;
                    layout5.startToStart = -1;
                    layout5.startMargin = -1;
                    layout5.goneStartMargin = -1;
                    return;
                case 7:
                    Layout layout6 = constraint.layout;
                    layout6.endToStart = -1;
                    layout6.endToEnd = -1;
                    layout6.endMargin = -1;
                    layout6.goneEndMargin = -1;
                    return;
                default:
                    throw new IllegalArgumentException("unknown constraint");
            }
        }
    }

    public void clone(Context context, int i2) {
        clone((ConstraintLayout) LayoutInflater.from(context).inflate(i2, (ViewGroup) null));
    }

    public void clone(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.f438c.clear();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f438c.containsKey(Integer.valueOf(id))) {
                this.f438c.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = this.f438c.get(Integer.valueOf(id));
            constraint.mCustomConstraints = ConstraintAttribute.extractAttributes(this.a, childAt);
            constraint.h(id, layoutParams);
            constraint.propertySet.visibility = childAt.getVisibility();
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 17) {
                constraint.propertySet.alpha = childAt.getAlpha();
                constraint.transform.rotation = childAt.getRotation();
                constraint.transform.rotationX = childAt.getRotationX();
                constraint.transform.rotationY = childAt.getRotationY();
                constraint.transform.scaleX = childAt.getScaleX();
                constraint.transform.scaleY = childAt.getScaleY();
                float pivotX = childAt.getPivotX();
                float pivotY = childAt.getPivotY();
                if (pivotX != 0.0d || pivotY != 0.0d) {
                    Transform transform = constraint.transform;
                    transform.transformPivotX = pivotX;
                    transform.transformPivotY = pivotY;
                }
                constraint.transform.translationX = childAt.getTranslationX();
                constraint.transform.translationY = childAt.getTranslationY();
                if (i3 >= 21) {
                    constraint.transform.translationZ = childAt.getTranslationZ();
                    Transform transform2 = constraint.transform;
                    if (transform2.applyElevation) {
                        transform2.elevation = childAt.getElevation();
                    }
                }
            }
            if (childAt instanceof Barrier) {
                Barrier barrier = (Barrier) childAt;
                constraint.layout.mBarrierAllowsGoneWidgets = barrier.allowsGoneWidget();
                constraint.layout.mReferenceIds = barrier.getReferencedIds();
                constraint.layout.mBarrierDirection = barrier.getType();
                constraint.layout.mBarrierMargin = barrier.getMargin();
            }
        }
    }

    public void clone(ConstraintSet constraintSet) {
        this.f438c.clear();
        for (Integer num : constraintSet.f438c.keySet()) {
            this.f438c.put(num, constraintSet.f438c.get(num).m8clone());
        }
    }

    public void clone(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.f438c.clear();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraints.getChildAt(i2);
            Constraints.LayoutParams layoutParams = (Constraints.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f438c.containsKey(Integer.valueOf(id))) {
                this.f438c.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = this.f438c.get(Integer.valueOf(id));
            if (childAt instanceof ConstraintHelper) {
                constraint.j((ConstraintHelper) childAt, id, layoutParams);
            }
            constraint.i(id, layoutParams);
        }
    }

    public void connect(int i2, int i3, int i4, int i5) {
        Layout layout;
        if (!this.f438c.containsKey(Integer.valueOf(i2))) {
            this.f438c.put(Integer.valueOf(i2), new Constraint());
        }
        Constraint constraint = this.f438c.get(Integer.valueOf(i2));
        switch (i3) {
            case 1:
                if (i5 == 1) {
                    Layout layout2 = constraint.layout;
                    layout2.leftToLeft = i4;
                    layout2.leftToRight = -1;
                    return;
                } else if (i5 == 2) {
                    Layout layout3 = constraint.layout;
                    layout3.leftToRight = i4;
                    layout3.leftToLeft = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("left to " + k(i5) + " undefined");
                }
            case 2:
                if (i5 == 1) {
                    Layout layout4 = constraint.layout;
                    layout4.rightToLeft = i4;
                    layout4.rightToRight = -1;
                    return;
                } else if (i5 == 2) {
                    Layout layout5 = constraint.layout;
                    layout5.rightToRight = i4;
                    layout5.rightToLeft = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + k(i5) + " undefined");
                }
            case 3:
                if (i5 == 3) {
                    layout = constraint.layout;
                    layout.topToTop = i4;
                    layout.topToBottom = -1;
                    break;
                } else {
                    if (i5 != 4) {
                        throw new IllegalArgumentException("right to " + k(i5) + " undefined");
                    }
                    layout = constraint.layout;
                    layout.topToBottom = i4;
                    layout.topToTop = -1;
                    break;
                }
            case 4:
                if (i5 == 4) {
                    layout = constraint.layout;
                    layout.bottomToBottom = i4;
                    layout.bottomToTop = -1;
                    break;
                } else {
                    if (i5 != 3) {
                        throw new IllegalArgumentException("right to " + k(i5) + " undefined");
                    }
                    layout = constraint.layout;
                    layout.bottomToTop = i4;
                    layout.bottomToBottom = -1;
                    break;
                }
            case 5:
                if (i5 != 5) {
                    throw new IllegalArgumentException("right to " + k(i5) + " undefined");
                }
                Layout layout6 = constraint.layout;
                layout6.baselineToBaseline = i4;
                layout6.bottomToBottom = -1;
                layout6.bottomToTop = -1;
                layout6.topToTop = -1;
                layout6.topToBottom = -1;
                return;
            case 6:
                if (i5 == 6) {
                    Layout layout7 = constraint.layout;
                    layout7.startToStart = i4;
                    layout7.startToEnd = -1;
                    return;
                } else if (i5 == 7) {
                    Layout layout8 = constraint.layout;
                    layout8.startToEnd = i4;
                    layout8.startToStart = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + k(i5) + " undefined");
                }
            case 7:
                if (i5 == 7) {
                    Layout layout9 = constraint.layout;
                    layout9.endToEnd = i4;
                    layout9.endToStart = -1;
                    return;
                } else if (i5 == 6) {
                    Layout layout10 = constraint.layout;
                    layout10.endToStart = i4;
                    layout10.endToEnd = -1;
                    return;
                } else {
                    throw new IllegalArgumentException("right to " + k(i5) + " undefined");
                }
            default:
                throw new IllegalArgumentException(k(i3) + " to " + k(i5) + " unknown");
        }
        layout.baselineToBaseline = -1;
    }

    public void connect(int i2, int i3, int i4, int i5, int i6) {
        Layout layout;
        Layout layout2;
        if (!this.f438c.containsKey(Integer.valueOf(i2))) {
            this.f438c.put(Integer.valueOf(i2), new Constraint());
        }
        Constraint constraint = this.f438c.get(Integer.valueOf(i2));
        switch (i3) {
            case 1:
                if (i5 == 1) {
                    Layout layout3 = constraint.layout;
                    layout3.leftToLeft = i4;
                    layout3.leftToRight = -1;
                } else {
                    if (i5 != 2) {
                        throw new IllegalArgumentException("Left to " + k(i5) + " undefined");
                    }
                    Layout layout4 = constraint.layout;
                    layout4.leftToRight = i4;
                    layout4.leftToLeft = -1;
                }
                constraint.layout.leftMargin = i6;
                return;
            case 2:
                if (i5 == 1) {
                    Layout layout5 = constraint.layout;
                    layout5.rightToLeft = i4;
                    layout5.rightToRight = -1;
                } else {
                    if (i5 != 2) {
                        throw new IllegalArgumentException("right to " + k(i5) + " undefined");
                    }
                    Layout layout6 = constraint.layout;
                    layout6.rightToRight = i4;
                    layout6.rightToLeft = -1;
                }
                constraint.layout.rightMargin = i6;
                return;
            case 3:
                if (i5 == 3) {
                    layout = constraint.layout;
                    layout.topToTop = i4;
                    layout.topToBottom = -1;
                } else {
                    if (i5 != 4) {
                        throw new IllegalArgumentException("right to " + k(i5) + " undefined");
                    }
                    layout = constraint.layout;
                    layout.topToBottom = i4;
                    layout.topToTop = -1;
                }
                layout.baselineToBaseline = -1;
                constraint.layout.topMargin = i6;
                return;
            case 4:
                if (i5 == 4) {
                    layout2 = constraint.layout;
                    layout2.bottomToBottom = i4;
                    layout2.bottomToTop = -1;
                } else {
                    if (i5 != 3) {
                        throw new IllegalArgumentException("right to " + k(i5) + " undefined");
                    }
                    layout2 = constraint.layout;
                    layout2.bottomToTop = i4;
                    layout2.bottomToBottom = -1;
                }
                layout2.baselineToBaseline = -1;
                constraint.layout.bottomMargin = i6;
                return;
            case 5:
                if (i5 != 5) {
                    throw new IllegalArgumentException("right to " + k(i5) + " undefined");
                }
                Layout layout7 = constraint.layout;
                layout7.baselineToBaseline = i4;
                layout7.bottomToBottom = -1;
                layout7.bottomToTop = -1;
                layout7.topToTop = -1;
                layout7.topToBottom = -1;
                return;
            case 6:
                if (i5 == 6) {
                    Layout layout8 = constraint.layout;
                    layout8.startToStart = i4;
                    layout8.startToEnd = -1;
                } else {
                    if (i5 != 7) {
                        throw new IllegalArgumentException("right to " + k(i5) + " undefined");
                    }
                    Layout layout9 = constraint.layout;
                    layout9.startToEnd = i4;
                    layout9.startToStart = -1;
                }
                constraint.layout.startMargin = i6;
                return;
            case 7:
                if (i5 == 7) {
                    Layout layout10 = constraint.layout;
                    layout10.endToEnd = i4;
                    layout10.endToStart = -1;
                } else {
                    if (i5 != 6) {
                        throw new IllegalArgumentException("right to " + k(i5) + " undefined");
                    }
                    Layout layout11 = constraint.layout;
                    layout11.endToStart = i4;
                    layout11.endToEnd = -1;
                }
                constraint.layout.endMargin = i6;
                return;
            default:
                throw new IllegalArgumentException(k(i3) + " to " + k(i5) + " unknown");
        }
    }

    public void constrainCircle(int i2, int i3, int i4, float f) {
        Layout layout = h(i2).layout;
        layout.circleConstraint = i3;
        layout.circleRadius = i4;
        layout.circleAngle = f;
    }

    public void constrainDefaultHeight(int i2, int i3) {
        h(i2).layout.heightDefault = i3;
    }

    public void constrainDefaultWidth(int i2, int i3) {
        h(i2).layout.widthDefault = i3;
    }

    public void constrainHeight(int i2, int i3) {
        h(i2).layout.mHeight = i3;
    }

    public void constrainMaxHeight(int i2, int i3) {
        h(i2).layout.heightMax = i3;
    }

    public void constrainMaxWidth(int i2, int i3) {
        h(i2).layout.widthMax = i3;
    }

    public void constrainMinHeight(int i2, int i3) {
        h(i2).layout.heightMin = i3;
    }

    public void constrainMinWidth(int i2, int i3) {
        h(i2).layout.widthMin = i3;
    }

    public void constrainPercentHeight(int i2, float f) {
        h(i2).layout.heightPercent = f;
    }

    public void constrainPercentWidth(int i2, float f) {
        h(i2).layout.widthPercent = f;
    }

    public void constrainWidth(int i2, int i3) {
        h(i2).layout.mWidth = i3;
    }

    public void constrainedHeight(int i2, boolean z) {
        h(i2).layout.constrainedHeight = z;
    }

    public void constrainedWidth(int i2, boolean z) {
        h(i2).layout.constrainedWidth = z;
    }

    public void create(int i2, int i3) {
        Layout layout = h(i2).layout;
        layout.mIsGuideline = true;
        layout.orientation = i3;
    }

    public void createBarrier(int i2, int i3, int i4, int... iArr) {
        Layout layout = h(i2).layout;
        layout.mHelperType = 1;
        layout.mBarrierDirection = i3;
        layout.mBarrierMargin = i4;
        layout.mIsGuideline = false;
        layout.mReferenceIds = iArr;
    }

    public void createHorizontalChain(int i2, int i3, int i4, int i5, int[] iArr, float[] fArr, int i6) {
        f(i2, i3, i4, i5, iArr, fArr, i6, 1, 2);
    }

    public void createHorizontalChainRtl(int i2, int i3, int i4, int i5, int[] iArr, float[] fArr, int i6) {
        f(i2, i3, i4, i5, iArr, fArr, i6, 6, 7);
    }

    public void createVerticalChain(int i2, int i3, int i4, int i5, int[] iArr, float[] fArr, int i6) {
        if (iArr.length < 2) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (fArr != null && fArr.length != iArr.length) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
        if (fArr != null) {
            h(iArr[0]).layout.verticalWeight = fArr[0];
        }
        h(iArr[0]).layout.verticalChainStyle = i6;
        connect(iArr[0], 3, i2, i3, 0);
        for (int i7 = 1; i7 < iArr.length; i7++) {
            int i8 = iArr[i7];
            int i9 = i7 - 1;
            connect(iArr[i7], 3, iArr[i9], 4, 0);
            connect(iArr[i9], 4, iArr[i7], 3, 0);
            if (fArr != null) {
                h(iArr[i7]).layout.verticalWeight = fArr[i7];
            }
        }
        connect(iArr[iArr.length - 1], 4, i4, i5, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet hashSet = new HashSet(this.f438c.keySet());
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            int id = childAt.getId();
            if (!this.f438c.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + Debug.getName(childAt));
            } else {
                if (this.b && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (this.f438c.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        Constraint constraint = this.f438c.get(Integer.valueOf(id));
                        if (childAt instanceof Barrier) {
                            constraint.layout.mHelperType = 1;
                        }
                        int i3 = constraint.layout.mHelperType;
                        if (i3 != -1 && i3 == 1) {
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(constraint.layout.mBarrierDirection);
                            barrier.setMargin(constraint.layout.mBarrierMargin);
                            barrier.setAllowsGoneWidget(constraint.layout.mBarrierAllowsGoneWidgets);
                            Layout layout = constraint.layout;
                            int[] iArr = layout.mReferenceIds;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = layout.mReferenceIdString;
                                if (str != null) {
                                    layout.mReferenceIds = e(barrier, str);
                                    barrier.setReferencedIds(constraint.layout.mReferenceIds);
                                }
                            }
                        }
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                        layoutParams.validate();
                        constraint.applyTo(layoutParams);
                        if (z) {
                            ConstraintAttribute.setAttributes(childAt, constraint.mCustomConstraints);
                        }
                        childAt.setLayoutParams(layoutParams);
                        PropertySet propertySet = constraint.propertySet;
                        if (propertySet.mVisibilityMode == 0) {
                            childAt.setVisibility(propertySet.visibility);
                        }
                        int i4 = Build.VERSION.SDK_INT;
                        if (i4 >= 17) {
                            childAt.setAlpha(constraint.propertySet.alpha);
                            childAt.setRotation(constraint.transform.rotation);
                            childAt.setRotationX(constraint.transform.rotationX);
                            childAt.setRotationY(constraint.transform.rotationY);
                            childAt.setScaleX(constraint.transform.scaleX);
                            childAt.setScaleY(constraint.transform.scaleY);
                            if (!Float.isNaN(constraint.transform.transformPivotX)) {
                                childAt.setPivotX(constraint.transform.transformPivotX);
                            }
                            if (!Float.isNaN(constraint.transform.transformPivotY)) {
                                childAt.setPivotY(constraint.transform.transformPivotY);
                            }
                            childAt.setTranslationX(constraint.transform.translationX);
                            childAt.setTranslationY(constraint.transform.translationY);
                            if (i4 >= 21) {
                                childAt.setTranslationZ(constraint.transform.translationZ);
                                Transform transform = constraint.transform;
                                if (transform.applyElevation) {
                                    childAt.setElevation(transform.elevation);
                                }
                            }
                        }
                    } else {
                        Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + id);
                    }
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            Constraint constraint2 = this.f438c.get(num);
            int i5 = constraint2.layout.mHelperType;
            if (i5 != -1 && i5 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                Layout layout2 = constraint2.layout;
                int[] iArr2 = layout2.mReferenceIds;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = layout2.mReferenceIdString;
                    if (str2 != null) {
                        layout2.mReferenceIds = e(barrier2, str2);
                        barrier2.setReferencedIds(constraint2.layout.mReferenceIds);
                    }
                }
                barrier2.setType(constraint2.layout.mBarrierDirection);
                barrier2.setMargin(constraint2.layout.mBarrierMargin);
                ConstraintLayout.LayoutParams generateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                barrier2.validateParams();
                constraint2.applyTo(generateDefaultLayoutParams);
                constraintLayout.addView(barrier2, generateDefaultLayoutParams);
            }
            if (constraint2.layout.mIsGuideline) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                ConstraintLayout.LayoutParams generateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                constraint2.applyTo(generateDefaultLayoutParams2);
                constraintLayout.addView(guideline, generateDefaultLayoutParams2);
            }
        }
    }

    public void dump(MotionScene motionScene, int... iArr) {
        HashSet hashSet;
        Set<Integer> keySet = this.f438c.keySet();
        if (iArr.length != 0) {
            hashSet = new HashSet();
            for (int i2 : iArr) {
                hashSet.add(Integer.valueOf(i2));
            }
        } else {
            hashSet = new HashSet(keySet);
        }
        System.out.println(hashSet.size() + " constraints");
        StringBuilder sb = new StringBuilder();
        for (Integer num : (Integer[]) hashSet.toArray(new Integer[0])) {
            Constraint constraint = this.f438c.get(num);
            sb.append("<Constraint id=");
            sb.append(num);
            sb.append(" \n");
            constraint.layout.dump(motionScene, sb);
            sb.append("/>\n");
        }
        System.out.println(sb.toString());
    }

    public boolean getApplyElevation(int i2) {
        return h(i2).transform.applyElevation;
    }

    public Constraint getConstraint(int i2) {
        if (this.f438c.containsKey(Integer.valueOf(i2))) {
            return this.f438c.get(Integer.valueOf(i2));
        }
        return null;
    }

    public HashMap<String, ConstraintAttribute> getCustomAttributeSet() {
        return this.a;
    }

    public int getHeight(int i2) {
        return h(i2).layout.mHeight;
    }

    public int[] getKnownIds() {
        Integer[] numArr = (Integer[]) this.f438c.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = numArr[i2].intValue();
        }
        return iArr;
    }

    public Constraint getParameters(int i2) {
        return h(i2);
    }

    public int[] getReferencedIds(int i2) {
        int[] iArr = h(i2).layout.mReferenceIds;
        return iArr == null ? new int[0] : Arrays.copyOf(iArr, iArr.length);
    }

    public int getVisibility(int i2) {
        return h(i2).propertySet.visibility;
    }

    public int getVisibilityMode(int i2) {
        return h(i2).propertySet.mVisibilityMode;
    }

    public int getWidth(int i2) {
        return h(i2).layout.mWidth;
    }

    public boolean isForceId() {
        return this.b;
    }

    public void load(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    Constraint g = g(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        g.layout.mIsGuideline = true;
                    }
                    this.f438c.put(Integer.valueOf(g.a), g);
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x0179, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:27:0x0093. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void load(android.content.Context r10, org.xmlpull.v1.XmlPullParser r11) {
        /*
            Method dump skipped, instructions count: 448
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintSet.load(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public void parseColorAttributes(Constraint constraint, String str) {
        String[] split = str.split(",");
        for (int i2 = 0; i2 < split.length; i2++) {
            String[] split2 = split[i2].split("=");
            if (split2.length != 2) {
                Log.w("ConstraintSet", " Unable to parse " + split[i2]);
            } else {
                constraint.l(split2[0], Color.parseColor(split2[1]));
            }
        }
    }

    public void parseFloatAttributes(Constraint constraint, String str) {
        String[] split = str.split(",");
        for (int i2 = 0; i2 < split.length; i2++) {
            String[] split2 = split[i2].split("=");
            if (split2.length != 2) {
                Log.w("ConstraintSet", " Unable to parse " + split[i2]);
            } else {
                constraint.m(split2[0], Float.parseFloat(split2[1]));
            }
        }
    }

    public void parseIntAttributes(Constraint constraint, String str) {
        String[] split = str.split(",");
        for (int i2 = 0; i2 < split.length; i2++) {
            String[] split2 = split[i2].split("=");
            if (split2.length != 2) {
                Log.w("ConstraintSet", " Unable to parse " + split[i2]);
            } else {
                constraint.m(split2[0], Integer.decode(split2[1]).intValue());
            }
        }
    }

    public void parseStringAttributes(Constraint constraint, String str) {
        String[] l = l(str);
        for (int i2 = 0; i2 < l.length; i2++) {
            String[] split = l[i2].split("=");
            Log.w("ConstraintSet", " Unable to parse " + l[i2]);
            constraint.o(split[0], split[1]);
        }
    }

    public void readFallback(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = constraintLayout.getChildAt(i2);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (this.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!this.f438c.containsKey(Integer.valueOf(id))) {
                this.f438c.put(Integer.valueOf(id), new Constraint());
            }
            Constraint constraint = this.f438c.get(Integer.valueOf(id));
            if (!constraint.layout.mApply) {
                constraint.h(id, layoutParams);
                if (childAt instanceof ConstraintHelper) {
                    constraint.layout.mReferenceIds = ((ConstraintHelper) childAt).getReferencedIds();
                    if (childAt instanceof Barrier) {
                        Barrier barrier = (Barrier) childAt;
                        constraint.layout.mBarrierAllowsGoneWidgets = barrier.allowsGoneWidget();
                        constraint.layout.mBarrierDirection = barrier.getType();
                        constraint.layout.mBarrierMargin = barrier.getMargin();
                    }
                }
                constraint.layout.mApply = true;
            }
            PropertySet propertySet = constraint.propertySet;
            if (!propertySet.mApply) {
                propertySet.visibility = childAt.getVisibility();
                constraint.propertySet.alpha = childAt.getAlpha();
                constraint.propertySet.mApply = true;
            }
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 17) {
                Transform transform = constraint.transform;
                if (!transform.mApply) {
                    transform.mApply = true;
                    transform.rotation = childAt.getRotation();
                    constraint.transform.rotationX = childAt.getRotationX();
                    constraint.transform.rotationY = childAt.getRotationY();
                    constraint.transform.scaleX = childAt.getScaleX();
                    constraint.transform.scaleY = childAt.getScaleY();
                    float pivotX = childAt.getPivotX();
                    float pivotY = childAt.getPivotY();
                    if (pivotX != 0.0d || pivotY != 0.0d) {
                        Transform transform2 = constraint.transform;
                        transform2.transformPivotX = pivotX;
                        transform2.transformPivotY = pivotY;
                    }
                    constraint.transform.translationX = childAt.getTranslationX();
                    constraint.transform.translationY = childAt.getTranslationY();
                    if (i3 >= 21) {
                        constraint.transform.translationZ = childAt.getTranslationZ();
                        Transform transform3 = constraint.transform;
                        if (transform3.applyElevation) {
                            transform3.elevation = childAt.getElevation();
                        }
                    }
                }
            }
        }
    }

    public void readFallback(ConstraintSet constraintSet) {
        for (Integer num : constraintSet.f438c.keySet()) {
            int intValue = num.intValue();
            Constraint constraint = constraintSet.f438c.get(num);
            if (!this.f438c.containsKey(Integer.valueOf(intValue))) {
                this.f438c.put(Integer.valueOf(intValue), new Constraint());
            }
            Constraint constraint2 = this.f438c.get(Integer.valueOf(intValue));
            Layout layout = constraint2.layout;
            if (!layout.mApply) {
                layout.copyFrom(constraint.layout);
            }
            PropertySet propertySet = constraint2.propertySet;
            if (!propertySet.mApply) {
                propertySet.copyFrom(constraint.propertySet);
            }
            Transform transform = constraint2.transform;
            if (!transform.mApply) {
                transform.copyFrom(constraint.transform);
            }
            Motion motion = constraint2.motion;
            if (!motion.mApply) {
                motion.copyFrom(constraint.motion);
            }
            for (String str : constraint.mCustomConstraints.keySet()) {
                if (!constraint2.mCustomConstraints.containsKey(str)) {
                    constraint2.mCustomConstraints.put(str, constraint.mCustomConstraints.get(str));
                }
            }
        }
    }

    public void removeAttribute(String str) {
        this.a.remove(str);
    }

    public void removeFromHorizontalChain(int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        ConstraintSet constraintSet;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        ConstraintSet constraintSet2;
        int i13;
        if (this.f438c.containsKey(Integer.valueOf(i2))) {
            Layout layout = this.f438c.get(Integer.valueOf(i2)).layout;
            int i14 = layout.leftToRight;
            int i15 = layout.rightToLeft;
            if (i14 == -1 && i15 == -1) {
                int i16 = layout.startToEnd;
                int i17 = layout.endToStart;
                if (i16 != -1 || i17 != -1) {
                    if (i16 != -1 && i17 != -1) {
                        i12 = 0;
                        constraintSet2 = this;
                        constraintSet2.connect(i16, 7, i17, 6, 0);
                        i10 = 6;
                        i11 = 7;
                        i13 = i17;
                        i9 = i14;
                    } else if (i14 != -1 || i17 != -1) {
                        i9 = layout.rightToRight;
                        if (i9 != -1) {
                            i10 = 7;
                            i11 = 7;
                            i12 = 0;
                            constraintSet2 = this;
                            i13 = i14;
                        } else {
                            i9 = layout.leftToLeft;
                            if (i9 != -1) {
                                i10 = 6;
                                i11 = 6;
                                i12 = 0;
                                constraintSet2 = this;
                                i13 = i17;
                            }
                        }
                    }
                    constraintSet2.connect(i13, i10, i9, i11, i12);
                }
                clear(i2, 6);
                i8 = 7;
            } else {
                if (i14 == -1 || i15 == -1) {
                    if (i14 != -1 || i15 != -1) {
                        i3 = layout.rightToRight;
                        if (i3 != -1) {
                            i4 = 2;
                            i5 = 2;
                            i6 = 0;
                            constraintSet = this;
                            i7 = i14;
                        } else {
                            i3 = layout.leftToLeft;
                            if (i3 != -1) {
                                i4 = 1;
                                i5 = 1;
                                i6 = 0;
                                constraintSet = this;
                                i7 = i15;
                            }
                        }
                    }
                    clear(i2, 1);
                    i8 = 2;
                } else {
                    i6 = 0;
                    constraintSet = this;
                    constraintSet.connect(i14, 2, i15, 1, 0);
                    i4 = 1;
                    i5 = 2;
                    i7 = i15;
                    i3 = i14;
                }
                constraintSet.connect(i7, i4, i3, i5, i6);
                clear(i2, 1);
                i8 = 2;
            }
            clear(i2, i8);
        }
    }

    public void removeFromVerticalChain(int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        ConstraintSet constraintSet;
        int i7;
        if (this.f438c.containsKey(Integer.valueOf(i2))) {
            Layout layout = this.f438c.get(Integer.valueOf(i2)).layout;
            int i8 = layout.topToBottom;
            int i9 = layout.bottomToTop;
            if (i8 != -1 || i9 != -1) {
                if (i8 != -1 && i9 != -1) {
                    i6 = 0;
                    constraintSet = this;
                    constraintSet.connect(i8, 4, i9, 3, 0);
                    i4 = 3;
                    i5 = 4;
                    i7 = i9;
                    i3 = i8;
                } else if (i8 != -1 || i9 != -1) {
                    i3 = layout.bottomToBottom;
                    if (i3 != -1) {
                        i4 = 4;
                        i5 = 4;
                        i6 = 0;
                        constraintSet = this;
                        i7 = i8;
                    } else {
                        i3 = layout.topToTop;
                        if (i3 != -1) {
                            i4 = 3;
                            i5 = 3;
                            i6 = 0;
                            constraintSet = this;
                            i7 = i9;
                        }
                    }
                }
                constraintSet.connect(i7, i4, i3, i5, i6);
            }
        }
        clear(i2, 3);
        clear(i2, 4);
    }

    public void setAlpha(int i2, float f) {
        h(i2).propertySet.alpha = f;
    }

    public void setApplyElevation(int i2, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            h(i2).transform.applyElevation = z;
        }
    }

    public void setBarrierType(int i2, int i3) {
        h(i2).layout.mHelperType = i3;
    }

    public void setColorValue(int i2, String str, int i3) {
        h(i2).l(str, i3);
    }

    public void setDimensionRatio(int i2, String str) {
        h(i2).layout.dimensionRatio = str;
    }

    public void setEditorAbsoluteX(int i2, int i3) {
        h(i2).layout.editorAbsoluteX = i3;
    }

    public void setEditorAbsoluteY(int i2, int i3) {
        h(i2).layout.editorAbsoluteY = i3;
    }

    public void setElevation(int i2, float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            h(i2).transform.elevation = f;
            h(i2).transform.applyElevation = true;
        }
    }

    public void setFloatValue(int i2, String str, float f) {
        h(i2).m(str, f);
    }

    public void setForceId(boolean z) {
        this.b = z;
    }

    public void setGoneMargin(int i2, int i3, int i4) {
        Constraint h = h(i2);
        switch (i3) {
            case 1:
                h.layout.goneLeftMargin = i4;
                return;
            case 2:
                h.layout.goneRightMargin = i4;
                return;
            case 3:
                h.layout.goneTopMargin = i4;
                return;
            case 4:
                h.layout.goneBottomMargin = i4;
                return;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                h.layout.goneStartMargin = i4;
                return;
            case 7:
                h.layout.goneEndMargin = i4;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void setGuidelineBegin(int i2, int i3) {
        h(i2).layout.guideBegin = i3;
        h(i2).layout.guideEnd = -1;
        h(i2).layout.guidePercent = -1.0f;
    }

    public void setGuidelineEnd(int i2, int i3) {
        h(i2).layout.guideEnd = i3;
        h(i2).layout.guideBegin = -1;
        h(i2).layout.guidePercent = -1.0f;
    }

    public void setGuidelinePercent(int i2, float f) {
        h(i2).layout.guidePercent = f;
        h(i2).layout.guideEnd = -1;
        h(i2).layout.guideBegin = -1;
    }

    public void setHorizontalBias(int i2, float f) {
        h(i2).layout.horizontalBias = f;
    }

    public void setHorizontalChainStyle(int i2, int i3) {
        h(i2).layout.horizontalChainStyle = i3;
    }

    public void setHorizontalWeight(int i2, float f) {
        h(i2).layout.horizontalWeight = f;
    }

    public void setIntValue(int i2, String str, int i3) {
        h(i2).n(str, i3);
    }

    public void setMargin(int i2, int i3, int i4) {
        Constraint h = h(i2);
        switch (i3) {
            case 1:
                h.layout.leftMargin = i4;
                return;
            case 2:
                h.layout.rightMargin = i4;
                return;
            case 3:
                h.layout.topMargin = i4;
                return;
            case 4:
                h.layout.bottomMargin = i4;
                return;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                h.layout.startMargin = i4;
                return;
            case 7:
                h.layout.endMargin = i4;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void setReferencedIds(int i2, int... iArr) {
        h(i2).layout.mReferenceIds = iArr;
    }

    public void setRotation(int i2, float f) {
        h(i2).transform.rotation = f;
    }

    public void setRotationX(int i2, float f) {
        h(i2).transform.rotationX = f;
    }

    public void setRotationY(int i2, float f) {
        h(i2).transform.rotationY = f;
    }

    public void setScaleX(int i2, float f) {
        h(i2).transform.scaleX = f;
    }

    public void setScaleY(int i2, float f) {
        h(i2).transform.scaleY = f;
    }

    public void setStringValue(int i2, String str, String str2) {
        h(i2).o(str, str2);
    }

    public void setTransformPivot(int i2, float f, float f2) {
        Transform transform = h(i2).transform;
        transform.transformPivotY = f2;
        transform.transformPivotX = f;
    }

    public void setTransformPivotX(int i2, float f) {
        h(i2).transform.transformPivotX = f;
    }

    public void setTransformPivotY(int i2, float f) {
        h(i2).transform.transformPivotY = f;
    }

    public void setTranslation(int i2, float f, float f2) {
        Transform transform = h(i2).transform;
        transform.translationX = f;
        transform.translationY = f2;
    }

    public void setTranslationX(int i2, float f) {
        h(i2).transform.translationX = f;
    }

    public void setTranslationY(int i2, float f) {
        h(i2).transform.translationY = f;
    }

    public void setTranslationZ(int i2, float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            h(i2).transform.translationZ = f;
        }
    }

    public void setValidateOnParse(boolean z) {
    }

    public void setVerticalBias(int i2, float f) {
        h(i2).layout.verticalBias = f;
    }

    public void setVerticalChainStyle(int i2, int i3) {
        h(i2).layout.verticalChainStyle = i3;
    }

    public void setVerticalWeight(int i2, float f) {
        h(i2).layout.verticalWeight = f;
    }

    public void setVisibility(int i2, int i3) {
        h(i2).propertySet.visibility = i3;
    }

    public void setVisibilityMode(int i2, int i3) {
        h(i2).propertySet.mVisibilityMode = i3;
    }
}
