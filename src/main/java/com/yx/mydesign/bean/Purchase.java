package com.yx.mydesign.bean;

import java.util.Date;

public class Purchase {
    public Purchase(Integer id, String username, Date starttime, Date stoptime,
			String price) {
		super();
		this.id = id;
		this.username = username;
		this.starttime = starttime;
		this.stoptime = stoptime;
		this.price = price;
	}
    public Purchase(){}
	private Integer id;

    private String username;

    private Date starttime;

    private Date stoptime;

    private String price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getStoptime() {
        return stoptime;
    }

    public void setStoptime(Date stoptime) {
        this.stoptime = stoptime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }
}