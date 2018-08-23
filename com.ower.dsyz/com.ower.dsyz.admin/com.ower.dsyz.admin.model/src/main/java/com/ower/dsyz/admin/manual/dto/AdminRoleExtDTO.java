/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.admin.manual.dto;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年7月25日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class AdminRoleExtDTO extends AdminRoleDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 机构id
	 */
	private String insId;
	
	/**
	 * 用户id
	 */
	private String userId;

	public String getInsId() {
		return insId;
	}

	public void setInsId(String insId) {
		this.insId = insId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AdminRoleExtDTO [insId=" + insId + ", userId=" + userId + "]";
	}
	
	
}

