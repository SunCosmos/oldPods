package androidx.constraintlayout.solver.widgets;

import android.support.v7.widget.ActivityChooserView;
import androidx.constraintlayout.solver.Cache;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.analyzer.ChainRun;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ConstraintWidget {
    public static final int ANCHOR_BASELINE = 4;
    public static final int ANCHOR_BOTTOM = 3;
    public static final int ANCHOR_LEFT = 0;
    public static final int ANCHOR_RIGHT = 1;
    public static final int ANCHOR_TOP = 2;
    public static final int BOTH = 2;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int UNKNOWN = -1;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    protected int A;
    int B;
    protected int C;
    protected int D;
    float E;
    float F;
    private Object G;
    private int H;
    private int I;
    private String J;
    private String K;
    int L;
    int M;
    int N;
    int O;
    boolean P;
    boolean Q;
    boolean R;
    boolean S;
    boolean T;
    boolean U;
    int V;
    int W;
    boolean X;
    boolean Y;
    protected ConstraintWidget[] Z;
    private boolean a;
    protected ConstraintWidget[] a0;
    private boolean b;
    ConstraintWidget b0;

    /* renamed from: c, reason: collision with root package name */
    private boolean f383c;
    ConstraintWidget c0;

    /* renamed from: d, reason: collision with root package name */
    private boolean f384d;
    private boolean e;
    int f;
    float g;
    private int[] h;
    public ChainRun horizontalChainRun;
    public int horizontalGroup;
    public HorizontalWidgetRun horizontalRun;

    /* renamed from: i, reason: collision with root package name */
    private float f385i;
    public boolean[] isTerminalWidget;
    private boolean j;
    private boolean k;
    private boolean l;
    private int m;
    public ConstraintAnchor mBaseline;
    public ConstraintAnchor mBottom;
    public ConstraintAnchor mCenter;
    public float mDimensionRatio;
    public int mHorizontalResolution;
    public boolean mIsHeightWrapContent;
    public boolean mIsWidthWrapContent;
    public ConstraintAnchor mLeft;
    public ConstraintAnchor[] mListAnchors;
    public DimensionBehaviour[] mListDimensionBehaviors;
    public int mMatchConstraintDefaultHeight;
    public int mMatchConstraintDefaultWidth;
    public int mMatchConstraintMaxHeight;
    public int mMatchConstraintMaxWidth;
    public int mMatchConstraintMinHeight;
    public int mMatchConstraintMinWidth;
    public float mMatchConstraintPercentHeight;
    public float mMatchConstraintPercentWidth;
    public ConstraintWidget mParent;
    public int[] mResolvedMatchConstraintDefault;
    public ConstraintAnchor mRight;
    public ConstraintAnchor mTop;
    public int mVerticalResolution;
    public float[] mWeight;
    public boolean measured;
    private int n;
    ConstraintAnchor o;
    ConstraintAnchor p;
    protected ArrayList<ConstraintAnchor> q;
    private boolean[] r;
    public WidgetRun[] run;
    int s;
    int t;
    protected int u;
    protected int v;
    public ChainRun verticalChainRun;
    public int verticalGroup;
    public VerticalWidgetRun verticalRun;
    protected int w;
    int x;
    int y;
    protected int z;

    /* loaded from: classes.dex */
    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[DimensionBehaviour.values().length];
            b = iArr;
            try {
                iArr[DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ConstraintAnchor.Type.values().length];
            a = iArr2;
            try {
                iArr2[ConstraintAnchor.Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[ConstraintAnchor.Type.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[ConstraintAnchor.Type.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[ConstraintAnchor.Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[ConstraintAnchor.Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[ConstraintAnchor.Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[ConstraintAnchor.Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                a[ConstraintAnchor.Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                a[ConstraintAnchor.Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    public ConstraintWidget() {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.a = true;
        this.b = false;
        this.f383c = true;
        this.f384d = false;
        this.e = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.f = -1;
        this.g = 1.0f;
        this.h = new int[]{ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
        this.f385i = 0.0f;
        this.j = false;
        this.l = false;
        this.m = 0;
        this.n = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.o = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.p = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.q = new ArrayList<>();
        this.r = new boolean[2];
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors = new DimensionBehaviour[]{dimensionBehaviour, dimensionBehaviour};
        this.mParent = null;
        this.s = 0;
        this.t = 0;
        this.mDimensionRatio = 0.0f;
        this.u = -1;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        float f = DEFAULT_BIAS;
        this.E = f;
        this.F = f;
        this.H = 0;
        this.I = 0;
        this.J = null;
        this.K = null;
        this.V = 0;
        this.W = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.Z = new ConstraintWidget[]{null, null};
        this.a0 = new ConstraintWidget[]{null, null};
        this.b0 = null;
        this.c0 = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        a();
    }

    public ConstraintWidget(int i2, int i3) {
        this(0, 0, i2, i3);
    }

    public ConstraintWidget(int i2, int i3, int i4, int i5) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.a = true;
        this.b = false;
        this.f383c = true;
        this.f384d = false;
        this.e = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.f = -1;
        this.g = 1.0f;
        this.h = new int[]{ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
        this.f385i = 0.0f;
        this.j = false;
        this.l = false;
        this.m = 0;
        this.n = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.o = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.p = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.q = new ArrayList<>();
        this.r = new boolean[2];
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors = new DimensionBehaviour[]{dimensionBehaviour, dimensionBehaviour};
        this.mParent = null;
        this.s = 0;
        this.t = 0;
        this.mDimensionRatio = 0.0f;
        this.u = -1;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        float f = DEFAULT_BIAS;
        this.E = f;
        this.F = f;
        this.H = 0;
        this.I = 0;
        this.J = null;
        this.K = null;
        this.V = 0;
        this.W = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.Z = new ConstraintWidget[]{null, null};
        this.a0 = new ConstraintWidget[]{null, null};
        this.b0 = null;
        this.c0 = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        this.v = i2;
        this.w = i3;
        this.s = i4;
        this.t = i5;
        a();
    }

    public ConstraintWidget(String str) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.a = true;
        this.b = false;
        this.f383c = true;
        this.f384d = false;
        this.e = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.f = -1;
        this.g = 1.0f;
        this.h = new int[]{ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
        this.f385i = 0.0f;
        this.j = false;
        this.l = false;
        this.m = 0;
        this.n = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.o = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.p = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.q = new ArrayList<>();
        this.r = new boolean[2];
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors = new DimensionBehaviour[]{dimensionBehaviour, dimensionBehaviour};
        this.mParent = null;
        this.s = 0;
        this.t = 0;
        this.mDimensionRatio = 0.0f;
        this.u = -1;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        float f = DEFAULT_BIAS;
        this.E = f;
        this.F = f;
        this.H = 0;
        this.I = 0;
        this.J = null;
        this.K = null;
        this.V = 0;
        this.W = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.Z = new ConstraintWidget[]{null, null};
        this.a0 = new ConstraintWidget[]{null, null};
        this.b0 = null;
        this.c0 = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        a();
        setDebugName(str);
    }

    public ConstraintWidget(String str, int i2, int i3) {
        this(i2, i3);
        setDebugName(str);
    }

    public ConstraintWidget(String str, int i2, int i3, int i4, int i5) {
        this(i2, i3, i4, i5);
        setDebugName(str);
    }

    private void a() {
        this.q.add(this.mLeft);
        this.q.add(this.mTop);
        this.q.add(this.mRight);
        this.q.add(this.mBottom);
        this.q.add(this.o);
        this.q.add(this.p);
        this.q.add(this.mCenter);
        this.q.add(this.mBaseline);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0406  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x04c2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0489 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void c(androidx.constraintlayout.solver.LinearSystem r32, boolean r33, boolean r34, boolean r35, boolean r36, androidx.constraintlayout.solver.SolverVariable r37, androidx.constraintlayout.solver.SolverVariable r38, androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour r39, boolean r40, androidx.constraintlayout.solver.widgets.ConstraintAnchor r41, androidx.constraintlayout.solver.widgets.ConstraintAnchor r42, int r43, int r44, int r45, int r46, float r47, boolean r48, boolean r49, boolean r50, boolean r51, boolean r52, int r53, int r54, int r55, int r56, float r57, boolean r58) {
        /*
            Method dump skipped, instructions count: 1282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.c(androidx.constraintlayout.solver.LinearSystem, boolean, boolean, boolean, boolean, androidx.constraintlayout.solver.SolverVariable, androidx.constraintlayout.solver.SolverVariable, androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour, boolean, androidx.constraintlayout.solver.widgets.ConstraintAnchor, androidx.constraintlayout.solver.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, boolean, boolean, boolean, int, int, int, int, float, boolean):void");
    }

    private boolean f(int i2) {
        int i3 = i2 * 2;
        ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
        if (constraintAnchorArr[i3].mTarget != null && constraintAnchorArr[i3].mTarget.mTarget != constraintAnchorArr[i3]) {
            int i4 = i3 + 1;
            if (constraintAnchorArr[i4].mTarget != null && constraintAnchorArr[i4].mTarget.mTarget == constraintAnchorArr[i4]) {
                return true;
            }
        }
        return false;
    }

    public void addChildrenToSolverByDependency(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, HashSet<ConstraintWidget> hashSet, int i2, boolean z) {
        if (z) {
            if (!hashSet.contains(this)) {
                return;
            }
            Optimizer.a(constraintWidgetContainer, linearSystem, this);
            hashSet.remove(this);
            addToSolver(linearSystem, constraintWidgetContainer.optimizeFor(64));
        }
        if (i2 == 0) {
            HashSet<ConstraintAnchor> dependents = this.mLeft.getDependents();
            if (dependents != null) {
                Iterator<ConstraintAnchor> it = dependents.iterator();
                while (it.hasNext()) {
                    it.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
                }
            }
            HashSet<ConstraintAnchor> dependents2 = this.mRight.getDependents();
            if (dependents2 != null) {
                Iterator<ConstraintAnchor> it2 = dependents2.iterator();
                while (it2.hasNext()) {
                    it2.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
                }
                return;
            }
            return;
        }
        HashSet<ConstraintAnchor> dependents3 = this.mTop.getDependents();
        if (dependents3 != null) {
            Iterator<ConstraintAnchor> it3 = dependents3.iterator();
            while (it3.hasNext()) {
                it3.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
            }
        }
        HashSet<ConstraintAnchor> dependents4 = this.mBottom.getDependents();
        if (dependents4 != null) {
            Iterator<ConstraintAnchor> it4 = dependents4.iterator();
            while (it4.hasNext()) {
                it4.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
            }
        }
        HashSet<ConstraintAnchor> dependents5 = this.mBaseline.getDependents();
        if (dependents5 != null) {
            Iterator<ConstraintAnchor> it5 = dependents5.iterator();
            while (it5.hasNext()) {
                it5.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i2, true);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:128:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x04c7  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x04cc  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x055d  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x05a6  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x05d6  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x05cc  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0560  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x04c9  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x04b3  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x02fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addToSolver(androidx.constraintlayout.solver.LinearSystem r53, boolean r54) {
        /*
            Method dump skipped, instructions count: 1532
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.addToSolver(androidx.constraintlayout.solver.LinearSystem, boolean):void");
    }

    public boolean allowedInBarrier() {
        return this.I != 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b() {
        return (this instanceof VirtualLayout) || (this instanceof Guideline);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2) {
        connect(type, constraintWidget, type2, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x01b7, code lost:
    
        if (r11.isConnected() != false) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x018e, code lost:
    
        if (r11.isConnected() != false) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01b9, code lost:
    
        r9.reset();
        r11.reset();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void connect(androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type r9, androidx.constraintlayout.solver.widgets.ConstraintWidget r10, androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type r11, int r12) {
        /*
            Method dump skipped, instructions count: 455
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.solver.widgets.ConstraintWidget.connect(androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type, androidx.constraintlayout.solver.widgets.ConstraintWidget, androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type, int):void");
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i2) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i2);
        }
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i2) {
        ConstraintAnchor.Type type = ConstraintAnchor.Type.CENTER;
        immediateConnect(type, constraintWidget, type, i2, 0);
        this.f385i = f;
    }

    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        this.mHorizontalResolution = constraintWidget.mHorizontalResolution;
        this.mVerticalResolution = constraintWidget.mVerticalResolution;
        this.mMatchConstraintDefaultWidth = constraintWidget.mMatchConstraintDefaultWidth;
        this.mMatchConstraintDefaultHeight = constraintWidget.mMatchConstraintDefaultHeight;
        int[] iArr = this.mResolvedMatchConstraintDefault;
        int[] iArr2 = constraintWidget.mResolvedMatchConstraintDefault;
        iArr[0] = iArr2[0];
        iArr[1] = iArr2[1];
        this.mMatchConstraintMinWidth = constraintWidget.mMatchConstraintMinWidth;
        this.mMatchConstraintMaxWidth = constraintWidget.mMatchConstraintMaxWidth;
        this.mMatchConstraintMinHeight = constraintWidget.mMatchConstraintMinHeight;
        this.mMatchConstraintMaxHeight = constraintWidget.mMatchConstraintMaxHeight;
        this.mMatchConstraintPercentHeight = constraintWidget.mMatchConstraintPercentHeight;
        this.mIsWidthWrapContent = constraintWidget.mIsWidthWrapContent;
        this.mIsHeightWrapContent = constraintWidget.mIsHeightWrapContent;
        this.f = constraintWidget.f;
        this.g = constraintWidget.g;
        int[] iArr3 = constraintWidget.h;
        this.h = Arrays.copyOf(iArr3, iArr3.length);
        this.f385i = constraintWidget.f385i;
        this.j = constraintWidget.j;
        this.k = constraintWidget.k;
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.o.reset();
        this.p.reset();
        this.mCenter.reset();
        this.mListDimensionBehaviors = (DimensionBehaviour[]) Arrays.copyOf(this.mListDimensionBehaviors, 2);
        this.mParent = this.mParent == null ? null : hashMap.get(constraintWidget.mParent);
        this.s = constraintWidget.s;
        this.t = constraintWidget.t;
        this.mDimensionRatio = constraintWidget.mDimensionRatio;
        this.u = constraintWidget.u;
        this.v = constraintWidget.v;
        this.w = constraintWidget.w;
        this.x = constraintWidget.x;
        this.y = constraintWidget.y;
        this.z = constraintWidget.z;
        this.A = constraintWidget.A;
        this.B = constraintWidget.B;
        this.C = constraintWidget.C;
        this.D = constraintWidget.D;
        this.E = constraintWidget.E;
        this.F = constraintWidget.F;
        this.G = constraintWidget.G;
        this.H = constraintWidget.H;
        this.I = constraintWidget.I;
        this.J = constraintWidget.J;
        this.K = constraintWidget.K;
        this.L = constraintWidget.L;
        this.M = constraintWidget.M;
        this.N = constraintWidget.N;
        this.O = constraintWidget.O;
        this.P = constraintWidget.P;
        this.Q = constraintWidget.Q;
        this.R = constraintWidget.R;
        this.S = constraintWidget.S;
        this.T = constraintWidget.T;
        this.U = constraintWidget.U;
        this.V = constraintWidget.V;
        this.W = constraintWidget.W;
        this.X = constraintWidget.X;
        this.Y = constraintWidget.Y;
        float[] fArr = this.mWeight;
        float[] fArr2 = constraintWidget.mWeight;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        ConstraintWidget[] constraintWidgetArr = this.Z;
        ConstraintWidget[] constraintWidgetArr2 = constraintWidget.Z;
        constraintWidgetArr[0] = constraintWidgetArr2[0];
        constraintWidgetArr[1] = constraintWidgetArr2[1];
        ConstraintWidget[] constraintWidgetArr3 = this.a0;
        ConstraintWidget[] constraintWidgetArr4 = constraintWidget.a0;
        constraintWidgetArr3[0] = constraintWidgetArr4[0];
        constraintWidgetArr3[1] = constraintWidgetArr4[1];
        ConstraintWidget constraintWidget2 = constraintWidget.b0;
        this.b0 = constraintWidget2 == null ? null : hashMap.get(constraintWidget2);
        ConstraintWidget constraintWidget3 = constraintWidget.c0;
        this.c0 = constraintWidget3 != null ? hashMap.get(constraintWidget3) : null;
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.B > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int d() {
        return this.v + this.z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int e() {
        return this.w + this.A;
    }

    public void ensureMeasureRequested() {
        this.a = true;
    }

    public void ensureWidgetRuns() {
        if (this.horizontalRun == null) {
            this.horizontalRun = new HorizontalWidgetRun(this);
        }
        if (this.verticalRun == null) {
            this.verticalRun = new VerticalWidgetRun(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g(int i2, boolean z) {
        this.r[i2] = z;
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (a.a[type.ordinal()]) {
            case 1:
                return this.mLeft;
            case 2:
                return this.mTop;
            case 3:
                return this.mRight;
            case 4:
                return this.mBottom;
            case 5:
                return this.mBaseline;
            case 6:
                return this.mCenter;
            case 7:
                return this.o;
            case 8:
                return this.p;
            case 9:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.q;
    }

    public int getBaselineDistance() {
        return this.B;
    }

    public float getBiasPercent(int i2) {
        if (i2 == 0) {
            return this.E;
        }
        if (i2 == 1) {
            return this.F;
        }
        return -1.0f;
    }

    public int getBottom() {
        return getY() + this.t;
    }

    public Object getCompanionWidget() {
        return this.G;
    }

    public int getContainerItemSkip() {
        return this.H;
    }

    public String getDebugName() {
        return this.J;
    }

    public DimensionBehaviour getDimensionBehaviour(int i2) {
        if (i2 == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (i2 == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.u;
    }

    public boolean getHasBaseline() {
        return this.j;
    }

    public int getHeight() {
        if (this.I == 8) {
            return 0;
        }
        return this.t;
    }

    public float getHorizontalBiasPercent() {
        return this.E;
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor target = anchor == null ? null : anchor.getTarget();
            ConstraintWidget owner = target == null ? null : target.getOwner();
            if (owner == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor target2 = owner == null ? null : owner.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
            if (target2 == null || target2.getOwner() == constraintWidget) {
                constraintWidget = owner;
            } else {
                constraintWidget2 = constraintWidget;
            }
        }
        return constraintWidget2;
    }

    public int getHorizontalChainStyle() {
        return this.V;
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public int getHorizontalMargin() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        int i2 = constraintAnchor != null ? 0 + constraintAnchor.mMargin : 0;
        ConstraintAnchor constraintAnchor2 = this.mRight;
        return constraintAnchor2 != null ? i2 + constraintAnchor2.mMargin : i2;
    }

    public int getLastHorizontalMeasureSpec() {
        return this.m;
    }

    public int getLastVerticalMeasureSpec() {
        return this.n;
    }

    public int getLeft() {
        return getX();
    }

    public int getLength(int i2) {
        if (i2 == 0) {
            return getWidth();
        }
        if (i2 == 1) {
            return getHeight();
        }
        return 0;
    }

    public int getMaxHeight() {
        return this.h[1];
    }

    public int getMaxWidth() {
        return this.h[0];
    }

    public int getMinHeight() {
        return this.D;
    }

    public int getMinWidth() {
        return this.C;
    }

    public ConstraintWidget getNextChainMember(int i2) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i2 != 0) {
            if (i2 == 1 && (constraintAnchor2 = (constraintAnchor = this.mBottom).mTarget) != null && constraintAnchor2.mTarget == constraintAnchor) {
                return constraintAnchor2.mOwner;
            }
            return null;
        }
        ConstraintAnchor constraintAnchor3 = this.mRight;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
        if (constraintAnchor4 == null || constraintAnchor4.mTarget != constraintAnchor3) {
            return null;
        }
        return constraintAnchor4.mOwner;
    }

    public int getOptimizerWrapHeight() {
        int i2;
        int i3 = this.t;
        if (this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i3;
        }
        if (this.mMatchConstraintDefaultHeight == 1) {
            i2 = Math.max(this.mMatchConstraintMinHeight, i3);
        } else {
            i2 = this.mMatchConstraintMinHeight;
            if (i2 > 0) {
                this.t = i2;
            } else {
                i2 = 0;
            }
        }
        int i4 = this.mMatchConstraintMaxHeight;
        return (i4 <= 0 || i4 >= i2) ? i2 : i4;
    }

    public int getOptimizerWrapWidth() {
        int i2;
        int i3 = this.s;
        if (this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i3;
        }
        if (this.mMatchConstraintDefaultWidth == 1) {
            i2 = Math.max(this.mMatchConstraintMinWidth, i3);
        } else {
            i2 = this.mMatchConstraintMinWidth;
            if (i2 > 0) {
                this.s = i2;
            } else {
                i2 = 0;
            }
        }
        int i4 = this.mMatchConstraintMaxWidth;
        return (i4 <= 0 || i4 >= i2) ? i2 : i4;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public ConstraintWidget getPreviousChainMember(int i2) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i2 != 0) {
            if (i2 == 1 && (constraintAnchor2 = (constraintAnchor = this.mTop).mTarget) != null && constraintAnchor2.mTarget == constraintAnchor) {
                return constraintAnchor2.mOwner;
            }
            return null;
        }
        ConstraintAnchor constraintAnchor3 = this.mLeft;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
        if (constraintAnchor4 == null || constraintAnchor4.mTarget != constraintAnchor3) {
            return null;
        }
        return constraintAnchor4.mOwner;
    }

    public int getRight() {
        return getX() + this.s;
    }

    public WidgetRun getRun(int i2) {
        if (i2 == 0) {
            return this.horizontalRun;
        }
        if (i2 == 1) {
            return this.verticalRun;
        }
        return null;
    }

    public int getTop() {
        return getY();
    }

    public String getType() {
        return this.K;
    }

    public float getVerticalBiasPercent() {
        return this.F;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget = this;
        ConstraintWidget constraintWidget2 = null;
        while (constraintWidget2 == null && constraintWidget != null) {
            ConstraintAnchor anchor = constraintWidget.getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor target = anchor == null ? null : anchor.getTarget();
            ConstraintWidget owner = target == null ? null : target.getOwner();
            if (owner == getParent()) {
                return constraintWidget;
            }
            ConstraintAnchor target2 = owner == null ? null : owner.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
            if (target2 == null || target2.getOwner() == constraintWidget) {
                constraintWidget = owner;
            } else {
                constraintWidget2 = constraintWidget;
            }
        }
        return constraintWidget2;
    }

    public int getVerticalChainStyle() {
        return this.W;
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public int getVerticalMargin() {
        int i2 = this.mLeft != null ? 0 + this.mTop.mMargin : 0;
        return this.mRight != null ? i2 + this.mBottom.mMargin : i2;
    }

    public int getVisibility() {
        return this.I;
    }

    public int getWidth() {
        if (this.I == 8) {
            return 0;
        }
        return this.s;
    }

    public int getX() {
        ConstraintWidget constraintWidget = this.mParent;
        return (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) ? this.v : ((ConstraintWidgetContainer) constraintWidget).h0 + this.v;
    }

    public int getY() {
        ConstraintWidget constraintWidget = this.mParent;
        return (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) ? this.w : ((ConstraintWidgetContainer) constraintWidget).i0 + this.w;
    }

    public boolean hasBaseline() {
        return this.j;
    }

    public boolean hasDanglingDimension(int i2) {
        if (i2 == 0) {
            return (this.mLeft.mTarget != null ? 1 : 0) + (this.mRight.mTarget != null ? 1 : 0) < 2;
        }
        return ((this.mTop.mTarget != null ? 1 : 0) + (this.mBottom.mTarget != null ? 1 : 0)) + (this.mBaseline.mTarget != null ? 1 : 0) < 2;
    }

    public boolean hasDependencies() {
        int size = this.q.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (this.q.get(i2).hasDependents()) {
                return true;
            }
        }
        return false;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i2, int i3) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i2, i3, true);
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public boolean isInHorizontalChain() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 != null && constraintAnchor2.mTarget == constraintAnchor) {
            return true;
        }
        ConstraintAnchor constraintAnchor3 = this.mRight;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
        return constraintAnchor4 != null && constraintAnchor4.mTarget == constraintAnchor3;
    }

    public boolean isInPlaceholder() {
        return this.k;
    }

    public boolean isInVerticalChain() {
        ConstraintAnchor constraintAnchor = this.mTop;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 != null && constraintAnchor2.mTarget == constraintAnchor) {
            return true;
        }
        ConstraintAnchor constraintAnchor3 = this.mBottom;
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
        return constraintAnchor4 != null && constraintAnchor4.mTarget == constraintAnchor3;
    }

    public boolean isInVirtualLayout() {
        return this.l;
    }

    public boolean isMeasureRequested() {
        return this.a && this.I != 8;
    }

    public boolean isResolvedHorizontally() {
        return this.f384d || (this.mLeft.hasFinalValue() && this.mRight.hasFinalValue());
    }

    public boolean isResolvedVertically() {
        return this.e || (this.mTop.hasFinalValue() && this.mBottom.hasFinalValue());
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public boolean oppositeDimensionDependsOn(int i2) {
        char c2 = i2 == 0 ? (char) 1 : (char) 0;
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[i2];
        DimensionBehaviour dimensionBehaviour2 = dimensionBehaviourArr[c2];
        DimensionBehaviour dimensionBehaviour3 = DimensionBehaviour.MATCH_CONSTRAINT;
        return dimensionBehaviour == dimensionBehaviour3 && dimensionBehaviour2 == dimensionBehaviour3;
    }

    public boolean oppositeDimensionsTied() {
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.MATCH_CONSTRAINT;
        return dimensionBehaviour == dimensionBehaviour2 && dimensionBehaviourArr[1] == dimensionBehaviour2;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.o.reset();
        this.p.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.f385i = 0.0f;
        this.s = 0;
        this.t = 0;
        this.mDimensionRatio = 0.0f;
        this.u = -1;
        this.v = 0;
        this.w = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        float f = DEFAULT_BIAS;
        this.E = f;
        this.F = f;
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        dimensionBehaviourArr[0] = dimensionBehaviour;
        dimensionBehaviourArr[1] = dimensionBehaviour;
        this.G = null;
        this.H = 0;
        this.I = 0;
        this.K = null;
        this.T = false;
        this.U = false;
        this.V = 0;
        this.W = 0;
        this.X = false;
        this.Y = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.h;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.mMatchConstraintMaxHeight = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.f = -1;
        this.g = 1.0f;
        boolean[] zArr = this.isTerminalWidget;
        zArr[0] = true;
        zArr[1] = true;
        this.l = false;
        boolean[] zArr2 = this.r;
        zArr2[0] = false;
        zArr2[1] = false;
        this.a = true;
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() != null && (getParent() instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            return;
        }
        ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
        ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
        ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
        ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
        ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.CENTER);
        ConstraintAnchor anchor6 = getAnchor(ConstraintAnchor.Type.CENTER_X);
        ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
        if (constraintAnchor != anchor5) {
            if (constraintAnchor == anchor6) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                    anchor.reset();
                    anchor2.reset();
                }
                this.E = 0.5f;
            } else if (constraintAnchor == anchor7) {
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                    anchor3.reset();
                    anchor4.reset();
                }
            } else if (constraintAnchor == anchor || constraintAnchor == anchor2 ? !(!anchor.isConnected() || anchor.getTarget() != anchor2.getTarget()) : !((constraintAnchor != anchor3 && constraintAnchor != anchor4) || !anchor3.isConnected() || anchor3.getTarget() != anchor4.getTarget())) {
                anchor5.reset();
            }
            constraintAnchor.reset();
        }
        if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
            anchor.reset();
            anchor2.reset();
        }
        if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
            anchor3.reset();
            anchor4.reset();
        }
        this.E = 0.5f;
        this.F = 0.5f;
        constraintAnchor.reset();
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent != null && (parent instanceof ConstraintWidgetContainer) && ((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            return;
        }
        int size = this.q.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.q.get(i2).reset();
        }
    }

    public void resetFinalResolution() {
        this.f384d = false;
        this.e = false;
        int size = this.q.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.q.get(i2).resetFinalResolution();
        }
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.o.resetSolverVariable(cache);
        this.p.resetSolverVariable(cache);
    }

    public void setBaselineDistance(int i2) {
        this.B = i2;
        this.j = i2 > 0;
    }

    public void setCompanionWidget(Object obj) {
        this.G = obj;
    }

    public void setContainerItemSkip(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.H = i2;
    }

    public void setDebugName(String str) {
        this.J = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.J = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        createObjectVariable.setName(str + ".left");
        createObjectVariable2.setName(str + ".top");
        createObjectVariable3.setName(str + ".right");
        createObjectVariable4.setName(str + ".bottom");
        linearSystem.createObjectVariable(this.mBaseline).setName(str + ".baseline");
    }

    public void setDimension(int i2, int i3) {
        this.s = i2;
        int i4 = this.C;
        if (i2 < i4) {
            this.s = i4;
        }
        this.t = i3;
        int i5 = this.D;
        if (i3 < i5) {
            this.t = i5;
        }
    }

    public void setDimensionRatio(float f, int i2) {
        this.mDimensionRatio = f;
        this.u = i2;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x0084 -> B:31:0x0085). Please report as a decompilation issue!!! */
    public void setDimensionRatio(String str) {
        float f;
        int i2 = 0;
        if (str == null || str.length() == 0) {
            this.mDimensionRatio = 0.0f;
            return;
        }
        int i3 = -1;
        int length = str.length();
        int indexOf = str.indexOf(44);
        int i4 = 0;
        if (indexOf > 0 && indexOf < length - 1) {
            String substring = str.substring(0, indexOf);
            if (substring.equalsIgnoreCase("W")) {
                i3 = 0;
            } else if (substring.equalsIgnoreCase("H")) {
                i3 = 1;
            }
            i4 = indexOf + 1;
        }
        int indexOf2 = str.indexOf(58);
        if (indexOf2 < 0 || indexOf2 >= length - 1) {
            String substring2 = str.substring(i4);
            if (substring2.length() > 0) {
                f = Float.parseFloat(substring2);
            }
            f = 0.0f;
        } else {
            String substring3 = str.substring(i4, indexOf2);
            String substring4 = str.substring(indexOf2 + 1);
            if (substring3.length() > 0 && substring4.length() > 0) {
                float parseFloat = Float.parseFloat(substring3);
                float parseFloat2 = Float.parseFloat(substring4);
                if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                    f = i3 == 1 ? Math.abs(parseFloat2 / parseFloat) : Math.abs(parseFloat / parseFloat2);
                }
            }
            f = 0.0f;
        }
        i2 = (f > i2 ? 1 : (f == i2 ? 0 : -1));
        if (i2 > 0) {
            this.mDimensionRatio = f;
            this.u = i3;
        }
    }

    public void setFinalBaseline(int i2) {
        if (this.j) {
            int i3 = i2 - this.B;
            int i4 = this.t + i3;
            this.w = i3;
            this.mTop.setFinalValue(i3);
            this.mBottom.setFinalValue(i4);
            this.mBaseline.setFinalValue(i2);
            this.e = true;
        }
    }

    public void setFinalFrame(int i2, int i3, int i4, int i5, int i6, int i7) {
        setFrame(i2, i3, i4, i5);
        setBaselineDistance(i6);
        if (i7 != 0) {
            if (i7 == 1) {
                this.f384d = false;
            } else if (i7 == 2) {
                this.f384d = true;
            } else {
                this.f384d = false;
            }
            this.e = true;
            return;
        }
        this.f384d = true;
        this.e = false;
    }

    public void setFinalHorizontal(int i2, int i3) {
        this.mLeft.setFinalValue(i2);
        this.mRight.setFinalValue(i3);
        this.v = i2;
        this.s = i3 - i2;
        this.f384d = true;
    }

    public void setFinalLeft(int i2) {
        this.mLeft.setFinalValue(i2);
        this.v = i2;
    }

    public void setFinalTop(int i2) {
        this.mTop.setFinalValue(i2);
        this.w = i2;
    }

    public void setFinalVertical(int i2, int i3) {
        this.mTop.setFinalValue(i2);
        this.mBottom.setFinalValue(i3);
        this.w = i2;
        this.t = i3 - i2;
        if (this.j) {
            this.mBaseline.setFinalValue(i2 + this.B);
        }
        this.e = true;
    }

    public void setFrame(int i2, int i3, int i4) {
        if (i4 == 0) {
            setHorizontalDimension(i2, i3);
        } else if (i4 == 1) {
            setVerticalDimension(i2, i3);
        }
    }

    public void setFrame(int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8 = i4 - i2;
        int i9 = i5 - i3;
        this.v = i2;
        this.w = i3;
        if (this.I == 8) {
            this.s = 0;
            this.t = 0;
            return;
        }
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        DimensionBehaviour dimensionBehaviour = dimensionBehaviourArr[0];
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.FIXED;
        if (dimensionBehaviour == dimensionBehaviour2 && i8 < (i7 = this.s)) {
            i8 = i7;
        }
        if (dimensionBehaviourArr[1] == dimensionBehaviour2 && i9 < (i6 = this.t)) {
            i9 = i6;
        }
        this.s = i8;
        this.t = i9;
        int i10 = this.D;
        if (i9 < i10) {
            this.t = i10;
        }
        int i11 = this.C;
        if (i8 < i11) {
            this.s = i11;
        }
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int i2) {
        ConstraintAnchor constraintAnchor;
        int i3 = a.a[type.ordinal()];
        if (i3 == 1) {
            constraintAnchor = this.mLeft;
        } else if (i3 == 2) {
            constraintAnchor = this.mTop;
        } else if (i3 == 3) {
            constraintAnchor = this.mRight;
        } else if (i3 != 4) {
            return;
        } else {
            constraintAnchor = this.mBottom;
        }
        constraintAnchor.f382d = i2;
    }

    public void setHasBaseline(boolean z) {
        this.j = z;
    }

    public void setHeight(int i2) {
        this.t = i2;
        int i3 = this.D;
        if (i2 < i3) {
            this.t = i3;
        }
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public void setHorizontalBiasPercent(float f) {
        this.E = f;
    }

    public void setHorizontalChainStyle(int i2) {
        this.V = i2;
    }

    public void setHorizontalDimension(int i2, int i3) {
        this.v = i2;
        int i4 = i3 - i2;
        this.s = i4;
        int i5 = this.C;
        if (i4 < i5) {
            this.s = i5;
        }
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
    }

    public void setHorizontalMatchStyle(int i2, int i3, int i4, float f) {
        this.mMatchConstraintDefaultWidth = i2;
        this.mMatchConstraintMinWidth = i3;
        if (i4 == Integer.MAX_VALUE) {
            i4 = 0;
        }
        this.mMatchConstraintMaxWidth = i4;
        this.mMatchConstraintPercentWidth = f;
        if (f <= 0.0f || f >= 1.0f || i2 != 0) {
            return;
        }
        this.mMatchConstraintDefaultWidth = 2;
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setInPlaceholder(boolean z) {
        this.k = z;
    }

    public void setInVirtualLayout(boolean z) {
        this.l = z;
    }

    public void setLastMeasureSpec(int i2, int i3) {
        this.m = i2;
        this.n = i3;
        setMeasureRequested(false);
    }

    public void setLength(int i2, int i3) {
        if (i3 == 0) {
            setWidth(i2);
        } else if (i3 == 1) {
            setHeight(i2);
        }
    }

    public void setMaxHeight(int i2) {
        this.h[1] = i2;
    }

    public void setMaxWidth(int i2) {
        this.h[0] = i2;
    }

    public void setMeasureRequested(boolean z) {
        this.a = z;
    }

    public void setMinHeight(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.D = i2;
    }

    public void setMinWidth(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.C = i2;
    }

    public void setOffset(int i2, int i3) {
        this.z = i2;
        this.A = i3;
    }

    public void setOrigin(int i2, int i3) {
        this.v = i2;
        this.w = i3;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setType(String str) {
        this.K = str;
    }

    public void setVerticalBiasPercent(float f) {
        this.F = f;
    }

    public void setVerticalChainStyle(int i2) {
        this.W = i2;
    }

    public void setVerticalDimension(int i2, int i3) {
        this.w = i2;
        int i4 = i3 - i2;
        this.t = i4;
        int i5 = this.D;
        if (i4 < i5) {
            this.t = i5;
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
    }

    public void setVerticalMatchStyle(int i2, int i3, int i4, float f) {
        this.mMatchConstraintDefaultHeight = i2;
        this.mMatchConstraintMinHeight = i3;
        if (i4 == Integer.MAX_VALUE) {
            i4 = 0;
        }
        this.mMatchConstraintMaxHeight = i4;
        this.mMatchConstraintPercentHeight = f;
        if (f <= 0.0f || f >= 1.0f || i2 != 0) {
            return;
        }
        this.mMatchConstraintDefaultHeight = 2;
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setVisibility(int i2) {
        this.I = i2;
    }

    public void setWidth(int i2) {
        this.s = i2;
        int i3 = this.C;
        if (i2 < i3) {
            this.s = i3;
        }
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public void setX(int i2) {
        this.v = i2;
    }

    public void setY(int i2) {
        this.w = i2;
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.f == -1) {
            if (z3 && !z4) {
                this.f = 0;
            } else if (!z3 && z4) {
                this.f = 1;
                if (this.u == -1) {
                    this.g = 1.0f / this.g;
                }
            }
        }
        if (this.f == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.f = 1;
        } else if (this.f == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.f = 0;
        }
        if (this.f == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.f = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.g = 1.0f / this.g;
                this.f = 1;
            }
        }
        if (this.f == -1) {
            int i2 = this.mMatchConstraintMinWidth;
            if (i2 > 0 && this.mMatchConstraintMinHeight == 0) {
                this.f = 0;
            } else {
                if (i2 != 0 || this.mMatchConstraintMinHeight <= 0) {
                    return;
                }
                this.g = 1.0f / this.g;
                this.f = 1;
            }
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (this.K != null) {
            str = "type: " + this.K + " ";
        } else {
            str = "";
        }
        sb.append(str);
        if (this.J != null) {
            str2 = "id: " + this.J + " ";
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.v);
        sb.append(", ");
        sb.append(this.w);
        sb.append(") - (");
        sb.append(this.s);
        sb.append(" x ");
        sb.append(this.t);
        sb.append(")");
        return sb.toString();
    }

    public void updateFromRuns(boolean z, boolean z2) {
        int i2;
        int i3;
        boolean isResolved = z & this.horizontalRun.isResolved();
        boolean isResolved2 = z2 & this.verticalRun.isResolved();
        HorizontalWidgetRun horizontalWidgetRun = this.horizontalRun;
        int i4 = horizontalWidgetRun.start.value;
        VerticalWidgetRun verticalWidgetRun = this.verticalRun;
        int i5 = verticalWidgetRun.start.value;
        int i6 = horizontalWidgetRun.end.value;
        int i7 = verticalWidgetRun.end.value;
        int i8 = i7 - i5;
        if (i6 - i4 < 0 || i8 < 0 || i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE || i5 == Integer.MIN_VALUE || i5 == Integer.MAX_VALUE || i6 == Integer.MIN_VALUE || i6 == Integer.MAX_VALUE || i7 == Integer.MIN_VALUE || i7 == Integer.MAX_VALUE) {
            i6 = 0;
            i4 = 0;
            i7 = 0;
            i5 = 0;
        }
        int i9 = i6 - i4;
        int i10 = i7 - i5;
        if (isResolved) {
            this.v = i4;
        }
        if (isResolved2) {
            this.w = i5;
        }
        if (this.I == 8) {
            this.s = 0;
            this.t = 0;
            return;
        }
        if (isResolved) {
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i9 < (i3 = this.s)) {
                i9 = i3;
            }
            this.s = i9;
            int i11 = this.C;
            if (i9 < i11) {
                this.s = i11;
            }
        }
        if (isResolved2) {
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i10 < (i2 = this.t)) {
                i10 = i2;
            }
            this.t = i10;
            int i12 = this.D;
            if (i10 < i12) {
                this.t = i12;
            }
        }
    }

    public void updateFromSolver(LinearSystem linearSystem, boolean z) {
        VerticalWidgetRun verticalWidgetRun;
        HorizontalWidgetRun horizontalWidgetRun;
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.mBottom);
        if (z && (horizontalWidgetRun = this.horizontalRun) != null) {
            DependencyNode dependencyNode = horizontalWidgetRun.start;
            if (dependencyNode.resolved) {
                DependencyNode dependencyNode2 = horizontalWidgetRun.end;
                if (dependencyNode2.resolved) {
                    objectVariableValue = dependencyNode.value;
                    objectVariableValue3 = dependencyNode2.value;
                }
            }
        }
        if (z && (verticalWidgetRun = this.verticalRun) != null) {
            DependencyNode dependencyNode3 = verticalWidgetRun.start;
            if (dependencyNode3.resolved) {
                DependencyNode dependencyNode4 = verticalWidgetRun.end;
                if (dependencyNode4.resolved) {
                    objectVariableValue2 = dependencyNode3.value;
                    objectVariableValue4 = dependencyNode4.value;
                }
            }
        }
        int i2 = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i2 < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue4 = 0;
            objectVariableValue = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }
}
