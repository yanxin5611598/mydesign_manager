package com.yx.mydesign.bean;

public class User {
    private Integer userid;

    private String username;

    private String password;

    private String gender;

    private String age;

    private String phone;

    private String email;

    private Integer isvip;

    private Integer rewardpoint;

    private Integer requestviewnum;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getIsvip() {
        return isvip;
    }

    public void setIsvip(Integer isvip) {
        this.isvip = isvip;
    }

    public Integer getRewardpoint() {
        return rewardpoint;
    }

    public void setRewardpoint(Integer rewardpoint) {
        this.rewardpoint = rewardpoint;
    }

    public Integer getRequestviewnum() {
        return requestviewnum;
    }

    public void setRequestviewnum(Integer requestviewnum) {
        this.requestviewnum = requestviewnum;
    }
}