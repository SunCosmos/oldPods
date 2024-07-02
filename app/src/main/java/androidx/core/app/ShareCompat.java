package androidx.core.app;

import android.app.Activity;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.core.util.Preconditions;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class ShareCompat {
    public static final String EXTRA_CALLING_ACTIVITY = "androidx.core.app.EXTRA_CALLING_ACTIVITY";
    public static final String EXTRA_CALLING_ACTIVITY_INTEROP = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";
    public static final String EXTRA_CALLING_PACKAGE = "androidx.core.app.EXTRA_CALLING_PACKAGE";
    public static final String EXTRA_CALLING_PACKAGE_INTEROP = "android.support.v4.app.EXTRA_CALLING_PACKAGE";

    /* loaded from: classes.dex */
    public static class IntentBuilder {

        @NonNull
        private final Context a;

        @NonNull
        private final Intent b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        private CharSequence f514c;

        /* renamed from: d, reason: collision with root package name */
        @Nullable
        private ArrayList<String> f515d;

        @Nullable
        private ArrayList<String> e;

        @Nullable
        private ArrayList<String> f;

        @Nullable
        private ArrayList<Uri> g;

        public IntentBuilder(@NonNull Context context) {
            Activity activity;
            this.a = (Context) Preconditions.checkNotNull(context);
            Intent action = new Intent().setAction("android.intent.action.SEND");
            this.b = action;
            action.putExtra(ShareCompat.EXTRA_CALLING_PACKAGE, context.getPackageName());
            action.putExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE", context.getPackageName());
            action.addFlags(524288);
            while (true) {
                if (!(context instanceof ContextWrapper)) {
                    activity = null;
                    break;
                } else {
                    if (context instanceof Activity) {
                        activity = (Activity) context;
                        break;
                    }
                    context = ((ContextWrapper) context).getBaseContext();
                }
            }
            if (activity != null) {
                ComponentName componentName = activity.getComponentName();
                this.b.putExtra(ShareCompat.EXTRA_CALLING_ACTIVITY, componentName);
                this.b.putExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY", componentName);
            }
        }

        private void a(String str, ArrayList<String> arrayList) {
            String[] stringArrayExtra = this.b.getStringArrayExtra(str);
            int length = stringArrayExtra != null ? stringArrayExtra.length : 0;
            String[] strArr = new String[arrayList.size() + length];
            arrayList.toArray(strArr);
            if (stringArrayExtra != null) {
                System.arraycopy(stringArrayExtra, 0, strArr, arrayList.size(), length);
            }
            this.b.putExtra(str, strArr);
        }

        private void b(@Nullable String str, @NonNull String[] strArr) {
            Intent intent = getIntent();
            String[] stringArrayExtra = intent.getStringArrayExtra(str);
            int length = stringArrayExtra != null ? stringArrayExtra.length : 0;
            String[] strArr2 = new String[strArr.length + length];
            if (stringArrayExtra != null) {
                System.arraycopy(stringArrayExtra, 0, strArr2, 0, length);
            }
            System.arraycopy(strArr, 0, strArr2, length, strArr.length);
            intent.putExtra(str, strArr2);
        }

        @NonNull
        @Deprecated
        public static IntentBuilder from(@NonNull Activity activity) {
            return new IntentBuilder(activity);
        }

        @NonNull
        public IntentBuilder addEmailBcc(@NonNull String str) {
            if (this.f == null) {
                this.f = new ArrayList<>();
            }
            this.f.add(str);
            return this;
        }

        @NonNull
        public IntentBuilder addEmailBcc(@NonNull String[] strArr) {
            b("android.intent.extra.BCC", strArr);
            return this;
        }

        @NonNull
        public IntentBuilder addEmailCc(@NonNull String str) {
            if (this.e == null) {
                this.e = new ArrayList<>();
            }
            this.e.add(str);
            return this;
        }

        @NonNull
        public IntentBuilder addEmailCc(@NonNull String[] strArr) {
            b("android.intent.extra.CC", strArr);
            return this;
        }

        @NonNull
        public IntentBuilder addEmailTo(@NonNull String str) {
            if (this.f515d == null) {
                this.f515d = new ArrayList<>();
            }
            this.f515d.add(str);
            return this;
        }

        @NonNull
        public IntentBuilder addEmailTo(@NonNull String[] strArr) {
            b("android.intent.extra.EMAIL", strArr);
            return this;
        }

        @NonNull
        public IntentBuilder addStream(@NonNull Uri uri) {
            if (this.g == null) {
                this.g = new ArrayList<>();
            }
            this.g.add(uri);
            return this;
        }

        @NonNull
        Context c() {
            return this.a;
        }

        @NonNull
        public Intent createChooserIntent() {
            return Intent.createChooser(getIntent(), this.f514c);
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x0056, code lost:
        
            if (r0 >= 16) goto L31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0076, code lost:
        
            androidx.core.app.ShareCompat.a.a(r6.b, r6.g);
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0074, code lost:
        
            if (r0 >= 16) goto L31;
         */
        @androidx.annotation.NonNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.content.Intent getIntent() {
            /*
                r6 = this;
                int r0 = android.os.Build.VERSION.SDK_INT
                java.util.ArrayList<java.lang.String> r1 = r6.f515d
                r2 = 0
                if (r1 == 0) goto Le
                java.lang.String r3 = "android.intent.extra.EMAIL"
                r6.a(r3, r1)
                r6.f515d = r2
            Le:
                java.util.ArrayList<java.lang.String> r1 = r6.e
                if (r1 == 0) goto L19
                java.lang.String r3 = "android.intent.extra.CC"
                r6.a(r3, r1)
                r6.e = r2
            L19:
                java.util.ArrayList<java.lang.String> r1 = r6.f
                if (r1 == 0) goto L24
                java.lang.String r3 = "android.intent.extra.BCC"
                r6.a(r3, r1)
                r6.f = r2
            L24:
                java.util.ArrayList<android.net.Uri> r1 = r6.g
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L31
                int r1 = r1.size()
                if (r1 <= r2) goto L31
                goto L32
            L31:
                r2 = 0
            L32:
                r1 = 16
                java.lang.String r4 = "android.intent.extra.STREAM"
                if (r2 != 0) goto L66
                android.content.Intent r2 = r6.b
                java.lang.String r5 = "android.intent.action.SEND"
                r2.setAction(r5)
                java.util.ArrayList<android.net.Uri> r2 = r6.g
                if (r2 == 0) goto L59
                boolean r2 = r2.isEmpty()
                if (r2 != 0) goto L59
                android.content.Intent r2 = r6.b
                java.util.ArrayList<android.net.Uri> r5 = r6.g
                java.lang.Object r3 = r5.get(r3)
                android.os.Parcelable r3 = (android.os.Parcelable) r3
                r2.putExtra(r4, r3)
                if (r0 < r1) goto L7d
                goto L76
            L59:
                android.content.Intent r2 = r6.b
                r2.removeExtra(r4)
                if (r0 < r1) goto L7d
                android.content.Intent r0 = r6.b
                androidx.core.app.ShareCompat.a.b(r0)
                goto L7d
            L66:
                android.content.Intent r2 = r6.b
                java.lang.String r3 = "android.intent.action.SEND_MULTIPLE"
                r2.setAction(r3)
                android.content.Intent r2 = r6.b
                java.util.ArrayList<android.net.Uri> r3 = r6.g
                r2.putParcelableArrayListExtra(r4, r3)
                if (r0 < r1) goto L7d
            L76:
                android.content.Intent r0 = r6.b
                java.util.ArrayList<android.net.Uri> r1 = r6.g
                androidx.core.app.ShareCompat.a.a(r0, r1)
            L7d:
                android.content.Intent r0 = r6.b
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.ShareCompat.IntentBuilder.getIntent():android.content.Intent");
        }

        @NonNull
        public IntentBuilder setChooserTitle(@StringRes int i2) {
            return setChooserTitle(this.a.getText(i2));
        }

        @NonNull
        public IntentBuilder setChooserTitle(@Nullable CharSequence charSequence) {
            this.f514c = charSequence;
            return this;
        }

        @NonNull
        public IntentBuilder setEmailBcc(@Nullable String[] strArr) {
            this.b.putExtra("android.intent.extra.BCC", strArr);
            return this;
        }

        @NonNull
        public IntentBuilder setEmailCc(@Nullable String[] strArr) {
            this.b.putExtra("android.intent.extra.CC", strArr);
            return this;
        }

        @NonNull
        public IntentBuilder setEmailTo(@Nullable String[] strArr) {
            if (this.f515d != null) {
                this.f515d = null;
            }
            this.b.putExtra("android.intent.extra.EMAIL", strArr);
            return this;
        }

        @NonNull
        public IntentBuilder setHtmlText(@Nullable String str) {
            this.b.putExtra("android.intent.extra.HTML_TEXT", str);
            if (!this.b.hasExtra("android.intent.extra.TEXT")) {
                setText(Html.fromHtml(str));
            }
            return this;
        }

        @NonNull
        public IntentBuilder setStream(@Nullable Uri uri) {
            this.g = null;
            if (uri != null) {
                addStream(uri);
            }
            return this;
        }

        @NonNull
        public IntentBuilder setSubject(@Nullable String str) {
            this.b.putExtra("android.intent.extra.SUBJECT", str);
            return this;
        }

        @NonNull
        public IntentBuilder setText(@Nullable CharSequence charSequence) {
            this.b.putExtra("android.intent.extra.TEXT", charSequence);
            return this;
        }

        @NonNull
        public IntentBuilder setType(@Nullable String str) {
            this.b.setType(str);
            return this;
        }

        public void startChooser() {
            this.a.startActivity(createChooserIntent());
        }
    }

    /* loaded from: classes.dex */
    public static class IntentReader {

        @NonNull
        private final Context a;

        @NonNull
        private final Intent b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        private final String f516c;

        /* renamed from: d, reason: collision with root package name */
        @Nullable
        private final ComponentName f517d;

        @Nullable
        private ArrayList<Uri> e;

        public IntentReader(@NonNull Activity activity) {
            this((Context) Preconditions.checkNotNull(activity), activity.getIntent());
        }

        public IntentReader(@NonNull Context context, @NonNull Intent intent) {
            this.a = (Context) Preconditions.checkNotNull(context);
            this.b = (Intent) Preconditions.checkNotNull(intent);
            this.f516c = ShareCompat.b(intent);
            this.f517d = ShareCompat.a(intent);
        }

        private static void a(StringBuilder sb, CharSequence charSequence, int i2, int i3) {
            String str;
            while (i2 < i3) {
                char charAt = charSequence.charAt(i2);
                if (charAt == '<') {
                    str = "&lt;";
                } else if (charAt == '>') {
                    str = "&gt;";
                } else if (charAt == '&') {
                    str = "&amp;";
                } else if (charAt > '~' || charAt < ' ') {
                    sb.append("&#");
                    sb.append((int) charAt);
                    str = ";";
                } else {
                    if (charAt == ' ') {
                        while (true) {
                            int i4 = i2 + 1;
                            if (i4 >= i3 || charSequence.charAt(i4) != ' ') {
                                break;
                            }
                            sb.append("&nbsp;");
                            i2 = i4;
                        }
                        sb.append(' ');
                    } else {
                        sb.append(charAt);
                    }
                    i2++;
                }
                sb.append(str);
                i2++;
            }
        }

        @NonNull
        @Deprecated
        public static IntentReader from(@NonNull Activity activity) {
            return new IntentReader(activity);
        }

        @Nullable
        public ComponentName getCallingActivity() {
            return this.f517d;
        }

        @Nullable
        public Drawable getCallingActivityIcon() {
            if (this.f517d == null) {
                return null;
            }
            try {
                return this.a.getPackageManager().getActivityIcon(this.f517d);
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("IntentReader", "Could not retrieve icon for calling activity", e);
                return null;
            }
        }

        @Nullable
        public Drawable getCallingApplicationIcon() {
            if (this.f516c == null) {
                return null;
            }
            try {
                return this.a.getPackageManager().getApplicationIcon(this.f516c);
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("IntentReader", "Could not retrieve icon for calling application", e);
                return null;
            }
        }

        @Nullable
        public CharSequence getCallingApplicationLabel() {
            if (this.f516c == null) {
                return null;
            }
            PackageManager packageManager = this.a.getPackageManager();
            try {
                return packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.f516c, 0));
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("IntentReader", "Could not retrieve label for calling application", e);
                return null;
            }
        }

        @Nullable
        public String getCallingPackage() {
            return this.f516c;
        }

        @Nullable
        public String[] getEmailBcc() {
            return this.b.getStringArrayExtra("android.intent.extra.BCC");
        }

        @Nullable
        public String[] getEmailCc() {
            return this.b.getStringArrayExtra("android.intent.extra.CC");
        }

        @Nullable
        public String[] getEmailTo() {
            return this.b.getStringArrayExtra("android.intent.extra.EMAIL");
        }

        @Nullable
        public String getHtmlText() {
            String stringExtra = this.b.getStringExtra("android.intent.extra.HTML_TEXT");
            if (stringExtra != null) {
                return stringExtra;
            }
            CharSequence text = getText();
            if (text instanceof Spanned) {
                return Html.toHtml((Spanned) text);
            }
            if (text == null) {
                return stringExtra;
            }
            if (Build.VERSION.SDK_INT >= 16) {
                return Html.escapeHtml(text);
            }
            StringBuilder sb = new StringBuilder();
            a(sb, text, 0, text.length());
            return sb.toString();
        }

        @Nullable
        public Uri getStream() {
            return (Uri) this.b.getParcelableExtra("android.intent.extra.STREAM");
        }

        @Nullable
        public Uri getStream(int i2) {
            Object parcelableExtra;
            if (this.e == null && isMultipleShare()) {
                this.e = this.b.getParcelableArrayListExtra("android.intent.extra.STREAM");
            }
            ArrayList<Uri> arrayList = this.e;
            if (arrayList != null) {
                parcelableExtra = arrayList.get(i2);
            } else {
                if (i2 != 0) {
                    throw new IndexOutOfBoundsException("Stream items available: " + getStreamCount() + " index requested: " + i2);
                }
                parcelableExtra = this.b.getParcelableExtra("android.intent.extra.STREAM");
            }
            return (Uri) parcelableExtra;
        }

        public int getStreamCount() {
            if (this.e == null && isMultipleShare()) {
                this.e = this.b.getParcelableArrayListExtra("android.intent.extra.STREAM");
            }
            ArrayList<Uri> arrayList = this.e;
            return arrayList != null ? arrayList.size() : this.b.hasExtra("android.intent.extra.STREAM") ? 1 : 0;
        }

        @Nullable
        public String getSubject() {
            return this.b.getStringExtra("android.intent.extra.SUBJECT");
        }

        @Nullable
        public CharSequence getText() {
            return this.b.getCharSequenceExtra("android.intent.extra.TEXT");
        }

        @Nullable
        public String getType() {
            return this.b.getType();
        }

        public boolean isMultipleShare() {
            return "android.intent.action.SEND_MULTIPLE".equals(this.b.getAction());
        }

        public boolean isShareIntent() {
            String action = this.b.getAction();
            return "android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action);
        }

        public boolean isSingleShare() {
            return "android.intent.action.SEND".equals(this.b.getAction());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(16)
    /* loaded from: classes.dex */
    public static class a {
        static void a(@NonNull Intent intent, @NonNull ArrayList<Uri> arrayList) {
            ClipData clipData = new ClipData(null, new String[]{intent.getType()}, new ClipData.Item(intent.getCharSequenceExtra("android.intent.extra.TEXT"), intent.getStringExtra("android.intent.extra.HTML_TEXT"), null, arrayList.get(0)));
            int size = arrayList.size();
            for (int i2 = 1; i2 < size; i2++) {
                clipData.addItem(new ClipData.Item(arrayList.get(i2)));
            }
            intent.setClipData(clipData);
            intent.addFlags(1);
        }

        static void b(@NonNull Intent intent) {
            intent.setClipData(null);
            intent.setFlags(intent.getFlags() & (-2));
        }
    }

    private ShareCompat() {
    }

    @Nullable
    static ComponentName a(@NonNull Intent intent) {
        ComponentName componentName = (ComponentName) intent.getParcelableExtra(EXTRA_CALLING_ACTIVITY);
        return componentName == null ? (ComponentName) intent.getParcelableExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY") : componentName;
    }

    @Nullable
    static String b(@NonNull Intent intent) {
        String stringExtra = intent.getStringExtra(EXTRA_CALLING_PACKAGE);
        return stringExtra == null ? intent.getStringExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE") : stringExtra;
    }

    @Deprecated
    public static void configureMenuItem(@NonNull Menu menu, @IdRes int i2, @NonNull IntentBuilder intentBuilder) {
        MenuItem findItem = menu.findItem(i2);
        if (findItem != null) {
            configureMenuItem(findItem, intentBuilder);
            return;
        }
        throw new IllegalArgumentException("Could not find menu item with id " + i2 + " in the supplied menu");
    }

    @Deprecated
    public static void configureMenuItem(@NonNull MenuItem menuItem, @NonNull IntentBuilder intentBuilder) {
        ActionProvider actionProvider = menuItem.getActionProvider();
        ShareActionProvider shareActionProvider = !(actionProvider instanceof ShareActionProvider) ? new ShareActionProvider(intentBuilder.c()) : (ShareActionProvider) actionProvider;
        shareActionProvider.setShareHistoryFileName(".sharecompat_" + intentBuilder.c().getClass().getName());
        shareActionProvider.setShareIntent(intentBuilder.getIntent());
        menuItem.setActionProvider(shareActionProvider);
        if (Build.VERSION.SDK_INT >= 16 || menuItem.hasSubMenu()) {
            return;
        }
        menuItem.setIntent(intentBuilder.createChooserIntent());
    }

    @Nullable
    public static ComponentName getCallingActivity(@NonNull Activity activity) {
        Intent intent = activity.getIntent();
        ComponentName callingActivity = activity.getCallingActivity();
        return callingActivity == null ? a(intent) : callingActivity;
    }

    @Nullable
    public static String getCallingPackage(@NonNull Activity activity) {
        Intent intent = activity.getIntent();
        String callingPackage = activity.getCallingPackage();
        return (callingPackage != null || intent == null) ? callingPackage : b(intent);
    }
}
