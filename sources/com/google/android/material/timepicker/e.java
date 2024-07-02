package com.google.android.material.timepicker;

import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;

/* loaded from: classes.dex */
class e implements TextView.OnEditorActionListener, View.OnKeyListener {
    private final ChipTextInputComboView a;
    private final ChipTextInputComboView b;

    /* renamed from: c, reason: collision with root package name */
    private final TimeModel f1746c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f1747d = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(ChipTextInputComboView chipTextInputComboView, ChipTextInputComboView chipTextInputComboView2, TimeModel timeModel) {
        this.a = chipTextInputComboView;
        this.b = chipTextInputComboView2;
        this.f1746c = timeModel;
    }

    private void b(int i2) {
        this.b.setChecked(i2 == 12);
        this.a.setChecked(i2 == 10);
        this.f1746c.f = i2;
    }

    private boolean c(int i2, KeyEvent keyEvent, EditText editText) {
        Editable text = editText.getText();
        if (text == null) {
            return false;
        }
        if (!(i2 >= 7 && i2 <= 16 && keyEvent.getAction() == 1 && editText.getSelectionStart() == 2 && text.length() == 2)) {
            return false;
        }
        b(12);
        return true;
    }

    private boolean d(int i2, KeyEvent keyEvent, EditText editText) {
        if (!(i2 == 67 && keyEvent.getAction() == 0 && TextUtils.isEmpty(editText.getText()))) {
            return false;
        }
        b(10);
        return true;
    }

    public void a() {
        TextInputLayout e = this.a.e();
        TextInputLayout e2 = this.b.e();
        EditText editText = e.getEditText();
        EditText editText2 = e2.getEditText();
        editText.setImeOptions(268435461);
        editText2.setImeOptions(268435462);
        editText.setOnEditorActionListener(this);
        editText.setOnKeyListener(this);
        editText2.setOnKeyListener(this);
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
        boolean z = i2 == 5;
        if (z) {
            b(12);
        }
        return z;
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (this.f1747d) {
            return false;
        }
        this.f1747d = true;
        EditText editText = (EditText) view;
        boolean d2 = this.f1746c.f == 12 ? d(i2, keyEvent, editText) : c(i2, keyEvent, editText);
        this.f1747d = false;
        return d2;
    }
}
