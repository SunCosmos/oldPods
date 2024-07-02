package bsh;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Hashtable;

/* loaded from: classes.dex */
public class XThis extends This {
    static /* synthetic */ Class class$java$lang$Object;
    Hashtable interfaces;
    InvocationHandler invocationHandler;

    /* loaded from: classes.dex */
    class Handler implements InvocationHandler, Serializable {
        Handler() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            try {
                return invokeImpl(obj, method, objArr);
            } catch (TargetError e) {
                throw e.getTarget();
            } catch (EvalError e2) {
                if (Interpreter.DEBUG) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("EvalError in scripted interface: ");
                    stringBuffer.append(XThis.this.toString());
                    stringBuffer.append(": ");
                    stringBuffer.append(e2);
                    Interpreter.debug(stringBuffer.toString());
                }
                throw e2;
            }
        }

        public Object invokeImpl(Object obj, Method method, Object[] objArr) {
            BshMethod bshMethod;
            String name = method.getName();
            new CallStack(XThis.this.namespace);
            BshMethod bshMethod2 = null;
            try {
                NameSpace nameSpace = XThis.this.namespace;
                Class[] clsArr = new Class[1];
                Class cls = XThis.class$java$lang$Object;
                if (cls == null) {
                    cls = XThis.class$("java.lang.Object");
                    XThis.class$java$lang$Object = cls;
                }
                clsArr[0] = cls;
                bshMethod = nameSpace.getMethod("equals", clsArr);
            } catch (UtilEvalError unused) {
                bshMethod = null;
            }
            if (name.equals("equals") && bshMethod == null) {
                return new Boolean(obj == objArr[0]);
            }
            try {
                bshMethod2 = XThis.this.namespace.getMethod("toString", new Class[0]);
            } catch (UtilEvalError unused2) {
            }
            if (!name.equals("toString") || bshMethod2 != null) {
                return Primitive.unwrap(XThis.this.invokeMethod(name, Primitive.wrap(objArr, method.getParameterTypes())));
            }
            Class<?>[] interfaces = obj.getClass().getInterfaces();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(XThis.this.toString());
            stringBuffer.append("\nimplements:");
            StringBuffer stringBuffer2 = new StringBuffer(stringBuffer.toString());
            for (Class<?> cls2 : interfaces) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append(" ");
                stringBuffer3.append(cls2.getName());
                stringBuffer3.append(interfaces.length > 1 ? "," : "");
                stringBuffer2.append(stringBuffer3.toString());
            }
            return stringBuffer2.toString();
        }
    }

    public XThis(NameSpace nameSpace, Interpreter interpreter) {
        super(nameSpace, interpreter);
        this.invocationHandler = new Handler();
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    @Override // bsh.This
    public Object getInterface(Class cls) {
        return getInterface(new Class[]{cls});
    }

    @Override // bsh.This
    public Object getInterface(Class[] clsArr) {
        if (this.interfaces == null) {
            this.interfaces = new Hashtable();
        }
        int i2 = 21;
        for (Class cls : clsArr) {
            i2 *= cls.hashCode() + 3;
        }
        Integer num = new Integer(i2);
        Object obj = this.interfaces.get(num);
        if (obj != null) {
            return obj;
        }
        Object newProxyInstance = Proxy.newProxyInstance(clsArr[0].getClassLoader(), clsArr, this.invocationHandler);
        this.interfaces.put(num, newProxyInstance);
        return newProxyInstance;
    }

    @Override // bsh.This
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("'this' reference (XThis) to Bsh object: ");
        stringBuffer.append(this.namespace);
        return stringBuffer.toString();
    }
}
