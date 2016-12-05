package com.upic.asn.ui.main;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.upic.asn.R;
import com.upic.asn.adapter.BaseAdapter;
import com.upic.asn.adapter.WanLeAdapter;
import com.upic.asn.model.ActivityArea;
import com.upic.asn.model.ImageModel;
import com.upic.asn.model.News;
import com.upic.asn.model.NobleChoice;
import com.upic.asn.model.Recommend;
import com.upic.asn.model.view.WanLeListener;
import com.upic.asn.presenter.WanLePersenter;
import com.upic.asn.ui.base.BaseFragment;
import com.upic.asn.util.SysUtil;
import com.upic.asn.view.pullrecyclerview.PullBaseView;
import com.upic.asn.view.pullrecyclerview.PullRecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZYF on 2016/11/16.
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
    List<Object> listBanners, listnews;
    List<Recommend> listRecommends;
    List<ActivityArea> listActivityAreas;
    List<NobleChoice> listNobleChoices;
    WanLePersenter wanLePersenter;

    int y, //滑动距离
            bannerH;//banner高度
    boolean isPullDown = false;//是否是下拉状态

    int page;


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
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {//监听滑动距离以改变标题栏透明度
//                super.onScrolled(recyclerView, dx, dy);
//                y += dy;
//                if (y >= bannerH) {
//                    rl_title.getBackground().setAlpha(255);
//                    rl_title.setVisibility(View.VISIBLE);
//                } else if (y >= 0 && y < bannerH) {
//                    if (isPullDown) {
//                        rl_title.setVisibility(View.GONE);
//                    } else {
//                        rl_title.getBackground().setAlpha((int) (255 * ((double) y / bannerH)));
//                        rl_title.setVisibility(View.VISIBLE);
//                    }
//                } else {
//                    rl_title.getBackground().setAlpha(0);
//                    rl_title.setVisibility(View.GONE);
//                }
//            }
//        });
    }

    @Override
    public void initClick() {

    }

    @Override
    public void initData() {
        bannerH = SysUtil.dip2px(context, 200);//将banner高度转为px
        listBanners = new ArrayList<>();
        listnews = new ArrayList<>();
        listNobleChoices = new ArrayList<NobleChoice>();
        listRecommends = new ArrayList<Recommend>();
        listActivityAreas = new ArrayList<ActivityArea>();
        wanLePersenter = new WanLePersenter();
        monishuju();
        initRecyclerView();
        doHeaderRefresh();
    }

    void initRecyclerView() {
        wanLeAdapter = new WanLeAdapter(context, listBanners, listnews, listRecommends, this);
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
        wanLePersenter.getRecommonedDatas(listRecommends,mRecyclerView,this);
        //wanLePersenter.getBannerDatas(this);
        //wanLePersenter.getActivityAreaDatas(this);
    }

    private void doHeaderRefresh() {
//        ApiUtil.createApiService().getRecommendDatas()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new RxSubscribe<Recommend>() {
//                    @Override
//                    protected void _onNext(Recommend recommend) {
//                        listRecommends.clear();
//                        listRecommends.add(recommend);
//                        listener.refreshSuccess("success");
//                    }
//                    @Override
//                    protected void _onError(String message) {
//                        Toast.makeText(context,"请检查网络状态",Toast.LENGTH_SHORT).show();
//                        mRecyclerView.onHeaderRefreshComplete();
//                        //测试时，避免连接服务器失败而导致其他数据也不加载
//                        listener.refreshSuccess("success");
//                    }
//                });

}
    /**
     * item点击监听
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        Toast.makeText(context, ((News) listnews.get(position)).getTitle(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(context, "位置="+position+"area1", Toast.LENGTH_SHORT).show();
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
        News news1 = new News("新增1","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","新增1","新增1","新增1");
        News news2 = new News("新增2","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","新增2","新增2","新增2");
        listnews.add(news1);
        listnews.add(news2);
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

    /**
     * ActivityArea回调方法
     * @param listActivityAreas
     */
    @Override
    public void loadActivityArea(List<ActivityArea> listActivityAreas) {
        this.listActivityAreas.clear();
        this.listActivityAreas = listActivityAreas;
    }

    /**
     * wanle界面数据监听
     * @param lists
     */
    @Override
    public void refreshSuccess(List lists) {
        listRecommends = lists;
        monishuju();
    }

    @Override
    public void fail(String message, int type) {
        switch (type){
            case 1:Toast.makeText(context,"加载banner图失败",Toast.LENGTH_SHORT).show();
                break;
            case 2:Toast.makeText(context,"area区域加载失败",Toast.LENGTH_SHORT).show();
                break;
            case 3:Toast.makeText(context,"推荐数据失败",Toast.LENGTH_SHORT).show();
                break;
            case 4:break;
            default:break;
        }
        monishuju();
    }

    @Override
    public void onClick(View v) {

    }
    public void monishuju(){
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
        ActivityArea area1 = new ActivityArea("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=650d5402a318972bbc3a07cad6cd7b9d/9f2f070828381f305c3fe5bfa1014c086e06f086.jpg","1");
        ActivityArea area2 = new ActivityArea("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=650d5402a318972bbc3a07cad6cd7b9d/9f2f070828381f305c3fe5bfa1014c086e06f086.jpg","1");
        ActivityArea area3 = new ActivityArea("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=650d5402a318972bbc3a07cad6cd7b9d/9f2f070828381f305c3fe5bfa1014c086e06f086.jpg","1");
        ActivityArea area4 = new ActivityArea("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=650d5402a318972bbc3a07cad6cd7b9d/9f2f070828381f305c3fe5bfa1014c086e06f086.jpg","1");
        listActivityAreas.add(area1);
        listActivityAreas.add(area2);
        listActivityAreas.add(area3);
        listActivityAreas.add(area4);
        //news 模拟数据
        listnews.clear();
        News news1 = new News("1","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","1","1","1");
        News news2 = new News("2","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","2","2","2");
        News news3 = new News("3","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","3","3","3");
        News news4 = new News("4","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","4","4","4");
        News news5 = new News("5","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","5","5","5");
        News news6 = new News("6","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","6","6","6");
        News news7 = new News("7","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","7","7","7");
        News news8 = new News("8","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","8","8","8");
        News news9 = new News("9","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","9","9","9");
        News news10 = new News("10","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","10","10","10");
        listnews.add(news1);
        listnews.add(news2);
        listnews.add(news3);
        listnews.add(news4);
        listnews.add(news5);
        listnews.add(news6);
        listnews.add(news7);
        listnews.add(news8);
        listnews.add(news9);
        listnews.add(news10);
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
        initRecyclerView();
    }
}
