package com.upic.asn.api;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by baiyuliang on 2016-7-14.
 */
public class ApiUtil {

    public static ApiService createApiService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.API_ROOT)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }

}
