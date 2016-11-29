package com.zyf.rxjavaretrofit_demo.model;

/**
 * 根据返回json类型，多级嵌套
 */
public class News {
    private String title;
    private String picUrl;
    private String description;
    private String ctime;
    private String url;

    public News(String title, String picUrl, String description, String ctime, String url) {
        this.title = title;
        this.picUrl = picUrl;
        this.description = description;
        this.ctime = ctime;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
