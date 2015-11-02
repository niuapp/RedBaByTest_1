package com.itheima.redbaby.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.R;
import com.itheima.redbaby.adapter.TopicAdapter;
import com.itheima.redbaby.domain.Topic;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.GetDataByNet.OnSetDataListener;
import com.itheima.redbaby.net.RequestVo;
import com.itheima.redbaby.net.ThreadPoolManager;
import com.itheima.redbaby.parser.TopicParser;
import com.itheima.redbaby.utils.BaseParamsMapUtil;
import com.itheima.redbaby.utils.ConstantsRedBaby;
import com.itheima.redbaby.utils.LogUtils;
import com.itheima.redbaby.utils.NetUtil;

/**
 * 促销快报
 * @author Administrator
 */
public class TopicActivity extends BaseActivity {
	ListView mylimitbuy_product_list;
	TopicAdapter topicAdapter;
	List<Topic> topics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_limit_activity);
		//设置标题文本
		((TextView)findViewById(R.id.textTitle)).setText("促销快报");
		mylimitbuy_product_list = (ListView) findViewById(R.id.mylimitbuy_product_list);
		// 权限 开启一个子线程 访问网络
		// 解析Json数据成JavaBean的集合或者对象
		// 通过Handler通知主界面更新UI
//		ThreadPoolManager tpm = ThreadPoolManager.getInstance();
//		tpm.addTask(new MyRunnable());
		
		//工具对象
		GetDataByNet.getDataByNet(this, "topic", BaseParamsMapUtil.getTopic("1", "8"), new TopicParser(), new OnSetDataListener() {
			
			@Override
			public void setData(Object data) {
				topics = (List<Topic>) data;
				//设置数据
				if (topicAdapter == null) {
					topicAdapter = new TopicAdapter(topics, TopicActivity.this);
					mylimitbuy_product_list.setAdapter(topicAdapter);
				} else {
					topicAdapter.notifyDataSetChanged();
				}
			}
		});

	}

//	class MyRunnable implements Runnable {
//
//		@Override
//		public void run() {
//			// 访问网络 解析Json数据
//			try {
//				if (!NetUtil.hasConnectedNetwork(getApplicationContext())) {
//					// 通知用户没有网络
//					Message msg = Message.obtain();
//					msg.what = ConstantsRedBaby.NET_FAILED;
//					mHandler.sendMessage(msg);
//					return;
//				}
//				RequestVo vo = new RequestVo(ConstantsRedBaby.SERVER_URL
//						+ "topic", getApplicationContext(),
//				// 请求所携带的所有参数（包括公共头部分的参数）
//						BaseParamsMapUtil.getTopic(
//								1 + "", 8 + ""),
//						// 解析Json数据所需要的解析工具类
//						new TopicParser());
//				topics = (List<Topic>) NetUtil.get(vo);
//				Message msg = Message.obtain();
//				msg.what = ConstantsRedBaby.SUCCESS;
//				mHandler.sendMessage(msg);
//			} catch (Exception e) {
//				Message msg = Message.obtain();
//				msg.what = ConstantsRedBaby.FAILED;
//				mHandler.sendMessage(msg);
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//
//	}
//
//	Handler mHandler = new Handler() {
//		public void handleMessage(android.os.Message msg) {
//			switch (msg.what) {
//			case ConstantsRedBaby.NET_FAILED:
//				LogUtils.toast(getApplicationContext(), "亲，网络连接失败了");
//				break;
//			case ConstantsRedBaby.SUCCESS:
//				LogUtils.toast(getApplicationContext(), "亲，数据获取成功了");
//				if (topicAdapter == null) {
//					topicAdapter = new TopicAdapter(topics, TopicActivity.this);
//					mylimitbuy_product_list.setAdapter(topicAdapter);
//				} else {
//					topicAdapter.notifyDataSetChanged();
//				}
//				break;
//			case ConstantsRedBaby.FAILED:
//				LogUtils.toast(getApplicationContext(), "亲，数据获取失败了");
//				break;
//				
//			}
//		};
//	};
}
