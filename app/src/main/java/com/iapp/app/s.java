package com.iapp.app;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import com.iapp.app.run.mian;

/* loaded from: classes.dex */
public class s {
    private TextView a;
    private Activity b;

    /* renamed from: c, reason: collision with root package name */
    private String f2003c;

    /* renamed from: d, reason: collision with root package name */
    private String f2004d;
    private String e;
    private String f;
    private TextWatcher g;
    private TextWatcher h;

    /* loaded from: classes.dex */
    class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (s.this.e != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(s.this.b);
                tVar.T("st_vId", Integer.valueOf(s.this.a.getId()));
                tVar.T("st_vW", s.this.a);
                tVar.T("st_sS", editable.toString());
                tVar.f(s.this.e);
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (s.this.f2004d != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(s.this.b);
                tVar.T("st_vId", Integer.valueOf(s.this.a.getId()));
                tVar.T("st_vW", s.this.a);
                tVar.T("st_sS", charSequence.toString());
                tVar.T("st_sT", Integer.valueOf(i2));
                tVar.T("st_cT", Integer.valueOf(i3));
                tVar.T("st_aR", Integer.valueOf(i4));
                tVar.f(s.this.f2004d);
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (s.this.f2003c != null) {
                c.b.a.a.t tVar = new c.b.a.a.t(s.this.b);
                tVar.T("st_vId", Integer.valueOf(s.this.a.getId()));
                tVar.T("st_vW", s.this.a);
                tVar.T("st_sS", charSequence.toString());
                tVar.T("st_sT", Integer.valueOf(i2));
                tVar.T("st_bE", Integer.valueOf(i3));
                tVar.T("st_cT", Integer.valueOf(i4));
                tVar.f(s.this.f2003c);
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements TextWatcher {
        b() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (s.this.e != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(s.this.b);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(s.this.a.getId()));
                aid_YuCodeX.dim("st_vW", s.this.a);
                aid_YuCodeX.dim("st_sS", editable.toString());
                mian.c(s.this.f, "aftertextchanged" + s.this.e, aid_YuCodeX);
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (s.this.f2004d != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(s.this.b);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(s.this.a.getId()));
                aid_YuCodeX.dim("st_vW", s.this.a);
                aid_YuCodeX.dim("st_sS", charSequence.toString());
                aid_YuCodeX.dim("st_sT", Integer.valueOf(i2));
                aid_YuCodeX.dim("st_cT", Integer.valueOf(i3));
                aid_YuCodeX.dim("st_aR", Integer.valueOf(i4));
                mian.c(s.this.f, "beforetextchanged" + s.this.f2004d, aid_YuCodeX);
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (s.this.f2003c != null) {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(s.this.b);
                aid_YuCodeX.dim("st_vId", Integer.valueOf(s.this.a.getId()));
                aid_YuCodeX.dim("st_vW", s.this.a);
                aid_YuCodeX.dim("st_sS", charSequence.toString());
                aid_YuCodeX.dim("st_sT", Integer.valueOf(i2));
                aid_YuCodeX.dim("st_bE", Integer.valueOf(i3));
                aid_YuCodeX.dim("st_cT", Integer.valueOf(i4));
                mian.c(s.this.f, "ontextchanged" + s.this.f2003c, aid_YuCodeX);
            }
        }
    }

    public s(TextView textView, Activity activity) {
        this.a = null;
        this.b = null;
        this.f = null;
        a aVar = new a();
        this.g = aVar;
        this.h = new b();
        this.a = textView;
        this.b = activity;
        textView.addTextChangedListener(aVar);
        String obj = ((Object[]) textView.getTag())[2].toString();
        this.f2003c = c.b.a.a.p.c(obj, "<eventItme type=\"ontextchanged\">", "</eventItme>");
        this.f2004d = c.b.a.a.p.c(obj, "<eventItme type=\"beforetextchanged\">", "</eventItme>");
        this.e = c.b.a.a.p.c(obj, "<eventItme type=\"aftertextchanged\">", "</eventItme>");
    }

    public s(TextView textView, Activity activity, String str) {
        this.a = null;
        this.b = null;
        this.f = null;
        this.g = new a();
        this.h = new b();
        this.a = textView;
        this.b = activity;
        this.f = str;
        String obj = ((Object[]) textView.getTag())[2].toString();
        this.f2003c = c.b.a.a.p.c(obj, "<eventItme type=\"ontextchanged\">", "</eventItme>");
        this.f2004d = c.b.a.a.p.c(obj, "<eventItme type=\"beforetextchanged\">", "</eventItme>");
        this.e = c.b.a.a.p.c(obj, "<eventItme type=\"aftertextchanged\">", "</eventItme>");
        textView.addTextChangedListener(this.h);
    }
}
