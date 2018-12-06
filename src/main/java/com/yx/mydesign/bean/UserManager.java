package com.yx.mydesign.bean;

public class UserManager {
    private Integer id;

    private String usermanagername;

    private String usermanagerpassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsermanagername() {
        return usermanagername;
    }

    public void setUsermanagername(String usermanagername) {
        this.usermanagername = usermanagername == null ? null : usermanagername.trim();
    }

    public String getUsermanagerpassword() {
        return usermanagerpassword;
    }

    public void setUsermanagerpassword(String usermanagerpassword) {
        this.usermanagerpassword = usermanagerpassword == null ? null : usermanagerpassword.trim();
    }
}