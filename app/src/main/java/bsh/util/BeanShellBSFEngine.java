package bsh.util;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.InterpreterError;
import bsh.Primitive;
import bsh.TargetError;
import bsh.This;
import java.util.Vector;
import org.apache.bsf.BSFDeclaredBean;
import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.bsf.util.BSFEngineImpl;

/* loaded from: classes.dex */
public class BeanShellBSFEngine extends BSFEngineImpl {
    static final String bsfApplyMethod = "_bsfApply( _bsfNames, _bsfArgs, _bsfText ) {for(i=0;i<_bsfNames.length;i++)this.namespace.setVariable(_bsfNames[i], _bsfArgs[i],false);return this.interpreter.eval(_bsfText, this.namespace);}";
    boolean installedApplyMethod;
    Interpreter interpreter;

    private String sourceInfo(String str, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" BSF info: ");
        stringBuffer.append(str);
        stringBuffer.append(" at line: ");
        stringBuffer.append(i2);
        stringBuffer.append(" column: columnNo");
        return stringBuffer.toString();
    }

    public Object apply(String str, int i2, int i3, Object obj, Vector vector, Vector vector2) {
        if (vector.size() != vector2.size()) {
            throw new BSFException("number of params/names mismatch");
        }
        if (!(obj instanceof String)) {
            throw new BSFException("apply: functino body must be a string");
        }
        String[] strArr = new String[vector.size()];
        vector.copyInto(strArr);
        Object[] objArr = new Object[vector2.size()];
        vector2.copyInto(objArr);
        try {
            if (!this.installedApplyMethod) {
                this.interpreter.eval(bsfApplyMethod);
                this.installedApplyMethod = true;
            }
            return Primitive.unwrap(((This) this.interpreter.get("global")).invokeMethod("_bsfApply", new Object[]{strArr, objArr, (String) obj}));
        } catch (TargetError e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("The application script threw an exception: ");
            stringBuffer.append(e.getTarget());
            stringBuffer.append(sourceInfo(str, i2, i3));
            throw new BSFException(stringBuffer.toString());
        } catch (EvalError e2) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("BeanShell script error: ");
            stringBuffer2.append(e2);
            stringBuffer2.append(sourceInfo(str, i2, i3));
            throw new BSFException(stringBuffer2.toString());
        } catch (InterpreterError e3) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("BeanShell interpreter internal error: ");
            stringBuffer3.append(e3);
            stringBuffer3.append(sourceInfo(str, i2, i3));
            throw new BSFException(stringBuffer3.toString());
        }
    }

    public Object call(Object obj, String str, Object[] objArr) {
        if (obj == null) {
            try {
                obj = this.interpreter.get("global");
            } catch (EvalError e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("bsh internal error: ");
                stringBuffer.append(e.toString());
                throw new BSFException(stringBuffer.toString());
            }
        }
        if (!(obj instanceof This)) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Cannot invoke method: ");
            stringBuffer2.append(str);
            stringBuffer2.append(". Object: ");
            stringBuffer2.append(obj);
            stringBuffer2.append(" is not a BeanShell scripted object.");
            throw new BSFException(stringBuffer2.toString());
        }
        try {
            return Primitive.unwrap(((This) obj).invokeMethod(str, objArr));
        } catch (InterpreterError e2) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("BeanShell interpreter internal error: ");
            stringBuffer3.append(e2);
            throw new BSFException(stringBuffer3.toString());
        } catch (TargetError e3) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("The application script threw an exception: ");
            stringBuffer4.append(e3.getTarget());
            throw new BSFException(stringBuffer4.toString());
        } catch (EvalError e4) {
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("BeanShell script error: ");
            stringBuffer5.append(e4);
            throw new BSFException(stringBuffer5.toString());
        }
    }

    public void declareBean(BSFDeclaredBean bSFDeclaredBean) {
        try {
            this.interpreter.set(bSFDeclaredBean.name, bSFDeclaredBean.bean);
        } catch (EvalError e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("error declaring bean: ");
            stringBuffer.append(bSFDeclaredBean.name);
            stringBuffer.append(" : ");
            stringBuffer.append(e.toString());
            throw new BSFException(stringBuffer.toString());
        }
    }

    public Object eval(String str, int i2, int i3, Object obj) {
        if (!(obj instanceof String)) {
            throw new BSFException("BeanShell expression must be a string");
        }
        try {
            return this.interpreter.eval((String) obj);
        } catch (EvalError e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("BeanShell script error: ");
            stringBuffer.append(e);
            stringBuffer.append(sourceInfo(str, i2, i3));
            throw new BSFException(stringBuffer.toString());
        } catch (InterpreterError e2) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("BeanShell interpreter internal error: ");
            stringBuffer2.append(e2);
            stringBuffer2.append(sourceInfo(str, i2, i3));
            throw new BSFException(stringBuffer2.toString());
        } catch (TargetError e3) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("The application script threw an exception: ");
            stringBuffer3.append(e3.getTarget());
            stringBuffer3.append(sourceInfo(str, i2, i3));
            throw new BSFException(stringBuffer3.toString());
        }
    }

    public void exec(String str, int i2, int i3, Object obj) {
        eval(str, i2, i3, obj);
    }

    public void initialize(BSFManager bSFManager, String str, Vector vector) {
        super.initialize(bSFManager, str, vector);
        Interpreter interpreter = new Interpreter();
        this.interpreter = interpreter;
        try {
            interpreter.set("bsf", bSFManager);
            for (int i2 = 0; i2 < vector.size(); i2++) {
                declareBean((BSFDeclaredBean) vector.get(i2));
            }
        } catch (EvalError e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("bsh internal error: ");
            stringBuffer.append(e.toString());
            throw new BSFException(stringBuffer.toString());
        }
    }

    public void setDebug(boolean z) {
        Interpreter.DEBUG = z;
    }

    public void terminate() {
    }

    public void undeclareBean(BSFDeclaredBean bSFDeclaredBean) {
        try {
            this.interpreter.unset(bSFDeclaredBean.name);
        } catch (EvalError e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("bsh internal error: ");
            stringBuffer.append(e.toString());
            throw new BSFException(stringBuffer.toString());
        }
    }
}
