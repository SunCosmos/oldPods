package com.iapp.app;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class u {
    private ArrayList<Object> a;
    private PagerAdapter b;

    /* loaded from: classes.dex */
    class a extends PagerAdapter {
        a() {
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
            viewGroup.removeView((View) u.this.a.get(i2));
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return u.this.a.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i2) {
            View view = (View) u.this.a.get(i2);
            viewGroup.addView(view);
            return view;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
    }

    public u(View view, ArrayList<Object> arrayList) {
        a aVar = new a();
        this.b = aVar;
        this.a = arrayList;
        if (view instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) view;
            viewPager.setAdapter(aVar);
            viewPager.setOffscreenPageLimit(arrayList.size());
        } else if (view instanceof VerticalViewPager) {
            VerticalViewPager verticalViewPager = (VerticalViewPager) view;
            verticalViewPager.setAdapter(aVar);
            verticalViewPager.setOffscreenPageLimit(arrayList.size());
        }
    }
}
