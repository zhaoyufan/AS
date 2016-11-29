package com.zyf.rxjavaretrofit_demo.ui.images;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.zyf.rxjavaretrofit_demo.R;
import com.zyf.rxjavaretrofit_demo.adapter.BaseAdapter;
import com.zyf.rxjavaretrofit_demo.adapter.ImagesAdapter;
import com.zyf.rxjavaretrofit_demo.model.News;
import com.zyf.rxjavaretrofit_demo.model.view.NewsListener;
import com.zyf.rxjavaretrofit_demo.ui.base.BaseFragment;
import com.zyf.rxjavaretrofit_demo.view.pullrecyclerview.PullBaseView;
import com.zyf.rxjavaretrofit_demo.view.pullrecyclerview.PullRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZYF on 2016/11/17.
 */
public class FragmentImageTab2 extends BaseFragment implements NewsListener,
        PullBaseView.OnHeaderRefreshListener,
        PullBaseView.OnFooterRefreshListener,
        BaseAdapter.OnItemClickListener,
        BaseAdapter.OnViewClickListener{

    PullRecyclerView mPullRecyclerView;
    ImagesAdapter imagesAdapter;
    List<Object> objectList;

    int page;

    @Override
    public int getContentView() {
        return R.layout.fragment_images_tab1;
    }

    @Override
    public void initView() {
        mPullRecyclerView = (PullRecyclerView) $(R.id.mPullRecyclerView);
        mPullRecyclerView.setOnHeaderRefreshListener(this);//设置下拉监听
        mPullRecyclerView.setOnFooterRefreshListener(this);//设置上拉监听
        mPullRecyclerView.setCanScrollAtRereshing(false);//设置正在刷新时是否可以滑动，默认不可滑动
        mPullRecyclerView.setCanPullDown(true);//设置是否可下拉
        mPullRecyclerView.setCanPullUp(true);//设置是否可上拉
    }

    @Override
    public void initClick() {

    }

    @Override
    public void initData() {
        objectList = new ArrayList<>();

        doHeaderRefresh(this);
    }

    void initRecyclerView() {
        imagesAdapter = new ImagesAdapter(context, objectList, this);
        mPullRecyclerView.setLayoutManager(new GridLayoutManager(context,2));
        imagesAdapter.setOnItemClickListener(this);
        mPullRecyclerView.setAdapter(imagesAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void refreshSuccess(String message) {
        mPullRecyclerView.onHeaderRefreshComplete();
        //news 模拟数据
        objectList.clear();
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
        objectList.add(news1);
        objectList.add(news2);
        objectList.add(news3);
        objectList.add(news4);
        objectList.add(news5);
        objectList.add(news6);
        objectList.add(news7);
        objectList.add(news8);
        objectList.add(news9);
        objectList.add(news10);
        initRecyclerView();
    }

    @Override
    public void loadMoreSuccess(String message) {
        mPullRecyclerView.onFooterRefreshComplete();
        News news1 = new News("新增1","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","新增1","新增1","新增1");
        News news2 = new News("新增2","http://ofhgnhf0s.bkt.clouddn.com/reigns.png","新增2","新增2","新增2");
        objectList.add(news1);
        objectList.add(news2);
        imagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String message) {

    }

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

    @Override
    public void onItemClick(int position) {
        Toast.makeText(context, "item>>" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemViewClick(int position, int viewtype) {
        switch (viewtype) {
            case 1:
                Toast.makeText(context, "赞>>" + position, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
