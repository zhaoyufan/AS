package com.upic.asn.ui.main.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZYF on 2016/12/23.
 */
public class MyPagerAdapter extends PagerAdapter {
    List<View> listView = new ArrayList<>();

    public MyPagerAdapter(List<View> listView) {
        this.listView = listView;
    }

    @Override
    public int getCount() {
        return listView.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(listView.get(position));
        return listView.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(listView.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
