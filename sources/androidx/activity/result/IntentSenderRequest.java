package androidx.activity.result;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes.dex */
public final class IntentSenderRequest implements Parcelable {

    @NonNull
    public static final Parcelable.Creator<IntentSenderRequest> CREATOR = new a();

    @NonNull
    private final IntentSender a;

    @Nullable
    private final Intent b;

    /* renamed from: c, reason: collision with root package name */
    private final int f15c;

    /* renamed from: d, reason: collision with root package name */
    private final int f16d;

    /* loaded from: classes.dex */
    public static final class Builder {
        private IntentSender a;
        private Intent b;

        /* renamed from: c, reason: collision with root package name */
        private int f17c;

        /* renamed from: d, reason: collision with root package name */
        private int f18d;

        public Builder(@NonNull PendingIntent pendingIntent) {
            this(pendingIntent.getIntentSender());
        }

        public Builder(@NonNull IntentSender intentSender) {
            this.a = intentSender;
        }

        @NonNull
        public IntentSenderRequest build() {
            return new IntentSenderRequest(this.a, this.b, this.f17c, this.f18d);
        }

        @NonNull
        public Builder setFillInIntent(@Nullable Intent intent) {
            this.b = intent;
            return this;
        }

        @NonNull
        public Builder setFlags(int i2, int i3) {
            this.f18d = i2;
            this.f17c = i3;
            return this;
        }
    }

    /* loaded from: classes.dex */
    class a implements Parcelable.Creator<IntentSenderRequest> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public IntentSenderRequest createFromParcel(Parcel parcel) {
            return new IntentSenderRequest(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public IntentSenderRequest[] newArray(int i2) {
            return new IntentSenderRequest[i2];
        }
    }

    IntentSenderRequest(@NonNull IntentSender intentSender, @Nullable Intent intent, int i2, int i3) {
        this.a = intentSender;
        this.b = intent;
        this.f15c = i2;
        this.f16d = i3;
    }

    IntentSenderRequest(@NonNull Parcel parcel) {
        this.a = (IntentSender) parcel.readParcelable(IntentSender.class.getClassLoader());
        this.b = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
        this.f15c = parcel.readInt();
        this.f16d = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Nullable
    public Intent getFillInIntent() {
        return this.b;
    }

    public int getFlagsMask() {
        return this.f15c;
    }

    public int getFlagsValues() {
        return this.f16d;
    }

    @NonNull
    public IntentSender getIntentSender() {
        return this.a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i2) {
        parcel.writeParcelable(this.a, i2);
        parcel.writeParcelable(this.b, i2);
        parcel.writeInt(this.f15c);
        parcel.writeInt(this.f16d);
    }
}
