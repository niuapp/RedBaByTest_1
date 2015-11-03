package com.itheima.redbaby.domain;

public class Newproduct {
//	"id": 10007,
//    "marketprice": 1007,
//    "name": "雅培金装7",
//    "pic": "http://192.168.13.28:8080/RedBabyServer/images/image10.png",
//    "price": 800
	private String id;
	private String marketprice;
	private String name;
	private String pic;
	private String price;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMarketprice() {
		return marketprice;
	}
	public void setMarketprice(String marketprice) {
		this.marketprice = marketprice;
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
		return "Newproduct [id=" + id + ", marketprice=" + marketprice
				+ ", name=" + name + ", pic=" + pic + ", price=" + price + "]";
	}
	
}
