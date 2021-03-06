package com.itheima.redbaby.activity;

import java.util.ArrayList;
import java.util.List;

import com.itheima.redbaby.R;
import com.itheima.redbaby.adapter.MyTextAdapter;
import com.itheima.redbaby.domain.MyCategory;
import com.itheima.redbaby.myutils.MyToast;
import com.itheima.redbaby.myutils.OtherUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ClassActivity_2 extends BaseActivity {
	
	private ListView listView;
	//填充集合
	private List<MyCategory> contentList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.class_2_activity);
		Intent intent = getIntent();
		String id = intent.getStringExtra("categoryId");
		if (id == null) {
			finish();
			showChangeAnim();
			MyToast.showToase(this, "无内容");
			return;
		}
		// 设置标题文本
		((TextView) findViewById(R.id.textTitle)).setText(intent.getStringExtra("categoryName"));
		listView = (ListView) findViewById(R.id.include_list);
		//拿BA集合判断id 如果一样就拿出来，用新集合填充
		contentList = new ArrayList<MyCategory>();
//		System.out.println("==================="+BA_categories_2);
		for (MyCategory mc : BA_categories_2) {
			if (id.equals(mc.getParent_id())) {
				contentList.add(mc);
			}
		}
		if (contentList.size() == 0) {
			finish();
			showChangeAnim();
			MyToast.showToase(this, "无内容");
			return;
		}
		//设置显示
		listView.setAdapter(new MyTextAdapter(this, OtherUtils.toStringList(contentList)));
		
		//点击  到三级页面，如果三级页面被关闭，自己也跟着关闭
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//三级
				Intent intent = new Intent(ClassActivity_2.this, ClassActivity_3.class);
				intent.putExtra("categoryId", contentList.get(position).getId());
				intent.putExtra("categoryName", contentList.get(position).getName());
				startActivityForResult(intent, 9324);
				showChangeAnim();
			}
		});
		
	}
	//关闭
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		finish();
		showChangeAnim();
	}
}
