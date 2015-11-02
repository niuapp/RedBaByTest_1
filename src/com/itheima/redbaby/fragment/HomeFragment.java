package com.itheima.redbaby.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.itheima.redbaby.R;
import com.itheima.redbaby.activity.BrandActivity;
import com.itheima.redbaby.activity.HomeActivity;
import com.itheima.redbaby.activity.HotproducttActivity;
import com.itheima.redbaby.activity.LimitbuyActivity;
import com.itheima.redbaby.activity.NewproductActivity;
import com.itheima.redbaby.activity.TopicActivity;
import com.itheima.redbaby.domain.HomeTitle;
import com.itheima.redbaby.myutils.GetDataByNet;
import com.itheima.redbaby.myutils.MyToast;
import com.itheima.redbaby.parser.HomeTitleParser;
import com.itheima.redbaby.ui.DefineViewPager;
import com.itheima.redbaby.utils.ImageUtils2;
import com.itheima.redbaby.utils.ImageUtils2.ImageCallback;

/**
 * 首页
 * 
 * @author Administrator
 * 
 */
public class HomeFragment extends BaseFragment {

	// 各个控件
	private EditText search_et;
	private Button serach_bt;
	private FrameLayout frameLayout_vp;
	private ListView listView;
	private TextView dingGRX;
	// ViewPager的数据
	private List<ImageView> images;
	// ListView的数据
	private static String[] listViewC = { "限时抢购", "促销快报", "新品上架", "热门单品",
			"推荐品牌" };
	// 对应的第一个图片
	private static int lv_imageId = R.drawable.home_classify_01;

	/**
	 * 开启页面
	 */
	private <T> void startAndFinish(Class<T> c) {
		startActivity(new Intent(context, c));
		// finish();
		showChangeAnim();
	}

	@Override
	public void initData() {
		// 请求数据，获取图片
		GetDataByNet.getDataByNet(getActivity(), "home", null,
				new HomeTitleParser(), new GetDataByNet.OnSetDataListener() {
					private DefineViewPager defineViewPager;

					@Override
					public void setData(Object data) {
						List<HomeTitle> temp = (List<HomeTitle>) data;
						images = new ArrayList<ImageView>();
						// 遍历解析
						for (HomeTitle homeTitle : temp) {

							Bitmap bitmap = ImageUtils2.loadImageDefault(
									context, context.getCacheDir(),
									homeTitle.getPic(), new ImageCallback() {

										@Override
										public void loadImage(Bitmap bitmap,
												String imagePath) {
											// System.out.println("==========11111"+images);
											ImageView image = new ImageView(
													context);
											image.setBackground(new BitmapDrawable(
													bitmap));
											images.add(image);
											homeFragmentList = images;
											defineViewPager.update(homeFragmentList);
										}
									}, true);
							// Bitmap bitmap =
							// Image_Utils.getBitmapByUrl(HomeActivity.this,
							// homeTitle.getPic());
							// bitmapTemps.add(bitmap);

						}
						//System.out.println("==========11111" + images);
						if (homeFragmentList == null) {
							homeFragmentList = images;
						}
						defineViewPager = new DefineViewPager(context, homeFragmentList);
						// 添加到FrameLayout
						frameLayout_vp.removeAllViews();
						frameLayout_vp.addView(defineViewPager);
					}
				});

		// 设置适配器
		listView.setAdapter(new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = View.inflate(context, R.layout.home_item, null);
				TextView tView = (TextView) view
						.findViewById(R.id.homeitem_textContent);
				tView.setText(listViewC[position]);
				Drawable drawable = getResources().getDrawable(
						lv_imageId + position);
				drawable.setBounds(0, 0, drawable.getMinimumWidth(),
						drawable.getMinimumHeight());
				tView.setCompoundDrawables(drawable, null, null, null);

				return view;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public int getCount() {
				return listViewC.length;
			}
		});

		// 解决ScrollView + ListView 数据显示不全(直接设置ListView布局的高度)
		// OtherUtils.setListViewHeightBasedOnChildren(listView);

		// ListView 的点击事件，开启不同的 Activity
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (position) {
				// "限时抢购", "促销快报", "新品上架", "热门单品", "推荐品牌"
				case 0:
					startAndFinish(LimitbuyActivity.class);
					break;
				case 1:
					// 促销快报
					startAndFinish(TopicActivity.class);
					break;
				case 2:
					// 新品上架(商品列表)
					startAndFinish(NewproductActivity.class);
					break;
				case 3:
					startAndFinish(HotproducttActivity.class);
					break;
				case 4:
					startAndFinish(BrandActivity.class);
					break;

				default:
					break;
				}
			}
		});
	}

	@Override
	public View initView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_activity_pageview, null);
		// 初始化控件
		search_et = (EditText) view.findViewById(R.id.editSearchInfo);
		serach_bt = (Button) view.findViewById(R.id.search_bt);
		frameLayout_vp = (FrameLayout) view.findViewById(R.id.home_fl_vp);
		listView = (ListView) view.findViewById(R.id.home_lv);
		dingGRX = (TextView) view.findViewById(R.id.orderTelTv);

		// 订购热线拨打电话
		dingGRX.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// 拨打电话，待
				MyToast.showToase(context, "打电话");
				return false;
			}
		});
		
		//搜索
		serchGL();

		return view;
	}

	//搜索  点击搜索判断输入框是否为空，不空就带数据到搜索结果页
	private void serchGL() {
		
		serach_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String content = search_et.getText().toString().trim();
				if (TextUtils.isEmpty(content)) {
					MyToast.showToase(context, "空内容");//可以谈对话框
					return;
				}
				Intent intent = new Intent();
				intent.putExtra("", content);
				// TODO ======================
				startActivity(intent);
			}
		});
	}
}
