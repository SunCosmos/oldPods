package com.iapp.app;

import android.text.Editable;
import android.text.TextWatcher;
import android.webkit.WebView;
import android.widget.TextView;

/* loaded from: classes.dex */
public class t {
    private TextView a;
    private d b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f2005c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f2006d;
    private boolean e;
    private int f;
    private j g;
    private WebView h;

    /* renamed from: i, reason: collision with root package name */
    private String f2007i;

    /* loaded from: classes.dex */
    class a implements TextWatcher {
        a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (t.this.e) {
                t.this.b.d("aftertextchanged" + t.this.f, Integer.valueOf(t.this.f), t.this.a, editable.toString());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (t.this.f2006d) {
                t.this.b.d("beforetextchanged" + t.this.f, Integer.valueOf(t.this.f), t.this.a, charSequence.toString(), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (t.this.f2005c) {
                t.this.b.d("ontextchanged" + t.this.f, Integer.valueOf(t.this.f), t.this.a, charSequence.toString(), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements TextWatcher {
        b() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (t.this.e) {
                t.this.g.b("aftertextchanged" + t.this.f, Integer.valueOf(t.this.f), t.this.a, editable.toString());
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (t.this.f2006d) {
                t.this.g.b("beforetextchanged" + t.this.f, Integer.valueOf(t.this.f), t.this.a, charSequence.toString(), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (t.this.f2005c) {
                t.this.g.b("ontextchanged" + t.this.f, Integer.valueOf(t.this.f), t.this.a, charSequence.toString(), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
            }
        }
    }

    /* loaded from: classes.dex */
    class c implements TextWatcher {
        c() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (t.this.e) {
                t.this.j("aftertextchanged" + t.this.f, t.this.f + ", '" + t.this.f2007i + "', '" + editable.toString() + "'");
            }
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (t.this.f2006d) {
                t.this.j("beforetextchanged" + t.this.f, t.this.f + ", '" + t.this.f2007i + "', '" + charSequence.toString() + "', " + i2 + ", " + i3 + ", " + i4);
            }
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            if (t.this.f2005c) {
                t.this.j("ontextchanged" + t.this.f, t.this.f + ", '" + t.this.f2007i + "', '" + charSequence.toString() + "', " + i2 + ", " + i3 + ", " + i4);
            }
        }
    }

    public t(TextView textView, WebView webView, String str, boolean z, boolean z2, boolean z3) {
        this.a = null;
        this.b = null;
        this.g = null;
        this.a = textView;
        this.h = webView;
        this.f = textView.getId();
        this.f2007i = str;
        this.f2005c = z;
        this.f2006d = z2;
        this.e = z3;
        textView.addTextChangedListener(new c());
    }

    public t(TextView textView, d dVar, boolean z, boolean z2, boolean z3) {
        this.a = null;
        this.b = null;
        this.g = null;
        this.a = textView;
        this.b = dVar;
        this.f = textView.getId();
        this.f2005c = z;
        this.f2006d = z2;
        this.e = z3;
        textView.addTextChangedListener(new a());
    }

    public t(TextView textView, j jVar, boolean z, boolean z2, boolean z3) {
        this.a = null;
        this.b = null;
        this.g = null;
        this.a = textView;
        this.g = jVar;
        this.f = textView.getId();
        this.f2005c = z;
        this.f2006d = z2;
        this.e = z3;
        textView.addTextChangedListener(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(String str, String str2) {
        this.h.loadUrl("javascript:" + str + "(" + str2 + ")");
    }
}
