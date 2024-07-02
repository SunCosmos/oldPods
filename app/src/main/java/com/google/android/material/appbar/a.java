package com.google.android.material.appbar;

import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
class a {
    private final View a;
    private int b;

    /* renamed from: c, reason: collision with root package name */
    private int f1462c;

    /* renamed from: d, reason: collision with root package name */
    private int f1463d;
    private int e;
    private boolean f = true;
    private boolean g = true;

    public a(View view) {
        this.a = view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        View view = this.a;
        ViewCompat.offsetTopAndBottom(view, this.f1463d - (view.getTop() - this.b));
        View view2 = this.a;
        ViewCompat.offsetLeftAndRight(view2, this.e - (view2.getLeft() - this.f1462c));
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.e;
    }

    public int d() {
        return this.f1463d;
    }

    public boolean e() {
        return this.g;
    }

    public boolean f() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        this.b = this.a.getTop();
        this.f1462c = this.a.getLeft();
    }

    public void h(boolean z) {
        this.g = z;
    }

    public boolean i(int i2) {
        if (!this.g || this.e == i2) {
            return false;
        }
        this.e = i2;
        a();
        return true;
    }

    public boolean j(int i2) {
        if (!this.f || this.f1463d == i2) {
            return false;
        }
        this.f1463d = i2;
        a();
        return true;
    }

    public void k(boolean z) {
        this.f = z;
    }
}
