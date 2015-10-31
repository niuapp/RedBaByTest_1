package com.itheima.redbaby.myutils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

public class Image_Utils{


	//通过url显示图片
	
	private static Bitmap bitmap;
	//
	public static Bitmap getBitmapByUrl(Context context, final String url){
		//判断本地是否有此图片，有就直接使用，没有就创建
		
		File dir = context.getCacheDir();
		//File dir = context.getCacheDir();
		
		final File file = new File(dir, url.substring(url.lastIndexOf("/")+1));
		if (file.exists()) {
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			return bitmap;
		}
		
		
		final Handler handler = new Handler(){
			
			public void handleMessage(android.os.Message msg) {
				
				bitmap = (Bitmap) msg.obj;
			}
		};
		
		//如果不存在就创建
		new Thread(){
			
			public void run() {
				//通过url开启连接，得到连接对象
				try {
					HttpURLConnection hConnection = (HttpURLConnection) new URL(url).openConnection();
				
					//判断状态码，获取图片对应的流
					if (hConnection.getResponseCode() == 200) {
						
						BufferedInputStream bis = new BufferedInputStream(hConnection.getInputStream());
						//保存流中的图片
						BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
						
						byte[] bys = new byte[1024];
						int l = 0;
						while ((l=bis.read(bys))!=-1){
							bos.write(bys, 0, l);
							bos.flush();
						}
						
						//得到图片
						//Bitmap bitmap = BitmapFactory.decodeStream(is);
						Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
						//得到消息对象，发送消息
						Message msg = Message.obtain();
						msg.obj = bitmap;
						handler.sendMessage(msg);
						
						bos.close();
						bis.close();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}.start();
		while(true){
			if (bitmap != null) {
				return bitmap;
			}
		}
	}
	
	
}
