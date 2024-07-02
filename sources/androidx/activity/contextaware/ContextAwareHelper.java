package androidx.activity.contextaware;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes.dex */
public final class ContextAwareHelper {
    private final Set<OnContextAvailableListener> a = new CopyOnWriteArraySet();
    private volatile Context b;

    public void addOnContextAvailableListener(@NonNull OnContextAvailableListener onContextAvailableListener) {
        if (this.b != null) {
            onContextAvailableListener.onContextAvailable(this.b);
        }
        this.a.add(onContextAvailableListener);
    }

    public void clearAvailableContext() {
        this.b = null;
    }

    public void dispatchOnContextAvailable(@NonNull Context context) {
        this.b = context;
        Iterator<OnContextAvailableListener> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().onContextAvailable(context);
        }
    }

    @Nullable
    public Context peekAvailableContext() {
        return this.b;
    }

    public void removeOnContextAvailableListener(@NonNull OnContextAvailableListener onContextAvailableListener) {
        this.a.remove(onContextAvailableListener);
    }
}
