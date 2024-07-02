package android.support.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: lib/Mus.dex */
public class AnimationUtilsCompat {
    public static Interpolator loadInterpolator(Context context, int i2) throws Resources.NotFoundException {
        Interpolator interpolator;
        if (Build.VERSION.SDK_INT >= 21) {
            return AnimationUtils.loadInterpolator(context, i2);
        }
        XmlResourceParser xmlResourceParser = null;
        try {
            try {
                try {
                    if (i2 == 17563663) {
                        FastOutLinearInInterpolator fastOutLinearInInterpolator = new FastOutLinearInInterpolator();
                        if (0 != 0) {
                            xmlResourceParser.close();
                        }
                        interpolator = fastOutLinearInInterpolator;
                    } else if (i2 == 17563661) {
                        FastOutSlowInInterpolator fastOutSlowInInterpolator = new FastOutSlowInInterpolator();
                        if (0 != 0) {
                            xmlResourceParser.close();
                        }
                        interpolator = fastOutSlowInInterpolator;
                    } else if (i2 == 17563662) {
                        LinearOutSlowInInterpolator linearOutSlowInInterpolator = new LinearOutSlowInInterpolator();
                        if (0 != 0) {
                            xmlResourceParser.close();
                        }
                        interpolator = linearOutSlowInInterpolator;
                    } else {
                        xmlResourceParser = context.getResources().getAnimation(i2);
                        Interpolator createInterpolatorFromXml = createInterpolatorFromXml(context, context.getResources(), context.getTheme(), xmlResourceParser);
                        if (xmlResourceParser != null) {
                            xmlResourceParser.close();
                        }
                        interpolator = createInterpolatorFromXml;
                    }
                    return interpolator;
                } catch (IOException e) {
                    Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i2));
                    notFoundException.initCause(e);
                    throw notFoundException;
                }
            } catch (XmlPullParserException e2) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i2));
                notFoundException2.initCause(e2);
                throw notFoundException2;
            }
        } catch (Throwable th) {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x012e, code lost:
    
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.view.animation.Interpolator createInterpolatorFromXml(android.content.Context r15, android.content.res.Resources r16, android.content.res.Resources.Theme r17, org.xmlpull.v1.XmlPullParser r18) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.AnimationUtilsCompat.createInterpolatorFromXml(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser):android.view.animation.Interpolator");
    }
}
