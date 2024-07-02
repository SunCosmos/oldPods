package androidx.fragment.app;

import android.R;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public class ListFragment extends Fragment {
    private final Handler b0 = new Handler();
    private final Runnable c0 = new a();
    private final AdapterView.OnItemClickListener d0 = new b();
    ListAdapter e0;
    ListView f0;
    View g0;
    TextView h0;
    View i0;
    View j0;
    CharSequence k0;
    boolean l0;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListView listView = ListFragment.this.f0;
            listView.focusableViewAvailable(listView);
        }
    }

    /* loaded from: classes.dex */
    class b implements AdapterView.OnItemClickListener {
        b() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            ListFragment.this.onListItemClick((ListView) adapterView, view, i2, j);
        }
    }

    private void o0() {
        if (this.f0 != null) {
            return;
        }
        View view = getView();
        if (view == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        if (view instanceof ListView) {
            this.f0 = (ListView) view;
        } else {
            TextView textView = (TextView) view.findViewById(16711681);
            this.h0 = textView;
            if (textView == null) {
                this.g0 = view.findViewById(R.id.empty);
            } else {
                textView.setVisibility(8);
            }
            this.i0 = view.findViewById(16711682);
            this.j0 = view.findViewById(16711683);
            View findViewById = view.findViewById(R.id.list);
            if (!(findViewById instanceof ListView)) {
                if (findViewById != null) {
                    throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
                }
                throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
            }
            ListView listView = (ListView) findViewById;
            this.f0 = listView;
            View view2 = this.g0;
            if (view2 == null) {
                CharSequence charSequence = this.k0;
                if (charSequence != null) {
                    this.h0.setText(charSequence);
                    listView = this.f0;
                    view2 = this.h0;
                }
            }
            listView.setEmptyView(view2);
        }
        this.l0 = true;
        this.f0.setOnItemClickListener(this.d0);
        ListAdapter listAdapter = this.e0;
        if (listAdapter != null) {
            this.e0 = null;
            setListAdapter(listAdapter);
        } else if (this.i0 != null) {
            p0(false, false);
        }
        this.b0.post(this.c0);
    }

    private void p0(boolean z, boolean z2) {
        o0();
        View view = this.i0;
        if (view == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        if (this.l0 == z) {
            return;
        }
        this.l0 = z;
        if (z) {
            if (z2) {
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
                this.j0.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
            } else {
                view.clearAnimation();
                this.j0.clearAnimation();
            }
            this.i0.setVisibility(8);
            this.j0.setVisibility(0);
            return;
        }
        if (z2) {
            view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
            this.j0.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_out));
        } else {
            view.clearAnimation();
            this.j0.clearAnimation();
        }
        this.i0.setVisibility(0);
        this.j0.setVisibility(8);
    }

    @Nullable
    public ListAdapter getListAdapter() {
        return this.e0;
    }

    @NonNull
    public ListView getListView() {
        o0();
        return this.f0;
    }

    public long getSelectedItemId() {
        o0();
        return this.f0.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        o0();
        return this.f0.getSelectedItemPosition();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Context requireContext = requireContext();
        FrameLayout frameLayout = new FrameLayout(requireContext);
        LinearLayout linearLayout = new LinearLayout(requireContext);
        linearLayout.setId(16711682);
        linearLayout.setOrientation(1);
        linearLayout.setVisibility(8);
        linearLayout.setGravity(17);
        linearLayout.addView(new ProgressBar(requireContext, null, R.attr.progressBarStyleLarge), new FrameLayout.LayoutParams(-2, -2));
        frameLayout.addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout frameLayout2 = new FrameLayout(requireContext);
        frameLayout2.setId(16711683);
        TextView textView = new TextView(requireContext);
        textView.setId(16711681);
        textView.setGravity(17);
        frameLayout2.addView(textView, new FrameLayout.LayoutParams(-1, -1));
        ListView listView = new ListView(requireContext);
        listView.setId(R.id.list);
        listView.setDrawSelectorOnTop(false);
        frameLayout2.addView(listView, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.addView(frameLayout2, new FrameLayout.LayoutParams(-1, -1));
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return frameLayout;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.b0.removeCallbacks(this.c0);
        this.f0 = null;
        this.l0 = false;
        this.j0 = null;
        this.i0 = null;
        this.g0 = null;
        this.h0 = null;
        super.onDestroyView();
    }

    public void onListItemClick(@NonNull ListView listView, @NonNull View view, int i2, long j) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        o0();
    }

    @NonNull
    public final ListAdapter requireListAdapter() {
        ListAdapter listAdapter = getListAdapter();
        if (listAdapter != null) {
            return listAdapter;
        }
        throw new IllegalStateException("ListFragment " + this + " does not have a ListAdapter.");
    }

    public void setEmptyText(@Nullable CharSequence charSequence) {
        o0();
        TextView textView = this.h0;
        if (textView == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        textView.setText(charSequence);
        if (this.k0 == null) {
            this.f0.setEmptyView(this.h0);
        }
        this.k0 = charSequence;
    }

    public void setListAdapter(@Nullable ListAdapter listAdapter) {
        boolean z = this.e0 != null;
        this.e0 = listAdapter;
        ListView listView = this.f0;
        if (listView != null) {
            listView.setAdapter(listAdapter);
            if (this.l0 || z) {
                return;
            }
            p0(true, requireView().getWindowToken() != null);
        }
    }

    public void setListShown(boolean z) {
        p0(z, true);
    }

    public void setListShownNoAnimation(boolean z) {
        p0(z, false);
    }

    public void setSelection(int i2) {
        o0();
        this.f0.setSelection(i2);
    }
}
