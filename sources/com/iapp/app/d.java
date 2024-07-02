package com.iapp.app;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import org.keplerproject.luajava.JavaFunction;
import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaObject;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

/* loaded from: classes.dex */
public class d {
    private LuaState a;
    private Context b;

    /* renamed from: c, reason: collision with root package name */
    private Handler f1934c = new HandlerC0066d();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends JavaFunction {
        a(LuaState luaState) {
            super(luaState);
        }

        @Override // org.keplerproject.luajava.JavaFunction
        public int execute() {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 2; i2 <= this.L.getTop(); i2++) {
                String typeName = this.L.typeName(this.L.type(i2));
                String str = null;
                if (typeName.equals("userdata")) {
                    Object javaObject = this.L.toJavaObject(i2);
                    if (javaObject != null) {
                        str = javaObject.toString();
                    }
                } else {
                    str = typeName.equals("nil") ? "null" : typeName.equals("boolean") ? this.L.toBoolean(i2) ? "true" : "false" : this.L.toString(i2);
                }
                if (str != null) {
                    typeName = str;
                }
                stringBuffer.append(typeName);
                stringBuffer.append('\t');
            }
            c.b.a.a.t.P2(d.this.b, stringBuffer.toString());
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b extends JavaFunction {
        b(LuaState luaState) {
            super(luaState);
        }

        @Override // org.keplerproject.luajava.JavaFunction
        public int execute() {
            Message message;
            int top = this.L.getTop();
            if (top < 2) {
                return 0;
            }
            String typeName = this.L.typeName(this.L.type(2));
            String str = null;
            if (typeName.equals("userdata")) {
                Object javaObject = this.L.toJavaObject(2);
                if (javaObject != null) {
                    str = javaObject.toString();
                }
            } else {
                str = typeName.equals("nil") ? "null" : typeName.equals("boolean") ? this.L.toBoolean(2) ? "true" : "false" : this.L.toString(2);
            }
            if (str != null) {
                typeName = str;
            }
            if (top > 2) {
                message = new Message();
                message.what = 1;
                message.arg1 = this.L.toInteger(3);
            } else {
                message = new Message();
                message.what = 1;
                message.arg1 = 1;
            }
            message.obj = typeName;
            d.this.f1934c.sendMessage(message);
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c extends JavaFunction {
        c(LuaState luaState) {
            super(luaState);
        }

        @Override // org.keplerproject.luajava.JavaFunction
        public int execute() {
            com.iapp.app.b.h8(d.this.b, this.L, this.L.toString(-1).toLowerCase());
            return 1;
        }
    }

    /* renamed from: com.iapp.app.d$d, reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class HandlerC0066d extends Handler {
        HandlerC0066d() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                Toast.makeText(d.this.b, (String) message.obj, message.arg1).show();
            }
        }
    }

    public d(Context context) {
        this.b = context;
        LuaState newLuaState = LuaStateFactory.newLuaState();
        this.a = newLuaState;
        newLuaState.openLibs();
    }

    private String f(int i2) {
        if (i2 == 1) {
            return "Yield error";
        }
        if (i2 == 2) {
            return "Runtime error";
        }
        if (i2 == 3) {
            return "Syntax error";
        }
        if (i2 == 4) {
            return "Out of memory";
        }
        return "Unknown error " + i2;
    }

    public void c(String str) {
        try {
            this.a.getLuaObject(str).callx();
        } catch (LuaException e) {
            e.printStackTrace();
            c.b.a.a.t.P2(this.b, "VoidMethod LuaErr：\n" + e.getMessage());
        }
    }

    public void d(String str, Object... objArr) {
        try {
            LuaObject luaObject = this.a.getLuaObject(str);
            if (luaObject.isNil()) {
                return;
            }
            luaObject.call(objArr);
        } catch (LuaException e) {
            e.printStackTrace();
            c.b.a.a.t.P2(this.b, "Method LuaErr：\n" + e.getMessage());
        }
    }

    public boolean e(String str, Object... objArr) {
        Object obj;
        LuaObject luaObject;
        try {
            luaObject = this.a.getLuaObject(str);
        } catch (LuaException e) {
            e.printStackTrace();
            c.b.a.a.t.P2(this.b, "Method LuaErr：\n" + e.getMessage());
            obj = null;
        }
        if (luaObject.isNil()) {
            return false;
        }
        obj = luaObject.call(objArr);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public void g(String str) {
        this.a.setTop(0);
        int LloadString = this.a.LloadString(str.replace("&lt;", "<").replace("&gt;", ">"));
        if (LloadString == 0) {
            this.a.getGlobal("debug");
            this.a.getField(-1, "traceback");
            this.a.remove(-2);
            this.a.insert(-2);
            LloadString = this.a.pcall(0, 0, -2);
            if (LloadString == 0) {
                return;
            }
        }
        throw new LuaException(f(LloadString) + ": " + this.a.toString(-1));
    }

    public void h(String str) {
        this.a.setTop(0);
        int LloadString = this.a.LloadString(str);
        if (LloadString == 0) {
            this.a.getGlobal("debug");
            this.a.getField(-1, "traceback");
            this.a.remove(-2);
            this.a.insert(-2);
            LloadString = this.a.pcall(0, 0, -2);
            if (LloadString == 0) {
                return;
            }
        }
        throw new LuaException(f(LloadString) + ": " + this.a.toString(-1));
    }

    public void i(String str) {
        this.a.setTop(0);
        int h8 = com.iapp.app.b.h8(this.b, this.a, str.toLowerCase());
        if (h8 == 0) {
            this.a.getGlobal("debug");
            this.a.getField(-1, "traceback");
            this.a.remove(-2);
            this.a.insert(-2);
            h8 = this.a.pcall(0, 0, -2);
            if (h8 == 0) {
                return;
            }
        }
        throw new LuaException(f(h8) + ": " + this.a.toString(-1));
    }

    public LuaState j() {
        return this.a;
    }

    public void k() {
        try {
            a aVar = new a(this.a);
            aVar.register("print");
            aVar.register("syso");
            new b(this.a).register("tw");
            c cVar = new c(this.a);
            this.a.getGlobal("package");
            this.a.getField(-1, "loaders");
            int objLen = this.a.objLen(-1);
            this.a.pushJavaFunction(cVar);
            this.a.rawSetI(-2, objLen + 1);
            this.a.pop(1);
            this.a.getField(-1, "path");
            String str = this.b.getFilesDir() + "/?.lua";
            this.a.pushString(";" + str);
            this.a.concat(2);
            this.a.setField(-2, "path");
            this.a.pop(1);
        } catch (Exception unused) {
        }
    }

    public void l(String str, Object obj) {
        this.a.pushJavaObject(obj);
        this.a.setGlobal(str);
    }
}
