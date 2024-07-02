package android.support.v4.text.util;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.PatternsCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.core.net.MailTo;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: lib/Mus.dex */
public final class LinkifyCompat {
    private static final String[] EMPTY_STRING = new String[0];
    private static final Comparator<LinkSpec> COMPARATOR = new Comparator<LinkSpec>() { // from class: android.support.v4.text.util.LinkifyCompat.1
        @Override // java.util.Comparator
        public int compare(LinkSpec linkSpec, LinkSpec linkSpec2) {
            if (linkSpec.start < linkSpec2.start) {
                return -1;
            }
            if (linkSpec.start <= linkSpec2.start && linkSpec.end >= linkSpec2.end) {
                if (linkSpec.end > linkSpec2.end) {
                    return -1;
                }
                return 0;
            }
            return 1;
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public @interface LinkifyMask {
    }

    public static boolean addLinks(@NonNull Spannable spannable, int i2) {
        if (Build.VERSION.SDK_INT >= 27) {
            return Linkify.addLinks(spannable, i2);
        }
        if (i2 == 0) {
            return false;
        }
        URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class);
        for (int length = uRLSpanArr.length - 1; length >= 0; length--) {
            spannable.removeSpan(uRLSpanArr[length]);
        }
        if ((i2 & 4) != 0) {
            Linkify.addLinks(spannable, 4);
        }
        ArrayList arrayList = new ArrayList();
        if ((i2 & 1) != 0) {
            gatherLinks(arrayList, spannable, PatternsCompat.AUTOLINK_WEB_URL, new String[]{"http://", "https://", "rtsp://"}, Linkify.sUrlMatchFilter, null);
        }
        if ((i2 & 2) != 0) {
            gatherLinks(arrayList, spannable, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[]{MailTo.MAILTO_SCHEME}, null, null);
        }
        if ((i2 & 8) != 0) {
            gatherMapLinks(arrayList, spannable);
        }
        pruneOverlaps(arrayList, spannable);
        if (arrayList.size() == 0) {
            return false;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            LinkSpec linkSpec = (LinkSpec) it.next();
            if (linkSpec.frameworkAddedSpan == null) {
                applyLink(linkSpec.url, linkSpec.start, linkSpec.end, spannable);
            }
        }
        return true;
    }

    public static boolean addLinks(@NonNull TextView textView, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            return Linkify.addLinks(textView, i2);
        }
        if (i2 == 0) {
            return false;
        }
        CharSequence text = textView.getText();
        if (text instanceof Spannable) {
            if (addLinks((Spannable) text, i2)) {
                addLinkMovementMethod(textView);
                return true;
            }
            return false;
        }
        SpannableString valueOf = SpannableString.valueOf(text);
        if (addLinks(valueOf, i2)) {
            addLinkMovementMethod(textView);
            textView.setText(valueOf);
            return true;
        }
        return false;
    }

    public static void addLinks(@NonNull TextView textView, @NonNull Pattern pattern, @Nullable String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            Linkify.addLinks(textView, pattern, str);
        } else {
            addLinks(textView, pattern, str, (String[]) null, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
        }
    }

    public static void addLinks(@NonNull TextView textView, @NonNull Pattern pattern, @Nullable String str, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        if (Build.VERSION.SDK_INT >= 26) {
            Linkify.addLinks(textView, pattern, str, matchFilter, transformFilter);
        } else {
            addLinks(textView, pattern, str, (String[]) null, matchFilter, transformFilter);
        }
    }

    public static void addLinks(@NonNull TextView textView, @NonNull Pattern pattern, @Nullable String str, @Nullable String[] strArr, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        if (Build.VERSION.SDK_INT >= 26) {
            Linkify.addLinks(textView, pattern, str, strArr, matchFilter, transformFilter);
            return;
        }
        SpannableString valueOf = SpannableString.valueOf(textView.getText());
        if (addLinks(valueOf, pattern, str, strArr, matchFilter, transformFilter)) {
            textView.setText(valueOf);
            addLinkMovementMethod(textView);
        }
    }

    public static boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String str) {
        if (Build.VERSION.SDK_INT < 26) {
            return addLinks(spannable, pattern, str, (String[]) null, (Linkify.MatchFilter) null, (Linkify.TransformFilter) null);
        }
        return Linkify.addLinks(spannable, pattern, str);
    }

    public static boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String str, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        if (Build.VERSION.SDK_INT < 26) {
            return addLinks(spannable, pattern, str, (String[]) null, matchFilter, transformFilter);
        }
        return Linkify.addLinks(spannable, pattern, str, matchFilter, transformFilter);
    }

    public static boolean addLinks(@NonNull Spannable spannable, @NonNull Pattern pattern, @Nullable String str, @Nullable String[] strArr, @Nullable Linkify.MatchFilter matchFilter, @Nullable Linkify.TransformFilter transformFilter) {
        String str2 = str;
        String[] strArr2 = strArr;
        if (Build.VERSION.SDK_INT >= 26) {
            return Linkify.addLinks(spannable, pattern, str2, strArr2, matchFilter, transformFilter);
        }
        if (str2 == null) {
            str2 = "";
        }
        if (strArr2 == null || strArr2.length < 1) {
            strArr2 = EMPTY_STRING;
        }
        String[] strArr3 = new String[strArr2.length + 1];
        strArr3[0] = str2.toLowerCase(Locale.ROOT);
        for (int i2 = 0; i2 < strArr2.length; i2++) {
            String str3 = strArr2[i2];
            strArr3[i2 + 1] = str3 == null ? "" : str3.toLowerCase(Locale.ROOT);
        }
        boolean z = false;
        Matcher matcher = pattern.matcher(spannable);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            boolean z2 = true;
            if (matchFilter != null) {
                z2 = matchFilter.acceptMatch(spannable, start, end);
            }
            if (z2) {
                applyLink(makeUrl(matcher.group(0), strArr3, matcher, transformFilter), start, end, spannable);
                z = true;
            }
        }
        return z;
    }

    private static void addLinkMovementMethod(@NonNull TextView textView) {
        MovementMethod movementMethod = textView.getMovementMethod();
        if ((movementMethod == null || !(movementMethod instanceof LinkMovementMethod)) && textView.getLinksClickable()) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private static String makeUrl(@NonNull String str, @NonNull String[] strArr, Matcher matcher, @Nullable Linkify.TransformFilter transformFilter) {
        String str2 = str;
        if (transformFilter != null) {
            str2 = transformFilter.transformUrl(matcher, str2);
        }
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= strArr.length) {
                break;
            }
            if (!str2.regionMatches(true, 0, strArr[i2], 0, strArr[i2].length())) {
                i2++;
            } else {
                z = true;
                if (!str2.regionMatches(false, 0, strArr[i2], 0, strArr[i2].length())) {
                    str2 = strArr[i2] + str2.substring(strArr[i2].length());
                }
            }
        }
        if (!z && strArr.length > 0) {
            str2 = strArr[0] + str2;
        }
        return str2;
    }

    private static void gatherLinks(ArrayList<LinkSpec> arrayList, Spannable spannable, Pattern pattern, String[] strArr, Linkify.MatchFilter matchFilter, Linkify.TransformFilter transformFilter) {
        Matcher matcher = pattern.matcher(spannable);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (matchFilter == null || matchFilter.acceptMatch(spannable, start, end)) {
                LinkSpec linkSpec = new LinkSpec();
                linkSpec.url = makeUrl(matcher.group(0), strArr, matcher, transformFilter);
                linkSpec.start = start;
                linkSpec.end = end;
                arrayList.add(linkSpec);
            }
        }
    }

    private static void applyLink(String str, int i2, int i3, Spannable spannable) {
        spannable.setSpan(new URLSpan(str), i2, i3, 33);
    }

    private static void gatherMapLinks(ArrayList<LinkSpec> arrayList, Spannable spannable) {
        int indexOf;
        String obj = spannable.toString();
        int i2 = 0;
        while (true) {
            try {
                String findAddress = WebView.findAddress(obj);
                if (findAddress != null && (indexOf = obj.indexOf(findAddress)) >= 0) {
                    LinkSpec linkSpec = new LinkSpec();
                    int length = indexOf + findAddress.length();
                    linkSpec.start = i2 + indexOf;
                    linkSpec.end = i2 + length;
                    obj = obj.substring(length);
                    i2 += length;
                    try {
                        linkSpec.url = "geo:0,0?q=" + URLEncoder.encode(findAddress, "UTF-8");
                        arrayList.add(linkSpec);
                    } catch (UnsupportedEncodingException e) {
                    }
                } else {
                    return;
                }
            } catch (UnsupportedOperationException e2) {
                return;
            }
        }
    }

    private static void pruneOverlaps(ArrayList<LinkSpec> arrayList, Spannable spannable) {
        URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class);
        for (int i2 = 0; i2 < uRLSpanArr.length; i2++) {
            LinkSpec linkSpec = new LinkSpec();
            linkSpec.frameworkAddedSpan = uRLSpanArr[i2];
            linkSpec.start = spannable.getSpanStart(uRLSpanArr[i2]);
            linkSpec.end = spannable.getSpanEnd(uRLSpanArr[i2]);
            arrayList.add(linkSpec);
        }
        Collections.sort(arrayList, COMPARATOR);
        int size = arrayList.size();
        int i3 = 0;
        while (i3 < size - 1) {
            LinkSpec linkSpec2 = arrayList.get(i3);
            LinkSpec linkSpec3 = arrayList.get(i3 + 1);
            int i4 = -1;
            if (linkSpec2.start <= linkSpec3.start && linkSpec2.end > linkSpec3.start) {
                if (linkSpec3.end <= linkSpec2.end) {
                    i4 = i3 + 1;
                } else if (linkSpec2.end - linkSpec2.start > linkSpec3.end - linkSpec3.start) {
                    i4 = i3 + 1;
                } else if (linkSpec2.end - linkSpec2.start < linkSpec3.end - linkSpec3.start) {
                    i4 = i3;
                }
                if (i4 != -1) {
                    Object obj = arrayList.get(i4).frameworkAddedSpan;
                    if (obj != null) {
                        spannable.removeSpan(obj);
                    }
                    arrayList.remove(i4);
                    size--;
                }
            }
            i3++;
        }
    }

    private LinkifyCompat() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: lib/Mus.dex */
    public static class LinkSpec {
        int end;
        URLSpan frameworkAddedSpan;
        int start;
        String url;

        LinkSpec() {
        }
    }
}
