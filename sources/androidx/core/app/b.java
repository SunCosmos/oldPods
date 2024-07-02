package androidx.core.app;

import android.app.Notification;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationCompat;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public class b implements NotificationBuilderWithBuilderAccessor {
    private final Context a;
    private final Notification.Builder b;

    /* renamed from: c */
    private final NotificationCompat.Builder f522c;

    /* renamed from: d */
    private RemoteViews f523d;
    private RemoteViews e;
    private final List<Bundle> f;
    private final Bundle g;
    private int h;

    /* renamed from: i */
    private RemoteViews f524i;

    public b(NotificationCompat.Builder builder) {
        Icon icon;
        List<String> d2;
        Bundle bundle;
        String str;
        int i2 = Build.VERSION.SDK_INT;
        this.f = new ArrayList();
        this.g = new Bundle();
        this.f522c = builder;
        this.a = builder.mContext;
        Context context = builder.mContext;
        this.b = i2 >= 26 ? new Notification.Builder(context, builder.I) : new Notification.Builder(context);
        Notification notification = builder.Q;
        this.b.setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, builder.f).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 2) != 0).setOnlyAlertOnce((notification.flags & 8) != 0).setAutoCancel((notification.flags & 16) != 0).setDefaults(notification.defaults).setContentTitle(builder.b).setContentText(builder.f480c).setContentInfo(builder.h).setContentIntent(builder.f481d).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(builder.e, (notification.flags & 128) != 0).setLargeIcon(builder.g).setNumber(builder.f482i).setProgress(builder.r, builder.s, builder.t);
        if (i2 < 21) {
            this.b.setSound(notification.sound, notification.audioStreamType);
        }
        if (i2 >= 16) {
            this.b.setSubText(builder.o).setUsesChronometer(builder.l).setPriority(builder.j);
            Iterator<NotificationCompat.Action> it = builder.mActions.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            Bundle bundle2 = builder.B;
            if (bundle2 != null) {
                this.g.putAll(bundle2);
            }
            if (i2 < 20) {
                if (builder.x) {
                    this.g.putBoolean("android.support.localOnly", true);
                }
                String str2 = builder.u;
                if (str2 != null) {
                    this.g.putString("android.support.groupKey", str2);
                    if (builder.v) {
                        bundle = this.g;
                        str = "android.support.isGroupSummary";
                    } else {
                        bundle = this.g;
                        str = "android.support.useSideChannel";
                    }
                    bundle.putBoolean(str, true);
                }
                String str3 = builder.w;
                if (str3 != null) {
                    this.g.putString("android.support.sortKey", str3);
                }
            }
            this.f523d = builder.F;
            this.e = builder.G;
        }
        if (i2 >= 17) {
            this.b.setShowWhen(builder.k);
        }
        if (i2 >= 19 && i2 < 21 && (d2 = d(f(builder.mPersonList), builder.mPeople)) != null && !d2.isEmpty()) {
            this.g.putStringArray("android.people", (String[]) d2.toArray(new String[d2.size()]));
        }
        if (i2 >= 20) {
            this.b.setLocalOnly(builder.x).setGroup(builder.u).setGroupSummary(builder.v).setSortKey(builder.w);
            this.h = builder.N;
        }
        if (i2 >= 21) {
            this.b.setCategory(builder.A).setColor(builder.C).setVisibility(builder.D).setPublicVersion(builder.E).setSound(notification.sound, notification.audioAttributes);
            List d3 = i2 < 28 ? d(f(builder.mPersonList), builder.mPeople) : builder.mPeople;
            if (d3 != null && !d3.isEmpty()) {
                Iterator it2 = d3.iterator();
                while (it2.hasNext()) {
                    this.b.addPerson((String) it2.next());
                }
            }
            this.f524i = builder.H;
            if (builder.a.size() > 0) {
                Bundle bundle3 = builder.getExtras().getBundle("android.car.EXTENSIONS");
                bundle3 = bundle3 == null ? new Bundle() : bundle3;
                Bundle bundle4 = new Bundle(bundle3);
                Bundle bundle5 = new Bundle();
                for (int i3 = 0; i3 < builder.a.size(); i3++) {
                    bundle5.putBundle(Integer.toString(i3), c.j(builder.a.get(i3)));
                }
                bundle3.putBundle("invisible_actions", bundle5);
                bundle4.putBundle("invisible_actions", bundle5);
                builder.getExtras().putBundle("android.car.EXTENSIONS", bundle3);
                this.g.putBundle("android.car.EXTENSIONS", bundle4);
            }
        }
        if (i2 >= 23 && (icon = builder.S) != null) {
            this.b.setSmallIcon(icon);
        }
        if (i2 >= 24) {
            this.b.setExtras(builder.B).setRemoteInputHistory(builder.q);
            RemoteViews remoteViews = builder.F;
            if (remoteViews != null) {
                this.b.setCustomContentView(remoteViews);
            }
            RemoteViews remoteViews2 = builder.G;
            if (remoteViews2 != null) {
                this.b.setCustomBigContentView(remoteViews2);
            }
            RemoteViews remoteViews3 = builder.H;
            if (remoteViews3 != null) {
                this.b.setCustomHeadsUpContentView(remoteViews3);
            }
        }
        if (i2 >= 26) {
            this.b.setBadgeIconType(builder.J).setSettingsText(builder.p).setShortcutId(builder.K).setTimeoutAfter(builder.M).setGroupAlertBehavior(builder.N);
            if (builder.z) {
                this.b.setColorized(builder.y);
            }
            if (!TextUtils.isEmpty(builder.I)) {
                this.b.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
            }
        }
        if (i2 >= 28) {
            Iterator<Person> it3 = builder.mPersonList.iterator();
            while (it3.hasNext()) {
                this.b.addPerson(it3.next().toAndroidPerson());
            }
        }
        if (i2 >= 29) {
            this.b.setAllowSystemGeneratedContextualActions(builder.O);
            this.b.setBubbleMetadata(NotificationCompat.BubbleMetadata.toPlatform(builder.P));
            LocusIdCompat locusIdCompat = builder.L;
            if (locusIdCompat != null) {
                this.b.setLocusId(locusIdCompat.toLocusId());
            }
        }
        if (builder.R) {
            if (this.f522c.v) {
                this.h = 2;
            } else {
                this.h = 1;
            }
            this.b.setVibrate(null);
            this.b.setSound(null);
            int i4 = notification.defaults & (-2);
            notification.defaults = i4;
            int i5 = i4 & (-3);
            notification.defaults = i5;
            this.b.setDefaults(i5);
            if (i2 >= 26) {
                if (TextUtils.isEmpty(this.f522c.u)) {
                    this.b.setGroup(NotificationCompat.GROUP_KEY_SILENT);
                }
                this.b.setGroupAlertBehavior(this.h);
            }
        }
    }

    private void a(NotificationCompat.Action action) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 20) {
            if (i2 >= 16) {
                this.f.add(c.o(this.b, action));
                return;
            }
            return;
        }
        IconCompat iconCompat = action.getIconCompat();
        Notification.Action.Builder builder = i2 >= 23 ? new Notification.Action.Builder(iconCompat != null ? iconCompat.toIcon() : null, action.getTitle(), action.getActionIntent()) : new Notification.Action.Builder(iconCompat != null ? iconCompat.getResId() : 0, action.getTitle(), action.getActionIntent());
        if (action.getRemoteInputs() != null) {
            for (android.app.RemoteInput remoteInput : RemoteInput.b(action.getRemoteInputs())) {
                builder.addRemoteInput(remoteInput);
            }
        }
        Bundle bundle = action.getExtras() != null ? new Bundle(action.getExtras()) : new Bundle();
        bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
        if (i2 >= 24) {
            builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
        }
        bundle.putInt("android.support.action.semanticAction", action.getSemanticAction());
        if (i2 >= 28) {
            builder.setSemanticAction(action.getSemanticAction());
        }
        if (i2 >= 29) {
            builder.setContextual(action.isContextual());
        }
        bundle.putBoolean("android.support.action.showsUserInterface", action.getShowsUserInterface());
        builder.addExtras(bundle);
        this.b.addAction(builder.build());
    }

    @Nullable
    private static List<String> d(@Nullable List<String> list, @Nullable List<String> list2) {
        if (list == null) {
            return list2;
        }
        if (list2 == null) {
            return list;
        }
        ArraySet arraySet = new ArraySet(list.size() + list2.size());
        arraySet.addAll(list);
        arraySet.addAll(list2);
        return new ArrayList(arraySet);
    }

    @Nullable
    private static List<String> f(@Nullable List<Person> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<Person> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().resolveToLegacyUri());
        }
        return arrayList;
    }

    private void g(Notification notification) {
        notification.sound = null;
        notification.vibrate = null;
        int i2 = notification.defaults & (-2);
        notification.defaults = i2;
        notification.defaults = i2 & (-3);
    }

    public Notification b() {
        Bundle extras;
        RemoteViews makeHeadsUpContentView;
        RemoteViews makeBigContentView;
        NotificationCompat.Style style = this.f522c.n;
        if (style != null) {
            style.apply(this);
        }
        RemoteViews makeContentView = style != null ? style.makeContentView(this) : null;
        Notification c2 = c();
        if (makeContentView != null || (makeContentView = this.f522c.F) != null) {
            c2.contentView = makeContentView;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 16 && style != null && (makeBigContentView = style.makeBigContentView(this)) != null) {
            c2.bigContentView = makeBigContentView;
        }
        if (i2 >= 21 && style != null && (makeHeadsUpContentView = this.f522c.n.makeHeadsUpContentView(this)) != null) {
            c2.headsUpContentView = makeHeadsUpContentView;
        }
        if (i2 >= 16 && style != null && (extras = NotificationCompat.getExtras(c2)) != null) {
            style.addCompatExtras(extras);
        }
        return c2;
    }

    protected Notification c() {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            return this.b.build();
        }
        if (i2 >= 24) {
            Notification build = this.b.build();
            if (this.h != 0) {
                if (build.getGroup() != null && (build.flags & 512) != 0 && this.h == 2) {
                    g(build);
                }
                if (build.getGroup() != null && (build.flags & 512) == 0 && this.h == 1) {
                    g(build);
                }
            }
            return build;
        }
        if (i2 >= 21) {
            this.b.setExtras(this.g);
            Notification build2 = this.b.build();
            RemoteViews remoteViews = this.f523d;
            if (remoteViews != null) {
                build2.contentView = remoteViews;
            }
            RemoteViews remoteViews2 = this.e;
            if (remoteViews2 != null) {
                build2.bigContentView = remoteViews2;
            }
            RemoteViews remoteViews3 = this.f524i;
            if (remoteViews3 != null) {
                build2.headsUpContentView = remoteViews3;
            }
            if (this.h != 0) {
                if (build2.getGroup() != null && (build2.flags & 512) != 0 && this.h == 2) {
                    g(build2);
                }
                if (build2.getGroup() != null && (build2.flags & 512) == 0 && this.h == 1) {
                    g(build2);
                }
            }
            return build2;
        }
        if (i2 >= 20) {
            this.b.setExtras(this.g);
            Notification build3 = this.b.build();
            RemoteViews remoteViews4 = this.f523d;
            if (remoteViews4 != null) {
                build3.contentView = remoteViews4;
            }
            RemoteViews remoteViews5 = this.e;
            if (remoteViews5 != null) {
                build3.bigContentView = remoteViews5;
            }
            if (this.h != 0) {
                if (build3.getGroup() != null && (build3.flags & 512) != 0 && this.h == 2) {
                    g(build3);
                }
                if (build3.getGroup() != null && (build3.flags & 512) == 0 && this.h == 1) {
                    g(build3);
                }
            }
            return build3;
        }
        if (i2 >= 19) {
            SparseArray<Bundle> a = c.a(this.f);
            if (a != null) {
                this.g.putSparseParcelableArray("android.support.actionExtras", a);
            }
            this.b.setExtras(this.g);
            Notification build4 = this.b.build();
            RemoteViews remoteViews6 = this.f523d;
            if (remoteViews6 != null) {
                build4.contentView = remoteViews6;
            }
            RemoteViews remoteViews7 = this.e;
            if (remoteViews7 != null) {
                build4.bigContentView = remoteViews7;
            }
            return build4;
        }
        if (i2 < 16) {
            return this.b.getNotification();
        }
        Notification build5 = this.b.build();
        Bundle extras = NotificationCompat.getExtras(build5);
        Bundle bundle = new Bundle(this.g);
        for (String str : this.g.keySet()) {
            if (extras.containsKey(str)) {
                bundle.remove(str);
            }
        }
        extras.putAll(bundle);
        SparseArray<Bundle> a2 = c.a(this.f);
        if (a2 != null) {
            NotificationCompat.getExtras(build5).putSparseParcelableArray("android.support.actionExtras", a2);
        }
        RemoteViews remoteViews8 = this.f523d;
        if (remoteViews8 != null) {
            build5.contentView = remoteViews8;
        }
        RemoteViews remoteViews9 = this.e;
        if (remoteViews9 != null) {
            build5.bigContentView = remoteViews9;
        }
        return build5;
    }

    public Context e() {
        return this.a;
    }

    @Override // androidx.core.app.NotificationBuilderWithBuilderAccessor
    public Notification.Builder getBuilder() {
        return this.b;
    }
}
