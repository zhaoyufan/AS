package com.upic.asn.ui.main.homehead;

import java.util.List;

/**
 * Created by ZYF on 2016/12/14.
 */
public class City {
    private String name;
    private List<Object> lists;

    public City() {
    }

    public City(String name, List<Object> lists) {
        this.name = name;
        this.lists = lists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }
}
