package com.itheima.redbaby.domain;

public class Limitbuy {
//
//	"id": 102,
//    "lefttime": 1335190432954,
//    "limitprice": 800,
//    "name": "雅培金装8",
//    "pic": "http://192.168.1.107:8080/RedBabyServer/images/image10.png",
//    "price": 1000
	private String id;
	private String lefttime;
	private String limitprice;
	private String name;
	private String pic;
	private String price;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLefttime() {
		return lefttime;
	}
	public void setLefttime(String lefttime) {
		this.lefttime = lefttime;
	}
	public String getLimitprice() {
		return limitprice;
	}
	public void setLimitprice(String limitprice) {
		this.limitprice = limitprice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Limitbuy [id=" + id + ", lefttime=" + lefttime
				+ ", limitprice=" + limitprice + ", name=" + name + ", pic="
				+ pic + ", price=" + price + "]";
	}
	public Limitbuy() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
