package android.support.transition;

import android.annotation.SuppressLint;
import android.support.annotation.StyleableRes;

@SuppressLint({"InlinedApi"})
/* loaded from: lib/Mus.dex */
class Styleable {

    @StyleableRes
    static final int[] TRANSITION_TARGET = {android.R.attr.targetClass, android.R.attr.targetId, android.R.attr.excludeId, android.R.attr.excludeClass, android.R.attr.targetName, android.R.attr.excludeName};

    @StyleableRes
    static final int[] TRANSITION_MANAGER = {android.R.attr.fromScene, android.R.attr.toScene, android.R.attr.transition};

    @StyleableRes
    static final int[] TRANSITION = {android.R.attr.interpolator, android.R.attr.duration, android.R.attr.startDelay, android.R.attr.matchOrder};

    @StyleableRes
    static final int[] CHANGE_BOUNDS = {android.R.attr.resizeClip};

    @StyleableRes
    static final int[] VISIBILITY_TRANSITION = {android.R.attr.transitionVisibilityMode};

    @StyleableRes
    static final int[] FADE = {android.R.attr.fadingMode};

    @StyleableRes
    static final int[] CHANGE_TRANSFORM = {android.R.attr.reparent, android.R.attr.reparentWithOverlay};

    @StyleableRes
    static final int[] SLIDE = {android.R.attr.slideEdge};

    @StyleableRes
    static final int[] TRANSITION_SET = {android.R.attr.transitionOrdering};

    @StyleableRes
    static final int[] ARC_MOTION = {android.R.attr.minimumHorizontalAngle, android.R.attr.minimumVerticalAngle, android.R.attr.maximumAngle};

    @StyleableRes
    static final int[] PATTERN_PATH_MOTION = {android.R.attr.patternPathData};

    /* loaded from: lib/Mus.dex */
    interface ArcMotion {

        @StyleableRes
        public static final int MAXIMUM_ANGLE = 2;

        @StyleableRes
        public static final int MINIMUM_HORIZONTAL_ANGLE = 0;

        @StyleableRes
        public static final int MINIMUM_VERTICAL_ANGLE = 1;
    }

    /* loaded from: lib/Mus.dex */
    interface ChangeBounds {

        @StyleableRes
        public static final int RESIZE_CLIP = 0;
    }

    /* loaded from: lib/Mus.dex */
    interface ChangeTransform {

        @StyleableRes
        public static final int REPARENT = 0;

        @StyleableRes
        public static final int REPARENT_WITH_OVERLAY = 1;
    }

    /* loaded from: lib/Mus.dex */
    interface Fade {

        @StyleableRes
        public static final int FADING_MODE = 0;
    }

    /* loaded from: lib/Mus.dex */
    interface PatternPathMotion {

        @StyleableRes
        public static final int PATTERN_PATH_DATA = 0;
    }

    /* loaded from: lib/Mus.dex */
    interface Slide {

        @StyleableRes
        public static final int SLIDE_EDGE = 0;
    }

    /* loaded from: lib/Mus.dex */
    interface Transition {

        @StyleableRes
        public static final int DURATION = 1;

        @StyleableRes
        public static final int INTERPOLATOR = 0;

        @StyleableRes
        public static final int MATCH_ORDER = 3;

        @StyleableRes
        public static final int START_DELAY = 2;
    }

    /* loaded from: lib/Mus.dex */
    interface TransitionManager {

        @StyleableRes
        public static final int FROM_SCENE = 0;

        @StyleableRes
        public static final int TO_SCENE = 1;

        @StyleableRes
        public static final int TRANSITION = 2;
    }

    /* loaded from: lib/Mus.dex */
    interface TransitionSet {

        @StyleableRes
        public static final int TRANSITION_ORDERING = 0;
    }

    /* loaded from: lib/Mus.dex */
    interface TransitionTarget {

        @StyleableRes
        public static final int EXCLUDE_CLASS = 3;

        @StyleableRes
        public static final int EXCLUDE_ID = 2;

        @StyleableRes
        public static final int EXCLUDE_NAME = 5;

        @StyleableRes
        public static final int TARGET_CLASS = 0;

        @StyleableRes
        public static final int TARGET_ID = 1;

        @StyleableRes
        public static final int TARGET_NAME = 4;
    }

    /* loaded from: lib/Mus.dex */
    interface VisibilityTransition {

        @StyleableRes
        public static final int TRANSITION_VISIBILITY_MODE = 0;
    }

    Styleable() {
    }
}
