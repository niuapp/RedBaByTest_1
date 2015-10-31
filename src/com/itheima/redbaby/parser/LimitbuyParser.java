package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONObject;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.domain.Limitbuy;
import com.itheima.redbaby.domain.Topic;
import com.itheima.redbaby.net.BaseParser;

public class LimitbuyParser extends BaseParser<List<Limitbuy>>{
//"productlist":[{"id":102,"lefttime":1335189632954,"limitprice":800,"name":"雅培金装0","pic":"http://1
	@Override
	public List<Limitbuy> parseJSON(String str) {
		try {
			List<Limitbuy> limitbuys = JSON.parseArray(new JSONObject(str).getString("productlist"), Limitbuy.class);
			return limitbuys;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
