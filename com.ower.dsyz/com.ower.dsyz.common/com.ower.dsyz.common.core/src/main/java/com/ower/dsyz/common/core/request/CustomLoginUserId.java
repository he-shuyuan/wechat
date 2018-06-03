package com.ower.dsyz.common.core.request;

import java.io.Serializable;

import com.ower.dsyz.common.core.holder.CustomLoginUserIdHolder;


public class CustomLoginUserId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 当前用户id
	 */
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
		CustomLoginUserIdHolder.set(this.userId);
	}

	@Override
	public String toString() {
		return "CustomLoginUserId [userId=" + userId + "]";
	}
	
	
}
