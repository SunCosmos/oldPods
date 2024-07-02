package bsh.commands;

import bsh.CallStack;
import bsh.Interpreter;
import bsh.StringUtil;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

/* loaded from: classes.dex */
public class dir {
    static final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public static void invoke(Interpreter interpreter, CallStack callStack) {
        invoke(interpreter, callStack, ".");
    }

    public static void invoke(Interpreter interpreter, CallStack callStack, String str) {
        String str2;
        StringBuffer stringBuffer;
        Object obj;
        File pathToFile;
        try {
            pathToFile = interpreter.pathToFile(str);
        } catch (IOException e) {
            str2 = "error reading path: ";
            obj = e;
            stringBuffer = new StringBuffer();
        }
        if (!pathToFile.exists() || !pathToFile.canRead()) {
            str2 = "Can't read ";
            obj = pathToFile;
            stringBuffer = new StringBuffer();
            stringBuffer.append(str2);
            stringBuffer.append(obj);
            interpreter.println(stringBuffer.toString());
            return;
        }
        if (!pathToFile.isDirectory()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("'");
            stringBuffer2.append(str);
            stringBuffer2.append("' is not a directory");
            interpreter.println(stringBuffer2.toString());
        }
        for (String str3 : StringUtil.bubbleSort(pathToFile.list())) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(str);
            stringBuffer3.append(File.separator);
            stringBuffer3.append(str3);
            File file = new File(stringBuffer3.toString());
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append(file.canRead() ? "r" : "-");
            stringBuffer4.append(file.canWrite() ? "w" : "-");
            stringBuffer4.append("_");
            stringBuffer4.append(" ");
            Date date = new Date(file.lastModified());
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(date);
            int i2 = gregorianCalendar.get(5);
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append(months[gregorianCalendar.get(2)]);
            stringBuffer5.append(" ");
            stringBuffer5.append(i2);
            stringBuffer4.append(stringBuffer5.toString());
            if (i2 < 10) {
                stringBuffer4.append(" ");
            }
            stringBuffer4.append(" ");
            StringBuffer stringBuffer6 = new StringBuffer();
            for (int i3 = 0; i3 < 8; i3++) {
                stringBuffer6.append(" ");
            }
            stringBuffer6.insert(0, file.length());
            stringBuffer6.setLength(8);
            int indexOf = stringBuffer6.toString().indexOf(" ");
            if (indexOf != -1) {
                String substring = stringBuffer6.toString().substring(indexOf);
                stringBuffer6.setLength(indexOf);
                stringBuffer6.insert(0, substring);
            }
            stringBuffer4.append(stringBuffer6.toString());
            StringBuffer stringBuffer7 = new StringBuffer();
            stringBuffer7.append(" ");
            stringBuffer7.append(file.getName());
            stringBuffer4.append(stringBuffer7.toString());
            if (file.isDirectory()) {
                stringBuffer4.append("/");
            }
            interpreter.println(stringBuffer4.toString());
        }
    }

    public static String usage() {
        return "usage: dir( String dir )\n       dir()";
    }
}
