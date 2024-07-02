package androidx.core.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* loaded from: classes.dex */
public final class GestureDetectorCompat {
    private final a a;

    /* loaded from: classes.dex */
    interface a {
        void a(GestureDetector.OnDoubleTapListener onDoubleTapListener);

        void b(boolean z);

        boolean c(MotionEvent motionEvent);

        boolean d();
    }

    /* loaded from: classes.dex */
    static class b implements a {
        private static final int v = ViewConfiguration.getLongPressTimeout();
        private static final int w = ViewConfiguration.getTapTimeout();
        private static final int x = ViewConfiguration.getDoubleTapTimeout();
        private int a;
        private int b;

        /* renamed from: c, reason: collision with root package name */
        private int f618c;

        /* renamed from: d, reason: collision with root package name */
        private int f619d;
        private final Handler e;
        final GestureDetector.OnGestureListener f;
        GestureDetector.OnDoubleTapListener g;
        boolean h;

        /* renamed from: i, reason: collision with root package name */
        boolean f620i;
        private boolean j;
        private boolean k;
        private boolean l;
        MotionEvent m;
        private MotionEvent n;
        private boolean o;
        private float p;
        private float q;
        private float r;
        private float s;
        private boolean t;
        private VelocityTracker u;

        /* loaded from: classes.dex */
        private class a extends Handler {
            a() {
            }

            a(Handler handler) {
                super(handler.getLooper());
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1) {
                    b bVar = b.this;
                    bVar.f.onShowPress(bVar.m);
                    return;
                }
                if (i2 == 2) {
                    b.this.g();
                    return;
                }
                if (i2 != 3) {
                    throw new RuntimeException("Unknown message " + message);
                }
                b bVar2 = b.this;
                GestureDetector.OnDoubleTapListener onDoubleTapListener = bVar2.g;
                if (onDoubleTapListener != null) {
                    if (bVar2.h) {
                        bVar2.f620i = true;
                    } else {
                        onDoubleTapListener.onSingleTapConfirmed(bVar2.m);
                    }
                }
            }
        }

        b(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            if (handler != null) {
                this.e = new a(handler);
            } else {
                this.e = new a();
            }
            this.f = onGestureListener;
            if (onGestureListener instanceof GestureDetector.OnDoubleTapListener) {
                a((GestureDetector.OnDoubleTapListener) onGestureListener);
            }
            h(context);
        }

        private void e() {
            this.e.removeMessages(1);
            this.e.removeMessages(2);
            this.e.removeMessages(3);
            this.u.recycle();
            this.u = null;
            this.o = false;
            this.h = false;
            this.k = false;
            this.l = false;
            this.f620i = false;
            if (this.j) {
                this.j = false;
            }
        }

        private void f() {
            this.e.removeMessages(1);
            this.e.removeMessages(2);
            this.e.removeMessages(3);
            this.o = false;
            this.k = false;
            this.l = false;
            this.f620i = false;
            if (this.j) {
                this.j = false;
            }
        }

        private void h(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null");
            }
            if (this.f == null) {
                throw new IllegalArgumentException("OnGestureListener must not be null");
            }
            this.t = true;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
            int scaledDoubleTapSlop = viewConfiguration.getScaledDoubleTapSlop();
            this.f618c = viewConfiguration.getScaledMinimumFlingVelocity();
            this.f619d = viewConfiguration.getScaledMaximumFlingVelocity();
            this.a = scaledTouchSlop * scaledTouchSlop;
            this.b = scaledDoubleTapSlop * scaledDoubleTapSlop;
        }

        private boolean i(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3) {
            if (!this.l || motionEvent3.getEventTime() - motionEvent2.getEventTime() > x) {
                return false;
            }
            int x2 = ((int) motionEvent.getX()) - ((int) motionEvent3.getX());
            int y = ((int) motionEvent.getY()) - ((int) motionEvent3.getY());
            return (x2 * x2) + (y * y) < this.b;
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public void a(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            this.g = onDoubleTapListener;
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public void b(boolean z) {
            this.t = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:114:0x0208  */
        /* JADX WARN: Removed duplicated region for block: B:117:0x021f  */
        @Override // androidx.core.view.GestureDetectorCompat.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean c(android.view.MotionEvent r13) {
            /*
                Method dump skipped, instructions count: 591
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.GestureDetectorCompat.b.c(android.view.MotionEvent):boolean");
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public boolean d() {
            return this.t;
        }

        void g() {
            this.e.removeMessages(3);
            this.f620i = false;
            this.j = true;
            this.f.onLongPress(this.m);
        }
    }

    /* loaded from: classes.dex */
    static class c implements a {
        private final GestureDetector a;

        c(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
            this.a = new GestureDetector(context, onGestureListener, handler);
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public void a(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
            this.a.setOnDoubleTapListener(onDoubleTapListener);
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public void b(boolean z) {
            this.a.setIsLongpressEnabled(z);
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public boolean c(MotionEvent motionEvent) {
            return this.a.onTouchEvent(motionEvent);
        }

        @Override // androidx.core.view.GestureDetectorCompat.a
        public boolean d() {
            return this.a.isLongpressEnabled();
        }
    }

    public GestureDetectorCompat(Context context, GestureDetector.OnGestureListener onGestureListener) {
        this(context, onGestureListener, null);
    }

    public GestureDetectorCompat(Context context, GestureDetector.OnGestureListener onGestureListener, Handler handler) {
        this.a = Build.VERSION.SDK_INT > 17 ? new c(context, onGestureListener, handler) : new b(context, onGestureListener, handler);
    }

    public boolean isLongpressEnabled() {
        return this.a.d();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.a.c(motionEvent);
    }

    public void setIsLongpressEnabled(boolean z) {
        this.a.b(z);
    }

    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener onDoubleTapListener) {
        this.a.a(onDoubleTapListener);
    }
}
