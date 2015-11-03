package com.itheima.redbaby.activity;

import com.itheima.redbaby.R;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.MyToast;
import com.itheima.redbaby.parser.NullParser;
import com.itheima.redbaby.utils.BaseParamsMapUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 注册页面
 * 
 * 点击注册按钮 给服务器发送数据(用户名和密码)，
 * 接收返回的数据，判断是否注册成功，
 * 如果注册成功，关闭注册页面，Intent返回注册信息
 * @author Administrator
 */
public class RegistActivity extends BaseActivity {
	
	private EditText username_et;
	private EditText password_et;
	private EditText repassword_et;
	private TextView regist_bt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist_activity);
		// 设置标题文本
		((TextView) findViewById(R.id.textTitle)).setText("注册");
		
		username_et = (EditText) findViewById(R.id.regist_username);
		password_et = (EditText) findViewById(R.id.regist_password);
		repassword_et = (EditText) findViewById(R.id.regist_repassword);
		regist_bt = (TextView) findViewById(R.id.bt_regist);
		
		//点击注册按钮 判断数据，不空就提交，接收服务器返回结果(有数据就是成功(都成功，所以本地判断(非空就好)))
		regist_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String username = username_et.getText().toString().trim();
				String password = password_et.getText().toString().trim();
				if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repassword_et.getText().toString().trim()) || !TextUtils.equals(password, repassword_et.getText().toString().trim())) {
					MyToast.showToase(RegistActivity.this, "错误，请确认信息");
					return;
				}
				//信息正确，提交服务器
				GetDataByNet.getDataByNet(RegistActivity.this, "register", BaseParamsMapUtil.getRegistInfo(username, password), new NullParser(), null);
				//关闭页面，返回数据
				Intent intent = getIntent();
				intent.putExtra("username", username);
				intent.putExtra("password", password);
				setResult(999983, intent);
				finish();
			}
		});
	}
}
