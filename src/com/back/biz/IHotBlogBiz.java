package com.back.biz;

import com.back.filter.PageBean;
import com.back.po.Bloghot;

public interface IHotBlogBiz {
	 //添加热议
	public String AddHotBlog(Bloghot hot,String[] items);
	//分页显示
	public PageBean SelectByPage();
	//批量删除
	public boolean DeleteMastHotblog(String[] uidc);
	//单个删除
	public boolean DeleteHotblog(String btitle);
	//禁用
	public boolean StopHotblog(String btitle);
	//反禁用
	public String LifeHotblog(String btitle);
}
