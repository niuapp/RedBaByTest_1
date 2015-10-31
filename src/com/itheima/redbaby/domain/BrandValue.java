package com.itheima.redbaby.domain;

public class BrandValue{
	
	public BrandValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String id;
	private String name;
	private String pic;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return "BrandValue [id=" + id + ", name=" + name + ", pic=" + pic
				+ "]";
	}
	
	
}
