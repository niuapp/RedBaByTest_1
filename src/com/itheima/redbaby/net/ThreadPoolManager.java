package com.itheima.redbaby.net;

import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * 线程池工具类
 * @author ajun
 *
 */
public class ThreadPoolManager {
	private ThreadPoolManager(){
		num = Runtime.getRuntime().availableProcessors();
		/**
		 * 进行优先级处理 
		 */
		Comparator<? super Runnable> comparator=new Comparator<Runnable>() {
			@Override
			public int compare(Runnable lhs, Runnable rhs) {
				return lhs.hashCode()>rhs.hashCode()? 1:-1;
			}
		};
		//无界阻塞队列
		//使用指定的初始容量创建一个 PriorityBlockingQueue，并根据指定的比较器对其元素进行排序。
		workQueue = new PriorityBlockingQueue<Runnable>(num*10, comparator);
		
//		corePoolSize - 池中所保存的线程数，包括空闲线程。
//		maximumPoolSize - 池中允许的最大线程数。
//		keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
//		unit - keepAliveTime 参数的时间单位。
//		workQueue - 执行前用于保持任务的队列。此队列仅保持由 execute 方法提交的 Runnable 任务。
//		handler - 由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序。
		executor = new ThreadPoolExecutor(num*2, num*2, 8, TimeUnit.SECONDS, workQueue, new ThreadPoolExecutor.CallerRunsPolicy());
	}
	
	private static final ThreadPoolManager manager= new ThreadPoolManager();
	public int num;
	private ThreadPoolExecutor executor;
	private PriorityBlockingQueue<Runnable> workQueue;
	
	public ExecutorService getService() {
		return executor;
	}
	public static ThreadPoolManager getInstance(){
		return manager;
	}
	/**
	 * 方法描述：关闭线程池不再接受新的任务
	 */
	public void stopReceiveTask(){
		if (!executor.isShutdown()) {
			executor.shutdown();
		}
	}
	/**
	 * 方法描述：停止所有线程,包括等待
	 */
	public void stopAllTask(){
		if (!executor.isShutdown()) {
			executor.shutdownNow();
		}
	}
	/**
	 * 添加一个新任务
	 * @param runnable 任务的Runnable对象
	 */
	public void addTask(Runnable runnable){
		if(executor.isShutdown()){
			executor = new ThreadPoolExecutor(num*2, num*2, 8, TimeUnit.SECONDS, workQueue, new ThreadPoolExecutor.CallerRunsPolicy());
		}
		executor.execute(runnable);
	}
}
