package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.domain.Help;
import com.itheima.redbaby.domain.MyAddress;
import com.itheima.redbaby.net.BaseParser;

public class MyAddressParser extends BaseParser<List<MyAddress>> {

	@Override
	public List<MyAddress> parseJSON(String str) {
		try {
			return JSON.parseArray(new JSONObject(str).getString("addresslist"), MyAddress.class);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
