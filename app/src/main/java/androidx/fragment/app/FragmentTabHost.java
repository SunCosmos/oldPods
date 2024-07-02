package androidx.fragment.app;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

@Deprecated
/* loaded from: classes.dex */
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {
    private final ArrayList<b> a;
    private FrameLayout b;

    /* renamed from: c, reason: collision with root package name */
    private Context f739c;

    /* renamed from: d, reason: collision with root package name */
    private FragmentManager f740d;
    private int e;
    private TabHost.OnTabChangeListener f;
    private b g;
    private boolean h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        String a;

        /* loaded from: classes.dex */
        class a implements Parcelable.Creator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readString();
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @NonNull
        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.a + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeString(this.a);
        }
    }

    /* loaded from: classes.dex */
    static class a implements TabHost.TabContentFactory {
        private final Context a;

        public a(Context context) {
            this.a = context;
        }

        @Override // android.widget.TabHost.TabContentFactory
        public View createTabContent(String str) {
            View view = new View(this.a);
            view.setMinimumWidth(0);
            view.setMinimumHeight(0);
            return view;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class b {

        @NonNull
        final String a;

        @NonNull
        final Class<?> b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        final Bundle f741c;

        /* renamed from: d, reason: collision with root package name */
        Fragment f742d;

        b(@NonNull String str, @NonNull Class<?> cls, @Nullable Bundle bundle) {
            this.a = str;
            this.b = cls;
            this.f741c = bundle;
        }
    }

    @Deprecated
    public FragmentTabHost(@NonNull Context context) {
        super(context, null);
        this.a = new ArrayList<>();
        e(context, null);
    }

    @Deprecated
    public FragmentTabHost(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new ArrayList<>();
        e(context, attributeSet);
    }

    @Nullable
    private FragmentTransaction a(@Nullable String str, @Nullable FragmentTransaction fragmentTransaction) {
        Fragment fragment;
        b d2 = d(str);
        if (this.g != d2) {
            if (fragmentTransaction == null) {
                fragmentTransaction = this.f740d.beginTransaction();
            }
            b bVar = this.g;
            if (bVar != null && (fragment = bVar.f742d) != null) {
                fragmentTransaction.detach(fragment);
            }
            if (d2 != null) {
                Fragment fragment2 = d2.f742d;
                if (fragment2 == null) {
                    Fragment instantiate = this.f740d.getFragmentFactory().instantiate(this.f739c.getClassLoader(), d2.b.getName());
                    d2.f742d = instantiate;
                    instantiate.setArguments(d2.f741c);
                    fragmentTransaction.add(this.e, d2.f742d, d2.a);
                } else {
                    fragmentTransaction.attach(fragment2);
                }
            }
            this.g = d2;
        }
        return fragmentTransaction;
    }

    private void b() {
        if (this.b == null) {
            FrameLayout frameLayout = (FrameLayout) findViewById(this.e);
            this.b = frameLayout;
            if (frameLayout != null) {
                return;
            }
            throw new IllegalStateException("No tab content FrameLayout found for id " + this.e);
        }
    }

    private void c(Context context) {
        if (findViewById(R.id.tabs) == null) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
            TabWidget tabWidget = new TabWidget(context);
            tabWidget.setId(R.id.tabs);
            tabWidget.setOrientation(0);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, -2, 0.0f));
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setId(R.id.tabcontent);
            linearLayout.addView(frameLayout, new LinearLayout.LayoutParams(0, 0, 0.0f));
            FrameLayout frameLayout2 = new FrameLayout(context);
            this.b = frameLayout2;
            frameLayout2.setId(this.e);
            linearLayout.addView(frameLayout2, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
    }

    @Nullable
    private b d(String str) {
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.a.get(i2);
            if (bVar.a.equals(str)) {
                return bVar;
            }
        }
        return null;
    }

    private void e(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.inflatedId}, 0, 0);
        this.e = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Deprecated
    public void addTab(@NonNull TabHost.TabSpec tabSpec, @NonNull Class<?> cls, @Nullable Bundle bundle) {
        tabSpec.setContent(new a(this.f739c));
        String tag = tabSpec.getTag();
        b bVar = new b(tag, cls, bundle);
        if (this.h) {
            Fragment findFragmentByTag = this.f740d.findFragmentByTag(tag);
            bVar.f742d = findFragmentByTag;
            if (findFragmentByTag != null && !findFragmentByTag.isDetached()) {
                FragmentTransaction beginTransaction = this.f740d.beginTransaction();
                beginTransaction.detach(bVar.f742d);
                beginTransaction.commit();
            }
        }
        this.a.add(bVar);
        addTab(tabSpec);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.a.size();
        FragmentTransaction fragmentTransaction = null;
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.a.get(i2);
            Fragment findFragmentByTag = this.f740d.findFragmentByTag(bVar.a);
            bVar.f742d = findFragmentByTag;
            if (findFragmentByTag != null && !findFragmentByTag.isDetached()) {
                if (bVar.a.equals(currentTabTag)) {
                    this.g = bVar;
                } else {
                    if (fragmentTransaction == null) {
                        fragmentTransaction = this.f740d.beginTransaction();
                    }
                    fragmentTransaction.detach(bVar.f742d);
                }
            }
        }
        this.h = true;
        FragmentTransaction a2 = a(currentTabTag, fragmentTransaction);
        if (a2 != null) {
            a2.commit();
            this.f740d.executePendingTransactions();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h = false;
    }

    @Override // android.view.View
    @Deprecated
    protected void onRestoreInstanceState(@SuppressLint({"UnknownNullness"}) Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.a);
    }

    @Override // android.view.View
    @NonNull
    @Deprecated
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(@Nullable String str) {
        FragmentTransaction a2;
        if (this.h && (a2 = a(str, null)) != null) {
            a2.commit();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.f;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setOnTabChangedListener(@Nullable TabHost.OnTabChangeListener onTabChangeListener) {
        this.f = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    @Deprecated
    public void setup(@NonNull Context context, @NonNull FragmentManager fragmentManager) {
        c(context);
        super.setup();
        this.f739c = context;
        this.f740d = fragmentManager;
        b();
    }

    @Deprecated
    public void setup(@NonNull Context context, @NonNull FragmentManager fragmentManager, int i2) {
        c(context);
        super.setup();
        this.f739c = context;
        this.f740d = fragmentManager;
        this.e = i2;
        b();
        this.b.setId(i2);
        if (getId() == -1) {
            setId(R.id.tabhost);
        }
    }
}
