package com.upic.asn.model;

import java.util.Date;

/**
 * Created by nengneng on 2016/12/4.
 * 那块区域
 */
public class ActivityArea {
    private Long activityAreaId;
    private Integer version;
    private Date createTime;
    private String url;
    private String title;

    public ActivityArea() {
    }

    public ActivityArea(Long activityAreaId, Integer version, Date createTime, String url, String title) {
        this.activityAreaId = activityAreaId;
        this.version = version;
        this.createTime = createTime;
        this.url = url;
        this.title = title;
    }

    public Long getActivityAreaId() {
        return activityAreaId;
    }

    public void setActivityAreaId(Long activityAreaId) {
        this.activityAreaId = activityAreaId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
