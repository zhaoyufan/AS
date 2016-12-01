package com.upic.asn.model.view;

/**
 * Created by ZYF on 2016/11/17.
 */
public interface NewsListener {
    /**
     * 下拉刷新成功
     *
     * @param message
     */
    void refreshSuccess(String message);

    /**
     * 上拉加载成功
     *
     * @param message
     */
    void loadMoreSuccess(String message);

    /**
     * 失败
     *
     * @param message
     */
    void fail(String message);
}
