package com.ower.dsyz.admin.manual.dto;

import java.util.List;

/**
 * 用户登录的机构信息
 * @author Administrator
 *
 */
public class UserLoginInsInfo {

	private String userId;
	
	private String busTypeId;
	
	private List<AdminDepartmentDTO> depList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBusTypeId() {
		return busTypeId;
	}

	public void setBusTypeId(String busTypeId) {
		this.busTypeId = busTypeId;
	}

	public List<AdminDepartmentDTO> getDepList() {
		return depList;
	}

	public void setDepList(List<AdminDepartmentDTO> depList) {
		this.depList = depList;
	}

	@Override
	public String toString() {
		return "UserLoginInsInfo [userId=" + userId + ", busTypeId=" + busTypeId + ", depList=" + depList + "]";
	}
	
	
	
}
