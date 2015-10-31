package com.itheima.redbaby.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.redbaby.R;
import com.itheima.redbaby.domain.Brand;
import com.itheima.redbaby.domain.BrandValue;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.MyToast;
import com.itheima.redbaby.parser.BrandParser;
import com.itheima.redbaby.ui.DefineBrandItem;
import com.itheima.redbaby.ui.DefineBrandItem.OnDGItemClickListener;
import com.itheima.redbaby.utils.ImageUtils2;
import com.itheima.redbaby.utils.ImageUtils2.ImageCallback;

public class BrandActivity extends BaseActivity {
	
	//
	private LinearLayout content_ll;
	//数据集合
	private List<Brand> brands;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.brand_activity);
		//设置标题文本
		((TextView)findViewById(R.id.textTitle)).setText("推荐品牌");
		
		//初始化控件
		content_ll = (LinearLayout) findViewById(R.id.content_ll);
		//请求数据
		GetDataByNet byNet = new GetDataByNet(this, "brand", null, new BrandParser(), new GetDataByNet.OnSetDataListener() {
			
			private DefineBrandItem brandItem;

			@Override
			public void setData(Object data) {
				brands = (List<Brand>) data;
				//填充
				for (final Brand brand : brands) {
					//
					final List<ImageView> images = new ArrayList<ImageView>();
					for (BrandValue bv : brand.getValue()) {
						String imageUrl = bv.getPic();
						ImageView imageView = new ImageView(BrandActivity.this);
						Bitmap bitmap = ImageUtils2.loadImageDefault(BrandActivity.this, getCacheDir(), imageUrl, new ImageCallback() {
							
							@Override
							public void loadImage(Bitmap bitmap, String imagePath) {
								brandItem.update(images);
							}
						}, true);
						imageView.setBackground(new BitmapDrawable(bitmap ));
						images.add(imageView);
					}
					brandItem = new DefineBrandItem(BrandActivity.this, images, brand.getKey());
					//点击事件
					brandItem.setClickListener(new OnDGItemClickListener() {
						@Override
						public void onClick(int position) {
							//
							MyToast.showThreadToase(BrandActivity.this, brand.getValue().get(position).getId()+"");
						}
					});
					content_ll.addView(brandItem);
				}
			}
		});
	}
}
