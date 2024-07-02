package androidx.fragment.app;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;

@Deprecated
/* loaded from: classes.dex */
public abstract class FragmentPagerAdapter extends PagerAdapter {
    public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;

    @Deprecated
    public static final int BEHAVIOR_SET_USER_VISIBLE_HINT = 0;

    /* renamed from: c, reason: collision with root package name */
    private final FragmentManager f731c;

    /* renamed from: d, reason: collision with root package name */
    private final int f732d;
    private FragmentTransaction e;
    private Fragment f;
    private boolean g;

    @Deprecated
    public FragmentPagerAdapter(@NonNull FragmentManager fragmentManager) {
        this(fragmentManager, 0);
    }

    public FragmentPagerAdapter(@NonNull FragmentManager fragmentManager, int i2) {
        this.e = null;
        this.f = null;
        this.f731c = fragmentManager;
        this.f732d = i2;
    }

    private static String b(int i2, long j) {
        return "android:switcher:" + i2 + ":" + j;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i2, @NonNull Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.e == null) {
            this.e = this.f731c.beginTransaction();
        }
        this.e.detach(fragment);
        if (fragment.equals(this.f)) {
            this.f = null;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void finishUpdate(@NonNull ViewGroup viewGroup) {
        FragmentTransaction fragmentTransaction = this.e;
        if (fragmentTransaction != null) {
            if (!this.g) {
                try {
                    this.g = true;
                    fragmentTransaction.commitNowAllowingStateLoss();
                } finally {
                    this.g = false;
                }
            }
            this.e = null;
        }
    }

    @NonNull
    public abstract Fragment getItem(int i2);

    public long getItemId(int i2) {
        return i2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i2) {
        if (this.e == null) {
            this.e = this.f731c.beginTransaction();
        }
        long itemId = getItemId(i2);
        Fragment findFragmentByTag = this.f731c.findFragmentByTag(b(viewGroup.getId(), itemId));
        if (findFragmentByTag != null) {
            this.e.attach(findFragmentByTag);
        } else {
            findFragmentByTag = getItem(i2);
            this.e.add(viewGroup.getId(), findFragmentByTag, b(viewGroup.getId(), itemId));
        }
        if (findFragmentByTag != this.f) {
            findFragmentByTag.setMenuVisibility(false);
            if (this.f732d == 1) {
                this.e.setMaxLifecycle(findFragmentByTag, Lifecycle.State.STARTED);
            } else {
                findFragmentByTag.setUserVisibleHint(false);
            }
        }
        return findFragmentByTag;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void restoreState(@Nullable Parcelable parcelable, @Nullable ClassLoader classLoader) {
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @Nullable
    public Parcelable saveState() {
        return null;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(@NonNull ViewGroup viewGroup, int i2, @NonNull Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.f;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(false);
                if (this.f732d == 1) {
                    if (this.e == null) {
                        this.e = this.f731c.beginTransaction();
                    }
                    this.e.setMaxLifecycle(this.f, Lifecycle.State.STARTED);
                } else {
                    this.f.setUserVisibleHint(false);
                }
            }
            fragment.setMenuVisibility(true);
            if (this.f732d == 1) {
                if (this.e == null) {
                    this.e = this.f731c.beginTransaction();
                }
                this.e.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
            } else {
                fragment.setUserVisibleHint(true);
            }
            this.f = fragment;
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
