package com.upic.asn.ui.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.upic.asn.R;
import com.upic.asn.adapter.base.BaseAdapter;
import com.upic.asn.adapter.WanLeAdapter;
import com.upic.asn.model.ActivityArea;
import com.upic.asn.model.Banner;
import com.upic.asn.model.NobleChoice;
import com.upic.asn.model.Recommend;
import com.upic.asn.model.Store;
import com.upic.asn.model.WanLe;
import com.upic.asn.model.view.WanLeListener;
import com.upic.asn.presenter.WanLePersenter;
import com.upic.asn.ui.base.BaseFragment;
import com.upic.asn.ui.main.homehead.WeekendActivity;
import com.upic.asn.ui.main.store_detail.StoreDetailActivity;
import com.upic.asn.utils.HttpUtils;
import com.upic.asn.utils.LogUtil;
import com.upic.asn.utils.SysUtil;
import com.upic.asn.view.pullrecyclerview.PullBaseView;
import com.upic.asn.view.pullrecyclerview.PullRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZYF on 2016/11/16.
 * 玩乐
 */
public class FragmentTab1_1 extends BaseFragment implements
        BaseAdapter.OnItemClickListener,//每个item的点击事件
        PullBaseView.OnHeaderRefreshListener,
        PullBaseView.OnPullDownScrollListener,
        BaseAdapter.OnViewClickListener,//item中view的点击事件，根据类型区分
        WanLeListener {

    private static final String TAG = "FragmentTab1_1";

    PullRecyclerView mRecyclerView;
    WanLeAdapter wanLeAdapter;
    List<Object> listBanners, listRecommends, listActivityAreas, listStores;
    List<NobleChoice> listNobleChoices;
    WanLePersenter wanLePersenter;
    public boolean isloading = false;

    int y, //滑动距离
            bannerH;//banner高度
    boolean isPullDown = false;//是否是下拉状态
    int page;

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = layoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            //SCROLL_STATE_IDLE
            //The RecyclerView is not currently scrolling.
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == wanLeAdapter.getItemCount()
                    && !isloading) {

                isloading = true;
                subscription = wanLePersenter.loadMoreStores(FragmentTab1_1.this);
            }

        }
    };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (!getUserVisibleHint() && isFirst){//
            wanLePersenter = new WanLePersenter();
            doRefresh();
            isFirst = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_1_1;
    }

    @Override
    public void initView() {
        mRecyclerView = (PullRecyclerView) $(R.id.mRecyclerView1_1);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRecyclerView.setOnHeaderRefreshListener(this);//设置下拉监听
        mRecyclerView.setOnPullDownScrollListener(this);//设置下拉滑动监听
        mRecyclerView.setCanScrollAtRereshing(false);//设置正在刷新时是否可以滑动，默认不可滑动
        mRecyclerView.setCanPullDown(true);//设置是否可下拉
        mRecyclerView.setCanPullUp(false);//设置是否可上拉
        mRecyclerView.addOnScrollListener(onScrollListener);
    }

    @Override
    public void initClick() {

    }

    @Override
    public void initData() {
        bannerH = SysUtil.dip2px(context, 200);//将banner高度转为px
        listBanners = new ArrayList<>();
        listNobleChoices = new ArrayList<>();
        listRecommends = new ArrayList<>();
        listActivityAreas = new ArrayList<>();
        listStores = new ArrayList<>();
        loading();
        initRecyclerView();
    }

    void initRecyclerView() {
        wanLeAdapter = new WanLeAdapter(context, listBanners, listActivityAreas, listRecommends, listStores, this);
        mRecyclerView.setLayoutManager(layoutManager);
        wanLeAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(wanLeAdapter);
    }


    /**
     * 下拉刷新
     * 实际应该在这里发送请求，在我的请求NewsListener中去根据成功/失败来加载数据
     * @param view
     */
    @Override
    public void onHeaderRefresh(PullBaseView view) {
        doRefresh();
    }

    private void doRefresh() {
        subscription = wanLePersenter.getWanLeDatas(this);
}
    /**
     * item点击监听
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        startActivity(new Intent(context, StoreDetailActivity.class));
    }

    @Override
    public void onPullDownScrolled() {
        isPullDown = true;
    }

    @Override
    public void onPullDownFinished() {
        isPullDown = false;
    }
    /**
     * @param position item position
     * @param viewtype 点击的view的类型，调用时根据不同的view传入不同的值加以区分
     */
    @Override
    public void onItemViewClick(int position, int viewtype) {
        switch (viewtype) {
            case 1:
                Toast.makeText(context, "位置="+position+"附近", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(context, "最热", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Intent intent = new Intent(context, WeekendActivity.class);
                startActivity(intent);
                break;
            case 4:
                Toast.makeText(context, "位置="+position+"发布", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(context, "位置="+position+"熟人团", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(context, "位置="+position+"area1"+((ActivityArea) listActivityAreas.get(0)).getUrl(), Toast.LENGTH_SHORT).show();
                break;
            case 7:
                Toast.makeText(context, "位置="+position+"area2", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Toast.makeText(context, "位置="+position+"area3", Toast.LENGTH_SHORT).show();
                break;
            case 9:
                Toast.makeText(context, "位置="+position+"area4", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void dataSuccess(WanLe wanLe) {
        mRecyclerView.onHeaderRefreshComplete();
        listActivityAreas.clear();
        listStores.clear();
        listRecommends.clear();
        listNobleChoices.clear();
        listBanners.clear();
        for (Banner banner : wanLe.getListBanners()){
            LogUtil.d(TAG,banner.getUrl());
            listBanners.add(banner);
        }
        for(Recommend recommend : wanLe.getListRecommends()){

            listRecommends.add(recommend);
        }
        for(ActivityArea activityArea : wanLe.getListActivityAreas()){
            LogUtil.d(TAG,activityArea.getUrl());
            listActivityAreas.add(activityArea);
        }
        for(Store store : wanLe.getListStores()){
            listStores.add(store);
        }
        initRecyclerView();
    }

    /**
     * 上拉加载成功回调方法
     * @param storeList
     */
    @Override
    public void loadMoreStoresSuccess(List<Store> storeList) {
        for (Store store : storeList){
            listStores.add(store);
        }
        isloading = false;
        wanLeAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String message, int type) {
        if(HttpUtils.isNetworkAvailable(context)){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"请检查网络",Toast.LENGTH_SHORT).show();
        }
        if(subscription != null){
            subscription.unsubscribe();
        }
        switch (type){
            case 1:
                loading();
                initRecyclerView();
                mRecyclerView.onHeaderRefreshComplete();
                break;
            case 2:
                isloading = false;
                wanLeAdapter.notifyDataSetChanged();
                break;
            default:break;
        }
    }

    @Override
    public void onClick(View v) {

    }
    public void loading(){
        mRecyclerView.onHeaderRefreshComplete();
        //banner 模拟数据
        listBanners.clear();
        Banner banner = new Banner();
        banner.setUrl("");
        listBanners.add(banner);
        banner = new Banner();
        banner.setUrl("");
        listBanners.add(banner);
        banner = new Banner();
        banner.setUrl("");
        listBanners.add(banner);

        //area
        listActivityAreas.clear();
        ActivityArea area1 = new ActivityArea(null,null,null,"","数据加载中...");
        ActivityArea area2 = new ActivityArea(null,null,null,"","数据加载中...");
        ActivityArea area3 = new ActivityArea(null,null,null,"","数据加载中...");
        ActivityArea area4 = new ActivityArea(null,null,null,"","数据加载中...");
        listActivityAreas.add(area1);
        listActivityAreas.add(area2);
        listActivityAreas.add(area3);
        listActivityAreas.add(area4);
        listStores.clear();

        //recommend
        listNobleChoices.clear();
        listRecommends.clear();
        NobleChoice nobleChoice1 = new NobleChoice("","数据加载中...",0);
        NobleChoice nobleChoice2 = new NobleChoice("","数据加载中...",0);
        NobleChoice nobleChoice3 = new NobleChoice("","数据加载中...",0);
        NobleChoice nobleChoice4 = new NobleChoice("","数据加载中...",0);
        listNobleChoices.add(nobleChoice1);
        listNobleChoices.add(nobleChoice2);
        listNobleChoices.add(nobleChoice3);
        listNobleChoices.add(nobleChoice4);
        Recommend recommend = new Recommend("数据加载中...","数据加载中...",listNobleChoices);
        listRecommends.add(recommend);

        //store
        listStores.clear();
        Store s1 = new Store();
        s1.setStoreName("数据加载中...");
        s1.setStoreBrief("数据加载中...");
        s1.setLogo("");
        s1.setPicture("");
        Store s2 = new Store();
        s2.setStoreName("数据加载中...");
        s2.setStoreBrief("数据加载中...");
        s2.setLogo("");
        s2.setPicture("");
        Store s3 = new Store();
        s3.setStoreName("数据加载中...");
        s3.setStoreBrief("数据加载中...");
        s3.setLogo("");
        s3.setPicture("");
        Store s4 = new Store();
        s4.setStoreName("数据加载中...");
        s4.setStoreBrief("数据加载中...");
        s4.setLogo("");
        s4.setPicture("");
        listStores.add(s1);
        listStores.add(s2);
        listStores.add(s3);
        listStores.add(s4);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null){
            subscription.unsubscribe();
        }
    }
}
