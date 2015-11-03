package com.itheima.redbaby.myutils;

import java.util.ArrayList;
import java.util.List;

import com.itheima.redbaby.domain.MyCategory;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class OtherUtils {
	
	//ScrollView 中 ListView
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		//条目的高
		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			//System.out.println("==========================="+listItem);
			listItem.measure(0, 0); // 计算子项View 的宽高
			totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
	}
	/**
	 * 往TextView中追加文本
	 * @param tv 
	 * @param str
	 */
	public static void addText(TextView tv, String str){
		tv.setText(tv.getText()+str);
	}
	/**
	 * categories集合 转String 集合
	 * @param categories
	 * @return
	 */
	public static List<String> toStringList(List<MyCategory> categories){
		List<String> list = new ArrayList<String>();
		for (MyCategory myCategory : categories) {
			list.add(myCategory.getName());
		}
		return list;
	}
}
