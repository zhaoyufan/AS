package com.upic.asn.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.upic.asn.R;
import com.upic.asn.model.Banner;
import com.upic.asn.model.Community;
import com.upic.asn.model.User;
import com.upic.asn.utils.CircleImageView;
import com.upic.asn.utils.PileLayout;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


/**
 * Created by ZYF on 2016/11/17.
 */
public class ChuXingAdapter extends BaseAdapter {
    private static final int TYPE_ITEM_HOME_HEAD = 0;//homehead

    private static final int TYPE_ITEM_MARK = 1;//mark

    private static final int TYPE_ITEM_COMMUNITY= 2;//Community

    private static final int TYPE_FOOTER = 3;//脚布局
    private boolean showFooter = true;
    boolean isViewPagerLoadScucess = false;//viewpager是否加载成功

    /**
     * @param context
     * @param listDatas1
     * @param listDatas2
     * @param listDatas3
     * @param onViewClickListener 我们要设置item（header）中某控件的点击事件
     */
    public ChuXingAdapter(Context context, List<Object> listDatas1, List<Object> listDatas2,List<Object> listDatas3, OnViewClickListener onViewClickListener) {
        super(context, listDatas1, listDatas2, listDatas3,onViewClickListener);
    }
    /**
     * @param parent
     * @param viewType 应该是传入的layout，通过getItemViewType方法获得对应layout
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM_HOME_HEAD){
            return new HomeHeadViewHolder(mInflater.inflate(R.layout.item_home_head,parent,false));
        }
        else if(viewType == TYPE_ITEM_MARK){
            return new MarkViewHolder(mInflater.inflate(R.layout.activity_mark,parent,false));
        }
        else if(viewType == TYPE_FOOTER){
            return new FooterViewHolder(mInflater.inflate(R.layout.load_footer,parent,false));
        }
        else {
            return new CommunityViewHolder(mInflater.inflate(R.layout.item_community,parent,false));
        }
    }

    //这里的position是recycleView自动会获取的，从0开始，整个recycleView是一个列表，banner为一个整体占用了第一行。
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (position == 0) {//header
            HomeHeadViewHolder homeHeadViewHolder = (HomeHeadViewHolder) holder;
            if (!isViewPagerLoadScucess && listDatas1.size() > 0) {
                initViewPager(homeHeadViewHolder);
            }
            homeHeadViewHolder.ll1.setOnClickListener(new ViewClickListener(onViewClickListener, position, 1));
            homeHeadViewHolder.ll2.setOnClickListener(new ViewClickListener(onViewClickListener, position, 2));
            homeHeadViewHolder.ll3.setOnClickListener(new ViewClickListener(onViewClickListener, position, 3));
            homeHeadViewHolder.ll4.setOnClickListener(new ViewClickListener(onViewClickListener, position, 4));
            homeHeadViewHolder.ll5.setOnClickListener(new ViewClickListener(onViewClickListener, position, 5));

        } else if(position == 1){
            MarkViewHolder markViewHolder = (MarkViewHolder) holder;
//            listDatas3.add("+");
            if (markViewHolder.linearLayout1 != null){
                markViewHolder.linearLayout1.removeAllViews();
            }

            for (int i = 0; i < listDatas3.size(); i++) {
                View view = View.inflate(context, R.layout.mark_layout, null);
                TextView tv = (TextView) view.findViewById(R.id.textView1);
                tv.setText((String)listDatas3.get(i));
                tv.setTag(i);
                view.setTag(false);
                // 设置view的点击事件，与onClick中的View一致
                //否则需要在onClick中，去findViewById，找出设置点击事件的控件进行操作
                //若不如此，则无法触发点击事件
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        TextView tv = (TextView) v.findViewById(R.id.textView1);
                        if ((Boolean) v.getTag()) {
                            v.setTag(false);
                            tv.setEnabled(false);
                            Toast.makeText(context, "你取消了选择标签" + tv.getText(), Toast.LENGTH_SHORT).show();
                        } else {
                            v.setTag(true);
                            tv.setEnabled(true);
                            Toast.makeText(context, "你选择了标签" + tv.getText(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                markViewHolder.linearLayout1.addView(view);
            }
        }else if(holder instanceof CommunityViewHolder){//列表
            CommunityViewHolder communityViewHolder = (CommunityViewHolder) holder;
            Community community = (Community) listDatas2.get(position - 2);
            if (!TextUtils.isEmpty(community.getImgUrl())) {
                Picasso.with(context).load(community.getImgUrl()).error(R.mipmap.banner).into(communityViewHolder.item_com_backimg);
            } else {
                communityViewHolder.item_com_backimg.setBackgroundResource(R.mipmap.banner);
            }
            //item_community
            communityViewHolder.item_com_title.setText(community.getTitle());
            communityViewHolder.item_com_num.setText(String.valueOf(community.getNum()));
            communityViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position - 2);
                    }
                }
            });
            LayoutInflater inflater = LayoutInflater.from(context);
            if (communityViewHolder.item_com_pileLayout != null){
                communityViewHolder.item_com_pileLayout.removeAllViews();
            }
            if (community.getUsers().size() > 5) {
                for (int i = 0; i < 5; i++) {
                    List<User> userList = new ArrayList<User>();
                    userList = community.getUsers();
                    CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_community_circleimage, communityViewHolder.item_com_pileLayout, false);
                    if (!TextUtils.isEmpty(community.getImgUrl())) {
                    Picasso.with(context).load(userList.get(i).getImgUrl()).into(imageView);
                    }else{
                        imageView.setImageResource(R.mipmap.banner);
                    }

                    communityViewHolder.item_com_pileLayout.addView(imageView);
                }
                CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_community_circleimage, communityViewHolder.item_com_pileLayout, false);
                imageView.setImageResource(R.mipmap.bg);
                communityViewHolder.item_com_pileLayout.addView(imageView);

            } else if (community.getUsers().size() < 5) {
                for (int i = 0; i < community.getUsers().size(); i++) {
                    List<User> userList = new ArrayList<User>();
                    userList = community.getUsers();
                    CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_community_circleimage, communityViewHolder.item_com_pileLayout, false);
                    if (!TextUtils.isEmpty(community.getImgUrl())) {
                    Picasso.with(context).load(userList.get(i).getImgUrl()).into(imageView);
                    } else {
                        imageView.setImageResource(R.mipmap.banner);
                    }
                    communityViewHolder.item_com_pileLayout.addView(imageView);
                }
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
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
        int ishow = showFooter ? 1 : 0;
        if (listDatas2 == null) {
            return ishow + 2;
        }
        return 2 + listDatas2.size() + ishow;
    }

    @Override
    public int getItemViewType(int position) {
        if (!showFooter) {
            return TYPE_ITEM_COMMUNITY;
        }
        if (position == 0) {
            return TYPE_ITEM_HOME_HEAD;
        }
        else if (position == 1){
            return TYPE_ITEM_MARK;
        }
        else if(position + 1 == getItemCount()){
            return TYPE_FOOTER;
        } else{
            return TYPE_ITEM_COMMUNITY;
        }
    }

    class CommunityViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_icon;
        //item_community.xml
        TextView item_com_title, item_com_num;
        PileLayout item_com_pileLayout;
        ImageView item_com_backimg;


        List<String> list;
        public CommunityViewHolder(View itemView) {
            super(itemView);
            item_com_backimg = (ImageView) itemView.findViewById(R.id.item_com_backimg);
            item_com_title = (TextView) itemView.findViewById(R.id.item_com_title);
            item_com_num = (TextView) itemView.findViewById(R.id.item_com_num);
            item_com_pileLayout = (PileLayout) itemView.findViewById(R.id.item_com_pileLayout);

            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }
    }
    class HomeHeadViewHolder extends RecyclerView.ViewHolder{
        ViewPager vp;
        //head
        LinearLayout ll1, ll2, ll3, ll4, ll5;
        CircleIndicator indicator;

        public HomeHeadViewHolder(View itemView) {
            super(itemView);
            vp = (ViewPager) itemView.findViewById(R.id.vp);//banner
            indicator = (CircleIndicator) itemView.findViewById(R.id.indicator);

            //head
            ll1 = (LinearLayout) itemView.findViewById(R.id.ll_1);//附近
            ll2 = (LinearLayout) itemView.findViewById(R.id.ll_2);//最热
            ll3 = (LinearLayout) itemView.findViewById(R.id.ll_3);//周末
            ll4 = (LinearLayout) itemView.findViewById(R.id.ll_4);//发布
            ll5 = (LinearLayout) itemView.findViewById(R.id.ll_5);//熟人团
        }
    }

    class MarkViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout1;
        TextView tv;
        public MarkViewHolder(View itemView) {
            super(itemView);
            linearLayout1 = (LinearLayout) itemView.findViewById(R.id.mark_ll);
            tv = (TextView) itemView.findViewById(R.id.textView1);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
    //初始化viewpager
    private void initViewPager(HomeHeadViewHolder holder) {
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

}
