package com.upic.asn.ui.main.homehead.bean;

/**
 * Created by ZYF on 2016/12/16.
 */
public class WanLeList {
    private String url;
    private String title;
    private String city;
    private String distance;
    private String label;

    public WanLeList() {
    }

    public WanLeList(String url, String title, String city, String distance, String label) {
        this.url = url;
        this.title = title;
        this.city = city;
        this.distance = distance;
        this.label = label;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
