package com.upic.asn.presenter;



import com.upic.asn.api.ApiUtil;
import com.upic.asn.api.RxSubscribe;
import com.upic.asn.model.ChuXing;
import com.upic.asn.model.Community;
import com.upic.asn.model.view.ChuXingListener;

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
                        listener.fail("连接服务器失败",1);
                    }
                });
    }

    public void loadMoreCommunity(final ChuXingListener listener){
        service.loadMoreCommunity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribe<List<Community>>() {
                    @Override
                    protected void _onNext(List<Community> communities) {
                        listener.loadMoreCommunitySuccess(communities);
                    }

                    @Override
                    protected void _onError(String message) {
                        listener.fail("加载失败,",2);
                    }
                });
    }
}
