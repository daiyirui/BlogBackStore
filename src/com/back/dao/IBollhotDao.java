package com.back.dao;

import java.util.List;

import com.back.po.Bloghot;
import com.back.po.Bloghotitem;


public interface IBollhotDao {
	//显示所有微博内容，按投票进行排序
	public List<Bloghot> FindAllHot();
	//单个热议内容下面的子选项
	public List<Bloghotitem> FindAllHotItem(Integer bid);
	//添加热议内容
	public int addHot(Bloghot hot);
	//添加对应热议内容的子选项
	public int addItem(Bloghotitem item);
}
