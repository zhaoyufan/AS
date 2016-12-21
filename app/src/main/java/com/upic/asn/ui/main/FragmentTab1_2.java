package com.upic.asn.ui.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.upic.asn.R;
import com.upic.asn.adapter.BaseAdapter;
import com.upic.asn.adapter.ChuXingAdapter;
import com.upic.asn.model.Banner;
import com.upic.asn.model.ChuXing;
import com.upic.asn.model.Community;
import com.upic.asn.model.User;
import com.upic.asn.model.view.ChuXingListener;
import com.upic.asn.presenter.ChuXingPersenter;
import com.upic.asn.ui.base.BaseFragment;
import com.upic.asn.utils.HttpUtils;
import com.upic.asn.utils.SysUtil;
import com.upic.asn.view.pullrecyclerview.PullBaseView;
import com.upic.asn.view.pullrecyclerview.PullRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZYF on 2016/11/16.
 * 出行圈
 */
public class FragmentTab1_2 extends BaseFragment implements
        BaseAdapter.OnItemClickListener,//每个item的点击事件
        PullBaseView.OnHeaderRefreshListener,
        PullBaseView.OnPullDownScrollListener,
        BaseAdapter.OnViewClickListener,//item中view的点击事件，根据类型区分
        ChuXingListener {
    private static final String TAG = "FragmentTab1_2";
    PullRecyclerView mRecyclerView2;
    ChuXingAdapter chuXingAdapter;
    List<Object> listBanners, listcommunity,listmarks;
    ChuXingPersenter chuXingPersenter;
    public boolean isloading = false;

    int y, //滑动距离
            bannerH;//banner高度
    boolean isPullDown = false;//是否是下拉状态
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
                    && lastVisibleItem + 1 == chuXingAdapter.getItemCount()
                    && !isloading) {
                isloading = true;
                subscription = chuXingPersenter.loadMoreCommunity(FragmentTab1_2.this);
            }
        }
    };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(!getUserVisibleHint() && isFirst){
            chuXingPersenter = new ChuXingPersenter();
            doRefresh();
            isFirst = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    int page;
    @Override
    public int getContentView() {
        return R.layout.fragment_1_2;
    }

    @Override
    public void initView() {
        mRecyclerView2 = (PullRecyclerView) $(R.id.mRecyclerView1_2);
        mRecyclerView2.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRecyclerView2.setOnHeaderRefreshListener(this);//设置下拉监听
        mRecyclerView2.setOnPullDownScrollListener(this);//设置下拉滑动监听
        mRecyclerView2.setCanScrollAtRereshing(false);//设置正在刷新时是否可以滑动，默认不可滑动
        mRecyclerView2.setCanPullDown(true);//设置是否可下拉
        mRecyclerView2.setCanPullUp(false);//设置是否可上拉
        mRecyclerView2.addOnScrollListener(onScrollListener);
    }

    @Override
    public void initClick() {

    }

    @Override
    public void initData() {
        bannerH = SysUtil.dip2px(context, 200);//将banner高度转为px
        listBanners = new ArrayList<>();
        listcommunity = new ArrayList<>();
        listmarks = new ArrayList<>();
        loading();
        initRecyclerView();
    }
    void initRecyclerView() {
        chuXingAdapter = new ChuXingAdapter(context, listBanners, listcommunity,listmarks, this);
        mRecyclerView2.setLayoutManager(layoutManager);
        chuXingAdapter.setOnItemClickListener(this);
        mRecyclerView2.setAdapter(chuXingAdapter);
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
        subscription = chuXingPersenter.getChuXingDatas(this);

    }
    /**
     * item点击监听
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        Toast.makeText(context, ((Community) listcommunity.get(position-2)).getTitle(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "附近", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(context, "最热", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(context, "周末", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(context, "发布", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(context, "熟人团", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void dataSuccess(ChuXing chuXing) {
        listmarks.clear();
        listcommunity.clear();
        listBanners.clear();

        for (Banner banner : chuXing.getListBanners()){
            listBanners.add(banner);
        }

        listmarks =chuXing.getMarksList();
        for (Community community : chuXing.getCommunityList()){
            listcommunity.add(community);
        }
        mRecyclerView2.onHeaderRefreshComplete();
        initRecyclerView();
    }

    @Override
    public void loadMoreCommunitySuccess(List<Community> communityList) {
        for (Community community : communityList){
            listcommunity.add(community);
        }
        isloading = false;
        chuXingAdapter.notifyDataSetChanged();
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
                mRecyclerView2.onHeaderRefreshComplete();
                break;
            case 2:
                isloading = false;
                chuXingAdapter.notifyDataSetChanged();
                break;
        }
    }
    @Override
    public void onClick(View v) {

    }

    void loading(){
        mRecyclerView2.onHeaderRefreshComplete();
        listBanners.clear();
        Banner banner = new Banner();
        banner.setUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2040796625,1810502195&fm=111&gp=0.jpg");
        listBanners.add(banner);
        banner = new Banner();
        banner.setUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2040796625,1810502195&fm=111&gp=0.jpg");
        listBanners.add(banner);
        banner = new Banner();
        banner.setUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2040796625,1810502195&fm=111&gp=0.jpg");
        listBanners.add(banner);
        listmarks.add("推荐");
        listmarks.add("爱情");
        listmarks.add("亲情");
        listmarks.add("友情");
        listmarks.add("旅游");
        listmarks.add("美食");

        User user1 = new User("张一","");
        User user2 = new User("张2","");
        User user3 = new User("张3","");
        User user4 = new User("张4","");
        User user5 = new User("张5","");
        User user6 = new User("张6","");
        List<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);

        Community community1 = new Community("#话题1#",100, "",users);
        Community community2 = new Community("#话题2#",100, "",users);
        Community community3 = new Community("#话题3#",100, "",users);
        listcommunity.add(community1);
        listcommunity.add(community2);
        listcommunity.add(community3);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null){
            subscription.unsubscribe();
        }
    }
}