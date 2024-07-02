package org.keplerproject.luajava;

/* loaded from: classes.dex */
public class LuaException extends Exception {
    private static final long serialVersionUID = 1;

    public LuaException(Exception exc) {
        super(exc.getCause() != null ? exc.getCause() : exc);
    }

    public LuaException(String str) {
        super(str);
    }
}
