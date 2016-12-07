package com.upic.asn.model;

import java.util.List;

/**
 * Created by ZF on 2016/12/6.
 */

public class Community {
    private String title;
    private int num;
    private String imgUrl;
    private List<User> users;
    private List<String> marks;

    public List<String> getMarks() {
        return marks;
    }

    public void setMarks(List<String> marks) {
        this.marks = marks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Community(String title, int num, String imgUrl, List<User> users ,List<String> marks) {
        this.title = title;
        this.num = num;
        this.imgUrl = imgUrl;
        this.users = users;
    }
}
