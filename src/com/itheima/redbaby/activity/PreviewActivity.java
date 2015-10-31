package com.itheima.redbaby.activity;

import java.util.ArrayList;
import java.util.List;

import com.itheima.redbaby.R;
import com.itheima.redbaby.ui.DefineViewPager;
import com.itheima.redbaby.ui.DefineViewPager.OnSelectPagerListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * 引导页(预览页)
 * 
 * 一个ViewPager 3张 当ViewPager在最后一页时 显示进入首页按钮，点击按钮后给 sp中保存不是第一次进入，关闭页面去主页
 * 
 * @author Administrator
 */
public class PreviewActivity extends BaseActivity {

	// ViewPager Button
	private FrameLayout frameLayout_vp;
	private Button button;
	private List<ImageView> imageViews;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preview_activity);

		// 初始化
		frameLayout_vp = (FrameLayout) findViewById(R.id.pre_vp);
		button = (Button) findViewById(R.id.pre_bt_goHome);

		// 初始化集合
		initData();

		// 把自定义的ViewPager放到FrameLayout中
		DefineViewPager defineViewPager = new DefineViewPager(this, imageViews);
		frameLayout_vp.addView(defineViewPager);
		//setContentView(defineViewPager);
		defineViewPager.setListener(new OnSelectPagerListener() {
			@Override
			public void onPageSelected(int position) {
				// 如果是最后一页就显示按钮，否则不显示
				if (position == imageViews.size() - 1) {
					button.setVisibility(View.VISIBLE);
				} else {
					button.setVisibility(View.GONE);
				}
			}
		});
		
		//给按钮设置点击事件，点击后到首页，关闭当前页面
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(PreviewActivity.this, HomeActivity.class));
				//往sp中保存已经进过引导页的记录
				sp.edit().putBoolean("isFirst", true).commit();
				finish();
				showChangeAnim();
			}
		});

	}

	// 初始化集合
	private void initData() {
		imageViews = new ArrayList<ImageView>();

		for (int i = 0; i < 3; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(R.drawable.pre_1 + i);
			imageViews.add(imageView);
		}
	}
}
