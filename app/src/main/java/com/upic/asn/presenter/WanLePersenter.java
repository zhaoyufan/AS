package com.upic.asn.presenter;

import android.widget.Toast;

import com.upic.asn.api.ApiService;
import com.upic.asn.api.ApiUtil;
import com.upic.asn.api.RxSubscribe;
import com.upic.asn.model.ActivityArea;
import com.upic.asn.model.Banner;
import com.upic.asn.model.Recommend;
import com.upic.asn.model.view.WanLeListener;
import com.upic.asn.view.pullrecyclerview.PullBaseView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZYF on 2016/12/4.
 */
public class WanLePersenter extends BasePresenter{

    public WanLePersenter() {
        service = ApiUtil.createApiService();
    }

    /**
     * 加载banner图
     * @param listener
     */
    public void getBannerDatas(final WanLeListener listener){
        service.getBannerDatas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribe<List<Banner>>() {
                    @Override
                    protected void _onNext(List<Banner> banners) {
                        listener.loadBanner(banners);
                    }

                    @Override
                    protected void _onError(String message) {
                        listener.fail(message,1);
                    }
                });
    }

    public void getActivityAreaDatas(final WanLeListener listener){
        service.getActivityAreaDatas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribe<List<ActivityArea>>() {
                    @Override
                    protected void _onNext(List<ActivityArea> activityAreas) {
                        listener.loadActivityArea(activityAreas);
                    }

                    @Override
                    protected void _onError(String message) {
                        listener.fail(message,2);
                    }
                });
    }
    /**
     * 推荐数据获取
     * @param listRecommends
     * @param mRecyclerView
     * @param listener
     */
    public void getRecommonedDatas(final List<Recommend> listRecommends, final PullBaseView mRecyclerView, final WanLeListener listener){
        service.getRecommendDatas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribe<Recommend>() {
                    @Override
                    protected void _onNext(Recommend recommend) {
                        listRecommends.clear();
                        listRecommends.add(recommend);
                        listener.refreshSuccess(listRecommends);
                    }
                    @Override
                    protected void _onError(String message) {
                        mRecyclerView.onHeaderRefreshComplete();
                        //测试时，避免连接服务器失败而导致其他数据也不加载
                        listener.fail("连接服务器失败!",3);
                    }
                });
    }
}
