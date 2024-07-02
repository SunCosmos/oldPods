package android.support.v4.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v4.provider.SelfDestructiveThread;
import android.support.v4.util.LruCache;
import android.support.v4.util.Preconditions;
import android.support.v4.util.SimpleArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: lib/Mus.dex */
public class FontsContractCompat {

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String PARCEL_FONT_RESULTS = "font_results";

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final String TAG = "FontsContractCompat";
    private static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);
    private static final int BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS = 10000;
    private static final SelfDestructiveThread sBackgroundThread = new SelfDestructiveThread("fonts", 10, BACKGROUND_THREAD_KEEP_ALIVE_DURATION_MS);
    private static final Object sLock = new Object();

    @GuardedBy("sLock")
    private static final SimpleArrayMap<String, ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>>> sPendingReplies = new SimpleArrayMap<>();
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() { // from class: android.support.v4.provider.FontsContractCompat.5
        AnonymousClass5() {
        }

        @Override // java.util.Comparator
        public int compare(byte[] bArr, byte[] bArr2) {
            if (bArr.length != bArr2.length) {
                return bArr.length - bArr2.length;
            }
            for (int i2 = 0; i2 < bArr.length; i2++) {
                if (bArr[i2] != bArr2[i2]) {
                    return bArr[i2] - bArr2[i2];
                }
            }
            return 0;
        }
    };

    /* loaded from: lib/Mus.dex */
    public static final class Columns implements BaseColumns {
        public static final String FILE_ID = "file_id";
        public static final String ITALIC = "font_italic";
        public static final String RESULT_CODE = "result_code";
        public static final int RESULT_CODE_FONT_NOT_FOUND = 1;
        public static final int RESULT_CODE_FONT_UNAVAILABLE = 2;
        public static final int RESULT_CODE_MALFORMED_QUERY = 3;
        public static final int RESULT_CODE_OK = 0;
        public static final String TTC_INDEX = "font_ttc_index";
        public static final String VARIATION_SETTINGS = "font_variation_settings";
        public static final String WEIGHT = "font_weight";
    }

    private FontsContractCompat() {
    }

    @NonNull
    public static TypefaceResult getFontInternal(Context context, FontRequest fontRequest, int i2) {
        try {
            FontFamilyResult fetchFonts = fetchFonts(context, null, fontRequest);
            if (fetchFonts.getStatusCode() == 0) {
                Typeface createFromFontInfo = TypefaceCompat.createFromFontInfo(context, null, fetchFonts.getFonts(), i2);
                return new TypefaceResult(createFromFontInfo, createFromFontInfo != null ? 0 : -3);
            }
            return new TypefaceResult(null, fetchFonts.getStatusCode() == 1 ? -2 : -3);
        } catch (PackageManager.NameNotFoundException e) {
            return new TypefaceResult(null, -1);
        }
    }

    /* loaded from: lib/Mus.dex */
    public static final class TypefaceResult {
        final int mResult;
        final Typeface mTypeface;

        TypefaceResult(@Nullable Typeface typeface, int i2) {
            this.mTypeface = typeface;
            this.mResult = i2;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void resetCache() {
        sTypefaceCache.evictAll();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Typeface getFontSync(Context context, FontRequest fontRequest, @Nullable ResourcesCompat.FontCallback fontCallback, @Nullable Handler handler, boolean z, int i2, int i3) {
        AnonymousClass2 anonymousClass2;
        String str = fontRequest.getIdentifier() + "-" + i3;
        Typeface typeface = sTypefaceCache.get(str);
        if (typeface != null) {
            if (fontCallback != null) {
                fontCallback.onFontRetrieved(typeface);
            }
            return typeface;
        }
        if (!z || i2 != -1) {
            AnonymousClass1 anonymousClass1 = new Callable<TypefaceResult>() { // from class: android.support.v4.provider.FontsContractCompat.1
                final /* synthetic */ Context val$context;
                final /* synthetic */ String val$id;
                final /* synthetic */ FontRequest val$request;
                final /* synthetic */ int val$style;

                AnonymousClass1(Context context2, FontRequest fontRequest2, int i32, String str2) {
                    context = context2;
                    fontRequest = fontRequest2;
                    i3 = i32;
                    str = str2;
                }

                @Override // java.util.concurrent.Callable
                public TypefaceResult call() throws Exception {
                    TypefaceResult fontInternal = FontsContractCompat.getFontInternal(context, fontRequest, i3);
                    if (fontInternal.mTypeface != null) {
                        FontsContractCompat.sTypefaceCache.put(str, fontInternal.mTypeface);
                    }
                    return fontInternal;
                }
            };
            if (z) {
                try {
                    return ((TypefaceResult) sBackgroundThread.postAndWait(anonymousClass1, i2)).mTypeface;
                } catch (InterruptedException e) {
                    return null;
                }
            }
            if (fontCallback == null) {
                anonymousClass2 = null;
            } else {
                anonymousClass2 = r14;
                AnonymousClass2 anonymousClass22 = new SelfDestructiveThread.ReplyCallback<TypefaceResult>() { // from class: android.support.v4.provider.FontsContractCompat.2
                    final /* synthetic */ Handler val$handler;

                    AnonymousClass2(Handler handler2) {
                        handler = handler2;
                    }

                    @Override // android.support.v4.provider.SelfDestructiveThread.ReplyCallback
                    public void onReply(TypefaceResult typefaceResult) {
                        if (typefaceResult == null) {
                            ResourcesCompat.FontCallback.this.callbackFailAsync(1, handler);
                        } else if (typefaceResult.mResult == 0) {
                            ResourcesCompat.FontCallback.this.callbackSuccessAsync(typefaceResult.mTypeface, handler);
                        } else {
                            ResourcesCompat.FontCallback.this.callbackFailAsync(typefaceResult.mResult, handler);
                        }
                    }
                };
            }
            AnonymousClass2 anonymousClass23 = anonymousClass2;
            synchronized (sLock) {
                if (sPendingReplies.containsKey(str2)) {
                    if (anonymousClass23 != null) {
                        sPendingReplies.get(str2).add(anonymousClass23);
                    }
                    return null;
                }
                if (anonymousClass23 != null) {
                    ArrayList<SelfDestructiveThread.ReplyCallback<TypefaceResult>> arrayList = new ArrayList<>();
                    arrayList.add(anonymousClass23);
                    sPendingReplies.put(str2, arrayList);
                }
                sBackgroundThread.postAndReply(anonymousClass1, new SelfDestructiveThread.ReplyCallback<TypefaceResult>() { // from class: android.support.v4.provider.FontsContractCompat.3
                    final /* synthetic */ String val$id;

                    AnonymousClass3(String str2) {
                        str = str2;
                    }

                    @Override // android.support.v4.provider.SelfDestructiveThread.ReplyCallback
                    public void onReply(TypefaceResult typefaceResult) {
                        synchronized (FontsContractCompat.sLock) {
                            ArrayList arrayList2 = (ArrayList) FontsContractCompat.sPendingReplies.get(str);
                            if (arrayList2 != null) {
                                FontsContractCompat.sPendingReplies.remove(str);
                                for (int i4 = 0; i4 < arrayList2.size(); i4++) {
                                    ((SelfDestructiveThread.ReplyCallback) arrayList2.get(i4)).onReply(typefaceResult);
                                }
                            }
                        }
                    }
                });
                return null;
            }
        }
        TypefaceResult fontInternal = getFontInternal(context2, fontRequest2, i32);
        if (fontCallback != null) {
            if (fontInternal.mResult == 0) {
                fontCallback.callbackSuccessAsync(fontInternal.mTypeface, handler2);
            } else {
                fontCallback.callbackFailAsync(fontInternal.mResult, handler2);
            }
        }
        return fontInternal.mTypeface;
    }

    /* renamed from: android.support.v4.provider.FontsContractCompat$1 */
    /* loaded from: lib/Mus.dex */
    public static class AnonymousClass1 implements Callable<TypefaceResult> {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$id;
        final /* synthetic */ FontRequest val$request;
        final /* synthetic */ int val$style;

        AnonymousClass1(Context context2, FontRequest fontRequest2, int i32, String str2) {
            context = context2;
            fontRequest = fontRequest2;
            i3 = i32;
            str = str2;
        }

        @Override // java.util.concurrent.Callable
        public TypefaceResult call() throws Exception {
            TypefaceResult fontInternal = FontsContractCompat.getFontInternal(context, fontRequest, i3);
            if (fontInternal.mTypeface != null) {
                FontsContractCompat.sTypefaceCache.put(str, fontInternal.mTypeface);
            }
            return fontInternal;
        }
    }

    /* renamed from: android.support.v4.provider.FontsContractCompat$2 */
    /* loaded from: lib/Mus.dex */
    public static class AnonymousClass2 implements SelfDestructiveThread.ReplyCallback<TypefaceResult> {
        final /* synthetic */ Handler val$handler;

        AnonymousClass2(Handler handler2) {
            handler = handler2;
        }

        @Override // android.support.v4.provider.SelfDestructiveThread.ReplyCallback
        public void onReply(TypefaceResult typefaceResult) {
            if (typefaceResult == null) {
                ResourcesCompat.FontCallback.this.callbackFailAsync(1, handler);
            } else if (typefaceResult.mResult == 0) {
                ResourcesCompat.FontCallback.this.callbackSuccessAsync(typefaceResult.mTypeface, handler);
            } else {
                ResourcesCompat.FontCallback.this.callbackFailAsync(typefaceResult.mResult, handler);
            }
        }
    }

    /* renamed from: android.support.v4.provider.FontsContractCompat$3 */
    /* loaded from: lib/Mus.dex */
    public static class AnonymousClass3 implements SelfDestructiveThread.ReplyCallback<TypefaceResult> {
        final /* synthetic */ String val$id;

        AnonymousClass3(String str2) {
            str = str2;
        }

        @Override // android.support.v4.provider.SelfDestructiveThread.ReplyCallback
        public void onReply(TypefaceResult typefaceResult) {
            synchronized (FontsContractCompat.sLock) {
                ArrayList arrayList2 = (ArrayList) FontsContractCompat.sPendingReplies.get(str);
                if (arrayList2 != null) {
                    FontsContractCompat.sPendingReplies.remove(str);
                    for (int i4 = 0; i4 < arrayList2.size(); i4++) {
                        ((SelfDestructiveThread.ReplyCallback) arrayList2.get(i4)).onReply(typefaceResult);
                    }
                }
            }
        }
    }

    /* loaded from: lib/Mus.dex */
    public static class FontInfo {
        private final boolean mItalic;
        private final int mResultCode;
        private final int mTtcIndex;
        private final Uri mUri;
        private final int mWeight;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public FontInfo(@NonNull Uri uri, @IntRange(from = 0) int i2, @IntRange(from = 1, to = 1000) int i3, boolean z, int i4) {
            this.mUri = (Uri) Preconditions.checkNotNull(uri);
            this.mTtcIndex = i2;
            this.mWeight = i3;
            this.mItalic = z;
            this.mResultCode = i4;
        }

        @NonNull
        public Uri getUri() {
            return this.mUri;
        }

        @IntRange(from = 0)
        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        @IntRange(from = 1, to = 1000)
        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }

        public int getResultCode() {
            return this.mResultCode;
        }
    }

    /* loaded from: lib/Mus.dex */
    public static class FontFamilyResult {
        public static final int STATUS_OK = 0;
        public static final int STATUS_UNEXPECTED_DATA_PROVIDED = 2;
        public static final int STATUS_WRONG_CERTIFICATES = 1;
        private final FontInfo[] mFonts;
        private final int mStatusCode;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        /* loaded from: lib/Mus.dex */
        @interface FontResultStatus {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public FontFamilyResult(int i2, @Nullable FontInfo[] fontInfoArr) {
            this.mStatusCode = i2;
            this.mFonts = fontInfoArr;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }

        public FontInfo[] getFonts() {
            return this.mFonts;
        }
    }

    /* loaded from: lib/Mus.dex */
    public static class FontRequestCallback {
        public static final int FAIL_REASON_FONT_LOAD_ERROR = -3;
        public static final int FAIL_REASON_FONT_NOT_FOUND = 1;
        public static final int FAIL_REASON_FONT_UNAVAILABLE = 2;
        public static final int FAIL_REASON_MALFORMED_QUERY = 3;
        public static final int FAIL_REASON_PROVIDER_NOT_FOUND = -1;
        public static final int FAIL_REASON_SECURITY_VIOLATION = -4;
        public static final int FAIL_REASON_WRONG_CERTIFICATES = -2;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static final int RESULT_OK = 0;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        /* loaded from: lib/Mus.dex */
        public @interface FontRequestFailReason {
        }

        public void onTypefaceRetrieved(Typeface typeface) {
        }

        public void onTypefaceRequestFailed(int i2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: android.support.v4.provider.FontsContractCompat$4 */
    /* loaded from: lib/Mus.dex */
    public static class AnonymousClass4 implements Runnable {
        final /* synthetic */ FontRequestCallback val$callback;
        final /* synthetic */ Handler val$callerThreadHandler;
        final /* synthetic */ Context val$context;
        final /* synthetic */ FontRequest val$request;

        AnonymousClass4(Context context, FontRequest fontRequest, Handler handler, FontRequestCallback fontRequestCallback) {
            context = context;
            fontRequest = fontRequest;
            handler2 = handler;
            fontRequestCallback = fontRequestCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                FontFamilyResult fetchFonts = FontsContractCompat.fetchFonts(context, null, fontRequest);
                if (fetchFonts.getStatusCode() != 0) {
                    switch (fetchFonts.getStatusCode()) {
                        case 1:
                            handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.2
                                AnonymousClass2() {
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-2);
                                }
                            });
                            return;
                        case 2:
                            handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.3
                                AnonymousClass3() {
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                            return;
                        default:
                            handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.4
                                RunnableC00014() {
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                            return;
                    }
                }
                FontInfo[] fonts = fetchFonts.getFonts();
                if (fonts == null || fonts.length == 0) {
                    handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.5
                        AnonymousClass5() {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            fontRequestCallback.onTypefaceRequestFailed(1);
                        }
                    });
                    return;
                }
                for (FontInfo fontInfo : fonts) {
                    if (fontInfo.getResultCode() != 0) {
                        int resultCode = fontInfo.getResultCode();
                        if (resultCode < 0) {
                            handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.6
                                AnonymousClass6() {
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(-3);
                                }
                            });
                            return;
                        } else {
                            handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.7
                                final /* synthetic */ int val$resultCode;

                                AnonymousClass7(int resultCode2) {
                                    resultCode = resultCode2;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    fontRequestCallback.onTypefaceRequestFailed(resultCode);
                                }
                            });
                            return;
                        }
                    }
                }
                Typeface buildTypeface = FontsContractCompat.buildTypeface(context, null, fonts);
                if (buildTypeface == null) {
                    handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.8
                        AnonymousClass8() {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            fontRequestCallback.onTypefaceRequestFailed(-3);
                        }
                    });
                } else {
                    handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.9
                        final /* synthetic */ Typeface val$typeface;

                        AnonymousClass9(Typeface buildTypeface2) {
                            buildTypeface = buildTypeface2;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            fontRequestCallback.onTypefaceRetrieved(buildTypeface);
                        }
                    });
                }
            } catch (PackageManager.NameNotFoundException e) {
                handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.1
                    AnonymousClass1() {
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        fontRequestCallback.onTypefaceRequestFailed(-1);
                    }
                });
            }
        }

        /* renamed from: android.support.v4.provider.FontsContractCompat$4$1 */
        /* loaded from: lib/Mus.dex */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.onTypefaceRequestFailed(-1);
            }
        }

        /* renamed from: android.support.v4.provider.FontsContractCompat$4$2 */
        /* loaded from: lib/Mus.dex */
        class AnonymousClass2 implements Runnable {
            AnonymousClass2() {
            }

            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.onTypefaceRequestFailed(-2);
            }
        }

        /* renamed from: android.support.v4.provider.FontsContractCompat$4$3 */
        /* loaded from: lib/Mus.dex */
        class AnonymousClass3 implements Runnable {
            AnonymousClass3() {
            }

            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.onTypefaceRequestFailed(-3);
            }
        }

        /* renamed from: android.support.v4.provider.FontsContractCompat$4$4 */
        /* loaded from: lib/Mus.dex */
        class RunnableC00014 implements Runnable {
            RunnableC00014() {
            }

            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.onTypefaceRequestFailed(-3);
            }
        }

        /* renamed from: android.support.v4.provider.FontsContractCompat$4$5 */
        /* loaded from: lib/Mus.dex */
        class AnonymousClass5 implements Runnable {
            AnonymousClass5() {
            }

            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.onTypefaceRequestFailed(1);
            }
        }

        /* renamed from: android.support.v4.provider.FontsContractCompat$4$6 */
        /* loaded from: lib/Mus.dex */
        class AnonymousClass6 implements Runnable {
            AnonymousClass6() {
            }

            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.onTypefaceRequestFailed(-3);
            }
        }

        /* renamed from: android.support.v4.provider.FontsContractCompat$4$7 */
        /* loaded from: lib/Mus.dex */
        class AnonymousClass7 implements Runnable {
            final /* synthetic */ int val$resultCode;

            AnonymousClass7(int resultCode2) {
                resultCode = resultCode2;
            }

            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.onTypefaceRequestFailed(resultCode);
            }
        }

        /* renamed from: android.support.v4.provider.FontsContractCompat$4$8 */
        /* loaded from: lib/Mus.dex */
        class AnonymousClass8 implements Runnable {
            AnonymousClass8() {
            }

            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.onTypefaceRequestFailed(-3);
            }
        }

        /* renamed from: android.support.v4.provider.FontsContractCompat$4$9 */
        /* loaded from: lib/Mus.dex */
        class AnonymousClass9 implements Runnable {
            final /* synthetic */ Typeface val$typeface;

            AnonymousClass9(Typeface buildTypeface2) {
                buildTypeface = buildTypeface2;
            }

            @Override // java.lang.Runnable
            public void run() {
                fontRequestCallback.onTypefaceRetrieved(buildTypeface);
            }
        }
    }

    public static void requestFont(@NonNull Context context, @NonNull FontRequest fontRequest, @NonNull FontRequestCallback fontRequestCallback, @NonNull Handler handler) {
        handler.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4
            final /* synthetic */ FontRequestCallback val$callback;
            final /* synthetic */ Handler val$callerThreadHandler;
            final /* synthetic */ Context val$context;
            final /* synthetic */ FontRequest val$request;

            AnonymousClass4(Context context2, FontRequest fontRequest2, Handler handler2, FontRequestCallback fontRequestCallback2) {
                context = context2;
                fontRequest = fontRequest2;
                handler2 = handler2;
                fontRequestCallback = fontRequestCallback2;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    FontFamilyResult fetchFonts = FontsContractCompat.fetchFonts(context, null, fontRequest);
                    if (fetchFonts.getStatusCode() != 0) {
                        switch (fetchFonts.getStatusCode()) {
                            case 1:
                                handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.2
                                    AnonymousClass2() {
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-2);
                                    }
                                });
                                return;
                            case 2:
                                handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.3
                                    AnonymousClass3() {
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-3);
                                    }
                                });
                                return;
                            default:
                                handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.4
                                    RunnableC00014() {
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-3);
                                    }
                                });
                                return;
                        }
                    }
                    FontInfo[] fonts = fetchFonts.getFonts();
                    if (fonts == null || fonts.length == 0) {
                        handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.5
                            AnonymousClass5() {
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                fontRequestCallback.onTypefaceRequestFailed(1);
                            }
                        });
                        return;
                    }
                    for (FontInfo fontInfo : fonts) {
                        if (fontInfo.getResultCode() != 0) {
                            int resultCode2 = fontInfo.getResultCode();
                            if (resultCode2 < 0) {
                                handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.6
                                    AnonymousClass6() {
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(-3);
                                    }
                                });
                                return;
                            } else {
                                handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.7
                                    final /* synthetic */ int val$resultCode;

                                    AnonymousClass7(int resultCode22) {
                                        resultCode = resultCode22;
                                    }

                                    @Override // java.lang.Runnable
                                    public void run() {
                                        fontRequestCallback.onTypefaceRequestFailed(resultCode);
                                    }
                                });
                                return;
                            }
                        }
                    }
                    Typeface buildTypeface2 = FontsContractCompat.buildTypeface(context, null, fonts);
                    if (buildTypeface2 == null) {
                        handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.8
                            AnonymousClass8() {
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                fontRequestCallback.onTypefaceRequestFailed(-3);
                            }
                        });
                    } else {
                        handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.9
                            final /* synthetic */ Typeface val$typeface;

                            AnonymousClass9(Typeface buildTypeface22) {
                                buildTypeface = buildTypeface22;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                fontRequestCallback.onTypefaceRetrieved(buildTypeface);
                            }
                        });
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    handler2.post(new Runnable() { // from class: android.support.v4.provider.FontsContractCompat.4.1
                        AnonymousClass1() {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            fontRequestCallback.onTypefaceRequestFailed(-1);
                        }
                    });
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$1 */
            /* loaded from: lib/Mus.dex */
            class AnonymousClass1 implements Runnable {
                AnonymousClass1() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-1);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$2 */
            /* loaded from: lib/Mus.dex */
            class AnonymousClass2 implements Runnable {
                AnonymousClass2() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-2);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$3 */
            /* loaded from: lib/Mus.dex */
            class AnonymousClass3 implements Runnable {
                AnonymousClass3() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$4 */
            /* loaded from: lib/Mus.dex */
            class RunnableC00014 implements Runnable {
                RunnableC00014() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$5 */
            /* loaded from: lib/Mus.dex */
            class AnonymousClass5 implements Runnable {
                AnonymousClass5() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(1);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$6 */
            /* loaded from: lib/Mus.dex */
            class AnonymousClass6 implements Runnable {
                AnonymousClass6() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$7 */
            /* loaded from: lib/Mus.dex */
            class AnonymousClass7 implements Runnable {
                final /* synthetic */ int val$resultCode;

                AnonymousClass7(int resultCode22) {
                    resultCode = resultCode22;
                }

                @Override // java.lang.Runnable
                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(resultCode);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$8 */
            /* loaded from: lib/Mus.dex */
            class AnonymousClass8 implements Runnable {
                AnonymousClass8() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    fontRequestCallback.onTypefaceRequestFailed(-3);
                }
            }

            /* renamed from: android.support.v4.provider.FontsContractCompat$4$9 */
            /* loaded from: lib/Mus.dex */
            class AnonymousClass9 implements Runnable {
                final /* synthetic */ Typeface val$typeface;

                AnonymousClass9(Typeface buildTypeface22) {
                    buildTypeface = buildTypeface22;
                }

                @Override // java.lang.Runnable
                public void run() {
                    fontRequestCallback.onTypefaceRetrieved(buildTypeface);
                }
            }
        });
    }

    @Nullable
    public static Typeface buildTypeface(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr) {
        return TypefaceCompat.createFromFontInfo(context, cancellationSignal, fontInfoArr, 0);
    }

    @RequiresApi(19)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Map<Uri, ByteBuffer> prepareFontData(Context context, FontInfo[] fontInfoArr, CancellationSignal cancellationSignal) {
        HashMap hashMap = new HashMap();
        for (FontInfo fontInfo : fontInfoArr) {
            if (fontInfo.getResultCode() == 0) {
                Uri uri = fontInfo.getUri();
                if (!hashMap.containsKey(uri)) {
                    hashMap.put(uri, TypefaceCompatUtil.mmap(context, cancellationSignal, uri));
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    @NonNull
    public static FontFamilyResult fetchFonts(@NonNull Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontRequest fontRequest) throws PackageManager.NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return new FontFamilyResult(1, null);
        }
        return new FontFamilyResult(0, getFontFromProvider(context, fontRequest, provider.authority, cancellationSignal));
    }

    @VisibleForTesting
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static ProviderInfo getProvider(@NonNull PackageManager packageManager, @NonNull FontRequest fontRequest, @Nullable Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
        }
        if (!resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        }
        List<byte[]> convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
        Collections.sort(convertToByteArrayList, sByteArrayComparator);
        List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
        for (int i2 = 0; i2 < certificates.size(); i2++) {
            ArrayList arrayList = new ArrayList(certificates.get(i2));
            Collections.sort(arrayList, sByteArrayComparator);
            if (equalsByteArrayList(convertToByteArrayList, arrayList)) {
                return resolveContentProvider;
            }
        }
        return null;
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    /* renamed from: android.support.v4.provider.FontsContractCompat$5 */
    /* loaded from: lib/Mus.dex */
    static class AnonymousClass5 implements Comparator<byte[]> {
        AnonymousClass5() {
        }

        @Override // java.util.Comparator
        public int compare(byte[] bArr, byte[] bArr2) {
            if (bArr.length != bArr2.length) {
                return bArr.length - bArr2.length;
            }
            for (int i2 = 0; i2 < bArr.length; i2++) {
                if (bArr[i2] != bArr2[i2]) {
                    return bArr[i2] - bArr2[i2];
                }
            }
            return 0;
        }
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (!Arrays.equals(list.get(i2), list2.get(i2))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    @VisibleForTesting
    @NonNull
    static FontInfo[] getFontFromProvider(Context context, FontRequest fontRequest, String str, CancellationSignal cancellationSignal) {
        Uri withAppendedId;
        ArrayList arrayList = new ArrayList();
        Uri build = new Uri.Builder().scheme("content").authority(str).build();
        Uri build2 = new Uri.Builder().scheme("content").authority(str).appendPath("file").build();
        Cursor cursor = null;
        try {
            if (Build.VERSION.SDK_INT > 16) {
                cursor = context.getContentResolver().query(build, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{fontRequest.getQuery()}, null, cancellationSignal);
            } else {
                cursor = context.getContentResolver().query(build, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{fontRequest.getQuery()}, null);
            }
            if (cursor != null && cursor.getCount() > 0) {
                int columnIndex = cursor.getColumnIndex("result_code");
                arrayList = new ArrayList();
                int columnIndex2 = cursor.getColumnIndex("_id");
                int columnIndex3 = cursor.getColumnIndex("file_id");
                int columnIndex4 = cursor.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursor.getColumnIndex("font_weight");
                int columnIndex6 = cursor.getColumnIndex("font_italic");
                while (cursor.moveToNext()) {
                    int i2 = columnIndex != -1 ? cursor.getInt(columnIndex) : 0;
                    int i3 = columnIndex4 != -1 ? cursor.getInt(columnIndex4) : 0;
                    if (columnIndex3 == -1) {
                        withAppendedId = ContentUris.withAppendedId(build, cursor.getLong(columnIndex2));
                    } else {
                        withAppendedId = ContentUris.withAppendedId(build2, cursor.getLong(columnIndex3));
                    }
                    arrayList.add(new FontInfo(withAppendedId, i3, columnIndex5 != -1 ? cursor.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursor.getInt(columnIndex6) == 1, i2));
                }
            }
            return (FontInfo[]) arrayList.toArray(new FontInfo[0]);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
