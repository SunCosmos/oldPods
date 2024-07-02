package androidx.documentfile.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;

@RequiresApi(21)
/* loaded from: classes.dex */
class d extends DocumentFile {
    private Context b;

    /* renamed from: c */
    private Uri f683c;

    public d(@Nullable DocumentFile documentFile, Context context, Uri uri) {
        super(documentFile);
        this.b = context;
        this.f683c = uri;
    }

    private static void a(@Nullable AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    @Nullable
    private static Uri b(Context context, Uri uri, String str, String str2) {
        try {
            return DocumentsContract.createDocument(context.getContentResolver(), uri, str, str2);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean canRead() {
        return a.a(this.b, this.f683c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean canWrite() {
        return a.b(this.b, this.f683c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    @Nullable
    public DocumentFile createDirectory(String str) {
        Uri b = b(this.b, this.f683c, "vnd.android.document/directory", str);
        if (b != null) {
            return new d(this, this.b, b);
        }
        return null;
    }

    @Override // androidx.documentfile.provider.DocumentFile
    @Nullable
    public DocumentFile createFile(String str, String str2) {
        Uri b = b(this.b, this.f683c, str, str2);
        if (b != null) {
            return new d(this, this.b, b);
        }
        return null;
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean delete() {
        try {
            return DocumentsContract.deleteDocument(this.b.getContentResolver(), this.f683c);
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean exists() {
        return a.d(this.b, this.f683c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    @Nullable
    public String getName() {
        return a.f(this.b, this.f683c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    @Nullable
    public String getType() {
        return a.h(this.b, this.f683c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public Uri getUri() {
        return this.f683c;
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean isDirectory() {
        return a.i(this.b, this.f683c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean isFile() {
        return a.j(this.b, this.f683c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean isVirtual() {
        return a.k(this.b, this.f683c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public long lastModified() {
        return a.l(this.b, this.f683c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public long length() {
        return a.m(this.b, this.f683c);
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public DocumentFile[] listFiles() {
        ContentResolver contentResolver = this.b.getContentResolver();
        Uri uri = this.f683c;
        Uri buildChildDocumentsUriUsingTree = DocumentsContract.buildChildDocumentsUriUsingTree(uri, DocumentsContract.getDocumentId(uri));
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                cursor = contentResolver.query(buildChildDocumentsUriUsingTree, new String[]{"document_id"}, null, null, null);
                while (cursor.moveToNext()) {
                    arrayList.add(DocumentsContract.buildDocumentUriUsingTree(this.f683c, cursor.getString(0)));
                }
            } catch (Exception e) {
                Log.w("DocumentFile", "Failed query: " + e);
            }
            Uri[] uriArr = (Uri[]) arrayList.toArray(new Uri[arrayList.size()]);
            DocumentFile[] documentFileArr = new DocumentFile[uriArr.length];
            for (int i2 = 0; i2 < uriArr.length; i2++) {
                documentFileArr[i2] = new d(this, this.b, uriArr[i2]);
            }
            return documentFileArr;
        } finally {
            a(cursor);
        }
    }

    @Override // androidx.documentfile.provider.DocumentFile
    public boolean renameTo(String str) {
        try {
            Uri renameDocument = DocumentsContract.renameDocument(this.b.getContentResolver(), this.f683c, str);
            if (renameDocument != null) {
                this.f683c = renameDocument;
                return true;
            }
        } catch (Exception unused) {
        }
        return false;
    }
}
