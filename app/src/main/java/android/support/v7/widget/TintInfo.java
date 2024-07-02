package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

/* loaded from: lib/Mus.dex */
class TintInfo {
    public boolean mHasTintList;
    public boolean mHasTintMode;
    public ColorStateList mTintList;
    public PorterDuff.Mode mTintMode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clear() {
        this.mTintList = null;
        this.mHasTintList = false;
        this.mTintMode = null;
        this.mHasTintMode = false;
    }
}
