package com.yx.mydesign.bean;

import java.util.Date;

public class Device {
    private Integer id;

    private String deviceid;

    private String placefrom;

    private Date timefrom;

    private String state;

    private String placecurrent;

    private String longitude;

    private String latitude;

    private Date timecurrent;

    private String username;

    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public String getPlacefrom() {
        return placefrom;
    }

    public void setPlacefrom(String placefrom) {
        this.placefrom = placefrom == null ? null : placefrom.trim();
    }

    public Date getTimefrom() {
        return timefrom;
    }

    public void setTimefrom(Date timefrom) {
        this.timefrom = timefrom;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPlacecurrent() {
        return placecurrent;
    }

    public void setPlacecurrent(String placecurrent) {
        this.placecurrent = placecurrent == null ? null : placecurrent.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public Date getTimecurrent() {
        return timecurrent;
    }

    public void setTimecurrent(Date timecurrent) {
        this.timecurrent = timecurrent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}