package com.itheima.redbaby.utils;

import java.util.Map;

import android.content.Context;

public class ParamsMapUtils extends BaseParamsMapUtil {
	//获取推荐的商品
	public static Map<String,String> getTopic(Context context,String page,String pageNum){
		Map<String, String> paramsMap = getParamsMap(context);
		paramsMap.put("page", page);
		paramsMap.put("pageNum", pageNum);
		return paramsMap;
		
	}
	
	public static Map<String, String> getBrand(Context context) {
		Map<String, String> paramsMap = getParamsMap(context);
		return paramsMap;
	}
	

}
