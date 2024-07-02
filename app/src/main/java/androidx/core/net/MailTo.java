package androidx.core.net;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public final class MailTo {
    public static final String MAILTO_SCHEME = "mailto:";
    private HashMap<String, String> a = new HashMap<>();

    private MailTo() {
    }

    public static boolean isMailTo(@Nullable Uri uri) {
        return uri != null && "mailto".equals(uri.getScheme());
    }

    public static boolean isMailTo(@Nullable String str) {
        return str != null && str.startsWith(MAILTO_SCHEME);
    }

    @NonNull
    public static MailTo parse(@NonNull Uri uri) {
        return parse(uri.toString());
    }

    @NonNull
    public static MailTo parse(@NonNull String str) {
        String decode;
        String substring;
        Preconditions.checkNotNull(str);
        if (!isMailTo(str)) {
            throw new ParseException("Not a mailto scheme");
        }
        int indexOf = str.indexOf(35);
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        int indexOf2 = str.indexOf(63);
        if (indexOf2 == -1) {
            decode = Uri.decode(str.substring(7));
            substring = null;
        } else {
            decode = Uri.decode(str.substring(7, indexOf2));
            substring = str.substring(indexOf2 + 1);
        }
        MailTo mailTo = new MailTo();
        if (substring != null) {
            for (String str2 : substring.split("&")) {
                String[] split = str2.split("=", 2);
                if (split.length != 0) {
                    mailTo.a.put(Uri.decode(split[0]).toLowerCase(Locale.ROOT), split.length > 1 ? Uri.decode(split[1]) : null);
                }
            }
        }
        String to = mailTo.getTo();
        if (to != null) {
            decode = decode + ", " + to;
        }
        mailTo.a.put("to", decode);
        return mailTo;
    }

    @Nullable
    public String getBcc() {
        return this.a.get("bcc");
    }

    @Nullable
    public String getBody() {
        return this.a.get("body");
    }

    @Nullable
    public String getCc() {
        return this.a.get("cc");
    }

    @Nullable
    public Map<String, String> getHeaders() {
        return this.a;
    }

    @Nullable
    public String getSubject() {
        return this.a.get("subject");
    }

    @Nullable
    public String getTo() {
        return this.a.get("to");
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder(MAILTO_SCHEME);
        sb.append('?');
        for (Map.Entry<String, String> entry : this.a.entrySet()) {
            sb.append(Uri.encode(entry.getKey()));
            sb.append('=');
            sb.append(Uri.encode(entry.getValue()));
            sb.append('&');
        }
        return sb.toString();
    }
}
