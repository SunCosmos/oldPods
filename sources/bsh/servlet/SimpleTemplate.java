package bsh.servlet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class SimpleTemplate {
    static String NO_TEMPLATE = "NO_TEMPLATE";
    StringBuffer buff;
    static Map templateData = new HashMap();
    static boolean cacheTemplates = true;

    public SimpleTemplate(Reader reader) {
        init(getStringFromStream(reader));
    }

    public SimpleTemplate(String str) {
        init(str);
    }

    public SimpleTemplate(URL url) {
        init(getStringFromStream(url.openStream()));
    }

    public static String getStringFromStream(InputStream inputStream) {
        return getStringFromStream(new InputStreamReader(inputStream));
    }

    public static String getStringFromStream(Reader reader) {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(reader);
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return stringBuffer.toString();
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(readLine);
            stringBuffer2.append("\n");
            stringBuffer.append(stringBuffer2.toString());
        }
    }

    public static SimpleTemplate getTemplate(String str) {
        String str2 = (String) templateData.get(str);
        if (str2 == null || !cacheTemplates) {
            try {
                str2 = getStringFromStream(new FileReader(str));
                templateData.put(str, str2);
            } catch (IOException unused) {
                templateData.put(str, NO_TEMPLATE);
            }
        } else if (str2.equals(NO_TEMPLATE)) {
            return null;
        }
        if (str2 == null) {
            return null;
        }
        return new SimpleTemplate(str2);
    }

    private void init(String str) {
        this.buff = new StringBuffer(str);
    }

    public static void main(String[] strArr) {
        String str = strArr[0];
        String str2 = strArr[1];
        String str3 = strArr[2];
        SimpleTemplate simpleTemplate = new SimpleTemplate(getStringFromStream(new FileReader(str)));
        simpleTemplate.replace(str2, str3);
        simpleTemplate.write(System.out);
    }

    public static void setCacheTemplates(boolean z) {
        cacheTemplates = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x004d, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    int[] findTemplate(java.lang.String r11) {
        /*
            r10 = this;
            java.lang.StringBuffer r0 = r10.buff
            java.lang.String r0 = r0.toString()
            int r1 = r0.length()
            r2 = 0
            r3 = 0
        Lc:
            r4 = 0
            if (r3 < r1) goto L10
            return r4
        L10:
            java.lang.String r5 = "<!--"
            int r3 = r0.indexOf(r5, r3)
            r5 = -1
            if (r3 != r5) goto L1a
            return r4
        L1a:
            java.lang.String r6 = "-->"
            int r6 = r0.indexOf(r6, r3)
            if (r6 != r5) goto L23
            return r4
        L23:
            int r6 = r6 + 3
            java.lang.String r7 = "TEMPLATE-"
            int r7 = r0.indexOf(r7, r3)
            if (r7 != r5) goto L2e
            goto L61
        L2e:
            if (r7 <= r6) goto L31
            goto L61
        L31:
            int r7 = r7 + 9
            r5 = r7
        L34:
            if (r5 < r1) goto L37
            goto L4b
        L37:
            char r8 = r0.charAt(r5)
            r9 = 32
            if (r8 == r9) goto L4b
            r9 = 9
            if (r8 == r9) goto L4b
            r9 = 45
            if (r8 != r9) goto L48
            goto L4b
        L48:
            int r5 = r5 + 1
            goto L34
        L4b:
            if (r5 < r1) goto L4e
            return r4
        L4e:
            java.lang.String r4 = r0.substring(r7, r5)
            boolean r4 = r4.equals(r11)
            if (r4 == 0) goto L61
            r11 = 2
            int[] r11 = new int[r11]
            r11[r2] = r3
            r0 = 1
            r11[r0] = r6
            return r11
        L61:
            r3 = r6
            goto Lc
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.servlet.SimpleTemplate.findTemplate(java.lang.String):int[]");
    }

    public void replace(String str, String str2) {
        while (true) {
            int[] findTemplate = findTemplate(str);
            if (findTemplate == null) {
                return;
            } else {
                this.buff.replace(findTemplate[0], findTemplate[1], str2);
            }
        }
    }

    public String toString() {
        return this.buff.toString();
    }

    public void write(PrintStream printStream) {
        printStream.println(toString());
    }

    public void write(PrintWriter printWriter) {
        printWriter.println(toString());
    }
}
