package c.b.a.a;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaState;

/* loaded from: classes.dex */
public class j {
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004d, code lost:
    
        if (r7.isAssignableFrom(org.keplerproject.luajava.LuaObject.class) == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0050, code lost:
    
        r2 = r6.getLuaObject(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005f, code lost:
    
        if (r7.isAssignableFrom(org.keplerproject.luajava.LuaObject.class) == false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0076, code lost:
    
        if (r2 == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x009a, code lost:
    
        if (r7.isAssignableFrom(org.keplerproject.luajava.LuaObject.class) == false) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object a(org.keplerproject.luajava.LuaState r6, java.lang.Class r7, int r8) {
        /*
            java.lang.Class<org.keplerproject.luajava.LuaObject> r0 = org.keplerproject.luajava.LuaObject.class
            boolean r1 = r6.isBoolean(r8)
            r2 = 0
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L2b
            boolean r0 = r7.isPrimitive()
            if (r0 == 0) goto L16
            java.lang.Class r0 = java.lang.Boolean.TYPE
            if (r7 == r0) goto L1f
            goto L20
        L16:
            java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
            boolean r7 = r7.isAssignableFrom(r0)
            if (r7 != 0) goto L1f
            goto L20
        L1f:
            r3 = 1
        L20:
            java.lang.Boolean r2 = new java.lang.Boolean
            boolean r6 = r6.toBoolean(r8)
            r2.<init>(r6)
            goto La4
        L2b:
            int r1 = r6.type(r8)
            r5 = 4
            if (r1 != r5) goto L43
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            boolean r7 = r7.isAssignableFrom(r0)
            if (r7 != 0) goto L3c
            goto La4
        L3c:
            java.lang.String r2 = r6.toString(r8)
        L40:
            r3 = 1
            goto La4
        L43:
            boolean r1 = r6.isFunction(r8)
            if (r1 == 0) goto L55
            boolean r7 = r7.isAssignableFrom(r0)
            if (r7 != 0) goto L50
            goto La4
        L50:
            org.keplerproject.luajava.LuaObject r2 = r6.getLuaObject(r8)
            goto L40
        L55:
            boolean r1 = r6.isTable(r8)
            if (r1 == 0) goto L62
            boolean r7 = r7.isAssignableFrom(r0)
            if (r7 != 0) goto L50
            goto La4
        L62:
            int r1 = r6.type(r8)
            r5 = 3
            if (r1 != r5) goto L79
            java.lang.Double r0 = new java.lang.Double
            double r1 = r6.toNumber(r8)
            r0.<init>(r1)
            java.lang.Number r2 = org.keplerproject.luajava.LuaState.convertLuaNumber(r0, r7)
            if (r2 != 0) goto L40
            goto La4
        L79:
            boolean r1 = r6.isUserdata(r8)
            if (r1 == 0) goto L9d
            boolean r1 = r6.isObject(r8)
            if (r1 == 0) goto L96
            java.lang.Object r6 = r6.getObjectFromUserdata(r8)
            java.lang.Class r8 = r6.getClass()
            boolean r3 = r7.isAssignableFrom(r8)
            if (r3 != 0) goto L94
            goto La4
        L94:
            r2 = r6
            goto La4
        L96:
            boolean r7 = r7.isAssignableFrom(r0)
            if (r7 != 0) goto L50
            goto La4
        L9d:
            boolean r6 = r6.isNil(r8)
            if (r6 == 0) goto Laf
            goto L40
        La4:
            if (r3 == 0) goto La7
            return r2
        La7:
            org.keplerproject.luajava.LuaException r6 = new org.keplerproject.luajava.LuaException
            java.lang.String r7 = "Invalid Parameter."
            r6.<init>(r7)
            throw r6
        Laf:
            org.keplerproject.luajava.LuaException r6 = new org.keplerproject.luajava.LuaException
            java.lang.String r7 = "Invalid Parameters."
            r6.<init>(r7)
            goto Lb8
        Lb7:
            throw r6
        Lb8:
            goto Lb7
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.a.j.a(org.keplerproject.luajava.LuaState, java.lang.Class, int):java.lang.Object");
    }

    private static int b(LuaState luaState, Object obj, String str, Method[] methodArr, boolean z) {
        synchronized (luaState) {
            String str2 = "setOn" + str.substring(2) + "Listener";
            for (Method method : methodArr) {
                if (method.getName().equals(str2) && (!z || Modifier.isStatic(method.getModifiers()))) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1 && parameterTypes[0].isInterface()) {
                        luaState.newTable();
                        luaState.pushValue(-2);
                        luaState.setField(-2, str);
                        try {
                            method.invoke(obj, luaState.getLuaObject(-1).createProxy(parameterTypes[0]));
                            return 1;
                        } catch (Exception e) {
                            throw new LuaException(e);
                        }
                    }
                }
            }
            return 0;
        }
    }

    private static int c(LuaState luaState, Object obj, String str, Method[] methodArr, boolean z) {
        synchronized (luaState) {
            String str2 = "set" + str;
            StringBuilder sb = new StringBuilder();
            for (Method method : methodArr) {
                if (method.getName().equals(str2) && (!z || Modifier.isStatic(method.getModifiers()))) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 1) {
                        continue;
                    } else {
                        try {
                            try {
                                method.invoke(obj, a(luaState, parameterTypes[0], -1));
                                return 1;
                            } catch (Exception e) {
                                throw new LuaException(e);
                            }
                        } catch (LuaException unused) {
                            sb.append(parameterTypes[0]);
                            sb.append("\n");
                        }
                    }
                }
            }
            if (sb.length() <= 0) {
                return 0;
            }
            throw new LuaException("Invalid setter " + str + ". Invalid Parameters.\n" + sb.toString() + luaState.typeName(-1));
        }
    }

    public static int d(LuaState luaState, Object obj, String str) {
        Class<?> cls;
        synchronized (luaState) {
            boolean z = true;
            if (obj instanceof Map) {
                ((Map) obj).put(str, luaState.toJavaObject(2));
                return 1;
            }
            if (obj instanceof Class) {
                cls = (Class) obj;
            } else {
                cls = obj.getClass();
                z = false;
            }
            cls.getName();
            Method[] methods = cls.getMethods();
            if (str.length() > 2 && str.substring(0, 2).equals("on") && luaState.type(-1) == 6) {
                return b(luaState, obj, str, methods, z);
            }
            return c(luaState, obj, str, methods, z);
        }
    }

    public static int e(LuaState luaState, Object obj, String str) {
        Class<?> cls;
        boolean z;
        synchronized (luaState) {
            if (obj == null) {
                return 0;
            }
            if (obj instanceof Class) {
                cls = (Class) obj;
                z = true;
            } else {
                cls = obj.getClass();
                z = false;
            }
            try {
                Field field = cls.getField(str);
                if (field == null) {
                    return 0;
                }
                if (z && !Modifier.isStatic(field.getModifiers())) {
                    return 0;
                }
                Class<?> type = field.getType();
                try {
                    try {
                        if (!Modifier.isPublic(field.getModifiers())) {
                            field.setAccessible(true);
                        }
                        field.set(obj, a(luaState, type, 3));
                        return 1;
                    } catch (LuaException unused) {
                        throw new LuaException("bad argument to '" + str + "' (" + type.getName() + " expected, got " + f(luaState, 3) + " value)");
                    }
                } catch (Exception e) {
                    throw new LuaException(e);
                }
            } catch (NoSuchFieldException unused2) {
                return 0;
            }
        }
    }

    public static String f(LuaState luaState, int i2) {
        if (luaState.isObject(i2)) {
            return luaState.getObjectFromUserdata(i2).getClass().getName();
        }
        switch (luaState.type(i2)) {
            case 1:
                return "boolean";
            case 2:
            case 7:
                return "userdata";
            case 3:
                return "number";
            case 4:
                return "string";
            case 5:
                return "table";
            case 6:
                return "function";
            case 8:
                return "thread";
            default:
                return "unkown";
        }
    }
}
