package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public abstract class FragmentHostCallback<E> extends FragmentContainer {

    @Nullable
    private final Activity a;

    @NonNull
    private final Context b;

    /* renamed from: c, reason: collision with root package name */
    @NonNull
    private final Handler f715c;

    /* renamed from: d, reason: collision with root package name */
    private final int f716d;
    final FragmentManager e;

    FragmentHostCallback(@Nullable Activity activity, @NonNull Context context, @NonNull Handler handler, int i2) {
        this.e = new f();
        this.a = activity;
        this.b = (Context) Preconditions.checkNotNull(context, "context == null");
        this.f715c = (Handler) Preconditions.checkNotNull(handler, "handler == null");
        this.f716d = i2;
    }

    public FragmentHostCallback(@NonNull Context context, @NonNull Handler handler, int i2) {
        this(context instanceof Activity ? (Activity) context : null, context, handler, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentHostCallback(@NonNull FragmentActivity fragmentActivity) {
        this(fragmentActivity, fragmentActivity, new Handler(), 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Activity a() {
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Context b() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Handler c() {
        return this.f715c;
    }

    public void onDump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
    }

    @Override // androidx.fragment.app.FragmentContainer
    @Nullable
    public View onFindViewById(int i2) {
        return null;
    }

    @Nullable
    public abstract E onGetHost();

    @NonNull
    public LayoutInflater onGetLayoutInflater() {
        return LayoutInflater.from(this.b);
    }

    public int onGetWindowAnimations() {
        return this.f716d;
    }

    @Override // androidx.fragment.app.FragmentContainer
    public boolean onHasView() {
        return true;
    }

    public boolean onHasWindowAnimations() {
        return true;
    }

    @Deprecated
    public void onRequestPermissionsFromFragment(@NonNull Fragment fragment, @NonNull String[] strArr, int i2) {
    }

    public boolean onShouldSaveFragmentState(@NonNull Fragment fragment) {
        return true;
    }

    public boolean onShouldShowRequestPermissionRationale(@NonNull String str) {
        return false;
    }

    public void onStartActivityFromFragment(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i2) {
        onStartActivityFromFragment(fragment, intent, i2, null);
    }

    public void onStartActivityFromFragment(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i2, @Nullable Bundle bundle) {
        if (i2 != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        ContextCompat.startActivity(this.b, intent, bundle);
    }

    @Deprecated
    public void onStartIntentSenderFromFragment(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i2, @Nullable Intent intent, int i3, int i4, int i5, @Nullable Bundle bundle) {
        if (i2 != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        ActivityCompat.startIntentSenderForResult(this.a, intentSender, i2, intent, i3, i4, i5, bundle);
    }

    public void onSupportInvalidateOptionsMenu() {
    }
}
