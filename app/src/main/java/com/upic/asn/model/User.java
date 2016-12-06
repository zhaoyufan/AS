package com.upic.asn.model;

/**
 * Created by ZF on 2016/12/6.
 */


public class User {
    private String name;
    private String imgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public User(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }
}