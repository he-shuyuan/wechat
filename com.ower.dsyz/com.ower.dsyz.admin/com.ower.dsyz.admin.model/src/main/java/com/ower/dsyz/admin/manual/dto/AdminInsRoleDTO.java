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
 * @date 2018年7月26日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class AdminInsRoleDTO extends AdminRoleDTO {

	/**
	 * serialVersionUID:TODO。
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 机构id
	 */
	private String insId;
	/**
	 * 是否分配
	 */
	private String dist;

	
	public String getInsId() {
		return insId;
	}

	public void setInsId(String insId) {
		this.insId = insId;
	}


	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

    @Override
    public String toString() {
        return "AdminInsRoleDTO [insId=" + insId + ", dist=" + dist + "]";
    }


}
