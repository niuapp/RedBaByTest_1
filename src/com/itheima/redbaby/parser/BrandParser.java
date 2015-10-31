package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.domain.Brand;
import com.itheima.redbaby.domain.Topic;
import com.itheima.redbaby.net.BaseParser;

public class BrandParser extends BaseParser<List<Brand>>{

	@Override
	public List<Brand> parseJSON(String str) {
		
		List<Brand> brands;
		try {
			String sss = new JSONObject(str).getString("brand");
			brands = JSON.parseArray(sss, Brand.class);
			System.out.println("====================="+brands);
			return brands;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
