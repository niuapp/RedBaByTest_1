package com.itheima.redbaby.activity;

import com.itheima.redbaby.R;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.parser.NullParser;
import com.itheima.redbaby.utils.BaseParamsMapUtil;

import android.os.Bundle;
import android.widget.TextView;

public class OrderLogisticsActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wuliu___);
		// 设置标题文本
		((TextView) findViewById(R.id.textTitle)).setText("物流查询");
		
		//请求数据
		GetDataByNet.getDataByNet(this, "logistics", BaseParamsMapUtil.getOrderId(getIntent().getStringExtra("orderId")), new NullParser(), null);
	}
}
