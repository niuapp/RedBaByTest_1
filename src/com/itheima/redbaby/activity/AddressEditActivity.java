package com.itheima.redbaby.activity;

import com.itheima.redbaby.R;
import com.itheima.redbaby.domain.MyAddress;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.MyToast;
import com.itheima.redbaby.parser.NullParser;
import com.itheima.redbaby.utils.BaseParamsMapUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddressEditActivity extends BaseActivity {
	
	private EditText name_et;
	private EditText phone_et;
	private EditText city_et;
	private EditText xiangxi_et;
	
	private Button delete_bt;
	private Button default_bt;
	private Button save_bt;
	private int index;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address_edit_activity);
		// 设置标题文本
		((TextView) findViewById(R.id.textTitle)).setText("修改地址");
		
		name_et = (EditText) findViewById(R.id.et_name);
		phone_et = (EditText) findViewById(R.id.et_phone);
		city_et = (EditText) findViewById(R.id.et_city);
		xiangxi_et = (EditText) findViewById(R.id.et_xiangxi);
		
		default_bt = (Button) findViewById(R.id.bt_default);
		delete_bt = (Button) findViewById(R.id.bt_delete);
		save_bt = (Button) findViewById(R.id.bt_save);

		Intent intent = getIntent();
		index = intent.getIntExtra("index", -1);
		// 获取集合中对应数据，显示
		MyAddress address = BA_addressList.get(index);
		// 显示对应数据 点击保存后 关闭页面回显，设默认地址，删除地址（删除集合对应条目）
		String name = address.getName();
		String phoneN = address.getPhonenumber();
		String city = address.getAreaid();
		String xiangxi = address.getAreadetail();
		
		name_et.setText(name);
		phone_et.setText(phoneN);
		city_et.setText(city);
		xiangxi_et.setText(xiangxi);
		
		
		//三个按钮的点击监听
		clickButton(default_bt);
		clickButton(delete_bt);
		clickButton(save_bt);

	}
	private void clickButton(View view){
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.bt_default:
					//默认地址
					MyToast.showToase(AddressEditActivity.this, "设置默认地址");
					GetDataByNet.getDataByNet(AddressEditActivity.this, "addressdefault", BaseParamsMapUtil.getHelp(BA_addressList.get(index).getId()), new NullParser(), null);
					finishX();
					break;
				case R.id.bt_delete:
					//先请求 然后移除这个地址
					GetDataByNet.getDataByNet(AddressEditActivity.this, "addressdelete", BaseParamsMapUtil.getHelp(BA_addressList.get(index).getId()), new NullParser(), null);
					BA_addressList.remove(index);
					finishX();
					break;
				case R.id.bt_save:
					//保存更改
					BA_addressList.get(index).setName(name_et.getText().toString().trim());
					BA_addressList.get(index).setPhonenumber(phone_et.getText().toString().trim());
					BA_addressList.get(index).setAreaid(city_et.getText().toString().trim());
					BA_addressList.get(index).setAreadetail(xiangxi_et.getText().toString().trim());
					//关闭页面(地址列表直接更新，这边发消息到服务器记录);
					GetDataByNet.getDataByNet(AddressEditActivity.this, "addresssave", BaseParamsMapUtil.getAddressEdit(BA_addressList.get(index)), new NullParser(), null);
					finishX();
					break;

				default:
					break;
				}
			}
		});
	}
}
