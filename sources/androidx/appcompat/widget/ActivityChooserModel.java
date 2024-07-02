package androidx.appcompat.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ActivityChooserModel extends DataSetObservable {
    static final String n = ActivityChooserModel.class.getSimpleName();
    private static final Object o = new Object();
    private static final Map<String, ActivityChooserModel> p = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    final Context f143d;
    final String e;
    private Intent f;
    private OnChooseActivityListener m;
    private final Object a = new Object();
    private final List<ActivityResolveInfo> b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    private final List<HistoricalRecord> f142c = new ArrayList();
    private ActivitySorter g = new a();
    private int h = 50;

    /* renamed from: i, reason: collision with root package name */
    boolean f144i = true;
    private boolean j = false;
    private boolean k = true;
    private boolean l = false;

    /* loaded from: classes.dex */
    public interface ActivityChooserModelClient {
        void setActivityChooserModel(ActivityChooserModel activityChooserModel);
    }

    /* loaded from: classes.dex */
    public static final class ActivityResolveInfo implements Comparable<ActivityResolveInfo> {
        public final ResolveInfo resolveInfo;
        public float weight;

        public ActivityResolveInfo(ResolveInfo resolveInfo) {
            this.resolveInfo = resolveInfo;
        }

        @Override // java.lang.Comparable
        public int compareTo(ActivityResolveInfo activityResolveInfo) {
            return Float.floatToIntBits(activityResolveInfo.weight) - Float.floatToIntBits(this.weight);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && ActivityResolveInfo.class == obj.getClass() && Float.floatToIntBits(this.weight) == Float.floatToIntBits(((ActivityResolveInfo) obj).weight);
        }

        public int hashCode() {
            return Float.floatToIntBits(this.weight) + 31;
        }

        public String toString() {
            return "[resolveInfo:" + this.resolveInfo.toString() + "; weight:" + new BigDecimal(this.weight) + "]";
        }
    }

    /* loaded from: classes.dex */
    public interface ActivitySorter {
        void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2);
    }

    /* loaded from: classes.dex */
    public static final class HistoricalRecord {
        public final ComponentName activity;
        public final long time;
        public final float weight;

        public HistoricalRecord(ComponentName componentName, long j, float f) {
            this.activity = componentName;
            this.time = j;
            this.weight = f;
        }

        public HistoricalRecord(String str, long j, float f) {
            this(ComponentName.unflattenFromString(str), j, f);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || HistoricalRecord.class != obj.getClass()) {
                return false;
            }
            HistoricalRecord historicalRecord = (HistoricalRecord) obj;
            ComponentName componentName = this.activity;
            if (componentName == null) {
                if (historicalRecord.activity != null) {
                    return false;
                }
            } else if (!componentName.equals(historicalRecord.activity)) {
                return false;
            }
            return this.time == historicalRecord.time && Float.floatToIntBits(this.weight) == Float.floatToIntBits(historicalRecord.weight);
        }

        public int hashCode() {
            ComponentName componentName = this.activity;
            int hashCode = componentName == null ? 0 : componentName.hashCode();
            long j = this.time;
            return ((((hashCode + 31) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + Float.floatToIntBits(this.weight);
        }

        public String toString() {
            return "[; activity:" + this.activity + "; time:" + this.time + "; weight:" + new BigDecimal(this.weight) + "]";
        }
    }

    /* loaded from: classes.dex */
    public interface OnChooseActivityListener {
        boolean onChooseActivity(ActivityChooserModel activityChooserModel, Intent intent);
    }

    /* loaded from: classes.dex */
    private static final class a implements ActivitySorter {
        private final Map<ComponentName, ActivityResolveInfo> a = new HashMap();

        a() {
        }

        @Override // androidx.appcompat.widget.ActivityChooserModel.ActivitySorter
        public void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2) {
            Map<ComponentName, ActivityResolveInfo> map = this.a;
            map.clear();
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                ActivityResolveInfo activityResolveInfo = list.get(i2);
                activityResolveInfo.weight = 0.0f;
                ActivityInfo activityInfo = activityResolveInfo.resolveInfo.activityInfo;
                map.put(new ComponentName(activityInfo.packageName, activityInfo.name), activityResolveInfo);
            }
            float f = 1.0f;
            for (int size2 = list2.size() - 1; size2 >= 0; size2--) {
                HistoricalRecord historicalRecord = list2.get(size2);
                ActivityResolveInfo activityResolveInfo2 = map.get(historicalRecord.activity);
                if (activityResolveInfo2 != null) {
                    activityResolveInfo2.weight += historicalRecord.weight * f;
                    f *= 0.95f;
                }
            }
            Collections.sort(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class b extends AsyncTask<Object, Void, Void> {
        b() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x006b, code lost:
        
            if (r15 != null) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x00d3, code lost:
        
            return null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x006d, code lost:
        
            r15.close();
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0090, code lost:
        
            if (r15 == null) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00d0, code lost:
        
            if (r15 == null) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00b0, code lost:
        
            if (r15 == null) goto L30;
         */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Void doInBackground(java.lang.Object... r15) {
            /*
                Method dump skipped, instructions count: 244
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActivityChooserModel.b.doInBackground(java.lang.Object[]):java.lang.Void");
        }
    }

    private ActivityChooserModel(Context context, String str) {
        this.f143d = context.getApplicationContext();
        if (TextUtils.isEmpty(str) || str.endsWith(".xml")) {
            this.e = str;
            return;
        }
        this.e = str + ".xml";
    }

    private boolean a(HistoricalRecord historicalRecord) {
        boolean add = this.f142c.add(historicalRecord);
        if (add) {
            this.k = true;
            l();
            k();
            r();
            notifyChanged();
        }
        return add;
    }

    private void c() {
        boolean j = j() | m();
        l();
        if (j) {
            r();
            notifyChanged();
        }
    }

    public static ActivityChooserModel d(Context context, String str) {
        ActivityChooserModel activityChooserModel;
        synchronized (o) {
            Map<String, ActivityChooserModel> map = p;
            activityChooserModel = map.get(str);
            if (activityChooserModel == null) {
                activityChooserModel = new ActivityChooserModel(context, str);
                map.put(str, activityChooserModel);
            }
        }
        return activityChooserModel;
    }

    private boolean j() {
        if (!this.l || this.f == null) {
            return false;
        }
        this.l = false;
        this.b.clear();
        List<ResolveInfo> queryIntentActivities = this.f143d.getPackageManager().queryIntentActivities(this.f, 0);
        int size = queryIntentActivities.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.b.add(new ActivityResolveInfo(queryIntentActivities.get(i2)));
        }
        return true;
    }

    private void k() {
        if (!this.j) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }
        if (this.k) {
            this.k = false;
            if (TextUtils.isEmpty(this.e)) {
                return;
            }
            new b().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new ArrayList(this.f142c), this.e);
        }
    }

    private void l() {
        int size = this.f142c.size() - this.h;
        if (size <= 0) {
            return;
        }
        this.k = true;
        for (int i2 = 0; i2 < size; i2++) {
            this.f142c.remove(0);
        }
    }

    private boolean m() {
        if (!this.f144i || !this.k || TextUtils.isEmpty(this.e)) {
            return false;
        }
        this.f144i = false;
        this.j = true;
        n();
        return true;
    }

    private void n() {
        XmlPullParser newPullParser;
        try {
            FileInputStream openFileInput = this.f143d.openFileInput(this.e);
            try {
                try {
                    try {
                        newPullParser = Xml.newPullParser();
                        newPullParser.setInput(openFileInput, "UTF-8");
                        for (int i2 = 0; i2 != 1 && i2 != 2; i2 = newPullParser.next()) {
                        }
                    } catch (XmlPullParserException e) {
                        Log.e(n, "Error reading historical recrod file: " + this.e, e);
                        if (openFileInput == null) {
                            return;
                        }
                    }
                } catch (IOException e2) {
                    Log.e(n, "Error reading historical recrod file: " + this.e, e2);
                    if (openFileInput == null) {
                        return;
                    }
                }
                if (!"historical-records".equals(newPullParser.getName())) {
                    throw new XmlPullParserException("Share records file does not start with historical-records tag.");
                }
                List<HistoricalRecord> list = this.f142c;
                list.clear();
                while (true) {
                    int next = newPullParser.next();
                    if (next == 1) {
                        if (openFileInput == null) {
                            return;
                        }
                    } else if (next != 3 && next != 4) {
                        if (!"historical-record".equals(newPullParser.getName())) {
                            throw new XmlPullParserException("Share records file not well-formed.");
                        }
                        list.add(new HistoricalRecord(newPullParser.getAttributeValue(null, "activity"), Long.parseLong(newPullParser.getAttributeValue(null, "time")), Float.parseFloat(newPullParser.getAttributeValue(null, "weight"))));
                    }
                }
                try {
                    openFileInput.close();
                } catch (IOException unused) {
                }
            } catch (Throwable th) {
                if (openFileInput != null) {
                    try {
                        openFileInput.close();
                    } catch (IOException unused2) {
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException unused3) {
        }
    }

    private boolean r() {
        if (this.g == null || this.f == null || this.b.isEmpty() || this.f142c.isEmpty()) {
            return false;
        }
        this.g.sort(this.f, this.b, Collections.unmodifiableList(this.f142c));
        return true;
    }

    public Intent b(int i2) {
        synchronized (this.a) {
            if (this.f == null) {
                return null;
            }
            c();
            ActivityInfo activityInfo = this.b.get(i2).resolveInfo.activityInfo;
            ComponentName componentName = new ComponentName(activityInfo.packageName, activityInfo.name);
            Intent intent = new Intent(this.f);
            intent.setComponent(componentName);
            if (this.m != null) {
                if (this.m.onChooseActivity(this, new Intent(intent))) {
                    return null;
                }
            }
            a(new HistoricalRecord(componentName, System.currentTimeMillis(), 1.0f));
            return intent;
        }
    }

    public ResolveInfo e(int i2) {
        ResolveInfo resolveInfo;
        synchronized (this.a) {
            c();
            resolveInfo = this.b.get(i2).resolveInfo;
        }
        return resolveInfo;
    }

    public int f() {
        int size;
        synchronized (this.a) {
            c();
            size = this.b.size();
        }
        return size;
    }

    public int g(ResolveInfo resolveInfo) {
        synchronized (this.a) {
            c();
            List<ActivityResolveInfo> list = this.b;
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (list.get(i2).resolveInfo == resolveInfo) {
                    return i2;
                }
            }
            return -1;
        }
    }

    public ResolveInfo h() {
        synchronized (this.a) {
            c();
            if (this.b.isEmpty()) {
                return null;
            }
            return this.b.get(0).resolveInfo;
        }
    }

    public int i() {
        int size;
        synchronized (this.a) {
            c();
            size = this.f142c.size();
        }
        return size;
    }

    public void o(int i2) {
        synchronized (this.a) {
            c();
            ActivityResolveInfo activityResolveInfo = this.b.get(i2);
            ActivityResolveInfo activityResolveInfo2 = this.b.get(0);
            float f = activityResolveInfo2 != null ? (activityResolveInfo2.weight - activityResolveInfo.weight) + 5.0f : 1.0f;
            ActivityInfo activityInfo = activityResolveInfo.resolveInfo.activityInfo;
            a(new HistoricalRecord(new ComponentName(activityInfo.packageName, activityInfo.name), System.currentTimeMillis(), f));
        }
    }

    public void p(Intent intent) {
        synchronized (this.a) {
            if (this.f == intent) {
                return;
            }
            this.f = intent;
            this.l = true;
            c();
        }
    }

    public void q(OnChooseActivityListener onChooseActivityListener) {
        synchronized (this.a) {
            this.m = onChooseActivityListener;
        }
    }
}
