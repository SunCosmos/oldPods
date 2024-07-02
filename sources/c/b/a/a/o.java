package c.b.a.a;

import com.iapp.interfaces.OnMessagesListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class o {
    private List<c> a;
    private ServerSocket b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f1351c;

    /* renamed from: d, reason: collision with root package name */
    private int f1352d;
    private String e;
    public boolean f;
    private int g;
    private c h;

    /* renamed from: i, reason: collision with root package name */
    private OnMessagesListener f1353i;

    /* loaded from: classes.dex */
    class a extends Thread {
        final /* synthetic */ String a;
        final /* synthetic */ int b;

        a(String str, int i2) {
            this.a = str;
            this.b = i2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Socket socket = new Socket(this.a, this.b);
                if (o.this.f1352d != 0) {
                    socket.setSoTimeout(o.this.f1352d);
                }
                o oVar = o.this;
                oVar.h = new c(socket, 1000, oVar.f1353i);
                o.this.a.add(o.this.h);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* loaded from: classes.dex */
    class b extends Thread {
        b() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!o.this.b.isClosed()) {
                try {
                    Socket accept = o.this.b.accept();
                    o oVar = o.this;
                    if (oVar.f1351c) {
                        if (oVar.f1352d != 0) {
                            accept.setSoTimeout(o.this.f1352d);
                        }
                        o.o(o.this);
                        List list = o.this.a;
                        o oVar2 = o.this;
                        list.add(new c(accept, oVar2.g, o.this.f1353i));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class c {
        private Socket a;
        private int b;

        /* renamed from: c, reason: collision with root package name */
        private OnMessagesListener f1355c;

        /* loaded from: classes.dex */
        class a extends Thread {
            a(o oVar) {
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                InputStream inputStream;
                long j;
                byte[] bArr = new byte[512];
                ByteArrayOutputStream byteArrayOutputStream = null;
                try {
                    inputStream = c.this.a.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                    inputStream = null;
                }
                String str = null;
                long j2 = 0;
                long j3 = 0;
                byte b = 0;
                while (!c.this.a.isClosed()) {
                    try {
                        int read = inputStream.read(bArr);
                        if (read > 0) {
                            if (read < 20 || !o.this.C(bArr)) {
                                j2 += read;
                                j = 0;
                                if (j3 > 0) {
                                    byteArrayOutputStream.write(bArr, 0, read);
                                }
                            } else {
                                long z = o.this.z(bArr);
                                byte b2 = bArr[0];
                                if (b2 != 1 && b2 != 3) {
                                    if (b2 == 2) {
                                        int i2 = bArr[20] + 21;
                                        int i3 = read - i2;
                                        long j4 = i3;
                                        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream((int) z);
                                        String str2 = new String(bArr, 21, (int) bArr[20]);
                                        if (read > i2) {
                                            byteArrayOutputStream2.write(bArr, i2, i3);
                                        }
                                        byteArrayOutputStream = byteArrayOutputStream2;
                                        str = str2;
                                        b = b2;
                                        j3 = z;
                                        j2 = j4;
                                        j = 0;
                                    } else {
                                        b = b2;
                                        j = 0;
                                        j2 = 0;
                                        j3 = 0;
                                    }
                                }
                                int i4 = read - 20;
                                long j5 = i4;
                                ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream((int) z);
                                if (read > 20) {
                                    byteArrayOutputStream3.write(bArr, 20, i4);
                                }
                                byteArrayOutputStream = byteArrayOutputStream3;
                                b = b2;
                                j = 0;
                                j2 = j5;
                                j3 = z;
                            }
                            if (j3 == j2) {
                                if (b == 1) {
                                    c.this.f1355c.Message(o.this.y(byteArrayOutputStream.toByteArray()), c.this);
                                } else if (b == 2) {
                                    c.this.f1355c.Message(o.this.x(byteArrayOutputStream.toByteArray(), str), c.this);
                                } else {
                                    if (b == 3) {
                                        c.this.f1355c.Message(byteArrayOutputStream.toByteArray(), c.this);
                                    }
                                    j2 = j;
                                    j3 = j2;
                                }
                                byteArrayOutputStream.close();
                                j2 = j;
                                j3 = j2;
                            }
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
            }
        }

        public c(Socket socket, int i2, OnMessagesListener onMessagesListener) {
            this.a = socket;
            this.b = i2;
            this.f1355c = onMessagesListener;
            a aVar = new a(o.this);
            aVar.setName("CeShi_" + aVar.getId());
            aVar.start();
        }

        public void a(File file) {
            byte[] v = d.v(file);
            try {
                this.a.getOutputStream().write(o.this.E(v.length, file.getName()));
                this.a.getOutputStream().write(v);
                this.a.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void b(String str) {
            byte[] bytes = str.getBytes();
            try {
                this.a.getOutputStream().write(o.this.D(1, bytes.length));
                this.a.getOutputStream().write(bytes);
                this.a.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void c(byte[] bArr) {
            try {
                this.a.getOutputStream().write(o.this.D(1, bArr.length));
                this.a.getOutputStream().write(bArr);
                this.a.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void d(byte[] bArr) {
            try {
                this.a.getOutputStream().write(bArr);
                this.a.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void g() {
            if (this.a.isClosed()) {
                return;
            }
            o.this.a.remove(this.a);
            try {
                this.a.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String h() {
            return this.a.getInetAddress().getHostAddress();
        }

        public int i() {
            return this.b;
        }

        public Socket j() {
            return this.a;
        }

        public boolean k() {
            return this.a.isClosed();
        }
    }

    public o(int i2, String str, int i3, int i4, boolean z) {
        this.a = new ArrayList();
        this.b = null;
        this.f1351c = true;
        this.f1352d = 0;
        this.e = null;
        this.f = false;
        this.g = 1000;
        this.h = null;
        try {
            this.b = new ServerSocket(i2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (i3 != 0) {
            try {
                this.b.setSoTimeout(i3);
            } catch (SocketException e2) {
                e2.printStackTrace();
            }
        }
        this.f1352d = i4;
        this.e = str;
        this.f = z;
        b bVar = new b();
        bVar.setName("CeShi_" + bVar.getId());
        bVar.start();
    }

    public o(String str, int i2, int i3, boolean z) {
        this.a = new ArrayList();
        this.b = null;
        this.f1351c = true;
        this.f1352d = 0;
        this.e = null;
        this.f = false;
        this.g = 1000;
        this.h = null;
        this.f1352d = i3;
        this.f = z;
        a aVar = new a(str, i2);
        aVar.setName("CeShi_" + aVar.getId());
        aVar.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean C(byte[] bArr) {
        return bArr[16] == 116 && bArr[17] == 93 && bArr[18] == 123 && bArr[19] == 111;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] D(int i2, long j) {
        byte[] bArr = new byte[20];
        bArr[0] = (byte) i2;
        char[] charArray = String.valueOf(j).toCharArray();
        int length = charArray.length;
        bArr[1] = (byte) length;
        int i3 = 2;
        for (int i4 = length - 1; i4 >= 0; i4--) {
            bArr[i3] = (byte) Integer.parseInt(String.valueOf(charArray[i4]));
            i3++;
        }
        bArr[16] = 116;
        bArr[17] = 93;
        bArr[18] = 123;
        bArr[19] = 111;
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] E(long j, String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[length + 21];
        int i2 = 2;
        bArr[0] = 2;
        char[] charArray = String.valueOf(j).toCharArray();
        bArr[1] = (byte) charArray.length;
        for (int i3 = r8 - 1; i3 >= 0; i3--) {
            bArr[i2] = (byte) Integer.parseInt(String.valueOf(charArray[i3]));
            i2++;
        }
        bArr[16] = 116;
        bArr[17] = 93;
        bArr[18] = 123;
        bArr[19] = 111;
        bArr[20] = (byte) length;
        int i4 = 21;
        for (byte b2 : bytes) {
            bArr[i4] = b2;
            i4++;
        }
        return bArr;
    }

    static /* synthetic */ int o(o oVar) {
        int i2 = oVar.g;
        oVar.g = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String x(byte[] bArr, String str) {
        String e = p.e(bArr);
        String str2 = this.e + "/" + e + ".temp";
        File file = new File(str2);
        if (this.f || !file.exists()) {
            d.j(str2, bArr);
        }
        return "{FileSend:[file:" + str2 + "; name:" + str + "; md5:" + e + "; size:" + bArr.length + "]}";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String y(byte[] bArr) {
        return new String(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long z(byte[] bArr) {
        byte b2 = bArr[1];
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = b2 + 1; i2 > 1; i2--) {
            stringBuffer.append((int) bArr[i2]);
        }
        return Long.parseLong(stringBuffer.toString());
    }

    public c A(int i2) {
        if (this.a.size() < i2) {
            return this.a.get(i2);
        }
        return null;
    }

    public boolean B() {
        ServerSocket serverSocket = this.b;
        if (serverSocket != null) {
            return serverSocket.isClosed();
        }
        c cVar = this.h;
        if (cVar != null) {
            return cVar.k();
        }
        return true;
    }

    public void F(OnMessagesListener onMessagesListener) {
        this.f1353i = onMessagesListener;
    }

    public void a(File file) {
        byte[] v = d.v(file);
        byte[] E = E(v.length, file.getName());
        for (c cVar : this.a) {
            try {
                cVar.j().getOutputStream().write(E);
                cVar.j().getOutputStream().write(v);
                cVar.j().getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void b(String str) {
        byte[] bytes = str.getBytes();
        byte[] D = D(1, bytes.length);
        for (c cVar : this.a) {
            try {
                cVar.j().getOutputStream().write(D);
                cVar.j().getOutputStream().write(bytes);
                cVar.j().getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void c(byte[] bArr) {
        byte[] D = D(3, bArr.length);
        for (c cVar : this.a) {
            try {
                cVar.j().getOutputStream().write(D);
                cVar.j().getOutputStream().write(bArr);
                cVar.j().getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void d(byte[] bArr) {
        for (c cVar : this.a) {
            try {
                cVar.j().getOutputStream().write(bArr);
                cVar.j().getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void s() {
        this.f1351c = false;
        Iterator<c> it = this.a.iterator();
        while (it.hasNext()) {
            try {
                it.next().j().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.a.clear();
        ServerSocket serverSocket = this.b;
        if (serverSocket == null || serverSocket.isClosed()) {
            return;
        }
        try {
            this.b.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public int t() {
        return this.g;
    }

    public int u() {
        return this.a.size();
    }

    public ServerSocket v() {
        return this.b;
    }

    public List<c> w() {
        return this.a;
    }
}
