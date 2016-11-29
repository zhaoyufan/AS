package com.zyf.rxjavaretrofit_demo.ui.main;

import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zyf.rxjavaretrofit_demo.R;
import com.zyf.rxjavaretrofit_demo.adapter.NewsAdapter;
import com.zyf.rxjavaretrofit_demo.model.view.NewsListener;
import com.zyf.rxjavaretrofit_demo.ui.base.BaseFragment;
import com.zyf.rxjavaretrofit_demo.ui.main.adapter.FragmentMainTabAdapter;
import com.zyf.rxjavaretrofit_demo.view.pullrecyclerview.PullRecyclerView;

import java.util.List;

/**
 * Created by ZYF on 2016/11/16.
 */
public class FragmentTab1 extends BaseFragment implements View.OnClickListener
{
    LinearLayout rl_title;
    TextView tv_search;
    ImageView iv_qr;//二维码
    PullRecyclerView mRecyclerView;
    NewsAdapter newsAdapter;
    List<Object> listbanner, listnews;
    TabLayout tabLayout;
    ViewPager viewPager;
    FragmentMainTabAdapter fragmentMainTabAdapter;
    private String[] mTabTitles;
    private Integer[] mTabIcons;
    private Fragment[] mFragments;

//    int y, //滑动距离
//            bannerH;//banner高度
//    boolean isPullDown = false;//是否是下拉状态
//
//    int page;

    @Override
    public int getContentView() {
        return R.layout.fragment_1;
    }

    @Override
    public void initView() {
        rl_title = (LinearLayout) $(R.id.rl_title);//标题栏
        rl_title.getBackground().setAlpha(0);
        tv_search = (TextView) $(R.id.tv_search);
        iv_qr = (ImageView) $(R.id.iv_qr);
        tabLayout = (TabLayout) $(R.id.fragmnet_1_tabLayout);
        viewPager = (ViewPager) $(R.id.fragmnet_1_viewpager);
        mTabTitles = new String[]{"1", "2"};
        mTabIcons = new Integer[]{R.drawable.tab1_selector, R.drawable.tab2_selector};
        mFragments = new Fragment[]{new FragmentTab1_1(), new FragmentTab1_2()};
        setupViewPager();
        setupTabLayout();

//        mRecyclerView = (PullRecyclerView) $(R.id.mRecyclerView);
//        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        mRecyclerView.setOnHeaderRefreshListener(this);//设置下拉监听
//        mRecyclerView.setOnFooterRefreshListener(this);//设置上拉监听
//        mRecyclerView.setOnPullDownScrollListener(this);//设置下拉滑动监听
//        mRecyclerView.setCanScrollAtRereshing(false);//设置正在刷新时是否可以滑动，默认不可滑动
//        mRecyclerView.setCanPullDown(true);//设置是否可下拉
//        mRecyclerView.setCanPullUp(true);//设置是否可上拉
////        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
////            @Override
////            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
////                super.onScrollStateChanged(recyclerView, newState);
////            }
////
////            @Override
////            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {//监听滑动距离以改变标题栏透明度
////                super.onScrolled(recyclerView, dx, dy);
////                y += dy;
////                if (y >= bannerH) {
////                    rl_title.getBackground().setAlpha(255);
////                    rl_title.setVisibility(View.VISIBLE);
////                } else if (y >= 0 && y < bannerH) {
////                    if (isPullDown) {
////                        rl_title.setVisibility(View.GONE);
////                    } else {
////                        rl_title.getBackground().setAlpha((int) (255 * ((double) y / bannerH)));
////                        rl_title.setVisibility(View.VISIBLE);
////                    }
////                } else {
////                    rl_title.getBackground().setAlpha(0);
////                    rl_title.setVisibility(View.GONE);
////                }
////            }
////        });
    }
    private void setupTabLayout() {
        //TabGravity:放置Tab的Gravity,有GRAVITY_CENTER 和 GRAVITY_FILL两种效果。顾名思义，一个是居中，另一个是尽可能的填充
        //（注意，GRAVITY_FILL需要和MODE_FIXED一起使用才有效果）
        //TabMode:布局中Tab的行为模式（behavior mode），有两种值：MODE_FIXED 和 MODE_SCROLLABLE。
        //MODE_FIXED:固定tabs，并同时显示所有的tabs。
        //MODE_SCROLLABLE：可滚动tabs，显示一部分tabs，在这个模式下能包含长标签和大量的tabs，最好用于用户不需要直接比较tabs
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            Log.d("aaa",""+tabLayout.getTabCount());
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(fragmentMainTabAdapter.getTabView(i));//设置tab内容
        }
        tabLayout.requestFocus();
    }

    private void setupViewPager() {
        fragmentMainTabAdapter = new FragmentMainTabAdapter(context, getFragmentManager(), mFragments, mTabTitles, mTabIcons);
        viewPager.setAdapter(fragmentMainTabAdapter);
        viewPager.setOffscreenPageLimit(2);//设置相邻两个页面会被缓存
    }

    @Override
    public void initClick() {
        tv_search.setOnClickListener(this);
        iv_qr.setOnClickListener(this);
        //initRecyclerView();
    }

    @Override
    public void initData() {

    }
//
//    @Override
//    public void initData() {
//        bannerH = SysUtil.dip2px(context, 200);//将banner高度转为px
//        listbanner = new ArrayList<>();
//        listnews = new ArrayList<>();
////        doHeaderRefresh(this);
//    }



//    /**
//     * 上拉加载
//     *
//     * @param view
//     */
//    @Override
//    public void onFooterRefresh(PullBaseView view) {
//        doFootRefresh(this);
//    }
//
//    private void doFootRefresh(final NewsListener listener) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                listener.loadMoreSuccess("suceess");
//            }
//        },1500);
//
//    }
//
//    /**
//     * 下拉刷新
//     * 实际应该在这里发送请求，在我的请求NewsListener中去根据成功/失败来加载数据
//     * @param view
//     */
//    @Override
//    public void onHeaderRefresh(PullBaseView view) {
//        doHeaderRefresh(this);
//
//    }

    private void doHeaderRefresh(final NewsListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.refreshSuccess("suceess");
            }
        },1500);

}
//    /**
//     * item点击监听
//     *
//     * @param position
//     */
//    @Override
//    public void onItemClick(int position) {
//        Toast.makeText(context, ((News) listnews.get(position)).getTitle(), Toast.LENGTH_SHORT).show();
//    }

//    @Override
//    public void onPullDownScrolled() {
//        isPullDown = true;
//        rl_title.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void onPullDownFinished() {
//        isPullDown = false;
//        rl_title.setVisibility(View.VISIBLE);
//    }
//    /**
//     * @param position item position
//     * @param viewtype 点击的view的类型，调用时根据不同的view传入不同的值加以区分
//     */
//    @Override
//    public void onItemViewClick(int position, int viewtype) {
//        switch (viewtype) {
//            case 1:
//                Toast.makeText(context, "新闻1", Toast.LENGTH_SHORT).show();
//                break;
//            case 2:
//                Toast.makeText(context, "新闻2", Toast.LENGTH_SHORT).show();
//                break;
//            case 3:
//                Toast.makeText(context, "新闻3", Toast.LENGTH_SHORT).show();
//                break;
//            case 4:
//                Toast.makeText(context, "新闻4", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_qr:
                Toast.makeText(context, "二维码", Toast.LENGTH_SHORT).show();
                break;
        }
    }

//    /**
//     * 刷新成功回调方法
//     * @param message
//     */
//    @Override
//    public void refreshSuccess(String message) {
//        mRecyclerView.onHeaderRefreshComplete();
//        //banner 模拟数据
//        listbanner.clear();
//        ImageModel imageModel = new ImageModel();
//        imageModel.setUrl("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=650d5402a318972bbc3a07cad6cd7b9d/9f2f070828381f305c3fe5bfa1014c086e06f086.jpg");
//        listbanner.add(imageModel);
//        imageModel = new ImageModel();
//        imageModel.setUrl("https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=a219dde79125bc31345d06986ede8de7/a5c27d1ed21b0ef494399077d5c451da80cb3ec1.jpg");
//        listbanner.add(imageModel);
//        imageModel = new ImageModel();
//        imageModel.setUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2040796625,1810502195&fm=111&gp=0.jpg");
//        listbanner.add(imageModel);
//        //news 模拟数据
//        listnews.clear();
//        News news1 = new News("1","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","1","1","1");
//        News news2 = new News("2","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","2","2","2");
//        News news3 = new News("3","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","3","3","3");
//        News news4 = new News("4","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","4","4","4");
//        News news5 = new News("5","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","5","5","5");
//        News news6 = new News("6","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","6","6","6");
//        News news7 = new News("7","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","7","7","7");
//        News news8 = new News("8","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","8","8","8");
//        News news9 = new News("9","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","9","9","9");
//        News news10 = new News("10","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","10","10","10");
//        listnews.add(news1);
//        listnews.add(news2);
//        listnews.add(news3);
//        listnews.add(news4);
//        listnews.add(news5);
//        listnews.add(news6);
//        listnews.add(news7);
//        listnews.add(news8);
//        listnews.add(news9);
//        listnews.add(news10);
//        initRecyclerView();
//    }
//
//    /**
//     * 加载更多成功回调方法
//     * @param message
//     */
//    @Override
//    public void loadMoreSuccess(String message) {
//        mRecyclerView.onFooterRefreshComplete();
//        News news1 = new News("新增1","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","新增1","新增1","新增1");
//        News news2 = new News("新增2","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","新增2","新增2","新增2");
//        listnews.add(news1);
//        listnews.add(news2);
//        newsAdapter.notifyDataSetChanged();
//    }
//
//    /**
//     * 失败
//     * @param message
//     */
//    @Override
//    public void fail(String message) {
//
//    }
}
