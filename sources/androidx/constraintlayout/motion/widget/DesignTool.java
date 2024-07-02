package androidx.constraintlayout.motion.widget;

import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;

/* loaded from: classes.dex */
public class DesignTool {
    static final HashMap<Pair<Integer, Integer>, String> g;
    static final HashMap<String, String> h;
    private final MotionLayout a;
    private MotionScene b;

    /* renamed from: c, reason: collision with root package name */
    private String f297c = null;

    /* renamed from: d, reason: collision with root package name */
    private String f298d = null;
    private int e = -1;
    private int f = -1;

    static {
        HashMap<Pair<Integer, Integer>, String> hashMap = new HashMap<>();
        g = hashMap;
        HashMap<String, String> hashMap2 = new HashMap<>();
        h = hashMap2;
        hashMap.put(Pair.create(4, 4), "layout_constraintBottom_toBottomOf");
        hashMap.put(Pair.create(4, 3), "layout_constraintBottom_toTopOf");
        hashMap.put(Pair.create(3, 4), "layout_constraintTop_toBottomOf");
        hashMap.put(Pair.create(3, 3), "layout_constraintTop_toTopOf");
        hashMap.put(Pair.create(6, 6), "layout_constraintStart_toStartOf");
        hashMap.put(Pair.create(6, 7), "layout_constraintStart_toEndOf");
        hashMap.put(Pair.create(7, 6), "layout_constraintEnd_toStartOf");
        hashMap.put(Pair.create(7, 7), "layout_constraintEnd_toEndOf");
        hashMap.put(Pair.create(1, 1), "layout_constraintLeft_toLeftOf");
        hashMap.put(Pair.create(1, 2), "layout_constraintLeft_toRightOf");
        hashMap.put(Pair.create(2, 2), "layout_constraintRight_toRightOf");
        hashMap.put(Pair.create(2, 1), "layout_constraintRight_toLeftOf");
        hashMap.put(Pair.create(5, 5), "layout_constraintBaseline_toBaselineOf");
        hashMap2.put("layout_constraintBottom_toBottomOf", "layout_marginBottom");
        hashMap2.put("layout_constraintBottom_toTopOf", "layout_marginBottom");
        hashMap2.put("layout_constraintTop_toBottomOf", "layout_marginTop");
        hashMap2.put("layout_constraintTop_toTopOf", "layout_marginTop");
        hashMap2.put("layout_constraintStart_toStartOf", "layout_marginStart");
        hashMap2.put("layout_constraintStart_toEndOf", "layout_marginStart");
        hashMap2.put("layout_constraintEnd_toStartOf", "layout_marginEnd");
        hashMap2.put("layout_constraintEnd_toEndOf", "layout_marginEnd");
        hashMap2.put("layout_constraintLeft_toLeftOf", "layout_marginLeft");
        hashMap2.put("layout_constraintLeft_toRightOf", "layout_marginLeft");
        hashMap2.put("layout_constraintRight_toRightOf", "layout_marginRight");
        hashMap2.put("layout_constraintRight_toLeftOf", "layout_marginRight");
    }

    public DesignTool(MotionLayout motionLayout) {
        this.a = motionLayout;
    }

    private static void a(int i2, ConstraintSet constraintSet, View view, HashMap<String, String> hashMap, int i3, int i4) {
        String str = g.get(Pair.create(Integer.valueOf(i3), Integer.valueOf(i4)));
        String str2 = hashMap.get(str);
        if (str2 != null) {
            String str3 = h.get(str);
            constraintSet.connect(view.getId(), i3, Integer.parseInt(str2), i4, str3 != null ? b(i2, hashMap.get(str3)) : 0);
        }
    }

    private static int b(int i2, String str) {
        int indexOf;
        if (str == null || (indexOf = str.indexOf(100)) == -1) {
            return 0;
        }
        return (int) ((Integer.valueOf(str.substring(0, indexOf)).intValue() * i2) / 160.0f);
    }

    private static void c(int i2, ConstraintSet constraintSet, View view, HashMap<String, String> hashMap) {
        String str = hashMap.get("layout_editor_absoluteX");
        if (str != null) {
            constraintSet.setEditorAbsoluteX(view.getId(), b(i2, str));
        }
        String str2 = hashMap.get("layout_editor_absoluteY");
        if (str2 != null) {
            constraintSet.setEditorAbsoluteY(view.getId(), b(i2, str2));
        }
    }

    private static void d(ConstraintSet constraintSet, View view, HashMap<String, String> hashMap, int i2) {
        String str = hashMap.get(i2 == 1 ? "layout_constraintVertical_bias" : "layout_constraintHorizontal_bias");
        if (str != null) {
            if (i2 == 0) {
                constraintSet.setHorizontalBias(view.getId(), Float.parseFloat(str));
            } else if (i2 == 1) {
                constraintSet.setVerticalBias(view.getId(), Float.parseFloat(str));
            }
        }
    }

    private static void e(int i2, ConstraintSet constraintSet, View view, HashMap<String, String> hashMap, int i3) {
        String str = hashMap.get(i3 == 1 ? "layout_height" : "layout_width");
        if (str != null) {
            int b = str.equalsIgnoreCase("wrap_content") ? -2 : b(i2, str);
            int id = view.getId();
            if (i3 == 0) {
                constraintSet.constrainWidth(id, b);
            } else {
                constraintSet.constrainHeight(id, b);
            }
        }
    }

    public int designAccess(int i2, String str, Object obj, float[] fArr, int i3, float[] fArr2, int i4) {
        MotionController motionController;
        View view = (View) obj;
        if (i2 != 0) {
            MotionLayout motionLayout = this.a;
            if (motionLayout.t == null || view == null || (motionController = motionLayout.C.get(view)) == null) {
                return -1;
            }
        } else {
            motionController = null;
        }
        if (i2 == 0) {
            return 1;
        }
        if (i2 == 1) {
            int duration = this.a.t.getDuration() / 16;
            motionController.d(fArr2, duration);
            return duration;
        }
        if (i2 == 2) {
            int duration2 = this.a.t.getDuration() / 16;
            motionController.c(fArr2, null);
            return duration2;
        }
        if (i2 != 3) {
            return -1;
        }
        int duration3 = this.a.t.getDuration() / 16;
        return motionController.h(str, fArr2, i4);
    }

    public void disableAutoTransition(boolean z) {
        this.a.O(z);
    }

    public void dumpConstraintSet(String str) {
        MotionLayout motionLayout = this.a;
        if (motionLayout.t == null) {
            motionLayout.t = this.b;
        }
        int X = motionLayout.X(str);
        System.out.println(" dumping  " + str + " (" + X + ")");
        try {
            this.a.t.f(X).dump(this.a.t, new int[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getAnimationKeyFrames(Object obj, float[] fArr) {
        MotionScene motionScene = this.a.t;
        if (motionScene == null) {
            return -1;
        }
        int duration = motionScene.getDuration() / 16;
        MotionController motionController = this.a.C.get(obj);
        if (motionController == null) {
            return 0;
        }
        motionController.c(fArr, null);
        return duration;
    }

    public int getAnimationPath(Object obj, float[] fArr, int i2) {
        MotionLayout motionLayout = this.a;
        if (motionLayout.t == null) {
            return -1;
        }
        MotionController motionController = motionLayout.C.get(obj);
        if (motionController == null) {
            return 0;
        }
        motionController.d(fArr, i2);
        return i2;
    }

    public void getAnimationRectangles(Object obj, float[] fArr) {
        MotionScene motionScene = this.a.t;
        if (motionScene == null) {
            return;
        }
        int duration = motionScene.getDuration() / 16;
        MotionController motionController = this.a.C.get(obj);
        if (motionController == null) {
            return;
        }
        motionController.f(fArr, duration);
    }

    public String getEndState() {
        int endState = this.a.getEndState();
        if (this.f == endState) {
            return this.f298d;
        }
        String U = this.a.U(endState);
        if (U != null) {
            this.f298d = U;
            this.f = endState;
        }
        return U;
    }

    public int getKeyFrameInfo(Object obj, int i2, int[] iArr) {
        MotionController motionController = this.a.C.get((View) obj);
        if (motionController == null) {
            return 0;
        }
        return motionController.getKeyFrameInfo(i2, iArr);
    }

    public float getKeyFramePosition(Object obj, int i2, float f, float f2) {
        return this.a.C.get((View) obj).m(i2, f, f2);
    }

    public int getKeyFramePositions(Object obj, int[] iArr, float[] fArr) {
        MotionController motionController = this.a.C.get((View) obj);
        if (motionController == null) {
            return 0;
        }
        return motionController.getkeyFramePositions(iArr, fArr);
    }

    public Object getKeyframe(int i2, int i3, int i4) {
        MotionLayout motionLayout = this.a;
        MotionScene motionScene = motionLayout.t;
        if (motionScene == null) {
            return null;
        }
        return motionScene.k(motionLayout.getContext(), i2, i3, i4);
    }

    public Object getKeyframe(Object obj, int i2, int i3) {
        if (this.a.t == null) {
            return null;
        }
        int id = ((View) obj).getId();
        MotionLayout motionLayout = this.a;
        return motionLayout.t.k(motionLayout.getContext(), i2, id, i3);
    }

    public Object getKeyframeAtLocation(Object obj, float f, float f2) {
        MotionController motionController;
        View view = (View) obj;
        MotionLayout motionLayout = this.a;
        if (motionLayout.t == null) {
            return -1;
        }
        if (view == null || (motionController = motionLayout.C.get(view)) == null) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        return motionController.n(viewGroup.getWidth(), viewGroup.getHeight(), f, f2);
    }

    public Boolean getPositionKeyframe(Object obj, Object obj2, float f, float f2, String[] strArr, float[] fArr) {
        if (!(obj instanceof a)) {
            return Boolean.FALSE;
        }
        View view = (View) obj2;
        this.a.C.get(view).s(view, (a) obj, f, f2, strArr, fArr);
        this.a.rebuildScene();
        this.a.K = true;
        return Boolean.TRUE;
    }

    public float getProgress() {
        return this.a.getProgress();
    }

    public String getStartState() {
        int startState = this.a.getStartState();
        if (this.e == startState) {
            return this.f297c;
        }
        String U = this.a.U(startState);
        if (U != null) {
            this.f297c = U;
            this.e = startState;
        }
        return this.a.U(startState);
    }

    public String getState() {
        if (this.f297c != null && this.f298d != null) {
            float progress = getProgress();
            if (progress <= 0.01f) {
                return this.f297c;
            }
            if (progress >= 0.99f) {
                return this.f298d;
            }
        }
        return this.f297c;
    }

    public long getTransitionTimeMs() {
        return this.a.getTransitionTimeMs();
    }

    public boolean isInTransition() {
        return (this.f297c == null || this.f298d == null) ? false : true;
    }

    public void setAttributes(int i2, String str, Object obj, Object obj2) {
        View view = (View) obj;
        HashMap hashMap = (HashMap) obj2;
        int X = this.a.X(str);
        ConstraintSet f = this.a.t.f(X);
        if (f == null) {
            return;
        }
        f.clear(view.getId());
        e(i2, f, view, hashMap, 0);
        e(i2, f, view, hashMap, 1);
        a(i2, f, view, hashMap, 6, 6);
        a(i2, f, view, hashMap, 6, 7);
        a(i2, f, view, hashMap, 7, 7);
        a(i2, f, view, hashMap, 7, 6);
        a(i2, f, view, hashMap, 1, 1);
        a(i2, f, view, hashMap, 1, 2);
        a(i2, f, view, hashMap, 2, 2);
        a(i2, f, view, hashMap, 2, 1);
        a(i2, f, view, hashMap, 3, 3);
        a(i2, f, view, hashMap, 3, 4);
        a(i2, f, view, hashMap, 4, 3);
        a(i2, f, view, hashMap, 4, 4);
        a(i2, f, view, hashMap, 5, 5);
        d(f, view, hashMap, 0);
        d(f, view, hashMap, 1);
        c(i2, f, view, hashMap);
        this.a.updateState(X, f);
        this.a.requestLayout();
    }

    public void setKeyFrame(Object obj, int i2, String str, Object obj2) {
        MotionScene motionScene = this.a.t;
        if (motionScene != null) {
            motionScene.setKeyframe((View) obj, i2, str, obj2);
            MotionLayout motionLayout = this.a;
            motionLayout.I = i2 / 100.0f;
            motionLayout.G = 0.0f;
            motionLayout.rebuildScene();
            this.a.P(true);
        }
    }

    public boolean setKeyFramePosition(Object obj, int i2, int i3, float f, float f2) {
        MotionLayout motionLayout = this.a;
        if (motionLayout.t == null) {
            return false;
        }
        MotionController motionController = motionLayout.C.get(obj);
        MotionLayout motionLayout2 = this.a;
        int i4 = (int) (motionLayout2.F * 100.0f);
        if (motionController == null) {
            return false;
        }
        View view = (View) obj;
        if (!motionLayout2.t.s(view, i4)) {
            return false;
        }
        float m = motionController.m(2, f, f2);
        float m2 = motionController.m(5, f, f2);
        this.a.t.setKeyframe(view, i4, "motion:percentX", Float.valueOf(m));
        this.a.t.setKeyframe(view, i4, "motion:percentY", Float.valueOf(m2));
        this.a.rebuildScene();
        this.a.P(true);
        this.a.invalidate();
        return true;
    }

    public void setKeyframe(Object obj, String str, Object obj2) {
        if (obj instanceof Key) {
            ((Key) obj).setValue(str, obj2);
            this.a.rebuildScene();
            this.a.K = true;
        }
    }

    public void setState(String str) {
        if (str == null) {
            str = "motion_base";
        }
        if (this.f297c == str) {
            return;
        }
        this.f297c = str;
        this.f298d = null;
        MotionLayout motionLayout = this.a;
        if (motionLayout.t == null) {
            motionLayout.t = this.b;
        }
        int X = str != null ? motionLayout.X(str) : R.id.motion_base;
        this.e = X;
        if (X != 0) {
            if (X == this.a.getStartState()) {
                this.a.setProgress(0.0f);
            } else {
                if (X != this.a.getEndState()) {
                    this.a.transitionToState(X);
                }
                this.a.setProgress(1.0f);
            }
        }
        this.a.requestLayout();
    }

    public void setToolPosition(float f) {
        MotionLayout motionLayout = this.a;
        if (motionLayout.t == null) {
            motionLayout.t = this.b;
        }
        motionLayout.setProgress(f);
        this.a.P(true);
        this.a.requestLayout();
        this.a.invalidate();
    }

    public void setTransition(String str, String str2) {
        MotionLayout motionLayout = this.a;
        if (motionLayout.t == null) {
            motionLayout.t = this.b;
        }
        int X = motionLayout.X(str);
        int X2 = this.a.X(str2);
        this.a.setTransition(X, X2);
        this.e = X;
        this.f = X2;
        this.f297c = str;
        this.f298d = str2;
    }

    public void setViewDebug(Object obj, int i2) {
        MotionController motionController = this.a.C.get(obj);
        if (motionController != null) {
            motionController.setDrawPath(i2);
            this.a.invalidate();
        }
    }
}
