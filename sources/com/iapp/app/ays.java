package com.iapp.app;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import bsh.EvalError;
import com.iapp.app.run.mian;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.keplerproject.luajava.LuaException;

/* loaded from: classes.dex */
public class ays extends AccessibilityService {
    public static int ets = -1;
    public static int f = 1;
    public static int fbt = -1;
    public static int nt = 500;
    public static String pns = "com.iapp.app";
    private c.b.a.a.t a = null;
    private Aid_YuCodeX b = null;

    /* renamed from: c, reason: collision with root package name */
    private d f1932c = null;

    /* renamed from: d, reason: collision with root package name */
    private j f1933d = null;
    private int e = 0;

    @TargetApi(14)
    private void a(AccessibilityNodeInfo accessibilityNodeInfo, List<AccessibilityNodeInfo> list) {
        if (accessibilityNodeInfo.getChildCount() > 0) {
            int childCount = accessibilityNodeInfo.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i2);
                if (child != null) {
                    list.add(child);
                    a(child, list);
                }
            }
        }
    }

    public static void goset(Context context) {
        context.startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
    }

    public static boolean isas(Context context) {
        int i2;
        String string;
        String str = context.getPackageName() + "/" + ays.class.getCanonicalName();
        try {
            i2 = Settings.Secure.getInt(context.getApplicationContext().getContentResolver(), "accessibility_enabled");
        } catch (Settings.SettingNotFoundException unused) {
            i2 = 0;
        }
        TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
        if (i2 == 1 && (string = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), "enabled_accessibility_services")) != null) {
            simpleStringSplitter.setString(string);
            while (simpleStringSplitter.hasNext()) {
                if (simpleStringSplitter.next().equalsIgnoreCase(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    @TargetApi(14)
    public boolean ck(AccessibilityNodeInfo accessibilityNodeInfo, int i2) {
        return accessibilityNodeInfo.performAction(i2);
    }

    @TargetApi(16)
    public boolean ck(AccessibilityNodeInfo accessibilityNodeInfo, int i2, Bundle bundle) {
        return accessibilityNodeInfo.performAction(i2, bundle);
    }

    public boolean ck(List<AccessibilityNodeInfo> list, int i2) {
        Iterator<AccessibilityNodeInfo> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z = cka(it.next(), i2);
        }
        return z;
    }

    public boolean ck(List<AccessibilityNodeInfo> list, int i2, Bundle bundle) {
        Iterator<AccessibilityNodeInfo> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z = cka(it.next(), i2, bundle);
        }
        return z;
    }

    @TargetApi(14)
    public boolean cka(AccessibilityNodeInfo accessibilityNodeInfo, int i2) {
        if (accessibilityNodeInfo.isEnabled()) {
            return accessibilityNodeInfo.performAction(i2);
        }
        return false;
    }

    @TargetApi(14)
    public boolean cka(AccessibilityNodeInfo accessibilityNodeInfo, int i2, Bundle bundle) {
        if (accessibilityNodeInfo.isEnabled()) {
            return accessibilityNodeInfo.performAction(i2);
        }
        return false;
    }

    public boolean ckfocus(AccessibilityNodeInfo accessibilityNodeInfo, int i2, int i3) {
        return cka(focus(accessibilityNodeInfo, i3), i2);
    }

    public boolean ckid(AccessibilityNodeInfo accessibilityNodeInfo, int i2, String str) {
        return ck(id(accessibilityNodeInfo, str), i2);
    }

    public boolean cktext(AccessibilityNodeInfo accessibilityNodeInfo, int i2, String str) {
        return ck(text(accessibilityNodeInfo, str), i2);
    }

    @SuppressLint({"InlinedApi"})
    @TargetApi(16)
    public boolean enter(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            Bundle bundle = new Bundle();
            bundle.putCharSequence("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE", str);
            return accessibilityNodeInfo.performAction(2097152, bundle);
        }
        if (i2 < 18) {
            return false;
        }
        ((ClipboardManager) getBaseContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("iapp label", str));
        accessibilityNodeInfo.performAction(1);
        return accessibilityNodeInfo.performAction(32768);
    }

    public boolean enter(List<AccessibilityNodeInfo> list, String str) {
        Iterator<AccessibilityNodeInfo> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z = enter(it.next(), str);
        }
        return z;
    }

    @TargetApi(16)
    public AccessibilityNodeInfo focus(AccessibilityNodeInfo accessibilityNodeInfo, int i2) {
        return accessibilityNodeInfo.findFocus(i2);
    }

    @TargetApi(16)
    public AccessibilityNodeInfo focussearch(AccessibilityNodeInfo accessibilityNodeInfo, int i2) {
        return accessibilityNodeInfo.focusSearch(i2);
    }

    @SuppressLint({"NewApi"})
    @TargetApi(14)
    public List<AccessibilityNodeInfo.AccessibilityAction> gal(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.getActionList();
    }

    @TargetApi(16)
    public AccessibilityNodeInfo gall() {
        return getRootInActiveWindow();
    }

    @TargetApi(14)
    public AccessibilityNodeInfo gall(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getSource();
    }

    public List<AccessibilityNodeInfo> ganiall(AccessibilityNodeInfo accessibilityNodeInfo) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(accessibilityNodeInfo);
        a(accessibilityNodeInfo, arrayList);
        return arrayList;
    }

    public Context gbc() {
        return getBaseContext();
    }

    @TargetApi(14)
    public Rect gbip(AccessibilityNodeInfo accessibilityNodeInfo) {
        Rect rect = new Rect();
        accessibilityNodeInfo.getBoundsInParent(rect);
        return rect;
    }

    @TargetApi(14)
    public Rect gbis(AccessibilityNodeInfo accessibilityNodeInfo) {
        Rect rect = new Rect();
        accessibilityNodeInfo.getBoundsInScreen(rect);
        return rect;
    }

    public String gcn(AccessibilityEvent accessibilityEvent) {
        CharSequence className = accessibilityEvent.getClassName();
        if (className == null) {
            return null;
        }
        return className.toString();
    }

    @TargetApi(14)
    public String gcn(AccessibilityNodeInfo accessibilityNodeInfo) {
        CharSequence className = accessibilityNodeInfo.getClassName();
        if (className == null) {
            return null;
        }
        return className.toString();
    }

    @TargetApi(14)
    public AccessibilityNodeInfo gi(AccessibilityNodeInfo accessibilityNodeInfo, int i2) {
        return accessibilityNodeInfo.getChild(i2);
    }

    @TargetApi(18)
    public String gid(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.getViewIdResourceName();
    }

    @TargetApi(14)
    public int gl(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.getChildCount();
    }

    @TargetApi(14)
    public AccessibilityNodeInfo gp(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.getParent();
    }

    public String gpn(AccessibilityEvent accessibilityEvent) {
        CharSequence packageName = accessibilityEvent.getPackageName();
        if (packageName == null) {
            return null;
        }
        return packageName.toString();
    }

    @TargetApi(14)
    public String gpn(AccessibilityNodeInfo accessibilityNodeInfo) {
        CharSequence packageName = accessibilityNodeInfo.getPackageName();
        if (packageName == null) {
            return null;
        }
        return packageName.toString();
    }

    @TargetApi(16)
    public AccessibilityServiceInfo gsi() {
        return getServiceInfo();
    }

    public int gsl(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getItemCount();
    }

    @TargetApi(14)
    public String gt(AccessibilityNodeInfo accessibilityNodeInfo) {
        CharSequence text = accessibilityNodeInfo.getText();
        if (text == null) {
            return null;
        }
        return text.toString();
    }

    public long gtime(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getEventTime();
    }

    public int gtype(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getEventType();
    }

    @TargetApi(14)
    public int gwid(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getWindowId();
    }

    @TargetApi(14)
    public int gwid(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.getWindowId();
    }

    @TargetApi(18)
    public List<AccessibilityNodeInfo> id(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        return accessibilityNodeInfo.findAccessibilityNodeInfosByViewId(str);
    }

    @TargetApi(14)
    public boolean iscd(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.isChecked();
    }

    @TargetApi(14)
    public boolean isck(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.isClickable();
    }

    public boolean ised(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.isEnabled();
    }

    @TargetApi(14)
    public boolean ised(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.isEnabled();
    }

    @TargetApi(14)
    public boolean isfd(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.isFocused();
    }

    @TargetApi(14)
    public boolean islck(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.isLongClickable();
    }

    @TargetApi(14)
    public boolean ispd(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.isPassword();
    }

    @TargetApi(14)
    public boolean issd(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.isSelected();
    }

    @TargetApi(14)
    public boolean isse(AccessibilityNodeInfo accessibilityNodeInfo) {
        return accessibilityNodeInfo.isScrollable();
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null) {
            return;
        }
        int i2 = this.e;
        if (i2 == 1) {
            if (mian.sh) {
                this.a.T("e", accessibilityEvent);
                this.a.x0("ays_service.on(e)", "ays", this);
                return;
            } else {
                this.b.dim("e", accessibilityEvent);
                this.b.function3("ays_service.on(e)", "ays", this);
                return;
            }
        }
        if (i2 == 2) {
            this.f1933d.b("on", accessibilityEvent);
        } else if (i2 == 3) {
            this.f1932c.d("on", accessibilityEvent);
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
    }

    @Override // android.accessibilityservice.AccessibilityService
    @TargetApi(16)
    protected void onServiceConnected() {
        int i2;
        fbt = -1;
        if (c.b.a.a.t.l == null || a.f1931c == null) {
            a.f1931c = getPackageName();
            c.b.a.a.t.l = new HashMap<>();
            c.b.a.a.b.l();
        }
        Context baseContext = getBaseContext();
        if (b.h9(baseContext, "ays_service.myu")) {
            if (mian.sh) {
                c.b.a.a.t tVar = new c.b.a.a.t(baseContext, null);
                this.a = tVar;
                tVar.w0("ays_service.onsc()");
            } else {
                Aid_YuCodeX aid_YuCodeX = new Aid_YuCodeX(baseContext, null);
                this.b = aid_YuCodeX;
                aid_YuCodeX.function2("ays_service.onsc()");
            }
            i2 = 1;
        } else {
            if (!b.h9(baseContext, "ays_service.mjava")) {
                if (b.h9(baseContext, "ays_service.mlua")) {
                    d dVar = new d(baseContext);
                    this.f1932c = dVar;
                    dVar.l("ays", this);
                    this.f1932c.l("activity", baseContext);
                    this.f1932c.l("i", new Aid_luaCode(baseContext, null, this.f1932c));
                    this.f1932c.k();
                    try {
                        this.f1932c.i("ays_service.mlua");
                    } catch (LuaException e) {
                        e.printStackTrace();
                        c.b.a.a.t.P2(this, "LuaErr：\n" + e.getMessage());
                    }
                    this.f1932c.c("onsc");
                    i2 = 3;
                }
                AccessibilityServiceInfo serviceInfo = getServiceInfo();
                serviceInfo.eventTypes = ets;
                serviceInfo.packageNames = pns.split(",", -1);
                serviceInfo.feedbackType = fbt;
                serviceInfo.notificationTimeout = nt;
                serviceInfo.flags = f;
                setServiceInfo(serviceInfo);
            }
            j jVar = new j(baseContext);
            this.f1933d = jVar;
            try {
                jVar.set("ays", this);
                this.f1933d.set("activity", baseContext);
                this.f1933d.set("i", new Aid_javaCode(baseContext, null, this.f1933d));
            } catch (EvalError e2) {
                e2.printStackTrace();
            }
            if (this.f1933d.e("ays_service.mjava")) {
                this.f1933d.a("onsc");
            }
            i2 = 2;
        }
        this.e = i2;
        AccessibilityServiceInfo serviceInfo2 = getServiceInfo();
        serviceInfo2.eventTypes = ets;
        serviceInfo2.packageNames = pns.split(",", -1);
        serviceInfo2.feedbackType = fbt;
        serviceInfo2.notificationTimeout = nt;
        serviceInfo2.flags = f;
        setServiceInfo(serviceInfo2);
    }

    @TargetApi(16)
    public boolean pga(int i2) {
        return performGlobalAction(i2);
    }

    public void re(AccessibilityEvent accessibilityEvent) {
        accessibilityEvent.recycle();
    }

    @TargetApi(14)
    public void re(AccessibilityNodeInfo accessibilityNodeInfo) {
        accessibilityNodeInfo.recycle();
    }

    public void ssi(AccessibilityServiceInfo accessibilityServiceInfo) {
        setServiceInfo(accessibilityServiceInfo);
    }

    @TargetApi(14)
    public List<AccessibilityNodeInfo> text(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        return accessibilityNodeInfo.findAccessibilityNodeInfosByText(str);
    }
}
