package com.upic.asn.model.view;


import com.upic.asn.model.ChuXing;
import com.upic.asn.model.Community;

import java.util.List;

/**
 * Created by ZYF on 2016/12/4.
 * 出行圈回调接口
 */
public interface ChuXingListener {

    /**
     * 首页数据加载成功回调方法
     * @param chuXing
     */
    void dataSuccess(ChuXing chuXing);

    /**
     * 加载更多成功回调方法
     * @param communityList
     */
    void loadMoreCommunitySuccess(List<Community> communityList);

    /**
     * 请求失败回调方法
     * @param message
     */
    void fail(String message, int type);

}
