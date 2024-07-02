package bsh;

import java.io.Serializable;
import java.lang.reflect.Field;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class LHS implements ParserConstants, Serializable {
    static final int FIELD = 1;
    static final int INDEX = 3;
    static final int METHOD_EVAL = 4;
    static final int PROPERTY = 2;
    static final int VARIABLE = 0;
    Field field;
    int index;
    boolean localVar;
    NameSpace nameSpace;
    Object object;
    String propName;
    int type;
    String varName;

    LHS(NameSpace nameSpace, String str) {
        throw new Error("namespace lhs");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LHS(NameSpace nameSpace, String str, boolean z) {
        this.type = 0;
        this.localVar = z;
        this.varName = str;
        this.nameSpace = nameSpace;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LHS(Object obj, int i2) {
        if (obj == null) {
            throw new NullPointerException("constructed empty LHS");
        }
        this.type = 3;
        this.object = obj;
        this.index = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LHS(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException("constructed empty LHS");
        }
        this.type = 2;
        this.object = obj;
        this.propName = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LHS(Object obj, Field field) {
        if (obj == null) {
            throw new NullPointerException("constructed empty LHS");
        }
        this.type = 1;
        this.object = obj;
        this.field = field;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LHS(Field field) {
        this.type = 1;
        this.object = null;
        this.field = field;
    }

    public Object assign(Object obj, boolean z) {
        int i2 = this.type;
        if (i2 == 0) {
            if (this.localVar) {
                this.nameSpace.setLocalVariable(this.varName, obj, z);
            } else {
                this.nameSpace.setVariable(this.varName, obj, z);
            }
        } else {
            if (i2 == 1) {
                try {
                    Object value = obj instanceof Primitive ? ((Primitive) obj).getValue() : obj;
                    ReflectManager.RMSetAccessible(this.field);
                    this.field.set(this.object, value);
                    return obj;
                } catch (IllegalAccessException e) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("LHS (");
                    stringBuffer.append(this.field.getName());
                    stringBuffer.append(") can't access field: ");
                    stringBuffer.append(e);
                    throw new UtilEvalError(stringBuffer.toString());
                } catch (IllegalArgumentException unused) {
                    String name = (obj instanceof Primitive ? ((Primitive) obj).getType() : obj.getClass()).getName();
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Argument type mismatch. ");
                    if (obj == null) {
                        name = "null";
                    }
                    stringBuffer2.append(name);
                    stringBuffer2.append(" not assignable to field ");
                    stringBuffer2.append(this.field.getName());
                    throw new UtilEvalError(stringBuffer2.toString());
                } catch (NullPointerException unused2) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("LHS (");
                    stringBuffer3.append(this.field.getName());
                    stringBuffer3.append(") not a static field.");
                    throw new UtilEvalError(stringBuffer3.toString());
                }
            }
            if (i2 == 2) {
                CollectionManager collectionManager = CollectionManager.getCollectionManager();
                if (collectionManager.isMap(this.object)) {
                    collectionManager.putInMap(this.object, this.propName, obj);
                } else {
                    try {
                        Reflect.setObjectProperty(this.object, this.propName, obj);
                    } catch (ReflectError e2) {
                        StringBuffer stringBuffer4 = new StringBuffer();
                        stringBuffer4.append("Assignment: ");
                        stringBuffer4.append(e2.getMessage());
                        Interpreter.debug(stringBuffer4.toString());
                        StringBuffer stringBuffer5 = new StringBuffer();
                        stringBuffer5.append("No such property: ");
                        stringBuffer5.append(this.propName);
                        throw new UtilEvalError(stringBuffer5.toString());
                    }
                }
            } else {
                if (i2 != 3) {
                    throw new InterpreterError("unknown lhs");
                }
                try {
                    Reflect.setIndex(this.object, this.index, obj);
                } catch (UtilTargetError e3) {
                    throw e3;
                } catch (Exception e4) {
                    StringBuffer stringBuffer6 = new StringBuffer();
                    stringBuffer6.append("Assignment: ");
                    stringBuffer6.append(e4.getMessage());
                    throw new UtilEvalError(stringBuffer6.toString());
                }
            }
        }
        return obj;
    }

    public Object getValue() {
        int i2 = this.type;
        if (i2 == 0) {
            return this.nameSpace.getVariable(this.varName);
        }
        if (i2 == 1) {
            try {
                return Primitive.wrap(this.field.get(this.object), this.field.getType());
            } catch (IllegalAccessException unused) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Can't read field: ");
                stringBuffer.append(this.field);
                throw new UtilEvalError(stringBuffer.toString());
            }
        }
        if (i2 == 2) {
            try {
                return Reflect.getObjectProperty(this.object, this.propName);
            } catch (ReflectError e) {
                Interpreter.debug(e.getMessage());
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("No such property: ");
                stringBuffer2.append(this.propName);
                throw new UtilEvalError(stringBuffer2.toString());
            }
        }
        if (i2 != 3) {
            throw new InterpreterError("LHS type");
        }
        try {
            return Reflect.getIndex(this.object, this.index);
        } catch (Exception e2) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Array access: ");
            stringBuffer3.append(e2);
            throw new UtilEvalError(stringBuffer3.toString());
        }
    }

    public String toString() {
        String str;
        String str2;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("LHS: ");
        String str3 = "";
        if (this.field != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("field = ");
            stringBuffer2.append(this.field.toString());
            str = stringBuffer2.toString();
        } else {
            str = "";
        }
        stringBuffer.append(str);
        if (this.varName != null) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(" varName = ");
            stringBuffer3.append(this.varName);
            str2 = stringBuffer3.toString();
        } else {
            str2 = "";
        }
        stringBuffer.append(str2);
        if (this.nameSpace != null) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append(" nameSpace = ");
            stringBuffer4.append(this.nameSpace.toString());
            str3 = stringBuffer4.toString();
        }
        stringBuffer.append(str3);
        return stringBuffer.toString();
    }
}
