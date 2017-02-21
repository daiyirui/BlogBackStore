package com.back.biz;

import com.back.filter.PageBean;

public interface IWeiboBiz {
	//分页显示微博信息
	public PageBean SelectByPage(int currentPage,int pageSize);
	//单个删除微博
	public boolean DeleteWeibo(int wid);
	//禁用微博
	public boolean StopWeibo(int wid);
	//反禁用微博
	public boolean LifeWeibo(int wid);
	//批量删除微博信息
	public boolean DeleteMastWeibo(String[] widc);
}
