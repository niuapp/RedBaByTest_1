package com.itheima.redbaby.myutils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/10/20.
 */
public class MyTime {
    /**
     * @return 返回当前时间 月 日 时 分 秒
     */
    public static String getCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }
    /**
     * @return 返回时间  时 分 秒
     */
    public static String getCurrentTime_sfm(long time){
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" HH:mm:ss");
    	return simpleDateFormat.format(new Date(time));
    }

    /**
     * @return 返回当前时间 月 日 时 分
     */
    public static String getSimpleCurrentTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        return simpleDateFormat.format(new Date());
    }
}
