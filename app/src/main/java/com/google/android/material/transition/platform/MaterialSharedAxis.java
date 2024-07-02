package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@RequiresApi(21)
/* loaded from: classes.dex */
public final class MaterialSharedAxis extends h<VisibilityAnimatorProvider> {
    public static final int X = 0;
    public static final int Y = 1;
    public static final int Z = 2;

    @AttrRes
    private static final int f = R.attr.motionDurationLong1;

    @AttrRes
    private static final int g = R.attr.motionEasingStandard;

    /* renamed from: d, reason: collision with root package name */
    private final int f1801d;
    private final boolean e;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface Axis {
    }

    public MaterialSharedAxis(int i2, boolean z) {
        super(g(i2, z), h());
        this.f1801d = i2;
        this.e = z;
    }

    private static VisibilityAnimatorProvider g(int i2, boolean z) {
        if (i2 == 0) {
            return new SlideDistanceProvider(z ? 8388613 : 8388611);
        }
        if (i2 == 1) {
            return new SlideDistanceProvider(z ? 80 : 48);
        }
        if (i2 == 2) {
            return new ScaleProvider(z);
        }
        throw new IllegalArgumentException("Invalid axis: " + i2);
    }

    private static VisibilityAnimatorProvider h() {
        return new FadeThroughProvider();
    }

    @Override // com.google.android.material.transition.platform.h
    public /* bridge */ /* synthetic */ void addAdditionalAnimatorProvider(@NonNull VisibilityAnimatorProvider visibilityAnimatorProvider) {
        super.addAdditionalAnimatorProvider(visibilityAnimatorProvider);
    }

    @Override // com.google.android.material.transition.platform.h
    public /* bridge */ /* synthetic */ void clearAdditionalAnimatorProvider() {
        super.clearAdditionalAnimatorProvider();
    }

    @Override // com.google.android.material.transition.platform.h
    @AttrRes
    int d(boolean z) {
        return f;
    }

    @Override // com.google.android.material.transition.platform.h
    @AttrRes
    int e(boolean z) {
        return g;
    }

    public int getAxis() {
        return this.f1801d;
    }

    @Override // com.google.android.material.transition.platform.h
    @NonNull
    public /* bridge */ /* synthetic */ VisibilityAnimatorProvider getPrimaryAnimatorProvider() {
        return super.getPrimaryAnimatorProvider();
    }

    @Override // com.google.android.material.transition.platform.h
    @Nullable
    public /* bridge */ /* synthetic */ VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return super.getSecondaryAnimatorProvider();
    }

    public boolean isForward() {
        return this.e;
    }

    @Override // com.google.android.material.transition.platform.h, android.transition.Visibility
    public /* bridge */ /* synthetic */ Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return super.onAppear(viewGroup, view, transitionValues, transitionValues2);
    }

    @Override // com.google.android.material.transition.platform.h, android.transition.Visibility
    public /* bridge */ /* synthetic */ Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return super.onDisappear(viewGroup, view, transitionValues, transitionValues2);
    }

    @Override // com.google.android.material.transition.platform.h
    public /* bridge */ /* synthetic */ boolean removeAdditionalAnimatorProvider(@NonNull VisibilityAnimatorProvider visibilityAnimatorProvider) {
        return super.removeAdditionalAnimatorProvider(visibilityAnimatorProvider);
    }

    @Override // com.google.android.material.transition.platform.h
    public /* bridge */ /* synthetic */ void setSecondaryAnimatorProvider(@Nullable VisibilityAnimatorProvider visibilityAnimatorProvider) {
        super.setSecondaryAnimatorProvider(visibilityAnimatorProvider);
    }
}
