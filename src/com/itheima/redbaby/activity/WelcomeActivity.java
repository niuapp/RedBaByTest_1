package com.itheima.redbaby.activity;

import com.itheima.redbaby.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

/**
 * welcome 判断是否第一次登陆，如果是就去引导页预览，如果不是就直接进入主页
 * @author Administrator
 */
public class WelcomeActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_activity);
		
		//延时1.5秒
		new Thread(){
			public void run() {
				SystemClock.sleep(500L);
				go();
			}
		}.start();
	}

	private void go() {
		//获取是否是第一次登陆，判断
		if (sp.getBoolean("isFirst", false)) {
			//不是,去首页
			startActivity(new Intent(this, HomeActivity.class));
		}else {
			//是第一次登陆，去引导页
			startActivity(new Intent(this, PreviewActivity.class));
		}
		//关闭当前页
		finish();
		//过场动画
		showChangeAnim();
	}


}
