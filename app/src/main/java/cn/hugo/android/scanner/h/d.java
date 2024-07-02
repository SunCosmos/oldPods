package cn.hugo.android.scanner.h;

import java.util.Collection;
import java.util.EnumSet;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
final class d {
    static final Collection<c.a.a.a> a;
    static final Collection<c.a.a.a> b;

    /* renamed from: c, reason: collision with root package name */
    static final Collection<c.a.a.a> f1439c;

    /* renamed from: d, reason: collision with root package name */
    static final Collection<c.a.a.a> f1440d;

    static {
        Pattern.compile(",");
        f1439c = EnumSet.of(c.a.a.a.QR_CODE);
        f1440d = EnumSet.of(c.a.a.a.DATA_MATRIX);
        EnumSet of = EnumSet.of(c.a.a.a.UPC_A, c.a.a.a.UPC_E, c.a.a.a.EAN_13, c.a.a.a.EAN_8, c.a.a.a.RSS_14, c.a.a.a.RSS_EXPANDED);
        a = of;
        EnumSet of2 = EnumSet.of(c.a.a.a.CODE_39, c.a.a.a.CODE_93, c.a.a.a.CODE_128, c.a.a.a.ITF, c.a.a.a.CODABAR);
        b = of2;
        of2.addAll(of);
    }
}
