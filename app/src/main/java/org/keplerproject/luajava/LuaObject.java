package org.keplerproject.luajava;

import c.b.a.a.t;
import java.lang.reflect.Array;
import java.lang.reflect.Proxy;
import java.util.StringTokenizer;

/* loaded from: classes.dex */
public class LuaObject {
    protected LuaState L;
    protected Integer ref;

    public LuaObject(LuaObject luaObject, Number number) {
        synchronized (luaObject.getLuaState()) {
            this.L = luaObject.getLuaState();
            if (!luaObject.isTable() && !luaObject.isUserdata()) {
                throw new LuaException("Object parent should be a table or userdata .");
            }
            luaObject.push();
            this.L.pushNumber(number.doubleValue());
            this.L.getTable(-2);
            this.L.remove(-2);
            registerValue(-1);
            this.L.pop(1);
        }
    }

    public LuaObject(LuaObject luaObject, String str) {
        synchronized (luaObject.getLuaState()) {
            this.L = luaObject.getLuaState();
            if (!luaObject.isTable() && !luaObject.isUserdata()) {
                throw new LuaException("Object parent should be a table or userdata .");
            }
            luaObject.push();
            this.L.pushString(str);
            this.L.getTable(-2);
            this.L.remove(-2);
            registerValue(-1);
            this.L.pop(1);
        }
    }

    public LuaObject(LuaObject luaObject, LuaObject luaObject2) {
        if (luaObject.getLuaState() != luaObject2.getLuaState()) {
            throw new LuaException("LuaStates must be the same!");
        }
        synchronized (luaObject.getLuaState()) {
            if (!luaObject.isTable() && !luaObject.isUserdata()) {
                throw new LuaException("Object parent should be a table or userdata .");
            }
            this.L = luaObject.getLuaState();
            luaObject.push();
            luaObject2.push();
            this.L.getTable(-2);
            this.L.remove(-2);
            registerValue(-1);
            this.L.pop(1);
        }
    }

    public LuaObject(LuaState luaState, int i2) {
        synchronized (luaState) {
            this.L = luaState;
            registerValue(i2);
        }
    }

    public LuaObject(LuaState luaState, String str) {
        synchronized (luaState) {
            this.L = luaState;
            luaState.getGlobal(str);
            registerValue(-1);
            luaState.pop(1);
        }
    }

    private void registerValue(int i2) {
        synchronized (this.L) {
            this.L.pushValue(i2);
            this.ref = new Integer(this.L.Lref(LuaState.LUA_REGISTRYINDEX));
        }
    }

    public Object[] array() {
        Object[] objArr;
        synchronized (this.L) {
            if (!isTable()) {
                throw new LuaException("Invalid object. Not a table .");
            }
            push();
            int objLen2 = this.L.objLen2(-1);
            Object newInstance = Array.newInstance((Class<?>) Object.class, objLen2);
            for (int i2 = 1; i2 <= objLen2; i2++) {
                this.L.pushInteger(i2);
                this.L.getTable(-2);
                Array.set(newInstance, i2 - 1, this.L.toJavaObject(-1));
                this.L.pop(1);
            }
            this.L.pop(1);
            objArr = (Object[]) newInstance;
        }
        return objArr;
    }

    public Object call(Object... objArr) {
        return call(objArr, 1)[0];
    }

    public Object[] call(Object[] objArr, int i2) {
        int i3;
        Object[] objArr2;
        String str;
        StringBuilder sb;
        String str2;
        synchronized (this.L) {
            if (!isFunction() && !isTable() && !isUserdata()) {
                throw new LuaException("Invalid object. Not a function, table or userdata .");
            }
            int top = this.L.getTop();
            push();
            if (objArr != null) {
                for (Object obj : objArr) {
                    this.L.pushObjectValue(obj);
                }
            } else {
                i3 = 0;
            }
            int pcall = this.L.pcall(i3, i2, 0);
            if (pcall != 0) {
                if (this.L.isString(-1)) {
                    str = this.L.toString(-1);
                    this.L.pop(1);
                } else {
                    str = "";
                }
                if (pcall == 2) {
                    sb = new StringBuilder();
                    sb.append("Runtime error. ");
                    sb.append(str);
                } else if (pcall == 4) {
                    sb = new StringBuilder();
                    sb.append("Memory allocation error. ");
                    sb.append(str);
                } else {
                    if (pcall != 6) {
                        str2 = "Lua Error code " + pcall + ". " + str;
                        throw new LuaException(str2);
                    }
                    sb = new StringBuilder();
                    sb.append("Error while running the error handler function. ");
                    sb.append(str);
                }
                str2 = sb.toString();
                throw new LuaException(str2);
            }
            if (i2 == -1) {
                i2 = this.L.getTop() - top;
            }
            if (this.L.getTop() - top < i2) {
                throw new LuaException("Invalid Number of Results .");
            }
            objArr2 = new Object[i2];
            while (i2 > 0) {
                objArr2[i2 - 1] = this.L.toJavaObject(-1);
                this.L.pop(1);
                i2--;
            }
        }
        return objArr2;
    }

    public boolean callBoolNoErr(Object... objArr) {
        Object obj = null;
        try {
            obj = callx(objArr, 1)[0];
        } catch (LuaException e) {
            e.printStackTrace();
            t.P2(null, "Method LuaErr：\n" + e.getMessage());
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public Object callNoErr(Object... objArr) {
        try {
            return callx(objArr, 1)[0];
        } catch (LuaException e) {
            e.printStackTrace();
            t.P2(null, "Method LuaErr：\n" + e.getMessage());
            return null;
        }
    }

    public void callx() {
        String str;
        StringBuilder sb;
        String str2;
        synchronized (this.L) {
            if (!isFunction() && !isTable() && !isUserdata()) {
                throw new LuaException("Invalid object. Not a function, table or userdata .");
            }
            int top = this.L.getTop();
            push();
            int pcall = this.L.pcall(0, 1, 0);
            if (pcall != 0) {
                if (this.L.isString(-1)) {
                    str = this.L.toString(-1);
                    this.L.pop(1);
                } else {
                    str = "";
                }
                if (pcall == 2) {
                    sb = new StringBuilder();
                    sb.append("Runtime error. ");
                    sb.append(str);
                } else if (pcall == 4) {
                    sb = new StringBuilder();
                    sb.append("Memory allocation error. ");
                    sb.append(str);
                } else {
                    if (pcall != 6) {
                        str2 = "Lua Error code " + pcall + ". " + str;
                        throw new LuaException(str2);
                    }
                    sb = new StringBuilder();
                    sb.append("Error while running the error handler function. ");
                    sb.append(str);
                }
                str2 = sb.toString();
                throw new LuaException(str2);
            }
            if (this.L.getTop() - top < 1) {
                throw new LuaException("Invalid Number of Results .");
            }
            Object[] objArr = new Object[1];
            for (int i2 = 1; i2 > 0; i2--) {
                objArr[i2 - 1] = this.L.toJavaObject(-1);
                this.L.pop(1);
            }
        }
    }

    public Object[] callx(Object[] objArr, int i2) {
        int i3;
        Object[] objArr2;
        String str;
        StringBuilder sb;
        String str2;
        synchronized (this.L) {
            if (!isFunction() && !isTable() && !isUserdata()) {
                throw new LuaException("Invalid object. Not a function, table or userdata .");
            }
            int top = this.L.getTop();
            push();
            if (objArr != null) {
                for (Object obj : objArr) {
                    this.L.pushObjectValue(obj);
                }
            } else {
                i3 = 0;
            }
            int pcall = this.L.pcall(i3, i2, 0);
            if (pcall != 0) {
                if (this.L.isString(-1)) {
                    str = this.L.toString(-1);
                    this.L.pop(1);
                } else {
                    str = "";
                }
                if (pcall == 2) {
                    sb = new StringBuilder();
                    sb.append("Runtime error. ");
                    sb.append(str);
                } else if (pcall == 4) {
                    sb = new StringBuilder();
                    sb.append("Memory allocation error. ");
                    sb.append(str);
                } else {
                    if (pcall != 6) {
                        str2 = "Lua Error code " + pcall + ". " + str;
                        throw new LuaException(str2);
                    }
                    sb = new StringBuilder();
                    sb.append("Error while running the error handler function. ");
                    sb.append(str);
                }
                str2 = sb.toString();
                throw new LuaException(str2);
            }
            if (i2 == -1) {
                i2 = this.L.getTop() - top;
            }
            if (this.L.getTop() - top < i2) {
                throw new LuaException("Invalid Number of Results .");
            }
            objArr2 = new Object[i2];
            while (i2 > 0) {
                objArr2[i2 - 1] = this.L.toJavaObject(-1);
                this.L.pop(1);
                i2--;
            }
        }
        return objArr2;
    }

    public void callxNoErr() {
        try {
            callx();
        } catch (LuaException e) {
            e.printStackTrace();
            t.P2(null, "VoidMethod LuaErr：\n" + e.getMessage());
        }
    }

    public Object createProxy(Class cls) {
        Object newProxyInstance;
        synchronized (this.L) {
            if (!isTable()) {
                throw new LuaException("Invalid Object. Must be Table.");
            }
            newProxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{cls}, new LuaInvocationHandler(this));
        }
        return newProxyInstance;
    }

    public Object createProxy(String str) {
        Object newProxyInstance;
        synchronized (this.L) {
            if (!isTable()) {
                throw new LuaException("Invalid Object. Must be Table.");
            }
            StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
            Class[] clsArr = new Class[stringTokenizer.countTokens()];
            int i2 = 0;
            while (stringTokenizer.hasMoreTokens()) {
                clsArr[i2] = Class.forName(stringTokenizer.nextToken());
                i2++;
            }
            newProxyInstance = Proxy.newProxyInstance(getClass().getClassLoader(), clsArr, new LuaInvocationHandler(this));
        }
        return newProxyInstance;
    }

    protected void finalize() {
        try {
            synchronized (this.L) {
                if (this.L.getCPtrPeer() != 0) {
                    this.L.LunRef(LuaState.LUA_REGISTRYINDEX, this.ref.intValue());
                }
            }
        } catch (Exception unused) {
            System.err.println("Unable to release object " + this.ref);
        }
    }

    public boolean getBoolean() {
        boolean z;
        synchronized (this.L) {
            push();
            z = this.L.toBoolean(-1);
            this.L.pop(1);
        }
        return z;
    }

    public LuaObject getField(String str) {
        return this.L.getLuaObject(this, str);
    }

    public LuaState getLuaState() {
        return this.L;
    }

    public double getNumber() {
        double number;
        synchronized (this.L) {
            push();
            number = this.L.toNumber(-1);
            this.L.pop(1);
        }
        return number;
    }

    public Object getObject() {
        Object objectFromUserdata;
        synchronized (this.L) {
            push();
            objectFromUserdata = this.L.getObjectFromUserdata(-1);
            this.L.pop(1);
        }
        return objectFromUserdata;
    }

    public String getString() {
        String luaState;
        synchronized (this.L) {
            push();
            luaState = this.L.toString(-1);
            this.L.pop(1);
        }
        return luaState;
    }

    public boolean isBoolean() {
        boolean isBoolean;
        synchronized (this.L) {
            push();
            isBoolean = this.L.isBoolean(-1);
            this.L.pop(1);
        }
        return isBoolean;
    }

    public boolean isFunction() {
        boolean isFunction;
        synchronized (this.L) {
            push();
            isFunction = this.L.isFunction(-1);
            this.L.pop(1);
        }
        return isFunction;
    }

    public boolean isJavaFunction() {
        boolean isJavaFunction;
        synchronized (this.L) {
            push();
            isJavaFunction = this.L.isJavaFunction(-1);
            this.L.pop(1);
        }
        return isJavaFunction;
    }

    public boolean isJavaObject() {
        boolean isObject;
        synchronized (this.L) {
            push();
            isObject = this.L.isObject(-1);
            this.L.pop(1);
        }
        return isObject;
    }

    public boolean isNil() {
        boolean isNil;
        synchronized (this.L) {
            push();
            isNil = this.L.isNil(-1);
            this.L.pop(1);
        }
        return isNil;
    }

    public boolean isNumber() {
        boolean isNumber;
        synchronized (this.L) {
            push();
            isNumber = this.L.isNumber(-1);
            this.L.pop(1);
        }
        return isNumber;
    }

    public boolean isString() {
        boolean isString;
        synchronized (this.L) {
            push();
            isString = this.L.isString(-1);
            this.L.pop(1);
        }
        return isString;
    }

    public boolean isTable() {
        boolean isTable;
        synchronized (this.L) {
            push();
            isTable = this.L.isTable(-1);
            this.L.pop(1);
        }
        return isTable;
    }

    public boolean isUserdata() {
        boolean isUserdata;
        synchronized (this.L) {
            push();
            isUserdata = this.L.isUserdata(-1);
            this.L.pop(1);
        }
        return isUserdata;
    }

    public void push() {
        this.L.rawGetI(LuaState.LUA_REGISTRYINDEX, this.ref.intValue());
    }

    public String toString() {
        synchronized (this.L) {
            try {
                try {
                    if (isNil()) {
                        return "nil";
                    }
                    if (isBoolean()) {
                        return String.valueOf(getBoolean());
                    }
                    if (isNumber()) {
                        return String.valueOf(getNumber());
                    }
                    if (isString()) {
                        return getString();
                    }
                    if (isFunction()) {
                        return "Lua Function";
                    }
                    if (isJavaObject()) {
                        return getObject().toString();
                    }
                    if (isUserdata()) {
                        return "Userdata";
                    }
                    if (isTable()) {
                        return "Lua Table";
                    }
                    if (isJavaFunction()) {
                        return "Java Function";
                    }
                    return null;
                } catch (LuaException unused) {
                    return null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int type() {
        int type;
        synchronized (this.L) {
            push();
            type = this.L.type(-1);
            this.L.pop(1);
        }
        return type;
    }
}
