package com.itheima.redbaby.activity;

import com.itheima.redbaby.R;
import com.itheima.redbaby.ui.DefineCouponItem;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CouponActivity extends BaseActivity {

	private ListView listView;
	// 数据
	private static String[] items = { "9月惊喜1元礼券", "国庆2元礼券", "元旦5元礼券", "春节红包" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coupon_activity);
		// 设置标题文本
		((TextView) findViewById(R.id.textTitle)).setText("优惠券");

		listView = (ListView) findViewById(R.id.include_list);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, items));
		// TODO ==============优惠券 点击条目  状态变更================
		// listView.setAdapter(new MyAdapter());
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return items.length;
		}

		@Override
		public Object getItem(int position) {
			return items[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		private View cv;

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			cv = new DefineCouponItem(CouponActivity.this, items[position]);
			cv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					((DefineCouponItem) cv).setChange();
					notifyDataSetChanged();
				}
			});

			return cv;
		}

	}
}
