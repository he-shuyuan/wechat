package com.ower.dsyz.common.core.bean;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class CustomTreadPool {
	public static final int cpuNums = Runtime.getRuntime().availableProcessors();  //24
	private static final int POOL_SIZE = 2;// 单个CPU线程池大小
	
	private int size = 100*10;
	private  BlockingQueue<Runnable> queue;
	public  ThreadPoolExecutor poolExecutor;
	
	private String name = "thread_"+System.currentTimeMillis();
	
	public CustomTreadPool(int size,String name){
		this.size = size;
	    this.name = name;
	    this.initThreadPool();
	}
    
	public CustomTreadPool(){
	    this.initThreadPool();
	}
	
	private void initThreadPool(){
		this.queue = new LinkedBlockingQueue<Runnable>(this.size);
		this.poolExecutor = new ThreadPoolExecutor(cpuNums*POOL_SIZE, cpuNums*POOL_SIZE*2, 30, TimeUnit.SECONDS, queue,new ThreadFactory() {
	        private AtomicLong threadIndex = new AtomicLong(0);
			@Override
	        public Thread newThread(Runnable r) {
	            return new Thread(r,CustomTreadPool.this.name+"_"+ threadIndex.incrementAndGet());
	        }
		}); // 线程池主
		this.poolExecutor.prestartAllCoreThreads();
	}

	public ThreadPoolExecutor getPoolExecutor() {
		return poolExecutor;
	}
}
