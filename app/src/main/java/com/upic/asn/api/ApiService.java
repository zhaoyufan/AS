package com.upic.asn.api;

import com.upic.asn.model.ChuXing;
import com.upic.asn.model.Community;
import com.upic.asn.model.LoginBean;
import com.upic.asn.model.Store;
import com.upic.asn.model.WanLe;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;


public interface ApiService {

    String API_ROOT = "http://maneng.uicp.io/";

    /**
     * 登录
     * 模拟登录，实际为手机号归属地查询接口
     *
     * @return
     */
    @POST("/login/")
    Observable<LoginBean> login(@QueryMap Map<String,String> maps);


    @POST("load")
    Observable<WanLe> getWanLeDatas();

    @GET("listStore")
    Observable<List<Store>> loadMoreStores();


    @POST("loadChuXing")
    Observable<ChuXing> getChuXingDatas();

    @GET("listComunity")
    Observable<List<Community>> loadMoreCommunity();
}
