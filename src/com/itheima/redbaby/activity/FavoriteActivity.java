package com.itheima.redbaby.activity;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.itheima.redbaby.R;
import com.itheima.redbaby.domain.FavoriteItem;
import com.itheima.redbaby.domain.Newproduct;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.parser.NewproductParser;
import com.itheima.redbaby.ui.DefineGoodsItem;
import com.itheima.redbaby.utils.BaseParamsMapUtil;
import com.itheima.redbaby.utils.ConstantsRedBaby;

public class FavoriteActivity extends BaseActivity {
	
	private Button clean_bt;
	private ListView listView;
	//ListView数据
	private List<Newproduct> favoriteItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.favorite_activity);
		// 设置标题文本
		((TextView) findViewById(R.id.textTitle)).setText("收藏夹");
		
		clean_bt = (Button) findViewById(R.id.bt_clean);
		listView = (ListView) findViewById(R.id.favorite_list);

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
				((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
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
				convertView = new DefineGoodsItem(FavoriteActivity.this, favoriteItems.get(position));
			}
			return convertView;
		}
		
	}
}
