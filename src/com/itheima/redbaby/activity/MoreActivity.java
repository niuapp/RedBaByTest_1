package com.itheima.redbaby.activity;

import com.itheima.redbaby.R;
import com.itheima.redbaby.myutils.MyToast;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class MoreActivity extends BaseActivity {
	
	private ListView listView;
	private TextView tel_tv;
	//集合  
	private static String[] items = {"账号中心", "浏览记录", "帮助中心", "用户反馈", "关于"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_more_activity);
		//设置标题文本
		((TextView)findViewById(R.id.textTitle)).setText("更多");
		//电话
		tel_tv = (TextView) findViewById(R.id.orderTelTv);
		tel_tv.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				MyToast.showToase(MoreActivity.this, "打电话");
				return true;
			}
		});
		
		listView = (ListView) findViewById(R.id.mylimitbuy_product_list);
		
		//设置适配器
		listView.setAdapter(new MyAdapter());
		
		//点击事件
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				case 0:
					startAndFinish(More_UserActivity.class, false);
					break;
				case 1:
					startAndFinish(More_HistoryActivity.class, false);
					break;
				case 2:
					startAndFinish(More_HelpActivity.class, false);
					break;
				case 3:
					startAndFinish(More_SuggestActivity.class, false);
					break;
				case 4:
					//关于
					startAndFinish(More_AboutActivity.class, false);
					break;

				default:
					break;
				}
			}
		});
	}
	
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return items.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return items[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(MoreActivity.this, R.layout.more_item, null);
			((TextView)view.findViewById(R.id.moreitem_textContent)).setText(items[position]);
			return view;
		}
		
	}

}
