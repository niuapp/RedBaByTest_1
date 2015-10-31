package com.itheima.redbaby.activity;

import com.itheima.redbaby.R;

import android.os.Bundle;
import android.widget.TextView;

public class More_SuggestActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suggest_activity);
		// 设置标题文本
		((TextView) findViewById(R.id.more_title_tv)).setText("用户反馈");
	}
}
