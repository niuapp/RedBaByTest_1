package com.itheima.redbaby.adapter;

import java.util.List;

import com.itheima.redbaby.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyTextAdapter extends BaseAdapter {

	private List<String> items;
	private Context context;
	
	public MyTextAdapter(Context context, List<String> items) {
		super();
		this.items = items;
		this.context = context;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.more_item, null);
		}
		((TextView)convertView.findViewById(R.id.moreitem_textContent)).setText(items.get(position));
		return convertView;
	}

}
