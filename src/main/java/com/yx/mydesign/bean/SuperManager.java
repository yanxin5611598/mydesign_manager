package com.yx.mydesign.bean;

public class SuperManager {
    private Integer id;

    private String supermanagername;

    private String supermanagerpassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupermanagername() {
        return supermanagername;
    }

    public void setSupermanagername(String supermanagername) {
        this.supermanagername = supermanagername == null ? null : supermanagername.trim();
    }

    public String getSupermanagerpassword() {
        return supermanagerpassword;
    }

    public void setSupermanagerpassword(String supermanagerpassword) {
        this.supermanagerpassword = supermanagerpassword == null ? null : supermanagerpassword.trim();
    }
}