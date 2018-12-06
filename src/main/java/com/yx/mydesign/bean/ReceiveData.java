package com.yx.mydesign.bean;

import java.util.Date;

public class ReceiveData {
    private Integer receiveid;

    private String deviceid;

    private String tempvalue;

    private String humvalue;

    private String pm10value;

    private String pm25value;

    private String hchovalue;

    private Date receivetime;

    public Integer getReceiveid() {
        return receiveid;
    }

    public void setReceiveid(Integer receiveid) {
        this.receiveid = receiveid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid == null ? null : deviceid.trim();
    }

    public String getTempvalue() {
        return tempvalue;
    }

    public void setTempvalue(String tempvalue) {
        this.tempvalue = tempvalue == null ? null : tempvalue.trim();
    }

    public String getHumvalue() {
        return humvalue;
    }

    public void setHumvalue(String humvalue) {
        this.humvalue = humvalue == null ? null : humvalue.trim();
    }

    public String getPm10value() {
        return pm10value;
    }

    public void setPm10value(String pm10value) {
        this.pm10value = pm10value == null ? null : pm10value.trim();
    }

    public String getPm25value() {
        return pm25value;
    }

    public void setPm25value(String pm25value) {
        this.pm25value = pm25value == null ? null : pm25value.trim();
    }

    public String getHchovalue() {
        return hchovalue;
    }

    public void setHchovalue(String hchovalue) {
        this.hchovalue = hchovalue == null ? null : hchovalue.trim();
    }

    public Date getReceivetime() {
        return receivetime;
    }

    public void setReceivetime(Date receivetime) {
        this.receivetime = receivetime;
    }

	@Override
	public String toString() {
		return "tempvalue=" + tempvalue + ", humvalue=" + humvalue
				+ ", pm10value=" + pm10value + ", pm25value=" + pm25value
				+ ", hchovalue=" + hchovalue;
	}
    
}