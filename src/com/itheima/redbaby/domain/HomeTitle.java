package com.itheima.redbaby.domain;

/**
 * 首页 轮播图
 * @author Administrator
 */
public class HomeTitle {

	//
	private String id;
	private String title;
	private String pic;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return "HomeTitle [id=" + id + ", title=" + title + ", pic=" + pic
				+ "]";
	}
	
}
