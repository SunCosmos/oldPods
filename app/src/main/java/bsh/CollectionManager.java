package bsh;

import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: classes.dex */
public class CollectionManager {
    private static CollectionManager manager;

    /* loaded from: classes.dex */
    public static class BasicBshIterator implements BshIterator {
        Enumeration enumeration;

        public BasicBshIterator(Object obj) {
            this.enumeration = createEnumeration(obj);
        }

        protected Enumeration createEnumeration(Object obj) {
            if (obj == null) {
                throw new NullPointerException("Object arguments passed to the BasicBshIterator constructor cannot be null.");
            }
            if (obj instanceof Enumeration) {
                return (Enumeration) obj;
            }
            if (obj instanceof Vector) {
                return ((Vector) obj).elements();
            }
            if (obj.getClass().isArray()) {
                return new Enumeration(obj) { // from class: bsh.CollectionManager.1
                    int index = 0;
                    int length;
                    private final /* synthetic */ Object val$array;

                    {
                        this.val$array = obj;
                        this.length = Array.getLength(obj);
                    }

                    @Override // java.util.Enumeration
                    public boolean hasMoreElements() {
                        return this.index < this.length;
                    }

                    @Override // java.util.Enumeration
                    public Object nextElement() {
                        Object obj2 = this.val$array;
                        int i2 = this.index;
                        this.index = i2 + 1;
                        return Array.get(obj2, i2);
                    }
                };
            }
            if (obj instanceof String) {
                return createEnumeration(((String) obj).toCharArray());
            }
            if (obj instanceof StringBuffer) {
                return createEnumeration(obj.toString().toCharArray());
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot enumerate object of type ");
            stringBuffer.append(obj.getClass());
            throw new IllegalArgumentException(stringBuffer.toString());
        }

        @Override // bsh.BshIterator
        public boolean hasNext() {
            return this.enumeration.hasMoreElements();
        }

        @Override // bsh.BshIterator
        public Object next() {
            return this.enumeration.nextElement();
        }
    }

    public static synchronized CollectionManager getCollectionManager() {
        CollectionManager collectionManager;
        synchronized (CollectionManager.class) {
            if (manager == null && Capabilities.classExists("java.util.Collection")) {
                try {
                    manager = (CollectionManager) Class.forName("bsh.collection.CollectionManagerImpl").newInstance();
                } catch (Exception e) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("unable to load CollectionManagerImpl: ");
                    stringBuffer.append(e);
                    Interpreter.debug(stringBuffer.toString());
                }
            }
            if (manager == null) {
                manager = new CollectionManager();
            }
            collectionManager = manager;
        }
        return collectionManager;
    }

    public BshIterator getBshIterator(Object obj) {
        return new BasicBshIterator(obj);
    }

    public Object getFromMap(Object obj, Object obj2) {
        return ((Hashtable) obj).get(obj2);
    }

    public boolean isBshIterable(Object obj) {
        try {
            getBshIterator(obj);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public boolean isMap(Object obj) {
        return obj instanceof Hashtable;
    }

    public Object putInMap(Object obj, Object obj2, Object obj3) {
        return ((Hashtable) obj).put(obj2, obj3);
    }
}
