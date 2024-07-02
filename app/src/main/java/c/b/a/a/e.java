package c.b.a.a;

import android.net.Uri;
import com.iapp.interfaces.OnFileDownStatusListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public class e {
    private int a;
    private int b;

    /* renamed from: c, reason: collision with root package name */
    private String f1342c;

    /* renamed from: d, reason: collision with root package name */
    private String f1343d;
    private ArrayList<HashMap<Object, Object>> e;
    private boolean f;
    private boolean g;
    private int h;

    /* renamed from: i, reason: collision with root package name */
    private OnFileDownStatusListener f1344i;
    public Object j;
    public Object k;
    private HashMap<Object, Object> l;

    /* loaded from: classes.dex */
    class a extends Thread {
        a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            boolean z;
            int i2 = 0;
            while (e.this.f) {
                if (e.this.h < e.this.a) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= e.this.e.size()) {
                            z = true;
                            break;
                        } else {
                            if (e.this.h >= e.this.a) {
                                z = false;
                                break;
                            }
                            if (((HashMap) e.this.e.get(i3)).get("ok").equals(-3)) {
                                e.this.p(i3);
                                i2++;
                            }
                            i3++;
                        }
                    }
                    if (z) {
                        e.this.f = false;
                    }
                }
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (e.this.h > 0) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            e.this.f1344i.complete(i2, e.this.k);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends Thread {
        final /* synthetic */ int a;

        b(int i2) {
            this.a = i2;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0096  */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r7 = this;
                c.b.a.a.e r0 = c.b.a.a.e.this
                c.b.a.a.e.e(r0)
                int r0 = r7.a
                c.b.a.a.e r1 = c.b.a.a.e.this
                java.util.ArrayList r1 = c.b.a.a.e.h(r1)
                java.lang.Object r1 = r1.get(r0)
                java.util.HashMap r1 = (java.util.HashMap) r1
                java.lang.String r2 = "url"
                java.lang.Object r2 = r1.get(r2)
                java.lang.String r2 = (java.lang.String) r2
                java.lang.String r3 = "filename"
                java.lang.Object r4 = r1.get(r3)
                if (r4 != 0) goto L49
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                c.b.a.a.e r5 = c.b.a.a.e.this
                java.lang.String r5 = c.b.a.a.e.k(r5)
                r4.append(r5)
                java.lang.String r5 = c.b.a.a.p.d(r2)
                r4.append(r5)
                r5 = 46
                r4.append(r5)
                java.lang.String r5 = c.b.a.a.p.a(r2)
                r4.append(r5)
                java.lang.String r4 = r4.toString()
                goto L50
            L49:
                r5 = r4
                java.lang.String r5 = (java.lang.String) r5
                r6 = 0
                c.b.a.a.d.b(r5, r6)
            L50:
                java.lang.String r5 = "coveringis"
                java.lang.Object r1 = r1.get(r5)
                java.lang.Boolean r5 = java.lang.Boolean.TRUE
                boolean r1 = r1.equals(r5)
                if (r1 == 0) goto L6c
                c.b.a.a.e r1 = c.b.a.a.e.this
                r5 = r4
                java.lang.String r5 = (java.lang.String) r5
            L63:
                boolean r6 = c.b.a.a.e.l(r1)
                int r1 = c.b.a.a.e.m(r1, r2, r5, r6)
                goto L7f
            L6c:
                java.io.File r1 = new java.io.File
                r5 = r4
                java.lang.String r5 = (java.lang.String) r5
                r1.<init>(r5)
                boolean r1 = r1.exists()
                if (r1 == 0) goto L7c
                r1 = 1
                goto L7f
            L7c:
                c.b.a.a.e r1 = c.b.a.a.e.this
                goto L63
            L7f:
                c.b.a.a.e r2 = c.b.a.a.e.this
                java.util.ArrayList r2 = c.b.a.a.e.h(r2)
                java.lang.Object r2 = r2.get(r0)
                java.util.HashMap r2 = (java.util.HashMap) r2
                java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
                java.lang.String r6 = "ok"
                r2.put(r6, r5)
                if (r1 < 0) goto La5
                c.b.a.a.e r2 = c.b.a.a.e.this
                java.util.ArrayList r2 = c.b.a.a.e.h(r2)
                java.lang.Object r2 = r2.get(r0)
                java.util.HashMap r2 = (java.util.HashMap) r2
                r2.put(r3, r4)
            La5:
                c.b.a.a.e r2 = c.b.a.a.e.this
                com.iapp.interfaces.OnFileDownStatusListener r2 = c.b.a.a.e.j(r2)
                c.b.a.a.e r3 = c.b.a.a.e.this
                java.lang.Object r3 = r3.j
                r2.resultStatus(r0, r1, r3)
                c.b.a.a.e r0 = c.b.a.a.e.this
                c.b.a.a.e.f(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.e.b.run():void");
        }
    }

    public e(String str) {
        this.a = 3;
        this.b = 25000;
        this.f = false;
        this.g = false;
        this.h = 0;
        this.j = null;
        this.k = null;
        this.e = new ArrayList<>();
        String replace = str.replace('\\', '/');
        if (!replace.endsWith("/")) {
            replace = replace + File.separator;
        }
        this.f1342c = replace;
        this.f1343d = replace;
        d.b(replace, true);
    }

    public e(String str, String str2) {
        this.a = 3;
        this.b = 25000;
        this.f = false;
        this.g = false;
        this.h = 0;
        this.j = null;
        this.k = null;
        this.e = new ArrayList<>();
        String replace = str.replace('\\', '/');
        if (!replace.endsWith("/")) {
            replace = replace + File.separator;
        }
        String replace2 = str2.replace('\\', '/');
        if (!replace2.endsWith("/")) {
            replace2 = replace2 + File.separator;
        }
        this.f1342c = replace;
        this.f1343d = replace2;
        d.b(replace, true);
        d.b(this.f1343d, true);
    }

    public e(String str, String str2, int i2, int i3, boolean z) {
        this.a = 3;
        this.b = 25000;
        this.f = false;
        this.g = false;
        this.h = 0;
        this.j = null;
        this.k = null;
        this.e = new ArrayList<>();
        String replace = str.replace('\\', '/');
        if (!replace.endsWith("/")) {
            replace = replace + File.separator;
        }
        String replace2 = str2.replace('\\', '/');
        if (!replace2.endsWith("/")) {
            replace2 = replace2 + File.separator;
        }
        this.f1342c = replace;
        this.f1343d = replace2;
        this.a = i2;
        this.b = i3;
        this.g = z;
        d.b(replace, true);
        d.b(this.f1343d, true);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: PHI empty after try-catch fix!
        	at jadx.core.dex.visitors.ssa.SSATransform.fixPhiInTryCatch(SSATransform.java:228)
        	at jadx.core.dex.visitors.ssa.SSATransform.fixLastAssignInTry(SSATransform.java:208)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:64)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
        */
    /* JADX INFO: Access modifiers changed from: private */
    public int a(
    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: PHI empty after try-catch fix!
        	at jadx.core.dex.visitors.ssa.SSATransform.fixPhiInTryCatch(SSATransform.java:228)
        	at jadx.core.dex.visitors.ssa.SSATransform.fixLastAssignInTry(SSATransform.java:208)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:64)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r7v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:237)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:223)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:168)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:401)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    static /* synthetic */ int e(e eVar) {
        int i2 = eVar.h;
        eVar.h = i2 + 1;
        return i2;
    }

    static /* synthetic */ int f(e eVar) {
        int i2 = eVar.h;
        eVar.h = i2 - 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p(int i2) {
        b bVar = new b(i2);
        bVar.setName("CeShi_" + bVar.getId());
        bVar.start();
    }

    private int r(URL url) {
        int i2 = -1;
        try {
            URLConnection openConnection = url.openConnection();
            openConnection.setRequestProperty("Accept-Encoding", "identity");
            openConnection.setConnectTimeout(this.b);
            int contentLength = openConnection.getContentLength();
            if (contentLength != -1) {
                return contentLength;
            }
            try {
                String headerField = openConnection.getHeaderField("Content-Length");
                return headerField != null ? Integer.parseInt(headerField) : contentLength;
            } catch (MalformedURLException e) {
                e = e;
                i2 = contentLength;
                e.printStackTrace();
                return i2;
            } catch (IOException e2) {
                e = e2;
                i2 = contentLength;
                e.printStackTrace();
                return i2;
            }
        } catch (MalformedURLException e3) {
            e = e3;
        } catch (IOException e4) {
            e = e4;
        }
    }

    public void n(String str, int i2, Object obj) {
        if (str.contains(" ")) {
            str = str.replace(" ", "%20");
        }
        if (str.getBytes().length != str.length()) {
            String str2 = "";
            for (char c2 : str.toCharArray()) {
                String valueOf = String.valueOf(c2);
                str2 = valueOf.getBytes().length == 1 ? str2 + c2 : str2 + Uri.encode(valueOf);
            }
            str = str2;
        }
        HashMap<Object, Object> hashMap = new HashMap<>();
        this.l = hashMap;
        hashMap.put("url", str);
        this.l.put("type", Integer.valueOf(i2));
        this.l.put("text", obj);
        this.l.put("filename", null);
        this.l.put("coveringis", Boolean.FALSE);
        this.l.put("ok", -3);
        this.e.add(this.l);
    }

    public void o(String str, int i2, Object obj, String str2) {
        if (str.contains(" ")) {
            str = str.replace(" ", "%20");
        }
        if (str.getBytes().length != str.length()) {
            String str3 = "";
            for (char c2 : str.toCharArray()) {
                String valueOf = String.valueOf(c2);
                str3 = valueOf.getBytes().length == 1 ? str3 + c2 : str3 + Uri.encode(valueOf);
            }
            str = str3;
        }
        d.b(str2, false);
        HashMap<Object, Object> hashMap = new HashMap<>();
        this.l = hashMap;
        hashMap.put("url", str);
        this.l.put("type", Integer.valueOf(i2));
        this.l.put("text", obj);
        this.l.put("filename", str2);
        this.l.put("coveringis", Boolean.FALSE);
        this.l.put("ok", -3);
        this.e.add(this.l);
    }

    public ArrayList<HashMap<Object, Object>> q() {
        return this.e;
    }

    public void s(OnFileDownStatusListener onFileDownStatusListener) {
        this.f1344i = onFileDownStatusListener;
    }

    public boolean t() {
        if (this.f) {
            return false;
        }
        this.f = true;
        a aVar = new a();
        aVar.setName("CeShi_" + aVar.getId());
        aVar.start();
        return true;
    }
}
