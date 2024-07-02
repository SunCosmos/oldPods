package org.keplerproject.luajava;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class LuaInvocationHandler implements InvocationHandler {
    private LuaObject obj;

    public LuaInvocationHandler(LuaObject luaObject) {
        this.obj = luaObject;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        synchronized (this.obj.L) {
            LuaObject field = this.obj.getField(method.getName());
            Object obj2 = null;
            if (field.isNil()) {
                return null;
            }
            Class<?> returnType = method.getReturnType();
            if (!returnType.equals(Void.class) && !returnType.equals(Void.TYPE)) {
                obj2 = field.call(objArr, 1)[0];
                if (obj2 != null && (obj2 instanceof Double)) {
                    obj2 = LuaState.convertLuaNumber((Double) obj2, returnType);
                }
                return obj2;
            }
            field.call(objArr, 0);
            return obj2;
        }
    }
}
