package androidx.fragment.app;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.LayoutRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;

/* loaded from: classes.dex */
public class DialogFragment extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_NO_FRAME = 2;
    public static final int STYLE_NO_INPUT = 3;
    public static final int STYLE_NO_TITLE = 1;
    private Handler b0;
    private Runnable c0;
    private DialogInterface.OnCancelListener d0;
    private DialogInterface.OnDismissListener e0;
    private int f0;
    private int g0;
    private boolean h0;
    private boolean i0;
    private int j0;
    private boolean k0;
    private Observer<LifecycleOwner> l0;

    @Nullable
    private Dialog m0;
    private boolean n0;
    private boolean o0;
    private boolean p0;
    private boolean q0;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        @SuppressLint({"SyntheticAccessor"})
        public void run() {
            DialogFragment.this.e0.onDismiss(DialogFragment.this.m0);
        }
    }

    /* loaded from: classes.dex */
    class b implements DialogInterface.OnCancelListener {
        b() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        @SuppressLint({"SyntheticAccessor"})
        public void onCancel(@Nullable DialogInterface dialogInterface) {
            if (DialogFragment.this.m0 != null) {
                DialogFragment dialogFragment = DialogFragment.this;
                dialogFragment.onCancel(dialogFragment.m0);
            }
        }
    }

    /* loaded from: classes.dex */
    class c implements DialogInterface.OnDismissListener {
        c() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        @SuppressLint({"SyntheticAccessor"})
        public void onDismiss(@Nullable DialogInterface dialogInterface) {
            if (DialogFragment.this.m0 != null) {
                DialogFragment dialogFragment = DialogFragment.this;
                dialogFragment.onDismiss(dialogFragment.m0);
            }
        }
    }

    /* loaded from: classes.dex */
    class d implements Observer<LifecycleOwner> {
        d() {
        }

        @Override // androidx.lifecycle.Observer
        @SuppressLint({"SyntheticAccessor"})
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onChanged(LifecycleOwner lifecycleOwner) {
            if (lifecycleOwner == null || !DialogFragment.this.i0) {
                return;
            }
            View requireView = DialogFragment.this.requireView();
            if (requireView.getParent() != null) {
                throw new IllegalStateException("DialogFragment can not be attached to a container view");
            }
            if (DialogFragment.this.m0 != null) {
                if (FragmentManager.x0(3)) {
                    Log.d("FragmentManager", "DialogFragment " + this + " setting the content view on " + DialogFragment.this.m0);
                }
                DialogFragment.this.m0.setContentView(requireView);
            }
        }
    }

    /* loaded from: classes.dex */
    class e extends FragmentContainer {
        final /* synthetic */ FragmentContainer a;

        e(FragmentContainer fragmentContainer) {
            this.a = fragmentContainer;
        }

        @Override // androidx.fragment.app.FragmentContainer
        @Nullable
        public View onFindViewById(int i2) {
            return this.a.onHasView() ? this.a.onFindViewById(i2) : DialogFragment.this.s0(i2);
        }

        @Override // androidx.fragment.app.FragmentContainer
        public boolean onHasView() {
            return this.a.onHasView() || DialogFragment.this.t0();
        }
    }

    public DialogFragment() {
        this.c0 = new a();
        this.d0 = new b();
        this.e0 = new c();
        this.f0 = 0;
        this.g0 = 0;
        this.h0 = true;
        this.i0 = true;
        this.j0 = -1;
        this.l0 = new d();
        this.q0 = false;
    }

    public DialogFragment(@LayoutRes int i2) {
        super(i2);
        this.c0 = new a();
        this.d0 = new b();
        this.e0 = new c();
        this.f0 = 0;
        this.g0 = 0;
        this.h0 = true;
        this.i0 = true;
        this.j0 = -1;
        this.l0 = new d();
        this.q0 = false;
    }

    private void r0(boolean z, boolean z2) {
        if (this.o0) {
            return;
        }
        this.o0 = true;
        this.p0 = false;
        Dialog dialog = this.m0;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.m0.dismiss();
            if (!z2) {
                if (Looper.myLooper() == this.b0.getLooper()) {
                    onDismiss(this.m0);
                } else {
                    this.b0.post(this.c0);
                }
            }
        }
        this.n0 = true;
        if (this.j0 >= 0) {
            getParentFragmentManager().popBackStack(this.j0, 1);
            this.j0 = -1;
            return;
        }
        FragmentTransaction beginTransaction = getParentFragmentManager().beginTransaction();
        beginTransaction.remove(this);
        if (z) {
            beginTransaction.commitAllowingStateLoss();
        } else {
            beginTransaction.commit();
        }
    }

    private void u0(@Nullable Bundle bundle) {
        if (this.i0 && !this.q0) {
            try {
                this.k0 = true;
                Dialog onCreateDialog = onCreateDialog(bundle);
                this.m0 = onCreateDialog;
                if (this.i0) {
                    setupDialog(onCreateDialog, this.f0);
                    Context context = getContext();
                    if (context instanceof Activity) {
                        this.m0.setOwnerActivity((Activity) context);
                    }
                    this.m0.setCancelable(this.h0);
                    this.m0.setOnCancelListener(this.d0);
                    this.m0.setOnDismissListener(this.e0);
                    this.q0 = true;
                } else {
                    this.m0 = null;
                }
            } finally {
                this.k0 = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.fragment.app.Fragment
    public void H(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Bundle bundle2;
        super.H(layoutInflater, viewGroup, bundle);
        if (this.H != null || this.m0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.m0.onRestoreInstanceState(bundle2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.fragment.app.Fragment
    @NonNull
    public FragmentContainer b() {
        return new e(super.b());
    }

    public void dismiss() {
        r0(false, false);
    }

    public void dismissAllowingStateLoss() {
        r0(true, false);
    }

    @Nullable
    public Dialog getDialog() {
        return this.m0;
    }

    public boolean getShowsDialog() {
        return this.i0;
    }

    @StyleRes
    public int getTheme() {
        return this.g0;
    }

    public boolean isCancelable() {
        return this.h0;
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        getViewLifecycleOwnerLiveData().observeForever(this.l0);
        if (this.p0) {
            return;
        }
        this.o0 = false;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(@NonNull DialogInterface dialogInterface) {
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.b0 = new Handler();
        this.i0 = this.x == 0;
        if (bundle != null) {
            this.f0 = bundle.getInt("android:style", 0);
            this.g0 = bundle.getInt("android:theme", 0);
            this.h0 = bundle.getBoolean("android:cancelable", true);
            this.i0 = bundle.getBoolean("android:showsDialog", this.i0);
            this.j0 = bundle.getInt("android:backStackId", -1);
        }
    }

    @NonNull
    @MainThread
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "onCreateDialog called for DialogFragment " + this);
        }
        return new Dialog(requireContext(), getTheme());
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void onDestroyView() {
        super.onDestroyView();
        Dialog dialog = this.m0;
        if (dialog != null) {
            this.n0 = true;
            dialog.setOnDismissListener(null);
            this.m0.dismiss();
            if (!this.o0) {
                onDismiss(this.m0);
            }
            this.m0 = null;
            this.q0 = false;
        }
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void onDetach() {
        super.onDetach();
        if (!this.p0 && !this.o0) {
            this.o0 = true;
        }
        getViewLifecycleOwnerLiveData().removeObserver(this.l0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(@NonNull DialogInterface dialogInterface) {
        if (this.n0) {
            return;
        }
        if (FragmentManager.x0(3)) {
            Log.d("FragmentManager", "onDismiss called for DialogFragment " + this);
        }
        r0(true, true);
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle bundle) {
        StringBuilder sb;
        String str;
        LayoutInflater onGetLayoutInflater = super.onGetLayoutInflater(bundle);
        if (this.i0 && !this.k0) {
            u0(bundle);
            if (FragmentManager.x0(2)) {
                Log.d("FragmentManager", "get layout inflater for DialogFragment " + this + " from dialog context");
            }
            Dialog dialog = this.m0;
            return dialog != null ? onGetLayoutInflater.cloneInContext(dialog.getContext()) : onGetLayoutInflater;
        }
        if (FragmentManager.x0(2)) {
            String str2 = "getting layout inflater for DialogFragment " + this;
            if (this.i0) {
                sb = new StringBuilder();
                str = "mCreatingDialog = true: ";
            } else {
                sb = new StringBuilder();
                str = "mShowsDialog = false: ";
            }
            sb.append(str);
            sb.append(str2);
            Log.d("FragmentManager", sb.toString());
        }
        return onGetLayoutInflater;
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Dialog dialog = this.m0;
        if (dialog != null) {
            Bundle onSaveInstanceState = dialog.onSaveInstanceState();
            onSaveInstanceState.putBoolean("android:dialogShowing", false);
            bundle.putBundle("android:savedDialogState", onSaveInstanceState);
        }
        int i2 = this.f0;
        if (i2 != 0) {
            bundle.putInt("android:style", i2);
        }
        int i3 = this.g0;
        if (i3 != 0) {
            bundle.putInt("android:theme", i3);
        }
        boolean z = this.h0;
        if (!z) {
            bundle.putBoolean("android:cancelable", z);
        }
        boolean z2 = this.i0;
        if (!z2) {
            bundle.putBoolean("android:showsDialog", z2);
        }
        int i4 = this.j0;
        if (i4 != -1) {
            bundle.putInt("android:backStackId", i4);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void onStart() {
        super.onStart();
        Dialog dialog = this.m0;
        if (dialog != null) {
            this.n0 = false;
            dialog.show();
            View decorView = this.m0.getWindow().getDecorView();
            ViewTreeLifecycleOwner.set(decorView, this);
            ViewTreeViewModelStoreOwner.set(decorView, this);
            ViewTreeSavedStateRegistryOwner.set(decorView, this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void onStop() {
        super.onStop();
        Dialog dialog = this.m0;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @MainThread
    public void onViewStateRestored(@Nullable Bundle bundle) {
        Bundle bundle2;
        super.onViewStateRestored(bundle);
        if (this.m0 == null || bundle == null || (bundle2 = bundle.getBundle("android:savedDialogState")) == null) {
            return;
        }
        this.m0.onRestoreInstanceState(bundle2);
    }

    @NonNull
    public final Dialog requireDialog() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            return dialog;
        }
        throw new IllegalStateException("DialogFragment " + this + " does not have a Dialog.");
    }

    @Nullable
    View s0(int i2) {
        Dialog dialog = this.m0;
        if (dialog != null) {
            return dialog.findViewById(i2);
        }
        return null;
    }

    public void setCancelable(boolean z) {
        this.h0 = z;
        Dialog dialog = this.m0;
        if (dialog != null) {
            dialog.setCancelable(z);
        }
    }

    public void setShowsDialog(boolean z) {
        this.i0 = z;
    }

    public void setStyle(int i2, @StyleRes int i3) {
        if (FragmentManager.x0(2)) {
            Log.d("FragmentManager", "Setting style and theme for DialogFragment " + this + " to " + i2 + ", " + i3);
        }
        this.f0 = i2;
        if (i2 == 2 || i2 == 3) {
            this.g0 = R.style.Theme.Panel;
        }
        if (i3 != 0) {
            this.g0 = i3;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void setupDialog(@NonNull Dialog dialog, int i2) {
        if (i2 != 1 && i2 != 2) {
            if (i2 != 3) {
                return;
            }
            Window window = dialog.getWindow();
            if (window != null) {
                window.addFlags(24);
            }
        }
        dialog.requestWindowFeature(1);
    }

    public int show(@NonNull FragmentTransaction fragmentTransaction, @Nullable String str) {
        this.o0 = false;
        this.p0 = true;
        fragmentTransaction.add(this, str);
        this.n0 = false;
        int commit = fragmentTransaction.commit();
        this.j0 = commit;
        return commit;
    }

    public void show(@NonNull FragmentManager fragmentManager, @Nullable String str) {
        this.o0 = false;
        this.p0 = true;
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(this, str);
        beginTransaction.commit();
    }

    public void showNow(@NonNull FragmentManager fragmentManager, @Nullable String str) {
        this.o0 = false;
        this.p0 = true;
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(this, str);
        beginTransaction.commitNow();
    }

    boolean t0() {
        return this.q0;
    }
}
