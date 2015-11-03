package com.itheima.redbaby.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.itheima.redbaby.R;
import com.itheima.redbaby.activity.BaseActivity;
import com.itheima.redbaby.activity.ClassActivity_2;
import com.itheima.redbaby.adapter.MyTextAdapter;
import com.itheima.redbaby.domain.MyCategory;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.OtherUtils;
import com.itheima.redbaby.parser.CategoryParser;

/**
 * 分类搜索
 * @author Administrator
 */
public class ClassFragment extends BaseFragment {
	
	private ListView listView;
	//总集合
	private List<MyCategory> allData;
	//一级，二级，三级
	private List<MyCategory> categories_1;
	private List<MyCategory> categories_2;
	private List<MyCategory> categories_3;
	@Override
	public View initView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.class_activity, null);
		listView = (ListView) view.findViewById(R.id.include_list);
		return view;
	}

	@Override
	public void initData() {
		//请求数据后根据 自己的id分成三个集合， 把集合存到 Base中(二三级都是Activity 所以用context存到BaseActivity)，
		//一级页面点击时 传自己的id 在二级页面接收id 在对应集合中查(结果展示)，三级同样
		//获取数据
		GetDataByNet.getDataByNet(context, "category", null, new CategoryParser(), new GetDataByNet.OnSetDataListener() {
			@Override
			public void setData(Object data) {
				allData = (List<MyCategory>) data;
				categories_1 = new ArrayList<MyCategory>();
				categories_2 = new ArrayList<MyCategory>();
				categories_3 = new ArrayList<MyCategory>();
				for (MyCategory mc : allData) {
					//判断id的长度分 3 5 7
					if (3 == mc.getId().length()) {
						categories_1.add(mc);
					}else if (5 == mc.getId().length()) {
						categories_2.add(mc);
					}else if (7 == mc.getId().length()) {
						categories_3.add(mc);
					}
				}
				//给BaseActivity中保存 只保存 2 3
				((BaseActivity)context).BA_categories_2 = categories_2;
				((BaseActivity)context).BA_categories_3 = categories_3;
				
				//设置当前页数据
				listView.setAdapter(new MyTextAdapter(context, OtherUtils.toStringList(categories_1)));
			}
		});
		
		//ListView的条目点击事件 跳二级页面，带自己id
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//二级
				Intent intent = new Intent(context, ClassActivity_2.class);
				intent.putExtra("categoryId", categories_1.get(position).getId());
				intent.putExtra("categoryName", categories_1.get(position).getName());
				startActivity(intent);
				showChangeAnim();
			}
		});
	}
	

}
