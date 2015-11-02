package com.itheima.redbaby.activity;

import com.itheima.redbaby.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class More_UserActivity extends BaseActivity {
	
	private Button loginout_tv;

	private ListView listView;
	private static String[] listData = { "我的订单", "地址管理", "优惠券/礼品卡", "收藏夹" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_activity);
		// 设置标题文本
		((TextView) findViewById(R.id.more_title_tv)).setText("账户中心");

		loginout_tv = (Button) findViewById(R.id.loginout_tv);
		listView = (ListView) findViewById(R.id.userinfo_list);
		
		// 给ListView设置适配器、点击监听
		initView();
	}

	private void initView() {
		//退出登陆按钮
		loginout_tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sp.edit().putBoolean("isLogin", false).commit();
				finish();//关闭  到更多页面
				showChangeAnim();
			}
		});
		
		listView.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = View.inflate(More_UserActivity.this, R.layout.more_item, null);
				((TextView)view.findViewById(R.id.moreitem_textContent)).setText(listData[position]);
				return view;
			}
			@Override
			public long getItemId(int position) {
				return position;
			}
			@Override
			public Object getItem(int position) {
				return listData[position];
			}
			@Override
			public int getCount() {
				return listData.length;
			}
		});
		// 点击监听
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					//我的订单
					startAndFinish(OrderListActivity.class, false);
					break;
				case 1:
					//地址管理
					startAndFinish(AddressActivity.class, false);
					break;
				case 2:
					//优惠
					break;
				case 3:
					//收藏夹
					break;

				default:
					break;
				}
			}
		});
	}
}
