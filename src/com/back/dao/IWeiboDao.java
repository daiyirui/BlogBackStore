package com.back.dao;

import com.back.filter.PageBean;

public interface IWeiboDao {
    //分页显示微博信息
	public PageBean FindByPage(String strSQL,int currentPage,int pageSize);
	//单个删除微博
	public int DeleteWeibo(int wid);
	//禁用微博
	public int StopWeibo(int wid);
	//反禁用微博
	public int LifeWeibo(int wid);
	//批量删除微博信息
	public int DeleteMastWeibo(String[] widc);
}
