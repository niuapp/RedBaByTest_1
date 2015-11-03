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

public class AddressAddActivity extends BaseActivity {
	
	private EditText name_et;
	private EditText phone_et;
	private EditText city_et;
	private EditText xiangxi_et;
	
	private Button save_bt;
	private int index;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.address_add_activity);
		// 设置标题文本
		((TextView) findViewById(R.id.textTitle)).setText("新增地址");
		
		name_et = (EditText) findViewById(R.id.et_name);
		phone_et = (EditText) findViewById(R.id.et_phone);
		city_et = (EditText) findViewById(R.id.et_city);
		xiangxi_et = (EditText) findViewById(R.id.et_xiangxi);
		save_bt = (Button) findViewById(R.id.bt_save);
		clickButton(save_bt);

	}
	private void clickButton(View view){
		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.bt_save:
					//保存更改
					MyAddress address = new MyAddress();
					address.setId("");
					address.setName(name_et.getText().toString().trim());
					address.setPhonenumber(phone_et.getText().toString().trim());
					address.setAreaid(city_et.getText().toString().trim());
					address.setAreadetail(xiangxi_et.getText().toString().trim());
					//添加
					BA_addressList.add(address);
					//关闭页面(地址列表直接更新，这边发消息到服务器记录);
					GetDataByNet.getDataByNet(AddressAddActivity.this, "addresssave", BaseParamsMapUtil.getAddressEdit(address), new NullParser(), null);
					finishX();
					break;

				default:
					break;
				}
			}
		});
	}
}
