package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.provider.FontsContractCompat;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RequiresApi(14)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: lib/Mus.dex */
public class TypefaceCompatBaseImpl implements TypefaceCompat.TypefaceCompatImpl {
    private static final String CACHE_FILE_PREFIX = "cached_font_";
    private static final String TAG = "TypefaceCompatBaseImpl";

    /* loaded from: lib/Mus.dex */
    public interface StyleExtractor<T> {
        int getWeight(T t);

        boolean isItalic(T t);
    }

    private static <T> T findBestFont(T[] tArr, int i2, StyleExtractor<T> styleExtractor) {
        int i3 = (i2 & 1) == 0 ? 400 : 700;
        boolean z = (i2 & 2) != 0;
        T t = null;
        int i4 = Integer.MAX_VALUE;
        for (T t2 : tArr) {
            int abs = (Math.abs(styleExtractor.getWeight(t2) - i3) * 2) + (styleExtractor.isItalic(t2) == z ? 0 : 1);
            if (t == null || i4 > abs) {
                t = t2;
                i4 = abs;
            }
        }
        return t;
    }

    /* renamed from: android.support.v4.graphics.TypefaceCompatBaseImpl$1 */
    /* loaded from: lib/Mus.dex */
    public class AnonymousClass1 implements StyleExtractor<FontsContractCompat.FontInfo> {
        AnonymousClass1() {
        }

        @Override // android.support.v4.graphics.TypefaceCompatBaseImpl.StyleExtractor
        public int getWeight(FontsContractCompat.FontInfo fontInfo) {
            return fontInfo.getWeight();
        }

        @Override // android.support.v4.graphics.TypefaceCompatBaseImpl.StyleExtractor
        public boolean isItalic(FontsContractCompat.FontInfo fontInfo) {
            return fontInfo.isItalic();
        }
    }

    public FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] fontInfoArr, int i2) {
        return (FontsContractCompat.FontInfo) findBestFont(fontInfoArr, i2, new StyleExtractor<FontsContractCompat.FontInfo>() { // from class: android.support.v4.graphics.TypefaceCompatBaseImpl.1
            AnonymousClass1() {
            }

            @Override // android.support.v4.graphics.TypefaceCompatBaseImpl.StyleExtractor
            public int getWeight(FontsContractCompat.FontInfo fontInfo) {
                return fontInfo.getWeight();
            }

            @Override // android.support.v4.graphics.TypefaceCompatBaseImpl.StyleExtractor
            public boolean isItalic(FontsContractCompat.FontInfo fontInfo) {
                return fontInfo.isItalic();
            }
        });
    }

    public Typeface createFromInputStream(Context context, InputStream inputStream) {
        File tempFile = TypefaceCompatUtil.getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (TypefaceCompatUtil.copyToFile(tempFile, inputStream)) {
                return Typeface.createFromFile(tempFile.getPath());
            }
            return null;
        } catch (RuntimeException e) {
            return null;
        } finally {
            tempFile.delete();
        }
    }

    @Override // android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontsContractCompat.FontInfo[] fontInfoArr, int i2) {
        if (fontInfoArr.length < 1) {
            return null;
        }
        InputStream inputStream = null;
        try {
            inputStream = context.getContentResolver().openInputStream(findBestInfo(fontInfoArr, i2).getUri());
            Typeface createFromInputStream = createFromInputStream(context, inputStream);
            TypefaceCompatUtil.closeQuietly(inputStream);
            return createFromInputStream;
        } catch (IOException e) {
            TypefaceCompatUtil.closeQuietly(inputStream);
            return null;
        } catch (Throwable th) {
            TypefaceCompatUtil.closeQuietly(inputStream);
            throw th;
        }
    }

    /* renamed from: android.support.v4.graphics.TypefaceCompatBaseImpl$2 */
    /* loaded from: lib/Mus.dex */
    public class AnonymousClass2 implements StyleExtractor<FontResourcesParserCompat.FontFileResourceEntry> {
        AnonymousClass2() {
        }

        @Override // android.support.v4.graphics.TypefaceCompatBaseImpl.StyleExtractor
        public int getWeight(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
            return fontFileResourceEntry.getWeight();
        }

        @Override // android.support.v4.graphics.TypefaceCompatBaseImpl.StyleExtractor
        public boolean isItalic(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
            return fontFileResourceEntry.isItalic();
        }
    }

    private FontResourcesParserCompat.FontFileResourceEntry findBestEntry(FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, int i2) {
        return (FontResourcesParserCompat.FontFileResourceEntry) findBestFont(fontFamilyFilesResourceEntry.getEntries(), i2, new StyleExtractor<FontResourcesParserCompat.FontFileResourceEntry>() { // from class: android.support.v4.graphics.TypefaceCompatBaseImpl.2
            AnonymousClass2() {
            }

            @Override // android.support.v4.graphics.TypefaceCompatBaseImpl.StyleExtractor
            public int getWeight(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
                return fontFileResourceEntry.getWeight();
            }

            @Override // android.support.v4.graphics.TypefaceCompatBaseImpl.StyleExtractor
            public boolean isItalic(FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry) {
                return fontFileResourceEntry.isItalic();
            }
        });
    }

    @Override // android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    @Nullable
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i2) {
        FontResourcesParserCompat.FontFileResourceEntry findBestEntry = findBestEntry(fontFamilyFilesResourceEntry, i2);
        if (findBestEntry == null) {
            return null;
        }
        return TypefaceCompat.createFromResourcesFontFile(context, resources, findBestEntry.getResourceId(), findBestEntry.getFileName(), i2);
    }

    @Override // android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i2, String str, int i3) {
        File tempFile = TypefaceCompatUtil.getTempFile(context);
        if (tempFile == null) {
            return null;
        }
        try {
            if (TypefaceCompatUtil.copyToFile(tempFile, resources, i2)) {
                return Typeface.createFromFile(tempFile.getPath());
            }
            return null;
        } catch (RuntimeException e) {
            return null;
        } finally {
            tempFile.delete();
        }
    }
}
