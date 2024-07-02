package androidx.core.net;

import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public class ParseException extends RuntimeException {

    @NonNull
    public final String response;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParseException(@NonNull String str) {
        super(str);
        this.response = str;
    }
}
