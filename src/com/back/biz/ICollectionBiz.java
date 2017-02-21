package com.back.biz;

import com.back.filter.PageBean;

public interface ICollectionBiz {
	//分页显示
	public PageBean SelectByPage(int currentPage,int pageSize);
	//批量删除
	public boolean DeleteMastCollection(String[] uidc);
	//单个删除
	public boolean DeleteCollection(int lid);
	//禁用
	public boolean StopCollection(int lid);
	//反禁用
	public boolean LifeCollection(int lid);
}
