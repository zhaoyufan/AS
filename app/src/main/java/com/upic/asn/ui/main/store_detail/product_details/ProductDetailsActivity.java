package com.upic.asn.ui.main.store_detail.product_details;

import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.upic.asn.R;
import com.upic.asn.model.Banner;
import com.upic.asn.ui.base.BaseActivity;
import com.upic.asn.ui.main.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by ZYF on 2016/12/24.
 */
public class ProductDetailsActivity extends BaseActivity {
    private ViewPager viewPager;
    private CircleIndicator indicator;
    private Button book,bookNow,price;//预定，立即预定，每人多少
    private TextView back,activity,term,content,booktime;//活动，限制，内容，预定时间
    List<Object> listBanners;
    List<View> imageViews;
    @Override
    public int getContentView() {
        return R.layout.activity_product_details;
    }

    @Override
    public void initView() {
        initTitleBar("1","商品详情","",R.mipmap.book,this);
        back = (TextView) $(R.id.common_title_bar_left);
        viewPager = (ViewPager) $(R.id.activity_product_details_viewpager);
        indicator = (CircleIndicator) $(R.id.activity_product_details_indicator);
        book = (Button) $(R.id.activity_product_details_book);
        bookNow = (Button) $(R.id.activity_product_details_book_now);
        price = (Button) $(R.id.activity_product_details_price);
        activity = (TextView) $(R.id.activity_product_details_activity);
        term = (TextView) $(R.id.activity_product_details_term);
        content = (TextView) $(R.id.activity_product_details_content);
        booktime = (TextView) $(R.id.activity_product_details_book_time);
        listBanners = new ArrayList<>();
        imageViews = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.common_title_bar_left:
               onBackPressed();
               finish();
        }
    }

    @Override
    public void initClick() {
        back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        load();
        initViewPagerDatas();

    }
    private void initViewPagerDatas(){
        for (int i = 0; i < listBanners.size(); i++) {
            final Banner banner = (Banner) listBanners.get(i);
            View view = LayoutInflater.from(this).inflate(R.layout.item_img, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.img);
            if (!TextUtils.isEmpty(banner.getUrl())) {
                Picasso.with(context).load(banner.getUrl()).error(R.mipmap.banner).into(imageView);
            } else {
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
        viewPager.setAdapter(new MyPagerAdapter(imageViews));
        indicator.setViewPager(viewPager);
    }
    private void load(){
        Banner banner1 = new Banner("");
        Banner banner2 = new Banner("");
        Banner banner3 = new Banner("");
        listBanners.add(banner1);
        listBanners.add(banner2);
        listBanners.add(banner3);
    }
}
