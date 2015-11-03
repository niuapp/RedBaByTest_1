package com.itheima.redbaby.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redbaby.R;
import com.itheima.redbaby.domain.MyAddress;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.parser.MyAddressParser;

public class AddressActivity extends BaseActivity {
	
	private ListView listView;
	//对应集合
	private List<MyAddress> addressList;
	
	//新增地址
	private Button add_bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address_activity);
		// 设置标题文本
		((TextView) findViewById(R.id.textTitle)).setText("地址管理");
		add_bt = (Button) findViewById(R.id.bt_add);
		add_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//点击去新增地址页面
				startAndResult(AddressAddActivity.class);
			}
		});
		
		listView = (ListView) findViewById(R.id.address_list);
		
		//请求数据
		GetDataByNet.getDataByNet(this, "addresslist", null, new MyAddressParser(), new GetDataByNet.OnSetDataListener() {
			@Override
			public void setData(Object data) {
				addressList = (List<MyAddress>) data;
				//通过基类传递集合
				BA_addressList = addressList;
				//System.out.println("======"+addressList.size()+"======"+BA_addressList.size());
				//设置适配器
				listView.setAdapter(new MyAdapter());
				//设置点击条目监听，点击后跳到编辑页, 在编辑页保存数据后更新list
				listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						//传对应的 position
						Intent intent = new Intent(AddressActivity.this, AddressEditActivity.class);
						intent.putExtra("index", position);
						
						startActivityForResult(intent, 9999932);
						showChangeAnim();
					}
				});
			}
		});
	}
	
	//刷新数据
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		//得到数据
		addressList = BA_addressList;
		((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
	}
	
	//适配器
	private class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return addressList.size();
		}
		@Override
		public Object getItem(int position) {
			return addressList.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = View.inflate(AddressActivity.this, R.layout.address_item, null);
			MyAddress m = addressList.get(position);
			((TextView)view.findViewById(R.id.addressinfo_tv)).setText(m.getName()+"\n"+m.getPhonenumber()+"\n"+m.getAreaid()+"\n"+m.getAreadetail());
			return view;
		}
		
	}
}
