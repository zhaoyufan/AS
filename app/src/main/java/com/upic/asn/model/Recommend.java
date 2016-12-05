package com.upic.asn.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nengneng on 2016/12/2.
 * 推荐
 */
public class Recommend extends BaseBean{

    private  String title;

    private String name;

     private  List<NobleChoice> nobleChoices;

    public Recommend() {

        nobleChoices = new ArrayList<>();
    }

    public Recommend(String title, String name, List<NobleChoice> nobleChoices) {
        this.title = title;
        this.name = name;
        this.nobleChoices = nobleChoices;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NobleChoice> getNobleChoices() {
        return nobleChoices;
    }

    public void setNobleChoices(List<NobleChoice> nobleChoices) {
        this.nobleChoices = nobleChoices;
    }
}
