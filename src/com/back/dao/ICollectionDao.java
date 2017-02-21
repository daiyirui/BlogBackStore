package com.back.dao;

import com.back.filter.PageBean;

public interface ICollectionDao {
  //分页显示
	public PageBean FindByPage(String strSQL,int currentPage,int pageSize);
	//批量删除
	public int DeleteMastCollection(String[] uidc);
	//单个删除
	public int DeleteCollection(int lid);
	//禁用
	public int StopCollection(int lid);
	//反禁用
	public int LifeCollection(int lid);
}
