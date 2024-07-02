package androidx.core.app;

import android.app.RemoteInput;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class RemoteInput {
    public static final int EDIT_CHOICES_BEFORE_SENDING_AUTO = 0;
    public static final int EDIT_CHOICES_BEFORE_SENDING_DISABLED = 1;
    public static final int EDIT_CHOICES_BEFORE_SENDING_ENABLED = 2;
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
    public static final int SOURCE_CHOICE = 1;
    public static final int SOURCE_FREE_FORM_INPUT = 0;
    private final String a;
    private final CharSequence b;

    /* renamed from: c, reason: collision with root package name */
    private final CharSequence[] f510c;

    /* renamed from: d, reason: collision with root package name */
    private final boolean f511d;
    private final int e;
    private final Bundle f;
    private final Set<String> g;

    /* loaded from: classes.dex */
    public static final class Builder {
        private final String a;

        /* renamed from: d, reason: collision with root package name */
        private CharSequence f513d;
        private CharSequence[] e;
        private final Set<String> b = new HashSet();

        /* renamed from: c, reason: collision with root package name */
        private final Bundle f512c = new Bundle();
        private boolean f = true;
        private int g = 0;

        public Builder(@NonNull String str) {
            if (str == null) {
                throw new IllegalArgumentException("Result key can't be null");
            }
            this.a = str;
        }

        @NonNull
        public Builder addExtras(@NonNull Bundle bundle) {
            if (bundle != null) {
                this.f512c.putAll(bundle);
            }
            return this;
        }

        @NonNull
        public RemoteInput build() {
            return new RemoteInput(this.a, this.f513d, this.e, this.f, this.g, this.f512c, this.b);
        }

        @NonNull
        public Bundle getExtras() {
            return this.f512c;
        }

        @NonNull
        public Builder setAllowDataType(@NonNull String str, boolean z) {
            if (z) {
                this.b.add(str);
            } else {
                this.b.remove(str);
            }
            return this;
        }

        @NonNull
        public Builder setAllowFreeFormInput(boolean z) {
            this.f = z;
            return this;
        }

        @NonNull
        public Builder setChoices(@Nullable CharSequence[] charSequenceArr) {
            this.e = charSequenceArr;
            return this;
        }

        @NonNull
        public Builder setEditChoicesBeforeSending(int i2) {
            this.g = i2;
            return this;
        }

        @NonNull
        public Builder setLabel(@Nullable CharSequence charSequence) {
            this.f513d = charSequence;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface EditChoicesBeforeSending {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Source {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RemoteInput(String str, CharSequence charSequence, CharSequence[] charSequenceArr, boolean z, int i2, Bundle bundle, Set<String> set) {
        this.a = str;
        this.b = charSequence;
        this.f510c = charSequenceArr;
        this.f511d = z;
        this.e = i2;
        this.f = bundle;
        this.g = set;
        if (getEditChoicesBeforeSending() == 2 && !getAllowFreeFormInput()) {
            throw new IllegalArgumentException("setEditChoicesBeforeSending requires setAllowFreeFormInput");
        }
    }

    @RequiresApi(20)
    static android.app.RemoteInput a(RemoteInput remoteInput) {
        RemoteInput.Builder addExtras = new RemoteInput.Builder(remoteInput.getResultKey()).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras());
        if (Build.VERSION.SDK_INT >= 29) {
            addExtras.setEditChoicesBeforeSending(remoteInput.getEditChoicesBeforeSending());
        }
        return addExtras.build();
    }

    public static void addDataResultToIntent(RemoteInput remoteInput, Intent intent, Map<String, Uri> map) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            android.app.RemoteInput.addDataResultToIntent(a(remoteInput), intent, map);
            return;
        }
        if (i2 >= 16) {
            Intent d2 = d(intent);
            if (d2 == null) {
                d2 = new Intent();
            }
            for (Map.Entry<String, Uri> entry : map.entrySet()) {
                String key = entry.getKey();
                Uri value = entry.getValue();
                if (key != null) {
                    Bundle bundleExtra = d2.getBundleExtra(e(key));
                    if (bundleExtra == null) {
                        bundleExtra = new Bundle();
                    }
                    bundleExtra.putString(remoteInput.getResultKey(), value.toString());
                    d2.putExtra(e(key), bundleExtra);
                }
            }
            intent.setClipData(ClipData.newIntent("android.remoteinput.results", d2));
        }
    }

    public static void addResultsToIntent(RemoteInput[] remoteInputArr, Intent intent, Bundle bundle) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            android.app.RemoteInput.addResultsToIntent(b(remoteInputArr), intent, bundle);
            return;
        }
        if (i2 >= 20) {
            Bundle resultsFromIntent = getResultsFromIntent(intent);
            int resultsSource = getResultsSource(intent);
            if (resultsFromIntent != null) {
                resultsFromIntent.putAll(bundle);
                bundle = resultsFromIntent;
            }
            for (RemoteInput remoteInput : remoteInputArr) {
                Map<String, Uri> dataResultsFromIntent = getDataResultsFromIntent(intent, remoteInput.getResultKey());
                android.app.RemoteInput.addResultsToIntent(b(new RemoteInput[]{remoteInput}), intent, bundle);
                if (dataResultsFromIntent != null) {
                    addDataResultToIntent(remoteInput, intent, dataResultsFromIntent);
                }
            }
            setResultsSource(intent, resultsSource);
            return;
        }
        if (i2 >= 16) {
            Intent d2 = d(intent);
            if (d2 == null) {
                d2 = new Intent();
            }
            Bundle bundleExtra = d2.getBundleExtra("android.remoteinput.resultsData");
            if (bundleExtra == null) {
                bundleExtra = new Bundle();
            }
            for (RemoteInput remoteInput2 : remoteInputArr) {
                Object obj = bundle.get(remoteInput2.getResultKey());
                if (obj instanceof CharSequence) {
                    bundleExtra.putCharSequence(remoteInput2.getResultKey(), (CharSequence) obj);
                }
            }
            d2.putExtra("android.remoteinput.resultsData", bundleExtra);
            intent.setClipData(ClipData.newIntent("android.remoteinput.results", d2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(20)
    public static android.app.RemoteInput[] b(RemoteInput[] remoteInputArr) {
        if (remoteInputArr == null) {
            return null;
        }
        android.app.RemoteInput[] remoteInputArr2 = new android.app.RemoteInput[remoteInputArr.length];
        for (int i2 = 0; i2 < remoteInputArr.length; i2++) {
            remoteInputArr2[i2] = a(remoteInputArr[i2]);
        }
        return remoteInputArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(20)
    public static RemoteInput c(android.app.RemoteInput remoteInput) {
        Builder addExtras = new Builder(remoteInput.getResultKey()).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras());
        if (Build.VERSION.SDK_INT >= 29) {
            addExtras.setEditChoicesBeforeSending(remoteInput.getEditChoicesBeforeSending());
        }
        return addExtras.build();
    }

    @RequiresApi(16)
    private static Intent d(Intent intent) {
        ClipData clipData = intent.getClipData();
        if (clipData == null) {
            return null;
        }
        ClipDescription description = clipData.getDescription();
        if (description.hasMimeType("text/vnd.android.intent") && description.getLabel().toString().contentEquals("android.remoteinput.results")) {
            return clipData.getItemAt(0).getIntent();
        }
        return null;
    }

    private static String e(String str) {
        return "android.remoteinput.dataTypeResultsData" + str;
    }

    public static Map<String, Uri> getDataResultsFromIntent(Intent intent, String str) {
        Intent d2;
        String string;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            return android.app.RemoteInput.getDataResultsFromIntent(intent, str);
        }
        if (i2 < 16 || (d2 = d(intent)) == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str2 : d2.getExtras().keySet()) {
            if (str2.startsWith("android.remoteinput.dataTypeResultsData")) {
                String substring = str2.substring(39);
                if (!substring.isEmpty() && (string = d2.getBundleExtra(str2).getString(str)) != null && !string.isEmpty()) {
                    hashMap.put(substring, Uri.parse(string));
                }
            }
        }
        if (hashMap.isEmpty()) {
            return null;
        }
        return hashMap;
    }

    public static Bundle getResultsFromIntent(Intent intent) {
        Intent d2;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 20) {
            return android.app.RemoteInput.getResultsFromIntent(intent);
        }
        if (i2 < 16 || (d2 = d(intent)) == null) {
            return null;
        }
        return (Bundle) d2.getExtras().getParcelable("android.remoteinput.resultsData");
    }

    public static int getResultsSource(@NonNull Intent intent) {
        Intent d2;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            return android.app.RemoteInput.getResultsSource(intent);
        }
        if (i2 < 16 || (d2 = d(intent)) == null) {
            return 0;
        }
        return d2.getExtras().getInt("android.remoteinput.resultsSource", 0);
    }

    public static void setResultsSource(@NonNull Intent intent, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 28) {
            android.app.RemoteInput.setResultsSource(intent, i2);
            return;
        }
        if (i3 >= 16) {
            Intent d2 = d(intent);
            if (d2 == null) {
                d2 = new Intent();
            }
            d2.putExtra("android.remoteinput.resultsSource", i2);
            intent.setClipData(ClipData.newIntent("android.remoteinput.results", d2));
        }
    }

    public boolean getAllowFreeFormInput() {
        return this.f511d;
    }

    public Set<String> getAllowedDataTypes() {
        return this.g;
    }

    public CharSequence[] getChoices() {
        return this.f510c;
    }

    public int getEditChoicesBeforeSending() {
        return this.e;
    }

    public Bundle getExtras() {
        return this.f;
    }

    public CharSequence getLabel() {
        return this.b;
    }

    public String getResultKey() {
        return this.a;
    }

    public boolean isDataOnly() {
        return (getAllowFreeFormInput() || (getChoices() != null && getChoices().length != 0) || getAllowedDataTypes() == null || getAllowedDataTypes().isEmpty()) ? false : true;
    }
}
