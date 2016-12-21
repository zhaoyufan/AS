package com.upic.asn.ui.main.homehead.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.upic.asn.R;
import com.upic.asn.ui.main.homehead.bean.SortSelect;

import java.util.List;
import java.util.Vector;

/**
 * Created by ZF on 2016/12/15.
 */

public class SortingMyAdapter extends SortingListAdapter {
    private Context context;
    private List<SortSelect> sortSelects;
    private LayoutInflater inflater;
    private int pos;
    private int lastPosition = -1;                                //记录上一次选中的图片位置，-1表示未选中
    private Vector<Boolean> vector = new Vector<Boolean>();

    public SortingMyAdapter(Context context, List<SortSelect> sortSelects) {
        super(context);
        this.context = context;
        this.sortSelects = sortSelects;
        inflater = LayoutInflater.from(context);
        vector.add(true);
        for (int i = 0; i < sortSelects.size()-1; i++) {
            vector.add(false);
        }
    }

    @Override
    public int getCount() {
        return sortSelects.size();
    }

    @Override
    protected ViewHolder createViewHolder(View root) {
        sortSelectHolder holder = new sortSelectHolder();
        holder.pop_sorting_text = (TextView) root.findViewById(R.id.pop_sorting_text);
        return holder;
    }

    @Override
    protected void fillView(View root, Object item, ViewHolder holder, int position) {
        final sortSelectHolder holder1 = (sortSelectHolder) holder;
        holder1.sortSelect = sortSelects.get(position);
        if (!"".equals(sortSelects.get(position).getContent())) {
            holder1.pop_sorting_text.setText(sortSelects.get(position).getContent());
        }
        if (vector.elementAt(position) == true) {
            holder1.pop_sorting_text.setEnabled(true);
        } else {
            holder1.pop_sorting_text.setEnabled(false);
        }
    }

    @Override
    protected int getItemViewId() {
        return R.layout.pop_sorting_griditem;
    }

    private class sortSelectHolder extends ViewHolder {
        private TextView pop_sorting_text;
        private SortSelect sortSelect;
    }

    /**
     * 修改选中时的状态
     *
     * @param position
     */
    public void changeState(int position) {

            if (lastPosition != -1){           //取消上一次的选中状态
                vector.setElementAt(false, lastPosition);
            } else if(lastPosition == -1){
                vector.setElementAt(false, 0);
            }
        vector.setElementAt(!vector.elementAt(position), position);     //直接取反即可
        lastPosition = position;                                        //记录本次选中的位置
        notifyDataSetChanged();
    }
}
