package androidx.appcompat.widget;

import android.support.v7.widget.ActivityChooserView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
class p implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    private static p j;
    private static p k;
    private final View a;
    private final CharSequence b;

    /* renamed from: c, reason: collision with root package name */
    private final int f233c;

    /* renamed from: d, reason: collision with root package name */
    private final Runnable f234d = new a();
    private final Runnable e = new b();
    private int f;
    private int g;
    private q h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f235i;

    /* loaded from: classes.dex */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            p.this.g(false);
        }
    }

    /* loaded from: classes.dex */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            p.this.c();
        }
    }

    private p(View view, CharSequence charSequence) {
        this.a = view;
        this.b = charSequence;
        this.f233c = ViewConfigurationCompat.getScaledHoverSlop(ViewConfiguration.get(view.getContext()));
        b();
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    private void a() {
        this.a.removeCallbacks(this.f234d);
    }

    private void b() {
        this.f = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.g = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    private void d() {
        this.a.postDelayed(this.f234d, ViewConfiguration.getLongPressTimeout());
    }

    private static void e(p pVar) {
        p pVar2 = j;
        if (pVar2 != null) {
            pVar2.a();
        }
        j = pVar;
        if (pVar != null) {
            pVar.d();
        }
    }

    public static void f(View view, CharSequence charSequence) {
        p pVar = j;
        if (pVar != null && pVar.a == view) {
            e(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new p(view, charSequence);
            return;
        }
        p pVar2 = k;
        if (pVar2 != null && pVar2.a == view) {
            pVar2.c();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    private boolean h(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.f) <= this.f233c && Math.abs(y - this.g) <= this.f233c) {
            return false;
        }
        this.f = x;
        this.g = y;
        return true;
    }

    void c() {
        if (k == this) {
            k = null;
            q qVar = this.h;
            if (qVar != null) {
                qVar.c();
                this.h = null;
                b();
                this.a.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (j == this) {
            e(null);
        }
        this.a.removeCallbacks(this.e);
    }

    void g(boolean z) {
        long longPressTimeout;
        if (ViewCompat.isAttachedToWindow(this.a)) {
            e(null);
            p pVar = k;
            if (pVar != null) {
                pVar.c();
            }
            k = this;
            this.f235i = z;
            q qVar = new q(this.a.getContext());
            this.h = qVar;
            qVar.e(this.a, this.f, this.g, this.f235i, this.b);
            this.a.addOnAttachStateChangeListener(this);
            if (this.f235i) {
                longPressTimeout = 2500;
            } else {
                longPressTimeout = ((ViewCompat.getWindowSystemUiVisibility(this.a) & 1) == 1 ? 3000L : 15000L) - ViewConfiguration.getLongPressTimeout();
            }
            this.a.removeCallbacks(this.e);
            this.a.postDelayed(this.e, longPressTimeout);
        }
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.h != null && this.f235i) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.a.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                b();
                c();
            }
        } else if (this.a.isEnabled() && this.h == null && h(motionEvent)) {
            e(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.f = view.getWidth() / 2;
        this.g = view.getHeight() / 2;
        g(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        c();
    }
}
