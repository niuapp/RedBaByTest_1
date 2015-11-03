package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.domain.Help;
import com.itheima.redbaby.net.BaseParser;

public class LoginParser extends BaseParser<String> {

	@Override
	public String parseJSON(String str) {
		try {
			//解析返回登陆信息
			return new JSONObject(str).getString("response");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "";
	}

}
