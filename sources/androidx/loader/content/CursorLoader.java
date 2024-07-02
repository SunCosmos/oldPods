package androidx.loader.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContentResolverCompat;
import androidx.core.os.CancellationSignal;
import androidx.core.os.OperationCanceledException;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

/* loaded from: classes.dex */
public class CursorLoader extends AsyncTaskLoader<Cursor> {
    final Loader<Cursor>.ForceLoadContentObserver p;
    Uri q;
    String[] r;
    String s;
    String[] t;
    String u;
    Cursor v;
    CancellationSignal w;

    public CursorLoader(@NonNull Context context) {
        super(context);
        this.p = new Loader.ForceLoadContentObserver();
    }

    public CursorLoader(@NonNull Context context, @NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        super(context);
        this.p = new Loader.ForceLoadContentObserver();
        this.q = uri;
        this.r = strArr;
        this.s = str;
        this.t = strArr2;
        this.u = str2;
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
        synchronized (this) {
            CancellationSignal cancellationSignal = this.w;
            if (cancellationSignal != null) {
                cancellationSignal.cancel();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.loader.content.Loader
    public void d() {
        super.d();
        f();
        Cursor cursor = this.v;
        if (cursor != null && !cursor.isClosed()) {
            this.v.close();
        }
        this.v = null;
    }

    @Override // androidx.loader.content.Loader
    public void deliverResult(Cursor cursor) {
        if (isReset()) {
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        Cursor cursor2 = this.v;
        this.v = cursor;
        if (isStarted()) {
            super.deliverResult((CursorLoader) cursor);
        }
        if (cursor2 == null || cursor2 == cursor || cursor2.isClosed()) {
            return;
        }
        cursor2.close();
    }

    @Override // androidx.loader.content.AsyncTaskLoader, androidx.loader.content.Loader
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("mUri=");
        printWriter.println(this.q);
        printWriter.print(str);
        printWriter.print("mProjection=");
        printWriter.println(Arrays.toString(this.r));
        printWriter.print(str);
        printWriter.print("mSelection=");
        printWriter.println(this.s);
        printWriter.print(str);
        printWriter.print("mSelectionArgs=");
        printWriter.println(Arrays.toString(this.t));
        printWriter.print(str);
        printWriter.print("mSortOrder=");
        printWriter.println(this.u);
        printWriter.print(str);
        printWriter.print("mCursor=");
        printWriter.println(this.v);
        printWriter.print(str);
        printWriter.print("mContentChanged=");
        printWriter.println(this.h);
    }

    @Override // androidx.loader.content.Loader
    protected void e() {
        Cursor cursor = this.v;
        if (cursor != null) {
            deliverResult(cursor);
        }
        if (takeContentChanged() || this.v == null) {
            forceLoad();
        }
    }

    @Override // androidx.loader.content.Loader
    protected void f() {
        cancelLoad();
    }

    @Nullable
    public String[] getProjection() {
        return this.r;
    }

    @Nullable
    public String getSelection() {
        return this.s;
    }

    @Nullable
    public String[] getSelectionArgs() {
        return this.t;
    }

    @Nullable
    public String getSortOrder() {
        return this.u;
    }

    @NonNull
    public Uri getUri() {
        return this.q;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.loader.content.AsyncTaskLoader
    public Cursor loadInBackground() {
        synchronized (this) {
            if (isLoadInBackgroundCanceled()) {
                throw new OperationCanceledException();
            }
            this.w = new CancellationSignal();
        }
        try {
            Cursor query = ContentResolverCompat.query(getContext().getContentResolver(), this.q, this.r, this.s, this.t, this.u, this.w);
            if (query != null) {
                try {
                    query.getCount();
                    query.registerContentObserver(this.p);
                } catch (RuntimeException e) {
                    query.close();
                    throw e;
                }
            }
            synchronized (this) {
                this.w = null;
            }
            return query;
        } catch (Throwable th) {
            synchronized (this) {
                this.w = null;
                throw th;
            }
        }
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public void onCanceled(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return;
        }
        cursor.close();
    }

    public void setProjection(@Nullable String[] strArr) {
        this.r = strArr;
    }

    public void setSelection(@Nullable String str) {
        this.s = str;
    }

    public void setSelectionArgs(@Nullable String[] strArr) {
        this.t = strArr;
    }

    public void setSortOrder(@Nullable String str) {
        this.u = str;
    }

    public void setUri(@NonNull Uri uri) {
        this.q = uri;
    }
}
