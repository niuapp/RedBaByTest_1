package com.itheima.redbaby.activity;

import java.util.ArrayList;
import java.util.List;

import com.itheima.redbaby.R;
import com.itheima.redbaby.domain.Limitbuy;
import com.itheima.redbaby.domain.Newproduct;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.parser.NewproductParser;
import com.itheima.redbaby.ui.DefineGoodsItem;
import com.itheima.redbaby.utils.BaseParamsMapUtil;
import com.itheima.redbaby.utils.ConstantsRedBaby;

import android.content.Intent;
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
 * 新品上架
 * 
 * @author Administrator
 * 
 */
public class NewproductActivity extends BaseActivity {
	
	//List
	private ListView listView;
	//数据集合
	private List<Newproduct> newproducts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_new_activity);
		// 设置标题文本
		((TextView) findViewById(R.id.textTitle)).setText("新品上架");
		listView = (ListView) findViewById(R.id.newproduct_list);
		
		//获取数据
		GetDataByNet.getDataByNet(this, "newproduct", BaseParamsMapUtil.getLimitbuy("1", "10", ConstantsRedBaby.SALE_DOWN), new NewproductParser(), new GetDataByNet.OnSetDataListener() {
			@Override
			public void setData(Object data) {
				newproducts = (List<Newproduct>) data;
				
				listView.setAdapter(new MyAdapter());
			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//TODO =====================
//				startAndFinish(商品详情, true)
			}
		});
	}
	
	//适配器
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return newproducts.size();
		}

		@Override
		public Object getItem(int position) {
			return newproducts.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = new DefineGoodsItem(NewproductActivity.this, newproducts.get(position));
			}
			return convertView;
		}
		
	}
}
