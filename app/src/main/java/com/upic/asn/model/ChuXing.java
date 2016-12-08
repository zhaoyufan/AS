package com.upic.asn.model;

import java.util.List;

/**
 * Created by ZF on 2016/12/8.
 */

public class ChuXing {
    private List<Community> communityList;
    private List<Object> marksList;

    public List<Community> getCommunityList() {
        return communityList;
    }

    public void setCommunityList(List<Community> communityList) {
        this.communityList = communityList;
    }

    public List<Object> getMarksList() {
        return marksList;
    }

    public void setMarksList(List<Object> marksList) {
        this.marksList = marksList;
    }

}
