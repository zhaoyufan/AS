package com.upic.asn.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.upic.asn.R;
import com.upic.asn.model.Recommend;


import java.util.List;


/**
 * Created by ZYF on 2016/12/2.
 */
public class RecommendAdapter extends BaseAdapter {
    List<Recommend> recommends;
    Context context;

    public RecommendAdapter(List<Recommend> recommends, Context context) {
        this.recommends = recommends;
        this.context = context;
    }

    @Override
    public int getCount() {
        return recommends.size();
    }

    @Override
    public Object getItem(int position) {
        return recommends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend,null);
            holder.noble_choice1 = (LinearLayout) convertView.findViewById(R.id.noble_choice1);
            holder.noble_choice2 = (LinearLayout) convertView.findViewById(R.id.noble_choice2);
            holder.noble_choice3 = (LinearLayout) convertView.findViewById(R.id.noble_choice3);
            holder.noble_choice4 = (LinearLayout) convertView.findViewById(R.id.noble_choice4);
            holder.recommend_title = (TextView) convertView.findViewById(R.id.recommend_title);
            holder.recommend_name = (TextView) convertView.findViewById(R.id.recommend_name);
            holder.noble_choice1_pic = (ImageView) convertView.findViewById(R.id.noble_choice1_pic);
            holder.noble_choice2_pic = (ImageView) convertView.findViewById(R.id.noble_choice2_pic);
            holder.noble_choice3_pic = (ImageView) convertView.findViewById(R.id.noble_choice3_pic);
            holder.noble_choice4_pic = (ImageView) convertView.findViewById(R.id.noble_choice4_pic);
            holder.noble_choice1_title = (TextView) convertView.findViewById(R.id.noble_choice1_title);
            holder.noble_choice2_title = (TextView) convertView.findViewById(R.id.noble_choice2_title);
            holder.noble_choice3_title = (TextView) convertView.findViewById(R.id.noble_choice3_title);
            holder.noble_choice4_title = (TextView) convertView.findViewById(R.id.noble_choice4_title);
            holder.noble_choice1_price = (TextView) convertView.findViewById(R.id.noble_choice1_price);
            holder.noble_choice2_price = (TextView) convertView.findViewById(R.id.noble_choice2_price);
            holder.noble_choice3_price = (TextView) convertView.findViewById(R.id.noble_choice3_price);
            holder.noble_choice4_price = (TextView) convertView.findViewById(R.id.noble_choice4_price);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Recommend recommend = (Recommend) recommends.get(position);

        holder.recommend_title.setText(recommend.getTitle());
        holder.recommend_name.setText(recommend.getName());
        Picasso.with(context).load(recommend.getNobleChoices().get(0).getUrl()).error(R.mipmap.banner).into(holder.noble_choice1_pic);
        Picasso.with(context).load(recommend.getNobleChoices().get(1).getUrl()).error(R.mipmap.banner).into(holder.noble_choice2_pic);
        Picasso.with(context).load(recommend.getNobleChoices().get(2).getUrl()).error(R.mipmap.banner).into(holder.noble_choice3_pic);
        Picasso.with(context).load(recommend.getNobleChoices().get(3).getUrl()).error(R.mipmap.banner).into(holder.noble_choice4_pic);
        holder.noble_choice1_title.setText(recommend.getNobleChoices().get(0).getTitlt());
        holder.noble_choice2_title.setText(recommend.getNobleChoices().get(1).getTitlt());
        holder.noble_choice3_title.setText(recommend.getNobleChoices().get(2).getTitlt());
        holder.noble_choice4_title.setText(recommend.getNobleChoices().get(3).getTitlt());
        holder.noble_choice1_price.setText(String.valueOf(recommend.getNobleChoices().get(0).getPrice()));
        holder.noble_choice2_price.setText(String.valueOf(recommend.getNobleChoices().get(1).getPrice()));
        holder.noble_choice3_price.setText(String.valueOf(recommend.getNobleChoices().get(2).getPrice()));
        holder.noble_choice4_price.setText(String.valueOf(recommend.getNobleChoices().get(3).getPrice()));
        holder.noble_choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击第一个",Toast.LENGTH_SHORT).show();
            }
        });
        holder.noble_choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击第二个",Toast.LENGTH_SHORT).show();
            }
        });
        holder.noble_choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击第三个",Toast.LENGTH_SHORT).show();
            }
        });
        holder.noble_choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击第四个",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView recommend_title, recommend_name;
        ImageView noble_choice1_pic, noble_choice2_pic, noble_choice3_pic, noble_choice4_pic;
        TextView noble_choice1_title, noble_choice2_title, noble_choice3_title, noble_choice4_title;
        TextView noble_choice1_price, noble_choice2_price, noble_choice3_price, noble_choice4_price;
        LinearLayout noble_choice1,noble_choice2,noble_choice3,noble_choice4;

        public ViewHolder() {
        }
    }
}
