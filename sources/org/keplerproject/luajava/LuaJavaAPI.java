package org.keplerproject.luajava;

import c.b.a.a.j;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* loaded from: classes.dex */
public final class LuaJavaAPI {
    private LuaJavaAPI() {
    }

    public static int checkField(int i2, Object obj, String str) {
        LuaState existingState = LuaStateFactory.getExistingState(i2);
        synchronized (existingState) {
            try {
                Field field = (obj instanceof Class ? (Class) obj : obj.getClass()).getField(str);
                if (field == null) {
                    return 0;
                }
                try {
                    Object obj2 = field.get(obj);
                    if (obj == null) {
                        return 0;
                    }
                    existingState.pushObjectValue(obj2);
                    return 1;
                } catch (Exception unused) {
                    return 0;
                }
            } catch (Exception unused2) {
                return 0;
            }
        }
    }

    public static int checkMethod(int i2, Object obj, String str) {
        synchronized (LuaStateFactory.getExistingState(i2)) {
            for (Method method : (obj instanceof Class ? (Class) obj : obj.getClass()).getMethods()) {
                if (method.getName().equals(str)) {
                    return 1;
                }
            }
            return 0;
        }
    }

    public static int classIndex(int i2, Class cls, String str) {
        synchronized (LuaStateFactory.getExistingState(i2)) {
            if (checkField(i2, cls, str) != 0) {
                return 1;
            }
            return checkMethod(i2, cls, str) != 0 ? 2 : 0;
        }
    }

    public static int createProxyObject(int i2, String str) {
        LuaState existingState = LuaStateFactory.getExistingState(i2);
        synchronized (existingState) {
            try {
                if (!existingState.isTable(2)) {
                    throw new LuaException("Parameter is not a table. Can't create proxy.");
                }
                existingState.pushJavaObject(existingState.getLuaObject(2).createProxy(str));
            } catch (Exception e) {
                throw new LuaException(e);
            }
        }
        return 1;
    }

    private static Object getObjInstance(LuaState luaState, Class cls) {
        Object newInstance;
        boolean z;
        synchronized (luaState) {
            int top = luaState.getTop() - 1;
            Object[] objArr = new Object[top];
            Constructor<?>[] constructors = cls.getConstructors();
            Constructor<?> constructor = null;
            int i2 = 0;
            while (true) {
                if (i2 >= constructors.length) {
                    break;
                }
                Class<?>[] parameterTypes = constructors[i2].getParameterTypes();
                if (parameterTypes.length == top) {
                    for (int i3 = 0; i3 < parameterTypes.length; i3++) {
                        try {
                            objArr[i3] = j.a(luaState, parameterTypes[i3], i3 + 2);
                        } catch (Exception unused) {
                            z = false;
                        }
                    }
                    z = true;
                    if (z) {
                        constructor = constructors[i2];
                        break;
                    }
                }
                i2++;
            }
            if (constructor == null) {
                throw new LuaException("Invalid method call. No such method.");
            }
            try {
                newInstance = constructor.newInstance(objArr);
                if (newInstance == null) {
                    throw new LuaException("Couldn't instantiate java Object");
                }
            } catch (Exception e) {
                throw new LuaException(e);
            }
        }
        return newInstance;
    }

    public static int javaLoadLib(int i2, String str, String str2) {
        LuaState existingState = LuaStateFactory.getExistingState(i2);
        synchronized (existingState) {
            try {
                try {
                    try {
                        Object invoke = Class.forName(str).getMethod(str2, LuaState.class).invoke(null, existingState);
                        if (invoke == null || !(invoke instanceof Integer)) {
                            return 0;
                        }
                        return ((Integer) invoke).intValue();
                    } catch (Exception e) {
                        throw new LuaException("Error on calling method. Library could not be loaded. " + e.getMessage());
                    }
                } catch (ClassNotFoundException e2) {
                    throw new LuaException(e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static int javaNew(int i2, Class cls) {
        LuaState existingState = LuaStateFactory.getExistingState(i2);
        synchronized (existingState) {
            existingState.pushJavaObject(getObjInstance(existingState, cls));
        }
        return 1;
    }

    public static int javaNewInstance(int i2, String str) {
        LuaState existingState = LuaStateFactory.getExistingState(i2);
        synchronized (existingState) {
            try {
                try {
                    existingState.pushJavaObject(getObjInstance(existingState, Class.forName(str)));
                } catch (ClassNotFoundException e) {
                    throw new LuaException(e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return 1;
    }

    public static int objectIndex(int i2, Object obj, String str) {
        Method method;
        boolean z;
        LuaState existingState = LuaStateFactory.getExistingState(i2);
        synchronized (existingState) {
            int top = existingState.getTop() - 1;
            Object[] objArr = new Object[top];
            Method[] methods = (obj instanceof Class ? (Class) obj : obj.getClass()).getMethods();
            int i3 = 0;
            while (true) {
                if (i3 >= methods.length) {
                    method = null;
                    break;
                }
                if (methods[i3].getName().equals(str)) {
                    Class<?>[] parameterTypes = methods[i3].getParameterTypes();
                    if (parameterTypes.length == top) {
                        for (int i4 = 0; i4 < parameterTypes.length; i4++) {
                            try {
                                objArr[i4] = j.a(existingState, parameterTypes[i4], i4 + 2);
                            } catch (Exception unused) {
                                z = false;
                            }
                        }
                        z = true;
                        if (z) {
                            method = methods[i3];
                            break;
                        }
                    }
                }
                i3++;
            }
            if (method == null) {
                throw new LuaException("Invalid method call. No such method.");
            }
            try {
                if (Modifier.isPublic(method.getModifiers())) {
                    method.setAccessible(true);
                }
                Object invoke = obj instanceof Class ? method.invoke(null, objArr) : method.invoke(obj, objArr);
                if (invoke == null) {
                    return 0;
                }
                existingState.pushObjectValue(invoke);
                return 1;
            } catch (Exception e) {
                throw new LuaException(e);
            }
        }
    }

    public static int objectIndex2(int i2, Object obj, String str) {
        LuaState existingState = LuaStateFactory.getExistingState(i2);
        synchronized (existingState) {
            if (j.e(existingState, obj, str) != 0) {
                return 1;
            }
            return j.d(existingState, obj, str) != 0 ? 1 : 0;
        }
    }

    public static int setsz(int i2, Object obj, int i3) {
        LuaState existingState = LuaStateFactory.getExistingState(i2);
        synchronized (existingState) {
            Class<?> componentType = obj.getClass().getComponentType();
            if (componentType == null) {
                throw new LuaException(obj.toString() + " is not a array");
            }
            try {
                Array.set(obj, i3, j.a(existingState, componentType, 3));
            } catch (LuaException unused) {
                throw new LuaException("bad argument to '" + obj.getClass().getName() + "' (" + componentType.getName() + " expected, got " + j.f(existingState, 3) + " value)");
            } catch (Exception e) {
                throw new LuaException("can not set array value: " + e.getMessage());
            }
        }
        return 0;
    }
}
