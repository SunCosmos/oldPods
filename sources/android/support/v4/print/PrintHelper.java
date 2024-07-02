package android.support.v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: lib/Mus.dex */
public final class PrintHelper {
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    private final PrintHelperVersionImpl mImpl;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: lib/Mus.dex */
    private @interface ColorMode {
    }

    /* loaded from: lib/Mus.dex */
    public interface OnPrintFinishCallback {
        void onFinish();
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: lib/Mus.dex */
    private @interface Orientation {
    }

    /* loaded from: lib/Mus.dex */
    interface PrintHelperVersionImpl {
        int getColorMode();

        int getOrientation();

        int getScaleMode();

        void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback);

        void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException;

        void setColorMode(int i2);

        void setOrientation(int i2);

        void setScaleMode(int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: lib/Mus.dex */
    private @interface ScaleMode {
    }

    public static boolean systemSupportsPrint() {
        return Build.VERSION.SDK_INT >= 19;
    }

    /* loaded from: lib/Mus.dex */
    private static final class PrintHelperStub implements PrintHelperVersionImpl {
        int mColorMode;
        int mOrientation;
        int mScaleMode;

        private PrintHelperStub() {
            this.mScaleMode = 2;
            this.mColorMode = 2;
            this.mOrientation = 1;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setScaleMode(int i2) {
            this.mScaleMode = i2;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getScaleMode() {
            return this.mScaleMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getColorMode() {
            return this.mColorMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setColorMode(int i2) {
            this.mColorMode = i2;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setOrientation(int i2) {
            this.mOrientation = i2;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getOrientation() {
            return this.mOrientation;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(19)
    /* loaded from: lib/Mus.dex */
    public static class PrintHelperApi19 implements PrintHelperVersionImpl {
        private static final String LOG_TAG = "PrintHelperApi19";
        private static final int MAX_PRINT_SIZE = 3500;
        final Context mContext;
        int mOrientation;
        BitmapFactory.Options mDecodeOptions = null;
        private final Object mLock = new Object();
        int mScaleMode = 2;
        int mColorMode = 2;
        protected boolean mPrintActivityRespectsOrientation = true;
        protected boolean mIsMinMarginsHandlingCorrect = true;

        PrintHelperApi19(Context context) {
            this.mContext = context;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setScaleMode(int i2) {
            this.mScaleMode = i2;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getScaleMode() {
            return this.mScaleMode;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setColorMode(int i2) {
            this.mColorMode = i2;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void setOrientation(int i2) {
            this.mOrientation = i2;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getOrientation() {
            if (this.mOrientation != 0) {
                return this.mOrientation;
            }
            return 1;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public int getColorMode() {
            return this.mColorMode;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isPortrait(Bitmap bitmap) {
            return bitmap.getWidth() <= bitmap.getHeight();
        }

        protected PrintAttributes.Builder copyAttributes(PrintAttributes printAttributes) {
            PrintAttributes.Builder minMargins = new PrintAttributes.Builder().setMediaSize(printAttributes.getMediaSize()).setResolution(printAttributes.getResolution()).setMinMargins(printAttributes.getMinMargins());
            if (printAttributes.getColorMode() != 0) {
                minMargins.setColorMode(printAttributes.getColorMode());
            }
            return minMargins;
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void printBitmap(final String str, final Bitmap bitmap, final OnPrintFinishCallback onPrintFinishCallback) {
            PrintAttributes.MediaSize mediaSize;
            if (bitmap != null) {
                final int i2 = this.mScaleMode;
                PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
                if (isPortrait(bitmap)) {
                    mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
                } else {
                    mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
                }
                printManager.print(str, new PrintDocumentAdapter() { // from class: android.support.v4.print.PrintHelper.PrintHelperApi19.1
                    private PrintAttributes mAttributes;

                    @Override // android.print.PrintDocumentAdapter
                    public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
                        this.mAttributes = printAttributes2;
                        layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(str).setContentType(1).setPageCount(1).build(), !printAttributes2.equals(printAttributes));
                    }

                    @Override // android.print.PrintDocumentAdapter
                    public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
                        PrintHelperApi19.this.writeBitmap(this.mAttributes, i2, bitmap, parcelFileDescriptor, cancellationSignal, writeResultCallback);
                    }

                    @Override // android.print.PrintDocumentAdapter
                    public void onFinish() {
                        if (onPrintFinishCallback != null) {
                            onPrintFinishCallback.onFinish();
                        }
                    }
                }, new PrintAttributes.Builder().setMediaSize(mediaSize).setColorMode(this.mColorMode).build());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Matrix getMatrix(int i2, int i3, RectF rectF, int i4) {
            float min;
            Matrix matrix = new Matrix();
            float width = rectF.width() / i2;
            if (i4 == 2) {
                min = Math.max(width, rectF.height() / i3);
            } else {
                min = Math.min(width, rectF.height() / i3);
            }
            matrix.postScale(min, min);
            matrix.postTranslate((rectF.width() - (i2 * min)) / 2.0f, (rectF.height() - (i3 * min)) / 2.0f);
            return matrix;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference failed for: r8v6, types: [android.support.v4.print.PrintHelper$PrintHelperApi19$2] */
        public void writeBitmap(final PrintAttributes printAttributes, final int i2, final Bitmap bitmap, final ParcelFileDescriptor parcelFileDescriptor, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PrintAttributes build;
            if (this.mIsMinMarginsHandlingCorrect) {
                build = printAttributes;
            } else {
                build = copyAttributes(printAttributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
            }
            final PrintAttributes printAttributes2 = build;
            new AsyncTask<Void, Void, Throwable>() { // from class: android.support.v4.print.PrintHelper.PrintHelperApi19.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Throwable doInBackground(Void... voidArr) {
                    RectF rectF;
                    try {
                        if (cancellationSignal.isCanceled()) {
                            return null;
                        }
                        PrintedPdfDocument printedPdfDocument = new PrintedPdfDocument(PrintHelperApi19.this.mContext, printAttributes2);
                        Bitmap convertBitmapForColorMode = PrintHelperApi19.this.convertBitmapForColorMode(bitmap, printAttributes2.getColorMode());
                        if (cancellationSignal.isCanceled()) {
                            return null;
                        }
                        try {
                            PdfDocument.Page startPage = printedPdfDocument.startPage(1);
                            if (PrintHelperApi19.this.mIsMinMarginsHandlingCorrect) {
                                rectF = new RectF(startPage.getInfo().getContentRect());
                            } else {
                                PrintedPdfDocument printedPdfDocument2 = new PrintedPdfDocument(PrintHelperApi19.this.mContext, printAttributes);
                                PdfDocument.Page startPage2 = printedPdfDocument2.startPage(1);
                                rectF = new RectF(startPage2.getInfo().getContentRect());
                                printedPdfDocument2.finishPage(startPage2);
                                printedPdfDocument2.close();
                            }
                            Matrix matrix = PrintHelperApi19.this.getMatrix(convertBitmapForColorMode.getWidth(), convertBitmapForColorMode.getHeight(), rectF, i2);
                            if (!PrintHelperApi19.this.mIsMinMarginsHandlingCorrect) {
                                matrix.postTranslate(rectF.left, rectF.top);
                                startPage.getCanvas().clipRect(rectF);
                            }
                            startPage.getCanvas().drawBitmap(convertBitmapForColorMode, matrix, null);
                            printedPdfDocument.finishPage(startPage);
                            if (!cancellationSignal.isCanceled()) {
                                printedPdfDocument.writeTo(new FileOutputStream(parcelFileDescriptor.getFileDescriptor()));
                                printedPdfDocument.close();
                                if (parcelFileDescriptor != null) {
                                    try {
                                        parcelFileDescriptor.close();
                                    } catch (IOException e) {
                                    }
                                }
                                if (convertBitmapForColorMode != bitmap) {
                                    convertBitmapForColorMode.recycle();
                                }
                                return null;
                            }
                            printedPdfDocument.close();
                            if (parcelFileDescriptor != null) {
                                try {
                                    parcelFileDescriptor.close();
                                } catch (IOException e2) {
                                }
                            }
                            if (convertBitmapForColorMode != bitmap) {
                                convertBitmapForColorMode.recycle();
                            }
                            return null;
                        } catch (Throwable th) {
                            printedPdfDocument.close();
                            if (parcelFileDescriptor != null) {
                                try {
                                    parcelFileDescriptor.close();
                                } catch (IOException e3) {
                                }
                            }
                            if (convertBitmapForColorMode != bitmap) {
                                convertBitmapForColorMode.recycle();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        return th2;
                    }
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(Throwable th) {
                    if (cancellationSignal.isCanceled()) {
                        writeResultCallback.onWriteCancelled();
                    } else if (th == null) {
                        writeResultCallback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                    } else {
                        Log.e(PrintHelperApi19.LOG_TAG, "Error writing printed content", th);
                        writeResultCallback.onWriteFailed(null);
                    }
                }
            }.execute(new Void[0]);
        }

        @Override // android.support.v4.print.PrintHelper.PrintHelperVersionImpl
        public void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(str, uri, onPrintFinishCallback, this.mScaleMode);
            PrintManager printManager = (PrintManager) this.mContext.getSystemService("print");
            PrintAttributes.Builder builder = new PrintAttributes.Builder();
            builder.setColorMode(this.mColorMode);
            if (this.mOrientation == 1 || this.mOrientation == 0) {
                builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
            } else if (this.mOrientation == 2) {
                builder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
            }
            printManager.print(str, anonymousClass3, builder.build());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: android.support.v4.print.PrintHelper$PrintHelperApi19$3, reason: invalid class name */
        /* loaded from: lib/Mus.dex */
        public class AnonymousClass3 extends PrintDocumentAdapter {
            private PrintAttributes mAttributes;
            Bitmap mBitmap = null;
            AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;
            final /* synthetic */ OnPrintFinishCallback val$callback;
            final /* synthetic */ int val$fittingMode;
            final /* synthetic */ Uri val$imageFile;
            final /* synthetic */ String val$jobName;

            AnonymousClass3(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback, int i2) {
                this.val$jobName = str;
                this.val$imageFile = uri;
                this.val$callback = onPrintFinishCallback;
                this.val$fittingMode = i2;
            }

            /* JADX WARN: Type inference failed for: r9v2, types: [android.support.v4.print.PrintHelper$PrintHelperApi19$3$1] */
            @Override // android.print.PrintDocumentAdapter
            public void onLayout(final PrintAttributes printAttributes, final PrintAttributes printAttributes2, final CancellationSignal cancellationSignal, final PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
                synchronized (this) {
                    this.mAttributes = printAttributes2;
                }
                if (cancellationSignal.isCanceled()) {
                    layoutResultCallback.onLayoutCancelled();
                } else if (this.mBitmap != null) {
                    layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.val$jobName).setContentType(1).setPageCount(1).build(), !printAttributes2.equals(printAttributes));
                } else {
                    this.mLoadBitmap = new AsyncTask<Uri, Boolean, Bitmap>() { // from class: android.support.v4.print.PrintHelper.PrintHelperApi19.3.1
                        @Override // android.os.AsyncTask
                        protected void onPreExecute() {
                            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: android.support.v4.print.PrintHelper.PrintHelperApi19.3.1.1
                                @Override // android.os.CancellationSignal.OnCancelListener
                                public void onCancel() {
                                    AnonymousClass3.this.cancelLoad();
                                    cancel(false);
                                }
                            });
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // android.os.AsyncTask
                        public Bitmap doInBackground(Uri... uriArr) {
                            try {
                                return PrintHelperApi19.this.loadConstrainedBitmap(AnonymousClass3.this.val$imageFile);
                            } catch (FileNotFoundException e) {
                                return null;
                            }
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // android.os.AsyncTask
                        public void onPostExecute(Bitmap bitmap) {
                            PrintAttributes.MediaSize mediaSize;
                            Bitmap bitmap2 = bitmap;
                            super.onPostExecute((AnonymousClass1) bitmap2);
                            if (bitmap2 != null && (!PrintHelperApi19.this.mPrintActivityRespectsOrientation || PrintHelperApi19.this.mOrientation == 0)) {
                                synchronized (this) {
                                    mediaSize = AnonymousClass3.this.mAttributes.getMediaSize();
                                }
                                if (mediaSize != null && mediaSize.isPortrait() != PrintHelperApi19.isPortrait(bitmap2)) {
                                    Matrix matrix = new Matrix();
                                    matrix.postRotate(90.0f);
                                    bitmap2 = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix, true);
                                }
                            }
                            AnonymousClass3.this.mBitmap = bitmap2;
                            if (bitmap2 != null) {
                                layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(AnonymousClass3.this.val$jobName).setContentType(1).setPageCount(1).build(), !printAttributes2.equals(printAttributes));
                            } else {
                                layoutResultCallback.onLayoutFailed(null);
                            }
                            AnonymousClass3.this.mLoadBitmap = null;
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // android.os.AsyncTask
                        public void onCancelled(Bitmap bitmap) {
                            layoutResultCallback.onLayoutCancelled();
                            AnonymousClass3.this.mLoadBitmap = null;
                        }
                    }.execute(new Uri[0]);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void cancelLoad() {
                synchronized (PrintHelperApi19.this.mLock) {
                    if (PrintHelperApi19.this.mDecodeOptions != null) {
                        PrintHelperApi19.this.mDecodeOptions.requestCancelDecode();
                        PrintHelperApi19.this.mDecodeOptions = null;
                    }
                }
            }

            @Override // android.print.PrintDocumentAdapter
            public void onFinish() {
                super.onFinish();
                cancelLoad();
                if (this.mLoadBitmap != null) {
                    this.mLoadBitmap.cancel(true);
                }
                if (this.val$callback != null) {
                    this.val$callback.onFinish();
                }
                if (this.mBitmap != null) {
                    this.mBitmap.recycle();
                    this.mBitmap = null;
                }
            }

            @Override // android.print.PrintDocumentAdapter
            public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
                PrintHelperApi19.this.writeBitmap(this.mAttributes, this.val$fittingMode, this.mBitmap, parcelFileDescriptor, cancellationSignal, writeResultCallback);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Bitmap loadConstrainedBitmap(Uri uri) throws FileNotFoundException {
            int i2;
            BitmapFactory.Options options;
            if (uri == null || this.mContext == null) {
                throw new IllegalArgumentException("bad argument to getScaledBitmap");
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inJustDecodeBounds = true;
            loadBitmap(uri, options2);
            int i3 = options2.outWidth;
            int i4 = options2.outHeight;
            if (i3 <= 0 || i4 <= 0) {
                return null;
            }
            int max = Math.max(i3, i4);
            int i5 = 1;
            while (true) {
                i2 = i5;
                if (max <= MAX_PRINT_SIZE) {
                    break;
                }
                max >>>= 1;
                i5 = i2 << 1;
            }
            if (i2 <= 0 || 0 >= Math.min(i3, i4) / i2) {
                return null;
            }
            synchronized (this.mLock) {
                this.mDecodeOptions = new BitmapFactory.Options();
                this.mDecodeOptions.inMutable = true;
                this.mDecodeOptions.inSampleSize = i2;
                options = this.mDecodeOptions;
            }
            try {
                Bitmap loadBitmap = loadBitmap(uri, options);
                synchronized (this.mLock) {
                    this.mDecodeOptions = null;
                }
                return loadBitmap;
            } catch (Throwable th) {
                synchronized (this.mLock) {
                    this.mDecodeOptions = null;
                    throw th;
                }
            }
        }

        private Bitmap loadBitmap(Uri uri, BitmapFactory.Options options) throws FileNotFoundException {
            if (uri == null || this.mContext == null) {
                throw new IllegalArgumentException("bad argument to loadBitmap");
            }
            InputStream inputStream = null;
            try {
                inputStream = this.mContext.getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        Log.w(LOG_TAG, "close fail ", e);
                    }
                }
                return decodeStream;
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        Log.w(LOG_TAG, "close fail ", e2);
                    }
                }
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Bitmap convertBitmapForColorMode(Bitmap bitmap, int i2) {
            if (i2 != 1) {
                return bitmap;
            }
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
            canvas.setBitmap(null);
            return createBitmap;
        }
    }

    @RequiresApi(20)
    /* loaded from: lib/Mus.dex */
    private static class PrintHelperApi20 extends PrintHelperApi19 {
        PrintHelperApi20(Context context) {
            super(context);
            this.mPrintActivityRespectsOrientation = false;
        }
    }

    @RequiresApi(23)
    /* loaded from: lib/Mus.dex */
    private static class PrintHelperApi23 extends PrintHelperApi20 {
        @Override // android.support.v4.print.PrintHelper.PrintHelperApi19
        protected PrintAttributes.Builder copyAttributes(PrintAttributes printAttributes) {
            PrintAttributes.Builder copyAttributes = super.copyAttributes(printAttributes);
            if (printAttributes.getDuplexMode() != 0) {
                copyAttributes.setDuplexMode(printAttributes.getDuplexMode());
            }
            return copyAttributes;
        }

        PrintHelperApi23(Context context) {
            super(context);
            this.mIsMinMarginsHandlingCorrect = false;
        }
    }

    @RequiresApi(24)
    /* loaded from: lib/Mus.dex */
    private static class PrintHelperApi24 extends PrintHelperApi23 {
        PrintHelperApi24(Context context) {
            super(context);
            this.mIsMinMarginsHandlingCorrect = true;
            this.mPrintActivityRespectsOrientation = true;
        }
    }

    public PrintHelper(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.mImpl = new PrintHelperApi24(context);
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            this.mImpl = new PrintHelperApi23(context);
            return;
        }
        if (Build.VERSION.SDK_INT >= 20) {
            this.mImpl = new PrintHelperApi20(context);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.mImpl = new PrintHelperApi19(context);
        } else {
            this.mImpl = new PrintHelperStub();
        }
    }

    public void setScaleMode(int i2) {
        this.mImpl.setScaleMode(i2);
    }

    public int getScaleMode() {
        return this.mImpl.getScaleMode();
    }

    public void setColorMode(int i2) {
        this.mImpl.setColorMode(i2);
    }

    public int getColorMode() {
        return this.mImpl.getColorMode();
    }

    public void setOrientation(int i2) {
        this.mImpl.setOrientation(i2);
    }

    public int getOrientation() {
        return this.mImpl.getOrientation();
    }

    public void printBitmap(String str, Bitmap bitmap) {
        this.mImpl.printBitmap(str, bitmap, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
        this.mImpl.printBitmap(str, bitmap, onPrintFinishCallback);
    }

    public void printBitmap(String str, Uri uri) throws FileNotFoundException {
        this.mImpl.printBitmap(str, uri, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException {
        this.mImpl.printBitmap(str, uri, onPrintFinishCallback);
    }
}
