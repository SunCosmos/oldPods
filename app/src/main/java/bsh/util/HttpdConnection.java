package bsh.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/* loaded from: classes.dex */
class HttpdConnection extends Thread {
    Socket client;
    BufferedReader in;
    boolean isHttp1;
    OutputStream out;
    PrintStream pout;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpdConnection(Socket socket) {
        this.client = socket;
        setPriority(4);
    }

    private void error(int i2, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html><h1>");
        stringBuffer.append(str);
        stringBuffer.append("</h1></html>");
        String stringBuffer2 = stringBuffer.toString();
        if (this.isHttp1) {
            PrintStream printStream = this.pout;
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("HTTP/1.0 ");
            stringBuffer3.append(i2);
            stringBuffer3.append(" ");
            stringBuffer3.append(stringBuffer2);
            printStream.println(stringBuffer3.toString());
            this.pout.println("Content-type: text/html");
            PrintStream printStream2 = this.pout;
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("Content-length: ");
            stringBuffer4.append(stringBuffer2.length());
            stringBuffer4.append("\n");
            printStream2.println(stringBuffer4.toString());
        }
        this.pout.println(stringBuffer2);
    }

    private void sendFileData(String str) {
        int read;
        PrintStream printStream;
        String str2;
        InputStream resourceAsStream = getClass().getResourceAsStream(str);
        if (resourceAsStream == null) {
            throw new FileNotFoundException(str);
        }
        int available = resourceAsStream.available();
        byte[] bArr = new byte[available];
        if (this.isHttp1) {
            this.pout.println("HTTP/1.0 200 Document follows");
            PrintStream printStream2 = this.pout;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Content-length: ");
            stringBuffer.append(available);
            printStream2.println(stringBuffer.toString());
            if (str.endsWith(".gif")) {
                printStream = this.pout;
                str2 = "Content-type: image/gif";
            } else if (str.endsWith(".html") || str.endsWith(".htm")) {
                printStream = this.pout;
                str2 = "Content-Type: text/html";
            } else {
                printStream = this.pout;
                str2 = "Content-Type: application/octet-stream";
            }
            printStream.println(str2);
            this.pout.println();
        }
        do {
            read = resourceAsStream.read(bArr);
            if (read > 0) {
                this.pout.write(bArr, 0, read);
            }
        } while (read != -1);
        this.pout.flush();
    }

    private void serveFile(String str) {
        if (str.equals("/")) {
            str = "/remote/remote.html";
        }
        if (str.startsWith("/remote/")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("/bsh/util/lib/");
            stringBuffer.append(str.substring(8));
            str = stringBuffer.toString();
        }
        if (!str.startsWith("/java")) {
            try {
                PrintStream printStream = System.out;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("sending file: ");
                stringBuffer2.append(str);
                printStream.println(stringBuffer2.toString());
                sendFileData(str);
                return;
            } catch (FileNotFoundException unused) {
            }
        }
        error(404, "Object Not Found");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x004f, code lost:
    
        r5.isHttp1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0052, code lost:
    
        r2 = new java.util.StringTokenizer(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005e, code lost:
    
        if (r2.countTokens() >= 2) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0060, code lost:
    
        error(400, "Bad Request");
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0077, code lost:
    
        r5.client.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006e, code lost:
    
        if (r2.nextToken().equals("GET") == false) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0070, code lost:
    
        serveFile(r2.nextToken());
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x003d, code lost:
    
        if (r0.toLowerCase().indexOf("http/1.") != (-1)) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x003f, code lost:
    
        r2 = r5.in.readLine();
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x004b, code lost:
    
        if (r2.equals("") != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x004d, code lost:
    
        if (r2 != null) goto L31;
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r5 = this;
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.io.IOException -> L7d
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.io.IOException -> L7d
            java.net.Socket r2 = r5.client     // Catch: java.io.IOException -> L7d
            java.io.InputStream r2 = r2.getInputStream()     // Catch: java.io.IOException -> L7d
            r1.<init>(r2)     // Catch: java.io.IOException -> L7d
            r0.<init>(r1)     // Catch: java.io.IOException -> L7d
            r5.in = r0     // Catch: java.io.IOException -> L7d
            java.net.Socket r0 = r5.client     // Catch: java.io.IOException -> L7d
            java.io.OutputStream r0 = r0.getOutputStream()     // Catch: java.io.IOException -> L7d
            r5.out = r0     // Catch: java.io.IOException -> L7d
            java.io.PrintStream r0 = new java.io.PrintStream     // Catch: java.io.IOException -> L7d
            java.io.OutputStream r1 = r5.out     // Catch: java.io.IOException -> L7d
            r0.<init>(r1)     // Catch: java.io.IOException -> L7d
            r5.pout = r0     // Catch: java.io.IOException -> L7d
            java.io.BufferedReader r0 = r5.in     // Catch: java.io.IOException -> L7d
            java.lang.String r0 = r0.readLine()     // Catch: java.io.IOException -> L7d
            r1 = 400(0x190, float:5.6E-43)
            if (r0 != 0) goto L32
            java.lang.String r2 = "Empty Request"
            r5.error(r1, r2)     // Catch: java.io.IOException -> L7d
        L32:
            java.lang.String r2 = r0.toLowerCase()     // Catch: java.io.IOException -> L7d
            java.lang.String r3 = "http/1."
            int r2 = r2.indexOf(r3)     // Catch: java.io.IOException -> L7d
            r3 = -1
            if (r2 == r3) goto L52
        L3f:
            java.io.BufferedReader r2 = r5.in     // Catch: java.io.IOException -> L7d
            java.lang.String r2 = r2.readLine()     // Catch: java.io.IOException -> L7d
            java.lang.String r3 = ""
            boolean r3 = r2.equals(r3)     // Catch: java.io.IOException -> L7d
            if (r3 != 0) goto L4f
            if (r2 != 0) goto L3f
        L4f:
            r2 = 1
            r5.isHttp1 = r2     // Catch: java.io.IOException -> L7d
        L52:
            java.util.StringTokenizer r2 = new java.util.StringTokenizer     // Catch: java.io.IOException -> L7d
            r2.<init>(r0)     // Catch: java.io.IOException -> L7d
            int r0 = r2.countTokens()     // Catch: java.io.IOException -> L7d
            r3 = 2
            java.lang.String r4 = "Bad Request"
            if (r0 >= r3) goto L64
        L60:
            r5.error(r1, r4)     // Catch: java.io.IOException -> L7d
            goto L77
        L64:
            java.lang.String r0 = r2.nextToken()     // Catch: java.io.IOException -> L7d
            java.lang.String r3 = "GET"
            boolean r0 = r0.equals(r3)     // Catch: java.io.IOException -> L7d
            if (r0 == 0) goto L60
            java.lang.String r0 = r2.nextToken()     // Catch: java.io.IOException -> L7d
            r5.serveFile(r0)     // Catch: java.io.IOException -> L7d
        L77:
            java.net.Socket r0 = r5.client     // Catch: java.io.IOException -> L7d
            r0.close()     // Catch: java.io.IOException -> L7d
            goto L99
        L7d:
            r0 = move-exception
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "I/O error "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.println(r0)
            java.net.Socket r0 = r5.client     // Catch: java.lang.Exception -> L99
            r0.close()     // Catch: java.lang.Exception -> L99
        L99:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bsh.util.HttpdConnection.run():void");
    }
}
