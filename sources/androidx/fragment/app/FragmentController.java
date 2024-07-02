package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Preconditions;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class FragmentController {
    private final FragmentHostCallback<?> a;

    private FragmentController(FragmentHostCallback<?> fragmentHostCallback) {
        this.a = fragmentHostCallback;
    }

    @NonNull
    public static FragmentController createController(@NonNull FragmentHostCallback<?> fragmentHostCallback) {
        return new FragmentController((FragmentHostCallback) Preconditions.checkNotNull(fragmentHostCallback, "callbacks == null"));
    }

    public void attachHost(@Nullable Fragment fragment) {
        FragmentHostCallback<?> fragmentHostCallback = this.a;
        fragmentHostCallback.e.j(fragmentHostCallback, fragmentHostCallback, fragment);
    }

    public void dispatchActivityCreated() {
        this.a.e.w();
    }

    public void dispatchConfigurationChanged(@NonNull Configuration configuration) {
        this.a.e.y(configuration);
    }

    public boolean dispatchContextItemSelected(@NonNull MenuItem menuItem) {
        return this.a.e.z(menuItem);
    }

    public void dispatchCreate() {
        this.a.e.A();
    }

    public boolean dispatchCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        return this.a.e.B(menu, menuInflater);
    }

    public void dispatchDestroy() {
        this.a.e.C();
    }

    public void dispatchDestroyView() {
        this.a.e.D();
    }

    public void dispatchLowMemory() {
        this.a.e.E();
    }

    public void dispatchMultiWindowModeChanged(boolean z) {
        this.a.e.F(z);
    }

    public boolean dispatchOptionsItemSelected(@NonNull MenuItem menuItem) {
        return this.a.e.H(menuItem);
    }

    public void dispatchOptionsMenuClosed(@NonNull Menu menu) {
        this.a.e.I(menu);
    }

    public void dispatchPause() {
        this.a.e.K();
    }

    public void dispatchPictureInPictureModeChanged(boolean z) {
        this.a.e.L(z);
    }

    public boolean dispatchPrepareOptionsMenu(@NonNull Menu menu) {
        return this.a.e.M(menu);
    }

    @Deprecated
    public void dispatchReallyStop() {
    }

    public void dispatchResume() {
        this.a.e.O();
    }

    public void dispatchStart() {
        this.a.e.P();
    }

    public void dispatchStop() {
        this.a.e.R();
    }

    @Deprecated
    public void doLoaderDestroy() {
    }

    @Deprecated
    public void doLoaderRetain() {
    }

    @Deprecated
    public void doLoaderStart() {
    }

    @Deprecated
    public void doLoaderStop(boolean z) {
    }

    @Deprecated
    public void dumpLoaders(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
    }

    public boolean execPendingActions() {
        return this.a.e.X(true);
    }

    @Nullable
    public Fragment findFragmentByWho(@NonNull String str) {
        return this.a.e.d0(str);
    }

    @NonNull
    public List<Fragment> getActiveFragments(@SuppressLint({"UnknownNullness"}) List<Fragment> list) {
        return this.a.e.i0();
    }

    public int getActiveFragmentsCount() {
        return this.a.e.h0();
    }

    @NonNull
    public FragmentManager getSupportFragmentManager() {
        return this.a.e;
    }

    @SuppressLint({"UnknownNullness"})
    @Deprecated
    public LoaderManager getSupportLoaderManager() {
        throw new UnsupportedOperationException("Loaders are managed separately from FragmentController, use LoaderManager.getInstance() to obtain a LoaderManager.");
    }

    public void noteStateNotSaved() {
        this.a.e.K0();
    }

    @Nullable
    public View onCreateView(@Nullable View view, @NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        return this.a.e.o0().onCreateView(view, str, context, attributeSet);
    }

    @Deprecated
    public void reportLoaderStart() {
    }

    @Deprecated
    public void restoreAllState(@Nullable Parcelable parcelable, @Nullable FragmentManagerNonConfig fragmentManagerNonConfig) {
        this.a.e.V0(parcelable, fragmentManagerNonConfig);
    }

    @Deprecated
    public void restoreAllState(@Nullable Parcelable parcelable, @Nullable List<Fragment> list) {
        this.a.e.V0(parcelable, new FragmentManagerNonConfig(list, null, null));
    }

    @Deprecated
    public void restoreLoaderNonConfig(@SuppressLint({"UnknownNullness"}) SimpleArrayMap<String, LoaderManager> simpleArrayMap) {
    }

    public void restoreSaveState(@Nullable Parcelable parcelable) {
        FragmentHostCallback<?> fragmentHostCallback = this.a;
        if (!(fragmentHostCallback instanceof ViewModelStoreOwner)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        fragmentHostCallback.e.W0(parcelable);
    }

    @Nullable
    @Deprecated
    public SimpleArrayMap<String, LoaderManager> retainLoaderNonConfig() {
        return null;
    }

    @Nullable
    @Deprecated
    public FragmentManagerNonConfig retainNestedNonConfig() {
        return this.a.e.X0();
    }

    @Nullable
    @Deprecated
    public List<Fragment> retainNonConfig() {
        FragmentManagerNonConfig X0 = this.a.e.X0();
        if (X0 == null || X0.b() == null) {
            return null;
        }
        return new ArrayList(X0.b());
    }

    @Nullable
    public Parcelable saveAllState() {
        return this.a.e.Z0();
    }
}
