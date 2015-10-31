package com.itheima.redbaby.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;

import com.itheima.redbaby.net.ThreadPoolManager;

/**
 * 图片处理工具类  核心是LRU算法,即:Least Recently Used的缩写，即最少使用页面置换算法，是为虚拟页式存储管理服务的。
 * 
 * @author ajun
 * 
 */
public class ImageUtil {
	/** 网络类型 0表示其他网络 1表示移动联通 2代表电信 */
	public static int NetType = 0;
	/** 中国移动联通wap代理网关 */
	public static final String proxyMobile = "10.0.0.172";
	/** 中国电信wap代理网关 */
	public static final String proxyTel = "10.0.0.200";
	public static final String CTWAP = "ctwap";
	public static final String CMWAP = "cmwap";
	public static final String WAP_3G = "3gwap";
	public static final String UNIWAP = "uniwap";
	public static final int HARD_CACHE_CAPACITY = 30;// 定义map集合存的图片数量
	public static final int TYPE_CM_CU_WAP = 4;// 移动联通wap10.0.0.172
	public static final int TYPE_CT_WAP = 5;// 电信wap 10.0.0.200
	public static final int TYPE_OTHER_NET = 6;// 电信,移动,联通,wifi 等net网络
	/**
	 * 常用数据池 WeakReference与SoftReference都属于软引用,只不过WeakReference是弱引用,而SoftReference是强引用
	 */
	public final static HashMap<String, SoftReference<Bitmap>> mHardBitmapCache = new LinkedHashMap<String, SoftReference<Bitmap>>(
			HARD_CACHE_CAPACITY / 2, 0.75f, true) {
		public static final long serialVersionUID = -6716765964916804088L;
		@Override
		protected boolean removeEldestEntry(LinkedHashMap.Entry<String, SoftReference<Bitmap>> eldest) {
			if (size() > HARD_CACHE_CAPACITY) {
				// 当map的size大于30时，把最近不常用的key放到mSoftBitmapCache中，从而保证mHardBitmapCache的效率
				SoftReference<Bitmap> value = eldest.getValue();
				if (value.get() != null) {
					mSoftBitmapCache.put(eldest.getKey(),new SoftReference<Bitmap>(value.get()));
				}
				return true;
			} else
				return false;
		}
	};
	protected static final String TAG = "ImageUtil";

	public static void clearAllMap() {
		mHardBitmapCache.clear();
		mSoftBitmapCache.clear();
		System.gc();
	}

	/**
	 * TODO 当mHardBitmapCache的key大于30的时候，会根据LRU算法把最近没有被使用的key放入到这个缓存中。
	 * Bitmap使用了SoftReference，当内存空间不足时，此cache中的bitmap会被垃圾回收掉
	 * ConcurrentHashMap是支持 高并发、高吞吐量的线程安全HashMap实现.
	 */
	public static Map<String, SoftReference<Bitmap>> mSoftBitmapCache = new ConcurrentHashMap<String, SoftReference<Bitmap>>(
			HARD_CACHE_CAPACITY / 2);

	/**
	 * TODO 从缓存中获取图片
	 */
	public static Bitmap getBitmapFromCache(String imagePath) {
		// 先从mHardBitmapCache缓存中获取
		synchronized (mHardBitmapCache) {
			if (mHardBitmapCache.containsKey(imagePath)) {
				SoftReference<Bitmap> softbitmap = mHardBitmapCache.get(imagePath);
				if (softbitmap != null) {
					final Bitmap bitmap = softbitmap.get();
					if (bitmap != null) {
						// 如果找到的话，把元素移到linkedhashmap的最前面，从而保证在LRU算法中是最后被删除
						mHardBitmapCache.remove(imagePath);
						mHardBitmapCache.put(imagePath,new SoftReference<Bitmap>(bitmap));
						return bitmap;
					}
				}
			}
		}
		// 如果mHardBitmapCache中找不到，到mSoftBitmapCache中找,但要先判断一下这里是否包含key,如果不包含肯定没有
		if (mSoftBitmapCache.containsKey(imagePath)) {
			SoftReference<Bitmap> bitmapReference = mSoftBitmapCache.get(imagePath);
			if (bitmapReference != null) {
				final Bitmap bitmap = bitmapReference.get();
				if (bitmap != null) {
					return bitmap;
				} else {
					// 如果key存在,但Soft里的Bitmap为空,那么清除这一组对象
					mSoftBitmapCache.remove(imagePath);
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * TODO @author 图片加载回调接口
	 * 
	 */
	public interface ImageCallback {
		public void loadImage(Bitmap bitmap, String imagePath);
	}

	/**
	 * 
	 * 方法描述： 创建人：ajun<br/>
	 * 参数名称：dir:本地缓存的目录 返回值：
	 */
	/**
	 * 默认获取本地或者服务端加载图片
	 * @param context
	 * @param dir 本地缓存的目录
	 * @param imgUrl 图片Url
	 * @param callback 回调函数
	 * @return Bitmap对象
	 */
	public static Bitmap loadImageDefault(Context context,File dir, String imgUrl,
			ImageCallback callback) {
		try {
			if (dir != null && imgUrl != null) {
				if (imgUrl.startsWith("http://")) {
					String imagePath = new File(dir, URLEncoder.encode(imgUrl,"utf-8")).getAbsolutePath();
					return loadImage(context, imagePath, imgUrl, callback);
				} else {
					return loadImage(context, imgUrl, null, callback);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
/**
 * 只从本地加载图片
 * @param dir 本地缓存的目录
 * @param imgUrl 图片Url
 * @param callback 回调函数
 * @return Bitmap对象
 */
	public static Bitmap loadLocalURLImage(Context context,File dir, String imgUrl,
			ImageCallback callback) {
		if (dir != null && imgUrl != null) {
			if (imgUrl.startsWith("http://")) {
				String imagePath = new File(dir, URLEncoder.encode(imgUrl)).getAbsolutePath();
				return loadImage(context,imagePath, null, callback);
			} else {
				return loadImage(context,imgUrl, null, callback);
			}
		}
		return null;

	}

	/**
	 * 方法描述：新:从本地或者服务端加载图片
	 * @param dir 本地缓存的目录
	 * @param imgUrl 图片Url
	 * @param callback 回调函数
	 * @return Bitmap对象
    */
	private static Bitmap loadImage(final Context context,final String imagePath,
			final String imgUrl, final ImageCallback callback) {
		Bitmap bitmap = null;
		if (imagePath != null)
			bitmap = getBitmapFromCache(imagePath);
		if (bitmap != null) {
			return bitmap;
		} else {// 从网上加载
			final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					if (msg.obj != null) {
						Bitmap bitmap = (Bitmap) msg.obj;
						putBitmapToMap(imagePath,bitmap);
						if (callback != null) {
							callback.loadImage(bitmap, imagePath);
						}
					}
				}
			};
			Runnable runnable = new Runnable() {
				@Override
				public int hashCode() {
					return 0;
				}

				@Override
				public void run() {
					try {
						Message msg = handler.obtainMessage();
						//检查本地缓存是否存在
						File imageFile = new File(imagePath);
						if (imageFile.exists() || imgUrl == null) {
							if (imageFile.exists()) {
								LogUtils.d("图片本地缓存加载url=:"+imagePath);
								putImageToMap(imagePath);
								msg.obj = getBitmapFromCache(imagePath);
								handler.sendMessage(msg);
							}
						} else {//不存在则从网络获取
							Bitmap bitmap = null;
							SharedPreferences sp= context.getSharedPreferences("config",Context.MODE_PRIVATE);
							// 仅在wifi下加载
							if (sp.getBoolean("isWifiLoad", false)) {
								if (!NetUtil.isWifi(context)) {
									return;
								}
							}
							URL url = new URL(imgUrl);
							LogUtils.d("图片从服务器加载url=:"+imgUrl);
							URLConnection conn = null;
							switch (NetType) {// 判断网络类型,开启网络代理访问服务器
							case TYPE_CM_CU_WAP:
								Proxy proxy = new Proxy(
										java.net.Proxy.Type.HTTP,
										new InetSocketAddress(
												proxyMobile,
												android.net.Proxy
														.getDefaultPort()));
								conn = url.openConnection(proxy);
								Log.v(TAG,
										"picture load by china_mobile 10.0.0.172");
								break;
							case TYPE_CT_WAP:
								Proxy proxyc = new Proxy(
										java.net.Proxy.Type.HTTP,
										new InetSocketAddress(
												proxyTel,
												android.net.Proxy
														.getDefaultPort()));
								conn = url.openConnection(proxyc);
								Log.v(TAG,
										"picture load by china_tel 10.0.0.200");
								break;
							case TYPE_OTHER_NET:
								conn = url.openConnection();
								Log.v(TAG, "picture load by WIFI or net");
								break;
							}
							conn = url.openConnection();
							conn.setConnectTimeout(5000);
							conn.setReadTimeout(5000);
							conn.connect();
							BufferedInputStream bis = new BufferedInputStream(
									conn.getInputStream(), 8192);
							if (bis != null) {
								bitmap = BitmapFactory.decodeStream(bis);
							}
							try {
								// 保存文件到sd卡
								saveToSD(imagePath, bitmap);
							} catch (Exception e) {
								e.printStackTrace();
								Log.e(ImageUtil.class.getName(), "保存图片至SD卡出错！");
							}
							msg.what = 0;
							msg.obj = bitmap;
							handler.sendMessage(msg);

						}
					} catch (IOException e) {
						Log.e(ImageUtil.class.getName(), "网络请求图片出错！");
						e.printStackTrace();
					}
				}
			};
			ThreadPoolManager.getInstance().addTask(runnable);
		}
		return null;
	}

	/**
	 * TODO 保存图片至sd卡
	 * 
	 * @param bitmap
	 */
	public static synchronized void saveToSD(final String fileName,
			final Bitmap bitmap) {
		if (bitmap == null || fileName == null) {
			return;
		}
		Runnable runnable = new Runnable() {
			@Override
			public int hashCode() {
				return 0;
			}

			public void run() {
				FileOutputStream b = null;
				try {
					b = new FileOutputStream(fileName);

					bitmap.compress(Bitmap.CompressFormat.PNG, 100, b);// 把数据写入文件
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					try {
						b.flush();
						b.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		};
		ThreadPoolManager.getInstance().addTask(runnable);

	}

/**
 * 保存图片到主线程中
 * @param pathName 
 * @param bitmap
 * @return 存储的路径
 */
	public static synchronized String saveToSD2(String pathName, Bitmap bitmap) {
		if (bitmap == null || pathName == null) {
			return null;
		}
		String fileName = null;
		if (hasSdcard()) {
			File directory = Environment.getExternalStorageDirectory();
			File file = new File(directory, pathName + "/");
			if (!file.exists()) {
				file.mkdirs();// 创建文件夹
			}
			fileName = new File(file, System.currentTimeMillis() + ".png")
					.getAbsolutePath();
			FileOutputStream b = null;
			try {
				b = new FileOutputStream(fileName);
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, b);// 把数据写入文件
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (b != null) {
						b.flush();
						b.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			System.out.println("没有sd卡");
		}
		return fileName;

	}

/**
 * 保存图片至sd卡 
 * @param bitmap bitmap对象
 * @param pathName 目录名
 * @return 存储路径
 */
	public static synchronized String saveToSD(Bitmap bitmap, String pathName) {
		if (bitmap == null || pathName == null) {
			return null;
		}
		if (hasSdcard()) {
			File directory = Environment.getExternalStorageDirectory();
			File file = new File(directory, pathName + "/");
			if (!file.exists()) {
				file.mkdirs();// 创建文件夹
			}
			String fileName = new File(file, System.currentTimeMillis()
					+ ".png").getAbsolutePath();
			saveToSD(fileName, bitmap);
			return fileName;
		} else {
			System.out.println("SD卡不存在");
		}
		return null;
	}
	/***
	 * 添加图片至本地相册
	 * @param bitmap 
	 * @param context
	 */
	public static synchronized void saveToDCIM(Bitmap bitmap,Context context) {
		if (bitmap == null) {
			return;
		}
		try {
			String insertImage = MediaStore.Images.Media.insertImage(
					context.getContentResolver(), bitmap, "", "");
			context.sendBroadcast(new Intent(
					Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"
							+ Environment.getExternalStorageDirectory())));
			System.out.println(insertImage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * 判断是否有SD卡 方法
 * @return 是否有SD卡 方法
 */
	public static boolean hasSdcard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * TODO 添加BitMap进map集合,并与路径对应
	 * 
	 * @param imagePath
	 *            String图片路径
	 */
	public static void putImageToMap(String imagePath) {
		Bitmap bitmap = getZoomBitmap(imagePath, 150f);
		if (bitmap != null) {
			mHardBitmapCache.put(imagePath, new SoftReference<Bitmap>(bitmap));
		}
	}

	/**
	 * 添加本地缓存进map集合,并与路径对应
	 * @param imagePath 图片路径
	 * @param isCompress 是否要压缩
	 */
	public static void putImageToMap(String imagePath,boolean isCompress) {
		if(isCompress){
			Bitmap bitmap = getZoomBitmap(imagePath, 150f);
			if (bitmap != null) {
				mHardBitmapCache.put(imagePath, new SoftReference<Bitmap>(bitmap));
			}
		}else{
			getHighBitmap(imagePath);
		}
	}
	/**
	 * 保存网络图片到map中
	 * @param imagePath 图片路径
	 * @param bitmap
	 */
	public static void putBitmapToMap(String imagePath,Bitmap bitmap) {
			if (bitmap != null) {
				mHardBitmapCache.put(imagePath, new SoftReference<Bitmap>(bitmap));
			}
	}
	
/**
 * 方法描述：不压缩直接获取一个图片
 * @param path 图片路径
 * @return Bitmap对象
 */
	public static Bitmap getSoftBitmap(String path) {
		SoftReference<Bitmap> bmp = null;
		bmp = mSoftBitmapCache.get(path);
		if (bmp != null && bmp.get() != null)
			return bmp.get();
		mSoftBitmapCache.remove(path);
		return null;
	}
/**
 * 从内存中取一张图片
 * @param path 图片路径
 * @return Bitmap
 */
	public static Bitmap getBitmap(String path) {
		Bitmap bmp = new SoftReference<Bitmap>(getZoomBitmap(path, 300F)).get();
		return bmp;
	}

	/**
	 * TODO 添加BitMap进map集合,并与路径对应
	 * 
	 * @param imagePath
	 *            String图片路径
	 */
	public static void putURLImageToMap(String imagePath, Bitmap bitmap) {
		if (!mHardBitmapCache.containsKey(imagePath)) {
			if (bitmap == null) {
				return;
			}
			mHardBitmapCache.put(imagePath, new SoftReference<Bitmap>(bitmap));
		}
	}

	public static void putSoftImageToMap(String imagePath, Bitmap bitmap) {
		if (!mSoftBitmapCache.containsKey(imagePath)) {
			if (bitmap == null) {
				return;
			}
			mSoftBitmapCache.put(imagePath, new SoftReference<Bitmap>(bitmap));
		}
	}

	/**
	 * TODO 缩放图片
	 * 
	 * @param imagePath
	 * @param scale 压缩比率 可以为空,那么是默认是200,这个值越大则 压缩比越小  如果压缩率是0,则表示不压缩
	 * @return 一个缩放好的bitmap
	 */
	public static Bitmap getZoomBitmap(String imagePath, Float scale) {
		if (!new File(imagePath).exists())
			return null;
		if (scale == null){
			scale = 200f;
		}
		Bitmap bm=null;
		if(scale>0){
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			bm = BitmapFactory.decodeFile(imagePath, options);
			int be = (int) (options.outHeight / (float) scale);
			if (be <= 1) {
				be = 1;
			}
			options.inSampleSize = be;// be=2.表示压缩为原来的1/2,以此类推
			options.inJustDecodeBounds = false;
			bm = BitmapFactory.decodeFile(imagePath, options);
		}else{
			bm = BitmapFactory.decodeFile(imagePath);
		}
		return bm;
	}
	/**
	 * 获取原版图片不压缩
	 * @param dir 图片存在的目录
	 * @param imgUrl 图片名
	 * @return
	 */
	public static Bitmap getHighBitmap(File dir,String imgUrl) {
		String imagePath = new File(dir, URLEncoder.encode(imgUrl)).getAbsolutePath();
		if (!new File(imagePath).exists())
			return null;
		Bitmap bm = BitmapFactory.decodeFile(imagePath);
		return bm;
	}
	/**
	 * 获取原版图片不压缩
	 * @param imagePath 图片路径
	 * @return
	 */
	public static Bitmap getHighBitmap(String imagePath) {
		Bitmap bm = BitmapFactory.decodeFile(imagePath);
		return bm;
	}
}
