package com.itheima.redbaby.domain;

public class MyOrder {
//	"flag": 3,
//    "orderid": "412423146",
//    "price": 200.23,
//    "status": "已处理",
//    "time": "2011/10/100 12:18:40"
	private String flag;
	private String orderid;
	private String price;
	private String status;
	private String time;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "MyOrder [flag=" + flag + ", orderid=" + orderid + ", price="
				+ price + ", status=" + status + ", time=" + time + "]";
	}
	
}
