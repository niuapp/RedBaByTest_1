package com.itheima.redbaby.myutils;

import android.content.Context;

public class Px_DipUtils {

	/**
	 * dip -> px
	 */
	public static float dip2Px(Context context, float dp){
		//获取屏幕显示规格密度
		float scale = context.getResources().getDisplayMetrics().density;
		float px = dp * scale + 0.5f;
		return px;
	}

	/**
	 * px -> dip
	 */
	public static float px2Dip(Context context, float px){
		//获取屏幕显示规格密度
		float scale = context.getResources().getDisplayMetrics().density;
		float dp = px / scale + 0.5f;
		return dp;
	}
}
