package com.itheima.redbaby.fragment;

import com.itheima.redbaby.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 搜索页面
 * @author Administrator
 */
public class SearchFragment extends BaseFragment {

	@Override
	public View initView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.search_activity, null);
		
		return view;
	}

	@Override
	public void initData() {

	}

}
