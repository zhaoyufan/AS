package com.upic.asn.presenter;


import com.upic.asn.api.ApiService;

import rx.Subscription;

/**
 * Created by ZYF on 2016/12/4.
 */
public class BasePresenter {
    public ApiService service;
    public Subscription subscription;

    public BasePresenter() {
    }

}
