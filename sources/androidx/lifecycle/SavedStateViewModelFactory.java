package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class SavedStateViewModelFactory extends ViewModelProvider.a {
    private static final Class<?>[] f = {Application.class, SavedStateHandle.class};
    private static final Class<?>[] g = {SavedStateHandle.class};
    private final Application a;
    private final ViewModelProvider.Factory b;

    /* renamed from: c, reason: collision with root package name */
    private final Bundle f822c;

    /* renamed from: d, reason: collision with root package name */
    private final Lifecycle f823d;
    private final SavedStateRegistry e;

    public SavedStateViewModelFactory(@Nullable Application application, @NonNull SavedStateRegistryOwner savedStateRegistryOwner) {
        this(application, savedStateRegistryOwner, null);
    }

    @SuppressLint({"LambdaLast"})
    public SavedStateViewModelFactory(@Nullable Application application, @NonNull SavedStateRegistryOwner savedStateRegistryOwner, @Nullable Bundle bundle) {
        this.e = savedStateRegistryOwner.getSavedStateRegistry();
        this.f823d = savedStateRegistryOwner.getLifecycle();
        this.f822c = bundle;
        this.a = application;
        this.b = application != null ? ViewModelProvider.AndroidViewModelFactory.getInstance(application) : ViewModelProvider.NewInstanceFactory.a();
    }

    private static <T> Constructor<T> b(Class<T> cls, Class<?>[] clsArr) {
        for (Object obj : cls.getConstructors()) {
            Constructor<T> constructor = (Constructor<T>) obj;
            if (Arrays.equals(clsArr, constructor.getParameterTypes())) {
                return constructor;
            }
        }
        return null;
    }

    @Override // androidx.lifecycle.ViewModelProvider.b
    void a(@NonNull ViewModel viewModel) {
        SavedStateHandleController.g(viewModel, this.e, this.f823d);
    }

    @Override // androidx.lifecycle.ViewModelProvider.a, androidx.lifecycle.ViewModelProvider.Factory
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) create(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // androidx.lifecycle.ViewModelProvider.a
    @NonNull
    public <T extends ViewModel> T create(@NonNull String str, @NonNull Class<T> cls) {
        Object newInstance;
        boolean isAssignableFrom = AndroidViewModel.class.isAssignableFrom(cls);
        Constructor b = (!isAssignableFrom || this.a == null) ? b(cls, g) : b(cls, f);
        if (b == null) {
            return (T) this.b.create(cls);
        }
        SavedStateHandleController i2 = SavedStateHandleController.i(this.e, this.f823d, str, this.f822c);
        if (isAssignableFrom) {
            try {
                Application application = this.a;
                if (application != null) {
                    newInstance = b.newInstance(application, i2.j());
                    T t = (T) newInstance;
                    t.e("androidx.lifecycle.savedstate.vm.tag", i2);
                    return t;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access " + cls, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("A " + cls + " cannot be instantiated.", e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("An exception happened in constructor of " + cls, e3.getCause());
            }
        }
        newInstance = b.newInstance(i2.j());
        T t2 = (T) newInstance;
        t2.e("androidx.lifecycle.savedstate.vm.tag", i2);
        return t2;
    }
}
