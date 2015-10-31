package com.itheima.redbaby.domain;

import java.util.List;

public class Brand {
	private String key;
	private List<BrandValue> value;
	
	@Override
	public String toString() {
		return "Brand [key=" + key + ", value=" + value + "]";
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<BrandValue> getValue() {
		return value;
	}

	public void setValue(List<BrandValue> value) {
		this.value = value;
	}
	

	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}


	
}
