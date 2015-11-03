package com.itheima.redbaby.ui;

import com.itheima.redbaby.R;
import com.itheima.redbaby.domain.Limitbuy;
import com.itheima.redbaby.domain.Newproduct;
import com.itheima.redbaby.myutils.MyTime;
import com.itheima.redbaby.utils.ImageUtils2;
import com.itheima.redbaby.utils.ImageUtils2.ImageCallback;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DefineLimitItem extends RelativeLayout {

	private Context context;
	private Limitbuy data;

	public DefineLimitItem(Context context, Limitbuy data) {
		super(context);
		this.context = context;
		this.data = data;
		initView();
	}

	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			String timeStr = MyTime.getCurrentTime_sfm(tempTime -= 1000);
			time.setText(timeStr);
			sendEmptyMessageDelayed(0, 1000L);
		}
	};
	private TextView time;
	private long tempTime;
	
	// 关联布局
	private void initView() {
		View.inflate(context, R.layout.limit_items, this);
		//控件
		final ImageView iv = (ImageView) findViewById(R.id.item_iv);
		TextView name = (TextView) findViewById(R.id.item_name);
		TextView price = (TextView) findViewById(R.id.item_p);
		TextView price_y = (TextView) findViewById(R.id.item_p_y);
		time = (TextView) findViewById(R.id.item_time);
		
		//设置数据
		name.setText(data.getName());
		price.setText(data.getLimitprice());
		price_y.setText(data.getPrice());
		price_y.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG |Paint.ANTI_ALIAS_FLAG);
		
		tempTime = Long.parseLong(data.getLefttime());
		time.setText(MyTime.getCurrentTime_sfm(tempTime));
		handler.sendEmptyMessageDelayed(0, 1000L);
		
		Bitmap bitmap = ImageUtils2.loadImageDefault(context, context.getCacheDir(), data.getPic(), new ImageCallback() {
			@Override
			public void loadImage(Bitmap bitmap, String imagePath) {
				iv.setImageBitmap(bitmap);
			}
		}, true);
		iv.setImageBitmap(bitmap);
	}

}
