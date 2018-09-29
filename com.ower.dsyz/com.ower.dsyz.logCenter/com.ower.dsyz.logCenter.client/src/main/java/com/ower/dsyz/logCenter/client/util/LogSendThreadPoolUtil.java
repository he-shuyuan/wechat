package com.ower.dsyz.logCenter.client.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class LogSendThreadPoolUtil {

	private static final int MAX_CONC = 10000*10;
	public static final int cpuNums = Runtime.getRuntime().availableProcessors();  //24
	private static final int POOL_SIZE = 2;// 单个CPU线程池大小
	private static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(MAX_CONC);
	public static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(cpuNums*POOL_SIZE, cpuNums*POOL_SIZE*2, 30, TimeUnit.SECONDS, queue,new ThreadFactory() {
        private AtomicLong threadIndex = new AtomicLong(0);
		@Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "_log_send_" + threadIndex.incrementAndGet());
        }
	}); // 线程池主
	
	
	static{
		poolExecutor.prestartAllCoreThreads();
	}    
}
