package com.google.android.material.button;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.annotation.BoolRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;

/* loaded from: classes.dex */
public class MaterialButtonToggleGroup extends LinearLayout {
    private static final String k = MaterialButtonToggleGroup.class.getSimpleName();
    private static final int l = R.style.Widget_MaterialComponents_MaterialButtonToggleGroup;
    private final List<d> a;
    private final c b;

    /* renamed from: c */
    private final e f1506c;

    /* renamed from: d */
    private final LinkedHashSet<OnButtonCheckedListener> f1507d;
    private final Comparator<MaterialButton> e;
    private Integer[] f;
    private boolean g;
    private boolean h;

    /* renamed from: i */
    private boolean f1508i;

    @IdRes
    private int j;

    /* loaded from: classes.dex */
    public interface OnButtonCheckedListener {
        void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, @IdRes int i2, boolean z);
    }

    /* loaded from: classes.dex */
    public class a implements Comparator<MaterialButton> {
        a() {
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(MaterialButton materialButton, MaterialButton materialButton2) {
            int compareTo = Boolean.valueOf(materialButton.isChecked()).compareTo(Boolean.valueOf(materialButton2.isChecked()));
            if (compareTo != 0) {
                return compareTo;
            }
            int compareTo2 = Boolean.valueOf(materialButton.isPressed()).compareTo(Boolean.valueOf(materialButton2.isPressed()));
            return compareTo2 != 0 ? compareTo2 : Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton)).compareTo(Integer.valueOf(MaterialButtonToggleGroup.this.indexOfChild(materialButton2)));
        }
    }

    /* loaded from: classes.dex */
    class b extends AccessibilityDelegateCompat {
        b() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(0, 1, MaterialButtonToggleGroup.this.l(view), 1, false, ((MaterialButton) view).isChecked()));
        }
    }

    /* loaded from: classes.dex */
    public class c implements MaterialButton.OnCheckedChangeListener {
        private c() {
        }

        /* synthetic */ c(MaterialButtonToggleGroup materialButtonToggleGroup, a aVar) {
            this();
        }

        @Override // com.google.android.material.button.MaterialButton.OnCheckedChangeListener
        public void onCheckedChanged(@NonNull MaterialButton materialButton, boolean z) {
            if (MaterialButtonToggleGroup.this.g) {
                return;
            }
            if (MaterialButtonToggleGroup.this.h) {
                MaterialButtonToggleGroup.this.j = z ? materialButton.getId() : -1;
            }
            if (MaterialButtonToggleGroup.this.r(materialButton.getId(), z)) {
                MaterialButtonToggleGroup.this.j(materialButton.getId(), materialButton.isChecked());
            }
            MaterialButtonToggleGroup.this.invalidate();
        }
    }

    /* loaded from: classes.dex */
    public static class d {
        private static final CornerSize e = new AbsoluteCornerSize(0.0f);
        CornerSize a;
        CornerSize b;

        /* renamed from: c */
        CornerSize f1510c;

        /* renamed from: d */
        CornerSize f1511d;

        d(CornerSize cornerSize, CornerSize cornerSize2, CornerSize cornerSize3, CornerSize cornerSize4) {
            this.a = cornerSize;
            this.b = cornerSize3;
            this.f1510c = cornerSize4;
            this.f1511d = cornerSize2;
        }

        public static d a(d dVar) {
            CornerSize cornerSize = e;
            return new d(cornerSize, dVar.f1511d, cornerSize, dVar.f1510c);
        }

        public static d b(d dVar, View view) {
            return ViewUtils.isLayoutRtl(view) ? c(dVar) : d(dVar);
        }

        public static d c(d dVar) {
            CornerSize cornerSize = dVar.a;
            CornerSize cornerSize2 = dVar.f1511d;
            CornerSize cornerSize3 = e;
            return new d(cornerSize, cornerSize2, cornerSize3, cornerSize3);
        }

        public static d d(d dVar) {
            CornerSize cornerSize = e;
            return new d(cornerSize, cornerSize, dVar.b, dVar.f1510c);
        }

        public static d e(d dVar, View view) {
            return ViewUtils.isLayoutRtl(view) ? d(dVar) : c(dVar);
        }

        public static d f(d dVar) {
            CornerSize cornerSize = dVar.a;
            CornerSize cornerSize2 = e;
            return new d(cornerSize, cornerSize2, dVar.b, cornerSize2);
        }
    }

    /* loaded from: classes.dex */
    public class e implements MaterialButton.a {
        private e() {
        }

        /* synthetic */ e(MaterialButtonToggleGroup materialButtonToggleGroup, a aVar) {
            this();
        }

        @Override // com.google.android.material.button.MaterialButton.a
        public void a(@NonNull MaterialButton materialButton, boolean z) {
            MaterialButtonToggleGroup.this.invalidate();
        }
    }

    public MaterialButtonToggleGroup(@NonNull Context context) {
        this(context, null);
    }

    public MaterialButtonToggleGroup(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialButtonToggleGroupStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public MaterialButtonToggleGroup(@androidx.annotation.NonNull android.content.Context r7, @androidx.annotation.Nullable android.util.AttributeSet r8, int r9) {
        /*
            r6 = this;
            int r4 = com.google.android.material.button.MaterialButtonToggleGroup.l
            android.content.Context r7 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r7, r8, r9, r4)
            r6.<init>(r7, r8, r9)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r6.a = r7
            com.google.android.material.button.MaterialButtonToggleGroup$c r7 = new com.google.android.material.button.MaterialButtonToggleGroup$c
            r0 = 0
            r7.<init>(r6, r0)
            r6.b = r7
            com.google.android.material.button.MaterialButtonToggleGroup$e r7 = new com.google.android.material.button.MaterialButtonToggleGroup$e
            r7.<init>(r6, r0)
            r6.f1506c = r7
            java.util.LinkedHashSet r7 = new java.util.LinkedHashSet
            r7.<init>()
            r6.f1507d = r7
            com.google.android.material.button.MaterialButtonToggleGroup$a r7 = new com.google.android.material.button.MaterialButtonToggleGroup$a
            r7.<init>()
            r6.e = r7
            r7 = 0
            r6.g = r7
            android.content.Context r0 = r6.getContext()
            int[] r2 = com.google.android.material.R.styleable.MaterialButtonToggleGroup
            int[] r5 = new int[r7]
            r1 = r8
            r3 = r9
            android.content.res.TypedArray r8 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r0, r1, r2, r3, r4, r5)
            int r9 = com.google.android.material.R.styleable.MaterialButtonToggleGroup_singleSelection
            boolean r9 = r8.getBoolean(r9, r7)
            r6.setSingleSelection(r9)
            int r9 = com.google.android.material.R.styleable.MaterialButtonToggleGroup_checkedButton
            r0 = -1
            int r9 = r8.getResourceId(r9, r0)
            r6.j = r9
            int r9 = com.google.android.material.R.styleable.MaterialButtonToggleGroup_selectionRequired
            boolean r7 = r8.getBoolean(r9, r7)
            r6.f1508i = r7
            r7 = 1
            r6.setChildrenDrawingOrderEnabled(r7)
            r8.recycle()
            androidx.core.view.ViewCompat.setImportantForAccessibility(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.button.MaterialButtonToggleGroup.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    private void g() {
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        if (firstVisibleChildIndex == -1) {
            return;
        }
        for (int i2 = firstVisibleChildIndex + 1; i2 < getChildCount(); i2++) {
            MaterialButton k2 = k(i2);
            int min = Math.min(k2.getStrokeWidth(), k(i2 - 1).getStrokeWidth());
            LinearLayout.LayoutParams h = h(k2);
            if (getOrientation() == 0) {
                MarginLayoutParamsCompat.setMarginEnd(h, 0);
                MarginLayoutParamsCompat.setMarginStart(h, -min);
                h.topMargin = 0;
            } else {
                h.bottomMargin = 0;
                h.topMargin = -min;
                MarginLayoutParamsCompat.setMarginStart(h, 0);
            }
            k2.setLayoutParams(h);
        }
        o(firstVisibleChildIndex);
    }

    private int getFirstVisibleChildIndex() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (n(i2)) {
                return i2;
            }
        }
        return -1;
    }

    private int getLastVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (n(childCount)) {
                return childCount;
            }
        }
        return -1;
    }

    private int getVisibleButtonCount() {
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if ((getChildAt(i3) instanceof MaterialButton) && n(i3)) {
                i2++;
            }
        }
        return i2;
    }

    @NonNull
    private LinearLayout.LayoutParams h(@NonNull View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams instanceof LinearLayout.LayoutParams ? (LinearLayout.LayoutParams) layoutParams : new LinearLayout.LayoutParams(layoutParams.width, layoutParams.height);
    }

    private void i(int i2, boolean z) {
        MaterialButton materialButton = (MaterialButton) findViewById(i2);
        if (materialButton != null) {
            materialButton.setChecked(z);
        }
    }

    public void j(@IdRes int i2, boolean z) {
        Iterator<OnButtonCheckedListener> it = this.f1507d.iterator();
        while (it.hasNext()) {
            it.next().onButtonChecked(this, i2, z);
        }
    }

    private MaterialButton k(int i2) {
        return (MaterialButton) getChildAt(i2);
    }

    public int l(@Nullable View view) {
        if (!(view instanceof MaterialButton)) {
            return -1;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3) == view) {
                return i2;
            }
            if ((getChildAt(i3) instanceof MaterialButton) && n(i3)) {
                i2++;
            }
        }
        return -1;
    }

    @Nullable
    private d m(int i2, int i3, int i4) {
        d dVar = this.a.get(i2);
        if (i3 == i4) {
            return dVar;
        }
        boolean z = getOrientation() == 0;
        if (i2 == i3) {
            return z ? d.e(dVar, this) : d.f(dVar);
        }
        if (i2 == i4) {
            return z ? d.b(dVar, this) : d.a(dVar);
        }
        return null;
    }

    private boolean n(int i2) {
        return getChildAt(i2).getVisibility() != 8;
    }

    private void o(int i2) {
        if (getChildCount() == 0 || i2 == -1) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) k(i2).getLayoutParams();
        if (getOrientation() == 1) {
            layoutParams.topMargin = 0;
            layoutParams.bottomMargin = 0;
        } else {
            MarginLayoutParamsCompat.setMarginEnd(layoutParams, 0);
            MarginLayoutParamsCompat.setMarginStart(layoutParams, 0);
            layoutParams.leftMargin = 0;
            layoutParams.rightMargin = 0;
        }
    }

    private void p(@IdRes int i2, boolean z) {
        View findViewById = findViewById(i2);
        if (findViewById instanceof MaterialButton) {
            this.g = true;
            ((MaterialButton) findViewById).setChecked(z);
            this.g = false;
        }
    }

    private static void q(ShapeAppearanceModel.Builder builder, @Nullable d dVar) {
        if (dVar == null) {
            builder.setAllCornerSizes(0.0f);
        } else {
            builder.setTopLeftCornerSize(dVar.a).setBottomLeftCornerSize(dVar.f1511d).setTopRightCornerSize(dVar.b).setBottomRightCornerSize(dVar.f1510c);
        }
    }

    public boolean r(int i2, boolean z) {
        List<Integer> checkedButtonIds = getCheckedButtonIds();
        if (this.f1508i && checkedButtonIds.isEmpty()) {
            p(i2, true);
            this.j = i2;
            return false;
        }
        if (z && this.h) {
            checkedButtonIds.remove(Integer.valueOf(i2));
            Iterator<Integer> it = checkedButtonIds.iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                p(intValue, false);
                j(intValue, false);
            }
        }
        return true;
    }

    private void s() {
        TreeMap treeMap = new TreeMap(this.e);
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            treeMap.put(k(i2), Integer.valueOf(i2));
        }
        this.f = (Integer[]) treeMap.values().toArray(new Integer[0]);
    }

    private void setCheckedId(int i2) {
        this.j = i2;
        j(i2, true);
    }

    private void setGeneratedIdIfNeeded(@NonNull MaterialButton materialButton) {
        if (materialButton.getId() == -1) {
            materialButton.setId(ViewCompat.generateViewId());
        }
    }

    private void setupButtonChild(@NonNull MaterialButton materialButton) {
        materialButton.setMaxLines(1);
        materialButton.setEllipsize(TextUtils.TruncateAt.END);
        materialButton.setCheckable(true);
        materialButton.addOnCheckedChangeListener(this.b);
        materialButton.setOnPressedChangeListenerInternal(this.f1506c);
        materialButton.setShouldDrawSurfaceColorStroke(true);
    }

    public void addOnButtonCheckedListener(@NonNull OnButtonCheckedListener onButtonCheckedListener) {
        this.f1507d.add(onButtonCheckedListener);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!(view instanceof MaterialButton)) {
            Log.e(k, "Child views must be of type MaterialButton.");
            return;
        }
        super.addView(view, i2, layoutParams);
        MaterialButton materialButton = (MaterialButton) view;
        setGeneratedIdIfNeeded(materialButton);
        setupButtonChild(materialButton);
        if (materialButton.isChecked()) {
            r(materialButton.getId(), true);
            setCheckedId(materialButton.getId());
        }
        ShapeAppearanceModel shapeAppearanceModel = materialButton.getShapeAppearanceModel();
        this.a.add(new d(shapeAppearanceModel.getTopLeftCornerSize(), shapeAppearanceModel.getBottomLeftCornerSize(), shapeAppearanceModel.getTopRightCornerSize(), shapeAppearanceModel.getBottomRightCornerSize()));
        ViewCompat.setAccessibilityDelegate(materialButton, new b());
    }

    public void check(@IdRes int i2) {
        if (i2 == this.j) {
            return;
        }
        i(i2, true);
    }

    public void clearChecked() {
        this.g = true;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            MaterialButton k2 = k(i2);
            k2.setChecked(false);
            j(k2.getId(), false);
        }
        this.g = false;
        setCheckedId(-1);
    }

    public void clearOnButtonCheckedListeners() {
        this.f1507d.clear();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(@NonNull Canvas canvas) {
        s();
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    @NonNull
    public CharSequence getAccessibilityClassName() {
        return MaterialButtonToggleGroup.class.getName();
    }

    @IdRes
    public int getCheckedButtonId() {
        if (this.h) {
            return this.j;
        }
        return -1;
    }

    @NonNull
    public List<Integer> getCheckedButtonIds() {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            MaterialButton k2 = k(i2);
            if (k2.isChecked()) {
                arrayList.add(Integer.valueOf(k2.getId()));
            }
        }
        return arrayList;
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i2, int i3) {
        Integer[] numArr = this.f;
        if (numArr != null && i3 < numArr.length) {
            return numArr[i3].intValue();
        }
        Log.w(k, "Child order wasn't updated");
        return i3;
    }

    public boolean isSelectionRequired() {
        return this.f1508i;
    }

    public boolean isSingleSelection() {
        return this.h;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int i2 = this.j;
        if (i2 != -1) {
            i(i2, true);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(1, getVisibleButtonCount(), false, isSingleSelection() ? 1 : 2));
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        t();
        g();
        super.onMeasure(i2, i3);
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        if (view instanceof MaterialButton) {
            MaterialButton materialButton = (MaterialButton) view;
            materialButton.removeOnCheckedChangeListener(this.b);
            materialButton.setOnPressedChangeListenerInternal(null);
        }
        int indexOfChild = indexOfChild(view);
        if (indexOfChild >= 0) {
            this.a.remove(indexOfChild);
        }
        t();
        g();
    }

    public void removeOnButtonCheckedListener(@NonNull OnButtonCheckedListener onButtonCheckedListener) {
        this.f1507d.remove(onButtonCheckedListener);
    }

    public void setSelectionRequired(boolean z) {
        this.f1508i = z;
    }

    public void setSingleSelection(@BoolRes int i2) {
        setSingleSelection(getResources().getBoolean(i2));
    }

    public void setSingleSelection(boolean z) {
        if (this.h != z) {
            this.h = z;
            clearChecked();
        }
    }

    @VisibleForTesting
    void t() {
        int childCount = getChildCount();
        int firstVisibleChildIndex = getFirstVisibleChildIndex();
        int lastVisibleChildIndex = getLastVisibleChildIndex();
        for (int i2 = 0; i2 < childCount; i2++) {
            MaterialButton k2 = k(i2);
            if (k2.getVisibility() != 8) {
                ShapeAppearanceModel.Builder builder = k2.getShapeAppearanceModel().toBuilder();
                q(builder, m(i2, firstVisibleChildIndex, lastVisibleChildIndex));
                k2.setShapeAppearanceModel(builder.build());
            }
        }
    }

    public void uncheck(@IdRes int i2) {
        i(i2, false);
    }
}
