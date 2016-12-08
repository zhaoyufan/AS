package com.upic.asn.adapter;


import android.content.Context;
import android.util.Log;
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
    List<Object> recommends;
    Context context;

    public RecommendAdapter(List<Object> recommends, Context context) {
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
            holder.nobleChoice1 = (LinearLayout) convertView.findViewById(R.id.noble_choice1);
            holder.nobleChoice2 = (LinearLayout) convertView.findViewById(R.id.noble_choice2);
            holder.nobleChoice3 = (LinearLayout) convertView.findViewById(R.id.noble_choice3);
            holder.nobleChoice4 = (LinearLayout) convertView.findViewById(R.id.noble_choice4);
            holder.recommendTitle = (TextView) convertView.findViewById(R.id.recommend_title);
            holder.recommendName = (TextView) convertView.findViewById(R.id.recommend_name);
            holder.allSetmeal = (TextView) convertView.findViewById(R.id.all_setmeal);
            holder.nobleChoice1Pic = (ImageView) convertView.findViewById(R.id.noble_choice1_pic);
            holder.nobleChoice2Pic = (ImageView) convertView.findViewById(R.id.noble_choice2_pic);
            holder.nobleChoice3Pic = (ImageView) convertView.findViewById(R.id.noble_choice3_pic);
            holder.nobleChoice4Pic = (ImageView) convertView.findViewById(R.id.noble_choice4_pic);
            holder.nobleChoice1Title = (TextView) convertView.findViewById(R.id.noble_choice1_title);
            holder.nobleChoice2Title = (TextView) convertView.findViewById(R.id.noble_choice2_title);
            holder.nobleChoice3Title = (TextView) convertView.findViewById(R.id.noble_choice3_title);
            holder.nobleChoice4Title = (TextView) convertView.findViewById(R.id.noble_choice4_title);
            holder.nobleChoice1Price = (TextView) convertView.findViewById(R.id.noble_choice1_price);
            holder.nobleChoice2Price = (TextView) convertView.findViewById(R.id.noble_choice2_price);
            holder.nobleChoice3Price = (TextView) convertView.findViewById(R.id.noble_choice3_price);
            holder.nobleChoice4Price = (TextView) convertView.findViewById(R.id.noble_choice4_price);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Recommend recommend = (Recommend) recommends.get(position);

        holder.recommendTitle.setText(recommend.getTitle());
        holder.recommendName.setText(recommend.getTitle());

        if (recommend.getNobleChoices().get(0).getUrl().isEmpty()){
            holder.nobleChoice1Pic.setImageResource(R.mipmap.banner);
        }else{
            Picasso.with(context).load(recommend.getNobleChoices().get(0).getUrl()).error(R.mipmap.banner).into(holder.nobleChoice1Pic);
        }
        if (recommend.getNobleChoices().get(1).getUrl().isEmpty()){
            holder.nobleChoice2Pic.setImageResource(R.mipmap.banner);
        }else{
            Picasso.with(context).load(recommend.getNobleChoices().get(1).getUrl()).error(R.mipmap.banner).into(holder.nobleChoice2Pic);
        }
        if (recommend.getNobleChoices().get(2).getUrl().isEmpty()){
            holder.nobleChoice3Pic.setImageResource(R.mipmap.banner);
        }else{
            Picasso.with(context).load(recommend.getNobleChoices().get(2).getUrl()).error(R.mipmap.banner).into(holder.nobleChoice3Pic);
        }
        if (recommend.getNobleChoices().get(3).getUrl().isEmpty()){
            holder.nobleChoice4Pic.setImageResource(R.mipmap.banner);
        }else{
            Picasso.with(context).load(recommend.getNobleChoices().get(3).getUrl()).error(R.mipmap.banner).into(holder.nobleChoice4Pic);
        }

        holder.nobleChoice1Title.setText(recommend.getNobleChoices().get(0).getTitlt());
        holder.nobleChoice2Title.setText(recommend.getNobleChoices().get(1).getTitlt());
        holder.nobleChoice3Title.setText(recommend.getNobleChoices().get(2).getTitlt());
        holder.nobleChoice4Title.setText(recommend.getNobleChoices().get(3).getTitlt());

        holder.nobleChoice1Price.setText(String.valueOf(recommend.getNobleChoices().get(0).getPrice()));
        holder.nobleChoice2Price.setText(String.valueOf(recommend.getNobleChoices().get(1).getPrice()));
        holder.nobleChoice3Price.setText(String.valueOf(recommend.getNobleChoices().get(2).getPrice()));
        holder.nobleChoice4Price.setText(String.valueOf(recommend.getNobleChoices().get(3).getPrice()));
        Log.d("bbbb", ((Recommend) recommends.get(position)).getTitle());
        holder.allSetmeal.setText(("加载失败...".equals(((Recommend) recommends.get(position)).getTitle())?"加载失败... ":("剩余"+String.valueOf((((Recommend) recommends.get(position)).getNobleChoices().size()-4)))+"个套餐"));
        holder.nobleChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击第一个",Toast.LENGTH_SHORT).show();
            }
        });
        holder.nobleChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击第二个",Toast.LENGTH_SHORT).show();
            }
        });
        holder.nobleChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击第三个",Toast.LENGTH_SHORT).show();
            }
        });
        holder.nobleChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击第四个",Toast.LENGTH_SHORT).show();
            }
        });
        holder.allSetmeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击剩余套餐",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView recommendTitle, recommendName, allSetmeal;
        ImageView nobleChoice1Pic, nobleChoice2Pic, nobleChoice3Pic, nobleChoice4Pic;
        TextView nobleChoice1Title, nobleChoice2Title, nobleChoice3Title, nobleChoice4Title;
        TextView nobleChoice1Price, nobleChoice2Price, nobleChoice3Price, nobleChoice4Price;
        LinearLayout nobleChoice1, nobleChoice2, nobleChoice3, nobleChoice4;

        public ViewHolder() {
        }
    }
}
