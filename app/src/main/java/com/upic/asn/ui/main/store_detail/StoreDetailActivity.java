package com.upic.asn.ui.main.store_detail;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.upic.asn.R;
import com.upic.asn.adapter.WanLeAdapter;
import com.upic.asn.adapter.base.BaseAdapter;
import com.upic.asn.model.Banner;
import com.upic.asn.model.Store;
import com.upic.asn.model.WanLe;
import com.upic.asn.model.view.WanLeListener;
import com.upic.asn.ui.base.BaseActivity;
import com.upic.asn.ui.main.FragmentTab1;
import com.upic.asn.ui.main.FragmentTab1_1;
import com.upic.asn.ui.main.MainActivity;
import com.upic.asn.ui.main.store_detail.adapter.StoreDetailAdapter;
import com.upic.asn.ui.main.store_detail.bean.StoreDetail;
import com.upic.asn.ui.main.store_detail.product_details.ProductDetailsActivity;
import com.upic.asn.view.pullrecyclerview.PullBaseView;
import com.upic.asn.view.pullrecyclerview.PullRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZYF on 2016/12/23.
 */
public class StoreDetailActivity extends BaseActivity implements
        BaseAdapter.OnItemClickListener,//每个item的点击事件
        PullBaseView.OnHeaderRefreshListener,
        PullBaseView.OnPullDownScrollListener,
        BaseAdapter.OnViewClickListener,//item中view的点击事件，根据类型区分
        WanLeListener {
    private List<Object> listBanners,listStoreDetails,listStoreRecommends;
    private PullRecyclerView recyclerView;
    boolean isPullDown = false;//是否是下拉状态
    private TextView back;
    private StoreDetailAdapter adapter;
    private LinearLayoutManager layoutManager;
    @Override
    public int getContentView() {
        return R.layout.activity_store_detail;
    }

    @Override
    public void initView() {
        listBanners = new ArrayList<>();
        listStoreDetails = new ArrayList<>();
        listStoreRecommends = new ArrayList<>();
        recyclerView = (PullRecyclerView) findViewById(R.id.activity_store_detail_recyclerview);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        recyclerView.setOnHeaderRefreshListener(this);//设置下拉监听
        recyclerView.setOnPullDownScrollListener(this);//设置下拉滑动监听
        recyclerView.setCanScrollAtRereshing(false);//设置正在刷新时是否可以滑动，默认不可滑动
        recyclerView.setCanPullDown(true);//设置是否可下拉
        recyclerView.setCanPullUp(false);//设置是否可上拉
        layoutManager = new LinearLayoutManager(this);
        back = (TextView) findViewById(R.id.activity_store_detail_back);
    }
    private void initRecyclerView(){
        adapter = new StoreDetailAdapter(this, listBanners, listStoreDetails, listStoreRecommends, this);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initClick() {
        back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        load();
        initRecyclerView();
    }

    private void load() {
        Banner banner1 = new Banner("");
        Banner banner2 = new Banner("");
        Banner banner3 = new Banner("");
        listBanners.add(banner1);
        listBanners.add(banner2);
        listBanners.add(banner3);
        StoreDetail storeDetail = new StoreDetail("天胜四不用农庄","三人餐","245","4","555","优质餐位","宁波鄞州区");
        listStoreDetails.add(storeDetail);
        Store store1 = new Store();
        store1.setPicture("");
        store1.setStoreName("1111");
        Store store2 = new Store();
        store2.setPicture("");
        store2.setStoreName("2222");
        Store store3 = new Store();
        store3.setPicture("");
        store3.setStoreName("33333");
        listStoreRecommends.add(store1);
        listStoreRecommends.add(store2);
        listStoreRecommends.add(store3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_store_detail_back:
                //手动调用back方法
                onBackPressed();
                finish();
        }
    }

    @Override
    public void onHeaderRefresh(PullBaseView view) {

    }

    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(this, ProductDetailsActivity.class));
    }

    @Override
    public void onItemViewClick(int position, int viewtype) {

    }

    @Override
    public void dataSuccess(WanLe wanLe) {

    }

    @Override
    public void loadMoreStoresSuccess(List<Store> storeList) {

    }

    @Override
    public void fail(String message, int type) {

    }

    @Override
    public void onPullDownScrolled() {
        isPullDown = true;
    }

    @Override
    public void onPullDownFinished() {
        isPullDown = false;
    }
}
