package com.itheima.redbaby.activity;

import com.itheima.redbaby.R;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.MyToast;
import com.itheima.redbaby.parser.NullParser;
import com.itheima.redbaby.utils.BaseParamsMapUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 订单详情
 * 
 * @author Administrator
 */
public class OrderInfoActivity extends BaseActivity {
	
	//取消订单
	private Button cancel_bt;
	//物流
	private RelativeLayout logistics_rl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_order_info_activity);
		// 设置标题文本
		((TextView) findViewById(R.id.textTitle)).setText("订单详情");
		
		cancel_bt = (Button) findViewById(R.id.bt_cancel);
		logistics_rl = (RelativeLayout) findViewById(R.id.ordr_logistics_rel);
		
		//点击取消订单
		cancel_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String orderId = getIntent().getStringExtra("orderId");
				//发送请求，关闭页面
				GetDataByNet.getDataByNet(OrderInfoActivity.this, "ordercancel", BaseParamsMapUtil.getOrderId(orderId), new NullParser(), null);
				MyToast.showToase(OrderInfoActivity.this, "请求成功");
				finish();
			}
		});
		//点击去物流
		logistics_rl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OrderInfoActivity.this, OrderLogisticsActivity.class);
				intent.putExtra("orderId", getIntent().getStringExtra("orderId"));
				startActivity(intent);
				showChangeAnim();
			}
		});
	}
}
