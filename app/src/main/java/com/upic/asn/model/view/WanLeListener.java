package com.upic.asn.model.view;


import com.upic.asn.model.Store;
import com.upic.asn.model.WanLe;

import java.util.List;


/**
 * Created by ZYF on 2016/12/4.
 * 玩乐首页回调接口
 */
public interface WanLeListener {

    /**
     * 首页数据加载回调方法
     * @param wanLe
     */
    void dataSuccess(WanLe wanLe);

    /**
     * 上拉回调方法
     * @param storeList
     */
    void loadMoreStoresSuccess(List<Store> storeList);

    /**
     * 请求失败回调方法
     * @param message
     */
    void fail(String message, int type);

}
