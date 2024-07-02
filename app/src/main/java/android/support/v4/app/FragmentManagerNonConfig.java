package android.support.v4.app;

import android.arch.lifecycle.ViewModelStore;
import java.util.List;

/* loaded from: lib/Mus.dex */
public class FragmentManagerNonConfig {
    private final List<FragmentManagerNonConfig> mChildNonConfigs;
    private final List<Fragment> mFragments;
    private final List<ViewModelStore> mViewModelStores;

    public FragmentManagerNonConfig(List<Fragment> list, List<FragmentManagerNonConfig> list2, List<ViewModelStore> list3) {
        this.mFragments = list;
        this.mChildNonConfigs = list2;
        this.mViewModelStores = list3;
    }

    public List<Fragment> getFragments() {
        return this.mFragments;
    }

    public List<FragmentManagerNonConfig> getChildNonConfigs() {
        return this.mChildNonConfigs;
    }

    public List<ViewModelStore> getViewModelStores() {
        return this.mViewModelStores;
    }
}
