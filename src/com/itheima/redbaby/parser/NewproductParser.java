package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.domain.Newproduct;
import com.itheima.redbaby.net.BaseParser;

public class NewproductParser extends BaseParser<List<Newproduct>> {

	@Override
	public List<Newproduct> parseJSON(String str) {
		
		try {
			return JSON.parseArray(new JSONObject(str).getString("productlist"), Newproduct.class);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
