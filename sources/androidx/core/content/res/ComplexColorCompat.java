package androidx.core.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public final class ComplexColorCompat {
    private final Shader a;
    private final ColorStateList b;

    /* renamed from: c */
    private int f533c;

    private ComplexColorCompat(Shader shader, ColorStateList colorStateList, @ColorInt int i2) {
        this.a = shader;
        this.b = colorStateList;
        this.f533c = i2;
    }

    @NonNull
    private static ComplexColorCompat a(@NonNull Resources resources, @ColorRes int i2, @Nullable Resources.Theme theme) {
        int next;
        XmlResourceParser xml = resources.getXml(i2);
        AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
        do {
            next = xml.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        String name = xml.getName();
        name.hashCode();
        if (name.equals("gradient")) {
            return d(a.b(resources, xml, asAttributeSet, theme));
        }
        if (name.equals("selector")) {
            return c(ColorStateListInflaterCompat.createFromXmlInner(resources, xml, asAttributeSet, theme));
        }
        throw new XmlPullParserException(xml.getPositionDescription() + ": unsupported complex color tag " + name);
    }

    public static ComplexColorCompat b(@ColorInt int i2) {
        return new ComplexColorCompat(null, null, i2);
    }

    static ComplexColorCompat c(@NonNull ColorStateList colorStateList) {
        return new ComplexColorCompat(null, colorStateList, colorStateList.getDefaultColor());
    }

    static ComplexColorCompat d(@NonNull Shader shader) {
        return new ComplexColorCompat(shader, null, 0);
    }

    @Nullable
    public static ComplexColorCompat inflate(@NonNull Resources resources, @ColorRes int i2, @Nullable Resources.Theme theme) {
        try {
            return a(resources, i2, theme);
        } catch (Exception e) {
            Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e);
            return null;
        }
    }

    @ColorInt
    public int getColor() {
        return this.f533c;
    }

    @Nullable
    public Shader getShader() {
        return this.a;
    }

    public boolean isGradient() {
        return this.a != null;
    }

    public boolean isStateful() {
        ColorStateList colorStateList;
        return this.a == null && (colorStateList = this.b) != null && colorStateList.isStateful();
    }

    public boolean onStateChanged(int[] iArr) {
        if (isStateful()) {
            ColorStateList colorStateList = this.b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != this.f533c) {
                this.f533c = colorForState;
                return true;
            }
        }
        return false;
    }

    public void setColor(@ColorInt int i2) {
        this.f533c = i2;
    }

    public boolean willDraw() {
        return isGradient() || this.f533c != 0;
    }
}
