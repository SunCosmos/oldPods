package androidx.appcompat.graphics.drawable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.StateSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.graphics.drawable.a;
import androidx.appcompat.resources.R;
import androidx.core.content.res.TypedArrayUtils;
import org.xmlpull.v1.XmlPullParser;

@SuppressLint({"RestrictedAPI"})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
class b extends androidx.appcompat.graphics.drawable.a {
    private a m;
    private boolean n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class a extends a.d {
        int[][] J;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(a aVar, b bVar, Resources resources) {
            super(aVar, bVar, resources);
            if (aVar != null) {
                this.J = aVar.J;
            } else {
                this.J = new int[f()];
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int A(int[] iArr) {
            int[][] iArr2 = this.J;
            int h = h();
            for (int i2 = 0; i2 < h; i2++) {
                if (StateSet.stateSetMatches(iArr2[i2], iArr)) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return new b(this, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable(Resources resources) {
            return new b(this, resources);
        }

        @Override // androidx.appcompat.graphics.drawable.a.d
        public void o(int i2, int i3) {
            super.o(i2, i3);
            int[][] iArr = new int[i3];
            System.arraycopy(this.J, 0, iArr, 0, i2);
            this.J = iArr;
        }

        @Override // androidx.appcompat.graphics.drawable.a.d
        void r() {
            int[][] iArr = this.J;
            int[][] iArr2 = new int[iArr.length];
            for (int length = iArr.length - 1; length >= 0; length--) {
                int[][] iArr3 = this.J;
                iArr2[length] = iArr3[length] != null ? (int[]) iArr3[length].clone() : null;
            }
            this.J = iArr2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int z(int[] iArr, Drawable drawable) {
            int a = a(drawable);
            this.J[a] = iArr;
            return a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(@Nullable a aVar) {
        if (aVar != null) {
            h(aVar);
        }
    }

    b(a aVar, Resources resources) {
        h(new a(aVar, this, resources));
        onStateChange(getState());
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0049, code lost:
    
        if (r4 == null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004b, code lost:
    
        r4 = r11.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0050, code lost:
    
        if (r4 != 4) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0053, code lost:
    
        if (r4 != 2) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0059, code lost:
    
        if (android.os.Build.VERSION.SDK_INT < 21) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005b, code lost:
    
        r4 = android.graphics.drawable.Drawable.createFromXmlInner(r10, r11, r12, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0060, code lost:
    
        r4 = android.graphics.drawable.Drawable.createFromXmlInner(r10, r11, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007f, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException(r11.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0080, code lost:
    
        r0.z(r3, r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void l(android.content.Context r9, android.content.res.Resources r10, org.xmlpull.v1.XmlPullParser r11, android.util.AttributeSet r12, android.content.res.Resources.Theme r13) {
        /*
            r8 = this;
            androidx.appcompat.graphics.drawable.b$a r0 = r8.m
            int r1 = r11.getDepth()
            r2 = 1
            int r1 = r1 + r2
        L8:
            int r3 = r11.next()
            if (r3 == r2) goto L84
            int r4 = r11.getDepth()
            if (r4 >= r1) goto L17
            r5 = 3
            if (r3 == r5) goto L84
        L17:
            r5 = 2
            if (r3 == r5) goto L1b
            goto L8
        L1b:
            if (r4 > r1) goto L8
            java.lang.String r3 = r11.getName()
            java.lang.String r4 = "item"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L2a
            goto L8
        L2a:
            int[] r3 = androidx.appcompat.resources.R.styleable.StateListDrawableItem
            android.content.res.TypedArray r3 = androidx.core.content.res.TypedArrayUtils.obtainAttributes(r10, r13, r12, r3)
            r4 = 0
            int r6 = androidx.appcompat.resources.R.styleable.StateListDrawableItem_android_drawable
            r7 = -1
            int r6 = r3.getResourceId(r6, r7)
            if (r6 <= 0) goto L42
            androidx.appcompat.widget.ResourceManagerInternal r4 = androidx.appcompat.widget.ResourceManagerInternal.get()
            android.graphics.drawable.Drawable r4 = r4.getDrawable(r9, r6)
        L42:
            r3.recycle()
            int[] r3 = r8.k(r12)
            if (r4 != 0) goto L80
        L4b:
            int r4 = r11.next()
            r6 = 4
            if (r4 != r6) goto L53
            goto L4b
        L53:
            if (r4 != r5) goto L65
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 21
            if (r4 < r5) goto L60
            android.graphics.drawable.Drawable r4 = android.graphics.drawable.Drawable.createFromXmlInner(r10, r11, r12, r13)
            goto L80
        L60:
            android.graphics.drawable.Drawable r4 = android.graphics.drawable.Drawable.createFromXmlInner(r10, r11, r12)
            goto L80
        L65:
            org.xmlpull.v1.XmlPullParserException r9 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = r11.getPositionDescription()
            r10.append(r11)
            java.lang.String r11 = ": <item> tag requires a 'drawable' attribute or child tag defining a drawable"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L80:
            r0.z(r3, r4)
            goto L8
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.graphics.drawable.b.l(android.content.Context, android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):void");
    }

    private void m(TypedArray typedArray) {
        a aVar = this.m;
        if (Build.VERSION.SDK_INT >= 21) {
            aVar.f74d |= typedArray.getChangingConfigurations();
        }
        aVar.f75i = typedArray.getBoolean(R.styleable.StateListDrawable_android_variablePadding, aVar.f75i);
        aVar.l = typedArray.getBoolean(R.styleable.StateListDrawable_android_constantSize, aVar.l);
        aVar.A = typedArray.getInt(R.styleable.StateListDrawable_android_enterFadeDuration, aVar.A);
        aVar.B = typedArray.getInt(R.styleable.StateListDrawable_android_exitFadeDuration, aVar.B);
        aVar.x = typedArray.getBoolean(R.styleable.StateListDrawable_android_dither, aVar.x);
    }

    public void addState(int[] iArr, Drawable drawable) {
        if (drawable != null) {
            this.m.z(iArr, drawable);
            onStateChange(getState());
        }
    }

    @Override // androidx.appcompat.graphics.drawable.a, android.graphics.drawable.Drawable
    @RequiresApi(21)
    public void applyTheme(@NonNull Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.graphics.drawable.a
    public void h(@NonNull a.d dVar) {
        super.h(dVar);
        if (dVar instanceof a) {
            this.m = (a) dVar;
        }
    }

    public void inflate(@NonNull Context context, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) {
        TypedArray obtainAttributes = TypedArrayUtils.obtainAttributes(resources, theme, attributeSet, R.styleable.StateListDrawable);
        setVisible(obtainAttributes.getBoolean(R.styleable.StateListDrawable_android_visible, true), true);
        m(obtainAttributes);
        i(resources);
        obtainAttributes.recycle();
        l(context, resources, xmlPullParser, attributeSet, theme);
        onStateChange(getState());
    }

    @Override // androidx.appcompat.graphics.drawable.a, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.graphics.drawable.a
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public a b() {
        return new a(this.m, this, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] k(AttributeSet attributeSet) {
        int attributeCount = attributeSet.getAttributeCount();
        int[] iArr = new int[attributeCount];
        int i2 = 0;
        for (int i3 = 0; i3 < attributeCount; i3++) {
            int attributeNameResource = attributeSet.getAttributeNameResource(i3);
            if (attributeNameResource != 0 && attributeNameResource != 16842960 && attributeNameResource != 16843161) {
                int i4 = i2 + 1;
                if (!attributeSet.getAttributeBooleanValue(i3, false)) {
                    attributeNameResource = -attributeNameResource;
                }
                iArr[i2] = attributeNameResource;
                i2 = i4;
            }
        }
        return StateSet.trimStateSet(iArr, i2);
    }

    @Override // androidx.appcompat.graphics.drawable.a, android.graphics.drawable.Drawable
    @NonNull
    public Drawable mutate() {
        if (!this.n && super.mutate() == this) {
            this.m.r();
            this.n = true;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.graphics.drawable.a, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean onStateChange = super.onStateChange(iArr);
        int A = this.m.A(iArr);
        if (A < 0) {
            A = this.m.A(StateSet.WILD_CARD);
        }
        return g(A) || onStateChange;
    }
}
