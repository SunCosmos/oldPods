package c.b.a.a;

import java.io.CharArrayWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.BitSet;

/* loaded from: classes.dex */
public class s {
    static BitSet a = new BitSet(256);
    static String b;

    static {
        for (int i2 = 97; i2 <= 122; i2++) {
            a.set(i2);
        }
        for (int i3 = 65; i3 <= 90; i3++) {
            a.set(i3);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            a.set(i4);
        }
        a.set(32);
        a.set(45);
        a.set(95);
        a.set(46);
        a.set(42);
        b = "utf-8";
    }

    @Deprecated
    public static String a(String str) {
        try {
            return b(str, b);
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static String b(String str, String str2) {
        BitSet bitSet;
        int i2;
        char charAt;
        StringBuffer stringBuffer = new StringBuffer(str.length());
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        if (str2 == null) {
            throw new NullPointerException("charsetName");
        }
        try {
            Charset forName = Charset.forName(str2);
            int i3 = 0;
            boolean z = false;
            while (i3 < str.length()) {
                char charAt2 = str.charAt(i3);
                if (a.get(charAt2)) {
                    if (charAt2 == ' ') {
                        stringBuffer.append("%20");
                        z = true;
                    } else {
                        stringBuffer.append(charAt2);
                    }
                    i3++;
                } else {
                    do {
                        charArrayWriter.write(charAt2);
                        if (charAt2 >= 55296 && charAt2 <= 56319 && (i2 = i3 + 1) < str.length() && (charAt = str.charAt(i2)) >= 56320 && charAt <= 57343) {
                            charArrayWriter.write(charAt);
                            i3 = i2;
                        }
                        i3++;
                        if (i3 >= str.length()) {
                            break;
                        }
                        bitSet = a;
                        charAt2 = str.charAt(i3);
                    } while (!bitSet.get(charAt2));
                    charArrayWriter.flush();
                    byte[] bytes = new String(charArrayWriter.toCharArray()).getBytes(forName);
                    for (int i4 = 0; i4 < bytes.length; i4++) {
                        stringBuffer.append('%');
                        char forDigit = Character.forDigit((bytes[i4] >> 4) & 15, 16);
                        if (Character.isLetter(forDigit)) {
                            forDigit = (char) (forDigit - ' ');
                        }
                        stringBuffer.append(forDigit);
                        char forDigit2 = Character.forDigit(bytes[i4] & 15, 16);
                        if (Character.isLetter(forDigit2)) {
                            forDigit2 = (char) (forDigit2 - ' ');
                        }
                        stringBuffer.append(forDigit2);
                    }
                    charArrayWriter.reset();
                    z = true;
                }
            }
            return z ? stringBuffer.toString() : str;
        } catch (IllegalCharsetNameException unused) {
            throw new UnsupportedEncodingException(str2);
        } catch (UnsupportedCharsetException unused2) {
            throw new UnsupportedEncodingException(str2);
        }
    }
}
