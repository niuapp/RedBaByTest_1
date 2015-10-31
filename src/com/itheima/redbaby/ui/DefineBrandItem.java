package com.itheima.redbaby.ui;

import java.util.ArrayList;
import java.util.List;

import com.itheima.redbaby.R;

import android.R.integer;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class DefineBrandItem extends LinearLayout {
	
	private List<ImageView> images;
	private String title;
	private Context context;
	private GridView gridView;
	private MyAdapter adapter;
	
	//回调接口
	public interface OnDGItemClickListener{
		public void onClick(int position);
	}
	private OnDGItemClickListener clickListener;
	public void setClickListener(OnDGItemClickListener clickListener) {
		this.clickListener = clickListener;
	}

	public DefineBrandItem(Context context, List<ImageView> images, String title) {
		super(context);
		this.context = context;
		this.images = images;
		if (this.images == null) {
			images = new ArrayList<ImageView>();
		}
		this.title = title;
		initView();
	}
	public void update(List<ImageView> images){
		this.images = images;
		if (this.images == null) {
			images = new ArrayList<ImageView>();
		}
		((BaseAdapter) gridView.getAdapter()).notifyDataSetChanged();
	}

	//初始化，关联布局
	private void initView() {
		View.inflate(context, R.layout.brand_items, this);
		//标题
		((TextView) findViewById(R.id.brand_item_tv)).setText(title);
		gridView = (GridView) findViewById(R.id.brand_item_gridview);
		if (adapter == null) {
			adapter = new MyAdapter();
			gridView.setAdapter(adapter);
		}else {
			adapter.notifyDataSetChanged();
		}
		
		//条目点击回调
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//返回 id()
				if (clickListener != null) {
					clickListener.onClick(position);
				}
			}
		});
	}
	
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return images.size();
		}

		@Override
		public Object getItem(int position) {
			return images.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return images.get(position);
		}
		
	}

}
