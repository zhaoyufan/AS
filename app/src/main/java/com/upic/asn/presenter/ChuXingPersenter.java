package com.upic.asn.presenter;


import com.upic.asn.api.ApiUtil;
import com.upic.asn.api.RxSubscribe;
import com.upic.asn.model.Banner;
import com.upic.asn.model.ChuXing;
import com.upic.asn.model.WanLe;
import com.upic.asn.model.view.ChuXingListener;
import com.upic.asn.model.view.WanLeListener;

import java.security.PublicKey;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ZF on 2016/12/8.
 */

public class ChuXingPersenter extends BasePresenter {
    public ChuXingPersenter(){
        service = ApiUtil.createApiService();
    }
    /**
     * 加载banner图
     * @param listener
     */
    public void getBannerDatas(final ChuXingListener listener){
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
    public void getChuXingDatas(final ChuXingListener listener){
        service.getChuXingDatas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribe<ChuXing>() {
                    @Override
                    protected void _onNext(ChuXing chuXing) {
                        listener.dataSuccess(chuXing);
                    }

                    @Override
                    protected void _onError(String message) {
                        listener.fail("连接服务器失败",2);
                    }
                });
    }
}
