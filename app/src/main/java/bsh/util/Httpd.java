package bsh.util;

import java.io.IOException;
import java.net.ServerSocket;

/* loaded from: classes.dex */
public class Httpd extends Thread {
    ServerSocket ss;

    public Httpd(int i2) {
        this.ss = new ServerSocket(i2);
    }

    public static void main(String[] strArr) {
        new Httpd(Integer.parseInt(strArr[0])).start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            try {
                new HttpdConnection(this.ss.accept()).start();
            } catch (IOException e) {
                System.out.println(e);
                return;
            }
        }
    }
}
