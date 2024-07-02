package com.iapp.app;

import android.app.Activity;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
public class h extends FragmentStatePagerAdapter {
    public static c.b.a.a.h o;
    private int j;
    private String k;
    private ArrayList<String> l;
    private ArrayList<Fragment> m;
    private ArrayList<HashMap<Integer, Object>> n;

    public h(Activity activity, int i2, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.j = 0;
        this.l = new ArrayList<>();
        this.m = new ArrayList<>();
        this.n = new ArrayList<>();
        g.k0 = activity;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i2);
        stringBuffer.append('_');
        stringBuffer.append(c.b.a.a.p.g(1000, 9999));
        this.k = stringBuffer.toString();
        c.b.a.a.h hVar = o;
        if (hVar != null) {
            hVar.m();
        }
        o = new c.b.a.a.h(activity, this, 1);
    }

    public void b(int i2, String str, String str2, int i3, HashMap<Integer, Object> hashMap) {
        this.j++;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.k);
        stringBuffer.append('_');
        stringBuffer.append(this.j);
        String stringBuffer2 = stringBuffer.toString();
        g.j0.put(stringBuffer2, hashMap);
        if (i2 == -1) {
            this.n.add(hashMap);
            this.l.add(str);
            this.m.add(g.s0(str2, i3, stringBuffer2));
        } else {
            this.n.add(i2, hashMap);
            this.l.add(str);
            this.m.add(i2, g.s0(str2, i3, stringBuffer2));
        }
        notifyDataSetChanged();
    }

    public void c() {
        Iterator<String> it = g.j0.keySet().iterator();
        new HashMap();
        while (it.hasNext()) {
            String valueOf = String.valueOf(it.next());
            if (valueOf != null && valueOf.startsWith(this.k)) {
                g.j0.remove(valueOf);
            }
        }
        o.m();
    }

    public void d(int i2) {
        if (i2 == -1) {
            i2 = this.l.size() - 1;
        }
        this.l.remove(i2);
        this.m.remove(i2);
        try {
            g.j0.remove(this.n.get(i2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.n.remove(i2);
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
        super.destroyItem(viewGroup, i2, obj);
    }

    public ArrayList<HashMap<Integer, Object>> e() {
        return this.n;
    }

    public void f(int i2, String str) {
        if (i2 == -1) {
            i2 = this.l.size() - 1;
        }
        this.l.set(i2, str);
    }

    public int g() {
        return this.m.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.m.size();
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i2) {
        return this.m.get(i2);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i2) {
        return this.l.get(i2);
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i2) {
        return super.instantiateItem(viewGroup, i2);
    }
}
