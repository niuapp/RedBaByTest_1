package com.itheima.redbaby.ui;

import com.itheima.redbaby.R;
import com.itheima.redbaby.domain.Newproduct;
import com.itheima.redbaby.utils.ImageUtils2;
import com.itheima.redbaby.utils.ImageUtils2.ImageCallback;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DefineGoodsItem extends RelativeLayout {

	private Context context;
	private Newproduct data;

	public DefineGoodsItem(Context context, Newproduct data) {
		super(context);
		this.context = context;
		this.data = data;
		initView();
	}

	// 关联布局
	private void initView() {
		View.inflate(context, R.layout.new_items, this);
		//控件
		final ImageView iv = (ImageView) findViewById(R.id.item_iv);
		TextView name = (TextView) findViewById(R.id.item_name);
		TextView price = (TextView) findViewById(R.id.item_p);
		TextView price_y = (TextView) findViewById(R.id.item_p_y);
		TextView assess = (TextView) findViewById(R.id.item_pingj);
		//设置数据
		name.setText(data.getName());
		price.setText(data.getMarketprice());
		price_y.setText(data.getPrice());
		price_y.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG |Paint.ANTI_ALIAS_FLAG);
		assess.setText("评论数：(不知道)");
		
		Bitmap bitmap = ImageUtils2.loadImageDefault(context, context.getCacheDir(), data.getPic(), new ImageCallback() {
			@Override
			public void loadImage(Bitmap bitmap, String imagePath) {
				iv.setImageBitmap(bitmap);
			}
		}, true);
		iv.setImageBitmap(bitmap);
	}

}
