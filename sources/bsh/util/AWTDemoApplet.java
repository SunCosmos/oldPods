package bsh.util;

import bsh.Interpreter;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Component;

/* loaded from: classes.dex */
public class AWTDemoApplet extends Applet {
    public void init() {
        setLayout(new BorderLayout());
        AWTConsole aWTConsole = new AWTConsole();
        add("Center", (Component) aWTConsole);
        new Thread(new Interpreter(aWTConsole)).start();
    }
}
