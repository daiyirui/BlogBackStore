package com.back.biz;

import java.util.List;

import com.back.filter.PageBean;
import com.back.po.Admins;

public interface IAdminBiz {
	//insert 
	public boolean AddAdmins(Admins admin);
	//分页显示
	public PageBean SelectAdminsList(int currentPage,int pageSize);
	//获取单个admins对象信息
	public Admins SelectByaid(int aid);
	//更新后台用户
	public boolean UpdateAdmins(Admins admin);
	//删除后台用户
	public boolean DeleteAdmins(int aid);
	//后台用户登陆
	public Admins LoginBackStrone(String aname,String apwd);
	//导出excel
	public boolean ExportAdmins();
	//导入excel insert操作
	public String AddExcelAdmins(List<Admins> lisAdmins);
	
}
