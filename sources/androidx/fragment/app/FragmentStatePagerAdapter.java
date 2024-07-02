package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;

@Deprecated
/* loaded from: classes.dex */
public abstract class FragmentStatePagerAdapter extends PagerAdapter {
    public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;

    @Deprecated
    public static final int BEHAVIOR_SET_USER_VISIBLE_HINT = 0;

    /* renamed from: c, reason: collision with root package name */
    private final FragmentManager f736c;

    /* renamed from: d, reason: collision with root package name */
    private final int f737d;
    private FragmentTransaction e;
    private ArrayList<Fragment.SavedState> f;
    private ArrayList<Fragment> g;
    private Fragment h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f738i;

    @Deprecated
    public FragmentStatePagerAdapter(@NonNull FragmentManager fragmentManager) {
        this(fragmentManager, 0);
    }

    public FragmentStatePagerAdapter(@NonNull FragmentManager fragmentManager, int i2) {
        this.e = null;
        this.f = new ArrayList<>();
        this.g = new ArrayList<>();
        this.h = null;
        this.f736c = fragmentManager;
        this.f737d = i2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i2, @NonNull Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.e == null) {
            this.e = this.f736c.beginTransaction();
        }
        while (this.f.size() <= i2) {
            this.f.add(null);
        }
        this.f.set(i2, fragment.isAdded() ? this.f736c.saveFragmentInstanceState(fragment) : null);
        this.g.set(i2, null);
        this.e.remove(fragment);
        if (fragment.equals(this.h)) {
            this.h = null;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void finishUpdate(@NonNull ViewGroup viewGroup) {
        FragmentTransaction fragmentTransaction = this.e;
        if (fragmentTransaction != null) {
            if (!this.f738i) {
                try {
                    this.f738i = true;
                    fragmentTransaction.commitNowAllowingStateLoss();
                } finally {
                    this.f738i = false;
                }
            }
            this.e = null;
        }
    }

    @NonNull
    public abstract Fragment getItem(int i2);

    @Override // androidx.viewpager.widget.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i2) {
        Fragment.SavedState savedState;
        Fragment fragment;
        if (this.g.size() > i2 && (fragment = this.g.get(i2)) != null) {
            return fragment;
        }
        if (this.e == null) {
            this.e = this.f736c.beginTransaction();
        }
        Fragment item = getItem(i2);
        if (this.f.size() > i2 && (savedState = this.f.get(i2)) != null) {
            item.setInitialSavedState(savedState);
        }
        while (this.g.size() <= i2) {
            this.g.add(null);
        }
        item.setMenuVisibility(false);
        if (this.f737d == 0) {
            item.setUserVisibleHint(false);
        }
        this.g.set(i2, item);
        this.e.add(viewGroup.getId(), item);
        if (this.f737d == 1) {
            this.e.setMaxLifecycle(item, Lifecycle.State.STARTED);
        }
        return item;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void restoreState(@Nullable Parcelable parcelable, @Nullable ClassLoader classLoader) {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(classLoader);
            Parcelable[] parcelableArray = bundle.getParcelableArray("states");
            this.f.clear();
            this.g.clear();
            if (parcelableArray != null) {
                for (Parcelable parcelable2 : parcelableArray) {
                    this.f.add((Fragment.SavedState) parcelable2);
                }
            }
            for (String str : bundle.keySet()) {
                if (str.startsWith("f")) {
                    int parseInt = Integer.parseInt(str.substring(1));
                    Fragment fragment = this.f736c.getFragment(bundle, str);
                    if (fragment != null) {
                        while (this.g.size() <= parseInt) {
                            this.g.add(null);
                        }
                        fragment.setMenuVisibility(false);
                        this.g.set(parseInt, fragment);
                    } else {
                        Log.w("FragmentStatePagerAdapt", "Bad fragment at key " + str);
                    }
                }
            }
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @Nullable
    public Parcelable saveState() {
        Bundle bundle;
        if (this.f.size() > 0) {
            bundle = new Bundle();
            Fragment.SavedState[] savedStateArr = new Fragment.SavedState[this.f.size()];
            this.f.toArray(savedStateArr);
            bundle.putParcelableArray("states", savedStateArr);
        } else {
            bundle = null;
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            Fragment fragment = this.g.get(i2);
            if (fragment != null && fragment.isAdded()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                this.f736c.putFragment(bundle, "f" + i2, fragment);
            }
        }
        return bundle;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(@NonNull ViewGroup viewGroup, int i2, @NonNull Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.h;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(false);
                if (this.f737d == 1) {
                    if (this.e == null) {
                        this.e = this.f736c.beginTransaction();
                    }
                    this.e.setMaxLifecycle(this.h, Lifecycle.State.STARTED);
                } else {
                    this.h.setUserVisibleHint(false);
                }
            }
            fragment.setMenuVisibility(true);
            if (this.f737d == 1) {
                if (this.e == null) {
                    this.e = this.f736c.beginTransaction();
                }
                this.e.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
            } else {
                fragment.setUserVisibleHint(true);
            }
            this.h = fragment;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void startUpdate(@NonNull ViewGroup viewGroup) {
        if (viewGroup.getId() != -1) {
            return;
        }
        throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
    }
}
