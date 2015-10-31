package com.itheima.redbaby.adapter;

import java.util.List;

import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redbaby.R;
import com.itheima.redbaby.activity.TopicActivity;
import com.itheima.redbaby.domain.Topic;
import com.itheima.redbaby.domain.Topic1;
import com.itheima.redbaby.utils.ConstantsRedBaby;
import com.itheima.redbaby.utils.ImageUtils2;
import com.itheima.redbaby.utils.ImageUtils2.ImageCallback;

public class TopicAdapter extends BaseAdapter {
	List<Topic> list;
	private TopicActivity activity;
	

	public TopicAdapter(List<Topic> paramObject,TopicActivity activity) {
		list= paramObject;
		this.activity=activity;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	ImageCallback callback=new ImageCallback() {
		@Override
		public void loadImage(Bitmap bitmap, String imagePath) {
			notifyDataSetChanged();
		}
	};
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder hodler;
		View view ;
		if (convertView!=null&&convertView instanceof RelativeLayout) {
			view = convertView;
			hodler=(ViewHolder) view.getTag();
		}else{
			view=View.inflate(activity, R.layout.topic_item, null);
			hodler=new ViewHolder();
			hodler.topic_IV=(ImageView) view.findViewById(R.id.topic_IV);
			hodler.topic_TV=(TextView) view.findViewById(R.id.topic_TV);
			view.setTag(hodler);
		}
		final Topic limitbuy=list.get(position);
		Bitmap mbitmap=ImageUtils2.loadImageDefault(activity, activity.getCacheDir(), limitbuy.getPic(), callback,true);
		if (mbitmap!=null) {
			hodler.topic_IV.setImageBitmap(mbitmap);
		}else{
			hodler.topic_IV.setImageResource(R.drawable.product_loading);
		}
		hodler.topic_IV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(list.size()==0){
					if(goneView!=null){
						goneView.goneView();
					}
				}
			}
		});
		
		hodler.topic_TV.setText(limitbuy.getName());
		return view;
	}
	private GoneView goneView;
	public void setGoneView(GoneView goneView){
		this.goneView=goneView;
		
	}
public interface GoneView{
	void goneView();
}
	static class ViewHolder {
		TextView topic_TV;
		ImageView topic_IV;
		
	}
	
}



