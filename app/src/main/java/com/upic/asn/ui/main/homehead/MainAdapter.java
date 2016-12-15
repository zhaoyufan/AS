package com.upic.asn.ui.main.homehead;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.upic.asn.R;

import java.util.List;

/**
 * Created by ZYF on 2016/12/14.
 */
public class MainAdapter extends MyBaseAdapter {

    public MainAdapter(Context context, List<Object> cityList) {
        super(context,cityList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_main, null);
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
            txt = (TextView) view.findViewById(R.id.mainitem_txt);
            img = (ImageView) view.findViewById(R.id.mainitem_img);
            layout = (LinearLayout) view.findViewById(R.id.mainitem_layout);
        }
    }
}
