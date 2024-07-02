package androidx.core.app;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class NotificationManagerCompat {
    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;

    /* renamed from: d, reason: collision with root package name */
    @GuardedBy("sEnabledNotificationListenersLock")
    private static String f497d;

    @GuardedBy("sLock")
    private static d g;
    private final Context a;
    private final NotificationManager b;

    /* renamed from: c, reason: collision with root package name */
    private static final Object f496c = new Object();

    @GuardedBy("sEnabledNotificationListenersLock")
    private static Set<String> e = new HashSet();
    private static final Object f = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class a implements e {
        final String a;
        final int b;

        /* renamed from: c, reason: collision with root package name */
        final String f498c;

        /* renamed from: d, reason: collision with root package name */
        final boolean f499d;

        a(String str) {
            this.a = str;
            this.b = 0;
            this.f498c = null;
            this.f499d = true;
        }

        a(String str, int i2, String str2) {
            this.a = str;
            this.b = i2;
            this.f498c = str2;
            this.f499d = false;
        }

        @Override // androidx.core.app.NotificationManagerCompat.e
        public void a(INotificationSideChannel iNotificationSideChannel) {
            if (this.f499d) {
                iNotificationSideChannel.cancelAll(this.a);
            } else {
                iNotificationSideChannel.cancel(this.a, this.b, this.f498c);
            }
        }

        @NonNull
        public String toString() {
            return "CancelTask[packageName:" + this.a + ", id:" + this.b + ", tag:" + this.f498c + ", all:" + this.f499d + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b implements e {
        final String a;
        final int b;

        /* renamed from: c, reason: collision with root package name */
        final String f500c;

        /* renamed from: d, reason: collision with root package name */
        final Notification f501d;

        b(String str, int i2, String str2, Notification notification) {
            this.a = str;
            this.b = i2;
            this.f500c = str2;
            this.f501d = notification;
        }

        @Override // androidx.core.app.NotificationManagerCompat.e
        public void a(INotificationSideChannel iNotificationSideChannel) {
            iNotificationSideChannel.notify(this.a, this.b, this.f500c, this.f501d);
        }

        @NonNull
        public String toString() {
            return "NotifyTask[packageName:" + this.a + ", id:" + this.b + ", tag:" + this.f500c + "]";
        }
    }

    /* loaded from: classes.dex */
    private static class c {
        final ComponentName a;
        final IBinder b;

        c(ComponentName componentName, IBinder iBinder) {
            this.a = componentName;
            this.b = iBinder;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class d implements Handler.Callback, ServiceConnection {
        private final Context a;
        private final HandlerThread b;

        /* renamed from: c, reason: collision with root package name */
        private final Handler f502c;

        /* renamed from: d, reason: collision with root package name */
        private final Map<ComponentName, a> f503d = new HashMap();
        private Set<String> e = new HashSet();

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class a {
            final ComponentName a;

            /* renamed from: c, reason: collision with root package name */
            INotificationSideChannel f504c;
            boolean b = false;

            /* renamed from: d, reason: collision with root package name */
            ArrayDeque<e> f505d = new ArrayDeque<>();
            int e = 0;

            a(ComponentName componentName) {
                this.a = componentName;
            }
        }

        d(Context context) {
            this.a = context;
            HandlerThread handlerThread = new HandlerThread("NotificationManagerCompat");
            this.b = handlerThread;
            handlerThread.start();
            this.f502c = new Handler(handlerThread.getLooper(), this);
        }

        private boolean a(a aVar) {
            if (aVar.b) {
                return true;
            }
            boolean bindService = this.a.bindService(new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(aVar.a), this, 33);
            aVar.b = bindService;
            if (bindService) {
                aVar.e = 0;
            } else {
                Log.w("NotifManCompat", "Unable to bind to listener " + aVar.a);
                this.a.unbindService(this);
            }
            return aVar.b;
        }

        private void b(a aVar) {
            if (aVar.b) {
                this.a.unbindService(this);
                aVar.b = false;
            }
            aVar.f504c = null;
        }

        private void c(e eVar) {
            j();
            for (a aVar : this.f503d.values()) {
                aVar.f505d.add(eVar);
                g(aVar);
            }
        }

        private void d(ComponentName componentName) {
            a aVar = this.f503d.get(componentName);
            if (aVar != null) {
                g(aVar);
            }
        }

        private void e(ComponentName componentName, IBinder iBinder) {
            a aVar = this.f503d.get(componentName);
            if (aVar != null) {
                aVar.f504c = INotificationSideChannel.Stub.asInterface(iBinder);
                aVar.e = 0;
                g(aVar);
            }
        }

        private void f(ComponentName componentName) {
            a aVar = this.f503d.get(componentName);
            if (aVar != null) {
                b(aVar);
            }
        }

        private void g(a aVar) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Processing component " + aVar.a + ", " + aVar.f505d.size() + " queued tasks");
            }
            if (aVar.f505d.isEmpty()) {
                return;
            }
            if (!a(aVar) || aVar.f504c == null) {
                i(aVar);
                return;
            }
            while (true) {
                e peek = aVar.f505d.peek();
                if (peek == null) {
                    break;
                }
                try {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Sending task " + peek);
                    }
                    peek.a(aVar.f504c);
                    aVar.f505d.remove();
                } catch (DeadObjectException unused) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Remote service has died: " + aVar.a);
                    }
                } catch (RemoteException e) {
                    Log.w("NotifManCompat", "RemoteException communicating with " + aVar.a, e);
                }
            }
            if (aVar.f505d.isEmpty()) {
                return;
            }
            i(aVar);
        }

        private void i(a aVar) {
            if (this.f502c.hasMessages(3, aVar.a)) {
                return;
            }
            int i2 = aVar.e + 1;
            aVar.e = i2;
            if (i2 <= 6) {
                int i3 = (1 << (i2 - 1)) * 1000;
                if (Log.isLoggable("NotifManCompat", 3)) {
                    Log.d("NotifManCompat", "Scheduling retry for " + i3 + " ms");
                }
                this.f502c.sendMessageDelayed(this.f502c.obtainMessage(3, aVar.a), i3);
                return;
            }
            Log.w("NotifManCompat", "Giving up on delivering " + aVar.f505d.size() + " tasks to " + aVar.a + " after " + aVar.e + " retries");
            aVar.f505d.clear();
        }

        private void j() {
            Set<String> enabledListenerPackages = NotificationManagerCompat.getEnabledListenerPackages(this.a);
            if (enabledListenerPackages.equals(this.e)) {
                return;
            }
            this.e = enabledListenerPackages;
            List<ResolveInfo> queryIntentServices = this.a.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
            HashSet<ComponentName> hashSet = new HashSet();
            for (ResolveInfo resolveInfo : queryIntentServices) {
                if (enabledListenerPackages.contains(resolveInfo.serviceInfo.packageName)) {
                    ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                    ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
                    if (resolveInfo.serviceInfo.permission != null) {
                        Log.w("NotifManCompat", "Permission present on component " + componentName + ", not adding listener record.");
                    } else {
                        hashSet.add(componentName);
                    }
                }
            }
            for (ComponentName componentName2 : hashSet) {
                if (!this.f503d.containsKey(componentName2)) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Adding listener record for " + componentName2);
                    }
                    this.f503d.put(componentName2, new a(componentName2));
                }
            }
            Iterator<Map.Entry<ComponentName, a>> it = this.f503d.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<ComponentName, a> next = it.next();
                if (!hashSet.contains(next.getKey())) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Removing listener record for " + next.getKey());
                    }
                    b(next.getValue());
                    it.remove();
                }
            }
        }

        public void h(e eVar) {
            this.f502c.obtainMessage(0, eVar).sendToTarget();
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 0) {
                c((e) message.obj);
                return true;
            }
            if (i2 == 1) {
                c cVar = (c) message.obj;
                e(cVar.a, cVar.b);
                return true;
            }
            if (i2 == 2) {
                f((ComponentName) message.obj);
                return true;
            }
            if (i2 != 3) {
                return false;
            }
            d((ComponentName) message.obj);
            return true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Connected to service " + componentName);
            }
            this.f502c.obtainMessage(1, new c(componentName, iBinder)).sendToTarget();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Disconnected from service " + componentName);
            }
            this.f502c.obtainMessage(2, componentName).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface e {
        void a(INotificationSideChannel iNotificationSideChannel);
    }

    private NotificationManagerCompat(Context context) {
        this.a = context;
        this.b = (NotificationManager) context.getSystemService("notification");
    }

    private void a(e eVar) {
        synchronized (f) {
            if (g == null) {
                g = new d(this.a.getApplicationContext());
            }
            g.h(eVar);
        }
    }

    private static boolean b(Notification notification) {
        Bundle extras = NotificationCompat.getExtras(notification);
        return extras != null && extras.getBoolean("android.support.useSideChannel");
    }

    @NonNull
    public static NotificationManagerCompat from(@NonNull Context context) {
        return new NotificationManagerCompat(context);
    }

    @NonNull
    public static Set<String> getEnabledListenerPackages(@NonNull Context context) {
        Set<String> set;
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        synchronized (f496c) {
            if (string != null) {
                if (!string.equals(f497d)) {
                    String[] split = string.split(":", -1);
                    HashSet hashSet = new HashSet(split.length);
                    for (String str : split) {
                        ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
                        if (unflattenFromString != null) {
                            hashSet.add(unflattenFromString.getPackageName());
                        }
                    }
                    e = hashSet;
                    f497d = string;
                }
            }
            set = e;
        }
        return set;
    }

    public boolean areNotificationsEnabled() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 24) {
            return this.b.areNotificationsEnabled();
        }
        if (i2 < 19) {
            return true;
        }
        AppOpsManager appOpsManager = (AppOpsManager) this.a.getSystemService("appops");
        ApplicationInfo applicationInfo = this.a.getApplicationInfo();
        String packageName = this.a.getApplicationContext().getPackageName();
        int i3 = applicationInfo.uid;
        try {
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            Class<?> cls2 = Integer.TYPE;
            return ((Integer) cls.getMethod("checkOpNoThrow", cls2, cls2, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i3), packageName)).intValue() == 0;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            return true;
        }
    }

    public void cancel(int i2) {
        cancel(null, i2);
    }

    public void cancel(@Nullable String str, int i2) {
        this.b.cancel(str, i2);
        if (Build.VERSION.SDK_INT <= 19) {
            a(new a(this.a.getPackageName(), i2, str));
        }
    }

    public void cancelAll() {
        this.b.cancelAll();
        if (Build.VERSION.SDK_INT <= 19) {
            a(new a(this.a.getPackageName()));
        }
    }

    public void createNotificationChannel(@NonNull NotificationChannel notificationChannel) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.b.createNotificationChannel(notificationChannel);
        }
    }

    public void createNotificationChannel(@NonNull NotificationChannelCompat notificationChannelCompat) {
        createNotificationChannel(notificationChannelCompat.a());
    }

    public void createNotificationChannelGroup(@NonNull NotificationChannelGroup notificationChannelGroup) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.b.createNotificationChannelGroup(notificationChannelGroup);
        }
    }

    public void createNotificationChannelGroup(@NonNull NotificationChannelGroupCompat notificationChannelGroupCompat) {
        createNotificationChannelGroup(notificationChannelGroupCompat.b());
    }

    public void createNotificationChannelGroups(@NonNull List<NotificationChannelGroup> list) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.b.createNotificationChannelGroups(list);
        }
    }

    public void createNotificationChannelGroupsCompat(@NonNull List<NotificationChannelGroupCompat> list) {
        if (Build.VERSION.SDK_INT < 26 || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<NotificationChannelGroupCompat> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().b());
        }
        this.b.createNotificationChannelGroups(arrayList);
    }

    public void createNotificationChannels(@NonNull List<NotificationChannel> list) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.b.createNotificationChannels(list);
        }
    }

    public void createNotificationChannelsCompat(@NonNull List<NotificationChannelCompat> list) {
        if (Build.VERSION.SDK_INT < 26 || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<NotificationChannelCompat> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a());
        }
        this.b.createNotificationChannels(arrayList);
    }

    public void deleteNotificationChannel(@NonNull String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.b.deleteNotificationChannel(str);
        }
    }

    public void deleteNotificationChannelGroup(@NonNull String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.b.deleteNotificationChannelGroup(str);
        }
    }

    public void deleteUnlistedNotificationChannels(@NonNull Collection<String> collection) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            for (NotificationChannel notificationChannel : this.b.getNotificationChannels()) {
                if (!collection.contains(notificationChannel.getId()) && (i2 < 30 || !collection.contains(notificationChannel.getParentChannelId()))) {
                    this.b.deleteNotificationChannel(notificationChannel.getId());
                }
            }
        }
    }

    public int getImportance() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.b.getImportance();
        }
        return -1000;
    }

    @Nullable
    public NotificationChannel getNotificationChannel(@NonNull String str) {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.b.getNotificationChannel(str);
        }
        return null;
    }

    @Nullable
    public NotificationChannel getNotificationChannel(@NonNull String str, @NonNull String str2) {
        return Build.VERSION.SDK_INT >= 30 ? this.b.getNotificationChannel(str, str2) : getNotificationChannel(str);
    }

    @Nullable
    public NotificationChannelCompat getNotificationChannelCompat(@NonNull String str) {
        NotificationChannel notificationChannel;
        if (Build.VERSION.SDK_INT < 26 || (notificationChannel = getNotificationChannel(str)) == null) {
            return null;
        }
        return new NotificationChannelCompat(notificationChannel);
    }

    @Nullable
    public NotificationChannelCompat getNotificationChannelCompat(@NonNull String str, @NonNull String str2) {
        NotificationChannel notificationChannel;
        if (Build.VERSION.SDK_INT < 26 || (notificationChannel = getNotificationChannel(str, str2)) == null) {
            return null;
        }
        return new NotificationChannelCompat(notificationChannel);
    }

    @Nullable
    public NotificationChannelGroup getNotificationChannelGroup(@NonNull String str) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            return this.b.getNotificationChannelGroup(str);
        }
        if (i2 >= 26) {
            for (NotificationChannelGroup notificationChannelGroup : getNotificationChannelGroups()) {
                if (notificationChannelGroup.getId().equals(str)) {
                    return notificationChannelGroup;
                }
            }
        }
        return null;
    }

    @Nullable
    public NotificationChannelGroupCompat getNotificationChannelGroupCompat(@NonNull String str) {
        NotificationChannelGroup notificationChannelGroup;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            NotificationChannelGroup notificationChannelGroup2 = getNotificationChannelGroup(str);
            if (notificationChannelGroup2 != null) {
                return new NotificationChannelGroupCompat(notificationChannelGroup2);
            }
            return null;
        }
        if (i2 < 26 || (notificationChannelGroup = getNotificationChannelGroup(str)) == null) {
            return null;
        }
        return new NotificationChannelGroupCompat(notificationChannelGroup, getNotificationChannels());
    }

    @NonNull
    public List<NotificationChannelGroup> getNotificationChannelGroups() {
        return Build.VERSION.SDK_INT >= 26 ? this.b.getNotificationChannelGroups() : Collections.emptyList();
    }

    @NonNull
    public List<NotificationChannelGroupCompat> getNotificationChannelGroupsCompat() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            List<NotificationChannelGroup> notificationChannelGroups = getNotificationChannelGroups();
            if (!notificationChannelGroups.isEmpty()) {
                List<NotificationChannel> emptyList = i2 >= 28 ? Collections.emptyList() : getNotificationChannels();
                ArrayList arrayList = new ArrayList(notificationChannelGroups.size());
                for (NotificationChannelGroup notificationChannelGroup : notificationChannelGroups) {
                    arrayList.add(i2 >= 28 ? new NotificationChannelGroupCompat(notificationChannelGroup) : new NotificationChannelGroupCompat(notificationChannelGroup, emptyList));
                }
                return arrayList;
            }
        }
        return Collections.emptyList();
    }

    @NonNull
    public List<NotificationChannel> getNotificationChannels() {
        return Build.VERSION.SDK_INT >= 26 ? this.b.getNotificationChannels() : Collections.emptyList();
    }

    @NonNull
    public List<NotificationChannelCompat> getNotificationChannelsCompat() {
        if (Build.VERSION.SDK_INT >= 26) {
            List<NotificationChannel> notificationChannels = getNotificationChannels();
            if (!notificationChannels.isEmpty()) {
                ArrayList arrayList = new ArrayList(notificationChannels.size());
                Iterator<NotificationChannel> it = notificationChannels.iterator();
                while (it.hasNext()) {
                    arrayList.add(new NotificationChannelCompat(it.next()));
                }
                return arrayList;
            }
        }
        return Collections.emptyList();
    }

    public void notify(int i2, @NonNull Notification notification) {
        notify(null, i2, notification);
    }

    public void notify(@Nullable String str, int i2, @NonNull Notification notification) {
        if (!b(notification)) {
            this.b.notify(str, i2, notification);
        } else {
            a(new b(this.a.getPackageName(), i2, str, notification));
            this.b.cancel(str, i2);
        }
    }
}
