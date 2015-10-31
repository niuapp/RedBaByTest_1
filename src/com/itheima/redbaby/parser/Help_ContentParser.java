package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONObject;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.domain.Help_detail;
import com.itheima.redbaby.domain.Limitbuy;
import com.itheima.redbaby.domain.Topic;
import com.itheima.redbaby.net.BaseParser;

public class Help_ContentParser extends BaseParser<List<Help_detail>>{
	@Override
	public List<Help_detail> parseJSON(String str) {
		try {
			List<Help_detail> help_details = JSON.parseArray(new JSONObject(str).getString("help_detail "), Help_detail.class);
			return help_details;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
