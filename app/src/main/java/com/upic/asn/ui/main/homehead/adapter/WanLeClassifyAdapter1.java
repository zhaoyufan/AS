package com.upic.asn.ui.main.homehead.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.upic.asn.R;
import com.upic.asn.ui.main.homehead.bean.City;

import java.util.List;

/**
 * Created by ZYF on 2016/12/14.
 */
public class WanLeClassifyAdapter1 extends MyBaseAdapter {

    public WanLeClassifyAdapter1(Context context, List<Object> cityList) {
        super(context,cityList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_wanle_classify_1, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.img.setImageResource(R.mipmap.ic_launcher);
        holder.txt.setText(((City)listDatas.get(position)).getName());
        holder.layout.setBackgroundColor(0xFFEBEBEB);
        if (position == super.position) {
            holder.layout.setBackgroundColor(0xFFFFFFFF);
        }
        return convertView;
    }

    class Holder{
        LinearLayout layout;
        ImageView img;
        TextView txt;

        public Holder(View view) {
            txt = (TextView) view.findViewById(R.id.wanle_classify_1_txt);
            img = (ImageView) view.findViewById(R.id.wanle_classify_1_img);
            layout = (LinearLayout) view.findViewById(R.id.mainitem_layout);
        }
    }
}
