package bsh.util;

import bsh.org.objectweb.asm.Constants;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Window;

/* loaded from: classes.dex */
public class Util {
    static /* synthetic */ Class class$bsh$Interpreter;
    static Window splashScreen;

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static void endSplashScreen() {
        Window window = splashScreen;
        if (window != null) {
            window.dispose();
        }
    }

    public static void startSplashScreen() {
        Window window = new Window(new Frame());
        window.pack();
        BshCanvas bshCanvas = new BshCanvas();
        bshCanvas.setSize(275, Constants.LCMP);
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = defaultToolkit.getScreenSize();
        window.setBounds((screenSize.width / 2) - 137, (screenSize.height / 2) - 74, 275, Constants.LCMP);
        window.add("Center", bshCanvas);
        Class cls = class$bsh$Interpreter;
        if (cls == null) {
            cls = class$("bsh.Interpreter");
            class$bsh$Interpreter = cls;
        }
        Image image = defaultToolkit.getImage(cls.getResource("/bsh/util/lib/splash.gif"));
        MediaTracker mediaTracker = new MediaTracker(bshCanvas);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        } catch (Exception unused) {
        }
        bshCanvas.getBufferedGraphics().drawImage(image, 0, 0, bshCanvas);
        window.setVisible(true);
        window.toFront();
        splashScreen = window;
    }
}
