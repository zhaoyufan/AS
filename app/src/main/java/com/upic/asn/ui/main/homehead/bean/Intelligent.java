package com.upic.asn.ui.main.homehead.bean;


/**
 * Created by ZF on 2016/12/16.
 */

public class Intelligent {
    private String content;
    private int type;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Intelligent() {
    }

    public Intelligent(String content, int type) {
        super();
        this.content = content;
        this.type = type;
    }
}
