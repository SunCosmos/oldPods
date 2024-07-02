package com.iapp.app;

import android.content.Context;
import bsh.EvalError;
import bsh.Interpreter;

/* loaded from: classes.dex */
public class j extends Interpreter {
    private Context a;

    public j(Context context) {
        this.a = context;
    }

    public Object a(String str) {
        try {
            return getNameSpace().invokeMethod(str, new Object[0], this);
        } catch (EvalError e) {
            e.printStackTrace();
            c.b.a.a.t.P2(this.a, "JavaErr：\n" + e.toString());
            return null;
        }
    }

    public Object b(String str, Object... objArr) {
        try {
            return getNameSpace().invokeMethod(str, objArr, this);
        } catch (EvalError e) {
            e.printStackTrace();
            c.b.a.a.t.P2(this.a, "JavaErr：\n" + e.toString());
            return null;
        }
    }

    public boolean c(String str, Object... objArr) {
        Object obj;
        try {
            obj = getNameSpace().invokeMethod(str, objArr, this);
        } catch (EvalError e) {
            e.printStackTrace();
            c.b.a.a.t.P2(this.a, "JavaErr：\n" + e.toString());
            obj = null;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public boolean d(String str) {
        try {
            eval("import com.iapp.interfaces.*;\nimport java.lang.*;\nimport java.util.*;\nprivate void syso(Object o){\ni.syso(o);\n}\nprivate void tw(Object o){\ni.tw(o);\n}\nprivate void tw(Object o,int s){\ni.tw(o,s);\n}\nprivate void fn(String o){\ni.fn(o);\n}\nprivate void ss(String o,Object v){\ni.ss(o,v);\n}\nprivate void sss(String o,Object v){\ni.sss(o,v);\n}\nprivate Object ss(String o){\nreturn i.ss(o);\n}\nprivate Object sss(String o){\nreturn i.sss(o);\n}\nprivate void imports(String o){\ni.imports(o);\n}\n");
            eval(str.replace("&lt;", "<").replace("&gt;", ">"), getNameSpace());
            return true;
        } catch (EvalError e) {
            e.printStackTrace();
            c.b.a.a.t.P2(this.a, "JavaErr：\n" + e.toString());
            return false;
        }
    }

    public boolean e(String str) {
        try {
            eval("import com.iapp.interfaces.*;\nimport java.lang.*;\nimport java.util.*;\nprivate void syso(Object o){\ni.syso(o);\n}\nprivate void tw(Object o){\ni.tw(o);\n}\nprivate void tw(Object o,int s){\ni.tw(o,s);\n}\nprivate void fn(String o){\ni.fn(o);\n}\nprivate void ss(String o,Object v){\ni.ss(o,v);\n}\nprivate void sss(String o,Object v){\ni.sss(o,v);\n}\nprivate Object ss(String o){\nreturn i.ss(o);\n}\nprivate Object sss(String o){\nreturn i.sss(o);\n}\nprivate void imports(String o){\ni.imports(o);\n}\n");
            return b.h7(this.a, this, str.toLowerCase());
        } catch (EvalError e) {
            e.printStackTrace();
            c.b.a.a.t.P2(this.a, "MJavaErr：\n" + e.toString());
            return false;
        }
    }

    public boolean f(String str) {
        return b.h7(this.a, this, str.toLowerCase());
    }
}
