package org.keplerproject.luajava;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class LuaStateFactory {
    private static final String LUAJAVA_LIB = "luajava";
    public static byte[] a;
    private static final boolean isLuaLibLoaded;
    private static final List<Object> states;

    static {
        boolean z;
        try {
            System.loadLibrary(LUAJAVA_LIB);
            z = true;
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            z = false;
        }
        isLuaLibLoaded = z;
        states = new ArrayList();
    }

    private LuaStateFactory() {
    }

    public static synchronized LuaState getExistingState(int i2) {
        LuaState luaState;
        synchronized (LuaStateFactory.class) {
            luaState = (LuaState) states.get(i2);
        }
        return luaState;
    }

    private static synchronized int getNextStateIndex() {
        int i2;
        synchronized (LuaStateFactory.class) {
            i2 = 0;
            while (true) {
                List<Object> list = states;
                if (i2 >= list.size() || list.get(i2) == null) {
                    break;
                }
                i2++;
            }
        }
        return i2;
    }

    public static synchronized int insertLuaState(LuaState luaState) {
        synchronized (LuaStateFactory.class) {
            int i2 = 0;
            while (true) {
                List<Object> list = states;
                if (i2 >= list.size()) {
                    int nextStateIndex = getNextStateIndex();
                    list.set(nextStateIndex, luaState);
                    return nextStateIndex;
                }
                LuaState luaState2 = (LuaState) list.get(i2);
                if (luaState2 != null && luaState2.getCPtrPeer() == luaState.getCPtrPeer()) {
                    return i2;
                }
                i2++;
            }
        }
    }

    public static synchronized LuaState newLuaState() {
        synchronized (LuaStateFactory.class) {
            if (!isLuaLibLoaded) {
                return null;
            }
            int nextStateIndex = getNextStateIndex();
            LuaState luaState = new LuaState(nextStateIndex);
            states.add(nextStateIndex, luaState);
            return luaState;
        }
    }

    public static synchronized void removeLuaState(int i2) {
        synchronized (LuaStateFactory.class) {
            states.add(i2, null);
        }
    }
}
