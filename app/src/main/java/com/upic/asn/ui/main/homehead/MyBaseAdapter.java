package com.upic.asn.ui.main.homehead;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by ZYF on 2016/12/15.
 */
public class MyBaseAdapter extends BaseAdapter {
    public List<Object> listDatas;
    public Context context;
    public int position = 0;

    public MyBaseAdapter(Context context, List<Object> listDatas) {
        init(context,listDatas);
    }

    private void init(Context context, List<Object> listDatas) {
        this.context = context;
        this.listDatas = listDatas;

    }

    @Override
    public int getCount() {
        return listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 需要子类去重写
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void setSelectItem(int position) {
        this.position = position;
    }

}
