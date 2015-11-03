package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.domain.Help;
import com.itheima.redbaby.net.BaseParser;

public class NullParser extends BaseParser<String> {

	@Override
	public String parseJSON(String str) {
		return "空";

	}

}
