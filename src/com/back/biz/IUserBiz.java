package com.back.biz;

import com.back.filter.PageBean;

public interface IUserBiz {
	//分页显示前台用户
	public PageBean SelectUserByPage(int currentPage,int pageSize);
	//delete users
	public boolean DeleteUsers(int uid);
	//禁用前台用户
	public boolean StopUsers(int uid);
	//反禁用前台用户
	public boolean LifeUsers(int uid);
	//批量删除前台用户
	public boolean DeleteMastUsers(String[] uidc);
}
