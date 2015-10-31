package com.itheima.redbaby.domain;

/**
 * 促销快报
 * @author ajun
 *
 */
public class Topic1 {
	private int id;
	private String name;
	private String pic;
	
	public Topic1() {
	}
	
	
	public Topic1(int id, String name, String pic) {
		super();
		this.id = id;
		this.name = name;
		this.pic = pic;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
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
		return "Topic [id=" + id + ", name=" + name + ", pic=" + pic + "]";
	}
	
	
}
