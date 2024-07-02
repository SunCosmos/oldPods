package androidx.core.util;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public class AtomicFile {
    private final File a;
    private final File b;

    /* renamed from: c, reason: collision with root package name */
    private final File f605c;

    public AtomicFile(@NonNull File file) {
        this.a = file;
        this.b = new File(file.getPath() + ".new");
        this.f605c = new File(file.getPath() + ".bak");
    }

    private static void a(@NonNull File file, @NonNull File file2) {
        if (file2.isDirectory() && !file2.delete()) {
            Log.e("AtomicFile", "Failed to delete file which is a directory " + file2);
        }
        if (file.renameTo(file2)) {
            return;
        }
        Log.e("AtomicFile", "Failed to rename " + file + " to " + file2);
    }

    private static boolean b(@NonNull FileOutputStream fileOutputStream) {
        try {
            fileOutputStream.getFD().sync();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public void delete() {
        this.a.delete();
        this.b.delete();
        this.f605c.delete();
    }

    public void failWrite(@Nullable FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) {
            return;
        }
        if (!b(fileOutputStream)) {
            Log.e("AtomicFile", "Failed to sync file output stream");
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e("AtomicFile", "Failed to close file output stream", e);
        }
        if (this.b.delete()) {
            return;
        }
        Log.e("AtomicFile", "Failed to delete new file " + this.b);
    }

    public void finishWrite(@Nullable FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) {
            return;
        }
        if (!b(fileOutputStream)) {
            Log.e("AtomicFile", "Failed to sync file output stream");
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            Log.e("AtomicFile", "Failed to close file output stream", e);
        }
        a(this.b, this.a);
    }

    @NonNull
    public File getBaseFile() {
        return this.a;
    }

    @NonNull
    public FileInputStream openRead() {
        if (this.f605c.exists()) {
            a(this.f605c, this.a);
        }
        if (this.b.exists() && this.a.exists() && !this.b.delete()) {
            Log.e("AtomicFile", "Failed to delete outdated new file " + this.b);
        }
        return new FileInputStream(this.a);
    }

    @NonNull
    public byte[] readFully() {
        FileInputStream openRead = openRead();
        try {
            byte[] bArr = new byte[openRead.available()];
            int i2 = 0;
            while (true) {
                int read = openRead.read(bArr, i2, bArr.length - i2);
                if (read <= 0) {
                    return bArr;
                }
                i2 += read;
                int available = openRead.available();
                if (available > bArr.length - i2) {
                    byte[] bArr2 = new byte[available + i2];
                    System.arraycopy(bArr, 0, bArr2, 0, i2);
                    bArr = bArr2;
                }
            }
        } finally {
            openRead.close();
        }
    }

    @NonNull
    public FileOutputStream startWrite() {
        if (this.f605c.exists()) {
            a(this.f605c, this.a);
        }
        try {
            return new FileOutputStream(this.b);
        } catch (FileNotFoundException unused) {
            if (!this.b.getParentFile().mkdirs()) {
                throw new IOException("Failed to create directory for " + this.b);
            }
            try {
                return new FileOutputStream(this.b);
            } catch (FileNotFoundException e) {
                throw new IOException("Failed to create new file " + this.b, e);
            }
        }
    }
}
