package com.itheima.redbaby.activity;

import java.util.ArrayList;
import java.util.List;

import com.itheima.redbaby.R;
import com.itheima.redbaby.adapter.TopicAdapter;
import com.itheima.redbaby.domain.Limitbuy;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.MyToast;
import com.itheima.redbaby.parser.LimitbuyParser;
import com.itheima.redbaby.utils.BaseParamsMapUtil;
import com.itheima.redbaby.utils.ConstantsRedBaby;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
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
		GetDataByNet byNet = new GetDataByNet(this, "limitbuy", BaseParamsMapUtil.getLimitbuy("1", "10", ConstantsRedBaby.SALE_DOWN), new LimitbuyParser(), new GetDataByNet.OnSetDataListener() {
			@Override
			public void setData(Object data) {
				limitbuys = (List<Limitbuy>) data;
				List<String> temp = new ArrayList<String>();
				for (Limitbuy s : limitbuys) {
					temp.add(s.toString());
				}
				//设置数据
				listView.setAdapter(new ArrayAdapter<String>(LimitbuyActivity.this, android.R.layout.simple_list_item_1, temp));
			}
		});
		//点击
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyToast.showToase(LimitbuyActivity.this, position+"");
			}
		});
	}
}
