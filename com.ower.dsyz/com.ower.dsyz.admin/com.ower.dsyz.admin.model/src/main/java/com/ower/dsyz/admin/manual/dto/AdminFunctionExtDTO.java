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
	List<AdminFunctionExtDTO> childrens;

	public List<AdminFunctionExtDTO> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<AdminFunctionExtDTO> childrens) {
		this.childrens = childrens;
	}

	@Override
	public String toString() {
		return "AdminFunctionExtDTO [childrens=" + childrens + "]";
	}
	
	
}
