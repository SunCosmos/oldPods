package com.iapp.app;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import java.util.Locale;

/* loaded from: classes.dex */
public class r {
    private TextToSpeech a;
    private int b = 0;

    /* loaded from: classes.dex */
    class a implements TextToSpeech.OnInitListener {
        a() {
        }

        @Override // android.speech.tts.TextToSpeech.OnInitListener
        public void onInit(int i2) {
            if (i2 != 0) {
                r.this.b = -1;
            }
        }
    }

    /* loaded from: classes.dex */
    class b implements TextToSpeech.OnInitListener {
        final /* synthetic */ String a;
        final /* synthetic */ float b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ float f1957c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ String f1958d;

        b(String str, float f, float f2, String str2) {
            this.a = str;
            this.b = f;
            this.f1957c = f2;
            this.f1958d = str2;
        }

        @Override // android.speech.tts.TextToSpeech.OnInitListener
        public void onInit(int i2) {
            if (i2 != 0) {
                r.this.b = -1;
                return;
            }
            int language = r.this.a.setLanguage(new Locale(this.a));
            if (language == -1 || language == -2) {
                r.this.b = -2;
                return;
            }
            r.this.b = 1;
            r.this.a.setSpeechRate(this.b);
            r.this.a.setPitch(this.f1957c);
            r.this.a.speak(this.f1958d, 0, null);
        }
    }

    public r(Context context) {
        this.a = new TextToSpeech(context, new a());
    }

    public r(Context context, String str, String str2, float f, float f2) {
        this.a = new TextToSpeech(context, new b(str, f, f2, str2));
    }

    public int c() {
        return this.b;
    }

    public boolean d() {
        return this.a.isSpeaking();
    }

    public void e(String str) {
        int language = this.a.setLanguage(new Locale(str));
        if (language == -1 || language == -2) {
            this.b = -2;
        } else {
            this.b = 1;
        }
    }

    public void f(float f) {
        this.a.setPitch(0.5f);
    }

    public void g(float f) {
        this.a.setSpeechRate(0.5f);
    }

    public void h() {
        this.a.shutdown();
    }

    public boolean i(String str, int i2) {
        return this.a.speak(str, i2, null) != -1;
    }

    public boolean j() {
        return this.a.stop() != -1;
    }

    public boolean k(String str, String str2) {
        return this.a.synthesizeToFile(str, null, str2) != -1;
    }
}
