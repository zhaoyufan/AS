package com.upic.asn.ui.main.homehead.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.upic.asn.R;
import com.upic.asn.adapter.BaseAdapter;
import com.upic.asn.ui.main.homehead.bean.WanLeList;

import java.util.List;


/**
 * Created by ZYF on 2016/12/16.
 */
public class WeekendRecyclerAdapter extends BaseAdapter<WeekendRecyclerAdapter.MyViewHolder> {


    public WeekendRecyclerAdapter(Context context, List<Object> listDatas) {
        super(context, listDatas);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(viewType,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        WanLeList wanLeList = (WanLeList) listDatas.get(position);
        if(!TextUtils.isEmpty(wanLeList.getUrl())){
            Picasso.with(context).load(wanLeList.getUrl()).into(holder.wanleImg);
        }else{
            holder.wanleImg.setImageResource(R.mipmap.banner);
        }
        holder.wanleTitle.setText(wanLeList.getTitle());
        holder.wanleCity.setText(wanLeList.getCity());
        holder.wanleDistance.setText(wanLeList.getDistance());
        holder.wanleLabel.setText(wanLeList.getLabel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_wanle_list;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView wanleImg;
        TextView wanleTitle,wanleCity,wanleDistance,wanleLabel;
        public MyViewHolder(View view) {
            super(view);
            wanleImg = (ImageView) view.findViewById(R.id.item_wanle_list_img);
            wanleTitle = (TextView) view.findViewById(R.id.item_wanle_list_title);
            wanleCity = (TextView) view.findViewById(R.id.item_wanle_list_city);
            wanleDistance = (TextView) view.findViewById(R.id.item_wanle_list_distance);
            wanleLabel = (TextView) view.findViewById(R.id.item_wanle_list_label);
        }
    }
}
