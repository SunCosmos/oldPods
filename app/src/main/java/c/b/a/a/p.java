package c.b.a.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.util.TypedValue;
import android.view.View;
import bsh.org.objectweb.asm.Constants;
import com.oldpods.app.R;
import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes.dex */
public class p {
    private static int a = -3;
    private static String b;

    /* renamed from: c */
    private static final String[][] f1357c = {new String[]{".3gp", "video/3gpp"}, new String[]{".apk", "application/vnd.android.package-archive"}, new String[]{".asf", "video/x-ms-asf"}, new String[]{".flv", "video/x-flv"}, new String[]{".rar", "application/x-rar"}, new String[]{".avi", "video/x-msvideo"}, new String[]{".ico", "image/x-ico"}, new String[]{".bin", "application/octet-stream"}, new String[]{".bmp", "image/bmp"}, new String[]{".c", "text/plain"}, new String[]{".class", "application/octet-stream"}, new String[]{".conf", "text/plain"}, new String[]{".cpp", "text/plain"}, new String[]{".doc", "application/msword"}, new String[]{".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"}, new String[]{".xls", "application/vnd.ms-excel"}, new String[]{".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"}, new String[]{".exe", "application/octet-stream"}, new String[]{".gif", "image/gif"}, new String[]{".gtar", "application/x-gtar"}, new String[]{".gz", "application/x-gzip"}, new String[]{".h", "text/plain"}, new String[]{".htm", "text/html"}, new String[]{".html", "text/html"}, new String[]{".jar", "application/java-archive"}, new String[]{".java", "text/plain"}, new String[]{".jpeg", "image/jpeg"}, new String[]{".jpg", "image/jpeg"}, new String[]{".js", "application/x-javascript"}, new String[]{".log", "text/plain"}, new String[]{".m3u", "audio/x-mpegurl"}, new String[]{".m4a", "audio/mp4a-latm"}, new String[]{".m4b", "audio/mp4a-latm"}, new String[]{".m4p", "audio/mp4a-latm"}, new String[]{".m4u", "video/vnd.mpegurl"}, new String[]{".m4v", "video/x-m4v"}, new String[]{".mov", "video/quicktime"}, new String[]{".mp2", "audio/x-mpeg"}, new String[]{".mp3", "audio/x-mpeg"}, new String[]{".mp4", "video/mp4"}, new String[]{".mpc", "application/vnd.mpohun.certificate"}, new String[]{".mpe", "video/mpeg"}, new String[]{".mpeg", "video/mpeg"}, new String[]{".mpg", "video/mpeg"}, new String[]{".mpg4", "video/mp4"}, new String[]{".mpga", "audio/mpeg"}, new String[]{".msg", "application/vnd.ms-outlook"}, new String[]{".ogg", "audio/ogg"}, new String[]{".pdf", "application/pdf"}, new String[]{".png", "image/png"}, new String[]{".pps", "application/vnd.ms-powerpoint"}, new String[]{".ppt", "application/vnd.ms-powerpoint"}, new String[]{".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"}, new String[]{".prop", "text/plain"}, new String[]{".rc", "text/plain"}, new String[]{".rmvb", "audio/x-pn-realaudio"}, new String[]{".rtf", "application/rtf"}, new String[]{".sh", "text/plain"}, new String[]{".tar", "application/x-tar"}, new String[]{".tgz", "application/x-compressed"}, new String[]{".txt", "text/plain"}, new String[]{".wav", "audio/x-wav"}, new String[]{".wma", "audio/x-ms-wma"}, new String[]{".wmv", "audio/x-ms-wmv"}, new String[]{".wps", "application/vnd.ms-works"}, new String[]{".xml", "text/plain"}, new String[]{".z", "application/x-compress"}, new String[]{".zip", "application/x-zip-compressed"}, new String[]{"", "*/*"}};

    public static int A(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static String B(String str, String str2) {
        byte[] bArr;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '%') {
                stringBuffer.append("%25");
            } else if (charAt < 0 || charAt > 255) {
                try {
                    bArr = String.valueOf(charAt).getBytes(str2);
                } catch (Exception unused) {
                    bArr = new byte[0];
                }
                for (int i3 : bArr) {
                    if (i3 < 0) {
                        i3 += 256;
                    }
                    stringBuffer.append("%" + Integer.toHexString(i3).toUpperCase());
                }
            } else {
                stringBuffer.append(charAt);
            }
        }
        return stringBuffer.toString();
    }

    public static String C(String str, String str2) {
        try {
            return URLEncoder.encode(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String D(String str) {
        char c2;
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        int i2 = -1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < length) {
            int charAt = str.charAt(i3);
            if (charAt == 37) {
                int i5 = i3 + 1;
                char charAt2 = str.charAt(i5);
                int lowerCase = (Character.isDigit(charAt2) ? charAt2 - '0' : (Character.toLowerCase(r6) + '\n') - 97) & 15;
                i3 = i5 + 1;
                char charAt3 = str.charAt(i3);
                charAt = (lowerCase << 4) | ((Character.isDigit(charAt3) ? charAt3 - '0' : (Character.toLowerCase(r8) + '\n') - 97) & 15);
            } else if (charAt == 43) {
                charAt = 32;
            }
            if ((charAt & Constants.CHECKCAST) == 128) {
                i4 = (i4 << 6) | (charAt & 63);
                i2--;
                if (i2 == 0) {
                    c2 = (char) i4;
                    stringBuffer.append(c2);
                    i3++;
                } else {
                    i3++;
                }
            } else if ((charAt & 128) == 0) {
                c2 = (char) charAt;
                stringBuffer.append(c2);
                i3++;
            } else {
                if ((charAt & 224) == 192) {
                    i4 = charAt & 31;
                    i2 = 1;
                } else if ((charAt & 240) == 224) {
                    i4 = charAt & 15;
                    i2 = 2;
                } else if ((charAt & 248) == 240) {
                    i4 = charAt & 7;
                    i2 = 3;
                } else if ((charAt & 252) == 248) {
                    i4 = charAt & 3;
                    i2 = 4;
                } else {
                    i4 = charAt & 1;
                    i2 = 5;
                }
                i3++;
            }
        }
        return stringBuffer.toString();
    }

    public static String E(String str, String str2) {
        try {
            return URLDecoder.decode(str, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return str.substring((lastIndexOf == -1 && (lastIndexOf = str.replace('\\', '/').lastIndexOf(47)) == -1) ? str.length() - 6 : lastIndexOf + 1);
    }

    public static String b(String str) {
        return str.substring(str.replace('\\', '/').lastIndexOf(47) + 1);
    }

    public static String c(String str, String str2, String str3) {
        int i2;
        int indexOf;
        if (str2 != null) {
            int indexOf2 = str.indexOf(str2);
            if (indexOf2 == -1) {
                return null;
            }
            i2 = indexOf2 + str2.length();
        } else {
            i2 = 0;
        }
        if (str3 == null) {
            indexOf = str.length();
        } else {
            indexOf = str.indexOf(str3, i2);
            if (indexOf == -1) {
                return null;
            }
        }
        return str.substring(i2, indexOf);
    }

    public static String d(String str) {
        return e(str.getBytes());
    }

    public static String e(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            char[] cArr2 = new char[digest.length * 2];
            int i2 = 0;
            for (byte b2 : digest) {
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b2 >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b2 & 15];
            }
            return new String(cArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String f(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            char[] cArr2 = new char[bArr.length * 2];
            int i2 = 0;
            for (byte b2 : bArr) {
                int i3 = i2 + 1;
                cArr2[i2] = cArr[(b2 >>> 4) & 15];
                i2 = i3 + 1;
                cArr2[i3] = cArr[b2 & 15];
            }
            return new String(cArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int g(int i2, int i3) {
        double random = Math.random();
        double d2 = (i3 + 1) - i2;
        Double.isNaN(d2);
        double d3 = i2;
        Double.isNaN(d3);
        return (int) ((random * d2) + d3);
    }

    public static String h(String str, String str2) {
        String c2 = c(str, "<" + str2 + ">", "</" + str2 + ">");
        return c2 == null ? "" : c2;
    }

    public static int i(String str, int i2) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return i2;
        }
    }

    public static String j(String str, String str2) {
        return str + str2;
    }

    public static void k(String str, Context context) {
        ((ClipboardManager) context.getSystemService("clipboard")).setText(str.trim());
    }

    public static int l(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static View m(int i2, View view) {
        return view.findViewById(i2);
    }

    public static void n(String str, String str2) {
        t.l.put(str, str2.replace("&lt;", "<").replace("&gt;", ">"));
    }

    public static int o(String str) {
        return str.startsWith("#") ? Color.parseColor(str) : Integer.parseInt(str);
    }

    public static String p(File file) {
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                String probeContentType = Files.probeContentType(Paths.get(file.toURI()));
                if (probeContentType != null) {
                    return probeContentType;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return r(file);
    }

    @SuppressLint({"HardwareIds"})
    public static String q(Context context) {
        String str = b;
        if (str != null && str.length() > 0) {
            return b;
        }
        try {
            b = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Throwable unused) {
        }
        String str2 = b;
        if (str2 == null || str2.length() == 0) {
            try {
                b = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), "android_id");
            } catch (Throwable unused2) {
            }
            String str3 = b;
            if (str3 == null || str3.length() == 0) {
                File file = new File(String.format("%s/.UUID.User", context.getFilesDir()));
                String h = file.exists() ? d.h(file.getAbsolutePath()) : null;
                if (h == null || h.length() == 0) {
                    h = d(UUID.randomUUID().toString()).substring(16);
                    d.k(file.getAbsolutePath(), h);
                }
                b = h;
            }
        }
        return b;
    }

    private static String r(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf < 0) {
            return "*/*";
        }
        String lowerCase = name.substring(lastIndexOf, name.length()).toLowerCase();
        if (lowerCase.length() == 0) {
            return "*/*";
        }
        int i2 = 0;
        while (true) {
            String[][] strArr = f1357c;
            if (i2 >= strArr.length) {
                return "*/*";
            }
            if (lowerCase.equals(strArr[i2][0])) {
                return strArr[i2][1];
            }
            i2++;
        }
    }

    public static int s(Context context) {
        if (a == -3) {
            a = -2;
            TypedValue typedValue = new TypedValue();
            if (context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
                a = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
            }
        }
        return a;
    }

    public static byte t(byte[] bArr) {
        int i2 = 0;
        for (char c2 : String.valueOf(bArr.length).toCharArray()) {
            i2 += Integer.parseInt(String.valueOf(c2));
        }
        return (byte) i2;
    }

    public static String u(int i2) {
        Formatter format;
        Calendar calendar = Calendar.getInstance();
        long time = new Date().getTime();
        calendar.setTimeInMillis(time);
        Formatter formatter = new Formatter(Locale.CHINA);
        if (i2 == 0) {
            format = formatter.format("%1$tY-%1$tm-%1$td %1$tT", calendar);
        } else if (i2 == 1) {
            format = formatter.format("%1$tY/%1$tm/%1$td %1$tT", calendar);
        } else if (i2 == 2) {
            format = formatter.format("%1$tY-%1$tm-%1$td", calendar);
        } else if (i2 == 3) {
            format = formatter.format("%1$tT", calendar);
        } else {
            if (i2 == 4) {
                return String.valueOf(time);
            }
            format = formatter.format("%1$tY年%1$tm月%1$td日 %1$tT", calendar);
        }
        return format.toString();
    }

    public static boolean v(String str) {
        return str.startsWith("http:") || str.startsWith("https:") || str.startsWith("rtsp:") || str.startsWith("ftp:");
    }

    public static int w(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int x(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static void y(View view, View view2) {
    }

    public static boolean z(Context context) {
        return String.valueOf(d.f(context, "extra_conf1g.xml")).contains("<signature>1</signature>");
    }
}
