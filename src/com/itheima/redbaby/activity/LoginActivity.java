package com.itheima.redbaby.activity;

import com.itheima.redbaby.R;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.GetDataByNet.OnSetDataListener;
import com.itheima.redbaby.myutils.MyToast;
import com.itheima.redbaby.parser.LoginParser;
import com.itheima.redbaby.parser.NullParser;
import com.itheima.redbaby.utils.BaseParamsMapUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 登陆注册页面
 * 
 * @author Administrator
 */
public class LoginActivity extends BaseActivity {

	private EditText username_et;
	private EditText password_et;
	private TextView login_bt;
	private TextView regist_bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		// 设置标题文本
		((TextView) findViewById(R.id.textTitle)).setText("登陆");

		username_et = (EditText) findViewById(R.id.login_username);
		password_et = (EditText) findViewById(R.id.login_password);
		login_bt = (TextView) findViewById(R.id.bt_login);
		regist_bt = (TextView) findViewById(R.id.bt_regist);
		// 点击注册按钮去注册页面
		regist_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//开启返回数据的Activity
				startAndResult(RegistActivity.class);
			}
		});

		// 登陆，获取输入的用户名和密码(判空)，发送数据，获取数据，判断是否登陆成功，
		//成功就去用户信息页，在sp中保留登陆信息(在主页Activity关闭后把登陆信息重置(或用退出登陆选项))
		login_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String username = username_et.getText().toString().trim();
				String password = password_et.getText().toString().trim();
				if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
					MyToast.showToase(LoginActivity.this, "错误，确认信息");
					return;
				}
				
				//请求数据
				GetDataByNet.getDataByNet(LoginActivity.this, "login", BaseParamsMapUtil.getRegistInfo(username, password), new LoginParser(), new OnSetDataListener() {
					@Override
					public void setData(Object data) {
						//判断data是否是"login"
						if (TextUtils.equals("login", (String) data)) {
							//登陆成功
							//保存登陆信息
							sp.edit().putBoolean("isLogin", true).commit();
							//跳到账户信息页(关闭)
							startAndFinish(More_UserActivity.class, true);
						}else {
							MyToast.showToase(LoginActivity.this, "登陆失败");
						}
					}
				});
			}
		});
		
	}
	
	//设置注册成功的返回数据
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//接收注册页面返回的数据(如果有的话)
		if (data != null) {
			username_et.setText(data.getStringExtra("username"));
			password_et.setText(data.getStringExtra("password"));
		}
	}
}
