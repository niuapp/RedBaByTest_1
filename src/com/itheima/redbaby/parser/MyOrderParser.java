package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.domain.Brand;
import com.itheima.redbaby.domain.MyOrder;
import com.itheima.redbaby.domain.Topic;
import com.itheima.redbaby.net.BaseParser;

public class MyOrderParser extends BaseParser<List<MyOrder>>{

	@Override
	public List<MyOrder> parseJSON(String str) {
		
		try {
			return JSON.parseArray(new JSONObject(str).getString("orderlist"), MyOrder.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
