package com.itheima.redbaby.activity;

import com.itheima.redbaby.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HelpContentActivity extends BaseActivity {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helo_content_activity);
		Intent intent = getIntent();
		// 设置标题文本
		((TextView) findViewById(R.id.textTitle)).setText(intent.getStringExtra("title"));
		((TextView) findViewById(R.id.title_x)).setText("1、问："+intent.getStringExtra("title"));
		((TextView) findViewById(R.id.content_tv)).setText(intent.getStringExtra("content"));
		
	}
}
