package c.b.a.a;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.iapp.app.Aid_YuCodeX;
import com.iapp.app.run.mian;
import com.iapp.app.z;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class f {
    public static int a = 2131231173;
    public static int b = 2131689499;

    /* renamed from: c, reason: collision with root package name */
    private static long f1345c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements MediaPlayer.OnCompletionListener {
        a() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.release();
        }
    }

    public static void A(Context context, Object obj) {
        if (t.u == 0) {
            return;
        }
        if (t.m.length() > 6000) {
            t.m = t.m.substring(0, 3000);
        }
        StringBuffer stringBuffer = new StringBuffer("[");
        stringBuffer.append(new SimpleDateFormat("HH:mm:ss:SSS").format(new Date()));
        stringBuffer.append("]  ");
        stringBuffer.append(obj);
        stringBuffer.append('\n');
        stringBuffer.append(t.m);
        t.m = stringBuffer.toString();
        Log.v("ygs", String.valueOf(obj));
        if (t.u != 2 || context == null) {
            return;
        }
        long time = new Date().getTime();
        if (time - f1345c > 1000) {
            d.k(d.m(context) + "/iApp/Log/" + String.valueOf(com.iapp.app.a.f1931c) + ".log", t.m);
            f1345c = time;
        }
    }

    public static Bundle B(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("OpenFilexmlui", str);
        return bundle;
    }

    public static String C(Context context, String str) {
        return com.iapp.app.b.h5(context, str.toLowerCase());
    }

    public static void D(Object obj, Context context, String str, Object obj2, int i2, Object obj3, Object obj4) {
        String lowerCase = str.toLowerCase();
        com.iapp.app.b.h4(context, lowerCase, obj instanceof Aid_YuCodeX ? lowerCase.endsWith(".iyu") ? new Object[]{obj, obj2, Integer.valueOf(i2), obj3, obj4, lowerCase.substring(0, lowerCase.length() - 4)} : new Object[]{obj, obj2, Integer.valueOf(i2), obj3, obj4, lowerCase} : new Object[]{obj, obj2, Integer.valueOf(i2), obj3, obj4});
    }

    public static void E(Context context, Activity activity, String str, String str2) {
        if (mian.sh) {
            new t(context, activity).e(str2);
            return;
        }
        Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(context, activity);
        int indexOf = str.indexOf(46);
        mian.c(str.substring(0, indexOf), str.substring(indexOf + 1) + str2, aid_YuCodeX);
    }

    public static void F(Context context, Activity activity, String[] strArr, Object[] objArr, String str, String str2) {
        if (mian.sh) {
            t tVar = new t(context, activity);
            for (int i2 = 0; i2 < objArr.length; i2++) {
                tVar.T(strArr[i2].trim(), objArr[i2]);
            }
            tVar.e(str2);
            return;
        }
        Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(context, activity);
        for (int i3 = 0; i3 < objArr.length; i3++) {
            aid_YuCodeX.dim(strArr[i3].trim(), objArr[i3]);
        }
        int indexOf = str.indexOf(46);
        mian.c(str.substring(0, indexOf), str.substring(indexOf + 1) + str2, aid_YuCodeX);
    }

    public static void G(Context context, String str) {
        com.iapp.app.b.h6(context, str.toLowerCase(), t.l);
    }

    public static String a(Context context, String str) {
        return com.iapp.app.b.h(context, str.toLowerCase());
    }

    public static Bitmap b(Context context, String str) {
        return str.startsWith("@") ? i.b(context, d.p(context, str)) : i.c(d.p(context, str));
    }

    public static Bitmap c(Context context, String str) {
        InputStream open;
        long available;
        boolean z;
        Bitmap bitmap = null;
        if (str == null) {
            return null;
        }
        String p = d.p(context, str);
        if (str.startsWith("@")) {
            try {
                open = context.getAssets().open(p);
                available = open.available();
                z = true;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            File file = new File(p);
            if (!file.exists()) {
                return null;
            }
            available = file.length();
            open = null;
            z = false;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = available >= 67584 ? available < 204800 ? 2 : available < 512000 ? 3 : available < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED ? 5 : 10 : 1;
        options.inDensity = 120;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;
        try {
            bitmap = z ? BitmapFactory.decodeStream(open, null, options) : BitmapFactory.decodeFile(p, options);
        } catch (Throwable unused) {
        }
        if (open != null) {
            try {
                open.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return bitmap;
    }

    public static boolean d(Context context, MediaPlayer mediaPlayer, String str) {
        try {
            if (p.v(str.toLowerCase())) {
                mediaPlayer.setDataSource(str);
            } else if (str.startsWith("@")) {
                AssetFileDescriptor openFd = context.getAssets().openFd(o(context, str));
                mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            } else {
                File file = new File(o(context, str));
                if (!file.exists()) {
                    return false;
                }
                mediaPlayer.setDataSource(file.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        }
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new a());
        return true;
    }

    public static String e(Context context, String str) {
        return com.iapp.app.b.h(context, str.toLowerCase());
    }

    public static boolean f(Context context, String str, String str2, boolean z) {
        boolean startsWith = str.startsWith("@");
        String o = o(context, str);
        return startsWith ? d.e(context, o, o(context, str2), z) : d.a(o, o(context, str2), Boolean.valueOf(z));
    }

    public static boolean g(Context context, String str) {
        if (!str.startsWith("@")) {
            return new File(o(context, str)).exists();
        }
        try {
            context.getAssets().open(o(context, str)).close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String[] h(Context context, String str) {
        if (str.startsWith("@")) {
            try {
                return context.getAssets().list(o(context, str));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        File file = new File(o(context, str));
        if (file.exists()) {
            return file.list();
        }
        return null;
    }

    public static String i(Context context, String str) {
        return str.startsWith("@") ? d.f(context, o(context, str)) : d.h(o(context, str));
    }

    public static String j(Context context, String str, String str2) {
        return str.startsWith("@") ? d.g(context, o(context, str), str2) : d.i(o(context, str), str2);
    }

    public static long k(Context context, String str) {
        if (!str.startsWith("@")) {
            File file = new File(o(context, str));
            if (file.exists()) {
                return file.length();
            }
            return -1L;
        }
        int i2 = -1;
        try {
            InputStream open = context.getAssets().open(o(context, str));
            i2 = open.available();
            open.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i2;
    }

    public static int l(Context context, String str, String str2, String str3, boolean z) {
        return str.startsWith("@") ? c.e(context.getAssets().open(o(context, str)), str2, str3, z) : c.f(o(context, str), str2, str3, z);
    }

    public static void m(Context context, String str, String str2, boolean z) {
        if (str.startsWith("@")) {
            c.g(context.getAssets().open(o(context, str)), str2, z);
        } else {
            c.h(o(context, str), str2, z);
        }
    }

    public static Object n(String str, Object[] objArr) {
        return b.h(null, z.class, str, objArr);
    }

    public static String o(Context context, String str) {
        return d.p(context, str);
    }

    public static View p(Context context, String str) {
        AssetManager assets = context.getResources().getAssets();
        try {
            return LayoutInflater.from(context).inflate(assets.openXmlResourceParser("assets/res/" + str), (ViewGroup) null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void q(Object obj, Context context, String str, Object obj2, int i2, Object obj3, Object obj4, Object obj5, Object obj6) {
        com.iapp.app.b.h4(context, str.toLowerCase(), new Object[]{obj, obj2, Integer.valueOf(i2), obj3, obj4, obj5, obj6});
    }

    public static DexClassLoader r(Context context, String str, ClassLoader classLoader) {
        String str2 = com.iapp.app.a.a(context) + "_RunDex";
        String str3 = com.iapp.app.a.a(context) + "_RunDex_";
        d.b(str2, true);
        d.b(str3, true);
        byte[] w = d.w(context.getClassLoader().getResourceAsStream("lib/" + str));
        File file = new File(str3 + "/" + str);
        if (file.exists() && !p.e(w).equals(p.e(d.v(file)))) {
            file.delete();
        }
        if (!file.exists()) {
            d.j(str3 + "/" + str, w);
        }
        return new DexClassLoader(str3 + "/" + str, str2, null, classLoader);
    }

    public static void s(String str) {
        System.loadLibrary(str);
    }

    public static byte[] t(Context context, String str) {
        return str.startsWith("@") ? d.u(context, o(context, str)) : d.x(o(context, str));
    }

    public static void u(Context context, DexClassLoader dexClassLoader) {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Class<?> cls2 = Class.forName("android.app.LoadedApk");
            Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mPackages");
            declaredField.setAccessible(true);
            WeakReference weakReference = (WeakReference) ((Map) declaredField.get(invoke)).get(context.getPackageName());
            Field declaredField2 = cls2.getDeclaredField("mClassLoader");
            declaredField2.setAccessible(true);
            declaredField2.set(weakReference.get(), dexClassLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap v(Context context, String str) {
        return str.startsWith("@") ? i.b(context, o(context, str)) : i.c(o(context, str));
    }

    public static void w(View view, Object obj, HashMap<Integer, Object> hashMap, h hVar) {
        Boolean bool = Boolean.TRUE;
        if (view == null) {
            return;
        }
        Object[] objArr = (Object[]) view.getTag();
        objArr[3] = hashMap;
        view.setTag(objArr);
        if (obj == null) {
            if (view instanceof RadioButton) {
                ((RadioButton) view).setText("");
                return;
            }
            if (view instanceof CheckBox) {
                ((CheckBox) view).setText("");
                return;
            } else if (view instanceof TextView) {
                ((TextView) view).setText("");
                return;
            } else {
                if (view instanceof ImageView) {
                    ((ImageView) view).setImageDrawable(null);
                    return;
                }
                return;
            }
        }
        if (view instanceof RadioButton) {
            if (obj instanceof Boolean) {
                ((RadioButton) view).setChecked(obj.equals(bool));
                return;
            }
            String valueOf = String.valueOf(obj);
            boolean startsWith = valueOf.startsWith("(html)");
            RadioButton radioButton = (RadioButton) view;
            CharSequence charSequence = valueOf;
            if (startsWith) {
                charSequence = Html.fromHtml(valueOf.substring(6));
            }
            radioButton.setText(charSequence);
            return;
        }
        if (view instanceof CheckBox) {
            if (obj instanceof Boolean) {
                ((CheckBox) view).setChecked(obj.equals(bool));
                return;
            }
            String valueOf2 = String.valueOf(obj);
            boolean startsWith2 = valueOf2.startsWith("(html)");
            CheckBox checkBox = (CheckBox) view;
            CharSequence charSequence2 = valueOf2;
            if (startsWith2) {
                charSequence2 = Html.fromHtml(valueOf2.substring(6));
            }
            checkBox.setText(charSequence2);
            return;
        }
        if (view instanceof TextView) {
            String valueOf3 = String.valueOf(obj);
            boolean startsWith3 = valueOf3.startsWith("(html)");
            TextView textView = (TextView) view;
            CharSequence charSequence3 = valueOf3;
            if (startsWith3) {
                charSequence3 = Html.fromHtml(valueOf3.substring(6));
            }
            textView.setText(charSequence3);
            return;
        }
        if (view instanceof ImageView) {
            if (obj instanceof Bitmap) {
                ((ImageView) view).setImageBitmap((Bitmap) obj);
                return;
            }
            if (obj instanceof Drawable) {
                ((ImageView) view).setImageDrawable((Drawable) obj);
                return;
            }
            if (hVar == null) {
                ((ImageView) view).setImageBitmap(b(view.getContext(), String.valueOf(obj)));
                return;
            }
            ImageView imageView = (ImageView) view;
            String obj2 = obj.toString();
            String lowerCase = obj2.toLowerCase();
            SoftReference<Drawable> softReference = hVar.b.get(lowerCase);
            Drawable drawable = softReference != null ? softReference.get() : null;
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
            } else if (lowerCase.startsWith("http:") || lowerCase.startsWith("https:") || lowerCase.startsWith("ftp:")) {
                hVar.s(imageView, obj2, lowerCase, true);
            } else {
                hVar.p(imageView, obj2, lowerCase, true);
            }
        }
    }

    public static boolean x(Context context, String str, boolean z) {
        return n.c(context, str, z);
    }

    public static boolean y(Context context, String str, boolean z) {
        return n.f(context, str, z);
    }

    public static SQLiteDatabase z(Context context, String str, boolean z) {
        return n.g(context, str, z);
    }
}
