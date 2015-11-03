package com.itheima.redbaby.activity;

import com.itheima.redbaby.R;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.MyToast;
import com.itheima.redbaby.parser.NullParser;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class More_SuggestActivity extends BaseActivity {
	
	private EditText suggest_et;
	private EditText phone_et;
	private Button submit_bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suggest_activity);
		// 设置标题文本
		((TextView) findViewById(R.id.more_title_tv)).setText("用户反馈");
		
		suggest_et = (EditText) findViewById(R.id.suggest_suggest);
		phone_et = (EditText) findViewById(R.id.suggest_phone);
		submit_bt = (Button) findViewById(R.id.bt_submit);
		
		//判空，点击提交(发送到服务器(null))
		submit_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String suggest = suggest_et.getText().toString().trim();
				String phoneN = phone_et.getText().toString().trim();
				if (TextUtils.isEmpty(suggest) || TextUtils.isEmpty(phoneN)) {
					MyToast.showToase(More_SuggestActivity.this, "请完整填写数据");
					return;
				}
				MyToast.showToase(More_SuggestActivity.this, "提交成功");
				//请求服务器
				GetDataByNet.getDataByNet(More_SuggestActivity.this, "", null, new NullParser(), null);
				//关闭当前页面
				finish();
				showChangeAnim();
			}
		});
	}
}
