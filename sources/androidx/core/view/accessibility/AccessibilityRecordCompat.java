package androidx.core.view.accessibility;

import android.os.Build;
import android.os.Parcelable;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import androidx.annotation.NonNull;
import java.util.List;

/* loaded from: classes.dex */
public class AccessibilityRecordCompat {
    private final AccessibilityRecord a;

    @Deprecated
    public AccessibilityRecordCompat(Object obj) {
        this.a = (AccessibilityRecord) obj;
    }

    public static int getMaxScrollX(AccessibilityRecord accessibilityRecord) {
        if (Build.VERSION.SDK_INT >= 15) {
            return accessibilityRecord.getMaxScrollX();
        }
        return 0;
    }

    public static int getMaxScrollY(AccessibilityRecord accessibilityRecord) {
        if (Build.VERSION.SDK_INT >= 15) {
            return accessibilityRecord.getMaxScrollY();
        }
        return 0;
    }

    @Deprecated
    public static AccessibilityRecordCompat obtain() {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain());
    }

    @Deprecated
    public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat accessibilityRecordCompat) {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain(accessibilityRecordCompat.a));
    }

    public static void setMaxScrollX(AccessibilityRecord accessibilityRecord, int i2) {
        if (Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollX(i2);
        }
    }

    public static void setMaxScrollY(AccessibilityRecord accessibilityRecord, int i2) {
        if (Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord.setMaxScrollY(i2);
        }
    }

    public static void setSource(@NonNull AccessibilityRecord accessibilityRecord, View view, int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            accessibilityRecord.setSource(view, i2);
        }
    }

    @Deprecated
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessibilityRecordCompat)) {
            return false;
        }
        AccessibilityRecord accessibilityRecord = this.a;
        AccessibilityRecord accessibilityRecord2 = ((AccessibilityRecordCompat) obj).a;
        if (accessibilityRecord == null) {
            if (accessibilityRecord2 != null) {
                return false;
            }
        } else if (!accessibilityRecord.equals(accessibilityRecord2)) {
            return false;
        }
        return true;
    }

    @Deprecated
    public int getAddedCount() {
        return this.a.getAddedCount();
    }

    @Deprecated
    public CharSequence getBeforeText() {
        return this.a.getBeforeText();
    }

    @Deprecated
    public CharSequence getClassName() {
        return this.a.getClassName();
    }

    @Deprecated
    public CharSequence getContentDescription() {
        return this.a.getContentDescription();
    }

    @Deprecated
    public int getCurrentItemIndex() {
        return this.a.getCurrentItemIndex();
    }

    @Deprecated
    public int getFromIndex() {
        return this.a.getFromIndex();
    }

    @Deprecated
    public Object getImpl() {
        return this.a;
    }

    @Deprecated
    public int getItemCount() {
        return this.a.getItemCount();
    }

    @Deprecated
    public int getMaxScrollX() {
        return getMaxScrollX(this.a);
    }

    @Deprecated
    public int getMaxScrollY() {
        return getMaxScrollY(this.a);
    }

    @Deprecated
    public Parcelable getParcelableData() {
        return this.a.getParcelableData();
    }

    @Deprecated
    public int getRemovedCount() {
        return this.a.getRemovedCount();
    }

    @Deprecated
    public int getScrollX() {
        return this.a.getScrollX();
    }

    @Deprecated
    public int getScrollY() {
        return this.a.getScrollY();
    }

    @Deprecated
    public AccessibilityNodeInfoCompat getSource() {
        return AccessibilityNodeInfoCompat.l(this.a.getSource());
    }

    @Deprecated
    public List<CharSequence> getText() {
        return this.a.getText();
    }

    @Deprecated
    public int getToIndex() {
        return this.a.getToIndex();
    }

    @Deprecated
    public int getWindowId() {
        return this.a.getWindowId();
    }

    @Deprecated
    public int hashCode() {
        AccessibilityRecord accessibilityRecord = this.a;
        if (accessibilityRecord == null) {
            return 0;
        }
        return accessibilityRecord.hashCode();
    }

    @Deprecated
    public boolean isChecked() {
        return this.a.isChecked();
    }

    @Deprecated
    public boolean isEnabled() {
        return this.a.isEnabled();
    }

    @Deprecated
    public boolean isFullScreen() {
        return this.a.isFullScreen();
    }

    @Deprecated
    public boolean isPassword() {
        return this.a.isPassword();
    }

    @Deprecated
    public boolean isScrollable() {
        return this.a.isScrollable();
    }

    @Deprecated
    public void recycle() {
        this.a.recycle();
    }

    @Deprecated
    public void setAddedCount(int i2) {
        this.a.setAddedCount(i2);
    }

    @Deprecated
    public void setBeforeText(CharSequence charSequence) {
        this.a.setBeforeText(charSequence);
    }

    @Deprecated
    public void setChecked(boolean z) {
        this.a.setChecked(z);
    }

    @Deprecated
    public void setClassName(CharSequence charSequence) {
        this.a.setClassName(charSequence);
    }

    @Deprecated
    public void setContentDescription(CharSequence charSequence) {
        this.a.setContentDescription(charSequence);
    }

    @Deprecated
    public void setCurrentItemIndex(int i2) {
        this.a.setCurrentItemIndex(i2);
    }

    @Deprecated
    public void setEnabled(boolean z) {
        this.a.setEnabled(z);
    }

    @Deprecated
    public void setFromIndex(int i2) {
        this.a.setFromIndex(i2);
    }

    @Deprecated
    public void setFullScreen(boolean z) {
        this.a.setFullScreen(z);
    }

    @Deprecated
    public void setItemCount(int i2) {
        this.a.setItemCount(i2);
    }

    @Deprecated
    public void setMaxScrollX(int i2) {
        setMaxScrollX(this.a, i2);
    }

    @Deprecated
    public void setMaxScrollY(int i2) {
        setMaxScrollY(this.a, i2);
    }

    @Deprecated
    public void setParcelableData(Parcelable parcelable) {
        this.a.setParcelableData(parcelable);
    }

    @Deprecated
    public void setPassword(boolean z) {
        this.a.setPassword(z);
    }

    @Deprecated
    public void setRemovedCount(int i2) {
        this.a.setRemovedCount(i2);
    }

    @Deprecated
    public void setScrollX(int i2) {
        this.a.setScrollX(i2);
    }

    @Deprecated
    public void setScrollY(int i2) {
        this.a.setScrollY(i2);
    }

    @Deprecated
    public void setScrollable(boolean z) {
        this.a.setScrollable(z);
    }

    @Deprecated
    public void setSource(View view) {
        this.a.setSource(view);
    }

    @Deprecated
    public void setSource(View view, int i2) {
        setSource(this.a, view, i2);
    }

    @Deprecated
    public void setToIndex(int i2) {
        this.a.setToIndex(i2);
    }
}
