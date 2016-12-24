package com.upic.asn.ui.main.store_detail.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.upic.asn.R;
import com.upic.asn.adapter.base.BaseAdapter;
import com.upic.asn.model.Banner;
import com.upic.asn.model.Store;
import com.upic.asn.ui.main.adapter.MyPagerAdapter;
import com.upic.asn.ui.main.store_detail.bean.StoreDetail;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by ZYF on 2016/12/23.
 */
public class StoreDetailAdapter extends BaseAdapter {
    private static final int TYPE_ITEM_STORE = 0;//viewpager

    private static final int TYPE_ITEM = 1;//area

    private static final int TYPE_ITEM_FOOTER = 2;//footer

    private boolean showFooter = true;
    boolean isViewPagerLoadScucess = false;//viewpager是否加载成功

    public StoreDetailAdapter(Context context, List<Object> listBanners,List<Object> listStoreDetail, List<Object> listStores, OnViewClickListener onViewClickListener) {
        super(context, listBanners,listStoreDetail,listStores,onViewClickListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM_STORE) {
            return new StoreViewHolder(mInflater.inflate(R.layout.item_store_detail, parent, false));
        }else if (viewType == TYPE_ITEM_FOOTER){
            return new FooterViewHolder(mInflater.inflate(R.layout.load_footer,parent,false));
        }else{
            return new StoreRecommendViewHolder(mInflater.inflate(R.layout.item_store_detail_recommend,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        super.onBindViewHolder(holder, position);
        if(position == 0){
            StoreViewHolder storeViewHolder = (StoreViewHolder) holder;
            if (!isViewPagerLoadScucess) {
                initViewPager(storeViewHolder);
            }
            StoreDetail storeDetail = (StoreDetail) listDatas2.get(position);
            storeViewHolder.storeName.setText(storeDetail.getStoreName());
            storeViewHolder.storePeople.setText(storeDetail.getStorePeople());
            storeViewHolder.storePrice.setText(storeDetail.getStorePrice());
            storeViewHolder.storePeopleCount.setText(storeDetail.getStorePeopleCount());
            storeViewHolder.storeCostPrice.setText(storeDetail.getStoreCostPrice());
            storeViewHolder.storeMember.setText(storeDetail.getStoreMember());
            storeViewHolder.storeAddress.setText(storeDetail.getStoreAddress());
            storeViewHolder.storeDetail.setOnClickListener(new ViewClickListener(onViewClickListener,1,1));
        }
        if (holder instanceof StoreRecommendViewHolder){
            Store store = (Store) listDatas3.get(position - 1);
            StoreRecommendViewHolder storeRecommendViewHolder = (StoreRecommendViewHolder) holder;
            if(!TextUtils.isEmpty(store.getPicture())){
                Picasso.with(context).load(store.getPicture()).error(R.mipmap.banner).into(storeRecommendViewHolder.storeImg);
            }else{
                storeRecommendViewHolder.storeImg.setImageResource(R.mipmap.banner);
            }
            storeRecommendViewHolder.storeName.setText(store.getStoreName());
            storeRecommendViewHolder.storePrice.setText("111");
            storeRecommendViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.onItemClick(position - 1);
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!showFooter) {
            return TYPE_ITEM;
        }
        if (position == 0) {
            return TYPE_ITEM_STORE;
        }
        else if(position + 1 == getItemCount()){
            return TYPE_ITEM_FOOTER;
        } else{
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        int ishow = showFooter ? 1 : 0;
        if (listDatas3 == null) {
            return ishow + 1;
        }
        return 1 + listDatas3.size() + ishow;
    }
    class StoreViewHolder extends RecyclerView.ViewHolder{
        ViewPager viewPager;
        CircleIndicator indicator;
        TextView storeName,storePeople,storePrice,storePeopleCount,storeCostPrice,storeMember,storeAddress,storeDetail;
        public StoreViewHolder(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.item_store_detail_viewpager);
            indicator = (CircleIndicator) itemView.findViewById(R.id.item_store_detail_indicator);
            storeName = (TextView) itemView.findViewById(R.id.item_store_detail_name);
            storePeople = (TextView) itemView.findViewById(R.id.item_store_detail_pepole);
            storePrice = (TextView) itemView.findViewById(R.id.item_store_detail_price);
            storePeopleCount = (TextView) itemView.findViewById(R.id.item_store_detail_people_count);
            storeCostPrice = (TextView) itemView.findViewById(R.id.item_store_detail_cost_price);
            storeMember = (TextView) itemView.findViewById(R.id.item_store_detail_member);
            storeAddress = (TextView) itemView.findViewById(R.id.item_store_detail_address);
            storeDetail = (TextView) itemView.findViewById(R.id.item_store_detail);
        }
    }
    class StoreRecommendViewHolder extends RecyclerView.ViewHolder{
        ImageView storeImg;
        TextView storeName,storePrice;
        public StoreRecommendViewHolder(View itemView) {
            super(itemView);
            storeImg = (ImageView) itemView.findViewById(R.id.item_store_detail_recommend_img);
            storeName = (TextView) itemView.findViewById(R.id.item_store_detail_recommend_name);
            storePrice = (TextView) itemView.findViewById(R.id.item_store_detail_recommend_price);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
    private void initViewPager(StoreViewHolder holder) {
        isViewPagerLoadScucess = true;
        final List<View> imageViews = new ArrayList<>();

        for (int i = 0; i < listDatas1.size(); i++) {
            final Banner banner = (Banner) listDatas1.get(i);
            View view = mInflater.inflate(R.layout.item_img, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.img);
            if (!TextUtils.isEmpty(banner.getUrl())){
                Picasso.with(context).load(banner.getUrl()).error(R.mipmap.banner).into(imageView);
            }else {
                imageView.setImageResource(R.mipmap.banner);
            }
            //设置广告点击事件
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "图片>>" + banner.getUrl(), Toast.LENGTH_SHORT).show();
                }
            });
            imageViews.add(view);
        }
        holder.viewPager.setAdapter(new MyPagerAdapter(imageViews));
        holder.indicator.setViewPager(holder.viewPager);
    }
}
