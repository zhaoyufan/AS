package com.upic.asn.presenter;

import android.util.Log;
import android.widget.Toast;

import com.upic.asn.api.ApiService;
import com.upic.asn.api.ApiUtil;
import com.upic.asn.api.RxSubscribe;
import com.upic.asn.model.ActivityArea;
import com.upic.asn.model.Banner;
import com.upic.asn.model.Recommend;
import com.upic.asn.model.Store;
import com.upic.asn.model.WanLe;
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

    public void getWanLeDatas(final WanLeListener listener){
        service.getWanLeDatas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribe<WanLe>() {
                    @Override
                    protected void _onNext(WanLe wanLe) {
                        listener.dataSuccess(wanLe);
                    }

                    @Override
                    protected void _onError(String message) {
                        listener.fail("连接服务器失败",2);
                    }
                });
    }

}
