package com.itheima.redbaby.parser;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.itheima.redbaby.domain.Topic;
import com.itheima.redbaby.net.BaseParser;

public class TopicParser extends BaseParser<List<Topic>>{
//	{
//		  "response": "topic",
//		  " topic ": [
//		{
//		  "id": "专题活动ID",
//		      "name": "专题活动名称",
//		      "pic": "图片URL"
//		    },
//		{
//		  "id": "专题活动ID",
//		      "name": "专题活动名称",
//		      "pic": "图片URL"
//		    }
//		  ]
//		}


	@Override
	public List<Topic> parseJSON(String str) {
		try {
			JSONObject jObject = new JSONObject(str);
			String str_  = jObject.getString("topic");
			Log.e("TopicParser", str_);
			List<Topic> topics = JSON.parseArray(str_, Topic.class);
			return topics;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
