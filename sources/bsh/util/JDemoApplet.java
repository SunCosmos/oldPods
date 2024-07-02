package bsh.util;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.TargetError;
import java.awt.BorderLayout;
import javax.swing.JApplet;

/* loaded from: classes.dex */
public class JDemoApplet extends JApplet {
    public void init() {
        String parameter = getParameter("debug");
        if (parameter != null && parameter.equals("true")) {
            Interpreter.DEBUG = true;
        }
        String parameter2 = getParameter("type");
        if (parameter2 == null || !parameter2.equals("desktop")) {
            getContentPane().setLayout(new BorderLayout());
            JConsole jConsole = new JConsole();
            getContentPane().add("Center", jConsole);
            new Thread(new Interpreter(jConsole)).start();
            return;
        }
        try {
            new Interpreter().eval("desktop()");
        } catch (TargetError e) {
            e.printStackTrace();
            System.out.println(e.getTarget());
            e.getTarget().printStackTrace();
        } catch (EvalError e2) {
            System.out.println(e2);
            e2.printStackTrace();
        }
    }
}
