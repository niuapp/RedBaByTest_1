package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.itheima.redbaby.domain.Brand;
import com.itheima.redbaby.domain.MyCategory;
import com.itheima.redbaby.domain.Topic;
import com.itheima.redbaby.net.BaseParser;

public class CategoryParser extends BaseParser<List<MyCategory>>{

	@Override
	public List<MyCategory> parseJSON(String str) {
		
		try {
			return JSON.parseArray(new JSONObject(str).getString("category"), MyCategory.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
