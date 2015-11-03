package com.itheima.redbaby.activity;

import java.util.ArrayList;
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
import com.itheima.redbaby.domain.MyOrder;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.OtherUtils;
import com.itheima.redbaby.parser.MyOrderParser;
import com.itheima.redbaby.ui.SelectButton;
import com.itheima.redbaby.utils.BaseParamsMapUtil;

public class OrderListActivity extends BaseActivity {
	
	private SelectButton zuijin_tv;
	private SelectButton all_tv;
	private SelectButton none_tv;
	//切换状态
	private List<SelectButton> views;
	
	private ListView listView;
	//集合4个(一个用来显示其他的用来存储)
	private List<MyOrder> orders;
	
	private List<MyOrder> okList;
	private List<MyOrder> allList;
	private List<MyOrder> noneList;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_order_activity);
		// 设置标题文本
				((TextView) findViewById(R.id.textTitle)).setText("我的订单");
		
		zuijin_tv = (SelectButton) findViewById(R.id.my_order_month);
		all_tv = (SelectButton) findViewById(R.id.my_order_all);
		none_tv = (SelectButton) findViewById(R.id.my_order_notsend);
		
		views = new ArrayList<SelectButton>();
		views.add(zuijin_tv);
		views.add(all_tv);
		views.add(none_tv);
		//默认全部
		setChangeState(all_tv);
		//设置点击监听
		clickView(zuijin_tv);
		clickView(all_tv);
		clickView(none_tv);
		
		listView = (ListView) findViewById(R.id.my_order_list);

	}
	//返回该页面时重新请求
	@Override
	protected void onStart() {
		initData();
		super.onStart();
	}

	private void initData() {
		//请求数据
		GetDataByNet.getDataByNet(this, "orderlist", BaseParamsMapUtil.getOrderList("1", "10", "2"), new MyOrderParser(), new GetDataByNet.OnSetDataListener() {
			@Override
			public void setData(Object data) {
				orders = (List<MyOrder>) data;
				//拆分数据
				okList = new ArrayList<MyOrder>();
				allList = new ArrayList<MyOrder>();
				noneList = new ArrayList<MyOrder>();
				for (MyOrder order : orders) {
					allList.add(order);
					if (order.getStatus().equals("未处理")) {
						noneList.add(order);
					}else{
						okList.add(order);
					}
				}
				
				//设置适配器
				listView.setAdapter(new MyAdapter());
				//设置点击监听，点击后到 订单详情页
				listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						//传id
						Intent intent = new Intent(OrderListActivity.this, OrderInfoActivity.class);
						intent.putExtra("orderId", orders.get(position).getOrderid());
						startActivity(intent);
						showChangeAnim();
					}
				});
			}
		});
	}
	
	//适配器
	private class MyAdapter extends BaseAdapter{
		@Override
		public int getCount() {
			return orders.size();
		}
		@Override
		public Object getItem(int position) {
			return orders.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = View.inflate(OrderListActivity.this, R.layout.orderlist_item, null);
			}
			MyOrder order = orders.get(position);
			TextView id = (TextView)convertView.findViewById(R.id.orderid_tv);
			id.setText(""+order.getOrderid());
			TextView price = (TextView)convertView.findViewById(R.id.orderprice_tv);
			price.setText(""+order.getPrice());
			TextView time = (TextView)convertView.findViewById(R.id.ordertime_tv);
			time.setText(""+order.getTime());
			TextView status = (TextView)convertView.findViewById(R.id.orderstate_tv);
			status.setText(""+order.getStatus());
			
			return convertView;
		}
		
	}

	//点击监听
	private void clickView(View view) {
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//更改适配器中集合为对应集合
				switch (v.getId()) {
				case R.id.my_order_month:
					setChangeState(zuijin_tv);
					orders = okList;
					((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
					break;
				case R.id.my_order_all:
					setChangeState(all_tv);
					orders = allList;
					((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
					break;
				case R.id.my_order_notsend:
					setChangeState(none_tv);
					orders = noneList;
					((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
					break;

				default:
					break;
				}
			}
		});
	}
	
	//状态切换
	private void setChangeState(SelectButton sb){
//		//TODO 选择更改待===================================
		for (SelectButton v : views) {
			if (v.getId() == sb.getId()) {
				sb.changeBG(true);
			}else {
				sb.changeBG(false);
			}
		}
	}
}
