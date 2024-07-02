package androidx.lifecycle;

import androidx.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class MethodCallsLogger {
    private Map<String, Integer> a = new HashMap();

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public boolean approveCall(String str, int i2) {
        Integer num = this.a.get(str);
        int intValue = num != null ? num.intValue() : 0;
        boolean z = (intValue & i2) != 0;
        this.a.put(str, Integer.valueOf(i2 | intValue));
        return !z;
    }
}
