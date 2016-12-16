package com.upic.asn.ui.main.homehead.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.upic.asn.R;
import com.upic.asn.ui.main.homehead.bean.Intelligent;

import java.util.List;

/**
 * Created by ZF on 2016/12/16.
 */

public class IntelligentAdapter extends MyBaseAdapter {

    public IntelligentAdapter(Context context, List<Object> listDatas) {
        super(context,listDatas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Holder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.pop_intelligent_item, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.img.setImageResource(R.mipmap.directions);
        holder.txt.setText(((Intelligent)listDatas.get(position)).getContent());
        if (super.position == position) {
            holder.txt.setTextColor(0xFFFF8C00);
            holder.img2.setVisibility(View.VISIBLE);
        } else {
            holder.txt.setTextColor(0xFF666666);
            holder.img2.setVisibility(View.GONE);
        }
        return convertView;
    }

    class Holder {
        ImageView img;
        TextView txt;
        ImageView img2;
        public Holder(View view) {
            txt = (TextView) view.findViewById(R.id.pop_intelligent_text);
            img = (ImageView) view.findViewById(R.id.pop_intelligent_img);
            img2 = (ImageView) view.findViewById(R.id.pop_intelligent_selector);
        }
    }
}
