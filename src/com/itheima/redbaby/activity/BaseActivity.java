package com.itheima.redbaby.activity;

import com.itheima.redbaby.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
/**
 * 基本Activity
 * @author Administrator
 *
 */
public class BaseActivity extends Activity {
	
	//sp
	protected SharedPreferences sp;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//初始化 sp
		sp = getSharedPreferences("config", MODE_PRIVATE);
		
	}
	
	//返回按钮
	public void clickBack(View view){
		//如果有返回按钮，点击关闭
		finish();
	}
	
	
	//过场动画
	public void showChangeAnim(){
		overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
	}
	/**
	 * 开启页面
	 */
	public <T> void startAndFinish(Class<T> c, boolean isFinish) {
		startActivity(new Intent(this, c));
		if (isFinish) {
			finish();
		}
		showChangeAnim();
	}
	
}
