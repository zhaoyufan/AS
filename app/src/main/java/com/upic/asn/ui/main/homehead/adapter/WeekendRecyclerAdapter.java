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
import com.upic.asn.adapter.base.BaseAdapter;
import com.upic.asn.ui.main.homehead.bean.WanLeList;

import java.util.List;


/**
 * Created by ZYF on 2016/12/16.
 */
public class WeekendRecyclerAdapter extends BaseAdapter {
    public static final int TYPE_ITEM = 0;//item

    public static final int TYPE_FOOTER = 1;//脚布局
    private boolean showFooter = true;
    public WeekendRecyclerAdapter(Context context, List<Object> listDatas) {
        super(context, listDatas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_FOOTER){
            return new FooterViewHolder(mInflater.inflate(R.layout.load_footer,parent,false));
        }else{
            return new WanLeListViewHolder(mInflater.inflate(R.layout.item_wanle_list,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if(holder instanceof WanLeListViewHolder){
            WanLeListViewHolder wanLeListViewHolder = (WanLeListViewHolder) holder;
            WanLeList wanLeList = (WanLeList) listDatas.get(position);
            if(!TextUtils.isEmpty(wanLeList.getUrl())){
                Picasso.with(context).load(wanLeList.getUrl()).into(wanLeListViewHolder.wanleImg);
            }else{
                wanLeListViewHolder.wanleImg.setImageResource(R.mipmap.banner);
            }
            wanLeListViewHolder.wanleTitle.setText(wanLeList.getTitle());
            wanLeListViewHolder.wanleCity.setText(wanLeList.getCity());
            wanLeListViewHolder.wanleDistance.setText(wanLeList.getDistance());
            wanLeListViewHolder.wanlePrice.setText(wanLeList.getLabel());
            wanLeListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int ishow = showFooter ? 1 : 0;
        if (listDatas == null) {
            return ishow;
        }
        return listDatas.size() + ishow;
    }

    @Override
    public int getItemViewType(int position) {
        if(!showFooter){
            return TYPE_ITEM;
        }
        if(position + 1 == getItemCount()){
            return TYPE_FOOTER;
        } else{
            return TYPE_ITEM;
        }
    }

    class WanLeListViewHolder extends RecyclerView.ViewHolder{
        ImageView wanleImg;
        TextView wanleTitle,wanleCity,wanleDistance,wanleLabel,wanleType,wanlePrice;
        public WanLeListViewHolder(View view) {
            super(view);
            wanleImg = (ImageView) view.findViewById(R.id.item_wanle_list_img);
            wanleTitle = (TextView) view.findViewById(R.id.item_wanle_list_title);
            wanleCity = (TextView) view.findViewById(R.id.item_wanle_list_time);
            wanleDistance = (TextView) view.findViewById(R.id.item_wanle_list_distance);
            wanlePrice = (TextView) view.findViewById(R.id.item_wanle_list_price);
        }
    }
    class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
