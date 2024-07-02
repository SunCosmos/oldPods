package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompatJellybean;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiresApi(16)
/* loaded from: classes.dex */
public class c {
    private static Field b;

    /* renamed from: c */
    private static boolean f525c;
    private static Field e;
    private static Field f;
    private static Field g;
    private static Field h;

    /* renamed from: i */
    private static boolean f527i;
    private static final Object a = new Object();

    /* renamed from: d */
    private static final Object f526d = new Object();

    public static SparseArray<Bundle> a(List<Bundle> list) {
        int size = list.size();
        SparseArray<Bundle> sparseArray = null;
        for (int i2 = 0; i2 < size; i2++) {
            Bundle bundle = list.get(i2);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                sparseArray.put(i2, bundle);
            }
        }
        return sparseArray;
    }

    private static boolean b() {
        if (f527i) {
            return false;
        }
        try {
            if (e == null) {
                Class<?> cls = Class.forName("android.app.Notification$Action");
                f = cls.getDeclaredField("icon");
                g = cls.getDeclaredField("title");
                h = cls.getDeclaredField("actionIntent");
                Field declaredField = Notification.class.getDeclaredField("actions");
                e = declaredField;
                declaredField.setAccessible(true);
            }
        } catch (ClassNotFoundException | NoSuchFieldException e2) {
            Log.e(NotificationCompatJellybean.TAG, "Unable to access notification actions", e2);
            f527i = true;
        }
        return !f527i;
    }

    private static RemoteInput c(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("allowedDataTypes");
        HashSet hashSet = new HashSet();
        if (stringArrayList != null) {
            Iterator<String> it = stringArrayList.iterator();
            while (it.hasNext()) {
                hashSet.add(it.next());
            }
        }
        return new RemoteInput(bundle.getString("resultKey"), bundle.getCharSequence("label"), bundle.getCharSequenceArray("choices"), bundle.getBoolean("allowFreeFormInput"), 0, bundle.getBundle("extras"), hashSet);
    }

    private static RemoteInput[] d(Bundle[] bundleArr) {
        if (bundleArr == null) {
            return null;
        }
        RemoteInput[] remoteInputArr = new RemoteInput[bundleArr.length];
        for (int i2 = 0; i2 < bundleArr.length; i2++) {
            remoteInputArr[i2] = c(bundleArr[i2]);
        }
        return remoteInputArr;
    }

    public static NotificationCompat.Action e(Notification notification, int i2) {
        SparseArray sparseParcelableArray;
        synchronized (f526d) {
            try {
                try {
                    Object[] h2 = h(notification);
                    if (h2 != null) {
                        Object obj = h2[i2];
                        Bundle k = k(notification);
                        return l(f.getInt(obj), (CharSequence) g.get(obj), (PendingIntent) h.get(obj), (k == null || (sparseParcelableArray = k.getSparseParcelableArray("android.support.actionExtras")) == null) ? null : (Bundle) sparseParcelableArray.get(i2));
                    }
                } catch (IllegalAccessException e2) {
                    Log.e(NotificationCompatJellybean.TAG, "Unable to access notification actions", e2);
                    f527i = true;
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static int f(Notification notification) {
        int length;
        synchronized (f526d) {
            Object[] h2 = h(notification);
            length = h2 != null ? h2.length : 0;
        }
        return length;
    }

    public static NotificationCompat.Action g(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("extras");
        return new NotificationCompat.Action(bundle.getInt("icon"), bundle.getCharSequence("title"), (PendingIntent) bundle.getParcelable("actionIntent"), bundle.getBundle("extras"), d(i(bundle, "remoteInputs")), d(i(bundle, "dataOnlyRemoteInputs")), bundle2 != null ? bundle2.getBoolean("android.support.allowGeneratedReplies", false) : false, bundle.getInt("semanticAction"), bundle.getBoolean("showsUserInterface"), false);
    }

    private static Object[] h(Notification notification) {
        synchronized (f526d) {
            if (!b()) {
                return null;
            }
            try {
                return (Object[]) e.get(notification);
            } catch (IllegalAccessException e2) {
                Log.e(NotificationCompatJellybean.TAG, "Unable to access notification actions", e2);
                f527i = true;
                return null;
            }
        }
    }

    private static Bundle[] i(Bundle bundle, String str) {
        Parcelable[] parcelableArray = bundle.getParcelableArray(str);
        if ((parcelableArray instanceof Bundle[]) || parcelableArray == null) {
            return (Bundle[]) parcelableArray;
        }
        Bundle[] bundleArr = (Bundle[]) Arrays.copyOf(parcelableArray, parcelableArray.length, Bundle[].class);
        bundle.putParcelableArray(str, bundleArr);
        return bundleArr;
    }

    public static Bundle j(NotificationCompat.Action action) {
        Bundle bundle = new Bundle();
        IconCompat iconCompat = action.getIconCompat();
        bundle.putInt("icon", iconCompat != null ? iconCompat.getResId() : 0);
        bundle.putCharSequence("title", action.getTitle());
        bundle.putParcelable("actionIntent", action.getActionIntent());
        Bundle bundle2 = action.getExtras() != null ? new Bundle(action.getExtras()) : new Bundle();
        bundle2.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        bundle.putBundle("extras", bundle2);
        bundle.putParcelableArray("remoteInputs", n(action.getRemoteInputs()));
        bundle.putBoolean("showsUserInterface", action.getShowsUserInterface());
        bundle.putInt("semanticAction", action.getSemanticAction());
        return bundle;
    }

    public static Bundle k(Notification notification) {
        String str;
        String str2;
        synchronized (a) {
            if (f525c) {
                return null;
            }
            try {
                if (b == null) {
                    Field declaredField = Notification.class.getDeclaredField("extras");
                    if (!Bundle.class.isAssignableFrom(declaredField.getType())) {
                        Log.e(NotificationCompatJellybean.TAG, "Notification.extras field is not of type Bundle");
                        f525c = true;
                        return null;
                    }
                    declaredField.setAccessible(true);
                    b = declaredField;
                }
                Bundle bundle = (Bundle) b.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    b.set(notification, bundle);
                }
                return bundle;
            } catch (IllegalAccessException e2) {
                e = e2;
                str = NotificationCompatJellybean.TAG;
                str2 = "Unable to access notification extras";
                Log.e(str, str2, e);
                f525c = true;
                return null;
            } catch (NoSuchFieldException e3) {
                e = e3;
                str = NotificationCompatJellybean.TAG;
                str2 = "Unable to access notification extras";
                Log.e(str, str2, e);
                f525c = true;
                return null;
            }
        }
    }

    public static NotificationCompat.Action l(int i2, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle) {
        RemoteInput[] remoteInputArr;
        RemoteInput[] remoteInputArr2;
        boolean z;
        if (bundle != null) {
            remoteInputArr = d(i(bundle, "android.support.remoteInputs"));
            remoteInputArr2 = d(i(bundle, "android.support.dataRemoteInputs"));
            z = bundle.getBoolean("android.support.allowGeneratedReplies");
        } else {
            remoteInputArr = null;
            remoteInputArr2 = null;
            z = false;
        }
        return new NotificationCompat.Action(i2, charSequence, pendingIntent, bundle, remoteInputArr, remoteInputArr2, z, 0, true, false);
    }

    private static Bundle m(RemoteInput remoteInput) {
        Bundle bundle = new Bundle();
        bundle.putString("resultKey", remoteInput.getResultKey());
        bundle.putCharSequence("label", remoteInput.getLabel());
        bundle.putCharSequenceArray("choices", remoteInput.getChoices());
        bundle.putBoolean("allowFreeFormInput", remoteInput.getAllowFreeFormInput());
        bundle.putBundle("extras", remoteInput.getExtras());
        Set<String> allowedDataTypes = remoteInput.getAllowedDataTypes();
        if (allowedDataTypes != null && !allowedDataTypes.isEmpty()) {
            ArrayList<String> arrayList = new ArrayList<>(allowedDataTypes.size());
            Iterator<String> it = allowedDataTypes.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            bundle.putStringArrayList("allowedDataTypes", arrayList);
        }
        return bundle;
    }

    private static Bundle[] n(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[remoteInputArr.length];
        for (int i2 = 0; i2 < remoteInputArr.length; i2++) {
            bundleArr[i2] = m(remoteInputArr[i2]);
        }
        return bundleArr;
    }

    public static Bundle o(Notification.Builder builder, NotificationCompat.Action action) {
        IconCompat iconCompat = action.getIconCompat();
        builder.addAction(iconCompat != null ? iconCompat.getResId() : 0, action.getTitle(), action.getActionIntent());
        Bundle bundle = new Bundle(action.getExtras());
        if (action.getRemoteInputs() != null) {
            bundle.putParcelableArray("android.support.remoteInputs", n(action.getRemoteInputs()));
        }
        if (action.getDataOnlyRemoteInputs() != null) {
            bundle.putParcelableArray("android.support.dataRemoteInputs", n(action.getDataOnlyRemoteInputs()));
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        return bundle;
    }
}
