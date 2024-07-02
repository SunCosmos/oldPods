package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/* loaded from: classes.dex */
public abstract class ActivityResultRegistry {
    private Random a = new Random();
    private final Map<Integer, String> b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    final Map<String, Integer> f7c = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    private final Map<String, d> f8d = new HashMap();
    ArrayList<String> e = new ArrayList<>();
    final transient Map<String, c<?>> f = new HashMap();
    final Map<String, Object> g = new HashMap();
    final Bundle h = new Bundle();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Add missing generic type declarations: [I] */
    /* loaded from: classes.dex */
    public class a<I> extends ActivityResultLauncher<I> {
        final /* synthetic */ String a;
        final /* synthetic */ int b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ActivityResultContract f11c;

        a(String str, int i2, ActivityResultContract activityResultContract) {
            this.a = str;
            this.b = i2;
            this.f11c = activityResultContract;
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        @NonNull
        public ActivityResultContract<I, ?> getContract() {
            return this.f11c;
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void launch(I i2, @Nullable ActivityOptionsCompat activityOptionsCompat) {
            ActivityResultRegistry.this.e.add(this.a);
            Integer num = ActivityResultRegistry.this.f7c.get(this.a);
            ActivityResultRegistry.this.onLaunch(num != null ? num.intValue() : this.b, this.f11c, i2, activityOptionsCompat);
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void unregister() {
            ActivityResultRegistry.this.e(this.a);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [I] */
    /* loaded from: classes.dex */
    class b<I> extends ActivityResultLauncher<I> {
        final /* synthetic */ String a;
        final /* synthetic */ int b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ActivityResultContract f13c;

        b(String str, int i2, ActivityResultContract activityResultContract) {
            this.a = str;
            this.b = i2;
            this.f13c = activityResultContract;
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        @NonNull
        public ActivityResultContract<I, ?> getContract() {
            return this.f13c;
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void launch(I i2, @Nullable ActivityOptionsCompat activityOptionsCompat) {
            ActivityResultRegistry.this.e.add(this.a);
            Integer num = ActivityResultRegistry.this.f7c.get(this.a);
            ActivityResultRegistry.this.onLaunch(num != null ? num.intValue() : this.b, this.f13c, i2, activityOptionsCompat);
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void unregister() {
            ActivityResultRegistry.this.e(this.a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c<O> {
        final ActivityResultCallback<O> a;
        final ActivityResultContract<?, O> b;

        c(ActivityResultCallback<O> activityResultCallback, ActivityResultContract<?, O> activityResultContract) {
            this.a = activityResultCallback;
            this.b = activityResultContract;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d {
        final Lifecycle a;
        private final ArrayList<LifecycleEventObserver> b = new ArrayList<>();

        d(@NonNull Lifecycle lifecycle) {
            this.a = lifecycle;
        }

        void a(@NonNull LifecycleEventObserver lifecycleEventObserver) {
            this.a.addObserver(lifecycleEventObserver);
            this.b.add(lifecycleEventObserver);
        }

        void b() {
            Iterator<LifecycleEventObserver> it = this.b.iterator();
            while (it.hasNext()) {
                this.a.removeObserver(it.next());
            }
            this.b.clear();
        }
    }

    private void a(int i2, String str) {
        this.b.put(Integer.valueOf(i2), str);
        this.f7c.put(str, Integer.valueOf(i2));
    }

    private <O> void b(String str, int i2, @Nullable Intent intent, @Nullable c<O> cVar) {
        ActivityResultCallback<O> activityResultCallback;
        if (cVar != null && (activityResultCallback = cVar.a) != null) {
            activityResultCallback.onActivityResult(cVar.b.parseResult(i2, intent));
        } else {
            this.g.remove(str);
            this.h.putParcelable(str, new ActivityResult(i2, intent));
        }
    }

    private int c() {
        int nextInt = this.a.nextInt(2147418112);
        while (true) {
            int i2 = nextInt + 65536;
            if (!this.b.containsKey(Integer.valueOf(i2))) {
                return i2;
            }
            nextInt = this.a.nextInt(2147418112);
        }
    }

    private int d(String str) {
        Integer num = this.f7c.get(str);
        if (num != null) {
            return num.intValue();
        }
        int c2 = c();
        a(c2, str);
        return c2;
    }

    @MainThread
    public final boolean dispatchResult(int i2, int i3, @Nullable Intent intent) {
        String str = this.b.get(Integer.valueOf(i2));
        if (str == null) {
            return false;
        }
        this.e.remove(str);
        b(str, i3, intent, this.f.get(str));
        return true;
    }

    @MainThread
    public final <O> boolean dispatchResult(int i2, @SuppressLint({"UnknownNullness"}) O o) {
        ActivityResultCallback<?> activityResultCallback;
        String str = this.b.get(Integer.valueOf(i2));
        if (str == null) {
            return false;
        }
        this.e.remove(str);
        c<?> cVar = this.f.get(str);
        if (cVar != null && (activityResultCallback = cVar.a) != null) {
            activityResultCallback.onActivityResult(o);
            return true;
        }
        this.h.remove(str);
        this.g.put(str, o);
        return true;
    }

    @MainThread
    final void e(@NonNull String str) {
        Integer remove;
        if (!this.e.contains(str) && (remove = this.f7c.remove(str)) != null) {
            this.b.remove(remove);
        }
        this.f.remove(str);
        if (this.g.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.g.get(str));
            this.g.remove(str);
        }
        if (this.h.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + this.h.getParcelable(str));
            this.h.remove(str);
        }
        d dVar = this.f8d.get(str);
        if (dVar != null) {
            dVar.b();
            this.f8d.remove(str);
        }
    }

    @MainThread
    public abstract <I, O> void onLaunch(int i2, @NonNull ActivityResultContract<I, O> activityResultContract, @SuppressLint({"UnknownNullness"}) I i3, @Nullable ActivityOptionsCompat activityOptionsCompat);

    public final void onRestoreInstanceState(@Nullable Bundle bundle) {
        if (bundle == null) {
            return;
        }
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
        if (stringArrayList == null || integerArrayList == null) {
            return;
        }
        this.e = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
        this.a = (Random) bundle.getSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT");
        this.h.putAll(bundle.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT"));
        for (int i2 = 0; i2 < stringArrayList.size(); i2++) {
            String str = stringArrayList.get(i2);
            if (this.f7c.containsKey(str)) {
                Integer remove = this.f7c.remove(str);
                if (!this.h.containsKey(str)) {
                    this.b.remove(remove);
                }
            }
            a(integerArrayList.get(i2).intValue(), stringArrayList.get(i2));
        }
    }

    public final void onSaveInstanceState(@NonNull Bundle bundle) {
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(this.f7c.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(this.f7c.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(this.e));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) this.h.clone());
        bundle.putSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT", this.a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public final <I, O> ActivityResultLauncher<I> register(@NonNull String str, @NonNull ActivityResultContract<I, O> activityResultContract, @NonNull ActivityResultCallback<O> activityResultCallback) {
        int d2 = d(str);
        this.f.put(str, new c<>(activityResultCallback, activityResultContract));
        if (this.g.containsKey(str)) {
            Object obj = this.g.get(str);
            this.g.remove(str);
            activityResultCallback.onActivityResult(obj);
        }
        ActivityResult activityResult = (ActivityResult) this.h.getParcelable(str);
        if (activityResult != null) {
            this.h.remove(str);
            activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
        }
        return new b(str, d2, activityResultContract);
    }

    @NonNull
    public final <I, O> ActivityResultLauncher<I> register(@NonNull final String str, @NonNull LifecycleOwner lifecycleOwner, @NonNull final ActivityResultContract<I, O> activityResultContract, @NonNull final ActivityResultCallback<O> activityResultCallback) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            throw new IllegalStateException("LifecycleOwner " + lifecycleOwner + " is attempting to register while current state is " + lifecycle.getCurrentState() + ". LifecycleOwners must call register before they are STARTED.");
        }
        int d2 = d(str);
        d dVar = this.f8d.get(str);
        if (dVar == null) {
            dVar = new d(lifecycle);
        }
        dVar.a(new LifecycleEventObserver() { // from class: androidx.activity.result.ActivityResultRegistry.1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner2, @NonNull Lifecycle.Event event) {
                if (!Lifecycle.Event.ON_START.equals(event)) {
                    if (Lifecycle.Event.ON_STOP.equals(event)) {
                        ActivityResultRegistry.this.f.remove(str);
                        return;
                    } else {
                        if (Lifecycle.Event.ON_DESTROY.equals(event)) {
                            ActivityResultRegistry.this.e(str);
                            return;
                        }
                        return;
                    }
                }
                ActivityResultRegistry.this.f.put(str, new c<>(activityResultCallback, activityResultContract));
                if (ActivityResultRegistry.this.g.containsKey(str)) {
                    Object obj = ActivityResultRegistry.this.g.get(str);
                    ActivityResultRegistry.this.g.remove(str);
                    activityResultCallback.onActivityResult(obj);
                }
                ActivityResult activityResult = (ActivityResult) ActivityResultRegistry.this.h.getParcelable(str);
                if (activityResult != null) {
                    ActivityResultRegistry.this.h.remove(str);
                    activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
                }
            }
        });
        this.f8d.put(str, dVar);
        return new a(str, d2, activityResultContract);
    }
}
