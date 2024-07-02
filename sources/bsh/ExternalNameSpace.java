package bsh;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Vector;

/* loaded from: classes.dex */
public class ExternalNameSpace extends NameSpace {
    private Map externalMap;

    public ExternalNameSpace() {
        this(null, "External Map Namespace", null);
    }

    public ExternalNameSpace(NameSpace nameSpace, String str, Map map) {
        super(nameSpace, str);
        this.externalMap = map == null ? new HashMap() : map;
    }

    @Override // bsh.NameSpace
    public void clear() {
        super.clear();
        this.externalMap.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // bsh.NameSpace
    public void getAllNamesAux(Vector vector) {
        super.getAllNamesAux(vector);
    }

    @Override // bsh.NameSpace
    public Variable[] getDeclaredVariables() {
        return super.getDeclaredVariables();
    }

    public Map getMap() {
        return this.externalMap;
    }

    @Override // bsh.NameSpace
    public BshMethod getMethod(String str, Class[] clsArr, boolean z) {
        return super.getMethod(str, clsArr, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // bsh.NameSpace
    public Variable getVariableImpl(String str, boolean z) {
        Object obj = this.externalMap.get(str);
        if (obj == null) {
            super.unsetVariable(str);
            return super.getVariableImpl(str, z);
        }
        Variable variableImpl = super.getVariableImpl(str, false);
        if (variableImpl == null) {
            variableImpl = new Variable(str, (Class) null, obj, (Modifiers) null);
        }
        return variableImpl;
    }

    @Override // bsh.NameSpace
    public String[] getVariableNames() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(super.getVariableNames()));
        hashSet.addAll(this.externalMap.keySet());
        return (String[]) hashSet.toArray(new String[0]);
    }

    protected void putExternalMap(String str, Object obj) {
        if (obj instanceof Variable) {
            try {
                obj = unwrapVariable((Variable) obj);
            } catch (UtilEvalError unused) {
                throw new InterpreterError("unexpected UtilEvalError");
            }
        }
        if (obj instanceof Primitive) {
            obj = Primitive.unwrap((Primitive) obj);
        }
        this.externalMap.put(str, obj);
    }

    public void setMap(Map map) {
        this.externalMap = null;
        clear();
        this.externalMap = map;
    }

    @Override // bsh.NameSpace
    public void setMethod(String str, BshMethod bshMethod) {
        super.setMethod(str, bshMethod);
    }

    @Override // bsh.NameSpace
    public void setTypedVariable(String str, Class cls, Object obj, Modifiers modifiers) {
        super.setTypedVariable(str, cls, obj, modifiers);
        putExternalMap(str, obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // bsh.NameSpace
    public void setVariable(String str, Object obj, boolean z, boolean z2) {
        super.setVariable(str, obj, z, z2);
        putExternalMap(str, obj);
    }

    @Override // bsh.NameSpace
    public void unsetVariable(String str) {
        super.unsetVariable(str);
        this.externalMap.remove(str);
    }
}
