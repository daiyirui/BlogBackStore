package com.back.dao;

import com.back.filter.PageBean;

public interface IUserDao {
    //分页显示前台用户
	public PageBean FindUserByPage(String strSQL,int currentPage,int pageSize);
	//delete users
	public int DeleteUsers(int uid);
	//禁用前台用户
	public int StopUsers(int uid);
	//反禁用前台用户
	public int LifeUsers(int uid);
	//批量删除前台用户
	public int DeleteMastUsers(String[] uidc);
}
