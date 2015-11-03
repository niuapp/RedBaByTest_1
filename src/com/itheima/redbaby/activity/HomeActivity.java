package com.itheima.redbaby.activity;

import java.util.ArrayList;
import java.util.List;

import com.itheima.redbaby.R;
import com.itheima.redbaby.domain.HomeTitle;
import com.itheima.redbaby.fragment.BaseFragment;
import com.itheima.redbaby.fragment.ClassFragment;
import com.itheima.redbaby.fragment.HomeFragment;
import com.itheima.redbaby.fragment.SearchFragment;
import com.itheima.redbaby.fragment.ShopFragment;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.Image_Utils;
import com.itheima.redbaby.myutils.MyToast;
import com.itheima.redbaby.myutils.OtherUtils;
import com.itheima.redbaby.parser.HomeTitleParser;
import com.itheima.redbaby.ui.DefineViewPager;
import com.itheima.redbaby.utils.ImageUtils2;
import com.itheima.redbaby.utils.ImageUtils2.ImageCallback;

import android.R.integer;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 首页
 * 
 * @author Administrator
 */
public class HomeActivity extends BaseActivity {
	
	private FragmentManager supportFragmentManager;
	//下边的5个按钮
	private ImageView home_iv;
	private ImageView class_iv;
	private ImageView search_iv;
	private ImageView shop_iv;
	private ImageView more_iv;
	
	//购物车
	private TextView shop_tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		getWindow().setSoftInputMode(     
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  
		//初始化、默认第一个选中
		initView();
		
		supportFragmentManager = getSupportFragmentManager();
		//默认是第一页
		supportFragmentManager.beginTransaction().replace(R.id.home_fl, new HomeFragment()).commit();
		
	}
	@Override
	protected void onStart() {
		super.onStart();
		
	}
	private static List<View> views = new ArrayList<View>();

	//控件初始化
	private void initView() {
		home_iv = (ImageView) findViewById(R.id.imgHome);
		class_iv = (ImageView) findViewById(R.id.imgClassify);
		search_iv = (ImageView) findViewById(R.id.imgSearch);
		shop_iv = (ImageView) findViewById(R.id.imgShoppingCar);
		more_iv = (ImageView) findViewById(R.id.imgMore);
		
		shop_tv = (TextView) findViewById(R.id.textShopCarNum);
		shop_tv.setVisibility(View.GONE);
		
		views.add(home_iv);
		views.add(class_iv);
		views.add(search_iv);
		views.add(shop_iv);
		views.add(more_iv);
		
		click(home_iv);
		click(class_iv);
		click(search_iv);
		click(shop_iv);
		click(more_iv);
		
		//默认选中
		changeSelect(home_iv);
	}
	private View tempView;
	//切换
	private void changeSelect(View view){
		for (View v : views) {
			if (v == view && v != more_iv) {
				v.setSelected(true);
				tempView = view;
			}else {
				v.setSelected(false);
			}
		}
	}
	
	//点击事件
	public void click(View view){
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//选中状态
				changeSelect(v);
				//切换fragment
				BaseFragment baseFragment = null;
				switch (v.getId()) {
				case R.id.imgHome:
					//首页
					baseFragment = new HomeFragment();
					break;
				case R.id.imgClassify:
					//分类
					baseFragment = new ClassFragment();
//					MyToast.showToase(HomeActivity.this, "搜索");
					break;
				case R.id.imgSearch:
					//搜索
					baseFragment = new SearchFragment();
					break;
				case R.id.imgShoppingCar:
					//购物车
					baseFragment = new ShopFragment();
					break;
				case R.id.imgMore:
					//更多页面，应该是个Activity
					//记录当前选择
					startActivityForResult(new Intent(HomeActivity.this, MoreActivity.class), 9939);
					overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
					break;

				default:
					break;
				}
				if (baseFragment != null) {
					supportFragmentManager.beginTransaction().replace(R.id.home_fl, baseFragment).commit();
				}
			}
		});
	}
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		//切换更多选择到原来的
		changeSelect(tempView);
	}
}
