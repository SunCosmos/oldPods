package org.keplerproject.luajava;

/* loaded from: classes.dex */
public class CPtr {
    private long peer;

    CPtr() {
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return CPtr.class == obj.getClass() && this.peer == ((CPtr) obj).peer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getPeer() {
        return this.peer;
    }
}
