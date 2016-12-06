package com.upic.asn.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.upic.asn.R;
import com.upic.asn.model.Community;
import com.upic.asn.model.ImageModel;
import com.upic.asn.model.User;
import com.upic.asn.util.CircleImageView;
import com.upic.asn.util.PileLayout;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


/**
 * Created by ZYF on 2016/11/17.
 */
public class ChuXingAdapter extends BaseAdapter<ChuXingAdapter.MyViewHolder> {

    boolean isViewPagerLoadScucess = false;//viewpager是否加载成功

    /**
     * @param context
     * @param listDatas1          banner图片数据
     * @param listDatas2          新闻列表数据
     * @param onViewClickListener 我们要设置item（header）中某控件的点击事件
     */
    public ChuXingAdapter(Context context, List<Object> listDatas1, List<Object> listDatas2, OnViewClickListener onViewClickListener) {
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
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
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
            holder.list = new ArrayList<String>();
            for (int i = 0; i < 10; i++) {
                String str = "标签" + i;
                holder.list.add(str);
            }
            holder.list.add("+");
            for (int i = 0; i < holder.list.size(); i++) {
                View view = View.inflate(context, R.layout.mark_layout, null);
                TextView tv = (TextView) view.findViewById(R.id.textView1);
                tv.setText(holder.list.get(i));
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
                        Log.i("dxl", "TextView click");
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
                if(view == null){
                    Log.d("bbbbb","view == null");
                }
                holder.linearLayout1.addView(view);
            }
        }
        else{//列表

            final Community community = (Community) listDatas2.get(position-1);

            if (!TextUtils.isEmpty(community.getImgUrl())) {
                Picasso.with(context).load(community.getImgUrl()).error(R.mipmap.banner).into(holder.item_com_backimg);
            } else {
                holder.item_com_backimg.setBackgroundResource(R.mipmap.banner);
            }
            //item_community.xml
            holder.item_com_title.setText(community.getTitle());
            holder.item_com_num.setText(String.valueOf(community.getNum()));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position - 1);
                    }
                }
            });
            //
            LayoutInflater inflater = LayoutInflater.from(context);
            if(community.getUsers().size()>5){
                for (int i = 0; i < 5; i++) {
                    List<User> userList = new ArrayList<User>();
                    userList = community.getUsers();
                    CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_community_circleimage, holder.item_com_pileLayout, false);
                    Picasso.with(context).load(userList.get(i).getImgUrl()).into(imageView);
                    holder.item_com_pileLayout.addView(imageView);
                }
                CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_community_circleimage, holder.item_com_pileLayout, false);
                imageView.setImageResource(R.drawable.bg);
                holder.item_com_pileLayout.addView(imageView);
            } else if(community.getUsers().size()<5){
                for (int i = 0; i < community.getUsers().size(); i++) {
                    List<User> userList = new ArrayList<User>();
                    userList = community.getUsers();
                    CircleImageView imageView = (CircleImageView) inflater.inflate(R.layout.item_community_circleimage, holder.item_com_pileLayout, false);
                    Picasso.with(context).load(userList.get(i).getImgUrl()).into(imageView);
                    holder.item_com_pileLayout.addView(imageView);
                }
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position );
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
            return R.layout.item_community;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ViewPager vp;
        LinearLayout ll_1, ll_2, ll_3, ll_4, ll_5,linearLayout1;
        TextView tv;
        List<String> list;
        CircleIndicator indicator;

        ImageView iv_icon;//
        TextView tv_title, tv_des, tv_time;//

        //item_chuxing.xml
//        ImageView item_chuxing_img,item_chuxing_photo;
//        TextView item_chuxing_name,item_chuxing_type,item_chuxing_time,item_chuxing_title,item_chuxing_content,
//                item_chuxing_address,item_chuxing_liketv,item_chuxing_pingluntv;
//        LinearLayout item_chuxing_pinglun,item_chuxing_like;

        //item_community.xml
        TextView item_com_title,item_com_num;
        PileLayout item_com_pileLayout;
        ImageView item_com_backimg;
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
            linearLayout1 = (LinearLayout) view.findViewById(R.id.mark_ll);
            tv = (TextView)view.findViewById(R.id.textView1);
            //
//            gridView = (GridView) view.findViewById(R.id.hot_gridview);

//            //农场
//            item_chuxing_img = (ImageView)view.findViewById(R.id.item_chuxing_img);//头像
//            item_chuxing_photo = (ImageView)view.findViewById(R.id.item_chuxing_photo);//照片
//            item_chuxing_name = (TextView)view.findViewById(R.id.item_chuxing_name);//昵称
//            item_chuxing_type = (TextView)view.findViewById(R.id.item_chuxing_type);//个人身份比如：旅行爱好者
//            item_chuxing_time = (TextView)view.findViewById(R.id.item_chuxing_time);//发布时间
//            item_chuxing_title = (TextView)view.findViewById(R.id.item_chuxing_title);//说说标题
//            item_chuxing_content = (TextView)view.findViewById(R.id.item_chuxing_content);//说说内容
//            item_chuxing_address = (TextView)view.findViewById(R.id.item_chuxing_address);//发布地址
//            item_chuxing_liketv = (TextView)view.findViewById(R.id.item_chuxing_liketv);//点赞人数
//            item_chuxing_pingluntv = (TextView)view.findViewById(R.id.item_chuxing_pingluntv);//评论人数
//            item_chuxing_pinglun = (LinearLayout)view.findViewById(R.id.item_chuxing_pinglun);//评论按钮,给LinearLayout设置按钮点击事件
//            item_chuxing_like = (LinearLayout)view.findViewById(R.id.item_chuxing_like);//喜欢按钮
            //圈子
            item_com_backimg = (ImageView)view.findViewById(R.id.item_com_backimg);
            item_com_title = (TextView) view.findViewById(R.id.item_com_title);
            item_com_num =(TextView) view.findViewById(R.id.item_com_num);
            item_com_pileLayout = (PileLayout)view.findViewById(R.id.item_com_pileLayout);

            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);//
//            tv_title = (TextView) view.findViewById(R.id.tv_title);//标题
//            tv_des = (TextView) view.findViewById(R.id.tv_des);//内容
//            tv_time = (TextView) view.findViewById(R.id.tv_time);//时间


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

}
