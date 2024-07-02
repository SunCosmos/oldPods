package bsh.util;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Label;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

/* loaded from: classes.dex */
public class AWTRemoteApplet extends Applet {
    InputStream in;
    OutputStream out;

    public void init() {
        setLayout(new BorderLayout());
        try {
            URL documentBase = getDocumentBase();
            Socket socket = new Socket(documentBase.getHost(), documentBase.getPort() + 1);
            this.out = socket.getOutputStream();
            this.in = socket.getInputStream();
            add("Center", new AWTConsole(this.in, this.out));
        } catch (IOException unused) {
            add("Center", new Label("Remote Connection Failed", 1));
        }
    }
}
