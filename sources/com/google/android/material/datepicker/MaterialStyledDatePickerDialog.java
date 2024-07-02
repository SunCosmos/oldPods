package com.google.android.material.datepicker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import com.google.android.material.R;
import com.google.android.material.dialog.InsetDialogOnTouchListener;
import com.google.android.material.dialog.MaterialDialogs;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP, RestrictTo.Scope.TESTS})
/* loaded from: classes.dex */
public class MaterialStyledDatePickerDialog extends DatePickerDialog {

    /* renamed from: c, reason: collision with root package name */
    @StyleRes
    private static final int f1540c = R.style.MaterialAlertDialog_MaterialComponents_Picker_Date_Spinner;

    @NonNull
    private final Drawable a;

    @NonNull
    private final Rect b;

    public MaterialStyledDatePickerDialog(@NonNull Context context) {
        this(context, 0);
    }

    public MaterialStyledDatePickerDialog(@NonNull Context context, int i2) {
        this(context, i2, null, -1, -1, -1);
    }

    public MaterialStyledDatePickerDialog(@NonNull Context context, int i2, @Nullable DatePickerDialog.OnDateSetListener onDateSetListener, int i3, int i4, int i5) {
        super(context, i2, onDateSetListener, i3, i4, i5);
        Context context2 = getContext();
        int resolveOrThrow = MaterialAttributes.resolveOrThrow(getContext(), R.attr.colorSurface, getClass().getCanonicalName());
        int i6 = f1540c;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(context2, null, android.R.attr.datePickerStyle, i6);
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(Build.VERSION.SDK_INT < 21 ? 0 : resolveOrThrow));
        Rect dialogBackgroundInsets = MaterialDialogs.getDialogBackgroundInsets(context2, android.R.attr.datePickerStyle, i6);
        this.b = dialogBackgroundInsets;
        this.a = MaterialDialogs.insetDrawable(materialShapeDrawable, dialogBackgroundInsets);
    }

    public MaterialStyledDatePickerDialog(@NonNull Context context, @Nullable DatePickerDialog.OnDateSetListener onDateSetListener, int i2, int i3, int i4) {
        this(context, 0, onDateSetListener, i2, i3, i4);
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(this.a);
        getWindow().getDecorView().setOnTouchListener(new InsetDialogOnTouchListener(this, this.b));
    }
}
