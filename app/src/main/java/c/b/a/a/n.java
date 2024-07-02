package c.b.a.a;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;

/* loaded from: classes.dex */
public class n {
    public static boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        try {
            sQLiteDatabase.execSQL("INSERT INTO " + str + " (" + str2 + ") VALUES (" + str3 + ")");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean b(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE " + str + " (" + str2 + ")");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean c(Context context, String str, boolean z) {
        if (z) {
            return context.deleteDatabase(str);
        }
        boolean delete = new File(str).delete();
        new File(str + "-journal").delete();
        return delete;
    }

    public static boolean d(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        String str3;
        try {
            if (str2 != null) {
                str3 = "DELETE FROM " + str + " WHERE " + str2;
            } else {
                str3 = "DELETE FROM " + str;
            }
            sQLiteDatabase.execSQL(str3);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean e(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL("DROP TABLE " + str);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean f(Context context, String str, boolean z) {
        if (z) {
            str = "/data/data/" + context.getPackageName() + "/databases/" + str;
        }
        try {
            SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(str, null, 1);
            if (openDatabase != null) {
                openDatabase.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static SQLiteDatabase g(Context context, String str, boolean z) {
        try {
            if (z) {
                return context.openOrCreateDatabase(str, 0, null);
            }
            d.b(str, false);
            return SQLiteDatabase.openOrCreateDatabase(str, (SQLiteDatabase.CursorFactory) null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Cursor h(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        try {
            if (str3 == null) {
                return sQLiteDatabase.rawQuery("SELECT " + str + " from " + str2, null);
            }
            return sQLiteDatabase.rawQuery("SELECT " + str + " from " + str2 + " WHERE " + str3, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean i(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT count(name) as c FROM sqlite_master where type='table' and name='" + str + "'", null);
            if (rawQuery.moveToNext()) {
                if (rawQuery.getInt(0) > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean j(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        String str4;
        try {
            if (str3 != null) {
                str4 = "UPDATE " + str + " SET " + str2 + "  WHERE " + str3;
            } else {
                str4 = "UPDATE " + str + " SET " + str2;
            }
            sQLiteDatabase.execSQL(str4);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
