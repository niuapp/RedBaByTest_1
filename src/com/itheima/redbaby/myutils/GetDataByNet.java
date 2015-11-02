package com.itheima.redbaby.myutils;

import java.util.Map;

import com.itheima.redbaby.activity.TopicActivity;
import com.itheima.redbaby.adapter.TopicAdapter;
import com.itheima.redbaby.net.BaseParser;
import com.itheima.redbaby.net.RequestVo;
import com.itheima.redbaby.net.ThreadPoolManager;
import com.itheima.redbaby.utils.BaseParamsMapUtil;
import com.itheima.redbaby.utils.ConstantsRedBaby;
import com.itheima.redbaby.utils.LogUtils;
import com.itheima.redbaby.utils.NetUtil;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class GetDataByNet {
	
	//=========回调接口=================
	public interface OnSetDataListener{
		//设置数据
		public void setData(Object data);
	}
	private static OnSetDataListener dataListenerS;
	public OnSetDataListener getDataListener() {
		return dataListenerS;
	}
	public void setDataListener(OnSetDataListener dataListener) {
		dataListenerS = dataListener;
	}
	//==================================
	
	private static Context contextS;
	private static String urlS;
	private static Map<String, String> requestDataMapS;
	private static BaseParser<?> jsonParserS;
	
	//解析后的数据
	private static Object data;
	
	/**
	 * 在外界创建 类的对象，传入context，传入解析工具，传入回调接口，传入请求 url(位置) 请求参数   
	 * @param context context
	 * @param url 请求 url(位置)
	 * @param requestDataMap 请求参数
	 * @param jsonParser 解析工具
	 * @param dataListener 回调接口
	 */
	public static void getDataByNet(Context context,
			String url, Map<String, String> requestDataMap,
			BaseParser<?> jsonParser, OnSetDataListener dataListener) {
		//填补数据
		dataListenerS = dataListener;
		contextS = context;
		//补url
		urlS = ConstantsRedBaby.SERVER_URL + url;
		//补基本map数据
		if (requestDataMap == null) {
			requestDataMapS = BaseParamsMapUtil.getParamsMap(context);
		}else {
			requestDataMapS = requestDataMap;
			requestDataMapS.putAll(BaseParamsMapUtil.getParamsMap(context));
		}
		
		jsonParserS = jsonParser;
		
		//开启任务
		ThreadPoolManager manager = ThreadPoolManager.getInstance();
		manager.addTask(new MyRunnable());
		
	}

	//handler
	private static Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case ConstantsRedBaby.NET_FAILED:
				//网络连接失败了
				MyToast.showToase(contextS, "网络连接失败了");
				break;
			case ConstantsRedBaby.SUCCESS:
				//数据获取成功了
				//调用回调方法，设置数据
				if (dataListenerS != null) {
					dataListenerS.setData(data);
				}
				break;
			case ConstantsRedBaby.FAILED:
				//数据获取失败了
				MyToast.showToase(contextS, "数据获取失败了");
				break;

			}
		}
	};
	//获取解析数据的任务
	private static class MyRunnable implements Runnable{

		@Override
		public void run() {
			//msg对象
			Message msg = Message.obtain();
			//判断网络
			if (!NetUtil.hasConnectedNetwork(contextS)) {
				//没有网络
				msg.what = ConstantsRedBaby.NET_FAILED;
				handler.sendMessage(msg);
				return;
			}
			//请求对象
			RequestVo requestVo = new RequestVo(urlS, contextS, requestDataMapS, jsonParserS);
			//得到解析后的对象
			try {
				//得到数据
				data = NetUtil.get(requestVo);
				if (data == null) {
					//解析失败
					msg.what = ConstantsRedBaby.FAILED;
					handler.sendMessage(msg);
					return;
				}
				msg.what = ConstantsRedBaby.SUCCESS;
			} catch (Exception e) {
				//解析失败
				msg.what = ConstantsRedBaby.FAILED;
				e.printStackTrace();
			}
			//发送
			handler.sendMessage(msg);
		}
	}
	

}
