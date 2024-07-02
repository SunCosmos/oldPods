package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ViewUtils;
import java.util.Calendar;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class MaterialCalendarGridView extends GridView {
    private final Calendar a;
    private final boolean b;

    /* loaded from: classes.dex */
    class a extends AccessibilityDelegateCompat {
        a(MaterialCalendarGridView materialCalendarGridView) {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setCollectionInfo(null);
        }
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = j.q();
        if (MaterialDatePicker.I0(getContext())) {
            setNextFocusLeftId(R.id.cancel_button);
            setNextFocusRightId(R.id.confirm_button);
        }
        this.b = MaterialDatePicker.J0(getContext());
        ViewCompat.setAccessibilityDelegate(this, new a(this));
    }

    private void a(int i2, Rect rect) {
        int b;
        if (i2 == 33) {
            b = getAdapter().i();
        } else {
            if (i2 != 130) {
                super.onFocusChanged(true, i2, rect);
                return;
            }
            b = getAdapter().b();
        }
        setSelection(b);
    }

    private static int c(@NonNull View view) {
        return view.getLeft() + (view.getWidth() / 2);
    }

    private static boolean d(@Nullable Long l, @Nullable Long l2, @Nullable Long l3, @Nullable Long l4) {
        return l == null || l2 == null || l3 == null || l4 == null || l3.longValue() > l2.longValue() || l4.longValue() < l.longValue();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    @NonNull
    /* renamed from: b, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public f getAdapter2() {
        return (f) super.getAdapter();
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAdapter().notifyDataSetChanged();
    }

    @Override // android.view.View
    protected final void onDraw(@NonNull Canvas canvas) {
        int a2;
        int c2;
        int a3;
        int c3;
        int width;
        int i2;
        MaterialCalendarGridView materialCalendarGridView = this;
        super.onDraw(canvas);
        f adapter = getAdapter();
        DateSelector<?> dateSelector = adapter.b;
        b bVar = adapter.f1559d;
        Long item = adapter.getItem(adapter.b());
        Long item2 = adapter.getItem(adapter.i());
        for (Pair<Long, Long> pair : dateSelector.getSelectedRanges()) {
            Long l = pair.first;
            if (l != null) {
                if (pair.second != null) {
                    long longValue = l.longValue();
                    long longValue2 = pair.second.longValue();
                    if (!d(item, item2, Long.valueOf(longValue), Long.valueOf(longValue2))) {
                        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
                        if (longValue < item.longValue()) {
                            a2 = adapter.b();
                            if (adapter.f(a2)) {
                                c2 = 0;
                            } else {
                                View childAt = materialCalendarGridView.getChildAt(a2 - 1);
                                c2 = !isLayoutRtl ? childAt.getRight() : childAt.getLeft();
                            }
                        } else {
                            materialCalendarGridView.a.setTimeInMillis(longValue);
                            a2 = adapter.a(materialCalendarGridView.a.get(5));
                            c2 = c(materialCalendarGridView.getChildAt(a2));
                        }
                        if (longValue2 > item2.longValue()) {
                            a3 = Math.min(adapter.i(), getChildCount() - 1);
                            if (adapter.g(a3)) {
                                c3 = getWidth();
                            } else {
                                View childAt2 = materialCalendarGridView.getChildAt(a3);
                                c3 = !isLayoutRtl ? childAt2.getRight() : childAt2.getLeft();
                            }
                        } else {
                            materialCalendarGridView.a.setTimeInMillis(longValue2);
                            a3 = adapter.a(materialCalendarGridView.a.get(5));
                            c3 = c(materialCalendarGridView.getChildAt(a3));
                        }
                        int itemId = (int) adapter.getItemId(a2);
                        int itemId2 = (int) adapter.getItemId(a3);
                        while (itemId <= itemId2) {
                            int numColumns = getNumColumns() * itemId;
                            int numColumns2 = (getNumColumns() + numColumns) - 1;
                            View childAt3 = materialCalendarGridView.getChildAt(numColumns);
                            int top = childAt3.getTop() + bVar.a.c();
                            int bottom = childAt3.getBottom() - bVar.a.b();
                            if (isLayoutRtl) {
                                int i3 = a3 > numColumns2 ? 0 : c3;
                                width = numColumns > a2 ? getWidth() : c2;
                                i2 = i3;
                            } else {
                                i2 = numColumns > a2 ? 0 : c2;
                                width = a3 > numColumns2 ? getWidth() : c3;
                            }
                            canvas.drawRect(i2, top, width, bottom, bVar.h);
                            itemId++;
                            materialCalendarGridView = this;
                            adapter = adapter;
                        }
                    }
                }
            }
            materialCalendarGridView = this;
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    protected void onFocusChanged(boolean z, int i2, Rect rect) {
        if (z) {
            a(i2, rect);
        } else {
            super.onFocusChanged(false, i2, rect);
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (!super.onKeyDown(i2, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= getAdapter().b()) {
            return true;
        }
        if (19 != i2) {
            return false;
        }
        setSelection(getAdapter().b());
        return true;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i2, int i3) {
        if (!this.b) {
            super.onMeasure(i2, i3);
            return;
        }
        super.onMeasure(i2, View.MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE));
        getLayoutParams().height = getMeasuredHeight();
    }

    @Override // android.widget.AdapterView
    public final void setAdapter(ListAdapter listAdapter) {
        if (!(listAdapter instanceof f)) {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), f.class.getCanonicalName()));
        }
        super.setAdapter(listAdapter);
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public void setSelection(int i2) {
        if (i2 < getAdapter().b()) {
            i2 = getAdapter().b();
        }
        super.setSelection(i2);
    }
}
