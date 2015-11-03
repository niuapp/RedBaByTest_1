package com.itheima.redbaby.ui;

import com.itheima.redbaby.R;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DefineCouponItem extends RelativeLayout {
	
	private String content;
	private Context context;
	
	//状态
	private boolean flag;

	public DefineCouponItem(Context context, String content) {
		super(context);
		this.context = context;
		this.content = content;
		initView();
	}

	//关联布局
	private void initView() {
		View.inflate(context, R.layout.coupon_item, this);
		//TextView
		((TextView)findViewById(R.id.coupon_textContent)).setText(content);
	}
	//点击的时候切换选择图片
	public void setChange(){
		ImageView imageView = (ImageView)findViewById(R.id.coupon_image);
		if (flag) {
			imageView.setImageResource(R.drawable.ok);
		}else{
			imageView.setImageDrawable(null);
		}
	}
}
