package bsh.util;

import bsh.NameSpace;
import java.io.IOException;
import java.net.ServerSocket;

/* loaded from: classes.dex */
public class Sessiond extends Thread {
    NameSpace globalNameSpace;
    private ServerSocket ss;

    public Sessiond(NameSpace nameSpace, int i2) {
        this.ss = new ServerSocket(i2);
        this.globalNameSpace = nameSpace;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            try {
                new SessiondConnection(this.globalNameSpace, this.ss.accept()).start();
            } catch (IOException e) {
                System.out.println(e);
                return;
            }
        }
    }
}
