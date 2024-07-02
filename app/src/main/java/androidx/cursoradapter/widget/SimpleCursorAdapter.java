package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RestrictTo;

/* loaded from: classes.dex */
public class SimpleCursorAdapter extends ResourceCursorAdapter {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected int[] m;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    protected int[] n;
    private int o;
    private CursorToStringConverter p;
    private ViewBinder q;
    String[] r;

    /* loaded from: classes.dex */
    public interface CursorToStringConverter {
        CharSequence convertToString(Cursor cursor);
    }

    /* loaded from: classes.dex */
    public interface ViewBinder {
        boolean setViewValue(View view, Cursor cursor, int i2);
    }

    @Deprecated
    public SimpleCursorAdapter(Context context, int i2, Cursor cursor, String[] strArr, int[] iArr) {
        super(context, i2, cursor);
        this.o = -1;
        this.n = iArr;
        this.r = strArr;
        c(cursor, strArr);
    }

    public SimpleCursorAdapter(Context context, int i2, Cursor cursor, String[] strArr, int[] iArr, int i3) {
        super(context, i2, cursor, i3);
        this.o = -1;
        this.n = iArr;
        this.r = strArr;
        c(cursor, strArr);
    }

    private void c(Cursor cursor, String[] strArr) {
        if (cursor == null) {
            this.m = null;
            return;
        }
        int length = strArr.length;
        int[] iArr = this.m;
        if (iArr == null || iArr.length != length) {
            this.m = new int[length];
        }
        for (int i2 = 0; i2 < length; i2++) {
            this.m[i2] = cursor.getColumnIndexOrThrow(strArr[i2]);
        }
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        ViewBinder viewBinder = this.q;
        int[] iArr = this.n;
        int length = iArr.length;
        int[] iArr2 = this.m;
        for (int i2 = 0; i2 < length; i2++) {
            View findViewById = view.findViewById(iArr[i2]);
            if (findViewById != null) {
                if (viewBinder != null ? viewBinder.setViewValue(findViewById, cursor, iArr2[i2]) : false) {
                    continue;
                } else {
                    String string = cursor.getString(iArr2[i2]);
                    if (string == null) {
                        string = "";
                    }
                    if (findViewById instanceof TextView) {
                        setViewText((TextView) findViewById, string);
                    } else {
                        if (!(findViewById instanceof ImageView)) {
                            throw new IllegalStateException(findViewById.getClass().getName() + " is not a  view that can be bounds by this SimpleCursorAdapter");
                        }
                        setViewImage((ImageView) findViewById, string);
                    }
                }
            }
        }
    }

    public void changeCursorAndColumns(Cursor cursor, String[] strArr, int[] iArr) {
        this.r = strArr;
        this.n = iArr;
        c(cursor, strArr);
        super.changeCursor(cursor);
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter, androidx.cursoradapter.widget.a.InterfaceC0025a
    public CharSequence convertToString(Cursor cursor) {
        CursorToStringConverter cursorToStringConverter = this.p;
        if (cursorToStringConverter != null) {
            return cursorToStringConverter.convertToString(cursor);
        }
        int i2 = this.o;
        return i2 > -1 ? cursor.getString(i2) : super.convertToString(cursor);
    }

    public CursorToStringConverter getCursorToStringConverter() {
        return this.p;
    }

    public int getStringConversionColumn() {
        return this.o;
    }

    public ViewBinder getViewBinder() {
        return this.q;
    }

    public void setCursorToStringConverter(CursorToStringConverter cursorToStringConverter) {
        this.p = cursorToStringConverter;
    }

    public void setStringConversionColumn(int i2) {
        this.o = i2;
    }

    public void setViewBinder(ViewBinder viewBinder) {
        this.q = viewBinder;
    }

    public void setViewImage(ImageView imageView, String str) {
        try {
            imageView.setImageResource(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            imageView.setImageURI(Uri.parse(str));
        }
    }

    public void setViewText(TextView textView, String str) {
        textView.setText(str);
    }

    @Override // androidx.cursoradapter.widget.CursorAdapter
    public Cursor swapCursor(Cursor cursor) {
        c(cursor, this.r);
        return super.swapCursor(cursor);
    }
}
