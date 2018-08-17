package com.ower.dsyz.common.core.request;

import java.io.Serializable;



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
	}

	@Override
	public String toString() {
		return "CustomLoginUserId [userId=" + userId + "]";
	}
	
	
}
