package com.google.android.material.expandable;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* loaded from: classes.dex */
public final class ExpandableWidgetHelper {

    @NonNull
    private final View a;
    private boolean b = false;

    /* renamed from: c, reason: collision with root package name */
    @IdRes
    private int f1567c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public ExpandableWidgetHelper(ExpandableWidget expandableWidget) {
        this.a = (View) expandableWidget;
    }

    private void a() {
        ViewParent parent = this.a.getParent();
        if (parent instanceof CoordinatorLayout) {
            ((CoordinatorLayout) parent).dispatchDependentViewsChanged(this.a);
        }
    }

    @IdRes
    public int getExpandedComponentIdHint() {
        return this.f1567c;
    }

    public boolean isExpanded() {
        return this.b;
    }

    public void onRestoreInstanceState(@NonNull Bundle bundle) {
        this.b = bundle.getBoolean("expanded", false);
        this.f1567c = bundle.getInt("expandedComponentIdHint", 0);
        if (this.b) {
            a();
        }
    }

    @NonNull
    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", this.b);
        bundle.putInt("expandedComponentIdHint", this.f1567c);
        return bundle;
    }

    public boolean setExpanded(boolean z) {
        if (this.b == z) {
            return false;
        }
        this.b = z;
        a();
        return true;
    }

    public void setExpandedComponentIdHint(@IdRes int i2) {
        this.f1567c = i2;
    }
}
