package com.upic.asn.model.view;

/**
 * Created by ZYF on 2016/11/17.
 */
public interface LoginListener {
    /**
     * 登录成功
     * @param message
     */
    void loginSuccess(String message);

    /**
     * 登录失败
     * @param message
     */
    void loginFail(String message);
}
