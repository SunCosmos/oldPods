package androidx.core.view.inputmethod;

import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.view.inputmethod.InputContentInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public final class InputContentInfoCompat {
    private final c a;

    @RequiresApi(25)
    /* loaded from: classes.dex */
    private static final class a implements c {

        @NonNull
        final InputContentInfo a;

        a(@NonNull Uri uri, @NonNull ClipDescription clipDescription, @Nullable Uri uri2) {
            this.a = new InputContentInfo(uri, clipDescription, uri2);
        }

        a(@NonNull Object obj) {
            this.a = (InputContentInfo) obj;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        public void a() {
            this.a.requestPermission();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        public void b() {
            this.a.releasePermission();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @Nullable
        public Uri c() {
            return this.a.getLinkUri();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @NonNull
        public ClipDescription d() {
            return this.a.getDescription();
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @Nullable
        public Object e() {
            return this.a;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @NonNull
        public Uri f() {
            return this.a.getContentUri();
        }
    }

    /* loaded from: classes.dex */
    private static final class b implements c {

        @NonNull
        private final Uri a;

        @NonNull
        private final ClipDescription b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        private final Uri f657c;

        b(@NonNull Uri uri, @NonNull ClipDescription clipDescription, @Nullable Uri uri2) {
            this.a = uri;
            this.b = clipDescription;
            this.f657c = uri2;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        public void a() {
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        public void b() {
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @Nullable
        public Uri c() {
            return this.f657c;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @NonNull
        public ClipDescription d() {
            return this.b;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @Nullable
        public Object e() {
            return null;
        }

        @Override // androidx.core.view.inputmethod.InputContentInfoCompat.c
        @NonNull
        public Uri f() {
            return this.a;
        }
    }

    /* loaded from: classes.dex */
    private interface c {
        void a();

        void b();

        @Nullable
        Uri c();

        @NonNull
        ClipDescription d();

        @Nullable
        Object e();

        @NonNull
        Uri f();
    }

    public InputContentInfoCompat(@NonNull Uri uri, @NonNull ClipDescription clipDescription, @Nullable Uri uri2) {
        this.a = Build.VERSION.SDK_INT >= 25 ? new a(uri, clipDescription, uri2) : new b(uri, clipDescription, uri2);
    }

    private InputContentInfoCompat(@NonNull c cVar) {
        this.a = cVar;
    }

    @Nullable
    public static InputContentInfoCompat wrap(@Nullable Object obj) {
        if (obj != null && Build.VERSION.SDK_INT >= 25) {
            return new InputContentInfoCompat(new a(obj));
        }
        return null;
    }

    @NonNull
    public Uri getContentUri() {
        return this.a.f();
    }

    @NonNull
    public ClipDescription getDescription() {
        return this.a.d();
    }

    @Nullable
    public Uri getLinkUri() {
        return this.a.c();
    }

    public void releasePermission() {
        this.a.b();
    }

    public void requestPermission() {
        this.a.a();
    }

    @Nullable
    public Object unwrap() {
        return this.a.e();
    }
}
