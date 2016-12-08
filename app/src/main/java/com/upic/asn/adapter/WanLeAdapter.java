package com.upic.asn.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.upic.asn.R;
import com.upic.asn.model.ActivityArea;
import com.upic.asn.model.ImageModel;
import com.upic.asn.model.Recommend;
import com.upic.asn.model.Store;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by ZYF on 2016/11/17.
 */
public class WanLeAdapter extends BaseAdapter<WanLeAdapter.MyViewHolder> {

    boolean isViewPagerLoadScucess = false;//viewpager是否加载成功

    /**
     * @param context
     * @param listDatas1          banner图片数据
     * @param listDatas2          新闻列表数据
     * @param listActivityAreas       区域数据源
     * @param listRecommends          推荐数据源
     * @param onViewClickListener 我们要设置item（header）中某控件的点击事件
     */
//    public WanLeAdapter(Context context, List<Object> listDatas1, List<Object> listDatas2, List<ActivityArea> listActivityAreas, List<Recommend> listRecommends, OnViewClickListener onViewClickListener) {
//        super(context, listDatas1, listDatas2, listActivityAreas, listRecommends, onViewClickListener);
//    }
    /**
     * @param context
     * @param listDatas1          banner图片数据
     * @param listActivityAreas       区域数据源
     * @param listRecommends          推荐数据源
     * @param listStores          商家
     * @param onViewClickListener 我们要设置item（header）中某控件的点击事件
     */
    public WanLeAdapter(Context context, List<Object> listDatas1, List<Object> listActivityAreas, List<Object> listRecommends, List<Object> listStores, OnViewClickListener onViewClickListener) {
        super(context, listDatas1, listActivityAreas, listRecommends, listStores, onViewClickListener);
    }

    /**
     *
     * @param parent
     * @param viewType 应该是传入的layout，通过getItemViewType方法获得对应layout
     * @return
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(viewType, parent, false));
    }
    //这里的position是recycleView自动会获取的，从0开始，整个recycleView是一个列表，banner为一个整体占用了第一行。
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//        super.onBindViewHolder(holder, position);//当前环境不继承父类的点击事件
        if (position == 0) {//header
            if (!isViewPagerLoadScucess) {
                initViewPager(holder);
            }
            holder.ll1.setOnClickListener(new ViewClickListener(onViewClickListener, position, 1));
            holder.ll2.setOnClickListener(new ViewClickListener(onViewClickListener, position, 2));
            holder.ll3.setOnClickListener(new ViewClickListener(onViewClickListener, position, 3));
            holder.ll4.setOnClickListener(new ViewClickListener(onViewClickListener, position, 4));
            holder.ll5.setOnClickListener(new ViewClickListener(onViewClickListener, position, 5));
        } else if (position == 1) {//area
            ActivityArea activityArea1 = (ActivityArea) listDatas2.get(0);
            ActivityArea activityArea2 = (ActivityArea) listDatas2.get(1);
            ActivityArea activityArea3 = (ActivityArea) listDatas2.get(2);
            ActivityArea activityArea4 = (ActivityArea) listDatas2.get(3);

            holder.area1Title.setText(activityArea1.getTitle());
            holder.area2Title.setText(activityArea2.getTitle());
            holder.area3Title.setText(activityArea3.getTitle());
            holder.area4Title.setText(activityArea4.getTitle());
            if (activityArea1.getUrl().isEmpty()){
                holder.area1Img.setImageResource(R.mipmap.banner);
            }else{
                Picasso.with(context).load(activityArea1.getUrl()).error(R.mipmap.banner).into(holder.area1Img);
            }
            if (activityArea2.getUrl().isEmpty()){
                holder.area2Img.setImageResource(R.mipmap.banner);
            }else{
                Picasso.with(context).load(activityArea2.getUrl()).error(R.mipmap.banner).into(holder.area2Img);
            }
            if (activityArea3.getUrl().isEmpty()){
                holder.area3Img.setImageResource(R.mipmap.banner);
            }else{
                Picasso.with(context).load(activityArea3.getUrl()).error(R.mipmap.banner).into(holder.area3Img);
            }if (activityArea4.getUrl().isEmpty()){
                holder.area4Img.setImageResource(R.mipmap.banner);
            }else{
                Picasso.with(context).load(activityArea4.getUrl()).error(R.mipmap.banner).into(holder.area4Img);
            }

            holder.area1.setOnClickListener(new ViewClickListener(onViewClickListener, position, 6));
            holder.area2.setOnClickListener(new ViewClickListener(onViewClickListener, position, 7));
            holder.area3.setOnClickListener(new ViewClickListener(onViewClickListener, position, 8));
            holder.area4.setOnClickListener(new ViewClickListener(onViewClickListener, position, 9));
        } else if (position == 2){
            initListView(holder);
        } else{//列表

            final Store store = (Store) listDatas4.get(position - 3);
            if (store.getLogo().isEmpty()){
                holder.storeLogo.setImageResource(R.mipmap.banner);
            }else {
                Picasso.with(context).load(store.getLogo()).error(R.mipmap.banner).into(holder.storeLogo);
            }
            if (store.getLogo().isEmpty()){
                holder.storePic.setImageResource(R.mipmap.banner);
            }else {
                Picasso.with(context).load(store.getPicture()).error(R.mipmap.banner).into(holder.storePic);
            }
            holder.storeName.setText(store.getStoreName());
            holder.storeBrief.setText(store.getStoreBrief());
            holder.storePic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"点击商店图片"+store.getPicture(),Toast.LENGTH_SHORT).show();
                }
            });
            holder.storeBrief.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"点击商店brief"+store.getStoreBrief(),Toast.LENGTH_SHORT).show();
                }
            });
            holder.goStore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"进店逛逛",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    /**
     * 注意事项：
     * 因为banner为一个header，属于recycleview的一个item，
     * 当前情况下，listDatas1为banner数据，listDatas2为新闻列表数据
     * 即便此时listDatas1有多条数据，但是是属于头部item 的数据源，所以整体的ItemCount=1+listDatas2.size()，1即代表header；
     * <p>
     * 如果是非header的情况，即两个数据源的列表，则ItemCount=listDatas1.size()+listDatas2.size()；
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 1 + 1 + listDatas4.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.layout.item_home_head;
        } else if (position == 1){
            return R.layout.item_area;
        } else if (position == 2){
            return R.layout.recommend;
        } else {
            return R.layout.item_store;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ViewPager vp;
        //head
        LinearLayout ll1, ll2, ll3, ll4, ll5;
        CircleIndicator indicator;
        //area
        LinearLayout area1,area2,area3,area4;
        ImageView area1Img, area2Img, area3Img, area4Img;
        TextView area1Title, area2Title, area3Title, area4Title;
        //recommend
        ListView listView;
        //store
        ImageView storeLogo,storePic;
        TextView storeName,storeBrief,goStore;

        public MyViewHolder(View view) {
            super(view);
            vp = (ViewPager) view.findViewById(R.id.vp);//banner
            indicator = (CircleIndicator) view.findViewById(R.id.indicator);

            //head
            ll1 = (LinearLayout) view.findViewById(R.id.ll_1);//附近
            ll2 = (LinearLayout) view.findViewById(R.id.ll_2);//最热
            ll3 = (LinearLayout) view.findViewById(R.id.ll_3);//周末
            ll4 = (LinearLayout) view.findViewById(R.id.ll_4);//发布
            ll5 = (LinearLayout) view.findViewById(R.id.ll_5);//熟人团

            //area
            area1 = (LinearLayout) view.findViewById(R.id.area1);
            area2 = (LinearLayout) view.findViewById(R.id.area2);
            area3 = (LinearLayout) view.findViewById(R.id.area3);
            area4 = (LinearLayout) view.findViewById(R.id.area4);

            area1Img = (ImageView) view.findViewById(R.id.area1_img);
            area2Img = (ImageView) view.findViewById(R.id.area2_img);
            area3Img = (ImageView) view.findViewById(R.id.area3_img);
            area4Img = (ImageView) view.findViewById(R.id.area4_img);

            area1Title = (TextView) view.findViewById(R.id.area1_title);
            area2Title = (TextView) view.findViewById(R.id.area2_title);
            area3Title = (TextView) view.findViewById(R.id.area3_title);
            area4Title = (TextView) view.findViewById(R.id.area4_title);


            //recommend
            listView = (ListView) view.findViewById(R.id.recommendlistview);

            //store
            storeLogo = (ImageView) view.findViewById(R.id.store_logo);
            storePic = (ImageView) view.findViewById(R.id.store_pic);
            storeName = (TextView) view.findViewById(R.id.store_name);
            storeBrief = (TextView) view.findViewById(R.id.store_brief);
            goStore = (TextView) view.findViewById(R.id.go_store);


        }
    }
    //初始化viewpager
    private void initViewPager(MyViewHolder holder) {
        isViewPagerLoadScucess = true;
        final List<View> imageViews = new ArrayList<>();
        for (int i = 0; i < listDatas1.size(); i++) {
            final ImageModel imageModel = (ImageModel) listDatas1.get(i);
            View view = mInflater.inflate(R.layout.item_img, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.img);
            Picasso.with(context).load(imageModel.getUrl()).error(R.mipmap.banner).into(imageView);
            //设置广告点击事件
            view.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Toast.makeText(context, "图片>>" + imageModel.getUrl(), Toast.LENGTH_SHORT).show();
                                        }
            });
            imageViews.add(view);
        }
        holder.vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViews.size();
            }

            @Override
            public Object instantiateItem(ViewGroup arg0, int arg1) {
                arg0.addView(imageViews.get(arg1));
                return imageViews.get(arg1);
            }

            @Override
            public void destroyItem(ViewGroup arg0, int arg1, Object arg2) {
                arg0.removeView((View) arg2);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
        holder.indicator.setViewPager(holder.vp);
    }

    //recommonedlist初始化
    private void initListView(MyViewHolder holder){
        RecommendAdapter adapter = new RecommendAdapter(listDatas3,context);
        holder.listView.setAdapter(adapter);
    }
}
