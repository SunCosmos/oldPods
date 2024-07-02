package org.keplerproject.luajava;

/* loaded from: classes.dex */
public class LuaState {
    public static final int LUA_ERRERR = 6;
    public static final int LUA_ERRGCMM = 5;
    public static final int LUA_ERRMEM = 4;
    public static final int LUA_ERRRUN = 2;
    public static final int LUA_ERRSYNTAX = 3;
    public static final int LUA_MINSTACK = 20;
    public static final int LUA_MULTRET = -1;
    public static final int LUA_NUMTAGS = 9;
    public static final int LUA_OK = 0;
    public static final int LUA_TBOOLEAN = 1;
    public static final int LUA_TFUNCTION = 6;
    public static final int LUA_TLIGHTUSERDATA = 2;
    public static final int LUA_TNIL = 0;
    public static final int LUA_TNONE = -1;
    public static final int LUA_TNUMBER = 3;
    public static final int LUA_TSTRING = 4;
    public static final int LUA_TTABLE = 5;
    public static final int LUA_TTHREAD = 8;
    public static final int LUA_TUSERDATA = 7;
    public static final int LUA_YIELD = 1;
    private static final double zs_d = 0.09394d;
    private static final double zs_x = 0.09392d;
    private CPtr luaState;
    private int stateId;
    public static final int LUA_REGISTRYINDEX = _registryIndex();
    public static final Integer LUA_GCSTOP = new Integer(0);
    public static final Integer LUA_GCRESTART = new Integer(1);
    public static final Integer LUA_GCCOLLECT = new Integer(2);
    public static final Integer LUA_GCCOUNT = new Integer(3);
    public static final Integer LUA_GCCOUNTB = new Integer(4);
    public static final Integer LUA_GCSTEP = new Integer(5);
    public static final Integer LUA_GCSETPAUSE = new Integer(6);
    public static final Integer LUA_GCSETSTEPMUL = new Integer(7);

    /* JADX INFO: Access modifiers changed from: protected */
    public LuaState(int i2) {
        CPtr cPtr = (CPtr) _open();
        this.luaState = cPtr;
        _luajavaOpen(cPtr, i2);
        this.stateId = i2;
    }

    protected LuaState(CPtr cPtr) {
        this.luaState = cPtr;
        int insertLuaState = LuaStateFactory.insertLuaState(this);
        this.stateId = insertLuaState;
        _luajavaOpen(cPtr, insertLuaState);
    }

    private native synchronized int _LargError(CPtr cPtr, int i2, String str);

    private native synchronized int _LcallMeta(CPtr cPtr, int i2, String str);

    private native synchronized void _LcheckAny(CPtr cPtr, int i2);

    private native synchronized int _LcheckInteger(CPtr cPtr, int i2);

    private native synchronized double _LcheckNumber(CPtr cPtr, int i2);

    private native synchronized void _LcheckStack(CPtr cPtr, int i2, String str);

    private native synchronized String _LcheckString(CPtr cPtr, int i2);

    private native synchronized void _LcheckType(CPtr cPtr, int i2, int i3);

    private native synchronized int _LdoFile(CPtr cPtr, String str);

    private native synchronized int _LdoString(CPtr cPtr, String str);

    private native synchronized int _LgetMetaField(CPtr cPtr, int i2, String str);

    private native synchronized void _LgetMetatable(CPtr cPtr, String str);

    private native synchronized String _Lgsub(CPtr cPtr, String str, String str2, String str3);

    private native synchronized int _LloadBuffer(CPtr cPtr, byte[] bArr, long j, String str);

    private native synchronized int _LloadFile(CPtr cPtr, String str);

    private native synchronized int _LloadString(CPtr cPtr, String str);

    private native synchronized int _LnewMetatable(CPtr cPtr, String str);

    private native synchronized int _LoptInteger(CPtr cPtr, int i2, int i3);

    private native synchronized double _LoptNumber(CPtr cPtr, int i2, double d2);

    private native synchronized String _LoptString(CPtr cPtr, int i2, String str);

    private native synchronized int _Lref(CPtr cPtr, int i2);

    private native synchronized void _LunRef(CPtr cPtr, int i2, int i3);

    private native synchronized void _Lwhere(CPtr cPtr, int i2);

    private native synchronized void _call(CPtr cPtr, int i2, int i3);

    private native synchronized int _checkStack(CPtr cPtr, int i2);

    private native synchronized void _close(CPtr cPtr);

    private native synchronized void _concat(CPtr cPtr, int i2);

    private native synchronized void _createTable(CPtr cPtr, int i2, int i3);

    private native synchronized int _equal(CPtr cPtr, int i2, int i3);

    private native synchronized int _error(CPtr cPtr);

    private native synchronized int _gc(CPtr cPtr, int i2, int i3);

    private native synchronized void _getField(CPtr cPtr, int i2, String str);

    private native synchronized void _getGlobal(CPtr cPtr, String str);

    private native synchronized int _getMetaTable(CPtr cPtr, int i2);

    private native synchronized Object _getObjectFromUserdata(CPtr cPtr, int i2);

    private native synchronized void _getTable(CPtr cPtr, int i2);

    private native synchronized int _getTop(CPtr cPtr);

    private native synchronized void _insert(CPtr cPtr, int i2);

    private native synchronized int _isBoolean(CPtr cPtr, int i2);

    private native synchronized int _isCFunction(CPtr cPtr, int i2);

    private native synchronized int _isFunction(CPtr cPtr, int i2);

    private native synchronized boolean _isJavaFunction(CPtr cPtr, int i2);

    private native synchronized int _isNil(CPtr cPtr, int i2);

    private native synchronized int _isNone(CPtr cPtr, int i2);

    private native synchronized int _isNoneOrNil(CPtr cPtr, int i2);

    private native synchronized int _isNumber(CPtr cPtr, int i2);

    private native synchronized boolean _isObject(CPtr cPtr, int i2);

    private native synchronized int _isString(CPtr cPtr, int i2);

    private native synchronized int _isTable(CPtr cPtr, int i2);

    private native synchronized int _isThread(CPtr cPtr, int i2);

    private native synchronized int _isUserdata(CPtr cPtr, int i2);

    private native synchronized int _lessthan(CPtr cPtr, int i2, int i3);

    private native synchronized void _luajavaOpen(CPtr cPtr, int i2);

    private native synchronized void _newTable(CPtr cPtr);

    private native synchronized Object _newthread(CPtr cPtr);

    private native synchronized int _next(CPtr cPtr, int i2);

    private native synchronized int _objlen(CPtr cPtr, int i2);

    private native synchronized int _objlen2(CPtr cPtr, int i2);

    private native synchronized Object _open();

    private native synchronized void _openBase(CPtr cPtr);

    private native synchronized void _openDebug(CPtr cPtr);

    private native synchronized void _openIo(CPtr cPtr);

    private native synchronized void _openLibs(CPtr cPtr);

    private native synchronized void _openMath(CPtr cPtr);

    private native synchronized void _openOs(CPtr cPtr);

    private native synchronized void _openPackage(CPtr cPtr);

    private native synchronized void _openString(CPtr cPtr);

    private native synchronized void _openTable(CPtr cPtr);

    private native synchronized int _pcall(CPtr cPtr, int i2, int i3, int i4);

    private native synchronized void _pop(CPtr cPtr, int i2);

    private native synchronized void _pushBoolean(CPtr cPtr, int i2);

    private native synchronized void _pushInteger(CPtr cPtr, int i2);

    private native synchronized void _pushJavaFunction(CPtr cPtr, Object obj);

    private native synchronized void _pushJavaObject(CPtr cPtr, Object obj);

    private native synchronized void _pushNil(CPtr cPtr);

    private native synchronized void _pushNumber(CPtr cPtr, double d2);

    private native synchronized void _pushString(CPtr cPtr, String str);

    private native synchronized void _pushString2(CPtr cPtr, byte[] bArr, int i2);

    private native synchronized void _pushValue(CPtr cPtr, int i2);

    private native synchronized void _rawGet(CPtr cPtr, int i2);

    private native synchronized void _rawGetI(CPtr cPtr, int i2, int i3);

    private native synchronized void _rawSet(CPtr cPtr, int i2);

    private native synchronized void _rawSetI(CPtr cPtr, int i2, int i3);

    private native synchronized int _rawequal(CPtr cPtr, int i2, int i3);

    private static native int _registryIndex();

    private native synchronized void _remove(CPtr cPtr, int i2);

    private native synchronized void _replace(CPtr cPtr, int i2);

    private native synchronized int _resume(CPtr cPtr, CPtr cPtr2, int i2);

    private native synchronized void _setField(CPtr cPtr, int i2, String str);

    private native synchronized void _setGlobal(CPtr cPtr, String str);

    private native synchronized int _setMetaTable(CPtr cPtr, int i2);

    private native synchronized void _setTable(CPtr cPtr, int i2);

    private native synchronized void _setTop(CPtr cPtr, int i2);

    private native synchronized int _status(CPtr cPtr);

    private native synchronized int _strlen(CPtr cPtr, int i2);

    private native synchronized int _toBoolean(CPtr cPtr, int i2);

    private native synchronized int _toInteger(CPtr cPtr, int i2);

    private native synchronized double _toNumber(CPtr cPtr, int i2);

    private native synchronized String _toString(CPtr cPtr, int i2);

    private native synchronized Object _toThread(CPtr cPtr, int i2);

    private native synchronized int _type(CPtr cPtr, int i2);

    private native synchronized String _typeName(CPtr cPtr, int i2);

    private native synchronized void _xmove(CPtr cPtr, CPtr cPtr2, int i2);

    private native synchronized int _yield(CPtr cPtr, int i2);

    public static Number convertLuaNumber(Double d2, Class cls) {
        if (cls.isPrimitive()) {
            return cls == Integer.TYPE ? new Integer(d2.intValue()) : cls == Long.TYPE ? new Long(d2.longValue()) : cls == Float.TYPE ? new Float(d2.floatValue()) : cls == Double.TYPE ? d2 : cls == Byte.TYPE ? new Byte(d2.byteValue()) : cls == Short.TYPE ? new Short(d2.shortValue()) : d2;
        }
        if (!cls.isAssignableFrom(Number.class)) {
            return null;
        }
        long longValue = d2.longValue();
        double doubleValue = d2.doubleValue();
        double d3 = longValue;
        Double.isNaN(d3);
        double d4 = doubleValue - d3;
        if (d4 < 0.0d) {
            d4 = -d4;
        }
        return (d4 <= zs_x || d4 >= zs_d) ? (d2.doubleValue() <= 1.401298464324817E-45d || d2.doubleValue() >= 3.4028234663852886E38d) ? Double.valueOf(d2.doubleValue()) : Float.valueOf(d2.floatValue()) : (longValue <= -2147483648L || longValue >= 2147483647L) ? Long.valueOf(longValue) : Integer.valueOf(d2.intValue());
    }

    public int LargError(int i2, String str) {
        return _LargError(this.luaState, i2, str);
    }

    public int LcallMeta(int i2, String str) {
        return _LcallMeta(this.luaState, i2, str);
    }

    public void LcheckAny(int i2) {
        _LcheckAny(this.luaState, i2);
    }

    public int LcheckInteger(int i2) {
        return _LcheckInteger(this.luaState, i2);
    }

    public double LcheckNumber(int i2) {
        return _LcheckNumber(this.luaState, i2);
    }

    public void LcheckStack(int i2, String str) {
        _LcheckStack(this.luaState, i2, str);
    }

    public String LcheckString(int i2) {
        return _LcheckString(this.luaState, i2);
    }

    public void LcheckType(int i2, int i3) {
        _LcheckType(this.luaState, i2, i3);
    }

    public int LdoFile(String str) {
        return _LdoFile(this.luaState, str);
    }

    public int LdoString(String str) {
        return _LdoString(this.luaState, str);
    }

    public int LgetMetaField(int i2, String str) {
        return _LgetMetaField(this.luaState, i2, str);
    }

    public void LgetMetatable(String str) {
        _LgetMetatable(this.luaState, str);
    }

    public String Lgsub(String str, String str2, String str3) {
        return _Lgsub(this.luaState, str, str2, str3);
    }

    public int LloadBuffer(byte[] bArr, String str) {
        return _LloadBuffer(this.luaState, bArr, bArr.length, str);
    }

    public int LloadFile(String str) {
        return _LloadFile(this.luaState, str);
    }

    public int LloadString(String str) {
        return _LloadString(this.luaState, str);
    }

    public int LnewMetatable(String str) {
        return _LnewMetatable(this.luaState, str);
    }

    public int LoptInteger(int i2, int i3) {
        return _LoptInteger(this.luaState, i2, i3);
    }

    public double LoptNumber(int i2, double d2) {
        return _LoptNumber(this.luaState, i2, d2);
    }

    public String LoptString(int i2, String str) {
        return _LoptString(this.luaState, i2, str);
    }

    public int Lref(int i2) {
        return _Lref(this.luaState, i2);
    }

    public void LunRef(int i2, int i3) {
        _LunRef(this.luaState, i2, i3);
    }

    public void Lwhere(int i2) {
        _Lwhere(this.luaState, i2);
    }

    public void call(int i2, int i3) {
        _call(this.luaState, i2, i3);
    }

    public int checkStack(int i2) {
        return _checkStack(this.luaState, i2);
    }

    public synchronized void close() {
        LuaStateFactory.removeLuaState(this.stateId);
        _close(this.luaState);
        this.luaState = null;
    }

    public void concat(int i2) {
        _concat(this.luaState, i2);
    }

    public void createTable(int i2, int i3) {
        _createTable(this.luaState, i2, i3);
    }

    public String dumpStack() {
        int top = getTop();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 1; i2 <= top; i2++) {
            int type = type(i2);
            sb.append(i2);
            sb.append(": ");
            sb.append(typeName(type));
            if (type == 3) {
                sb.append(" = ");
                sb.append(toNumber(i2));
            } else if (type == 4) {
                sb.append(" = '");
                sb.append(toString(i2));
                sb.append("'");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int equal(int i2, int i3) {
        return _equal(this.luaState, i2, i3);
    }

    public int error() {
        return _error(this.luaState);
    }

    public int gc(int i2, int i3) {
        return _gc(this.luaState, i2, i3);
    }

    public long getCPtrPeer() {
        CPtr cPtr = this.luaState;
        if (cPtr != null) {
            return cPtr.getPeer();
        }
        return 0L;
    }

    public void getField(int i2, String str) {
        _getField(this.luaState, i2, str);
    }

    public synchronized void getGlobal(String str) {
        _getGlobal(this.luaState, str);
    }

    public LuaObject getLuaObject(int i2) {
        return new LuaObject(this, i2);
    }

    public LuaObject getLuaObject(String str) {
        return new LuaObject(this, str);
    }

    public LuaObject getLuaObject(LuaObject luaObject, Number number) {
        if (luaObject.L.getCPtrPeer() == this.luaState.getPeer()) {
            return new LuaObject(luaObject, number);
        }
        throw new LuaException("Object must have the same LuaState as the parent!");
    }

    public LuaObject getLuaObject(LuaObject luaObject, String str) {
        if (luaObject.L.getCPtrPeer() == this.luaState.getPeer()) {
            return new LuaObject(luaObject, str);
        }
        throw new LuaException("Object must have the same LuaState as the parent!");
    }

    public LuaObject getLuaObject(LuaObject luaObject, LuaObject luaObject2) {
        if (luaObject.getLuaState().getCPtrPeer() == this.luaState.getPeer() && luaObject.getLuaState().getCPtrPeer() == luaObject2.getLuaState().getCPtrPeer()) {
            return new LuaObject(luaObject, luaObject2);
        }
        throw new LuaException("Object must have the same LuaState as the parent!");
    }

    public int getMetaTable(int i2) {
        return _getMetaTable(this.luaState, i2);
    }

    public Object getObjectFromUserdata(int i2) {
        return _getObjectFromUserdata(this.luaState, i2);
    }

    public void getTable(int i2) {
        _getTable(this.luaState, i2);
    }

    public int getTop() {
        return _getTop(this.luaState);
    }

    public void insert(int i2) {
        _insert(this.luaState, i2);
    }

    public boolean isBoolean(int i2) {
        return _isBoolean(this.luaState, i2) != 0;
    }

    public boolean isCFunction(int i2) {
        return _isCFunction(this.luaState, i2) != 0;
    }

    public synchronized boolean isClosed() {
        return this.luaState == null;
    }

    public boolean isFunction(int i2) {
        return _isFunction(this.luaState, i2) != 0;
    }

    public boolean isJavaFunction(int i2) {
        return _isJavaFunction(this.luaState, i2);
    }

    public boolean isNil(int i2) {
        return _isNil(this.luaState, i2) != 0;
    }

    public boolean isNone(int i2) {
        return _isNone(this.luaState, i2) != 0;
    }

    public boolean isNoneOrNil(int i2) {
        return _isNoneOrNil(this.luaState, i2) != 0;
    }

    public boolean isNumber(int i2) {
        return _isNumber(this.luaState, i2) != 0;
    }

    public boolean isObject(int i2) {
        return _isObject(this.luaState, i2);
    }

    public boolean isString(int i2) {
        return _isString(this.luaState, i2) != 0;
    }

    public boolean isTable(int i2) {
        return _isTable(this.luaState, i2) != 0;
    }

    public boolean isThread(int i2) {
        return _isThread(this.luaState, i2) != 0;
    }

    public boolean isUserdata(int i2) {
        return _isUserdata(this.luaState, i2) != 0;
    }

    public int lessthan(int i2, int i3) {
        return _lessthan(this.luaState, i2, i3);
    }

    public void newTable() {
        _newTable(this.luaState);
    }

    public LuaState newThread() {
        LuaState luaState = new LuaState((CPtr) _newthread(this.luaState));
        LuaStateFactory.insertLuaState(luaState);
        return luaState;
    }

    public int next(int i2) {
        return _next(this.luaState, i2);
    }

    public int objLen(int i2) {
        return _objlen(this.luaState, i2);
    }

    public int objLen2(int i2) {
        return _objlen2(this.luaState, i2);
    }

    public void openBase() {
        _openBase(this.luaState);
    }

    public void openDebug() {
        _openDebug(this.luaState);
    }

    public void openIo() {
        _openIo(this.luaState);
    }

    public void openLibs() {
        _openLibs(this.luaState);
    }

    public void openMath() {
        _openMath(this.luaState);
    }

    public void openOs() {
        _openOs(this.luaState);
    }

    public void openPackage() {
        _openPackage(this.luaState);
    }

    public void openString() {
        _openString(this.luaState);
    }

    public void openTable() {
        _openTable(this.luaState);
    }

    public int pcall(int i2, int i3, int i4) {
        return _pcall(this.luaState, i2, i3, i4);
    }

    public void pop(int i2) {
        _pop(this.luaState, i2);
    }

    public void pushBoolean(boolean z) {
        _pushBoolean(this.luaState, z ? 1 : 0);
    }

    public void pushInteger(int i2) {
        _pushInteger(this.luaState, i2);
    }

    public void pushJavaFunction(JavaFunction javaFunction) {
        _pushJavaFunction(this.luaState, javaFunction);
    }

    public void pushJavaObject(Object obj) {
        _pushJavaObject(this.luaState, obj);
    }

    public void pushNil() {
        _pushNil(this.luaState);
    }

    public void pushNumber(double d2) {
        _pushNumber(this.luaState, d2);
    }

    public void pushObjectValue(Object obj) {
        if (obj == null) {
            pushNil();
            return;
        }
        if (obj instanceof Boolean) {
            pushBoolean(((Boolean) obj).booleanValue());
            return;
        }
        if (obj instanceof Number) {
            pushNumber(((Number) obj).doubleValue());
            return;
        }
        if (obj instanceof String) {
            pushString((String) obj);
            return;
        }
        if (obj instanceof JavaFunction) {
            pushJavaFunction((JavaFunction) obj);
            return;
        }
        if (obj instanceof LuaObject) {
            ((LuaObject) obj).push();
        } else if (obj instanceof byte[]) {
            pushString((byte[]) obj);
        } else {
            pushJavaObject(obj);
        }
    }

    public void pushString(String str) {
        if (str == null) {
            _pushNil(this.luaState);
        } else {
            _pushString(this.luaState, str);
        }
    }

    public void pushString(byte[] bArr) {
        if (bArr == null) {
            _pushNil(this.luaState);
        } else {
            _pushString2(this.luaState, bArr, bArr.length);
        }
    }

    public void pushValue(int i2) {
        _pushValue(this.luaState, i2);
    }

    public void rawGet(int i2) {
        _rawGet(this.luaState, i2);
    }

    public void rawGetI(int i2, int i3) {
        _rawGetI(this.luaState, i2, i3);
    }

    public void rawSet(int i2) {
        _rawSet(this.luaState, i2);
    }

    public void rawSetI(int i2, int i3) {
        _rawSetI(this.luaState, i2, i3);
    }

    public int rawequal(int i2, int i3) {
        return _rawequal(this.luaState, i2, i3);
    }

    public void remove(int i2) {
        _remove(this.luaState, i2);
    }

    public void replace(int i2) {
        _replace(this.luaState, i2);
    }

    public int resume(CPtr cPtr, int i2) {
        return _resume(this.luaState, cPtr, i2);
    }

    public void setField(int i2, String str) {
        _setField(this.luaState, i2, str);
    }

    public synchronized void setGlobal(String str) {
        _setGlobal(this.luaState, str);
    }

    public int setMetaTable(int i2) {
        return _setMetaTable(this.luaState, i2);
    }

    public void setTable(int i2) {
        _setTable(this.luaState, i2);
    }

    public void setTop(int i2) {
        _setTop(this.luaState, i2);
    }

    public int status() {
        return _status(this.luaState);
    }

    public int strLen(int i2) {
        return _strlen(this.luaState, i2);
    }

    public boolean toBoolean(int i2) {
        return _toBoolean(this.luaState, i2) != 0;
    }

    public int toInteger(int i2) {
        return _toInteger(this.luaState, i2);
    }

    public synchronized Object toJavaObject(int i2) {
        Object obj;
        obj = null;
        if (isBoolean(i2)) {
            obj = new Boolean(toBoolean(i2));
        } else if (type(i2) == 4) {
            obj = toString(i2);
        } else {
            if (!isFunction(i2) && !isTable(i2)) {
                if (type(i2) == 3) {
                    obj = new Double(toNumber(i2));
                } else if (!isUserdata(i2)) {
                    isNil(i2);
                } else if (isObject(i2)) {
                    obj = getObjectFromUserdata(i2);
                }
            }
            obj = getLuaObject(i2);
        }
        return obj;
    }

    public double toNumber(int i2) {
        return _toNumber(this.luaState, i2);
    }

    public String toString(int i2) {
        return _toString(this.luaState, i2);
    }

    public LuaState toThread(int i2) {
        return new LuaState((CPtr) _toThread(this.luaState, i2));
    }

    public int type(int i2) {
        return _type(this.luaState, i2);
    }

    public String typeName(int i2) {
        return _typeName(this.luaState, i2);
    }

    public void xmove(LuaState luaState, int i2) {
        _xmove(this.luaState, luaState.luaState, i2);
    }

    public int yield(int i2) {
        return _yield(this.luaState, i2);
    }
}
