package com.yx.mydesign.bean;

public class UserCheckResult {
    private Integer id;

    private String tem;

    private String hum;

    private String choh;

    private String pm25;

    private String pm10;

    private String airrank;

    private String username;

    private String gpsinfo;

    private String deviceinfo;

    private String time;

    private String roominfo;

    private String contentevaluate;

    private String imageinfo;

    private String imagepath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem == null ? null : tem.trim();
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum == null ? null : hum.trim();
    }

    public String getChoh() {
        return choh;
    }

    public void setChoh(String choh) {
        this.choh = choh == null ? null : choh.trim();
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25 == null ? null : pm25.trim();
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10 == null ? null : pm10.trim();
    }

    public String getAirrank() {
        return airrank;
    }

    public void setAirrank(String airrank) {
        this.airrank = airrank == null ? null : airrank.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getGpsinfo() {
        return gpsinfo;
    }

    public void setGpsinfo(String gpsinfo) {
        this.gpsinfo = gpsinfo == null ? null : gpsinfo.trim();
    }

    public String getDeviceinfo() {
        return deviceinfo;
    }

    public void setDeviceinfo(String deviceinfo) {
        this.deviceinfo = deviceinfo == null ? null : deviceinfo.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getRoominfo() {
        return roominfo;
    }

    public void setRoominfo(String roominfo) {
        this.roominfo = roominfo == null ? null : roominfo.trim();
    }

    public String getContentevaluate() {
        return contentevaluate;
    }

    public void setContentevaluate(String contentevaluate) {
        this.contentevaluate = contentevaluate == null ? null : contentevaluate.trim();
    }

    public String getImageinfo() {
        return imageinfo;
    }

    public void setImageinfo(String imageinfo) {
        this.imageinfo = imageinfo == null ? null : imageinfo.trim();
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath == null ? null : imagepath.trim();
    }
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return getId()+""+getTem()+","+getHum()+getAirrank();
    }
}