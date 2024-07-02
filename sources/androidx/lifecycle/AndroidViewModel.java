package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public class AndroidViewModel extends ViewModel {

    /* renamed from: c, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    private Application f808c;

    public AndroidViewModel(@NonNull Application application) {
        this.f808c = application;
    }

    @NonNull
    public <T extends Application> T getApplication() {
        return (T) this.f808c;
    }
}
