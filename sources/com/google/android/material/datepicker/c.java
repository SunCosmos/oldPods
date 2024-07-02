package com.google.android.material.datepicker;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.R;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/* loaded from: classes.dex */
abstract class c extends TextWatcherAdapter {

    @NonNull
    private final TextInputLayout a;
    private final DateFormat b;

    /* renamed from: c, reason: collision with root package name */
    private final CalendarConstraints f1554c;

    /* renamed from: d, reason: collision with root package name */
    private final String f1555d;
    private final Runnable e;
    private Runnable f;

    /* loaded from: classes.dex */
    class a implements Runnable {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout textInputLayout = c.this.a;
            DateFormat dateFormat = c.this.b;
            Context context = textInputLayout.getContext();
            textInputLayout.setError(context.getString(R.string.mtrl_picker_invalid_format) + "\n" + String.format(context.getString(R.string.mtrl_picker_invalid_format_use), this.a) + "\n" + String.format(context.getString(R.string.mtrl_picker_invalid_format_example), dateFormat.format(new Date(j.o().getTimeInMillis()))));
            c.this.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements Runnable {
        final /* synthetic */ long a;

        b(long j) {
            this.a = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.a.setError(String.format(c.this.f1555d, d.c(this.a)));
            c.this.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(String str, DateFormat dateFormat, @NonNull TextInputLayout textInputLayout, CalendarConstraints calendarConstraints) {
        this.b = dateFormat;
        this.a = textInputLayout;
        this.f1554c = calendarConstraints;
        this.f1555d = textInputLayout.getContext().getString(R.string.mtrl_picker_out_of_range);
        this.e = new a(str);
    }

    private Runnable d(long j) {
        return new b(j);
    }

    abstract void e();

    abstract void f(@Nullable Long l);

    public void g(View view, Runnable runnable) {
        view.postDelayed(runnable, 1000L);
    }

    @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
    public void onTextChanged(@NonNull CharSequence charSequence, int i2, int i3, int i4) {
        this.a.removeCallbacks(this.e);
        this.a.removeCallbacks(this.f);
        this.a.setError(null);
        f(null);
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        try {
            Date parse = this.b.parse(charSequence.toString());
            this.a.setError(null);
            long time = parse.getTime();
            if (this.f1554c.getDateValidator().isValid(time) && this.f1554c.k(time)) {
                f(Long.valueOf(parse.getTime()));
                return;
            }
            Runnable d2 = d(time);
            this.f = d2;
            g(this.a, d2);
        } catch (ParseException unused) {
            g(this.a, this.e);
        }
    }
}
