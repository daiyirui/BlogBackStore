package com.back.dao;

import java.util.List;

import com.back.filter.PageBean;
import com.back.po.Users;

public interface IRelationsDao {
   //分页显示
	public PageBean FindByPage(String strSQL,int currentPage,int pageSize);
	//获取关注好友结合
	public List<Users> FindByuid(int uid);
}
