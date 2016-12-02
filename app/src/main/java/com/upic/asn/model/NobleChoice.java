package com.upic.asn.model;

/**
 * Created by nengneng on 2016/12/2.
 * 优选
 */
public class NobleChoice {

    private String url;

    private String titlt;

    private double price;

    public NobleChoice() {
    }

    public NobleChoice(String url, String titlt, double price) {
        this.url = url;
        this.titlt = titlt;
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitlt() {
        return titlt;
    }

    public void setTitlt(String titlt) {
        this.titlt = titlt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
