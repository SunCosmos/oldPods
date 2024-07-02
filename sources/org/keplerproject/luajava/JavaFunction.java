package org.keplerproject.luajava;

/* loaded from: classes.dex */
public abstract class JavaFunction {
    protected LuaState L;

    public JavaFunction(LuaState luaState) {
        this.L = luaState;
    }

    public abstract int execute();

    public LuaObject getParam(int i2) {
        return this.L.getLuaObject(i2);
    }

    public void register(String str) {
        synchronized (this.L) {
            this.L.pushJavaFunction(this);
            this.L.setGlobal(str);
        }
    }
}
