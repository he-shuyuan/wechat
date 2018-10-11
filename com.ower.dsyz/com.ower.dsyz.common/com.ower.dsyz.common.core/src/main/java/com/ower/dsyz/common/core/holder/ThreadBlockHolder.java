package com.ower.dsyz.common.core.holder;

public abstract class ThreadBlockHolder {

	/**
	 * 默认10毫秒轮询
	 */
	private Long sleepMin=10L;
	
	/**
	 * 默认超时时间5秒
	 */
	private Long timeOut=5000L;
	
	/**
	 * 已经组赛时间
	 */
	private Long blockTime=0L;
	
	
	public ThreadBlockHolder(){
		
	}
	
	public ThreadBlockHolder(Long sleepMin,Long timeOut){
		this.sleepMin = sleepMin;
		this.timeOut = timeOut;
	}
	
	public void run() throws InterruptedException{
		while(blockCondition()){
			Thread.sleep(sleepMin);
			blockTime+=sleepMin;
			if(blockTime>timeOut){
				break;
			}
		}
	}
	
	/**
	 * 阻塞条件，true 阻塞，false 继续
	 * @return
	 */
	protected abstract Boolean blockCondition();

	/**
	 * 启动方法
	 */
	public void block(){
		try {
			this.run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
