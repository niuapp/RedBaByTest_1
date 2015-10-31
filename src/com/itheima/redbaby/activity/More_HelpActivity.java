package com.itheima.redbaby.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.R;
import com.itheima.redbaby.domain.Help;
import com.itheima.redbaby.domain.Help_detail;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.MyToast;
import com.itheima.redbaby.parser.HelpParser;
import com.itheima.redbaby.parser.Help_ContentParser;
import com.itheima.redbaby.utils.BaseParamsMapUtil;

public class More_HelpActivity extends BaseActivity {

	private TextView orderTelTv;
	private ListView listView;
	//对应的集合
	private List<Help> helps;
	//同时获取帮助信息
	private List<Help_detail> help_details;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help_activity);
		// 设置标题文本
		((TextView) findViewById(R.id.more_title_tv)).setText("帮助中心");
		
		orderTelTv = (TextView) findViewById(R.id.orderTelTv);
		orderTelTv.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				//传 数据  调用工具类中的方法  打电话
				MyToast.showToase(More_HelpActivity.this, "打电话");
				return true;
			}
		});
		
		
		//ListView
		listView = (ListView) findViewById(R.id.help_list);
		
		//获取数据 -- 帮助列表
		GetDataByNet byNet = new GetDataByNet(this, "help", BaseParamsMapUtil.getHelp("12"), new HelpParser(), new GetDataByNet.OnSetDataListener() {
			@Override
			public void setData(Object data) {
				helps = (List<Help>) data;
				//填充数据
				listView.setAdapter(new MyAdapter());
			}
		});
		//获取数据 -- 帮助内容
		GetDataByNet byNet2 = new GetDataByNet(this, "help_detail", BaseParamsMapUtil.getHelp_Detail("1"), new Help_ContentParser(), new GetDataByNet.OnSetDataListener() {
			@Override
			public void setData(Object data) {
				help_details = (List<Help_detail>) data;
				//得到内容后给 listView 设置点击事件，带着对应的数据打开内容页
				listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						//索引是对应的
						Intent intent = new Intent(More_HelpActivity.this, HelpContentActivity.class);
						intent.putExtra("title", help_details.get(position).getTitle());
						intent.putExtra("content", help_details.get(position).getContent());
						startActivity(intent);
					}
				});
			}
		});
		
	}
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return helps.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return helps.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(More_HelpActivity.this, R.layout.more_item, null);
			((TextView)view.findViewById(R.id.moreitem_textContent)).setText(helps.get(position).getTitle());
			return view;
		}
		
	}
}
