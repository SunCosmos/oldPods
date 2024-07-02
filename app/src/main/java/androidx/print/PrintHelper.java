package androidx.print;

import android.annotation.SuppressLint;
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
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public final class PrintHelper {

    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_COLOR = 2;

    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    static final boolean g;
    static final boolean h;
    final Context a;
    BitmapFactory.Options b = null;

    /* renamed from: c, reason: collision with root package name */
    final Object f885c = new Object();

    /* renamed from: d, reason: collision with root package name */
    int f886d = 2;
    int e = 2;
    int f = 1;

    /* loaded from: classes.dex */
    public interface OnPrintFinishCallback {
        void onFinish();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends AsyncTask<Void, Void, Throwable> {
        final /* synthetic */ CancellationSignal a;
        final /* synthetic */ PrintAttributes b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Bitmap f887c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ PrintAttributes f888d;
        final /* synthetic */ int e;
        final /* synthetic */ ParcelFileDescriptor f;
        final /* synthetic */ PrintDocumentAdapter.WriteResultCallback g;

        a(CancellationSignal cancellationSignal, PrintAttributes printAttributes, Bitmap bitmap, PrintAttributes printAttributes2, int i2, ParcelFileDescriptor parcelFileDescriptor, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            this.a = cancellationSignal;
            this.b = printAttributes;
            this.f887c = bitmap;
            this.f888d = printAttributes2;
            this.e = i2;
            this.f = parcelFileDescriptor;
            this.g = writeResultCallback;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Throwable doInBackground(Void... voidArr) {
            RectF rectF;
            try {
                if (this.a.isCanceled()) {
                    return null;
                }
                PrintedPdfDocument printedPdfDocument = new PrintedPdfDocument(PrintHelper.this.a, this.b);
                Bitmap a = PrintHelper.a(this.f887c, this.b.getColorMode());
                if (this.a.isCanceled()) {
                    return null;
                }
                try {
                    PdfDocument.Page startPage = printedPdfDocument.startPage(1);
                    boolean z = PrintHelper.h;
                    if (z) {
                        rectF = new RectF(startPage.getInfo().getContentRect());
                    } else {
                        PrintedPdfDocument printedPdfDocument2 = new PrintedPdfDocument(PrintHelper.this.a, this.f888d);
                        PdfDocument.Page startPage2 = printedPdfDocument2.startPage(1);
                        RectF rectF2 = new RectF(startPage2.getInfo().getContentRect());
                        printedPdfDocument2.finishPage(startPage2);
                        printedPdfDocument2.close();
                        rectF = rectF2;
                    }
                    Matrix c2 = PrintHelper.c(a.getWidth(), a.getHeight(), rectF, this.e);
                    if (!z) {
                        c2.postTranslate(rectF.left, rectF.top);
                        startPage.getCanvas().clipRect(rectF);
                    }
                    startPage.getCanvas().drawBitmap(a, c2, null);
                    printedPdfDocument.finishPage(startPage);
                    if (this.a.isCanceled()) {
                        printedPdfDocument.close();
                        ParcelFileDescriptor parcelFileDescriptor = this.f;
                        if (parcelFileDescriptor != null) {
                            try {
                                parcelFileDescriptor.close();
                            } catch (IOException unused) {
                            }
                        }
                        if (a != this.f887c) {
                            a.recycle();
                        }
                        return null;
                    }
                    printedPdfDocument.writeTo(new FileOutputStream(this.f.getFileDescriptor()));
                    printedPdfDocument.close();
                    ParcelFileDescriptor parcelFileDescriptor2 = this.f;
                    if (parcelFileDescriptor2 != null) {
                        try {
                            parcelFileDescriptor2.close();
                        } catch (IOException unused2) {
                        }
                    }
                    if (a != this.f887c) {
                        a.recycle();
                    }
                    return null;
                } finally {
                }
            } catch (Throwable th) {
                return th;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Throwable th) {
            if (this.a.isCanceled()) {
                this.g.onWriteCancelled();
            } else if (th == null) {
                this.g.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
            } else {
                Log.e("PrintHelper", "Error writing printed content", th);
                this.g.onWriteFailed(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(19)
    /* loaded from: classes.dex */
    public class b extends PrintDocumentAdapter {
        private final String a;
        private final int b;

        /* renamed from: c, reason: collision with root package name */
        private final Bitmap f889c;

        /* renamed from: d, reason: collision with root package name */
        private final OnPrintFinishCallback f890d;
        private PrintAttributes e;

        b(String str, int i2, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback) {
            this.a = str;
            this.b = i2;
            this.f889c = bitmap;
            this.f890d = onPrintFinishCallback;
        }

        @Override // android.print.PrintDocumentAdapter
        public void onFinish() {
            OnPrintFinishCallback onPrintFinishCallback = this.f890d;
            if (onPrintFinishCallback != null) {
                onPrintFinishCallback.onFinish();
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            this.e = printAttributes2;
            layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.a).setContentType(1).setPageCount(1).build(), !printAttributes2.equals(printAttributes));
        }

        @Override // android.print.PrintDocumentAdapter
        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PrintHelper.this.g(this.e, this.b, this.f889c, parcelFileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(19)
    /* loaded from: classes.dex */
    public class c extends PrintDocumentAdapter {
        final String a;
        final Uri b;

        /* renamed from: c, reason: collision with root package name */
        final OnPrintFinishCallback f891c;

        /* renamed from: d, reason: collision with root package name */
        final int f892d;
        PrintAttributes e;
        AsyncTask<Uri, Boolean, Bitmap> f;
        Bitmap g = null;

        /* loaded from: classes.dex */
        class a extends AsyncTask<Uri, Boolean, Bitmap> {
            final /* synthetic */ CancellationSignal a;
            final /* synthetic */ PrintAttributes b;

            /* renamed from: c, reason: collision with root package name */
            final /* synthetic */ PrintAttributes f893c;

            /* renamed from: d, reason: collision with root package name */
            final /* synthetic */ PrintDocumentAdapter.LayoutResultCallback f894d;

            /* renamed from: androidx.print.PrintHelper$c$a$a, reason: collision with other inner class name */
            /* loaded from: classes.dex */
            class C0032a implements CancellationSignal.OnCancelListener {
                C0032a() {
                }

                @Override // android.os.CancellationSignal.OnCancelListener
                public void onCancel() {
                    c.this.a();
                    a.this.cancel(false);
                }
            }

            a(CancellationSignal cancellationSignal, PrintAttributes printAttributes, PrintAttributes printAttributes2, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback) {
                this.a = cancellationSignal;
                this.b = printAttributes;
                this.f893c = printAttributes2;
                this.f894d = layoutResultCallback;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Bitmap doInBackground(Uri... uriArr) {
                try {
                    c cVar = c.this;
                    return PrintHelper.this.f(cVar.b);
                } catch (FileNotFoundException unused) {
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onCancelled(Bitmap bitmap) {
                this.f894d.onLayoutCancelled();
                c.this.f = null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Bitmap bitmap) {
                PrintAttributes.MediaSize mediaSize;
                super.onPostExecute(bitmap);
                if (bitmap != null && (!PrintHelper.g || PrintHelper.this.f == 0)) {
                    synchronized (this) {
                        mediaSize = c.this.e.getMediaSize();
                    }
                    if (mediaSize != null && mediaSize.isPortrait() != PrintHelper.d(bitmap)) {
                        Matrix matrix = new Matrix();
                        matrix.postRotate(90.0f);
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    }
                }
                c.this.g = bitmap;
                if (bitmap != null) {
                    this.f894d.onLayoutFinished(new PrintDocumentInfo.Builder(c.this.a).setContentType(1).setPageCount(1).build(), true ^ this.b.equals(this.f893c));
                } else {
                    this.f894d.onLayoutFailed(null);
                }
                c.this.f = null;
            }

            @Override // android.os.AsyncTask
            protected void onPreExecute() {
                this.a.setOnCancelListener(new C0032a());
            }
        }

        c(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback, int i2) {
            this.a = str;
            this.b = uri;
            this.f891c = onPrintFinishCallback;
            this.f892d = i2;
        }

        void a() {
            synchronized (PrintHelper.this.f885c) {
                BitmapFactory.Options options = PrintHelper.this.b;
                if (options != null) {
                    if (Build.VERSION.SDK_INT < 24) {
                        options.requestCancelDecode();
                    }
                    PrintHelper.this.b = null;
                }
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onFinish() {
            super.onFinish();
            a();
            AsyncTask<Uri, Boolean, Bitmap> asyncTask = this.f;
            if (asyncTask != null) {
                asyncTask.cancel(true);
            }
            OnPrintFinishCallback onPrintFinishCallback = this.f891c;
            if (onPrintFinishCallback != null) {
                onPrintFinishCallback.onFinish();
            }
            Bitmap bitmap = this.g;
            if (bitmap != null) {
                bitmap.recycle();
                this.g = null;
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes2, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback layoutResultCallback, Bundle bundle) {
            synchronized (this) {
                this.e = printAttributes2;
            }
            if (cancellationSignal.isCanceled()) {
                layoutResultCallback.onLayoutCancelled();
            } else if (this.g != null) {
                layoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.a).setContentType(1).setPageCount(1).build(), !printAttributes2.equals(printAttributes));
            } else {
                this.f = new a(cancellationSignal, printAttributes2, printAttributes, layoutResultCallback).execute(new Uri[0]);
            }
        }

        @Override // android.print.PrintDocumentAdapter
        public void onWrite(PageRange[] pageRangeArr, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
            PrintHelper.this.g(this.e, this.f892d, this.g, parcelFileDescriptor, cancellationSignal, writeResultCallback);
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        g = i2 < 20 || i2 > 23;
        h = i2 != 23;
    }

    public PrintHelper(@NonNull Context context) {
        this.a = context;
    }

    static Bitmap a(Bitmap bitmap, int i2) {
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

    @RequiresApi(19)
    private static PrintAttributes.Builder b(PrintAttributes printAttributes) {
        PrintAttributes.Builder minMargins = new PrintAttributes.Builder().setMediaSize(printAttributes.getMediaSize()).setResolution(printAttributes.getResolution()).setMinMargins(printAttributes.getMinMargins());
        if (printAttributes.getColorMode() != 0) {
            minMargins.setColorMode(printAttributes.getColorMode());
        }
        if (Build.VERSION.SDK_INT >= 23 && printAttributes.getDuplexMode() != 0) {
            minMargins.setDuplexMode(printAttributes.getDuplexMode());
        }
        return minMargins;
    }

    static Matrix c(int i2, int i3, RectF rectF, int i4) {
        Matrix matrix = new Matrix();
        float f = i2;
        float width = rectF.width() / f;
        float max = i4 == 2 ? Math.max(width, rectF.height() / i3) : Math.min(width, rectF.height() / i3);
        matrix.postScale(max, max);
        matrix.postTranslate((rectF.width() - (f * max)) / 2.0f, (rectF.height() - (i3 * max)) / 2.0f);
        return matrix;
    }

    static boolean d(Bitmap bitmap) {
        return bitmap.getWidth() <= bitmap.getHeight();
    }

    private Bitmap e(Uri uri, BitmapFactory.Options options) {
        Context context;
        InputStream openInputStream;
        if (uri == null || (context = this.a) == null) {
            throw new IllegalArgumentException("bad argument to loadBitmap");
        }
        InputStream inputStream = null;
        try {
            openInputStream = context.getContentResolver().openInputStream(uri);
        } catch (Throwable th) {
            th = th;
        }
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream, null, options);
            if (openInputStream != null) {
                try {
                    openInputStream.close();
                } catch (IOException e) {
                    Log.w("PrintHelper", "close fail ", e);
                }
            }
            return decodeStream;
        } catch (Throwable th2) {
            th = th2;
            inputStream = openInputStream;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    Log.w("PrintHelper", "close fail ", e2);
                }
            }
            throw th;
        }
    }

    public static boolean systemSupportsPrint() {
        return Build.VERSION.SDK_INT >= 19;
    }

    Bitmap f(Uri uri) {
        BitmapFactory.Options options;
        if (uri == null || this.a == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inJustDecodeBounds = true;
        e(uri, options2);
        int i2 = options2.outWidth;
        int i3 = options2.outHeight;
        if (i2 > 0 && i3 > 0) {
            int max = Math.max(i2, i3);
            int i4 = 1;
            while (max > 3500) {
                max >>>= 1;
                i4 <<= 1;
            }
            if (i4 > 0 && Math.min(i2, i3) / i4 > 0) {
                synchronized (this.f885c) {
                    options = new BitmapFactory.Options();
                    this.b = options;
                    options.inMutable = true;
                    options.inSampleSize = i4;
                }
                try {
                    Bitmap e = e(uri, options);
                    synchronized (this.f885c) {
                        this.b = null;
                    }
                    return e;
                } catch (Throwable th) {
                    synchronized (this.f885c) {
                        this.b = null;
                        throw th;
                    }
                }
            }
        }
        return null;
    }

    @RequiresApi(19)
    void g(PrintAttributes printAttributes, int i2, Bitmap bitmap, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback writeResultCallback) {
        new a(cancellationSignal, h ? printAttributes : b(printAttributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build(), bitmap, printAttributes, i2, parcelFileDescriptor, writeResultCallback).execute(new Void[0]);
    }

    public int getColorMode() {
        return this.e;
    }

    public int getOrientation() {
        if (Build.VERSION.SDK_INT < 19 || this.f != 0) {
            return this.f;
        }
        return 1;
    }

    public int getScaleMode() {
        return this.f886d;
    }

    public void printBitmap(@NonNull String str, @NonNull Bitmap bitmap) {
        printBitmap(str, bitmap, (OnPrintFinishCallback) null);
    }

    public void printBitmap(@NonNull String str, @NonNull Bitmap bitmap, @Nullable OnPrintFinishCallback onPrintFinishCallback) {
        if (Build.VERSION.SDK_INT < 19 || bitmap == null) {
            return;
        }
        ((PrintManager) this.a.getSystemService("print")).print(str, new b(str, this.f886d, bitmap, onPrintFinishCallback), new PrintAttributes.Builder().setMediaSize(d(bitmap) ? PrintAttributes.MediaSize.UNKNOWN_PORTRAIT : PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE).setColorMode(this.e).build());
    }

    public void printBitmap(@NonNull String str, @NonNull Uri uri) {
        printBitmap(str, uri, (OnPrintFinishCallback) null);
    }

    public void printBitmap(@NonNull String str, @NonNull Uri uri, @Nullable OnPrintFinishCallback onPrintFinishCallback) {
        PrintAttributes.MediaSize mediaSize;
        if (Build.VERSION.SDK_INT < 19) {
            return;
        }
        c cVar = new c(str, uri, onPrintFinishCallback, this.f886d);
        PrintManager printManager = (PrintManager) this.a.getSystemService("print");
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setColorMode(this.e);
        int i2 = this.f;
        if (i2 != 1 && i2 != 0) {
            if (i2 == 2) {
                mediaSize = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
            }
            printManager.print(str, cVar, builder.build());
        }
        mediaSize = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
        builder.setMediaSize(mediaSize);
        printManager.print(str, cVar, builder.build());
    }

    public void setColorMode(int i2) {
        this.e = i2;
    }

    public void setOrientation(int i2) {
        this.f = i2;
    }

    public void setScaleMode(int i2) {
        this.f886d = i2;
    }
}
