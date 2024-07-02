package com.iapp.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import bsh.EvalError;
import com.iapp.app.run.mian;
import java.util.HashMap;
import java.util.Iterator;
import org.keplerproject.luajava.LuaObject;

/* loaded from: classes.dex */
public class g extends Fragment {
    public static HashMap<String, Object> j0 = new HashMap<>();
    public static Activity k0 = null;
    private static final Object[] l0 = new Object[0];
    private Aid_YuCodeX b0 = null;
    private String c0 = null;
    private boolean d0 = true;
    private c.b.a.a.t e0 = null;
    private d f0 = null;
    private Aid_luaCode g0 = null;
    private j h0 = null;
    private Aid_javaCode i0 = null;

    public static g s0(String str, int i2, String str2) {
        g gVar = new g();
        Bundle bundle = new Bundle();
        bundle.putString("iyu", str);
        bundle.putInt("addid", i2);
        bundle.putString("pagename", str2);
        gVar.setArguments(bundle);
        return gVar;
    }

    public View o0(Context context, String str, String str2, int i2) {
        if (this.i0 == null) {
            this.h0 = new j(context);
            this.i0 = new Aid_javaCode(context, k0, this.h0);
            try {
                this.h0.set("activity", k0);
                this.h0.set("i", this.i0);
            } catch (EvalError e) {
                e.printStackTrace();
            }
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        j openRestoreinterface = this.i0.openRestoreinterface(str, linearLayout, i2, null);
        HashMap hashMap = (HashMap) j0.get(str2);
        Iterator it = hashMap.keySet().iterator();
        HashMap hashMap2 = new HashMap();
        while (it.hasNext()) {
            int parseInt = Integer.parseInt(String.valueOf(it.next()));
            if (parseInt > 0) {
                hashMap2.put(Integer.valueOf(parseInt), linearLayout.findViewById(parseInt + i2));
            }
        }
        Iterator it2 = hashMap.keySet().iterator();
        while (it2.hasNext()) {
            int parseInt2 = Integer.parseInt(String.valueOf(it2.next()));
            if (parseInt2 > 0) {
                c.b.a.a.f.w((View) hashMap2.get(Integer.valueOf(parseInt2)), hashMap.get(Integer.valueOf(parseInt2)), hashMap, h.o);
            }
        }
        if (openRestoreinterface != null) {
            try {
                openRestoreinterface.getNameSpace().invokeMethod("loading", l0, openRestoreinterface);
            } catch (EvalError e2) {
                e2.printStackTrace();
            }
        }
        return linearLayout;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        String string = arguments.getString("pagename");
        String string2 = arguments.getString("iyu");
        int i2 = arguments.getInt("addid");
        if (string2.endsWith(".iyu")) {
            if (mian.sh) {
                return q0(layoutInflater.getContext(), string2, string, i2);
            }
            return r0(layoutInflater.getContext(), string2, string2.substring(0, string2.length() - 4).toLowerCase(), string, i2);
        }
        if (string2.endsWith(".ijava")) {
            return o0(layoutInflater.getContext(), string2, string, i2);
        }
        if (string2.endsWith(".ilua")) {
            return p0(layoutInflater.getContext(), string2, string, i2);
        }
        return null;
    }

    public View p0(Context context, String str, String str2, int i2) {
        if (this.g0 == null) {
            this.f0 = new d(context);
            this.g0 = new Aid_luaCode(context, k0, this.f0);
            this.f0.l("activity", k0);
            this.f0.l("i", this.g0);
            this.f0.k();
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        LuaObject openRestoreinterface = this.g0.openRestoreinterface(str, linearLayout, i2, null);
        HashMap hashMap = (HashMap) j0.get(str2);
        Iterator it = hashMap.keySet().iterator();
        HashMap hashMap2 = new HashMap();
        while (it.hasNext()) {
            int parseInt = Integer.parseInt(String.valueOf(it.next()));
            if (parseInt > 0) {
                hashMap2.put(Integer.valueOf(parseInt), linearLayout.findViewById(parseInt + i2));
            }
        }
        Iterator it2 = hashMap.keySet().iterator();
        while (it2.hasNext()) {
            int parseInt2 = Integer.parseInt(String.valueOf(it2.next()));
            if (parseInt2 > 0) {
                c.b.a.a.f.w((View) hashMap2.get(Integer.valueOf(parseInt2)), hashMap.get(Integer.valueOf(parseInt2)), hashMap, h.o);
            }
        }
        if (openRestoreinterface != null) {
            openRestoreinterface.callxNoErr();
        }
        return linearLayout;
    }

    public View q0(Context context, String str, String str2, int i2) {
        if (this.e0 == null) {
            this.e0 = new c.b.a.a.t(context, k0);
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        this.e0.J1(str, linearLayout, i2, null);
        HashMap hashMap = (HashMap) j0.get(str2);
        Iterator it = hashMap.keySet().iterator();
        HashMap hashMap2 = new HashMap();
        while (it.hasNext()) {
            int parseInt = Integer.parseInt(String.valueOf(it.next()));
            if (parseInt > 0) {
                hashMap2.put(Integer.valueOf(parseInt), linearLayout.findViewById(parseInt + i2));
            }
        }
        Iterator it2 = hashMap.keySet().iterator();
        while (it2.hasNext()) {
            int parseInt2 = Integer.parseInt(String.valueOf(it2.next()));
            if (parseInt2 > 0) {
                c.b.a.a.f.w((View) hashMap2.get(Integer.valueOf(parseInt2)), hashMap.get(Integer.valueOf(parseInt2)), hashMap, h.o);
            }
        }
        if (this.d0) {
            if (this.c0 == null) {
                this.c0 = this.e0.Q0(str);
            }
            if (this.c0 != null) {
                linearLayout.setTag(new Object[]{null, null, null, j0.get(str2)});
                this.e0.T("st_vW", linearLayout);
                this.e0.T("st_lS", j0.get(str2));
                this.e0.T("st_pN", -1);
                this.e0.f(this.c0);
            } else {
                this.d0 = false;
            }
        }
        return linearLayout;
    }

    public View r0(Context context, String str, String str2, String str3, int i2) {
        if (this.b0 == null) {
            this.b0 = new Aid_YuCodeX(context, k0);
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        this.b0.openRestoreinterface(str, linearLayout, i2, null);
        HashMap hashMap = (HashMap) j0.get(str3);
        Iterator it = hashMap.keySet().iterator();
        HashMap hashMap2 = new HashMap();
        while (it.hasNext()) {
            int parseInt = Integer.parseInt(String.valueOf(it.next()));
            if (parseInt > 0) {
                hashMap2.put(Integer.valueOf(parseInt), linearLayout.findViewById(parseInt + i2));
            }
        }
        Iterator it2 = hashMap.keySet().iterator();
        while (it2.hasNext()) {
            int parseInt2 = Integer.parseInt(String.valueOf(it2.next()));
            if (parseInt2 > 0) {
                c.b.a.a.f.w((View) hashMap2.get(Integer.valueOf(parseInt2)), hashMap.get(Integer.valueOf(parseInt2)), hashMap, h.o);
            }
        }
        if (this.d0) {
            if (this.c0 == null) {
                this.c0 = this.b0.getopenRestoreinterface(str);
            }
            if (this.c0 != null) {
                linearLayout.setTag(new Object[]{null, null, null, j0.get(str3)});
                this.b0.dim("st_vW", linearLayout);
                this.b0.dim("st_lS", j0.get(str3));
                this.b0.dim("st_pN", -1);
                mian.c(str2, "loading" + this.c0, this.b0);
            } else {
                this.d0 = false;
            }
        }
        return linearLayout;
    }
}
