package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.domain.HomeTitle;
import com.itheima.redbaby.net.BaseParser;

public class HomeTitleParser extends BaseParser<List<HomeTitle>> {

	@Override
	public List<HomeTitle> parseJSON(String str) {
		try {
			return JSON.parseArray(new JSONObject(str).getString("home_banner"), HomeTitle.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
