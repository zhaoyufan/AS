package com.upic.asn.model.view;


import com.upic.asn.model.Banner;
import com.upic.asn.model.WanLe;

import java.util.List;

/**
 * Created by ZYF on 2016/12/4.
 */
public interface WanLeListener {
    /**
     * 上拉加载成功
     *
     * @param message
     */
    void loadMoreSuccess(String message);

    /**
     * 加载banner图监听
     * @param listBanners
     */
    void loadBanner(List<Banner> listBanners);

    void dataSuccess(WanLe wanLe);

    /**
     * 请求失败
     * @param message
     */
    void fail(String message, int type);

}
