package android.support.v4.provider;

import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: lib/Mus.dex */
class RawDocumentFile extends DocumentFile {
    private File mFile;

    public RawDocumentFile(DocumentFile documentFile, File file) {
        super(documentFile);
        this.mFile = file;
    }

    @Override // android.support.v4.provider.DocumentFile
    public DocumentFile createFile(String str, String str2) {
        String str3 = str2;
        String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(str);
        if (extensionFromMimeType != null) {
            str3 = str3 + "." + extensionFromMimeType;
        }
        File file = new File(this.mFile, str3);
        try {
            file.createNewFile();
            return new RawDocumentFile(this, file);
        } catch (IOException e) {
            Log.w("DocumentFile", "Failed to createFile: " + e);
            return null;
        }
    }

    @Override // android.support.v4.provider.DocumentFile
    public DocumentFile createDirectory(String str) {
        File file = new File(this.mFile, str);
        if (file.isDirectory() || file.mkdir()) {
            return new RawDocumentFile(this, file);
        }
        return null;
    }

    @Override // android.support.v4.provider.DocumentFile
    public Uri getUri() {
        return Uri.fromFile(this.mFile);
    }

    @Override // android.support.v4.provider.DocumentFile
    public String getName() {
        return this.mFile.getName();
    }

    @Override // android.support.v4.provider.DocumentFile
    public String getType() {
        if (!this.mFile.isDirectory()) {
            return getTypeForName(this.mFile.getName());
        }
        return null;
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean isDirectory() {
        return this.mFile.isDirectory();
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean isFile() {
        return this.mFile.isFile();
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean isVirtual() {
        return false;
    }

    @Override // android.support.v4.provider.DocumentFile
    public long lastModified() {
        return this.mFile.lastModified();
    }

    @Override // android.support.v4.provider.DocumentFile
    public long length() {
        return this.mFile.length();
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean canRead() {
        return this.mFile.canRead();
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean canWrite() {
        return this.mFile.canWrite();
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean delete() {
        deleteContents(this.mFile);
        return this.mFile.delete();
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean exists() {
        return this.mFile.exists();
    }

    @Override // android.support.v4.provider.DocumentFile
    public DocumentFile[] listFiles() {
        ArrayList arrayList = new ArrayList();
        File[] listFiles = this.mFile.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                arrayList.add(new RawDocumentFile(this, file));
            }
        }
        return (DocumentFile[]) arrayList.toArray(new DocumentFile[arrayList.size()]);
    }

    @Override // android.support.v4.provider.DocumentFile
    public boolean renameTo(String str) {
        File file = new File(this.mFile.getParentFile(), str);
        if (!this.mFile.renameTo(file)) {
            return false;
        }
        this.mFile = file;
        return true;
    }

    private static String getTypeForName(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf >= 0) {
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str.substring(lastIndexOf + 1).toLowerCase());
            if (mimeTypeFromExtension != null) {
                return mimeTypeFromExtension;
            }
        }
        return "application/octet-stream";
    }

    private static boolean deleteContents(File file) {
        File[] listFiles = file.listFiles();
        boolean z = true;
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    z &= deleteContents(file2);
                }
                if (!file2.delete()) {
                    Log.w("DocumentFile", "Failed to delete " + file2);
                    z = false;
                }
            }
        }
        return z;
    }
}
