package com.upic.asn.ui.main;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.upic.asn.R;
import com.upic.asn.adapter.BaseAdapter;
import com.upic.asn.adapter.ChuXingAdapter;
import com.upic.asn.model.Community;
import com.upic.asn.model.ImageModel;
import com.upic.asn.model.User;
import com.upic.asn.model.view.NewsListener;
import com.upic.asn.ui.base.BaseFragment;
import com.upic.asn.util.SysUtil;
import com.upic.asn.view.pullrecyclerview.PullBaseView;
import com.upic.asn.view.pullrecyclerview.PullRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZYF on 2016/11/16.
 */
public class FragmentTab1_2 extends BaseFragment implements
        BaseAdapter.OnItemClickListener,//每个item的点击事件
        PullBaseView.OnHeaderRefreshListener,
        PullBaseView.OnFooterRefreshListener,
        PullBaseView.OnPullDownScrollListener,
        BaseAdapter.OnViewClickListener,//item中view的点击事件，根据类型区分
        NewsListener {
    PullRecyclerView mRecyclerView_f_1_2;
    ChuXingAdapter chuXingAdapter;
    List<Object> listbanner, listcommunity;
    String url = "http://img3.imgtn.bdimg.com/it/u=3040533120,2016018949&fm=21&gp=0.jpg";
    String url2 = "http://img.qq745.com/uploads/allimg/151022/1-151022193521.jpg";
    String url3 = "http://p.3761.com/pic/12461391736719.png";
    String url4 = "http://ofhgnhf0s.bkt.clouddn.com/5-160PQ51923.jpg";
    int y, //滑动距离
            bannerH;//banner高度
    boolean isPullDown = false;//是否是下拉状态

    int page;


    @Override
    public int getContentView() {
        return R.layout.fragment_1_2;
    }

    @Override
    public void initView() {
        mRecyclerView_f_1_2 = (PullRecyclerView) $(R.id.mRecyclerView_f_1_2);
        mRecyclerView_f_1_2.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mRecyclerView_f_1_2.setOnHeaderRefreshListener(this);//设置下拉监听
        mRecyclerView_f_1_2.setOnFooterRefreshListener(this);//设置上拉监听
        mRecyclerView_f_1_2.setOnPullDownScrollListener(this);//设置下拉滑动监听
        mRecyclerView_f_1_2.setCanScrollAtRereshing(false);//设置正在刷新时是否可以滑动，默认不可滑动
        mRecyclerView_f_1_2.setCanPullDown(true);//设置是否可下拉
        mRecyclerView_f_1_2.setCanPullUp(true);//设置是否可上拉
    }

    @Override
    public void initClick() {

    }

    @Override
    public void initData() {
        bannerH = SysUtil.dip2px(context, 200);//将banner高度转为px
        listbanner = new ArrayList<>();
        listcommunity = new ArrayList<>();
        User user1 = new User("张一",url);
        User user2 = new User("张2",url2);
        User user3 = new User("张3",url3);
        User user4 = new User("张4",url);
        User user5 = new User("张5",url2);
        User user6 = new User("张6",url3);
        List<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        List<String> marks = new ArrayList<String>();
        marks.add("推荐");
        marks.add("爱情");
        marks.add("亲情");
        marks.add("友情");
        marks.add("旅游");
        marks.add("美食");
        Community community1 = new Community("#话题1#",100, url4,users,marks);
        Community community2 = new Community("#话题2#",100, url4,users,marks);
        Community community3 = new Community("#话题3#",100, url4,users,marks);
        listcommunity.add(community1);
        listcommunity.add(community2);
        listcommunity.add(community3);
        initRecyclerView();
        doHeaderRefresh(this);
    }

    void initRecyclerView() {
        chuXingAdapter = new ChuXingAdapter(context, listbanner, listcommunity, this);
        mRecyclerView_f_1_2.setLayoutManager(new LinearLayoutManager(context));
        chuXingAdapter.setOnItemClickListener(this);
        mRecyclerView_f_1_2.setAdapter(chuXingAdapter);
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

    private void doFootRefresh(final NewsListener listener) {
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
        doHeaderRefresh(this);
    }

    private void doHeaderRefresh(final NewsListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.refreshSuccess("suceess");
            }
        },1500);

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
    /**
     * 刷新成功回调方法
     * @param message
     */
    @Override
    public void refreshSuccess(String message) {
        mRecyclerView_f_1_2.onHeaderRefreshComplete();
        //banner 模拟数据
        listbanner.clear();
        ImageModel imageModel = new ImageModel();
        imageModel.setUrl("https://ss2.baidu.com/-vo3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=650d5402a318972bbc3a07cad6cd7b9d/9f2f070828381f305c3fe5bfa1014c086e06f086.jpg");
        listbanner.add(imageModel);
        imageModel = new ImageModel();
        imageModel.setUrl("https://ss0.baidu.com/7Po3dSag_xI4khGko9WTAnF6hhy/image/h%3D200/sign=a219dde79125bc31345d06986ede8de7/a5c27d1ed21b0ef494399077d5c451da80cb3ec1.jpg");
        listbanner.add(imageModel);
        imageModel = new ImageModel();
        imageModel.setUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2040796625,1810502195&fm=111&gp=0.jpg");
        listbanner.add(imageModel);
        //news 模拟数据
        listcommunity.clear();

        User user1 = new User("张1",url);
        User user2 = new User("张2",url2);
        User user3 = new User("张3",url3);
        User user4 = new User("张4",url);
        User user5 = new User("张5",url2);
        User user6 = new User("张6",url3);
        List<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        List<String> marks = new ArrayList<String>();
        marks.add("推荐");
        marks.add("爱情");
        marks.add("亲情");
        marks.add("友情");
        marks.add("旅游");
        marks.add("美食");
        Community community1 = new Community("#爱就讲出来#",100, url4,users,marks);
        Community community2 = new Community("#爱就说出来#",200, url4,users,marks);
        Community community3 = new Community("#爱就话出来#",300, url4,users,marks);
        Community community4 = new Community("#爱就say出来#",400, url4,users,marks);
        Community community5 = new Community("#爱就讲出来#",500, url4,users,marks);
        listcommunity.add(community1);
        listcommunity.add(community2);
        listcommunity.add(community3);
        listcommunity.add(community4);
        listcommunity.add(community5);

        initRecyclerView();
    }

    /**
     * 加载更多成功回调方法
     * @param message
     */
    @Override
    public void loadMoreSuccess(String message) {
        mRecyclerView_f_1_2.onFooterRefreshComplete();
        User user1 = new User("张一",url);
        User user2 = new User("张2",url2);
        User user3 = new User("张3",url3);
        User user4 = new User("张4",url);
        List<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        List<String> marks = new ArrayList<String>();
        marks.add("推荐");
        marks.add("爱情");
        marks.add("亲情");
        marks.add("友情");
        marks.add("旅游");
        marks.add("美食");
        Community community1 = new Community("#爱就讲出来新增1#",888, url4,users,marks);
        Community community2 = new Community("#爱就说出来新增1#",12345, url4,users,marks);

        listcommunity.add(community1);
        listcommunity.add(community2);
        chuXingAdapter.notifyDataSetChanged();
    }

    /**
     * 失败
     * @param message
     */
    @Override
    public void fail(String message) {

    }

    @Override
    public void onClick(View v) {

    }
}