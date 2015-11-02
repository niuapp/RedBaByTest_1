package com.itheima.redbaby.activity;

import java.util.ArrayList;
import java.util.List;

import com.itheima.redbaby.R;
import com.itheima.redbaby.adapter.TopicAdapter;
import com.itheima.redbaby.domain.Limitbuy;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.MyToast;
import com.itheima.redbaby.parser.LimitbuyParser;
import com.itheima.redbaby.ui.DefineLimitItem;
import com.itheima.redbaby.utils.BaseParamsMapUtil;
import com.itheima.redbaby.utils.ConstantsRedBaby;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 限时抢购
 * @author Administrator
 */
public class LimitbuyActivity extends BaseActivity {
	
	private ListView listView;
	//对应集合
	private List<Limitbuy> limitbuys;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_limit_activity);
		//设置标题文本
		((TextView)findViewById(R.id.textTitle)).setText("限时抢购");
		
		listView = (ListView) findViewById(R.id.mylimitbuy_product_list);
		
		//请求数据
		GetDataByNet.getDataByNet(this, "limitbuy", BaseParamsMapUtil.getLimitbuy("1", "10", ConstantsRedBaby.SALE_DOWN), new LimitbuyParser(), new GetDataByNet.OnSetDataListener() {
			@Override
			public void setData(Object data) {
				limitbuys = (List<Limitbuy>) data;
				listView.setAdapter(new MyAdapter());
			}
		});
		//点击
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//TODO ======================
				MyToast.showToase(LimitbuyActivity.this, position+"");
			}
		});
	}
	
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return limitbuys.size();
		}
		@Override
		public Object getItem(int position) {
			return limitbuys.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = new DefineLimitItem(LimitbuyActivity.this, limitbuys.get(position));
			}
			return convertView;
		}
		
	}
}
