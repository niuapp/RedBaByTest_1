package com.itheima.redbaby.ui;

import com.itheima.redbaby.R;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SelectButton extends TextView {

	public SelectButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	//切换背景
	public void changeBG(boolean flag){
		if (flag) {
//			setImageResource(R.drawable.bar_class_selected);
//			setBackgroundColor(0x99ff0000);
		}else {
//			setImageResource(R.drawable.bar_class_normal);
//			setBackgroundColor(0x33ff0000);
		}
	}

}
