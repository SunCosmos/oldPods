package bsh.util;

import java.awt.BorderLayout;
import java.awt.Label;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import javax.swing.JApplet;

/* loaded from: classes.dex */
public class JRemoteApplet extends JApplet {
    InputStream in;
    OutputStream out;

    public void init() {
        getContentPane().setLayout(new BorderLayout());
        try {
            URL documentBase = getDocumentBase();
            Socket socket = new Socket(documentBase.getHost(), documentBase.getPort() + 1);
            this.out = socket.getOutputStream();
            this.in = socket.getInputStream();
            getContentPane().add("Center", new JConsole(this.in, this.out));
        } catch (IOException unused) {
            getContentPane().add("Center", new Label("Remote Connection Failed", 1));
        }
    }
}
