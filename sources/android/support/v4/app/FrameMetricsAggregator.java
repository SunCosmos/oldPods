package android.support.v4.app;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.SparseIntArray;
import android.view.FrameMetrics;
import android.view.Window;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: lib/Mus.dex */
public class FrameMetricsAggregator {
    public static final int ANIMATION_DURATION = 256;
    public static final int ANIMATION_INDEX = 8;
    public static final int COMMAND_DURATION = 32;
    public static final int COMMAND_INDEX = 5;
    private static final boolean DBG = false;
    public static final int DELAY_DURATION = 128;
    public static final int DELAY_INDEX = 7;
    public static final int DRAW_DURATION = 8;
    public static final int DRAW_INDEX = 3;
    public static final int EVERY_DURATION = 511;
    public static final int INPUT_DURATION = 2;
    public static final int INPUT_INDEX = 1;
    private static final int LAST_INDEX = 8;
    public static final int LAYOUT_MEASURE_DURATION = 4;
    public static final int LAYOUT_MEASURE_INDEX = 2;
    public static final int SWAP_DURATION = 64;
    public static final int SWAP_INDEX = 6;
    public static final int SYNC_DURATION = 16;
    public static final int SYNC_INDEX = 4;
    private static final String TAG = "FrameMetrics";
    public static final int TOTAL_DURATION = 1;
    public static final int TOTAL_INDEX = 0;
    private FrameMetricsBaseImpl mInstance;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: lib/Mus.dex */
    public @interface MetricType {
    }

    public FrameMetricsAggregator() {
        this(1);
    }

    public FrameMetricsAggregator(int i2) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.mInstance = new FrameMetricsApi24Impl(i2);
        } else {
            this.mInstance = new FrameMetricsBaseImpl();
        }
    }

    public void add(@NonNull Activity activity) {
        this.mInstance.add(activity);
    }

    @Nullable
    public SparseIntArray[] remove(@NonNull Activity activity) {
        return this.mInstance.remove(activity);
    }

    @Nullable
    public SparseIntArray[] stop() {
        return this.mInstance.stop();
    }

    @Nullable
    public SparseIntArray[] reset() {
        return this.mInstance.reset();
    }

    @Nullable
    public SparseIntArray[] getMetrics() {
        return this.mInstance.getMetrics();
    }

    /* loaded from: lib/Mus.dex */
    private static class FrameMetricsBaseImpl {
        private FrameMetricsBaseImpl() {
        }

        public void add(Activity activity) {
        }

        public SparseIntArray[] remove(Activity activity) {
            return null;
        }

        public SparseIntArray[] stop() {
            return null;
        }

        public SparseIntArray[] getMetrics() {
            return null;
        }

        public SparseIntArray[] reset() {
            return null;
        }
    }

    @RequiresApi(24)
    /* loaded from: lib/Mus.dex */
    private static class FrameMetricsApi24Impl extends FrameMetricsBaseImpl {
        private static final int NANOS_PER_MS = 1000000;
        private static final int NANOS_ROUNDING_VALUE = 500000;
        private ArrayList<WeakReference<Activity>> mActivities;
        Window.OnFrameMetricsAvailableListener mListener;
        private SparseIntArray[] mMetrics;
        private int mTrackingFlags;
        private static HandlerThread sHandlerThread = null;
        private static Handler sHandler = null;

        FrameMetricsApi24Impl(int i2) {
            super();
            this.mMetrics = new SparseIntArray[9];
            this.mActivities = new ArrayList<>();
            this.mListener = new Window.OnFrameMetricsAvailableListener() { // from class: android.support.v4.app.FrameMetricsAggregator.FrameMetricsApi24Impl.1
                @Override // android.view.Window.OnFrameMetricsAvailableListener
                public void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i3) {
                    if ((FrameMetricsApi24Impl.this.mTrackingFlags & 1) != 0) {
                        FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[0], frameMetrics.getMetric(8));
                    }
                    if ((FrameMetricsApi24Impl.this.mTrackingFlags & 2) != 0) {
                        FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[1], frameMetrics.getMetric(1));
                    }
                    if ((FrameMetricsApi24Impl.this.mTrackingFlags & 4) != 0) {
                        FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[2], frameMetrics.getMetric(3));
                    }
                    if ((FrameMetricsApi24Impl.this.mTrackingFlags & 8) != 0) {
                        FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[3], frameMetrics.getMetric(4));
                    }
                    if ((FrameMetricsApi24Impl.this.mTrackingFlags & 16) != 0) {
                        FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[4], frameMetrics.getMetric(5));
                    }
                    if ((FrameMetricsApi24Impl.this.mTrackingFlags & 64) != 0) {
                        FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[6], frameMetrics.getMetric(7));
                    }
                    if ((FrameMetricsApi24Impl.this.mTrackingFlags & 32) != 0) {
                        FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[5], frameMetrics.getMetric(6));
                    }
                    if ((FrameMetricsApi24Impl.this.mTrackingFlags & 128) != 0) {
                        FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[7], frameMetrics.getMetric(0));
                    }
                    if ((FrameMetricsApi24Impl.this.mTrackingFlags & 256) != 0) {
                        FrameMetricsApi24Impl.this.addDurationItem(FrameMetricsApi24Impl.this.mMetrics[8], frameMetrics.getMetric(2));
                    }
                }
            };
            this.mTrackingFlags = i2;
        }

        void addDurationItem(SparseIntArray sparseIntArray, long j) {
            if (sparseIntArray != null) {
                int i2 = (int) ((j + 500000) / 1000000);
                if (j >= 0) {
                    sparseIntArray.put(i2, sparseIntArray.get(i2) + 1);
                }
            }
        }

        @Override // android.support.v4.app.FrameMetricsAggregator.FrameMetricsBaseImpl
        public void add(Activity activity) {
            if (sHandlerThread == null) {
                sHandlerThread = new HandlerThread("FrameMetricsAggregator");
                sHandlerThread.start();
                sHandler = new Handler(sHandlerThread.getLooper());
            }
            for (int i2 = 0; i2 <= 8; i2++) {
                if (this.mMetrics[i2] == null && (this.mTrackingFlags & (1 << i2)) != 0) {
                    this.mMetrics[i2] = new SparseIntArray();
                }
            }
            activity.getWindow().addOnFrameMetricsAvailableListener(this.mListener, sHandler);
            this.mActivities.add(new WeakReference<>(activity));
        }

        @Override // android.support.v4.app.FrameMetricsAggregator.FrameMetricsBaseImpl
        public SparseIntArray[] remove(Activity activity) {
            Iterator<WeakReference<Activity>> it = this.mActivities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WeakReference<Activity> next = it.next();
                if (next.get() == activity) {
                    this.mActivities.remove(next);
                    break;
                }
            }
            activity.getWindow().removeOnFrameMetricsAvailableListener(this.mListener);
            return this.mMetrics;
        }

        @Override // android.support.v4.app.FrameMetricsAggregator.FrameMetricsBaseImpl
        public SparseIntArray[] stop() {
            for (int size = this.mActivities.size() - 1; size >= 0; size--) {
                WeakReference<Activity> weakReference = this.mActivities.get(size);
                Activity activity = weakReference.get();
                if (weakReference.get() != null) {
                    activity.getWindow().removeOnFrameMetricsAvailableListener(this.mListener);
                    this.mActivities.remove(size);
                }
            }
            return this.mMetrics;
        }

        @Override // android.support.v4.app.FrameMetricsAggregator.FrameMetricsBaseImpl
        public SparseIntArray[] getMetrics() {
            return this.mMetrics;
        }

        @Override // android.support.v4.app.FrameMetricsAggregator.FrameMetricsBaseImpl
        public SparseIntArray[] reset() {
            SparseIntArray[] sparseIntArrayArr = this.mMetrics;
            this.mMetrics = new SparseIntArray[9];
            return sparseIntArrayArr;
        }
    }
}
