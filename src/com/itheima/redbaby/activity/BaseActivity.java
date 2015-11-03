package com.itheima.redbaby.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.itheima.redbaby.R;
import com.itheima.redbaby.domain.MyAddress;
import com.itheima.redbaby.domain.MyCategory;
/**
 * 基本Activity
 * @author Administrator
 *
 */
public class BaseActivity extends FragmentActivity {
	
	//brand的图片存储集合
	protected static List<ImageView> brandImageViewList;
	
	
	//地址管理条目集合
	protected static List<MyAddress> BA_addressList;
	
	//二 三级分类集合
	public static List<MyCategory> BA_categories_2;
	public static List<MyCategory> BA_categories_3;
	
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
	/**
	 * 开启返回数据的页面
	 */
	public <T> void startAndResult(Class<T> c) {
		startActivityForResult(new Intent(this, c), 98779);
		showChangeAnim();
	}
	/**
	 * 带动画的finish
	 */
	public void finishX(){
		finish();
		showChangeAnim();
	}
	
}
