package com.upic.asn.ui.main;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.upic.asn.R;
import com.upic.asn.adapter.BaseAdapter;
import com.upic.asn.adapter.WanLeAdapter;
import com.upic.asn.model.ActivityArea;
import com.upic.asn.model.ImageModel;
import com.upic.asn.model.NobleChoice;
import com.upic.asn.model.Recommend;
import com.upic.asn.model.Store;
import com.upic.asn.model.WanLe;
import com.upic.asn.model.view.WanLeListener;
import com.upic.asn.presenter.WanLePersenter;
import com.upic.asn.ui.base.BaseFragment;
import com.upic.asn.util.SysUtil;
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
        PullBaseView.OnFooterRefreshListener,
        PullBaseView.OnPullDownScrollListener,
        BaseAdapter.OnViewClickListener,//item中view的点击事件，根据类型区分
        WanLeListener {
    PullRecyclerView mRecyclerView;
    WanLeAdapter wanLeAdapter;
    List<Object> listBanners, listRecommends, listActivityAreas, listStores;
    List<NobleChoice> listNobleChoices;
    WanLePersenter wanLePersenter;

    int y, //滑动距离
            bannerH;//banner高度
    boolean isPullDown = false;//是否是下拉状态
    int page;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (!getUserVisibleHint()){//可见是刷新
            doRefresh();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_1_1;
    }

    @Override
    public void initView() {
        mRecyclerView = (PullRecyclerView) $(R.id.mRecyclerView);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRecyclerView.setOnHeaderRefreshListener(this);//设置下拉监听
        mRecyclerView.setOnFooterRefreshListener(this);//设置上拉监听
        mRecyclerView.setOnPullDownScrollListener(this);//设置下拉滑动监听
        mRecyclerView.setCanScrollAtRereshing(false);//设置正在刷新时是否可以滑动，默认不可滑动
        mRecyclerView.setCanPullDown(true);//设置是否可下拉
        mRecyclerView.setCanPullUp(true);//设置是否可上拉
        wanLePersenter = new WanLePersenter();
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        wanLeAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(wanLeAdapter);
    }

    /**
     * 上拉加载
     *
     * @param view
     */
    @Override
    public void onFooterRefresh(PullBaseView view) {
        doFootRefresh(this);
    }

    private void doFootRefresh(final WanLeListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.loadMoreSuccess("suceess");
            }
        },1500);

    }

    /**
     * 下拉刷新
     * 实际应该在这里发送请求，在我的请求NewsListener中去根据成功/失败来加载数据
     * @param view
     */
    @Override
    public void onHeaderRefresh(PullBaseView view) {
        wanLePersenter.getWanLeDatas(this);
    }

    private void doRefresh() {
        wanLePersenter.getWanLeDatas(this);
}
    /**
     * item点击监听
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        Toast.makeText(context,"点击第"+position+"个商品",Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "周末", Toast.LENGTH_SHORT).show();
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

    /**
     * 加载更多成功回调方法
     * @param message
     */
    @Override
    public void loadMoreSuccess(String message) {
        mRecyclerView.onFooterRefreshComplete();
        Store s1 = new Store();
        s1.setStoreName("天胜四不用");
        s1.setStoreBrief("高颜值，高品质的户外拓展");
        s1.setLogo("http://ofhgnhf0s.bkt.clouddn.com/5-160PQ51923.jpg");
        s1.setPicture("http://ofhgnhf0s.bkt.clouddn.com/5-160PQ51923.jpg");
        Store s2 = new Store();
        s2.setStoreName("天胜四不用");
        s2.setStoreBrief("高颜值，高品质的户外拓展");
        s2.setLogo("http://ofhgnhf0s.bkt.clouddn.com/5-160PQ51923.jpg");
        s2.setPicture("http://ofhgnhf0s.bkt.clouddn.com/5-160PQ51923.jpg");
        listStores.add(s1);
        listStores.add(s2);
        wanLeAdapter.notifyDataSetChanged();
    }

    /**
     * banner回调方法
     * @param listBanners
     */
    @Override
    public void loadBanner(List listBanners) {
        this.listBanners.clear();
        this.listBanners = listBanners;
    }


    @Override
    public void dataSuccess(WanLe wanLe) {
        mRecyclerView.onHeaderRefreshComplete();
        listActivityAreas.clear();
        listStores.clear();
        listRecommends.clear();
        listNobleChoices.clear();
        for(Recommend recommend : wanLe.getListRecommends()){
            listRecommends.add(recommend);
        }
        for(ActivityArea activityArea : wanLe.getListActivityAreas()){
            listActivityAreas.add(activityArea);
        }
        for(Store store : wanLe.getListStores()){
            Log.d("bbb",store.getPicture());
            listStores.add(store);
        }
        Log.d("bbb","listActivityAreas长度="+listActivityAreas.size()+"listRecommends长度="+listRecommends.size()+"listStores长度="+listStores.size());
        initRecyclerView();
    }

    @Override
    public void fail(String message, int type) {
        mRecyclerView.onHeaderRefreshComplete();
        switch (type){
            case 1:break;
            case 2:Log.d("bbb",message);
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show();break;
            default:break;
        }
        //请求失败，提供失败数据，并且初始化recycleView
        loadingFaile();
        initRecyclerView();
    }

    @Override
    public void onClick(View v) {

    }
    public void loading(){
        mRecyclerView.onHeaderRefreshComplete();
        //banner 模拟数据
        listBanners.clear();
        ImageModel imageModel = new ImageModel();
        imageModel.setUrl("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=650d5402a318972bbc3a07cad6cd7b9d/9f2f070828381f305c3fe5bfa1014c086e06f086.jpg");
        listBanners.add(imageModel);
        imageModel = new ImageModel();
        imageModel.setUrl("https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=a219dde79125bc31345d06986ede8de7/a5c27d1ed21b0ef494399077d5c451da80cb3ec1.jpg");
        listBanners.add(imageModel);
        imageModel = new ImageModel();
        imageModel.setUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2040796625,1810502195&fm=111&gp=0.jpg");
        listBanners.add(imageModel);

        //area
        listActivityAreas.clear();
        ActivityArea area1 = new ActivityArea(null,null,null,"","数据加载中...");
        ActivityArea area2 = new ActivityArea(null,null,null,"http://ofhgnhf0s.bkt.clouddn.com/reigns.png","数据加载中...");
        ActivityArea area3 = new ActivityArea(null,null,null,"http://ofhgnhf0s.bkt.clouddn.com/reigns.png","数据加载中...");
        ActivityArea area4 = new ActivityArea(null,null,null,"http://ofhgnhf0s.bkt.clouddn.com/reigns.png","数据加载中...");
        listActivityAreas.add(area1);
        listActivityAreas.add(area2);
        listActivityAreas.add(area3);
        listActivityAreas.add(area4);
        listStores.clear();

        //recommend
        listNobleChoices.clear();
        listRecommends.clear();
        NobleChoice nobleChoice1 = new NobleChoice("http://ofhgnhf0s.bkt.clouddn.com/reigns.png","数据加载中...",0);
        NobleChoice nobleChoice2 = new NobleChoice("http://ofhgnhf0s.bkt.clouddn.com/reigns.png","数据加载中...",0);
        NobleChoice nobleChoice3 = new NobleChoice("http://ofhgnhf0s.bkt.clouddn.com/reigns.png","数据加载中...",0);
        NobleChoice nobleChoice4 = new NobleChoice("http://ofhgnhf0s.bkt.clouddn.com/reigns.png","数据加载中...",0);
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
        s1.setLogo("http://ofhgnhf0s.bkt.clouddn.com/5-160PQ51923.jpg");
        s1.setPicture("http://ofhgnhf0s.bkt.clouddn.com/5-160PQ51923.jpg");
        Store s2 = new Store();
        s2.setStoreName("数据加载中...");
        s2.setStoreBrief("数据加载中...");
        s2.setLogo("http://ofhgnhf0s.bkt.clouddn.com/5-160PQ51923.jpg");
        s2.setPicture("http://ofhgnhf0s.bkt.clouddn.com/5-160PQ51923.jpg");
        Store s3 = new Store();
        s3.setStoreName("数据加载中...");
        s3.setStoreBrief("数据加载中...");
        s3.setLogo("http://ofhgnhf0s.bkt.clouddn.com/5-160PQ51923.jpg");
        s3.setPicture("http://ofhgnhf0s.bkt.clouddn.com/5-160PQ51923.jpg");
        Store s4 = new Store();
        s4.setStoreName("数据加载中...");
        s4.setStoreBrief("数据加载中...");
        s4.setLogo("http://ofhgnhf0s.bkt.clouddn.com/5-160PQ51923.jpg");
        s4.setPicture("http://ofhgnhf0s.bkt.clouddn.com/5-160PQ51923.jpg");
        listStores.add(s1);
        listStores.add(s2);
        listStores.add(s3);
        listStores.add(s4);
    }

    public void loadingFaile(){
        mRecyclerView.onHeaderRefreshComplete();
        //banner 模拟数据
        listBanners.clear();
        ImageModel imageModel = new ImageModel();
        imageModel.setUrl("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=650d5402a318972bbc3a07cad6cd7b9d/9f2f070828381f305c3fe5bfa1014c086e06f086.jpg");
        listBanners.add(imageModel);
        imageModel = new ImageModel();
        imageModel.setUrl("https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=a219dde79125bc31345d06986ede8de7/a5c27d1ed21b0ef494399077d5c451da80cb3ec1.jpg");
        listBanners.add(imageModel);
        imageModel = new ImageModel();
        imageModel.setUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2040796625,1810502195&fm=111&gp=0.jpg");
        listBanners.add(imageModel);

        //area
        listActivityAreas.clear();
        ActivityArea area1 = new ActivityArea(null,null,null,"","加载失败...");
        ActivityArea area2 = new ActivityArea(null,null,null,"","加载失败...");
        ActivityArea area3 = new ActivityArea(null,null,null,"","加载失败...");
        ActivityArea area4 = new ActivityArea(null,null,null,"","加载失败...");
        listActivityAreas.add(area1);
        listActivityAreas.add(area2);
        listActivityAreas.add(area3);
        listActivityAreas.add(area4);
        listStores.clear();

        //recommend
        listNobleChoices.clear();
        listRecommends.clear();
        NobleChoice nobleChoice1 = new NobleChoice("","加载失败...",0);
        NobleChoice nobleChoice2 = new NobleChoice("","加载失败...",0);
        NobleChoice nobleChoice3 = new NobleChoice("","加载失败...",0);
        NobleChoice nobleChoice4 = new NobleChoice("","加载失败...",0);
        listNobleChoices.add(nobleChoice1);
        listNobleChoices.add(nobleChoice2);
        listNobleChoices.add(nobleChoice3);
        listNobleChoices.add(nobleChoice4);
        Recommend recommend = new Recommend("加载失败...","加载失败...",listNobleChoices);
        listRecommends.add(recommend);

        //store
        listStores.clear();
        Store s1 = new Store();
        s1.setStoreName("加载失败...");
        s1.setStoreBrief("加载失败...");
        s1.setLogo("");
        s1.setPicture("");
        Store s2 = new Store();
        s2.setStoreName("加载失败...");
        s2.setStoreBrief("加载失败...");
        s2.setLogo("");
        s2.setPicture("");
        Store s3 = new Store();
        s3.setStoreName("加载失败...");
        s3.setStoreBrief("加载失败...");
        s3.setLogo("");
        s3.setPicture("");
        Store s4 = new Store();
        s4.setStoreName("加载失败...");
        s4.setStoreBrief("加载失败...");
        s4.setLogo("");
        s4.setPicture("");
        listStores.add(s1);
        listStores.add(s2);
        listStores.add(s3);
        listStores.add(s4);
    }
}
