package bsh.util;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.This;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;

/* loaded from: classes.dex */
public class BshCanvas extends JComponent {
    Image imageBuffer;
    This ths;

    public BshCanvas() {
    }

    public BshCanvas(This r1) {
        this.ths = r1;
    }

    public Graphics getBufferedGraphics() {
        Dimension size = getSize();
        Image createImage = createImage(size.width, size.height);
        this.imageBuffer = createImage;
        return createImage.getGraphics();
    }

    public void paintComponent(Graphics graphics) {
        Image image = this.imageBuffer;
        if (image != null) {
            graphics.drawImage(image, 0, 0, this);
        }
        This r0 = this.ths;
        if (r0 != null) {
            try {
                r0.invokeMethod("paint", new Object[]{graphics});
            } catch (EvalError e) {
                if (Interpreter.DEBUG) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("BshCanvas: method invocation error:");
                    stringBuffer.append(e);
                    Interpreter.debug(stringBuffer.toString());
                }
            }
        }
    }

    public void setBounds(int i2, int i3, int i4, int i5) {
        setPreferredSize(new Dimension(i4, i5));
        setMinimumSize(new Dimension(i4, i5));
        super.setBounds(i2, i3, i4, i5);
    }
}
