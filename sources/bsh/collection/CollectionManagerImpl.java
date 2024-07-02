package bsh.collection;

import bsh.BshIterator;
import bsh.CollectionManager;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class CollectionManagerImpl extends CollectionManager {
    @Override // bsh.CollectionManager
    public BshIterator getBshIterator(Object obj) {
        return ((obj instanceof Collection) || (obj instanceof Iterator)) ? new CollectionIterator(obj) : new CollectionManager.BasicBshIterator(obj);
    }

    @Override // bsh.CollectionManager
    public Object getFromMap(Object obj, Object obj2) {
        return ((Map) obj).get(obj2);
    }

    @Override // bsh.CollectionManager
    public boolean isMap(Object obj) {
        if (obj instanceof Map) {
            return true;
        }
        return super.isMap(obj);
    }

    @Override // bsh.CollectionManager
    public Object putInMap(Object obj, Object obj2, Object obj3) {
        return ((Map) obj).put(obj2, obj3);
    }
}
