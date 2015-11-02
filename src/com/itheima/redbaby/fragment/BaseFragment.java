package com.itheima.redbaby.fragment;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itheima.redbaby.R;

public abstract class BaseFragment extends Fragment {
	public View view;
	public Context context;
	//homeFragment 的图片集合
	public static List<ImageView> homeFragmentList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.context = getActivity();
		view = initView(inflater, container,
				savedInstanceState);
		
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
	}
	/**
	 * 初始化 view
	 * @return
	 */
	public abstract View initView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState);
	/**
	 * 初始化数据
	 */
	public abstract void initData();
	/**
	 * 动画
	 */
	public void showChangeAnim(){
		((Activity) context).overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
	}
}
