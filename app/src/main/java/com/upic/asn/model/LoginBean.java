package com.upic.asn.model;

/**
 * Created by ZYF on 2016/11/16.
 */
public class LoginBean {
    private String num;
    private String pass;

    public LoginBean() {
    }

    public LoginBean(String num, String pass) {
        this.num = num;
        this.pass = pass;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
