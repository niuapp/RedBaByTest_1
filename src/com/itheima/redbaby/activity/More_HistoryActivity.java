package com.itheima.redbaby.activity;

import java.util.ArrayList;
import java.util.List;

import com.itheima.redbaby.R;
import com.itheima.redbaby.domain.Newproduct;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.parser.NewproductParser;
import com.itheima.redbaby.ui.DefineGoodsItem;
import com.itheima.redbaby.utils.BaseParamsMapUtil;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class More_HistoryActivity extends BaseActivity {
	
	private Button clean_bt;
	private ListView listView;
	//ListView数据
	private List<Newproduct> favoriteItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_activity_co);
		// 设置标题文本
		((TextView) findViewById(R.id.more_title_tv)).setText("浏览历史");
		
		clean_bt = (Button) findViewById(R.id.bt_clean);
		listView = (ListView) findViewById(R.id.favorite_list);
		
		favoriteItems = new ArrayList<Newproduct>();

		GetDataByNet.getDataByNet(this, "favorites", BaseParamsMapUtil.getTopic("1", "10"), new NewproductParser(), new GetDataByNet.OnSetDataListener() {
			@Override
			public void setData(Object data) {
				favoriteItems = (List<Newproduct>) data;
				listView.setAdapter(new MyAdapter());
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//TODO ==========收藏夹==========
			}
		});
		
		clean_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				favoriteItems.clear();
				BaseAdapter adapter = (BaseAdapter)listView.getAdapter();
				if (adapter != null) {
					adapter.notifyDataSetChanged();
				}
			}
		});
	}
	
	//适配器
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return favoriteItems.size();
		}

		@Override
		public Object getItem(int position) {
			return favoriteItems.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = new DefineGoodsItem(More_HistoryActivity.this, favoriteItems.get(position));
			}
			return convertView;
		}
		
	}
}
