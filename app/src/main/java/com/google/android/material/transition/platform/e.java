package com.google.android.material.transition.platform;

import android.graphics.RectF;
import androidx.annotation.RequiresApi;

@RequiresApi(21)
/* loaded from: classes.dex */
class e {
    private static final d a = new a();
    private static final d b = new b();

    /* loaded from: classes.dex */
    static class a implements d {
        a() {
        }

        @Override // com.google.android.material.transition.platform.d
        public f a(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
            float p = j.p(f4, f6, f2, f3, f, true);
            float f8 = p / f4;
            float f9 = p / f6;
            return new f(f8, f9, p, f5 * f8, p, f7 * f9);
        }

        @Override // com.google.android.material.transition.platform.d
        public void b(RectF rectF, float f, f fVar) {
            rectF.bottom -= Math.abs(fVar.f - fVar.f1809d) * f;
        }

        @Override // com.google.android.material.transition.platform.d
        public boolean c(f fVar) {
            return fVar.f1809d > fVar.f;
        }
    }

    /* loaded from: classes.dex */
    static class b implements d {
        b() {
        }

        @Override // com.google.android.material.transition.platform.d
        public f a(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
            float p = j.p(f5, f7, f2, f3, f, true);
            float f8 = p / f5;
            float f9 = p / f7;
            return new f(f8, f9, f4 * f8, p, f6 * f9, p);
        }

        @Override // com.google.android.material.transition.platform.d
        public void b(RectF rectF, float f, f fVar) {
            float abs = (Math.abs(fVar.e - fVar.f1808c) / 2.0f) * f;
            rectF.left += abs;
            rectF.right -= abs;
        }

        @Override // com.google.android.material.transition.platform.d
        public boolean c(f fVar) {
            return fVar.f1808c > fVar.e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d a(int i2, boolean z, RectF rectF, RectF rectF2) {
        if (i2 == 0) {
            return b(z, rectF, rectF2) ? a : b;
        }
        if (i2 == 1) {
            return a;
        }
        if (i2 == 2) {
            return b;
        }
        throw new IllegalArgumentException("Invalid fit mode: " + i2);
    }

    private static boolean b(boolean z, RectF rectF, RectF rectF2) {
        float width = rectF.width();
        float height = rectF.height();
        float width2 = rectF2.width();
        float height2 = rectF2.height();
        float f = (height2 * width) / width2;
        float f2 = (width2 * height) / width;
        if (z) {
            if (f >= height) {
                return true;
            }
        } else if (f2 >= height2) {
            return true;
        }
        return false;
    }
}
