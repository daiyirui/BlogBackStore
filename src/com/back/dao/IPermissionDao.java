package com.back.dao;

import java.util.List;

import com.back.filter.PageBean;
import com.back.po.Permissions;

public interface IPermissionDao {
    //添加后台用户权限
	public int AddPermission(Permissions per);
	//根据部分属性查询permission单个对象
	public Permissions FindByObject(Permissions per);
	//分页显示权限信息
	public PageBean FindByPage(String strSQL,int currentPage,int pageSize);
	//查询单个对象信息
	public Permissions FindBypid(int pid);
	//更新权限信息
	public int UpdatePermissions(Permissions per);
	//删除权限信息
	public int DeletePermissions(int pid);
	//获取全部权限集合信息
	public List<Permissions> FindAll();
	//excel导入，根据pname查询单个对象
	public Permissions FindBypname(String pname);
}
