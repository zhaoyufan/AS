package com.upic.asn.ui.main.homehead;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upic.asn.R;

import java.util.List;

/**
 * Created by ZYF on 2016/12/14.
 */
public class MoreAdapter extends MyBaseAdapter {

    public MoreAdapter(Context context, List<Object> lists) {
        super(context,lists);
    }

    public View getView(int position, View view, ViewGroup viewGroup) {
        Holder hold;
        if (view == null) {
            view = View.inflate(context, R.layout.item_more, null);
            hold = new Holder(view);
            view.setTag(hold);
        } else {
            hold = (Holder) view.getTag();
        }
        hold.txt.setText(((City_2)listDatas.get(position)).getName());
        hold.txt.setTextColor(0xFF666666);
        if (position == super.position) {
            hold.txt.setTextColor(0xFFFF8C00);
        }
        return view;
    }

    private class Holder {
        TextView txt;

        public Holder(View view) {
            txt = (TextView) view.findViewById(R.id.moreitem_txt);
        }
    }
}
