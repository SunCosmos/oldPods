package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;
import androidx.annotation.RestrictTo;
import androidx.cursoradapter.widget.a;

/* loaded from: classes.dex */
public abstract class CursorAdapter extends BaseAdapter implements Filterable, a.InterfaceC0025a {

    @Deprecated
    public static final int FLAG_AUTO_REQUERY = 1;
    public static final int FLAG_REGISTER_CONTENT_OBSERVER = 2;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected boolean a;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected boolean b;

    /* renamed from: c, reason: collision with root package name */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected Cursor f672c;

    /* renamed from: d, reason: collision with root package name */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected Context f673d;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected int e;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected a f;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected DataSetObserver g;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected androidx.cursoradapter.widget.a h;

    /* renamed from: i, reason: collision with root package name */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected FilterQueryProvider f674i;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class a extends ContentObserver {
        a() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            CursorAdapter.this.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b extends DataSetObserver {
        b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            CursorAdapter cursorAdapter = CursorAdapter.this;
            cursorAdapter.a = true;
            cursorAdapter.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            CursorAdapter cursorAdapter = CursorAdapter.this;
            cursorAdapter.a = false;
            cursorAdapter.notifyDataSetInvalidated();
        }
    }

    @Deprecated
    public CursorAdapter(Context context, Cursor cursor) {
        a(context, cursor, 1);
    }

    public CursorAdapter(Context context, Cursor cursor, int i2) {
        a(context, cursor, i2);
    }

    public CursorAdapter(Context context, Cursor cursor, boolean z) {
        a(context, cursor, z ? 1 : 2);
    }

    void a(Context context, Cursor cursor, int i2) {
        b bVar;
        if ((i2 & 1) == 1) {
            i2 |= 2;
            this.b = true;
        } else {
            this.b = false;
        }
        boolean z = cursor != null;
        this.f672c = cursor;
        this.a = z;
        this.f673d = context;
        this.e = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i2 & 2) == 2) {
            this.f = new a();
            bVar = new b();
        } else {
            bVar = null;
            this.f = null;
        }
        this.g = bVar;
        if (z) {
            a aVar = this.f;
            if (aVar != null) {
                cursor.registerContentObserver(aVar);
            }
            DataSetObserver dataSetObserver = this.g;
            if (dataSetObserver != null) {
                cursor.registerDataSetObserver(dataSetObserver);
            }
        }
    }

    protected void b() {
        Cursor cursor;
        if (!this.b || (cursor = this.f672c) == null || cursor.isClosed()) {
            return;
        }
        this.a = this.f672c.requery();
    }

    public abstract void bindView(View view, Context context, Cursor cursor);

    public void changeCursor(Cursor cursor) {
        Cursor swapCursor = swapCursor(cursor);
        if (swapCursor != null) {
            swapCursor.close();
        }
    }

    public CharSequence convertToString(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        Cursor cursor;
        if (!this.a || (cursor = this.f672c) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // androidx.cursoradapter.widget.a.InterfaceC0025a
    public Cursor getCursor() {
        return this.f672c;
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        if (!this.a) {
            return null;
        }
        this.f672c.moveToPosition(i2);
        if (view == null) {
            view = newDropDownView(this.f673d, this.f672c, viewGroup);
        }
        bindView(view, this.f673d, this.f672c);
        return view;
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (this.h == null) {
            this.h = new androidx.cursoradapter.widget.a(this);
        }
        return this.h;
    }

    public FilterQueryProvider getFilterQueryProvider() {
        return this.f674i;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        Cursor cursor;
        if (!this.a || (cursor = this.f672c) == null) {
            return null;
        }
        cursor.moveToPosition(i2);
        return this.f672c;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        Cursor cursor;
        if (this.a && (cursor = this.f672c) != null && cursor.moveToPosition(i2)) {
            return this.f672c.getLong(this.e);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (!this.a) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (this.f672c.moveToPosition(i2)) {
            if (view == null) {
                view = newView(this.f673d, this.f672c, viewGroup);
            }
            bindView(view, this.f673d, this.f672c);
            return view;
        }
        throw new IllegalStateException("couldn't move cursor to position " + i2);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public View newDropDownView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return newView(context, cursor, viewGroup);
    }

    public abstract View newView(Context context, Cursor cursor, ViewGroup viewGroup);

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        FilterQueryProvider filterQueryProvider = this.f674i;
        return filterQueryProvider != null ? filterQueryProvider.runQuery(charSequence) : this.f672c;
    }

    public void setFilterQueryProvider(FilterQueryProvider filterQueryProvider) {
        this.f674i = filterQueryProvider;
    }

    public Cursor swapCursor(Cursor cursor) {
        Cursor cursor2 = this.f672c;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            a aVar = this.f;
            if (aVar != null) {
                cursor2.unregisterContentObserver(aVar);
            }
            DataSetObserver dataSetObserver = this.g;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f672c = cursor;
        if (cursor != null) {
            a aVar2 = this.f;
            if (aVar2 != null) {
                cursor.registerContentObserver(aVar2);
            }
            DataSetObserver dataSetObserver2 = this.g;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.e = cursor.getColumnIndexOrThrow("_id");
            this.a = true;
            notifyDataSetChanged();
        } else {
            this.e = -1;
            this.a = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }
}
