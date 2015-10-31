package com.itheima.redbaby.domain;

import android.R.string;

public class Help_detail {

	private String content;
	private String title;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Help_detail [content=" + content + ", title=" + title + "]";
	}
	
}
