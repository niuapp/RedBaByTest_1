package com.itheima.redbaby.myutils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/10/20.
 * Toast工具
 */
public class MyToast {

    private static Toast toast;
    private static Handler handler = new Handler();
    public static void showThreadToase(Context context, String text){
        if (toast == null){
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }else{
            toast.setText(text);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                toast.show();
            }
        });
    }
    public static void showToase(Context context, String text){
        if (toast == null){
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }else{
            toast.setText(text);
        }
        toast.show();
    }
}
