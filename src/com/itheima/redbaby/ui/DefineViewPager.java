package com.itheima.redbaby.ui;

import java.util.ArrayList;
import java.util.List;

import com.itheima.redbaby.R;
import com.itheima.redbaby.myutils.Px_DipUtils;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class DefineViewPager extends RelativeLayout {
	private Context context;
	private ViewPager vp_image;
	private LinearLayout ll_point;
	private int lastPointIndex;
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			vp_image.setCurrentItem((vp_image.getCurrentItem()+1)%images.size());
			sendEmptyMessageDelayed(0, 1500L);
		}
	};
	
	//选择页面的回调
	public interface OnSelectPagerListener{
		 public void onPageSelected(int position);
	}
	private OnSelectPagerListener listener;
	public OnSelectPagerListener getListener() {
		return listener;
	}
	public void setListener(OnSelectPagerListener listener) {
		this.listener = listener;
	}
	
	public DefineViewPager(Context context, List<ImageView> imageViews) {
		super(context);
		this.images = imageViews;
		if (this.images == null) {
			this.images = new ArrayList<ImageView>();
		}
		this.context = context;
		//System.out.println("================================="+imageViews.size());
		initView();
	}
	public DefineViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void update(List<ImageView> imageViews){
		this.images = imageViews;
		if (this.images == null) {
			this.images = new ArrayList<ImageView>();
		}
		initPoint();
		vp_image.getAdapter().notifyDataSetChanged();
	}
	
	//适配器
	private MyPageAdapter myPageAdapter;
	//初始化
	private void initView() {
		//引入布局
		 //关联布局
        View.inflate(context, R.layout.my_viewpager_layout, this);
        //查找对应控件
        vp_image = (ViewPager) findViewById(R.id.vp_image);
        ll_point = (LinearLayout) findViewById(R.id.ll_point);
        
        //指示点
        initPoint();
        
        //给ViewPager设置适配器
        if (myPageAdapter == null){
        	myPageAdapter = new MyPageAdapter();
        	vp_image.setAdapter(myPageAdapter);
        }else{
        	myPageAdapter.notifyDataSetChanged();
        }
        //发送滚动消息
        handler.sendEmptyMessageDelayed(0, 1500L);
        
      //给ViewPager设置改变监听
        vp_image.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override    //当页面被选择时调用
            public void onPageSelected(int position) {

                //上一个位置指示点状态更改
                ll_point.getChildAt(lastPointIndex).setEnabled(false);
                //当前位置指示点状态更改
                ll_point.getChildAt(position).setEnabled(true);
                //记录当前点
                lastPointIndex = position;
                
                //选择页面的回调
                if (listener != null) {
					listener.onPageSelected(position);
				}
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
	}
	//设置指示点
	private void initPoint() {
		ll_point.removeAllViews();
		//指示点
        for (int i = 0; i < images.size(); i++) {
            //添加指示点
            ImageView point = new ImageView(context);
            //设置图片(点)
            point.setBackgroundResource(R.drawable.point_selector);
            //给包point设置布局参数 间距
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
            params.leftMargin = (int) Px_DipUtils.dip2Px(context, 20f);
            point.setLayoutParams(params);
            //指示点应该显示的状态(第一个默认)
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }

            //把指示点添加到对应的 LinearLayout中
            ll_point.addView(point);
        }
	}
	
	//设置数据
	private List<ImageView> images;
	
	public List<ImageView> getImages() {
		return images;
	}
	public void setImages(List<ImageView> images) {
		this.images = images;
	}

	

	//适配器
	private class MyPageAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return images.size();
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			//TODO ==========BUG==============
			ImageView imageView = new ImageView(context);
			imageView.setImageDrawable(images.get(position).getBackground());
			container.addView(imageView);
			return imageView;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
}
