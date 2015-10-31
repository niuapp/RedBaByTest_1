package com.itheima.redbaby.fragment;

import com.itheima.redbaby.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * 分类搜索
 * @author Administrator
 */
public class ClassFragment extends BaseFragment {
	
	private ListView listView;

	@Override
	public View initView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.class_activity, null);
		listView = (ListView) view.findViewById(R.id.class_lv);
		return view;
	}

	@Override
	public void initData() {

	}

}
