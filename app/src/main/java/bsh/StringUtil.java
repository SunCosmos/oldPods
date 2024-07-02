package bsh;

import java.util.StringTokenizer;
import java.util.Vector;

/* loaded from: classes.dex */
public class StringUtil {
    public static String[] bubbleSort(String[] strArr) {
        Vector vector = new Vector();
        for (String str : strArr) {
            vector.addElement(str);
        }
        int size = vector.size();
        boolean z = true;
        while (z) {
            z = false;
            int i2 = 0;
            while (i2 < size - 1) {
                int i3 = i2 + 1;
                if (((String) vector.elementAt(i2)).compareTo((String) vector.elementAt(i3)) > 0) {
                    String str2 = (String) vector.elementAt(i3);
                    vector.removeElementAt(i3);
                    vector.insertElementAt(str2, i2);
                    z = true;
                }
                i2 = i3;
            }
        }
        String[] strArr2 = new String[size];
        vector.copyInto(strArr2);
        return strArr2;
    }

    public static String maxCommonPrefix(String str, String str2) {
        int i2 = 0;
        while (str.regionMatches(0, str2, 0, i2)) {
            i2++;
        }
        return str.substring(0, i2 - 1);
    }

    public static String methodString(String str, Class[] clsArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append("(");
        StringBuffer stringBuffer2 = new StringBuffer(stringBuffer.toString());
        if (clsArr.length > 0) {
            stringBuffer2.append(" ");
        }
        int i2 = 0;
        while (i2 < clsArr.length) {
            Class cls = clsArr[i2];
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(cls == null ? "null" : cls.getName());
            stringBuffer3.append(i2 < clsArr.length + (-1) ? ", " : " ");
            stringBuffer2.append(stringBuffer3.toString());
            i2++;
        }
        stringBuffer2.append(")");
        return stringBuffer2.toString();
    }

    public static String normalizeClassName(Class cls) {
        return Reflect.normalizeClassName(cls);
    }

    public static String[] split(String str, String str2) {
        Vector vector = new Vector();
        StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(stringTokenizer.nextToken());
        }
        String[] strArr = new String[vector.size()];
        vector.copyInto(strArr);
        return strArr;
    }
}
