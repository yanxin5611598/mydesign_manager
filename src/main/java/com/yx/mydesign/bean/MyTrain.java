package com.yx.mydesign.bean;

public class MyTrain {
    private Integer id;

    private Double tem;

    private Double hum;

    private Double choh;

    private Double pm25;

    private Double pm10;

    private Double you;

    private Double liang;

    private Double zhong;

    private Double cha;

    private Double yanzhong;

    private String rankMax;

    private String rankCenter;

    private String rankCenterNew;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTem() {
        return tem;
    }

    public void setTem(Double tem) {
        this.tem = tem;
    }

    public Double getHum() {
        return hum;
    }

    public void setHum(Double hum) {
        this.hum = hum;
    }

    public Double getChoh() {
        return choh;
    }

    public void setChoh(Double choh) {
        this.choh = choh;
    }

    public Double getPm25() {
        return pm25;
    }

    public void setPm25(Double pm25) {
        this.pm25 = pm25;
    }

    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }

    public Double getYou() {
        return you;
    }

    public void setYou(Double you) {
        this.you = you;
    }

    public Double getLiang() {
        return liang;
    }

    public void setLiang(Double liang) {
        this.liang = liang;
    }

    public Double getZhong() {
        return zhong;
    }

    public void setZhong(Double zhong) {
        this.zhong = zhong;
    }

    public Double getCha() {
        return cha;
    }

    public void setCha(Double cha) {
        this.cha = cha;
    }

    public Double getYanzhong() {
        return yanzhong;
    }

    public void setYanzhong(Double yanzhong) {
        this.yanzhong = yanzhong;
    }

    public String getRankMax() {
        return rankMax;
    }

    public void setRankMax(String rankMax) {
        this.rankMax = rankMax == null ? null : rankMax.trim();
    }

    public String getRankCenter() {
        return rankCenter;
    }

    public void setRankCenter(String rankCenter) {
        this.rankCenter = rankCenter == null ? null : rankCenter.trim();
    }

    public String getRankCenterNew() {
        return rankCenterNew;
    }

    public void setRankCenterNew(String rankCenterNew) {
        this.rankCenterNew = rankCenterNew == null ? null : rankCenterNew.trim();
    }
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return this.getId()+","+getTem()+","+getHum()+","+getChoh()+","+getPm25()+","+getPm10()+","+getYou()+","+getLiang()+","+getZhong()+","+getCha()+","+getYanzhong()+","+getRankMax()+","+getRankCenter()+","+getRankCenterNew();
    }
}