package com.upic.asn.model;

import java.util.List;

/**
 * Created by ZYF on 2016/12/5.
 */
public class WanLe {
    private List<ActivityArea> listActivityAreas;
    private List<Recommend> listRecommends;
    private List<Store> listStores;

    public WanLe() {
    }
    public WanLe(List<ActivityArea> listActivityAreas, List<Recommend> listRecommends, List<Store> listStores) {
        this.listActivityAreas = listActivityAreas;
        this.listRecommends = listRecommends;
        this.listStores = listStores;
    }

    public List<ActivityArea> getListActivityAreas() {
        return listActivityAreas;
    }

    public void setListActivityAreas(List<ActivityArea> listActivityAreas) {
        this.listActivityAreas = listActivityAreas;
    }

    public List<Recommend> getListRecommends() {
        return listRecommends;
    }

    public void setListRecommends(List<Recommend> listRecommends) {
        this.listRecommends = listRecommends;
    }

    public List<Store> getListStores() {
        return listStores;
    }

    public void setListStores(List<Store> listStores) {
        this.listStores = listStores;
    }
}
