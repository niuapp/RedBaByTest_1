package com.itheima.redbaby.domain;

public class Help {
	private String detail_url;
	private String id;
	private String title;
	public String getDetail_url() {
		return detail_url;
	}
	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}
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
	@Override
	public String toString() {
		return "Help [detail_url=" + detail_url + ", id=" + id + ", title="
				+ title + "]";
	}
	
}
