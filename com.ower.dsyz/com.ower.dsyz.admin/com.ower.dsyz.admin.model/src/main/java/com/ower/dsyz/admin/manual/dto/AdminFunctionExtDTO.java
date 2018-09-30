package com.ower.dsyz.admin.manual.dto;

import java.util.List;


public class AdminFunctionExtDTO extends AdminFunctionDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 子菜单列表
	 */
	List<AdminFunctionExtDTO> children;

	public List<AdminFunctionExtDTO> getChildren() {
		return children;
	}

	public void setChildren(List<AdminFunctionExtDTO> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "AdminFunctionExtDTO [children=" + children + "]";
	}
	
	
}
