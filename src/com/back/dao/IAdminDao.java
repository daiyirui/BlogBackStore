package com.back.dao;

import java.util.List;

import com.back.filter.PageBean;
import com.back.po.Admins;

public interface IAdminDao {
    //insert 
	public int AddAdmins(Admins admin);
	//分页显示后台用户信息
	public PageBean AdminsList(String strSQL,int currentPage,int pageSize);
	//获取单个admins对象信息
	public Admins FindByaid(int aid);
	//更新后台用户
	public int UpdateAdmins(Admins admin);
	//删除后台用户
	public int DeleteAdmins(int aid);
	//后台用户登陆
	public Admins LoginBackStrone(String aname,String apwd);
	//导出excel
	public List<Admins> ExportAdmins();
	//导入excel insert操作
	//public int AddExcelAdmins(Admins admin);
}
