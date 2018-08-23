package com.ower.dsyz.admin.manual.dto;

import java.util.List;

import com.ower.dsyz.admin.auto.model.AdminRole;

/**
 * 用户登陆菜单信息
 * @author Administrator
 *
 */
public class UserLoginFunInfo {

	/**
	 * 角色列表
	 */
	List<AdminRole> roleList;
	
	/**
	 * 菜单列表
	 */
	List<AdminFunctionExtDTO> menuList;

	public List<AdminRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<AdminRole> roleList) {
		this.roleList = roleList;
	}

	public List<AdminFunctionExtDTO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<AdminFunctionExtDTO> menuList) {
		this.menuList = menuList;
	}

	@Override
	public String toString() {
		return "UserLoginFunInfo [roleList=" + roleList + ", menuList=" + menuList + "]";
	}
	
	
}
