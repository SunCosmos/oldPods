package androidx.media.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.widget.RemoteViews;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.app.BundleCompat;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;
import androidx.media.R;

/* loaded from: classes.dex */
public class NotificationCompat {

    /* loaded from: classes.dex */
    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        private void u(RemoteViews remoteViews) {
            remoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", this.a.getColor() != 0 ? this.a.getColor() : this.a.mContext.getResources().getColor(R.color.notification_material_background_media_default_color));
        }

        @Override // androidx.media.app.NotificationCompat.MediaStyle, androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT < 24) {
                super.apply(notificationBuilderWithBuilderAccessor);
                return;
            }
            Notification.Builder builder = notificationBuilderWithBuilderAccessor.getBuilder();
            Notification.DecoratedMediaCustomViewStyle decoratedMediaCustomViewStyle = new Notification.DecoratedMediaCustomViewStyle();
            o(decoratedMediaCustomViewStyle);
            builder.setStyle(decoratedMediaCustomViewStyle);
        }

        @Override // androidx.media.app.NotificationCompat.MediaStyle, androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 24) {
                return null;
            }
            RemoteViews bigContentView = this.a.getBigContentView() != null ? this.a.getBigContentView() : this.a.getContentView();
            if (bigContentView == null) {
                return null;
            }
            RemoteViews p = p();
            buildIntoRemoteViews(p, bigContentView);
            if (i2 >= 21) {
                u(p);
            }
            return p;
        }

        @Override // androidx.media.app.NotificationCompat.MediaStyle, androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 24) {
                return null;
            }
            boolean z = true;
            boolean z2 = this.a.getContentView() != null;
            if (i2 >= 21) {
                if (!z2 && this.a.getBigContentView() == null) {
                    z = false;
                }
                if (z) {
                    RemoteViews q = q();
                    if (z2) {
                        buildIntoRemoteViews(q, this.a.getContentView());
                    }
                    u(q);
                    return q;
                }
            } else {
                RemoteViews q2 = q();
                if (z2) {
                    buildIntoRemoteViews(q2, this.a.getContentView());
                    return q2;
                }
            }
            return null;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 24) {
                return null;
            }
            RemoteViews headsUpContentView = this.a.getHeadsUpContentView() != null ? this.a.getHeadsUpContentView() : this.a.getContentView();
            if (headsUpContentView == null) {
                return null;
            }
            RemoteViews p = p();
            buildIntoRemoteViews(p, headsUpContentView);
            if (i2 >= 21) {
                u(p);
            }
            return p;
        }

        @Override // androidx.media.app.NotificationCompat.MediaStyle
        int s(int i2) {
            return i2 <= 3 ? R.layout.notification_template_big_media_narrow_custom : R.layout.notification_template_big_media_custom;
        }

        @Override // androidx.media.app.NotificationCompat.MediaStyle
        int t() {
            return this.a.getContentView() != null ? R.layout.notification_template_media_custom : super.t();
        }
    }

    /* loaded from: classes.dex */
    public static class MediaStyle extends NotificationCompat.Style {
        int[] e = null;
        MediaSessionCompat.Token f;
        boolean g;
        PendingIntent h;

        public MediaStyle() {
        }

        public MediaStyle(NotificationCompat.Builder builder) {
            setBuilder(builder);
        }

        public static MediaSessionCompat.Token getMediaSession(Notification notification) {
            Bundle extras = androidx.core.app.NotificationCompat.getExtras(notification);
            if (extras == null) {
                return null;
            }
            if (Build.VERSION.SDK_INT >= 21) {
                Parcelable parcelable = extras.getParcelable("android.mediaSession");
                if (parcelable != null) {
                    return MediaSessionCompat.Token.fromToken(parcelable);
                }
                return null;
            }
            IBinder binder = BundleCompat.getBinder(extras, "android.mediaSession");
            if (binder == null) {
                return null;
            }
            Parcel obtain = Parcel.obtain();
            obtain.writeStrongBinder(binder);
            obtain.setDataPosition(0);
            MediaSessionCompat.Token createFromParcel = MediaSessionCompat.Token.CREATOR.createFromParcel(obtain);
            obtain.recycle();
            return createFromParcel;
        }

        private RemoteViews r(NotificationCompat.Action action) {
            boolean z = action.getActionIntent() == null;
            RemoteViews remoteViews = new RemoteViews(this.a.mContext.getPackageName(), R.layout.notification_media_action);
            int i2 = R.id.action0;
            remoteViews.setImageViewResource(i2, action.getIcon());
            if (!z) {
                remoteViews.setOnClickPendingIntent(i2, action.getActionIntent());
            }
            if (Build.VERSION.SDK_INT >= 15) {
                remoteViews.setContentDescription(i2, action.getTitle());
            }
            return remoteViews;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT < 21) {
                if (this.g) {
                    notificationBuilderWithBuilderAccessor.getBuilder().setOngoing(true);
                }
            } else {
                Notification.Builder builder = notificationBuilderWithBuilderAccessor.getBuilder();
                Notification.MediaStyle mediaStyle = new Notification.MediaStyle();
                o(mediaStyle);
                builder.setStyle(mediaStyle);
            }
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 21) {
                return null;
            }
            return p();
        }

        @Override // androidx.core.app.NotificationCompat.Style
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build.VERSION.SDK_INT >= 21) {
                return null;
            }
            return q();
        }

        @RequiresApi(21)
        Notification.MediaStyle o(Notification.MediaStyle mediaStyle) {
            int[] iArr = this.e;
            if (iArr != null) {
                mediaStyle.setShowActionsInCompactView(iArr);
            }
            MediaSessionCompat.Token token = this.f;
            if (token != null) {
                mediaStyle.setMediaSession((MediaSession.Token) token.getToken());
            }
            return mediaStyle;
        }

        RemoteViews p() {
            int min = Math.min(this.a.mActions.size(), 5);
            RemoteViews applyStandardTemplate = applyStandardTemplate(false, s(min), false);
            applyStandardTemplate.removeAllViews(R.id.media_actions);
            if (min > 0) {
                for (int i2 = 0; i2 < min; i2++) {
                    applyStandardTemplate.addView(R.id.media_actions, r(this.a.mActions.get(i2)));
                }
            }
            if (this.g) {
                int i3 = R.id.cancel_action;
                applyStandardTemplate.setViewVisibility(i3, 0);
                applyStandardTemplate.setInt(i3, "setAlpha", this.a.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
                applyStandardTemplate.setOnClickPendingIntent(i3, this.h);
            } else {
                applyStandardTemplate.setViewVisibility(R.id.cancel_action, 8);
            }
            return applyStandardTemplate;
        }

        RemoteViews q() {
            RemoteViews applyStandardTemplate = applyStandardTemplate(false, t(), true);
            int size = this.a.mActions.size();
            int[] iArr = this.e;
            int min = iArr == null ? 0 : Math.min(iArr.length, 3);
            applyStandardTemplate.removeAllViews(R.id.media_actions);
            if (min > 0) {
                for (int i2 = 0; i2 < min; i2++) {
                    if (i2 >= size) {
                        throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", Integer.valueOf(i2), Integer.valueOf(size - 1)));
                    }
                    applyStandardTemplate.addView(R.id.media_actions, r(this.a.mActions.get(this.e[i2])));
                }
            }
            if (this.g) {
                applyStandardTemplate.setViewVisibility(R.id.end_padder, 8);
                int i3 = R.id.cancel_action;
                applyStandardTemplate.setViewVisibility(i3, 0);
                applyStandardTemplate.setOnClickPendingIntent(i3, this.h);
                applyStandardTemplate.setInt(i3, "setAlpha", this.a.mContext.getResources().getInteger(R.integer.cancel_button_image_alpha));
            } else {
                applyStandardTemplate.setViewVisibility(R.id.end_padder, 0);
                applyStandardTemplate.setViewVisibility(R.id.cancel_action, 8);
            }
            return applyStandardTemplate;
        }

        int s(int i2) {
            return i2 <= 3 ? R.layout.notification_template_big_media_narrow : R.layout.notification_template_big_media;
        }

        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            this.h = pendingIntent;
            return this;
        }

        public MediaStyle setMediaSession(MediaSessionCompat.Token token) {
            this.f = token;
            return this;
        }

        public MediaStyle setShowActionsInCompactView(int... iArr) {
            this.e = iArr;
            return this;
        }

        public MediaStyle setShowCancelButton(boolean z) {
            if (Build.VERSION.SDK_INT < 21) {
                this.g = z;
            }
            return this;
        }

        int t() {
            return R.layout.notification_template_media;
        }
    }

    private NotificationCompat() {
    }
}
