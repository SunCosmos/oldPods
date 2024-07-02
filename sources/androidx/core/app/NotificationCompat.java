package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.LocusId;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.annotation.ColorInt;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.R;
import androidx.core.app.Person;
import androidx.core.content.LocusIdCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.text.BidiFormatter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class NotificationCompat {
    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_LOCATION_SHARING = "location_sharing";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_MISSED_CALL = "missed_call";
    public static final String CATEGORY_NAVIGATION = "navigation";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_STOPWATCH = "stopwatch";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    public static final String CATEGORY_WORKOUT = "workout";

    @ColorInt
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_BIG_TEXT = "android.bigText";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_CHANNEL_GROUP_ID = "android.intent.extra.CHANNEL_GROUP_ID";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_CHANNEL_ID = "android.intent.extra.CHANNEL_ID";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_CHRONOMETER_COUNT_DOWN = "android.chronometerCountDown";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_COLORIZED = "android.colorized";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_COMPAT_TEMPLATE = "androidx.core.app.extra.COMPAT_TEMPLATE";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_HISTORIC_MESSAGES = "android.messages.historic";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_INFO_TEXT = "android.infoText";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_MESSAGES = "android.messages";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_NOTIFICATION_ID = "android.intent.extra.NOTIFICATION_ID";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_NOTIFICATION_TAG = "android.intent.extra.NOTIFICATION_TAG";

    @SuppressLint({"ActionValue"})
    @Deprecated
    public static final String EXTRA_PEOPLE = "android.people";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_PEOPLE_LIST = "android.people.list";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_PICTURE = "android.picture";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_PROGRESS = "android.progress";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_SMALL_ICON = "android.icon";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_SUB_TEXT = "android.subText";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_TEMPLATE = "android.template";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_TEXT = "android.text";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_TEXT_LINES = "android.textLines";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_TITLE = "android.title";

    @SuppressLint({"ActionValue"})
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_BUBBLE = 4096;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;

    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final String GROUP_KEY_SILENT = "silent";

    @SuppressLint({"ActionValue"})
    public static final String INTENT_CATEGORY_NOTIFICATION_PREFERENCES = "android.intent.category.NOTIFICATION_PREFERENCES";
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    /* loaded from: classes.dex */
    public static class Action {
        public static final int SEMANTIC_ACTION_ARCHIVE = 5;
        public static final int SEMANTIC_ACTION_CALL = 10;
        public static final int SEMANTIC_ACTION_DELETE = 4;
        public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
        public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
        public static final int SEMANTIC_ACTION_MUTE = 6;
        public static final int SEMANTIC_ACTION_NONE = 0;
        public static final int SEMANTIC_ACTION_REPLY = 1;
        public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
        public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
        public static final int SEMANTIC_ACTION_UNMUTE = 7;
        final Bundle a;
        public PendingIntent actionIntent;

        @Nullable
        private IconCompat b;

        /* renamed from: c, reason: collision with root package name */
        private final RemoteInput[] f469c;

        /* renamed from: d, reason: collision with root package name */
        private final RemoteInput[] f470d;
        private boolean e;
        boolean f;
        private final int g;
        private final boolean h;

        @Deprecated
        public int icon;
        public CharSequence title;

        /* loaded from: classes.dex */
        public static final class Builder {
            private final IconCompat a;
            private final CharSequence b;

            /* renamed from: c, reason: collision with root package name */
            private final PendingIntent f471c;

            /* renamed from: d, reason: collision with root package name */
            private boolean f472d;
            private final Bundle e;
            private ArrayList<RemoteInput> f;
            private int g;
            private boolean h;

            /* renamed from: i, reason: collision with root package name */
            private boolean f473i;

            public Builder(int i2, @Nullable CharSequence charSequence, @Nullable PendingIntent pendingIntent) {
                this(i2 != 0 ? IconCompat.createWithResource(null, "", i2) : null, charSequence, pendingIntent, new Bundle(), null, true, 0, true, false);
            }

            public Builder(@NonNull Action action) {
                this(action.getIconCompat(), action.title, action.actionIntent, new Bundle(action.a), action.getRemoteInputs(), action.getAllowGeneratedReplies(), action.getSemanticAction(), action.f, action.isContextual());
            }

            public Builder(@Nullable IconCompat iconCompat, @Nullable CharSequence charSequence, @Nullable PendingIntent pendingIntent) {
                this(iconCompat, charSequence, pendingIntent, new Bundle(), null, true, 0, true, false);
            }

            private Builder(@Nullable IconCompat iconCompat, @Nullable CharSequence charSequence, @Nullable PendingIntent pendingIntent, @NonNull Bundle bundle, @Nullable RemoteInput[] remoteInputArr, boolean z, int i2, boolean z2, boolean z3) {
                this.f472d = true;
                this.h = true;
                this.a = iconCompat;
                this.b = Builder.b(charSequence);
                this.f471c = pendingIntent;
                this.e = bundle;
                this.f = remoteInputArr == null ? null : new ArrayList<>(Arrays.asList(remoteInputArr));
                this.f472d = z;
                this.g = i2;
                this.h = z2;
                this.f473i = z3;
            }

            private void a() {
                if (this.f473i && this.f471c == null) {
                    throw new NullPointerException("Contextual Actions must contain a valid PendingIntent");
                }
            }

            @NonNull
            @RequiresApi(19)
            @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
            public static Builder fromAndroidAction(@NonNull Notification.Action action) {
                android.app.RemoteInput[] remoteInputs;
                int i2 = Build.VERSION.SDK_INT;
                Builder builder = (i2 < 23 || action.getIcon() == null) ? new Builder(action.icon, action.title, action.actionIntent) : new Builder(IconCompat.createFromIcon(action.getIcon()), action.title, action.actionIntent);
                if (i2 >= 20 && (remoteInputs = action.getRemoteInputs()) != null && remoteInputs.length != 0) {
                    for (android.app.RemoteInput remoteInput : remoteInputs) {
                        builder.addRemoteInput(RemoteInput.c(remoteInput));
                    }
                }
                if (i2 >= 24) {
                    builder.f472d = action.getAllowGeneratedReplies();
                }
                if (i2 >= 28) {
                    builder.setSemanticAction(action.getSemanticAction());
                }
                if (i2 >= 29) {
                    builder.setContextual(action.isContextual());
                }
                return builder;
            }

            @NonNull
            public Builder addExtras(@Nullable Bundle bundle) {
                if (bundle != null) {
                    this.e.putAll(bundle);
                }
                return this;
            }

            @NonNull
            public Builder addRemoteInput(@Nullable RemoteInput remoteInput) {
                if (this.f == null) {
                    this.f = new ArrayList<>();
                }
                if (remoteInput != null) {
                    this.f.add(remoteInput);
                }
                return this;
            }

            @NonNull
            public Action build() {
                a();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList<RemoteInput> arrayList3 = this.f;
                if (arrayList3 != null) {
                    Iterator<RemoteInput> it = arrayList3.iterator();
                    while (it.hasNext()) {
                        RemoteInput next = it.next();
                        if (next.isDataOnly()) {
                            arrayList.add(next);
                        } else {
                            arrayList2.add(next);
                        }
                    }
                }
                RemoteInput[] remoteInputArr = arrayList.isEmpty() ? null : (RemoteInput[]) arrayList.toArray(new RemoteInput[arrayList.size()]);
                return new Action(this.a, this.b, this.f471c, this.e, arrayList2.isEmpty() ? null : (RemoteInput[]) arrayList2.toArray(new RemoteInput[arrayList2.size()]), remoteInputArr, this.f472d, this.g, this.h, this.f473i);
            }

            @NonNull
            public Builder extend(@NonNull Extender extender) {
                extender.extend(this);
                return this;
            }

            @NonNull
            public Bundle getExtras() {
                return this.e;
            }

            @NonNull
            public Builder setAllowGeneratedReplies(boolean z) {
                this.f472d = z;
                return this;
            }

            @NonNull
            public Builder setContextual(boolean z) {
                this.f473i = z;
                return this;
            }

            @NonNull
            public Builder setSemanticAction(int i2) {
                this.g = i2;
                return this;
            }

            @NonNull
            public Builder setShowsUserInterface(boolean z) {
                this.h = z;
                return this;
            }
        }

        /* loaded from: classes.dex */
        public interface Extender {
            @NonNull
            Builder extend(@NonNull Builder builder);
        }

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface SemanticAction {
        }

        /* loaded from: classes.dex */
        public static final class WearableExtender implements Extender {
            private int a;
            private CharSequence b;

            /* renamed from: c, reason: collision with root package name */
            private CharSequence f474c;

            /* renamed from: d, reason: collision with root package name */
            private CharSequence f475d;

            public WearableExtender() {
                this.a = 1;
            }

            public WearableExtender(@NonNull Action action) {
                this.a = 1;
                Bundle bundle = action.getExtras().getBundle("android.wearable.EXTENSIONS");
                if (bundle != null) {
                    this.a = bundle.getInt("flags", 1);
                    this.b = bundle.getCharSequence("inProgressLabel");
                    this.f474c = bundle.getCharSequence("confirmLabel");
                    this.f475d = bundle.getCharSequence("cancelLabel");
                }
            }

            private void a(int i2, boolean z) {
                int i3;
                if (z) {
                    i3 = i2 | this.a;
                } else {
                    i3 = (i2 ^ (-1)) & this.a;
                }
                this.a = i3;
            }

            @NonNull
            /* renamed from: clone, reason: merged with bridge method [inline-methods] */
            public WearableExtender m9clone() {
                WearableExtender wearableExtender = new WearableExtender();
                wearableExtender.a = this.a;
                wearableExtender.b = this.b;
                wearableExtender.f474c = this.f474c;
                wearableExtender.f475d = this.f475d;
                return wearableExtender;
            }

            @Override // androidx.core.app.NotificationCompat.Action.Extender
            @NonNull
            public Builder extend(@NonNull Builder builder) {
                Bundle bundle = new Bundle();
                int i2 = this.a;
                if (i2 != 1) {
                    bundle.putInt("flags", i2);
                }
                CharSequence charSequence = this.b;
                if (charSequence != null) {
                    bundle.putCharSequence("inProgressLabel", charSequence);
                }
                CharSequence charSequence2 = this.f474c;
                if (charSequence2 != null) {
                    bundle.putCharSequence("confirmLabel", charSequence2);
                }
                CharSequence charSequence3 = this.f475d;
                if (charSequence3 != null) {
                    bundle.putCharSequence("cancelLabel", charSequence3);
                }
                builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
                return builder;
            }

            @Nullable
            @Deprecated
            public CharSequence getCancelLabel() {
                return this.f475d;
            }

            @Nullable
            @Deprecated
            public CharSequence getConfirmLabel() {
                return this.f474c;
            }

            public boolean getHintDisplayActionInline() {
                return (this.a & 4) != 0;
            }

            public boolean getHintLaunchesActivity() {
                return (this.a & 2) != 0;
            }

            @Nullable
            @Deprecated
            public CharSequence getInProgressLabel() {
                return this.b;
            }

            public boolean isAvailableOffline() {
                return (this.a & 1) != 0;
            }

            @NonNull
            public WearableExtender setAvailableOffline(boolean z) {
                a(1, z);
                return this;
            }

            @NonNull
            @Deprecated
            public WearableExtender setCancelLabel(@Nullable CharSequence charSequence) {
                this.f475d = charSequence;
                return this;
            }

            @NonNull
            @Deprecated
            public WearableExtender setConfirmLabel(@Nullable CharSequence charSequence) {
                this.f474c = charSequence;
                return this;
            }

            @NonNull
            public WearableExtender setHintDisplayActionInline(boolean z) {
                a(4, z);
                return this;
            }

            @NonNull
            public WearableExtender setHintLaunchesActivity(boolean z) {
                a(2, z);
                return this;
            }

            @NonNull
            @Deprecated
            public WearableExtender setInProgressLabel(@Nullable CharSequence charSequence) {
                this.b = charSequence;
                return this;
            }
        }

        public Action(int i2, @Nullable CharSequence charSequence, @Nullable PendingIntent pendingIntent) {
            this(i2 != 0 ? IconCompat.createWithResource(null, "", i2) : null, charSequence, pendingIntent);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Action(int i2, @Nullable CharSequence charSequence, @Nullable PendingIntent pendingIntent, @Nullable Bundle bundle, @Nullable RemoteInput[] remoteInputArr, @Nullable RemoteInput[] remoteInputArr2, boolean z, int i3, boolean z2, boolean z3) {
            this(i2 != 0 ? IconCompat.createWithResource(null, "", i2) : null, charSequence, pendingIntent, bundle, remoteInputArr, remoteInputArr2, z, i3, z2, z3);
        }

        public Action(@Nullable IconCompat iconCompat, @Nullable CharSequence charSequence, @Nullable PendingIntent pendingIntent) {
            this(iconCompat, charSequence, pendingIntent, new Bundle(), (RemoteInput[]) null, (RemoteInput[]) null, true, 0, true, false);
        }

        Action(@Nullable IconCompat iconCompat, @Nullable CharSequence charSequence, @Nullable PendingIntent pendingIntent, @Nullable Bundle bundle, @Nullable RemoteInput[] remoteInputArr, @Nullable RemoteInput[] remoteInputArr2, boolean z, int i2, boolean z2, boolean z3) {
            this.f = true;
            this.b = iconCompat;
            if (iconCompat != null && iconCompat.getType() == 2) {
                this.icon = iconCompat.getResId();
            }
            this.title = Builder.b(charSequence);
            this.actionIntent = pendingIntent;
            this.a = bundle == null ? new Bundle() : bundle;
            this.f469c = remoteInputArr;
            this.f470d = remoteInputArr2;
            this.e = z;
            this.g = i2;
            this.f = z2;
            this.h = z3;
        }

        @Nullable
        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public boolean getAllowGeneratedReplies() {
            return this.e;
        }

        @Nullable
        public RemoteInput[] getDataOnlyRemoteInputs() {
            return this.f470d;
        }

        @NonNull
        public Bundle getExtras() {
            return this.a;
        }

        @Deprecated
        public int getIcon() {
            return this.icon;
        }

        @Nullable
        public IconCompat getIconCompat() {
            int i2;
            if (this.b == null && (i2 = this.icon) != 0) {
                this.b = IconCompat.createWithResource(null, "", i2);
            }
            return this.b;
        }

        @Nullable
        public RemoteInput[] getRemoteInputs() {
            return this.f469c;
        }

        public int getSemanticAction() {
            return this.g;
        }

        public boolean getShowsUserInterface() {
            return this.f;
        }

        @Nullable
        public CharSequence getTitle() {
            return this.title;
        }

        public boolean isContextual() {
            return this.h;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface BadgeIconType {
    }

    /* loaded from: classes.dex */
    public static class BigPictureStyle extends Style {
        private Bitmap e;
        private IconCompat f;
        private boolean g;

        @RequiresApi(16)
        /* loaded from: classes.dex */
        private static class a {
            @RequiresApi(16)
            static void a(Notification.BigPictureStyle bigPictureStyle, Bitmap bitmap) {
                bigPictureStyle.bigLargeIcon(bitmap);
            }

            @RequiresApi(16)
            static void b(Notification.BigPictureStyle bigPictureStyle, CharSequence charSequence) {
                bigPictureStyle.setSummaryText(charSequence);
            }
        }

        @RequiresApi(23)
        /* loaded from: classes.dex */
        private static class b {
            @RequiresApi(23)
            static void a(Notification.BigPictureStyle bigPictureStyle, Icon icon) {
                bigPictureStyle.bigLargeIcon(icon);
            }
        }

        public BigPictureStyle() {
        }

        public BigPictureStyle(@Nullable Builder builder) {
            setBuilder(builder);
        }

        @Nullable
        private static IconCompat o(@Nullable Parcelable parcelable) {
            if (parcelable == null) {
                return null;
            }
            if (Build.VERSION.SDK_INT >= 23 && (parcelable instanceof Icon)) {
                return IconCompat.createFromIcon((Icon) parcelable);
            }
            if (parcelable instanceof Bitmap) {
                return IconCompat.createWithBitmap((Bitmap) parcelable);
            }
            return null;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 16) {
                Notification.BigPictureStyle bigPicture = new Notification.BigPictureStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.b).bigPicture(this.e);
                if (this.g) {
                    IconCompat iconCompat = this.f;
                    if (iconCompat != null) {
                        if (i2 >= 23) {
                            b.a(bigPicture, this.f.toIcon(notificationBuilderWithBuilderAccessor instanceof androidx.core.app.b ? ((androidx.core.app.b) notificationBuilderWithBuilderAccessor).e() : null));
                        } else if (iconCompat.getType() == 1) {
                            a.a(bigPicture, this.f.getBitmap());
                        }
                    }
                    a.a(bigPicture, null);
                }
                if (this.f492d) {
                    a.b(bigPicture, this.f491c);
                }
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected void b(@NonNull Bundle bundle) {
            super.b(bundle);
            bundle.remove("android.largeIcon.big");
            bundle.remove("android.picture");
        }

        @NonNull
        public BigPictureStyle bigLargeIcon(@Nullable Bitmap bitmap) {
            this.f = bitmap == null ? null : IconCompat.createWithBitmap(bitmap);
            this.g = true;
            return this;
        }

        @NonNull
        public BigPictureStyle bigPicture(@Nullable Bitmap bitmap) {
            this.e = bitmap;
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected String l() {
            return "androidx.core.app.NotificationCompat$BigPictureStyle";
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected void n(@NonNull Bundle bundle) {
            super.n(bundle);
            if (bundle.containsKey("android.largeIcon.big")) {
                this.f = o(bundle.getParcelable("android.largeIcon.big"));
                this.g = true;
            }
            this.e = (Bitmap) bundle.getParcelable("android.picture");
        }

        @NonNull
        public BigPictureStyle setBigContentTitle(@Nullable CharSequence charSequence) {
            this.b = Builder.b(charSequence);
            return this;
        }

        @NonNull
        public BigPictureStyle setSummaryText(@Nullable CharSequence charSequence) {
            this.f491c = Builder.b(charSequence);
            this.f492d = true;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class BigTextStyle extends Style {
        private CharSequence e;

        public BigTextStyle() {
        }

        public BigTextStyle(@Nullable Builder builder) {
            setBuilder(builder);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void addCompatExtras(@NonNull Bundle bundle) {
            super.addCompatExtras(bundle);
            if (Build.VERSION.SDK_INT < 21) {
                bundle.putCharSequence("android.bigText", this.e);
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 16) {
                Notification.BigTextStyle bigText = new Notification.BigTextStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.b).bigText(this.e);
                if (this.f492d) {
                    bigText.setSummaryText(this.f491c);
                }
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected void b(@NonNull Bundle bundle) {
            super.b(bundle);
            bundle.remove("android.bigText");
        }

        @NonNull
        public BigTextStyle bigText(@Nullable CharSequence charSequence) {
            this.e = Builder.b(charSequence);
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected String l() {
            return "androidx.core.app.NotificationCompat$BigTextStyle";
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected void n(@NonNull Bundle bundle) {
            super.n(bundle);
            this.e = bundle.getCharSequence("android.bigText");
        }

        @NonNull
        public BigTextStyle setBigContentTitle(@Nullable CharSequence charSequence) {
            this.b = Builder.b(charSequence);
            return this;
        }

        @NonNull
        public BigTextStyle setSummaryText(@Nullable CharSequence charSequence) {
            this.f491c = Builder.b(charSequence);
            this.f492d = true;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class BubbleMetadata {
        private PendingIntent a;
        private PendingIntent b;

        /* renamed from: c, reason: collision with root package name */
        private IconCompat f476c;

        /* renamed from: d, reason: collision with root package name */
        private int f477d;

        @DimenRes
        private int e;
        private int f;
        private String g;

        /* loaded from: classes.dex */
        public static final class Builder {
            private PendingIntent a;
            private IconCompat b;

            /* renamed from: c, reason: collision with root package name */
            private int f478c;

            /* renamed from: d, reason: collision with root package name */
            @DimenRes
            private int f479d;
            private int e;
            private PendingIntent f;
            private String g;

            @Deprecated
            public Builder() {
            }

            public Builder(@NonNull PendingIntent pendingIntent, @NonNull IconCompat iconCompat) {
                if (pendingIntent == null) {
                    throw new NullPointerException("Bubble requires non-null pending intent");
                }
                if (iconCompat == null) {
                    throw new NullPointerException("Bubbles require non-null icon");
                }
                this.a = pendingIntent;
                this.b = iconCompat;
            }

            @RequiresApi(30)
            public Builder(@NonNull String str) {
                if (TextUtils.isEmpty(str)) {
                    throw new NullPointerException("Bubble requires a non-null shortcut id");
                }
                this.g = str;
            }

            @NonNull
            private Builder a(int i2, boolean z) {
                int i3;
                if (z) {
                    i3 = i2 | this.e;
                } else {
                    i3 = (i2 ^ (-1)) & this.e;
                }
                this.e = i3;
                return this;
            }

            @NonNull
            @SuppressLint({"SyntheticAccessor"})
            public BubbleMetadata build() {
                String str = this.g;
                if (str == null && this.a == null) {
                    throw new NullPointerException("Must supply pending intent or shortcut to bubble");
                }
                if (str == null && this.b == null) {
                    throw new NullPointerException("Must supply an icon or shortcut for the bubble");
                }
                PendingIntent pendingIntent = this.a;
                PendingIntent pendingIntent2 = this.f;
                IconCompat iconCompat = this.b;
                int i2 = this.f478c;
                int i3 = this.f479d;
                int i4 = this.e;
                BubbleMetadata bubbleMetadata = new BubbleMetadata(pendingIntent, pendingIntent2, iconCompat, i2, i3, i4, str);
                bubbleMetadata.setFlags(i4);
                return bubbleMetadata;
            }

            @NonNull
            public Builder setAutoExpandBubble(boolean z) {
                a(1, z);
                return this;
            }

            @NonNull
            public Builder setDeleteIntent(@Nullable PendingIntent pendingIntent) {
                this.f = pendingIntent;
                return this;
            }

            @NonNull
            public Builder setDesiredHeight(@Dimension(unit = 0) int i2) {
                this.f478c = Math.max(i2, 0);
                this.f479d = 0;
                return this;
            }

            @NonNull
            public Builder setDesiredHeightResId(@DimenRes int i2) {
                this.f479d = i2;
                this.f478c = 0;
                return this;
            }

            @NonNull
            public Builder setIcon(@NonNull IconCompat iconCompat) {
                if (this.g != null) {
                    throw new IllegalStateException("Created as a shortcut bubble, cannot set an Icon. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
                }
                if (iconCompat == null) {
                    throw new NullPointerException("Bubbles require non-null icon");
                }
                this.b = iconCompat;
                return this;
            }

            @NonNull
            public Builder setIntent(@NonNull PendingIntent pendingIntent) {
                if (this.g != null) {
                    throw new IllegalStateException("Created as a shortcut bubble, cannot set a PendingIntent. Consider using BubbleMetadata.Builder(PendingIntent,Icon) instead.");
                }
                if (pendingIntent == null) {
                    throw new NullPointerException("Bubble requires non-null pending intent");
                }
                this.a = pendingIntent;
                return this;
            }

            @NonNull
            public Builder setSuppressNotification(boolean z) {
                a(2, z);
                return this;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @RequiresApi(29)
        /* loaded from: classes.dex */
        public static class a {
            @Nullable
            @RequiresApi(29)
            static BubbleMetadata a(@Nullable Notification.BubbleMetadata bubbleMetadata) {
                if (bubbleMetadata == null || bubbleMetadata.getIntent() == null) {
                    return null;
                }
                Builder suppressNotification = new Builder(bubbleMetadata.getIntent(), IconCompat.createFromIcon(bubbleMetadata.getIcon())).setAutoExpandBubble(bubbleMetadata.getAutoExpandBubble()).setDeleteIntent(bubbleMetadata.getDeleteIntent()).setSuppressNotification(bubbleMetadata.isNotificationSuppressed());
                if (bubbleMetadata.getDesiredHeight() != 0) {
                    suppressNotification.setDesiredHeight(bubbleMetadata.getDesiredHeight());
                }
                if (bubbleMetadata.getDesiredHeightResId() != 0) {
                    suppressNotification.setDesiredHeightResId(bubbleMetadata.getDesiredHeightResId());
                }
                return suppressNotification.build();
            }

            @Nullable
            @RequiresApi(29)
            static Notification.BubbleMetadata b(@Nullable BubbleMetadata bubbleMetadata) {
                if (bubbleMetadata == null || bubbleMetadata.getIntent() == null) {
                    return null;
                }
                Notification.BubbleMetadata.Builder suppressNotification = new Notification.BubbleMetadata.Builder().setIcon(bubbleMetadata.getIcon().toIcon()).setIntent(bubbleMetadata.getIntent()).setDeleteIntent(bubbleMetadata.getDeleteIntent()).setAutoExpandBubble(bubbleMetadata.getAutoExpandBubble()).setSuppressNotification(bubbleMetadata.isNotificationSuppressed());
                if (bubbleMetadata.getDesiredHeight() != 0) {
                    suppressNotification.setDesiredHeight(bubbleMetadata.getDesiredHeight());
                }
                if (bubbleMetadata.getDesiredHeightResId() != 0) {
                    suppressNotification.setDesiredHeightResId(bubbleMetadata.getDesiredHeightResId());
                }
                return suppressNotification.build();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @RequiresApi(30)
        /* loaded from: classes.dex */
        public static class b {
            @Nullable
            @RequiresApi(30)
            static BubbleMetadata a(@Nullable Notification.BubbleMetadata bubbleMetadata) {
                if (bubbleMetadata == null) {
                    return null;
                }
                Builder builder = bubbleMetadata.getShortcutId() != null ? new Builder(bubbleMetadata.getShortcutId()) : new Builder(bubbleMetadata.getIntent(), IconCompat.createFromIcon(bubbleMetadata.getIcon()));
                builder.setAutoExpandBubble(bubbleMetadata.getAutoExpandBubble()).setDeleteIntent(bubbleMetadata.getDeleteIntent()).setSuppressNotification(bubbleMetadata.isNotificationSuppressed());
                if (bubbleMetadata.getDesiredHeight() != 0) {
                    builder.setDesiredHeight(bubbleMetadata.getDesiredHeight());
                }
                if (bubbleMetadata.getDesiredHeightResId() != 0) {
                    builder.setDesiredHeightResId(bubbleMetadata.getDesiredHeightResId());
                }
                return builder.build();
            }

            @Nullable
            @RequiresApi(30)
            static Notification.BubbleMetadata b(@Nullable BubbleMetadata bubbleMetadata) {
                if (bubbleMetadata == null) {
                    return null;
                }
                Notification.BubbleMetadata.Builder builder = bubbleMetadata.getShortcutId() != null ? new Notification.BubbleMetadata.Builder(bubbleMetadata.getShortcutId()) : new Notification.BubbleMetadata.Builder(bubbleMetadata.getIntent(), bubbleMetadata.getIcon().toIcon());
                builder.setDeleteIntent(bubbleMetadata.getDeleteIntent()).setAutoExpandBubble(bubbleMetadata.getAutoExpandBubble()).setSuppressNotification(bubbleMetadata.isNotificationSuppressed());
                if (bubbleMetadata.getDesiredHeight() != 0) {
                    builder.setDesiredHeight(bubbleMetadata.getDesiredHeight());
                }
                if (bubbleMetadata.getDesiredHeightResId() != 0) {
                    builder.setDesiredHeightResId(bubbleMetadata.getDesiredHeightResId());
                }
                return builder.build();
            }
        }

        private BubbleMetadata(@Nullable PendingIntent pendingIntent, @Nullable PendingIntent pendingIntent2, @Nullable IconCompat iconCompat, int i2, @DimenRes int i3, int i4, @Nullable String str) {
            this.a = pendingIntent;
            this.f476c = iconCompat;
            this.f477d = i2;
            this.e = i3;
            this.b = pendingIntent2;
            this.f = i4;
            this.g = str;
        }

        @Nullable
        public static BubbleMetadata fromPlatform(@Nullable Notification.BubbleMetadata bubbleMetadata) {
            if (bubbleMetadata == null) {
                return null;
            }
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 30) {
                return b.a(bubbleMetadata);
            }
            if (i2 == 29) {
                return a.a(bubbleMetadata);
            }
            return null;
        }

        @Nullable
        public static Notification.BubbleMetadata toPlatform(@Nullable BubbleMetadata bubbleMetadata) {
            if (bubbleMetadata == null) {
                return null;
            }
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 30) {
                return b.b(bubbleMetadata);
            }
            if (i2 == 29) {
                return a.b(bubbleMetadata);
            }
            return null;
        }

        public boolean getAutoExpandBubble() {
            return (this.f & 1) != 0;
        }

        @Nullable
        public PendingIntent getDeleteIntent() {
            return this.b;
        }

        @Dimension(unit = 0)
        public int getDesiredHeight() {
            return this.f477d;
        }

        @DimenRes
        public int getDesiredHeightResId() {
            return this.e;
        }

        @Nullable
        @SuppressLint({"InvalidNullConversion"})
        public IconCompat getIcon() {
            return this.f476c;
        }

        @Nullable
        @SuppressLint({"InvalidNullConversion"})
        public PendingIntent getIntent() {
            return this.a;
        }

        @Nullable
        public String getShortcutId() {
            return this.g;
        }

        public boolean isNotificationSuppressed() {
            return (this.f & 2) != 0;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void setFlags(int i2) {
            this.f = i2;
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        String A;
        Bundle B;
        int C;
        int D;
        Notification E;
        RemoteViews F;
        RemoteViews G;
        RemoteViews H;
        String I;
        int J;
        String K;
        LocusIdCompat L;
        long M;
        int N;
        boolean O;
        BubbleMetadata P;
        Notification Q;
        boolean R;
        Icon S;
        ArrayList<Action> a;
        CharSequence b;

        /* renamed from: c, reason: collision with root package name */
        CharSequence f480c;

        /* renamed from: d, reason: collision with root package name */
        PendingIntent f481d;
        PendingIntent e;
        RemoteViews f;
        Bitmap g;
        CharSequence h;

        /* renamed from: i, reason: collision with root package name */
        int f482i;
        int j;
        boolean k;
        boolean l;
        boolean m;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public ArrayList<Action> mActions;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Context mContext;

        @Deprecated
        public ArrayList<String> mPeople;

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public ArrayList<Person> mPersonList;
        Style n;
        CharSequence o;
        CharSequence p;
        CharSequence[] q;
        int r;
        int s;
        boolean t;
        String u;
        boolean v;
        String w;
        boolean x;
        boolean y;
        boolean z;

        @Deprecated
        public Builder(@NonNull Context context) {
            this(context, (String) null);
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @RequiresApi(19)
        public Builder(@NonNull Context context, @NonNull Notification notification) {
            this(context, NotificationCompat.getChannelId(notification));
            ArrayList parcelableArrayList;
            int i2 = Build.VERSION.SDK_INT;
            Bundle bundle = notification.extras;
            Style extractStyleFromNotification = Style.extractStyleFromNotification(notification);
            setContentTitle(NotificationCompat.getContentTitle(notification)).setContentText(NotificationCompat.getContentText(notification)).setContentInfo(NotificationCompat.getContentInfo(notification)).setSubText(NotificationCompat.getSubText(notification)).setSettingsText(NotificationCompat.getSettingsText(notification)).setStyle(extractStyleFromNotification).setContentIntent(notification.contentIntent).setGroup(NotificationCompat.getGroup(notification)).setGroupSummary(NotificationCompat.isGroupSummary(notification)).setLocusId(NotificationCompat.getLocusId(notification)).setWhen(notification.when).setShowWhen(NotificationCompat.getShowWhen(notification)).setUsesChronometer(NotificationCompat.getUsesChronometer(notification)).setAutoCancel(NotificationCompat.getAutoCancel(notification)).setOnlyAlertOnce(NotificationCompat.getOnlyAlertOnce(notification)).setOngoing(NotificationCompat.getOngoing(notification)).setLocalOnly(NotificationCompat.getLocalOnly(notification)).setLargeIcon(notification.largeIcon).setBadgeIconType(NotificationCompat.getBadgeIconType(notification)).setCategory(NotificationCompat.getCategory(notification)).setBubbleMetadata(NotificationCompat.getBubbleMetadata(notification)).setNumber(notification.number).setTicker(notification.tickerText).setContentIntent(notification.contentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(notification.fullScreenIntent, NotificationCompat.b(notification)).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setDefaults(notification.defaults).setPriority(notification.priority).setColor(NotificationCompat.getColor(notification)).setVisibility(NotificationCompat.getVisibility(notification)).setPublicVersion(NotificationCompat.getPublicVersion(notification)).setSortKey(NotificationCompat.getSortKey(notification)).setTimeoutAfter(NotificationCompat.getTimeoutAfter(notification)).setShortcutId(NotificationCompat.getShortcutId(notification)).setProgress(bundle.getInt("android.progressMax"), bundle.getInt("android.progress"), bundle.getBoolean("android.progressIndeterminate")).setAllowSystemGeneratedContextualActions(NotificationCompat.getAllowSystemGeneratedContextualActions(notification)).setSmallIcon(notification.icon, notification.iconLevel).addExtras(a(notification, extractStyleFromNotification));
            if (i2 >= 23) {
                this.S = notification.getSmallIcon();
            }
            Notification.Action[] actionArr = notification.actions;
            if (actionArr != null && actionArr.length != 0) {
                for (Notification.Action action : actionArr) {
                    addAction(Action.Builder.fromAndroidAction(action).build());
                }
            }
            if (i2 >= 21) {
                List<Action> invisibleActions = NotificationCompat.getInvisibleActions(notification);
                if (!invisibleActions.isEmpty()) {
                    Iterator<Action> it = invisibleActions.iterator();
                    while (it.hasNext()) {
                        addInvisibleAction(it.next());
                    }
                }
            }
            String[] stringArray = notification.extras.getStringArray("android.people");
            if (stringArray != null && stringArray.length != 0) {
                for (String str : stringArray) {
                    addPerson(str);
                }
            }
            if (i2 >= 28 && (parcelableArrayList = notification.extras.getParcelableArrayList(NotificationCompat.EXTRA_PEOPLE_LIST)) != null && !parcelableArrayList.isEmpty()) {
                Iterator it2 = parcelableArrayList.iterator();
                while (it2.hasNext()) {
                    addPerson(Person.fromAndroidPerson((android.app.Person) it2.next()));
                }
            }
            if (i2 >= 24 && bundle.containsKey(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN)) {
                setChronometerCountDown(bundle.getBoolean(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN));
            }
            if (i2 < 26 || !bundle.containsKey(NotificationCompat.EXTRA_COLORIZED)) {
                return;
            }
            setColorized(bundle.getBoolean(NotificationCompat.EXTRA_COLORIZED));
        }

        public Builder(@NonNull Context context, @NonNull String str) {
            this.mActions = new ArrayList<>();
            this.mPersonList = new ArrayList<>();
            this.a = new ArrayList<>();
            this.k = true;
            this.x = false;
            this.C = 0;
            this.D = 0;
            this.J = 0;
            this.N = 0;
            Notification notification = new Notification();
            this.Q = notification;
            this.mContext = context;
            this.I = str;
            notification.when = System.currentTimeMillis();
            this.Q.audioStreamType = -1;
            this.j = 0;
            this.mPeople = new ArrayList<>();
            this.O = true;
        }

        @Nullable
        @RequiresApi(19)
        private static Bundle a(@NonNull Notification notification, @Nullable Style style) {
            if (notification.extras == null) {
                return null;
            }
            Bundle bundle = new Bundle(notification.extras);
            bundle.remove("android.title");
            bundle.remove("android.text");
            bundle.remove("android.infoText");
            bundle.remove("android.subText");
            bundle.remove(NotificationCompat.EXTRA_CHANNEL_ID);
            bundle.remove(NotificationCompat.EXTRA_CHANNEL_GROUP_ID);
            bundle.remove("android.showWhen");
            bundle.remove("android.progress");
            bundle.remove("android.progressMax");
            bundle.remove("android.progressIndeterminate");
            bundle.remove(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN);
            bundle.remove(NotificationCompat.EXTRA_COLORIZED);
            bundle.remove(NotificationCompat.EXTRA_PEOPLE_LIST);
            bundle.remove("android.people");
            bundle.remove("android.support.sortKey");
            bundle.remove("android.support.groupKey");
            bundle.remove("android.support.isGroupSummary");
            bundle.remove("android.support.localOnly");
            bundle.remove("android.support.actionExtras");
            Bundle bundle2 = bundle.getBundle("android.car.EXTENSIONS");
            if (bundle2 != null) {
                Bundle bundle3 = new Bundle(bundle2);
                bundle3.remove("invisible_actions");
                bundle.putBundle("android.car.EXTENSIONS", bundle3);
            }
            if (style != null) {
                style.b(bundle);
            }
            return bundle;
        }

        @Nullable
        protected static CharSequence b(@Nullable CharSequence charSequence) {
            return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
        }

        @Nullable
        private Bitmap c(@Nullable Bitmap bitmap) {
            if (bitmap == null || Build.VERSION.SDK_INT >= 27) {
                return bitmap;
            }
            Resources resources = this.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_width);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.compat_notification_large_icon_max_height);
            if (bitmap.getWidth() <= dimensionPixelSize && bitmap.getHeight() <= dimensionPixelSize2) {
                return bitmap;
            }
            double d2 = dimensionPixelSize;
            double max = Math.max(1, bitmap.getWidth());
            Double.isNaN(d2);
            Double.isNaN(max);
            double d3 = d2 / max;
            double d4 = dimensionPixelSize2;
            double max2 = Math.max(1, bitmap.getHeight());
            Double.isNaN(d4);
            Double.isNaN(max2);
            double min = Math.min(d3, d4 / max2);
            double width = bitmap.getWidth();
            Double.isNaN(width);
            int ceil = (int) Math.ceil(width * min);
            double height = bitmap.getHeight();
            Double.isNaN(height);
            return Bitmap.createScaledBitmap(bitmap, ceil, (int) Math.ceil(height * min), true);
        }

        private void d(int i2, boolean z) {
            Notification notification;
            int i3;
            if (z) {
                notification = this.Q;
                i3 = i2 | notification.flags;
            } else {
                notification = this.Q;
                i3 = (i2 ^ (-1)) & notification.flags;
            }
            notification.flags = i3;
        }

        private boolean e() {
            Style style = this.n;
            return style == null || !style.displayCustomViewInline();
        }

        @NonNull
        public Builder addAction(int i2, @Nullable CharSequence charSequence, @Nullable PendingIntent pendingIntent) {
            this.mActions.add(new Action(i2, charSequence, pendingIntent));
            return this;
        }

        @NonNull
        public Builder addAction(@Nullable Action action) {
            if (action != null) {
                this.mActions.add(action);
            }
            return this;
        }

        @NonNull
        public Builder addExtras(@Nullable Bundle bundle) {
            if (bundle != null) {
                Bundle bundle2 = this.B;
                if (bundle2 == null) {
                    this.B = new Bundle(bundle);
                } else {
                    bundle2.putAll(bundle);
                }
            }
            return this;
        }

        @NonNull
        @RequiresApi(21)
        public Builder addInvisibleAction(int i2, @Nullable CharSequence charSequence, @Nullable PendingIntent pendingIntent) {
            this.a.add(new Action(i2, charSequence, pendingIntent));
            return this;
        }

        @NonNull
        @RequiresApi(21)
        public Builder addInvisibleAction(@Nullable Action action) {
            if (action != null) {
                this.a.add(action);
            }
            return this;
        }

        @NonNull
        public Builder addPerson(@Nullable Person person) {
            if (person != null) {
                this.mPersonList.add(person);
            }
            return this;
        }

        @NonNull
        @Deprecated
        public Builder addPerson(@Nullable String str) {
            if (str != null && !str.isEmpty()) {
                this.mPeople.add(str);
            }
            return this;
        }

        @NonNull
        public Notification build() {
            return new b(this).b();
        }

        @NonNull
        public Builder clearActions() {
            this.mActions.clear();
            return this;
        }

        @NonNull
        public Builder clearInvisibleActions() {
            this.a.clear();
            Bundle bundle = this.B.getBundle("android.car.EXTENSIONS");
            if (bundle != null) {
                Bundle bundle2 = new Bundle(bundle);
                bundle2.remove("invisible_actions");
                this.B.putBundle("android.car.EXTENSIONS", bundle2);
            }
            return this;
        }

        @NonNull
        public Builder clearPeople() {
            this.mPersonList.clear();
            this.mPeople.clear();
            return this;
        }

        @Nullable
        @SuppressLint({"BuilderSetStyle"})
        public RemoteViews createBigContentView() {
            RemoteViews makeBigContentView;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 16) {
                return null;
            }
            if (this.G != null && e()) {
                return this.G;
            }
            b bVar = new b(this);
            Style style = this.n;
            if (style != null && (makeBigContentView = style.makeBigContentView(bVar)) != null) {
                return makeBigContentView;
            }
            Notification b = bVar.b();
            return i2 >= 24 ? Notification.Builder.recoverBuilder(this.mContext, b).createBigContentView() : b.bigContentView;
        }

        @Nullable
        @SuppressLint({"BuilderSetStyle"})
        public RemoteViews createContentView() {
            RemoteViews makeContentView;
            if (this.F != null && e()) {
                return this.F;
            }
            b bVar = new b(this);
            Style style = this.n;
            if (style != null && (makeContentView = style.makeContentView(bVar)) != null) {
                return makeContentView;
            }
            Notification b = bVar.b();
            return Build.VERSION.SDK_INT >= 24 ? Notification.Builder.recoverBuilder(this.mContext, b).createContentView() : b.contentView;
        }

        @Nullable
        @SuppressLint({"BuilderSetStyle"})
        public RemoteViews createHeadsUpContentView() {
            RemoteViews makeHeadsUpContentView;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 21) {
                return null;
            }
            if (this.H != null && e()) {
                return this.H;
            }
            b bVar = new b(this);
            Style style = this.n;
            if (style != null && (makeHeadsUpContentView = style.makeHeadsUpContentView(bVar)) != null) {
                return makeHeadsUpContentView;
            }
            Notification b = bVar.b();
            return i2 >= 24 ? Notification.Builder.recoverBuilder(this.mContext, b).createHeadsUpContentView() : b.headsUpContentView;
        }

        @NonNull
        public Builder extend(@NonNull Extender extender) {
            extender.extend(this);
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews getBigContentView() {
            return this.G;
        }

        @Nullable
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public BubbleMetadata getBubbleMetadata() {
            return this.P;
        }

        @ColorInt
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public int getColor() {
            return this.C;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews getContentView() {
            return this.F;
        }

        @NonNull
        public Bundle getExtras() {
            if (this.B == null) {
                this.B = new Bundle();
            }
            return this.B;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews getHeadsUpContentView() {
            return this.H;
        }

        @NonNull
        @Deprecated
        public Notification getNotification() {
            return build();
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public int getPriority() {
            return this.j;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public long getWhenIfShowing() {
            if (this.k) {
                return this.Q.when;
            }
            return 0L;
        }

        @NonNull
        public Builder setAllowSystemGeneratedContextualActions(boolean z) {
            this.O = z;
            return this;
        }

        @NonNull
        public Builder setAutoCancel(boolean z) {
            d(16, z);
            return this;
        }

        @NonNull
        public Builder setBadgeIconType(int i2) {
            this.J = i2;
            return this;
        }

        @NonNull
        public Builder setBubbleMetadata(@Nullable BubbleMetadata bubbleMetadata) {
            this.P = bubbleMetadata;
            return this;
        }

        @NonNull
        public Builder setCategory(@Nullable String str) {
            this.A = str;
            return this;
        }

        @NonNull
        public Builder setChannelId(@NonNull String str) {
            this.I = str;
            return this;
        }

        @NonNull
        @RequiresApi(24)
        public Builder setChronometerCountDown(boolean z) {
            this.m = z;
            getExtras().putBoolean(NotificationCompat.EXTRA_CHRONOMETER_COUNT_DOWN, z);
            return this;
        }

        @NonNull
        public Builder setColor(@ColorInt int i2) {
            this.C = i2;
            return this;
        }

        @NonNull
        public Builder setColorized(boolean z) {
            this.y = z;
            this.z = true;
            return this;
        }

        @NonNull
        public Builder setContent(@Nullable RemoteViews remoteViews) {
            this.Q.contentView = remoteViews;
            return this;
        }

        @NonNull
        public Builder setContentInfo(@Nullable CharSequence charSequence) {
            this.h = b(charSequence);
            return this;
        }

        @NonNull
        public Builder setContentIntent(@Nullable PendingIntent pendingIntent) {
            this.f481d = pendingIntent;
            return this;
        }

        @NonNull
        public Builder setContentText(@Nullable CharSequence charSequence) {
            this.f480c = b(charSequence);
            return this;
        }

        @NonNull
        public Builder setContentTitle(@Nullable CharSequence charSequence) {
            this.b = b(charSequence);
            return this;
        }

        @NonNull
        public Builder setCustomBigContentView(@Nullable RemoteViews remoteViews) {
            this.G = remoteViews;
            return this;
        }

        @NonNull
        public Builder setCustomContentView(@Nullable RemoteViews remoteViews) {
            this.F = remoteViews;
            return this;
        }

        @NonNull
        public Builder setCustomHeadsUpContentView(@Nullable RemoteViews remoteViews) {
            this.H = remoteViews;
            return this;
        }

        @NonNull
        public Builder setDefaults(int i2) {
            Notification notification = this.Q;
            notification.defaults = i2;
            if ((i2 & 4) != 0) {
                notification.flags |= 1;
            }
            return this;
        }

        @NonNull
        public Builder setDeleteIntent(@Nullable PendingIntent pendingIntent) {
            this.Q.deleteIntent = pendingIntent;
            return this;
        }

        @NonNull
        public Builder setExtras(@Nullable Bundle bundle) {
            this.B = bundle;
            return this;
        }

        @NonNull
        public Builder setFullScreenIntent(@Nullable PendingIntent pendingIntent, boolean z) {
            this.e = pendingIntent;
            d(128, z);
            return this;
        }

        @NonNull
        public Builder setGroup(@Nullable String str) {
            this.u = str;
            return this;
        }

        @NonNull
        public Builder setGroupAlertBehavior(int i2) {
            this.N = i2;
            return this;
        }

        @NonNull
        public Builder setGroupSummary(boolean z) {
            this.v = z;
            return this;
        }

        @NonNull
        public Builder setLargeIcon(@Nullable Bitmap bitmap) {
            this.g = c(bitmap);
            return this;
        }

        @NonNull
        public Builder setLights(@ColorInt int i2, int i3, int i4) {
            Notification notification = this.Q;
            notification.ledARGB = i2;
            notification.ledOnMS = i3;
            notification.ledOffMS = i4;
            notification.flags = ((i3 == 0 || i4 == 0) ? 0 : 1) | (notification.flags & (-2));
            return this;
        }

        @NonNull
        public Builder setLocalOnly(boolean z) {
            this.x = z;
            return this;
        }

        @NonNull
        public Builder setLocusId(@Nullable LocusIdCompat locusIdCompat) {
            this.L = locusIdCompat;
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setNotificationSilent() {
            this.R = true;
            return this;
        }

        @NonNull
        public Builder setNumber(int i2) {
            this.f482i = i2;
            return this;
        }

        @NonNull
        public Builder setOngoing(boolean z) {
            d(2, z);
            return this;
        }

        @NonNull
        public Builder setOnlyAlertOnce(boolean z) {
            d(8, z);
            return this;
        }

        @NonNull
        public Builder setPriority(int i2) {
            this.j = i2;
            return this;
        }

        @NonNull
        public Builder setProgress(int i2, int i3, boolean z) {
            this.r = i2;
            this.s = i3;
            this.t = z;
            return this;
        }

        @NonNull
        public Builder setPublicVersion(@Nullable Notification notification) {
            this.E = notification;
            return this;
        }

        @NonNull
        public Builder setRemoteInputHistory(@Nullable CharSequence[] charSequenceArr) {
            this.q = charSequenceArr;
            return this;
        }

        @NonNull
        public Builder setSettingsText(@Nullable CharSequence charSequence) {
            this.p = b(charSequence);
            return this;
        }

        @NonNull
        public Builder setShortcutId(@Nullable String str) {
            this.K = str;
            return this;
        }

        @NonNull
        public Builder setShortcutInfo(@Nullable ShortcutInfoCompat shortcutInfoCompat) {
            LocusIdCompat locusIdCompat;
            if (shortcutInfoCompat == null) {
                return this;
            }
            this.K = shortcutInfoCompat.getId();
            if (this.L == null) {
                if (shortcutInfoCompat.getLocusId() != null) {
                    locusIdCompat = shortcutInfoCompat.getLocusId();
                } else if (shortcutInfoCompat.getId() != null) {
                    locusIdCompat = new LocusIdCompat(shortcutInfoCompat.getId());
                }
                this.L = locusIdCompat;
            }
            if (this.b == null) {
                setContentTitle(shortcutInfoCompat.getShortLabel());
            }
            return this;
        }

        @NonNull
        public Builder setShowWhen(boolean z) {
            this.k = z;
            return this;
        }

        @NonNull
        public Builder setSilent(boolean z) {
            this.R = z;
            return this;
        }

        @NonNull
        public Builder setSmallIcon(int i2) {
            this.Q.icon = i2;
            return this;
        }

        @NonNull
        public Builder setSmallIcon(int i2, int i3) {
            Notification notification = this.Q;
            notification.icon = i2;
            notification.iconLevel = i3;
            return this;
        }

        @NonNull
        @RequiresApi(23)
        public Builder setSmallIcon(@NonNull IconCompat iconCompat) {
            this.S = iconCompat.toIcon(this.mContext);
            return this;
        }

        @NonNull
        public Builder setSortKey(@Nullable String str) {
            this.w = str;
            return this;
        }

        @NonNull
        public Builder setSound(@Nullable Uri uri) {
            Notification notification = this.Q;
            notification.sound = uri;
            notification.audioStreamType = -1;
            if (Build.VERSION.SDK_INT >= 21) {
                notification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setUsage(5).build();
            }
            return this;
        }

        @NonNull
        public Builder setSound(@Nullable Uri uri, int i2) {
            Notification notification = this.Q;
            notification.sound = uri;
            notification.audioStreamType = i2;
            if (Build.VERSION.SDK_INT >= 21) {
                notification.audioAttributes = new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(i2).build();
            }
            return this;
        }

        @NonNull
        public Builder setStyle(@Nullable Style style) {
            if (this.n != style) {
                this.n = style;
                if (style != null) {
                    style.setBuilder(this);
                }
            }
            return this;
        }

        @NonNull
        public Builder setSubText(@Nullable CharSequence charSequence) {
            this.o = b(charSequence);
            return this;
        }

        @NonNull
        public Builder setTicker(@Nullable CharSequence charSequence) {
            this.Q.tickerText = b(charSequence);
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setTicker(@Nullable CharSequence charSequence, @Nullable RemoteViews remoteViews) {
            this.Q.tickerText = b(charSequence);
            this.f = remoteViews;
            return this;
        }

        @NonNull
        public Builder setTimeoutAfter(long j) {
            this.M = j;
            return this;
        }

        @NonNull
        public Builder setUsesChronometer(boolean z) {
            this.l = z;
            return this;
        }

        @NonNull
        public Builder setVibrate(@Nullable long[] jArr) {
            this.Q.vibrate = jArr;
            return this;
        }

        @NonNull
        public Builder setVisibility(int i2) {
            this.D = i2;
            return this;
        }

        @NonNull
        public Builder setWhen(long j) {
            this.Q.when = j;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class CarExtender implements Extender {
        private Bitmap a;
        private UnreadConversation b;

        /* renamed from: c, reason: collision with root package name */
        private int f483c;

        @Deprecated
        /* loaded from: classes.dex */
        public static class UnreadConversation {
            private final String[] a;
            private final RemoteInput b;

            /* renamed from: c, reason: collision with root package name */
            private final PendingIntent f484c;

            /* renamed from: d, reason: collision with root package name */
            private final PendingIntent f485d;
            private final String[] e;
            private final long f;

            /* loaded from: classes.dex */
            public static class Builder {
                private final List<String> a = new ArrayList();
                private final String b;

                /* renamed from: c, reason: collision with root package name */
                private RemoteInput f486c;

                /* renamed from: d, reason: collision with root package name */
                private PendingIntent f487d;
                private PendingIntent e;
                private long f;

                public Builder(@NonNull String str) {
                    this.b = str;
                }

                @NonNull
                public Builder addMessage(@Nullable String str) {
                    if (str != null) {
                        this.a.add(str);
                    }
                    return this;
                }

                @NonNull
                public UnreadConversation build() {
                    List<String> list = this.a;
                    return new UnreadConversation((String[]) list.toArray(new String[list.size()]), this.f486c, this.e, this.f487d, new String[]{this.b}, this.f);
                }

                @NonNull
                public Builder setLatestTimestamp(long j) {
                    this.f = j;
                    return this;
                }

                @NonNull
                public Builder setReadPendingIntent(@Nullable PendingIntent pendingIntent) {
                    this.f487d = pendingIntent;
                    return this;
                }

                @NonNull
                public Builder setReplyAction(@Nullable PendingIntent pendingIntent, @Nullable RemoteInput remoteInput) {
                    this.f486c = remoteInput;
                    this.e = pendingIntent;
                    return this;
                }
            }

            UnreadConversation(@Nullable String[] strArr, @Nullable RemoteInput remoteInput, @Nullable PendingIntent pendingIntent, @Nullable PendingIntent pendingIntent2, @Nullable String[] strArr2, long j) {
                this.a = strArr;
                this.b = remoteInput;
                this.f485d = pendingIntent2;
                this.f484c = pendingIntent;
                this.e = strArr2;
                this.f = j;
            }

            public long getLatestTimestamp() {
                return this.f;
            }

            @Nullable
            public String[] getMessages() {
                return this.a;
            }

            @Nullable
            public String getParticipant() {
                String[] strArr = this.e;
                if (strArr.length > 0) {
                    return strArr[0];
                }
                return null;
            }

            @Nullable
            public String[] getParticipants() {
                return this.e;
            }

            @Nullable
            public PendingIntent getReadPendingIntent() {
                return this.f485d;
            }

            @Nullable
            public RemoteInput getRemoteInput() {
                return this.b;
            }

            @Nullable
            public PendingIntent getReplyPendingIntent() {
                return this.f484c;
            }
        }

        public CarExtender() {
            this.f483c = 0;
        }

        public CarExtender(@NonNull Notification notification) {
            this.f483c = 0;
            if (Build.VERSION.SDK_INT < 21) {
                return;
            }
            Bundle bundle = NotificationCompat.getExtras(notification) == null ? null : NotificationCompat.getExtras(notification).getBundle("android.car.EXTENSIONS");
            if (bundle != null) {
                this.a = (Bitmap) bundle.getParcelable("large_icon");
                this.f483c = bundle.getInt("app_color", 0);
                this.b = b(bundle.getBundle("car_conversation"));
            }
        }

        @RequiresApi(21)
        private static Bundle a(@NonNull UnreadConversation unreadConversation) {
            Bundle bundle = new Bundle();
            String str = (unreadConversation.getParticipants() == null || unreadConversation.getParticipants().length <= 1) ? null : unreadConversation.getParticipants()[0];
            int length = unreadConversation.getMessages().length;
            Parcelable[] parcelableArr = new Parcelable[length];
            for (int i2 = 0; i2 < length; i2++) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("text", unreadConversation.getMessages()[i2]);
                bundle2.putString("author", str);
                parcelableArr[i2] = bundle2;
            }
            bundle.putParcelableArray("messages", parcelableArr);
            RemoteInput remoteInput = unreadConversation.getRemoteInput();
            if (remoteInput != null) {
                bundle.putParcelable("remote_input", new RemoteInput.Builder(remoteInput.getResultKey()).setLabel(remoteInput.getLabel()).setChoices(remoteInput.getChoices()).setAllowFreeFormInput(remoteInput.getAllowFreeFormInput()).addExtras(remoteInput.getExtras()).build());
            }
            bundle.putParcelable("on_reply", unreadConversation.getReplyPendingIntent());
            bundle.putParcelable("on_read", unreadConversation.getReadPendingIntent());
            bundle.putStringArray("participants", unreadConversation.getParticipants());
            bundle.putLong("timestamp", unreadConversation.getLatestTimestamp());
            return bundle;
        }

        @RequiresApi(21)
        private static UnreadConversation b(@Nullable Bundle bundle) {
            String[] strArr;
            boolean z;
            if (bundle == null) {
                return null;
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray("messages");
            if (parcelableArray != null) {
                int length = parcelableArray.length;
                String[] strArr2 = new String[length];
                for (int i2 = 0; i2 < length; i2++) {
                    if (parcelableArray[i2] instanceof Bundle) {
                        strArr2[i2] = ((Bundle) parcelableArray[i2]).getString("text");
                        if (strArr2[i2] != null) {
                        }
                    }
                    z = false;
                    break;
                }
                z = true;
                if (!z) {
                    return null;
                }
                strArr = strArr2;
            } else {
                strArr = null;
            }
            PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("on_read");
            PendingIntent pendingIntent2 = (PendingIntent) bundle.getParcelable("on_reply");
            android.app.RemoteInput remoteInput = (android.app.RemoteInput) bundle.getParcelable("remote_input");
            String[] stringArray = bundle.getStringArray("participants");
            if (stringArray == null || stringArray.length != 1) {
                return null;
            }
            return new UnreadConversation(strArr, remoteInput != null ? new RemoteInput(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), Build.VERSION.SDK_INT >= 29 ? remoteInput.getEditChoicesBeforeSending() : 0, remoteInput.getExtras(), null) : null, pendingIntent2, pendingIntent, stringArray, bundle.getLong("timestamp"));
        }

        @Override // androidx.core.app.NotificationCompat.Extender
        @NonNull
        public Builder extend(@NonNull Builder builder) {
            if (Build.VERSION.SDK_INT < 21) {
                return builder;
            }
            Bundle bundle = new Bundle();
            Bitmap bitmap = this.a;
            if (bitmap != null) {
                bundle.putParcelable("large_icon", bitmap);
            }
            int i2 = this.f483c;
            if (i2 != 0) {
                bundle.putInt("app_color", i2);
            }
            UnreadConversation unreadConversation = this.b;
            if (unreadConversation != null) {
                bundle.putBundle("car_conversation", a(unreadConversation));
            }
            builder.getExtras().putBundle("android.car.EXTENSIONS", bundle);
            return builder;
        }

        @ColorInt
        public int getColor() {
            return this.f483c;
        }

        @Nullable
        public Bitmap getLargeIcon() {
            return this.a;
        }

        @Nullable
        @Deprecated
        public UnreadConversation getUnreadConversation() {
            return this.b;
        }

        @NonNull
        public CarExtender setColor(@ColorInt int i2) {
            this.f483c = i2;
            return this;
        }

        @NonNull
        public CarExtender setLargeIcon(@Nullable Bitmap bitmap) {
            this.a = bitmap;
            return this;
        }

        @NonNull
        @Deprecated
        public CarExtender setUnreadConversation(@Nullable UnreadConversation unreadConversation) {
            this.b = unreadConversation;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class DecoratedCustomViewStyle extends Style {
        private RemoteViews o(RemoteViews remoteViews, boolean z) {
            int min;
            boolean z2 = true;
            RemoteViews applyStandardTemplate = applyStandardTemplate(true, R.layout.notification_template_custom_big, false);
            applyStandardTemplate.removeAllViews(R.id.actions);
            List<Action> q = q(this.a.mActions);
            if (!z || q == null || (min = Math.min(q.size(), 3)) <= 0) {
                z2 = false;
            } else {
                for (int i2 = 0; i2 < min; i2++) {
                    applyStandardTemplate.addView(R.id.actions, p(q.get(i2)));
                }
            }
            int i3 = z2 ? 0 : 8;
            applyStandardTemplate.setViewVisibility(R.id.actions, i3);
            applyStandardTemplate.setViewVisibility(R.id.action_divider, i3);
            buildIntoRemoteViews(applyStandardTemplate, remoteViews);
            return applyStandardTemplate;
        }

        private RemoteViews p(Action action) {
            boolean z = action.actionIntent == null;
            RemoteViews remoteViews = new RemoteViews(this.a.mContext.getPackageName(), z ? R.layout.notification_action_tombstone : R.layout.notification_action);
            IconCompat iconCompat = action.getIconCompat();
            if (iconCompat != null) {
                remoteViews.setImageViewBitmap(R.id.action_image, i(iconCompat, this.a.mContext.getResources().getColor(R.color.notification_action_color_filter)));
            }
            remoteViews.setTextViewText(R.id.action_text, action.title);
            if (!z) {
                remoteViews.setOnClickPendingIntent(R.id.action_container, action.actionIntent);
            }
            if (Build.VERSION.SDK_INT >= 15) {
                remoteViews.setContentDescription(R.id.action_container, action.title);
            }
            return remoteViews;
        }

        private static List<Action> q(List<Action> list) {
            if (list == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Action action : list) {
                if (!action.isContextual()) {
                    arrayList.add(action);
                }
            }
            return arrayList;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(new Notification.DecoratedCustomViewStyle());
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public boolean displayCustomViewInline() {
            return true;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected String l() {
            return "androidx.core.app.NotificationCompat$DecoratedCustomViewStyle";
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews bigContentView = this.a.getBigContentView();
            if (bigContentView == null) {
                bigContentView = this.a.getContentView();
            }
            if (bigContentView == null) {
                return null;
            }
            return o(bigContentView, true);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT < 24 && this.a.getContentView() != null) {
                return o(this.a.getContentView(), false);
            }
            return null;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews headsUpContentView = this.a.getHeadsUpContentView();
            RemoteViews contentView = headsUpContentView != null ? headsUpContentView : this.a.getContentView();
            if (headsUpContentView == null) {
                return null;
            }
            return o(contentView, true);
        }
    }

    /* loaded from: classes.dex */
    public interface Extender {
        @NonNull
        Builder extend(@NonNull Builder builder);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface GroupAlertBehavior {
    }

    /* loaded from: classes.dex */
    public static class InboxStyle extends Style {
        private ArrayList<CharSequence> e = new ArrayList<>();

        public InboxStyle() {
        }

        public InboxStyle(@Nullable Builder builder) {
            setBuilder(builder);
        }

        @NonNull
        public InboxStyle addLine(@Nullable CharSequence charSequence) {
            if (charSequence != null) {
                this.e.add(Builder.b(charSequence));
            }
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 16) {
                Notification.InboxStyle bigContentTitle = new Notification.InboxStyle(notificationBuilderWithBuilderAccessor.getBuilder()).setBigContentTitle(this.b);
                if (this.f492d) {
                    bigContentTitle.setSummaryText(this.f491c);
                }
                Iterator<CharSequence> it = this.e.iterator();
                while (it.hasNext()) {
                    bigContentTitle.addLine(it.next());
                }
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected void b(@NonNull Bundle bundle) {
            super.b(bundle);
            bundle.remove("android.textLines");
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected String l() {
            return "androidx.core.app.NotificationCompat$InboxStyle";
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected void n(@NonNull Bundle bundle) {
            super.n(bundle);
            this.e.clear();
            if (bundle.containsKey("android.textLines")) {
                Collections.addAll(this.e, bundle.getCharSequenceArray("android.textLines"));
            }
        }

        @NonNull
        public InboxStyle setBigContentTitle(@Nullable CharSequence charSequence) {
            this.b = Builder.b(charSequence);
            return this;
        }

        @NonNull
        public InboxStyle setSummaryText(@Nullable CharSequence charSequence) {
            this.f491c = Builder.b(charSequence);
            this.f492d = true;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class MessagingStyle extends Style {
        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        private final List<Message> e = new ArrayList();
        private final List<Message> f = new ArrayList();
        private Person g;

        @Nullable
        private CharSequence h;

        /* renamed from: i, reason: collision with root package name */
        @Nullable
        private Boolean f488i;

        /* loaded from: classes.dex */
        public static final class Message {
            private final CharSequence a;
            private final long b;

            /* renamed from: c, reason: collision with root package name */
            @Nullable
            private final Person f489c;

            /* renamed from: d, reason: collision with root package name */
            private Bundle f490d;

            @Nullable
            private String e;

            @Nullable
            private Uri f;

            public Message(@Nullable CharSequence charSequence, long j, @Nullable Person person) {
                this.f490d = new Bundle();
                this.a = charSequence;
                this.b = j;
                this.f489c = person;
            }

            @Deprecated
            public Message(@Nullable CharSequence charSequence, long j, @Nullable CharSequence charSequence2) {
                this(charSequence, j, new Person.Builder().setName(charSequence2).build());
            }

            @NonNull
            static Bundle[] a(@NonNull List<Message> list) {
                Bundle[] bundleArr = new Bundle[list.size()];
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    bundleArr[i2] = list.get(i2).e();
                }
                return bundleArr;
            }

            @Nullable
            static Message b(@NonNull Bundle bundle) {
                try {
                    if (bundle.containsKey("text") && bundle.containsKey("time")) {
                        Message message = new Message(bundle.getCharSequence("text"), bundle.getLong("time"), bundle.containsKey("person") ? Person.fromBundle(bundle.getBundle("person")) : (!bundle.containsKey("sender_person") || Build.VERSION.SDK_INT < 28) ? bundle.containsKey("sender") ? new Person.Builder().setName(bundle.getCharSequence("sender")).build() : null : Person.fromAndroidPerson((android.app.Person) bundle.getParcelable("sender_person")));
                        if (bundle.containsKey("type") && bundle.containsKey("uri")) {
                            message.setData(bundle.getString("type"), (Uri) bundle.getParcelable("uri"));
                        }
                        if (bundle.containsKey("extras")) {
                            message.getExtras().putAll(bundle.getBundle("extras"));
                        }
                        return message;
                    }
                } catch (ClassCastException unused) {
                }
                return null;
            }

            @NonNull
            static List<Message> c(@NonNull Parcelable[] parcelableArr) {
                Message b;
                ArrayList arrayList = new ArrayList(parcelableArr.length);
                for (int i2 = 0; i2 < parcelableArr.length; i2++) {
                    if ((parcelableArr[i2] instanceof Bundle) && (b = b((Bundle) parcelableArr[i2])) != null) {
                        arrayList.add(b);
                    }
                }
                return arrayList;
            }

            @NonNull
            private Bundle e() {
                Bundle bundle = new Bundle();
                CharSequence charSequence = this.a;
                if (charSequence != null) {
                    bundle.putCharSequence("text", charSequence);
                }
                bundle.putLong("time", this.b);
                Person person = this.f489c;
                if (person != null) {
                    bundle.putCharSequence("sender", person.getName());
                    if (Build.VERSION.SDK_INT >= 28) {
                        bundle.putParcelable("sender_person", this.f489c.toAndroidPerson());
                    } else {
                        bundle.putBundle("person", this.f489c.toBundle());
                    }
                }
                String str = this.e;
                if (str != null) {
                    bundle.putString("type", str);
                }
                Uri uri = this.f;
                if (uri != null) {
                    bundle.putParcelable("uri", uri);
                }
                Bundle bundle2 = this.f490d;
                if (bundle2 != null) {
                    bundle.putBundle("extras", bundle2);
                }
                return bundle;
            }

            @NonNull
            @RequiresApi(24)
            @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
            Notification.MessagingStyle.Message d() {
                Notification.MessagingStyle.Message message;
                Person person = getPerson();
                if (Build.VERSION.SDK_INT >= 28) {
                    message = new Notification.MessagingStyle.Message(getText(), getTimestamp(), person != null ? person.toAndroidPerson() : null);
                } else {
                    message = new Notification.MessagingStyle.Message(getText(), getTimestamp(), person != null ? person.getName() : null);
                }
                if (getDataMimeType() != null) {
                    message.setData(getDataMimeType(), getDataUri());
                }
                return message;
            }

            @Nullable
            public String getDataMimeType() {
                return this.e;
            }

            @Nullable
            public Uri getDataUri() {
                return this.f;
            }

            @NonNull
            public Bundle getExtras() {
                return this.f490d;
            }

            @Nullable
            public Person getPerson() {
                return this.f489c;
            }

            @Nullable
            @Deprecated
            public CharSequence getSender() {
                Person person = this.f489c;
                if (person == null) {
                    return null;
                }
                return person.getName();
            }

            @Nullable
            public CharSequence getText() {
                return this.a;
            }

            public long getTimestamp() {
                return this.b;
            }

            @NonNull
            public Message setData(@Nullable String str, @Nullable Uri uri) {
                this.e = str;
                this.f = uri;
                return this;
            }
        }

        MessagingStyle() {
        }

        public MessagingStyle(@NonNull Person person) {
            if (TextUtils.isEmpty(person.getName())) {
                throw new IllegalArgumentException("User's name must not be empty.");
            }
            this.g = person;
        }

        @Deprecated
        public MessagingStyle(@NonNull CharSequence charSequence) {
            this.g = new Person.Builder().setName(charSequence).build();
        }

        @Nullable
        public static MessagingStyle extractMessagingStyleFromNotification(@NonNull Notification notification) {
            Style extractStyleFromNotification = Style.extractStyleFromNotification(notification);
            if (extractStyleFromNotification instanceof MessagingStyle) {
                return (MessagingStyle) extractStyleFromNotification;
            }
            return null;
        }

        @Nullable
        private Message o() {
            for (int size = this.e.size() - 1; size >= 0; size--) {
                Message message = this.e.get(size);
                if (message.getPerson() != null && !TextUtils.isEmpty(message.getPerson().getName())) {
                    return message;
                }
            }
            if (this.e.isEmpty()) {
                return null;
            }
            return this.e.get(r0.size() - 1);
        }

        private boolean p() {
            for (int size = this.e.size() - 1; size >= 0; size--) {
                Message message = this.e.get(size);
                if (message.getPerson() != null && message.getPerson().getName() == null) {
                    return true;
                }
            }
            return false;
        }

        @NonNull
        private TextAppearanceSpan q(int i2) {
            return new TextAppearanceSpan(null, 0, 0, ColorStateList.valueOf(i2), null);
        }

        private CharSequence r(@NonNull Message message) {
            BidiFormatter bidiFormatter = BidiFormatter.getInstance();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            boolean z = Build.VERSION.SDK_INT >= 21;
            int i2 = z ? -16777216 : -1;
            CharSequence name = message.getPerson() == null ? "" : message.getPerson().getName();
            if (TextUtils.isEmpty(name)) {
                name = this.g.getName();
                if (z && this.a.getColor() != 0) {
                    i2 = this.a.getColor();
                }
            }
            CharSequence unicodeWrap = bidiFormatter.unicodeWrap(name);
            spannableStringBuilder.append(unicodeWrap);
            spannableStringBuilder.setSpan(q(i2), spannableStringBuilder.length() - unicodeWrap.length(), spannableStringBuilder.length(), 33);
            spannableStringBuilder.append((CharSequence) "  ").append(bidiFormatter.unicodeWrap(message.getText() != null ? message.getText() : ""));
            return spannableStringBuilder;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void addCompatExtras(@NonNull Bundle bundle) {
            super.addCompatExtras(bundle);
            bundle.putCharSequence("android.selfDisplayName", this.g.getName());
            bundle.putBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER, this.g.toBundle());
            bundle.putCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE, this.h);
            if (this.h != null && this.f488i.booleanValue()) {
                bundle.putCharSequence("android.conversationTitle", this.h);
            }
            if (!this.e.isEmpty()) {
                bundle.putParcelableArray("android.messages", Message.a(this.e));
            }
            if (!this.f.isEmpty()) {
                bundle.putParcelableArray(NotificationCompat.EXTRA_HISTORIC_MESSAGES, Message.a(this.f));
            }
            Boolean bool = this.f488i;
            if (bool != null) {
                bundle.putBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION, bool.booleanValue());
            }
        }

        @NonNull
        public MessagingStyle addHistoricMessage(@Nullable Message message) {
            if (message != null) {
                this.f.add(message);
                if (this.f.size() > 25) {
                    this.f.remove(0);
                }
            }
            return this;
        }

        @NonNull
        public MessagingStyle addMessage(@Nullable Message message) {
            if (message != null) {
                this.e.add(message);
                if (this.e.size() > 25) {
                    this.e.remove(0);
                }
            }
            return this;
        }

        @NonNull
        public MessagingStyle addMessage(@Nullable CharSequence charSequence, long j, @Nullable Person person) {
            addMessage(new Message(charSequence, j, person));
            return this;
        }

        @NonNull
        @Deprecated
        public MessagingStyle addMessage(@Nullable CharSequence charSequence, long j, @Nullable CharSequence charSequence2) {
            this.e.add(new Message(charSequence, j, new Person.Builder().setName(charSequence2).build()));
            if (this.e.size() > 25) {
                this.e.remove(0);
            }
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x00b9  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00d1  */
        /* JADX WARN: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
        @Override // androidx.core.app.NotificationCompat.Style
        @androidx.annotation.RestrictTo({androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void apply(androidx.core.app.NotificationBuilderWithBuilderAccessor r8) {
            /*
                Method dump skipped, instructions count: 296
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat.MessagingStyle.apply(androidx.core.app.NotificationBuilderWithBuilderAccessor):void");
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected void b(@NonNull Bundle bundle) {
            super.b(bundle);
            bundle.remove(NotificationCompat.EXTRA_MESSAGING_STYLE_USER);
            bundle.remove("android.selfDisplayName");
            bundle.remove("android.conversationTitle");
            bundle.remove(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE);
            bundle.remove("android.messages");
            bundle.remove(NotificationCompat.EXTRA_HISTORIC_MESSAGES);
            bundle.remove(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION);
        }

        @Nullable
        public CharSequence getConversationTitle() {
            return this.h;
        }

        @NonNull
        public List<Message> getHistoricMessages() {
            return this.f;
        }

        @NonNull
        public List<Message> getMessages() {
            return this.e;
        }

        @NonNull
        public Person getUser() {
            return this.g;
        }

        @Nullable
        @Deprecated
        public CharSequence getUserDisplayName() {
            return this.g.getName();
        }

        public boolean isGroupConversation() {
            Builder builder = this.a;
            if (builder != null && builder.mContext.getApplicationInfo().targetSdkVersion < 28 && this.f488i == null) {
                return this.h != null;
            }
            Boolean bool = this.f488i;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected String l() {
            return "androidx.core.app.NotificationCompat$MessagingStyle";
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected void n(@NonNull Bundle bundle) {
            super.n(bundle);
            this.e.clear();
            this.g = bundle.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER) ? Person.fromBundle(bundle.getBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) : new Person.Builder().setName(bundle.getString("android.selfDisplayName")).build();
            CharSequence charSequence = bundle.getCharSequence("android.conversationTitle");
            this.h = charSequence;
            if (charSequence == null) {
                this.h = bundle.getCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE);
            }
            Parcelable[] parcelableArray = bundle.getParcelableArray("android.messages");
            if (parcelableArray != null) {
                this.e.addAll(Message.c(parcelableArray));
            }
            Parcelable[] parcelableArray2 = bundle.getParcelableArray(NotificationCompat.EXTRA_HISTORIC_MESSAGES);
            if (parcelableArray2 != null) {
                this.f.addAll(Message.c(parcelableArray2));
            }
            if (bundle.containsKey(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION)) {
                this.f488i = Boolean.valueOf(bundle.getBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION));
            }
        }

        @NonNull
        public MessagingStyle setConversationTitle(@Nullable CharSequence charSequence) {
            this.h = charSequence;
            return this;
        }

        @NonNull
        public MessagingStyle setGroupConversation(boolean z) {
            this.f488i = Boolean.valueOf(z);
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface NotificationVisibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface StreamType {
    }

    /* loaded from: classes.dex */
    public static abstract class Style {

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected Builder a;
        CharSequence b;

        /* renamed from: c, reason: collision with root package name */
        CharSequence f491c;

        /* renamed from: d, reason: collision with root package name */
        boolean f492d = false;

        private int a() {
            Resources resources = this.a.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_top_pad);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.notification_top_pad_large_text);
            float c2 = (c(resources.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
            return Math.round(((1.0f - c2) * dimensionPixelSize) + (c2 * dimensionPixelSize2));
        }

        private static float c(float f, float f2, float f3) {
            return f < f2 ? f2 : f > f3 ? f3 : f;
        }

        @Nullable
        static Style d(@Nullable String str) {
            if (str == null) {
                return null;
            }
            str.hashCode();
            char c2 = 65535;
            switch (str.hashCode()) {
                case -716705180:
                    if (str.equals("androidx.core.app.NotificationCompat$DecoratedCustomViewStyle")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -171946061:
                    if (str.equals("androidx.core.app.NotificationCompat$BigPictureStyle")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 912942987:
                    if (str.equals("androidx.core.app.NotificationCompat$InboxStyle")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 919595044:
                    if (str.equals("androidx.core.app.NotificationCompat$BigTextStyle")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 2090799565:
                    if (str.equals("androidx.core.app.NotificationCompat$MessagingStyle")) {
                        c2 = 4;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    return new DecoratedCustomViewStyle();
                case 1:
                    return new BigPictureStyle();
                case 2:
                    return new InboxStyle();
                case 3:
                    return new BigTextStyle();
                case 4:
                    return new MessagingStyle();
                default:
                    return null;
            }
        }

        @Nullable
        private static Style e(@Nullable String str) {
            int i2;
            if (str != null && (i2 = Build.VERSION.SDK_INT) >= 16) {
                if (str.equals(Notification.BigPictureStyle.class.getName())) {
                    return new BigPictureStyle();
                }
                if (str.equals(Notification.BigTextStyle.class.getName())) {
                    return new BigTextStyle();
                }
                if (str.equals(Notification.InboxStyle.class.getName())) {
                    return new InboxStyle();
                }
                if (i2 >= 24) {
                    if (str.equals(Notification.MessagingStyle.class.getName())) {
                        return new MessagingStyle();
                    }
                    if (str.equals(Notification.DecoratedCustomViewStyle.class.getName())) {
                        return new DecoratedCustomViewStyle();
                    }
                }
            }
            return null;
        }

        @Nullable
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public static Style extractStyleFromNotification(@NonNull Notification notification) {
            Bundle extras = NotificationCompat.getExtras(notification);
            if (extras == null) {
                return null;
            }
            return g(extras);
        }

        @Nullable
        static Style f(@NonNull Bundle bundle) {
            Style d2 = d(bundle.getString(NotificationCompat.EXTRA_COMPAT_TEMPLATE));
            return d2 != null ? d2 : (bundle.containsKey("android.selfDisplayName") || bundle.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) ? new MessagingStyle() : bundle.containsKey("android.picture") ? new BigPictureStyle() : bundle.containsKey("android.bigText") ? new BigTextStyle() : bundle.containsKey("android.textLines") ? new InboxStyle() : e(bundle.getString("android.template"));
        }

        @Nullable
        static Style g(@NonNull Bundle bundle) {
            Style f = f(bundle);
            if (f == null) {
                return null;
            }
            try {
                f.n(bundle);
                return f;
            } catch (ClassCastException unused) {
                return null;
            }
        }

        private Bitmap h(int i2, int i3, int i4) {
            return j(IconCompat.createWithResource(this.a.mContext, i2), i3, i4);
        }

        private Bitmap j(@NonNull IconCompat iconCompat, int i2, int i3) {
            Drawable loadDrawable = iconCompat.loadDrawable(this.a.mContext);
            int intrinsicWidth = i3 == 0 ? loadDrawable.getIntrinsicWidth() : i3;
            if (i3 == 0) {
                i3 = loadDrawable.getIntrinsicHeight();
            }
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i3, Bitmap.Config.ARGB_8888);
            loadDrawable.setBounds(0, 0, intrinsicWidth, i3);
            if (i2 != 0) {
                loadDrawable.mutate().setColorFilter(new PorterDuffColorFilter(i2, PorterDuff.Mode.SRC_IN));
            }
            loadDrawable.draw(new Canvas(createBitmap));
            return createBitmap;
        }

        private Bitmap k(int i2, int i3, int i4, int i5) {
            int i6 = R.drawable.notification_icon_background;
            if (i5 == 0) {
                i5 = 0;
            }
            Bitmap h = h(i6, i5, i3);
            Canvas canvas = new Canvas(h);
            Drawable mutate = this.a.mContext.getResources().getDrawable(i2).mutate();
            mutate.setFilterBitmap(true);
            int i7 = (i3 - i4) / 2;
            int i8 = i4 + i7;
            mutate.setBounds(i7, i7, i8, i8);
            mutate.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
            mutate.draw(canvas);
            return h;
        }

        private void m(RemoteViews remoteViews) {
            remoteViews.setViewVisibility(R.id.title, 8);
            remoteViews.setViewVisibility(R.id.text2, 8);
            remoteViews.setViewVisibility(R.id.text, 8);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void addCompatExtras(@NonNull Bundle bundle) {
            if (this.f492d) {
                bundle.putCharSequence("android.summaryText", this.f491c);
            }
            CharSequence charSequence = this.b;
            if (charSequence != null) {
                bundle.putCharSequence("android.title.big", charSequence);
            }
            String l = l();
            if (l != null) {
                bundle.putString(NotificationCompat.EXTRA_COMPAT_TEMPLATE, l);
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
        }

        /* JADX WARN: Removed duplicated region for block: B:44:0x016c  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x017e A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0182  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x01a4  */
        /* JADX WARN: Removed duplicated region for block: B:62:0x01ef  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x01fb  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x01f1  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x01ea  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x0176  */
        @androidx.annotation.NonNull
        @androidx.annotation.RestrictTo({androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.widget.RemoteViews applyStandardTemplate(boolean r17, int r18, boolean r19) {
            /*
                Method dump skipped, instructions count: 513
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.NotificationCompat.Style.applyStandardTemplate(boolean, int, boolean):android.widget.RemoteViews");
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected void b(@NonNull Bundle bundle) {
            bundle.remove("android.summaryText");
            bundle.remove("android.title.big");
            bundle.remove(NotificationCompat.EXTRA_COMPAT_TEMPLATE);
        }

        @Nullable
        public Notification build() {
            Builder builder = this.a;
            if (builder != null) {
                return builder.build();
            }
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public void buildIntoRemoteViews(RemoteViews remoteViews, RemoteViews remoteViews2) {
            m(remoteViews);
            int i2 = R.id.notification_main_column;
            remoteViews.removeAllViews(i2);
            remoteViews.addView(i2, remoteViews2.clone());
            remoteViews.setViewVisibility(i2, 0);
            if (Build.VERSION.SDK_INT >= 21) {
                remoteViews.setViewPadding(R.id.notification_main_column_container, 0, a(), 0, 0);
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Bitmap createColoredBitmap(int i2, int i3) {
            return h(i2, i3, 0);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public boolean displayCustomViewInline() {
            return false;
        }

        Bitmap i(@NonNull IconCompat iconCompat, int i2) {
            return j(iconCompat, i2, 0);
        }

        @Nullable
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected String l() {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected void n(@NonNull Bundle bundle) {
            if (bundle.containsKey("android.summaryText")) {
                this.f491c = bundle.getCharSequence("android.summaryText");
                this.f492d = true;
            }
            this.b = bundle.getCharSequence("android.title.big");
        }

        public void setBuilder(@Nullable Builder builder) {
            if (this.a != builder) {
                this.a = builder;
                if (builder != null) {
                    builder.setStyle(this);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class WearableExtender implements Extender {

        @Deprecated
        public static final int SCREEN_TIMEOUT_LONG = -1;

        @Deprecated
        public static final int SCREEN_TIMEOUT_SHORT = 0;

        @Deprecated
        public static final int SIZE_DEFAULT = 0;

        @Deprecated
        public static final int SIZE_FULL_SCREEN = 5;

        @Deprecated
        public static final int SIZE_LARGE = 4;

        @Deprecated
        public static final int SIZE_MEDIUM = 3;

        @Deprecated
        public static final int SIZE_SMALL = 2;

        @Deprecated
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList<Action> a;
        private int b;

        /* renamed from: c, reason: collision with root package name */
        private PendingIntent f493c;

        /* renamed from: d, reason: collision with root package name */
        private ArrayList<Notification> f494d;
        private Bitmap e;
        private int f;
        private int g;
        private int h;

        /* renamed from: i, reason: collision with root package name */
        private int f495i;
        private int j;
        private int k;
        private int l;
        private String m;
        private String n;

        public WearableExtender() {
            this.a = new ArrayList<>();
            this.b = 1;
            this.f494d = new ArrayList<>();
            this.g = 8388613;
            this.h = -1;
            this.f495i = 0;
            this.k = 80;
        }

        public WearableExtender(@NonNull Notification notification) {
            int i2 = Build.VERSION.SDK_INT;
            this.a = new ArrayList<>();
            this.b = 1;
            this.f494d = new ArrayList<>();
            this.g = 8388613;
            this.h = -1;
            this.f495i = 0;
            this.k = 80;
            Bundle extras = NotificationCompat.getExtras(notification);
            Bundle bundle = extras != null ? extras.getBundle("android.wearable.EXTENSIONS") : null;
            if (bundle != null) {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList("actions");
                if (i2 >= 16 && parcelableArrayList != null) {
                    int size = parcelableArrayList.size();
                    Action[] actionArr = new Action[size];
                    for (int i3 = 0; i3 < size; i3++) {
                        if (i2 >= 20) {
                            actionArr[i3] = NotificationCompat.a((Notification.Action) parcelableArrayList.get(i3));
                        } else if (i2 >= 16) {
                            actionArr[i3] = c.g((Bundle) parcelableArrayList.get(i3));
                        }
                    }
                    Collections.addAll(this.a, actionArr);
                }
                this.b = bundle.getInt("flags", 1);
                this.f493c = (PendingIntent) bundle.getParcelable("displayIntent");
                Notification[] c2 = NotificationCompat.c(bundle, "pages");
                if (c2 != null) {
                    Collections.addAll(this.f494d, c2);
                }
                this.e = (Bitmap) bundle.getParcelable("background");
                this.f = bundle.getInt("contentIcon");
                this.g = bundle.getInt("contentIconGravity", 8388613);
                this.h = bundle.getInt("contentActionIndex", -1);
                this.f495i = bundle.getInt("customSizePreset", 0);
                this.j = bundle.getInt("customContentHeight");
                this.k = bundle.getInt("gravity", 80);
                this.l = bundle.getInt("hintScreenTimeout");
                this.m = bundle.getString("dismissalId");
                this.n = bundle.getString("bridgeTag");
            }
        }

        @RequiresApi(20)
        private static Notification.Action a(Action action) {
            Notification.Action.Builder builder;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 23) {
                IconCompat iconCompat = action.getIconCompat();
                builder = new Notification.Action.Builder(iconCompat == null ? null : iconCompat.toIcon(), action.getTitle(), action.getActionIntent());
            } else {
                IconCompat iconCompat2 = action.getIconCompat();
                builder = new Notification.Action.Builder((iconCompat2 == null || iconCompat2.getType() != 2) ? 0 : iconCompat2.getResId(), action.getTitle(), action.getActionIntent());
            }
            Bundle bundle = action.getExtras() != null ? new Bundle(action.getExtras()) : new Bundle();
            bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
            if (i2 >= 24) {
                builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
            }
            builder.addExtras(bundle);
            RemoteInput[] remoteInputs = action.getRemoteInputs();
            if (remoteInputs != null) {
                for (android.app.RemoteInput remoteInput : RemoteInput.b(remoteInputs)) {
                    builder.addRemoteInput(remoteInput);
                }
            }
            return builder.build();
        }

        private void b(int i2, boolean z) {
            int i3;
            if (z) {
                i3 = i2 | this.b;
            } else {
                i3 = (i2 ^ (-1)) & this.b;
            }
            this.b = i3;
        }

        @NonNull
        public WearableExtender addAction(@NonNull Action action) {
            this.a.add(action);
            return this;
        }

        @NonNull
        public WearableExtender addActions(@NonNull List<Action> list) {
            this.a.addAll(list);
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender addPage(@NonNull Notification notification) {
            this.f494d.add(notification);
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender addPages(@NonNull List<Notification> list) {
            this.f494d.addAll(list);
            return this;
        }

        @NonNull
        public WearableExtender clearActions() {
            this.a.clear();
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender clearPages() {
            this.f494d.clear();
            return this;
        }

        @NonNull
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public WearableExtender m10clone() {
            WearableExtender wearableExtender = new WearableExtender();
            wearableExtender.a = new ArrayList<>(this.a);
            wearableExtender.b = this.b;
            wearableExtender.f493c = this.f493c;
            wearableExtender.f494d = new ArrayList<>(this.f494d);
            wearableExtender.e = this.e;
            wearableExtender.f = this.f;
            wearableExtender.g = this.g;
            wearableExtender.h = this.h;
            wearableExtender.f495i = this.f495i;
            wearableExtender.j = this.j;
            wearableExtender.k = this.k;
            wearableExtender.l = this.l;
            wearableExtender.m = this.m;
            wearableExtender.n = this.n;
            return wearableExtender;
        }

        @Override // androidx.core.app.NotificationCompat.Extender
        @NonNull
        public Builder extend(@NonNull Builder builder) {
            Parcelable a;
            int i2 = Build.VERSION.SDK_INT;
            Bundle bundle = new Bundle();
            if (!this.a.isEmpty()) {
                if (i2 >= 16) {
                    ArrayList<? extends Parcelable> arrayList = new ArrayList<>(this.a.size());
                    Iterator<Action> it = this.a.iterator();
                    while (it.hasNext()) {
                        Action next = it.next();
                        if (i2 >= 20) {
                            a = a(next);
                        } else if (i2 >= 16) {
                            a = c.j(next);
                        }
                        arrayList.add(a);
                    }
                    bundle.putParcelableArrayList("actions", arrayList);
                } else {
                    bundle.putParcelableArrayList("actions", null);
                }
            }
            int i3 = this.b;
            if (i3 != 1) {
                bundle.putInt("flags", i3);
            }
            PendingIntent pendingIntent = this.f493c;
            if (pendingIntent != null) {
                bundle.putParcelable("displayIntent", pendingIntent);
            }
            if (!this.f494d.isEmpty()) {
                ArrayList<Notification> arrayList2 = this.f494d;
                bundle.putParcelableArray("pages", (Parcelable[]) arrayList2.toArray(new Notification[arrayList2.size()]));
            }
            Bitmap bitmap = this.e;
            if (bitmap != null) {
                bundle.putParcelable("background", bitmap);
            }
            int i4 = this.f;
            if (i4 != 0) {
                bundle.putInt("contentIcon", i4);
            }
            int i5 = this.g;
            if (i5 != 8388613) {
                bundle.putInt("contentIconGravity", i5);
            }
            int i6 = this.h;
            if (i6 != -1) {
                bundle.putInt("contentActionIndex", i6);
            }
            int i7 = this.f495i;
            if (i7 != 0) {
                bundle.putInt("customSizePreset", i7);
            }
            int i8 = this.j;
            if (i8 != 0) {
                bundle.putInt("customContentHeight", i8);
            }
            int i9 = this.k;
            if (i9 != 80) {
                bundle.putInt("gravity", i9);
            }
            int i10 = this.l;
            if (i10 != 0) {
                bundle.putInt("hintScreenTimeout", i10);
            }
            String str = this.m;
            if (str != null) {
                bundle.putString("dismissalId", str);
            }
            String str2 = this.n;
            if (str2 != null) {
                bundle.putString("bridgeTag", str2);
            }
            builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
            return builder;
        }

        @NonNull
        public List<Action> getActions() {
            return this.a;
        }

        @Nullable
        @Deprecated
        public Bitmap getBackground() {
            return this.e;
        }

        @Nullable
        public String getBridgeTag() {
            return this.n;
        }

        public int getContentAction() {
            return this.h;
        }

        @Deprecated
        public int getContentIcon() {
            return this.f;
        }

        @Deprecated
        public int getContentIconGravity() {
            return this.g;
        }

        public boolean getContentIntentAvailableOffline() {
            return (this.b & 1) != 0;
        }

        @Deprecated
        public int getCustomContentHeight() {
            return this.j;
        }

        @Deprecated
        public int getCustomSizePreset() {
            return this.f495i;
        }

        @Nullable
        public String getDismissalId() {
            return this.m;
        }

        @Nullable
        @Deprecated
        public PendingIntent getDisplayIntent() {
            return this.f493c;
        }

        @Deprecated
        public int getGravity() {
            return this.k;
        }

        @Deprecated
        public boolean getHintAmbientBigPicture() {
            return (this.b & 32) != 0;
        }

        @Deprecated
        public boolean getHintAvoidBackgroundClipping() {
            return (this.b & 16) != 0;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            return (this.b & 64) != 0;
        }

        @Deprecated
        public boolean getHintHideIcon() {
            return (this.b & 2) != 0;
        }

        @Deprecated
        public int getHintScreenTimeout() {
            return this.l;
        }

        @Deprecated
        public boolean getHintShowBackgroundOnly() {
            return (this.b & 4) != 0;
        }

        @NonNull
        @Deprecated
        public List<Notification> getPages() {
            return this.f494d;
        }

        public boolean getStartScrollBottom() {
            return (this.b & 8) != 0;
        }

        @NonNull
        @Deprecated
        public WearableExtender setBackground(@Nullable Bitmap bitmap) {
            this.e = bitmap;
            return this;
        }

        @NonNull
        public WearableExtender setBridgeTag(@Nullable String str) {
            this.n = str;
            return this;
        }

        @NonNull
        public WearableExtender setContentAction(int i2) {
            this.h = i2;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setContentIcon(int i2) {
            this.f = i2;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setContentIconGravity(int i2) {
            this.g = i2;
            return this;
        }

        @NonNull
        public WearableExtender setContentIntentAvailableOffline(boolean z) {
            b(1, z);
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setCustomContentHeight(int i2) {
            this.j = i2;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setCustomSizePreset(int i2) {
            this.f495i = i2;
            return this;
        }

        @NonNull
        public WearableExtender setDismissalId(@Nullable String str) {
            this.m = str;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setDisplayIntent(@Nullable PendingIntent pendingIntent) {
            this.f493c = pendingIntent;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setGravity(int i2) {
            this.k = i2;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setHintAmbientBigPicture(boolean z) {
            b(32, z);
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setHintAvoidBackgroundClipping(boolean z) {
            b(16, z);
            return this;
        }

        @NonNull
        public WearableExtender setHintContentIntentLaunchesActivity(boolean z) {
            b(64, z);
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setHintHideIcon(boolean z) {
            b(2, z);
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setHintScreenTimeout(int i2) {
            this.l = i2;
            return this;
        }

        @NonNull
        @Deprecated
        public WearableExtender setHintShowBackgroundOnly(boolean z) {
            b(4, z);
            return this;
        }

        @NonNull
        public WearableExtender setStartScrollBottom(boolean z) {
            b(8, z);
            return this;
        }
    }

    @Deprecated
    public NotificationCompat() {
    }

    @NonNull
    @RequiresApi(20)
    static Action a(@NonNull Notification.Action action) {
        RemoteInput[] remoteInputArr;
        int i2;
        int i3 = Build.VERSION.SDK_INT;
        android.app.RemoteInput[] remoteInputs = action.getRemoteInputs();
        if (remoteInputs == null) {
            remoteInputArr = null;
        } else {
            RemoteInput[] remoteInputArr2 = new RemoteInput[remoteInputs.length];
            for (int i4 = 0; i4 < remoteInputs.length; i4++) {
                android.app.RemoteInput remoteInput = remoteInputs[i4];
                remoteInputArr2[i4] = new RemoteInput(remoteInput.getResultKey(), remoteInput.getLabel(), remoteInput.getChoices(), remoteInput.getAllowFreeFormInput(), i3 >= 29 ? remoteInput.getEditChoicesBeforeSending() : 0, remoteInput.getExtras(), null);
            }
            remoteInputArr = remoteInputArr2;
        }
        boolean z = i3 >= 24 ? action.getExtras().getBoolean("android.support.allowGeneratedReplies") || action.getAllowGeneratedReplies() : action.getExtras().getBoolean("android.support.allowGeneratedReplies");
        boolean z2 = action.getExtras().getBoolean("android.support.action.showsUserInterface", true);
        int semanticAction = i3 >= 28 ? action.getSemanticAction() : action.getExtras().getInt("android.support.action.semanticAction", 0);
        boolean isContextual = i3 >= 29 ? action.isContextual() : false;
        if (i3 < 23) {
            return new Action(action.icon, action.title, action.actionIntent, action.getExtras(), remoteInputArr, (RemoteInput[]) null, z, semanticAction, z2, isContextual);
        }
        if (action.getIcon() != null || (i2 = action.icon) == 0) {
            return new Action(action.getIcon() != null ? IconCompat.createFromIconOrNullIfZeroResId(action.getIcon()) : null, action.title, action.actionIntent, action.getExtras(), remoteInputArr, (RemoteInput[]) null, z, semanticAction, z2, isContextual);
        }
        return new Action(i2, action.title, action.actionIntent, action.getExtras(), remoteInputArr, (RemoteInput[]) null, z, semanticAction, z2, isContextual);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    static boolean b(@NonNull Notification notification) {
        return (notification.flags & 128) != 0;
    }

    @NonNull
    static Notification[] c(@NonNull Bundle bundle, @NonNull String str) {
        Parcelable[] parcelableArray = bundle.getParcelableArray(str);
        if ((parcelableArray instanceof Notification[]) || parcelableArray == null) {
            return (Notification[]) parcelableArray;
        }
        Notification[] notificationArr = new Notification[parcelableArray.length];
        for (int i2 = 0; i2 < parcelableArray.length; i2++) {
            notificationArr[i2] = (Notification) parcelableArray[i2];
        }
        bundle.putParcelableArray(str, notificationArr);
        return notificationArr;
    }

    @Nullable
    public static Action getAction(@NonNull Notification notification, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 20) {
            return a(notification.actions[i2]);
        }
        if (i3 >= 19) {
            Notification.Action action = notification.actions[i2];
            SparseArray sparseParcelableArray = notification.extras.getSparseParcelableArray("android.support.actionExtras");
            return c.l(action.icon, action.title, action.actionIntent, sparseParcelableArray != null ? (Bundle) sparseParcelableArray.get(i2) : null);
        }
        if (i3 >= 16) {
            return c.e(notification, i2);
        }
        return null;
    }

    public static int getActionCount(@NonNull Notification notification) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 19) {
            if (i2 >= 16) {
                return c.f(notification);
            }
            return 0;
        }
        Notification.Action[] actionArr = notification.actions;
        if (actionArr != null) {
            return actionArr.length;
        }
        return 0;
    }

    public static boolean getAllowSystemGeneratedContextualActions(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 29) {
            return notification.getAllowSystemGeneratedContextualActions();
        }
        return false;
    }

    public static boolean getAutoCancel(@NonNull Notification notification) {
        return (notification.flags & 16) != 0;
    }

    public static int getBadgeIconType(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getBadgeIconType();
        }
        return 0;
    }

    @Nullable
    public static BubbleMetadata getBubbleMetadata(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 29) {
            return BubbleMetadata.fromPlatform(notification.getBubbleMetadata());
        }
        return null;
    }

    @Nullable
    public static String getCategory(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 21) {
            return notification.category;
        }
        return null;
    }

    @Nullable
    public static String getChannelId(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getChannelId();
        }
        return null;
    }

    public static int getColor(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 21) {
            return notification.color;
        }
        return 0;
    }

    @Nullable
    @RequiresApi(19)
    public static CharSequence getContentInfo(@NonNull Notification notification) {
        return notification.extras.getCharSequence("android.infoText");
    }

    @Nullable
    @RequiresApi(19)
    public static CharSequence getContentText(@NonNull Notification notification) {
        return notification.extras.getCharSequence("android.text");
    }

    @Nullable
    @RequiresApi(19)
    public static CharSequence getContentTitle(@NonNull Notification notification) {
        return notification.extras.getCharSequence("android.title");
    }

    @Nullable
    public static Bundle getExtras(@NonNull Notification notification) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 19) {
            return notification.extras;
        }
        if (i2 >= 16) {
            return c.k(notification);
        }
        return null;
    }

    @Nullable
    public static String getGroup(@NonNull Notification notification) {
        Bundle k;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 20) {
            return notification.getGroup();
        }
        if (i2 >= 19) {
            k = notification.extras;
        } else {
            if (i2 < 16) {
                return null;
            }
            k = c.k(notification);
        }
        return k.getString("android.support.groupKey");
    }

    public static int getGroupAlertBehavior(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getGroupAlertBehavior();
        }
        return 0;
    }

    @NonNull
    @RequiresApi(21)
    public static List<Action> getInvisibleActions(@NonNull Notification notification) {
        Bundle bundle;
        Bundle bundle2;
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 19 && (bundle = notification.extras.getBundle("android.car.EXTENSIONS")) != null && (bundle2 = bundle.getBundle("invisible_actions")) != null) {
            for (int i2 = 0; i2 < bundle2.size(); i2++) {
                arrayList.add(c.g(bundle2.getBundle(Integer.toString(i2))));
            }
        }
        return arrayList;
    }

    public static boolean getLocalOnly(@NonNull Notification notification) {
        Bundle k;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 20) {
            return (notification.flags & 256) != 0;
        }
        if (i2 >= 19) {
            k = notification.extras;
        } else {
            if (i2 < 16) {
                return false;
            }
            k = c.k(notification);
        }
        return k.getBoolean("android.support.localOnly");
    }

    @Nullable
    public static LocusIdCompat getLocusId(@NonNull Notification notification) {
        LocusId locusId;
        if (Build.VERSION.SDK_INT < 29 || (locusId = notification.getLocusId()) == null) {
            return null;
        }
        return LocusIdCompat.toLocusIdCompat(locusId);
    }

    public static boolean getOngoing(@NonNull Notification notification) {
        return (notification.flags & 2) != 0;
    }

    public static boolean getOnlyAlertOnce(@NonNull Notification notification) {
        return (notification.flags & 8) != 0;
    }

    @NonNull
    public static List<Person> getPeople(@NonNull Notification notification) {
        String[] stringArray;
        ArrayList arrayList = new ArrayList();
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            ArrayList parcelableArrayList = notification.extras.getParcelableArrayList(EXTRA_PEOPLE_LIST);
            if (parcelableArrayList != null && !parcelableArrayList.isEmpty()) {
                Iterator it = parcelableArrayList.iterator();
                while (it.hasNext()) {
                    arrayList.add(Person.fromAndroidPerson((android.app.Person) it.next()));
                }
            }
        } else if (i2 >= 19 && (stringArray = notification.extras.getStringArray("android.people")) != null && stringArray.length != 0) {
            for (String str : stringArray) {
                arrayList.add(new Person.Builder().setUri(str).build());
            }
        }
        return arrayList;
    }

    @Nullable
    public static Notification getPublicVersion(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 21) {
            return notification.publicVersion;
        }
        return null;
    }

    @Nullable
    public static CharSequence getSettingsText(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getSettingsText();
        }
        return null;
    }

    @Nullable
    public static String getShortcutId(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getShortcutId();
        }
        return null;
    }

    @RequiresApi(19)
    public static boolean getShowWhen(@NonNull Notification notification) {
        return notification.extras.getBoolean("android.showWhen");
    }

    @Nullable
    public static String getSortKey(@NonNull Notification notification) {
        Bundle k;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 20) {
            return notification.getSortKey();
        }
        if (i2 >= 19) {
            k = notification.extras;
        } else {
            if (i2 < 16) {
                return null;
            }
            k = c.k(notification);
        }
        return k.getString("android.support.sortKey");
    }

    @Nullable
    @RequiresApi(19)
    public static CharSequence getSubText(@NonNull Notification notification) {
        return notification.extras.getCharSequence("android.subText");
    }

    public static long getTimeoutAfter(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification.getTimeoutAfter();
        }
        return 0L;
    }

    @RequiresApi(19)
    public static boolean getUsesChronometer(@NonNull Notification notification) {
        return notification.extras.getBoolean("android.showChronometer");
    }

    public static int getVisibility(@NonNull Notification notification) {
        if (Build.VERSION.SDK_INT >= 21) {
            return notification.visibility;
        }
        return 0;
    }

    public static boolean isGroupSummary(@NonNull Notification notification) {
        Bundle k;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 20) {
            return (notification.flags & 512) != 0;
        }
        if (i2 >= 19) {
            k = notification.extras;
        } else {
            if (i2 < 16) {
                return false;
            }
            k = c.k(notification);
        }
        return k.getBoolean("android.support.isGroupSummary");
    }
}
