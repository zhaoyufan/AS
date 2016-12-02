package com.upic.asn.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.upic.asn.R;
import com.upic.asn.model.ImageModel;
import com.upic.asn.model.News;

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
     * @param onViewClickListener 我们要设置item（header）中某控件的点击事件
     */
    public WanLeAdapter(Context context, List<Object> listDatas1, List<Object> listDatas2, OnViewClickListener onViewClickListener) {
        super(context, listDatas1, listDatas2, onViewClickListener);
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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        super.onBindViewHolder(holder, position);//当前环境不继承父类的点击事件
        if (position == 0) {//header
            if (!isViewPagerLoadScucess && listDatas1.size() > 0) {
                initViewPager(holder);
            }
            holder.ll_1.setOnClickListener(new ViewClickListener(onViewClickListener, position, 1));
            holder.ll_2.setOnClickListener(new ViewClickListener(onViewClickListener, position, 2));
            holder.ll_3.setOnClickListener(new ViewClickListener(onViewClickListener, position, 3));
            holder.ll_4.setOnClickListener(new ViewClickListener(onViewClickListener, position, 4));
            holder.ll_5.setOnClickListener(new ViewClickListener(onViewClickListener, position, 5));
        }
        else{//列表
            News news = (News) listDatas2.get(position - 1);//转换（注意：是position-1）
            if (!TextUtils.isEmpty(news.getPicUrl())) {
                Picasso.with(context).load(news.getPicUrl()).error(R.mipmap.banner).into(holder.iv_icon);
            } else {
                holder.iv_icon.setImageResource(R.mipmap.banner);
            }
            holder.tv_title.setText(news.getTitle());//
            holder.tv_des.setText(news.getDescription());//
            holder.tv_time.setText(news.getCtime());//
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position - 1);
                    }
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
        return 1 + listDatas2.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.layout.item_home_head;
//        } else if (position == 1){
//            return R.layout.item_news;
        } else {
            return R.layout.item_news;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ViewPager vp;
        LinearLayout ll_1, ll_2, ll_3, ll_4, ll_5;
        CircleIndicator indicator;

        GridView gridView;

        ImageView iv_icon;//
        TextView tv_title, tv_des, tv_time;//

        public MyViewHolder(View view) {
            super(view);
            vp = (ViewPager) view.findViewById(R.id.vp);//banner
            indicator = (CircleIndicator) view.findViewById(R.id.indicator);

            //head
            ll_1 = (LinearLayout) view.findViewById(R.id.ll_1);//附近
            ll_2 = (LinearLayout) view.findViewById(R.id.ll_2);//最热
            ll_3 = (LinearLayout) view.findViewById(R.id.ll_3);//周末
            ll_4 = (LinearLayout) view.findViewById(R.id.ll_4);//发布
            ll_5 = (LinearLayout) view.findViewById(R.id.ll_5);//熟人团

            //
//            gridView = (GridView) view.findViewById(R.id.hot_gridview);

            //农场
            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);//
            tv_title = (TextView) view.findViewById(R.id.tv_title);//标题
            tv_des = (TextView) view.findViewById(R.id.tv_des);//内容
            tv_time = (TextView) view.findViewById(R.id.tv_time);//时间


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

    //初始化 gridView
    private void initHotGridView(MyViewHolder holder){
        holder.gridView.setAdapter(new android.widget.BaseAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }
        });
    }

}
