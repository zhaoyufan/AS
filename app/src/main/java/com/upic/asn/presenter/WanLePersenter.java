package com.upic.asn.presenter;




import com.upic.asn.api.ApiUtil;
import com.upic.asn.api.RxSubscribe;
import com.upic.asn.model.Store;
import com.upic.asn.model.WanLe;
import com.upic.asn.model.view.WanLeListener;

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
                        listener.fail("连接服务器失败", 1);
                    }
                });
    }

    public void loadMoreStores(final WanLeListener listener){
        service.loadMoreStores()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribe<List<Store>>() {
                    @Override
                    protected void _onNext(List<Store> stores) {
                        listener.loadMoreStoresSuccess(stores);
                    }
                    @Override
                    protected void _onError(String message) {
                        listener.fail("连接服务失败器",2);
                    }
                });
    }

}
