package com.back.biz;

import java.util.List;

import com.back.filter.PageBean;
import com.back.po.Permissions;

public interface IPermissionBiz {
   //添加权限
	public boolean AddPermission(Permissions per,String[] items);
	//分页显示权限信息
	public PageBean SelectByPage(int currentPage,int pageSize);
	//查询单个对象信息
	public Permissions SelectBypid(int pid);
	//更新权限信息
	public boolean UpdatePermissions(Permissions per);
	//删除权限信息
	public boolean DeletePermissions(int pid);
	//获取全部权限集合信息
	public List<Permissions> SelectAll();
}
