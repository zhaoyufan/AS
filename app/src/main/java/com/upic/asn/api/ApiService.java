package com.upic.asn.api;

import com.upic.asn.model.LoginBean;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;


public interface ApiService {

    String API_ROOT = "http://maneng.uicp.io";

    /**
     * 登录
     * 模拟登录，实际为手机号归属地查询接口
     *
     * @return
     */
    @POST("/login/")
    Observable<LoginBean> login(@QueryMap Map<String,String> maps);


}
