package com.itheima.redbaby.domain;

public class MyAddress {
	
	
	@Override
	public String toString() {
		return "MyAddress [areadetail=" + areadetail + ", areaid=" + areaid
				+ ", cityid=" + cityid + ", fixedtel=" + fixedtel + ", id="
				+ id + ", name=" + name + ", phonenumber=" + phonenumber
				+ ", provinceid=" + provinceid + ", zipcode=" + zipcode + "]";
	}
	public String getAreadetail() {
		return areadetail;
	}
	public void setAreadetail(String areadetail) {
		this.areadetail = areadetail;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public String getFixedtel() {
		return fixedtel;
	}
	public void setFixedtel(String fixedtel) {
		this.fixedtel = fixedtel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	private String areadetail;
	private String areaid;
	private String cityid;
	private String fixedtel;
	private String id;
	private String name;
	private String phonenumber;
	private String provinceid;
	private String zipcode;
}
