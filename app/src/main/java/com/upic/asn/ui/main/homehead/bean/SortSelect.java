package com.upic.asn.ui.main.homehead.bean;

/**
 * Created by ZF on 2016/12/15.
 */

public class SortSelect {
    private int type;
    private String content;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SortSelect() {
    }

    public SortSelect(int type, String content) {
        this.type = type;
        this.content = content;
    }
}
