package com.back.dao;

import com.back.filter.PageBean;
import com.back.po.Bloghot;

public interface IHotblogDao {
    //添加热议
	public int AddHotBlog(Bloghot hot,String[] items);
	//分页显示
	public PageBean FindByPage(String strSQL);
	//批量删除
	public int DeleteMastHotblog(String[] uidc);
	//单个删除
	public int DeleteHotblog(String btitle);
	//禁用
	public int StopHotblog(String btitle);
	//反禁用
	public int LifeHotblog(String btitle);
}
