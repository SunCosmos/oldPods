package bsh;

import bsh.util.Util;
import java.io.PrintStream;

/* loaded from: classes.dex */
public class Console {
    public static void main(String[] strArr) {
        if (!Capabilities.classExists("bsh.util.Util")) {
            System.out.println("Can't find the BeanShell utilities...");
        }
        if (!Capabilities.haveSwing()) {
            System.err.println("Can't find javax.swing package:  An AWT based Console is available but not built by default.");
            return;
        }
        Util.startSplashScreen();
        try {
            new Interpreter().eval("desktop()");
        } catch (EvalError e) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Couldn't start desktop: ");
            stringBuffer.append(e);
            printStream.println(stringBuffer.toString());
        }
    }
}
