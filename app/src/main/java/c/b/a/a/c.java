package c.b.a.a;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* loaded from: classes.dex */
public class c {
    private static boolean a(String str, boolean z) {
        return (z ? new File(str) : new File(new File(str).getParent())).mkdirs();
    }

    public static void b(String str, String str2, ZipOutputStream zipOutputStream) {
        if (zipOutputStream == null) {
            return;
        }
        File file = new File(str + str2);
        if (file.isFile()) {
            ZipEntry zipEntry = new ZipEntry(str2);
            FileInputStream fileInputStream = new FileInputStream(file);
            String lowerCase = file.getName().toLowerCase();
            if (lowerCase.endsWith(".mp3") || lowerCase.endsWith(".ogg") || lowerCase.endsWith(".amr") || lowerCase.endsWith(".wma") || lowerCase.endsWith(".aac") || lowerCase.endsWith(".wav") || lowerCase.endsWith(".ra") || lowerCase.endsWith(".mid") || lowerCase.endsWith(".ape")) {
                zipEntry.setCrc(d(file));
                zipEntry.setMethod(0);
                zipEntry.setCompressedSize(file.length());
            }
            zipOutputStream.putNextEntry(zipEntry);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                } else {
                    zipOutputStream.write(bArr, 0, read);
                }
            }
            fileInputStream.close();
        } else {
            String[] list = file.list();
            if (list != null && list.length > 0) {
                for (String str3 : list) {
                    b(str, str2 + File.separator + str3, zipOutputStream);
                }
                return;
            }
            zipOutputStream.putNextEntry(new ZipEntry(str2 + File.separator));
        }
        zipOutputStream.closeEntry();
    }

    public static void c(String str, String str2, boolean z) {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(str2));
        File file = new File(str);
        if (!z || file.isFile()) {
            b(file.getParent() + File.separator, file.getName(), zipOutputStream);
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    b(file2.getParent() + File.separator, file2.getName(), zipOutputStream);
                }
            }
        }
        zipOutputStream.finish();
        zipOutputStream.close();
    }

    public static long d(File file) {
        CRC32 crc32 = new CRC32();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        byte[] bArr = new byte[1024];
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read == -1) {
                bufferedInputStream.close();
                return crc32.getValue();
            }
            crc32.update(bArr, 0, read);
        }
    }

    public static int e(InputStream inputStream, String str, String str2, boolean z) {
        if (!str2.replace('\\', '/').endsWith("/")) {
            str2 = str2 + "/";
        }
        ZipInputStream zipInputStream = null;
        int i2 = 0;
        try {
            ZipInputStream zipInputStream2 = new ZipInputStream(inputStream);
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream2.getNextEntry();
                    if (nextEntry == null) {
                        zipInputStream2.close();
                        return i2;
                    }
                    if (nextEntry.getName().startsWith(str)) {
                        i(zipInputStream2, nextEntry, str2 + nextEntry.getName(), z);
                        i2++;
                    }
                } catch (Throwable th) {
                    th = th;
                    zipInputStream = zipInputStream2;
                    if (zipInputStream != null) {
                        zipInputStream.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static int f(String str, String str2, String str3, boolean z) {
        ZipInputStream zipInputStream;
        Throwable th;
        if (!str3.replace('\\', '/').endsWith("/")) {
            str3 = str3 + "/";
        }
        int i2 = 0;
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(new File(str)));
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        zipInputStream.close();
                        return i2;
                    }
                    if (nextEntry.getName().startsWith(str2)) {
                        i(zipInputStream, nextEntry, str3 + nextEntry.getName(), z);
                        i2++;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (zipInputStream != null) {
                        zipInputStream.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th3) {
            zipInputStream = null;
            th = th3;
        }
    }

    public static void g(InputStream inputStream, String str, boolean z) {
        if (!str.replace('\\', '/').endsWith("/")) {
            str = str + "/";
        }
        ZipInputStream zipInputStream = null;
        try {
            ZipInputStream zipInputStream2 = new ZipInputStream(inputStream);
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream2.getNextEntry();
                    if (nextEntry == null) {
                        zipInputStream2.close();
                        return;
                    }
                    i(zipInputStream2, nextEntry, str + nextEntry.getName(), z);
                } catch (Throwable th) {
                    th = th;
                    zipInputStream = zipInputStream2;
                    if (zipInputStream != null) {
                        zipInputStream.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void h(String str, String str2, boolean z) {
        ZipInputStream zipInputStream;
        Throwable th;
        if (!str2.replace('\\', '/').endsWith("/")) {
            str2 = str2 + "/";
        }
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(new File(str)));
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        zipInputStream.close();
                        return;
                    }
                    i(zipInputStream, nextEntry, str2 + nextEntry.getName(), z);
                } catch (Throwable th2) {
                    th = th2;
                    if (zipInputStream != null) {
                        zipInputStream.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th3) {
            zipInputStream = null;
            th = th3;
        }
    }

    public static void i(ZipInputStream zipInputStream, ZipEntry zipEntry, String str, boolean z) {
        a(str, false);
        File file = new File(str);
        if (file.exists()) {
            if (!z) {
                return;
            }
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    if (!zipEntry.isDirectory()) {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                        try {
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int read = zipInputStream.read(bArr);
                                if (read == -1) {
                                    break;
                                } else {
                                    fileOutputStream2.write(bArr, 0, read);
                                }
                            }
                            fileOutputStream = fileOutputStream2;
                        } catch (FileNotFoundException e2) {
                            e = e2;
                            fileOutputStream = fileOutputStream2;
                            e.printStackTrace();
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            return;
                        } catch (IOException e3) {
                            e = e3;
                            fileOutputStream = fileOutputStream2;
                            e.printStackTrace();
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            return;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (FileNotFoundException e5) {
                    e = e5;
                } catch (IOException e6) {
                    e = e6;
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
