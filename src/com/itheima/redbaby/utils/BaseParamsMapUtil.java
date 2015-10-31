package com.itheima.redbaby.utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * 请求参数工具类
 * 
 * @author ajun
 * 
 */
public class BaseParamsMapUtil {
	private static String imei;
	private static String imsi;
	private static String mac;
	private static String xid;// imei和mac的md5值
	private static String cid;// 渠道号
	private static String pkName;// 包名

	/**
	 * 通用标准参数
	 * 
	 * @return 返回请求键值对Map
	 */
	public static Map<String, String> getParamsMap(Context context) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("appkey", "321");
		paramsMap.put("udid", "156151");
		return paramsMap;
	}

	public static Map<String, String> getHelp(String version) {
		// Map<String, String> paramsMap = getParamsMap(context);
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("version", version);
		return paramsMap;
	}
	
	public static Map<String, String> getHelp_Detail(String id) {
		// Map<String, String> paramsMap = getParamsMap(context);
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("id", id);
		return paramsMap;
	}
	

	public static Map<String, String> getTopic(String page, String pageNum) {
		// Map<String, String> paramsMap = getParamsMap(context);
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("page", page);
		paramsMap.put("pageNum", pageNum);

		return paramsMap;

	}

	public static Map<String, String> getLimitbuy(String page, String pageNum,
			String orderby) {
		// Map<String, String> paramsMap = getParamsMap(context);
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("page", page);
		paramsMap.put("pageNum", pageNum);
		paramsMap.put("orderby", orderby);

		return paramsMap;

	}

	/**
	 * push软件接口
	 * 
	 * @param context
	 *            上下文
	 * @param ver
	 *            备注
	 * @return 返回请求键值对Map
	 */
	public static Map<String, String> getPush(Context context, String ver) {
		Map<String, String> paramsMap = getParamsMap(context);
		paramsMap.put("interfaceName", "getPush");
		paramsMap.put("ver", ver == null ? "" : ver);
		return paramsMap;
	}

	/**
	 * 启动应用提交
	 * 
	 * @param context
	 *            上下文
	 * @param ver
	 *            备注
	 * @return 返回请求键值对Map
	 */
	public static Map<String, String> getOnCreate(Context context, String ver) {
		Map<String, String> paramsMap = getParamsMap(context);
		paramsMap.put("interfaceName", "startApp");
		paramsMap.put("ver", ver == null ? "" : ver);
		return paramsMap;
	}

	/**
	 * ActivityOnPause提交
	 * 
	 * @param context
	 * @param ver
	 * @return 返回请求键值对Map
	 */
	public static Map<String, String> getOnPause(Context context, String ver) {
		Map<String, String> paramsMap = getParamsMap(context);
		paramsMap.put("interfaceName", "onPause");
		paramsMap.put("ver", ver == null ? "" : ver);
		return paramsMap;
	}

	/**
	 * 退出应用提交
	 * 
	 * @param context
	 * @param ver
	 * @return 返回请求键值对Map
	 */
	public static Map<String, String> getOnDestroy(Context context, String ver) {
		Map<String, String> paramsMap = getParamsMap(context);
		paramsMap.put("interfaceName", "exitApp");
		paramsMap.put("ver", ver == null ? "" : ver);
		return paramsMap;
	}

	/**
	 * 启动服务提交
	 * 
	 * @param context
	 * @param ver
	 * @return 返回请求键值对Map
	 */
	public static Map<String, String> getOnStartService(Context context,
			String location, String ver) {
		Map<String, String> paramsMap = getParamsMap(context);
		paramsMap.put("interfaceName", "startService");
		paramsMap.put("location", location);
		paramsMap.put("ver", ver == null ? "" : ver);
		return paramsMap;
	}

	/**
	 * 捕获错误提交
	 * 
	 * @param context
	 * @param ver
	 * @param error
	 *            错误信息
	 * @return 返回请求键值对Map
	 */
	public static Map<String, String> getOnError(Context context, String error,
			String ver) {
		Map<String, String> paramsMap = getParamsMap(context);
		paramsMap.put("interfaceName", "submitError");
		paramsMap.put("error", error);
		paramsMap.put("ver", ver == null ? "" : ver);
		return paramsMap;
	}

	/**
	 * 退出服务提交
	 * 
	 * @param context
	 * @param ver
	 * @return 返回请求键值对Map
	 */
	public static Map<String, String> getExitService(Context context, String ver) {
		Map<String, String> paramsMap = getParamsMap(context);
		paramsMap.put("interfaceName", "exitService");
		paramsMap.put("ver", ver == null ? "" : ver);
		return paramsMap;
	}

	/**
	 * 作弊提交
	 * 
	 * @param context
	 *            上下文
	 * @param name
	 *            注册的所有类名
	 * @param ver
	 *            备注
	 * @return 返回请求键值对Map
	 */
	public static Map<String, String> submitCheatCid(Context context,
			String name, String ver) {
		Map<String, String> paramsMap = getParamsMap(context);
		paramsMap.put("interfaceName", "submitCheatCid");
		paramsMap.put("name", name);
		paramsMap.put("ver", ver == null ? "" : ver);
		return paramsMap;
	}

	public static Map<String, String> getVersionUpdate(Context context,
			String ver) {
		Map<String, String> paramsMap = getParamsMap(context);
		paramsMap.put("interfaceName", "versionUpdate");
		paramsMap.put("ver", ver == null ? "" : ver);
		return paramsMap;
	}

}
